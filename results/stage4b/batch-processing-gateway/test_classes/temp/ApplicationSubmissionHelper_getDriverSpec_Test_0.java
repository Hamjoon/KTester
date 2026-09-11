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
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.HashMap;

@ExtendWith(MockitoExtension.class)
class ApplicationSubmissionHelper_getDriverSpec_Test {

    private SubmitApplicationRequest request;

    private AppConfig appConfig;

    private SparkCluster sparkCluster;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the necessary fields and dependencies before each test
        // Assuming there's a default constructor
        request = new SubmitApplicationRequest();
        // Using default constructor
        appConfig = new AppConfig();
        // Mocking SparkCluster for testing
        sparkCluster = mock(SparkCluster.class);
    }

    @Test
    void testGetDriverSpec() {
        // Given: Set up the request and appConfig with necessary values
        // Populate the request object with expected values
        // For example:
        // request.setDriver(...); // Set up the driver spec in the request
        // appConfig.setDefaultSparkConf(...); // Set any required configurations
        // When: Call the method under test
        DriverSpec driverSpec = ApplicationSubmissionHelper.getDriverSpec(request, appConfig, "parentQueue", sparkCluster);
        // Then: Assert the expected outcomes
        // For example:
        assertNotNull(driverSpec);
        // Add more assertions based on what you expect the driverSpec to contain
    }

    @Test
    void testGetDriverSpecWithValidRequest() {
        // Test with a valid SubmitApplicationRequest containing driver with labels and volumes.
        request.setDriver(new DriverSpec());
        request.getDriver().setLabels(new HashMap<>() {

            {
                put(Constants.DAG_NAME_LABEL, "testDAG");
                put(Constants.TASK_NAME_LABEL, "testTask");
            }
        });
        request.getDriver().setVolumeMounts(Arrays.asList("mount1", "mount2"));
        request.setVolumes(Arrays.asList("volume1"));
        when(sparkCluster.getDriver()).thenReturn(new DriverSpec());
        when(sparkCluster.getSparkServiceAccount()).thenReturn("default");
        // When: Call the method under test
        DriverSpec driverSpec = ApplicationSubmissionHelper.getDriverSpec(request, appConfig, "testQueue", sparkCluster);
        // Then: Assert the expected outcomes
        assertNotNull(driverSpec);
        assertEquals("default", driverSpec.getServiceAccount());
        assertEquals("mount1", driverSpec.getVolumeMounts().get(0));
        assertEquals("testDAG", driverSpec.getLabels().get(Constants.DAG_NAME_LABEL));
        assertEquals("testTask", driverSpec.getLabels().get(Constants.TASK_NAME_LABEL));
    }

    @Test
    void testGetDriverSpecWhenDriverIsNullAndVolumesAreEmpty() {
        // Test when driver is null and volumes are empty, should use sparkCluster's driver volumeMounts.
        request.setDriver(null);
        request.setVolumes(new ArrayList<>());
        DriverSpec sparkClusterDriverSpec = new DriverSpec();
        sparkClusterDriverSpec.setVolumeMounts(Arrays.asList("mount3"));
        when(sparkCluster.getDriver()).thenReturn(sparkClusterDriverSpec);
        when(sparkCluster.getSparkServiceAccount()).thenReturn("default");
        // When: Call the method under test
        DriverSpec driverSpec = ApplicationSubmissionHelper.getDriverSpec(request, appConfig, "testQueue", sparkCluster);
        // Then: Assert the expected outcomes
        assertNotNull(driverSpec);
        assertEquals("mount3", driverSpec.getVolumeMounts().get(0));
        assertEquals("default", driverSpec.getServiceAccount());
    }

    @Test
    void testGetDriverSpecWithDriverHavingEmptyLabelsAndVolumeMounts() {
        // Test with driver having empty labels and volumeMounts, should default to sparkCluster's service account.
        request.setDriver(new DriverSpec());
        request.getDriver().setLabels(new HashMap<>());
        request.getDriver().setVolumeMounts(new ArrayList<>());
        request.setVolumes(new ArrayList<>());
        when(sparkCluster.getDriver()).thenReturn(new DriverSpec());
        when(sparkCluster.getSparkServiceAccount()).thenReturn("default");
        // When: Call the method under test
        DriverSpec driverSpec = ApplicationSubmissionHelper.getDriverSpec(request, appConfig, "testQueue", sparkCluster);
        // Then: Assert the expected outcomes
        assertNotNull(driverSpec);
        assertEquals("default", driverSpec.getServiceAccount());
        assertTrue(driverSpec.getVolumeMounts().isEmpty());
    }

    @Test
    void testGetDriverSpecWithDriverLabelsOnly() {
        // Test with driver having only DAG_NAME_LABEL set.
        request.setDriver(new DriverSpec());
        request.getDriver().setLabels(new HashMap<>() {

            {
                put(Constants.DAG_NAME_LABEL, "testDAG");
            }
        });
        request.setVolumes(new ArrayList<>());
        when(sparkCluster.getDriver()).thenReturn(new DriverSpec());
        when(sparkCluster.getSparkServiceAccount()).thenReturn("default");
        // When: Call the method under test
        DriverSpec driverSpec = ApplicationSubmissionHelper.getDriverSpec(request, appConfig, "testQueue", sparkCluster);
        // Then: Assert the expected outcomes
        assertNotNull(driverSpec);
        assertEquals("testDAG", driverSpec.getLabels().get(Constants.DAG_NAME_LABEL));
        assertEquals("default", driverSpec.getServiceAccount());
    }

    @Test
    void testGetDriverSpecWithDriverLabelsAndVolumeMounts() {
        // Test with driver having labels and volumeMounts specified, should prioritize driver's volumeMounts.
        request.setDriver(new DriverSpec());
        request.getDriver().setLabels(new HashMap<>() {

            {
                put(Constants.DAG_NAME_LABEL, "testDAG");
                put(Constants.TASK_NAME_LABEL, "testTask");
            }
        });
        request.getDriver().setVolumeMounts(Arrays.asList("mount1"));
        request.setVolumes(new ArrayList<>());
        when(sparkCluster.getDriver()).thenReturn(new DriverSpec());
        when(sparkCluster.getSparkServiceAccount()).thenReturn("default");
        // When: Call the method under test
        DriverSpec driverSpec = ApplicationSubmissionHelper.getDriverSpec(request, appConfig, "testQueue", sparkCluster);
        // Then: Assert the expected outcomes
        assertNotNull(driverSpec);
        assertEquals("mount1", driverSpec.getVolumeMounts().get(0));
        assertEquals("testDAG", driverSpec.getLabels().get(Constants.DAG_NAME_LABEL));
        assertEquals("testTask", driverSpec.getLabels().get(Constants.TASK_NAME_LABEL));
        assertEquals("default", driverSpec.getServiceAccount());
    }
}
