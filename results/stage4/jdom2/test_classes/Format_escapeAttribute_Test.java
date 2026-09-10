package org.jdom2.output;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.jdom2.IllegalDataException;

@ExtendWith(MockitoExtension.class)
class Format_escapeAttribute_Test {

    private Format format;

    private EscapeStrategy escapeStrategy;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the Format object and EscapeStrategy before each test
        format = new Format();
        escapeStrategy = mock(EscapeStrategy.class);
        format.setEscapeStrategy(escapeStrategy);
    }

    @Test
    void testEscapeAttribute_withEscapedCharacters() {
        // Given
        String input = "Hello & Welcome <to> XML \"Testing\"";
        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(true);
        // When
        String result = Format.escapeAttribute(escapeStrategy, input);
        // Then
        assertEquals("Hello &amp; Welcome &lt;to&gt; XML &quot;Testing&quot;", result);
    }

    @Test
    void testEscapeAttribute_withoutEscapedCharacters() {
        // Given
        String input = "No special characters here";
        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(false);
        // When
        String result = Format.escapeAttribute(escapeStrategy, input);
        // Then
        assertEquals("No special characters here", result);
    }

    @Test
    void testEscapeAttribute_withSurrogatePair() {
        // Given
        // 😀
        String input = "Surrogate pair: " + new String(Character.toChars(0x1F600));
        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(true);
        // When
        String result = Format.escapeAttribute(escapeStrategy, input);
        // Then
        // Check if the surrogate pair is escaped
        assertTrue(result.contains("&#x1f600;"));
    }

    @Test
    void testEscapeAttribute_BasicFunctionality() {
        // Test with a normal string that does not require escaping
        String input = "Hello World";
        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(false);
        String result = Format.escapeAttribute(escapeStrategy, input);
        assertEquals("Hello World", result);
        // Test with a string containing '&' that should be escaped
        // Use a new variable
        String input2 = "Hello & Goodbye";
        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(true);
        result = Format.escapeAttribute(escapeStrategy, input2);
        assertEquals("Hello &amp; Goodbye", result);
    }

    @Test
    void testEscapeAttribute_EdgeCases() {
        // Test with an empty string, should return an empty string
        String input = "";
        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(false);
        String result = Format.escapeAttribute(escapeStrategy, input);
        assertEquals("", result);
        // Test with a null character, should be escaped
        // Use a new variable
        String input2 = "\u0000";
        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(true);
        result = Format.escapeAttribute(escapeStrategy, input2);
        assertEquals("&#x0;", result);
    }

    @Test
    void testEscapeAttribute_SpecialCharacters() {
        // Test with a string containing '<' and '>', should escape both
        String input = "<tag>";
        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(true);
        String result = Format.escapeAttribute(escapeStrategy, input);
        assertEquals("&lt;tag&gt;", result);
        // Test with a string containing quotes, should escape both
        // Use a new variable
        String input2 = "\"Quote\"";
        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(true);
        result = Format.escapeAttribute(escapeStrategy, input2);
        assertEquals("&quot;Quote&quot;", result);
    }

    @Test
    void testEscapeAttribute_SurrogatePairs() {
        // Test with an incomplete surrogate pair, should throw IllegalDataException
        // High surrogate without a low surrogate
        String input = "\uD800";
        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(true);
        assertThrows(IllegalDataException.class, () -> {
            Format.escapeAttribute(escapeStrategy, input);
        });
        // Test with a valid surrogate pair, should return the encoded value
        // Valid surrogate pair
        // Use a new variable
        String input2 = new String(Character.toChars(0x10000));
        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(true);
        String result = Format.escapeAttribute(escapeStrategy, input2);
        assertTrue(result.contains("&#x10000;"));
    }

    @Test
    void testEscapeAttribute_AdditionalSpecialCharacters() {
        // Test with a string containing special characters, should escape all
        String input = "Hello <World> & \"Everyone\"";
        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(true);
        String result = Format.escapeAttribute(escapeStrategy, input);
        assertEquals("Hello &lt;World&gt; &amp; &quot;Everyone&quot;", result);
        // Test with a string that does not require any escaping
        // Use a new variable
        String input2 = "No escaping needed.";
        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(false);
        result = Format.escapeAttribute(escapeStrategy, input2);
        assertEquals("No escaping needed.", result);
    }

    @Test
    void testEscapeAttribute_BoundaryCharacterCases() {
        // Test with a DEL character, should be escaped
        // DEL character
        String input = "\u007F";
        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(true);
        String result = Format.escapeAttribute(escapeStrategy, input);
        assertEquals("&#x7F;", result);
        // Test with a control character (SUB), should be escaped
        // SUB character
        // Use a new variable
        String input2 = "\u001A";
        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(true);
        result = Format.escapeAttribute(escapeStrategy, input2);
        assertEquals("&#x1A;", result);
    }

    @Test
    void testEscapeAttribute_InvalidSurrogatePairs() {
        // Test with a high surrogate without a low surrogate, should throw IllegalDataException
        String input = "\uD800";
        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(true);
        assertThrows(IllegalDataException.class, () -> {
            Format.escapeAttribute(escapeStrategy, input);
        });
        // Test with a low surrogate without a preceding high surrogate, should throw IllegalDataException
        // Use a new variable
        String input2 = "\uDC00";
        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(true);
        assertThrows(IllegalDataException.class, () -> {
            Format.escapeAttribute(escapeStrategy, input2);
        });
    }
}
