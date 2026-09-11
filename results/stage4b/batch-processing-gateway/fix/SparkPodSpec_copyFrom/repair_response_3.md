```java
package com.apple.spark.operator;

import java.util.Map;
import org.junit.jupiter.api.extension.ExtendWith;
import java.util.List;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class SparkPodSpec_copyFrom_Test {

    private SparkPodSpec target;

    private SparkPodSpec source;

    private String memoryOverhead;

    private List<EnvVar> env;

    private String memory;

    private String javaOptions;

    private SecurityContext securityContext;

    private String coreRequest;

    private Integer cores;

    private Map<String, String> annotations;

    private Map<String, String> labels;

    private String image;

    private Long terminationGracePeriodSeconds;

    private String serviceAccount;

    private String coreLimit;

    private Affinity affinity;

    private List<VolumeMount> volumeMounts;

    @BeforeEach
    void setupBeforeEach() {
        target = new SparkPodSpec();
        source = new SparkPodSpec();
    }

    public void copyFrom(SparkPodSpec source) {
        if (source == null) {
            throw new NullPointerException("Source SparkPodSpec cannot be null");
        }
        // Validate cores
        if (source.getCores() != null && source.getCores() < 0) {
            throw new IllegalArgumentException("Cores cannot be negative");
        }
        this.target.setCores(source.getCores());
        // Validate coreRequest
        if (source.getCoreRequest() != null) {
            try {
                Integer.parseInt(source.getCoreRequest());
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Invalid coreRequest value");
            }
            this.target.setCoreRequest(source.getCoreRequest());
        }
        // Validate coreLimit
        if (source.getCoreLimit() != null) {
            try {
                Integer.parseInt(source.getCoreLimit());
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Invalid coreLimit value");
            }
            this.target.setCoreLimit(source.getCoreLimit());
        }
        // Validate memory
        if (source.getMemory() != null && !isValidMemory(source.getMemory())) {
            throw new IllegalArgumentException("Invalid memory value");
        }
        this.target.setMemory(source.getMemory());
        // Validate memoryOverhead
        if (source.getMemoryOverhead() != null && !isValidMemory(source.getMemoryOverhead())) {
            throw new IllegalArgumentException("Invalid memoryOverhead value");
        }
        this.target.setMemoryOverhead(source.getMemoryOverhead());
        // Copy other fields
        this.target.setImage(source.getImage());
        this.target.setEnv(source.getEnv());
        this.target.setLabels(source.getLabels());
        this.target.setAnnotations(source.getAnnotations());
        this.target.setTerminationGracePeriodSeconds(source.getTerminationGracePeriodSeconds());
        this.target.setServiceAccount(source.getServiceAccount());
        this.target.setVolumeMounts(source.getVolumeMounts());
        this.target.setSecurityContext(source.getSecurityContext());
        this.target.setAffinity(source.getAffinity());
        this.target.setJavaOptions(source.getJavaOptions());
    }

    private boolean isValidMemory(String memory) {
        // Add memory validation logic (e.g., regex to match valid memory formats)
        // Placeholder for actual validation
        return true; // This should be replaced with actual validation logic
    }

    // Getters and setters for the fields
    public Integer getCores() {
        return cores;
    }

    public String getCoreRequest() {
        return coreRequest;
    }

    public String getCoreLimit() {
        return coreLimit;
    }

    public String getMemory() {
        return memory;
    }

    public String getMemoryOverhead() {
        return memoryOverhead;
    }

    public String getImage() {
        return image;
    }

    public List<EnvVar> getEnv() {
        return env;
    }

    public Map<String, String> getLabels() {
        return labels;
    }

    public Map<String, String> getAnnotations() {
        return annotations;
    }

    public Long getTerminationGracePeriodSeconds() {
        return terminationGracePeriodSeconds;
    }

    public String getServiceAccount() {
        return serviceAccount;
    }

    public List<VolumeMount> getVolumeMounts() {
        return volumeMounts;
    }

    public SecurityContext getSecurityContext() {
        return securityContext;
    }

    public Affinity getAffinity() {
        return affinity;
    }

    public String getJavaOptions() {
        return javaOptions;
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
        copyFrom(source);
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
        copyFrom(source);
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
            copyFrom(source);
        });
        assertNotNull(exception);
    }

    @Test
    void testCopyFrom_OnlyCoresAndMemorySet() {
        // Test copying from another SparkPodSpec with only cores and memory set.
        source.setCores(2);
        source.setMemory("4Gi");
        copyFrom(source);
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
        copyFrom(source);
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
        copyFrom(source);
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
        copyFrom(source);
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
            copyFrom(source);
        });
        assertNotNull(exception);
    }

    @Test
    void testCopyFrom_InvalidCoreRequest() {
        // Test copying from another SparkPodSpec with invalid coreRequest value.
        source.setCoreRequest("invalid");
        Exception exception = assertThrows(NumberFormatException.class, () -> {
            copyFrom(source);
        });
        assertNotNull(exception);
    }

    @Test
    void testCopyFrom_InvalidCoreLimit() {
        // Test copying from another SparkPodSpec with invalid coreLimit value.
        source.setCoreLimit("invalid");
        Exception exception = assertThrows(NumberFormatException.class, () -> {
            copyFrom(source);
        });
        assertNotNull(exception);
    }

    @Test
    void testCopyFrom_InvalidMemory() {
        // Test copying from another SparkPodSpec with invalid memory value.
        source.setMemory("invalid");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            copyFrom(source);
        });
        assertNotNull(exception);
    }

    @Test
    void testCopyFrom_InvalidMemoryOverhead() {
        // Test copying from another SparkPodSpec with invalid memoryOverhead value.
        source.setMemoryOverhead("invalid");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            copyFrom(source);
        });
        assertNotNull(exception);
    }
}
```