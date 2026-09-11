Based on the provided information, here’s the completed test class template for the `populateEnv` method in the `ApplicationSubmissionHelper` class. This implementation includes the setup and teardown methods, necessary fields, and a test method to validate the behavior of the `populateEnv` method.

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

@ExtendWith(MockitoExtension.class)
class ApplicationSubmissionHelper_populateEnv_Test {
    
    private SparkApplicationSpec sparkSpec;
    private SubmitApplicationRequest request;
    private AppConfig.SparkCluster sparkCluster;

    @BeforeAll
    static void setupBeforeAll() {
        // Any global setup can be done here if necessary
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the necessary objects before each test
        sparkSpec = new SparkApplicationSpec();
        request = new SubmitApplicationRequest();
        sparkCluster = new AppConfig.SparkCluster();
    }

    @AfterEach
    void teardownAfterEach() {
        // Clean up resources after each test if necessary
    }

    @AfterAll
    static void teardownAfterAll() {
        // Any global cleanup can be done here if necessary
    }

    @Test
    void testPopulateEnv_withDriverEnvInCluster() {
        // Given: Set up the environment variables in the SparkCluster
        List<EnvVar> clusterDriverEnv = new ArrayList<>();
        clusterDriverEnv.add(new EnvVar("CLUSTER_VAR", "cluster_value"));
        sparkCluster.getDriver().setEnv(clusterDriverEnv);
        
        // Given: Set up the environment variables in the SubmitApplicationRequest
        List<EnvVar> requestDriverEnv = new ArrayList<>();
        requestDriverEnv.add(new EnvVar("REQUEST_VAR", "request_value"));
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

    // Additional test cases can be added to cover more scenarios
}
```

### Explanation:
1. **Setup and Teardown Methods**: 
   - `setupBeforeAll()` and `teardownAfterAll()` are placeholders for any global setup and cleanup, respectively.
   - `setupBeforeEach()` initializes the `SparkApplicationSpec`, `SubmitApplicationRequest`, and `AppConfig.SparkCluster` before each test.
   - `teardownAfterEach()` is a placeholder for any cleanup needed after each test.

2. **Test Methods**: 
   - `testPopulateEnv_withDriverEnvInCluster()` verifies that the environment variables from both the `SubmitApplicationRequest` and `AppConfig.SparkCluster` are copied to the `SparkApplicationSpec`.
   - `testPopulateEnv_withNoEnvVariables()` checks that if no environment variables are provided, the driver and executor remain uninitialized.

3. **Assertions**: 
   - The assertions confirm the expected behavior of the `populateEnv` method, ensuring that environment variables are correctly set.

This structure provides a solid foundation for testing the `populateEnv` method, allowing for easy expansion with additional test cases as needed.