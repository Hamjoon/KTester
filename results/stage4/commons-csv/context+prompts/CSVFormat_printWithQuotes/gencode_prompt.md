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
class `org.apache.commons.csv.QuoteMode`:
	dependent fields:
		org.apache.commons.csv.QuoteMode org.apache.commons.csv.QuoteMode.ALL;
		org.apache.commons.csv.QuoteMode org.apache.commons.csv.QuoteMode.NONE;
		org.apache.commons.csv.QuoteMode org.apache.commons.csv.QuoteMode.ALL_NON_NULL;
		org.apache.commons.csv.QuoteMode org.apache.commons.csv.QuoteMode.MINIMAL;
		org.apache.commons.csv.QuoteMode org.apache.commons.csv.QuoteMode.NON_NUMERIC;
	
class `org.apache.commons.csv.Constants`:
	dependent fields:
		char org.apache.commons.csv.Constants.COMMENT;
		char org.apache.commons.csv.Constants.LF;
		char org.apache.commons.csv.Constants.CR;
	
class `org.apache.commons.csv.CSVFormat`:
	related functions:
		method `private synchronized void print(Object, CharSequence, Appendable, boolean)` returns `void`, related with `org.apache.commons.csv.CSVFormat.printWithQuotes(Object, CharSequence, Appendable, boolean)`;
		method `private void printWithEscapes(Reader, Appendable)` returns `void`, related with `org.apache.commons.csv.CSVFormat.printWithQuotes(Object, CharSequence, Appendable, boolean)`;
		method `private static boolean isTrimChar(CharSequence, int)` returns `boolean`, related with `org.apache.commons.csv.CSVFormat.printWithQuotes(Object, CharSequence, Appendable, boolean)`, api document:  Same test as in as {@link String#trim()}. ;
		method `private static boolean containsLineBreak(String)` returns `boolean`, related with `org.apache.commons.csv.CSVFormat.printWithQuotes(Object, CharSequence, Appendable, boolean)`, api document: 
     * Returns true if the given string contains a line break character.
     *
     * @param source the string to check.
     *
     * @return true if {@code c} contains a line break character.
     ;
		method `private void printWithEscapes(CharSequence, Appendable)` returns `void`, related with `org.apache.commons.csv.CSVFormat.printWithQuotes(Object, CharSequence, Appendable, boolean)`;
		method `private void printWithQuotes(Reader, Appendable)` returns `void`, related with `org.apache.commons.csv.CSVFormat.printWithQuotes(Object, CharSequence, Appendable, boolean)`, api document: 
     * Always use quotes unless QuoteMode is NONE, so we not have to look ahead.
     *
     * @param reader What to print
     * @param appendable Where to print it
     * @throws IOException If an I/O error occurs
     ;
class `org.apache.commons.csv.CSVPrinter`:
	related functions:
		method `public synchronized void printComment(String)` returns `void`, related with `org.apache.commons.csv.CSVFormat.printWithQuotes(Object, CharSequence, Appendable, boolean)`, api document: 
     * Prints a comment on a new line among the delimiter separated values.
     *
     * <p>
     * Comments will always begin on a new line and occupy at least one full line. The character specified to start
     * comments and a space will be inserted at the beginning of each new line in the comment.
     * </p>
     *
     * <p>
     * If comments are disabled in the current CSV format this method does nothing.
     * </p>
     *
     * <p>This method detects line breaks inside the comment string and inserts {@link CSVFormat#getRecordSeparator()}
     * to start a new line of the comment. Note that this might produce unexpected results for formats that do not use
     * line breaks as record separator.</p>
     *
     * @param comment
     *            the comment to output
     * @throws IOException
     *             If an I/O error occurs
     ;
class `org.apache.commons.csv.CSVParser`:
	related functions:
		method `private boolean isStrictQuoteMode()` returns `boolean`, related with `org.apache.commons.csv.CSVFormat.printWithQuotes(Object, CharSequence, Appendable, boolean)`, api document: 
     * Tests whether the format's {@link QuoteMode} is {@link QuoteMode#ALL_NON_NULL} or {@link QuoteMode#NON_NUMERIC}.
     *
     * @return true if the format's {@link QuoteMode} is {@link QuoteMode#ALL_NON_NULL} or
     *         {@link QuoteMode#NON_NUMERIC}.
     ;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.