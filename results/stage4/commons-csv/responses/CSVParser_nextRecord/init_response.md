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

    // Additional test cases can be added here
}
```