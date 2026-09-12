To fix the compilation errors, we need to address the issue of attempting to mock or directly call private methods of the `Lexer` class. Since the `parseEncapsulatedToken` and `parseSimpleToken` methods are private, we cannot directly mock them using Mockito. Instead, we can use reflection to access these private methods for testing purposes.

Here's the corrected Java code using reflection to invoke the private methods:

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

In this corrected code, I used reflection to access the private methods `parseEncapsulatedToken` and `parseSimpleToken`. This allows the test to invoke these methods without changing their access modifiers in the `Lexer` class.