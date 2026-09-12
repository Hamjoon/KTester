


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
import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class Lexer_nextToken_Test {

    private CSVFormat csvFormat;

    private ExtendedBufferedReader extendedBufferedReader;

    private Lexer lexer;

    private Token token;

    @BeforeEach
    void setupBeforeEach() {
        csvFormat = CSVFormat.DEFAULT.withIgnoreEmptyLines(false);
        extendedBufferedReader = mock(ExtendedBufferedReader.class);
        lexer = new Lexer(csvFormat, extendedBufferedReader);
        token = new Token();
    }

    @Test
    void testNextTokenWithEOF() throws IOException {
        // Simulate EOF
        when(extendedBufferedReader.read()).thenReturn(-1);
        when(extendedBufferedReader.getLastChar()).thenReturn(-1);
        Token result = lexer.nextToken(token);
        assertEquals(Token.Type.EOF, result.type);
    }

    @Test
    void testNextTokenWithComment() throws IOException {
        // Simulate reading a comment line
        when(extendedBufferedReader.read()).thenReturn((int) '#', (int) 'c', (int) 'o', (int) 'm', (int) 'm', (int) 'e', (int) 'n', (int) 't', (int) '\n', -1);
        when(extendedBufferedReader.getLastChar()).thenReturn((int) '\n');
        Token result = lexer.nextToken(token);
        assertEquals(Token.Type.COMMENT, result.type);
        assertEquals("comment", result.content.toString());
    }

    @Test
    void testNextTokenWithDelimiter() throws IOException {
        // Simulate reading a delimiter
        when(extendedBufferedReader.read()).thenReturn((int) ',');
        Token result = lexer.nextToken(token);
        assertEquals(Token.Type.TOKEN, result.type);
    }

    @Test
    void testNextTokenWithEndOfLine() throws IOException {
        // Simulate reading an end of line
        when(extendedBufferedReader.read()).thenReturn((int) '\n');
        when(extendedBufferedReader.getLastChar()).thenReturn((int) '\n');
        Token result = lexer.nextToken(token);
        assertEquals(Token.Type.EORECORD, result.type);
    }

    @Test
    void testNextTokenWithEOFAndData() throws IOException {
        // Simulate EOF with data present
        when(extendedBufferedReader.read()).thenReturn(-1);
        when(extendedBufferedReader.getLastChar()).thenReturn((int) 'd');
        // Set the token as ready to simulate data presence
        token.isReady = true;
        Token result = lexer.nextToken(token);
        assertEquals(Token.Type.EOF, result.type);
        assertTrue(result.isReady);
    }

    @Test
    void testNextTokenWithQuoteChar() throws Exception {
        // Simulate reading a quoted token
        when(extendedBufferedReader.read()).thenReturn((int) '"');
        Method parseEncapsulatedToken = Lexer.class.getDeclaredMethod("parseEncapsulatedToken", Token.class);
        parseEncapsulatedToken.setAccessible(true);
        // Mock the private method parseEncapsulatedToken
        Token mockToken = new Token();
        mockToken.content.append("quoted");
        when(lexer.nextToken(token)).thenReturn(mockToken);
        Token result = (Token) parseEncapsulatedToken.invoke(lexer, token);
        assertEquals("quoted", result.content.toString());
    }

    @Test
    void testNextTokenWithSimpleToken() throws Exception {
        // Simulate reading a simple token
        when(extendedBufferedReader.read()).thenReturn((int) 'a');
        Method parseSimpleToken = Lexer.class.getDeclaredMethod("parseSimpleToken", Token.class, int.class);
        parseSimpleToken.setAccessible(true);
        // Mock the private method parseSimpleToken
        Token mockToken = new Token();
        mockToken.content.append("simple");
        when(lexer.nextToken(token)).thenReturn(mockToken);
        Token result = (Token) parseSimpleToken.invoke(lexer, token, (int) 'a');
        assertEquals("simple", result.content.toString());
    }

    @Test
    void testNextTokenWithIgnoreEmptyLines() throws IOException {
        // Simulate empty lines at end of file
        lexer = new Lexer(CSVFormat.DEFAULT.withIgnoreEmptyLines(true), extendedBufferedReader);
        when(extendedBufferedReader.read()).thenReturn((int) '\n', -1);
        when(extendedBufferedReader.getLastChar()).thenReturn((int) '\n');
        Token result = lexer.nextToken(token);
        assertEquals(Token.Type.EOF, result.type);
    }

    @Test
    void testNextTokenWithLeadingSpacesAndDelimiter() throws IOException {
        // Simulate leading spaces followed by a delimiter
        lexer = new Lexer(CSVFormat.DEFAULT.withIgnoreSurroundingSpaces(true), extendedBufferedReader);
        when(extendedBufferedReader.read()).thenReturn((int) ' ', (int) ' ', (int) ',');
        Token result = lexer.nextToken(token);
        assertEquals(Token.Type.TOKEN, result.type);
    }

    @Test
    void testNextTokenWithLeadingSpacesAndNonDelimiter() throws Exception {
        // Simulate leading spaces followed by a non-delimiter character
        lexer = new Lexer(CSVFormat.DEFAULT.withIgnoreSurroundingSpaces(true), extendedBufferedReader);
        when(extendedBufferedReader.read()).thenReturn((int) ' ', (int) ' ', (int) 'a');
        Method parseSimpleToken = Lexer.class.getDeclaredMethod("parseSimpleToken", Token.class, int.class);
        parseSimpleToken.setAccessible(true);
        // Mock the private method parseSimpleToken
        Token mockToken = new Token();
        mockToken.content.append("a");
        when(lexer.nextToken(token)).thenReturn(mockToken);
        Token result = (Token) parseSimpleToken.invoke(lexer, token, (int) 'a');
        assertEquals("a", result.content.toString());
    }

    @Test
    void testNextTokenWithLeadingSpacesAndEOL() throws IOException {
        // Simulate leading spaces followed by an end of line
        lexer = new Lexer(CSVFormat.DEFAULT.withIgnoreSurroundingSpaces(true), extendedBufferedReader);
        when(extendedBufferedReader.read()).thenReturn((int) ' ', (int) ' ', (int) '\n');
        when(extendedBufferedReader.getLastChar()).thenReturn((int) '\n');
        Token result = lexer.nextToken(token);
        assertEquals(Token.Type.EORECORD, result.type);
    }

    @Test
    void testNextTokenWithTrimmedComment() throws IOException {
        // Simulate a comment with leading and trailing spaces
        when(extendedBufferedReader.read()).thenReturn((int) '#', (int) ' ', (int) 'c', (int) 'o', (int) 'm', (int) 'm', (int) 'e', (int) 'n', (int) 't', (int) ' ', (int) '\n', -1);
        when(extendedBufferedReader.getLastChar()).thenReturn((int) '\n');
        Token result = lexer.nextToken(token);
        assertEquals(Token.Type.COMMENT, result.type);
        assertEquals("comment", result.content.toString().trim());
    }

    @Test
    void testNextTokenWithNullToken() {
        // Test passing a null token
        assertThrows(NullPointerException.class, () -> lexer.nextToken(null));
    }

    @Test
    void testNextTokenWithIOException() throws IOException {
        // Simulate IOException during reading
        when(extendedBufferedReader.read()).thenThrow(IOException.class);
        assertThrows(IOException.class, () -> lexer.nextToken(token));
    }
}

```

Corresponding execution feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:569)
	at org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:727)
	at org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
	at org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
	at org.junit.jupiter.engine.extension.TimeoutExtension.intercept(TimeoutExtension.java:156)
	at org.junit.jupiter.engine.extension.TimeoutExtension.interceptTestableMethod(TimeoutExtension.java:147)
	at org.junit.jupiter.engine.extension.TimeoutExtension.interceptTestMethod(TimeoutExtension.java:86)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor$$Lambda$176/0x000000900109b4b8.apply(Unknown Source)
	at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker$ReflectiveInterceptorCall.lambda$ofVoidMethod$0(InterceptingExecutableInvoker.java:103)
	at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker$ReflectiveInterceptorCall$$Lambda$177/0x000000900109b8d8.apply(Unknown Source)
	at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.lambda$invoke$0(InterceptingExecutableInvoker.java:93)
	at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker$$Lambda$337/0x00000090010d0af8.apply(Unknown Source)
	at org.junit.jupiter.engine.execution.InvocationInterceptorChain$InterceptedInvocation.proceed(InvocationInterceptorChain.java:106)
	at org.junit.jupiter.engine.execution.InvocationInterceptorChain.proceed(InvocationInterceptorChain.java:64)
	at org.junit.jupiter.engine.execution.InvocationInterceptorChain.chainAndInvoke(InvocationInterceptorChain.java:45)
	at org.junit.jupiter.engine.execution.InvocationInterceptorChain.invoke(InvocationInterceptorChain.java:37)
	at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.invoke(InterceptingExecutableInvoker.java:92)
	at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.invoke(InterceptingExecutableInvoker.java:86)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$invokeTestMethod$7(TestMethodTestDescriptor.java:217)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor$$Lambda$397/0x000000900123c000.execute(Unknown Source)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.invokeTestMethod(TestMethodTestDescriptor.java:213)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:138)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:68)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:151)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask$$Lambda$276/0x00000090010c29e8.execute(Unknown Source)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:141)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask$$Lambda$275/0x00000090010c27c0.invoke(Unknown Source)


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
		boolean org.apache.commons.csv.Token.isReady;
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.type;
	related functions:
		method `public String toString()` returns `java.lang.String`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`, api document: 
     * Eases IDE debugging.
     *
     * @return a string helpful for debugging.
     ;
		method ` void reset()` returns `void`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`;
class `org.apache.commons.csv.Token.Type`:
	dependent fields:
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.COMMENT;
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.TOKEN;
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.INVALID;
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.EOF;
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.EORECORD;
	
class `org.apache.commons.csv.Lexer`:
	dependent fields:
		boolean org.apache.commons.csv.Lexer.ignoreSurroundingSpaces;
		org.apache.commons.csv.ExtendedBufferedReader org.apache.commons.csv.Lexer.reader;
		boolean org.apache.commons.csv.Lexer.ignoreEmptyLines;
		boolean org.apache.commons.csv.Lexer.isLastTokenDelimiter;
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
