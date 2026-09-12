


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
            assertTrue(antPathMatcher.isMatch("/", ""));
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
        AntPathMatcher.Builder builder = AntPathMatcher.newBuild();
        antPathMatcher = builder.withPathSeparator('/').withMatchStart().build();
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
│     ├─ testIsMatchWithSpecificPattern() ✔
│     ├─ testIsMatchWithAsterisk() ✔
│     ├─ IsMatchMethodExceptionTestCases ✔
│     │  ├─ testNullPath() ✔
│     │  └─ testNullPattern() ✔
│     ├─ IsMatchMethodAdditionalTestCases ✔
│     │  ├─ testAsteriskWithLeadingSlash() ✘ expected: <false> but was: <true>
│     │  ├─ testDoubleAsteriskInMiddle() ✔
│     │  ├─ testDoubleAsteriskWithTrailingSlash() ✔
│     │  ├─ testDoubleAsteriskWithPathSegment() ✔
│     │  ├─ testSingleAsteriskWithPathSegment() ✔
│     │  ├─ testDoubleAsteriskWithDeeplyNestedPath() ✔
│     │  ├─ testSingleAsteriskWithMissingSegment() ✘ expected: <false> but was: <true>
│     │  ├─ testDoubleAsteriskAtEnd() ✔
│     │  ├─ testSingleAsteriskWithDeeplyNestedPath() ✔
│     │  └─ testSingleAsteriskWithTrailingSlash() ✔
│     └─ IsMatchMethodTestCases ✔
│        ├─ testEmptyPatternAndPath() ✔
│        ├─ testPatternWithSingleAsteriskAndEmptyPath() ✘ expected: <false> but was: <true>
│        ├─ testDoubleAsteriskPattern() ✔
│        ├─ testPatternWithSingleQuestionMarkAndRepeatedChar() ✔
│        ├─ testPatternWithTrailingAsterisk() ✔
│        ├─ testPatternWithLeadingAsterisk() ✔
│        ├─ testEmptyPatternNonEmptyPath() ✔
│        ├─ testPatternStartsWithPathSeparatorAndEmptyPath() ✔
│        ├─ testSingleAsteriskPattern() ✔
│        ├─ testPatternWithSingleQuestionMark() ✔
│        ├─ testPatternWithSingleQuestionMarkAndShortPath() ✔
│        └─ testPatternWithSingleQuestionMarkForDifferentChar() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Failures (3):
  JUnit Jupiter:AntPathMatcher_isMatch_Test:IsMatchMethodAdditionalTestCases:testAsteriskWithLeadingSlash()
    MethodSource [className = 'org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodAdditionalTestCases', methodName = 'testAsteriskWithLeadingSlash', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <false> but was: <true>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertFalse.failNotFalse(AssertFalse.java:63)
       org.junit.jupiter.api.AssertFalse.assertFalse(AssertFalse.java:36)
       org.junit.jupiter.api.AssertFalse.assertFalse(AssertFalse.java:31)
       org.junit.jupiter.api.Assertions.assertFalse(Assertions.java:231)
       org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodAdditionalTestCases.testAsteriskWithLeadingSlash(AntPathMatcher_isMatch_Test.java:108)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:AntPathMatcher_isMatch_Test:IsMatchMethodAdditionalTestCases:testSingleAsteriskWithMissingSegment()
    MethodSource [className = 'org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodAdditionalTestCases', methodName = 'testSingleAsteriskWithMissingSegment', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <false> but was: <true>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertFalse.failNotFalse(AssertFalse.java:63)
       org.junit.jupiter.api.AssertFalse.assertFalse(AssertFalse.java:36)
       org.junit.jupiter.api.AssertFalse.assertFalse(AssertFalse.java:31)
       org.junit.jupiter.api.Assertions.assertFalse(Assertions.java:231)
       org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodAdditionalTestCases.testSingleAsteriskWithMissingSegment(AntPathMatcher_isMatch_Test.java:103)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:AntPathMatcher_isMatch_Test:IsMatchMethodTestCases:testPatternWithSingleAsteriskAndEmptyPath()
    MethodSource [className = 'org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodTestCases', methodName = 'testPatternWithSingleAsteriskAndEmptyPath', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <false> but was: <true>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertFalse.failNotFalse(AssertFalse.java:63)
       org.junit.jupiter.api.AssertFalse.assertFalse(AssertFalse.java:36)
       org.junit.jupiter.api.AssertFalse.assertFalse(AssertFalse.java:31)
       org.junit.jupiter.api.Assertions.assertFalse(Assertions.java:231)
       org.flmelody.util.AntPathMatcher_isMatch_Test$IsMatchMethodTestCases.testPatternWithSingleAsteriskAndEmptyPath(AntPathMatcher_isMatch_Test.java:34)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]

Test run finished after 584 ms
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
[        23 tests successful      ]
[         3 tests failed          ]


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
