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
Here is the code of focal method, `copyFrom(SparkApplication)`, with the focal class `SubmissionSummary`:
```Java
package com.apple.spark.api;
import static org.apache.spark.network.util.JavaUtils.byteStringAsGb;
import com.apple.spark.AppConfig;
import com.apple.spark.core.Constants;
import com.apple.spark.util.ConfigUtil;
import org.apache.commons.lang3.StringUtils;
import com.apple.spark.core.SparkConstants;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import com.apple.spark.operator.SparkApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.apple.spark.operator.SparkApplicationSpec;
@JsonInclude(Include.NON_NULL)
public class SubmissionSummary extends SubmissionStatus {
    private static final Logger logger = LoggerFactory.getLogger(SubmissionSummary.class);
    private String submissionId;
    @Override
    public void copyFrom(SparkApplication sparkApplicationResource) {
        super.copyFrom(sparkApplicationResource);
        setSubmissionId(sparkApplicationResource.getMetadata().getName());
        if (sparkApplicationResource.getMetadata().getLabels() != null) {
            String user = sparkApplicationResource.getMetadata().getLabels().get(Constants.PROXY_USER_LABEL);
            String queue = "";
            if (sparkApplicationResource.getMetadata().getLabels().containsKey(Constants.QUEUE_LABEL)) {
                queue = sparkApplicationResource.getMetadata().getLabels().get(Constants.QUEUE_LABEL);
            }
            String dagName = "";
            int driverCores = 1;
            Long driverMemoryGB = 0L;
            int executorInstances = 1;
            int executorCores = 1;
            Long executorMemoryGB = 0L;
            if (sparkApplicationResource.getSpec().getDriver().getLabels() != null) {
                if (sparkApplicationResource.getSpec().getDriver().getLabels().containsKey(Constants.DAG_NAME_LABEL)) {
                    dagName = sparkApplicationResource.getSpec().getDriver().getLabels().get(Constants.DAG_NAME_LABEL);
                }
            }
            if (sparkApplicationResource.getSpec().getDriver().getMemory() != null) {
                try {
                    driverMemoryGB = byteStringAsGb(sparkApplicationResource.getSpec().getDriver().getMemory());
                } catch (Exception e) {
                    logger.info("Cannot convert Driver memory size string to Gb for {}, {}.", submissionId, e.toString());
                }
            }
            if (sparkApplicationResource.getSpec().getDriver().getCores() != null) {
                driverCores = sparkApplicationResource.getSpec().getDriver().getCores();
            }
            if (sparkApplicationResource.getSpec().getExecutor().getCores() != null) {
                executorCores = sparkApplicationResource.getSpec().getExecutor().getCores();
            }
            if (sparkApplicationResource.getSpec().getExecutor().getInstances() != null) {
                executorInstances = sparkApplicationResource.getSpec().getExecutor().getInstances();
            }
            if (sparkApplicationResource.getSpec().getExecutor().getMemory() != null) {
                try {
                    executorMemoryGB = byteStringAsGb(sparkApplicationResource.getSpec().getExecutor().getMemory());
                } catch (Exception e) {
                    logger.info("Cannot convert Executor memory size string to Gb for {}, {}.", submissionId, e.toString());
                }
            }
            setDriverMemoryGB(driverMemoryGB);
            setDriverCores(driverCores);
            setExecutorInstances(executorInstances);
            setExecutorMemoryGB(executorMemoryGB);
            setExecutorCores(executorCores);
            setTotalMemoryGB((executorInstances * executorMemoryGB) + driverMemoryGB);
            setTotalCores((executorInstances * executorCores) + driverCores);
            if (user != null) {
                setUser(user);
            }
            if (queue != null) {
                setQueue(queue);
            }
            if (dagName != null) {
                setDagName(dagName);
            }
        }
        SparkApplicationSpec spec = sparkApplicationResource.getSpec();
        if (spec != null) {
            setSparkVersion(spec.getSparkVersion());
            setApplicationArguments(spec.getArguments());
            if (spec.getSparkConf() != null) {
                String appNameConfigValue = spec.getSparkConf().get(SparkConstants.SPARK_APP_NAME_CONFIG);
                if (appNameConfigValue != null) {
                    setApplicationName(appNameConfigValue);
                }
            }
        }
    }
public void copyFrom(SparkApplication sparkApplicationResource, AppConfig.SparkCluster sparkCluster, AppConfig appConfig);
    public List<String> getApplicationArguments();
    public String getApplicationName();
    public String getDagName();
    public int getDriverCores();
    public Long getDriverMemoryGB();
    public int getExecutorCores();
    public int getExecutorInstances();
    public Long getExecutorMemoryGB();
    public String getQueue();
    public String getSparkUIUrl();
    public String getSparkVersion();
    public String getSubmissionId();
    public int getTotalCores();
    public Long getTotalMemoryGB();
    public String getUser();
    public void setApplicationArguments(List<String> applicationArguments);
    public void setApplicationName(String applicationName);
    public void setDagName(String dagName);
    public void setDriverCores(int driverCores);
    public void setDriverMemoryGB(Long driverMemoryGB);
    public void setExecutorCores(int executorCores);
    public void setExecutorInstances(int executorInstances);
    public void setExecutorMemoryGB(Long executorMemoryGB);
    public void setQueue(String queue);
    public void setSparkUIUrl(String sparkUIUrl);
    public void setSparkVersion(String sparkVersion);
    public void setSubmissionId(String submissionId);
    public void setTotalCores(int totalCores);
    public void setTotalMemoryGB(Long totalMemoryGB);
    public void setUser(String user);
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

dependent classes: 
class `com.apple.spark.core.Constants`:
	dependent fields:
		java.lang.String com.apple.spark.core.Constants.QUEUE_LABEL;
		java.lang.String com.apple.spark.core.Constants.DAG_NAME_LABEL;
		java.lang.String com.apple.spark.core.Constants.PROXY_USER_LABEL;
	
class `com.apple.spark.api.SubmissionSummary`:
	dependent fields:
		org.slf4j.Logger com.apple.spark.api.SubmissionSummary.logger;
		java.lang.String com.apple.spark.api.SubmissionSummary.submissionId;
	related functions:
		method `public String getSubmissionId()` returns `java.lang.String`, related with `com.apple.spark.api.SubmissionSummary.copyFrom(SparkApplication)`;
class `com.apple.spark.core.SparkConstants`:
	dependent fields:
		java.lang.String com.apple.spark.core.SparkConstants.SPARK_APP_NAME_CONFIG;
	
class `com.apple.spark.core.ApplicationMonitor`:
	related functions:
		method `private void onUpdateImpl_logApplication(AppConfig.SparkCluster, SparkApplication, SparkApplication)` returns `void`, related with `com.apple.spark.api.SubmissionSummary.copyFrom(SparkApplication)`, api document: 
   * Update DB with a new resource object, including job status, metadata and applicationId. Using
   * the submissionId hashset to reduce unnecessary updates.
   *
   * @param sparkCluster Spark cluster
   * @param prevCRDState the old resource object
   * @param currCRDState the new resource object
   ;
class `com.apple.spark.core.ApplicationSubmissionHelper`:
	related functions:
		method `public static ExecutorSpec getExecutorSpec(SubmitApplicationRequest, AppConfig, String, SparkCluster)` returns `com.apple.spark.operator.ExecutorSpec`, related with `com.apple.spark.api.SubmissionSummary.copyFrom(SparkApplication)`;
		method `public static DriverSpec getDriverSpec(SubmitApplicationRequest, AppConfig, String, SparkCluster)` returns `com.apple.spark.operator.DriverSpec`, related with `com.apple.spark.api.SubmissionSummary.copyFrom(SparkApplication)`;
class `com.apple.spark.core.RunningApplicationMonitor`:
	related functions:
		method `protected void killApplication(String, String)` returns `void`, related with `com.apple.spark.api.SubmissionSummary.copyFrom(SparkApplication)`, api document: 
   * Kill an application by deleting the driver pod.
   *
   * @param namespace the namespace of the app
   * @param appName the name of the app (typically submission ID)
   ;
class `com.apple.spark.rest.ApplicationSubmissionRest`:
	related functions:
		method `private SubmitApplicationResponse submitSparkCRD(SparkCluster, String, SparkApplicationSpec, SubmitApplicationRequest, String, String)` returns `com.apple.spark.api.SubmitApplicationResponse`, related with `com.apple.spark.api.SubmissionSummary.copyFrom(SparkApplication)`;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.