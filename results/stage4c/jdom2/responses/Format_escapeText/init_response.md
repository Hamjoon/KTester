```java
package org.jdom2.output;

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
}
```