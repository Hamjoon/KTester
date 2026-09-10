
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
        // Initialize the Namespace and Element before each test
        namespace = new Namespace("prefix", "http://example.com");
        element = new Element("testElement", namespace);
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
        assertTrue(namespaces.contains(namespace));
        assertTrue(namespaces.contains(additionalNamespace));
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
        // Set as root element
        element.setParentElement(null);
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

Corresponding compilation feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
src/test/java/org/jdom2/Element_getNamespacesInScope_Test.java:21: error: Namespace(String,String) has private access in Namespace
        namespace = new Namespace("prefix", "http://example.com");
                    ^
src/test/java/org/jdom2/Element_getNamespacesInScope_Test.java:28: error: Namespace(String,String) has private access in Namespace
        Namespace additionalNamespace = new Namespace("additionalPrefix", "http://additional.com");
                                        ^
src/test/java/org/jdom2/Element_getNamespacesInScope_Test.java:57: error: Namespace(String,String) has private access in Namespace
        Namespace additionalNamespace = new Namespace("ns1", "http://example.com/ns1");
                                        ^
src/test/java/org/jdom2/Element_getNamespacesInScope_Test.java:73: error: Namespace(String,String) has private access in Namespace
        Namespace attributeNamespace = new Namespace("ns2", "http://example.com/ns2");
                                       ^
src/test/java/org/jdom2/Element_getNamespacesInScope_Test.java:76: error: Namespace(String,String) has private access in Namespace
        Namespace additionalNamespace = new Namespace("ns1", "http://example.com/ns1");
                                        ^
src/test/java/org/jdom2/Element_getNamespacesInScope_Test.java:93: error: Namespace(String,String) has private access in Namespace
        Element parentElement = new Element("parentElement", new Namespace("ns3", "http://example.com/ns3"));
                                                             ^
src/test/java/org/jdom2/Element_getNamespacesInScope_Test.java:94: error: cannot find symbol
        element.setParentElement(parentElement);
               ^
  symbol:   method setParentElement(Element)
  location: variable element of type Element
src/test/java/org/jdom2/Element_getNamespacesInScope_Test.java:95: error: Namespace(String,String) has private access in Namespace
        Namespace additionalNamespace = new Namespace("ns1", "http://example.com/ns1");
                                        ^
src/test/java/org/jdom2/Element_getNamespacesInScope_Test.java:113: error: cannot find symbol
        element.setParentElement(null);
               ^
  symbol:   method setParentElement(<null>)
  location: variable element of type Element
9 errors

```

And you can use the following related information to assist you in your analysis and correction:
```
{'api document of class org.jdom2.Element': "\n * An XML element. Methods allow the user to get and manipulate its child\n * elements and content, directly access the element's textual content,\n * manipulate its attributes, and manage namespaces.\n * <p>\n * See {@link NamespaceAware} and {@link #getNamespacesInScope()} for more\n * details on what the Namespace scope is and how it is managed in JDOM and\n * specifically by this Element class.\n * \n * @see NamespaceAware\n * @see Content\n * \n * @author  Brett McLaughlin\n * @author  Jason Hunter\n * @author  Lucas Gonze\n * @author  Kevin Regan\n * @author  Dan Schaffer\n * @author  Yusuf Goolamabbas\n * @author  Kent C. Johnson\n * @author  Jools Enticknap\n * @author  Alex Rosen\n * @author  Bradley S. Huffman\n * @author  Victor Toni\n * @author  Rolf Lear\n * \n ", 'api document of method getNamespacesInScope()': "\n\t * Get the Namespaces that are in-scope on this Element. Element has the\n\t * most complex rules for the namespaces-in-scope.\n\t * <p>\n\t * The scope is built up from a number of sources following the rules of\n\t * XML namespace inheritence as follows:\n\t * <ul>\n\t * <li>The {@link Namespace#XML_NAMESPACE} is added\n\t * <li>The element's namespace is added (commonly \n\t * {@link Namespace#NO_NAMESPACE})\n\t * <li>All the attributes are inspected and their Namespaces are included\n\t * <li>All Namespaces declared on this Element using\n\t * {@link #addNamespaceDeclaration(Namespace)} are included.\n\t * <li>If the element has a parent then the parent's Namespace scope is\n\t * inspected, and any prefixes in the parent scope that are not yet bound\n\t * in this Element's scope are included.\n\t * <li>If the default Namespace (the no-prefix namespace) has not been\n\t * encountered for this Element then {@link Namespace#NO_NAMESPACE} is\n\t * included.\n\t * </ul> \n\t * The Element's Namespace scope consist of it's inherited Namespaces and\n\t * any modifications to that scope derived from the Element itself. If the\n\t * element is detached then it's inherited scope consists of just \n\t * If an element has no parent then \n\t * <p>\n\t * Note that the Element's Namespace will always be reported first.\n\t * <p>\n\t * <strong>Description copied from</strong>\n\t * {@link NamespaceAware#getNamespacesInScope()}:\n\t * <p>\n\t * {@inheritDoc}\n\t * \n\t * @see NamespaceAware\n\t ", 'dependent classes': 'class `org.jdom2.Namespace`:\n\tdependent fields:\n\t\torg.jdom2.Namespace org.jdom2.Namespace.XML_NAMESPACE;\n\t\torg.jdom2.Namespace org.jdom2.Namespace.NO_NAMESPACE;\n\t\nclass `org.jdom2.Element`:\n\tdependent fields:\n\t\tjava.util.List<org.jdom2.Namespace> org.jdom2.Element.additionalNamespaces;\n\t\torg.jdom2.AttributeList org.jdom2.Element.attributes;\n\trelated functions:\n\t\tmethod `public List<Namespace> getNamespacesInherited()` returns `java.util.List<org.jdom2.Namespace>`, related with `org.jdom2.Element.getNamespacesInScope()`;\n\t\tmethod `public List<Namespace> getNamespacesIntroduced()` returns `java.util.List<org.jdom2.Namespace>`, related with `org.jdom2.Element.getNamespacesInScope()`;\n\t\tmethod `public Namespace getNamespace(String)` returns `org.jdom2.Namespace`, related with `org.jdom2.Element.getNamespacesInScope()`, api document: \n\t * Returns the {@link Namespace} corresponding to the given prefix in scope\n\t * for this element. This involves searching up the tree, so the results\n\t * depend on the current location of the element. Returns null if there is\n\t * no namespace in scope with the given prefix at this point in the\n\t * document.\n\t *\n\t * @param  prefix              namespace prefix to look up\n\t * @return                     the Namespace for this prefix at this\n\t *                             location, or null if none\n\t ;\n\t\tmethod `public Element setNamespace(Namespace)` returns `org.jdom2.Element`, related with `org.jdom2.Element.getNamespacesInScope()`, api document: \n\t * Sets the element\'s {@link Namespace}. If the provided namespace is null,\n\t * the element will have no namespace.\n\t *\n\t * @param  namespace           the new namespace. A null implies Namespace.NO_NAMESPACE.\n\t * @return                     the target element\n\t * @throws IllegalAddException if there is a Namespace conflict\n\t ;\nclass `org.jdom2.Attribute`:\n\trelated functions:\n\t\tmethod `public List<Namespace> getNamespacesInScope()` returns `java.util.List<org.jdom2.Namespace>`, related with `org.jdom2.Element.getNamespacesInScope()`, api document: \n\t * Get the namespaces that are in-scope on this Attribute.\n\t * <p>\n\t * Attribute has peculiarities that affect the in-scope Namespaces because\n\t * there are conditions in which the Attribute\'s scope is different to its\n\t * parent Element\'s scope. Specifically, if the parent Element is in a\n\t * \'default\' Namespace that is not the empty Namespace (e.g.\n\t * xmlns="someurl") and this Attribute is also in the default Namespace (has\n\t * no prefix - but for Attributes that means the Namespace URL is ""), then\n\t * this Attribute has a different namespace scope from it\'s parent Element\n\t * because it does not include the \'someurl\' Namespace.\n\t * <p>\n\t * In the above conditions (no-prefix Attribute in an Element with a\n\t * non-empty no-prefix Namespace) this Attribute effectively re-binds the ""\n\t * prefix to the "" URL, thus the Attribute \'introduces\' the Namespace.\n\t * It follows then that the getNamespacesIntroduced() will return a list\n\t * with the single member {@link Namespace#NO_NAMESPACE}.\n\t * <p>\n\t * Note that the Attribute\'s Namespace will always be reported first.\n\t * <p>\n\t * <strong>Description copied from</strong>\n\t * {@link NamespaceAware#getNamespacesInScope()}:\n\t * <p>\n\t * {@inheritDoc}\n\t ;\n\t\tmethod `private static final List<Namespace> orderFirst(Namespace, List<Namespace>)` returns `java.util.List<org.jdom2.Namespace>`, related with `org.jdom2.Element.getNamespacesInScope()`;\nclass `org.jdom2.Document`:\n\trelated functions:\n\t\tmethod `public List<Namespace> getNamespacesInScope()` returns `java.util.List<org.jdom2.Namespace>`, related with `org.jdom2.Element.getNamespacesInScope()`, api document: \n\t * Get the Namespaces that are in-scope on this Document.\n\t * <p>\n\t * Document always has exactly two Namespaces in-scope:\n\t * {@link Namespace#NO_NAMESPACE} and {@link Namespace#XML_NAMESPACE}.\n\t * <p>\n\t * These namespaces are always introduced by the Document, and thus they are\n\t * both returned by {@link #getNamespacesIntroduced()}, and additionally\n\t * {@link #getNamespacesInherited()} will always be empty.\n\t * <p>\n\t * <strong>Description copied from</strong>\n\t * {@link NamespaceAware#getNamespacesInScope()}:\n\t * <p>\n\t * {@inheritDoc}\n\t ;\nclass `org.jdom2.output.support.AbstractStAXEventProcessor`:\n\trelated functions:\n\t\tmethod `protected void printElement(XMLEventConsumer, FormatStack, NamespaceStack, XMLEventFactory, Element)` returns `void`, related with `org.jdom2.Element.getNamespacesInScope()`, api document: \n\t * This will handle printing of an {@link Element}.\n\t * <p>\n\t * This method arranges for outputting the Element infrastructure including\n\t * Namespace Declarations and Attributes.\n\t * <p>\n\t * \n\t * @param out\n\t *        <code>XMLEventConsumer</code> to use.\n\t * @param fstack\n\t *        the FormatStack\n\t * @param nstack\n\t *        the NamespaceStack\n\t * @param eventfactory\n\t * \t\t  The XMLEventFactory for creating XMLEvents \n\t * @param element\n\t *        <code>Element</code> to write.\n\t * @throws XMLStreamException\n\t *         if the destination XMLEventConsumer fails\n\t ;'}
```

**Note**: If the method-to-test in the @Test function is a private method, you can use **reflection** to call it.

Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 


