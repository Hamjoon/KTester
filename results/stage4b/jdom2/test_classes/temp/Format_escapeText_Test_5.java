package org.jdom2.output;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.jdom2.IllegalDataException;

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
        String input = "Hello World";
        String expected = "Hello World";
        String result = Format.escapeText(format.getEscapeStrategy(), format.getLineSeparator(), input);
        assertEquals(expected, result);
    }

    @Test
    void testEscapeText_WithEscapingNeeded() {
        String input = "Hello <World>";
        String expected = "Hello &lt;World&gt;";
        when(escapeStrategy.shouldEscape('<')).thenReturn(true);
        when(escapeStrategy.shouldEscape('>')).thenReturn(true);
        when(escapeStrategy.shouldEscape('H')).thenReturn(false);
        when(escapeStrategy.shouldEscape('e')).thenReturn(false);
        when(escapeStrategy.shouldEscape('l')).thenReturn(false);
        when(escapeStrategy.shouldEscape('o')).thenReturn(false);
        when(escapeStrategy.shouldEscape(' ')).thenReturn(false);
        when(escapeStrategy.shouldEscape('W')).thenReturn(false);
        when(escapeStrategy.shouldEscape('r')).thenReturn(false);
        // Added stubbing for 'd'
        when(escapeStrategy.shouldEscape('d')).thenReturn(false);
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
        String input = "Surrogate test: \uD83D\uDE00";
        String expected = "Surrogate test: &#x1f600;";
        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(false);
        when(escapeStrategy.shouldEscape('\uD83D')).thenReturn(false);
        when(escapeStrategy.shouldEscape('\uDE00')).thenReturn(false);
        String result = Format.escapeText(format.getEscapeStrategy(), format.getLineSeparator(), input);
        assertEquals(expected, result);
    }

    @Test
    void testEscapeText_WithLineBreaks() {
        String input = "Line1\nLine2";
        String expected = "Line1" + format.getLineSeparator() + "Line2";
        String result = Format.escapeText(format.getEscapeStrategy(), format.getLineSeparator(), input);
        assertEquals(expected, result);
    }

    @Test
    void testEscapeText_EmptyString() {
        String input = "";
        String expected = "";
        String result = Format.escapeText(format.getEscapeStrategy(), format.getLineSeparator(), input);
        assertEquals(expected, result);
    }

    @Test
    void testEscapeText_WithAllCharactersNeedingEscape() {
        String input = "<>&\n\r";
        String expected = "&lt;&gt;&amp;" + format.getLineSeparator();
        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(true);
        when(escapeStrategy.shouldEscape('\n')).thenReturn(false);
        when(escapeStrategy.shouldEscape('\r')).thenReturn(false);
        String result = Format.escapeText(format.getEscapeStrategy(), format.getLineSeparator(), input);
        assertEquals(expected, result);
    }

    @Test
    void testEscapeText_WithMixedCharacters() {
        String input = "a <b> & c";
        String expected = "&lt;a&gt; &amp; c";
        when(escapeStrategy.shouldEscape('a')).thenReturn(true);
        when(escapeStrategy.shouldEscape('<')).thenReturn(true);
        when(escapeStrategy.shouldEscape('>')).thenReturn(true);
        when(escapeStrategy.shouldEscape('&')).thenReturn(true);
        when(escapeStrategy.shouldEscape(' ')).thenReturn(false);
        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(false);
        String result = Format.escapeText(format.getEscapeStrategy(), format.getLineSeparator(), input);
        assertEquals(expected, result);
    }

    @Test
    void testEscapeText_WithIllegalSurrogatePair() {
        String input = "\uD800";
        assertThrows(IllegalDataException.class, () -> {
            Format.escapeText(format.getEscapeStrategy(), format.getLineSeparator(), input);
        });
    }

    @Test
    void testEscapeText_WithTruncatedSurrogatePair() {
        String input = "\uD83D";
        assertThrows(IllegalDataException.class, () -> {
            Format.escapeText(format.getEscapeStrategy(), format.getLineSeparator(), input);
        });
    }

    @Test
    void testEscapeText_WithCustomLineSeparator() {
        String input = "Line1\nLine2";
        String expected = "Line1---Line2";
        String customEOL = "---";
        String result = Format.escapeText(format.getEscapeStrategy(), customEOL, input);
        assertEquals(expected, result);
    }
}
