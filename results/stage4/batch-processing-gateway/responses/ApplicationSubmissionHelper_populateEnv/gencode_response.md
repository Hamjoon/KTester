```java
package com.apple.spark.core;

import com.apple.spark.AppConfig;
import com.apple.spark.api.SubmitApplicationRequest;
import com.apple.spark.operator.DriverSpec;
import com.apple.spark.operator.ExecutorSpec;
import com.apple.spark.operator.SparkApplicationSpec;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplicationSubmissionHelper_populateEnv_Test {

    @Mock
    private SparkApplicationSpec sparkApplicationSpec;

    @Mock
    private SubmitApplicationRequest submitApplicationRequest;

    @Mock
    private AppConfig.SparkCluster sparkCluster;

    @BeforeAll
    static void setupBeforeAll() {
        // This method can be used to set up any global resources needed for the tests.
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize mocks and set up common test data
        when(sparkApplicationSpec.getDriver()).thenReturn(new DriverSpec());
        when(sparkApplicationSpec.getExecutor()).thenReturn(new ExecutorSpec());
    }

    @AfterEach
    void teardownAfterEach() {
        // Clean up after each test if necessary
    }

    @AfterAll
    static void teardownAfterAll() {
        // Clean up any global resources after all tests have run
    }

    @Test
    void testPopulateEnv_withDriverEnvFromCluster() {
        // Given: SparkCluster has a driver environment, and sparkSpec initially has no driver.
        List<String> driverEnv = new ArrayList<>();
        driverEnv.add("VAR1=value1");
        driverEnv.add("VAR2=value2");
        when(sparkCluster.getDriver()).thenReturn(mock(DriverSpec.class));
        when(sparkCluster.getDriver().getEnv()).thenReturn(driverEnv);
        when(sparkApplicationSpec.getDriver()).thenReturn(new DriverSpec());

        // When: populateEnv is invoked
        ApplicationSubmissionHelper.populateEnv(sparkApplicationSpec, submitApplicationRequest, sparkCluster);

        // Then: the driver environment should be copied to sparkSpec.
        assertNotNull(sparkApplicationSpec.getDriver().getEnv());
        assertEquals(driverEnv, sparkApplicationSpec.getDriver().getEnv());
    }

    @Test
    void testPopulateEnv_withDriverEnvFromRequest() {
        // Given: SubmitApplicationRequest has a driver environment, and sparkSpec initially has no driver.
        List<String> requestDriverEnv = new ArrayList<>();
        requestDriverEnv.add("VAR3=value3");
        when(submitApplicationRequest.getDriver()).thenReturn(mock(DriverSpec.class));
        when(submitApplicationRequest.getDriver().getEnv()).thenReturn(requestDriverEnv);
        when(sparkApplicationSpec.getDriver()).thenReturn(new DriverSpec());

        // When: populateEnv is invoked
        ApplicationSubmissionHelper.populateEnv(sparkApplicationSpec, submitApplicationRequest, sparkCluster);

        // Then: the driver environment should be copied to sparkSpec.
        assertNotNull(sparkApplicationSpec.getDriver().getEnv());
        assertEquals(requestDriverEnv, sparkApplicationSpec.getDriver().getEnv());
    }

    @Test
    void testPopulateEnv_withExecutorEnvFromCluster() {
        // Given: SparkCluster has an executor environment, and sparkSpec initially has no executor.
        List<String> executorEnv = new ArrayList<>();
        executorEnv.add("VAR4=value4");
        when(sparkCluster.getExecutor()).thenReturn(mock(ExecutorSpec.class));
        when(sparkCluster.getExecutor().getEnv()).thenReturn(executorEnv);
        when(sparkApplicationSpec.getExecutor()).thenReturn(new ExecutorSpec());

        // When: populateEnv is invoked
        ApplicationSubmissionHelper.populateEnv(sparkApplicationSpec, submitApplicationRequest, sparkCluster);

        // Then: the executor environment should be copied to sparkSpec.
        assertNotNull(sparkApplicationSpec.getExecutor().getEnv());
        assertEquals(executorEnv, sparkApplicationSpec.getExecutor().getEnv());
    }

    @Test
    void testPopulateEnv_withExecutorEnvFromRequest() {
        // Given: SubmitApplicationRequest has an executor environment, and sparkSpec initially has no executor.
        List<String> requestExecutorEnv = new ArrayList<>();
        requestExecutorEnv.add("VAR5=value5");
        when(submitApplicationRequest.getExecutor()).thenReturn(mock(ExecutorSpec.class));
        when(submitApplicationRequest.getExecutor().getEnv()).thenReturn(requestExecutorEnv);
        when(sparkApplicationSpec.getExecutor()).thenReturn(new ExecutorSpec());

        // When: populateEnv is invoked
        ApplicationSubmissionHelper.populateEnv(sparkApplicationSpec, submitApplicationRequest, sparkCluster);

        // Then: the executor environment should be copied to sparkSpec.
        assertNotNull(sparkApplicationSpec.getExecutor().getEnv());
        assertEquals(requestExecutorEnv, sparkApplicationSpec.getExecutor().getEnv());
    }

    @Test
    void testPopulateEnv_withMixedEnvironments() {
        // Given: SparkCluster driver has an environment, request executor has an environment, and sparkSpec driver already has an environment.
        List<String> driverEnv = new ArrayList<>();
        driverEnv.add("VAR1=value1");
        when(sparkCluster.getDriver()).thenReturn(mock(DriverSpec.class));
        when(sparkCluster.getDriver().getEnv()).thenReturn(driverEnv);

        List<String> requestExecutorEnv = new ArrayList<>();
        requestExecutorEnv.add("VAR2=value2");
        when(submitApplicationRequest.getExecutor()).thenReturn(mock(ExecutorSpec.class));
        when(submitApplicationRequest.getExecutor().getEnv()).thenReturn(requestExecutorEnv);

        when(sparkApplicationSpec.getDriver()).thenReturn(new DriverSpec());
        when(sparkApplicationSpec.getExecutor()).thenReturn(new ExecutorSpec());

        // When: populateEnv is invoked
        ApplicationSubmissionHelper.populateEnv(sparkApplicationSpec, submitApplicationRequest, sparkCluster);

        // Then: the environments should be merged appropriately.
        List<String> mergedDriverEnv = new ArrayList<>(driverEnv);
        mergedDriverEnv.addAll(sparkApplicationSpec.getDriver().getEnv());
        assertEquals(mergedDriverEnv, sparkApplicationSpec.getDriver().getEnv());

        List<String> mergedExecutorEnv = new ArrayList<>(requestExecutorEnv);
        mergedExecutorEnv.addAll(sparkApplicationSpec.getExecutor().getEnv());
        assertEquals(mergedExecutorEnv, sparkApplicationSpec.getExecutor().getEnv());
    }

    @Test
    void testPopulateEnv_withNullValues() {
        // Given: all parameters are null.
        SparkApplicationSpec nullSparkSpec = null;
        SubmitApplicationRequest nullRequest = null;
        AppConfig.SparkCluster nullSparkCluster = null;

        // When: populateEnv is invoked
        assertDoesNotThrow(() -> ApplicationSubmissionHelper.populateEnv(nullSparkSpec, nullRequest, nullSparkCluster));
    }

    @Test
    void testPopulateEnv_withEmptyEnvironments() {
        // Given: all environments (sparkSpec, request, sparkCluster) are empty.
        when(sparkApplicationSpec.getDriver()).thenReturn(new DriverSpec());
        when(sparkApplicationSpec.getExecutor()).thenReturn(new ExecutorSpec());

        // When: populateEnv is invoked
        ApplicationSubmissionHelper.populateEnv(sparkApplicationSpec, submitApplicationRequest, sparkCluster);

        // Then: no changes should occur to sparkSpec.
        assertNotNull(sparkApplicationSpec.getDriver().getEnv());
        assertTrue(sparkApplicationSpec.getDriver().getEnv().isEmpty());
        assertNotNull(sparkApplicationSpec.getExecutor().getEnv());
        assertTrue(sparkApplicationSpec.getExecutor().getEnv().isEmpty());
    }
}
```