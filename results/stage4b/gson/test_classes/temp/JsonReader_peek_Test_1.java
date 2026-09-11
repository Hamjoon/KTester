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
import java.io.EOFException;
import java.lang.reflect.Field;

@ExtendWith(MockitoExtension.class)
class JsonReader_peek_Test {

    private JsonReader jsonReader;

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

    private void setPeekedValue(Field field, int value) throws IllegalAccessException {
        field.setAccessible(true);
        field.setInt(jsonReader, value);
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
            // This should throw EOFException
            jsonReader.peek();
        });
    }

    // Test cases for 'peek' method when 'peeked' is PEEKED_NONE
    @Test
    void testPeekReturnsCorrectTokenFromPeekedNone() throws IOException {
        // Test that calling peek() when peeked is PEEKED_NONE returns JsonToken.BEGIN_OBJECT after doPeek() sets it to PEEKED_BEGIN_OBJECT.
        jsonReader.beginObject();
        assertEquals(JsonToken.BEGIN_OBJECT, jsonReader.peek());
        // Test that calling peek() when peeked is PEEKED_NONE returns JsonToken.END_OBJECT after doPeek() sets it to PEEKED_END_OBJECT.
        jsonReader.endObject();
        assertEquals(JsonToken.END_OBJECT, jsonReader.peek());
        // Test that calling peek() when peeked is PEEKED_NONE returns JsonToken.BEGIN_ARRAY after doPeek() sets it to PEEKED_BEGIN_ARRAY.
        jsonReader.beginArray();
        assertEquals(JsonToken.BEGIN_ARRAY, jsonReader.peek());
        // Test that calling peek() when peeked is PEEKED_NONE returns JsonToken.END_ARRAY after doPeek() sets it to PEEKED_END_ARRAY.
        jsonReader.endArray();
        assertEquals(JsonToken.END_ARRAY, jsonReader.peek());
        // Test that calling peek() when peeked is PEEKED_NONE returns JsonToken.BOOLEAN after doPeek() sets it to PEEKED_TRUE.
        jsonReader.nextBoolean();
        assertEquals(JsonToken.BOOLEAN, jsonReader.peek());
        // Test that calling peek() when peeked is PEEKED_NONE returns JsonToken.NULL after doPeek() sets it to PEEKED_NULL.
        jsonReader.nextNull();
        assertEquals(JsonToken.NULL, jsonReader.peek());
        // Test that calling peek() when peeked is PEEKED_NONE returns JsonToken.STRING after doPeek() sets it to PEEKED_SINGLE_QUOTED.
        jsonReader.nextString();
        assertEquals(JsonToken.STRING, jsonReader.peek());
        // Test that calling peek() when peeked is PEEKED_NONE returns JsonToken.NUMBER after doPeek() sets it to PEEKED_LONG.
        jsonReader.nextLong();
        assertEquals(JsonToken.NUMBER, jsonReader.peek());
        // Test that calling peek() when peeked is PEEKED_NONE returns JsonToken.END_DOCUMENT after doPeek() sets it to PEEKED_EOF.
        jsonReader.endObject();
        assertEquals(JsonToken.END_DOCUMENT, jsonReader.peek());
    }

    // Test cases for 'peek' method with various PEEKED cases
    @Test
    void testPeekReturnsCorrectTokenFromPeekedCases() throws IOException {
        // Set up the state to simulate different peeked values using reflection
        setPeekedValue(JsonReader.class.getDeclaredField("peeked"), JsonReader.PEEKED_SINGLE_QUOTED_NAME);
        assertEquals(JsonToken.NAME, jsonReader.peek());
        setPeekedValue(JsonReader.class.getDeclaredField("peeked"), JsonReader.PEEKED_DOUBLE_QUOTED_NAME);
        assertEquals(JsonToken.NAME, jsonReader.peek());
        setPeekedValue(JsonReader.class.getDeclaredField("peeked"), JsonReader.PEEKED_UNQUOTED_NAME);
        assertEquals(JsonToken.NAME, jsonReader.peek());
        // Test that calling peek() when peeked is an invalid value throws an AssertionError.
        // Invalid state
        setPeekedValue(JsonReader.class.getDeclaredField("peeked"), -1);
        assertThrows(AssertionError.class, () -> jsonReader.peek());
    }

    // Test cases for 'peek' method when peeked is PEEKED_BUFFERED
    @Test
    void testPeekReturnsStringFromPeekedBuffered() throws IOException {
        // Simulating PEEKED_BUFFERED
        setPeekedValue(JsonReader.class.getDeclaredField("peeked"), JsonReader.PEEKED_BUFFERED);
        assertEquals(JsonToken.STRING, jsonReader.peek());
    }

    // Test cases for 'peek' method when peeked is PEEKED_TRUE
    @Test
    void testPeekReturnsBooleanFromPeekedTrue() throws IOException {
        // Simulating PEEKED_TRUE
        setPeekedValue(JsonReader.class.getDeclaredField("peeked"), JsonReader.PEEKED_TRUE);
        assertEquals(JsonToken.BOOLEAN, jsonReader.peek());
    }

    // Test cases for 'peek' method when peeked is PEEKED_FALSE
    @Test
    void testPeekReturnsBooleanFromPeekedFalse() throws IOException {
        // Simulating PEEKED_FALSE
        setPeekedValue(JsonReader.class.getDeclaredField("peeked"), JsonReader.PEEKED_FALSE);
        assertEquals(JsonToken.BOOLEAN, jsonReader.peek());
    }

    // Test cases for 'peek' method when peeked is PEEKED_LONG
    @Test
    void testPeekReturnsNumberFromPeekedLong() throws IOException {
        // Simulating PEEKED_LONG
        setPeekedValue(JsonReader.class.getDeclaredField("peeked"), JsonReader.PEEKED_LONG);
        assertEquals(JsonToken.NUMBER, jsonReader.peek());
    }

    // Test cases for 'peek' method when peeked is PEEKED_NUMBER
    @Test
    void testPeekReturnsNumberFromPeekedNumber() throws IOException {
        // Simulating PEEKED_NUMBER
        setPeekedValue(JsonReader.class.getDeclaredField("peeked"), JsonReader.PEEKED_NUMBER);
        assertEquals(JsonToken.NUMBER, jsonReader.peek());
    }

    // Test cases for 'peek' method when peeked is an invalid value
    @Test
    void testPeekThrowsAssertionErrorForInvalidPeekedValue() throws IOException {
        // Setting an invalid peeked value
        setPeekedValue(JsonReader.class.getDeclaredField("peeked"), -1);
        assertThrows(AssertionError.class, () -> jsonReader.peek());
    }

    // Test cases for 'peek' method when an IOException occurs
    @Test
    void testPeekThrowsIOException() throws IOException {
        // Mocking the doPeek method to throw an IOException
        JsonReader spyReader = spy(jsonReader);
        doThrow(new IOException("Mocked IOException")).when(spyReader).doPeek();
        assertThrows(IOException.class, () -> spyReader.peek());
    }
}
