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

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the JsonReader with a sample JSON input
        Reader reader = new StringReader("{\"key\":\"value\"}");
        jsonReader = new JsonReader(reader);
    }

    @Test
    void testDoPeekBeginObject() throws IOException {
        // This should call doPeek internally
        jsonReader.beginObject();
        assertEquals(JsonReader.PEEKED_BEGIN_OBJECT, jsonReader.peeked);
    }

    @Test
    void testDoPeekEndObject() throws IOException {
        // Test case for an empty object. Expecting to peek an end object.
        jsonReader = new JsonReader(new StringReader("{}"));
        // Prepare the reader for an object
        jsonReader.beginObject();
        // This should also call doPeek internally
        jsonReader.endObject();
        assertEquals(JsonReader.PEEKED_END_OBJECT, jsonReader.peeked);
    }

    @Test
    void testDoPeekBeginArray() throws IOException {
        // JSON array input
        jsonReader = new JsonReader(new StringReader("[1, 2, 3]"));
        // This should call doPeek internally
        jsonReader.beginArray();
        assertEquals(JsonReader.PEEKED_BEGIN_ARRAY, jsonReader.peeked);
    }

    @Test
    void testDoPeekEndArray() throws IOException {
        // Test case for an empty array. Expecting to peek an end array.
        jsonReader = new JsonReader(new StringReader("[]"));
        // Prepare the reader for an array
        jsonReader.beginArray();
        // This should also call doPeek internally
        jsonReader.endArray();
        assertEquals(JsonReader.PEEKED_END_ARRAY, jsonReader.peeked);
    }

    @Test
    void testDoPeekDoubleQuotedName() throws IOException {
        // Test case for a valid double-quoted name in a non-empty object.
        jsonReader = new JsonReader(new StringReader("{\"key\":\"value\"}"));
        // Prepare the reader for an object
        jsonReader.beginObject();
        assertEquals(JsonReader.PEEKED_DOUBLE_QUOTED_NAME, jsonReader.peeked);
        // Consume the name
        jsonReader.nextName();
    }

    @Test
    void testDoPeekSingleQuotedName() throws IOException {
        // Test case for a valid single-quoted name in a non-empty object.
        jsonReader = new JsonReader(new StringReader("{'key':'value'}"));
        // Prepare the reader for an object
        jsonReader.beginObject();
        // Enable lenient mode for single quotes
        jsonReader.setLenient(true);
        assertEquals(JsonReader.PEEKED_SINGLE_QUOTED_NAME, jsonReader.peeked);
        // Consume the name
        jsonReader.nextName();
    }

    @Test
    void testDoPeekUnquotedName() throws IOException {
        // Test case for a valid unquoted name in a non-empty object.
        jsonReader = new JsonReader(new StringReader("{key:value}"));
        // Enable lenient mode for unquoted names
        jsonReader.setLenient(true);
        // Prepare the reader for an object
        jsonReader.beginObject();
        assertEquals(JsonReader.PEEKED_UNQUOTED_NAME, jsonReader.peeked);
        // Consume the name
        jsonReader.nextName();
    }

    @Test
    void testDoPeekEOF() throws IOException {
        // Test case for end of file in a non-empty document.
        jsonReader = new JsonReader(new StringReader("{\"key\":\"value\"}"));
        // Prepare the reader for an object
        jsonReader.beginObject();
        // Consume the name
        jsonReader.nextName();
        // Consume the value
        jsonReader.nextString();
        assertEquals(JsonReader.PEEKED_EOF, jsonReader.peeked);
    }

    @Test
    void testDoPeekUnterminatedArray() {
        // Test case for an unterminated array. Expecting a syntax error.
        jsonReader = new JsonReader(new StringReader("[1, 2, 3"));
        // Prepare the reader for an array
        jsonReader.beginArray();
        assertThrows(IOException.class, () -> {
            // This should throw an error
            jsonReader.endArray();
        });
    }

    @Test
    void testDoPeekUnterminatedObject() {
        // Test case for an unterminated object. Expecting a syntax error.
        jsonReader = new JsonReader(new StringReader("{\"key\":\"value\""));
        // Prepare the reader for an object
        jsonReader.beginObject();
        assertThrows(IOException.class, () -> {
            // This should throw an error
            jsonReader.endObject();
        });
    }

    @Test
    void testDoPeekExpectedName() {
        // Test case where a name was expected but not found. Expecting a syntax error.
        jsonReader = new JsonReader(new StringReader("{:}"));
        // Prepare the reader for an object
        jsonReader.beginObject();
        assertThrows(IOException.class, () -> {
            // This should throw an error
            jsonReader.nextName();
        });
    }

    @Test
    void testDoPeekClosedReader() {
        // Test case where the JsonReader is closed. Expecting an IllegalStateException.
        jsonReader.close();
        assertThrows(IllegalStateException.class, () -> {
            // This should throw an error
            jsonReader.doPeek();
        });
    }

    @Test
    void testDoPeekNullInLenientMode() throws IOException {
        // Test case for a null value in a lenient mode. Expecting to peek null.
        jsonReader = new JsonReader(new StringReader("[null]"));
        // Enable lenient mode
        jsonReader.setLenient(true);
        // Prepare the reader for an array
        jsonReader.beginArray();
        assertEquals(JsonReader.PEEKED_NULL, jsonReader.peeked);
        // Consume the null value
        jsonReader.nextNull();
    }

    @Test
    void testDoPeekSingleQuotedString() throws IOException {
        // Test case for a valid single-quoted string value. Expecting to peek a single-quoted string.
        jsonReader = new JsonReader(new StringReader("{'key':'value'}"));
        // Enable lenient mode for single quotes
        jsonReader.setLenient(true);
        // Prepare the reader for an object
        jsonReader.beginObject();
        // Consume the name
        jsonReader.nextName();
        assertEquals(JsonReader.PEEKED_SINGLE_QUOTED, jsonReader.peeked);
        // Consume the value
        jsonReader.nextString();
    }

    @Test
    void testDoPeekDoubleQuotedString() throws IOException {
        // Test case for a valid double-quoted string value. Expecting to peek a double-quoted string.
        jsonReader = new JsonReader(new StringReader("{\"key\":\"value\"}"));
        // Prepare the reader for an object
        jsonReader.beginObject();
        // Consume the name
        jsonReader.nextName();
        assertEquals(JsonReader.PEEKED_DOUBLE_QUOTED, jsonReader.peeked);
        // Consume the value
        jsonReader.nextString();
    }

    @Test
    void testDoPeekUnexpectedValue() {
        // Test case for an unexpected value encountered. Expecting a syntax error.
        jsonReader = new JsonReader(new StringReader("{key: value}"));
        // Enable lenient mode
        jsonReader.setLenient(true);
        // Prepare the reader for an object
        jsonReader.beginObject();
        assertThrows(IOException.class, () -> {
            // This should throw an error
            jsonReader.nextName();
        });
    }

    @Test
    void testDoPeekExpectedValue() {
        // Test case for an expected value not found. Expecting a syntax error.
        jsonReader = new JsonReader(new StringReader("{\"key\":}"));
        // Prepare the reader for an object
        jsonReader.beginObject();
        assertThrows(IOException.class, () -> {
            // This should throw an error
            jsonReader.nextString();
        });
    }
}
