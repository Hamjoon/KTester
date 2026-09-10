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
Here is the code of focal method, `nextRecord()`, with the focal class `CSVParser`:
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
 

api document of method nextRecord(): 

     * Parses the next record from the current point in the stream.
     *
     * @return the record as an array of values, or {@code null} if the end of the stream has been reached
     * @throws IOException
     *             on parse error or input read-failure
     

return type: 
org.apache.commons.csv.CSVRecord

dependent classes: 
class `org.apache.commons.csv.Token.Type`:
	dependent fields:
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.TOKEN;
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.EOF;
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.EORECORD;
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.INVALID;
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.COMMENT;
	
class `org.apache.commons.csv.Token`:
	dependent fields:
		java.lang.StringBuilder org.apache.commons.csv.Token.content;
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.type;
		boolean org.apache.commons.csv.Token.isReady;
	related functions:
		method `public String toString()` returns `java.lang.String`, related with `org.apache.commons.csv.CSVParser.nextRecord()`, api document: 
     * Eases IDE debugging.
     *
     * @return a string helpful for debugging.
     ;
		method ` void reset()` returns `void`, related with `org.apache.commons.csv.CSVParser.nextRecord()`;
class `org.apache.commons.csv.Constants`:
	dependent fields:
		char org.apache.commons.csv.Constants.LF;
		java.lang.String[] org.apache.commons.csv.Constants.EMPTY_STRING_ARRAY;
	
class `org.apache.commons.csv.CSVParser`:
	dependent fields:
		long org.apache.commons.csv.CSVParser.characterOffset;
		java.util.List<java.lang.String> org.apache.commons.csv.CSVParser.recordList;
		org.apache.commons.csv.Lexer org.apache.commons.csv.CSVParser.lexer;
		org.apache.commons.csv.Token org.apache.commons.csv.CSVParser.reusableToken;
		java.lang.String org.apache.commons.csv.CSVParser.trailerComment;
		long org.apache.commons.csv.CSVParser.recordNumber;
	related functions:
		method `private void addRecordValue(boolean)` returns `void`, related with `org.apache.commons.csv.CSVParser.nextRecord()`;
class `org.apache.commons.csv.Lexer`:
	related functions:
		method ` Token nextToken(Token)` returns `org.apache.commons.csv.Token`, related with `org.apache.commons.csv.CSVParser.nextRecord()`, api document: 
     * Returns the next token.
     * <p>
     * A token corresponds to a term, a record change or an end-of-file indicator.
     * </p>
     *
     * @param token
     *            an existing Token object to reuse. The caller is responsible to initialize the Token.
     * @return the next token found.
     * @throws IOException on stream access error.
     ;
		method `private Token parseEncapsulatedToken(Token)` returns `org.apache.commons.csv.Token`, related with `org.apache.commons.csv.CSVParser.nextRecord()`, api document: 
     * Parses an encapsulated token.
     * <p>
     * Encapsulated tokens are surrounded by the given encapsulating-string. The encapsulator itself might be included
     * in the token using a doubling syntax (as "", '') or using escaping (as in \", \'). Whitespaces before and after
     * an encapsulated token are ignored. The token is finished when one of the following conditions become true:
     * </p>
     * <ul>
     * <li>an unescaped encapsulator has been reached, and is followed by optional whitespace then:</li>
     * <ul>
     * <li>delimiter (TOKEN)</li>
     * <li>end of line (EORECORD)</li>
     * </ul>
     * <li>end of stream has been reached (EOF)</li> </ul>
     *
     * @param token
     *            the current token
     * @return a valid token object
     * @throws IOException
     *             on invalid state: EOF before closing encapsulator or invalid character before delimiter or EOL
     ;
		method `private Token parseSimpleToken(Token, int)` returns `org.apache.commons.csv.Token`, related with `org.apache.commons.csv.CSVParser.nextRecord()`, api document: 
     * Parses a simple token.
     * <p>
     * Simple token are tokens which are not surrounded by encapsulators. A simple token might contain escaped
     * delimiters (as \, or \;). The token is finished when one of the following conditions become true:
     * </p>
     * <ul>
     * <li>end of line has been reached (EORECORD)</li>
     * <li>end of stream has been reached (EOF)</li>
     * <li>an unescaped delimiter has been reached (TOKEN)</li>
     * </ul>
     *
     * @param token
     *            the current token
     * @param ch
     *            the current character
     * @return the filled token
     * @throws IOException
     *             on stream access error
     ;
class `org.apache.commons.csv.ExtendedBufferedReader`:
	related functions:
		method `public String readLine()` returns `java.lang.String`, related with `org.apache.commons.csv.CSVParser.nextRecord()`, api document: 
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
class `org.apache.commons.csv.CSVFormat`:
	related functions:
		method `public String toString()` returns `java.lang.String`, related with `org.apache.commons.csv.CSVParser.nextRecord()`;
		method `private void printWithEscapes(Reader, Appendable)` returns `void`, related with `org.apache.commons.csv.CSVParser.nextRecord()`;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.