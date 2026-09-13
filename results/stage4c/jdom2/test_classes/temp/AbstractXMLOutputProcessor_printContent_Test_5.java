package org.jdom2.output.support;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.IOException;
import java.io.Writer;
import java.util.Collections;
import java.util.List;
import org.jdom2.CDATA;
import org.jdom2.Content;
import org.jdom2.Text;
import org.jdom2.output.Format;
import org.jdom2.util.NamespaceStack;
import org.jdom2.Comment;
import org.jdom2.DocType;
import org.jdom2.Element;
import org.jdom2.EntityRef;
import org.jdom2.ProcessingInstruction;
import org.jdom2.IllegalDataException;

@ExtendWith(MockitoExtension.class)
class AbstractXMLOutputProcessor_printContent_Test {

    @Mock
    private Writer mockWriter;

    @Mock
    private Format mockFormat;

    @InjectMocks
    private AbstractXMLOutputProcessor outputProcessor = new AbstractXMLOutputProcessor() {
    };

    private FormatStack formatStack;

    private NamespaceStack namespaceStack;

    private Walker mockWalker;

    @BeforeEach
    void setupBeforeEach() {
        formatStack = new FormatStack(mockFormat);
        namespaceStack = new NamespaceStack();
        mockWalker = mock(Walker.class);
    }

    // Mock the buildWalker method for testing purposes
    private Walker buildWalker(FormatStack formatStack, List<? extends Content> contentList, boolean flag) {
        return mockWalker;
    }

    @Test
    void testPrintContentWithList() throws IOException {
        List<Content> contentList = Collections.singletonList(mock(Text.class));
        Walker walker = buildWalker(formatStack, contentList, true);
        when(mockWalker.hasNext()).thenReturn(true, false);
        when(mockWalker.next()).thenReturn(contentList.get(0));
        when(contentList.get(0).getCType()).thenReturn(Content.CType.Text);
        when(((Text) contentList.get(0)).getText()).thenReturn("sample text");
        // Mock escape strategy
        when(mockFormat.getEscapeStrategy()).thenReturn((ch) -> false);
        outputProcessor.printContent(mockWriter, formatStack, namespaceStack, walker);
        verify(mockWriter, atLeastOnce()).write(anyString());
    }

    @Test
    void testPrintContentWithCDATA() throws IOException {
        CDATA cdata = mock(CDATA.class);
        when(mockWalker.hasNext()).thenReturn(true, false);
        when(mockWalker.next()).thenReturn(cdata);
        when(cdata.getCType()).thenReturn(Content.CType.CDATA);
        doNothing().when(outputProcessor).printCDATA(any(), any(), any());
        outputProcessor.printContent(mockWriter, formatStack, namespaceStack, mockWalker);
        verify(outputProcessor, atLeastOnce()).printCDATA(eq(mockWriter), eq(formatStack), eq(cdata));
    }

    @Test
    void testPrintContentWithText() throws IOException {
        Text text = mock(Text.class);
        when(mockWalker.hasNext()).thenReturn(true, false);
        when(mockWalker.next()).thenReturn(text);
        when(text.getCType()).thenReturn(Content.CType.Text);
        when(text.getText()).thenReturn("sample text");
        doNothing().when(outputProcessor).printText(any(), any(), any());
        outputProcessor.printContent(mockWriter, formatStack, namespaceStack, mockWalker);
        verify(outputProcessor, atLeastOnce()).printText(eq(mockWriter), eq(formatStack), eq(text));
    }

    @Test
    void testPrintContentWithNullContent() throws IOException {
        when(mockWalker.hasNext()).thenReturn(true, false);
        when(mockWalker.next()).thenReturn(null);
        when(mockWalker.text()).thenReturn("text");
        when(mockWalker.isCDATA()).thenReturn(false);
        outputProcessor.printContent(mockWriter, formatStack, namespaceStack, mockWalker);
        verify(mockWriter, atLeastOnce()).write("text");
    }

    @Test
    void testPrintContentWithComment() throws IOException {
        Comment comment = mock(Comment.class);
        when(mockWalker.hasNext()).thenReturn(true, false);
        when(mockWalker.next()).thenReturn(comment);
        when(comment.getCType()).thenReturn(Content.CType.Comment);
        doNothing().when(outputProcessor).printComment(any(), any(), any());
        outputProcessor.printContent(mockWriter, formatStack, namespaceStack, mockWalker);
        verify(outputProcessor, atLeastOnce()).printComment(eq(mockWriter), eq(formatStack), eq(comment));
    }

    @Test
    void testPrintContentWithDocType() throws IOException {
        DocType docType = mock(DocType.class);
        when(mockWalker.hasNext()).thenReturn(true, false);
        when(mockWalker.next()).thenReturn(docType);
        when(docType.getCType()).thenReturn(Content.CType.DocType);
        doNothing().when(outputProcessor).printDocType(any(), any(), any());
        outputProcessor.printContent(mockWriter, formatStack, namespaceStack, mockWalker);
        verify(outputProcessor, atLeastOnce()).printDocType(eq(mockWriter), eq(formatStack), eq(docType));
    }

    @Test
    void testPrintContentWithEntityRef() throws IOException {
        EntityRef entityRef = mock(EntityRef.class);
        when(mockWalker.hasNext()).thenReturn(true, false);
        when(mockWalker.next()).thenReturn(entityRef);
        when(entityRef.getCType()).thenReturn(Content.CType.EntityRef);
        doNothing().when(outputProcessor).printEntityRef(any(), any(), any());
        outputProcessor.printContent(mockWriter, formatStack, namespaceStack, mockWalker);
        verify(outputProcessor, atLeastOnce()).printEntityRef(eq(mockWriter), eq(formatStack), eq(entityRef));
    }

    @Test
    void testPrintContentWithProcessingInstruction() throws IOException {
        ProcessingInstruction pi = mock(ProcessingInstruction.class);
        when(mockWalker.hasNext()).thenReturn(true, false);
        when(mockWalker.next()).thenReturn(pi);
        when(pi.getCType()).thenReturn(Content.CType.ProcessingInstruction);
        doNothing().when(outputProcessor).printProcessingInstruction(any(), any(), any());
        outputProcessor.printContent(mockWriter, formatStack, namespaceStack, mockWalker);
        verify(outputProcessor, atLeastOnce()).printProcessingInstruction(eq(mockWriter), eq(formatStack), eq(pi));
    }

    @Test
    void testPrintContentWithEmptyWalker() throws IOException {
        when(mockWalker.hasNext()).thenReturn(false);
        outputProcessor.printContent(mockWriter, formatStack, namespaceStack, mockWalker);
        verifyNoInteractions(mockWriter);
    }

    @Test
    void testPrintContentWithInvalidContent() {
        Content invalidContent = mock(Content.class);
        when(mockWalker.hasNext()).thenReturn(true, false);
        when(mockWalker.next()).thenReturn(invalidContent);
        when(invalidContent.getCType()).thenReturn(null);
        assertThrows(IllegalDataException.class, () -> {
            outputProcessor.printContent(mockWriter, formatStack, namespaceStack, mockWalker);
        });
    }

    @Test
    void testPrintContentWithNullWriter() {
        when(mockWalker.hasNext()).thenReturn(true);
        when(mockWalker.next()).thenReturn(mock(Text.class));
        assertThrows(NullPointerException.class, () -> {
            outputProcessor.printContent(null, formatStack, namespaceStack, mockWalker);
        });
    }

    @Test
    void testPrintContentWithNullFormatStack() {
        when(mockWalker.hasNext()).thenReturn(true);
        when(mockWalker.next()).thenReturn(mock(Text.class));
        assertThrows(NullPointerException.class, () -> {
            outputProcessor.printContent(mockWriter, null, namespaceStack, mockWalker);
        });
    }

    @Test
    void testPrintContentWithNullNamespaceStack() {
        when(mockWalker.hasNext()).thenReturn(true);
        when(mockWalker.next()).thenReturn(mock(Text.class));
        assertThrows(NullPointerException.class, () -> {
            outputProcessor.printContent(mockWriter, formatStack, null, mockWalker);
        });
    }

    @Test
    void testPrintContentWithNullWalker() {
        assertThrows(NullPointerException.class, () -> {
            outputProcessor.printContent(mockWriter, formatStack, namespaceStack, null);
        });
    }

    @Test
    void testPrintContentWithIOException() throws IOException {
        when(mockWalker.hasNext()).thenReturn(true);
        when(mockWalker.next()).thenReturn(mock(Text.class));
        doThrow(IOException.class).when(mockWriter).write(anyString());
        assertThrows(IOException.class, () -> {
            outputProcessor.printContent(mockWriter, formatStack, namespaceStack, mockWalker);
        });
    }
}
