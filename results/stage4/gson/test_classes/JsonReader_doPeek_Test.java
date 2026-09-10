package com.google.gson.stream;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import java.io.StringReader;
import java.io.Reader;
import java.io.IOException;
import org.mockito.junit.jupiter.MockitoExtension;
import java.lang.reflect.Field;

@ExtendWith(MockitoExtension.class)
class JsonReader_doPeek_Test {

    private JsonReader jsonReader;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the JsonReader with a sample JSON input
        Reader reader = new StringReader("{\"key\":\"value\"}");
        jsonReader = new JsonReader(reader);
    }

    private int getPrivatePeekedConstant(String constantName) throws Exception {
        Field field = JsonReader.class.getDeclaredField(constantName);
        field.setAccessible(true);
        return field.getInt(null);
    }

    @Test
    void testDoPeekBeginObject() throws Exception {
        jsonReader.beginObject();
        assertEquals(getPrivatePeekedConstant("PEEKED_BEGIN_OBJECT"), jsonReader.peeked);
    }

    @Test
    void testDoPeekEndObject() throws Exception {
        jsonReader = new JsonReader(new StringReader("{}"));
        jsonReader.beginObject();
        jsonReader.endObject();
        assertEquals(getPrivatePeekedConstant("PEEKED_END_OBJECT"), jsonReader.peeked);
    }

    @Test
    void testDoPeekBeginArray() throws Exception {
        jsonReader = new JsonReader(new StringReader("[1, 2, 3]"));
        jsonReader.beginArray();
        assertEquals(getPrivatePeekedConstant("PEEKED_BEGIN_ARRAY"), jsonReader.peeked);
    }

    @Test
    void testDoPeekEndArray() throws Exception {
        jsonReader = new JsonReader(new StringReader("[1, 2, 3]"));
        jsonReader.beginArray();
        jsonReader.endArray();
        assertEquals(getPrivatePeekedConstant("PEEKED_END_ARRAY"), jsonReader.peeked);
    }

    @Test
    void testDoPeekDoubleQuotedName() throws Exception {
        jsonReader = new JsonReader(new StringReader("{\"key\":\"value\"}"));
        jsonReader.beginObject();
        assertEquals(getPrivatePeekedConstant("PEEKED_DOUBLE_QUOTED_NAME"), jsonReader.peeked);
        jsonReader.nextName();
    }

    @Test
    void testDoPeekSingleQuotedName() throws Exception {
        jsonReader = new JsonReader(new StringReader("{'key':'value'}"));
        jsonReader.setLenient(true);
        jsonReader.beginObject();
        assertEquals(getPrivatePeekedConstant("PEEKED_SINGLE_QUOTED_NAME"), jsonReader.peeked);
        jsonReader.nextName();
    }

    @Test
    void testDoPeekUnquotedName() throws Exception {
        jsonReader = new JsonReader(new StringReader("{key:value}"));
        jsonReader.setLenient(true);
        jsonReader.beginObject();
        assertEquals(getPrivatePeekedConstant("PEEKED_UNQUOTED_NAME"), jsonReader.peeked);
        jsonReader.nextName();
    }

    @Test
    void testDoPeekEOF() throws Exception {
        jsonReader = new JsonReader(new StringReader("{\"key\":\"value\"}"));
        jsonReader.beginObject();
        jsonReader.nextName();
        jsonReader.nextString();
        assertEquals(getPrivatePeekedConstant("PEEKED_EOF"), jsonReader.peeked);
    }

    @Test
    void testDoPeekUnterminatedArray() throws Exception {
        jsonReader = new JsonReader(new StringReader("[1, 2, 3"));
        jsonReader.beginArray();
        assertThrows(IOException.class, () -> {
            jsonReader.endArray();
        });
    }

    @Test
    void testDoPeekUnterminatedObject() throws Exception {
        jsonReader = new JsonReader(new StringReader("{\"key\":\"value\""));
        jsonReader.beginObject();
        assertThrows(IOException.class, () -> {
            jsonReader.endObject();
        });
    }

    @Test
    void testDoPeekExpectedName() throws Exception {
        jsonReader = new JsonReader(new StringReader("{:}"));
        jsonReader.beginObject();
        assertThrows(IOException.class, () -> {
            jsonReader.nextName();
        });
    }

    @Test
    void testDoPeekClosedReader() throws Exception {
        jsonReader.close();
        assertThrows(IllegalStateException.class, () -> {
            jsonReader.doPeek();
        });
    }

    @Test
    void testDoPeekNullInLenientMode() throws Exception {
        jsonReader = new JsonReader(new StringReader("[null]"));
        jsonReader.setLenient(true);
        jsonReader.beginArray();
        assertEquals(getPrivatePeekedConstant("PEEKED_NULL"), jsonReader.peeked);
        jsonReader.nextNull();
    }

    @Test
    void testDoPeekSingleQuotedString() throws Exception {
        jsonReader = new JsonReader(new StringReader("{'key':'value'}"));
        jsonReader.setLenient(true);
        jsonReader.beginObject();
        jsonReader.nextName();
        assertEquals(getPrivatePeekedConstant("PEEKED_SINGLE_QUOTED"), jsonReader.peeked);
        jsonReader.nextString();
    }

    @Test
    void testDoPeekDoubleQuotedString() throws Exception {
        jsonReader = new JsonReader(new StringReader("{\"key\":\"value\"}"));
        jsonReader.beginObject();
        jsonReader.nextName();
        assertEquals(getPrivatePeekedConstant("PEEKED_DOUBLE_QUOTED"), jsonReader.peeked);
        jsonReader.nextString();
    }

    @Test
    void testDoPeekUnexpectedValue() throws Exception {
        jsonReader = new JsonReader(new StringReader("{key: value}"));
        jsonReader.setLenient(true);
        jsonReader.beginObject();
        assertThrows(IOException.class, () -> {
            jsonReader.nextName();
        });
    }

    @Test
    void testDoPeekExpectedValue() throws Exception {
        jsonReader = new JsonReader(new StringReader("{\"key\":}"));
        jsonReader.beginObject();
        assertThrows(IOException.class, () -> {
            jsonReader.nextString();
        });
    }
}
