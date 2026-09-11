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
import org.jdom2.output.support.FormatStack;
import org.jdom2.Content;
// Add other imports if necessary
import org.jdom2.Content.CType;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
class AbstractStAXEventProcessor_printDocument_Test {

    private XMLEventConsumer out;

    private FormatStack fstack;

    private NamespaceStack nstack;

    private XMLEventFactory eventfactory;

    private Document doc;

    private Format format;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize mocks and objects needed for each test
        out = mock(XMLEventConsumer.class);
        format = mock(Format.class);
        fstack = new FormatStack(format);
        nstack = new NamespaceStack();
        eventfactory = mock(XMLEventFactory.class);
        doc = mock(Document.class);
        // Set up default behavior for format and document
        when(format.getEncoding()).thenReturn("UTF-8");
        when(format.getOmitDeclaration()).thenReturn(false);
        when(doc.hasRootElement()).thenReturn(true);
        // Ensure TextMode is set
//         when(format.getTextMode()).thenReturn(Format.TextMode.NORMAL);
    }

    @Test
    void testPrintDocument() throws XMLStreamException {
        // When
        AbstractStAXEventProcessor processor = new AbstractStAXEventProcessor() {

            @Override
            public void process(XMLEventConsumer out, Format format, XMLEventFactory eventfactory, Document doc) throws XMLStreamException {
                printDocument(out, new FormatStack(format), new NamespaceStack(), eventfactory, doc);
            }
        };
        processor.process(out, format, eventfactory, doc);
        // Then
        verify(out, times(1)).add(any());
    }

    @Test
    void testPrintDocumentWithOmittedDeclaration() throws XMLStreamException {
        // Given
        when(format.getOmitDeclaration()).thenReturn(true);
        // When
        AbstractStAXEventProcessor processor = new AbstractStAXEventProcessor() {

            @Override
            public void process(XMLEventConsumer out, Format format, XMLEventFactory eventfactory, Document doc) throws XMLStreamException {
                printDocument(out, new FormatStack(format), new NamespaceStack(), eventfactory, doc);
            }
        };
        processor.process(out, format, eventfactory, doc);
        // Then
        verify(out, times(1)).add(any());
    }

    @Test
    void testPrintDocumentWithOmittedEncoding() throws XMLStreamException {
        // Given
        when(format.getOmitEncoding()).thenReturn(true);
        // When
        AbstractStAXEventProcessor processor = new AbstractStAXEventProcessor() {

            @Override
            public void process(XMLEventConsumer out, Format format, XMLEventFactory eventfactory, Document doc) throws XMLStreamException {
                printDocument(out, new FormatStack(format), new NamespaceStack(), eventfactory, doc);
            }
        };
        processor.process(out, format, eventfactory, doc);
        // Then
        verify(out, times(1)).add(any());
    }

    @Test
    void testPrintDocumentWithDefaultEncoding() throws XMLStreamException {
        // Given
        when(format.getEncoding()).thenReturn("UTF-8");
        // When
        AbstractStAXEventProcessor processor = new AbstractStAXEventProcessor() {

            @Override
            public void process(XMLEventConsumer out, Format format, XMLEventFactory eventfactory, Document doc) throws XMLStreamException {
                printDocument(out, new FormatStack(format), new NamespaceStack(), eventfactory, doc);
            }
        };
        processor.process(out, format, eventfactory, doc);
        // Then
        verify(out, times(1)).add(any());
    }

    @Test
    void testPrintDocumentWithEmptyDocument() throws XMLStreamException {
        // Given
        when(doc.hasRootElement()).thenReturn(false);
        // When
        AbstractStAXEventProcessor processor = new AbstractStAXEventProcessor() {

            @Override
            public void process(XMLEventConsumer out, Format format, XMLEventFactory eventfactory, Document doc) throws XMLStreamException {
                printDocument(out, new FormatStack(format), new NamespaceStack(), eventfactory, doc);
            }
        };
        processor.process(out, format, eventfactory, doc);
        // Then
        verify(out, times(1)).add(any());
    }

    @Test
    void testPrintDocumentWithVariousContentTypes() throws XMLStreamException {
        // Given
        when(doc.hasRootElement()).thenReturn(true);
        ArrayList<Content> contentList = new ArrayList<>();
        Content mockComment = mock(Content.class);
        Content mockElement = mock(Content.class);
        Content mockProcessingInstruction = mock(Content.class);
        when(mockComment.getCType()).thenReturn(CType.Comment);
        when(mockElement.getCType()).thenReturn(CType.Element);
        when(mockProcessingInstruction.getCType()).thenReturn(CType.ProcessingInstruction);
        contentList.add(mockComment);
        contentList.add(mockElement);
        contentList.add(mockProcessingInstruction);
        when(doc.getContent()).thenReturn(contentList);
        // When
        AbstractStAXEventProcessor processor = new AbstractStAXEventProcessor() {

            @Override
            public void process(XMLEventConsumer out, Format format, XMLEventFactory eventfactory, Document doc) throws XMLStreamException {
                printDocument(out, new FormatStack(format), new NamespaceStack(), eventfactory, doc);
            }
        };
        processor.process(out, format, eventfactory, doc);
        // Then
        verify(out, times(1)).add(any());
    }

    @Test
    void testPrintDocumentWithNoRootElement() throws XMLStreamException {
        // Given
        when(doc.hasRootElement()).thenReturn(false);
        // When
        AbstractStAXEventProcessor processor = new AbstractStAXEventProcessor() {

            @Override
            public void process(XMLEventConsumer out, Format format, XMLEventFactory eventfactory, Document doc) throws XMLStreamException {
                printDocument(out, new FormatStack(format), new NamespaceStack(), eventfactory, doc);
            }
        };
        processor.process(out, format, eventfactory, doc);
        // Then
        verify(out, times(1)).add(any());
    }

    @Test
    void testPrintDocumentWithLineSeparator() throws XMLStreamException {
        // Given
        when(format.getLineSeparator()).thenReturn("\n");
        // When
        AbstractStAXEventProcessor processor = new AbstractStAXEventProcessor() {

            @Override
            public void process(XMLEventConsumer out, Format format, XMLEventFactory eventfactory, Document doc) throws XMLStreamException {
                printDocument(out, new FormatStack(format), new NamespaceStack(), eventfactory, doc);
            }
        };
        processor.process(out, format, eventfactory, doc);
        // Then
        verify(out, times(1)).add(any());
    }

    @Test
    void testPrintDocumentWithMultipleRootElements() {
        // Given
        when(doc.hasRootElement()).thenReturn(true);
        // Simulating multiple root elements
        when(doc.getContentSize()).thenReturn(2);
        // When
        AbstractStAXEventProcessor processor = new AbstractStAXEventProcessor() {

            @Override
            public void process(XMLEventConsumer out, Format format, XMLEventFactory eventfactory, Document doc) throws XMLStreamException {
                assertThrows(XMLStreamException.class, () -> {
                    printDocument(out, new FormatStack(format), new NamespaceStack(), eventfactory, doc);
                });
            }
        };
        // Then
        assertThrows(XMLStreamException.class, () -> {
            processor.process(out, format, eventfactory, doc);
        });
    }

    @Test
    void testPrintDocumentWithOnlyComments() throws XMLStreamException {
        // Given
        when(doc.hasRootElement()).thenReturn(true);
        ArrayList<Content> contentList = new ArrayList<>();
        Content mockComment = mock(Content.class);
        when(mockComment.getCType()).thenReturn(CType.Comment);
        contentList.add(mockComment);
        when(doc.getContent()).thenReturn(contentList);
        // When
        AbstractStAXEventProcessor processor = new AbstractStAXEventProcessor() {

            @Override
            public void process(XMLEventConsumer out, Format format, XMLEventFactory eventfactory, Document doc) throws XMLStreamException {
                printDocument(out, new FormatStack(format), new NamespaceStack(), eventfactory, doc);
            }
        };
        processor.process(out, format, eventfactory, doc);
        // Then
        verify(out, times(1)).add(any());
    }

    @Test
    void testPrintDocumentWithProcessingInstructions() throws XMLStreamException {
        // Given
        when(doc.hasRootElement()).thenReturn(true);
        ArrayList<Content> contentList = new ArrayList<>();
        Content mockProcessingInstruction = mock(Content.class);
        when(mockProcessingInstruction.getCType()).thenReturn(CType.ProcessingInstruction);
        contentList.add(mockProcessingInstruction);
        when(doc.getContent()).thenReturn(contentList);
        // When
        AbstractStAXEventProcessor processor = new AbstractStAXEventProcessor() {

            @Override
            public void process(XMLEventConsumer out, Format format, XMLEventFactory eventfactory, Document doc) throws XMLStreamException {
                printDocument(out, new FormatStack(format), new NamespaceStack(), eventfactory, doc);
            }
        };
        processor.process(out, format, eventfactory, doc);
        // Then
        verify(out, times(1)).add(any());
    }

    @Test
    void testPrintDocumentExceptionScenarios() {
        // Test case where the XMLEventConsumer is null
        assertThrows(XMLStreamException.class, () -> {
            AbstractStAXEventProcessor processor = new AbstractStAXEventProcessor() {

                @Override
                public void process(XMLEventConsumer out, Format format, XMLEventFactory eventfactory, Document doc) throws XMLStreamException {
                    printDocument(null, new FormatStack(format), new NamespaceStack(), eventfactory, doc);
                }
            };
            processor.process(null, format, eventfactory, doc);
        });
        // Test case where the XMLEventFactory is null
        assertThrows(XMLStreamException.class, () -> {
            AbstractStAXEventProcessor processor = new AbstractStAXEventProcessor() {

                @Override
                public void process(XMLEventConsumer out, Format format, XMLEventFactory eventfactory, Document doc) throws XMLStreamException {
                    printDocument(out, new FormatStack(format), new NamespaceStack(), null, doc);
                }
            };
            processor.process(out, format, null, doc);
        });
        // Test case where the Document has a null root element
        when(doc.hasRootElement()).thenReturn(false);
        assertThrows(XMLStreamException.class, () -> {
            AbstractStAXEventProcessor processor = new AbstractStAXEventProcessor() {

                @Override
                public void process(XMLEventConsumer out, Format format, XMLEventFactory eventfactory, Document doc) throws XMLStreamException {
                    printDocument(out, new FormatStack(format), new NamespaceStack(), eventfactory, doc);
                }
            };
            processor.process(out, format, eventfactory, doc);
        });
    }
}