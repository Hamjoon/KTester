
You are an experienced Java developer tasked with fixing a piece of code that contains compilation errors. Your task is to resolve these issues after analyzing the code and its compilation feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any syntactical or logical errors.
2. Analyze the compilation feedback to understand the specific errors and their locations within the code.
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
            // Both pattern and path are empty strings, should return true as they match.
            assertTrue(antPathMatcher.isMatch("", ""));
        }

        @Test
        void testEmptyPatternNonEmptyPath() {
            // Pattern is empty and path is not, should return false as they do not match.
            assertFalse(antPathMatcher.isMatch("", "somePath"));
        }

        @Test
        void testPatternStartsWithPathSeparatorAndEmptyPath() {
            // Pattern starts with pathSeparator and path is empty, should return true if matchStart is true.
            assertTrue(antPathMatcher.isMatch("/", ""));
        }

        @Test
        void testPatternWithSingleAsteriskAndEmptyPath() {
            // Pattern is '/*' and path is empty, should return false if matchStart is false.
            assertFalse(antPathMatcher.isMatch("/*", ""));
        }

        @Test
        void testSingleAsteriskPattern() {
            // Pattern is '*' and path is 'somePath', should return true as '*' matches any sequence of characters.
            assertTrue(antPathMatcher.isMatch("*", "somePath"));
        }

        @Test
        void testPatternWithLeadingAsterisk() {
            // Pattern is '*some' and path is 'anysome', should return true as '*' can match 'any'.
            assertTrue(antPathMatcher.isMatch("*some", "anysome"));
        }

        @Test
        void testPatternWithTrailingAsterisk() {
            // Pattern is 'some*' and path is 'somePath', should return true as '*' can match 'Path'.
            assertTrue(antPathMatcher.isMatch("some*", "somePath"));
        }

        @Test
        void testPatternWithSingleQuestionMark() {
            // Pattern is 'so?e' and path is 'some', should return true as '?' matches any single character.
            assertTrue(antPathMatcher.isMatch("so?e", "some"));
        }

        @Test
        void testPatternWithSingleQuestionMarkForDifferentChar() {
            // Pattern is 'so?e' and path is 'sore', should return true as '?' matches 'r'.
            assertTrue(antPathMatcher.isMatch("so?e", "sore"));
        }

        @Test
        void testPatternWithSingleQuestionMarkAndRepeatedChar() {
            // Pattern is 'so?e' and path is 'sooe', should return true as '?' matches 'o'.
            assertTrue(antPathMatcher.isMatch("so?e", "sooe"));
        }

        @Test
        void testPatternWithSingleQuestionMarkAndShortPath() {
            // Pattern is 'so?e' and path is 'soe', should return false as '?' cannot match zero characters.
            assertFalse(antPathMatcher.isMatch("so?e", "soe"));
        }

        @Test
        void testDoubleAsteriskPattern() {
            // Pattern is '**' and path is 'anyPath', should return true as '**' matches any sequence of characters.
            assertTrue(antPathMatcher.isMatch("**", "anyPath"));
        }
    }

    @Nested
    class IsMatchMethodAdditionalTestCases {

        @Test
        void testDoubleAsteriskInMiddle() {
            // Pattern is 'some/**' and path is 'some/any/path', should return true as '**' matches any sequence of characters including path separators.
            assertTrue(antPathMatcher.isMatch("some/**", "some/any/path"));
        }

        @Test
        void testSingleAsteriskWithTrailingSlash() {
            // Pattern is 'some/*' and path is 'some/', should return true since '*' can match zero or more characters.
            assertTrue(antPathMatcher.isMatch("some/*", "some/"));
        }

        @Test
        void testDoubleAsteriskWithPathSegment() {
            // Pattern is 'some/**/path' and path is 'some/any/deep/path', should return true as '**' can match any path segment.
            assertTrue(antPathMatcher.isMatch("some/**/path", "some/any/deep/path"));
        }

        @Test
        void testSingleAsteriskWithPathSegment() {
            // Pattern is 'some/*/path' and path is 'some/any/path', should return true as '*' can match a single path segment.
            assertTrue(antPathMatcher.isMatch("some/*/path", "some/any/path"));
        }

        @Test
        void testSingleAsteriskWithMissingSegment() {
            // Pattern is 'some/*/path' and path is 'some/path', should return false as '*' should match exactly one segment.
            assertFalse(antPathMatcher.isMatch("some/*/path", "some/path"));
        }

        @Test
        void testAsteriskWithLeadingSlash() {
            // Pattern is '*/path' and path is '/path', should return false because '*' cannot match an empty segment when path starts with a separator.
            assertFalse(antPathMatcher.isMatch("*/path", "/path"));
        }

        @Test
        void testDoubleAsteriskAtEnd() {
            // Pattern is 'some/**' and path is 'some', should return true as '**' can match zero or more characters.
            assertTrue(antPathMatcher.isMatch("some/**", "some"));
        }

        @Test
        void testDoubleAsteriskWithTrailingSlash() {
            // Pattern is 'some/**/' and path is 'some/', should return true as '**' can match zero or more characters including path separators.
            assertTrue(antPathMatcher.isMatch("some/**/", "some/"));
        }

        @Test
        void testDoubleAsteriskWithDeeplyNestedPath() {
            // Pattern is 'some/path/**' and path is 'some/path/deeply/nested', should return true as '**' matches any sequence of characters following 'some/path/'.
            assertTrue(antPathMatcher.isMatch("some/path/**", "some/path/deeply/nested"));
        }

        @Test
        void testSingleAsteriskWithDeeplyNestedPath() {
            // Pattern is 'some/path/*' and path is 'some/path/deeply/nested', should return false as '*' only matches a single segment.
            assertFalse(antPathMatcher.isMatch("some/path/*", "some/path/deeply/nested"));
        }
    }

    @Nested
    class IsMatchMethodExceptionTestCases {

        @Test
        void testNullPattern() {
            // Pattern is null, should throw NullPointerException as the pattern cannot be null.
            assertThrows(NullPointerException.class, () -> antPathMatcher.isMatch(null, "somePath"));
        }

        @Test
        void testNullPath() {
            // Path is null, should throw NullPointerException as the path cannot be null.
            assertThrows(NullPointerException.class, () -> antPathMatcher.isMatch("somePattern", null));
        }
    }

    @BeforeEach
    void setupBeforeEach() {
        // Setup the AntPathMatcher with necessary configurations
        AntPathMatcher.Builder builder = AntPathMatcher.newBuild();
        antPathMatcher = builder.withPathSeparator('/').withMatchStart(// Adjusted according to test cases
        true).build();
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

Corresponding compilation feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
src/test/java/org/flmelody/util/AntPathMatcher_isMatch_Test.java:174: error: method withMatchStart in class Builder cannot be applied to given types;
        antPathMatcher = builder.withPathSeparator('/').withMatchStart(// Adjusted according to test cases
                                                       ^
  required: no arguments
  found:    boolean
  reason: actual and formal argument lists differ in length
1 error

```

And you can use the following related information to assist you in your analysis and correction:
```
{'api document of class org.flmelody.util.AntPathMatcher': '\n * Path matcher implementation for Ant-style path patterns. This implementation matches URLs using\n * the following rules:\n *\n * <p>\'?\' - matches one character \'*\' - matches zero or more characters \'**\' - matches zero or more\n * directories in a path.\n *\n * <p>The instances of this class can be configured via its {@link Builder} to: (1) Use a custom\n * path separator. The default is \'/\' character (2) Ignore character case during comparison. The\n * default is {@code false} (3) Match start. Determines whether the pattern at least matches as far\n * as the given base path goes, assuming that a full path may then match as well. The default is\n * {@code false}.\n *\n * <p>Specify whether to trim tokenized paths. The default is {@code false} The custom path\n * separator &amp; ignoring character case options were inspired by Spring\'s AntPathMatcher.\n *\n * @author original author\n * @see <a href="https://github.com/azagniotov/ant-style-path-matcher">azagniotov</a>\n ', 'return type': 'boolean', 'dependent classes': 'class `org.flmelody.util.AntPathMatcher`:\n\tdependent fields:\n\t\tchar org.flmelody.util.AntPathMatcher.QUESTION;\n\t\tchar org.flmelody.util.AntPathMatcher.pathSeparator;\n\t\tchar org.flmelody.util.AntPathMatcher.ASTERISK;\n\t\tboolean org.flmelody.util.AntPathMatcher.matchStart;\n\trelated functions:\n\t\tmethod `private boolean doubleAsteriskMatch(String, String)` returns `boolean`, related with `org.flmelody.util.AntPathMatcher.isMatch(String, String)`;\n\t\tmethod `private int skipBlanks(String)` returns `int`, related with `org.flmelody.util.AntPathMatcher.isMatch(String, String)`;\nclass `org.flmelody.core.plugin.ws.ExtensionalWebSocketPlugin`:\n\trelated functions:\n\t\tmethod `public boolean isMatch(String)` returns `boolean`, related with `org.flmelody.util.AntPathMatcher.isMatch(String, String)`;\nclass `org.flmelody.core.context.AbstractHttpWindwardContext`:\n\trelated functions:\n\t\tmethod `public void html(String, M)` returns `void`, related with `org.flmelody.util.AntPathMatcher.isMatch(String, String)`, api document:  {@inheritDoc} ;\nclass `org.flmelody.core.AbstractRouterGroup`:\n\trelated functions:\n\t\tmethod `public RouterGroup<M> resources(String, String[])` returns `org.flmelody.core.RouterGroup<M>`, related with `org.flmelody.util.AntPathMatcher.isMatch(String, String)`;\n\t\tmethod `private Map<String, Object> checkPlaceholder(String)` returns `java.util.Map<java.lang.String, java.lang.Object>`, related with `org.flmelody.util.AntPathMatcher.isMatch(String, String)`;\n\t\tmethod `public R matchRouter(String, String)` returns `R`, related with `org.flmelody.util.AntPathMatcher.isMatch(String, String)`;\nclass `org.flmelody.support.FunctionHelper`:\n\trelated functions:\n\t\tmethod `private static FunctionDefinition findLambdaFunction(F)` returns `org.flmelody.support.FunctionDefinition`, related with `org.flmelody.util.AntPathMatcher.isMatch(String, String)`;'}
```

**Note**: If the method-to-test in the @Test function is a private method, you can use **reflection** to call it.

Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 


