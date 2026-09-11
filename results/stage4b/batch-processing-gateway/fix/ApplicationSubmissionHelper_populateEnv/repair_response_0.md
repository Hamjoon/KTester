Here is the corrected Java code with the necessary fixes applied:

```java
package com.apple.spark.core;

import com.apple.spark.AppConfig;
import com.apple.spark.api.SubmitApplicationRequest;
import com.apple.spark.operator.DriverSpec;
import com.apple.spark.operator.ExecutorSpec;
import com.apple.spark.operator.SparkApplicationSpec;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import com.apple.spark.operator.EnvVar;

@ExtendWith(MockitoExtension.class)
class ApplicationSubmissionHelper_populateEnv_Test {

    private SparkApplicationSpec sparkSpec;
    private SubmitApplicationRequest request;
    private AppConfig.SparkCluster sparkCluster;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the necessary objects before each test
        sparkSpec = new SparkApplicationSpec();
        request = new SubmitApplicationRequest();
        sparkCluster = new AppConfig.SparkCluster();
    }

    @Test
    void testPopulateEnv_withDriverEnvInCluster() {
        // Given: Set up the environment variables in the SparkCluster
        List<EnvVar> clusterDriverEnv = new ArrayList<>();
        clusterDriverEnv.add(new EnvVar("CLUSTER_VAR", "cluster_value"));
        sparkCluster.setDriver(new DriverSpec());
        sparkCluster.getDriver().setEnv(clusterDriverEnv);
        
        // Given: Set up the environment variables in the SubmitApplicationRequest
        List<EnvVar> requestDriverEnv = new ArrayList<>();
        requestDriverEnv.add(new EnvVar("REQUEST_VAR", "request_value"));
        request.setDriver(new DriverSpec());
        request.getDriver().setEnv(requestDriverEnv);
        
        // When: Call the method under test
        ApplicationSubmissionHelper.populateEnv(sparkSpec, request, sparkCluster);
        
        // Then: Assert that the environment variables are populated correctly
        assertNotNull(sparkSpec.getDriver());
        assertEquals(2, sparkSpec.getDriver().getEnv().size());
        assertTrue(sparkSpec.getDriver().getEnv().stream().anyMatch(env -> env.getName().equals("CLUSTER_VAR") && env.getValue().equals("cluster_value")));
        assertTrue(sparkSpec.getDriver().getEnv().stream().anyMatch(env -> env.getName().equals("REQUEST_VAR") && env.getValue().equals("request_value")));
    }

    @Test
    void testPopulateEnv_withNoEnvVariables() {
        // When: Call the method under test with empty envs
        ApplicationSubmissionHelper.populateEnv(sparkSpec, request, sparkCluster);
        
        // Then: Assert that the driver and executor are not initialized
        assertNull(sparkSpec.getDriver());
        assertNull(sparkSpec.getExecutor());
    }

    @Test
    void testPopulateEnv_withDriverEnvInClusterNull() {
        // Given: SparkCluster driver is null
        sparkCluster.setDriver(null);
        
        // When: Call the method under test
        ApplicationSubmissionHelper.populateEnv(sparkSpec, request, sparkCluster);
        
        // Then: Assert that the driver remains null
        assertNull(sparkSpec.getDriver());
    }

    @Test
    void testPopulateEnv_withRequestDriverEnvNull() {
        // Given: Request driver is null
        request.setDriver(null);
        
        // When: Call the method under test
        ApplicationSubmissionHelper.populateEnv(sparkSpec, request, sparkCluster);
        
        // Then: Assert that the driver remains null
        assertNull(sparkSpec.getDriver());
    }

    @Test
    void testPopulateEnv_withExecutorEnvInCluster() {
        // Given: Set up the environment variables in the SparkCluster executor
        List<EnvVar> clusterExecutorEnv = new ArrayList<>();
        clusterExecutorEnv.add(new EnvVar("EXECUTOR_VAR", "executor_value"));
        sparkCluster.setExecutor(new ExecutorSpec());
        sparkCluster.getExecutor().setEnv(clusterExecutorEnv);
        
        // When: Call the method under test
        ApplicationSubmissionHelper.populateEnv(sparkSpec, request, sparkCluster);
        
        // Then: Assert that the executor environment variables are populated correctly
        assertNotNull(sparkSpec.getExecutor());
        assertEquals(1, sparkSpec.getExecutor().getEnv().size());
        assertTrue(sparkSpec.getExecutor().getEnv().stream().anyMatch(env -> env.getName().equals("EXECUTOR_VAR") && env.getValue().equals("executor_value")));
    }

    @Test
    void testPopulateEnv_withExecutorEnvInRequest() {
        // Given: Set up the environment variables in the SubmitApplicationRequest executor
        List<EnvVar> requestExecutorEnv = new ArrayList<>();
        requestExecutorEnv.add(new EnvVar("REQUEST_EXECUTOR_VAR", "request_executor_value"));
        request.setExecutor(new ExecutorSpec());
        request.getExecutor().setEnv(requestExecutorEnv);
        
        // When: Call the method under test
        ApplicationSubmissionHelper.populateEnv(sparkSpec, request, sparkCluster);
        
        // Then: Assert that the executor environment variables are populated correctly
        assertNotNull(sparkSpec.getExecutor());
        assertEquals(1, sparkSpec.getExecutor().getEnv().size());
        assertTrue(sparkSpec.getExecutor().getEnv().stream().anyMatch(env -> env.getName().equals("REQUEST_EXECUTOR_VAR") && env.getValue().equals("request_executor_value")));
    }

    @Test
    void testPopulateEnv_withBothDriverAndExecutorEnvironments() {
        // Given: Set up driver and executor environments in both SparkCluster and SubmitApplicationRequest
        List<EnvVar> clusterDriverEnv = new ArrayList<>();
        clusterDriverEnv.add(new EnvVar("CLUSTER_VAR", "cluster_value"));
        sparkCluster.setDriver(new DriverSpec());
        sparkCluster.getDriver().setEnv(clusterDriverEnv);
        
        List<EnvVar> requestDriverEnv = new ArrayList<>();
        requestDriverEnv.add(new EnvVar("REQUEST_VAR", "request_value"));
        request.setDriver(new DriverSpec());
        request.getDriver().setEnv(requestDriverEnv);
        
        List<EnvVar> clusterExecutorEnv = new ArrayList<>();
        clusterExecutorEnv.add(new EnvVar("EXECUTOR_VAR", "executor_value"));
        sparkCluster.setExecutor(new ExecutorSpec());
        sparkCluster.getExecutor().setEnv(clusterExecutorEnv);
        
        List<EnvVar> requestExecutorEnv = new ArrayList<>();
        requestExecutorEnv.add(new EnvVar("REQUEST_EXECUTOR_VAR", "request_executor_value"));
        request.setExecutor(new ExecutorSpec());
        request.getExecutor().setEnv(requestExecutorEnv);
        
        // When: Call the method under test
        ApplicationSubmissionHelper.populateEnv(sparkSpec, request, sparkCluster);
        
        // Then: Assert that both driver and executor environments are populated correctly
        assertNotNull(sparkSpec.getDriver());
        assertEquals(2, sparkSpec.getDriver().getEnv().size());
        assertNotNull(sparkSpec.getExecutor());
        assertEquals(2, sparkSpec.getExecutor().getEnv().size());
    }

    @Test
    void testPopulateEnv_withNullSparkSpec() {
        // Given: sparkSpec is null
        sparkSpec = null;
        
        // When: Call the method under test
        Exception exception = assertThrows(NullPointerException.class, () -> {
            ApplicationSubmissionHelper.populateEnv(sparkSpec, request, sparkCluster);
        });
        
        // Then: Assert that a NullPointerException is thrown
        assertEquals("sparkSpec must not be null", exception.getMessage());
    }

    @Test
    void testPopulateEnv_withNullRequest() {
        // Given: request is null
        request = null;
        
        // When: Call the method under test
        Exception exception = assertThrows(NullPointerException.class, () -> {
            ApplicationSubmissionHelper.populateEnv(sparkSpec, request, sparkCluster);
        });
        
        // Then: Assert that a NullPointerException is thrown
        assertEquals("request must not be null", exception.getMessage());
    }

    @Test
    void testPopulateEnv_withNullSparkCluster() {
        // Given: sparkCluster is null
        sparkCluster = null;
        
        // When: Call the method under test
        Exception exception = assertThrows(NullPointerException.class, () -> {
            ApplicationSubmissionHelper.populateEnv(sparkSpec, request, sparkCluster);
        });
        
        // Then: Assert that a NullPointerException is thrown
        assertEquals("sparkCluster must not be null", exception.getMessage());
    }
}
```

### Key Changes Made:
1. **Exception Messages**: The tests for null checks were updated to ensure that the expected exception messages are correctly asserted.
2. **Null Handling**: The logic for handling null values in the `populateEnv` method needs to ensure that it throws `NullPointerException` with the correct messages. This may require changes in the `ApplicationSubmissionHelper.populateEnv` method if it doesn't already handle null cases properly.

Make sure to check the implementation of the `populateEnv` method in the `ApplicationSubmissionHelper` class to ensure it throws the expected exceptions when any of the parameters are null.