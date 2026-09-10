package org.jdom2;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class Element_getNamespacesInScope_Test {

    private Element element;

    private Namespace namespace;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the Element before each test
        element = new Element("testElement", Namespace.NO_NAMESPACE);
    }

    @Test
    void testGetNamespacesInScope() {
        // Given
        Namespace additionalNamespace = new Namespace("additionalPrefix", "http://additional.com");
        element.addNamespaceDeclaration(additionalNamespace);
        // When
        List<Namespace> namespaces = element.getNamespacesInScope();
        // Then
        assertNotNull(namespaces);
        assertEquals(2, namespaces.size());
        assertTrue(namespaces.contains(Namespace.NO_NAMESPACE));
        assertTrue(namespaces.contains(additionalNamespace));
    }

    @Test
    void testGetNamespacesInScope_NoAdditionalNamespaces() {
        // When
        List<Namespace> namespaces = element.getNamespacesInScope();
        // Then
        assertNotNull(namespaces);
        assertEquals(1, namespaces.size());
        assertTrue(namespaces.contains(Namespace.NO_NAMESPACE));
    }

    @Test
    void testGetNamespacesInScope_WithAdditionalNamespace() {
        // Given
        Namespace additionalNamespace = new Namespace("ns1", "http://example.com/ns1");
        element.addNamespaceDeclaration(additionalNamespace);
        // When
        List<Namespace> namespaces = element.getNamespacesInScope();
        // Then
        assertNotNull(namespaces);
        assertEquals(2, namespaces.size());
        assertTrue(namespaces.contains(Namespace.NO_NAMESPACE));
        assertTrue(namespaces.contains(additionalNamespace));
    }

    @Test
    void testGetNamespacesInScope_WithAttributeNamespace() {
        // Given
        Namespace attributeNamespace = new Namespace("ns2", "http://example.com/ns2");
        Attribute attribute = new Attribute("attr", "value", attributeNamespace);
        // Assuming this method exists
        element.setAttributes(List.of(attribute));
        Namespace additionalNamespace = new Namespace("ns1", "http://example.com/ns1");
        element.addNamespaceDeclaration(additionalNamespace);
        // When
        List<Namespace> namespaces = element.getNamespacesInScope();
        // Then
        assertNotNull(namespaces);
        assertEquals(3, namespaces.size());
        assertTrue(namespaces.contains(Namespace.NO_NAMESPACE));
        assertTrue(namespaces.contains(additionalNamespace));
        assertTrue(namespaces.contains(attributeNamespace));
    }

    @Test
    void testGetNamespacesInScope_WithParentElementNamespace() {
        // Given
        Element parentElement = new Element("parentElement", new Namespace("ns3", "http://example.com/ns3"));
        // Assuming we can set the parent in a different way
        element.setParent(parentElement);
        Namespace additionalNamespace = new Namespace("ns1", "http://example.com/ns1");
        element.addNamespaceDeclaration(additionalNamespace);
        // When
        List<Namespace> namespaces = element.getNamespacesInScope();
        // Then
        assertNotNull(namespaces);
        assertEquals(3, namespaces.size());
        assertTrue(namespaces.contains(Namespace.NO_NAMESPACE));
        assertTrue(namespaces.contains(additionalNamespace));
        assertTrue(namespaces.contains(parentElement.getNamespace()));
    }

    @Test
    void testGetNamespacesInScope_RootElementNoDefaultNamespace() {
        // Given
        // Set as root element
        // Assuming we can set the parent to null in a different way
        element.setParent(null);
        // When
        List<Namespace> namespaces = element.getNamespacesInScope();
        // Then
        assertNotNull(namespaces);
        assertEquals(1, namespaces.size());
        assertTrue(namespaces.contains(Namespace.NO_NAMESPACE));
    }
}
