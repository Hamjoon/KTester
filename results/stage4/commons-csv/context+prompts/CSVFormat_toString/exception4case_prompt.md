You are a professional software tester. Write test cases for a focal method in a target class.

**Instructions:**
1. Analyze method behavior and identify ALL possible exception scenarios
2. Design test cases that trigger each identified exception
3. Follow existing test case structure, Avoid duplication and create new test cases for uncovered exception scenarios

Test Case Format:
1. Group test cases by test function name
2. Each test case MUST include:
   - Input parameters with values (use empty list `[]` if none)
   - Expected exception or observable behavior
   - Clear description of test scenario
3. Return empty array if no exception scenarios found

Output Schema (STRICT JSON format):
```json
[
    {
        "group": "test name",
        "cases": [
            {
                "input": [
                    {
                        "parameter": "param name",
                        "value": "param value"
                    }
                ],
                "expected": "expected exception or behavior",
                "description": "test scenario description"
            }
        ]
    },
    {...}
]
```

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
@input{existing test case}
here's the existing test cases:
```json
<cases_json>
```

@output{test cases}: output the test cases in the JSON format: