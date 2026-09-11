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

    @BeforeAll
    static void setupBeforeAll() {
        // Code to run once before all tests, if needed
    }

    @BeforeEach
    void setupBeforeEach() {
        format = new Format();
        escapeStrategy = mock(EscapeStrategy.class);
        format.setEscapeStrategy(escapeStrategy);
    }

    @AfterEach
    void teardownAfterEach() {
        // Code to run after each test, if needed
    }

    @AfterAll
    static void teardownAfterAll() {
        // Code to run once after all tests, if needed
    }

    @Test
    void testEscapeText_NoEscapingNeeded() {
        String input = "Some plain text";
        String expected = "Some plain text";

        String result = Format.escapeText(format.getEscapeStrategy(), format.getLineSeparator(), input);
        
        assertEquals(expected, result);
    }

    @Test
    void testEscapeText_WithEscapingNeeded() {
        String input = "Some text with <tag> & special characters";
        String expected = "Some text with &lt;tag&gt; &amp; special characters";

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
        String input = "Surrogate test: \uD83D\uDE00"; // Smiley face emoji
        String expected = "Surrogate test: &#x1f600;"; // Expected escaped representation

        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(true);
        
        String result = Format.escapeText(format.getEscapeStrategy(), format.getLineSeparator(), input);
        
        assertEquals(expected, result);
    }
}
```