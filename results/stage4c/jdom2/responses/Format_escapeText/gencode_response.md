```java
package org.jdom2.output;

import org.jdom2.IllegalDataException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class Format_escapeText_Test {

    private EscapeStrategy mockStrategy;
    private Format format;

    @BeforeAll
    static void setupBeforeAll() {
        // Initialize any static resources if necessary
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize mocks and other necessary objects before each test
        mockStrategy = mock(EscapeStrategy.class);
        format = new Format();
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup resources after each test if necessary
    }

    @AfterAll
    static void teardownAfterAll() {
        // Cleanup any static resources if necessary
    }

    @Test
    void testEscapeText_noEscapingNeeded() {
        // Given
        String input = "No special chars";
        when(mockStrategy.shouldEscape(anyChar())).thenReturn(false);

        // When
        String result = Format.escapeText(mockStrategy, null, input);

        // Then
        assertEquals(input, result);
    }

    @Test
    void testEscapeText_withSpecialChars() {
        // Given
        String input = "Text with < and &";
        when(mockStrategy.shouldEscape(anyChar())).thenReturn(false);

        // When
        String result = Format.escapeText(mockStrategy, null, input);

        // Then
        assertEquals("Text with &lt; and &amp;", result);
    }

    @Test
    void testEscapeText_withCustomEOL() {
        // Given
        String input = "Line1\nLine2";
        String eol = "<br/>";
        when(mockStrategy.shouldEscape(anyChar())).thenReturn(false);

        // When
        String result = Format.escapeText(mockStrategy, eol, input);

        // Then
        assertEquals("Line1<br/>Line2", result);
    }

    @Test
    void testEscapeText_withSurrogatePair() {
        // Given
        String input = "Text with \uD83D\uDE00"; // Smiley face emoji
        when(mockStrategy.shouldEscape(anyChar())).thenReturn(true);
        when(mockStrategy.shouldEscape('\uD83D')).thenReturn(true);
        when(mockStrategy.shouldEscape('\uDE00')).thenReturn(true);

        // When
        String result = Format.escapeText(mockStrategy, null, input);

        // Then
        assertEquals("Text with &#x1f600;", result); // Emoji should be escaped
    }

    @Test
    void testEscapeText_withInvalidChar() {
        // Given
        String input = "\u0000"; // Null character
        when(mockStrategy.shouldEscape(anyChar())).thenReturn(false);

        // When / Then
        assertThrows(IllegalDataException.class, () -> {
            Format.escapeText(mockStrategy, null, input);
        });
    }

    @Test
    void testEscapeText_withHighSurrogateWithoutLow() {
        // Given
        String input = "\uD83D"; // High surrogate without a low surrogate
        when(mockStrategy.shouldEscape(anyChar())).thenReturn(false);

        // When / Then
        assertThrows(IllegalDataException.class, () -> {
            Format.escapeText(mockStrategy, null, input);
        });
    }

    @Test
    void testEscapeText_emptyString() {
        // Given
        String input = "";
        when(mockStrategy.shouldEscape(anyChar())).thenReturn(false);

        // When
        String result = Format.escapeText(mockStrategy, null, input);

        // Then
        assertEquals("", result); // Empty input should return empty output
    }

    @Test
    void testEscapeText_withCustomStrategy() {
        // Given
        String input = "Escape x character";
        when(mockStrategy.shouldEscape('x')).thenReturn(true);
        when(mockStrategy.shouldEscape(anyChar())).thenReturn(false);

        // When
        String result = Format.escapeText(mockStrategy, null, input);

        // Then
        assertEquals("Escape &#x78; character", result); // 'x' should be escaped
    }

    @Test
    void testEscapeText_withCustomEOLAndNewlines() {
        // Given
        String input = "Line1\nLine2";
        String eol = "\r\n";
        when(mockStrategy.shouldEscape(anyChar())).thenReturn(false);

        // When
        String result = Format.escapeText(mockStrategy, eol, input);

        // Then
        assertEquals("Line1\r\nLine2", result); // Newlines should be replaced by eol
    }

    @Test
    void testEscapeText_withSpecialCharacter() {
        // Given
        String input = "Special char \u007F";
        when(mockStrategy.shouldEscape('\u007F')).thenReturn(true);
        when(mockStrategy.shouldEscape(anyChar())).thenReturn(false);

        // When
        String result = Format.escapeText(mockStrategy, null, input);

        // Then
        assertEquals("Special char &#x7F;", result); // Special character should be escaped
    }
}
```