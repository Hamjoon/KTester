
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
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.IOException;
import java.io.StringReader;

@ExtendWith(MockitoExtension.class)
class Lexer_nextToken_Test {

    private CSVFormat formatWithEscaping;

    private Lexer lexer;

    private ExtendedBufferedReader reader;

    private Token token;

    @BeforeEach
    void setupBeforeEach() {
        formatWithEscaping = CSVFormat.DEFAULT.withEscape('\\');
        // Initialize the token to be passed to nextToken
        token = new Token();
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup resources after each test if necessary
        if (lexer != null) {
            try {
                lexer.close();
            } catch (IOException e) {
                // Handle exception
            }
        }
    }

    // Custom matcher for token verification
    private void assertToken(Token actual, Token.Type expectedType, String expectedContent) {
        assertEquals(expectedType, actual.type);
        assertEquals(expectedContent, actual.content.toString());
    }

    // Test cases for nextToken method
    @Test
    void testNextTokenWithEscaping() throws IOException {
        final String code = "a,\\,,b\\\\\n\\,,\\\nc,d\\\r\ne";
        lexer = new Lexer(formatWithEscaping, new ExtendedBufferedReader(new StringReader(code)));
        assertToken(lexer.nextToken(token), Token.Type.TOKEN, "a");
        assertToken(lexer.nextToken(token), Token.Type.TOKEN, ",");
        assertToken(lexer.nextToken(token), Token.Type.EORECORD, "b\\");
        assertToken(lexer.nextToken(token), Token.Type.TOKEN, ",");
        assertToken(lexer.nextToken(token), Token.Type.TOKEN, "\nc");
        assertToken(lexer.nextToken(token), Token.Type.EORECORD, "d\r");
        assertToken(lexer.nextToken(token), Token.Type.EOF, "e");
    }

    @Test
    void testNextTokenWithoutEscaping() throws IOException {
        final String code = "a,\\,,b\\\n\\,,";
        lexer = new Lexer(CSVFormat.DEFAULT, new ExtendedBufferedReader(new StringReader(code)));
        assertToken(lexer.nextToken(token), Token.Type.TOKEN, "a");
        assertToken(lexer.nextToken(token), Token.Type.TOKEN, "\\");
        assertToken(lexer.nextToken(token), Token.Type.TOKEN, "");
        assertToken(lexer.nextToken(token), Token.Type.EORECORD, "b\\");
        assertToken(lexer.nextToken(token), Token.Type.TOKEN, "\\");
        assertToken(lexer.nextToken(token), Token.Type.TOKEN, "");
        assertToken(lexer.nextToken(token), Token.Type.EOF, "");
    }

    @Test
    void testNextTokenWithSurroundingSpaces() throws IOException {
        final String code = " noSpaces ,  leadingSpaces , trailingSpaces  ,  surroundingSpaces  ,  ,, ";
        lexer = new Lexer(CSVFormat.DEFAULT.withIgnoreSurroundingSpaces(), new ExtendedBufferedReader(new StringReader(code)));
        assertToken(lexer.nextToken(token), Token.Type.TOKEN, "noSpaces");
        assertToken(lexer.nextToken(token), Token.Type.TOKEN, "leadingSpaces");
        assertToken(lexer.nextToken(token), Token.Type.TOKEN, "trailingSpaces");
        assertToken(lexer.nextToken(token), Token.Type.TOKEN, "surroundingSpaces");
        assertToken(lexer.nextToken(token), Token.Type.TOKEN, "");
        assertToken(lexer.nextToken(token), Token.Type.TOKEN, "");
        assertToken(lexer.nextToken(token), Token.Type.EOF, "");
    }

    // Test cases for empty lines handling
    @Test
    void testNextTokenEmptyLinesHandling() throws IOException {
        // Test when the input contains only empty lines and ignoreEmptyLines is true
        lexer = new Lexer(CSVFormat.DEFAULT.withIgnoreEmptyLines(true), new ExtendedBufferedReader(new StringReader("\n\n")));
        assertToken(lexer.nextToken(token), Token.Type.EOF);
        // Test when the input contains only empty lines and ignoreEmptyLines is false
        lexer = new Lexer(CSVFormat.DEFAULT.withIgnoreEmptyLines(false), new ExtendedBufferedReader(new StringReader("\n\n")));
        assertToken(lexer.nextToken(token), Token.Type.EOF);
    }

    // Test cases for end of file handling
    @Test
    void testNextTokenEndOfFileHandling() throws IOException {
        // Test when the last read character is EOF
        lexer = new Lexer(CSVFormat.DEFAULT, new ExtendedBufferedReader(new StringReader("")));
        assertToken(lexer.nextToken(token), Token.Type.EOF);
        // Test when the current character read is EOF
        lexer = new Lexer(CSVFormat.DEFAULT, new ExtendedBufferedReader(new StringReader("a")));
        // Consume 'a'
        lexer.nextToken(token);
        assertToken(lexer.nextToken(token), Token.Type.EOF);
    }

    // Test cases for comment handling
    @Test
    void testNextTokenCommentHandling() throws IOException {
        // Test when the current character is a comment start and the line has a comment
        lexer = new Lexer(CSVFormat.DEFAULT, new ExtendedBufferedReader(new StringReader("# This is a comment\n")));
        assertToken(lexer.nextToken(token), Token.Type.COMMENT, "This is a comment");
        // Test when the line is empty after a comment
        lexer = new Lexer(CSVFormat.DEFAULT, new ExtendedBufferedReader(new StringReader("# Comment\n\n")));
        assertToken(lexer.nextToken(token), Token.Type.EOF);
    }

    // Test cases for token parsing
    @Test
    void testNextTokenParsing() throws IOException {
        // Test when a valid token is read
        lexer = new Lexer(CSVFormat.DEFAULT, new ExtendedBufferedReader(new StringReader("sampleToken,,")));
        assertToken(lexer.nextToken(token), Token.Type.TOKEN, "sampleToken");
        // Test when an end of record is reached
        assertToken(lexer.nextToken(token), Token.Type.EORECORD);
        // Test when EOF is reached with valid content
        lexer = new Lexer(CSVFormat.DEFAULT, new ExtendedBufferedReader(new StringReader("sampleToken")));
        assertToken(lexer.nextToken(token), Token.Type.TOKEN, "sampleToken");
        assertToken(lexer.nextToken(token), Token.Type.EOF, true);
    }

    // Test cases for whitespace handling
    @Test
    void testNextTokenWhitespaceHandling() throws IOException {
        // Test when surrounding spaces are ignored and a valid token is read
        lexer = new Lexer(CSVFormat.DEFAULT.withIgnoreSurroundingSpaces(), new ExtendedBufferedReader(new StringReader("  trimmedToken  ,  ")));
        assertToken(lexer.nextToken(token), Token.Type.TOKEN, "trimmedToken");
        // Test when only whitespace is encountered and eol is true
        assertToken(lexer.nextToken(token), Token.Type.EORECORD);
    }
}
```

Corresponding compilation feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:92: error: method assertToken in class Lexer_nextToken_Test cannot be applied to given types;
        assertToken(lexer.nextToken(token), Token.Type.EOF);
        ^
  required: Token,Type,String
  found:    Token,Type
  reason: actual and formal argument lists differ in length
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:95: error: method assertToken in class Lexer_nextToken_Test cannot be applied to given types;
        assertToken(lexer.nextToken(token), Token.Type.EOF);
        ^
  required: Token,Type,String
  found:    Token,Type
  reason: actual and formal argument lists differ in length
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:103: error: method assertToken in class Lexer_nextToken_Test cannot be applied to given types;
        assertToken(lexer.nextToken(token), Token.Type.EOF);
        ^
  required: Token,Type,String
  found:    Token,Type
  reason: actual and formal argument lists differ in length
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:108: error: method assertToken in class Lexer_nextToken_Test cannot be applied to given types;
        assertToken(lexer.nextToken(token), Token.Type.EOF);
        ^
  required: Token,Type,String
  found:    Token,Type
  reason: actual and formal argument lists differ in length
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:119: error: method assertToken in class Lexer_nextToken_Test cannot be applied to given types;
        assertToken(lexer.nextToken(token), Token.Type.EOF);
        ^
  required: Token,Type,String
  found:    Token,Type
  reason: actual and formal argument lists differ in length
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:129: error: method assertToken in class Lexer_nextToken_Test cannot be applied to given types;
        assertToken(lexer.nextToken(token), Token.Type.EORECORD);
        ^
  required: Token,Type,String
  found:    Token,Type
  reason: actual and formal argument lists differ in length
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:133: error: incompatible types: boolean cannot be converted to String
        assertToken(lexer.nextToken(token), Token.Type.EOF, true);
                                                            ^
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:143: error: method assertToken in class Lexer_nextToken_Test cannot be applied to given types;
        assertToken(lexer.nextToken(token), Token.Type.EORECORD);
        ^
  required: Token,Type,String
  found:    Token,Type
  reason: actual and formal argument lists differ in length
Note: src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java uses or overrides a deprecated API.
Note: Recompile with -Xlint:deprecation for details.
Note: Some messages have been simplified; recompile with -Xdiags:verbose to get full output
8 errors

```

And you can use the following related information to assist you in your analysis and correction:
```
{'api document of class org.apache.commons.csv.Lexer': '\n * Lexical analyzer.\n ', 'api document of method nextToken(Token)': '\n     * Returns the next token.\n     * <p>\n     * A token corresponds to a term, a record change or an end-of-file indicator.\n     * </p>\n     *\n     * @param token\n     *            an existing Token object to reuse. The caller is responsible to initialize the Token.\n     * @return the next token found.\n     * @throws IOException on stream access error.\n     ', 'return type': 'org.apache.commons.csv.Token', 'dependent classes': 'class `org.apache.commons.csv.Token`:\n\tdependent fields:\n\t\tjava.lang.StringBuilder org.apache.commons.csv.Token.content;\n\t\torg.apache.commons.csv.Token.Type org.apache.commons.csv.Token.type;\n\t\tboolean org.apache.commons.csv.Token.isReady;\n\trelated functions:\n\t\tmethod `public String toString()` returns `java.lang.String`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`, api document: \n     * Eases IDE debugging.\n     *\n     * @return a string helpful for debugging.\n     ;\n\t\tmethod ` void reset()` returns `void`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`;\nclass `org.apache.commons.csv.Token.Type`:\n\tdependent fields:\n\t\torg.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.TOKEN;\n\t\torg.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.EOF;\n\t\torg.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.EORECORD;\n\t\torg.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.INVALID;\n\t\torg.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.COMMENT;\n\t\nclass `org.apache.commons.csv.Lexer`:\n\tdependent fields:\n\t\tboolean org.apache.commons.csv.Lexer.isLastTokenDelimiter;\n\t\tboolean org.apache.commons.csv.Lexer.ignoreEmptyLines;\n\t\tboolean org.apache.commons.csv.Lexer.ignoreSurroundingSpaces;\n\t\torg.apache.commons.csv.ExtendedBufferedReader org.apache.commons.csv.Lexer.reader;\n\trelated functions:\n\t\tmethod `private Token parseSimpleToken(Token, int)` returns `org.apache.commons.csv.Token`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`, api document: \n     * Parses a simple token.\n     * <p>\n     * Simple token are tokens which are not surrounded by encapsulators. A simple token might contain escaped\n     * delimiters (as \\, or \\;). The token is finished when one of the following conditions become true:\n     * </p>\n     * <ul>\n     * <li>end of line has been reached (EORECORD)</li>\n     * <li>end of stream has been reached (EOF)</li>\n     * <li>an unescaped delimiter has been reached (TOKEN)</li>\n     * </ul>\n     *\n     * @param token\n     *            the current token\n     * @param ch\n     *            the current character\n     * @return the filled token\n     * @throws IOException\n     *             on stream access error\n     ;\n\t\tmethod `private Token parseEncapsulatedToken(Token)` returns `org.apache.commons.csv.Token`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`, api document: \n     * Parses an encapsulated token.\n     * <p>\n     * Encapsulated tokens are surrounded by the given encapsulating-string. The encapsulator itself might be included\n     * in the token using a doubling syntax (as "", \'\') or using escaping (as in \\", \\\'). Whitespaces before and after\n     * an encapsulated token are ignored. The token is finished when one of the following conditions become true:\n     * </p>\n     * <ul>\n     * <li>an unescaped encapsulator has been reached, and is followed by optional whitespace then:</li>\n     * <ul>\n     * <li>delimiter (TOKEN)</li>\n     * <li>end of line (EORECORD)</li>\n     * </ul>\n     * <li>end of stream has been reached (EOF)</li> </ul>\n     *\n     * @param token\n     *            the current token\n     * @return a valid token object\n     * @throws IOException\n     *             on invalid state: EOF before closing encapsulator or invalid character before delimiter or EOL\n     ;\n\t\tmethod ` boolean isDelimiter(int)` returns `boolean`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`, api document: \n     * Determine whether the next characters constitute a delimiter through {@link ExtendedBufferedReader#lookAhead(char[])}.\n     *\n     * @param ch\n     *             the current character.\n     * @return true if the next characters constitute a delimiter.\n     * @throws IOException If an I/O error occurs.\n     ;\n\t\tmethod ` int readEscape()` returns `int`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`, api document: \n     * Handle an escape sequence.\n     * The current character must be the escape character.\n     * On return, the next character is available by calling {@link ExtendedBufferedReader#getLastChar()}\n     * on the input stream.\n     *\n     * @return the unescaped character (as an int) or {@link Constants#END_OF_STREAM} if char following the escape is\n     *      invalid.\n     * @throws IOException if there is a problem reading the stream or the end of stream is detected:\n     *      the escape character is not allowed at end of stream\n     ;\n\t\tmethod ` boolean readEndOfLine(int)` returns `boolean`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`, api document: \n     * Greedily accepts \\n, \\r and \\r\\n This checker consumes silently the second control-character...\n     *\n     * @return true if the given or next character is a line-terminator\n     ;\nclass `org.apache.commons.csv.CSVParser`:\n\trelated functions:\n\t\tmethod ` CSVRecord nextRecord()` returns `org.apache.commons.csv.CSVRecord`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`, api document: \n     * Parses the next record from the current point in the stream.\n     *\n     * @return the record as an array of values, or {@code null} if the end of the stream has been reached\n     * @throws IOException\n     *             on parse error or input read-failure\n     ;\nclass `org.apache.commons.csv.CSVFormat`:\n\trelated functions:\n\t\tmethod ` static boolean isBlank(String)` returns `boolean`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`;'}
```

**Note**: If the method-to-test in the @Test function is a private method, you can use **reflection** to call it.

Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 


