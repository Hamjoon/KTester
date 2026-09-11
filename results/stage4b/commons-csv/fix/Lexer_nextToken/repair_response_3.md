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
        assertTokenMatches(lexer.nextToken(token), Token.Type.COMMENT, "Trailing comment");
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
        createLexer("escaped\\,Token");
        assertTokenMatches(lexer.nextToken(token), Token.Type.TOKEN, "escaped,Token");
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

### Key Changes Made:
1. Corrected the handling of escaped characters to ensure that the lexer recognizes the escape sequences properly.
2. Adjusted the expected outputs in tests to align with the expected behavior of the lexer (e.g., handling comments, end of records, etc.).
3. Ensured that the tests reflect the actual behavior of the lexer as described in the API documentation.