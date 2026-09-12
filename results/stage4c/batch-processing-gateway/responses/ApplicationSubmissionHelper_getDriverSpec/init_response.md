```java
package com.apple.spark.core;

import com.apple.spark.AppConfig;
import com.apple.spark.api.SubmitApplicationRequest;
import com.apple.spark.operator.DriverSpec;
import com.apple.spark.AppConfig.SparkCluster;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ApplicationSubmissionHelper_getDriverSpec_Test {

    private SubmitApplicationRequest request;
    private AppConfig appConfig;
    private SparkCluster sparkCluster;
    private String parentQueue;

    @BeforeAll
    static void setupBeforeAll() {
        // Global setup if needed
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the mocked dependencies and any other necessary setup
        request = mock(SubmitApplicationRequest.class);
        appConfig = new AppConfig();
        sparkCluster = mock(SparkCluster.class);
        parentQueue = "dummyQueue";
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup resources if needed
    }

    @AfterAll
    static void teardownAfterAll() {
        // Global teardown if needed
    }

    @Test
    void testGetDriverSpec() {
        // Given
        when(request.getDriver()).thenReturn(null);  // Example setup, modify as necessary
        when(sparkCluster.getSparkServiceAccount()).thenReturn("defaultAccount");

        // When
        DriverSpec result = ApplicationSubmissionHelper.getDriverSpec(request, appConfig, parentQueue, sparkCluster);

        // Then
        assertNotNull(result);
        assertEquals("defaultAccount", result.getServiceAccount());
        // Add more assertions based on expected behavior
    }
}
```