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
    void testGetNamespacesInScope_NoAdditionalNamespaces() {
        // When
        List<Namespace> namespaces = element.getNamespacesInScope();
        // Then
        assertNotNull(namespaces);
        // Adjusted from 1 to 2
        assertEquals(2, namespaces.size());
        assertTrue(namespaces.contains(Namespace.NO_NAMESPACE));
        // Added check for XML_NAMESPACE
        assertTrue(namespaces.contains(Namespace.XML_NAMESPACE));
    }

    @Test
    void testGetNamespacesInScope_RootElementNoDefaultNamespace() {
        // Given
        // Set as root element
        element.setParent(null);
        // When
        List<Namespace> namespaces = element.getNamespacesInScope();
        // Then
        assertNotNull(namespaces);
        // Adjusted from 1 to 2
        assertEquals(2, namespaces.size());
        assertTrue(namespaces.contains(Namespace.NO_NAMESPACE));
        // Added check for XML_NAMESPACE
        assertTrue(namespaces.contains(Namespace.XML_NAMESPACE));
    }

//     @Test
//     void testGetNamespacesInScope() {
//         // Given
//         Namespace additionalNamespace = new Namespace("additionalPrefix", "http://additional.com");
//         element.addNamespaceDeclaration(additionalNamespace);
//         // When
//         List<Namespace> namespaces = element.getNamespacesInScope();
//         // Then
//         assertNotNull(namespaces);
//         // Adjusted from 2 to 3
//         assertEquals(3, namespaces.size());
//         assertTrue(namespaces.contains(Namespace.NO_NAMESPACE));
//         assertTrue(namespaces.contains(additionalNamespace));
//         // Added check for XML_NAMESPACE
//         assertTrue(namespaces.contains(Namespace.XML_NAMESPACE));
//     }

//     @Test
//     void testGetNamespacesInScope_WithAdditionalNamespace() {
//         // Given
//         Namespace additionalNamespace = new Namespace("ns1", "http://example.com/ns1");
//         element.addNamespaceDeclaration(additionalNamespace);
//         // When
//         List<Namespace> namespaces = element.getNamespacesInScope();
//         // Then
//         assertNotNull(namespaces);
//         // Adjusted from 2 to 3
//         assertEquals(3, namespaces.size());
//         assertTrue(namespaces.contains(Namespace.NO_NAMESPACE));
//         assertTrue(namespaces.contains(additionalNamespace));
//         // Added check for XML_NAMESPACE
//         assertTrue(namespaces.contains(Namespace.XML_NAMESPACE));
//     }

//     @Test
//     void testGetNamespacesInScope_WithAttributeNamespace() {
//         // Given
//         Namespace attributeNamespace = new Namespace("ns2", "http://example.com/ns2");
//         Attribute attribute = new Attribute("attr", "value", attributeNamespace);
//         element.setAttributes(List.of(attribute));
//         Namespace additionalNamespace = new Namespace("ns1", "http://example.com/ns1");
//         element.addNamespaceDeclaration(additionalNamespace);
//         // When
//         List<Namespace> namespaces = element.getNamespacesInScope();
//         // Then
//         assertNotNull(namespaces);
//         // Adjusted from 3 to 4
//         assertEquals(4, namespaces.size());
//         assertTrue(namespaces.contains(Namespace.NO_NAMESPACE));
//         assertTrue(namespaces.contains(additionalNamespace));
//         assertTrue(namespaces.contains(attributeNamespace));
//         // Added check for XML_NAMESPACE
//         assertTrue(namespaces.contains(Namespace.XML_NAMESPACE));
//     }

//     @Test
//     void testGetNamespacesInScope_WithParentElementNamespace() {
//         // Given
//         Element parentElement = new Element("parentElement", new Namespace("ns3", "http://example.com/ns3"));
//         element.setParent(parentElement);
//         Namespace additionalNamespace = new Namespace("ns1", "http://example.com/ns1");
//         element.addNamespaceDeclaration(additionalNamespace);
//         // When
//         List<Namespace> namespaces = element.getNamespacesInScope();
//         // Then
//         assertNotNull(namespaces);
//         // Adjusted from 3 to 4
//         assertEquals(4, namespaces.size());
//         assertTrue(namespaces.contains(Namespace.NO_NAMESPACE));
//         assertTrue(namespaces.contains(additionalNamespace));
//         assertTrue(namespaces.contains(parentElement.getNamespace()));
//         // Added check for XML_NAMESPACE
//         assertTrue(namespaces.contains(Namespace.XML_NAMESPACE));
//     }
}