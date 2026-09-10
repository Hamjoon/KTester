Based on the provided information and the requirements for the test class, here's the completed test class template for testing the `populateEnv` method of the `ApplicationSubmissionHelper` class. This test class will initialize the necessary fields and dependencies, handle cleanup, and ensure that the focal method is tested correctly without invoking any private methods or fields.

```java
package com.apple.spark.core;

import com.apple.spark.AppConfig;
import com.apple.spark.api.SubmitApplicationRequest;
import com.apple.spark.operator.DriverSpec;
import com.apple.spark.operator.ExecutorSpec;
import com.apple.spark.operator.SparkApplicationSpec;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplicationSubmissionHelper_populateEnv_Test {

    @Mock
    private SparkApplicationSpec sparkApplicationSpec;

    @Mock
    private SubmitApplicationRequest submitApplicationRequest;

    @Mock
    private AppConfig.SparkCluster sparkCluster;

    @BeforeAll
    static void setupBeforeAll() {
        // This method can be used to set up any global resources needed for the tests.
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize mocks and set up common test data
        when(sparkApplicationSpec.getDriver()).thenReturn(new DriverSpec());
        when(sparkApplicationSpec.getExecutor()).thenReturn(new ExecutorSpec());
    }

    @AfterEach
    void teardownAfterEach() {
        // Clean up after each test if necessary
    }

    @AfterAll
    static void teardownAfterAll() {
        // Clean up any global resources after all tests have run
    }

    @Test
    void testPopulateEnv_withDriverEnvFromCluster() {
        // Given
        List<String> driverEnv = new ArrayList<>();
        driverEnv.add("ENV_VAR1=value1");
        when(sparkCluster.getDriver().getEnv()).thenReturn(driverEnv);

        // When
        ApplicationSubmissionHelper.populateEnv(sparkApplicationSpec, submitApplicationRequest, sparkCluster);

        // Then
        assertEquals(driverEnv, sparkApplicationSpec.getDriver().getEnv());
    }

    @Test
    void testPopulateEnv_withDriverEnvFromRequest() {
        // Given
        List<String> requestDriverEnv = new ArrayList<>();
        requestDriverEnv.add("ENV_VAR2=value2");
        when(submitApplicationRequest.getDriver().getEnv()).thenReturn(requestDriverEnv);

        // When
        ApplicationSubmissionHelper.populateEnv(sparkApplicationSpec, submitApplicationRequest, sparkCluster);

        // Then
        assertEquals(requestDriverEnv, sparkApplicationSpec.getDriver().getEnv());
    }

    @Test
    void testPopulateEnv_withExecutorEnvFromCluster() {
        // Given
        List<String> executorEnv = new ArrayList<>();
        executorEnv.add("ENV_VAR3=value3");
        when(sparkCluster.getExecutor().getEnv()).thenReturn(executorEnv);

        // When
        ApplicationSubmissionHelper.populateEnv(sparkApplicationSpec, submitApplicationRequest, sparkCluster);

        // Then
        assertEquals(executorEnv, sparkApplicationSpec.getExecutor().getEnv());
    }

    @Test
    void testPopulateEnv_withExecutorEnvFromRequest() {
        // Given
        List<String> requestExecutorEnv = new ArrayList<>();
        requestExecutorEnv.add("ENV_VAR4=value4");
        when(submitApplicationRequest.getExecutor().getEnv()).thenReturn(requestExecutorEnv);

        // When
        ApplicationSubmissionHelper.populateEnv(sparkApplicationSpec, submitApplicationRequest, sparkCluster);

        // Then
        assertEquals(requestExecutorEnv, sparkApplicationSpec.getExecutor().getEnv());
    }
}
```

### Explanation:
1. **Setup and Teardown Methods**: The `setupBeforeAll`, `setupBeforeEach`, `teardownAfterEach`, and `teardownAfterAll` methods are defined to manage the lifecycle of the tests.
2. **Mocking Dependencies**: The `SparkApplicationSpec`, `SubmitApplicationRequest`, and `AppConfig.SparkCluster` classes are mocked to isolate the tests from their actual implementations.
3. **Test Cases**: Four test cases are provided to validate the behavior of the `populateEnv` method:
   - Testing environment variables from the `SparkCluster` driver.
   - Testing environment variables from the `SubmitApplicationRequest` driver.
   - Testing environment variables from the `SparkCluster` executor.
   - Testing environment variables from the `SubmitApplicationRequest` executor.
4. **Assertions**: Each test case asserts that the environment variables are set correctly in the `SparkApplicationSpec`.

This structure ensures that the tests are clear, maintainable, and effectively validate the functionality of the `populateEnv` method.