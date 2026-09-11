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
        fakeValuesService = new FakeValuesService();
        context = new FakerContext(Locale.ENGLISH, mock(RandomService.class));
        providerRegistration = mock(ProviderRegistration.class);
    }

    @AfterEach
    void teardownAfterEach() {
        // Any cleanup after each test can be done here
    }

    @AfterAll
    static void teardownAfterAll() {
        // Any global cleanup can be done here
    }

    @ParameterizedTest
    @MethodSource("resolveExpressionTestCases")
    void testResolveExpression(String expression, Object current, String expected) {
        // Test with valid expressions
        String result = invokeResolveExpression(expression, current, providerRegistration, context);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("resolveExpressionExceptionTestCases")
    void testResolveExpressionThrowsException(String expression, Object current, String expectedMessage) {
        // Test with invalid expressions that should throw exceptions
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            invokeResolveExpression(expression, current, providerRegistration, context);
        });
        assertEquals(expectedMessage, exception.getMessage());
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
        return Stream.of(
            Arguments.of("Hello, World!", null, "Hello, World!"),
            Arguments.of("Hello, #{name}!", null, "Hello, John Doe!"),
            Arguments.of("Hello, #{name} #{surname}!", null, "Hello, John Doe!"),
            Arguments.of("Hello, #{address.street}!", null, "Hello, 123 Main St!"),
            Arguments.of("#{name} #{surname} #{address.city} #{address.country}", null, "John Doe New York USA"),
            Arguments.of("The price is #{number} dollars.", null, "The price is 42 dollars."),
            Arguments.of("#{company.name} is located at #{address.street} #{address.city}.", null, "Acme Corp is located at 123 Main St.")
        );
    }

    private static Stream<Arguments> resolveExpressionExceptionTestCases() {
        return Stream.of(
            Arguments.of("Hello, #{unknownDirective}!", null, "Unable to resolve #{unknownDirective} directive for FakerContext null."),
            Arguments.of("#{name} #{surname} #{unknownDirective} #{address.city}", null, "Unable to resolve #{unknownDirective} directive for FakerContext null."),
            Arguments.of("#{name} #{surname} #{address.street} #{unknownDirective} #{address.city}", null, "Unable to resolve #{unknownDirective} directive for FakerContext null."),
            Arguments.of("#{unknownDirective}", null, "Unable to resolve #{unknownDirective} directive for FakerContext null.")
        );
    }
}
```