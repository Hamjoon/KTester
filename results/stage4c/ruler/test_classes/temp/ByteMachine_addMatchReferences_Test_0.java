package software.amazon.event.ruler;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

// Add other imports if necessary
@ExtendWith(MockitoExtension.class)
class ByteMachine_addMatchReferences_Test {

    private ByteMachine byteMachine;

    private ByteMatch byteMatchMock;

    private Patterns patternMock;

    private NameState nameStateMock;

    @BeforeEach
    void setupBeforeEach() {
        byteMachine = new ByteMachine();
        patternMock = mock(Patterns.class);
        nameStateMock = mock(NameState.class);
        byteMatchMock = mock(ByteMatch.class);
        when(byteMatchMock.getPattern()).thenReturn(patternMock);
        when(byteMatchMock.getNextNameState()).thenReturn(nameStateMock);
    }

    @Test
    void testAddMatchReferences_withExactPattern() {
        // Given
        when(patternMock.type()).thenReturn(MatchType.EXACT);
        // When
        byteMachine.addMatchReferences(byteMatchMock);
        // Then
        // Verify no changes to atomic integers or anythingButs set
        assertEquals(0, byteMachine.hasNumeric.get());
        assertEquals(0, byteMachine.hasIP.get());
        assertEquals(0, byteMachine.hasSuffix.get());
        assertTrue(byteMachine.anythingButs.isEmpty());
    }

    @Test
    void testAddMatchReferences_withSuffixPattern() {
        // Given
        when(patternMock.type()).thenReturn(MatchType.SUFFIX);
        // When
        byteMachine.addMatchReferences(byteMatchMock);
        // Then
        assertEquals(1, byteMachine.hasSuffix.get());
    }

    @Test
    void testAddMatchReferences_withNumericEqPattern() {
        // Given
        when(patternMock.type()).thenReturn(MatchType.NUMERIC_EQ);
        // When
        byteMachine.addMatchReferences(byteMatchMock);
        // Then
        assertEquals(1, byteMachine.hasNumeric.get());
    }

    @Test
    void testAddMatchReferences_withNumericRangePattern() {
        // Given
        Range rangeMock = mock(Range.class);
        when(patternMock.type()).thenReturn(MatchType.NUMERIC_RANGE);
        when(rangeMock.isCIDR).thenReturn(false);
        when(byteMatchMock.getPattern()).thenReturn(rangeMock);
        // When
        byteMachine.addMatchReferences(byteMatchMock);
        // Then
        assertEquals(1, byteMachine.hasNumeric.get());
    }

    @Test
    void testAddMatchReferences_withAnythingButPattern() {
        // Given
        AnythingBut anythingButMock = mock(AnythingBut.class);
        when(patternMock.type()).thenReturn(MatchType.ANYTHING_BUT);
        when(anythingButMock.isNumeric()).thenReturn(true);
        when(byteMatchMock.getPattern()).thenReturn(anythingButMock);
        // When
        byteMachine.addMatchReferences(byteMatchMock);
        // Then
        assertEquals(1, byteMachine.hasNumeric.get());
        assertTrue(byteMachine.anythingButs.contains(nameStateMock));
    }

    @Test
    void testAddMatchReferences_withNumericRangePattern_CIDRTrue() {
        // Given
        Range rangeMock = mock(Range.class);
        when(patternMock.type()).thenReturn(MatchType.NUMERIC_RANGE);
        when(rangeMock.isCIDR).thenReturn(true);
        when(byteMatchMock.getPattern()).thenReturn(rangeMock);
        // When
        byteMachine.addMatchReferences(byteMatchMock);
        // Then
        assertEquals(1, byteMachine.hasIP.get());
    }

    @Test
    void testAddMatchReferences_withNumericRangePattern_CIDRFalse() {
        // Given
        Range rangeMock = mock(Range.class);
        when(patternMock.type()).thenReturn(MatchType.NUMERIC_RANGE);
        when(rangeMock.isCIDR).thenReturn(false);
        when(byteMatchMock.getPattern()).thenReturn(rangeMock);
        // When
        byteMachine.addMatchReferences(byteMatchMock);
        // Then
        assertEquals(1, byteMachine.hasNumeric.get());
    }

    @Test
    void testAddMatchReferences_withAnythingButPattern_NumericTrue() {
        // Given
        AnythingBut anythingButMock = mock(AnythingBut.class);
        when(patternMock.type()).thenReturn(MatchType.ANYTHING_BUT);
        when(anythingButMock.isNumeric()).thenReturn(true);
        when(byteMatchMock.getPattern()).thenReturn(anythingButMock);
        // When
        byteMachine.addMatchReferences(byteMatchMock);
        // Then
        assertEquals(1, byteMachine.hasNumeric.get());
        assertTrue(byteMachine.anythingButs.contains(nameStateMock));
    }

    @Test
    void testAddMatchReferences_withAnythingButPattern_NumericFalse() {
        // Given
        AnythingBut anythingButMock = mock(AnythingBut.class);
        when(patternMock.type()).thenReturn(MatchType.ANYTHING_BUT);
        when(anythingButMock.isNumeric()).thenReturn(false);
        when(byteMatchMock.getPattern()).thenReturn(anythingButMock);
        // When
        byteMachine.addMatchReferences(byteMatchMock);
        // Then
        assertEquals(0, byteMachine.hasNumeric.get());
        assertTrue(byteMachine.anythingButs.contains(nameStateMock));
    }

    @Test
    void testAddMatchReferences_withAnythingButIgnoreCasePattern() {
        // Given
        when(patternMock.type()).thenReturn(MatchType.ANYTHING_BUT_IGNORE_CASE);
        // When
        byteMachine.addMatchReferences(byteMatchMock);
        // Then
        assertTrue(byteMachine.anythingButs.contains(nameStateMock));
    }

    @Test
    void testAddMatchReferences_withAnythingButSuffixPattern() {
        // Given
        when(patternMock.type()).thenReturn(MatchType.ANYTHING_BUT_SUFFIX);
        // When
        byteMachine.addMatchReferences(byteMatchMock);
        // Then
        assertEquals(1, byteMachine.hasSuffix.get());
        assertTrue(byteMachine.anythingButs.contains(nameStateMock));
    }

    @Test
    void testAddMatchReferences_withAnythingButPrefixPattern() {
        // Given
        when(patternMock.type()).thenReturn(MatchType.ANYTHING_BUT_PREFIX);
        // When
        byteMachine.addMatchReferences(byteMatchMock);
        // Then
        assertTrue(byteMachine.anythingButs.contains(nameStateMock));
    }

    @Test
    void testAddMatchReferences_withUndefinedPattern() {
        // Given
        when(patternMock.type()).thenReturn(null);
        // When & Then
        assertThrows(AssertionError.class, () -> {
            byteMachine.addMatchReferences(byteMatchMock);
        });
    }

    @Test
    void testAddMatchReferences_withPrefixPattern() {
        // Given
        when(patternMock.type()).thenReturn(MatchType.PREFIX);
        // When
        byteMachine.addMatchReferences(byteMatchMock);
        // Then
        // Verify no changes to atomic integers or anythingButs set
        assertEquals(0, byteMachine.hasNumeric.get());
        assertEquals(0, byteMachine.hasIP.get());
        assertEquals(0, byteMachine.hasSuffix.get());
        assertTrue(byteMachine.anythingButs.isEmpty());
    }

    @Test
    void testAddMatchReferences_withExistsPattern() {
        // Given
        when(patternMock.type()).thenReturn(MatchType.EXISTS);
        // When
        byteMachine.addMatchReferences(byteMatchMock);
        // Then
        // Verify no changes to atomic integers or anythingButs set
        assertEquals(0, byteMachine.hasNumeric.get());
        assertEquals(0, byteMachine.hasIP.get());
        assertEquals(0, byteMachine.hasSuffix.get());
        assertTrue(byteMachine.anythingButs.isEmpty());
    }

    @Test
    void testAddMatchReferences_withEqualsIgnoreCasePattern() {
        // Given
        when(patternMock.type()).thenReturn(MatchType.EQUALS_IGNORE_CASE);
        // When
        byteMachine.addMatchReferences(byteMatchMock);
        // Then
        // Verify no changes to atomic integers or anythingButs set
        assertEquals(0, byteMachine.hasNumeric.get());
        assertEquals(0, byteMachine.hasIP.get());
        assertEquals(0, byteMachine.hasSuffix.get());
        assertTrue(byteMachine.anythingButs.isEmpty());
    }

    @Test
    void testAddMatchReferences_withWildcardPattern() {
        // Given
        when(patternMock.type()).thenReturn(MatchType.WILDCARD);
        // When
        byteMachine.addMatchReferences(byteMatchMock);
        // Then
        // Verify no changes to atomic integers or anythingButs set
        assertEquals(0, byteMachine.hasNumeric.get());
        assertEquals(0, byteMachine.hasIP.get());
        assertEquals(0, byteMachine.hasSuffix.get());
        assertTrue(byteMachine.anythingButs.isEmpty());
    }
}
