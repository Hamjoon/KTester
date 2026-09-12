You are a professional Java software testing expert. Your task is to generate high-quality unit test code for a focal method, using **JUnit5** and **Mockito** libraries. 

You'll be provided with:
1. The focal method to test.
2. The target class's structure (fields and methods).
3. A pre-defined test class framework with `@Before`/`@After` methods.
4. Test cases in JSON format.
5. Any relevant context.

**Requirements:**

Code Quality:
- Include clear comments explaining each test scenario.
- Ensure code is clean, understandable, and maintainable.
- Do not directly access private methods or fields. **Use reflection if the focal method is private.**
- Include all necessary imports; code must be free of syntax errors.

**Test Structure:**
- Follow the given test class framework; do not modify `@Before`/`@After` methods.
- Create one test function per test case group, ensuring all cases are covered.
- Use parameterized tests where appropriate.
- Reinitialize variables inside test functions if `@Setup` values don't fit.
- Properly mock and set up dependencies.

@input{focal method and target class}
Here is the code of focal method, `getDriverSpec(SubmitApplicationRequest, AppConfig, String, SparkCluster)`, with the focal class `ApplicationSubmissionHelper`:
```Java
package com.apple.spark.core;
import com.apple.spark.operator.NodeAffinity;
import static com.apple.spark.core.BatchSchedulerConstants.YUNIKORN_ROOT_QUEUE;
import com.apple.spark.api.SubmitApplicationRequest;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import java.util.HashMap;
import static com.apple.spark.core.BatchSchedulerConstants.YUNIKORN_SPARK_DEFAULT_QUEUE;
import java.util.ArrayList;
import com.apple.spark.util.ExceptionUtils;
import org.slf4j.Logger;
import com.apple.spark.operator.SparkApplicationSpec;
import static com.apple.spark.core.SparkConstants.DRIVER_MEM_BUFFER_RATIO;
import static com.apple.spark.core.SparkConstants.EXECUTOR_MEM_BUFFER_RATIO;
import com.apple.spark.operator.ExecutorSpec;
import static com.apple.spark.core.BatchSchedulerConstants.PLACEHOLDER_TIMEOUT_IN_SECONDS;
import org.apache.commons.lang3.StringUtils;
import com.apple.spark.operator.NodeSelectorTerm;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.apple.spark.operator.DriverSpec;
import com.apple.spark.operator.BatchSchedulerConfiguration;
import java.util.List;
import java.util.UUID;
import static com.apple.spark.core.SparkConstants.DRIVER_CPU_BUFFER_RATIO;
import org.slf4j.LoggerFactory;
import com.apple.spark.operator.NodeSelectorOperator;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.Optional;
import com.apple.spark.AppConfig;
import com.apple.spark.operator.RequiredDuringSchedulingIgnoredDuringExecutionTerm;
import com.apple.spark.operator.NodeSelectorRequirement;
import static com.apple.spark.core.SparkConstants.EXECUTOR_CPU_BUFFER_RATIO;
import io.fabric8.kubernetes.api.model.PodDNSConfig;
import com.apple.spark.operator.Volume;
import javax.ws.rs.core.Response;
import static com.apple.spark.core.Constants.*;
import io.fabric8.kubernetes.api.model.PodDNSConfigOption;
import com.apple.spark.operator.SparkUIConfiguration;
import com.apple.spark.operator.Affinity;
import javax.ws.rs.WebApplicationException;
import com.apple.spark.AppConfig.SparkCluster;
import java.util.Map;
import java.util.Arrays;
import static com.apple.spark.core.SparkConstants.CORE_LIMIT_RATIO;
public class ApplicationSubmissionHelper {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationSubmissionHelper.class);
    public static DriverSpec getDriverSpec(SubmitApplicationRequest request, AppConfig appConfig, String parentQueue, SparkCluster sparkCluster) {
        DriverSpec driverSpec = new DriverSpec();
        if (request.getDriver() != null) {
            driverSpec.copyFrom(request.getDriver());
            Map<String, String> driverLabels = request.getDriver().getLabels();
            if (driverLabels != null) {
                Map<String, String> labels = new HashMap<>();
                if (driverLabels.containsKey(DAG_NAME_LABEL)) {
                    labels.put(DAG_NAME_LABEL, KubernetesHelper.normalizeLabelValue(driverLabels.get(DAG_NAME_LABEL)));
                }
                if (driverLabels.containsKey(TASK_NAME_LABEL)) {
                    labels.put(TASK_NAME_LABEL, KubernetesHelper.normalizeLabelValue(driverLabels.get(TASK_NAME_LABEL)));
                }
                driverLabels.putAll(labels);
                driverSpec.setLabels(driverLabels);
            }
        }
        // if there's any volumes and volume mounts specified, add them to driver spec
        // mounts specified in requests have higher priority
        if (((request.getDriver() != null) && (request.getVolumes() != null)) && (!request.getVolumes().isEmpty())) {
            driverSpec.setVolumeMounts(request.getDriver().getVolumeMounts());
        } else if (((sparkCluster.getDriver() != null) && (sparkCluster.getVolumes() != null)) && (!sparkCluster.getVolumes().isEmpty())) {
            driverSpec.setVolumeMounts(sparkCluster.getDriver().getVolumeMounts());
        }
        double driverCpuBufferRatioQueue = getDriverCPUBufferForQueue(appConfig, parentQueue);
        double driverMemBufferRatioQueue = getDriverMemBufferForQueue(appConfig, parentQueue);
        if ((driverSpec.getServiceAccount() == null) || driverSpec.getServiceAccount().isEmpty()) {
            driverSpec.setServiceAccount(sparkCluster.getSparkServiceAccount());
        }
        int originalDriverCores = driverSpec.getCores();
        long adjustedDriverCores = Math.round(originalDriverCores * driverCpuBufferRatioQueue);
        driverSpec.setCores(((int) (adjustedDriverCores)));
        logger.info("Setting driver core to {}", adjustedDriverCores);
        if ((driverSpec.getCoreLimit() == null) || driverSpec.getCoreLimit().isEmpty()) {
            driverSpec.setCoreLimit(String.format("%sm", Math.ceil((driverSpec.getCores() * CORE_LIMIT_RATIO) * driverCpuBufferRatioQueue)));
        } else {
            long originalDriverCoreLimit = Long.parseLong(driverSpec.getCoreLimit());
            long adjustedDriverCoreLimit = Math.round(originalDriverCoreLimit * driverCpuBufferRatioQueue);
            driverSpec.setCoreLimit(String.valueOf(adjustedDriverCoreLimit));
            logger.info("Setting driver core limits to {}", adjustedDriverCoreLimit);
        }
        long originalDriverMem = getMemNumFromRequestStr(driverSpec.getMemory());
        long adjustedDriverMem = ((long) (Math.ceil(originalDriverMem * driverMemBufferRatioQueue)));
        String memUnit = getMemUnitFromRequestStr(driverSpec.getMemory());
        driverSpec.setMemory(adjustedDriverMem + memUnit);
        logger.info("Setting driver memory to {}{}", adjustedDriverMem, memUnit);
        // set annotations to driver pod to avoid scale-in from KubeC
        Map<String, String> driverAnnotations = (driverSpec.getAnnotations() == null) ? new HashMap<>() : driverSpec.getAnnotations();
        driverAnnotations.put(KUBE_CLUSTER_AUTOSCALER_SCALE_IN_ANNOTATION_KEY, KUBE_CLUSTER_AUTOSCALER_SCALE_IN_ANNOTATION_VALUE);
        driverSpec.setAnnotations(driverAnnotations);
        // Driver affinity is for all drivers to share the same node group, with only scaling up
        // this is to prevent driver pods from being killed when nodes are being scaling down
        if (driverSpec.getAffinity() == null) {
            List<String> driverNodeLabelValues = getDriverNodeLabelValuesForQueue(appConfig, parentQueue);
            String nodeLabelKey = getDriverNodeLabelKeyForQueue(appConfig, parentQueue);
            if ((driverNodeLabelValues != null) && (driverNodeLabelValues.size() > 0)) {
                // set hard requiredDuringSchedulingIgnoredDuringExecutionTerm to driver
                NodeSelectorRequirement nodeSelectorRequirement = new NodeSelectorRequirement(nodeLabelKey, NodeSelectorOperator.NodeSelectorOpIn.toString(), driverNodeLabelValues.toArray(new String[0]));// set "spark_driver" as nodeselctor
    
                NodeSelectorTerm nodeSelectorTermOnDriver = new NodeSelectorTerm(new NodeSelectorRequirement[]{ nodeSelectorRequirement });
                RequiredDuringSchedulingIgnoredDuringExecutionTerm requiredSchedulingTerm = new RequiredDuringSchedulingIgnoredDuringExecutionTerm(new NodeSelectorTerm[]{ nodeSelectorTermOnDriver });
                // attach required affinity only to driver
                NodeAffinity nodeAffinity = new NodeAffinity(requiredSchedulingTerm);
                driverSpec.setAffinity(new Affinity(nodeAffinity));
            }
        }
        return driverSpec;
    }
static Map<String, String> applyFeatureGate(SubmitApplicationRequest request, Map<String, String> conf);
    public static String generateSubmissionId(String clusterId);
    public static String generateSubmissionId(String clusterId, String submissionIdSuffix);
    public static String getClusterIdFromSubmissionId(String submissionId);
    private static double getDriverCPUBufferForQueue(AppConfig appConfig, String queue);
    private static double getDriverMemBufferForQueue(AppConfig appConfig, String queue);
    private static String getDriverNodeLabelKeyForQueue(AppConfig appConfig, String queue);
    private static List<String> getDriverNodeLabelValuesForQueue(AppConfig appConfig, String queue);
    public static ExecutorSpec getExecutorSpec(SubmitApplicationRequest request, AppConfig appConfig, String parentQueue, SparkCluster sparkCluster);
    public static String getImage(AppConfig appConfig, SubmitApplicationRequest request, String type, String sparkVersion, String proxyUser);
    private static long getMemNumFromRequestStr(String memStr);
    private static String getMemUnitFromRequestStr(String memStr);
    public static String getProxyUser(String user, String dagUser);
    public static Map<String, String> getSparkConf(String submissionId, SubmitApplicationRequest request, AppConfig appConfig, AppConfig.SparkCluster sparkCluster);
    public static SparkUIConfiguration getSparkUIConfiguration(String submissionId, AppConfig.SparkCluster sparkCluster);
    public static String getType(String specifiedType, String mainClass);
    public static List<Volume> getVolumes(SubmitApplicationRequest request, AppConfig.SparkCluster sparkCluster);
    public static BatchSchedulerConfiguration getYuniKornSchedulerConfig(String queue);
    public static boolean looksLikeFilePath(String requestBody);
    public static SubmitApplicationRequest parseSubmitRequest(String requestBody, String contentType);
    public static void populateEnv(SparkApplicationSpec sparkSpec, SubmitApplicationRequest request, AppConfig.SparkCluster sparkCluster);
    public static void validateQueueToken(String queue, String queueToken, AppConfig appConfig);
}
```

@input{initial test class framework}
```Java
<initial_class>
```

@input{existing test case}
here's the existing test cases:
```json
<cases_json>
```


@input{context information}

return type: 
com.apple.spark.operator.DriverSpec

dependent classes: 
class `com.apple.spark.core.Constants`:
	dependent fields:
		java.lang.String com.apple.spark.core.Constants.TASK_NAME_LABEL;
		java.lang.String com.apple.spark.core.Constants.KUBE_CLUSTER_AUTOSCALER_SCALE_IN_ANNOTATION_VALUE;
		java.lang.String com.apple.spark.core.Constants.DAG_NAME_LABEL;
		java.lang.String com.apple.spark.core.Constants.KUBE_CLUSTER_AUTOSCALER_SCALE_IN_ANNOTATION_KEY;
	
class `com.apple.spark.core.SparkConstants`:
	dependent fields:
		java.lang.Integer com.apple.spark.core.SparkConstants.CORE_LIMIT_RATIO;
	
class `com.apple.spark.core.ApplicationSubmissionHelper`:
	dependent fields:
		org.slf4j.Logger com.apple.spark.core.ApplicationSubmissionHelper.logger;
	related functions:
		method `public static ExecutorSpec getExecutorSpec(SubmitApplicationRequest, AppConfig, String, SparkCluster)` returns `com.apple.spark.operator.ExecutorSpec`, related with `com.apple.spark.core.ApplicationSubmissionHelper.getDriverSpec(SubmitApplicationRequest, AppConfig, String, SparkCluster)`;
		method `public static String getImage(AppConfig, SubmitApplicationRequest, String, String, String)` returns `java.lang.String`, related with `com.apple.spark.core.ApplicationSubmissionHelper.getDriverSpec(SubmitApplicationRequest, AppConfig, String, SparkCluster)`;
		method `public static List<Volume> getVolumes(SubmitApplicationRequest, AppConfig.SparkCluster)` returns `java.util.List<com.apple.spark.operator.Volume>`, related with `com.apple.spark.core.ApplicationSubmissionHelper.getDriverSpec(SubmitApplicationRequest, AppConfig, String, SparkCluster)`;
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



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.