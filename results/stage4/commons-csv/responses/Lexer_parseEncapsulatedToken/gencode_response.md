```java
package org.apache.commons.csv;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.IOException;

@ExtendWith(MockitoExtension.class)
class Lexer_parseEncapsulatedToken_Test {

    private Lexer lexer;
    private CSVFormat csvFormat;
    private ExtendedBufferedReader reader;

    @BeforeAll
    static void setupBeforeAll() {
        // Any global setup can be done here
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the CSVFormat and ExtendedBufferedReader here
        csvFormat = CSVFormat.DEFAULT.withEscape('\\');
        reader = mock(ExtendedBufferedReader.class); // Mocking the reader for testing
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

    @AfterAll
    static void teardownAfterAll() {
        // Any global cleanup can be done here
    }

    @Test
    void testParseEncapsulatedToken_EscapeCharacters() throws IOException {
        // Test when an escape character is followed by an escape delimiter
        Token token = new Token();
        when(reader.read()).thenReturn('\\', ';'); // Escape character followed by escape delimiter
        lexer.parseEncapsulatedToken(token);
        assertTrue(token.content.toString().contains(";"), "Expected token.content to contain escaped delimiter");

        // Test when an escape character is followed by a valid character
        token = new Token();
        when(reader.read()).thenReturn('\\', 'a'); // Escape character followed by valid character
        lexer.parseEncapsulatedToken(token);
        assertTrue(token.content.toString().contains("a"), "Expected token.content to contain the unescaped character");

        // Test when an escape character is followed by end of stream
        token = new Token();
        when(reader.read()).thenReturn('\\', Constants.END_OF_STREAM); // Escape character followed by end of stream
        Exception exception = assertThrows(IOException.class, () -> lexer.parseEncapsulatedToken(token));
        assertTrue(exception.getMessage().contains("unexpected char after escape"), "Expected IOException with correct message");
    }

    @Test
    void testParseEncapsulatedToken_QuotingLogic() throws IOException {
        // Test when a quote character is followed by another quote character
        Token token = new Token();
        when(reader.read()).thenReturn('"', '"', 'b', ';'); // Quote followed by quote, then character
        lexer.parseEncapsulatedToken(token);
        assertTrue(token.content.toString().contains("b"), "Expected token.content to contain the character read after a quote character");

        // Test when a quote character is followed by a delimiter
        token = new Token();
        when(reader.read()).thenReturn('"', ';'); // Quote followed by delimiter
        lexer.parseEncapsulatedToken(token);
        assertEquals(Token.Type.TOKEN, token.type, "Expected token.type to be TOKEN");

        // Test when a quote character is followed by end of file
        token = new Token();
        when(reader.read()).thenReturn('"', Constants.END_OF_STREAM); // Quote followed by EOF
        lexer.parseEncapsulatedToken(token);
        assertEquals(Token.Type.EOF, token.type, "Expected token.type to be EOF");
        assertTrue(token.isReady, "Expected token.isReady to be true");

        // Test when a non-whitespace character is found between the token and the next delimiter
        token = new Token();
        when(reader.read()).thenReturn('"', 'x', ';'); // Invalid character found
        Exception exception = assertThrows(IOException.class, () -> lexer.parseEncapsulatedToken(token));
        assertTrue(exception.getMessage().contains("invalid char between encapsulated token and delimiter"), "Expected IOException with correct message");
    }

    @Test
    void testParseEncapsulatedToken_EndOfFileHandling() throws IOException {
        // Test when EOF is reached before the token is finished
        Token token = new Token();
        when(reader.read()).thenReturn(Constants.END_OF_STREAM); // End of file reached
        Exception exception = assertThrows(IOException.class, () -> lexer.parseEncapsulatedToken(token));
        assertTrue(exception.getMessage().contains("EOF reached before encapsulated token finished"), "Expected IOException with correct message");
    }

    @Test
    void testParseEncapsulatedToken_GeneralCharacterConsumption() throws IOException {
        // Test normal flow where characters are read and appended to the token content until a delimiter is reached
        Token token = new Token();
        when(reader.read()).thenReturn('a', 'b', 'c', ';'); // Characters followed by delimiter
        lexer.parseEncapsulatedToken(token);
        assertEquals("abc", token.content.toString(), "Expected token.content to contain consumed characters");
    }

    @Test
    void testParseEncapsulatedToken_EdgeCases() throws IOException {
        // Test when a character that is neither whitespace nor a delimiter is found directly after a quote character
        Token token = new Token();
        when(reader.read()).thenReturn('"', '!', ';'); // Invalid character after quote
        Exception exception = assertThrows(IOException.class, () -> lexer.parseEncapsulatedToken(token));
        assertTrue(exception.getMessage().contains("invalid char between encapsulated token and delimiter"), "Expected IOException with correct message");

        // Test when an empty token is passed to the method
        token = new Token();
        lexer.parseEncapsulatedToken(token);
        assertEquals("", token.content.toString(), "Expected token.content to be empty");

        // Test when characters are consumed until EOF is reached
        token = new Token();
        when(reader.read()).thenReturn('a', 'b', Constants.END_OF_STREAM); // Characters until EOF
        lexer.parseEncapsulatedToken(token);
        assertEquals("ab", token.content.toString(), "Expected token.content to contain characters before EOF");
        assertEquals(Token.Type.EOF, token.type, "Expected token.type to be EOF");
    }

    @Test
    void testParseEncapsulatedToken_SpecialCharacters() throws IOException {
        // Test when special characters (e.g., newline, tab) are included in the token content
        Token token = new Token();
        when(reader.read()).thenReturn('a', '\n', 'b', ';'); // Special character included
        lexer.parseEncapsulatedToken(token);
        assertTrue(token.content.toString().contains("\n"), "Expected token.content to contain special characters");

        // Test when an invalid escape sequence is encountered
        token = new Token();
        when(reader.read()).thenReturn('\\', '!', ';'); // Invalid escape sequence
        Exception exception = assertThrows(IOException.class, () -> lexer.parseEncapsulatedToken(token));
        assertTrue(exception.getMessage().contains("invalid escape sequence"), "Expected IOException with correct message");
    }

    @Test
    void testParseEncapsulatedToken_MultipleQuotes() throws IOException {
        // Test when multiple quote characters are used in succession
        Token token = new Token();
        when(reader.read()).thenReturn('"', '"', '"', ';'); // Multiple quotes
        lexer.parseEncapsulatedToken(token);
        assertTrue(token.content.toString().contains("\""), "Expected token.content to contain all characters between quotes");

        // Test when an opening quote is present without a matching closing quote
        token = new Token();
        when(reader.read()).thenReturn('"', 'a', Constants.END_OF_STREAM); // Unmatched quote
        Exception exception = assertThrows(IOException.class, () -> lexer.parseEncapsulatedToken(token));
        assertTrue(exception.getMessage().contains("unmatched quotes"), "Expected IOException with correct message");
    }

    @Test
    void testParseEncapsulatedToken_ExceptionScenarios() throws IOException {
        // Test when a non-whitespace character is found between the token and the next delimiter after a quote character
        Token token = new Token();
        when(reader.read()).thenReturn('"', 'x', ';'); // Invalid character found
        Exception exception = assertThrows(IOException.class, () -> lexer.parseEncapsulatedToken(token));
        assertTrue(exception.getMessage().contains("invalid char between encapsulated token and delimiter"), "Expected IOException with correct message");

        // Test when the end of file is reached before the encapsulated token is finished
        token = new Token();
        when(reader.read()).thenReturn('"', Constants.END_OF_STREAM); // EOF reached
        Exception exceptionEOF = assertThrows(IOException.class, () -> lexer.parseEncapsulatedToken(token));
        assertTrue(exceptionEOF.getMessage().contains("EOF reached before encapsulated token finished"), "Expected IOException with correct message");
    }
}
```