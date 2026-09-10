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
        // Initialize the JsonTreeReader or any other necessary setup
        // Default initialization
        jsonTreeReader = new JsonTreeReader(new JsonObject());
    }

    private void setStack(Object value) {
        try {
            java.lang.reflect.Field stackField = JsonTreeReader.class.getDeclaredField("stack");
            stackField.setAccessible(true);
            Object[] stack = (Object[]) stackField.get(jsonTreeReader);
            // Set value at the top of the stack
            stack[jsonTreeReader.stackSize - 1] = value;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testPeek_emptyJsonObject() throws IOException {
        // Test when stackSize is 0, should return END_DOCUMENT
        jsonTreeReader = new JsonTreeReader(new JsonObject());
        assertEquals(JsonToken.END_DOCUMENT, jsonTreeReader.peek());
    }

    @Test
    void testPeek_filledJsonObject() throws IOException {
        // Test when the top of the stack is an Iterator and isObject is true, should return NAME
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
        // Test when the top of the stack is a JsonArray, should return BEGIN_ARRAY
        JsonArray jsonArray = new JsonArray();
        jsonArray.add("first");
        jsonArray.add("second");
        jsonTreeReader = new JsonTreeReader(jsonArray);
        jsonTreeReader.beginArray();
        assertEquals(JsonToken.STRING, jsonTreeReader.peek());
    }

    @Test
    void testPeek_afterEndOfDocument() throws IOException {
        // Test after ending the object, should return END_DOCUMENT
        jsonTreeReader = new JsonTreeReader(new JsonObject());
        jsonTreeReader.beginObject();
        jsonTreeReader.endObject();
        assertEquals(JsonToken.END_DOCUMENT, jsonTreeReader.peek());
    }

    @Test
    void testPeek_customJsonElementSubclass() throws IOException {
        // Test when the top of the stack is a Custom JsonElement subclass that is not supported
        class CustomSubclass extends JsonElement {

            @Override
            public JsonElement deepCopy() {
                return this;
            }
        }
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
        // Test when the top of the stack is an Iterator and has next element, should return NAME
        Iterator<JsonElement> iterator = mock(Iterator.class);
        when(iterator.hasNext()).thenReturn(true);
        when(iterator.next()).thenReturn(new JsonPrimitive("mocked"));
        jsonTreeReader = new JsonTreeReader(new JsonObject());
        // Using reflection to access private stack and set it
        setStack(iterator);
        // Set stack size to simulate the state
        jsonTreeReader.stackSize = 2;
        assertEquals(JsonToken.NAME, jsonTreeReader.peek());
    }

    @Test
    void testPeek_stackContainsIteratorWithoutNextElement() throws IOException {
        // Test when the top of the stack is an Iterator but has no next element, should return END_OBJECT
        Iterator<JsonElement> iterator = mock(Iterator.class);
        when(iterator.hasNext()).thenReturn(false);
        jsonTreeReader = new JsonTreeReader(new JsonObject());
        // Using reflection to access private stack and set it
        setStack(iterator);
        // Set stack size to simulate the state
        jsonTreeReader.stackSize = 2;
        assertEquals(JsonToken.END_OBJECT, jsonTreeReader.peek());
    }

    @Test
    void testPeek_stackContainsJsonObject() throws IOException {
        // Test when the top of the stack is a JsonObject, should return BEGIN_OBJECT
        jsonTreeReader = new JsonTreeReader(new JsonObject());
        setStack(new JsonObject());
        // Set stack size to simulate the state
        jsonTreeReader.stackSize = 1;
        assertEquals(JsonToken.BEGIN_OBJECT, jsonTreeReader.peek());
    }

    @Test
    void testPeek_stackContainsJsonArray() throws IOException {
        // Test when the top of the stack is a JsonArray, should return BEGIN_ARRAY
        jsonTreeReader = new JsonTreeReader(new JsonArray());
        setStack(new JsonArray());
        // Set stack size to simulate the state
        jsonTreeReader.stackSize = 1;
        assertEquals(JsonToken.BEGIN_ARRAY, jsonTreeReader.peek());
    }

    @Test
    void testPeek_stackContainsJsonPrimitiveString() throws IOException {
        // Test when the top of the stack is a JsonPrimitive that is a string, should return STRING
        jsonTreeReader = new JsonTreeReader(new JsonPrimitive("stringValue"));
        setStack(new JsonPrimitive("stringValue"));
        // Set stack size to simulate the state
        jsonTreeReader.stackSize = 1;
        assertEquals(JsonToken.STRING, jsonTreeReader.peek());
    }

    @Test
    void testPeek_stackContainsJsonPrimitiveBoolean() throws IOException {
        // Test when the top of the stack is a JsonPrimitive that is a boolean, should return BOOLEAN
        jsonTreeReader = new JsonTreeReader(new JsonPrimitive(true));
        setStack(new JsonPrimitive(true));
        // Set stack size to simulate the state
        jsonTreeReader.stackSize = 1;
        assertEquals(JsonToken.BOOLEAN, jsonTreeReader.peek());
    }

    @Test
    void testPeek_stackContainsJsonPrimitiveNumber() throws IOException {
        // Test when the top of the stack is a JsonPrimitive that is a number, should return NUMBER
        jsonTreeReader = new JsonTreeReader(new JsonPrimitive(123));
        setStack(new JsonPrimitive(123));
        // Set stack size to simulate the state
        jsonTreeReader.stackSize = 1;
        assertEquals(JsonToken.NUMBER, jsonTreeReader.peek());
    }

    @Test
    void testPeek_stackContainsJsonPrimitiveUnsupported() {
        // Test when the top of the stack is a JsonPrimitive with an unsupported type, should throw AssertionError
        // Unsupported type
        jsonTreeReader = new JsonTreeReader(new JsonPrimitive(new Object()));
        setStack(new JsonPrimitive(new Object()));
        // Set stack size to simulate the state
        jsonTreeReader.stackSize = 1;
        assertThrows(AssertionError.class, () -> {
            jsonTreeReader.peek();
        });
    }

    @Test
    void testPeek_stackContainsJsonNull() throws IOException {
        // Test when the top of the stack is JsonNull, should return NULL
        jsonTreeReader = new JsonTreeReader(JsonNull.INSTANCE);
        setStack(JsonNull.INSTANCE);
        // Set stack size to simulate the state
        jsonTreeReader.stackSize = 1;
        assertEquals(JsonToken.NULL, jsonTreeReader.peek());
    }

    @Test
    void testPeek_stackContainsSentinelClosed() {
        // Test when the top of the stack is SENTINEL_CLOSED, should throw IllegalStateException
        jsonTreeReader = new JsonTreeReader(new JsonObject());
        setStack(JsonTreeReader.SENTINEL_CLOSED);
        // Set stack size to simulate the state
        jsonTreeReader.stackSize = 1;
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            jsonTreeReader.peek();
        });
        assertEquals("JsonReader is closed", exception.getMessage());
    }

    @Test
    void testPeek_stackContainsUnsupportedType() {
        // Test when the top of the stack is an unsupported type, should throw MalformedJsonException
        jsonTreeReader = new JsonTreeReader(new JsonObject());
        // Unsupported type
        setStack(new Object());
        // Set stack size to simulate the state
        jsonTreeReader.stackSize = 1;
        assertThrows(MalformedJsonException.class, () -> {
            jsonTreeReader.peek();
        });
    }
}
