package software.amazon.event.ruler;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class ByteMachine_updateMatchReferences_Test {

    private ByteMachine byteMachine;

    private Patterns mockPattern;

    private NameState mockNameState;

    private ByteMatch byteMatch;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the ByteMachine and mock dependencies
        byteMachine = new ByteMachine();
        mockPattern = mock(Patterns.class);
        mockNameState = mock(NameState.class);
        byteMatch = new ByteMatch(mockPattern, mockNameState);
    }

    // Helper method to invoke the private method using reflection
    private void invokeUpdateMatchReferences(ByteMatch match) {
        try {
            Method method = ByteMachine.class.getDeclaredMethod("updateMatchReferences", ByteMatch.class);
            method.setAccessible(true);
            method.invoke(byteMachine, match);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testUpdateMatchReferences_withExactPattern() {
        when(mockPattern.type()).thenReturn(MatchType.EXACT);
        // Call the method under test
        invokeUpdateMatchReferences(byteMatch);
        // Assert expected behavior: no change in hasNumeric, hasIP, hasSuffix
        assertEquals(0, byteMachine.getHasNumeric().get());
        assertEquals(0, byteMachine.getHasIP().get());
        assertEquals(0, byteMachine.getHasSuffix().get());
    }

    @Test
    void testUpdateMatchReferences_withSuffixPattern() {
        when(mockPattern.type()).thenReturn(MatchType.SUFFIX);
        // Call the method under test
        invokeUpdateMatchReferences(byteMatch);
        // Assert expected behavior: hasSuffix decremented by 1
        assertEquals(-1, byteMachine.getHasSuffix().get());
    }

    @Test
    void testUpdateMatchReferences_withNumericRangePattern() {
        when(mockPattern.type()).thenReturn(MatchType.NUMERIC_RANGE);
        Range mockRange = mock(Range.class);
        // Assuming the correct method to get range is getRange()
        when(mockPattern.getRange()).thenReturn(mockRange);
        when(mockRange.isCIDR()).thenReturn(false);
        // Call the method under test
        invokeUpdateMatchReferences(byteMatch);
        // Add assertions based on expected state of byteMachine
    }

    @Test
    void testUpdateMatchReferences_withAnythingButPattern() {
        when(mockPattern.type()).thenReturn(MatchType.ANYTHING_BUT);
        // Call the method under test
        invokeUpdateMatchReferences(byteMatch);
        // Add assertions based on expected state of byteMachine
    }

    @Test
    void testUpdateMatchReferences_withNumericEqualPattern() {
        when(mockPattern.type()).thenReturn(MatchType.NUMERIC_EQ);
        // Call the method under test
        invokeUpdateMatchReferences(byteMatch);
        // Assert expected behavior: hasNumeric decremented by 1
        assertEquals(-1, byteMachine.getHasNumeric().get());
    }

    @Test
    void testUpdateMatchReferences_withNumericRangePatternCIDR() {
        when(mockPattern.type()).thenReturn(MatchType.NUMERIC_RANGE);
        Range mockRange = mock(Range.class);
        when(mockPattern.getRange()).thenReturn(mockRange);
        when(mockRange.isCIDR()).thenReturn(true);
        // Call the method under test
        invokeUpdateMatchReferences(byteMatch);
        // Assert expected behavior: hasIP decremented by 1
        assertEquals(-1, byteMachine.getHasIP().get());
    }

    @Test
    void testUpdateMatchReferences_withNumericRangePatternNotCIDR() {
        when(mockPattern.type()).thenReturn(MatchType.NUMERIC_RANGE);
        Range mockRange = mock(Range.class);
        when(mockPattern.getRange()).thenReturn(mockRange);
        when(mockRange.isCIDR()).thenReturn(false);
        // Call the method under test
        invokeUpdateMatchReferences(byteMatch);
        // Assert expected behavior: hasNumeric decremented by 1
        assertEquals(-1, byteMachine.getHasNumeric().get());
    }

    @Test
    void testUpdateMatchReferences_withAnythingButPatternNumeric() {
        when(mockPattern.type()).thenReturn(MatchType.ANYTHING_BUT);
        when(mockPattern.isNumeric()).thenReturn(true);
        when(byteMatch.getNextNameState()).thenReturn(mockNameState);
        // Call the method under test
        invokeUpdateMatchReferences(byteMatch);
        // Assert expected behavior: hasNumeric decremented by 1 and nextNameState removed from anythingButs
        assertEquals(-1, byteMachine.getHasNumeric().get());
        assertFalse(byteMachine.getAnythingButs().contains(mockNameState));
    }

    @Test
    void testUpdateMatchReferences_withAnythingButPatternNonNumeric() {
        when(mockPattern.type()).thenReturn(MatchType.ANYTHING_BUT);
        when(mockPattern.isNumeric()).thenReturn(false);
        when(byteMatch.getNextNameState()).thenReturn(mockNameState);
        // Call the method under test
        invokeUpdateMatchReferences(byteMatch);
        // Assert expected behavior: nextNameState removed from anythingButs
        assertFalse(byteMachine.getAnythingButs().contains(mockNameState));
    }

    @Test
    void testUpdateMatchReferences_withAnythingButIgnoreCasePattern() {
        when(mockPattern.type()).thenReturn(MatchType.ANYTHING_BUT_IGNORE_CASE);
        when(byteMatch.getNextNameState()).thenReturn(mockNameState);
        // Call the method under test
        invokeUpdateMatchReferences(byteMatch);
        // Assert expected behavior: nextNameState removed from anythingButs
        assertFalse(byteMachine.getAnythingButs().contains(mockNameState));
    }

    @Test
    void testUpdateMatchReferences_withAnythingButSuffixPattern() {
        when(mockPattern.type()).thenReturn(MatchType.ANYTHING_BUT_SUFFIX);
        when(byteMatch.getNextNameState()).thenReturn(mockNameState);
        // Call the method under test
        invokeUpdateMatchReferences(byteMatch);
        // Assert expected behavior: hasSuffix decremented by 1 and nextNameState removed from anythingButs
        assertEquals(-1, byteMachine.getHasSuffix().get());
        assertFalse(byteMachine.getAnythingButs().contains(mockNameState));
    }

    @Test
    void testUpdateMatchReferences_withAnythingButPrefixPattern() {
        when(mockPattern.type()).thenReturn(MatchType.ANYTHING_BUT_PREFIX);
        when(byteMatch.getNextNameState()).thenReturn(mockNameState);
        // Call the method under test
        invokeUpdateMatchReferences(byteMatch);
        // Assert expected behavior: nextNameState removed from anythingButs
        assertFalse(byteMachine.getAnythingButs().contains(mockNameState));
    }

    @Test
    void testUpdateMatchReferences_withUnknownPattern() {
        // Assuming UNKNOWN is not a valid MatchType
        // Adjust this to a valid type if necessary
        // or any valid type
        when(mockPattern.type()).thenReturn(MatchType.EXISTS);
        // Call the method under test and assert that it throws an AssertionError
        assertThrows(AssertionError.class, () -> invokeUpdateMatchReferences(byteMatch));
    }
}
