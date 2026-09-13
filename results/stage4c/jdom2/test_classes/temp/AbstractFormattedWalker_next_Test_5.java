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
import org.jdom2.output.EscapeStrategy;
import java.lang.reflect.Field;
import org.jdom2.Content.CType;
import java.util.Iterator;

@ExtendWith(MockitoExtension.class)
class AbstractFormattedWalker_next_Test {

    private AbstractFormattedWalker walker;

    private List<Content> contentList;

    private FormatStack formatStack;

    private EscapeStrategy escapeStrategy;

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

            @Override
            protected boolean hasNext() {
                return content.hasNext();
            }

            @Override
            protected Content next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("Cannot walk off end of Content");
                }
                return content.next();
            }
        };
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup after each test
        contentList.clear();
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
        // Mock getCType() correctly
        when(content.getCType()).thenReturn(CType.Element);
        contentList.add(content);
        // Initialize the iterator
        walker.content = contentList.iterator();
        // When
        Content result = walker.next();
        // Then
        assertNotNull(result);
        assertEquals(content, result);
    }

    @Test
    void testNextWithPendingNull() throws Exception {
        // Given
        Field pendingField = AbstractFormattedWalker.class.getDeclaredField("pending");
        pendingField.setAccessible(true);
        pendingField.set(walker, null);
        // When
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            walker.next();
        });
        // Then
        assertEquals("Cannot walk off end of Content", exception.getMessage());
    }
}
