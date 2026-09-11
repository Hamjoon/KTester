import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonToken;
import com.google.gson.internal.bind.JsonTreeReader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.IOException;
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

    class CustomElement extends JsonElement {

        @Override
        public JsonElement deepCopy() {
            return this;
        }
    }

    @Test
    void testPeek_emptyJsonObject() throws IOException {
        jsonTreeReader = new JsonTreeReader(new JsonObject());
        assertEquals(JsonToken.END_DOCUMENT, jsonTreeReader.peek());
    }

    @Test
    void testPeek_filledJsonObject() throws IOException {
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        // Changed char to JsonPrimitive
        jsonArray.add(new JsonPrimitive('c'));
        // Changed String to JsonPrimitive
        jsonArray.add(new JsonPrimitive("text"));
        jsonObject.add("a", jsonArray);
        jsonObject.addProperty("b", true);
        jsonObject.addProperty("i", 1);
        jsonObject.add("n", JsonNull.INSTANCE);
        jsonObject.addProperty("s", "text");
        jsonTreeReader = new JsonTreeReader(jsonObject);
        assertEquals(JsonToken.BEGIN_OBJECT, jsonTreeReader.peek());
    }

    @Test
    void testPeek_afterEndOfDocument() throws IOException {
        jsonTreeReader = new JsonTreeReader(new JsonObject());
        jsonTreeReader.beginObject();
        jsonTreeReader.endObject();
        assertEquals(JsonToken.END_DOCUMENT, jsonTreeReader.peek());
    }

    @Test
    void testPeek_atArrayEnd() throws IOException {
        jsonTreeReader = new JsonTreeReader(new JsonArray());
        jsonTreeReader.beginArray();
        jsonTreeReader.endArray();
        assertEquals(JsonToken.END_DOCUMENT, jsonTreeReader.peek());
    }

    @Test
    void testPeek_customJsonElementSubclass() throws IOException {
        JsonArray array = new JsonArray();
        array.add(new CustomSubclass());
        jsonTreeReader = new JsonTreeReader(array);
        jsonTreeReader.beginArray();
        assertThrows(MalformedJsonException.class, () -> {
            jsonTreeReader.peek();
        });
    }

    @Test
    void testPeek_emptyStack() throws IOException {
        jsonTreeReader = new JsonTreeReader(new JsonObject());
        assertEquals(JsonToken.END_DOCUMENT, jsonTreeReader.peek());
    }

    @Test
    void testPeek_iteratorWithNextElement() throws IOException {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("key", new JsonPrimitive("value"));
        jsonTreeReader = new JsonTreeReader(jsonObject);
        jsonTreeReader.beginObject();
        assertEquals(JsonToken.NAME, jsonTreeReader.peek());
    }

    @Test
    void testPeek_iteratorWithoutNextElement() throws IOException {
        JsonObject jsonObject = new JsonObject();
        jsonTreeReader = new JsonTreeReader(jsonObject);
        jsonTreeReader.beginObject();
        jsonTreeReader.endObject();
        assertEquals(JsonToken.END_OBJECT, jsonTreeReader.peek());
        JsonArray jsonArray = new JsonArray();
        jsonTreeReader = new JsonTreeReader(jsonArray);
        jsonTreeReader.beginArray();
        jsonTreeReader.endArray();
        assertEquals(JsonToken.END_ARRAY, jsonTreeReader.peek());
    }

    @Test
    void testPeek_JsonObject() throws IOException {
        JsonObject jsonObject = new JsonObject();
        jsonTreeReader = new JsonTreeReader(jsonObject);
        assertEquals(JsonToken.BEGIN_OBJECT, jsonTreeReader.peek());
    }

    @Test
    void testPeek_JsonArray() throws IOException {
        JsonArray jsonArray = new JsonArray();
        jsonTreeReader = new JsonTreeReader(jsonArray);
        assertEquals(JsonToken.BEGIN_ARRAY, jsonTreeReader.peek());
    }

    @Test
    void testPeek_JsonPrimitive() throws IOException {
        jsonTreeReader = new JsonTreeReader(new JsonPrimitive("string"));
        assertEquals(JsonToken.STRING, jsonTreeReader.peek());
        jsonTreeReader = new JsonTreeReader(new JsonPrimitive(true));
        assertEquals(JsonToken.BOOLEAN, jsonTreeReader.peek());
        jsonTreeReader = new JsonTreeReader(new JsonPrimitive(123));
        assertEquals(JsonToken.NUMBER, jsonTreeReader.peek());
    }

    @Test
    void testPeek_JsonNull() throws IOException {
        jsonTreeReader = new JsonTreeReader(JsonNull.INSTANCE);
        assertEquals(JsonToken.NULL, jsonTreeReader.peek());
    }

    @Test
    void testPeek_closedReader() throws IOException {
        jsonTreeReader = new JsonTreeReader(new JsonObject());
        jsonTreeReader.close();
        assertThrows(IllegalStateException.class, () -> {
            jsonTreeReader.peek();
        });
    }

    @Test
    void testPeek_unsupportedType() throws IOException {
        jsonTreeReader = new JsonTreeReader(new CustomElement());
        assertThrows(MalformedJsonException.class, () -> {
            jsonTreeReader.peek();
        });
    }
}
