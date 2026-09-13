


You are an experienced Java developer tasked with fixing a piece of code that contains execution errors. Your task is to resolve these issues after analyzing the code and its execution feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any logical errors.
2. Analyze the execution feedback to understand the specific errors and their locations within the code.
3. Apply necessary corrections to the code based on the feedback, and output the compelete correct code.
The Java code you need to fix is provided below:
```java
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
        // Initialize the builder and mock objects before each test
        builder = new StAXStreamBuilder();
    }

    private Element invokeProcessPrunableElement(JDOMFactory factory, XMLStreamReader reader, int depth, StAXFilter filter) throws Exception {
        Method method = StAXStreamBuilder.class.getDeclaredMethod("processPrunableElement", JDOMFactory.class, XMLStreamReader.class, int.class, StAXFilter.class);
        method.setAccessible(true);
        return (Element) method.invoke(null, factory, reader, depth, filter);
    }

    @Test
    void testProcessPrunableElement() throws Exception {
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
    void testProcessPrunableElement_CompleteNestedStructure() throws Exception {
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
    void testProcessPrunableElement_AllElementsPruned() throws Exception {
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
    void testProcessPrunableElement_CDATAAndCharacters() throws Exception {
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
    void testProcessPrunableElement_EntityReferencePruned() throws Exception {
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
    void testProcessPrunableElement_ProcessingInstructionNotPruned() throws Exception {
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
    void testProcessPrunableElement_CommentPruned() throws Exception {
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
    void testProcessPrunableElement_SelectivelyPrunedNestedStructure() throws Exception {
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
    void testProcessPrunableElement_CharactersAndSpace() throws Exception {
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
    void testProcessPrunableElement_CDATAAndEntityReference() throws Exception {
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
    void testProcessPrunableElement_CommentsPrunedBasedOnDepth() throws Exception {
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
    void testProcessPrunableElement_SelectivelyIncludedProcessingInstructions() throws Exception {
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
    void testProcessPrunableElement_EndDocumentEvent() throws Exception {
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
```

Corresponding execution feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended

╷
├─ JUnit Jupiter ✔
│  └─ StAXStreamBuilder_processPrunableElement_Test ✔
│     ├─ testProcessPrunableElement_CDATAAndEntityReference() ✘ 
│     │        Strict stubbing argument mismatch. Please check:
│     │         - this invocation of 'element' method:
│     │            mockFactory.element(
│     │            null,
│     │            [Namespace: prefix "" is mapped to URI ""]
│     │        );
│     │            -> at org.jdom2.input.StAXStreamBuilder.processElement(StAXStreamBuilder.java:505)
│     │         - has following stubbing(s) with different arguments:
│     │            1. mockFactory.element("", null);
│     │              -> at org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.testProcessPrunableElement_CDATAAndEntityReference(StAXStreamBuilder_processPrunableElement_Test.java:246)
│     │        Typically, stubbing argument mismatch indicates user mistake when writing tests.
│     │        Mockito fails early so that you can debug potential problem easily.
│     │        However, there are legit scenarios when this exception generates false negative signal:
│     │          - stubbing the same method multiple times using 'given().will()' or 'when().then()' API
│     │            Please use 'will().given()' or 'doReturn().when()' API for stubbing.
│     │          - stubbed method is intentionally invoked with different arguments by code under test
│     │            Please use default or 'silent' JUnit Rule (equivalent of Strictness.LENIENT).
│     │        For more information see javadoc for PotentialStubbingProblem class.
│     ├─ testProcessPrunableElement_AllElementsPruned() ✘ Cannot invoke "org.jdom2.Element.getParentElement()" because "current" is null
│     ├─ testProcessPrunableElement_InvalidXMLStreamEvent() ✘ Unexpected exception type thrown, expected: <org.jdom2.JDOMException> but was: <java.lang.reflect.InvocationTargetException>
│     ├─ testProcessPrunableElement_CommentsPrunedBasedOnDepth() ✘ 
│     │        Strict stubbing argument mismatch. Please check:
│     │         - this invocation of 'pruneComment' method:
│     │            mockFilter.pruneComment(1, null);
│     │            -> at org.jdom2.input.StAXStreamBuilder.processPrunableElement(StAXStreamBuilder.java:351)
│     │         - has following stubbing(s) with different arguments:
│     │            1. mockFilter.pruneComment(0, "");
│     │              -> at org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.testProcessPrunableElement_CommentsPrunedBasedOnDepth(StAXStreamBuilder_processPrunableElement_Test.java:265)
│     │        Typically, stubbing argument mismatch indicates user mistake when writing tests.
│     │        Mockito fails early so that you can debug potential problem easily.
│     │        However, there are legit scenarios when this exception generates false negative signal:
│     │          - stubbing the same method multiple times using 'given().will()' or 'when().then()' API
│     │            Please use 'will().given()' or 'doReturn().when()' API for stubbing.
│     │          - stubbed method is intentionally invoked with different arguments by code under test
│     │            Please use default or 'silent' JUnit Rule (equivalent of Strictness.LENIENT).
│     │        For more information see javadoc for PotentialStubbingProblem class.
│     ├─ testProcessPrunableElement_CharactersAndSpace() ✘ 
│     │        Strict stubbing argument mismatch. Please check:
│     │         - this invocation of 'element' method:
│     │            mockFactory.element(
│     │            null,
│     │            [Namespace: prefix "" is mapped to URI ""]
│     │        );
│     │            -> at org.jdom2.input.StAXStreamBuilder.processElement(StAXStreamBuilder.java:505)
│     │         - has following stubbing(s) with different arguments:
│     │            1. mockFactory.element("", null);
│     │              -> at org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.testProcessPrunableElement_CharactersAndSpace(StAXStreamBuilder_processPrunableElement_Test.java:225)
│     │        Typically, stubbing argument mismatch indicates user mistake when writing tests.
│     │        Mockito fails early so that you can debug potential problem easily.
│     │        However, there are legit scenarios when this exception generates false negative signal:
│     │          - stubbing the same method multiple times using 'given().will()' or 'when().then()' API
│     │            Please use 'will().given()' or 'doReturn().when()' API for stubbing.
│     │          - stubbed method is intentionally invoked with different arguments by code under test
│     │            Please use default or 'silent' JUnit Rule (equivalent of Strictness.LENIENT).
│     │        For more information see javadoc for PotentialStubbingProblem class.
│     ├─ testProcessPrunableElement_CompleteNestedStructure() ✘ 
│     │        Strict stubbing argument mismatch. Please check:
│     │         - this invocation of 'element' method:
│     │            mockFactory.element(
│     │            null,
│     │            [Namespace: prefix "" is mapped to URI ""]
│     │        );
│     │            -> at org.jdom2.input.StAXStreamBuilder.processElement(StAXStreamBuilder.java:505)
│     │         - has following stubbing(s) with different arguments:
│     │            1. mockFactory.element("", null);
│     │              -> at org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.testProcessPrunableElement_CompleteNestedStructure(StAXStreamBuilder_processPrunableElement_Test.java:73)
│     │        Typically, stubbing argument mismatch indicates user mistake when writing tests.
│     │        Mockito fails early so that you can debug potential problem easily.
│     │        However, there are legit scenarios when this exception generates false negative signal:
│     │          - stubbing the same method multiple times using 'given().will()' or 'when().then()' API
│     │            Please use 'will().given()' or 'doReturn().when()' API for stubbing.
│     │          - stubbed method is intentionally invoked with different arguments by code under test
│     │            Please use default or 'silent' JUnit Rule (equivalent of Strictness.LENIENT).
│     │        For more information see javadoc for PotentialStubbingProblem class.
│     ├─ testProcessPrunableElement_NotAtStartElement() ✘ Unexpected exception type thrown, expected: <org.jdom2.JDOMException> but was: <java.lang.reflect.InvocationTargetException>
│     ├─ testProcessPrunableElement_UnsupportedEventType() ✘ Unexpected exception type thrown, expected: <org.jdom2.JDOMException> but was: <java.lang.reflect.InvocationTargetException>
│     ├─ testProcessPrunableElement_SelectivelyPrunedNestedStructure() ✘ 
│     │        Strict stubbing argument mismatch. Please check:
│     │         - this invocation of 'element' method:
│     │            mockFactory.element(
│     │            null,
│     │            [Namespace: prefix "" is mapped to URI ""]
│     │        );
│     │            -> at org.jdom2.input.StAXStreamBuilder.processElement(StAXStreamBuilder.java:505)
│     │         - has following stubbing(s) with different arguments:
│     │            1. mockFactory.element("", null);
│     │              -> at org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.testProcessPrunableElement_SelectivelyPrunedNestedStructure(StAXStreamBuilder_processPrunableElement_Test.java:205)
│     │        Typically, stubbing argument mismatch indicates user mistake when writing tests.
│     │        Mockito fails early so that you can debug potential problem easily.
│     │        However, there are legit scenarios when this exception generates false negative signal:
│     │          - stubbing the same method multiple times using 'given().will()' or 'when().then()' API
│     │            Please use 'will().given()' or 'doReturn().when()' API for stubbing.
│     │          - stubbed method is intentionally invoked with different arguments by code under test
│     │            Please use default or 'silent' JUnit Rule (equivalent of Strictness.LENIENT).
│     │        For more information see javadoc for PotentialStubbingProblem class.
│     ├─ testProcessPrunableElement_EntityReferencePruned() ✘ 
│     │        Strict stubbing argument mismatch. Please check:
│     │         - this invocation of 'pruneEntityRef' method:
│     │            mockFilter.pruneEntityRef(1, null);
│     │            -> at org.jdom2.input.StAXStreamBuilder.processPrunableElement(StAXStreamBuilder.java:357)
│     │         - has following stubbing(s) with different arguments:
│     │            1. mockFilter.pruneEntityRef(0, "");
│     │              -> at org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.testProcessPrunableElement_EntityReferencePruned(StAXStreamBuilder_processPrunableElement_Test.java:138)
│     │        Typically, stubbing argument mismatch indicates user mistake when writing tests.
│     │        Mockito fails early so that you can debug potential problem easily.
│     │        However, there are legit scenarios when this exception generates false negative signal:
│     │          - stubbing the same method multiple times using 'given().will()' or 'when().then()' API
│     │            Please use 'will().given()' or 'doReturn().when()' API for stubbing.
│     │          - stubbed method is intentionally invoked with different arguments by code under test
│     │            Please use default or 'silent' JUnit Rule (equivalent of Strictness.LENIENT).
│     │        For more information see javadoc for PotentialStubbingProblem class.
│     ├─ testProcessPrunableElement() ✘ Cannot invoke "org.jdom2.Element.getParentElement()" because "current" is null
│     ├─ testProcessPrunableElement_SelectivelyIncludedProcessingInstructions() ✘ 
│     │        Strict stubbing argument mismatch. Please check:
│     │         - this invocation of 'element' method:
│     │            mockFactory.element(
│     │            null,
│     │            [Namespace: prefix "" is mapped to URI ""]
│     │        );
│     │            -> at org.jdom2.input.StAXStreamBuilder.processElement(StAXStreamBuilder.java:505)
│     │         - has following stubbing(s) with different arguments:
│     │            1. mockFactory.element("", null);
│     │              -> at org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.testProcessPrunableElement_SelectivelyIncludedProcessingInstructions(StAXStreamBuilder_processPrunableElement_Test.java:284)
│     │        Typically, stubbing argument mismatch indicates user mistake when writing tests.
│     │        Mockito fails early so that you can debug potential problem easily.
│     │        However, there are legit scenarios when this exception generates false negative signal:
│     │          - stubbing the same method multiple times using 'given().will()' or 'when().then()' API
│     │            Please use 'will().given()' or 'doReturn().when()' API for stubbing.
│     │          - stubbed method is intentionally invoked with different arguments by code under test
│     │            Please use default or 'silent' JUnit Rule (equivalent of Strictness.LENIENT).
│     │        For more information see javadoc for PotentialStubbingProblem class.
│     ├─ testProcessPrunableElement_ProcessingInstructionNotPruned() ✘ 
│     │        Strict stubbing argument mismatch. Please check:
│     │         - this invocation of 'element' method:
│     │            mockFactory.element(
│     │            null,
│     │            [Namespace: prefix "" is mapped to URI ""]
│     │        );
│     │            -> at org.jdom2.input.StAXStreamBuilder.processElement(StAXStreamBuilder.java:505)
│     │         - has following stubbing(s) with different arguments:
│     │            1. mockFactory.element("", null);
│     │              -> at org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.testProcessPrunableElement_ProcessingInstructionNotPruned(StAXStreamBuilder_processPrunableElement_Test.java:157)
│     │        Typically, stubbing argument mismatch indicates user mistake when writing tests.
│     │        Mockito fails early so that you can debug potential problem easily.
│     │        However, there are legit scenarios when this exception generates false negative signal:
│     │          - stubbing the same method multiple times using 'given().will()' or 'when().then()' API
│     │            Please use 'will().given()' or 'doReturn().when()' API for stubbing.
│     │          - stubbed method is intentionally invoked with different arguments by code under test
│     │            Please use default or 'silent' JUnit Rule (equivalent of Strictness.LENIENT).
│     │        For more information see javadoc for PotentialStubbingProblem class.
│     ├─ testProcessPrunableElement_CommentPruned() ✘ 
│     │        Strict stubbing argument mismatch. Please check:
│     │         - this invocation of 'pruneComment' method:
│     │            mockFilter.pruneComment(1, null);
│     │            -> at org.jdom2.input.StAXStreamBuilder.processPrunableElement(StAXStreamBuilder.java:351)
│     │         - has following stubbing(s) with different arguments:
│     │            1. mockFilter.pruneComment(0, "");
│     │              -> at org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.testProcessPrunableElement_CommentPruned(StAXStreamBuilder_processPrunableElement_Test.java:175)
│     │        Typically, stubbing argument mismatch indicates user mistake when writing tests.
│     │        Mockito fails early so that you can debug potential problem easily.
│     │        However, there are legit scenarios when this exception generates false negative signal:
│     │          - stubbing the same method multiple times using 'given().will()' or 'when().then()' API
│     │            Please use 'will().given()' or 'doReturn().when()' API for stubbing.
│     │          - stubbed method is intentionally invoked with different arguments by code under test
│     │            Please use default or 'silent' JUnit Rule (equivalent of Strictness.LENIENT).
│     │        For more information see javadoc for PotentialStubbingProblem class.
│     ├─ testProcessPrunableElement_EndDocumentEvent() ✘ Unexpected XMLStream event 1
│     └─ testProcessPrunableElement_CDATAAndCharacters() ✘ 
│              Strict stubbing argument mismatch. Please check:
│               - this invocation of 'element' method:
│                  mockFactory.element(
│                  null,
│                  [Namespace: prefix "" is mapped to URI ""]
│              );
│                  -> at org.jdom2.input.StAXStreamBuilder.processElement(StAXStreamBuilder.java:505)
│               - has following stubbing(s) with different arguments:
│                  1. mockFactory.element("", null);
│                    -> at org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.testProcessPrunableElement_CDATAAndCharacters(StAXStreamBuilder_processPrunableElement_Test.java:119)
│              Typically, stubbing argument mismatch indicates user mistake when writing tests.
│              Mockito fails early so that you can debug potential problem easily.
│              However, there are legit scenarios when this exception generates false negative signal:
│                - stubbing the same method multiple times using 'given().will()' or 'when().then()' API
│                  Please use 'will().given()' or 'doReturn().when()' API for stubbing.
│                - stubbed method is intentionally invoked with different arguments by code under test
│                  Please use default or 'silent' JUnit Rule (equivalent of Strictness.LENIENT).
│              For more information see javadoc for PotentialStubbingProblem class.
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Failures (16):
  JUnit Jupiter:StAXStreamBuilder_processPrunableElement_Test:testProcessPrunableElement_CDATAAndEntityReference()
    MethodSource [className = 'org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test', methodName = 'testProcessPrunableElement_CDATAAndEntityReference', methodParameterTypes = '']
    => org.mockito.exceptions.misusing.PotentialStubbingProblem: 
Strict stubbing argument mismatch. Please check:
 - this invocation of 'element' method:
    mockFactory.element(
    null,
    [Namespace: prefix "" is mapped to URI ""]
);
    -> at org.jdom2.input.StAXStreamBuilder.processElement(StAXStreamBuilder.java:505)
 - has following stubbing(s) with different arguments:
    1. mockFactory.element("", null);
      -> at org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.testProcessPrunableElement_CDATAAndEntityReference(StAXStreamBuilder_processPrunableElement_Test.java:246)
Typically, stubbing argument mismatch indicates user mistake when writing tests.
Mockito fails early so that you can debug potential problem easily.
However, there are legit scenarios when this exception generates false negative signal:
  - stubbing the same method multiple times using 'given().will()' or 'when().then()' API
    Please use 'will().given()' or 'doReturn().when()' API for stubbing.
  - stubbed method is intentionally invoked with different arguments by code under test
    Please use default or 'silent' JUnit Rule (equivalent of Strictness.LENIENT).
For more information see javadoc for PotentialStubbingProblem class.
       org.jdom2.input.StAXStreamBuilder.processElement(StAXStreamBuilder.java:505)
       org.jdom2.input.StAXStreamBuilder.processPrunableElement(StAXStreamBuilder.java:304)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.invokeProcessPrunableElement(StAXStreamBuilder_processPrunableElement_Test.java:47)
       org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.testProcessPrunableElement_CDATAAndEntityReference(StAXStreamBuilder_processPrunableElement_Test.java:249)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       [...]
  JUnit Jupiter:StAXStreamBuilder_processPrunableElement_Test:testProcessPrunableElement_AllElementsPruned()
    MethodSource [className = 'org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test', methodName = 'testProcessPrunableElement_AllElementsPruned', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "org.jdom2.Element.getParentElement()" because "current" is null
       org.jdom2.input.StAXStreamBuilder.processPrunableElement(StAXStreamBuilder.java:334)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.invokeProcessPrunableElement(StAXStreamBuilder_processPrunableElement_Test.java:47)
       org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.testProcessPrunableElement_AllElementsPruned(StAXStreamBuilder_processPrunableElement_Test.java:101)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:StAXStreamBuilder_processPrunableElement_Test:testProcessPrunableElement_InvalidXMLStreamEvent()
    MethodSource [className = 'org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test', methodName = 'testProcessPrunableElement_InvalidXMLStreamEvent', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Unexpected exception type thrown, expected: <org.jdom2.JDOMException> but was: <java.lang.reflect.InvocationTargetException>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:67)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3128)
       org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.testProcessPrunableElement_InvalidXMLStreamEvent(StAXStreamBuilder_processPrunableElement_Test.java:318)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.invokeProcessPrunableElement(StAXStreamBuilder_processPrunableElement_Test.java:47)
       [...]
     Caused by: org.jdom2.JDOMException: Unexpected XMLStream event 1
       org.jdom2.input.StAXStreamBuilder.processPrunableElement(StAXStreamBuilder.java:370)
       [...]
  JUnit Jupiter:StAXStreamBuilder_processPrunableElement_Test:testProcessPrunableElement_CommentsPrunedBasedOnDepth()
    MethodSource [className = 'org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test', methodName = 'testProcessPrunableElement_CommentsPrunedBasedOnDepth', methodParameterTypes = '']
    => org.mockito.exceptions.misusing.PotentialStubbingProblem: 
Strict stubbing argument mismatch. Please check:
 - this invocation of 'pruneComment' method:
    mockFilter.pruneComment(1, null);
    -> at org.jdom2.input.StAXStreamBuilder.processPrunableElement(StAXStreamBuilder.java:351)
 - has following stubbing(s) with different arguments:
    1. mockFilter.pruneComment(0, "");
      -> at org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.testProcessPrunableElement_CommentsPrunedBasedOnDepth(StAXStreamBuilder_processPrunableElement_Test.java:265)
Typically, stubbing argument mismatch indicates user mistake when writing tests.
Mockito fails early so that you can debug potential problem easily.
However, there are legit scenarios when this exception generates false negative signal:
  - stubbing the same method multiple times using 'given().will()' or 'when().then()' API
    Please use 'will().given()' or 'doReturn().when()' API for stubbing.
  - stubbed method is intentionally invoked with different arguments by code under test
    Please use default or 'silent' JUnit Rule (equivalent of Strictness.LENIENT).
For more information see javadoc for PotentialStubbingProblem class.
       org.jdom2.input.StAXStreamBuilder.processPrunableElement(StAXStreamBuilder.java:351)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.invokeProcessPrunableElement(StAXStreamBuilder_processPrunableElement_Test.java:47)
       org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.testProcessPrunableElement_CommentsPrunedBasedOnDepth(StAXStreamBuilder_processPrunableElement_Test.java:267)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:StAXStreamBuilder_processPrunableElement_Test:testProcessPrunableElement_CharactersAndSpace()
    MethodSource [className = 'org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test', methodName = 'testProcessPrunableElement_CharactersAndSpace', methodParameterTypes = '']
    => org.mockito.exceptions.misusing.PotentialStubbingProblem: 
Strict stubbing argument mismatch. Please check:
 - this invocation of 'element' method:
    mockFactory.element(
    null,
    [Namespace: prefix "" is mapped to URI ""]
);
    -> at org.jdom2.input.StAXStreamBuilder.processElement(StAXStreamBuilder.java:505)
 - has following stubbing(s) with different arguments:
    1. mockFactory.element("", null);
      -> at org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.testProcessPrunableElement_CharactersAndSpace(StAXStreamBuilder_processPrunableElement_Test.java:225)
Typically, stubbing argument mismatch indicates user mistake when writing tests.
Mockito fails early so that you can debug potential problem easily.
However, there are legit scenarios when this exception generates false negative signal:
  - stubbing the same method multiple times using 'given().will()' or 'when().then()' API
    Please use 'will().given()' or 'doReturn().when()' API for stubbing.
  - stubbed method is intentionally invoked with different arguments by code under test
    Please use default or 'silent' JUnit Rule (equivalent of Strictness.LENIENT).
For more information see javadoc for PotentialStubbingProblem class.
       org.jdom2.input.StAXStreamBuilder.processElement(StAXStreamBuilder.java:505)
       org.jdom2.input.StAXStreamBuilder.processPrunableElement(StAXStreamBuilder.java:304)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.invokeProcessPrunableElement(StAXStreamBuilder_processPrunableElement_Test.java:47)
       org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.testProcessPrunableElement_CharactersAndSpace(StAXStreamBuilder_processPrunableElement_Test.java:228)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       [...]
  JUnit Jupiter:StAXStreamBuilder_processPrunableElement_Test:testProcessPrunableElement_CompleteNestedStructure()
    MethodSource [className = 'org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test', methodName = 'testProcessPrunableElement_CompleteNestedStructure', methodParameterTypes = '']
    => org.mockito.exceptions.misusing.PotentialStubbingProblem: 
Strict stubbing argument mismatch. Please check:
 - this invocation of 'element' method:
    mockFactory.element(
    null,
    [Namespace: prefix "" is mapped to URI ""]
);
    -> at org.jdom2.input.StAXStreamBuilder.processElement(StAXStreamBuilder.java:505)
 - has following stubbing(s) with different arguments:
    1. mockFactory.element("", null);
      -> at org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.testProcessPrunableElement_CompleteNestedStructure(StAXStreamBuilder_processPrunableElement_Test.java:73)
Typically, stubbing argument mismatch indicates user mistake when writing tests.
Mockito fails early so that you can debug potential problem easily.
However, there are legit scenarios when this exception generates false negative signal:
  - stubbing the same method multiple times using 'given().will()' or 'when().then()' API
    Please use 'will().given()' or 'doReturn().when()' API for stubbing.
  - stubbed method is intentionally invoked with different arguments by code under test
    Please use default or 'silent' JUnit Rule (equivalent of Strictness.LENIENT).
For more information see javadoc for PotentialStubbingProblem class.
       org.jdom2.input.StAXStreamBuilder.processElement(StAXStreamBuilder.java:505)
       org.jdom2.input.StAXStreamBuilder.processPrunableElement(StAXStreamBuilder.java:304)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.invokeProcessPrunableElement(StAXStreamBuilder_processPrunableElement_Test.java:47)
       org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.testProcessPrunableElement_CompleteNestedStructure(StAXStreamBuilder_processPrunableElement_Test.java:75)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       [...]
  JUnit Jupiter:StAXStreamBuilder_processPrunableElement_Test:testProcessPrunableElement_NotAtStartElement()
    MethodSource [className = 'org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test', methodName = 'testProcessPrunableElement_NotAtStartElement', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Unexpected exception type thrown, expected: <org.jdom2.JDOMException> but was: <java.lang.reflect.InvocationTargetException>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:67)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3128)
       org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.testProcessPrunableElement_NotAtStartElement(StAXStreamBuilder_processPrunableElement_Test.java:89)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.invokeProcessPrunableElement(StAXStreamBuilder_processPrunableElement_Test.java:47)
       [...]
     Caused by: org.jdom2.JDOMException: JDOM requires that the XMLStreamReader is at the START_ELEMENT state when retrieving an Element Fragment.
       org.jdom2.input.StAXStreamBuilder.processPrunableElement(StAXStreamBuilder.java:299)
       [...]
  JUnit Jupiter:StAXStreamBuilder_processPrunableElement_Test:testProcessPrunableElement_UnsupportedEventType()
    MethodSource [className = 'org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test', methodName = 'testProcessPrunableElement_UnsupportedEventType', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Unexpected exception type thrown, expected: <org.jdom2.JDOMException> but was: <java.lang.reflect.InvocationTargetException>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:67)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3128)
       org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.testProcessPrunableElement_UnsupportedEventType(StAXStreamBuilder_processPrunableElement_Test.java:193)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.invokeProcessPrunableElement(StAXStreamBuilder_processPrunableElement_Test.java:47)
       [...]
     Caused by: org.jdom2.JDOMException: Unexpected XMLStream event 1
       org.jdom2.input.StAXStreamBuilder.processPrunableElement(StAXStreamBuilder.java:370)
       [...]
  JUnit Jupiter:StAXStreamBuilder_processPrunableElement_Test:testProcessPrunableElement_SelectivelyPrunedNestedStructure()
    MethodSource [className = 'org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test', methodName = 'testProcessPrunableElement_SelectivelyPrunedNestedStructure', methodParameterTypes = '']
    => org.mockito.exceptions.misusing.PotentialStubbingProblem: 
Strict stubbing argument mismatch. Please check:
 - this invocation of 'element' method:
    mockFactory.element(
    null,
    [Namespace: prefix "" is mapped to URI ""]
);
    -> at org.jdom2.input.StAXStreamBuilder.processElement(StAXStreamBuilder.java:505)
 - has following stubbing(s) with different arguments:
    1. mockFactory.element("", null);
      -> at org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.testProcessPrunableElement_SelectivelyPrunedNestedStructure(StAXStreamBuilder_processPrunableElement_Test.java:205)
Typically, stubbing argument mismatch indicates user mistake when writing tests.
Mockito fails early so that you can debug potential problem easily.
However, there are legit scenarios when this exception generates false negative signal:
  - stubbing the same method multiple times using 'given().will()' or 'when().then()' API
    Please use 'will().given()' or 'doReturn().when()' API for stubbing.
  - stubbed method is intentionally invoked with different arguments by code under test
    Please use default or 'silent' JUnit Rule (equivalent of Strictness.LENIENT).
For more information see javadoc for PotentialStubbingProblem class.
       org.jdom2.input.StAXStreamBuilder.processElement(StAXStreamBuilder.java:505)
       org.jdom2.input.StAXStreamBuilder.processPrunableElement(StAXStreamBuilder.java:304)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.invokeProcessPrunableElement(StAXStreamBuilder_processPrunableElement_Test.java:47)
       org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.testProcessPrunableElement_SelectivelyPrunedNestedStructure(StAXStreamBuilder_processPrunableElement_Test.java:207)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       [...]
  JUnit Jupiter:StAXStreamBuilder_processPrunableElement_Test:testProcessPrunableElement_EntityReferencePruned()
    MethodSource [className = 'org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test', methodName = 'testProcessPrunableElement_EntityReferencePruned', methodParameterTypes = '']
    => org.mockito.exceptions.misusing.PotentialStubbingProblem: 
Strict stubbing argument mismatch. Please check:
 - this invocation of 'pruneEntityRef' method:
    mockFilter.pruneEntityRef(1, null);
    -> at org.jdom2.input.StAXStreamBuilder.processPrunableElement(StAXStreamBuilder.java:357)
 - has following stubbing(s) with different arguments:
    1. mockFilter.pruneEntityRef(0, "");
      -> at org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.testProcessPrunableElement_EntityReferencePruned(StAXStreamBuilder_processPrunableElement_Test.java:138)
Typically, stubbing argument mismatch indicates user mistake when writing tests.
Mockito fails early so that you can debug potential problem easily.
However, there are legit scenarios when this exception generates false negative signal:
  - stubbing the same method multiple times using 'given().will()' or 'when().then()' API
    Please use 'will().given()' or 'doReturn().when()' API for stubbing.
  - stubbed method is intentionally invoked with different arguments by code under test
    Please use default or 'silent' JUnit Rule (equivalent of Strictness.LENIENT).
For more information see javadoc for PotentialStubbingProblem class.
       org.jdom2.input.StAXStreamBuilder.processPrunableElement(StAXStreamBuilder.java:357)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.invokeProcessPrunableElement(StAXStreamBuilder_processPrunableElement_Test.java:47)
       org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.testProcessPrunableElement_EntityReferencePruned(StAXStreamBuilder_processPrunableElement_Test.java:140)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:StAXStreamBuilder_processPrunableElement_Test:testProcessPrunableElement()
    MethodSource [className = 'org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test', methodName = 'testProcessPrunableElement', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "org.jdom2.Element.getParentElement()" because "current" is null
       org.jdom2.input.StAXStreamBuilder.processPrunableElement(StAXStreamBuilder.java:334)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.invokeProcessPrunableElement(StAXStreamBuilder_processPrunableElement_Test.java:47)
       org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.testProcessPrunableElement(StAXStreamBuilder_processPrunableElement_Test.java:57)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:StAXStreamBuilder_processPrunableElement_Test:testProcessPrunableElement_SelectivelyIncludedProcessingInstructions()
    MethodSource [className = 'org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test', methodName = 'testProcessPrunableElement_SelectivelyIncludedProcessingInstructions', methodParameterTypes = '']
    => org.mockito.exceptions.misusing.PotentialStubbingProblem: 
Strict stubbing argument mismatch. Please check:
 - this invocation of 'element' method:
    mockFactory.element(
    null,
    [Namespace: prefix "" is mapped to URI ""]
);
    -> at org.jdom2.input.StAXStreamBuilder.processElement(StAXStreamBuilder.java:505)
 - has following stubbing(s) with different arguments:
    1. mockFactory.element("", null);
      -> at org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.testProcessPrunableElement_SelectivelyIncludedProcessingInstructions(StAXStreamBuilder_processPrunableElement_Test.java:284)
Typically, stubbing argument mismatch indicates user mistake when writing tests.
Mockito fails early so that you can debug potential problem easily.
However, there are legit scenarios when this exception generates false negative signal:
  - stubbing the same method multiple times using 'given().will()' or 'when().then()' API
    Please use 'will().given()' or 'doReturn().when()' API for stubbing.
  - stubbed method is intentionally invoked with different arguments by code under test
    Please use default or 'silent' JUnit Rule (equivalent of Strictness.LENIENT).
For more information see javadoc for PotentialStubbingProblem class.
       org.jdom2.input.StAXStreamBuilder.processElement(StAXStreamBuilder.java:505)
       org.jdom2.input.StAXStreamBuilder.processPrunableElement(StAXStreamBuilder.java:304)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.invokeProcessPrunableElement(StAXStreamBuilder_processPrunableElement_Test.java:47)
       org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.testProcessPrunableElement_SelectivelyIncludedProcessingInstructions(StAXStreamBuilder_processPrunableElement_Test.java:287)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       [...]
  JUnit Jupiter:StAXStreamBuilder_processPrunableElement_Test:testProcessPrunableElement_ProcessingInstructionNotPruned()
    MethodSource [className = 'org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test', methodName = 'testProcessPrunableElement_ProcessingInstructionNotPruned', methodParameterTypes = '']
    => org.mockito.exceptions.misusing.PotentialStubbingProblem: 
Strict stubbing argument mismatch. Please check:
 - this invocation of 'element' method:
    mockFactory.element(
    null,
    [Namespace: prefix "" is mapped to URI ""]
);
    -> at org.jdom2.input.StAXStreamBuilder.processElement(StAXStreamBuilder.java:505)
 - has following stubbing(s) with different arguments:
    1. mockFactory.element("", null);
      -> at org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.testProcessPrunableElement_ProcessingInstructionNotPruned(StAXStreamBuilder_processPrunableElement_Test.java:157)
Typically, stubbing argument mismatch indicates user mistake when writing tests.
Mockito fails early so that you can debug potential problem easily.
However, there are legit scenarios when this exception generates false negative signal:
  - stubbing the same method multiple times using 'given().will()' or 'when().then()' API
    Please use 'will().given()' or 'doReturn().when()' API for stubbing.
  - stubbed method is intentionally invoked with different arguments by code under test
    Please use default or 'silent' JUnit Rule (equivalent of Strictness.LENIENT).
For more information see javadoc for PotentialStubbingProblem class.
       org.jdom2.input.StAXStreamBuilder.processElement(StAXStreamBuilder.java:505)
       org.jdom2.input.StAXStreamBuilder.processPrunableElement(StAXStreamBuilder.java:304)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.invokeProcessPrunableElement(StAXStreamBuilder_processPrunableElement_Test.java:47)
       org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.testProcessPrunableElement_ProcessingInstructionNotPruned(StAXStreamBuilder_processPrunableElement_Test.java:160)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       [...]
  JUnit Jupiter:StAXStreamBuilder_processPrunableElement_Test:testProcessPrunableElement_CommentPruned()
    MethodSource [className = 'org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test', methodName = 'testProcessPrunableElement_CommentPruned', methodParameterTypes = '']
    => org.mockito.exceptions.misusing.PotentialStubbingProblem: 
Strict stubbing argument mismatch. Please check:
 - this invocation of 'pruneComment' method:
    mockFilter.pruneComment(1, null);
    -> at org.jdom2.input.StAXStreamBuilder.processPrunableElement(StAXStreamBuilder.java:351)
 - has following stubbing(s) with different arguments:
    1. mockFilter.pruneComment(0, "");
      -> at org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.testProcessPrunableElement_CommentPruned(StAXStreamBuilder_processPrunableElement_Test.java:175)
Typically, stubbing argument mismatch indicates user mistake when writing tests.
Mockito fails early so that you can debug potential problem easily.
However, there are legit scenarios when this exception generates false negative signal:
  - stubbing the same method multiple times using 'given().will()' or 'when().then()' API
    Please use 'will().given()' or 'doReturn().when()' API for stubbing.
  - stubbed method is intentionally invoked with different arguments by code under test
    Please use default or 'silent' JUnit Rule (equivalent of Strictness.LENIENT).
For more information see javadoc for PotentialStubbingProblem class.
       org.jdom2.input.StAXStreamBuilder.processPrunableElement(StAXStreamBuilder.java:351)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.invokeProcessPrunableElement(StAXStreamBuilder_processPrunableElement_Test.java:47)
       org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.testProcessPrunableElement_CommentPruned(StAXStreamBuilder_processPrunableElement_Test.java:177)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:StAXStreamBuilder_processPrunableElement_Test:testProcessPrunableElement_EndDocumentEvent()
    MethodSource [className = 'org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test', methodName = 'testProcessPrunableElement_EndDocumentEvent', methodParameterTypes = '']
    => org.jdom2.JDOMException: Unexpected XMLStream event 1
       org.jdom2.input.StAXStreamBuilder.processPrunableElement(StAXStreamBuilder.java:370)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.invokeProcessPrunableElement(StAXStreamBuilder_processPrunableElement_Test.java:47)
       org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.testProcessPrunableElement_EndDocumentEvent(StAXStreamBuilder_processPrunableElement_Test.java:303)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:StAXStreamBuilder_processPrunableElement_Test:testProcessPrunableElement_CDATAAndCharacters()
    MethodSource [className = 'org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test', methodName = 'testProcessPrunableElement_CDATAAndCharacters', methodParameterTypes = '']
    => org.mockito.exceptions.misusing.PotentialStubbingProblem: 
Strict stubbing argument mismatch. Please check:
 - this invocation of 'element' method:
    mockFactory.element(
    null,
    [Namespace: prefix "" is mapped to URI ""]
);
    -> at org.jdom2.input.StAXStreamBuilder.processElement(StAXStreamBuilder.java:505)
 - has following stubbing(s) with different arguments:
    1. mockFactory.element("", null);
      -> at org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.testProcessPrunableElement_CDATAAndCharacters(StAXStreamBuilder_processPrunableElement_Test.java:119)
Typically, stubbing argument mismatch indicates user mistake when writing tests.
Mockito fails early so that you can debug potential problem easily.
However, there are legit scenarios when this exception generates false negative signal:
  - stubbing the same method multiple times using 'given().will()' or 'when().then()' API
    Please use 'will().given()' or 'doReturn().when()' API for stubbing.
  - stubbed method is intentionally invoked with different arguments by code under test
    Please use default or 'silent' JUnit Rule (equivalent of Strictness.LENIENT).
For more information see javadoc for PotentialStubbingProblem class.
       org.jdom2.input.StAXStreamBuilder.processElement(StAXStreamBuilder.java:505)
       org.jdom2.input.StAXStreamBuilder.processPrunableElement(StAXStreamBuilder.java:304)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.invokeProcessPrunableElement(StAXStreamBuilder_processPrunableElement_Test.java:47)
       org.jdom2.input.StAXStreamBuilder_processPrunableElement_Test.testProcessPrunableElement_CDATAAndCharacters(StAXStreamBuilder_processPrunableElement_Test.java:122)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       [...]

Test run finished after 593 ms
[         4 containers found      ]
[         0 containers skipped    ]
[         4 containers started    ]
[         0 containers aborted    ]
[         4 containers successful ]
[         0 containers failed     ]
[        16 tests found           ]
[         0 tests skipped         ]
[        16 tests started         ]
[         0 tests aborted         ]
[         0 tests successful      ]
[        16 tests failed          ]


```


And you can use the following related information to assist you in your analysis and correction:

api document of class org.jdom2.input.StAXStreamBuilder: 

 * Builds a JDOM Document from a StAX-based XMLStreamReader.
 * <p>
 * XMLStreamReaders are pre-configured and as a result JDOM is not able to
 * alter whether the input is validated, or whether the Stream has escaped
 * entities or not. These (and other) characteristics are configurable by
 * setting the correct features and properties on the XMLInputFactory when it
 * is used to create the XMLStreamReader.
 * <p>
 * Useful configuration to set, or know about is:
 * <ul>
 * <li>StAX streams seldom differentiate between Text and CDATA content. You
 * will likely want to configure your StAX factory (XMLInputFactory) with
 * <code>http://java.sun.com/xml/stream/properties/report-cdata-event</code>
 * for the default Java StAX implementation, or the equivalent property for your
 * StAX engine.
 * <li>The remaining XMLInputFactory settings are likely to work fine at their
 * default values.
 * <li>StAX is not likely to be your best option if you want a validating
 * parser, at least not with the default (built-in Java implementation in Java6 
 * which does not support it). Consider a SAX parser.
 * </ul>
 * <p>
 * From a JDOM perspective XMLStreamReaders are more efficient than 
 * XMLEventReaders. Where possible use an XMLStreamReader.
 * <p>
 * If you happen to be looking at the source code, pay careful attention to the
 * imports so you know what type of instance is being processed, whether it is
 * a StAX class, or a JDOM class, because there are name conflicts.
 * 
 * @author Rolf Lear
 *
 

return type: 
org.jdom2.Element

dependent classes: 
class `org.jdom2.JDOMFactory`:
	
class `org.jdom2.input.stax.StAXFilter`:
	
class `org.jdom2.input.StAXStreamBuilder`:
	related functions:
		method `private static final Content processFragment(JDOMFactory, XMLStreamReader)` returns `org.jdom2.Content`, related with `org.jdom2.input.StAXStreamBuilder.processPrunableElement(JDOMFactory, XMLStreamReader, int, StAXFilter)`, api document: 
	 * Create a Content from an XMLStreamReader
	 * The stream is advanced to the event after the current event (or to the
	 * event after the matching END_ELEMENT for an Element fragment).
	 * @param factory The {@link JDOMFactory} to use
	 * @param stream The XMLStreamReader to read from
	 * @return the parsed Document
	 * @throws JDOMException if there is any issue
	 * 				(XMLStreamExceptions are wrapped).
	 ;
		method `private List<Content> processFragments(JDOMFactory, XMLStreamReader, StAXFilter)` returns `java.util.List<org.jdom2.Content>`, related with `org.jdom2.input.StAXStreamBuilder.processPrunableElement(JDOMFactory, XMLStreamReader, int, StAXFilter)`;
		method `private static final Element processElementFragment(JDOMFactory, XMLStreamReader)` returns `org.jdom2.Element`, related with `org.jdom2.input.StAXStreamBuilder.processPrunableElement(JDOMFactory, XMLStreamReader, int, StAXFilter)`;
		method `private static final Document process(JDOMFactory, XMLStreamReader)` returns `org.jdom2.Document`, related with `org.jdom2.input.StAXStreamBuilder.processPrunableElement(JDOMFactory, XMLStreamReader, int, StAXFilter)`, api document: 
	 * Create a Document from an XMLStreamReader
	 * @param factory The {@link JDOMFactory} to use
	 * @param stream The XMLStreamReader to read from
	 * @return the parsed Document
	 * @throws JDOMException if there is any issue
	 * 				(XMLStreamExceptions are wrapped).
	 ;
		method `private static final Element processElement(JDOMFactory, XMLStreamReader)` returns `org.jdom2.Element`, related with `org.jdom2.input.StAXStreamBuilder.processPrunableElement(JDOMFactory, XMLStreamReader, int, StAXFilter)`;
class `org.jdom2.input.StAXEventBuilder`:
	related functions:
		method `private static final Document process(JDOMFactory, XMLEventReader)` returns `org.jdom2.Document`, related with `org.jdom2.input.StAXStreamBuilder.processPrunableElement(JDOMFactory, XMLStreamReader, int, StAXFilter)`, api document: 
	 * Create a Document from an XMLEventReader
	 * @param factory the {@link JDOMFactory} to use
	 * @param stream the XMLEventReader to read from
	 * @return the parsed Document
	 * @throws JDOMException if there is any issue
	 * 				(XMLStreamExceptions are wrapped).
	 ;
		method `private static final Element processElement(JDOMFactory, StartElement)` returns `org.jdom2.Element`, related with `org.jdom2.input.StAXStreamBuilder.processPrunableElement(JDOMFactory, XMLStreamReader, int, StAXFilter)`;
class `org.jdom2.input.sax.SAXHandler`:
	related functions:
		method `protected void flushCharacters(String)` returns `void`, related with `org.jdom2.input.StAXStreamBuilder.processPrunableElement(JDOMFactory, XMLStreamReader, int, StAXFilter)`, api document: 
	 * Flush the given string into the document. This is a protected method so
	 * subclassers can control text handling without knowledge of the internals
	 * of this class.
	 * 
	 * @param data
	 *        string to flush
	 * @throws SAXException
	 *         if the state of the handler does not allow this.
	 ;
class `org.jdom2.Namespace`:
	related functions:
		method `public static Namespace getNamespace(String)` returns `org.jdom2.Namespace`, related with `org.jdom2.input.StAXStreamBuilder.processPrunableElement(JDOMFactory, XMLStreamReader, int, StAXFilter)`, api document: 
	 * This will retrieve (if in existence) or create (if not) a 
	 * <code>Namespace</code> for the supplied URI, and make it usable 
	 * as a default namespace, as no prefix is supplied.
	 * This method is thread-safe.
	 *
	 * @param uri <code>String</code> URI of new <code>Namespace</code>.
	 * @return <code>Namespace</code> - ready to use namespace.
	 ;



Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 
