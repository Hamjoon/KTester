```java
package org.apache.commons.csv;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.IOException;
import java.io.StringWriter;

@ExtendWith(MockitoExtension.class)
class CSVFormat_printWithQuotes_Test {

    private CSVFormat csvFormat;
    private StringWriter stringWriter;

    @BeforeAll
    static void setupBeforeAll() {
        // Initialize resources that are shared across all tests
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize resources for each test
        csvFormat = CSVFormat.DEFAULT;
        stringWriter = new StringWriter();
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup resources after each test
        stringWriter = null;
        csvFormat = null;
    }

    @AfterAll
    static void teardownAfterAll() {
        // Cleanup resources shared across all tests
    }

    @Test
    void testPrintWithQuotes() throws IOException {
        // Given
        Object object = "test";
        CharSequence charSeq = "value";
        Appendable out = stringWriter;
        boolean newRecord = true;

        // When
        csvFormat.printWithQuotes(object, charSeq, out, newRecord);

        // Then
        assertNotNull(stringWriter.toString());
        // Add assertions based on expected behavior
    }

    // Additional test cases can be added here
}
```