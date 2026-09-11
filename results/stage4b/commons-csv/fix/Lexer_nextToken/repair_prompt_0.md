
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

    private Lexer lexer;

    private CSVFormat format;

    private ExtendedBufferedReader reader;

    private Token token;

    @BeforeEach
    void setupBeforeEach() {
        // Setup the CSVFormat and ExtendedBufferedReader
        format = CSVFormat.DEFAULT.withEscape('\\');
        token = new Token();
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup after each test if necessary
        if (lexer != null) {
            try {
                lexer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void createLexer(final String input) {
        reader = new ExtendedBufferedReader(new StringReader(input));
        lexer = new Lexer(format, reader);
    }

    @Test
    void testNextTokenWithValidInput() throws IOException {
        createLexer("a,b,c");
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "a"));
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "b"));
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "c"));
        assertThat(lexer.nextToken(token), matches(Token.Type.EOF, ""));
    }

    @Test
    void testNextTokenWithEmptyInput() throws IOException {
        createLexer("");
        assertThat(lexer.nextToken(token), matches(Token.Type.EOF, ""));
    }

    @Test
    void testNextTokenWithComment() throws IOException {
        createLexer("# This is a comment\nvalue1,value2");
        assertThat(lexer.nextToken(token), matches(Token.Type.COMMENT, "This is a comment"));
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "value1"));
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "value2"));
        assertThat(lexer.nextToken(token), matches(Token.Type.EOF, ""));
    }

    @Test
    void testNextTokenWithEscapedCharacters() throws IOException {
        createLexer("a,\\,b");
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "a"));
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, ","));
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "b"));
        assertThat(lexer.nextToken(token), matches(Token.Type.EOF, ""));
    }

    @Test
    void testNextTokenHandlingEmptyLines() throws IOException {
        // Test when ignoreEmptyLines is true and the input contains only empty lines
        createLexer("\n\n\n");
        // Set the ignoreEmptyLines flag
        lexer.ignoreEmptyLines = true;
        assertThat(lexer.nextToken(token), matches(Token.Type.EOF, ""));
        // Test when ignoreEmptyLines is false and the input contains only empty lines
        createLexer("\n\n\n");
        // Set the ignoreEmptyLines flag
        lexer.ignoreEmptyLines = false;
        assertThat(lexer.nextToken(token), matches(Token.Type.EOF, ""));
    }

    @Test
    void testNextTokenEndOfFileHandling() throws IOException {
        // Test when the last character read is EOF
        createLexer("a,b,c");
        // Read tokens until EOF
        lexer.nextToken(token);
        lexer.nextToken(token);
        lexer.nextToken(token);
        assertThat(lexer.nextToken(token), matches(Token.Type.EOF, ""));
        // Test when the next character read is EOF
        createLexer("a,b,c");
        // Read first token
        lexer.nextToken(token);
        // Read second token
        lexer.nextToken(token);
        // Read third token
        lexer.nextToken(token);
        // Read EOF
        lexer.nextToken(token);
        assertThat(lexer.nextToken(token), matches(Token.Type.EOF, ""));
    }

    @Test
    void testNextTokenCommentHandling() throws IOException {
        // Test when the character read indicates a comment start
        createLexer("# This is a comment\nvalue1,value2");
        assertThat(lexer.nextToken(token), matches(Token.Type.COMMENT, "This is a comment"));
    }

    @Test
    void testNextTokenTokenParsing() throws IOException {
        // Test when the character read is a simple token
        createLexer("simpleToken,");
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "simpleToken"));
        // Test when the character read indicates end of record
        createLexer("value1,value2\n");
        assertThat(lexer.nextToken(token), matches(Token.Type.EORECORD, ""));
        // Test when the character read indicates the start of an encapsulated token
        createLexer("\"encapsulatedToken\",value");
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "encapsulatedToken"));
    }

    @Test
    void testNextTokenWhitespaceHandling() throws IOException {
        // Test when ignoreSurroundingSpaces is true and there are leading whitespaces
        createLexer("   tokenWithSpaces");
        // Set the ignoreSurroundingSpaces flag
        lexer.ignoreSurroundingSpaces = true;
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "tokenWithSpaces"));
        // Test when ignoreSurroundingSpaces is false and there are leading whitespaces
        createLexer("   tokenWithSpaces");
        // Set the ignoreSurroundingSpaces flag
        lexer.ignoreSurroundingSpaces = false;
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "   tokenWithSpaces"));
    }

    @Test
    void testNextTokenHandlingMixedContent() throws IOException {
        // Test when the input contains mixed content including tokens and comments
        createLexer("# This is a comment\nmixedContent,value");
        assertThat(lexer.nextToken(token), matches(Token.Type.COMMENT, "This is a comment"));
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "mixedContent"));
    }

    @Test
    void testNextTokenHandlingCommentsWithTrailingSpaces() throws IOException {
        // Test when the character read indicates a comment start with trailing spaces
        createLexer("# Trailing comment    \nvalue");
        assertThat(lexer.nextToken(token), matches(Token.Type.COMMENT, "Trailing comment    "));
    }

    @Test
    void testNextTokenEndOfFileWithContent() throws IOException {
        // Test when the last character read is EOF with content before it
        createLexer("content");
        // Read token
        lexer.nextToken(token);
        assertThat(lexer.nextToken(token), matches(Token.Type.EOF, ""));
    }

    @Test
    void testNextTokenHandlingEscapedCharacters() throws IOException {
        // Test when the character read indicates an escaped character
        createLexer("escaped\\nToken");
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "escapedToken"));
    }

    @Test
    void testNextTokenHandlingMixedLineEndings() throws IOException {
        // Test when the input contains mixed line endings (CR, LF)
        createLexer("line1\r\nline2\nline3");
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "line1"));
        assertThat(lexer.nextToken(token), matches(Token.Type.EORECORD, ""));
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "line2"));
        assertThat(lexer.nextToken(token), matches(Token.Type.EORECORD, ""));
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "line3"));
        assertThat(lexer.nextToken(token), matches(Token.Type.EOF, ""));
    }

    @Test
    void testNextTokenHandlingMultipleConsecutiveDelimiters() throws IOException {
        // Test when the input contains multiple consecutive delimiters
        createLexer(",,");
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, ""));
    }

    @Test
    void testNextTokenIOExceptionHandling() throws IOException {
        // Mock the ExtendedBufferedReader to throw IOException
        reader = mock(ExtendedBufferedReader.class);
        when(reader.read()).thenThrow(new IOException("Mocked IOException"));
        lexer = new Lexer(format, reader);
        // Test when an IOException is thrown by the reader during character reading
        assertThrows(IOException.class, () -> lexer.nextToken(token));
    }

    @Test
    void testNextTokenInvalidTokenHandling() throws IOException {
        // Test when the input character is invalid and should be handled as an INVALID token
        createLexer("invalidChar");
        // Assuming the first character is invalid
        assertThat(lexer.nextToken(token), matches(Token.Type.INVALID, ""));
    }
}
```

Corresponding compilation feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:50: error: no suitable method found for matches(Type,String)
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "a"));
                                           ^
    method Mockito.matches(Pattern) is not applicable
      (actual and formal argument lists differ in length)
    method Mockito.matches(String) is not applicable
      (actual and formal argument lists differ in length)
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:51: error: no suitable method found for matches(Type,String)
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "b"));
                                           ^
    method Mockito.matches(Pattern) is not applicable
      (actual and formal argument lists differ in length)
    method Mockito.matches(String) is not applicable
      (actual and formal argument lists differ in length)
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:52: error: no suitable method found for matches(Type,String)
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "c"));
                                           ^
    method Mockito.matches(Pattern) is not applicable
      (actual and formal argument lists differ in length)
    method Mockito.matches(String) is not applicable
      (actual and formal argument lists differ in length)
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:53: error: no suitable method found for matches(Type,String)
        assertThat(lexer.nextToken(token), matches(Token.Type.EOF, ""));
                                           ^
    method Mockito.matches(Pattern) is not applicable
      (actual and formal argument lists differ in length)
    method Mockito.matches(String) is not applicable
      (actual and formal argument lists differ in length)
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:59: error: no suitable method found for matches(Type,String)
        assertThat(lexer.nextToken(token), matches(Token.Type.EOF, ""));
                                           ^
    method Mockito.matches(Pattern) is not applicable
      (actual and formal argument lists differ in length)
    method Mockito.matches(String) is not applicable
      (actual and formal argument lists differ in length)
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:65: error: no suitable method found for matches(Type,String)
        assertThat(lexer.nextToken(token), matches(Token.Type.COMMENT, "This is a comment"));
                                           ^
    method Mockito.matches(Pattern) is not applicable
      (actual and formal argument lists differ in length)
    method Mockito.matches(String) is not applicable
      (actual and formal argument lists differ in length)
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:66: error: no suitable method found for matches(Type,String)
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "value1"));
                                           ^
    method Mockito.matches(Pattern) is not applicable
      (actual and formal argument lists differ in length)
    method Mockito.matches(String) is not applicable
      (actual and formal argument lists differ in length)
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:67: error: no suitable method found for matches(Type,String)
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "value2"));
                                           ^
    method Mockito.matches(Pattern) is not applicable
      (actual and formal argument lists differ in length)
    method Mockito.matches(String) is not applicable
      (actual and formal argument lists differ in length)
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:68: error: no suitable method found for matches(Type,String)
        assertThat(lexer.nextToken(token), matches(Token.Type.EOF, ""));
                                           ^
    method Mockito.matches(Pattern) is not applicable
      (actual and formal argument lists differ in length)
    method Mockito.matches(String) is not applicable
      (actual and formal argument lists differ in length)
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:74: error: no suitable method found for matches(Type,String)
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "a"));
                                           ^
    method Mockito.matches(Pattern) is not applicable
      (actual and formal argument lists differ in length)
    method Mockito.matches(String) is not applicable
      (actual and formal argument lists differ in length)
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:75: error: no suitable method found for matches(Type,String)
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, ","));
                                           ^
    method Mockito.matches(Pattern) is not applicable
      (actual and formal argument lists differ in length)
    method Mockito.matches(String) is not applicable
      (actual and formal argument lists differ in length)
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:76: error: no suitable method found for matches(Type,String)
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "b"));
                                           ^
    method Mockito.matches(Pattern) is not applicable
      (actual and formal argument lists differ in length)
    method Mockito.matches(String) is not applicable
      (actual and formal argument lists differ in length)
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:77: error: no suitable method found for matches(Type,String)
        assertThat(lexer.nextToken(token), matches(Token.Type.EOF, ""));
                                           ^
    method Mockito.matches(Pattern) is not applicable
      (actual and formal argument lists differ in length)
    method Mockito.matches(String) is not applicable
      (actual and formal argument lists differ in length)
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:85: error: ignoreEmptyLines has private access in Lexer
        lexer.ignoreEmptyLines = true;
             ^
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:86: error: no suitable method found for matches(Type,String)
        assertThat(lexer.nextToken(token), matches(Token.Type.EOF, ""));
                                           ^
    method Mockito.matches(Pattern) is not applicable
      (actual and formal argument lists differ in length)
    method Mockito.matches(String) is not applicable
      (actual and formal argument lists differ in length)
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:90: error: ignoreEmptyLines has private access in Lexer
        lexer.ignoreEmptyLines = false;
             ^
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:91: error: no suitable method found for matches(Type,String)
        assertThat(lexer.nextToken(token), matches(Token.Type.EOF, ""));
                                           ^
    method Mockito.matches(Pattern) is not applicable
      (actual and formal argument lists differ in length)
    method Mockito.matches(String) is not applicable
      (actual and formal argument lists differ in length)
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:102: error: no suitable method found for matches(Type,String)
        assertThat(lexer.nextToken(token), matches(Token.Type.EOF, ""));
                                           ^
    method Mockito.matches(Pattern) is not applicable
      (actual and formal argument lists differ in length)
    method Mockito.matches(String) is not applicable
      (actual and formal argument lists differ in length)
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:113: error: no suitable method found for matches(Type,String)
        assertThat(lexer.nextToken(token), matches(Token.Type.EOF, ""));
                                           ^
    method Mockito.matches(Pattern) is not applicable
      (actual and formal argument lists differ in length)
    method Mockito.matches(String) is not applicable
      (actual and formal argument lists differ in length)
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:120: error: no suitable method found for matches(Type,String)
        assertThat(lexer.nextToken(token), matches(Token.Type.COMMENT, "This is a comment"));
                                           ^
    method Mockito.matches(Pattern) is not applicable
      (actual and formal argument lists differ in length)
    method Mockito.matches(String) is not applicable
      (actual and formal argument lists differ in length)
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:127: error: no suitable method found for matches(Type,String)
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "simpleToken"));
                                           ^
    method Mockito.matches(Pattern) is not applicable
      (actual and formal argument lists differ in length)
    method Mockito.matches(String) is not applicable
      (actual and formal argument lists differ in length)
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:130: error: no suitable method found for matches(Type,String)
        assertThat(lexer.nextToken(token), matches(Token.Type.EORECORD, ""));
                                           ^
    method Mockito.matches(Pattern) is not applicable
      (actual and formal argument lists differ in length)
    method Mockito.matches(String) is not applicable
      (actual and formal argument lists differ in length)
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:133: error: no suitable method found for matches(Type,String)
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "encapsulatedToken"));
                                           ^
    method Mockito.matches(Pattern) is not applicable
      (actual and formal argument lists differ in length)
    method Mockito.matches(String) is not applicable
      (actual and formal argument lists differ in length)
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:141: error: ignoreSurroundingSpaces has private access in Lexer
        lexer.ignoreSurroundingSpaces = true;
             ^
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:142: error: no suitable method found for matches(Type,String)
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "tokenWithSpaces"));
                                           ^
    method Mockito.matches(Pattern) is not applicable
      (actual and formal argument lists differ in length)
    method Mockito.matches(String) is not applicable
      (actual and formal argument lists differ in length)
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:146: error: ignoreSurroundingSpaces has private access in Lexer
        lexer.ignoreSurroundingSpaces = false;
             ^
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:147: error: no suitable method found for matches(Type,String)
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "   tokenWithSpaces"));
                                           ^
    method Mockito.matches(Pattern) is not applicable
      (actual and formal argument lists differ in length)
    method Mockito.matches(String) is not applicable
      (actual and formal argument lists differ in length)
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:154: error: no suitable method found for matches(Type,String)
        assertThat(lexer.nextToken(token), matches(Token.Type.COMMENT, "This is a comment"));
                                           ^
    method Mockito.matches(Pattern) is not applicable
      (actual and formal argument lists differ in length)
    method Mockito.matches(String) is not applicable
      (actual and formal argument lists differ in length)
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:155: error: no suitable method found for matches(Type,String)
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "mixedContent"));
                                           ^
    method Mockito.matches(Pattern) is not applicable
      (actual and formal argument lists differ in length)
    method Mockito.matches(String) is not applicable
      (actual and formal argument lists differ in length)
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:162: error: no suitable method found for matches(Type,String)
        assertThat(lexer.nextToken(token), matches(Token.Type.COMMENT, "Trailing comment    "));
                                           ^
    method Mockito.matches(Pattern) is not applicable
      (actual and formal argument lists differ in length)
    method Mockito.matches(String) is not applicable
      (actual and formal argument lists differ in length)
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:171: error: no suitable method found for matches(Type,String)
        assertThat(lexer.nextToken(token), matches(Token.Type.EOF, ""));
                                           ^
    method Mockito.matches(Pattern) is not applicable
      (actual and formal argument lists differ in length)
    method Mockito.matches(String) is not applicable
      (actual and formal argument lists differ in length)
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:178: error: no suitable method found for matches(Type,String)
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "escapedToken"));
                                           ^
    method Mockito.matches(Pattern) is not applicable
      (actual and formal argument lists differ in length)
    method Mockito.matches(String) is not applicable
      (actual and formal argument lists differ in length)
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:185: error: no suitable method found for matches(Type,String)
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "line1"));
                                           ^
    method Mockito.matches(Pattern) is not applicable
      (actual and formal argument lists differ in length)
    method Mockito.matches(String) is not applicable
      (actual and formal argument lists differ in length)
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:186: error: no suitable method found for matches(Type,String)
        assertThat(lexer.nextToken(token), matches(Token.Type.EORECORD, ""));
                                           ^
    method Mockito.matches(Pattern) is not applicable
      (actual and formal argument lists differ in length)
    method Mockito.matches(String) is not applicable
      (actual and formal argument lists differ in length)
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:187: error: no suitable method found for matches(Type,String)
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "line2"));
                                           ^
    method Mockito.matches(Pattern) is not applicable
      (actual and formal argument lists differ in length)
    method Mockito.matches(String) is not applicable
      (actual and formal argument lists differ in length)
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:188: error: no suitable method found for matches(Type,String)
        assertThat(lexer.nextToken(token), matches(Token.Type.EORECORD, ""));
                                           ^
    method Mockito.matches(Pattern) is not applicable
      (actual and formal argument lists differ in length)
    method Mockito.matches(String) is not applicable
      (actual and formal argument lists differ in length)
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:189: error: no suitable method found for matches(Type,String)
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "line3"));
                                           ^
    method Mockito.matches(Pattern) is not applicable
      (actual and formal argument lists differ in length)
    method Mockito.matches(String) is not applicable
      (actual and formal argument lists differ in length)
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:190: error: no suitable method found for matches(Type,String)
        assertThat(lexer.nextToken(token), matches(Token.Type.EOF, ""));
                                           ^
    method Mockito.matches(Pattern) is not applicable
      (actual and formal argument lists differ in length)
    method Mockito.matches(String) is not applicable
      (actual and formal argument lists differ in length)
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:197: error: no suitable method found for matches(Type,String)
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, ""));
                                           ^
    method Mockito.matches(Pattern) is not applicable
      (actual and formal argument lists differ in length)
    method Mockito.matches(String) is not applicable
      (actual and formal argument lists differ in length)
src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java:215: error: no suitable method found for matches(Type,String)
        assertThat(lexer.nextToken(token), matches(Token.Type.INVALID, ""));
                                           ^
    method Mockito.matches(Pattern) is not applicable
      (actual and formal argument lists differ in length)
    method Mockito.matches(String) is not applicable
      (actual and formal argument lists differ in length)
Note: src/test/java/org/apache/commons/csv/Lexer_nextToken_Test.java uses or overrides a deprecated API.
Note: Recompile with -Xlint:deprecation for details.
40 errors

```

And you can use the following related information to assist you in your analysis and correction:
```
{'api document of class org.apache.commons.csv.Lexer': '\n * Lexical analyzer.\n ', 'api document of method nextToken(Token)': '\n     * Returns the next token.\n     * <p>\n     * A token corresponds to a term, a record change or an end-of-file indicator.\n     * </p>\n     *\n     * @param token\n     *            an existing Token object to reuse. The caller is responsible to initialize the Token.\n     * @return the next token found.\n     * @throws IOException on stream access error.\n     ', 'return type': 'org.apache.commons.csv.Token', 'dependent classes': 'class `org.apache.commons.csv.Token`:\n\tdependent fields:\n\t\torg.apache.commons.csv.Token.Type org.apache.commons.csv.Token.type;\n\t\tjava.lang.StringBuilder org.apache.commons.csv.Token.content;\n\t\tboolean org.apache.commons.csv.Token.isReady;\n\trelated functions:\n\t\tmethod ` void reset()` returns `void`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`;\n\t\tmethod `public String toString()` returns `java.lang.String`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`, api document: \n     * Eases IDE debugging.\n     *\n     * @return a string helpful for debugging.\n     ;\nclass `org.apache.commons.csv.Token.Type`:\n\tdependent fields:\n\t\torg.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.INVALID;\n\t\torg.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.TOKEN;\n\t\torg.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.EORECORD;\n\t\torg.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.COMMENT;\n\t\torg.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.EOF;\n\t\nclass `org.apache.commons.csv.Lexer`:\n\tdependent fields:\n\t\tboolean org.apache.commons.csv.Lexer.isLastTokenDelimiter;\n\t\torg.apache.commons.csv.ExtendedBufferedReader org.apache.commons.csv.Lexer.reader;\n\t\tboolean org.apache.commons.csv.Lexer.ignoreSurroundingSpaces;\n\t\tboolean org.apache.commons.csv.Lexer.ignoreEmptyLines;\n\trelated functions:\n\t\tmethod ` int readEscape()` returns `int`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`, api document: \n     * Handle an escape sequence.\n     * The current character must be the escape character.\n     * On return, the next character is available by calling {@link ExtendedBufferedReader#getLastChar()}\n     * on the input stream.\n     *\n     * @return the unescaped character (as an int) or {@link Constants#END_OF_STREAM} if char following the escape is\n     *      invalid.\n     * @throws IOException if there is a problem reading the stream or the end of stream is detected:\n     *      the escape character is not allowed at end of stream\n     ;\n\t\tmethod `private Token parseEncapsulatedToken(Token)` returns `org.apache.commons.csv.Token`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`, api document: \n     * Parses an encapsulated token.\n     * <p>\n     * Encapsulated tokens are surrounded by the given encapsulating-string. The encapsulator itself might be included\n     * in the token using a doubling syntax (as "", \'\') or using escaping (as in \\", \\\'). Whitespaces before and after\n     * an encapsulated token are ignored. The token is finished when one of the following conditions become true:\n     * </p>\n     * <ul>\n     * <li>an unescaped encapsulator has been reached, and is followed by optional whitespace then:</li>\n     * <ul>\n     * <li>delimiter (TOKEN)</li>\n     * <li>end of line (EORECORD)</li>\n     * </ul>\n     * <li>end of stream has been reached (EOF)</li> </ul>\n     *\n     * @param token\n     *            the current token\n     * @return a valid token object\n     * @throws IOException\n     *             on invalid state: EOF before closing encapsulator or invalid character before delimiter or EOL\n     ;\n\t\tmethod `private Token parseSimpleToken(Token, int)` returns `org.apache.commons.csv.Token`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`, api document: \n     * Parses a simple token.\n     * <p>\n     * Simple token are tokens which are not surrounded by encapsulators. A simple token might contain escaped\n     * delimiters (as \\, or \\;). The token is finished when one of the following conditions become true:\n     * </p>\n     * <ul>\n     * <li>end of line has been reached (EORECORD)</li>\n     * <li>end of stream has been reached (EOF)</li>\n     * <li>an unescaped delimiter has been reached (TOKEN)</li>\n     * </ul>\n     *\n     * @param token\n     *            the current token\n     * @param ch\n     *            the current character\n     * @return the filled token\n     * @throws IOException\n     *             on stream access error\n     ;\n\t\tmethod ` boolean readEndOfLine(int)` returns `boolean`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`, api document: \n     * Greedily accepts \\n, \\r and \\r\\n This checker consumes silently the second control-character...\n     *\n     * @return true if the given or next character is a line-terminator\n     ;\n\t\tmethod ` boolean isDelimiter(int)` returns `boolean`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`, api document: \n     * Determine whether the next characters constitute a delimiter through {@link ExtendedBufferedReader#lookAhead(char[])}.\n     *\n     * @param ch\n     *             the current character.\n     * @return true if the next characters constitute a delimiter.\n     * @throws IOException If an I/O error occurs.\n     ;\nclass `org.apache.commons.csv.CSVParser`:\n\trelated functions:\n\t\tmethod ` CSVRecord nextRecord()` returns `org.apache.commons.csv.CSVRecord`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`, api document: \n     * Parses the next record from the current point in the stream.\n     *\n     * @return the record as an array of values, or {@code null} if the end of the stream has been reached\n     * @throws IOException\n     *             on parse error or input read-failure\n     ;\nclass `org.apache.commons.csv.CSVFormat`:\n\trelated functions:\n\t\tmethod ` static boolean isBlank(String)` returns `boolean`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`;'}
```

**Note**: If the method-to-test in the @Test function is a private method, you can use **reflection** to call it.

Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 


