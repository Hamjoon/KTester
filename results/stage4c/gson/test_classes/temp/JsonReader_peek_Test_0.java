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
import java.lang.reflect.Field;

@ExtendWith(MockitoExtension.class)
class JsonReader_peek_Test {

    private JsonReader jsonReader;

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

    private void setPeekedValue(int peekedValue) throws Exception {
        Field peekedField = JsonReader.class.getDeclaredField("peeked");
        peekedField.setAccessible(true);
        peekedField.set(jsonReader, peekedValue);
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

    @Test
    void testPeekWithPresetPeekedValues() throws Exception {
        // Test cases for different peeked values
        Object[][] testCases = { { JsonReader.PEEKED_BEGIN_OBJECT, JsonToken.BEGIN_OBJECT }, { JsonReader.PEEKED_END_OBJECT, JsonToken.END_OBJECT }, { JsonReader.PEEKED_BEGIN_ARRAY, JsonToken.BEGIN_ARRAY }, { JsonReader.PEEKED_END_ARRAY, JsonToken.END_ARRAY }, { JsonReader.PEEKED_SINGLE_QUOTED_NAME, JsonToken.NAME }, { JsonReader.PEEKED_DOUBLE_QUOTED_NAME, JsonToken.NAME }, { JsonReader.PEEKED_UNQUOTED_NAME, JsonToken.NAME }, { JsonReader.PEEKED_TRUE, JsonToken.BOOLEAN }, { JsonReader.PEEKED_FALSE, JsonToken.BOOLEAN }, { JsonReader.PEEKED_NULL, JsonToken.NULL }, { JsonReader.PEEKED_SINGLE_QUOTED, JsonToken.STRING }, { JsonReader.PEEKED_DOUBLE_QUOTED, JsonToken.STRING }, { JsonReader.PEEKED_UNQUOTED, JsonToken.STRING }, { JsonReader.PEEKED_BUFFERED, JsonToken.STRING }, { JsonReader.PEEKED_LONG, JsonToken.NUMBER }, { JsonReader.PEEKED_NUMBER, JsonToken.NUMBER }, { JsonReader.PEEKED_EOF, JsonToken.END_DOCUMENT } };
        for (Object[] testCase : testCases) {
            setPeekedValue((int) testCase[0]);
            assertEquals(testCase[1], jsonReader.peek(), "Failed for peeked value: " + testCase[0]);
        }
    }

    @Test
    void testPeekWithUnexpectedPeekedValue() throws Exception {
        // Test case for unexpected peeked value
        // an unexpected value
        setPeekedValue(999);
        assertThrows(AssertionError.class, () -> jsonReader.peek(), "Expected an AssertionError for unexpected peeked value.");
    }

    @Test
    void testPeekWhenDoPeekReturnsValues() throws Exception {
        // Mock doPeek() method to return specific values
        JsonReader spyReader = Mockito.spy(jsonReader);
        // Test cases for doPeek() returning different values
        Object[][] testCases = { { JsonReader.PEEKED_BEGIN_OBJECT, JsonToken.BEGIN_OBJECT }, { JsonReader.PEEKED_END_OBJECT, JsonToken.END_OBJECT }, { JsonReader.PEEKED_BEGIN_ARRAY, JsonToken.BEGIN_ARRAY }, { JsonReader.PEEKED_END_ARRAY, JsonToken.END_ARRAY }, { JsonReader.PEEKED_SINGLE_QUOTED_NAME, JsonToken.NAME }, { JsonReader.PEEKED_DOUBLE_QUOTED_NAME, JsonToken.NAME }, { JsonReader.PEEKED_UNQUOTED_NAME, JsonToken.NAME }, { JsonReader.PEEKED_TRUE, JsonToken.BOOLEAN }, { JsonReader.PEEKED_FALSE, JsonToken.BOOLEAN }, { JsonReader.PEEKED_NULL, JsonToken.NULL }, { JsonReader.PEEKED_SINGLE_QUOTED, JsonToken.STRING }, { JsonReader.PEEKED_DOUBLE_QUOTED, JsonToken.STRING }, { JsonReader.PEEKED_UNQUOTED, JsonToken.STRING }, { JsonReader.PEEKED_BUFFERED, JsonToken.STRING }, { JsonReader.PEEKED_LONG, JsonToken.NUMBER }, { JsonReader.PEEKED_NUMBER, JsonToken.NUMBER }, { JsonReader.PEEKED_EOF, JsonToken.END_DOCUMENT } };
        for (Object[] testCase : testCases) {
            doReturn((int) testCase[0]).when(spyReader).doPeek();
            assertEquals(testCase[1], spyReader.peek(), "Failed for doPeek() returning value: " + testCase[0]);
        }
    }

    @Test
    void testPeekWhenDoPeekThrowsIOException() throws Exception {
        // Mock doPeek() to throw IOException
        JsonReader spyReader = Mockito.spy(jsonReader);
        doThrow(new IOException()).when(spyReader).doPeek();
        assertThrows(IOException.class, spyReader::peek, "Expected IOException when doPeek() throws IOException.");
    }

    @Test
    void testPeekWhenDoPeekThrowsEOFException() throws Exception {
        // Mock doPeek() to throw EOFException
        JsonReader spyReader = Mockito.spy(jsonReader);
        doThrow(new EOFException()).when(spyReader).doPeek();
        assertThrows(EOFException.class, spyReader::peek, "Expected EOFException when doPeek() throws EOFException.");
    }
}
