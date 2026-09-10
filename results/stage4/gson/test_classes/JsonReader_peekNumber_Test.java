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
class JsonReader_peekNumber_Test {

    private JsonReader jsonReader;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the JsonReader with a StringReader for testing
        // Wrap input in an array
        jsonReader = new JsonReader(new StringReader("[123]"));
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

    private int getPrivateConstant(String constantName) throws Exception {
        Field field = JsonReader.class.getDeclaredField(constantName);
        field.setAccessible(true);
        return (int) field.get(null);
    }

    @Test
    void testPeekNumber() throws Exception {
        int result = jsonReader.doPeek();
        assertEquals(getPrivateConstant("PEEKED_NUMBER"), result);
    }

    @Test
    void testPeekNegativeNumber() throws Exception {
        // Wrap input in an array
        jsonReader = new JsonReader(new StringReader("[-123]"));
        int result = jsonReader.doPeek();
        assertEquals(getPrivateConstant("PEEKED_NUMBER"), result);
    }

    @Test
    void testPeekInvalidNumber() throws Exception {
        // Wrap input in an array
        jsonReader = new JsonReader(new StringReader("[abc]"));
        int result = jsonReader.doPeek();
        assertEquals(getPrivateConstant("PEEKED_NONE"), result);
    }

    @Test
    void testPeekNumber_EmptyBuffer() throws Exception {
        // Wrap input in an empty array
        jsonReader = new JsonReader(new StringReader("[]"));
        Method peekNumberMethod = JsonReader.class.getDeclaredMethod("peekNumber");
        peekNumberMethod.setAccessible(true);
        int result = (int) peekNumberMethod.invoke(jsonReader);
        assertEquals(getPrivateConstant("PEEKED_NONE"), result);
    }

    @Test
    void testPeekNumber_ValidNegativeLong() throws Exception {
        // Wrap input in an array
        jsonReader = new JsonReader(new StringReader("[-123]"));
        Method peekNumberMethod = JsonReader.class.getDeclaredMethod("peekNumber");
        peekNumberMethod.setAccessible(true);
        int result = (int) peekNumberMethod.invoke(jsonReader);
        assertEquals(getPrivateConstant("PEEKED_LONG"), result);
    }

    @Test
    void testPeekNumber_ValidPositiveLong() throws Exception {
        // Wrap input in an array
        jsonReader = new JsonReader(new StringReader("[456]"));
        Method peekNumberMethod = JsonReader.class.getDeclaredMethod("peekNumber");
        peekNumberMethod.setAccessible(true);
        int result = (int) peekNumberMethod.invoke(jsonReader);
        assertEquals(getPrivateConstant("PEEKED_LONG"), result);
    }

    @Test
    void testPeekNumber_ValidDecimal() throws Exception {
        // Wrap input in an array
        jsonReader = new JsonReader(new StringReader("[0.123]"));
        Method peekNumberMethod = JsonReader.class.getDeclaredMethod("peekNumber");
        peekNumberMethod.setAccessible(true);
        int result = (int) peekNumberMethod.invoke(jsonReader);
        assertEquals(getPrivateConstant("PEEKED_NUMBER"), result);
    }

    @Test
    void testPeekNumber_ScientificNotation() throws Exception {
        // Wrap input in an array
        jsonReader = new JsonReader(new StringReader("[1e10]"));
        Method peekNumberMethod = JsonReader.class.getDeclaredMethod("peekNumber");
        peekNumberMethod.setAccessible(true);
        int result = (int) peekNumberMethod.invoke(jsonReader);
        assertEquals(getPrivateConstant("PEEKED_NUMBER"), result);
    }

    @Test
    void testPeekNumber_InvalidLeadingZeros() throws Exception {
        // Wrap input in an array
        jsonReader = new JsonReader(new StringReader("[0123]"));
        Method peekNumberMethod = JsonReader.class.getDeclaredMethod("peekNumber");
        peekNumberMethod.setAccessible(true);
        int result = (int) peekNumberMethod.invoke(jsonReader);
        assertEquals(getPrivateConstant("PEEKED_NONE"), result);
    }

    @Test
    void testPeekNumber_InvalidNonNumeric() throws Exception {
        // Wrap input in an array
        jsonReader = new JsonReader(new StringReader("[abc]"));
        Method peekNumberMethod = JsonReader.class.getDeclaredMethod("peekNumber");
        peekNumberMethod.setAccessible(true);
        int result = (int) peekNumberMethod.invoke(jsonReader);
        assertEquals(getPrivateConstant("PEEKED_NONE"), result);
    }

    @Test
    void testPeekNumber_InvalidMultipleDots() throws Exception {
        // Wrap input in an array
        jsonReader = new JsonReader(new StringReader("[12.34.56]"));
        Method peekNumberMethod = JsonReader.class.getDeclaredMethod("peekNumber");
        peekNumberMethod.setAccessible(true);
        int result = (int) peekNumberMethod.invoke(jsonReader);
        assertEquals(getPrivateConstant("PEEKED_NONE"), result);
    }

    @Test
    void testPeekNumber_IncompleteNegative() throws Exception {
        // Wrap input in an array
        jsonReader = new JsonReader(new StringReader("[-]"));
        Method peekNumberMethod = JsonReader.class.getDeclaredMethod("peekNumber");
        peekNumberMethod.setAccessible(true);
        int result = (int) peekNumberMethod.invoke(jsonReader);
        assertEquals(getPrivateConstant("PEEKED_NONE"), result);
    }
}
