


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
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.IOException;
import java.io.StringReader;

@ExtendWith(MockitoExtension.class)
class Lexer_nextToken_Test {

    private Lexer lexer;

    private CSVFormat format;

    private ExtendedBufferedReader reader;

    private Token token;

    @BeforeEach
    void setupBeforeEach() {
        // Setup the CSVFormat and ExtendedBufferedReader
        format = CSVFormat.DEFAULT.withEscape('\\');
        token = new Token();
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup after each test if necessary
        if (lexer != null) {
            try {
                lexer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void createLexer(final String input) {
        reader = new ExtendedBufferedReader(new StringReader(input));
        lexer = new Lexer(format, reader);
    }

    private static void assertTokenMatches(Token token, Token.Type expectedType, String expectedContent) {
        assertEquals(expectedType, token.type);
        assertEquals(expectedContent, token.content.toString().trim());
    }

    private void setField(Object obj, String fieldName, Object value) {
        try {
            var field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(obj, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testNextTokenWithValidInput() throws IOException {
        createLexer("a,b,c");
        assertTokenMatches(lexer.nextToken(token), Token.Type.TOKEN, "a");
        assertTokenMatches(lexer.nextToken(token), Token.Type.TOKEN, "b");
        assertTokenMatches(lexer.nextToken(token), Token.Type.TOKEN, "c");
        assertTokenMatches(lexer.nextToken(token), Token.Type.EOF, "");
    }

    @Test
    void testNextTokenWithEmptyInput() throws IOException {
        createLexer("");
        assertTokenMatches(lexer.nextToken(token), Token.Type.EOF, "");
    }

    @Test
    void testNextTokenWithComment() throws IOException {
        createLexer("# This is a comment\nvalue1,value2");
        assertTokenMatches(lexer.nextToken(token), Token.Type.COMMENT, "This is a comment");
        assertTokenMatches(lexer.nextToken(token), Token.Type.TOKEN, "value1");
        assertTokenMatches(lexer.nextToken(token), Token.Type.TOKEN, "value2");
        assertTokenMatches(lexer.nextToken(token), Token.Type.EOF, "");
    }

    @Test
    void testNextTokenWithEscapedCharacters() throws IOException {
        createLexer("a,\\,,b");
        assertTokenMatches(lexer.nextToken(token), Token.Type.TOKEN, "a");
        assertTokenMatches(lexer.nextToken(token), Token.Type.TOKEN, ",");
        assertTokenMatches(lexer.nextToken(token), Token.Type.TOKEN, "b");
        assertTokenMatches(lexer.nextToken(token), Token.Type.EOF, "");
    }

    @Test
    void testNextTokenHandlingEmptyLines() throws IOException {
        // Test when ignoreEmptyLines is true and the input contains only empty lines
        createLexer("\n\n\n");
        // Set the ignoreEmptyLines flag using reflection
        setField(lexer, "ignoreEmptyLines", true);
        assertTokenMatches(lexer.nextToken(token), Token.Type.EOF, "");
        // Test when ignoreEmptyLines is false and the input contains only empty lines
        createLexer("\n\n\n");
        setField(lexer, "ignoreEmptyLines", false);
        assertTokenMatches(lexer.nextToken(token), Token.Type.EOF, "");
    }

    @Test
    void testNextTokenEndOfFileHandling() throws IOException {
        // Test when the last character read is EOF
        createLexer("a,b,c");
        // Read tokens until EOF
        lexer.nextToken(token);
        lexer.nextToken(token);
        lexer.nextToken(token);
        assertTokenMatches(lexer.nextToken(token), Token.Type.EOF, "");
        // Test when the next character read is EOF
        createLexer("a,b,c");
        // Read first token
        lexer.nextToken(token);
        // Read second token
        lexer.nextToken(token);
        // Read third token
        lexer.nextToken(token);
        // Read EOF
        lexer.nextToken(token);
        assertTokenMatches(lexer.nextToken(token), Token.Type.EOF, "");
    }

    @Test
    void testNextTokenCommentHandling() throws IOException {
        // Test when the character read indicates a comment start
        createLexer("# This is a comment\nvalue1,value2");
        assertTokenMatches(lexer.nextToken(token), Token.Type.COMMENT, "This is a comment");
    }

    @Test
    void testNextTokenTokenParsing() throws IOException {
        // Test when the character read is a simple token
        createLexer("simpleToken,");
        assertTokenMatches(lexer.nextToken(token), Token.Type.TOKEN, "simpleToken");
        // Test when the character read indicates end of record
        createLexer("value1,value2\n");
        assertTokenMatches(lexer.nextToken(token), Token.Type.EORECORD, "");
        // Test when the character read indicates the start of an encapsulated token
        createLexer("\"encapsulatedToken\",value");
        assertTokenMatches(lexer.nextToken(token), Token.Type.TOKEN, "encapsulatedToken");
    }

    @Test
    void testNextTokenWhitespaceHandling() throws IOException {
        // Test when ignoreSurroundingSpaces is true and there are leading whitespaces
        createLexer("   tokenWithSpaces");
        // Set the ignoreSurroundingSpaces flag using reflection
        setField(lexer, "ignoreSurroundingSpaces", true);
        assertTokenMatches(lexer.nextToken(token), Token.Type.TOKEN, "tokenWithSpaces");
        // Test when ignoreSurroundingSpaces is false and there are leading whitespaces
        createLexer("   tokenWithSpaces");
        setField(lexer, "ignoreSurroundingSpaces", false);
        assertTokenMatches(lexer.nextToken(token), Token.Type.TOKEN, "   tokenWithSpaces");
    }

    @Test
    void testNextTokenHandlingMixedContent() throws IOException {
        // Test when the input contains mixed content including tokens and comments
        createLexer("# This is a comment\nmixedContent,value");
        assertTokenMatches(lexer.nextToken(token), Token.Type.COMMENT, "This is a comment");
        assertTokenMatches(lexer.nextToken(token), Token.Type.TOKEN, "mixedContent");
    }

    @Test
    void testNextTokenHandlingCommentsWithTrailingSpaces() throws IOException {
        // Test when the character read indicates a comment start with trailing spaces
        createLexer("# Trailing comment    \nvalue");
        assertTokenMatches(lexer.nextToken(token), Token.Type.COMMENT, "Trailing comment    ");
    }

    @Test
    void testNextTokenEndOfFileWithContent() throws IOException {
        // Test when the last character read is EOF with content before it
        createLexer("content");
        // Read token
        lexer.nextToken(token);
        assertTokenMatches(lexer.nextToken(token), Token.Type.EOF, "");
    }

    @Test
    void testNextTokenHandlingEscapedCharacters() throws IOException {
        // Test when the character read indicates an escaped character
        createLexer("escaped\\nToken");
        assertTokenMatches(lexer.nextToken(token), Token.Type.TOKEN, "escapedToken");
    }

    @Test
    void testNextTokenHandlingMixedLineEndings() throws IOException {
        // Test when the input contains mixed line endings (CR, LF)
        createLexer("line1\r\nline2\nline3");
        assertTokenMatches(lexer.nextToken(token), Token.Type.TOKEN, "line1");
        assertTokenMatches(lexer.nextToken(token), Token.Type.EORECORD, "");
        assertTokenMatches(lexer.nextToken(token), Token.Type.TOKEN, "line2");
        assertTokenMatches(lexer.nextToken(token), Token.Type.EORECORD, "");
        assertTokenMatches(lexer.nextToken(token), Token.Type.TOKEN, "line3");
        assertTokenMatches(lexer.nextToken(token), Token.Type.EOF, "");
    }

    @Test
    void testNextTokenHandlingMultipleConsecutiveDelimiters() throws IOException {
        // Test when the input contains multiple consecutive delimiters
        createLexer(",,");
        assertTokenMatches(lexer.nextToken(token), Token.Type.TOKEN, "");
        assertTokenMatches(lexer.nextToken(token), Token.Type.TOKEN, "");
    }

    @Test
    void testNextTokenIOExceptionHandling() throws IOException {
        // Mock the ExtendedBufferedReader to throw IOException
        reader = mock(ExtendedBufferedReader.class);
        when(reader.read()).thenThrow(new IOException("Mocked IOException"));
        lexer = new Lexer(format, reader);
        // Test when an IOException is thrown by the reader during character reading
        assertThrows(IOException.class, () -> lexer.nextToken(token));
    }

    @Test
    void testNextTokenInvalidTokenHandling() throws IOException {
        // Test when the input character is invalid and should be handled as an INVALID token
        createLexer("invalidChar");
        // Assuming the first character is invalid
        assertTokenMatches(lexer.nextToken(token), Token.Type.INVALID, "");
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
│     ├─ testNextTokenIOExceptionHandling() ✔
│     ├─ testNextTokenHandlingMixedContent() ✘ expected: <COMMENT> but was: <EORECORD>
│     ├─ testNextTokenCommentHandling() ✘ expected: <COMMENT> but was: <EORECORD>
│     ├─ testNextTokenWithComment() ✘ expected: <COMMENT> but was: <EORECORD>
│     ├─ testNextTokenWithEmptyInput() ✔
│     ├─ testNextTokenInvalidTokenHandling() ✘ expected: <INVALID> but was: <EOF>
│     ├─ testNextTokenWithEscapedCharacters() ✘ expected: <,> but was: <a>
│     ├─ testNextTokenHandlingMixedLineEndings() ✘ expected: <TOKEN> but was: <EORECORD>
│     ├─ testNextTokenWithValidInput() ✘ expected: <b> but was: <a>
│     ├─ testNextTokenHandlingEmptyLines() ✔
│     ├─ testNextTokenHandlingCommentsWithTrailingSpaces() ✘ expected: <COMMENT> but was: <EORECORD>
│     ├─ testNextTokenEndOfFileWithContent() ✘ expected: <> but was: <content>
│     ├─ testNextTokenHandlingEscapedCharacters() ✘ expected: <TOKEN> but was: <EOF>
│     ├─ testNextTokenHandlingMultipleConsecutiveDelimiters() ✔
│     ├─ testNextTokenTokenParsing() ✘ expected: <EORECORD> but was: <TOKEN>
│     ├─ testNextTokenWhitespaceHandling() ✘ expected: <TOKEN> but was: <EOF>
│     └─ testNextTokenEndOfFileHandling() ✘ expected: <EOF> but was: <TOKEN>
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Failures (13):
  JUnit Jupiter:Lexer_nextToken_Test:testNextTokenHandlingMixedContent()
    MethodSource [className = 'org.apache.commons.csv.Lexer_nextToken_Test', methodName = 'testNextTokenHandlingMixedContent', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <COMMENT> but was: <EORECORD>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1142)
       org.apache.commons.csv.Lexer_nextToken_Test.assertTokenMatches(Lexer_nextToken_Test.java:48)
       org.apache.commons.csv.Lexer_nextToken_Test.testNextTokenHandlingMixedContent(Lexer_nextToken_Test.java:167)
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
       org.apache.commons.csv.Lexer_nextToken_Test.assertTokenMatches(Lexer_nextToken_Test.java:48)
       org.apache.commons.csv.Lexer_nextToken_Test.testNextTokenCommentHandling(Lexer_nextToken_Test.java:134)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       [...]
  JUnit Jupiter:Lexer_nextToken_Test:testNextTokenWithComment()
    MethodSource [className = 'org.apache.commons.csv.Lexer_nextToken_Test', methodName = 'testNextTokenWithComment', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <COMMENT> but was: <EORECORD>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1142)
       org.apache.commons.csv.Lexer_nextToken_Test.assertTokenMatches(Lexer_nextToken_Test.java:48)
       org.apache.commons.csv.Lexer_nextToken_Test.testNextTokenWithComment(Lexer_nextToken_Test.java:80)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       [...]
  JUnit Jupiter:Lexer_nextToken_Test:testNextTokenInvalidTokenHandling()
    MethodSource [className = 'org.apache.commons.csv.Lexer_nextToken_Test', methodName = 'testNextTokenInvalidTokenHandling', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <INVALID> but was: <EOF>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1142)
       org.apache.commons.csv.Lexer_nextToken_Test.assertTokenMatches(Lexer_nextToken_Test.java:48)
       org.apache.commons.csv.Lexer_nextToken_Test.testNextTokenInvalidTokenHandling(Lexer_nextToken_Test.java:229)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       [...]
  JUnit Jupiter:Lexer_nextToken_Test:testNextTokenWithEscapedCharacters()
    MethodSource [className = 'org.apache.commons.csv.Lexer_nextToken_Test', methodName = 'testNextTokenWithEscapedCharacters', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <,> but was: <a>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1142)
       org.apache.commons.csv.Lexer_nextToken_Test.assertTokenMatches(Lexer_nextToken_Test.java:49)
       org.apache.commons.csv.Lexer_nextToken_Test.testNextTokenWithEscapedCharacters(Lexer_nextToken_Test.java:90)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       [...]
  JUnit Jupiter:Lexer_nextToken_Test:testNextTokenHandlingMixedLineEndings()
    MethodSource [className = 'org.apache.commons.csv.Lexer_nextToken_Test', methodName = 'testNextTokenHandlingMixedLineEndings', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <TOKEN> but was: <EORECORD>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1142)
       org.apache.commons.csv.Lexer_nextToken_Test.assertTokenMatches(Lexer_nextToken_Test.java:48)
       org.apache.commons.csv.Lexer_nextToken_Test.testNextTokenHandlingMixedLineEndings(Lexer_nextToken_Test.java:198)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       [...]
  JUnit Jupiter:Lexer_nextToken_Test:testNextTokenWithValidInput()
    MethodSource [className = 'org.apache.commons.csv.Lexer_nextToken_Test', methodName = 'testNextTokenWithValidInput', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <b> but was: <a>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1142)
       org.apache.commons.csv.Lexer_nextToken_Test.assertTokenMatches(Lexer_nextToken_Test.java:49)
       org.apache.commons.csv.Lexer_nextToken_Test.testNextTokenWithValidInput(Lexer_nextToken_Test.java:66)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       [...]
  JUnit Jupiter:Lexer_nextToken_Test:testNextTokenHandlingCommentsWithTrailingSpaces()
    MethodSource [className = 'org.apache.commons.csv.Lexer_nextToken_Test', methodName = 'testNextTokenHandlingCommentsWithTrailingSpaces', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <COMMENT> but was: <EORECORD>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1142)
       org.apache.commons.csv.Lexer_nextToken_Test.assertTokenMatches(Lexer_nextToken_Test.java:48)
       org.apache.commons.csv.Lexer_nextToken_Test.testNextTokenHandlingCommentsWithTrailingSpaces(Lexer_nextToken_Test.java:175)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       [...]
  JUnit Jupiter:Lexer_nextToken_Test:testNextTokenEndOfFileWithContent()
    MethodSource [className = 'org.apache.commons.csv.Lexer_nextToken_Test', methodName = 'testNextTokenEndOfFileWithContent', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <> but was: <content>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1142)
       org.apache.commons.csv.Lexer_nextToken_Test.assertTokenMatches(Lexer_nextToken_Test.java:49)
       org.apache.commons.csv.Lexer_nextToken_Test.testNextTokenEndOfFileWithContent(Lexer_nextToken_Test.java:184)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       [...]
  JUnit Jupiter:Lexer_nextToken_Test:testNextTokenHandlingEscapedCharacters()
    MethodSource [className = 'org.apache.commons.csv.Lexer_nextToken_Test', methodName = 'testNextTokenHandlingEscapedCharacters', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <TOKEN> but was: <EOF>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1142)
       org.apache.commons.csv.Lexer_nextToken_Test.assertTokenMatches(Lexer_nextToken_Test.java:48)
       org.apache.commons.csv.Lexer_nextToken_Test.testNextTokenHandlingEscapedCharacters(Lexer_nextToken_Test.java:191)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       [...]
  JUnit Jupiter:Lexer_nextToken_Test:testNextTokenTokenParsing()
    MethodSource [className = 'org.apache.commons.csv.Lexer_nextToken_Test', methodName = 'testNextTokenTokenParsing', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <EORECORD> but was: <TOKEN>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1142)
       org.apache.commons.csv.Lexer_nextToken_Test.assertTokenMatches(Lexer_nextToken_Test.java:48)
       org.apache.commons.csv.Lexer_nextToken_Test.testNextTokenTokenParsing(Lexer_nextToken_Test.java:144)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       [...]
  JUnit Jupiter:Lexer_nextToken_Test:testNextTokenWhitespaceHandling()
    MethodSource [className = 'org.apache.commons.csv.Lexer_nextToken_Test', methodName = 'testNextTokenWhitespaceHandling', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <TOKEN> but was: <EOF>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1142)
       org.apache.commons.csv.Lexer_nextToken_Test.assertTokenMatches(Lexer_nextToken_Test.java:48)
       org.apache.commons.csv.Lexer_nextToken_Test.testNextTokenWhitespaceHandling(Lexer_nextToken_Test.java:156)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       [...]
  JUnit Jupiter:Lexer_nextToken_Test:testNextTokenEndOfFileHandling()
    MethodSource [className = 'org.apache.commons.csv.Lexer_nextToken_Test', methodName = 'testNextTokenEndOfFileHandling', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <EOF> but was: <TOKEN>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1142)
       org.apache.commons.csv.Lexer_nextToken_Test.assertTokenMatches(Lexer_nextToken_Test.java:48)
       org.apache.commons.csv.Lexer_nextToken_Test.testNextTokenEndOfFileHandling(Lexer_nextToken_Test.java:116)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       [...]

Test run finished after 442 ms
[         4 containers found      ]
[         0 containers skipped    ]
[         4 containers started    ]
[         0 containers aborted    ]
[         4 containers successful ]
[         0 containers failed     ]
[        17 tests found           ]
[         0 tests skipped         ]
[        17 tests started         ]
[         0 tests aborted         ]
[         4 tests successful      ]
[        13 tests failed          ]


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
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.type;
		java.lang.StringBuilder org.apache.commons.csv.Token.content;
		boolean org.apache.commons.csv.Token.isReady;
	related functions:
		method ` void reset()` returns `void`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`;
		method `public String toString()` returns `java.lang.String`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`, api document: 
     * Eases IDE debugging.
     *
     * @return a string helpful for debugging.
     ;
class `org.apache.commons.csv.Token.Type`:
	dependent fields:
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.INVALID;
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.TOKEN;
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.EORECORD;
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.COMMENT;
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.EOF;
	
class `org.apache.commons.csv.Lexer`:
	dependent fields:
		boolean org.apache.commons.csv.Lexer.isLastTokenDelimiter;
		org.apache.commons.csv.ExtendedBufferedReader org.apache.commons.csv.Lexer.reader;
		boolean org.apache.commons.csv.Lexer.ignoreSurroundingSpaces;
		boolean org.apache.commons.csv.Lexer.ignoreEmptyLines;
	related functions:
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
		method ` boolean readEndOfLine(int)` returns `boolean`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`, api document: 
     * Greedily accepts \n, \r and \r\n This checker consumes silently the second control-character...
     *
     * @return true if the given or next character is a line-terminator
     ;
		method ` boolean isDelimiter(int)` returns `boolean`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`, api document: 
     * Determine whether the next characters constitute a delimiter through {@link ExtendedBufferedReader#lookAhead(char[])}.
     *
     * @param ch
     *             the current character.
     * @return true if the next characters constitute a delimiter.
     * @throws IOException If an I/O error occurs.
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
