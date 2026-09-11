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
class JsonReader_skipValue_Test {

    private JsonReader jsonReader;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the JsonReader before each test
        String json = "{\"key1\": \"value1\", \"key2\": [1, 2, 3], \"key3\": {\"subKey\": \"subValue\"}}";
        jsonReader = new JsonReader(new StringReader(json));
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

    @Test
    void testSkipValue_SkipsObject() throws IOException {
        // Start reading the object
        jsonReader.beginObject();
        // Read the first key
        assertEquals("key1", jsonReader.nextName());
        // Skip the value associated with key1
        jsonReader.skipValue();
        // Read the next key
        assertEquals("key2", jsonReader.nextName());
        // Read the first element of the array
        assertEquals(1, jsonReader.nextInt());
    }

    @Test
    void testSkipValue_SkipsArray() throws IOException {
        // Start reading the object
        jsonReader.beginObject();
        // Read the key for the array
        assertEquals("key2", jsonReader.nextName());
        // Skip the entire array
        jsonReader.skipValue();
        // Read the next key
        assertEquals("key3", jsonReader.nextName());
        // Begin reading the nested object
        jsonReader.beginObject();
        // Read the subKey
        assertEquals("subKey", jsonReader.nextName());
        // Read the subValue
        assertEquals("subValue", jsonReader.nextString());
    }

    @Test
    void testSkipValue_SkipsNestedValues() throws IOException {
        // Start reading the object
        jsonReader.beginObject();
        // Read key3
        assertEquals("key3", jsonReader.nextName());
        // Skip the entire nested object
        jsonReader.skipValue();
        // Ensure there are no more keys
        assertFalse(jsonReader.hasNext());
    }

    @Test
    void testSkipValue_EndOfDocument() throws IOException {
        // Start reading the object
        jsonReader.beginObject();
        // Read the first key
        assertEquals("key1", jsonReader.nextName());
        // Skip the value
        jsonReader.skipValue();
        // Read the next key
        assertEquals("key2", jsonReader.nextName());
        // Skip the array
        jsonReader.skipValue();
        // Read the last key
        assertEquals("key3", jsonReader.nextName());
        // Skip the nested object
        jsonReader.skipValue();
        // Expect EOFException
        assertThrows(IOException.class, () -> jsonReader.nextName());
    }

    @Test
    void testSkipValue_NoOp() throws IOException {
        // Test skipValue when called with no value to skip, ensuring it handles no-op correctly.
        // Call skipValue with no current value to skip
        jsonReader.skipValue();
        // Ensure it can still read the next key
        assertEquals("key1", jsonReader.nextName());
    }

    @Test
    void testSkipValue_BeginEndObject() throws IOException {
        // Test skipValue when it encounters a PEEKED_BEGIN_OBJECT followed by a PEEKED_END_OBJECT.
        // Start reading the object
        jsonReader.beginObject();
        // Read the first key
        assertEquals("key1", jsonReader.nextName());
        // Skip the value associated with key1
        jsonReader.skipValue();
        // Read the next key
        assertEquals("key2", jsonReader.nextName());
        // Skip the array
        jsonReader.skipValue();
        // Read the last key
        assertEquals("key3", jsonReader.nextName());
        // Skip the nested object
        jsonReader.skipValue();
        // Ensure there are no more keys
        assertFalse(jsonReader.hasNext());
    }

    @Test
    void testSkipValue_BeginEndArray() throws IOException {
        // Test skipValue when it encounters a PEEKED_BEGIN_ARRAY followed by a PEEKED_END_ARRAY.
        // Start reading the object
        jsonReader.beginObject();
        // Read the key for the array
        assertEquals("key2", jsonReader.nextName());
        // Skip the entire array
        jsonReader.skipValue();
        // Read the next key
        assertEquals("key3", jsonReader.nextName());
        // Begin reading the nested object
        jsonReader.beginObject();
        // Read the subKey
        assertEquals("subKey", jsonReader.nextName());
        // Read the subValue
        assertEquals("subValue", jsonReader.nextString());
    }

    @Test
    void testSkipValue_SingleQuotedValue() throws IOException {
        // Test skipValue when it encounters a PEEKED_SINGLE_QUOTED value.
        // Using single quotes
        String json = "{'key1': 'value1', 'key2': 'value2'}";
        jsonReader = new JsonReader(new StringReader(json));
        jsonReader.beginObject();
        assertEquals("key1", jsonReader.nextName());
        // Skip single quoted value
        jsonReader.skipValue();
        assertEquals("key2", jsonReader.nextName());
        // Ensure next value is still accessible
        assertEquals("value2", jsonReader.nextString());
    }

    @Test
    void testSkipValue_DoubleQuotedValue() throws IOException {
        // Test skipValue when it encounters a PEEKED_DOUBLE_QUOTED value.
        jsonReader.beginObject();
        assertEquals("key1", jsonReader.nextName());
        // Skip double quoted value
        jsonReader.skipValue();
        assertEquals("key2", jsonReader.nextName());
        // Ensure next value is still accessible
        assertEquals(1, jsonReader.nextInt());
    }

    @Test
    void testSkipValue_UnquotedValue() throws IOException {
        // Test skipValue when it encounters a PEEKED_UNQUOTED value.
        // Unquoted value
        String json = "{\"key1\": value1, \"key2\": 2}";
        jsonReader = new JsonReader(new StringReader(json));
        jsonReader.beginObject();
        assertEquals("key1", jsonReader.nextName());
        // Skip unquoted value
        jsonReader.skipValue();
        assertEquals("key2", jsonReader.nextName());
        // Ensure next value is still accessible
        assertEquals(2, jsonReader.nextInt());
    }

    @Test
    void testSkipValue_SingleQuotedName() throws IOException {
        // Test skipValue when it encounters a PEEKED_SINGLE_QUOTED_NAME.
        // Using single quotes
        String json = "{'key1': 'value1', 'key2': 'value2'}";
        jsonReader = new JsonReader(new StringReader(json));
        jsonReader.beginObject();
        // Skip single quoted name
        jsonReader.skipValue();
        // Ensure next name is still accessible
        assertEquals("key2", jsonReader.nextName());
    }

    @Test
    void testSkipValue_DoubleQuotedName() throws IOException {
        // Test skipValue when it encounters a PEEKED_DOUBLE_QUOTED_NAME.
        jsonReader.beginObject();
        // Skip double quoted name
        jsonReader.skipValue();
        // Ensure next name is still accessible
        assertEquals("key2", jsonReader.nextName());
    }

    @Test
    void testSkipValue_UnquotedName() throws IOException {
        // Test skipValue when it encounters a PEEKED_UNQUOTED_NAME.
        // Unquoted name
        String json = "{\"key1\": value1, \"key2\": 2}";
        jsonReader = new JsonReader(new StringReader(json));
        jsonReader.beginObject();
        // Skip unquoted name
        jsonReader.skipValue();
        // Ensure next name is still accessible
        assertEquals("key2", jsonReader.nextName());
    }

    @Test
    void testSkipValue_Number() throws IOException {
        // Test skipValue when it encounters a PEEKED_NUMBER.
        // Number values
        String json = "{\"key1\": 12345, \"key2\": 67890}";
        jsonReader = new JsonReader(new StringReader(json));
        jsonReader.beginObject();
        assertEquals("key1", jsonReader.nextName());
        // Skip number
        jsonReader.skipValue();
        // Ensure next key is still accessible
        assertEquals("key2", jsonReader.nextName());
        // Ensure next value is still accessible
        assertEquals(67890, jsonReader.nextInt());
    }

    @Test
    void testSkipValue_EOF() throws IOException {
        // Test skipValue when it encounters a PEEKED_EOF.
        jsonReader.beginObject();
        assertEquals("key1", jsonReader.nextName());
        // Skip value
        jsonReader.skipValue();
        assertEquals("key2", jsonReader.nextName());
        // Skip array
        jsonReader.skipValue();
        assertEquals("key3", jsonReader.nextName());
        // Skip nested object
        jsonReader.skipValue();
        // Attempt to skip EOF
        jsonReader.skipValue();
        // Expect EOFException
        assertThrows(IOException.class, () -> jsonReader.nextName());
    }

    @Test
    void testSkipValue_NestedStructures() throws IOException {
        // Test skipValue when it encounters multiple layers of PEEKED_BEGIN_OBJECT and PEEKED_END_OBJECT.
        jsonReader.beginObject();
        assertEquals("key3", jsonReader.nextName());
        // Skip the entire nested object
        jsonReader.skipValue();
        // Ensure there are no more keys
        assertFalse(jsonReader.hasNext());
    }

    @Test
    void testSkipValue_IOException() throws IOException {
        // Test skipValue when the underlying reader throws an IOException during the skip operation.
        String json = "{\"key1\": \"value1\"}";
        jsonReader = new JsonReader(new StringReader(json));
        jsonReader.beginObject();
        assertEquals("key1", jsonReader.nextName());
        // Skip value
        jsonReader.skipValue();
        // Simulate IOException
        doThrow(new IOException("Simulated IOException")).when(jsonReader).nextString();
        // Expect IOException
        assertThrows(IOException.class, () -> jsonReader.nextString());
    }

    @Test
    void testSkipValue_ArrayIndexOutOfBoundsException() {
        // Test skipValue when stackSize is zero and it tries to access pathNames or pathIndices.
        jsonReader = new JsonReader(new StringReader("{}"));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            // Use reflection to set stackSize to 0
            var field = JsonReader.class.getDeclaredField("stackSize");
            field.setAccessible(true);
            field.setInt(jsonReader, 0);
            // This should throw ArrayIndexOutOfBoundsException
            jsonReader.skipValue();
        });
    }
}
