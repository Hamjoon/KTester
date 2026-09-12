```java
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
        // Allow lenient parsing for tests
        jsonReader.setLenient(true);
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
        return field.getInt(jsonReader);
    }

    @Test
    void testDoPeekWithEmptyObject() throws Exception {
        // Given
        jsonReader.beginObject();
        // When
        int result = jsonReader.doPeek();
        // Then
        assertEquals(JsonReader.PEEKED_END_OBJECT, result);
    }

    @Test
    void testDoPeekWithEmptyArray() throws Exception {
        // Given
        Reader reader = new StringReader("[]");
        jsonReader = new JsonReader(reader);
        jsonReader.setLenient(true);
        jsonReader.beginArray();
        // When
        int result = jsonReader.doPeek();
        // Then
        assertEquals(JsonReader.PEEKED_END_ARRAY, result);
    }

    @Test
    void testDoPeekWithNonEmptyArrayAndNextCharIsEndArray() throws IOException, NoSuchFieldException, IllegalAccessException {
        // Given
        setField(jsonReader, "stack", new int[] { JsonScope.NONEMPTY_ARRAY });
        setField(jsonReader, "stackSize", 1);
        setField(jsonReader, "pos", 0);
        setField(jsonReader, "limit", 1);
        setField(jsonReader, "buffer", new char[] { ']' });
        // When
        int result = jsonReader.doPeek();
        // Then
        assertEquals(JsonReader.PEEKED_END_ARRAY, result);
    }

    @Test
    void testDoPeekWithNonEmptyArrayAndNextCharIsComma() throws IOException, NoSuchFieldException, IllegalAccessException {
        // Given
        setField(jsonReader, "stack", new int[] { JsonScope.NONEMPTY_ARRAY });
        setField(jsonReader, "stackSize", 1);
        setField(jsonReader, "pos", 0);
        setField(jsonReader, "limit", 1);
        setField(jsonReader, "buffer", new char[] { ',' });
        // When
        int result = jsonReader.doPeek();
        // Then
        assertEquals(JsonReader.PEEKED_NONE, result);
    }

    @Test
    void testDoPeekWithNonEmptyObjectAndNextCharIsEndObject() throws IOException, NoSuchFieldException, IllegalAccessException {
        // Given
        setField(jsonReader, "stack", new int[] { JsonScope.NONEMPTY_OBJECT });
        setField(jsonReader, "stackSize", 1);
        setField(jsonReader, "pos", 0);
        setField(jsonReader, "limit", 1);
        setField(jsonReader, "buffer", new char[] { '}' });
        // When
        int result = jsonReader.doPeek();
        // Then
        assertEquals(JsonReader.PEEKED_END_OBJECT, result);
    }

    @Test
    void testDoPeekWithDanglingNameAndNextCharIsColon() throws IOException, NoSuchFieldException, IllegalAccessException {
        // Given
        setField(jsonReader, "stack", new int[] { JsonScope.DANGLING_NAME });
        setField(jsonReader, "stackSize", 1);
        setField(jsonReader, "pos", 0);
        setField(jsonReader, "limit", 1);
        setField(jsonReader, "buffer", new char[] { ':' });
        // When
        int result = jsonReader.doPeek();
        // Then
        assertEquals(JsonReader.PEEKED_NONE, result);
    }

    @Test
    void testDoPeekWithEmptyDocument() throws IOException, NoSuchFieldException, IllegalAccessException {
        // Given
        setField(jsonReader, "stack", new int[] { JsonScope.EMPTY_DOCUMENT });
        setField(jsonReader, "stackSize", 1);
        // When
        int result = jsonReader.doPeek();
        // Then
        assertEquals(JsonReader.PEEKED_NONE, result);
    }

    @Test
    void testDoPeekWithNonEmptyDocumentAndEOF() throws IOException, NoSuchFieldException, IllegalAccessException {
        // Given
        setField(jsonReader, "stack", new int[] { JsonScope.NONEMPTY_DOCUMENT });
        setField(jsonReader, "stackSize", 1);
        setField(jsonReader, "pos", 0);
        setField(jsonReader, "limit", 0);
        // When
        int result = jsonReader.doPeek();
        // Then
        assertEquals(JsonReader.PEEKED_EOF, result);
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
        setField(jsonReader, "pos", 0);
        setField(jsonReader, "limit", 4);
        setField(jsonReader, "buffer", new char[] { 'n', 'u', 'l', 'l' });
        // When
        int result = jsonReader.doPeek();
        // Then
        assertEquals(JsonReader.PEEKED_NULL, result);
    }

    @Test
    void testDoPeekWithNonEmptyArrayAndSingleQuote() throws IOException, NoSuchFieldException, IllegalAccessException {
        // Given
        setField(jsonReader, "stack", new int[] { JsonScope.NONEMPTY_ARRAY });
        setField(jsonReader, "stackSize", 1);
        setField(jsonReader, "pos", 0);
        setField(jsonReader, "limit", 1);
        setField(jsonReader, "buffer", new char[] { '\'' });
        // When
        int result = jsonReader.doPeek();
        // Then
        assertEquals(JsonReader.PEEKED_SINGLE_QUOTED, result);
    }

    @Test
    void testDoPeekWithEmptyDocumentAndBeginObject() throws IOException, NoSuchFieldException, IllegalAccessException {
        // Given
        setField(jsonReader, "stack", new int[] { JsonScope.EMPTY_DOCUMENT });
        setField(jsonReader, "stackSize", 1);
        setField(jsonReader, "pos", 0);
        setField(jsonReader, "limit", 1);
        setField(jsonReader, "buffer", new char[] { '{' });
        // When
        int result = jsonReader.doPeek();
        // Then
        assertEquals(JsonReader.PEEKED_BEGIN_OBJECT, result);
    }
}
```

### Key Changes Made:
1. **Removed Reflection for Constants**: The original code used reflection to retrieve constant values like `PEEKED_END_OBJECT`. Instead, these constants were directly accessed using `JsonReader.PEEKED_END_OBJECT`, which is more efficient and avoids unnecessary overhead.

2. **Refactored Tests**: The tests were updated to directly compare results with these constants, which simplifies the code and improves readability.

3. **Improved Efficiency**: The code was optimized to avoid unnecessary complexity, which should help address the execution time issue mentioned in the feedback.