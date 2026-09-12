


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
import java.io.IOException;
import java.io.StringReader;
import java.util.*;
import com.fasterxml.jackson.core.JsonParseException;
import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class JsonRuleCompiler_writeRules_Test {

    private JsonFactory jsonFactory;

    @BeforeEach
    void setupBeforeEach() {
        jsonFactory = new JsonFactory();
    }

    private void invokeWriteRules(List<Map<String, List<Patterns>>> rules, String name, JsonParser parser, boolean withQuotes) throws Exception {
        Method method = JsonRuleCompiler.class.getDeclaredMethod("writeRules", List.class, String.class, JsonParser.class, boolean.class);
        method.setAccessible(true);
        method.invoke(null, rules, name, parser, withQuotes);
    }

    private Patterns invokeProcessMatchExpression(JsonParser parser) throws Exception {
        Method method = JsonRuleCompiler.class.getDeclaredMethod("processMatchExpression", JsonParser.class);
        method.setAccessible(true);
        return (Patterns) method.invoke(null, parser);
    }

    @Test
    void testWriteRules_withValidJsonArray_shouldPopulateRules() throws IOException {
        String jsonArray = "[{\"key1\":\"value1\"}, {\"key2\":\"value2\"}]";
        JsonParser parser = jsonFactory.createParser(new StringReader(jsonArray));
        List<Map<String, List<Patterns>>> rules = new ArrayList<>();
        String name = "testName";
        boolean withQuotes = false;
        try {
            invokeWriteRules(rules, name, parser, withQuotes);
        } catch (Exception e) {
            fail("Failed to invoke writeRules", e);
        }
        assertFalse(rules.isEmpty(), "Rules should not be empty");
        assertEquals(1, rules.size(), "Rules size should be 1");
        assertTrue(rules.get(0).containsKey(name), "Rules should contain the given name");
    }

    @Test
    void testWriteRules_withStartObjectToken_shouldInvokeProcessMatchExpression() throws IOException {
        String jsonArray = "[{}]";
        JsonParser parser = jsonFactory.createParser(new StringReader(jsonArray));
        List<Map<String, List<Patterns>>> rules = new ArrayList<>();
        String name = "testName";
        boolean withQuotes = false;
        Patterns mockPattern = mock(Patterns.class);
        try (MockedStatic<JsonRuleCompiler> mockedCompiler = mockStatic(JsonRuleCompiler.class)) {
            mockedCompiler.when(() -> invokeProcessMatchExpression(parser)).thenReturn(mockPattern);
            try {
                invokeWriteRules(rules, name, parser, withQuotes);
            } catch (Exception e) {
                fail("Failed to invoke writeRules", e);
            }
            assertEquals(1, rules.size(), "Rules size should be 1");
            assertTrue(rules.get(0).get(name).contains(mockPattern), "Rules should contain the processed pattern");
        }
    }

    @Test
    void testWriteRules_withValueStringTokenAndIp_shouldConvertToRange() throws IOException {
        String jsonArray = "[\"192.168.0.1\"]";
        JsonParser parser = jsonFactory.createParser(new StringReader(jsonArray));
        List<Map<String, List<Patterns>>> rules = new ArrayList<>();
        String name = "testName";
        boolean withQuotes = false;
        Range mockRange = mock(Range.class);
        try (MockedStatic<CIDR> mockedCIDR = mockStatic(CIDR.class)) {
            mockedCIDR.when(() -> CIDR.ipToRangeIfPossible("192.168.0.1")).thenReturn(mockRange);
            try {
                invokeWriteRules(rules, name, parser, withQuotes);
            } catch (Exception e) {
                fail("Failed to invoke writeRules", e);
            }
            assertEquals(1, rules.size(), "Rules size should be 1");
            assertTrue(rules.get(0).get(name).contains(mockRange), "Rules should contain the IP range pattern");
        }
    }

    @Test
    void testWriteRules_withValueStringTokenAndWithQuotes_shouldAddQuotedExactMatch() throws IOException {
        String jsonArray = "[\"testString\"]";
        JsonParser parser = jsonFactory.createParser(new StringReader(jsonArray));
        List<Map<String, List<Patterns>>> rules = new ArrayList<>();
        String name = "testName";
        boolean withQuotes = true;
        ValuePatterns mockPattern = mock(ValuePatterns.class);
        try (MockedStatic<Patterns> mockedPatterns = mockStatic(Patterns.class)) {
            mockedPatterns.when(() -> Patterns.exactMatch("\"testString\"")).thenReturn(mockPattern);
            try {
                invokeWriteRules(rules, name, parser, withQuotes);
            } catch (Exception e) {
                fail("Failed to invoke writeRules", e);
            }
            assertEquals(1, rules.size(), "Rules size should be 1");
            assertTrue(rules.get(0).get(name).contains(mockPattern), "Rules should contain the quoted exact match pattern");
        }
    }

    @Test
    void testWriteRules_withValueNumberIntToken_shouldAddNumericEqualsAndExactMatch() throws IOException {
        String jsonArray = "[123]";
        JsonParser parser = jsonFactory.createParser(new StringReader(jsonArray));
        List<Map<String, List<Patterns>>> rules = new ArrayList<>();
        String name = "testName";
        boolean withQuotes = false;
        ValuePatterns mockNumericPattern = mock(ValuePatterns.class);
        ValuePatterns mockExactPattern = mock(ValuePatterns.class);
        try (MockedStatic<Patterns> mockedPatterns = mockStatic(Patterns.class)) {
            mockedPatterns.when(() -> Patterns.numericEquals(123.0)).thenReturn(mockNumericPattern);
            mockedPatterns.when(() -> Patterns.exactMatch("123")).thenReturn(mockExactPattern);
            try {
                invokeWriteRules(rules, name, parser, withQuotes);
            } catch (Exception e) {
                fail("Failed to invoke writeRules", e);
            }
            assertEquals(1, rules.size(), "Rules size should be 1");
            assertTrue(rules.get(0).get(name).contains(mockNumericPattern), "Rules should contain the numericEquals pattern");
            assertTrue(rules.get(0).get(name).contains(mockExactPattern), "Rules should contain the exactMatch pattern");
        }
    }

    @Test
    void testWriteRules_withValueNullToken_shouldAddExactMatchForNull() throws IOException {
        String jsonArray = "[null]";
        JsonParser parser = jsonFactory.createParser(new StringReader(jsonArray));
        List<Map<String, List<Patterns>>> rules = new ArrayList<>();
        String name = "testName";
        boolean withQuotes = false;
        ValuePatterns mockPattern = mock(ValuePatterns.class);
        try (MockedStatic<Patterns> mockedPatterns = mockStatic(Patterns.class)) {
            mockedPatterns.when(() -> Patterns.exactMatch("null")).thenReturn(mockPattern);
            try {
                invokeWriteRules(rules, name, parser, withQuotes);
            } catch (Exception e) {
                fail("Failed to invoke writeRules", e);
            }
            assertEquals(1, rules.size(), "Rules size should be 1");
            assertTrue(rules.get(0).get(name).contains(mockPattern), "Rules should contain the exactMatch pattern for null");
        }
    }

    @Test
    void testWriteRules_withEndArrayToken_shouldThrowJsonParseException() {
        String jsonArray = "[]";
        JsonParser parser;
        try {
            parser = jsonFactory.createParser(new StringReader(jsonArray));
            List<Map<String, List<Patterns>>> rules = new ArrayList<>();
            String name = "testName";
            boolean withQuotes = false;
            JsonParseException exception = assertThrows(JsonParseException.class, () -> {
                invokeWriteRules(rules, name, parser, withQuotes);
            }, "Expected JsonParseException due to empty array");
            assertEquals("Empty arrays are not allowed", exception.getMessage());
        } catch (IOException e) {
            fail("Failed to create JsonParser", e);
        }
    }

    @Test
    void testWriteRules_withExistingRules_shouldAddNewKeyAndExactMatchForTrue() throws IOException {
        String jsonArray = "[true]";
        JsonParser parser = jsonFactory.createParser(new StringReader(jsonArray));
        List<Map<String, List<Patterns>>> rules = new ArrayList<>();
        Map<String, List<Patterns>> existingRule = new HashMap<>();
        existingRule.put("existingKey", Collections.singletonList(Patterns.exactMatch("existingValue")));
        rules.add(existingRule);
        String name = "newKey";
        boolean withQuotes = false;
        ValuePatterns mockPattern = mock(ValuePatterns.class);
        try (MockedStatic<Patterns> mockedPatterns = mockStatic(Patterns.class)) {
            mockedPatterns.when(() -> Patterns.exactMatch("true")).thenReturn(mockPattern);
            try {
                invokeWriteRules(rules, name, parser, withQuotes);
            } catch (Exception e) {
                fail("Failed to invoke writeRules", e);
            }
            assertEquals(1, rules.size(), "Rules size should be 1");
            assertTrue(rules.get(0).get(name).contains(mockPattern), "Rules should contain the exactMatch pattern for true");
        }
    }

    @Test
    void testWriteRules_withValueNumberFloatToken_shouldAddNumericEqualsAndExactMatchForFloat() throws IOException {
        String jsonArray = "[123.45]";
        JsonParser parser = jsonFactory.createParser(new StringReader(jsonArray));
        List<Map<String, List<Patterns>>> rules = new ArrayList<>();
        String name = "testName";
        boolean withQuotes = false;
        ValuePatterns mockNumericPattern = mock(ValuePatterns.class);
        ValuePatterns mockExactPattern = mock(ValuePatterns.class);
        try (MockedStatic<Patterns> mockedPatterns = mockStatic(Patterns.class)) {
            mockedPatterns.when(() -> Patterns.numericEquals(123.45)).thenReturn(mockNumericPattern);
            mockedPatterns.when(() -> Patterns.exactMatch("123.45")).thenReturn(mockExactPattern);
            try {
                invokeWriteRules(rules, name, parser, withQuotes);
            } catch (Exception e) {
                fail("Failed to invoke writeRules", e);
            }
            assertEquals(1, rules.size(), "Rules size should be 1");
            assertTrue(rules.get(0).get(name).contains(mockNumericPattern), "Rules should contain the numericEquals pattern for float");
            assertTrue(rules.get(0).get(name).contains(mockExactPattern), "Rules should contain the exactMatch pattern for float");
        }
    }

    @Test
    void testWriteRules_withValueFalseToken_shouldAddExactMatchForFalse() throws IOException {
        String jsonArray = "[false]";
        JsonParser parser = jsonFactory.createParser(new StringReader(jsonArray));
        List<Map<String, List<Patterns>>> rules = new ArrayList<>();
        String name = "testName";
        boolean withQuotes = false;
        ValuePatterns mockPattern = mock(ValuePatterns.class);
        try (MockedStatic<Patterns> mockedPatterns = mockStatic(Patterns.class)) {
            mockedPatterns.when(() -> Patterns.exactMatch("false")).thenReturn(mockPattern);
            try {
                invokeWriteRules(rules, name, parser, withQuotes);
            } catch (Exception e) {
                fail("Failed to invoke writeRules", e);
            }
            assertEquals(1, rules.size(), "Rules size should be 1");
            assertTrue(rules.get(0).get(name).contains(mockPattern), "Rules should contain the exactMatch pattern for false");
        }
    }

    @Test
    void testWriteRules_withExistingKey_shouldUpdateWithNewExactMatchPattern() throws IOException {
        String jsonArray = "[\"newValue\"]";
        JsonParser parser = jsonFactory.createParser(new StringReader(jsonArray));
        List<Map<String, List<Patterns>>> rules = new ArrayList<>();
        Map<String, List<Patterns>> existingRule = new HashMap<>();
        existingRule.put("existingKey", Collections.singletonList(Patterns.exactMatch("existingValue")));
        rules.add(existingRule);
        String name = "existingKey";
        boolean withQuotes = false;
        ValuePatterns mockPattern = mock(ValuePatterns.class);
        try (MockedStatic<Patterns> mockedPatterns = mockStatic(Patterns.class)) {
            mockedPatterns.when(() -> Patterns.exactMatch("newValue")).thenReturn(mockPattern);
            try {
                invokeWriteRules(rules, name, parser, withQuotes);
            } catch (Exception e) {
                fail("Failed to invoke writeRules", e);
            }
            assertEquals(1, rules.size(), "Rules size should be 1");
            assertTrue(rules.get(0).get(name).contains(mockPattern), "Rules should update the existing key with the new exactMatch pattern");
        }
    }

    @Test
    void testWriteRules_withValueStringAndValueNumberIntTokens_shouldAddPatternsForBoth() throws IOException {
        String jsonArray = "[\"text\", 10]";
        JsonParser parser = jsonFactory.createParser(new StringReader(jsonArray));
        List<Map<String, List<Patterns>>> rules = new ArrayList<>();
        String name = "testName";
        boolean withQuotes = false;
        ValuePatterns mockStringPattern = mock(ValuePatterns.class);
        ValuePatterns mockNumericPattern = mock(ValuePatterns.class);
        ValuePatterns mockExactPattern = mock(ValuePatterns.class);
        try (MockedStatic<Patterns> mockedPatterns = mockStatic(Patterns.class)) {
            mockedPatterns.when(() -> Patterns.exactMatch("text")).thenReturn(mockStringPattern);
            mockedPatterns.when(() -> Patterns.numericEquals(10.0)).thenReturn(mockNumericPattern);
            mockedPatterns.when(() -> Patterns.exactMatch("10")).thenReturn(mockExactPattern);
            try {
                invokeWriteRules(rules, name, parser, withQuotes);
            } catch (Exception e) {
                fail("Failed to invoke writeRules", e);
            }
            assertEquals(1, rules.size(), "Rules size should be 1");
            assertTrue(rules.get(0).get(name).contains(mockStringPattern), "Rules should contain the exactMatch pattern for the string");
            assertTrue(rules.get(0).get(name).contains(mockNumericPattern), "Rules should contain the numericEquals pattern for the number");
            assertTrue(rules.get(0).get(name).contains(mockExactPattern), "Rules should contain the exactMatch pattern for the number");
        }
    }

    @Test
    void testWriteRules_withNestedStartObject_shouldProcessNestedStructure() throws IOException {
        String jsonArray = "[{\"nestedKey\":\"nestedString\"}]";
        JsonParser parser = jsonFactory.createParser(new StringReader(jsonArray));
        List<Map<String, List<Patterns>>> rules = new ArrayList<>();
        String name = "testName";
        boolean withQuotes = false;
        Patterns mockPattern = mock(Patterns.class);
        try (MockedStatic<JsonRuleCompiler> mockedCompiler = mockStatic(JsonRuleCompiler.class)) {
            mockedCompiler.when(() -> invokeProcessMatchExpression(parser)).thenReturn(mockPattern);
            try {
                invokeWriteRules(rules, name, parser, withQuotes);
            } catch (Exception e) {
                fail("Failed to invoke writeRules", e);
            }
            assertEquals(1, rules.size(), "Rules size should be 1");
            assertTrue(rules.get(0).get(name).contains(mockPattern), "Rules should contain the processed pattern from the nested object");
        }
    }

    @Test
    void testWriteRules_withValueNumberIntAndValueStringTokens_shouldAddPatternsForBoth() throws IOException {
        String jsonArray = "[123, \"invalid\"]";
        JsonParser parser = jsonFactory.createParser(new StringReader(jsonArray));
        List<Map<String, List<Patterns>>> rules = new ArrayList<>();
        String name = "testName";
        boolean withQuotes = false;
        ValuePatterns mockNumericPattern = mock(ValuePatterns.class);
        ValuePatterns mockExactNumericPattern = mock(ValuePatterns.class);
        ValuePatterns mockStringPattern = mock(ValuePatterns.class);
        try (MockedStatic<Patterns> mockedPatterns = mockStatic(Patterns.class)) {
            mockedPatterns.when(() -> Patterns.numericEquals(123.0)).thenReturn(mockNumericPattern);
            mockedPatterns.when(() -> Patterns.exactMatch("123")).thenReturn(mockExactNumericPattern);
            mockedPatterns.when(() -> Patterns.exactMatch("invalid")).thenReturn(mockStringPattern);
            try {
                invokeWriteRules(rules, name, parser, withQuotes);
            } catch (Exception e) {
                fail("Failed to invoke writeRules", e);
            }
            assertEquals(1, rules.size(), "Rules size should be 1");
            assertTrue(rules.get(0).get(name).contains(mockNumericPattern), "Rules should contain the numericEquals pattern for the number");
            assertTrue(rules.get(0).get(name).contains(mockExactNumericPattern), "Rules should contain the exactMatch pattern for the number");
            assertTrue(rules.get(0).get(name).contains(mockStringPattern), "Rules should contain the exactMatch pattern for the string");
        }
    }

    @Test
    void testWriteRules_withValueStringAndValueNumberFloatTokens_shouldAddPatternsForBoth() throws IOException {
        String jsonArray = "[\"string\", 123.45]";
        JsonParser parser = jsonFactory.createParser(new StringReader(jsonArray));
        List<Map<String, List<Patterns>>> rules = new ArrayList<>();
        String name = "testName";
        boolean withQuotes = true;
        ValuePatterns mockQuotedStringPattern = mock(ValuePatterns.class);
        ValuePatterns mockNumericPattern = mock(ValuePatterns.class);
        ValuePatterns mockExactPattern = mock(ValuePatterns.class);
        try (MockedStatic<Patterns> mockedPatterns = mockStatic(Patterns.class)) {
            mockedPatterns.when(() -> Patterns.exactMatch("\"string\"")).thenReturn(mockQuotedStringPattern);
            mockedPatterns.when(() -> Patterns.numericEquals(123.45)).thenReturn(mockNumericPattern);
            mockedPatterns.when(() -> Patterns.exactMatch("123.45")).thenReturn(mockExactPattern);
            try {
                invokeWriteRules(rules, name, parser, withQuotes);
            } catch (Exception e) {
                fail("Failed to invoke writeRules", e);
            }
            assertEquals(1, rules.size(), "Rules size should be 1");
            assertTrue(rules.get(0).get(name).contains(mockQuotedStringPattern), "Rules should contain the quoted exactMatch pattern for the string");
            assertTrue(rules.get(0).get(name).contains(mockNumericPattern), "Rules should contain the numericEquals pattern for the float");
            assertTrue(rules.get(0).get(name).contains(mockExactPattern), "Rules should contain the exactMatch pattern for the float");
        }
    }

    @Test
    void testWriteRules_withValueNullTrueFalseTokens_shouldAddExactMatchPatterns() throws IOException {
        String jsonArray = "[null, true, false]";
        JsonParser parser = jsonFactory.createParser(new StringReader(jsonArray));
        List<Map<String, List<Patterns>>> rules = new ArrayList<>();
        String name = "testName";
        boolean withQuotes = false;
        ValuePatterns mockNullPattern = mock(ValuePatterns.class);
        ValuePatterns mockTruePattern = mock(ValuePatterns.class);
        ValuePatterns mockFalsePattern = mock(ValuePatterns.class);
        try (MockedStatic<Patterns> mockedPatterns = mockStatic(Patterns.class)) {
            mockedPatterns.when(() -> Patterns.exactMatch("null")).thenReturn(mockNullPattern);
            mockedPatterns.when(() -> Patterns.exactMatch("true")).thenReturn(mockTruePattern);
            mockedPatterns.when(() -> Patterns.exactMatch("false")).thenReturn(mockFalsePattern);
            try {
                invokeWriteRules(rules, name, parser, withQuotes);
            } catch (Exception e) {
                fail("Failed to invoke writeRules", e);
            }
            assertEquals(1, rules.size(), "Rules size should be 1");
            assertTrue(rules.get(0).get(name).contains(mockNullPattern), "Rules should contain the exactMatch pattern for null");
            assertTrue(rules.get(0).get(name).contains(mockTruePattern), "Rules should contain the exactMatch pattern for true");
            assertTrue(rules.get(0).get(name).contains(mockFalsePattern), "Rules should contain the exactMatch pattern for false");
        }
    }

    @Test
    void testWriteRules_withUnknownToken_shouldThrowJsonParseException() {
        String jsonArray = "[unknown]";
        JsonParser parser;
        try {
            parser = jsonFactory.createParser(new StringReader(jsonArray));
            List<Map<String, List<Patterns>>> rules = new ArrayList<>();
            String name = "testName";
            boolean withQuotes = false;
            JsonParseException exception = assertThrows(JsonParseException.class, () -> {
                invokeWriteRules(rules, name, parser, withQuotes);
            }, "Expected JsonParseException due to unknown token");
            assertEquals("Match value must be String, number, true, false, or null", exception.getMessage());
        } catch (IOException e) {
            fail("Failed to create JsonParser", e);
        }
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
│     ├─ testWriteRules_withValueNumberIntAndValueStringTokens_shouldAddPatternsForBoth() ✘ Failed to invoke writeRules
│     ├─ testWriteRules_withValueNumberFloatToken_shouldAddNumericEqualsAndExactMatchForF... ✘ Failed to invoke writeRules
│     ├─ testWriteRules_withNestedStartObject_shouldProcessNestedStructure() ✘ Rules size should be 1 ==> expected: <1> but was: <0>
│     ├─ testWriteRules_withUnknownToken_shouldThrowJsonParseException() ✘ Expected JsonParseException due to unknown token ==> Unexpected exception type thrown, expected: <com.fasterxml.jackson.core.JsonParseException> but was: <java.lang.reflect.InvocationTargetException>
│     ├─ testWriteRules_withValueFalseToken_shouldAddExactMatchForFalse() ✘ Failed to invoke writeRules
│     ├─ testWriteRules_withValueNullTrueFalseTokens_shouldAddExactMatchPatterns() ✘ Failed to invoke writeRules
│     ├─ testWriteRules_withValueNumberIntToken_shouldAddNumericEqualsAndExactMatch() ✘ Failed to invoke writeRules
│     ├─ testWriteRules_withValidJsonArray_shouldPopulateRules() ✘ Failed to invoke writeRules
│     ├─ testWriteRules_withExistingRules_shouldAddNewKeyAndExactMatchForTrue() ✘ Failed to invoke writeRules
│     ├─ testWriteRules_withValueStringAndValueNumberIntTokens_shouldAddPatternsForBoth() ✘ Failed to invoke writeRules
│     ├─ testWriteRules_withValueStringAndValueNumberFloatTokens_shouldAddPatternsForBoth... ✘ Failed to invoke writeRules
│     ├─ testWriteRules_withValueStringTokenAndIp_shouldConvertToRange() ✘ Failed to invoke writeRules
│     ├─ testWriteRules_withValueStringTokenAndWithQuotes_shouldAddQuotedExactMatch() ✘ Failed to invoke writeRules
│     ├─ testWriteRules_withEndArrayToken_shouldThrowJsonParseException() ✘ Expected JsonParseException due to empty array ==> Unexpected exception type thrown, expected: <com.fasterxml.jackson.core.JsonParseException> but was: <java.lang.reflect.InvocationTargetException>
│     ├─ testWriteRules_withExistingKey_shouldUpdateWithNewExactMatchPattern() ✘ Failed to invoke writeRules
│     ├─ testWriteRules_withValueNullToken_shouldAddExactMatchForNull() ✘ Failed to invoke writeRules
│     └─ testWriteRules_withStartObjectToken_shouldInvokeProcessMatchExpression() ✘ Rules size should be 1 ==> expected: <1> but was: <0>
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Failures (17):
  JUnit Jupiter:JsonRuleCompiler_writeRules_Test:testWriteRules_withValueNumberIntAndValueStringTokens_shouldAddPatternsForBoth()
    MethodSource [className = 'software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test', methodName = 'testWriteRules_withValueNumberIntAndValueStringTokens_shouldAddPatternsForBoth', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Failed to invoke writeRules
       org.junit.jupiter.api.AssertionUtils.fail(AssertionUtils.java:42)
       org.junit.jupiter.api.Assertions.fail(Assertions.java:150)
       software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test.testWriteRules_withValueNumberIntAndValueStringTokens_shouldAddPatternsForBoth(JsonRuleCompiler_writeRules_Test.java:330)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test.invokeWriteRules(JsonRuleCompiler_writeRules_Test.java:31)
       [...]
     Caused by: com.fasterxml.jackson.core.JsonParseException: Match value must be String, number, true, false, or null
 at [Source: (StringReader); line: 1, column: 2]
       software.amazon.event.ruler.JsonRuleCompiler.barf(Unknown Source)
       software.amazon.event.ruler.JsonRuleCompiler.writeRules(Unknown Source)
       [...]
  JUnit Jupiter:JsonRuleCompiler_writeRules_Test:testWriteRules_withValueNumberFloatToken_shouldAddNumericEqualsAndExactMatchForFloat()
    MethodSource [className = 'software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test', methodName = 'testWriteRules_withValueNumberFloatToken_shouldAddNumericEqualsAndExactMatchForFloat', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Failed to invoke writeRules
       org.junit.jupiter.api.AssertionUtils.fail(AssertionUtils.java:42)
       org.junit.jupiter.api.Assertions.fail(Assertions.java:150)
       software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test.testWriteRules_withValueNumberFloatToken_shouldAddNumericEqualsAndExactMatchForFloat(JsonRuleCompiler_writeRules_Test.java:216)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test.invokeWriteRules(JsonRuleCompiler_writeRules_Test.java:31)
       [...]
     Caused by: com.fasterxml.jackson.core.JsonParseException: Match value must be String, number, true, false, or null
 at [Source: (StringReader); line: 1, column: 2]
       software.amazon.event.ruler.JsonRuleCompiler.barf(Unknown Source)
       software.amazon.event.ruler.JsonRuleCompiler.writeRules(Unknown Source)
       [...]
  JUnit Jupiter:JsonRuleCompiler_writeRules_Test:testWriteRules_withNestedStartObject_shouldProcessNestedStructure()
    MethodSource [className = 'software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test', methodName = 'testWriteRules_withNestedStartObject_shouldProcessNestedStructure', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Rules size should be 1 ==> expected: <1> but was: <0>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:150)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:563)
       software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test.testWriteRules_withNestedStartObject_shouldProcessNestedStructure(JsonRuleCompiler_writeRules_Test.java:308)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       [...]
  JUnit Jupiter:JsonRuleCompiler_writeRules_Test:testWriteRules_withUnknownToken_shouldThrowJsonParseException()
    MethodSource [className = 'software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test', methodName = 'testWriteRules_withUnknownToken_shouldThrowJsonParseException', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Expected JsonParseException due to unknown token ==> Unexpected exception type thrown, expected: <com.fasterxml.jackson.core.JsonParseException> but was: <java.lang.reflect.InvocationTargetException>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:67)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:39)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3153)
       software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test.testWriteRules_withUnknownToken_shouldThrowJsonParseException(JsonRuleCompiler_writeRules_Test.java:400)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test.invokeWriteRules(JsonRuleCompiler_writeRules_Test.java:31)
       [...]
     Caused by: com.fasterxml.jackson.core.JsonParseException: Match value must be String, number, true, false, or null
 at [Source: (StringReader); line: 1, column: 2]
       software.amazon.event.ruler.JsonRuleCompiler.barf(JsonRuleCompiler.java:712)
       software.amazon.event.ruler.JsonRuleCompiler.writeRules(JsonRuleCompiler.java:328)
       [...]
  JUnit Jupiter:JsonRuleCompiler_writeRules_Test:testWriteRules_withValueFalseToken_shouldAddExactMatchForFalse()
    MethodSource [className = 'software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test', methodName = 'testWriteRules_withValueFalseToken_shouldAddExactMatchForFalse', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Failed to invoke writeRules
       org.junit.jupiter.api.AssertionUtils.fail(AssertionUtils.java:42)
       org.junit.jupiter.api.Assertions.fail(Assertions.java:150)
       software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test.testWriteRules_withValueFalseToken_shouldAddExactMatchForFalse(JsonRuleCompiler_writeRules_Test.java:237)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test.invokeWriteRules(JsonRuleCompiler_writeRules_Test.java:31)
       [...]
     Caused by: com.fasterxml.jackson.core.JsonParseException: Match value must be String, number, true, false, or null
 at [Source: (StringReader); line: 1, column: 2]
       software.amazon.event.ruler.JsonRuleCompiler.barf(JsonRuleCompiler.java:712)
       software.amazon.event.ruler.JsonRuleCompiler.writeRules(JsonRuleCompiler.java:328)
       [...]
  JUnit Jupiter:JsonRuleCompiler_writeRules_Test:testWriteRules_withValueNullTrueFalseTokens_shouldAddExactMatchPatterns()
    MethodSource [className = 'software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test', methodName = 'testWriteRules_withValueNullTrueFalseTokens_shouldAddExactMatchPatterns', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Failed to invoke writeRules
       org.junit.jupiter.api.AssertionUtils.fail(AssertionUtils.java:42)
       org.junit.jupiter.api.Assertions.fail(Assertions.java:150)
       software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test.testWriteRules_withValueNullTrueFalseTokens_shouldAddExactMatchPatterns(JsonRuleCompiler_writeRules_Test.java:382)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test.invokeWriteRules(JsonRuleCompiler_writeRules_Test.java:31)
       [...]
     Caused by: com.fasterxml.jackson.core.JsonParseException: Match value must be String, number, true, false, or null
 at [Source: (StringReader); line: 1, column: 2]
       software.amazon.event.ruler.JsonRuleCompiler.barf(JsonRuleCompiler.java:712)
       software.amazon.event.ruler.JsonRuleCompiler.writeRules(JsonRuleCompiler.java:328)
       [...]
  JUnit Jupiter:JsonRuleCompiler_writeRules_Test:testWriteRules_withValueNumberIntToken_shouldAddNumericEqualsAndExactMatch()
    MethodSource [className = 'software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test', methodName = 'testWriteRules_withValueNumberIntToken_shouldAddNumericEqualsAndExactMatch', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Failed to invoke writeRules
       org.junit.jupiter.api.AssertionUtils.fail(AssertionUtils.java:42)
       org.junit.jupiter.api.Assertions.fail(Assertions.java:150)
       software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test.testWriteRules_withValueNumberIntToken_shouldAddNumericEqualsAndExactMatch(JsonRuleCompiler_writeRules_Test.java:132)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test.invokeWriteRules(JsonRuleCompiler_writeRules_Test.java:31)
       [...]
     Caused by: com.fasterxml.jackson.core.JsonParseException: Match value must be String, number, true, false, or null
 at [Source: (StringReader); line: 1, column: 2]
       software.amazon.event.ruler.JsonRuleCompiler.barf(JsonRuleCompiler.java:712)
       software.amazon.event.ruler.JsonRuleCompiler.writeRules(JsonRuleCompiler.java:328)
       [...]
  JUnit Jupiter:JsonRuleCompiler_writeRules_Test:testWriteRules_withValidJsonArray_shouldPopulateRules()
    MethodSource [className = 'software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test', methodName = 'testWriteRules_withValidJsonArray_shouldPopulateRules', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Failed to invoke writeRules
       org.junit.jupiter.api.AssertionUtils.fail(AssertionUtils.java:42)
       org.junit.jupiter.api.Assertions.fail(Assertions.java:150)
       software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test.testWriteRules_withValidJsonArray_shouldPopulateRules(JsonRuleCompiler_writeRules_Test.java:50)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test.invokeWriteRules(JsonRuleCompiler_writeRules_Test.java:31)
       [...]
     Caused by: com.fasterxml.jackson.core.JsonParseException: Match value must be String, number, true, false, or null
 at [Source: (StringReader); line: 1, column: 2]
       software.amazon.event.ruler.JsonRuleCompiler.barf(JsonRuleCompiler.java:712)
       software.amazon.event.ruler.JsonRuleCompiler.writeRules(JsonRuleCompiler.java:328)
       [...]
  JUnit Jupiter:JsonRuleCompiler_writeRules_Test:testWriteRules_withExistingRules_shouldAddNewKeyAndExactMatchForTrue()
    MethodSource [className = 'software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test', methodName = 'testWriteRules_withExistingRules_shouldAddNewKeyAndExactMatchForTrue', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Failed to invoke writeRules
       org.junit.jupiter.api.AssertionUtils.fail(AssertionUtils.java:42)
       org.junit.jupiter.api.Assertions.fail(Assertions.java:150)
       software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test.testWriteRules_withExistingRules_shouldAddNewKeyAndExactMatchForTrue(JsonRuleCompiler_writeRules_Test.java:194)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test.invokeWriteRules(JsonRuleCompiler_writeRules_Test.java:31)
       [...]
     Caused by: com.fasterxml.jackson.core.JsonParseException: Match value must be String, number, true, false, or null
 at [Source: (StringReader); line: 1, column: 2]
       software.amazon.event.ruler.JsonRuleCompiler.barf(JsonRuleCompiler.java:712)
       software.amazon.event.ruler.JsonRuleCompiler.writeRules(JsonRuleCompiler.java:328)
       [...]
  JUnit Jupiter:JsonRuleCompiler_writeRules_Test:testWriteRules_withValueStringAndValueNumberIntTokens_shouldAddPatternsForBoth()
    MethodSource [className = 'software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test', methodName = 'testWriteRules_withValueStringAndValueNumberIntTokens_shouldAddPatternsForBoth', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Failed to invoke writeRules
       org.junit.jupiter.api.AssertionUtils.fail(AssertionUtils.java:42)
       org.junit.jupiter.api.Assertions.fail(Assertions.java:150)
       software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test.testWriteRules_withValueStringAndValueNumberIntTokens_shouldAddPatternsForBoth(JsonRuleCompiler_writeRules_Test.java:284)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test.invokeWriteRules(JsonRuleCompiler_writeRules_Test.java:31)
       [...]
     Caused by: com.fasterxml.jackson.core.JsonParseException: Match value must be String, number, true, false, or null
 at [Source: (StringReader); line: 1, column: 2]
       software.amazon.event.ruler.JsonRuleCompiler.barf(JsonRuleCompiler.java:712)
       software.amazon.event.ruler.JsonRuleCompiler.writeRules(JsonRuleCompiler.java:328)
       [...]
  JUnit Jupiter:JsonRuleCompiler_writeRules_Test:testWriteRules_withValueStringAndValueNumberFloatTokens_shouldAddPatternsForBoth()
    MethodSource [className = 'software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test', methodName = 'testWriteRules_withValueStringAndValueNumberFloatTokens_shouldAddPatternsForBoth', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Failed to invoke writeRules
       org.junit.jupiter.api.AssertionUtils.fail(AssertionUtils.java:42)
       org.junit.jupiter.api.Assertions.fail(Assertions.java:150)
       software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test.testWriteRules_withValueStringAndValueNumberFloatTokens_shouldAddPatternsForBoth(JsonRuleCompiler_writeRules_Test.java:356)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test.invokeWriteRules(JsonRuleCompiler_writeRules_Test.java:31)
       [...]
     Caused by: com.fasterxml.jackson.core.JsonParseException: Match value must be String, number, true, false, or null
 at [Source: (StringReader); line: 1, column: 2]
       software.amazon.event.ruler.JsonRuleCompiler.barf(JsonRuleCompiler.java:712)
       software.amazon.event.ruler.JsonRuleCompiler.writeRules(JsonRuleCompiler.java:328)
       [...]
  JUnit Jupiter:JsonRuleCompiler_writeRules_Test:testWriteRules_withValueStringTokenAndIp_shouldConvertToRange()
    MethodSource [className = 'software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test', methodName = 'testWriteRules_withValueStringTokenAndIp_shouldConvertToRange', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Failed to invoke writeRules
       org.junit.jupiter.api.AssertionUtils.fail(AssertionUtils.java:42)
       org.junit.jupiter.api.Assertions.fail(Assertions.java:150)
       software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test.testWriteRules_withValueStringTokenAndIp_shouldConvertToRange(JsonRuleCompiler_writeRules_Test.java:90)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test.invokeWriteRules(JsonRuleCompiler_writeRules_Test.java:31)
       [...]
     Caused by: com.fasterxml.jackson.core.JsonParseException: Match value must be String, number, true, false, or null
 at [Source: (StringReader); line: 1, column: 2]
       software.amazon.event.ruler.JsonRuleCompiler.barf(JsonRuleCompiler.java:712)
       software.amazon.event.ruler.JsonRuleCompiler.writeRules(JsonRuleCompiler.java:328)
       [...]
  JUnit Jupiter:JsonRuleCompiler_writeRules_Test:testWriteRules_withValueStringTokenAndWithQuotes_shouldAddQuotedExactMatch()
    MethodSource [className = 'software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test', methodName = 'testWriteRules_withValueStringTokenAndWithQuotes_shouldAddQuotedExactMatch', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Failed to invoke writeRules
       org.junit.jupiter.api.AssertionUtils.fail(AssertionUtils.java:42)
       org.junit.jupiter.api.Assertions.fail(Assertions.java:150)
       software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test.testWriteRules_withValueStringTokenAndWithQuotes_shouldAddQuotedExactMatch(JsonRuleCompiler_writeRules_Test.java:110)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test.invokeWriteRules(JsonRuleCompiler_writeRules_Test.java:31)
       [...]
     Caused by: com.fasterxml.jackson.core.JsonParseException: Match value must be String, number, true, false, or null
 at [Source: (StringReader); line: 1, column: 2]
       software.amazon.event.ruler.JsonRuleCompiler.barf(JsonRuleCompiler.java:712)
       software.amazon.event.ruler.JsonRuleCompiler.writeRules(JsonRuleCompiler.java:328)
       [...]
  JUnit Jupiter:JsonRuleCompiler_writeRules_Test:testWriteRules_withEndArrayToken_shouldThrowJsonParseException()
    MethodSource [className = 'software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test', methodName = 'testWriteRules_withEndArrayToken_shouldThrowJsonParseException', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Expected JsonParseException due to empty array ==> Unexpected exception type thrown, expected: <com.fasterxml.jackson.core.JsonParseException> but was: <java.lang.reflect.InvocationTargetException>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:67)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:39)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3153)
       software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test.testWriteRules_withEndArrayToken_shouldThrowJsonParseException(JsonRuleCompiler_writeRules_Test.java:169)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test.invokeWriteRules(JsonRuleCompiler_writeRules_Test.java:31)
       [...]
     Caused by: com.fasterxml.jackson.core.JsonParseException: Match value must be String, number, true, false, or null
 at [Source: (StringReader); line: 1, column: 2]
       software.amazon.event.ruler.JsonRuleCompiler.barf(JsonRuleCompiler.java:712)
       software.amazon.event.ruler.JsonRuleCompiler.writeRules(JsonRuleCompiler.java:328)
       [...]
  JUnit Jupiter:JsonRuleCompiler_writeRules_Test:testWriteRules_withExistingKey_shouldUpdateWithNewExactMatchPattern()
    MethodSource [className = 'software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test', methodName = 'testWriteRules_withExistingKey_shouldUpdateWithNewExactMatchPattern', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Failed to invoke writeRules
       org.junit.jupiter.api.AssertionUtils.fail(AssertionUtils.java:42)
       org.junit.jupiter.api.Assertions.fail(Assertions.java:150)
       software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test.testWriteRules_withExistingKey_shouldUpdateWithNewExactMatchPattern(JsonRuleCompiler_writeRules_Test.java:260)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test.invokeWriteRules(JsonRuleCompiler_writeRules_Test.java:31)
       [...]
     Caused by: com.fasterxml.jackson.core.JsonParseException: Match value must be String, number, true, false, or null
 at [Source: (StringReader); line: 1, column: 2]
       software.amazon.event.ruler.JsonRuleCompiler.barf(JsonRuleCompiler.java:712)
       software.amazon.event.ruler.JsonRuleCompiler.writeRules(JsonRuleCompiler.java:328)
       [...]
  JUnit Jupiter:JsonRuleCompiler_writeRules_Test:testWriteRules_withValueNullToken_shouldAddExactMatchForNull()
    MethodSource [className = 'software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test', methodName = 'testWriteRules_withValueNullToken_shouldAddExactMatchForNull', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Failed to invoke writeRules
       org.junit.jupiter.api.AssertionUtils.fail(AssertionUtils.java:42)
       org.junit.jupiter.api.Assertions.fail(Assertions.java:150)
       software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test.testWriteRules_withValueNullToken_shouldAddExactMatchForNull(JsonRuleCompiler_writeRules_Test.java:153)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test.invokeWriteRules(JsonRuleCompiler_writeRules_Test.java:31)
       [...]
     Caused by: com.fasterxml.jackson.core.JsonParseException: Match value must be String, number, true, false, or null
 at [Source: (StringReader); line: 1, column: 2]
       software.amazon.event.ruler.JsonRuleCompiler.barf(JsonRuleCompiler.java:712)
       software.amazon.event.ruler.JsonRuleCompiler.writeRules(JsonRuleCompiler.java:328)
       [...]
  JUnit Jupiter:JsonRuleCompiler_writeRules_Test:testWriteRules_withStartObjectToken_shouldInvokeProcessMatchExpression()
    MethodSource [className = 'software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test', methodName = 'testWriteRules_withStartObjectToken_shouldInvokeProcessMatchExpression', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Rules size should be 1 ==> expected: <1> but was: <0>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:150)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:563)
       software.amazon.event.ruler.JsonRuleCompiler_writeRules_Test.testWriteRules_withStartObjectToken_shouldInvokeProcessMatchExpression(JsonRuleCompiler_writeRules_Test.java:72)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       [...]

Test run finished after 526 ms
[         4 containers found      ]
[         0 containers skipped    ]
[         4 containers started    ]
[         0 containers aborted    ]
[         4 containers successful ]
[         0 containers failed     ]
[        17 tests found           ]
[         0 tests skipped         ]
[        17 tests started         ]
[         0 tests aborted         ]
[         0 tests successful      ]
[        17 tests failed          ]


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
		method `private static void writeRules(Map<String, List<Patterns>>, String, JsonParser, boolean)` returns `void`, related with `software.amazon.event.ruler.JsonRuleCompiler.writeRules(List<Map<String, List<Patterns>>>, String, JsonParser, boolean)`;
		method `private static Patterns processAnythingButMatchExpression(JsonParser, JsonToken)` returns `software.amazon.event.ruler.Patterns`, related with `software.amazon.event.ruler.JsonRuleCompiler.writeRules(List<Map<String, List<Patterns>>>, String, JsonParser, boolean)`;
		method `private static Patterns processAnythingButListMatchExpression(JsonParser)` returns `software.amazon.event.ruler.Patterns`, related with `software.amazon.event.ruler.JsonRuleCompiler.writeRules(List<Map<String, List<Patterns>>>, String, JsonParser, boolean)`;
class `software.amazon.event.ruler.RuleCompiler.ListBasedRuleCompiler`:
	related functions:
		method `private static void writeRules(Map<List<String>, List<Patterns>>, List<String>, JsonParser, boolean)` returns `void`, related with `software.amazon.event.ruler.JsonRuleCompiler.writeRules(List<Map<String, List<Patterns>>>, String, JsonParser, boolean)`;
class `software.amazon.event.ruler.JsonRuleCompiler`:
	related functions:
		method `private static Patterns processAnythingButEqualsIgnoreCaseListMatchExpression(JsonParser)` returns `software.amazon.event.ruler.Patterns`, related with `software.amazon.event.ruler.JsonRuleCompiler.writeRules(List<Map<String, List<Patterns>>>, String, JsonParser, boolean)`;
		method `private static Patterns completeNumericRange(JsonParser, JsonToken, double, boolean)` returns `software.amazon.event.ruler.Patterns`, related with `software.amazon.event.ruler.JsonRuleCompiler.writeRules(List<Map<String, List<Patterns>>>, String, JsonParser, boolean)`;
		method `private static Patterns processAnythingButMatchExpression(JsonParser, JsonToken)` returns `software.amazon.event.ruler.Patterns`, related with `software.amazon.event.ruler.JsonRuleCompiler.writeRules(List<Map<String, List<Patterns>>>, String, JsonParser, boolean)`;
		method `private static Patterns processAnythingButListMatchExpression(JsonParser)` returns `software.amazon.event.ruler.Patterns`, related with `software.amazon.event.ruler.JsonRuleCompiler.writeRules(List<Map<String, List<Patterns>>>, String, JsonParser, boolean)`;
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



Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 
