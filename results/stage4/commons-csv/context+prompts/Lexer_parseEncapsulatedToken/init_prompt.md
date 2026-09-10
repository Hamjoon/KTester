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
Here is the code of focal method, `parseEncapsulatedToken(Token)`, with the focal class `Lexer_parseEncapsulatedToken_Test`:
```Java
package org.apache.commons.csv;
import static org.apache.commons.csv.Constants.FF;
import static org.apache.commons.csv.Token.Type.INVALID;
import static org.apache.commons.csv.Constants.BACKSPACE;
import static org.apache.commons.csv.Constants.UNDEFINED;
import java.io.Closeable;
import java.io.IOException;
import static org.apache.commons.csv.Token.Type.EORECORD;
import static org.apache.commons.csv.Constants.CR;
import static org.apache.commons.csv.Token.Type.COMMENT;
import static org.apache.commons.csv.Constants.TAB;
import static org.apache.commons.csv.Constants.LF;
import static org.apache.commons.csv.Token.Type.EOF;
import static org.apache.commons.csv.Token.Type.TOKEN;
import static org.apache.commons.csv.Constants.END_OF_STREAM;
final class Lexer implements Closeable {
    private final char[] delimiter;
    private final ExtendedBufferedReader reader;
    private Token parseEncapsulatedToken(final Token token) throws IOException {
        token.isQuoted = true;
        // save current line number in case needed for IOE
        final long startLineNumber = getCurrentLineNumber();
        int c;
        while (true) {
            c = reader.read();
            if (isEscape(c)) {
                if (isEscapeDelimiter()) {
                    token.content.append(delimiter);
                } else {
                    final int unescaped = readEscape();
                    if (unescaped == END_OF_STREAM) {
                        // unexpected char after escape
                        token.content.append(((char) (c))).append(((char) (reader.getLastChar())));
                    } else {
                        token.content.append(((char) (unescaped)));
                    }
                }
            } else if (isQuoteChar(c)) {
                if (isQuoteChar(reader.lookAhead())) {
                    // double or escaped encapsulator -> add single encapsulator to token
                    c = reader.read();
                    token.content.append(((char) (c)));
                } else {
                    // token finish mark (encapsulator) reached: ignore whitespace till delimiter
                    while (true) {
                        c = reader.read();
                        if (isDelimiter(c)) {
                            token.type = TOKEN;
                            return token;
                        }
                        if (isEndOfFile(c)) {
                            token.type = EOF;
                            token.isReady = true;// There is data at EOF
    
                            return token;
                        }
                        if (readEndOfLine(c)) {
                            token.type = EORECORD;
                            return token;
                        }
                        if (!Character.isWhitespace(((char) (c)))) {
                            // error invalid char between token and next delimiter
                            throw new IOException(("(line " + getCurrentLineNumber()) + ") invalid char between encapsulated token and delimiter");
                        }
                    } 
                }
            } else if (isEndOfFile(c)) {
                // error condition (end of file before end of token)
                throw new IOException(("(startline " + startLineNumber) + ") EOF reached before encapsulated token finished");
            } else {
                // consume character
                token.content.append(((char) (c)));
            }
        } 
    }
@Override
public void close() throws IOException;
    long getCharacterPosition();
    long getCurrentLineNumber();
    String getFirstEol();
    boolean isClosed();
    boolean isCommentStart(final int ch);
    boolean isDelimiter(final int ch) throws IOException;
    boolean isEndOfFile(final int ch);
    boolean isEscape(final int ch);
    boolean isEscapeDelimiter() throws IOException;
    boolean isQuoteChar(final int ch);
    boolean isStartOfLine(final int ch);
    Token nextToken(final Token token) throws IOException;
    boolean readEndOfLine(int ch) throws IOException;
    int readEscape() throws IOException;
    void trimTrailingSpaces(final StringBuilder buffer);
}
```


@input{context information}

api document of class org.apache.commons.csv.Lexer: 

 * Lexical analyzer.
 

constructors for class `org.apache.commons.csv.Lexer`: 
params: org.apache.commons.csv.CSVFormat format 
org.apache.commons.csv.ExtendedBufferedReader reader 
body:
```java
    Lexer(final CSVFormat format, final ExtendedBufferedReader reader) {
        this.reader = reader;
        this.delimiter = format.getDelimiterString().toCharArray();
        this.escape = mapNullToDisabled(format.getEscapeCharacter());
        this.quoteChar = mapNullToDisabled(format.getQuoteCharacter());
        this.commentStart = mapNullToDisabled(format.getCommentMarker());
        this.ignoreSurroundingSpaces = format.getIgnoreSurroundingSpaces();
        this.ignoreEmptyLines = format.getIgnoreEmptyLines();
        this.delimiterBuf = new char[delimiter.length - 1];
        this.escapeDelimiterBuf = new char[2 * delimiter.length - 1];
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
class `org.apache.commons.csv.ExtendedBufferedReader`:
api document : 
 * A special buffered reader which supports sophisticated read access.
 * <p>
 * In particular the reader supports a look-ahead option, which allows you to see the next char returned by
 * {@link #read()}. This reader also tracks how many characters have been read with {@link #getPosition()}.
 * </p>
 
constructor:
```java
    ExtendedBufferedReader(final Reader reader) {
        super(reader);
    }

```
class `org.apache.commons.csv.Token`:
api document : 
 * Internal token representation.
 * <p/>
 * It is used as contract between the lexer and the parser.
 
constructor:
```java
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

import static org.apache.commons.csv.Constants.BACKSPACE;
import static org.apache.commons.csv.Constants.CR;
import static org.apache.commons.csv.Constants.FF;
import static org.apache.commons.csv.Constants.LF;
import static org.apache.commons.csv.Constants.TAB;
import static org.apache.commons.csv.Token.Type.COMMENT;
import static org.apache.commons.csv.Token.Type.EOF;
import static org.apache.commons.csv.Token.Type.EORECORD;
import static org.apache.commons.csv.Token.Type.TOKEN;
import static org.apache.commons.csv.TokenMatchers.hasContent;
import static org.apache.commons.csv.TokenMatchers.matches;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.StringReader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 */
public class LexerTest {

    private CSVFormat formatWithEscaping;

    @SuppressWarnings("resource")
    private Lexer createLexer(final String input, final CSVFormat format) {
        return new Lexer(format, new ExtendedBufferedReader(new StringReader(input)));
    }

    @BeforeEach
    public void setUp() {
        formatWithEscaping = CSVFormat.DEFAULT.withEscape('\\');
    }

    // simple token with escaping enabled
    @Test
    public void testBackslashWithEscaping() throws IOException {
        /*
         * file: a,\,,b \,,
         */
        final String code = "a,\\,,b\\\\\n\\,,\\\nc,d\\\r\ne";
        final CSVFormat format = formatWithEscaping.withIgnoreEmptyLines(false);
        assertTrue(format.isEscapeCharacterSet());
        try (final Lexer parser = createLexer(code, format)) {
            assertThat(parser.nextToken(new Token()), matches(TOKEN, "a"));
            assertThat(parser.nextToken(new Token()), matches(TOKEN, ","));
            assertThat(parser.nextToken(new Token()), matches(EORECORD, "b\\"));
            assertThat(parser.nextToken(new Token()), matches(TOKEN, ","));
            assertThat(parser.nextToken(new Token()), matches(TOKEN, "\nc"));
            assertThat(parser.nextToken(new Token()), matches(EORECORD, "d\r"));
            assertThat(parser.nextToken(new Token()), matches(EOF, "e"));
        }
    }

    // simple token with escaping not enabled
    @Test
    public void testBackslashWithoutEscaping() throws IOException {
        /*
         * file: a,\,,b \,,
         */
        final String code = "a,\\,,b\\\n\\,,";
        final CSVFormat format = CSVFormat.DEFAULT;
        assertFalse(format.isEscapeCharacterSet());
        try (final Lexer parser = createLexer(code, format)) {
            assertThat(parser.nextToken(new Token()), matches(TOKEN, "a"));
            // an unquoted single backslash is not an escape char
            assertThat(parser.nextToken(new Token()), matches(TOKEN, "\\"));
            assertThat(parser.nextToken(new Token()), matches(TOKEN, ""));
            assertThat(parser.nextToken(new Token()), matches(EORECORD, "b\\"));
            // an unquoted single backslash is not an escape char
            assertThat(parser.nextToken(new Token()), matches(TOKEN, "\\"));
            assertThat(parser.nextToken(new Token()), matches(TOKEN, ""));
            assertThat(parser.nextToken(new Token()), matches(EOF, ""));
        }
    }
......
        try (final Lexer lexer = createLexer("t", CSVFormat.DEFAULT.withEscape('\t'))) {
            final int ch = lexer.readEscape();
            assertThat(lexer.nextToken(new Token()), matches(EOF, ""));
            assertEquals(TAB, ch);
        }
    }

    @Test
    public void testSurroundingSpacesAreDeleted() throws IOException {
        final String code = "noSpaces,  leadingSpaces,trailingSpaces  ,  surroundingSpaces  ,  ,,";
        try (final Lexer parser = createLexer(code, CSVFormat.DEFAULT.withIgnoreSurroundingSpaces())) {
            assertThat(parser.nextToken(new Token()), matches(TOKEN, "noSpaces"));
            assertThat(parser.nextToken(new Token()), matches(TOKEN, "leadingSpaces"));
            assertThat(parser.nextToken(new Token()), matches(TOKEN, "trailingSpaces"));
            assertThat(parser.nextToken(new Token()), matches(TOKEN, "surroundingSpaces"));
            assertThat(parser.nextToken(new Token()), matches(TOKEN, ""));
            assertThat(parser.nextToken(new Token()), matches(TOKEN, ""));
            assertThat(parser.nextToken(new Token()), matches(EOF, ""));
        }
    }

    @Test
    public void testSurroundingTabsAreDeleted() throws IOException {
        final String code = "noTabs,\tleadingTab,trailingTab\t,\tsurroundingTabs\t,\t\t,,";
        try (final Lexer parser = createLexer(code, CSVFormat.DEFAULT.withIgnoreSurroundingSpaces())) {
            assertThat(parser.nextToken(new Token()), matches(TOKEN, "noTabs"));
            assertThat(parser.nextToken(new Token()), matches(TOKEN, "leadingTab"));
            assertThat(parser.nextToken(new Token()), matches(TOKEN, "trailingTab"));
            assertThat(parser.nextToken(new Token()), matches(TOKEN, "surroundingTabs"));
            assertThat(parser.nextToken(new Token()), matches(TOKEN, ""));
            assertThat(parser.nextToken(new Token()), matches(TOKEN, ""));
            assertThat(parser.nextToken(new Token()), matches(EOF, ""));
        }
    }

    @Test
    public void testTab() throws Exception {
        try (final Lexer lexer = createLexer("character" + TAB + "NotEscaped", formatWithEscaping)) {
            assertThat(lexer.nextToken(new Token()), hasContent("character" + TAB + "NotEscaped"));
        }
    }

    @Test
    public void testTrimTrailingSpacesZeroLength() throws Exception {
        final StringBuilder buffer = new StringBuilder("");
        final Lexer lexer = createLexer(buffer.toString(), CSVFormat.DEFAULT);
        lexer.trimTrailingSpaces(buffer);
        assertThat(lexer.nextToken(new Token()), matches(EOF, ""));
    }
}
```

invoke examples of focal method: 
example 1:
```java
    Token nextToken(final Token token) throws IOException {

        // get the last read char (required for empty line detection)
        int lastChar = reader.getLastChar();
        int c = reader.read();
        boolean eol = readEndOfLine(c);
        if (ignoreEmptyLines) {
        if (isEndOfFile(lastChar) || !isLastTokenDelimiter && isEndOfFile(c)) {
        if (isStartOfLine(lastChar) && isCommentStart(c)) {
        while (token.type == INVALID) {
                parseEncapsulatedToken(token);
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
class Lexer_parseEncapsulatedToken_Test {
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