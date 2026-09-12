```java
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

@ExtendWith(MockitoExtension.class)
class ApplicationSubmissionHelper_populateEnv_Test {

    private SparkApplicationSpec sparkSpec;
    private SubmitApplicationRequest request;
    private AppConfig.SparkCluster sparkCluster;

    @BeforeAll
    static void setupBeforeAll() {
        // Initialize any static resources if necessary
    }

    @BeforeEach
    void setupBeforeEach() {
        sparkSpec = new SparkApplicationSpec();
        request = new SubmitApplicationRequest();
        sparkCluster = new AppConfig.SparkCluster();
        
        // Initialize mocks if necessary
        // Example:
        // when(mockedDependency.someMethod()).thenReturn(someValue);
    }

    @AfterEach
    void teardownAfterEach() {
        // Clean up resources if necessary
    }

    @AfterAll
    static void teardownAfterAll() {
        // Clean up any static resources if necessary
    }

    @Test
    void testPopulateEnv_withValidInputs() {
        // Given
        // Setup input parameters and expected results
        // Example: populate request, sparkCluster with necessary data

        // When
        ApplicationSubmissionHelper.populateEnv(sparkSpec, request, sparkCluster);

        // Then
        // Assert the expected outcomes
        // Example: assertNotNull(sparkSpec.getDriver().getEnv());
    }

    // Add more test methods to cover different scenarios
}
```