package software.amazon.event.ruler;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource; // Fixed missing semicolon
import java.util.List;
import java.lang.reflect.Method;
import org.junit.jupiter.params.ParameterizedTest;

// Add other imports if necessary
@ExtendWith(MockitoExtension.class)
class Ruler_matches_Test {

    private ObjectMapper objectMapper;

    private JsonNode testJsonNode;

    private Patterns testPattern;

    @BeforeEach
    void setupBeforeEach() {
        objectMapper = new ObjectMapper();
        // Initialize a sample JsonNode for testing
        // Example JSON
        String jsonString = "{\"key\":\"value\"}";
        try {
            testJsonNode = objectMapper.readTree(jsonString);
        } catch (Exception e) {
            fail("Failed to create JsonNode from string: " + e.getMessage());
        }
        // Initialize a sample Patterns object for testing (example: ValuePatterns)
        // Use the appropriate MatchType
        testPattern = new Patterns(MatchType.EXACT);
    }

    private static Stream<Arguments> exactPatternTestCases() {
        return Stream.of(
            Arguments.of(createJsonNode("\"test\""), new ValuePatterns(MatchType.EXACT, "\"test\""), true),
            Arguments.of(createJsonNode("\"test\""), new ValuePatterns(MatchType.EXACT, "\"not_test\""), false)
        );
    }

    private static Stream<Arguments> prefixPatternTestCases() {
        return Stream.of(
            Arguments.of(createJsonNode("\"testValue\""), new ValuePatterns(MatchType.PREFIX, "\"test\""), true),
            Arguments.of(createJsonNode("\"valueTest\""), new ValuePatterns(MatchType.PREFIX, "\"test\""), false)
        );
    }

    private static Stream<Arguments> anythingButPatternTestCases() {
        return Stream.of(
            Arguments.of(createJsonNode("\"test\""), new AnythingBut(MatchType.ANYTHING_BUT, List.of("\"test\"", "\"example\"")), false),
            Arguments.of(createJsonNode("\"not_test\""), new AnythingBut(MatchType.ANYTHING_BUT, List.of("\"test\"", "\"example\"")), true),
            Arguments.of(createJsonNode("\"example\""), new AnythingBut(MatchType.ANYTHING_BUT, List.of("\"test\"", "\"example\"")), false)
        );
    }

    private static Stream<Arguments> existsAndAbsentPatternTestCases() {
        return Stream.of(
            Arguments.of(createJsonNode("\"any_value\""), new Patterns(MatchType.EXISTS), true),
            Arguments.of(createJsonNode("\"any_value\""), new Patterns(MatchType.ABSENT), false)
        );
    }

    private static Stream<Arguments> numericEqPatternTestCases() {
        return Stream.of(
            Arguments.of(createJsonNode("42"), new ValuePatterns(MatchType.NUMERIC_EQ, 42), true),
            Arguments.of(createJsonNode("42"), new ValuePatterns(MatchType.NUMERIC_EQ, 43), false)
        );
    }

    private boolean invokeMatches(JsonNode val, Patterns pattern) {
        try {
            // Using reflection to access the private method
            Method method = Ruler.class.getDeclaredMethod("matches", JsonNode.class, Patterns.class);
            method.setAccessible(true);
            return (boolean) method.invoke(null, val, pattern);
        } catch (Exception e) {
            throw new RuntimeException("Error invoking matches method", e);
        }
    }

    private static JsonNode createJsonNode(String value) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readTree(value);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create JsonNode from string: " + e.getMessage());
        }
    }

    @Test
    void testMatchesExactPattern() {
        // Given
        // The expected value to match against
        String expectedValue = "\"value\"";
        testPattern = new ValuePatterns(MatchType.EXACT, expectedValue);
        // When
        boolean result = invokeMatches(testJsonNode, testPattern);
        // Then
        assertTrue(result, "The match should be successful for the exact pattern.");
    }

    @ParameterizedTest
    @MethodSource("exactPatternTestCases")
    void testExactPattern(JsonNode inputValue, Patterns pattern, boolean expected) {
        // When
        boolean result = invokeMatches(inputValue, pattern);
        // Then
        assertEquals(expected, result, "The match result should be as expected for the exact pattern.");
    }

    @ParameterizedTest
    @MethodSource("prefixPatternTestCases")
    void testPrefixPattern(JsonNode inputValue, Patterns pattern, boolean expected) {
        // When
        boolean result = invokeMatches(inputValue, pattern);
        // Then
        assertEquals(expected, result, "The match result should be as expected for the prefix pattern.");
    }

    @ParameterizedTest
    @MethodSource("anythingButPatternTestCases")
    void testAnythingButPattern(JsonNode inputValue, Patterns pattern, boolean expected) {
        // When
        boolean result = invokeMatches(inputValue, pattern);
        // Then
        assertEquals(expected, result, "The match result should be as expected for the ANYTHING_BUT pattern.");
    }

    @ParameterizedTest
    @MethodSource("existsAndAbsentPatternTestCases")
    void testExistsAndAbsentPattern(JsonNode inputValue, Patterns pattern, boolean expected) {
        // When
        boolean result = invokeMatches(inputValue, pattern);
        // Then
        assertEquals(expected, result, "The match result should be as expected for EXISTS and ABSENT patterns.");
    }

    @ParameterizedTest
    @MethodSource("numericEqPatternTestCases")
    void testNumericEqPattern(JsonNode inputValue, Patterns pattern, boolean expected) {
        // When
        boolean result = invokeMatches(inputValue, pattern);
        // Then
        assertEquals(expected, result, "The match result should be as expected for NUMERIC_EQ patterns.");
    }

    @Test
    void testUnsupportedPatternType() {
        // Given
        JsonNode inputValue = createJsonNode("\"test\"");
        Patterns pattern = new Patterns(MatchType.UNKNOWN_PATTERN_TYPE);
        // When / Then
        assertThrows(RuntimeException.class, () -> invokeMatches(inputValue, pattern), "Should throw RuntimeException for unsupported pattern type.");
    }
}