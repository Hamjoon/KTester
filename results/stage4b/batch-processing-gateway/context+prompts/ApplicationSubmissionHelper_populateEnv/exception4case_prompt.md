You are a professional software tester. Write test cases for a focal method in a target class.

**Instructions:**
1. Analyze method behavior and identify ALL possible exception scenarios
2. Design test cases that trigger each identified exception
3. Follow existing test case structure, Avoid duplication and create new test cases for uncovered exception scenarios

Test Case Format:
1. Group test cases by test function name
2. Each test case MUST include:
   - Input parameters with values (use empty list `[]` if none)
   - Expected exception or observable behavior
   - Clear description of test scenario
3. Return empty array if no exception scenarios found

Output Schema (STRICT JSON format):
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
Here is the code of focal method, `populateEnv(SparkApplicationSpec, SubmitApplicationRequest, AppConfig.SparkCluster)`, with the focal class `ApplicationSubmissionHelper`:
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
    
    public static void populateEnv(SparkApplicationSpec sparkSpec, SubmitApplicationRequest request, AppConfig.SparkCluster sparkCluster) {
        if ((sparkCluster.getDriver() != null) && (sparkCluster.getDriver().getEnv() != null)) {
            // there is env in spark cluster driver configure, copy it to spark spec
            if (sparkSpec.getDriver() == null) {
                sparkSpec.setDriver(new DriverSpec());
            }
            if (sparkSpec.getDriver().getEnv() == null) {
                sparkSpec.getDriver().setEnv(new ArrayList<>());
            }
            SparkSpecHelper.copyEnv(sparkCluster.getDriver().getEnv(), sparkSpec.getDriver().getEnv());
        }
        if ((request.getDriver() != null) && (request.getDriver().getEnv() != null)) {
            // there is env in request driver configure, copy it to spark spec
            if (sparkSpec.getDriver() == null) {
                sparkSpec.setDriver(new DriverSpec());
            }
            if (sparkSpec.getDriver().getEnv() == null) {
                sparkSpec.getDriver().setEnv(new ArrayList<>());
            }
            SparkSpecHelper.copyEnv(request.getDriver().getEnv(), sparkSpec.getDriver().getEnv());
        }
        if ((sparkCluster.getExecutor() != null) && (sparkCluster.getExecutor().getEnv() != null)) {
            // there is env in spark cluster executor configure, copy it to spark spec
            if (sparkSpec.getExecutor() == null) {
                sparkSpec.setExecutor(new ExecutorSpec());
            }
            if (sparkSpec.getExecutor().getEnv() == null) {
                sparkSpec.getExecutor().setEnv(new ArrayList<>());
            }
            SparkSpecHelper.copyEnv(sparkCluster.getExecutor().getEnv(), sparkSpec.getExecutor().getEnv());
        }
        if ((request.getExecutor() != null) && (request.getExecutor().getEnv() != null)) {
            // there is env in request executor configure, copy it to spark spec
            if (sparkSpec.getExecutor() == null) {
                sparkSpec.setExecutor(new ExecutorSpec());
            }
            if (sparkSpec.getExecutor().getEnv() == null) {
                sparkSpec.getExecutor().setEnv(new ArrayList<>());
            }
            SparkSpecHelper.copyEnv(request.getExecutor().getEnv(), sparkSpec.getExecutor().getEnv());
        }
    }
static Map<String, String> applyFeatureGate(SubmitApplicationRequest request, Map<String, String> conf);
    public static String generateSubmissionId(String clusterId);
    public static String generateSubmissionId(String clusterId, String submissionIdSuffix);
    public static String getClusterIdFromSubmissionId(String submissionId);
    public static DriverSpec getDriverSpec(SubmitApplicationRequest request, AppConfig appConfig, String parentQueue, SparkCluster sparkCluster);
    public static ExecutorSpec getExecutorSpec(SubmitApplicationRequest request, AppConfig appConfig, String parentQueue, SparkCluster sparkCluster);
    public static String getImage(AppConfig appConfig, SubmitApplicationRequest request, String type, String sparkVersion, String proxyUser);
    public static String getProxyUser(String user, String dagUser);
    public static Map<String, String> getSparkConf(String submissionId, SubmitApplicationRequest request, AppConfig appConfig, AppConfig.SparkCluster sparkCluster);
    public static SparkUIConfiguration getSparkUIConfiguration(String submissionId, AppConfig.SparkCluster sparkCluster);
    public static String getType(String specifiedType, String mainClass);
    public static List<Volume> getVolumes(SubmitApplicationRequest request, AppConfig.SparkCluster sparkCluster);
    public static BatchSchedulerConfiguration getYuniKornSchedulerConfig(String queue);
    public static boolean looksLikeFilePath(String requestBody);
    public static SubmitApplicationRequest parseSubmitRequest(String requestBody, String contentType);
    public static void validateQueueToken(String queue, String queueToken, AppConfig appConfig);
}
```
@input{existing test case}
here's the existing test cases:
```json
<cases_json>
```

@output{test cases}: output the test cases in the JSON format: