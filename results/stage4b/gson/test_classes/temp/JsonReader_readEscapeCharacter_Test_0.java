package com.google.gson.stream;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import java.io.IOException;
import java.io.StringReader;

@ExtendWith(MockitoExtension.class)
class JsonReader_readEscapeCharacter_Test {

    private JsonReader jsonReader;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the JsonReader with a StringReader for testing escape characters
        jsonReader = new JsonReader(new StringReader(""));
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup after each test if necessary
        jsonReader = null;
    }

    private char readEscapeCharacterFromJson(String json) throws IOException {
        // Helper method to read the escape character from the provided JSON string
        jsonReader = new JsonReader(new StringReader(json));
        jsonReader.beginObject();
        // Move to the next name
        jsonReader.nextName();
        return invokePrivateReadEscapeCharacter();
    }

    private char invokePrivateReadEscapeCharacter() throws IOException {
        // Use reflection to access the private method readEscapeCharacter
        try {
            java.lang.reflect.Method method = JsonReader.class.getDeclaredMethod("readEscapeCharacter");
            method.setAccessible(true);
            return (char) method.invoke(jsonReader);
        } catch (Exception e) {
            throw new IOException("Failed to invoke readEscapeCharacter", e);
        }
    }

    @Test
    void testReadEscapeCharacter_UniCode() throws IOException {
        // Represents the character 'A'
        jsonReader = new JsonReader(new StringReader("\"\\u0041\""));
        jsonReader.beginObject();
        String name = jsonReader.nextName();
        assertEquals("A", jsonReader.nextString());
        jsonReader.endObject();
    }

    @Test
    void testReadEscapeCharacter_SingleQuote() throws IOException {
        // Represents the character '\''
        jsonReader = new JsonReader(new StringReader("\"\\'\""));
        jsonReader.beginObject();
        String name = jsonReader.nextName();
        assertEquals("'", jsonReader.nextString());
        jsonReader.endObject();
    }

    @Test
    void testReadEscapeCharacter_DoubleQuote() throws IOException {
        // Represents the character '"'
        jsonReader = new JsonReader(new StringReader("\"\\\"\""));
        jsonReader.beginObject();
        String name = jsonReader.nextName();
        assertEquals("\"", jsonReader.nextString());
        jsonReader.endObject();
    }

    @Test
    void testReadEscapeCharacter_Backslash() throws IOException {
        // Represents the character '\'
        jsonReader = new JsonReader(new StringReader("\"\\\\\""));
        jsonReader.beginObject();
        String name = jsonReader.nextName();
        assertEquals("\\", jsonReader.nextString());
        jsonReader.endObject();
    }

    @Test
    void testReadEscapeCharacter_Invalid() {
        // Invalid escape sequence
        jsonReader = new JsonReader(new StringReader("\"\\x\""));
        jsonReader.beginObject();
        assertThrows(IOException.class, () -> {
            jsonReader.nextString();
        });
        jsonReader.endObject();
    }

    @Test
    void testReadEscapeCharacter_ValidEscapeSequences() throws IOException {
        // Test for valid escape sequences
        // Tab character
        assertEquals('\t', readEscapeCharacterFromJson("\"\\t\""));
        // Newline character
        assertEquals('\n', readEscapeCharacterFromJson("\"\\n\""));
        // Carriage return character
        assertEquals('\r', readEscapeCharacterFromJson("\"\\r\""));
        // Form feed character
        assertEquals('\f', readEscapeCharacterFromJson("\"\\f\""));
        // Backslash character
        assertEquals('\\', readEscapeCharacterFromJson("\"\\\\\""));
        // Single quote character
        assertEquals('\'', readEscapeCharacterFromJson("\"\\'\""));
        // Double quote character
        assertEquals('\"', readEscapeCharacterFromJson("\"\\\"\""));
        // Backspace character
        assertEquals('\b', readEscapeCharacterFromJson("\"\\b\""));
    }

    @Test
    void testReadEscapeCharacter_UnicodeEscapeSequences() throws IOException {
        // Test for valid unicode escape sequences
        // Unicode for 'A'
        assertEquals('A', readEscapeCharacterFromJson("\"\\u0041\""));
        // Unicode for 'Ω'
        assertEquals('Ω', readEscapeCharacterFromJson("\"\\u03A9\""));
        // Unicode for ©
        assertEquals('©', readEscapeCharacterFromJson("\"\\u00A9\""));
        // Unicode for €
        assertEquals('€', readEscapeCharacterFromJson("\"\\u20AC\""));
    }

    @Test
    void testReadEscapeCharacter_InvalidEscapeSequences() {
        // Test for invalid escape sequences
        assertThrows(NumberFormatException.class, () -> {
            // Invalid unicode
            readEscapeCharacterFromJson("\"\\uXYZ1\"");
        });
        assertThrows(IOException.class, () -> {
            // Invalid escape
            readEscapeCharacterFromJson("\"\\q\"");
        });
        assertThrows(IOException.class, () -> {
            // Invalid escape
            readEscapeCharacterFromJson("\"\\x\"");
        });
        assertThrows(IOException.class, () -> {
            // Invalid escape
            readEscapeCharacterFromJson("\"\\g\"");
        });
    }

    @Test
    void testReadEscapeCharacter_BufferHandling() {
        // Test for buffer handling cases
        assertThrows(IOException.class, () -> {
            // Unterminated escape sequence
            readEscapeCharacterFromJson("\"\\");
        });
        assertThrows(IOException.class, () -> {
            // Unterminated unicode escape
            readEscapeCharacterFromJson("\"\\u004\"");
        });
    }
}
