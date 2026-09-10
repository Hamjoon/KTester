import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import org.jdom2.DocType;
import org.jdom2.output.support.FormatStack;
import org.jdom2.CDATA;
import org.jdom2.output.support.AbstractXMLOutputProcessor;
import org.jdom2.Content;
import org.jdom2.EntityRef;
import org.jdom2.output.Format;
import org.jdom2.Element;
import org.jdom2.Comment;
import org.jdom2.util.NamespaceStack;
import org.jdom2.Text;
import org.jdom2.ProcessingInstruction;
import org.jdom2.output.support.Walker;

@ExtendWith(MockitoExtension.class)
class AbstractXMLOutputProcessor_printContent_Test {

    private AbstractXMLOutputProcessor processor;

    private Writer writer;

    private Format format;

    private FormatStack formatStack;

    private NamespaceStack namespaceStack;

    private Walker walker;

    // Concrete subclass to instantiate AbstractXMLOutputProcessor
    class ConcreteXMLOutputProcessor extends AbstractXMLOutputProcessor {

        @Override
        public void process(Writer out, Format format, List<? extends Content> list) throws IOException {
            FormatStack fstack = new FormatStack(format);
            Walker walker = buildWalker(fstack, list, true);
            printContent(out, fstack, new NamespaceStack(), walker);
        }
    }

    @BeforeEach
    void setupBeforeEach() {
        writer = new StringWriter();
        format = mock(Format.class);
        formatStack = new FormatStack(format);
        namespaceStack = new NamespaceStack();
        walker = mock(Walker.class);
        processor = new ConcreteXMLOutputProcessor();
    }

    private void invokePrintMethod(String methodName, Object... args) throws Exception {
        var method = AbstractXMLOutputProcessor.class.getDeclaredMethod(methodName, getParameterTypes(args));
        method.setAccessible(true);
        method.invoke(processor, args);
    }

    private Class<?>[] getParameterTypes(Object... args) {
        return Arrays.stream(args).map(arg -> arg.getClass()).toArray(Class<?>[]::new);
    }

    @Test
    void testPrintContentWithValidInput() throws Exception {
        Content content = mock(Content.class);
        when(walker.hasNext()).thenReturn(true);
        when(walker.next()).thenReturn(content);
        processor.printContent(writer, formatStack, namespaceStack, walker);
        assertNotNull(writer.toString());
    }

    @Test
    void testPrintContentWithNullContent() throws Exception {
        when(walker.hasNext()).thenReturn(true);
        when(walker.next()).thenReturn(null);
        when(walker.text()).thenReturn("Some text value");
        when(walker.isCDATA()).thenReturn(false);
        processor.printContent(writer, formatStack, namespaceStack, walker);
        assertTrue(writer.toString().contains("Some text value"));
    }

    @Test
    void testPrintContentWithCDATA() throws Exception {
        CDATA cdata = mock(CDATA.class);
        when(cdata.getCType()).thenReturn(Content.CType.CDATA);
        when(walker.hasNext()).thenReturn(true);
        when(walker.next()).thenReturn(cdata);
        invokePrintMethod("printCDATA", writer, formatStack, cdata);
    }

    @Test
    void testPrintContentWithComment() throws Exception {
        Comment comment = mock(Comment.class);
        when(comment.getCType()).thenReturn(Content.CType.Comment);
        when(walker.hasNext()).thenReturn(true);
        when(walker.next()).thenReturn(comment);
        invokePrintMethod("printComment", writer, formatStack, comment);
    }

    @Test
    void testPrintContentWithDocType() throws Exception {
        DocType docType = mock(DocType.class);
        when(docType.getCType()).thenReturn(Content.CType.DocType);
        when(walker.hasNext()).thenReturn(true);
        when(walker.next()).thenReturn(docType);
        invokePrintMethod("printDocType", writer, formatStack, docType);
    }

    @Test
    void testPrintContentWithElement() throws Exception {
        Element element = mock(org.jdom2.Element.class);
        when(element.getCType()).thenReturn(Content.CType.Element);
        when(walker.hasNext()).thenReturn(true);
        when(walker.next()).thenReturn(element);
        invokePrintMethod("printElement", writer, formatStack, namespaceStack, element);
    }

    @Test
    void testPrintContentWithEntityRef() throws Exception {
        EntityRef entityRef = mock(EntityRef.class);
        when(entityRef.getCType()).thenReturn(Content.CType.EntityRef);
        when(walker.hasNext()).thenReturn(true);
        when(walker.next()).thenReturn(entityRef);
        invokePrintMethod("printEntityRef", writer, formatStack, entityRef);
    }

    @Test
    void testPrintContentWithProcessingInstruction() throws Exception {
        ProcessingInstruction pi = mock(ProcessingInstruction.class);
        when(pi.getCType()).thenReturn(Content.CType.ProcessingInstruction);
        when(walker.hasNext()).thenReturn(true);
        when(walker.next()).thenReturn(pi);
        invokePrintMethod("printProcessingInstruction", writer, formatStack, pi);
    }

    @Test
    void testPrintContentWithText() throws Exception {
        Text text = mock(org.jdom2.Text.class);
        when(text.getCType()).thenReturn(Content.CType.Text);
        when(walker.hasNext()).thenReturn(true);
        when(walker.next()).thenReturn(text);
        invokePrintMethod("printText", writer, formatStack, text);
    }

    @Test
    void testPrintContentWithMultipleContentTypes() throws Exception {
        when(walker.hasNext()).thenReturn(true, true, true, false);
        when(walker.next()).thenReturn(mock(CDATA.class), mock(Comment.class), mock(org.jdom2.Element.class));
        processor.printContent(writer, formatStack, namespaceStack, walker);
        verify(processor, times(1)).printCDATA(any(Writer.class), any(FormatStack.class), any(CDATA.class));
        verify(processor, times(1)).printComment(any(Writer.class), any(FormatStack.class), any(Comment.class));
        verify(processor, times(1)).printElement(any(Writer.class), any(FormatStack.class), any(NamespaceStack.class), any(org.jdom2.Element.class));
    }

    @Test
    void testPrintContentWithNoContent() throws Exception {
        when(walker.hasNext()).thenReturn(false);
        processor.printContent(writer, formatStack, namespaceStack, walker);
        assertEquals("", writer.toString());
    }

    @Test
    void testPrintContentWithIOExceptionFromWalker() throws Exception {
        when(walker.hasNext()).thenThrow(new IOException("Walker error"));
        assertThrows(IOException.class, () -> {
            processor.printContent(writer, formatStack, namespaceStack, walker);
        });
    }

    @Test
    void testPrintContentWithIOExceptionFromWriter() throws Exception {
        doThrow(new IOException("Writer error")).when(writer).write(anyString());
        when(walker.hasNext()).thenReturn(true);
        when(walker.next()).thenReturn(mock(Text.class));
        assertThrows(IOException.class, () -> {
            processor.printContent(writer, formatStack, namespaceStack, walker);
        });
    }
}
