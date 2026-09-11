package com.apple.spark.operator;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;

@ExtendWith(MockitoExtension.class)
class SparkPodSpec_copyFrom_Test {

    private SparkPodSpec target;

    private SparkPodSpec source;

    @BeforeEach
    void setupBeforeEach() {
        target = new SparkPodSpec();
        source = new SparkPodSpec();
    }

    @Test
    void testCopyFrom() {
        // Given: Set up the source SparkPodSpec with some values
        source.setCores(4);
        source.setCoreLimit("2");
        source.setMemory("4Gi");
        source.setImage("my-image:latest");
        source.setEnv(Arrays.asList(new EnvVar("ENV_VAR", "value")));
        source.setLabels(Collections.singletonMap("key", "value"));
        source.setAnnotations(Collections.singletonMap("annotation", "value"));
        // When: Call the copyFrom method
        target.copyFrom(source);
        // Then: Assert that target has the same values as source
        assertEquals(source.getCores(), target.getCores());
        assertEquals(source.getCoreLimit(), target.getCoreLimit());
        assertEquals(source.getMemory(), target.getMemory());
        assertEquals(source.getImage(), target.getImage());
        assertEquals(source.getEnv(), target.getEnv());
        assertEquals(source.getLabels(), target.getLabels());
        assertEquals(source.getAnnotations(), target.getAnnotations());
    }

    @Test
    void testCopyFrom_AllFieldsPopulated() {
        // Test copying from another SparkPodSpec with all fields populated.
        source.setCores(4);
        source.setCoreRequest("2");
        source.setCoreLimit("4");
        source.setMemory("8Gi");
        source.setMemoryOverhead("1Gi");
        source.setImage("spark:latest");
        source.setEnv(new ArrayList<>());
        source.setLabels(new HashMap<>());
        source.setAnnotations(new HashMap<>());
        source.setTerminationGracePeriodSeconds(30L);
        source.setServiceAccount("default");
        source.setVolumeMounts(new ArrayList<>());
        source.setSecurityContext(null);
        source.setAffinity(null);
        source.setJavaOptions("-Dconfig.file=application.conf");
        target.copyFrom(source);
        assertEquals(source.getCores(), target.getCores());
        assertEquals(source.getCoreRequest(), target.getCoreRequest());
        assertEquals(source.getCoreLimit(), target.getCoreLimit());
        assertEquals(source.getMemory(), target.getMemory());
        assertEquals(source.getMemoryOverhead(), target.getMemoryOverhead());
        assertEquals(source.getImage(), target.getImage());
        assertEquals(source.getEnv(), target.getEnv());
        assertEquals(source.getLabels(), target.getLabels());
        assertEquals(source.getAnnotations(), target.getAnnotations());
        assertEquals(source.getTerminationGracePeriodSeconds(), target.getTerminationGracePeriodSeconds());
        assertEquals(source.getServiceAccount(), target.getServiceAccount());
        assertEquals(source.getVolumeMounts(), target.getVolumeMounts());
        assertEquals(source.getSecurityContext(), target.getSecurityContext());
        assertEquals(source.getAffinity(), target.getAffinity());
        assertEquals(source.getJavaOptions(), target.getJavaOptions());
    }

    @Test
    void testCopyFrom_AllFieldsNull() {
        // Test copying from another SparkPodSpec with all fields set to null.
        // Simulate null source
        source = null;
        Exception exception = assertThrows(NullPointerException.class, () -> {
            target.copyFrom(source);
        });
        assertNotNull(exception);
    }

    @Test
    void testCopyFrom_OnlyCoresAndMemorySet() {
        // Test copying from another SparkPodSpec with only cores and memory set.
        source.setCores(2);
        source.setMemory("4Gi");
        target.copyFrom(source);
        assertEquals(source.getCores(), target.getCores());
        assertEquals(source.getMemory(), target.getMemory());
        assertNull(target.getCoreRequest());
        assertNull(target.getCoreLimit());
        assertNull(target.getImage());
        assertNull(target.getEnv());
        assertNull(target.getLabels());
        assertNull(target.getAnnotations());
    }

    @Test
    void testCopyFrom_PartialFieldsSet() {
        // Test copying from another SparkPodSpec with partial fields set.
        source.setCores(3);
        source.setCoreRequest("1");
        source.setCoreLimit("3");
        source.setMemory("2Gi");
        target.copyFrom(source);
        assertEquals(source.getCores(), target.getCores());
        assertEquals(source.getCoreRequest(), target.getCoreRequest());
        assertEquals(source.getCoreLimit(), target.getCoreLimit());
        assertEquals(source.getMemory(), target.getMemory());
    }

    @Test
    void testCopyFrom_SomeFieldsNull() {
        // Test copying from another SparkPodSpec with some fields null and others set.
        source.setCores(5);
        source.setCoreRequest("2");
        source.setCoreLimit("5");
        source.setMemory("16Gi");
        // Image is null
        source.setImage(null);
        target.copyFrom(source);
        assertEquals(source.getCores(), target.getCores());
        assertEquals(source.getCoreRequest(), target.getCoreRequest());
        assertEquals(source.getCoreLimit(), target.getCoreLimit());
        assertEquals(source.getMemory(), target.getMemory());
        assertNull(target.getImage());
    }

    @Test
    void testCopyFrom_MinimalValues() {
        // Test copying from another SparkPodSpec with all fields set to minimal values.
        source.setCores(0);
        source.setMemory("0Gi");
        source.setImage("empty:spark");
        source.setTerminationGracePeriodSeconds(0L);
        source.setServiceAccount("default");
        target.copyFrom(source);
        assertEquals(source.getCores(), target.getCores());
        assertEquals(source.getMemory(), target.getMemory());
        assertEquals(source.getImage(), target.getImage());
        assertEquals(source.getTerminationGracePeriodSeconds(), target.getTerminationGracePeriodSeconds());
        assertEquals(source.getServiceAccount(), target.getServiceAccount());
    }

    @Test
    void testCopyFrom_NegativeCores() {
        // Test copying from another SparkPodSpec with negative cores value.
        source.setCores(-1);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            target.copyFrom(source);
        });
        assertNotNull(exception);
    }

    @Test
    void testCopyFrom_InvalidCoreRequest() {
        // Test copying from another SparkPodSpec with invalid coreRequest value.
        source.setCoreRequest("invalid");
        Exception exception = assertThrows(NumberFormatException.class, () -> {
            target.copyFrom(source);
        });
        assertNotNull(exception);
    }

    @Test
    void testCopyFrom_InvalidCoreLimit() {
        // Test copying from another SparkPodSpec with invalid coreLimit value.
        source.setCoreLimit("invalid");
        Exception exception = assertThrows(NumberFormatException.class, () -> {
            target.copyFrom(source);
        });
        assertNotNull(exception);
    }

    @Test
    void testCopyFrom_InvalidMemory() {
        // Test copying from another SparkPodSpec with invalid memory value.
        source.setMemory("invalid");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            target.copyFrom(source);
        });
        assertNotNull(exception);
    }

    @Test
    void testCopyFrom_InvalidMemoryOverhead() {
        // Test copying from another SparkPodSpec with invalid memoryOverhead value.
        source.setMemoryOverhead("invalid");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            target.copyFrom(source);
        });
        assertNotNull(exception);
    }
}
