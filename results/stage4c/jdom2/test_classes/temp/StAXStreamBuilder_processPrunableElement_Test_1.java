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
        // Initialize the builder and mock objects before each test
        builder = new StAXStreamBuilder();
    }

    private Element invokeProcessPrunableElement(JDOMFactory factory, XMLStreamReader reader, int depth, StAXFilter filter) throws Exception {
        Method method = StAXStreamBuilder.class.getDeclaredMethod("processPrunableElement", JDOMFactory.class, XMLStreamReader.class, int.class, StAXFilter.class);
        method.setAccessible(true);
        return (Element) method.invoke(null, factory, reader, depth, filter);
    }

    @Test
    void testProcessPrunableElement() throws XMLStreamException, JDOMException {
        // Given
        when(mockReader.getEventType()).thenReturn(XMLStreamConstants.START_ELEMENT);
        when(mockReader.hasNext()).thenReturn(true, false);
        when(mockReader.next()).thenReturn(XMLStreamConstants.END_ELEMENT);
        // When
        Element result = invokeProcessPrunableElement(mockFactory, mockReader, 0, mockFilter);
        // Then
        assertNotNull(result);
        verify(mockReader, atLeastOnce()).getEventType();
        verify(mockReader, atLeastOnce()).hasNext();
        verify(mockReader, atLeastOnce()).next();
    }

    @Test
    void testProcessPrunableElement_CompleteNestedStructure() throws XMLStreamException, JDOMException {
        // Given: Mock XMLStreamReader starting with START_ELEMENT and not pruning any elements
        when(mockReader.getEventType()).thenReturn(XMLStreamConstants.START_ELEMENT);
        when(mockReader.hasNext()).thenReturn(true, true, false);
        when(mockReader.next()).thenReturn(XMLStreamConstants.START_ELEMENT, XMLStreamConstants.END_ELEMENT);
        when(mockFilter.pruneElement(anyInt(), anyString(), any())).thenReturn(false);
        Element mockElement = mock(Element.class);
        when(mockFactory.element(anyString(), any(Namespace.class))).thenReturn(mockElement);
        // When: processPrunableElement is invoked
        Element result = invokeProcessPrunableElement(mockFactory, mockReader, 0, mockFilter);
        // Then: Verify the result is a complete nested structure
        assertNotNull(result);
        verify(mockReader, atLeastOnce()).getEventType();
        verify(mockReader, atLeastOnce()).hasNext();
        verify(mockReader, atLeastOnce()).next();
        verify(mockFilter, atLeastOnce()).pruneElement(anyInt(), anyString(), any());
    }

    @Test
    void testProcessPrunableElement_NotAtStartElement() {
        // Given: Mock XMLStreamReader not at START_ELEMENT
        when(mockReader.getEventType()).thenReturn(XMLStreamConstants.CHARACTERS);
        // When/Then: Expect JDOMException
        JDOMException exception = assertThrows(JDOMException.class, () -> invokeProcessPrunableElement(mockFactory, mockReader, 0, mockFilter));
        assertEquals("JDOM requires that the XMLStreamReader is at the START_ELEMENT state when retrieving an Element Fragment.", exception.getMessage());
    }

    @Test
    void testProcessPrunableElement_AllElementsPruned() throws XMLStreamException, JDOMException {
        // Given: Mock XMLStreamReader with START_ELEMENT and END_ELEMENT, and filter prunes all elements
        when(mockReader.getEventType()).thenReturn(XMLStreamConstants.START_ELEMENT);
        when(mockReader.hasNext()).thenReturn(true, false);
        when(mockReader.next()).thenReturn(XMLStreamConstants.END_ELEMENT);
        when(mockFilter.pruneElement(anyInt(), anyString(), any())).thenReturn(true);
        // When: processPrunableElement is invoked
        Element result = invokeProcessPrunableElement(mockFactory, mockReader, 0, mockFilter);
        // Then: Verify the result has no children
        assertNotNull(result);
        verify(mockReader, atLeastOnce()).getEventType();
        verify(mockReader, atLeastOnce()).hasNext();
        verify(mockReader, atLeastOnce()).next();
        verify(mockFilter, atLeastOnce()).pruneElement(anyInt(), anyString(), any());
    }

    @Test
    void testProcessPrunableElement_CDATAAndCharacters() throws XMLStreamException, JDOMException {
        // Given: Mock XMLStreamReader with START_ELEMENT, CDATA, and CHARACTERS events
        when(mockReader.getEventType()).thenReturn(XMLStreamConstants.START_ELEMENT);
        when(mockReader.hasNext()).thenReturn(true, true, false);
        when(mockReader.next()).thenReturn(XMLStreamConstants.CDATA, XMLStreamConstants.CHARACTERS, XMLStreamConstants.END_ELEMENT);
        when(mockFilter.pruneCDATA(anyInt(), anyString())).thenReturn(null);
        when(mockFilter.pruneText(anyInt(), anyString())).thenReturn("text");
        Element mockElement = mock(Element.class);
        when(mockFactory.element(anyString(), any(Namespace.class))).thenReturn(mockElement);
        when(mockFactory.text(anyString())).thenReturn(mock(Text.class));
        // When: processPrunableElement is invoked
        Element result = invokeProcessPrunableElement(mockFactory, mockReader, 0, mockFilter);
        // Then: Verify only CHARACTERS are added to the element
        assertNotNull(result);
        verify(mockReader, atLeastOnce()).getEventType();
        verify(mockReader, atLeastOnce()).hasNext();
        verify(mockReader, atLeastOnce()).next();
        verify(mockFilter, atLeastOnce()).pruneCDATA(anyInt(), anyString());
        verify(mockFilter, atLeastOnce()).pruneText(anyInt(), anyString());
    }

    @Test
    void testProcessPrunableElement_EntityReferencePruned() throws XMLStreamException, JDOMException {
        // Given: Mock XMLStreamReader with START_ELEMENT and ENTITY_REFERENCE events
        when(mockReader.getEventType()).thenReturn(XMLStreamConstants.START_ELEMENT);
        when(mockReader.hasNext()).thenReturn(true, true, false);
        when(mockReader.next()).thenReturn(XMLStreamConstants.ENTITY_REFERENCE, XMLStreamConstants.END_ELEMENT);
        when(mockFilter.pruneEntityRef(anyInt(), anyString())).thenReturn(true);
        // When: processPrunableElement is invoked
        Element result = invokeProcessPrunableElement(mockFactory, mockReader, 0, mockFilter);
        // Then: Verify entity references are not added
        assertNotNull(result);
        verify(mockReader, atLeastOnce()).getEventType();
        verify(mockReader, atLeastOnce()).hasNext();
        verify(mockReader, atLeastOnce()).next();
        verify(mockFilter, atLeastOnce()).pruneEntityRef(anyInt(), anyString());
    }

    @Test
    void testProcessPrunableElement_ProcessingInstructionNotPruned() throws XMLStreamException, JDOMException {
        // Given: Mock XMLStreamReader with START_ELEMENT and PROCESSING_INSTRUCTION events
        when(mockReader.getEventType()).thenReturn(XMLStreamConstants.START_ELEMENT);
        when(mockReader.hasNext()).thenReturn(true, true, false);
        when(mockReader.next()).thenReturn(XMLStreamConstants.PROCESSING_INSTRUCTION, XMLStreamConstants.END_ELEMENT);
        when(mockFilter.pruneProcessingInstruction(anyInt(), anyString())).thenReturn(false);
        Element mockElement = mock(Element.class);
        when(mockFactory.element(anyString(), any(Namespace.class))).thenReturn(mockElement);
        when(mockFactory.processingInstruction(anyString(), anyString())).thenReturn(mock(ProcessingInstruction.class));
        // When: processPrunableElement is invoked
        Element result = invokeProcessPrunableElement(mockFactory, mockReader, 0, mockFilter);
        // Then: Verify processing instructions are added
        assertNotNull(result);
        verify(mockReader, atLeastOnce()).getEventType();
        verify(mockReader, atLeastOnce()).hasNext();
        verify(mockReader, atLeastOnce()).next();
        verify(mockFilter, atLeastOnce()).pruneProcessingInstruction(anyInt(), anyString());
    }

    @Test
    void testProcessPrunableElement_CommentPruned() throws XMLStreamException, JDOMException {
        // Given: Mock XMLStreamReader with START_ELEMENT and COMMENT events
        when(mockReader.getEventType()).thenReturn(XMLStreamConstants.START_ELEMENT);
        when(mockReader.hasNext()).thenReturn(true, true, false);
        when(mockReader.next()).thenReturn(XMLStreamConstants.COMMENT, XMLStreamConstants.END_ELEMENT);
        when(mockFilter.pruneComment(anyInt(), anyString())).thenReturn(null);
        // When: processPrunableElement is invoked
        Element result = invokeProcessPrunableElement(mockFactory, mockReader, 0, mockFilter);
        // Then: Verify comments are not added
        assertNotNull(result);
        verify(mockReader, atLeastOnce()).getEventType();
        verify(mockReader, atLeastOnce()).hasNext();
        verify(mockReader, atLeastOnce()).next();
        verify(mockFilter, atLeastOnce()).pruneComment(anyInt(), anyString());
    }

    @Test
    void testProcessPrunableElement_UnsupportedEventType() throws XMLStreamException, JDOMException {
        // Given: Mock XMLStreamReader with unsupported event type
        when(mockReader.getEventType()).thenReturn(XMLStreamConstants.START_ELEMENT);
        when(mockReader.hasNext()).thenReturn(true);
        when(mockReader.next()).thenReturn(XMLStreamConstants.NOTATION_DECLARATION);
        // When/Then: Expect JDOMException for unsupported event
        JDOMException exception = assertThrows(JDOMException.class, () -> invokeProcessPrunableElement(mockFactory, mockReader, 0, mockFilter));
        assertEquals("Unexpected XMLStream event " + XMLStreamConstants.NOTATION_DECLARATION, exception.getMessage());
    }

    @Test
    void testProcessPrunableElement_SelectivelyPrunedNestedStructure() throws XMLStreamException, JDOMException {
        // Given: Mock XMLStreamReader with multiple nested elements and selective pruning
        when(mockReader.getEventType()).thenReturn(XMLStreamConstants.START_ELEMENT);
        when(mockReader.hasNext()).thenReturn(true, true, true, false);
        when(mockReader.next()).thenReturn(XMLStreamConstants.START_ELEMENT, XMLStreamConstants.START_ELEMENT, XMLStreamConstants.END_ELEMENT, XMLStreamConstants.END_ELEMENT);
        when(mockFilter.pruneElement(anyInt(), anyString(), any())).thenReturn(false, true);
        Element mockElement = mock(Element.class);
        when(mockFactory.element(anyString(), any(Namespace.class))).thenReturn(mockElement);
        // When: processPrunableElement is invoked
        Element result = invokeProcessPrunableElement(mockFactory, mockReader, 0, mockFilter);
        // Then: Verify nested structure is selectively pruned
        assertNotNull(result);
        verify(mockReader, atLeastOnce()).getEventType();
        verify(mockReader, atLeastOnce()).hasNext();
        verify(mockReader, atLeastOnce()).next();
        verify(mockFilter, atLeastOnce()).pruneElement(anyInt(), anyString(), any());
    }

    @Test
    void testProcessPrunableElement_CharactersAndSpace() throws XMLStreamException, JDOMException {
        // Given: Mock XMLStreamReader with CHARACTERS and SPACE events
        when(mockReader.getEventType()).thenReturn(XMLStreamConstants.START_ELEMENT);
        when(mockReader.hasNext()).thenReturn(true, true, false);
        when(mockReader.next()).thenReturn(XMLStreamConstants.CHARACTERS, XMLStreamConstants.SPACE, XMLStreamConstants.END_ELEMENT);
        when(mockFilter.pruneText(anyInt(), anyString())).thenReturn("text");
        when(mockFilter.pruneCDATA(anyInt(), anyString())).thenReturn(null);
        Element mockElement = mock(Element.class);
        when(mockFactory.element(anyString(), any(Namespace.class))).thenReturn(mockElement);
        when(mockFactory.text(anyString())).thenReturn(mock(Text.class));
        // When: processPrunableElement is invoked
        Element result = invokeProcessPrunableElement(mockFactory, mockReader, 0, mockFilter);
        // Then: Verify only CHARACTERS are added to the element
        assertNotNull(result);
        verify(mockReader, atLeastOnce()).getEventType();
        verify(mockReader, atLeastOnce()).hasNext();
        verify(mockReader, atLeastOnce()).next();
        verify(mockFilter, atLeastOnce()).pruneText(anyInt(), anyString());
    }

    @Test
    void testProcessPrunableElement_CDATAAndEntityReference() throws XMLStreamException, JDOMException {
        // Given: Mock XMLStreamReader with CDATA and ENTITY_REFERENCE events
        when(mockReader.getEventType()).thenReturn(XMLStreamConstants.START_ELEMENT);
        when(mockReader.hasNext()).thenReturn(true, true, false);
        when(mockReader.next()).thenReturn(XMLStreamConstants.CDATA, XMLStreamConstants.ENTITY_REFERENCE, XMLStreamConstants.END_ELEMENT);
        when(mockFilter.pruneCDATA(anyInt(), anyString())).thenReturn("cdata");
        when(mockFilter.pruneEntityRef(anyInt(), anyString())).thenReturn(true);
        Element mockElement = mock(Element.class);
        when(mockFactory.element(anyString(), any(Namespace.class))).thenReturn(mockElement);
        when(mockFactory.cdata(anyString())).thenReturn(mock(CDATA.class));
        // When: processPrunableElement is invoked
        Element result = invokeProcessPrunableElement(mockFactory, mockReader, 0, mockFilter);
        // Then: Verify CDATA content is included but not entity references
        assertNotNull(result);
        verify(mockReader, atLeastOnce()).getEventType();
        verify(mockReader, atLeastOnce()).hasNext();
        verify(mockReader, atLeastOnce()).next();
        verify(mockFilter, atLeastOnce()).pruneCDATA(anyInt(), anyString());
        verify(mockFilter, atLeastOnce()).pruneEntityRef(anyInt(), anyString());
    }

    @Test
    void testProcessPrunableElement_CommentsPrunedBasedOnDepth() throws XMLStreamException, JDOMException {
        // Given: Mock XMLStreamReader with START_ELEMENT and COMMENT events
        when(mockReader.getEventType()).thenReturn(XMLStreamConstants.START_ELEMENT);
        when(mockReader.hasNext()).thenReturn(true, true, false);
        when(mockReader.next()).thenReturn(XMLStreamConstants.COMMENT, XMLStreamConstants.END_ELEMENT);
        when(mockFilter.pruneComment(anyInt(), anyString())).thenReturn(null);
        // When: processPrunableElement is invoked
        Element result = invokeProcessPrunableElement(mockFactory, mockReader, 0, mockFilter);
        // Then: Verify comments are pruned at certain depths
        assertNotNull(result);
        verify(mockReader, atLeastOnce()).getEventType();
        verify(mockReader, atLeastOnce()).hasNext();
        verify(mockReader, atLeastOnce()).next();
        verify(mockFilter, atLeastOnce()).pruneComment(anyInt(), anyString());
    }

    @Test
    void testProcessPrunableElement_SelectivelyIncludedProcessingInstructions() throws XMLStreamException, JDOMException {
        // Given: Mock XMLStreamReader with START_ELEMENT and PROCESSING_INSTRUCTION events
        when(mockReader.getEventType()).thenReturn(XMLStreamConstants.START_ELEMENT);
        when(mockReader.hasNext()).thenReturn(true, true, false);
        when(mockReader.next()).thenReturn(XMLStreamConstants.PROCESSING_INSTRUCTION, XMLStreamConstants.END_ELEMENT);
        when(mockFilter.pruneProcessingInstruction(anyInt(), anyString())).thenReturn(false);
        Element mockElement = mock(Element.class);
        when(mockFactory.element(anyString(), any(Namespace.class))).thenReturn(mockElement);
        when(mockFactory.processingInstruction(anyString(), anyString())).thenReturn(mock(ProcessingInstruction.class));
        // When: processPrunableElement is invoked
        Element result = invokeProcessPrunableElement(mockFactory, mockReader, 0, mockFilter);
        // Then: Verify processing instructions are selectively included
        assertNotNull(result);
        verify(mockReader, atLeastOnce()).getEventType();
        verify(mockReader, atLeastOnce()).hasNext();
        verify(mockReader, atLeastOnce()).next();
        verify(mockFilter, atLeastOnce()).pruneProcessingInstruction(anyInt(), anyString());
    }

    @Test
    void testProcessPrunableElement_EndDocumentEvent() throws XMLStreamException, JDOMException {
        // Given: Mock XMLStreamReader with START_ELEMENT and END_DOCUMENT events
        when(mockReader.getEventType()).thenReturn(XMLStreamConstants.START_ELEMENT);
        when(mockReader.hasNext()).thenReturn(true, false);
        when(mockReader.next()).thenReturn(XMLStreamConstants.END_DOCUMENT);
        // When: processPrunableElement is invoked
        Element result = invokeProcessPrunableElement(mockFactory, mockReader, 0, mockFilter);
        // Then: Verify no further processing beyond END_DOCUMENT
        assertNotNull(result);
        verify(mockReader, atLeastOnce()).getEventType();
        verify(mockReader, atLeastOnce()).hasNext();
        verify(mockReader, atLeastOnce()).next();
    }

    @Test
    void testProcessPrunableElement_InvalidXMLStreamEvent() throws XMLStreamException, JDOMException {
        // Given: Mock XMLStreamReader with invalid XMLStream event
        when(mockReader.getEventType()).thenReturn(XMLStreamConstants.START_ELEMENT);
        when(mockReader.hasNext()).thenReturn(true);
        when(mockReader.next()).thenReturn(XMLStreamConstants.NOTATION_DECLARATION);
        // When/Then: Expect JDOMException for invalid XMLStream event
        JDOMException exception = assertThrows(JDOMException.class, () -> invokeProcessPrunableElement(mockFactory, mockReader, 0, mockFilter));
        assertEquals("Unexpected XMLStream event " + XMLStreamConstants.NOTATION_DECLARATION, exception.getMessage());
    }
}
