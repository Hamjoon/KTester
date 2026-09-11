You are tasked with creating a framework for a test class in Java. Now analyze the following information carefully:

1. The method that needs to be tested and its parameters.
2. The constructor of the target class and its parameters.
3. Any dependencies or external resources used by the target class.

Based on this analysis, complete the test class template in the provided structure:

1. Initialize necessary fields and dependencies within setupBeforeAll() and setupBeforeEach().
2. Handle any required cleanup in teardownAfterEach() and teardownAfterAll().
3. Make sure not to invoke any private methods or fields of the target class and dependent class.
4. Refer to the "given" phase in the existing test class for guidance.
5. Add necessary import statements and ensure there are no syntax errors in the generated code.

@input{focal method and target class}
Here is the code of focal method, `nextRecord()`, with the focal class `CSVParser_nextRecord_Test`:
```Java
package org.apache.commons.csv;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.io.UncheckedIOException;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Files;
import java.util.stream.StreamSupport;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;
import java.util.Spliterator;
import static org.apache.commons.csv.Token.Type.TOKEN;
import java.util.Collections;
import java.util.stream.Collectors;
import java.nio.file.Path;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.TreeMap;
import java.util.Spliterators;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.io.File;
import java.util.Map;
import java.util.Arrays;
public final class CSVParser implements Iterable<CSVRecord> , Closeable {
    private String trailerComment;
    private final Lexer lexer;
    private final List<String> recordList = new ArrayList<>();
    private long recordNumber;
    private final long characterOffset;
    private final Token reusableToken = new Token();
    CSVRecord nextRecord() throws IOException {
        CSVRecord result = null;
        this.recordList.clear();
        StringBuilder sb = null;
        final long startCharPosition = lexer.getCharacterPosition() + this.characterOffset;
        do {
            this.reusableToken.reset();
            this.lexer.nextToken(this.reusableToken);
            switch (this.reusableToken.type) {
                case TOKEN :
                    this.addRecordValue(false);
                    break;
                case EORECORD :
                    this.addRecordValue(true);
                    break;
                case EOF :
                    if (this.reusableToken.isReady) {
                        this.addRecordValue(true);
                    } else if (sb != null) {
                        trailerComment = sb.toString();
                    }
                    break;
                case INVALID :
                    throw new IOException(("(line " + this.getCurrentLineNumber()) + ") invalid parse sequence");
                case COMMENT :
                    // Ignored currently
                    if (sb == null) {
                        // first comment for this record
                        sb = new StringBuilder();
                    } else {
                        sb.append(Constants.LF);
                    }
                    sb.append(this.reusableToken.content);
                    this.reusableToken.type = TOKEN;// Read another token
    
                    break;
                default :
                    throw new IllegalStateException("Unexpected Token type: " + this.reusableToken.type);
            }
        } while (this.reusableToken.type == TOKEN );
        if (!this.recordList.isEmpty()) {
            this.recordNumber++;
            final String comment = (sb == null) ? null : sb.toString();
            result = new CSVRecord(this, this.recordList.toArray(Constants.EMPTY_STRING_ARRAY), comment, this.recordNumber, startCharPosition);
        }
        return result;
    }
private void addRecordValue(final boolean lastRecord);
    @Override
public void close() throws IOException;
    public long getCurrentLineNumber();
    public String getFirstEndOfLine();
    public String getHeaderComment();
    public Map<String, Integer> getHeaderMap();
    Map<String, Integer> getHeaderMapRaw();
    public List<String> getHeaderNames();
    public long getRecordNumber();
    public List<CSVRecord> getRecords();
    public String getTrailerComment();
    public boolean hasHeaderComment();
    public boolean hasTrailerComment();
    public boolean isClosed();
    @Override
public Iterator<CSVRecord> iterator();
    public static CSVParser parse(final File file, final Charset charset, final CSVFormat format) throws IOException;
    @SuppressWarnings("resource")
public static CSVParser parse(final InputStream inputStream, final Charset charset, final CSVFormat format) throws IOException;
    public static CSVParser parse(final Reader reader, final CSVFormat format) throws IOException;
    public static CSVParser parse(final String string, final CSVFormat format) throws IOException;
    @SuppressWarnings("resource")
public static CSVParser parse(final URL url, final Charset charset, final CSVFormat format) throws IOException;
    @SuppressWarnings("resource")
public static CSVParser parse(final Path path, final Charset charset, final CSVFormat format) throws IOException;
    public Stream<CSVRecord> stream();
}
```


@input{context information}

api document of class org.apache.commons.csv.CSVParser: 

 * Parses CSV files according to the specified format.
 *
 * Because CSV appears in many different dialects, the parser supports many formats by allowing the
 * specification of a {@link CSVFormat}.
 *
 * The parser works record wise. It is not possible to go back, once a record has been parsed from the input stream.
 *
 * <h2>Creating instances</h2>
 * <p>
 * There are several static factory methods that can be used to create instances for various types of resources:
 * </p>
 * <ul>
 *     <li>{@link #parse(java.io.File, Charset, CSVFormat)}</li>
 *     <li>{@link #parse(String, CSVFormat)}</li>
 *     <li>{@link #parse(java.net.URL, java.nio.charset.Charset, CSVFormat)}</li>
 * </ul>
 * <p>
 * Alternatively parsers can also be created by passing a {@link Reader} directly to the sole constructor.
 *
 * For those who like fluent APIs, parsers can be created using {@link CSVFormat#parse(java.io.Reader)} as a shortcut:
 * </p>
 * <pre>
 * for(CSVRecord record : CSVFormat.EXCEL.parse(in)) {
 *     ...
 * }
 * </pre>
 *
 * <h2>Parsing record wise</h2>
 * <p>
 * To parse a CSV input from a file, you write:
 * </p>
 *
 * <pre>
 * File csvData = new File(&quot;/path/to/csv&quot;);
 * CSVParser parser = CSVParser.parse(csvData, CSVFormat.RFC4180);
 * for (CSVRecord csvRecord : parser) {
 *     ...
 * }
 * </pre>
 *
 * <p>
 * This will read the parse the contents of the file using the
 * <a href="http://tools.ietf.org/html/rfc4180" target="_blank">RFC 4180</a> format.
 * </p>
 *
 * <p>
 * To parse CSV input in a format like Excel, you write:
 * </p>
 *
 * <pre>
 * CSVParser parser = CSVParser.parse(csvData, CSVFormat.EXCEL);
 * for (CSVRecord csvRecord : parser) {
 *     ...
 * }
 * </pre>
 *
 * <p>
 * If the predefined formats don't match the format at hands, custom formats can be defined. More information about
 * customising CSVFormats is available in {@link CSVFormat CSVFormat Javadoc}.
 * </p>
 *
 * <h2>Parsing into memory</h2>
 * <p>
 * If parsing record wise is not desired, the contents of the input can be read completely into memory.
 * </p>
 *
 * <pre>
 * Reader in = new StringReader(&quot;a;b\nc;d&quot;);
 * CSVParser parser = new CSVParser(in, CSVFormat.EXCEL);
 * List&lt;CSVRecord&gt; list = parser.getRecords();
 * </pre>
 *
 * <p>
 * There are two constraints that have to be kept in mind:
 * </p>
 *
 * <ol>
 *     <li>Parsing into memory starts at the current position of the parser. If you have already parsed records from
 *     the input, those records will not end up in the in memory representation of your CSV data.</li>
 *     <li>Parsing into memory may consume a lot of system resources depending on the input. For example if you're
 *     parsing a 150MB file of CSV data the contents will be read completely into memory.</li>
 * </ol>
 *
 * <h2>Notes</h2>
 * <p>
 * Internal parser state is completely covered by the format and the reader-state.
 * </p>
 *
 * @see <a href="package-summary.html">package documentation for more details</a>
 

constructors for class `org.apache.commons.csv.CSVParser`: 
params: org.apache.commons.csv.CSVFormat format 
body:
```java
    public CSVParser(final Reader reader, final CSVFormat format) throws IOException {
        this(reader, format, 0, 1);
    }

```
params: org.apache.commons.csv.CSVFormat format 
body:
```java
    @SuppressWarnings("resource")
    public CSVParser(final Reader reader, final CSVFormat format, final long characterOffset, final long recordNumber)
        throws IOException {
        Objects.requireNonNull(reader, "reader");
        Objects.requireNonNull(format, "format");

        this.format = format.copy();
        this.lexer = new Lexer(format, new ExtendedBufferedReader(reader));
        this.csvRecordIterator = new CSVRecordIterator();
        this.headers = createHeaders();
        this.characterOffset = characterOffset;
        this.recordNumber = recordNumber - 1;
    }

```

parameters in constructors and focal method: 
class `org.apache.commons.csv.CSVFormat`:
api document : 
 * Specifies the format of a CSV file for parsing and writing.
 *
 * <h2>Using predefined formats</h2>
 *
 * <p>
 * You can use one of the predefined formats:
 * </p>
 *
 * <ul>
 * <li>{@link #DEFAULT}</li>
 * <li>{@link #EXCEL}</li>
 * <li>{@link #INFORMIX_UNLOAD}</li>
 * <li>{@link #INFORMIX_UNLOAD_CSV}</li>
 * <li>{@link #MYSQL}</li>
 * <li>{@link #RFC4180}</li>
 * <li>{@link #ORACLE}</li>
 * <li>{@link #POSTGRESQL_CSV}</li>
 * <li>{@link #POSTGRESQL_TEXT}</li>
 * <li>{@link #TDF}</li>
 * </ul>
 *
 * <p>
 * For example:
 * </p>
 *
 * <pre>
 * CSVParser parser = CSVFormat.EXCEL.parse(reader);
 * </pre>
 *
 * <p>
 * The {@link CSVParser} provides static methods to parse other input types, for example:
 * </p>
 *
 * <pre>
 * CSVParser parser = CSVParser.parse(file, StandardCharsets.US_ASCII, CSVFormat.EXCEL);
 * </pre>
 *
 * <h2>Defining formats</h2>
 *
 * <p>
 * You can extend a format by calling the {@code set} methods. For example:
 * </p>
 *
 * <pre>
 * CSVFormat.EXCEL.withNullString(&quot;N/A&quot;).withIgnoreSurroundingSpaces(true);
 * </pre>
 *
 * <h2>Defining column names</h2>
 *
 * <p>
 * To define the column names you want to use to access records, write:
 * </p>
 *
 * <pre>
 * CSVFormat.EXCEL.withHeader(&quot;Col1&quot;, &quot;Col2&quot;, &quot;Col3&quot;);
 * </pre>
 *
 * <p>
 * Calling {@link Builder#setHeader(String...)} lets you use the given names to address values in a {@link CSVRecord}, and assumes that your CSV source does not
 * contain a first record that also defines column names.
 *
 * If it does, then you are overriding this metadata with your names and you should skip the first record by calling
 * {@link Builder#setSkipHeaderRecord(boolean)} with {@code true}.
 * </p>
 *
 * <h2>Parsing</h2>
 *
 * <p>
 * You can use a format directly to parse a reader. For example, to parse an Excel file with columns header, write:
 * </p>
 *
 * <pre>
 * Reader in = ...;
 * CSVFormat.EXCEL.withHeader(&quot;Col1&quot;, &quot;Col2&quot;, &quot;Col3&quot;).parse(in);
 * </pre>
 *
 * <p>
 * For other input types, like resources, files, and URLs, use the static methods on {@link CSVParser}.
 * </p>
 *
 * <h2>Referencing columns safely</h2>
 *
 * <p>
 * If your source contains a header record, you can simplify your code and safely reference columns, by using {@link Builder#setHeader(String...)} with no
 * arguments:
 * </p>
 *
 * <pre>
 * CSVFormat.EXCEL.withHeader();
 * </pre>
 *
 * <p>
 * This causes the parser to read the first record and use its values as column names.
 *
 * Then, call one of the {@link CSVRecord} get method that takes a String column name argument:
 * </p>
 *
 * <pre>
 * String value = record.get(&quot;Col1&quot;);
 * </pre>
 *
 * <p>
 * This makes your code impervious to changes in column order in the CSV file.
 * </p>
 *
 * <h2>Serialization</h2>
 * <p>
 *   This class implements the {@link Serializable} interface with the following caveats:
 * </p>
 * <ul>
 *   <li>This class will no longer implement Serializable in 2.0.</li>
 *   <li>Serialization is not supported from one version to the next.</li>
 * </ul>
 * <p>
 *   The {@code serialVersionUID} values are:
 * </p>
 * <ul>
 *   <li>Version 1.10.0: {@code 2L}</li>
 *   <li>Version 1.9.0 through 1.0: {@code 1L}</li>
 * </ul>
 *
 * <h2>Notes</h2>
 * <p>
 * This class is immutable.
 * </p>
 * <p>
 * Not all settings are used for both parsing and writing.
 * </p>
 
constructor:
```java
    private CSVFormat(final Builder builder) {
        this.delimiter = builder.delimiter;
        this.quoteCharacter = builder.quoteCharacter;
        this.quoteMode = builder.quoteMode;
        this.commentMarker = builder.commentMarker;
        this.escapeCharacter = builder.escapeCharacter;
        this.ignoreSurroundingSpaces = builder.ignoreSurroundingSpaces;
        this.allowMissingColumnNames = builder.allowMissingColumnNames;
        this.ignoreEmptyLines = builder.ignoreEmptyLines;
        this.recordSeparator = builder.recordSeparator;
        this.nullString = builder.nullString;
        this.headerComments = builder.headerComments;
        this.headers = builder.headers;
        this.skipHeaderRecord = builder.skipHeaderRecord;
        this.ignoreHeaderCase = builder.ignoreHeaderCase;
        this.trailingDelimiter = builder.trailingDelimiter;
        this.trim = builder.trim;
        this.autoFlush = builder.autoFlush;
        this.quotedNullString = builder.quotedNullString;
        this.duplicateHeaderMode = builder.duplicateHeaderMode;
        validate();
    }

    private CSVFormat(final String delimiter, final Character quoteChar, final QuoteMode quoteMode, final Character commentStart, final Character escape,
            final boolean ignoreSurroundingSpaces, final boolean ignoreEmptyLines, final String recordSeparator, final String nullString,
            final Object[] headerComments, final String[] header, final boolean skipHeaderRecord, final boolean allowMissingColumnNames,
            final boolean ignoreHeaderCase, final boolean trim, final boolean trailingDelimiter, final boolean autoFlush,
            final DuplicateHeaderMode duplicateHeaderMode) {
        this.delimiter = delimiter;
        this.quoteCharacter = quoteChar;
        this.quoteMode = quoteMode;
        this.commentMarker = commentStart;
        this.escapeCharacter = escape;
        this.ignoreSurroundingSpaces = ignoreSurroundingSpaces;
        this.allowMissingColumnNames = allowMissingColumnNames;
        this.ignoreEmptyLines = ignoreEmptyLines;
        this.recordSeparator = recordSeparator;
        this.nullString = nullString;
        this.headerComments = toStringArray(headerComments);
        this.headers = clone(header);
        this.skipHeaderRecord = skipHeaderRecord;
        this.ignoreHeaderCase = ignoreHeaderCase;
        this.trailingDelimiter = trailingDelimiter;
        this.trim = trim;
        this.autoFlush = autoFlush;
        this.quotedNullString = quoteCharacter + nullString + quoteCharacter;
        this.duplicateHeaderMode = duplicateHeaderMode;
        validate();
    }

```

existing test class: 
```java
/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.csv;

import static org.apache.commons.csv.Constants.CR;
import static org.apache.commons.csv.Constants.CRLF;
import static org.apache.commons.csv.Constants.LF;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UncheckedIOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.apache.commons.io.input.BOMInputStream;
import org.apache.commons.io.input.BrokenInputStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * CSVParserTest
 *
 * The test are organized in three different sections: The 'setter/getter' section, the lexer section and finally the
 * parser section. In case a test fails, you should follow a top-down approach for fixing a potential bug (its likely
 * that the parser itself fails if the lexer has problems...).
 */
public class CSVParserTest {

    private static final Charset UTF_8 = StandardCharsets.UTF_8;

    private static final String UTF_8_NAME = UTF_8.name();

    private static final String CSV_INPUT = "a,b,c,d\n" + " a , b , 1 2 \n" + "\"foo baar\", b,\n"
    // + " \"foo\n,,\n\"\",,\n\\\"\",d,e\n";
        + "   \"foo\n,,\n\"\",,\n\"\"\",d,e\n"; // changed to use standard CSV escaping

    private static final String CSV_INPUT_1 = "a,b,c,d";

    private static final String CSV_INPUT_2 = "a,b,1 2";

    private static final String[][] RESULT = {{"a", "b", "c", "d"}, {"a", "b", "1 2"}, {"foo baar", "b", ""}, {"foo\n,,\n\",,\n\"", "d", "e"}};

    // CSV with no header comments
    static private final String CSV_INPUT_NO_COMMENT = "A,B"+CRLF+"1,2"+CRLF;

    // CSV with a header comment
    static private final String CSV_INPUT_HEADER_COMMENT = "# header comment" + CRLF + "A,B" + CRLF + "1,2" + CRLF;

    // CSV with a single line header and trailer comment
    static private final String CSV_INPUT_HEADER_TRAILER_COMMENT = "# header comment" + CRLF + "A,B" + CRLF + "1,2" + CRLF + "# comment";

    // CSV with a multi-line header and trailer comment
    static private final String CSV_INPUT_MULTILINE_HEADER_TRAILER_COMMENT = "# multi-line" + CRLF + "# header comment" + CRLF + "A,B" + CRLF + "1,2" + CRLF + "# multi-line" + CRLF + "# comment";

    // Format with auto-detected header
    static private final CSVFormat FORMAT_AUTO_HEADER = CSVFormat.Builder.create(CSVFormat.DEFAULT).setCommentMarker('#').setHeader().build();

    // Format with explicit header
    static private final CSVFormat FORMAT_EXPLICIT_HEADER = CSVFormat.Builder.create(CSVFormat.DEFAULT)
            .setSkipHeaderRecord(true)
......
        CSVParser parser = CSVParser.parse(code, format);

        CSVRecord record;
        assertEquals(0, parser.getRecordNumber());

        assertNotNull(record = parser.nextRecord());
        assertEquals(1, record.getRecordNumber());
        assertEquals(code.indexOf('a'), record.getCharacterPosition());

        assertNotNull(record = parser.nextRecord());
        assertEquals(2, record.getRecordNumber());
        assertEquals(code.indexOf('1'), record.getCharacterPosition());

        assertNotNull(record = parser.nextRecord());
        final long positionRecord3 = record.getCharacterPosition();
        assertEquals(3, record.getRecordNumber());
        assertEquals(code.indexOf("'A"), record.getCharacterPosition());
        assertEquals("A" + lineSeparator + "A", record.get(0));
        assertEquals("B" + lineSeparator + "B", record.get(1));
        assertEquals("CC", record.get(2));

        assertNotNull(record = parser.nextRecord());
        assertEquals(4, record.getRecordNumber());
        assertEquals(code.indexOf('\u00c4'), record.getCharacterPosition());

        assertNotNull(record = parser.nextRecord());
        assertEquals(5, record.getRecordNumber());
        assertEquals(code.indexOf("EOF"), record.getCharacterPosition());

        parser.close();

        // now try to read starting at record 3
        parser = new CSVParser(new StringReader(code.substring((int) positionRecord3)), format, positionRecord3, 3);

        assertNotNull(record = parser.nextRecord());
        assertEquals(3, record.getRecordNumber());
        assertEquals(code.indexOf("'A"), record.getCharacterPosition());
        assertEquals("A" + lineSeparator + "A", record.get(0));
        assertEquals("B" + lineSeparator + "B", record.get(1));
        assertEquals("CC", record.get(2));

        assertNotNull(record = parser.nextRecord());
        assertEquals(4, record.getRecordNumber());
        assertEquals(code.indexOf('\u00c4'), record.getCharacterPosition());
        assertEquals("\u00c4", record.get(0));

        parser.close();
    }

}
```

invoke examples of focal method: 
example 1:
```java
        private CSVRecord getNextRecord() {
            try {
                return CSVParser.this.nextRecord();
        }
```
example 2:
```java
    private Headers createHeaders() throws IOException {
        Map<String, Integer> hdrMap = null;
        List<String> headerNames = null;
        final String[] formatHeader = this.format.getHeader();
        if (formatHeader != null) {
            hdrMap = createEmptyHeaderMap();
            String[] headerRecord = null;
            if (formatHeader.length == 0) {
                final CSVRecord nextRecord = this.nextRecord();
                if (this.format.getSkipHeaderRecord()) {
                    final CSVRecord nextRecord = this.nextRecord();
    }
```



@input{test class template}
```java
package org.apache.commons.csv;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
// Add other imports if necessary

@ExtendWith(MockitoExtension.class)
class CSVParser_nextRecord_Test {
    @BeforeAll
    static void setupBeforeAll() {
    }

    @BeforeEach
    void setupBeforeEach() {
    }

    @AfterEach
    void teardownAfterEach() {
    }

    @AfterAll
    static void teardownAfterAll() {
    }
}
```

@output{initial test class}: now consider the invoke examples output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.