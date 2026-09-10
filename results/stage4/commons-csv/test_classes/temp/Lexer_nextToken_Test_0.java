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

    // Test cases for nextToken method
    @Test
    void testNextTokenWithEscaping() throws IOException {
        final String code = "a,\\,,b\\\\\n\\,,\\\nc,d\\\r\ne";
        lexer = new Lexer(formatWithEscaping, new ExtendedBufferedReader(new StringReader(code)));
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "a"));
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, ","));
        assertThat(lexer.nextToken(token), matches(Token.Type.EORECORD, "b\\"));
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, ","));
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "\nc"));
        assertThat(lexer.nextToken(token), matches(Token.Type.EORECORD, "d\r"));
        assertThat(lexer.nextToken(token), matches(Token.Type.EOF, "e"));
    }

    @Test
    void testNextTokenWithoutEscaping() throws IOException {
        final String code = "a,\\,,b\\\n\\,,";
        lexer = new Lexer(CSVFormat.DEFAULT, new ExtendedBufferedReader(new StringReader(code)));
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "a"));
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "\\"));
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, ""));
        assertThat(lexer.nextToken(token), matches(Token.Type.EORECORD, "b\\"));
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "\\"));
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, ""));
        assertThat(lexer.nextToken(token), matches(Token.Type.EOF, ""));
    }

    @Test
    void testNextTokenWithSurroundingSpaces() throws IOException {
        final String code = " noSpaces ,  leadingSpaces , trailingSpaces  ,  surroundingSpaces  ,  ,, ";
        lexer = new Lexer(CSVFormat.DEFAULT.withIgnoreSurroundingSpaces(), new ExtendedBufferedReader(new StringReader(code)));
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "noSpaces"));
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "leadingSpaces"));
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "trailingSpaces"));
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "surroundingSpaces"));
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, ""));
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, ""));
        assertThat(lexer.nextToken(token), matches(Token.Type.EOF, ""));
    }

    // Test cases for empty lines handling
    @Test
    void testNextTokenEmptyLinesHandling() throws IOException {
        // Test when the input contains only empty lines and ignoreEmptyLines is true
        lexer = new Lexer(CSVFormat.DEFAULT.withIgnoreEmptyLines(true), new ExtendedBufferedReader(new StringReader("\n\n")));
        assertThat(lexer.nextToken(token), matches(Token.Type.EOF));
        // Test when the input contains only empty lines and ignoreEmptyLines is false
        lexer = new Lexer(CSVFormat.DEFAULT.withIgnoreEmptyLines(false), new ExtendedBufferedReader(new StringReader("\n\n")));
        assertThat(lexer.nextToken(token), matches(Token.Type.EOF));
    }

    // Test cases for end of file handling
    @Test
    void testNextTokenEndOfFileHandling() throws IOException {
        // Test when the last read character is EOF
        lexer = new Lexer(CSVFormat.DEFAULT, new ExtendedBufferedReader(new StringReader("")));
        assertThat(lexer.nextToken(token), matches(Token.Type.EOF));
        // Test when the current character read is EOF
        lexer = new Lexer(CSVFormat.DEFAULT, new ExtendedBufferedReader(new StringReader("a")));
        // Consume 'a'
        lexer.nextToken(token);
        assertThat(lexer.nextToken(token), matches(Token.Type.EOF));
    }

    // Test cases for comment handling
    @Test
    void testNextTokenCommentHandling() throws IOException {
        // Test when the current character is a comment start and the line has a comment
        lexer = new Lexer(CSVFormat.DEFAULT, new ExtendedBufferedReader(new StringReader("# This is a comment\n")));
        assertThat(lexer.nextToken(token), matches(Token.Type.COMMENT, "This is a comment"));
        // Test when the line is empty after a comment
        lexer = new Lexer(CSVFormat.DEFAULT, new ExtendedBufferedReader(new StringReader("# Comment\n\n")));
        assertThat(lexer.nextToken(token), matches(Token.Type.EOF));
    }

    // Test cases for token parsing
    @Test
    void testNextTokenParsing() throws IOException {
        // Test when a valid token is read
        lexer = new Lexer(CSVFormat.DEFAULT, new ExtendedBufferedReader(new StringReader("sampleToken,,")));
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "sampleToken"));
        // Test when an end of record is reached
        assertThat(lexer.nextToken(token), matches(Token.Type.EORECORD));
        // Test when EOF is reached with valid content
        lexer = new Lexer(CSVFormat.DEFAULT, new ExtendedBufferedReader(new StringReader("sampleToken")));
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "sampleToken"));
        assertThat(lexer.nextToken(token), matches(Token.Type.EOF, true));
    }

    // Test cases for whitespace handling
    @Test
    void testNextTokenWhitespaceHandling() throws IOException {
        // Test when surrounding spaces are ignored and a valid token is read
        lexer = new Lexer(CSVFormat.DEFAULT.withIgnoreSurroundingSpaces(), new ExtendedBufferedReader(new StringReader("  trimmedToken  ,  ")));
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "trimmedToken"));
        // Test when only whitespace is encountered and eol is true
        assertThat(lexer.nextToken(token), matches(Token.Type.EORECORD));
    }
}
