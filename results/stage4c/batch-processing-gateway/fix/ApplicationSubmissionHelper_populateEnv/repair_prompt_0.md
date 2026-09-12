
You are an experienced Java developer tasked with fixing a piece of code that contains compilation errors. Your task is to resolve these issues after analyzing the code and its compilation feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any syntactical or logical errors.
2. Analyze the compilation feedback to understand the specific errors and their locations within the code.
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
        request.getDriver().setEnv(List.of("VAR1=value1"));
        request.setExecutor(new ExecutorSpec());
        request.getExecutor().setEnv(List.of("VAR2=value2"));
        sparkCluster.setDriver(new DriverSpec());
        sparkCluster.getDriver().setEnv(List.of("VAR3=value3"));
        sparkCluster.setExecutor(new ExecutorSpec());
        sparkCluster.getExecutor().setEnv(List.of("VAR4=value4"));
        // When
        ApplicationSubmissionHelper.populateEnv(sparkSpec, request, sparkCluster);
        // Then
        assertNotNull(sparkSpec.getDriver().getEnv());
        assertNotNull(sparkSpec.getExecutor().getEnv());
        assertTrue(sparkSpec.getDriver().getEnv().containsAll(List.of("VAR3=value3", "VAR1=value1")));
        assertTrue(sparkSpec.getExecutor().getEnv().containsAll(List.of("VAR4=value4", "VAR2=value2")));
    }

    @Test
    void testPopulateEnv_onlySparkClusterHasEnvs() {
        // Given
        sparkSpec.setDriver(new DriverSpec());
        sparkSpec.setExecutor(new ExecutorSpec());
        sparkCluster.setDriver(new DriverSpec());
        sparkCluster.getDriver().setEnv(List.of("VAR3=value3"));
        sparkCluster.setExecutor(new ExecutorSpec());
        sparkCluster.getExecutor().setEnv(List.of("VAR4=value4"));
        // When
        ApplicationSubmissionHelper.populateEnv(sparkSpec, request, sparkCluster);
        // Then
        assertEquals(List.of("VAR3=value3"), sparkSpec.getDriver().getEnv());
        assertEquals(List.of("VAR4=value4"), sparkSpec.getExecutor().getEnv());
    }

    @Test
    void testPopulateEnv_onlyRequestHasEnvs() {
        // Given
        sparkSpec.setDriver(new DriverSpec());
        sparkSpec.setExecutor(new ExecutorSpec());
        request.setDriver(new DriverSpec());
        request.getDriver().setEnv(List.of("VAR1=value1"));
        request.setExecutor(new ExecutorSpec());
        request.getExecutor().setEnv(List.of("VAR2=value2"));
        // When
        ApplicationSubmissionHelper.populateEnv(sparkSpec, request, sparkCluster);
        // Then
        assertEquals(List.of("VAR1=value1"), sparkSpec.getDriver().getEnv());
        assertEquals(List.of("VAR2=value2"), sparkSpec.getExecutor().getEnv());
    }

    @Test
    void testPopulateEnv_existingEnvsInSparkSpec() {
        // Given
        sparkSpec.setDriver(new DriverSpec());
        sparkSpec.getDriver().setEnv(new ArrayList<>(List.of("EXISTING=value")));
        sparkSpec.setExecutor(new ExecutorSpec());
        sparkSpec.getExecutor().setEnv(new ArrayList<>(List.of("EXISTING=value")));
        request.setDriver(new DriverSpec());
        request.getDriver().setEnv(List.of("VAR1=value1"));
        request.setExecutor(new ExecutorSpec());
        request.getExecutor().setEnv(List.of("VAR2=value2"));
        sparkCluster.setDriver(new DriverSpec());
        sparkCluster.getDriver().setEnv(List.of("VAR3=value3"));
        sparkCluster.setExecutor(new ExecutorSpec());
        sparkCluster.getExecutor().setEnv(List.of("VAR4=value4"));
        // When
        ApplicationSubmissionHelper.populateEnv(sparkSpec, request, sparkCluster);
        // Then
        assertTrue(sparkSpec.getDriver().getEnv().containsAll(List.of("EXISTING=value", "VAR3=value3", "VAR1=value1")));
        assertTrue(sparkSpec.getExecutor().getEnv().containsAll(List.of("EXISTING=value", "VAR4=value4", "VAR2=value2")));
    }

    @Test
    void testPopulateEnv_noEnvsInRequestOrSparkCluster() {
        // Given
        sparkSpec.setDriver(new DriverSpec());
        sparkSpec.setExecutor(new ExecutorSpec());
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
        sparkSpec.getDriver().setEnv(new ArrayList<>(List.of("EXISTING=value")));
        sparkSpec.setExecutor(new ExecutorSpec());
        request.setDriver(new DriverSpec());
        request.getDriver().setEnv(List.of("VAR1=value1"));
        request.setExecutor(new ExecutorSpec());
        request.getExecutor().setEnv(List.of("VAR2=value2"));
        sparkCluster.setExecutor(new ExecutorSpec());
        sparkCluster.getExecutor().setEnv(List.of("VAR3=value3"));
        // When
        ApplicationSubmissionHelper.populateEnv(sparkSpec, request, sparkCluster);
        // Then
        assertTrue(sparkSpec.getDriver().getEnv().containsAll(List.of("EXISTING=value", "VAR1=value1")));
        assertTrue(sparkSpec.getExecutor().getEnv().containsAll(List.of("VAR2=value2", "VAR3=value3")));
    }

    @Test
    void testPopulateEnv_existingEnvsAndEmptyRequestAndSparkCluster() {
        // Given
        sparkSpec.setDriver(new DriverSpec());
        sparkSpec.getDriver().setEnv(new ArrayList<>(List.of("EXISTING=value")));
        sparkSpec.setExecutor(new ExecutorSpec());
        sparkSpec.getExecutor().setEnv(new ArrayList<>(List.of("EXISTING=value")));
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
        assertEquals(List.of("EXISTING=value"), sparkSpec.getDriver().getEnv());
        assertEquals(List.of("EXISTING=value"), sparkSpec.getExecutor().getEnv());
    }

    @Test
    void testPopulateEnv_nullSparkSpec() {
        // Given
        sparkSpec = null;
        request.setDriver(new DriverSpec());
        request.getDriver().setEnv(List.of("VAR1=value1"));
        request.setExecutor(new ExecutorSpec());
        request.getExecutor().setEnv(List.of("VAR2=value2"));
        sparkCluster.setDriver(new DriverSpec());
        sparkCluster.getDriver().setEnv(List.of("VAR3=value3"));
        sparkCluster.setExecutor(new ExecutorSpec());
        sparkCluster.getExecutor().setEnv(List.of("VAR4=value4"));
        // When & Then
        assertThrows(NullPointerException.class, () -> ApplicationSubmissionHelper.populateEnv(sparkSpec, request, sparkCluster));
    }

    @Test
    void testPopulateEnv_nullRequest() {
        // Given
        request = null;
        sparkCluster.setDriver(new DriverSpec());
        sparkCluster.getDriver().setEnv(List.of("VAR3=value3"));
        sparkCluster.setExecutor(new ExecutorSpec());
        sparkCluster.getExecutor().setEnv(List.of("VAR4=value4"));
        // When & Then
        assertThrows(NullPointerException.class, () -> ApplicationSubmissionHelper.populateEnv(sparkSpec, request, sparkCluster));
    }

    @Test
    void testPopulateEnv_nullSparkCluster() {
        // Given
        sparkCluster = null;
        request.setDriver(new DriverSpec());
        request.getDriver().setEnv(List.of("VAR1=value1"));
        request.setExecutor(new ExecutorSpec());
        request.getExecutor().setEnv(List.of("VAR2=value2"));
        // When & Then
        assertThrows(NullPointerException.class, () -> ApplicationSubmissionHelper.populateEnv(sparkSpec, request, sparkCluster));
    }
}
```

Corresponding compilation feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
warning: Supported source version 'RELEASE_6' from annotation processor 'org.sonatype.guice.bean.scanners.index.SisuIndexAPT6' less than -source '17'
warning: Supported source version 'RELEASE_6' from annotation processor 'org.eclipse.sisu.scanners.index.SisuIndexAPT6' less than -source '17'
src/test/java/com/apple/spark/core/ApplicationSubmissionHelper_populateEnv_Test.java:42: error: incompatible types: inference variable E has incompatible bounds
        request.getDriver().setEnv(List.of("VAR1=value1"));
                           ^
    equality constraints: EnvVar
    lower bounds: String
  where E is a type-variable:
    E extends Object declared in method <E>of(E)
src/test/java/com/apple/spark/core/ApplicationSubmissionHelper_populateEnv_Test.java:44: error: incompatible types: inference variable E has incompatible bounds
        request.getExecutor().setEnv(List.of("VAR2=value2"));
                             ^
    equality constraints: EnvVar
    lower bounds: String
  where E is a type-variable:
    E extends Object declared in method <E>of(E)
src/test/java/com/apple/spark/core/ApplicationSubmissionHelper_populateEnv_Test.java:46: error: incompatible types: inference variable E has incompatible bounds
        sparkCluster.getDriver().setEnv(List.of("VAR3=value3"));
                                ^
    equality constraints: EnvVar
    lower bounds: String
  where E is a type-variable:
    E extends Object declared in method <E>of(E)
src/test/java/com/apple/spark/core/ApplicationSubmissionHelper_populateEnv_Test.java:48: error: incompatible types: inference variable E has incompatible bounds
        sparkCluster.getExecutor().setEnv(List.of("VAR4=value4"));
                                  ^
    equality constraints: EnvVar
    lower bounds: String
  where E is a type-variable:
    E extends Object declared in method <E>of(E)
src/test/java/com/apple/spark/core/ApplicationSubmissionHelper_populateEnv_Test.java:64: error: incompatible types: inference variable E has incompatible bounds
        sparkCluster.getDriver().setEnv(List.of("VAR3=value3"));
                                ^
    equality constraints: EnvVar
    lower bounds: String
  where E is a type-variable:
    E extends Object declared in method <E>of(E)
src/test/java/com/apple/spark/core/ApplicationSubmissionHelper_populateEnv_Test.java:66: error: incompatible types: inference variable E has incompatible bounds
        sparkCluster.getExecutor().setEnv(List.of("VAR4=value4"));
                                  ^
    equality constraints: EnvVar
    lower bounds: String
  where E is a type-variable:
    E extends Object declared in method <E>of(E)
src/test/java/com/apple/spark/core/ApplicationSubmissionHelper_populateEnv_Test.java:80: error: incompatible types: inference variable E has incompatible bounds
        request.getDriver().setEnv(List.of("VAR1=value1"));
                           ^
    equality constraints: EnvVar
    lower bounds: String
  where E is a type-variable:
    E extends Object declared in method <E>of(E)
src/test/java/com/apple/spark/core/ApplicationSubmissionHelper_populateEnv_Test.java:82: error: incompatible types: inference variable E has incompatible bounds
        request.getExecutor().setEnv(List.of("VAR2=value2"));
                             ^
    equality constraints: EnvVar
    lower bounds: String
  where E is a type-variable:
    E extends Object declared in method <E>of(E)
src/test/java/com/apple/spark/core/ApplicationSubmissionHelper_populateEnv_Test.java:94: error: incompatible types: cannot infer type arguments for ArrayList<>
        sparkSpec.getDriver().setEnv(new ArrayList<>(List.of("EXISTING=value")));
                                                  ^
    reason: inference variable E#1 has incompatible bounds
      equality constraints: EnvVar
      lower bounds: E#2,String
  where E#1,E#2 are type-variables:
    E#1 extends Object declared in class ArrayList
    E#2 extends Object declared in method <E#2>of(E#2)
src/test/java/com/apple/spark/core/ApplicationSubmissionHelper_populateEnv_Test.java:96: error: incompatible types: cannot infer type arguments for ArrayList<>
        sparkSpec.getExecutor().setEnv(new ArrayList<>(List.of("EXISTING=value")));
                                                    ^
    reason: inference variable E#1 has incompatible bounds
      equality constraints: EnvVar
      lower bounds: E#2,String
  where E#1,E#2 are type-variables:
    E#1 extends Object declared in class ArrayList
    E#2 extends Object declared in method <E#2>of(E#2)
src/test/java/com/apple/spark/core/ApplicationSubmissionHelper_populateEnv_Test.java:98: error: incompatible types: inference variable E has incompatible bounds
        request.getDriver().setEnv(List.of("VAR1=value1"));
                           ^
    equality constraints: EnvVar
    lower bounds: String
  where E is a type-variable:
    E extends Object declared in method <E>of(E)
src/test/java/com/apple/spark/core/ApplicationSubmissionHelper_populateEnv_Test.java:100: error: incompatible types: inference variable E has incompatible bounds
        request.getExecutor().setEnv(List.of("VAR2=value2"));
                             ^
    equality constraints: EnvVar
    lower bounds: String
  where E is a type-variable:
    E extends Object declared in method <E>of(E)
src/test/java/com/apple/spark/core/ApplicationSubmissionHelper_populateEnv_Test.java:102: error: incompatible types: inference variable E has incompatible bounds
        sparkCluster.getDriver().setEnv(List.of("VAR3=value3"));
                                ^
    equality constraints: EnvVar
    lower bounds: String
  where E is a type-variable:
    E extends Object declared in method <E>of(E)
src/test/java/com/apple/spark/core/ApplicationSubmissionHelper_populateEnv_Test.java:104: error: incompatible types: inference variable E has incompatible bounds
        sparkCluster.getExecutor().setEnv(List.of("VAR4=value4"));
                                  ^
    equality constraints: EnvVar
    lower bounds: String
  where E is a type-variable:
    E extends Object declared in method <E>of(E)
src/test/java/com/apple/spark/core/ApplicationSubmissionHelper_populateEnv_Test.java:148: error: incompatible types: cannot infer type arguments for ArrayList<>
        sparkSpec.getDriver().setEnv(new ArrayList<>(List.of("EXISTING=value")));
                                                  ^
    reason: inference variable E#1 has incompatible bounds
      equality constraints: EnvVar
      lower bounds: E#2,String
  where E#1,E#2 are type-variables:
    E#1 extends Object declared in class ArrayList
    E#2 extends Object declared in method <E#2>of(E#2)
src/test/java/com/apple/spark/core/ApplicationSubmissionHelper_populateEnv_Test.java:151: error: incompatible types: inference variable E has incompatible bounds
        request.getDriver().setEnv(List.of("VAR1=value1"));
                           ^
    equality constraints: EnvVar
    lower bounds: String
  where E is a type-variable:
    E extends Object declared in method <E>of(E)
src/test/java/com/apple/spark/core/ApplicationSubmissionHelper_populateEnv_Test.java:153: error: incompatible types: inference variable E has incompatible bounds
        request.getExecutor().setEnv(List.of("VAR2=value2"));
                             ^
    equality constraints: EnvVar
    lower bounds: String
  where E is a type-variable:
    E extends Object declared in method <E>of(E)
src/test/java/com/apple/spark/core/ApplicationSubmissionHelper_populateEnv_Test.java:155: error: incompatible types: inference variable E has incompatible bounds
        sparkCluster.getExecutor().setEnv(List.of("VAR3=value3"));
                                  ^
    equality constraints: EnvVar
    lower bounds: String
  where E is a type-variable:
    E extends Object declared in method <E>of(E)
src/test/java/com/apple/spark/core/ApplicationSubmissionHelper_populateEnv_Test.java:167: error: incompatible types: cannot infer type arguments for ArrayList<>
        sparkSpec.getDriver().setEnv(new ArrayList<>(List.of("EXISTING=value")));
                                                  ^
    reason: inference variable E#1 has incompatible bounds
      equality constraints: EnvVar
      lower bounds: E#2,String
  where E#1,E#2 are type-variables:
    E#1 extends Object declared in class ArrayList
    E#2 extends Object declared in method <E#2>of(E#2)
src/test/java/com/apple/spark/core/ApplicationSubmissionHelper_populateEnv_Test.java:169: error: incompatible types: cannot infer type arguments for ArrayList<>
        sparkSpec.getExecutor().setEnv(new ArrayList<>(List.of("EXISTING=value")));
                                                    ^
    reason: inference variable E#1 has incompatible bounds
      equality constraints: EnvVar
      lower bounds: E#2,String
  where E#1,E#2 are type-variables:
    E#1 extends Object declared in class ArrayList
    E#2 extends Object declared in method <E#2>of(E#2)
src/test/java/com/apple/spark/core/ApplicationSubmissionHelper_populateEnv_Test.java:190: error: incompatible types: inference variable E has incompatible bounds
        request.getDriver().setEnv(List.of("VAR1=value1"));
                           ^
    equality constraints: EnvVar
    lower bounds: String
  where E is a type-variable:
    E extends Object declared in method <E>of(E)
src/test/java/com/apple/spark/core/ApplicationSubmissionHelper_populateEnv_Test.java:192: error: incompatible types: inference variable E has incompatible bounds
        request.getExecutor().setEnv(List.of("VAR2=value2"));
                             ^
    equality constraints: EnvVar
    lower bounds: String
  where E is a type-variable:
    E extends Object declared in method <E>of(E)
src/test/java/com/apple/spark/core/ApplicationSubmissionHelper_populateEnv_Test.java:194: error: incompatible types: inference variable E has incompatible bounds
        sparkCluster.getDriver().setEnv(List.of("VAR3=value3"));
                                ^
    equality constraints: EnvVar
    lower bounds: String
  where E is a type-variable:
    E extends Object declared in method <E>of(E)
src/test/java/com/apple/spark/core/ApplicationSubmissionHelper_populateEnv_Test.java:196: error: incompatible types: inference variable E has incompatible bounds
        sparkCluster.getExecutor().setEnv(List.of("VAR4=value4"));
                                  ^
    equality constraints: EnvVar
    lower bounds: String
  where E is a type-variable:
    E extends Object declared in method <E>of(E)
src/test/java/com/apple/spark/core/ApplicationSubmissionHelper_populateEnv_Test.java:206: error: incompatible types: inference variable E has incompatible bounds
        sparkCluster.getDriver().setEnv(List.of("VAR3=value3"));
                                ^
    equality constraints: EnvVar
    lower bounds: String
  where E is a type-variable:
    E extends Object declared in method <E>of(E)
src/test/java/com/apple/spark/core/ApplicationSubmissionHelper_populateEnv_Test.java:208: error: incompatible types: inference variable E has incompatible bounds
        sparkCluster.getExecutor().setEnv(List.of("VAR4=value4"));
                                  ^
    equality constraints: EnvVar
    lower bounds: String
  where E is a type-variable:
    E extends Object declared in method <E>of(E)
src/test/java/com/apple/spark/core/ApplicationSubmissionHelper_populateEnv_Test.java:218: error: incompatible types: inference variable E has incompatible bounds
        request.getDriver().setEnv(List.of("VAR1=value1"));
                           ^
    equality constraints: EnvVar
    lower bounds: String
  where E is a type-variable:
    E extends Object declared in method <E>of(E)
src/test/java/com/apple/spark/core/ApplicationSubmissionHelper_populateEnv_Test.java:220: error: incompatible types: inference variable E has incompatible bounds
        request.getExecutor().setEnv(List.of("VAR2=value2"));
                             ^
    equality constraints: EnvVar
    lower bounds: String
  where E is a type-variable:
    E extends Object declared in method <E>of(E)
Note: Some messages have been simplified; recompile with -Xdiags:verbose to get full output
28 errors
2 warnings

```

And you can use the following related information to assist you in your analysis and correction:
```
{'dependent classes': 'class `com.apple.spark.core.ApplicationSubmissionHelper`:\n\trelated functions:\n\t\tmethod `public static ExecutorSpec getExecutorSpec(SubmitApplicationRequest, AppConfig, String, SparkCluster)` returns `com.apple.spark.operator.ExecutorSpec`, related with `com.apple.spark.core.ApplicationSubmissionHelper.populateEnv(SparkApplicationSpec, SubmitApplicationRequest, AppConfig.SparkCluster)`;\n\t\tmethod `public static void populateEnv(SparkApplicationSpec, SubmitApplicationRequest, AppConfig.SparkCluster)` returns `void`, related with `com.apple.spark.core.ApplicationSubmissionHelper.populateEnv(SparkApplicationSpec, SubmitApplicationRequest, AppConfig.SparkCluster)`;\nclass `com.apple.spark.rest.ApplicationSubmissionRest`:\n\trelated functions:\n\t\tmethod `private SparkApplicationSpec removeEnvFromSpec(SparkApplicationSpec)` returns `com.apple.spark.operator.SparkApplicationSpec`, related with `com.apple.spark.core.ApplicationSubmissionHelper.populateEnv(SparkApplicationSpec, SubmitApplicationRequest, AppConfig.SparkCluster)`, api document: \n   * Remove env variables from spec to avoid leaking sensitive information\n   *\n   * @param sparkApplicationSpec full application spec (will be modified)\n   * @return modified application spec\n   ;\nclass `com.apple.spark.util.CustomSerDe`:\n\trelated functions:\n\t\tmethod `public static String submitRequestToNonSensitiveJson(SubmitApplicationRequest)` returns `java.lang.String`, related with `com.apple.spark.core.ApplicationSubmissionHelper.populateEnv(SparkApplicationSpec, SubmitApplicationRequest, AppConfig.SparkCluster)`, api document: \n   * Given an application submission request, serialize it to a JSON string with sensitive\n   * information removed/masked\n   *\n   * @param submitRequest\n   * @return a serialized JSON string\n   * @throws JsonProcessingException\n   ;\n\t\tmethod `public static String sparkSpecToNonSensitiveJson(SparkApplicationSpec)` returns `java.lang.String`, related with `com.apple.spark.core.ApplicationSubmissionHelper.populateEnv(SparkApplicationSpec, SubmitApplicationRequest, AppConfig.SparkCluster)`, api document: \n   * Given a Spark application spec, serialize it to a JSON string with sensitive information\n   * removed/masked\n   *\n   * @param sparkSpec\n   * @return a serialized JSON string\n   * @throws JsonProcessingException\n   ;\nclass `com.apple.spark.operator.SparkApplicationSpec.Builder`:\n\trelated functions:\n\t\tmethod `public Builder extendVolumes(List<Volume>)` returns `com.apple.spark.operator.SparkApplicationSpec.Builder`, related with `com.apple.spark.core.ApplicationSubmissionHelper.populateEnv(SparkApplicationSpec, SubmitApplicationRequest, AppConfig.SparkCluster)`;\nclass `com.apple.spark.tools.LoadTest`:\n\trelated functions:\n\t\tmethod `private static void submitApplication(int, int, int, int)` returns `void`, related with `com.apple.spark.core.ApplicationSubmissionHelper.populateEnv(SparkApplicationSpec, SubmitApplicationRequest, AppConfig.SparkCluster)`;\nclass `com.apple.spark.BPGApplication`:\n\trelated functions:\n\t\tmethod `private void sendQueueInfoMetrics(AppConfig, CounterMetricContainer)` returns `void`, related with `com.apple.spark.core.ApplicationSubmissionHelper.populateEnv(SparkApplicationSpec, SubmitApplicationRequest, AppConfig.SparkCluster)`, api document: \n   * Go through clusters and send out metrics, so we could show which queues are on which clusters.\n   *\n   * @param configuration App configuration\n   * @param periodicMetrics Metric instances\n   ;'}
```

**Note**: If the method-to-test in the @Test function is a private method, you can use **reflection** to call it.

Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 


