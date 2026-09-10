
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

    @Test
    void testParseEncapsulatedToken() throws IOException {
        // Given
        Token token = new Token();
        // Set up the necessary behavior for the mocked reader
        // Example: when(reader.read()).thenReturn(...);
        // When
        lexer.parseEncapsulatedToken(token);
        // Then
        // Add assertions to verify the expected behavior of the token after parsing
        // Example: assertEquals(expectedValue, token.content.toString());
    }

    @Test
    void testParseEncapsulatedToken_EscapeCharacters() throws IOException {
        // Test when an escape character is followed by an escape delimiter
        Token token = new Token();
        // Escape character followed by escape delimiter
        when(reader.read()).thenReturn('\\', ';');
        lexer.parseEncapsulatedToken(token);
        assertTrue(token.content.toString().contains(";"), "Expected token.content to contain escaped delimiter");
        // Test when an escape character is followed by a valid character
        token = new Token();
        // Escape character followed by valid character
        when(reader.read()).thenReturn('\\', 'a');
        lexer.parseEncapsulatedToken(token);
        assertTrue(token.content.toString().contains("a"), "Expected token.content to contain the unescaped character");
        // Test when an escape character is followed by end of stream
        token = new Token();
        // Escape character followed by end of stream
        when(reader.read()).thenReturn('\\', Constants.END_OF_STREAM);
        Exception exception = assertThrows(IOException.class, () -> lexer.parseEncapsulatedToken(token));
        assertTrue(exception.getMessage().contains("unexpected char after escape"), "Expected IOException with correct message");
    }

    @Test
    void testParseEncapsulatedToken_QuotingLogic() throws IOException {
        // Test when a quote character is followed by another quote character
        Token token = new Token();
        // Quote followed by quote, then character
        when(reader.read()).thenReturn('"', '"', 'b', ';');
        lexer.parseEncapsulatedToken(token);
        assertTrue(token.content.toString().contains("b"), "Expected token.content to contain the character read after a quote character");
        // Test when a quote character is followed by a delimiter
        token = new Token();
        // Quote followed by delimiter
        when(reader.read()).thenReturn('"', ';');
        lexer.parseEncapsulatedToken(token);
        assertEquals(Token.Type.TOKEN, token.type, "Expected token.type to be TOKEN");
        // Test when a quote character is followed by end of file
        token = new Token();
        // Quote followed by EOF
        when(reader.read()).thenReturn('"', Constants.END_OF_STREAM);
        lexer.parseEncapsulatedToken(token);
        assertEquals(Token.Type.EOF, token.type, "Expected token.type to be EOF");
        assertTrue(token.isReady, "Expected token.isReady to be true");
        // Test when a non-whitespace character is found between the token and the next delimiter
        token = new Token();
        // Invalid character found
        when(reader.read()).thenReturn('"', 'x', ';');
        Exception exception = assertThrows(IOException.class, () -> lexer.parseEncapsulatedToken(token));
        assertTrue(exception.getMessage().contains("invalid char between encapsulated token and delimiter"), "Expected IOException with correct message");
    }

    @Test
    void testParseEncapsulatedToken_EndOfFileHandling() throws IOException {
        // Test when EOF is reached before the token is finished
        Token token = new Token();
        // End of file reached
        when(reader.read()).thenReturn(Constants.END_OF_STREAM);
        Exception exception = assertThrows(IOException.class, () -> lexer.parseEncapsulatedToken(token));
        assertTrue(exception.getMessage().contains("EOF reached before encapsulated token finished"), "Expected IOException with correct message");
    }

    @Test
    void testParseEncapsulatedToken_GeneralCharacterConsumption() throws IOException {
        // Test normal flow where characters are read and appended to the token content until a delimiter is reached
        Token token = new Token();
        // Characters followed by delimiter
        when(reader.read()).thenReturn('a', 'b', 'c', ';');
        lexer.parseEncapsulatedToken(token);
        assertEquals("abc", token.content.toString(), "Expected token.content to contain consumed characters");
    }

    @Test
    void testParseEncapsulatedToken_EdgeCases() throws IOException {
        // Test when a character that is neither whitespace nor a delimiter is found directly after a quote character
        Token token = new Token();
        // Invalid character after quote
        when(reader.read()).thenReturn('"', '!', ';');
        Exception exception = assertThrows(IOException.class, () -> lexer.parseEncapsulatedToken(token));
        assertTrue(exception.getMessage().contains("invalid char between encapsulated token and delimiter"), "Expected IOException with correct message");
        // Test when an empty token is passed to the method
        token = new Token();
        lexer.parseEncapsulatedToken(token);
        assertEquals("", token.content.toString(), "Expected token.content to be empty");
        // Test when characters are consumed until EOF is reached
        token = new Token();
        // Characters until EOF
        when(reader.read()).thenReturn('a', 'b', Constants.END_OF_STREAM);
        lexer.parseEncapsulatedToken(token);
        assertEquals("ab", token.content.toString(), "Expected token.content to contain characters before EOF");
        assertEquals(Token.Type.EOF, token.type, "Expected token.type to be EOF");
    }

    @Test
    void testParseEncapsulatedToken_SpecialCharacters() throws IOException {
        // Test when special characters (e.g., newline, tab) are included in the token content
        Token token = new Token();
        // Special character included
        when(reader.read()).thenReturn('a', '\n', 'b', ';');
        lexer.parseEncapsulatedToken(token);
        assertTrue(token.content.toString().contains("\n"), "Expected token.content to contain special characters");
        // Test when an invalid escape sequence is encountered
        token = new Token();
        // Invalid escape sequence
        when(reader.read()).thenReturn('\\', '!', ';');
        Exception exception = assertThrows(IOException.class, () -> lexer.parseEncapsulatedToken(token));
        assertTrue(exception.getMessage().contains("invalid escape sequence"), "Expected IOException with correct message");
    }

    @Test
    void testParseEncapsulatedToken_MultipleQuotes() throws IOException {
        // Test when multiple quote characters are used in succession
        Token token = new Token();
        // Multiple quotes
        when(reader.read()).thenReturn('"', '"', '"', ';');
        lexer.parseEncapsulatedToken(token);
        assertTrue(token.content.toString().contains("\""), "Expected token.content to contain all characters between quotes");
        // Test when an opening quote is present without a matching closing quote
        token = new Token();
        // Unmatched quote
        when(reader.read()).thenReturn('"', 'a', Constants.END_OF_STREAM);
        Exception exception = assertThrows(IOException.class, () -> lexer.parseEncapsulatedToken(token));
        assertTrue(exception.getMessage().contains("unmatched quotes"), "Expected IOException with correct message");
    }

    @Test
    void testParseEncapsulatedToken_ExceptionScenarios() throws IOException {
        // Test when a non-whitespace character is found between the token and the next delimiter after a quote character
        Token token = new Token();
        // Invalid character found
        when(reader.read()).thenReturn('"', 'x', ';');
        Exception exception = assertThrows(IOException.class, () -> lexer.parseEncapsulatedToken(token));
        assertTrue(exception.getMessage().contains("invalid char between encapsulated token and delimiter"), "Expected IOException with correct message");
        // Test when the end of file is reached before the encapsulated token is finished
        token = new Token();
        // EOF reached
        when(reader.read()).thenReturn('"', Constants.END_OF_STREAM);
        Exception exceptionEOF = assertThrows(IOException.class, () -> lexer.parseEncapsulatedToken(token));
        assertTrue(exceptionEOF.getMessage().contains("EOF reached before encapsulated token finished"), "Expected IOException with correct message");
    }
}
```

Corresponding compilation feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:46: error: parseEncapsulatedToken(Token) has private access in Lexer
        lexer.parseEncapsulatedToken(token);
             ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:57: error: incompatible types: char cannot be converted to Integer
        when(reader.read()).thenReturn('\\', ';');
                                       ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:58: error: parseEncapsulatedToken(Token) has private access in Lexer
        lexer.parseEncapsulatedToken(token);
             ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:63: error: incompatible types: char cannot be converted to Integer
        when(reader.read()).thenReturn('\\', 'a');
                                       ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:64: error: parseEncapsulatedToken(Token) has private access in Lexer
        lexer.parseEncapsulatedToken(token);
             ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:69: error: incompatible types: char cannot be converted to Integer
        when(reader.read()).thenReturn('\\', Constants.END_OF_STREAM);
                                       ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:70: error: parseEncapsulatedToken(Token) has private access in Lexer
        Exception exception = assertThrows(IOException.class, () -> lexer.parseEncapsulatedToken(token));
                                                                         ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:79: error: incompatible types: char cannot be converted to Integer
        when(reader.read()).thenReturn('"', '"', 'b', ';');
                                       ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:80: error: parseEncapsulatedToken(Token) has private access in Lexer
        lexer.parseEncapsulatedToken(token);
             ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:85: error: incompatible types: char cannot be converted to Integer
        when(reader.read()).thenReturn('"', ';');
                                       ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:86: error: parseEncapsulatedToken(Token) has private access in Lexer
        lexer.parseEncapsulatedToken(token);
             ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:91: error: incompatible types: char cannot be converted to Integer
        when(reader.read()).thenReturn('"', Constants.END_OF_STREAM);
                                       ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:92: error: parseEncapsulatedToken(Token) has private access in Lexer
        lexer.parseEncapsulatedToken(token);
             ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:98: error: incompatible types: char cannot be converted to Integer
        when(reader.read()).thenReturn('"', 'x', ';');
                                       ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:99: error: parseEncapsulatedToken(Token) has private access in Lexer
        Exception exception = assertThrows(IOException.class, () -> lexer.parseEncapsulatedToken(token));
                                                                         ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:109: error: parseEncapsulatedToken(Token) has private access in Lexer
        Exception exception = assertThrows(IOException.class, () -> lexer.parseEncapsulatedToken(token));
                                                                         ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:118: error: incompatible types: char cannot be converted to Integer
        when(reader.read()).thenReturn('a', 'b', 'c', ';');
                                       ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:119: error: parseEncapsulatedToken(Token) has private access in Lexer
        lexer.parseEncapsulatedToken(token);
             ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:128: error: incompatible types: char cannot be converted to Integer
        when(reader.read()).thenReturn('"', '!', ';');
                                       ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:129: error: parseEncapsulatedToken(Token) has private access in Lexer
        Exception exception = assertThrows(IOException.class, () -> lexer.parseEncapsulatedToken(token));
                                                                         ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:133: error: parseEncapsulatedToken(Token) has private access in Lexer
        lexer.parseEncapsulatedToken(token);
             ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:138: error: incompatible types: char cannot be converted to Integer
        when(reader.read()).thenReturn('a', 'b', Constants.END_OF_STREAM);
                                       ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:139: error: parseEncapsulatedToken(Token) has private access in Lexer
        lexer.parseEncapsulatedToken(token);
             ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:149: error: incompatible types: char cannot be converted to Integer
        when(reader.read()).thenReturn('a', '\n', 'b', ';');
                                       ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:150: error: parseEncapsulatedToken(Token) has private access in Lexer
        lexer.parseEncapsulatedToken(token);
             ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:155: error: incompatible types: char cannot be converted to Integer
        when(reader.read()).thenReturn('\\', '!', ';');
                                       ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:156: error: parseEncapsulatedToken(Token) has private access in Lexer
        Exception exception = assertThrows(IOException.class, () -> lexer.parseEncapsulatedToken(token));
                                                                         ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:165: error: incompatible types: char cannot be converted to Integer
        when(reader.read()).thenReturn('"', '"', '"', ';');
                                       ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:166: error: parseEncapsulatedToken(Token) has private access in Lexer
        lexer.parseEncapsulatedToken(token);
             ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:171: error: incompatible types: char cannot be converted to Integer
        when(reader.read()).thenReturn('"', 'a', Constants.END_OF_STREAM);
                                       ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:172: error: parseEncapsulatedToken(Token) has private access in Lexer
        Exception exception = assertThrows(IOException.class, () -> lexer.parseEncapsulatedToken(token));
                                                                         ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:181: error: incompatible types: char cannot be converted to Integer
        when(reader.read()).thenReturn('"', 'x', ';');
                                       ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:182: error: parseEncapsulatedToken(Token) has private access in Lexer
        Exception exception = assertThrows(IOException.class, () -> lexer.parseEncapsulatedToken(token));
                                                                         ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:187: error: incompatible types: char cannot be converted to Integer
        when(reader.read()).thenReturn('"', Constants.END_OF_STREAM);
                                       ^
src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java:188: error: parseEncapsulatedToken(Token) has private access in Lexer
        Exception exceptionEOF = assertThrows(IOException.class, () -> lexer.parseEncapsulatedToken(token));
                                                                            ^
Note: src/test/java/org/apache/commons/csv/Lexer_parseEncapsulatedToken_Test.java uses or overrides a deprecated API.
Note: Recompile with -Xlint:deprecation for details.
Note: Some messages have been simplified; recompile with -Xdiags:verbose to get full output
35 errors

```

And you can use the following related information to assist you in your analysis and correction:
```
{'api document of class org.apache.commons.csv.Lexer': '\n * Lexical analyzer.\n ', 'api document of method parseEncapsulatedToken(Token)': '\n     * Parses an encapsulated token.\n     * <p>\n     * Encapsulated tokens are surrounded by the given encapsulating-string. The encapsulator itself might be included\n     * in the token using a doubling syntax (as "", \'\') or using escaping (as in \\", \\\'). Whitespaces before and after\n     * an encapsulated token are ignored. The token is finished when one of the following conditions become true:\n     * </p>\n     * <ul>\n     * <li>an unescaped encapsulator has been reached, and is followed by optional whitespace then:</li>\n     * <ul>\n     * <li>delimiter (TOKEN)</li>\n     * <li>end of line (EORECORD)</li>\n     * </ul>\n     * <li>end of stream has been reached (EOF)</li> </ul>\n     *\n     * @param token\n     *            the current token\n     * @return a valid token object\n     * @throws IOException\n     *             on invalid state: EOF before closing encapsulator or invalid character before delimiter or EOL\n     ', 'return type': 'org.apache.commons.csv.Token', 'dependent classes': 'class `org.apache.commons.csv.Token`:\n\tdependent fields:\n\t\tjava.lang.StringBuilder org.apache.commons.csv.Token.content;\n\t\torg.apache.commons.csv.Token.Type org.apache.commons.csv.Token.type;\n\t\tboolean org.apache.commons.csv.Token.isQuoted;\n\t\tboolean org.apache.commons.csv.Token.isReady;\n\trelated functions:\n\t\tmethod ` void reset()` returns `void`, related with `org.apache.commons.csv.Lexer.parseEncapsulatedToken(Token)`;\nclass `org.apache.commons.csv.Token.Type`:\n\tdependent fields:\n\t\torg.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.TOKEN;\n\t\torg.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.EORECORD;\n\t\torg.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.EOF;\n\t\nclass `org.apache.commons.csv.Lexer`:\n\tdependent fields:\n\t\tchar[] org.apache.commons.csv.Lexer.delimiter;\n\t\torg.apache.commons.csv.ExtendedBufferedReader org.apache.commons.csv.Lexer.reader;\n\trelated functions:\n\t\tmethod `private Token parseSimpleToken(Token, int)` returns `org.apache.commons.csv.Token`, related with `org.apache.commons.csv.Lexer.parseEncapsulatedToken(Token)`, api document: \n     * Parses a simple token.\n     * <p>\n     * Simple token are tokens which are not surrounded by encapsulators. A simple token might contain escaped\n     * delimiters (as \\, or \\;). The token is finished when one of the following conditions become true:\n     * </p>\n     * <ul>\n     * <li>end of line has been reached (EORECORD)</li>\n     * <li>end of stream has been reached (EOF)</li>\n     * <li>an unescaped delimiter has been reached (TOKEN)</li>\n     * </ul>\n     *\n     * @param token\n     *            the current token\n     * @param ch\n     *            the current character\n     * @return the filled token\n     * @throws IOException\n     *             on stream access error\n     ;\n\t\tmethod ` boolean isDelimiter(int)` returns `boolean`, related with `org.apache.commons.csv.Lexer.parseEncapsulatedToken(Token)`, api document: \n     * Determine whether the next characters constitute a delimiter through {@link ExtendedBufferedReader#lookAhead(char[])}.\n     *\n     * @param ch\n     *             the current character.\n     * @return true if the next characters constitute a delimiter.\n     * @throws IOException If an I/O error occurs.\n     ;\n\t\tmethod ` int readEscape()` returns `int`, related with `org.apache.commons.csv.Lexer.parseEncapsulatedToken(Token)`, api document: \n     * Handle an escape sequence.\n     * The current character must be the escape character.\n     * On return, the next character is available by calling {@link ExtendedBufferedReader#getLastChar()}\n     * on the input stream.\n     *\n     * @return the unescaped character (as an int) or {@link Constants#END_OF_STREAM} if char following the escape is\n     *      invalid.\n     * @throws IOException if there is a problem reading the stream or the end of stream is detected:\n     *      the escape character is not allowed at end of stream\n     ;\n\t\tmethod ` boolean readEndOfLine(int)` returns `boolean`, related with `org.apache.commons.csv.Lexer.parseEncapsulatedToken(Token)`, api document: \n     * Greedily accepts \\n, \\r and \\r\\n This checker consumes silently the second control-character...\n     *\n     * @return true if the given or next character is a line-terminator\n     ;\n\t\tmethod ` Token nextToken(Token)` returns `org.apache.commons.csv.Token`, related with `org.apache.commons.csv.Lexer.parseEncapsulatedToken(Token)`, api document: \n     * Returns the next token.\n     * <p>\n     * A token corresponds to a term, a record change or an end-of-file indicator.\n     * </p>\n     *\n     * @param token\n     *            an existing Token object to reuse. The caller is responsible to initialize the Token.\n     * @return the next token found.\n     * @throws IOException on stream access error.\n     ;\nclass `org.apache.commons.csv.Constants`:\n\tdependent fields:\n\t\tint org.apache.commons.csv.Constants.END_OF_STREAM;\n\t\nclass `org.apache.commons.csv.CSVParser`:\n\trelated functions:\n\t\tmethod ` CSVRecord nextRecord()` returns `org.apache.commons.csv.CSVRecord`, related with `org.apache.commons.csv.Lexer.parseEncapsulatedToken(Token)`, api document: \n     * Parses the next record from the current point in the stream.\n     *\n     * @return the record as an array of values, or {@code null} if the end of the stream has been reached\n     * @throws IOException\n     *             on parse error or input read-failure\n     ;\nclass `org.apache.commons.csv.ExtendedBufferedReader`:\n\trelated functions:\n\t\tmethod `public String readLine()` returns `java.lang.String`, related with `org.apache.commons.csv.Lexer.parseEncapsulatedToken(Token)`, api document: \n     * Gets the next line, dropping the line terminator(s). This method should only be called when processing a\n     * comment, otherwise information can be lost.\n     * <p>\n     * Increments {@link #eolCounter} and updates {@link #position}.\n     * </p>\n     * <p>\n     * Sets {@link #lastChar} to {@link Constants#END_OF_STREAM} at EOF, otherwise the last EOL character.\n     * </p>\n     *\n     * @return the line that was read, or null if reached EOF.\n     ;'}
```

**Note**: If the method-to-test in the @Test function is a private method, you can use **reflection** to call it.

Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 


