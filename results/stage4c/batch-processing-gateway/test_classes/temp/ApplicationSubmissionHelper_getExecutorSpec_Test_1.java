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
import com.apple.spark.operator.NodeSelectorOperator;
import com.apple.spark.operator.NodeSelectorRequirement;
import com.apple.spark.operator.NodeSelectorTerm;
import com.apple.spark.operator.RequiredDuringSchedulingIgnoredDuringExecutionTerm;
import com.apple.spark.operator.Affinity;
import com.apple.spark.operator.NodeAffinity;
import io.fabric8.kubernetes.api.model.PodDNSConfig;
import io.fabric8.kubernetes.api.model.PodDNSConfigOption;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static com.apple.spark.core.Constants.*;
import static com.apple.spark.core.SparkConstants.CORE_LIMIT_RATIO;
import com.apple.spark.operator.Volume;

@ExtendWith(MockitoExtension.class)
class ApplicationSubmissionHelper_getExecutorSpec_Test {

    private SubmitApplicationRequest request;

    private AppConfig appConfig;

    private SparkCluster sparkCluster;

    private String parentQueue;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the necessary fields and dependencies before each test
        request = mock(SubmitApplicationRequest.class);
        // Mock AppConfig to avoid null pointer issues
        appConfig = mock(AppConfig.class);
        sparkCluster = mock(SparkCluster.class);
        parentQueue = "default";
    }

    @Test
    void testGetExecutorSpec_withValidRequest() {
        // Given
        ExecutorSpec executorSpec = new ExecutorSpec();
        when(request.getExecutor()).thenReturn(executorSpec);
        Map<String, String> executorLabels = new HashMap<>();
        executorLabels.put("key", "value");
        when(executorSpec.getLabels()).thenReturn(executorLabels);
        // When
        ExecutorSpec result = ApplicationSubmissionHelper.getExecutorSpec(request, appConfig, parentQueue, sparkCluster);
        // Then
        assertNotNull(result);
        assertEquals(executorSpec, result);
    }

    @Test
    void testGetExecutorSpec_withNullExecutorAndVolumes() {
        // Given
        when(request.getExecutor()).thenReturn(null);
        when(request.getVolumes()).thenReturn(null);
        when(sparkCluster.getExecutor()).thenReturn(null);
        when(sparkCluster.getVolumes()).thenReturn(null);
        when(request.getSpotInstance()).thenReturn(false);
        // Avoid null pointer
        when(appConfig.getQueues()).thenReturn(Collections.emptyList());
        // When
        ExecutorSpec result = ApplicationSubmissionHelper.getExecutorSpec(request, appConfig, parentQueue, sparkCluster);
        // Then
        assertNotNull(result);
        assertEquals(0, result.getCores());
        assertEquals("0m", result.getCoreLimit());
        assertEquals("0Mi", result.getMemory());
        assertEquals(Collections.singletonMap(KUBE_CLUSTER_AUTOSCALER_SCALE_IN_ANNOTATION_KEY, KUBE_CLUSTER_AUTOSCALER_SCALE_IN_ANNOTATION_VALUE), result.getAnnotations());
        assertNull(result.getAffinity());
        assertEquals(Collections.singletonList(new PodDNSConfigOption(DNS_CONFIG_OPTION_NDOTS_NAME, DNS_CONFIG_OPTION_NDOTS_VALUE)), result.getDnsConfig().getOptions());
    }

    @Test
    void testGetExecutorSpec_withExecutorDetailsInRequest() {
        // Given
        ExecutorSpec executorSpec = new ExecutorSpec();
        executorSpec.setCores(2);
        executorSpec.setCoreLimit("2000m");
        executorSpec.setMemory("2048Mi");
        Map<String, String> labels = new HashMap<>();
        labels.put(DAG_NAME_LABEL, "testDag");
        labels.put(TASK_NAME_LABEL, "testTask");
        executorSpec.setLabels(labels);
        when(request.getExecutor()).thenReturn(executorSpec);
        when(request.getVolumes()).thenReturn(Collections.emptyList());
        when(request.getSpotInstance()).thenReturn(true);
        when(sparkCluster.getExecutor()).thenReturn(null);
        when(sparkCluster.getVolumes()).thenReturn(null);
        // Avoid null pointer
        when(appConfig.getQueues()).thenReturn(Collections.emptyList());
        // When
        ExecutorSpec result = ApplicationSubmissionHelper.getExecutorSpec(request, appConfig, parentQueue, sparkCluster);
        // Then
        assertNotNull(result);
        assertEquals(2, result.getCores());
        assertEquals("2000m", result.getCoreLimit());
        assertEquals("2048Mi", result.getMemory());
        assertEquals(labels, result.getLabels());
        assertEquals(Collections.singletonMap(KUBE_CLUSTER_AUTOSCALER_SCALE_IN_ANNOTATION_KEY, KUBE_CLUSTER_AUTOSCALER_SCALE_IN_ANNOTATION_VALUE), result.getAnnotations());
        assertNotNull(result.getAffinity());
        assertEquals(Collections.singletonList(new PodDNSConfigOption(DNS_CONFIG_OPTION_NDOTS_NAME, DNS_CONFIG_OPTION_NDOTS_VALUE)), result.getDnsConfig().getOptions());
    }

    @Test
    void testGetExecutorSpec_withExecutorCoreLimitEmpty() {
        // Given
        ExecutorSpec executorSpec = new ExecutorSpec();
        executorSpec.setCores(4);
        executorSpec.setCoreLimit("");
        executorSpec.setMemory("4096Mi");
        when(request.getExecutor()).thenReturn(executorSpec);
        when(request.getVolumes()).thenReturn(Collections.emptyList());
        when(request.getSpotInstance()).thenReturn(false);
        when(sparkCluster.getExecutor()).thenReturn(mock(ExecutorSpec.class));
        when(sparkCluster.getVolumes()).thenReturn(Collections.singletonList(mock(Volume.class)));
        // Avoid null pointer
        when(appConfig.getQueues()).thenReturn(Collections.emptyList());
        // When
        ExecutorSpec result = ApplicationSubmissionHelper.getExecutorSpec(request, appConfig, parentQueue, sparkCluster);
        // Then
        assertNotNull(result);
        assertEquals(4, result.getCores());
        assertEquals("0m", result.getCoreLimit());
        assertEquals("4096Mi", result.getMemory());
        assertNotNull(result.getVolumeMounts());
        assertEquals(Collections.singletonMap(KUBE_CLUSTER_AUTOSCALER_SCALE_IN_ANNOTATION_KEY, KUBE_CLUSTER_AUTOSCALER_SCALE_IN_ANNOTATION_VALUE), result.getAnnotations());
        assertNotNull(result.getAffinity());
        assertEquals(Collections.singletonList(new PodDNSConfigOption(DNS_CONFIG_OPTION_NDOTS_NAME, DNS_CONFIG_OPTION_NDOTS_VALUE)), result.getDnsConfig().getOptions());
    }

    @Test
    void testGetExecutorSpec_withVolumesInRequestAndCluster() {
        // Given
        ExecutorSpec executorSpec = new ExecutorSpec();
        executorSpec.setCores(2);
        executorSpec.setCoreLimit("1000m");
        executorSpec.setMemory("1024Mi");
        Map<String, String> labels = new HashMap<>();
        labels.put(DAG_NAME_LABEL, "testDag");
        labels.put(TASK_NAME_LABEL, "testTask");
        executorSpec.setLabels(labels);
        when(request.getExecutor()).thenReturn(executorSpec);
        when(request.getVolumes()).thenReturn(Collections.singletonList(mock(Volume.class)));
        when(request.getSpotInstance()).thenReturn(false);
        when(sparkCluster.getExecutor()).thenReturn(mock(ExecutorSpec.class));
        when(sparkCluster.getVolumes()).thenReturn(Collections.singletonList(mock(Volume.class)));
        // Avoid null pointer
        when(appConfig.getQueues()).thenReturn(Collections.emptyList());
        // When
        ExecutorSpec result = ApplicationSubmissionHelper.getExecutorSpec(request, appConfig, parentQueue, sparkCluster);
        // Then
        assertNotNull(result);
        assertEquals(2, result.getCores());
        assertEquals("1000m", result.getCoreLimit());
        assertEquals("1024Mi", result.getMemory());
        assertEquals(labels, result.getLabels());
        assertNotNull(result.getVolumeMounts());
        assertEquals(Collections.singletonMap(KUBE_CLUSTER_AUTOSCALER_SCALE_IN_ANNOTATION_KEY, KUBE_CLUSTER_AUTOSCALER_SCALE_IN_ANNOTATION_VALUE), result.getAnnotations());
        assertNotNull(result.getAffinity());
        assertEquals(Collections.singletonList(new PodDNSConfigOption(DNS_CONFIG_OPTION_NDOTS_NAME, DNS_CONFIG_OPTION_NDOTS_VALUE)), result.getDnsConfig().getOptions());
    }

    @Test
    void testGetExecutorSpec_withInvalidCoreLimit() {
        // Given
        ExecutorSpec executorSpec = new ExecutorSpec();
        executorSpec.setCores(2);
        executorSpec.setCoreLimit("invalid");
        executorSpec.setMemory("2048Mi");
        Map<String, String> labels = new HashMap<>();
        labels.put(DAG_NAME_LABEL, "testDag");
        labels.put(TASK_NAME_LABEL, "testTask");
        executorSpec.setLabels(labels);
        when(request.getExecutor()).thenReturn(executorSpec);
        when(request.getVolumes()).thenReturn(Collections.emptyList());
        when(request.getSpotInstance()).thenReturn(true);
        when(sparkCluster.getExecutor()).thenReturn(null);
        when(sparkCluster.getVolumes()).thenReturn(null);
        // Avoid null pointer
        when(appConfig.getQueues()).thenReturn(Collections.emptyList());
        // When & Then
        assertThrows(NumberFormatException.class, () -> {
            ApplicationSubmissionHelper.getExecutorSpec(request, appConfig, parentQueue, sparkCluster);
        });
    }

    @Test
    void testGetExecutorSpec_withInvalidMemoryFormat() {
        // Given
        ExecutorSpec executorSpec = new ExecutorSpec();
        executorSpec.setCores(2);
        executorSpec.setCoreLimit("1000m");
        executorSpec.setMemory("invalid");
        Map<String, String> labels = new HashMap<>();
        labels.put(DAG_NAME_LABEL, "testDag");
        labels.put(TASK_NAME_LABEL, "testTask");
        executorSpec.setLabels(labels);
        when(request.getExecutor()).thenReturn(executorSpec);
        when(request.getVolumes()).thenReturn(Collections.emptyList());
        when(request.getSpotInstance()).thenReturn(false);
        when(sparkCluster.getExecutor()).thenReturn(null);
        when(sparkCluster.getVolumes()).thenReturn(null);
        // Avoid null pointer
        when(appConfig.getQueues()).thenReturn(Collections.emptyList());
        // When & Then
        assertThrows(NumberFormatException.class, () -> {
            ApplicationSubmissionHelper.getExecutorSpec(request, appConfig, parentQueue, sparkCluster);
        });
    }

    @Test
    void testGetExecutorSpec_withNullDagName() {
        // Given
        ExecutorSpec executorSpec = new ExecutorSpec();
        executorSpec.setCores(2);
        executorSpec.setCoreLimit("1000m");
        executorSpec.setMemory("2048Mi");
        Map<String, String> labels = new HashMap<>();
        labels.put(DAG_NAME_LABEL, null);
        labels.put(TASK_NAME_LABEL, "testTask");
        executorSpec.setLabels(labels);
        when(request.getExecutor()).thenReturn(executorSpec);
        when(request.getVolumes()).thenReturn(Collections.emptyList());
        when(request.getSpotInstance()).thenReturn(false);
        when(sparkCluster.getExecutor()).thenReturn(null);
        when(sparkCluster.getVolumes()).thenReturn(null);
        // Avoid null pointer
        when(appConfig.getQueues()).thenReturn(Collections.emptyList());
        // When & Then
        assertThrows(NullPointerException.class, () -> {
            ApplicationSubmissionHelper.getExecutorSpec(request, appConfig, parentQueue, sparkCluster);
        });
    }

    @Test
    void testGetExecutorSpec_withNullTaskName() {
        // Given
        ExecutorSpec executorSpec = new ExecutorSpec();
        executorSpec.setCores(2);
        executorSpec.setCoreLimit("1000m");
        executorSpec.setMemory("2048Mi");
        Map<String, String> labels = new HashMap<>();
        labels.put(DAG_NAME_LABEL, "testDag");
        labels.put(TASK_NAME_LABEL, null);
        executorSpec.setLabels(labels);
        when(request.getExecutor()).thenReturn(executorSpec);
        when(request.getVolumes()).thenReturn(Collections.emptyList());
        when(request.getSpotInstance()).thenReturn(false);
        when(sparkCluster.getExecutor()).thenReturn(null);
        when(sparkCluster.getVolumes()).thenReturn(null);
        // Avoid null pointer
        when(appConfig.getQueues()).thenReturn(Collections.emptyList());
        // When & Then
        assertThrows(NullPointerException.class, () -> {
            ApplicationSubmissionHelper.getExecutorSpec(request, appConfig, parentQueue, sparkCluster);
        });
    }
}
