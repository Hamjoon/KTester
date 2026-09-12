You are a professional Java software testing expert. Your task is to generate high-quality unit test code for a focal method, using **JUnit5** and **Mockito** libraries. 

You'll be provided with:
1. The focal method to test.
2. The target class's structure (fields and methods).
3. A pre-defined test class framework with `@Before`/`@After` methods.
4. Test cases in JSON format.
5. Any relevant context.

**Requirements:**

Code Quality:
- Include clear comments explaining each test scenario.
- Ensure code is clean, understandable, and maintainable.
- Do not directly access private methods or fields. **Use reflection if the focal method is private.**
- Include all necessary imports; code must be free of syntax errors.

**Test Structure:**
- Follow the given test class framework; do not modify `@Before`/`@After` methods.
- Create one test function per test case group, ensuring all cases are covered.
- Use parameterized tests where appropriate.
- Reinitialize variables inside test functions if `@Setup` values don't fit.
- Properly mock and set up dependencies.

@input{focal method and target class}
Here is the code of focal method, `toString()`, with the focal class `CSVFormat`:
```Java
package org.apache.commons.csv;
import java.util.Set;
import java.sql.SQLException;
import static org.apache.commons.csv.Constants.PIPE;
import java.io.Reader;
import java.sql.ResultSetMetaData;
import java.nio.file.Files;
import static org.apache.commons.csv.Constants.BACKSLASH;
import java.io.StringWriter;
import java.sql.ResultSet;
import static org.apache.commons.csv.Constants.EMPTY;
import static org.apache.commons.csv.Constants.SP;
import java.util.HashSet;
import static org.apache.commons.csv.Constants.COMMA;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.charset.Charset;
import static org.apache.commons.csv.Constants.DOUBLE_QUOTE_CHAR;
import static org.apache.commons.csv.Constants.CR;
import static org.apache.commons.csv.Constants.COMMENT;
import java.io.FileOutputStream;
import static org.apache.commons.csv.Constants.CRLF;
import static org.apache.commons.csv.Constants.TAB;
import java.io.Writer;
import java.util.Objects;
import static org.apache.commons.csv.Constants.LF;
import java.io.OutputStreamWriter;
import java.io.File;
import java.io.Serializable;
import java.util.Arrays;
public final class CSVFormat implements Serializable {
    public static final CSVFormat DEFAULT = new CSVFormat(COMMA, DOUBLE_QUOTE_CHAR, null, null, null, false, true, CRLF, null, null, null, false, false, false, false, false, false, DuplicateHeaderMode.ALLOW_ALL);
    public static final CSVFormat EXCEL = DEFAULT.builder().setIgnoreEmptyLines(false).setAllowMissingColumnNames(true).build();
    public static final CSVFormat INFORMIX_UNLOAD = DEFAULT.builder().setDelimiter(PIPE).setEscape(BACKSLASH).setQuote(DOUBLE_QUOTE_CHAR).setRecordSeparator(LF).build();
    public static final CSVFormat INFORMIX_UNLOAD_CSV = DEFAULT.builder().setDelimiter(COMMA).setQuote(DOUBLE_QUOTE_CHAR).setRecordSeparator(LF).build();
    public static final CSVFormat MONGODB_CSV = DEFAULT.builder().setDelimiter(COMMA).setEscape(DOUBLE_QUOTE_CHAR).setQuote(DOUBLE_QUOTE_CHAR).setQuoteMode(QuoteMode.MINIMAL).setSkipHeaderRecord(false).build();
    public static final CSVFormat MONGODB_TSV = DEFAULT.builder().setDelimiter(TAB).setEscape(DOUBLE_QUOTE_CHAR).setQuote(DOUBLE_QUOTE_CHAR).setQuoteMode(QuoteMode.MINIMAL).setSkipHeaderRecord(false).build();
    public static final CSVFormat MYSQL = DEFAULT.builder().setDelimiter(TAB).setEscape(BACKSLASH).setIgnoreEmptyLines(false).setQuote(null).setRecordSeparator(LF).setNullString(Constants.SQL_NULL_STRING).setQuoteMode(QuoteMode.ALL_NON_NULL).build();
    public static final CSVFormat ORACLE = DEFAULT.builder().setDelimiter(COMMA).setEscape(BACKSLASH).setIgnoreEmptyLines(false).setQuote(DOUBLE_QUOTE_CHAR).setNullString(Constants.SQL_NULL_STRING).setTrim(true).setRecordSeparator(System.lineSeparator()).setQuoteMode(QuoteMode.MINIMAL).build();
    public static final CSVFormat POSTGRESQL_CSV = DEFAULT.builder().setDelimiter(COMMA).setEscape(null).setIgnoreEmptyLines(false).setQuote(DOUBLE_QUOTE_CHAR).setRecordSeparator(LF).setNullString(EMPTY).setQuoteMode(QuoteMode.ALL_NON_NULL).build();
    public static final CSVFormat POSTGRESQL_TEXT = DEFAULT.builder().setDelimiter(TAB).setEscape(BACKSLASH).setIgnoreEmptyLines(false).setQuote(null).setRecordSeparator(LF).setNullString(Constants.SQL_NULL_STRING).setQuoteMode(QuoteMode.ALL_NON_NULL).build();
    public static final CSVFormat RFC4180 = DEFAULT.builder().setIgnoreEmptyLines(false).build();
    public static final CSVFormat TDF = DEFAULT.builder().setDelimiter(TAB).setIgnoreSurroundingSpaces(true).build();
    private final Character commentMarker;
    private final String delimiter;
    private final Character escapeCharacter;
    private final String[] headers;
    private final String[] headerComments;
    private final String nullString;
    private final Character quoteCharacter;
    private final QuoteMode quoteMode;
    private final String recordSeparator;
    private final boolean skipHeaderRecord;
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Delimiter=<").append(delimiter).append('>');
        if (isEscapeCharacterSet()) {
            sb.append(' ');
            sb.append("Escape=<").append(escapeCharacter).append('>');
        }
        if (isQuoteCharacterSet()) {
            sb.append(' ');
            sb.append("QuoteChar=<").append(quoteCharacter).append('>');
        }
        if (quoteMode != null) {
            sb.append(' ');
            sb.append("QuoteMode=<").append(quoteMode).append('>');
        }
        if (isCommentMarkerSet()) {
            sb.append(' ');
            sb.append("CommentStart=<").append(commentMarker).append('>');
        }
        if (isNullStringSet()) {
            sb.append(' ');
            sb.append("NullString=<").append(nullString).append('>');
        }
        if (recordSeparator != null) {
            sb.append(' ');
            sb.append("RecordSeparator=<").append(recordSeparator).append('>');
        }
        if (getIgnoreEmptyLines()) {
            sb.append(" EmptyLines:ignored");
        }
        if (getIgnoreSurroundingSpaces()) {
            sb.append(" SurroundingSpaces:ignored");
        }
        if (getIgnoreHeaderCase()) {
            sb.append(" IgnoreHeaderCase:ignored");
        }
        sb.append(" SkipHeaderRecord:").append(skipHeaderRecord);
        if (headerComments != null) {
            sb.append(' ');
            sb.append("HeaderComments:").append(Arrays.toString(headerComments));
        }
        if (headers != null) {
            sb.append(' ');
            sb.append("Header:").append(Arrays.toString(headers));
        }
        return sb.toString();
    }
public Builder builder();
    @SafeVarargs
static <T> T[] clone(final T... values);
    CSVFormat copy();
    @Override
public boolean equals(final Object obj);
    public String format(final Object... values);
    public boolean getAllowMissingColumnNames();
    public boolean getAutoFlush();
    public Character getCommentMarker();
    public String getDelimiterString();
    public DuplicateHeaderMode getDuplicateHeaderMode();
    public Character getEscapeCharacter();
    public String[] getHeader();
    public String[] getHeaderComments();
    public boolean getIgnoreEmptyLines();
    public boolean getIgnoreHeaderCase();
    public boolean getIgnoreSurroundingSpaces();
    public String getNullString();
    public Character getQuoteCharacter();
    public QuoteMode getQuoteMode();
    public String getRecordSeparator();
    public boolean getSkipHeaderRecord();
    public boolean getTrailingDelimiter();
    public boolean getTrim();
    @Override
public int hashCode();
    static boolean isBlank(final String value);
    public boolean isCommentMarkerSet();
    public boolean isEscapeCharacterSet();
    public boolean isNullStringSet();
    public boolean isQuoteCharacterSet();
    public static CSVFormat newFormat(final char delimiter);
    public CSVParser parse(final Reader reader) throws IOException;
    @SuppressWarnings("resource")
public CSVPrinter print(final File out, final Charset charset) throws IOException;
    public CSVPrinter print(final Appendable out) throws IOException;
    public synchronized void print(final Object value, final Appendable out, final boolean newRecord) throws IOException;
    @SuppressWarnings("resource")
public CSVPrinter print(final Path out, final Charset charset) throws IOException;
    public synchronized void printRecord(final Appendable appendable, final Object... values) throws IOException;
    public CSVPrinter printer() throws IOException;
    public synchronized void println(final Appendable appendable) throws IOException;
    static String[] toStringArray(final Object[] values);
    static CharSequence trim(final CharSequence charSequence);
    String trim(final String value);
    public static CSVFormat valueOf(final String format);
}
```

@input{initial test class framework}
```Java
<initial_class>
```

@input{existing test case}
here's the existing test cases:
```json
<cases_json>
```


@input{context information}

api document of class org.apache.commons.csv.CSVFormat: 

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
 

dependent classes: 
class `org.apache.commons.csv.CSVFormat`:
	dependent fields:
		java.lang.Character org.apache.commons.csv.CSVFormat.commentMarker;
		java.lang.String org.apache.commons.csv.CSVFormat.delimiter;
		org.apache.commons.csv.QuoteMode org.apache.commons.csv.CSVFormat.quoteMode;
		java.lang.Character org.apache.commons.csv.CSVFormat.quoteCharacter;
		java.lang.String[] org.apache.commons.csv.CSVFormat.headers;
		java.lang.String org.apache.commons.csv.CSVFormat.nullString;
		java.lang.String org.apache.commons.csv.CSVFormat.recordSeparator;
		boolean org.apache.commons.csv.CSVFormat.skipHeaderRecord;
		java.lang.String[] org.apache.commons.csv.CSVFormat.headerComments;
		java.lang.Character org.apache.commons.csv.CSVFormat.escapeCharacter;
	related functions:
		method `private void validate()` returns `void`, related with `org.apache.commons.csv.CSVFormat.toString()`, api document: 
     * Verifies the validity and consistency of the attributes, and throws an {@link IllegalArgumentException} if necessary.
     * <p>
     * Because an instance can be used for both writing an parsing, not all conditions can be tested here. For example allowMissingColumnNames is only used for
     * parsing, so it cannot be used here.
     * </p>
     *
     * @throws IllegalArgumentException Throw when any attribute is invalid or inconsistent with other attributes.
     ;
		method `public synchronized void print(Object, Appendable, boolean)` returns `void`, related with `org.apache.commons.csv.CSVFormat.toString()`, api document: 
     * Prints the {@code value} as the next value on the line to {@code out}. The value will be escaped or encapsulated as needed. Useful when one wants to
     * avoid creating CSVPrinters. Trims the value if {@link #getTrim()} is true.
     *
     * @param value     value to output.
     * @param out       where to print the value.
     * @param newRecord if this a new record.
     * @throws IOException If an I/O error occurs.
     * @since 1.4
     ;
		method `private void printWithEscapes(Reader, Appendable)` returns `void`, related with `org.apache.commons.csv.CSVFormat.toString()`;
		method `public boolean equals(Object)` returns `boolean`, related with `org.apache.commons.csv.CSVFormat.toString()`;
		method `private synchronized void print(Object, CharSequence, Appendable, boolean)` returns `void`, related with `org.apache.commons.csv.CSVFormat.toString()`;
class `org.apache.commons.csv.ExtendedBufferedReader`:
	related functions:
		method `public String readLine()` returns `java.lang.String`, related with `org.apache.commons.csv.CSVFormat.toString()`, api document: 
     * Gets the next line, dropping the line terminator(s). This method should only be called when processing a
     * comment, otherwise information can be lost.
     * <p>
     * Increments {@link #eolCounter} and updates {@link #position}.
     * </p>
     * <p>
     * Sets {@link #lastChar} to {@link Constants#END_OF_STREAM} at EOF, otherwise the last EOL character.
     * </p>
     *
     * @return the line that was read, or null if reached EOF.
     ;
class `org.apache.commons.csv.CSVParser`:
	related functions:
		method ` CSVRecord nextRecord()` returns `org.apache.commons.csv.CSVRecord`, related with `org.apache.commons.csv.CSVFormat.toString()`, api document: 
     * Parses the next record from the current point in the stream.
     *
     * @return the record as an array of values, or {@code null} if the end of the stream has been reached
     * @throws IOException
     *             on parse error or input read-failure
     ;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.