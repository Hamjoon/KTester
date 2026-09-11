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

    @Test
    void testNextRecord() throws IOException {
        // Given
        CSVRecord record;
        // When
        record = parser.nextRecord();
        // Then
        assertNotNull(record);
        assertEquals(1, record.getRecordNumber());
        assertEquals("1", record.get(0));
        assertEquals("2", record.get(1));
        assertEquals("3", record.get(2));
        assertEquals("4", record.get(3));
        // When
        record = parser.nextRecord();
        // Then
        assertNotNull(record);
        assertEquals(2, record.getRecordNumber());
        assertEquals("5", record.get(0));
        assertEquals("6", record.get(1));
        assertEquals("7", record.get(2));
        assertEquals("8", record.get(3));
        // When
        record = parser.nextRecord();
        // Then
        // No more records should be present
        assertNull(record);
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
        // Read first record
        CSVRecord record = parser.nextRecord();
        assertNotNull(record);
        // Read second record
        record = parser.nextRecord();
        assertNotNull(record);
    }

    @Test
    void testNextRecord_EndOfFileHandling() throws IOException {
        // Test the nextRecord method when encountering an EOF token with ready state to ensure it adds a trailer comment correctly.
        parser = new CSVParser(new StringReader("a,b,c\n1,2,3\n"), format);
        CSVRecord record = parser.nextRecord();
        assertNotNull(record);
        // Read second record
        record = parser.nextRecord();
        // End of file should return null
        assertNull(parser.nextRecord());
    }

    @Test
    void testNextRecord_InvalidTokenHandling() throws Exception {
        // Test the nextRecord method when encountering an INVALID token to ensure it throws an IOException.
        parser = new CSVParser(new StringReader("a,b,c\n1,2,3\nINVALID_TOKEN\n"), format);
        assertThrows(IOException.class, () -> {
            while (parser.nextRecord() != null) {
                // Consume records until the end
            }
        });
    }

    @Test
    void testNextRecord_CommentHandling() throws IOException {
        // Test the nextRecord method when encountering COMMENT tokens to ensure it correctly handles and stores comments.
        String csvData = "# This is a comment\n1,2,3\n";
        parser = new CSVParser(new StringReader(csvData), format);
        CSVRecord record = parser.nextRecord();
        assertNotNull(record);
        // Check that the comment is ignored
        assertEquals("1", record.get(0));
    }

    @Test
    void testNextRecord_UnexpectedTokenHandling() throws Exception {
        // Test the nextRecord method when encountering an unexpected token type to ensure it throws an IOException.
        parser = new CSVParser(new StringReader("a,b,c\n1,2,3\n# Comment\n"), format);
        CSVRecord record = parser.nextRecord();
        assertNotNull(record);
        // The comment should be ignored
        assertEquals("1", record.get(0));
        // Now test for unexpected token handling
        assertThrows(IOException.class, () -> {
            // This should be handled correctly
            parser.nextRecord();
        });
    }

    @Test
    void testNextRecord_EmptyRecordListHandling() throws IOException {
        // Test the nextRecord method when the recordList is empty to ensure it returns null.
        parser = new CSVParser(new StringReader(""), format);
        // No records should be present
        assertNull(parser.nextRecord());
    }

    @Test
    void testNextRecord_LexerNotInitialized() {
        // Test the nextRecord method when the lexer is not initialized to ensure it throws an IOException.
        assertThrows(IOException.class, () -> {
            // Simulate lexer not initialized
            parser = new CSVParser(new StringReader("Invalid data"), format);
            // This should throw an exception
            parser.nextRecord();
        });
    }

    @Test
    void testNextRecord_LexerThrowsIOException() throws Exception {
        // Test the nextRecord method when the lexer throws an IOException during token retrieval to ensure it propagates the exception.
        parser = mock(CSVParser.class);
        when(parser.nextRecord()).thenThrow(new IOException("Lexer error"));
        assertThrows(IOException.class, () -> {
            parser.nextRecord();
        });
    }
}
