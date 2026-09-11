package org.apache.commons.csv;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.StringReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class CSVParser_createHeaders_Test {

    private static final String CSV_INPUT = "header1,header2\nvalue1,value2";

    private CSVParser parser;

    private CSVFormat format;

    @BeforeEach
    void setupBeforeEach() throws IOException {
        format = CSVFormat.DEFAULT.withHeader("header1", "header2");
        parser = new CSVParser(new StringReader(CSV_INPUT), format);
    }

    @AfterEach
    void teardownAfterEach() throws IOException {
        if (parser != null) {
            parser.close();
        }
    }

    private Object invokeCreateHeaders() throws Exception {
        Method method = CSVParser.class.getDeclaredMethod("createHeaders");
        method.setAccessible(true);
        return method.invoke(parser);
    }

    @Test
    void testCreateHeaders() throws IOException {
        CSVParser.Headers headers = (CSVParser.Headers) invokeCreateHeaders();
        assertNotNull(headers);
        assertEquals(2, headers.getHeaderCount());
        assertEquals("header1", headers.getHeader(0));
        assertEquals("header2", headers.getHeader(1));
        assertTrue(headers.getHeaderMap().containsKey("header1"));
        assertTrue(headers.getHeaderMap().containsKey("header2"));
    }

    @Test
    void testCreateHeadersWithDuplicateNames() {
        format = CSVFormat.DEFAULT.withHeader("header1", "header1");
        parser = new CSVParser(new StringReader(CSV_INPUT), format);
        assertThrows(IllegalArgumentException.class, () -> {
            invokeCreateHeaders();
        });
    }

    @Test
    void testCreateHeadersWithMissingNames() {
        format = CSVFormat.DEFAULT.withHeader("header1", "");
        parser = new CSVParser(new StringReader(CSV_INPUT), format);
        assertThrows(IllegalArgumentException.class, () -> {
            invokeCreateHeaders();
        });
    }

    @Test
    void testCreateHeaders_ValidHeader_NoDuplicates() throws IOException {
        String[] formatHeader = { "header1", "header2", "header3" };
        format = CSVFormat.DEFAULT.withHeader(formatHeader);
        parser = new CSVParser(new StringReader("header1,header2,header3\nvalue1,value2,value3"), format);
        CSVParser.Headers headers = (CSVParser.Headers) invokeCreateHeaders();
        assertNotNull(headers);
        assertEquals(3, headers.getHeaderCount());
        assertEquals("header1", headers.getHeader(0));
        assertEquals("header2", headers.getHeader(1));
        assertEquals("header3", headers.getHeader(2));
        assertEquals(Map.of("header1", 0, "header2", 1, "header3", 2), headers.getHeaderMap());
    }

    @Test
    void testCreateHeaders_ValidHeader_DuplicatesAllowed() throws IOException {
        String[] formatHeader = { "header1", "header1" };
        format = CSVFormat.DEFAULT.withHeader(formatHeader);
        parser = new CSVParser(new StringReader("header1,header1\nvalue1,value2"), format);
        CSVParser.Headers headers = (CSVParser.Headers) invokeCreateHeaders();
        assertNotNull(headers);
        assertEquals(1, headers.getHeaderCount());
        assertEquals("header1", headers.getHeader(0));
        assertEquals(Map.of("header1", 0), headers.getHeaderMap());
    }

    @Test
    void testCreateHeaders_MissingHeaderName_NoAllowance() {
        String[] formatHeader = { "", "header2" };
        format = CSVFormat.DEFAULT.withHeader(formatHeader);
        parser = new CSVParser(new StringReader("header2\nvalue1,value2"), format);
        assertThrows(IllegalArgumentException.class, () -> {
            invokeCreateHeaders();
        });
    }

    @Test
    void testCreateHeaders_DuplicateHeaderName_NoAllowance() {
        String[] formatHeader = { "header1", "header1" };
        format = CSVFormat.DEFAULT.withHeader(formatHeader);
        parser = new CSVParser(new StringReader("header1,header1\nvalue1,value2"), format);
        assertThrows(IllegalArgumentException.class, () -> {
            invokeCreateHeaders();
        });
    }

    @Test
    void testCreateHeaders_EmptyHeader() throws IOException {
        String[] formatHeader = {};
        format = CSVFormat.DEFAULT.withHeader(formatHeader);
        parser = new CSVParser(new StringReader(""), format);
        CSVParser.Headers headers = (CSVParser.Headers) invokeCreateHeaders();
        assertNotNull(headers);
        assertTrue(headers.getHeaderCount() == 0);
        assertTrue(headers.getHeaderMap().isEmpty());
    }

    @Test
    void testCreateHeaders_ValidHeader_WithBlankHeader_Allowance() throws IOException {
        String[] formatHeader = { "header1", "", "header3" };
        format = CSVFormat.DEFAULT.withHeader(formatHeader).withAllowMissingColumnNames();
        parser = new CSVParser(new StringReader("header1,,header3\nvalue1,value2,value3"), format);
        CSVParser.Headers headers = (CSVParser.Headers) invokeCreateHeaders();
        assertNotNull(headers);
        assertEquals(2, headers.getHeaderCount());
        assertTrue(headers.getHeaderMap().containsKey("header1"));
        assertTrue(headers.getHeaderMap().containsKey("header3"));
        assertEquals(Map.of("header1", 0, "header3", 2), headers.getHeaderMap());
    }

    @Test
    void testCreateHeaders_ValidHeader_WithComment() throws IOException {
        String[] formatHeader = { "header1", "header2" };
        format = CSVFormat.DEFAULT.withHeader(formatHeader);
        parser = new CSVParser(new StringReader("header1,header2\nvalue1,value2"), format);
        CSVRecord mockRecord = mock(CSVRecord.class);
        when(mockRecord.values()).thenReturn(new String[] { "value1", "value2" });
        when(mockRecord.getComment()).thenReturn("This is a comment");
        when(parser.nextRecord()).thenReturn(mockRecord);
        CSVParser.Headers headers = (CSVParser.Headers) invokeCreateHeaders();
        assertNotNull(headers);
        assertEquals("This is a comment", parser.getHeaderComment());
        assertEquals(Map.of("header1", 0, "header2", 1), headers.getHeaderMap());
    }

    @Test
    void testCreateHeaders_ValidHeader_WithBlankHeader_NoAllowance() {
        String[] formatHeader = { "header1", "", "header3" };
        format = CSVFormat.DEFAULT.withHeader(formatHeader);
        parser = new CSVParser(new StringReader("header1,,header3\nvalue1,value2,value3"), format);
        assertThrows(IllegalArgumentException.class, () -> {
            invokeCreateHeaders();
        });
    }

    @Test
    void testCreateHeaders_ValidHeader_WithMultipleBlankHeaders_Allowance() throws IOException {
        String[] formatHeader = { "header1", "", "", "header3" };
        format = CSVFormat.DEFAULT.withHeader(formatHeader).withAllowMissingColumnNames();
        parser = new CSVParser(new StringReader("header1,,header3\nvalue1,value2,value3"), format);
        CSVParser.Headers headers = (CSVParser.Headers) invokeCreateHeaders();
        assertNotNull(headers);
        assertEquals(2, headers.getHeaderCount());
        assertTrue(headers.getHeaderMap().containsKey("header1"));
        assertTrue(headers.getHeaderMap().containsKey("header3"));
        assertEquals(Map.of("header1", 0, "header3", 3), headers.getHeaderMap());
    }

    @Test
    void testCreateHeaders_ValidHeader_AllBlankNames_Allowance() throws IOException {
        String[] formatHeader = { "", "", "" };
        format = CSVFormat.DEFAULT.withHeader(formatHeader).withAllowMissingColumnNames();
        parser = new CSVParser(new StringReader(",,,value1,value2,value3"), format);
        CSVParser.Headers headers = (CSVParser.Headers) invokeCreateHeaders();
        assertNotNull(headers);
        assertTrue(headers.getHeaderCount() == 0);
        assertTrue(headers.getHeaderMap().isEmpty());
    }

    @Test
    void testCreateHeaders_ValidHeader_DuplicatesAndEmptyAllowed() throws IOException {
        String[] formatHeader = { "header1", "header1" };
        format = CSVFormat.DEFAULT.withHeader(formatHeader).withAllowMissingColumnNames();
        parser = new CSVParser(new StringReader("header1,header1\nvalue1,value2"), format);
        CSVParser.Headers headers = (CSVParser.Headers) invokeCreateHeaders();
        assertNotNull(headers);
        assertEquals(1, headers.getHeaderCount());
        assertTrue(headers.getHeaderMap().containsKey("header1"));
        assertEquals(Map.of("header1", 0), headers.getHeaderMap());
    }

    @Test
    void testCreateHeaders_ValidHeader_NoDuplicates_Valid() throws IOException {
        String[] formatHeader = { "header1", "header2", "header3" };
        format = CSVFormat.DEFAULT.withHeader(formatHeader);
        parser = new CSVParser(new StringReader("header1,header2,header3\nvalue1,value2,value3"), format);
        CSVParser.Headers headers = (CSVParser.Headers) invokeCreateHeaders();
        assertNotNull(headers);
        assertEquals(3, headers.getHeaderCount());
        assertTrue(headers.getHeaderMap().containsKey("header1"));
        assertTrue(headers.getHeaderMap().containsKey("header2"));
        assertTrue(headers.getHeaderMap().containsKey("header3"));
        assertEquals(Map.of("header1", 0, "header2", 1, "header3", 2), headers.getHeaderMap());
    }

    @Test
    void testCreateHeaders_MissingHeaderName_WithAllowance() {
        String[] formatHeader = { "", "header2" };
        format = CSVFormat.DEFAULT.withHeader(formatHeader).withAllowMissingColumnNames();
        parser = new CSVParser(new StringReader("header2\nvalue1,value2"), format);
        assertThrows(IllegalArgumentException.class, () -> {
            invokeCreateHeaders();
        });
    }
}
