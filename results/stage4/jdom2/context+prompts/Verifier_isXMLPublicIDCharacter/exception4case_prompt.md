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
Here is the code of focal method, `isXMLPublicIDCharacter(char)`, with the focal class `Verifier`:
```Java
package org.jdom2;
import java.util.*;
public final class Verifier {
    
    public static boolean isXMLPublicIDCharacter(final char c) {
        // [13] PubidChar ::= #x20 | #xD | #xA | [a-zA-Z0-9] |
        // [-'()+,./:=?;*#@$_%]
        if ((c >= 'a') && (c <= 'z'))
            return true;
    
        if ((c >= '?') && (c <= 'Z'))
            return true;
    
        if ((c >= '\'') && (c <= ';'))
            return true;
    
        if (c == ' ')
            return true;
    
        if (c == '!')
            return true;
    
        if (c == '=')
            return true;
    
        if (c == '#')
            return true;
    
        if (c == '$')
            return true;
    
        if (c == '_')
            return true;
    
        if (c == '%')
            return true;
    
        if (c == '\n')
            return true;
    
        if (c == '\r')
            return true;
    
        if (c == '\t')
            return true;
    
        return false;
    }
public static String checkAttributeName(final String name);
    public static String checkCDATASection(final String data);
    public static String checkCharacterData(final String text);
    public static String checkCommentData(final String data);
    public static String checkElementName(final String name);
    public static String checkNamespaceCollision(final Attribute attribute, final Element element);
    public static String checkNamespaceCollision(final Attribute attribute, final Element element, final int ignoreatt);
    public static String checkNamespaceCollision(final Namespace namespace, final List<?> list);
    public static String checkNamespaceCollision(final Namespace namespace, final List<?> list, final int ignoreatt);
    public static String checkNamespaceCollision(final Namespace namespace, final Attribute attribute);
    public static String checkNamespaceCollision(final Namespace namespace, final Element element);
    public static String checkNamespaceCollision(final Namespace namespace, final Element element, final int ignoreatt);
    public static String checkNamespaceCollision(final Namespace namespace, final Namespace other);
    public static String checkNamespacePrefix(final String prefix);
    public static String checkNamespaceURI(final String uri);
    public static String checkProcessingInstructionData(final String data);
    public static String checkProcessingInstructionTarget(final String target);
    public static String checkPublicID(final String publicID);
    public static String checkSystemLiteral(final String systemLiteral);
    public static String checkURI(final String uri);
    public static String checkXMLName(final String name);
    public static int decodeSurrogatePair(final char high, final char low);
    public static final boolean isAllXMLWhitespace(final String value);
    public static boolean isHexDigit(final char c);
    public static boolean isHighSurrogate(final char ch);
    public static boolean isLowSurrogate(final char ch);
    public static boolean isURICharacter(final char c);
    public static boolean isXMLCharacter(final int c);
    public static boolean isXMLCombiningChar(final char c);
    public static boolean isXMLDigit(final char c);
    public static boolean isXMLExtender(final char c);
    public static boolean isXMLLetter(final char c);
    public static boolean isXMLLetterOrDigit(final char c);
    public static boolean isXMLNameCharacter(final char c);
    public static boolean isXMLNameStartCharacter(final char c);
    public static boolean isXMLWhitespace(final char c);
}
```
@input{existing test case}
here's the existing test cases:
```json
<cases_json>
```

@output{test cases}: output the test cases in the JSON format: