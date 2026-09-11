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
import org.jdom2.filter.Filters;
import java.util.ArrayList;
import org.mockito.junit.jupiter.MockitoExtension;
import org.jdom2.Parent;
import java.lang.reflect.Method;

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

    private StringBuilder invokeGetSingleStep(NamespaceAware nsa) throws Exception {
        Method method = XPathHelper.class.getDeclaredMethod("getSingleStep", NamespaceAware.class, StringBuilder.class);
        method.setAccessible(true);
        return (StringBuilder) method.invoke(null, nsa, buffer);
    }

    @Test
    void testGetSingleStepWithText() {
        // Given
        Text mockText = mock(Text.class);
        Element mockParent = mock(Element.class);
        when(mockText.getParent()).thenReturn(mockParent);
        // Mock siblings
        when(mockParent.getContent(Filters.text())).thenReturn(new ArrayList<>());
        // When
        StringBuilder result = invokeGetSingleStep(mockText);
        // Then
        assertNotNull(result);
        // Add assertions to verify the contents of the buffer
        assertTrue(result.toString().contains("text()"));
    }

    @Test
    void testGetSingleStepWithComment() {
        // Given
        Comment mockComment = mock(Comment.class);
        Element mockParent = mock(Element.class);
        when(mockComment.getParent()).thenReturn(mockParent);
        // Mock siblings
        when(mockParent.getContent(Filters.comment())).thenReturn(new ArrayList<>());
        // When
        StringBuilder result = invokeGetSingleStep(mockComment);
        // Then
        assertNotNull(result);
        // Add assertions to verify the contents of the buffer
        assertTrue(result.toString().contains("comment()"));
    }

    @Test
    void testGetSingleStepWithProcessingInstruction() {
        // Given
        ProcessingInstruction mockProcessingInstruction = mock(ProcessingInstruction.class);
        Element mockParent = mock(Element.class);
        when(mockProcessingInstruction.getParent()).thenReturn(mockParent);
        // Mock siblings
        // Ensure this is the correct filter
        when(mockParent.getContent(Filters.processingInstruction())).thenReturn(new ArrayList<>());
        // When
        StringBuilder result = invokeGetSingleStep(mockProcessingInstruction);
        // Then
        assertNotNull(result);
        // Add assertions to verify the contents of the buffer
        assertTrue(result.toString().contains("processing-instruction()"));
    }

    @Test
    void testGetSingleStepWithElementNoNamespace() {
        // Given
        Element mockElement = mock(Element.class);
        Element mockParent = mock(Element.class);
        when(mockElement.getNamespace()).thenReturn(Namespace.NO_NAMESPACE);
        when(mockElement.getParent()).thenReturn(mockParent);
        // Mock siblings
        when(mockParent.getChildren(mockElement.getName())).thenReturn(new ArrayList<>());
        // When
        StringBuilder result = invokeGetSingleStep(mockElement);
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
        Element mockParent = mock(Element.class);
        when(mockElement.getParent()).thenReturn(mockParent);
        // Mock siblings
        when(mockParent.getChildren(mockElement.getName(), mockNamespace)).thenReturn(new ArrayList<>());
        // When
        StringBuilder result = invokeGetSingleStep(mockElement);
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
        StringBuilder result = invokeGetSingleStep(mockAttribute);
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
        StringBuilder result = invokeGetSingleStep(mockAttribute);
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
        StringBuilder result = invokeGetSingleStep((NamespaceAware) unsupportedType);
        // Then
        assertNotNull(result);
        // Buffer should remain unchanged
        assertEquals(0, result.length());
    }

    @Test
    void testGetSingleStepWithNull() {
        // When
        StringBuilder result = invokeGetSingleStep(null);
        // Then
        assertNotNull(result);
        // Buffer should remain unchanged
        assertEquals(0, result.length());
    }
}
