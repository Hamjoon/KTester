As a professional Java software testing expert, your task is to generate test cases for a focal method.

You will be provided with:
1. The focal method that needs testing.
2. Basic information of the target class to which the focus method belongs, including fields and methods.

Ensure the generated test cases meet the following requirements:

Test Coverage Requirements:
- Create test cases to cover all conditional branches in the target method, including `if`, `else if`, `else`, `switch`, and `case` statements.

Output Format:
- Organize the test cases by its functions logically.
- Include input parameters, expected output, and a description for each test case.
- Use empty list `[]` for the input when a test case does not require input parameters.
- Provide an expected return value for each test case, or specify the behavior that should be verified if there's no return value.
- Clearly describe the intention of each test case in the description.

Follow the JSON format provided here for the output:
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

@output{test cases}: output the test cases in the JSON format: