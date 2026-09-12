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
    void testCreateHeaders() {
        try {
            // Invoke the focal method
            Headers headers = csvParser.createHeaders();

            // Assertions to validate the behavior
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
}
```