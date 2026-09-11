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
        // Added to ensure we are at the end of the document
        jsonReader.endObject();
        assertThrows(EOFException.class, () -> {
            // This should throw EOFException
            jsonReader.peek();
        });
    }

    // Test cases for 'peek' method when 'peeked' is PEEKED_NONE
    @Test
    void testPeekReturnsCorrectTokenFromPeekedNone() throws IOException {
        jsonReader.beginObject();
        assertEquals(JsonToken.BEGIN_OBJECT, jsonReader.peek());
        jsonReader.endObject();
        // Begin a new object to test the next tokens
        jsonReader.beginObject();
        // First token is NAME
        assertEquals(JsonToken.NAME, jsonReader.peek());
        jsonReader.nextName();
        // Next token is STRING
        assertEquals(JsonToken.STRING, jsonReader.peek());
        jsonReader.nextString();
        // End of object
        assertEquals(JsonToken.END_OBJECT, jsonReader.peek());
        jsonReader.endObject();
        // End of document
        assertEquals(JsonToken.END_DOCUMENT, jsonReader.peek());
    }

    // Test cases for 'peek' method when an IOException occurs
    @Test
    void testPeekThrowsIOException() throws IOException {
        // Mocking the doPeek method to throw an IOException
        JsonReader spyReader = spy(jsonReader);
        doThrow(new IOException("Mocked IOException")).when(spyReader).doPeek();
        assertThrows(IOException.class, () -> spyReader.peek());
    }

    // Uncommented test case
    @Test
    void testPeekReturnsStringFromPeekedBuffered() throws IOException, NoSuchFieldException, IllegalAccessException {
        // Assuming 4 corresponds to PEEKED_BUFFERED
        setPeekedValue(JsonReader.class.getDeclaredField("peeked"), 4);
        assertEquals(JsonToken.STRING, jsonReader.peek());
    }

    // Test cases for 'peek' method when peeked is PEEKED_TRUE
    @Test
    void testPeekReturnsBooleanFromPeekedTrue() throws IOException, NoSuchFieldException, IllegalAccessException {
        // Assuming 5 corresponds to PEEKED_TRUE
        setPeekedValue(JsonReader.class.getDeclaredField("peeked"), 5);
        assertEquals(JsonToken.BOOLEAN, jsonReader.peek());
    }

    // Test cases for 'peek' method when peeked is PEEKED_FALSE
    @Test
    void testPeekReturnsBooleanFromPeekedFalse() throws IOException, NoSuchFieldException, IllegalAccessException {
        // Assuming 6 corresponds to PEEKED_FALSE
        setPeekedValue(JsonReader.class.getDeclaredField("peeked"), 6);
        assertEquals(JsonToken.BOOLEAN, jsonReader.peek());
    }

    // Test cases for 'peek' method when peeked is PEEKED_LONG
    @Test
    void testPeekReturnsNumberFromPeekedLong() throws IOException, NoSuchFieldException, IllegalAccessException {
        // Assuming 7 corresponds to PEEKED_LONG
        setPeekedValue(JsonReader.class.getDeclaredField("peeked"), 7);
        assertEquals(JsonToken.NUMBER, jsonReader.peek());
    }

    // Test cases for 'peek' method when peeked is PEEKED_NUMBER
    @Test
    void testPeekReturnsNumberFromPeekedNumber() throws IOException, NoSuchFieldException, IllegalAccessException {
        // Assuming 8 corresponds to PEEKED_NUMBER
        setPeekedValue(JsonReader.class.getDeclaredField("peeked"), 8);
        assertEquals(JsonToken.NUMBER, jsonReader.peek());
    }

    // Test cases for 'peek' method when peeked is an invalid value
    @Test
    void testPeekThrowsAssertionErrorForInvalidPeekedValue() throws IOException, NoSuchFieldException, IllegalAccessException {
        setPeekedValue(JsonReader.class.getDeclaredField("peeked"), -1);
        assertThrows(AssertionError.class, () -> jsonReader.peek());
    }
}
