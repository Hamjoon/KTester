


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
        when(extendedBufferedReader.read()).thenReturn((int) '#', (int) 'c', (int) 'o', (int) 'm', (int) 'm', (int) 'e', (int) 'n', (int) 't', (int) '\n');
        when(extendedBufferedReader.getLastChar()).thenReturn((int) '\n');
        when(extendedBufferedReader.readLine()).thenReturn("comment");
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
        when(lexer.readEndOfLine((int) '\n')).thenReturn(true);
        Token result = lexer.nextToken(token);
        assertEquals(Token.Type.EORECORD, result.type);
    }

    @Test
    void testNextTokenWithEOFAndData() throws IOException {
        // Simulate EOF with data present
        when(extendedBufferedReader.read()).thenReturn(-1);
        when(extendedBufferedReader.getLastChar()).thenReturn((int) 'd');
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
        doAnswer(invocation -> {
            Token t = invocation.getArgument(0);
            t.content.append("quoted");
            return t;
        }).when(lexer).nextToken(token);
        Token result = (Token) parseEncapsulatedToken.invoke(lexer, token);
        assertEquals("quoted", result.content.toString());
    }

    @Test
    void testNextTokenWithSimpleToken() throws Exception {
        // Simulate reading a simple token
        when(extendedBufferedReader.read()).thenReturn((int) 'a');
        Method parseSimpleToken = Lexer.class.getDeclaredMethod("parseSimpleToken", Token.class, int.class);
        parseSimpleToken.setAccessible(true);
        doAnswer(invocation -> {
            Token t = invocation.getArgument(0);
            t.content.append("simple");
            return t;
        }).when(lexer).nextToken(token);
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
        doAnswer(invocation -> {
            Token t = invocation.getArgument(0);
            t.content.append("a");
            return t;
        }).when(lexer).nextToken(token);
        Token result = (Token) parseSimpleToken.invoke(lexer, token, (int) 'a');
        assertEquals("a", result.content.toString());
    }

    @Test
    void testNextTokenWithLeadingSpacesAndEOL() throws IOException {
        // Simulate leading spaces followed by an end of line
        lexer = new Lexer(CSVFormat.DEFAULT.withIgnoreSurroundingSpaces(true), extendedBufferedReader);
        when(extendedBufferedReader.read()).thenReturn((int) ' ', (int) ' ', (int) '\n');
        when(lexer.readEndOfLine((int) '\n')).thenReturn(true);
        Token result = lexer.nextToken(token);
        assertEquals(Token.Type.EORECORD, result.type);
    }

    @Test
    void testNextTokenWithTrimmedComment() throws IOException {
        // Simulate a comment with leading and trailing spaces
        when(extendedBufferedReader.read()).thenReturn((int) '#', (int) ' ', (int) 'c', (int) 'o', (int) 'm', (int) 'm', (int) 'e', (int) 'n', (int) 't', (int) ' ', (int) '\n');
        when(extendedBufferedReader.getLastChar()).thenReturn((int) '\n');
        when(extendedBufferedReader.readLine()).thenReturn(" comment ");
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

╷
├─ JUnit Jupiter ✔
│  └─ Lexer_nextToken_Test ✔
│     ├─ testNextTokenWithEndOfLine() ✘ 
│     │        when() requires an argument which has to be 'a method call on a mock'.
│     │        For example:
│     │            when(mock.getArticles()).thenReturn(articles);
│     │     
│     │        Also, this error might show up because:
│     │        1. you stub either of: final/private/equals()/hashCode() methods.
│     │           Those methods *cannot* be stubbed/verified.
│     │           Mocking methods declared on non-public parent classes is not supported.
│     │        2. inside when() you don't call method on mock but on some other object.
│     ├─ testNextTokenWithComment() ✘ expected: <COMMENT> but was: <EORECORD>
│     ├─ testNextTokenWithEOFAndData() ✘ expected: <true> but was: <false>
│     ├─ testNextTokenWithTrimmedComment() ✘ expected: <COMMENT> but was: <EORECORD>
│     ├─ testNextTokenWithLeadingSpacesAndEOL() ✘ 
│     │        when() requires an argument which has to be 'a method call on a mock'.
│     │        For example:
│     │            when(mock.getArticles()).thenReturn(articles);
│     │     
│     │        Also, this error might show up because:
│     │        1. you stub either of: final/private/equals()/hashCode() methods.
│     │           Those methods *cannot* be stubbed/verified.
│     │           Mocking methods declared on non-public parent classes is not supported.
│     │        2. inside when() you don't call method on mock but on some other object.
│     ├─ testNextTokenWithSimpleToken() ✘ 
│     │        Argument passed to when() is not a mock!
│     │        Example of correct stubbing:
│     │            doThrow(new RuntimeException()).when(mock).someMethod();
│     ├─ testNextTokenWithQuoteChar() ✘ 
│     │        Argument passed to when() is not a mock!
│     │        Example of correct stubbing:
│     │            doThrow(new RuntimeException()).when(mock).someMethod();
│     ├─ testNextTokenWithEOF() ✔
│     ├─ testNextTokenWithDelimiter() ✔
│     ├─ testNextTokenWithIgnoreEmptyLines() ✔
│     ├─ testNextTokenWithLeadingSpacesAndDelimiter() ✔
│     ├─ testNextTokenWithIOException() ✔
│     ├─ testNextTokenWithNullToken() ✔
│     └─ testNextTokenWithLeadingSpacesAndNonDelimiter() ✘ 
│              Argument passed to when() is not a mock!
│              Example of correct stubbing:
│                  doThrow(new RuntimeException()).when(mock).someMethod();
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Failures (8):
  JUnit Jupiter:Lexer_nextToken_Test:testNextTokenWithEndOfLine()
    MethodSource [className = 'org.apache.commons.csv.Lexer_nextToken_Test', methodName = 'testNextTokenWithEndOfLine', methodParameterTypes = '']
    => org.mockito.exceptions.misusing.MissingMethodInvocationException: 
when() requires an argument which has to be 'a method call on a mock'.
For example:
    when(mock.getArticles()).thenReturn(articles);

Also, this error might show up because:
1. you stub either of: final/private/equals()/hashCode() methods.
   Those methods *cannot* be stubbed/verified.
   Mocking methods declared on non-public parent classes is not supported.
2. inside when() you don't call method on mock but on some other object.

       org.apache.commons.csv.Lexer_nextToken_Test.testNextTokenWithEndOfLine(Lexer_nextToken_Test.java:64)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:727)
       org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
       org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
       org.junit.jupiter.engine.extension.TimeoutExtension.intercept(TimeoutExtension.java:156)
       org.junit.jupiter.engine.extension.TimeoutExtension.interceptTestableMethod(TimeoutExtension.java:147)
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
       org.apache.commons.csv.Lexer_nextToken_Test.testNextTokenWithComment(Lexer_nextToken_Test.java:48)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:Lexer_nextToken_Test:testNextTokenWithEOFAndData()
    MethodSource [className = 'org.apache.commons.csv.Lexer_nextToken_Test', methodName = 'testNextTokenWithEOFAndData', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <true> but was: <false>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertTrue.failNotTrue(AssertTrue.java:63)
       org.junit.jupiter.api.AssertTrue.assertTrue(AssertTrue.java:36)
       org.junit.jupiter.api.AssertTrue.assertTrue(AssertTrue.java:31)
       org.junit.jupiter.api.Assertions.assertTrue(Assertions.java:180)
       org.apache.commons.csv.Lexer_nextToken_Test.testNextTokenWithEOFAndData(Lexer_nextToken_Test.java:76)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:Lexer_nextToken_Test:testNextTokenWithTrimmedComment()
    MethodSource [className = 'org.apache.commons.csv.Lexer_nextToken_Test', methodName = 'testNextTokenWithTrimmedComment', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <COMMENT> but was: <EORECORD>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1142)
       org.apache.commons.csv.Lexer_nextToken_Test.testNextTokenWithTrimmedComment(Lexer_nextToken_Test.java:161)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:Lexer_nextToken_Test:testNextTokenWithLeadingSpacesAndEOL()
    MethodSource [className = 'org.apache.commons.csv.Lexer_nextToken_Test', methodName = 'testNextTokenWithLeadingSpacesAndEOL', methodParameterTypes = '']
    => org.mockito.exceptions.misusing.MissingMethodInvocationException: 
when() requires an argument which has to be 'a method call on a mock'.
For example:
    when(mock.getArticles()).thenReturn(articles);

Also, this error might show up because:
1. you stub either of: final/private/equals()/hashCode() methods.
   Those methods *cannot* be stubbed/verified.
   Mocking methods declared on non-public parent classes is not supported.
2. inside when() you don't call method on mock but on some other object.

       org.apache.commons.csv.Lexer_nextToken_Test.testNextTokenWithLeadingSpacesAndEOL(Lexer_nextToken_Test.java:149)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:727)
       org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
       org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
       org.junit.jupiter.engine.extension.TimeoutExtension.intercept(TimeoutExtension.java:156)
       org.junit.jupiter.engine.extension.TimeoutExtension.interceptTestableMethod(TimeoutExtension.java:147)
       [...]
  JUnit Jupiter:Lexer_nextToken_Test:testNextTokenWithSimpleToken()
    MethodSource [className = 'org.apache.commons.csv.Lexer_nextToken_Test', methodName = 'testNextTokenWithSimpleToken', methodParameterTypes = '']
    => org.mockito.exceptions.misusing.NotAMockException: 
Argument passed to when() is not a mock!
Example of correct stubbing:
    doThrow(new RuntimeException()).when(mock).someMethod();
       org.apache.commons.csv.Lexer_nextToken_Test.testNextTokenWithSimpleToken(Lexer_nextToken_Test.java:104)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:727)
       org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
       org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
       org.junit.jupiter.engine.extension.TimeoutExtension.intercept(TimeoutExtension.java:156)
       org.junit.jupiter.engine.extension.TimeoutExtension.interceptTestableMethod(TimeoutExtension.java:147)
       [...]
  JUnit Jupiter:Lexer_nextToken_Test:testNextTokenWithQuoteChar()
    MethodSource [className = 'org.apache.commons.csv.Lexer_nextToken_Test', methodName = 'testNextTokenWithQuoteChar', methodParameterTypes = '']
    => org.mockito.exceptions.misusing.NotAMockException: 
Argument passed to when() is not a mock!
Example of correct stubbing:
    doThrow(new RuntimeException()).when(mock).someMethod();
       org.apache.commons.csv.Lexer_nextToken_Test.testNextTokenWithQuoteChar(Lexer_nextToken_Test.java:89)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:727)
       org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
       org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
       org.junit.jupiter.engine.extension.TimeoutExtension.intercept(TimeoutExtension.java:156)
       org.junit.jupiter.engine.extension.TimeoutExtension.interceptTestableMethod(TimeoutExtension.java:147)
       [...]
  JUnit Jupiter:Lexer_nextToken_Test:testNextTokenWithLeadingSpacesAndNonDelimiter()
    MethodSource [className = 'org.apache.commons.csv.Lexer_nextToken_Test', methodName = 'testNextTokenWithLeadingSpacesAndNonDelimiter', methodParameterTypes = '']
    => org.mockito.exceptions.misusing.NotAMockException: 
Argument passed to when() is not a mock!
Example of correct stubbing:
    doThrow(new RuntimeException()).when(mock).someMethod();
       org.apache.commons.csv.Lexer_nextToken_Test.testNextTokenWithLeadingSpacesAndNonDelimiter(Lexer_nextToken_Test.java:139)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:727)
       org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
       org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
       org.junit.jupiter.engine.extension.TimeoutExtension.intercept(TimeoutExtension.java:156)
       org.junit.jupiter.engine.extension.TimeoutExtension.interceptTestableMethod(TimeoutExtension.java:147)
       [...]

Test run finished after 486 ms
[         4 containers found      ]
[         0 containers skipped    ]
[         4 containers started    ]
[         0 containers aborted    ]
[         4 containers successful ]
[         0 containers failed     ]
[        14 tests found           ]
[         0 tests skipped         ]
[        14 tests started         ]
[         0 tests aborted         ]
[         6 tests successful      ]
[         8 tests failed          ]


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
