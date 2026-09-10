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
    void testPeekThrowsIOException() {
        jsonReader = new JsonReader(new StringReader("{"));
        // Start a malformed object
        jsonReader.beginObject();
        assertThrows(IOException.class, () -> jsonReader.peek());
    }

    @Test
    void testPeekWithNoPriorPeeking() throws IOException {
        // Test peek when no prior peeking has been done and the next token is a begin object
        jsonReader = new JsonReader(new StringReader("{\"key\": \"value\"}"));
        assertEquals(JsonToken.BEGIN_OBJECT, jsonReader.peek());
        // Consume the object
        jsonReader.beginObject();
        // Test peek when no prior peeking has been done and the next token is an end object
        jsonReader = new JsonReader(new StringReader("{\"key\": \"value\"}"));
        // Consume the object
        jsonReader.beginObject();
        assertEquals(JsonToken.END_OBJECT, jsonReader.peek());
        // Consume the end of object
        jsonReader.endObject();
        // Test peek when no prior peeking has been done and the next token is a begin array
        jsonReader = new JsonReader(new StringReader("[1, 2, 3]"));
        assertEquals(JsonToken.BEGIN_ARRAY, jsonReader.peek());
        // Consume the array
        jsonReader.beginArray();
        // Test peek when no prior peeking has been done and the next token is an end array
        jsonReader = new JsonReader(new StringReader("[1, 2, 3]"));
        // Consume the array
        jsonReader.beginArray();
        assertEquals(JsonToken.END_ARRAY, jsonReader.peek());
        // Consume the end of array
        jsonReader.endArray();
        // Test peek when no prior peeking has been done and the next token is null
        jsonReader = new JsonReader(new StringReader("null"));
        assertEquals(JsonToken.NULL, jsonReader.peek());
        // Consume the null
        jsonReader.nextNull();
        // Test peek when no prior peeking has been done and the next token is a boolean
        jsonReader = new JsonReader(new StringReader("true"));
        assertEquals(JsonToken.BOOLEAN, jsonReader.peek());
        // Consume the boolean
        assertTrue(jsonReader.nextBoolean());
        // Test peek when no prior peeking has been done and the next token is a string
        jsonReader = new JsonReader(new StringReader("\"string\""));
        assertEquals(JsonToken.STRING, jsonReader.peek());
        // Consume the string
        assertEquals("string", jsonReader.nextString());
        // Test peek when no prior peeking has been done and the next token is a number
        jsonReader = new JsonReader(new StringReader("123"));
        assertEquals(JsonToken.NUMBER, jsonReader.peek());
        // Consume the number
        assertEquals(123, jsonReader.nextInt());
        // Test peek when no prior peeking has been done and the next token is end of document
        jsonReader = new JsonReader(new StringReader("{}"));
        // Consume the object
        jsonReader.beginObject();
        // Consume the end of object
        jsonReader.endObject();
        assertEquals(JsonToken.END_DOCUMENT, jsonReader.peek());
    }

    @Test
    void testPeekWithPriorPeeking() throws IOException {
        // Test peek when prior peeking has set the state to BEGIN_OBJECT
        jsonReader = new JsonReader(new StringReader("{\"key\": \"value\"}"));
        assertEquals(JsonToken.BEGIN_OBJECT, jsonReader.peek());
        // Consume the object
        jsonReader.beginObject();
        // Test peek when prior peeking has set the state to END_OBJECT
        jsonReader = new JsonReader(new StringReader("{\"key\": \"value\"}"));
        // Consume the object
        jsonReader.beginObject();
        // Consume the end of object
        jsonReader.endObject();
        assertEquals(JsonToken.END_OBJECT, jsonReader.peek());
        // Test peek when prior peeking has set the state to BEGIN_ARRAY
        jsonReader = new JsonReader(new StringReader("[1, 2, 3]"));
        assertEquals(JsonToken.BEGIN_ARRAY, jsonReader.peek());
        // Consume the array
        jsonReader.beginArray();
        // Test peek when prior peeking has set the state to END_ARRAY
        jsonReader = new JsonReader(new StringReader("[1, 2, 3]"));
        // Consume the array
        jsonReader.beginArray();
        // Consume the end of array
        jsonReader.endArray();
        assertEquals(JsonToken.END_ARRAY, jsonReader.peek());
        // Test peek when prior peeking has set the state to NULL
        jsonReader = new JsonReader(new StringReader("null"));
        assertEquals(JsonToken.NULL, jsonReader.peek());
        // Consume the null
        jsonReader.nextNull();
        // Test peek when prior peeking has set the state to BOOLEAN
        jsonReader = new JsonReader(new StringReader("true"));
        assertEquals(JsonToken.BOOLEAN, jsonReader.peek());
        // Consume the boolean
        assertTrue(jsonReader.nextBoolean());
        // Test peek when prior peeking has set the state to STRING
        jsonReader = new JsonReader(new StringReader("\"string\""));
        assertEquals(JsonToken.STRING, jsonReader.peek());
        // Consume the string
        assertEquals("string", jsonReader.nextString());
        // Test peek when prior peeking has set the state to NUMBER
        jsonReader = new JsonReader(new StringReader("123"));
        assertEquals(JsonToken.NUMBER, jsonReader.peek());
        // Consume the number
        assertEquals(123, jsonReader.nextInt());
        // Test peek when prior peeking has set the state to END_DOCUMENT
        jsonReader = new JsonReader(new StringReader("{}"));
        // Consume the object
        jsonReader.beginObject();
        // Consume the end of object
        jsonReader.endObject();
        assertEquals(JsonToken.END_DOCUMENT, jsonReader.peek());
    }

    @Test
    void testPeekWithAssertionError() throws IOException {
        // Mock the peeked state to an unexpected value to trigger an AssertionError
        jsonReader = new JsonReader(new StringReader("{\"key\": \"value\"}"));
        // Simulating unexpected state by directly accessing the peeked variable using reflection
        java.lang.reflect.Field field = JsonReader.class.getDeclaredField("peeked");
        field.setAccessible(true);
        // Set to an unexpected value
        field.setInt(jsonReader, 999);
        // Expecting an AssertionError
        assertThrows(AssertionError.class, () -> jsonReader.peek());
    }

    @Test
    void testPeekWithEdgeCases() throws IOException {
        // Test peek when the input JSON starts with a begin object token
        jsonReader = new JsonReader(new StringReader("{\"key\": \"value\"}"));
        assertEquals(JsonToken.BEGIN_OBJECT, jsonReader.peek());
        // Consume the object
        jsonReader.beginObject();
        // Test peek when the input JSON ends with an end array token
        jsonReader = new JsonReader(new StringReader("[1, 2, 3]"));
        // Consume the array
        jsonReader.beginArray();
        // Consume the end of array
        jsonReader.endArray();
        assertEquals(JsonToken.END_ARRAY, jsonReader.peek());
        // Test peek when the input JSON has a number token at the beginning
        jsonReader = new JsonReader(new StringReader("123"));
        assertEquals(JsonToken.NUMBER, jsonReader.peek());
        // Consume the number
        assertEquals(123, jsonReader.nextInt());
        // Test peek when the input JSON has a string token at the beginning
        jsonReader = new JsonReader(new StringReader("\"string\""));
        assertEquals(JsonToken.STRING, jsonReader.peek());
        // Consume the string
        assertEquals("string", jsonReader.nextString());
        // Test peek when the input JSON has a boolean token at the beginning
        jsonReader = new JsonReader(new StringReader("true"));
        assertEquals(JsonToken.BOOLEAN, jsonReader.peek());
        // Consume the boolean
        assertTrue(jsonReader.nextBoolean());
    }

    @Test
    void testPeekWithComplexJsonStructures() throws IOException {
        // Test peek when the input JSON is a complex structure that starts with a begin array token
        jsonReader = new JsonReader(new StringReader("[{\"key\": \"value\"}]"));
        assertEquals(JsonToken.BEGIN_ARRAY, jsonReader.peek());
        // Consume the array
        jsonReader.beginArray();
        // Test peek when the input JSON is a complex structure that ends with an end object token
        jsonReader = new JsonReader(new StringReader("{\"key\": \"value\"}"));
        // Consume the object
        jsonReader.beginObject();
        // Consume the end of object
        jsonReader.endObject();
        assertEquals(JsonToken.END_OBJECT, jsonReader.peek());
    }

    @Test
    void testPeekAfterMultipleOperations() throws IOException {
        // Test peek after reading all tokens in the input JSON, expecting an end document token
        jsonReader = new JsonReader(new StringReader("[{\"key\": \"value\"}]"));
        // Consume the array
        jsonReader.beginArray();
        // Consume the object
        jsonReader.beginObject();
        // Consume the name
        jsonReader.nextName();
        // Consume the string
        jsonReader.nextString();
        // Consume the end of object
        jsonReader.endObject();
        // Consume the end of array
        jsonReader.endArray();
        assertEquals(JsonToken.END_DOCUMENT, jsonReader.peek());
        // Test peek after reading a null token in the input JSON
        jsonReader = new JsonReader(new StringReader("[null]"));
        // Consume the array
        jsonReader.beginArray();
        assertEquals(JsonToken.NULL, jsonReader.peek());
        // Consume the null
        jsonReader.nextNull();
        // Consume the end of array
        jsonReader.endArray();
    }

    @Test
    void testPeekWithIOException() throws IOException {
        // Test peek when an IOException occurs during the doPeek() method call
        jsonReader = new JsonReader(new StringReader("{"));
        // Start a malformed object
        jsonReader.beginObject();
        assertThrows(IOException.class, () -> jsonReader.peek());
    }
}
