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
Here is the code of focal method, `getValueType(char)`, with the focal class `PatternOptionBuilder`:
```Java
package org.apache.commons.cli;
import java.net.URL;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
public class PatternOptionBuilder {
    public static final Class<String> STRING_VALUE = String.class;
    public static final Class<Object> OBJECT_VALUE = Object.class;
    public static final Class<Number> NUMBER_VALUE = Number.class;
    public static final Class<Date> DATE_VALUE = Date.class;
    public static final Class<?> CLASS_VALUE = Class.class;
    public static final Class<FileInputStream> EXISTING_FILE_VALUE = FileInputStream.class;
    public static final Class<File> FILE_VALUE = File.class;
    public static final Class<File[]> FILES_VALUE = File[].class;
    public static final Class<URL> URL_VALUE = URL.class;
    static final Converter<?, UnsupportedOperationException> NOT_IMPLEMENTED = s -> {
    throw new UnsupportedOperationException("Not yet implemented");
};
    public static Class<?> getValueType(final char ch) {
        switch (ch) {
            case '@' :
                return PatternOptionBuilder.OBJECT_VALUE;
            case ':' :
                return PatternOptionBuilder.STRING_VALUE;
            case '%' :
                return PatternOptionBuilder.NUMBER_VALUE;
            case '+' :
                return PatternOptionBuilder.CLASS_VALUE;
            case '#' :
                return PatternOptionBuilder.DATE_VALUE;
            case '<' :
                return PatternOptionBuilder.EXISTING_FILE_VALUE;
            case '>' :
                return PatternOptionBuilder.FILE_VALUE;
            case '*' :
                return PatternOptionBuilder.FILES_VALUE;
            case '/' :
                return PatternOptionBuilder.URL_VALUE;
        }
        return null;
    }
public static boolean isValueCode(final char ch);
    public static Options parsePattern(final String pattern);
    public static void registerTypes();
}
```
@input{existing test case}
here's the existing test cases:
```json
<cases_json>
```

@output{test cases}: output the test cases in the JSON format: