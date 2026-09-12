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

    @BeforeAll
    static void setupBeforeAll() {
        // Initialize any static resources if necessary
    }

    @BeforeEach
    void setupBeforeEach() {
        csvFormat = CSVFormat.DEFAULT.withIgnoreEmptyLines(false);
        extendedBufferedReader = mock(ExtendedBufferedReader.class);
        lexer = new Lexer(csvFormat, extendedBufferedReader);
        token = new Token();
    }

    @AfterEach
    void teardownAfterEach() {
        // Clean up resources if necessary
    }

    @AfterAll
    static void teardownAfterAll() {
        // Clean up any static resources if necessary
    }

    @Test
    void testNextTokenWithEOF() throws IOException {
        when(extendedBufferedReader.read()).thenReturn(-1); // Simulate EOF
        when(extendedBufferedReader.getLastChar()).thenReturn(-1);

        Token result = lexer.nextToken(token);

        assertEquals(Token.Type.EOF, result.type);
    }

    @Test
    void testNextTokenWithComment() throws IOException {
        when(extendedBufferedReader.read()).thenReturn('#', 'c', 'o', 'm', 'm', 'e', 'n', 't', '\n');
        when(extendedBufferedReader.getLastChar()).thenReturn('\n');
        when(extendedBufferedReader.readLine()).thenReturn("comment");

        Token result = lexer.nextToken(token);

        assertEquals(Token.Type.COMMENT, result.type);
        assertEquals("comment", result.content.toString());
    }

    // Add more tests to cover other scenarios
}
```