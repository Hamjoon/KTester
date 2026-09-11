package com.apple.spark.api;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import com.apple.spark.operator.SparkApplication;
import com.apple.spark.operator.SparkApplicationSpec;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import com.apple.spark.AppConfig;
// Importing Constants class
import com.apple.spark.core.Constants;
// Importing SparkConstants class
import com.apple.spark.core.SparkConstants;

@ExtendWith(MockitoExtension.class)
class SubmissionSummary_copyFrom_Test {

    private SubmissionSummary submissionSummary;

    private SparkApplication sparkApplicationMock;

    private AppConfig.SparkCluster sparkClusterMock;

    private AppConfig appConfigMock;

    @BeforeEach
    void setupBeforeEach() {
        submissionSummary = new SubmissionSummary();
        sparkApplicationMock = mock(SparkApplication.class);
        sparkClusterMock = mock(AppConfig.SparkCluster.class);
        appConfigMock = mock(AppConfig.class);
        // Mocking SparkApplication's metadata and spec
        SparkApplicationSpec specMock = mock(SparkApplicationSpec.class);
        when(sparkApplicationMock.getSpec()).thenReturn(specMock);
        // Mocking metadata and ensuring it is not null
        ObjectMeta metadataMock = mock(ObjectMeta.class);
        when(sparkApplicationMock.getMetadata()).thenReturn(metadataMock);
        // Create a mock metadata
        Map<String, String> labels = new HashMap<>();
        labels.put(Constants.PROXY_USER_LABEL, "testUser");
        labels.put(Constants.QUEUE_LABEL, "testQueue");
        when(metadataMock.getLabels()).thenReturn(labels);
        // Mocking other necessary behaviors
        // Set up any other necessary mocks for the spec, driver, etc.
    }

    @Test
    void testCopyFrom() {
        // Given
        // Setup any specific behavior for the test case here
        // When
        submissionSummary.copyFrom(sparkApplicationMock);
        // Then
        // Verify the expected outcomes
        assertEquals("testUser", submissionSummary.getUser());
        assertEquals("testQueue", submissionSummary.getQueue());
        // Add more assertions as necessary to validate the state of submissionSummary
    }

    @Test
    void testCopyFrom_BasicFunctionality() {
        // Given
        when(sparkApplicationMock.getMetadata().getName()).thenReturn("app1");
        when(sparkApplicationMock.getSpec().getDriver().getLabels()).thenReturn(Map.of(Constants.DAG_NAME_LABEL, "DAG1"));
        when(sparkApplicationMock.getSpec().getDriver().getMemory()).thenReturn("2g");
        when(sparkApplicationMock.getSpec().getDriver().getCores()).thenReturn(2);
        when(sparkApplicationMock.getSpec().getExecutor().getCores()).thenReturn(2);
        when(sparkApplicationMock.getSpec().getExecutor().getInstances()).thenReturn(3);
        when(sparkApplicationMock.getSpec().getExecutor().getMemory()).thenReturn("4g");
        when(sparkApplicationMock.getSpec().getSparkVersion()).thenReturn("3.0.0");
        when(sparkApplicationMock.getSpec().getArguments()).thenReturn(Arrays.asList("arg1", "arg2"));
        when(sparkApplicationMock.getSpec().getSparkConf()).thenReturn(Map.of(SparkConstants.SPARK_APP_NAME_CONFIG, "MyApp"));
        // When
        submissionSummary.copyFrom(sparkApplicationMock);
        // Then
        assertEquals("app1", submissionSummary.getSubmissionId());
        assertEquals("testUser", submissionSummary.getUser());
        assertEquals("testQueue", submissionSummary.getQueue());
        assertEquals("DAG1", submissionSummary.getDagName());
        assertEquals(2, submissionSummary.getDriverCores());
        assertEquals(Long.valueOf(2), submissionSummary.getDriverMemoryGB());
        assertEquals(2, submissionSummary.getExecutorCores());
        assertEquals(3, submissionSummary.getExecutorInstances());
        assertEquals(Long.valueOf(4), submissionSummary.getExecutorMemoryGB());
        // 2 + (3 * 4)
        assertEquals(Long.valueOf(14), submissionSummary.getTotalMemoryGB());
        // 2 + (3 * 2)
        assertEquals(8, submissionSummary.getTotalCores());
        assertEquals("MyApp", submissionSummary.getApplicationName());
        assertEquals(Arrays.asList("arg1", "arg2"), submissionSummary.getApplicationArguments());
        assertEquals("3.0.0", submissionSummary.getSparkVersion());
    }

    @Test
    void testCopyFrom_NullHandling() {
        // Given
        when(sparkApplicationMock.getMetadata().getName()).thenReturn("app2");
        when(sparkApplicationMock.getMetadata().getLabels()).thenReturn(null);
        when(sparkApplicationMock.getSpec()).thenReturn(null);
        // When
        submissionSummary.copyFrom(sparkApplicationMock);
        // Then
        assertEquals("app2", submissionSummary.getSubmissionId());
        // All other fields should remain default or null
        assertNull(submissionSummary.getUser());
        assertNull(submissionSummary.getQueue());
        assertNull(submissionSummary.getDagName());
        // Default value
        assertEquals(1, submissionSummary.getDriverCores());
        // Default value
        assertEquals(Long.valueOf(0), submissionSummary.getDriverMemoryGB());
        // Default value
        assertEquals(1, submissionSummary.getExecutorCores());
        // Default value
        assertEquals(1, submissionSummary.getExecutorInstances());
        // Default value
        assertEquals(Long.valueOf(0), submissionSummary.getExecutorMemoryGB());
        // Default value
        assertEquals(Long.valueOf(0), submissionSummary.getTotalMemoryGB());
        // Default value
        assertEquals(2, submissionSummary.getTotalCores());
    }

    @Test
    void testCopyFrom_ExceptionHandling() {
        // Given
        when(sparkApplicationMock.getMetadata().getName()).thenReturn("app3");
        when(sparkApplicationMock.getMetadata().getLabels()).thenReturn(Map.of(Constants.PROXY_USER_LABEL, "user2", Constants.QUEUE_LABEL, "default"));
        when(sparkApplicationMock.getSpec().getDriver().getLabels()).thenReturn(Map.of(Constants.DAG_NAME_LABEL, "DAG2"));
        when(sparkApplicationMock.getSpec().getDriver().getMemory()).thenReturn("invalid_memory");
        when(sparkApplicationMock.getSpec().getDriver().getCores()).thenReturn(2);
        when(sparkApplicationMock.getSpec().getExecutor().getCores()).thenReturn(2);
        when(sparkApplicationMock.getSpec().getExecutor().getInstances()).thenReturn(3);
        when(sparkApplicationMock.getSpec().getExecutor().getMemory()).thenReturn("4g");
        when(sparkApplicationMock.getSpec().getSparkVersion()).thenReturn("3.0.0");
        when(sparkApplicationMock.getSpec().getArguments()).thenReturn(Arrays.asList("arg1", "arg2"));
        when(sparkApplicationMock.getSpec().getSparkConf()).thenReturn(Map.of(SparkConstants.SPARK_APP_NAME_CONFIG, "MyApp"));
        // When
        submissionSummary.copyFrom(sparkApplicationMock);
        // Then
        // Verify that the exception was handled and default values are set
        assertEquals(Long.valueOf(0), submissionSummary.getDriverMemoryGB());
    }
}
