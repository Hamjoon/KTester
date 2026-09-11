Based on the provided information and the structure of the existing test class, here is the completed test class template for the `ApplicationSubmissionHelper_getSparkConf_Test`. This class will include necessary setup and teardown methods, as well as a test case for the focal method `getSparkConf`.

```java
package com.apple.spark.core;

import com.apple.spark.AppConfig;
import com.apple.spark.api.SubmitApplicationRequest;
import com.apple.spark.AppConfig.SparkCluster;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class ApplicationSubmissionHelper_getSparkConf_Test {
    
    private AppConfig appConfig;
    private SubmitApplicationRequest submitApplicationRequest;
    private SparkCluster sparkCluster;

    @BeforeAll
    static void setupBeforeAll() {
        // Code that runs once before all tests, if needed.
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize necessary fields and dependencies before each test
        appConfig = new AppConfig();
        submitApplicationRequest = mock(SubmitApplicationRequest.class);
        sparkCluster = mock(SparkCluster.class);
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup code after each test, if needed
    }

    @AfterAll
    static void teardownAfterAll() {
        // Code that runs once after all tests, if needed.
    }

    @Test
    void testGetSparkConf() {
        // Given
        String submissionId = "test-submission-id";
        Map<String, String> requestSparkConf = new HashMap<>();
        requestSparkConf.put("spark.executor.memory", "2g");
        requestSparkConf.put("spark.driver.memory", "1g");

        when(submitApplicationRequest.getSparkConf()).thenReturn(requestSparkConf);
        when(submitApplicationRequest.getApplicationName()).thenReturn("TestApp");

        Map<String, String> defaultSparkConf = new HashMap<>();
        defaultSparkConf.put("spark.default.parallelism", "4");
        appConfig.setDefaultSparkConf(defaultSparkConf);

        Map<String, String> sparkClusterConf = new HashMap<>();
        sparkClusterConf.put("spark.executor.cores", "2");
        when(sparkCluster.getSparkConf()).thenReturn(sparkClusterConf);

        // When
        Map<String, String> result = ApplicationSubmissionHelper.getSparkConf(submissionId, submitApplicationRequest, appConfig, sparkCluster);

        // Then
        assertNotNull(result);
        assertEquals("2g", result.get("spark.executor.memory"));
        assertEquals("1g", result.get("spark.driver.memory"));
        assertEquals("TestApp", result.get(SparkConstants.SPARK_APP_NAME_CONFIG));
        assertEquals("4", result.get("spark.default.parallelism"));
        assertEquals("2", result.get("spark.executor.cores"));
    }
}
```

### Explanation:
- **Setup and Teardown Methods**: The `setupBeforeEach` method initializes the `AppConfig`, `SubmitApplicationRequest`, and `SparkCluster` instances before each test. The `teardownAfterEach` method is available for any cleanup, though not used here.
- **Test Method**: The `testGetSparkConf` method tests the `getSparkConf` method. It sets up mock behavior for the `SubmitApplicationRequest` and `SparkCluster`, and asserts that the returned configuration map contains the expected values.
- **Assertions**: Various assertions are used to verify that the returned configuration is correct based on the inputs provided to the `getSparkConf` method.

This structure ensures that the test class adheres to best practices, including the setup and teardown lifecycle for JUnit tests.