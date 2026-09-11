package net.datafaker.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Locale;
import java.util.stream.Stream;
import net.datafaker.providers.base.ProviderRegistration;
import org.junit.jupiter.params.ParameterizedTest;
import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class FakeValuesService_resolveExpression_2_Test {

    private FakeValuesService fakeValuesService;

    private FakerContext context;

    private ProviderRegistration providerRegistration;

    @BeforeEach
    void setupBeforeEach() {
        fakeValuesService = new FakeValuesService();
        context = new FakerContext(Locale.ENGLISH, mock(RandomService.class));
        providerRegistration = mock(ProviderRegistration.class);
    }

    private String invokeResolveExpression(String expression, Object current, ProviderRegistration root, FakerContext context) {
        try {
            Method method = FakeValuesService.class.getDeclaredMethod("resolveExpression", String.class, Object.class, ProviderRegistration.class, FakerContext.class);
            method.setAccessible(true);
            return (String) method.invoke(fakeValuesService, expression, current, root, context);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Failed to invoke resolveExpression", e);
        }
    }

    private static Stream<Arguments> resolveExpressionTestCases() {
        return Stream.of(Arguments.of("Hello, World!", null, "Hello, World!"), Arguments.of("Hello, #{name.firstName}!", null, "Hello, John!"), Arguments.of("Hello, #{name.firstName} #{name.lastName}!", null, "Hello, John Doe!"), Arguments.of("Hello, #{address.streetName}!", null, "Hello, 123 Main St!"), Arguments.of("#{name.firstName} #{name.lastName} #{address.city} #{address.country}", null, "John Doe New York USA"), Arguments.of("The price is #{number.numberBetween(1, 100)} dollars.", null, "The price is 42 dollars."), Arguments.of("#{company.name} is located at #{address.streetName} #{address.city}.", null, "Acme Corp is located at 123 Main St."));
    }

    private static Stream<Arguments> resolveExpressionExceptionTestCases() {
        return Stream.of(Arguments.of("Hello, #{unknownDirective}!", null, "Unable to resolve #{unknownDirective} directive for FakerContext null."), Arguments.of("#{name.firstName} #{surname} #{unknownDirective} #{address.city}", null, "Unable to resolve #{unknownDirective} directive for FakerContext null."), Arguments.of("#{name.firstName} #{surname} #{address.streetName} #{unknownDirective} #{address.city}", null, "Unable to resolve #{unknownDirective} directive for FakerContext null."), Arguments.of("#{unknownDirective}", null, "Unable to resolve #{unknownDirective} directive for FakerContext null."));
    }

    @Test
    void testResolveExpressionWithValidExpression() {
        String expression = "#{name.firstName}";
        Object current = null;
        String result = invokeResolveExpression(expression, current, providerRegistration, context);
        assertNotNull(result);
        assertEquals("John", result);
    }

    @Test
    void testResolveExpressionWithInvalidExpression() {
        String expression = "#{unknown}";
        Object current = null;
        assertThrows(RuntimeException.class, () -> {
            invokeResolveExpression(expression, current, providerRegistration, context);
        });
    }

    @ParameterizedTest
    @MethodSource("resolveExpressionTestCases")
    void testResolveExpression(String expression, Object current, String expected) {
        String result = invokeResolveExpression(expression, current, providerRegistration, context);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("resolveExpressionExceptionTestCases")
    void testResolveExpressionThrowsException(String expression, Object current, String expectedMessage) {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            invokeResolveExpression(expression, current, providerRegistration, context);
        });
        assertEquals(expectedMessage, exception.getMessage());
    }
}
