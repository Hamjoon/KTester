package com.google.gson.stream;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.IOException;
import java.io.StringReader;

@ExtendWith(MockitoExtension.class)
class JsonReader_peek_Test {

    private JsonReader jsonReader;

    @AfterEach
    void teardownAfterEach() {
        // Cleanup after each test
        if (jsonReader != null) {
            try {
                jsonReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    void testPeekBeginObject() throws IOException {
        jsonReader = new JsonReader(new StringReader("{\"key\": \"value\"}"));
        assertEquals(JsonToken.BEGIN_OBJECT, jsonReader.peek());
        // Consume the object
        jsonReader.beginObject();
    }

    @Test
    void testPeekEndObject() throws IOException {
        jsonReader = new JsonReader(new StringReader("{\"key\": \"value\"}"));
        // Consume the object
        jsonReader.beginObject();
        // Ensure there's something to consume
        assertTrue(jsonReader.hasNext());
        assertEquals(JsonToken.END_OBJECT, jsonReader.peek());
        // Consume the end of object
        jsonReader.endObject();
    }

    @Test
    void testPeekBeginArray() throws IOException {
        jsonReader = new JsonReader(new StringReader("[1, 2, 3]"));
        assertEquals(JsonToken.BEGIN_ARRAY, jsonReader.peek());
        // Consume the array
        jsonReader.beginArray();
    }

    @Test
    void testPeekEndArray() throws IOException {
        jsonReader = new JsonReader(new StringReader("[1, 2, 3]"));
        // Consume the array
        jsonReader.beginArray();
        // Ensure there's something to consume
        assertTrue(jsonReader.hasNext());
        // Consume an element
        jsonReader.nextInt();
        // Ensure there's still something left
        assertTrue(jsonReader.hasNext());
        assertEquals(JsonToken.END_ARRAY, jsonReader.peek());
        // Consume the end of array
        jsonReader.endArray();
    }

    @Test
    void testPeekBoolean() throws IOException {
        jsonReader = new JsonReader(new StringReader("true"));
        assertEquals(JsonToken.BOOLEAN, jsonReader.peek());
        // Consume the boolean
        assertTrue(jsonReader.nextBoolean());
    }

    @Test
    void testPeekString() throws IOException {
        jsonReader = new JsonReader(new StringReader("\"string\""));
        assertEquals(JsonToken.STRING, jsonReader.peek());
        // Consume the string
        assertEquals("string", jsonReader.nextString());
    }

    @Test
    void testPeekNull() throws IOException {
        jsonReader = new JsonReader(new StringReader("null"));
        assertEquals(JsonToken.NULL, jsonReader.peek());
        // Consume the null
        jsonReader.nextNull();
    }

    @Test
    void testPeekEndDocument() throws IOException {
        jsonReader = new JsonReader(new StringReader("{}"));
        // Consume the object
        jsonReader.beginObject();
        // Consume the end of object
        jsonReader.endObject();
        assertEquals(JsonToken.END_DOCUMENT, jsonReader.peek());
    }

    @Test
    void testPeekThrowsIOException() throws Exception {
        jsonReader = new JsonReader(new StringReader("{"));
        // Start a malformed object
        assertThrows(IOException.class, () -> {
            jsonReader.beginObject();
            jsonReader.peek();
        });
    }

    @Test
    void testPeekWithNoPriorPeeking() throws IOException {
        jsonReader = new JsonReader(new StringReader("{\"key\": \"value\"}"));
        assertEquals(JsonToken.BEGIN_OBJECT, jsonReader.peek());
        // Consume the object
        jsonReader.beginObject();
        // Test peek when next token is a name
        assertTrue(jsonReader.hasNext());
        assertEquals(JsonToken.NAME, jsonReader.peek());
        // Consume the name
        jsonReader.nextName();
        assertEquals(JsonToken.END_OBJECT, jsonReader.peek());
        // Consume the end of object
        jsonReader.endObject();
        // Test peek with a new array
        jsonReader = new JsonReader(new StringReader("[1, 2, 3]"));
        assertEquals(JsonToken.BEGIN_ARRAY, jsonReader.peek());
        // Consume the array
        jsonReader.beginArray();
        // Consume an element
        jsonReader.nextInt();
        assertEquals(JsonToken.NUMBER, jsonReader.peek());
        // Consume the number
        jsonReader.nextInt();
        assertEquals(JsonToken.END_ARRAY, jsonReader.peek());
        // Consume the end of array
        jsonReader.endArray();
        // Test peek with null
        jsonReader = new JsonReader(new StringReader("null"));
        assertEquals(JsonToken.NULL, jsonReader.peek());
        // Consume the null
        jsonReader.nextNull();
        // Test peek with boolean
        jsonReader = new JsonReader(new StringReader("true"));
        assertEquals(JsonToken.BOOLEAN, jsonReader.peek());
        // Consume the boolean
        assertTrue(jsonReader.nextBoolean());
        // Test peek with string
        jsonReader = new JsonReader(new StringReader("\"string\""));
        assertEquals(JsonToken.STRING, jsonReader.peek());
        // Consume the string
        assertEquals("string", jsonReader.nextString());
        // Test peek with number
        jsonReader = new JsonReader(new StringReader("123"));
        assertEquals(JsonToken.NUMBER, jsonReader.peek());
        // Consume the number
        assertEquals(123, jsonReader.nextInt());
        // Test peek with end of document
        jsonReader = new JsonReader(new StringReader("{}"));
        jsonReader.beginObject();
        jsonReader.endObject();
        assertEquals(JsonToken.END_DOCUMENT, jsonReader.peek());
    }

    @Test
    void testPeekWithPriorPeeking() throws IOException {
        jsonReader = new JsonReader(new StringReader("{\"key\": \"value\"}"));
        assertEquals(JsonToken.BEGIN_OBJECT, jsonReader.peek());
        jsonReader.beginObject();
        assertTrue(jsonReader.hasNext());
        jsonReader.nextName();
        assertTrue(jsonReader.hasNext());
        jsonReader.nextString();
        assertEquals(JsonToken.END_OBJECT, jsonReader.peek());
        jsonReader.endObject();
        jsonReader = new JsonReader(new StringReader("[1, 2, 3]"));
        assertEquals(JsonToken.BEGIN_ARRAY, jsonReader.peek());
        jsonReader.beginArray();
        jsonReader.nextInt();
        assertEquals(JsonToken.NUMBER, jsonReader.peek());
        jsonReader.nextInt();
        assertEquals(JsonToken.END_ARRAY, jsonReader.peek());
        jsonReader.endArray();
        jsonReader = new JsonReader(new StringReader("null"));
        assertEquals(JsonToken.NULL, jsonReader.peek());
        jsonReader.nextNull();
        jsonReader = new JsonReader(new StringReader("true"));
        assertEquals(JsonToken.BOOLEAN, jsonReader.peek());
        jsonReader.nextBoolean();
        jsonReader = new JsonReader(new StringReader("\"string\""));
        assertEquals(JsonToken.STRING, jsonReader.peek());
        assertEquals("string", jsonReader.nextString());
        jsonReader = new JsonReader(new StringReader("123"));
        assertEquals(JsonToken.NUMBER, jsonReader.peek());
        assertEquals(123, jsonReader.nextInt());
        jsonReader = new JsonReader(new StringReader("{}"));
        jsonReader.beginObject();
        jsonReader.endObject();
        assertEquals(JsonToken.END_DOCUMENT, jsonReader.peek());
    }

    @Test
    void testPeekWithAssertionError() throws Exception {
        jsonReader = new JsonReader(new StringReader("{\"key\": \"value\"}"));
        java.lang.reflect.Field field = JsonReader.class.getDeclaredField("peeked");
        field.setAccessible(true);
        field.setInt(jsonReader, 999);
        assertThrows(AssertionError.class, () -> jsonReader.peek());
    }

    @Test
    void testPeekWithEdgeCases() throws IOException {
        jsonReader = new JsonReader(new StringReader("{\"key\": \"value\"}"));
        assertEquals(JsonToken.BEGIN_OBJECT, jsonReader.peek());
        jsonReader.beginObject();
        jsonReader.endObject();
        jsonReader = new JsonReader(new StringReader("[1, 2, 3]"));
        jsonReader.beginArray();
        jsonReader.endArray();
        assertEquals(JsonToken.END_ARRAY, jsonReader.peek());
        jsonReader = new JsonReader(new StringReader("123"));
        assertEquals(JsonToken.NUMBER, jsonReader.peek());
        assertEquals(123, jsonReader.nextInt());
        jsonReader = new JsonReader(new StringReader("\"string\""));
        assertEquals(JsonToken.STRING, jsonReader.peek());
        assertEquals("string", jsonReader.nextString());
        jsonReader = new JsonReader(new StringReader("true"));
        assertEquals(JsonToken.BOOLEAN, jsonReader.peek());
        assertTrue(jsonReader.nextBoolean());
    }

    @Test
    void testPeekWithComplexJsonStructures() throws IOException {
        jsonReader = new JsonReader(new StringReader("[{\"key\": \"value\"}]"));
        assertEquals(JsonToken.BEGIN_ARRAY, jsonReader.peek());
        jsonReader.beginArray();
        assertEquals(JsonToken.BEGIN_OBJECT, jsonReader.peek());
        jsonReader.beginObject();
        assertTrue(jsonReader.hasNext());
        jsonReader.nextName();
        jsonReader.nextString();
        assertEquals(JsonToken.END_OBJECT, jsonReader.peek());
        jsonReader.endObject();
        assertEquals(JsonToken.END_ARRAY, jsonReader.peek());
        jsonReader.endArray();
    }

    @Test
    void testPeekAfterMultipleOperations() throws IOException {
        jsonReader = new JsonReader(new StringReader("[{\"key\": \"value\"}]"));
        jsonReader.beginArray();
        jsonReader.beginObject();
        jsonReader.nextName();
        jsonReader.nextString();
        jsonReader.endObject();
        jsonReader.endArray();
        assertEquals(JsonToken.END_DOCUMENT, jsonReader.peek());
        jsonReader = new JsonReader(new StringReader("[null]"));
        jsonReader.beginArray();
        assertEquals(JsonToken.NULL, jsonReader.peek());
        jsonReader.nextNull();
        jsonReader.endArray();
    }

    @Test
    void testPeekWithIOException() throws IOException {
        jsonReader = new JsonReader(new StringReader("{"));
        assertThrows(IOException.class, () -> {
            jsonReader.beginObject();
            jsonReader.peek();
        });
    }
}
