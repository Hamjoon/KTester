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
 

api document of method skipValue(): 

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
   

dependent classes: 
class `com.google.gson.stream.JsonReader`:
	dependent fields:
		int com.google.gson.stream.JsonReader.stackSize;
		int com.google.gson.stream.JsonReader.pos;
		int[] com.google.gson.stream.JsonReader.pathIndices;
		int com.google.gson.stream.JsonReader.PEEKED_DOUBLE_QUOTED_NAME;
		int com.google.gson.stream.JsonReader.PEEKED_DOUBLE_QUOTED;
		int com.google.gson.stream.JsonReader.PEEKED_BEGIN_ARRAY;
		int com.google.gson.stream.JsonReader.PEEKED_NUMBER;
		int com.google.gson.stream.JsonReader.PEEKED_BEGIN_OBJECT;
		int com.google.gson.stream.JsonReader.PEEKED_UNQUOTED;
		int com.google.gson.stream.JsonReader.PEEKED_EOF;
		java.lang.String[] com.google.gson.stream.JsonReader.pathNames;
		int com.google.gson.stream.JsonReader.PEEKED_SINGLE_QUOTED_NAME;
		int com.google.gson.stream.JsonReader.PEEKED_SINGLE_QUOTED;
		int com.google.gson.stream.JsonReader.PEEKED_END_ARRAY;
		int com.google.gson.stream.JsonReader.peekedNumberLength;
		int com.google.gson.stream.JsonReader.PEEKED_END_OBJECT;
		int com.google.gson.stream.JsonReader.peeked;
		int com.google.gson.stream.JsonReader.PEEKED_UNQUOTED_NAME;
		int com.google.gson.stream.JsonReader.PEEKED_NONE;
	related functions:
		method `public boolean hasNext()` returns `boolean`, related with `com.google.gson.stream.JsonReader.skipValue()`, api document: 
   * Returns true if the current array or object has another element.
   ;
		method ` int doPeek()` returns `int`, related with `com.google.gson.stream.JsonReader.skipValue()`;
		method `public String nextString()` returns `java.lang.String`, related with `com.google.gson.stream.JsonReader.skipValue()`, api document: 
   * Returns the {@link JsonToken#STRING string} value of the next token,
   * consuming it. If the next token is a number, this method will return its
   * string form.
   *
   * @throws IllegalStateException if the next token is not a string or if
   *     this reader is closed.
   ;
		method `public String nextName()` returns `java.lang.String`, related with `com.google.gson.stream.JsonReader.skipValue()`, api document: 
   * Returns the next token, a {@link JsonToken#NAME property name}, and consumes it.
   *
   * @throws IOException if the next token in the stream is not a property
   *     name.
   ;
		method `public void beginArray()` returns `void`, related with `com.google.gson.stream.JsonReader.skipValue()`, api document: 
   * Consumes the next token from the JSON stream and asserts that it is the
   * beginning of a new array.
   ;
		method `public void beginObject()` returns `void`, related with `com.google.gson.stream.JsonReader.skipValue()`, api document: 
   * Consumes the next token from the JSON stream and asserts that it is the
   * beginning of a new object.
   ;
		method `public JsonToken peek()` returns `com.google.gson.stream.JsonToken`, related with `com.google.gson.stream.JsonReader.skipValue()`, api document: 
   * Returns the type of the next token without consuming it.
   ;
		method `public void endObject()` returns `void`, related with `com.google.gson.stream.JsonReader.skipValue()`, api document: 
   * Consumes the next token from the JSON stream and asserts that it is the
   * end of the current object.
   ;
class `com.google.gson.stream.JsonScope`:
	dependent fields:
		int com.google.gson.stream.JsonScope.EMPTY_ARRAY;
		int com.google.gson.stream.JsonScope.EMPTY_OBJECT;
	



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.