```java
package org.apache.commons.csv;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class CSVParser_createHeaders_Test {

    private CSVParser csvParser;
    private CSVFormat csvFormat;

    @BeforeAll
    static void setupBeforeAll() {
        // Global setup if needed
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize CSVFormat with necessary configurations
        csvFormat = CSVFormat.DEFAULT.withHeader("Col1", "Col2", "Col3").withSkipHeaderRecord(true);

        // Initialize CSVParser with a StringReader and the CSVFormat
        String csvData = "Col1,Col2,Col3\nval1,val2,val3";
        try {
            csvParser = new CSVParser(new StringReader(csvData), csvFormat);
        } catch (IOException e) {
            fail("IOException during setup: " + e.getMessage());
        }
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup resources
        try {
            csvParser.close();
        } catch (IOException e) {
            fail("IOException during teardown: " + e.getMessage());
        }
    }

    @AfterAll
    static void teardownAfterAll() {
        // Global cleanup if needed
    }

    @Test
    void testCreateHeadersWithNullFormatHeader() {
        // Test when formatHeader is null, expecting empty headerNames list.
        csvFormat = CSVFormat.DEFAULT.withHeader((String[]) null);

        try {
            csvParser = new CSVParser(new StringReader("val1,val2,val3"), csvFormat);
            Headers headers = csvParser.createHeaders();

            assertNotNull(headers, "Headers should not be null");
            assertTrue(headers.getHeaderNames().isEmpty(), "Header names list should be empty");
        } catch (IOException e) {
            fail("IOException during test: " + e.getMessage());
        }
    }

    @Test
    void testCreateHeadersWithEmptyFormatHeader() {
        // Test when formatHeader is empty, expecting headers to be read from the first line of the file.
        csvFormat = CSVFormat.DEFAULT.withHeader().withSkipHeaderRecord(false);

        try {
            csvParser = new CSVParser(new StringReader("Col1,Col2,Col3\nval1,val2,val3"), csvFormat);
            Headers headers = csvParser.createHeaders();

            assertNotNull(headers, "Headers should not be null");
            Map<String, Integer> headerMap = headers.getHeaderMap();
            List<String> headerNames = headers.getHeaderNames();

            assertEquals(3, headerMap.size(), "Header map size should be 3");
            assertEquals(3, headerNames.size(), "Header names size should be 3");
            assertTrue(headerMap.containsKey("Col1"), "Header map should contain 'Col1'");
            assertTrue(headerMap.containsKey("Col2"), "Header map should contain 'Col2'");
            assertTrue(headerMap.containsKey("Col3"), "Header map should contain 'Col3'");
        } catch (IOException e) {
            fail("IOException during test: " + e.getMessage());
        }
    }

    @Test
    void testCreateHeadersWithDuplicateHeadersAllowed() {
        // Test when formatHeader contains duplicates and duplicateHeaderMode is ALLOW_ALL, expecting no exception.
        csvFormat = CSVFormat.DEFAULT.withHeader("header1", "header1")
                .withDuplicateHeaderMode(DuplicateHeaderMode.ALLOW_ALL);

        try {
            csvParser = new CSVParser(new StringReader("val1,val2"), csvFormat);
            Headers headers = csvParser.createHeaders();

            assertNotNull(headers, "Headers should not be null");
            assertEquals(2, headers.getHeaderNames().size(), "Header names size should be 2");
        } catch (IOException e) {
            fail("IOException during test: " + e.getMessage());
        }
    }

    @Test
    void testCreateHeadersWithDuplicateHeadersDisallowed() {
        // Test when formatHeader contains duplicates and duplicateHeaderMode is DISALLOW, expecting IllegalArgumentException.
        csvFormat = CSVFormat.DEFAULT.withHeader("header1", "header1")
                .withDuplicateHeaderMode(DuplicateHeaderMode.DISALLOW);

        try {
            csvParser = new CSVParser(new StringReader("val1,val2"), csvFormat);
            assertThrows(IllegalArgumentException.class, () -> csvParser.createHeaders(),
                    "Expected IllegalArgumentException for duplicate headers");
        } catch (IOException e) {
            fail("IOException during test: " + e.getMessage());
        }
    }

    @Test
    void testCreateHeadersWithMissingHeaderNameNotAllowed() {
        // Test when formatHeader contains blank header and allowMissingColumnNames is false, expecting IllegalArgumentException.
        csvFormat = CSVFormat.DEFAULT.withHeader("header1", "").withAllowMissingColumnNames(false);

        try {
            csvParser = new CSVParser(new StringReader("val1,val2"), csvFormat);
            assertThrows(IllegalArgumentException.class, () -> csvParser.createHeaders(),
                    "Expected IllegalArgumentException for missing header name");
        } catch (IOException e) {
            fail("IOException during test: " + e.getMessage());
        }
    }

    @Test
    void testCreateHeadersWithMissingHeaderNameAllowed() {
        // Test when formatHeader contains blank header and allowMissingColumnNames is true, expecting no exception.
        csvFormat = CSVFormat.DEFAULT.withHeader("header1", "").withAllowMissingColumnNames(true);

        try {
            csvParser = new CSVParser(new StringReader("val1,val2"), csvFormat);
            Headers headers = csvParser.createHeaders();

            assertNotNull(headers, "Headers should not be null");
            assertEquals(2, headers.getHeaderNames().size(), "Header names size should be 2");
        } catch (IOException e) {
            fail("IOException during test: " + e.getMessage());
        }
    }
}
```