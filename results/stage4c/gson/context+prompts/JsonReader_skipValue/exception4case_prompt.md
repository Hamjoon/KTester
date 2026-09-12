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
Here is the code of focal method, `skipValue()`, with the focal class `JsonReader`:
```Java
package com.google.gson.stream;
import com.google.gson.internal.bind.JsonTreeReader;
import java.io.Closeable;
import java.io.IOException;
import java.util.Objects;
import java.util.Arrays;
import com.google.gson.internal.JsonReaderInternalAccess;
import java.io.Reader;
import java.io.EOFException;
public class JsonReader implements Closeable {
    private static final int PEEKED_NONE = 0;
    private static final int PEEKED_BEGIN_OBJECT = 1;
    private static final int PEEKED_END_OBJECT = 2;
    private static final int PEEKED_BEGIN_ARRAY = 3;
    private static final int PEEKED_END_ARRAY = 4;
    private static final int PEEKED_SINGLE_QUOTED = 8;
    private static final int PEEKED_DOUBLE_QUOTED = 9;
    private static final int PEEKED_UNQUOTED = 10;
    private static final int PEEKED_SINGLE_QUOTED_NAME = 12;
    private static final int PEEKED_DOUBLE_QUOTED_NAME = 13;
    private static final int PEEKED_UNQUOTED_NAME = 14;
    private static final int PEEKED_NUMBER = 16;
    private static final int PEEKED_EOF = 17;
    static final int BUFFER_SIZE = 1024;
    private int pos = 0;
    int peeked = PEEKED_NONE;
    private int peekedNumberLength;
    private int stackSize = 0;
    private String[] pathNames = new String[32];
    private int[] pathIndices = new int[32];
    public void skipValue() throws IOException {
        int count = 0;
        do {
            int p = peeked;
            if (p == PEEKED_NONE) {
                p = doPeek();
            }
            switch (p) {
                case PEEKED_BEGIN_ARRAY :
                    push(JsonScope.EMPTY_ARRAY);
                    count++;
                    break;
                case PEEKED_BEGIN_OBJECT :
                    push(JsonScope.EMPTY_OBJECT);
                    count++;
                    break;
                case PEEKED_END_ARRAY :
                    stackSize--;
                    count--;
                    break;
                case PEEKED_END_OBJECT :
                    // Only update when object end is explicitly skipped, otherwise stack is not updated anyways
                    if (count == 0) {
                        pathNames[stackSize - 1] = null;// Free the last path name so that it can be garbage collected
    
                    }
                    stackSize--;
                    count--;
                    break;
                case PEEKED_UNQUOTED :
                    skipUnquotedValue();
                    break;
                case PEEKED_SINGLE_QUOTED :
                    skipQuotedValue('\'');
                    break;
                case PEEKED_DOUBLE_QUOTED :
                    skipQuotedValue('"');
                    break;
                case PEEKED_UNQUOTED_NAME :
                    skipUnquotedValue();
                    // Only update when name is explicitly skipped, otherwise stack is not updated anyways
                    if (count == 0) {
                        pathNames[stackSize - 1] = "<skipped>";
                    }
                    break;
                case PEEKED_SINGLE_QUOTED_NAME :
                    skipQuotedValue('\'');
                    // Only update when name is explicitly skipped, otherwise stack is not updated anyways
                    if (count == 0) {
                        pathNames[stackSize - 1] = "<skipped>";
                    }
                    break;
                case PEEKED_DOUBLE_QUOTED_NAME :
                    skipQuotedValue('"');
                    // Only update when name is explicitly skipped, otherwise stack is not updated anyways
                    if (count == 0) {
                        pathNames[stackSize - 1] = "<skipped>";
                    }
                    break;
                case PEEKED_NUMBER :
                    pos += peekedNumberLength;
                    break;
                case PEEKED_EOF :
                    // Do nothing
                    return;
                    // For all other tokens there is nothing to do; token has already been consumed from underlying reader
            }
            peeked = PEEKED_NONE;
        } while (count > 0 );
        pathIndices[stackSize - 1]++;
    }
public void beginArray() throws IOException;
    public void beginObject() throws IOException;
    @Override
public void close() throws IOException;
    @SuppressWarnings("fallthrough")
int doPeek() throws IOException;
    public void endArray() throws IOException;
    public void endObject() throws IOException;
    public String getPath();
    public String getPreviousPath();
    public boolean hasNext() throws IOException;
    public final boolean isLenient();
    String locationString();
    public boolean nextBoolean() throws IOException;
    public double nextDouble() throws IOException;
    public int nextInt() throws IOException;
    public long nextLong() throws IOException;
    public String nextName() throws IOException;
    public void nextNull() throws IOException;
    public String nextString() throws IOException;
    public JsonToken peek() throws IOException;
    private void push(int newTop);
    public final void setLenient(boolean lenient);
    private void skipQuotedValue(char quote) throws IOException;
    @SuppressWarnings("fallthrough")
private void skipUnquotedValue() throws IOException;
    @Override
public String toString();
}
```
@input{existing test case}
here's the existing test cases:
```json
<cases_json>
```

@output{test cases}: output the test cases in the JSON format: