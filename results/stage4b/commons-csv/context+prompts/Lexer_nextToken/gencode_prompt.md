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
Here is the code of focal method, `nextToken(Token)`, with the focal class `Lexer`:
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
    private final boolean ignoreSurroundingSpaces;
    private final boolean ignoreEmptyLines;
    private final ExtendedBufferedReader reader;
    private boolean isLastTokenDelimiter;
    Token nextToken(final Token token) throws IOException {
        // get the last read char (required for empty line detection)
        int lastChar = reader.getLastChar();
        // read the next char and set eol
        int c = reader.read();
        /* Note: The following call will swallow LF if c == CR. But we don't need to know if the last char was CR or LF
        - they are equivalent here.
         */
        boolean eol = readEndOfLine(c);
        // empty line detection: eol AND (last char was EOL or beginning)
        if (ignoreEmptyLines) {
            while (eol && isStartOfLine(lastChar)) {
                // go on char ahead ...
                lastChar = c;
                c = reader.read();
                eol = readEndOfLine(c);
                // reached end of file without any content (empty line at the end)
                if (isEndOfFile(c)) {
                    token.type = EOF;
                    // don't set token.isReady here because no content
                    return token;
                }
            } 
        }
        // did we reach eof during the last iteration already ? EOF
        if (isEndOfFile(lastChar) || ((!isLastTokenDelimiter) && isEndOfFile(c))) {
            token.type = EOF;
            // don't set token.isReady here because no content
            return token;
        }
        if (isStartOfLine(lastChar) && isCommentStart(c)) {
            final String line = reader.readLine();
            if (line == null) {
                token.type = EOF;
                // don't set token.isReady here because no content
                return token;
            }
            final String comment = line.trim();
            token.content.append(comment);
            token.type = COMMENT;
            return token;
        }
        // important: make sure a new char gets consumed in each iteration
        while (token.type == INVALID) {
            // ignore whitespaces at beginning of a token
            if (ignoreSurroundingSpaces) {
                while ((Character.isWhitespace(((char) (c))) && (!isDelimiter(c))) && (!eol)) {
                    c = reader.read();
                    eol = readEndOfLine(c);
                } 
            }
            // ok, start of token reached: encapsulated, or token
            if (isDelimiter(c)) {
                // empty token return TOKEN("")
                token.type = TOKEN;
            } else if (eol) {
                // empty token return EORECORD("")
                // noop: token.content.append("");
                token.type = EORECORD;
            } else if (isQuoteChar(c)) {
                // consume encapsulated token
                parseEncapsulatedToken(token);
            } else if (isEndOfFile(c)) {
                // end of file return EOF()
                // noop: token.content.append("");
                token.type = EOF;
                token.isReady = true;// there is data at EOF
    
            } else {
                // next token must be a simple token
                // add removed blanks when not ignoring whitespace chars...
                parseSimpleToken(token, c);
            }
        } 
        return token;
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
    private Token parseEncapsulatedToken(final Token token) throws IOException;
    private Token parseSimpleToken(final Token token, int ch) throws IOException;
    boolean readEndOfLine(int ch) throws IOException;
    int readEscape() throws IOException;
    void trimTrailingSpaces(final StringBuilder buffer);
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

api document of class org.apache.commons.csv.Lexer: 

 * Lexical analyzer.
 

api document of method nextToken(Token): 

     * Returns the next token.
     * <p>
     * A token corresponds to a term, a record change or an end-of-file indicator.
     * </p>
     *
     * @param token
     *            an existing Token object to reuse. The caller is responsible to initialize the Token.
     * @return the next token found.
     * @throws IOException on stream access error.
     

return type: 
org.apache.commons.csv.Token

dependent classes: 
class `org.apache.commons.csv.Token`:
	dependent fields:
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.type;
		java.lang.StringBuilder org.apache.commons.csv.Token.content;
		boolean org.apache.commons.csv.Token.isReady;
	related functions:
		method ` void reset()` returns `void`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`;
		method `public String toString()` returns `java.lang.String`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`, api document: 
     * Eases IDE debugging.
     *
     * @return a string helpful for debugging.
     ;
class `org.apache.commons.csv.Token.Type`:
	dependent fields:
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.INVALID;
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.TOKEN;
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.EORECORD;
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.COMMENT;
		org.apache.commons.csv.Token.Type org.apache.commons.csv.Token.Type.EOF;
	
class `org.apache.commons.csv.Lexer`:
	dependent fields:
		boolean org.apache.commons.csv.Lexer.isLastTokenDelimiter;
		org.apache.commons.csv.ExtendedBufferedReader org.apache.commons.csv.Lexer.reader;
		boolean org.apache.commons.csv.Lexer.ignoreSurroundingSpaces;
		boolean org.apache.commons.csv.Lexer.ignoreEmptyLines;
	related functions:
		method ` int readEscape()` returns `int`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`, api document: 
     * Handle an escape sequence.
     * The current character must be the escape character.
     * On return, the next character is available by calling {@link ExtendedBufferedReader#getLastChar()}
     * on the input stream.
     *
     * @return the unescaped character (as an int) or {@link Constants#END_OF_STREAM} if char following the escape is
     *      invalid.
     * @throws IOException if there is a problem reading the stream or the end of stream is detected:
     *      the escape character is not allowed at end of stream
     ;
		method `private Token parseEncapsulatedToken(Token)` returns `org.apache.commons.csv.Token`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`, api document: 
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
		method `private Token parseSimpleToken(Token, int)` returns `org.apache.commons.csv.Token`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`, api document: 
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
		method ` boolean readEndOfLine(int)` returns `boolean`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`, api document: 
     * Greedily accepts \n, \r and \r\n This checker consumes silently the second control-character...
     *
     * @return true if the given or next character is a line-terminator
     ;
		method ` boolean isDelimiter(int)` returns `boolean`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`, api document: 
     * Determine whether the next characters constitute a delimiter through {@link ExtendedBufferedReader#lookAhead(char[])}.
     *
     * @param ch
     *             the current character.
     * @return true if the next characters constitute a delimiter.
     * @throws IOException If an I/O error occurs.
     ;
class `org.apache.commons.csv.CSVParser`:
	related functions:
		method ` CSVRecord nextRecord()` returns `org.apache.commons.csv.CSVRecord`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`, api document: 
     * Parses the next record from the current point in the stream.
     *
     * @return the record as an array of values, or {@code null} if the end of the stream has been reached
     * @throws IOException
     *             on parse error or input read-failure
     ;
class `org.apache.commons.csv.CSVFormat`:
	related functions:
		method ` static boolean isBlank(String)` returns `boolean`, related with `org.apache.commons.csv.Lexer.nextToken(Token)`;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.