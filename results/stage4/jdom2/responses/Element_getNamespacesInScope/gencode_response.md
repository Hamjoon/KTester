```java
package org.jdom2;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class Element_getNamespacesInScope_Test{
    
    private Element element;
    private Namespace namespace;

    @BeforeAll
    static void setupBeforeAll() {
        // Any global setup can be done here
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the Namespace and Element before each test
        namespace = new Namespace("prefix", "http://example.com");
        element = new Element("testElement", namespace);
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup after each test if necessary
    }

    @AfterAll
    static void teardownAfterAll() {
        // Any global cleanup can be done here
    }

    @Test
    void testGetNamespacesInScope_NoAdditionalNamespaces() {
        // Test case for an Element without additional namespaces and no attributes,
        // ensuring it returns the XML and default namespaces.
        
        // When
        List<Namespace> namespaces = element.getNamespacesInScope();
        
        // Then
        assertNotNull(namespaces);
        assertEquals(2, namespaces.size());
        assertTrue(namespaces.contains(Namespace.XML_NAMESPACE));
        assertTrue(namespaces.contains(Namespace.NO_NAMESPACE));
    }

    @Test
    void testGetNamespacesInScope_WithAdditionalNamespace() {
        // Test case for an Element with one additional namespace,
        // ensuring it returns the XML namespace and the additional namespace.

        // Given
        Namespace additionalNamespace = new Namespace("ns1", "http://example.com/ns1");
        element.addNamespaceDeclaration(additionalNamespace);
        
        // When
        List<Namespace> namespaces = element.getNamespacesInScope();
        
        // Then
        assertNotNull(namespaces);
        assertEquals(3, namespaces.size());
        assertTrue(namespaces.contains(Namespace.XML_NAMESPACE));
        assertTrue(namespaces.contains(additionalNamespace));
    }

    @Test
    void testGetNamespacesInScope_WithAttributeNamespace() {
        // Test case for an Element with one attribute that has a namespace,
        // ensuring it returns the XML namespace, additional namespace, and attribute's namespace.

        // Given
        Namespace attributeNamespace = new Namespace("ns2", "http://example.com/ns2");
        Attribute attribute = new Attribute("attr", "value", attributeNamespace);
        element.setAttributes(List.of(attribute));
        Namespace additionalNamespace = new Namespace("ns1", "http://example.com/ns1");
        element.addNamespaceDeclaration(additionalNamespace);
        
        // When
        List<Namespace> namespaces = element.getNamespacesInScope();
        
        // Then
        assertNotNull(namespaces);
        assertEquals(4, namespaces.size());
        assertTrue(namespaces.contains(Namespace.XML_NAMESPACE));
        assertTrue(namespaces.contains(additionalNamespace));
        assertTrue(namespaces.contains(attributeNamespace));
    }

    @Test
    void testGetNamespacesInScope_WithParentElementNamespace() {
        // Test case for an Element that has a parent Element with an additional namespace,
        // ensuring it returns all namespaces from the child and parent.

        // Given
        Element parentElement = new Element("parentElement", new Namespace("ns3", "http://example.com/ns3"));
        element.setParentElement(parentElement);
        Namespace additionalNamespace = new Namespace("ns1", "http://example.com/ns1");
        element.addNamespaceDeclaration(additionalNamespace);
        
        // When
        List<Namespace> namespaces = element.getNamespacesInScope();
        
        // Then
        assertNotNull(namespaces);
        assertEquals(4, namespaces.size());
        assertTrue(namespaces.contains(Namespace.XML_NAMESPACE));
        assertTrue(namespaces.contains(additionalNamespace));
        assertTrue(namespaces.contains(parentElement.getNamespace()));
    }

    @Test
    void testGetNamespacesInScope_RootElementNoDefaultNamespace() {
        // Test case for a root Element with no default namespace,
        // ensuring it returns the XML namespace and NO_NAMESPACE.

        // Given
        element.setParentElement(null); // Set as root element
        
        // When
        List<Namespace> namespaces = element.getNamespacesInScope();
        
        // Then
        assertNotNull(namespaces);
        assertEquals(2, namespaces.size());
        assertTrue(namespaces.contains(Namespace.XML_NAMESPACE));
        assertTrue(namespaces.contains(Namespace.NO_NAMESPACE));
    }
}
```