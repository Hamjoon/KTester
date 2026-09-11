package org.jdom2.output.support;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collections;
import java.io.IOException;
import org.jdom2.ProcessingInstruction;
import org.jdom2.Element;
import org.jdom2.Text;
import org.jdom2.output.Format;
import org.jdom2.util.NamespaceStack;
import org.jdom2.DocType;
import org.jdom2.CDATA;
import org.jdom2.Comment;
import org.jdom2.EntityRef;

@ExtendWith(MockitoExtension.class)
class AbstractXMLOutputProcessor_printContent_Test {

    private AbstractXMLOutputProcessor processor;

    private Writer writer;

    private FormatStack formatStack;

    private NamespaceStack namespaceStack;

    private Walker walker;

    @BeforeEach
    void setupBeforeEach() {
        writer = new StringWriter();
        Format format = Format.getPrettyFormat();
        formatStack = new FormatStack(format);
        namespaceStack = new NamespaceStack();
        walker = mock(Walker.class);
        processor = new AbstractXMLOutputProcessor() {
        };
    }

    @Test
    void testPrintContentWithEmptyWalker() throws IOException {
        when(walker.hasNext()).thenReturn(false);
        processor.printContent(writer, formatStack, namespaceStack, walker);
        assertEquals("", writer.toString());
    }

    @Test
    void testPrintContentWithText() throws IOException {
        when(walker.hasNext()).thenReturn(true).thenReturn(false);
        when(walker.next()).thenReturn(new Text("Sample text"));
        when(walker.isCDATA()).thenReturn(false);
        processor.printContent(writer, formatStack, namespaceStack, walker);
        assertEquals("Sample text", writer.toString());
    }

    @Test
    void testPrintContentWithCDATA() throws IOException {
        CDATA cdata = new CDATA("Sample CDATA");
        when(walker.hasNext()).thenReturn(true).thenReturn(false);
        when(walker.next()).thenReturn(cdata);
        when(walker.isCDATA()).thenReturn(true);
        processor.printContent(writer, formatStack, namespaceStack, walker);
        assertEquals("<![CDATA[Sample CDATA]]>", writer.toString());
    }

    @Test
    void testPrintContentWithComment() throws IOException {
        Comment comment = new Comment("This is a comment");
        when(walker.hasNext()).thenReturn(true).thenReturn(false);
        when(walker.next()).thenReturn(comment);
        processor.printContent(writer, formatStack, namespaceStack, walker);
        verify(processor, times(1)).printComment(writer, formatStack, comment);
    }

    @Test
    void testPrintContentWithDocType() throws IOException {
        DocType docType = new DocType("root");
        when(walker.hasNext()).thenReturn(true).thenReturn(false);
        when(walker.next()).thenReturn(docType);
        processor.printContent(writer, formatStack, namespaceStack, walker);
        verify(processor, times(1)).printDocType(writer, formatStack, docType);
    }

    @Test
    void testPrintContentWithElement() throws IOException {
        Element element = new Element("root");
        when(walker.hasNext()).thenReturn(true).thenReturn(false);
        when(walker.next()).thenReturn(element);
        processor.printContent(writer, formatStack, namespaceStack, walker);
        verify(processor, times(1)).printElement(writer, formatStack, namespaceStack, element);
    }

    @Test
    void testPrintContentWithEntityRef() throws IOException {
        EntityRef entityRef = new EntityRef("entity");
        when(walker.hasNext()).thenReturn(true).thenReturn(false);
        when(walker.next()).thenReturn(entityRef);
        processor.printContent(writer, formatStack, namespaceStack, walker);
        verify(processor, times(1)).printEntityRef(writer, formatStack, entityRef);
    }

    @Test
    void testPrintContentWithProcessingInstruction() throws IOException {
        ProcessingInstruction pi = new ProcessingInstruction("target", "data");
        when(walker.hasNext()).thenReturn(true).thenReturn(false);
        when(walker.next()).thenReturn(pi);
        processor.printContent(writer, formatStack, namespaceStack, walker);
        verify(processor, times(1)).printProcessingInstruction(writer, formatStack, pi);
    }

    @Test
    void testPrintContentWithIOException() throws IOException {
        when(walker.hasNext()).thenReturn(true).thenReturn(false);
        when(walker.next()).thenReturn(new Text("Sample text"));
        doThrow(new IOException("Writer error")).when(processor).textRaw(any(Writer.class), anyString());
        assertThrows(IOException.class, () -> {
            processor.printContent(writer, formatStack, namespaceStack, walker);
        });
    }
}
