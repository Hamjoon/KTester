package software.amazon.event.ruler;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.StringReader;
import java.util.*;
import com.fasterxml.jackson.core.JsonParseException;
import org.mockito.junit.jupiter.MockitoExtension;
import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class RuleCompiler_writeRules_Test {

    private static JsonFactory jsonFactory;

    private JsonParser jsonParser;

    private Map<String, List<Patterns>> ruleMap;

    @BeforeAll
    static void setupBeforeAll() {
        jsonFactory = new JsonFactory();
    }

    @BeforeEach
    void setupBeforeEach() throws Exception {
        ruleMap = new HashMap<>();
    }

    @AfterEach
    void teardownAfterEach() throws Exception {
        if (jsonParser != null) {
            jsonParser.close();
        }
    }

    @AfterAll
    static void teardownAfterAll() {
        jsonFactory = null;
    }

//     private void invokeWriteRules(Map<String, List<Patterns>> ruleMap, String name, JsonParser jsonParser, boolean withQuotes) throws Exception {
//         jsonParser = jsonFactory.createParser(new StringReader(json));
//         Method method = RuleCompiler.class.getDeclaredMethod("writeRules", Map.class, String.class, JsonParser.class, boolean.class);
//         method.setAccessible(true);
//         method.invoke(null, ruleMap, name, jsonParser, withQuotes);
//     }

//     @Test
//     void testWriteRules_ValidInput() throws Exception {
//         // Given
//         String name = "testRules";
//         String json = "[{\"key\":\"value\"}, {\"key2\":\"value2\"}]";
//         boolean withQuotes = true;
//         // When
//         invokeWriteRules(ruleMap, name, json, withQuotes);
//         // Then
//         assertFalse(ruleMap.isEmpty(), "Rule map should not be empty after writing rules.");
//         assertTrue(ruleMap.containsKey(name), "Rule map should contain the key for the written rules.");
//         assertNotNull(ruleMap.get(name), "The value for the key should not be null.");
//     }

//     @Test
//     void testWriteRules_EmptyArray() throws Exception {
//         // Given
//         String name = "testRule";
//         // Empty JSON array
//         String json = "[]";
//         // When & Then
//         Exception exception = assertThrows(JsonParseException.class, () -> {
//             invokeWriteRules(ruleMap, name, json, false);
//         });
//         assertTrue(exception.getMessage().contains("Empty arrays are not allowed"));
//     }

//     @Test
//     void testWriteRules_SingleObject() throws Exception {
//         // Given
//         String name = "testRule";
//         // JSON with a single object
//         String json = "[{\"key\":\"value\"}]";
//         // When
//         invokeWriteRules(ruleMap, name, json, false);
//         // Then
//         assertTrue(ruleMap.containsKey(name), "Rule map should contain the key for the written rules.");
//         assertNotNull(ruleMap.get(name), "The value for the key should not be null.");
//     }

//     @Test
//     void testWriteRules_StringValueWithQuotes() throws Exception {
//         // Given
//         String name = "testRule";
//         // JSON with a string value
//         String json = "[\"stringValue\"]";
//         // When
//         invokeWriteRules(ruleMap, name, json, true);
//         // Then
//         assertTrue(ruleMap.containsKey(name), "Rule map should contain the key for the written rules.");
//     }

//     @Test
//     void testWriteRules_NumericValue() throws Exception {
//         // Given
//         String name = "testRule";
//         // JSON with a numeric value
//         String json = "[123]";
//         // When
//         invokeWriteRules(ruleMap, name, json, false);
//         // Then
//         assertTrue(ruleMap.containsKey(name), "Rule map should contain the key for the written rules.");
//     }

//     @Test
//     void testWriteRules_BooleanValue() throws Exception {
//         // Given
//         String name = "testRule";
//         // JSON with a boolean value
//         String json = "[true]";
//         // When
//         invokeWriteRules(ruleMap, name, json, false);
//         // Then
//         assertTrue(ruleMap.containsKey(name), "Rule map should contain the key for the written rules.");
//     }

//     @Test
//     void testWriteRules_NullValue() throws Exception {
//         // Given
//         String name = "testRule";
//         // JSON with a null value
//         String json = "[null]";
//         // When
//         invokeWriteRules(ruleMap, name, json, false);
//         // Then
//         assertTrue(ruleMap.containsKey(name), "Rule map should contain the key for the written rules.");
//     }

//     @Test
//     void testWriteRules_InvalidValue() throws Exception {
//         // Given
//         String name = "testRule";
//         // JSON with an empty object
//         String json = "[{}]";
//         // When & Then
//         Exception exception = assertThrows(JsonParseException.class, () -> {
//             invokeWriteRules(ruleMap, name, json, false);
//         });
//         assertTrue(exception.getMessage().contains("Match value must be String, number, true, false, or null"));
//     }

//     @Test
//     void testWriteRules_MultipleStringValues() throws Exception {
//         // Given
//         String name = "testRule";
//         // JSON with multiple string values
//         String json = "[\"value1\", \"value2\"]";
//         // When
//         invokeWriteRules(ruleMap, name, json, false);
//         // Then
//         assertTrue(ruleMap.containsKey(name), "Rule map should contain the key for the written rules.");
//     }

//     @Test
//     void testWriteRules_MixedTypes() throws Exception {
//         // Given
//         String name = "testRule";
//         // JSON with mixed types
//         String json = "[\"stringValue\", 123, true]";
//         // When
//         invokeWriteRules(ruleMap, name, json, true);
//         // Then
//         assertTrue(ruleMap.containsKey(name), "Rule map should contain the key for the written rules.");
//     }

//     @Test
//     void testWriteRules_LargeNumberValue() throws Exception {
//         // Given
//         String name = "testRule";
//         // JSON with a large numeric value
//         String json = "[9999999999999999]";
//         // When
//         invokeWriteRules(ruleMap, name, json, false);
//         // Then
//         assertTrue(ruleMap.containsKey(name), "Rule map should contain the key for the written rules.");
//     }

//     @Test
//     void testWriteRules_SpecialCharacters() throws Exception {
//         // Given
//         String name = "testRule";
//         // JSON with special characters
//         String json = "[\"!@#$%^&*()\"]";
//         // When
//         invokeWriteRules(ruleMap, name, json, true);
//         // Then
//         assertTrue(ruleMap.containsKey(name), "Rule map should contain the key for the written rules.");
//     }

//     @Test
//     void testWriteRules_InvalidNumberFormat() throws Exception {
//         // Given
//         String name = "testRule";
//         // JSON with an invalid number format
//         String json = "[\"123abc\"]";
//         // When & Then
//         Exception exception = assertThrows(JsonParseException.class, () -> {
//             invokeWriteRules(ruleMap, name, json, false);
//         });
//         assertTrue(exception.getMessage().contains("Invalid number format"));
//     }

//     @Test
//     void testWriteRules_MalformedJson() throws Exception {
//         // Given
//         String name = "testRule";
//         // Malformed JSON
//         String json = "[{\"key\":\"value\",]";
//         // When & Then
//         Exception exception = assertThrows(JsonParseException.class, () -> {
//             invokeWriteRules(ruleMap, name, json, false);
//         });
//         assertTrue(exception.getMessage().contains("Unexpected end-of-input"));
//     }

//     @Test
//     void testWriteRules_ExtraComma() throws Exception {
//         // Given
//         String name = "testRule";
//         // JSON with an extra comma
//         String json = "[\"value1\",]";
//         // When & Then
//         Exception exception = assertThrows(JsonParseException.class, () -> {
//             invokeWriteRules(ruleMap, name, json, false);
//         });
//         assertTrue(exception.getMessage().contains("Unexpected character encountered while parsing value"));
//     }
}