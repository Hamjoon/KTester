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
Here is the code of focal method, `peekNumber()`, with the focal class `JsonReader_peekNumber_Test`:
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
    private static final long MIN_INCOMPLETE_INTEGER = Long.MIN_VALUE / 10;
    private static final int PEEKED_NONE = 0;
    private static final int PEEKED_LONG = 15;
    private static final int PEEKED_NUMBER = 16;
    private static final int NUMBER_CHAR_NONE = 0;
    private static final int NUMBER_CHAR_SIGN = 1;
    private static final int NUMBER_CHAR_DIGIT = 2;
    private static final int NUMBER_CHAR_DECIMAL = 3;
    private static final int NUMBER_CHAR_FRACTION_DIGIT = 4;
    private static final int NUMBER_CHAR_EXP_E = 5;
    private static final int NUMBER_CHAR_EXP_SIGN = 6;
    private static final int NUMBER_CHAR_EXP_DIGIT = 7;
    static final int BUFFER_SIZE = 1024;
    private final char[] buffer = new char[BUFFER_SIZE];
    private int pos = 0;
    private int limit = 0;
    int peeked = PEEKED_NONE;
    private long peekedLong;
    private int peekedNumberLength;
    private int peekNumber() throws IOException {
        // Like nextNonWhitespace, this uses locals 'p' and 'l' to save inner-loop field access.
        char[] buffer = this.buffer;
        int p = pos;
        int l = limit;
        long value = 0;// Negative to accommodate Long.MIN_VALUE more easily.
    
        boolean negative = false;
        boolean fitsInLong = true;
        int last = NUMBER_CHAR_NONE;
        int i = 0;
        charactersOfNumber : for (; true; i++) {
            if ((p + i) == l) {
                if (i == buffer.length) {
                    // Though this looks like a well-formed number, it's too long to continue reading. Give up
                    // and let the application handle this as an unquoted literal.
                    return PEEKED_NONE;
                }
                if (!fillBuffer(i + 1)) {
                    break;
                }
                p = pos;
                l = limit;
            }
            char c = buffer[p + i];
            switch (c) {
                case '-' :
                    if (last == NUMBER_CHAR_NONE) {
                        negative = true;
                        last = NUMBER_CHAR_SIGN;
                        continue;
                    } else if (last == NUMBER_CHAR_EXP_E) {
                        last = NUMBER_CHAR_EXP_SIGN;
                        continue;
                    }
                    return PEEKED_NONE;
                case '+' :
                    if (last == NUMBER_CHAR_EXP_E) {
                        last = NUMBER_CHAR_EXP_SIGN;
                        continue;
                    }
                    return PEEKED_NONE;
                case 'e' :
                case 'E' :
                    if ((last == NUMBER_CHAR_DIGIT) || (last == NUMBER_CHAR_FRACTION_DIGIT)) {
                        last = NUMBER_CHAR_EXP_E;
                        continue;
                    }
                    return PEEKED_NONE;
                case '.' :
                    if (last == NUMBER_CHAR_DIGIT) {
                        last = NUMBER_CHAR_DECIMAL;
                        continue;
                    }
                    return PEEKED_NONE;
                default :
                    if ((c < '0') || (c > '9')) {
                        if (!isLiteral(c)) {
                            break charactersOfNumber;
                        }
                        return PEEKED_NONE;
                    }
                    if ((last == NUMBER_CHAR_SIGN) || (last == NUMBER_CHAR_NONE)) {
                        value = -(c - '0');
                        last = NUMBER_CHAR_DIGIT;
                    } else if (last == NUMBER_CHAR_DIGIT) {
                        if (value == 0) {
                            return PEEKED_NONE;// Leading '0' prefix is not allowed (since it could be octal).
    
                        }
                        long newValue = (value * 10) - (c - '0');
                        fitsInLong &= (value > MIN_INCOMPLETE_INTEGER) || ((value == MIN_INCOMPLETE_INTEGER) && (newValue < value));
                        value = newValue;
                    } else if (last == NUMBER_CHAR_DECIMAL) {
                        last = NUMBER_CHAR_FRACTION_DIGIT;
                    } else if ((last == NUMBER_CHAR_EXP_E) || (last == NUMBER_CHAR_EXP_SIGN)) {
                        last = NUMBER_CHAR_EXP_DIGIT;
                    }
            }
        }
        // We've read a complete number. Decide if it's a PEEKED_LONG or a PEEKED_NUMBER.
        // Don't store -0 as long; user might want to read it as double -0.0
        // Don't try to convert Long.MIN_VALUE to positive long; it would overflow MAX_VALUE
        if ((((last == NUMBER_CHAR_DIGIT) && fitsInLong) && ((value != Long.MIN_VALUE) || negative)) && ((value != 0) || (!negative))) {
            peekedLong = (negative) ? value : -value;
            pos += i;
            return peeked = PEEKED_LONG;
        } else if (((last == NUMBER_CHAR_DIGIT) || (last == NUMBER_CHAR_FRACTION_DIGIT)) || (last == NUMBER_CHAR_EXP_DIGIT)) {
            peekedNumberLength = i;
            return peeked = PEEKED_NUMBER;
        } else {
            return PEEKED_NONE;
        }
    }
public void beginArray() throws IOException;
    public void beginObject() throws IOException;
    @Override
public void close() throws IOException;
    @SuppressWarnings("fallthrough")
int doPeek() throws IOException;
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
    public void nextNull() throws IOException;
    public String nextString() throws IOException;
    public JsonToken peek() throws IOException;
    public final void setLenient(boolean lenient);
    public void skipValue() throws IOException;
    @Override
public String toString();
}
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
 

constructors for class `com.google.gson.stream.JsonReader`: 
params: 
body:
```java
  public JsonReader(Reader in) {
    this.in = Objects.requireNonNull(in, "in == null");
  }

```

existing test class: 
```java
/*
 * Copyright (C) 2010 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.gson.stream;

import static com.google.gson.stream.JsonToken.BEGIN_ARRAY;
import static com.google.gson.stream.JsonToken.BEGIN_OBJECT;
import static com.google.gson.stream.JsonToken.BOOLEAN;
import static com.google.gson.stream.JsonToken.END_ARRAY;
import static com.google.gson.stream.JsonToken.END_OBJECT;
import static com.google.gson.stream.JsonToken.NAME;
import static com.google.gson.stream.JsonToken.NULL;
import static com.google.gson.stream.JsonToken.NUMBER;
import static com.google.gson.stream.JsonToken.STRING;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;
import org.junit.Ignore;
import org.junit.Test;

@SuppressWarnings("resource")
public final class JsonReaderTest {
  @Test
  public void testReadArray() throws IOException {
    JsonReader reader = new JsonReader(reader("[true, true]"));
    reader.beginArray();
    assertEquals(true, reader.nextBoolean());
    assertEquals(true, reader.nextBoolean());
    reader.endArray();
    assertEquals(JsonToken.END_DOCUMENT, reader.peek());
  }

  @Test
  public void testReadEmptyArray() throws IOException {
    JsonReader reader = new JsonReader(reader("[]"));
    reader.beginArray();
    assertFalse(reader.hasNext());
    reader.endArray();
    assertEquals(JsonToken.END_DOCUMENT, reader.peek());
  }

  @Test
  public void testReadObject() throws IOException {
    JsonReader reader = new JsonReader(reader(
        "{\"a\": \"android\", \"b\": \"banana\"}"));
    reader.beginObject();
    assertEquals("a", reader.nextName());
    assertEquals("android", reader.nextString());
    assertEquals("b", reader.nextName());
    assertEquals("banana", reader.nextString());
    reader.endObject();
    assertEquals(JsonToken.END_DOCUMENT, reader.peek());
  }

  @Test
  public void testReadEmptyObject() throws IOException {
    JsonReader reader = new JsonReader(reader("{}"));
    reader.beginObject();
    assertFalse(reader.hasNext());
    reader.endObject();
    assertEquals(JsonToken.END_DOCUMENT, reader.peek());
  }

  @Test
  public void testHasNextEndOfDocument() throws IOException {
    JsonReader reader = new JsonReader(reader("{}"));
    reader.beginObject();
    reader.endObject();
    assertFalse(reader.hasNext());
  }

  @Test
  public void testSkipArray() throws IOException {
    JsonReader reader = new JsonReader(reader(
        "{\"a\": [\"one\", \"two\", \"three\"], \"b\": 123}"));
    reader.beginObject();
    assertEquals("a", reader.nextName());
    reader.skipValue();
    assertEquals("b", reader.nextName());
    assertEquals(123, reader.nextInt());
......
      } else if (expectation == BEGIN_ARRAY) {
        reader.beginArray();
      } else if (expectation == END_OBJECT) {
        reader.endObject();
      } else if (expectation == END_ARRAY) {
        reader.endArray();
      } else if (expectation == NAME) {
        assertEquals("name", reader.nextName());
      } else if (expectation == BOOLEAN) {
        assertEquals(false, reader.nextBoolean());
      } else if (expectation == STRING) {
        assertEquals("string", reader.nextString());
      } else if (expectation == NUMBER) {
        assertEquals(123, reader.nextInt());
      } else if (expectation == NULL) {
        reader.nextNull();
      } else if (expectation == IOException.class) {
        try {
          reader.peek();
          fail();
        } catch (IOException expected) {
        }
      } else {
        throw new AssertionError();
      }
    }
  }

  /**
   * Returns a reader that returns one character at a time.
   */
  private Reader reader(final String s) {
    /* if (true) */ return new StringReader(s);
    /* return new Reader() {
      int position = 0;
      @Override public int read(char[] buffer, int offset, int count) throws IOException {
        if (position == s.length()) {
          return -1;
        } else if (count > 0) {
          buffer[offset] = s.charAt(position++);
          return 1;
        } else {
          throw new IllegalArgumentException();
        }
      }
      @Override public void close() throws IOException {
      }
    }; */
  }
}
```

invoke examples of focal method: 
example 1:
```java
  @SuppressWarnings("fallthrough")
  int doPeek() throws IOException {
    int peekStack = stack[stackSize - 1];
    if (peekStack == JsonScope.EMPTY_ARRAY) {
      stack[stackSize - 1] = JsonScope.NONEMPTY_ARRAY;
    int c = nextNonWhitespace(true);
    switch (c) {
      pos--; // Don't consume the first character in a literal value.
    int result = peekKeyword();
    if (result != PEEKED_NONE) {
    result = peekNumber();
  }
```



@input{test class template}
```java
package com.google.gson.stream;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
// Add other imports if necessary

@ExtendWith(MockitoExtension.class)
class JsonReader_peekNumber_Test {
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