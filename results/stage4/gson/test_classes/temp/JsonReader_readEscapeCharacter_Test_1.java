package com.google.gson.stream;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.IOException;
import java.io.StringReader;

@ExtendWith(MockitoExtension.class)
class JsonReader_readEscapeCharacter_Test {

    private JsonReader jsonReader;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the JsonReader with a StringReader
        // Example input with an escape character
        String input = "\"\\u0041\"";
        jsonReader = new JsonReader(new StringReader(input));
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup after each test if necessary
        try {
            jsonReader.close();
        } catch (IOException e) {
            fail("Failed to close JsonReader: " + e.getMessage());
        }
    }

    private char invokeReadEscapeCharacter() throws IOException {
        // Use reflection to invoke the private method readEscapeCharacter
        try {
            var method = JsonReader.class.getDeclaredMethod("readEscapeCharacter");
            method.setAccessible(true);
            return (char) method.invoke(jsonReader);
        } catch (Exception e) {
            throw new IOException("Failed to invoke readEscapeCharacter", e);
        }
    }

    @Test
    void testReadEscapeCharacter_UniCode() throws IOException {
        // Given: a JSON string containing a unicode escape sequence
        jsonReader.beginObject();
        // Move to the next name
        jsonReader.nextName();
        // Invoke the focal method using reflection
        char result = invokeReadEscapeCharacter();
        // Then: it should correctly read the unicode character 'A'
        assertEquals('A', result);
        jsonReader.endObject();
    }

    @Test
    void testReadEscapeCharacter_SingleQuote() throws IOException {
        // Given: a JSON string containing a single quote escape
        jsonReader = new JsonReader(new StringReader("\"\\'\""));
        jsonReader.beginObject();
        // Move to the next name
        jsonReader.nextName();
        // Invoke the focal method using reflection
        char result = invokeReadEscapeCharacter();
        // Then: it should correctly read the single quote character
        assertEquals('\'', result);
        jsonReader.endObject();
    }

    @Test
    void testReadEscapeCharacter_DoubleQuote() throws IOException {
        // Given: a JSON string containing a double quote escape
        jsonReader = new JsonReader(new StringReader("\"\\\"\""));
        jsonReader.beginObject();
        // Move to the next name
        jsonReader.nextName();
        // Invoke the focal method using reflection
        char result = invokeReadEscapeCharacter();
        // Then: it should correctly read the double quote character
        assertEquals('"', result);
        jsonReader.endObject();
    }

    @Test
    void testReadEscapeCharacter_Backslash() throws IOException {
        // Given: a JSON string containing a backslash escape
        jsonReader = new JsonReader(new StringReader("\"\\\\\""));
        jsonReader.beginObject();
        // Move to the next name
        jsonReader.nextName();
        // Invoke the focal method using reflection
        char result = invokeReadEscapeCharacter();
        // Then: it should correctly read the backslash character
        assertEquals('\\', result);
        jsonReader.endObject();
    }

    @Test
    void testReadEscapeCharacter_InvalidEscape() {
        // Given: a JSON string with an invalid escape sequence
        jsonReader = new JsonReader(new StringReader("\"\\z\""));
        jsonReader.beginObject();
        // Move to the next name
        jsonReader.nextName();
        // When: trying to read an invalid escape character
        Exception exception = assertThrows(IOException.class, () -> {
            // Invoke the focal method using reflection
            invokeReadEscapeCharacter();
        });
        // Then: an IOException should be thrown
        assertTrue(exception.getMessage().contains("Invalid escape sequence"));
        jsonReader.endObject();
    }

    @Test
    void testReadEscapeCharacter_ValidEscapeSequences() throws IOException {
        // Given: a JSON string containing various valid escape sequences
        String[] escapeSequences = { "\\t", "\\b", "\\n", "\\r", "\\f", "\\'", "\\\"", "\\\\", "\\/" };
        char[] expectedResults = { '\t', '\b', '\n', '\r', '\f', '\'', '"', '\\', '/' };
        for (int i = 0; i < escapeSequences.length; i++) {
            jsonReader = new JsonReader(new StringReader("\"" + escapeSequences[i] + "\""));
            jsonReader.beginObject();
            // Move to the next name
            jsonReader.nextName();
            // Invoke the focal method using reflection
            char result = invokeReadEscapeCharacter();
            assertEquals(expectedResults[i], result, "Failed for escape sequence: " + escapeSequences[i]);
            jsonReader.endObject();
        }
    }

    @Test
    void testReadEscapeCharacter_UnicodeEscape() throws IOException {
        // Given: a JSON string containing a unicode escape sequence for 'Ω'
        jsonReader = new JsonReader(new StringReader("\"\\u03A9\""));
        jsonReader.beginObject();
        // Move to the next name
        jsonReader.nextName();
        // Invoke the focal method using reflection
        char result = invokeReadEscapeCharacter();
        // Then: it should correctly read the unicode character 'Ω'
        assertEquals('Ω', result);
        jsonReader.endObject();
    }

    @Test
    void testReadEscapeCharacter_UnterminatedEscape() {
        // Given: a JSON string with an unterminated unicode escape sequence
        jsonReader = new JsonReader(new StringReader("\"\\u\""));
        jsonReader.beginObject();
        // Move to the next name
        jsonReader.nextName();
        // When: trying to read an unterminated escape character
        Exception exception = assertThrows(IOException.class, () -> {
            // Invoke the focal method using reflection
            invokeReadEscapeCharacter();
        });
        // Then: an IOException should be thrown
        assertTrue(exception.getMessage().contains("Unterminated escape sequence"));
        jsonReader.endObject();
    }

    @Test
    void testReadEscapeCharacter_InvalidUnicode() {
        // Given: a JSON string with an invalid unicode escape sequence
        jsonReader = new JsonReader(new StringReader("\"\\uG123\""));
        jsonReader.beginObject();
        // Move to the next name
        jsonReader.nextName();
        // When: trying to read an invalid unicode escape character
        Exception exception = assertThrows(NumberFormatException.class, () -> {
            // Invoke the focal method using reflection
            invokeReadEscapeCharacter();
        });
        // Then: a NumberFormatException should be thrown
        assertTrue(exception.getMessage().contains("\\uG123"));
        jsonReader.endObject();
    }

    @Test
    void testReadEscapeCharacter_BufferEmpty() {
        // Given: a JSON string where the buffer is empty
        // No characters available to read
        jsonReader = new JsonReader(new StringReader("\"\\u\""));
        // When: trying to read an escape character
        Exception exception = assertThrows(IOException.class, () -> {
            // Invoke the focal method using reflection
            invokeReadEscapeCharacter();
        });
        // Then: an IOException should be thrown
        assertTrue(exception.getMessage().contains("Unterminated escape sequence"));
    }
}
