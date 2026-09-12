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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.apple.spark.operator.Volume;
import com.apple.spark.operator.VolumeMount;
import com.apple.spark.operator.HostPathVolumeSource;

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
        // Mock AppConfig
        appConfig = mock(AppConfig.class);
        sparkCluster = mock(SparkCluster.class);
        parentQueue = "dummyQueue";
    }

    @Test
    void testGetDriverSpec() {
        // Given
        when(request.getDriver()).thenReturn(null);
        when(sparkCluster.getSparkServiceAccount()).thenReturn("defaultAccount");
        // When
        DriverSpec result = ApplicationSubmissionHelper.getDriverSpec(request, appConfig, parentQueue, sparkCluster);
        // Then
        assertNotNull(result);
        assertEquals("defaultAccount", result.getServiceAccount());
    }

    @Test
    void testGetDriverSpecWithDriverLabelsAndVolumes() {
        // Given
        Map<String, String> driverLabels = new HashMap<>();
        driverLabels.put(Constants.DAG_NAME_LABEL, "dagName");
        driverLabels.put(Constants.TASK_NAME_LABEL, "taskName");
        // Mock DriverSpec
        DriverSpec driverSpec = mock(DriverSpec.class);
        when(driverSpec.getLabels()).thenReturn(driverLabels);
        when(driverSpec.getVolumeMounts()).thenReturn(List.of(new VolumeMount("mount1", "path1"), new VolumeMount("mount2", "path2")));
        when(driverSpec.getServiceAccount()).thenReturn("serviceAccount1");
        when(driverSpec.getCores()).thenReturn(4);
        when(driverSpec.getCoreLimit()).thenReturn("4000");
        when(driverSpec.getMemory()).thenReturn("4096Mi");
        when(request.getDriver()).thenReturn(driverSpec);
        when(request.getVolumes()).thenReturn(List.of(new Volume("volume1", new HostPathVolumeSource())));
        DriverSpec sparkClusterDriverSpec = mock(DriverSpec.class);
        when(sparkCluster.getDriver()).thenReturn(sparkClusterDriverSpec);
        when(sparkClusterDriverSpec.getVolumeMounts()).thenReturn(List.of(new VolumeMount("mount3", "path3"), new VolumeMount("mount4", "path4")));
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
        assertEquals(List.of(new VolumeMount("mount1", "path1"), new VolumeMount("mount2", "path2")), result.getVolumeMounts());
    }

    @Test
    void testGetDriverSpecWithNoDriverAndNoVolumes() {
        // Given
        when(request.getDriver()).thenReturn(null);
        when(request.getVolumes()).thenReturn(null);
        DriverSpec sparkClusterDriverSpec = mock(DriverSpec.class);
        when(sparkCluster.getDriver()).thenReturn(sparkClusterDriverSpec);
        when(sparkClusterDriverSpec.getVolumeMounts()).thenReturn(List.of(new VolumeMount("mount3", "path3"), new VolumeMount("mount4", "path4")));
        when(sparkCluster.getSparkServiceAccount()).thenReturn("sparkServiceAccount1");
        // When
        DriverSpec result = ApplicationSubmissionHelper.getDriverSpec(request, appConfig, parentQueue, sparkCluster);
        // Then
        assertNotNull(result);
        assertEquals("sparkServiceAccount1", result.getServiceAccount());
        assertEquals(List.of(new VolumeMount("mount3", "path3"), new VolumeMount("mount4", "path4")), result.getVolumeMounts());
    }

    @Test
    void testGetDriverSpecWithMissingServiceAccountAndCoreLimit() {
        // Given
        Map<String, String> driverLabels = new HashMap<>();
        driverLabels.put(Constants.DAG_NAME_LABEL, "dagName");
        DriverSpec driverSpec = mock(DriverSpec.class);
        when(driverSpec.getLabels()).thenReturn(driverLabels);
        when(driverSpec.getVolumeMounts()).thenReturn(List.of(new VolumeMount("mount1", "path1")));
        when(driverSpec.getServiceAccount()).thenReturn(null);
        when(driverSpec.getCores()).thenReturn(2);
        when(driverSpec.getCoreLimit()).thenReturn(null);
        when(driverSpec.getMemory()).thenReturn("2048Mi");
        when(request.getDriver()).thenReturn(driverSpec);
        when(request.getVolumes()).thenReturn(List.of(new Volume("volume1", new HostPathVolumeSource()), new Volume("volume2", new HostPathVolumeSource())));
        DriverSpec sparkClusterDriverSpec = mock(DriverSpec.class);
        when(sparkCluster.getDriver()).thenReturn(sparkClusterDriverSpec);
        when(sparkClusterDriverSpec.getVolumeMounts()).thenReturn(List.of(new VolumeMount("mount3", "path3"), new VolumeMount("mount4", "path4")));
        when(sparkCluster.getSparkServiceAccount()).thenReturn("sparkServiceAccount2");
        // When
        DriverSpec result = ApplicationSubmissionHelper.getDriverSpec(request, appConfig, parentQueue, sparkCluster);
        // Then
        assertNotNull(result);
        assertEquals("sparkServiceAccount2", result.getServiceAccount());
        assertEquals(2, result.getCores());
        assertEquals("2048Mi", result.getMemory());
        assertEquals(driverLabels, result.getLabels());
        assertEquals(List.of(new VolumeMount("mount1", "path1")), result.getVolumeMounts());
    }

    @Test
    void testGetDriverSpecWithEmptyServiceAccountAndVolumes() {
        // Given
        DriverSpec driverSpec = mock(DriverSpec.class);
        when(driverSpec.getLabels()).thenReturn(null);
        when(driverSpec.getVolumeMounts()).thenReturn(List.of());
        when(driverSpec.getServiceAccount()).thenReturn("");
        when(driverSpec.getCores()).thenReturn(1);
        when(driverSpec.getCoreLimit()).thenReturn("1000m");
        when(driverSpec.getMemory()).thenReturn("1024Mi");
        when(request.getDriver()).thenReturn(driverSpec);
        when(request.getVolumes()).thenReturn(null);
        DriverSpec sparkClusterDriverSpec = mock(DriverSpec.class);
        when(sparkCluster.getDriver()).thenReturn(sparkClusterDriverSpec);
        when(sparkClusterDriverSpec.getVolumeMounts()).thenReturn(List.of(new VolumeMount("mount3", "path3"), new VolumeMount("mount4", "path4")));
        when(sparkCluster.getSparkServiceAccount()).thenReturn("sparkServiceAccount3");
        // When
        DriverSpec result = ApplicationSubmissionHelper.getDriverSpec(request, appConfig, parentQueue, sparkCluster);
        // Then
        assertNotNull(result);
        assertEquals("sparkServiceAccount3", result.getServiceAccount());
        assertEquals(1, result.getCores());
        assertEquals("1000m", result.getCoreLimit());
        assertEquals("1024Mi", result.getMemory());
        assertEquals(List.of(), result.getVolumeMounts());
    }

    @Test
    void testGetDriverSpecWithNormalizedLabelsAndAdjustedResources() {
        // Given
        Map<String, String> driverLabels = new HashMap<>();
        driverLabels.put(Constants.TASK_NAME_LABEL, "taskName");
        DriverSpec driverSpec = mock(DriverSpec.class);
        when(driverSpec.getLabels()).thenReturn(driverLabels);
        when(driverSpec.getVolumeMounts()).thenReturn(List.of(new VolumeMount("mount1", "path1")));
        when(driverSpec.getServiceAccount()).thenReturn("serviceAccount4");
        when(driverSpec.getCores()).thenReturn(3);
        when(driverSpec.getCoreLimit()).thenReturn("3000");
        when(driverSpec.getMemory()).thenReturn("3072Mi");
        when(request.getDriver()).thenReturn(driverSpec);
        when(request.getVolumes()).thenReturn(List.of(new Volume("volume3", new HostPathVolumeSource())));
        DriverSpec sparkClusterDriverSpec = mock(DriverSpec.class);
        when(sparkCluster.getDriver()).thenReturn(sparkClusterDriverSpec);
        when(sparkClusterDriverSpec.getVolumeMounts()).thenReturn(List.of(new VolumeMount("mount5", "path5"), new VolumeMount("mount6", "path6")));
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
        assertEquals(List.of(new VolumeMount("mount1", "path1")), result.getVolumeMounts());
    }

    @Test
    void testGetDriverSpecWithZeroCoresAndMemory() {
        // Given
        Map<String, String> driverLabels = new HashMap<>();
        driverLabels.put(Constants.DAG_NAME_LABEL, "dagName");
        DriverSpec driverSpec = mock(DriverSpec.class);
        when(driverSpec.getLabels()).thenReturn(driverLabels);
        when(driverSpec.getVolumeMounts()).thenReturn(List.of(new VolumeMount("mount1", "path1")));
        when(driverSpec.getServiceAccount()).thenReturn("serviceAccount2");
        when(driverSpec.getCores()).thenReturn(0);
        when(driverSpec.getCoreLimit()).thenReturn("0");
        when(driverSpec.getMemory()).thenReturn("0Mi");
        when(request.getDriver()).thenReturn(driverSpec);
        when(request.getVolumes()).thenReturn(List.of(new Volume("volume1", new HostPathVolumeSource())));
        DriverSpec sparkClusterDriverSpec = mock(DriverSpec.class);
        when(sparkCluster.getDriver()).thenReturn(sparkClusterDriverSpec);
        when(sparkClusterDriverSpec.getVolumeMounts()).thenReturn(List.of(new VolumeMount("mount7", "path7"), new VolumeMount("mount8", "path8")));
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
        assertEquals(List.of(new VolumeMount("mount1", "path1")), result.getVolumeMounts());
    }

    @Test
    void testGetDriverSpecWithEmptyServiceAccountAndNoCoreLimit() {
        // Given
        Map<String, String> driverLabels = new HashMap<>();
        driverLabels.put(Constants.TASK_NAME_LABEL, "taskName");
        DriverSpec driverSpec = mock(DriverSpec.class);
        when(driverSpec.getLabels()).thenReturn(driverLabels);
        when(driverSpec.getVolumeMounts()).thenReturn(List.of());
        when(driverSpec.getServiceAccount()).thenReturn("");
        when(driverSpec.getCores()).thenReturn(2);
        when(driverSpec.getCoreLimit()).thenReturn(null);
        when(driverSpec.getMemory()).thenReturn("2048Mi");
        when(request.getDriver()).thenReturn(driverSpec);
        when(request.getVolumes()).thenReturn(null);
        DriverSpec sparkClusterDriverSpec = mock(DriverSpec.class);
        when(sparkCluster.getDriver()).thenReturn(sparkClusterDriverSpec);
        when(sparkClusterDriverSpec.getVolumeMounts()).thenReturn(List.of(new VolumeMount("mount9", "path9"), new VolumeMount("mount10", "path10")));
        when(sparkCluster.getSparkServiceAccount()).thenReturn("sparkServiceAccount7");
        // When
        DriverSpec result = ApplicationSubmissionHelper.getDriverSpec(request, appConfig, parentQueue, sparkCluster);
        // Then
        assertNotNull(result);
        assertEquals("sparkServiceAccount7", result.getServiceAccount());
        assertEquals(2, result.getCores());
        assertEquals("2048Mi", result.getMemory());
        assertEquals(driverLabels, result.getLabels());
        assertEquals(List.of(), result.getVolumeMounts());
    }

    @Test
    void testGetDriverSpecWithSpecifiedServiceAccountAndVolumes() {
        // Given
        DriverSpec driverSpec = mock(DriverSpec.class);
        when(driverSpec.getLabels()).thenReturn(null);
        when(driverSpec.getVolumeMounts()).thenReturn(List.of(new VolumeMount("mount1", "path1"), new VolumeMount("mount2", "path2")));
        when(driverSpec.getServiceAccount()).thenReturn("serviceAccount3");
        when(driverSpec.getCores()).thenReturn(3);
        when(driverSpec.getCoreLimit()).thenReturn("3000");
        when(driverSpec.getMemory()).thenReturn("3072Mi");
        when(request.getDriver()).thenReturn(driverSpec);
        when(request.getVolumes()).thenReturn(List.of(new Volume("volume2", new HostPathVolumeSource())));
        DriverSpec sparkClusterDriverSpec = mock(DriverSpec.class);
        when(sparkCluster.getDriver()).thenReturn(sparkClusterDriverSpec);
        when(sparkClusterDriverSpec.getVolumeMounts()).thenReturn(List.of(new VolumeMount("mount11", "path11"), new VolumeMount("mount12", "path12")));
        when(sparkCluster.getSparkServiceAccount()).thenReturn("sparkServiceAccount8");
        // When
        DriverSpec result = ApplicationSubmissionHelper.getDriverSpec(request, appConfig, parentQueue, sparkCluster);
        // Then
        assertNotNull(result);
        assertEquals("serviceAccount3", result.getServiceAccount());
        assertEquals(3, result.getCores());
        assertEquals("3000", result.getCoreLimit());
        assertEquals("3072Mi", result.getMemory());
        assertEquals(List.of(new VolumeMount("mount1", "path1"), new VolumeMount("mount2", "path2")), result.getVolumeMounts());
    }
}
