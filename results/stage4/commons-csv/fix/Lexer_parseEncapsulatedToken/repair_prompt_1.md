
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
import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class Lexer_parseEncapsulatedToken_Test {

    private Lexer lexer;

    private CSVFormat csvFormat;

    private ExtendedBufferedReader reader;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the CSVFormat and ExtendedBufferedReader here
        csvFormat = CSVFormat.DEFAULT.withEscape('\\');
        // Mocking the reader for testing
        reader = mock(ExtendedBufferedReader.class);
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

    private void invokeParseEncapsulatedToken(Token token) throws Exception {
        Method method = Lexer.class.getDeclaredMethod("parseEncapsulatedToken", Token.class);
        method.setAccessible(true);
        method.invoke(lexer, token);
    }

    @Test
    void testParseEncapsulatedToken() throws Exception {
        // Given
        Token token = new Token();
        // When
        invokeParseEncapsulatedToken(token);
        // Then
        // Add assertions to verify the expected behavior of the token after parsing
        // Example: assertEquals(expectedValue, token.content.toString());
    }

    @Test
    void testParseEncapsulatedToken_EscapeCharacters() throws Exception {
        // Test when an escape character is followed by an escape delimiter
        Token token = new Token();
        // Escape character followed by escape delimiter
        when(reader.read()).thenReturn((int) '\\', (int) ';');
        invokeParseEncapsulatedToken(token);
        assertTrue(token.content.toString().contains(";"), "Expected token.content to contain escaped delimiter");
        // Test when an escape character is followed by a valid character
        token = new Token();
        // Escape character followed by valid character
        when(reader.read()).thenReturn((int) '\\', (int) 'a');
        invokeParseEncapsulatedToken(token);
        assertTrue(token.content.toString().contains("a"), "Expected token.content to contain the unescaped character");
        // Test when an escape character is followed by end of stream
        token = new Token();
        // Escape character followed by end of stream
        when(reader.read()).thenReturn((int) '\\', Constants.END_OF_STREAM);
        Exception exception = assertThrows(IOException.class, () -> invokeParseEncapsulatedToken(token));
        assertTrue(exception.getMessage().contains("unexpected char after escape"), "Expected IOException with correct message");
    }

    @Test
    void testParseEncapsulatedToken_QuotingLogic() throws Exception {
        // Test when a quote character is followed by another quote character
        Token token = new Token();
        // Quote followed by quote, then character
        when(reader.read()).thenReturn((int) '"', (int) '"', (int) 'b', (int) ';');
        invokeParseEncapsulatedToken(token);
        assertTrue(token.content.toString().contains("b"), "Expected token.content to contain the character read after a quote character");
        // Test when a quote character is followed by a delimiter
        token = new Token();
        // Quote followed by delimiter
        when(reader.read()).thenReturn((int) '"', (int) ';');
        invokeParseEncapsulatedToken(token);
        assertEquals(Token.Type.TOKEN, token.type, "Expected token.type to be TOKEN");
        // Test when a quote character is followed by end of file
        token = new Token();
        // Quote followed by EOF
        when(reader.read()).thenReturn((int) '"', Constants.END_OF_STREAM);
        invokeParseEncapsulatedToken(token);
        assertEquals(Token.Type.EOF, token.type, "Expected token.type to be EOF");
        assertTrue(token.isReady, "Expected token.isReady to be true");
        // Test when a non-whitespace character is found between the token and the next delimiter
        token = new Token();
        // Invalid character found
        when(reader.read()).thenReturn((int) '"', (int) 'x', (int) ';');
        Exception exception = assertThrows(IOException.class, () -> invokeParseEncapsulatedToken(token));
        assertTrue(exception.getMessage().contains("invalid char between encapsulated token and delimiter"), "Expected IOException with correct message");
    }

    @Test
    void testParseEncapsulatedToken_EndOfFileHandling() throws IOException {
        // Test when EOF is reached before the token is finished
        Token token = new Token();
        // End of file reached
        when(reader.read()).thenReturn(Constants.END_OF_STREAM);
        Exception exception = assertThrows(IOException.class, () -> invokeParseEncapsulatedToken(token));
        assertTrue(exception.getMessage().contains("EOF reached before encapsulated token finished"), "Expected IOException with correct message");
    }

    @Test
    void testParseEncapsulatedToken_GeneralCharacterConsumption() throws Exception {
        // Test normal flow where characters are read and appended to the token content until a delimiter is reached
        Token token = new Token();
        // Characters followed by delimiter
        when(reader.read()).thenReturn((int) 'a', (int) 'b', (int) 'c', (int) ';');
        invokeParseEncapsulatedToken(token);
        assertEquals("abc", token.content.toString(), "Expected token.content to contain consumed characters");
    }

    @Test
    void testParseEncapsulatedToken_EdgeCases() throws Exception {
        // Test when a character that is neither whitespace nor a delimiter is found directly after a quote character
        Token token = new Token();
        // Invalid character after quote
        when(reader.read()).thenReturn((int) '"', (int) '!', (int) ';');
        Exception exception = assertThrows(IOException.class, () -> invokeParseEncapsulatedToken(token));
        assertTrue(exception.getMessage().contains("invalid char between encapsulated token and delimiter"), "Expected IOException with correct message");
        // Test when an empty token is passed to the method
        token = new Token();
        invokeParseEncapsulatedToken(token);
        assertEquals("", token.content.toString(), "Expected token.content to be empty");
        // Test when characters are consumed until EOF is reached
        token = new Token();
        // Characters until EOF
        when(reader.read()).thenReturn((int) 'a', (int) 'b', Constants.END_OF_STREAM);
        invokeParseEncapsulatedToken(token);
        assertEquals("ab", token.content.toString(), "Expected token.content to contain characters before EOF");
        assertEquals(Token.Type.EOF, token.type, "Expected token.type to be EOF");
    }

    @Test
    void testParseEncapsulatedToken_SpecialCharacters() throws Exception {
        // Test when special characters (e.g., newline, tab) are included in the token content
        Token token = new Token();
        // Special character included
        when(reader.read()).thenReturn((int) 'a', (int) '\n', (int) 'b', (int) ';');
        invokeParseEncapsulatedToken(token);
        assertTrue(token.content.toString().contains("\n"), "Expected token.content to contain special characters");
        // Test when an invalid escape sequence is encountered
        token = new Token();
        // Invalid escape sequence
        when(reader.read()).thenReturn((int) '\\', (int) '!', (int) ';');
        Exception exception = assertThrows(IOException.class, () -> invokeParseEncapsulatedToken(token));
        assertTrue(exception.getMessage().contains("invalid escape sequence"), "Expected IOException with correct message");
    }

    @Test
    void testParseEncapsulatedToken_MultipleQuotes() throws Exception {
        // Test when multiple quote characters are used in succession
        Token token = new Token();
        // Multiple quotes
        when(reader.read()).thenReturn((int) '"', (int) '"', (int) '"', (int) ';');
        invokeParseEncapsulatedToken(token);
        assertTrue(token.content.toString().contains("\""), "Expected token.content to contain all characters between quotes");
        // Test when an opening quote is present without a matching closing quote
        token = new Token();
        // Unmatched quote
        when(reader.read()).thenReturn((int) '"', (int) 'a', Constants.END_OF_STREAM);
        Exception exception = assertThrows(IOException.class, () -> invokeParseEncapsulatedToken(token));
        assertTrue(exception.getMessage().contains("unmatched quotes"), "Expected IOException with correct message");
    }

    @Test
    void testParseEncapsulatedToken_ExceptionScenarios() throws IOException {
        // Test when a non-whitespace character is found between the token and the next delimiter after a quote character
        Token token = new Token();
        // Invalid character found
        when(reader.read()).thenReturn((int) '"', (int) 'x', (int) ';');
        Exception exception = assertThrows(IOException.class, () -> invokeParseEncapsulatedToken(token));
        assertTrue(exception.getMessage().contains("invalid char between encapsulated token and delimiter"), "Expected IOException with correct message");
        // Test when the end of file is reached before the encapsulated token is finished
        token = new Token();
        // EOF reached
        when(reader.read()).thenReturn((int) '"', Constants.END_OF_STREAM);
        Exception exceptionEOF = assertThrows(IOException.class, () -> invokeParseEncapsulatedToken(token));
        assertTrue(exceptionEOF.getMessage().contains("EOF reached before encapsulated token finished"), "Expected IOException with correct message");
    }
}
```

Corresponding compilation feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:75: error: local variables referenced from a lambda expression must be final or effectively final
        Exception exception = assertThrows(IOException.class, () -> invokeParseEncapsulatedToken(token));
                                                                                                 ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:104: error: local variables referenced from a lambda expression must be final or effectively final
        Exception exception = assertThrows(IOException.class, () -> invokeParseEncapsulatedToken(token));
                                                                                                 ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:134: error: local variables referenced from a lambda expression must be final or effectively final
        Exception exception = assertThrows(IOException.class, () -> invokeParseEncapsulatedToken(token));
                                                                                                 ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:161: error: local variables referenced from a lambda expression must be final or effectively final
        Exception exception = assertThrows(IOException.class, () -> invokeParseEncapsulatedToken(token));
                                                                                                 ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:177: error: local variables referenced from a lambda expression must be final or effectively final
        Exception exception = assertThrows(IOException.class, () -> invokeParseEncapsulatedToken(token));
                                                                                                 ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:187: error: local variables referenced from a lambda expression must be final or effectively final
        Exception exception = assertThrows(IOException.class, () -> invokeParseEncapsulatedToken(token));
                                                                                                 ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:193: error: local variables referenced from a lambda expression must be final or effectively final
        Exception exceptionEOF = assertThrows(IOException.class, () -> invokeParseEncapsulatedToken(token));
                                                                                                    ^
Note: src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java uses or overrides a deprecated API.
Note: Recompile with -Xlint:deprecation for details.
7 errors

```

And you can use the following related information to assist you in your analysis and correction:
```
{'api document of class org.apache.commons.csv.Lexer': '\n * Lexical analyzer.\n ', 'api document of method parseEncapsulatedToken(Token)': '\n     * Parses an encapsulated token.\n     * <p>\n     * Encapsulated tokens are surrounded by the given encapsulating-string. The encapsulator itself might be included\n     * in the token using a doubling syntax (as "", \'\') or using escaping (as in \\", \\\'). Whitespaces before and after\n     * an encapsulated token are ignored. The token is finished when one of the following conditions become true:\n     * </p>\n     * <ul>\n     * <li>an unescaped encapsulator has been reached, and is followed by optional whitespace then:</li>\n     * <ul>\n     * <li>delimiter (TOKEN)</li>\n     * <li>end of line (EORECORD)</li>\n     * </ul>\n     * <li>end of stream has been reached (EOF)</li> </ul>\n     *\n     * @param token\n     *            the current token\n     * @return a valid token object\n     * @throws IOException\n     *             on invalid state: EOF before closing encapsulator or invalid character before delimiter or EOL\n     ', 'return type': 'org.apache.commons.csv.Token', 'dependent classes': 'class `org.apache.commons.csv.Token`:\n\tdependent fields:\n\t\tjava.lang.StringBuilder org.apache.commons.csv.Token.content;\n\t\torg.apache.commons.csv.Token.Type org.apache.commons.csv.Token.type;\n\t\tboolean org.apache.commons.csv.Token.isQuoted;\n\t\tboolean org.apache.commons.csv.Token.isReady;\n\trelated functions:\n\t\tmethod ` void reset()` returns `void`, related with `org.apache.commons.csv.Lexer.parseEncapsulatedToken(Token)`;\nclass `org.apache.commons.csv.Token.Type`:\n\tdependent fields:\n\t\torg.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.TOKEN;\n\t\torg.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.EORECORD;\n\t\torg.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.EOF;\n\t\nclass `org.apache.commons.csv.Lexer`:\n\tdependent fields:\n\t\tchar[] org.apache.commons.csv.Lexer.delimiter;\n\t\torg.apache.commons.csv.ExtendedBufferedReader org.apache.commons.csv.Lexer.reader;\n\trelated functions:\n\t\tmethod `private Token parseSimpleToken(Token, int)` returns `org.apache.commons.csv.Token`, related with `org.apache.commons.csv.Lexer.parseEncapsulatedToken(Token)`, api document: \n     * Parses a simple token.\n     * <p>\n     * Simple token are tokens which are not surrounded by encapsulators. A simple token might contain escaped\n     * delimiters (as \\, or \\;). The token is finished when one of the following conditions become true:\n     * </p>\n     * <ul>\n     * <li>end of line has been reached (EORECORD)</li>\n     * <li>end of stream has been reached (EOF)</li>\n     * <li>an unescaped delimiter has been reached (TOKEN)</li>\n     * </ul>\n     *\n     * @param token\n     *            the current token\n     * @param ch\n     *            the current character\n     * @return the filled token\n     * @throws IOException\n     *             on stream access error\n     ;\n\t\tmethod ` boolean isDelimiter(int)` returns `boolean`, related with `org.apache.commons.csv.Lexer.parseEncapsulatedToken(Token)`, api document: \n     * Determine whether the next characters constitute a delimiter through {@link ExtendedBufferedReader#lookAhead(char[])}.\n     *\n     * @param ch\n     *             the current character.\n     * @return true if the next characters constitute a delimiter.\n     * @throws IOException If an I/O error occurs.\n     ;\n\t\tmethod ` int readEscape()` returns `int`, related with `org.apache.commons.csv.Lexer.parseEncapsulatedToken(Token)`, api document: \n     * Handle an escape sequence.\n     * The current character must be the escape character.\n     * On return, the next character is available by calling {@link ExtendedBufferedReader#getLastChar()}\n     * on the input stream.\n     *\n     * @return the unescaped character (as an int) or {@link Constants#END_OF_STREAM} if char following the escape is\n     *      invalid.\n     * @throws IOException if there is a problem reading the stream or the end of stream is detected:\n     *      the escape character is not allowed at end of stream\n     ;\n\t\tmethod ` boolean readEndOfLine(int)` returns `boolean`, related with `org.apache.commons.csv.Lexer.parseEncapsulatedToken(Token)`, api document: \n     * Greedily accepts \\n, \\r and \\r\\n This checker consumes silently the second control-character...\n     *\n     * @return true if the given or next character is a line-terminator\n     ;\n\t\tmethod ` Token nextToken(Token)` returns `org.apache.commons.csv.Token`, related with `org.apache.commons.csv.Lexer.parseEncapsulatedToken(Token)`, api document: \n     * Returns the next token.\n     * <p>\n     * A token corresponds to a term, a record change or an end-of-file indicator.\n     * </p>\n     *\n     * @param token\n     *            an existing Token object to reuse. The caller is responsible to initialize the Token.\n     * @return the next token found.\n     * @throws IOException on stream access error.\n     ;\nclass `org.apache.commons.csv.Constants`:\n\tdependent fields:\n\t\tint org.apache.commons.csv.Constants.END_OF_STREAM;\n\t\nclass `org.apache.commons.csv.CSVParser`:\n\trelated functions:\n\t\tmethod ` CSVRecord nextRecord()` returns `org.apache.commons.csv.CSVRecord`, related with `org.apache.commons.csv.Lexer.parseEncapsulatedToken(Token)`, api document: \n     * Parses the next record from the current point in the stream.\n     *\n     * @return the record as an array of values, or {@code null} if the end of the stream has been reached\n     * @throws IOException\n     *             on parse error or input read-failure\n     ;\nclass `org.apache.commons.csv.ExtendedBufferedReader`:\n\trelated functions:\n\t\tmethod `public String readLine()` returns `java.lang.String`, related with `org.apache.commons.csv.Lexer.parseEncapsulatedToken(Token)`, api document: \n     * Gets the next line, dropping the line terminator(s). This method should only be called when processing a\n     * comment, otherwise information can be lost.\n     * <p>\n     * Increments {@link #eolCounter} and updates {@link #position}.\n     * </p>\n     * <p>\n     * Sets {@link #lastChar} to {@link Constants#END_OF_STREAM} at EOF, otherwise the last EOL character.\n     * </p>\n     *\n     * @return the line that was read, or null if reached EOF.\n     ;'}
```

**Note**: If the method-to-test in the @Test function is a private method, you can use **reflection** to call it.

Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 


