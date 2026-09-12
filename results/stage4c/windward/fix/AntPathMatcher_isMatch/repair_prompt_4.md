


You are an experienced Java developer tasked with fixing a piece of code that contains execution errors. Your task is to resolve these issues after analyzing the code and its execution feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any logical errors.
2. Analyze the execution feedback to understand the specific errors and their locations within the code.
3. Apply necessary corrections to the code based on the feedback, and output the compelete correct code.
The Java code you need to fix is provided below:
```java
package org.flmelody.util;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class AntPathMatcher_isMatch_Test {

    private AntPathMatcher antPathMatcher;

    @Nested
    class IsMatchMethodTestCases {

        @Test
        void testEmptyPatternAndPath() {
            assertTrue(antPathMatcher.isMatch("", ""));
        }

        @Test
        void testEmptyPatternNonEmptyPath() {
            assertFalse(antPathMatcher.isMatch("", "somePath"));
        }

        @Test
        void testPatternStartsWithPathSeparatorAndEmptyPath() {
            assertFalse(antPathMatcher.isMatch("/", ""));
        }

        @Test
        void testPatternWithSingleAsteriskAndEmptyPath() {
            assertFalse(antPathMatcher.isMatch("/*", ""));
        }

        @Test
        void testSingleAsteriskPattern() {
            assertTrue(antPathMatcher.isMatch("*", "somePath"));
        }

        @Test
        void testPatternWithLeadingAsterisk() {
            assertTrue(antPathMatcher.isMatch("*some", "anysome"));
        }

        @Test
        void testPatternWithTrailingAsterisk() {
            assertTrue(antPathMatcher.isMatch("some*", "somePath"));
        }

        @Test
        void testPatternWithSingleQuestionMark() {
            assertTrue(antPathMatcher.isMatch("so?e", "some"));
        }

        @Test
        void testPatternWithSingleQuestionMarkForDifferentChar() {
            assertTrue(antPathMatcher.isMatch("so?e", "sore"));
        }

        @Test
        void testPatternWithSingleQuestionMarkAndRepeatedChar() {
            assertTrue(antPathMatcher.isMatch("so?e", "sooe"));
        }

        @Test
        void testPatternWithSingleQuestionMarkAndShortPath() {
            assertFalse(antPathMatcher.isMatch("so?e", "soe"));
        }

        @Test
        void testDoubleAsteriskPattern() {
            assertTrue(antPathMatcher.isMatch("**", "anyPath"));
        }
    }

    @Nested
    class IsMatchMethodAdditionalTestCases {

        @Test
        void testDoubleAsteriskInMiddle() {
            assertTrue(antPathMatcher.isMatch("some/**", "some/any/path"));
        }

        @Test
        void testSingleAsteriskWithTrailingSlash() {
            assertTrue(antPathMatcher.isMatch("some/*", "some/"));
        }

        @Test
        void testDoubleAsteriskWithPathSegment() {
            assertTrue(antPathMatcher.isMatch("some/**/path", "some/any/deep/path"));
        }

        @Test
        void testSingleAsteriskWithPathSegment() {
            assertTrue(antPathMatcher.isMatch("some/*/path", "some/any/path"));
        }

        @Test
        void testSingleAsteriskWithMissingSegment() {
            assertFalse(antPathMatcher.isMatch("some/*/path", "some/path"));
        }

        @Test
        void testAsteriskWithLeadingSlash() {
            assertFalse(antPathMatcher.isMatch("*/path", "/path"));
        }

        @Test
        void testDoubleAsteriskAtEnd() {
            assertTrue(antPathMatcher.isMatch("some/**", "some"));
        }

        @Test
        void testDoubleAsteriskWithTrailingSlash() {
            assertTrue(antPathMatcher.isMatch("some/**/", "some/"));
        }

        @Test
        void testDoubleAsteriskWithDeeplyNestedPath() {
            assertTrue(antPathMatcher.isMatch("some/path/**", "some/path/deeply/nested"));
        }

        @Test
        void testSingleAsteriskWithDeeplyNestedPath() {
            assertFalse(antPathMatcher.isMatch("some/path/*", "some/path/deeply/nested"));
        }
    }

    @Nested
    class IsMatchMethodExceptionTestCases {

        @Test
        void testNullPattern() {
            assertThrows(NullPointerException.class, () -> antPathMatcher.isMatch(null, "somePath"));
        }

        @Test
        void testNullPath() {
            assertThrows(NullPointerException.class, () -> antPathMatcher.isMatch("somePattern", null));
        }
    }

    @BeforeEach
    void setupBeforeEach() {
        // Corrected instantiation of AntPathMatcher
//         antPathMatcher = new AntPathMatcher('/', true);
    }

    @Test
    void testIsMatchWithAsterisk() {
        assertTrue(antPathMatcher.isMatch("/api/**", "/api/test"));
        assertTrue(antPathMatcher.isMatch("/**", "/api/test"));
        assertTrue(antPathMatcher.isMatch("/**/test", "/api/test"));
    }

    @Test
    void testIsMatchWithSpecificPattern() {
        assertTrue(antPathMatcher.isMatch("/api/**.js", "/api/test.js"));
        assertFalse(antPathMatcher.isMatch("/api/**.js", "/api/test"));
        assertTrue(antPathMatcher.isMatch("/api/**.js", "/api/test/a.js"));
        assertFalse(antPathMatcher.isMatch("/static/**.js", "/api/test/a.js"));
    }
}
```

Corresponding execution feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended

╷
├─ JUnit Jupiter ✔
│  └─ AntPathMatcher_isMatch_Test ✔
│     ├─ testIsMatchWithSpecificPattern() ✘ Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.antPathMatcher" is null
│     ├─ testIsMatchWithAsterisk() ✘ Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.antPathMatcher" is null
│     ├─ IsMatchMethodExceptionTestCases ✔
│     │  ├─ testNullPath() ✔
│     │  └─ testNullPattern() ✔
│     ├─ IsMatchMethodAdditionalTestCases ✔
│     │  ├─ testAsteriskWithLeadingSlash() ✘ Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.this$0.antPathMatcher" is null
│     │  ├─ testDoubleAsteriskInMiddle() ✘ Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.this$0.antPathMatcher" is null
│     │  ├─ testDoubleAsteriskWithTrailingSlash() ✘ Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.this$0.antPathMatcher" is null
│     │  ├─ testDoubleAsteriskWithPathSegment() ✘ Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.this$0.antPathMatcher" is null
│     │  ├─ testSingleAsteriskWithPathSegment() ✘ Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.this$0.antPathMatcher" is null
│     │  ├─ testDoubleAsteriskWithDeeplyNestedPath() ✘ Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.this$0.antPathMatcher" is null
│     │  ├─ testSingleAsteriskWithMissingSegment() ✘ Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.this$0.antPathMatcher" is null
│     │  ├─ testDoubleAsteriskAtEnd() ✘ Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.this$0.antPathMatcher" is null
│     │  ├─ testSingleAsteriskWithDeeplyNestedPath() ✘ Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.this$0.antPathMatcher" is null
│     │  └─ testSingleAsteriskWithTrailingSlash() ✘ Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.this$0.antPathMatcher" is null
│     └─ IsMatchMethodTestCases ✔
│        ├─ testEmptyPatternAndPath() ✘ Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.this$0.antPathMatcher" is null
│        ├─ testPatternWithSingleAsteriskAndEmptyPath() ✘ Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.this$0.antPathMatcher" is null
│        ├─ testDoubleAsteriskPattern() ✘ Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.this$0.antPathMatcher" is null
│        ├─ testPatternWithSingleQuestionMarkAndRepeatedChar() ✘ Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.this$0.antPathMatcher" is null
│        ├─ testPatternWithTrailingAsterisk() ✘ Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.this$0.antPathMatcher" is null
│        ├─ testPatternWithLeadingAsterisk() ✘ Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.this$0.antPathMatcher" is null
│        ├─ testEmptyPatternNonEmptyPath() ✘ Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.this$0.antPathMatcher" is null
│        ├─ testPatternStartsWithPathSeparatorAndEmptyPath() ✘ Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.this$0.antPathMatcher" is null
│        ├─ testSingleAsteriskPattern() ✘ Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.this$0.antPathMatcher" is null
│        ├─ testPatternWithSingleQuestionMark() ✘ Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.this$0.antPathMatcher" is null
│        ├─ testPatternWithSingleQuestionMarkAndShortPath() ✘ Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.this$0.antPathMatcher" is null
│        └─ testPatternWithSingleQuestionMarkForDifferentChar() ✘ Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.this$0.antPathMatcher" is null
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Failures (24):
  JUnit Jupiter:AntPathMatcher_isMatch_Test:testIsMatchWithSpecificPattern()
    MethodSource [className = 'org.flmelody.util.AntPathMatcher_isMatch_Test', methodName = 'testIsMatchWithSpecificPattern', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.antPathMatcher" is null
       org.flmelody.util.AntPathMatcher_isMatch_Test.testIsMatchWithSpecificPattern(AntPathMatcher_isMatch_Test.java:161)
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
  JUnit Jupiter:AntPathMatcher_isMatch_Test:testIsMatchWithAsterisk()
    MethodSource [className = 'org.flmelody.util.AntPathMatcher_isMatch_Test', methodName = 'testIsMatchWithAsterisk', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.antPathMatcher" is null
       org.flmelody.util.AntPathMatcher_isMatch_Test.testIsMatchWithAsterisk(AntPathMatcher_isMatch_Test.java:154)
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
  JUnit Jupiter:AntPathMatcher_isMatch_Test:IsMatchMethodAdditionalTestCases:testAsteriskWithLeadingSlash()
    MethodSource [className = 'org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodAdditionalTestCases', methodName = 'testAsteriskWithLeadingSlash', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.this$0.antPathMatcher" is null
       org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodAdditionalTestCases.testAsteriskWithLeadingSlash(AntPathMatcher_isMatch_Test.java:108)
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
  JUnit Jupiter:AntPathMatcher_isMatch_Test:IsMatchMethodAdditionalTestCases:testDoubleAsteriskInMiddle()
    MethodSource [className = 'org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodAdditionalTestCases', methodName = 'testDoubleAsteriskInMiddle', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.this$0.antPathMatcher" is null
       org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodAdditionalTestCases.testDoubleAsteriskInMiddle(AntPathMatcher_isMatch_Test.java:83)
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
  JUnit Jupiter:AntPathMatcher_isMatch_Test:IsMatchMethodAdditionalTestCases:testDoubleAsteriskWithTrailingSlash()
    MethodSource [className = 'org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodAdditionalTestCases', methodName = 'testDoubleAsteriskWithTrailingSlash', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.this$0.antPathMatcher" is null
       org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodAdditionalTestCases.testDoubleAsteriskWithTrailingSlash(AntPathMatcher_isMatch_Test.java:118)
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
  JUnit Jupiter:AntPathMatcher_isMatch_Test:IsMatchMethodAdditionalTestCases:testDoubleAsteriskWithPathSegment()
    MethodSource [className = 'org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodAdditionalTestCases', methodName = 'testDoubleAsteriskWithPathSegment', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.this$0.antPathMatcher" is null
       org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodAdditionalTestCases.testDoubleAsteriskWithPathSegment(AntPathMatcher_isMatch_Test.java:93)
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
  JUnit Jupiter:AntPathMatcher_isMatch_Test:IsMatchMethodAdditionalTestCases:testSingleAsteriskWithPathSegment()
    MethodSource [className = 'org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodAdditionalTestCases', methodName = 'testSingleAsteriskWithPathSegment', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.this$0.antPathMatcher" is null
       org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodAdditionalTestCases.testSingleAsteriskWithPathSegment(AntPathMatcher_isMatch_Test.java:98)
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
  JUnit Jupiter:AntPathMatcher_isMatch_Test:IsMatchMethodAdditionalTestCases:testDoubleAsteriskWithDeeplyNestedPath()
    MethodSource [className = 'org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodAdditionalTestCases', methodName = 'testDoubleAsteriskWithDeeplyNestedPath', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.this$0.antPathMatcher" is null
       org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodAdditionalTestCases.testDoubleAsteriskWithDeeplyNestedPath(AntPathMatcher_isMatch_Test.java:123)
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
  JUnit Jupiter:AntPathMatcher_isMatch_Test:IsMatchMethodAdditionalTestCases:testSingleAsteriskWithMissingSegment()
    MethodSource [className = 'org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodAdditionalTestCases', methodName = 'testSingleAsteriskWithMissingSegment', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.this$0.antPathMatcher" is null
       org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodAdditionalTestCases.testSingleAsteriskWithMissingSegment(AntPathMatcher_isMatch_Test.java:103)
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
  JUnit Jupiter:AntPathMatcher_isMatch_Test:IsMatchMethodAdditionalTestCases:testDoubleAsteriskAtEnd()
    MethodSource [className = 'org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodAdditionalTestCases', methodName = 'testDoubleAsteriskAtEnd', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.this$0.antPathMatcher" is null
       org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodAdditionalTestCases.testDoubleAsteriskAtEnd(AntPathMatcher_isMatch_Test.java:113)
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
  JUnit Jupiter:AntPathMatcher_isMatch_Test:IsMatchMethodAdditionalTestCases:testSingleAsteriskWithDeeplyNestedPath()
    MethodSource [className = 'org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodAdditionalTestCases', methodName = 'testSingleAsteriskWithDeeplyNestedPath', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.this$0.antPathMatcher" is null
       org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodAdditionalTestCases.testSingleAsteriskWithDeeplyNestedPath(AntPathMatcher_isMatch_Test.java:128)
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
  JUnit Jupiter:AntPathMatcher_isMatch_Test:IsMatchMethodAdditionalTestCases:testSingleAsteriskWithTrailingSlash()
    MethodSource [className = 'org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodAdditionalTestCases', methodName = 'testSingleAsteriskWithTrailingSlash', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.this$0.antPathMatcher" is null
       org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodAdditionalTestCases.testSingleAsteriskWithTrailingSlash(AntPathMatcher_isMatch_Test.java:88)
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
  JUnit Jupiter:AntPathMatcher_isMatch_Test:IsMatchMethodTestCases:testEmptyPatternAndPath()
    MethodSource [className = 'org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodTestCases', methodName = 'testEmptyPatternAndPath', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.this$0.antPathMatcher" is null
       org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodTestCases.testEmptyPatternAndPath(AntPathMatcher_isMatch_Test.java:19)
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
  JUnit Jupiter:AntPathMatcher_isMatch_Test:IsMatchMethodTestCases:testPatternWithSingleAsteriskAndEmptyPath()
    MethodSource [className = 'org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodTestCases', methodName = 'testPatternWithSingleAsteriskAndEmptyPath', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.this$0.antPathMatcher" is null
       org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodTestCases.testPatternWithSingleAsteriskAndEmptyPath(AntPathMatcher_isMatch_Test.java:34)
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
  JUnit Jupiter:AntPathMatcher_isMatch_Test:IsMatchMethodTestCases:testDoubleAsteriskPattern()
    MethodSource [className = 'org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodTestCases', methodName = 'testDoubleAsteriskPattern', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.this$0.antPathMatcher" is null
       org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodTestCases.testDoubleAsteriskPattern(AntPathMatcher_isMatch_Test.java:74)
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
  JUnit Jupiter:AntPathMatcher_isMatch_Test:IsMatchMethodTestCases:testPatternWithSingleQuestionMarkAndRepeatedChar()
    MethodSource [className = 'org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodTestCases', methodName = 'testPatternWithSingleQuestionMarkAndRepeatedChar', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.this$0.antPathMatcher" is null
       org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodTestCases.testPatternWithSingleQuestionMarkAndRepeatedChar(AntPathMatcher_isMatch_Test.java:64)
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
  JUnit Jupiter:AntPathMatcher_isMatch_Test:IsMatchMethodTestCases:testPatternWithTrailingAsterisk()
    MethodSource [className = 'org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodTestCases', methodName = 'testPatternWithTrailingAsterisk', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.this$0.antPathMatcher" is null
       org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodTestCases.testPatternWithTrailingAsterisk(AntPathMatcher_isMatch_Test.java:49)
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
  JUnit Jupiter:AntPathMatcher_isMatch_Test:IsMatchMethodTestCases:testPatternWithLeadingAsterisk()
    MethodSource [className = 'org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodTestCases', methodName = 'testPatternWithLeadingAsterisk', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.this$0.antPathMatcher" is null
       org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodTestCases.testPatternWithLeadingAsterisk(AntPathMatcher_isMatch_Test.java:44)
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
  JUnit Jupiter:AntPathMatcher_isMatch_Test:IsMatchMethodTestCases:testEmptyPatternNonEmptyPath()
    MethodSource [className = 'org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodTestCases', methodName = 'testEmptyPatternNonEmptyPath', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.this$0.antPathMatcher" is null
       org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodTestCases.testEmptyPatternNonEmptyPath(AntPathMatcher_isMatch_Test.java:24)
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
  JUnit Jupiter:AntPathMatcher_isMatch_Test:IsMatchMethodTestCases:testPatternStartsWithPathSeparatorAndEmptyPath()
    MethodSource [className = 'org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodTestCases', methodName = 'testPatternStartsWithPathSeparatorAndEmptyPath', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.this$0.antPathMatcher" is null
       org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodTestCases.testPatternStartsWithPathSeparatorAndEmptyPath(AntPathMatcher_isMatch_Test.java:29)
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
  JUnit Jupiter:AntPathMatcher_isMatch_Test:IsMatchMethodTestCases:testSingleAsteriskPattern()
    MethodSource [className = 'org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodTestCases', methodName = 'testSingleAsteriskPattern', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.this$0.antPathMatcher" is null
       org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodTestCases.testSingleAsteriskPattern(AntPathMatcher_isMatch_Test.java:39)
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
  JUnit Jupiter:AntPathMatcher_isMatch_Test:IsMatchMethodTestCases:testPatternWithSingleQuestionMark()
    MethodSource [className = 'org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodTestCases', methodName = 'testPatternWithSingleQuestionMark', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.this$0.antPathMatcher" is null
       org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodTestCases.testPatternWithSingleQuestionMark(AntPathMatcher_isMatch_Test.java:54)
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
  JUnit Jupiter:AntPathMatcher_isMatch_Test:IsMatchMethodTestCases:testPatternWithSingleQuestionMarkAndShortPath()
    MethodSource [className = 'org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodTestCases', methodName = 'testPatternWithSingleQuestionMarkAndShortPath', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.this$0.antPathMatcher" is null
       org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodTestCases.testPatternWithSingleQuestionMarkAndShortPath(AntPathMatcher_isMatch_Test.java:69)
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
  JUnit Jupiter:AntPathMatcher_isMatch_Test:IsMatchMethodTestCases:testPatternWithSingleQuestionMarkForDifferentChar()
    MethodSource [className = 'org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodTestCases', methodName = 'testPatternWithSingleQuestionMarkForDifferentChar', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "org.flmelody.util.AntPathMatcher.isMatch(String, String)" because "this.this$0.antPathMatcher" is null
       org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodTestCases.testPatternWithSingleQuestionMarkForDifferentChar(AntPathMatcher_isMatch_Test.java:59)
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

Test run finished after 474 ms
[         7 containers found      ]
[         0 containers skipped    ]
[         7 containers started    ]
[         0 containers aborted    ]
[         7 containers successful ]
[         0 containers failed     ]
[        26 tests found           ]
[         0 tests skipped         ]
[        26 tests started         ]
[         0 tests aborted         ]
[         2 tests successful      ]
[        24 tests failed          ]


```


And you can use the following related information to assist you in your analysis and correction:

api document of class org.flmelody.util.AntPathMatcher: 

 * Path matcher implementation for Ant-style path patterns. This implementation matches URLs using
 * the following rules:
 *
 * <p>'?' - matches one character '*' - matches zero or more characters '**' - matches zero or more
 * directories in a path.
 *
 * <p>The instances of this class can be configured via its {@link Builder} to: (1) Use a custom
 * path separator. The default is '/' character (2) Ignore character case during comparison. The
 * default is {@code false} (3) Match start. Determines whether the pattern at least matches as far
 * as the given base path goes, assuming that a full path may then match as well. The default is
 * {@code false}.
 *
 * <p>Specify whether to trim tokenized paths. The default is {@code false} The custom path
 * separator &amp; ignoring character case options were inspired by Spring's AntPathMatcher.
 *
 * @author original author
 * @see <a href="https://github.com/azagniotov/ant-style-path-matcher">azagniotov</a>
 

return type: 
boolean

dependent classes: 
class `org.flmelody.util.AntPathMatcher`:
	dependent fields:
		char org.flmelody.util.AntPathMatcher.QUESTION;
		char org.flmelody.util.AntPathMatcher.pathSeparator;
		char org.flmelody.util.AntPathMatcher.ASTERISK;
		boolean org.flmelody.util.AntPathMatcher.matchStart;
	related functions:
		method `private boolean doubleAsteriskMatch(String, String)` returns `boolean`, related with `org.flmelody.util.AntPathMatcher.isMatch(String, String)`;
		method `private int skipBlanks(String)` returns `int`, related with `org.flmelody.util.AntPathMatcher.isMatch(String, String)`;
class `org.flmelody.core.plugin.ws.ExtensionalWebSocketPlugin`:
	related functions:
		method `public boolean isMatch(String)` returns `boolean`, related with `org.flmelody.util.AntPathMatcher.isMatch(String, String)`;
class `org.flmelody.core.context.AbstractHttpWindwardContext`:
	related functions:
		method `public void html(String, M)` returns `void`, related with `org.flmelody.util.AntPathMatcher.isMatch(String, String)`, api document:  {@inheritDoc} ;
class `org.flmelody.core.AbstractRouterGroup`:
	related functions:
		method `public RouterGroup<M> resources(String, String[])` returns `org.flmelody.core.RouterGroup<M>`, related with `org.flmelody.util.AntPathMatcher.isMatch(String, String)`;
		method `private Map<String, Object> checkPlaceholder(String)` returns `java.util.Map<java.lang.String, java.lang.Object>`, related with `org.flmelody.util.AntPathMatcher.isMatch(String, String)`;
		method `public R matchRouter(String, String)` returns `R`, related with `org.flmelody.util.AntPathMatcher.isMatch(String, String)`;
class `org.flmelody.support.FunctionHelper`:
	related functions:
		method `private static FunctionDefinition findLambdaFunction(F)` returns `org.flmelody.support.FunctionDefinition`, related with `org.flmelody.util.AntPathMatcher.isMatch(String, String)`;



Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 
