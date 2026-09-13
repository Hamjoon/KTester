package org.jdom2.output;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
// Add other imports if necessary
import org.jdom2.IllegalDataException;

@ExtendWith(MockitoExtension.class)
class Format_escapeAttribute_Test {

    @Mock
    private EscapeStrategy mockEscapeStrategy;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize mocks before each test
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testEscapeAttribute_noSpecialCharacters() {
        // Given
        String input = "Hello World";
        when(mockEscapeStrategy.shouldEscape(anyChar())).thenReturn(false);
        // When
        String result = Format.escapeAttribute(mockEscapeStrategy, input);
        // Then
        assertEquals("Hello World", result);
    }

    @Test
    void testEscapeAttribute_withSpecialCharacters() {
        // Given
        String input = "text<with&special>chars";
        when(mockEscapeStrategy.shouldEscape(anyChar())).thenReturn(false);
        // When
        String result = Format.escapeAttribute(mockEscapeStrategy, input);
        // Then
        assertEquals("text&lt;with&amp;special&gt;chars", result);
    }

    @Test
    void testEscapeAttribute_withCustomEscapeStrategy() {
        // Given
        String input = "customEscape";
        when(mockEscapeStrategy.shouldEscape('c')).thenReturn(true);
        // When
        String result = Format.escapeAttribute(mockEscapeStrategy, input);
        // Then
        assertEquals("&#x63;ustomEscape", result);
    }

    @Test
    void testEscapeAttribute_withAmpersand() {
        // Given
        String input = "Hello & Goodbye";
        when(mockEscapeStrategy.shouldEscape(anyChar())).thenReturn(false);
        // When
        String result = Format.escapeAttribute(mockEscapeStrategy, input);
        // Then
        assertEquals("Hello &amp; Goodbye", result);
    }

    @Test
    void testEscapeAttribute_withCarriageReturn() {
        // Given
        String input = "Line1\rLine2";
        when(mockEscapeStrategy.shouldEscape(anyChar())).thenReturn(false);
        // When
        String result = Format.escapeAttribute(mockEscapeStrategy, input);
        // Then
        assertEquals("Line1&#xD;Line2", result);
    }

    @Test
    void testEscapeAttribute_withNewline() {
        // Given
        String input = "Line1\nLine2";
        when(mockEscapeStrategy.shouldEscape(anyChar())).thenReturn(false);
        // When
        String result = Format.escapeAttribute(mockEscapeStrategy, input);
        // Then
        assertEquals("Line1&#xA;Line2", result);
    }

    @Test
    void testEscapeAttribute_withTab() {
        // Given
        String input = "Tab\tCharacter";
        when(mockEscapeStrategy.shouldEscape(anyChar())).thenReturn(false);
        // When
        String result = Format.escapeAttribute(mockEscapeStrategy, input);
        // Then
        assertEquals("Tab&#x9;Character", result);
    }

    @Test
    void testEscapeAttribute_withQuote() {
        // Given
        String input = "Quote\"Here";
        when(mockEscapeStrategy.shouldEscape(anyChar())).thenReturn(false);
        // When
        String result = Format.escapeAttribute(mockEscapeStrategy, input);
        // Then
        assertEquals("Quote&quot;Here", result);
    }

    @Test
    void testEscapeAttribute_nonPrintableASCII() {
        // Given
        String input = "\u001F";
        when(mockEscapeStrategy.shouldEscape(anyChar())).thenReturn(false);
        // When
        String result = Format.escapeAttribute(mockEscapeStrategy, input);
        // Then
        assertEquals("&#x1F;", result);
    }

    @Test
    void testEscapeAttribute_nullCharacter() {
        // Given
        String input = "\u0000";
        when(mockEscapeStrategy.shouldEscape(anyChar())).thenReturn(false);
        // When
        String result = Format.escapeAttribute(mockEscapeStrategy, input);
        // Then
        assertEquals("&#x0;", result);
    }

    @Test
    void testEscapeAttribute_multipleNonPrintableASCII() {
        // Given
        String input = "\u001F\u001F";
        when(mockEscapeStrategy.shouldEscape(anyChar())).thenReturn(false);
        // When
        String result = Format.escapeAttribute(mockEscapeStrategy, input);
        // Then
        assertEquals("&#x1F;&#x1F;", result);
    }

    @Test
    void testEscapeAttribute_surrogateException() {
        // Given
        String input = "\uD800";
        when(mockEscapeStrategy.shouldEscape(anyChar())).thenReturn(false);
        // When & Then
        assertThrows(IllegalDataException.class, () -> {
            Format.escapeAttribute(mockEscapeStrategy, input);
        });
    }

    @Test
    void testEscapeAttribute_validSurrogatePair() {
        // Given
        String input = "\uD800\uDC00";
        when(mockEscapeStrategy.shouldEscape(anyChar())).thenReturn(false);
        // When
        String result = Format.escapeAttribute(mockEscapeStrategy, input);
        // Then
        assertEquals("&#x10000;", result);
    }
}
