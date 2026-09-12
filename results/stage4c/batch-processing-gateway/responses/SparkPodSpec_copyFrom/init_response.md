```java
package com.apple.spark.operator;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import io.fabric8.kubernetes.api.model.PodDNSConfig;
import io.fabric8.kubernetes.api.model.PodDNSConfigOption;

class SparkPodSpec_copyFrom_Test {

    private SparkPodSpec originalSpec;
    private SparkPodSpec anotherSpec;

    @BeforeAll
    static void setupBeforeAll() {
        // Initialize resources that are shared across all tests
    }

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
        anotherSpec.setVolumeMounts(Collections.singletonList(new VolumeMount("/path", "mountName", false)));
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

    @AfterAll
    static void teardownAfterAll() {
        // Clean up resources that are shared across all tests
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
}
```