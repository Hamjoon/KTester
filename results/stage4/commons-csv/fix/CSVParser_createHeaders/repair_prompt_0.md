
You are an experienced Java developer tasked with fixing a piece of code that contains compilation errors. Your task is to resolve these issues after analyzing the code and its compilation feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any syntactical or logical errors.
2. Analyze the compilation feedback to understand the specific errors and their locations within the code.
3. Apply necessary corrections to the code based on the feedback, and output the compelete correct code.
The Java code you need to fix is provided below:
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
import java.util.Collections;

@ExtendWith(MockitoExtension.class)
class CSVParser_createHeaders_Test {

    private CSVParser csvParser;

    private CSVFormat csvFormat;

    @BeforeEach
    void setupBeforeEach() throws IOException {
        // Initialize the CSVFormat with a predefined format
        csvFormat = CSVFormat.DEFAULT.withHeader("Column1", "Column2", "Column3");
        // Create a CSVParser instance using a StringReader for testing
        String csvData = "Column1,Column2,Column3\nvalue1,value2,value3";
        csvParser = new CSVParser(new StringReader(csvData), csvFormat);
    }

    @AfterEach
    void teardownAfterEach() throws IOException {
        // Close the CSVParser to clean up resources
        if (csvParser != null) {
            csvParser.close();
        }
    }

    // Helper method to invoke the private createHeaders method using reflection
    private Headers invokeCreateHeaders() throws IOException {
        try {
            java.lang.reflect.Method method = CSVParser.class.getDeclaredMethod("createHeaders");
            method.setAccessible(true);
            return (Headers) method.invoke(csvParser);
        } catch (Exception e) {
            throw new IOException("Failed to invoke createHeaders method", e);
        }
    }

    @Test
    void testCreateHeaders() throws IOException {
        // Given
        // The CSVParser has been initialized with CSV data containing headers
        // When
        Headers headers = csvParser.createHeaders();
        // Then
        assertNotNull(headers);
        assertEquals(3, headers.getHeaderNames().size());
        assertTrue(headers.getHeaderNames().contains("Column1"));
        assertTrue(headers.getHeaderNames().contains("Column2"));
        assertTrue(headers.getHeaderNames().contains("Column3"));
    }

    @Test
    void testCreateHeaders_NullHeaderRecord() throws IOException {
        // Given
        // Simulate null header
        csvFormat = CSVFormat.DEFAULT.withHeader(null);
        csvParser = new CSVParser(new StringReader("value1,value2,value3"), csvFormat);
        // When
        Headers headers = invokeCreateHeaders();
        // Then
        assertNotNull(headers);
        assertTrue(headers.getHeaderNames().isEmpty(), "Expected empty header names when header record is null");
    }

    @Test
    void testCreateHeaders_EmptyHeaderRecord() throws IOException {
        // Given
        // Simulate empty header
        csvFormat = CSVFormat.DEFAULT.withHeader("");
        csvParser = new CSVParser(new StringReader("value1,value2,value3"), csvFormat);
        // When
        Headers headers = invokeCreateHeaders();
        // Then
        assertNotNull(headers);
        assertTrue(headers.getHeaderNames().isEmpty(), "Expected empty header names when header record is empty");
    }

    @Test
    void testCreateHeaders_SkipHeaderRecord() throws IOException {
        // Given
        csvFormat = CSVFormat.DEFAULT.withSkipHeaderRecord(true);
        csvParser = new CSVParser(new StringReader("headerComment\nvalue1,value2,value3"), csvFormat);
        // When
        Headers headers = invokeCreateHeaders();
        // Then
        assertNotNull(headers);
        assertTrue(headers.getHeaderNames().isEmpty(), "Expected empty header names when skipping header record");
        assertEquals("headerComment", csvParser.getHeaderComment(), "Expected header comment to be retrieved");
    }

    @Test
    void testCreateHeaders_ValidHeaders() throws IOException {
        // When
        Headers headers = invokeCreateHeaders();
        // Then
        assertNotNull(headers);
        assertEquals(3, headers.getHeaderNames().size(), "Expected three header names");
        assertTrue(headers.getHeaderNames().contains("Column1"));
        assertTrue(headers.getHeaderNames().contains("Column2"));
        assertTrue(headers.getHeaderNames().contains("Column3"));
    }

    @Test
    void testCreateHeaders_DuplicateHeadersNotAllowed() {
        // Given
        // Duplicate headers
        csvFormat = CSVFormat.DEFAULT.withHeader("Column1", "Column1");
        csvParser = new CSVParser(new StringReader("value1,value2,value3"), csvFormat);
        // When / Then
        Exception exception = assertThrows(IllegalArgumentException.class, this::invokeCreateHeaders);
        assertTrue(exception.getMessage().contains("The header contains a duplicate name"), "Expected IllegalArgumentException for duplicate headers");
    }

    @Test
    void testCreateHeaders_BlankHeaderNotAllowed() {
        // Given
        // Blank header
        csvFormat = CSVFormat.DEFAULT.withHeader("Column1", "", "Column3");
        csvParser = new CSVParser(new StringReader("value1,value2,value3"), csvFormat);
        // When / Then
        Exception exception = assertThrows(IllegalArgumentException.class, this::invokeCreateHeaders);
        assertTrue(exception.getMessage().contains("A header name is missing"), "Expected IllegalArgumentException for blank header names");
    }

    @Test
    void testCreateHeaders_AllowEmptyDuplicates() throws IOException {
        // Given
        csvFormat = CSVFormat.DEFAULT.withHeader("Column1", "").withDuplicateHeaderMode(DuplicateHeaderMode.ALLOW_EMPTY);
        csvParser = new CSVParser(new StringReader("value1,value2,value3"), csvFormat);
        // When
        Headers headers = invokeCreateHeaders();
        // Then
        assertNotNull(headers);
        assertEquals(2, headers.getHeaderNames().size(), "Expected two header names when empty duplicates are allowed");
    }

    @Test
    void testCreateHeaders_AllowAllDuplicates() throws IOException {
        // Given
        csvFormat = CSVFormat.DEFAULT.withHeader("Column1", "Column1").withDuplicateHeaderMode(DuplicateHeaderMode.ALLOW_ALL);
        csvParser = new CSVParser(new StringReader("value1,value2,value3"), csvFormat);
        // When
        Headers headers = invokeCreateHeaders();
        // Then
        assertNotNull(headers);
        assertEquals(2, headers.getHeaderNames().size(), "Expected two header names when all duplicates are allowed");
    }

    @Test
    void testCreateHeaders_MissingHeaderNamesNotAllowed() {
        // Given
        // Simulate missing header names
        csvFormat = CSVFormat.DEFAULT.withHeader(null);
        csvParser = new CSVParser(new StringReader("value1,value2,value3"), csvFormat);
        // When / Then
        Exception exception = assertThrows(IllegalArgumentException.class, this::invokeCreateHeaders);
        assertTrue(exception.getMessage().contains("A header name is missing"), "Expected IllegalArgumentException for missing header names");
    }

    @Test
    void testCreateHeaders_ValidHeadersWithComments() throws IOException {
        // Given
        csvFormat = CSVFormat.DEFAULT.withHeader("Column1", "Column2", "Column3");
        csvParser = new CSVParser(new StringReader("# This is a comment\nvalue1,value2,value3"), csvFormat);
        // When
        Headers headers = invokeCreateHeaders();
        // Then
        assertNotNull(headers);
        assertEquals(3, headers.getHeaderNames().size(), "Expected three header names");
        assertTrue(headers.getHeaderNames().contains("Column1"));
        assertTrue(headers.getHeaderNames().contains("Column2"));
        assertTrue(headers.getHeaderNames().contains("Column3"));
        assertEquals("# This is a comment", csvParser.getHeaderComment(), "Expected header comment to be retrieved");
    }

    @Test
    void testCreateHeaders_SingleHeader() throws IOException {
        // Given
        csvFormat = CSVFormat.DEFAULT.withHeader("Column1");
        csvParser = new CSVParser(new StringReader("value1,value2,value3"), csvFormat);
        // When
        Headers headers = invokeCreateHeaders();
        // Then
        assertNotNull(headers);
        assertEquals(1, headers.getHeaderNames().size(), "Expected one header name");
        assertTrue(headers.getHeaderNames().contains("Column1"));
    }

    @Test
    void testCreateHeaders_MultipleBlankHeadersNotAllowed() {
        // Given
        // Multiple blank headers
        csvFormat = CSVFormat.DEFAULT.withHeader("", "");
        csvParser = new CSVParser(new StringReader("value1,value2,value3"), csvFormat);
        // When / Then
        Exception exception = assertThrows(IllegalArgumentException.class, this::invokeCreateHeaders);
        assertTrue(exception.getMessage().contains("A header name is missing"), "Expected IllegalArgumentException for multiple blank headers");
    }

    @Test
    void testCreateHeaders_MixedValidAndBlankHeaders() {
        // Given
        // Mixed valid and blank headers
        csvFormat = CSVFormat.DEFAULT.withHeader("Column1", "");
        csvParser = new CSVParser(new StringReader("value1,value2,value3"), csvFormat);
        // When / Then
        Exception exception = assertThrows(IllegalArgumentException.class, this::invokeCreateHeaders);
        assertTrue(exception.getMessage().contains("A header name is missing"), "Expected IllegalArgumentException for mixed valid and blank headers");
    }

    @Test
    void testCreateHeaders_ValidHeadersWithDuplicatesAllowed() throws IOException {
        // Given
        csvFormat = CSVFormat.DEFAULT.withHeader("Column1", "Column1").withDuplicateHeaderMode(DuplicateHeaderMode.ALLOW_ALL);
        csvParser = new CSVParser(new StringReader("value1,value2,value3"), csvFormat);
        // When
        Headers headers = invokeCreateHeaders();
        // Then
        assertNotNull(headers);
        assertEquals(2, headers.getHeaderNames().size(), "Expected two header names when duplicates are allowed");
    }

    @Test
    void testCreateHeaders_ValidHeadersWithEmptyNamesAllowed() throws IOException {
        // Given
        csvFormat = CSVFormat.DEFAULT.withHeader("Column1", "").withDuplicateHeaderMode(DuplicateHeaderMode.ALLOW_EMPTY);
        csvParser = new CSVParser(new StringReader("value1,value2,value3"), csvFormat);
        // When
        Headers headers = invokeCreateHeaders();
        // Then
        assertNotNull(headers);
        assertEquals(2, headers.getHeaderNames().size(), "Expected two header names when empty names are allowed");
    }
}
```

Corresponding compilation feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
src/test/java/org/apache/commons/csv/CSVParser_createHeaders_Test.java:38: error: cannot find symbol
    private Headers invokeCreateHeaders() throws IOException {
            ^
  symbol:   class Headers
  location: class CSVParser_createHeaders_Test
src/test/java/org/apache/commons/csv/CSVParser_createHeaders_Test.java:42: error: cannot find symbol
            return (Headers) method.invoke(csvParser);
                    ^
  symbol:   class Headers
  location: class CSVParser_createHeaders_Test
src/test/java/org/apache/commons/csv/CSVParser_createHeaders_Test.java:53: error: cannot find symbol
        Headers headers = csvParser.createHeaders();
        ^
  symbol:   class Headers
  location: class CSVParser_createHeaders_Test
src/test/java/org/apache/commons/csv/CSVParser_createHeaders_Test.java:53: error: createHeaders() has private access in CSVParser
        Headers headers = csvParser.createHeaders();
                                   ^
src/test/java/org/apache/commons/csv/CSVParser_createHeaders_Test.java:66: error: reference to withHeader is ambiguous
        csvFormat = CSVFormat.DEFAULT.withHeader(null);
                                     ^
  both method withHeader(ResultSetMetaData) in CSVFormat and method withHeader(String...) in CSVFormat match
src/test/java/org/apache/commons/csv/CSVParser_createHeaders_Test.java:69: error: cannot find symbol
        Headers headers = invokeCreateHeaders();
        ^
  symbol:   class Headers
  location: class CSVParser_createHeaders_Test
src/test/java/org/apache/commons/csv/CSVParser_createHeaders_Test.java:82: error: cannot find symbol
        Headers headers = invokeCreateHeaders();
        ^
  symbol:   class Headers
  location: class CSVParser_createHeaders_Test
src/test/java/org/apache/commons/csv/CSVParser_createHeaders_Test.java:94: error: cannot find symbol
        Headers headers = invokeCreateHeaders();
        ^
  symbol:   class Headers
  location: class CSVParser_createHeaders_Test
src/test/java/org/apache/commons/csv/CSVParser_createHeaders_Test.java:104: error: cannot find symbol
        Headers headers = invokeCreateHeaders();
        ^
  symbol:   class Headers
  location: class CSVParser_createHeaders_Test
src/test/java/org/apache/commons/csv/CSVParser_createHeaders_Test.java:138: error: cannot find symbol
        csvFormat = CSVFormat.DEFAULT.withHeader("Column1", "").withDuplicateHeaderMode(DuplicateHeaderMode.ALLOW_EMPTY);
                                                               ^
  symbol:   method withDuplicateHeaderMode(DuplicateHeaderMode)
  location: class CSVFormat
src/test/java/org/apache/commons/csv/CSVParser_createHeaders_Test.java:141: error: cannot find symbol
        Headers headers = invokeCreateHeaders();
        ^
  symbol:   class Headers
  location: class CSVParser_createHeaders_Test
src/test/java/org/apache/commons/csv/CSVParser_createHeaders_Test.java:150: error: cannot find symbol
        csvFormat = CSVFormat.DEFAULT.withHeader("Column1", "Column1").withDuplicateHeaderMode(DuplicateHeaderMode.ALLOW_ALL);
                                                                      ^
  symbol:   method withDuplicateHeaderMode(DuplicateHeaderMode)
  location: class CSVFormat
src/test/java/org/apache/commons/csv/CSVParser_createHeaders_Test.java:153: error: cannot find symbol
        Headers headers = invokeCreateHeaders();
        ^
  symbol:   class Headers
  location: class CSVParser_createHeaders_Test
src/test/java/org/apache/commons/csv/CSVParser_createHeaders_Test.java:163: error: reference to withHeader is ambiguous
        csvFormat = CSVFormat.DEFAULT.withHeader(null);
                                     ^
  both method withHeader(ResultSetMetaData) in CSVFormat and method withHeader(String...) in CSVFormat match
src/test/java/org/apache/commons/csv/CSVParser_createHeaders_Test.java:176: error: cannot find symbol
        Headers headers = invokeCreateHeaders();
        ^
  symbol:   class Headers
  location: class CSVParser_createHeaders_Test
src/test/java/org/apache/commons/csv/CSVParser_createHeaders_Test.java:192: error: cannot find symbol
        Headers headers = invokeCreateHeaders();
        ^
  symbol:   class Headers
  location: class CSVParser_createHeaders_Test
src/test/java/org/apache/commons/csv/CSVParser_createHeaders_Test.java:224: error: cannot find symbol
        csvFormat = CSVFormat.DEFAULT.withHeader("Column1", "Column1").withDuplicateHeaderMode(DuplicateHeaderMode.ALLOW_ALL);
                                                                      ^
  symbol:   method withDuplicateHeaderMode(DuplicateHeaderMode)
  location: class CSVFormat
src/test/java/org/apache/commons/csv/CSVParser_createHeaders_Test.java:227: error: cannot find symbol
        Headers headers = invokeCreateHeaders();
        ^
  symbol:   class Headers
  location: class CSVParser_createHeaders_Test
src/test/java/org/apache/commons/csv/CSVParser_createHeaders_Test.java:236: error: cannot find symbol
        csvFormat = CSVFormat.DEFAULT.withHeader("Column1", "").withDuplicateHeaderMode(DuplicateHeaderMode.ALLOW_EMPTY);
                                                               ^
  symbol:   method withDuplicateHeaderMode(DuplicateHeaderMode)
  location: class CSVFormat
src/test/java/org/apache/commons/csv/CSVParser_createHeaders_Test.java:239: error: cannot find symbol
        Headers headers = invokeCreateHeaders();
        ^
  symbol:   class Headers
  location: class CSVParser_createHeaders_Test
Note: src/test/java/org/apache/commons/csv/CSVParser_createHeaders_Test.java uses or overrides a deprecated API.
Note: Recompile with -Xlint:deprecation for details.
20 errors

```

And you can use the following related information to assist you in your analysis and correction:
```
{'api document of class org.apache.commons.csv.CSVParser': '\n * Parses CSV files according to the specified format.\n *\n * Because CSV appears in many different dialects, the parser supports many formats by allowing the\n * specification of a {@link CSVFormat}.\n *\n * The parser works record wise. It is not possible to go back, once a record has been parsed from the input stream.\n *\n * <h2>Creating instances</h2>\n * <p>\n * There are several static factory methods that can be used to create instances for various types of resources:\n * </p>\n * <ul>\n *     <li>{@link #parse(java.io.File, Charset, CSVFormat)}</li>\n *     <li>{@link #parse(String, CSVFormat)}</li>\n *     <li>{@link #parse(java.net.URL, java.nio.charset.Charset, CSVFormat)}</li>\n * </ul>\n * <p>\n * Alternatively parsers can also be created by passing a {@link Reader} directly to the sole constructor.\n *\n * For those who like fluent APIs, parsers can be created using {@link CSVFormat#parse(java.io.Reader)} as a shortcut:\n * </p>\n * <pre>\n * for(CSVRecord record : CSVFormat.EXCEL.parse(in)) {\n *     ...\n * }\n * </pre>\n *\n * <h2>Parsing record wise</h2>\n * <p>\n * To parse a CSV input from a file, you write:\n * </p>\n *\n * <pre>\n * File csvData = new File(&quot;/path/to/csv&quot;);\n * CSVParser parser = CSVParser.parse(csvData, CSVFormat.RFC4180);\n * for (CSVRecord csvRecord : parser) {\n *     ...\n * }\n * </pre>\n *\n * <p>\n * This will read the parse the contents of the file using the\n * <a href="http://tools.ietf.org/html/rfc4180" target="_blank">RFC 4180</a> format.\n * </p>\n *\n * <p>\n * To parse CSV input in a format like Excel, you write:\n * </p>\n *\n * <pre>\n * CSVParser parser = CSVParser.parse(csvData, CSVFormat.EXCEL);\n * for (CSVRecord csvRecord : parser) {\n *     ...\n * }\n * </pre>\n *\n * <p>\n * If the predefined formats don\'t match the format at hands, custom formats can be defined. More information about\n * customising CSVFormats is available in {@link CSVFormat CSVFormat Javadoc}.\n * </p>\n *\n * <h2>Parsing into memory</h2>\n * <p>\n * If parsing record wise is not desired, the contents of the input can be read completely into memory.\n * </p>\n *\n * <pre>\n * Reader in = new StringReader(&quot;a;b\\nc;d&quot;);\n * CSVParser parser = new CSVParser(in, CSVFormat.EXCEL);\n * List&lt;CSVRecord&gt; list = parser.getRecords();\n * </pre>\n *\n * <p>\n * There are two constraints that have to be kept in mind:\n * </p>\n *\n * <ol>\n *     <li>Parsing into memory starts at the current position of the parser. If you have already parsed records from\n *     the input, those records will not end up in the in memory representation of your CSV data.</li>\n *     <li>Parsing into memory may consume a lot of system resources depending on the input. For example if you\'re\n *     parsing a 150MB file of CSV data the contents will be read completely into memory.</li>\n * </ol>\n *\n * <h2>Notes</h2>\n * <p>\n * Internal parser state is completely covered by the format and the reader-state.\n * </p>\n *\n * @see <a href="package-summary.html">package documentation for more details</a>\n ', 'api document of method createHeaders()': '\n     * Creates the name to index mapping if the format defines a header.\n     *\n     * @return null if the format has no header.\n     * @throws IOException if there is a problem reading the header or skipping the first record\n     ', 'return type': 'org.apache.commons.csv.CSVParser.Headers', 'dependent classes': 'class `org.apache.commons.csv.CSVParser`:\n\tdependent fields:\n\t\tjava.lang.String org.apache.commons.csv.CSVParser.headerComment;\n\t\torg.apache.commons.csv.CSVFormat org.apache.commons.csv.CSVParser.format;\n\t\nclass `org.apache.commons.csv.DuplicateHeaderMode`:\n\tdependent fields:\n\t\torg.apache.commons.csv.DuplicateHeaderMode org.apache.commons.csv.DuplicateHeaderMode.ALLOW_ALL;\n\t\torg.apache.commons.csv.DuplicateHeaderMode org.apache.commons.csv.DuplicateHeaderMode.ALLOW_EMPTY;\n\t\nclass `org.apache.commons.csv.CSVFormat`:\n\trelated functions:\n\t\tmethod `public static CSVFormat newFormat(char)` returns `org.apache.commons.csv.CSVFormat`, related with `org.apache.commons.csv.CSVParser.createHeaders()`, api document: \r\n     * Creates a new CSV format with the specified delimiter.\r\n     *\r\n     * <p>\r\n     * Use this method if you want to create a CSVFormat from scratch. All fields but the delimiter will be initialized with null/false.\r\n     * </p>\r\n     *\r\n     * @param delimiter the char used for value separation, must not be a line break character\r\n     * @return a new CSV format.\r\n     * @throws IllegalArgumentException if the delimiter is a line break character\r\n     *\r\n     * @see #DEFAULT\r\n     * @see #RFC4180\r\n     * @see #MYSQL\r\n     * @see #EXCEL\r\n     * @see #TDF\r\n     ;\n\t\tmethod `private void validate()` returns `void`, related with `org.apache.commons.csv.CSVParser.createHeaders()`, api document: \r\n     * Verifies the validity and consistency of the attributes, and throws an {@link IllegalArgumentException} if necessary.\r\n     * <p>\r\n     * Because an instance can be used for both writing an parsing, not all conditions can be tested here. For example allowMissingColumnNames is only used for\r\n     * parsing, so it cannot be used here.\r\n     * </p>\r\n     *\r\n     * @throws IllegalArgumentException Throw when any attribute is invalid or inconsistent with other attributes.\r\n     ;\nclass `org.apache.commons.csv.CSVRecord`:\n\trelated functions:\n\t\tmethod `public String get(String)` returns `java.lang.String`, related with `org.apache.commons.csv.CSVParser.createHeaders()`, api document: \n     * Returns a value by name.\n     *\n     * <p>\n     * Note: This requires a field mapping obtained from the original parser.\n     * A check using {@link #isMapped(String)} should be used to determine if a\n     * mapping exists from the provided {@code name} to a field index. In this case an\n     * exception will only be thrown if the record does not contain a field corresponding\n     * to the mapping, that is the record length is not consistent with the mapping size.\n     * </p>\n     *\n     * @param name\n     *            the name of the column to be retrieved.\n     * @return the column value, maybe null depending on {@link CSVFormat#getNullString()}.\n     * @throws IllegalStateException\n     *             if no header mapping was provided\n     * @throws IllegalArgumentException\n     *             if {@code name} is not mapped or if the record is inconsistent\n     * @see #isMapped(String)\n     * @see #isConsistent()\n     * @see #getParser()\n     * @see CSVFormat.Builder#setNullString(String)\n     ;\n\t\tmethod `public M putIn(M)` returns `M`, related with `org.apache.commons.csv.CSVParser.createHeaders()`, api document: \n     * Puts all values of this record into the given Map.\n     *\n     * @param <M> the map type\n     * @param map The Map to populate.\n     * @return the given map.\n     * @since 1.9.0\n     ;'}
```

**Note**: If the method-to-test in the @Test function is a private method, you can use **reflection** to call it.

Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 


