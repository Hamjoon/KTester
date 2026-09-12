


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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class ByteMachine_addMatchReferences_Test {

    private ByteMachine byteMachine;

    private ByteMatch byteMatchMock;

    private Patterns patternMock;

    private NameState nameStateMock;

    @BeforeEach
    void setupBeforeEach() {
        byteMachine = new ByteMachine();
        patternMock = mock(Patterns.class);
        nameStateMock = mock(NameState.class);
        byteMatchMock = mock(ByteMatch.class);
        when(byteMatchMock.getPattern()).thenReturn(patternMock);
        when(byteMatchMock.getNextNameState()).thenReturn(nameStateMock);
    }

    private void invokeAddMatchReferences(ByteMatch byteMatch) throws Exception {
        Method method = ByteMachine.class.getDeclaredMethod("addMatchReferences", ByteMatch.class);
        method.setAccessible(true);
        method.invoke(byteMachine, byteMatch);
    }

    private AtomicInteger getAtomicIntegerField(String fieldName) throws Exception {
        Field field = ByteMachine.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        return (AtomicInteger) field.get(byteMachine);
    }

    private Set<NameState> getAnythingButs() throws Exception {
        Field field = ByteMachine.class.getDeclaredField("anythingButs");
        field.setAccessible(true);
        return (Set<NameState>) field.get(byteMachine);
    }

    @Test
    void testAddMatchReferences_withExactPattern() throws Exception {
        // Given
        when(patternMock.type()).thenReturn(MatchType.EXACT);
        // When
        invokeAddMatchReferences(byteMatchMock);
        // Then
        assertEquals(0, getAtomicIntegerField("hasNumeric").get());
        assertEquals(0, getAtomicIntegerField("hasIP").get());
        assertEquals(0, getAtomicIntegerField("hasSuffix").get());
        assertTrue(getAnythingButs().isEmpty());
    }

    @Test
    void testAddMatchReferences_withSuffixPattern() throws Exception {
        // Given
        when(patternMock.type()).thenReturn(MatchType.SUFFIX);
        // When
        invokeAddMatchReferences(byteMatchMock);
        // Then
        assertEquals(1, getAtomicIntegerField("hasSuffix").get());
    }

    @Test
    void testAddMatchReferences_withNumericEqPattern() throws Exception {
        // Given
        when(patternMock.type()).thenReturn(MatchType.NUMERIC_EQ);
        // When
        invokeAddMatchReferences(byteMatchMock);
        // Then
        assertEquals(1, getAtomicIntegerField("hasNumeric").get());
    }

    @Test
    void testAddMatchReferences_withNumericRangePattern() throws Exception {
        // Given
        Range rangeMock = mock(Range.class);
        when(patternMock.type()).thenReturn(MatchType.NUMERIC_RANGE);
        when(byteMatchMock.getPattern()).thenReturn(rangeMock);
        when(rangeMock.isCIDR).thenReturn(false);
        // When
        invokeAddMatchReferences(byteMatchMock);
        // Then
        assertEquals(1, getAtomicIntegerField("hasNumeric").get());
    }

    @Test
    void testAddMatchReferences_withAnythingButPattern() throws Exception {
        // Given
        AnythingBut anythingButMock = mock(AnythingBut.class);
        when(patternMock.type()).thenReturn(MatchType.ANYTHING_BUT);
        when(anythingButMock.isNumeric()).thenReturn(true);
        when(byteMatchMock.getPattern()).thenReturn(anythingButMock);
        // When
        invokeAddMatchReferences(byteMatchMock);
        // Then
        assertEquals(1, getAtomicIntegerField("hasNumeric").get());
        assertTrue(getAnythingButs().contains(nameStateMock));
    }

    @Test
    void testAddMatchReferences_withNumericRangePattern_CIDRTrue() throws Exception {
        // Given
        Range rangeMock = mock(Range.class);
        when(patternMock.type()).thenReturn(MatchType.NUMERIC_RANGE);
        when(byteMatchMock.getPattern()).thenReturn(rangeMock);
        when(rangeMock.isCIDR).thenReturn(true);
        // When
        invokeAddMatchReferences(byteMatchMock);
        // Then
        assertEquals(1, getAtomicIntegerField("hasIP").get());
    }

    @Test
    void testAddMatchReferences_withNumericRangePattern_CIDRFalse() throws Exception {
        // Given
        Range rangeMock = mock(Range.class);
        when(patternMock.type()).thenReturn(MatchType.NUMERIC_RANGE);
        when(byteMatchMock.getPattern()).thenReturn(rangeMock);
        when(rangeMock.isCIDR).thenReturn(false);
        // When
        invokeAddMatchReferences(byteMatchMock);
        // Then
        assertEquals(1, getAtomicIntegerField("hasNumeric").get());
    }

    @Test
    void testAddMatchReferences_withAnythingButPattern_NumericTrue() throws Exception {
        // Given
        AnythingBut anythingButMock = mock(AnythingBut.class);
        when(patternMock.type()).thenReturn(MatchType.ANYTHING_BUT);
        when(anythingButMock.isNumeric()).thenReturn(true);
        when(byteMatchMock.getPattern()).thenReturn(anythingButMock);
        // When
        invokeAddMatchReferences(byteMatchMock);
        // Then
        assertEquals(1, getAtomicIntegerField("hasNumeric").get());
        assertTrue(getAnythingButs().contains(nameStateMock));
    }

    @Test
    void testAddMatchReferences_withAnythingButPattern_NumericFalse() throws Exception {
        // Given
        AnythingBut anythingButMock = mock(AnythingBut.class);
        when(patternMock.type()).thenReturn(MatchType.ANYTHING_BUT);
        when(anythingButMock.isNumeric()).thenReturn(false);
        when(byteMatchMock.getPattern()).thenReturn(anythingButMock);
        // When
        invokeAddMatchReferences(byteMatchMock);
        // Then
        assertEquals(0, getAtomicIntegerField("hasNumeric").get());
        assertTrue(getAnythingButs().contains(nameStateMock));
    }

    @Test
    void testAddMatchReferences_withAnythingButIgnoreCasePattern() throws Exception {
        // Given
        when(patternMock.type()).thenReturn(MatchType.ANYTHING_BUT_IGNORE_CASE);
        // When
        invokeAddMatchReferences(byteMatchMock);
        // Then
        assertTrue(getAnythingButs().contains(nameStateMock));
    }

    @Test
    void testAddMatchReferences_withAnythingButSuffixPattern() throws Exception {
        // Given
        when(patternMock.type()).thenReturn(MatchType.ANYTHING_BUT_SUFFIX);
        // When
        invokeAddMatchReferences(byteMatchMock);
        // Then
        assertEquals(1, getAtomicIntegerField("hasSuffix").get());
        assertTrue(getAnythingButs().contains(nameStateMock));
    }

    @Test
    void testAddMatchReferences_withAnythingButPrefixPattern() throws Exception {
        // Given
        when(patternMock.type()).thenReturn(MatchType.ANYTHING_BUT_PREFIX);
        // When
        invokeAddMatchReferences(byteMatchMock);
        // Then
        assertTrue(getAnythingButs().contains(nameStateMock));
    }

    @Test
    void testAddMatchReferences_withUndefinedPattern() {
        // Given
        when(patternMock.type()).thenReturn(null);
        // When & Then
        assertThrows(NullPointerException.class, () -> {
            invokeAddMatchReferences(byteMatchMock);
        });
    }

    @Test
    void testAddMatchReferences_withPrefixPattern() throws Exception {
        // Given
        when(patternMock.type()).thenReturn(MatchType.PREFIX);
        // When
        invokeAddMatchReferences(byteMatchMock);
        // Then
        assertEquals(0, getAtomicIntegerField("hasNumeric").get());
        assertEquals(0, getAtomicIntegerField("hasIP").get());
        assertEquals(0, getAtomicIntegerField("hasSuffix").get());
        assertTrue(getAnythingButs().isEmpty());
    }

    @Test
    void testAddMatchReferences_withExistsPattern() throws Exception {
        // Given
        when(patternMock.type()).thenReturn(MatchType.EXISTS);
        // When
        invokeAddMatchReferences(byteMatchMock);
        // Then
        assertEquals(0, getAtomicIntegerField("hasNumeric").get());
        assertEquals(0, getAtomicIntegerField("hasIP").get());
        assertEquals(0, getAtomicIntegerField("hasSuffix").get());
        assertTrue(getAnythingButs().isEmpty());
    }

    @Test
    void testAddMatchReferences_withEqualsIgnoreCasePattern() throws Exception {
        // Given
        when(patternMock.type()).thenReturn(MatchType.EQUALS_IGNORE_CASE);
        // When
        invokeAddMatchReferences(byteMatchMock);
        // Then
        assertEquals(0, getAtomicIntegerField("hasNumeric").get());
        assertEquals(0, getAtomicIntegerField("hasIP").get());
        assertEquals(0, getAtomicIntegerField("hasSuffix").get());
        assertTrue(getAnythingButs().isEmpty());
    }

    @Test
    void testAddMatchReferences_withWildcardPattern() throws Exception {
        // Given
        when(patternMock.type()).thenReturn(MatchType.WILDCARD);
        // When
        invokeAddMatchReferences(byteMatchMock);
        // Then
        assertEquals(0, getAtomicIntegerField("hasNumeric").get());
        assertEquals(0, getAtomicIntegerField("hasIP").get());
        assertEquals(0, getAtomicIntegerField("hasSuffix").get());
        assertTrue(getAnythingButs().isEmpty());
    }
}

```

Corresponding execution feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended

╷
├─ JUnit Jupiter ✔
│  └─ ByteMachine_addMatchReferences_Test ✔
│     ├─ testAddMatchReferences_withAnythingButSuffixPattern() ✔
│     ├─ testAddMatchReferences_withNumericRangePattern_CIDRFalse() ✘ 
│     │        when() requires an argument which has to be 'a method call on a mock'.
│     │        For example:
│     │            when(mock.getArticles()).thenReturn(articles);
│     │     
│     │        Also, this error might show up because:
│     │        1. you stub either of: final/private/equals()/hashCode() methods.
│     │           Those methods *cannot* be stubbed/verified.
│     │           Mocking methods declared on non-public parent classes is not supported.
│     │        2. inside when() you don't call method on mock but on some other object.
│     ├─ testAddMatchReferences_withNumericRangePattern_CIDRTrue() ✘ 
│     │        when() requires an argument which has to be 'a method call on a mock'.
│     │        For example:
│     │            when(mock.getArticles()).thenReturn(articles);
│     │     
│     │        Also, this error might show up because:
│     │        1. you stub either of: final/private/equals()/hashCode() methods.
│     │           Those methods *cannot* be stubbed/verified.
│     │           Mocking methods declared on non-public parent classes is not supported.
│     │        2. inside when() you don't call method on mock but on some other object.
│     ├─ testAddMatchReferences_withPrefixPattern() ✘ 
│     │        Unnecessary stubbings detected.
│     │        Clean & maintainable test code requires zero unnecessary code.
│     │        Following stubbings are unnecessary (click to navigate to relevant line of code):
│     │          1. -> at software.amazon.event.ruler.ByteMachine_addMatchReferences_Test.setupBeforeEach(ByteMachine_addMatchReferences_Test.java:33)
│     │        Please remove unnecessary stubbings or use 'lenient' strictness. More info: javadoc for UnnecessaryStubbingException class.
│     ├─ testAddMatchReferences_withExistsPattern() ✘ 
│     │        Unnecessary stubbings detected.
│     │        Clean & maintainable test code requires zero unnecessary code.
│     │        Following stubbings are unnecessary (click to navigate to relevant line of code):
│     │          1. -> at software.amazon.event.ruler.ByteMachine_addMatchReferences_Test.setupBeforeEach(ByteMachine_addMatchReferences_Test.java:33)
│     │        Please remove unnecessary stubbings or use 'lenient' strictness. More info: javadoc for UnnecessaryStubbingException class.
│     ├─ testAddMatchReferences_withAnythingButPattern() ✘ Cannot invoke "software.amazon.event.ruler.MatchType.ordinal()" because the return value of "software.amazon.event.ruler.Patterns.type()" is null
│     ├─ testAddMatchReferences_withExactPattern() ✘ 
│     │        Unnecessary stubbings detected.
│     │        Clean & maintainable test code requires zero unnecessary code.
│     │        Following stubbings are unnecessary (click to navigate to relevant line of code):
│     │          1. -> at software.amazon.event.ruler.ByteMachine_addMatchReferences_Test.setupBeforeEach(ByteMachine_addMatchReferences_Test.java:33)
│     │        Please remove unnecessary stubbings or use 'lenient' strictness. More info: javadoc for UnnecessaryStubbingException class.
│     ├─ testAddMatchReferences_withNumericRangePattern() ✘ 
│     │        when() requires an argument which has to be 'a method call on a mock'.
│     │        For example:
│     │            when(mock.getArticles()).thenReturn(articles);
│     │     
│     │        Also, this error might show up because:
│     │        1. you stub either of: final/private/equals()/hashCode() methods.
│     │           Those methods *cannot* be stubbed/verified.
│     │           Mocking methods declared on non-public parent classes is not supported.
│     │        2. inside when() you don't call method on mock but on some other object.
│     ├─ testAddMatchReferences_withEqualsIgnoreCasePattern() ✘ 
│     │        Unnecessary stubbings detected.
│     │        Clean & maintainable test code requires zero unnecessary code.
│     │        Following stubbings are unnecessary (click to navigate to relevant line of code):
│     │          1. -> at software.amazon.event.ruler.ByteMachine_addMatchReferences_Test.setupBeforeEach(ByteMachine_addMatchReferences_Test.java:33)
│     │        Please remove unnecessary stubbings or use 'lenient' strictness. More info: javadoc for UnnecessaryStubbingException class.
│     ├─ testAddMatchReferences_withWildcardPattern() ✘ 
│     │        Unnecessary stubbings detected.
│     │        Clean & maintainable test code requires zero unnecessary code.
│     │        Following stubbings are unnecessary (click to navigate to relevant line of code):
│     │          1. -> at software.amazon.event.ruler.ByteMachine_addMatchReferences_Test.setupBeforeEach(ByteMachine_addMatchReferences_Test.java:33)
│     │        Please remove unnecessary stubbings or use 'lenient' strictness. More info: javadoc for UnnecessaryStubbingException class.
│     ├─ testAddMatchReferences_withNumericEqPattern() ✘ 
│     │        Unnecessary stubbings detected.
│     │        Clean & maintainable test code requires zero unnecessary code.
│     │        Following stubbings are unnecessary (click to navigate to relevant line of code):
│     │          1. -> at software.amazon.event.ruler.ByteMachine_addMatchReferences_Test.setupBeforeEach(ByteMachine_addMatchReferences_Test.java:33)
│     │        Please remove unnecessary stubbings or use 'lenient' strictness. More info: javadoc for UnnecessaryStubbingException class.
│     ├─ testAddMatchReferences_withAnythingButPrefixPattern() ✔
│     ├─ testAddMatchReferences_withAnythingButPattern_NumericTrue() ✘ Cannot invoke "software.amazon.event.ruler.MatchType.ordinal()" because the return value of "software.amazon.event.ruler.Patterns.type()" is null
│     ├─ testAddMatchReferences_withSuffixPattern() ✘ 
│     │        Unnecessary stubbings detected.
│     │        Clean & maintainable test code requires zero unnecessary code.
│     │        Following stubbings are unnecessary (click to navigate to relevant line of code):
│     │          1. -> at software.amazon.event.ruler.ByteMachine_addMatchReferences_Test.setupBeforeEach(ByteMachine_addMatchReferences_Test.java:33)
│     │        Please remove unnecessary stubbings or use 'lenient' strictness. More info: javadoc for UnnecessaryStubbingException class.
│     ├─ testAddMatchReferences_withAnythingButIgnoreCasePattern() ✔
│     ├─ testAddMatchReferences_withUndefinedPattern() ✘ Unexpected exception type thrown, expected: <java.lang.NullPointerException> but was: <java.lang.reflect.InvocationTargetException>
│     └─ testAddMatchReferences_withAnythingButPattern_NumericFalse() ✘ Cannot invoke "software.amazon.event.ruler.MatchType.ordinal()" because the return value of "software.amazon.event.ruler.Patterns.type()" is null
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Failures (14):
  JUnit Jupiter:ByteMachine_addMatchReferences_Test:testAddMatchReferences_withNumericRangePattern_CIDRFalse()
    MethodSource [className = 'software.amazon.event.ruler.ByteMachine_addMatchReferences_Test', methodName = 'testAddMatchReferences_withNumericRangePattern_CIDRFalse', methodParameterTypes = '']
    => org.mockito.exceptions.misusing.MissingMethodInvocationException: 
when() requires an argument which has to be 'a method call on a mock'.
For example:
    when(mock.getArticles()).thenReturn(articles);

Also, this error might show up because:
1. you stub either of: final/private/equals()/hashCode() methods.
   Those methods *cannot* be stubbed/verified.
   Mocking methods declared on non-public parent classes is not supported.
2. inside when() you don't call method on mock but on some other object.

       software.amazon.event.ruler.ByteMachine_addMatchReferences_Test.testAddMatchReferences_withNumericRangePattern_CIDRFalse(ByteMachine_addMatchReferences_Test.java:133)
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
  JUnit Jupiter:ByteMachine_addMatchReferences_Test:testAddMatchReferences_withNumericRangePattern_CIDRTrue()
    MethodSource [className = 'software.amazon.event.ruler.ByteMachine_addMatchReferences_Test', methodName = 'testAddMatchReferences_withNumericRangePattern_CIDRTrue', methodParameterTypes = '']
    => org.mockito.exceptions.misusing.MissingMethodInvocationException: 
when() requires an argument which has to be 'a method call on a mock'.
For example:
    when(mock.getArticles()).thenReturn(articles);

Also, this error might show up because:
1. you stub either of: final/private/equals()/hashCode() methods.
   Those methods *cannot* be stubbed/verified.
   Mocking methods declared on non-public parent classes is not supported.
2. inside when() you don't call method on mock but on some other object.

       software.amazon.event.ruler.ByteMachine_addMatchReferences_Test.testAddMatchReferences_withNumericRangePattern_CIDRTrue(ByteMachine_addMatchReferences_Test.java:120)
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
  JUnit Jupiter:ByteMachine_addMatchReferences_Test:testAddMatchReferences_withPrefixPattern()
    MethodSource [className = 'software.amazon.event.ruler.ByteMachine_addMatchReferences_Test', methodName = 'testAddMatchReferences_withPrefixPattern', methodParameterTypes = '']
    => org.mockito.exceptions.misusing.UnnecessaryStubbingException: 
Unnecessary stubbings detected.
Clean & maintainable test code requires zero unnecessary code.
Following stubbings are unnecessary (click to navigate to relevant line of code):
  1. -> at software.amazon.event.ruler.ByteMachine_addMatchReferences_Test.setupBeforeEach(ByteMachine_addMatchReferences_Test.java:33)
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
  JUnit Jupiter:ByteMachine_addMatchReferences_Test:testAddMatchReferences_withExistsPattern()
    MethodSource [className = 'software.amazon.event.ruler.ByteMachine_addMatchReferences_Test', methodName = 'testAddMatchReferences_withExistsPattern', methodParameterTypes = '']
    => org.mockito.exceptions.misusing.UnnecessaryStubbingException: 
Unnecessary stubbings detected.
Clean & maintainable test code requires zero unnecessary code.
Following stubbings are unnecessary (click to navigate to relevant line of code):
  1. -> at software.amazon.event.ruler.ByteMachine_addMatchReferences_Test.setupBeforeEach(ByteMachine_addMatchReferences_Test.java:33)
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
  JUnit Jupiter:ByteMachine_addMatchReferences_Test:testAddMatchReferences_withAnythingButPattern()
    MethodSource [className = 'software.amazon.event.ruler.ByteMachine_addMatchReferences_Test', methodName = 'testAddMatchReferences_withAnythingButPattern', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "software.amazon.event.ruler.MatchType.ordinal()" because the return value of "software.amazon.event.ruler.Patterns.type()" is null
       software.amazon.event.ruler.ByteMachine.addMatchReferences(ByteMachine.java:1548)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.ByteMachine_addMatchReferences_Test.invokeAddMatchReferences(ByteMachine_addMatchReferences_Test.java:39)
       software.amazon.event.ruler.ByteMachine_addMatchReferences_Test.testAddMatchReferences_withAnythingButPattern(ByteMachine_addMatchReferences_Test.java:108)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:ByteMachine_addMatchReferences_Test:testAddMatchReferences_withExactPattern()
    MethodSource [className = 'software.amazon.event.ruler.ByteMachine_addMatchReferences_Test', methodName = 'testAddMatchReferences_withExactPattern', methodParameterTypes = '']
    => org.mockito.exceptions.misusing.UnnecessaryStubbingException: 
Unnecessary stubbings detected.
Clean & maintainable test code requires zero unnecessary code.
Following stubbings are unnecessary (click to navigate to relevant line of code):
  1. -> at software.amazon.event.ruler.ByteMachine_addMatchReferences_Test.setupBeforeEach(ByteMachine_addMatchReferences_Test.java:33)
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
  JUnit Jupiter:ByteMachine_addMatchReferences_Test:testAddMatchReferences_withNumericRangePattern()
    MethodSource [className = 'software.amazon.event.ruler.ByteMachine_addMatchReferences_Test', methodName = 'testAddMatchReferences_withNumericRangePattern', methodParameterTypes = '']
    => org.mockito.exceptions.misusing.MissingMethodInvocationException: 
when() requires an argument which has to be 'a method call on a mock'.
For example:
    when(mock.getArticles()).thenReturn(articles);

Also, this error might show up because:
1. you stub either of: final/private/equals()/hashCode() methods.
   Those methods *cannot* be stubbed/verified.
   Mocking methods declared on non-public parent classes is not supported.
2. inside when() you don't call method on mock but on some other object.

       software.amazon.event.ruler.ByteMachine_addMatchReferences_Test.testAddMatchReferences_withNumericRangePattern(ByteMachine_addMatchReferences_Test.java:93)
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
  JUnit Jupiter:ByteMachine_addMatchReferences_Test:testAddMatchReferences_withEqualsIgnoreCasePattern()
    MethodSource [className = 'software.amazon.event.ruler.ByteMachine_addMatchReferences_Test', methodName = 'testAddMatchReferences_withEqualsIgnoreCasePattern', methodParameterTypes = '']
    => org.mockito.exceptions.misusing.UnnecessaryStubbingException: 
Unnecessary stubbings detected.
Clean & maintainable test code requires zero unnecessary code.
Following stubbings are unnecessary (click to navigate to relevant line of code):
  1. -> at software.amazon.event.ruler.ByteMachine_addMatchReferences_Test.setupBeforeEach(ByteMachine_addMatchReferences_Test.java:33)
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
  JUnit Jupiter:ByteMachine_addMatchReferences_Test:testAddMatchReferences_withWildcardPattern()
    MethodSource [className = 'software.amazon.event.ruler.ByteMachine_addMatchReferences_Test', methodName = 'testAddMatchReferences_withWildcardPattern', methodParameterTypes = '']
    => org.mockito.exceptions.misusing.UnnecessaryStubbingException: 
Unnecessary stubbings detected.
Clean & maintainable test code requires zero unnecessary code.
Following stubbings are unnecessary (click to navigate to relevant line of code):
  1. -> at software.amazon.event.ruler.ByteMachine_addMatchReferences_Test.setupBeforeEach(ByteMachine_addMatchReferences_Test.java:33)
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
  JUnit Jupiter:ByteMachine_addMatchReferences_Test:testAddMatchReferences_withNumericEqPattern()
    MethodSource [className = 'software.amazon.event.ruler.ByteMachine_addMatchReferences_Test', methodName = 'testAddMatchReferences_withNumericEqPattern', methodParameterTypes = '']
    => org.mockito.exceptions.misusing.UnnecessaryStubbingException: 
Unnecessary stubbings detected.
Clean & maintainable test code requires zero unnecessary code.
Following stubbings are unnecessary (click to navigate to relevant line of code):
  1. -> at software.amazon.event.ruler.ByteMachine_addMatchReferences_Test.setupBeforeEach(ByteMachine_addMatchReferences_Test.java:33)
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
  JUnit Jupiter:ByteMachine_addMatchReferences_Test:testAddMatchReferences_withAnythingButPattern_NumericTrue()
    MethodSource [className = 'software.amazon.event.ruler.ByteMachine_addMatchReferences_Test', methodName = 'testAddMatchReferences_withAnythingButPattern_NumericTrue', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "software.amazon.event.ruler.MatchType.ordinal()" because the return value of "software.amazon.event.ruler.Patterns.type()" is null
       software.amazon.event.ruler.ByteMachine.addMatchReferences(ByteMachine.java:1548)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.ByteMachine_addMatchReferences_Test.invokeAddMatchReferences(ByteMachine_addMatchReferences_Test.java:39)
       software.amazon.event.ruler.ByteMachine_addMatchReferences_Test.testAddMatchReferences_withAnythingButPattern_NumericTrue(ByteMachine_addMatchReferences_Test.java:148)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:ByteMachine_addMatchReferences_Test:testAddMatchReferences_withSuffixPattern()
    MethodSource [className = 'software.amazon.event.ruler.ByteMachine_addMatchReferences_Test', methodName = 'testAddMatchReferences_withSuffixPattern', methodParameterTypes = '']
    => org.mockito.exceptions.misusing.UnnecessaryStubbingException: 
Unnecessary stubbings detected.
Clean & maintainable test code requires zero unnecessary code.
Following stubbings are unnecessary (click to navigate to relevant line of code):
  1. -> at software.amazon.event.ruler.ByteMachine_addMatchReferences_Test.setupBeforeEach(ByteMachine_addMatchReferences_Test.java:33)
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
  JUnit Jupiter:ByteMachine_addMatchReferences_Test:testAddMatchReferences_withUndefinedPattern()
    MethodSource [className = 'software.amazon.event.ruler.ByteMachine_addMatchReferences_Test', methodName = 'testAddMatchReferences_withUndefinedPattern', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Unexpected exception type thrown, expected: <java.lang.NullPointerException> but was: <java.lang.reflect.InvocationTargetException>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:67)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3128)
       software.amazon.event.ruler.ByteMachine_addMatchReferences_Test.testAddMatchReferences_withUndefinedPattern(ByteMachine_addMatchReferences_Test.java:204)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.ByteMachine_addMatchReferences_Test.invokeAddMatchReferences(ByteMachine_addMatchReferences_Test.java:39)
       [...]
     Caused by: java.lang.NullPointerException: Cannot invoke "software.amazon.event.ruler.MatchType.ordinal()" because the return value of "software.amazon.event.ruler.Patterns.type()" is null
       software.amazon.event.ruler.ByteMachine.addMatchReferences(ByteMachine.java:1548)
       [...]
  JUnit Jupiter:ByteMachine_addMatchReferences_Test:testAddMatchReferences_withAnythingButPattern_NumericFalse()
    MethodSource [className = 'software.amazon.event.ruler.ByteMachine_addMatchReferences_Test', methodName = 'testAddMatchReferences_withAnythingButPattern_NumericFalse', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "software.amazon.event.ruler.MatchType.ordinal()" because the return value of "software.amazon.event.ruler.Patterns.type()" is null
       software.amazon.event.ruler.ByteMachine.addMatchReferences(ByteMachine.java:1548)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.ByteMachine_addMatchReferences_Test.invokeAddMatchReferences(ByteMachine_addMatchReferences_Test.java:39)
       software.amazon.event.ruler.ByteMachine_addMatchReferences_Test.testAddMatchReferences_withAnythingButPattern_NumericFalse(ByteMachine_addMatchReferences_Test.java:162)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]

Test run finished after 721 ms
[         4 containers found      ]
[         0 containers skipped    ]
[         4 containers started    ]
[         0 containers aborted    ]
[         4 containers successful ]
[         0 containers failed     ]
[        17 tests found           ]
[         0 tests skipped         ]
[        17 tests started         ]
[         0 tests aborted         ]
[         3 tests successful      ]
[        14 tests failed          ]


```


And you can use the following related information to assist you in your analysis and correction:

api document of class software.amazon.event.ruler.ByteMachine: 

 * Represents a UTF8-byte-level state machine that matches a Ruler state machine's field values.
 * Each state is a map keyed by utf8 byte. getTransition(byte) yields a Target, which can contain either or
 *  both of the next ByteState, and the first of a chain of Matches, which indicate a match to some pattern.
 

dependent classes: 
class `software.amazon.event.ruler.ByteMatch`:
	
class `software.amazon.event.ruler.ByteMachine`:
	dependent fields:
		java.util.Set<software.amazon.event.ruler.NameState> software.amazon.event.ruler.ByteMachine.anythingButs;
		java.util.concurrent.atomic.AtomicInteger software.amazon.event.ruler.ByteMachine.hasIP;
		java.util.concurrent.atomic.AtomicInteger software.amazon.event.ruler.ByteMachine.hasNumeric;
		java.util.concurrent.atomic.AtomicInteger software.amazon.event.ruler.ByteMachine.hasSuffix;
	related functions:
		method `private void addExistenceMatch(Set<NameState>)` returns `void`, related with `software.amazon.event.ruler.ByteMachine.addMatchReferences(ByteMatch)`;
		method `private void addSuffixMatch(byte[], Set<NameState>, Set<NameState>)` returns `void`, related with `software.amazon.event.ruler.ByteMachine.addMatchReferences(ByteMatch)`;
		method `private void updateMatchReferences(ByteMatch)` returns `void`, related with `software.amazon.event.ruler.ByteMachine.addMatchReferences(ByteMatch)`;
		method `private void doTransitionOn(String, Set<NameState>, boolean)` returns `void`, related with `software.amazon.event.ruler.ByteMachine.addMatchReferences(ByteMatch)`;
		method ` NameState findPattern(Patterns)` returns `software.amazon.event.ruler.NameState`, related with `software.amazon.event.ruler.ByteMachine.addMatchReferences(ByteMatch)`;
		method ` void deletePattern(Patterns)` returns `void`, related with `software.amazon.event.ruler.ByteMachine.addMatchReferences(ByteMatch)`;
class `software.amazon.event.ruler.MatchType`:
	dependent fields:
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.ANYTHING_BUT_PREFIX;
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.EXISTS;
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.WILDCARD;
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.EXACT;
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.ANYTHING_BUT_SUFFIX;
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.PREFIX;
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.ANYTHING_BUT_IGNORE_CASE;
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.SUFFIX;
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.EQUALS_IGNORE_CASE;
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.NUMERIC_EQ;
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.NUMERIC_RANGE;
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.ANYTHING_BUT;
	
class `software.amazon.event.ruler.Range`:
	dependent fields:
		boolean software.amazon.event.ruler.Range.isCIDR;
	
class `software.amazon.event.ruler.MachineComplexityEvaluator`:
	related functions:
		method `private static Set<Patterns> getWildcardPatterns(Set<ByteMatch>)` returns `java.util.Set<software.amazon.event.ruler.Patterns>`, related with `software.amazon.event.ruler.ByteMachine.addMatchReferences(ByteMatch)`;
class `software.amazon.event.ruler.Ruler`:
	related functions:
		method `private static boolean matches(JsonNode, Patterns)` returns `boolean`, related with `software.amazon.event.ruler.ByteMachine.addMatchReferences(ByteMatch)`;



Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 
