
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
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import static org.jdom2.JDOMConstants.NS_PREFIX_XML;
import static org.jdom2.JDOMConstants.NS_PREFIX_DEFAULT;

@ExtendWith(MockitoExtension.class)
class Element_getNamespace_Test {

    private Element element;

    private Namespace namespace;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the Element and Namespace objects before each test
        namespace = new Namespace("prefix", "http://example.com");
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
        String prefix = NS_PREFIX_XML;
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
        Namespace additionalNamespace = new Namespace("additionalPrefix", "http://example.org");
        element.additionalNamespaces = new ArrayList<>();
        element.additionalNamespaces.add(additionalNamespace);
        String prefix = "additionalPrefix";
        // When
        Namespace result = element.getNamespace(prefix);
        // Then
        assertEquals(additionalNamespace, result, "Expected the additional namespace for the given prefix");
    }

    @Test
    void testGetNamespaceWithAttributePrefix() {
        // Given
        Attribute attribute = new Attribute("attrName", "attrValue", new Namespace("attributePrefix", "http://attr.example.com"));
        element.attributes = new AttributeList();
        element.attributes.add(attribute);
        String prefix = "attributePrefix";
        // When
        Namespace result = element.getNamespace(prefix);
        // Then
        assertEquals(attribute.getNamespace(), result, "Expected the namespace from the attribute for the given prefix");
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
        Element parentElement = new Element("parentElement", new Namespace("parentPrefix", "http://parent.example.com"));
        // Setting the parent directly via reflection
        element.parent = parentElement;
        String prefix = "parentPrefix";
        // When
        Namespace result = element.getNamespace(prefix);
        // Then
        assertEquals(parentElement.getNamespace(), result, "Expected the namespace from the parent element for the given prefix");
    }

    @Test
    void testGetNamespaceWithDefaultPrefix() {
        // Given
        String prefix = NS_PREFIX_DEFAULT;
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
        Namespace mixedNamespace = new Namespace("mixedPrefix", "http://mixed.example.com");
        element.additionalNamespaces = new ArrayList<>();
        element.additionalNamespaces.add(mixedNamespace);
        String prefix = "mixedPrefix";
        // When
        Namespace result = element.getNamespace(prefix);
        // Then
        assertEquals(mixedNamespace, result, "Expected the namespace from mixed content for the given prefix");
    }
}
```

Corresponding compilation feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
src/test/java/org/jdom2/Element_getNamespace_Test.java:25: error: Namespace(String,String) has private access in Namespace
        namespace = new Namespace("prefix", "http://example.com");
                    ^
src/test/java/org/jdom2/Element_getNamespace_Test.java:62: error: Namespace(String,String) has private access in Namespace
        Namespace additionalNamespace = new Namespace("additionalPrefix", "http://example.org");
                                        ^
src/test/java/org/jdom2/Element_getNamespace_Test.java:75: error: Namespace(String,String) has private access in Namespace
        Attribute attribute = new Attribute("attrName", "attrValue", new Namespace("attributePrefix", "http://attr.example.com"));
                                                                     ^
src/test/java/org/jdom2/Element_getNamespace_Test.java:76: error: constructor AttributeList in class AttributeList cannot be applied to given types;
        element.attributes = new AttributeList();
                             ^
  required: Element
  found:    no arguments
  reason: actual and formal argument lists differ in length
src/test/java/org/jdom2/Element_getNamespace_Test.java:98: error: Namespace(String,String) has private access in Namespace
        Element parentElement = new Element("parentElement", new Namespace("parentPrefix", "http://parent.example.com"));
                                                             ^
src/test/java/org/jdom2/Element_getNamespace_Test.java:141: error: Namespace(String,String) has private access in Namespace
        Namespace mixedNamespace = new Namespace("mixedPrefix", "http://mixed.example.com");
                                   ^
6 errors

```

And you can use the following related information to assist you in your analysis and correction:
```
{'api document of class org.jdom2.Element': "\n * An XML element. Methods allow the user to get and manipulate its child\n * elements and content, directly access the element's textual content,\n * manipulate its attributes, and manage namespaces.\n * <p>\n * See {@link NamespaceAware} and {@link #getNamespacesInScope()} for more\n * details on what the Namespace scope is and how it is managed in JDOM and\n * specifically by this Element class.\n * \n * @see NamespaceAware\n * @see Content\n * \n * @author  Brett McLaughlin\n * @author  Jason Hunter\n * @author  Lucas Gonze\n * @author  Kevin Regan\n * @author  Dan Schaffer\n * @author  Yusuf Goolamabbas\n * @author  Kent C. Johnson\n * @author  Jools Enticknap\n * @author  Alex Rosen\n * @author  Bradley S. Huffman\n * @author  Victor Toni\n * @author  Rolf Lear\n * \n ", 'api document of method getNamespace(String)': '\n\t * Returns the {@link Namespace} corresponding to the given prefix in scope\n\t * for this element. This involves searching up the tree, so the results\n\t * depend on the current location of the element. Returns null if there is\n\t * no namespace in scope with the given prefix at this point in the\n\t * document.\n\t *\n\t * @param  prefix              namespace prefix to look up\n\t * @return                     the Namespace for this prefix at this\n\t *                             location, or null if none\n\t ', 'return type': 'org.jdom2.Namespace', 'dependent classes': "class `org.jdom2.Content`:\n\tdependent fields:\n\t\torg.jdom2.Parent org.jdom2.Content.parent;\n\t\nclass `org.jdom2.Element`:\n\tdependent fields:\n\t\tjava.util.List<org.jdom2.Namespace> org.jdom2.Element.additionalNamespaces;\n\t\torg.jdom2.AttributeList org.jdom2.Element.attributes;\n\trelated functions:\n\t\tmethod `public Element clone()` returns `org.jdom2.Element`, related with `org.jdom2.Element.getNamespace(String)`, api document: \n\t * <p>\n\t *  This returns a deep clone of this element.\n\t *  The new element is detached from its parent, and getParent()\n\t *  on the clone will return null.\n\t * </p>\n\t *\n\t * @return the clone of this element\n\t ;\n\t\tmethod `public List<Namespace> getNamespacesInScope()` returns `java.util.List<org.jdom2.Namespace>`, related with `org.jdom2.Element.getNamespace(String)`, api document: \n\t * Get the Namespaces that are in-scope on this Element. Element has the\n\t * most complex rules for the namespaces-in-scope.\n\t * <p>\n\t * The scope is built up from a number of sources following the rules of\n\t * XML namespace inheritence as follows:\n\t * <ul>\n\t * <li>The {@link Namespace#XML_NAMESPACE} is added\n\t * <li>The element's namespace is added (commonly \n\t * {@link Namespace#NO_NAMESPACE})\n\t * <li>All the attributes are inspected and their Namespaces are included\n\t * <li>All Namespaces declared on this Element using\n\t * {@link #addNamespaceDeclaration(Namespace)} are included.\n\t * <li>If the element has a parent then the parent's Namespace scope is\n\t * inspected, and any prefixes in the parent scope that are not yet bound\n\t * in this Element's scope are included.\n\t * <li>If the default Namespace (the no-prefix namespace) has not been\n\t * encountered for this Element then {@link Namespace#NO_NAMESPACE} is\n\t * included.\n\t * </ul> \n\t * The Element's Namespace scope consist of it's inherited Namespaces and\n\t * any modifications to that scope derived from the Element itself. If the\n\t * element is detached then it's inherited scope consists of just \n\t * If an element has no parent then \n\t * <p>\n\t * Note that the Element's Namespace will always be reported first.\n\t * <p>\n\t * <strong>Description copied from</strong>\n\t * {@link NamespaceAware#getNamespacesInScope()}:\n\t * <p>\n\t * {@inheritDoc}\n\t * \n\t * @see NamespaceAware\n\t ;\n\t\tmethod `private void writeObject(ObjectOutputStream)` returns `void`, related with `org.jdom2.Element.getNamespace(String)`, api document: \n\t * Serialize out the Element.\n\t * \n\t * @serialData\n\t * The Stream protocol is:\n\t * <ol>\n\t *   <li>The Element name and Namespace using default Serialization.\n\t *   <li>The count of additional Namespace Declarations.\n\t *   <li>The actual additional Namespace Declarations.\n\t *   <li>The count of Attributes.\n\t *   <li>The actual Attributes.\n\t *   <li>The count of child Content\n\t *   <li>The actual Child Content.\n\t * </ol>\n\t * \n\t * @param out where to write the Element to.\n\t * @throws IOException if there is a writing problem.\n\t ;\nclass `org.jdom2.Namespace`:\n\tdependent fields:\n\t\torg.jdom2.Namespace org.jdom2.Namespace.XML_NAMESPACE;\n\t\nclass `org.jdom2.JDOMConstants`:\n\tdependent fields:\n\t\tjava.lang.String org.jdom2.JDOMConstants.NS_PREFIX_XML;\n\t\nclass `org.jdom2.Verifier`:\n\trelated functions:\n\t\tmethod `public static String checkNamespaceCollision(Attribute, Element, int)` returns `java.lang.String`, related with `org.jdom2.Element.getNamespace(String)`, api document: \n\t * Check if <code>{@link Attribute}</code>'s namespace collides with a \n\t * <code>{@link Element}</code>'s namespace.\n\t *\n\t * @param attribute <code>Attribute</code> to check.\n\t * @param element <code>Element</code> to check against.\n\t * @param ignoreatt Ignore a specific Attribute (if it exists) when\n\t *        calculating any collisions (used when replacing one attribute\n\t *        with another).\n\t * @return <code>String</code> reason for collision, or\n\t *         <code>null</code> if no collision.\n\t ;\nclass `org.jdom2.xpath.jaxen.JDOMCoreNavigator`:\n\trelated functions:\n\t\tmethod `public final String getElementQName(Object)` returns `java.lang.String`, related with `org.jdom2.Element.getNamespace(String)`;\nclass `org.jdom2.Attribute`:\n\trelated functions:\n\t\tmethod `public Attribute setNamespace(Namespace)` returns `org.jdom2.Attribute`, related with `org.jdom2.Element.getNamespace(String)`, api document: \n\t * This sets this <code>Attribute</code>'s <code>{@link Namespace}</code>.\n\t * If the provided namespace is null, the attribute will have no namespace.\n\t * The namespace must have a prefix.\n\t *\n\t * @param namespace the new namespace\n\t * @return <code>Element</code> - the element modified.\n\t * @throws IllegalNameException if the new namespace is the default\n\t *         namespace. Attributes cannot be in a default namespace.\n\t ;\n\t\tmethod `public List<Namespace> getNamespacesInherited()` returns `java.util.List<org.jdom2.Namespace>`, related with `org.jdom2.Element.getNamespace(String)`;"}
```

**Note**: If the method-to-test in the @Test function is a private method, you can use **reflection** to call it.

Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 


