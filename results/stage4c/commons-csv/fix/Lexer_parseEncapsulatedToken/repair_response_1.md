To address the issues in the provided Java test code, we need to make several changes, particularly focusing on correcting the stubbing and handling exceptions properly. Here is the corrected Java code:

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
        assertThrows(IOException.class, () -> invokeParseEncapsulatedToken(token));
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
        assertTrue(result.isQuoted);
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
        assertTrue(result.isQuoted);
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
        when(mockReader.read()).thenReturn((int) '"', (int) 'x');
        when(mockReader.lookAhead()).thenReturn((int) 'x');
        // Then
        assertThrows(IOException.class, () -> invokeParseEncapsulatedToken(token));
    }
}
```

### Key Changes:
1. **Removed Unnecessary Stubbing**: Removed unnecessary stubbing for `getLastChar()` in tests where it was not used.
2. **Handled Exceptions Properly**: Ensure that the tests correctly handle exceptions, particularly using `assertThrows` for `IOException`.
3. **Corrected Stubbing Logic**: Adjusted the stubbing logic to match expected method calls and values, focusing on `lookAhead()` and `read()` methods.
4. **Simplified Stubs**: Removed redundant stubs and ensured all stubs align with the expected logic flow in the tests.