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
Here is the code of focal method, `startElement(String, String, String, Attributes)`, with the focal class `SAXHandler`:
```Java
package org.jdom2.input.sax;
import java.util.HashMap;
import org.jdom2.Attribute;
import org.jdom2.Parent;
import org.jdom2.Element;
import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.jdom2.JDOMFactory;
import org.jdom2.Document;
import org.xml.sax.SAXException;
import org.jdom2.DefaultJDOMFactory;
import org.xml.sax.Locator;
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.ext.LexicalHandler;
import org.jdom2.AttributeType;
import java.util.List;
import org.jdom2.EntityRef;
import org.jdom2.Comment;
import org.jdom2.DocType;
import org.jdom2.input.SAXBuilder;
import org.jdom2.Namespace;
import org.jdom2.Text;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.ext.Attributes2;
import javax.xml.XMLConstants;
import org.jdom2.ProcessingInstruction;
import java.util.Map;
import org.xml.sax.DTDHandler;
import org.jdom2.CDATA;
public class SAXHandler extends DefaultHandler implements LexicalHandler , DeclHandler , DTDHandler {
    private final JDOMFactory factory;
    private final List<Namespace> declaredNamespaces = new ArrayList<Namespace>(32);
    private Document currentDocument = null;
    private Element currentElement = null;
    private Locator currentLocator = null;
    private boolean atRoot = true;
    private boolean suppress = false;
    @Override
    public void startElement(final String namespaceURI, String localName, final String qName, final Attributes atts) throws SAXException {
        if (suppress)
            return;
    
        String prefix = "";
        // If QName is set, then set prefix and local name as necessary
        if (!"".equals(qName)) {
            final int colon = qName.indexOf(':');
            if (colon > 0) {
                prefix = qName.substring(0, colon);
            }
            // If local name is not set, try to get it from the QName
            if ((localName == null) || localName.equals("")) {
                localName = qName.substring(colon + 1);
            }
        }
        // At this point either prefix and localName are set correctly or
        // there is an error in the parser.
        final Namespace namespace = Namespace.getNamespace(prefix, namespaceURI);
        final Element element = (currentLocator == null) ? factory.element(localName, namespace) : factory.element(currentLocator.getLineNumber(), currentLocator.getColumnNumber(), localName, namespace);
        // Take leftover declared namespaces and add them to this element's
        // map of namespaces
        if (declaredNamespaces.size() > 0) {
            transferNamespaces(element);
        }
        flushCharacters();
        if (atRoot) {
            factory.setRoot(currentDocument, element);// Yes, use a factory
    
            // call...
            atRoot = false;
        } else {
            factory.addContent(getCurrentElement(), element);
        }
        currentElement = element;
        // Handle attributes
        for (int i = 0, len = atts.getLength(); i < len; i++) {
            String attPrefix = "";
            String attLocalName = atts.getLocalName(i);
            final String attQName = atts.getQName(i);
            final boolean specified = (atts instanceof Attributes2) ? ((Attributes2) (atts)).isSpecified(i) : true;
            // If attribute QName is set, then set attribute prefix and
            // attribute local name as necessary
            if (!attQName.equals("")) {
                // Bypass any xmlns attributes which might appear, as we got
                // them already in startPrefixMapping(). This is sometimes
                // necessary when SAXHandler is used with another source than
                // SAXBuilder, as with JDOMResult.
                if (attQName.startsWith("xmlns:") || attQName.equals("xmlns")) {
                    continue;
                }
                final int attColon = attQName.indexOf(':');
                if (attColon > 0) {
                    attPrefix = attQName.substring(0, attColon);
                }
                // If localName is not set, try to get it from the QName
                if ("".equals(attLocalName)) {
                    attLocalName = attQName.substring(attColon + 1);
                }
            }
            final AttributeType attType = AttributeType.getAttributeType(atts.getType(i));
            final String attValue = atts.getValue(i);
            final String attURI = atts.getURI(i);
            if ((XMLConstants.XMLNS_ATTRIBUTE.equals(attLocalName) || XMLConstants.XMLNS_ATTRIBUTE.equals(attPrefix)) || XMLConstants.XMLNS_ATTRIBUTE_NS_URI.equals(attURI)) {
                // use the actual Namespace to check too, because, in theory, a
                // namespace-aware parser does not need to set the qName unless
                // the namespace-prefixes feature is set as well.
                continue;
            }
            // At this point either attPrefix and attLocalName are set
            // correctly or there is an error in the parser.
            // just one thing to sort out....
            // the prefix for the namespace.
            if ((!"".equals(attURI)) && "".equals(attPrefix)) {
                // the localname and qName are the same, but there is a
                // Namspace URI. We need to figure out the namespace prefix.
                // this is an unusual condition. Currently the only known
                // trigger
                // is when there is a fixed/defaulted attribute from a
                // validating
                // XMLSchema, and the attribute is in a different namespace
                // than the rest of the document, this happens whenever there
                // is an attribute definition that has form="qualified".
                // <xs:attribute name="attname" form="qualified" ... />
                // or the schema sets attributeFormDefault="qualified"
                final HashMap<String, Namespace> tmpmap = new HashMap<String, Namespace>();
                for (final Namespace nss : element.getNamespacesInScope()) {
                    if ((nss.getPrefix().length() > 0) && nss.getURI().equals(attURI)) {
                        attPrefix = nss.getPrefix();
                        break;
                    }
                    tmpmap.put(nss.getPrefix(), nss);
                }
                if ("".equals(attPrefix)) {
                    // we cannot find a 'prevailing' namespace that has a prefix
                    // that is for this namespace.
                    // This basically means that there's an XMLSchema, for the
                    // DEFAULT namespace, and there's a defaulted/fixed
                    // attribute definition in the XMLSchema that's targeted
                    // for this namespace,... but, the user has either not
                    // declared a prefixed version of the namespace, or has
                    // re-declared the same prefix at a lower level with a
                    // different namespace.
                    // All of these things are possible.
                    // Create some sort of default prefix.
                    int cnt = 0;
                    final String base = "attns";
                    String pfx = base + cnt;
                    while (tmpmap.containsKey(pfx)) {
                        cnt++;
                        pfx = base + cnt;
                    } 
                    attPrefix = pfx;
                }
            }
            final Namespace attNs = Namespace.getNamespace(attPrefix, attURI);
            final Attribute attribute = factory.attribute(attLocalName, attValue, attType, attNs);
            if (!specified) {
                // it is a DTD defaulted value.
                attribute.setSpecified(false);
            }
            factory.setAttribute(element, attribute);
        }
    }
@Override
public void attributeDecl(final String eName, final String aName, final String type, final String valueDefault, final String value);
    @Override
public void characters(final char[] ch, final int start, final int length) throws SAXException;
    @Override
public void comment(final char[] ch, final int start, final int length) throws SAXException;
    @Override
public void elementDecl(final String name, final String model);
    @Override
public void endCDATA() throws SAXException;
    @Override
public void endDTD();
    @Override
public void endElement(final String namespaceURI, final String localName, final String qName) throws SAXException;
    @Override
public void endEntity(final String name) throws SAXException;
    @Override
public void externalEntityDecl(final String name, final String publicID, final String systemID) throws SAXException;
    protected void flushCharacters() throws SAXException;
    protected void flushCharacters(final String data) throws SAXException;
    public Element getCurrentElement() throws SAXException;
    public Document getDocument();
    public Locator getDocumentLocator();
    public boolean getExpandEntities();
    public JDOMFactory getFactory();
    public boolean getIgnoringBoundaryWhitespace();
    public boolean getIgnoringElementContentWhitespace();
    @Override
public void ignorableWhitespace(final char[] ch, final int start, final int length) throws SAXException;
    @Override
public void internalEntityDecl(final String name, final String value);
    @Override
public void notationDecl(final String name, final String publicID, final String systemID) throws SAXException;
    @Override
public void processingInstruction(final String target, final String data) throws SAXException;
    protected void pushElement(final Element element);
    public final void reset();
    protected void resetSubCLass();
    @Override
public void setDocumentLocator(final Locator locator);
    public void setExpandEntities(final boolean expand);
    public void setIgnoringBoundaryWhitespace(final boolean ignoringBoundaryWhite);
    public void setIgnoringElementContentWhitespace(final boolean ignoringWhite);
    @Override
public void skippedEntity(final String name) throws SAXException;
    @Override
public void startCDATA();
    @Override
public void startDTD(final String name, final String publicID, final String systemID) throws SAXException;
    @Override
public void startDocument();
    @Override
public void startEntity(final String name) throws SAXException;
    @Override
public void startPrefixMapping(final String prefix, final String uri) throws SAXException;
    private void transferNamespaces(final Element element);
    @Override
public void unparsedEntityDecl(final String name, final String publicID, final String systemID, final String notationName);
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

api document of class org.jdom2.input.sax.SAXHandler: 

 * A support class for {@link SAXBuilder} which listens for SAX events.
 * <p>
 * People overriding this class are cautioned to ensure that the implementation
 * of the cleanUp() method resets to a virgin state. The cleanUp() method will
 * be called when this SAXHandler is reset(), which may happen multiple times
 * between parses. The cleanUp() method must ensure that there are no references
 * remaining to any external instances.
 * <p>
 * Overriding of this class is permitted to allow for different handling of SAX
 * events. Once you have created a subclass of this, you also need to create a
 * custom implementation of {@link SAXHandlerFactory} to supply your instances
 * to {@link SAXBuilder}
 * <p>
 * If the XMLReader producing the SAX Events supports a document Locator, then
 * this instance will use the locator to supply the line and column data from
 * the SAX locator to the JDOMFactory. <strong>Note:</strong> the SAX
 * specification for the SAX Locator indicates that the line and column
 * represent the position of the <strong>end</strong> of the SAX Event. For
 * example, the line and column of the simple XML <code>&lt;root /&gt;</code>
 * would be line 1, column 9.
 * 
 * @see org.jdom2.input.sax
 * @author Brett McLaughlin
 * @author Jason Hunter
 * @author Philip Nelson
 * @author Bradley S. Huffman
 * @author phil@triloggroup.com
 * @author Rolf Lear
 

api document of method startElement(String, String, String, Attributes): 

	 * This reports the occurrence of an actual element. It will include the
	 * element's attributes, with the exception of XML vocabulary specific
	 * attributes, such as <code>xmlns:[namespace prefix]</code> and
	 * <code>xsi:schemaLocation</code>.
	 * 
	 * @param namespaceURI
	 *        <code>String</code> namespace URI this element is associated with,
	 *        or an empty <code>String</code>
	 * @param localName
	 *        <code>String</code> name of element (with no namespace prefix, if
	 *        one is present)
	 * @param qName
	 *        <code>String</code> XML 1.0 version of element name: [namespace
	 *        prefix]:[localName]
	 * @param atts
	 *        <code>Attributes</code> list for this element
	 * @throws SAXException
	 *         when things go wrong
	 

dependent classes: 
class `org.jdom2.input.sax.SAXHandler`:
	dependent fields:
		boolean org.jdom2.input.sax.SAXHandler.atRoot;
		org.jdom2.JDOMFactory org.jdom2.input.sax.SAXHandler.factory;
		org.jdom2.Element org.jdom2.input.sax.SAXHandler.currentElement;
		org.xml.sax.Locator org.jdom2.input.sax.SAXHandler.currentLocator;
		boolean org.jdom2.input.sax.SAXHandler.suppress;
		org.jdom2.Document org.jdom2.input.sax.SAXHandler.currentDocument;
		java.util.List<org.jdom2.Namespace> org.jdom2.input.sax.SAXHandler.declaredNamespaces;
	related functions:
		method `public void processingInstruction(String, String)` returns `void`, related with `org.jdom2.input.sax.SAXHandler.startElement(String, String, String, Attributes)`, api document: 
	 * This will indicate that a processing instruction has been encountered.
	 * (The XML declaration is not a processing instruction and will not be
	 * reported.)
	 * 
	 * @param target
	 *        <code>String</code> target of PI
	 * @param data
	 *        <code>String</code> containing all data sent to the PI. This
	 *        typically looks like one or more attribute value pairs.
	 * @throws SAXException
	 *         when things go wrong
	 ;
		method `public void startDTD(String, String, String)` returns `void`, related with `org.jdom2.input.sax.SAXHandler.startElement(String, String, String, Attributes)`, api document: 
	 * This will signify that a DTD is being parsed, and can be used to ensure
	 * that comments and other lexical structures in the DTD are not added to
	 * the JDOM <code>Document</code> object.
	 * 
	 * @param name
	 *        <code>String</code> name of element listed in DTD
	 * @param publicID
	 *        <code>String</code> public ID of DTD
	 * @param systemID
	 *        <code>String</code> system ID of DTD
	 ;
		method `public void endElement(String, String, String)` returns `void`, related with `org.jdom2.input.sax.SAXHandler.startElement(String, String, String, Attributes)`, api document: 
	 * Indicates the end of an element (<code>&lt;/[element name]&gt;</code>) is
	 * reached. Note that the parser does not distinguish between empty elements
	 * and non-empty elements, so this will occur uniformly.
	 * 
	 * @param namespaceURI
	 *        <code>String</code> URI of namespace this element is associated
	 *        with
	 * @param localName
	 *        <code>String</code> name of element without prefix
	 * @param qName
	 *        <code>String</code> name of element in XML 1.0 form
	 * @throws SAXException
	 *         when things go wrong
	 ;
		method `protected void pushElement(Element)` returns `void`, related with `org.jdom2.input.sax.SAXHandler.startElement(String, String, String, Attributes)`, api document: 
	 * Pushes an element onto the tree under construction. Allows subclasses to
	 * put content under a dummy root element which is useful for building
	 * content that would otherwise be a non-well formed document.
	 * 
	 * @param element
	 *        root element under which content will be built
	 ;
		method `public void skippedEntity(String)` returns `void`, related with `org.jdom2.input.sax.SAXHandler.startElement(String, String, String, Attributes)`, api document: 
	 * This indicates that an unresolvable entity reference has been
	 * encountered, normally because the external DTD subset has not been read.
	 * 
	 * @param name
	 *        <code>String</code> name of entity
	 * @throws SAXException
	 *         when things go wrong
	 ;
		method `public final void reset()` returns `void`, related with `org.jdom2.input.sax.SAXHandler.startElement(String, String, String, Attributes)`, api document: 
	 * Restore this SAXHandler to a clean state ready for another parse round.
	 * All internal variables are cleared to an initialized state, and then the
	 * resetSubClass() method is called to clear any methods that a subclass may
	 * need to have reset.
	 ;
		method `public void comment(char[], int, int)` returns `void`, related with `org.jdom2.input.sax.SAXHandler.startElement(String, String, String, Attributes)`, api document: 
	 * This reports that a comments is parsed. If not in the DTD, this comment
	 * is added to the current JDOM <code>Element</code>, or the
	 * <code>Document</code> itself if at that level.
	 * 
	 * @param ch
	 *        <code>ch[]</code> array of comment characters.
	 * @param start
	 *        <code>int</code> index to start reading from.
	 * @param length
	 *        <code>int</code> length of data.
	 * @throws SAXException
	 *         if the state of the handler disallows this call
	 ;
		method `public void startEntity(String)` returns `void`, related with `org.jdom2.input.sax.SAXHandler.startElement(String, String, String, Attributes)`;
class `org.jdom2.input.DOMBuilder`:
	related functions:
		method `private void buildTree(org.w3c.dom.Node, Document, Element, boolean)` returns `void`, related with `org.jdom2.input.sax.SAXHandler.startElement(String, String, String, Attributes)`, api document: 
	 * This takes a DOM <code>Node</code> and builds up
	 * a JDOM tree, recursing until the DOM tree is exhausted
	 * and the JDOM tree results.
	 *
	 * @param node <code>Code</node> to examine.
	 * @param doc JDOM <code>Document</code> being built.
	 * @param current <code>Element</code> that is current parent.
	 * @param atRoot <code>boolean</code> indicating whether at root level.
	 ;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.