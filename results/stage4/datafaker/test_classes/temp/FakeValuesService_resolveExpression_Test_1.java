package net.datafaker.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Locale;
import net.datafaker.providers.base.ProviderRegistration;
import java.lang.reflect.InvocationTargetException;
import net.datafaker.providers.base.Name;
import java.lang.reflect.Method;
import net.datafaker.providers.base.Address;

@ExtendWith(MockitoExtension.class)
class FakeValuesService_resolveExpression_Test {

    private FakeValuesService fakeValuesService;

    private FakerContext context;

    private ProviderRegistration providerRegistration;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the FakeValuesService and FakerContext before each test
        fakeValuesService = new FakeValuesService();
        providerRegistration = mock(ProviderRegistration.class);
        context = new FakerContext(Locale.ENGLISH, mock(RandomService.class));
    }

    // Utility method to invoke the private method using reflection
    private Object invokeResolveExpression(String directive, String[] args, Object current, ProviderRegistration root, FakerContext context) {
        try {
            Method method = FakeValuesService.class.getDeclaredMethod("resolveExpression", String.class, String[].class, Object.class, ProviderRegistration.class, FakerContext.class);
            method.setAccessible(true);
            return method.invoke(fakeValuesService, directive, args, current, root, context);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Failed to invoke resolveExpression", e);
        }
    }

    @Test
    void resolveExpressionShouldReturnCorrectValue() {
        // Given
        String directive = "name.first_name";
        String[] args = new String[] {};
        Object current = mock(Name.class);
        when(((Name) current).firstName()).thenReturn("John");
        // When
        Object actualValue = invokeResolveExpression(directive, args, current, providerRegistration, context);
        // Then
        assertEquals("John", actualValue);
    }

    @Test
    void resolveExpressionShouldHandleEmptyDirective() {
        // Given
        String directive = "";
        String[] args = new String[] {};
        Object current = null;
        // When
        Object actualValue = invokeResolveExpression(directive, args, current, providerRegistration, context);
        // Then
        assertEquals(directive, actualValue);
    }

    @Test
    void resolveExpressionShouldThrowExceptionForUnresolvableDirective() {
        // Given
        String directive = "#{unresolvable_directive}";
        String[] args = new String[] {};
        Object current = null;
        // When & Then
        assertThrows(RuntimeException.class, () -> {
            invokeResolveExpression(directive, args, current, providerRegistration, context);
        });
    }

    @Test
    void resolveExpressionShouldReturnEmptyStringForEmptyDirective() {
        // Given
        String directive = "";
        String[] args = new String[] {};
        Object current = null;
        // When
        Object actualValue = invokeResolveExpression(directive, args, current, providerRegistration, context);
        // Then
        assertEquals(directive, actualValue, "Expected empty string for empty directive.");
    }

    @Test
    void resolveExpressionShouldResolveMethodOnCurrentProviderWithEmptyArgs() {
        // Given
        String directive = "testMethod";
        String[] args = new String[] {};
        Object current = mock(MockedProvider.class);
        when(((MockedProvider) current).testMethod()).thenReturn("ExpectedValueFromTestMethod");
        // When
        Object actualValue = invokeResolveExpression(directive, args, current, providerRegistration, context);
        // Then
        assertEquals("ExpectedValueFromTestMethod", actualValue, "Expected value from testMethod.");
    }

    @Test
    void resolveExpressionShouldResolveNestedProviderMethod() {
        // Given
        String directive = "Name.first_name";
        String[] args = new String[] {};
        Object current = null;
        ProviderRegistration root = mock(ProviderRegistration.class);
        when(root.getProvider(Name.class)).thenReturn(mock(Name.class));
        when(((Name) root.getProvider(Name.class)).firstName()).thenReturn("ExpectedFirstName");
        // When
        Object actualValue = invokeResolveExpression(directive, args, current, root, context);
        // Then
        assertEquals("ExpectedFirstName", actualValue, "Expected first name from Name provider.");
    }

    @Test
    void resolveExpressionShouldResolveMethodOnCurrentProviderWithArgs() {
        // Given
        String directive = "testMethod";
        String[] args = new String[] { "arg1", "arg2" };
        Object current = mock(MockedProvider.class);
        when(((MockedProvider) current).testMethod("arg1", "arg2")).thenReturn("ExpectedValueWithArgs");
        // When
        Object actualValue = invokeResolveExpression(directive, args, current, providerRegistration, context);
        // Then
        assertEquals("ExpectedValueWithArgs", actualValue, "Expected value from testMethod with arguments.");
    }

    @Test
    void resolveExpressionShouldResolveClassMethodFromRootProvider() {
        // Given
        String directive = "ClassName.method_name";
        String[] args = new String[] {};
        Object current = null;
        ProviderRegistration root = mock(ProviderRegistration.class);
        when(root.getProvider(ClassName.class)).thenReturn(mock(ClassName.class));
        when(((ClassName) root.getProvider(ClassName.class)).methodName()).thenReturn("ExpectedValueFromClassMethod");
        // When
        Object actualValue = invokeResolveExpression(directive, args, current, root, context);
        // Then
        assertEquals("ExpectedValueFromClassMethod", actualValue, "Expected value from ClassName.method_name.");
    }

    @Test
    void resolveExpressionShouldThrowRuntimeExceptionForNonExistentMethod() {
        // Given
        String directive = "nonExistentMethod";
        String[] args = new String[] {};
        Object current = mock(MockedProvider.class);
        // When & Then
        assertThrows(RuntimeException.class, () -> {
            invokeResolveExpression(directive, args, current, providerRegistration, context);
        }, "Expected RuntimeException for non-existent method.");
    }

    @Test
    void resolveExpressionShouldResolveDynamicMethodFromAddressProvider() {
        // Given
        String directive = "Address.street_title";
        String[] args = new String[] {};
        Object current = null;
        ProviderRegistration root = mock(ProviderRegistration.class);
        when(root.getProvider(Address.class)).thenReturn(mock(Address.class));
        when(((Address) root.getProvider(Address.class)).streetTitle()).thenReturn("ExpectedStreetTitle");
        // When
        Object actualValue = invokeResolveExpression(directive, args, current, root, context);
        // Then
        assertEquals("ExpectedStreetTitle", actualValue, "Expected street title from Address provider.");
    }
}
