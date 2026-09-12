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
import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class JsonReader_skipValue_Test {

    private JsonReader jsonReader;

    private Reader reader;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the JsonReader with an actual Reader
        reader = new StringReader("{\"a\": [\"one\", \"two\", \"three\"], \"b\": 123}");
        jsonReader = new JsonReader(reader);
    }

    @AfterEach
    void tearDownAfterEach() throws IOException {
        // Close the reader to release resources
        if (reader != null) {
            reader.close();
        }
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

    private Object invokePrivateMethod(String methodName, Object instance, Class<?>[] parameterTypes, Object... args) throws Exception {
        Method method = JsonReader.class.getDeclaredMethod(methodName, parameterTypes);
        method.setAccessible(true);
        return method.invoke(instance, args);
    }

    @Test
    void testSkipValue() throws IOException {
        jsonReader.beginObject();
        assertEquals("a", jsonReader.nextName());
        // This is the focal method
        jsonReader.skipValue();
        assertEquals("b", jsonReader.nextName());
        assertEquals(123, jsonReader.nextInt());
        jsonReader.endObject();
    }

    @Test
    void testSkipValueWhenPeekedIsNone() throws Exception {
        // Set peeked to PEEKED_NONE
        setPrivateField("peeked", jsonReader, getPrivateField("PEEKED_NONE", jsonReader));
        // Mock doPeek() to return PEEKED_BEGIN_OBJECT
        JsonReader spyReader = spy(jsonReader);
        doReturn(getPrivateField("PEEKED_BEGIN_OBJECT", jsonReader)).when(spyReader).doPeek();
        // Call skipValue and verify behavior
        spyReader.skipValue();
        verify(spyReader, times(1)).doPeek();
    }

    @Test
    void testSkipValueBeginArray() throws Exception {
        // Set peeked to PEEKED_BEGIN_ARRAY
        setPrivateField("peeked", jsonReader, getPrivateField("PEEKED_BEGIN_ARRAY", jsonReader));
        // Call skipValue and assert stackSize increment
        jsonReader.skipValue();
        assertEquals(1, getPrivateField("stackSize", jsonReader));
    }

    @Test
    void testSkipValueBeginObject() throws Exception {
        // Set peeked to PEEKED_BEGIN_OBJECT
        setPrivateField("peeked", jsonReader, getPrivateField("PEEKED_BEGIN_OBJECT", jsonReader));
        // Call skipValue and assert stackSize increment
        jsonReader.skipValue();
        assertEquals(1, getPrivateField("stackSize", jsonReader));
    }

    @Test
    void testSkipValueEndArray() throws Exception {
        // Set peeked to PEEKED_END_ARRAY and stackSize to 1
        setPrivateField("peeked", jsonReader, getPrivateField("PEEKED_END_ARRAY", jsonReader));
        setPrivateField("stackSize", jsonReader, 1);
        // Call skipValue and assert stackSize decrement
        jsonReader.skipValue();
        assertEquals(0, getPrivateField("stackSize", jsonReader));
    }

    @Test
    void testSkipValueEndObject() throws Exception {
        // Set peeked to PEEKED_END_OBJECT and stackSize to 1
        setPrivateField("peeked", jsonReader, getPrivateField("PEEKED_END_OBJECT", jsonReader));
        setPrivateField("stackSize", jsonReader, 1);
        // Call skipValue and assert stackSize decrement
        jsonReader.skipValue();
        assertEquals(0, getPrivateField("stackSize", jsonReader));
    }

    @Test
    void testSkipValueUnquoted() throws Exception {
        // Set peeked to PEEKED_UNQUOTED
        setPrivateField("peeked", jsonReader, getPrivateField("PEEKED_UNQUOTED", jsonReader));
        // Spy and verify skipUnquotedValue() is called
        JsonReader spyReader = spy(jsonReader);
        invokePrivateMethod("skipUnquotedValue", spyReader, new Class<?>[] {});
        verify(spyReader, times(1)).skipValue();
    }

    @Test
    void testSkipValueSingleQuoted() throws Exception {
        // Set peeked to PEEKED_SINGLE_QUOTED
        setPrivateField("peeked", jsonReader, getPrivateField("PEEKED_SINGLE_QUOTED", jsonReader));
        // Spy and verify skipQuotedValue('\'') is called
        JsonReader spyReader = spy(jsonReader);
        invokePrivateMethod("skipQuotedValue", spyReader, new Class<?>[] { char.class }, '\'');
        verify(spyReader, times(1)).skipValue();
    }

    @Test
    void testSkipValueDoubleQuoted() throws Exception {
        // Set peeked to PEEKED_DOUBLE_QUOTED
        setPrivateField("peeked", jsonReader, getPrivateField("PEEKED_DOUBLE_QUOTED", jsonReader));
        // Spy and verify skipQuotedValue('"') is called
        JsonReader spyReader = spy(jsonReader);
        invokePrivateMethod("skipQuotedValue", spyReader, new Class<?>[] { char.class }, '"');
        verify(spyReader, times(1)).skipValue();
    }

    @Test
    void testSkipValueUnquotedName() throws Exception {
        // Set peeked to PEEKED_UNQUOTED_NAME
        setPrivateField("peeked", jsonReader, getPrivateField("PEEKED_UNQUOTED_NAME", jsonReader));
        // Spy and verify skipUnquotedValue() is called
        JsonReader spyReader = spy(jsonReader);
        invokePrivateMethod("skipUnquotedValue", spyReader, new Class<?>[] {});
        verify(spyReader, times(1)).skipValue();
    }

    @Test
    void testSkipValueSingleQuotedName() throws Exception {
        // Set peeked to PEEKED_SINGLE_QUOTED_NAME
        setPrivateField("peeked", jsonReader, getPrivateField("PEEKED_SINGLE_QUOTED_NAME", jsonReader));
        // Spy and verify skipQuotedValue('\'') is called
        JsonReader spyReader = spy(jsonReader);
        invokePrivateMethod("skipQuotedValue", spyReader, new Class<?>[] { char.class }, '\'');
        verify(spyReader, times(1)).skipValue();
    }

    @Test
    void testSkipValueDoubleQuotedName() throws Exception {
        // Set peeked to PEEKED_DOUBLE_QUOTED_NAME
        setPrivateField("peeked", jsonReader, getPrivateField("PEEKED_DOUBLE_QUOTED_NAME", jsonReader));
        // Spy and verify skipQuotedValue('"') is called
        JsonReader spyReader = spy(jsonReader);
        invokePrivateMethod("skipQuotedValue", spyReader, new Class<?>[] { char.class }, '"');
        verify(spyReader, times(1)).skipValue();
    }

    @Test
    void testSkipValueNumber() throws Exception {
        // Set peeked to PEEKED_NUMBER and peekedNumberLength
        setPrivateField("peeked", jsonReader, getPrivateField("PEEKED_NUMBER", jsonReader));
        setPrivateField("peekedNumberLength", jsonReader, 5);
        // Call skipValue and assert pos increment
        jsonReader.skipValue();
        assertEquals(5, getPrivateField("pos", jsonReader));
    }

    @Test
    void testSkipValueEOF() throws Exception {
        // Set peeked to PEEKED_EOF
        setPrivateField("peeked", jsonReader, getPrivateField("PEEKED_EOF", jsonReader));
        // Call skipValue and ensure no exceptions
        jsonReader.skipValue();
    }

    @Test
    void testSkipValueEndObjectException() {
        // Set peeked to PEEKED_END_OBJECT and stackSize to 0
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            setPrivateField("peeked", jsonReader, getPrivateField("PEEKED_END_OBJECT", jsonReader));
            setPrivateField("stackSize", jsonReader, 0);
            jsonReader.skipValue();
        });
    }

    @Test
    void testSkipValueEndArrayException() {
        // Set peeked to PEEKED_END_ARRAY and stackSize to 0
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            setPrivateField("peeked", jsonReader, getPrivateField("PEEKED_END_ARRAY", jsonReader));
            setPrivateField("stackSize", jsonReader, 0);
            jsonReader.skipValue();
        });
    }

    @Test
    void testSkipValueEndObjectNullPointerException() {
        // Set peeked to PEEKED_END_OBJECT, stackSize to 1, and count to 0
        assertThrows(NullPointerException.class, () -> {
            setPrivateField("peeked", jsonReader, getPrivateField("PEEKED_END_OBJECT", jsonReader));
            setPrivateField("stackSize", jsonReader, 1);
            setPrivateField("pathNames", jsonReader, new String[32]);
            jsonReader.skipValue();
        });
    }
}
