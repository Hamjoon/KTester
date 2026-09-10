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

    private String endOfLine;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize Format instance
        format = new Format();
        // Mock EscapeStrategy
        escapeStrategy = mock(EscapeStrategy.class);
        // Set the mocked escape strategy
        format.setEscapeStrategy(escapeStrategy);
        // Set end of line character
        endOfLine = "\n";
    }

    @Test
    void testEscapeText_NoEscapingNeeded() {
        String input = "Hello World!";
        String expected = "Hello World!";
        // Assuming the strategy does not require any escaping
        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(false);
        String result = Format.escapeText(escapeStrategy, endOfLine, input);
        assertEquals(expected, result);
    }

    @Test
    void testEscapeText_WithEscaping() {
        String input = "Hello <World> & Co.";
        String expected = "Hello &lt;World&gt; &amp; Co.";
        // Assuming the strategy requires escaping for < and &
        when(escapeStrategy.shouldEscape(anyChar())).thenAnswer(invocation -> {
            char ch = invocation.getArgument(0);
            return ch == '<' || ch == '&';
        });
        String result = Format.escapeText(escapeStrategy, endOfLine, input);
        assertEquals(expected, result);
    }

    @Test
    void testEscapeText_WithNewline() {
        String input = "Line1\nLine2";
        // Assuming endOfLine is set to "\n"
        String expected = "Line1\nLine2";
        // Assuming the strategy does not require any escaping
        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(false);
        String result = Format.escapeText(escapeStrategy, endOfLine, input);
        assertEquals(expected, result);
    }

    @Test
    void testEscapeText_WithSurrogatePair() {
        // Grinning Face emoji
        String input = "Surrogate: \uD83D\uDE00";
        // Expected escape for the emoji
        String expected = "Surrogate: &#x1F600;";
        // Assuming the strategy requires escaping for surrogates
        when(escapeStrategy.shouldEscape(anyChar())).thenAnswer(invocation -> {
            char ch = invocation.getArgument(0);
            return Character.isHighSurrogate(ch) || Character.isLowSurrogate(ch);
        });
        String result = Format.escapeText(escapeStrategy, endOfLine, input);
        assertEquals(expected, result);
    }

    @Test
    void testEscapeText_WithLessThanCharacter() {
        String input = "Hello < World";
        String expected = "Hello &lt; World";
        // Assuming the strategy requires escaping for '<'
        when(escapeStrategy.shouldEscape(anyChar())).thenAnswer(invocation -> {
            char ch = invocation.getArgument(0);
            return ch == '<';
        });
        String result = Format.escapeText(escapeStrategy, endOfLine, input);
        assertEquals(expected, result);
    }

    @Test
    void testEscapeText_WithGreaterThanCharacter() {
        String input = "Hello > World";
        String expected = "Hello &gt; World";
        // Assuming the strategy requires escaping for '>'
        when(escapeStrategy.shouldEscape(anyChar())).thenAnswer(invocation -> {
            char ch = invocation.getArgument(0);
            return ch == '>';
        });
        String result = Format.escapeText(escapeStrategy, endOfLine, input);
        assertEquals(expected, result);
    }

    @Test
    void testEscapeText_WithCarriageReturnAndNewline() {
        String input = "Hello \r\n World";
        // Expecting CR to be escaped
        String expected = "Hello &#xD;\n World";
        // Assuming the strategy requires escaping for carriage return
        when(escapeStrategy.shouldEscape(anyChar())).thenAnswer(invocation -> {
            char ch = invocation.getArgument(0);
            return ch == '\r';
        });
        String result = Format.escapeText(escapeStrategy, endOfLine, input);
        assertEquals(expected, result);
    }

    @Test
    void testEscapeText_WithEndOfLineSpecified() {
        String input = "Hello\nWorld";
        // EOL is specified
        String expected = "Hello\nWorld";
        // Assuming the strategy does not require escaping
        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(false);
        String result = Format.escapeText(escapeStrategy, "\n", input);
        assertEquals(expected, result);
    }

    @Test
    void testEscapeText_WithEmptyString() {
        String input = "";
        String expected = "";
        // No characters need escaping
        String result = Format.escapeText(escapeStrategy, null, input);
        assertEquals(expected, result);
    }

    @Test
    void testEscapeText_WithNullCharacter() {
        // Null character
        String input = "\u0000";
        assertThrows(IllegalDataException.class, () -> {
            Format.escapeText(escapeStrategy, null, input);
        });
    }

    @Test
    void testEscapeText_WithControlCharacter() {
        // Control character
        String input = "\u0001";
        // Expected escape for control character
        String expected = "&#x1;";
        // Assuming the strategy requires escaping for control characters
        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(true);
        String result = Format.escapeText(escapeStrategy, null, input);
        assertEquals(expected, result);
    }

    @Test
    void testEscapeText_WithDelCharacter() {
        // DEL character
        String input = "\u007F";
        // Expected escape for DEL character
        String expected = "&#x7F;";
        // Assuming the strategy requires escaping for DEL character
        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(true);
        String result = Format.escapeText(escapeStrategy, null, input);
        assertEquals(expected, result);
    }

    @Test
    void testEscapeText_WithInvalidSurrogate() {
        // Incomplete surrogate
        String input = "Invalid Surrogate: \uD83D";
        assertThrows(IllegalDataException.class, () -> {
            Format.escapeText(escapeStrategy, null, input);
        });
    }
}
