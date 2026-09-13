package org.jdom2.input;

import org.jdom2.JDOMException;
import org.jdom2.Element;
import org.jdom2.JDOMFactory;
import org.jdom2.input.stax.StAXFilter;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamException;
import org.jdom2.Content;
import org.jdom2.Namespace;
import org.jdom2.Text;
import org.jdom2.CDATA;
import org.jdom2.ProcessingInstruction;
import java.lang.reflect.Method;
import javax.xml.stream.XMLStreamConstants;
import static javax.xml.stream.XMLStreamConstants.*;

@ExtendWith(MockitoExtension.class)
class StAXStreamBuilder_processPrunableElement_Test {

    @Mock
    private JDOMFactory mockFactory;

    @Mock
    private XMLStreamReader mockReader;

    @Mock
    private StAXFilter mockFilter;

    private StAXStreamBuilder builder;

    @BeforeEach
    void setupBeforeEach() {
        builder = new StAXStreamBuilder();
    }

    private Element invokeProcessPrunableElement(JDOMFactory factory, XMLStreamReader reader, int depth, StAXFilter filter) throws Exception {
        Method method = StAXStreamBuilder.class.getDeclaredMethod("processPrunableElement", JDOMFactory.class, XMLStreamReader.class, int.class, StAXFilter.class);
        method.setAccessible(true);
        return (Element) method.invoke(builder, factory, reader, depth, filter);
    }

    @Test
    void testProcessPrunableElement() throws Exception {
        when(mockReader.getEventType()).thenReturn(XMLStreamConstants.START_ELEMENT);
        when(mockReader.hasNext()).thenReturn(true, false);
        when(mockReader.next()).thenReturn(XMLStreamConstants.END_ELEMENT);
        when(mockFilter.pruneElement(anyInt(), anyString(), any())).thenReturn(false);
        Element result = invokeProcessPrunableElement(mockFactory, mockReader, 0, mockFilter);
        assertNotNull(result);
        verify(mockReader, atLeastOnce()).getEventType();
        verify(mockReader, atLeastOnce()).hasNext();
        verify(mockReader, atLeastOnce()).next();
    }

    @Test
    void testProcessPrunableElement_CompleteNestedStructure() throws Exception {
        when(mockReader.getEventType()).thenReturn(XMLStreamConstants.START_ELEMENT);
        when(mockReader.hasNext()).thenReturn(true, true, false);
        when(mockReader.next()).thenReturn(XMLStreamConstants.START_ELEMENT, XMLStreamConstants.END_ELEMENT);
        when(mockFilter.pruneElement(anyInt(), anyString(), any())).thenReturn(false);
        Element mockElement = mock(Element.class);
        when(mockFactory.element(anyString(), any(Namespace.class))).thenReturn(mockElement);
        Element result = invokeProcessPrunableElement(mockFactory, mockReader, 0, mockFilter);
        assertNotNull(result);
        verify(mockReader, atLeastOnce()).getEventType();
        verify(mockReader, atLeastOnce()).hasNext();
        verify(mockReader, atLeastOnce()).next();
        verify(mockFilter, atLeastOnce()).pruneElement(anyInt(), anyString(), any());
    }

    @Test
    void testProcessPrunableElement_NotAtStartElement() {
        when(mockReader.getEventType()).thenReturn(XMLStreamConstants.CHARACTERS);
        JDOMException exception = assertThrows(JDOMException.class, () -> invokeProcessPrunableElement(mockFactory, mockReader, 0, mockFilter));
        assertEquals("JDOM requires that the XMLStreamReader is at the START_ELEMENT state when retrieving an Element Fragment.", exception.getMessage());
    }

    @Test
    void testProcessPrunableElement_AllElementsPruned() throws Exception {
        when(mockReader.getEventType()).thenReturn(XMLStreamConstants.START_ELEMENT);
        when(mockReader.hasNext()).thenReturn(true, false);
        when(mockReader.next()).thenReturn(XMLStreamConstants.END_ELEMENT);
        when(mockFilter.pruneElement(anyInt(), anyString(), any())).thenReturn(true);
        Element result = invokeProcessPrunableElement(mockFactory, mockReader, 0, mockFilter);
        // should be null if all elements are pruned
        assertNull(result);
        verify(mockReader, atLeastOnce()).getEventType();
        verify(mockReader, atLeastOnce()).hasNext();
        verify(mockReader, atLeastOnce()).next();
        verify(mockFilter, atLeastOnce()).pruneElement(anyInt(), anyString(), any());
    }

    @Test
    void testProcessPrunableElement_CDATAAndCharacters() throws Exception {
        when(mockReader.getEventType()).thenReturn(XMLStreamConstants.START_ELEMENT);
        when(mockReader.hasNext()).thenReturn(true, true, false);
        when(mockReader.next()).thenReturn(XMLStreamConstants.CDATA, XMLStreamConstants.CHARACTERS, XMLStreamConstants.END_ELEMENT);
        when(mockFilter.pruneCDATA(anyInt(), anyString())).thenReturn(null);
        when(mockFilter.pruneText(anyInt(), anyString())).thenReturn("text");
        Element mockElement = mock(Element.class);
        when(mockFactory.element(anyString(), any(Namespace.class))).thenReturn(mockElement);
        when(mockFactory.text(anyString())).thenReturn(mock(Text.class));
        Element result = invokeProcessPrunableElement(mockFactory, mockReader, 0, mockFilter);
        assertNotNull(result);
        verify(mockReader, atLeastOnce()).getEventType();
        verify(mockReader, atLeastOnce()).hasNext();
        verify(mockReader, atLeastOnce()).next();
        verify(mockFilter, atLeastOnce()).pruneCDATA(anyInt(), anyString());
        verify(mockFilter, atLeastOnce()).pruneText(anyInt(), anyString());
    }

    @Test
    void testProcessPrunableElement_EntityReferencePruned() throws Exception {
        when(mockReader.getEventType()).thenReturn(XMLStreamConstants.START_ELEMENT);
        when(mockReader.hasNext()).thenReturn(true, true, false);
        when(mockReader.next()).thenReturn(XMLStreamConstants.ENTITY_REFERENCE, XMLStreamConstants.END_ELEMENT);
        when(mockFilter.pruneEntityRef(anyInt(), anyString())).thenReturn(true);
        Element result = invokeProcessPrunableElement(mockFactory, mockReader, 0, mockFilter);
        assertNotNull(result);
        verify(mockReader, atLeastOnce()).getEventType();
        verify(mockReader, atLeastOnce()).hasNext();
        verify(mockReader, atLeastOnce()).next();
        verify(mockFilter, atLeastOnce()).pruneEntityRef(anyInt(), anyString());
    }

    @Test
    void testProcessPrunableElement_ProcessingInstructionNotPruned() throws Exception {
        when(mockReader.getEventType()).thenReturn(XMLStreamConstants.START_ELEMENT);
        when(mockReader.hasNext()).thenReturn(true, true, false);
        when(mockReader.next()).thenReturn(XMLStreamConstants.PROCESSING_INSTRUCTION, XMLStreamConstants.END_ELEMENT);
        when(mockFilter.pruneProcessingInstruction(anyInt(), anyString())).thenReturn(false);
        Element mockElement = mock(Element.class);
        when(mockFactory.element(anyString(), any(Namespace.class))).thenReturn(mockElement);
        when(mockFactory.processingInstruction(anyString(), anyString())).thenReturn(mock(ProcessingInstruction.class));
        Element result = invokeProcessPrunableElement(mockFactory, mockReader, 0, mockFilter);
        assertNotNull(result);
        verify(mockReader, atLeastOnce()).getEventType();
        verify(mockReader, atLeastOnce()).hasNext();
        verify(mockReader, atLeastOnce()).next();
        verify(mockFilter, atLeastOnce()).pruneProcessingInstruction(anyInt(), anyString());
    }

    @Test
    void testProcessPrunableElement_CommentPruned() throws Exception {
        when(mockReader.getEventType()).thenReturn(XMLStreamConstants.START_ELEMENT);
        when(mockReader.hasNext()).thenReturn(true, true, false);
        when(mockReader.next()).thenReturn(XMLStreamConstants.COMMENT, XMLStreamConstants.END_ELEMENT);
        when(mockFilter.pruneComment(anyInt(), anyString())).thenReturn(null);
        Element result = invokeProcessPrunableElement(mockFactory, mockReader, 0, mockFilter);
        assertNotNull(result);
        verify(mockReader, atLeastOnce()).getEventType();
        verify(mockReader, atLeastOnce()).hasNext();
        verify(mockReader, atLeastOnce()).next();
        verify(mockFilter, atLeastOnce()).pruneComment(anyInt(), anyString());
    }

    @Test
    void testProcessPrunableElement_UnsupportedEventType() throws XMLStreamException, JDOMException {
        when(mockReader.getEventType()).thenReturn(XMLStreamConstants.START_ELEMENT);
        when(mockReader.hasNext()).thenReturn(true);
        when(mockReader.next()).thenReturn(XMLStreamConstants.NOTATION_DECLARATION);
        JDOMException exception = assertThrows(JDOMException.class, () -> invokeProcessPrunableElement(mockFactory, mockReader, 0, mockFilter));
        assertEquals("Unexpected XMLStream event " + XMLStreamConstants.NOTATION_DECLARATION, exception.getMessage());
    }

    @Test
    void testProcessPrunableElement_SelectivelyPrunedNestedStructure() throws Exception {
        when(mockReader.getEventType()).thenReturn(XMLStreamConstants.START_ELEMENT);
        when(mockReader.hasNext()).thenReturn(true, true, true, false);
        when(mockReader.next()).thenReturn(XMLStreamConstants.START_ELEMENT, XMLStreamConstants.START_ELEMENT, XMLStreamConstants.END_ELEMENT, XMLStreamConstants.END_ELEMENT);
        when(mockFilter.pruneElement(anyInt(), anyString(), any())).thenReturn(false, true);
        Element mockElement = mock(Element.class);
        when(mockFactory.element(anyString(), any(Namespace.class))).thenReturn(mockElement);
        Element result = invokeProcessPrunableElement(mockFactory, mockReader, 0, mockFilter);
        assertNotNull(result);
        verify(mockReader, atLeastOnce()).getEventType();
        verify(mockReader, atLeastOnce()).hasNext();
        verify(mockReader, atLeastOnce()).next();
        verify(mockFilter, atLeastOnce()).pruneElement(anyInt(), anyString(), any());
    }

    @Test
    void testProcessPrunableElement_CharactersAndSpace() throws Exception {
        when(mockReader.getEventType()).thenReturn(XMLStreamConstants.START_ELEMENT);
        when(mockReader.hasNext()).thenReturn(true, true, false);
        when(mockReader.next()).thenReturn(XMLStreamConstants.CHARACTERS, XMLStreamConstants.SPACE, XMLStreamConstants.END_ELEMENT);
        when(mockFilter.pruneText(anyInt(), anyString())).thenReturn("text");
        when(mockFilter.pruneCDATA(anyInt(), anyString())).thenReturn(null);
        Element mockElement = mock(Element.class);
        when(mockFactory.element(anyString(), any(Namespace.class))).thenReturn(mockElement);
        when(mockFactory.text(anyString())).thenReturn(mock(Text.class));
        Element result = invokeProcessPrunableElement(mockFactory, mockReader, 0, mockFilter);
        assertNotNull(result);
        verify(mockReader, atLeastOnce()).getEventType();
        verify(mockReader, atLeastOnce()).hasNext();
        verify(mockReader, atLeastOnce()).next();
        verify(mockFilter, atLeastOnce()).pruneText(anyInt(), anyString());
    }

    @Test
    void testProcessPrunableElement_CDATAAndEntityReference() throws Exception {
        when(mockReader.getEventType()).thenReturn(XMLStreamConstants.START_ELEMENT);
        when(mockReader.hasNext()).thenReturn(true, true, false);
        when(mockReader.next()).thenReturn(XMLStreamConstants.CDATA, XMLStreamConstants.ENTITY_REFERENCE, XMLStreamConstants.END_ELEMENT);
        when(mockFilter.pruneCDATA(anyInt(), anyString())).thenReturn("cdata");
        when(mockFilter.pruneEntityRef(anyInt(), anyString())).thenReturn(true);
        Element mockElement = mock(Element.class);
        when(mockFactory.element(anyString(), any(Namespace.class))).thenReturn(mockElement);
        when(mockFactory.cdata(anyString())).thenReturn(mock(CDATA.class));
        Element result = invokeProcessPrunableElement(mockFactory, mockReader, 0, mockFilter);
        assertNotNull(result);
        verify(mockReader, atLeastOnce()).getEventType();
        verify(mockReader, atLeastOnce()).hasNext();
        verify(mockReader, atLeastOnce()).next();
        verify(mockFilter, atLeastOnce()).pruneCDATA(anyInt(), anyString());
        verify(mockFilter, atLeastOnce()).pruneEntityRef(anyInt(), anyString());
    }

    @Test
    void testProcessPrunableElement_CommentsPrunedBasedOnDepth() throws Exception {
        when(mockReader.getEventType()).thenReturn(XMLStreamConstants.START_ELEMENT);
        when(mockReader.hasNext()).thenReturn(true, true, false);
        when(mockReader.next()).thenReturn(XMLStreamConstants.COMMENT, XMLStreamConstants.END_ELEMENT);
        when(mockFilter.pruneComment(anyInt(), anyString())).thenReturn(null);
        Element result = invokeProcessPrunableElement(mockFactory, mockReader, 0, mockFilter);
        assertNotNull(result);
        verify(mockReader, atLeastOnce()).getEventType();
        verify(mockReader, atLeastOnce()).hasNext();
        verify(mockReader, atLeastOnce()).next();
        verify(mockFilter, atLeastOnce()).pruneComment(anyInt(), anyString());
    }

    @Test
    void testProcessPrunableElement_SelectivelyIncludedProcessingInstructions() throws Exception {
        when(mockReader.getEventType()).thenReturn(XMLStreamConstants.START_ELEMENT);
        when(mockReader.hasNext()).thenReturn(true, true, false);
        when(mockReader.next()).thenReturn(XMLStreamConstants.PROCESSING_INSTRUCTION, XMLStreamConstants.END_ELEMENT);
        when(mockFilter.pruneProcessingInstruction(anyInt(), anyString())).thenReturn(false);
        Element mockElement = mock(Element.class);
        when(mockFactory.element(anyString(), any(Namespace.class))).thenReturn(mockElement);
        when(mockFactory.processingInstruction(anyString(), anyString())).thenReturn(mock(ProcessingInstruction.class));
        Element result = invokeProcessPrunableElement(mockFactory, mockReader, 0, mockFilter);
        assertNotNull(result);
        verify(mockReader, atLeastOnce()).getEventType();
        verify(mockReader, atLeastOnce()).hasNext();
        verify(mockReader, atLeastOnce()).next();
        verify(mockFilter, atLeastOnce()).pruneProcessingInstruction(anyInt(), anyString());
    }

    @Test
    void testProcessPrunableElement_EndDocumentEvent() throws Exception {
        when(mockReader.getEventType()).thenReturn(XMLStreamConstants.START_ELEMENT);
        when(mockReader.hasNext()).thenReturn(true, false);
        when(mockReader.next()).thenReturn(XMLStreamConstants.END_DOCUMENT);
        JDOMException exception = assertThrows(JDOMException.class, () -> invokeProcessPrunableElement(mockFactory, mockReader, 0, mockFilter));
        assertEquals("Unexpected XMLStream event " + XMLStreamConstants.END_DOCUMENT, exception.getMessage());
    }

    @Test
    void testProcessPrunableElement_InvalidXMLStreamEvent() throws XMLStreamException, JDOMException {
        when(mockReader.getEventType()).thenReturn(XMLStreamConstants.START_ELEMENT);
        when(mockReader.hasNext()).thenReturn(true);
        when(mockReader.next()).thenReturn(XMLStreamConstants.NOTATION_DECLARATION);
        JDOMException exception = assertThrows(JDOMException.class, () -> invokeProcessPrunableElement(mockFactory, mockReader, 0, mockFilter));
        assertEquals("Unexpected XMLStream event " + XMLStreamConstants.NOTATION_DECLARATION, exception.getMessage());
    }
}
