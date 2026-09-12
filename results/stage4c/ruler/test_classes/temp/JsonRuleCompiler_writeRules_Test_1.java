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
