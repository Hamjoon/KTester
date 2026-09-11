```java
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

    @BeforeAll
    static void setupBeforeAll() {
        // Any global setup can be done here, if necessary
    }

    @BeforeEach
    void setupBeforeEach() {
        format = new Format();
        escapeStrategy = mock(EscapeStrategy.class);
        format.setEscapeStrategy(escapeStrategy);
    }

    @AfterEach
    void teardownAfterEach() {
        // Any cleanup after each test can be done here
    }

    @AfterAll
    static void teardownAfterAll() {
        // Any global cleanup can be done here, if necessary
    }

    @Test
    void testEscapeAttribute_withNoCharactersToEscape() {
        String input = "normalString";
        String expected = "normalString";

        String result = Format.escapeAttribute(escapeStrategy, input);

        assertEquals(expected, result);
        verify(escapeStrategy, never()).shouldEscape(anyChar());
    }

    @Test
    void testEscapeAttribute_withSpecialCharacters() {
        String input = "stringWith<special> & characters\"";
        String expected = "stringWith&lt;special&gt; &amp; characters&quot;";

        // Mock the behavior of the escape strategy
        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(true);

        String result = Format.escapeAttribute(escapeStrategy, input);

        assertEquals(expected, result);
        verify(escapeStrategy, times(6)).shouldEscape(anyChar());
    }

    // Additional test cases can be added here
}
```