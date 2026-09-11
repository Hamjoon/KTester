


You are an experienced Java developer tasked with fixing a piece of code that contains execution errors. Your task is to resolve these issues after analyzing the code and its execution feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any logical errors.
2. Analyze the execution feedback to understand the specific errors and their locations within the code.
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
import java.io.IOException;
import java.io.StringReader;

@ExtendWith(MockitoExtension.class)
class CSVParser_nextRecord_Test {

    private CSVParser parser;

    private CSVFormat format;

    @BeforeEach
    void setupBeforeEach() throws IOException {
        // Initialize the CSVFormat and CSVParser before each test
        format = CSVFormat.DEFAULT.withHeader();
        String csvData = "a,b,c,d\n1,2,3,4\n5,6,7,8\n";
        parser = new CSVParser(new StringReader(csvData), format);
    }

    @AfterEach
    void teardownAfterEach() throws IOException {
        // Cleanup resources after each test
        if (parser != null) {
            parser.close();
        }
    }

    @Test
    void testNextRecord() throws IOException {
        // Given
        CSVRecord record;
        // When
        record = parser.nextRecord();
        // Then
        assertNotNull(record);
        assertEquals(1, record.getRecordNumber());
        assertEquals("1", record.get(0));
        assertEquals("2", record.get(1));
        assertEquals("3", record.get(2));
        assertEquals("4", record.get(3));
        // When
        record = parser.nextRecord();
        // Then
        assertNotNull(record);
        assertEquals(2, record.getRecordNumber());
        assertEquals("5", record.get(0));
        assertEquals("6", record.get(1));
        assertEquals("7", record.get(2));
        assertEquals("8", record.get(3));
        // When
        record = parser.nextRecord();
        // Then
        // No more records should be present
        assertNull(record);
    }

    @Test
    void testNextRecord_ValidTokenProcessing() throws IOException {
        // Test the nextRecord method with valid tokens to ensure it processes and returns a CSVRecord correctly.
        CSVRecord record = parser.nextRecord();
        assertNotNull(record);
        assertEquals(1, record.getRecordNumber());
        assertEquals("1", record.get(0));
        assertEquals("2", record.get(1));
        assertEquals("3", record.get(2));
        assertEquals("4", record.get(3));
        record = parser.nextRecord();
        assertNotNull(record);
        assertEquals(2, record.getRecordNumber());
        assertEquals("5", record.get(0));
        assertEquals("6", record.get(1));
        assertEquals("7", record.get(2));
        assertEquals("8", record.get(3));
    }

    @Test
    void testNextRecord_EndOfRecordHandling() throws IOException {
        // Test the nextRecord method when encountering an end of record (EORecord) token to ensure it adds the last record value correctly.
        // Read first record
        CSVRecord record = parser.nextRecord();
        assertNotNull(record);
        // Read second record
        record = parser.nextRecord();
        assertNotNull(record);
    }

    @Test
    void testNextRecord_EndOfFileHandling() throws IOException {
        // Test the nextRecord method when encountering an EOF token with ready state to ensure it adds a trailer comment correctly.
        parser = new CSVParser(new StringReader("a,b,c\n1,2,3\n"), format);
        CSVRecord record = parser.nextRecord();
        assertNotNull(record);
        // Read second record
        record = parser.nextRecord();
        // End of file should return null
        assertNull(parser.nextRecord());
    }

    @Test
    void testNextRecord_InvalidTokenHandling() throws Exception {
        // Test the nextRecord method when encountering an INVALID token to ensure it throws an IOException.
        parser = new CSVParser(new StringReader("a,b,c\n1,2,3\nINVALID_TOKEN\n"), format);
        assertThrows(IOException.class, () -> {
            while (true) {
                CSVRecord record = parser.nextRecord();
                if (record == null) {
                    // Break the loop if end of records is reached
                    break;
                }
            }
        });
    }

    @Test
    void testNextRecord_CommentHandling() throws IOException {
        // Test the nextRecord method when encountering COMMENT tokens to ensure it correctly handles and stores comments.
        String csvData = "# This is a comment\n1,2,3\n";
        parser = new CSVParser(new StringReader(csvData), format);
        CSVRecord record = parser.nextRecord();
        assertNotNull(record);
        // Check that the comment is ignored
        assertEquals("1", record.get(0));
    }

    @Test
    void testNextRecord_UnexpectedTokenHandling() throws Exception {
        // Test the nextRecord method when encountering an unexpected token type to ensure it throws an IOException.
        parser = new CSVParser(new StringReader("a,b,c\n1,2,3\n# Comment\n"), format);
        CSVRecord record = parser.nextRecord();
        assertNotNull(record);
        // The comment should be ignored
        assertEquals("1", record.get(0));
        // Now test for unexpected token handling
        assertThrows(IOException.class, () -> {
            // This should throw an exception for the comment
            parser.nextRecord();
        });
    }

    @Test
    void testNextRecord_EmptyRecordListHandling() throws IOException {
        // Test the nextRecord method when the recordList is empty to ensure it returns null.
        parser = new CSVParser(new StringReader(""), format);
        // No records should be present
        assertNull(parser.nextRecord());
    }

    @Test
    void testNextRecord_LexerNotInitialized() {
        // Test the nextRecord method when the lexer is not initialized to ensure it throws an IOException.
        assertThrows(IOException.class, () -> {
            // Simulate lexer not initialized
            parser = new CSVParser(new StringReader("Invalid data"), format);
            // This should throw an exception
            parser.nextRecord();
        });
    }

    @Test
    void testNextRecord_LexerThrowsIOException() throws Exception {
        // Test the nextRecord method when the lexer throws an IOException during token retrieval to ensure it propagates the exception.
        parser = mock(CSVParser.class);
        when(parser.nextRecord()).thenThrow(new IOException("Lexer error"));
        assertThrows(IOException.class, () -> {
            parser.nextRecord();
        });
    }
}

```

Corresponding execution feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended

╷
├─ JUnit Jupiter ✔
│  └─ CSVParser_nextRecord_Test ✔
│     ├─ testNextRecord() ✔
│     ├─ testNextRecord_InvalidTokenHandling() ✘ Expected java.io.IOException to be thrown, but nothing was thrown.
│     ├─ testNextRecord_EmptyRecordListHandling() ✔
│     ├─ testNextRecord_EndOfFileHandling() ✔
│     ├─ testNextRecord_CommentHandling() ✔
│     ├─ testNextRecord_EndOfRecordHandling() ✔
│     ├─ testNextRecord_LexerThrowsIOException() ✔
│     ├─ testNextRecord_UnexpectedTokenHandling() ✘ Expected java.io.IOException to be thrown, but nothing was thrown.
│     ├─ testNextRecord_LexerNotInitialized() ✘ Expected java.io.IOException to be thrown, but nothing was thrown.
│     └─ testNextRecord_ValidTokenProcessing() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Failures (3):
  JUnit Jupiter:CSVParser_nextRecord_Test:testNextRecord_InvalidTokenHandling()
    MethodSource [className = 'org.apache.commons.csv.CSVParser_nextRecord_Test', methodName = 'testNextRecord_InvalidTokenHandling', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Expected java.io.IOException to be thrown, but nothing was thrown.
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:73)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3083)
       org.apache.commons.csv.CSVParser_nextRecord_Test.testNextRecord_InvalidTokenHandling(CSVParser_nextRecord_Test.java:110)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:727)
       [...]
  JUnit Jupiter:CSVParser_nextRecord_Test:testNextRecord_UnexpectedTokenHandling()
    MethodSource [className = 'org.apache.commons.csv.CSVParser_nextRecord_Test', methodName = 'testNextRecord_UnexpectedTokenHandling', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Expected java.io.IOException to be thrown, but nothing was thrown.
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:73)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3083)
       org.apache.commons.csv.CSVParser_nextRecord_Test.testNextRecord_UnexpectedTokenHandling(CSVParser_nextRecord_Test.java:141)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:727)
       [...]
  JUnit Jupiter:CSVParser_nextRecord_Test:testNextRecord_LexerNotInitialized()
    MethodSource [className = 'org.apache.commons.csv.CSVParser_nextRecord_Test', methodName = 'testNextRecord_LexerNotInitialized', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Expected java.io.IOException to be thrown, but nothing was thrown.
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:73)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3083)
       org.apache.commons.csv.CSVParser_nextRecord_Test.testNextRecord_LexerNotInitialized(CSVParser_nextRecord_Test.java:158)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:727)
       [...]

Test run finished after 473 ms
[         4 containers found      ]
[         0 containers skipped    ]
[         4 containers started    ]
[         0 containers aborted    ]
[         4 containers successful ]
[         0 containers failed     ]
[        10 tests found           ]
[         0 tests skipped         ]
[        10 tests started         ]
[         0 tests aborted         ]
[         7 tests successful      ]
[         3 tests failed          ]


```


And you can use the following related information to assist you in your analysis and correction:

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
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.INVALID;
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.TOKEN;
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.EORECORD;
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.COMMENT;
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.EOF;
	
class `org.apache.commons.csv.Token`:
	dependent fields:
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.type;
		java.lang.StringBuilder org.apache.commons.csv.Token.content;
		boolean org.apache.commons.csv.Token.isReady;
	related functions:
		method ` void reset()` returns `void`, related with `org.apache.commons.csv.CSVParser.nextRecord()`;
		method `public String toString()` returns `java.lang.String`, related with `org.apache.commons.csv.CSVParser.nextRecord()`, api document: 
     * Eases IDE debugging.
     *
     * @return a string helpful for debugging.
     ;
class `org.apache.commons.csv.Constants`:
	dependent fields:
		java.lang.String[] org.apache.commons.csv.Constants.EMPTY_STRING_ARRAY;
		char org.apache.commons.csv.Constants.LF;
	
class `org.apache.commons.csv.CSVParser`:
	dependent fields:
		java.util.List<java.lang.String> org.apache.commons.csv.CSVParser.recordList;
		java.lang.String org.apache.commons.csv.CSVParser.trailerComment;
		org.apache.commons.csv.Token org.apache.commons.csv.CSVParser.reusableToken;
		long org.apache.commons.csv.CSVParser.recordNumber;
		org.apache.commons.csv.Lexer org.apache.commons.csv.CSVParser.lexer;
		long org.apache.commons.csv.CSVParser.characterOffset;
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
		method `private void printWithEscapes(Reader, Appendable)` returns `void`, related with `org.apache.commons.csv.CSVParser.nextRecord()`;
		method `public String toString()` returns `java.lang.String`, related with `org.apache.commons.csv.CSVParser.nextRecord()`;



Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 
