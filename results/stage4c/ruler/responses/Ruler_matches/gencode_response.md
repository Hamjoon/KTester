```java
package software.amazon.event.ruler;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class Ruler_matches_Test {

    private static ObjectMapper objectMapper;

    @BeforeAll
    static void setupBeforeAll() {
        objectMapper = new ObjectMapper();
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize any necessary mocks or data here before each test
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup resources or reset mocks here after each test
    }

    @AfterAll
    static void teardownAfterAll() {
        // Perform any final cleanup after all tests have run
    }

    @Test
    void testMatchesExact() {
        // Given
        JsonNode val = objectMapper.createObjectNode().put("key", "value");
        Patterns pattern = mock(Patterns.class);
        when(pattern.type()).thenReturn(MatchType.EXACT);
        when(pattern.pattern()).thenReturn("\"value\"");

        // When
        boolean result = invokeMatches(val, pattern);

        // Then
        assertTrue(result);
    }

    @Test
    void testMatchesPrefix() {
        // Given
        JsonNode val = objectMapper.createObjectNode().put("key", "test");
        Patterns pattern = mock(Patterns.class);
        when(pattern.type()).thenReturn(MatchType.PREFIX);
        when(pattern.pattern()).thenReturn("\"te");

        // When
        boolean result = invokeMatches(val, pattern);

        // Then
        assertTrue(result);
    }

    @Test
    void testMatchesAnythingBut() {
        // Given
        JsonNode val = objectMapper.createObjectNode().put("key", "test");
        AnythingBut pattern = mock(AnythingBut.class);
        when(pattern.type()).thenReturn(MatchType.ANYTHING_BUT);
        when(pattern.getValues()).thenReturn(List.of("\"notest\""));

        // When
        boolean result = invokeMatches(val, pattern);

        // Then
        assertTrue(result);
    }

    @Test
    void testMatchesAnythingButIgnoreCase() {
        // Given
        JsonNode val = objectMapper.createObjectNode().put("key", "test");
        AnythingButEqualsIgnoreCase pattern = mock(AnythingButEqualsIgnoreCase.class);
        when(pattern.type()).thenReturn(MatchType.ANYTHING_BUT_IGNORE_CASE);
        when(pattern.getValues()).thenReturn(List.of("\"TEST\""));

        // When
        boolean result = invokeMatches(val, pattern);

        // Then
        assertFalse(result);
    }

    @Test
    void testMatchesAnythingButSuffix() {
        // Given
        JsonNode val = objectMapper.createObjectNode().put("key", "test");
        Patterns pattern = mock(Patterns.class);
        when(pattern.type()).thenReturn(MatchType.ANYTHING_BUT_SUFFIX);
        when(pattern.pattern()).thenReturn("\"est\"");

        // When
        boolean result = invokeMatches(val, pattern);

        // Then
        assertFalse(result);
    }

    @Test
    void testMatchesAnythingButPrefix() {
        // Given
        JsonNode val = objectMapper.createObjectNode().put("key", "test");
        Patterns pattern = mock(Patterns.class);
        when(pattern.type()).thenReturn(MatchType.ANYTHING_BUT_PREFIX);
        when(pattern.pattern()).thenReturn("\"te");

        // When
        boolean result = invokeMatches(val, pattern);

        // Then
        assertFalse(result);
    }

    @Test
    void testMatchesNumericEq() {
        // Given
        JsonNode val = objectMapper.createObjectNode().put("key", 42);
        Patterns pattern = mock(Patterns.class);
        when(pattern.type()).thenReturn(MatchType.NUMERIC_EQ);
        when(pattern.pattern()).thenReturn("42");

        // When
        boolean result = invokeMatches(val, pattern);

        // Then
        assertTrue(result);
    }

    @Test
    void testMatchesEqualsIgnoreCase() {
        // Given
        JsonNode val = objectMapper.createObjectNode().put("key", "test");
        Patterns pattern = mock(Patterns.class);
        when(pattern.type()).thenReturn(MatchType.EQUALS_IGNORE_CASE);
        when(pattern.pattern()).thenReturn("\"TEST\"");

        // When
        boolean result = invokeMatches(val, pattern);

        // Then
        assertTrue(result);
    }

    @Test
    void testMatchesWildcard() {
        // Given
        JsonNode val = objectMapper.createObjectNode().put("key", "test");
        Patterns pattern = mock(Patterns.class);
        when(pattern.type()).thenReturn(MatchType.WILDCARD);
        when(pattern.pattern()).thenReturn("\"t*st\"");

        // When
        boolean result = invokeMatches(val, pattern);

        // Then
        assertTrue(result);
    }

    @Test
    void testMatchesUnsupportedPatternType() {
        // Given
        JsonNode val = objectMapper.createObjectNode().put("key", "test");
        Patterns pattern = mock(Patterns.class);
        when(pattern.type()).thenReturn(null); // Simulate unsupported pattern type

        // When / Then
        assertThrows(RuntimeException.class, () -> invokeMatches(val, pattern));
    }

    @Test
    void testMatchesNumericRange() {
        // Given
        JsonNode val = objectMapper.createObjectNode().put("key", 42);
        Range pattern = mock(Range.class);
        when(pattern.type()).thenReturn(MatchType.NUMERIC_RANGE);
        when(pattern.isCIDR).thenReturn(false);
        when(pattern.bottom).thenReturn("30".getBytes());
        when(pattern.top).thenReturn("50".getBytes());
        when(pattern.openBottom).thenReturn(false);
        when(pattern.openTop).thenReturn(false);

        // When
        boolean result = invokeMatches(val, pattern);

        // Then
        assertTrue(result);
    }

    @Test
    void testMatchesExists() {
        // Given
        JsonNode val = objectMapper.createObjectNode().put("key", "example");
        Patterns pattern = mock(Patterns.class);
        when(pattern.type()).thenReturn(MatchType.EXISTS);

        // When
        boolean result = invokeMatches(val, pattern);

        // Then
        assertTrue(result);
    }

    @Test
    void testMatchesAbsent() {
        // Given
        JsonNode val = objectMapper.createObjectNode().put("key", "example");
        Patterns pattern = mock(Patterns.class);
        when(pattern.type()).thenReturn(MatchType.ABSENT);

        // When
        boolean result = invokeMatches(val, pattern);

        // Then
        assertFalse(result);
    }

    private boolean invokeMatches(JsonNode val, Patterns pattern) {
        try {
            java.lang.reflect.Method method = Ruler.class.getDeclaredMethod("matches", JsonNode.class, Patterns.class);
            method.setAccessible(true);
            return (boolean) method.invoke(null, val, pattern);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
```