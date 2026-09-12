package net.datafaker.service;

import net.datafaker.providers.base.ProviderRegistration;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import net.datafaker.providers.base.BaseFaker;
import net.datafaker.providers.base.AbstractProvider;
import java.util.Locale;
import java.util.function.Supplier;
// Add other imports if necessary
import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class FakeValuesService_resolveExpression_Test {

    @Spy
    private FakeValuesService fakeValuesService;

    @Mock
    private ProviderRegistration providerRegistration;

    @Mock
    private RandomService randomService;

    private FakerContext context;

    @BeforeEach
    void setupBeforeEach() {
        context = new FakerContext(Locale.ENGLISH, randomService);
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
    void testResolveExpressionWithValidDirective() {
        // given
        String directive = "name.name";
        String[] args = new String[0];
        Object current = mock(AbstractProvider.class);
        // when
        Object result = invokeResolveExpression(directive, args, current, providerRegistration, context);
        // then
        assertNotNull(result);
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
        when(BaseFaker.getProvider("provider", context)).thenReturn((AbstractProvider) provider);
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
        when(fakeValuesService.safeFetch(directive, context, (String) null)).thenReturn("safeFetchResult");
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
        // Mock the behavior instead of directly calling the private method
        when(fakeValuesService.safeFetch(directive, context, (String) null)).thenReturn("mockedMethodInvokeResult");
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
        String[] args = new String[] { "arg1", "arg2" };
        AbstractProvider<?> current = mock(AbstractProvider.class);
        Supplier<Object> supplier = mock(Supplier.class);
        // Mock the behavior instead of directly calling the private method
        when(fakeValuesService.safeFetch(directive, context, args)).thenReturn("mockedMethodInvokeResultWithArgs");
        // when
        Object result = invokeResolveExpression(directive, args, current, null, context);
        // then
        assertEquals("mockedMethodInvokeResultWithArgs", result);
    }

    @Test
    void testResolveExpressionWithProviderMethodAndArguments() {
        // given
        String directive = "provider.methodName";
        String[] args = new String[] { "arg1" };
        AbstractProvider<?> provider = mock(AbstractProvider.class);
        when(BaseFaker.getProvider("provider", context)).thenReturn((AbstractProvider) provider);
        Supplier<Object> supplier = mock(Supplier.class);
        // Mock the behavior instead of directly calling the private method
        when(fakeValuesService.safeFetch(directive, context, args)).thenReturn("mockedMethodInvokeResultWithArgs");
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
        when(fakeValuesService.safeFetch(directive, context, (String) null)).thenReturn("safeFetchResult");
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
        when(fakeValuesService.safeFetch(directive, context, (String) null)).thenReturn("safeFetchResult");
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
}
