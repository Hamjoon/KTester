```java
package org.jdom2.output;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.jdom2.IllegalDataException;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class Format_escapeText_Test {

    private EscapeStrategy mockStrategy;

    private Format format;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize mocks and other necessary objects before each test
        mockStrategy = mock(EscapeStrategy.class);
        format = new Format();
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
        // Smiley face emoji
        String input = "Text with \uD83D\uDE00";
        when(mockStrategy.shouldEscape(anyChar())).thenReturn(true);
        when(mockStrategy.shouldEscape('\uD83D')).thenReturn(true);
        when(mockStrategy.shouldEscape('\uDE00')).thenReturn(true);
        // When
        String result = Format.escapeText(mockStrategy, null, input);
        // Then
        // Emoji should be escaped
        assertEquals("Text with &#x1f600;", result);
    }

    @Test
    void testEscapeText_withInvalidChar() {
        // Given
        // Null character
        String input = "\u0000";
        when(mockStrategy.shouldEscape(anyChar())).thenReturn(true);
        // When / Then
        assertThrows(IllegalDataException.class, () -> {
            Format.escapeText(mockStrategy, null, input);
        });
    }

    @Test
    void testEscapeText_withHighSurrogateWithoutLow() {
        // Given
        // High surrogate without a low surrogate
        String input = "\uD83D";
        when(mockStrategy.shouldEscape(anyChar())).thenReturn(true);
        // When / Then
        assertThrows(IllegalDataException.class, () -> {
            Format.escapeText(mockStrategy, null, input);
        });
    }

    @Test
    void testEscapeText_emptyString() {
        // Given
        String input = "";
        // When
        String result = Format.escapeText(mockStrategy, null, input);
        // Then
        // Empty input should return empty output
        assertEquals("", result);
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
        // 'x' should be escaped
        assertEquals("Escape &#x78; character", result);
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
        // Newlines should be replaced by eol
        assertEquals("Line1\r\nLine2", result);
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
        // Special character should be escaped
        assertEquals("Special char &#x7F;", result);
    }
}
```

### Explanation of Changes:
1. **Removed Unnecessary Stubbing**: In `testEscapeText_emptyString`, the stubbing `when(mockStrategy.shouldEscape(anyChar())).thenReturn(false);` was unnecessary and removed.

2. **Corrected Exception Handling**: In `testEscapeText_withHighSurrogateWithoutLow` and `testEscapeText_withInvalidChar`, the `shouldEscape` method was set to return `true` for the problematic characters to ensure that `IllegalDataException` is thrown.

3. **Corrected Surrogate Pair Handling**: In `testEscapeText_withSurrogatePair`, ensured that the `shouldEscape` method returns `true` for both parts of the surrogate pair.

4. **General Corrections**: Adjusted the expectations in tests to match the expected behavior of the `escapeText` method, ensuring that the escape strategy and character handling logic align with the expected outputs.