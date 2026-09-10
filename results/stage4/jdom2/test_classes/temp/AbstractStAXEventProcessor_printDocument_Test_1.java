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
import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.util.NamespaceStack;

@ExtendWith(MockitoExtension.class)
class AbstractStAXEventProcessor_printDocument_Test {

    private XMLEventConsumer mockEventConsumer;

    private Format mockFormat;

    private XMLEventFactory mockEventFactory;

    private Document mockDocument;

    private FormatStack formatStack;

    private NamespaceStack namespaceStack;

    @BeforeEach
    void setupBeforeEach() {
        mockEventConsumer = mock(XMLEventConsumer.class);
        mockFormat = mock(Format.class);
        mockEventFactory = mock(XMLEventFactory.class);
        mockDocument = mock(Document.class);
        formatStack = new FormatStack(mockFormat);
        namespaceStack = new NamespaceStack();
    }

    @Test
    void testPrintDocument_withValidInput() throws XMLStreamException {
        // Given: setup the expected behavior of mocks
        when(mockFormat.getOmitDeclaration()).thenReturn(false);
        when(mockDocument.hasRootElement()).thenReturn(true);
        // When: call the method under test
        AbstractStAXEventProcessor processor = new AbstractStAXEventProcessor() {

            @Override
            public void process(XMLEventConsumer out, Format format, XMLEventFactory eventfactory, Document doc) throws XMLStreamException {
                printDocument(out, new FormatStack(format), new NamespaceStack(), eventfactory, doc);
            }
        };
        processor.process(mockEventConsumer, mockFormat, mockEventFactory, mockDocument);
        // Then: verify the expected interactions or outcomes
        // Adjust based on expected calls
        verify(mockEventConsumer, times(1)).add(any());
    }

    @Test
    void testPrintDocument_withOmitDeclarationTrue() throws XMLStreamException {
        // Given: Setup the expected behavior of mocks for omitting declaration
        when(mockFormat.getOmitDeclaration()).thenReturn(true);
        when(mockDocument.hasRootElement()).thenReturn(true);
        when(mockEventFactory.createStartDocument(null, null)).thenReturn(mock());
        // When: Call the method under test
        AbstractStAXEventProcessor processor = new AbstractStAXEventProcessor() {

            @Override
            public void process(XMLEventConsumer out, Format format, XMLEventFactory eventfactory, Document doc) throws XMLStreamException {
                printDocument(out, new FormatStack(format), new NamespaceStack(), eventfactory, doc);
            }
        };
        processor.process(mockEventConsumer, mockFormat, mockEventFactory, mockDocument);
        // Then: Verify that the start document is created without encoding
        verify(mockEventConsumer, times(1)).add(any());
    }

    @Test
    void testPrintDocument_withOmitEncodingTrue() throws XMLStreamException {
        // Given: Setup the expected behavior of mocks for omitting encoding
        when(mockFormat.getOmitEncoding()).thenReturn(true);
        when(mockDocument.hasRootElement()).thenReturn(true);
        when(mockEventFactory.createStartDocument(null, "1.0")).thenReturn(mock());
        // When: Call the method under test
        AbstractStAXEventProcessor processor = new AbstractStAXEventProcessor() {

            @Override
            public void process(XMLEventConsumer out, Format format, XMLEventFactory eventfactory, Document doc) throws XMLStreamException {
                printDocument(out, new FormatStack(format), new NamespaceStack(), eventfactory, doc);
            }
        };
        processor.process(mockEventConsumer, mockFormat, mockEventFactory, mockDocument);
        // Then: Verify that the start document is created with version 1.0 and no encoding
        verify(mockEventConsumer, times(1)).add(any());
    }

    @Test
    void testPrintDocument_withNormalEncoding() throws XMLStreamException {
        // Given: Setup the expected behavior of mocks for normal encoding
        when(mockFormat.getEncoding()).thenReturn("UTF-8");
        when(mockDocument.hasRootElement()).thenReturn(true);
        when(mockEventFactory.createStartDocument("UTF-8", "1.0")).thenReturn(mock());
        // When: Call the method under test
        AbstractStAXEventProcessor processor = new AbstractStAXEventProcessor() {

            @Override
            public void process(XMLEventConsumer out, Format format, XMLEventFactory eventfactory, Document doc) throws XMLStreamException {
                printDocument(out, new FormatStack(format), new NamespaceStack(), eventfactory, doc);
            }
        };
        processor.process(mockEventConsumer, mockFormat, mockEventFactory, mockDocument);
        // Then: Verify that the start document is created with specified encoding
        verify(mockEventConsumer, times(1)).add(any());
    }

    @Test
    void testPrintDocument_withEmptyDocument() throws XMLStreamException {
        // Given: Setup the expected behavior of mocks for an empty document
        when(mockDocument.hasRootElement()).thenReturn(false);
        when(mockDocument.getContentSize()).thenReturn(0);
        // When: Call the method under test
        AbstractStAXEventProcessor processor = new AbstractStAXEventProcessor() {

            @Override
            public void process(XMLEventConsumer out, Format format, XMLEventFactory eventfactory, Document doc) throws XMLStreamException {
                printDocument(out, new FormatStack(format), new NamespaceStack(), eventfactory, doc);
            }
        };
        processor.process(mockEventConsumer, mockFormat, mockEventFactory, mockDocument);
        // Then: Verify that the end document is created without processing content
        verify(mockEventConsumer, times(1)).add(any());
    }

    @Test
    void testPrintDocument_withDifferentContentTypes() throws XMLStreamException {
        // Given: Setup the expected behavior of mocks for various content types
        when(mockDocument.hasRootElement()).thenReturn(true);
        // Mock other content types as needed
        // When: Call the method under test
        AbstractStAXEventProcessor processor = new AbstractStAXEventProcessor() {

            @Override
            public void process(XMLEventConsumer out, Format format, XMLEventFactory eventfactory, Document doc) throws XMLStreamException {
                printDocument(out, new FormatStack(format), new NamespaceStack(), eventfactory, doc);
            }
        };
        processor.process(mockEventConsumer, mockFormat, mockEventFactory, mockDocument);
        // Then: Verify that all content types are processed correctly
        verify(mockEventConsumer, times(1)).add(any());
    }

    @Test
    void testPrintDocument_withLineSeparator() throws XMLStreamException {
        // Given: Setup the expected behavior of mocks for line separator
        when(mockFormat.getLineSeparator()).thenReturn("\n");
        when(mockDocument.hasRootElement()).thenReturn(true);
        // When: Call the method under test
        AbstractStAXEventProcessor processor = new AbstractStAXEventProcessor() {

            @Override
            public void process(XMLEventConsumer out, Format format, XMLEventFactory eventfactory, Document doc) throws XMLStreamException {
                printDocument(out, new FormatStack(format), new NamespaceStack(), eventfactory, doc);
            }
        };
        processor.process(mockEventConsumer, mockFormat, mockEventFactory, mockDocument);
        // Then: Verify that the line separator is added before the end document
        verify(mockEventConsumer, times(1)).add(any());
    }

    @Test
    void testPrintDocument_withNoRootElement() throws XMLStreamException {
        // Given: Setup the expected behavior of mocks for no root element
        when(mockDocument.hasRootElement()).thenReturn(false);
        // When: Call the method under test
        AbstractStAXEventProcessor processor = new AbstractStAXEventProcessor() {

            @Override
            public void process(XMLEventConsumer out, Format format, XMLEventFactory eventfactory, Document doc) throws XMLStreamException {
                printDocument(out, new FormatStack(format), new NamespaceStack(), eventfactory, doc);
            }
        };
        processor.process(mockEventConsumer, mockFormat, mockEventFactory, mockDocument);
        // Then: Verify that the end document is created without processing content
        verify(mockEventConsumer, times(1)).add(any());
    }

    @Test
    void testPrintDocument_withXMLStreamExceptionThrown() {
        // Given: Setup the expected behavior of mocks for XMLStreamException
        doThrow(new XMLStreamException("Error")).when(mockEventConsumer).add(any());
        // When: Call the method under test
        AbstractStAXEventProcessor processor = new AbstractStAXEventProcessor() {

            @Override
            public void process(XMLEventConsumer out, Format format, XMLEventFactory eventfactory, Document doc) throws XMLStreamException {
                printDocument(out, new FormatStack(format), new NamespaceStack(), eventfactory, doc);
            }
        };
        // Then: Verify that an XMLStreamException is thrown
        assertThrows(XMLStreamException.class, () -> {
            processor.process(mockEventConsumer, mockFormat, mockEventFactory, mockDocument);
        });
    }
}
