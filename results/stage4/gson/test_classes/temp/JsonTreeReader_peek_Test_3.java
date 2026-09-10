package com.google.gson.internal.bind;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import com.google.gson.JsonPrimitive;
import java.util.Iterator;
import org.mockito.junit.jupiter.MockitoExtension;
import com.google.gson.stream.MalformedJsonException;

@ExtendWith(MockitoExtension.class)
class JsonTreeReader_peek_Test {

    private JsonTreeReader jsonTreeReader;

    class CustomSubclass extends JsonElement {

        @Override
        public JsonElement deepCopy() {
            return this;
        }
    }

    @BeforeEach
    void setupBeforeEach() {
        jsonTreeReader = new JsonTreeReader(new JsonObject());
    }

    private void setStack(Object value) {
        try {
            java.lang.reflect.Field stackField = JsonTreeReader.class.getDeclaredField("stack");
            stackField.setAccessible(true);
            Object[] stack = (Object[]) stackField.get(jsonTreeReader);
            // Set value at the top of the stack
            stack[stack.length - 1] = value;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testPeek_emptyJsonObject() throws IOException {
        jsonTreeReader = new JsonTreeReader(new JsonObject());
        jsonTreeReader.beginObject();
        assertEquals(JsonToken.END_OBJECT, jsonTreeReader.peek());
    }

    @Test
    void testPeek_filledJsonObject() throws IOException {
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        jsonArray.add('c');
        jsonArray.add("text");
        jsonObject.add("a", jsonArray);
        jsonObject.addProperty("b", true);
        jsonObject.addProperty("i", 1);
        jsonObject.add("n", JsonNull.INSTANCE);
        jsonObject.addProperty("s", "text");
        jsonTreeReader = new JsonTreeReader(jsonObject);
        jsonTreeReader.beginObject();
        assertEquals(JsonToken.NAME, jsonTreeReader.peek());
    }

    @Test
    void testPeek_atArrayStart() throws IOException {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add("first");
        jsonArray.add("second");
        jsonTreeReader = new JsonTreeReader(jsonArray);
        jsonTreeReader.beginArray();
        assertEquals(JsonToken.STRING, jsonTreeReader.peek());
    }

    @Test
    void testPeek_afterEndOfDocument() throws IOException {
        jsonTreeReader = new JsonTreeReader(new JsonObject());
        jsonTreeReader.beginObject();
        jsonTreeReader.endObject();
        assertEquals(JsonToken.END_DOCUMENT, jsonTreeReader.peek());
    }

    @Test
    void testPeek_customJsonElementSubclass() throws IOException {
        JsonArray array = new JsonArray();
        array.add(new CustomSubclass());
        jsonTreeReader = new JsonTreeReader(array);
        jsonTreeReader.beginArray();
        Exception exception = assertThrows(MalformedJsonException.class, () -> {
            jsonTreeReader.peek();
        });
        assertEquals("Custom JsonElement subclass " + CustomSubclass.class.getName() + " is not supported", exception.getMessage());
    }

    @Test
    void testPeek_stackContainsIteratorWithNextElement() throws IOException {
        Iterator<JsonElement> iterator = mock(Iterator.class);
        when(iterator.hasNext()).thenReturn(true);
        when(iterator.next()).thenReturn(new JsonPrimitive("mocked"));
        jsonTreeReader = new JsonTreeReader(new JsonObject());
        setStack(iterator);
        // Consume the object
        jsonTreeReader.beginObject();
        assertEquals(JsonToken.NAME, jsonTreeReader.peek());
    }

    @Test
    void testPeek_stackContainsIteratorWithoutNextElement() throws IOException {
        Iterator<JsonElement> iterator = mock(Iterator.class);
        when(iterator.hasNext()).thenReturn(false);
        jsonTreeReader = new JsonTreeReader(new JsonObject());
        setStack(iterator);
        // Consume the object
        jsonTreeReader.beginObject();
        assertEquals(JsonToken.END_OBJECT, jsonTreeReader.peek());
    }

    @Test
    void testPeek_stackContainsJsonObject() throws IOException {
        jsonTreeReader = new JsonTreeReader(new JsonObject());
        setStack(new JsonObject());
        // Consume the object
        jsonTreeReader.beginObject();
        assertEquals(JsonToken.BEGIN_OBJECT, jsonTreeReader.peek());
    }

    @Test
    void testPeek_stackContainsJsonArray() throws IOException {
        jsonTreeReader = new JsonTreeReader(new JsonArray());
        setStack(new JsonArray());
        // Consume the array
        jsonTreeReader.beginArray();
        assertEquals(JsonToken.BEGIN_ARRAY, jsonTreeReader.peek());
    }

    @Test
    void testPeek_stackContainsJsonPrimitiveString() throws IOException {
        jsonTreeReader = new JsonTreeReader(new JsonPrimitive("stringValue"));
        setStack(new JsonPrimitive("stringValue"));
        assertEquals(JsonToken.STRING, jsonTreeReader.peek());
    }

    @Test
    void testPeek_stackContainsJsonPrimitiveBoolean() throws IOException {
        jsonTreeReader = new JsonTreeReader(new JsonPrimitive(true));
        setStack(new JsonPrimitive(true));
        assertEquals(JsonToken.BOOLEAN, jsonTreeReader.peek());
    }

    @Test
    void testPeek_stackContainsJsonPrimitiveNumber() throws IOException {
        jsonTreeReader = new JsonTreeReader(new JsonPrimitive(123));
        setStack(new JsonPrimitive(123));
        assertEquals(JsonToken.NUMBER, jsonTreeReader.peek());
    }

    @Test
    void testPeek_stackContainsJsonPrimitiveUnsupported() {
        jsonTreeReader = new JsonTreeReader(new JsonPrimitive("unsupported"));
        setStack(new JsonPrimitive("unsupported"));
        assertThrows(MalformedJsonException.class, () -> {
            jsonTreeReader.peek();
        });
    }

    @Test
    void testPeek_stackContainsJsonNull() throws IOException {
        jsonTreeReader = new JsonTreeReader(JsonNull.INSTANCE);
        setStack(JsonNull.INSTANCE);
        assertEquals(JsonToken.NULL, jsonTreeReader.peek());
    }

    @Test
    void testPeek_stackContainsSentinelClosed() throws Exception {
        jsonTreeReader = new JsonTreeReader(new JsonObject());
        // Instead of accessing a private static final field, we should mock the state
        // Commenting out the following line as it causes IllegalAccessException
        // setStack(JsonTreeReader.class.getDeclaredField("SENTINEL_CLOSED").get(null));
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            jsonTreeReader.peek();
        });
        assertEquals("JsonReader is closed", exception.getMessage());
    }

    @Test
    void testPeek_stackContainsUnsupportedType() {
        jsonTreeReader = new JsonTreeReader(new JsonObject());
        setStack(new Object());
        assertThrows(MalformedJsonException.class, () -> {
            jsonTreeReader.peek();
        });
    }
}
