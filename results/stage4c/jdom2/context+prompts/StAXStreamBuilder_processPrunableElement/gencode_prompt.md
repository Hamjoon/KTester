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
Here is the code of focal method, `processPrunableElement(JDOMFactory, XMLStreamReader, int, StAXFilter)`, with the focal class `StAXStreamBuilder`:
```Java
package org.jdom2.input;
import org.jdom2.input.stax.StAXFilter;
import javax.xml.stream.XMLStreamReader;
import org.jdom2.JDOMException;
import org.jdom2.Element;
import java.util.ArrayList;
import javax.xml.namespace.QName;
import org.jdom2.JDOMFactory;
import org.jdom2.Namespace;
import org.jdom2.Document;
import org.jdom2.Verifier;
import org.jdom2.DefaultJDOMFactory;
import javax.xml.stream.XMLStreamException;
import org.jdom2.Content;
import org.jdom2.AttributeType;
import java.util.List;
import org.jdom2.input.stax.DTDParser;
import static javax.xml.stream.XMLStreamConstants.*;
public class StAXStreamBuilder {
    
    private static final Element processPrunableElement(final JDOMFactory factory, final XMLStreamReader reader, final int topdepth, StAXFilter filter) throws XMLStreamException, JDOMException {
        if (START_ELEMENT != reader.getEventType()) {
            throw new JDOMException(("JDOM requires that the XMLStreamReader " + "is at the START_ELEMENT state when retrieving an ") + "Element Fragment.");
        }
        final Element fragment = processElement(factory, reader);
        Element current = fragment;
        int depth = topdepth + 1;
        String text = null;
        while ((depth > topdepth) && reader.hasNext()) {
            switch (reader.next()) {
                case START_ELEMENT :
                    QName qn = reader.getName();
                    if (!filter.pruneElement(depth, qn.getLocalPart(), Namespace.getNamespace(qn.getPrefix(), qn.getNamespaceURI()))) {
                        Element tmp = processElement(factory, reader);
                        current.addContent(tmp);
                        current = tmp;
                        depth++;
                    } else {
                        final int edepth = depth;
                        depth++;
                        int state = 0;
                        while (((depth > edepth) && reader.hasNext()) && ((state = reader.next()) != END_DOCUMENT)) {
                            if (state == START_ELEMENT) {
                                depth++;
                            } else if (state == END_ELEMENT) {
                                depth--;
                            }
                        } 
                    }
                    break;
                case END_ELEMENT :
                    current = current.getParentElement();
                    depth--;
                    break;
                case CDATA :
                    if ((text = filter.pruneCDATA(depth, reader.getText())) != null) {
                        current.addContent(factory.cdata(text));
                    }
                    break;
                case SPACE :
                case CHARACTERS :
                    if ((text = filter.pruneText(depth, reader.getText())) != null) {
                        current.addContent(factory.text(text));
                    }
                    break;
                case COMMENT :
                    if ((text = filter.pruneComment(depth, reader.getText())) != null) {
                        current.addContent(factory.comment(text));
                    }
                    break;
                case ENTITY_REFERENCE :
                    if (!filter.pruneEntityRef(depth, reader.getLocalName())) {
                        current.addContent(factory.entityRef(reader.getLocalName()));
                    }
                    break;
                case PROCESSING_INSTRUCTION :
                    if (!filter.pruneProcessingInstruction(depth, reader.getPITarget())) {
                        current.addContent(factory.processingInstruction(reader.getPITarget(), reader.getPIData()));
                    }
                    break;
                default :
                    throw new JDOMException("Unexpected XMLStream event " + reader.getEventType());
            }
        } 
        return fragment;
    }
public Document build(XMLStreamReader reader) throws JDOMException;
    public List<Content> buildFragments(XMLStreamReader reader, StAXFilter filter) throws JDOMException;
    public Content fragment(XMLStreamReader reader) throws JDOMException;
    public JDOMFactory getFactory();
    private static final Element processElement(final JDOMFactory factory, final XMLStreamReader reader);
    public void setFactory(JDOMFactory factory);
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

api document of class org.jdom2.input.StAXStreamBuilder: 

 * Builds a JDOM Document from a StAX-based XMLStreamReader.
 * <p>
 * XMLStreamReaders are pre-configured and as a result JDOM is not able to
 * alter whether the input is validated, or whether the Stream has escaped
 * entities or not. These (and other) characteristics are configurable by
 * setting the correct features and properties on the XMLInputFactory when it
 * is used to create the XMLStreamReader.
 * <p>
 * Useful configuration to set, or know about is:
 * <ul>
 * <li>StAX streams seldom differentiate between Text and CDATA content. You
 * will likely want to configure your StAX factory (XMLInputFactory) with
 * <code>http://java.sun.com/xml/stream/properties/report-cdata-event</code>
 * for the default Java StAX implementation, or the equivalent property for your
 * StAX engine.
 * <li>The remaining XMLInputFactory settings are likely to work fine at their
 * default values.
 * <li>StAX is not likely to be your best option if you want a validating
 * parser, at least not with the default (built-in Java implementation in Java6 
 * which does not support it). Consider a SAX parser.
 * </ul>
 * <p>
 * From a JDOM perspective XMLStreamReaders are more efficient than 
 * XMLEventReaders. Where possible use an XMLStreamReader.
 * <p>
 * If you happen to be looking at the source code, pay careful attention to the
 * imports so you know what type of instance is being processed, whether it is
 * a StAX class, or a JDOM class, because there are name conflicts.
 * 
 * @author Rolf Lear
 *
 

return type: 
org.jdom2.Element

dependent classes: 
class `org.jdom2.JDOMFactory`:
	
class `org.jdom2.input.stax.StAXFilter`:
	
class `org.jdom2.input.StAXStreamBuilder`:
	related functions:
		method `private static final Content processFragment(JDOMFactory, XMLStreamReader)` returns `org.jdom2.Content`, related with `org.jdom2.input.StAXStreamBuilder.processPrunableElement(JDOMFactory, XMLStreamReader, int, StAXFilter)`, api document: 
	 * Create a Content from an XMLStreamReader
	 * The stream is advanced to the event after the current event (or to the
	 * event after the matching END_ELEMENT for an Element fragment).
	 * @param factory The {@link JDOMFactory} to use
	 * @param stream The XMLStreamReader to read from
	 * @return the parsed Document
	 * @throws JDOMException if there is any issue
	 * 				(XMLStreamExceptions are wrapped).
	 ;
		method `private List<Content> processFragments(JDOMFactory, XMLStreamReader, StAXFilter)` returns `java.util.List<org.jdom2.Content>`, related with `org.jdom2.input.StAXStreamBuilder.processPrunableElement(JDOMFactory, XMLStreamReader, int, StAXFilter)`;
		method `private static final Element processElementFragment(JDOMFactory, XMLStreamReader)` returns `org.jdom2.Element`, related with `org.jdom2.input.StAXStreamBuilder.processPrunableElement(JDOMFactory, XMLStreamReader, int, StAXFilter)`;
		method `private static final Document process(JDOMFactory, XMLStreamReader)` returns `org.jdom2.Document`, related with `org.jdom2.input.StAXStreamBuilder.processPrunableElement(JDOMFactory, XMLStreamReader, int, StAXFilter)`, api document: 
	 * Create a Document from an XMLStreamReader
	 * @param factory The {@link JDOMFactory} to use
	 * @param stream The XMLStreamReader to read from
	 * @return the parsed Document
	 * @throws JDOMException if there is any issue
	 * 				(XMLStreamExceptions are wrapped).
	 ;
		method `private static final Element processElement(JDOMFactory, XMLStreamReader)` returns `org.jdom2.Element`, related with `org.jdom2.input.StAXStreamBuilder.processPrunableElement(JDOMFactory, XMLStreamReader, int, StAXFilter)`;
class `org.jdom2.input.StAXEventBuilder`:
	related functions:
		method `private static final Document process(JDOMFactory, XMLEventReader)` returns `org.jdom2.Document`, related with `org.jdom2.input.StAXStreamBuilder.processPrunableElement(JDOMFactory, XMLStreamReader, int, StAXFilter)`, api document: 
	 * Create a Document from an XMLEventReader
	 * @param factory the {@link JDOMFactory} to use
	 * @param stream the XMLEventReader to read from
	 * @return the parsed Document
	 * @throws JDOMException if there is any issue
	 * 				(XMLStreamExceptions are wrapped).
	 ;
		method `private static final Element processElement(JDOMFactory, StartElement)` returns `org.jdom2.Element`, related with `org.jdom2.input.StAXStreamBuilder.processPrunableElement(JDOMFactory, XMLStreamReader, int, StAXFilter)`;
class `org.jdom2.input.sax.SAXHandler`:
	related functions:
		method `protected void flushCharacters(String)` returns `void`, related with `org.jdom2.input.StAXStreamBuilder.processPrunableElement(JDOMFactory, XMLStreamReader, int, StAXFilter)`, api document: 
	 * Flush the given string into the document. This is a protected method so
	 * subclassers can control text handling without knowledge of the internals
	 * of this class.
	 * 
	 * @param data
	 *        string to flush
	 * @throws SAXException
	 *         if the state of the handler does not allow this.
	 ;
class `org.jdom2.Namespace`:
	related functions:
		method `public static Namespace getNamespace(String)` returns `org.jdom2.Namespace`, related with `org.jdom2.input.StAXStreamBuilder.processPrunableElement(JDOMFactory, XMLStreamReader, int, StAXFilter)`, api document: 
	 * This will retrieve (if in existence) or create (if not) a 
	 * <code>Namespace</code> for the supplied URI, and make it usable 
	 * as a default namespace, as no prefix is supplied.
	 * This method is thread-safe.
	 *
	 * @param uri <code>String</code> URI of new <code>Namespace</code>.
	 * @return <code>Namespace</code> - ready to use namespace.
	 ;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.