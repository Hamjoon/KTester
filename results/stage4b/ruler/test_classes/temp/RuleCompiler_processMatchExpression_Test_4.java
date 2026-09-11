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
        reset(parser);
    }

    private Patterns invokeProcessMatchExpression(JsonParser parser) throws Exception {
        Method method = RuleCompiler.class.getDeclaredMethod("processMatchExpression", JsonParser.class);
        method.setAccessible(true);
        return (Patterns) method.invoke(null, parser);
    }

    @Test
    void testProcessMatchExpressionExactMatch() throws Exception {
        when(parser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.VALUE_STRING, JsonToken.END_OBJECT);
        when(parser.getCurrentName()).thenReturn(Constants.EXACT_MATCH);
        when(parser.getText()).thenReturn("test");
        Patterns result = invokeProcessMatchExpression(parser);
        assertNotNull(result);
        assertEquals("test", result.toString().replaceAll(" \\(T:EXACT\\)", ""));
    }

    @Test
    void testProcessMatchExpressionPrefixMatch() throws Exception {
        when(parser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.VALUE_STRING, JsonToken.END_OBJECT);
        when(parser.getCurrentName()).thenReturn(Constants.PREFIX_MATCH);
        when(parser.getText()).thenReturn("test");
        Patterns result = invokeProcessMatchExpression(parser);
        assertNotNull(result);
        assertEquals("test", result.toString().replaceAll(" \\(T:PREFIX\\)", ""));
    }

    @Test
    void testProcessMatchExpressionInvalidMatchType() throws IOException {
        when(parser.nextToken()).thenReturn(JsonToken.FIELD_NAME);
        when(parser.getCurrentName()).thenReturn("UNKNOWN_MATCH_TYPE");
        Exception exception = assertThrows(IOException.class, () -> {
            invokeProcessMatchExpression(parser);
        });
        assertEquals("Unrecognized match type UNKNOWN_MATCH_TYPE", exception.getMessage());
    }

    @Test
    void testProcessMatchExpressionExactMatchInvalidValue() throws IOException {
        when(parser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.VALUE_NUMBER_INT);
        when(parser.getCurrentName()).thenReturn(Constants.EXACT_MATCH);
        Exception exception = assertThrows(IOException.class, () -> {
            invokeProcessMatchExpression(parser);
        });
        assertEquals("exact match pattern must be a string", exception.getMessage());
    }

    @Test
    void testProcessMatchExpressionPrefixMatchInvalidValue() throws IOException {
        when(parser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.VALUE_NUMBER_INT);
        when(parser.getCurrentName()).thenReturn(Constants.PREFIX_MATCH);
        Exception exception = assertThrows(IOException.class, () -> {
            invokeProcessMatchExpression(parser);
        });
        assertEquals("prefix match pattern must be a string", exception.getMessage());
    }

    @Test
    void testProcessMatchExpressionSuffixMatch() throws Exception {
        when(parser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.VALUE_STRING, JsonToken.END_OBJECT);
        when(parser.getCurrentName()).thenReturn(Constants.SUFFIX_MATCH);
        when(parser.getText()).thenReturn("test");
        Patterns result = invokeProcessMatchExpression(parser);
        assertNotNull(result);
        assertEquals("test", result.toString().replaceAll(" \\(T:SUFFIX\\)", ""));
    }

    @Test
    void testProcessMatchExpressionSuffixMatchInvalidValue() throws IOException {
        when(parser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.VALUE_NUMBER_INT);
        when(parser.getCurrentName()).thenReturn(Constants.SUFFIX_MATCH);
        Exception exception = assertThrows(IOException.class, () -> {
            invokeProcessMatchExpression(parser);
        });
        assertEquals("suffix match pattern must be a string", exception.getMessage());
    }

    @Test
    void testProcessMatchExpressionNumericMatch() throws Exception {
        when(parser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.START_ARRAY, JsonToken.END_OBJECT);
        when(parser.getCurrentName()).thenReturn(Constants.NUMERIC);
        Patterns result = invokeProcessMatchExpression(parser);
        assertNotNull(result);
        // Add assertions based on the expected output of numeric match
    }

    @Test
    void testProcessMatchExpressionNumericMatchInvalidValue() throws IOException {
        when(parser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.VALUE_STRING);
        when(parser.getCurrentName()).thenReturn(Constants.NUMERIC);
        Exception exception = assertThrows(IOException.class, () -> {
            invokeProcessMatchExpression(parser);
        });
        assertEquals("Value of NUMERIC must be an array.", exception.getMessage());
    }
}
