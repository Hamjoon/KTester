```java
package org.jdom2.output.support;

import org.jdom2.Content;
import org.jdom2.output.Format;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@ExtendWith(MockitoExtension.class)
class AbstractFormattedWalker_next_Test {

    private AbstractFormattedWalker walker;
    private List<Content> contentList;
    private FormatStack formatStack;
    private EscapeStrategy escapeStrategy;

    @BeforeAll
    static void setupBeforeAll() {
        // Global setup if needed
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the necessary objects and dependencies
        contentList = new ArrayList<>();
        Format format = mock(Format.class);
        escapeStrategy = mock(EscapeStrategy.class);
        when(format.getEscapeStrategy()).thenReturn(escapeStrategy);
        formatStack = new FormatStack(format);

        walker = new AbstractFormattedWalker(contentList, formatStack, true) {
            @Override
            protected void analyzeMultiText(MultiText mtext, int offset, int len) {
                // Implementation for testing purposes
            }
        };
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup after each test
        contentList.clear();
    }

    @AfterAll
    static void teardownAfterAll() {
        // Global cleanup if needed
    }

    @Test
    void testNextWithNoContent() {
        // Given
        assertThrows(NoSuchElementException.class, () -> {
            // When
            walker.next();
        });
    }

    @Test
    void testNextWithContent() {
        // Given
        Content content = mock(Content.class);
        contentList.add(content);

        // When
        Content result = walker.next();

        // Then
        assertNotNull(result);
        assertEquals(content, result);
    }
}
```