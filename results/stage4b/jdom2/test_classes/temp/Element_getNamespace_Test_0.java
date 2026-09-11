package org.jdom2;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class Element_getNamespace_Test {

    private Element element;

    private Namespace namespace;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the element and namespace before each test
        namespace = new Namespace("ns", "http://example.com/ns");
        element = new Element("exampleElement", namespace);
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup after each test, if needed
        element = null;
        namespace = null;
    }

    @Test
    void testGetNamespaceWithNullPrefix() {
        // Test with null prefix, expecting a return value of null.
        String prefix = null;
        Namespace result = element.getNamespace(prefix);
        assertNull(result, "Expected null for null prefix");
    }

    @Test
    void testGetNamespaceWithXmlPrefix() {
        // Test with prefix 'xml', expecting the XML namespace.
        String prefix = NS_PREFIX_XML;
        Namespace result = element.getNamespace(prefix);
        assertEquals(Namespace.XML_NAMESPACE, result, "Expected XML namespace for xml prefix");
    }

    @Test
    void testGetNamespaceWithValidPrefix() {
        // Given
        String prefix = "ns";
        // When
        Namespace result = element.getNamespace(prefix);
        // Then
        assertEquals(namespace, result, "Expected the correct namespace for the given prefix");
    }

    @Test
    void testGetNamespaceWithInvalidPrefix() {
        // Given
        String prefix = "invalidPrefix";
        // When
        Namespace result = element.getNamespace(prefix);
        // Then
        assertNull(result, "Expected null for an invalid prefix");
    }

    @Test
    void testGetNamespaceWithElementPrefix() {
        // Test with a prefix that matches the element's own namespace prefix.
        String prefix = "ns";
        // Set the element's namespace
        element.setNamespace(namespace);
        Namespace result = element.getNamespace(prefix);
        assertEquals(namespace, result, "Expected the correct namespace for the element's prefix");
    }

    @Test
    void testGetNamespaceWithAdditionalNamespace() {
        // Test with a prefix that matches one of the additional namespaces.
        Namespace additionalNamespace = new Namespace("additionalNamespacePrefix", "http://example.com/additional");
        element.addNamespaceDeclaration(additionalNamespace);
        String prefix = "additionalNamespacePrefix";
        Namespace result = element.getNamespace(prefix);
        assertEquals(additionalNamespace, result, "Expected the corresponding namespace for additional prefix");
    }

    @Test
    void testGetNamespaceWithAttributeNamespace() {
        // Test with a prefix that matches an attribute's namespace prefix.
        Attribute attribute = new Attribute("attr", "value", new Namespace("attributeNamespacePrefix", "http://example.com/attr"));
        element.setAttributes(Collections.singletonList(attribute));
        String prefix = "attributeNamespacePrefix";
        Namespace result = element.getNamespace(prefix);
        assertEquals(attribute.getNamespace(), result, "Expected the corresponding namespace for attribute prefix");
    }

    @Test
    void testGetNamespaceWithParentElementPrefix() {
        // Test with a prefix that matches a parent element's namespace.
        Element parentElement = new Element("parentElement", new Namespace("parentNamespace", "http://example.com/parent"));
        // Set the parent element
        element.setParent(parentElement);
        String prefix = "parentNamespace";
        Namespace result = element.getNamespace(prefix);
        assertEquals(parentElement.getNamespace(), result, "Expected the parent's namespace for the prefix");
    }

    @Test
    void testGetNamespaceWithNonExistentPrefix() {
        // Test with a prefix that does not match any namespace.
        String prefix = "nonExistentPrefix";
        Namespace result = element.getNamespace(prefix);
        assertNull(result, "Expected null for a non-existent prefix");
    }

    @Test
    void testGetNamespaceWithEmptyPrefix() {
        // Test with an empty string as prefix, expecting a return value of null.
        String prefix = "";
        Namespace result = element.getNamespace(prefix);
        assertNull(result, "Expected null for an empty prefix");
    }

    @Test
    void testGetNamespaceWithDefaultNamespace() {
        // Test with a prefix that matches the default namespace.
        Namespace defaultNamespace = new Namespace("defaultNamespace", "http://example.com/default");
        element.addNamespaceDeclaration(defaultNamespace);
        String prefix = "defaultNamespace";
        Namespace result = element.getNamespace(prefix);
        assertEquals(defaultNamespace, result, "Expected the corresponding namespace for default prefix");
    }

    @Test
    void testGetNamespaceWithAnotherAdditionalNamespace() {
        // Test with a prefix that matches another additional namespace.
        Namespace anotherAdditionalNamespace = new Namespace("anotherAdditionalNamespacePrefix", "http://example.com/another");
        element.addNamespaceDeclaration(anotherAdditionalNamespace);
        String prefix = "anotherAdditionalNamespacePrefix";
        Namespace result = element.getNamespace(prefix);
        assertEquals(anotherAdditionalNamespace, result, "Expected the corresponding namespace for another additional prefix");
    }

    @Test
    void testGetNamespaceWithNonMatchingParentPrefix() {
        // Test with a prefix that does not match any namespace in the current or parent element.
        Element parentElement = new Element("parentElement", new Namespace("parentNamespace", "http://example.com/parent"));
        // Set the parent element
        element.setParent(parentElement);
        String prefix = "nonMatchingParentPrefix";
        Namespace result = element.getNamespace(prefix);
        assertNull(result, "Expected null for a non-matching parent prefix");
    }

    @Test
    void testGetNamespaceWithNotDeclaredPrefix() {
        // Test with a prefix that is not declared at all.
        String prefix = "notDeclaredPrefix";
        Namespace result = element.getNamespace(prefix);
        assertNull(result, "Expected null for a not declared prefix");
    }
}
