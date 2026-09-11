package org.apache.commons.csv;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.StringReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class CSVParser_createHeaders_Test {

    private static final String CSV_INPUT = "header1,header2\nvalue1,value2";

    private CSVParser parser;

    private CSVFormat format;

    @BeforeEach
    void setupBeforeEach() throws IOException {
        // Initialize the CSVFormat with default settings for tests
        format = CSVFormat.DEFAULT.withHeader("header1", "header2");
        // Create a new CSVParser instance before each test
        parser = new CSVParser(new StringReader(CSV_INPUT), format);
    }

    @AfterEach
    void teardownAfterEach() throws IOException {
        // Clean up resources after each test
        if (parser != null) {
            parser.close();
        }
    }

    @Test
    void testCreateHeaders() throws IOException {
        // Given: A CSVParser instance with a predefined header format
        // When: The createHeaders() method is invoked (indirectly through parsing)
        Headers headers = parser.createHeaders();
        // Then: Verify that the headers are created correctly
        assertNotNull(headers);
        assertEquals(2, headers.getHeaderNames().size());
        assertTrue(headers.getHeaderNames().contains("header1"));
        assertTrue(headers.getHeaderNames().contains("header2"));
    }

    @Test
    void testCreateHeadersWithDuplicateNames() {
        // Given: A CSVFormat that allows duplicate headers
        format = CSVFormat.DEFAULT.withHeader("header1", "header1");
        // When: A CSVParser instance is created with duplicate headers
        parser = new CSVParser(new StringReader(CSV_INPUT), format);
        // Then: An IllegalArgumentException should be thrown
        assertThrows(IllegalArgumentException.class, () -> {
            parser.createHeaders();
        });
    }

    @Test
    void testCreateHeadersWithMissingNames() {
        // Given: A CSVFormat that does not allow missing column names
        format = CSVFormat.DEFAULT.withHeader("header1", "");
        // When: A CSVParser instance is created with a missing header name
        parser = new CSVParser(new StringReader(CSV_INPUT), format);
        // Then: An IllegalArgumentException should be thrown
        assertThrows(IllegalArgumentException.class, () -> {
            parser.createHeaders();
        });
    }

    @Test
    void testCreateHeaders_ValidHeader_NoDuplicates() throws IOException {
        // Given: A CSVParser instance with a valid header format
        String[] formatHeader = { "header1", "header2", "header3" };
        format = CSVFormat.DEFAULT.withHeader(formatHeader);
        parser = new CSVParser(new StringReader("header1,header2,header3\nvalue1,value2,value3"), format);
        // When: The createHeaders() method is invoked
        Headers headers = parser.createHeaders();
        // Then: Verify that the headers are created correctly
        assertNotNull(headers);
        assertEquals(3, headers.getHeaderNames().size());
        assertTrue(headers.getHeaderNames().contains("header1"));
        assertTrue(headers.getHeaderNames().contains("header2"));
        assertTrue(headers.getHeaderNames().contains("header3"));
        assertEquals(Map.of("header1", 0, "header2", 1, "header3", 2), headers.getHeaderMap());
    }

    @Test
    void testCreateHeaders_ValidHeader_DuplicatesAllowed() throws IOException {
        // Given: A CSVFormat that allows duplicate headers
        String[] formatHeader = { "header1", "header1" };
        format = CSVFormat.DEFAULT.withHeader(formatHeader);
        parser = new CSVParser(new StringReader("header1,header1\nvalue1,value2"), format);
        // When: The createHeaders() method is invoked
        Headers headers = parser.createHeaders();
        // Then: Verify that the headers are created correctly
        assertNotNull(headers);
        assertEquals(1, headers.getHeaderNames().size());
        assertTrue(headers.getHeaderNames().contains("header1"));
        assertEquals(Map.of("header1", 0), headers.getHeaderMap());
    }

    @Test
    void testCreateHeaders_MissingHeaderName_NoAllowance() {
        // Given: A CSVFormat that does not allow missing column names
        String[] formatHeader = { "", "header2" };
        format = CSVFormat.DEFAULT.withHeader(formatHeader);
        parser = new CSVParser(new StringReader("header2\nvalue1,value2"), format);
        // Then: An IllegalArgumentException should be thrown
        assertThrows(IllegalArgumentException.class, () -> {
            parser.createHeaders();
        });
    }

    @Test
    void testCreateHeaders_DuplicateHeaderName_NoAllowance() {
        // Given: A CSVFormat that does not allow duplicate headers
        String[] formatHeader = { "header1", "header1" };
        format = CSVFormat.DEFAULT.withHeader(formatHeader);
        parser = new CSVParser(new StringReader("header1,header1\nvalue1,value2"), format);
        // Then: An IllegalArgumentException should be thrown
        assertThrows(IllegalArgumentException.class, () -> {
            parser.createHeaders();
        });
    }

    @Test
    void testCreateHeaders_EmptyHeader() throws IOException {
        // Given: A CSVFormat with an empty header
        String[] formatHeader = {};
        format = CSVFormat.DEFAULT.withHeader(formatHeader);
        parser = new CSVParser(new StringReader(""), format);
        // When: The createHeaders() method is invoked
        Headers headers = parser.createHeaders();
        // Then: Verify that the headers are empty
        assertNotNull(headers);
        assertTrue(headers.getHeaderNames().isEmpty());
        assertTrue(headers.getHeaderMap().isEmpty());
    }

    @Test
    void testCreateHeaders_ValidHeader_WithBlankHeader_Allowance() throws IOException {
        // Given: A CSVFormat that allows blank headers
        String[] formatHeader = { "header1", "", "header3" };
        format = CSVFormat.DEFAULT.withHeader(formatHeader).withAllowMissingColumnNames();
        parser = new CSVParser(new StringReader("header1,,header3\nvalue1,value2,value3"), format);
        // When: The createHeaders() method is invoked
        Headers headers = parser.createHeaders();
        // Then: Verify that the headers are created correctly
        assertNotNull(headers);
        assertEquals(2, headers.getHeaderNames().size());
        assertTrue(headers.getHeaderNames().contains("header1"));
        assertTrue(headers.getHeaderNames().contains("header3"));
        assertEquals(Map.of("header1", 0, "header3", 2), headers.getHeaderMap());
    }

    @Test
    void testCreateHeaders_ValidHeader_WithComment() throws IOException {
        // Given: A CSVFormat with a valid header and a comment
        String[] formatHeader = { "header1", "header2" };
        format = CSVFormat.DEFAULT.withHeader(formatHeader);
        parser = new CSVParser(new StringReader("header1,header2\nvalue1,value2"), format);
        CSVRecord mockRecord = mock(CSVRecord.class);
        when(mockRecord.values()).thenReturn(new String[] { "value1", "value2" });
        when(mockRecord.getComment()).thenReturn("This is a comment");
        when(parser.nextRecord()).thenReturn(mockRecord);
        // When: The createHeaders() method is invoked
        Headers headers = parser.createHeaders();
        // Then: Verify that the headers are created correctly and the comment is set
        assertNotNull(headers);
        assertEquals("This is a comment", parser.getHeaderComment());
        assertEquals(Map.of("header1", 0, "header2", 1), headers.getHeaderMap());
    }

    @Test
    void testCreateHeaders_ValidHeader_WithBlankHeader_NoAllowance() {
        // Given: A CSVFormat that does not allow blank headers
        String[] formatHeader = { "header1", "", "header3" };
        format = CSVFormat.DEFAULT.withHeader(formatHeader);
        parser = new CSVParser(new StringReader("header1,,header3\nvalue1,value2,value3"), format);
        // Then: An IllegalArgumentException should be thrown
        assertThrows(IllegalArgumentException.class, () -> {
            parser.createHeaders();
        });
    }

    @Test
    void testCreateHeaders_ValidHeader_WithMultipleBlankHeaders_Allowance() throws IOException {
        // Given: A CSVFormat that allows multiple blank headers
        String[] formatHeader = { "header1", "", "", "header3" };
        format = CSVFormat.DEFAULT.withHeader(formatHeader).withAllowMissingColumnNames();
        parser = new CSVParser(new StringReader("header1,,header3\nvalue1,value2,value3"), format);
        // When: The createHeaders() method is invoked
        Headers headers = parser.createHeaders();
        // Then: Verify that the headers are created correctly
        assertNotNull(headers);
        assertEquals(2, headers.getHeaderNames().size());
        assertTrue(headers.getHeaderNames().contains("header1"));
        assertTrue(headers.getHeaderNames().contains("header3"));
        assertEquals(Map.of("header1", 0, "header3", 3), headers.getHeaderMap());
    }

    @Test
    void testCreateHeaders_ValidHeader_AllBlankNames_Allowance() throws IOException {
        // Given: A CSVFormat that allows all blank names
        String[] formatHeader = { "", "", "" };
        format = CSVFormat.DEFAULT.withHeader(formatHeader).withAllowMissingColumnNames();
        parser = new CSVParser(new StringReader(",,,value1,value2,value3"), format);
        // When: The createHeaders() method is invoked
        Headers headers = parser.createHeaders();
        // Then: Verify that the headers are empty
        assertNotNull(headers);
        assertTrue(headers.getHeaderNames().isEmpty());
        assertTrue(headers.getHeaderMap().isEmpty());
    }

    @Test
    void testCreateHeaders_ValidHeader_DuplicatesAndEmptyAllowed() throws IOException {
        // Given: A CSVFormat that allows duplicates and empty duplicates
        String[] formatHeader = { "header1", "header1" };
        format = CSVFormat.DEFAULT.withHeader(formatHeader).withAllowEmptyDuplicates();
        parser = new CSVParser(new StringReader("header1,header1\nvalue1,value2"), format);
        // When: The createHeaders() method is invoked
        Headers headers = parser.createHeaders();
        // Then: Verify that the headers are created correctly
        assertNotNull(headers);
        assertEquals(1, headers.getHeaderNames().size());
        assertTrue(headers.getHeaderNames().contains("header1"));
        assertEquals(Map.of("header1", 0), headers.getHeaderMap());
    }

    @Test
    void testCreateHeaders_ValidHeader_NoDuplicates_Valid() throws IOException {
        // Given: A CSVFormat with unique names and no duplicates allowed
        String[] formatHeader = { "header1", "header2", "header3" };
        format = CSVFormat.DEFAULT.withHeader(formatHeader);
        parser = new CSVParser(new StringReader("header1,header2,header3\nvalue1,value2,value3"), format);
        // When: The createHeaders() method is invoked
        Headers headers = parser.createHeaders();
        // Then: Verify that the headers are created correctly
        assertNotNull(headers);
        assertEquals(3, headers.getHeaderNames().size());
        assertTrue(headers.getHeaderNames().contains("header1"));
        assertTrue(headers.getHeaderNames().contains("header2"));
        assertTrue(headers.getHeaderNames().contains("header3"));
        assertEquals(Map.of("header1", 0, "header2", 1, "header3", 2), headers.getHeaderMap());
    }

    @Test
    void testCreateHeaders_MissingHeaderName_WithAllowance() {
        // Given: A CSVFormat that allows missing names
        String[] formatHeader = { "", "header2" };
        format = CSVFormat.DEFAULT.withHeader(formatHeader).withAllowMissingColumnNames();
        parser = new CSVParser(new StringReader("header2\nvalue1,value2"), format);
        // When: The createHeaders() method is invoked
        assertThrows(IllegalArgumentException.class, () -> {
            parser.createHeaders();
        });
    }
}
