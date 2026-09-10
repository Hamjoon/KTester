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
        format = CSVFormat.DEFAULT.withHeader(); // Using a default format with header
        String csvInput = "a,b,c\n1,2,3\n4,5,6"; // Sample CSV input
        parser = new CSVParser(new StringReader(csvInput), format);
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
        CSVRecord record = parser.nextRecord(); // First record
        assertNotNull(record);
        
        record = parser.nextRecord(); // Second record
        assertNotNull(record);
    }

    @Test
    void testNextRecord_EndOfFileHandling() throws IOException {
        // Test nextRecord when an EOF token is encountered and the reusable token is ready, ensuring the last record is added.
        CSVRecord record = parser.nextRecord(); // First record
        assertNotNull(record);
        
        record = parser.nextRecord(); // Second record
        assertNotNull(record);
        
        // Now we reach EOF
        record = parser.nextRecord(); // Should return null
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
        parser = new CSVParser(new StringReader(unexpectedCsvInput), format);
        
        // Expect IllegalStateException
        assertThrows(IllegalStateException.class, () -> {
            parser.nextRecord();
        });
    }

    @Test
    void testNextRecord_CommentHandling() throws IOException {
        // Test nextRecord when a COMMENT token is encountered to ensure comments are appended correctly.
        String commentCsvInput = "# This is a comment\na,b,c\n1,2,3";
        parser = new CSVParser(new StringReader(commentCsvInput), format);
        
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

    // Additional tests can be added for other scenarios as needed
}
```