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

@ExtendWith(MockitoExtension.class)
class JsonReader_peekNumber_Test {

    private JsonReader jsonReader;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the JsonReader with a StringReader for testing
        // Example JSON input
        jsonReader = new JsonReader(new StringReader("123"));
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup resources after each test
        try {
            jsonReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testPeekNumber() throws IOException {
        // Given a valid number in the input
        // Start an array
        jsonReader.beginArray();
        // When peekNumber is called
        // Assuming doPeek internally calls peekNumber
        int result = jsonReader.doPeek();
        // Then the result should indicate a number was peeked
        assertEquals(JsonReader.PEEKED_NUMBER, result);
        // End the array
        jsonReader.endArray();
    }

    @Test
    void testPeekNegativeNumber() throws IOException {
        // Given a valid negative number in the input
        // Example JSON input
        jsonReader = new JsonReader(new StringReader("-123"));
        // Start an array
        jsonReader.beginArray();
        // When peekNumber is called
        // Assuming doPeek internally calls peekNumber
        int result = jsonReader.doPeek();
        // Then the result should indicate a number was peeked
        assertEquals(JsonReader.PEEKED_NUMBER, result);
        // End the array
        jsonReader.endArray();
    }

    @Test
    void testPeekInvalidNumber() throws IOException {
        // Given an invalid number in the input
        // Example JSON input
        jsonReader = new JsonReader(new StringReader("abc"));
        // Start an array
        jsonReader.beginArray();
        // When peekNumber is called
        // Assuming doPeek internally calls peekNumber
        int result = jsonReader.doPeek();
        // Then the result should indicate that no number was peeked
        assertEquals(JsonReader.PEEKED_NONE, result);
        // End the array
        jsonReader.endArray();
    }

    @Test
    void testPeekNumber_EmptyBuffer() throws Exception {
        // Test when the buffer is empty, expecting PEEKED_NONE as no number can be read.
        // Empty input
        jsonReader = new JsonReader(new StringReader(""));
        // Start an array
        jsonReader.beginArray();
        // Invoke the private method peekNumber using reflection
        Method peekNumberMethod = JsonReader.class.getDeclaredMethod("peekNumber");
        peekNumberMethod.setAccessible(true);
        int result = (int) peekNumberMethod.invoke(jsonReader);
        // Then the result should indicate that no number was peeked
        assertEquals(JsonReader.PEEKED_NONE, result);
        // End the array
        jsonReader.endArray();
    }

    @Test
    void testPeekNumber_ValidNegativeLong() throws Exception {
        // Test when a valid negative long number is in the buffer, expecting PEEKED_LONG with value -123.
        // Example JSON input
        jsonReader = new JsonReader(new StringReader("-123"));
        // Start an array
        jsonReader.beginArray();
        // Invoke the private method peekNumber using reflection
        Method peekNumberMethod = JsonReader.class.getDeclaredMethod("peekNumber");
        peekNumberMethod.setAccessible(true);
        int result = (int) peekNumberMethod.invoke(jsonReader);
        // Then the result should indicate a long number was peeked
        assertEquals(JsonReader.PEEKED_LONG, result);
        // End the array
        jsonReader.endArray();
    }

    @Test
    void testPeekNumber_ValidPositiveLong() throws Exception {
        // Test when a valid positive long number is in the buffer, expecting PEEKED_LONG with value 456.
        // Example JSON input
        jsonReader = new JsonReader(new StringReader("456"));
        // Start an array
        jsonReader.beginArray();
        // Invoke the private method peekNumber using reflection
        Method peekNumberMethod = JsonReader.class.getDeclaredMethod("peekNumber");
        peekNumberMethod.setAccessible(true);
        int result = (int) peekNumberMethod.invoke(jsonReader);
        // Then the result should indicate a long number was peeked
        assertEquals(JsonReader.PEEKED_LONG, result);
        // End the array
        jsonReader.endArray();
    }

    @Test
    void testPeekNumber_ValidDecimal() throws Exception {
        // Test when a valid decimal number is in the buffer, expecting PEEKED_NUMBER.
        // Example JSON input
        jsonReader = new JsonReader(new StringReader("0.123"));
        // Start an array
        jsonReader.beginArray();
        // Invoke the private method peekNumber using reflection
        Method peekNumberMethod = JsonReader.class.getDeclaredMethod("peekNumber");
        peekNumberMethod.setAccessible(true);
        int result = (int) peekNumberMethod.invoke(jsonReader);
        // Then the result should indicate a number was peeked
        assertEquals(JsonReader.PEEKED_NUMBER, result);
        // End the array
        jsonReader.endArray();
    }

    @Test
    void testPeekNumber_ScientificNotation() throws Exception {
        // Test when a valid scientific notation number is in the buffer, expecting PEEKED_NUMBER.
        // Example JSON input
        jsonReader = new JsonReader(new StringReader("1e10"));
        // Start an array
        jsonReader.beginArray();
        // Invoke the private method peekNumber using reflection
        Method peekNumberMethod = JsonReader.class.getDeclaredMethod("peekNumber");
        peekNumberMethod.setAccessible(true);
        int result = (int) peekNumberMethod.invoke(jsonReader);
        // Then the result should indicate a number was peeked
        assertEquals(JsonReader.PEEKED_NUMBER, result);
        // End the array
        jsonReader.endArray();
    }

    @Test
    void testPeekNumber_InvalidLeadingZeros() throws Exception {
        // Test when a number with leading zeros is in the buffer, expecting PEEKED_NONE due to invalid format.
        // Example JSON input
        jsonReader = new JsonReader(new StringReader("0123"));
        // Start an array
        jsonReader.beginArray();
        // Invoke the private method peekNumber using reflection
        Method peekNumberMethod = JsonReader.class.getDeclaredMethod("peekNumber");
        peekNumberMethod.setAccessible(true);
        int result = (int) peekNumberMethod.invoke(jsonReader);
        // Then the result should indicate that no number was peeked
        assertEquals(JsonReader.PEEKED_NONE, result);
        // End the array
        jsonReader.endArray();
    }

    @Test
    void testPeekNumber_InvalidNonNumeric() throws Exception {
        // Test when a non-numeric string is in the buffer, expecting PEEKED_NONE.
        // Example JSON input
        jsonReader = new JsonReader(new StringReader("abc"));
        // Start an array
        jsonReader.beginArray();
        // Invoke the private method peekNumber using reflection
        Method peekNumberMethod = JsonReader.class.getDeclaredMethod("peekNumber");
        peekNumberMethod.setAccessible(true);
        int result = (int) peekNumberMethod.invoke(jsonReader);
        // Then the result should indicate that no number was peeked
        assertEquals(JsonReader.PEEKED_NONE, result);
        // End the array
        jsonReader.endArray();
    }

    @Test
    void testPeekNumber_InvalidMultipleDots() throws Exception {
        // Test when an invalid decimal format with multiple dots is in the buffer, expecting PEEKED_NONE.
        // Example JSON input
        jsonReader = new JsonReader(new StringReader("12.34.56"));
        // Start an array
        jsonReader.beginArray();
        // Invoke the private method peekNumber using reflection
        Method peekNumberMethod = JsonReader.class.getDeclaredMethod("peekNumber");
        peekNumberMethod.setAccessible(true);
        int result = (int) peekNumberMethod.invoke(jsonReader);
        // Then the result should indicate that no number was peeked
        assertEquals(JsonReader.PEEKED_NONE, result);
        // End the array
        jsonReader.endArray();
    }

    @Test
    void testPeekNumber_IncompleteNegative() throws Exception {
        // Test when only a negative sign is in the buffer, expecting PEEKED_NONE as it's incomplete.
        // Example JSON input
        jsonReader = new JsonReader(new StringReader("-"));
        // Start an array
        jsonReader.beginArray();
        // Invoke the private method peekNumber using reflection
        Method peekNumberMethod = JsonReader.class.getDeclaredMethod("peekNumber");
        peekNumberMethod.setAccessible(true);
        int result = (int) peekNumberMethod.invoke(jsonReader);
        // Then the result should indicate that no number was peeked
        assertEquals(JsonReader.PEEKED_NONE, result);
        // End the array
        jsonReader.endArray();
    }
}
