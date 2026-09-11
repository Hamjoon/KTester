


You are an experienced Java developer tasked with fixing a piece of code that contains execution errors. Your task is to resolve these issues after analyzing the code and its execution feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any logical errors.
2. Analyze the execution feedback to understand the specific errors and their locations within the code.
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
        // Should return a list with only the XML namespace and the NO_NAMESPACE
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

```

Corresponding execution feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended

╷
├─ JUnit Jupiter ✔
│  └─ Element_getNamespacesInScope_Test ✔
│     ├─ testGetNamespacesInScope_WithDuplicateAdditionalNamespaces() ✔
│     ├─ testGetNamespacesInScope_WithAttributes() ✔
│     ├─ testGetNamespacesInScope() ✔
│     ├─ testGetNamespacesInScope_WithAdditionalNamespaces() ✔
│     ├─ testGetNamespacesInScope_RootElement() ✔
│     ├─ testGetNamespacesInScope_NoAdditionalNamespacesOrAttributes() ✘ Should not contain the default namespace ==> expected: <false> but was: <true>
│     └─ testGetNamespacesInScope_WithParentElement() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Failures (1):
  JUnit Jupiter:Element_getNamespacesInScope_Test:testGetNamespacesInScope_NoAdditionalNamespacesOrAttributes()
    MethodSource [className = 'org.jdom2.Element_getNamespacesInScope_Test', methodName = 'testGetNamespacesInScope_NoAdditionalNamespacesOrAttributes', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Should not contain the default namespace ==> expected: <false> but was: <true>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertFalse.failNotFalse(AssertFalse.java:63)
       org.junit.jupiter.api.AssertFalse.assertFalse(AssertFalse.java:36)
       org.junit.jupiter.api.Assertions.assertFalse(Assertions.java:239)
       org.jdom2.Element_getNamespacesInScope_Test.testGetNamespacesInScope_NoAdditionalNamespacesOrAttributes(Element_getNamespacesInScope_Test.java:45)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       [...]

Test run finished after 367 ms
[         4 containers found      ]
[         0 containers skipped    ]
[         4 containers started    ]
[         0 containers aborted    ]
[         4 containers successful ]
[         0 containers failed     ]
[         7 tests found           ]
[         0 tests skipped         ]
[         7 tests started         ]
[         0 tests aborted         ]
[         6 tests successful      ]
[         1 tests failed          ]


```


And you can use the following related information to assist you in your analysis and correction:

api document of class org.jdom2.Element: 

 * An XML element. Methods allow the user to get and manipulate its child
 * elements and content, directly access the element's textual content,
 * manipulate its attributes, and manage namespaces.
 * <p>
 * See {@link NamespaceAware} and {@link #getNamespacesInScope()} for more
 * details on what the Namespace scope is and how it is managed in JDOM and
 * specifically by this Element class.
 * 
 * @see NamespaceAware
 * @see Content
 * 
 * @author  Brett McLaughlin
 * @author  Jason Hunter
 * @author  Lucas Gonze
 * @author  Kevin Regan
 * @author  Dan Schaffer
 * @author  Yusuf Goolamabbas
 * @author  Kent C. Johnson
 * @author  Jools Enticknap
 * @author  Alex Rosen
 * @author  Bradley S. Huffman
 * @author  Victor Toni
 * @author  Rolf Lear
 * 
 

api document of method getNamespacesInScope(): 

	 * Get the Namespaces that are in-scope on this Element. Element has the
	 * most complex rules for the namespaces-in-scope.
	 * <p>
	 * The scope is built up from a number of sources following the rules of
	 * XML namespace inheritence as follows:
	 * <ul>
	 * <li>The {@link Namespace#XML_NAMESPACE} is added
	 * <li>The element's namespace is added (commonly 
	 * {@link Namespace#NO_NAMESPACE})
	 * <li>All the attributes are inspected and their Namespaces are included
	 * <li>All Namespaces declared on this Element using
	 * {@link #addNamespaceDeclaration(Namespace)} are included.
	 * <li>If the element has a parent then the parent's Namespace scope is
	 * inspected, and any prefixes in the parent scope that are not yet bound
	 * in this Element's scope are included.
	 * <li>If the default Namespace (the no-prefix namespace) has not been
	 * encountered for this Element then {@link Namespace#NO_NAMESPACE} is
	 * included.
	 * </ul> 
	 * The Element's Namespace scope consist of it's inherited Namespaces and
	 * any modifications to that scope derived from the Element itself. If the
	 * element is detached then it's inherited scope consists of just 
	 * If an element has no parent then 
	 * <p>
	 * Note that the Element's Namespace will always be reported first.
	 * <p>
	 * <strong>Description copied from</strong>
	 * {@link NamespaceAware#getNamespacesInScope()}:
	 * <p>
	 * {@inheritDoc}
	 * 
	 * @see NamespaceAware
	 

dependent classes: 
class `org.jdom2.Namespace`:
	dependent fields:
		org.jdom2.Namespace org.jdom2.Namespace.NO_NAMESPACE;
		org.jdom2.Namespace org.jdom2.Namespace.XML_NAMESPACE;
	
class `org.jdom2.Element`:
	dependent fields:
		java.util.List<org.jdom2.Namespace> org.jdom2.Element.additionalNamespaces;
		org.jdom2.AttributeList org.jdom2.Element.attributes;
	related functions:
		method `public Element setNamespace(Namespace)` returns `org.jdom2.Element`, related with `org.jdom2.Element.getNamespacesInScope()`, api document: 
	 * Sets the element's {@link Namespace}. If the provided namespace is null,
	 * the element will have no namespace.
	 *
	 * @param  namespace           the new namespace. A null implies Namespace.NO_NAMESPACE.
	 * @return                     the target element
	 * @throws IllegalAddException if there is a Namespace conflict
	 ;
		method `public List<Namespace> getNamespacesIntroduced()` returns `java.util.List<org.jdom2.Namespace>`, related with `org.jdom2.Element.getNamespacesInScope()`;
		method `public Namespace getNamespace(String)` returns `org.jdom2.Namespace`, related with `org.jdom2.Element.getNamespacesInScope()`, api document: 
	 * Returns the {@link Namespace} corresponding to the given prefix in scope
	 * for this element. This involves searching up the tree, so the results
	 * depend on the current location of the element. Returns null if there is
	 * no namespace in scope with the given prefix at this point in the
	 * document.
	 *
	 * @param  prefix              namespace prefix to look up
	 * @return                     the Namespace for this prefix at this
	 *                             location, or null if none
	 ;
		method `public List<Namespace> getNamespacesInherited()` returns `java.util.List<org.jdom2.Namespace>`, related with `org.jdom2.Element.getNamespacesInScope()`;
class `org.jdom2.Attribute`:
	related functions:
		method `public List<Namespace> getNamespacesInScope()` returns `java.util.List<org.jdom2.Namespace>`, related with `org.jdom2.Element.getNamespacesInScope()`, api document: 
	 * Get the namespaces that are in-scope on this Attribute.
	 * <p>
	 * Attribute has peculiarities that affect the in-scope Namespaces because
	 * there are conditions in which the Attribute's scope is different to its
	 * parent Element's scope. Specifically, if the parent Element is in a
	 * 'default' Namespace that is not the empty Namespace (e.g.
	 * xmlns="someurl") and this Attribute is also in the default Namespace (has
	 * no prefix - but for Attributes that means the Namespace URL is ""), then
	 * this Attribute has a different namespace scope from it's parent Element
	 * because it does not include the 'someurl' Namespace.
	 * <p>
	 * In the above conditions (no-prefix Attribute in an Element with a
	 * non-empty no-prefix Namespace) this Attribute effectively re-binds the ""
	 * prefix to the "" URL, thus the Attribute 'introduces' the Namespace.
	 * It follows then that the getNamespacesIntroduced() will return a list
	 * with the single member {@link Namespace#NO_NAMESPACE}.
	 * <p>
	 * Note that the Attribute's Namespace will always be reported first.
	 * <p>
	 * <strong>Description copied from</strong>
	 * {@link NamespaceAware#getNamespacesInScope()}:
	 * <p>
	 * {@inheritDoc}
	 ;
		method `private static final List<Namespace> orderFirst(Namespace, List<Namespace>)` returns `java.util.List<org.jdom2.Namespace>`, related with `org.jdom2.Element.getNamespacesInScope()`;
class `org.jdom2.Document`:
	related functions:
		method `public List<Namespace> getNamespacesInScope()` returns `java.util.List<org.jdom2.Namespace>`, related with `org.jdom2.Element.getNamespacesInScope()`, api document: 
	 * Get the Namespaces that are in-scope on this Document.
	 * <p>
	 * Document always has exactly two Namespaces in-scope:
	 * {@link Namespace#NO_NAMESPACE} and {@link Namespace#XML_NAMESPACE}.
	 * <p>
	 * These namespaces are always introduced by the Document, and thus they are
	 * both returned by {@link #getNamespacesIntroduced()}, and additionally
	 * {@link #getNamespacesInherited()} will always be empty.
	 * <p>
	 * <strong>Description copied from</strong>
	 * {@link NamespaceAware#getNamespacesInScope()}:
	 * <p>
	 * {@inheritDoc}
	 ;
class `org.jdom2.output.support.AbstractStAXEventProcessor`:
	related functions:
		method `protected void printElement(XMLEventConsumer, FormatStack, NamespaceStack, XMLEventFactory, Element)` returns `void`, related with `org.jdom2.Element.getNamespacesInScope()`, api document: 
	 * This will handle printing of an {@link Element}.
	 * <p>
	 * This method arranges for outputting the Element infrastructure including
	 * Namespace Declarations and Attributes.
	 * <p>
	 * 
	 * @param out
	 *        <code>XMLEventConsumer</code> to use.
	 * @param fstack
	 *        the FormatStack
	 * @param nstack
	 *        the NamespaceStack
	 * @param eventfactory
	 * 		  The XMLEventFactory for creating XMLEvents 
	 * @param element
	 *        <code>Element</code> to write.
	 * @throws XMLStreamException
	 *         if the destination XMLEventConsumer fails
	 ;



Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 
