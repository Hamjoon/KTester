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
import java.io.EOFException;

@ExtendWith(MockitoExtension.class)
class JsonReader_nextNonWhitespace_Test {

    private JsonReader jsonReader;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the JsonReader with a default input for testing
        // Example JSON input
        String jsonInput = "  { \"key\": \"value\" }  ";
        Reader reader = new StringReader(jsonInput);
        jsonReader = new JsonReader(reader);
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup resources after each test if necessary
        try {
            jsonReader.close();
        } catch (IOException e) {
            fail("Failed to close JsonReader: " + e.getMessage());
        }
    }

    @Test
    void testNextNonWhitespaceWithThrowOnEofTrue() throws Exception {
        // Given a JsonReader with leading whitespace
        // When invoking nextNonWhitespace with throwOnEof as true
        try {
            Method method = JsonReader.class.getDeclaredMethod("nextNonWhitespace", boolean.class);
            method.setAccessible(true);
            int result = (int) method.invoke(jsonReader, true);
            // Then assert that the result is the expected character (the first non-whitespace character)
            assertEquals('{', result);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    void testNextNonWhitespaceWithThrowOnEofFalse() throws Exception {
        // Given a JsonReader with leading whitespace
        // When invoking nextNonWhitespace with throwOnEof as false
        try {
            Method method = JsonReader.class.getDeclaredMethod("nextNonWhitespace", boolean.class);
            method.setAccessible(true);
            // Move to the first non-whitespace character
            // Consume leading whitespace
            method.invoke(jsonReader, true);
            // Should return -1 at EOF
            int result = (int) method.invoke(jsonReader, false);
            // Then assert that the result is -1 (indicating EOF)
            assertEquals(-1, result);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    void testNextNonWhitespaceThrowsEOFException() {
        // Given an empty JsonReader
        Reader emptyReader = new StringReader("");
        jsonReader = new JsonReader(emptyReader);
        // When invoking nextNonWhitespace with throwOnEof as true
        // Then an EOFException should be thrown
        try {
            Method method = JsonReader.class.getDeclaredMethod("nextNonWhitespace", boolean.class);
            method.setAccessible(true);
            assertThrows(EOFException.class, () -> {
                method.invoke(jsonReader, true);
            });
        } catch (NoSuchMethodException | IllegalAccessException e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    void testNextNonWhitespaceWhitespaceHandling() throws Exception {
        // Given a JsonReader with leading whitespace
        // Leading whitespace
        String jsonInput = "    { \"key\": \"value\" }";
        jsonReader = new JsonReader(new StringReader(jsonInput));
        try {
            Method method = JsonReader.class.getDeclaredMethod("nextNonWhitespace", boolean.class);
            method.setAccessible(true);
            // When invoking nextNonWhitespace with throwOnEof as false
            // Should return '{'
            int result = (int) method.invoke(jsonReader, false);
            // Then assert that the result is the expected non-whitespace character
            assertEquals('{', result);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    void testNextNonWhitespaceCommentHandling() throws Exception {
        // Given a JsonReader with a single-line comment
        String jsonInput = "// This is a comment\n{ \"key\": \"value\" }";
        jsonReader = new JsonReader(new StringReader(jsonInput));
        try {
            Method method = JsonReader.class.getDeclaredMethod("nextNonWhitespace", boolean.class);
            method.setAccessible(true);
            // When invoking nextNonWhitespace with throwOnEof as false
            // Should return '{'
            int result = (int) method.invoke(jsonReader, false);
            // Then assert that the result is the expected non-whitespace character
            assertEquals('{', result);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    void testNextNonWhitespaceMultiLineCommentHandling() throws Exception {
        // Given a JsonReader with a multi-line comment
        String jsonInput = "/* This is a multi-line comment */\n{ \"key\": \"value\" }";
        jsonReader = new JsonReader(new StringReader(jsonInput));
        try {
            Method method = JsonReader.class.getDeclaredMethod("nextNonWhitespace", boolean.class);
            method.setAccessible(true);
            // When invoking nextNonWhitespace with throwOnEof as false
            // Should return '{'
            int result = (int) method.invoke(jsonReader, false);
            // Then assert that the result is the expected non-whitespace character
            assertEquals('{', result);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    void testNextNonWhitespaceHashCommentHandling() throws Exception {
        // Given a JsonReader with a hash comment
        String jsonInput = "# This is a hash comment\n{ \"key\": \"value\" }";
        jsonReader = new JsonReader(new StringReader(jsonInput));
        try {
            Method method = JsonReader.class.getDeclaredMethod("nextNonWhitespace", boolean.class);
            method.setAccessible(true);
            // When invoking nextNonWhitespace with throwOnEof as false
            // Should return '{'
            int result = (int) method.invoke(jsonReader, false);
            // Then assert that the result is the expected non-whitespace character
            assertEquals('{', result);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }
}
