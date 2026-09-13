package org.jdom2.output.support;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.xml.stream.util.XMLEventConsumer;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLStreamException;
import org.jdom2.output.Format;
import org.jdom2.util.NamespaceStack;
import org.jdom2.*;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartDocument;

@ExtendWith(MockitoExtension.class)
class AbstractStAXEventProcessor_printDocument_Test {

    @Mock
    private XMLEventConsumer mockXMLEventConsumer;

    @Mock
    private XMLEventFactory mockXMLEventFactory;

    private FormatStack formatStack;

    private NamespaceStack namespaceStack;

    private Document document;

    private AbstractStAXEventProcessor processor;

    @BeforeEach
    void setupBeforeEach() {
        Format format = new Format();
        formatStack = new FormatStack(format);
        namespaceStack = new NamespaceStack();
        document = new Document();
        processor = new AbstractStAXEventProcessor() {

            @Override
            public void process(XMLEventConsumer out, Format format, XMLEventFactory eventfactory, List<? extends Content> list) throws XMLStreamException {
                // Dummy implementation for abstract method
            }
        };
    }

    @Test
    void testPrintDocument() throws XMLStreamException {
        // Given
        // (Setup is done in setupBeforeEach)
        // When
        processor.printDocument(mockXMLEventConsumer, formatStack, namespaceStack, mockXMLEventFactory, document);
        // Then
        // Add assertions to verify the behavior of printDocument
        // Example: verify(mockXMLEventConsumer).add(any());
    }

    @Test
    void testPrintDocument_OmitDeclaration() throws XMLStreamException {
        // Given
        formatStack = new FormatStack(new Format().setOmitDeclaration(true));
        when(mockXMLEventFactory.createStartDocument(null, null)).thenReturn(mock(StartDocument.class));
        // When
        processor.printDocument(mockXMLEventConsumer, formatStack, namespaceStack, mockXMLEventFactory, document);
        // Then
        verify(mockXMLEventConsumer).add(mockXMLEventFactory.createStartDocument(null, null));
    }

    @Test
    void testPrintDocument_OmitEncodingWithLineSeparator() throws XMLStreamException {
        // Given
        formatStack = new FormatStack(new Format().setOmitEncoding(true).setLineSeparator("\n"));
        when(mockXMLEventFactory.createStartDocument(null, "1.0")).thenReturn(mock(StartDocument.class));
        when(mockXMLEventFactory.createCharacters("\n")).thenReturn(mock(Characters.class));
        // When
        processor.printDocument(mockXMLEventConsumer, formatStack, namespaceStack, mockXMLEventFactory, document);
        // Then
        verify(mockXMLEventConsumer).add(mockXMLEventFactory.createStartDocument(null, "1.0"));
        verify(mockXMLEventConsumer).add(mockXMLEventFactory.createCharacters("\n"));
    }

    @Test
    void testPrintDocument_FullDeclaration() throws XMLStreamException {
        // Given
        formatStack = new FormatStack(new Format().setEncoding("UTF-8").setLineSeparator("\n"));
        when(mockXMLEventFactory.createStartDocument("UTF-8", "1.0")).thenReturn(mock(StartDocument.class));
        when(mockXMLEventFactory.createCharacters("\n")).thenReturn(mock(Characters.class));
        // When
        processor.printDocument(mockXMLEventConsumer, formatStack, namespaceStack, mockXMLEventFactory, document);
        // Then
        verify(mockXMLEventConsumer).add(mockXMLEventFactory.createStartDocument("UTF-8", "1.0"));
        verify(mockXMLEventConsumer).add(mockXMLEventFactory.createCharacters("\n"));
    }

    @Test
    void testPrintDocument_WithCommentContent() throws XMLStreamException {
        // Given
        Comment comment = new Comment("This is a comment");
        document.addContent(comment);
        when(mockXMLEventFactory.createStartDocument("UTF-8", "1.0")).thenReturn(mock(StartDocument.class));
        doNothing().when(processor).printComment(any(), any(), any(), eq(comment));
        // When
        processor.printDocument(mockXMLEventConsumer, formatStack, namespaceStack, mockXMLEventFactory, document);
        // Then
        verify(processor).printComment(mockXMLEventConsumer, formatStack, mockXMLEventFactory, comment);
    }

    @Test
    void testPrintDocument_WithDocTypeContent() throws XMLStreamException {
        // Given
        DocType docType = new DocType("html");
        document.addContent(docType);
        when(mockXMLEventFactory.createStartDocument("UTF-8", "1.0")).thenReturn(mock(StartDocument.class));
        doNothing().when(processor).printDocType(any(), any(), any(), eq(docType));
        // When
        processor.printDocument(mockXMLEventConsumer, formatStack, namespaceStack, mockXMLEventFactory, document);
        // Then
        verify(processor).printDocType(mockXMLEventConsumer, formatStack, mockXMLEventFactory, docType);
    }

    @Test
    void testPrintDocument_WithElementContent() throws XMLStreamException {
        // Given
        Element element = new Element("element");
        document.addContent(element);
        when(mockXMLEventFactory.createStartDocument("UTF-8", "1.0")).thenReturn(mock(StartDocument.class));
        doNothing().when(processor).printElement(any(), any(), any(), any(), eq(element));
        // When
        processor.printDocument(mockXMLEventConsumer, formatStack, namespaceStack, mockXMLEventFactory, document);
        // Then
        verify(processor).printElement(mockXMLEventConsumer, formatStack, namespaceStack, mockXMLEventFactory, element);
    }

    @Test
    void testPrintDocument_WithProcessingInstructionContent() throws XMLStreamException {
        // Given
        ProcessingInstruction pi = new ProcessingInstruction("xml-stylesheet", "type=\"text/xsl\" href=\"style.xsl\"");
        document.addContent(pi);
        when(mockXMLEventFactory.createStartDocument("UTF-8", "1.0")).thenReturn(mock(StartDocument.class));
        doNothing().when(processor).printProcessingInstruction(any(), any(), any(), eq(pi));
        // When
        processor.printDocument(mockXMLEventConsumer, formatStack, namespaceStack, mockXMLEventFactory, document);
        // Then
        verify(processor).printProcessingInstruction(mockXMLEventConsumer, formatStack, mockXMLEventFactory, pi);
    }

    @Test
    void testPrintDocument_WithTextContent() throws XMLStreamException {
        // Given
        Text text = new Text("Some text content");
        document.addContent(text);
        when(mockXMLEventFactory.createStartDocument("UTF-8", "1.0")).thenReturn(mock(StartDocument.class));
        when(mockXMLEventFactory.createCharacters("Some text content")).thenReturn(mock(Characters.class));
        // When
        processor.printDocument(mockXMLEventConsumer, formatStack, namespaceStack, mockXMLEventFactory, document);
        // Then
        verify(mockXMLEventConsumer).add(mockXMLEventFactory.createCharacters("Some text content"));
    }

    @Test
    void testPrintDocument_WithEntityRefContent() throws XMLStreamException {
        // Given
        EntityRef entityRef = new EntityRef("entity");
        document.addContent(entityRef);
        when(mockXMLEventFactory.createStartDocument("UTF-8", "1.0")).thenReturn(mock(StartDocument.class));
        doNothing().when(processor).printEntityRef(any(), any(), any(), eq(entityRef));
        // When
        processor.printDocument(mockXMLEventConsumer, formatStack, namespaceStack, mockXMLEventFactory, document);
        // Then
        verify(processor).printEntityRef(mockXMLEventConsumer, formatStack, mockXMLEventFactory, entityRef);
    }

    @Test
    void testPrintDocument_NullXMLEventConsumer() {
        // Given
        formatStack = new FormatStack(new Format().setEncoding("UTF-8").setLineSeparator("\n"));
        document = new Document();
        // When & Then
        assertThrows(NullPointerException.class, () -> {
            processor.printDocument(null, formatStack, namespaceStack, mockXMLEventFactory, document);
        });
    }

    @Test
    void testPrintDocument_NullFormatStack() {
        // Given
        document = new Document();
        // When & Then
        assertThrows(NullPointerException.class, () -> {
            processor.printDocument(mockXMLEventConsumer, null, namespaceStack, mockXMLEventFactory, document);
        });
    }

    @Test
    void testPrintDocument_NullXMLEventFactory() {
        // Given
        formatStack = new FormatStack(new Format().setEncoding("UTF-8").setLineSeparator("\n"));
        document = new Document();
        // When & Then
        assertThrows(NullPointerException.class, () -> {
            processor.printDocument(mockXMLEventConsumer, formatStack, namespaceStack, null, document);
        });
    }

    @Test
    void testPrintDocument_NullDocument() {
        // Given
        formatStack = new FormatStack(new Format().setEncoding("UTF-8").setLineSeparator("\n"));
        // When & Then
        assertThrows(NullPointerException.class, () -> {
            processor.printDocument(mockXMLEventConsumer, formatStack, namespaceStack, mockXMLEventFactory, null);
        });
    }

    @Test
    void testPrintDocument_InvalidContentType() {
        // Given
        Content invalidContent = mock(Content.class);
        when(invalidContent.getCType()).thenReturn(null);
        document.addContent(invalidContent);
        // When & Then
        assertThrows(ClassCastException.class, () -> {
            processor.printDocument(mockXMLEventConsumer, formatStack, namespaceStack, mockXMLEventFactory, document);
        });
    }
}
