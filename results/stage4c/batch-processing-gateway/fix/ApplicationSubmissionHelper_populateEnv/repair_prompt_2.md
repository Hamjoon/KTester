


You are an experienced Java developer tasked with fixing a piece of code that contains execution errors. Your task is to resolve these issues after analyzing the code and its execution feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any logical errors.
2. Analyze the execution feedback to understand the specific errors and their locations within the code.
3. Apply necessary corrections to the code based on the feedback, and output the compelete correct code.
The Java code you need to fix is provided below:
```java
package com.apple.spark.core;

import com.apple.spark.AppConfig;
import com.apple.spark.api.SubmitApplicationRequest;
import com.apple.spark.operator.DriverSpec;
import com.apple.spark.operator.ExecutorSpec;
import com.apple.spark.operator.SparkApplicationSpec;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import com.apple.spark.operator.EnvVar;

@ExtendWith(MockitoExtension.class)
class ApplicationSubmissionHelper_populateEnv_Test {

    private SparkApplicationSpec sparkSpec;

    private SubmitApplicationRequest request;

    private AppConfig.SparkCluster sparkCluster;

    @BeforeEach
    void setupBeforeEach() {
        sparkSpec = new SparkApplicationSpec();
        request = new SubmitApplicationRequest();
        sparkCluster = new AppConfig.SparkCluster();
        // Initialize mocks if necessary
        // Example:
        // when(mockedDependency.someMethod()).thenReturn(someValue);
    }

    @Test
    void testPopulateEnv_withValidInputs() {
        // Given
        sparkSpec.setDriver(new DriverSpec());
        sparkSpec.setExecutor(new ExecutorSpec());
        request.setDriver(new DriverSpec());
        request.getDriver().setEnv(List.of(new EnvVar("VAR1", "value1")));
        request.setExecutor(new ExecutorSpec());
        request.getExecutor().setEnv(List.of(new EnvVar("VAR2", "value2")));
        sparkCluster.setDriver(new DriverSpec());
        sparkCluster.getDriver().setEnv(List.of(new EnvVar("VAR3", "value3")));
        sparkCluster.setExecutor(new ExecutorSpec());
        sparkCluster.getExecutor().setEnv(List.of(new EnvVar("VAR4", "value4")));
        // When
        ApplicationSubmissionHelper.populateEnv(sparkSpec, request, sparkCluster);
        // Then
        assertNotNull(sparkSpec.getDriver().getEnv());
        assertNotNull(sparkSpec.getExecutor().getEnv());
        assertTrue(sparkSpec.getDriver().getEnv().containsAll(List.of(new EnvVar("VAR3", "value3"), new EnvVar("VAR1", "value1"))));
        assertTrue(sparkSpec.getExecutor().getEnv().containsAll(List.of(new EnvVar("VAR4", "value4"), new EnvVar("VAR2", "value2"))));
    }

    @Test
    void testPopulateEnv_onlySparkClusterHasEnvs() {
        // Given
        sparkSpec.setDriver(new DriverSpec());
        sparkSpec.setExecutor(new ExecutorSpec());
        sparkCluster.setDriver(new DriverSpec());
        sparkCluster.getDriver().setEnv(List.of(new EnvVar("VAR3", "value3")));
        sparkCluster.setExecutor(new ExecutorSpec());
        sparkCluster.getExecutor().setEnv(List.of(new EnvVar("VAR4", "value4")));
        // When
        ApplicationSubmissionHelper.populateEnv(sparkSpec, request, sparkCluster);
        // Then
        assertEquals(List.of(new EnvVar("VAR3", "value3")), sparkSpec.getDriver().getEnv());
        assertEquals(List.of(new EnvVar("VAR4", "value4")), sparkSpec.getExecutor().getEnv());
    }

    @Test
    void testPopulateEnv_onlyRequestHasEnvs() {
        // Given
        sparkSpec.setDriver(new DriverSpec());
        sparkSpec.setExecutor(new ExecutorSpec());
        request.setDriver(new DriverSpec());
        request.getDriver().setEnv(List.of(new EnvVar("VAR1", "value1")));
        request.setExecutor(new ExecutorSpec());
        request.getExecutor().setEnv(List.of(new EnvVar("VAR2", "value2")));
        // When
        ApplicationSubmissionHelper.populateEnv(sparkSpec, request, sparkCluster);
        // Then
        assertEquals(List.of(new EnvVar("VAR1", "value1")), sparkSpec.getDriver().getEnv());
        assertEquals(List.of(new EnvVar("VAR2", "value2")), sparkSpec.getExecutor().getEnv());
    }

    @Test
    void testPopulateEnv_existingEnvsInSparkSpec() {
        // Given
        sparkSpec.setDriver(new DriverSpec());
        sparkSpec.getDriver().setEnv(new ArrayList<>(List.of(new EnvVar("EXISTING", "value"))));
        sparkSpec.setExecutor(new ExecutorSpec());
        sparkSpec.getExecutor().setEnv(new ArrayList<>(List.of(new EnvVar("EXISTING", "value"))));
        request.setDriver(new DriverSpec());
        request.getDriver().setEnv(List.of(new EnvVar("VAR1", "value1")));
        request.setExecutor(new ExecutorSpec());
        request.getExecutor().setEnv(List.of(new EnvVar("VAR2", "value2")));
        sparkCluster.setDriver(new DriverSpec());
        sparkCluster.getDriver().setEnv(List.of(new EnvVar("VAR3", "value3")));
        sparkCluster.setExecutor(new ExecutorSpec());
        sparkCluster.getExecutor().setEnv(List.of(new EnvVar("VAR4", "value4")));
        // When
        ApplicationSubmissionHelper.populateEnv(sparkSpec, request, sparkCluster);
        // Then
        assertTrue(sparkSpec.getDriver().getEnv().containsAll(List.of(new EnvVar("EXISTING", "value"), new EnvVar("VAR3", "value3"), new EnvVar("VAR1", "value1"))));
        assertTrue(sparkSpec.getExecutor().getEnv().containsAll(List.of(new EnvVar("EXISTING", "value"), new EnvVar("VAR4", "value4"), new EnvVar("VAR2", "value2"))));
    }

    @Test
    void testPopulateEnv_noEnvsInRequestOrSparkCluster() {
        // Given
        sparkSpec.setDriver(new DriverSpec());
        sparkSpec.setExecutor(new ExecutorSpec());
        sparkSpec.getDriver().setEnv(new ArrayList<>());
        sparkSpec.getExecutor().setEnv(new ArrayList<>());
        // When
        ApplicationSubmissionHelper.populateEnv(sparkSpec, request, sparkCluster);
        // Then
        assertTrue(sparkSpec.getDriver().getEnv().isEmpty());
        assertTrue(sparkSpec.getExecutor().getEnv().isEmpty());
    }

    @Test
    void testPopulateEnv_emptyEnvsInRequestAndSparkCluster() {
        // Given
        sparkSpec.setDriver(new DriverSpec());
        sparkSpec.setExecutor(new ExecutorSpec());
        sparkSpec.getDriver().setEnv(new ArrayList<>());
        sparkSpec.getExecutor().setEnv(new ArrayList<>());
        request.setDriver(new DriverSpec());
        request.getDriver().setEnv(new ArrayList<>());
        request.setExecutor(new ExecutorSpec());
        request.getExecutor().setEnv(new ArrayList<>());
        sparkCluster.setDriver(new DriverSpec());
        sparkCluster.getDriver().setEnv(new ArrayList<>());
        sparkCluster.setExecutor(new ExecutorSpec());
        sparkCluster.getExecutor().setEnv(new ArrayList<>());
        // When
        ApplicationSubmissionHelper.populateEnv(sparkSpec, request, sparkCluster);
        // Then
        assertTrue(sparkSpec.getDriver().getEnv().isEmpty());
        assertTrue(sparkSpec.getExecutor().getEnv().isEmpty());
    }

    @Test
    void testPopulateEnv_sparkSpecDriverHasExistingEnvs() {
        // Given
        sparkSpec.setDriver(new DriverSpec());
        sparkSpec.getDriver().setEnv(new ArrayList<>(List.of(new EnvVar("EXISTING", "value"))));
        sparkSpec.setExecutor(new ExecutorSpec());
        request.setDriver(new DriverSpec());
        request.getDriver().setEnv(List.of(new EnvVar("VAR1", "value1")));
        request.setExecutor(new ExecutorSpec());
        request.getExecutor().setEnv(List.of(new EnvVar("VAR2", "value2")));
        sparkCluster.setExecutor(new ExecutorSpec());
        sparkCluster.getExecutor().setEnv(List.of(new EnvVar("VAR3", "value3")));
        // When
        ApplicationSubmissionHelper.populateEnv(sparkSpec, request, sparkCluster);
        // Then
        assertTrue(sparkSpec.getDriver().getEnv().containsAll(List.of(new EnvVar("EXISTING", "value"), new EnvVar("VAR1", "value1"))));
        assertTrue(sparkSpec.getExecutor().getEnv().containsAll(List.of(new EnvVar("VAR2", "value2"), new EnvVar("VAR3", "value3"))));
    }

    @Test
    void testPopulateEnv_existingEnvsAndEmptyRequestAndSparkCluster() {
        // Given
        sparkSpec.setDriver(new DriverSpec());
        sparkSpec.getDriver().setEnv(new ArrayList<>(List.of(new EnvVar("EXISTING", "value"))));
        sparkSpec.setExecutor(new ExecutorSpec());
        sparkSpec.getExecutor().setEnv(new ArrayList<>(List.of(new EnvVar("EXISTING", "value"))));
        request.setDriver(new DriverSpec());
        request.getDriver().setEnv(new ArrayList<>());
        request.setExecutor(new ExecutorSpec());
        request.getExecutor().setEnv(new ArrayList<>());
        sparkCluster.setDriver(new DriverSpec());
        sparkCluster.getDriver().setEnv(new ArrayList<>());
        sparkCluster.setExecutor(new ExecutorSpec());
        sparkCluster.getExecutor().setEnv(new ArrayList<>());
        // When
        ApplicationSubmissionHelper.populateEnv(sparkSpec, request, sparkCluster);
        // Then
        assertEquals(List.of(new EnvVar("EXISTING", "value")), sparkSpec.getDriver().getEnv());
        assertEquals(List.of(new EnvVar("EXISTING", "value")), sparkSpec.getExecutor().getEnv());
    }

    @Test
    void testPopulateEnv_nullSparkSpec() {
        // Given
        sparkSpec = null;
        request.setDriver(new DriverSpec());
        request.getDriver().setEnv(List.of(new EnvVar("VAR1", "value1")));
        request.setExecutor(new ExecutorSpec());
        request.getExecutor().setEnv(List.of(new EnvVar("VAR2", "value2")));
        sparkCluster.setDriver(new DriverSpec());
        sparkCluster.getDriver().setEnv(List.of(new EnvVar("VAR3", "value3")));
        sparkCluster.setExecutor(new ExecutorSpec());
        sparkCluster.getExecutor().setEnv(List.of(new EnvVar("VAR4", "value4")));
        // When & Then
        assertThrows(NullPointerException.class, () -> ApplicationSubmissionHelper.populateEnv(sparkSpec, request, sparkCluster));
    }

    @Test
    void testPopulateEnv_nullRequest() {
        // Given
        request = null;
        sparkCluster.setDriver(new DriverSpec());
        sparkCluster.getDriver().setEnv(List.of(new EnvVar("VAR3", "value3")));
        sparkCluster.setExecutor(new ExecutorSpec());
        sparkCluster.getExecutor().setEnv(List.of(new EnvVar("VAR4", "value4")));
        // When & Then
        assertThrows(NullPointerException.class, () -> ApplicationSubmissionHelper.populateEnv(sparkSpec, request, sparkCluster));
    }

    @Test
    void testPopulateEnv_nullSparkCluster() {
        // Given
        sparkCluster = null;
        request.setDriver(new DriverSpec());
        request.getDriver().setEnv(List.of(new EnvVar("VAR1", "value1")));
        request.setExecutor(new ExecutorSpec());
        request.getExecutor().setEnv(List.of(new EnvVar("VAR2", "value2")));
        // When & Then
        assertThrows(NullPointerException.class, () -> ApplicationSubmissionHelper.populateEnv(sparkSpec, request, sparkCluster));
    }
}

```

Corresponding execution feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended
SLF4J: Class path contains multiple SLF4J bindings.
SLF4J: Found binding in [jar:file:/work/data/projects/batch-processing-gateway/libs/logback-classic-1.2.11.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: Found binding in [jar:file:/work/code/dependencies/evosuite-1.2.0.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
SLF4J: Actual binding is of type [ch.qos.logback.classic.util.ContextSelectorStaticBinder]

╷
├─ JUnit Jupiter ✔
│  └─ ApplicationSubmissionHelper_populateEnv_Test ✔
│     ├─ testPopulateEnv_onlySparkClusterHasEnvs() ✘ expected: <[com.apple.spark.operator.EnvVar@1efdcd5]> but was: <[com.apple.spark.operator.EnvVar@1623bbe5]>
│     ├─ testPopulateEnv_existingEnvsInSparkSpec() ✘ expected: <true> but was: <false>
│     ├─ testPopulateEnv_nullRequest() ✔
│     ├─ testPopulateEnv_emptyEnvsInRequestAndSparkCluster() ✔
│     ├─ testPopulateEnv_nullSparkSpec() ✔
│     ├─ testPopulateEnv_onlyRequestHasEnvs() ✘ expected: <[com.apple.spark.operator.EnvVar@c68a5f8]> but was: <[com.apple.spark.operator.EnvVar@69c6161d]>
│     ├─ testPopulateEnv_withValidInputs() ✘ expected: <true> but was: <false>
│     ├─ testPopulateEnv_sparkSpecDriverHasExistingEnvs() ✘ expected: <true> but was: <false>
│     ├─ testPopulateEnv_existingEnvsAndEmptyRequestAndSparkCluster() ✘ expected: <[com.apple.spark.operator.EnvVar@5eccd3b9]> but was: <[com.apple.spark.operator.EnvVar@4d6f197e]>
│     ├─ testPopulateEnv_nullSparkCluster() ✔
│     └─ testPopulateEnv_noEnvsInRequestOrSparkCluster() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Failures (6):
  JUnit Jupiter:ApplicationSubmissionHelper_populateEnv_Test:testPopulateEnv_onlySparkClusterHasEnvs()
    MethodSource [className = 'com.apple.spark.core.ApplicationSubmissionHelper_populateEnv_Test', methodName = 'testPopulateEnv_onlySparkClusterHasEnvs', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <[com.apple.spark.operator.EnvVar@1efdcd5]> but was: <[com.apple.spark.operator.EnvVar@1623bbe5]>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1141)
       com.apple.spark.core.ApplicationSubmissionHelper_populateEnv_Test.testPopulateEnv_onlySparkClusterHasEnvs(ApplicationSubmissionHelper_populateEnv_Test.java:71)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:ApplicationSubmissionHelper_populateEnv_Test:testPopulateEnv_existingEnvsInSparkSpec()
    MethodSource [className = 'com.apple.spark.core.ApplicationSubmissionHelper_populateEnv_Test', methodName = 'testPopulateEnv_existingEnvsInSparkSpec', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <true> but was: <false>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertTrue.failNotTrue(AssertTrue.java:63)
       org.junit.jupiter.api.AssertTrue.assertTrue(AssertTrue.java:36)
       org.junit.jupiter.api.AssertTrue.assertTrue(AssertTrue.java:31)
       org.junit.jupiter.api.Assertions.assertTrue(Assertions.java:179)
       com.apple.spark.core.ApplicationSubmissionHelper_populateEnv_Test.testPopulateEnv_existingEnvsInSparkSpec(ApplicationSubmissionHelper_populateEnv_Test.java:109)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:ApplicationSubmissionHelper_populateEnv_Test:testPopulateEnv_onlyRequestHasEnvs()
    MethodSource [className = 'com.apple.spark.core.ApplicationSubmissionHelper_populateEnv_Test', methodName = 'testPopulateEnv_onlyRequestHasEnvs', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <[com.apple.spark.operator.EnvVar@c68a5f8]> but was: <[com.apple.spark.operator.EnvVar@69c6161d]>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1141)
       com.apple.spark.core.ApplicationSubmissionHelper_populateEnv_Test.testPopulateEnv_onlyRequestHasEnvs(ApplicationSubmissionHelper_populateEnv_Test.java:87)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:ApplicationSubmissionHelper_populateEnv_Test:testPopulateEnv_withValidInputs()
    MethodSource [className = 'com.apple.spark.core.ApplicationSubmissionHelper_populateEnv_Test', methodName = 'testPopulateEnv_withValidInputs', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <true> but was: <false>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertTrue.failNotTrue(AssertTrue.java:63)
       org.junit.jupiter.api.AssertTrue.assertTrue(AssertTrue.java:36)
       org.junit.jupiter.api.AssertTrue.assertTrue(AssertTrue.java:31)
       org.junit.jupiter.api.Assertions.assertTrue(Assertions.java:179)
       com.apple.spark.core.ApplicationSubmissionHelper_populateEnv_Test.testPopulateEnv_withValidInputs(ApplicationSubmissionHelper_populateEnv_Test.java:55)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:ApplicationSubmissionHelper_populateEnv_Test:testPopulateEnv_sparkSpecDriverHasExistingEnvs()
    MethodSource [className = 'com.apple.spark.core.ApplicationSubmissionHelper_populateEnv_Test', methodName = 'testPopulateEnv_sparkSpecDriverHasExistingEnvs', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <true> but was: <false>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertTrue.failNotTrue(AssertTrue.java:63)
       org.junit.jupiter.api.AssertTrue.assertTrue(AssertTrue.java:36)
       org.junit.jupiter.api.AssertTrue.assertTrue(AssertTrue.java:31)
       org.junit.jupiter.api.Assertions.assertTrue(Assertions.java:179)
       com.apple.spark.core.ApplicationSubmissionHelper_populateEnv_Test.testPopulateEnv_sparkSpecDriverHasExistingEnvs(ApplicationSubmissionHelper_populateEnv_Test.java:164)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:ApplicationSubmissionHelper_populateEnv_Test:testPopulateEnv_existingEnvsAndEmptyRequestAndSparkCluster()
    MethodSource [className = 'com.apple.spark.core.ApplicationSubmissionHelper_populateEnv_Test', methodName = 'testPopulateEnv_existingEnvsAndEmptyRequestAndSparkCluster', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <[com.apple.spark.operator.EnvVar@5eccd3b9]> but was: <[com.apple.spark.operator.EnvVar@4d6f197e]>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1141)
       com.apple.spark.core.ApplicationSubmissionHelper_populateEnv_Test.testPopulateEnv_existingEnvsAndEmptyRequestAndSparkCluster(ApplicationSubmissionHelper_populateEnv_Test.java:186)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]

Test run finished after 413 ms
[         4 containers found      ]
[         0 containers skipped    ]
[         4 containers started    ]
[         0 containers aborted    ]
[         4 containers successful ]
[         0 containers failed     ]
[        11 tests found           ]
[         0 tests skipped         ]
[        11 tests started         ]
[         0 tests aborted         ]
[         5 tests successful      ]
[         6 tests failed          ]


```


And you can use the following related information to assist you in your analysis and correction:

dependent classes: 
class `com.apple.spark.core.ApplicationSubmissionHelper`:
	related functions:
		method `public static ExecutorSpec getExecutorSpec(SubmitApplicationRequest, AppConfig, String, SparkCluster)` returns `com.apple.spark.operator.ExecutorSpec`, related with `com.apple.spark.core.ApplicationSubmissionHelper.populateEnv(SparkApplicationSpec, SubmitApplicationRequest, AppConfig.SparkCluster)`;
		method `public static void populateEnv(SparkApplicationSpec, SubmitApplicationRequest, AppConfig.SparkCluster)` returns `void`, related with `com.apple.spark.core.ApplicationSubmissionHelper.populateEnv(SparkApplicationSpec, SubmitApplicationRequest, AppConfig.SparkCluster)`;
class `com.apple.spark.rest.ApplicationSubmissionRest`:
	related functions:
		method `private SparkApplicationSpec removeEnvFromSpec(SparkApplicationSpec)` returns `com.apple.spark.operator.SparkApplicationSpec`, related with `com.apple.spark.core.ApplicationSubmissionHelper.populateEnv(SparkApplicationSpec, SubmitApplicationRequest, AppConfig.SparkCluster)`, api document: 
   * Remove env variables from spec to avoid leaking sensitive information
   *
   * @param sparkApplicationSpec full application spec (will be modified)
   * @return modified application spec
   ;
class `com.apple.spark.util.CustomSerDe`:
	related functions:
		method `public static String submitRequestToNonSensitiveJson(SubmitApplicationRequest)` returns `java.lang.String`, related with `com.apple.spark.core.ApplicationSubmissionHelper.populateEnv(SparkApplicationSpec, SubmitApplicationRequest, AppConfig.SparkCluster)`, api document: 
   * Given an application submission request, serialize it to a JSON string with sensitive
   * information removed/masked
   *
   * @param submitRequest
   * @return a serialized JSON string
   * @throws JsonProcessingException
   ;
		method `public static String sparkSpecToNonSensitiveJson(SparkApplicationSpec)` returns `java.lang.String`, related with `com.apple.spark.core.ApplicationSubmissionHelper.populateEnv(SparkApplicationSpec, SubmitApplicationRequest, AppConfig.SparkCluster)`, api document: 
   * Given a Spark application spec, serialize it to a JSON string with sensitive information
   * removed/masked
   *
   * @param sparkSpec
   * @return a serialized JSON string
   * @throws JsonProcessingException
   ;
class `com.apple.spark.operator.SparkApplicationSpec.Builder`:
	related functions:
		method `public Builder extendVolumes(List<Volume>)` returns `com.apple.spark.operator.SparkApplicationSpec.Builder`, related with `com.apple.spark.core.ApplicationSubmissionHelper.populateEnv(SparkApplicationSpec, SubmitApplicationRequest, AppConfig.SparkCluster)`;
class `com.apple.spark.tools.LoadTest`:
	related functions:
		method `private static void submitApplication(int, int, int, int)` returns `void`, related with `com.apple.spark.core.ApplicationSubmissionHelper.populateEnv(SparkApplicationSpec, SubmitApplicationRequest, AppConfig.SparkCluster)`;
class `com.apple.spark.BPGApplication`:
	related functions:
		method `private void sendQueueInfoMetrics(AppConfig, CounterMetricContainer)` returns `void`, related with `com.apple.spark.core.ApplicationSubmissionHelper.populateEnv(SparkApplicationSpec, SubmitApplicationRequest, AppConfig.SparkCluster)`, api document: 
   * Go through clusters and send out metrics, so we could show which queues are on which clusters.
   *
   * @param configuration App configuration
   * @param periodicMetrics Metric instances
   ;



Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 
