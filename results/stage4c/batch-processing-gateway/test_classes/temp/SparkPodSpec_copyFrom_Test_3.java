package com.apple.spark.operator;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

class SparkPodSpec_copyFrom_Test {

    private SparkPodSpec originalSpec;

    private SparkPodSpec anotherSpec;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the SparkPodSpec objects before each test
        originalSpec = new SparkPodSpec();
        anotherSpec = new SparkPodSpec();
        // Setup anotherSpec with some initial values for testing
        anotherSpec.setCores(4);
        anotherSpec.setCoreLimit("2000m");
        anotherSpec.setMemory("4Gi");
        anotherSpec.setImage("spark-image:v1");
        anotherSpec.setEnv(Collections.singletonList(new EnvVar("ENV_VAR", "value", null)));
        anotherSpec.setLabels(Collections.singletonMap("labelKey", "labelValue"));
        anotherSpec.setAnnotations(Collections.singletonMap("annotationKey", "annotationValue"));
        anotherSpec.setTerminationGracePeriodSeconds(30L);
        anotherSpec.setServiceAccount("spark-service-account");
        anotherSpec.setVolumeMounts(Collections.singletonList(new VolumeMount("/path", "mountName")));
        anotherSpec.setSecurityContext(new SecurityContext());
        anotherSpec.setAffinity(new Affinity());
        anotherSpec.setCoreRequest("1000m");
        anotherSpec.setJavaOptions("-Xmx2G");
    }

    @AfterEach
    void teardownAfterEach() {
        // Clean up resources specific to each test
        originalSpec = null;
        anotherSpec = null;
    }

    @Test
    void testCopyFrom() {
        // Given
        // anotherSpec is already initialized in setupBeforeEach
        // When
        originalSpec.copyFrom(anotherSpec);
        // Then
        assertEquals(anotherSpec.getCores(), originalSpec.getCores());
        assertEquals(anotherSpec.getCoreLimit(), originalSpec.getCoreLimit());
        assertEquals(anotherSpec.getMemory(), originalSpec.getMemory());
        assertEquals(anotherSpec.getImage(), originalSpec.getImage());
        assertEquals(anotherSpec.getEnv(), originalSpec.getEnv());
        assertEquals(anotherSpec.getLabels(), originalSpec.getLabels());
        assertEquals(anotherSpec.getAnnotations(), originalSpec.getAnnotations());
        assertEquals(anotherSpec.getTerminationGracePeriodSeconds(), originalSpec.getTerminationGracePeriodSeconds());
        assertEquals(anotherSpec.getServiceAccount(), originalSpec.getServiceAccount());
        assertEquals(anotherSpec.getVolumeMounts(), originalSpec.getVolumeMounts());
        assertEquals(anotherSpec.getSecurityContext(), originalSpec.getSecurityContext());
        assertEquals(anotherSpec.getAffinity(), originalSpec.getAffinity());
        assertEquals(anotherSpec.getCoreRequest(), originalSpec.getCoreRequest());
        assertEquals(anotherSpec.getJavaOptions(), originalSpec.getJavaOptions());
    }

    @Test
    void testCopyFromFullyPopulated() {
        // Given
        anotherSpec.setMemoryOverhead("500Mi");
        anotherSpec.setEnv(Collections.singletonList(new EnvVar("ENV_VAR", "value", null)));
        anotherSpec.setLabels(Collections.singletonMap("app", "spark"));
        anotherSpec.setAnnotations(Collections.singletonMap("annotation1", "value1"));
        anotherSpec.setTerminationGracePeriodSeconds(30L);
        anotherSpec.setServiceAccount("spark-sa");
        anotherSpec.setVolumeMounts(Collections.singletonList(new VolumeMount("/data", "volume1")));
        anotherSpec.setSecurityContext(new SecurityContext());
        anotherSpec.setAffinity(new Affinity());
        anotherSpec.setCoreRequest("1000m");
        anotherSpec.setJavaOptions("-Xmx2g");
        // When
        originalSpec.copyFrom(anotherSpec);
        // Then
        assertEquals(anotherSpec.getCores(), originalSpec.getCores());
        assertEquals(anotherSpec.getCoreLimit(), originalSpec.getCoreLimit());
        assertEquals(anotherSpec.getMemory(), originalSpec.getMemory());
        assertEquals(anotherSpec.getMemoryOverhead(), originalSpec.getMemoryOverhead());
        assertEquals(anotherSpec.getImage(), originalSpec.getImage());
        assertEquals(anotherSpec.getEnv(), originalSpec.getEnv());
        assertEquals(anotherSpec.getLabels(), originalSpec.getLabels());
        assertEquals(anotherSpec.getAnnotations(), originalSpec.getAnnotations());
        assertEquals(anotherSpec.getTerminationGracePeriodSeconds(), originalSpec.getTerminationGracePeriodSeconds());
        assertEquals(anotherSpec.getServiceAccount(), originalSpec.getServiceAccount());
        assertEquals(anotherSpec.getVolumeMounts(), originalSpec.getVolumeMounts());
        assertEquals(anotherSpec.getSecurityContext(), originalSpec.getSecurityContext());
        assertEquals(anotherSpec.getAffinity(), originalSpec.getAffinity());
        assertEquals(anotherSpec.getCoreRequest(), originalSpec.getCoreRequest());
        assertEquals(anotherSpec.getJavaOptions(), originalSpec.getJavaOptions());
    }

    @Test
    void testCopyFromAllFieldsNull() {
        // Given
        // Reset anotherSpec to have all fields as null
        anotherSpec = new SparkPodSpec();
        // When
        originalSpec.copyFrom(anotherSpec);
        // Then
        assertNull(originalSpec.getCores());
        assertNull(originalSpec.getCoreLimit());
        assertNull(originalSpec.getMemory());
        assertNull(originalSpec.getMemoryOverhead());
        assertNull(originalSpec.getImage());
        assertNull(originalSpec.getEnv());
        assertNull(originalSpec.getLabels());
        assertNull(originalSpec.getAnnotations());
        assertNull(originalSpec.getTerminationGracePeriodSeconds());
        assertNull(originalSpec.getServiceAccount());
        assertNull(originalSpec.getVolumeMounts());
        assertNull(originalSpec.getSecurityContext());
        assertNull(originalSpec.getAffinity());
        assertNull(originalSpec.getCoreRequest());
        assertNull(originalSpec.getJavaOptions());
    }

    @Test
    void testCopyFromPartialFieldsPopulated() {
        // Given
        anotherSpec.setCores(4);
        anotherSpec.setMemory("4Gi");
        anotherSpec.setImage("spark:latest");
        anotherSpec.setEnv(new ArrayList<>());
        anotherSpec.setLabels(new HashMap<>());
        anotherSpec.setAnnotations(new HashMap<>());
        // Ensure coreLimit is null
        anotherSpec.setCoreLimit(null);
        // Set this to null
        anotherSpec.setTerminationGracePeriodSeconds(null);
        // When
        originalSpec.copyFrom(anotherSpec);
        // Then
        assertEquals(anotherSpec.getCores(), originalSpec.getCores());
        assertNull(originalSpec.getCoreLimit());
        assertEquals(anotherSpec.getMemory(), originalSpec.getMemory());
        assertNull(originalSpec.getMemoryOverhead());
        assertEquals(anotherSpec.getImage(), originalSpec.getImage());
        assertEquals(anotherSpec.getEnv(), originalSpec.getEnv());
        assertEquals(anotherSpec.getLabels(), originalSpec.getLabels());
        assertEquals(anotherSpec.getAnnotations(), originalSpec.getAnnotations());
        assertNull(originalSpec.getTerminationGracePeriodSeconds());
        assertNull(originalSpec.getServiceAccount());
        assertNull(originalSpec.getVolumeMounts());
        assertNull(originalSpec.getSecurityContext());
        assertNull(originalSpec.getAffinity());
        assertNull(originalSpec.getCoreRequest());
        assertNull(originalSpec.getJavaOptions());
    }

    @Test
    void testCopyFromFullyPopulatedDifferentValues() {
        // Given
        anotherSpec.setCores(8);
        anotherSpec.setCoreLimit("3000m");
        anotherSpec.setMemory("8Gi");
        anotherSpec.setMemoryOverhead("1Gi");
        anotherSpec.setImage("spark:v2");
        anotherSpec.setEnv(Collections.singletonList(new EnvVar("ENV_VAR_NEW", "new_value", null)));
        anotherSpec.setLabels(Collections.singletonMap("app", "spark-new"));
        anotherSpec.setAnnotations(Collections.singletonMap("annotation2", "value2"));
        anotherSpec.setTerminationGracePeriodSeconds(60L);
        anotherSpec.setServiceAccount("spark-sa-new");
        anotherSpec.setVolumeMounts(Collections.singletonList(new VolumeMount("/newdata", "volume2")));
        anotherSpec.setSecurityContext(new SecurityContext());
        anotherSpec.setAffinity(new Affinity());
        anotherSpec.setCoreRequest("2000m");
        anotherSpec.setJavaOptions("-Xmx4g");
        // When
        originalSpec.copyFrom(anotherSpec);
        // Then
        assertEquals(anotherSpec.getCores(), originalSpec.getCores());
        assertEquals(anotherSpec.getCoreLimit(), originalSpec.getCoreLimit());
        assertEquals(anotherSpec.getMemory(), originalSpec.getMemory());
        assertEquals(anotherSpec.getMemoryOverhead(), originalSpec.getMemoryOverhead());
        assertEquals(anotherSpec.getImage(), originalSpec.getImage());
        assertEquals(anotherSpec.getEnv(), originalSpec.getEnv());
        assertEquals(anotherSpec.getLabels(), originalSpec.getLabels());
        assertEquals(anotherSpec.getAnnotations(), originalSpec.getAnnotations());
        assertEquals(anotherSpec.getTerminationGracePeriodSeconds(), originalSpec.getTerminationGracePeriodSeconds());
        assertEquals(anotherSpec.getServiceAccount(), originalSpec.getServiceAccount());
        assertEquals(anotherSpec.getVolumeMounts(), originalSpec.getVolumeMounts());
        assertEquals(anotherSpec.getSecurityContext(), originalSpec.getSecurityContext());
        assertEquals(anotherSpec.getAffinity(), originalSpec.getAffinity());
        assertEquals(anotherSpec.getCoreRequest(), originalSpec.getCoreRequest());
        assertEquals(anotherSpec.getJavaOptions(), originalSpec.getJavaOptions());
    }

    @Test
    void testCopyFromZeroValues() {
        // Given
        anotherSpec.setCores(0);
        anotherSpec.setCoreLimit("0m");
        anotherSpec.setMemory("0Gi");
        anotherSpec.setMemoryOverhead("0Mi");
        anotherSpec.setImage("");
        anotherSpec.setEnv(new ArrayList<>());
        anotherSpec.setLabels(new HashMap<>());
        anotherSpec.setAnnotations(new HashMap<>());
        anotherSpec.setTerminationGracePeriodSeconds(0L);
        anotherSpec.setServiceAccount("");
        anotherSpec.setVolumeMounts(new ArrayList<>());
        anotherSpec.setSecurityContext(null);
        anotherSpec.setAffinity(null);
        anotherSpec.setCoreRequest("0m");
        anotherSpec.setJavaOptions("");
        // When
        originalSpec.copyFrom(anotherSpec);
        // Then
        assertEquals(anotherSpec.getCores(), originalSpec.getCores());
        assertEquals(anotherSpec.getCoreLimit(), originalSpec.getCoreLimit());
        assertEquals(anotherSpec.getMemory(), originalSpec.getMemory());
        assertEquals(anotherSpec.getMemoryOverhead(), originalSpec.getMemoryOverhead());
        assertEquals(anotherSpec.getImage(), originalSpec.getImage());
        assertEquals(anotherSpec.getEnv(), originalSpec.getEnv());
        assertEquals(anotherSpec.getLabels(), originalSpec.getLabels());
        assertEquals(anotherSpec.getAnnotations(), originalSpec.getAnnotations());
        assertEquals(anotherSpec.getTerminationGracePeriodSeconds(), originalSpec.getTerminationGracePeriodSeconds());
        assertEquals(anotherSpec.getServiceAccount(), originalSpec.getServiceAccount());
        assertEquals(anotherSpec.getVolumeMounts(), originalSpec.getVolumeMounts());
        assertNull(originalSpec.getSecurityContext());
        assertNull(originalSpec.getAffinity());
        assertEquals(anotherSpec.getCoreRequest(), originalSpec.getCoreRequest());
        assertEquals(anotherSpec.getJavaOptions(), originalSpec.getJavaOptions());
    }

    @Test
    void testCopyFromMixedFields() {
        // Given
        anotherSpec.setCores(2);
        anotherSpec.setCoreLimit("1500m");
        anotherSpec.setMemory("2Gi");
        anotherSpec.setMemoryOverhead("300Mi");
        anotherSpec.setImage("spark:v1");
        anotherSpec.setEnv(null);
        anotherSpec.setLabels(null);
        anotherSpec.setAnnotations(null);
        anotherSpec.setTerminationGracePeriodSeconds(45L);
        anotherSpec.setServiceAccount(null);
        anotherSpec.setVolumeMounts(null);
        anotherSpec.setSecurityContext(null);
        anotherSpec.setAffinity(null);
        anotherSpec.setCoreRequest("500m");
        anotherSpec.setJavaOptions(null);
        // When
        originalSpec.copyFrom(anotherSpec);
        // Then
        assertEquals(anotherSpec.getCores(), originalSpec.getCores());
        assertEquals(anotherSpec.getCoreLimit(), originalSpec.getCoreLimit());
        assertEquals(anotherSpec.getMemory(), originalSpec.getMemory());
        assertEquals(anotherSpec.getMemoryOverhead(), originalSpec.getMemoryOverhead());
        assertEquals(anotherSpec.getImage(), originalSpec.getImage());
        assertNull(originalSpec.getEnv());
        assertNull(originalSpec.getLabels());
        assertNull(originalSpec.getAnnotations());
        assertEquals(anotherSpec.getTerminationGracePeriodSeconds(), originalSpec.getTerminationGracePeriodSeconds());
        assertNull(originalSpec.getServiceAccount());
        assertNull(originalSpec.getVolumeMounts());
        assertNull(originalSpec.getSecurityContext());
        assertNull(originalSpec.getAffinity());
        assertEquals(anotherSpec.getCoreRequest(), originalSpec.getCoreRequest());
        assertNull(originalSpec.getJavaOptions());
    }

    @Test
    void testCopyFromComplexStructures() {
        // Given
        anotherSpec.setCores(16);
        anotherSpec.setCoreLimit("4000m");
        anotherSpec.setMemory("16Gi");
        anotherSpec.setMemoryOverhead("2Gi");
        anotherSpec.setImage("spark:latest");
        anotherSpec.setEnv(Arrays.asList(new EnvVar("ENV_VAR", "value", null), new EnvVar("ENV_VAR_EXTRA", "extra_value", null)));
        anotherSpec.setLabels(new HashMap<String, String>() {

            {
                put("app", "spark");
                put("version", "v2");
            }
        });
        anotherSpec.setAnnotations(new HashMap<String, String>() {

            {
                put("annotation1", "value1");
                put("annotation3", "value3");
            }
        });
        anotherSpec.setTerminationGracePeriodSeconds(90L);
        anotherSpec.setServiceAccount("spark-sa-extra");
        anotherSpec.setVolumeMounts(Arrays.asList(new VolumeMount("/data", "volume1"), new VolumeMount("/extra", "volume3")));
        anotherSpec.setSecurityContext(new SecurityContext());
        anotherSpec.setAffinity(new Affinity());
        anotherSpec.setCoreRequest("3000m");
        anotherSpec.setJavaOptions("-Xmx8g");
        // When
        originalSpec.copyFrom(anotherSpec);
        // Then
        assertEquals(anotherSpec.getCores(), originalSpec.getCores());
        assertEquals(anotherSpec.getCoreLimit(), originalSpec.getCoreLimit());
        assertEquals(anotherSpec.getMemory(), originalSpec.getMemory());
        assertEquals(anotherSpec.getMemoryOverhead(), originalSpec.getMemoryOverhead());
        assertEquals(anotherSpec.getImage(), originalSpec.getImage());
        assertEquals(anotherSpec.getEnv(), originalSpec.getEnv());
        assertEquals(anotherSpec.getLabels(), originalSpec.getLabels());
        assertEquals(anotherSpec.getAnnotations(), originalSpec.getAnnotations());
        assertEquals(anotherSpec.getTerminationGracePeriodSeconds(), originalSpec.getTerminationGracePeriodSeconds());
        assertEquals(anotherSpec.getServiceAccount(), originalSpec.getServiceAccount());
        assertEquals(anotherSpec.getVolumeMounts(), originalSpec.getVolumeMounts());
        assertEquals(anotherSpec.getSecurityContext(), originalSpec.getSecurityContext());
        assertEquals(anotherSpec.getAffinity(), originalSpec.getAffinity());
        assertEquals(anotherSpec.getCoreRequest(), originalSpec.getCoreRequest());
        assertEquals(anotherSpec.getJavaOptions(), originalSpec.getJavaOptions());
    }

    @Test
    void testCopyFromNull() {
        // Given
        anotherSpec = null;
        // When & Then
        assertThrows(NullPointerException.class, () -> {
            originalSpec.copyFrom(anotherSpec);
        });
    }
}
