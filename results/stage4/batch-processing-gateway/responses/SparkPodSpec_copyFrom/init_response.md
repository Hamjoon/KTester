```java
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
    
    private SparkPodSpec sourceSpec;
    private SparkPodSpec targetSpec;

    @BeforeAll
    static void setupBeforeAll() {
        // This method is called once before all tests are executed.
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the source and target SparkPodSpec objects before each test.
        sourceSpec = new SparkPodSpec();
        targetSpec = new SparkPodSpec();
        
        // Set up initial values for the sourceSpec as needed for the tests.
        sourceSpec.setCores(4);
        sourceSpec.setCoreLimit("2");
        sourceSpec.setMemory("8Gi");
        sourceSpec.setImage("spark:latest");
        
        List<EnvVar> envVars = new ArrayList<>();
        envVars.add(new EnvVar("ENV_VAR", "value"));
        sourceSpec.setEnv(envVars);

        Map<String, String> labels = new HashMap<>();
        labels.put("app", "spark");
        sourceSpec.setLabels(labels);
        
        Map<String, String> annotations = new HashMap<>();
        annotations.put("annotation", "value");
        sourceSpec.setAnnotations(annotations);
    }

    @AfterEach
    void teardownAfterEach() {
        // Clean up after each test if necessary.
    }

    @AfterAll
    static void teardownAfterAll() {
        // This method is called once after all tests are executed.
    }

    @Test
    void testCopyFrom() {
        // Given
        // The sourceSpec is already initialized in setupBeforeEach

        // When
        targetSpec.copyFrom(sourceSpec);

        // Then
        assertEquals(sourceSpec.getCores(), targetSpec.getCores());
        assertEquals(sourceSpec.getCoreLimit(), targetSpec.getCoreLimit());
        assertEquals(sourceSpec.getMemory(), targetSpec.getMemory());
        assertEquals(sourceSpec.getImage(), targetSpec.getImage());
        assertNotNull(targetSpec.getEnv());
        assertEquals(sourceSpec.getEnv().size(), targetSpec.getEnv().size());
        assertEquals(sourceSpec.getLabels(), targetSpec.getLabels());
        assertEquals(sourceSpec.getAnnotations(), targetSpec.getAnnotations());
    }
}
```