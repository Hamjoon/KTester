```java
package org.apache.commons.csv;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.StringReader;
import java.io.IOException;

@ExtendWith(MockitoExtension.class)
class CSVParser_createHeaders_Test {
    private static final String CSV_INPUT = "header1,header2\nvalue1,value2";
    private CSVParser parser;
    private CSVFormat format;

    @BeforeAll
    static void setupBeforeAll() {
        // This method can be used for one-time setup that doesn't require instance variables.
    }

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

    @AfterAll
    static void teardownAfterAll() {
        // This method can be used for any cleanup that should happen once after all tests.
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
}
```