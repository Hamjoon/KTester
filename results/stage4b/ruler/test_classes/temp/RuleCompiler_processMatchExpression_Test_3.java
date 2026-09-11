package software.amazon.event.ruler;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import org.mockito.junit.jupiter.MockitoExtension;
import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class RuleCompiler_processMatchExpression_Test {

    @Mock
    private JsonParser parser;

    @BeforeEach
    void setupBeforeEach() {
        // Prepare the mocked JsonParser before each test
        // Reset the parser mock to avoid state carryover between tests
        reset(parser);
    }

    private Patterns invokeProcessMatchExpression(JsonParser parser) throws Exception {
        Method method = RuleCompiler.class.getDeclaredMethod("processMatchExpression", JsonParser.class);
        // Make the private method accessible
        method.setAccessible(true);
        // Invoke the method statically
        return (Patterns) method.invoke(null, parser);
    }

    @Test
    void testProcessMatchExpressionExactMatch() throws Exception {
        // Test for exact match with a valid string value.
        when(parser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.VALUE_STRING, JsonToken.END_OBJECT);
        when(parser.getCurrentName()).thenReturn(Constants.EXACT_MATCH);
        when(parser.getText()).thenReturn("test");
        Patterns result = invokeProcessMatchExpression(parser);
        assertNotNull(result);
        // Assuming toString() returns the matched value
        assertEquals("test", result.toString().replaceAll(" \\(T:EXACT\\)", ""));
    }

    @Test
    void testProcessMatchExpressionPrefixMatch() throws Exception {
        // Test for prefix match with a valid string value.
        when(parser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.VALUE_STRING, JsonToken.END_OBJECT);
        when(parser.getCurrentName()).thenReturn(Constants.PREFIX_MATCH);
        when(parser.getText()).thenReturn("test");
        Patterns result = invokeProcessMatchExpression(parser);
        assertNotNull(result);
        // Assuming toString() returns the matched value
        assertEquals("test", result.toString().replaceAll(" \\(T:PREFIX\\)", ""));
    }

    @Test
    void testProcessMatchExpressionInvalidMatchType() throws IOException {
        // Test for unrecognized match type.
        when(parser.nextToken()).thenReturn(JsonToken.FIELD_NAME);
        when(parser.getCurrentName()).thenReturn("UNKNOWN_MATCH_TYPE");
        Exception exception = assertThrows(IOException.class, () -> {
            invokeProcessMatchExpression(parser);
        });
        assertEquals("Unrecognized match type UNKNOWN_MATCH_TYPE", exception.getMessage());
    }

    @Test
    void testProcessMatchExpressionExactMatchInvalidValue() throws IOException {
        // Test for exact match with an invalid numeric value.
        when(parser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.VALUE_NUMBER_INT);
        when(parser.getCurrentName()).thenReturn(Constants.EXACT_MATCH);
        Exception exception = assertThrows(IOException.class, () -> {
            invokeProcessMatchExpression(parser);
        });
        assertEquals("exact match pattern must be a string", exception.getMessage());
    }

    @Test
    void testProcessMatchExpressionPrefixMatchInvalidValue() throws IOException {
        // Test for prefix match with an invalid numeric value.
        when(parser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.VALUE_NUMBER_INT);
        when(parser.getCurrentName()).thenReturn(Constants.PREFIX_MATCH);
        Exception exception = assertThrows(IOException.class, () -> {
            invokeProcessMatchExpression(parser);
        });
        assertEquals("prefix match pattern must be a string", exception.getMessage());
    }

    @Test
    void testProcessMatchExpressionSuffixMatch() throws Exception {
        // Test for suffix match with a valid string value.
        when(parser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.VALUE_STRING, JsonToken.END_OBJECT);
        when(parser.getCurrentName()).thenReturn(Constants.SUFFIX_MATCH);
        when(parser.getText()).thenReturn("test");
        Patterns result = invokeProcessMatchExpression(parser);
        assertNotNull(result);
        // Assuming toString() returns the matched value
        assertEquals("test", result.toString().replaceAll(" \\(T:SUFFIX\\)", ""));
    }

    @Test
    void testProcessMatchExpressionSuffixMatchInvalidValue() throws IOException {
        // Test for suffix match with an invalid numeric value.
        when(parser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.VALUE_NUMBER_INT);
        when(parser.getCurrentName()).thenReturn(Constants.SUFFIX_MATCH);
        Exception exception = assertThrows(IOException.class, () -> {
            invokeProcessMatchExpression(parser);
        });
        assertEquals("suffix match pattern must be a string", exception.getMessage());
    }

    @Test
    void testProcessMatchExpressionNumericMatch() throws Exception {
        // Test for numeric match with a valid array of numbers.
        when(parser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.START_ARRAY, JsonToken.END_OBJECT);
        when(parser.getCurrentName()).thenReturn(Constants.NUMERIC);
        // Mock processing numeric match expression here
        Patterns result = invokeProcessMatchExpression(parser);
        assertNotNull(result);
        // Add assertions based on the expected output of numeric match
    }

    @Test
    void testProcessMatchExpressionNumericMatchInvalidValue() throws IOException {
        // Test for numeric match with an invalid non-array value.
        when(parser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.VALUE_STRING);
        when(parser.getCurrentName()).thenReturn(Constants.NUMERIC);
        Exception exception = assertThrows(IOException.class, () -> {
            invokeProcessMatchExpression(parser);
        });
        assertEquals("Value of NUMERIC must be an array.", exception.getMessage());
    }
}
