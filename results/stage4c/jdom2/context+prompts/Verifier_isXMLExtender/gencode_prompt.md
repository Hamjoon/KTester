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
Here is the code of focal method, `isXMLExtender(char)`, with the focal class `Verifier`:
```Java
package org.jdom2;
import java.util.*;
public final class Verifier {
    
    public static boolean isXMLExtender(final char c) {
        /* This function is not accellerated by the bitmask system because
        there are no longer any actual calls to it from the JDOM code.
        It used to be called by the isXMLNameCharacter() method before
        the bitmask optimization. Now the VerifierBuilder code actually
        calls this method instead.
         */
        if (c < 0xb6)
            return false;
        // quick short circuit
    
        // Extenders
        if (c == 0xb7)
            return true;
    
        if (c == 0x2d0)
            return true;
    
        if (c == 0x2d1)
            return true;
    
        if (c == 0x387)
            return true;
    
        if (c == 0x640)
            return true;
    
        if (c == 0xe46)
            return true;
    
        if (c == 0xec6)
            return true;
    
        if (c == 0x3005)
            return true;
    
        if (c < 0x3031)
            return false;
    
        if (c <= 0x3035)
            return true;
    
        if (c < 0x309d)
            return false;
    
        if (c <= 0x309e)
            return true;
    
        if (c < 0x30fc)
            return false;
    
        if (c <= 0x30fe)
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
    public static boolean isXMLLetter(final char c);
    public static boolean isXMLLetterOrDigit(final char c);
    public static boolean isXMLNameCharacter(final char c);
    public static boolean isXMLNameStartCharacter(final char c);
    public static boolean isXMLPublicIDCharacter(final char c);
    public static boolean isXMLWhitespace(final char c);
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

api document of class org.jdom2.Verifier: 

 * A utility class to handle well-formedness checks on names, data, and other
 * verification tasks for JDOM. The class is final and may not be subclassed.
 *
 * @author  Brett McLaughlin
 * @author  Elliotte Rusty Harold
 * @author  Jason Hunter
 * @author  Bradley S. Huffman
 * @author  Rolf Lear
 * @author  Wilfried Middleton
 * 
* KEY TO UNDERSTANDING MASKS.
* ===========================
* 
* This Verifier uses bitwise logic to perform fast validation on
* XML characters. The concept is as follows...
* 
* There are 7 major tests for characters in JDOM and one special case.
* Can the character be a regular character, can it be part of an XML Name
* (element, attribute, entity-ref, etc.), does it represent a letter,
* digit, or combining character. Finally can a character be the first
* character in a name, or can the character be part of a URI. The special
* case is that Attributes and Element names in JDOM do not include the
* namespace prefix, thus, for Attribute and Elements, the name is the
* identical test to other XML names, but excludes the ':'. For performance
* reasons we only have the bitmask for the JDOM names, and then add the
* ':' for the general case tests.
* 
* These 7 tests are often performed in very tight performance critical
* loops. It is essential for them to be fast.
* 
* These 7 tests conveniently can be represented as 8 bits in a byte.
* We can thus have a single byte that represents the possible roles for
* each possible character. There are 64K characters... thus we need 64K
* bytes to represent each character's possible roles.
* 
* We could use arrays of booleans to accomplish the same thing, but each
* boolean is a byte of memory, and using a bitmask allows us to put the
* 8 bitmask tests in the same memory space as just one boolean array.
* 
* The end solution is to have an array of these bytes, one per character,
* and to then query each bit on the byte to see whether the corresponding
* character is able to perform in the respective role.
* 
* The complicated part of this process is three-fold. The hardest part is
* knowing what role each character can play. The next hard part is
* converting this knowledge in to an array of bytes we can express in this
* Verifier class. The final part is querying that array for each test.
* 
* Before this particular performance upgrade, the knowledge of what roles
* each character can play was embedded in each of the isXML*() methods.
* Those methods have been transferred in to the 'contrib' class
* org.jdom2.contrib.verifier.VerifierBuilder. That VerifierBuilder class
* has a main method which takes that knowledge, and converts it in to a
* 'compressed' set of two arrays, the byte mask, and the number of
* consecutive characters that have that mask, which are then copy/pasted
* in to this file as the VALCONST and LENCONST arrays.
* 
* These two arrays are then 'decompressed' in to the CHARFLAGS array.
* 
* The CHARFLAGS array is then queried for each of the 8 critical tests
* to determine which roles a character performs.
* 
* If you need to change the roles a character plays in XML (i.e. change
* the return-value of one of the isXML...() methods, then you need to:
* 
*  - update the logic in org.jdom2.contrib.verifier.VerifierBuilder
*  - run the VerifierBuilder
*  - copy/paste the output to this file.
*  - update the JUnit test harness TestVerifier
*
 

api document of method isXMLExtender(char): 

	 * This is a utility function for determining whether a specified 
	 * character is an extender according to production 88 of the XML 1.0
	 * specification.
	 *
	 * @param c <code>char</code> to check.
	 * @return <code>String</code> true if it's an extender, false otherwise.
	 

return type: 
boolean

dependent classes: 
class `org.jdom2.adapters.AbstractDOMAdapter`:
	related functions:
		method `public Document createDocument(DocType)` returns `org.w3c.dom.Document`, related with `org.jdom2.Verifier.isXMLExtender(char)`, api document: 
	 * This creates an empty <code>Document</code> object based
	 * on a specific parser implementation with the given DOCTYPE.
	 * If the doctype parameter is null, the behavior is the same as
	 * calling <code>createDocument()</code>.
	 *
	 * @param doctype Initial <code>DocType</code> of the document.
	 * @return <code>Document</code> - created DOM Document.
	 * @throws JDOMException when errors occur.
	 ;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.