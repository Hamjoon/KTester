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
Here is the code of focal method, `next()`, with the focal class `AbstractFormattedWalker`:
```Java
package org.jdom2.output.support;
import org.jdom2.internal.ArrayCopy;
import org.jdom2.Content;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.List;
import org.jdom2.output.EscapeStrategy;
import org.jdom2.output.Format;
import org.jdom2.CDATA;
public abstract class AbstractFormattedWalker implements Walker {
    private Content pending = null;
    private final Iterator<? extends Content> content;
    private final String newlineindent;
    private final FormatStack fstack;
    private boolean hasnext = true;
    private MultiText multitext = null;
    private MultiText pendingmt = null;
    private final MultiText holdingmt = new MultiText();
    private int mtsize = 0;
    private int mtsourcesize = 0;
    private Content[] mtdata = new Content[8];
    private String[] mttext = new String[8];
    private int mtpos = -1;
    private Boolean mtwasescape;
        /**
     * Collect together the items that constitute formatted Text-like content.
     *
     * @author Rolf Lear
     */
    protected final class MultiText {
        /**
         * This is private so only this abstract class can create instances.
         */
        private MultiText() {
        }
    
        /**
         * Ensure we have space for at least one more text-like item.
         */
        private void ensurespace() {
            if (mtsize >= mtdata.length) {
                mtdata = ArrayCopy.copyOf(mtdata, (mtsize + 1) + (mtsize / 2));
                mttext = ArrayCopy.copyOf(mttext, mtdata.length);
            }
        }
    
        /**
         * Handle the case where we have been accumulating true text content,
         * and the next item is not more text.
         *
         * @param postspace
         * 		true if the last char in the text should be a space
         */
        private void closeText() {
            if (mtbuffer.length() == 0) {
                // empty text does not need adding at all.
                return;
            }
            ensurespace();
            mtdata[mtsize] = null;
            mttext[mtsize++] = mtbuffer.toString();
            mtbuffer.setLength(0);
        }
    
        /**
         * Append some text to the text-like sequence that will be treated as
         * plain XML text (PCDATA). If the last content added to this text-like
         * sequence then this new text will be appended directly to the previous
         * text.
         *
         * @param trim
         * 		How to prepare the Text content
         * @param text
         * 		The actual Text content.
         */
        public void appendText(final Trim trim, final String text) {
            final int tlen = text.length();
            if (tlen == 0) {
                return;
            }
            String toadd = null;
            switch (trim) {
                case NONE :
                    toadd = text;
                    break;
                case BOTH :
                    toadd = Format.trimBoth(text);
                    break;
                case LEFT :
                    toadd = Format.trimLeft(text);
                    break;
                case RIGHT :
                    toadd = Format.trimRight(text);
                    break;
                case COMPACT :
                    toadd = Format.compact(text);
                    break;
            }
            if (toadd != null) {
                toadd = escapeText(toadd);
                mtbuffer.append(toadd);
                mtgottext = true;
            }
        }
    
        private String escapeText(final String text) {
            if ((escape == null) || (!fstack.getEscapeOutput())) {
                return text;
            }
            return Format.escapeText(escape, endofline, text);
        }
    
        private String escapeCDATA(final String text) {
            if (escape == null) {
                return text;
            }
            return text;
        }
    
        /**
         * Append some text to the text-like sequence that will be treated as
         * CDATA.
         *
         * @param trim
         * 		How to prepare the CDATA content
         * @param text
         * 		The actual CDATA content.
         */
        public void appendCDATA(final Trim trim, final String text) {
            // this resets the mtbuffer too.
            closeText();
            String toadd = null;
            switch (trim) {
                case NONE :
                    toadd = text;
                    break;
                case BOTH :
                    toadd = Format.trimBoth(text);
                    break;
                case LEFT :
                    toadd = Format.trimLeft(text);
                    break;
                case RIGHT :
                    toadd = Format.trimRight(text);
                    break;
                case COMPACT :
                    toadd = Format.compact(text);
                    break;
            }
            toadd = escapeCDATA(toadd);
            ensurespace();
            // mark this as being CDATA text
            mtdata[mtsize] = CDATATOKEN;
            mttext[mtsize++] = toadd;
            mtgottext = true;
        }
    
        /**
         * Simple method that ensures the text is processed, regardless of
         * content, and is never escaped.
         *
         * @param text
         */
        private void forceAppend(final String text) {
            mtgottext = true;
            mtbuffer.append(text);
        }
    
        /**
         * Add some JDOM Content (typically an EntityRef) that will be treated
         * as part of the Text-like sequence.
         *
         * @param c
         * 		the content to add.
         */
        public void appendRaw(final Content c) {
            closeText();
            ensurespace();
            mttext[mtsize] = null;
            mtdata[mtsize++] = c;
            mtbuffer.setLength(0);
        }
    
        /**
         * Indicate that there is no further content to be added to the
         * text-like sequence.
         */
        public void done() {
            if (mtpostpad && (newlineindent != null)) {
                // this will be ignored if there was not some content.
                mtbuffer.append(newlineindent);
            }
            if (mtgottext) {
                closeText();
            }
            mtbuffer.setLength(0);
        }
    }
@Override
    public final Content next() {
        if (!hasnext) {
            throw new NoSuchElementException("Cannot walk off end of Content");
        }
        if ((multitext != null) && ((mtpos + 1) >= mtsize)) {
            // finished this multitext. need to move on.
            multitext = null;
            resetMultiText();
        }
        if (pendingmt != null) {
            // we have a multi-text pending from the last block
            // this will only be the case when the previous value was non-text.
            if ((mtwasescape != null) && (fstack.getEscapeOutput() != mtwasescape.booleanValue())) {
                // we calculated pending with one escape strategy, but it changed...
                // we need to recalculate it....
                mtsize = 0;
                mtwasescape = fstack.getEscapeOutput();
                analyzeMultiText(pendingmt, 0, mtsourcesize);
                pendingmt.done();
            }
            multitext = pendingmt;
            pendingmt = null;
        }
        if (multitext != null) {
            // OK, we have text-like content to push back.
            // and it still has values in it.
            // advance the cursor
            mtpos++;
            final Content ret = (mttext[mtpos] == null) ? mtdata[mtpos] : null;
            // we can calculate the hasnext
            hasnext = ((mtpos + 1) < mtsize) || (pending != null);
            // return null to indicate text content.
            return ret;
        }
        // non-text, increment and return content.
        final Content ret = pending;
        pending = (content.hasNext()) ? content.next() : null;
        // OK, we are returning some content.
        // we need to determine the state of the next loop.
        // cursor at this point has been advanced!
        if (pending == null) {
            hasnext = false;
        } else // there is some more content.
        // we need to inspect it to determine whether it is good
        if (isTextLike(pending)) {
            // calculate what this next text-like content looks like.
            pendingmt = buildMultiText(false);
            analyzeMultiText(pendingmt, 0, mtsourcesize);
            pendingmt.done();
            if (mtsize > 0) {
                hasnext = true;
            } else // all white text... perhaps we need indenting anyway.
            // buildMultiText has moved on the pending value....
            if ((pending != null) && (newlineindent != null)) {
                // yes, we need indenting.
                // redefine the pending.
                resetMultiText();
                pendingmt = holdingmt;
                pendingmt.forceAppend(newlineindent);
                pendingmt.done();
                hasnext = true;
            } else {
                pendingmt = null;
                hasnext = pending != null;
            }
        } else {
            // it is non-text content... we have more content.
            // but, we just returned non-text content. We may need to indent
            if (newlineindent != null) {
                resetMultiText();
                pendingmt = holdingmt;
                pendingmt.forceAppend(newlineindent);
                pendingmt.done();
            }
            hasnext = true;
        }
        return ret;
    }
protected abstract void analyzeMultiText(MultiText mtext, int offset, int len);;
    private final MultiText buildMultiText(final boolean first);
    protected final Content get(final int index);
    @Override
public final boolean hasNext();
    @Override
public final boolean isAllText();
    @Override
public final boolean isAllWhitespace();
    @Override
public final boolean isCDATA();
    private final boolean isTextLike(final Content c);
    private void resetMultiText();
    @Override
public final String text();
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

api document of class org.jdom2.output.support.AbstractFormattedWalker: 

 * This Walker implementation walks a list of Content in a Formatted form of
 * some sort.
 * <p>
 * The JDOM content can be loosely categorised in to 'Text-like' content
 * (consisting of Text, CDATA, and EntityRef), and everything else. This
 * distinction is significant for for this class and it's sub-classes.
 * <p>
 * There will be text manipulation, and some (but not necessarily 
 * all) Text-like content will be returned as text() instead of next().
 * <p>
 * The trick in this class is that it deals with the regular content, and
 * delegates the Text-like content to the sub-classes.
 * <p>
 * Subclasses are tasked with analysing chunks of Text-like content in the
 * {@link #analyzeMultiText(MultiText, int, int)}  method. The subclasses are
 * responsible for adding the relevant text content to the suppliedMultiText
 * instance in such a way as to result in the correct format.
 * <p>
 * The Subclass needs to concern itself with only the text portion because this
 * abstract class will ensure the Text-like content is appropriately indented.
 * 
 * @author Rolf Lear
 

return type: 
org.jdom2.Content

dependent classes: 
class `org.jdom2.output.support.AbstractFormattedWalker`:
	dependent fields:
		int org.jdom2.output.support.AbstractFormattedWalker.mtpos;
		java.lang.Boolean org.jdom2.output.support.AbstractFormattedWalker.mtwasescape;
		org.jdom2.Content org.jdom2.output.support.AbstractFormattedWalker.pending;
		java.lang.String[] org.jdom2.output.support.AbstractFormattedWalker.mttext;
		org.jdom2.Content[] org.jdom2.output.support.AbstractFormattedWalker.mtdata;
		org.jdom2.output.support.AbstractFormattedWalker.MultiText org.jdom2.output.support.AbstractFormattedWalker.multitext;
		org.jdom2.output.support.AbstractFormattedWalker.MultiText org.jdom2.output.support.AbstractFormattedWalker.pendingmt;
		int org.jdom2.output.support.AbstractFormattedWalker.mtsize;
		int org.jdom2.output.support.AbstractFormattedWalker.mtsourcesize;
		boolean org.jdom2.output.support.AbstractFormattedWalker.hasnext;
		org.jdom2.output.support.FormatStack org.jdom2.output.support.AbstractFormattedWalker.fstack;
		java.util.Iterator<? extends org.jdom2.Content> org.jdom2.output.support.AbstractFormattedWalker.content;
		org.jdom2.output.support.AbstractFormattedWalker.MultiText org.jdom2.output.support.AbstractFormattedWalker.holdingmt;
		java.lang.String org.jdom2.output.support.AbstractFormattedWalker.newlineindent;
	related functions:
		method `public final boolean isCDATA()` returns `boolean`, related with `org.jdom2.output.support.AbstractFormattedWalker.next()`;
		method `public final String text()` returns `java.lang.String`, related with `org.jdom2.output.support.AbstractFormattedWalker.next()`;
		method `private void resetMultiText()` returns `void`, related with `org.jdom2.output.support.AbstractFormattedWalker.next()`;
		method `private final MultiText buildMultiText(boolean)` returns `org.jdom2.output.support.AbstractFormattedWalker.MultiText`, related with `org.jdom2.output.support.AbstractFormattedWalker.next()`, api document: 
	 * This method was changed in 2.0.5
	 * It now is only called when building the content of the variable pendingmt
	 * This is important, because only pendingmt can be referenced when analyzing
	 * the MultiText content.
	 * @param first
	 * @return The updated MultiText containing the correct sequence of Text-like content
	 ;
class `org.jdom2.Element`:
	related functions:
		method `public Element getChild(String, Namespace)` returns `org.jdom2.Element`, related with `org.jdom2.output.support.AbstractFormattedWalker.next()`, api document: 
	 * This returns the first child element within this element with the
	 * given local name and belonging to the given namespace.
	 * If no elements exist for the specified name and namespace, null is
	 * returned.
	 *
	 * @param cname local name of child element to match
	 * @param ns <code>Namespace</code> to search within. A null implies Namespace.NO_NAMESPACE.
	 * @return the first matching child element, or null if not found
	 ;
class `org.jdom2.DescendantIterator`:
	related functions:
		method `public Content next()` returns `org.jdom2.Content`, related with `org.jdom2.output.support.AbstractFormattedWalker.next()`, api document: 
	 * Returns the next {@link Content} descendant.
	 *
	 * @return the next descendant
	 ;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.