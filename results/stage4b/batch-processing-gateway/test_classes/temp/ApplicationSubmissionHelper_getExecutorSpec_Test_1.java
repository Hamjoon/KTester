package com.apple.spark.core;

import com.apple.spark.AppConfig;
import com.apple.spark.api.SubmitApplicationRequest;
import com.apple.spark.operator.ExecutorSpec;
import com.apple.spark.operator.AppConfig.SparkCluster;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class ApplicationSubmissionHelper_getExecutorSpec_Test {

    private AppConfig appConfig;

    private SubmitApplicationRequest submitApplicationRequest;

    private SparkCluster sparkCluster;

    private String parentQueue;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize AppConfig
        appConfig = new AppConfig();
        // Initialize SubmitApplicationRequest
        submitApplicationRequest = new SubmitApplicationRequest();
        // Initialize SparkCluster
        sparkCluster = new SparkCluster();
        // Set a default queue name
        parentQueue = "defaultQueue";
    }

    @Test
    void testGetExecutorSpec() {
        // Given: Prepare the SubmitApplicationRequest and AppConfig
        // When: Call the method under test
        ExecutorSpec executorSpec = ApplicationSubmissionHelper.getExecutorSpec(submitApplicationRequest, appConfig, parentQueue, sparkCluster);
        // Then: Assert the expected results
        assertNotNull(executorSpec);
    }

    @Test
    void testGetExecutorSpec_WithValidExecutor() {
        // Test case where executor is specified in the request with valid labels.
        submitApplicationRequest.setExecutor(new ExecutorSpec());
        submitApplicationRequest.getExecutor().setCores(2);
        submitApplicationRequest.getExecutor().setMemory("4G");
        submitApplicationRequest.getExecutor().setLabels(Map.of(Constants.DAG_NAME_LABEL, "testDAG", Constants.TASK_NAME_LABEL, "testTask"));
        // When: Call the method under test
        ExecutorSpec executorSpec = ApplicationSubmissionHelper.getExecutorSpec(submitApplicationRequest, appConfig, parentQueue, sparkCluster);
        // Then: Assert the expected results
        assertNotNull(executorSpec);
        assertEquals(2, executorSpec.getCores());
        assertEquals("4G", executorSpec.getMemory());
        assertEquals(Map.of(Constants.DAG_NAME_LABEL, "testDAG", Constants.TASK_NAME_LABEL, "testTask"), executorSpec.getLabels());
    }

    @Test
    void testGetExecutorSpec_ExecutorNotSpecified_FallsBackToSparkCluster() {
        // Test case where executor is not specified in the request but is provided in the sparkCluster.
        sparkCluster.setExecutor(new ExecutorSpec());
        sparkCluster.getExecutor().setCores(4);
        sparkCluster.getExecutor().setMemory("8G");
        // When: Call the method under test
        ExecutorSpec executorSpec = ApplicationSubmissionHelper.getExecutorSpec(submitApplicationRequest, appConfig, parentQueue, sparkCluster);
        // Then: Assert the expected results
        assertNotNull(executorSpec);
        assertEquals(4, executorSpec.getCores());
        assertEquals("8G", executorSpec.getMemory());
    }

    @Test
    void testGetExecutorSpec_ExecutorWithoutLabels() {
        // Test case where executor is specified without labels.
        submitApplicationRequest.setExecutor(new ExecutorSpec());
        submitApplicationRequest.getExecutor().setCores(2);
        submitApplicationRequest.getExecutor().setMemory("4G");
        submitApplicationRequest.getExecutor().setLabels(new HashMap<>());
        // When: Call the method under test
        ExecutorSpec executorSpec = ApplicationSubmissionHelper.getExecutorSpec(submitApplicationRequest, appConfig, parentQueue, sparkCluster);
        // Then: Assert the expected results
        assertNotNull(executorSpec);
        assertEquals(2, executorSpec.getCores());
        assertEquals("4G", executorSpec.getMemory());
        assertTrue(executorSpec.getLabels().isEmpty());
    }

    @Test
    void testGetExecutorSpec_ExecutorWithOnlyDAGNameLabel() {
        // Test case where executor has only DAG_NAME_LABEL specified.
        submitApplicationRequest.setExecutor(new ExecutorSpec());
        submitApplicationRequest.getExecutor().setCores(2);
        submitApplicationRequest.getExecutor().setMemory("4G");
        submitApplicationRequest.getExecutor().setLabels(Map.of(Constants.DAG_NAME_LABEL, "testDAG"));
        // When: Call the method under test
        ExecutorSpec executorSpec = ApplicationSubmissionHelper.getExecutorSpec(submitApplicationRequest, appConfig, parentQueue, sparkCluster);
        // Then: Assert the expected results
        assertNotNull(executorSpec);
        assertEquals(2, executorSpec.getCores());
        assertEquals("4G", executorSpec.getMemory());
        assertEquals(Map.of(Constants.DAG_NAME_LABEL, "testDAG"), executorSpec.getLabels());
    }

    @Test
    void testGetExecutorSpec_ExecutorWithVolumesSpecified() {
        // Test case where executor has volumes specified in request but no volume mounts.
        submitApplicationRequest.setExecutor(new ExecutorSpec());
        submitApplicationRequest.getExecutor().setCores(2);
        submitApplicationRequest.getExecutor().setMemory("4G");
        submitApplicationRequest.getExecutor().setLabels(Map.of(Constants.DAG_NAME_LABEL, "testDAG", Constants.TASK_NAME_LABEL, "testTask"));
        submitApplicationRequest.setVolumes(List.of(new Volume("volume1", "/mnt/volume1")));
        // When: Call the method under test
        ExecutorSpec executorSpec = ApplicationSubmissionHelper.getExecutorSpec(submitApplicationRequest, appConfig, parentQueue, sparkCluster);
        // Then: Assert the expected results
        assertNotNull(executorSpec);
        assertEquals(2, executorSpec.getCores());
        assertEquals("4G", executorSpec.getMemory());
        assertEquals(Map.of(Constants.DAG_NAME_LABEL, "testDAG", Constants.TASK_NAME_LABEL, "testTask"), executorSpec.getLabels());
        assertTrue(executorSpec.getVolumeMounts().isEmpty());
    }

    @Test
    void testGetExecutorSpec_ExecutorWithVolumeMounts() {
        // Test case where executor has volume mounts specified in the request.
        submitApplicationRequest.setExecutor(new ExecutorSpec());
        submitApplicationRequest.getExecutor().setCores(2);
        submitApplicationRequest.getExecutor().setMemory("4G");
        submitApplicationRequest.getExecutor().setLabels(Map.of(Constants.DAG_NAME_LABEL, "testDAG", Constants.TASK_NAME_LABEL, "testTask"));
        submitApplicationRequest.getExecutor().setVolumeMounts(List.of(new Volume("volume1", "/mnt/volume1")));
        // When: Call the method under test
        ExecutorSpec executorSpec = ApplicationSubmissionHelper.getExecutorSpec(submitApplicationRequest, appConfig, parentQueue, sparkCluster);
        // Then: Assert the expected results
        assertNotNull(executorSpec);
        assertEquals(2, executorSpec.getCores());
        assertEquals("4G", executorSpec.getMemory());
        assertEquals(Map.of(Constants.DAG_NAME_LABEL, "testDAG", Constants.TASK_NAME_LABEL, "testTask"), executorSpec.getLabels());
        assertFalse(executorSpec.getVolumeMounts().isEmpty());
    }

    @Test
    void testGetExecutorSpec_ExecutorWithZeroCoresAndMemory() {
        // Test case where executor has zero cores and memory specified in the request but valid executor configuration is provided in sparkCluster.
        submitApplicationRequest.setExecutor(new ExecutorSpec());
        submitApplicationRequest.getExecutor().setCores(0);
        submitApplicationRequest.getExecutor().setMemory("0G");
        sparkCluster.setExecutor(new ExecutorSpec());
        sparkCluster.getExecutor().setCores(4);
        sparkCluster.getExecutor().setMemory("8G");
        // When: Call the method under test
        ExecutorSpec executorSpec = ApplicationSubmissionHelper.getExecutorSpec(submitApplicationRequest, appConfig, parentQueue, sparkCluster);
        // Then: Assert the expected results
        assertNotNull(executorSpec);
        assertEquals(4, executorSpec.getCores());
        assertEquals("8G", executorSpec.getMemory());
    }

    @Test
    void testGetExecutorSpec_ExecutorWithNegativeCores() {
        // Test case where executor has negative cores specified in the request, should fallback to sparkCluster.
        submitApplicationRequest.setExecutor(new ExecutorSpec());
        submitApplicationRequest.getExecutor().setCores(-1);
        submitApplicationRequest.getExecutor().setMemory("4G");
        sparkCluster.setExecutor(new ExecutorSpec());
        sparkCluster.getExecutor().setCores(4);
        sparkCluster.getExecutor().setMemory("8G");
        // When: Call the method under test
        ExecutorSpec executorSpec = ApplicationSubmissionHelper.getExecutorSpec(submitApplicationRequest, appConfig, parentQueue, sparkCluster);
        // Then: Assert the expected results
        assertNotNull(executorSpec);
        assertEquals(4, executorSpec.getCores());
        assertEquals("8G", executorSpec.getMemory());
    }

    @Test
    void testGetExecutorSpec_InvalidQueueFallback() {
        // Test case where an invalid queue is specified in parentQueue, should fallback to sparkCluster.
        submitApplicationRequest.setExecutor(new ExecutorSpec());
        submitApplicationRequest.getExecutor().setCores(2);
        submitApplicationRequest.getExecutor().setMemory("4G");
        sparkCluster.setExecutor(new ExecutorSpec());
        sparkCluster.getExecutor().setCores(4);
        sparkCluster.getExecutor().setMemory("8G");
        // Set an invalid queue
        parentQueue = "invalidQueue";
        // When: Call the method under test
        ExecutorSpec executorSpec = ApplicationSubmissionHelper.getExecutorSpec(submitApplicationRequest, appConfig, parentQueue, sparkCluster);
        // Then: Assert the expected results
        assertNotNull(executorSpec);
        assertEquals(4, executorSpec.getCores());
        assertEquals("8G", executorSpec.getMemory());
    }
}
