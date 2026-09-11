```java
package net.datafaker.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Locale;
import java.lang.reflect.InvocationTargetException;
import net.datafaker.providers.base.ProviderRegistration;
import net.datafaker.providers.base.Name;
import net.datafaker.providers.base.AbstractProvider;
import java.lang.reflect.Method;
// Added import for NumberProvider
import net.datafaker.providers.base.NumberProvider; // Corrected import for NumberProvider
// Added import for Company
import net.datafaker.providers.base.Company;

@ExtendWith(MockitoExtension.class)
class FakeValuesService_resolveExpression_Test {

    private FakeValuesService fakeValuesService;

    private FakerContext context;

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
        String expression = "#{firstName}";
        Object current = new DummyService();
        ProviderRegistration root = mock(ProviderRegistration.class);
        Object result = invokePrivateMethod("resolveExpression", expression, new String[] {}, current, root, context);
        assertEquals("John", result);
    }

    @Test
    void testResolveExpressionWithInvalidDirective() {
        String expression = "#{invalidDirective}";
        Object current = new DummyService();
        ProviderRegistration root = mock(ProviderRegistration.class);
        assertThrows(RuntimeException.class, () -> {
            invokePrivateMethod("resolveExpression", expression, new String[] {}, current, root, context);
        });
    }

    @Test
    void testResolveExpressionWithEmptyDirective() {
        String expression = "";
        Object current = null;
        ProviderRegistration root = null;
        Object result = invokePrivateMethod("resolveExpression", expression, new String[] {}, current, root, context);
        assertEquals("", result);
    }

    @Test
    void testResolveExpressionWithValidDirectiveNoDotIndexCurrentIsAbstractProvider() {
        String expression = "firstName"; // Changed to match method name
        Object current = mock(AbstractProvider.class);
        when(((AbstractProvider<?>) current).firstName()).thenReturn("Expected first name from the provider");
        ProviderRegistration root = null;
        Object result = invokePrivateMethod("resolveExpression", expression, new String[] {}, current, root, context);
        assertEquals("Expected first name from the provider", result);
    }

    @Test
    void testResolveExpressionWithInvalidDirectiveNoDotIndex() {
        String expression = "invalid_method";
        Object current = new Object();
        ProviderRegistration root = null;
        Object result = invokePrivateMethod("resolveExpression", expression, new String[] {}, current, root, context);
        assertNull(result);
    }

    @Test
    void testResolveExpressionWithDotIndex() {
        String expression = "Name.firstName"; // Changed to match method name
        Object current = null;
        ProviderRegistration root = mock(ProviderRegistration.class);
        // Updated to use class type
        when(root.getProvider(Name.class, null)).thenReturn(mock(Name.class));
        when(((Name) root.getProvider(Name.class, null)).firstName()).thenReturn("Expected first name from Name provider");
        Object result = invokePrivateMethod("resolveExpression", expression, new String[] {}, current, root, context);
        assertEquals("Expected first name from Name provider", result);
    }

    @Test
    void testResolveExpressionLastDitchEffortFetch() {
        String expression = "Address.street_title";
        Object current = null;
        ProviderRegistration root = mock(ProviderRegistration.class);
        when(fakeValuesService.safeFetch("address.street_title", context, null)).thenReturn("Expected street title from Address provider");
        Object result = invokePrivateMethod("resolveExpression", expression, new String[] {}, current, root, context);
        assertEquals("Expected street title from Address provider", result);
    }

    @Test
    void testResolveExpressionWithArguments() {
        String expression = "numberBetween"; // Changed to match method name
        String[] args = { "1", "10" };
        Object current = mock(NumberProvider.class); // Corrected instantiation of NumberProvider
        when(((NumberProvider) current).numberBetween(1, 10)).thenReturn("A number between 1 and 10");
        ProviderRegistration root = null;
        Object result = invokePrivateMethod("resolveExpression", expression, args, current, root, context);
        assertEquals("A number between 1 and 10", result);
    }

    @Test
    void testResolveExpressionInvalidDirectiveWithDotIndex() {
        String expression = "InvalidProvider.invalid_method";
        Object current = null;
        ProviderRegistration root = mock(ProviderRegistration.class);
        Object result = invokePrivateMethod("resolveExpression", expression, new String[] {}, current, root, context);
        assertNull(result);
    }

    @Test
    void testResolveExpressionCurrentIsNullAndValidProvider() {
        String expression = "Company.name"; // Changed to match method name
        Object current = null;
        ProviderRegistration root = mock(ProviderRegistration.class);
        // Updated to use class type
        when(root.getProvider(Company.class, null)).thenReturn(mock(Company.class));
        when(((Company) root.getProvider(Company.class, null)).name()).thenReturn("Expected company name from Company provider");
        Object result = invokePrivateMethod("resolveExpression", expression, new String[] {}, current, root, context);
        assertEquals("Expected company name from Company provider", result);
    }

    @Test
    void testResolveExpressionExceptionScenarios() {
        String expression = "firstName"; // Changed to match method name
        Object current = mock(AbstractProvider.class);
        when(((AbstractProvider<?>) current).firstName()).thenThrow(new RuntimeException("Method invocation failed"));
        ProviderRegistration root = null;
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            invokePrivateMethod("resolveExpression", expression, new String[] {}, current, root, context);
        });
        assertEquals("Method invocation failed", thrown.getMessage());
    }
}
```