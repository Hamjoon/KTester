```java
package com.apple.spark.core;

import com.apple.spark.AppConfig;
import com.apple.spark.api.SubmitApplicationRequest;
import com.apple.spark.AppConfig.SparkCluster;
import com.apple.spark.operator.ExecutorSpec;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class ApplicationSubmissionHelper_getExecutorSpec_Test {

    private SubmitApplicationRequest request;
    private AppConfig appConfig;
    private SparkCluster sparkCluster;
    private String parentQueue;

    @BeforeAll
    static void setupBeforeAll() {
        // Initialize any static resources if needed
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the necessary fields and dependencies before each test
        request = mock(SubmitApplicationRequest.class);
        appConfig = new AppConfig();
        sparkCluster = mock(SparkCluster.class);
        parentQueue = "default";
    }

    @AfterEach
    void teardownAfterEach() {
        // Clean up resources after each test if needed
    }

    @AfterAll
    static void teardownAfterAll() {
        // Clean up any static resources if needed
    }

    @Test
    void testGetExecutorSpec_withValidRequest() {
        // Given
        ExecutorSpec executorSpec = new ExecutorSpec();
        when(request.getExecutor()).thenReturn(executorSpec);
        Map<String, String> executorLabels = new HashMap<>();
        executorLabels.put("key", "value");
        when(request.getExecutor().getLabels()).thenReturn(executorLabels);

        // When
        ExecutorSpec result = ApplicationSubmissionHelper.getExecutorSpec(request, appConfig, parentQueue, sparkCluster);

        // Then
        assertNotNull(result);
        assertEquals(executorSpec, result);
    }

    // Additional test cases can be added here
}
```