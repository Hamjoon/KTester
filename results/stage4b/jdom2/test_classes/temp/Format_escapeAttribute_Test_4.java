package org.jdom2.output;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class Format_escapeAttribute_Test {

    private Format format;

    private EscapeStrategy escapeStrategy;

    @BeforeEach
    void setupBeforeEach() {
        format = new Format();
        escapeStrategy = mock(EscapeStrategy.class);
        format.setEscapeStrategy(escapeStrategy);
    }

    @Test
    void testEscapeAttribute_withNoCharactersToEscape() {
        String input = "normalString";
        String expected = "normalString";
        // Mock the behavior to not escape any character for this test
        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(false);
        String result = Format.escapeAttribute(escapeStrategy, input);
        assertEquals(expected, result);
        verify(escapeStrategy, times(input.length())).shouldEscape(anyChar());
    }

    @Test
    void testEscapeAttribute_withSpecialCharacters() {
        String input = "stringWith<special> & characters\"";
        String expected = "stringWith&lt;special&gt; &amp; characters&quot;";
        // Mock the behavior of the escape strategy
        when(escapeStrategy.shouldEscape(anyChar())).thenAnswer(invocation -> {
            char ch = invocation.getArgument(0);
            return ch == '<' || ch == '>' || ch == '&' || ch == '"';
        });
        String result = Format.escapeAttribute(escapeStrategy, input);
        assertEquals(expected, result);
        // Verify that shouldEscape was called for all characters
        for (char ch : input.toCharArray()) {
            verify(escapeStrategy, times(1)).shouldEscape(ch);
        }
    }
}
