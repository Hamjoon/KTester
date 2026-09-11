package com.google.gson.stream;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class JsonReader_doPeek_Test {

    private JsonReader jsonReader;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the JsonReader with a valid JSON input for testing
        // Example JSON input
        String jsonInput = "{\"key\": \"value\"}";
        jsonReader = new JsonReader(new StringReader(jsonInput));
    }

    @AfterEach
    void teardownAfterEach() throws IOException {
        // Close the JsonReader after each test
        jsonReader.close();
    }

    // Helper method to invoke the private doPeek method using reflection
    private int invokeDoPeek() throws Exception {
        Method method = JsonReader.class.getDeclaredMethod("doPeek");
        method.setAccessible(true);
        return (int) method.invoke(jsonReader);
    }

    // Helper method to invoke the private doPeek method and assert the expected value
    private void invokeDoPeekAndAssert(int expectedValue, String description) throws Exception {
        assertEquals(expectedValue, invokeDoPeek(), description);
    }

    @Test
    void testDoPeekWithBeginObject() throws IOException {
        // Begin object
        jsonReader = new JsonReader(new StringReader("{\"key\": \"value\"}"));
        // This will invoke doPeek indirectly
        jsonReader.beginObject();
        // Expect PEEKED_BEGIN_OBJECT
        assertEquals(1, invokeDoPeek());
    }

    @Test
    void testDoPeekWithBeginArray() throws IOException {
        String jsonArrayInput = "[1, 2, 3]";
        jsonReader = new JsonReader(new StringReader(jsonArrayInput));
        // This will invoke doPeek indirectly
        jsonReader.beginArray();
        assertTrue(jsonReader.hasNext());
        assertEquals(1, jsonReader.nextInt());
        assertEquals(2, jsonReader.nextInt());
        assertEquals(3, jsonReader.nextInt());
        jsonReader.endArray();
    }

    @Test
    void testDoPeekWithEmptyObject() throws IOException {
        // Empty object
        jsonReader = new JsonReader(new StringReader("{}"));
        // This will invoke doPeek indirectly
        jsonReader.beginObject();
        // Expect PEEKED_END_OBJECT
        assertEquals(2, invokeDoPeek());
        jsonReader.endObject();
    }

    @Test
    void testDoPeekWithEmptyArray() throws IOException {
        // Empty array
        jsonReader = new JsonReader(new StringReader("[]"));
        // This will invoke doPeek indirectly
        jsonReader.beginArray();
        // Expect PEEKED_END_ARRAY
        assertEquals(2, invokeDoPeek());
        jsonReader.endArray();
    }

    /**
     * Test the doPeek method when the JSON document is empty.
     * Expecting PEEKED_EOF.
     */
    @Test
    void testDoPeekWithEmptyDocument() throws Exception {
        // Empty JSON document
        jsonReader = new JsonReader(new StringReader(""));
        invokeDoPeekAndAssert(17, "PEEKED_EOF");
    }

    /**
     * Test the doPeek method when the next character is a single-quoted name.
     * Expecting PEEKED_SINGLE_QUOTED_NAME.
     */
    @Test
    void testDoPeekWithSingleQuotedName() throws Exception {
        // Single-quoted name
        jsonReader = new JsonReader(new StringReader("{'key': 'value'}"));
        // This will invoke doPeek indirectly
        jsonReader.beginObject();
        // Expect PEEKED_SINGLE_QUOTED_NAME
        assertEquals(12, invokeDoPeek());
        jsonReader.endObject();
    }

    /**
     * Test the doPeek method when the next character is a double-quoted name.
     * Expecting PEEKED_DOUBLE_QUOTED_NAME.
     */
    @Test
    void testDoPeekWithDoubleQuotedName() throws Exception {
        // Double-quoted name
        jsonReader = new JsonReader(new StringReader("{\"key\": \"value\"}"));
        // This will invoke doPeek indirectly
        jsonReader.beginObject();
        // Expect PEEKED_DOUBLE_QUOTED_NAME
        assertEquals(13, invokeDoPeek());
        jsonReader.endObject();
    }

    /**
     * Test the doPeek method when the next character is an unquoted name.
     * Expecting PEEKED_UNQUOTED_NAME.
     */
    @Test
    void testDoPeekWithUnquotedName() throws Exception {
        // Unquoted name
        jsonReader = new JsonReader(new StringReader("{key: \"value\"}"));
        // This will invoke doPeek indirectly
        jsonReader.beginObject();
        // Expect PEEKED_UNQUOTED_NAME
        assertEquals(14, invokeDoPeek());
        jsonReader.endObject();
    }

    /**
     * Test the doPeek method when the next character is a single quote in lenient mode.
     * Expecting PEEKED_SINGLE_QUOTED.
     */
    @Test
    void testDoPeekWithSingleQuoteInLenientMode() throws Exception {
        jsonReader.setLenient(true);
        // Single quote in lenient mode
        jsonReader = new JsonReader(new StringReader("[ 'value' ]"));
        // This will invoke doPeek indirectly
        jsonReader.beginArray();
        // Expect PEEKED_SINGLE_QUOTED
        assertEquals(8, invokeDoPeek());
        jsonReader.endArray();
    }

    /**
     * Test the doPeek method when the next character is a double quote.
     * Expecting PEEKED_DOUBLE_QUOTED.
     */
    @Test
    void testDoPeekWithDoubleQuote() throws Exception {
        // Double quote
        jsonReader = new JsonReader(new StringReader("[ \"value\" ]"));
        // This will invoke doPeek indirectly
        jsonReader.beginArray();
        // Expect PEEKED_DOUBLE_QUOTED
        assertEquals(9, invokeDoPeek());
        jsonReader.endArray();
    }

    /**
     * Test the doPeek method when there is an unexpected character in the array.
     * Expecting an IOException for 'Unterminated array'.
     */
    @Test
    void testDoPeekWithUnexpectedCharacterInArray() {
        // Unterminated array
        jsonReader = new JsonReader(new StringReader("[1, 2, 3"));
        jsonReader.beginArray();
        assertThrows(IOException.class, () -> invokeDoPeek());
    }

    /**
     * Test the doPeek method when there is an unexpected character in the object.
     * Expecting an IOException for 'Unterminated object'.
     */
    @Test
    void testDoPeekWithUnexpectedCharacterInObject() {
        // Unterminated object
        jsonReader = new JsonReader(new StringReader("{\"key\": \"value\""));
        jsonReader.beginObject();
        assertThrows(IOException.class, () -> invokeDoPeek());
    }

    /**
     * Test the doPeek method when the JsonReader is closed.
     * Expecting an IllegalStateException.
     */
    @Test
    void testDoPeekWhenJsonReaderIsClosed() {
        jsonReader.close();
        assertThrows(IllegalStateException.class, () -> invokeDoPeek());
    }

    /**
     * Test the doPeek method when a name is expected but not found.
     * Expecting an IOException for 'Expected name'.
     */
    @Test
    void testDoPeekWhenExpectedNameNotFound() {
        // Expected name not found
        jsonReader = new JsonReader(new StringReader("{: \"value\"}"));
        jsonReader.beginObject();
        assertThrows(IOException.class, () -> invokeDoPeek());
    }

    /**
     * Test the doPeek method when a value is expected but not found.
     * Expecting an IOException for 'Expected value'.
     */
    @Test
    void testDoPeekWhenExpectedValueNotFound() {
        // Expected value not found
        jsonReader = new JsonReader(new StringReader("{\"key\":}"));
        jsonReader.beginObject();
        assertThrows(IOException.class, () -> invokeDoPeek());
    }

    /**
     * Test the doPeek method when an unexpected value is encountered.
     * Expecting an IOException for 'Unexpected value'.
     */
    @Test
    void testDoPeekWhenUnexpectedValueEncountered() {
        // Unexpected value
        jsonReader = new JsonReader(new StringReader("[1, 2, 'value']"));
        jsonReader.beginArray();
        assertThrows(IOException.class, () -> invokeDoPeek());
    }
}
