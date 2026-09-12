```java
package com.google.gson.stream;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

@ExtendWith(MockitoExtension.class)
class JsonReader_peek_Test {

    private JsonReader jsonReader;

    @BeforeAll
    static void setupBeforeAll() {
        // Initialize resources shared across tests
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize resources before each test
        jsonReader = new JsonReader(new StringReader("{}"));
    }

    @AfterEach
    void teardownAfterEach() {
        // Clean up resources after each test
        try {
            jsonReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    static void teardownAfterAll() {
        // Clean up resources shared across tests
    }

    @Test
    void testPeekWithEmptyObject() throws IOException {
        jsonReader.beginObject();
        assertEquals(JsonToken.END_OBJECT, jsonReader.peek());
        jsonReader.endObject();
        assertEquals(JsonToken.END_DOCUMENT, jsonReader.peek());
    }

    @Test
    void testPeekWithArray() throws IOException {
        JsonReader reader = new JsonReader(new StringReader("[true, false]"));
        reader.beginArray();
        assertEquals(JsonToken.BOOLEAN, reader.peek());
        reader.nextBoolean();
        assertEquals(JsonToken.BOOLEAN, reader.peek());
        reader.nextBoolean();
        reader.endArray();
        assertEquals(JsonToken.END_DOCUMENT, reader.peek());
    }

    @Test
    void testPeekWithNull() throws IOException {
        JsonReader reader = new JsonReader(new StringReader("null"));
        assertEquals(JsonToken.NULL, reader.peek());
        reader.nextNull();
        assertEquals(JsonToken.END_DOCUMENT, reader.peek());
    }

    @Test
    void testPeekWithString() throws IOException {
        JsonReader reader = new JsonReader(new StringReader("\"string\""));
        assertEquals(JsonToken.STRING, reader.peek());
        assertEquals("string", reader.nextString());
        assertEquals(JsonToken.END_DOCUMENT, reader.peek());
    }
}
```