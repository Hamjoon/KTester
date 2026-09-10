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
import java.io.EOFException;

@ExtendWith(MockitoExtension.class)
class JsonReader_nextNonWhitespace_Test {

    private JsonReader jsonReader;

    private StringReader stringReader;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the StringReader and JsonReader before each test
        stringReader = new StringReader("");
        jsonReader = new JsonReader(stringReader);
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup if necessary after each test
        if (jsonReader != null) {
            try {
                jsonReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    void testNextNonWhitespaceWithValidInput() throws Exception {
        // Given
        stringReader = new StringReader("  \n   {\"key\": \"value\"}  ");
        jsonReader = new JsonReader(stringReader);
        // Allow lenient parsing
        jsonReader.setLenient(true);
        // When
        Method method = JsonReader.class.getDeclaredMethod("nextNonWhitespace", boolean.class);
        method.setAccessible(true);
        int result = (int) method.invoke(jsonReader, true);
        // Then
        assertEquals('{', result);
    }

    @Test
    void testNextNonWhitespaceThrowsEOFException() throws Exception {
        // Given
        stringReader = new StringReader("");
        jsonReader = new JsonReader(stringReader);
        // Allow lenient parsing
        jsonReader.setLenient(true);
        // When & Then
        Method method = JsonReader.class.getDeclaredMethod("nextNonWhitespace", boolean.class);
        method.setAccessible(true);
        assertThrows(EOFException.class, () -> {
            method.invoke(jsonReader, true);
        });
    }

    @Test
    void testNextNonWhitespaceHandlesComment() throws Exception {
        // Given
        stringReader = new StringReader("// This is a comment\n{ \"key\": \"value\" }");
        jsonReader = new JsonReader(stringReader);
        // Allow lenient parsing
        jsonReader.setLenient(true);
        // When
        Method method = JsonReader.class.getDeclaredMethod("nextNonWhitespace", boolean.class);
        method.setAccessible(true);
        int result = (int) method.invoke(jsonReader, true);
        // Then
        assertEquals('{', result);
    }

    @Test
    void testNextNonWhitespaceHandlesWhitespace() throws Exception {
        // Given
        stringReader = new StringReader("   \t \n   [1, 2, 3]");
        jsonReader = new JsonReader(stringReader);
        // Allow lenient parsing
        jsonReader.setLenient(true);
        // When
        Method method = JsonReader.class.getDeclaredMethod("nextNonWhitespace", boolean.class);
        method.setAccessible(true);
        int result = (int) method.invoke(jsonReader, true);
        // Then
        assertEquals('[', result);
    }

    @Test
    void testNextNonWhitespaceHandlesEndOfInput() throws Exception {
        // Given
        stringReader = new StringReader("   \t \n   ");
        jsonReader = new JsonReader(stringReader);
        // Allow lenient parsing
        jsonReader.setLenient(true);
        // When
        Method method = JsonReader.class.getDeclaredMethod("nextNonWhitespace", boolean.class);
        method.setAccessible(true);
        int result = (int) method.invoke(jsonReader, false);
        // Then
        // -1 indicates end of input without throwing an exception
        assertEquals(-1, result);
    }

    /**
     * Test cases for nextNonWhitespace method with EOF handling.
     */
    @Test
    void testNextNonWhitespaceWithEOFHandling() throws Exception {
        // Test case where the method reaches the end of input and throws EOFException
        stringReader = new StringReader("");
        jsonReader = new JsonReader(stringReader);
        // Allow lenient parsing
        jsonReader.setLenient(true);
        Method method = JsonReader.class.getDeclaredMethod("nextNonWhitespace", boolean.class);
        method.setAccessible(true);
        // When & Then
        assertThrows(EOFException.class, () -> {
            method.invoke(jsonReader, true);
        });
        // Test case where the method reaches the end of input and returns -1
        stringReader = new StringReader("");
        jsonReader = new JsonReader(stringReader);
        // Allow lenient parsing
        jsonReader.setLenient(true);
        int result = (int) method.invoke(jsonReader, false);
        assertEquals(-1, result);
    }

    /**
     * Test cases for nextNonWhitespace method with whitespace handling.
     */
    @Test
    void testNextNonWhitespaceWithWhitespaceHandling() throws Exception {
        // Given input with only whitespace characters
        stringReader = new StringReader("   \t \n   ");
        jsonReader = new JsonReader(stringReader);
        // Allow lenient parsing
        jsonReader.setLenient(true);
        Method method = JsonReader.class.getDeclaredMethod("nextNonWhitespace", boolean.class);
        method.setAccessible(true);
        // When
        int result = (int) method.invoke(jsonReader, false);
        // Then
        // Expecting -1 since there are no non-whitespace characters
        assertEquals(-1, result);
    }

    /**
     * Test cases for nextNonWhitespace method with comments handling.
     */
    @Test
    void testNextNonWhitespaceWithCommentsHandling() throws Exception {
        // Given input with a single-line comment
        stringReader = new StringReader("// This is a comment\n{ \"key\": \"value\" }");
        jsonReader = new JsonReader(stringReader);
        // Allow lenient parsing
        jsonReader.setLenient(true);
        Method method = JsonReader.class.getDeclaredMethod("nextNonWhitespace", boolean.class);
        method.setAccessible(true);
        // When
        int result = (int) method.invoke(jsonReader, true);
        // Then
        // Expecting the first non-comment character
        assertEquals('{', result);
    }

    @Test
    void testNextNonWhitespaceWithMultiLineCommentHandling() throws Exception {
        // Given input with a multi-line comment
        stringReader = new StringReader("/* This is a multi-line comment */ { \"key\": \"value\" }");
        jsonReader = new JsonReader(stringReader);
        // Allow lenient parsing
        jsonReader.setLenient(true);
        Method method = JsonReader.class.getDeclaredMethod("nextNonWhitespace", boolean.class);
        method.setAccessible(true);
        // When
        int result = (int) method.invoke(jsonReader, true);
        // Then
        // Expecting the first non-comment character
        assertEquals('{', result);
    }

    /**
     * Test cases for nextNonWhitespace method with invalid input handling.
     */
    @Test
    void testNextNonWhitespaceWithInvalidInputHandling() throws Exception {
        // Given input with an unterminated multi-line comment
        stringReader = new StringReader("/* This is an unterminated comment");
        jsonReader = new JsonReader(stringReader);
        // Allow lenient parsing
        jsonReader.setLenient(true);
        Method method = JsonReader.class.getDeclaredMethod("nextNonWhitespace", boolean.class);
        method.setAccessible(true);
        // When & Then
        assertThrows(IOException.class, () -> {
            method.invoke(jsonReader, true);
        });
    }

    @Test
    void testNextNonWhitespaceWithInvalidCharacterHandling() throws Exception {
        // Given input with an invalid character
        stringReader = new StringReader("{ invalid character }");
        jsonReader = new JsonReader(stringReader);
        // Allow lenient parsing
        jsonReader.setLenient(true);
        Method method = JsonReader.class.getDeclaredMethod("nextNonWhitespace", boolean.class);
        method.setAccessible(true);
        // When & Then
        assertThrows(IOException.class, () -> {
            method.invoke(jsonReader, true);
        });
    }

    /**
     * Test cases for nextNonWhitespace method with valid character handling.
     */
    @Test
    void testNextNonWhitespaceWithValidCharacterHandling() throws Exception {
        // Given input with valid JSON characters
        stringReader = new StringReader("{ \"key\": \"value\" }");
        jsonReader = new JsonReader(stringReader);
        // Allow lenient parsing
        jsonReader.setLenient(true);
        Method method = JsonReader.class.getDeclaredMethod("nextNonWhitespace", boolean.class);
        method.setAccessible(true);
        // When
        int result = (int) method.invoke(jsonReader, false);
        // Then
        // Expecting the first valid character code
        assertEquals('{', result);
    }

    @Test
    void testNextNonWhitespaceWithValidCharacterHandlingEOF() throws Exception {
        // Given input with valid JSON characters
        stringReader = new StringReader("{ \"key\": \"value\" }");
        jsonReader = new JsonReader(stringReader);
        // Allow lenient parsing
        jsonReader.setLenient(true);
        Method method = JsonReader.class.getDeclaredMethod("nextNonWhitespace", boolean.class);
        method.setAccessible(true);
        // When
        int result = (int) method.invoke(jsonReader, true);
        // Then
        // Expecting the first valid character code even when throwOnEof is true
        assertEquals('{', result);
    }
}
