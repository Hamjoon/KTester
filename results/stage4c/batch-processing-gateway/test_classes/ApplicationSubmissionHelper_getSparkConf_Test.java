package com.apple.spark.core;

import com.apple.spark.AppConfig;
import com.apple.spark.api.SubmitApplicationRequest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class ApplicationSubmissionHelper_getSparkConf_Test {

    private AppConfig appConfig;

    private SubmitApplicationRequest request;

    private AppConfig.SparkCluster sparkCluster;

    private String submissionId;

    @BeforeEach
    void setupBeforeEach() {
        appConfig = new AppConfig();
        request = new SubmitApplicationRequest();
        sparkCluster = new AppConfig.SparkCluster();
        submissionId = "testSubmissionId";
        // Set up default configurations
        Map<String, String> defaultSparkConf = new HashMap<>();
        defaultSparkConf.put("spark.default.conf", "default");
        appConfig.setDefaultSparkConf(defaultSparkConf);
        Map<String, String> fixedSparkConf = new HashMap<>();
        fixedSparkConf.put("spark.fixed.conf", "fixed");
        appConfig.setFixedSparkConf(fixedSparkConf);
        Map<String, String> requestSparkConf = new HashMap<>();
        requestSparkConf.put("spark.request.conf", "request");
        request.setSparkConf(requestSparkConf);
        Map<String, String> clusterSparkConf = new HashMap<>();
        clusterSparkConf.put("spark.cluster.conf", "cluster");
        sparkCluster.setSparkConf(clusterSparkConf);
    }

    @Test
    void testGetSparkConf() {
        // Given
        // Setup is done in setupBeforeEach
        // When
        Map<String, String> sparkConf = ApplicationSubmissionHelper.getSparkConf(submissionId, request, appConfig, sparkCluster);
        // Then
        assertNotNull(sparkConf);
        assertEquals("default", sparkConf.get("spark.default.conf"));
        assertEquals("fixed", sparkConf.get("spark.fixed.conf"));
        assertEquals("request", sparkConf.get("spark.request.conf"));
        assertEquals("cluster", sparkConf.get("spark.cluster.conf"));
    }

    @Test
    void testWithAllConfigurationsProvided() {
        // Given
        submissionId = "sub-123";
        request.setSparkConf(Map.of("spark.executor.memory", "2g"));
        request.setApplicationName("TestApp");
        appConfig.setDefaultSparkConf(Map.of("spark.executor.memory", "1g", "spark.driver.memory", "1g"));
        appConfig.setFixedSparkConf(Map.of("spark.driver.cores", "1"));
        sparkCluster.setSparkConf(Map.of("spark.executor.cores", "2"));
        // When
        Map<String, String> sparkConf = ApplicationSubmissionHelper.getSparkConf(submissionId, request, appConfig, sparkCluster);
        // Then
        assertNotNull(sparkConf);
        assertEquals("2g", sparkConf.get("spark.executor.memory"));
        assertEquals("1g", sparkConf.get("spark.driver.memory"));
        assertEquals("2", sparkConf.get("spark.executor.cores"));
        assertEquals("1", sparkConf.get("spark.driver.cores"));
        assertEquals("TestApp", sparkConf.get(SparkConstants.SPARK_APP_NAME_CONFIG));
    }

    @Test
    void testWithAllConfigurationsNull() {
        // Given
        submissionId = "sub-456";
        request.setSparkConf(null);
        request.setApplicationName(null);
        appConfig.setDefaultSparkConf(null);
        appConfig.setFixedSparkConf(null);
        sparkCluster.setSparkConf(null);
        // When
        Map<String, String> sparkConf = ApplicationSubmissionHelper.getSparkConf(submissionId, request, appConfig, sparkCluster);
        // Then
        assertNull(sparkConf);
    }

    @Test
    void testWithNoSparkConfInRequestAndCluster() {
        // Given
        submissionId = "sub-789";
        request.setSparkConf(new HashMap<>());
        request.setApplicationName("AppWithoutConf");
        appConfig.setDefaultSparkConf(Map.of("spark.executor.memory", "4g"));
        appConfig.setFixedSparkConf(Map.of("spark.executor.cores", "4"));
        sparkCluster.setSparkConf(new HashMap<>());
        // When
        Map<String, String> sparkConf = ApplicationSubmissionHelper.getSparkConf(submissionId, request, appConfig, sparkCluster);
        // Then
        assertNotNull(sparkConf);
        assertEquals("4g", sparkConf.get("spark.executor.memory"));
        assertEquals("4", sparkConf.get("spark.executor.cores"));
        assertEquals("AppWithoutConf", sparkConf.get(SparkConstants.SPARK_APP_NAME_CONFIG));
    }

    @Test
    void testWithEmptyApplicationName() {
        // Given
        submissionId = "sub-101";
        request.setSparkConf(Map.of("spark.executor.memory", "3g"));
        request.setApplicationName("");
        appConfig.setDefaultSparkConf(Map.of("spark.executor.memory", "2g"));
        appConfig.setFixedSparkConf(Map.of("spark.driver.memory", "1g"));
        sparkCluster.setSparkConf(Map.of("spark.executor.cores", "3"));
        // When
        Map<String, String> sparkConf = ApplicationSubmissionHelper.getSparkConf(submissionId, request, appConfig, sparkCluster);
        // Then
        assertNotNull(sparkConf);
        assertEquals("3g", sparkConf.get("spark.executor.memory"));
        assertEquals("1g", sparkConf.get("spark.driver.memory"));
        assertEquals("3", sparkConf.get("spark.executor.cores"));
        assertFalse(sparkConf.containsKey(SparkConstants.SPARK_APP_NAME_CONFIG));
    }

    @Test
    void testWithOnlyRequestConf() {
        // Given
        submissionId = "sub-202";
        request.setSparkConf(Map.of("spark.executor.memory", "2g"));
        request.setApplicationName("AppWithOnlyRequestConf");
        appConfig.setDefaultSparkConf(null);
        appConfig.setFixedSparkConf(null);
        sparkCluster.setSparkConf(null);
        // When
        Map<String, String> sparkConf = ApplicationSubmissionHelper.getSparkConf(submissionId, request, appConfig, sparkCluster);
        // Then
        assertNotNull(sparkConf);
        assertEquals("2g", sparkConf.get("spark.executor.memory"));
        assertEquals("AppWithOnlyRequestConf", sparkConf.get(SparkConstants.SPARK_APP_NAME_CONFIG));
    }

    @Test
    void testWithOnlyFixedConf() {
        // Given
        submissionId = "sub-303";
        request.setSparkConf(new HashMap<>());
        request.setApplicationName("AppWithOnlyFixedConf");
        appConfig.setDefaultSparkConf(null);
        appConfig.setFixedSparkConf(Map.of("spark.driver.memory", "2g"));
        sparkCluster.setSparkConf(null);
        // When
        Map<String, String> sparkConf = ApplicationSubmissionHelper.getSparkConf(submissionId, request, appConfig, sparkCluster);
        // Then
        assertNotNull(sparkConf);
        assertEquals("2g", sparkConf.get("spark.driver.memory"));
        assertEquals("AppWithOnlyFixedConf", sparkConf.get(SparkConstants.SPARK_APP_NAME_CONFIG));
    }

    @Test
    void testWithOnlyDefaultConf() {
        // Given
        submissionId = "sub-404";
        request.setSparkConf(null);
        request.setApplicationName(null);
        appConfig.setDefaultSparkConf(Map.of("spark.driver.memory", "3g"));
        appConfig.setFixedSparkConf(null);
        sparkCluster.setSparkConf(new HashMap<>());
        // When
        Map<String, String> sparkConf = ApplicationSubmissionHelper.getSparkConf(submissionId, request, appConfig, sparkCluster);
        // Then
        assertNotNull(sparkConf);
        assertEquals("3g", sparkConf.get("spark.driver.memory"));
    }

    @Test
    void testWithRequestAndClusterConf() {
        // Given
        submissionId = "sub-505";
        request.setSparkConf(Map.of("spark.driver.memory", "4g"));
        request.setApplicationName("AppWithRequestAndClusterConf");
        appConfig.setDefaultSparkConf(null);
        appConfig.setFixedSparkConf(null);
        sparkCluster.setSparkConf(Map.of("spark.executor.cores", "2"));
        // When
        Map<String, String> sparkConf = ApplicationSubmissionHelper.getSparkConf(submissionId, request, appConfig, sparkCluster);
        // Then
        assertNotNull(sparkConf);
        assertEquals("4g", sparkConf.get("spark.driver.memory"));
        assertEquals("2", sparkConf.get("spark.executor.cores"));
        assertEquals("AppWithRequestAndClusterConf", sparkConf.get(SparkConstants.SPARK_APP_NAME_CONFIG));
    }

    @Test
    void testWithNullRequest() {
        // Given
        submissionId = "sub-001";
        request = null;
        appConfig.setDefaultSparkConf(Map.of("spark.executor.memory", "1g"));
        appConfig.setFixedSparkConf(Map.of("spark.driver.cores", "1"));
        sparkCluster.setSparkConf(Map.of("spark.executor.cores", "2"));
        // When & Then
        assertThrows(NullPointerException.class, () -> {
            ApplicationSubmissionHelper.getSparkConf(submissionId, request, appConfig, sparkCluster);
        });
    }

    @Test
    void testWithNullAppConfig() {
        // Given
        submissionId = "sub-002";
        request.setSparkConf(Map.of("spark.executor.memory", "2g"));
        request.setApplicationName("TestApp");
        appConfig = null;
        sparkCluster.setSparkConf(Map.of("spark.executor.cores", "2"));
        // When & Then
        assertThrows(NullPointerException.class, () -> {
            ApplicationSubmissionHelper.getSparkConf(submissionId, request, appConfig, sparkCluster);
        });
    }

    @Test
    void testWithNullSparkCluster() {
        // Given
        submissionId = "sub-003";
        request.setSparkConf(Map.of("spark.executor.memory", "2g"));
        request.setApplicationName("TestApp");
        appConfig.setDefaultSparkConf(Map.of("spark.executor.memory", "1g"));
        appConfig.setFixedSparkConf(Map.of("spark.driver.cores", "1"));
        sparkCluster = null;
        // When & Then
        assertThrows(NullPointerException.class, () -> {
            ApplicationSubmissionHelper.getSparkConf(submissionId, request, appConfig, sparkCluster);
        });
    }
}
