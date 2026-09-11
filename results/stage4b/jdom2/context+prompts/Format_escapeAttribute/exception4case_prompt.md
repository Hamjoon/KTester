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
Here is the code of focal method, `escapeAttribute(EscapeStrategy, String)`, with the focal class `Format`:
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
    public static final String escapeAttribute(final EscapeStrategy strategy, final String value) {
        final int len = value.length();
        int idx = 0;
        checkloop : while (idx < len) {
            final char ch = value.charAt(idx);
            if ((((((((ch == '<') || (ch == '>')) || (ch == '&')) || (ch == '\r')) || (ch == '\n')) || (ch == '"')) || (ch == '\t')) || strategy.shouldEscape(ch)) {
                break checkloop;
            }
            idx++;
        } 
        if (idx == len) {
            return value;
        }
        char highsurrogate = 0;
        final StringBuilder sb = new StringBuilder(len + 5);
        sb.append(value, 0, idx);
        while (idx < len) {
            final char ch = value.charAt(idx++);
            if (highsurrogate > 0) {
                if (!Verifier.isLowSurrogate(ch)) {
                    throw new IllegalDataException((("Could not decode surrogate pair 0x" + Integer.toHexString(highsurrogate)) + " / 0x") + Integer.toHexString(ch));
                }
                int chp = Verifier.decodeSurrogatePair(highsurrogate, ch);
                sb.append("&#x");
                sb.append(Integer.toHexString(chp));
                sb.append(';');
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
                case '"' :
                    sb.append("&quot;");
                    break;
                case '\t' :
                    sb.append("&#x9;");
                    break;
                case '\n' :
                    sb.append("&#xA;");
                    break;
                default :
                    if (strategy.shouldEscape(ch)) {
                        // make sure what we are escaping is not the
                        // beginning of a multi-byte character.
                        if (Verifier.isHighSurrogate(ch)) {
                            // this is a the high of a surrogate pair
                            highsurrogate = ch;
                        } else {
                            sb.append("&#x");
                            sb.append(Integer.toHexString(ch));
                            sb.append(';');
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
    public static final String escapeText(final EscapeStrategy strategy, final String eol, final String value);
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
@input{existing test case}
here's the existing test cases:
```json
<cases_json>
```

@output{test cases}: output the test cases in the JSON format: