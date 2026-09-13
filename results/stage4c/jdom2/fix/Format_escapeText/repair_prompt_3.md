


You are an experienced Java developer tasked with fixing a piece of code that contains execution errors. Your task is to resolve these issues after analyzing the code and its execution feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any logical errors.
2. Analyze the execution feedback to understand the specific errors and their locations within the code.
3. Apply necessary corrections to the code based on the feedback, and output the compelete correct code.
The Java code you need to fix is provided below:
```java
package org.jdom2.output;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.jdom2.IllegalDataException;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class Format_escapeText_Test {

    private EscapeStrategy mockStrategy;

    private Format format;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize mocks and other necessary objects before each test
        mockStrategy = mock(EscapeStrategy.class);
        format = new Format();
    }

    @Test
    void testEscapeText_noEscapingNeeded() {
        // Given
        String input = "No special chars";
        when(mockStrategy.shouldEscape(anyChar())).thenReturn(false);
        // When
        String result = Format.escapeText(mockStrategy, null, input);
        // Then
        assertEquals(input, result);
    }

    @Test
    void testEscapeText_withSpecialChars() {
        // Given
        String input = "Text with < and &";
        when(mockStrategy.shouldEscape('<')).thenReturn(true);
        when(mockStrategy.shouldEscape('&')).thenReturn(true);
        when(mockStrategy.shouldEscape(anyChar())).thenReturn(false);
        // When
        String result = Format.escapeText(mockStrategy, null, input);
        // Then
        assertEquals("Text with &lt; and &amp;", result);
    }

    @Test
    void testEscapeText_withCustomEOL() {
        // Given
        String input = "Line1\nLine2";
        String eol = "<br/>";
        when(mockStrategy.shouldEscape(anyChar())).thenReturn(false);
        // When
        String result = Format.escapeText(mockStrategy, eol, input);
        // Then
        assertEquals("Line1<br/>Line2", result);
    }

    @Test
    void testEscapeText_withSurrogatePair() {
        // Given
        // Smiley face emoji
        String input = "Text with \uD83D\uDE00";
        when(mockStrategy.shouldEscape('\uD83D')).thenReturn(true);
        when(mockStrategy.shouldEscape('\uDE00')).thenReturn(true);
        when(mockStrategy.shouldEscape(anyChar())).thenReturn(false);
        // When
        String result = Format.escapeText(mockStrategy, null, input);
        // Then
        // Emoji should be escaped
        assertEquals("Text with &#x1f600;", result);
    }

    @Test
    void testEscapeText_withInvalidChar() {
        // Given
        // Null character
        String input = "\u0000";
        when(mockStrategy.shouldEscape('\u0000')).thenReturn(true);
        when(mockStrategy.shouldEscape(anyChar())).thenReturn(false);
        // When / Then
        assertThrows(IllegalDataException.class, () -> {
            Format.escapeText(mockStrategy, null, input);
        });
    }

    @Test
    void testEscapeText_withHighSurrogateWithoutLow() {
        // Given
        // High surrogate without a low surrogate
        String input = "\uD83D";
        when(mockStrategy.shouldEscape('\uD83D')).thenReturn(true);
        when(mockStrategy.shouldEscape(anyChar())).thenReturn(false);
        // When / Then
        assertThrows(IllegalDataException.class, () -> {
            Format.escapeText(mockStrategy, null, input);
        });
    }

    @Test
    void testEscapeText_emptyString() {
        // Given
        String input = "";
        // When
        String result = Format.escapeText(mockStrategy, null, input);
        // Then
        // Empty input should return empty output
        assertEquals("", result);
    }

    @Test
    void testEscapeText_withCustomStrategy() {
        // Given
        String input = "Escape x character";
        when(mockStrategy.shouldEscape('x')).thenReturn(true);
        when(mockStrategy.shouldEscape(anyChar())).thenReturn(false);
        // When
        String result = Format.escapeText(mockStrategy, null, input);
        // Then
        // 'x' should be escaped
        assertEquals("Escape &#x78; character", result);
    }

    @Test
    void testEscapeText_withCustomEOLAndNewlines() {
        // Given
        String input = "Line1\nLine2";
        String eol = "\r\n";
        when(mockStrategy.shouldEscape(anyChar())).thenReturn(false);
        // When
        String result = Format.escapeText(mockStrategy, eol, input);
        // Then
        // Newlines should be replaced by eol
        assertEquals("Line1\r\nLine2", result);
    }

    @Test
    void testEscapeText_withSpecialCharacter() {
        // Given
        String input = "Special char \u007F";
        when(mockStrategy.shouldEscape('\u007F')).thenReturn(true);
        when(mockStrategy.shouldEscape(anyChar())).thenReturn(false);
        // When
        String result = Format.escapeText(mockStrategy, null, input);
        // Then
        // Special character should be escaped
        assertEquals("Special char &#x7F;", result);
    }
}

```

Corresponding execution feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended

╷
├─ JUnit Jupiter ✔
│  └─ Format_escapeText_Test ✔
│     ├─ testEscapeText_emptyString() ✔
│     ├─ testEscapeText_withHighSurrogateWithoutLow() ✘ Expected org.jdom2.IllegalDataException to be thrown, but nothing was thrown.
│     ├─ testEscapeText_withSurrogatePair() ✘ expected: <Text with &#x1f600;> but was: <Text with 😀>
│     ├─ testEscapeText_withCustomEOLAndNewlines() ✔
│     ├─ testEscapeText_withCustomEOL() ✔
│     ├─ testEscapeText_withCustomStrategy() ✘ expected: <Escape &#x78; character> but was: <Escape x character>
│     ├─ testEscapeText_noEscapingNeeded() ✔
│     ├─ testEscapeText_withSpecialCharacter() ✘ expected: <Special char &#x7F;> but was: <Special char >
│     ├─ testEscapeText_withInvalidChar() ✘ Expected org.jdom2.IllegalDataException to be thrown, but nothing was thrown.
│     └─ testEscapeText_withSpecialChars() ✘ 
│              Unnecessary stubbings detected.
│              Clean & maintainable test code requires zero unnecessary code.
│              Following stubbings are unnecessary (click to navigate to relevant line of code):
│                1. -> at org.jdom2.output.Format_escapeText_Test.testEscapeText_withSpecialChars(Format_escapeText_Test.java:40)
│                2. -> at org.jdom2.output.Format_escapeText_Test.testEscapeText_withSpecialChars(Format_escapeText_Test.java:41)
│              Please remove unnecessary stubbings or use 'lenient' strictness. More info: javadoc for UnnecessaryStubbingException class.
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Failures (6):
  JUnit Jupiter:Format_escapeText_Test:testEscapeText_withHighSurrogateWithoutLow()
    MethodSource [className = 'org.jdom2.output.Format_escapeText_Test', methodName = 'testEscapeText_withHighSurrogateWithoutLow', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Expected org.jdom2.IllegalDataException to be thrown, but nothing was thrown.
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:73)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3128)
       org.jdom2.output.Format_escapeText_Test.testEscapeText_withHighSurrogateWithoutLow(Format_escapeText_Test.java:97)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:767)
       [...]
  JUnit Jupiter:Format_escapeText_Test:testEscapeText_withSurrogatePair()
    MethodSource [className = 'org.jdom2.output.Format_escapeText_Test', methodName = 'testEscapeText_withSurrogatePair', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <Text with &#x1f600;> but was: <Text with 😀>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1145)
       org.jdom2.output.Format_escapeText_Test.testEscapeText_withSurrogatePair(Format_escapeText_Test.java:73)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:Format_escapeText_Test:testEscapeText_withCustomStrategy()
    MethodSource [className = 'org.jdom2.output.Format_escapeText_Test', methodName = 'testEscapeText_withCustomStrategy', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <Escape &#x78; character> but was: <Escape x character>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1145)
       org.jdom2.output.Format_escapeText_Test.testEscapeText_withCustomStrategy(Format_escapeText_Test.java:123)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:Format_escapeText_Test:testEscapeText_withSpecialCharacter()
    MethodSource [className = 'org.jdom2.output.Format_escapeText_Test', methodName = 'testEscapeText_withSpecialCharacter', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <Special char &#x7F;> but was: <Special char >
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1145)
       org.jdom2.output.Format_escapeText_Test.testEscapeText_withSpecialCharacter(Format_escapeText_Test.java:149)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:Format_escapeText_Test:testEscapeText_withInvalidChar()
    MethodSource [className = 'org.jdom2.output.Format_escapeText_Test', methodName = 'testEscapeText_withInvalidChar', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Expected org.jdom2.IllegalDataException to be thrown, but nothing was thrown.
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:73)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3128)
       org.jdom2.output.Format_escapeText_Test.testEscapeText_withInvalidChar(Format_escapeText_Test.java:84)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:767)
       [...]
  JUnit Jupiter:Format_escapeText_Test:testEscapeText_withSpecialChars()
    MethodSource [className = 'org.jdom2.output.Format_escapeText_Test', methodName = 'testEscapeText_withSpecialChars', methodParameterTypes = '']
    => org.mockito.exceptions.misusing.UnnecessaryStubbingException: 
Unnecessary stubbings detected.
Clean & maintainable test code requires zero unnecessary code.
Following stubbings are unnecessary (click to navigate to relevant line of code):
  1. -> at org.jdom2.output.Format_escapeText_Test.testEscapeText_withSpecialChars(Format_escapeText_Test.java:40)
  2. -> at org.jdom2.output.Format_escapeText_Test.testEscapeText_withSpecialChars(Format_escapeText_Test.java:41)
Please remove unnecessary stubbings or use 'lenient' strictness. More info: javadoc for UnnecessaryStubbingException class.
       org.mockito.junit.jupiter.MockitoExtension.afterEach(MockitoExtension.java:186)
       org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$invokeAfterEachCallbacks$13(TestMethodTestDescriptor.java:260)
       org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$invokeAllAfterMethodsOrCallbacks$14(TestMethodTestDescriptor.java:276)
       org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
       org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$invokeAllAfterMethodsOrCallbacks$15(TestMethodTestDescriptor.java:276)
       org.junit.platform.commons.util.CollectionUtils.forEachInReverseOrder(CollectionUtils.java:240)
       org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.invokeAllAfterMethodsOrCallbacks(TestMethodTestDescriptor.java:275)
       org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.invokeAfterEachCallbacks(TestMethodTestDescriptor.java:259)
       org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:144)
       org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:68)
       [...]

Test run finished after 477 ms
[         4 containers found      ]
[         0 containers skipped    ]
[         4 containers started    ]
[         0 containers aborted    ]
[         4 containers successful ]
[         0 containers failed     ]
[        10 tests found           ]
[         0 tests skipped         ]
[        10 tests started         ]
[         0 tests aborted         ]
[         4 tests successful      ]
[         6 tests failed          ]


```


And you can use the following related information to assist you in your analysis and correction:

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
 

api document of method escapeText(EscapeStrategy, String, String): 

	 * This will take the three pre-defined entities in XML 1.0 ('&lt;', '&gt;',
	 * and '&amp;' - used specifically in XML elements) and convert their
	 * character representation to the appropriate entity reference, suitable
	 * for XML element content. Further, some special characters (e.g.
	 * characters that are not valid in the current encoding) are converted to
	 * escaped representations. If the eol parameter is not null, then any
	 * internal newlines will be replaced with the specified eol sequence.
	 * 
	 * @param strategy
	 *        The EscapeStrategy
	 * @param eol
	 *        The End-Of-Line sequence to be used (may be null).
	 * @param value
	 *        The String to escape
	 * @return The input value escaped.
	 * @throws IllegalDataException
	 *         if an entity can not be escaped
	 * @since JDOM2
	 

dependent classes: 
class `org.jdom2.output.EscapeStrategy`:
	
class `org.jdom2.output.Format`:
	related functions:
		method `public static final String escapeAttribute(EscapeStrategy, String)` returns `java.lang.String`, related with `org.jdom2.output.Format.escapeText(EscapeStrategy, String, String)`, api document: 
	 * This will take the three pre-defined entities in XML 1.0 ('&lt;', '&gt;',
	 * and '&amp;' - used specifically in XML elements) as well as CR/NL, tabs,
	 * and Quote characters which require escaping inside Attribute values and
	 * converts their character representation to the appropriate entity
	 * reference suitable for XML attribute content. Further, some special
	 * characters (e.g. characters that are not valid in the current encoding)
	 * are converted to escaped representations.
	 * <p>
	 * @param strategy 
	 *        The EscapeStrategy to query.
	 * @param value
	 *        <code>String</code> Attribute value to escape.
	 * @return The value appropriately escaped.
	 * @throws IllegalDataException
	 *         if an entity can not be escaped
	 ;
		method `public static final String escapeText(EscapeStrategy, String, String)` returns `java.lang.String`, related with `org.jdom2.output.Format.escapeText(EscapeStrategy, String, String)`, api document: 
	 * This will take the three pre-defined entities in XML 1.0 ('&lt;', '&gt;',
	 * and '&amp;' - used specifically in XML elements) and convert their
	 * character representation to the appropriate entity reference, suitable
	 * for XML element content. Further, some special characters (e.g.
	 * characters that are not valid in the current encoding) are converted to
	 * escaped representations. If the eol parameter is not null, then any
	 * internal newlines will be replaced with the specified eol sequence.
	 * 
	 * @param strategy
	 *        The EscapeStrategy
	 * @param eol
	 *        The End-Of-Line sequence to be used (may be null).
	 * @param value
	 *        The String to escape
	 * @return The input value escaped.
	 * @throws IllegalDataException
	 *         if an entity can not be escaped
	 * @since JDOM2
	 ;
		method `public static final String compact(String)` returns `java.lang.String`, related with `org.jdom2.output.Format.escapeText(EscapeStrategy, String, String)`, api document: 
	 * Use the XML Specification definition of whitespace to compact the
	 * input value. The value is trimmed, and any internal XML whitespace
	 * is replaced with a single ' ' space.
	 * @param str The value to compact.
	 * @return The compacted value
	 * @since JDOM2
	 ;
class `org.jdom2.Verifier`:
	related functions:
		method `public static String checkCharacterData(String)` returns `java.lang.String`, related with `org.jdom2.output.Format.escapeText(EscapeStrategy, String, String)`, api document: 
	 * This will check the supplied string to see if it only contains
	 * characters allowed by the XML 1.0 specification. The C0 controls
	 * (e.g. null, vertical tab, form-feed, etc.) are specifically excluded
	 * except for carriage return, line-feed, and the horizontal tab.
	 * Surrogates are also excluded. 
	 * <p>
	 * This method is useful for checking element content and attribute
	 * values. Note that characters 
	 * like " and &lt; are allowed in attribute values and element content. 
	 * They will simply be escaped as &quot; or &lt; 
	 * when the value is serialized. 
	 * </p>
	 *
	 * @param text <code>String</code> value to check.
	 * @return <code>String</code> reason name is illegal, or
	 *         <code>null</code> if name is OK.
	 ;
class `org.jdom2.EntityRef`:
	related functions:
		method `public String toString()` returns `java.lang.String`, related with `org.jdom2.output.Format.escapeText(EscapeStrategy, String, String)`, api document: 
	 * This returns a <code>String</code> representation of the
	 * <code>EntityRef</code>, suitable for debugging.
	 *
	 * @return <code>String</code> - information about the
	 *         <code>EntityRef</code>
	 ;
class `org.jdom2.input.stax.DTDParser`:
	related functions:
		method `private static String formatInternal(String)` returns `java.lang.String`, related with `org.jdom2.output.Format.escapeText(EscapeStrategy, String, String)`, api document: 
	 * Reformat an internal subset.... Each declaration starts on an indented
	 * newline.
	 * @param internal the input DocType declaration as found in a StAX Reader.
	 * @return the formatted input.
	 ;
class `org.jdom2.Attribute`:
	related functions:
		method `public String toString()` returns `java.lang.String`, related with `org.jdom2.output.Format.escapeText(EscapeStrategy, String, String)`, api document: 
	 * This returns a <code>String</code> representation of the
	 * <code>Attribute</code>, suitable for debugging.
	 *
	 * @return <code>String</code> - information about the
	 *         <code>Attribute</code>
	 ;
class `org.jdom2.Comment`:
	related functions:
		method `public String toString()` returns `java.lang.String`, related with `org.jdom2.output.Format.escapeText(EscapeStrategy, String, String)`, api document: 
	 * This returns a <code>String</code> representation of the
	 * <code>Comment</code>, suitable for debugging. If the XML
	 * representation of the <code>Comment</code> is desired,
	 * {@link org.jdom2.output.XMLOutputter#outputString(Comment)}
	 * should be used.
	 *
	 * @return <code>String</code> - information about the
	 *         <code>Comment</code>
	 ;



Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 
