You are a professional Java software testing expert. Your task is to generate high-quality unit test code for a focal method, using **JUnit5** and **Mockito** libraries. 

You'll be provided with:
1. The focal method to test.
2. The target class's structure (fields and methods).
3. A pre-defined test class framework with `@Before`/`@After` methods.
4. Test cases in JSON format.
5. Any relevant context.

**Requirements:**

Code Quality:
- Include clear comments explaining each test scenario.
- Ensure code is clean, understandable, and maintainable.
- Do not directly access private methods or fields. **Use reflection if the focal method is private.**
- Include all necessary imports; code must be free of syntax errors.

**Test Structure:**
- Follow the given test class framework; do not modify `@Before`/`@After` methods.
- Create one test function per test case group, ensuring all cases are covered.
- Use parameterized tests where appropriate.
- Reinitialize variables inside test functions if `@Setup` values don't fit.
- Properly mock and set up dependencies.

@input{focal method and target class}
Here is the code of focal method, `getNamespacesInScope()`, with the focal class `Element`:
```Java
package org.jdom2;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.IOException;
import java.net.URISyntaxException;
import org.jdom2.ContentList.FilterList;
import java.net.URI;
import java.io.ObjectOutputStream;
import java.util.TreeMap;
import java.util.Comparator;
import org.jdom2.filter.Filter;
import org.jdom2.filter.ElementFilter;
import org.jdom2.util.IteratorIterable;
import static org.jdom2.JDOMConstants.NS_PREFIX_XML;
import java.io.ObjectInputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import static org.jdom2.JDOMConstants.NS_PREFIX_DEFAULT;
import java.util.Collections;
public class Element extends Content implements Parent {
    protected String name;
    protected Namespace namespace;
    transient List<Namespace> additionalNamespaces = null;
    transient AttributeList attributes = null;
    transient ContentList content = new ContentList(this);
    @Override
    public List<Namespace> getNamespacesInScope() {
        // The assumption here is that all namespaces are valid,
        // that there are no namespace collisions on this element
        // This method is also the 'anchor' of the three getNamespaces*() methods
        // It does not make reference to this Element instance's other
        // getNamespace*() methods
        TreeMap<String, Namespace> namespaces = new TreeMap<String, Namespace>();
        namespaces.put(Namespace.XML_NAMESPACE.getPrefix(), Namespace.XML_NAMESPACE);
        namespaces.put(getNamespacePrefix(), getNamespace());
        if (additionalNamespaces != null) {
            for (Namespace ns : getAdditionalNamespaces()) {
                if (!namespaces.containsKey(ns.getPrefix())) {
                    namespaces.put(ns.getPrefix(), ns);
                }
            }
        }
        if (attributes != null) {
            for (Attribute att : getAttributes()) {
                Namespace ns = att.getNamespace();
                if (!namespaces.containsKey(ns.getPrefix())) {
                    namespaces.put(ns.getPrefix(), ns);
                }
            }
        }
        // Right, we now have all the namespaces that are current on this ELement.
        // Include any other namespaces that are inherited.
        final Element pnt = getParentElement();
        if (pnt != null) {
            for (Namespace ns : pnt.getNamespacesInScope()) {
                if (!namespaces.containsKey(ns.getPrefix())) {
                    namespaces.put(ns.getPrefix(), ns);
                }
            }
        }
        if ((pnt == null) && (!namespaces.containsKey(""))) {
            // we are the root element, and there is no 'default' namespace.
            namespaces.put(Namespace.NO_NAMESPACE.getPrefix(), Namespace.NO_NAMESPACE);
        }
        ArrayList<Namespace> al = new ArrayList<Namespace>(namespaces.size());
        al.add(getNamespace());
        namespaces.remove(getNamespacePrefix());
        al.addAll(namespaces.values());
        return Collections.unmodifiableList(al);
    }
@Override
public Element addContent(final int index, final Collection<? extends Content> newContent);
    @Override
public Element addContent(final int index, final Content child);
    public Element addContent(final String str);
    @Override
public Element addContent(final Collection<? extends Content> newContent);
    @Override
public Element addContent(final Content child);
    public boolean addNamespaceDeclaration(final Namespace additionalNamespace);
    @Override
public void canContainContent(Content child, int index, boolean replace) throws IllegalAddException;
    @Override
public Element clone();
    @Override
public List<Content> cloneContent();
    public boolean coalesceText(boolean recursively);
    @Override
public Element detach();
    public List<Namespace> getAdditionalNamespaces();
    public Attribute getAttribute(final String attname);
    public Attribute getAttribute(final String attname, final Namespace ns);
    AttributeList getAttributeList();
    public String getAttributeValue(final String attname);
    public String getAttributeValue(final String attname, final String def);
    public String getAttributeValue(final String attname, final Namespace ns);
    public String getAttributeValue(final String attname, final Namespace ns, final String def);
    public List<Attribute> getAttributes();
    public Element getChild(final String cname);
    public Element getChild(final String cname, final Namespace ns);
    public String getChildText(final String cname);
    public String getChildText(final String cname, final Namespace ns);
    public String getChildTextNormalize(final String cname);
    public String getChildTextNormalize(final String cname, final Namespace ns);
    public String getChildTextTrim(final String cname);
    public String getChildTextTrim(final String cname, final Namespace ns);
    public List<Element> getChildren();
    public List<Element> getChildren(final String cname);
    public List<Element> getChildren(final String cname, final Namespace ns);
    @Override
public List<Content> getContent();
    @Override
public Content getContent(final int index);
    @Override
public <E extends Content> List<E> getContent(final Filter<E> filter);
    @Override
public int getContentSize();
    @Override
public IteratorIterable<Content> getDescendants();
    @Override
public <F extends Content> IteratorIterable<F> getDescendants(final Filter<F> filter);
    public String getName();
    public Namespace getNamespace();
    public Namespace getNamespace(final String prefix);
    public String getNamespacePrefix();
    public String getNamespaceURI();
    @Override
public List<Namespace> getNamespacesInherited();
    @Override
public List<Namespace> getNamespacesIntroduced();
    public String getQualifiedName();
    public String getText();
    public String getTextNormalize();
    public String getTextTrim();
    @Override
public String getValue();
    public URI getXMLBaseURI() throws URISyntaxException;
    public boolean hasAdditionalNamespaces();
    public boolean hasAttributes();
    @Override
public int indexOf(final Content child);
    public boolean isAncestor(final Element element);
    public boolean isRootElement();
    public boolean removeAttribute(final String attname);
    public boolean removeAttribute(final String attname, final Namespace ns);
    public boolean removeAttribute(final Attribute attribute);
    public boolean removeChild(final String cname);
    public boolean removeChild(final String cname, final Namespace ns);
    public boolean removeChildren(final String cname);
    public boolean removeChildren(final String cname, final Namespace ns);
    @Override
public List<Content> removeContent();
    @Override
public Content removeContent(final int index);
    @Override
public boolean removeContent(final Content child);
    @Override
public <F extends Content> List<F> removeContent(final Filter<F> filter);
    public void removeNamespaceDeclaration(final Namespace additionalNamespace);
    public Element setAttribute(final String name, final String value);
    public Element setAttribute(final String name, final String value, final Namespace ns);
    public Element setAttribute(final Attribute attribute);
    public Element setAttributes(final Collection<? extends Attribute> newAttributes);
    public Parent setContent(final int index, final Collection<? extends Content> newContent);
    public Element setContent(final int index, final Content child);
    public Element setContent(final Collection<? extends Content> newContent);
    public Element setContent(final Content child);
    public Element setName(final String name);
    public Element setNamespace(Namespace namespace);
    public Element setText(final String text);
    public void sortAttributes(Comparator<? super Attribute> comparator);
    public void sortChildren(Comparator<? super Element> comparator);
    public void sortContent(Comparator<? super Content> comparator);
    public <E extends Content> void sortContent(Filter<E> filter, Comparator<? super E> comparator);
    @Override
public String toString();
}
```

@input{initial test class framework}
```Java
<initial_class>
```

@input{existing test case}
here's the existing test cases:
```json
<cases_json>
```


@input{context information}

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
		org.jdom2.AttributeList org.jdom2.Element.attributes;
		java.util.List<org.jdom2.Namespace> org.jdom2.Element.additionalNamespaces;
	related functions:
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
		method `public Element setNamespace(Namespace)` returns `org.jdom2.Element`, related with `org.jdom2.Element.getNamespacesInScope()`, api document: 
	 * Sets the element's {@link Namespace}. If the provided namespace is null,
	 * the element will have no namespace.
	 *
	 * @param  namespace           the new namespace. A null implies Namespace.NO_NAMESPACE.
	 * @return                     the target element
	 * @throws IllegalAddException if there is a Namespace conflict
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



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.