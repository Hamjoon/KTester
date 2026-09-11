You are a professional Java software testing expert. Your task is to generate high-quality unit test code for a focal method, using **JUnit5** and **Mockito** libraries. 

You'll be provided with:
1. The focal method to test.
2. The target class's structure (fields and methods).
3. A pre-defined test class framework with `@Before`/`@After` methods.
4. Test cases in JSON format.
5. Any relevant context.

**Requirements:**

Code Quality:
- Include clear comments explaining each test scenario.
- Ensure code is clean, understandable, and maintainable.
- Do not directly access private methods or fields. **Use reflection if the focal method is private.**
- Include all necessary imports; code must be free of syntax errors.

**Test Structure:**
- Follow the given test class framework; do not modify `@Before`/`@After` methods.
- Create one test function per test case group, ensuring all cases are covered.
- Use parameterized tests where appropriate.
- Reinitialize variables inside test functions if `@Setup` values don't fit.
- Properly mock and set up dependencies.

@input{focal method and target class}
Here is the code of focal method, `isMatch(String, String)`, with the focal class `AntPathMatcher`:
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

@input{initial test class framework}
```Java
<initial_class>
```

@input{existing test case}
here's the existing test cases:
```json
<cases_json>
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
 

return type: 
boolean

dependent classes: 
class `org.flmelody.util.AntPathMatcher`:
	dependent fields:
		boolean org.flmelody.util.AntPathMatcher.matchStart;
		char org.flmelody.util.AntPathMatcher.ASTERISK;
		char org.flmelody.util.AntPathMatcher.QUESTION;
		char org.flmelody.util.AntPathMatcher.pathSeparator;
	related functions:
		method `private int skipBlanks(String)` returns `int`, related with `org.flmelody.util.AntPathMatcher.isMatch(String, String)`;
		method `private boolean doubleAsteriskMatch(String, String)` returns `boolean`, related with `org.flmelody.util.AntPathMatcher.isMatch(String, String)`;
class `org.flmelody.core.plugin.ws.ExtensionalWebSocketPlugin`:
	related functions:
		method `public boolean isMatch(String)` returns `boolean`, related with `org.flmelody.util.AntPathMatcher.isMatch(String, String)`;
class `org.flmelody.core.context.AbstractHttpWindwardContext`:
	related functions:
		method `public void html(String, M)` returns `void`, related with `org.flmelody.util.AntPathMatcher.isMatch(String, String)`, api document:  {@inheritDoc} ;
class `org.flmelody.core.AbstractRouterGroup`:
	related functions:
		method `public R matchRouter(String, String)` returns `R`, related with `org.flmelody.util.AntPathMatcher.isMatch(String, String)`;
		method `public RouterGroup<M> resources(String, String[])` returns `org.flmelody.core.RouterGroup<M>`, related with `org.flmelody.util.AntPathMatcher.isMatch(String, String)`;
		method `private Map<String, Object> checkPlaceholder(String)` returns `java.util.Map<java.lang.String, java.lang.Object>`, related with `org.flmelody.util.AntPathMatcher.isMatch(String, String)`;
class `org.flmelody.support.FunctionHelper`:
	related functions:
		method `private static FunctionDefinition findLambdaFunction(F)` returns `org.flmelody.support.FunctionDefinition`, related with `org.flmelody.util.AntPathMatcher.isMatch(String, String)`;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.