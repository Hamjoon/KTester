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
import java.lang.reflect.Field;

@ExtendWith(MockitoExtension.class)
class JsonReader_skipValue_Test {

    private JsonReader jsonReader;

    @BeforeAll
    static void setupBeforeAll() {
        // Setup resources that are shared across all tests, if any
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the JsonReader with a mock or actual Reader
        Reader reader = new StringReader("{\"a\": [\"one\", \"two\", \"three\"], \"b\": 123}");
        jsonReader = new JsonReader(reader);
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup resources specific to each test
    }

    @AfterAll
    static void teardownAfterAll() {
        // Cleanup shared resources, if any
    }

    @Test
    void testSkipValue() throws IOException {
        jsonReader.beginObject();
        assertEquals("a", jsonReader.nextName());
        jsonReader.skipValue(); // This is the focal method
        assertEquals("b", jsonReader.nextName());
        assertEquals(123, jsonReader.nextInt());
        jsonReader.endObject();
    }

    @Test
    void testSkipValueWhenPeekedIsNone() throws Exception {
        // Set peeked to PEEKED_NONE
        setPrivateField("peeked", jsonReader, JsonReader.PEEKED_NONE);
        
        // Mock doPeek() to return PEEKED_BEGIN_OBJECT
        JsonReader spyReader = spy(jsonReader);
        doReturn(JsonReader.PEEKED_BEGIN_OBJECT).when(spyReader).doPeek();

        // Call skipValue and verify behavior
        spyReader.skipValue();
        verify(spyReader, times(1)).doPeek();
    }

    @Test
    void testSkipValueBeginArray() throws Exception {
        // Set peeked to PEEKED_BEGIN_ARRAY
        setPrivateField("peeked", jsonReader, JsonReader.PEEKED_BEGIN_ARRAY);

        // Call skipValue and assert stackSize increment
        jsonReader.skipValue();
        assertEquals(1, getPrivateField("stackSize", jsonReader));
    }

    @Test
    void testSkipValueBeginObject() throws Exception {
        // Set peeked to PEEKED_BEGIN_OBJECT
        setPrivateField("peeked", jsonReader, JsonReader.PEEKED_BEGIN_OBJECT);

        // Call skipValue and assert stackSize increment
        jsonReader.skipValue();
        assertEquals(1, getPrivateField("stackSize", jsonReader));
    }

    @Test
    void testSkipValueEndArray() throws Exception {
        // Set peeked to PEEKED_END_ARRAY and stackSize to 1
        setPrivateField("peeked", jsonReader, JsonReader.PEEKED_END_ARRAY);
        setPrivateField("stackSize", jsonReader, 1);

        // Call skipValue and assert stackSize decrement
        jsonReader.skipValue();
        assertEquals(0, getPrivateField("stackSize", jsonReader));
    }

    @Test
    void testSkipValueEndObject() throws Exception {
        // Set peeked to PEEKED_END_OBJECT and stackSize to 1
        setPrivateField("peeked", jsonReader, JsonReader.PEEKED_END_OBJECT);
        setPrivateField("stackSize", jsonReader, 1);

        // Call skipValue and assert stackSize decrement
        jsonReader.skipValue();
        assertEquals(0, getPrivateField("stackSize", jsonReader));
    }

    @Test
    void testSkipValueUnquoted() throws Exception {
        // Set peeked to PEEKED_UNQUOTED
        setPrivateField("peeked", jsonReader, JsonReader.PEEKED_UNQUOTED);

        // Spy and verify skipUnquotedValue() is called
        JsonReader spyReader = spy(jsonReader);
        spyReader.skipValue();
        verify(spyReader, times(1)).skipUnquotedValue();
    }

    @Test
    void testSkipValueSingleQuoted() throws Exception {
        // Set peeked to PEEKED_SINGLE_QUOTED
        setPrivateField("peeked", jsonReader, JsonReader.PEEKED_SINGLE_QUOTED);

        // Spy and verify skipQuotedValue('\'') is called
        JsonReader spyReader = spy(jsonReader);
        spyReader.skipValue();
        verify(spyReader, times(1)).skipQuotedValue('\'');
    }

    @Test
    void testSkipValueDoubleQuoted() throws Exception {
        // Set peeked to PEEKED_DOUBLE_QUOTED
        setPrivateField("peeked", jsonReader, JsonReader.PEEKED_DOUBLE_QUOTED);

        // Spy and verify skipQuotedValue('"') is called
        JsonReader spyReader = spy(jsonReader);
        spyReader.skipValue();
        verify(spyReader, times(1)).skipQuotedValue('"');
    }

    @Test
    void testSkipValueUnquotedName() throws Exception {
        // Set peeked to PEEKED_UNQUOTED_NAME
        setPrivateField("peeked", jsonReader, JsonReader.PEEKED_UNQUOTED_NAME);

        // Spy and verify skipUnquotedValue() is called
        JsonReader spyReader = spy(jsonReader);
        spyReader.skipValue();
        verify(spyReader, times(1)).skipUnquotedValue();
    }

    @Test
    void testSkipValueSingleQuotedName() throws Exception {
        // Set peeked to PEEKED_SINGLE_QUOTED_NAME
        setPrivateField("peeked", jsonReader, JsonReader.PEEKED_SINGLE_QUOTED_NAME);

        // Spy and verify skipQuotedValue('\'') is called
        JsonReader spyReader = spy(jsonReader);
        spyReader.skipValue();
        verify(spyReader, times(1)).skipQuotedValue('\'');
    }

    @Test
    void testSkipValueDoubleQuotedName() throws Exception {
        // Set peeked to PEEKED_DOUBLE_QUOTED_NAME
        setPrivateField("peeked", jsonReader, JsonReader.PEEKED_DOUBLE_QUOTED_NAME);

        // Spy and verify skipQuotedValue('"') is called
        JsonReader spyReader = spy(jsonReader);
        spyReader.skipValue();
        verify(spyReader, times(1)).skipQuotedValue('"');
    }

    @Test
    void testSkipValueNumber() throws Exception {
        // Set peeked to PEEKED_NUMBER and peekedNumberLength
        setPrivateField("peeked", jsonReader, JsonReader.PEEKED_NUMBER);
        setPrivateField("peekedNumberLength", jsonReader, 5);

        // Call skipValue and assert pos increment
        jsonReader.skipValue();
        assertEquals(5, getPrivateField("pos", jsonReader));
    }

    @Test
    void testSkipValueEOF() throws Exception {
        // Set peeked to PEEKED_EOF
        setPrivateField("peeked", jsonReader, JsonReader.PEEKED_EOF);

        // Call skipValue and ensure no exceptions
        jsonReader.skipValue();
    }

    @Test
    void testSkipValueEndObjectException() {
        // Set peeked to PEEKED_END_OBJECT and stackSize to 0
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            setPrivateField("peeked", jsonReader, JsonReader.PEEKED_END_OBJECT);
            setPrivateField("stackSize", jsonReader, 0);
            jsonReader.skipValue();
        });
    }

    @Test
    void testSkipValueEndArrayException() {
        // Set peeked to PEEKED_END_ARRAY and stackSize to 0
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            setPrivateField("peeked", jsonReader, JsonReader.PEEKED_END_ARRAY);
            setPrivateField("stackSize", jsonReader, 0);
            jsonReader.skipValue();
        });
    }

    @Test
    void testSkipValueEndObjectNullPointerException() {
        // Set peeked to PEEKED_END_OBJECT, stackSize to 1, and count to 0
        assertThrows(NullPointerException.class, () -> {
            setPrivateField("peeked", jsonReader, JsonReader.PEEKED_END_OBJECT);
            setPrivateField("stackSize", jsonReader, 1);
            setPrivateField("pathNames", jsonReader, new String[32]);
            jsonReader.skipValue();
        });
    }

    // Utility methods to set and get private fields using reflection
    private void setPrivateField(String fieldName, Object instance, Object value) throws Exception {
        Field field = JsonReader.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(instance, value);
    }

    private Object getPrivateField(String fieldName, Object instance) throws Exception {
        Field field = JsonReader.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(instance);
    }
}
```