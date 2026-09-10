package org.jdom2.output.support;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.xml.stream.util.XMLEventConsumer;
import javax.xml.stream.XMLEventFactory;
import org.jdom2.output.Format;
import org.jdom2.util.NamespaceStack;
import org.jdom2.output.support.FormatStack;
import org.jdom2.Content;
import org.jdom2.output.support.Walker;
import javax.xml.stream.XMLStreamException;
import java.util.List;
import java.util.ArrayList;
import org.jdom2.DocType;
import org.w3c.dom.Text;
import org.jdom2.CDATA;
import org.jdom2.EntityRef;
import org.jdom2.Element;
import org.jdom2.Comment;
import org.jdom2.ProcessingInstruction;

@ExtendWith(MockitoExtension.class)
class AbstractStAXEventProcessor_printContent_Test {

    private XMLEventConsumer mockConsumer;

    private FormatStack mockFormatStack;

    private NamespaceStack mockNamespaceStack;

    private XMLEventFactory mockEventFactory;

    private Walker mockWalker;

    @BeforeEach
    void setupBeforeEach() {
        mockConsumer = mock(XMLEventConsumer.class);
        mockFormatStack = mock(FormatStack.class);
        mockNamespaceStack = mock(NamespaceStack.class);
        mockEventFactory = mock(XMLEventFactory.class);
        mockWalker = mock(Walker.class);
    }

    @Test
    void testPrintContent() throws XMLStreamException {
        // Given
        when(mockWalker.hasNext()).thenReturn(true);
        when(mockWalker.next()).thenReturn(null);
        when(mockWalker.isCDATA()).thenReturn(false);
        when(mockWalker.text()).thenReturn("Sample Text");
        AbstractStAXEventProcessor processor = new AbstractStAXEventProcessor() {

            @Override
            public void process(XMLEventConsumer out, Format format, XMLEventFactory eventfactory, List<? extends Content> list) throws XMLStreamException {
                printContent(out, new FormatStack(format), new NamespaceStack(), eventfactory, buildWalker(new FormatStack(format), list, false));
            }
        };
        processor.printContent(mockConsumer, mockFormatStack, mockNamespaceStack, mockEventFactory, mockWalker);
        verify(mockConsumer, times(1)).add(any());
    }

    @Test
    void testPrintContentWithNullContent() throws XMLStreamException {
        when(mockWalker.hasNext()).thenReturn(true);
        when(mockWalker.next()).thenReturn(null);
        when(mockWalker.isCDATA()).thenReturn(false);
        when(mockWalker.text()).thenReturn("Sample Text");
        AbstractStAXEventProcessor processor = new AbstractStAXEventProcessor() {

            @Override
            public void process(XMLEventConsumer out, Format format, XMLEventFactory eventfactory, List<? extends Content> list) throws XMLStreamException {
                printContent(out, new FormatStack(format), new NamespaceStack(), eventfactory, buildWalker(new FormatStack(format), list, false));
            }
        };
        processor.printContent(mockConsumer, mockFormatStack, mockNamespaceStack, mockEventFactory, mockWalker);
        verify(mockConsumer, times(1)).add(any(org.w3c.dom.Text.class));
    }

    @Test
    void testPrintContentWithCDATAContent() throws XMLStreamException {
        when(mockWalker.hasNext()).thenReturn(true);
        when(mockWalker.next()).thenReturn(mock(CDATA.class));
        when(mockWalker.isCDATA()).thenReturn(true);
        AbstractStAXEventProcessor processor = new AbstractStAXEventProcessor() {

            @Override
            public void process(XMLEventConsumer out, Format format, XMLEventFactory eventfactory, List<? extends Content> list) throws XMLStreamException {
                printContent(out, new FormatStack(format), new NamespaceStack(), eventfactory, buildWalker(new FormatStack(format), list, false));
            }
        };
        processor.printContent(mockConsumer, mockFormatStack, mockNamespaceStack, mockEventFactory, mockWalker);
        verify(processor, times(1)).printCDATA(mockConsumer, mockFormatStack, mockEventFactory, any(CDATA.class));
    }

    @Test
    void testPrintContentWithCommentContent() throws XMLStreamException {
        when(mockWalker.hasNext()).thenReturn(true);
        when(mockWalker.next()).thenReturn(mock(Comment.class));
        AbstractStAXEventProcessor processor = new AbstractStAXEventProcessor() {

            @Override
            public void process(XMLEventConsumer out, Format format, XMLEventFactory eventfactory, List<? extends Content> list) throws XMLStreamException {
                printContent(out, new FormatStack(format), new NamespaceStack(), eventfactory, buildWalker(new FormatStack(format), list, false));
            }
        };
        processor.printContent(mockConsumer, mockFormatStack, mockNamespaceStack, mockEventFactory, mockWalker);
        verify(processor, times(1)).printComment(mockConsumer, mockFormatStack, mockEventFactory, any(Comment.class));
    }

    @Test
    void testPrintContentWithElementContent() throws XMLStreamException {
        when(mockWalker.hasNext()).thenReturn(true);
        when(mockWalker.next()).thenReturn(mock(Element.class));
        AbstractStAXEventProcessor processor = new AbstractStAXEventProcessor() {

            @Override
            public void process(XMLEventConsumer out, Format format, XMLEventFactory eventfactory, List<? extends Content> list) throws XMLStreamException {
                printContent(out, new FormatStack(format), new NamespaceStack(), eventfactory, buildWalker(new FormatStack(format), list, false));
            }
        };
        processor.printContent(mockConsumer, mockFormatStack, mockNamespaceStack, mockEventFactory, mockWalker);
        verify(processor, times(1)).printElement(mockConsumer, mockFormatStack, mockNamespaceStack, mockEventFactory, any(Element.class));
    }

    @Test
    void testPrintContentWithEntityRefContent() throws XMLStreamException {
        when(mockWalker.hasNext()).thenReturn(true);
        when(mockWalker.next()).thenReturn(mock(EntityRef.class));
        AbstractStAXEventProcessor processor = new AbstractStAXEventProcessor() {

            @Override
            public void process(XMLEventConsumer out, Format format, XMLEventFactory eventfactory, List<? extends Content> list) throws XMLStreamException {
                printContent(out, new FormatStack(format), new NamespaceStack(), eventfactory, buildWalker(new FormatStack(format), list, false));
            }
        };
        processor.printContent(mockConsumer, mockFormatStack, mockNamespaceStack, mockEventFactory, mockWalker);
        verify(processor, times(1)).printEntityRef(mockConsumer, mockFormatStack, mockEventFactory, any(EntityRef.class));
    }

    @Test
    void testPrintContentWithProcessingInstructionContent() throws XMLStreamException {
        when(mockWalker.hasNext()).thenReturn(true);
        when(mockWalker.next()).thenReturn(mock(ProcessingInstruction.class));
        AbstractStAXEventProcessor processor = new AbstractStAXEventProcessor() {

            @Override
            public void process(XMLEventConsumer out, Format format, XMLEventFactory eventfactory, List<? extends Content> list) throws XMLStreamException {
                printContent(out, new FormatStack(format), new NamespaceStack(), eventfactory, buildWalker(new FormatStack(format), list, false));
            }
        };
        processor.printContent(mockConsumer, mockFormatStack, mockNamespaceStack, mockEventFactory, mockWalker);
        verify(processor, times(1)).printProcessingInstruction(mockConsumer, mockFormatStack, mockEventFactory, any(ProcessingInstruction.class));
    }

    @Test
    void testPrintContentWithTextContent() throws XMLStreamException {
        when(mockWalker.hasNext()).thenReturn(true);
        when(mockWalker.next()).thenReturn(mock(org.w3c.dom.Text.class));
        AbstractStAXEventProcessor processor = new AbstractStAXEventProcessor() {

            @Override
            public void process(XMLEventConsumer out, Format format, XMLEventFactory eventfactory, List<? extends Content> list) throws XMLStreamException {
                printContent(out, new FormatStack(format), new NamespaceStack(), eventfactory, buildWalker(new FormatStack(format), list, false));
            }
        };
        processor.printContent(mockConsumer, mockFormatStack, mockNamespaceStack, mockEventFactory, mockWalker);
        verify(processor, times(1)).printText(mockConsumer, mockFormatStack, mockEventFactory, any(org.w3c.dom.Text.class));
    }

    @Test
    void testPrintContentWithDocTypeContent() throws XMLStreamException {
        when(mockWalker.hasNext()).thenReturn(true);
        when(mockWalker.next()).thenReturn(mock(DocType.class));
        AbstractStAXEventProcessor processor = new AbstractStAXEventProcessor() {

            @Override
            public void process(XMLEventConsumer out, Format format, XMLEventFactory eventfactory, List<? extends Content> list) throws XMLStreamException {
                printContent(out, new FormatStack(format), new NamespaceStack(), eventfactory, buildWalker(new FormatStack(format), list, false));
            }
        };
        processor.printContent(mockConsumer, mockFormatStack, mockNamespaceStack, mockEventFactory, mockWalker);
        verify(processor, times(1)).printDocType(mockConsumer, mockFormatStack, mockEventFactory, any(DocType.class));
    }

    @Test
    void testPrintContentWithUnexpectedContentType() {
        when(mockWalker.hasNext()).thenReturn(true);
        when(mockWalker.next()).thenReturn(mock(Content.class));
        AbstractStAXEventProcessor processor = new AbstractStAXEventProcessor() {

            @Override
            public void process(XMLEventConsumer out, Format format, XMLEventFactory eventfactory, List<? extends Content> list) throws XMLStreamException {
                printContent(out, new FormatStack(format), new NamespaceStack(), eventfactory, buildWalker(new FormatStack(format), list, false));
            }
        };
        assertThrows(IllegalStateException.class, () -> {
            processor.printContent(mockConsumer, mockFormatStack, mockNamespaceStack, mockEventFactory, mockWalker);
        });
    }

    @Test
    void testPrintContentWithEmptyWalker() throws XMLStreamException {
        when(mockWalker.hasNext()).thenReturn(false);
        AbstractStAXEventProcessor processor = new AbstractStAXEventProcessor() {

            @Override
            public void process(XMLEventConsumer out, Format format, XMLEventFactory eventfactory, List<? extends Content> list) throws XMLStreamException {
                printContent(out, new FormatStack(format), new NamespaceStack(), eventfactory, buildWalker(new FormatStack(format), list, false));
            }
        };
        processor.printContent(mockConsumer, mockFormatStack, mockNamespaceStack, mockEventFactory, mockWalker);
        verify(mockConsumer, never()).add(any());
    }

    @Test
    void testPrintContentWithXMLStreamException() {
        when(mockWalker.hasNext()).thenReturn(true);
        when(mockWalker.next()).thenThrow(new XMLStreamException("Mocked exception"));
        AbstractStAXEventProcessor processor = new AbstractStAXEventProcessor() {

            @Override
            public void process(XMLEventConsumer out, Format format, XMLEventFactory eventfactory, List<? extends Content> list) throws XMLStreamException {
                printContent(out, new FormatStack(format), new NamespaceStack(), eventfactory, buildWalker(new FormatStack(format), list, false));
            }
        };
        assertThrows(XMLStreamException.class, () -> {
            processor.printContent(mockConsumer, mockFormatStack, mockNamespaceStack, mockEventFactory, mockWalker);
        });
    }
}
