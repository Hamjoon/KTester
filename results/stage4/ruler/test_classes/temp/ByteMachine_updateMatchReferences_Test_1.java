package software.amazon.event.ruler;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

@ExtendWith(MockitoExtension.class)
class ByteMachine_updateMatchReferences_Test {

    private ByteMachine byteMachine;

    private Patterns mockPattern;

    private NameState mockNameState;

    private ByteMatch byteMatch;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the ByteMachine instance and mocks before each test
        byteMachine = new ByteMachine();
        mockPattern = mock(Patterns.class);
        mockNameState = mock(NameState.class);
        byteMatch = new ByteMatch(mockPattern, mockNameState);
    }

    private int getFieldValue(ByteMachine byteMachine, String fieldName) {
        try {
            Field field = ByteMachine.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            return ((AtomicInteger) field.get(byteMachine)).get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void incrementFieldValue(ByteMachine byteMachine, String fieldName) {
        try {
            Field field = ByteMachine.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            ((AtomicInteger) field.get(byteMachine)).incrementAndGet();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Helper method to invoke the private method using reflection
    private void invokeUpdateMatchReferences(ByteMachine byteMachine, ByteMatch byteMatch) {
        try {
            Method method = ByteMachine.class.getDeclaredMethod("updateMatchReferences", ByteMatch.class);
            method.setAccessible(true);
            method.invoke(byteMachine, byteMatch);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testUpdateMatchReferencesWithExactPattern() {
        // Given
        when(mockPattern.type()).thenReturn(MatchType.EXACT);
        // When
        invokeUpdateMatchReferences(byteMachine, byteMatch);
        // Then
        // Verify that no state changes occurred for EXACT pattern
        assertEquals(0, getFieldValue(byteMachine, "hasSuffix"));
        assertEquals(0, getFieldValue(byteMachine, "hasNumeric"));
        assertEquals(0, getFieldValue(byteMachine, "hasIP"));
    }

    @Test
    void testUpdateMatchReferencesWithSuffixPattern() {
        // Given
        when(mockPattern.type()).thenReturn(MatchType.SUFFIX);
        // Set initial state
        incrementFieldValue(byteMachine, "hasSuffix");
        // When
        invokeUpdateMatchReferences(byteMachine, byteMatch);
        // Then
        assertEquals(0, getFieldValue(byteMachine, "hasSuffix"));
    }

    @Test
    void testUpdateMatchReferencesWithNumericRangePattern() {
        // Given
        Range mockRange = mock(Range.class);
        when(mockPattern.type()).thenReturn(MatchType.NUMERIC_RANGE);
        when(mockRange.isCIDR()).thenReturn(false);
        when(mockPattern).thenReturn(mockRange);
        // When
        invokeUpdateMatchReferences(byteMachine, byteMatch);
        // Then
        // Verify that hasNumeric is decremented
        // You can assert the internal state of ByteMachine after the operation
    }

    @Test
    void testUpdateMatchReferencesWithNumericEqPattern() {
        // Given
        when(mockPattern.type()).thenReturn(MatchType.NUMERIC_EQ);
        // Set initial state
        incrementFieldValue(byteMachine, "hasNumeric");
        // When
        invokeUpdateMatchReferences(byteMachine, byteMatch);
        // Then
        assertEquals(0, getFieldValue(byteMachine, "hasNumeric"));
    }

    @Test
    void testUpdateMatchReferencesWithNumericRangePatternNotCIDR() {
        // Given
        Range mockRange = mock(Range.class);
        when(mockPattern.type()).thenReturn(MatchType.NUMERIC_RANGE);
        when(mockRange.isCIDR()).thenReturn(false);
        when(mockPattern).thenReturn(mockRange);
        // Set initial state
        incrementFieldValue(byteMachine, "hasNumeric");
        // When
        invokeUpdateMatchReferences(byteMachine, byteMatch);
        // Then
        assertEquals(0, getFieldValue(byteMachine, "hasNumeric"));
    }

    @Test
    void testUpdateMatchReferencesWithNumericRangePatternCIDR() {
        // Given
        Range mockRange = mock(Range.class);
        when(mockPattern.type()).thenReturn(MatchType.NUMERIC_RANGE);
        when(mockRange.isCIDR()).thenReturn(true);
        when(mockPattern).thenReturn(mockRange);
        // Set initial state
        incrementFieldValue(byteMachine, "hasIP");
        // When
        invokeUpdateMatchReferences(byteMachine, byteMatch);
        // Then
        assertEquals(0, getFieldValue(byteMachine, "hasIP"));
    }

    @Test
    void testUpdateMatchReferencesWithAnythingButPatternNumeric() {
        // Given
        when(mockPattern.type()).thenReturn(MatchType.ANYTHING_BUT);
        when(mockPattern.isNumeric()).thenReturn(true);
        // Set initial state
        incrementFieldValue(byteMachine, "hasNumeric");
        byteMachine.anythingButs.add(mockNameState);
        // When
        invokeUpdateMatchReferences(byteMachine, byteMatch);
        // Then
        assertEquals(0, getFieldValue(byteMachine, "hasNumeric"));
        assertFalse(byteMachine.anythingButs.contains(mockNameState));
    }

    @Test
    void testUpdateMatchReferencesWithAnythingButIgnoreCasePattern() {
        // Given
        when(mockPattern.type()).thenReturn(MatchType.ANYTHING_BUT_IGNORE_CASE);
        // Set initial state
        byteMachine.anythingButs.add(mockNameState);
        // When
        invokeUpdateMatchReferences(byteMachine, byteMatch);
        // Then
        assertFalse(byteMachine.anythingButs.contains(mockNameState));
    }

    @Test
    void testUpdateMatchReferencesWithAnythingButSuffixPattern() {
        // Given
        when(mockPattern.type()).thenReturn(MatchType.ANYTHING_BUT_SUFFIX);
        // Set initial state
        incrementFieldValue(byteMachine, "hasSuffix");
        byteMachine.anythingButs.add(mockNameState);
        // When
        invokeUpdateMatchReferences(byteMachine, byteMatch);
        // Then
        assertEquals(0, getFieldValue(byteMachine, "hasSuffix"));
        assertFalse(byteMachine.anythingButs.contains(mockNameState));
    }

    @Test
    void testUpdateMatchReferencesWithAnythingButPrefixPattern() {
        // Given
        when(mockPattern.type()).thenReturn(MatchType.ANYTHING_BUT_PREFIX);
        // Set initial state
        byteMachine.anythingButs.add(mockNameState);
        // When
        invokeUpdateMatchReferences(byteMachine, byteMatch);
        // Then
        assertFalse(byteMachine.anythingButs.contains(mockNameState));
    }

    @Test
    void testUpdateMatchReferencesWithUnknownPattern() {
        // Given
        // Assuming UNKNOWN is the correct value for unknown pattern
        when(mockPattern.type()).thenReturn(MatchType.UNKNOWN);
        // When & Then
        assertThrows(AssertionError.class, () -> invokeUpdateMatchReferences(byteMachine, byteMatch));
    }
}
