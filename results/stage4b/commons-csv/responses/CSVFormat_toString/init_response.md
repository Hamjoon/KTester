```java
package org.apache.commons.csv;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CSVFormat_toString_Test {
    private CSVFormat csvFormat;

    @BeforeAll
    static void setupBeforeAll() {
        // Perform any global setup if necessary
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize a CSVFormat instance for testing
        csvFormat = CSVFormat.DEFAULT.builder()
                .setDelimiter(',')
                .setQuote('"')
                .setRecordSeparator(CRLF)
                .setNullString("NULL")
                .build();
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup after each test if necessary
    }

    @AfterAll
    static void teardownAfterAll() {
        // Perform any global cleanup if necessary
    }

    @Test
    void testToString() {
        String expected = "Delimiter=<,> QuoteChar=<\"> RecordSeparator=<\r\n> NullString=<NULL> SkipHeaderRecord:false";
        assertEquals(expected, csvFormat.toString());
    }

    @Test
    void testToStringWithDifferentDelimiter() {
        CSVFormat formatWithPipe = CSVFormat.DEFAULT.builder()
                .setDelimiter('|')
                .build();
        String expected = "Delimiter=<|> SkipHeaderRecord:false";
        assertEquals(expected, formatWithPipe.toString());
    }

    @Test
    void testToStringWithCommentMarker() {
        CSVFormat formatWithComment = CSVFormat.DEFAULT.builder()
                .setCommentMarker('#')
                .build();
        String expected = "Delimiter=<,> CommentStart=<#> SkipHeaderRecord:false";
        assertEquals(expected, formatWithComment.toString());
    }

    @Test
    void testToStringWithNullString() {
        CSVFormat formatWithNullString = CSVFormat.DEFAULT.builder()
                .setNullString("N/A")
                .build();
        String expected = "Delimiter=<,> NullString=<N/A> SkipHeaderRecord:false";
        assertEquals(expected, formatWithNullString.toString());
    }
}
```