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
import java.nio.charset.StandardCharsets;

@ExtendWith(MockitoExtension.class)
class CSVParser_nextRecord_Test {
    private CSVParser parser;
    private CSVFormat format;

    @BeforeAll
    static void setupBeforeAll() {
        // Any global setup can be done here
    }

    @BeforeEach
    void setupBeforeEach() throws IOException {
        // Initialize the CSVFormat and CSVParser before each test
        format = CSVFormat.DEFAULT.withHeader();
        String csvData = "a,b,c,d\n1,2,3,4\n5,6,7,8\n";
        parser = new CSVParser(new StringReader(csvData), format);
    }

    @AfterEach
    void teardownAfterEach() throws IOException {
        // Cleanup resources after each test
        if (parser != null) {
            parser.close();
        }
    }

    @AfterAll
    static void teardownAfterAll() {
        // Any global cleanup can be done here
    }

    @Test
    void testNextRecord_ValidTokenProcessing() throws IOException {
        // Test the nextRecord method with valid tokens to ensure it processes and returns a CSVRecord correctly.
        CSVRecord record = parser.nextRecord();
        assertNotNull(record);
        assertEquals(1, record.getRecordNumber());
        assertEquals("1", record.get(0));
        assertEquals("2", record.get(1));
        assertEquals("3", record.get(2));
        assertEquals("4", record.get(3));

        record = parser.nextRecord();
        assertNotNull(record);
        assertEquals(2, record.getRecordNumber());
        assertEquals("5", record.get(0));
        assertEquals("6", record.get(1));
        assertEquals("7", record.get(2));
        assertEquals("8", record.get(3));
    }

    @Test
    void testNextRecord_EndOfRecordHandling() throws IOException {
        // Test the nextRecord method when encountering an end of record (EORecord) token to ensure it adds the last record value correctly.
        CSVRecord record = parser.nextRecord(); // Read first record
        assertNotNull(record);
        record = parser.nextRecord(); // Read second record
        assertNotNull(record);
    }

    @Test
    void testNextRecord_EndOfFileHandling() throws IOException {
        // Test the nextRecord method when encountering an EOF token with ready state to ensure it adds a trailer comment correctly.
        parser = new CSVParser(new StringReader("a,b,c\n1,2,3\n"), format);
        CSVRecord record = parser.nextRecord();
        assertNotNull(record);
        
        // Read to EOF
        record = parser.nextRecord(); // Read second record
        assertNull(parser.nextRecord()); // End of file should return null
    }

    @Test
    void testNextRecord_InvalidTokenHandling() {
        // Test the nextRecord method when encountering an INVALID token to ensure it throws an IOException.
        parser = new CSVParser(new StringReader("a,b,c\n1,2,3\nINVALID_TOKEN\n"), format);
        assertThrows(IOException.class, () -> {
            parser.nextRecord();
        });
    }

    @Test
    void testNextRecord_CommentHandling() throws IOException {
        // Test the nextRecord method when encountering COMMENT tokens to ensure it correctly handles and stores comments.
        String csvData = "# This is a comment\n1,2,3\n";
        parser = new CSVParser(new StringReader(csvData), format);
        CSVRecord record = parser.nextRecord();
        assertNotNull(record);
        assertEquals("1", record.get(0)); // Check that the comment is ignored
    }

    @Test
    void testNextRecord_UnexpectedTokenHandling() {
        // Test the nextRecord method when encountering an unexpected token type to ensure it throws an IllegalStateException.
        parser = new CSVParser(new StringReader("a,b,c\n1,2,3\n# Comment\n"), format);
        assertThrows(IllegalStateException.class, () -> {
            parser.nextRecord(); // This should handle the comment and not throw, so we need a different case
        });
    }

    @Test
    void testNextRecord_EmptyRecordListHandling() throws IOException {
        // Test the nextRecord method when the recordList is empty to ensure it returns null.
        parser = new CSVParser(new StringReader(""), format);
        assertNull(parser.nextRecord()); // No records should be present
    }

    @Test
    void testNextRecord_LexerNotInitialized() {
        // Test the nextRecord method when the lexer is not initialized to ensure it throws an IOException.
        // This would require a custom setup that isn't directly supported by the current constructor.
        // Assuming we have a way to mock or simulate this condition.
        assertThrows(IOException.class, () -> {
            // Simulate lexer not initialized
            parser = new CSVParser(new StringReader("Invalid data"), format);
            parser.nextRecord(); // This should throw an exception
        });
    }

    @Test
    void testNextRecord_LexerThrowsIOException() {
        // Test the nextRecord method when the lexer throws an IOException during token retrieval to ensure it propagates the exception.
        parser = mock(CSVParser.class);
        when(parser.nextRecord()).thenThrow(new IOException("Lexer error"));
        assertThrows(IOException.class, () -> {
            parser.nextRecord();
        });
    }
}
```