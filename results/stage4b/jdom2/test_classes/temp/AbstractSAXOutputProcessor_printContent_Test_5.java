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
import org.xml.sax.ext.LexicalHandler;

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
    void testPrintContentWithEmptyWalker() throws Exception {
        walker = mock(Walker.class);
        when(walker.hasNext()).thenReturn(false);
        AbstractSAXOutputProcessor processor = spy(new AbstractSAXOutputProcessor());
        processor.printContent(saxTarget, formatStack, namespaceStack, walker);
        verifyNoInteractions(contentHandler);
    }

    @Test
    void testPrintContentWithNullContent() throws Exception {
        walker = mock(Walker.class);
        when(walker.hasNext()).thenReturn(true);
        when(walker.next()).thenReturn(null);
        when(walker.text()).thenReturn("Sample Text");
        when(walker.isCDATA()).thenReturn(false);
        AbstractSAXOutputProcessor processor = spy(new AbstractSAXOutputProcessor());
        processor.printContent(saxTarget, formatStack, namespaceStack, walker);
        verify(processor).printText(eq(saxTarget), eq(formatStack), any(Text.class));
    }

    @Test
    void testPrintContentWithCDATA() throws Exception {
        CDATA cdata = new CDATA("Test CDATA");
        walker = mock(Walker.class);
        when(walker.hasNext()).thenReturn(true);
        when(walker.next()).thenReturn(cdata);
        AbstractSAXOutputProcessor processor = spy(new AbstractSAXOutputProcessor());
        processor.printContent(saxTarget, formatStack, namespaceStack, walker);
        verify(processor).printCDATA(eq(saxTarget), eq(formatStack), eq(cdata));
    }

    @Test
    void testPrintContentWithComment() throws Exception {
        Comment comment = new Comment("Test Comment");
        walker = mock(Walker.class);
        when(walker.hasNext()).thenReturn(true);
        when(walker.next()).thenReturn(comment);
        AbstractSAXOutputProcessor processor = spy(new AbstractSAXOutputProcessor());
        processor.printContent(saxTarget, formatStack, namespaceStack, walker);
        verify(processor).printComment(eq(saxTarget), eq(formatStack), eq(comment));
    }

    @Test
    void testPrintContentWithDocType() throws Exception {
        DocType docType = new DocType("Test DocType");
        walker = mock(Walker.class);
        when(walker.hasNext()).thenReturn(true);
        when(walker.next()).thenReturn(docType);
        AbstractSAXOutputProcessor processor = spy(new AbstractSAXOutputProcessor());
        processor.printContent(saxTarget, formatStack, namespaceStack, walker);
        verifyNoInteractions(contentHandler);
    }

    @Test
    void testPrintContentWithElement() throws Exception {
        Element element = new Element("TestElement");
        walker = mock(Walker.class);
        when(walker.hasNext()).thenReturn(true);
        when(walker.next()).thenReturn(element);
        AbstractSAXOutputProcessor processor = spy(new AbstractSAXOutputProcessor());
        processor.printContent(saxTarget, formatStack, namespaceStack, walker);
        verify(processor).printElement(eq(saxTarget), eq(formatStack), eq(namespaceStack), eq(element));
    }

    @Test
    void testPrintContentWithEntityRef() throws Exception {
        EntityRef entityRef = new EntityRef("TestEntity");
        walker = mock(Walker.class);
        when(walker.hasNext()).thenReturn(true);
        when(walker.next()).thenReturn(entityRef);
        AbstractSAXOutputProcessor processor = spy(new AbstractSAXOutputProcessor());
        processor.printContent(saxTarget, formatStack, namespaceStack, walker);
        verify(processor).printEntityRef(eq(saxTarget), eq(formatStack), eq(entityRef));
    }

    @Test
    void testPrintContentWithProcessingInstruction() throws Exception {
        ProcessingInstruction pi = new ProcessingInstruction("Test PI");
        walker = mock(Walker.class);
        when(walker.hasNext()).thenReturn(true);
        when(walker.next()).thenReturn(pi);
        AbstractSAXOutputProcessor processor = spy(new AbstractSAXOutputProcessor());
        processor.printContent(saxTarget, formatStack, namespaceStack, walker);
        verify(processor).printProcessingInstruction(eq(saxTarget), eq(formatStack), eq(pi));
    }

    @Test
    void testPrintContentWithInterruptedWalker() {
        walker = mock(Walker.class);
        when(walker.hasNext()).thenReturn(true);
        when(walker.next()).thenThrow(new RuntimeException("Walker interrupted"));
        AbstractSAXOutputProcessor processor = new AbstractSAXOutputProcessor();
        assertThrows(SAXException.class, () -> {
            processor.printContent(saxTarget, formatStack, namespaceStack, walker);
        });
    }
}
