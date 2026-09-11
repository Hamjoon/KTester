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
import java.lang.reflect.Method;
import java.lang.reflect.Field;

@ExtendWith(MockitoExtension.class)
class JsonReader_doPeek_Test {

    private JsonReader jsonReader;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the JsonReader with a valid JSON input for testing
        String jsonInput = "{\"key\": \"value\"}";
        jsonReader = new JsonReader(new StringReader(jsonInput));
    }

    @AfterEach
    void teardownAfterEach() throws IOException {
        // Close the JsonReader after each test
        jsonReader.close();
    }

    // Helper method to invoke the private doPeek method using reflection
    private int invokeDoPeek() throws Exception {
        Method method = JsonReader.class.getDeclaredMethod("doPeek");
        method.setAccessible(true);
        return (int) method.invoke(jsonReader);
    }

    // Helper method to invoke the private doPeek method and assert the expected value
    private void invokeDoPeekAndAssert(int expectedValue, String description) throws Exception {
        assertEquals(expectedValue, invokeDoPeek(), description);
    }

    // Helper method to get the field value from JsonReader using reflection
    private int getFieldValue(String fieldName) throws Exception {
        Field field = JsonReader.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        return (int) field.get(null);
    }

    @Test
    void testDoPeekWithBeginObject() throws Exception {
        jsonReader = new JsonReader(new StringReader("{\"key\": \"value\"}"));
        jsonReader.beginObject();
        invokeDoPeekAndAssert(getFieldValue("PEEKED_BEGIN_OBJECT"), "Expected PEEKED_BEGIN_OBJECT");
        jsonReader.endObject();
    }

    @Test
    void testDoPeekWithBeginArray() throws IOException {
        String jsonArrayInput = "[1, 2, 3]";
        jsonReader = new JsonReader(new StringReader(jsonArrayInput));
        jsonReader.beginArray();
        assertTrue(jsonReader.hasNext());
        assertEquals(1, jsonReader.nextInt());
        assertEquals(2, jsonReader.nextInt());
        assertEquals(3, jsonReader.nextInt());
        jsonReader.endArray();
    }

    @Test
    void testDoPeekWithEmptyObject() throws Exception {
        jsonReader = new JsonReader(new StringReader("{}"));
        jsonReader.beginObject();
        invokeDoPeekAndAssert(getFieldValue("PEEKED_END_OBJECT"), "Expected PEEKED_END_OBJECT");
        jsonReader.endObject();
    }

    @Test
    void testDoPeekWithEmptyArray() throws Exception {
        jsonReader = new JsonReader(new StringReader("[]"));
        jsonReader.beginArray();
        invokeDoPeekAndAssert(getFieldValue("PEEKED_END_ARRAY"), "Expected PEEKED_END_ARRAY");
        jsonReader.endArray();
    }

    @Test
    void testDoPeekWithEmptyDocument() throws Exception {
        jsonReader = new JsonReader(new StringReader(""));
        assertThrows(IOException.class, () -> invokeDoPeek());
    }

    @Test
    void testDoPeekWithSingleQuotedName() throws Exception {
        jsonReader = new JsonReader(new StringReader("{'key': 'value'}"));
        jsonReader.setLenient(true);
        jsonReader.beginObject();
        invokeDoPeekAndAssert(getFieldValue("PEEKED_SINGLE_QUOTED_NAME"), "Expected PEEKED_SINGLE_QUOTED_NAME");
        jsonReader.endObject();
    }

    @Test
    void testDoPeekWithDoubleQuotedName() throws Exception {
        jsonReader = new JsonReader(new StringReader("{\"key\": \"value\"}"));
        jsonReader.beginObject();
        invokeDoPeekAndAssert(getFieldValue("PEEKED_DOUBLE_QUOTED_NAME"), "Expected PEEKED_DOUBLE_QUOTED_NAME");
        jsonReader.endObject();
    }

    @Test
    void testDoPeekWithUnquotedName() throws Exception {
        jsonReader = new JsonReader(new StringReader("{key: \"value\"}"));
        jsonReader.setLenient(true);
        jsonReader.beginObject();
        invokeDoPeekAndAssert(getFieldValue("PEEKED_UNQUOTED_NAME"), "Expected PEEKED_UNQUOTED_NAME");
        jsonReader.endObject();
    }

    @Test
    void testDoPeekWithSingleQuoteInLenientMode() throws Exception {
        jsonReader.setLenient(true);
        jsonReader = new JsonReader(new StringReader("[ 'value' ]"));
        jsonReader.beginArray();
        invokeDoPeekAndAssert(getFieldValue("PEEKED_SINGLE_QUOTED"), "Expected PEEKED_SINGLE_QUOTED");
        jsonReader.endArray();
    }

    @Test
    void testDoPeekWithDoubleQuote() throws Exception {
        jsonReader = new JsonReader(new StringReader("[ \"value\" ]"));
        jsonReader.beginArray();
        invokeDoPeekAndAssert(getFieldValue("PEEKED_DOUBLE_QUOTED"), "Expected PEEKED_DOUBLE_QUOTED");
        jsonReader.endArray();
    }

    @Test
    void testDoPeekWithUnexpectedCharacterInArray() throws Exception {
        jsonReader = new JsonReader(new StringReader("[1, 2, 3"));
        jsonReader.beginArray();
        assertThrows(IOException.class, () -> invokeDoPeek());
    }

    @Test
    void testDoPeekWithUnexpectedCharacterInObject() throws Exception {
        jsonReader = new JsonReader(new StringReader("{\"key\": \"value\""));
        jsonReader.beginObject();
        assertThrows(IOException.class, () -> invokeDoPeek());
    }

    @Test
    void testDoPeekWhenJsonReaderIsClosed() throws Exception {
        jsonReader.close();
        assertThrows(IllegalStateException.class, () -> invokeDoPeek());
    }

    @Test
    void testDoPeekWhenExpectedNameNotFound() throws Exception {
        jsonReader = new JsonReader(new StringReader("{: \"value\"}"));
        jsonReader.beginObject();
        assertThrows(IOException.class, () -> invokeDoPeek());
    }

    @Test
    void testDoPeekWhenExpectedValueNotFound() throws Exception {
        jsonReader = new JsonReader(new StringReader("{\"key\":}"));
        jsonReader.beginObject();
        assertThrows(IOException.class, () -> invokeDoPeek());
    }

    @Test
    void testDoPeekWhenUnexpectedValueEncountered() throws Exception {
        jsonReader = new JsonReader(new StringReader("[1, 2, 'value']"));
        jsonReader.setLenient(true);
        jsonReader.beginArray();
        assertThrows(IOException.class, () -> invokeDoPeek());
    }
}
```