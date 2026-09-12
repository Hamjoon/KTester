
You are an experienced Java developer tasked with fixing a piece of code that contains compilation errors. Your task is to resolve these issues after analyzing the code and its compilation feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any syntactical or logical errors.
2. Analyze the compilation feedback to understand the specific errors and their locations within the code.
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
        // Initialize JsonFactory or other dependencies if needed
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
        // Given
        String jsonArray = "[{\"key1\":\"value1\"}, {\"key2\":\"value2\"}]";
        JsonParser parser = jsonFactory.createParser(new StringReader(jsonArray));
        List<Map<String, List<Patterns>>> rules = new ArrayList<>();
        String name = "testName";
        boolean withQuotes = false;
        // When
        invokeWriteRules(rules, name, parser, withQuotes);
        // Then
        assertFalse(rules.isEmpty(), "Rules should not be empty");
        assertEquals(1, rules.size(), "Rules size should be 1");
        assertTrue(rules.get(0).containsKey(name), "Rules should contain the given name");
    }

    @Test
    void testWriteRules_withStartObjectToken_shouldInvokeProcessMatchExpression() throws IOException {
        // Given
        String jsonArray = "[{}]";
        JsonParser parser = jsonFactory.createParser(new StringReader(jsonArray));
        List<Map<String, List<Patterns>>> rules = new ArrayList<>();
        String name = "testName";
        boolean withQuotes = false;
        // Mock processMatchExpression method
        Patterns mockPattern = mock(Patterns.class);
        try (MockedStatic<JsonRuleCompiler> mockedCompiler = mockStatic(JsonRuleCompiler.class)) {
            mockedCompiler.when(() -> invokeProcessMatchExpression(parser)).thenReturn(mockPattern);
            // When
            invokeWriteRules(rules, name, parser, withQuotes);
            // Then
            assertEquals(1, rules.size(), "Rules size should be 1");
            assertTrue(rules.get(0).get(name).contains(mockPattern), "Rules should contain the processed pattern");
        }
    }

    @Test
    void testWriteRules_withValueStringTokenAndIp_shouldConvertToRange() throws IOException {
        // Given
        String jsonArray = "[\"192.168.0.1\"]";
        JsonParser parser = jsonFactory.createParser(new StringReader(jsonArray));
        List<Map<String, List<Patterns>>> rules = new ArrayList<>();
        String name = "testName";
        boolean withQuotes = false;
        // Mock CIDR.ipToRangeIfPossible method
        Patterns mockRange = mock(Patterns.class);
        try (MockedStatic<CIDR> mockedCIDR = mockStatic(CIDR.class)) {
            mockedCIDR.when(() -> CIDR.ipToRangeIfPossible("192.168.0.1")).thenReturn(mockRange);
            // When
            JsonRuleCompiler.writeRules(rules, name, parser, withQuotes);
            // Then
            assertEquals(1, rules.size(), "Rules size should be 1");
            assertTrue(rules.get(0).get(name).contains(mockRange), "Rules should contain the IP range pattern");
        }
    }

    @Test
    void testWriteRules_withValueStringTokenAndWithQuotes_shouldAddQuotedExactMatch() throws IOException {
        // Given
        String jsonArray = "[\"testString\"]";
        JsonParser parser = jsonFactory.createParser(new StringReader(jsonArray));
        List<Map<String, List<Patterns>>> rules = new ArrayList<>();
        String name = "testName";
        boolean withQuotes = true;
        // Mock Patterns.exactMatch method
        Patterns mockPattern = mock(Patterns.class);
        try (MockedStatic<Patterns> mockedPatterns = mockStatic(Patterns.class)) {
            mockedPatterns.when(() -> Patterns.exactMatch("\"testString\"")).thenReturn(mockPattern);
            // When
            JsonRuleCompiler.writeRules(rules, name, parser, withQuotes);
            // Then
            assertEquals(1, rules.size(), "Rules size should be 1");
            assertTrue(rules.get(0).get(name).contains(mockPattern), "Rules should contain the quoted exact match pattern");
        }
    }

    @Test
    void testWriteRules_withValueNumberIntToken_shouldAddNumericEqualsAndExactMatch() throws IOException {
        // Given
        String jsonArray = "[123]";
        JsonParser parser = jsonFactory.createParser(new StringReader(jsonArray));
        List<Map<String, List<Patterns>>> rules = new ArrayList<>();
        String name = "testName";
        boolean withQuotes = false;
        // Mock Patterns.numericEquals and Patterns.exactMatch methods
        Patterns mockNumericPattern = mock(Patterns.class);
        Patterns mockExactPattern = mock(Patterns.class);
        try (MockedStatic<Patterns> mockedPatterns = mockStatic(Patterns.class)) {
            mockedPatterns.when(() -> Patterns.numericEquals(123.0)).thenReturn(mockNumericPattern);
            mockedPatterns.when(() -> Patterns.exactMatch("123")).thenReturn(mockExactPattern);
            // When
            JsonRuleCompiler.writeRules(rules, name, parser, withQuotes);
            // Then
            assertEquals(1, rules.size(), "Rules size should be 1");
            assertTrue(rules.get(0).get(name).contains(mockNumericPattern), "Rules should contain the numericEquals pattern");
            assertTrue(rules.get(0).get(name).contains(mockExactPattern), "Rules should contain the exactMatch pattern");
        }
    }

    @Test
    void testWriteRules_withValueNullToken_shouldAddExactMatchForNull() throws IOException {
        // Given
        String jsonArray = "[null]";
        JsonParser parser = jsonFactory.createParser(new StringReader(jsonArray));
        List<Map<String, List<Patterns>>> rules = new ArrayList<>();
        String name = "testName";
        boolean withQuotes = false;
        // Mock Patterns.exactMatch method
        Patterns mockPattern = mock(Patterns.class);
        try (MockedStatic<Patterns> mockedPatterns = mockStatic(Patterns.class)) {
            mockedPatterns.when(() -> Patterns.exactMatch("null")).thenReturn(mockPattern);
            // When
            JsonRuleCompiler.writeRules(rules, name, parser, withQuotes);
            // Then
            assertEquals(1, rules.size(), "Rules size should be 1");
            assertTrue(rules.get(0).get(name).contains(mockPattern), "Rules should contain the exactMatch pattern for null");
        }
    }

    @Test
    void testWriteRules_withEndArrayToken_shouldThrowJsonParseException() {
        // Given
        String jsonArray = "[]";
        JsonParser parser;
        try {
            parser = jsonFactory.createParser(new StringReader(jsonArray));
            List<Map<String, List<Patterns>>> rules = new ArrayList<>();
            String name = "testName";
            boolean withQuotes = false;
            // When & Then
            JsonParseException exception = assertThrows(JsonParseException.class, () -> {
                JsonRuleCompiler.writeRules(rules, name, parser, withQuotes);
            }, "Expected JsonParseException due to empty array");
            assertEquals("Empty arrays are not allowed", exception.getMessage());
        } catch (IOException e) {
            fail("Failed to create JsonParser", e);
        }
    }

    @Test
    void testWriteRules_withExistingRules_shouldAddNewKeyAndExactMatchForTrue() throws IOException {
        // Given
        String jsonArray = "[true]";
        JsonParser parser = jsonFactory.createParser(new StringReader(jsonArray));
        List<Map<String, List<Patterns>>> rules = new ArrayList<>();
        Map<String, List<Patterns>> existingRule = new HashMap<>();
        existingRule.put("existingKey", Collections.singletonList(Patterns.exactMatch("existingValue")));
        rules.add(existingRule);
        String name = "newKey";
        boolean withQuotes = false;
        // Mock Patterns.exactMatch method
        Patterns mockPattern = mock(Patterns.class);
        try (MockedStatic<Patterns> mockedPatterns = mockStatic(Patterns.class)) {
            mockedPatterns.when(() -> Patterns.exactMatch("true")).thenReturn(mockPattern);
            // When
            JsonRuleCompiler.writeRules(rules, name, parser, withQuotes);
            // Then
            assertEquals(1, rules.size(), "Rules size should be 1");
            assertTrue(rules.get(0).get(name).contains(mockPattern), "Rules should contain the exactMatch pattern for true");
        }
    }

    @Test
    void testWriteRules_withValueNumberFloatToken_shouldAddNumericEqualsAndExactMatchForFloat() throws IOException {
        // Given
        String jsonArray = "[123.45]";
        JsonParser parser = jsonFactory.createParser(new StringReader(jsonArray));
        List<Map<String, List<Patterns>>> rules = new ArrayList<>();
        String name = "testName";
        boolean withQuotes = false;
        // Mock Patterns.numericEquals and Patterns.exactMatch methods
        Patterns mockNumericPattern = mock(Patterns.class);
        Patterns mockExactPattern = mock(Patterns.class);
        try (MockedStatic<Patterns> mockedPatterns = mockStatic(Patterns.class)) {
            mockedPatterns.when(() -> Patterns.numericEquals(123.45)).thenReturn(mockNumericPattern);
            mockedPatterns.when(() -> Patterns.exactMatch("123.45")).thenReturn(mockExactPattern);
            // When
            JsonRuleCompiler.writeRules(rules, name, parser, withQuotes);
            // Then
            assertEquals(1, rules.size(), "Rules size should be 1");
            assertTrue(rules.get(0).get(name).contains(mockNumericPattern), "Rules should contain the numericEquals pattern for float");
            assertTrue(rules.get(0).get(name).contains(mockExactPattern), "Rules should contain the exactMatch pattern for float");
        }
    }

    @Test
    void testWriteRules_withValueFalseToken_shouldAddExactMatchForFalse() throws IOException {
        // Given
        String jsonArray = "[false]";
        JsonParser parser = jsonFactory.createParser(new StringReader(jsonArray));
        List<Map<String, List<Patterns>>> rules = new ArrayList<>();
        String name = "testName";
        boolean withQuotes = false;
        // Mock Patterns.exactMatch method
        Patterns mockPattern = mock(Patterns.class);
        try (MockedStatic<Patterns> mockedPatterns = mockStatic(Patterns.class)) {
            mockedPatterns.when(() -> Patterns.exactMatch("false")).thenReturn(mockPattern);
            // When
            JsonRuleCompiler.writeRules(rules, name, parser, withQuotes);
            // Then
            assertEquals(1, rules.size(), "Rules size should be 1");
            assertTrue(rules.get(0).get(name).contains(mockPattern), "Rules should contain the exactMatch pattern for false");
        }
    }

    @Test
    void testWriteRules_withExistingKey_shouldUpdateWithNewExactMatchPattern() throws IOException {
        // Given
        String jsonArray = "[\"newValue\"]";
        JsonParser parser = jsonFactory.createParser(new StringReader(jsonArray));
        List<Map<String, List<Patterns>>> rules = new ArrayList<>();
        Map<String, List<Patterns>> existingRule = new HashMap<>();
        existingRule.put("existingKey", Collections.singletonList(Patterns.exactMatch("existingValue")));
        rules.add(existingRule);
        String name = "existingKey";
        boolean withQuotes = false;
        // Mock Patterns.exactMatch method
        Patterns mockPattern = mock(Patterns.class);
        try (MockedStatic<Patterns> mockedPatterns = mockStatic(Patterns.class)) {
            mockedPatterns.when(() -> Patterns.exactMatch("newValue")).thenReturn(mockPattern);
            // When
            JsonRuleCompiler.writeRules(rules, name, parser, withQuotes);
            // Then
            assertEquals(1, rules.size(), "Rules size should be 1");
            assertTrue(rules.get(0).get(name).contains(mockPattern), "Rules should update the existing key with the new exactMatch pattern");
        }
    }

    @Test
    void testWriteRules_withValueStringAndValueNumberIntTokens_shouldAddPatternsForBoth() throws IOException {
        // Given
        String jsonArray = "[\"text\", 10]";
        JsonParser parser = jsonFactory.createParser(new StringReader(jsonArray));
        List<Map<String, List<Patterns>>> rules = new ArrayList<>();
        String name = "testName";
        boolean withQuotes = false;
        // Mock Patterns.exactMatch and Patterns.numericEquals methods
        Patterns mockStringPattern = mock(Patterns.class);
        Patterns mockNumericPattern = mock(Patterns.class);
        Patterns mockExactPattern = mock(Patterns.class);
        try (MockedStatic<Patterns> mockedPatterns = mockStatic(Patterns.class)) {
            mockedPatterns.when(() -> Patterns.exactMatch("text")).thenReturn(mockStringPattern);
            mockedPatterns.when(() -> Patterns.numericEquals(10.0)).thenReturn(mockNumericPattern);
            mockedPatterns.when(() -> Patterns.exactMatch("10")).thenReturn(mockExactPattern);
            // When
            JsonRuleCompiler.writeRules(rules, name, parser, withQuotes);
            // Then
            assertEquals(1, rules.size(), "Rules size should be 1");
            assertTrue(rules.get(0).get(name).contains(mockStringPattern), "Rules should contain the exactMatch pattern for the string");
            assertTrue(rules.get(0).get(name).contains(mockNumericPattern), "Rules should contain the numericEquals pattern for the number");
            assertTrue(rules.get(0).get(name).contains(mockExactPattern), "Rules should contain the exactMatch pattern for the number");
        }
    }

    @Test
    void testWriteRules_withNestedStartObject_shouldProcessNestedStructure() throws IOException {
        // Given
        String jsonArray = "[{\"nestedKey\":\"nestedString\"}]";
        JsonParser parser = jsonFactory.createParser(new StringReader(jsonArray));
        List<Map<String, List<Patterns>>> rules = new ArrayList<>();
        String name = "testName";
        boolean withQuotes = false;
        // Mock processMatchExpression method
        Patterns mockPattern = mock(Patterns.class);
        try (MockedStatic<JsonRuleCompiler> mockedCompiler = mockStatic(JsonRuleCompiler.class)) {
            mockedCompiler.when(() -> JsonRuleCompiler.processMatchExpression(parser)).thenReturn(mockPattern);
            // When
            JsonRuleCompiler.writeRules(rules, name, parser, withQuotes);
            // Then
            assertEquals(1, rules.size(), "Rules size should be 1");
            assertTrue(rules.get(0).get(name).contains(mockPattern), "Rules should contain the processed pattern from the nested object");
        }
    }

    @Test
    void testWriteRules_withValueNumberIntAndValueStringTokens_shouldAddPatternsForBoth() throws IOException {
        // Given
        String jsonArray = "[123, \"invalid\"]";
        JsonParser parser = jsonFactory.createParser(new StringReader(jsonArray));
        List<Map<String, List<Patterns>>> rules = new ArrayList<>();
        String name = "testName";
        boolean withQuotes = false;
        // Mock Patterns.numericEquals and Patterns.exactMatch methods
        Patterns mockNumericPattern = mock(Patterns.class);
        Patterns mockExactNumericPattern = mock(Patterns.class);
        Patterns mockStringPattern = mock(Patterns.class);
        try (MockedStatic<Patterns> mockedPatterns = mockStatic(Patterns.class)) {
            mockedPatterns.when(() -> Patterns.numericEquals(123.0)).thenReturn(mockNumericPattern);
            mockedPatterns.when(() -> Patterns.exactMatch("123")).thenReturn(mockExactNumericPattern);
            mockedPatterns.when(() -> Patterns.exactMatch("invalid")).thenReturn(mockStringPattern);
            // When
            JsonRuleCompiler.writeRules(rules, name, parser, withQuotes);
            // Then
            assertEquals(1, rules.size(), "Rules size should be 1");
            assertTrue(rules.get(0).get(name).contains(mockNumericPattern), "Rules should contain the numericEquals pattern for the number");
            assertTrue(rules.get(0).get(name).contains(mockExactNumericPattern), "Rules should contain the exactMatch pattern for the number");
            assertTrue(rules.get(0).get(name).contains(mockStringPattern), "Rules should contain the exactMatch pattern for the string");
        }
    }

    @Test
    void testWriteRules_withValueStringAndValueNumberFloatTokens_shouldAddPatternsForBoth() throws IOException {
        // Given
        String jsonArray = "[\"string\", 123.45]";
        JsonParser parser = jsonFactory.createParser(new StringReader(jsonArray));
        List<Map<String, List<Patterns>>> rules = new ArrayList<>();
        String name = "testName";
        boolean withQuotes = true;
        // Mock Patterns.exactMatch and Patterns.numericEquals methods
        Patterns mockQuotedStringPattern = mock(Patterns.class);
        Patterns mockNumericPattern = mock(Patterns.class);
        Patterns mockExactPattern = mock(Patterns.class);
        try (MockedStatic<Patterns> mockedPatterns = mockStatic(Patterns.class)) {
            mockedPatterns.when(() -> Patterns.exactMatch("\"string\"")).thenReturn(mockQuotedStringPattern);
            mockedPatterns.when(() -> Patterns.numericEquals(123.45)).thenReturn(mockNumericPattern);
            mockedPatterns.when(() -> Patterns.exactMatch("123.45")).thenReturn(mockExactPattern);
            // When
            JsonRuleCompiler.writeRules(rules, name, parser, withQuotes);
            // Then
            assertEquals(1, rules.size(), "Rules size should be 1");
            assertTrue(rules.get(0).get(name).contains(mockQuotedStringPattern), "Rules should contain the quoted exactMatch pattern for the string");
            assertTrue(rules.get(0).get(name).contains(mockNumericPattern), "Rules should contain the numericEquals pattern for the float");
            assertTrue(rules.get(0).get(name).contains(mockExactPattern), "Rules should contain the exactMatch pattern for the float");
        }
    }

    @Test
    void testWriteRules_withValueNullTrueFalseTokens_shouldAddExactMatchPatterns() throws IOException {
        // Given
        String jsonArray = "[null, true, false]";
        JsonParser parser = jsonFactory.createParser(new StringReader(jsonArray));
        List<Map<String, List<Patterns>>> rules = new ArrayList<>();
        String name = "testName";
        boolean withQuotes = false;
        // Mock Patterns.exactMatch method
        Patterns mockNullPattern = mock(Patterns.class);
        Patterns mockTruePattern = mock(Patterns.class);
        Patterns mockFalsePattern = mock(Patterns.class);
        try (MockedStatic<Patterns> mockedPatterns = mockStatic(Patterns.class)) {
            mockedPatterns.when(() -> Patterns.exactMatch("null")).thenReturn(mockNullPattern);
            mockedPatterns.when(() -> Patterns.exactMatch("true")).thenReturn(mockTruePattern);
            mockedPatterns.when(() -> Patterns.exactMatch("false")).thenReturn(mockFalsePattern);
            // When
            JsonRuleCompiler.writeRules(rules, name, parser, withQuotes);
            // Then
            assertEquals(1, rules.size(), "Rules size should be 1");
            assertTrue(rules.get(0).get(name).contains(mockNullPattern), "Rules should contain the exactMatch pattern for null");
            assertTrue(rules.get(0).get(name).contains(mockTruePattern), "Rules should contain the exactMatch pattern for true");
            assertTrue(rules.get(0).get(name).contains(mockFalsePattern), "Rules should contain the exactMatch pattern for false");
        }
    }

    @Test
    void testWriteRules_withUnknownToken_shouldThrowJsonParseException() {
        // Given
        String jsonArray = "[unknown]";
        JsonParser parser;
        try {
            parser = jsonFactory.createParser(new StringReader(jsonArray));
            List<Map<String, List<Patterns>>> rules = new ArrayList<>();
            String name = "testName";
            boolean withQuotes = false;
            // When & Then
            JsonParseException exception = assertThrows(JsonParseException.class, () -> {
                JsonRuleCompiler.writeRules(rules, name, parser, withQuotes);
            }, "Expected JsonParseException due to unknown token");
            assertEquals("Match value must be String, number, true, false, or null", exception.getMessage());
        } catch (IOException e) {
            fail("Failed to create JsonParser", e);
        }
    }
}
```

Corresponding compilation feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
src/test/java/software/amazon/event/ruler/JsonRuleCompiler_writeRules_Test.java:90: error: writeRules(List<Map<String,List<Patterns>>>,String,JsonParser,boolean) has private access in JsonRuleCompiler
            JsonRuleCompiler.writeRules(rules, name, parser, withQuotes);
                            ^
src/test/java/software/amazon/event/ruler/JsonRuleCompiler_writeRules_Test.java:110: error: writeRules(List<Map<String,List<Patterns>>>,String,JsonParser,boolean) has private access in JsonRuleCompiler
            JsonRuleCompiler.writeRules(rules, name, parser, withQuotes);
                            ^
src/test/java/software/amazon/event/ruler/JsonRuleCompiler_writeRules_Test.java:132: error: writeRules(List<Map<String,List<Patterns>>>,String,JsonParser,boolean) has private access in JsonRuleCompiler
            JsonRuleCompiler.writeRules(rules, name, parser, withQuotes);
                            ^
src/test/java/software/amazon/event/ruler/JsonRuleCompiler_writeRules_Test.java:153: error: writeRules(List<Map<String,List<Patterns>>>,String,JsonParser,boolean) has private access in JsonRuleCompiler
            JsonRuleCompiler.writeRules(rules, name, parser, withQuotes);
                            ^
src/test/java/software/amazon/event/ruler/JsonRuleCompiler_writeRules_Test.java:172: error: writeRules(List<Map<String,List<Patterns>>>,String,JsonParser,boolean) has private access in JsonRuleCompiler
                JsonRuleCompiler.writeRules(rules, name, parser, withQuotes);
                                ^
src/test/java/software/amazon/event/ruler/JsonRuleCompiler_writeRules_Test.java:196: error: writeRules(List<Map<String,List<Patterns>>>,String,JsonParser,boolean) has private access in JsonRuleCompiler
            JsonRuleCompiler.writeRules(rules, name, parser, withQuotes);
                            ^
src/test/java/software/amazon/event/ruler/JsonRuleCompiler_writeRules_Test.java:218: error: writeRules(List<Map<String,List<Patterns>>>,String,JsonParser,boolean) has private access in JsonRuleCompiler
            JsonRuleCompiler.writeRules(rules, name, parser, withQuotes);
                            ^
src/test/java/software/amazon/event/ruler/JsonRuleCompiler_writeRules_Test.java:239: error: writeRules(List<Map<String,List<Patterns>>>,String,JsonParser,boolean) has private access in JsonRuleCompiler
            JsonRuleCompiler.writeRules(rules, name, parser, withQuotes);
                            ^
src/test/java/software/amazon/event/ruler/JsonRuleCompiler_writeRules_Test.java:262: error: writeRules(List<Map<String,List<Patterns>>>,String,JsonParser,boolean) has private access in JsonRuleCompiler
            JsonRuleCompiler.writeRules(rules, name, parser, withQuotes);
                            ^
src/test/java/software/amazon/event/ruler/JsonRuleCompiler_writeRules_Test.java:286: error: writeRules(List<Map<String,List<Patterns>>>,String,JsonParser,boolean) has private access in JsonRuleCompiler
            JsonRuleCompiler.writeRules(rules, name, parser, withQuotes);
                            ^
src/test/java/software/amazon/event/ruler/JsonRuleCompiler_writeRules_Test.java:306: error: processMatchExpression(JsonParser) has private access in JsonRuleCompiler
            mockedCompiler.when(() -> JsonRuleCompiler.processMatchExpression(parser)).thenReturn(mockPattern);
                                                      ^
src/test/java/software/amazon/event/ruler/JsonRuleCompiler_writeRules_Test.java:308: error: writeRules(List<Map<String,List<Patterns>>>,String,JsonParser,boolean) has private access in JsonRuleCompiler
            JsonRuleCompiler.writeRules(rules, name, parser, withQuotes);
                            ^
src/test/java/software/amazon/event/ruler/JsonRuleCompiler_writeRules_Test.java:332: error: writeRules(List<Map<String,List<Patterns>>>,String,JsonParser,boolean) has private access in JsonRuleCompiler
            JsonRuleCompiler.writeRules(rules, name, parser, withQuotes);
                            ^
src/test/java/software/amazon/event/ruler/JsonRuleCompiler_writeRules_Test.java:358: error: writeRules(List<Map<String,List<Patterns>>>,String,JsonParser,boolean) has private access in JsonRuleCompiler
            JsonRuleCompiler.writeRules(rules, name, parser, withQuotes);
                            ^
src/test/java/software/amazon/event/ruler/JsonRuleCompiler_writeRules_Test.java:384: error: writeRules(List<Map<String,List<Patterns>>>,String,JsonParser,boolean) has private access in JsonRuleCompiler
            JsonRuleCompiler.writeRules(rules, name, parser, withQuotes);
                            ^
src/test/java/software/amazon/event/ruler/JsonRuleCompiler_writeRules_Test.java:405: error: writeRules(List<Map<String,List<Patterns>>>,String,JsonParser,boolean) has private access in JsonRuleCompiler
                JsonRuleCompiler.writeRules(rules, name, parser, withQuotes);
                                ^
16 errors

```

And you can use the following related information to assist you in your analysis and correction:
```
{'api document of class software.amazon.event.ruler.JsonRuleCompiler': '\n * Represents a updated compiler comparing to RuleCompiler class, it parses a rule described by a JSON string into\n * a list of Map which is composed of field Patterns, each Map represents one dedicated match branch in the rule.\n * By default, fields in the rule will be interpreted as "And" relationship implicitly and will result into one Map with\n * fields of Patterns, but once it comes across the "$or" relationship which is explicitly decorated by "$or" primitive\n * array, for each object in "$or" array, this Compiler will fork a new Map to include all fields of patterns in\n * that "or" branch, and when it goes over all branches in "$or" relationship, it will end up with the list of Map\n * and each Map represents a match branch composed of fields of patterns.\n * i.e.: a rule which describe the effect of ("source" AND ("metricName" OR ("metricType AND "namespace") OR "scope"))\n * its JSON format String is:\n *   {\n *     "source": [ "aws.cloudwatch" ],\n *     "$or": [\n *       { "metricName": [ "CPUUtilization", "ReadLatency" ] },\n *       {\n *         "metricType": [ "MetricType" ] ,\n *         "namespace": [ "AWS/EC2", "AWS/ES" ]\n *       },\n *       { "scope": [ "Service" ] }\n *     ]\n *   }\n * It will be parsed to a list of match branches composed by a Map of fields patterns, e.g. for above rule, there will\n * result 3 match branches:\n * [\n *   {source=[\\"aws.cloudwatch\\"], metricName=[\\"CPUUtilization\\",\\"ReadLatency\\"]},\n *   {namespace=[\\"AWS/EC2\\", \\"AWS/ES\\"], metricType=[\\"MetricType\\"], source=[\\"aws.cloudwatch\\"]},\n *   {source=[\\"aws.cloudwatch\\"], scope=[\\"Service\\"]}\n * ]\n ', 'dependent classes': 'class `software.amazon.event.ruler.RuleCompiler`:\n\trelated functions:\n\t\tmethod `private static void writeRules(Map<String, List<Patterns>>, String, JsonParser, boolean)` returns `void`, related with `software.amazon.event.ruler.JsonRuleCompiler.writeRules(List<Map<String, List<Patterns>>>, String, JsonParser, boolean)`;\n\t\tmethod `private static Patterns processAnythingButMatchExpression(JsonParser, JsonToken)` returns `software.amazon.event.ruler.Patterns`, related with `software.amazon.event.ruler.JsonRuleCompiler.writeRules(List<Map<String, List<Patterns>>>, String, JsonParser, boolean)`;\n\t\tmethod `private static Patterns processAnythingButListMatchExpression(JsonParser)` returns `software.amazon.event.ruler.Patterns`, related with `software.amazon.event.ruler.JsonRuleCompiler.writeRules(List<Map<String, List<Patterns>>>, String, JsonParser, boolean)`;\nclass `software.amazon.event.ruler.RuleCompiler.ListBasedRuleCompiler`:\n\trelated functions:\n\t\tmethod `private static void writeRules(Map<List<String>, List<Patterns>>, List<String>, JsonParser, boolean)` returns `void`, related with `software.amazon.event.ruler.JsonRuleCompiler.writeRules(List<Map<String, List<Patterns>>>, String, JsonParser, boolean)`;\nclass `software.amazon.event.ruler.JsonRuleCompiler`:\n\trelated functions:\n\t\tmethod `private static Patterns processAnythingButEqualsIgnoreCaseListMatchExpression(JsonParser)` returns `software.amazon.event.ruler.Patterns`, related with `software.amazon.event.ruler.JsonRuleCompiler.writeRules(List<Map<String, List<Patterns>>>, String, JsonParser, boolean)`;\n\t\tmethod `private static Patterns completeNumericRange(JsonParser, JsonToken, double, boolean)` returns `software.amazon.event.ruler.Patterns`, related with `software.amazon.event.ruler.JsonRuleCompiler.writeRules(List<Map<String, List<Patterns>>>, String, JsonParser, boolean)`;\n\t\tmethod `private static Patterns processAnythingButMatchExpression(JsonParser, JsonToken)` returns `software.amazon.event.ruler.Patterns`, related with `software.amazon.event.ruler.JsonRuleCompiler.writeRules(List<Map<String, List<Patterns>>>, String, JsonParser, boolean)`;\n\t\tmethod `private static Patterns processAnythingButListMatchExpression(JsonParser)` returns `software.amazon.event.ruler.Patterns`, related with `software.amazon.event.ruler.JsonRuleCompiler.writeRules(List<Map<String, List<Patterns>>>, String, JsonParser, boolean)`;\n\t\tmethod `private static void parseIntoOrRelationship(List<Map<String, List<Patterns>>>, Path, JsonParser, boolean)` returns `void`, related with `software.amazon.event.ruler.JsonRuleCompiler.writeRules(List<Map<String, List<Patterns>>>, String, JsonParser, boolean)`, api document: \n     * This function is to parse the "$or" relationship described as an array, each object in the array will be\n     * interpreted as "$or" relationship, for example:\n     * {\n     *     "detail": {\n     *         "$or" : [\n     *             {"c-count": [ { "numeric": [ ">", 0, "<=", 5 ] } ]},\n     *             {"d-count": [ { "numeric": [ "<", 10 ] } ]},\n     *             {"x-limit": [ { "numeric": [ "=", 3.018e2 ] } ]}\n     *         ]\n     *     }\n     * }\n     * above rule will be interpreted as or effect: ("detail.c-count" || "detail.d-count" || detail.x-limit"),\n     * The result will be described as the list of Map, each Map represents a sub rule composed of fields of patterns in\n     * that or condition path, for example:\n     * [\n     *   {detail.c-count=[38D7EA4C68000/38D7EA512CB40:true/false]},\n     *   {detail.d-count=[0000000000000/38D7EA55F1680:false/true]},\n     *   {detail.x-limit=[VP:38D7EB6C39A40]}\n     * ]\n     * This List will be added into Ruler with the same rule name to demonstrate the "or" effects inside Ruler state\n     * machine.\n     ;'}
```

**Note**: If the method-to-test in the @Test function is a private method, you can use **reflection** to call it.

Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 


