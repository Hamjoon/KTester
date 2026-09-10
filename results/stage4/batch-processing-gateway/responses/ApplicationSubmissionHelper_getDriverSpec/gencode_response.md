```java
package com.apple.spark.core;

import com.apple.spark.AppConfig;
import com.apple.spark.api.SubmitApplicationRequest;
import com.apple.spark.operator.DriverSpec;
import com.apple.spark.operator.AppConfig.SparkCluster;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class ApplicationSubmissionHelper_getDriverSpec_Test {
    private SubmitApplicationRequest request;
    private AppConfig appConfig;
    private SparkCluster sparkCluster;
    private String parentQueue;

    @BeforeAll
    static void setupBeforeAll() {
        // This method can be used for one-time setup before all tests, if needed
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize necessary fields and dependencies before each test
        request = mock(SubmitApplicationRequest.class);
        appConfig = new AppConfig();
        sparkCluster = mock(SparkCluster.class);
        parentQueue = "parentQueue"; // Example value, adjust as necessary
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup after each test if necessary
    }

    @AfterAll
    static void teardownAfterAll() {
        // This method can be used for one-time cleanup after all tests, if needed
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
    void testGetDriverSpec_withDriverVolumesPriority() {
        // Arrange: Set up the request with driver volumes
        DriverSpec driver = mock(DriverSpec.class);
        when(driver.getVolumeMounts()).thenReturn(new String[]{"mount1"});
        when(request.getDriver()).thenReturn(driver);
        when(request.getVolumes()).thenReturn(new String[]{"volume1"});
        when(sparkCluster.getDriver()).thenReturn(mock(DriverSpec.class));
        
        // Act: Call the method under test
        DriverSpec driverSpec = ApplicationSubmissionHelper.getDriverSpec(request, appConfig, parentQueue, sparkCluster);

        // Assert: Validate the results
        assertArrayEquals(new String[]{"mount1"}, driverSpec.getVolumeMounts());
    }

    @Test
    void testGetDriverSpec_withSparkClusterVolumes() {
        // Arrange: Set up the request with no driver volumes
        when(request.getDriver()).thenReturn(null);
        when(sparkCluster.getDriver()).thenReturn(mock(DriverSpec.class));
        when(sparkCluster.getDriver().getVolumeMounts()).thenReturn(new String[]{"mount2"});
        when(request.getVolumes()).thenReturn(new String[]{"volume1"});

        // Act: Call the method under test
        DriverSpec driverSpec = ApplicationSubmissionHelper.getDriverSpec(request, appConfig, parentQueue, sparkCluster);

        // Assert: Validate the results
        assertArrayEquals(new String[]{"mount2"}, driverSpec.getVolumeMounts());
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

        // Simulate that there are no affinity settings
        when(request.getDriver().getAffinity()).thenReturn(null); 

        // Act: Call the method under test
        DriverSpec driverSpec = ApplicationSubmissionHelper.getDriverSpec(request, appConfig, parentQueue, sparkCluster);

        // Assert: Validate the results
        assertNotNull(driverSpec.getAffinity()); // Check if affinity is set
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

    // Additional test methods can be added to cover more scenarios
}
```