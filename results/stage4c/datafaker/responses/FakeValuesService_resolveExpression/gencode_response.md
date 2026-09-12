```java
package net.datafaker.service;

import net.datafaker.providers.base.ProviderRegistration;
import net.datafaker.providers.base.AbstractProvider;
import net.datafaker.providers.base.BaseFaker;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Locale;
import java.util.function.Supplier;
import java.lang.reflect.Method;

// Add other imports if necessary

@ExtendWith(MockitoExtension.class)
class FakeValuesService_resolveExpression_Test {

    @Spy
    private FakeValuesService fakeValuesService;

    @Mock
    private ProviderRegistration providerRegistration;

    @Mock
    private RandomService randomService;

    private FakerContext context;

    @BeforeAll
    static void setupBeforeAll() {
        // Initialize resources if needed
    }

    @BeforeEach
    void setupBeforeEach() {
        context = new FakerContext(Locale.ENGLISH, randomService);
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup resources if needed
    }

    @AfterAll
    static void teardownAfterAll() {
        // Cleanup resources if needed
    }

    @Test
    void testResolveExpressionWithEmptyDirective() {
        // given
        String directive = "";
        String[] args = new String[0];
        Object current = mock(AbstractProvider.class);

        // when
        Object result = invokeResolveExpression(directive, args, current, providerRegistration, context);

        // then
        assertEquals("", result);
    }

    @Test
    void testResolveExpressionWithValidMethodOnCurrent() {
        // given
        String directive = "methodName";
        String[] args = new String[0];
        AbstractProvider<?> current = mock(AbstractProvider.class);
        Method method = mock(Method.class);
        when(BaseFaker.getMethod(current, directive)).thenReturn(method);
        when(method.invoke(current)).thenReturn("mockedMethodInvokeResult");

        // when
        Object result = invokeResolveExpression(directive, args, current, null, context);

        // then
        assertEquals("mockedMethodInvokeResult", result);
    }

    @Test
    void testResolveExpressionWithValidProviderMethod() {
        // given
        String directive = "provider.methodName";
        String[] args = new String[0];
        AbstractProvider<?> provider = mock(AbstractProvider.class);
        when(BaseFaker.getProvider("provider", context)).thenReturn(provider);
        Method method = mock(Method.class);
        when(BaseFaker.getMethod(provider, "methodName")).thenReturn(method);
        when(method.invoke(provider)).thenReturn("mockedMethodInvokeResult");

        // when
        Object result = invokeResolveExpression(directive, args, null, providerRegistration, context);

        // then
        assertEquals("mockedMethodInvokeResult", result);
    }

    @Test
    void testResolveExpressionWithNonExistentMethod() {
        // given
        String directive = "nonExistentMethod";
        String[] args = new String[0];
        AbstractProvider<?> current = mock(AbstractProvider.class);

        // Mock safeFetch to return a specific value
        when(fakeValuesService.safeFetch(directive, context, null)).thenReturn("safeFetchResult");

        // when
        Object result = invokeResolveExpression(directive, args, current, null, context);

        // then
        assertEquals("safeFetchResult", result);
    }

    @Test
    void testResolveExpressionWithFakerObjectMethod() {
        // given
        String directive = "ClassName.methodName";
        String[] args = new String[0];
        Supplier<Object> supplier = mock(Supplier.class);
        when(fakeValuesService.resolveFakerObjectAndMethod(providerRegistration, directive, directive.indexOf('.'), args)).thenReturn(supplier);
        when(supplier.get()).thenReturn("mockedMethodInvokeResult");

        // when
        Object result = invokeResolveExpression(directive, args, null, providerRegistration, context);

        // then
        assertEquals("mockedMethodInvokeResult", result);
    }

    @Test
    void testResolveExpressionWithInvalidDirective() {
        // given
        String directive = "invalid.method";
        String[] args = new String[0];

        // when
        Object result = invokeResolveExpression(directive, args, null, null, context);

        // then
        assertNull(result);
    }

    @Test
    void testResolveExpressionWithMethodAndArguments() {
        // given
        String directive = "methodName";
        String[] args = new String[]{"arg1", "arg2"};
        AbstractProvider<?> current = mock(AbstractProvider.class);
        Supplier<Object> supplier = mock(Supplier.class);
        when(fakeValuesService.resolveFromMethodOn(current, directive, args)).thenReturn(supplier);
        when(supplier.get()).thenReturn("mockedMethodInvokeResultWithArgs");

        // when
        Object result = invokeResolveExpression(directive, args, current, null, context);

        // then
        assertEquals("mockedMethodInvokeResultWithArgs", result);
    }

    @Test
    void testResolveExpressionWithProviderMethodAndArguments() {
        // given
        String directive = "provider.methodName";
        String[] args = new String[]{"arg1"};
        AbstractProvider<?> provider = mock(AbstractProvider.class);
        when(BaseFaker.getProvider("provider", context)).thenReturn(provider);
        Supplier<Object> supplier = mock(Supplier.class);
        when(fakeValuesService.resolveFromMethodOn(provider, "methodName", args)).thenReturn(supplier);
        when(supplier.get()).thenReturn("mockedMethodInvokeResultWithArgs");

        // when
        Object result = invokeResolveExpression(directive, args, null, providerRegistration, context);

        // then
        assertEquals("mockedMethodInvokeResultWithArgs", result);
    }

    @Test
    void testResolveExpressionWithNonAbstractProvider() {
        // given
        String directive = "methodName";
        String[] args = new String[0];
        Object current = mock(Object.class);

        // Mock safeFetch to return a specific value
        when(fakeValuesService.safeFetch(directive, context, null)).thenReturn("safeFetchResult");

        // when
        Object result = invokeResolveExpression(directive, args, current, null, context);

        // then
        assertEquals("safeFetchResult", result);
    }

    @Test
    void testResolveExpressionWithNonExistentClass() {
        // given
        String directive = "nonExistentClass.methodName";
        String[] args = new String[0];

        // Mock safeFetch to return a specific value
        when(fakeValuesService.safeFetch(directive, context, null)).thenReturn("safeFetchResult");

        // when
        Object result = invokeResolveExpression(directive, args, null, providerRegistration, context);

        // then
        assertEquals("safeFetchResult", result);
    }

    @Test
    void testResolveExpressionWithNullRootAndCurrent() {
        // given
        String directive = "methodName";
        String[] args = new String[0];

        // when
        Object result = invokeResolveExpression(directive, args, null, null, context);

        // then
        assertNull(result);
    }

    // Helper method to invoke the private resolveExpression method using reflection
    private Object invokeResolveExpression(String directive, String[] args, Object current, ProviderRegistration root, FakerContext context) {
        try {
            Method method = FakeValuesService.class.getDeclaredMethod("resolveExpression", String.class, String[].class, Object.class, ProviderRegistration.class, FakerContext.class);
            method.setAccessible(true);
            return method.invoke(fakeValuesService, directive, args, current, root, context);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
```