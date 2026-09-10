```java
package org.jdom2.output;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

// Add other imports if necessary

@ExtendWith(MockitoExtension.class)
class Format_escapeAttribute_Test {
    private Format format;
    private EscapeStrategy escapeStrategy;

    @BeforeAll
    static void setupBeforeAll() {
        // Any global setup can be done here if needed
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the Format object and EscapeStrategy before each test
        format = new Format();
        escapeStrategy = mock(EscapeStrategy.class);
        format.setEscapeStrategy(escapeStrategy);
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup after each test if necessary
    }

    @AfterAll
    static void teardownAfterAll() {
        // Any global cleanup can be done here if needed
    }

    @Test
    void testEscapeAttribute_withEscapedCharacters() {
        // Given
        String input = "Hello & Welcome <to> XML \"Testing\"";
        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(true);

        // When
        String result = Format.escapeAttribute(escapeStrategy, input);

        // Then
        assertEquals("Hello &amp; Welcome &lt;to&gt; XML &quot;Testing&quot;", result);
    }

    @Test
    void testEscapeAttribute_withoutEscapedCharacters() {
        // Given
        String input = "No special characters here";
        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(false);

        // When
        String result = Format.escapeAttribute(escapeStrategy, input);

        // Then
        assertEquals("No special characters here", result);
    }

    @Test
    void testEscapeAttribute_withSurrogatePair() {
        // Given
        String input = "Surrogate pair: " + Character.toChars(0x1F600)[0]; // 😀
        when(escapeStrategy.shouldEscape(anyChar())).thenReturn(true);

        // When
        String result = Format.escapeAttribute(escapeStrategy, input);

        // Then
        assertTrue(result.contains("&#x1f600;")); // Check if the surrogate pair is escaped
    }

    // Additional test cases can be added here
}
```