You are tasked with creating a framework for a test class in Java. Now analyze the following information carefully:

1. The method that needs to be tested and its parameters.
2. The constructor of the target class and its parameters.
3. Any dependencies or external resources used by the target class.

Based on this analysis, complete the test class template in the provided structure:

1. Initialize necessary fields and dependencies within setupBeforeAll() and setupBeforeEach().
2. Handle any required cleanup in teardownAfterEach() and teardownAfterAll().
3. Make sure not to invoke any private methods or fields of the target class and dependent class.
4. Refer to the "given" phase in the existing test class for guidance.
5. Add necessary import statements and ensure there are no syntax errors in the generated code.

@input{focal method and target class}
Here is the code of focal method, `populateEnv(SparkApplicationSpec, SubmitApplicationRequest, AppConfig.SparkCluster)`, with the focal class `ApplicationSubmissionHelper_populateEnv_Test`:
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


@input{context information}

parameters in constructors and focal method: 
class `com.apple.spark.operator.SparkApplicationSpec`:
constructor:
```java
```
class `com.apple.spark.api.SubmitApplicationRequest`:
constructor:
```java
```
class `com.apple.spark.AppConfig.SparkCluster`:
constructor:
```java
```

existing test class: 
```java
/*
 *
 * This source file is part of the Batch Processing Gateway open source project
 *
 * Copyright 2022 Apple Inc. and the Batch Processing Gateway project authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.apple.spark.core;

import com.apple.spark.AppConfig;
import com.apple.spark.api.SubmitApplicationRequest;
import com.apple.spark.operator.DriverSpec;
import com.apple.spark.operator.EnvVar;
import com.apple.spark.operator.ExecutorSpec;
import com.apple.spark.operator.HostPathVolumeSource;
import com.apple.spark.operator.SparkApplicationSpec;
import com.apple.spark.operator.Volume;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.WebApplicationException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApplicationSubmissionHelperTest {

  private final String DUMMY_REQUEST_BODY_JSON =
      "{\n"
          + "  \"type\": \"Scala\",\n"
          + "  \"sparkVersion\": \"3.0\",\n"
          + "  \"mainApplicationFile\":"
          + " \"s3a://artifacts-bucket/uploaded/20220322_9am/metrics-1.0-SNAPSHOT.jar\",\n"
          + "  \"mainClass\": \"com.apple.Metrics\",\n"
          + "  \"deps\": {\n"
          + "    \"jars\": [\n"
          + "     "
          + " \"s3a://artifacts-bucket/uploaded/20220322_9am/Dependency-0.7.45.jar\",\n"
          + "      \"s3a://artifacts-bucket/uploaded/20220322_9am/some-jar-1.4.4-3.jar\"\n"
          + "    ]\n"
          + "  },\n"
          + "  \"queue\": \"sde\",\n"
          + "  \"driver\": {\n"
          + "    \"cores\": 1,\n"
          + "    \"memory\": \"1g\",\n"
          + "    \"env\": [\n"
          + "      {\n"
          + "        \"name\": \"tgt\",\n"
          + "        \"value\": \"xxxxxxxxxxx...\"\n"
          + "      }\n"
          + "    ]\n"
          + "  },\n"
          + "  \"executor\": {\n"
          + "    \"instances\": 2,\n"
          + "    \"cores\": 1,\n"
          + "    \"memory\": \"1g\"\n"
          + "  }\n"
          + "}";

  private final String DUMMY_REQUEST_BODY_YAML =
      "---\n"
          + "type: Scala\n"
          + "sparkVersion: '3.0'\n"
          + "mainApplicationFile:"
          + " s3a://artifacts-bucket/uploaded/20220322_9am/metrics-1.0-SNAPSHOT.jar\n"
          + "mainClass: com.apple.Metrics\n"
          + "deps:\n"
          + "  jars:\n"
          + "  - s3a://artifacts-bucket/uploaded/20220322_9am/Dependency-0.7.45.jar\n"
          + "  - s3a://artifacts-bucket/uploaded/20220322_9am/some-jar-1.4.4-3.jar\n"
          + "queue: sde\n"
          + "driver:\n"
          + "  cores: 1\n"
          + "  memory: 1g\n"
          + "  env:\n"
          + "  - name: tgt\n"
          + "    value: xxxxxxxxxxx...\n"
          + "executor:\n"
          + "  instances: 2\n"
          + "  cores: 1\n"
          + "  memory: 1g\n"
          + "\n";

  @Test
  public void getSparkConf_nullDefaultSparkConf() {
    AppConfig appConfig = new AppConfig();
    Map<String, String> defaultSparkConf = null;
    appConfig.setDefaultSparkConf(defaultSparkConf);
......

    queueConfig = new AppConfig.QueueConfig();
    queueConfig.setName("queue1");
    appConfig.getQueues().add(queueConfig);
    ApplicationSubmissionHelper.validateQueueToken("queue1", null, appConfig);
  }

  @Test(expectedExceptions = WebApplicationException.class)
  public void validateQueueToken_nullQueueTokenWithSecureQueue() {
    AppConfig appConfig = new AppConfig();
    appConfig.setQueues(new ArrayList<>());

    AppConfig.QueueConfig queueConfig = new AppConfig.QueueConfig();
    queueConfig.setName("anotherQueue");
    appConfig.getQueues().add(queueConfig);
    ApplicationSubmissionHelper.validateQueueToken("queue1", null, appConfig);

    queueConfig = new AppConfig.QueueConfig();
    queueConfig.setName("queue1");
    queueConfig.setSecure(true);
    appConfig.getQueues().add(queueConfig);
    ApplicationSubmissionHelper.validateQueueToken("queue1", null, appConfig);
  }

  @Test(expectedExceptions = WebApplicationException.class)
  public void validateQueueToken_emptyStringQueueTokenWithSecureQueue() {
    AppConfig appConfig = new AppConfig();
    appConfig.setQueues(new ArrayList<>());

    AppConfig.QueueConfig queueConfig = new AppConfig.QueueConfig();
    queueConfig.setName("anotherQueue");
    appConfig.getQueues().add(queueConfig);
    ApplicationSubmissionHelper.validateQueueToken("queue1", null, appConfig);

    queueConfig = new AppConfig.QueueConfig();
    queueConfig.setName("queue1");
    queueConfig.setSecure(true);
    appConfig.getQueues().add(queueConfig);
    ApplicationSubmissionHelper.validateQueueToken("queue1", "", appConfig);
  }

  @Test
  public void requestBodyCheck_suspiciousFilePathLike() {
    Assert.assertTrue(ApplicationSubmissionHelper.looksLikeFilePath("~/Downloads/cool_stuff.txt"));
    Assert.assertTrue(ApplicationSubmissionHelper.looksLikeFilePath("/Users/tim/Downloads"));

    Assert.assertFalse(ApplicationSubmissionHelper.looksLikeFilePath(DUMMY_REQUEST_BODY_JSON));
    Assert.assertFalse(ApplicationSubmissionHelper.looksLikeFilePath(DUMMY_REQUEST_BODY_YAML));
  }
}
```

invoke examples of focal method: 
example 1:
```java
  private SubmitApplicationResponse submitApplicationImpl(
      String requestBody, String contentType, User user, String dagUser) {
    SubmitApplicationRequest request =
        ApplicationSubmissionHelper.parseSubmitRequest(requestBody, contentType);
    String queue = SparkClusterHelper.getQueue(appConfig, request, user.getName());
    validateSubmissionRequest(request, queue);
    AppConfig.SparkCluster sparkCluster =
        SparkClusterHelper.chooseSparkCluster(appConfig, request, user.getName());
    String sparkVersionTagValue =
        request.getSparkVersion() != null ? request.getSparkVersion() : "";
    String submissionId =
        ApplicationSubmissionHelper.generateSubmissionId(
            sparkCluster.getId(), request.getSubmissionIdSuffix());
    logger.info("Creating application submission: {}", submissionId);
    String proxyUser = getProxyUser(user.getName(), dagUser);
    String type = getType(request.getType(), request.getMainClass());
    String sparkVersion = request.getSparkVersion();
    String queue_2 = SparkClusterHelper.getQueue(appConfig, request, user.getName());
    String parentQueue = SparkClusterHelper.getParentQueue(queue_2);
    String queueTagValue = queue_2 == null ? "" : queue_2;
    SparkApplicationSpec.Builder specBuilder =
        new SparkApplicationSpec.Builder()
            .withMode(SparkConstants.CLUSTER_MODE)
            .withOriginalUser(user.getName())
            .withProxyUser(proxyUser)
            .withType(type)
            .withSparkVersion(sparkVersion)
            .withMainClass(request.getMainClass())
            .withMainApplicationFile(request.getMainApplicationFile())
            .withDriver(getDriverSpec(request, appConfig, parentQueue, sparkCluster))
            .withExecutor(getExecutorSpec(request, appConfig, parentQueue, sparkCluster))
            .withImage(getImage(getAppConfig(), request, type, sparkVersion, proxyUser))
            .withArguments(request.getArguments())
            .withImagePullPolicy(request.getImagePullPolicy())
            .withRestartPolicy(request.getRestartPolicy())
            .withVolumes(request.getVolumes())
            .withDeps(request.getDeps())
            .withPythonVersion(request.getPythonVersion())
            .withTimeToLiveSeconds(sparkCluster.getTtlSeconds())
            .extendSparkConf(getSparkConf(submissionId, request, appConfig, sparkCluster))
            .withSparkUIConfiguration(getSparkUIConfiguration(submissionId, sparkCluster))
            .extendVolumes(getVolumes(request, sparkCluster));
    String batchScheduler = sparkCluster.getBatchScheduler();
    if (batchScheduler != null) {
    SparkApplicationSpec sparkSpec = specBuilder.build();
    ApplicationSubmissionHelper.populateEnv(sparkSpec, request, sparkCluster);
  }
```



@input{test class template}
```java
package com.apple.spark.core;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
// Add other imports if necessary

@ExtendWith(MockitoExtension.class)
class ApplicationSubmissionHelper_populateEnv_Test {
    @BeforeAll
    static void setupBeforeAll() {
    }

    @BeforeEach
    void setupBeforeEach() {
    }

    @AfterEach
    void teardownAfterEach() {
    }

    @AfterAll
    static void teardownAfterAll() {
    }
}
```

@output{initial test class}: now consider the invoke examples output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.