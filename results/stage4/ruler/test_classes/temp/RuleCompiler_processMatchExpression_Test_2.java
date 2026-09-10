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
import com.fasterxml.jackson.core.JsonParseException;
import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class RuleCompiler_processMatchExpression_Test {

    private JsonFactory jsonFactory;

    private JsonParser jsonParser;

    @BeforeEach
    void setupBeforeEach() throws IOException {
        jsonFactory = new JsonFactory();
        String jsonString = "{\"exactMatch\": \"testValue\"}";
        jsonParser = jsonFactory.createParser(jsonString);
    }

    @AfterEach
    void teardownAfterEach() throws IOException {
        if (jsonParser != null) {
            jsonParser.close();
        }
    }

    private Patterns invokeProcessMatchExpression(JsonParser parser) throws Exception {
        Method method = RuleCompiler.class.getDeclaredMethod("processMatchExpression", JsonParser.class);
        // Make the private method accessible
        method.setAccessible(true);
        return (Patterns) method.invoke(null, parser);
    }

    @Test
    void testProcessMatchExpression_ExactMatch() throws IOException {
        String jsonString = "{\"exactMatch\": \"value\"}";
        jsonParser = jsonFactory.createParser(jsonString);
        jsonParser.nextToken();
        Patterns result = invokeProcessMatchExpression(jsonParser);
        assertNotNull(result);
        // Assuming a method getValue() exists in Patterns
        assertEquals("value", result.getValue());
    }

    @Test
    void testProcessMatchExpression_PrefixMatch() throws IOException {
        String jsonString = "{\"prefixMatch\": \"prefixValue\"}";
        jsonParser = jsonFactory.createParser(jsonString);
        jsonParser.nextToken();
        Patterns result = invokeProcessMatchExpression(jsonParser);
        assertNotNull(result);
        assertEquals("prefixValue", result.getValue());
    }

    @Test
    void testProcessMatchExpression_SuffixMatch() throws IOException {
        String jsonString = "{\"suffixMatch\": \"suffixValue\"}";
        jsonParser = jsonFactory.createParser(jsonString);
        jsonParser.nextToken();
        Patterns result = invokeProcessMatchExpression(jsonParser);
        assertNotNull(result);
        assertEquals("suffixValue", result.getValue());
    }

    @Test
    void testProcessMatchExpression_ExactMatch_InvalidNonString() {
        String jsonString = "{\"exactMatch\": 123}";
        jsonParser = jsonFactory.createParser(jsonString);
        jsonParser.nextToken();
        assertThrows(JsonParseException.class, () -> {
            invokeProcessMatchExpression(jsonParser);
        }, "exact match pattern must be a string");
    }

    @Test
    void testProcessMatchExpression_ExactMatch_TooManyTokens() {
        String jsonString = "{\"exactMatch\": \"value\", \"extraKey\": \"extraValue\"}";
        jsonParser = jsonFactory.createParser(jsonString);
        jsonParser.nextToken();
        assertThrows(JsonParseException.class, () -> {
            invokeProcessMatchExpression(jsonParser);
        }, "Only one key allowed in match expression");
    }

    @Test
    void testProcessMatchExpression_PrefixMatch_InvalidNonString() {
        String jsonString = "{\"prefixMatch\": 123}";
        jsonParser = jsonFactory.createParser(jsonString);
        jsonParser.nextToken();
        assertThrows(JsonParseException.class, () -> {
            invokeProcessMatchExpression(jsonParser);
        }, "prefix match pattern must be a string");
    }

    @Test
    void testProcessMatchExpression_PrefixMatch_TooManyTokens() {
        String jsonString = "{\"prefixMatch\": \"prefixValue\", \"extraKey\": \"extraValue\"}";
        jsonParser = jsonFactory.createParser(jsonString);
        jsonParser.nextToken();
        assertThrows(JsonParseException.class, () -> {
            invokeProcessMatchExpression(jsonParser);
        }, "Only one key allowed in match expression");
    }

    @Test
    void testProcessMatchExpression_SuffixMatch_InvalidNonString() {
        String jsonString = "{\"suffixMatch\": 123}";
        jsonParser = jsonFactory.createParser(jsonString);
        jsonParser.nextToken();
        assertThrows(JsonParseException.class, () -> {
            invokeProcessMatchExpression(jsonParser);
        }, "suffix match pattern must be a string");
    }

    @Test
    void testProcessMatchExpression_SuffixMatch_TooManyTokens() {
        String jsonString = "{\"suffixMatch\": \"suffixValue\", \"extraKey\": \"extraValue\"}";
        jsonParser = jsonFactory.createParser(jsonString);
        jsonParser.nextToken();
        assertThrows(JsonParseException.class, () -> {
            invokeProcessMatchExpression(jsonParser);
        }, "Only one key allowed in match expression");
    }
}
