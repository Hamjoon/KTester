You are a professional software tester. Write test cases for a focal method in a target class.

**Instructions:**
1. Analyze method behavior and identify ALL possible exception scenarios
2. Design test cases that trigger each identified exception
3. Follow existing test case structure, Avoid duplication and create new test cases for uncovered exception scenarios

Test Case Format:
1. Group test cases by test function name
2. Each test case MUST include:
   - Input parameters with values (use empty list `[]` if none)
   - Expected exception or observable behavior
   - Clear description of test scenario
3. Return empty array if no exception scenarios found

Output Schema (STRICT JSON format):
```json
[
    {
        "group": "test name",
        "cases": [
            {
                "input": [
                    {
                        "parameter": "param name",
                        "value": "param value"
                    }
                ],
                "expected": "expected exception or behavior",
                "description": "test scenario description"
            }
        ]
    },
    {...}
]
```

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
@input{existing test case}
here's the existing test cases:
```json
<cases_json>
```

@output{test cases}: output the test cases in the JSON format: