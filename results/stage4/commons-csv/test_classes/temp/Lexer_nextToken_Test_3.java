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
        token = new Token();
    }

    @AfterEach
    void teardownAfterEach() {
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
        assertToken(lexer.nextToken(token), Token.Type.TOKEN, "b\\");
        assertToken(lexer.nextToken(token), Token.Type.TOKEN, ",");
        assertToken(lexer.nextToken(token), Token.Type.TOKEN, "\nc");
        assertToken(lexer.nextToken(token), Token.Type.EORECORD, "d");
        assertToken(lexer.nextToken(token), Token.Type.EOF, "e");
    }

    @Test
    void testNextTokenWithoutEscaping() throws IOException {
        final String code = "a,\\,,b\\\n\\,,";
        lexer = new Lexer(CSVFormat.DEFAULT, new ExtendedBufferedReader(new StringReader(code)));
        assertToken(lexer.nextToken(token), Token.Type.TOKEN, "a");
        assertToken(lexer.nextToken(token), Token.Type.TOKEN, "\\");
        assertToken(lexer.nextToken(token), Token.Type.TOKEN, "");
        assertToken(lexer.nextToken(token), Token.Type.EORECORD, "b");
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
        lexer = new Lexer(CSVFormat.DEFAULT.withIgnoreEmptyLines(true), new ExtendedBufferedReader(new StringReader("\n\n")));
        assertToken(lexer.nextToken(token), Token.Type.EOF, "");
        lexer = new Lexer(CSVFormat.DEFAULT.withIgnoreEmptyLines(false), new ExtendedBufferedReader(new StringReader("\n\n")));
        assertToken(lexer.nextToken(token), Token.Type.EORECORD, "");
    }

    // Test cases for end of file handling
    @Test
    void testNextTokenEndOfFileHandling() throws IOException {
        lexer = new Lexer(CSVFormat.DEFAULT, new ExtendedBufferedReader(new StringReader("")));
        assertToken(lexer.nextToken(token), Token.Type.EOF, "");
        lexer = new Lexer(CSVFormat.DEFAULT, new ExtendedBufferedReader(new StringReader("a")));
        lexer.nextToken(token);
        assertToken(lexer.nextToken(token), Token.Type.EOF, "");
    }

    // Test cases for comment handling
    @Test
    void testNextTokenCommentHandling() throws IOException {
        lexer = new Lexer(CSVFormat.DEFAULT, new ExtendedBufferedReader(new StringReader("# This is a comment\n")));
        assertToken(lexer.nextToken(token), Token.Type.COMMENT, "This is a comment");
        lexer = new Lexer(CSVFormat.DEFAULT, new ExtendedBufferedReader(new StringReader("# Comment\n\n")));
        assertToken(lexer.nextToken(token), Token.Type.EOF, "");
    }

    // Test cases for token parsing
    @Test
    void testNextTokenParsing() throws IOException {
        lexer = new Lexer(CSVFormat.DEFAULT, new ExtendedBufferedReader(new StringReader("sampleToken,,")));
        assertToken(lexer.nextToken(token), Token.Type.TOKEN, "sampleToken");
        assertToken(lexer.nextToken(token), Token.Type.EORECORD, "");
        lexer = new Lexer(CSVFormat.DEFAULT, new ExtendedBufferedReader(new StringReader("sampleToken")));
        assertToken(lexer.nextToken(token), Token.Type.TOKEN, "sampleToken");
        assertToken(lexer.nextToken(token), Token.Type.EOF, "");
    }

    // Test cases for whitespace handling
    @Test
    void testNextTokenWhitespaceHandling() throws IOException {
        lexer = new Lexer(CSVFormat.DEFAULT.withIgnoreSurroundingSpaces(), new ExtendedBufferedReader(new StringReader("  trimmedToken  ,  ")));
        assertToken(lexer.nextToken(token), Token.Type.TOKEN, "trimmedToken");
        assertToken(lexer.nextToken(token), Token.Type.EORECORD, "");
    }
}
