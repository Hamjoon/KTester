


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
import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class Lexer_parseEncapsulatedToken_Test {

    private Lexer lexer;

    private Token token;

    private CSVFormat format;

    private ExtendedBufferedReader reader;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the necessary objects before each test
        // example format with escaping
        format = CSVFormat.DEFAULT.withEscape('\\');
        // Mock the ExtendedBufferedReader
        reader = mock(ExtendedBufferedReader.class);
        // Initialize the Lexer with the mocked reader
        lexer = new Lexer(format, reader);
        // Initialize a new Token object
        token = new Token();
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup after each test, if necessary
        try {
            // Close the lexer to release resources
            lexer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Token invokeParseEncapsulatedToken(Token token) throws Exception {
        Method method = Lexer.class.getDeclaredMethod("parseEncapsulatedToken", Token.class);
        // Make the private method accessible
        method.setAccessible(true);
        return (Token) method.invoke(lexer, token);
    }

    @Test
    void testParseEncapsulatedToken() throws Exception {
        // Example input for the test
        String input = "\"test token\"";
        // Simulate reading characters
        when(reader.read()).thenReturn((int) '"', (int) 't', (int) 'e', (int) 's', (int) 't', (int) ' ', (int) 't', (int) 'o', (int) 'k', (int) 'e', (int) 'n', (int) '"', Constants.END_OF_STREAM);
        // Call the method under test
        Token resultToken = invokeParseEncapsulatedToken(token);
        // Verify the results
        assertNotNull(resultToken);
        assertTrue(resultToken.isQuoted);
        assertEquals("test token", resultToken.content.toString());
    }

    @Test
    void testParseEncapsulatedToken_EscapeDelimiter() throws Exception {
        // Test when an escape character is encountered followed by an escape delimiter
        when(reader.read()).thenReturn((int) '"', (int) 'a', (int) '\\', (int) '"', (int) '"', (int) 'b', (int) '"', Constants.END_OF_STREAM);
        Token resultToken = invokeParseEncapsulatedToken(token);
        assertEquals("\"ab\"", resultToken.content.toString());
        assertTrue(resultToken.isQuoted);
    }

    @Test
    void testParseEncapsulatedToken_ValidCharacterAfterEscape() throws Exception {
        // Test when an escape character is encountered followed by a valid character
        when(reader.read()).thenReturn((int) '"', (int) 'a', (int) '\\', (int) 'b', (int) 'c', (int) '"', Constants.END_OF_STREAM);
        Token resultToken = invokeParseEncapsulatedToken(token);
        assertEquals("abc", resultToken.content.toString());
        assertTrue(resultToken.isQuoted);
    }

    @Test
    void testParseEncapsulatedToken_EOFReachedBeforeFinish() throws Exception {
        // Test when an escape character is encountered, but the end of stream is reached
        when(reader.read()).thenReturn((int) '"', (int) 'a', (int) '\\', (int) 'b', Constants.END_OF_STREAM);
        assertThrows(IOException.class, () -> invokeParseEncapsulatedToken(token));
    }

    @Test
    void testParseEncapsulatedToken_QuoteCharacterFollowedByDelimiter() throws Exception {
        // Test when a quote character is encountered and a delimiter follows
        when(reader.read()).thenReturn((int) '"', (int) 'a', (int) 'b', (int) 'c', (int) ',', Constants.END_OF_STREAM);
        Token resultToken = invokeParseEncapsulatedToken(token);
        assertEquals("abc", resultToken.content.toString());
        assertEquals(Token.Type.TOKEN, resultToken.type);
    }

    @Test
    void testParseEncapsulatedToken_QuoteCharacterEOF() throws Exception {
        // Test when a quote character is encountered and end of file is reached
        when(reader.read()).thenReturn((int) '"', (int) 'a', (int) 'b', (int) 'c', Constants.END_OF_STREAM);
        Token resultToken = invokeParseEncapsulatedToken(token);
        assertEquals("abc", resultToken.content.toString());
        assertEquals(Token.Type.EOF, resultToken.type);
        assertTrue(resultToken.isReady);
    }

    @Test
    void testParseEncapsulatedToken_QuoteCharacterNewLine() throws Exception {
        // Test when a quote character is encountered and a new line is read
        when(reader.read()).thenReturn((int) '"', (int) 'a', (int) 'b', (int) 'c', (int) '\n', Constants.END_OF_STREAM);
        Token resultToken = invokeParseEncapsulatedToken(token);
        assertEquals("abc", resultToken.content.toString());
        assertEquals(Token.Type.EORECORD, resultToken.type);
    }

    @Test
    void testParseEncapsulatedToken_InvalidCharacterBeforeDelimiter() throws Exception {
        // Test when a quote character is encountered and an invalid character is found before the delimiter
        when(reader.read()).thenReturn((int) '"', (int) 'a', (int) '!', (int) ',', Constants.END_OF_STREAM);
        assertThrows(IOException.class, () -> invokeParseEncapsulatedToken(token));
    }

    @Test
    void testParseEncapsulatedToken_EmptyToken() throws Exception {
        // Test when an empty token is processed
        when(reader.read()).thenReturn((int) '"', Constants.END_OF_STREAM);
        assertThrows(IOException.class, () -> invokeParseEncapsulatedToken(token));
    }

    @Test
    void testParseEncapsulatedToken_NormalCharacters() throws Exception {
        // Test when normal characters are read without encountering special conditions
        when(reader.read()).thenReturn((int) '"', (int) 'n', (int) 'o', (int) 'r', (int) 'm', (int) 'a', (int) 'l', (int) '"', (int) ',', Constants.END_OF_STREAM);
        Token resultToken = invokeParseEncapsulatedToken(token);
        assertEquals("normal", resultToken.content.toString());
    }

    @Test
    void testParseEncapsulatedToken_WhitespaceHandling() throws Exception {
        // Test when leading and trailing whitespace is present
        when(reader.read()).thenReturn((int) '"', (int) ' ', (int) 't', (int) 'o', (int) 'k', (int) 'e', (int) 'n', (int) ' ', (int) '"', (int) ',', Constants.END_OF_STREAM);
        Token resultToken = invokeParseEncapsulatedToken(token);
        assertEquals(" token ", resultToken.content.toString());
    }

    @Test
    void testParseEncapsulatedToken_InvalidCharacters() throws Exception {
        // Test when an invalid character is encountered between a token and the delimiter
        when(reader.read()).thenReturn((int) '"', (int) 'a', (int) '@', (int) ',', Constants.END_OF_STREAM);
        assertThrows(IOException.class, () -> invokeParseEncapsulatedToken(token));
    }
}

```

Corresponding execution feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended

╷
├─ JUnit Jupiter ✔
│  └─ Lexer_parseEncapsulatedToken_Test ✔
│     ├─ testParseEncapsulatedToken_EmptyToken() ✘ Expected java.io.IOException to be thrown, but nothing was thrown.
│     ├─ testParseEncapsulatedToken_InvalidCharacters() ✘ Unexpected exception type thrown, expected: <java.io.IOException> but was: <java.lang.reflect.InvocationTargetException>
│     ├─ testParseEncapsulatedToken_QuoteCharacterFollowedByDelimiter() ✘ (line 0) invalid char between encapsulated token and delimiter
│     ├─ testParseEncapsulatedToken_ValidCharacterAfterEscape() ✘ (line 0) invalid char between encapsulated token and delimiter
│     ├─ testParseEncapsulatedToken_QuoteCharacterEOF() ✘ (line 0) invalid char between encapsulated token and delimiter
│     ├─ testParseEncapsulatedToken_EOFReachedBeforeFinish() ✘ Unexpected exception type thrown, expected: <java.io.IOException> but was: <java.lang.reflect.InvocationTargetException>
│     ├─ testParseEncapsulatedToken_InvalidCharacterBeforeDelimiter() ✘ Unexpected exception type thrown, expected: <java.io.IOException> but was: <java.lang.reflect.InvocationTargetException>
│     ├─ testParseEncapsulatedToken_WhitespaceHandling() ✘ (line 0) invalid char between encapsulated token and delimiter
│     ├─ testParseEncapsulatedToken_NormalCharacters() ✘ (line 0) invalid char between encapsulated token and delimiter
│     ├─ testParseEncapsulatedToken_QuoteCharacterNewLine() ✘ (line 0) invalid char between encapsulated token and delimiter
│     ├─ testParseEncapsulatedToken_EscapeDelimiter() ✘ (line 0) invalid char between encapsulated token and delimiter
│     └─ testParseEncapsulatedToken() ✘ (line 0) invalid char between encapsulated token and delimiter
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Failures (12):
  JUnit Jupiter:Lexer_parseEncapsulatedToken_Test:testParseEncapsulatedToken_EmptyToken()
    MethodSource [className = 'org.apache.commons.csv.Lexer_parseEncapsulatedToken_Test', methodName = 'testParseEncapsulatedToken_EmptyToken', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Expected java.io.IOException to be thrown, but nothing was thrown.
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:73)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3083)
       org.apache.commons.csv.Lexer_parseEncapsulatedToken_Test.testParseEncapsulatedToken_EmptyToken(Lexer_parseEncapsulatedToken_Test.java:132)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:727)
       [...]
  JUnit Jupiter:Lexer_parseEncapsulatedToken_Test:testParseEncapsulatedToken_InvalidCharacters()
    MethodSource [className = 'org.apache.commons.csv.Lexer_parseEncapsulatedToken_Test', methodName = 'testParseEncapsulatedToken_InvalidCharacters', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Unexpected exception type thrown, expected: <java.io.IOException> but was: <java.lang.reflect.InvocationTargetException>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:67)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3083)
       org.apache.commons.csv.Lexer_parseEncapsulatedToken_Test.testParseEncapsulatedToken_InvalidCharacters(Lexer_parseEncapsulatedToken_Test.java:155)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.apache.commons.csv.Lexer_parseEncapsulatedToken_Test.invokeParseEncapsulatedToken(Lexer_parseEncapsulatedToken_Test.java:51)
       [...]
     Caused by: java.io.IOException: (line 0) invalid char between encapsulated token and delimiter
       org.apache.commons.csv.Lexer.parseEncapsulatedToken(Lexer.java:369)
       [...]
  JUnit Jupiter:Lexer_parseEncapsulatedToken_Test:testParseEncapsulatedToken_QuoteCharacterFollowedByDelimiter()
    MethodSource [className = 'org.apache.commons.csv.Lexer_parseEncapsulatedToken_Test', methodName = 'testParseEncapsulatedToken_QuoteCharacterFollowedByDelimiter', methodParameterTypes = '']
    => java.io.IOException: (line 0) invalid char between encapsulated token and delimiter
       org.apache.commons.csv.Lexer.parseEncapsulatedToken(Lexer.java:369)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.apache.commons.csv.Lexer_parseEncapsulatedToken_Test.invokeParseEncapsulatedToken(Lexer_parseEncapsulatedToken_Test.java:51)
       org.apache.commons.csv.Lexer_parseEncapsulatedToken_Test.testParseEncapsulatedToken_QuoteCharacterFollowedByDelimiter(Lexer_parseEncapsulatedToken_Test.java:97)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:Lexer_parseEncapsulatedToken_Test:testParseEncapsulatedToken_ValidCharacterAfterEscape()
    MethodSource [className = 'org.apache.commons.csv.Lexer_parseEncapsulatedToken_Test', methodName = 'testParseEncapsulatedToken_ValidCharacterAfterEscape', methodParameterTypes = '']
    => java.io.IOException: (line 0) invalid char between encapsulated token and delimiter
       org.apache.commons.csv.Lexer.parseEncapsulatedToken(Lexer.java:369)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.apache.commons.csv.Lexer_parseEncapsulatedToken_Test.invokeParseEncapsulatedToken(Lexer_parseEncapsulatedToken_Test.java:51)
       org.apache.commons.csv.Lexer_parseEncapsulatedToken_Test.testParseEncapsulatedToken_ValidCharacterAfterEscape(Lexer_parseEncapsulatedToken_Test.java:81)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:Lexer_parseEncapsulatedToken_Test:testParseEncapsulatedToken_QuoteCharacterEOF()
    MethodSource [className = 'org.apache.commons.csv.Lexer_parseEncapsulatedToken_Test', methodName = 'testParseEncapsulatedToken_QuoteCharacterEOF', methodParameterTypes = '']
    => java.io.IOException: (line 0) invalid char between encapsulated token and delimiter
       org.apache.commons.csv.Lexer.parseEncapsulatedToken(Lexer.java:369)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.apache.commons.csv.Lexer_parseEncapsulatedToken_Test.invokeParseEncapsulatedToken(Lexer_parseEncapsulatedToken_Test.java:51)
       org.apache.commons.csv.Lexer_parseEncapsulatedToken_Test.testParseEncapsulatedToken_QuoteCharacterEOF(Lexer_parseEncapsulatedToken_Test.java:106)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:Lexer_parseEncapsulatedToken_Test:testParseEncapsulatedToken_EOFReachedBeforeFinish()
    MethodSource [className = 'org.apache.commons.csv.Lexer_parseEncapsulatedToken_Test', methodName = 'testParseEncapsulatedToken_EOFReachedBeforeFinish', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Unexpected exception type thrown, expected: <java.io.IOException> but was: <java.lang.reflect.InvocationTargetException>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:67)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3083)
       org.apache.commons.csv.Lexer_parseEncapsulatedToken_Test.testParseEncapsulatedToken_EOFReachedBeforeFinish(Lexer_parseEncapsulatedToken_Test.java:90)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.apache.commons.csv.Lexer_parseEncapsulatedToken_Test.invokeParseEncapsulatedToken(Lexer_parseEncapsulatedToken_Test.java:51)
       [...]
     Caused by: java.io.IOException: (line 0) invalid char between encapsulated token and delimiter
       org.apache.commons.csv.Lexer.parseEncapsulatedToken(Lexer.java:369)
       [...]
  JUnit Jupiter:Lexer_parseEncapsulatedToken_Test:testParseEncapsulatedToken_InvalidCharacterBeforeDelimiter()
    MethodSource [className = 'org.apache.commons.csv.Lexer_parseEncapsulatedToken_Test', methodName = 'testParseEncapsulatedToken_InvalidCharacterBeforeDelimiter', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Unexpected exception type thrown, expected: <java.io.IOException> but was: <java.lang.reflect.InvocationTargetException>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:67)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3083)
       org.apache.commons.csv.Lexer_parseEncapsulatedToken_Test.testParseEncapsulatedToken_InvalidCharacterBeforeDelimiter(Lexer_parseEncapsulatedToken_Test.java:125)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.apache.commons.csv.Lexer_parseEncapsulatedToken_Test.invokeParseEncapsulatedToken(Lexer_parseEncapsulatedToken_Test.java:51)
       [...]
     Caused by: java.io.IOException: (line 0) invalid char between encapsulated token and delimiter
       org.apache.commons.csv.Lexer.parseEncapsulatedToken(Lexer.java:369)
       [...]
  JUnit Jupiter:Lexer_parseEncapsulatedToken_Test:testParseEncapsulatedToken_WhitespaceHandling()
    MethodSource [className = 'org.apache.commons.csv.Lexer_parseEncapsulatedToken_Test', methodName = 'testParseEncapsulatedToken_WhitespaceHandling', methodParameterTypes = '']
    => java.io.IOException: (line 0) invalid char between encapsulated token and delimiter
       org.apache.commons.csv.Lexer.parseEncapsulatedToken(Lexer.java:369)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.apache.commons.csv.Lexer_parseEncapsulatedToken_Test.invokeParseEncapsulatedToken(Lexer_parseEncapsulatedToken_Test.java:51)
       org.apache.commons.csv.Lexer_parseEncapsulatedToken_Test.testParseEncapsulatedToken_WhitespaceHandling(Lexer_parseEncapsulatedToken_Test.java:147)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:Lexer_parseEncapsulatedToken_Test:testParseEncapsulatedToken_NormalCharacters()
    MethodSource [className = 'org.apache.commons.csv.Lexer_parseEncapsulatedToken_Test', methodName = 'testParseEncapsulatedToken_NormalCharacters', methodParameterTypes = '']
    => java.io.IOException: (line 0) invalid char between encapsulated token and delimiter
       org.apache.commons.csv.Lexer.parseEncapsulatedToken(Lexer.java:369)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.apache.commons.csv.Lexer_parseEncapsulatedToken_Test.invokeParseEncapsulatedToken(Lexer_parseEncapsulatedToken_Test.java:51)
       org.apache.commons.csv.Lexer_parseEncapsulatedToken_Test.testParseEncapsulatedToken_NormalCharacters(Lexer_parseEncapsulatedToken_Test.java:139)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:Lexer_parseEncapsulatedToken_Test:testParseEncapsulatedToken_QuoteCharacterNewLine()
    MethodSource [className = 'org.apache.commons.csv.Lexer_parseEncapsulatedToken_Test', methodName = 'testParseEncapsulatedToken_QuoteCharacterNewLine', methodParameterTypes = '']
    => java.io.IOException: (line 0) invalid char between encapsulated token and delimiter
       org.apache.commons.csv.Lexer.parseEncapsulatedToken(Lexer.java:369)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.apache.commons.csv.Lexer_parseEncapsulatedToken_Test.invokeParseEncapsulatedToken(Lexer_parseEncapsulatedToken_Test.java:51)
       org.apache.commons.csv.Lexer_parseEncapsulatedToken_Test.testParseEncapsulatedToken_QuoteCharacterNewLine(Lexer_parseEncapsulatedToken_Test.java:116)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:Lexer_parseEncapsulatedToken_Test:testParseEncapsulatedToken_EscapeDelimiter()
    MethodSource [className = 'org.apache.commons.csv.Lexer_parseEncapsulatedToken_Test', methodName = 'testParseEncapsulatedToken_EscapeDelimiter', methodParameterTypes = '']
    => java.io.IOException: (line 0) invalid char between encapsulated token and delimiter
       org.apache.commons.csv.Lexer.parseEncapsulatedToken(Lexer.java:369)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.apache.commons.csv.Lexer_parseEncapsulatedToken_Test.invokeParseEncapsulatedToken(Lexer_parseEncapsulatedToken_Test.java:51)
       org.apache.commons.csv.Lexer_parseEncapsulatedToken_Test.testParseEncapsulatedToken_EscapeDelimiter(Lexer_parseEncapsulatedToken_Test.java:72)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:Lexer_parseEncapsulatedToken_Test:testParseEncapsulatedToken()
    MethodSource [className = 'org.apache.commons.csv.Lexer_parseEncapsulatedToken_Test', methodName = 'testParseEncapsulatedToken', methodParameterTypes = '']
    => java.io.IOException: (line 0) invalid char between encapsulated token and delimiter
       org.apache.commons.csv.Lexer.parseEncapsulatedToken(Lexer.java:369)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.apache.commons.csv.Lexer_parseEncapsulatedToken_Test.invokeParseEncapsulatedToken(Lexer_parseEncapsulatedToken_Test.java:51)
       org.apache.commons.csv.Lexer_parseEncapsulatedToken_Test.testParseEncapsulatedToken(Lexer_parseEncapsulatedToken_Test.java:61)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]

Test run finished after 538 ms
[         4 containers found      ]
[         0 containers skipped    ]
[         4 containers started    ]
[         0 containers aborted    ]
[         4 containers successful ]
[         0 containers failed     ]
[        12 tests found           ]
[         0 tests skipped         ]
[        12 tests started         ]
[         0 tests aborted         ]
[         0 tests successful      ]
[        12 tests failed          ]


```


And you can use the following related information to assist you in your analysis and correction:

api document of class org.apache.commons.csv.Lexer: 

 * Lexical analyzer.
 

api document of method parseEncapsulatedToken(Token): 

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
     

return type: 
org.apache.commons.csv.Token

dependent classes: 
class `org.apache.commons.csv.Token`:
	dependent fields:
		boolean org.apache.commons.csv.Token.isReady;
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.type;
		java.lang.StringBuilder org.apache.commons.csv.Token.content;
		boolean org.apache.commons.csv.Token.isQuoted;
	related functions:
		method ` void reset()` returns `void`, related with `org.apache.commons.csv.Lexer.parseEncapsulatedToken(Token)`;
class `org.apache.commons.csv.Token.Type`:
	dependent fields:
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.EORECORD;
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.TOKEN;
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.EOF;
	
class `org.apache.commons.csv.Lexer`:
	dependent fields:
		char[] org.apache.commons.csv.Lexer.delimiter;
		org.apache.commons.csv.ExtendedBufferedReader org.apache.commons.csv.Lexer.reader;
	related functions:
		method ` int readEscape()` returns `int`, related with `org.apache.commons.csv.Lexer.parseEncapsulatedToken(Token)`, api document: 
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
		method `private Token parseSimpleToken(Token, int)` returns `org.apache.commons.csv.Token`, related with `org.apache.commons.csv.Lexer.parseEncapsulatedToken(Token)`, api document: 
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
		method ` boolean isDelimiter(int)` returns `boolean`, related with `org.apache.commons.csv.Lexer.parseEncapsulatedToken(Token)`, api document: 
     * Determine whether the next characters constitute a delimiter through {@link ExtendedBufferedReader#lookAhead(char[])}.
     *
     * @param ch
     *             the current character.
     * @return true if the next characters constitute a delimiter.
     * @throws IOException If an I/O error occurs.
     ;
		method ` Token nextToken(Token)` returns `org.apache.commons.csv.Token`, related with `org.apache.commons.csv.Lexer.parseEncapsulatedToken(Token)`, api document: 
     * Returns the next token.
     * <p>
     * A token corresponds to a term, a record change or an end-of-file indicator.
     * </p>
     *
     * @param token
     *            an existing Token object to reuse. The caller is responsible to initialize the Token.
     * @return the next token found.
     * @throws IOException on stream access error.
     ;
		method ` boolean readEndOfLine(int)` returns `boolean`, related with `org.apache.commons.csv.Lexer.parseEncapsulatedToken(Token)`, api document: 
     * Greedily accepts \n, \r and \r\n This checker consumes silently the second control-character...
     *
     * @return true if the given or next character is a line-terminator
     ;
class `org.apache.commons.csv.Constants`:
	dependent fields:
		int org.apache.commons.csv.Constants.END_OF_STREAM;
	
class `org.apache.commons.csv.CSVParser`:
	related functions:
		method ` CSVRecord nextRecord()` returns `org.apache.commons.csv.CSVRecord`, related with `org.apache.commons.csv.Lexer.parseEncapsulatedToken(Token)`, api document: 
     * Parses the next record from the current point in the stream.
     *
     * @return the record as an array of values, or {@code null} if the end of the stream has been reached
     * @throws IOException
     *             on parse error or input read-failure
     ;
class `org.apache.commons.csv.ExtendedBufferedReader`:
	related functions:
		method `public String readLine()` returns `java.lang.String`, related with `org.apache.commons.csv.Lexer.parseEncapsulatedToken(Token)`, api document: 
     * Gets the next line, dropping the line terminator(s). This method should only be called when processing a
     * comment, otherwise information can be lost.
     * <p>
     * Increments {@link #eolCounter} and updates {@link #position}.
     * </p>
     * <p>
     * Sets {@link #lastChar} to {@link Constants#END_OF_STREAM} at EOF, otherwise the last EOL character.
     * </p>
     *
     * @return the line that was read, or null if reached EOF.
     ;



Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 
