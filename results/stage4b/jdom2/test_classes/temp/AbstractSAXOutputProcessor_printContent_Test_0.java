package org.jdom2.output.support;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.LexicalHandler;
import org.xml.sax.ext.DeclHandler;
import org.jdom2.output.Format;
import org.jdom2.output.support.SAXTarget;
import org.jdom2.output.support.FormatStack;
import org.jdom2.util.NamespaceStack;
import org.jdom2.output.support.Walker;
import org.jdom2.Content;
import org.jdom2.CDATA;
import org.jdom2.Comment;
import org.jdom2.DocType;
import org.jdom2.Element;
import org.jdom2.EntityRef;
import org.jdom2.ProcessingInstruction;
import org.jdom2.Text;
import org.jdom2.JDOMException;
import java.util.Collections;
import java.util.List;
import org.xml.sax.SAXException;

@ExtendWith(MockitoExtension.class)
class AbstractSAXOutputProcessor_printContent_Test {

    private SAXTarget saxTarget;

    private FormatStack formatStack;

    private NamespaceStack namespaceStack;

    private Walker walker;

    private ContentHandler contentHandler;

    private ErrorHandler errorHandler;

    private DTDHandler dtdHandler;

    private EntityResolver entityResolver;

    private LexicalHandler lexicalHandler;

    private DeclHandler declHandler;

    private Format format;

    @BeforeEach
    void setupBeforeEach() {
        contentHandler = mock(ContentHandler.class);
        errorHandler = mock(ErrorHandler.class);
        dtdHandler = mock(DTDHandler.class);
        entityResolver = mock(EntityResolver.class);
        lexicalHandler = mock(LexicalHandler.class);
        declHandler = mock(DeclHandler.class);
        format = mock(Format.class);
        saxTarget = new SAXTarget(contentHandler, errorHandler, dtdHandler, entityResolver, lexicalHandler, declHandler, true, false, null, null);
        formatStack = new FormatStack(format);
        namespaceStack = new NamespaceStack();
    }

    @Test
    void testProcessWithList() throws JDOMException {
        List<Content> contentList = Collections.singletonList(new Text("Test Content"));
        walker = mock(Walker.class);
        when(walker.hasNext()).thenReturn(true);
        when(walker.next()).thenReturn(new Text("Test Content"));
        AbstractSAXOutputProcessor processor = new AbstractSAXOutputProcessor();
        processor.process(saxTarget, format, contentList);
        // Add assertions to verify expected behavior
    }

    @Test
    void testProcessWithCDATA() throws JDOMException {
        CDATA cdata = new CDATA("Test CDATA");
        walker = mock(Walker.class);
        when(walker.hasNext()).thenReturn(true);
        when(walker.next()).thenReturn(cdata);
        AbstractSAXOutputProcessor processor = new AbstractSAXOutputProcessor();
        processor.process(saxTarget, format, cdata);
        // Add assertions to verify expected behavior
    }

    @Test
    void testProcessWithText() throws JDOMException {
        Text text = new Text("Test Text");
        walker = mock(Walker.class);
        when(walker.hasNext()).thenReturn(true);
        when(walker.next()).thenReturn(text);
        AbstractSAXOutputProcessor processor = new AbstractSAXOutputProcessor();
        processor.process(saxTarget, format, text);
        // Add assertions to verify expected behavior
    }

    @Test
    void testPrintContentWithEmptyWalker() throws Exception {
        // Test the printContent method when the walker has no elements to process.
        walker = mock(Walker.class);
        when(walker.hasNext()).thenReturn(false);
        AbstractSAXOutputProcessor processor = spy(new AbstractSAXOutputProcessor());
        processor.printContent(saxTarget, formatStack, namespaceStack, walker);
        // Verify that no output is generated and method completes successfully.
        verifyNoInteractions(contentHandler);
    }

    @Test
    void testPrintContentWithNullContent() throws Exception {
        // Test the printContent method when the walker returns null content.
        walker = mock(Walker.class);
        when(walker.hasNext()).thenReturn(true);
        when(walker.next()).thenReturn(null);
        when(walker.text()).thenReturn("Sample Text");
        when(walker.isCDATA()).thenReturn(false);
        AbstractSAXOutputProcessor processor = spy(new AbstractSAXOutputProcessor());
        processor.printContent(saxTarget, formatStack, namespaceStack, walker);
        // Verify that printText is called with the text from walker.
        verify(processor).printText(eq(saxTarget), eq(formatStack), any(Text.class));
    }

    @Test
    void testPrintContentWithCDATA() throws Exception {
        // Test the printContent method when the walker returns a CDATA instance.
        CDATA cdata = new CDATA("Test CDATA");
        walker = mock(Walker.class);
        when(walker.hasNext()).thenReturn(true);
        when(walker.next()).thenReturn(cdata);
        AbstractSAXOutputProcessor processor = spy(new AbstractSAXOutputProcessor());
        processor.printContent(saxTarget, formatStack, namespaceStack, walker);
        // Verify that printCDATA is called with the CDATA content.
        verify(processor).printCDATA(eq(saxTarget), eq(formatStack), eq(cdata));
    }

    @Test
    void testPrintContentWithComment() throws Exception {
        // Test the printContent method when the walker returns a Comment instance.
        Comment comment = new Comment("Test Comment");
        walker = mock(Walker.class);
        when(walker.hasNext()).thenReturn(true);
        when(walker.next()).thenReturn(comment);
        AbstractSAXOutputProcessor processor = spy(new AbstractSAXOutputProcessor());
        processor.printContent(saxTarget, formatStack, namespaceStack, walker);
        // Verify that printComment is called with the Comment content.
        verify(processor).printComment(eq(saxTarget), eq(formatStack), eq(comment));
    }

    @Test
    void testPrintContentWithDocType() throws Exception {
        // Test the printContent method when the walker returns a DocType instance.
        DocType docType = new DocType("Test DocType");
        walker = mock(Walker.class);
        when(walker.hasNext()).thenReturn(true);
        when(walker.next()).thenReturn(docType);
        AbstractSAXOutputProcessor processor = spy(new AbstractSAXOutputProcessor());
        processor.printContent(saxTarget, formatStack, namespaceStack, walker);
        // Verify that no action is taken for DocType.
        verifyNoInteractions(contentHandler);
    }

    @Test
    void testPrintContentWithElement() throws Exception {
        // Test the printContent method when the walker returns an Element instance.
        Element element = new Element("TestElement");
        walker = mock(Walker.class);
        when(walker.hasNext()).thenReturn(true);
        when(walker.next()).thenReturn(element);
        AbstractSAXOutputProcessor processor = spy(new AbstractSAXOutputProcessor());
        processor.printContent(saxTarget, formatStack, namespaceStack, walker);
        // Verify that printElement is called with the Element content.
        verify(processor).printElement(eq(saxTarget), eq(formatStack), eq(namespaceStack), eq(element));
    }

    @Test
    void testPrintContentWithEntityRef() throws Exception {
        // Test the printContent method when the walker returns an EntityRef instance.
        EntityRef entityRef = new EntityRef("TestEntity");
        walker = mock(Walker.class);
        when(walker.hasNext()).thenReturn(true);
        when(walker.next()).thenReturn(entityRef);
        AbstractSAXOutputProcessor processor = spy(new AbstractSAXOutputProcessor());
        processor.printContent(saxTarget, formatStack, namespaceStack, walker);
        // Verify that printEntityRef is called with the EntityRef content.
        verify(processor).printEntityRef(eq(saxTarget), eq(formatStack), eq(entityRef));
    }

    @Test
    void testPrintContentWithProcessingInstruction() throws Exception {
        // Test the printContent method when the walker returns a ProcessingInstruction instance.
        ProcessingInstruction pi = new ProcessingInstruction("Test PI");
        walker = mock(Walker.class);
        when(walker.hasNext()).thenReturn(true);
        when(walker.next()).thenReturn(pi);
        AbstractSAXOutputProcessor processor = spy(new AbstractSAXOutputProcessor());
        processor.printContent(saxTarget, formatStack, namespaceStack, walker);
        // Verify that printProcessingInstruction is called with the ProcessingInstruction content.
        verify(processor).printProcessingInstruction(eq(saxTarget), eq(formatStack), eq(pi));
    }

    @Test
    void testPrintContentWithInterruptedWalker() {
        // Test the printContent method when the walker throws an exception during iteration.
        walker = mock(Walker.class);
        when(walker.hasNext()).thenReturn(true);
        when(walker.next()).thenThrow(new RuntimeException("Walker interrupted"));
        AbstractSAXOutputProcessor processor = new AbstractSAXOutputProcessor();
        // Verify that SAXException is thrown due to interruption in the walker.
        assertThrows(SAXException.class, () -> {
            processor.printContent(saxTarget, formatStack, namespaceStack, walker);
        });
    }
}
