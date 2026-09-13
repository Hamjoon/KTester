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

    @Mock
    private EscapeStrategy mockEscapeStrategy;

    @BeforeAll
    static void setupBeforeAll() {
        // Global setup can be done here if needed
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize mocks before each test
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void teardownAfterEach() {
        // Clean up resources after each test
    }

    @AfterAll
    static void teardownAfterAll() {
        // Global cleanup can be done here if needed
    }

    @Test
    void testEscapeAttribute_noSpecialCharacters() {
        // Given
        String input = "normalText";
        when(mockEscapeStrategy.shouldEscape(anyChar())).thenReturn(false);

        // When
        String result = Format.escapeAttribute(mockEscapeStrategy, input);

        // Then
        assertEquals(input, result);
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

    // Additional test cases can be added here
}
```