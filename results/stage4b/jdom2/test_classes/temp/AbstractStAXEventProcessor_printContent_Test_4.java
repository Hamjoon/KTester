package org.jdom2.output.support;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.xml.stream.util.XMLEventConsumer;
import javax.xml.stream.XMLEventFactory;
import org.jdom2.output.Format;
import org.jdom2.util.NamespaceStack;
import org.jdom2.output.support.FormatStack;
import org.jdom2.Content;
import org.jdom2.output.support.Walker;
import javax.xml.stream.XMLStreamException;
import org.jdom2.CDATA;
import org.jdom2.Comment;
import org.w3c.dom.Text;

// import org.jdom2.Text;
@ExtendWith(MockitoExtension.class)
class AbstractStAXEventProcessor_printContent_Test {

    private XMLEventConsumer eventConsumer;

    private FormatStack formatStack;

    private NamespaceStack namespaceStack;

    private XMLEventFactory eventFactory;

    private Walker walker;

    private AbstractStAXEventProcessor processor;

    @BeforeEach
    void setupBeforeEach() {
        eventConsumer = mock(XMLEventConsumer.class);
        formatStack = new FormatStack(mock(Format.class));
        namespaceStack = new NamespaceStack();
        eventFactory = mock(XMLEventFactory.class);
        walker = mock(Walker.class);
        processor = mock(AbstractStAXEventProcessor.class);
    }

    @Test
    void testPrintContent() throws XMLStreamException {
        // Given
        when(walker.hasNext()).thenReturn(true);
        // Changed to return a mock Content
        when(walker.next()).thenReturn(mock(Content.class));
        when(walker.isCDATA()).thenReturn(false);
        when(walker.text()).thenReturn("Sample Text");
        // When
        processor.printContent(eventConsumer, formatStack, namespaceStack, eventFactory, walker);
        // Then
        verify(eventConsumer, times(1)).add(any());
    }

    @Test
    void testPrintContent_withWalkerHavingNextContent() throws XMLStreamException {
        // Test when walker has next content and processes various content types.
        // Given
        when(walker.hasNext()).thenReturn(true);
        // Simulating next content
        when(walker.next()).thenReturn(mock(Content.class));
        when(walker.isCDATA()).thenReturn(false);
        // When
        processor.printContent(eventConsumer, formatStack, namespaceStack, eventFactory, walker);
        // Then
        verify(eventConsumer, times(1)).add(any());
    }

    @Test
    void testPrintContent_withWalkerReturningNullContentAsCDATA() throws XMLStreamException {
        // Test when walker returns null content and is identified as CDATA.
        // Given
        when(walker.hasNext()).thenReturn(true, false);
        when(walker.next()).thenReturn(null);
        when(walker.isCDATA()).thenReturn(true);
        when(walker.text()).thenReturn("Sample CDATA");
        // When
        processor.printContent(eventConsumer, formatStack, namespaceStack, eventFactory, walker);
        // Then
        verify(processor, times(1)).printCDATA(eq(eventConsumer), eq(formatStack), eq(eventFactory), any(CDATA.class));
    }

    @Test
    void testPrintContent_withWalkerHavingContentTypeCDATA() throws XMLStreamException {
        // Test case for processing CDATA content type.
        // Given
        Content cdataContent = mock(Content.class);
        when(cdataContent.getCType()).thenReturn(Content.CType.CDATA);
        when(walker.hasNext()).thenReturn(true, false);
        when(walker.next()).thenReturn(cdataContent);
        // When
        processor.printContent(eventConsumer, formatStack, namespaceStack, eventFactory, walker);
        // Then
        verify(processor, times(1)).printCDATA(eq(eventConsumer), eq(formatStack), eq(eventFactory), any(CDATA.class));
    }

    @Test
    void testPrintContent_withWalkerHavingUnexpectedContent() {
        // Test case for handling unexpected content types.
        // Given
        Content unexpectedContent = mock(Content.class);
        when(unexpectedContent.getCType()).thenReturn(null);
        when(walker.hasNext()).thenReturn(true);
        when(walker.next()).thenReturn(unexpectedContent);
        // When / Then
        assertThrows(IllegalStateException.class, () -> {
            processor.printContent(eventConsumer, formatStack, namespaceStack, eventFactory, walker);
        });
    }

    @Test
    void testPrintContent_withWalkerHavingEmptyContent() throws XMLStreamException {
        // Test when walker has no content to process.
        // Given
        when(walker.hasNext()).thenReturn(false);
        // When
        processor.printContent(eventConsumer, formatStack, namespaceStack, eventFactory, walker);
        // Then
        verifyNoInteractions(eventConsumer);
    }
}
