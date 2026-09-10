```java
package com.google.gson.stream;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import java.io.StringReader;
import java.io.Reader;
import java.io.IOException;

@ExtendWith(MockitoExtension.class)
class JsonReader_doPeek_Test {
    private JsonReader jsonReader;

    @BeforeAll
    static void setupBeforeAll() {
        // Any global setup can be done here
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the JsonReader with a sample JSON input
        Reader reader = new StringReader("{\"key\":\"value\"}");
        jsonReader = new JsonReader(reader);
    }

    @Test
    void testDoPeekEndArray() throws IOException {
        // Test case for an empty array. Expecting to peek an end array.
        jsonReader = new JsonReader(new StringReader("[]"));
        jsonReader.beginArray(); // Prepare the reader for an array
        jsonReader.endArray(); // This should also call doPeek internally
        assertEquals(JsonReader.PEEKED_END_ARRAY, jsonReader.peeked);
    }

    @Test
    void testDoPeekEndObject() throws IOException {
        // Test case for an empty object. Expecting to peek an end object.
        jsonReader = new JsonReader(new StringReader("{}"));
        jsonReader.beginObject(); // Prepare the reader for an object
        jsonReader.endObject(); // This should also call doPeek internally
        assertEquals(JsonReader.PEEKED_END_OBJECT, jsonReader.peeked);
    }

    @Test
    void testDoPeekDoubleQuotedName() throws IOException {
        // Test case for a valid double-quoted name in a non-empty object.
        jsonReader = new JsonReader(new StringReader("{\"key\":\"value\"}"));
        jsonReader.beginObject(); // Prepare the reader for an object
        assertEquals(JsonReader.PEEKED_DOUBLE_QUOTED_NAME, jsonReader.peeked);
        jsonReader.nextName(); // Consume the name
    }

    @Test
    void testDoPeekSingleQuotedName() throws IOException {
        // Test case for a valid single-quoted name in a non-empty object.
        jsonReader = new JsonReader(new StringReader("{'key':'value'}"));
        jsonReader.beginObject(); // Prepare the reader for an object
        jsonReader.setLenient(true); // Enable lenient mode for single quotes
        assertEquals(JsonReader.PEEKED_SINGLE_QUOTED_NAME, jsonReader.peeked);
        jsonReader.nextName(); // Consume the name
    }

    @Test
    void testDoPeekUnquotedName() throws IOException {
        // Test case for a valid unquoted name in a non-empty object.
        jsonReader = new JsonReader(new StringReader("{key:value}"));
        jsonReader.setLenient(true); // Enable lenient mode for unquoted names
        jsonReader.beginObject(); // Prepare the reader for an object
        assertEquals(JsonReader.PEEKED_UNQUOTED_NAME, jsonReader.peeked);
        jsonReader.nextName(); // Consume the name
    }

    @Test
    void testDoPeekEOF() throws IOException {
        // Test case for end of file in a non-empty document.
        jsonReader = new JsonReader(new StringReader("{\"key\":\"value\"}"));
        jsonReader.beginObject(); // Prepare the reader for an object
        jsonReader.nextName(); // Consume the name
        jsonReader.nextString(); // Consume the value
        assertEquals(JsonReader.PEEKED_EOF, jsonReader.peeked);
    }

    @Test
    void testDoPeekUnterminatedArray() {
        // Test case for an unterminated array. Expecting a syntax error.
        jsonReader = new JsonReader(new StringReader("[1, 2, 3"));
        jsonReader.beginArray(); // Prepare the reader for an array
        assertThrows(IOException.class, () -> {
            jsonReader.endArray(); // This should throw an error
        });
    }

    @Test
    void testDoPeekUnterminatedObject() {
        // Test case for an unterminated object. Expecting a syntax error.
        jsonReader = new JsonReader(new StringReader("{\"key\":\"value\""));
        jsonReader.beginObject(); // Prepare the reader for an object
        assertThrows(IOException.class, () -> {
            jsonReader.endObject(); // This should throw an error
        });
    }

    @Test
    void testDoPeekExpectedName() {
        // Test case where a name was expected but not found. Expecting a syntax error.
        jsonReader = new JsonReader(new StringReader("{:}"));
        jsonReader.beginObject(); // Prepare the reader for an object
        assertThrows(IOException.class, () -> {
            jsonReader.nextName(); // This should throw an error
        });
    }

    @Test
    void testDoPeekClosedReader() {
        // Test case where the JsonReader is closed. Expecting an IllegalStateException.
        jsonReader.close();
        assertThrows(IllegalStateException.class, () -> {
            jsonReader.doPeek(); // This should throw an error
        });
    }

    @Test
    void testDoPeekNullInLenientMode() throws IOException {
        // Test case for a null value in a lenient mode. Expecting to peek null.
        jsonReader = new JsonReader(new StringReader("[null]"));
        jsonReader.setLenient(true); // Enable lenient mode
        jsonReader.beginArray(); // Prepare the reader for an array
        assertEquals(JsonReader.PEEKED_NULL, jsonReader.peeked);
        jsonReader.nextNull(); // Consume the null value
    }

    @Test
    void testDoPeekSingleQuotedString() throws IOException {
        // Test case for a valid single-quoted string value. Expecting to peek a single-quoted string.
        jsonReader = new JsonReader(new StringReader("{'key':'value'}"));
        jsonReader.setLenient(true); // Enable lenient mode for single quotes
        jsonReader.beginObject(); // Prepare the reader for an object
        jsonReader.nextName(); // Consume the name
        assertEquals(JsonReader.PEEKED_SINGLE_QUOTED, jsonReader.peeked);
        jsonReader.nextString(); // Consume the value
    }

    @Test
    void testDoPeekDoubleQuotedString() throws IOException {
        // Test case for a valid double-quoted string value. Expecting to peek a double-quoted string.
        jsonReader = new JsonReader(new StringReader("{\"key\":\"value\"}"));
        jsonReader.beginObject(); // Prepare the reader for an object
        jsonReader.nextName(); // Consume the name
        assertEquals(JsonReader.PEEKED_DOUBLE_QUOTED, jsonReader.peeked);
        jsonReader.nextString(); // Consume the value
    }

    @Test
    void testDoPeekUnexpectedValue() {
        // Test case for an unexpected value encountered. Expecting a syntax error.
        jsonReader = new JsonReader(new StringReader("{key: value}"));
        jsonReader.setLenient(true); // Enable lenient mode
        jsonReader.beginObject(); // Prepare the reader for an object
        assertThrows(IOException.class, () -> {
            jsonReader.nextName(); // This should throw an error
        });
    }

    @Test
    void testDoPeekExpectedValue() {
        // Test case for an expected value not found. Expecting a syntax error.
        jsonReader = new JsonReader(new StringReader("{\"key\":}"));
        jsonReader.beginObject(); // Prepare the reader for an object
        assertThrows(IOException.class, () -> {
            jsonReader.nextString(); // This should throw an error
        });
    }

    @AfterEach
    void teardownAfterEach() {
        // Any cleanup after each test can be done here
    }

    @AfterAll
    static void teardownAfterAll() {
        // Any global cleanup can be done here
    }
}
```