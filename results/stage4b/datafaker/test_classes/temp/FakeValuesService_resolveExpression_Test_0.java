package net.datafaker.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Locale;

@ExtendWith(MockitoExtension.class)
class FakeValuesService_resolveExpression_Test {

    private FakeValuesService fakeValuesService;

    private FakerContext context;

    // Add more tests to cover various scenarios for resolveExpression
    public static class DummyService {

        public String firstName() {
            return "John";
        }

        public String lastName() {
            return "Smith";
        }

        public String hello() {
            return "Hello";
        }
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the FakerContext and FakeValuesService before each test
        RandomService randomService = mock(RandomService.class);
        context = new FakerContext(new Locale("en"), randomService);
        fakeValuesService = Mockito.spy(new FakeValuesService());
    }

    // Helper method to invoke private methods using reflection
    private Object invokePrivateMethod(String methodName, Object... params) {
        try {
            Method method = FakeValuesService.class.getDeclaredMethod(methodName, String.class, String[].class, Object.class, ProviderRegistration.class, FakerContext.class);
            method.setAccessible(true);
            return method.invoke(fakeValuesService, params);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testResolveExpressionWithValidDirective() {
        // Example test for a valid directive
        String expression = "#{firstName}";
        // Assuming DummyService has a firstName() method
        Object current = new DummyService();
        ProviderRegistration root = mock(ProviderRegistration.class);
        // Call the method under test
        Object result = fakeValuesService.resolveExpression(expression, new String[] {}, current, root, context);
        // Assert the expected outcome
        // Assuming DummyService returns "John"
        assertEquals("John", result);
    }

    @Test
    void testResolveExpressionWithInvalidDirective() {
        // Example test for an invalid directive
        String expression = "#{invalidDirective}";
        Object current = new DummyService();
        ProviderRegistration root = mock(ProviderRegistration.class);
        // Call the method under test and assert that it throws the expected exception
        assertThrows(RuntimeException.class, () -> {
            fakeValuesService.resolveExpression(expression, new String[] {}, current, root, context);
        });
    }

    @Test
    void testResolveExpressionWithEmptyDirective() {
        // Test case where the directive is empty. The method should return an empty string.
        String expression = "";
        Object current = null;
        ProviderRegistration root = null;
        // Call the method under test
        Object result = invokePrivateMethod("resolveExpression", expression, new String[] {}, current, root, context);
        // Assert the expected outcome
        assertEquals("", result);
    }

    @Test
    void testResolveExpressionWithValidDirectiveNoDotIndexCurrentIsAbstractProvider() {
        // Test case where directive is a method on the AbstractProvider.
        // The method should be invoked and return the first name.
        String expression = "first_name";
        Object current = mock(AbstractProvider.class);
        when(((AbstractProvider<?>) current).firstName()).thenReturn("Expected first name from the provider");
        ProviderRegistration root = null;
        // Call the method under test
        Object result = invokePrivateMethod("resolveExpression", expression, new String[] {}, current, root, context);
        // Assert the expected outcome
        assertEquals("Expected first name from the provider", result);
    }

    @Test
    void testResolveExpressionWithInvalidDirectiveNoDotIndex() {
        // Test case where directive is invalid and current is not an AbstractProvider.
        // The method should return null.
        String expression = "invalid_method";
        // Not an AbstractProvider
        Object current = new Object();
        ProviderRegistration root = null;
        // Call the method under test
        Object result = invokePrivateMethod("resolveExpression", expression, new String[] {}, current, root, context);
        // Assert the expected outcome
        assertNull(result);
    }

    @Test
    void testResolveExpressionWithDotIndex() {
        // Test case where the directive includes a dot.
        // The method should resolve to the Name provider and return the first name.
        String expression = "Name.first_name";
        Object current = null;
        ProviderRegistration root = mock(ProviderRegistration.class);
        when(root.getProvider("Name")).thenReturn(mock(Name.class));
        when(((Name) root.getProvider("Name")).firstName()).thenReturn("Expected first name from Name provider");
        // Call the method under test
        Object result = invokePrivateMethod("resolveExpression", expression, new String[] {}, current, root, context);
        // Assert the expected outcome
        assertEquals("Expected first name from Name provider", result);
    }

    @Test
    void testResolveExpressionLastDitchEffortFetch() {
        // Test case where the directive cannot be resolved normally,
        // but should fall back to safeFetch for Address.street_title.
        String expression = "Address.street_title";
        Object current = null;
        ProviderRegistration root = mock(ProviderRegistration.class);
        when(fakeValuesService.safeFetch("address.street_title", context, null)).thenReturn("Expected street title from Address provider");
        // Call the method under test
        Object result = invokePrivateMethod("resolveExpression", expression, new String[] {}, current, root, context);
        // Assert the expected outcome
        assertEquals("Expected street title from Address provider", result);
    }

    @Test
    void testResolveExpressionWithArguments() {
        // Test case where the directive is a method on the current provider that takes arguments.
        // The method should return a number between the specified range.
        String expression = "number_between";
        String[] args = { "1", "10" };
        Object current = mock(NumberProvider.class);
        when(((NumberProvider) current).numberBetween(1, 10)).thenReturn("A number between 1 and 10");
        ProviderRegistration root = null;
        // Call the method under test
        Object result = invokePrivateMethod("resolveExpression", expression, args, current, root, context);
        // Assert the expected outcome
        assertEquals("A number between 1 and 10", result);
    }

    @Test
    void testResolveExpressionInvalidDirectiveWithDotIndex() {
        // Test case where the directive contains an invalid provider and method.
        // The method should return null.
        String expression = "InvalidProvider.invalid_method";
        Object current = null;
        ProviderRegistration root = mock(ProviderRegistration.class);
        // Call the method under test
        Object result = invokePrivateMethod("resolveExpression", expression, new String[] {}, current, root, context);
        // Assert the expected outcome
        assertNull(result);
    }

    @Test
    void testResolveExpressionCurrentIsNullAndValidProvider() {
        // Test case where the directive is valid but current is null.
        // The method should resolve to the Company provider and return a company name.
        String expression = "Company.name";
        Object current = null;
        ProviderRegistration root = mock(ProviderRegistration.class);
        when(root.getProvider("Company")).thenReturn(mock(Company.class));
        when(((Company) root.getProvider("Company")).name()).thenReturn("Expected company name from Company provider");
        // Call the method under test
        Object result = invokePrivateMethod("resolveExpression", expression, new String[] {}, current, root, context);
        // Assert the expected outcome
        assertEquals("Expected company name from Company provider", result);
    }

    @Test
    void testResolveExpressionExceptionScenarios() {
        // Test case where the method invocation on AbstractProvider throws an exception, causing a RuntimeException.
        String expression = "first_name";
        Object current = mock(AbstractProvider.class);
        when(((AbstractProvider<?>) current).firstName()).thenThrow(new RuntimeException("Method invocation failed"));
        ProviderRegistration root = null;
        // Call the method under test and assert that it throws the expected exception
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            invokePrivateMethod("resolveExpression", expression, new String[] {}, current, root, context);
        });
        // Assert the exception message
        assertEquals("Method invocation failed", thrown.getMessage());
    }
}
