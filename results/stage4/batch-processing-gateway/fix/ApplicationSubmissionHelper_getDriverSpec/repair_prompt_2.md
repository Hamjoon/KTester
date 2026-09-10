


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
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import java.util.HashMap;
import java.util.Map;
import org.mockito.junit.jupiter.MockitoExtension;
import com.apple.spark.AppConfig.SparkCluster;
import static com.apple.spark.AppConfig.SparkCluster;
// Corrected import for List
import java.util.List;
// Corrected import
// Assuming Volume class exists
import com.apple.spark.operator.Volume;
// Assuming VolumeMount class exists
import com.apple.spark.operator.VolumeMount;

@ExtendWith(MockitoExtension.class)
class ApplicationSubmissionHelper_getDriverSpec_Test {

    private SubmitApplicationRequest request;

    private AppConfig appConfig;

    private SparkCluster sparkCluster;

    private String parentQueue;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize necessary fields and dependencies before each test
        request = mock(SubmitApplicationRequest.class);
        appConfig = new AppConfig();
        sparkCluster = mock(SparkCluster.class);
        // Example value, adjust as necessary
        parentQueue = "parentQueue";
    }

    @Test
    void testGetDriverSpec_withValidRequest() {
        // Arrange: Set up the mock request and its expected behavior
        when(request.getDriver()).thenReturn(mock(DriverSpec.class));
        when(request.getDriver().getCores()).thenReturn(1);
        when(request.getDriver().getMemory()).thenReturn("1g");
        // Act: Call the method under test
        DriverSpec driverSpec = ApplicationSubmissionHelper.getDriverSpec(request, appConfig, parentQueue, sparkCluster);
        // Assert: Validate the results
        assertNotNull(driverSpec);
        // Add more assertions based on the expected behavior of getDriverSpec
    }

    @Test
    void testGetDriverSpec_withNullDriver() {
        // Arrange: Set up the mock request with a null driver
        when(request.getDriver()).thenReturn(null);
        when(sparkCluster.getSparkServiceAccount()).thenReturn("default");
        // Act: Call the method under test
        DriverSpec driverSpec = ApplicationSubmissionHelper.getDriverSpec(request, appConfig, parentQueue, sparkCluster);
        // Assert: Validate the results
        assertNotNull(driverSpec);
        assertEquals("default", driverSpec.getServiceAccount());
    }

    @Test
    void testGetDriverSpec_withDriverAndLabels() {
        // Arrange: Set up the mock request with a driver and labels
        DriverSpec driver = mock(DriverSpec.class);
        Map<String, String> labels = new HashMap<>();
        labels.put(Constants.DAG_NAME_LABEL, "testDAG");
        labels.put(Constants.TASK_NAME_LABEL, "testTask");
        when(request.getDriver()).thenReturn(driver);
        when(driver.getLabels()).thenReturn(labels);
        when(sparkCluster.getSparkServiceAccount()).thenReturn("default");
        // Act: Call the method under test
        DriverSpec driverSpec = ApplicationSubmissionHelper.getDriverSpec(request, appConfig, parentQueue, sparkCluster);
        // Assert: Validate the results
        assertNotNull(driverSpec);
        assertEquals("default", driverSpec.getServiceAccount());
        assertEquals(labels, driverSpec.getLabels());
    }

//     @Test
//     void testGetDriverSpec_withDriverVolumesPriority() {
//         // Arrange: Set up the request with driver volumes
//         DriverSpec driver = mock(DriverSpec.class);
//         // Updated to return List<VolumeMount>
//         when(driver.getVolumeMounts()).thenReturn(List.of(new VolumeMount("mount1")));
//         // Updated to return List<Volume>
//         when(request.getVolumes()).thenReturn(List.of(new Volume("volume1")));
//         when(sparkCluster.getDriver()).thenReturn(mock(DriverSpec.class));
//         // Act: Call the method under test
//         DriverSpec driverSpec = ApplicationSubmissionHelper.getDriverSpec(request, appConfig, parentQueue, sparkCluster);
//         // Assert: Validate the results
//         // Updated to compare with List
//         assertEquals(List.of(new VolumeMount("mount1")), driverSpec.getVolumeMounts());
//     }

//     @Test
//     void testGetDriverSpec_withSparkClusterVolumes() {
//         // Arrange: Set up the request with no driver volumes
//         when(request.getDriver()).thenReturn(null);
//         when(sparkCluster.getDriver()).thenReturn(mock(DriverSpec.class));
//         // Updated to return List<VolumeMount>
//         when(sparkCluster.getDriver().getVolumeMounts()).thenReturn(List.of(new VolumeMount("mount2")));
//         // Updated to return List<Volume>
//         when(request.getVolumes()).thenReturn(List.of(new Volume("volume1")));
//         // Act: Call the method under test
//         DriverSpec driverSpec = ApplicationSubmissionHelper.getDriverSpec(request, appConfig, parentQueue, sparkCluster);
//         // Assert: Validate the results
//         // Updated to compare with List
//         assertEquals(List.of(new VolumeMount("mount2")), driverSpec.getVolumeMounts());
//     }

    @Test
    void testGetDriverSpec_withDriverCoreAndMemory() {
        // Arrange: Set up the request with driver core and memory values
        DriverSpec driver = mock(DriverSpec.class);
        when(driver.getCores()).thenReturn(4);
        when(driver.getMemory()).thenReturn("4G");
        when(request.getDriver()).thenReturn(driver);
        when(sparkCluster.getSparkServiceAccount()).thenReturn("default");
        // Act: Call the method under test
        DriverSpec driverSpec = ApplicationSubmissionHelper.getDriverSpec(request, appConfig, parentQueue, sparkCluster);
        // Assert: Validate the results
        assertEquals(4, driverSpec.getCores());
        assertEquals("4G", driverSpec.getMemory());
    }

    @Test
    void testGetDriverSpec_withAffinitySettings() {
        // Arrange: Set up the request with an empty driver
        when(request.getDriver()).thenReturn(mock(DriverSpec.class));
        when(sparkCluster.getSparkServiceAccount()).thenReturn("default");
        // Simulate that there are no affinity settings
        when(request.getDriver().getAffinity()).thenReturn(null);
        // Act: Call the method under test
        DriverSpec driverSpec = ApplicationSubmissionHelper.getDriverSpec(request, appConfig, parentQueue, sparkCluster);
        // Assert: Validate the results
        // Check if affinity is set
        assertNotNull(driverSpec.getAffinity());
    }

    @Test
    void testGetDriverSpec_withLargeCoreAndMemoryValues() {
        // Arrange: Set up the request with large core and memory values
        DriverSpec driver = mock(DriverSpec.class);
        when(driver.getCores()).thenReturn(100);
        when(driver.getMemory()).thenReturn("100G");
        when(request.getDriver()).thenReturn(driver);
        when(sparkCluster.getSparkServiceAccount()).thenReturn("default");
        // Act: Call the method under test
        DriverSpec driverSpec = ApplicationSubmissionHelper.getDriverSpec(request, appConfig, parentQueue, sparkCluster);
        // Assert: Validate the results
        assertEquals(100, driverSpec.getCores());
        assertEquals("100G", driverSpec.getMemory());
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
│  └─ ApplicationSubmissionHelper_getDriverSpec_Test ✔
│     ├─ testGetDriverSpec_withAffinitySettings() ✘ Cannot invoke "java.util.List.stream()" because the return value of "com.apple.spark.AppConfig.getQueues()" is null
│     ├─ testGetDriverSpec_withValidRequest() ✘ Cannot invoke "java.util.List.stream()" because the return value of "com.apple.spark.AppConfig.getQueues()" is null
│     ├─ testGetDriverSpec_withNullDriver() ✘ Cannot invoke "java.util.List.stream()" because the return value of "com.apple.spark.AppConfig.getQueues()" is null
│     ├─ testGetDriverSpec_withDriverCoreAndMemory() ✘ Cannot invoke "java.util.List.stream()" because the return value of "com.apple.spark.AppConfig.getQueues()" is null
│     ├─ testGetDriverSpec_withLargeCoreAndMemoryValues() ✘ Cannot invoke "java.util.List.stream()" because the return value of "com.apple.spark.AppConfig.getQueues()" is null
│     └─ testGetDriverSpec_withDriverAndLabels() ✘ Cannot invoke "java.util.List.stream()" because the return value of "com.apple.spark.AppConfig.getQueues()" is null
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Failures (6):
  JUnit Jupiter:ApplicationSubmissionHelper_getDriverSpec_Test:testGetDriverSpec_withAffinitySettings()
    MethodSource [className = 'com.apple.spark.core.ApplicationSubmissionHelper_getDriverSpec_Test', methodName = 'testGetDriverSpec_withAffinitySettings', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "java.util.List.stream()" because the return value of "com.apple.spark.AppConfig.getQueues()" is null
       com.apple.spark.core.ApplicationSubmissionHelper.getDriverCPUBufferForQueue(ApplicationSubmissionHelper.java:507)
       com.apple.spark.core.ApplicationSubmissionHelper.getDriverSpec(ApplicationSubmissionHelper.java:438)
       com.apple.spark.core.ApplicationSubmissionHelper_getDriverSpec_Test.testGetDriverSpec_withAffinitySettings(ApplicationSubmissionHelper_getDriverSpec_Test.java:143)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:727)
       org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
       org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
       [...]
  JUnit Jupiter:ApplicationSubmissionHelper_getDriverSpec_Test:testGetDriverSpec_withValidRequest()
    MethodSource [className = 'com.apple.spark.core.ApplicationSubmissionHelper_getDriverSpec_Test', methodName = 'testGetDriverSpec_withValidRequest', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "java.util.List.stream()" because the return value of "com.apple.spark.AppConfig.getQueues()" is null
       com.apple.spark.core.ApplicationSubmissionHelper.getDriverCPUBufferForQueue(ApplicationSubmissionHelper.java:507)
       com.apple.spark.core.ApplicationSubmissionHelper.getDriverSpec(ApplicationSubmissionHelper.java:438)
       com.apple.spark.core.ApplicationSubmissionHelper_getDriverSpec_Test.testGetDriverSpec_withValidRequest(ApplicationSubmissionHelper_getDriverSpec_Test.java:52)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:727)
       org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
       org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
       [...]
  JUnit Jupiter:ApplicationSubmissionHelper_getDriverSpec_Test:testGetDriverSpec_withNullDriver()
    MethodSource [className = 'com.apple.spark.core.ApplicationSubmissionHelper_getDriverSpec_Test', methodName = 'testGetDriverSpec_withNullDriver', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "java.util.List.stream()" because the return value of "com.apple.spark.AppConfig.getQueues()" is null
       com.apple.spark.core.ApplicationSubmissionHelper.getDriverCPUBufferForQueue(ApplicationSubmissionHelper.java:507)
       com.apple.spark.core.ApplicationSubmissionHelper.getDriverSpec(ApplicationSubmissionHelper.java:438)
       com.apple.spark.core.ApplicationSubmissionHelper_getDriverSpec_Test.testGetDriverSpec_withNullDriver(ApplicationSubmissionHelper_getDriverSpec_Test.java:64)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:727)
       org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
       org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
       [...]
  JUnit Jupiter:ApplicationSubmissionHelper_getDriverSpec_Test:testGetDriverSpec_withDriverCoreAndMemory()
    MethodSource [className = 'com.apple.spark.core.ApplicationSubmissionHelper_getDriverSpec_Test', methodName = 'testGetDriverSpec_withDriverCoreAndMemory', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "java.util.List.stream()" because the return value of "com.apple.spark.AppConfig.getQueues()" is null
       com.apple.spark.core.ApplicationSubmissionHelper.getDriverCPUBufferForQueue(ApplicationSubmissionHelper.java:507)
       com.apple.spark.core.ApplicationSubmissionHelper.getDriverSpec(ApplicationSubmissionHelper.java:438)
       com.apple.spark.core.ApplicationSubmissionHelper_getDriverSpec_Test.testGetDriverSpec_withDriverCoreAndMemory(ApplicationSubmissionHelper_getDriverSpec_Test.java:129)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:727)
       org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
       org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
       [...]
  JUnit Jupiter:ApplicationSubmissionHelper_getDriverSpec_Test:testGetDriverSpec_withLargeCoreAndMemoryValues()
    MethodSource [className = 'com.apple.spark.core.ApplicationSubmissionHelper_getDriverSpec_Test', methodName = 'testGetDriverSpec_withLargeCoreAndMemoryValues', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "java.util.List.stream()" because the return value of "com.apple.spark.AppConfig.getQueues()" is null
       com.apple.spark.core.ApplicationSubmissionHelper.getDriverCPUBufferForQueue(ApplicationSubmissionHelper.java:507)
       com.apple.spark.core.ApplicationSubmissionHelper.getDriverSpec(ApplicationSubmissionHelper.java:438)
       com.apple.spark.core.ApplicationSubmissionHelper_getDriverSpec_Test.testGetDriverSpec_withLargeCoreAndMemoryValues(ApplicationSubmissionHelper_getDriverSpec_Test.java:158)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:727)
       org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
       org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
       [...]
  JUnit Jupiter:ApplicationSubmissionHelper_getDriverSpec_Test:testGetDriverSpec_withDriverAndLabels()
    MethodSource [className = 'com.apple.spark.core.ApplicationSubmissionHelper_getDriverSpec_Test', methodName = 'testGetDriverSpec_withDriverAndLabels', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "java.util.List.stream()" because the return value of "com.apple.spark.AppConfig.getQueues()" is null
       com.apple.spark.core.ApplicationSubmissionHelper.getDriverCPUBufferForQueue(ApplicationSubmissionHelper.java:507)
       com.apple.spark.core.ApplicationSubmissionHelper.getDriverSpec(ApplicationSubmissionHelper.java:438)
       com.apple.spark.core.ApplicationSubmissionHelper_getDriverSpec_Test.testGetDriverSpec_withDriverAndLabels(ApplicationSubmissionHelper_getDriverSpec_Test.java:81)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:727)
       org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
       org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
       [...]

Test run finished after 583 ms
[         4 containers found      ]
[         0 containers skipped    ]
[         4 containers started    ]
[         0 containers aborted    ]
[         4 containers successful ]
[         0 containers failed     ]
[         6 tests found           ]
[         0 tests skipped         ]
[         6 tests started         ]
[         0 tests aborted         ]
[         0 tests successful      ]
[         6 tests failed          ]


```


And you can use the following related information to assist you in your analysis and correction:

return type: 
com.apple.spark.operator.DriverSpec

dependent classes: 
class `com.apple.spark.core.Constants`:
	dependent fields:
		java.lang.String com.apple.spark.core.Constants.KUBE_CLUSTER_AUTOSCALER_SCALE_IN_ANNOTATION_KEY;
		java.lang.String com.apple.spark.core.Constants.TASK_NAME_LABEL;
		java.lang.String com.apple.spark.core.Constants.DAG_NAME_LABEL;
		java.lang.String com.apple.spark.core.Constants.KUBE_CLUSTER_AUTOSCALER_SCALE_IN_ANNOTATION_VALUE;
	
class `com.apple.spark.core.SparkConstants`:
	dependent fields:
		java.lang.Integer com.apple.spark.core.SparkConstants.CORE_LIMIT_RATIO;
	
class `com.apple.spark.core.ApplicationSubmissionHelper`:
	dependent fields:
		org.slf4j.Logger com.apple.spark.core.ApplicationSubmissionHelper.logger;
	related functions:
		method `public static String getImage(AppConfig, SubmitApplicationRequest, String, String, String)` returns `java.lang.String`, related with `com.apple.spark.core.ApplicationSubmissionHelper.getDriverSpec(SubmitApplicationRequest, AppConfig, String, SparkCluster)`;
		method `public static List<Volume> getVolumes(SubmitApplicationRequest, AppConfig.SparkCluster)` returns `java.util.List<com.apple.spark.operator.Volume>`, related with `com.apple.spark.core.ApplicationSubmissionHelper.getDriverSpec(SubmitApplicationRequest, AppConfig, String, SparkCluster)`;
		method `public static ExecutorSpec getExecutorSpec(SubmitApplicationRequest, AppConfig, String, SparkCluster)` returns `com.apple.spark.operator.ExecutorSpec`, related with `com.apple.spark.core.ApplicationSubmissionHelper.getDriverSpec(SubmitApplicationRequest, AppConfig, String, SparkCluster)`;
		method `public static SubmitApplicationRequest parseSubmitRequest(String, String)` returns `com.apple.spark.api.SubmitApplicationRequest`, related with `com.apple.spark.core.ApplicationSubmissionHelper.getDriverSpec(SubmitApplicationRequest, AppConfig, String, SparkCluster)`;
class `com.apple.spark.operator.NodeSelectorOperator`:
	dependent fields:
		com.apple.spark.operator.NodeSelectorOperator com.apple.spark.operator.NodeSelectorOperator.NodeSelectorOpIn;
	
class `com.apple.spark.api.SubmissionSummary`:
	related functions:
		method `public void copyFrom(SparkApplication)` returns `void`, related with `com.apple.spark.core.ApplicationSubmissionHelper.getDriverSpec(SubmitApplicationRequest, AppConfig, String, SparkCluster)`;
class `com.apple.spark.rest.ApplicationSubmissionRest`:
	related functions:
		method `private SubmitApplicationResponse submitSparkCRD(SparkCluster, String, SparkApplicationSpec, SubmitApplicationRequest, String, String)` returns `com.apple.spark.api.SubmitApplicationResponse`, related with `com.apple.spark.core.ApplicationSubmissionHelper.getDriverSpec(SubmitApplicationRequest, AppConfig, String, SparkCluster)`;
class `com.apple.spark.core.RunningApplicationMonitor`:
	related functions:
		method `public static long getMaxRunningMillis(SparkApplication)` returns `long`, related with `com.apple.spark.core.ApplicationSubmissionHelper.getDriverSpec(SubmitApplicationRequest, AppConfig, String, SparkCluster)`;
class `com.apple.spark.core.QueueTokenVerifier`:
	related functions:
		method `public static void verify(String, List<String>, String)` returns `void`, related with `com.apple.spark.core.ApplicationSubmissionHelper.getDriverSpec(SubmitApplicationRequest, AppConfig, String, SparkCluster)`;



Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 
