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
        return (Patterns) method.invoke(null, parser);
    }

    @Test
    void testProcessMatchExpressionWithExactMatch() throws IOException {
        // Given
        // Move to FIELD_NAME
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
        parser = jsonFactory.createParser(new StringReader(json));
        // Move to START_OBJECT
        parser.nextToken();
        // Move to FIELD_NAME
        parser.nextToken();
        // When & Then
        assertThrows(JsonParseException.class, () -> invokeProcessMatchExpression(parser));
    }

    @Test
    void testProcessMatchExpressionWithPrefixMatch() throws IOException {
        // Given
        String json = "{\"prefixMatch\":\"value\"}";
        parser = jsonFactory.createParser(new StringReader(json));
        // Move to START_OBJECT
        parser.nextToken();
        // Move to FIELD_NAME
        parser.nextToken();
        // When
        Patterns pattern = invokeProcessMatchExpression(parser);
        // Then
        assertNotNull(pattern);
        // Additional assertions to validate pattern
    }

    @Test
    void testProcessMatchExpressionWithInvalidPrefixMatch() throws IOException {
        // Given
        String json = "{\"prefixMatch\":123}";
        parser = jsonFactory.createParser(new StringReader(json));
        // Move to START_OBJECT
        parser.nextToken();
        // Move to FIELD_NAME
        parser.nextToken();
        // When & Then
        assertThrows(JsonParseException.class, () -> invokeProcessMatchExpression(parser));
    }

    @Test
    void testProcessMatchExpressionWithSuffixMatch() throws IOException {
        // Given
        String json = "{\"suffixMatch\":\"value\"}";
        parser = jsonFactory.createParser(new StringReader(json));
        // Move to START_OBJECT
        parser.nextToken();
        // Move to FIELD_NAME
        parser.nextToken();
        // When
        Patterns pattern = invokeProcessMatchExpression(parser);
        // Then
        assertNotNull(pattern);
        // Additional assertions to validate pattern
    }

    @Test
    void testProcessMatchExpressionWithInvalidSuffixMatch() throws IOException {
        // Given
        String json = "{\"suffixMatch\":123}";
        parser = jsonFactory.createParser(new StringReader(json));
        // Move to START_OBJECT
        parser.nextToken();
        // Move to FIELD_NAME
        parser.nextToken();
        // When & Then
        assertThrows(JsonParseException.class, () -> invokeProcessMatchExpression(parser));
    }

    @Test
    void testProcessMatchExpressionWithNumericMatch() throws IOException {
        // Given
        String json = "{\"numeric\":[1,2,3]}";
        parser = jsonFactory.createParser(new StringReader(json));
        // Move to START_OBJECT
        parser.nextToken();
        // Move to FIELD_NAME
        parser.nextToken();
        // When
        Patterns pattern = invokeProcessMatchExpression(parser);
        // Then
        assertNotNull(pattern);
        // Additional assertions to validate pattern
    }

    @Test
    void testProcessMatchExpressionWithInvalidNumericMatch() throws IOException {
        // Given
        String json = "{\"numeric\":\"value\"}";
        parser = jsonFactory.createParser(new StringReader(json));
        // Move to START_OBJECT
        parser.nextToken();
        // Move to FIELD_NAME
        parser.nextToken();
        // When & Then
        assertThrows(JsonParseException.class, () -> invokeProcessMatchExpression(parser));
    }

    @Test
    void testProcessMatchExpressionWithAnythingButMatchObject() throws IOException {
        // Given
        String json = "{\"anythingButMatch\":{\"prefixMatch\":\"value\"}}";
        parser = jsonFactory.createParser(new StringReader(json));
        // Move to START_OBJECT
        parser.nextToken();
        // Move to FIELD_NAME
        parser.nextToken();
        // When
        Patterns pattern = invokeProcessMatchExpression(parser);
        // Then
        assertNotNull(pattern);
        // Additional assertions to validate pattern
    }

    @Test
    void testProcessMatchExpressionWithAnythingButMatchArray() throws IOException {
        // Given
        String json = "{\"anythingButMatch\":[\"value1\", \"value2\"]}";
        parser = jsonFactory.createParser(new StringReader(json));
        // Move to START_OBJECT
        parser.nextToken();
        // Move to FIELD_NAME
        parser.nextToken();
        // When
        Patterns pattern = invokeProcessMatchExpression(parser);
        // Then
        assertNotNull(pattern);
        // Additional assertions to validate pattern
    }

    @Test
    void testProcessMatchExpressionWithExistsMatch() throws IOException {
        // Given
        String json = "{\"existsMatch\":\"fieldName\"}";
        parser = jsonFactory.createParser(new StringReader(json));
        // Move to START_OBJECT
        parser.nextToken();
        // Move to FIELD_NAME
        parser.nextToken();
        // When
        Patterns pattern = invokeProcessMatchExpression(parser);
        // Then
        assertNotNull(pattern);
        // Additional assertions to validate pattern
    }

    @Test
    void testProcessMatchExpressionWithCidrMatch() throws IOException {
        // Given
        String json = "{\"cidr\":\"192.168.0.0/24\"}";
        parser = jsonFactory.createParser(new StringReader(json));
        // Move to START_OBJECT
        parser.nextToken();
        // Move to FIELD_NAME
        parser.nextToken();
        // When
        Patterns pattern = invokeProcessMatchExpression(parser);
        // Then
        assertNotNull(pattern);
        // Additional assertions to validate pattern
    }

    @Test
    void testProcessMatchExpressionWithInvalidCidrMatch() throws IOException {
        // Given
        String json = "{\"cidr\":123}";
        parser = jsonFactory.createParser(new StringReader(json));
        // Move to START_OBJECT
        parser.nextToken();
        // Move to FIELD_NAME
        parser.nextToken();
        // When & Then
        assertThrows(JsonParseException.class, () -> invokeProcessMatchExpression(parser));
    }

    @Test
    void testProcessMatchExpressionWithEqualsIgnoreCaseMatch() throws IOException {
        // Given
        String json = "{\"equalsIgnoreCase\":\"value\"}";
        parser = jsonFactory.createParser(new StringReader(json));
        // Move to START_OBJECT
        parser.nextToken();
        // Move to FIELD_NAME
        parser.nextToken();
        // When
        Patterns pattern = invokeProcessMatchExpression(parser);
        // Then
        assertNotNull(pattern);
        // Additional assertions to validate pattern
    }

    @Test
    void testProcessMatchExpressionWithWildcardMatch() throws IOException {
        // Given
        String json = "{\"wildcard\":\"value*\"}";
        parser = jsonFactory.createParser(new StringReader(json));
        // Move to START_OBJECT
        parser.nextToken();
        // Move to FIELD_NAME
        parser.nextToken();
        // When
        Patterns pattern = invokeProcessMatchExpression(parser);
        // Then
        assertNotNull(pattern);
        // Additional assertions to validate pattern
    }

    @Test
    void testProcessMatchExpressionWithUnrecognizedMatchType() throws IOException {
        // Given
        String json = "{\"unknownMatch\":\"value\"}";
        parser = jsonFactory.createParser(new StringReader(json));
        // Move to START_OBJECT
        parser.nextToken();
        // Move to FIELD_NAME
        parser.nextToken();
        // When & Then
        assertThrows(JsonParseException.class, () -> invokeProcessMatchExpression(parser));
    }
}
