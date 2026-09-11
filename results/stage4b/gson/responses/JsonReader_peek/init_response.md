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
        // This method can be used to set up any static resources before all tests.
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the JsonReader with a sample JSON string for testing
        String jsonString = "{\"key\": \"value\"}";
        Reader reader = new StringReader(jsonString);
        jsonReader = new JsonReader(reader);
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
        // This method can be used to clean up any static resources after all tests.
    }

    @Test
    void testPeekReturnsCorrectToken() throws IOException {
        jsonReader.beginObject();
        assertEquals(JsonToken.NAME, jsonReader.peek());
        jsonReader.nextName();
        assertEquals(JsonToken.STRING, jsonReader.peek());
        jsonReader.nextString();
        assertEquals(JsonToken.END_OBJECT, jsonReader.peek());
        jsonReader.endObject();
        assertEquals(JsonToken.END_DOCUMENT, jsonReader.peek());
    }

    @Test
    void testPeekThrowsEOFException() throws IOException {
        jsonReader.beginObject();
        jsonReader.nextName();
        jsonReader.nextString();
        jsonReader.endObject();
        
        // At this point, the reader is at the end of the document
        assertThrows(EOFException.class, () -> {
            jsonReader.peek(); // This should throw EOFException
        });
    }
}
``` 

This code outlines a test class for the `JsonReader` class, specifically testing the `peek()` method. It includes setup and teardown methods to manage resources and provides sample tests to validate the expected behavior of the `peek()` method under different conditions.