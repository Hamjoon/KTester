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
// Added import for MockedProvider
// import net.datafaker.providers.base.MockedProvider;
// Added import for Finance
import net.datafaker.providers.base.Finance;
// Added import for ClassName
// import net.datafaker.providers.base.ClassName;
// import net.datafaker.providers.base.ClassName;
// import net.datafaker.providers.base.MockedProvider;

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
    void resolveExpressionShouldResolveNestedProviderMethod() {
        // Given
        String directive = "name.first_name";
        String[] args = new String[] {};
        Object current = null;
        ProviderRegistration root = mock(ProviderRegistration.class);
        Name nameProvider = mock(Name.class);
        when(root.getProvider(Name.class, any())).thenReturn(nameProvider);
        when(nameProvider.firstName()).thenReturn("ExpectedFirstName");
        // When
        Object actualValue = invokeResolveExpression(directive, args, current, root, context);
        // Then
        assertEquals("ExpectedFirstName", actualValue, "Expected first name from Name provider.");
    }
}