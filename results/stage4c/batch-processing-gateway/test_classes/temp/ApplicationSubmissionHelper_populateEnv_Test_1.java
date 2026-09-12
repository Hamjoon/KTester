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
        request.getDriver().setEnv(List.of(new EnvVar("VAR1", "value1")));
        request.setExecutor(new ExecutorSpec());
        request.getExecutor().setEnv(List.of(new EnvVar("VAR2", "value2")));
        sparkCluster.setDriver(new DriverSpec());
        sparkCluster.getDriver().setEnv(List.of(new EnvVar("VAR3", "value3")));
        sparkCluster.setExecutor(new ExecutorSpec());
        sparkCluster.getExecutor().setEnv(List.of(new EnvVar("VAR4", "value4")));
        // When
        ApplicationSubmissionHelper.populateEnv(sparkSpec, request, sparkCluster);
        // Then
        assertNotNull(sparkSpec.getDriver().getEnv());
        assertNotNull(sparkSpec.getExecutor().getEnv());
        assertTrue(sparkSpec.getDriver().getEnv().containsAll(List.of(new EnvVar("VAR3", "value3"), new EnvVar("VAR1", "value1"))));
        assertTrue(sparkSpec.getExecutor().getEnv().containsAll(List.of(new EnvVar("VAR4", "value4"), new EnvVar("VAR2", "value2"))));
    }

    @Test
    void testPopulateEnv_onlySparkClusterHasEnvs() {
        // Given
        sparkSpec.setDriver(new DriverSpec());
        sparkSpec.setExecutor(new ExecutorSpec());
        sparkCluster.setDriver(new DriverSpec());
        sparkCluster.getDriver().setEnv(List.of(new EnvVar("VAR3", "value3")));
        sparkCluster.setExecutor(new ExecutorSpec());
        sparkCluster.getExecutor().setEnv(List.of(new EnvVar("VAR4", "value4")));
        // When
        ApplicationSubmissionHelper.populateEnv(sparkSpec, request, sparkCluster);
        // Then
        assertEquals(List.of(new EnvVar("VAR3", "value3")), sparkSpec.getDriver().getEnv());
        assertEquals(List.of(new EnvVar("VAR4", "value4")), sparkSpec.getExecutor().getEnv());
    }

    @Test
    void testPopulateEnv_onlyRequestHasEnvs() {
        // Given
        sparkSpec.setDriver(new DriverSpec());
        sparkSpec.setExecutor(new ExecutorSpec());
        request.setDriver(new DriverSpec());
        request.getDriver().setEnv(List.of(new EnvVar("VAR1", "value1")));
        request.setExecutor(new ExecutorSpec());
        request.getExecutor().setEnv(List.of(new EnvVar("VAR2", "value2")));
        // When
        ApplicationSubmissionHelper.populateEnv(sparkSpec, request, sparkCluster);
        // Then
        assertEquals(List.of(new EnvVar("VAR1", "value1")), sparkSpec.getDriver().getEnv());
        assertEquals(List.of(new EnvVar("VAR2", "value2")), sparkSpec.getExecutor().getEnv());
    }

    @Test
    void testPopulateEnv_existingEnvsInSparkSpec() {
        // Given
        sparkSpec.setDriver(new DriverSpec());
        sparkSpec.getDriver().setEnv(new ArrayList<>(List.of(new EnvVar("EXISTING", "value"))));
        sparkSpec.setExecutor(new ExecutorSpec());
        sparkSpec.getExecutor().setEnv(new ArrayList<>(List.of(new EnvVar("EXISTING", "value"))));
        request.setDriver(new DriverSpec());
        request.getDriver().setEnv(List.of(new EnvVar("VAR1", "value1")));
        request.setExecutor(new ExecutorSpec());
        request.getExecutor().setEnv(List.of(new EnvVar("VAR2", "value2")));
        sparkCluster.setDriver(new DriverSpec());
        sparkCluster.getDriver().setEnv(List.of(new EnvVar("VAR3", "value3")));
        sparkCluster.setExecutor(new ExecutorSpec());
        sparkCluster.getExecutor().setEnv(List.of(new EnvVar("VAR4", "value4")));
        // When
        ApplicationSubmissionHelper.populateEnv(sparkSpec, request, sparkCluster);
        // Then
        assertTrue(sparkSpec.getDriver().getEnv().containsAll(List.of(new EnvVar("EXISTING", "value"), new EnvVar("VAR3", "value3"), new EnvVar("VAR1", "value1"))));
        assertTrue(sparkSpec.getExecutor().getEnv().containsAll(List.of(new EnvVar("EXISTING", "value"), new EnvVar("VAR4", "value4"), new EnvVar("VAR2", "value2"))));
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
        sparkSpec.getDriver().setEnv(new ArrayList<>(List.of(new EnvVar("EXISTING", "value"))));
        sparkSpec.setExecutor(new ExecutorSpec());
        request.setDriver(new DriverSpec());
        request.getDriver().setEnv(List.of(new EnvVar("VAR1", "value1")));
        request.setExecutor(new ExecutorSpec());
        request.getExecutor().setEnv(List.of(new EnvVar("VAR2", "value2")));
        sparkCluster.setExecutor(new ExecutorSpec());
        sparkCluster.getExecutor().setEnv(List.of(new EnvVar("VAR3", "value3")));
        // When
        ApplicationSubmissionHelper.populateEnv(sparkSpec, request, sparkCluster);
        // Then
        assertTrue(sparkSpec.getDriver().getEnv().containsAll(List.of(new EnvVar("EXISTING", "value"), new EnvVar("VAR1", "value1"))));
        assertTrue(sparkSpec.getExecutor().getEnv().containsAll(List.of(new EnvVar("VAR2", "value2"), new EnvVar("VAR3", "value3"))));
    }

    @Test
    void testPopulateEnv_existingEnvsAndEmptyRequestAndSparkCluster() {
        // Given
        sparkSpec.setDriver(new DriverSpec());
        sparkSpec.getDriver().setEnv(new ArrayList<>(List.of(new EnvVar("EXISTING", "value"))));
        sparkSpec.setExecutor(new ExecutorSpec());
        sparkSpec.getExecutor().setEnv(new ArrayList<>(List.of(new EnvVar("EXISTING", "value"))));
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
        assertEquals(List.of(new EnvVar("EXISTING", "value")), sparkSpec.getDriver().getEnv());
        assertEquals(List.of(new EnvVar("EXISTING", "value")), sparkSpec.getExecutor().getEnv());
    }

    @Test
    void testPopulateEnv_nullSparkSpec() {
        // Given
        sparkSpec = null;
        request.setDriver(new DriverSpec());
        request.getDriver().setEnv(List.of(new EnvVar("VAR1", "value1")));
        request.setExecutor(new ExecutorSpec());
        request.getExecutor().setEnv(List.of(new EnvVar("VAR2", "value2")));
        sparkCluster.setDriver(new DriverSpec());
        sparkCluster.getDriver().setEnv(List.of(new EnvVar("VAR3", "value3")));
        sparkCluster.setExecutor(new ExecutorSpec());
        sparkCluster.getExecutor().setEnv(List.of(new EnvVar("VAR4", "value4")));
        // When & Then
        assertThrows(NullPointerException.class, () -> ApplicationSubmissionHelper.populateEnv(sparkSpec, request, sparkCluster));
    }

    @Test
    void testPopulateEnv_nullRequest() {
        // Given
        request = null;
        sparkCluster.setDriver(new DriverSpec());
        sparkCluster.getDriver().setEnv(List.of(new EnvVar("VAR3", "value3")));
        sparkCluster.setExecutor(new ExecutorSpec());
        sparkCluster.getExecutor().setEnv(List.of(new EnvVar("VAR4", "value4")));
        // When & Then
        assertThrows(NullPointerException.class, () -> ApplicationSubmissionHelper.populateEnv(sparkSpec, request, sparkCluster));
    }

    @Test
    void testPopulateEnv_nullSparkCluster() {
        // Given
        sparkCluster = null;
        request.setDriver(new DriverSpec());
        request.getDriver().setEnv(List.of(new EnvVar("VAR1", "value1")));
        request.setExecutor(new ExecutorSpec());
        request.getExecutor().setEnv(List.of(new EnvVar("VAR2", "value2")));
        // When & Then
        assertThrows(NullPointerException.class, () -> ApplicationSubmissionHelper.populateEnv(sparkSpec, request, sparkCluster));
    }
}
