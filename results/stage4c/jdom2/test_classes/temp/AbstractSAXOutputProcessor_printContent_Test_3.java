package org.jdom2.output.support;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.ext.LexicalHandler;
import org.jdom2.output.Format;
import org.jdom2.util.NamespaceStack;
import org.jdom2.Comment;
import org.jdom2.DocType;
import org.jdom2.Element;
import org.jdom2.EntityRef;
import org.jdom2.ProcessingInstruction;
import org.jdom2.CDATA;
import org.jdom2.Text;

@ExtendWith(MockitoExtension.class)
class AbstractSAXOutputProcessor_printContent_Test {

    @Mock
    private ContentHandler contentHandler;

    @Mock
    private ErrorHandler errorHandler;

    @Mock
    private DTDHandler dtdHandler;

    @Mock
    private EntityResolver entityResolver;

    @Mock
    private LexicalHandler lexicalHandler;

    @Mock
    private DeclHandler declHandler;

    private SAXTarget saxTarget;

    private Format format;

    private FormatStack formatStack;

    private NamespaceStack namespaceStack;

    private Walker walker;

    private AbstractSAXOutputProcessor processor;

    @BeforeEach
    void setupBeforeEach() {
        saxTarget = new SAXTarget(contentHandler, errorHandler, dtdHandler, entityResolver, lexicalHandler, declHandler, true, true, "publicID", "systemID");
        // Use a static method to get a default format
        format = Format.getRawFormat();
        formatStack = new FormatStack(format);
        namespaceStack = new NamespaceStack();
        walker = mock(Walker.class);
        processor = spy(new AbstractSAXOutputProcessor() {
        });
    }

    @Test
    void testPrintContent_withCDATA() throws SAXException {
        CDATA cdata = new CDATA("some cdata");
        when(walker.hasNext()).thenReturn(true, false);
        when(walker.next()).thenReturn(cdata);
        when(walker.text()).thenReturn("some cdata");
        when(walker.isCDATA()).thenReturn(true);
        processor.printContent(saxTarget, formatStack, namespaceStack, walker);
        verify(processor).printCDATA(saxTarget, formatStack, cdata);
    }

    @Test
    void testPrintContent_withText() throws SAXException {
        Text text = new Text("some text");
        when(walker.hasNext()).thenReturn(true, false);
        when(walker.next()).thenReturn(text);
        when(walker.text()).thenReturn("some text");
        when(walker.isCDATA()).thenReturn(false);
        processor.printContent(saxTarget, formatStack, namespaceStack, walker);
        verify(processor).printText(saxTarget, formatStack, text);
    }

    @Test
    void testPrintContent_withEmptyWalker() throws SAXException {
        when(walker.hasNext()).thenReturn(false);
        processor.printContent(saxTarget, formatStack, namespaceStack, walker);
        verifyNoInteractions(contentHandler, lexicalHandler);
    }

    @Test
    void testPrintContent_withComment() throws SAXException {
        Comment comment = new Comment("some comment");
        when(walker.hasNext()).thenReturn(true, false);
        when(walker.next()).thenReturn(comment);
        processor.printContent(saxTarget, formatStack, namespaceStack, walker);
        verify(processor).printComment(saxTarget, formatStack, comment);
    }

    @Test
    void testPrintContent_withDocType() throws SAXException {
        DocType docType = new DocType("root");
        when(walker.hasNext()).thenReturn(true, false);
        when(walker.next()).thenReturn(docType);
        processor.printContent(saxTarget, formatStack, namespaceStack, walker);
        verifyNoInteractions(contentHandler, lexicalHandler);
    }

    @Test
    void testPrintContent_withElement() throws SAXException {
        Element element = new Element("element");
        when(walker.hasNext()).thenReturn(true, false);
        when(walker.next()).thenReturn(element);
        processor.printContent(saxTarget, formatStack, namespaceStack, walker);
        verify(processor).printElement(saxTarget, formatStack, namespaceStack, element);
    }

    @Test
    void testPrintContent_withEntityRef() throws SAXException {
        EntityRef entityRef = new EntityRef("entity");
        when(walker.hasNext()).thenReturn(true, false);
        when(walker.next()).thenReturn(entityRef);
        processor.printContent(saxTarget, formatStack, namespaceStack, walker);
        verify(processor).printEntityRef(saxTarget, formatStack, entityRef);
    }

    @Test
    void testPrintContent_withProcessingInstruction() throws SAXException {
        ProcessingInstruction pi = new ProcessingInstruction("target", "data");
        when(walker.hasNext()).thenReturn(true, false);
        when(walker.next()).thenReturn(pi);
        processor.printContent(saxTarget, formatStack, namespaceStack, walker);
        verify(processor).printProcessingInstruction(saxTarget, formatStack, pi);
    }

    @Test
    void testPrintContent_withNullSAXTarget() {
        saxTarget = null;
        when(walker.hasNext()).thenReturn(true);
        assertThrows(NullPointerException.class, () -> processor.printContent(saxTarget, formatStack, namespaceStack, walker));
    }

    @Test
    void testPrintContent_withNullFormatStack() {
        formatStack = null;
        when(walker.hasNext()).thenReturn(true);
        assertThrows(NullPointerException.class, () -> processor.printContent(saxTarget, formatStack, namespaceStack, walker));
    }

    @Test
    void testPrintContent_withNullNamespaceStack() {
        namespaceStack = null;
        when(walker.hasNext()).thenReturn(true);
        assertThrows(NullPointerException.class, () -> processor.printContent(saxTarget, formatStack, namespaceStack, walker));
    }

    @Test
    void testPrintContent_withNullWalker() {
        walker = null;
        assertThrows(NullPointerException.class, () -> processor.printContent(saxTarget, formatStack, namespaceStack, walker));
    }
}
