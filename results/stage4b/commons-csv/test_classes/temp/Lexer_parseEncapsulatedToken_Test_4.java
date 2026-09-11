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
        format = CSVFormat.DEFAULT.withEscape('\\');
        reader = mock(ExtendedBufferedReader.class);
        lexer = new Lexer(format, reader);
        token = new Token();
    }

    @AfterEach
    void teardownAfterEach() {
        try {
            lexer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Token invokeParseEncapsulatedToken(Token token) throws Exception {
        Method method = Lexer.class.getDeclaredMethod("parseEncapsulatedToken", Token.class);
        method.setAccessible(true);
        return (Token) method.invoke(lexer, token);
    }

    @Test
    void testParseEncapsulatedToken() throws Exception {
        String input = "\"test token\"";
        when(reader.read()).thenReturn((int) '"', (int) 't', (int) 'e', (int) 's', (int) 't', (int) ' ', (int) 't', (int) 'o', (int) 'k', (int) 'e', (int) 'n', (int) '"', Constants.END_OF_STREAM);
        Token resultToken = invokeParseEncapsulatedToken(token);
        assertNotNull(resultToken);
        assertTrue(resultToken.isQuoted);
        assertEquals("test token", resultToken.content.toString());
    }

    @Test
    void testParseEncapsulatedToken_EscapeDelimiter() throws Exception {
        when(reader.read()).thenReturn((int) '"', (int) 'a', (int) '\\', (int) '"', (int) '"', (int) 'b', (int) '"', Constants.END_OF_STREAM);
        Token resultToken = invokeParseEncapsulatedToken(token);
        assertEquals("\"ab\"", resultToken.content.toString());
        assertTrue(resultToken.isQuoted);
    }

    @Test
    void testParseEncapsulatedToken_ValidCharacterAfterEscape() throws Exception {
        when(reader.read()).thenReturn((int) '"', (int) 'a', (int) '\\', (int) 'b', (int) 'c', (int) '"', Constants.END_OF_STREAM);
        Token resultToken = invokeParseEncapsulatedToken(token);
        assertEquals("abc", resultToken.content.toString());
        assertTrue(resultToken.isQuoted);
    }

    @Test
    void testParseEncapsulatedToken_EOFReachedBeforeFinish() throws Exception {
        when(reader.read()).thenReturn((int) '"', (int) 'a', (int) '\\', (int) 'b', Constants.END_OF_STREAM);
        assertThrows(IOException.class, () -> invokeParseEncapsulatedToken(token));
    }

    @Test
    void testParseEncapsulatedToken_QuoteCharacterFollowedByDelimiter() throws Exception {
        when(reader.read()).thenReturn((int) '"', (int) 'a', (int) 'b', (int) 'c', (int) ',', Constants.END_OF_STREAM);
        Token resultToken = invokeParseEncapsulatedToken(token);
        assertEquals("abc", resultToken.content.toString());
        assertEquals(Token.Type.TOKEN, resultToken.type);
    }

    @Test
    void testParseEncapsulatedToken_QuoteCharacterEOF() throws Exception {
        when(reader.read()).thenReturn((int) '"', (int) 'a', (int) 'b', (int) 'c', Constants.END_OF_STREAM);
        Token resultToken = invokeParseEncapsulatedToken(token);
        assertEquals("abc", resultToken.content.toString());
        assertEquals(Token.Type.EOF, resultToken.type);
        assertTrue(resultToken.isReady);
    }

    @Test
    void testParseEncapsulatedToken_QuoteCharacterNewLine() throws Exception {
        when(reader.read()).thenReturn((int) '"', (int) 'a', (int) 'b', (int) 'c', (int) '\n', Constants.END_OF_STREAM);
        Token resultToken = invokeParseEncapsulatedToken(token);
        assertEquals("abc", resultToken.content.toString());
        assertEquals(Token.Type.EORECORD, resultToken.type);
    }

    @Test
    void testParseEncapsulatedToken_InvalidCharacterBeforeDelimiter() throws Exception {
        when(reader.read()).thenReturn((int) '"', (int) 'a', (int) '!', (int) ',', Constants.END_OF_STREAM);
        assertThrows(IOException.class, () -> invokeParseEncapsulatedToken(token));
    }

    @Test
    void testParseEncapsulatedToken_EmptyToken() throws Exception {
        when(reader.read()).thenReturn((int) '"', Constants.END_OF_STREAM);
        assertThrows(IOException.class, () -> invokeParseEncapsulatedToken(token));
    }

    @Test
    void testParseEncapsulatedToken_NormalCharacters() throws Exception {
        when(reader.read()).thenReturn((int) '"', (int) 'n', (int) 'o', (int) 'r', (int) 'm', (int) 'a', (int) 'l', (int) '"', (int) ',', Constants.END_OF_STREAM);
        Token resultToken = invokeParseEncapsulatedToken(token);
        assertEquals("normal", resultToken.content.toString());
    }

    @Test
    void testParseEncapsulatedToken_WhitespaceHandling() throws Exception {
        when(reader.read()).thenReturn((int) '"', (int) ' ', (int) 't', (int) 'o', (int) 'k', (int) 'e', (int) 'n', (int) ' ', (int) '"', (int) ',', Constants.END_OF_STREAM);
        Token resultToken = invokeParseEncapsulatedToken(token);
        assertEquals(" token ", resultToken.content.toString());
    }

    @Test
    void testParseEncapsulatedToken_InvalidCharacters() throws Exception {
        when(reader.read()).thenReturn((int) '"', (int) 'a', (int) '@', (int) ',', Constants.END_OF_STREAM);
        assertThrows(IOException.class, () -> invokeParseEncapsulatedToken(token));
    }
}
