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
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testEscapeAttribute_noSpecialCharacters() {
        String input = "Hello World";
        when(mockEscapeStrategy.shouldEscape(anyChar())).thenReturn(false);
        String result = Format.escapeAttribute(mockEscapeStrategy, input);
        assertEquals("Hello World", result);
    }

    @Test
    void testEscapeAttribute_withSpecialCharacters() {
        String input = "text<with&special>chars";
        when(mockEscapeStrategy.shouldEscape(anyChar())).thenReturn(true);
        String result = Format.escapeAttribute(mockEscapeStrategy, input);
        assertEquals("text&lt;with&amp;special&gt;chars", result);
    }

    @Test
    void testEscapeAttribute_withCustomEscapeStrategy() {
        String input = "customEscape";
        when(mockEscapeStrategy.shouldEscape('c')).thenReturn(true);
        when(mockEscapeStrategy.shouldEscape('E')).thenReturn(true);
        when(mockEscapeStrategy.shouldEscape(anyChar())).thenReturn(false);
        String result = Format.escapeAttribute(mockEscapeStrategy, input);
        assertEquals("&#x63;ustomEs&#x63;ape", result);
    }

    @Test
    void testEscapeAttribute_withAmpersand() {
        String input = "Hello & Goodbye";
        when(mockEscapeStrategy.shouldEscape(anyChar())).thenReturn(true);
        String result = Format.escapeAttribute(mockEscapeStrategy, input);
        assertEquals("Hello &amp; Goodbye", result);
    }

    @Test
    void testEscapeAttribute_withCarriageReturn() {
        String input = "Line1\rLine2";
        when(mockEscapeStrategy.shouldEscape(anyChar())).thenReturn(true);
        String result = Format.escapeAttribute(mockEscapeStrategy, input);
        assertEquals("Line1&#xD;Line2", result);
    }

    @Test
    void testEscapeAttribute_withNewline() {
        String input = "Line1\nLine2";
        when(mockEscapeStrategy.shouldEscape(anyChar())).thenReturn(true);
        String result = Format.escapeAttribute(mockEscapeStrategy, input);
        assertEquals("Line1&#xA;Line2", result);
    }

    @Test
    void testEscapeAttribute_withTab() {
        String input = "Tab\tCharacter";
        when(mockEscapeStrategy.shouldEscape(anyChar())).thenReturn(true);
        String result = Format.escapeAttribute(mockEscapeStrategy, input);
        assertEquals("Tab&#x9;Character", result);
    }

    @Test
    void testEscapeAttribute_withQuote() {
        String input = "Quote\"Here";
        when(mockEscapeStrategy.shouldEscape(anyChar())).thenReturn(true);
        String result = Format.escapeAttribute(mockEscapeStrategy, input);
        assertEquals("Quote&quot;Here", result);
    }

    @Test
    void testEscapeAttribute_nonPrintableASCII() {
        String input = "\u001F";
        when(mockEscapeStrategy.shouldEscape(anyChar())).thenReturn(true);
        String result = Format.escapeAttribute(mockEscapeStrategy, input);
        assertEquals("&#x1F;", result);
    }

    @Test
    void testEscapeAttribute_nullCharacter() {
        String input = "\u0000";
        when(mockEscapeStrategy.shouldEscape(anyChar())).thenReturn(true);
        String result = Format.escapeAttribute(mockEscapeStrategy, input);
        assertEquals("&#x0;", result);
    }

    @Test
    void testEscapeAttribute_multipleNonPrintableASCII() {
        String input = "\u001F\u001F";
        when(mockEscapeStrategy.shouldEscape(anyChar())).thenReturn(true);
        String result = Format.escapeAttribute(mockEscapeStrategy, input);
        assertEquals("&#x1F;&#x1F;", result);
    }

    @Test
    void testEscapeAttribute_surrogateException() {
        String input = "\uD800";
        when(mockEscapeStrategy.shouldEscape(anyChar())).thenReturn(true);
        assertThrows(IllegalDataException.class, () -> {
            Format.escapeAttribute(mockEscapeStrategy, input);
        });
    }

    @Test
    void testEscapeAttribute_validSurrogatePair() {
        String input = "\uD800\uDC00";
        when(mockEscapeStrategy.shouldEscape(anyChar())).thenReturn(true);
        String result = Format.escapeAttribute(mockEscapeStrategy, input);
        assertEquals("&#x10000;", result);
    }
}
