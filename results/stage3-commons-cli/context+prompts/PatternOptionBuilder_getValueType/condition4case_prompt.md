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

@output{test cases}: output the test cases in the JSON format: