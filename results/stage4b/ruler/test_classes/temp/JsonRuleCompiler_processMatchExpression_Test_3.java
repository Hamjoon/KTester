package software.amazon.event.ruler;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonParseException;
import org.mockito.junit.jupiter.MockitoExtension;
import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class JsonRuleCompiler_processMatchExpression_Test {

    private JsonParser mockParser;

    private JsonRuleCompiler jsonRuleCompiler;

    @BeforeEach
    void setupBeforeEach() throws Exception {
        // Assuming JsonRuleCompiler has a public constructor or a static method to instantiate it
        // This line may need to be adjusted if the constructor is private
        // Assuming the constructor is accessible
        jsonRuleCompiler = new JsonRuleCompiler();
        mockParser = mock(JsonParser.class);
    }

    private Patterns invokeProcessMatchExpression(JsonParser parser) throws Exception {
        Method method = JsonRuleCompiler.class.getDeclaredMethod("processMatchExpression", JsonParser.class);
        method.setAccessible(true);
        return (Patterns) method.invoke(null, parser);
    }

    @Test
    void testProcessMatchExpression_WithExactMatch() throws IOException {
        // Given
        when(mockParser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.VALUE_STRING, JsonToken.END_OBJECT);
        when(mockParser.getCurrentName()).thenReturn(Constants.EXACT_MATCH);
        when(mockParser.getText()).thenReturn("testValue");
        // When
        Patterns result = invokeProcessMatchExpression(mockParser);
        // Then
        assertNotNull(result);
        assertEquals("testValue", result.getValue());
        assertEquals(Patterns.Type.EXACT_MATCH, result.getType());
    }

    @Test
    void testProcessMatchExpression_WithPrefixMatch() throws IOException {
        // Given
        when(mockParser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.VALUE_STRING, JsonToken.END_OBJECT);
        when(mockParser.getCurrentName()).thenReturn(Constants.PREFIX_MATCH);
        when(mockParser.getText()).thenReturn("testPrefix");
        // When
        Patterns result = invokeProcessMatchExpression(mockParser);
        // Then
        assertNotNull(result);
        assertEquals("testPrefix", result.getValue());
        assertEquals(Patterns.Type.PREFIX_MATCH, result.getType());
    }

    @Test
    void testProcessMatchExpression_WithInvalidMatchType() throws IOException {
        // Given
        when(mockParser.nextToken()).thenReturn(JsonToken.FIELD_NAME);
        when(mockParser.getCurrentName()).thenReturn("INVALID_TYPE");
        // When & Then
        Exception exception = assertThrows(JsonParseException.class, () -> {
            invokeProcessMatchExpression(mockParser);
        });
        assertEquals("Unrecognized match type INVALID_TYPE", exception.getMessage());
    }

    // Test cases for Exact Match
    @Test
    void testProcessMatchExpression_WithExactMatch_Valid() throws IOException {
        // Given
        when(mockParser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.VALUE_STRING, JsonToken.END_OBJECT);
        when(mockParser.getCurrentName()).thenReturn(Constants.EXACT_MATCH);
        when(mockParser.getText()).thenReturn("test");
        // When
        Patterns result = invokeProcessMatchExpression(mockParser);
        // Then
        assertNotNull(result);
        assertEquals("test", result.getValue());
        assertEquals(Patterns.Type.EXACT_MATCH, result.getType());
    }

    @Test
    void testProcessMatchExpression_WithExactMatch_Invalid() throws IOException {
        // Given
        when(mockParser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.VALUE_NUMBER_INT);
        when(mockParser.getCurrentName()).thenReturn(Constants.EXACT_MATCH);
        // When & Then
        Exception exception = assertThrows(JsonParseException.class, () -> {
            invokeProcessMatchExpression(mockParser);
        });
        assertEquals("exact match pattern must be a string", exception.getMessage());
    }

    // Test cases for Prefix Match
    @Test
    void testProcessMatchExpression_WithPrefixMatch_Valid() throws IOException {
        // Given
        when(mockParser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.VALUE_STRING, JsonToken.END_OBJECT);
        when(mockParser.getCurrentName()).thenReturn(Constants.PREFIX_MATCH);
        when(mockParser.getText()).thenReturn("testPrefix");
        // When
        Patterns result = invokeProcessMatchExpression(mockParser);
        // Then
        assertNotNull(result);
        assertEquals("testPrefix", result.getValue());
        assertEquals(Patterns.Type.PREFIX_MATCH, result.getType());
    }

    @Test
    void testProcessMatchExpression_WithPrefixMatch_Invalid() throws IOException {
        // Given
        when(mockParser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.VALUE_NUMBER_INT);
        when(mockParser.getCurrentName()).thenReturn(Constants.PREFIX_MATCH);
        // When & Then
        Exception exception = assertThrows(JsonParseException.class, () -> {
            invokeProcessMatchExpression(mockParser);
        });
        assertEquals("prefix match pattern must be a string", exception.getMessage());
    }

    // Test cases for Suffix Match
    @Test
    void testProcessMatchExpression_WithSuffixMatch_Valid() throws IOException {
        // Given
        when(mockParser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.VALUE_STRING, JsonToken.END_OBJECT);
        when(mockParser.getCurrentName()).thenReturn(Constants.SUFFIX_MATCH);
        when(mockParser.getText()).thenReturn("test");
        // When
        Patterns result = invokeProcessMatchExpression(mockParser);
        // Then
        assertNotNull(result);
        assertEquals("test", result.getValue());
        assertEquals(Patterns.Type.SUFFIX_MATCH, result.getType());
    }

    @Test
    void testProcessMatchExpression_WithSuffixMatch_Invalid() throws IOException {
        // Given
        when(mockParser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.VALUE_NUMBER_INT);
        when(mockParser.getCurrentName()).thenReturn(Constants.SUFFIX_MATCH);
        // When & Then
        Exception exception = assertThrows(JsonParseException.class, () -> {
            invokeProcessMatchExpression(mockParser);
        });
        assertEquals("suffix match pattern must be a string", exception.getMessage());
    }

    // Test cases for Numeric Match
    @Test
    void testProcessMatchExpression_WithNumericMatch_Valid() throws IOException {
        // Given
        when(mockParser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.START_ARRAY, JsonToken.END_OBJECT);
        when(mockParser.getCurrentName()).thenReturn(Constants.NUMERIC);
        // When
        Patterns result = invokeProcessMatchExpression(mockParser);
        // Then
        assertNotNull(result);
        // Additional assertions depending on the expected behavior of processNumericMatchExpression
    }

    @Test
    void testProcessMatchExpression_WithNumericMatch_Invalid() throws IOException {
        // Given
        when(mockParser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.VALUE_STRING);
        when(mockParser.getCurrentName()).thenReturn(Constants.NUMERIC);
        // When & Then
        Exception exception = assertThrows(JsonParseException.class, () -> {
            invokeProcessMatchExpression(mockParser);
        });
        assertEquals("Value of numeric must be an array.", exception.getMessage());
    }

    // Test cases for Anything But Match
    @Test
    void testProcessMatchExpression_WithAnythingButMatch_Valid() throws IOException {
        // Given
        when(mockParser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.START_OBJECT, JsonToken.FIELD_NAME, JsonToken.VALUE_STRING, JsonToken.END_OBJECT, JsonToken.END_OBJECT);
        when(mockParser.getCurrentName()).thenReturn(Constants.ANYTHING_BUT_MATCH);
        when(mockParser.getText()).thenReturn("test");
        // When
        Patterns result = invokeProcessMatchExpression(mockParser);
        // Then
        assertNotNull(result);
        // Additional assertions based on expected output
    }

    @Test
    void testProcessMatchExpression_WithAnythingButMatch_Invalid() throws IOException {
        // Given
        when(mockParser.nextToken()).thenReturn(JsonToken.FIELD_NAME);
        when(mockParser.getCurrentName()).thenReturn(Constants.ANYTHING_BUT_MATCH);
        // When & Then
        Exception exception = assertThrows(JsonParseException.class, () -> {
            invokeProcessMatchExpression(mockParser);
        });
        assertEquals("Unsupported anything-but pattern: INVALID_TYPE", exception.getMessage());
    }

    // Test cases for Exists Match
    @Test
    void testProcessMatchExpression_WithExistsMatch() throws IOException {
        // Given
        when(mockParser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.END_OBJECT);
        when(mockParser.getCurrentName()).thenReturn(Constants.EXISTS_MATCH);
        // When
        Patterns result = invokeProcessMatchExpression(mockParser);
        // Then
        assertNotNull(result);
        // Additional assertions based on expected output
    }

    // Test cases for CIDR Match
    @Test
    void testProcessMatchExpression_WithCidrMatch_Valid() throws IOException {
        // Given
        when(mockParser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.VALUE_STRING, JsonToken.END_OBJECT);
        when(mockParser.getCurrentName()).thenReturn(Constants.CIDR);
        when(mockParser.getText()).thenReturn("192.168.0.0/24");
        // When
        Patterns result = invokeProcessMatchExpression(mockParser);
        // Then
        assertNotNull(result);
        // Additional assertions based on expected output
    }

    @Test
    void testProcessMatchExpression_WithCidrMatch_Invalid() throws IOException {
        // Given
        when(mockParser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.VALUE_STRING);
        when(mockParser.getCurrentName()).thenReturn(Constants.CIDR);
        when(mockParser.getText()).thenReturn("invalid_cidr");
        // When & Then
        Exception exception = assertThrows(JsonParseException.class, () -> {
            invokeProcessMatchExpression(mockParser);
        });
        assertEquals("CIDR match pattern must be a valid string", exception.getMessage());
    }

    // Test cases for Equals Ignore Case Match
    @Test
    void testProcessMatchExpression_WithEqualsIgnoreCaseMatch_Valid() throws IOException {
        // Given
        when(mockParser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.VALUE_STRING, JsonToken.END_OBJECT);
        when(mockParser.getCurrentName()).thenReturn(Constants.EQUALS_IGNORE_CASE);
        when(mockParser.getText()).thenReturn("test");
        // When
        Patterns result = invokeProcessMatchExpression(mockParser);
        // Then
        assertNotNull(result);
        assertEquals("test", result.getValue());
        assertEquals(Patterns.Type.EQUALS_IGNORE_CASE, result.getType());
    }

    @Test
    void testProcessMatchExpression_WithEqualsIgnoreCaseMatch_Invalid() throws IOException {
        // Given
        when(mockParser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.VALUE_NUMBER_INT);
        when(mockParser.getCurrentName()).thenReturn(Constants.EQUALS_IGNORE_CASE);
        // When & Then
        Exception exception = assertThrows(JsonParseException.class, () -> {
            invokeProcessMatchExpression(mockParser);
        });
        assertEquals("equals-ignore-case match pattern must be a string", exception.getMessage());
    }

    // Test cases for Wildcard Match
    @Test
    void testProcessMatchExpression_WithWildcardMatch_Valid() throws IOException {
        // Given
        when(mockParser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.VALUE_STRING, JsonToken.END_OBJECT);
        when(mockParser.getCurrentName()).thenReturn(Constants.WILDCARD);
        when(mockParser.getText()).thenReturn("test");
        // When
        Patterns result = invokeProcessMatchExpression(mockParser);
        // Then
        assertNotNull(result);
        assertEquals("test", result.getValue());
        assertEquals(Patterns.Type.WILDCARD, result.getType());
    }

    @Test
    void testProcessMatchExpression_WithWildcardMatch_Invalid() throws IOException {
        // Given
        when(mockParser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.VALUE_NUMBER_INT);
        when(mockParser.getCurrentName()).thenReturn(Constants.WILDCARD);
        // When & Then
        Exception exception = assertThrows(JsonParseException.class, () -> {
            invokeProcessMatchExpression(mockParser);
        });
        assertEquals("wildcard match pattern must be a string", exception.getMessage());
    }

    // Test case for Unrecognized Match Type
    @Test
    void testProcessMatchExpression_WithUnrecognizedMatchType() throws IOException {
        // Given
        when(mockParser.nextToken()).thenReturn(JsonToken.FIELD_NAME);
        when(mockParser.getCurrentName()).thenReturn("INVALID_TYPE");
        // When & Then
        Exception exception = assertThrows(JsonParseException.class, () -> {
            invokeProcessMatchExpression(mockParser);
        });
        assertEquals("Unrecognized match type INVALID_TYPE", exception.getMessage());
    }
}
