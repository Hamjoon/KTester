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
class CSVParser_nextRecord_Test {

    private CSVParser parser;

    private CSVFormat format;

    @BeforeEach
    void setupBeforeEach() throws IOException {
        // Initialize the CSVFormat and CSVParser before each test
        format = CSVFormat.DEFAULT;
        parser = new CSVParser(new StringReader("a,b,c\n1,2,3"), format);
    }

    @AfterEach
    void teardownAfterEach() throws IOException {
        // Clean up resources after each test
        if (parser != null) {
            parser.close();
        }
    }

    // Helper method to set private fields via reflection
    private void setField(Object target, String fieldName, Object value) {
        try {
            java.lang.reflect.Field field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testNextRecord() throws IOException {
        // Given
        assertNotNull(parser);
        // When
        CSVRecord record1 = parser.nextRecord();
        CSVRecord record2 = parser.nextRecord();
        CSVRecord record3 = parser.nextRecord();
        // Then
        assertNotNull(record1);
        assertEquals("a", record1.get(0));
        assertEquals("b", record1.get(1));
        assertEquals("c", record1.get(2));
        assertNotNull(record2);
        assertEquals("1", record2.get(0));
        assertEquals("2", record2.get(1));
        assertEquals("3", record2.get(2));
        assertNull(record3);
    }

    @Test
    void testNextRecordWithIOException() throws Exception {
        // Given
        Lexer lexerMock = mock(Lexer.class);
        when(lexerMock.nextToken(any(Token.class))).thenAnswer(invocation -> {
            Token token = invocation.getArgument(0);
            token.type = Token.Type.INVALID;
            return token;
        });
        CSVParser parserWithMockedLexer = new CSVParser(new StringReader("a,b,c\n1,2,3"), format);
        setField(parserWithMockedLexer, "lexer", lexerMock);
        // When & Then
        assertThrows(IOException.class, parserWithMockedLexer::nextRecord);
    }

    @Test
    void testNextRecordWithEOFAndNoReadyToken() throws IOException {
        // Given
        CSVParser parserWithEOF = new CSVParser(new StringReader(""), format);
        // When
        CSVRecord record = parserWithEOF.nextRecord();
        // Then
        assertNull(record);
    }

    @Test
    void testNextRecordWithCommentFollowedByValidRecord() throws IOException {
        // Given
        format = CSVFormat.DEFAULT.withCommentMarker('#');
        CSVParser parserWithComment = new CSVParser(new StringReader("# This is a comment\na,b,c\n1,2,3"), format);
        // When
        CSVRecord record = parserWithComment.nextRecord();
        // Then
        assertNotNull(record);
        assertEquals("a", record.get(0));
        assertEquals("b", record.get(1));
        assertEquals("c", record.get(2));
    }

    @Test
    void testNextRecordWithUnexpectedToken() throws Exception {
        // Given
        Lexer lexerMock = mock(Lexer.class);
        when(lexerMock.nextToken(any(Token.class))).thenAnswer(invocation -> {
            Token token = invocation.getArgument(0);
            // Simulate unexpected token type
            token.type = Token.Type.INVALID;
            return token;
        });
        CSVParser parserWithMockedLexer = new CSVParser(new StringReader("a,b,c\n1,2,3"), format);
        setField(parserWithMockedLexer, "lexer", lexerMock);
        // When & Then
        assertThrows(IOException.class, parserWithMockedLexer::nextRecord);
    }

    @Test
    void testNextRecordWithEORECORDToken() throws IOException {
        // Given
        Lexer lexerMock = mock(Lexer.class);
        when(lexerMock.nextToken(any(Token.class))).thenAnswer(invocation -> {
            Token token = invocation.getArgument(0);
            token.type = Token.Type.EORECORD;
            return token;
        });
        CSVParser parserWithMockedLexer = new CSVParser(new StringReader("a,b,c\n1,2,3"), format);
        setField(parserWithMockedLexer, "lexer", lexerMock);
        // When
        CSVRecord record = parserWithMockedLexer.nextRecord();
        // Then
        assertNotNull(record);
    }

    @Test
    void testNextRecordWithEOFAndReadyToken() throws IOException {
        // Given
        Lexer lexerMock = mock(Lexer.class);
        when(lexerMock.nextToken(any(Token.class))).thenAnswer(invocation -> {
            Token token = invocation.getArgument(0);
            token.type = Token.Type.EOF;
            token.isReady = true;
            return token;
        });
        CSVParser parserWithMockedLexer = new CSVParser(new StringReader("a,b,c\n1,2,3"), format);
        setField(parserWithMockedLexer, "lexer", lexerMock);
        // When
        CSVRecord record = parserWithMockedLexer.nextRecord();
        // Then
        assertNotNull(record);
    }
}
```

In the `testNextRecordWithUnexpectedToken` method, the expected exception type was changed from `IllegalStateException` to `IOException` to match the actual behavior of the `CSVParser.nextRecord()` method when encountering an `INVALID` token type. This aligns with the feedback indicating that an `IOException` is thrown in this scenario.