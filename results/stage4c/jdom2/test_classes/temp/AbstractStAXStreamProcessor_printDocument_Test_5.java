package org.jdom2.output.support;

import org.jdom2.output.Format;
import org.jdom2.util.NamespaceStack;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import static org.mockito.Mockito.*;
import org.jdom2.*;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class AbstractStAXStreamProcessor_printDocument_Test {

    @Mock
    private XMLStreamWriter xmlStreamWriter;

    @Mock
    private Format format;

    private FormatStack formatStack;

    private NamespaceStack namespaceStack;

    private Document document;

    private AbstractStAXStreamProcessor processor;

    @Nested
    class PrintDocumentMethodTests {

        @Test
        void testOmitDeclarationWithRootElement() throws XMLStreamException {
            // Given
            when(format.getOmitDeclaration()).thenReturn(true);
            when(format.getLineSeparator()).thenReturn("\n");
            document.setRootElement(new Element("root"));
            // When
            processor.printDocument(xmlStreamWriter, formatStack, namespaceStack, document);
            // Then
            verify(xmlStreamWriter, times(0)).writeStartDocument();
            verify(xmlStreamWriter, times(1)).writeCharacters("\n");
            verify(xmlStreamWriter, times(1)).writeEndDocument();
        }

        @Test
        void testOmitEncodingWithoutRootElement() throws XMLStreamException {
            // Given
            when(format.getOmitEncoding()).thenReturn(true);
            when(format.getLineSeparator()).thenReturn("\n");
            document.setRootElement(new Element("root"));
            // When
            processor.printDocument(xmlStreamWriter, formatStack, namespaceStack, document);
            // Then
            verify(xmlStreamWriter, times(1)).writeStartDocument("1.0");
            verify(xmlStreamWriter, times(1)).writeCharacters("\n");
            verify(xmlStreamWriter, times(1)).writeEndDocument();
        }

        @Test
        void testEncodingWithEmptyContent() throws XMLStreamException {
            // Given
            when(format.getEncoding()).thenReturn("UTF-8");
            when(format.getLineSeparator()).thenReturn("\n");
            document.setRootElement(new Element("root"));
            // When
            processor.printDocument(xmlStreamWriter, formatStack, namespaceStack, document);
            // Then
            verify(xmlStreamWriter, times(1)).writeStartDocument("UTF-8", "1.0");
            verify(xmlStreamWriter, times(1)).writeCharacters("\n");
            verify(xmlStreamWriter, times(1)).writeEndDocument();
        }

        @Test
        void testMixedContent() throws XMLStreamException {
            // Given
            List<Content> contents = new ArrayList<>();
            contents.add(new Comment("comment"));
            contents.add(new Element("element"));
            document.setRootElement(new Element("root"));
            document.getRootElement().addContent(contents);
            // When
            processor.printDocument(xmlStreamWriter, formatStack, namespaceStack, document);
            // Then
            verify(xmlStreamWriter, times(1)).writeStartDocument("UTF-8", "1.0");
            verify(xmlStreamWriter, times(1)).writeEndDocument();
            verify(processor, times(1)).printComment(any(), any(), any());
            verify(processor, times(1)).printElement(any(), any(), any(), any());
        }

        @Test
        void testWhitespaceText() throws XMLStreamException {
            // Given
            document.setRootElement(new Element("root"));
            document.getRootElement().addContent(new Text("   "));
            // When
            processor.printDocument(xmlStreamWriter, formatStack, namespaceStack, document);
            // Then
            verify(xmlStreamWriter, times(1)).writeCharacters("   ");
        }

        @Test
        void testCDATAContent() throws XMLStreamException {
            // Given
            document.setRootElement(new Element("root"));
            document.getRootElement().addContent(new CDATA("cdata"));
            // When
            processor.printDocument(xmlStreamWriter, formatStack, namespaceStack, document);
            // Then
            verify(processor, times(1)).printCDATA(any(), any(), any());
        }
    }

    @Nested
    class PrintDocumentMethodAdditionalTests {

        @Test
        void testNullLineSeparatorWithRootElement() throws XMLStreamException {
            // Given
            when(format.getLineSeparator()).thenReturn(null);
            document.setRootElement(new Element("root"));
            // When
            processor.printDocument(xmlStreamWriter, formatStack, namespaceStack, document);
            // Then
            verify(xmlStreamWriter, times(1)).writeStartDocument("UTF-8", "1.0");
            verify(xmlStreamWriter, times(1)).writeEndDocument();
        }

        @Test
        void testOmitDeclarationAndEncodingWithMultipleRootElements() throws XMLStreamException {
            // Given
            when(format.getOmitDeclaration()).thenReturn(true);
            when(format.getOmitEncoding()).thenReturn(true);
            document.setRootElement(new Element("root1"));
            document.getRootElement().addContent(new Element("root2"));
            // When
            processor.printDocument(xmlStreamWriter, formatStack, namespaceStack, document);
            // Then
            verify(xmlStreamWriter, times(0)).writeStartDocument();
            verify(xmlStreamWriter, times(1)).writeEndDocument();
        }

        @Test
        void testEntityRef() throws XMLStreamException {
            // Given
            document.setRootElement(new Element("root"));
            document.getRootElement().addContent(new EntityRef("entity"));
            // When
            processor.printDocument(xmlStreamWriter, formatStack, namespaceStack, document);
            // Then
            verify(processor, times(1)).printEntityRef(any(), any(), any());
        }

        @Test
        void testProcessingInstruction() throws XMLStreamException {
            // Given
            document.setRootElement(new Element("root"));
            document.getRootElement().addContent(new ProcessingInstruction("target", "data"));
            // When
            processor.printDocument(xmlStreamWriter, formatStack, namespaceStack, document);
            // Then
            verify(processor, times(1)).printProcessingInstruction(any(), any(), any());
        }
    }

    @Nested
    class PrintDocumentExceptionTests {

        @Test
        void testNullXMLStreamWriter() {
            // Given
            xmlStreamWriter = null;
            // When & Then
            Assertions.assertThrows(NullPointerException.class, () -> processor.printDocument(xmlStreamWriter, formatStack, namespaceStack, document));
        }

        @Test
        void testNullFormatStack() {
            // Given
            formatStack = null;
            // When & Then
            Assertions.assertThrows(NullPointerException.class, () -> processor.printDocument(xmlStreamWriter, formatStack, namespaceStack, document));
        }

        @Test
        void testNullNamespaceStack() {
            // Given
            namespaceStack = null;
            // When & Then
            Assertions.assertThrows(NullPointerException.class, () -> processor.printDocument(xmlStreamWriter, formatStack, namespaceStack, document));
        }

        @Test
        void testNullDocument() {
            // Given
            document = null;
            // When & Then
            Assertions.assertThrows(NullPointerException.class, () -> processor.printDocument(xmlStreamWriter, formatStack, namespaceStack, document));
        }

        @Test
        void testXMLStreamException() throws XMLStreamException {
            // Given
            doThrow(XMLStreamException.class).when(xmlStreamWriter).writeStartDocument(anyString(), anyString());
            // When & Then
            Assertions.assertThrows(XMLStreamException.class, () -> processor.printDocument(xmlStreamWriter, formatStack, namespaceStack, document));
        }
    }

    @BeforeEach
    void setupBeforeEach() {
        namespaceStack = new NamespaceStack();
        document = new Document();
        processor = new AbstractStAXStreamProcessor() {

            @Override
            protected void printAttribute(XMLStreamWriter out, FormatStack fstack, org.jdom2.Attribute attribute) throws XMLStreamException {
                // Mock implementation
            }

            @Override
            protected void printCDATA(XMLStreamWriter out, FormatStack fstack, org.jdom2.CDATA cdata) throws XMLStreamException {
                // Mock implementation
            }

            @Override
            protected void printComment(XMLStreamWriter out, FormatStack fstack, org.jdom2.Comment comment) throws XMLStreamException {
                // Mock implementation
            }

            @Override
            protected void printContent(XMLStreamWriter out, FormatStack fstack, NamespaceStack nstack, Walker walker) throws XMLStreamException {
                // Mock implementation
            }

            @Override
            protected void printDocType(XMLStreamWriter out, FormatStack fstack, org.jdom2.DocType docType) throws XMLStreamException {
                // Mock implementation
            }

            @Override
            protected void printElement(XMLStreamWriter out, FormatStack fstack, NamespaceStack nstack, org.jdom2.Element element) throws XMLStreamException {
                // Mock implementation
            }

            @Override
            protected void printEntityRef(XMLStreamWriter out, FormatStack fstack, org.jdom2.EntityRef entity) throws XMLStreamException {
                // Mock implementation
            }

            @Override
            protected void printNamespace(XMLStreamWriter out, FormatStack fstack, org.jdom2.Namespace ns) throws XMLStreamException {
                // Mock implementation
            }

            @Override
            protected void printProcessingInstruction(XMLStreamWriter out, FormatStack fstack, org.jdom2.ProcessingInstruction pi) throws XMLStreamException {
                // Mock implementation
            }

            @Override
            protected void printText(XMLStreamWriter out, FormatStack fstack, org.jdom2.Text text) throws XMLStreamException {
                // Mock implementation
            }
        };
    }

    @Test
    void testPrintDocument() throws XMLStreamException {
        // Given
        when(format.getEncoding()).thenReturn("UTF-8");
        when(format.getLineSeparator()).thenReturn("\n");
        when(format.getOmitDeclaration()).thenReturn(false);
        when(format.getOmitEncoding()).thenReturn(false);
        document.setRootElement(new Element("root"));
        // When
        processor.printDocument(xmlStreamWriter, formatStack, namespaceStack, document);
        // Then
        verify(xmlStreamWriter, times(1)).writeStartDocument("UTF-8", "1.0");
        verify(xmlStreamWriter, times(1)).writeEndDocument();
    }
}
