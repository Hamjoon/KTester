
You are an experienced Java developer tasked with fixing a piece of code that contains compilation errors. Your task is to resolve these issues after analyzing the code and its compilation feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any syntactical or logical errors.
2. Analyze the compilation feedback to understand the specific errors and their locations within the code.
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

Corresponding compilation feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:42: error: parseEncapsulatedToken(Token) has private access in Lexer
        Token result = lexer.parseEncapsulatedToken(token);
                            ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:56: error: parseEncapsulatedToken(Token) has private access in Lexer
        Token result = lexer.parseEncapsulatedToken(token);
                            ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:70: error: parseEncapsulatedToken(Token) has private access in Lexer
        assertThrows(IOException.class, () -> lexer.parseEncapsulatedToken(token));
                                                   ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:80: error: parseEncapsulatedToken(Token) has private access in Lexer
        Token result = lexer.parseEncapsulatedToken(token);
                            ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:94: error: parseEncapsulatedToken(Token) has private access in Lexer
        Token result = lexer.parseEncapsulatedToken(token);
                            ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:108: error: parseEncapsulatedToken(Token) has private access in Lexer
        Token result = lexer.parseEncapsulatedToken(token);
                            ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:122: error: parseEncapsulatedToken(Token) has private access in Lexer
        Token result = lexer.parseEncapsulatedToken(token);
                            ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:136: error: parseEncapsulatedToken(Token) has private access in Lexer
        Token result = lexer.parseEncapsulatedToken(token);
                            ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:149: error: parseEncapsulatedToken(Token) has private access in Lexer
        assertThrows(IOException.class, () -> lexer.parseEncapsulatedToken(token));
                                                   ^
Note: src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java uses or overrides a deprecated API.
Note: Recompile with -Xlint:deprecation for details.
9 errors

```

And you can use the following related information to assist you in your analysis and correction:
```
{'api document of class org.apache.commons.csv.Lexer': '\n * Lexical analyzer.\n ', 'api document of method parseEncapsulatedToken(Token)': '\n     * Parses an encapsulated token.\n     * <p>\n     * Encapsulated tokens are surrounded by the given encapsulating-string. The encapsulator itself might be included\n     * in the token using a doubling syntax (as "", \'\') or using escaping (as in \\", \\\'). Whitespaces before and after\n     * an encapsulated token are ignored. The token is finished when one of the following conditions become true:\n     * </p>\n     * <ul>\n     * <li>an unescaped encapsulator has been reached, and is followed by optional whitespace then:</li>\n     * <ul>\n     * <li>delimiter (TOKEN)</li>\n     * <li>end of line (EORECORD)</li>\n     * </ul>\n     * <li>end of stream has been reached (EOF)</li> </ul>\n     *\n     * @param token\n     *            the current token\n     * @return a valid token object\n     * @throws IOException\n     *             on invalid state: EOF before closing encapsulator or invalid character before delimiter or EOL\n     ', 'return type': 'org.apache.commons.csv.Token', 'dependent classes': 'class `org.apache.commons.csv.Token`:\n\tdependent fields:\n\t\tjava.lang.StringBuilder org.apache.commons.csv.Token.content;\n\t\tboolean org.apache.commons.csv.Token.isReady;\n\t\tboolean org.apache.commons.csv.Token.isQuoted;\n\t\torg.apache.commons.csv.Token.Type org.apache.commons.csv.Token.type;\n\trelated functions:\n\t\tmethod ` void reset()` returns `void`, related with `org.apache.commons.csv.Lexer.parseEncapsulatedToken(Token)`;\nclass `org.apache.commons.csv.Token.Type`:\n\tdependent fields:\n\t\torg.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.EOF;\n\t\torg.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.TOKEN;\n\t\torg.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.EORECORD;\n\t\nclass `org.apache.commons.csv.Lexer`:\n\tdependent fields:\n\t\torg.apache.commons.csv.ExtendedBufferedReader org.apache.commons.csv.Lexer.reader;\n\t\tchar[] org.apache.commons.csv.Lexer.delimiter;\n\trelated functions:\n\t\tmethod ` boolean readEndOfLine(int)` returns `boolean`, related with `org.apache.commons.csv.Lexer.parseEncapsulatedToken(Token)`, api document: \n     * Greedily accepts \\n, \\r and \\r\\n This checker consumes silently the second control-character...\n     *\n     * @return true if the given or next character is a line-terminator\n     ;\n\t\tmethod ` int readEscape()` returns `int`, related with `org.apache.commons.csv.Lexer.parseEncapsulatedToken(Token)`, api document: \n     * Handle an escape sequence.\n     * The current character must be the escape character.\n     * On return, the next character is available by calling {@link ExtendedBufferedReader#getLastChar()}\n     * on the input stream.\n     *\n     * @return the unescaped character (as an int) or {@link Constants#END_OF_STREAM} if char following the escape is\n     *      invalid.\n     * @throws IOException if there is a problem reading the stream or the end of stream is detected:\n     *      the escape character is not allowed at end of stream\n     ;\n\t\tmethod ` boolean isDelimiter(int)` returns `boolean`, related with `org.apache.commons.csv.Lexer.parseEncapsulatedToken(Token)`, api document: \n     * Determine whether the next characters constitute a delimiter through {@link ExtendedBufferedReader#lookAhead(char[])}.\n     *\n     * @param ch\n     *             the current character.\n     * @return true if the next characters constitute a delimiter.\n     * @throws IOException If an I/O error occurs.\n     ;\n\t\tmethod ` Token nextToken(Token)` returns `org.apache.commons.csv.Token`, related with `org.apache.commons.csv.Lexer.parseEncapsulatedToken(Token)`, api document: \n     * Returns the next token.\n     * <p>\n     * A token corresponds to a term, a record change or an end-of-file indicator.\n     * </p>\n     *\n     * @param token\n     *            an existing Token object to reuse. The caller is responsible to initialize the Token.\n     * @return the next token found.\n     * @throws IOException on stream access error.\n     ;\n\t\tmethod `private Token parseSimpleToken(Token, int)` returns `org.apache.commons.csv.Token`, related with `org.apache.commons.csv.Lexer.parseEncapsulatedToken(Token)`, api document: \n     * Parses a simple token.\n     * <p>\n     * Simple token are tokens which are not surrounded by encapsulators. A simple token might contain escaped\n     * delimiters (as \\, or \\;). The token is finished when one of the following conditions become true:\n     * </p>\n     * <ul>\n     * <li>end of line has been reached (EORECORD)</li>\n     * <li>end of stream has been reached (EOF)</li>\n     * <li>an unescaped delimiter has been reached (TOKEN)</li>\n     * </ul>\n     *\n     * @param token\n     *            the current token\n     * @param ch\n     *            the current character\n     * @return the filled token\n     * @throws IOException\n     *             on stream access error\n     ;\nclass `org.apache.commons.csv.Constants`:\n\tdependent fields:\n\t\tint org.apache.commons.csv.Constants.END_OF_STREAM;\n\t\nclass `org.apache.commons.csv.CSVParser`:\n\trelated functions:\n\t\tmethod ` CSVRecord nextRecord()` returns `org.apache.commons.csv.CSVRecord`, related with `org.apache.commons.csv.Lexer.parseEncapsulatedToken(Token)`, api document: \n     * Parses the next record from the current point in the stream.\n     *\n     * @return the record as an array of values, or {@code null} if the end of the stream has been reached\n     * @throws IOException\n     *             on parse error or input read-failure\n     ;\nclass `org.apache.commons.csv.ExtendedBufferedReader`:\n\trelated functions:\n\t\tmethod `public String readLine()` returns `java.lang.String`, related with `org.apache.commons.csv.Lexer.parseEncapsulatedToken(Token)`, api document: \n     * Gets the next line, dropping the line terminator(s). This method should only be called when processing a\n     * comment, otherwise information can be lost.\n     * <p>\n     * Increments {@link #eolCounter} and updates {@link #position}.\n     * </p>\n     * <p>\n     * Sets {@link #lastChar} to {@link Constants#END_OF_STREAM} at EOF, otherwise the last EOL character.\n     * </p>\n     *\n     * @return the line that was read, or null if reached EOF.\n     ;'}
```

**Note**: If the method-to-test in the @Test function is a private method, you can use **reflection** to call it.

Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 


