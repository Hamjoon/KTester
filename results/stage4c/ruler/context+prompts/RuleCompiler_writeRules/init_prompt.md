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
Here is the code of focal method, `writeRules(Map<String, List<Patterns>>, String, JsonParser, boolean)`, with the focal class `RuleCompiler_writeRules_Test`:
```Java
package software.amazon.event.ruler;
import java.util.ArrayDeque;
import java.util.Set;
import java.util.HashMap;
import java.io.InputStream;
import java.util.ArrayList;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonFactory;
import java.util.Deque;
import com.fasterxml.jackson.core.JsonParseException;
import java.io.Reader;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.List;
import java.util.Map;
import java.util.HashSet;
public final class RuleCompiler {
    
    private static void writeRules(final Map<String, List<Patterns>> rule, final String name, final JsonParser parser, final boolean withQuotes) throws IOException {
        JsonToken token;
        final List<Patterns> values = new ArrayList<>();
        while ((token = parser.nextToken()) != JsonToken.END_ARRAY) {
            switch (token) {
                case START_OBJECT :
                    values.add(processMatchExpression(parser));
                    break;
                case VALUE_STRING :
                    final String toMatch = parser.getText();
                    final Range ipRange = CIDR.ipToRangeIfPossible(toMatch);
                    if (ipRange != null) {
                        values.add(ipRange);
                    } else if (withQuotes) {
                        values.add(Patterns.exactMatch(('"' + toMatch) + '"'));
                    } else {
                        values.add(Patterns.exactMatch(toMatch));
                    }
                    break;
                case VALUE_NUMBER_FLOAT :
                case VALUE_NUMBER_INT :
                    /* If the rule specifies a match to a number, we'll insert matchers for both the
                     literal expression and the ComparableNumber form. But the number might not
                     be representble as a ComparableNumber, for example an AWS account number,
                     so make that condition survivable.
                     */
                    try {
                        values.add(Patterns.numericEquals(parser.getDoubleValue()));
                    } catch (Exception e) {
                        // no-op
                    }
                    values.add(Patterns.exactMatch(parser.getText()));
                    break;
                case VALUE_NULL :
                case VALUE_TRUE :
                case VALUE_FALSE :
                    values.add(Patterns.exactMatch(parser.getText()));
                    break;
                default :
                    barf(parser, "Match value must be String, number, true, false, or null");
            }
        } 
        if (values.isEmpty()) {
            barf(parser, "Empty arrays are not allowed");
        }
        rule.put(name, values);
    }
private static void barf(final JsonParser parser, final String message) throws JsonParseException;
    public static String check(final byte[] source);
    public static String check(final InputStream source);
    public static String check(final Reader source);
    public static String check(final String source);
    public static Map<String, List<Patterns>> compile(final byte[] source) throws IOException;
    public static Map<String, List<Patterns>> compile(final InputStream source) throws IOException;
    public static Map<String, List<Patterns>> compile(final Reader source) throws IOException;
    public static Map<String, List<Patterns>> compile(final String source) throws IOException;
    private static Patterns processMatchExpression(final JsonParser parser) throws IOException;
}
```


@input{context information}

api document of class software.amazon.event.ruler.RuleCompiler: 

 * Compiles Rules, expressed in JSON, for use in Ruler.
 * There are two flavors of compilation:
 * 1. Compile a JSON-based Rule into Map of String to List of Patterns which can be used in rulesForEvent,
 *    and has a "check" variant that just checks rules for syntactic accuracy
 * 2. Starting in ListBasedRuleCompiler, does the same thing but expresses field names as List ofString
 *    rather than "."-separated strings for use in the Ruler class, which does not use state machines and
 *    needs to step into the event field by field.
 *
 * Is public so clients can call the check() method to syntax-check filters
 

constructors for class `software.amazon.event.ruler.RuleCompiler`: 
params: 
body:
```java
    private RuleCompiler() {
      throw new UnsupportedOperationException("You can't create instance of utility class.");
    }

```

invoke examples of focal method: 
example 1:
```java
    private static void parseObject(final Map<String, List<Patterns>> rule,
                                    final Path path,
                                    final JsonParser parser,
                                    final boolean withQuotes) throws IOException {

        boolean fieldsPresent = false;
        while (parser.nextToken() != JsonToken.END_OBJECT) {
            fieldsPresent = true;
            final String stepName = parser.getCurrentName();
            switch (parser.nextToken()) {
            case START_OBJECT:
                path.push(stepName);
                parseObject(rule, path, parser, withQuotes);
    }
    private static void parseObject(final Map<String, List<Patterns>> rule,
                                    final Path path,
                                    final JsonParser parser,
                                    final boolean withQuotes) throws IOException {

        boolean fieldsPresent = false;
        while (parser.nextToken() != JsonToken.END_OBJECT) {
            fieldsPresent = true;
            final String stepName = parser.getCurrentName();
            switch (parser.nextToken()) {
            case START_ARRAY:
                writeRules(rule, path.extendedName(stepName), parser, withQuotes);
    }
```
example 2:
```java
    public static String check(final Reader source) {
        try {
            doCompile(JSON_FACTORY.createParser(source));
    }
    private static Map<String, List<Patterns>> doCompile(final JsonParser parser) throws IOException {
        final Path path = new Path();
        final Map<String, List<Patterns>> rule = new HashMap<>();
        if (parser.nextToken() != JsonToken.START_OBJECT) {
        parseObject(rule, path, parser, true);
    }
    private static void parseObject(final Map<String, List<Patterns>> rule,
                                    final Path path,
                                    final JsonParser parser,
                                    final boolean withQuotes) throws IOException {

        boolean fieldsPresent = false;
        while (parser.nextToken() != JsonToken.END_OBJECT) {
            fieldsPresent = true;
            final String stepName = parser.getCurrentName();
            switch (parser.nextToken()) {
            case START_ARRAY:
                writeRules(rule, path.extendedName(stepName), parser, withQuotes);
    }
```
example 3:
```java
    public static String check(final String source) {
        try {
            doCompile(JSON_FACTORY.createParser(source));
    }
    private static Map<String, List<Patterns>> doCompile(final JsonParser parser) throws IOException {
        final Path path = new Path();
        final Map<String, List<Patterns>> rule = new HashMap<>();
        if (parser.nextToken() != JsonToken.START_OBJECT) {
        parseObject(rule, path, parser, true);
    }
    private static void parseObject(final Map<String, List<Patterns>> rule,
                                    final Path path,
                                    final JsonParser parser,
                                    final boolean withQuotes) throws IOException {

        boolean fieldsPresent = false;
        while (parser.nextToken() != JsonToken.END_OBJECT) {
            fieldsPresent = true;
            final String stepName = parser.getCurrentName();
            switch (parser.nextToken()) {
            case START_ARRAY:
                writeRules(rule, path.extendedName(stepName), parser, withQuotes);
    }
```



@input{test class template}
```java
package software.amazon.event.ruler;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
// Add other imports if necessary

@ExtendWith(MockitoExtension.class)
class RuleCompiler_writeRules_Test {
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