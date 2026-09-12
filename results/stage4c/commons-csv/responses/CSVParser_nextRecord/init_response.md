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

@ExtendWith(MockitoExtension.class)
class CSVParser_nextRecord_Test {

    private CSVParser parser;
    private CSVFormat format;

    @BeforeAll
    static void setupBeforeAll() {
        // Initialize any static resources if necessary
    }

    @BeforeEach
    void setupBeforeEach() throws IOException {
        // Initialize the CSVFormat and CSVParser before each test
        format = CSVFormat.DEFAULT;
        parser = new CSVParser(new StringReader("a,b,c\n1,2,3"), format);
    }

    @AfterEach
    void teardownAfterEach() throws IOException {
        // Clean up resources after each test
        if (parser != null) {
            parser.close();
        }
    }

    @AfterAll
    static void teardownAfterAll() {
        // Clean up any static resources if necessary
    }

    @Test
    void testNextRecord() throws IOException {
        // Given
        assertNotNull(parser);

        // When
        CSVRecord record1 = parser.nextRecord();
        CSVRecord record2 = parser.nextRecord();
        CSVRecord record3 = parser.nextRecord();

        // Then
        assertNotNull(record1);
        assertEquals("a", record1.get(0));
        assertEquals("b", record1.get(1));
        assertEquals("c", record1.get(2));

        assertNotNull(record2);
        assertEquals("1", record2.get(0));
        assertEquals("2", record2.get(1));
        assertEquals("3", record2.get(2));

        assertNull(record3);
    }
}
```