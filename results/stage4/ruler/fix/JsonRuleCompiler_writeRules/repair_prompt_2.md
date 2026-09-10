


You are an experienced Java developer tasked with fixing a piece of code that contains execution errors. Your task is to resolve these issues after analyzing the code and its execution feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any logical errors.
2. Analyze the execution feedback to understand the specific errors and their locations within the code.
3. Apply necessary corrections to the code based on the feedback, and output the compelete correct code.
The Java code you need to fix is provided below:
```java
package software.amazon.event.ruler;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.StringReader;
import java.util.*;
import com.fasterxml.jackson.core.JsonParseException;
import java.io.IOException;
import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class JsonRuleCompiler_writeRules_Test {

    private static JsonFactory jsonFactory;

    private JsonParser jsonParser;

    private List<Map<String, List<Patterns>>> rules;

    @BeforeAll
    static void setupBeforeAll() {
        jsonFactory = new JsonFactory();
    }

    @BeforeEach
    void setupBeforeEach() throws Exception {
        rules = new ArrayList<>();
        String jsonString = "{ \"source\": [ \"aws.cloudwatch\" ], \"$or\": [ { \"metricName\": [ \"CPUUtilization\", \"ReadLatency\" ] }, { \"metricType\": [ \"MetricType\" ], \"namespace\": [ \"AWS/EC2\", \"AWS/ES\" ] }, { \"scope\": [ \"Service\" ] } ] } }";
        jsonParser = jsonFactory.createParser(new StringReader(jsonString));
    }

    @AfterEach
    void teardownAfterEach() throws Exception {
        if (jsonParser != null) {
            jsonParser.close();
        }
    }

    @AfterAll
    static void teardownAfterAll() {
        jsonFactory = null;
    }

    private void invokeWriteRules(List<Map<String, List<Patterns>>> rules, String key, JsonParser parser, boolean withQuotes) throws Exception {
        Method method = JsonRuleCompiler.class.getDeclaredMethod("writeRules", List.class, String.class, JsonParser.class, boolean.class);
        method.setAccessible(true);
        method.invoke(null, rules, key, parser, withQuotes);
    }

    @Test
    void testWriteRulesWithValidInput() throws Exception {
        boolean withQuotes = true;
        // Invoke the method to be tested
        invokeWriteRules(rules, "source", jsonParser, withQuotes);
        // Assertions to check the expected output
        assertFalse(rules.isEmpty(), "Rules should not be empty after invocation.");
        assertEquals(3, rules.size(), "There should be 3 match branches.");
        // Additional assertions can be added here to validate the contents of the rules
    }

    @Test
    void testWriteRulesWithEmptyArray() {
        boolean withQuotes = true;
        // Attempt to invoke the method with an empty parser
        assertThrows(JsonParseException.class, () -> {
            invokeWriteRules(rules, "emptySource", jsonFactory.createParser("[]"), withQuotes);
        }, "Expected JsonParseException for empty array.");
    }

    @Test
    void testWriteRulesWithNullTokens() throws Exception {
        boolean withQuotes = false;
        // Mock JsonParser to return VALUE_NULL token
        JsonParser mockParser = mock(JsonParser.class);
        when(mockParser.nextToken()).thenReturn(JsonToken.VALUE_NULL, JsonToken.END_ARRAY);
        when(mockParser.getText()).thenReturn("null");
        // Invoke the method to be tested
        invokeWriteRules(rules, "testRule", mockParser, withQuotes);
        // Assertions to check if the rule was added correctly
        assertFalse(rules.isEmpty(), "Rules should not be empty after invocation.");
        assertEquals(1, rules.size(), "There should be 1 match branch.");
        assertTrue(rules.get(0).containsKey("testRule"), "Rules should contain 'testRule'.");
        assertNull(rules.get(0).get("testRule").get(0), "Expected pattern for 'null' not found.");
    }

    @Test
    void testWriteRulesWithBooleanTokens() throws Exception {
        boolean withQuotes = false;
        // Test for VALUE_TRUE
        JsonParser mockParserTrue = mock(JsonParser.class);
        when(mockParserTrue.nextToken()).thenReturn(JsonToken.VALUE_TRUE, JsonToken.END_ARRAY);
        when(mockParserTrue.getText()).thenReturn("true");
        invokeWriteRules(rules, "testRuleTrue", mockParserTrue, withQuotes);
        assertTrue(rules.get(0).containsKey("testRuleTrue"), "Rules should contain 'testRuleTrue'.");
        assertEquals("true", rules.get(0).get("testRuleTrue").get(0).toString(), "Expected pattern for 'true' not found.");
        // Reset rules for the next test
        rules.clear();
        // Test for VALUE_FALSE
        JsonParser mockParserFalse = mock(JsonParser.class);
        when(mockParserFalse.nextToken()).thenReturn(JsonToken.VALUE_FALSE, JsonToken.END_ARRAY);
        when(mockParserFalse.getText()).thenReturn("false");
        invokeWriteRules(rules, "testRuleFalse", mockParserFalse, withQuotes);
        assertTrue(rules.get(0).containsKey("testRuleFalse"), "Rules should contain 'testRuleFalse'.");
        assertEquals("false", rules.get(0).get("testRuleFalse").get(0).toString(), "Expected pattern for 'false' not found.");
    }

    @Test
    void testWriteRulesWithNumberTokens() throws Exception {
        boolean withQuotes = false;
        // Mock JsonParser to return VALUE_NUMBER_INT token
        JsonParser mockParser = mock(JsonParser.class);
        when(mockParser.nextToken()).thenReturn(JsonToken.VALUE_NUMBER_INT, JsonToken.END_ARRAY);
        when(mockParser.getDoubleValue()).thenReturn(42.0);
        when(mockParser.getText()).thenReturn("42");
        invokeWriteRules(rules, "testRuleNumber", mockParser, withQuotes);
        assertTrue(rules.get(0).containsKey("testRuleNumber"), "Rules should contain 'testRuleNumber'.");
        assertEquals(1, rules.get(0).get("testRuleNumber").size(), "Expected 1 pattern for number.");
    }

    @Test
    void testWriteRulesWithInvalidTokens() throws Exception {
        boolean withQuotes = false;
        // Mock JsonParser to return an unexpected token
        JsonParser mockParser = mock(JsonParser.class);
        when(mockParser.nextToken()).thenReturn(JsonToken.VALUE_STRING, JsonToken.END_ARRAY);
        when(mockParser.getText()).thenReturn("unexpected");
        // Attempt to invoke the method with an unexpected token
        assertThrows(JsonParseException.class, () -> {
            invokeWriteRules(rules, "testRuleInvalid", mockParser, withQuotes);
        }, "Expected JsonParseException for unexpected token.");
    }
}

```

Corresponding execution feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended

╷
├─ JUnit Jupiter ✔
│  └─ JsonRuleCompiler_writeRules_Test ✔
│     ├─ testWriteRulesWithValidInput() ✘ Unrecognized match type source
│     │         at [Source: (StringReader); line: 1, column: 14]
│     ├─ testWriteRulesWithInvalidTokens() ✘ Expected JsonParseException for unexpected token. ==> Expected com.fasterxml.jackson.core.JsonParseException to be thrown, but nothing was thrown.
│     ├─ testWriteRulesWithNumberTokens() ✘ Expected 1 pattern for number. ==> expected: <1> but was: <2>
│     ├─ testWriteRulesWithNullTokens() ✘ Expected pattern for 'null' not found. ==> expected: <null> but was: <VP:null (T:EXACT)>
│     ├─ testWriteRulesWithEmptyArray() ✘ Expected JsonParseException for empty array. ==> Unexpected exception type thrown, expected: <com.fasterxml.jackson.core.JsonParseException> but was: <java.lang.reflect.InvocationTargetException>
│     └─ testWriteRulesWithBooleanTokens() ✘ Expected pattern for 'true' not found. ==> expected: <true> but was: <VP:true (T:EXACT)>
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Failures (6):
  JUnit Jupiter:JsonRuleCompiler_writeRules_Test:testWriteRulesWithValidInput()
    MethodSource [className = 'software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test', methodName = 'testWriteRulesWithValidInput', methodParameterTypes = '']
    => com.fasterxml.jackson.core.JsonParseException: Unrecognized match type source
 at [Source: (StringReader); line: 1, column: 14]
       software.amazon.event.ruler.JsonRuleCompiler.barf(JsonRuleCompiler.java:712)
       software.amazon.event.ruler.JsonRuleCompiler.processMatchExpression(JsonRuleCompiler.java:503)
       software.amazon.event.ruler.JsonRuleCompiler.writeRules(JsonRuleCompiler.java:289)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test.invokeWriteRules(JsonRuleCompiler_writeRules_Test.java:54)
       software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test.testWriteRulesWithValidInput(JsonRuleCompiler_writeRules_Test.java:61)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       [...]
  JUnit Jupiter:JsonRuleCompiler_writeRules_Test:testWriteRulesWithInvalidTokens()
    MethodSource [className = 'software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test', methodName = 'testWriteRulesWithInvalidTokens', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Expected JsonParseException for unexpected token. ==> Expected com.fasterxml.jackson.core.JsonParseException to be thrown, but nothing was thrown.
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:73)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:39)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3153)
       software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test.testWriteRulesWithInvalidTokens(JsonRuleCompiler_writeRules_Test.java:135)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:767)
       [...]
  JUnit Jupiter:JsonRuleCompiler_writeRules_Test:testWriteRulesWithNumberTokens()
    MethodSource [className = 'software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test', methodName = 'testWriteRulesWithNumberTokens', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Expected 1 pattern for number. ==> expected: <1> but was: <2>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:150)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:563)
       software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test.testWriteRulesWithNumberTokens(JsonRuleCompiler_writeRules_Test.java:124)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       [...]
  JUnit Jupiter:JsonRuleCompiler_writeRules_Test:testWriteRulesWithNullTokens()
    MethodSource [className = 'software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test', methodName = 'testWriteRulesWithNullTokens', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Expected pattern for 'null' not found. ==> expected: <null> but was: <VP:null (T:EXACT)>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertNull.failNotNull(AssertNull.java:50)
       org.junit.jupiter.api.AssertNull.assertNull(AssertNull.java:35)
       org.junit.jupiter.api.Assertions.assertNull(Assertions.java:287)
       software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test.testWriteRulesWithNullTokens(JsonRuleCompiler_writeRules_Test.java:90)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       [...]
  JUnit Jupiter:JsonRuleCompiler_writeRules_Test:testWriteRulesWithEmptyArray()
    MethodSource [className = 'software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test', methodName = 'testWriteRulesWithEmptyArray', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Expected JsonParseException for empty array. ==> Unexpected exception type thrown, expected: <com.fasterxml.jackson.core.JsonParseException> but was: <java.lang.reflect.InvocationTargetException>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:67)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:39)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3153)
       software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test.testWriteRulesWithEmptyArray(JsonRuleCompiler_writeRules_Test.java:72)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test.invokeWriteRules(JsonRuleCompiler_writeRules_Test.java:54)
       [...]
     Caused by: com.fasterxml.jackson.core.JsonParseException: Match value must be String, number, true, false, or null
 at [Source: (String)"[]"; line: 1, column: 2]
       software.amazon.event.ruler.JsonRuleCompiler.barf(JsonRuleCompiler.java:712)
       software.amazon.event.ruler.JsonRuleCompiler.writeRules(JsonRuleCompiler.java:328)
       [...]
  JUnit Jupiter:JsonRuleCompiler_writeRules_Test:testWriteRulesWithBooleanTokens()
    MethodSource [className = 'software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test', methodName = 'testWriteRulesWithBooleanTokens', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Expected pattern for 'true' not found. ==> expected: <true> but was: <VP:true (T:EXACT)>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1156)
       software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test.testWriteRulesWithBooleanTokens(JsonRuleCompiler_writeRules_Test.java:102)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       [...]

Test run finished after 535 ms
[         4 containers found      ]
[         0 containers skipped    ]
[         4 containers started    ]
[         0 containers aborted    ]
[         4 containers successful ]
[         0 containers failed     ]
[         6 tests found           ]
[         0 tests skipped         ]
[         6 tests started         ]
[         0 tests aborted         ]
[         0 tests successful      ]
[         6 tests failed          ]


```


And you can use the following related information to assist you in your analysis and correction:

api document of class software.amazon.event.ruler.JsonRuleCompiler: 

 * Represents a updated compiler comparing to RuleCompiler class, it parses a rule described by a JSON string into
 * a list of Map which is composed of field Patterns, each Map represents one dedicated match branch in the rule.
 * By default, fields in the rule will be interpreted as "And" relationship implicitly and will result into one Map with
 * fields of Patterns, but once it comes across the "$or" relationship which is explicitly decorated by "$or" primitive
 * array, for each object in "$or" array, this Compiler will fork a new Map to include all fields of patterns in
 * that "or" branch, and when it goes over all branches in "$or" relationship, it will end up with the list of Map
 * and each Map represents a match branch composed of fields of patterns.
 * i.e.: a rule which describe the effect of ("source" AND ("metricName" OR ("metricType AND "namespace") OR "scope"))
 * its JSON format String is:
 *   {
 *     "source": [ "aws.cloudwatch" ],
 *     "$or": [
 *       { "metricName": [ "CPUUtilization", "ReadLatency" ] },
 *       {
 *         "metricType": [ "MetricType" ] ,
 *         "namespace": [ "AWS/EC2", "AWS/ES" ]
 *       },
 *       { "scope": [ "Service" ] }
 *     ]
 *   }
 * It will be parsed to a list of match branches composed by a Map of fields patterns, e.g. for above rule, there will
 * result 3 match branches:
 * [
 *   {source=[\"aws.cloudwatch\"], metricName=[\"CPUUtilization\",\"ReadLatency\"]},
 *   {namespace=[\"AWS/EC2\", \"AWS/ES\"], metricType=[\"MetricType\"], source=[\"aws.cloudwatch\"]},
 *   {source=[\"aws.cloudwatch\"], scope=[\"Service\"]}
 * ]
 

dependent classes: 
class `software.amazon.event.ruler.RuleCompiler`:
	related functions:
		method `private static Patterns processAnythingButListMatchExpression(JsonParser)` returns `software.amazon.event.ruler.Patterns`, related with `software.amazon.event.ruler.JsonRuleCompiler.writeRules(List<Map<String, List<Patterns>>>, String, JsonParser, boolean)`;
		method `private static void writeRules(Map<String, List<Patterns>>, String, JsonParser, boolean)` returns `void`, related with `software.amazon.event.ruler.JsonRuleCompiler.writeRules(List<Map<String, List<Patterns>>>, String, JsonParser, boolean)`;
		method `private static Patterns processAnythingButMatchExpression(JsonParser, JsonToken)` returns `software.amazon.event.ruler.Patterns`, related with `software.amazon.event.ruler.JsonRuleCompiler.writeRules(List<Map<String, List<Patterns>>>, String, JsonParser, boolean)`;
class `software.amazon.event.ruler.RuleCompiler.ListBasedRuleCompiler`:
	related functions:
		method `private static void writeRules(Map<List<String>, List<Patterns>>, List<String>, JsonParser, boolean)` returns `void`, related with `software.amazon.event.ruler.JsonRuleCompiler.writeRules(List<Map<String, List<Patterns>>>, String, JsonParser, boolean)`;
class `software.amazon.event.ruler.JsonRuleCompiler`:
	related functions:
		method `private static void parseIntoOrRelationship(List<Map<String, List<Patterns>>>, Path, JsonParser, boolean)` returns `void`, related with `software.amazon.event.ruler.JsonRuleCompiler.writeRules(List<Map<String, List<Patterns>>>, String, JsonParser, boolean)`, api document: 
     * This function is to parse the "$or" relationship described as an array, each object in the array will be
     * interpreted as "$or" relationship, for example:
     * {
     *     "detail": {
     *         "$or" : [
     *             {"c-count": [ { "numeric": [ ">", 0, "<=", 5 ] } ]},
     *             {"d-count": [ { "numeric": [ "<", 10 ] } ]},
     *             {"x-limit": [ { "numeric": [ "=", 3.018e2 ] } ]}
     *         ]
     *     }
     * }
     * above rule will be interpreted as or effect: ("detail.c-count" || "detail.d-count" || detail.x-limit"),
     * The result will be described as the list of Map, each Map represents a sub rule composed of fields of patterns in
     * that or condition path, for example:
     * [
     *   {detail.c-count=[38D7EA4C68000/38D7EA512CB40:true/false]},
     *   {detail.d-count=[0000000000000/38D7EA55F1680:false/true]},
     *   {detail.x-limit=[VP:38D7EB6C39A40]}
     * ]
     * This List will be added into Ruler with the same rule name to demonstrate the "or" effects inside Ruler state
     * machine.
     ;
		method `private static Patterns completeNumericRange(JsonParser, JsonToken, double, boolean)` returns `software.amazon.event.ruler.Patterns`, related with `software.amazon.event.ruler.JsonRuleCompiler.writeRules(List<Map<String, List<Patterns>>>, String, JsonParser, boolean)`;
		method `private static Patterns processAnythingButListMatchExpression(JsonParser)` returns `software.amazon.event.ruler.Patterns`, related with `software.amazon.event.ruler.JsonRuleCompiler.writeRules(List<Map<String, List<Patterns>>>, String, JsonParser, boolean)`;
		method `private static Patterns processAnythingButEqualsIgnoreCaseListMatchExpression(JsonParser)` returns `software.amazon.event.ruler.Patterns`, related with `software.amazon.event.ruler.JsonRuleCompiler.writeRules(List<Map<String, List<Patterns>>>, String, JsonParser, boolean)`;
		method `private static Patterns processAnythingButMatchExpression(JsonParser, JsonToken)` returns `software.amazon.event.ruler.Patterns`, related with `software.amazon.event.ruler.JsonRuleCompiler.writeRules(List<Map<String, List<Patterns>>>, String, JsonParser, boolean)`;



Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 
