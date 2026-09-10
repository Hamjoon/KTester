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
import java.io.StringReader;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
class JsonRuleCompiler_processMatchExpression_Test {

    private JsonRuleCompiler jsonRuleCompiler;

    private JsonParser jsonParser;

    @BeforeEach
    void setupBeforeEach() throws Exception {
        jsonRuleCompiler = new JsonRuleCompiler();
        JsonFactory jsonFactory = new JsonFactory();
        // Example JSON input
        String jsonString = "{\"exampleKey\": \"exampleValue\"}";
        jsonParser = jsonFactory.createParser(new StringReader(jsonString));
    }

    @AfterEach
    void teardownAfterEach() throws Exception {
        if (jsonParser != null) {
            // Cleanup resources
            jsonParser.close();
        }
    }

    @Test
    void testProcessMatchExpression_withExactMatch() throws Exception {
        // Prepare the JSON for an exact match
        String jsonString = "{\"exactMatch\": \"testValue\"}";
        jsonParser = new JsonFactory().createParser(new StringReader(jsonString));
        // Invoke the method
        Patterns result = JsonRuleCompiler.processMatchExpression(jsonParser);
        // Validate the result
        assertNotNull(result);
        // Assuming getValue() returns the string value
        assertEquals("testValue", result.getValue());
    }

    @Test
    void testProcessMatchExpression_withPrefixMatch() throws Exception {
        // Prepare the JSON for a prefix match
        String jsonString = "{\"prefixMatch\": \"testPrefix\"}";
        jsonParser = new JsonFactory().createParser(new StringReader(jsonString));
        // Invoke the method
        Patterns result = JsonRuleCompiler.processMatchExpression(jsonParser);
        // Validate the result
        assertNotNull(result);
        // Assuming getValue() returns the string value
        assertEquals("testPrefix", result.getValue());
    }

    @Test
    void testProcessMatchExpression_withExactMatch_nonStringValue() throws Exception {
        // Prepare the JSON for an exact match with a non-string value
        String jsonString = "{\"exactMatch\": 123}";
        jsonParser = new JsonFactory().createParser(new StringReader(jsonString));
        // Expect JsonParseException to be thrown
        JsonParseException exception = assertThrows(JsonParseException.class, () -> {
            JsonRuleCompiler.processMatchExpression(jsonParser);
        });
        // Validate the exception message
        assertEquals("exact match pattern must be a string", exception.getMessage());
    }

    @Test
    void testProcessMatchExpression_withPrefixMatch_nonStringValue() throws Exception {
        // Prepare the JSON for a prefix match with a non-string value
        String jsonString = "{\"prefixMatch\": 123}";
        jsonParser = new JsonFactory().createParser(new StringReader(jsonString));
        // Expect JsonParseException to be thrown
        JsonParseException exception = assertThrows(JsonParseException.class, () -> {
            JsonRuleCompiler.processMatchExpression(jsonParser);
        });
        // Validate the exception message
        assertEquals("prefix match pattern must be a string", exception.getMessage());
    }

    @Test
    void testProcessMatchExpression_withSuffixMatch() throws Exception {
        // Prepare the JSON for a suffix match
        String jsonString = "{\"suffixMatch\": \"testSuffix\"}";
        jsonParser = new JsonFactory().createParser(new StringReader(jsonString));
        // Invoke the method
        Patterns result = JsonRuleCompiler.processMatchExpression(jsonParser);
        // Validate the result
        assertNotNull(result);
        // Assuming getValue() returns the string value
        assertEquals("testSuffix", result.getValue());
    }

    @Test
    void testProcessMatchExpression_withSuffixMatch_nonStringValue() throws Exception {
        // Prepare the JSON for a suffix match with a non-string value
        String jsonString = "{\"suffixMatch\": 123}";
        jsonParser = new JsonFactory().createParser(new StringReader(jsonString));
        // Expect JsonParseException to be thrown
        JsonParseException exception = assertThrows(JsonParseException.class, () -> {
            JsonRuleCompiler.processMatchExpression(jsonParser);
        });
        // Validate the exception message
        assertEquals("suffix match pattern must be a string", exception.getMessage());
    }

    @Test
    void testProcessMatchExpression_withNumericMatch() throws Exception {
        // Prepare the JSON for a numeric match
        String jsonString = "{\"numeric\": [1, 2, 3]}";
        jsonParser = new JsonFactory().createParser(new StringReader(jsonString));
        // Invoke the method
        Patterns result = JsonRuleCompiler.processMatchExpression(jsonParser);
        // Validate the result
        assertNotNull(result);
        // Additional assertions can be added based on the expected numeric pattern representation
    }

    @Test
    void testProcessMatchExpression_withNumericMatch_nonArrayValue() throws Exception {
        // Prepare the JSON for a numeric match with a non-array value
        String jsonString = "{\"numeric\": \"notAnArray\"}";
        jsonParser = new JsonFactory().createParser(new StringReader(jsonString));
        // Expect JsonParseException to be thrown
        JsonParseException exception = assertThrows(JsonParseException.class, () -> {
            JsonRuleCompiler.processMatchExpression(jsonParser);
        });
        // Validate the exception message
        assertEquals("Value of NUMERIC must be an array.", exception.getMessage());
    }
}
