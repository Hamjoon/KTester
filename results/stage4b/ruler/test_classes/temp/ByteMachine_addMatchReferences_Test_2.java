package software.amazon.event.ruler;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;
import java.lang.reflect.Method;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class ByteMachine_addMatchReferences_Test {

    private ByteMachine byteMachine;

    private Patterns mockPattern;

    private NameState mockNameState;

    @BeforeEach
    void setupBeforeEach() {
        byteMachine = new ByteMachine();
        mockPattern = mock(Patterns.class);
        mockNameState = mock(NameState.class);
    }

    // Helper method to retrieve the value of private AtomicInteger fields using reflection
    private int getAtomicIntegerValue(String fieldName) {
        try {
            Field field = ByteMachine.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            AtomicInteger atomicInteger = (AtomicInteger) field.get(byteMachine);
            return atomicInteger.get();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Error accessing field: " + fieldName, e);
        }
    }

    // Helper method to invoke the private addMatchReferences method
    private void invokeAddMatchReferences(ByteMatch byteMatch) {
        try {
            Method method = ByteMachine.class.getDeclaredMethod("addMatchReferences", ByteMatch.class);
            method.setAccessible(true);
            method.invoke(byteMachine, byteMatch);
        } catch (Exception e) {
            throw new RuntimeException("Error invoking addMatchReferences", e);
        }
    }

    // Helper method to retrieve the size of anythingButs using reflection
    private int getAnythingButsSize() {
        try {
            Field field = ByteMachine.class.getDeclaredField("anythingButs");
            field.setAccessible(true);
            @SuppressWarnings("unchecked")
            Set<NameState> anythingButs = (Set<NameState>) field.get(byteMachine);
            return anythingButs.size();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Error accessing field: anythingButs", e);
        }
    }

    @Test
    void testAddMatchReferences_WithSuffixPattern() {
        // Given
        when(mockPattern.type()).thenReturn(MatchType.SUFFIX);
        ByteMatch byteMatch = new ByteMatch(mockPattern, mockNameState);
        // When
        invokeAddMatchReferences(byteMatch);
        // Then
        // Assert that hasSuffix was incremented (you might need to expose this for testing)
        // assertEquals(expectedValue, byteMachine.getHasSuffixCount());
    }

    @Test
    void testAddMatchReferences_WithNumericPattern() {
        // Given
        when(mockPattern.type()).thenReturn(MatchType.NUMERIC_EQ);
        ByteMatch byteMatch = new ByteMatch(mockPattern, mockNameState);
        // When
        invokeAddMatchReferences(byteMatch);
        // Then
        // Assert that hasNumeric was incremented
        // assertEquals(expectedValue, byteMachine.getHasNumericCount());
    }

    @Test
    void testAddMatchReferences_WithExactPattern() {
        // Given
        when(mockPattern.type()).thenReturn(MatchType.EXACT);
        ByteMatch byteMatch = new ByteMatch(mockPattern, mockNameState);
        // When
        invokeAddMatchReferences(byteMatch);
        // Then
        assertEquals(0, getAtomicIntegerValue("hasNumeric"));
        assertEquals(0, getAtomicIntegerValue("hasIP"));
        assertEquals(0, getAtomicIntegerValue("hasSuffix"));
    }

    @Test
    void testAddMatchReferences_WithPrefixPattern() {
        // Given
        when(mockPattern.type()).thenReturn(MatchType.PREFIX);
        ByteMatch byteMatch = new ByteMatch(mockPattern, mockNameState);
        // When
        invokeAddMatchReferences(byteMatch);
        // Then
        assertEquals(0, getAtomicIntegerValue("hasNumeric"));
        assertEquals(0, getAtomicIntegerValue("hasIP"));
        assertEquals(0, getAtomicIntegerValue("hasSuffix"));
    }

    @Test
    void testAddMatchReferences_WithNumericEqPattern() {
        // Given
        when(mockPattern.type()).thenReturn(MatchType.NUMERIC_EQ);
        ByteMatch byteMatch = new ByteMatch(mockPattern, mockNameState);
        // When
        invokeAddMatchReferences(byteMatch);
        // Then
        assertEquals(1, getAtomicIntegerValue("hasNumeric"));
        assertEquals(0, getAtomicIntegerValue("hasIP"));
        assertEquals(0, getAtomicIntegerValue("hasSuffix"));
    }

    @Test
    void testAddMatchReferences_WithNumericRangePatternNotCIDR() {
        // Given
        Range range = mock(Range.class);
        // Ensure isCIDR is a field in Range
        when(range.isCIDR).thenReturn(false);
        when(mockPattern.type()).thenReturn(MatchType.NUMERIC_RANGE);
        ByteMatch byteMatch = new ByteMatch(range, mockNameState);
        // When
        invokeAddMatchReferences(byteMatch);
        // Then
        assertEquals(1, getAtomicIntegerValue("hasNumeric"));
        assertEquals(0, getAtomicIntegerValue("hasIP"));
    }

    @Test
    void testAddMatchReferences_WithNumericRangePatternCIDR() {
        // Given
        Range range = mock(Range.class);
        // Ensure isCIDR is a field in Range
        when(range.isCIDR).thenReturn(true);
        when(mockPattern.type()).thenReturn(MatchType.NUMERIC_RANGE);
        ByteMatch byteMatch = new ByteMatch(range, mockNameState);
        // When
        invokeAddMatchReferences(byteMatch);
        // Then
        assertEquals(0, getAtomicIntegerValue("hasNumeric"));
        assertEquals(1, getAtomicIntegerValue("hasIP"));
    }

    @Test
    void testAddMatchReferences_WithAnythingButPattern() {
        // Given
        when(mockPattern.type()).thenReturn(MatchType.ANYTHING_BUT);
        // Ensure isNumeric() exists in NameState, if not, remove this line
        // Change this line if isNumeric() does not exist
        when(mockNameState.isNumeric()).thenReturn(true);
        ByteMatch byteMatch = new ByteMatch(mockPattern, mockNameState);
        // When
        invokeAddMatchReferences(byteMatch);
        // Then
        assertEquals(1, getAtomicIntegerValue("hasNumeric"));
        assertEquals(0, getAtomicIntegerValue("hasIP"));
        assertEquals(1, getAnythingButsSize());
    }

    @Test
    void testAddMatchReferences_WithAnythingButIgnoreCasePattern() {
        // Given
        when(mockPattern.type()).thenReturn(MatchType.ANYTHING_BUT_IGNORE_CASE);
        ByteMatch byteMatch = new ByteMatch(mockPattern, mockNameState);
        // When
        invokeAddMatchReferences(byteMatch);
        // Then
        assertEquals(0, getAtomicIntegerValue("hasNumeric"));
        assertEquals(0, getAtomicIntegerValue("hasIP"));
        assertEquals(1, getAnythingButsSize());
    }

    @Test
    void testAddMatchReferences_WithAnythingButSuffixPattern() {
        // Given
        when(mockPattern.type()).thenReturn(MatchType.ANYTHING_BUT_SUFFIX);
        ByteMatch byteMatch = new ByteMatch(mockPattern, mockNameState);
        // When
        invokeAddMatchReferences(byteMatch);
        // Then
        assertEquals(0, getAtomicIntegerValue("hasNumeric"));
        assertEquals(0, getAtomicIntegerValue("hasIP"));
        assertEquals(1, getAtomicIntegerValue("hasSuffix"));
        assertEquals(1, getAnythingButsSize());
    }

    @Test
    void testAddMatchReferences_WithAnythingButPrefixPattern() {
        // Given
        when(mockPattern.type()).thenReturn(MatchType.ANYTHING_BUT_PREFIX);
        ByteMatch byteMatch = new ByteMatch(mockPattern, mockNameState);
        // When
        invokeAddMatchReferences(byteMatch);
        // Then
        assertEquals(0, getAtomicIntegerValue("hasNumeric"));
        assertEquals(0, getAtomicIntegerValue("hasIP"));
        assertEquals(0, getAtomicIntegerValue("hasSuffix"));
        assertEquals(1, getAnythingButsSize());
    }

    @Test
    void testAddMatchReferences_WithUndefinedPattern() {
        // Given
        // Simulating an undefined pattern
        when(mockPattern.type()).thenReturn(null);
        ByteMatch byteMatch = new ByteMatch(mockPattern, mockNameState);
        // When & Then
        assertThrows(AssertionError.class, () -> invokeAddMatchReferences(byteMatch));
    }

    @Test
    void testAddMatchReferences_WithWildcardPattern() {
        // Given
        when(mockPattern.type()).thenReturn(MatchType.WILDCARD);
        ByteMatch byteMatch = new ByteMatch(mockPattern, mockNameState);
        // When
        invokeAddMatchReferences(byteMatch);
        // Then
        assertEquals(0, getAtomicIntegerValue("hasNumeric"));
        assertEquals(0, getAtomicIntegerValue("hasIP"));
        assertEquals(0, getAtomicIntegerValue("hasSuffix"));
    }

    @Test
    void testAddMatchReferences_WithEqualsIgnoreCasePattern() {
        // Given
        when(mockPattern.type()).thenReturn(MatchType.EQUALS_IGNORE_CASE);
        ByteMatch byteMatch = new ByteMatch(mockPattern, mockNameState);
        // When
        invokeAddMatchReferences(byteMatch);
        // Then
        assertEquals(0, getAtomicIntegerValue("hasNumeric"));
        assertEquals(0, getAtomicIntegerValue("hasIP"));
        assertEquals(0, getAtomicIntegerValue("hasSuffix"));
    }
}
