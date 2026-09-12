package com.google.gson.stream;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.io.EOFException;

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

    private int getPeekedConstant(String fieldName) throws Exception {
        Field field = JsonReader.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.getInt(null);
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
        Object[][] testCases = { { "PEEKED_BEGIN_OBJECT", JsonToken.BEGIN_OBJECT }, { "PEEKED_END_OBJECT", JsonToken.END_OBJECT }, { "PEEKED_BEGIN_ARRAY", JsonToken.BEGIN_ARRAY }, { "PEEKED_END_ARRAY", JsonToken.END_ARRAY }, { "PEEKED_SINGLE_QUOTED_NAME", JsonToken.NAME }, { "PEEKED_DOUBLE_QUOTED_NAME", JsonToken.NAME }, { "PEEKED_UNQUOTED_NAME", JsonToken.NAME }, { "PEEKED_TRUE", JsonToken.BOOLEAN }, { "PEEKED_FALSE", JsonToken.BOOLEAN }, { "PEEKED_NULL", JsonToken.NULL }, { "PEEKED_SINGLE_QUOTED", JsonToken.STRING }, { "PEEKED_DOUBLE_QUOTED", JsonToken.STRING }, { "PEEKED_UNQUOTED", JsonToken.STRING }, { "PEEKED_BUFFERED", JsonToken.STRING }, { "PEEKED_LONG", JsonToken.NUMBER }, { "PEEKED_NUMBER", JsonToken.NUMBER }, { "PEEKED_EOF", JsonToken.END_DOCUMENT } };
        for (Object[] testCase : testCases) {
            setPeekedValue(getPeekedConstant((String) testCase[0]));
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
        Object[][] testCases = { { "PEEKED_BEGIN_OBJECT", JsonToken.BEGIN_OBJECT }, { "PEEKED_END_OBJECT", JsonToken.END_OBJECT }, { "PEEKED_BEGIN_ARRAY", JsonToken.BEGIN_ARRAY }, { "PEEKED_END_ARRAY", JsonToken.END_ARRAY }, { "PEEKED_SINGLE_QUOTED_NAME", JsonToken.NAME }, { "PEEKED_DOUBLE_QUOTED_NAME", JsonToken.NAME }, { "PEEKED_UNQUOTED_NAME", JsonToken.NAME }, { "PEEKED_TRUE", JsonToken.BOOLEAN }, { "PEEKED_FALSE", JsonToken.BOOLEAN }, { "PEEKED_NULL", JsonToken.NULL }, { "PEEKED_SINGLE_QUOTED", JsonToken.STRING }, { "PEEKED_DOUBLE_QUOTED", JsonToken.STRING }, { "PEEKED_UNQUOTED", JsonToken.STRING }, { "PEEKED_BUFFERED", JsonToken.STRING }, { "PEEKED_LONG", JsonToken.NUMBER }, { "PEEKED_NUMBER", JsonToken.NUMBER }, { "PEEKED_EOF", JsonToken.END_DOCUMENT } };
        for (Object[] testCase : testCases) {
            doReturn(getPeekedConstant((String) testCase[0])).when(spyReader).doPeek();
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
