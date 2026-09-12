package net.datafaker.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import net.datafaker.providers.base.ProviderRegistration;
import net.datafaker.service.FakerContext;
import java.util.Locale;
import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class FakeValuesService_resolveExpression_2_Test {

    @Mock
    private ProviderRegistration providerRegistration;

    @Mock
    private RandomService randomService;

    private FakeValuesService fakeValuesService;

    private FakerContext context;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the FakeValuesService and FakerContext before each test
        fakeValuesService = new FakeValuesService();
        context = new FakerContext(Locale.ENGLISH, randomService);
    }

    private String invokeResolveExpression(String expression, Object current, ProviderRegistration providerRegistration, FakerContext context) throws Exception {
        Method method = FakeValuesService.class.getDeclaredMethod("resolveExpression", String.class, Object.class, ProviderRegistration.class, FakerContext.class);
        method.setAccessible(true);
        return (String) method.invoke(fakeValuesService, expression, current, providerRegistration, context);
    }

    @Test
    void testResolveExpression() throws Exception {
        // Given
        String expression = "#{directive}";
        Object current = null;
        // When / Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            try {
                invokeResolveExpression(expression, current, providerRegistration, context);
            } catch (Exception e) {
                throw e.getCause() instanceof RuntimeException ? (RuntimeException) e.getCause() : new RuntimeException(e);
            }
        }, "Expected a RuntimeException for unresolved directive.");
        assertTrue(exception.getMessage().contains("Unable to resolve"), "Exception message should indicate inability to resolve.");
    }

    @Test
    void testResolveExpressionSimpleText() throws Exception {
        // Given
        String expression = "simple text";
        Object current = null;
        // When
        String result = invokeResolveExpression(expression, current, providerRegistration, context);
        // Then
        assertEquals("simple text", result, "Expected the same simple text to be returned.");
    }

    @Test
    void testResolveExpressionUnresolvedDirective() {
        // Given
        String expression = "#{directive}";
        Object current = null;
        // When / Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            try {
                invokeResolveExpression(expression, current, providerRegistration, context);
            } catch (Exception e) {
                throw e.getCause() instanceof RuntimeException ? (RuntimeException) e.getCause() : new RuntimeException(e);
            }
        }, "Expected a RuntimeException for unresolved directive.");
        assertTrue(exception.getMessage().contains("Unable to resolve"), "Exception message should indicate inability to resolve.");
    }

    @Test
    void testResolveExpressionValidDirectives() throws Exception {
        // Given
        String expression = "#{name.firstName} #{name.lastName}";
        Object current = null;
        // Mock the behavior of the resolveExpression method for valid directives
        when(fakeValuesService.resolveExpression("name.firstName", current, providerRegistration, context)).thenReturn("John");
        when(fakeValuesService.resolveExpression("name.lastName", current, providerRegistration, context)).thenReturn("Doe");
        // When
        String result = invokeResolveExpression(expression, current, providerRegistration, context);
        // Then
        assertEquals("John Doe", result, "Expected resolved full name.");
    }

    @Test
    void testResolveExpressionMixedTextAndDirective() throws Exception {
        // Given
        String expression = "Hello, #{name.firstName}!";
        Object current = null;
        // Mock the behavior of the resolveExpression method for valid directive
        when(fakeValuesService.resolveExpression("name.firstName", current, providerRegistration, context)).thenReturn("Alice");
        // When
        String result = invokeResolveExpression(expression, current, providerRegistration, context);
        // Then
        assertEquals("Hello, Alice!", result, "Expected resolved text with a first name.");
    }

    @Test
    void testResolveExpressionUnknownDirective() {
        // Given
        String expression = "#{unknown.directive}";
        Object current = null;
        // When / Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            try {
                invokeResolveExpression(expression, current, providerRegistration, context);
            } catch (Exception e) {
                throw e.getCause() instanceof RuntimeException ? (RuntimeException) e.getCause() : new RuntimeException(e);
            }
        }, "Expected a RuntimeException for unknown directive.");
        assertTrue(exception.getMessage().contains("Unable to resolve"), "Exception message should indicate inability to resolve.");
    }

    @Test
    void testResolveExpressionEmptyDirective() {
        // Given
        String expression = "{}";
        Object current = null;
        // When / Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            try {
                invokeResolveExpression(expression, current, providerRegistration, context);
            } catch (Exception e) {
                throw e.getCause() instanceof RuntimeException ? (RuntimeException) e.getCause() : new RuntimeException(e);
            }
        }, "Expected a RuntimeException for empty directive.");
        assertTrue(exception.getMessage().contains("Unable to resolve"), "Exception message should indicate inability to resolve.");
    }
}
