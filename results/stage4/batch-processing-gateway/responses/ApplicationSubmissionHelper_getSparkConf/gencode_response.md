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
    private SubmitApplicationRequest request;
    private SparkCluster sparkCluster;

    @BeforeAll
    static void setupBeforeAll() {
        // Code to run before all tests, if needed
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize necessary fields and dependencies
        appConfig = new AppConfig();
        request = mock(SubmitApplicationRequest.class);
        sparkCluster = mock(SparkCluster.class);
        
        // Setting up default configurations for the test
        Map<String, String> defaultSparkConf = new HashMap<>();
        defaultSparkConf.put("spark.executor.memory", "2g");
        appConfig.setDefaultSparkConf(defaultSparkConf);
        
        Map<String, String> fixedSparkConf = new HashMap<>();
        fixedSparkConf.put("spark.executor.cores", "4");
        appConfig.setFixedSparkConf(fixedSparkConf);
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup code after each test
    }

    @AfterAll
    static void teardownAfterAll() {
        // Code to run after all tests, if needed
    }

    @Test
    void testGetSparkConf_DefaultSparkConfiguration() {
        // Given
        String submissionId = "test-submission-id";
        when(request.getSparkConf()).thenReturn(null);
        when(request.getApplicationName()).thenReturn("TestApp");

        appConfig.setDefaultSparkConf(Map.of("key1", "value1", "key2", "value2"));
        sparkCluster = mock(SparkCluster.class);
        when(sparkCluster.getSparkConf()).thenReturn(null);

        // When
        Map<String, String> result = ApplicationSubmissionHelper.getSparkConf(submissionId, request, appConfig, sparkCluster);

        // Then
        assertNotNull(result);
        assertEquals("value1", result.get("key1"));
        assertEquals("value2", result.get("key2"));
        assertEquals("TestApp", result.get("spark.app.name"));
    }

    @Test
    void testGetSparkConf_SparkClusterConfiguration() {
        // Given
        String submissionId = "test-submission-id";
        when(request.getSparkConf()).thenReturn(null);
        when(request.getApplicationName()).thenReturn(null);

        Map<String, String> clusterSparkConf = new HashMap<>();
        clusterSparkConf.put("key3", "value3");
        when(sparkCluster.getSparkConf()).thenReturn(clusterSparkConf);

        // When
        Map<String, String> result = ApplicationSubmissionHelper.getSparkConf(submissionId, request, appConfig, sparkCluster);

        // Then
        assertNotNull(result);
        assertEquals("value3", result.get("key3"));
    }

    @Test
    void testGetSparkConf_RequestSparkConfiguration() {
        // Given
        String submissionId = "test-submission-id";
        Map<String, String> requestSparkConf = new HashMap<>();
        requestSparkConf.put("key4", "value4");
        when(request.getSparkConf()).thenReturn(requestSparkConf);
        when(request.getApplicationName()).thenReturn(null);

        // When
        Map<String, String> result = ApplicationSubmissionHelper.getSparkConf(submissionId, request, appConfig, sparkCluster);

        // Then
        assertNotNull(result);
        assertEquals("value4", result.get("key4"));
    }

    @Test
    void testGetSparkConf_FixedSparkConfiguration() {
        // Given
        String submissionId = "test-submission-id";
        when(request.getSparkConf()).thenReturn(null);
        when(request.getApplicationName()).thenReturn(null);

        appConfig.setFixedSparkConf(Map.of("key5", "value5"));

        // When
        Map<String, String> result = ApplicationSubmissionHelper.getSparkConf(submissionId, request, appConfig, sparkCluster);

        // Then
        assertNotNull(result);
        assertEquals("value5", result.get("key5"));
    }

    @Test
    void testGetSparkConf_EmptyConfigurations() {
        // Given
        String submissionId = "test-submission-id";
        when(request.getSparkConf()).thenReturn(null);
        when(request.getApplicationName()).thenReturn(null);
        appConfig.setDefaultSparkConf(null);
        appConfig.setFixedSparkConf(null);
        when(sparkCluster.getSparkConf()).thenReturn(null);

        // When
        Map<String, String> result = ApplicationSubmissionHelper.getSparkConf(submissionId, request, appConfig, sparkCluster);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetSparkConf_MultipleConfigurations() {
        // Given
        String submissionId = "test-submission-id";
        when(request.getSparkConf()).thenReturn(Map.of("key6", "value6"));
        when(request.getApplicationName()).thenReturn("TestApp2");

        appConfig.setDefaultSparkConf(Map.of("key7", "value7"));
        appConfig.setFixedSparkConf(Map.of("key8", "value8"));
        when(sparkCluster.getSparkConf()).thenReturn(Map.of("key9", "value9"));

        // When
        Map<String, String> result = ApplicationSubmissionHelper.getSparkConf(submissionId, request, appConfig, sparkCluster);

        // Then
        assertNotNull(result);
        assertEquals("value6", result.get("key6"));
        assertEquals("value7", result.get("key7"));
        assertEquals("value8", result.get("key8"));
        assertEquals("value9", result.get("key9"));
        assertEquals("TestApp2", result.get("spark.app.name"));
    }

    @Test
    void testGetSparkConf_ApplicationNameOnly() {
        // Given
        String submissionId = "test-submission-id";
        when(request.getSparkConf()).thenReturn(null);
        when(request.getApplicationName()).thenReturn("OnlyAppName");

        // When
        Map<String, String> result = ApplicationSubmissionHelper.getSparkConf(submissionId, request, appConfig, sparkCluster);

        // Then
        assertNotNull(result);
        assertEquals("OnlyAppName", result.get("spark.app.name"));
    }

    @Test
    void testGetSparkConf_MixedConfigurations() {
        // Given
        String submissionId = "test-submission-id";
        when(request.getSparkConf()).thenReturn(Map.of("key10", "value10"));
        when(request.getApplicationName()).thenReturn("MixedApp");

        appConfig.setDefaultSparkConf(Map.of("key11", "value11"));
        appConfig.setFixedSparkConf(Map.of("key12", "value12"));
        when(sparkCluster.getSparkConf()).thenReturn(Map.of("key13", "value13"));

        // When
        Map<String, String> result = ApplicationSubmissionHelper.getSparkConf(submissionId, request, appConfig, sparkCluster);

        // Then
        assertNotNull(result);
        assertEquals("value10", result.get("key10"));
        assertEquals("value11", result.get("key11"));
        assertEquals("value12", result.get("key12"));
        assertEquals("value13", result.get("key13"));
        assertEquals("MixedApp", result.get("spark.app.name"));
    }

    @Test
    void testGetSparkConf_OnlyFixedConfiguration() {
        // Given
        String submissionId = "test-submission-id";
        when(request.getSparkConf()).thenReturn(null);
        when(request.getApplicationName()).thenReturn(null);

        appConfig.setFixedSparkConf(Map.of("key14", "value14"));

        // When
        Map<String, String> result = ApplicationSubmissionHelper.getSparkConf(submissionId, request, appConfig, sparkCluster);

        // Then
        assertNotNull(result);
        assertEquals("value14", result.get("key14"));
    }

    @Test
    void testGetSparkConf_DefaultAndFixedConfigurations() {
        // Given
        String submissionId = "test-submission-id";
        when(request.getSparkConf()).thenReturn(null);
        when(request.getApplicationName()).thenReturn(null);

        appConfig.setDefaultSparkConf(Map.of("key15", "value15"));
        appConfig.setFixedSparkConf(Map.of("key16", "value16"));

        // When
        Map<String, String> result = ApplicationSubmissionHelper.getSparkConf(submissionId, request, appConfig, sparkCluster);

        // Then
        assertNotNull(result);
        assertEquals("value15", result.get("key15"));
        assertEquals("value16", result.get("key16"));
    }

    @Test
    void testGetSparkConf_NullParameters() {
        // Test null submissionId
        Map<String, String> result = ApplicationSubmissionHelper.getSparkConf(null, request, appConfig, sparkCluster);
        assertNotNull(result);
        assertTrue(result.isEmpty());

        // Test null request
        result = ApplicationSubmissionHelper.getSparkConf("test-submission-id", null, appConfig, sparkCluster);
        assertNotNull(result);
        assertTrue(result.isEmpty());

        // Test null appConfig
        result = ApplicationSubmissionHelper.getSparkConf("test-submission-id", request, null, sparkCluster);
        assertNotNull(result);
        assertTrue(result.isEmpty());

        // Test null sparkCluster
        result = ApplicationSubmissionHelper.getSparkConf("test-submission-id", request, appConfig, null);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
```