As a professional Java software testing expert, your task is to generate test cases for a focal method.

You will be provided with:
1. The focal method that needs testing.
2. Basic information of the target class to which the focus method belongs, including fields and methods.

Ensure the generated test cases meet the following requirements:

Test Coverage Requirements:
- Create test cases to cover all conditional branches in the target method, including `if`, `else if`, `else`, `switch`, and `case` statements.

Output Format:
- Organize the test cases by its functions logically.
- Include input parameters, expected output, and a description for each test case.
- Use empty list `[]` for the input when a test case does not require input parameters.
- Provide an expected return value for each test case, or specify the behavior that should be verified if there's no return value.
- Clearly describe the intention of each test case in the description.

Follow the JSON format provided here for the output:
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

@output{test cases}: output the test cases in the JSON format: