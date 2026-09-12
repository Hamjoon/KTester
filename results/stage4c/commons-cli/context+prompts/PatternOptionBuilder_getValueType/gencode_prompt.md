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

api document of class org.apache.commons.cli.PatternOptionBuilder: 

 * Allows Options to be created from a single String. The pattern contains various single character flags and via an
 * optional punctuation character, their expected type.
 *
 * <table border="1">
 * <caption>Overview of PatternOptionBuilder patterns</caption>
 * <tr>
 * <td>a</td>
 * <td>-a flag</td>
 * </tr>
 * <tr>
 * <td>b@</td>
 * <td>-b [class name]</td>
 * </tr>
 * <tr>
 * <td>c&gt;</td>
 * <td>-c [file name]</td>
 * </tr>
 * <tr>
 * <td>d+</td>
 * <td>-d [class name] (creates object via empty constructor)</td>
 * </tr>
 * <tr>
 * <td>e%</td>
 * <td>-e [number] (creates Double/Long instance depending on existing of a '.')</td>
 * </tr>
 * <tr>
 * <td>f/</td>
 * <td>-f [URL]</td>
 * </tr>
 * <tr>
 * <td>g:</td>
 * <td>-g [string]</td>
 * </tr>
 * </table>
 *
 * <p>
 * For example, the following allows command line flags of '-v -p string-value -f /dir/file'. The exclamation mark
 * precede a mandatory option.
 * </p>
 *
 * <pre>
 * Options options = PatternOptionBuilder.parsePattern("vp:!f/");
 * </pre>
 *
 * <p>
 * TODO These need to break out to OptionType and also to be pluggable.
 * </p>
 

api document of method getValueType(char): 

     * Retrieve the class that {@code ch} represents.
     *
     * @param ch the specified character
     * @return The class that {@code ch} represents
     * @since 1.7.0
     

dependent classes: 
class `org.apache.commons.cli.PatternOptionBuilder`:
	dependent fields:
		java.lang.Class<java.lang.Object> org.apache.commons.cli.PatternOptionBuilder.OBJECT_VALUE;
		java.lang.Class<java.io.FileInputStream> org.apache.commons.cli.PatternOptionBuilder.EXISTING_FILE_VALUE;
		java.lang.Class<java.util.Date> org.apache.commons.cli.PatternOptionBuilder.DATE_VALUE;
		java.lang.Class<java.lang.Number> org.apache.commons.cli.PatternOptionBuilder.NUMBER_VALUE;
		java.lang.Class<java.net.URL> org.apache.commons.cli.PatternOptionBuilder.URL_VALUE;
		java.lang.Class<java.lang.String> org.apache.commons.cli.PatternOptionBuilder.STRING_VALUE;
		java.lang.Class<java.io.File> org.apache.commons.cli.PatternOptionBuilder.FILE_VALUE;
		java.lang.Class<java.io.File[]> org.apache.commons.cli.PatternOptionBuilder.FILES_VALUE;
		java.lang.Class<?> org.apache.commons.cli.PatternOptionBuilder.CLASS_VALUE;
	related functions:
		method `public static Class<?> getValueType(char)` returns `java.lang.Class<?>`, related with `org.apache.commons.cli.PatternOptionBuilder.getValueType(char)`, api document: 
     * Retrieve the class that {@code ch} represents.
     *
     * @param ch the specified character
     * @return The class that {@code ch} represents
     * @since 1.7.0
     ;
		method `public static void registerTypes()` returns `void`, related with `org.apache.commons.cli.PatternOptionBuilder.getValueType(char)`, api document: 
     * Registers custom {@code Converter}s with the {@code TypeHandler}.
     * @since 1.7.0
     ;
class `org.apache.commons.cli.AlreadySelectedException`:
	related functions:
		method `public Option getOption()` returns `org.apache.commons.cli.Option`, related with `org.apache.commons.cli.PatternOptionBuilder.getValueType(char)`, api document: 
     * Gets the option that was added to the group and triggered the exception.
     *
     * @return the related option
     * @since 1.2
     ;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.