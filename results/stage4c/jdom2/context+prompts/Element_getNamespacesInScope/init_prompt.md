You are tasked with creating a framework for a test class in Java. Now analyze the following information carefully:

1. The method that needs to be tested and its parameters.
2. The constructor of the target class and its parameters.
3. Any dependencies or external resources used by the target class.

Based on this analysis, complete the test class template in the provided structure:

1. Initialize necessary fields and dependencies within setupBeforeAll() and setupBeforeEach().
2. Handle any required cleanup in teardownAfterEach() and teardownAfterAll().
3. Make sure not to invoke any private methods or fields of the target class and dependent class.
4. Refer to the "given" phase in the existing test class for guidance.
5. Add necessary import statements and ensure there are no syntax errors in the generated code.

@input{focal method and target class}
Here is the code of focal method, `getNamespacesInScope()`, with the focal class `Element_getNamespacesInScope_Test`:
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
 

constructors for class `org.jdom2.Element`: 
params: 
body:
```java
	protected Element() {
		super(CType.Element);
	}

```
params: org.jdom2.Namespace namespace 
body:
```java
	public Element(final String name, final Namespace namespace) {
		super(CType.Element);
		setName(name);
		setNamespace(namespace);
	}

```
params: 
body:
```java
	public Element(final String name) {
		this(name, (Namespace) null);
	}

```
params: 
body:
```java
	public Element(final String name, final String uri) {
		this(name, Namespace.getNamespace(NS_PREFIX_DEFAULT, uri));
	}

```
params: 
body:
```java
	public Element(final String name, final String prefix, final String uri) {
		this(name, Namespace.getNamespace(prefix, uri));
	}

```

parameters in constructors and focal method: 
class `org.jdom2.Namespace`:
api document : 
 * An XML namespace representation, as well as a factory for creating XML
 * namespace objects. All methods on Namespace (including
 * {@link #getNamespace(String)} and {@link #getNamespace(String, String)})
 * are thread-safe.
 * 
 * <p>
 * See {@link NamespaceAware} for additional notes on how Namespaces are
 * 'in-scope' in JDOM content, and how those in-scope Namespaces are accessed.
 *
 * @see NamespaceAware
 * @author  Brett McLaughlin
 * @author  Elliotte Rusty Harold
 * @author  Jason Hunter
 * @author  Wesley Biggs
 * @author  Rolf Lear
 
constructor:
```java
	private Namespace(final String prefix, final String uri) {
		this.prefix = prefix;
		this.uri = uri;
	}

```

invoke examples of focal method: 
example 1:
```java
	@Override
	public List<Namespace> getNamespacesInScope() {
		if (getParent() == null) {
		return orderFirst(getNamespace(), getParent().getNamespacesInScope());
	}
```
example 2:
```java
	@Override
	public List<Namespace> getNamespacesInherited() {
		if (getParent() == null) {
		return orderFirst(getNamespace(), getParent().getNamespacesInScope());
	}
```
example 3:
```java
	@Override
	public List<Namespace> getNamespacesInScope() {
		// Element class will override this method to do it differently.
		Element emt = getParentElement();
		if (emt == null) {
		return emt.getNamespacesInScope();
	}
```



@input{test class template}
```java
package org.jdom2;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
// Add other imports if necessary

@ExtendWith(MockitoExtension.class)
class Element_getNamespacesInScope_Test {
    @BeforeAll
    static void setupBeforeAll() {
    }

    @BeforeEach
    void setupBeforeEach() {
    }

    @AfterEach
    void teardownAfterEach() {
    }

    @AfterAll
    static void teardownAfterAll() {
    }
}
```

@output{initial test class}: now consider the invoke examples output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.