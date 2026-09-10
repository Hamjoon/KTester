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
import java.util.Collections;

@ExtendWith(MockitoExtension.class)
class ApplicationSubmissionHelper_getExecutorSpec_Test {

    private SubmitApplicationRequest request;

    private AppConfig appConfig;

    private SparkCluster sparkCluster;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the request, appConfig, and sparkCluster before each test
        // Assuming a default constructor exists
        request = new SubmitApplicationRequest();
        // Using the default constructor
        appConfig = new AppConfig();
        // Mocking SparkCluster as needed
        sparkCluster = mock(SparkCluster.class);
    }

    @Test
    void testGetExecutorSpec_withValidRequest() {
        // Given
        // Assuming ExecutorSpec has a default constructor
        request.setExecutor(new ExecutorSpec());
        request.getExecutor().setCores(2);
        request.getExecutor().setMemory("2g");
        // Assuming you have methods to set other necessary properties
        // appConfig and sparkCluster should also be set up with required values
        // When
        ExecutorSpec executorSpec = ApplicationSubmissionHelper.getExecutorSpec(request, appConfig, "parentQueue", sparkCluster);
        // Then
        assertNotNull(executorSpec);
        assertEquals(2, executorSpec.getCores());
        assertEquals("2g", executorSpec.getMemory());
        // Add more assertions as necessary to validate the behavior
    }

    @Test
    void testGetExecutorSpec_ExecutorNotNull() {
        // Test case where executor is provided in the request.
        // Given
        request.setExecutor(new ExecutorSpec());
        request.getExecutor().setCores(2);
        request.getExecutor().setMemory("4Gi");
        request.getExecutor().setLabels(Collections.singletonMap("dagName", "testDag"));
        request.getExecutor().setVolumeMounts(Collections.emptyList());
        // When
        ExecutorSpec executorSpec = ApplicationSubmissionHelper.getExecutorSpec(request, appConfig, "parentQueue", sparkCluster);
        // Then
        assertNotNull(executorSpec);
        assertEquals(2, executorSpec.getCores());
        assertEquals("4Gi", executorSpec.getMemory());
        assertEquals("testDag", executorSpec.getLabels().get("dagName"));
    }

    @Test
    void testGetExecutorSpec_ExecutorNull() {
        // Test case where executor is null in the request.
        // Given
        request.setExecutor(null);
        request.setVolumes(Collections.emptyList());
        // When
        ExecutorSpec executorSpec = ApplicationSubmissionHelper.getExecutorSpec(request, appConfig, "parentQueue", sparkCluster);
        // Then
        assertNotNull(executorSpec);
        assertEquals(0, executorSpec.getCores());
        assertNull(executorSpec.getMemory());
        assertTrue(executorSpec.getLabels().isEmpty());
    }

    @Test
    void testGetExecutorSpec_VolumesSpecifiedInRequest() {
        // Test case where volumes are specified in the request.
        // Given
        request.setExecutor(new ExecutorSpec());
        request.getExecutor().setVolumeMounts(Collections.emptyList());
        request.setVolumes(Arrays.asList("volume1", "volume2"));
        // When
        ExecutorSpec executorSpec = ApplicationSubmissionHelper.getExecutorSpec(request, appConfig, "parentQueue", sparkCluster);
        // Then
        assertNotNull(executorSpec);
        assertTrue(executorSpec.getVolumeMounts().isEmpty());
    }

    @Test
    void testGetExecutorSpec_VolumesSpecifiedInSparkCluster() {
        // Test case where volumes are specified in SparkCluster.
        // Given
        request.setExecutor(null);
        request.setVolumes(Collections.emptyList());
        when(sparkCluster.getVolumes()).thenReturn(Arrays.asList("volume3"));
        // When
        ExecutorSpec executorSpec = ApplicationSubmissionHelper.getExecutorSpec(request, appConfig, "parentQueue", sparkCluster);
        // Then
        assertNotNull(executorSpec);
        assertTrue(executorSpec.getVolumeMounts().isEmpty());
    }

    @Test
    void testGetExecutorSpec_CoreLimitAndMemoryAdjustments() {
        // Test case to verify core limits and memory adjustments.
        // Given
        request.setExecutor(new ExecutorSpec());
        request.getExecutor().setCores(3);
        request.getExecutor().setMemory("2Gi");
        // When
        ExecutorSpec executorSpec = ApplicationSubmissionHelper.getExecutorSpec(request, appConfig, "parentQueue", sparkCluster);
        // Then
        assertNotNull(executorSpec);
        assertEquals(3, executorSpec.getCores());
        assertEquals("2Gi", executorSpec.getMemory());
        // Assuming core limit is calculated as 3m
        assertEquals("3m", executorSpec.getCoreLimit());
    }

    @Test
    void testGetExecutorSpec_AffinitySettings() {
        // Test case to verify affinity settings when spot instances are requested.
        // Given
        request.setExecutor(new ExecutorSpec());
        request.getExecutor().setSpotInstance(true);
        // When
        ExecutorSpec executorSpec = ApplicationSubmissionHelper.getExecutorSpec(request, appConfig, "parentQueue", sparkCluster);
        // Then
        assertNotNull(executorSpec);
        assertNotNull(executorSpec.getAffinity());
        assertNotNull(executorSpec.getAffinity().getRequiredDuringSchedulingIgnoredDuringExecution());
    }

    @Test
    void testGetExecutorSpec_DNSConfig() {
        // Test case to verify DNS configuration is set correctly.
        // Given
        request.setExecutor(null);
        // When
        ExecutorSpec executorSpec = ApplicationSubmissionHelper.getExecutorSpec(request, appConfig, "parentQueue", sparkCluster);
        // Then
        assertNotNull(executorSpec);
        assertNotNull(executorSpec.getDnsConfig());
        assertEquals(1, executorSpec.getDnsConfig().getOptions().size());
        assertEquals("ndots", executorSpec.getDnsConfig().getOptions().get(0).getName());
        assertEquals("5", executorSpec.getDnsConfig().getOptions().get(0).getValue());
    }
}
