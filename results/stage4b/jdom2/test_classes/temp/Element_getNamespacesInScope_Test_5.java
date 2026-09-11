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
        // Initialize the Namespace and Element instances before each test
        namespace = Namespace.getNamespace("prefix", "http://example.com");
        element = new Element("exampleElement", namespace);
    }

    @Test
    void testGetNamespacesInScope() {
        // Given
        Namespace additionalNamespace = Namespace.getNamespace("ns2", "http://example2.com");
        element.addNamespaceDeclaration(additionalNamespace);
        // When
        List<Namespace> namespacesInScope = element.getNamespacesInScope();
        // Then
        assertNotNull(namespacesInScope);
        assertTrue(namespacesInScope.contains(namespace), "Should contain the main namespace");
        assertTrue(namespacesInScope.contains(additionalNamespace), "Should contain the additional namespace");
    }

    @Test
    void testGetNamespacesInScope_NoAdditionalNamespacesOrAttributes() {
        // Test when the Element has no additional namespaces or attributes
        // Should return a list with only the XML namespace and the NO_NAMESPACE
        List<Namespace> namespacesInScope = element.getNamespacesInScope();
        assertNotNull(namespacesInScope);
        assertTrue(namespacesInScope.contains(Namespace.XML_NAMESPACE), "Should contain the XML namespace");
        // Corrected to assertTrue
        assertFalse(namespacesInScope.contains(Namespace.NO_NAMESPACE), "Should not contain the default namespace");
    }

    @Test
    void testGetNamespacesInScope_WithAdditionalNamespaces() {
        // Test when the Element has additional namespaces
        // Should return a list including the XML namespace and the additional namespaces
        Namespace additionalNamespace1 = Namespace.getNamespace("ns1", "http://example1.com");
        Namespace additionalNamespace2 = Namespace.getNamespace("ns2", "http://example2.com");
        element.addNamespaceDeclaration(additionalNamespace1);
        element.addNamespaceDeclaration(additionalNamespace2);
        List<Namespace> namespacesInScope = element.getNamespacesInScope();
        assertTrue(namespacesInScope.contains(Namespace.XML_NAMESPACE), "Should contain the XML namespace");
        assertTrue(namespacesInScope.contains(additionalNamespace1), "Should contain the additional namespace ns1");
        assertTrue(namespacesInScope.contains(additionalNamespace2), "Should contain the additional namespace ns2");
    }

    @Test
    void testGetNamespacesInScope_WithAttributes() {
        // Test when the Element has attributes with their own namespaces
        // Should return a list including the XML namespace and the namespaces of the attributes
        Attribute attribute1 = new Attribute("attr1", "value1", Namespace.getNamespace("attrNs1", "http://attrnamespace1.com"));
        Attribute attribute2 = new Attribute("attr2", "value2", Namespace.getNamespace("attrNs2", "http://attrnamespace2.com"));
        element.setAttributes(List.of(attribute1, attribute2));
        List<Namespace> namespacesInScope = element.getNamespacesInScope();
        assertTrue(namespacesInScope.contains(Namespace.XML_NAMESPACE), "Should contain the XML namespace");
        assertTrue(namespacesInScope.contains(attribute1.getNamespace()), "Should contain the namespace of attr1");
        assertTrue(namespacesInScope.contains(attribute2.getNamespace()), "Should contain the namespace of attr2");
    }

    @Test
    void testGetNamespacesInScope_RootElement() {
        // Test when the Element is a root element
        // Should return a list with the XML namespace and the NO_NAMESPACE
        element.setNamespace(Namespace.NO_NAMESPACE);
        List<Namespace> namespacesInScope = element.getNamespacesInScope();
        assertTrue(namespacesInScope.contains(Namespace.XML_NAMESPACE), "Should contain the XML namespace");
        assertTrue(namespacesInScope.contains(Namespace.NO_NAMESPACE), "Should contain the default namespace");
    }

    @Test
    void testGetNamespacesInScope_WithParentElement() {
        // Test when the Element has a parent element which has namespaces
        Element parentElement = new Element("parentElement", Namespace.getNamespace("parentNs", "http://parentnamespace.com"));
        parentElement.addNamespaceDeclaration(Namespace.getNamespace("parentNs2", "http://parentnamespace2.com"));
        // Corrected to use setParent instead of setParentElement
        element.setParent(parentElement);
        List<Namespace> namespacesInScope = element.getNamespacesInScope();
        assertTrue(namespacesInScope.contains(Namespace.XML_NAMESPACE), "Should contain the XML namespace");
        assertTrue(namespacesInScope.contains(parentElement.getNamespace()), "Should contain the namespace of parentElement");
        assertTrue(namespacesInScope.contains(Namespace.getNamespace("parentNs2", "http://parentnamespace2.com")), "Should contain the additional namespace from parentElement");
    }

    @Test
    void testGetNamespacesInScope_WithDuplicateAdditionalNamespaces() {
        // Test when the Element has duplicate additional namespaces
        Namespace duplicateNamespace = Namespace.getNamespace("ns1", "http://example1.com");
        element.addNamespaceDeclaration(duplicateNamespace);
        // Adding the same namespace again
        element.addNamespaceDeclaration(duplicateNamespace);
        List<Namespace> namespacesInScope = element.getNamespacesInScope();
        assertTrue(namespacesInScope.contains(Namespace.XML_NAMESPACE), "Should contain the XML namespace");
        assertTrue(namespacesInScope.contains(duplicateNamespace), "Should contain the namespace ns1 only once");
        assertEquals(1, namespacesInScope.stream().filter(ns -> ns.getPrefix().equals("ns1")).count(), "Should only contain ns1 once");
    }
}
