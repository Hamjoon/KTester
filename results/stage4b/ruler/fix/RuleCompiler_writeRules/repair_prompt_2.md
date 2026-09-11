
You are an experienced Java developer tasked with fixing a piece of code that contains compilation errors. Your task is to resolve these issues after analyzing the code and its compilation feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any syntactical or logical errors.
2. Analyze the compilation feedback to understand the specific errors and their locations within the code.
3. Apply necessary corrections to the code based on the feedback, and output the compelete correct code.
The Java code you need to fix is provided below:
```java
package software.amazon.event.ruler;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import java.io.StringReader;
import java.io.IOException;
import java.util.*;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.JsonParseException;
import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class RuleCompiler_writeRules_Test {

    private Map<String, List<Patterns>> rule;

    private JsonParser parser;

    private final JsonFactory jsonFactory = new JsonFactory();

    // Example value, adjust as necessary
    private final boolean withQuotes = true;

    @BeforeEach
    void setupBeforeEach() {
        rule = new HashMap<>();
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup code if necessary
        rule.clear();
        parser = null;
    }

    private void invokeWriteRules(Map<String, List<Patterns>> rule, String name, JsonParser parser, boolean withQuotes) throws Exception {
        parser = jsonFactory.createParser(new StringReader(jsonInput));
        Method method = RuleCompiler.class.getDeclaredMethod("writeRules", Map.class, String.class, JsonParser.class, boolean.class);
        method.setAccessible(true);
        method.invoke(null, rule, name, parser, withQuotes);
    }

    @Test
    void testWriteRules() {
        // Given
        String name = "testRule";
        // Provide a valid JSON input
        String jsonInput = "[{\"name\":\"test\"}]";
        // When
        try {
            invokeWriteRules(rule, name, jsonInput, withQuotes);
        } catch (Exception e) {
            fail("Exception thrown during writeRules: " + e.getCause().getMessage());
        }
        // Then
        assertFalse(rule.isEmpty(), "Rule map should not be empty after writeRules call");
        assertTrue(rule.containsKey(name), "Rule map should contain the key: " + name);
    }

    @Test
    void testWriteRules_ValidCases() throws Exception {
        // Test case where parser returns valid objects, should populate the rule map with patterns.
        String name = "testRule";
        String jsonInput = "[{\"name\":\"test\"}, {\"name\":\"test2\"}]";
        // When
        invokeWriteRules(rule, name, jsonInput, false);
        // Then
        assertFalse(rule.isEmpty(), "Rule map should not be empty after writeRules call");
        assertTrue(rule.containsKey(name), "Rule map should contain the key: " + name);
    }

    @Test
    void testWriteRules_ValidCases_WithQuotes() throws Exception {
        // Test case where parser returns a string value and withQuotes is true, should add quoted patterns.
        String name = "testRule";
        // JSON input for quoted string
        String jsonInput = "[\"testString\"]";
        // When
        invokeWriteRules(rule, name, jsonInput, true);
        // Then
        assertFalse(rule.isEmpty(), "Rule map should not be empty after writeRules call");
        assertTrue(rule.containsKey(name), "Rule map should contain the key: " + name);
    }

    @Test
    void testWriteRules_ExceptionCases_UnexpectedToken() throws Exception {
        // Test case where parser returns an unexpected token, should throw an exception.
        String name = "testRule";
        // JSON input with unexpected token
        String jsonInput = "[{\"unexpectedToken\": true}]";
        // When and Then
        JsonParseException exception = assertThrows(JsonParseException.class, () -> {
            invokeWriteRules(rule, name, jsonInput, false);
        });
        assertEquals("Match value must be String, number, true, false, or null", exception.getMessage());
    }

    @Test
    void testWriteRules_ExceptionCases_EmptyArray() throws Exception {
        // Test case where parser indicates an empty array, should throw an exception.
        String name = "testRule";
        // JSON input for empty array
        String jsonInput = "[]";
        // When and Then
        JsonParseException exception = assertThrows(JsonParseException.class, () -> {
            invokeWriteRules(rule, name, jsonInput, false);
        });
        assertEquals("Empty arrays are not allowed", exception.getMessage());
    }

    @Test
    void testWriteRules_EdgeCases_NumericValue() throws Exception {
        // Test case where parser processes numeric values, should add numeric patterns.
        String name = "testRule";
        // JSON input for numeric value
        String jsonInput = "[123.45]";
        // When
        invokeWriteRules(rule, name, jsonInput, false);
        // Then
        assertFalse(rule.isEmpty(), "Rule map should not be empty after writeRules call");
        assertTrue(rule.containsKey(name), "Rule map should contain the key: " + name);
    }

    @Test
    void testWriteRules_ExceptionCases_InvalidNumericValue() throws Exception {
        // Test case where parser encounters an invalid numeric value that cannot be processed, should throw an exception.
        String name = "testRule";
        // JSON input for invalid numeric value
        String jsonInput = "[\"invalidNumber\"]";
        // When and Then
        JsonParseException exception = assertThrows(JsonParseException.class, () -> {
            invokeWriteRules(rule, name, jsonInput, false);
        });
        assertEquals("Invalid numeric value encountered", exception.getMessage());
    }
}
```

Corresponding compilation feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
src/test/java/software/amazon/event/ruler/RuleCompiler_writeRules_Test.java:43: error: cannot find symbol
        parser = jsonFactory.createParser(new StringReader(jsonInput));
                                                           ^
  symbol:   variable jsonInput
  location: class RuleCompiler_writeRules_Test
src/test/java/software/amazon/event/ruler/RuleCompiler_writeRules_Test.java:57: error: incompatible types: String cannot be converted to JsonParser
            invokeWriteRules(rule, name, jsonInput, withQuotes);
                                         ^
src/test/java/software/amazon/event/ruler/RuleCompiler_writeRules_Test.java:72: error: incompatible types: String cannot be converted to JsonParser
        invokeWriteRules(rule, name, jsonInput, false);
                                     ^
src/test/java/software/amazon/event/ruler/RuleCompiler_writeRules_Test.java:85: error: incompatible types: String cannot be converted to JsonParser
        invokeWriteRules(rule, name, jsonInput, true);
                                     ^
src/test/java/software/amazon/event/ruler/RuleCompiler_writeRules_Test.java:99: error: incompatible types: String cannot be converted to JsonParser
            invokeWriteRules(rule, name, jsonInput, false);
                                         ^
src/test/java/software/amazon/event/ruler/RuleCompiler_writeRules_Test.java:112: error: incompatible types: String cannot be converted to JsonParser
            invokeWriteRules(rule, name, jsonInput, false);
                                         ^
src/test/java/software/amazon/event/ruler/RuleCompiler_writeRules_Test.java:124: error: incompatible types: String cannot be converted to JsonParser
        invokeWriteRules(rule, name, jsonInput, false);
                                     ^
src/test/java/software/amazon/event/ruler/RuleCompiler_writeRules_Test.java:138: error: incompatible types: String cannot be converted to JsonParser
            invokeWriteRules(rule, name, jsonInput, false);
                                         ^
Note: Some messages have been simplified; recompile with -Xdiags:verbose to get full output
8 errors

```

And you can use the following related information to assist you in your analysis and correction:
```
{'api document of class software.amazon.event.ruler.RuleCompiler': '\n * Compiles Rules, expressed in JSON, for use in Ruler.\n * There are two flavors of compilation:\n * 1. Compile a JSON-based Rule into Map of String to List of Patterns which can be used in rulesForEvent,\n *    and has a "check" variant that just checks rules for syntactic accuracy\n * 2. Starting in ListBasedRuleCompiler, does the same thing but expresses field names as List ofString\n *    rather than "."-separated strings for use in the Ruler class, which does not use state machines and\n *    needs to step into the event field by field.\n *\n * Is public so clients can call the check() method to syntax-check filters\n ', 'dependent classes': 'class `software.amazon.event.ruler.RuleCompiler.ListBasedRuleCompiler`:\n\trelated functions:\n\t\tmethod `private static void writeRules(Map<List<String>, List<Patterns>>, List<String>, JsonParser, boolean)` returns `void`, related with `software.amazon.event.ruler.RuleCompiler.writeRules(Map<String, List<Patterns>>, String, JsonParser, boolean)`;\nclass `software.amazon.event.ruler.JsonRuleCompiler`:\n\trelated functions:\n\t\tmethod `private static Patterns processAnythingButMatchExpression(JsonParser, JsonToken)` returns `software.amazon.event.ruler.Patterns`, related with `software.amazon.event.ruler.RuleCompiler.writeRules(Map<String, List<Patterns>>, String, JsonParser, boolean)`;\n\t\tmethod `private static Patterns processAnythingButListMatchExpression(JsonParser)` returns `software.amazon.event.ruler.Patterns`, related with `software.amazon.event.ruler.RuleCompiler.writeRules(Map<String, List<Patterns>>, String, JsonParser, boolean)`;\n\t\tmethod `private static void writeRules(List<Map<String, List<Patterns>>>, String, JsonParser, boolean)` returns `void`, related with `software.amazon.event.ruler.RuleCompiler.writeRules(Map<String, List<Patterns>>, String, JsonParser, boolean)`;\nclass `software.amazon.event.ruler.RuleCompiler`:\n\trelated functions:\n\t\tmethod `private static Patterns processAnythingButMatchExpression(JsonParser, JsonToken)` returns `software.amazon.event.ruler.Patterns`, related with `software.amazon.event.ruler.RuleCompiler.writeRules(Map<String, List<Patterns>>, String, JsonParser, boolean)`;\n\t\tmethod `private static Patterns processAnythingButListMatchExpression(JsonParser)` returns `software.amazon.event.ruler.Patterns`, related with `software.amazon.event.ruler.RuleCompiler.writeRules(Map<String, List<Patterns>>, String, JsonParser, boolean)`;\n\t\tmethod `private static Patterns processNumericMatchExpression(JsonParser)` returns `software.amazon.event.ruler.Patterns`, related with `software.amazon.event.ruler.RuleCompiler.writeRules(Map<String, List<Patterns>>, String, JsonParser, boolean)`;\n\t\tmethod `private static Patterns processAnythingButEqualsIgnoreCaseListMatchExpression(JsonParser)` returns `software.amazon.event.ruler.Patterns`, related with `software.amazon.event.ruler.RuleCompiler.writeRules(Map<String, List<Patterns>>, String, JsonParser, boolean)`;\n\t\tmethod `private static Patterns completeNumericRange(JsonParser, JsonToken, double, boolean)` returns `software.amazon.event.ruler.Patterns`, related with `software.amazon.event.ruler.RuleCompiler.writeRules(Map<String, List<Patterns>>, String, JsonParser, boolean)`;'}
```

**Note**: If the method-to-test in the @Test function is a private method, you can use **reflection** to call it.

Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 


