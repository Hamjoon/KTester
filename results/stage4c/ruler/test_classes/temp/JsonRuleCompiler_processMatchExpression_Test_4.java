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
import java.io.StringReader;
import com.fasterxml.jackson.core.JsonParseException;
import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class JsonRuleCompiler_processMatchExpression_Test {

    private static JsonFactory jsonFactory;

    private JsonParser parser;

    @BeforeAll
    static void setupBeforeAll() {
        jsonFactory = new JsonFactory();
    }

    @BeforeEach
    void setupBeforeEach() throws IOException {
        // Example JSON for testing
        String json = "{\"exactMatch\":\"value\"}";
        parser = jsonFactory.createParser(new StringReader(json));
        // Move to START_OBJECT
        parser.nextToken();
    }

    @AfterEach
    void teardownAfterEach() throws IOException {
        if (parser != null) {
            parser.close();
        }
    }

    @AfterAll
    static void teardownAfterAll() {
        jsonFactory = null;
    }

    private Patterns invokeProcessMatchExpression(JsonParser parser) throws Exception {
        Method method = JsonRuleCompiler.class.getDeclaredMethod("processMatchExpression", JsonParser.class);
        method.setAccessible(true);
        try {
            return (Patterns) method.invoke(null, parser);
        } catch (InvocationTargetException e) {
            if (e.getCause() instanceof JsonParseException) {
                throw (JsonParseException) e.getCause();
            } else {
                throw e;
            }
        }
    }

    @Test
    void testProcessMatchExpressionWithExactMatch() throws Exception {
        // Given
        // Move to FIELD_NAME
        parser.nextToken();
        // Move to VALUE_STRING
        parser.nextToken();
        // When
        Patterns pattern = invokeProcessMatchExpression(parser);
        // Then
        assertNotNull(pattern);
        // Additional assertions to validate pattern
    }

    @Test
    void testProcessMatchExpressionWithInvalidExactMatch() throws IOException {
        // Given
        String json = "{\"exactMatch\":123}";
        try (JsonParser parser = jsonFactory.createParser(new StringReader(json))) {
            // Move to START_OBJECT
            parser.nextToken();
            // Move to FIELD_NAME
            parser.nextToken();
            // Move to VALUE_NUMBER_INT
            parser.nextToken();
            // When & Then
            assertThrows(JsonParseException.class, () -> invokeProcessMatchExpression(parser));
        }
    }

    @Test
    void testProcessMatchExpressionWithPrefixMatch() throws Exception {
        // Given
        String json = "{\"prefixMatch\":\"value\"}";
        try (JsonParser parser = jsonFactory.createParser(new StringReader(json))) {
            // Move to START_OBJECT
            parser.nextToken();
            // Move to FIELD_NAME
            parser.nextToken();
            // Move to VALUE_STRING
            parser.nextToken();
            // When
            Patterns pattern = invokeProcessMatchExpression(parser);
            // Then
            assertNotNull(pattern);
            // Additional assertions to validate pattern
        }
    }

    @Test
    void testProcessMatchExpressionWithInvalidPrefixMatch() throws IOException {
        // Given
        String json = "{\"prefixMatch\":123}";
        try (JsonParser parser = jsonFactory.createParser(new StringReader(json))) {
            // Move to START_OBJECT
            parser.nextToken();
            // Move to FIELD_NAME
            parser.nextToken();
            // Move to VALUE_NUMBER_INT
            parser.nextToken();
            // When & Then
            assertThrows(JsonParseException.class, () -> invokeProcessMatchExpression(parser));
        }
    }

    @Test
    void testProcessMatchExpressionWithSuffixMatch() throws Exception {
        // Given
        String json = "{\"suffixMatch\":\"value\"}";
        try (JsonParser parser = jsonFactory.createParser(new StringReader(json))) {
            // Move to START_OBJECT
            parser.nextToken();
            // Move to FIELD_NAME
            parser.nextToken();
            // Move to VALUE_STRING
            parser.nextToken();
            // When
            Patterns pattern = invokeProcessMatchExpression(parser);
            // Then
            assertNotNull(pattern);
            // Additional assertions to validate pattern
        }
    }

    @Test
    void testProcessMatchExpressionWithInvalidSuffixMatch() throws IOException {
        // Given
        String json = "{\"suffixMatch\":123}";
        try (JsonParser parser = jsonFactory.createParser(new StringReader(json))) {
            // Move to START_OBJECT
            parser.nextToken();
            // Move to FIELD_NAME
            parser.nextToken();
            // Move to VALUE_NUMBER_INT
            parser.nextToken();
            // When & Then
            assertThrows(JsonParseException.class, () -> invokeProcessMatchExpression(parser));
        }
    }

    @Test
    void testProcessMatchExpressionWithNumericMatch() throws Exception {
        // Given
        String json = "{\"numeric\":[1,2,3]}";
        try (JsonParser parser = jsonFactory.createParser(new StringReader(json))) {
            // Move to START_OBJECT
            parser.nextToken();
            // Move to FIELD_NAME
            parser.nextToken();
            // Move to START_ARRAY
            parser.nextToken();
            // When
            Patterns pattern = invokeProcessMatchExpression(parser);
            // Then
            assertNotNull(pattern);
            // Additional assertions to validate pattern
        }
    }

    @Test
    void testProcessMatchExpressionWithInvalidNumericMatch() throws IOException {
        // Given
        String json = "{\"numeric\":\"value\"}";
        try (JsonParser parser = jsonFactory.createParser(new StringReader(json))) {
            // Move to START_OBJECT
            parser.nextToken();
            // Move to FIELD_NAME
            parser.nextToken();
            // Move to VALUE_STRING
            parser.nextToken();
            // When & Then
            assertThrows(JsonParseException.class, () -> invokeProcessMatchExpression(parser));
        }
    }

    @Test
    void testProcessMatchExpressionWithAnythingButMatchObject() throws Exception {
        // Given
        String json = "{\"anythingButMatch\":{\"prefixMatch\":\"value\"}}";
        try (JsonParser parser = jsonFactory.createParser(new StringReader(json))) {
            // Move to START_OBJECT
            parser.nextToken();
            // Move to FIELD_NAME
            parser.nextToken();
            // Move to START_OBJECT
            parser.nextToken();
            // When
            Patterns pattern = invokeProcessMatchExpression(parser);
            // Then
            assertNotNull(pattern);
            // Additional assertions to validate pattern
        }
    }

    @Test
    void testProcessMatchExpressionWithAnythingButMatchArray() throws Exception {
        // Given
        String json = "{\"anythingButMatch\":[\"value1\", \"value2\"]}";
        try (JsonParser parser = jsonFactory.createParser(new StringReader(json))) {
            // Move to START_OBJECT
            parser.nextToken();
            // Move to FIELD_NAME
            parser.nextToken();
            // Move to START_ARRAY
            parser.nextToken();
            // When
            Patterns pattern = invokeProcessMatchExpression(parser);
            // Then
            assertNotNull(pattern);
            // Additional assertions to validate pattern
        }
    }

    @Test
    void testProcessMatchExpressionWithExistsMatch() throws Exception {
        // Given
        String json = "{\"existsMatch\":\"fieldName\"}";
        try (JsonParser parser = jsonFactory.createParser(new StringReader(json))) {
            // Move to START_OBJECT
            parser.nextToken();
            // Move to FIELD_NAME
            parser.nextToken();
            // Move to VALUE_STRING
            parser.nextToken();
            // When
            Patterns pattern = invokeProcessMatchExpression(parser);
            // Then
            assertNotNull(pattern);
            // Additional assertions to validate pattern
        }
    }

    @Test
    void testProcessMatchExpressionWithCidrMatch() throws Exception {
        // Given
        String json = "{\"cidr\":\"192.168.0.0/24\"}";
        try (JsonParser parser = jsonFactory.createParser(new StringReader(json))) {
            // Move to START_OBJECT
            parser.nextToken();
            // Move to FIELD_NAME
            parser.nextToken();
            // Move to VALUE_STRING
            parser.nextToken();
            // When
            Patterns pattern = invokeProcessMatchExpression(parser);
            // Then
            assertNotNull(pattern);
            // Additional assertions to validate pattern
        }
    }

    @Test
    void testProcessMatchExpressionWithInvalidCidrMatch() throws IOException {
        // Given
        String json = "{\"cidr\":123}";
        try (JsonParser parser = jsonFactory.createParser(new StringReader(json))) {
            // Move to START_OBJECT
            parser.nextToken();
            // Move to FIELD_NAME
            parser.nextToken();
            // Move to VALUE_NUMBER_INT
            parser.nextToken();
            // When & Then
            assertThrows(JsonParseException.class, () -> invokeProcessMatchExpression(parser));
        }
    }

    @Test
    void testProcessMatchExpressionWithEqualsIgnoreCaseMatch() throws Exception {
        // Given
        String json = "{\"equalsIgnoreCase\":\"value\"}";
        try (JsonParser parser = jsonFactory.createParser(new StringReader(json))) {
            // Move to START_OBJECT
            parser.nextToken();
            // Move to FIELD_NAME
            parser.nextToken();
            // Move to VALUE_STRING
            parser.nextToken();
            // When
            Patterns pattern = invokeProcessMatchExpression(parser);
            // Then
            assertNotNull(pattern);
            // Additional assertions to validate pattern
        }
    }

    @Test
    void testProcessMatchExpressionWithWildcardMatch() throws Exception {
        // Given
        String json = "{\"wildcard\":\"value*\"}";
        try (JsonParser parser = jsonFactory.createParser(new StringReader(json))) {
            // Move to START_OBJECT
            parser.nextToken();
            // Move to FIELD_NAME
            parser.nextToken();
            // Move to VALUE_STRING
            parser.nextToken();
            // When
            Patterns pattern = invokeProcessMatchExpression(parser);
            // Then
            assertNotNull(pattern);
            // Additional assertions to validate pattern
        }
    }

    @Test
    void testProcessMatchExpressionWithUnrecognizedMatchType() throws IOException {
        // Given
        String json = "{\"unknownMatch\":\"value\"}";
        try (JsonParser parser = jsonFactory.createParser(new StringReader(json))) {
            // Move to START_OBJECT
            parser.nextToken();
            // Move to FIELD_NAME
            parser.nextToken();
            // Move to VALUE_STRING
            parser.nextToken();
            // When & Then
            assertThrows(JsonParseException.class, () -> invokeProcessMatchExpression(parser));
        }
    }
}
