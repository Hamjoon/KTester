```java
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
    private String endOfLine;

    @BeforeAll
    static void setupBeforeAll() {
        // Any global setup can be done here
    }

    @BeforeEach
    void setupBeforeEach() {
        format = new Format(); // Initialize Format instance
        escapeStrategy = mock(EscapeStrategy.class); // Mock EscapeStrategy
        format.setEscapeStrategy(escapeStrategy); // Set the mocked escape strategy
        endOfLine = "\n"; // Set end of line character
    }

    @AfterEach
    void teardownAfterEach() {
        // Any cleanup after each test can be done here
    }

    @AfterAll
    static void teardownAfterAll() {
        // Any global cleanup can be done here
    }

    @Test
    void testEscapeText_NoEscapingNeeded() {
        String input = "Hello World!";
        String expected = "Hello World!";
        
        // Assuming the strategy does not require any escaping
        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(false);
        
        String result = Format.escapeText(escapeStrategy, null, input);
        assertEquals(expected, result);
    }

    @Test
    void testEscapeText_WithEscaping() {
        String input = "Hello & World";
        String expected = "Hello &amp; World";

        // Assuming the strategy requires escaping for '&'
        when(escapeStrategy.shouldEscape(anyChar())).thenAnswer(invocation -> {
            char ch = invocation.getArgument(0);
            return ch == '&';
        });

        String result = Format.escapeText(escapeStrategy, null, input);
        assertEquals(expected, result);
    }

    @Test
    void testEscapeText_WithLessThanCharacter() {
        String input = "Hello < World";
        String expected = "Hello &lt; World";

        // Assuming the strategy requires escaping for '<'
        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(false);
        
        String result = Format.escapeText(escapeStrategy, null, input);
        assertEquals(expected, result);
    }

    @Test
    void testEscapeText_WithGreaterThanCharacter() {
        String input = "Hello > World";
        String expected = "Hello &gt; World";

        // Assuming the strategy does not require escaping
        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(false);

        String result = Format.escapeText(escapeStrategy, null, input);
        assertEquals(expected, result);
    }

    @Test
    void testEscapeText_WithCarriageReturnAndNewline() {
        String input = "Hello \r\n World";
        String expected = "Hello &#xD;\n World"; // Expecting CR to be escaped

        // Assuming the strategy does not require escaping
        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(false);

        String result = Format.escapeText(escapeStrategy, null, input);
        assertEquals(expected, result);
    }

    @Test
    void testEscapeText_WithEndOfLineSpecified() {
        String input = "Hello\nWorld";
        String expected = "Hello\nWorld"; // EOL is specified

        // Assuming the strategy requires escaping for newline
        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(true);

        String result = Format.escapeText(escapeStrategy, "\n", input);
        assertEquals(expected, result);
    }

    @Test
    void testEscapeText_WithEmptyString() {
        String input = "";
        String expected = "";

        // No characters need escaping
        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(false);

        String result = Format.escapeText(escapeStrategy, null, input);
        assertEquals(expected, result);
    }

    @Test
    void testEscapeText_WithNullCharacter() {
        String input = "\u0000"; // Null character
        assertThrows(IllegalDataException.class, () -> {
            Format.escapeText(escapeStrategy, null, input);
        });
    }

    @Test
    void testEscapeText_WithControlCharacter() {
        String input = "\u0001"; // Control character
        String expected = "&#x1;"; // Expected escape for control character

        // Assuming the strategy requires escaping for control characters
        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(true);

        String result = Format.escapeText(escapeStrategy, null, input);
        assertEquals(expected, result);
    }

    @Test
    void testEscapeText_WithDelCharacter() {
        String input = "\u007F"; // DEL character
        String expected = "&#x7F;"; // Expected escape for DEL character

        // Assuming the strategy requires escaping for DEL character
        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(true);

        String result = Format.escapeText(escapeStrategy, null, input);
        assertEquals(expected, result);
    }

    @Test
    void testEscapeText_WithSurrogatePair() {
        String input = "Surrogate: \uD83D\uDE00"; // Grinning Face emoji
        String expected = "Surrogate: &#x1f600;"; // Expected escape for the emoji

        // Assuming the strategy requires escaping for surrogates
        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(true);

        String result = Format.escapeText(escapeStrategy, null, input);
        assertEquals(expected, result);
    }

    @Test
    void testEscapeText_WithInvalidSurrogate() {
        String input = "Invalid Surrogate: \uD83D"; // Incomplete surrogate
        assertThrows(IllegalDataException.class, () -> {
            Format.escapeText(escapeStrategy, null, input);
        });
    }
}
```