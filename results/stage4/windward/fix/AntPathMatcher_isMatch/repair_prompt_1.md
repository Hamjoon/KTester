


You are an experienced Java developer tasked with fixing a piece of code that contains execution errors. Your task is to resolve these issues after analyzing the code and its execution feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any logical errors.
2. Analyze the execution feedback to understand the specific errors and their locations within the code.
3. Apply necessary corrections to the code based on the feedback, and output the compelete correct code.
The Java code you need to fix is provided below:
```java
package org.flmelody.util;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AntPathMatcher_isMatch_Test {

    private AntPathMatcher antPathMatcher;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the AntPathMatcher with default parameters
        AntPathMatcher.Builder builder = new AntPathMatcher.Builder();
        antPathMatcher = builder.withPathSeparator('/').withIgnoreCase().withTrimTokens().build();
    }

    @Test
    void testIsMatchWithAsterisk() {
        assertTrue(antPathMatcher.isMatch("/api/**", "/api/test"));
        assertTrue(antPathMatcher.isMatch("/**", "/api/test"));
        assertTrue(antPathMatcher.isMatch("/**/test", "/api/test"));
    }

    @Test
    void testIsMatchWithFileExtensions() {
        assertTrue(antPathMatcher.isMatch("/api/**.js", "/api/test.js"));
        assertFalse(antPathMatcher.isMatch("/api/**.js", "/api/test"));
        assertTrue(antPathMatcher.isMatch("/api/**.js", "/api/test/a.js"));
    }

    @Test
    void testIsMatchWithNonMatchingPaths() {
        assertFalse(antPathMatcher.isMatch("/static/**.js", "/api/test/a.js"));
    }

    @Test
    void testIsMatchWithBasicFunctionality() {
        // Test case where both pattern and path are empty strings.
        assertTrue(antPathMatcher.isMatch("", ""), "Expected to return true for both empty pattern and path.");
        // Test case where pattern has a leading wildcard and path is empty.
        assertFalse(antPathMatcher.isMatch("/*", ""), "Expected to return false for pattern '/*' and empty path.");
        // Test case where pattern is a single wildcard and path is a non-empty string.
        assertTrue(antPathMatcher.isMatch("*", "anyPath"), "Expected to return true for pattern '*' and non-empty path.");
        // Test case where pattern matches the path exactly.
        assertTrue(antPathMatcher.isMatch("test", "test"), "Expected to return true for exact match.");
        // Test case where pattern does not match the path.
        assertFalse(antPathMatcher.isMatch("test", "testing"), "Expected to return false for non-matching pattern and path.");
        // Test case where pattern matches a path with a wildcard at the end.
        assertTrue(antPathMatcher.isMatch("test/*", "test/folder"), "Expected to return true for pattern 'test/*' with matching path.");
        // Test case where pattern matches a path with a wildcard at the end and path ends with a separator.
        assertTrue(antPathMatcher.isMatch("test/*", "test/"), "Expected to return true for pattern 'test/*' with path ending with separator.");
        // Test case where pattern does not match the path due to differing prefixes.
        assertFalse(antPathMatcher.isMatch("test/*", "testing/folder"), "Expected to return false for differing prefixes.");
    }

    @Test
    void testIsMatchWithWildcardFunctionality() {
        // Test case where pattern has a wildcard followed by a separator and path ends with a separator.
        assertTrue(antPathMatcher.isMatch("*/", "folder/"), "Expected to return true for pattern '*/' and path 'folder/'.");
        // Test case where pattern matches a path with a wildcard followed by a specific file name.
        assertTrue(antPathMatcher.isMatch("*/file.txt", "folder/file.txt"), "Expected to return true for pattern '*/file.txt' and matching path.");
        // Test case where pattern does not include any wildcards and path includes a folder.
        assertFalse(antPathMatcher.isMatch("file.txt", "folder/file.txt"), "Expected to return false for non-matching pattern and path.");
        // Test case where pattern is a double wildcard.
        assertTrue(antPathMatcher.isMatch("**", "any/path/to/somewhere"), "Expected to return true for double wildcard '**'.");
        // Test case where pattern includes a wildcard followed by a double wildcard and matches the path.
        assertTrue(antPathMatcher.isMatch("*/**/file.txt", "folder/subfolder/file.txt"), "Expected to return true for pattern '*/**/file.txt' and matching path.");
        // Test case where pattern is a double wildcard followed by a specific file name and the path is the file itself.
        assertTrue(antPathMatcher.isMatch("**/file.txt", "file.txt"), "Expected to return true for pattern '**/file.txt' and matching path.");
    }

    @Test
    void testIsMatchWithQuestionMarkFunctionality() {
        // Test case where pattern includes a question mark that matches any single character.
        assertTrue(antPathMatcher.isMatch("te?t", "test"), "Expected to return true for pattern 'te?t' and matching path.");
        // Test case where pattern includes a question mark and path matches.
        assertTrue(antPathMatcher.isMatch("te?t", "teat"), "Expected to return true for pattern 'te?t' with matching path.");
        // Test case where pattern requires a character to match but path is too short.
        assertFalse(antPathMatcher.isMatch("te?t", "te"), "Expected to return false for pattern 'te?t' and short path.");
        // Test case where pattern includes two question marks that match any two characters.
        assertTrue(antPathMatcher.isMatch("te??t", "text"), "Expected to return true for pattern 'te??t' with matching path.");
        // Test case where pattern includes two question marks and path matches.
        assertTrue(antPathMatcher.isMatch("te??t", "teat"), "Expected to return true for pattern 'te??t' and matching path.");
        // Test case where pattern requires two characters to match but path has extra characters.
        assertFalse(antPathMatcher.isMatch("te??t", "testt"), "Expected to return false for pattern 'te??t' with extra characters in path.");
    }

    @Test
    void testIsMatchWithEdgeCases() {
        // Test case where pattern is a wildcard with a leading separator and path is a single separator.
        assertTrue(antPathMatcher.isMatch("/*", "/"), "Expected to return true for pattern '/*' and path '/'.");
        // Test case where pattern is a double wildcard with a leading separator and path has multiple segments.
        assertTrue(antPathMatcher.isMatch("/**", "/some/path"), "Expected to return true for pattern '/**' and matching path.");
        // Test case where pattern is a wildcard with a leading separator but path has additional segments.
        assertFalse(antPathMatcher.isMatch("/*", "folder/test"), "Expected to return false for pattern '/*' with additional segments in path.");
        // Test case where pattern matches a path with a leading separator and a wildcard at the end.
        assertTrue(antPathMatcher.isMatch("/folder/*", "/folder/test"), "Expected to return true for pattern '/folder/*' and matching path.");
        // Test case where pattern is a double wildcard with a leading separator and path has multiple segments leading to the test.
        assertTrue(antPathMatcher.isMatch("/**/test", "/some/folder/test"), "Expected to return true for pattern '/**/test' and matching path.");
        // Test case where pattern matches a single segment but path has extra segments.
        assertFalse(antPathMatcher.isMatch("/*/test", "/folder/test/extra"), "Expected to return false for pattern '/*/test' with extra segments in path.");
    }

    @Test
    void testIsMatchWithExceptionScenarios() {
        // Test case where pattern is null. Expected to throw NullPointerException.
        assertThrows(NullPointerException.class, () -> {
            antPathMatcher.isMatch(null, "validPath");
        }, "Expected NullPointerException when pattern is null.");
        // Test case where path is null. Expected to throw NullPointerException.
        assertThrows(NullPointerException.class, () -> {
            antPathMatcher.isMatch("validPattern", null);
        }, "Expected NullPointerException when path is null.");
        // Test case where pattern is invalid but path is empty.
        assertFalse(antPathMatcher.isMatch("invalid*pattern", ""), "Expected to return false for invalid pattern and empty path.");
        // Test case where pattern is a wildcard with a leading separator and path is non-empty.
        assertTrue(antPathMatcher.isMatch("/*", "nonEmptyPath"), "Expected to return true for pattern '/*' and non-empty path.");
        // Test case where pattern is a wildcard with a leading separator and path is empty.
        assertFalse(antPathMatcher.isMatch("/*", ""), "Expected to return false for pattern '/*' and empty path.");
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
│     ├─ testIsMatchWithFileExtensions() ✔
│     ├─ testIsMatchWithBasicFunctionality() ✔
│     ├─ testIsMatchWithWildcardFunctionality() ✔
│     ├─ testIsMatchWithEdgeCases() ✔
│     ├─ testIsMatchWithNonMatchingPaths() ✔
│     ├─ testIsMatchWithExceptionScenarios() ✘ Expected to return true for pattern '/*' and non-empty path. ==> expected: <true> but was: <false>
│     ├─ testIsMatchWithQuestionMarkFunctionality() ✘ Expected to return true for pattern 'te??t' with matching path. ==> expected: <true> but was: <false>
│     └─ testIsMatchWithAsterisk() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Failures (2):
  JUnit Jupiter:AntPathMatcher_isMatch_Test:testIsMatchWithExceptionScenarios()
    MethodSource [className = 'org.flmelody.util.AntPathMatcher_isMatch_Test', methodName = 'testIsMatchWithExceptionScenarios', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Expected to return true for pattern '/*' and non-empty path. ==> expected: <true> but was: <false>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertTrue.failNotTrue(AssertTrue.java:63)
       org.junit.jupiter.api.AssertTrue.assertTrue(AssertTrue.java:36)
       org.junit.jupiter.api.Assertions.assertTrue(Assertions.java:214)
       org.flmelody.util.AntPathMatcher_isMatch_Test.testIsMatchWithExceptionScenarios(AntPathMatcher_isMatch_Test.java:122)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       [...]
  JUnit Jupiter:AntPathMatcher_isMatch_Test:testIsMatchWithQuestionMarkFunctionality()
    MethodSource [className = 'org.flmelody.util.AntPathMatcher_isMatch_Test', methodName = 'testIsMatchWithQuestionMarkFunctionality', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Expected to return true for pattern 'te??t' with matching path. ==> expected: <true> but was: <false>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertTrue.failNotTrue(AssertTrue.java:63)
       org.junit.jupiter.api.AssertTrue.assertTrue(AssertTrue.java:36)
       org.junit.jupiter.api.Assertions.assertTrue(Assertions.java:214)
       org.flmelody.util.AntPathMatcher_isMatch_Test.testIsMatchWithQuestionMarkFunctionality(AntPathMatcher_isMatch_Test.java:86)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       [...]

Test run finished after 393 ms
[         4 containers found      ]
[         0 containers skipped    ]
[         4 containers started    ]
[         0 containers aborted    ]
[         4 containers successful ]
[         0 containers failed     ]
[         8 tests found           ]
[         0 tests skipped         ]
[         8 tests started         ]
[         0 tests aborted         ]
[         6 tests successful      ]
[         2 tests failed          ]


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
		char org.flmelody.util.AntPathMatcher.pathSeparator;
		char org.flmelody.util.AntPathMatcher.ASTERISK;
		char org.flmelody.util.AntPathMatcher.QUESTION;
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
		method `private Map<String, Object> checkPlaceholder(String)` returns `java.util.Map<java.lang.String, java.lang.Object>`, related with `org.flmelody.util.AntPathMatcher.isMatch(String, String)`;
		method `public RouterGroup<M> resources(String, String[])` returns `org.flmelody.core.RouterGroup<M>`, related with `org.flmelody.util.AntPathMatcher.isMatch(String, String)`;
		method `public R matchRouter(String, String)` returns `R`, related with `org.flmelody.util.AntPathMatcher.isMatch(String, String)`;
class `org.flmelody.support.FunctionHelper`:
	related functions:
		method `private static FunctionDefinition findLambdaFunction(F)` returns `org.flmelody.support.FunctionDefinition`, related with `org.flmelody.util.AntPathMatcher.isMatch(String, String)`;



Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 
