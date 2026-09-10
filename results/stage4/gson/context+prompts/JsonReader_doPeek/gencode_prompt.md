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
Here is the code of focal method, `doPeek()`, with the focal class `JsonReader`:
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
    private static final int PEEKED_NULL = 7;
    private static final int PEEKED_SINGLE_QUOTED = 8;
    private static final int PEEKED_DOUBLE_QUOTED = 9;
    private static final int PEEKED_UNQUOTED = 10;
    private static final int PEEKED_SINGLE_QUOTED_NAME = 12;
    private static final int PEEKED_DOUBLE_QUOTED_NAME = 13;
    private static final int PEEKED_UNQUOTED_NAME = 14;
    private static final int PEEKED_EOF = 17;
    private boolean lenient = false;
    static final int BUFFER_SIZE = 1024;
    private final char[] buffer = new char[BUFFER_SIZE];
    private int pos = 0;
    private int limit = 0;
    int peeked = PEEKED_NONE;
    private int[] stack = new int[32];
    private int stackSize = 0;
    @SuppressWarnings("fallthrough")
    int doPeek() throws IOException {
        int peekStack = stack[stackSize - 1];
        if (peekStack == JsonScope.EMPTY_ARRAY) {
            stack[stackSize - 1] = JsonScope.NONEMPTY_ARRAY;
        } else if (peekStack == JsonScope.NONEMPTY_ARRAY) {
            // Look for a comma before the next element.
            int c = nextNonWhitespace(true);
            switch (c) {
                case ']' :
                    return peeked = PEEKED_END_ARRAY;
                case ';' :
                    checkLenient();// fall-through
    
                case ',' :
                    break;
                default :
                    throw syntaxError("Unterminated array");
            }
        } else if ((peekStack == JsonScope.EMPTY_OBJECT) || (peekStack == JsonScope.NONEMPTY_OBJECT)) {
            stack[stackSize - 1] = JsonScope.DANGLING_NAME;
            // Look for a comma before the next element.
            if (peekStack == JsonScope.NONEMPTY_OBJECT) {
                int c = nextNonWhitespace(true);
                switch (c) {
                    case '}' :
                        return peeked = PEEKED_END_OBJECT;
                    case ';' :
                        checkLenient();// fall-through
    
                    case ',' :
                        break;
                    default :
                        throw syntaxError("Unterminated object");
                }
            }
            int c = nextNonWhitespace(true);
            switch (c) {
                case '"' :
                    return peeked = PEEKED_DOUBLE_QUOTED_NAME;
                case '\'' :
                    checkLenient();
                    return peeked = PEEKED_SINGLE_QUOTED_NAME;
                case '}' :
                    if (peekStack != JsonScope.NONEMPTY_OBJECT) {
                        return peeked = PEEKED_END_OBJECT;
                    } else {
                        throw syntaxError("Expected name");
                    }
                default :
                    checkLenient();
                    pos--;// Don't consume the first character in an unquoted string.
    
                    if (isLiteral(((char) (c)))) {
                        return peeked = PEEKED_UNQUOTED_NAME;
                    } else {
                        throw syntaxError("Expected name");
                    }
            }
        } else if (peekStack == JsonScope.DANGLING_NAME) {
            stack[stackSize - 1] = JsonScope.NONEMPTY_OBJECT;
            // Look for a colon before the value.
            int c = nextNonWhitespace(true);
            switch (c) {
                case ':' :
                    break;
                case '=' :
                    checkLenient();
                    if (((pos < limit) || fillBuffer(1)) && (buffer[pos] == '>')) {
                        pos++;
                    }
                    break;
                default :
                    throw syntaxError("Expected ':'");
            }
        } else if (peekStack == JsonScope.EMPTY_DOCUMENT) {
            if (lenient) {
                consumeNonExecutePrefix();
            }
            stack[stackSize - 1] = JsonScope.NONEMPTY_DOCUMENT;
        } else if (peekStack == JsonScope.NONEMPTY_DOCUMENT) {
            int c = nextNonWhitespace(false);
            if (c == (-1)) {
                return peeked = PEEKED_EOF;
            } else {
                checkLenient();
                pos--;
            }
        } else if (peekStack == JsonScope.CLOSED) {
            throw new IllegalStateException("JsonReader is closed");
        }
        int c = nextNonWhitespace(true);
        switch (c) {
            case ']' :
                if (peekStack == JsonScope.EMPTY_ARRAY) {
                    return peeked = PEEKED_END_ARRAY;
                }
                // fall-through to handle ",]"
            case ';' :
            case ',' :
                // In lenient mode, a 0-length literal in an array means 'null'.
                if ((peekStack == JsonScope.EMPTY_ARRAY) || (peekStack == JsonScope.NONEMPTY_ARRAY)) {
                    checkLenient();
                    pos--;
                    return peeked = PEEKED_NULL;
                } else {
                    throw syntaxError("Unexpected value");
                }
            case '\'' :
                checkLenient();
                return peeked = PEEKED_SINGLE_QUOTED;
            case '"' :
                return peeked = PEEKED_DOUBLE_QUOTED;
            case '[' :
                return peeked = PEEKED_BEGIN_ARRAY;
            case '{' :
                return peeked = PEEKED_BEGIN_OBJECT;
            default :
                pos--;// Don't consume the first character in a literal value.
    
        }
        int result = peekKeyword();
        if (result != PEEKED_NONE) {
            return result;
        }
        result = peekNumber();
        if (result != PEEKED_NONE) {
            return result;
        }
        if (!isLiteral(buffer[pos])) {
            throw syntaxError("Expected value");
        }
        checkLenient();
        return peeked = PEEKED_UNQUOTED;
    }
public void beginArray() throws IOException;
    public void beginObject() throws IOException;
    private void checkLenient() throws IOException;
    @Override
public void close() throws IOException;
    private void consumeNonExecutePrefix() throws IOException;
    public void endArray() throws IOException;
    public void endObject() throws IOException;
    private boolean fillBuffer(int minimum) throws IOException;
    public String getPath();
    public String getPreviousPath();
    public boolean hasNext() throws IOException;
    public final boolean isLenient();
    @SuppressWarnings("fallthrough")
private boolean isLiteral(char c) throws IOException;
    String locationString();
    public boolean nextBoolean() throws IOException;
    public double nextDouble() throws IOException;
    public int nextInt() throws IOException;
    public long nextLong() throws IOException;
    public String nextName() throws IOException;
    private int nextNonWhitespace(boolean throwOnEof) throws IOException;
    public void nextNull() throws IOException;
    public String nextString() throws IOException;
    public JsonToken peek() throws IOException;
    private int peekKeyword() throws IOException;
    private int peekNumber() throws IOException;
    public final void setLenient(boolean lenient);
    public void skipValue() throws IOException;
    private IOException syntaxError(String message) throws IOException;
    @Override
public String toString();
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

api document of class com.google.gson.stream.JsonReader: 

 * Reads a JSON (<a href="http://www.ietf.org/rfc/rfc7159.txt">RFC 7159</a>)
 * encoded value as a stream of tokens. This stream includes both literal
 * values (strings, numbers, booleans, and nulls) as well as the begin and
 * end delimiters of objects and arrays. The tokens are traversed in
 * depth-first order, the same order that they appear in the JSON document.
 * Within JSON objects, name/value pairs are represented by a single token.
 *
 * <h2>Parsing JSON</h2>
 * To create a recursive descent parser for your own JSON streams, first create
 * an entry point method that creates a {@code JsonReader}.
 *
 * <p>Next, create handler methods for each structure in your JSON text. You'll
 * need a method for each object type and for each array type.
 * <ul>
 *   <li>Within <strong>array handling</strong> methods, first call {@link
 *       #beginArray} to consume the array's opening bracket. Then create a
 *       while loop that accumulates values, terminating when {@link #hasNext}
 *       is false. Finally, read the array's closing bracket by calling {@link
 *       #endArray}.
 *   <li>Within <strong>object handling</strong> methods, first call {@link
 *       #beginObject} to consume the object's opening brace. Then create a
 *       while loop that assigns values to local variables based on their name.
 *       This loop should terminate when {@link #hasNext} is false. Finally,
 *       read the object's closing brace by calling {@link #endObject}.
 * </ul>
 * <p>When a nested object or array is encountered, delegate to the
 * corresponding handler method.
 *
 * <p>When an unknown name is encountered, strict parsers should fail with an
 * exception. Lenient parsers should call {@link #skipValue()} to recursively
 * skip the value's nested tokens, which may otherwise conflict.
 *
 * <p>If a value may be null, you should first check using {@link #peek()}.
 * Null literals can be consumed using either {@link #nextNull()} or {@link
 * #skipValue()}.
 *
 * <h2>Example</h2>
 * Suppose we'd like to parse a stream of messages such as the following: <pre> {@code
 * [
 *   {
 *     "id": 912345678901,
 *     "text": "How do I read a JSON stream in Java?",
 *     "geo": null,
 *     "user": {
 *       "name": "json_newb",
 *       "followers_count": 41
 *      }
 *   },
 *   {
 *     "id": 912345678902,
 *     "text": "@json_newb just use JsonReader!",
 *     "geo": [50.454722, -104.606667],
 *     "user": {
 *       "name": "jesse",
 *       "followers_count": 2
 *     }
 *   }
 * ]}</pre>
 * This code implements the parser for the above structure: <pre>   {@code
 *
 *   public List<Message> readJsonStream(InputStream in) throws IOException {
 *     JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
 *     try {
 *       return readMessagesArray(reader);
 *     } finally {
 *       reader.close();
 *     }
 *   }
 *
 *   public List<Message> readMessagesArray(JsonReader reader) throws IOException {
 *     List<Message> messages = new ArrayList<>();
 *
 *     reader.beginArray();
 *     while (reader.hasNext()) {
 *       messages.add(readMessage(reader));
 *     }
 *     reader.endArray();
 *     return messages;
 *   }
 *
 *   public Message readMessage(JsonReader reader) throws IOException {
 *     long id = -1;
 *     String text = null;
 *     User user = null;
 *     List<Double> geo = null;
 *
 *     reader.beginObject();
 *     while (reader.hasNext()) {
 *       String name = reader.nextName();
 *       if (name.equals("id")) {
 *         id = reader.nextLong();
 *       } else if (name.equals("text")) {
 *         text = reader.nextString();
 *       } else if (name.equals("geo") && reader.peek() != JsonToken.NULL) {
 *         geo = readDoublesArray(reader);
 *       } else if (name.equals("user")) {
 *         user = readUser(reader);
 *       } else {
 *         reader.skipValue();
 *       }
 *     }
 *     reader.endObject();
 *     return new Message(id, text, user, geo);
 *   }
 *
 *   public List<Double> readDoublesArray(JsonReader reader) throws IOException {
 *     List<Double> doubles = new ArrayList<>();
 *
 *     reader.beginArray();
 *     while (reader.hasNext()) {
 *       doubles.add(reader.nextDouble());
 *     }
 *     reader.endArray();
 *     return doubles;
 *   }
 *
 *   public User readUser(JsonReader reader) throws IOException {
 *     String username = null;
 *     int followersCount = -1;
 *
 *     reader.beginObject();
 *     while (reader.hasNext()) {
 *       String name = reader.nextName();
 *       if (name.equals("name")) {
 *         username = reader.nextString();
 *       } else if (name.equals("followers_count")) {
 *         followersCount = reader.nextInt();
 *       } else {
 *         reader.skipValue();
 *       }
 *     }
 *     reader.endObject();
 *     return new User(username, followersCount);
 *   }}</pre>
 *
 * <h2>Number Handling</h2>
 * This reader permits numeric values to be read as strings and string values to
 * be read as numbers. For example, both elements of the JSON array {@code
 * [1, "1"]} may be read using either {@link #nextInt} or {@link #nextString}.
 * This behavior is intended to prevent lossy numeric conversions: double is
 * JavaScript's only numeric type and very large values like {@code
 * 9007199254740993} cannot be represented exactly on that platform. To minimize
 * precision loss, extremely large values should be written and read as strings
 * in JSON.
 *
 * <h2 id="nonexecuteprefix">Non-Execute Prefix</h2>
 * Web servers that serve private data using JSON may be vulnerable to <a
 * href="http://en.wikipedia.org/wiki/JSON#Cross-site_request_forgery">Cross-site
 * request forgery</a> attacks. In such an attack, a malicious site gains access
 * to a private JSON file by executing it with an HTML {@code <script>} tag.
 *
 * <p>Prefixing JSON files with <code>")]}'\n"</code> makes them non-executable
 * by {@code <script>} tags, disarming the attack. Since the prefix is malformed
 * JSON, strict parsing fails when it is encountered. This class permits the
 * non-execute prefix when {@link #setLenient(boolean) lenient parsing} is
 * enabled.
 *
 * <p>Each {@code JsonReader} may be used to read a single JSON stream. Instances
 * of this class are not thread safe.
 *
 * @author Jesse Wilson
 * @since 1.6
 

return type: 
int

dependent classes: 
class `com.google.gson.stream.JsonReader`:
	dependent fields:
		char[] com.google.gson.stream.JsonReader.buffer;
		int com.google.gson.stream.JsonReader.stackSize;
		int com.google.gson.stream.JsonReader.PEEKED_UNQUOTED_NAME;
		int com.google.gson.stream.JsonReader.PEEKED_DOUBLE_QUOTED_NAME;
		int com.google.gson.stream.JsonReader.PEEKED_SINGLE_QUOTED;
		int com.google.gson.stream.JsonReader.PEEKED_SINGLE_QUOTED_NAME;
		int[] com.google.gson.stream.JsonReader.stack;
		int com.google.gson.stream.JsonReader.peeked;
		int com.google.gson.stream.JsonReader.PEEKED_END_ARRAY;
		int com.google.gson.stream.JsonReader.PEEKED_NONE;
		int com.google.gson.stream.JsonReader.PEEKED_EOF;
		int com.google.gson.stream.JsonReader.pos;
		int com.google.gson.stream.JsonReader.PEEKED_BEGIN_OBJECT;
		int com.google.gson.stream.JsonReader.PEEKED_NULL;
		int com.google.gson.stream.JsonReader.limit;
		int com.google.gson.stream.JsonReader.PEEKED_DOUBLE_QUOTED;
		boolean com.google.gson.stream.JsonReader.lenient;
		int com.google.gson.stream.JsonReader.PEEKED_BEGIN_ARRAY;
		int com.google.gson.stream.JsonReader.PEEKED_END_OBJECT;
		int com.google.gson.stream.JsonReader.PEEKED_UNQUOTED;
	related functions:
		method `public void skipValue()` returns `void`, related with `com.google.gson.stream.JsonReader.doPeek()`, api document: 
   * Skips the next value recursively. This method is intended for use when
   * the JSON token stream contains unrecognized or unhandled values.
   *
   * <p>The behavior depends on the type of the next JSON token:
   * <ul>
   *   <li>Start of a JSON array or object: It and all of its nested values are skipped.</li>
   *   <li>Primitive value (for example a JSON number): The primitive value is skipped.</li>
   *   <li>Property name: Only the name but not the value of the property is skipped.
   *   {@code skipValue()} has to be called again to skip the property value as well.</li>
   *   <li>End of a JSON array or object: Only this end token is skipped.</li>
   *   <li>End of JSON document: Skipping has no effect, the next token continues to be the
   *   end of the document.</li>
   * </ul>
   ;
		method `private char readEscapeCharacter()` returns `char`, related with `com.google.gson.stream.JsonReader.doPeek()`, api document: 
   * Unescapes the character identified by the character or characters that
   * immediately follow a backslash. The backslash '\' should have already
   * been read. This supports both unicode escapes "u000A" and two-character
   * escapes "\n".
   *
   * @throws NumberFormatException if any unicode escape sequences are
   *     malformed.
   ;
		method `private int nextNonWhitespace(boolean)` returns `int`, related with `com.google.gson.stream.JsonReader.doPeek()`, api document: 
   * Returns the next character in the stream that is neither whitespace nor a
   * part of a comment. When this returns, the returned character is always at
   * {@code buffer[pos-1]}; this means the caller can always push back the
   * returned character by decrementing {@code pos}.
   ;
		method `private void skipUnquotedValue()` returns `void`, related with `com.google.gson.stream.JsonReader.doPeek()`;
		method `private void skipQuotedValue(char)` returns `void`, related with `com.google.gson.stream.JsonReader.doPeek()`;
		method `public double nextDouble()` returns `double`, related with `com.google.gson.stream.JsonReader.doPeek()`, api document: 
   * Returns the {@link JsonToken#NUMBER double} value of the next token,
   * consuming it. If the next token is a string, this method will attempt to
   * parse it as a double using {@link Double#parseDouble(String)}.
   *
   * @throws IllegalStateException if the next token is not a literal value.
   * @throws NumberFormatException if the next literal value cannot be parsed
   *     as a double.
   * @throws MalformedJsonException if the next literal value is NaN or Infinity
   *     and this reader is not {@link #setLenient(boolean) lenient}.
   ;
		method `private int peekKeyword()` returns `int`, related with `com.google.gson.stream.JsonReader.doPeek()`;
		method `private int peekNumber()` returns `int`, related with `com.google.gson.stream.JsonReader.doPeek()`;
class `com.google.gson.stream.JsonScope`:
	dependent fields:
		int com.google.gson.stream.JsonScope.NONEMPTY_ARRAY;
		int com.google.gson.stream.JsonScope.EMPTY_OBJECT;
		int com.google.gson.stream.JsonScope.NONEMPTY_DOCUMENT;
		int com.google.gson.stream.JsonScope.EMPTY_ARRAY;
		int com.google.gson.stream.JsonScope.NONEMPTY_OBJECT;
		int com.google.gson.stream.JsonScope.CLOSED;
		int com.google.gson.stream.JsonScope.DANGLING_NAME;
		int com.google.gson.stream.JsonScope.EMPTY_DOCUMENT;
	



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.