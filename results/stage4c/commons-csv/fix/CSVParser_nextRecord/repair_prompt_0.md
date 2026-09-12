


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
        format = CSVFormat.DEFAULT;
        parser = new CSVParser(new StringReader("a,b,c\n1,2,3"), format);
    }

    @AfterEach
    void teardownAfterEach() throws IOException {
        // Clean up resources after each test
        if (parser != null) {
            parser.close();
        }
    }

    // Helper method to set private fields via reflection
    private void setField(Object target, String fieldName, Object value) {
        try {
            java.lang.reflect.Field field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testNextRecord() throws IOException {
        // Given
        assertNotNull(parser);
        // When
        CSVRecord record1 = parser.nextRecord();
        CSVRecord record2 = parser.nextRecord();
        CSVRecord record3 = parser.nextRecord();
        // Then
        assertNotNull(record1);
        assertEquals("a", record1.get(0));
        assertEquals("b", record1.get(1));
        assertEquals("c", record1.get(2));
        assertNotNull(record2);
        assertEquals("1", record2.get(0));
        assertEquals("2", record2.get(1));
        assertEquals("3", record2.get(2));
        assertNull(record3);
    }

    @Test
    void testNextRecordWithIOException()throws Exception {
        // Given
        Lexer lexerMock = mock(Lexer.class);
        when(lexerMock.nextToken(any(Token.class))).thenAnswer(invocation -> {
            Token token = invocation.getArgument(0);
            token.type = Token.Type.INVALID;
            return token;
        });
        CSVParser parserWithMockedLexer = new CSVParser(new StringReader("a,b,c\n1,2,3"), format);
        setField(parserWithMockedLexer, "lexer", lexerMock);
        // When & Then
        assertThrows(IOException.class, parserWithMockedLexer::nextRecord);
    }

    @Test
    void testNextRecordWithEOFAndNoReadyToken() throws IOException {
        // Given
        CSVParser parserWithEOF = new CSVParser(new StringReader(""), format);
        // When
        CSVRecord record = parserWithEOF.nextRecord();
        // Then
        assertNull(record);
    }

    @Test
    void testNextRecordWithCommentFollowedByValidRecord() throws IOException {
        // Given
        CSVParser parserWithComment = new CSVParser(new StringReader("# This is a comment\na,b,c\n1,2,3"), format);
        // When
        CSVRecord record = parserWithComment.nextRecord();
        // Then
        assertNotNull(record);
        assertEquals("a", record.get(0));
        assertEquals("b", record.get(1));
        assertEquals("c", record.get(2));
        assertEquals("# This is a comment", record.getComment());
    }

    @Test
    void testNextRecordWithUnexpectedToken()throws Exception {
        // Given
        Lexer lexerMock = mock(Lexer.class);
        when(lexerMock.nextToken(any(Token.class))).thenAnswer(invocation -> {
            Token token = invocation.getArgument(0);
            // Simulate unexpected token type
            token.type = null;
            return token;
        });
        CSVParser parserWithMockedLexer = new CSVParser(new StringReader("a,b,c\n1,2,3"), format);
        setField(parserWithMockedLexer, "lexer", lexerMock);
        // When & Then
        assertThrows(IllegalStateException.class, parserWithMockedLexer::nextRecord);
    }

    @Test
    void testNextRecordWithEORECORDToken() throws IOException {
        // Given
        Lexer lexerMock = mock(Lexer.class);
        when(lexerMock.nextToken(any(Token.class))).thenAnswer(invocation -> {
            Token token = invocation.getArgument(0);
            token.type = Token.Type.EORECORD;
            return token;
        });
        CSVParser parserWithMockedLexer = new CSVParser(new StringReader("a,b,c\n1,2,3"), format);
        setField(parserWithMockedLexer, "lexer", lexerMock);
        // When
        CSVRecord record = parserWithMockedLexer.nextRecord();
        // Then
        assertNotNull(record);
    }

    @Test
    void testNextRecordWithEOFAndReadyToken() throws IOException {
        // Given
        Lexer lexerMock = mock(Lexer.class);
        when(lexerMock.nextToken(any(Token.class))).thenAnswer(invocation -> {
            Token token = invocation.getArgument(0);
            token.type = Token.Type.EOF;
            token.isReady = true;
            return token;
        });
        CSVParser parserWithMockedLexer = new CSVParser(new StringReader("a,b,c\n1,2,3"), format);
        setField(parserWithMockedLexer, "lexer", lexerMock);
        // When
        CSVRecord record = parserWithMockedLexer.nextRecord();
        // Then
        assertNotNull(record);
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
│     ├─ testNextRecordWithEOFAndNoReadyToken() ✔
│     ├─ testNextRecordWithIOException() ✔
│     ├─ testNextRecordWithEORECORDToken() ✔
│     ├─ testNextRecordWithCommentFollowedByValidRecord() ✘ expected: <a> but was: <# This is a comment>
│     ├─ testNextRecordWithEOFAndReadyToken() ✔
│     └─ testNextRecordWithUnexpectedToken() ✘ Unexpected exception type thrown, expected: <java.lang.IllegalStateException> but was: <java.lang.NullPointerException>
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Failures (2):
  JUnit Jupiter:CSVParser_nextRecord_Test:testNextRecordWithCommentFollowedByValidRecord()
    MethodSource [className = 'org.apache.commons.csv.CSVParser_nextRecord_Test', methodName = 'testNextRecordWithCommentFollowedByValidRecord', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <a> but was: <# This is a comment>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1142)
       org.apache.commons.csv.CSVParser_nextRecord_Test.testNextRecordWithCommentFollowedByValidRecord(CSVParser_nextRecord_Test.java:98)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:CSVParser_nextRecord_Test:testNextRecordWithUnexpectedToken()
    MethodSource [className = 'org.apache.commons.csv.CSVParser_nextRecord_Test', methodName = 'testNextRecordWithUnexpectedToken', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Unexpected exception type thrown, expected: <java.lang.IllegalStateException> but was: <java.lang.NullPointerException>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:67)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3083)
       org.apache.commons.csv.CSVParser_nextRecord_Test.testNextRecordWithUnexpectedToken(CSVParser_nextRecord_Test.java:117)
       [...]
     Caused by: java.lang.NullPointerException: Cannot invoke "org.apache.commons.csv.Token$Type.ordinal()" because "this.reusableToken.type" is null
       org.apache.commons.csv.CSVParser.nextRecord(CSVParser.java:771)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:53)
       [...]

Test run finished after 460 ms
[         4 containers found      ]
[         0 containers skipped    ]
[         4 containers started    ]
[         0 containers aborted    ]
[         4 containers successful ]
[         0 containers failed     ]
[         7 tests found           ]
[         0 tests skipped         ]
[         7 tests started         ]
[         0 tests aborted         ]
[         5 tests successful      ]
[         2 tests failed          ]


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
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.COMMENT;
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.TOKEN;
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.INVALID;
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.EOF;
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.EORECORD;
	
class `org.apache.commons.csv.Token`:
	dependent fields:
		java.lang.StringBuilder org.apache.commons.csv.Token.content;
		boolean org.apache.commons.csv.Token.isReady;
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.type;
	related functions:
		method `public String toString()` returns `java.lang.String`, related with `org.apache.commons.csv.CSVParser.nextRecord()`, api document: 
     * Eases IDE debugging.
     *
     * @return a string helpful for debugging.
     ;
		method ` void reset()` returns `void`, related with `org.apache.commons.csv.CSVParser.nextRecord()`;
class `org.apache.commons.csv.Constants`:
	dependent fields:
		java.lang.String[] org.apache.commons.csv.Constants.EMPTY_STRING_ARRAY;
		char org.apache.commons.csv.Constants.LF;
	
class `org.apache.commons.csv.CSVParser`:
	dependent fields:
		org.apache.commons.csv.Token org.apache.commons.csv.CSVParser.reusableToken;
		org.apache.commons.csv.Lexer org.apache.commons.csv.CSVParser.lexer;
		long org.apache.commons.csv.CSVParser.characterOffset;
		java.lang.String org.apache.commons.csv.CSVParser.trailerComment;
		long org.apache.commons.csv.CSVParser.recordNumber;
		java.util.List<java.lang.String> org.apache.commons.csv.CSVParser.recordList;
	related functions:
		method `private void addRecordValue(boolean)` returns `void`, related with `org.apache.commons.csv.CSVParser.nextRecord()`;
class `org.apache.commons.csv.Lexer`:
	related functions:
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
