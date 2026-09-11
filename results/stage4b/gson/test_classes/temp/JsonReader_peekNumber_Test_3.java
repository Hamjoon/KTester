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
import java.lang.reflect.Method;
import java.lang.reflect.Field;

@ExtendWith(MockitoExtension.class)
class JsonReader_peekNumber_Test {

    private JsonReader jsonReader;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the JsonReader with a StringReader for testing
        // Example JSON input containing numbers
        String jsonInput = "[1, 2, 3]";
        Reader stringReader = new StringReader(jsonInput);
        jsonReader = new JsonReader(stringReader);
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup resources if needed
        try {
            if (jsonReader != null) {
                jsonReader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int invokePeekNumber(JsonReader reader) throws Exception {
        // Use reflection to access the private peekNumber() method
        Method method = JsonReader.class.getDeclaredMethod("peekNumber");
        method.setAccessible(true);
        return (int) method.invoke(reader);
    }

    private int getPeekedField(String fieldName) throws Exception {
        Field field = JsonReader.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        // Change from null to jsonReader to get instance field
        return (int) field.get(jsonReader);
    }

    @Test
    void testPeekNumber() throws Exception {
        jsonReader.beginArray();
        assertEquals(getPeekedField("PEEKED_NUMBER"), invokePeekNumber(jsonReader));
        assertEquals(1, jsonReader.nextInt());
        assertEquals(getPeekedField("PEEKED_NUMBER"), invokePeekNumber(jsonReader));
        assertEquals(2, jsonReader.nextInt());
        assertEquals(getPeekedField("PEEKED_NUMBER"), invokePeekNumber(jsonReader));
        assertEquals(3, jsonReader.nextInt());
        jsonReader.endArray();
        assertEquals(JsonToken.END_DOCUMENT, jsonReader.peek());
    }

    @Test
    void testPeekNumberForValidLongNumber() throws Exception {
        // Test with a valid long number to ensure it is correctly identified as PEEKED_LONG.
        String jsonInput = "[9223372036854775807]";
        jsonReader = new JsonReader(new StringReader(jsonInput));
        // Allow lenient parsing
        jsonReader.setLenient(true);
        jsonReader.beginArray();
        assertEquals(getPeekedField("PEEKED_LONG"), invokePeekNumber(jsonReader));
        jsonReader.nextLong();
        jsonReader.endArray();
    }

    @Test
    void testPeekNumberForValidDoubleNumber() throws Exception {
        String jsonInput = "[3.14159]";
        jsonReader = new JsonReader(new StringReader(jsonInput));
        // Allow lenient parsing
        jsonReader.setLenient(true);
        jsonReader.beginArray();
        assertEquals(getPeekedField("PEEKED_NUMBER"), invokePeekNumber(jsonReader));
        jsonReader.nextDouble();
        jsonReader.endArray();
    }

    @Test
    void testPeekNumberForInvalidNumberFormats() throws Exception {
        // Changed 'text' to "text" to make it valid JSON
        String jsonInput = "[01, \"text\", -5e]";
        jsonReader = new JsonReader(new StringReader(jsonInput));
        // Allow lenient parsing
        jsonReader.setLenient(true);
        jsonReader.beginArray();
        assertEquals(getPeekedField("PEEKED_NONE"), invokePeekNumber(jsonReader));
        jsonReader.skipValue();
        assertEquals(getPeekedField("PEEKED_NONE"), invokePeekNumber(jsonReader));
        jsonReader.skipValue();
        assertEquals(getPeekedField("PEEKED_NONE"), invokePeekNumber(jsonReader));
        jsonReader.endArray();
    }

    @Test
    void testPeekNumberForBufferFillingScenarios() throws Exception {
        // This might still cause issues if the buffer overflows
        String jsonInput = "[" + "1".repeat(1025) + "]";
        jsonReader = new JsonReader(new StringReader(jsonInput));
        // Allow lenient parsing
        jsonReader.setLenient(true);
        jsonReader.beginArray();
        assertEquals(getPeekedField("PEEKED_NONE"), invokePeekNumber(jsonReader));
        jsonReader.endArray();
    }

    @Test
    void testPeekNumberForEdgeCases() throws Exception {
        // This is valid JSON, but exceeds long range
        String jsonInput = "[9223372036854775808]";
        jsonReader = new JsonReader(new StringReader(jsonInput));
        // Allow lenient parsing
        jsonReader.setLenient(true);
        jsonReader.beginArray();
        assertEquals(getPeekedField("PEEKED_NONE"), invokePeekNumber(jsonReader));
        jsonReader.endArray();
    }
}
