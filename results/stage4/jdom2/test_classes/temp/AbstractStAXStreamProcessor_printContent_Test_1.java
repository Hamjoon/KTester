package org.jdom2.output.support;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.XMLStreamException;
import java.util.List;
import org.jdom2.DocType;
import org.jdom2.CDATA;
import org.jdom2.EntityRef;
import org.jdom2.Content;
import org.jdom2.output.Format;
import org.jdom2.Element;
import org.jdom2.Comment;
import org.jdom2.util.NamespaceStack;
import org.jdom2.ProcessingInstruction;

@ExtendWith(MockitoExtension.class)
class AbstractStAXStreamProcessor_printContent_Test {

    private XMLStreamWriter xmlStreamWriter;

    private FormatStack formatStack;

    private NamespaceStack namespaceStack;

    private Walker walker;

    private AbstractStAXStreamProcessor processor;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize necessary fields and dependencies
        xmlStreamWriter = mock(XMLStreamWriter.class);
        Format format = mock(Format.class);
        formatStack = new FormatStack(format);
        namespaceStack = new NamespaceStack();
        walker = mock(Walker.class);
        // Create a concrete implementation of AbstractStAXStreamProcessor for testing
        processor = new AbstractStAXStreamProcessor() {

            @Override
            public void process(final XMLStreamWriter out, final FormatStack fstack, final NamespaceStack nstack, final List<? extends Content> list) throws XMLStreamException {
                // Implement the process method as needed for testing
                printContent(out, fstack, nstack, walker);
            }
        };
    }

    @Test
    void testPrintContent_NullContent_CallsPrintText() throws XMLStreamException {
        // Test case where walker has next content as null and is not a CDATA. It should call printText.
        when(walker.hasNext()).thenReturn(true);
        when(walker.isCDATA()).thenReturn(false);
        when(walker.next()).thenReturn(null);
        processor.process(xmlStreamWriter, formatStack, namespaceStack, null);
        verify(processor, times(1)).printText(eq(xmlStreamWriter), eq(formatStack), any(org.jdom2.Text.class));
    }

    @Test
    void testPrintContent_NullContent_CallsPrintCDATA() throws XMLStreamException {
        // Test case where walker has next content as null and is a CDATA. It should call printCDATA.
        when(walker.hasNext()).thenReturn(true);
        when(walker.isCDATA()).thenReturn(true);
        when(walker.next()).thenReturn(null);
        processor.process(xmlStreamWriter, formatStack, namespaceStack, null);
        verify(processor, times(1)).printCDATA(eq(xmlStreamWriter), eq(formatStack), any(CDATA.class));
    }

    @Test
    void testPrintContent_CDATAContent() throws XMLStreamException {
        // Test case where walker returns a CDATA object.
        CDATA cdata = new CDATA("sample CDATA");
        when(walker.hasNext()).thenReturn(true);
        when(walker.next()).thenReturn(cdata);
        processor.process(xmlStreamWriter, formatStack, namespaceStack, null);
        verify(processor, times(1)).printCDATA(eq(xmlStreamWriter), eq(formatStack), eq(cdata));
    }

    @Test
    void testPrintContent_CommentContent() throws XMLStreamException {
        // Test case where walker returns a Comment object.
        Comment comment = new Comment("sample comment");
        when(walker.hasNext()).thenReturn(true);
        when(walker.next()).thenReturn(comment);
        processor.process(xmlStreamWriter, formatStack, namespaceStack, null);
        verify(processor, times(1)).printComment(eq(xmlStreamWriter), eq(formatStack), eq(comment));
    }

    @Test
    void testPrintContent_ElementContent() throws XMLStreamException {
        // Test case where walker returns an Element object.
        Element element = new Element("sampleElement");
        when(walker.hasNext()).thenReturn(true);
        when(walker.next()).thenReturn(element);
        processor.process(xmlStreamWriter, formatStack, namespaceStack, null);
        verify(processor, times(1)).printElement(eq(xmlStreamWriter), eq(formatStack), eq(namespaceStack), eq(element));
    }

    @Test
    void testPrintContent_EntityRefContent() throws XMLStreamException {
        // Test case where walker returns an EntityRef object.
        EntityRef entityRef = new EntityRef("sampleEntity");
        when(walker.hasNext()).thenReturn(true);
        when(walker.next()).thenReturn(entityRef);
        processor.process(xmlStreamWriter, formatStack, namespaceStack, null);
        verify(processor, times(1)).printEntityRef(eq(xmlStreamWriter), eq(formatStack), eq(entityRef));
    }

    @Test
    void testPrintContent_ProcessingInstructionContent() throws XMLStreamException {
        // Test case where walker returns a ProcessingInstruction object.
        ProcessingInstruction pi = new ProcessingInstruction("samplePI", "data");
        when(walker.hasNext()).thenReturn(true);
        when(walker.next()).thenReturn(pi);
        processor.process(xmlStreamWriter, formatStack, namespaceStack, null);
        verify(processor, times(1)).printProcessingInstruction(eq(xmlStreamWriter), eq(formatStack), eq(pi));
    }

    @Test
    void testPrintContent_TextContent() throws XMLStreamException {
        // Test case where walker returns a Text object.
        org.jdom2.Text text = new org.jdom2.Text("sample text");
        when(walker.hasNext()).thenReturn(true);
        when(walker.next()).thenReturn(text);
        processor.process(xmlStreamWriter, formatStack, namespaceStack, null);
        verify(processor, times(1)).printText(eq(xmlStreamWriter), eq(formatStack), eq(text));
    }

    @Test
    void testPrintContent_DocTypeContent() throws XMLStreamException {
        // Test case where walker returns a DocType object.
        DocType docType = new DocType("sampleDocType");
        when(walker.hasNext()).thenReturn(true);
        when(walker.next()).thenReturn(docType);
        processor.process(xmlStreamWriter, formatStack, namespaceStack, null);
        verify(processor, times(1)).printDocType(eq(xmlStreamWriter), eq(formatStack), eq(docType));
    }

    @Test
    void testPrintContent_UnexpectedContent() {
        // Test case where walker returns an unexpected content type, should throw IllegalStateException.
        when(walker.hasNext()).thenReturn(true);
        // Mocking unexpected content
        when(walker.next()).thenReturn(mock(Content.class));
        assertThrows(IllegalStateException.class, () -> {
            processor.process(xmlStreamWriter, formatStack, namespaceStack, null);
        });
    }

    @Test
    void testPrintContent_NoNextContent() throws XMLStreamException {
        // Test case where walker has no next content. The method should complete without any content processing.
        when(walker.hasNext()).thenReturn(false);
        processor.process(xmlStreamWriter, formatStack, namespaceStack, null);
        // No interactions should occur since there is no content
        verifyNoInteractions(processor);
    }

    @Test
    void testPrintContent_NullAfterValidContent() throws XMLStreamException {
        // Test case where walker returns a valid content followed by null. It should call printText for the null case.
        org.jdom2.Text text = new org.jdom2.Text("sample text");
        // hasNext() returns true twice, then false
        when(walker.hasNext()).thenReturn(true, true, false);
        when(walker.next()).thenReturn(text, null);
        processor.process(xmlStreamWriter, formatStack, namespaceStack, null);
        verify(processor, times(1)).printText(eq(xmlStreamWriter), eq(formatStack), eq(text));
        verify(processor, times(1)).printText(eq(xmlStreamWriter), eq(formatStack), any(org.jdom2.Text.class));
    }

    @Test
    void testPrintContent_ThrowsXMLStreamException_CDATA() throws XMLStreamException {
        // Test case where printCDATA throws XMLStreamException when called.
        CDATA cdata = new CDATA("sample CDATA");
        when(walker.hasNext()).thenReturn(true);
        when(walker.next()).thenReturn(cdata);
        doThrow(new XMLStreamException()).when(processor).printCDATA(eq(xmlStreamWriter), eq(formatStack), eq(cdata));
        assertThrows(XMLStreamException.class, () -> {
            processor.process(xmlStreamWriter, formatStack, namespaceStack, null);
        });
    }

    @Test
    void testPrintContent_ThrowsXMLStreamException_Comment() throws XMLStreamException {
        // Test case where printComment throws XMLStreamException when called.
        Comment comment = new Comment("sample comment");
        when(walker.hasNext()).thenReturn(true);
        when(walker.next()).thenReturn(comment);
        doThrow(new XMLStreamException()).when(processor).printComment(eq(xmlStreamWriter), eq(formatStack), eq(comment));
        assertThrows(XMLStreamException.class, () -> {
            processor.process(xmlStreamWriter, formatStack, namespaceStack, null);
        });
    }

    @Test
    void testPrintContent_ThrowsXMLStreamException_Element() throws XMLStreamException {
        // Test case where printElement throws XMLStreamException when called.
        Element element = new Element("sampleElement");
        when(walker.hasNext()).thenReturn(true);
        when(walker.next()).thenReturn(element);
        doThrow(new XMLStreamException()).when(processor).printElement(eq(xmlStreamWriter), eq(formatStack), eq(namespaceStack), eq(element));
        assertThrows(XMLStreamException.class, () -> {
            processor.process(xmlStreamWriter, formatStack, namespaceStack, null);
        });
    }

    @Test
    void testPrintContent_ThrowsXMLStreamException_EntityRef() throws XMLStreamException {
        // Test case where printEntityRef throws XMLStreamException when called.
        EntityRef entityRef = new EntityRef("sampleEntity");
        when(walker.hasNext()).thenReturn(true);
        when(walker.next()).thenReturn(entityRef);
        doThrow(new XMLStreamException()).when(processor).printEntityRef(eq(xmlStreamWriter), eq(formatStack), eq(entityRef));
        assertThrows(XMLStreamException.class, () -> {
            processor.process(xmlStreamWriter, formatStack, namespaceStack, null);
        });
    }

    @Test
    void testPrintContent_ThrowsXMLStreamException_ProcessingInstruction() throws XMLStreamException {
        // Test case where printProcessingInstruction throws XMLStreamException when called.
        ProcessingInstruction pi = new ProcessingInstruction("samplePI", "data");
        when(walker.hasNext()).thenReturn(true);
        when(walker.next()).thenReturn(pi);
        doThrow(new XMLStreamException()).when(processor).printProcessingInstruction(eq(xmlStreamWriter), eq(formatStack), eq(pi));
        assertThrows(XMLStreamException.class, () -> {
            processor.process(xmlStreamWriter, formatStack, namespaceStack, null);
        });
    }

    @Test
    void testPrintContent_ThrowsXMLStreamException_Text() throws XMLStreamException {
        // Test case where printText throws XMLStreamException when called.
        org.jdom2.Text text = new org.jdom2.Text("sample text");
        when(walker.hasNext()).thenReturn(true);
        when(walker.next()).thenReturn(text);
        doThrow(new XMLStreamException()).when(processor).printText(eq(xmlStreamWriter), eq(formatStack), eq(text));
        assertThrows(XMLStreamException.class, () -> {
            processor.process(xmlStreamWriter, formatStack, namespaceStack, null);
        });
    }

    @Test
    void testPrintContent_ThrowsXMLStreamException_DocType() throws XMLStreamException {
        // Test case where printDocType throws XMLStreamException when called.
        DocType docType = new DocType("sampleDocType");
        when(walker.hasNext()).thenReturn(true);
        when(walker.next()).thenReturn(docType);
        doThrow(new XMLStreamException()).when(processor).printDocType(eq(xmlStreamWriter), eq(formatStack), eq(docType));
        assertThrows(XMLStreamException.class, () -> {
            processor.process(xmlStreamWriter, formatStack, namespaceStack, null);
        });
    }
}
