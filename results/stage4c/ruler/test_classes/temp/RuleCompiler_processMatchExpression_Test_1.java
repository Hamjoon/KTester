package software.amazon.event.ruler;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.fasterxml.jackson.core.JsonParseException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class RuleCompiler_processMatchExpression_Test {

    private static JsonFactory jsonFactory;

    private JsonParser parser;

    @BeforeAll
    static void setupBeforeAll() {
        jsonFactory = new JsonFactory();
    }

    @BeforeEach
    void setupBeforeEach() throws IOException {
        parser = mock(JsonParser.class);
    }

    private Patterns invokeProcessMatchExpression(JsonParser parser) throws Exception {
        Method method = RuleCompiler.class.getDeclaredMethod("processMatchExpression", JsonParser.class);
        method.setAccessible(true);
        return (Patterns) method.invoke(null, parser);
    }

    @Test
    void testProcessMatchExpressionExactMatch() throws IOException {
        // Given
        when(parser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.VALUE_STRING, JsonToken.END_OBJECT);
        when(parser.getCurrentName()).thenReturn(Constants.EXACT_MATCH);
        when(parser.getText()).thenReturn("test");
        // When
        Patterns result = invokeProcessMatchExpression(parser);
        // Then
        assertNotNull(result);
        // Add more assertions based on expected behavior
    }

    @Test
    void testProcessMatchExpressionPrefixMatch() throws IOException {
        // Given
        when(parser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.VALUE_STRING, JsonToken.END_OBJECT);
        when(parser.getCurrentName()).thenReturn(Constants.PREFIX_MATCH);
        when(parser.getText()).thenReturn("test");
        // When
        Patterns result = invokeProcessMatchExpression(parser);
        // Then
        assertNotNull(result);
        // Add more assertions based on expected behavior
    }

    @Test
    void testProcessMatchExpressionSuffixMatch() throws IOException {
        // Given
        when(parser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.VALUE_STRING, JsonToken.END_OBJECT);
        when(parser.getCurrentName()).thenReturn(Constants.SUFFIX_MATCH);
        when(parser.getText()).thenReturn("test");
        // When
        Patterns result = invokeProcessMatchExpression(parser);
        // Then
        assertNotNull(result);
        // Add more assertions based on expected behavior
    }

    @Test
    void testProcessMatchExpressionNumericMatch() throws IOException {
        // Given
        when(parser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.START_ARRAY, JsonToken.END_ARRAY, JsonToken.END_OBJECT);
        when(parser.getCurrentName()).thenReturn(Constants.NUMERIC);
        // When
        Patterns result = invokeProcessMatchExpression(parser);
        // Then
        assertNotNull(result);
        // Add more assertions based on expected behavior
    }

    @Test
    void testProcessMatchExpressionAnythingButMatch() throws IOException {
        // Given
        when(parser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.VALUE_STRING, JsonToken.END_OBJECT);
        when(parser.getCurrentName()).thenReturn(Constants.ANYTHING_BUT_MATCH);
        when(parser.getText()).thenReturn("test");
        // When
        Patterns result = invokeProcessMatchExpression(parser);
        // Then
        assertNotNull(result);
        // Add more assertions based on expected behavior
    }

    @Test
    void testProcessMatchExpressionExistsMatch() throws IOException {
        // Given
        when(parser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.END_OBJECT);
        when(parser.getCurrentName()).thenReturn(Constants.EXISTS_MATCH);
        // When
        Patterns result = invokeProcessMatchExpression(parser);
        // Then
        assertNotNull(result);
        // Add more assertions based on expected behavior
    }

    @Test
    void testProcessMatchExpressionCidrMatch() throws IOException {
        // Given
        when(parser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.VALUE_STRING, JsonToken.END_OBJECT);
        when(parser.getCurrentName()).thenReturn(Constants.CIDR);
        when(parser.getText()).thenReturn("192.168.1.0/24");
        // When
        Patterns result = invokeProcessMatchExpression(parser);
        // Then
        assertNotNull(result);
        // Add more assertions based on expected behavior
    }

    @Test
    void testProcessMatchExpressionEqualsIgnoreCaseMatch() throws IOException {
        // Given
        when(parser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.VALUE_STRING, JsonToken.END_OBJECT);
        when(parser.getCurrentName()).thenReturn(Constants.EQUALS_IGNORE_CASE);
        when(parser.getText()).thenReturn("test");
        // When
        Patterns result = invokeProcessMatchExpression(parser);
        // Then
        assertNotNull(result);
        // Add more assertions based on expected behavior
    }

    @Test
    void testProcessMatchExpressionWildcardMatch() throws IOException {
        // Given
        when(parser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.VALUE_STRING, JsonToken.END_OBJECT);
        when(parser.getCurrentName()).thenReturn(Constants.WILDCARD);
        when(parser.getText()).thenReturn("test*");
        // When
        Patterns result = invokeProcessMatchExpression(parser);
        // Then
        assertNotNull(result);
        // Add more assertions based on expected behavior
    }

    @Test
    void testProcessMatchExpressionUnrecognizedMatchType() {
        // Given
        when(parser.nextToken()).thenReturn(JsonToken.FIELD_NAME);
        when(parser.getCurrentName()).thenReturn("UNRECOGNIZED");
        // When & Then
        JsonParseException exception = assertThrows(JsonParseException.class, () -> {
            invokeProcessMatchExpression(parser);
        });
        assertEquals("Unrecognized match type UNRECOGNIZED", exception.getMessage());
    }

    @Test
    void testProcessMatchExpressionMissingFieldNameToken() {
        // Given
        when(parser.nextToken()).thenReturn(JsonToken.VALUE_STRING);
        // When & Then
        JsonParseException exception = assertThrows(JsonParseException.class, () -> {
            invokeProcessMatchExpression(parser);
        });
        assertEquals("Match expression name not found", exception.getMessage());
    }
}
