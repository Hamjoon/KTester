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
Here is the code of focal method, `escapeText(EscapeStrategy, String, String)`, with the focal class `Format_escapeText_Test`:
```Java
package org.jdom2.output;
import org.jdom2.IllegalDataException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import org.jdom2.Verifier;
public class Format implements Cloneable {
    String indent = null;
    String lineSeparator = STANDARD_LINE_SEPARATOR;
    String encoding = STANDARD_ENCODING;
    boolean omitDeclaration = false;
    boolean omitEncoding = false;
    boolean specifiedAttributesOnly = false;
    boolean expandEmptyElements = false;
    boolean ignoreTrAXEscapingPIs = false;
    TextMode mode = TextMode.PRESERVE;
    EscapeStrategy escapeStrategy = DefaultEscapeStrategy;
    public static final String escapeText(final EscapeStrategy strategy, final String eol, final String value) {
        final int right = value.length();
        int idx = 0;
        checkloop : while (idx < right) {
            final char ch = value.charAt(idx);
            if ((((((ch == '<') || (ch == '>')) || (ch == '&')) || (ch == '\r')) || (ch == '\n')) || strategy.shouldEscape(ch)) {
                break checkloop;
            }
            idx++;
        } 
        if (idx == right) {
            // no escape needed.
            return value;
        }
        StringBuilder sb = new StringBuilder();
        if (idx > 0) {
            sb.append(value, 0, idx);
        }
        char highsurrogate = 0;
        while (idx < right) {
            final char ch = value.charAt(idx++);
            if (highsurrogate > 0) {
                if (!Verifier.isLowSurrogate(ch)) {
                    throw new IllegalDataException((("Could not decode surrogate pair 0x" + Integer.toHexString(highsurrogate)) + " / 0x") + Integer.toHexString(ch));
                }
                int chp = Verifier.decodeSurrogatePair(highsurrogate, ch);
                sb.append(("&#x" + Integer.toHexString(chp)) + ";");
                highsurrogate = 0;
                continue;
            }
            switch (ch) {
                case '<' :
                    sb.append("&lt;");
                    break;
                case '>' :
                    sb.append("&gt;");
                    break;
                case '&' :
                    sb.append("&amp;");
                    break;
                case '\r' :
                    sb.append("&#xD;");
                    break;
                case '\n' :
                    if (eol != null) {
                        sb.append(eol);
                    } else {
                        sb.append('\n');
                    }
                    break;
                default :
                    if (strategy.shouldEscape(ch)) {
                        // make sure what we are escaping is not the
                        // beginning of a multi-byte character.
                        if (Verifier.isHighSurrogate(ch)) {
                            // this is a the high of a surrogate pair
                            highsurrogate = ch;
                        } else {
                            sb.append(("&#x" + Integer.toHexString(ch)) + ";");
                        }
                    } else {
                        sb.append(ch);
                    }
                    break;
            }
        } 
        if (highsurrogate > 0) {
            throw new IllegalDataException(("Surrogate pair 0x" + Integer.toHexString(highsurrogate)) + "truncated");
        }
        return sb.toString();
    }
@Override
public Format clone();
    public static final String compact(String str);
    public static final String escapeAttribute(final EscapeStrategy strategy, final String value);
    public static Format getCompactFormat();
    public String getEncoding();
    public EscapeStrategy getEscapeStrategy();
    public boolean getExpandEmptyElements();
    public boolean getIgnoreTrAXEscapingPIs();
    public String getIndent();
    public String getLineSeparator();
    public boolean getOmitDeclaration();
    public boolean getOmitEncoding();
    public static Format getPrettyFormat();
    public static Format getRawFormat();
    public Format.TextMode getTextMode();
    public boolean isSpecifiedAttributesOnly();
    public Format setEncoding(String encoding);
    public Format setEscapeStrategy(EscapeStrategy strategy);
    public Format setExpandEmptyElements(boolean expandEmptyElements);
    public void setIgnoreTrAXEscapingPIs(boolean ignoreTrAXEscapingPIs);
    public Format setIndent(String indent);
    public Format setLineSeparator(String separator);
    public Format setLineSeparator(LineSeparator separator);
    public Format setOmitDeclaration(boolean omitDeclaration);
    public Format setOmitEncoding(boolean omitEncoding);
    public void setSpecifiedAttributesOnly(boolean specifiedAttributesOnly);
    public Format setTextMode(Format.TextMode mode);
    public static final String trimBoth(final String str);
    public static final String trimLeft(final String str);
    public static final String trimRight(String str);
}
```


@input{context information}

api document of class org.jdom2.output.Format: 

 * Class to encapsulate XMLOutputter format options.
 * Typically users adapt the standard format configurations obtained by
 * {@link #getRawFormat} (no whitespace changes),
 * {@link #getPrettyFormat} (whitespace beautification), and
 * {@link #getCompactFormat} (whitespace normalization).
 * <p>
 * Several modes are available to effect the way textual content is printed.
 * See the documentation for {@link TextMode} for details.
 * <p>
 * <b>Note about Line Separator:</b>
 * <p>
 * By default JDOM will always use the CRNL sequence "\r\n" for output. This
 * can be changed in a number of different ways. See the {@link LineSeparator}
 * enumeration for more information.
 * <p>
 * <b>Note about XML Character Escaping:</b>
 * <p>
 * JDOM will escape characters in the output based on the EscapeStrategy that
 * is specified by this Format. The Format will by default use a sensible
 * EscapeStrategy that is based on the character encoding of the output. If
 * the default escape mechanism is not producing the correct results you can
 * change the EscapeStrategy on the format to suit your own needs.  
 * 
 *
 * @see LineSeparator
 *
 * @author Jason Hunter
 * @author Rolf Lear
 

constructors for class `org.jdom2.output.Format`: 
params: 
body:
```java
	public Format() {
		setEncoding(STANDARD_ENCODING);
	}

```

parameters in constructors and focal method: 
class `org.jdom2.output.EscapeStrategy`:
api document : 
 * Logic to determine which characters should be formatted as character
 * entities.
 *
 * @author Alex Rosen
 * @author Bradley S. Huffman
 * @author Jason Hunter
 
constructor:
```java
```

invoke examples of focal method: 
example 1:
```java
		private String escapeText(final String text) {
			if (escape == null || !fstack.getEscapeOutput()) {
			return Format.escapeText(escape, endofline, text);
		}
```
example 2:
```java
		public final String escapeElementEntities(final String str,
				final Format format) {
			return Format.escapeText(format.getEscapeStrategy(),
					format.getLineSeparator(), str);
		}
```
example 3:
```java
	protected void printText(final Writer out, final FormatStack fstack,
			final Text text) throws IOException {
		if (fstack.getEscapeOutput()) {
			textRaw(out, Format.escapeText(fstack.getEscapeStrategy(),
					fstack.getLineSeparator(), text.getText()));
	}
```



@input{test class template}
```java
package org.jdom2.output;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
// Add other imports if necessary

@ExtendWith(MockitoExtension.class)
class Format_escapeText_Test {
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