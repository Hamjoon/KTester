package com.google.gson.stream;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Field;

class JsonReader_doPeek_Test {

    private JsonReader jsonReader;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize JsonReader with a StringReader for test cases
        // Example JSON input
        Reader reader = new StringReader("{}");
        jsonReader = new JsonReader(reader);
    }

    @AfterEach
    void teardownAfterEach() {
        // Perform any cleanup after each test
        try {
            jsonReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method to set private fields using reflection
    private void setField(Object obj, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = JsonReader.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }

    // Helper method to get private fields using reflection
    private int getPrivateField(String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = JsonReader.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.getInt(null);
    }

    @Test
    void testDoPeekWithEmptyObject() throws IOException {
        // Given
        jsonReader.beginObject();
        // When
        int result = jsonReader.doPeek();
        // Then
        assertEquals(getPrivateField("PEEKED_END_OBJECT"), result);
    }

    @Test
    void testDoPeekWithEmptyArray() throws IOException {
        // Given
        Reader reader = new StringReader("[]");
        jsonReader = new JsonReader(reader);
        jsonReader.beginArray();
        // When
        int result = jsonReader.doPeek();
        // Then
        assertEquals(getPrivateField("PEEKED_END_ARRAY"), result);
    }

    @Test
    void testDoPeekWithNonEmptyArrayAndNextCharIsEndArray() throws IOException, NoSuchFieldException, IllegalAccessException {
        // Given
        setField(jsonReader, "stack", new int[] { JsonScope.NONEMPTY_ARRAY });
        setField(jsonReader, "stackSize", 1);
        setField(jsonReader, "pos", 1);
        setField(jsonReader, "limit", 2);
        setField(jsonReader, "buffer", new char[] { ']' });
        // When
        int result = jsonReader.doPeek();
        // Then
        assertEquals(getPrivateField("PEEKED_END_ARRAY"), result);
    }

    @Test
    void testDoPeekWithNonEmptyArrayAndNextCharIsComma() throws IOException, NoSuchFieldException, IllegalAccessException {
        // Given
        setField(jsonReader, "stack", new int[] { JsonScope.NONEMPTY_ARRAY });
        setField(jsonReader, "stackSize", 1);
        setField(jsonReader, "pos", 1);
        setField(jsonReader, "limit", 2);
        setField(jsonReader, "buffer", new char[] { ',' });
        // When
        int result = jsonReader.doPeek();
        // Then
        assertEquals(getPrivateField("PEEKED_NONE"), result);
    }

    @Test
    void testDoPeekWithNonEmptyObjectAndNextCharIsEndObject() throws IOException, NoSuchFieldException, IllegalAccessException {
        // Given
        setField(jsonReader, "stack", new int[] { JsonScope.NONEMPTY_OBJECT });
        setField(jsonReader, "stackSize", 1);
        setField(jsonReader, "pos", 1);
        setField(jsonReader, "limit", 2);
        setField(jsonReader, "buffer", new char[] { '}' });
        // When
        int result = jsonReader.doPeek();
        // Then
        assertEquals(getPrivateField("PEEKED_END_OBJECT"), result);
    }

    @Test
    void testDoPeekWithDanglingNameAndNextCharIsColon() throws IOException, NoSuchFieldException, IllegalAccessException {
        // Given
        setField(jsonReader, "stack", new int[] { JsonScope.DANGLING_NAME });
        setField(jsonReader, "stackSize", 1);
        setField(jsonReader, "pos", 1);
        setField(jsonReader, "limit", 2);
        setField(jsonReader, "buffer", new char[] { ':' });
        // When
        int result = jsonReader.doPeek();
        // Then
        assertEquals(getPrivateField("PEEKED_NONE"), result);
    }

    @Test
    void testDoPeekWithEmptyDocument() throws IOException, NoSuchFieldException, IllegalAccessException {
        // Given
        setField(jsonReader, "stack", new int[] { JsonScope.EMPTY_DOCUMENT });
        setField(jsonReader, "stackSize", 1);
        // When
        int result = jsonReader.doPeek();
        // Then
        assertEquals(getPrivateField("PEEKED_NONE"), result);
    }

    @Test
    void testDoPeekWithNonEmptyDocumentAndEOF() throws IOException, NoSuchFieldException, IllegalAccessException {
        // Given
        setField(jsonReader, "stack", new int[] { JsonScope.NONEMPTY_DOCUMENT });
        setField(jsonReader, "stackSize", 1);
        setField(jsonReader, "pos", 1);
        setField(jsonReader, "limit", 1);
        // When
        int result = jsonReader.doPeek();
        // Then
        assertEquals(getPrivateField("PEEKED_EOF"), result);
    }

    @Test
    void testDoPeekWithClosed() throws IOException, NoSuchFieldException, IllegalAccessException {
        // Given
        setField(jsonReader, "stack", new int[] { JsonScope.CLOSED });
        setField(jsonReader, "stackSize", 1);
        // When / Then
        assertThrows(IllegalStateException.class, () -> jsonReader.doPeek());
    }

    @Test
    void testDoPeekWithEmptyArrayAndNullLiteral() throws IOException, NoSuchFieldException, IllegalAccessException {
        // Given
        setField(jsonReader, "stack", new int[] { JsonScope.EMPTY_ARRAY });
        setField(jsonReader, "stackSize", 1);
        setField(jsonReader, "pos", 1);
        setField(jsonReader, "limit", 5);
        setField(jsonReader, "buffer", new char[] { 'n', 'u', 'l', 'l' });
        // When
        int result = jsonReader.doPeek();
        // Then
        assertEquals(getPrivateField("PEEKED_NULL"), result);
    }

    @Test
    void testDoPeekWithNonEmptyArrayAndSingleQuote() throws IOException, NoSuchFieldException, IllegalAccessException {
        // Given
        setField(jsonReader, "stack", new int[] { JsonScope.NONEMPTY_ARRAY });
        setField(jsonReader, "stackSize", 1);
        setField(jsonReader, "pos", 1);
        setField(jsonReader, "limit", 2);
        setField(jsonReader, "buffer", new char[] { '\'' });
        // When
        int result = jsonReader.doPeek();
        // Then
        assertEquals(getPrivateField("PEEKED_SINGLE_QUOTED"), result);
    }

    @Test
    void testDoPeekWithEmptyDocumentAndBeginObject() throws IOException, NoSuchFieldException, IllegalAccessException {
        // Given
        setField(jsonReader, "stack", new int[] { JsonScope.EMPTY_DOCUMENT });
        setField(jsonReader, "stackSize", 1);
        setField(jsonReader, "pos", 1);
        setField(jsonReader, "limit", 2);
        setField(jsonReader, "buffer", new char[] { '{' });
        // When
        int result = jsonReader.doPeek();
        // Then
        assertEquals(getPrivateField("PEEKED_BEGIN_OBJECT"), result);
    }
}
