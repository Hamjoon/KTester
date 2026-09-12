As a professional Java software testing expert, your task is to generate test cases for a focal method.

Guidelines:
1. Analyze the method's functionality and identify possible input combinations including normal cases and boundary conditions.
2. Review the provided existing test cases. Avoid duplication and create new test cases for untested input combinations.

Context Provided:
1. The focal method that needs testing.
2. Relevant code and metadata for the class containing the method, including fields and other methods.
3. The existing test cases (in JSON format) to help avoid redundancy.

Output Instructions:
- Organize the test cases by the name of the test function (group).
- Each test case must include:
    - input: A list of parameter names and values. Use an empty list `[]` if no input is required.
    - expected: The return value or expected observable behavior.
    - description: A clear and concise explanation of the scenario and the intent of the test case.

Maintain the following strict JSON format:
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