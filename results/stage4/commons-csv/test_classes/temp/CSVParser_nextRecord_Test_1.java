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

    @BeforeEach
    void setupBeforeEach() throws IOException {
        // Initialize the CSVFormat and CSVParser before each test
        format = CSVFormat.DEFAULT.withHeader();
        // Sample CSV input
        String csvInput = "a,b,c\n1,2,3\n4,5,6";
        parser = new CSVParser(new StringReader(csvInput), format);
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
        // Given: A CSVParser initialized with sample CSV input
        CSVRecord record;
        // When: Calling nextRecord() to get the first record
        record = parser.nextRecord();
        // Then: Assert the values of the first record
        assertNotNull(record);
        assertEquals("1", record.get(0));
        assertEquals("2", record.get(1));
        assertEquals("3", record.get(2));
        // When: Calling nextRecord() to get the second record
        record = parser.nextRecord();
        // Then: Assert the values of the second record
        assertNotNull(record);
        assertEquals("4", record.get(0));
        assertEquals("5", record.get(1));
        assertEquals("6", record.get(2));
        // When: Calling nextRecord() again, expecting null as there are no more records
        record = parser.nextRecord();
        // Then: Assert that the record is null
        assertNull(record);
    }

    @Test
    void testNextRecord_ValidTokenParsing() throws IOException {
        // Test nextRecord when there are valid tokens present to ensure a CSVRecord is created with the correct values.
        CSVRecord record = parser.nextRecord();
        assertNotNull(record);
        assertEquals("1", record.get(0));
        assertEquals("2", record.get(1));
        assertEquals("3", record.get(2));
    }

    @Test
    void testNextRecord_EndOfRecordHandling() throws IOException {
        // Test nextRecord when an end of record (EORECORD) token is encountered to ensure the record is properly added.
        // First record
        CSVRecord record = parser.nextRecord();
        assertNotNull(record);
        // Second record
        record = parser.nextRecord();
        assertNotNull(record);
    }

    @Test
    void testNextRecord_EndOfFileHandling() throws IOException {
        // Test nextRecord when an EOF token is encountered and the reusable token is ready, ensuring the last record is added.
        // First record
        CSVRecord record = parser.nextRecord();
        assertNotNull(record);
        // Second record
        record = parser.nextRecord();
        assertNotNull(record);
        // Now we reach EOF
        // Should return null
        record = parser.nextRecord();
        assertNull(record);
    }

    @Test
    void testNextRecord_InvalidTokenHandling() throws IOException {
        // Test nextRecord when an INVALID token is encountered to ensure an IOException is thrown.
        // Simulate invalid data
        String invalidCsvInput = "a,b,c\n1,2,3\nINVALID_TOKEN";
        parser = new CSVParser(new StringReader(invalidCsvInput), format);
        // Expect IOException
        assertThrows(IOException.class, () -> {
            parser.nextRecord();
        });
    }

    @Test
    void testNextRecord_UnexpectedTokenHandling() throws IOException {
        // Test nextRecord when an unexpected token type is encountered to ensure an IllegalStateException is thrown.
        // Simulate unexpected state
        String unexpectedCsvInput = "a,b,c\n1,2,3\n# Comment\n4,5,6";
        parser = new CSVParser(new StringReader(unexpectedCsvInput), format.withCommentMarker('#'));
        // Expect IllegalStateException
        assertThrows(IllegalStateException.class, () -> {
            parser.nextRecord();
        });
    }

    @Test
    void testNextRecord_CommentHandling() throws IOException {
        // Test nextRecord when a COMMENT token is encountered to ensure comments are ignored correctly.
        String commentCsvInput = "# This is a comment\na,b,c\n1,2,3";
        parser = new CSVParser(new StringReader(commentCsvInput), format.withCommentMarker('#'));
        // Read the record
        CSVRecord record = parser.nextRecord();
        assertNotNull(record);
        assertEquals("1", record.get(0));
        assertNull(record.getComment());
    }

    @Test
    void testNextRecord_EmptyInputHandling() throws IOException {
        // Test nextRecord when there are no tokens available to ensure it returns null.
        String emptyCsvInput = "";
        parser = new CSVParser(new StringReader(emptyCsvInput), format);
        // Expect null
        CSVRecord record = parser.nextRecord();
        assertNull(record);
    }
}
