package com.apple.spark.core;

import com.apple.spark.AppConfig;
import com.apple.spark.api.SubmitApplicationRequest;
import com.apple.spark.operator.DriverSpec;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import java.util.HashMap;
import java.util.Map;
import org.mockito.junit.jupiter.MockitoExtension;
import com.apple.spark.AppConfig.SparkCluster;
import static com.apple.spark.AppConfig.SparkCluster;
// Corrected import for List
import java.util.List;
// Corrected import
// Assuming Volume class exists
import com.apple.spark.operator.Volume;
// Assuming VolumeMount class exists
import com.apple.spark.operator.VolumeMount;

@ExtendWith(MockitoExtension.class)
class ApplicationSubmissionHelper_getDriverSpec_Test {

    private SubmitApplicationRequest request;

    private AppConfig appConfig;

    private SparkCluster sparkCluster;

    private String parentQueue;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize necessary fields and dependencies before each test
        request = mock(SubmitApplicationRequest.class);
        appConfig = new AppConfig();
        sparkCluster = mock(SparkCluster.class);
        parentQueue = "parentQueue";
        // Initialize appConfig.getQueues() to avoid NullPointerException
        // Assuming this method exists to set queues
//         appConfig.setQueues(new ArrayList<>());
    }

    @Test
    void testGetDriverSpec_withValidRequest() {
        // Arrange: Set up the mock request and its expected behavior
        DriverSpec driverMock = mock(DriverSpec.class);
        when(request.getDriver()).thenReturn(driverMock);
        when(driverMock.getCores()).thenReturn(1);
        when(driverMock.getMemory()).thenReturn("1g");
        // Act: Call the method under test
        DriverSpec driverSpec = ApplicationSubmissionHelper.getDriverSpec(request, appConfig, parentQueue, sparkCluster);
        // Assert: Validate the results
        assertNotNull(driverSpec);
    }

    @Test
    void testGetDriverSpec_withNullDriver() {
        // Arrange: Set up the mock request with a null driver
        when(request.getDriver()).thenReturn(null);
        when(sparkCluster.getSparkServiceAccount()).thenReturn("default");
        // Act: Call the method under test
        DriverSpec driverSpec = ApplicationSubmissionHelper.getDriverSpec(request, appConfig, parentQueue, sparkCluster);
        // Assert: Validate the results
        assertNotNull(driverSpec);
        assertEquals("default", driverSpec.getServiceAccount());
    }

    @Test
    void testGetDriverSpec_withDriverAndLabels() {
        // Arrange: Set up the mock request with a driver and labels
        DriverSpec driver = mock(DriverSpec.class);
        Map<String, String> labels = new HashMap<>();
        labels.put(Constants.DAG_NAME_LABEL, "testDAG");
        labels.put(Constants.TASK_NAME_LABEL, "testTask");
        when(request.getDriver()).thenReturn(driver);
        when(driver.getLabels()).thenReturn(labels);
        when(sparkCluster.getSparkServiceAccount()).thenReturn("default");
        // Act: Call the method under test
        DriverSpec driverSpec = ApplicationSubmissionHelper.getDriverSpec(request, appConfig, parentQueue, sparkCluster);
        // Assert: Validate the results
        assertNotNull(driverSpec);
        assertEquals("default", driverSpec.getServiceAccount());
        assertEquals(labels, driverSpec.getLabels());
    }

    @Test
    void testGetDriverSpec_withDriverCoreAndMemory() {
        // Arrange: Set up the request with driver core and memory values
        DriverSpec driver = mock(DriverSpec.class);
        when(driver.getCores()).thenReturn(4);
        when(driver.getMemory()).thenReturn("4G");
        when(request.getDriver()).thenReturn(driver);
        when(sparkCluster.getSparkServiceAccount()).thenReturn("default");
        // Act: Call the method under test
        DriverSpec driverSpec = ApplicationSubmissionHelper.getDriverSpec(request, appConfig, parentQueue, sparkCluster);
        // Assert: Validate the results
        assertEquals(4, driverSpec.getCores());
        assertEquals("4G", driverSpec.getMemory());
    }

    @Test
    void testGetDriverSpec_withAffinitySettings() {
        // Arrange: Set up the request with an empty driver
        when(request.getDriver()).thenReturn(mock(DriverSpec.class));
        when(sparkCluster.getSparkServiceAccount()).thenReturn("default");
        when(request.getDriver().getAffinity()).thenReturn(null);
        // Act: Call the method under test
        DriverSpec driverSpec = ApplicationSubmissionHelper.getDriverSpec(request, appConfig, parentQueue, sparkCluster);
        // Assert: Validate the results
        assertNotNull(driverSpec.getAffinity());
    }

    @Test
    void testGetDriverSpec_withLargeCoreAndMemoryValues() {
        // Arrange: Set up the request with large core and memory values
        DriverSpec driver = mock(DriverSpec.class);
        when(driver.getCores()).thenReturn(100);
        when(driver.getMemory()).thenReturn("100G");
        when(request.getDriver()).thenReturn(driver);
        when(sparkCluster.getSparkServiceAccount()).thenReturn("default");
        // Act: Call the method under test
        DriverSpec driverSpec = ApplicationSubmissionHelper.getDriverSpec(request, appConfig, parentQueue, sparkCluster);
        // Assert: Validate the results
        assertEquals(100, driverSpec.getCores());
        assertEquals("100G", driverSpec.getMemory());
    }
}