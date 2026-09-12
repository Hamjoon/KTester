package software.amazon.event.ruler;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

// Add other imports if necessary
@ExtendWith(MockitoExtension.class)
class ByteMachine_updateMatchReferences_Test {

    private ByteMachine byteMachine;

    private ByteMatch byteMatch;

    private Patterns pattern;

    private NameState nextNameState;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the ByteMachine and its dependencies before each test
        byteMachine = new ByteMachine();
        pattern = mock(Patterns.class);
        nextNameState = mock(NameState.class);
        byteMatch = new ByteMatch(pattern, nextNameState);
    }

    @Test
    void testUpdateMatchReferences_withSuffixPattern() {
        // Given
        when(pattern.type()).thenReturn(MatchType.SUFFIX);
        // When
        byteMachine.updateMatchReferences(byteMatch);
        // Then
        // Verify that hasSuffix is decremented
        assertEquals(-1, byteMachine.hasSuffix.get());
    }

    @Test
    void testUpdateMatchReferences_withNumericEqPattern() {
        // Given
        when(pattern.type()).thenReturn(MatchType.NUMERIC_EQ);
        // When
        byteMachine.updateMatchReferences(byteMatch);
        // Then
        // Verify that hasNumeric is decremented
        assertEquals(-1, byteMachine.hasNumeric.get());
    }

    @Test
    void testUpdateMatchReferences_withAnythingButPattern() {
        // Given
        when(pattern.type()).thenReturn(MatchType.ANYTHING_BUT);
        when(pattern).thenReturn(mock(AnythingBut.class));
        when(((AnythingBut) pattern).isNumeric()).thenReturn(true);
        // When
        byteMachine.updateMatchReferences(byteMatch);
        // Then
        // Verify that hasNumeric is decremented and anythingButs set is updated
        assertEquals(-1, byteMachine.hasNumeric.get());
        assertFalse(byteMachine.anythingButs.contains(nextNameState));
    }

    @Test
    void testUpdateMatchReferences_withNumericRangePattern_withCIDR() {
        // Given
        when(pattern.type()).thenReturn(MatchType.NUMERIC_RANGE);
        Range range = mock(Range.class);
        when(range.isCIDR()).thenReturn(true);
        when(pattern).thenReturn(range);
        // When
        byteMachine.updateMatchReferences(byteMatch);
        // Then
        // Verify that hasIP is decremented
        assertEquals(-1, byteMachine.hasIP.get());
    }

    @Test
    void testUpdateMatchReferences_withNumericRangePattern_withoutCIDR() {
        // Given
        when(pattern.type()).thenReturn(MatchType.NUMERIC_RANGE);
        Range range = mock(Range.class);
        when(range.isCIDR()).thenReturn(false);
        when(pattern).thenReturn(range);
        // When
        byteMachine.updateMatchReferences(byteMatch);
        // Then
        // Verify that hasNumeric is decremented
        assertEquals(-1, byteMachine.hasNumeric.get());
    }

    @Test
    void testUpdateMatchReferences_withAnythingButPattern_withNumeric() {
        // Given
        when(pattern.type()).thenReturn(MatchType.ANYTHING_BUT);
        AnythingBut anythingBut = mock(AnythingBut.class);
        when(anythingBut.isNumeric()).thenReturn(true);
        when(pattern).thenReturn(anythingBut);
        // When
        byteMachine.updateMatchReferences(byteMatch);
        // Then
        // Verify that hasNumeric is decremented and anythingButs set is updated
        assertEquals(-1, byteMachine.hasNumeric.get());
        assertFalse(byteMachine.anythingButs.contains(nextNameState));
    }

    @Test
    void testUpdateMatchReferences_withAnythingButIgnoreCasePattern() {
        // Given
        when(pattern.type()).thenReturn(MatchType.ANYTHING_BUT_IGNORE_CASE);
        // When
        byteMachine.updateMatchReferences(byteMatch);
        // Then
        // Verify that anythingButs set is updated
        assertFalse(byteMachine.anythingButs.contains(nextNameState));
    }

    @Test
    void testUpdateMatchReferences_withAnythingButSuffixPattern() {
        // Given
        when(pattern.type()).thenReturn(MatchType.ANYTHING_BUT_SUFFIX);
        // When
        byteMachine.updateMatchReferences(byteMatch);
        // Then
        // Verify that hasSuffix is decremented and anythingButs set is updated
        assertEquals(-1, byteMachine.hasSuffix.get());
        assertFalse(byteMachine.anythingButs.contains(nextNameState));
    }

    @Test
    void testUpdateMatchReferences_withAnythingButPrefixPattern() {
        // Given
        when(pattern.type()).thenReturn(MatchType.ANYTHING_BUT_PREFIX);
        // When
        byteMachine.updateMatchReferences(byteMatch);
        // Then
        // Verify that anythingButs set is updated
        assertFalse(byteMachine.anythingButs.contains(nextNameState));
    }

    @Test
    void testUpdateMatchReferences_withUnknownPattern() {
        // Given
        when(pattern.type()).thenReturn(null);
        // When / Then
        // Verify that an AssertionError is thrown
        assertThrows(AssertionError.class, () -> byteMachine.updateMatchReferences(byteMatch));
    }

    @Test
    void testUpdateMatchReferences_withNoChangePatterns() {
        // Given
        MatchType[] noChangeTypes = { MatchType.EXACT, MatchType.PREFIX, MatchType.EXISTS, MatchType.EQUALS_IGNORE_CASE, MatchType.WILDCARD };
        for (MatchType type : noChangeTypes) {
            // Reset state before each iteration
            setupBeforeEach();
            // When
            when(pattern.type()).thenReturn(type);
            byteMachine.updateMatchReferences(byteMatch);
            // Then
            // Verify that no state has changed
            assertEquals(0, byteMachine.hasSuffix.get());
            assertEquals(0, byteMachine.hasNumeric.get());
            assertEquals(0, byteMachine.hasIP.get());
            assertTrue(byteMachine.anythingButs.isEmpty());
        }
    }
}
