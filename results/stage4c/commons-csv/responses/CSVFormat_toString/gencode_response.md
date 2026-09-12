```java
package org.apache.commons.csv;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

// Add other imports if necessary

class CSVFormat_toString_Test {

    private CSVFormat csvFormat;

    @BeforeAll
    static void setupBeforeAll() {
        // Initialize resources needed for all tests, if any
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize CSVFormat instance before each test
        csvFormat = CSVFormat.DEFAULT;
    }

    @AfterEach
    void teardownAfterEach() {
        // Clean up resources used in each test, if any
    }

    @AfterAll
    static void teardownAfterAll() {
        // Clean up resources used for all tests, if any
    }

    @Test
    void testToString_DefaultFormat() {
        // Given: A default CSVFormat instance
        // When: Calling toString
        String result = csvFormat.toString();
        // Then: Verify the string representation is as expected
        assertNotNull(result);
        assertTrue(result.contains("Delimiter=<,>"));
        assertTrue(result.contains("SkipHeaderRecord:false"));
    }

    @Test
    void testToString_INFORMIX_UNLOAD_CSVFormat() {
        // Given: An INFORMIX_UNLOAD_CSV CSVFormat instance
        csvFormat = CSVFormat.INFORMIX_UNLOAD_CSV;
        // When: Calling toString
        String result = csvFormat.toString();
        // Then: Verify the string representation is as expected
        assertEquals("Delimiter=<,> Escape=<\\> QuoteChar=<\"> QuoteMode=<MINIMAL> RecordSeparator=<\\n> SkipHeaderRecord:false", result);
    }

    @Test
    void testToString_POSTGRESQL_CSVFormat() {
        // Given: A POSTGRESQL_CSV CSVFormat instance
        csvFormat = CSVFormat.POSTGRESQL_CSV;
        // When: Calling toString
        String result = csvFormat.toString();
        // Then: Verify the string representation is as expected
        assertEquals("Delimiter=<,> QuoteChar=<\"> QuoteMode=<ALL_NON_NULL> RecordSeparator=<\\n> SkipHeaderRecord:false NullString=<>", result);
    }

    @Test
    void testToString_EXCELFormat() {
        // Given: An EXCEL CSVFormat instance
        csvFormat = CSVFormat.EXCEL;
        // When: Calling toString
        String result = csvFormat.toString();
        // Then: Verify the string representation is as expected
        assertEquals("Delimiter=<,> QuoteChar=<\"> QuoteMode=<MINIMAL> RecordSeparator=<\\r\\n> SkipHeaderRecord:false HeaderComments:null Header:null", result);
    }

    @Test
    void testToString_INFORMIX_UNLOADFormat() {
        // Given: An INFORMIX_UNLOAD CSVFormat instance
        csvFormat = CSVFormat.INFORMIX_UNLOAD;
        // When: Calling toString
        String result = csvFormat.toString();
        // Then: Verify the string representation is as expected
        assertEquals("Delimiter=<|> Escape=<\\> QuoteChar=<\"> RecordSeparator=<\\n> SkipHeaderRecord:false", result);
    }

    @Test
    void testToString_POSTGRESQL_TEXTFormat() {
        // Given: A POSTGRESQL_TEXT CSVFormat instance
        csvFormat = CSVFormat.POSTGRESQL_TEXT;
        // When: Calling toString
        String result = csvFormat.toString();
        // Then: Verify the string representation is as expected
        assertEquals("Delimiter=<\\t> Escape=<\\> RecordSeparator=<\\n> SkipHeaderRecord:false NullString=<NULL>", result);
    }

    @Test
    void testToString_ORACLEFormat() {
        // Given: An ORACLE CSVFormat instance
        csvFormat = CSVFormat.ORACLE;
        // When: Calling toString
        String result = csvFormat.toString();
        // Then: Verify the string representation is as expected
        assertEquals("Delimiter=<,> Escape=<\\> QuoteChar=<\"> NullString=<NULL> RecordSeparator=<system.lineSeparator()> SkipHeaderRecord:false", result);
    }

    @Test
    void testToString_MONGODB_TSVFormat() {
        // Given: A MONGODB_TSV CSVFormat instance
        csvFormat = CSVFormat.MONGODB_TSV;
        // When: Calling toString
        String result = csvFormat.toString();
        // Then: Verify the string representation is as expected
        assertEquals("Delimiter=<\\t> Escape=<\\> QuoteChar=<\"> QuoteMode=<MINIMAL> RecordSeparator=<\\n> SkipHeaderRecord:false", result);
    }

    @Test
    void testToString_MYSQLFormat() {
        // Given: A MYSQL CSVFormat instance
        csvFormat = CSVFormat.MYSQL;
        // When: Calling toString
        String result = csvFormat.toString();
        // Then: Verify the string representation is as expected
        assertEquals("Delimiter=<\\t> Escape=<\\> QuoteMode=<ALL_NON_NULL> RecordSeparator=<\\n> SkipHeaderRecord:false NullString=<NULL>", result);
    }

    @Test
    void testToString_RFC4180Format() {
        // Given: An RFC4180 CSVFormat instance
        csvFormat = CSVFormat.RFC4180;
        // When: Calling toString
        String result = csvFormat.toString();
        // Then: Verify the string representation is as expected
        assertEquals("Delimiter=<,> QuoteChar=<\"> RecordSeparator=<\\r\\n> SkipHeaderRecord:false", result);
    }

    @Test
    void testToString_TDFFormat() {
        // Given: A TDF CSVFormat instance
        csvFormat = CSVFormat.TDF;
        // When: Calling toString
        String result = csvFormat.toString();
        // Then: Verify the string representation is as expected
        assertEquals("Delimiter=<\\t> RecordSeparator=<\\r\\n> SkipHeaderRecord:false SurroundingSpaces:ignored", result);
    }

    @Test
    void testToString_CustomFormatWithHeaderCommentsAndHeaders() {
        // Given: A custom CSVFormat instance with headerComments and headers set
        csvFormat = CSVFormat.DEFAULT.builder()
            .setHeaderComments("Comment 1", "Comment 2")
            .setHeader("Header1", "Header2")
            .build();
        // When: Calling toString
        String result = csvFormat.toString();
        // Then: Verify the string representation is as expected
        assertEquals("Delimiter=<,> Escape=<\\> QuoteChar=<\"> QuoteMode=<MINIMAL> RecordSeparator=<\\n> SkipHeaderRecord:false HeaderComments:[\"Comment 1\", \"Comment 2\"] Header:[\"Header1\", \"Header2\"]", result);
    }

    @Test
    void testToString_CustomFormatWithSkipHeaderRecordTrue() {
        // Given: A custom CSVFormat instance with skipHeaderRecord set to true
        csvFormat = CSVFormat.DEFAULT.builder()
            .setSkipHeaderRecord(true)
            .build();
        // When: Calling toString
        String result = csvFormat.toString();
        // Then: Verify the string representation is as expected
        assertEquals("Delimiter=<,> QuoteChar=<\"> QuoteMode=<MINIMAL> RecordSeparator=<\\n> SkipHeaderRecord:true", result);
    }

    @Test
    void testToString_CustomFormatWithSemicolonDelimiter() {
        // Given: A custom CSVFormat instance with semicolon delimiter and single quote as quote character
        csvFormat = CSVFormat.DEFAULT.builder()
            .setDelimiter(';')
            .setQuote('\'')
            .setRecordSeparator("\r\n")
            .build();
        // When: Calling toString
        String result = csvFormat.toString();
        // Then: Verify the string representation is as expected
        assertEquals("Delimiter=<;> Escape=<\\> QuoteChar=<'>' QuoteMode=<MINIMAL> RecordSeparator=<\\r\\n> SkipHeaderRecord:false", result);
    }

    @Test
    void testToString_CustomFormatWithQuoteModeNone() {
        // Given: A custom CSVFormat instance with quoteMode set to NONE
        csvFormat = CSVFormat.DEFAULT.builder()
            .setQuoteMode(QuoteMode.NONE)
            .build();
        // When: Calling toString
        String result = csvFormat.toString();
        // Then: Verify the string representation is as expected
        assertEquals("Delimiter=<,> QuoteChar=<\"> QuoteMode=<NONE> RecordSeparator=<\\n> SkipHeaderRecord:false", result);
    }

    @Test
    void testToString_CustomFormatWithPipeDelimiterAndCarriageReturn() {
        // Given: A custom CSVFormat instance with pipe delimiter, carriage return as record separator, and all fields quoted
        csvFormat = CSVFormat.DEFAULT.builder()
            .setDelimiter('|')
            .setRecordSeparator("\r")
            .setQuoteMode(QuoteMode.ALL)
            .build();
        // When: Calling toString
        String result = csvFormat.toString();
        // Then: Verify the string representation is as expected
        assertEquals("Delimiter=<|> Escape=<\\> QuoteChar=<\"> QuoteMode=<ALL> RecordSeparator=<\\r> SkipHeaderRecord:false", result);
    }

    @Test
    void testToString_CustomFormatWithIgnoreHeaderCase() {
        // Given: A custom CSVFormat instance where ignoreHeaderCase is true
        csvFormat = CSVFormat.DEFAULT.builder()
            .setIgnoreHeaderCase(true)
            .build();
        // When: Calling toString
        String result = csvFormat.toString();
        // Then: Verify the string representation is as expected
        assertEquals("Delimiter=<,> QuoteChar=<\"> QuoteMode=<MINIMAL> RecordSeparator=<\\n> SkipHeaderRecord:false IgnoreHeaderCase:ignored", result);
    }

    @Test
    void testToString_CustomFormatWithIgnoreEmptyLines() {
        // Given: A custom CSVFormat instance with tab delimiter and ignore empty lines set to true
        csvFormat = CSVFormat.DEFAULT.builder()
            .setDelimiter('\t')
            .setIgnoreEmptyLines(true)
            .setRecordSeparator("\r\n")
            .build();
        // When: Calling toString
        String result = csvFormat.toString();
        // Then: Verify the string representation is as expected
        assertEquals("Delimiter=<\\t> Escape=<\\> QuoteChar=<\"> QuoteMode=<MINIMAL> RecordSeparator=<\\r\\n> SkipHeaderRecord:false EmptyLines:ignored", result);
    }
}
```