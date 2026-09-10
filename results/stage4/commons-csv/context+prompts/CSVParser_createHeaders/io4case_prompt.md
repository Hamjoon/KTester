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
Here is the code of focal method, `createHeaders()`, with the focal class `CSVParser`:
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
    private String headerComment;
    private final CSVFormat format;
    private Headers createHeaders() throws IOException {
        Map<String, Integer> hdrMap = null;
        List<String> headerNames = null;
        final String[] formatHeader = this.format.getHeader();
        if (formatHeader != null) {
            hdrMap = createEmptyHeaderMap();
            String[] headerRecord = null;
            if (formatHeader.length == 0) {
                // read the header from the first line of the file
                final CSVRecord nextRecord = this.nextRecord();
                if (nextRecord != null) {
                    headerRecord = nextRecord.values();
                    headerComment = nextRecord.getComment();
                }
            } else {
                if (this.format.getSkipHeaderRecord()) {
                    final CSVRecord nextRecord = this.nextRecord();
                    if (nextRecord != null) {
                        headerComment = nextRecord.getComment();
                    }
                }
                headerRecord = formatHeader;
            }
            // build the name to index mappings
            if (headerRecord != null) {
                // Track an occurrence of a null, empty or blank header.
                boolean observedMissing = false;
                for (int i = 0; i < headerRecord.length; i++) {
                    final String header = headerRecord[i];
                    final boolean blankHeader = CSVFormat.isBlank(header);
                    if (blankHeader && (!this.format.getAllowMissingColumnNames())) {
                        throw new IllegalArgumentException("A header name is missing in " + Arrays.toString(headerRecord));
                    }
                    final boolean containsHeader = (blankHeader) ? observedMissing : hdrMap.containsKey(header);
                    final DuplicateHeaderMode headerMode = this.format.getDuplicateHeaderMode();
                    final boolean duplicatesAllowed = headerMode == DuplicateHeaderMode.ALLOW_ALL;
                    final boolean emptyDuplicatesAllowed = headerMode == DuplicateHeaderMode.ALLOW_EMPTY;
                    if ((containsHeader && (!duplicatesAllowed)) && (!(blankHeader && emptyDuplicatesAllowed))) {
                        throw new IllegalArgumentException(String.format("The header contains a duplicate name: \"%s\" in %s. If this is valid then use CSVFormat.Builder.setDuplicateHeaderMode().", header, Arrays.toString(headerRecord)));
                    }
                    observedMissing |= blankHeader;
                    if (header != null) {
                        hdrMap.put(header, Integer.valueOf(i));
                        if (headerNames == null) {
                            headerNames = new ArrayList<>(headerRecord.length);
                        }
                        headerNames.add(header);
                    }
                }
            }
        }
        if (headerNames == null) {
            headerNames = Collections.emptyList();// immutable
    
        } else {
            headerNames = Collections.unmodifiableList(headerNames);
        }
        return new Headers(hdrMap, headerNames);
    }
@Override
public void close() throws IOException;
    private Map<String, Integer> createEmptyHeaderMap();
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
    CSVRecord nextRecord() throws IOException;
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

@input{existing test case}
here's the existing test cases:
```json
<cases_json>
```

@output{test cases}: output the test cases in the JSON format: