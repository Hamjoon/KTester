```java
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

    @BeforeAll
    static void setupBeforeAll() {
        // Any global setup can be done here
    }

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

    @AfterAll
    static void teardownAfterAll() {
        // Any global cleanup can be done here
    }

    @Test
    void testSkipValue_NoOp() throws IOException {
        // Test skipValue when called with no value to skip, ensuring it handles no-op correctly.
        jsonReader.skipValue(); // Call skipValue with no current value to skip
        assertEquals("key1", jsonReader.nextName()); // Ensure it can still read the next key
    }

    @Test
    void testSkipValue_BeginEndObject() throws IOException {
        // Test skipValue when it encounters a PEEKED_BEGIN_OBJECT followed by a PEEKED_END_OBJECT.
        jsonReader.beginObject(); // Start reading the object
        assertEquals("key1", jsonReader.nextName()); // Read the first key
        jsonReader.skipValue(); // Skip the value associated with key1
        assertEquals("key2", jsonReader.nextName()); // Read the next key
        jsonReader.skipValue(); // Skip the array
        assertEquals("key3", jsonReader.nextName()); // Read the last key
        jsonReader.skipValue(); // Skip the nested object
        assertFalse(jsonReader.hasNext()); // Ensure there are no more keys
    }

    @Test
    void testSkipValue_BeginEndArray() throws IOException {
        // Test skipValue when it encounters a PEEKED_BEGIN_ARRAY followed by a PEEKED_END_ARRAY.
        jsonReader.beginObject(); // Start reading the object
        assertEquals("key2", jsonReader.nextName()); // Read the key for the array
        jsonReader.skipValue(); // Skip the entire array
        assertEquals("key3", jsonReader.nextName()); // Read the next key
        jsonReader.beginObject(); // Begin reading the nested object
        assertEquals("subKey", jsonReader.nextName()); // Read the subKey
        assertEquals("subValue", jsonReader.nextString()); // Read the subValue
    }

    @Test
    void testSkipValue_SingleQuotedValue() throws IOException {
        // Test skipValue when it encounters a PEEKED_SINGLE_QUOTED value.
        String json = "{'key1': 'value1', 'key2': 'value2'}"; // Using single quotes
        jsonReader = new JsonReader(new StringReader(json));
        jsonReader.beginObject();
        assertEquals("key1", jsonReader.nextName());
        jsonReader.skipValue(); // Skip single quoted value
        assertEquals("key2", jsonReader.nextName());
        assertEquals("value2", jsonReader.nextString()); // Ensure next value is still accessible
    }

    @Test
    void testSkipValue_DoubleQuotedValue() throws IOException {
        // Test skipValue when it encounters a PEEKED_DOUBLE_QUOTED value.
        jsonReader.beginObject();
        assertEquals("key1", jsonReader.nextName());
        jsonReader.skipValue(); // Skip double quoted value
        assertEquals("key2", jsonReader.nextName());
        assertEquals(1, jsonReader.nextInt()); // Ensure next value is still accessible
    }

    @Test
    void testSkipValue_UnquotedValue() throws IOException {
        // Test skipValue when it encounters a PEEKED_UNQUOTED value.
        String json = "{\"key1\": value1, \"key2\": 2}"; // Unquoted value
        jsonReader = new JsonReader(new StringReader(json));
        jsonReader.beginObject();
        assertEquals("key1", jsonReader.nextName());
        jsonReader.skipValue(); // Skip unquoted value
        assertEquals("key2", jsonReader.nextName());
        assertEquals(2, jsonReader.nextInt()); // Ensure next value is still accessible
    }

    @Test
    void testSkipValue_SingleQuotedName() throws IOException {
        // Test skipValue when it encounters a PEEKED_SINGLE_QUOTED_NAME.
        String json = "{'key1': 'value1', 'key2': 'value2'}"; // Using single quotes
        jsonReader = new JsonReader(new StringReader(json));
        jsonReader.beginObject();
        jsonReader.skipValue(); // Skip single quoted name
        assertEquals("key2", jsonReader.nextName()); // Ensure next name is still accessible
    }

    @Test
    void testSkipValue_DoubleQuotedName() throws IOException {
        // Test skipValue when it encounters a PEEKED_DOUBLE_QUOTED_NAME.
        jsonReader.beginObject();
        jsonReader.skipValue(); // Skip double quoted name
        assertEquals("key2", jsonReader.nextName()); // Ensure next name is still accessible
    }

    @Test
    void testSkipValue_UnquotedName() throws IOException {
        // Test skipValue when it encounters a PEEKED_UNQUOTED_NAME.
        String json = "{\"key1\": value1, \"key2\": 2}"; // Unquoted name
        jsonReader = new JsonReader(new StringReader(json));
        jsonReader.beginObject();
        jsonReader.skipValue(); // Skip unquoted name
        assertEquals("key2", jsonReader.nextName()); // Ensure next name is still accessible
    }

    @Test
    void testSkipValue_Number() throws IOException {
        // Test skipValue when it encounters a PEEKED_NUMBER.
        String json = "{\"key1\": 12345, \"key2\": 67890}"; // Number values
        jsonReader = new JsonReader(new StringReader(json));
        jsonReader.beginObject();
        assertEquals("key1", jsonReader.nextName());
        jsonReader.skipValue(); // Skip number
        assertEquals("key2", jsonReader.nextName()); // Ensure next key is still accessible
        assertEquals(67890, jsonReader.nextInt()); // Ensure next value is still accessible
    }

    @Test
    void testSkipValue_EOF() throws IOException {
        // Test skipValue when it encounters a PEEKED_EOF.
        jsonReader.beginObject();
        assertEquals("key1", jsonReader.nextName());
        jsonReader.skipValue(); // Skip value
        assertEquals("key2", jsonReader.nextName());
        jsonReader.skipValue(); // Skip array
        assertEquals("key3", jsonReader.nextName());
        jsonReader.skipValue(); // Skip nested object
        jsonReader.skipValue(); // Attempt to skip EOF
        assertThrows(IOException.class, () -> jsonReader.nextName()); // Expect EOFException
    }

    @Test
    void testSkipValue_NestedStructures() throws IOException {
        // Test skipValue when it encounters multiple layers of PEEKED_BEGIN_OBJECT and PEEKED_END_OBJECT.
        jsonReader.beginObject();
        assertEquals("key3", jsonReader.nextName());
        jsonReader.skipValue(); // Skip the entire nested object
        assertFalse(jsonReader.hasNext()); // Ensure there are no more keys
    }

    @Test
    void testSkipValue_IOException() throws IOException {
        // Test skipValue when the underlying reader throws an IOException during the skip operation.
        String json = "{\"key1\": \"value1\"}";
        jsonReader = new JsonReader(new StringReader(json));
        jsonReader.beginObject();
        assertEquals("key1", jsonReader.nextName());
        jsonReader.skipValue(); // Skip value
        // Simulate IOException
        doThrow(new IOException("Simulated IOException")).when(jsonReader).nextString();
        assertThrows(IOException.class, () -> jsonReader.nextString()); // Expect IOException
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
            jsonReader.skipValue(); // This should throw ArrayIndexOutOfBoundsException
        });
    }
}
```