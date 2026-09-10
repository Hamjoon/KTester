Based on the provided information, here's the completed test class template for testing the `getExecutorSpec` method in the `ApplicationSubmissionHelper` class. The test class is structured according to the requirements and follows the guidelines given:

```java
package com.apple.spark.core;

import com.apple.spark.AppConfig;
import com.apple.spark.api.SubmitApplicationRequest;
import com.apple.spark.operator.ExecutorSpec;
import com.apple.spark.operator.AppConfig.SparkCluster;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ApplicationSubmissionHelper_getExecutorSpec_Test {
    
    private SubmitApplicationRequest request;
    private AppConfig appConfig;
    private SparkCluster sparkCluster;

    @BeforeAll
    static void setupBeforeAll() {
        // Any setup that needs to happen once for all tests can be done here
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the request, appConfig, and sparkCluster before each test
        request = new SubmitApplicationRequest(); // Assuming a default constructor exists
        appConfig = new AppConfig(); // Using the default constructor
        sparkCluster = mock(SparkCluster.class); // Mocking SparkCluster as needed
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup after each test if necessary
    }

    @AfterAll
    static void teardownAfterAll() {
        // Cleanup that needs to happen once after all tests can be done here
    }

    @Test
    void testGetExecutorSpec_withValidRequest() {
        // Given
        request.setExecutor(new ExecutorSpec()); // Assuming ExecutorSpec has a default constructor
        request.getExecutor().setCores(2);
        request.getExecutor().setMemory("2g");
        
        // Assuming you have methods to set other necessary properties
        // appConfig and sparkCluster should also be set up with required values

        // When
        ExecutorSpec executorSpec = ApplicationSubmissionHelper.getExecutorSpec(request, appConfig, "parentQueue", sparkCluster);

        // Then
        assertNotNull(executorSpec);
        assertEquals(2, executorSpec.getCores());
        assertEquals("2g", executorSpec.getMemory());
        // Add more assertions as necessary to validate the behavior
    }

    // Additional test cases can be added here to cover various scenarios
}
```

### Explanation:
1. **Imports**: The necessary classes and annotations are imported.
2. **Setup Methods**: 
   - `setupBeforeAll()` is left empty for any potential global setup.
   - `setupBeforeEach()` initializes the `SubmitApplicationRequest`, `AppConfig`, and mocks the `SparkCluster` before each test.
3. **Teardown Methods**: Empty for now, but can be used for cleanup if needed.
4. **Test Method**: A sample test method `testGetExecutorSpec_withValidRequest()` is provided to demonstrate how to test the `getExecutorSpec` method. It sets up a valid request and checks that the returned `ExecutorSpec` is not null and has the expected values.
5. **Assertions**: Use assertions to validate the expected outcomes from the method being tested.

You can expand this test class further by adding more test cases to cover edge cases and different scenarios for the `getExecutorSpec` method.