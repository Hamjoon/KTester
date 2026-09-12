As a professional Java software testing expert, your task is to generate test cases for a focal method.

Guidelines:
1. Analyze the method's functionality and identify possible input combinations including normal cases and boundary conditions.
2. Review the provided existing test cases. Avoid duplication and create new test cases for untested input combinations.

Context Provided:
1. The focal method that needs testing.
2. Relevant code and metadata for the class containing the method, including fields and other methods.
3. The existing test cases (in JSON format) to help avoid redundancy.

Output Instructions:
- Organize the test cases by the name of the test function (group).
- Each test case must include:
    - input: A list of parameter names and values. Use an empty list `[]` if no input is required.
    - expected: The return value or expected observable behavior.
    - description: A clear and concise explanation of the scenario and the intent of the test case.

Maintain the following strict JSON format:
```json
[
    {
        "group": "test name",
        "cases": [
            {
                "input": [
                    {
                        "parameter": "param name",
                        "value": "param value"
                    }
                ],
                "expected": "expected exception or behavior",
                "description": "test scenario description"
            }
        ]
    },
    {...}
]
```

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

@input{existing test case}
here's the existing test cases:
```json
<cases_json>
```

@output{test cases}: output the test cases in the JSON format: