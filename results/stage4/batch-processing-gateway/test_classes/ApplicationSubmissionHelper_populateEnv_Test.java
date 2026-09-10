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
import org.junit.jupiter.api.extension.ExtendWith;
import com.apple.spark.operator.EnvVar;

@ExtendWith(MockitoExtension.class)
class ApplicationSubmissionHelper_populateEnv_Test {

    @Mock
    private SparkApplicationSpec sparkApplicationSpec;

    @Mock
    private SubmitApplicationRequest submitApplicationRequest;

    @Mock
    private AppConfig.SparkCluster sparkCluster;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize mocks and set up common test data
        when(sparkApplicationSpec.getDriver()).thenReturn(new DriverSpec());
        when(sparkApplicationSpec.getExecutor()).thenReturn(new ExecutorSpec());
    }

    @Test
    void testPopulateEnv_withDriverEnvFromCluster() {
        // Given: SparkCluster has a driver environment, and sparkSpec initially has no driver.
        List<EnvVar> driverEnv = new ArrayList<>();
        driverEnv.add(new EnvVar("VAR1", "value1"));
        DriverSpec driverSpec = mock(DriverSpec.class);
        when(sparkCluster.getDriver()).thenReturn(driverSpec);
        when(driverSpec.getEnv()).thenReturn(driverEnv);
        // When: populateEnv is invoked
        ApplicationSubmissionHelper.populateEnv(sparkApplicationSpec, submitApplicationRequest, sparkCluster);
        // Then: the driver environment should be copied to sparkSpec.
        assertNotNull(sparkApplicationSpec.getDriver().getEnv());
        assertEquals(driverEnv, sparkApplicationSpec.getDriver().getEnv());
    }

    @Test
    void testPopulateEnv_withDriverEnvFromRequest() {
        // Given: SubmitApplicationRequest has a driver environment, and sparkSpec initially has no driver.
        List<EnvVar> requestDriverEnv = new ArrayList<>();
        requestDriverEnv.add(new EnvVar("VAR3", "value3"));
        DriverSpec requestDriverSpec = mock(DriverSpec.class);
        when(submitApplicationRequest.getDriver()).thenReturn(requestDriverSpec);
        when(requestDriverSpec.getEnv()).thenReturn(requestDriverEnv);
        // When: populateEnv is invoked
        ApplicationSubmissionHelper.populateEnv(sparkApplicationSpec, submitApplicationRequest, sparkCluster);
        // Then: the driver environment should be copied to sparkSpec.
        assertNotNull(sparkApplicationSpec.getDriver().getEnv());
        assertEquals(requestDriverEnv, sparkApplicationSpec.getDriver().getEnv());
    }

    @Test
    void testPopulateEnv_withExecutorEnvFromCluster() {
        // Given: SparkCluster has an executor environment, and sparkSpec initially has no executor.
        List<EnvVar> executorEnv = new ArrayList<>();
        executorEnv.add(new EnvVar("VAR4", "value4"));
        ExecutorSpec executorSpec = mock(ExecutorSpec.class);
        when(sparkCluster.getExecutor()).thenReturn(executorSpec);
        when(executorSpec.getEnv()).thenReturn(executorEnv);
        // When: populateEnv is invoked
        ApplicationSubmissionHelper.populateEnv(sparkApplicationSpec, submitApplicationRequest, sparkCluster);
        // Then: the executor environment should be copied to sparkSpec.
        assertNotNull(sparkApplicationSpec.getExecutor().getEnv());
        assertEquals(executorEnv, sparkApplicationSpec.getExecutor().getEnv());
    }

    @Test
    void testPopulateEnv_withExecutorEnvFromRequest() {
        // Given: SubmitApplicationRequest has an executor environment, and sparkSpec initially has no executor.
        List<EnvVar> requestExecutorEnv = new ArrayList<>();
        requestExecutorEnv.add(new EnvVar("VAR5", "value5"));
        ExecutorSpec requestExecutorSpec = mock(ExecutorSpec.class);
        when(submitApplicationRequest.getExecutor()).thenReturn(requestExecutorSpec);
        when(requestExecutorSpec.getEnv()).thenReturn(requestExecutorEnv);
        // When: populateEnv is invoked
        ApplicationSubmissionHelper.populateEnv(sparkApplicationSpec, submitApplicationRequest, sparkCluster);
        // Then: the executor environment should be copied to sparkSpec.
        assertNotNull(sparkApplicationSpec.getExecutor().getEnv());
        assertEquals(requestExecutorEnv, sparkApplicationSpec.getExecutor().getEnv());
    }

    @Test
    void testPopulateEnv_withMixedEnvironments() {
        // Given: SparkCluster driver has an environment, request executor has an environment, and sparkSpec driver already has an environment.
        List<EnvVar> driverEnv = new ArrayList<>();
        driverEnv.add(new EnvVar("VAR1", "value1"));
        DriverSpec driverSpec = mock(DriverSpec.class);
        when(sparkCluster.getDriver()).thenReturn(driverSpec);
        when(driverSpec.getEnv()).thenReturn(driverEnv);
        List<EnvVar> requestExecutorEnv = new ArrayList<>();
        requestExecutorEnv.add(new EnvVar("VAR2", "value2"));
        ExecutorSpec requestExecutorSpec = mock(ExecutorSpec.class);
        when(submitApplicationRequest.getExecutor()).thenReturn(requestExecutorSpec);
        when(requestExecutorSpec.getEnv()).thenReturn(requestExecutorEnv);
        // When: populateEnv is invoked
        ApplicationSubmissionHelper.populateEnv(sparkApplicationSpec, submitApplicationRequest, sparkCluster);
        // Then: the environments should be merged appropriately.
        List<EnvVar> mergedDriverEnv = new ArrayList<>(driverEnv);
        mergedDriverEnv.addAll(sparkApplicationSpec.getDriver().getEnv());
        assertEquals(mergedDriverEnv, sparkApplicationSpec.getDriver().getEnv());
        List<EnvVar> mergedExecutorEnv = new ArrayList<>(requestExecutorEnv);
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
