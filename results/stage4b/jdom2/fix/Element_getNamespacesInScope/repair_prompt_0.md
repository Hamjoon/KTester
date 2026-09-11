
You are an experienced Java developer tasked with fixing a piece of code that contains compilation errors. Your task is to resolve these issues after analyzing the code and its compilation feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any syntactical or logical errors.
2. Analyze the compilation feedback to understand the specific errors and their locations within the code.
3. Apply necessary corrections to the code based on the feedback, and output the compelete correct code.
The Java code you need to fix is provided below:
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
        // Should return a list with only the XML namespace
        List<Namespace> namespacesInScope = element.getNamespacesInScope();
        assertNotNull(namespacesInScope);
        assertTrue(namespacesInScope.contains(Namespace.XML_NAMESPACE), "Should contain the XML namespace");
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
        // Should return a list with the XML namespace and the default namespace
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
        element.setParentElement(parentElement);
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
```

Corresponding compilation feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
src/test/java/org/jdom2/Element_getNamespacesInScope_Test.java:90: error: cannot find symbol
        element.setParentElement(parentElement);
               ^
  symbol:   method setParentElement(Element)
  location: variable element of type Element
1 error

```

And you can use the following related information to assist you in your analysis and correction:
```
{'api document of class org.jdom2.Element': "\n * An XML element. Methods allow the user to get and manipulate its child\n * elements and content, directly access the element's textual content,\n * manipulate its attributes, and manage namespaces.\n * <p>\n * See {@link NamespaceAware} and {@link #getNamespacesInScope()} for more\n * details on what the Namespace scope is and how it is managed in JDOM and\n * specifically by this Element class.\n * \n * @see NamespaceAware\n * @see Content\n * \n * @author  Brett McLaughlin\n * @author  Jason Hunter\n * @author  Lucas Gonze\n * @author  Kevin Regan\n * @author  Dan Schaffer\n * @author  Yusuf Goolamabbas\n * @author  Kent C. Johnson\n * @author  Jools Enticknap\n * @author  Alex Rosen\n * @author  Bradley S. Huffman\n * @author  Victor Toni\n * @author  Rolf Lear\n * \n ", 'api document of method getNamespacesInScope()': "\n\t * Get the Namespaces that are in-scope on this Element. Element has the\n\t * most complex rules for the namespaces-in-scope.\n\t * <p>\n\t * The scope is built up from a number of sources following the rules of\n\t * XML namespace inheritence as follows:\n\t * <ul>\n\t * <li>The {@link Namespace#XML_NAMESPACE} is added\n\t * <li>The element's namespace is added (commonly \n\t * {@link Namespace#NO_NAMESPACE})\n\t * <li>All the attributes are inspected and their Namespaces are included\n\t * <li>All Namespaces declared on this Element using\n\t * {@link #addNamespaceDeclaration(Namespace)} are included.\n\t * <li>If the element has a parent then the parent's Namespace scope is\n\t * inspected, and any prefixes in the parent scope that are not yet bound\n\t * in this Element's scope are included.\n\t * <li>If the default Namespace (the no-prefix namespace) has not been\n\t * encountered for this Element then {@link Namespace#NO_NAMESPACE} is\n\t * included.\n\t * </ul> \n\t * The Element's Namespace scope consist of it's inherited Namespaces and\n\t * any modifications to that scope derived from the Element itself. If the\n\t * element is detached then it's inherited scope consists of just \n\t * If an element has no parent then \n\t * <p>\n\t * Note that the Element's Namespace will always be reported first.\n\t * <p>\n\t * <strong>Description copied from</strong>\n\t * {@link NamespaceAware#getNamespacesInScope()}:\n\t * <p>\n\t * {@inheritDoc}\n\t * \n\t * @see NamespaceAware\n\t ", 'dependent classes': 'class `org.jdom2.Namespace`:\n\tdependent fields:\n\t\torg.jdom2.Namespace org.jdom2.Namespace.NO_NAMESPACE;\n\t\torg.jdom2.Namespace org.jdom2.Namespace.XML_NAMESPACE;\n\t\nclass `org.jdom2.Element`:\n\tdependent fields:\n\t\tjava.util.List<org.jdom2.Namespace> org.jdom2.Element.additionalNamespaces;\n\t\torg.jdom2.AttributeList org.jdom2.Element.attributes;\n\trelated functions:\n\t\tmethod `public Element setNamespace(Namespace)` returns `org.jdom2.Element`, related with `org.jdom2.Element.getNamespacesInScope()`, api document: \n\t * Sets the element\'s {@link Namespace}. If the provided namespace is null,\n\t * the element will have no namespace.\n\t *\n\t * @param  namespace           the new namespace. A null implies Namespace.NO_NAMESPACE.\n\t * @return                     the target element\n\t * @throws IllegalAddException if there is a Namespace conflict\n\t ;\n\t\tmethod `public List<Namespace> getNamespacesIntroduced()` returns `java.util.List<org.jdom2.Namespace>`, related with `org.jdom2.Element.getNamespacesInScope()`;\n\t\tmethod `public Namespace getNamespace(String)` returns `org.jdom2.Namespace`, related with `org.jdom2.Element.getNamespacesInScope()`, api document: \n\t * Returns the {@link Namespace} corresponding to the given prefix in scope\n\t * for this element. This involves searching up the tree, so the results\n\t * depend on the current location of the element. Returns null if there is\n\t * no namespace in scope with the given prefix at this point in the\n\t * document.\n\t *\n\t * @param  prefix              namespace prefix to look up\n\t * @return                     the Namespace for this prefix at this\n\t *                             location, or null if none\n\t ;\n\t\tmethod `public List<Namespace> getNamespacesInherited()` returns `java.util.List<org.jdom2.Namespace>`, related with `org.jdom2.Element.getNamespacesInScope()`;\nclass `org.jdom2.Attribute`:\n\trelated functions:\n\t\tmethod `public List<Namespace> getNamespacesInScope()` returns `java.util.List<org.jdom2.Namespace>`, related with `org.jdom2.Element.getNamespacesInScope()`, api document: \n\t * Get the namespaces that are in-scope on this Attribute.\n\t * <p>\n\t * Attribute has peculiarities that affect the in-scope Namespaces because\n\t * there are conditions in which the Attribute\'s scope is different to its\n\t * parent Element\'s scope. Specifically, if the parent Element is in a\n\t * \'default\' Namespace that is not the empty Namespace (e.g.\n\t * xmlns="someurl") and this Attribute is also in the default Namespace (has\n\t * no prefix - but for Attributes that means the Namespace URL is ""), then\n\t * this Attribute has a different namespace scope from it\'s parent Element\n\t * because it does not include the \'someurl\' Namespace.\n\t * <p>\n\t * In the above conditions (no-prefix Attribute in an Element with a\n\t * non-empty no-prefix Namespace) this Attribute effectively re-binds the ""\n\t * prefix to the "" URL, thus the Attribute \'introduces\' the Namespace.\n\t * It follows then that the getNamespacesIntroduced() will return a list\n\t * with the single member {@link Namespace#NO_NAMESPACE}.\n\t * <p>\n\t * Note that the Attribute\'s Namespace will always be reported first.\n\t * <p>\n\t * <strong>Description copied from</strong>\n\t * {@link NamespaceAware#getNamespacesInScope()}:\n\t * <p>\n\t * {@inheritDoc}\n\t ;\n\t\tmethod `private static final List<Namespace> orderFirst(Namespace, List<Namespace>)` returns `java.util.List<org.jdom2.Namespace>`, related with `org.jdom2.Element.getNamespacesInScope()`;\nclass `org.jdom2.Document`:\n\trelated functions:\n\t\tmethod `public List<Namespace> getNamespacesInScope()` returns `java.util.List<org.jdom2.Namespace>`, related with `org.jdom2.Element.getNamespacesInScope()`, api document: \n\t * Get the Namespaces that are in-scope on this Document.\n\t * <p>\n\t * Document always has exactly two Namespaces in-scope:\n\t * {@link Namespace#NO_NAMESPACE} and {@link Namespace#XML_NAMESPACE}.\n\t * <p>\n\t * These namespaces are always introduced by the Document, and thus they are\n\t * both returned by {@link #getNamespacesIntroduced()}, and additionally\n\t * {@link #getNamespacesInherited()} will always be empty.\n\t * <p>\n\t * <strong>Description copied from</strong>\n\t * {@link NamespaceAware#getNamespacesInScope()}:\n\t * <p>\n\t * {@inheritDoc}\n\t ;\nclass `org.jdom2.output.support.AbstractStAXEventProcessor`:\n\trelated functions:\n\t\tmethod `protected void printElement(XMLEventConsumer, FormatStack, NamespaceStack, XMLEventFactory, Element)` returns `void`, related with `org.jdom2.Element.getNamespacesInScope()`, api document: \n\t * This will handle printing of an {@link Element}.\n\t * <p>\n\t * This method arranges for outputting the Element infrastructure including\n\t * Namespace Declarations and Attributes.\n\t * <p>\n\t * \n\t * @param out\n\t *        <code>XMLEventConsumer</code> to use.\n\t * @param fstack\n\t *        the FormatStack\n\t * @param nstack\n\t *        the NamespaceStack\n\t * @param eventfactory\n\t * \t\t  The XMLEventFactory for creating XMLEvents \n\t * @param element\n\t *        <code>Element</code> to write.\n\t * @throws XMLStreamException\n\t *         if the destination XMLEventConsumer fails\n\t ;'}
```

**Note**: If the method-to-test in the @Test function is a private method, you can use **reflection** to call it.

Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 


