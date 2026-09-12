import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.stream.JsonToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JsonTreeReader_peek_Test {

    private JsonTreeReader reader;

    class CustomSubclass extends JsonElement {

        @Override
        public JsonElement deepCopy() {
            return this;
        }
    }

    class CustomSubclass extends JsonElement {

        @Override
        public JsonElement deepCopy() {
            return this;
        }
    }

    class CustomSubclass extends JsonElement {

        @Override
        public JsonElement deepCopy() {
            return this;
        }
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the JsonTreeReader with a default JsonElement before each test
        reader = new JsonTreeReader(new JsonObject());
    }

    /**
     * Helper method to set the stack and stackSize fields of the JsonTreeReader using reflection.
     */
    private void setStackAndSize(Object... elements) throws NoSuchFieldException, IllegalAccessException {
        Field stackField = JsonTreeReader.class.getDeclaredField("stack");
        Field stackSizeField = JsonTreeReader.class.getDeclaredField("stackSize");
        stackField.setAccessible(true);
        stackSizeField.setAccessible(true);
        stackField.set(reader, elements);
        stackSizeField.set(reader, elements.length);
    }

    @Test
    void testPeek_onEmptyJsonObject() throws IOException {
        JsonTreeReader in = new JsonTreeReader(new JsonObject());
        assertEquals(JsonToken.BEGIN_OBJECT, in.peek());
    }

    @Test
    void testPeek_onFilledJsonObject() throws IOException {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("a", "value");
        JsonTreeReader in = new JsonTreeReader(jsonObject);
        in.beginObject();
        assertEquals(JsonToken.NAME, in.peek());
    }

    @Test
    void testPeek_onJsonArray() throws IOException {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add("value");
        JsonTreeReader in = new JsonTreeReader(jsonArray);
        assertEquals(JsonToken.BEGIN_ARRAY, in.peek());
    }

    @Test
    void testPeek_onJsonPrimitiveString() throws IOException {
        // Test when the stack contains a JsonPrimitive string. Expect STRING token.
        setStackAndSize(new JsonPrimitive("string"));
        assertEquals(JsonToken.STRING, reader.peek());
    }

    @Test
    void testPeek_onJsonPrimitiveNumber() throws IOException {
        // Test when the stack contains a JsonPrimitive number. Expect NUMBER token.
        setStackAndSize(new JsonPrimitive(123));
        assertEquals(JsonToken.NUMBER, reader.peek());
    }

    @Test
    void testPeek_onJsonPrimitiveBoolean() throws IOException {
        // Test when the stack contains a JsonPrimitive boolean. Expect BOOLEAN token.
        setStackAndSize(new JsonPrimitive(true));
        assertEquals(JsonToken.BOOLEAN, reader.peek());
    }

    @Test
    void testPeek_onJsonNull() throws IOException {
        // Test when the stack contains a JsonNull. Expect NULL token.
        setStackAndSize(JsonNull.INSTANCE);
        assertEquals(JsonToken.NULL, reader.peek());
    }

    @Test
    void testPeek_onCustomJsonElementSubclass() {
        // Test when the stack contains a custom JsonElement subclass. Expect MalformedJsonException due to unsupported subclass.
        class CustomSubclass extends JsonElement {

            @Override
            public JsonElement deepCopy() {
                return this;
            }
        }
        JsonArray array = new JsonArray();
        array.add(new CustomSubclass());
        JsonTreeReader in = new JsonTreeReader(array);
        in.beginArray();
        assertThrows(IllegalStateException.class, in::peek);
    }

    @Test
    void testPeek_onClosedReader() {
        // Test when the reader is closed. Expect IllegalStateException.
        reader.close();
        assertThrows(IllegalStateException.class, reader::peek);
    }

    @Test
    void testPeek_onEmptyStack() throws IOException {
        // Test when stack is empty. Expect END_DOCUMENT as there are no elements to process.
        assertEquals(JsonToken.END_DOCUMENT, reader.peek());
    }

    @Test
    void testPeek_onJsonObject() throws IOException, NoSuchFieldException, IllegalAccessException {
        // Test when the stack contains a JsonObject. Expect BEGIN_OBJECT token.
        setStackAndSize(new JsonObject());
        assertEquals(JsonToken.BEGIN_OBJECT, reader.peek());
    }

    @Test
    void testPeek_onSentinelClosed() throws NoSuchFieldException, IllegalAccessException {
        // Test when the stack contains SENTINEL_CLOSED. Expect IllegalStateException indicating the reader is closed.
        Field sentinelClosedField = JsonTreeReader.class.getDeclaredField("SENTINEL_CLOSED");
        sentinelClosedField.setAccessible(true);
        Object sentinelClosed = sentinelClosedField.get(null);
        setStackAndSize(sentinelClosed);
        assertThrows(IllegalStateException.class, reader::peek);
    }

    @Test
    void testPeek_onUnsupportedObject() throws NoSuchFieldException, IllegalAccessException {
        // Test when the stack contains an unsupported object type. Expect IllegalStateException.
        setStackAndSize(new Object());
        assertThrows(IllegalStateException.class, reader::peek);
    }

    @Test
    void testPeek_onJsonObjectWithIterator() throws IOException, NoSuchFieldException, IllegalAccessException {
        // Test when the stack contains a JsonObject followed by an Iterator with elements. Expect NAME token.
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("key", "value");
        setStackAndSize(jsonObject, jsonObject.entrySet().iterator());
        assertEquals(JsonToken.NAME, reader.peek());
    }

    @Test
    void testPeek_onJsonArrayWithEmptyIterator() throws IOException, NoSuchFieldException, IllegalAccessException {
        // Test when the stack contains a JsonArray followed by an empty Iterator. Expect END_ARRAY token.
        JsonArray jsonArray = new JsonArray();
        setStackAndSize(jsonArray, jsonArray.iterator());
        assertEquals(JsonToken.END_ARRAY, reader.peek());
    }

    @Test
    void testPeek_onJsonArrayWithIteratorHavingString() throws IOException, NoSuchFieldException, IllegalAccessException {
        // Test when the stack contains a JsonArray followed by an Iterator with a JsonPrimitive string element. Expect STRING token after pushing the element.
        JsonArray jsonArray = new JsonArray();
        Iterator<JsonElement> iterator = Arrays.asList(new JsonPrimitive("item")).iterator();
        setStackAndSize(jsonArray, iterator);
        assertEquals(JsonToken.STRING, reader.peek());
    }

    @Test
    void testPeek_onJsonArrayWithIteratorHavingNumber() throws IOException, NoSuchFieldException, IllegalAccessException {
        // Test when the stack contains a JsonArray followed by an Iterator with a JsonPrimitive number element. Expect NUMBER token after pushing the element.
        JsonArray jsonArray = new JsonArray();
        Iterator<JsonElement> iterator = Arrays.asList(new JsonPrimitive(42)).iterator();
        setStackAndSize(jsonArray, iterator);
        assertEquals(JsonToken.NUMBER, reader.peek());
    }

    @Test
    void testPeek_onJsonArrayWithIteratorHavingBoolean() throws IOException, NoSuchFieldException, IllegalAccessException {
        // Test when the stack contains a JsonArray followed by an Iterator with a JsonPrimitive boolean element. Expect BOOLEAN token after pushing the element.
        JsonArray jsonArray = new JsonArray();
        Iterator<JsonElement> iterator = Arrays.asList(new JsonPrimitive(true)).iterator();
        setStackAndSize(jsonArray, iterator);
        assertEquals(JsonToken.BOOLEAN, reader.peek());
    }

    @Test
    void testPeek_onJsonArrayWithIteratorHavingNull() throws IOException, NoSuchFieldException, IllegalAccessException {
        // Test when the stack contains a JsonArray followed by an Iterator with a JsonNull element. Expect NULL token after pushing the element.
        JsonArray jsonArray = new JsonArray();
        Iterator<JsonElement> iterator = Arrays.asList(JsonNull.INSTANCE).iterator();
        setStackAndSize(jsonArray, iterator);
        assertEquals(JsonToken.NULL, reader.peek());
    }
}
