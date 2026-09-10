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

@ExtendWith(MockitoExtension.class)
class JsonReader_skipValue_Test {

    private JsonReader jsonReader;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize necessary fields and dependencies before each test
        jsonReader = new JsonReader(new StringReader("{\"a\": [\"one\", \"two\"], \"b\": 123}"));
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup after each test if necessary
        try {
            jsonReader.close();
        } catch (IOException e) {
            fail("Failed to close JsonReader: " + e.getMessage());
        }
    }

    private void setPeekedValue(int value) throws Exception {
        Field peekedField = JsonReader.class.getDeclaredField("peeked");
        peekedField.setAccessible(true);
        peekedField.setInt(jsonReader, value);
    }

    private void setPathIndices(int value) throws Exception {
        Field stackSizeField = JsonReader.class.getDeclaredField("stackSize");
        stackSizeField.setAccessible(true);
        stackSizeField.setInt(jsonReader, value);
        Field pathIndicesField = JsonReader.class.getDeclaredField("pathIndices");
        pathIndicesField.setAccessible(true);
        int[] pathIndices = (int[]) pathIndicesField.get(jsonReader);
        // Update the first index
        pathIndices[0] = value;
    }

    @Test
    void testSkipValue_SkipsArray() throws IOException {
        jsonReader.beginObject();
        assertEquals("a", jsonReader.nextName());
        // This should skip the array
        jsonReader.skipValue();
        assertEquals("b", jsonReader.nextName());
        assertEquals(123, jsonReader.nextInt());
        jsonReader.endObject();
    }

    @Test
    void testSkipValue_SkipsObject() throws IOException {
        jsonReader = new JsonReader(new StringReader("{\"a\": {\"key\": \"value\"}, \"b\": 456}"));
        jsonReader.beginObject();
        assertEquals("a", jsonReader.nextName());
        // This should skip the object
        jsonReader.skipValue();
        assertEquals("b", jsonReader.nextName());
        assertEquals(456, jsonReader.nextInt());
        jsonReader.endObject();
    }

    @Test
    void testSkipValue_SkipsNestedValues() throws IOException {
        jsonReader = new JsonReader(new StringReader("{\"a\": [1, 2, 3], \"b\": {\"c\": 4}}"));
        jsonReader.beginObject();
        assertEquals("a", jsonReader.nextName());
        // This should skip the array
        jsonReader.skipValue();
        assertEquals("b", jsonReader.nextName());
        // This should skip the object
        jsonReader.skipValue();
        jsonReader.endObject();
    }

    @Test
    void testSkipValue_BasicCases() throws Exception {
        // Test when peeked is PEEKED_NONE
        // Simulate PEEKED_NONE
        setPeekedValue(0);
        // Should call doPeek and proceed without exceptions
        jsonReader.skipValue();
        // Test when peeked is PEEKED_BEGIN_ARRAY
        // Simulate PEEKED_BEGIN_ARRAY
        setPeekedValue(1);
        // Should push new state and increment count
        jsonReader.skipValue();
        // Test when peeked is PEEKED_BEGIN_OBJECT
        // Simulate PEEKED_BEGIN_OBJECT
        setPeekedValue(3);
        // Should push new state and increment count
        jsonReader.skipValue();
        // Test when peeked is PEEKED_END_ARRAY
        // Simulate PEEKED_END_ARRAY
        setPeekedValue(4);
        // Should decrement stack size and count
        jsonReader.skipValue();
        // Test when peeked is PEEKED_END_OBJECT
        // Simulate PEEKED_END_OBJECT
        setPeekedValue(2);
        // Should decrement stack size and count
        jsonReader.skipValue();
    }

    @Test
    void testSkipValue_QuotedAndUnquotedValues() throws Exception {
        // Test when peeked is PEEKED_UNQUOTED
        // Simulate PEEKED_UNQUOTED
        setPeekedValue(10);
        // Should call skipUnquotedValue without exceptions
        jsonReader.skipValue();
        // Test when peeked is PEEKED_SINGLE_QUOTED
        // Simulate PEEKED_SINGLE_QUOTED
        setPeekedValue(8);
        // Should call skipQuotedValue with single quote
        jsonReader.skipValue();
        // Test when peeked is PEEKED_DOUBLE_QUOTED
        // Simulate PEEKED_DOUBLE_QUOTED
        setPeekedValue(9);
        // Should call skipQuotedValue with double quote
        jsonReader.skipValue();
        // Test when peeked is PEEKED_UNQUOTED_NAME
        // Simulate PEEKED_UNQUOTED_NAME
        setPeekedValue(14);
        // Should call skipUnquotedValue and update pathNames
        jsonReader.skipValue();
        // Test when peeked is PEEKED_SINGLE_QUOTED_NAME
        // Simulate PEEKED_SINGLE_QUOTED_NAME
        setPeekedValue(12);
        // Should call skipQuotedValue with single quote and update pathNames
        jsonReader.skipValue();
        // Test when peeked is PEEKED_DOUBLE_QUOTED_NAME
        // Simulate PEEKED_DOUBLE_QUOTED_NAME
        setPeekedValue(13);
        // Should call skipQuotedValue with double quote and update pathNames
        jsonReader.skipValue();
    }

    @Test
    void testSkipValue_NumbersAndEOF() throws Exception {
        // Test when peeked is PEEKED_NUMBER
        // Simulate PEEKED_NUMBER
        setPeekedValue(16);
        // Should update pos with peekedNumberLength
        jsonReader.skipValue();
        // Test when peeked is PEEKED_EOF
        // Simulate PEEKED_EOF
        setPeekedValue(17);
        // Should exit the method without any changes
        jsonReader.skipValue();
    }

    @Test
    void testSkipValue_EdgeCases() throws Exception {
        // Test when count is 0 and peeked is PEEKED_END_OBJECT
        // Simulate PEEKED_END_OBJECT
        setPeekedValue(2);
        // Set stack size to 1
        setPathIndices(0);
        // Should set last pathName to null
        jsonReader.skipValue();
        // Test when count is 0 and peeked is PEEKED_UNQUOTED_NAME
        // Simulate PEEKED_UNQUOTED_NAME
        setPeekedValue(14);
        // Set stack size to 1
        setPathIndices(0);
        // Should set last pathName to '<skipped>'
        jsonReader.skipValue();
        // Test when count is 0 and peeked is PEEKED_SINGLE_QUOTED_NAME
        // Simulate PEEKED_SINGLE_QUOTED_NAME
        setPeekedValue(12);
        // Set stack size to 1
        setPathIndices(0);
        // Should set last pathName to '<skipped>'
        jsonReader.skipValue();
        // Test when count is 0 and peeked is PEEKED_DOUBLE_QUOTED_NAME
        // Simulate PEEKED_DOUBLE_QUOTED_NAME
        setPeekedValue(13);
        // Set stack size to 1
        setPathIndices(0);
        // Should set last pathName to '<skipped>'
        jsonReader.skipValue();
    }

    @Test
    void testSkipValue_ExceptionScenarios() throws Exception {
        // Test for IOException during doPeek
        // Simulate PEEKED_NONE
        setPeekedValue(0);
        doThrow(new IOException("Test Exception")).when(jsonReader).doPeek();
        assertThrows(IOException.class, () -> jsonReader.skipValue());
        // Test for IOException during skipUnquotedValue
        // Simulate PEEKED_UNQUOTED
        setPeekedValue(10);
        doThrow(new IOException("Test Exception")).when(jsonReader).skipUnquotedValue();
        assertThrows(IOException.class, () -> jsonReader.skipValue());
        // Test for IOException during skipQuotedValue
        // Simulate PEEKED_DOUBLE_QUOTED
        setPeekedValue(9);
        doThrow(new IOException("Test Exception")).when(jsonReader).skipQuotedValue(anyChar());
        assertThrows(IOException.class, () -> jsonReader.skipValue());
    }
}
