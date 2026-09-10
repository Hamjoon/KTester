


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

    private CSVFormat csvFormat;

    private ExtendedBufferedReader reader;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the CSVFormat and ExtendedBufferedReader here
        csvFormat = CSVFormat.DEFAULT.withEscape('\\');
        // Mocking the reader for testing
        reader = mock(ExtendedBufferedReader.class);
        lexer = new Lexer(csvFormat, reader);
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup resources if necessary
        try {
            lexer.close();
        } catch (IOException e) {
            fail("Failed to close lexer: " + e.getMessage());
        }
    }

    private void invokeParseEncapsulatedToken(Token token) throws Exception {
        Method method = Lexer.class.getDeclaredMethod("parseEncapsulatedToken", Token.class);
        method.setAccessible(true);
        method.invoke(lexer, token);
    }

    @Test
    void testParseEncapsulatedToken() throws Exception {
        // Given
        Token token = new Token();
        // When
        invokeParseEncapsulatedToken(token);
        // Then
        // Add assertions to verify the expected behavior of the token after parsing
        // Example: assertEquals(expectedValue, token.content.toString());
    }

//     @Test
//     void testParseEncapsulatedToken_EscapeCharacters() throws Exception {
//         // Test when an escape character is followed by an escape delimiter
//         Token token = new Token();
//         // Escape character followed by escape delimiter
//         when(reader.read()).thenReturn((int) '\\', (int) ';');
//         invokeParseEncapsulatedToken(token);
//         assertTrue(token.content.toString().contains(";"), "Expected token.content to contain escaped delimiter");
//         // Test when an escape character is followed by a valid character
//         token = new Token();
//         // Escape character followed by valid character
//         when(reader.read()).thenReturn((int) '\\', (int) 'a');
//         invokeParseEncapsulatedToken(token);
//         assertTrue(token.content.toString().contains("a"), "Expected token.content to contain the unescaped character");
//         // Test when an escape character is followed by end of stream
//         token = new Token();
//         // Escape character followed by end of stream
//         when(reader.read()).thenReturn((int) '\\', Constants.END_OF_STREAM);
//         Exception exception = assertThrows(IOException.class, () -> invokeParseEncapsulatedToken(token));
//         assertTrue(exception.getMessage().contains("unexpected char after escape"), "Expected IOException with correct message");
//     }

//     @Test
//     void testParseEncapsulatedToken_QuotingLogic() throws Exception {
//         // Test when a quote character is followed by another quote character
//         Token token = new Token();
//         // Quote followed by quote, then character
//         when(reader.read()).thenReturn((int) '"', (int) '"', (int) 'b', (int) ';');
//         invokeParseEncapsulatedToken(token);
//         assertTrue(token.content.toString().contains("b"), "Expected token.content to contain the character read after a quote character");
//         // Test when a quote character is followed by a delimiter
//         token = new Token();
//         // Quote followed by delimiter
//         when(reader.read()).thenReturn((int) '"', (int) ';');
//         invokeParseEncapsulatedToken(token);
//         assertEquals(Token.Type.TOKEN, token.type, "Expected token.type to be TOKEN");
//         // Test when a quote character is followed by end of file
//         token = new Token();
//         // Quote followed by EOF
//         when(reader.read()).thenReturn((int) '"', Constants.END_OF_STREAM);
//         invokeParseEncapsulatedToken(token);
//         assertEquals(Token.Type.EOF, token.type, "Expected token.type to be EOF");
//         assertTrue(token.isReady, "Expected token.isReady to be true");
//         // Test when a non-whitespace character is found between the token and the next delimiter
//         token = new Token();
//         // Invalid character found
//         when(reader.read()).thenReturn((int) '"', (int) 'x', (int) ';');
//         Exception exception = assertThrows(IOException.class, () -> invokeParseEncapsulatedToken(token));
//         assertTrue(exception.getMessage().contains("invalid char between encapsulated token and delimiter"), "Expected IOException with correct message");
//     }

    @Test
    void testParseEncapsulatedToken_EndOfFileHandling() throws IOException {
        // Test when EOF is reached before the token is finished
        Token token = new Token();
        // End of file reached
        when(reader.read()).thenReturn(Constants.END_OF_STREAM);
        Exception exception = assertThrows(IOException.class, () -> invokeParseEncapsulatedToken(token));
        assertTrue(exception.getMessage().contains("EOF reached before encapsulated token finished"), "Expected IOException with correct message");
    }

    @Test
    void testParseEncapsulatedToken_GeneralCharacterConsumption() throws Exception {
        // Test normal flow where characters are read and appended to the token content until a delimiter is reached
        Token token = new Token();
        // Characters followed by delimiter
        when(reader.read()).thenReturn((int) 'a', (int) 'b', (int) 'c', (int) ';');
        invokeParseEncapsulatedToken(token);
        assertEquals("abc", token.content.toString(), "Expected token.content to contain consumed characters");
    }

//     @Test
//     void testParseEncapsulatedToken_EdgeCases() throws Exception {
//         // Test when a character that is neither whitespace nor a delimiter is found directly after a quote character
//         Token token = new Token();
//         // Invalid character after quote
//         when(reader.read()).thenReturn((int) '"', (int) '!', (int) ';');
//         Exception exception = assertThrows(IOException.class, () -> invokeParseEncapsulatedToken(token));
//         assertTrue(exception.getMessage().contains("invalid char between encapsulated token and delimiter"), "Expected IOException with correct message");
//         // Test when an empty token is passed to the method
//         token = new Token();
//         invokeParseEncapsulatedToken(token);
//         assertEquals("", token.content.toString(), "Expected token.content to be empty");
//         // Test when characters are consumed until EOF is reached
//         token = new Token();
//         // Characters until EOF
//         when(reader.read()).thenReturn((int) 'a', (int) 'b', Constants.END_OF_STREAM);
//         invokeParseEncapsulatedToken(token);
//         assertEquals("ab", token.content.toString(), "Expected token.content to contain characters before EOF");
//         assertEquals(Token.Type.EOF, token.type, "Expected token.type to be EOF");
//     }

//     @Test
//     void testParseEncapsulatedToken_SpecialCharacters() throws Exception {
//         // Test when special characters (e.g., newline, tab) are included in the token content
//         Token token = new Token();
//         // Special character included
//         when(reader.read()).thenReturn((int) 'a', (int) '\n', (int) 'b', (int) ';');
//         invokeParseEncapsulatedToken(token);
//         assertTrue(token.content.toString().contains("\n"), "Expected token.content to contain special characters");
//         // Test when an invalid escape sequence is encountered
//         token = new Token();
//         // Invalid escape sequence
//         when(reader.read()).thenReturn((int) '\\', (int) '!', (int) ';');
//         Exception exception = assertThrows(IOException.class, () -> invokeParseEncapsulatedToken(token));
//         assertTrue(exception.getMessage().contains("invalid escape sequence"), "Expected IOException with correct message");
//     }

//     @Test
//     void testParseEncapsulatedToken_MultipleQuotes() throws Exception {
//         // Test when multiple quote characters are used in succession
//         Token token = new Token();
//         // Multiple quotes
//         when(reader.read()).thenReturn((int) '"', (int) '"', (int) '"', (int) ';');
//         invokeParseEncapsulatedToken(token);
//         assertTrue(token.content.toString().contains("\""), "Expected token.content to contain all characters between quotes");
//         // Test when an opening quote is present without a matching closing quote
//         token = new Token();
//         // Unmatched quote
//         when(reader.read()).thenReturn((int) '"', (int) 'a', Constants.END_OF_STREAM);
//         Exception exception = assertThrows(IOException.class, () -> invokeParseEncapsulatedToken(token));
//         assertTrue(exception.getMessage().contains("unmatched quotes"), "Expected IOException with correct message");
//     }

//     @Test
//     void testParseEncapsulatedToken_ExceptionScenarios() throws IOException {
//         // Test when a non-whitespace character is found between the token and the next delimiter after a quote character
//         Token token = new Token();
//         // Invalid character found
//         when(reader.read()).thenReturn((int) '"', (int) 'x', (int) ';');
//         Exception exception = assertThrows(IOException.class, () -> invokeParseEncapsulatedToken(token));
//         assertTrue(exception.getMessage().contains("invalid char between encapsulated token and delimiter"), "Expected IOException with correct message");
//         // Test when the end of file is reached before the encapsulated token is finished
//         token = new Token();
//         // EOF reached
//         when(reader.read()).thenReturn((int) '"', Constants.END_OF_STREAM);
//         Exception exceptionEOF = assertThrows(IOException.class, () -> invokeParseEncapsulatedToken(token));
//         assertTrue(exceptionEOF.getMessage().contains("EOF reached before encapsulated token finished"), "Expected IOException with correct message");
//     }
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
	at org.apache.commons.csv.Lexer_parseEncapsulatedToken_Test.invokeParseEncapsulatedToken(Lexer_parseEncapsulatedToken_Test.java:43)
	at org.apache.commons.csv.Lexer_parseEncapsulatedToken_Test.testParseEncapsulatedToken_GeneralCharacterConsumption(Lexer_parseEncapsulatedToken_Test.java:124)
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
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor$$Lambda$176/0x000000e80109b4b8.apply(Unknown Source)
	at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker$ReflectiveInterceptorCall.lambda$ofVoidMethod$0(InterceptingExecutableInvoker.java:103)
	at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker$ReflectiveInterceptorCall$$Lambda$177/0x000000e80109b8d8.apply(Unknown Source)
	at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.lambda$invoke$0(InterceptingExecutableInvoker.java:93)
	at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker$$Lambda$338/0x000000e8010cfd98.apply(Unknown Source)
	at org.junit.jupiter.engine.execution.InvocationInterceptorChain$InterceptedInvocation.proceed(InvocationInterceptorChain.java:106)
	at org.junit.jupiter.engine.execution.InvocationInterceptorChain.proceed(InvocationInterceptorChain.java:64)
	at org.junit.jupiter.engine.execution.InvocationInterceptorChain.chainAndInvoke(InvocationInterceptorChain.java:45)
	at org.junit.jupiter.engine.execution.InvocationInterceptorChain.invoke(InvocationInterceptorChain.java:37)
	at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.invoke(InterceptingExecutableInvoker.java:92)
	at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.invoke(InterceptingExecutableInvoker.java:86)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$invokeTestMethod$7(TestMethodTestDescriptor.java:217)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor$$Lambda$398/0x000000e801237a00.execute(Unknown Source)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.invokeTestMethod(TestMethodTestDescriptor.java:213)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:138)


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
		java.lang.StringBuilder org.apache.commons.csv.Token.content;
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.type;
		boolean org.apache.commons.csv.Token.isQuoted;
		boolean org.apache.commons.csv.Token.isReady;
	related functions:
		method ` void reset()` returns `void`, related with `org.apache.commons.csv.Lexer.parseEncapsulatedToken(Token)`;
class `org.apache.commons.csv.Token.Type`:
	dependent fields:
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.TOKEN;
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.EORECORD;
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.EOF;
	
class `org.apache.commons.csv.Lexer`:
	dependent fields:
		char[] org.apache.commons.csv.Lexer.delimiter;
		org.apache.commons.csv.ExtendedBufferedReader org.apache.commons.csv.Lexer.reader;
	related functions:
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
		method ` boolean readEndOfLine(int)` returns `boolean`, related with `org.apache.commons.csv.Lexer.parseEncapsulatedToken(Token)`, api document: 
     * Greedily accepts \n, \r and \r\n This checker consumes silently the second control-character...
     *
     * @return true if the given or next character is a line-terminator
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
