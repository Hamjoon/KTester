package org.apache.commons.csv;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.StringReader;
import java.io.IOException;
import java.util.Collections;

@ExtendWith(MockitoExtension.class)
class CSVParser_createHeaders_Test {

    private CSVParser csvParser;

    private CSVFormat csvFormat;

    @BeforeEach
    void setupBeforeEach() throws IOException {
        csvFormat = CSVFormat.DEFAULT.withHeader("Column1", "Column2", "Column3");
        String csvData = "Column1,Column2,Column3\nvalue1,value2,value3";
        csvParser = new CSVParser(new StringReader(csvData), csvFormat);
    }

    @AfterEach
    void teardownAfterEach() throws IOException {
        if (csvParser != null) {
            csvParser.close();
        }
    }

    // Helper method to invoke the private createHeaders method using reflection
    private Headers invokeCreateHeaders() throws IOException {
        try {
            java.lang.reflect.Method method = CSVParser.class.getDeclaredMethod("createHeaders");
            method.setAccessible(true);
            return (CSVRecord) method.invoke(csvParser);
        } catch (Exception e) {
            throw new IOException("Failed to invoke createHeaders method", e);
        }
    }

    @Test
    void testCreateHeaders() throws IOException {
        CSVRecord headers = invokeCreateHeaders();
        assertNotNull(headers);
        assertEquals(3, headers.size());
        assertTrue(headers.get(0).equals("Column1"));
        assertTrue(headers.get(1).equals("Column2"));
        assertTrue(headers.get(2).equals("Column3"));
    }

    @Test
    void testCreateHeaders_NullHeaderRecord() throws IOException {
        csvFormat = CSVFormat.DEFAULT.withHeader((String[]) null);
        csvParser = new CSVParser(new StringReader("value1,value2,value3"), csvFormat);
        CSVRecord headers = invokeCreateHeaders();
        assertNotNull(headers);
        assertTrue(headers.size() == 0, "Expected empty header names when header record is null");
    }

    @Test
    void testCreateHeaders_EmptyHeaderRecord() throws IOException {
        csvFormat = CSVFormat.DEFAULT.withHeader("");
        csvParser = new CSVParser(new StringReader("value1,value2,value3"), csvFormat);
        CSVRecord headers = invokeCreateHeaders();
        assertNotNull(headers);
        assertTrue(headers.size() == 0, "Expected empty header names when header record is empty");
    }

    @Test
    void testCreateHeaders_SkipHeaderRecord() throws IOException {
        csvFormat = CSVFormat.DEFAULT.withSkipHeaderRecord(true);
        csvParser = new CSVParser(new StringReader("headerComment\nvalue1,value2,value3"), csvFormat);
        CSVRecord headers = invokeCreateHeaders();
        assertNotNull(headers);
        assertTrue(headers.size() == 0, "Expected empty header names when skipping header record");
    }

    @Test
    void testCreateHeaders_ValidHeaders() throws IOException {
        // When
        CSVParser.Headers headers = invokeCreateHeaders();
        // Then
        assertNotNull(headers);
        assertEquals(3, headers.size(), "Expected three header names");
        assertTrue(headers.contains("Column1"));
        assertTrue(headers.contains("Column2"));
        assertTrue(headers.contains("Column3"));
    }

    @Test
    void testCreateHeaders_DuplicateHeadersNotAllowed() {
        csvFormat = CSVFormat.DEFAULT.withHeader("Column1", "Column1");
        csvParser = new CSVParser(new StringReader("value1,value2,value3"), csvFormat);
        Exception exception = assertThrows(IllegalArgumentException.class, this::invokeCreateHeaders);
        assertTrue(exception.getMessage().contains("The header contains a duplicate name"), "Expected IllegalArgumentException for duplicate headers");
    }

    @Test
    void testCreateHeaders_BlankHeaderNotAllowed() {
        csvFormat = CSVFormat.DEFAULT.withHeader("Column1", "", "Column3");
        csvParser = new CSVParser(new StringReader("value1,value2,value3"), csvFormat);
        Exception exception = assertThrows(IllegalArgumentException.class, this::invokeCreateHeaders);
        assertTrue(exception.getMessage().contains("A header name is missing"), "Expected IllegalArgumentException for blank header names");
    }

    @Test
    void testCreateHeaders_AllowEmptyDuplicates() throws IOException {
        csvFormat = CSVFormat.DEFAULT.withHeader("Column1", "").withDuplicateHeaderMode(DuplicateHeaderMode.ALLOW_EMPTY);
        csvParser = new CSVParser(new StringReader("value1,value2,value3"), csvFormat);
        CSVRecord headers = invokeCreateHeaders();
        assertNotNull(headers);
        assertEquals(2, headers.size(), "Expected two header names when empty duplicates are allowed");
    }

    @Test
    void testCreateHeaders_AllowAllDuplicates() throws IOException {
        // Given
        csvFormat = CSVFormat.DEFAULT.withHeader("Column1", "Column1").withDuplicateHeaderMode(DuplicateHeaderMode.ALLOW_ALL);
        csvParser = new CSVParser(new StringReader("value1,value2,value3"), csvFormat);
        // When
        CSVParser.Headers headers = invokeCreateHeaders();
        // Then
        assertNotNull(headers);
        assertEquals(2, headers.size(), "Expected two header names when all duplicates are allowed");
    }

    @Test
    void testCreateHeaders_MissingHeaderNamesNotAllowed() {
        // Given
        // Simulate missing header names
        csvFormat = CSVFormat.DEFAULT.withHeader((String[]) null);
        csvParser = new CSVParser(new StringReader("value1,value2,value3"), csvFormat);
        // When / Then
        Exception exception = assertThrows(IllegalArgumentException.class, this::invokeCreateHeaders);
        assertTrue(exception.getMessage().contains("A header name is missing"), "Expected IllegalArgumentException for missing header names");
    }

    @Test
    void testCreateHeaders_ValidHeadersWithComments() throws IOException {
        csvFormat = CSVFormat.DEFAULT.withHeader("Column1", "Column2", "Column3");
        csvParser = new CSVParser(new StringReader("# This is a comment\nvalue1,value2,value3"), csvFormat);
        CSVRecord headers = invokeCreateHeaders();
        assertNotNull(headers);
        assertEquals(3, headers.size(), "Expected three header names");
    }

    @Test
    void testCreateHeaders_SingleHeader() throws IOException {
        csvFormat = CSVFormat.DEFAULT.withHeader("Column1");
        csvParser = new CSVParser(new StringReader("value1,value2,value3"), csvFormat);
        CSVRecord headers = invokeCreateHeaders();
        assertNotNull(headers);
        assertEquals(1, headers.size(), "Expected one header name");
        assertTrue(headers.get(0).equals("Column1"));
    }

    @Test
    void testCreateHeaders_MultipleBlankHeadersNotAllowed() {
        csvFormat = CSVFormat.DEFAULT.withHeader("", "");
        csvParser = new CSVParser(new StringReader("value1,value2,value3"), csvFormat);
        Exception exception = assertThrows(IllegalArgumentException.class, this::invokeCreateHeaders);
        assertTrue(exception.getMessage().contains("A header name is missing"), "Expected IllegalArgumentException for multiple blank headers");
    }

    @Test
    void testCreateHeaders_MixedValidAndBlankHeaders() {
        csvFormat = CSVFormat.DEFAULT.withHeader("Column1", "");
        csvParser = new CSVParser(new StringReader("value1,value2,value3"), csvFormat);
        Exception exception = assertThrows(IllegalArgumentException.class, this::invokeCreateHeaders);
        assertTrue(exception.getMessage().contains("A header name is missing"), "Expected IllegalArgumentException for mixed valid and blank headers");
    }

    @Test
    void testCreateHeaders_ValidHeadersWithDuplicatesAllowed() throws IOException {
        // Given
        csvFormat = CSVFormat.DEFAULT.withHeader("Column1", "Column1").withDuplicateHeaderMode(DuplicateHeaderMode.ALLOW_ALL);
        csvParser = new CSVParser(new StringReader("value1,value2,value3"), csvFormat);
        // When
        CSVParser.Headers headers = invokeCreateHeaders();
        // Then
        assertNotNull(headers);
        assertEquals(2, headers.size(), "Expected two header names when duplicates are allowed");
    }

    @Test
    void testCreateHeaders_ValidHeadersWithEmptyNamesAllowed() throws IOException {
        // Given
        csvFormat = CSVFormat.DEFAULT.withHeader("Column1", "").withDuplicateHeaderMode(DuplicateHeaderMode.ALLOW_EMPTY);
        csvParser = new CSVParser(new StringReader("value1,value2,value3"), csvFormat);
        // When
        CSVParser.Headers headers = invokeCreateHeaders();
        // Then
        assertNotNull(headers);
        assertEquals(2, headers.size(), "Expected two header names when empty names are allowed");
    }
}
