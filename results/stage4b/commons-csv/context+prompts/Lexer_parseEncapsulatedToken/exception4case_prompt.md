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
Here is the code of focal method, `parseEncapsulatedToken(Token)`, with the focal class `Lexer`:
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
@input{existing test case}
here's the existing test cases:
```json
<cases_json>
```

@output{test cases}: output the test cases in the JSON format: