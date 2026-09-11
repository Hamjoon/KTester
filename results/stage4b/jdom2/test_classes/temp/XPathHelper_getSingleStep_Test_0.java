package org.jdom2.xpath;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.jdom2.NamespaceAware;
import org.jdom2.Attribute;
import org.jdom2.Element;
import org.jdom2.Content;
import org.jdom2.Text;
import org.jdom2.Comment;
import org.jdom2.ProcessingInstruction;
import org.jdom2.Namespace;

@ExtendWith(MockitoExtension.class)
class XPathHelper_getSingleStep_Test {

    private NamespaceAware mockNamespaceAware;

    private StringBuilder buffer;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize necessary fields before each test
        mockNamespaceAware = mock(NamespaceAware.class);
        buffer = new StringBuilder();
    }

    @Test
    void testGetSingleStepWithText() {
        // Given
        Text mockText = mock(Text.class);
        Parent mockParent = mock(Parent.class);
        when(mockText.getParent()).thenReturn(mockParent);
        // Mock siblings
        when(mockParent.getContent(Filters.text())).thenReturn(new ArrayList<>());
        // When
        StringBuilder result = XPathHelper.getSingleStep(mockText, buffer);
        // Then
        assertNotNull(result);
        // Add assertions to verify the contents of the buffer
        assertTrue(result.toString().contains("text()"));
    }

    @Test
    void testGetSingleStepWithComment() {
        // Given
        Comment mockComment = mock(Comment.class);
        Parent mockParent = mock(Parent.class);
        when(mockComment.getParent()).thenReturn(mockParent);
        // Mock siblings
        when(mockParent.getContent(Filters.comment())).thenReturn(new ArrayList<>());
        // When
        StringBuilder result = XPathHelper.getSingleStep(mockComment, buffer);
        // Then
        assertNotNull(result);
        // Add assertions to verify the contents of the buffer
        assertTrue(result.toString().contains("comment()"));
    }

    @Test
    void testGetSingleStepWithProcessingInstruction() {
        // Given
        ProcessingInstruction mockProcessingInstruction = mock(ProcessingInstruction.class);
        Parent mockParent = mock(Parent.class);
        when(mockProcessingInstruction.getParent()).thenReturn(mockParent);
        // Mock siblings
        when(mockParent.getContent(Filters.processinginstruction())).thenReturn(new ArrayList<>());
        // When
        StringBuilder result = XPathHelper.getSingleStep(mockProcessingInstruction, buffer);
        // Then
        assertNotNull(result);
        // Add assertions to verify the contents of the buffer
        assertTrue(result.toString().contains("processing-instruction()"));
    }

    @Test
    void testGetSingleStepWithElementNoNamespace() {
        // Given
        Element mockElement = mock(Element.class);
        Parent mockParent = mock(Parent.class);
        when(mockElement.getNamespace()).thenReturn(Namespace.NO_NAMESPACE);
        when(mockElement.getParent()).thenReturn(mockParent);
        // Mock siblings
        when(mockParent.getChildren(mockElement.getName())).thenReturn(new ArrayList<>());
        // When
        StringBuilder result = XPathHelper.getSingleStep(mockElement, buffer);
        // Then
        assertNotNull(result);
        // Add assertions to verify the contents of the buffer
        assertTrue(result.toString().contains(mockElement.getName()));
    }

    @Test
    void testGetSingleStepWithElementWithNamespace() {
        // Given
        Element mockElement = mock(Element.class);
        Namespace mockNamespace = mock(Namespace.class);
        when(mockElement.getNamespace()).thenReturn(mockNamespace);
        Parent mockParent = mock(Parent.class);
        when(mockElement.getParent()).thenReturn(mockParent);
        // Mock siblings
        when(mockParent.getChildren(mockElement.getName(), mockNamespace)).thenReturn(new ArrayList<>());
        // When
        StringBuilder result = XPathHelper.getSingleStep(mockElement, buffer);
        // Then
        assertNotNull(result);
        // Add assertions to verify the contents of the buffer
        assertTrue(result.toString().contains(mockElement.getName()));
    }

    @Test
    void testGetSingleStepWithAttributeNoNamespace() {
        // Given
        Attribute mockAttribute = mock(Attribute.class);
        when(mockAttribute.getNamespace()).thenReturn(Namespace.NO_NAMESPACE);
        // When
        StringBuilder result = XPathHelper.getSingleStep(mockAttribute, buffer);
        // Then
        assertNotNull(result);
        // Add assertions to verify the contents of the buffer
        assertTrue(result.toString().contains("@" + mockAttribute.getName()));
    }

    @Test
    void testGetSingleStepWithAttributeWithNamespace() {
        // Given
        Attribute mockAttribute = mock(Attribute.class);
        Namespace mockNamespace = mock(Namespace.class);
        when(mockAttribute.getNamespace()).thenReturn(mockNamespace);
        // When
        StringBuilder result = XPathHelper.getSingleStep(mockAttribute, buffer);
        // Then
        assertNotNull(result);
        // Add assertions to verify the contents of the buffer
        assertTrue(result.toString().contains("@*[local-name() = '" + mockAttribute.getName()));
    }

    @Test
    void testGetSingleStepWithUnsupportedType() {
        // Given
        // Mock an unsupported type
        Object unsupportedType = new Object();
        // When
        StringBuilder result = XPathHelper.getSingleStep((NamespaceAware) unsupportedType, buffer);
        // Then
        assertNotNull(result);
        // Buffer should remain unchanged
        assertEquals(0, result.length());
    }

    @Test
    void testGetSingleStepWithNull() {
        // When
        StringBuilder result = XPathHelper.getSingleStep(null, buffer);
        // Then
        assertNotNull(result);
        // Buffer should remain unchanged
        assertEquals(0, result.length());
    }
}
