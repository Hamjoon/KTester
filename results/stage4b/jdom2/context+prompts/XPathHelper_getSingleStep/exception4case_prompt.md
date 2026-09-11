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
@input{existing test case}
here's the existing test cases:
```json
<cases_json>
```

@output{test cases}: output the test cases in the JSON format: