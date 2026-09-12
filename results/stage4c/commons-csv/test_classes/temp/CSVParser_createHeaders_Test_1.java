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
import org.junit.jupiter.api.extension.ExtendWith;
import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class CSVParser_createHeaders_Test {

    private CSVParser csvParser;

    private CSVFormat csvFormat;

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

    @Test
    void testCreateHeaders() {
        try {
            // Use reflection to access the private createHeaders method
            Method createHeadersMethod = CSVParser.class.getDeclaredMethod("createHeaders");
            createHeadersMethod.setAccessible(true);
            // Invoke the focal method
            Object headers = createHeadersMethod.invoke(csvParser);
            // Assertions to validate the behavior
            assertNotNull(headers, "Headers should not be null");
            // Use reflection to access methods of the Headers object
            Method getHeaderMapMethod = headers.getClass().getDeclaredMethod("getHeaderMap");
            Method getHeaderNamesMethod = headers.getClass().getDeclaredMethod("getHeaderNames");
            Map<String, Integer> headerMap = (Map<String, Integer>) getHeaderMapMethod.invoke(headers);
            List<String> headerNames = (List<String>) getHeaderNamesMethod.invoke(headers);
            assertEquals(3, headerMap.size(), "Header map size should be 3");
            assertEquals(3, headerNames.size(), "Header names size should be 3");
            assertTrue(headerMap.containsKey("Col1"), "Header map should contain 'Col1'");
            assertTrue(headerMap.containsKey("Col2"), "Header map should contain 'Col2'");
            assertTrue(headerMap.containsKey("Col3"), "Header map should contain 'Col3'");
        } catch (Exception e) {
            fail("Exception during test: " + e.getMessage());
        }
    }

    @Test
    void testCreateHeadersWithNullFormatHeader() {
        // Test when formatHeader is null, expecting empty headerNames list.
        csvFormat = CSVFormat.DEFAULT.withHeader((String[]) null);
        try {
            csvParser = new CSVParser(new StringReader("val1,val2,val3"), csvFormat);
            // Use reflection to access the private createHeaders method
            Method createHeadersMethod = CSVParser.class.getDeclaredMethod("createHeaders");
            createHeadersMethod.setAccessible(true);
            Object headers = createHeadersMethod.invoke(csvParser);
            assertNotNull(headers, "Headers should not be null");
            // Use reflection to access methods of the Headers object
            Method getHeaderNamesMethod = headers.getClass().getDeclaredMethod("getHeaderNames");
            List<String> headerNames = (List<String>) getHeaderNamesMethod.invoke(headers);
            assertTrue(headerNames.isEmpty(), "Header names list should be empty");
        } catch (Exception e) {
            fail("Exception during test: " + e.getMessage());
        }
    }

    @Test
    void testCreateHeadersWithEmptyFormatHeader() {
        // Test when formatHeader is empty, expecting headers to be read from the first line of the file.
        csvFormat = CSVFormat.DEFAULT.withHeader().withSkipHeaderRecord(false);
        try {
            csvParser = new CSVParser(new StringReader("Col1,Col2,Col3\nval1,val2,val3"), csvFormat);
            // Use reflection to access the private createHeaders method
            Method createHeadersMethod = CSVParser.class.getDeclaredMethod("createHeaders");
            createHeadersMethod.setAccessible(true);
            Object headers = createHeadersMethod.invoke(csvParser);
            assertNotNull(headers, "Headers should not be null");
            // Use reflection to access methods of the Headers object
            Method getHeaderMapMethod = headers.getClass().getDeclaredMethod("getHeaderMap");
            Method getHeaderNamesMethod = headers.getClass().getDeclaredMethod("getHeaderNames");
            Map<String, Integer> headerMap = (Map<String, Integer>) getHeaderMapMethod.invoke(headers);
            List<String> headerNames = (List<String>) getHeaderNamesMethod.invoke(headers);
            assertEquals(3, headerMap.size(), "Header map size should be 3");
            assertEquals(3, headerNames.size(), "Header names size should be 3");
            assertTrue(headerMap.containsKey("Col1"), "Header map should contain 'Col1'");
            assertTrue(headerMap.containsKey("Col2"), "Header map should contain 'Col2'");
            assertTrue(headerMap.containsKey("Col3"), "Header map should contain 'Col3'");
        } catch (Exception e) {
            fail("Exception during test: " + e.getMessage());
        }
    }

    @Test
    void testCreateHeadersWithDuplicateHeadersAllowed() {
        // Test when formatHeader contains duplicates and duplicateHeaderMode is ALLOW_ALL, expecting no exception.
        csvFormat = CSVFormat.DEFAULT.withHeader("header1", "header1").withAllowDuplicateHeaderNames(true);
        try {
            csvParser = new CSVParser(new StringReader("val1,val2"), csvFormat);
            // Use reflection to access the private createHeaders method
            Method createHeadersMethod = CSVParser.class.getDeclaredMethod("createHeaders");
            createHeadersMethod.setAccessible(true);
            Object headers = createHeadersMethod.invoke(csvParser);
            assertNotNull(headers, "Headers should not be null");
            // Use reflection to access methods of the Headers object
            Method getHeaderNamesMethod = headers.getClass().getDeclaredMethod("getHeaderNames");
            List<String> headerNames = (List<String>) getHeaderNamesMethod.invoke(headers);
            assertEquals(2, headerNames.size(), "Header names size should be 2");
        } catch (Exception e) {
            fail("Exception during test: " + e.getMessage());
        }
    }

    @Test
    void testCreateHeadersWithDuplicateHeadersDisallowed() {
        // Test when formatHeader contains duplicates and duplicateHeaderMode is DISALLOW, expecting IllegalArgumentException.
        csvFormat = CSVFormat.DEFAULT.withHeader("header1", "header1").withAllowDuplicateHeaderNames(false);
        try {
            csvParser = new CSVParser(new StringReader("val1,val2"), csvFormat);
            // Use reflection to access the private createHeaders method
            Method createHeadersMethod = CSVParser.class.getDeclaredMethod("createHeaders");
            createHeadersMethod.setAccessible(true);
            assertThrows(IllegalArgumentException.class, () -> createHeadersMethod.invoke(csvParser), "Expected IllegalArgumentException for duplicate headers");
        } catch (Exception e) {
            fail("Exception during test: " + e.getMessage());
        }
    }

    @Test
    void testCreateHeadersWithMissingHeaderNameNotAllowed() {
        // Test when formatHeader contains blank header and allowMissingColumnNames is false, expecting IllegalArgumentException.
        csvFormat = CSVFormat.DEFAULT.withHeader("header1", "").withAllowMissingColumnNames(false);
        try {
            csvParser = new CSVParser(new StringReader("val1,val2"), csvFormat);
            // Use reflection to access the private createHeaders method
            Method createHeadersMethod = CSVParser.class.getDeclaredMethod("createHeaders");
            createHeadersMethod.setAccessible(true);
            assertThrows(IllegalArgumentException.class, () -> createHeadersMethod.invoke(csvParser), "Expected IllegalArgumentException for missing header name");
        } catch (Exception e) {
            fail("Exception during test: " + e.getMessage());
        }
    }

    @Test
    void testCreateHeadersWithMissingHeaderNameAllowed() {
        // Test when formatHeader contains blank header and allowMissingColumnNames is true, expecting no exception.
        csvFormat = CSVFormat.DEFAULT.withHeader("header1", "").withAllowMissingColumnNames(true);
        try {
            csvParser = new CSVParser(new StringReader("val1,val2"), csvFormat);
            // Use reflection to access the private createHeaders method
            Method createHeadersMethod = CSVParser.class.getDeclaredMethod("createHeaders");
            createHeadersMethod.setAccessible(true);
            Object headers = createHeadersMethod.invoke(csvParser);
            assertNotNull(headers, "Headers should not be null");
            // Use reflection to access methods of the Headers object
            Method getHeaderNamesMethod = headers.getClass().getDeclaredMethod("getHeaderNames");
            List<String> headerNames = (List<String>) getHeaderNamesMethod.invoke(headers);
            assertEquals(2, headerNames.size(), "Header names size should be 2");
        } catch (Exception e) {
            fail("Exception during test: " + e.getMessage());
        }
    }
}
