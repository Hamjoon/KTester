package org.jdom2.output.support;

import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.util.NamespaceStack;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.IOException;
import java.io.StringWriter;
import static org.mockito.Mockito.*;
import org.jdom2.Element;
import org.jdom2.Text;
import org.jdom2.Comment;
import org.jdom2.DocType;
import org.jdom2.ProcessingInstruction;
import org.jdom2.CDATA;
import org.jdom2.EntityRef;
// Add other imports if necessary
import java.io.Writer;
import java.util.Arrays;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class AbstractXMLOutputProcessor_printDocument_Test {

    private AbstractXMLOutputProcessor processor;

    private Writer writer;

    private FormatStack formatStack;

    private NamespaceStack namespaceStack;

    private Document document;

    @BeforeEach
    void setupBeforeEach() {
        processor = mock(AbstractXMLOutputProcessor.class, Mockito.CALLS_REAL_METHODS);
        writer = new StringWriter();
        formatStack = new FormatStack(new Format());
        namespaceStack = new NamespaceStack();
        document = new Document();
    }

    @Test
    void testPrintDocument() throws IOException {
        // Given
        // Setup any specific configurations for the test
        // When
        processor.printDocument(writer, formatStack, namespaceStack, document);
        // Then
        // Add assertions to verify the expected outcome
    }

    @Test
    void testPrintDocument_NoRootElement_EmptyContent() throws IOException {
        // Given: A document with no root element and empty content
        // We cannot mock final methods like hasRootElement. Instead, we check the actual condition.
        Assertions.assertFalse(document.hasRootElement());
        // When
        processor.printDocument(writer, formatStack, namespaceStack, document);
        // Then: Verify that only the declaration is printed
        verify(processor).printDeclaration(writer, formatStack);
        verifyNoMoreInteractions(processor);
    }

    @Test
    void testPrintDocument_WithRootElement_AndContent() throws IOException {
        // Given: A document with a root element and various content types
        Element rootElement = new Element("root");
        document.setRootElement(rootElement);
        document.addContent(new Comment("Comment"));
        document.addContent(new ProcessingInstruction("target", "data"));
        document.addContent(new Text("Some text"));
        // DocType should be added before the root element
        DocType docType = new DocType("doctype");
        document.addContent(0, docType);
        // When
        processor.printDocument(writer, formatStack, namespaceStack, document);
        // Then: Verify that content is processed and written correctly
        verify(processor).printElement(writer, formatStack, namespaceStack, rootElement);
        verify(processor).printComment(writer, formatStack, new Comment("Comment"));
        verify(processor).printDocType(writer, formatStack, docType);
        verify(processor).printProcessingInstruction(writer, formatStack, new ProcessingInstruction("target", "data"));
    }

    @Test
    void testPrintDocument_WhitespaceTextOutsideRoot() throws IOException {
        // Given: A document containing only whitespace text outside the root element
        Element rootElement = new Element("root");
        document.setRootElement(rootElement);
        Text whitespaceText = new Text("   ");
        rootElement.addContent(whitespaceText);
        // When
        processor.printDocument(writer, formatStack, namespaceStack, document);
        // Then: Verify that whitespace text is written
        verify(processor).write(writer, whitespaceText.getText());
    }

    @Test
    void testPrintDocument_CommentContent() throws IOException {
        // Given: A document containing a Comment content
        Comment comment = new Comment("This is a comment");
        document.addContent(comment);
        // When
        processor.printDocument(writer, formatStack, namespaceStack, document);
        // Then: Verify that printComment is called
        verify(processor).printComment(writer, formatStack, comment);
    }

    @Test
    void testPrintDocument_DocTypeContent() throws IOException {
        // Given: A document containing a DocType content
        DocType docType = new DocType("doctype");
        document.addContent(0, docType);
        // When
        processor.printDocument(writer, formatStack, namespaceStack, document);
        // Then: Verify that printDocType is called
        verify(processor).printDocType(writer, formatStack, docType);
    }

    @Test
    void testPrintDocument_ProcessingInstructionContent() throws IOException {
        // Given: A document containing a ProcessingInstruction content
        ProcessingInstruction pi = new ProcessingInstruction("target", "data");
        document.addContent(pi);
        // When
        processor.printDocument(writer, formatStack, namespaceStack, document);
        // Then: Verify that printProcessingInstruction is called
        verify(processor).printProcessingInstruction(writer, formatStack, pi);
    }

    @Test
    void testPrintDocument_WhitespaceTextContent() throws IOException {
        // Given: A document containing a Text content with only whitespace
        Element rootElement = new Element("root");
        document.setRootElement(rootElement);
        Text whitespaceText = new Text("   ");
        rootElement.addContent(whitespaceText);
        // When
        processor.printDocument(writer, formatStack, namespaceStack, document);
        // Then: Verify that whitespace text is written
        verify(processor).write(writer, whitespaceText.getText());
    }

    @Test
    void testPrintDocument_NonWhitespaceTextContent() throws IOException {
        // Given: A document containing a Text content that is not all whitespace
        Element rootElement = new Element("root");
        document.setRootElement(rootElement);
        Text text = new Text("Non-whitespace text");
        rootElement.addContent(text);
        // When
        processor.printDocument(writer, formatStack, namespaceStack, document);
        // Then: Verify that text content is written
        verify(processor).write(writer, text.getText());
    }

    @Test
    void testPrintDocument_CDATAContent() throws IOException {
        // Given: A document containing a CDATA content
        Element rootElement = new Element("root");
        document.setRootElement(rootElement);
        CDATA cdata = new CDATA("CDATA content");
        rootElement.addContent(cdata);
        // When
        processor.printDocument(writer, formatStack, namespaceStack, document);
        // Then: Verify that CDATA content is processed
        verify(processor).printCDATA(writer, formatStack, cdata);
    }

    @Test
    void testPrintDocument_MixedContent() throws IOException {
        // Given: A document containing mixed content types
        Element rootElement = new Element("root");
        document.setRootElement(rootElement);
        document.addContent(new Comment("Comment"));
        // Whitespace text
        Text whitespaceText = new Text("   ");
        rootElement.addContent(whitespaceText);
        // When
        processor.printDocument(writer, formatStack, namespaceStack, document);
        // Then: Verify that element and comment are processed, whitespace text is written
        verify(processor).printElement(writer, formatStack, namespaceStack, rootElement);
        verify(processor).printComment(writer, formatStack, new Comment("Comment"));
        verify(processor).write(writer, "   ");
    }

    @Test
    void testPrintDocument_EntityRefContent() throws IOException {
        // Given: A document containing an EntityRef content
        Element rootElement = new Element("root");
        document.setRootElement(rootElement);
        EntityRef entityRef = new EntityRef("entity");
        rootElement.addContent(entityRef);
        // When
        processor.printDocument(writer, formatStack, namespaceStack, document);
        // Then: Verify that EntityRef content is processed
        verify(processor).printEntityRef(writer, formatStack, entityRef);
    }

    @Test
    void testPrintDocument_NestedElements() throws IOException {
        // Given: A document containing a root element with nested elements
        Element rootElement = new Element("root");
        Element childElement = new Element("child");
        rootElement.addContent(childElement);
        document.setRootElement(rootElement);
        // When
        processor.printDocument(writer, formatStack, namespaceStack, document);
        // Then: Verify that nested elements are processed and written correctly
        verify(processor).printElement(writer, formatStack, namespaceStack, rootElement);
        verify(processor).printElement(writer, formatStack, namespaceStack, childElement);
    }

    @Test
    void testPrintDocument_SingleRootElement_NoContent() throws IOException {
        // Given: A document containing only a single root element
        Element rootElement = new Element("root");
        document.setRootElement(rootElement);
        // When
        processor.printDocument(writer, formatStack, namespaceStack, document);
        // Then: Verify that printElement is called with the root element
        verify(processor).printElement(writer, formatStack, namespaceStack, rootElement);
    }

    @Test
    void testPrintDocument_NullWriter() {
        // Given: A null Writer
        writer = null;
        // When & Then: Expecting NullPointerException
        Assertions.assertThrows(NullPointerException.class, () -> {
            processor.printDocument(writer, formatStack, namespaceStack, document);
        });
    }

    @Test
    void testPrintDocument_NullFormatStack() {
        // Given: A null FormatStack
        formatStack = null;
        // When & Then: Expecting NullPointerException
        Assertions.assertThrows(NullPointerException.class, () -> {
            processor.printDocument(writer, formatStack, namespaceStack, document);
        });
    }

    @Test
    void testPrintDocument_NullNamespaceStack() {
        // Given: A null NamespaceStack
        namespaceStack = null;
        // When & Then: Expecting NullPointerException
        Assertions.assertThrows(NullPointerException.class, () -> {
            processor.printDocument(writer, formatStack, namespaceStack, document);
        });
    }

    @Test
    void testPrintDocument_NullDocument() {
        // Given: A null Document
        document = null;
        // When & Then: Expecting NullPointerException
        Assertions.assertThrows(NullPointerException.class, () -> {
            processor.printDocument(writer, formatStack, namespaceStack, document);
        });
    }

    @Test
    void testPrintDocument_WriterThrowsIOException() throws IOException {
        // Given: A Writer that throws IOException
        writer = mock(Writer.class);
        doThrow(IOException.class).when(writer).write(anyString());
        // When & Then: Expecting IOException
        Assertions.assertThrows(IOException.class, () -> {
            processor.printDocument(writer, formatStack, namespaceStack, document);
        });
    }
}
