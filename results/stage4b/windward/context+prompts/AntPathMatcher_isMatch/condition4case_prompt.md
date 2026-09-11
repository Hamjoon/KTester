As a professional Java software testing expert, your task is to generate test cases for a focal method.

You will be provided with:
1. The focal method that needs testing.
2. Basic information of the target class to which the focus method belongs, including fields and methods.

Ensure the generated test cases meet the following requirements:

Test Coverage Requirements:
- Create test cases to cover all conditional branches in the target method, including `if`, `else if`, `else`, `switch`, and `case` statements.

Output Format:
- Organize the test cases by its functions logically.
- Include input parameters, expected output, and a description for each test case.
- Use empty list `[]` for the input when a test case does not require input parameters.
- Provide an expected return value for each test case, or specify the behavior that should be verified if there's no return value.
- Clearly describe the intention of each test case in the description.

Follow the JSON format provided here for the output:
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

@output{test cases}: output the test cases in the JSON format: