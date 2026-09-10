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

@input{existing test case}
here's the existing test cases:
```json
<cases_json>
```

@output{test cases}: output the test cases in the JSON format: