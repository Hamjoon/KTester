package software.amazon.event.ruler;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class RuleCompiler_processMatchExpression_Test {

    private JsonFactory jsonFactory;

    private JsonParser jsonParser;

    @BeforeEach
    void setupBeforeEach() throws IOException {
        jsonFactory = new JsonFactory();
        // Initialize JsonParser with a sample JSON string for testing
        // Adjust this JSON string based on your test case
        String jsonString = "{\"exactMatch\": \"testValue\"}";
        jsonParser = jsonFactory.createParser(jsonString);
    }

    @AfterEach
    void teardownAfterEach() throws IOException {
        if (jsonParser != null) {
            jsonParser.close();
        }
    }

    @Test
    void testProcessMatchExpression_ExactMatch() throws IOException {
        // Test for exact match pattern processing with valid string value.
        String jsonString = "{\"exactMatch\": \"value\"}";
        jsonParser = jsonFactory.createParser(jsonString);
        // Move to the first token
        jsonParser.nextToken();
        // When
        Patterns result = RuleCompiler.processMatchExpression(jsonParser);
        // Then
        assertNotNull(result);
        // Assuming getValue() returns the match value
        assertEquals("value", result.getValue());
    }

    @Test
    void testProcessMatchExpression_PrefixMatch() throws IOException {
        // Test for prefix match pattern processing with valid string value.
        String jsonString = "{\"prefixMatch\": \"prefixValue\"}";
        jsonParser = jsonFactory.createParser(jsonString);
        // Move to the first token
        jsonParser.nextToken();
        // When
        Patterns result = RuleCompiler.processMatchExpression(jsonParser);
        // Then
        assertNotNull(result);
        // Assuming getValue() returns the match value
        assertEquals("prefixValue", result.getValue());
    }

    @Test
    void testProcessMatchExpression_SuffixMatch() throws IOException {
        // Test for suffix match pattern processing with valid string value.
        String jsonString = "{\"suffixMatch\": \"suffixValue\"}";
        jsonParser = jsonFactory.createParser(jsonString);
        // Move to the first token
        jsonParser.nextToken();
        // When
        Patterns result = RuleCompiler.processMatchExpression(jsonParser);
        // Then
        assertNotNull(result);
        // Assuming getValue() returns the match value
        assertEquals("suffixValue", result.getValue());
    }

    @Test
    void testProcessMatchExpression_ExactMatch_InvalidNonString() {
        // Test for exact match pattern processing with invalid non-string value.
        String jsonString = "{\"exactMatch\": 123}";
        jsonParser = jsonFactory.createParser(jsonString);
        // Move to the first token
        jsonParser.nextToken();
        // When & Then
        assertThrows(JsonParseException.class, () -> {
            RuleCompiler.processMatchExpression(jsonParser);
        }, "exact match pattern must be a string");
    }

    @Test
    void testProcessMatchExpression_ExactMatch_TooManyTokens() {
        // Test for exact match pattern processing with additional tokens beyond one key.
        String jsonString = "{\"exactMatch\": \"value\", \"extraKey\": \"extraValue\"}";
        jsonParser = jsonFactory.createParser(jsonString);
        // Move to the first token
        jsonParser.nextToken();
        // When & Then
        assertThrows(JsonParseException.class, () -> {
            RuleCompiler.processMatchExpression(jsonParser);
        }, "Only one key allowed in match expression");
    }

    @Test
    void testProcessMatchExpression_PrefixMatch_InvalidNonString() {
        // Test for prefix match pattern processing with invalid non-string value.
        String jsonString = "{\"prefixMatch\": 123}";
        jsonParser = jsonFactory.createParser(jsonString);
        // Move to the first token
        jsonParser.nextToken();
        // When & Then
        assertThrows(JsonParseException.class, () -> {
            RuleCompiler.processMatchExpression(jsonParser);
        }, "prefix match pattern must be a string");
    }

    @Test
    void testProcessMatchExpression_PrefixMatch_TooManyTokens() {
        // Test for prefix match pattern processing with additional tokens beyond one key.
        String jsonString = "{\"prefixMatch\": \"prefixValue\", \"extraKey\": \"extraValue\"}";
        jsonParser = jsonFactory.createParser(jsonString);
        // Move to the first token
        jsonParser.nextToken();
        // When & Then
        assertThrows(JsonParseException.class, () -> {
            RuleCompiler.processMatchExpression(jsonParser);
        }, "Only one key allowed in match expression");
    }

    @Test
    void testProcessMatchExpression_SuffixMatch_InvalidNonString() {
        // Test for suffix match pattern processing with invalid non-string value.
        String jsonString = "{\"suffixMatch\": 123}";
        jsonParser = jsonFactory.createParser(jsonString);
        // Move to the first token
        jsonParser.nextToken();
        // When & Then
        assertThrows(JsonParseException.class, () -> {
            RuleCompiler.processMatchExpression(jsonParser);
        }, "suffix match pattern must be a string");
    }

    @Test
    void testProcessMatchExpression_SuffixMatch_TooManyTokens() {
        // Test for suffix match pattern processing with additional tokens beyond one key.
        String jsonString = "{\"suffixMatch\": \"suffixValue\", \"extraKey\": \"extraValue\"}";
        jsonParser = jsonFactory.createParser(jsonString);
        // Move to the first token
        jsonParser.nextToken();
        // When & Then
        assertThrows(JsonParseException.class, () -> {
            RuleCompiler.processMatchExpression(jsonParser);
        }, "Only one key allowed in match expression");
    }
}
