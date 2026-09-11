You are a professional software tester. Write test cases for a focal method in a target class.

**Instructions:**
1. Analyze method behavior and identify ALL possible exception scenarios
2. Design test cases that trigger each identified exception
3. Follow existing test case structure, Avoid duplication and create new test cases for uncovered exception scenarios

Test Case Format:
1. Group test cases by test function name
2. Each test case MUST include:
   - Input parameters with values (use empty list `[]` if none)
   - Expected exception or observable behavior
   - Clear description of test scenario
3. Return empty array if no exception scenarios found

Output Schema (STRICT JSON format):
```json
[
    {
        "group": "test name",
        "cases": [
            {
                "input": [
                    {
                        "parameter": "param name",
                        "value": "param value"
                    }
                ],
                "expected": "expected exception or behavior",
                "description": "test scenario description"
            }
        ]
    },
    {...}
]
```

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
@input{existing test case}
here's the existing test cases:
```json
<cases_json>
```

@output{test cases}: output the test cases in the JSON format: