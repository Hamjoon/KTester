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
import java.lang.reflect.Field;
import java.lang.reflect.Method;

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

    private void invokeAddMatchReferences(ByteMatch byteMatch) throws Exception {
        Method method = ByteMachine.class.getDeclaredMethod("addMatchReferences", ByteMatch.class);
        method.setAccessible(true);
        method.invoke(byteMachine, byteMatch);
    }

    private AtomicInteger getAtomicIntegerField(String fieldName) throws Exception {
        Field field = ByteMachine.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        return (AtomicInteger) field.get(byteMachine);
    }

    private Set<NameState> getAnythingButs() throws Exception {
        Field field = ByteMachine.class.getDeclaredField("anythingButs");
        field.setAccessible(true);
        return (Set<NameState>) field.get(byteMachine);
    }

    @Test
    void testAddMatchReferences_withExactPattern() {
        // Given
        when(patternMock.type()).thenReturn(MatchType.EXACT);
        // When
        invokeAddMatchReferences(byteMatchMock);
        // Then
        assertEquals(0, getAtomicIntegerField("hasNumeric").get());
        assertEquals(0, getAtomicIntegerField("hasIP").get());
        assertEquals(0, getAtomicIntegerField("hasSuffix").get());
        assertTrue(getAnythingButs().isEmpty());
    }

    @Test
    void testAddMatchReferences_withSuffixPattern() {
        // Given
        when(patternMock.type()).thenReturn(MatchType.SUFFIX);
        // When
        invokeAddMatchReferences(byteMatchMock);
        // Then
        assertEquals(1, getAtomicIntegerField("hasSuffix").get());
    }

    @Test
    void testAddMatchReferences_withNumericEqPattern() {
        // Given
        when(patternMock.type()).thenReturn(MatchType.NUMERIC_EQ);
        // When
        invokeAddMatchReferences(byteMatchMock);
        // Then
        assertEquals(1, getAtomicIntegerField("hasNumeric").get());
    }

    @Test
    void testAddMatchReferences_withNumericRangePattern() {
        // Given
        Range rangeMock = mock(Range.class);
        when(patternMock.type()).thenReturn(MatchType.NUMERIC_RANGE);
        when(rangeMock.isCIDR).thenReturn(false);
        when(byteMatchMock.getPattern()).thenReturn(rangeMock);
        // When
        invokeAddMatchReferences(byteMatchMock);
        // Then
        assertEquals(1, getAtomicIntegerField("hasNumeric").get());
    }

    @Test
    void testAddMatchReferences_withAnythingButPattern() {
        // Given
        AnythingBut anythingButMock = mock(AnythingBut.class);
        when(patternMock.type()).thenReturn(MatchType.ANYTHING_BUT);
        when(anythingButMock.isNumeric()).thenReturn(true);
        when(byteMatchMock.getPattern()).thenReturn(anythingButMock);
        // When
        invokeAddMatchReferences(byteMatchMock);
        // Then
        assertEquals(1, getAtomicIntegerField("hasNumeric").get());
        assertTrue(getAnythingButs().contains(nameStateMock));
    }

    @Test
    void testAddMatchReferences_withNumericRangePattern_CIDRTrue() {
        // Given
        Range rangeMock = mock(Range.class);
        when(patternMock.type()).thenReturn(MatchType.NUMERIC_RANGE);
        when(rangeMock.isCIDR).thenReturn(true);
        when(byteMatchMock.getPattern()).thenReturn(rangeMock);
        // When
        invokeAddMatchReferences(byteMatchMock);
        // Then
        assertEquals(1, getAtomicIntegerField("hasIP").get());
    }

    @Test
    void testAddMatchReferences_withNumericRangePattern_CIDRFalse() {
        // Given
        Range rangeMock = mock(Range.class);
        when(patternMock.type()).thenReturn(MatchType.NUMERIC_RANGE);
        when(rangeMock.isCIDR).thenReturn(false);
        when(byteMatchMock.getPattern()).thenReturn(rangeMock);
        // When
        invokeAddMatchReferences(byteMatchMock);
        // Then
        assertEquals(1, getAtomicIntegerField("hasNumeric").get());
    }

    @Test
    void testAddMatchReferences_withAnythingButPattern_NumericTrue() {
        // Given
        AnythingBut anythingButMock = mock(AnythingBut.class);
        when(patternMock.type()).thenReturn(MatchType.ANYTHING_BUT);
        when(anythingButMock.isNumeric()).thenReturn(true);
        when(byteMatchMock.getPattern()).thenReturn(anythingButMock);
        // When
        invokeAddMatchReferences(byteMatchMock);
        // Then
        assertEquals(1, getAtomicIntegerField("hasNumeric").get());
        assertTrue(getAnythingButs().contains(nameStateMock));
    }

    @Test
    void testAddMatchReferences_withAnythingButPattern_NumericFalse() {
        // Given
        AnythingBut anythingButMock = mock(AnythingBut.class);
        when(patternMock.type()).thenReturn(MatchType.ANYTHING_BUT);
        when(anythingButMock.isNumeric()).thenReturn(false);
        when(byteMatchMock.getPattern()).thenReturn(anythingButMock);
        // When
        invokeAddMatchReferences(byteMatchMock);
        // Then
        assertEquals(0, getAtomicIntegerField("hasNumeric").get());
        assertTrue(getAnythingButs().contains(nameStateMock));
    }

    @Test
    void testAddMatchReferences_withAnythingButIgnoreCasePattern() {
        // Given
        when(patternMock.type()).thenReturn(MatchType.ANYTHING_BUT_IGNORE_CASE);
        // When
        invokeAddMatchReferences(byteMatchMock);
        // Then
        assertTrue(getAnythingButs().contains(nameStateMock));
    }

    @Test
    void testAddMatchReferences_withAnythingButSuffixPattern() {
        // Given
        when(patternMock.type()).thenReturn(MatchType.ANYTHING_BUT_SUFFIX);
        // When
        invokeAddMatchReferences(byteMatchMock);
        // Then
        assertEquals(1, getAtomicIntegerField("hasSuffix").get());
        assertTrue(getAnythingButs().contains(nameStateMock));
    }

    @Test
    void testAddMatchReferences_withAnythingButPrefixPattern() {
        // Given
        when(patternMock.type()).thenReturn(MatchType.ANYTHING_BUT_PREFIX);
        // When
        invokeAddMatchReferences(byteMatchMock);
        // Then
        assertTrue(getAnythingButs().contains(nameStateMock));
    }

    @Test
    void testAddMatchReferences_withUndefinedPattern() {
        // Given
        when(patternMock.type()).thenReturn(null);
        // When & Then
        assertThrows(AssertionError.class, () -> {
            invokeAddMatchReferences(byteMatchMock);
        });
    }

    @Test
    void testAddMatchReferences_withPrefixPattern() {
        // Given
        when(patternMock.type()).thenReturn(MatchType.PREFIX);
        // When
        invokeAddMatchReferences(byteMatchMock);
        // Then
        assertEquals(0, getAtomicIntegerField("hasNumeric").get());
        assertEquals(0, getAtomicIntegerField("hasIP").get());
        assertEquals(0, getAtomicIntegerField("hasSuffix").get());
        assertTrue(getAnythingButs().isEmpty());
    }

    @Test
    void testAddMatchReferences_withExistsPattern() {
        // Given
        when(patternMock.type()).thenReturn(MatchType.EXISTS);
        // When
        invokeAddMatchReferences(byteMatchMock);
        // Then
        assertEquals(0, getAtomicIntegerField("hasNumeric").get());
        assertEquals(0, getAtomicIntegerField("hasIP").get());
        assertEquals(0, getAtomicIntegerField("hasSuffix").get());
        assertTrue(getAnythingButs().isEmpty());
    }

    @Test
    void testAddMatchReferences_withEqualsIgnoreCasePattern() {
        // Given
        when(patternMock.type()).thenReturn(MatchType.EQUALS_IGNORE_CASE);
        // When
        invokeAddMatchReferences(byteMatchMock);
        // Then
        assertEquals(0, getAtomicIntegerField("hasNumeric").get());
        assertEquals(0, getAtomicIntegerField("hasIP").get());
        assertEquals(0, getAtomicIntegerField("hasSuffix").get());
        assertTrue(getAnythingButs().isEmpty());
    }

    @Test
    void testAddMatchReferences_withWildcardPattern() {
        // Given
        when(patternMock.type()).thenReturn(MatchType.WILDCARD);
        // When
        invokeAddMatchReferences(byteMatchMock);
        // Then
        assertEquals(0, getAtomicIntegerField("hasNumeric").get());
        assertEquals(0, getAtomicIntegerField("hasIP").get());
        assertEquals(0, getAtomicIntegerField("hasSuffix").get());
        assertTrue(getAnythingButs().isEmpty());
    }
}
