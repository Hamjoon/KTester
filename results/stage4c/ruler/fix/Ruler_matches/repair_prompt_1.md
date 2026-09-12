


You are an experienced Java developer tasked with fixing a piece of code that contains execution errors. Your task is to resolve these issues after analyzing the code and its execution feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any logical errors.
2. Analyze the execution feedback to understand the specific errors and their locations within the code.
3. Apply necessary corrections to the code based on the feedback, and output the compelete correct code.
The Java code you need to fix is provided below:
```java
package software.amazon.event.ruler;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class Ruler_matches_Test {

    private static ObjectMapper objectMapper;

    @BeforeAll
    static void setupBeforeAll() {
        objectMapper = new ObjectMapper();
    }

    private boolean invokeMatches(JsonNode val, Patterns pattern) {
        try {
            java.lang.reflect.Method method = Ruler.class.getDeclaredMethod("matches", JsonNode.class, Patterns.class);
            method.setAccessible(true);
            return (boolean) method.invoke(null, val, pattern);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testMatchesExact() {
        // Given
        JsonNode val = objectMapper.createObjectNode().put("key", "value");
        Patterns pattern = mock(Patterns.class);
        when(pattern.type()).thenReturn(MatchType.EXACT);
        when(pattern.pattern()).thenReturn("\"value\"");
        // When
        boolean result = invokeMatches(val, pattern);
        // Then
        assertTrue(result);
    }

    @Test
    void testMatchesPrefix() {
        // Given
        JsonNode val = objectMapper.createObjectNode().put("key", "test");
        Patterns pattern = mock(Patterns.class);
        when(pattern.type()).thenReturn(MatchType.PREFIX);
        when(pattern.pattern()).thenReturn("\"te");
        // When
        boolean result = invokeMatches(val, pattern);
        // Then
        assertTrue(result);
    }

    @Test
    void testMatchesAnythingBut() {
        // Given
        JsonNode val = objectMapper.createObjectNode().put("key", "test");
        AnythingBut pattern = mock(AnythingBut.class);
        when(pattern.type()).thenReturn(MatchType.ANYTHING_BUT);
        when(pattern.getValues()).thenReturn(Set.of("\"notest\""));
        // When
        boolean result = invokeMatches(val, pattern);
        // Then
        assertTrue(result);
    }

    @Test
    void testMatchesAnythingButIgnoreCase() {
        // Given
        JsonNode val = objectMapper.createObjectNode().put("key", "test");
        AnythingButEqualsIgnoreCase pattern = mock(AnythingButEqualsIgnoreCase.class);
        when(pattern.type()).thenReturn(MatchType.ANYTHING_BUT_IGNORE_CASE);
        when(pattern.getValues()).thenReturn(Set.of("\"TEST\""));
        // When
        boolean result = invokeMatches(val, pattern);
        // Then
        assertFalse(result);
    }

    @Test
    void testMatchesAnythingButSuffix() {
        // Given
        JsonNode val = objectMapper.createObjectNode().put("key", "test");
        Patterns pattern = mock(Patterns.class);
        when(pattern.type()).thenReturn(MatchType.ANYTHING_BUT_SUFFIX);
        when(pattern.pattern()).thenReturn("\"est\"");
        // When
        boolean result = invokeMatches(val, pattern);
        // Then
        assertFalse(result);
    }

    @Test
    void testMatchesAnythingButPrefix() {
        // Given
        JsonNode val = objectMapper.createObjectNode().put("key", "test");
        Patterns pattern = mock(Patterns.class);
        when(pattern.type()).thenReturn(MatchType.ANYTHING_BUT_PREFIX);
        when(pattern.pattern()).thenReturn("\"te");
        // When
        boolean result = invokeMatches(val, pattern);
        // Then
        assertFalse(result);
    }

    @Test
    void testMatchesNumericEq() {
        // Given
        JsonNode val = objectMapper.createObjectNode().put("key", 42);
        Patterns pattern = mock(Patterns.class);
        when(pattern.type()).thenReturn(MatchType.NUMERIC_EQ);
        when(pattern.pattern()).thenReturn("42");
        // When
        boolean result = invokeMatches(val, pattern);
        // Then
        assertTrue(result);
    }

    @Test
    void testMatchesEqualsIgnoreCase() {
        // Given
        JsonNode val = objectMapper.createObjectNode().put("key", "test");
        Patterns pattern = mock(Patterns.class);
        when(pattern.type()).thenReturn(MatchType.EQUALS_IGNORE_CASE);
        when(pattern.pattern()).thenReturn("\"TEST\"");
        // When
        boolean result = invokeMatches(val, pattern);
        // Then
        assertTrue(result);
    }

    @Test
    void testMatchesWildcard() {
        // Given
        JsonNode val = objectMapper.createObjectNode().put("key", "test");
        Patterns pattern = mock(Patterns.class);
        when(pattern.type()).thenReturn(MatchType.WILDCARD);
        when(pattern.pattern()).thenReturn("\"t*st\"");
        // When
        boolean result = invokeMatches(val, pattern);
        // Then
        assertTrue(result);
    }

    @Test
    void testMatchesUnsupportedPatternType() {
        // Given
        JsonNode val = objectMapper.createObjectNode().put("key", "test");
        Patterns pattern = mock(Patterns.class);
        // Simulate unsupported pattern type
        when(pattern.type()).thenReturn(null);
        // When / Then
        assertThrows(RuntimeException.class, () -> invokeMatches(val, pattern));
    }

    @Test
    void testMatchesNumericRange() {
        // Given
        JsonNode val = objectMapper.createObjectNode().put("key", 42);
        Range pattern = mock(Range.class);
        when(pattern.type()).thenReturn(MatchType.NUMERIC_RANGE);
        when(pattern.isCIDR).thenReturn(false);
        when(pattern.bottom).thenReturn("30".getBytes());
        when(pattern.top).thenReturn("50".getBytes());
        when(pattern.openBottom).thenReturn(false);
        when(pattern.openTop).thenReturn(false);
        // When
        boolean result = invokeMatches(val, pattern);
        // Then
        assertTrue(result);
    }

    @Test
    void testMatchesExists() {
        // Given
        JsonNode val = objectMapper.createObjectNode().put("key", "example");
        Patterns pattern = mock(Patterns.class);
        when(pattern.type()).thenReturn(MatchType.EXISTS);
        // When
        boolean result = invokeMatches(val, pattern);
        // Then
        assertTrue(result);
    }

    @Test
    void testMatchesAbsent() {
        // Given
        JsonNode val = objectMapper.createObjectNode().put("key", "example");
        Patterns pattern = mock(Patterns.class);
        when(pattern.type()).thenReturn(MatchType.ABSENT);
        // When
        boolean result = invokeMatches(val, pattern);
        // Then
        assertFalse(result);
    }
}

```

Corresponding execution feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended

╷
├─ JUnit Jupiter ✔
│  └─ Ruler_matches_Test ✔
│     ├─ testMatchesAnythingButPrefix() ✘ java.lang.reflect.InvocationTargetException
│     ├─ testMatchesAnythingButSuffix() ✘ java.lang.reflect.InvocationTargetException
│     ├─ testMatchesNumericRange() ✘ 
│     │        when() requires an argument which has to be 'a method call on a mock'.
│     │        For example:
│     │            when(mock.getArticles()).thenReturn(articles);
│     │     
│     │        Also, this error might show up because:
│     │        1. you stub either of: final/private/equals()/hashCode() methods.
│     │           Those methods *cannot* be stubbed/verified.
│     │           Mocking methods declared on non-public parent classes is not supported.
│     │        2. inside when() you don't call method on mock but on some other object.
│     ├─ testMatchesExact() ✘ java.lang.reflect.InvocationTargetException
│     ├─ testMatchesNumericEq() ✘ java.lang.reflect.InvocationTargetException
│     ├─ testMatchesUnsupportedPatternType() ✔
│     ├─ testMatchesEqualsIgnoreCase() ✘ java.lang.reflect.InvocationTargetException
│     ├─ testMatchesWildcard() ✘ java.lang.reflect.InvocationTargetException
│     ├─ testMatchesAnythingButIgnoreCase() ✘ 
│     │        Unnecessary stubbings detected.
│     │        Clean & maintainable test code requires zero unnecessary code.
│     │        Following stubbings are unnecessary (click to navigate to relevant line of code):
│     │          1. -> at software.amazon.event.ruler.Ruler_matches_Test.testMatchesAnythingButIgnoreCase(Ruler_matches_Test.java:78)
│     │        Please remove unnecessary stubbings or use 'lenient' strictness. More info: javadoc for UnnecessaryStubbingException class.
│     ├─ testMatchesAnythingBut() ✘ expected: <true> but was: <false>
│     ├─ testMatchesAbsent() ✔
│     ├─ testMatchesExists() ✔
│     └─ testMatchesPrefix() ✘ java.lang.reflect.InvocationTargetException
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Failures (10):
  JUnit Jupiter:Ruler_matches_Test:testMatchesAnythingButPrefix()
    MethodSource [className = 'software.amazon.event.ruler.Ruler_matches_Test', methodName = 'testMatchesAnythingButPrefix', methodParameterTypes = '']
    => java.lang.RuntimeException: java.lang.reflect.InvocationTargetException
       software.amazon.event.ruler.Ruler_matches_Test.invokeMatches(Ruler_matches_Test.java:29)
       software.amazon.event.ruler.Ruler_matches_Test.testMatchesAnythingButPrefix(Ruler_matches_Test.java:106)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.Ruler_matches_Test.invokeMatches(Ruler_matches_Test.java:27)
       [...]
     Caused by: java.lang.ClassCastException: class software.amazon.event.ruler.Patterns cannot be cast to class software.amazon.event.ruler.ValuePatterns (software.amazon.event.ruler.Patterns and software.amazon.event.ruler.ValuePatterns are in unnamed module of loader 'app')
       software.amazon.event.ruler.Ruler.matches(Ruler.java:135)
       [...]
  JUnit Jupiter:Ruler_matches_Test:testMatchesAnythingButSuffix()
    MethodSource [className = 'software.amazon.event.ruler.Ruler_matches_Test', methodName = 'testMatchesAnythingButSuffix', methodParameterTypes = '']
    => java.lang.RuntimeException: java.lang.reflect.InvocationTargetException
       software.amazon.event.ruler.Ruler_matches_Test.invokeMatches(Ruler_matches_Test.java:29)
       software.amazon.event.ruler.Ruler_matches_Test.testMatchesAnythingButSuffix(Ruler_matches_Test.java:93)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.Ruler_matches_Test.invokeMatches(Ruler_matches_Test.java:27)
       [...]
     Caused by: java.lang.ClassCastException: class software.amazon.event.ruler.Patterns cannot be cast to class software.amazon.event.ruler.ValuePatterns (software.amazon.event.ruler.Patterns and software.amazon.event.ruler.ValuePatterns are in unnamed module of loader 'app')
       software.amazon.event.ruler.Ruler.matches(Ruler.java:132)
       [...]
  JUnit Jupiter:Ruler_matches_Test:testMatchesNumericRange()
    MethodSource [className = 'software.amazon.event.ruler.Ruler_matches_Test', methodName = 'testMatchesNumericRange', methodParameterTypes = '']
    => org.mockito.exceptions.misusing.MissingMethodInvocationException: 
when() requires an argument which has to be 'a method call on a mock'.
For example:
    when(mock.getArticles()).thenReturn(articles);

Also, this error might show up because:
1. you stub either of: final/private/equals()/hashCode() methods.
   Those methods *cannot* be stubbed/verified.
   Mocking methods declared on non-public parent classes is not supported.
2. inside when() you don't call method on mock but on some other object.

       software.amazon.event.ruler.Ruler_matches_Test.testMatchesNumericRange(Ruler_matches_Test.java:167)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:767)
       org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
       org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
       org.junit.jupiter.engine.extension.TimeoutExtension.intercept(TimeoutExtension.java:156)
       org.junit.jupiter.engine.extension.TimeoutExtension.interceptTestableMethod(TimeoutExtension.java:147)
       [...]
  JUnit Jupiter:Ruler_matches_Test:testMatchesExact()
    MethodSource [className = 'software.amazon.event.ruler.Ruler_matches_Test', methodName = 'testMatchesExact', methodParameterTypes = '']
    => java.lang.RuntimeException: java.lang.reflect.InvocationTargetException
       software.amazon.event.ruler.Ruler_matches_Test.invokeMatches(Ruler_matches_Test.java:29)
       software.amazon.event.ruler.Ruler_matches_Test.testMatchesExact(Ruler_matches_Test.java:41)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.Ruler_matches_Test.invokeMatches(Ruler_matches_Test.java:27)
       [...]
     Caused by: java.lang.ClassCastException: class software.amazon.event.ruler.Patterns cannot be cast to class software.amazon.event.ruler.ValuePatterns (software.amazon.event.ruler.Patterns and software.amazon.event.ruler.ValuePatterns are in unnamed module of loader 'app')
       software.amazon.event.ruler.Ruler.matches(Ruler.java:104)
       [...]
  JUnit Jupiter:Ruler_matches_Test:testMatchesNumericEq()
    MethodSource [className = 'software.amazon.event.ruler.Ruler_matches_Test', methodName = 'testMatchesNumericEq', methodParameterTypes = '']
    => java.lang.RuntimeException: java.lang.reflect.InvocationTargetException
       software.amazon.event.ruler.Ruler_matches_Test.invokeMatches(Ruler_matches_Test.java:29)
       software.amazon.event.ruler.Ruler_matches_Test.testMatchesNumericEq(Ruler_matches_Test.java:119)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.Ruler_matches_Test.invokeMatches(Ruler_matches_Test.java:27)
       [...]
     Caused by: java.lang.ClassCastException: class software.amazon.event.ruler.Patterns cannot be cast to class software.amazon.event.ruler.ValuePatterns (software.amazon.event.ruler.Patterns and software.amazon.event.ruler.ValuePatterns are in unnamed module of loader 'app')
       software.amazon.event.ruler.Ruler.matches(Ruler.java:139)
       [...]
  JUnit Jupiter:Ruler_matches_Test:testMatchesEqualsIgnoreCase()
    MethodSource [className = 'software.amazon.event.ruler.Ruler_matches_Test', methodName = 'testMatchesEqualsIgnoreCase', methodParameterTypes = '']
    => java.lang.RuntimeException: java.lang.reflect.InvocationTargetException
       software.amazon.event.ruler.Ruler_matches_Test.invokeMatches(Ruler_matches_Test.java:29)
       software.amazon.event.ruler.Ruler_matches_Test.testMatchesEqualsIgnoreCase(Ruler_matches_Test.java:132)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.Ruler_matches_Test.invokeMatches(Ruler_matches_Test.java:27)
       [...]
     Caused by: java.lang.ClassCastException: class software.amazon.event.ruler.Patterns cannot be cast to class software.amazon.event.ruler.ValuePatterns (software.amazon.event.ruler.Patterns and software.amazon.event.ruler.ValuePatterns are in unnamed module of loader 'app')
       software.amazon.event.ruler.Ruler.matches(Ruler.java:170)
       [...]
  JUnit Jupiter:Ruler_matches_Test:testMatchesWildcard()
    MethodSource [className = 'software.amazon.event.ruler.Ruler_matches_Test', methodName = 'testMatchesWildcard', methodParameterTypes = '']
    => java.lang.RuntimeException: java.lang.reflect.InvocationTargetException
       software.amazon.event.ruler.Ruler_matches_Test.invokeMatches(Ruler_matches_Test.java:29)
       software.amazon.event.ruler.Ruler_matches_Test.testMatchesWildcard(Ruler_matches_Test.java:145)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.Ruler_matches_Test.invokeMatches(Ruler_matches_Test.java:27)
       [...]
     Caused by: java.lang.ClassCastException: class software.amazon.event.ruler.Patterns cannot be cast to class software.amazon.event.ruler.ValuePatterns (software.amazon.event.ruler.Patterns and software.amazon.event.ruler.ValuePatterns are in unnamed module of loader 'app')
       software.amazon.event.ruler.Ruler.matches(Ruler.java:173)
       [...]
  JUnit Jupiter:Ruler_matches_Test:testMatchesAnythingButIgnoreCase()
    MethodSource [className = 'software.amazon.event.ruler.Ruler_matches_Test', methodName = 'testMatchesAnythingButIgnoreCase', methodParameterTypes = '']
    => org.mockito.exceptions.misusing.UnnecessaryStubbingException: 
Unnecessary stubbings detected.
Clean & maintainable test code requires zero unnecessary code.
Following stubbings are unnecessary (click to navigate to relevant line of code):
  1. -> at software.amazon.event.ruler.Ruler_matches_Test.testMatchesAnythingButIgnoreCase(Ruler_matches_Test.java:78)
Please remove unnecessary stubbings or use 'lenient' strictness. More info: javadoc for UnnecessaryStubbingException class.
       org.mockito.junit.jupiter.MockitoExtension.afterEach(MockitoExtension.java:186)
       org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$invokeAfterEachCallbacks$13(TestMethodTestDescriptor.java:260)
       org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$invokeAllAfterMethodsOrCallbacks$14(TestMethodTestDescriptor.java:276)
       org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
       org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$invokeAllAfterMethodsOrCallbacks$15(TestMethodTestDescriptor.java:276)
       org.junit.platform.commons.util.CollectionUtils.forEachInReverseOrder(CollectionUtils.java:240)
       org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.invokeAllAfterMethodsOrCallbacks(TestMethodTestDescriptor.java:275)
       org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.invokeAfterEachCallbacks(TestMethodTestDescriptor.java:259)
       org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:144)
       org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:68)
       [...]
  JUnit Jupiter:Ruler_matches_Test:testMatchesAnythingBut()
    MethodSource [className = 'software.amazon.event.ruler.Ruler_matches_Test', methodName = 'testMatchesAnythingBut', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <true> but was: <false>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertTrue.failNotTrue(AssertTrue.java:63)
       org.junit.jupiter.api.AssertTrue.assertTrue(AssertTrue.java:36)
       org.junit.jupiter.api.AssertTrue.assertTrue(AssertTrue.java:31)
       org.junit.jupiter.api.Assertions.assertTrue(Assertions.java:183)
       software.amazon.event.ruler.Ruler_matches_Test.testMatchesAnythingBut(Ruler_matches_Test.java:69)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:Ruler_matches_Test:testMatchesPrefix()
    MethodSource [className = 'software.amazon.event.ruler.Ruler_matches_Test', methodName = 'testMatchesPrefix', methodParameterTypes = '']
    => java.lang.RuntimeException: java.lang.reflect.InvocationTargetException
       software.amazon.event.ruler.Ruler_matches_Test.invokeMatches(Ruler_matches_Test.java:29)
       software.amazon.event.ruler.Ruler_matches_Test.testMatchesPrefix(Ruler_matches_Test.java:54)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.Ruler_matches_Test.invokeMatches(Ruler_matches_Test.java:27)
       [...]
     Caused by: java.lang.ClassCastException: class software.amazon.event.ruler.Patterns cannot be cast to class software.amazon.event.ruler.ValuePatterns (software.amazon.event.ruler.Patterns and software.amazon.event.ruler.ValuePatterns are in unnamed module of loader 'app')
       software.amazon.event.ruler.Ruler.matches(Ruler.java:110)
       [...]

Test run finished after 546 ms
[         4 containers found      ]
[         0 containers skipped    ]
[         4 containers started    ]
[         0 containers aborted    ]
[         4 containers successful ]
[         0 containers failed     ]
[        13 tests found           ]
[         0 tests skipped         ]
[        13 tests started         ]
[         0 tests aborted         ]
[         3 tests successful      ]
[        10 tests failed          ]


```


And you can use the following related information to assist you in your analysis and correction:

api document of class software.amazon.event.ruler.Ruler: 

 * The core idea of Ruler is to match rules to events at a rate that's independent of the number of rules.  This is
 *  achieved by compiling the rules into an automaton, at some up-front cost for compilation and memory use. There
 *  are some users who are unable to persist the compiled automaton but still like the "Event Pattern" idiom and want
 *  to match events with those semantics.
 * The memory cost is proportional to the product of the number of possible values provided in the rule and can
 *  grow surprisingly large
 * This class matches a single rule to a single event without any precompilation, using brute-force techniques, but
 *  with no up-front compute or memory cost.
 

return type: 
boolean

dependent classes: 
class `software.amazon.event.ruler.Patterns`:
	
class `software.amazon.event.ruler.MatchType`:
	dependent fields:
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.ANYTHING_BUT_PREFIX;
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.EXISTS;
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.WILDCARD;
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.ABSENT;
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.EXACT;
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.ANYTHING_BUT_SUFFIX;
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.PREFIX;
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.ANYTHING_BUT_IGNORE_CASE;
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.EQUALS_IGNORE_CASE;
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.NUMERIC_EQ;
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.NUMERIC_RANGE;
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.ANYTHING_BUT;
	
class `software.amazon.event.ruler.Range`:
	dependent fields:
		boolean software.amazon.event.ruler.Range.openBottom;
		boolean software.amazon.event.ruler.Range.openTop;
		boolean software.amazon.event.ruler.Range.isCIDR;
		byte[] software.amazon.event.ruler.Range.bottom;
		byte[] software.amazon.event.ruler.Range.top;
	related functions:
		method `public boolean equals(Object)` returns `boolean`, related with `software.amazon.event.ruler.Ruler.matches(JsonNode, Patterns)`;
		method `public String toString()` returns `java.lang.String`, related with `software.amazon.event.ruler.Ruler.matches(JsonNode, Patterns)`;
class `software.amazon.event.ruler.ByteMachine`:
	related functions:
		method ` void deletePattern(Patterns)` returns `void`, related with `software.amazon.event.ruler.Ruler.matches(JsonNode, Patterns)`;
		method `private void doTransitionOn(String, Set<NameState>, boolean)` returns `void`, related with `software.amazon.event.ruler.Ruler.matches(JsonNode, Patterns)`;
		method `private void addMatchReferences(ByteMatch)` returns `void`, related with `software.amazon.event.ruler.Ruler.matches(JsonNode, Patterns)`;
		method ` NameState findPattern(Patterns)` returns `software.amazon.event.ruler.NameState`, related with `software.amazon.event.ruler.Ruler.matches(JsonNode, Patterns)`;
class `software.amazon.event.ruler.input.DefaultParser`:
	related functions:
		method `public InputCharacter[] parse(MatchType, String)` returns `software.amazon.event.ruler.input.InputCharacter[]`, related with `software.amazon.event.ruler.Ruler.matches(JsonNode, Patterns)`;



Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 
