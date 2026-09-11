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
Here is the code of focal method, `getSingleStep(NamespaceAware, StringBuilder)`, with the focal class `XPathHelper`:
```Java
package org.jdom2.xpath;
import org.jdom2.NamespaceAware;
import org.jdom2.Attribute;
import org.jdom2.Parent;
import org.jdom2.Element;
import java.util.ArrayList;
import org.jdom2.Namespace;
import org.jdom2.Text;
import org.jdom2.Content;
import org.jdom2.filter.Filters;
import java.util.Iterator;
import org.jdom2.ProcessingInstruction;
import java.util.List;
import org.jdom2.Comment;
import java.util.Collections;
public final class XPathHelper {
    
    private static final StringBuilder getSingleStep(final NamespaceAware nsa, final StringBuilder buffer) {
        if (nsa instanceof Content) {
            final Content content = ((Content) (nsa));
            final Parent pnt = content.getParent();
            if (content instanceof Text) {
                // OR CDATA!
                final List<?> sibs = (pnt == null) ? null : pnt.getContent(Filters.text());// CDATA
    
                return getPositionPath(content, sibs, "text()", buffer);
            } else if (content instanceof Comment) {
                final List<?> sibs = (pnt == null) ? null : pnt.getContent(Filters.comment());
                return getPositionPath(content, sibs, "comment()", buffer);
            } else if (content instanceof ProcessingInstruction) {
                final List<?> sibs = (pnt == null) ? null : pnt.getContent(Filters.processinginstruction());
                return getPositionPath(content, sibs, "processing-instruction()", buffer);
            } else if ((content instanceof Element) && (((Element) (content)).getNamespace() == Namespace.NO_NAMESPACE)) {
                // simple XPath to a no-namespace Element.
                final String ename = ((Element) (content)).getName();
                final List<?> sibs = (pnt instanceof Element) ? ((Element) (pnt)).getChildren(ename) : null;
                return getPositionPath(content, sibs, ename, buffer);
            } else if (content instanceof Element) {
                // complex XPath to an Element with Namespace...
                // we do not want to have to prefix namespaces because that is
                // essentially impossible to get right with the new JDOM2 API.
                final Element emt = ((Element) (content));
                // Note, the getChildren compares only the URI (not the prefix)
                // so the results are the same as an XPath would be.
                final List<?> sibs = (pnt instanceof Element) ? ((Element) (pnt)).getChildren(emt.getName(), emt.getNamespace()) : null;
                String xps = ((("*[local-name() = '" + emt.getName()) + "' and namespace-uri() = '") + emt.getNamespaceURI()) + "']";
                return getPositionPath(content, sibs, xps, buffer);
            } else {
                final List<?> sibs = (pnt == null) ? Collections.singletonList(nsa) : pnt.getContent();
                return getPositionPath(content, sibs, "node()", buffer);
            }
        } else if (nsa instanceof Attribute) {
            Attribute att = ((Attribute) (nsa));
            if (att.getNamespace() == Namespace.NO_NAMESPACE) {
                buffer.append("@").append(att.getName());
            } else {
                buffer.append("@*[local-name() = '").append(att.getName());
                buffer.append("' and namespace-uri() = '");
                buffer.append(att.getNamespaceURI()).append("']");
            }
        }
        // do nothing...
        return buffer;
    }
public static String getAbsolutePath(final Attribute to);
    public static String getAbsolutePath(final Content to);
    private static StringBuilder getPositionPath(Object node, List<?> siblings, String pathToken, StringBuilder buffer);
    public static String getRelativePath(final Attribute from, final Attribute to);
    public static String getRelativePath(final Attribute from, final Content to);
    public static String getRelativePath(final Content from, final Attribute to);
    public static String getRelativePath(final Content from, final Content to);
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

api document of class org.jdom2.xpath.XPathHelper: 

 * Provides a set of utility methods to generate XPath expressions to select a
 * given node in a document. You can generate absolute XPath expressions to the
 * target node, or relative expressions from a specified start point. If you
 * request a relative expression, the start and target nodes must share some
 * common ancestry.
 * <p>
 * XPaths are required to be namespace-aware. Typically this is done by using
 * a namespace-prefixed query, with the actual namespace URI being set in the
 * context of the XPath expression. This is not possible to express using a
 * simple String return value. As a work-around, this method uses a potentially
 * slower, but more reliable, mechanism for ensuring the correct namespace
 * context is selected. The mechanism will appear like (for Elements):
 * <br>
 * <code>   .../*[local-name() = 'tag' and namespace-uri() = 'uri']</code>
 * <br>
 * Similarly, Attributes will have a syntax similar to:
 * <br>
 * <code>   .../@*[local-name() = 'attname' and namespace-uri() = 'uri'] </code>
 * <br>
 * This mechanism makes it possible to have a simple namespace context, and a
 * simple String value returned from the methods on this class.
 * <p>
 * This class does not provide ways to access document-level content. Nor does
 * it provide ways to access data relative to the Document level. Use absolute
 * methods to access data from the Document level.
 * <p>
 * The methods on this class assume that the Document is above the top-most
 * Element in the XML tree. The top-most Element is the one that does not have
 * a parent Element (although it may have a parent Document). As a result, you
 * can use Element data that is not attached to a JDOM Document.
 * <p>
 * Detatched Attributes, and detached non-Element content are not treated the
 * same. If you try to get an Absolute path to a detached Attribute or
 * non-Element Content you will get an IllegalArgumentException. On the other
 * hand it is legal to get the relative XPath for a detached node to itself (
 * but to some other node will cause an IllegalArgumentException because the
 * nodes do not share a common ancestor).  
 * <p>
 * <strong>Note</strong>: As this class has no knowledge of the document
 * content, the generated XPath expression rely on the document structure. Hence
 * any modification of the structure of the document may invalidate the
 * generated XPaths.
 * </p>
 * 
 * @author Laurent Bihanic
 * @author Rolf Lear
 

api document of method getSingleStep(NamespaceAware, StringBuilder): 

	 * Calculate a single stage of an XPath query.
	 * 
	 * @param nsa
	 *        The token to get the relative-to-parent XPath for
	 * @param buffer
	 *        The buffer to append the relative stage to
	 * @return The same buffer as was input.
	 

dependent classes: 
class `org.jdom2.NamespaceAware`:
	
class `org.jdom2.Namespace`:
	dependent fields:
		org.jdom2.Namespace org.jdom2.Namespace.NO_NAMESPACE;
	
class `org.jdom2.output.support.AbstractStAXStreamProcessor`:
	related functions:
		method `protected void printAttribute(XMLStreamWriter, FormatStack, Attribute)` returns `void`, related with `org.jdom2.xpath.XPathHelper.getSingleStep(NamespaceAware, StringBuilder)`, api document: 
	 * This will handle printing of an <code>{@link Attribute}</code>.
	 * 
	 * @param out
	 *        <code>XMLStreamWriter</code> to use.
	 * @param fstack
	 *        The current FormatStack
	 * @param attribute
	 *        <code>Attribute</code> to output
	 * @throws XMLStreamException
	 *         if the output fails
	 ;
class `org.jdom2.Element`:
	related functions:
		method `public List<Element> getChildren(String)` returns `java.util.List<org.jdom2.Element>`, related with `org.jdom2.xpath.XPathHelper.getSingleStep(NamespaceAware, StringBuilder)`, api document: 
	 * This returns a <code>List</code> of all the child elements
	 * nested directly (one level deep) within this element with the given
	 * local name and belonging to no namespace, returned as
	 * <code>Element</code> objects.  If this target element has no nested
	 * elements with the given name outside a namespace, an empty List
	 * is returned.  The returned list is "live" in document order
	 * and changes to it affect the element's actual contents.
	 * <p>
	 * Please see the notes for <code>{@link #getChildren}</code>
	 * for a code example.
	 * </p>
	 *
	 * @param cname local name for the children to match
	 * @return all matching child elements
	 ;
		method `public Attribute getAttribute(String)` returns `org.jdom2.Attribute`, related with `org.jdom2.xpath.XPathHelper.getSingleStep(NamespaceAware, StringBuilder)`, api document: 
	 * <p>
	 * This returns the attribute for this element with the given name
	 * and within no namespace, or null if no such attribute exists.
	 * </p>
	 *
	 * @param attname name of the attribute to return
	 * @return attribute for the element
	 ;
class `org.jdom2.Verifier`:
	related functions:
		method `public static String checkNamespaceCollision(Namespace, Attribute)` returns `java.lang.String`, related with `org.jdom2.xpath.XPathHelper.getSingleStep(NamespaceAware, StringBuilder)`, api document: 
	 * Check if a <code>{@link Namespace}</code> collides with a
	 * <code>{@link Attribute}</code>'s namespace.
	 *
	 * @param namespace <code>Namespace</code> to check.
	 * @param attribute <code>Attribute</code> to check against.
	 * @return <code>String</code> reason for collision, or
	 *         <code>null</code> if no collision.
	 ;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.