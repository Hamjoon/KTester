As a professional Java software testing expert, your task is to generate test cases for a focal method.

Guidelines:
1. Analyze the method's functionality and identify possible input combinations including normal cases and boundary conditions.
2. Review the provided existing test cases. Avoid duplication and create new test cases for untested input combinations.

Context Provided:
1. The focal method that needs testing.
2. Relevant code and metadata for the class containing the method, including fields and other methods.
3. The existing test cases (in JSON format) to help avoid redundancy.

Output Instructions:
- Organize the test cases by the name of the test function (group).
- Each test case must include:
    - input: A list of parameter names and values. Use an empty list `[]` if no input is required.
    - expected: The return value or expected observable behavior.
    - description: A clear and concise explanation of the scenario and the intent of the test case.

Maintain the following strict JSON format:
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
Here is the code of focal method, `printWithQuotes(Object, CharSequence, Appendable, boolean)`, with the focal class `CSVFormat`:
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
    private void printWithQuotes(final Object object, final CharSequence charSeq, final Appendable out, final boolean newRecord) throws IOException {
        boolean quote = false;
        int start = 0;
        int pos = 0;
        final int len = charSeq.length();
        final char[] delim = getDelimiterString().toCharArray();
        final int delimLength = delim.length;
        final char quoteChar = getQuoteCharacter().charValue();
        // If escape char not specified, default to the quote char
        // This avoids having to keep checking whether there is an escape character
        // at the cost of checking against quote twice
        final char escapeChar = (isEscapeCharacterSet()) ? getEscapeCharacter().charValue() : quoteChar;
        QuoteMode quoteModePolicy = getQuoteMode();
        if (quoteModePolicy == null) {
            quoteModePolicy = QuoteMode.MINIMAL;
        }
        switch (quoteModePolicy) {
            case ALL :
            case ALL_NON_NULL :
                quote = true;
                break;
            case NON_NUMERIC :
                quote = !(object instanceof Number);
                break;
            case NONE :
                // Use the existing escaping code
                printWithEscapes(charSeq, out);
                return;
            case MINIMAL :
                if (len <= 0) {
                    // always quote an empty token that is the first
                    // on the line, as it may be the only thing on the
                    // line. If it were not quoted in that case,
                    // an empty line has no tokens.
                    if (newRecord) {
                        quote = true;
                    }
                } else {
                    char c = charSeq.charAt(pos);
                    if (c <= COMMENT) {
                        // Some other chars at the start of a value caused the parser to fail, so for now
                        // encapsulate if we start in anything less than '#'. We are being conservative
                        // by including the default comment char too.
                        quote = true;
                    } else {
                        while (pos < len) {
                            c = charSeq.charAt(pos);
                            if (((((c == LF) || (c == CR)) || (c == quoteChar)) || (c == escapeChar)) || isDelimiter(c, charSeq, pos, delim, delimLength)) {
                                quote = true;
                                break;
                            }
                            pos++;
                        } 
                        if (!quote) {
                            pos = len - 1;
                            c = charSeq.charAt(pos);
                            // Some other chars at the end caused the parser to fail, so for now
                            // encapsulate if we end in anything less than ' '
                            if (isTrimChar(c)) {
                                quote = true;
                            }
                        }
                    }
                }
                if (!quote) {
                    // no encapsulation needed - write out the original value
                    out.append(charSeq, start, len);
                    return;
                }
                break;
            default :
                throw new IllegalStateException("Unexpected Quote value: " + quoteModePolicy);
        }
        if (!quote) {
            // no encapsulation needed - write out the original value
            out.append(charSeq, start, len);
            return;
        }
        // we hit something that needed encapsulation
        out.append(quoteChar);
        // Pick up where we left off: pos should be positioned on the first character that caused
        // the need for encapsulation.
        while (pos < len) {
            final char c = charSeq.charAt(pos);
            if ((c == quoteChar) || (c == escapeChar)) {
                // write out the chunk up until this point
                out.append(charSeq, start, pos);
                out.append(escapeChar);// now output the escape
    
                start = pos;// and restart with the matched char
    
            }
            pos++;
        } 
        // write the last segment
        out.append(charSeq, start, pos);
        out.append(quoteChar);
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
    private boolean isDelimiter(final char ch, final CharSequence charSeq, final int startIndex, final char[] delimiter, final int delimiterLength);
    public boolean isEscapeCharacterSet();
    public boolean isNullStringSet();
    public boolean isQuoteCharacterSet();
    private static boolean isTrimChar(final char ch);
    private static boolean isTrimChar(final CharSequence charSequence, final int pos);
    public static CSVFormat newFormat(final char delimiter);
    public CSVParser parse(final Reader reader) throws IOException;
    @SuppressWarnings("resource")
public CSVPrinter print(final File out, final Charset charset) throws IOException;
    public CSVPrinter print(final Appendable out) throws IOException;
    public synchronized void print(final Object value, final Appendable out, final boolean newRecord) throws IOException;
    @SuppressWarnings("resource")
public CSVPrinter print(final Path out, final Charset charset) throws IOException;
    public synchronized void printRecord(final Appendable appendable, final Object... values) throws IOException;
    private void printWithEscapes(final Reader reader, final Appendable appendable) throws IOException;
    private void printWithEscapes(final CharSequence charSeq, final Appendable appendable) throws IOException;
    public CSVPrinter printer() throws IOException;
    public synchronized void println(final Appendable appendable) throws IOException;
    @Override
public String toString();
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