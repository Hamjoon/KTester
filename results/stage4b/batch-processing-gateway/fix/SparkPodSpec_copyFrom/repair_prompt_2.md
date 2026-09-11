
You are an experienced Java developer tasked with fixing a piece of code that contains compilation errors. Your task is to resolve these issues after analyzing the code and its compilation feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any syntactical or logical errors.
2. Analyze the compilation feedback to understand the specific errors and their locations within the code.
3. Apply necessary corrections to the code based on the feedback, and output the compelete correct code.
The Java code you need to fix is provided below:
```java
package com.apple.spark.operator;

import java.util.Map;
import org.junit.jupiter.api.extension.ExtendWith;
import java.util.List;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.*;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.ArrayList;

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
        if (source.cores != null && source.cores < 0) {
            throw new IllegalArgumentException("Cores cannot be negative");
        }
        this.cores = source.cores;
        // Validate coreRequest
        if (source.coreRequest != null) {
            try {
                Integer.parseInt(source.coreRequest);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Invalid coreRequest value");
            }
            this.coreRequest = source.coreRequest;
        }
        // Validate coreLimit
        if (source.coreLimit != null) {
            try {
                Integer.parseInt(source.coreLimit);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Invalid coreLimit value");
            }
            this.coreLimit = source.coreLimit;
        }
        // Validate memory
        if (source.memory != null && !isValidMemory(source.memory)) {
            throw new IllegalArgumentException("Invalid memory value");
        }
        this.memory = source.memory;
        // Validate memoryOverhead
        if (source.memoryOverhead != null && !isValidMemory(source.memoryOverhead)) {
            throw new IllegalArgumentException("Invalid memoryOverhead value");
        }
        this.memoryOverhead = source.memoryOverhead;
        // Copy other fields
        this.image = source.image;
        this.env = source.env;
        this.labels = source.labels;
        this.annotations = source.annotations;
        this.terminationGracePeriodSeconds = source.terminationGracePeriodSeconds;
        this.serviceAccount = source.serviceAccount;
        this.volumeMounts = source.volumeMounts;
        this.securityContext = source.securityContext;
        this.affinity = source.affinity;
        this.javaOptions = source.javaOptions;
    }

    private boolean isValidMemory(String memory) {
        // Add memory validation logic (e.g., regex to match valid memory formats)
        // Placeholder for actual validation
        return true;
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
```

Corresponding compilation feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
warning: Supported source version 'RELEASE_6' from annotation processor 'org.sonatype.guice.bean.scanners.index.SisuIndexAPT6' less than -source '17'
warning: Supported source version 'RELEASE_6' from annotation processor 'org.eclipse.sisu.scanners.index.SisuIndexAPT6' less than -source '17'
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:62: error: cores has private access in SparkPodSpec
        if (source.cores != null && source.cores < 0) {
                  ^
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:62: error: cores has private access in SparkPodSpec
        if (source.cores != null && source.cores < 0) {
                                          ^
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:65: error: cores has private access in SparkPodSpec
        this.cores = source.cores;
                           ^
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:67: error: coreRequest has private access in SparkPodSpec
        if (source.coreRequest != null) {
                  ^
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:69: error: coreRequest has private access in SparkPodSpec
                Integer.parseInt(source.coreRequest);
                                       ^
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:73: error: coreRequest has private access in SparkPodSpec
            this.coreRequest = source.coreRequest;
                                     ^
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:76: error: coreLimit has private access in SparkPodSpec
        if (source.coreLimit != null) {
                  ^
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:78: error: coreLimit has private access in SparkPodSpec
                Integer.parseInt(source.coreLimit);
                                       ^
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:82: error: coreLimit has private access in SparkPodSpec
            this.coreLimit = source.coreLimit;
                                   ^
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:85: error: memory has private access in SparkPodSpec
        if (source.memory != null && !isValidMemory(source.memory)) {
                  ^
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:85: error: memory has private access in SparkPodSpec
        if (source.memory != null && !isValidMemory(source.memory)) {
                                                          ^
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:88: error: memory has private access in SparkPodSpec
        this.memory = source.memory;
                            ^
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:90: error: memoryOverhead has private access in SparkPodSpec
        if (source.memoryOverhead != null && !isValidMemory(source.memoryOverhead)) {
                  ^
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:90: error: memoryOverhead has private access in SparkPodSpec
        if (source.memoryOverhead != null && !isValidMemory(source.memoryOverhead)) {
                                                                  ^
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:93: error: memoryOverhead has private access in SparkPodSpec
        this.memoryOverhead = source.memoryOverhead;
                                    ^
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:95: error: image has private access in SparkPodSpec
        this.image = source.image;
                           ^
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:96: error: env has private access in SparkPodSpec
        this.env = source.env;
                         ^
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:97: error: labels has private access in SparkPodSpec
        this.labels = source.labels;
                            ^
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:98: error: annotations has private access in SparkPodSpec
        this.annotations = source.annotations;
                                 ^
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:99: error: terminationGracePeriodSeconds has private access in SparkPodSpec
        this.terminationGracePeriodSeconds = source.terminationGracePeriodSeconds;
                                                   ^
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:100: error: serviceAccount has private access in SparkPodSpec
        this.serviceAccount = source.serviceAccount;
                                    ^
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:101: error: volumeMounts has private access in SparkPodSpec
        this.volumeMounts = source.volumeMounts;
                                  ^
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:102: error: securityContext has private access in SparkPodSpec
        this.securityContext = source.securityContext;
                                     ^
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:103: error: affinity has private access in SparkPodSpec
        this.affinity = source.affinity;
                              ^
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:104: error: javaOptions has private access in SparkPodSpec
        this.javaOptions = source.javaOptions;
                                 ^
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:187: error: cannot find symbol
        assertEquals(source.getCores(), target.getCores());
        ^
  symbol:   method assertEquals(Integer,Integer)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:188: error: cannot find symbol
        assertEquals(source.getCoreLimit(), target.getCoreLimit());
        ^
  symbol:   method assertEquals(String,String)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:189: error: cannot find symbol
        assertEquals(source.getMemory(), target.getMemory());
        ^
  symbol:   method assertEquals(String,String)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:190: error: cannot find symbol
        assertEquals(source.getImage(), target.getImage());
        ^
  symbol:   method assertEquals(String,String)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:191: error: cannot find symbol
        assertEquals(source.getEnv(), target.getEnv());
        ^
  symbol:   method assertEquals(List<EnvVar>,List<EnvVar>)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:192: error: cannot find symbol
        assertEquals(source.getLabels(), target.getLabels());
        ^
  symbol:   method assertEquals(Map<String,String>,Map<String,String>)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:193: error: cannot find symbol
        assertEquals(source.getAnnotations(), target.getAnnotations());
        ^
  symbol:   method assertEquals(Map<String,String>,Map<String,String>)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:215: error: cannot find symbol
        assertEquals(source.getCores(), target.getCores());
        ^
  symbol:   method assertEquals(Integer,Integer)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:216: error: cannot find symbol
        assertEquals(source.getCoreRequest(), target.getCoreRequest());
        ^
  symbol:   method assertEquals(String,String)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:217: error: cannot find symbol
        assertEquals(source.getCoreLimit(), target.getCoreLimit());
        ^
  symbol:   method assertEquals(String,String)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:218: error: cannot find symbol
        assertEquals(source.getMemory(), target.getMemory());
        ^
  symbol:   method assertEquals(String,String)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:219: error: cannot find symbol
        assertEquals(source.getMemoryOverhead(), target.getMemoryOverhead());
        ^
  symbol:   method assertEquals(String,String)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:220: error: cannot find symbol
        assertEquals(source.getImage(), target.getImage());
        ^
  symbol:   method assertEquals(String,String)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:221: error: cannot find symbol
        assertEquals(source.getEnv(), target.getEnv());
        ^
  symbol:   method assertEquals(List<EnvVar>,List<EnvVar>)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:222: error: cannot find symbol
        assertEquals(source.getLabels(), target.getLabels());
        ^
  symbol:   method assertEquals(Map<String,String>,Map<String,String>)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:223: error: cannot find symbol
        assertEquals(source.getAnnotations(), target.getAnnotations());
        ^
  symbol:   method assertEquals(Map<String,String>,Map<String,String>)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:224: error: cannot find symbol
        assertEquals(source.getTerminationGracePeriodSeconds(), target.getTerminationGracePeriodSeconds());
        ^
  symbol:   method assertEquals(Long,Long)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:225: error: cannot find symbol
        assertEquals(source.getServiceAccount(), target.getServiceAccount());
        ^
  symbol:   method assertEquals(String,String)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:226: error: cannot find symbol
        assertEquals(source.getVolumeMounts(), target.getVolumeMounts());
        ^
  symbol:   method assertEquals(List<VolumeMount>,List<VolumeMount>)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:227: error: cannot find symbol
        assertEquals(source.getSecurityContext(), target.getSecurityContext());
        ^
  symbol:   method assertEquals(SecurityContext,SecurityContext)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:228: error: cannot find symbol
        assertEquals(source.getAffinity(), target.getAffinity());
        ^
  symbol:   method assertEquals(Affinity,Affinity)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:229: error: cannot find symbol
        assertEquals(source.getJavaOptions(), target.getJavaOptions());
        ^
  symbol:   method assertEquals(String,String)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:237: error: cannot find symbol
        Exception exception = assertThrows(NullPointerException.class, () -> {
                              ^
  symbol:   method assertThrows(Class<NullPointerException>,()->{ targ[...]e); })
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:240: error: cannot find symbol
        assertNotNull(exception);
        ^
  symbol:   method assertNotNull(Exception)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:249: error: cannot find symbol
        assertEquals(source.getCores(), target.getCores());
        ^
  symbol:   method assertEquals(Integer,Integer)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:250: error: cannot find symbol
        assertEquals(source.getMemory(), target.getMemory());
        ^
  symbol:   method assertEquals(String,String)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:251: error: cannot find symbol
        assertNull(target.getCoreRequest());
        ^
  symbol:   method assertNull(String)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:252: error: cannot find symbol
        assertNull(target.getCoreLimit());
        ^
  symbol:   method assertNull(String)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:253: error: cannot find symbol
        assertNull(target.getImage());
        ^
  symbol:   method assertNull(String)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:254: error: cannot find symbol
        assertNull(target.getEnv());
        ^
  symbol:   method assertNull(List<EnvVar>)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:255: error: cannot find symbol
        assertNull(target.getLabels());
        ^
  symbol:   method assertNull(Map<String,String>)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:256: error: cannot find symbol
        assertNull(target.getAnnotations());
        ^
  symbol:   method assertNull(Map<String,String>)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:267: error: cannot find symbol
        assertEquals(source.getCores(), target.getCores());
        ^
  symbol:   method assertEquals(Integer,Integer)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:268: error: cannot find symbol
        assertEquals(source.getCoreRequest(), target.getCoreRequest());
        ^
  symbol:   method assertEquals(String,String)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:269: error: cannot find symbol
        assertEquals(source.getCoreLimit(), target.getCoreLimit());
        ^
  symbol:   method assertEquals(String,String)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:270: error: cannot find symbol
        assertEquals(source.getMemory(), target.getMemory());
        ^
  symbol:   method assertEquals(String,String)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:283: error: cannot find symbol
        assertEquals(source.getCores(), target.getCores());
        ^
  symbol:   method assertEquals(Integer,Integer)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:284: error: cannot find symbol
        assertEquals(source.getCoreRequest(), target.getCoreRequest());
        ^
  symbol:   method assertEquals(String,String)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:285: error: cannot find symbol
        assertEquals(source.getCoreLimit(), target.getCoreLimit());
        ^
  symbol:   method assertEquals(String,String)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:286: error: cannot find symbol
        assertEquals(source.getMemory(), target.getMemory());
        ^
  symbol:   method assertEquals(String,String)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:287: error: cannot find symbol
        assertNull(target.getImage());
        ^
  symbol:   method assertNull(String)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:299: error: cannot find symbol
        assertEquals(source.getCores(), target.getCores());
        ^
  symbol:   method assertEquals(Integer,Integer)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:300: error: cannot find symbol
        assertEquals(source.getMemory(), target.getMemory());
        ^
  symbol:   method assertEquals(String,String)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:301: error: cannot find symbol
        assertEquals(source.getImage(), target.getImage());
        ^
  symbol:   method assertEquals(String,String)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:302: error: cannot find symbol
        assertEquals(source.getTerminationGracePeriodSeconds(), target.getTerminationGracePeriodSeconds());
        ^
  symbol:   method assertEquals(Long,Long)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:303: error: cannot find symbol
        assertEquals(source.getServiceAccount(), target.getServiceAccount());
        ^
  symbol:   method assertEquals(String,String)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:310: error: cannot find symbol
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                              ^
  symbol:   method assertThrows(Class<IllegalArgumentException>,()->{ targ[...]e); })
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:313: error: cannot find symbol
        assertNotNull(exception);
        ^
  symbol:   method assertNotNull(Exception)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:320: error: cannot find symbol
        Exception exception = assertThrows(NumberFormatException.class, () -> {
                              ^
  symbol:   method assertThrows(Class<NumberFormatException>,()->{ targ[...]e); })
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:323: error: cannot find symbol
        assertNotNull(exception);
        ^
  symbol:   method assertNotNull(Exception)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:330: error: cannot find symbol
        Exception exception = assertThrows(NumberFormatException.class, () -> {
                              ^
  symbol:   method assertThrows(Class<NumberFormatException>,()->{ targ[...]e); })
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:333: error: cannot find symbol
        assertNotNull(exception);
        ^
  symbol:   method assertNotNull(Exception)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:340: error: cannot find symbol
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                              ^
  symbol:   method assertThrows(Class<IllegalArgumentException>,()->{ targ[...]e); })
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:343: error: cannot find symbol
        assertNotNull(exception);
        ^
  symbol:   method assertNotNull(Exception)
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:350: error: cannot find symbol
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                              ^
  symbol:   method assertThrows(Class<IllegalArgumentException>,()->{ targ[...]e); })
  location: class SparkPodSpec_copyFrom_Test
src/test/java/com/apple/spark/operator/SparkPodSpec_copyFrom_Test.java:353: error: cannot find symbol
        assertNotNull(exception);
        ^
  symbol:   method assertNotNull(Exception)
  location: class SparkPodSpec_copyFrom_Test
81 errors
2 warnings

```

And you can use the following related information to assist you in your analysis and correction:
```
{'dependent classes': 'class `com.apple.spark.operator.SparkPodSpec`:\n\tdependent fields:\n\t\tjava.lang.String com.apple.spark.operator.SparkPodSpec.memoryOverhead;\n\t\tjava.util.List<com.apple.spark.operator.EnvVar> com.apple.spark.operator.SparkPodSpec.env;\n\t\tjava.lang.String com.apple.spark.operator.SparkPodSpec.memory;\n\t\tjava.lang.String com.apple.spark.operator.SparkPodSpec.javaOptions;\n\t\tcom.apple.spark.operator.SecurityContext com.apple.spark.operator.SparkPodSpec.securityContext;\n\t\tjava.lang.String com.apple.spark.operator.SparkPodSpec.coreRequest;\n\t\tjava.lang.Integer com.apple.spark.operator.SparkPodSpec.cores;\n\t\tjava.util.Map<java.lang.String, java.lang.String> com.apple.spark.operator.SparkPodSpec.annotations;\n\t\tjava.util.Map<java.lang.String, java.lang.String> com.apple.spark.operator.SparkPodSpec.labels;\n\t\tjava.lang.String com.apple.spark.operator.SparkPodSpec.image;\n\t\tjava.lang.Long com.apple.spark.operator.SparkPodSpec.terminationGracePeriodSeconds;\n\t\tjava.lang.String com.apple.spark.operator.SparkPodSpec.serviceAccount;\n\t\tjava.lang.String com.apple.spark.operator.SparkPodSpec.coreLimit;\n\t\tcom.apple.spark.operator.Affinity com.apple.spark.operator.SparkPodSpec.affinity;\n\t\tjava.util.List<com.apple.spark.operator.VolumeMount> com.apple.spark.operator.SparkPodSpec.volumeMounts;\n\trelated functions:\n\t\tmethod `public Integer getCores()` returns `java.lang.Integer`, related with `com.apple.spark.operator.SparkPodSpec.copyFrom(SparkPodSpec)`;\nclass `com.apple.spark.util.ConfigUtil`:\n\trelated functions:\n\t\tmethod `public static List<SparkCluster> merge(List<SparkCluster>, List<SparkCluster>)` returns `java.util.List<com.apple.spark.AppConfig.SparkCluster>`, related with `com.apple.spark.operator.SparkPodSpec.copyFrom(SparkPodSpec)`;\nclass `com.apple.spark.core.ApplicationSubmissionHelper`:\n\trelated functions:\n\t\tmethod ` static Map<String, String> applyFeatureGate(SubmitApplicationRequest, Map<String, String>)` returns `java.util.Map<java.lang.String, java.lang.String>`, related with `com.apple.spark.operator.SparkPodSpec.copyFrom(SparkPodSpec)`;'}
```

**Note**: If the method-to-test in the @Test function is a private method, you can use **reflection** to call it.

Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 


