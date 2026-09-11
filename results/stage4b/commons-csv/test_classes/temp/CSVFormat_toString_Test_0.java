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

    @BeforeEach
    void setupBeforeEach() {
        // Initialize a CSVFormat instance for testing
        csvFormat = CSVFormat.DEFAULT.builder().setDelimiter(',').setQuote('"').setRecordSeparator(CRLF).setNullString("NULL").build();
    }

    @Test
    void testToString() {
        String expected = "Delimiter=<,> QuoteChar=<\"> RecordSeparator=<\r\n> NullString=<NULL> SkipHeaderRecord:false";
        assertEquals(expected, csvFormat.toString());
    }

    @Test
    void testToStringWithDifferentDelimiter() {
        CSVFormat formatWithPipe = CSVFormat.DEFAULT.builder().setDelimiter('|').build();
        String expected = "Delimiter=<|> SkipHeaderRecord:false";
        assertEquals(expected, formatWithPipe.toString());
    }

    @Test
    void testToStringWithCommentMarker() {
        CSVFormat formatWithComment = CSVFormat.DEFAULT.builder().setCommentMarker('#').build();
        String expected = "Delimiter=<,> CommentStart=<#> SkipHeaderRecord:false";
        assertEquals(expected, formatWithComment.toString());
    }

    @Test
    void testToStringWithNullString() {
        CSVFormat formatWithNullString = CSVFormat.DEFAULT.builder().setNullString("N/A").build();
        String expected = "Delimiter=<,> NullString=<N/A> SkipHeaderRecord:false";
        assertEquals(expected, formatWithNullString.toString());
    }

    @Test
    void testToStringDefault() {
        // Test default CSVFormat instance to verify the default delimiter.
        String expected = "Delimiter=<,>";
        assertEquals(expected, csvFormat.toString());
    }

    @Test
    void testToStringInformixUnload() {
        // Test INFORMIX_UNLOAD CSVFormat instance to check all attributes are correctly represented.
        CSVFormat informixUnloadFormat = CSVFormat.INFORMIX_UNLOAD;
        String expected = "Delimiter=<|> Escape=<\\> QuoteChar=<\"> QuoteMode=<MINIMAL> CommentStart=<#> NullString=<NULL> RecordSeparator=<LF> EmptyLines:ignored SurroundingSpaces:ignored IgnoreHeaderCase:ignored SkipHeaderRecord:false HeaderComments:null Header:null";
        assertEquals(expected, informixUnloadFormat.toString());
    }

    @Test
    void testToStringExcel() {
        // Test EXCEL CSVFormat instance to verify the attributes without comment marker and null string.
        CSVFormat excelFormat = CSVFormat.EXCEL;
        String expected = "Delimiter=<,> Escape=<\\> QuoteChar=<\"> RecordSeparator=<LF> EmptyLines:ignored SurroundingSpaces:ignored IgnoreHeaderCase:ignored SkipHeaderRecord:false HeaderComments:null Header:null";
        assertEquals(expected, excelFormat.toString());
    }

    @Test
    void testToStringMongoDBTSV() {
        // Test MONGODB_TSV CSVFormat instance to verify tab delimiter and other attributes.
        CSVFormat mongoDBTSVFormat = CSVFormat.MONGODB_TSV;
        String expected = "Delimiter=<\t> Escape=<\\> QuoteChar=<\"> QuoteMode=<MINIMAL> SkipHeaderRecord:false HeaderComments:null Header:null";
        assertEquals(expected, mongoDBTSVFormat.toString());
    }

    @Test
    void testToStringMySQL() {
        // Test MYSQL CSVFormat instance to verify attributes including null string.
        CSVFormat mySQLFormat = CSVFormat.MYSQL;
        String expected = "Delimiter=<\t> Escape=<\\> IgnoreEmptyLines:false Quote=null RecordSeparator=<LF> NullString=<NULL> SkipHeaderRecord:false";
        assertEquals(expected, mySQLFormat.toString());
    }

    @Test
    void testToStringPostgreSQLCSV() {
        // Test POSTGRESQL_CSV CSVFormat instance to verify attributes including null string.
        CSVFormat postgreSQLCSVFormat = CSVFormat.POSTGRESQL_CSV;
        String expected = "Delimiter=<,> Escape=<\\> IgnoreEmptyLines:false QuoteChar=<\"> RecordSeparator=<LF> NullString=<NULL> SkipHeaderRecord:false";
        assertEquals(expected, postgreSQLCSVFormat.toString());
    }

    @Test
    void testToStringTDF() {
        // Test TDF CSVFormat instance to ensure surrounding spaces are ignored.
        CSVFormat tdfFormat = CSVFormat.TDF;
        String expected = "Delimiter=<\t> IgnoreSurroundingSpaces:true";
        assertEquals(expected, tdfFormat.toString());
    }

    @Test
    void testToStringRFC4180() {
        // Test RFC4180 CSVFormat instance to verify it does not ignore any attributes.
        CSVFormat rfc4180Format = CSVFormat.RFC4180;
        String expected = "Delimiter=<,> Escape=<\\> QuoteChar=<\"> IgnoreEmptyLines:false IgnoreSurroundingSpaces:false IgnoreHeaderCase:false SkipHeaderRecord:false HeaderComments:null Header:null";
        assertEquals(expected, rfc4180Format.toString());
    }
}
