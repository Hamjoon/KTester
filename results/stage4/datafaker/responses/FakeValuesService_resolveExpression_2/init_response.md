Based on the provided information, here is the completed test class template for the `FakeValuesService_resolveExpression_2_Test`. This class will focus on testing the `resolveExpression` method of the `FakeValuesService` class. The necessary fields and dependencies have been initialized in the setup methods, and cleanup is handled in the teardown methods. The test cases will be structured to follow the guidelines provided.

```java
package net.datafaker.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Locale;

@ExtendWith(MockitoExtension.class)
class FakeValuesService_resolveExpression_2_Test {

    private FakeValuesService fakeValuesService;
    private FakerContext context;
    private ProviderRegistration providerRegistration;

    @BeforeAll
    static void setupBeforeAll() {
        // Any global setup can be done here
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the FakeValuesService and FakerContext before each test
        fakeValuesService = new FakeValuesService();
        providerRegistration = mock(ProviderRegistration.class); // Mock the ProviderRegistration
        context = new FakerContext(new Locale("test"), mock(RandomService.class));
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup after each test if necessary
    }

    @AfterAll
    static void teardownAfterAll() {
        // Any global cleanup can be done here
    }

    @Test
    void testResolveExpressionWithValidInput() {
        // Given
        String expression = "#{name.firstName}";
        Object current = null; // You can set this to a mock or actual object if needed

        // When
        String result = fakeValuesService.resolveExpression(expression, current, providerRegistration, context);

        // Then
        assertNotNull(result);
        // Further assertions can be made based on expected outcomes
    }

    @Test
    void testResolveExpressionWithInvalidInput() {
        // Given
        String expression = "#{invalid.directive}";
        Object current = null;

        // When & Then
        assertThrows(RuntimeException.class, () -> {
            fakeValuesService.resolveExpression(expression, current, providerRegistration, context);
        });
    }

    // Additional tests can be added here for various scenarios
}
```

### Explanation:
- **Imports**: The necessary imports for JUnit and Mockito are included.
- **Setup and Teardown**: The `setupBeforeAll`, `setupBeforeEach`, `teardownAfterEach`, and `teardownAfterAll` methods are defined to manage the lifecycle of the tests.
- **Test Cases**: 
  - `testResolveExpressionWithValidInput`: Tests the `resolveExpression` method with a valid input expression.
  - `testResolveExpressionWithInvalidInput`: Tests the method with an invalid input to ensure it throws the expected exception.
- **Mocks**: `ProviderRegistration` and `RandomService` are mocked as they are dependencies required for the tests.

This structure allows for easy expansion with additional test cases to cover various scenarios and edge cases for the `resolveExpression` method.