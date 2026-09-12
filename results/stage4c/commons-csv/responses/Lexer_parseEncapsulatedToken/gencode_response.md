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
class Lexer_parseEncapsulatedToken_Test{

    private Lexer lexer;
    private CSVFormat csvFormat;
    private ExtendedBufferedReader mockReader;
    private Token token;

    @BeforeAll
    static void setupBeforeAll() {
        // Perform any setup required before all tests
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the CSVFormat with necessary configurations
        csvFormat = CSVFormat.DEFAULT.withEscape('\\');

        // Create a mock ExtendedBufferedReader
        mockReader = mock(ExtendedBufferedReader.class);

        // Instantiate the Lexer with the mockReader and csvFormat
        lexer = new Lexer(csvFormat, mockReader);

        // Initialize a new Token object
        token = new Token();
    }

    @AfterEach
    void teardownAfterEach() {
        // Perform any cleanup after each test
    }

    @AfterAll
    static void teardownAfterAll() {
        // Perform any cleanup required after all tests
    }

    @Test
    void testParseEncapsulatedToken() throws IOException {
        // Given
        when(mockReader.read()).thenReturn((int) '"', (int) 'a', (int) '"', (int) ',');
        when(mockReader.lookAhead()).thenReturn((int) ',');
        when(mockReader.getLastChar()).thenReturn((int) ',');

        // When
        Token result = lexer.parseEncapsulatedToken(token);

        // Then
        assertEquals(Token.Type.TOKEN, result.type);
        assertEquals("a", result.content.toString());
        assertTrue(result.isQuoted);
    }

    @Test
    void testParseEncapsulatedTokenWithEOF() throws IOException {
        // Given
        when(mockReader.read()).thenReturn((int) '"', (int) 'a', (int) '"', -1);
        when(mockReader.lookAhead()).thenReturn(-1);
        when(mockReader.getLastChar()).thenReturn(-1);

        // When
        Token result = lexer.parseEncapsulatedToken(token);

        // Then
        assertEquals(Token.Type.EOF, result.type);
        assertEquals("a", result.content.toString());
        assertTrue(result.isReady);
        assertTrue(result.isQuoted);
    }

    @Test
    void testParseEncapsulatedTokenThrowsIOException() throws IOException {
        // Given
        when(mockReader.read()).thenReturn((int) '"', (int) 'a', (int) 'b');
        when(mockReader.lookAhead()).thenReturn((int) 'b');

        // Then
        assertThrows(IOException.class, () -> lexer.parseEncapsulatedToken(token));
    }

    @Test
    void testParseEncapsulatedTokenWithEscapedDelimiter() throws IOException {
        // Given
        when(mockReader.read()).thenReturn((int) '\\', (int) ',');
        when(mockReader.lookAhead()).thenReturn((int) ',');
        when(mockReader.getLastChar()).thenReturn((int) ',');

        // When
        Token result = lexer.parseEncapsulatedToken(token);

        // Then
        assertEquals(Token.Type.TOKEN, result.type);
        assertEquals(",", result.content.toString());
        assertTrue(result.isQuoted);
    }

    @Test
    void testParseEncapsulatedTokenWithUnescapedCharacter() throws IOException {
        // Given
        when(mockReader.read()).thenReturn((int) '\\', (int) 'n');
        when(mockReader.lookAhead()).thenReturn((int) 'n');
        when(mockReader.getLastChar()).thenReturn((int) 'n');

        // When
        Token result = lexer.parseEncapsulatedToken(token);

        // Then
        assertEquals(Token.Type.TOKEN, result.type);
        assertEquals("n", result.content.toString());
        assertTrue(result.isQuoted);
    }

    @Test
    void testParseEncapsulatedTokenWithDoubleQuote() throws IOException {
        // Given
        when(mockReader.read()).thenReturn((int) '"', (int) '"', (int) ',');
        when(mockReader.lookAhead()).thenReturn((int) ',');
        when(mockReader.getLastChar()).thenReturn((int) ',');

        // When
        Token result = lexer.parseEncapsulatedToken(token);

        // Then
        assertEquals(Token.Type.TOKEN, result.type);
        assertEquals("\"", result.content.toString());
        assertTrue(result.isQuoted);
    }

    @Test
    void testParseEncapsulatedTokenWithWhitespaceAndDelimiter() throws IOException {
        // Given
        when(mockReader.read()).thenReturn((int) '"', (int) ' ', (int) ',');
        when(mockReader.lookAhead()).thenReturn((int) ',');
        when(mockReader.getLastChar()).thenReturn((int) ',');

        // When
        Token result = lexer.parseEncapsulatedToken(token);

        // Then
        assertEquals(Token.Type.TOKEN, result.type);
        assertEquals("", result.content.toString());
        assertTrue(result.isQuoted);
    }

    @Test
    void testParseEncapsulatedTokenWithEOL() throws IOException {
        // Given
        when(mockReader.read()).thenReturn((int) '"', (int) '\n');
        when(mockReader.lookAhead()).thenReturn((int) '\n');
        when(mockReader.getLastChar()).thenReturn((int) '\n');

        // When
        Token result = lexer.parseEncapsulatedToken(token);

        // Then
        assertEquals(Token.Type.EORECORD, result.type);
        assertEquals("", result.content.toString());
        assertTrue(result.isQuoted);
    }

    @Test
    void testParseEncapsulatedTokenWithInvalidCharAfterQuote() throws IOException {
        // Given
        when(mockReader.read()).thenReturn((int) '"', (int) 'x');
        when(mockReader.lookAhead()).thenReturn((int) 'x');

        // Then
        assertThrows(IOException.class, () -> lexer.parseEncapsulatedToken(token));
    }
}
```