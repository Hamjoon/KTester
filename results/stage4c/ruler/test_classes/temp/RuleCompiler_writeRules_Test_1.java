package software.amazon.event.ruler;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;
import com.fasterxml.jackson.core.JsonParseException;
import org.junit.jupiter.api.extension.ExtendWith;
import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class RuleCompiler_writeRules_Test {

    private static final JsonFactory JSON_FACTORY = new JsonFactory();

    private Map<String, List<Patterns>> rule;

    private JsonParser parser;

    @BeforeEach
    void setupBeforeEach() throws IOException {
        // Initialize the map and parser before each test
        rule = new HashMap<>();
    }

    private void invokeWriteRules(Map<String, List<Patterns>> rule, String name, JsonParser parser, boolean withQuotes) throws Exception {
        Method method = RuleCompiler.class.getDeclaredMethod("writeRules", Map.class, String.class, JsonParser.class, boolean.class);
        method.setAccessible(true);
        method.invoke(null, rule, name, parser, withQuotes);
    }

    @Test
    void testWriteRules_withValidJsonArray() throws IOException {
        // Given: A valid JSON array with various types of values
        String jsonArray = "[{\"key\": \"value\"}, \"stringValue\", 42, true, null]";
        parser = JSON_FACTORY.createParser(new StringReader(jsonArray));
        // Move to the start of the array
        parser.nextToken();
        // When: Calling writeRules
        invokeWriteRules(rule, "testName", parser, true);
        // Then: Verify the rule map is populated correctly
        assertFalse(rule.isEmpty(), "Rule map should not be empty");
        assertTrue(rule.containsKey("testName"), "Rule map should contain the key 'testName'");
        List<Patterns> patterns = rule.get("testName");
        assertNotNull(patterns, "Patterns list should not be null");
        assertEquals(5, patterns.size(), "Patterns list should contain 5 elements");
    }

    @Test
    void testWriteRules_withEmptyJsonArray() {
        // Given: An empty JSON array
        String jsonArray = "[]";
        parser = JSON_FACTORY.createParser(new StringReader(jsonArray));
        // Move to the start of the array
        parser.nextToken();
        // When & Then: Expect a JsonParseException due to empty array
        Exception exception = assertThrows(JsonParseException.class, () -> {
            invokeWriteRules(rule, "testName", parser, true);
        });
        String expectedMessage = "Empty arrays are not allowed";
        assertTrue(exception.getMessage().contains(expectedMessage), "Exception message should contain expected message");
    }

    @Test
    void testWriteRules_withStartObjectToken() throws IOException {
        // Given: A JSON array with a START_OBJECT token
        String jsonArray = "[{\"key\": \"value\"}]";
        parser = JSON_FACTORY.createParser(new StringReader(jsonArray));
        // Move to the start of the array
        parser.nextToken();
        // When: Calling writeRules
        invokeWriteRules(rule, "testRule", parser, false);
        // Then: Verify the rule map contains one Patterns object
        assertNotNull(rule.get("testRule"), "Rule should contain a list");
        assertEquals(1, rule.get("testRule").size(), "List should contain one Patterns object");
    }

    @Test
    void testWriteRules_withValueStringToken_ValidIP() throws IOException {
        // Given: A JSON array with a VALUE_STRING token containing a valid IP
        String jsonArray = "[\"192.168.0.1\"]";
        parser = JSON_FACTORY.createParser(new StringReader(jsonArray));
        // Move to the start of the array
        parser.nextToken();
        // When: Calling writeRules
        invokeWriteRules(rule, "testRule", parser, false);
        // Then: Verify the rule map contains a Range object
        assertNotNull(rule.get("testRule"), "Rule should contain a list");
        assertTrue(rule.get("testRule").get(0) instanceof Range, "List should contain a Range object");
    }

    @Test
    void testWriteRules_withValueStringToken_Quoted() throws IOException {
        // Given: A JSON array with a VALUE_STRING token and withQuotes set to true
        String jsonArray = "[\"someString\"]";
        parser = JSON_FACTORY.createParser(new StringReader(jsonArray));
        // Move to the start of the array
        parser.nextToken();
        // When: Calling writeRules
        invokeWriteRules(rule, "testRule", parser, true);
        // Then: Verify the rule map contains a Patterns object with quotes
        assertNotNull(rule.get("testRule"), "Rule should contain a list");
        assertEquals("\"someString\"", rule.get("testRule").get(0).toString(), "Patterns object should be quoted");
    }

    @Test
    void testWriteRules_withValueStringToken_Unquoted() throws IOException {
        // Given: A JSON array with a VALUE_STRING token and withQuotes set to false
        String jsonArray = "[\"someString\"]";
        parser = JSON_FACTORY.createParser(new StringReader(jsonArray));
        // Move to the start of the array
        parser.nextToken();
        // When: Calling writeRules
        invokeWriteRules(rule, "testRule", parser, false);
        // Then: Verify the rule map contains a Patterns object without quotes
        assertNotNull(rule.get("testRule"), "Rule should contain a list");
        assertEquals("someString", rule.get("testRule").get(0).toString(), "Patterns object should not be quoted");
    }

    @Test
    void testWriteRules_withValueNumberFloatToken() throws IOException {
        // Given: A JSON array with a VALUE_NUMBER_FLOAT token
        String jsonArray = "[123.456]";
        parser = JSON_FACTORY.createParser(new StringReader(jsonArray));
        // Move to the start of the array
        parser.nextToken();
        // When: Calling writeRules
        invokeWriteRules(rule, "testRule", parser, false);
        // Then: Verify the rule map contains numeric and exact match Patterns objects
        assertNotNull(rule.get("testRule"), "Rule should contain a list");
        assertEquals(2, rule.get("testRule").size(), "List should contain two Patterns objects");
    }

    @Test
    void testWriteRules_withValueNumberIntToken() throws IOException {
        // Given: A JSON array with a VALUE_NUMBER_INT token
        String jsonArray = "[123]";
        parser = JSON_FACTORY.createParser(new StringReader(jsonArray));
        // Move to the start of the array
        parser.nextToken();
        // When: Calling writeRules
        invokeWriteRules(rule, "testRule", parser, false);
        // Then: Verify the rule map contains numeric and exact match Patterns objects
        assertNotNull(rule.get("testRule"), "Rule should contain a list");
        assertEquals(2, rule.get("testRule").size(), "List should contain two Patterns objects");
    }

    @Test
    void testWriteRules_withValueNullToken() throws IOException {
        // Given: A JSON array with a VALUE_NULL token
        String jsonArray = "[null]";
        parser = JSON_FACTORY.createParser(new StringReader(jsonArray));
        // Move to the start of the array
        parser.nextToken();
        // When: Calling writeRules
        invokeWriteRules(rule, "testRule", parser, false);
        // Then: Verify the rule map contains an exact match Patterns object for null
        assertNotNull(rule.get("testRule"), "Rule should contain a list");
        assertEquals("null", rule.get("testRule").get(0).toString(), "Patterns object should match 'null'");
    }

    @Test
    void testWriteRules_withValueTrueToken() throws IOException {
        // Given: A JSON array with a VALUE_TRUE token
        String jsonArray = "[true]";
        parser = JSON_FACTORY.createParser(new StringReader(jsonArray));
        // Move to the start of the array
        parser.nextToken();
        // When: Calling writeRules
        invokeWriteRules(rule, "testRule", parser, false);
        // Then: Verify the rule map contains an exact match Patterns object for true
        assertNotNull(rule.get("testRule"), "Rule should contain a list");
        assertEquals("true", rule.get("testRule").get(0).toString(), "Patterns object should match 'true'");
    }

    @Test
    void testWriteRules_withValueFalseToken() throws IOException {
        // Given: A JSON array with a VALUE_FALSE token
        String jsonArray = "[false]";
        parser = JSON_FACTORY.createParser(new StringReader(jsonArray));
        // Move to the start of the array
        parser.nextToken();
        // When: Calling writeRules
        invokeWriteRules(rule, "testRule", parser, false);
        // Then: Verify the rule map contains an exact match Patterns object for false
        assertNotNull(rule.get("testRule"), "Rule should contain a list");
        assertEquals("false", rule.get("testRule").get(0).toString(), "Patterns object should match 'false'");
    }

    @Test
    void testWriteRules_withUnsupportedToken() {
        // Given: A JSON array with an unsupported token
        String jsonArray = "[\"unsupported\"]";
        parser = JSON_FACTORY.createParser(new StringReader(jsonArray));
        // Move to the start of the array
        parser.nextToken();
        // When & Then: Expect a JsonParseException due to unsupported token
        Exception exception = assertThrows(JsonParseException.class, () -> {
            invokeWriteRules(rule, "testRule", parser, false);
        });
        String expectedMessage = "Match value must be String, number, true, false, or null";
        assertTrue(exception.getMessage().contains(expectedMessage), "Exception message should contain expected message");
    }

    @Test
    void testWriteRules_withValidCIDRNotation() throws IOException {
        // Given: A JSON array with a VALUE_STRING token containing a valid CIDR notation
        String jsonArray = "[\"192.168.0.1/24\"]";
        parser = JSON_FACTORY.createParser(new StringReader(jsonArray));
        // Move to the start of the array
        parser.nextToken();
        // When: Calling writeRules
        invokeWriteRules(rule, "testRule", parser, false);
        // Then: Verify the rule map contains a Range object representing the CIDR range
        assertNotNull(rule.get("testRule"), "Rule should contain a list");
        assertTrue(rule.get("testRule").get(0) instanceof Range, "List should contain a Range object");
    }

    @Test
    void testWriteRules_withInvalidCIDRNotation() throws IOException {
        // Given: A JSON array with a VALUE_STRING token containing an invalid CIDR notation
        String jsonArray = "[\"invalidCIDR\"]";
        parser = JSON_FACTORY.createParser(new StringReader(jsonArray));
        // Move to the start of the array
        parser.nextToken();
        // When: Calling writeRules
        invokeWriteRules(rule, "testRule", parser, false);
        // Then: Verify the rule map contains an unquoted Patterns object
        assertNotNull(rule.get("testRule"), "Rule should contain a list");
        assertEquals("invalidCIDR", rule.get("testRule").get(0).toString(), "Patterns object should match 'invalidCIDR'");
    }

    @Test
    void testWriteRules_withLargeNumberString() throws IOException {
        // Given: A JSON array with a VALUE_STRING token containing a very large number string
        String jsonArray = "[\"123456789012345678901234567890\"]";
        parser = JSON_FACTORY.createParser(new StringReader(jsonArray));
        // Move to the start of the array
        parser.nextToken();
        // When: Calling writeRules
        invokeWriteRules(rule, "testRule", parser, false);
        // Then: Verify the rule map contains an exact match Patterns object for the large number
        assertNotNull(rule.get("testRule"), "Rule should contain a list");
        assertEquals("123456789012345678901234567890", rule.get("testRule").get(0).toString(), "Patterns object should match the large number string");
    }

    @Test
    void testWriteRules_withInvalidIPAddress() throws IOException {
        // Given: A JSON array with a VALUE_STRING token containing an invalid IP address
        String jsonArray = "[\"123.45.67.89\"]";
        parser = JSON_FACTORY.createParser(new StringReader(jsonArray));
        // Move to the start of the array
        parser.nextToken();
        // When: Calling writeRules
        invokeWriteRules(rule, "testRule", parser, true);
        // Then: Verify the rule map contains a Patterns object with quotes
        assertNotNull(rule.get("testRule"), "Rule should contain a list");
        assertEquals("\"123.45.67.89\"", rule.get("testRule").get(0).toString(), "Patterns object should be quoted");
    }

    @Test
    void testWriteRules_withLargeInteger() throws IOException {
        // Given: A JSON array with a VALUE_NUMBER_INT token containing a very large integer
        String jsonArray = "[123456789012345678901234567890]";
        parser = JSON_FACTORY.createParser(new StringReader(jsonArray));
        // Move to the start of the array
        parser.nextToken();
        // When: Calling writeRules
        invokeWriteRules(rule, "testRule", parser, false);
        // Then: Verify the rule map contains an exact match Patterns object for the large number
        assertNotNull(rule.get("testRule"), "Rule should contain a list");
        assertEquals("123456789012345678901234567890", rule.get("testRule").get(0).toString(), "Patterns object should match the large integer");
    }

    @Test
    void testWriteRules_withMalformedJsonInput() {
        // Given: A malformed JSON input
        String jsonArray = "[{malformed: json]";
        parser = JSON_FACTORY.createParser(new StringReader(jsonArray));
        // When & Then: Expect a JsonParseException due to malformed input
        Exception exception = assertThrows(JsonParseException.class, () -> {
            invokeWriteRules(rule, "testRule", parser, false);
        });
        assertNotNull(exception, "Should throw a JsonParseException due to malformed JSON input");
    }
}
