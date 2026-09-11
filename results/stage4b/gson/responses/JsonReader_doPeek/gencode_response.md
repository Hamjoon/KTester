```java
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

    @BeforeAll
    static void setupBeforeAll() {
        // Any global setup can be done here
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the JsonReader with a valid JSON input for testing
        String jsonInput = "{\"key\": \"value\"}"; // Example JSON input
        jsonReader = new JsonReader(new StringReader(jsonInput));
    }

    /**
     * Test the doPeek method when the JSON document is empty.
     * Expecting PEEKED_EOF.
     */
    @Test
    void testDoPeekWithEmptyDocument() throws Exception {
        jsonReader = new JsonReader(new StringReader("")); // Empty JSON document
        invokeDoPeekAndAssert(17, "PEEKED_EOF");
    }

    /**
     * Test the doPeek method when the current state is an empty array.
     * Expecting PEEKED_END_ARRAY.
     */
    @Test
    void testDoPeekWithEmptyArray() throws Exception {
        jsonReader = new JsonReader(new StringReader("[]")); // Empty array
        jsonReader.beginArray(); // This will invoke doPeek indirectly
        assertEquals(2, invokeDoPeek()); // Expect PEEKED_END_ARRAY
        jsonReader.endArray();
    }

    /**
     * Test the doPeek method when the current state is an empty object.
     * Expecting PEEKED_END_OBJECT.
     */
    @Test
    void testDoPeekWithEmptyObject() throws Exception {
        jsonReader = new JsonReader(new StringReader("{}")); // Empty object
        jsonReader.beginObject(); // This will invoke doPeek indirectly
        assertEquals(2, invokeDoPeek()); // Expect PEEKED_END_OBJECT
        jsonReader.endObject();
    }

    /**
     * Test the doPeek method when the next character is a single-quoted name.
     * Expecting PEEKED_SINGLE_QUOTED_NAME.
     */
    @Test
    void testDoPeekWithSingleQuotedName() throws Exception {
        jsonReader = new JsonReader(new StringReader("{'key': 'value'}")); // Single-quoted name
        jsonReader.beginObject(); // This will invoke doPeek indirectly
        assertEquals(12, invokeDoPeek()); // Expect PEEKED_SINGLE_QUOTED_NAME
        jsonReader.endObject();
    }

    /**
     * Test the doPeek method when the next character is a double-quoted name.
     * Expecting PEEKED_DOUBLE_QUOTED_NAME.
     */
    @Test
    void testDoPeekWithDoubleQuotedName() throws Exception {
        jsonReader = new JsonReader(new StringReader("{\"key\": \"value\"}")); // Double-quoted name
        jsonReader.beginObject(); // This will invoke doPeek indirectly
        assertEquals(13, invokeDoPeek()); // Expect PEEKED_DOUBLE_QUOTED_NAME
        jsonReader.endObject();
    }

    /**
     * Test the doPeek method when the next character is an unquoted name.
     * Expecting PEEKED_UNQUOTED_NAME.
     */
    @Test
    void testDoPeekWithUnquotedName() throws Exception {
        jsonReader = new JsonReader(new StringReader("{key: \"value\"}")); // Unquoted name
        jsonReader.beginObject(); // This will invoke doPeek indirectly
        assertEquals(14, invokeDoPeek()); // Expect PEEKED_UNQUOTED_NAME
        jsonReader.endObject();
    }

    /**
     * Test the doPeek method when the next character is a single quote in lenient mode.
     * Expecting PEEKED_SINGLE_QUOTED.
     */
    @Test
    void testDoPeekWithSingleQuoteInLenientMode() throws Exception {
        jsonReader.setLenient(true);
        jsonReader = new JsonReader(new StringReader("[ 'value' ]")); // Single quote in lenient mode
        jsonReader.beginArray(); // This will invoke doPeek indirectly
        assertEquals(8, invokeDoPeek()); // Expect PEEKED_SINGLE_QUOTED
        jsonReader.endArray();
    }

    /**
     * Test the doPeek method when the next character is a double quote.
     * Expecting PEEKED_DOUBLE_QUOTED.
     */
    @Test
    void testDoPeekWithDoubleQuote() throws Exception {
        jsonReader = new JsonReader(new StringReader("[ \"value\" ]")); // Double quote
        jsonReader.beginArray(); // This will invoke doPeek indirectly
        assertEquals(9, invokeDoPeek()); // Expect PEEKED_DOUBLE_QUOTED
        jsonReader.endArray();
    }

    /**
     * Test the doPeek method when the next character is a '[' indicating the start of an array.
     * Expecting PEEKED_BEGIN_ARRAY.
     */
    @Test
    void testDoPeekWithBeginArray() throws Exception {
        jsonReader = new JsonReader(new StringReader("[1, 2, 3]")); // Begin array
        jsonReader.beginArray(); // This will invoke doPeek indirectly
        assertEquals(3, invokeDoPeek()); // Expect PEEKED_BEGIN_ARRAY
    }

    /**
     * Test the doPeek method when the next character is a '{' indicating the start of an object.
     * Expecting PEEKED_BEGIN_OBJECT.
     */
    @Test
    void testDoPeekWithBeginObject() throws Exception {
        jsonReader = new JsonReader(new StringReader("{\"key\": \"value\"}")); // Begin object
        jsonReader.beginObject(); // This will invoke doPeek indirectly
        assertEquals(1, invokeDoPeek()); // Expect PEEKED_BEGIN_OBJECT
    }

    /**
     * Test the doPeek method when there is an unexpected character in the array.
     * Expecting an IOException for 'Unterminated array'.
     */
    @Test
    void testDoPeekWithUnexpectedCharacterInArray() {
        jsonReader = new JsonReader(new StringReader("[1, 2, 3")); // Unterminated array
        jsonReader.beginArray();
        assertThrows(IOException.class, () -> invokeDoPeek());
    }

    /**
     * Test the doPeek method when there is an unexpected character in the object.
     * Expecting an IOException for 'Unterminated object'.
     */
    @Test
    void testDoPeekWithUnexpectedCharacterInObject() {
        jsonReader = new JsonReader(new StringReader("{\"key\": \"value\"")); // Unterminated object
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
        jsonReader = new JsonReader(new StringReader("{: \"value\"}")); // Expected name not found
        jsonReader.beginObject();
        assertThrows(IOException.class, () -> invokeDoPeek());
    }

    /**
     * Test the doPeek method when a value is expected but not found.
     * Expecting an IOException for 'Expected value'.
     */
    @Test
    void testDoPeekWhenExpectedValueNotFound() {
        jsonReader = new JsonReader(new StringReader("{\"key\":}")); // Expected value not found
        jsonReader.beginObject();
        assertThrows(IOException.class, () -> invokeDoPeek());
    }

    /**
     * Test the doPeek method when an unexpected value is encountered.
     * Expecting an IOException for 'Unexpected value'.
     */
    @Test
    void testDoPeekWhenUnexpectedValueEncountered() {
        jsonReader = new JsonReader(new StringReader("[1, 2, 'value']")); // Unexpected value
        jsonReader.beginArray();
        assertThrows(IOException.class, () -> invokeDoPeek());
    }

    @AfterEach
    void teardownAfterEach() throws IOException {
        // Close the JsonReader after each test
        jsonReader.close();
    }

    @AfterAll
    static void teardownAfterAll() {
        // Any global cleanup can be done here
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
}
```