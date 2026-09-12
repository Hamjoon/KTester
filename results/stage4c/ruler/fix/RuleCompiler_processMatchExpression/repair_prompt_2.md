


You are an experienced Java developer tasked with fixing a piece of code that contains execution errors. Your task is to resolve these issues after analyzing the code and its execution feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any logical errors.
2. Analyze the execution feedback to understand the specific errors and their locations within the code.
3. Apply necessary corrections to the code based on the feedback, and output the compelete correct code.
The Java code you need to fix is provided below:
```java
package software.amazon.event.ruler;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.fasterxml.jackson.core.JsonParseException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class RuleCompiler_processMatchExpression_Test {

    private static JsonFactory jsonFactory;

    private JsonParser parser;

    @BeforeAll
    static void setupBeforeAll() {
        jsonFactory = new JsonFactory();
    }

    @BeforeEach
    void setupBeforeEach() throws IOException {
        parser = mock(JsonParser.class);
    }

    private Patterns invokeProcessMatchExpression(JsonParser parser) throws Exception {
        Method method = RuleCompiler.class.getDeclaredMethod("processMatchExpression", JsonParser.class);
        method.setAccessible(true);
        return (Patterns) method.invoke(null, parser);
    }

    @Test
    void testProcessMatchExpressionExactMatch() throws Exception {
        // Given
        when(parser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.VALUE_STRING, JsonToken.END_OBJECT);
        when(parser.getCurrentName()).thenReturn(Constants.EXACT_MATCH);
        when(parser.getText()).thenReturn("test");
        // When
        Patterns result = invokeProcessMatchExpression(parser);
        // Then
        assertNotNull(result);
        // Add more assertions based on expected behavior
    }

    @Test
    void testProcessMatchExpressionPrefixMatch() throws Exception {
        // Given
        when(parser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.VALUE_STRING, JsonToken.END_OBJECT);
        when(parser.getCurrentName()).thenReturn(Constants.PREFIX_MATCH);
        when(parser.getText()).thenReturn("test");
        // When
        Patterns result = invokeProcessMatchExpression(parser);
        // Then
        assertNotNull(result);
        // Add more assertions based on expected behavior
    }

    @Test
    void testProcessMatchExpressionSuffixMatch() throws Exception {
        // Given
        when(parser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.VALUE_STRING, JsonToken.END_OBJECT);
        when(parser.getCurrentName()).thenReturn(Constants.SUFFIX_MATCH);
        when(parser.getText()).thenReturn("test");
        // When
        Patterns result = invokeProcessMatchExpression(parser);
        // Then
        assertNotNull(result);
        // Add more assertions based on expected behavior
    }

    @Test
    void testProcessMatchExpressionNumericMatch() throws Exception {
        // Given
        when(parser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.START_ARRAY, JsonToken.VALUE_NUMBER_INT, JsonToken.END_ARRAY, JsonToken.END_OBJECT);
        when(parser.getCurrentName()).thenReturn(Constants.NUMERIC);
        // Assuming a valid numeric value
        when(parser.getIntValue()).thenReturn(42);
        // When
        Patterns result = invokeProcessMatchExpression(parser);
        // Then
        assertNotNull(result);
        // Add more assertions based on expected behavior
    }

    @Test
    void testProcessMatchExpressionAnythingButMatch() throws Exception {
        // Given
        when(parser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.VALUE_STRING, JsonToken.END_OBJECT);
        when(parser.getCurrentName()).thenReturn(Constants.ANYTHING_BUT_MATCH);
        when(parser.getText()).thenReturn("test");
        // When
        Patterns result = invokeProcessMatchExpression(parser);
        // Then
        assertNotNull(result);
        // Add more assertions based on expected behavior
    }

    @Test
    void testProcessMatchExpressionExistsMatch() throws Exception {
        // Given
        when(parser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.VALUE_TRUE, JsonToken.END_OBJECT);
        when(parser.getCurrentName()).thenReturn(Constants.EXISTS_MATCH);
        // When
        Patterns result = invokeProcessMatchExpression(parser);
        // Then
        assertNotNull(result);
        // Add more assertions based on expected behavior
    }

    @Test
    void testProcessMatchExpressionCidrMatch() throws Exception {
        // Given
        when(parser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.VALUE_STRING, JsonToken.END_OBJECT);
        when(parser.getCurrentName()).thenReturn(Constants.CIDR);
        when(parser.getText()).thenReturn("192.168.1.0/24");
        // When
        Patterns result = invokeProcessMatchExpression(parser);
        // Then
        assertNotNull(result);
        // Add more assertions based on expected behavior
    }

    @Test
    void testProcessMatchExpressionEqualsIgnoreCaseMatch() throws Exception {
        // Given
        when(parser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.VALUE_STRING, JsonToken.END_OBJECT);
        when(parser.getCurrentName()).thenReturn(Constants.EQUALS_IGNORE_CASE);
        when(parser.getText()).thenReturn("test");
        // When
        Patterns result = invokeProcessMatchExpression(parser);
        // Then
        assertNotNull(result);
        // Add more assertions based on expected behavior
    }

    @Test
    void testProcessMatchExpressionWildcardMatch() throws Exception {
        // Given
        when(parser.nextToken()).thenReturn(JsonToken.FIELD_NAME, JsonToken.VALUE_STRING, JsonToken.END_OBJECT);
        when(parser.getCurrentName()).thenReturn(Constants.WILDCARD);
        when(parser.getText()).thenReturn("test*");
        // When
        Patterns result = invokeProcessMatchExpression(parser);
        // Then
        assertNotNull(result);
        // Add more assertions based on expected behavior
    }

    @Test
    void testProcessMatchExpressionUnrecognizedMatchType() throws Exception {
        // Given
        when(parser.nextToken()).thenReturn(JsonToken.FIELD_NAME);
        when(parser.getCurrentName()).thenReturn("UNRECOGNIZED");
        // When & Then
        JsonParseException exception = assertThrows(JsonParseException.class, () -> {
            invokeProcessMatchExpression(parser);
        });
        assertEquals("Unrecognized match type UNRECOGNIZED", exception.getMessage());
    }

    @Test
    void testProcessMatchExpressionMissingFieldNameToken() throws Exception {
        // Given
        when(parser.nextToken()).thenReturn(JsonToken.VALUE_STRING);
        // When & Then
        JsonParseException exception = assertThrows(JsonParseException.class, () -> {
            invokeProcessMatchExpression(parser);
        });
        assertEquals("Match expression name not found", exception.getMessage());
    }
}

```

Corresponding execution feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended

╷
├─ JUnit Jupiter ✔
│  └─ RuleCompiler_processMatchExpression_Test ✔
│     ├─ testProcessMatchExpressionExistsMatch() ✔
│     ├─ testProcessMatchExpressionSuffixMatch() ✔
│     ├─ testProcessMatchExpressionEqualsIgnoreCaseMatch() ✔
│     ├─ testProcessMatchExpressionMissingFieldNameToken() ✘ Unexpected exception type thrown, expected: <com.fasterxml.jackson.core.JsonParseException> but was: <java.lang.reflect.InvocationTargetException>
│     ├─ testProcessMatchExpressionAnythingButMatch() ✔
│     ├─ testProcessMatchExpressionUnrecognizedMatchType() ✘ Unexpected exception type thrown, expected: <com.fasterxml.jackson.core.JsonParseException> but was: <java.lang.reflect.InvocationTargetException>
│     ├─ testProcessMatchExpressionExactMatch() ✔
│     ├─ testProcessMatchExpressionCidrMatch() ✔
│     ├─ testProcessMatchExpressionNumericMatch() ✘ Invalid member in numeric match: null
│     ├─ testProcessMatchExpressionPrefixMatch() ✔
│     └─ testProcessMatchExpressionWildcardMatch() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Failures (3):
  JUnit Jupiter:RuleCompiler_processMatchExpression_Test:testProcessMatchExpressionMissingFieldNameToken()
    MethodSource [className = 'software.amazon.event.ruler.RuleCompiler_processMatchExpression_Test', methodName = 'testProcessMatchExpressionMissingFieldNameToken', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Unexpected exception type thrown, expected: <com.fasterxml.jackson.core.JsonParseException> but was: <java.lang.reflect.InvocationTargetException>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:67)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3128)
       software.amazon.event.ruler.RuleCompiler_processMatchExpression_Test.testProcessMatchExpressionMissingFieldNameToken(RuleCompiler_processMatchExpression_Test.java:174)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.RuleCompiler_processMatchExpression_Test.invokeProcessMatchExpression(RuleCompiler_processMatchExpression_Test.java:37)
       [...]
     Caused by: com.fasterxml.jackson.core.JsonParseException: Match expression name not found
       software.amazon.event.ruler.RuleCompiler.barf(RuleCompiler.java:609)
       software.amazon.event.ruler.RuleCompiler.processMatchExpression(RuleCompiler.java:248)
       [...]
  JUnit Jupiter:RuleCompiler_processMatchExpression_Test:testProcessMatchExpressionUnrecognizedMatchType()
    MethodSource [className = 'software.amazon.event.ruler.RuleCompiler_processMatchExpression_Test', methodName = 'testProcessMatchExpressionUnrecognizedMatchType', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Unexpected exception type thrown, expected: <com.fasterxml.jackson.core.JsonParseException> but was: <java.lang.reflect.InvocationTargetException>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:67)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3128)
       software.amazon.event.ruler.RuleCompiler_processMatchExpression_Test.testProcessMatchExpressionUnrecognizedMatchType(RuleCompiler_processMatchExpression_Test.java:163)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.RuleCompiler_processMatchExpression_Test.invokeProcessMatchExpression(RuleCompiler_processMatchExpression_Test.java:37)
       [...]
     Caused by: com.fasterxml.jackson.core.JsonParseException: Unrecognized match type UNRECOGNIZED
       software.amazon.event.ruler.RuleCompiler.barf(RuleCompiler.java:609)
       software.amazon.event.ruler.RuleCompiler.processMatchExpression(RuleCompiler.java:402)
       [...]
  JUnit Jupiter:RuleCompiler_processMatchExpression_Test:testProcessMatchExpressionNumericMatch()
    MethodSource [className = 'software.amazon.event.ruler.RuleCompiler_processMatchExpression_Test', methodName = 'testProcessMatchExpressionNumericMatch', methodParameterTypes = '']
    => com.fasterxml.jackson.core.JsonParseException: Invalid member in numeric match: null
       software.amazon.event.ruler.RuleCompiler.barf(RuleCompiler.java:609)
       software.amazon.event.ruler.RuleCompiler.processNumericMatchExpression(RuleCompiler.java:496)
       software.amazon.event.ruler.RuleCompiler.processMatchExpression(RuleCompiler.java:286)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.RuleCompiler_processMatchExpression_Test.invokeProcessMatchExpression(RuleCompiler_processMatchExpression_Test.java:37)
       software.amazon.event.ruler.RuleCompiler_processMatchExpression_Test.testProcessMatchExpressionNumericMatch(RuleCompiler_processMatchExpression_Test.java:87)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       [...]

Test run finished after 549 ms
[         4 containers found      ]
[         0 containers skipped    ]
[         4 containers started    ]
[         0 containers aborted    ]
[         4 containers successful ]
[         0 containers failed     ]
[        11 tests found           ]
[         0 tests skipped         ]
[        11 tests started         ]
[         0 tests aborted         ]
[         8 tests successful      ]
[         3 tests failed          ]


```


And you can use the following related information to assist you in your analysis and correction:

api document of class software.amazon.event.ruler.RuleCompiler: 

 * Compiles Rules, expressed in JSON, for use in Ruler.
 * There are two flavors of compilation:
 * 1. Compile a JSON-based Rule into Map of String to List of Patterns which can be used in rulesForEvent,
 *    and has a "check" variant that just checks rules for syntactic accuracy
 * 2. Starting in ListBasedRuleCompiler, does the same thing but expresses field names as List ofString
 *    rather than "."-separated strings for use in the Ruler class, which does not use state machines and
 *    needs to step into the event field by field.
 *
 * Is public so clients can call the check() method to syntax-check filters
 

return type: 
software.amazon.event.ruler.Patterns

dependent classes: 
class `software.amazon.event.ruler.Constants`:
	dependent fields:
		java.lang.String software.amazon.event.ruler.Constants.NUMERIC;
		java.lang.String software.amazon.event.ruler.Constants.CIDR;
		java.lang.String software.amazon.event.ruler.Constants.ANYTHING_BUT_MATCH;
		java.lang.String software.amazon.event.ruler.Constants.EQUALS_IGNORE_CASE;
		java.lang.String software.amazon.event.ruler.Constants.SUFFIX_MATCH;
		java.lang.String software.amazon.event.ruler.Constants.EXISTS_MATCH;
		java.lang.String software.amazon.event.ruler.Constants.PREFIX_MATCH;
		java.lang.String software.amazon.event.ruler.Constants.EXACT_MATCH;
		java.lang.String software.amazon.event.ruler.Constants.WILDCARD;
	
class `software.amazon.event.ruler.JsonRuleCompiler`:
	related functions:
		method `private static void parseObject(List<Map<String, List<Patterns>>>, Path, JsonParser, boolean)` returns `void`, related with `software.amazon.event.ruler.RuleCompiler.processMatchExpression(JsonParser)`;
		method `private static Patterns processMatchExpression(JsonParser)` returns `software.amazon.event.ruler.Patterns`, related with `software.amazon.event.ruler.RuleCompiler.processMatchExpression(JsonParser)`;
		method `private static void writeRules(List<Map<String, List<Patterns>>>, String, JsonParser, boolean)` returns `void`, related with `software.amazon.event.ruler.RuleCompiler.processMatchExpression(JsonParser)`;
class `software.amazon.event.ruler.RuleCompiler.ListBasedRuleCompiler`:
	related functions:
		method `private static void writeRules(Map<List<String>, List<Patterns>>, List<String>, JsonParser, boolean)` returns `void`, related with `software.amazon.event.ruler.RuleCompiler.processMatchExpression(JsonParser)`;
class `software.amazon.event.ruler.RuleCompiler`:
	related functions:
		method `private static Patterns processAnythingButListMatchExpression(JsonParser)` returns `software.amazon.event.ruler.Patterns`, related with `software.amazon.event.ruler.RuleCompiler.processMatchExpression(JsonParser)`;
		method `private static void writeRules(Map<String, List<Patterns>>, String, JsonParser, boolean)` returns `void`, related with `software.amazon.event.ruler.RuleCompiler.processMatchExpression(JsonParser)`;
		method `private static void parseObject(Map<String, List<Patterns>>, Path, JsonParser, boolean)` returns `void`, related with `software.amazon.event.ruler.RuleCompiler.processMatchExpression(JsonParser)`;
class `software.amazon.event.ruler.Event`:
	related functions:
		method `private void traverseObject(JsonParser, TreeMap<String, List<Value>>, Progress)` returns `void`, related with `software.amazon.event.ruler.RuleCompiler.processMatchExpression(JsonParser)`;



Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 
