


You are an experienced Java developer tasked with fixing a piece of code that contains execution errors. Your task is to resolve these issues after analyzing the code and its execution feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any logical errors.
2. Analyze the execution feedback to understand the specific errors and their locations within the code.
3. Apply necessary corrections to the code based on the feedback, and output the compelete correct code.
The Java code you need to fix is provided below:
```java
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
        return Stream.of(Arguments.of("Hello, World!", null, "Hello, World!"), // Adjusted for valid name usage
        Arguments.of("Hello, #{name.firstName}!", null, "Hello, John!"), // Adjusted for valid name usage
        Arguments.of("Hello, #{name.firstName} #{name.lastName}!", null, "Hello, John Doe!"), // Adjusted for valid address usage
        Arguments.of("Hello, #{address.streetName}!", null, "Hello, 123 Main St!"), // Adjusted for valid name usage
        Arguments.of("#{name.firstName} #{name.lastName} #{address.city} #{address.country}", null, "John Doe New York USA"), // Adjusted for valid number usage
        Arguments.of("The price is #{number.numberBetween(1, 100)} dollars.", null, "The price is 42 dollars."), // Adjusted for valid company and address usage
        Arguments.of("#{company.name} is located at #{address.streetName} #{address.city}.", null, "Acme Corp is located at 123 Main St."));
    }

    private static Stream<Arguments> resolveExpressionExceptionTestCases() {
        return Stream.of(Arguments.of("Hello, #{unknownDirective}!", null, "Unable to resolve #{unknownDirective} directive for FakerContext null."), Arguments.of("#{name.firstName} #{surname} #{unknownDirective} #{address.city}", null, "Unable to resolve #{unknownDirective} directive for FakerContext null."), Arguments.of("#{name.firstName} #{surname} #{address.streetName} #{unknownDirective} #{address.city}", null, "Unable to resolve #{unknownDirective} directive for FakerContext null."), Arguments.of("#{unknownDirective}", null, "Unable to resolve #{unknownDirective} directive for FakerContext null."));
    }

    @Test
    void testResolveExpressionWithValidExpression() {
        // Adjusted to a valid expression
        String expression = "#{name.firstName}";
        Object current = null;
        String result = invokeResolveExpression(expression, current, providerRegistration, context);
        assertNotNull(result);
        // Adjusted based on expected outcome
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

```

Corresponding execution feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended

╷
├─ JUnit Jupiter ✔
│  └─ FakeValuesService_resolveExpression_2_Test ✔
│     ├─ testResolveExpression(String, Object, String) ✔
│     │  ├─ [1] Hello, World!, null, Hello, World! ✔
│     │  ├─ [2] Hello, #{name.firstName}!, null, Hello, John! ✘ Failed to invoke resolveExpression
│     │  ├─ [3] Hello, #{name.firstName} #{name.lastName}!, null, Hello, John Doe! ✘ Failed to invoke resolveExpression
│     │  ├─ [4] Hello, #{address.streetName}!, null, Hello, 123 Main St! ✘ Failed to invoke resolveExpression
│     │  ├─ [5] #{name.firstName} #{name.lastName} #{address.city} #{address.country}, null,... ✘ Failed to invoke resolveExpression
│     │  ├─ [6] The price is #{number.numberBetween(1, 100)} dollars., null, The price is 42... ✘ Failed to invoke resolveExpression
│     │  └─ [7] #{company.name} is located at #{address.streetName} #{address.city}., null, ... ✘ Failed to invoke resolveExpression
│     ├─ testResolveExpressionWithValidExpression() ✘ Failed to invoke resolveExpression
│     ├─ testResolveExpressionWithInvalidExpression() ✔
│     └─ testResolveExpressionThrowsException(String, Object, String) ✔
│        ├─ [1] Hello, #{unknownDirective}!, null, Unable to resolve #{unknownDirective} dir... ✘ expected: <Unable to resolve #{unknownDirective} directive for FakerContext null.> but was: <Failed to invoke resolveExpression>
│        ├─ [2] #{name.firstName} #{surname} #{unknownDirective} #{address.city}, null, Unab... ✘ expected: <Unable to resolve #{unknownDirective} directive for FakerContext null.> but was: <Failed to invoke resolveExpression>
│        ├─ [3] #{name.firstName} #{surname} #{address.streetName} #{unknownDirective} #{add... ✘ expected: <Unable to resolve #{unknownDirective} directive for FakerContext null.> but was: <Failed to invoke resolveExpression>
│        └─ [4] #{unknownDirective}, null, Unable to resolve #{unknownDirective} directive f... ✘ expected: <Unable to resolve #{unknownDirective} directive for FakerContext null.> but was: <Failed to invoke resolveExpression>
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Failures (11):
  JUnit Jupiter:FakeValuesService_resolveExpression_2_Test:testResolveExpression(String, Object, String):[2] Hello, #{name.firstName}!, null, Hello, John!
    MethodSource [className = 'net.datafaker.service.FakeValuesService_resolveExpression_2_Test', methodName = 'testResolveExpression', methodParameterTypes = 'java.lang.String, java.lang.Object, java.lang.String']
    => java.lang.RuntimeException: Failed to invoke resolveExpression
       net.datafaker.service.FakeValuesService_resolveExpression_2_Test.invokeResolveExpression(FakeValuesService_resolveExpression_2_Test.java:40)
       net.datafaker.service.FakeValuesService_resolveExpression_2_Test.testResolveExpression(FakeValuesService_resolveExpression_2_Test.java:81)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       net.datafaker.service.FakeValuesService_resolveExpression_2_Test.invokeResolveExpression(FakeValuesService_resolveExpression_2_Test.java:38)
       [...]
     Caused by: java.lang.RuntimeException: Unable to resolve #{name.firstName} directive for FakerContext FakerContext{, locale=net.datafaker.internal.helper.SingletonLocale@5ccc4de8, randomService=Mock for RandomService, hashCode: 663277822}.
       net.datafaker.service.FakeValuesService.resolveExpression(FakeValuesService.java:572)
       [...]
  JUnit Jupiter:FakeValuesService_resolveExpression_2_Test:testResolveExpression(String, Object, String):[3] Hello, #{name.firstName} #{name.lastName}!, null, Hello, John Doe!
    MethodSource [className = 'net.datafaker.service.FakeValuesService_resolveExpression_2_Test', methodName = 'testResolveExpression', methodParameterTypes = 'java.lang.String, java.lang.Object, java.lang.String']
    => java.lang.RuntimeException: Failed to invoke resolveExpression
       net.datafaker.service.FakeValuesService_resolveExpression_2_Test.invokeResolveExpression(FakeValuesService_resolveExpression_2_Test.java:40)
       net.datafaker.service.FakeValuesService_resolveExpression_2_Test.testResolveExpression(FakeValuesService_resolveExpression_2_Test.java:81)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       net.datafaker.service.FakeValuesService_resolveExpression_2_Test.invokeResolveExpression(FakeValuesService_resolveExpression_2_Test.java:38)
       [...]
     Caused by: java.lang.RuntimeException: Unable to resolve #{name.firstName} directive for FakerContext FakerContext{, locale=net.datafaker.internal.helper.SingletonLocale@5ccc4de8, randomService=Mock for RandomService, hashCode: 362432678}.
       net.datafaker.service.FakeValuesService.resolveExpression(FakeValuesService.java:572)
       [...]
  JUnit Jupiter:FakeValuesService_resolveExpression_2_Test:testResolveExpression(String, Object, String):[4] Hello, #{address.streetName}!, null, Hello, 123 Main St!
    MethodSource [className = 'net.datafaker.service.FakeValuesService_resolveExpression_2_Test', methodName = 'testResolveExpression', methodParameterTypes = 'java.lang.String, java.lang.Object, java.lang.String']
    => java.lang.RuntimeException: Failed to invoke resolveExpression
       net.datafaker.service.FakeValuesService_resolveExpression_2_Test.invokeResolveExpression(FakeValuesService_resolveExpression_2_Test.java:40)
       net.datafaker.service.FakeValuesService_resolveExpression_2_Test.testResolveExpression(FakeValuesService_resolveExpression_2_Test.java:81)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       net.datafaker.service.FakeValuesService_resolveExpression_2_Test.invokeResolveExpression(FakeValuesService_resolveExpression_2_Test.java:38)
       [...]
     Caused by: java.lang.RuntimeException: Unable to resolve #{address.streetName} directive for FakerContext FakerContext{, locale=net.datafaker.internal.helper.SingletonLocale@5ccc4de8, randomService=Mock for RandomService, hashCode: 945747249}.
       net.datafaker.service.FakeValuesService.resolveExpression(FakeValuesService.java:572)
       [...]
  JUnit Jupiter:FakeValuesService_resolveExpression_2_Test:testResolveExpression(String, Object, String):[5] #{name.firstName} #{name.lastName} #{address.city} #{address.country}, null, John Doe New York USA
    MethodSource [className = 'net.datafaker.service.FakeValuesService_resolveExpression_2_Test', methodName = 'testResolveExpression', methodParameterTypes = 'java.lang.String, java.lang.Object, java.lang.String']
    => java.lang.RuntimeException: Failed to invoke resolveExpression
       net.datafaker.service.FakeValuesService_resolveExpression_2_Test.invokeResolveExpression(FakeValuesService_resolveExpression_2_Test.java:40)
       net.datafaker.service.FakeValuesService_resolveExpression_2_Test.testResolveExpression(FakeValuesService_resolveExpression_2_Test.java:81)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       net.datafaker.service.FakeValuesService_resolveExpression_2_Test.invokeResolveExpression(FakeValuesService_resolveExpression_2_Test.java:38)
       [...]
     Caused by: java.lang.RuntimeException: Unable to resolve #{name.firstName} directive for FakerContext FakerContext{, locale=net.datafaker.internal.helper.SingletonLocale@5ccc4de8, randomService=Mock for RandomService, hashCode: 1929661663}.
       net.datafaker.service.FakeValuesService.resolveExpression(FakeValuesService.java:572)
       [...]
  JUnit Jupiter:FakeValuesService_resolveExpression_2_Test:testResolveExpression(String, Object, String):[6] The price is #{number.numberBetween(1, 100)} dollars., null, The price is 42 dollars.
    MethodSource [className = 'net.datafaker.service.FakeValuesService_resolveExpression_2_Test', methodName = 'testResolveExpression', methodParameterTypes = 'java.lang.String, java.lang.Object, java.lang.String']
    => java.lang.RuntimeException: Failed to invoke resolveExpression
       net.datafaker.service.FakeValuesService_resolveExpression_2_Test.invokeResolveExpression(FakeValuesService_resolveExpression_2_Test.java:40)
       net.datafaker.service.FakeValuesService_resolveExpression_2_Test.testResolveExpression(FakeValuesService_resolveExpression_2_Test.java:81)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       net.datafaker.service.FakeValuesService_resolveExpression_2_Test.invokeResolveExpression(FakeValuesService_resolveExpression_2_Test.java:38)
       [...]
     Caused by: java.lang.RuntimeException: Unable to resolve #{number.numberBetween(1, 100)} directive for FakerContext FakerContext{, locale=net.datafaker.internal.helper.SingletonLocale@5ccc4de8, randomService=Mock for RandomService, hashCode: 161672347}.
       net.datafaker.service.FakeValuesService.resolveExpression(FakeValuesService.java:572)
       [...]
  JUnit Jupiter:FakeValuesService_resolveExpression_2_Test:testResolveExpression(String, Object, String):[7] #{company.name} is located at #{address.streetName} #{address.city}., null, Acme Corp is located at 123 Main St.
    MethodSource [className = 'net.datafaker.service.FakeValuesService_resolveExpression_2_Test', methodName = 'testResolveExpression', methodParameterTypes = 'java.lang.String, java.lang.Object, java.lang.String']
    => java.lang.RuntimeException: Failed to invoke resolveExpression
       net.datafaker.service.FakeValuesService_resolveExpression_2_Test.invokeResolveExpression(FakeValuesService_resolveExpression_2_Test.java:40)
       net.datafaker.service.FakeValuesService_resolveExpression_2_Test.testResolveExpression(FakeValuesService_resolveExpression_2_Test.java:81)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       net.datafaker.service.FakeValuesService_resolveExpression_2_Test.invokeResolveExpression(FakeValuesService_resolveExpression_2_Test.java:38)
       [...]
     Caused by: java.lang.RuntimeException: Unable to resolve #{company.name} directive for FakerContext FakerContext{, locale=net.datafaker.internal.helper.SingletonLocale@5ccc4de8, randomService=Mock for RandomService, hashCode: 822409149}.
       net.datafaker.service.FakeValuesService.resolveExpression(FakeValuesService.java:572)
       [...]
  JUnit Jupiter:FakeValuesService_resolveExpression_2_Test:testResolveExpressionWithValidExpression()
    MethodSource [className = 'net.datafaker.service.FakeValuesService_resolveExpression_2_Test', methodName = 'testResolveExpressionWithValidExpression', methodParameterTypes = '']
    => java.lang.RuntimeException: Failed to invoke resolveExpression
       net.datafaker.service.FakeValuesService_resolveExpression_2_Test.invokeResolveExpression(FakeValuesService_resolveExpression_2_Test.java:40)
       net.datafaker.service.FakeValuesService_resolveExpression_2_Test.testResolveExpressionWithValidExpression(FakeValuesService_resolveExpression_2_Test.java:63)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       net.datafaker.service.FakeValuesService_resolveExpression_2_Test.invokeResolveExpression(FakeValuesService_resolveExpression_2_Test.java:38)
       [...]
     Caused by: java.lang.RuntimeException: Unable to resolve #{name.firstName} directive for FakerContext FakerContext{, locale=net.datafaker.internal.helper.SingletonLocale@5ccc4de8, randomService=Mock for RandomService, hashCode: 1787083839}.
       net.datafaker.service.FakeValuesService.resolveExpression(FakeValuesService.java:572)
       [...]
  JUnit Jupiter:FakeValuesService_resolveExpression_2_Test:testResolveExpressionThrowsException(String, Object, String):[1] Hello, #{unknownDirective}!, null, Unable to resolve #{unknownDirective} directive for FakerContext null.
    MethodSource [className = 'net.datafaker.service.FakeValuesService_resolveExpression_2_Test', methodName = 'testResolveExpressionThrowsException', methodParameterTypes = 'java.lang.String, java.lang.Object, java.lang.String']
    => org.opentest4j.AssertionFailedError: expected: <Unable to resolve #{unknownDirective} directive for FakerContext null.> but was: <Failed to invoke resolveExpression>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1145)
       net.datafaker.service.FakeValuesService_resolveExpression_2_Test.testResolveExpressionThrowsException(FakeValuesService_resolveExpression_2_Test.java:91)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:FakeValuesService_resolveExpression_2_Test:testResolveExpressionThrowsException(String, Object, String):[2] #{name.firstName} #{surname} #{unknownDirective} #{address.city}, null, Unable to resolve #{unknownDirective} directive for FakerContext null.
    MethodSource [className = 'net.datafaker.service.FakeValuesService_resolveExpression_2_Test', methodName = 'testResolveExpressionThrowsException', methodParameterTypes = 'java.lang.String, java.lang.Object, java.lang.String']
    => org.opentest4j.AssertionFailedError: expected: <Unable to resolve #{unknownDirective} directive for FakerContext null.> but was: <Failed to invoke resolveExpression>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1145)
       net.datafaker.service.FakeValuesService_resolveExpression_2_Test.testResolveExpressionThrowsException(FakeValuesService_resolveExpression_2_Test.java:91)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:FakeValuesService_resolveExpression_2_Test:testResolveExpressionThrowsException(String, Object, String):[3] #{name.firstName} #{surname} #{address.streetName} #{unknownDirective} #{address.city}, null, Unable to resolve #{unknownDirective} directive for FakerContext null.
    MethodSource [className = 'net.datafaker.service.FakeValuesService_resolveExpression_2_Test', methodName = 'testResolveExpressionThrowsException', methodParameterTypes = 'java.lang.String, java.lang.Object, java.lang.String']
    => org.opentest4j.AssertionFailedError: expected: <Unable to resolve #{unknownDirective} directive for FakerContext null.> but was: <Failed to invoke resolveExpression>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1145)
       net.datafaker.service.FakeValuesService_resolveExpression_2_Test.testResolveExpressionThrowsException(FakeValuesService_resolveExpression_2_Test.java:91)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:FakeValuesService_resolveExpression_2_Test:testResolveExpressionThrowsException(String, Object, String):[4] #{unknownDirective}, null, Unable to resolve #{unknownDirective} directive for FakerContext null.
    MethodSource [className = 'net.datafaker.service.FakeValuesService_resolveExpression_2_Test', methodName = 'testResolveExpressionThrowsException', methodParameterTypes = 'java.lang.String, java.lang.Object, java.lang.String']
    => org.opentest4j.AssertionFailedError: expected: <Unable to resolve #{unknownDirective} directive for FakerContext null.> but was: <Failed to invoke resolveExpression>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1145)
       net.datafaker.service.FakeValuesService_resolveExpression_2_Test.testResolveExpressionThrowsException(FakeValuesService_resolveExpression_2_Test.java:91)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]

Test run finished after 552 ms
[         6 containers found      ]
[         0 containers skipped    ]
[         6 containers started    ]
[         0 containers aborted    ]
[         6 containers successful ]
[         0 containers failed     ]
[        13 tests found           ]
[         0 tests skipped         ]
[        13 tests started         ]
[         0 tests aborted         ]
[         2 tests successful      ]
[        11 tests failed          ]


```


And you can use the following related information to assist you in your analysis and correction:

api document of method resolveExpression(String, Object, ProviderRegistration, FakerContext): 

     * processes a expression in the style #{X.y} using the current objects as the 'current' location
     * within the yml file (or the {@link BaseFaker} object hierarchy as it were).
     * <p>
     * #{Address.streetName} would get resolved to {@link BaseFaker#address()}'s {@link Address#streetName()}
     * #{address.street} would get resolved to the YAML like locale: faker: address: street:
     * Combinations are supported as well: "#{x} #{y}"
     * <p>
     * Recursive templates are supported.  if "#{x}" resolves to "#{Address.streetName}" then "#{x}" resolves to
     * {@link BaseFaker#address()}'s {@link Address#streetName()}.
     

dependent classes: 
class `net.datafaker.service.FakerContext`:
	
class `net.datafaker.service.FakeValuesService`:
	related functions:
		method `protected String resolveExpression(String, Object, ProviderRegistration, FakerContext)` returns `java.lang.String`, related with `net.datafaker.service.FakeValuesService.resolveExpression(String, Object, ProviderRegistration, FakerContext)`, api document: 
     * processes a expression in the style #{X.y} using the current objects as the 'current' location
     * within the yml file (or the {@link BaseFaker} object hierarchy as it were).
     * <p>
     * #{Address.streetName} would get resolved to {@link BaseFaker#address()}'s {@link Address#streetName()}
     * #{address.street} would get resolved to the YAML like locale: faker: address: street:
     * Combinations are supported as well: "#{x} #{y}"
     * <p>
     * Recursive templates are supported.  if "#{x}" resolves to "#{Address.streetName}" then "#{x}" resolves to
     * {@link BaseFaker#address()}'s {@link Address#streetName()}.
     ;
class `net.datafaker.service.FakerIDN`:
	related functions:
		method `public static String toASCII(String)` returns `java.lang.String`, related with `net.datafaker.service.FakeValuesService.resolveExpression(String, Object, ProviderRegistration, FakerContext)`, api document: 
     * {@link IDN#toASCII(String)} is too picky for our needs.  It was throwing exceptions for fa.yml and
     * he.yml as they're Bidi languages and something was causing them to die.  This is kind of a brute force
     * fix but it appears to fix the issue.
     ;
class `net.datafaker.transformations.sql.SqlTransformer`:
	related functions:
		method `private String generateBatchModeStatements(Schema<IN, ?>, List<IN>, int)` returns `java.lang.String`, related with `net.datafaker.service.FakeValuesService.resolveExpression(String, Object, ProviderRegistration, FakerContext)`;
		method `private String appendTableInfo(Field<?, ? extends CharSequence>[])` returns `java.lang.String`, related with `net.datafaker.service.FakeValuesService.resolveExpression(String, Object, ProviderRegistration, FakerContext)`;
class `net.datafaker.providers.base.Finance`:
	related functions:
		method `private static String padLeftZeros(String, int)` returns `java.lang.String`, related with `net.datafaker.service.FakeValuesService.resolveExpression(String, Object, ProviderRegistration, FakerContext)`;
class `net.datafaker.providers.base.Internet`:
	related functions:
		method `public String slug(List<String>, String)` returns `java.lang.String`, related with `net.datafaker.service.FakeValuesService.resolveExpression(String, Object, ProviderRegistration, FakerContext)`, api document: 
     * @param wordsOrNull if null, then 2 {@link Lorem} words
     * @param glueOrNull  if null, "_"
     * @return a slug string combining wordsOrNull with glueOrNull (ex. x_y)
     ;
class `net.datafaker.formats.Json.JsonForFakeCollection`:
	related functions:
		method `public String generate()` returns `java.lang.String`, related with `net.datafaker.service.FakeValuesService.resolveExpression(String, Object, ProviderRegistration, FakerContext)`;
class `net.datafaker.providers.base.Name`:
	related functions:
		method `public String username()` returns `java.lang.String`, related with `net.datafaker.service.FakeValuesService.resolveExpression(String, Object, ProviderRegistration, FakerContext)`, api document: 
     * A lowercase username composed of the first_name and last_name joined with a '.'. Some examples are:
     * <ul>
     *     <li>(template) {@link #firstName()}.{@link #lastName()}</li>
     *     <li>jim.jones</li>
     *     <li>jason.leigh</li>
     *     <li>tracy.jordan</li>
     * </ul>
     *
     * @return a random two part user name.
     * @see Name#firstName()
     * @see Name#lastName()
     ;



Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 
