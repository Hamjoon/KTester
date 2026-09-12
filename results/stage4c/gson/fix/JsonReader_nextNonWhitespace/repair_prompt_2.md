


You are an experienced Java developer tasked with fixing a piece of code that contains execution errors. Your task is to resolve these issues after analyzing the code and its execution feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any logical errors.
2. Analyze the execution feedback to understand the specific errors and their locations within the code.
3. Apply necessary corrections to the code based on the feedback, and output the compelete correct code.
The Java code you need to fix is provided below:
```java
package com.google.gson.stream;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.io.EOFException;

@ExtendWith(MockitoExtension.class)
class JsonReader_nextNonWhitespace_Test {

    private JsonReader jsonReader;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize JsonReader with a mocked or simple Reader
        Reader reader = new StringReader("   \n\t\r /# This is a comment\nabc");
        jsonReader = new JsonReader(reader);
        // Set lenient to handle comments and malformed JSON
        jsonReader.setLenient(true);
    }

    @Test
    void testNextNonWhitespace_throwsEOFException() throws Exception {
        // Given
        Reader reader = new StringReader("");
        jsonReader = new JsonReader(reader);
        // Set lenient to handle comments and malformed JSON
        jsonReader.setLenient(true);
        // When & Then
        Method nextNonWhitespaceMethod = JsonReader.class.getDeclaredMethod("nextNonWhitespace", boolean.class);
        nextNonWhitespaceMethod.setAccessible(true);
        assertThrows(EOFException.class, () -> {
            try {
                nextNonWhitespaceMethod.invoke(jsonReader, true);
            } catch (InvocationTargetException e) {
                // Unwrap the cause of InvocationTargetException
                throw e.getCause();
            }
        });
    }

    @Test
    void testNextNonWhitespace_returnsNonWhitespaceCharacter() throws Exception {
        // Given
        Reader reader = new StringReader("   \n\t\r /# This is a comment\nabc");
        jsonReader = new JsonReader(reader);
        // Set lenient to handle comments and malformed JSON
        jsonReader.setLenient(true);
        // When
        Method nextNonWhitespaceMethod = JsonReader.class.getDeclaredMethod("nextNonWhitespace", boolean.class);
        nextNonWhitespaceMethod.setAccessible(true);
        int result = (int) nextNonWhitespaceMethod.invoke(jsonReader, true);
        // Then
        assertEquals('a', result);
    }

    @Test
    void testNextNonWhitespace_returnsNegativeOneWhenEOFAndThrowOnEofFalse() throws Exception {
        // Given
        Reader reader = new StringReader("");
        jsonReader = new JsonReader(reader);
        // Set lenient to handle comments and malformed JSON
        jsonReader.setLenient(true);
        // When
        Method nextNonWhitespaceMethod = JsonReader.class.getDeclaredMethod("nextNonWhitespace", boolean.class);
        nextNonWhitespaceMethod.setAccessible(true);
        int result = (int) nextNonWhitespaceMethod.invoke(jsonReader, false);
        // Then
        assertEquals(-1, result);
    }

    @Test
    void testNextNonWhitespace_unterminatedCommentThrowsIOException() throws Exception {
        // Given
        Reader reader = new StringReader("/* unterminated comment");
        jsonReader = new JsonReader(reader);
        // Set lenient to handle comments and malformed JSON
        jsonReader.setLenient(true);
        // When & Then
        Method nextNonWhitespaceMethod = JsonReader.class.getDeclaredMethod("nextNonWhitespace", boolean.class);
        nextNonWhitespaceMethod.setAccessible(true);
        assertThrows(IOException.class, () -> {
            try {
                nextNonWhitespaceMethod.invoke(jsonReader, true);
            } catch (InvocationTargetException e) {
                // Unwrap the cause of InvocationTargetException
                throw e.getCause();
            }
        });
    }

    @Test
    void testNextNonWhitespace_unterminatedCommentThrowsIOExceptionWhenThrowOnEofFalse() throws Exception {
        // Given
        Reader reader = new StringReader("/* unterminated comment");
        jsonReader = new JsonReader(reader);
        // Set lenient to handle comments and malformed JSON
        jsonReader.setLenient(true);
        // When & Then
        Method nextNonWhitespaceMethod = JsonReader.class.getDeclaredMethod("nextNonWhitespace", boolean.class);
        nextNonWhitespaceMethod.setAccessible(true);
        assertThrows(IOException.class, () -> {
            try {
                nextNonWhitespaceMethod.invoke(jsonReader, false);
            } catch (InvocationTargetException e) {
                // Unwrap the cause of InvocationTargetException
                throw e.getCause();
            }
        });
    }
}

```

Corresponding execution feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended

╷
├─ JUnit Jupiter ✔
│  └─ JsonReader_nextNonWhitespace_Test ✔
│     ├─ testNextNonWhitespace_throwsEOFException() ✔
│     ├─ testNextNonWhitespace_unterminatedCommentThrowsIOExceptionWhenThrowOnEofFalse() ✔
│     ├─ testNextNonWhitespace_returnsNonWhitespaceCharacter() ✘ expected: <97> but was: <47>
│     ├─ testNextNonWhitespace_unterminatedCommentThrowsIOException() ✔
│     └─ testNextNonWhitespace_returnsNegativeOneWhenEOFAndThrowOnEofFalse() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Failures (1):
  JUnit Jupiter:JsonReader_nextNonWhitespace_Test:testNextNonWhitespace_returnsNonWhitespaceCharacter()
    MethodSource [className = 'com.google.gson.stream.JsonReader_nextNonWhitespace_Test', methodName = 'testNextNonWhitespace_returnsNonWhitespaceCharacter', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <97> but was: <47>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:150)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:145)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:531)
       com.google.gson.stream.JsonReader_nextNonWhitespace_Test.testNextNonWhitespace_returnsNonWhitespaceCharacter(JsonReader_nextNonWhitespace_Test.java:62)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]

Test run finished after 381 ms
[         4 containers found      ]
[         0 containers skipped    ]
[         4 containers started    ]
[         0 containers aborted    ]
[         4 containers successful ]
[         0 containers failed     ]
[         5 tests found           ]
[         0 tests skipped         ]
[         5 tests started         ]
[         0 tests aborted         ]
[         4 tests successful      ]
[         1 tests failed          ]


```


And you can use the following related information to assist you in your analysis and correction:

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
 

api document of method nextNonWhitespace(boolean): 

   * Returns the next character in the stream that is neither whitespace nor a
   * part of a comment. When this returns, the returned character is always at
   * {@code buffer[pos-1]}; this means the caller can always push back the
   * returned character by decrementing {@code pos}.
   

return type: 
int

dependent classes: 
class `com.google.gson.stream.JsonReader`:
	dependent fields:
		char[] com.google.gson.stream.JsonReader.buffer;
		int com.google.gson.stream.JsonReader.limit;
		int com.google.gson.stream.JsonReader.lineStart;
		int com.google.gson.stream.JsonReader.pos;
		int com.google.gson.stream.JsonReader.lineNumber;
	related functions:
		method `private void skipQuotedValue(char)` returns `void`, related with `com.google.gson.stream.JsonReader.nextNonWhitespace(boolean)`;
		method `private String nextQuotedValue(char)` returns `java.lang.String`, related with `com.google.gson.stream.JsonReader.nextNonWhitespace(boolean)`, api document: 
   * Returns the string up to but not including {@code quote}, unescaping any
   * character escape sequences encountered along the way. The opening quote
   * should have already been read. This consumes the closing quote, but does
   * not include it in the returned string.
   *
   * @param quote either ' or ".
   * @throws NumberFormatException if any unicode escape sequences are
   *     malformed.
   ;
		method `private String nextUnquotedValue()` returns `java.lang.String`, related with `com.google.gson.stream.JsonReader.nextNonWhitespace(boolean)`, api document: 
   * Returns an unquoted value as a string.
   ;
		method `private boolean fillBuffer(int)` returns `boolean`, related with `com.google.gson.stream.JsonReader.nextNonWhitespace(boolean)`, api document: 
   * Returns true once {@code limit - pos >= minimum}. If the data is
   * exhausted before that many characters are available, this returns
   * false.
   ;
		method `private void skipUnquotedValue()` returns `void`, related with `com.google.gson.stream.JsonReader.nextNonWhitespace(boolean)`;
		method `private char readEscapeCharacter()` returns `char`, related with `com.google.gson.stream.JsonReader.nextNonWhitespace(boolean)`, api document: 
   * Unescapes the character identified by the character or characters that
   * immediately follow a backslash. The backslash '\' should have already
   * been read. This supports both unicode escapes "u000A" and two-character
   * escapes "\n".
   *
   * @throws NumberFormatException if any unicode escape sequences are
   *     malformed.
   ;
		method `private void consumeNonExecutePrefix()` returns `void`, related with `com.google.gson.stream.JsonReader.nextNonWhitespace(boolean)`, api document: 
   * Consumes the non-execute prefix if it exists.
   ;
		method `private boolean skipTo(String)` returns `boolean`, related with `com.google.gson.stream.JsonReader.nextNonWhitespace(boolean)`, api document: 
   * @param toFind a string to search for. Must not contain a newline.
   ;



Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 
