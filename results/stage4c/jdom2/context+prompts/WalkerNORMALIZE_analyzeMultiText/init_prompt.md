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
Here is the code of focal method, `analyzeMultiText(MultiText, int, int)`, with the focal class `WalkerNORMALIZE_analyzeMultiText_Test`:
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


@input{context information}

api document of class org.jdom2.output.support.WalkerNORMALIZE: 

 * This Walker implementation will produce trimmed text content.
 * 
 * @author Rolf Lear
 *
 

constructors for class `org.jdom2.output.support.WalkerNORMALIZE`: 
params: org.jdom2.output.support.FormatStack fstack 
body:
```java
	public WalkerNORMALIZE(final List<? extends Content> content, 
			final FormatStack fstack, final boolean escape) {
		super(content, fstack, escape);
	}
	
```

parameters in constructors and focal method: 
class `org.jdom2.output.support.FormatStack`:
api document : 
 * FormatStack implements a mechanism where the formatting details can be
 * changed mid-tree, but then get reverted when that tree segment is
 * complete.
 * <p>
 * This class is intended as a working-class for in the various outputter
 * implementations. It is inly public so that people extending the
 * Abstract*Processor classes can take advantage of it's functionality.
 * <p>
 * The value this class adds is:
 * <ul>
 * <li>Fast - 
 * </ul>
 * 
 * @since JDOM2
 * @author Rolf Lear
 
constructor:
```java
	public FormatStack(Format format) {
		indent = format.getIndent();
		lineSeparator = format.getLineSeparator();

		encoding = format.getEncoding();
		omitDeclaration = format.getOmitDeclaration();
		omitEncoding = format.getOmitEncoding();
		expandEmptyElements = format.getExpandEmptyElements();
		escapeStrategy = format.getEscapeStrategy();
		defaultMode = format.getTextMode();
		specifiedAttributesOnly = format.isSpecifiedAttributesOnly();

        mode[depth] = format.getTextMode();
        if (mode[depth] == TextMode.PRESERVE) {
            // undo any special indenting and end-of-line management:
            levelIndent[depth] = null;
            levelEOL[depth] = null;
            levelEOLIndent[depth] = null;
            termEOLIndent[depth] = null;
        } else {
            levelIndent[depth] = format.getIndent() == null
                    ? null : "";
            levelEOL[depth] = format.getLineSeparator();
            levelEOLIndent[depth] = levelIndent[depth] == null ?  
                    null : levelEOL[depth];
            termEOLIndent[depth] = levelEOLIndent[depth];
            
        }
		ignoreTrAXEscapingPIs[depth] = format.getIgnoreTrAXEscapingPIs();
		escapeOutput[depth] = true;
	}

```
class `org.jdom2.output.support.AbstractFormattedWalker.MultiText`:
api document : 
	 * Collect together the items that constitute formatted Text-like content.
	 * 
	 * @author Rolf Lear
	 *
	 
constructor:
```java
		private MultiText() {
		}
		
```



@input{test class template}
```java
package org.jdom2.output.support;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
// Add other imports if necessary

@ExtendWith(MockitoExtension.class)
class WalkerNORMALIZE_analyzeMultiText_Test {
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