package software.amazon.event.ruler;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ByteMachine_addEndOfMatch_Test {

    private ByteMachine byteMachine;

    private ByteState byteState;

    private ByteState prevByteState;

    private InputCharacter[] characters;

    private Patterns pattern;

    private NameState nameStateCandidate;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the ByteMachine and other dependencies before each test
        byteMachine = new ByteMachine();
        byteState = new ByteState();
        prevByteState = new ByteState();
        // Mock instances
        characters = new InputCharacter[] { mock(InputCharacter.class) };
        pattern = new Patterns(MatchType.EXACT);
        nameStateCandidate = new NameState();
    }

    private NameState invokeAddEndOfMatch(ByteMachine byteMachine, ByteState state, ByteState prevState, InputCharacter[] characters, int charIndex, Patterns pattern, NameState nameStateCandidate) {
        try {
            // Use reflection to invoke the private method
            java.lang.reflect.Method method = ByteMachine.class.getDeclaredMethod("addEndOfMatch", ByteState.class, ByteState.class, InputCharacter[].class, int.class, Patterns.class, NameState.class);
            method.setAccessible(true);
            return (NameState) method.invoke(byteMachine, state, prevState, characters, charIndex, pattern, nameStateCandidate);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testAddEndOfMatch_withSingleWildcard() {
        // Given
        // Mock wildcard
        characters = new InputCharacter[] { mock(InputCharacter.class) };
        // Assuming getValue() method exists
        when(characters[0].getValue()).thenReturn('*');
        pattern = new Patterns(MatchType.EXACT);
        // When
        NameState result = invokeAddEndOfMatch(byteMachine, byteState, prevByteState, characters, 0, pattern, null);
        // Then
        assertNotNull(result);
    }

    @Test
    void testAddEndOfMatch_withExactMatch() {
        // Given
        // Mock specific characters
        characters = new InputCharacter[] { mock(InputCharacter.class), mock(InputCharacter.class) };
        when(characters[0].getValue()).thenReturn('a');
        when(characters[1].getValue()).thenReturn('b');
        pattern = new Patterns(MatchType.EXACT);
        // When
        NameState result = invokeAddEndOfMatch(byteMachine, byteState, prevByteState, characters, 0, pattern, nameStateCandidate);
        // Then
        assertNotNull(result);
    }

    @Test
    void testAddEndOfMatch_withLastCharacterWildcard() {
        // Given
        // Mock characters
        characters = new InputCharacter[] { mock(InputCharacter.class), mock(InputCharacter.class) };
        when(characters[0].getValue()).thenReturn('c');
        // Mock wildcard
        when(characters[1].getValue()).thenReturn('*');
        pattern = new Patterns(MatchType.EXISTS);
        // When
        NameState result = invokeAddEndOfMatch(byteMachine, byteState, prevByteState, characters, 0, pattern, nameStateCandidate);
        // Then
        assertNotNull(result);
    }

    @Test
    void testAddEndOfMatch_withMultipleCharacters() {
        // Given
        // Mock multiple characters
        characters = new InputCharacter[] { mock(InputCharacter.class), mock(InputCharacter.class) };
        when(characters[0].getValue()).thenReturn('d');
        when(characters[1].getValue()).thenReturn('e');
        pattern = new Patterns(MatchType.EXACT);
        // When
        NameState result = invokeAddEndOfMatch(byteMachine, byteState, prevByteState, characters, 0, pattern, nameStateCandidate);
        // Then
        assertNotNull(result);
    }

    @Test
    void testAddEndOfMatch_withEmptyCharacters() {
        // Given
        // No characters provided
        characters = new InputCharacter[] {};
        pattern = new Patterns(MatchType.EXISTS);
        // When
        NameState result = invokeAddEndOfMatch(byteMachine, byteState, prevByteState, characters, 0, pattern, nameStateCandidate);
        // Then
        assertNotNull(result);
    }

    @Test
    void testAddEndOfMatch_withInvalidPattern() {
        // Given
        // Mock character
        characters = new InputCharacter[] { mock(InputCharacter.class) };
        when(characters[0].getValue()).thenReturn('f');
        // Invalid pattern
        pattern = null;
        // When & Then
        assertThrows(NullPointerException.class, () -> {
            invokeAddEndOfMatch(byteMachine, byteState, prevByteState, characters, 0, pattern, nameStateCandidate);
        });
    }

    @Test
    void testAddEndOfMatch_withInvalidCharIndex() {
        // Given
        // Mock character
        characters = new InputCharacter[] { mock(InputCharacter.class) };
        when(characters[0].getValue()).thenReturn('g');
        pattern = new Patterns(MatchType.EXACT);
        // Invalid index
        int invalidCharIndex = 1;
        // When & Then
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            invokeAddEndOfMatch(byteMachine, byteState, prevByteState, characters, invalidCharIndex, pattern, nameStateCandidate);
        });
    }

    @Test
    void testAddEndOfMatch_withNullPrevState() {
        // Given
        // Mock character
        characters = new InputCharacter[] { mock(InputCharacter.class) };
        when(characters[0].getValue()).thenReturn('h');
        pattern = new Patterns(MatchType.EXACT);
        // Null previous state
        prevByteState = null;
        // When & Then
        assertThrows(NullPointerException.class, () -> {
            invokeAddEndOfMatch(byteMachine, byteState, prevByteState, characters, 0, pattern, nameStateCandidate);
        });
    }
}
