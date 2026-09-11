package org.jdom2.output;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class Format_escapeText_Test {

    private Format format;

    private EscapeStrategy escapeStrategy;

    @BeforeEach
    void setupBeforeEach() {
        format = new Format();
        escapeStrategy = mock(EscapeStrategy.class);
        format.setEscapeStrategy(escapeStrategy);
    }

    @Test
    void testEscapeText_NoEscapingNeeded() {
        // Test with a string that does not contain any characters that need escaping.
        String input = "Hello World";
        String expected = "Hello World";
        String result = Format.escapeText(format.getEscapeStrategy(), format.getLineSeparator(), input);
        assertEquals(expected, result);
    }

    @Test
    void testEscapeText_WithEscapingNeeded() {
        // Test with a string that contains '<' and '>' which need to be escaped.
        String input = "Hello <World>";
        String expected = "Hello &lt;World&gt;";
        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(true);
        String result = Format.escapeText(format.getEscapeStrategy(), format.getLineSeparator(), input);
        assertEquals(expected, result);
    }

    @Test
    void testEscapeText_WithNewLine() {
        String input = "Line 1\nLine 2";
        String expected = "Line 1" + format.getLineSeparator() + "Line 2";
        String result = Format.escapeText(format.getEscapeStrategy(), format.getLineSeparator(), input);
        assertEquals(expected, result);
    }

    @Test
    void testEscapeText_WithSurrogates() {
        // Test with a string containing surrogate characters, expecting them to be escaped.
        // Smiley face emoji
        String input = "Surrogate test: \uD83D\uDE00";
        // Expected escaped representation
        String expected = "Surrogate test: &#x1f600;";
        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(true);
        String result = Format.escapeText(format.getEscapeStrategy(), format.getLineSeparator(), input);
        assertEquals(expected, result);
    }

    @Test
    void testEscapeText_WithLineBreaks() {
        // Test with a string that contains line breaks and a specified eol.
        String input = "Line1\nLine2";
        String expected = "Line1" + format.getLineSeparator() + "Line2";
        String result = Format.escapeText(format.getEscapeStrategy(), "\n", input);
        assertEquals(expected, result);
    }

    @Test
    void testEscapeText_EmptyString() {
        // Test with an empty string, expecting the same empty string as output.
        String input = "";
        String expected = "";
        String result = Format.escapeText(format.getEscapeStrategy(), format.getLineSeparator(), input);
        assertEquals(expected, result);
    }

    @Test
    void testEscapeText_WithAllCharactersNeedingEscape() {
        // Test with a string containing all characters that need escaping, expecting all to be converted.
        String input = "<>&\n\r";
        String expected = "&lt;&gt;&amp;&#xD;\n";
        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(true);
        String result = Format.escapeText(format.getEscapeStrategy(), format.getLineSeparator(), input);
        assertEquals(expected, result);
    }

    @Test
    void testEscapeText_WithMixedCharacters() {
        // Test with a string containing a mix of characters, where only 'a' should be escaped.
        String input = "a <b> & c";
        String expected = "&amp;lt;b&amp;gt; &amp; c";
        when(escapeStrategy.shouldEscape('a')).thenReturn(true);
        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(false);
        String result = Format.escapeText(format.getEscapeStrategy(), format.getLineSeparator(), input);
        assertEquals(expected, result);
    }

    @Test
    void testEscapeText_WithIllegalSurrogatePair() {
        // Test with an illegal surrogate pair, expecting an IllegalDataException to be thrown.
        // High surrogate without a low surrogate
        String input = "\uD800";
        assertThrows(IllegalDataException.class, () -> {
            Format.escapeText(format.getEscapeStrategy(), format.getLineSeparator(), input);
        });
    }

    @Test
    void testEscapeText_WithTruncatedSurrogatePair() {
        // Test with a string containing only a high surrogate character, expecting an IllegalDataException.
        // High surrogate without a low surrogate
        String input = "\uD83D";
        assertThrows(IllegalDataException.class, () -> {
            Format.escapeText(format.getEscapeStrategy(), format.getLineSeparator(), input);
        });
    }

    @Test
    void testEscapeText_WithCustomLineSeparator() {
        // Test with a string containing line breaks and a custom line separator.
        String input = "Line1\nLine2";
        String expected = "Line1---Line2";
        String customEOL = "---";
        String result = Format.escapeText(format.getEscapeStrategy(), customEOL, input);
        assertEquals(expected, result);
    }
}
