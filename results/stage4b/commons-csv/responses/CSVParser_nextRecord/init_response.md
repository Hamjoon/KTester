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
        assertNull(record); // No more records should be present
    }
}
```