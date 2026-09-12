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
class Lexer_parseEncapsulatedToken_Test {

    private Lexer lexer;

    private CSVFormat csvFormat;

    private ExtendedBufferedReader mockReader;

    private Token token;

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

    private Token invokeParseEncapsulatedToken(Token token) throws Exception {
        Method method = Lexer.class.getDeclaredMethod("parseEncapsulatedToken", Token.class);
        method.setAccessible(true);
        return (Token) method.invoke(lexer, token);
    }

    @Test
    void testParseEncapsulatedToken() throws Exception {
        // Given
        when(mockReader.read()).thenReturn((int) '"', (int) 'a', (int) '"', (int) ',');
        when(mockReader.lookAhead()).thenReturn((int) ',');
        // When
        Token result = invokeParseEncapsulatedToken(token);
        // Then
        assertEquals(Token.Type.TOKEN, result.type);
        assertEquals("a", result.content.toString());
        assertTrue(result.isQuoted);
    }

    @Test
    void testParseEncapsulatedTokenWithEOF() throws Exception {
        // Given
        when(mockReader.read()).thenReturn((int) '"', (int) 'a', (int) '"', -1);
        when(mockReader.lookAhead()).thenReturn(-1);
        // When
        Token result = invokeParseEncapsulatedToken(token);
        // Then
        assertEquals(Token.Type.TOKEN, result.type);
        assertEquals("a", result.content.toString());
        assertTrue(result.isReady);
        assertTrue(result.isQuoted);
    }

    @Test
    void testParseEncapsulatedTokenThrowsIOException() throws IOException {
        // Given
        try {
            when(mockReader.read()).thenReturn((int) '"', (int) 'a', (int) 'b');
            when(mockReader.lookAhead()).thenReturn((int) 'b');
            // Then
            assertThrows(IOException.class, () -> invokeParseEncapsulatedToken(token));
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    void testParseEncapsulatedTokenWithEscapedDelimiter() throws Exception {
        // Given
        when(mockReader.read()).thenReturn((int) '\\', (int) ',');
        when(mockReader.lookAhead()).thenReturn((int) ',');
        // When
        Token result = invokeParseEncapsulatedToken(token);
        // Then
        assertEquals(Token.Type.TOKEN, result.type);
        assertEquals(",", result.content.toString());
        // Corrected: Escaped delimiter should not be marked as quoted
        assertFalse(result.isQuoted);
    }

    @Test
    void testParseEncapsulatedTokenWithUnescapedCharacter() throws Exception {
        // Given
        when(mockReader.read()).thenReturn((int) '\\', (int) 'n');
        when(mockReader.lookAhead()).thenReturn((int) 'n');
        // When
        Token result = invokeParseEncapsulatedToken(token);
        // Then
        assertEquals(Token.Type.TOKEN, result.type);
        assertEquals("n", result.content.toString());
        // Corrected: Unescaped character should not be marked as quoted
        assertFalse(result.isQuoted);
    }

    @Test
    void testParseEncapsulatedTokenWithDoubleQuote() throws Exception {
        // Given
        when(mockReader.read()).thenReturn((int) '"', (int) '"', (int) ',');
        when(mockReader.lookAhead()).thenReturn((int) ',');
        // When
        Token result = invokeParseEncapsulatedToken(token);
        // Then
        assertEquals(Token.Type.TOKEN, result.type);
        assertEquals("\"", result.content.toString());
        assertTrue(result.isQuoted);
    }

    @Test
    void testParseEncapsulatedTokenWithWhitespaceAndDelimiter() throws Exception {
        // Given
        when(mockReader.read()).thenReturn((int) '"', (int) ' ', (int) ',');
        when(mockReader.lookAhead()).thenReturn((int) ',');
        // When
        Token result = invokeParseEncapsulatedToken(token);
        // Then
        assertEquals(Token.Type.TOKEN, result.type);
        assertEquals("", result.content.toString());
        assertTrue(result.isQuoted);
    }

    @Test
    void testParseEncapsulatedTokenWithEOL() throws Exception {
        // Given
        when(mockReader.read()).thenReturn((int) '"', (int) '\n');
        // When
        Token result = invokeParseEncapsulatedToken(token);
        // Then
        assertEquals(Token.Type.EORECORD, result.type);
        assertEquals("", result.content.toString());
        assertTrue(result.isQuoted);
    }

    @Test
    void testParseEncapsulatedTokenWithInvalidCharAfterQuote() throws IOException {
        // Given
        try {
            when(mockReader.read()).thenReturn((int) '"', (int) 'x');
            when(mockReader.lookAhead()).thenReturn((int) 'x');
            // Then
            assertThrows(IOException.class, () -> invokeParseEncapsulatedToken(token));
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }
}
