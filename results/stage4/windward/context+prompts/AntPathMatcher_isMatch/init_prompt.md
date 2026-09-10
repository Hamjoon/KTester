You are tasked with creating a framework for a test class in Java. Now analyze the following information carefully:

1. The method that needs to be tested and its parameters.
2. The constructor of the target class and its parameters.
3. Any dependencies or external resources used by the target class.

Based on this analysis, complete the test class template in the provided structure:

1. Initialize necessary fields and dependencies within setupBeforeAll() and setupBeforeEach().
2. Handle any required cleanup in teardownAfterEach() and teardownAfterAll().
3. Make sure not to invoke any private methods or fields of the target class and dependent class.
4. Refer to the "given" phase in the existing test class for guidance.
5. Add necessary import statements and ensure there are no syntax errors in the generated code.

@input{focal method and target class}
Here is the code of focal method, `isMatch(String, String)`, with the focal class `AntPathMatcher_isMatch_Test`:
```Java
package org.flmelody.util;

public class AntPathMatcher {
    private static final char ASTERISK = '*';
    private static final char QUESTION = '?';
    private final char pathSeparator;
    private final boolean matchStart;
    public boolean isMatch(final String pattern, final String path) {
        if (pattern.isEmpty()) {
            return path.isEmpty();
        } else if (path.isEmpty() && (pattern.charAt(0) == pathSeparator)) {
            if (matchStart) {
                return true;
            } else if ((pattern.length() == 2) && (pattern.charAt(1) == ASTERISK)) {
                return false;
            }
            return isMatch(pattern.substring(1), path);
        }
        final char patternStart = pattern.charAt(0);
        if (patternStart == ASTERISK) {
            if (pattern.length() == 1) {
                return path.isEmpty() || ((path.charAt(0) != pathSeparator) && isMatch(pattern, path.substring(1)));
            } else if (doubleAsteriskMatch(pattern, path)) {
                return true;
            }
            int start = 0;
            while (start < path.length()) {
                if (isMatch(pattern.substring(1), path.substring(start))) {
                    return true;
                }
                start++;
            } 
            return isMatch(pattern.substring(1), path.substring(start));
        }
        int pointer = skipBlanks(path);
        return ((!path.isEmpty()) && (compareChar(path.charAt(pointer), patternStart) || (patternStart == QUESTION))) && isMatch(pattern.substring(1), path.substring(pointer + 1));
    }
private boolean compareChar(final char pathChar, final char patternChar);
    private boolean doubleAsteriskMatch(final String pattern, final String path);
    public static AntPathMatcher.Builder newBuild();
    private int skipBlanks(final String path);
}
```


@input{context information}

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
 

constructors for class `org.flmelody.util.AntPathMatcher`: 
params: 
body:
```java
  private AntPathMatcher(
      final char pathSeparator, boolean ignoreCase, boolean matchStart, boolean trimTokens) {
    this.pathSeparator = pathSeparator;
    this.ignoreCase = ignoreCase;
    this.matchStart = matchStart;
    this.trimTokens = trimTokens;
  }

```

existing test class: 
```java
package org.flmelody.util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * @author esotericman
 */
public class AntPathMatcherTest {

  @Test
  public void routerTest() {
    AntPathMatcher.Builder builder = new AntPathMatcher.Builder();
    AntPathMatcher antPathMatcher =
        builder.withIgnoreCase().withPathSeparator('/').withTrimTokens().build();
    assertTrue(antPathMatcher.isMatch("/api/**", "/api/test"));
    assertTrue(antPathMatcher.isMatch("/**", "/api/test"));
    assertTrue(antPathMatcher.isMatch("/**/test", "/api/test"));

    assertTrue(antPathMatcher.isMatch("/api/**.js", "/api/test.js"));
    assertFalse(antPathMatcher.isMatch("/api/**.js", "/api/test"));
    assertTrue(antPathMatcher.isMatch("/api/**.js", "/api/test/a.js"));

    assertFalse(antPathMatcher.isMatch("/static/**.js", "/api/test/a.js"));
  }
}

```

invoke examples of focal method: 
example 1:
```java
  public boolean isMatch(String path) {
    return this.pathMatcher.isMatch(pattern, path);
  }
```
example 2:
```java
  private boolean doubleAsteriskMatch(final String pattern, final String path) {
    if (pattern.charAt(1) != ASTERISK) {
    } else if (pattern.length() > 2) {
      return isMatch(pattern.substring(3), path);
  }
```
example 3:
```java
  @Override
  public <R> R matchRouter(String relativePath, String method) {
    if (!relativePath.startsWith(groupPath)) {
    if (relativePath.endsWith(UrlUtil.SLASH) && !UrlUtil.SLASH.equals(relativePath)) {
    relativePath = relativePath.replaceAll("[{}]", "");
    if (!routers.containsKey(relativePath)) {
      Set<String> routerKeys = routers.keySet();
      for (String routerKey : routerKeys) {
        Pattern compiledPattern = Pattern.compile("\\{(.*?)}");
        Matcher compiledMatcher = compiledPattern.matcher(routerKey);
        if (!compiledMatcher.find()) {
          if (HttpMethod.GET.name().equalsIgnoreCase(method) && resourceRouter) {
            Boolean result = matchedRouter.get(relativePath);
            if (Boolean.TRUE.equals(result)) {
              boolean matched = antPathMatcher.isMatch(routerKey, relativePath);
  }
```



@input{test class template}
```java
package org.flmelody.util;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
// Add other imports if necessary

@ExtendWith(MockitoExtension.class)
class AntPathMatcher_isMatch_Test {
    @BeforeAll
    static void setupBeforeAll() {
    }

    @BeforeEach
    void setupBeforeEach() {
    }

    @AfterEach
    void teardownAfterEach() {
    }

    @AfterAll
    static void teardownAfterAll() {
    }
}
```

@output{initial test class}: now consider the invoke examples output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.