


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
import java.io.StringReader;
import java.lang.reflect.Method;
import java.io.EOFException;

@ExtendWith(MockitoExtension.class)
class JsonReader_nextNonWhitespace_Test {

    private JsonReader jsonReader;

    private StringReader stringReader;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the StringReader and JsonReader before each test
        stringReader = new StringReader("");
        jsonReader = new JsonReader(stringReader);
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup if necessary after each test
        if (jsonReader != null) {
            try {
                jsonReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    void testNextNonWhitespaceWithValidInput() throws Exception {
        // Given
        stringReader = new StringReader("  \n   {\"key\": \"value\"}  ");
        jsonReader = new JsonReader(stringReader);
        // Allow lenient parsing
        jsonReader.setLenient(true);
        // When
        Method method = JsonReader.class.getDeclaredMethod("nextNonWhitespace", boolean.class);
        method.setAccessible(true);
        int result = (int) method.invoke(jsonReader, true);
        // Then
        assertEquals('{', result);
    }

    @Test
    void testNextNonWhitespaceThrowsEOFException() throws Exception {
        // Given
        stringReader = new StringReader("");
        jsonReader = new JsonReader(stringReader);
        // Allow lenient parsing
        jsonReader.setLenient(true);
        // When & Then
        Method method = JsonReader.class.getDeclaredMethod("nextNonWhitespace", boolean.class);
        method.setAccessible(true);
        assertThrows(EOFException.class, () -> {
            method.invoke(jsonReader, true);
        });
    }

    @Test
    void testNextNonWhitespaceHandlesComment() throws Exception {
        // Given
        stringReader = new StringReader("// This is a comment\n{ \"key\": \"value\" }");
        jsonReader = new JsonReader(stringReader);
        // Allow lenient parsing
        jsonReader.setLenient(true);
        // When
        Method method = JsonReader.class.getDeclaredMethod("nextNonWhitespace", boolean.class);
        method.setAccessible(true);
        int result = (int) method.invoke(jsonReader, true);
        // Then
        assertEquals('{', result);
    }

    @Test
    void testNextNonWhitespaceHandlesWhitespace() throws Exception {
        // Given
        stringReader = new StringReader("   \t \n   [1, 2, 3]");
        jsonReader = new JsonReader(stringReader);
        // Allow lenient parsing
        jsonReader.setLenient(true);
        // When
        Method method = JsonReader.class.getDeclaredMethod("nextNonWhitespace", boolean.class);
        method.setAccessible(true);
        int result = (int) method.invoke(jsonReader, true);
        // Then
        assertEquals('[', result);
    }

    @Test
    void testNextNonWhitespaceHandlesEndOfInput() throws Exception {
        // Given
        stringReader = new StringReader("   \t \n   ");
        jsonReader = new JsonReader(stringReader);
        // Allow lenient parsing
        jsonReader.setLenient(true);
        // When
        Method method = JsonReader.class.getDeclaredMethod("nextNonWhitespace", boolean.class);
        method.setAccessible(true);
        int result = (int) method.invoke(jsonReader, false);
        // Then
        // -1 indicates end of input without throwing an exception
        assertEquals(-1, result);
    }

    /**
     * Test cases for nextNonWhitespace method with EOF handling.
     */
    @Test
    void testNextNonWhitespaceWithEOFHandling() throws Exception {
        // Test case where the method reaches the end of input and throws EOFException
        stringReader = new StringReader("");
        jsonReader = new JsonReader(stringReader);
        // Allow lenient parsing
        jsonReader.setLenient(true);
        Method method = JsonReader.class.getDeclaredMethod("nextNonWhitespace", boolean.class);
        method.setAccessible(true);
        // When & Then
        assertThrows(EOFException.class, () -> {
            method.invoke(jsonReader, true);
        });
        // Test case where the method reaches the end of input and returns -1
        stringReader = new StringReader("");
        jsonReader = new JsonReader(stringReader);
        // Allow lenient parsing
        jsonReader.setLenient(true);
        int result = (int) method.invoke(jsonReader, false);
        assertEquals(-1, result);
    }

    /**
     * Test cases for nextNonWhitespace method with whitespace handling.
     */
    @Test
    void testNextNonWhitespaceWithWhitespaceHandling() throws Exception {
        // Given input with only whitespace characters
        stringReader = new StringReader("   \t \n   ");
        jsonReader = new JsonReader(stringReader);
        // Allow lenient parsing
        jsonReader.setLenient(true);
        Method method = JsonReader.class.getDeclaredMethod("nextNonWhitespace", boolean.class);
        method.setAccessible(true);
        // When
        int result = (int) method.invoke(jsonReader, false);
        // Then
        // Expecting -1 since there are no non-whitespace characters
        assertEquals(-1, result);
    }

    /**
     * Test cases for nextNonWhitespace method with comments handling.
     */
    @Test
    void testNextNonWhitespaceWithCommentsHandling() throws Exception {
        // Given input with a single-line comment
        stringReader = new StringReader("// This is a comment\n{ \"key\": \"value\" }");
        jsonReader = new JsonReader(stringReader);
        // Allow lenient parsing
        jsonReader.setLenient(true);
        Method method = JsonReader.class.getDeclaredMethod("nextNonWhitespace", boolean.class);
        method.setAccessible(true);
        // When
        int result = (int) method.invoke(jsonReader, true);
        // Then
        // Expecting the first non-comment character
        assertEquals('{', result);
    }

    @Test
    void testNextNonWhitespaceWithMultiLineCommentHandling() throws Exception {
        // Given input with a multi-line comment
        stringReader = new StringReader("/* This is a multi-line comment */ { \"key\": \"value\" }");
        jsonReader = new JsonReader(stringReader);
        // Allow lenient parsing
        jsonReader.setLenient(true);
        Method method = JsonReader.class.getDeclaredMethod("nextNonWhitespace", boolean.class);
        method.setAccessible(true);
        // When
        int result = (int) method.invoke(jsonReader, true);
        // Then
        // Expecting the first non-comment character
        assertEquals('{', result);
    }

    /**
     * Test cases for nextNonWhitespace method with invalid input handling.
     */
    @Test
    void testNextNonWhitespaceWithInvalidInputHandling() throws Exception {
        // Given input with an unterminated multi-line comment
        stringReader = new StringReader("/* This is an unterminated comment");
        jsonReader = new JsonReader(stringReader);
        // Allow lenient parsing
        jsonReader.setLenient(true);
        Method method = JsonReader.class.getDeclaredMethod("nextNonWhitespace", boolean.class);
        method.setAccessible(true);
        // When & Then
        assertThrows(IOException.class, () -> {
            method.invoke(jsonReader, true);
        });
    }

    @Test
    void testNextNonWhitespaceWithInvalidCharacterHandling() throws Exception {
        // Given input with an invalid character
        stringReader = new StringReader("{ invalid character }");
        jsonReader = new JsonReader(stringReader);
        // Allow lenient parsing
        jsonReader.setLenient(true);
        Method method = JsonReader.class.getDeclaredMethod("nextNonWhitespace", boolean.class);
        method.setAccessible(true);
        // When & Then
        assertThrows(IOException.class, () -> {
            method.invoke(jsonReader, true);
        });
    }

    /**
     * Test cases for nextNonWhitespace method with valid character handling.
     */
    @Test
    void testNextNonWhitespaceWithValidCharacterHandling() throws Exception {
        // Given input with valid JSON characters
        stringReader = new StringReader("{ \"key\": \"value\" }");
        jsonReader = new JsonReader(stringReader);
        // Allow lenient parsing
        jsonReader.setLenient(true);
        Method method = JsonReader.class.getDeclaredMethod("nextNonWhitespace", boolean.class);
        method.setAccessible(true);
        // When
        int result = (int) method.invoke(jsonReader, false);
        // Then
        // Expecting the first valid character code
        assertEquals('{', result);
    }

    @Test
    void testNextNonWhitespaceWithValidCharacterHandlingEOF() throws Exception {
        // Given input with valid JSON characters
        stringReader = new StringReader("{ \"key\": \"value\" }");
        jsonReader = new JsonReader(stringReader);
        // Allow lenient parsing
        jsonReader.setLenient(true);
        Method method = JsonReader.class.getDeclaredMethod("nextNonWhitespace", boolean.class);
        method.setAccessible(true);
        // When
        int result = (int) method.invoke(jsonReader, true);
        // Then
        // Expecting the first valid character code even when throwOnEof is true
        assertEquals('{', result);
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
│     ├─ testNextNonWhitespaceHandlesWhitespace() ✔
│     ├─ testNextNonWhitespaceWithInvalidInputHandling() ✘ Unexpected exception type thrown, expected: <java.io.IOException> but was: <java.lang.reflect.InvocationTargetException>
│     ├─ testNextNonWhitespaceWithCommentsHandling() ✔
│     ├─ testNextNonWhitespaceWithValidCharacterHandlingEOF() ✔
│     ├─ testNextNonWhitespaceWithValidInput() ✔
│     ├─ testNextNonWhitespaceWithMultiLineCommentHandling() ✔
│     ├─ testNextNonWhitespaceThrowsEOFException() ✘ Unexpected exception type thrown, expected: <java.io.EOFException> but was: <java.lang.reflect.InvocationTargetException>
│     ├─ testNextNonWhitespaceHandlesComment() ✔
│     ├─ testNextNonWhitespaceWithValidCharacterHandling() ✔
│     ├─ testNextNonWhitespaceWithInvalidCharacterHandling() ✘ Expected java.io.IOException to be thrown, but nothing was thrown.
│     ├─ testNextNonWhitespaceHandlesEndOfInput() ✔
│     ├─ testNextNonWhitespaceWithWhitespaceHandling() ✔
│     └─ testNextNonWhitespaceWithEOFHandling() ✘ Unexpected exception type thrown, expected: <java.io.EOFException> but was: <java.lang.reflect.InvocationTargetException>
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Failures (4):
  JUnit Jupiter:JsonReader_nextNonWhitespace_Test:testNextNonWhitespaceWithInvalidInputHandling()
    MethodSource [className = 'com.google.gson.stream.JsonReader_nextNonWhitespace_Test', methodName = 'testNextNonWhitespaceWithInvalidInputHandling', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Unexpected exception type thrown, expected: <java.io.IOException> but was: <java.lang.reflect.InvocationTargetException>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:67)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3128)
       com.google.gson.stream.JsonReader_nextNonWhitespace_Test.testNextNonWhitespaceWithInvalidInputHandling(JsonReader_nextNonWhitespace_Test.java:208)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       com.google.gson.stream.JsonReader_nextNonWhitespace_Test.lambda$testNextNonWhitespaceWithInvalidInputHandling$2(JsonReader_nextNonWhitespace_Test.java:209)
       [...]
     Caused by: com.google.gson.stream.MalformedJsonException: Unterminated comment at line 1 column 34 path $
       com.google.gson.stream.JsonReader.syntaxError(JsonReader.java:1659)
       com.google.gson.stream.JsonReader.nextNonWhitespace(JsonReader.java:1423)
       [...]
  JUnit Jupiter:JsonReader_nextNonWhitespace_Test:testNextNonWhitespaceThrowsEOFException()
    MethodSource [className = 'com.google.gson.stream.JsonReader_nextNonWhitespace_Test', methodName = 'testNextNonWhitespaceThrowsEOFException', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Unexpected exception type thrown, expected: <java.io.EOFException> but was: <java.lang.reflect.InvocationTargetException>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:67)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3128)
       com.google.gson.stream.JsonReader_nextNonWhitespace_Test.testNextNonWhitespaceThrowsEOFException(JsonReader_nextNonWhitespace_Test.java:65)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       com.google.gson.stream.JsonReader_nextNonWhitespace_Test.lambda$testNextNonWhitespaceThrowsEOFException$0(JsonReader_nextNonWhitespace_Test.java:66)
       [...]
     Caused by: java.io.EOFException: End of input at line 1 column 1 path $
       com.google.gson.stream.JsonReader.nextNonWhitespace(JsonReader.java:1457)
       [...]
  JUnit Jupiter:JsonReader_nextNonWhitespace_Test:testNextNonWhitespaceWithInvalidCharacterHandling()
    MethodSource [className = 'com.google.gson.stream.JsonReader_nextNonWhitespace_Test', methodName = 'testNextNonWhitespaceWithInvalidCharacterHandling', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Expected java.io.IOException to be thrown, but nothing was thrown.
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:73)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3128)
       com.google.gson.stream.JsonReader_nextNonWhitespace_Test.testNextNonWhitespaceWithInvalidCharacterHandling(JsonReader_nextNonWhitespace_Test.java:223)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:767)
       [...]
  JUnit Jupiter:JsonReader_nextNonWhitespace_Test:testNextNonWhitespaceWithEOFHandling()
    MethodSource [className = 'com.google.gson.stream.JsonReader_nextNonWhitespace_Test', methodName = 'testNextNonWhitespaceWithEOFHandling', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Unexpected exception type thrown, expected: <java.io.EOFException> but was: <java.lang.reflect.InvocationTargetException>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:67)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3128)
       com.google.gson.stream.JsonReader_nextNonWhitespace_Test.testNextNonWhitespaceWithEOFHandling(JsonReader_nextNonWhitespace_Test.java:129)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       com.google.gson.stream.JsonReader_nextNonWhitespace_Test.lambda$testNextNonWhitespaceWithEOFHandling$1(JsonReader_nextNonWhitespace_Test.java:130)
       [...]
     Caused by: java.io.EOFException: End of input at line 1 column 1 path $
       com.google.gson.stream.JsonReader.nextNonWhitespace(JsonReader.java:1457)
       [...]

Test run finished after 428 ms
[         4 containers found      ]
[         0 containers skipped    ]
[         4 containers started    ]
[         0 containers aborted    ]
[         4 containers successful ]
[         0 containers failed     ]
[        13 tests found           ]
[         0 tests skipped         ]
[        13 tests started         ]
[         0 tests aborted         ]
[         9 tests successful      ]
[         4 tests failed          ]


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
		int com.google.gson.stream.JsonReader.pos;
		int com.google.gson.stream.JsonReader.lineStart;
		int com.google.gson.stream.JsonReader.lineNumber;
		char[] com.google.gson.stream.JsonReader.buffer;
		int com.google.gson.stream.JsonReader.limit;
	related functions:
		method `private String nextUnquotedValue()` returns `java.lang.String`, related with `com.google.gson.stream.JsonReader.nextNonWhitespace(boolean)`, api document: 
   * Returns an unquoted value as a string.
   ;
		method `private boolean fillBuffer(int)` returns `boolean`, related with `com.google.gson.stream.JsonReader.nextNonWhitespace(boolean)`, api document: 
   * Returns true once {@code limit - pos >= minimum}. If the data is
   * exhausted before that many characters are available, this returns
   * false.
   ;
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
		method `private void consumeNonExecutePrefix()` returns `void`, related with `com.google.gson.stream.JsonReader.nextNonWhitespace(boolean)`, api document: 
   * Consumes the non-execute prefix if it exists.
   ;
		method `private char readEscapeCharacter()` returns `char`, related with `com.google.gson.stream.JsonReader.nextNonWhitespace(boolean)`, api document: 
   * Unescapes the character identified by the character or characters that
   * immediately follow a backslash. The backslash '\' should have already
   * been read. This supports both unicode escapes "u000A" and two-character
   * escapes "\n".
   *
   * @throws NumberFormatException if any unicode escape sequences are
   *     malformed.
   ;
		method `private void skipUnquotedValue()` returns `void`, related with `com.google.gson.stream.JsonReader.nextNonWhitespace(boolean)`;
		method `private boolean skipTo(String)` returns `boolean`, related with `com.google.gson.stream.JsonReader.nextNonWhitespace(boolean)`, api document: 
   * @param toFind a string to search for. Must not contain a newline.
   ;
		method `private void skipQuotedValue(char)` returns `void`, related with `com.google.gson.stream.JsonReader.nextNonWhitespace(boolean)`;



Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 
