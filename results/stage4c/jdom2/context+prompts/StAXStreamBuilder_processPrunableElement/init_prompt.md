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
Here is the code of focal method, `processPrunableElement(JDOMFactory, XMLStreamReader, int, StAXFilter)`, with the focal class `StAXStreamBuilder_processPrunableElement_Test`:
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
 

parameters in constructors and focal method: 
class `org.jdom2.JDOMFactory`:
api document : 
 * An interface to be used by builders when constructing JDOM objects. The
 * <code>DefaultJDOMFactory</code> creates the standard top-level JDOM classes
 * (Element, Document, Comment, etc). Another implementation of this factory
 * could be used to create custom classes.
 *
 * @author  Ken Rune Holland
 * @author  Phil Nelson
 * @author  Bradley S. Huffman
 * @author  Rolf Lear
 
constructor:
```java
```
class `org.jdom2.input.stax.StAXFilter`:
api document : 
 * In StAX Processing it is possible to read fragments of XML. JDOM supports
 * reading JDOM Content from StAX Readers in fragments. JDOM users can influence
 * the content that is processed by the return values in this interface.
 * <p>
 * Using the StAXStreamBuilder or StAXEventBuilder you can parse a List of
 * JDOM content by filtering that content with an instance of this filter.
 * <p>
 * There are two significant states in which methods in this interface will be
 * called:
 * <ul>
 * <li> We are not currently including any content, and we want to know whether
 *      the current StAX content should be included.
 * <li> We are currently inside an Element that this filter has indicated should
 *      be included, but perhaps you want to prune some content.
 * </ul>
 *  
 * @author Rolf Lear
 *
 
constructor:
```java
```

invoke examples of focal method: 
example 1:
```java
	public List<Content> buildFragments(XMLStreamReader reader, StAXFilter filter) throws JDOMException {
		return processFragments(builderfactory, reader, filter);
	}
	private List<Content> processFragments(JDOMFactory factory, XMLStreamReader stream, StAXFilter filter) throws JDOMException {
		
		int state = stream.getEventType();

		if (START_DOCUMENT != state) {
			throw new JDOMException("JDOM requires that XMLStreamReaders " +
					"are at their beginning when being processed.");
		}
		List<Content> ret = new ArrayList<Content>();
		
		int depth = 0;
		String text = null;
		
		try {
			while (stream.hasNext() && (state = stream.next()) != END_DOCUMENT) {
				switch (state) {
					case START_DOCUMENT:
						throw new JDOMException("Illegal state for XMLStreamReader. Cannot get XML Fragment for state START_DOCUMENT" );
					case END_DOCUMENT:
						throw new JDOMException("Illegal state for XMLStreamReader. Cannot get XML Fragment for state END_DOCUMENT" );
					case END_ELEMENT:
						throw new JDOMException("Illegal state for XMLStreamReader. Cannot get XML Fragment for state END_ELEMENT" );

					case START_ELEMENT:
						final QName qn = stream.getName();
						if (filter.includeElement(depth, qn.getLocalPart(), 
								Namespace.getNamespace(qn.getPrefix(), qn.getNamespaceURI()))) {
							ret.add(processPrunableElement(factory, stream, depth, filter));
	}
```



@input{test class template}
```java
package org.jdom2.input;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
// Add other imports if necessary

@ExtendWith(MockitoExtension.class)
class StAXStreamBuilder_processPrunableElement_Test {
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