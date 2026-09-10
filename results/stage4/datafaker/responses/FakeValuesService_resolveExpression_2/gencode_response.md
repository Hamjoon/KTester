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
    void testResolveExpressionWithSimpleText() {
        // Test with a simple string input that does not contain any expressions.
        String expression = "simple text";
        Object current = null;

        // When
        String result = invokeResolveExpression(expression, current, providerRegistration, context);

        // Then
        assertEquals("simple text", result);
    }

    @Test
    void testResolveExpressionWithValidFirstName() {
        // Test with a valid expression that should resolve to a first name.
        String expression = "#{name.firstName}";
        Object current = null;

        // Mock the expected behavior for the first name resolution
        when(providerRegistration.getProvider(Name.class)).thenReturn(mock(Name.class));
        when(providerRegistration.getProvider(Name.class).firstName()).thenReturn("resolved first name");

        // When
        String result = invokeResolveExpression(expression, current, providerRegistration, context);

        // Then
        assertEquals("resolved first name", result);
    }

    @Test
    void testResolveExpressionWithValidCityName() {
        // Test with a valid expression that should resolve to a city name.
        String expression = "#{address.city}";
        Object current = null;

        // Mock the expected behavior for the city name resolution
        when(providerRegistration.getProvider(Address.class)).thenReturn(mock(Address.class));
        when(providerRegistration.getProvider(Address.class).city()).thenReturn("resolved city name");

        // When
        String result = invokeResolveExpression(expression, current, providerRegistration, context);

        // Then
        assertEquals("resolved city name", result);
    }

    @Test
    void testResolveExpressionWithInvalidDirective() {
        // Test with an invalid expression that cannot be resolved.
        String expression = "#{invalid.directive}";
        Object current = null;

        // When & Then
        assertThrows(RuntimeException.class, () -> {
            invokeResolveExpression(expression, current, providerRegistration, context);
        });
    }

    @Test
    void testResolveExpressionWithComplexExpression() {
        // Test with a complex expression that combines multiple expressions.
        String expression = "#{name.firstName} #{name.lastName}";
        Object current = null;

        // Mock the expected behavior for name resolution
        when(providerRegistration.getProvider(Name.class)).thenReturn(mock(Name.class));
        when(providerRegistration.getProvider(Name.class).firstName()).thenReturn("resolved first name");
        when(providerRegistration.getProvider(Name.class).lastName()).thenReturn("resolved last name");

        // When
        String result = invokeResolveExpression(expression, current, providerRegistration, context);

        // Then
        assertEquals("resolved first name resolved last name", result);
    }

    @Test
    void testResolveExpressionWithMixedValidAndInvalid() {
        // Test with a mixture of valid and invalid expressions.
        String expression = "#{name.firstName} #{invalid.directive}";
        Object current = null;

        // Mock the expected behavior for name resolution
        when(providerRegistration.getProvider(Name.class)).thenReturn(mock(Name.class));
        when(providerRegistration.getProvider(Name.class).firstName()).thenReturn("resolved first name");

        // When & Then
        assertThrows(RuntimeException.class, () -> {
            invokeResolveExpression(expression, current, providerRegistration, context);
        });
    }

    private String invokeResolveExpression(String expression, Object current, ProviderRegistration root, FakerContext context) {
        try {
            // Use reflection to access the private method
            Method method = FakeValuesService.class.getDeclaredMethod("resolveExpression", String.class, Object.class, ProviderRegistration.class, FakerContext.class);
            method.setAccessible(true);
            return (String) method.invoke(fakeValuesService, expression, current, root, context);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Failed to invoke resolveExpression method", e);
        }
    }
}
```