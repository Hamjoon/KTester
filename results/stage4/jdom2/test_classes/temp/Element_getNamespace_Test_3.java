package org.jdom2;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class Element_getNamespace_Test {

    private Element element;

    private Namespace namespace;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the Element and Namespace objects before each test
        namespace = Namespace.getNamespace("prefix", "http://example.com");
        element = new Element("testElement", namespace);
    }

    @Test
    void testGetNamespaceWithNullPrefix() {
        // Given
        String prefix = null;
        // When
        Namespace result = element.getNamespace(prefix);
        // Then
        assertNull(result, "Expected null for null prefix");
    }

    @Test
    void testGetNamespaceWithXmlPrefix() {
        // Given
        String prefix = JDOMConstants.NS_PREFIX_XML;
        // When
        Namespace result = element.getNamespace(prefix);
        // Then
        assertEquals(Namespace.XML_NAMESPACE, result, "Expected XML Namespace for xml prefix");
    }

    @Test
    void testGetNamespaceWithCustomPrefix() {
        // Given
        String prefix = "prefix";
        // When
        Namespace result = element.getNamespace(prefix);
        // Then
        assertEquals(namespace, result, "Expected the custom namespace for the given prefix");
    }

    @Test
    void testGetNamespaceWithAdditionalNamespace() {
        // Given
        Namespace additionalNamespace = Namespace.getNamespace("additionalPrefix", "http://example.org");
        element.additionalNamespaces = new ArrayList<>();
        element.additionalNamespaces.add(additionalNamespace);
        String prefix = "additionalPrefix";
        // When
        Namespace result = element.getNamespace(prefix);
        // Then
        assertEquals(additionalNamespace, result, "Expected the additional namespace for the given prefix");
    }

    @Test
    void testGetNamespaceWithNonExistentPrefix() {
        // Given
        String prefix = "nonExistentPrefix";
        // When
        Namespace result = element.getNamespace(prefix);
        // Then
        assertNull(result, "Expected null for a non-existent prefix");
    }

    @Test
    void testGetNamespaceWithParentPrefix() throws Exception {
        // Given
        Element parentElement = new Element("parentElement", Namespace.getNamespace("parentPrefix", "http://parent.example.com"));
        // Setting the parent directly via reflection, assuming the method exists
        Method setParentMethod = Element.class.getDeclaredMethod("setParent", Element.class);
        setParentMethod.setAccessible(true);
        setParentMethod.invoke(element, parentElement);
        String prefix = "parentPrefix";
        // When
        Namespace result = element.getNamespace(prefix);
        // Then
        assertEquals(parentElement.getNamespace(), result, "Expected the namespace from the parent element for the given prefix");
    }

    @Test
    void testGetNamespaceWithDefaultPrefix() {
        // Given
        String prefix = JDOMConstants.NS_PREFIX_DEFAULT;
        // When
        Namespace result = element.getNamespace(prefix);
        // Then
        assertEquals(Namespace.NO_NAMESPACE, result, "Expected the default namespace for the default prefix");
    }

    @Test
    void testGetNamespaceWithEmptyPrefix() {
        // Given
        String prefix = "";
        // When
        Namespace result = element.getNamespace(prefix);
        // Then
        assertNull(result, "Expected null for an empty string prefix");
    }

    @Test
    void testGetNamespaceWithNullStringPrefix() {
        // Given
        String prefix = "null";
        // When
        Namespace result = element.getNamespace(prefix);
        // Then
        assertNull(result, "Expected null for a string 'null' prefix");
    }

    @Test
    void testGetNamespaceWithMixedPrefix() {
        // Given
        Namespace mixedNamespace = Namespace.getNamespace("mixedPrefix", "http://mixed.example.com");
        element.additionalNamespaces = new ArrayList<>();
        element.additionalNamespaces.add(mixedNamespace);
        String prefix = "mixedPrefix";
        // When
        Namespace result = element.getNamespace(prefix);
        // Then
        assertEquals(mixedNamespace, result, "Expected the namespace from mixed content for the given prefix");
    }
}
