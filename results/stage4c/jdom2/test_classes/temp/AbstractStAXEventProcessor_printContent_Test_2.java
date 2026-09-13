package org.jdom2.output.support;

import org.jdom2.output.Format;
import org.jdom2.util.NamespaceStack;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.util.XMLEventConsumer;
import javax.xml.stream.XMLStreamException;
import java.util.List;
import java.util.ArrayList;
import org.jdom2.*;

@ExtendWith(MockitoExtension.class)
class AbstractStAXEventProcessor_printContent_Test {

    @Mock
    private XMLEventConsumer mockXMLEventConsumer;

    @Mock
    private XMLEventFactory mockXMLEventFactory;

    @Mock
    private Walker mockWalker;

    private Format format;

    private FormatStack formatStack;

    private NamespaceStack namespaceStack;

    private AbstractStAXEventProcessor processor;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize necessary fields and dependencies
        format = mock(Format.class);
        formatStack = new FormatStack(format);
        namespaceStack = new NamespaceStack();
        processor = spy(new AbstractStAXEventProcessor() {

            // Implement abstract methods if necessary
            @Override
            protected void printCDATA(XMLEventConsumer out, FormatStack fstack, XMLEventFactory eventfactory, CDATA cdata) throws XMLStreamException {
                // Mocked behavior
            }

            @Override
            protected void printComment(XMLEventConsumer out, FormatStack fstack, XMLEventFactory eventfactory, Comment comment) throws XMLStreamException {
                // Mocked behavior
            }

            @Override
            protected void printDocType(XMLEventConsumer out, FormatStack fstack, XMLEventFactory eventfactory, DocType docType) throws XMLStreamException {
                // Mocked behavior
            }

            @Override
            protected void printElement(XMLEventConsumer out, FormatStack fstack, NamespaceStack nstack, XMLEventFactory eventfactory, Element element) throws XMLStreamException {
                // Mocked behavior
            }

            @Override
            protected void printEntityRef(XMLEventConsumer out, FormatStack fstack, XMLEventFactory eventfactory, EntityRef entity) throws XMLStreamException {
                // Mocked behavior
            }

            @Override
            protected void printProcessingInstruction(XMLEventConsumer out, FormatStack fstack, XMLEventFactory eventfactory, ProcessingInstruction pi) throws XMLStreamException {
                // Mocked behavior
            }

            @Override
            protected void printText(XMLEventConsumer out, FormatStack fstack, XMLEventFactory eventfactory, Text text) throws XMLStreamException {
                // Mocked behavior
            }
        });
    }

    @Test
    void testPrintContent_withValidInputs() throws XMLStreamException {
        // Given
        List<Content> contentList = new ArrayList<>();
        contentList.add(new Element("testElement"));
        when(mockWalker.hasNext()).thenReturn(true, false);
        when(mockWalker.next()).thenReturn(contentList.get(0));
        // When
        processor.printContent(mockXMLEventConsumer, formatStack, namespaceStack, mockXMLEventFactory, mockWalker);
        // Then
        verify(mockWalker, atLeastOnce()).hasNext();
        verify(mockWalker, atLeastOnce()).next();
        verify(processor).printElement(mockXMLEventConsumer, formatStack, namespaceStack, mockXMLEventFactory, (Element) contentList.get(0));
    }

    @Test
    void testPrintContent_withNullAndIsCDATA() throws XMLStreamException {
        // Given
        when(mockWalker.hasNext()).thenReturn(true, false);
        when(mockWalker.next()).thenReturn(null);
        when(mockWalker.isCDATA()).thenReturn(true);
        when(mockWalker.text()).thenReturn("CDATA content");
        // When
        processor.printContent(mockXMLEventConsumer, formatStack, namespaceStack, mockXMLEventFactory, mockWalker);
        // Then
        verify(mockWalker, atLeastOnce()).hasNext();
        verify(mockWalker, atLeastOnce()).next();
        verify(processor).printCDATA(mockXMLEventConsumer, formatStack, mockXMLEventFactory, new CDATA("CDATA content"));
    }

    @Test
    void testPrintContent_withNullAndNotCDATA() throws XMLStreamException {
        // Given
        when(mockWalker.hasNext()).thenReturn(true, false);
        when(mockWalker.next()).thenReturn(null);
        when(mockWalker.isCDATA()).thenReturn(false);
        when(mockWalker.text()).thenReturn("Text content");
        // When
        processor.printContent(mockXMLEventConsumer, formatStack, namespaceStack, mockXMLEventFactory, mockWalker);
        // Then
        verify(mockWalker, atLeastOnce()).hasNext();
        verify(mockWalker, atLeastOnce()).next();
        verify(processor).printText(mockXMLEventConsumer, formatStack, mockXMLEventFactory, new Text("Text content"));
    }

    @Test
    void testPrintContent_withContentTypes() throws XMLStreamException {
        // Given
        CDATA cdata = new CDATA("cdata");
        Comment comment = new Comment("comment");
        Element element = new Element("element");
        EntityRef entityRef = new EntityRef("entity");
        ProcessingInstruction pi = new ProcessingInstruction("target", "data");
        Text text = new Text("text");
        DocType docType = new DocType("doctype");
        when(mockWalker.hasNext()).thenReturn(true, true, true, true, true, true, true, false);
        when(mockWalker.next()).thenReturn(cdata, comment, element, entityRef, pi, text, docType);
        // When
        processor.printContent(mockXMLEventConsumer, formatStack, namespaceStack, mockXMLEventFactory, mockWalker);
        // Then
        verify(processor).printCDATA(mockXMLEventConsumer, formatStack, mockXMLEventFactory, cdata);
        verify(processor).printComment(mockXMLEventConsumer, formatStack, mockXMLEventFactory, comment);
        verify(processor).printElement(mockXMLEventConsumer, formatStack, namespaceStack, mockXMLEventFactory, element);
        verify(processor).printEntityRef(mockXMLEventConsumer, formatStack, mockXMLEventFactory, entityRef);
        verify(processor).printProcessingInstruction(mockXMLEventConsumer, formatStack, mockXMLEventFactory, pi);
        verify(processor).printText(mockXMLEventConsumer, formatStack, mockXMLEventFactory, text);
        verify(processor).printDocType(mockXMLEventConsumer, formatStack, mockXMLEventFactory, docType);
    }

    @Test
    void testPrintContent_withUnknownContentType() {
        // Given
        Content unknownContent = mock(Content.class);
        when(unknownContent.getCType()).thenReturn(null);
        when(mockWalker.hasNext()).thenReturn(true);
        when(mockWalker.next()).thenReturn(unknownContent);
        // When & Then
        Assertions.assertThrows(IllegalStateException.class, () -> {
            processor.printContent(mockXMLEventConsumer, formatStack, namespaceStack, mockXMLEventFactory, mockWalker);
        });
    }

    @Test
    void testPrintContent_withNullParameters() {
        // Test cases for null parameters
        Assertions.assertThrows(NullPointerException.class, () -> {
            processor.printContent(null, formatStack, namespaceStack, mockXMLEventFactory, mockWalker);
        });
        Assertions.assertThrows(NullPointerException.class, () -> {
            processor.printContent(mockXMLEventConsumer, null, namespaceStack, mockXMLEventFactory, mockWalker);
        });
        Assertions.assertThrows(NullPointerException.class, () -> {
            processor.printContent(mockXMLEventConsumer, formatStack, null, mockXMLEventFactory, mockWalker);
        });
        Assertions.assertThrows(NullPointerException.class, () -> {
            processor.printContent(mockXMLEventConsumer, formatStack, namespaceStack, null, mockWalker);
        });
        Assertions.assertThrows(NullPointerException.class, () -> {
            processor.printContent(mockXMLEventConsumer, formatStack, namespaceStack, mockXMLEventFactory, null);
        });
    }
}
