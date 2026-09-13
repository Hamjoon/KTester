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
Here is the code of focal method, `analyzeMultiText(MultiText, int, int)`, with the focal class `WalkerNORMALIZE`:
```Java
package org.jdom2.output.support;
import org.jdom2.Content;
import java.util.List;
import org.jdom2.Verifier;
public class WalkerNORMALIZE extends AbstractFormattedWalker {
    
    @Override
    protected void analyzeMultiText(final MultiText mtext, final int offset, final int len) {
        boolean needspace = false;
        boolean between = false;
        String ttext = null;
        for (int i = 0; i < len; i++) {
            final Content c = get(offset + i);
            switch (c.getCType()) {
                case Text :
                    ttext = c.getValue();
                    if (Verifier.isAllXMLWhitespace(ttext)) {
                        if (between && (ttext.length() > 0)) {
                            needspace = true;
                        }
                    } else {
                        if (between && (needspace || isSpaceFirst(ttext))) {
                            mtext.appendText(Trim.NONE, " ");
                        }
                        mtext.appendText(Trim.COMPACT, ttext);
                        between = true;
                        needspace = isSpaceLast(ttext);
                    }
                    break;
                case CDATA :
                    ttext = c.getValue();
                    if (Verifier.isAllXMLWhitespace(ttext)) {
                        if (between && (ttext.length() > 0)) {
                            needspace = true;
                        }
                    } else {
                        if (between && (needspace || isSpaceFirst(ttext))) {
                            mtext.appendText(Trim.NONE, " ");
                        }
                        mtext.appendCDATA(Trim.COMPACT, ttext);
                        between = true;
                        needspace = isSpaceLast(ttext);
                    }
                    break;
                case EntityRef :
                    // treat like any other content.
                    // raw.
                default :
                    ttext = null;
                    if (between && needspace) {
                        mtext.appendText(Trim.NONE, " ");
                    }
                    mtext.appendRaw(c);
                    between = true;
                    needspace = false;
                    break;
            }
        }
    }
private boolean isSpaceFirst(String text);
    private boolean isSpaceLast(String text);
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

api document of class org.jdom2.output.support.WalkerNORMALIZE: 

 * This Walker implementation will produce trimmed text content.
 * 
 * @author Rolf Lear
 *
 

dependent classes: 
class `org.jdom2.output.support.AbstractFormattedWalker.MultiText`:
	
class `org.jdom2.output.support.AbstractFormattedWalker`:
	related functions:
		method `private final boolean isTextLike(Content)` returns `boolean`, related with `org.jdom2.output.support.WalkerNORMALIZE.analyzeMultiText(MultiText, int, int)`;
class `org.jdom2.Content.CType`:
	dependent fields:
		org.jdom2.Content.CType org.jdom2.Content.CType.CDATA;
		org.jdom2.Content.CType org.jdom2.Content.CType.EntityRef;
		org.jdom2.Content.CType org.jdom2.Content.CType.Text;
	
class `org.jdom2.output.support.AbstractFormattedWalker.Trim`:
	dependent fields:
		org.jdom2.output.support.AbstractFormattedWalker.Trim org.jdom2.output.support.AbstractFormattedWalker.Trim.NONE;
		org.jdom2.output.support.AbstractFormattedWalker.Trim org.jdom2.output.support.AbstractFormattedWalker.Trim.COMPACT;
	
class `org.jdom2.output.support.WalkerTRIM_FULL_WHITE`:
	related functions:
		method `protected void analyzeMultiText(MultiText, int, int)` returns `void`, related with `org.jdom2.output.support.WalkerNORMALIZE.analyzeMultiText(MultiText, int, int)`;
class `org.jdom2.output.support.WalkerTRIM`:
	related functions:
		method `protected void analyzeMultiText(MultiText, int, int)` returns `void`, related with `org.jdom2.output.support.WalkerNORMALIZE.analyzeMultiText(MultiText, int, int)`;
class `org.jdom2.output.support.AbstractDOMOutputProcessor`:
	related functions:
		method `protected org.w3c.dom.Node helperContentDispatcher(FormatStack, NamespaceStack, org.w3c.dom.Document, Content)` returns `org.w3c.dom.Node`, related with `org.jdom2.output.support.WalkerNORMALIZE.analyzeMultiText(MultiText, int, int)`, api document: 
	 * This method contains code which is reused in a number of places. It
	 * simply determines what content is passed in, and dispatches it to the
	 * correct print* method.
	 * 
	 * @param fstack
	 *        The current FormatStack
	 * @param nstack
	 *        the NamespaceStack
	 * @param basedoc
	 *        The org.w3c.dom.Document for creating DOM Nodes
	 * @param content
	 *        The content to dispatch
	 * @return the input JDOM Content converted to a DOM Node.
	 ;
class `org.jdom2.output.support.AbstractSAXOutputProcessor`:
	related functions:
		method `protected void printContent(SAXTarget, FormatStack, NamespaceStack, Walker)` returns `void`, related with `org.jdom2.output.support.WalkerNORMALIZE.analyzeMultiText(MultiText, int, int)`, api document: 
	 * This will handle printing of a List of {@link Content}.
	 * <p>
	 * It relies on the appropriate Walker to get the formatting right.
	 * 
	 * @param out
	 *        <code>SAXTarget</code> to use.
	 * @param fstack
	 *        the FormatStack
	 * @param nstack
	 *        the NamespaceStack
	 * @param walker
	 *        <code>Waker</code> of <code>Content</code> to write.
	 * @throws SAXException
	 *         if the destination SAXTarget fails
	 ;
class `org.jdom2.output.support.AbstractXMLOutputProcessor`:
	related functions:
		method `protected void printContent(Writer, FormatStack, NamespaceStack, Walker)` returns `void`, related with `org.jdom2.output.support.WalkerNORMALIZE.analyzeMultiText(MultiText, int, int)`, api document: 
	 * This will handle printing of a List of {@link Content}.
	 * <p>
	 * The list of Content is basically processed as one of three types of
	 * content
	 * <ol>
	 * <li>Consecutive text-type (Text, CDATA, and EntityRef) content
	 * <li>Stand-alone text-type content
	 * <li>Non-text-type content.
	 * </ol>
	 * Although the code looks complex, the theory is conceptually simple:
	 * <ol>
	 * <li>identify one of the three types (consecutive, stand-alone, non-text)
	 * <li>do indent if any is specified.
	 * <li>send the type to the respective print* handler (e.g.
	 * {@link #printCDATA(Writer, FormatStack, CDATA)}, or
	 * {@link #printComment(Writer, FormatStack, Comment)},
	 * <li>do a newline if one is specified.
	 * <li>loop back to 1. until there's no more content to process.
	 * </ol>
	 * 
	 * @param out
	 *        <code>Writer</code> to use.
	 * @param fstack
	 *        the FormatStack
	 * @param nstack
	 *        the NamespaceStack
	 * @param walker
	 *        {@link Walker} of <code>Content</code> to write.
	 * @throws IOException
	 *         if the destination Writer fails
	 ;
class `org.jdom2.output.support.AbstractStAXEventProcessor`:
	related functions:
		method `protected void printContent(XMLEventConsumer, FormatStack, NamespaceStack, XMLEventFactory, Walker)` returns `void`, related with `org.jdom2.output.support.WalkerNORMALIZE.analyzeMultiText(MultiText, int, int)`, api document: 
	 * This will handle printing of a List of {@link Content}.
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
	 * @param walker
	 *        <code>Walker</code> of <code>Content</code> to write.
	 * @throws XMLStreamException
	 *         if the destination XMLEventConsumer fails
	 ;
class `org.jdom2.output.SAXOutputter`:
	related functions:
		method `public void outputFragment(Content)` returns `void`, related with `org.jdom2.output.support.WalkerNORMALIZE.analyzeMultiText(MultiText, int, int)`, api document: 
	 * This will output a single JDOM nodes as a fragment of an XML document,
	 * firing off the SAX events that have been registered.
	 * <p>
	 * <strong>Warning</strong>: This method does not call the
	 * {@link ContentHandler#setDocumentLocator},
	 * {@link ContentHandler#startDocument} and
	 * {@link ContentHandler#endDocument} callbacks on the
	 * {@link #setContentHandler ContentHandler}. The user shall invoke these
	 * methods directly prior/after outputting the document fragments.
	 * </p>
	 * 
	 * @param node
	 *        the <code>Content</code> node to output.
	 * @throws JDOMException
	 *         if any error occurred.
	 * @see #outputFragment(java.util.List)
	 ;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.