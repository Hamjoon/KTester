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

    @Test
    void testParseEncapsulatedToken() throws IOException {
        // Example input for the test
        // Token to be parsed
        String input = "\"test token\"";
        // Simulate reading characters
        when(reader.read()).thenReturn((int) '"', (int) 't', (int) 'e', (int) 's', (int) 't', (int) ' ', (int) 't', (int) 'o', (int) 'k', (int) 'e', (int) 'n', (int) '"', -1);
        // Call the method under test
        Token resultToken = lexer.parseEncapsulatedToken(token);
        // Verify the results
        assertNotNull(resultToken);
        assertTrue(resultToken.isQuoted);
        assertEquals("test token", resultToken.content.toString());
    }

    @Test
    void testParseEncapsulatedToken_EscapeDelimiter() throws IOException {
        // Test when an escape character is encountered followed by an escape delimiter
        when(reader.read()).thenReturn((int) '"', (int) 'a', (int) '\\', (int) '"', (int) '"', (int) 'b', (int) '"', -1);
        Token resultToken = lexer.parseEncapsulatedToken(token);
        assertEquals("\"ab\"", resultToken.content.toString());
        assertTrue(resultToken.isQuoted);
    }

    @Test
    void testParseEncapsulatedToken_ValidCharacterAfterEscape() throws IOException {
        // Test when an escape character is encountered followed by a valid character
        when(reader.read()).thenReturn((int) '"', (int) 'a', (int) '\\', (int) 'b', (int) 'c', (int) '"', -1);
        Token resultToken = lexer.parseEncapsulatedToken(token);
        assertEquals("abc", resultToken.content.toString());
        assertTrue(resultToken.isQuoted);
    }

    @Test
    void testParseEncapsulatedToken_EOFReachedBeforeFinish() {
        // Test when an escape character is encountered, but the end of stream is reached
        when(reader.read()).thenReturn((int) '"', (int) 'a', (int) '\\', (int) 'b', Constants.END_OF_STREAM);
        assertThrows(IOException.class, () -> lexer.parseEncapsulatedToken(token));
    }

    @Test
    void testParseEncapsulatedToken_QuoteCharacterFollowedByDelimiter() throws IOException {
        // Test when a quote character is encountered and a delimiter follows
        when(reader.read()).thenReturn((int) '"', (int) 'a', (int) 'b', (int) 'c', (int) ',', -1);
        Token resultToken = lexer.parseEncapsulatedToken(token);
        assertEquals("abc", resultToken.content.toString());
        assertEquals(Token.Type.TOKEN, resultToken.type);
    }

    @Test
    void testParseEncapsulatedToken_QuoteCharacterEOF() throws IOException {
        // Test when a quote character is encountered and end of file is reached
        when(reader.read()).thenReturn((int) '"', (int) 'a', (int) 'b', (int) 'c', Constants.END_OF_STREAM);
        Token resultToken = lexer.parseEncapsulatedToken(token);
        assertEquals("abc", resultToken.content.toString());
        assertEquals(Token.Type.EOF, resultToken.type);
        assertTrue(resultToken.isReady);
    }

    @Test
    void testParseEncapsulatedToken_QuoteCharacterNewLine() throws IOException {
        // Test when a quote character is encountered and a new line is read
        when(reader.read()).thenReturn((int) '"', (int) 'a', (int) 'b', (int) 'c', (int) '\n', -1);
        Token resultToken = lexer.parseEncapsulatedToken(token);
        assertEquals("abc", resultToken.content.toString());
        assertEquals(Token.Type.EORECORD, resultToken.type);
    }

    @Test
    void testParseEncapsulatedToken_InvalidCharacterBeforeDelimiter() {
        // Test when a quote character is encountered and an invalid character is found before the delimiter
        when(reader.read()).thenReturn((int) '"', (int) 'a', (int) '!', (int) ',', -1);
        assertThrows(IOException.class, () -> lexer.parseEncapsulatedToken(token));
    }

    @Test
    void testParseEncapsulatedToken_EmptyToken() {
        // Test when an empty token is processed
        when(reader.read()).thenReturn((int) '"', Constants.END_OF_STREAM);
        assertThrows(IOException.class, () -> lexer.parseEncapsulatedToken(token));
    }

    @Test
    void testParseEncapsulatedToken_NormalCharacters() throws IOException {
        // Test when normal characters are read without encountering special conditions
        when(reader.read()).thenReturn((int) '"', (int) 'n', (int) 'o', (int) 'r', (int) 'm', (int) 'a', (int) 'l', (int) '"', (int) ',', -1);
        Token resultToken = lexer.parseEncapsulatedToken(token);
        assertEquals("normal", resultToken.content.toString());
    }

    @Test
    void testParseEncapsulatedToken_WhitespaceHandling() throws IOException {
        // Test when leading and trailing whitespace is present
        when(reader.read()).thenReturn((int) '"', (int) ' ', (int) 't', (int) 'o', (int) 'k', (int) 'e', (int) 'n', (int) ' ', (int) '"', (int) ',', -1);
        Token resultToken = lexer.parseEncapsulatedToken(token);
        assertEquals(" token ", resultToken.content.toString());
    }

    @Test
    void testParseEncapsulatedToken_InvalidCharacters() {
        // Test when an invalid character is encountered between a token and the delimiter
        when(reader.read()).thenReturn((int) '"', (int) 'a', (int) '@', (int) ',', -1);
        assertThrows(IOException.class, () -> lexer.parseEncapsulatedToken(token));
    }
}
