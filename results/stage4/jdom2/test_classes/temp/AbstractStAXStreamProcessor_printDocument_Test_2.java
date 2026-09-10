package org.jdom2.output.support;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.XMLStreamException;
import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.support.FormatStack;
import org.jdom2.util.NamespaceStack;
import org.jdom2.Content;
// Add other imports if necessary
import org.jdom2.Content.CType;
import org.jdom2.Element;
import org.jdom2.Comment;
import org.jdom2.ProcessingInstruction;
import org.jdom2.Text;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class AbstractStAXStreamProcessor_printDocument_Test {

    private XMLStreamWriter xmlStreamWriter;

    private Format format;

    private Document document;

    private FormatStack formatStack;

    private NamespaceStack namespaceStack;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the mocks and test objects
        xmlStreamWriter = mock(XMLStreamWriter.class);
        format = mock(Format.class);
        document = mock(Document.class);
        formatStack = new FormatStack(format);
        namespaceStack = new NamespaceStack();
        // Ensure FormatStack returns a valid TextMode
        // Adjust this based on your requirements
        // Changed from NORMAL to DEFAULT
//         when(format.getTextMode()).thenReturn(Format.TextMode.DEFAULT);
    }

    @Test
    void testPrintDocument() throws XMLStreamException {
        // Given: setup the necessary conditions for the test
        when(format.getEncoding()).thenReturn("UTF-8");
        when(format.getOmitDeclaration()).thenReturn(false);
        when(document.hasRootElement()).thenReturn(true);
        // When: invoke the method under test
        AbstractStAXStreamProcessor processor = new AbstractStAXStreamProcessor() {
        };
        processor.printDocument(xmlStreamWriter, formatStack, namespaceStack, document);
        // Then: verify the expected outcomes
        verify(xmlStreamWriter).writeStartDocument("UTF-8", "1.0");
    }

    @Test
    void testPrintDocument_OmitDeclaration() throws XMLStreamException {
        // Given: setup for omit declaration
        when(format.getEncoding()).thenReturn("UTF-8");
        when(format.getOmitDeclaration()).thenReturn(true);
        when(document.hasRootElement()).thenReturn(true);
        // When: invoke the method under test
        AbstractStAXStreamProcessor processor = new AbstractStAXStreamProcessor() {
        };
        processor.printDocument(xmlStreamWriter, formatStack, namespaceStack, document);
        // Then: verify that start document is written without declaration
        verify(xmlStreamWriter).writeStartDocument();
    }

    @Test
    void testPrintDocument_OmitEncoding() throws XMLStreamException {
        // Given: setup for omit encoding
        when(format.getEncoding()).thenReturn("UTF-8");
        when(format.getOmitEncoding()).thenReturn(true);
        when(document.hasRootElement()).thenReturn(true);
        // When: invoke the method under test
        AbstractStAXStreamProcessor processor = new AbstractStAXStreamProcessor() {
        };
        processor.printDocument(xmlStreamWriter, formatStack, namespaceStack, document);
        // Then: verify that start document is written with version 1.0
        verify(xmlStreamWriter).writeStartDocument("1.0");
    }

    @Test
    void testPrintDocument_FullDeclaration() throws XMLStreamException {
        // Given: setup for full declaration
        when(format.getEncoding()).thenReturn("UTF-8");
        when(format.getOmitDeclaration()).thenReturn(false);
        when(document.hasRootElement()).thenReturn(true);
        // When: invoke the method under test
        AbstractStAXStreamProcessor processor = new AbstractStAXStreamProcessor() {
        };
        processor.printDocument(xmlStreamWriter, formatStack, namespaceStack, document);
        // Then: verify that start document is written with encoding and version 1.0
        verify(xmlStreamWriter).writeStartDocument("UTF-8", "1.0");
    }

    @Test
    void testPrintDocument_EmptyDocument() throws XMLStreamException {
        // Given: setup for empty document
        when(document.hasRootElement()).thenReturn(false);
        // When: invoke the method under test
        AbstractStAXStreamProcessor processor = new AbstractStAXStreamProcessor() {
        };
        processor.printDocument(xmlStreamWriter, formatStack, namespaceStack, document);
        // Then: verify that no content is written but end document is called
        verify(xmlStreamWriter).writeEndDocument();
    }

    @Test
    void testPrintDocument_WalkerIteration() throws XMLStreamException {
        // Given: setup for document with multiple content types
        Element element = mock(Element.class);
        Comment comment = mock(Comment.class);
        Text text = mock(Text.class);
        List<Content> contents = new ArrayList<>();
        contents.add(comment);
        contents.add(element);
        contents.add(text);
        when(document.hasRootElement()).thenReturn(true);
        when(document.getContent()).thenReturn(contents);
        when(comment.getCType()).thenReturn(CType.Comment);
        when(element.getCType()).thenReturn(CType.Element);
        when(text.getCType()).thenReturn(CType.Text);
        // When: invoke the method under test
        AbstractStAXStreamProcessor processor = new AbstractStAXStreamProcessor() {
        };
        processor.printDocument(xmlStreamWriter, formatStack, namespaceStack, document);
        // Then: verify that all content types are processed correctly
        verify(xmlStreamWriter).writeEndDocument();
    }

    @Test
    void testPrintDocument_PaddingHandling() throws XMLStreamException {
        // Given: setup for document with whitespace content
        Text whitespaceText = mock(Text.class);
        when(whitespaceText.getText()).thenReturn("   ");
        List<Content> contents = new ArrayList<>();
        contents.add(whitespaceText);
        when(document.hasRootElement()).thenReturn(true);
        when(document.getContent()).thenReturn(contents);
        // When: invoke the method under test
        AbstractStAXStreamProcessor processor = new AbstractStAXStreamProcessor() {
        };
        processor.printDocument(xmlStreamWriter, formatStack, namespaceStack, document);
        // Then: verify that whitespace content is written correctly
        verify(xmlStreamWriter).writeCharacters("   ");
    }

    @Test
    void testPrintDocument_OmitDeclarationWithLineSeparator() throws XMLStreamException {
        // Given: setup for omit declaration with line separator
        when(format.getOmitDeclaration()).thenReturn(true);
        when(format.getLineSeparator()).thenReturn("\n");
        when(document.hasRootElement()).thenReturn(true);
        // When: invoke the method under test
        AbstractStAXStreamProcessor processor = new AbstractStAXStreamProcessor() {
        };
        processor.printDocument(xmlStreamWriter, formatStack, namespaceStack, document);
        // Then: verify that start document is written correctly
        verify(xmlStreamWriter).writeCharacters("\n");
    }

    @Test
    void testPrintDocument_XMLStreamExceptionOnWriteStartDocument() throws XMLStreamException {
        // Given: setup for exception on writeStartDocument
        doThrow(new XMLStreamException()).when(xmlStreamWriter).writeStartDocument();
        // When: invoke the method under test
        AbstractStAXStreamProcessor processor = new AbstractStAXStreamProcessor() {
        };
        // Then: ensure that the exception is thrown
        assertThrows(XMLStreamException.class, () -> {
            processor.printDocument(xmlStreamWriter, formatStack, namespaceStack, document);
        });
    }

    @Test
    void testPrintDocument_XMLStreamExceptionOnWriteCharacters() throws XMLStreamException {
        // Given: setup for exception on writeCharacters
        when(document.hasRootElement()).thenReturn(true);
        when(format.getEncoding()).thenReturn("UTF-8");
        doThrow(new XMLStreamException()).when(xmlStreamWriter).writeCharacters(anyString());
        // When: invoke the method under test
        AbstractStAXStreamProcessor processor = new AbstractStAXStreamProcessor() {
        };
        // Then: ensure that the exception is thrown
        assertThrows(XMLStreamException.class, () -> {
            processor.printDocument(xmlStreamWriter, formatStack, namespaceStack, document);
        });
    }

    @Test
    void testPrintDocument_XMLStreamExceptionOnWriteEndDocument() throws XMLStreamException {
        // Given: setup for exception on writeEndDocument
        when(document.hasRootElement()).thenReturn(true);
        when(format.getEncoding()).thenReturn("UTF-8");
        doThrow(new XMLStreamException()).when(xmlStreamWriter).writeEndDocument();
        // When: invoke the method under test
        AbstractStAXStreamProcessor processor = new AbstractStAXStreamProcessor() {
        };
        // Then: ensure that the exception is thrown
        assertThrows(XMLStreamException.class, () -> {
            processor.printDocument(xmlStreamWriter, formatStack, namespaceStack, document);
        });
    }
}