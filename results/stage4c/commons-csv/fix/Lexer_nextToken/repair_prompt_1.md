
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
class Lexer_nextToken_Test {

    private CSVFormat csvFormat;

    private ExtendedBufferedReader extendedBufferedReader;

    private Lexer lexer;

    private Token token;

    @BeforeEach
    void setupBeforeEach() {
        csvFormat = CSVFormat.DEFAULT.withIgnoreEmptyLines(false);
        extendedBufferedReader = mock(ExtendedBufferedReader.class);
        lexer = new Lexer(csvFormat, extendedBufferedReader);
        token = new Token();
    }

    @Test
    void testNextTokenWithEOF() throws IOException {
        // Simulate EOF
        when(extendedBufferedReader.read()).thenReturn(-1);
        when(extendedBufferedReader.getLastChar()).thenReturn(-1);
        Token result = lexer.nextToken(token);
        assertEquals(Token.Type.EOF, result.type);
    }

    @Test
    void testNextTokenWithComment() throws IOException {
        // Simulate reading a comment line
        when(extendedBufferedReader.read()).thenReturn((int) '#', (int) 'c', (int) 'o', (int) 'm', (int) 'm', (int) 'e', (int) 'n', (int) 't', (int) '\n');
        when(extendedBufferedReader.getLastChar()).thenReturn((int) '\n');
        when(extendedBufferedReader.readLine()).thenReturn("comment");
        Token result = lexer.nextToken(token);
        assertEquals(Token.Type.COMMENT, result.type);
        assertEquals("comment", result.content.toString());
    }

    @Test
    void testNextTokenWithDelimiter() throws IOException {
        // Simulate reading a delimiter
        when(extendedBufferedReader.read()).thenReturn((int) ',');
        Token result = lexer.nextToken(token);
        assertEquals(Token.Type.TOKEN, result.type);
    }

    @Test
    void testNextTokenWithEndOfLine() throws IOException {
        // Simulate reading an end of line
        when(extendedBufferedReader.read()).thenReturn((int) '\n');
        when(lexer.readEndOfLine((int) '\n')).thenReturn(true);
        Token result = lexer.nextToken(token);
        assertEquals(Token.Type.EORECORD, result.type);
    }

    @Test
    void testNextTokenWithEOFAndData() throws IOException {
        // Simulate EOF with data present
        when(extendedBufferedReader.read()).thenReturn(-1);
        when(extendedBufferedReader.getLastChar()).thenReturn((int) 'd');
        Token result = lexer.nextToken(token);
        assertEquals(Token.Type.EOF, result.type);
        assertTrue(result.isReady);
    }

    @Test
    void testNextTokenWithQuoteChar() throws IOException {
        // Simulate reading a quoted token
        when(extendedBufferedReader.read()).thenReturn((int) '"');
        doAnswer(invocation -> {
            Token t = invocation.getArgument(0);
            t.content.append("quoted");
            return t;
        }).when(lexer).parseEncapsulatedToken(any(Token.class));
        Token result = lexer.nextToken(token);
        assertEquals("quoted", result.content.toString());
    }

    @Test
    void testNextTokenWithSimpleToken() throws IOException {
        // Simulate reading a simple token
        when(extendedBufferedReader.read()).thenReturn((int) 'a');
        doAnswer(invocation -> {
            Token t = invocation.getArgument(0);
            t.content.append("simple");
            return t;
        }).when(lexer).parseSimpleToken(any(Token.class), eq((int) 'a'));
        Token result = lexer.nextToken(token);
        assertEquals("simple", result.content.toString());
    }

    @Test
    void testNextTokenWithIgnoreEmptyLines() throws IOException {
        // Simulate empty lines at end of file
        lexer = new Lexer(CSVFormat.DEFAULT.withIgnoreEmptyLines(true), extendedBufferedReader);
        when(extendedBufferedReader.read()).thenReturn((int) '\n', -1);
        when(extendedBufferedReader.getLastChar()).thenReturn((int) '\n');
        Token result = lexer.nextToken(token);
        assertEquals(Token.Type.EOF, result.type);
    }

    @Test
    void testNextTokenWithLeadingSpacesAndDelimiter() throws IOException {
        // Simulate leading spaces followed by a delimiter
        lexer = new Lexer(CSVFormat.DEFAULT.withIgnoreSurroundingSpaces(true), extendedBufferedReader);
        when(extendedBufferedReader.read()).thenReturn((int) ' ', (int) ' ', (int) ',');
        Token result = lexer.nextToken(token);
        assertEquals(Token.Type.TOKEN, result.type);
    }

    @Test
    void testNextTokenWithLeadingSpacesAndNonDelimiter() throws IOException {
        // Simulate leading spaces followed by a non-delimiter character
        lexer = new Lexer(CSVFormat.DEFAULT.withIgnoreSurroundingSpaces(true), extendedBufferedReader);
        when(extendedBufferedReader.read()).thenReturn((int) ' ', (int) ' ', (int) 'a');
        doAnswer(invocation -> {
            Token t = invocation.getArgument(0);
            t.content.append("a");
            return t;
        }).when(lexer).parseSimpleToken(any(Token.class), eq((int) 'a'));
        Token result = lexer.nextToken(token);
        assertEquals("a", result.content.toString());
    }

    @Test
    void testNextTokenWithLeadingSpacesAndEOL() throws IOException {
        // Simulate leading spaces followed by an end of line
        lexer = new Lexer(CSVFormat.DEFAULT.withIgnoreSurroundingSpaces(true), extendedBufferedReader);
        when(extendedBufferedReader.read()).thenReturn((int) ' ', (int) ' ', (int) '\n');
        when(lexer.readEndOfLine((int) '\n')).thenReturn(true);
        Token result = lexer.nextToken(token);
        assertEquals(Token.Type.EORECORD, result.type);
    }

    @Test
    void testNextTokenWithTrimmedComment() throws IOException {
        // Simulate a comment with leading and trailing spaces
        when(extendedBufferedReader.read()).thenReturn((int) '#', (int) ' ', (int) 'c', (int) 'o', (int) 'm', (int) 'm', (int) 'e', (int) 'n', (int) 't', (int) ' ', (int) '\n');
        when(extendedBufferedReader.getLastChar()).thenReturn((int) '\n');
        when(extendedBufferedReader.readLine()).thenReturn(" comment ");
        Token result = lexer.nextToken(token);
        assertEquals(Token.Type.COMMENT, result.type);
        assertEquals("comment", result.content.toString().trim());
    }

    @Test
    void testNextTokenWithNullToken() {
        // Test passing a null token
        assertThrows(NullPointerException.class, () -> lexer.nextToken(null));
    }

    @Test
    void testNextTokenWithIOException() throws IOException {
        // Simulate IOException during reading
        when(extendedBufferedReader.read()).thenThrow(IOException.class);
        assertThrows(IOException.class, () -> lexer.nextToken(token));
    }
}
```

Corresponding compilation feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:86: error: parseEncapsulatedToken(Token) has private access in Lexer
        }).when(lexer).parseEncapsulatedToken(any(Token.class));
                      ^
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:99: error: parseSimpleToken(Token,int) has private access in Lexer
        }).when(lexer).parseSimpleToken(any(Token.class), eq((int) 'a'));
                      ^
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:132: error: parseSimpleToken(Token,int) has private access in Lexer
        }).when(lexer).parseSimpleToken(any(Token.class), eq((int) 'a'));
                      ^
Note: src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java uses or overrides a deprecated API.
Note: Recompile with -Xlint:deprecation for details.
3 errors

```

And you can use the following related information to assist you in your analysis and correction:
```
{'api document of class org.apache.commons.csv.Lexer': '\n * Lexical analyzer.\n ', 'api document of method nextToken(Token)': '\n     * Returns the next token.\n     * <p>\n     * A token corresponds to a term, a record change or an end-of-file indicator.\n     * </p>\n     *\n     * @param token\n     *            an existing Token object to reuse. The caller is responsible to initialize the Token.\n     * @return the next token found.\n     * @throws IOException on stream access error.\n     ', 'return type': 'org.apache.commons.csv.Token', 'dependent classes': 'class `org.apache.commons.csv.Token`:\n\tdependent fields:\n\t\tjava.lang.StringBuilder org.apache.commons.csv.Token.content;\n\t\tboolean org.apache.commons.csv.Token.isReady;\n\t\torg.apache.commons.csv.Token.Type org.apache.commons.csv.Token.type;\n\trelated functions:\n\t\tmethod `public String toString()` returns `java.lang.String`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`, api document: \n     * Eases IDE debugging.\n     *\n     * @return a string helpful for debugging.\n     ;\n\t\tmethod ` void reset()` returns `void`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`;\nclass `org.apache.commons.csv.Token.Type`:\n\tdependent fields:\n\t\torg.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.COMMENT;\n\t\torg.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.TOKEN;\n\t\torg.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.INVALID;\n\t\torg.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.EOF;\n\t\torg.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.EORECORD;\n\t\nclass `org.apache.commons.csv.Lexer`:\n\tdependent fields:\n\t\tboolean org.apache.commons.csv.Lexer.ignoreSurroundingSpaces;\n\t\torg.apache.commons.csv.ExtendedBufferedReader org.apache.commons.csv.Lexer.reader;\n\t\tboolean org.apache.commons.csv.Lexer.ignoreEmptyLines;\n\t\tboolean org.apache.commons.csv.Lexer.isLastTokenDelimiter;\n\trelated functions:\n\t\tmethod `private Token parseSimpleToken(Token, int)` returns `org.apache.commons.csv.Token`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`, api document: \n     * Parses a simple token.\n     * <p>\n     * Simple token are tokens which are not surrounded by encapsulators. A simple token might contain escaped\n     * delimiters (as \\, or \\;). The token is finished when one of the following conditions become true:\n     * </p>\n     * <ul>\n     * <li>end of line has been reached (EORECORD)</li>\n     * <li>end of stream has been reached (EOF)</li>\n     * <li>an unescaped delimiter has been reached (TOKEN)</li>\n     * </ul>\n     *\n     * @param token\n     *            the current token\n     * @param ch\n     *            the current character\n     * @return the filled token\n     * @throws IOException\n     *             on stream access error\n     ;\n\t\tmethod `private Token parseEncapsulatedToken(Token)` returns `org.apache.commons.csv.Token`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`, api document: \n     * Parses an encapsulated token.\n     * <p>\n     * Encapsulated tokens are surrounded by the given encapsulating-string. The encapsulator itself might be included\n     * in the token using a doubling syntax (as "", \'\') or using escaping (as in \\", \\\'). Whitespaces before and after\n     * an encapsulated token are ignored. The token is finished when one of the following conditions become true:\n     * </p>\n     * <ul>\n     * <li>an unescaped encapsulator has been reached, and is followed by optional whitespace then:</li>\n     * <ul>\n     * <li>delimiter (TOKEN)</li>\n     * <li>end of line (EORECORD)</li>\n     * </ul>\n     * <li>end of stream has been reached (EOF)</li> </ul>\n     *\n     * @param token\n     *            the current token\n     * @return a valid token object\n     * @throws IOException\n     *             on invalid state: EOF before closing encapsulator or invalid character before delimiter or EOL\n     ;\n\t\tmethod ` int readEscape()` returns `int`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`, api document: \n     * Handle an escape sequence.\n     * The current character must be the escape character.\n     * On return, the next character is available by calling {@link ExtendedBufferedReader#getLastChar()}\n     * on the input stream.\n     *\n     * @return the unescaped character (as an int) or {@link Constants#END_OF_STREAM} if char following the escape is\n     *      invalid.\n     * @throws IOException if there is a problem reading the stream or the end of stream is detected:\n     *      the escape character is not allowed at end of stream\n     ;\n\t\tmethod ` boolean readEndOfLine(int)` returns `boolean`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`, api document: \n     * Greedily accepts \\n, \\r and \\r\\n This checker consumes silently the second control-character...\n     *\n     * @return true if the given or next character is a line-terminator\n     ;\n\t\tmethod ` boolean isDelimiter(int)` returns `boolean`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`, api document: \n     * Determine whether the next characters constitute a delimiter through {@link ExtendedBufferedReader#lookAhead(char[])}.\n     *\n     * @param ch\n     *             the current character.\n     * @return true if the next characters constitute a delimiter.\n     * @throws IOException If an I/O error occurs.\n     ;\nclass `org.apache.commons.csv.CSVParser`:\n\trelated functions:\n\t\tmethod ` CSVRecord nextRecord()` returns `org.apache.commons.csv.CSVRecord`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`, api document: \n     * Parses the next record from the current point in the stream.\n     *\n     * @return the record as an array of values, or {@code null} if the end of the stream has been reached\n     * @throws IOException\n     *             on parse error or input read-failure\n     ;\nclass `org.apache.commons.csv.CSVFormat`:\n\trelated functions:\n\t\tmethod ` static boolean isBlank(String)` returns `boolean`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`;'}
```

**Note**: If the method-to-test in the @Test function is a private method, you can use **reflection** to call it.

Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 


