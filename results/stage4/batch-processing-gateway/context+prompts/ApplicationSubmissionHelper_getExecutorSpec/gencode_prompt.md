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
Here is the code of focal method, `getExecutorSpec(SubmitApplicationRequest, AppConfig, String, SparkCluster)`, with the focal class `ApplicationSubmissionHelper`:
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
    public static ExecutorSpec getExecutorSpec(SubmitApplicationRequest request, AppConfig appConfig, String parentQueue, SparkCluster sparkCluster) {
        ExecutorSpec executorSpec = new ExecutorSpec();
        if (request.getExecutor() != null) {
            executorSpec.copyFrom(request.getExecutor());
            Map<String, String> executorLabels = request.getExecutor().getLabels();
            if (executorLabels != null) {
                Map<String, String> labels = new HashMap<>();
                if (executorLabels.containsKey(DAG_NAME_LABEL)) {
                    labels.put(DAG_NAME_LABEL, KubernetesHelper.normalizeLabelValue(executorLabels.get(DAG_NAME_LABEL)));
                }
                if (executorLabels.containsKey(TASK_NAME_LABEL)) {
                    labels.put(TASK_NAME_LABEL, KubernetesHelper.normalizeLabelValue(executorLabels.get(TASK_NAME_LABEL)));
                }
                executorLabels.putAll(labels);
                executorSpec.setLabels(executorLabels);
            }
        }
        // if there's any volumes and volume mounts specified, add them to executor spec
        // mounts specified in requests have higher priority
        if (((request.getExecutor() != null) && (request.getVolumes() != null)) && (!request.getVolumes().isEmpty())) {
            executorSpec.setVolumeMounts(request.getExecutor().getVolumeMounts());
        } else if (((sparkCluster.getExecutor() != null) && (sparkCluster.getVolumes() != null)) && (!sparkCluster.getVolumes().isEmpty())) {
            executorSpec.setVolumeMounts(sparkCluster.getExecutor().getVolumeMounts());
        }
        double executorCpuBufferRatioQueue = getExecutorCPUBufferForQueue(appConfig, parentQueue);
        double executorMemBufferRatioQueue = getExecutorMemBufferForQueue(appConfig, parentQueue);
        int originalExecutorCores = executorSpec.getCores();
        long adjustedExecutorCores = Math.round(originalExecutorCores * executorCpuBufferRatioQueue);
        executorSpec.setCores(((int) (adjustedExecutorCores)));
        logger.info("Setting executor core to {}", adjustedExecutorCores);
        if ((executorSpec.getCoreLimit() == null) || executorSpec.getCoreLimit().isEmpty()) {
            executorSpec.setCoreLimit(String.format("%sm", Math.ceil((executorSpec.getCores() * CORE_LIMIT_RATIO) * executorCpuBufferRatioQueue)));
        } else {
            long originalExecutorCoreLimit = Long.parseLong(executorSpec.getCoreLimit());
            long adjustedExecutorCoreLimit = Math.round(originalExecutorCoreLimit * executorCpuBufferRatioQueue);
            executorSpec.setCoreLimit(String.valueOf(adjustedExecutorCoreLimit));
            logger.info("Setting executor core limits to {}", adjustedExecutorCoreLimit);
        }
        long originalExecutorMem = getMemNumFromRequestStr(executorSpec.getMemory());
        long adjustedExecutorMem = ((long) (Math.ceil(originalExecutorMem * executorMemBufferRatioQueue)));
        String memUnit = getMemUnitFromRequestStr(executorSpec.getMemory());
        executorSpec.setMemory(adjustedExecutorMem + memUnit);
        logger.info("Setting executor memory to {}{}", adjustedExecutorMem, memUnit);
        // set annotations to executor pod to avoid scale-in from KubeCA
        if (executorSpec.getAnnotations() == null) {
            Map<String, String> executorAnnotations = new HashMap<>();
            executorAnnotations.put(KUBE_CLUSTER_AUTOSCALER_SCALE_IN_ANNOTATION_KEY, KUBE_CLUSTER_AUTOSCALER_SCALE_IN_ANNOTATION_VALUE);
            executorSpec.setAnnotations(executorAnnotations);
        }
        if (executorSpec.getAffinity() == null) {
            List<String> executorNodeLabelValues = new ArrayList<>();
            if (request.getSpotInstance()) {
                executorNodeLabelValues = getExecutorSpotNodeLabelValuesForQueue(appConfig, parentQueue);
            } else {
                executorNodeLabelValues = getExecutorNodeLabelValuesForQueue(appConfig, parentQueue);
            }
            String nodeLabelKey = getExecutorNodeLabelKeyForQueue(appConfig, parentQueue);
            if ((executorNodeLabelValues != null) && (executorNodeLabelValues.size() > 0)) {
                // set hard requiredDuringSchedulingIgnoredDuringExecutionTerm to executor
                NodeSelectorRequirement nodeSelectorRequirement = new NodeSelectorRequirement(nodeLabelKey, NodeSelectorOperator.NodeSelectorOpIn.toString(), executorNodeLabelValues.toArray(new String[0]));
                NodeSelectorTerm nodeSelectorTermOnExecutor = new NodeSelectorTerm(new NodeSelectorRequirement[]{ nodeSelectorRequirement });
                RequiredDuringSchedulingIgnoredDuringExecutionTerm requiredSchedulingTermOnExecutor = new RequiredDuringSchedulingIgnoredDuringExecutionTerm(new NodeSelectorTerm[]{ nodeSelectorTermOnExecutor });
                // attach required affinity only to executor
                NodeAffinity nodeAffinityOnExecutor = new NodeAffinity(requiredSchedulingTermOnExecutor);
                executorSpec.setAffinity(new Affinity(nodeAffinityOnExecutor));
            }
        }
        PodDNSConfig executorPodDNSConfig = new PodDNSConfig();
        executorPodDNSConfig.setOptions(Collections.singletonList(new PodDNSConfigOption(DNS_CONFIG_OPTION_NDOTS_NAME, DNS_CONFIG_OPTION_NDOTS_VALUE)));
        executorSpec.setDnsConfig(executorPodDNSConfig);
        return executorSpec;
    }
static Map<String, String> applyFeatureGate(SubmitApplicationRequest request, Map<String, String> conf);
    public static String generateSubmissionId(String clusterId);
    public static String generateSubmissionId(String clusterId, String submissionIdSuffix);
    public static String getClusterIdFromSubmissionId(String submissionId);
    public static DriverSpec getDriverSpec(SubmitApplicationRequest request, AppConfig appConfig, String parentQueue, SparkCluster sparkCluster);
    private static double getExecutorCPUBufferForQueue(AppConfig appConfig, String queue);
    private static double getExecutorMemBufferForQueue(AppConfig appConfig, String queue);
    private static String getExecutorNodeLabelKeyForQueue(AppConfig appConfig, String queue);
    private static List<String> getExecutorNodeLabelValuesForQueue(AppConfig appConfig, String queue);
    private static List<String> getExecutorSpotNodeLabelValuesForQueue(AppConfig appConfig, String queue);
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
com.apple.spark.operator.ExecutorSpec

dependent classes: 
class `com.apple.spark.core.Constants`:
	dependent fields:
		java.lang.String com.apple.spark.core.Constants.DNS_CONFIG_OPTION_NDOTS_NAME;
		java.lang.String com.apple.spark.core.Constants.TASK_NAME_LABEL;
		java.lang.String com.apple.spark.core.Constants.DNS_CONFIG_OPTION_NDOTS_VALUE;
		java.lang.String com.apple.spark.core.Constants.KUBE_CLUSTER_AUTOSCALER_SCALE_IN_ANNOTATION_KEY;
		java.lang.String com.apple.spark.core.Constants.KUBE_CLUSTER_AUTOSCALER_SCALE_IN_ANNOTATION_VALUE;
		java.lang.String com.apple.spark.core.Constants.DAG_NAME_LABEL;
	
class `com.apple.spark.core.SparkConstants`:
	dependent fields:
		java.lang.Integer com.apple.spark.core.SparkConstants.CORE_LIMIT_RATIO;
	
class `com.apple.spark.core.ApplicationSubmissionHelper`:
	dependent fields:
		org.slf4j.Logger com.apple.spark.core.ApplicationSubmissionHelper.logger;
	related functions:
		method `public static String getImage(AppConfig, SubmitApplicationRequest, String, String, String)` returns `java.lang.String`, related with `com.apple.spark.core.ApplicationSubmissionHelper.getExecutorSpec(SubmitApplicationRequest, AppConfig, String, SparkCluster)`;
		method `public static DriverSpec getDriverSpec(SubmitApplicationRequest, AppConfig, String, SparkCluster)` returns `com.apple.spark.operator.DriverSpec`, related with `com.apple.spark.core.ApplicationSubmissionHelper.getExecutorSpec(SubmitApplicationRequest, AppConfig, String, SparkCluster)`;
		method `public static SubmitApplicationRequest parseSubmitRequest(String, String)` returns `com.apple.spark.api.SubmitApplicationRequest`, related with `com.apple.spark.core.ApplicationSubmissionHelper.getExecutorSpec(SubmitApplicationRequest, AppConfig, String, SparkCluster)`;
		method `public static void populateEnv(SparkApplicationSpec, SubmitApplicationRequest, AppConfig.SparkCluster)` returns `void`, related with `com.apple.spark.core.ApplicationSubmissionHelper.getExecutorSpec(SubmitApplicationRequest, AppConfig, String, SparkCluster)`;
class `com.apple.spark.operator.NodeSelectorOperator`:
	dependent fields:
		com.apple.spark.operator.NodeSelectorOperator com.apple.spark.operator.NodeSelectorOperator.NodeSelectorOpIn;
	
class `com.apple.spark.api.SubmissionSummary`:
	related functions:
		method `public void copyFrom(SparkApplication)` returns `void`, related with `com.apple.spark.core.ApplicationSubmissionHelper.getExecutorSpec(SubmitApplicationRequest, AppConfig, String, SparkCluster)`;
class `com.apple.spark.rest.ApplicationSubmissionRest`:
	related functions:
		method `private SubmitApplicationResponse submitSparkCRD(SparkCluster, String, SparkApplicationSpec, SubmitApplicationRequest, String, String)` returns `com.apple.spark.api.SubmitApplicationResponse`, related with `com.apple.spark.core.ApplicationSubmissionHelper.getExecutorSpec(SubmitApplicationRequest, AppConfig, String, SparkCluster)`;
class `com.apple.spark.core.RunningApplicationMonitor`:
	related functions:
		method `public static long getMaxRunningMillis(SparkApplication)` returns `long`, related with `com.apple.spark.core.ApplicationSubmissionHelper.getExecutorSpec(SubmitApplicationRequest, AppConfig, String, SparkCluster)`;
class `com.apple.spark.rest.ApplicationGetLogRest`:
	related functions:
		method `public Response getLog(String, String, String, String, String, User)` returns `javax.ws.rs.core.Response`, related with `com.apple.spark.core.ApplicationSubmissionHelper.getExecutorSpec(SubmitApplicationRequest, AppConfig, String, SparkCluster)`;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.