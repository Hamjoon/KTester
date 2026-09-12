```java
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

@ExtendWith(MockitoExtension.class)
class ApplicationSubmissionHelper_getSparkConf_Test {

    private AppConfig appConfig;
    private SubmitApplicationRequest request;
    private AppConfig.SparkCluster sparkCluster;
    private String submissionId;

    @BeforeAll
    static void setupBeforeAll() {
        // Initialize any global resources if needed
    }

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

    @AfterEach
    void teardownAfterEach() {
        // Clean up resources if needed
    }

    @AfterAll
    static void teardownAfterAll() {
        // Clean up global resources if needed
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
}
```