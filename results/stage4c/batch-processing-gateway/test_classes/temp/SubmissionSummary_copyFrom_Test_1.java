package com.apple.spark.api;

import com.apple.spark.operator.SparkApplication;
import com.apple.spark.AppConfig;
import com.apple.spark.core.Constants;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.HashMap;
import java.util.Map;
import com.apple.spark.operator.SparkApplicationSpec;

@ExtendWith(MockitoExtension.class)
class SubmissionSummary_copyFrom_Test {

    @Mock
    private SparkApplication mockSparkApplication;

    @Mock
    private AppConfig.SparkCluster mockSparkCluster;

    @Mock
    private AppConfig mockAppConfig;

    private SubmissionSummary submissionSummary;

    @BeforeEach
    void setupBeforeEach() {
        submissionSummary = new SubmissionSummary();
        // Setup mock behavior for SparkApplication
        when(mockSparkApplication.getMetadata()).thenReturn(mock(SparkApplication.Metadata.class));
        when(mockSparkApplication.getSpec()).thenReturn(mock(SparkApplicationSpec.class));
        // Setup mock behavior for Metadata
        Map<String, String> labels = new HashMap<>();
        labels.put(Constants.PROXY_USER_LABEL, "testUser");
        labels.put(Constants.QUEUE_LABEL, "testQueue");
        when(mockSparkApplication.getMetadata().getName()).thenReturn("testSubmissionId");
        when(mockSparkApplication.getMetadata().getLabels()).thenReturn(labels);
        // Setup mock behavior for SparkApplicationSpec
        when(mockSparkApplication.getSpec().getDriver()).thenReturn(mock(SparkApplicationSpec.DriverSpec.class));
        when(mockSparkApplication.getSpec().getExecutor()).thenReturn(mock(SparkApplicationSpec.ExecutorSpec.class));
    }

    @Test
    void testCopyFrom() {
        // Given
        // (Setup is done in setupBeforeEach)
        // When
        submissionSummary.copyFrom(mockSparkApplication);
        // Then
        assertEquals("testSubmissionId", submissionSummary.getSubmissionId());
        assertEquals("testUser", submissionSummary.getUser());
        assertEquals("testQueue", submissionSummary.getQueue());
        // Add more assertions as needed based on the expected behavior
    }

    @Test
    void testCopyFrom_withNonNullMetadataAndLabels() {
        // Given
        // (Setup is done in setupBeforeEach)
        // When
        submissionSummary.copyFrom(mockSparkApplication);
        // Then
        assertEquals("testSubmissionId", submissionSummary.getSubmissionId());
        assertEquals("testUser", submissionSummary.getUser());
        assertEquals("testQueue", submissionSummary.getQueue());
        // Add more assertions as needed based on the expected behavior
    }

    @Test
    void testCopyFrom_withNullMetadata() {
        // Given
        when(mockSparkApplication.getMetadata()).thenReturn(null);
        // When
        submissionSummary.copyFrom(mockSparkApplication);
        // Then
        assertNull(submissionSummary.getSubmissionId());
        assertNull(submissionSummary.getUser());
        assertNull(submissionSummary.getQueue());
        // Ensure no exceptions and default values are maintained
    }

    @Test
    void testCopyFrom_withMetadataButNullLabels() {
        // Given
        when(mockSparkApplication.getMetadata().getLabels()).thenReturn(null);
        // When
        submissionSummary.copyFrom(mockSparkApplication);
        // Then
        assertEquals("testSubmissionId", submissionSummary.getSubmissionId());
        assertNull(submissionSummary.getUser());
        assertNull(submissionSummary.getQueue());
        // Ensure only SubmissionId is set
    }

    @Test
    void testCopyFrom_withLabelsMissingSpecificKeys() {
        // Given
        Map<String, String> labels = new HashMap<>();
        labels.put(Constants.PROXY_USER_LABEL, "testUser");
        when(mockSparkApplication.getMetadata().getLabels()).thenReturn(labels);
        // When
        submissionSummary.copyFrom(mockSparkApplication);
        // Then
        assertEquals("testSubmissionId", submissionSummary.getSubmissionId());
        assertEquals("testUser", submissionSummary.getUser());
        assertNull(submissionSummary.getQueue());
        // Ensure missing label keys result in default values
    }

    @Test
    void testCopyFrom_withDriverAndExecutorMemoryNotConvertible() {
        // Given
        when(mockSparkApplication.getSpec().getDriver().getMemory()).thenReturn("invalidMemory");
        when(mockSparkApplication.getSpec().getExecutor().getMemory()).thenReturn("invalidMemory");
        // When
        submissionSummary.copyFrom(mockSparkApplication);
        // Then
        assertEquals(0L, submissionSummary.getDriverMemoryGB());
        assertEquals(0L, submissionSummary.getExecutorMemoryGB());
        // Ensure memory fields are set to 0 and check logs for conversion errors
    }

    @Test
    void testCopyFrom_withNullDriverAndExecutorSpecs() {
        // Given
        when(mockSparkApplication.getSpec().getDriver()).thenReturn(null);
        when(mockSparkApplication.getSpec().getExecutor()).thenReturn(null);
        // When
        submissionSummary.copyFrom(mockSparkApplication);
        // Then
        assertEquals(1, submissionSummary.getDriverCores());
        assertEquals(1, submissionSummary.getExecutorCores());
        // Ensure default values are used
    }

    @Test
    void testCopyFrom_withValidSpecButNullSparkConf() {
        // Given
        when(mockSparkApplication.getSpec().getSparkConf()).thenReturn(null);
        // When
        submissionSummary.copyFrom(mockSparkApplication);
        // Then
        assertEquals("testSubmissionId", submissionSummary.getSubmissionId());
        assertNull(submissionSummary.getApplicationName());
        // Ensure application name remains default
    }

    @Test
    void testCopyFrom_withValidSparkConfButMissingAppNameConfig() {
        // Given
        Map<String, String> sparkConf = new HashMap<>();
        when(mockSparkApplication.getSpec().getSparkConf()).thenReturn(sparkConf);
        // When
        submissionSummary.copyFrom(mockSparkApplication);
        // Then
        assertNull(submissionSummary.getApplicationName());
        // Ensure application name remains default
    }

    @Test
    void testCopyFrom_withEmptyLabelsAndDriverSpecs() {
        // Given
        when(mockSparkApplication.getMetadata().getLabels()).thenReturn(new HashMap<>());
        when(mockSparkApplication.getSpec().getDriver()).thenReturn(mock(SparkApplicationSpec.DriverSpec.class));
        // When
        submissionSummary.copyFrom(mockSparkApplication);
        // Then
        assertEquals("testSubmissionId", submissionSummary.getSubmissionId());
        assertNull(submissionSummary.getUser());
        // Ensure defaults are used for empty labels and driver specs
    }

    @Test
    void testCopyFrom_withEmptyExecutorSpecs() {
        // Given
        when(mockSparkApplication.getSpec().getExecutor()).thenReturn(mock(SparkApplicationSpec.ExecutorSpec.class));
        // When
        submissionSummary.copyFrom(mockSparkApplication);
        // Then
        assertEquals(1, submissionSummary.getExecutorCores());
        // Ensure defaults are used for empty executor specs
    }

    @Test
    void testCopyFrom_withValidMetadataAndLabelsButNullSpec() {
        // Given
        when(mockSparkApplication.getSpec()).thenReturn(null);
        // When
        submissionSummary.copyFrom(mockSparkApplication);
        // Then
        assertEquals("testSubmissionId", submissionSummary.getSubmissionId());
        assertEquals("testUser", submissionSummary.getUser());
        // Ensure fields related to spec remain default
    }

    @Test
    void testCopyFrom_withDriverAndExecutorMemorySetToZero() {
        // Given
        when(mockSparkApplication.getSpec().getDriver().getMemory()).thenReturn("0g");
        when(mockSparkApplication.getSpec().getExecutor().getMemory()).thenReturn("0g");
        // When
        submissionSummary.copyFrom(mockSparkApplication);
        // Then
        assertEquals(0L, submissionSummary.getDriverMemoryGB());
        assertEquals(0L, submissionSummary.getExecutorMemoryGB());
        // Ensure total memory is calculated correctly
    }

    @Test
    void testCopyFrom_withNullAndEmptyStringsInLabels() {
        // Given
        Map<String, String> labels = new HashMap<>();
        labels.put(Constants.PROXY_USER_LABEL, null);
        labels.put(Constants.QUEUE_LABEL, "");
        when(mockSparkApplication.getMetadata().getLabels()).thenReturn(labels);
        // When
        submissionSummary.copyFrom(mockSparkApplication);
        // Then
        assertNull(submissionSummary.getUser());
        assertEquals("", submissionSummary.getQueue());
        // Ensure method handles null and empty strings gracefully
    }

    @Test
    void testCopyFrom_withNullSpec() {
        // Given
        when(mockSparkApplication.getSpec()).thenReturn(null);
        // When
        submissionSummary.copyFrom(mockSparkApplication);
        // Then
        assertNull(submissionSummary.getSparkVersion());
        // Ensure fields related to spec remain default
    }

    @Test
    void testCopyFrom_withNullDriverAndExecutorMemory() {
        // Given
        when(mockSparkApplication.getSpec().getDriver().getMemory()).thenReturn(null);
        when(mockSparkApplication.getSpec().getExecutor().getMemory()).thenReturn(null);
        // When
        submissionSummary.copyFrom(mockSparkApplication);
        // Then
        assertEquals(0L, submissionSummary.getDriverMemoryGB());
        assertEquals(0L, submissionSummary.getExecutorMemoryGB());
        // Ensure method handles null memory gracefully
    }

    @Test
    void testCopyFrom_withNullDriverAndExecutorCores() {
        // Given
        when(mockSparkApplication.getSpec().getDriver().getCores()).thenReturn(null);
        when(mockSparkApplication.getSpec().getExecutor().getCores()).thenReturn(null);
        // When
        submissionSummary.copyFrom(mockSparkApplication);
        // Then
        assertEquals(1, submissionSummary.getDriverCores());
        assertEquals(1, submissionSummary.getExecutorCores());
        // Ensure method handles null cores gracefully
    }
}
