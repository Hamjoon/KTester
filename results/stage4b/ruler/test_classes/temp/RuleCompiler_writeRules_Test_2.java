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
