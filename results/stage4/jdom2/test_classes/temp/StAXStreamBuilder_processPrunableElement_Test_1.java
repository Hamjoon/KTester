package org.jdom2.input;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.xml.stream.XMLStreamReader;
import org.jdom2.JDOMFactory;
import org.jdom2.input.stax.StAXFilter;
import org.jdom2.JDOMException;
import javax.xml.stream.XMLStreamException;
import org.jdom2.Element;
import org.jdom2.DefaultJDOMFactory;
import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class StAXStreamBuilder_processPrunableElement_Test {

    private JDOMFactory factory;

    private XMLStreamReader reader;

    private StAXFilter filter;

    private StAXStreamBuilder staxStreamBuilder;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize necessary fields and dependencies
        factory = mock(JDOMFactory.class);
        reader = mock(XMLStreamReader.class);
        filter = mock(StAXFilter.class);
        staxStreamBuilder = new StAXStreamBuilder();
    }

    private Element invokeProcessPrunableElement(JDOMFactory factory, XMLStreamReader reader, int depth, StAXFilter filter) throws Exception {
        Method method = StAXStreamBuilder.class.getDeclaredMethod("processPrunableElement", JDOMFactory.class, XMLStreamReader.class, int.class, StAXFilter.class);
        method.setAccessible(true);
        return (Element) method.invoke(null, factory, reader, depth, filter);
    }

    @Test
    void testProcessPrunableElement() throws XMLStreamException, JDOMException {
        // Given
        when(reader.getEventType()).thenReturn(XMLStreamReader.START_ELEMENT);
        when(filter.pruneElement(anyInt(), anyString(), any())).thenReturn(false);
        // When
        Element result = invokeProcessPrunableElement(factory, reader, 0, filter);
        // Then
        assertNotNull(result);
    }

    @Test
    void testProcessPrunableElement_Valid_START_ELEMENT() throws XMLStreamException, JDOMException {
        // Test the method with a valid START_ELEMENT state and a filter that allows elements.
        when(reader.getEventType()).thenReturn(XMLStreamReader.START_ELEMENT);
        when(filter.pruneElement(anyInt(), anyString(), any())).thenReturn(false);
        Element mockElement = new Element("mockElement");
        when(factory.createElement("mockElement")).thenReturn(mockElement);
        // When
        Element result = invokeProcessPrunableElement(factory, reader, 0, filter);
        // Then
        assertNotNull(result);
        assertEquals("mockElement", result.getName());
    }

    @Test
    void testProcessPrunableElement_Invalid_START_ELEMENT_State() {
        // Test the method when the XMLStreamReader is not at START_ELEMENT, expecting a JDOMException.
        when(reader.getEventType()).thenReturn(XMLStreamReader.END_ELEMENT);
        // When & Then
        assertThrows(JDOMException.class, () -> {
            invokeProcessPrunableElement(factory, reader, 0, filter);
        });
    }

    @Test
    void testProcessPrunableElement_Handling_END_ELEMENT() throws XMLStreamException, JDOMException {
        // Test the method's ability to handle END_ELEMENT transitions correctly.
        when(reader.getEventType()).thenReturn(XMLStreamReader.START_ELEMENT, XMLStreamReader.END_ELEMENT);
        when(filter.pruneElement(anyInt(), anyString(), any())).thenReturn(false);
        Element mockElement = new Element("mockElement");
        when(factory.createElement("mockElement")).thenReturn(mockElement);
        // When
        Element result = invokeProcessPrunableElement(factory, reader, 0, filter);
        // Then
        assertNotNull(result);
        assertEquals("mockElement", result.getName());
    }

    @Test
    void testProcessPrunableElement_Handling_CDATA() throws XMLStreamException, JDOMException {
        // Test the method's ability to process CDATA sections correctly.
        when(reader.getEventType()).thenReturn(XMLStreamReader.START_ELEMENT, XMLStreamReader.CDATA, XMLStreamReader.END_ELEMENT);
        when(filter.pruneElement(anyInt(), anyString(), any())).thenReturn(false);
        when(filter.pruneCDATA(anyInt(), anyString())).thenReturn("CDATA Content");
        Element mockElement = new Element("mockElement");
        when(factory.createElement("mockElement")).thenReturn(mockElement);
        // When
        Element result = invokeProcessPrunableElement(factory, reader, 0, filter);
        // Then
        assertNotNull(result);
        assertEquals("mockElement", result.getName());
        // Assuming CDATA is added as content
        assertEquals(1, result.getContentSize());
    }

    @Test
    void testProcessPrunableElement_Handling_SPACE_AND_CHARACTERS() throws XMLStreamException, JDOMException {
        // Test the method's ability to handle SPACE and CHARACTERS correctly.
        when(reader.getEventType()).thenReturn(XMLStreamReader.START_ELEMENT, XMLStreamReader.CHARACTERS, XMLStreamReader.END_ELEMENT);
        when(filter.pruneElement(anyInt(), anyString(), any())).thenReturn(false);
        when(filter.pruneText(anyInt(), anyString())).thenReturn("Text Content");
        Element mockElement = new Element("mockElement");
        when(factory.createElement("mockElement")).thenReturn(mockElement);
        // When
        Element result = invokeProcessPrunableElement(factory, reader, 0, filter);
        // Then
        assertNotNull(result);
        assertEquals("mockElement", result.getName());
        // Assuming text is added as content
        assertEquals(1, result.getContentSize());
    }

    @Test
    void testProcessPrunableElement_Handling_COMMENT() throws XMLStreamException, JDOMException {
        // Test the method's ability to process COMMENT sections correctly.
        when(reader.getEventType()).thenReturn(XMLStreamReader.START_ELEMENT, XMLStreamReader.COMMENT, XMLStreamReader.END_ELEMENT);
        when(filter.pruneElement(anyInt(), anyString(), any())).thenReturn(false);
        when(filter.pruneComment(anyInt(), anyString())).thenReturn("Comment Content");
        Element mockElement = new Element("mockElement");
        when(factory.createElement("mockElement")).thenReturn(mockElement);
        // When
        Element result = invokeProcessPrunableElement(factory, reader, 0, filter);
        // Then
        assertNotNull(result);
        assertEquals("mockElement", result.getName());
        // Assuming comment is added as content
        assertEquals(1, result.getContentSize());
    }

    @Test
    void testProcessPrunableElement_Handling_ENTITY_REFERENCE() throws XMLStreamException, JDOMException {
        // Test the method's ability to handle ENTITY_REFERENCE correctly.
        when(reader.getEventType()).thenReturn(XMLStreamReader.START_ELEMENT, XMLStreamReader.ENTITY_REFERENCE, XMLStreamReader.END_ELEMENT);
        when(filter.pruneElement(anyInt(), anyString(), any())).thenReturn(false);
        when(filter.pruneEntityRef(anyInt(), anyString())).thenReturn(false);
        Element mockElement = new Element("mockElement");
        when(factory.createElement("mockElement")).thenReturn(mockElement);
        // When
        Element result = invokeProcessPrunableElement(factory, reader, 0, filter);
        // Then
        assertNotNull(result);
        assertEquals("mockElement", result.getName());
        // Assuming entity reference is added as content
        assertEquals(1, result.getContentSize());
    }

    @Test
    void testProcessPrunableElement_Handling_PROCESSING_INSTRUCTION() throws XMLStreamException, JDOMException {
        // Test the method's ability to process PROCESSING_INSTRUCTION correctly.
        when(reader.getEventType()).thenReturn(XMLStreamReader.START_ELEMENT, XMLStreamReader.PROCESSING_INSTRUCTION, XMLStreamReader.END_ELEMENT);
        when(filter.pruneElement(anyInt(), anyString(), any())).thenReturn(false);
        when(filter.pruneProcessingInstruction(anyInt(), anyString())).thenReturn(false);
        Element mockElement = new Element("mockElement");
        when(factory.createElement("mockElement")).thenReturn(mockElement);
        // When
        Element result = invokeProcessPrunableElement(factory, reader, 0, filter);
        // Then
        assertNotNull(result);
        assertEquals("mockElement", result.getName());
        // Assuming processing instruction is added as content
        assertEquals(1, result.getContentSize());
    }

    @Test
    void testProcessPrunableElement_Handling_Unexpected_XMLStream_Events() {
        // Test the method's behavior when encountering an unexpected XMLStream event.
        // An invalid event type
        when(reader.getEventType()).thenReturn(99);
        // When & Then
        assertThrows(JDOMException.class, () -> {
            invokeProcessPrunableElement(factory, reader, 0, filter);
        });
    }

    @Test
    void testProcessPrunableElement_Valid_Handling_Nested_Elements() throws XMLStreamException, JDOMException {
        // Test the method's ability to correctly process nested START_ELEMENT structures.
        when(reader.getEventType()).thenReturn(XMLStreamReader.START_ELEMENT, XMLStreamReader.START_ELEMENT, XMLStreamReader.END_ELEMENT, XMLStreamReader.END_ELEMENT);
        when(filter.pruneElement(anyInt(), anyString(), any())).thenReturn(false);
        Element parentElement = new Element("parent");
        Element childElement = new Element("child");
        when(factory.createElement("parent")).thenReturn(parentElement);
        when(factory.createElement("child")).thenReturn(childElement);
        // When
        Element result = invokeProcessPrunableElement(factory, reader, 0, filter);
        // Then
        assertNotNull(result);
        assertEquals("parent", result.getName());
        // Assuming one child is added
        assertEquals(1, result.getContentSize());
    }

    @Test
    void testProcessPrunableElement_Handling_Invalid_XMLStreamReader() {
        // Test the method when the XMLStreamReader throws an XMLStreamException during processing.
        when(reader.getEventType()).thenThrow(new XMLStreamException("Stream exception"));
        // When & Then
        assertThrows(XMLStreamException.class, () -> {
            invokeProcessPrunableElement(factory, reader, 0, filter);
        });
    }

    @Test
    void testProcessPrunableElement_Handling_Prune_Functions_Returning_Null() throws XMLStreamException, JDOMException {
        // Test the method when the filter's pruneElement function returns null, meaning the element is not pruned.
        when(reader.getEventType()).thenReturn(XMLStreamReader.START_ELEMENT, XMLStreamReader.END_ELEMENT);
        when(filter.pruneElement(anyInt(), anyString(), any())).thenReturn(null);
        Element mockElement = new Element("mockElement");
        when(factory.createElement("mockElement")).thenReturn(mockElement);
        // When
        Element result = invokeProcessPrunableElement(factory, reader, 0, filter);
        // Then
        assertNotNull(result);
        assertEquals("mockElement", result.getName());
        // No content added
        assertEquals(0, result.getContentSize());
    }

    @Test
    void testProcessPrunableElement_Handling_Prune_Functions_Returning_NonNull_Values() throws XMLStreamException, JDOMException {
        // Test the method when the filter's pruneCDATA function returns a non-null value, meaning CDATA content is added.
        when(reader.getEventType()).thenReturn(XMLStreamReader.START_ELEMENT, XMLStreamReader.CDATA, XMLStreamReader.END_ELEMENT);
        when(filter.pruneElement(anyInt(), anyString(), any())).thenReturn(false);
        when(filter.pruneCDATA(anyInt(), anyString())).thenReturn("CDATA Content");
        Element mockElement = new Element("mockElement");
        when(factory.createElement("mockElement")).thenReturn(mockElement);
        // When
        Element result = invokeProcessPrunableElement(factory, reader, 0, filter);
        // Then
        assertNotNull(result);
        assertEquals("mockElement", result.getName());
        // Assuming CDATA is added as content
        assertEquals(1, result.getContentSize());
    }
}
