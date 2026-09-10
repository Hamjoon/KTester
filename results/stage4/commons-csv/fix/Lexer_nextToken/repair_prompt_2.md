


You are an experienced Java developer tasked with fixing a piece of code that contains execution errors. Your task is to resolve these issues after analyzing the code and its execution feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any logical errors.
2. Analyze the execution feedback to understand the specific errors and their locations within the code.
3. Apply necessary corrections to the code based on the feedback, and output the compelete correct code.
The Java code you need to fix is provided below:
```java
package org.apache.commons.csv;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.IOException;
import java.io.StringReader;

@ExtendWith(MockitoExtension.class)
class Lexer_nextToken_Test {

    private CSVFormat formatWithEscaping;

    private Lexer lexer;

    private ExtendedBufferedReader reader;

    private Token token;

    @BeforeEach
    void setupBeforeEach() {
        formatWithEscaping = CSVFormat.DEFAULT.withEscape('\\');
        // Initialize the token to be passed to nextToken
        token = new Token();
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup resources after each test if necessary
        if (lexer != null) {
            try {
                lexer.close();
            } catch (IOException e) {
                // Handle exception
            }
        }
    }

    // Custom matcher for token verification
    private void assertToken(Token actual, Token.Type expectedType, String expectedContent) {
        assertEquals(expectedType, actual.type);
        assertEquals(expectedContent, actual.content.toString());
    }

    // Test cases for nextToken method
    @Test
    void testNextTokenWithEscaping() throws IOException {
        final String code = "a,\\,,b\\\\\n\\,,\\\nc,d\\\r\ne";
        lexer = new Lexer(formatWithEscaping, new ExtendedBufferedReader(new StringReader(code)));
        assertToken(lexer.nextToken(token), Token.Type.TOKEN, "a");
        assertToken(lexer.nextToken(token), Token.Type.TOKEN, ",");
        assertToken(lexer.nextToken(token), Token.Type.EORECORD, "b\\");
        assertToken(lexer.nextToken(token), Token.Type.TOKEN, ",");
        assertToken(lexer.nextToken(token), Token.Type.TOKEN, "\nc");
        assertToken(lexer.nextToken(token), Token.Type.EORECORD, "d\r");
        assertToken(lexer.nextToken(token), Token.Type.EOF, "e");
    }

    @Test
    void testNextTokenWithoutEscaping() throws IOException {
        final String code = "a,\\,,b\\\n\\,,";
        lexer = new Lexer(CSVFormat.DEFAULT, new ExtendedBufferedReader(new StringReader(code)));
        assertToken(lexer.nextToken(token), Token.Type.TOKEN, "a");
        assertToken(lexer.nextToken(token), Token.Type.TOKEN, "\\");
        assertToken(lexer.nextToken(token), Token.Type.TOKEN, "");
        assertToken(lexer.nextToken(token), Token.Type.EORECORD, "b\\");
        assertToken(lexer.nextToken(token), Token.Type.TOKEN, "\\");
        assertToken(lexer.nextToken(token), Token.Type.TOKEN, "");
        assertToken(lexer.nextToken(token), Token.Type.EOF, "");
    }

    @Test
    void testNextTokenWithSurroundingSpaces() throws IOException {
        final String code = " noSpaces ,  leadingSpaces , trailingSpaces  ,  surroundingSpaces  ,  ,, ";
        lexer = new Lexer(CSVFormat.DEFAULT.withIgnoreSurroundingSpaces(), new ExtendedBufferedReader(new StringReader(code)));
        assertToken(lexer.nextToken(token), Token.Type.TOKEN, "noSpaces");
        assertToken(lexer.nextToken(token), Token.Type.TOKEN, "leadingSpaces");
        assertToken(lexer.nextToken(token), Token.Type.TOKEN, "trailingSpaces");
        assertToken(lexer.nextToken(token), Token.Type.TOKEN, "surroundingSpaces");
        assertToken(lexer.nextToken(token), Token.Type.TOKEN, "");
        assertToken(lexer.nextToken(token), Token.Type.TOKEN, "");
        assertToken(lexer.nextToken(token), Token.Type.EOF, "");
    }

    // Test cases for empty lines handling
    @Test
    void testNextTokenEmptyLinesHandling() throws IOException {
        // Test when the input contains only empty lines and ignoreEmptyLines is true
        lexer = new Lexer(CSVFormat.DEFAULT.withIgnoreEmptyLines(true), new ExtendedBufferedReader(new StringReader("\n\n")));
        // Added empty string for expectedContent
        assertToken(lexer.nextToken(token), Token.Type.EOF, "");
        // Test when the input contains only empty lines and ignoreEmptyLines is false
        lexer = new Lexer(CSVFormat.DEFAULT.withIgnoreEmptyLines(false), new ExtendedBufferedReader(new StringReader("\n\n")));
        // Added empty string for expectedContent
        assertToken(lexer.nextToken(token), Token.Type.EOF, "");
    }

    // Test cases for end of file handling
    @Test
    void testNextTokenEndOfFileHandling() throws IOException {
        // Test when the last read character is EOF
        lexer = new Lexer(CSVFormat.DEFAULT, new ExtendedBufferedReader(new StringReader("")));
        // Added empty string for expectedContent
        assertToken(lexer.nextToken(token), Token.Type.EOF, "");
        // Test when the current character read is EOF
        lexer = new Lexer(CSVFormat.DEFAULT, new ExtendedBufferedReader(new StringReader("a")));
        // Consume 'a'
        lexer.nextToken(token);
        // Added empty string for expectedContent
        assertToken(lexer.nextToken(token), Token.Type.EOF, "");
    }

    // Test cases for comment handling
    @Test
    void testNextTokenCommentHandling() throws IOException {
        // Test when the current character is a comment start and the line has a comment
        lexer = new Lexer(CSVFormat.DEFAULT, new ExtendedBufferedReader(new StringReader("# This is a comment\n")));
        assertToken(lexer.nextToken(token), Token.Type.COMMENT, "This is a comment");
        // Test when the line is empty after a comment
        lexer = new Lexer(CSVFormat.DEFAULT, new ExtendedBufferedReader(new StringReader("# Comment\n\n")));
        // Added empty string for expectedContent
        assertToken(lexer.nextToken(token), Token.Type.EOF, "");
    }

    // Test cases for token parsing
    @Test
    void testNextTokenParsing() throws IOException {
        // Test when a valid token is read
        lexer = new Lexer(CSVFormat.DEFAULT, new ExtendedBufferedReader(new StringReader("sampleToken,,")));
        assertToken(lexer.nextToken(token), Token.Type.TOKEN, "sampleToken");
        // Test when an end of record is reached
        // Added empty string for expectedContent
        assertToken(lexer.nextToken(token), Token.Type.EORECORD, "");
        // Test when EOF is reached with valid content
        lexer = new Lexer(CSVFormat.DEFAULT, new ExtendedBufferedReader(new StringReader("sampleToken")));
        assertToken(lexer.nextToken(token), Token.Type.TOKEN, "sampleToken");
        // Added empty string for expectedContent
        assertToken(lexer.nextToken(token), Token.Type.EOF, "");
    }

    // Test cases for whitespace handling
    @Test
    void testNextTokenWhitespaceHandling() throws IOException {
        // Test when surrounding spaces are ignored and a valid token is read
        lexer = new Lexer(CSVFormat.DEFAULT.withIgnoreSurroundingSpaces(), new ExtendedBufferedReader(new StringReader("  trimmedToken  ,  ")));
        assertToken(lexer.nextToken(token), Token.Type.TOKEN, "trimmedToken");
        // Test when only whitespace is encountered and eol is true
        // Added empty string for expectedContent
        assertToken(lexer.nextToken(token), Token.Type.EORECORD, "");
    }
}

```

Corresponding execution feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended

╷
├─ JUnit Jupiter ✔
│  └─ Lexer_nextToken_Test ✔
│     ├─ testNextTokenWithSurroundingSpaces() ✘ expected: <leadingSpaces> but was: <noSpaces>
│     ├─ testNextTokenCommentHandling() ✘ expected: <COMMENT> but was: <EORECORD>
│     ├─ testNextTokenWithoutEscaping() ✘ expected: <\> but was: <a>
│     ├─ testNextTokenParsing() ✘ expected: <EORECORD> but was: <TOKEN>
│     ├─ testNextTokenWithEscaping() ✘ expected: <,> but was: <a>
│     ├─ testNextTokenEmptyLinesHandling() ✔
│     ├─ testNextTokenWhitespaceHandling() ✘ expected: <EORECORD> but was: <TOKEN>
│     └─ testNextTokenEndOfFileHandling() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Failures (6):
  JUnit Jupiter:Lexer_nextToken_Test:testNextTokenWithSurroundingSpaces()
    MethodSource [className = 'org.apache.commons.csv.Lexer_nextToken_Test', methodName = 'testNextTokenWithSurroundingSpaces', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <leadingSpaces> but was: <noSpaces>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1142)
       org.apache.commons.csv.Lexer_nextToken_Test.assertToken(Lexer_nextToken_Test.java:44)
       org.apache.commons.csv.Lexer_nextToken_Test.testNextTokenWithSurroundingSpaces(Lexer_nextToken_Test.java:79)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       [...]
  JUnit Jupiter:Lexer_nextToken_Test:testNextTokenCommentHandling()
    MethodSource [className = 'org.apache.commons.csv.Lexer_nextToken_Test', methodName = 'testNextTokenCommentHandling', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <COMMENT> but was: <EORECORD>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1142)
       org.apache.commons.csv.Lexer_nextToken_Test.assertToken(Lexer_nextToken_Test.java:43)
       org.apache.commons.csv.Lexer_nextToken_Test.testNextTokenCommentHandling(Lexer_nextToken_Test.java:120)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       [...]
  JUnit Jupiter:Lexer_nextToken_Test:testNextTokenWithoutEscaping()
    MethodSource [className = 'org.apache.commons.csv.Lexer_nextToken_Test', methodName = 'testNextTokenWithoutEscaping', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <\> but was: <a>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1142)
       org.apache.commons.csv.Lexer_nextToken_Test.assertToken(Lexer_nextToken_Test.java:44)
       org.apache.commons.csv.Lexer_nextToken_Test.testNextTokenWithoutEscaping(Lexer_nextToken_Test.java:66)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       [...]
  JUnit Jupiter:Lexer_nextToken_Test:testNextTokenParsing()
    MethodSource [className = 'org.apache.commons.csv.Lexer_nextToken_Test', methodName = 'testNextTokenParsing', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <EORECORD> but was: <TOKEN>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1142)
       org.apache.commons.csv.Lexer_nextToken_Test.assertToken(Lexer_nextToken_Test.java:43)
       org.apache.commons.csv.Lexer_nextToken_Test.testNextTokenParsing(Lexer_nextToken_Test.java:135)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       [...]
  JUnit Jupiter:Lexer_nextToken_Test:testNextTokenWithEscaping()
    MethodSource [className = 'org.apache.commons.csv.Lexer_nextToken_Test', methodName = 'testNextTokenWithEscaping', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <,> but was: <a>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1142)
       org.apache.commons.csv.Lexer_nextToken_Test.assertToken(Lexer_nextToken_Test.java:44)
       org.apache.commons.csv.Lexer_nextToken_Test.testNextTokenWithEscaping(Lexer_nextToken_Test.java:53)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       [...]
  JUnit Jupiter:Lexer_nextToken_Test:testNextTokenWhitespaceHandling()
    MethodSource [className = 'org.apache.commons.csv.Lexer_nextToken_Test', methodName = 'testNextTokenWhitespaceHandling', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <EORECORD> but was: <TOKEN>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1142)
       org.apache.commons.csv.Lexer_nextToken_Test.assertToken(Lexer_nextToken_Test.java:43)
       org.apache.commons.csv.Lexer_nextToken_Test.testNextTokenWhitespaceHandling(Lexer_nextToken_Test.java:151)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       [...]

Test run finished after 386 ms
[         4 containers found      ]
[         0 containers skipped    ]
[         4 containers started    ]
[         0 containers aborted    ]
[         4 containers successful ]
[         0 containers failed     ]
[         8 tests found           ]
[         0 tests skipped         ]
[         8 tests started         ]
[         0 tests aborted         ]
[         2 tests successful      ]
[         6 tests failed          ]


```


And you can use the following related information to assist you in your analysis and correction:

api document of class org.apache.commons.csv.Lexer: 

 * Lexical analyzer.
 

api document of method nextToken(Token): 

     * Returns the next token.
     * <p>
     * A token corresponds to a term, a record change or an end-of-file indicator.
     * </p>
     *
     * @param token
     *            an existing Token object to reuse. The caller is responsible to initialize the Token.
     * @return the next token found.
     * @throws IOException on stream access error.
     

return type: 
org.apache.commons.csv.Token

dependent classes: 
class `org.apache.commons.csv.Token`:
	dependent fields:
		java.lang.StringBuilder org.apache.commons.csv.Token.content;
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.type;
		boolean org.apache.commons.csv.Token.isReady;
	related functions:
		method `public String toString()` returns `java.lang.String`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`, api document: 
     * Eases IDE debugging.
     *
     * @return a string helpful for debugging.
     ;
		method ` void reset()` returns `void`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`;
class `org.apache.commons.csv.Token.Type`:
	dependent fields:
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.TOKEN;
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.EOF;
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.EORECORD;
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.INVALID;
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.COMMENT;
	
class `org.apache.commons.csv.Lexer`:
	dependent fields:
		boolean org.apache.commons.csv.Lexer.isLastTokenDelimiter;
		boolean org.apache.commons.csv.Lexer.ignoreEmptyLines;
		boolean org.apache.commons.csv.Lexer.ignoreSurroundingSpaces;
		org.apache.commons.csv.ExtendedBufferedReader org.apache.commons.csv.Lexer.reader;
	related functions:
		method `private Token parseSimpleToken(Token, int)` returns `org.apache.commons.csv.Token`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`, api document: 
     * Parses a simple token.
     * <p>
     * Simple token are tokens which are not surrounded by encapsulators. A simple token might contain escaped
     * delimiters (as \, or \;). The token is finished when one of the following conditions become true:
     * </p>
     * <ul>
     * <li>end of line has been reached (EORECORD)</li>
     * <li>end of stream has been reached (EOF)</li>
     * <li>an unescaped delimiter has been reached (TOKEN)</li>
     * </ul>
     *
     * @param token
     *            the current token
     * @param ch
     *            the current character
     * @return the filled token
     * @throws IOException
     *             on stream access error
     ;
		method `private Token parseEncapsulatedToken(Token)` returns `org.apache.commons.csv.Token`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`, api document: 
     * Parses an encapsulated token.
     * <p>
     * Encapsulated tokens are surrounded by the given encapsulating-string. The encapsulator itself might be included
     * in the token using a doubling syntax (as "", '') or using escaping (as in \", \'). Whitespaces before and after
     * an encapsulated token are ignored. The token is finished when one of the following conditions become true:
     * </p>
     * <ul>
     * <li>an unescaped encapsulator has been reached, and is followed by optional whitespace then:</li>
     * <ul>
     * <li>delimiter (TOKEN)</li>
     * <li>end of line (EORECORD)</li>
     * </ul>
     * <li>end of stream has been reached (EOF)</li> </ul>
     *
     * @param token
     *            the current token
     * @return a valid token object
     * @throws IOException
     *             on invalid state: EOF before closing encapsulator or invalid character before delimiter or EOL
     ;
		method ` boolean isDelimiter(int)` returns `boolean`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`, api document: 
     * Determine whether the next characters constitute a delimiter through {@link ExtendedBufferedReader#lookAhead(char[])}.
     *
     * @param ch
     *             the current character.
     * @return true if the next characters constitute a delimiter.
     * @throws IOException If an I/O error occurs.
     ;
		method ` int readEscape()` returns `int`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`, api document: 
     * Handle an escape sequence.
     * The current character must be the escape character.
     * On return, the next character is available by calling {@link ExtendedBufferedReader#getLastChar()}
     * on the input stream.
     *
     * @return the unescaped character (as an int) or {@link Constants#END_OF_STREAM} if char following the escape is
     *      invalid.
     * @throws IOException if there is a problem reading the stream or the end of stream is detected:
     *      the escape character is not allowed at end of stream
     ;
		method ` boolean readEndOfLine(int)` returns `boolean`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`, api document: 
     * Greedily accepts \n, \r and \r\n This checker consumes silently the second control-character...
     *
     * @return true if the given or next character is a line-terminator
     ;
class `org.apache.commons.csv.CSVParser`:
	related functions:
		method ` CSVRecord nextRecord()` returns `org.apache.commons.csv.CSVRecord`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`, api document: 
     * Parses the next record from the current point in the stream.
     *
     * @return the record as an array of values, or {@code null} if the end of the stream has been reached
     * @throws IOException
     *             on parse error or input read-failure
     ;
class `org.apache.commons.csv.CSVFormat`:
	related functions:
		method ` static boolean isBlank(String)` returns `boolean`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`;



Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 
