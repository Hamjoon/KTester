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
        String expected = "Line1\nLine2"; // Assuming endOfLine is set to "\n"

        // Assuming the strategy does not require any escaping
        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(false);

        String result = Format.escapeText(escapeStrategy, endOfLine, input);
        assertEquals(expected, result);
    }

    @Test
    void testEscapeText_WithSurrogatePair() {
        String input = "Surrogate: \uD83D\uDE00"; // Grinning Face emoji
        String expected = "Surrogate: &#x1f600;"; // Expected escape for the emoji

        // Assuming the strategy requires escaping for surrogates
        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(true);

        String result = Format.escapeText(escapeStrategy, endOfLine, input);
        assertEquals(expected, result);
    }
}
```