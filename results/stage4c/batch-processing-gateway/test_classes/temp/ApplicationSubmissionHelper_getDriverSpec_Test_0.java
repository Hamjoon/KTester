package com.apple.spark.core;

import com.apple.spark.AppConfig;
import com.apple.spark.api.SubmitApplicationRequest;
import com.apple.spark.operator.DriverSpec;
import com.apple.spark.AppConfig.SparkCluster;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import com.apple.spark.operator.NodeAffinity;
import com.apple.spark.operator.Affinity;
import com.apple.spark.operator.NodeSelectorRequirement;
import com.apple.spark.operator.NodeSelectorTerm;
import com.apple.spark.operator.RequiredDuringSchedulingIgnoredDuringExecutionTerm;
import com.apple.spark.operator.NodeSelectorOperator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class ApplicationSubmissionHelper_getDriverSpec_Test {

    private SubmitApplicationRequest request;

    private AppConfig appConfig;

    private SparkCluster sparkCluster;

    private String parentQueue;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the mocked dependencies and any other necessary setup
        request = mock(SubmitApplicationRequest.class);
        appConfig = new AppConfig();
        sparkCluster = mock(SparkCluster.class);
        parentQueue = "dummyQueue";
    }

    @Test
    void testGetDriverSpec() {
        // Given
        // Example setup, modify as necessary
        when(request.getDriver()).thenReturn(null);
        when(sparkCluster.getSparkServiceAccount()).thenReturn("defaultAccount");
        // When
        DriverSpec result = ApplicationSubmissionHelper.getDriverSpec(request, appConfig, parentQueue, sparkCluster);
        // Then
        assertNotNull(result);
        assertEquals("defaultAccount", result.getServiceAccount());
        // Add more assertions based on expected behavior
    }

    @Test
    void testGetDriverSpecWithDriverLabelsAndVolumes() {
        // Given
        Map<String, String> driverLabels = new HashMap<>();
        driverLabels.put(Constants.DAG_NAME_LABEL, "dagName");
        driverLabels.put(Constants.TASK_NAME_LABEL, "taskName");
        when(request.getDriver()).thenReturn(new DriverSpec());
        when(request.getDriver().getLabels()).thenReturn(driverLabels);
        when(request.getDriver().getVolumeMounts()).thenReturn(List.of("mount1", "mount2"));
        when(request.getDriver().getServiceAccount()).thenReturn("serviceAccount1");
        when(request.getDriver().getCores()).thenReturn(4);
        when(request.getDriver().getCoreLimit()).thenReturn("4000");
        when(request.getDriver().getMemory()).thenReturn("4096Mi");
        when(request.getVolumes()).thenReturn(List.of("volume1"));
        when(sparkCluster.getDriver()).thenReturn(new DriverSpec());
        when(sparkCluster.getDriver().getVolumeMounts()).thenReturn(List.of("mount3", "mount4"));
        when(sparkCluster.getSparkServiceAccount()).thenReturn("sparkServiceAccount1");
        // When
        DriverSpec result = ApplicationSubmissionHelper.getDriverSpec(request, appConfig, parentQueue, sparkCluster);
        // Then
        assertNotNull(result);
        assertEquals("serviceAccount1", result.getServiceAccount());
        assertEquals(4, result.getCores());
        assertEquals("4000", result.getCoreLimit());
        assertEquals("4096Mi", result.getMemory());
        assertEquals(driverLabels, result.getLabels());
        assertEquals(List.of("mount1", "mount2"), result.getVolumeMounts());
    }

    @Test
    void testGetDriverSpecWithNoDriverAndNoVolumes() {
        // Given
        when(request.getDriver()).thenReturn(null);
        when(request.getVolumes()).thenReturn(null);
        when(sparkCluster.getDriver()).thenReturn(new DriverSpec());
        when(sparkCluster.getDriver().getVolumeMounts()).thenReturn(List.of("mount3", "mount4"));
        when(sparkCluster.getSparkServiceAccount()).thenReturn("sparkServiceAccount1");
        // When
        DriverSpec result = ApplicationSubmissionHelper.getDriverSpec(request, appConfig, parentQueue, sparkCluster);
        // Then
        assertNotNull(result);
        assertEquals("sparkServiceAccount1", result.getServiceAccount());
        assertEquals(List.of("mount3", "mount4"), result.getVolumeMounts());
    }

    @Test
    void testGetDriverSpecWithMissingServiceAccountAndCoreLimit() {
        // Given
        Map<String, String> driverLabels = new HashMap<>();
        driverLabels.put(Constants.DAG_NAME_LABEL, "dagName");
        when(request.getDriver()).thenReturn(new DriverSpec());
        when(request.getDriver().getLabels()).thenReturn(driverLabels);
        when(request.getDriver().getVolumeMounts()).thenReturn(List.of("mount1"));
        when(request.getDriver().getServiceAccount()).thenReturn(null);
        when(request.getDriver().getCores()).thenReturn(2);
        when(request.getDriver().getCoreLimit()).thenReturn(null);
        when(request.getDriver().getMemory()).thenReturn("2048Mi");
        when(request.getVolumes()).thenReturn(List.of("volume1", "volume2"));
        when(sparkCluster.getDriver()).thenReturn(new DriverSpec());
        when(sparkCluster.getDriver().getVolumeMounts()).thenReturn(List.of("mount3", "mount4"));
        when(sparkCluster.getSparkServiceAccount()).thenReturn("sparkServiceAccount2");
        // When
        DriverSpec result = ApplicationSubmissionHelper.getDriverSpec(request, appConfig, parentQueue, sparkCluster);
        // Then
        assertNotNull(result);
        assertEquals("sparkServiceAccount2", result.getServiceAccount());
        assertEquals(2, result.getCores());
        assertNotNull(result.getCoreLimit());
        assertEquals("2048Mi", result.getMemory());
        assertEquals(driverLabels, result.getLabels());
        assertEquals(List.of("mount1"), result.getVolumeMounts());
    }

    @Test
    void testGetDriverSpecWithEmptyServiceAccountAndVolumes() {
        // Given
        when(request.getDriver()).thenReturn(new DriverSpec());
        when(request.getDriver().getLabels()).thenReturn(null);
        when(request.getDriver().getVolumeMounts()).thenReturn(List.of());
        when(request.getDriver().getServiceAccount()).thenReturn("");
        when(request.getDriver().getCores()).thenReturn(1);
        when(request.getDriver().getCoreLimit()).thenReturn("1000");
        when(request.getDriver().getMemory()).thenReturn("1024Mi");
        when(request.getVolumes()).thenReturn(null);
        when(sparkCluster.getDriver()).thenReturn(new DriverSpec());
        when(sparkCluster.getDriver().getVolumeMounts()).thenReturn(List.of("mount3", "mount4"));
        when(sparkCluster.getSparkServiceAccount()).thenReturn("sparkServiceAccount3");
        // When
        DriverSpec result = ApplicationSubmissionHelper.getDriverSpec(request, appConfig, parentQueue, sparkCluster);
        // Then
        assertNotNull(result);
        assertEquals("sparkServiceAccount3", result.getServiceAccount());
        assertEquals(1, result.getCores());
        assertEquals("1000", result.getCoreLimit());
        assertEquals("1024Mi", result.getMemory());
        assertEquals(List.of(), result.getVolumeMounts());
    }

    @Test
    void testGetDriverSpecWithNormalizedLabelsAndAdjustedResources() {
        // Given
        Map<String, String> driverLabels = new HashMap<>();
        driverLabels.put(Constants.TASK_NAME_LABEL, "taskName");
        when(request.getDriver()).thenReturn(new DriverSpec());
        when(request.getDriver().getLabels()).thenReturn(driverLabels);
        when(request.getDriver().getVolumeMounts()).thenReturn(List.of("mount1"));
        when(request.getDriver().getServiceAccount()).thenReturn("serviceAccount4");
        when(request.getDriver().getCores()).thenReturn(3);
        when(request.getDriver().getCoreLimit()).thenReturn("3000");
        when(request.getDriver().getMemory()).thenReturn("3072Mi");
        when(request.getVolumes()).thenReturn(List.of("volume3"));
        when(sparkCluster.getDriver()).thenReturn(new DriverSpec());
        when(sparkCluster.getDriver().getVolumeMounts()).thenReturn(List.of("mount5", "mount6"));
        when(sparkCluster.getSparkServiceAccount()).thenReturn("sparkServiceAccount4");
        // When
        DriverSpec result = ApplicationSubmissionHelper.getDriverSpec(request, appConfig, parentQueue, sparkCluster);
        // Then
        assertNotNull(result);
        assertEquals("serviceAccount4", result.getServiceAccount());
        assertEquals(3, result.getCores());
        assertEquals("3000", result.getCoreLimit());
        assertEquals("3072Mi", result.getMemory());
        assertEquals(driverLabels, result.getLabels());
        assertEquals(List.of("mount1"), result.getVolumeMounts());
    }

    @Test
    void testGetDriverSpecWithZeroCoresAndMemory() {
        // Given
        Map<String, String> driverLabels = new HashMap<>();
        driverLabels.put(Constants.DAG_NAME_LABEL, "dagName");
        when(request.getDriver()).thenReturn(new DriverSpec());
        when(request.getDriver().getLabels()).thenReturn(driverLabels);
        when(request.getDriver().getVolumeMounts()).thenReturn(List.of("mount1"));
        when(request.getDriver().getServiceAccount()).thenReturn("serviceAccount2");
        when(request.getDriver().getCores()).thenReturn(0);
        when(request.getDriver().getCoreLimit()).thenReturn("0");
        when(request.getDriver().getMemory()).thenReturn("0Mi");
        when(request.getVolumes()).thenReturn(List.of("volume1"));
        when(sparkCluster.getDriver()).thenReturn(new DriverSpec());
        when(sparkCluster.getDriver().getVolumeMounts()).thenReturn(List.of("mount7", "mount8"));
        when(sparkCluster.getSparkServiceAccount()).thenReturn("sparkServiceAccount6");
        // When
        DriverSpec result = ApplicationSubmissionHelper.getDriverSpec(request, appConfig, parentQueue, sparkCluster);
        // Then
        assertNotNull(result);
        assertEquals("serviceAccount2", result.getServiceAccount());
        assertEquals(0, result.getCores());
        assertEquals("0", result.getCoreLimit());
        assertEquals("0Mi", result.getMemory());
        assertEquals(driverLabels, result.getLabels());
        assertEquals(List.of("mount1"), result.getVolumeMounts());
    }

    @Test
    void testGetDriverSpecWithEmptyServiceAccountAndNoCoreLimit() {
        // Given
        Map<String, String> driverLabels = new HashMap<>();
        driverLabels.put(Constants.TASK_NAME_LABEL, "taskName");
        when(request.getDriver()).thenReturn(new DriverSpec());
        when(request.getDriver().getLabels()).thenReturn(driverLabels);
        when(request.getDriver().getVolumeMounts()).thenReturn(List.of());
        when(request.getDriver().getServiceAccount()).thenReturn("");
        when(request.getDriver().getCores()).thenReturn(2);
        when(request.getDriver().getCoreLimit()).thenReturn(null);
        when(request.getDriver().getMemory()).thenReturn("2048Mi");
        when(request.getVolumes()).thenReturn(null);
        when(sparkCluster.getDriver()).thenReturn(new DriverSpec());
        when(sparkCluster.getDriver().getVolumeMounts()).thenReturn(List.of("mount9", "mount10"));
        when(sparkCluster.getSparkServiceAccount()).thenReturn("sparkServiceAccount7");
        // When
        DriverSpec result = ApplicationSubmissionHelper.getDriverSpec(request, appConfig, parentQueue, sparkCluster);
        // Then
        assertNotNull(result);
        assertEquals("sparkServiceAccount7", result.getServiceAccount());
        assertEquals(2, result.getCores());
        assertNotNull(result.getCoreLimit());
        assertEquals("2048Mi", result.getMemory());
        assertEquals(driverLabels, result.getLabels());
        assertEquals(List.of(), result.getVolumeMounts());
    }

    @Test
    void testGetDriverSpecWithSpecifiedServiceAccountAndVolumes() {
        // Given
        when(request.getDriver()).thenReturn(new DriverSpec());
        when(request.getDriver().getLabels()).thenReturn(null);
        when(request.getDriver().getVolumeMounts()).thenReturn(List.of("mount1", "mount2"));
        when(request.getDriver().getServiceAccount()).thenReturn("serviceAccount3");
        when(request.getDriver().getCores()).thenReturn(3);
        when(request.getDriver().getCoreLimit()).thenReturn("3000");
        when(request.getDriver().getMemory()).thenReturn("3072Mi");
        when(request.getVolumes()).thenReturn(List.of("volume2"));
        when(sparkCluster.getDriver()).thenReturn(new DriverSpec());
        when(sparkCluster.getDriver().getVolumeMounts()).thenReturn(List.of("mount11", "mount12"));
        when(sparkCluster.getSparkServiceAccount()).thenReturn("sparkServiceAccount8");
        // When
        DriverSpec result = ApplicationSubmissionHelper.getDriverSpec(request, appConfig, parentQueue, sparkCluster);
        // Then
        assertNotNull(result);
        assertEquals("serviceAccount3", result.getServiceAccount());
        assertEquals(3, result.getCores());
        assertEquals("3000", result.getCoreLimit());
        assertEquals("3072Mi", result.getMemory());
        assertEquals(List.of("mount1", "mount2"), result.getVolumeMounts());
    }
}
