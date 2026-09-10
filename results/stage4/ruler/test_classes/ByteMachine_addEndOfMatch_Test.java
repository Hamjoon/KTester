package software.amazon.event.ruler;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.event.ruler.input.InputCharacter;

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
        byteMachine = new ByteMachine();
        byteState = new ByteState();
        prevByteState = new ByteState();
        characters = new InputCharacter[] { mock(InputCharacter.class) };
        pattern = new Patterns(MatchType.EXACT);
        nameStateCandidate = new NameState();
    }

    private NameState invokeAddEndOfMatch(ByteMachine byteMachine, ByteState state, ByteState prevState, InputCharacter[] characters, int charIndex, Patterns pattern, NameState nameStateCandidate) {
        try {
            java.lang.reflect.Method method = ByteMachine.class.getDeclaredMethod("addEndOfMatch", ByteState.class, ByteState.class, InputCharacter[].class, int.class, Patterns.class, NameState.class);
            method.setAccessible(true);
            return (NameState) method.invoke(byteMachine, state, prevState, characters, charIndex, pattern, nameStateCandidate);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//     @Test
//     void testAddEndOfMatch_withSingleWildcard() {
//         characters = new InputCharacter[] { mock(InputCharacter.class) };
//         // Changed to getChar()
//         when(characters[0].getChar()).thenReturn('*');
//         pattern = new Patterns(MatchType.EXACT);
//         NameState result = invokeAddEndOfMatch(byteMachine, byteState, prevByteState, characters, 0, pattern, null);
//         assertNotNull(result);
//     }

//     @Test
//     void testAddEndOfMatch_withExactMatch() {
//         characters = new InputCharacter[] { mock(InputCharacter.class), mock(InputCharacter.class) };
//         // Changed to getChar()
//         when(characters[0].getChar()).thenReturn('a');
//         // Changed to getChar()
//         when(characters[1].getChar()).thenReturn('b');
//         pattern = new Patterns(MatchType.EXACT);
//         NameState result = invokeAddEndOfMatch(byteMachine, byteState, prevByteState, characters, 0, pattern, nameStateCandidate);
//         assertNotNull(result);
//     }

//     @Test
//     void testAddEndOfMatch_withLastCharacterWildcard() {
//         characters = new InputCharacter[] { mock(InputCharacter.class), mock(InputCharacter.class) };
//         // Changed to getChar()
//         when(characters[0].getChar()).thenReturn('c');
//         // Changed to getChar()
//         when(characters[1].getChar()).thenReturn('*');
//         pattern = new Patterns(MatchType.EXISTS);
//         NameState result = invokeAddEndOfMatch(byteMachine, byteState, prevByteState, characters, 0, pattern, nameStateCandidate);
//         assertNotNull(result);
//     }

//     @Test
//     void testAddEndOfMatch_withMultipleCharacters() {
//         characters = new InputCharacter[] { mock(InputCharacter.class), mock(InputCharacter.class) };
//         // Changed to getChar()
//         when(characters[0].getChar()).thenReturn('d');
//         // Changed to getChar()
//         when(characters[1].getChar()).thenReturn('e');
//         pattern = new Patterns(MatchType.EXACT);
//         NameState result = invokeAddEndOfMatch(byteMachine, byteState, prevByteState, characters, 0, pattern, nameStateCandidate);
//         assertNotNull(result);
//     }

    @Test
    void testAddEndOfMatch_withEmptyCharacters() {
        characters = new InputCharacter[] {};
        pattern = new Patterns(MatchType.EXISTS);
        NameState result = invokeAddEndOfMatch(byteMachine, byteState, prevByteState, characters, 0, pattern, nameStateCandidate);
        assertNotNull(result);
    }

//     @Test
//     void testAddEndOfMatch_withInvalidPattern() {
//         characters = new InputCharacter[] { mock(InputCharacter.class) };
//         // Changed to getChar()
//         when(characters[0].getChar()).thenReturn('f');
//         pattern = null;
//         assertThrows(NullPointerException.class, () -> {
//             invokeAddEndOfMatch(byteMachine, byteState, prevByteState, characters, 0, pattern, nameStateCandidate);
//         });
//     }

//     @Test
//     void testAddEndOfMatch_withInvalidCharIndex() {
//         characters = new InputCharacter[] { mock(InputCharacter.class) };
//         // Changed to getChar()
//         when(characters[0].getChar()).thenReturn('g');
//         pattern = new Patterns(MatchType.EXACT);
//         int invalidCharIndex = 1;
//         assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
//             invokeAddEndOfMatch(byteMachine, byteState, prevByteState, characters, invalidCharIndex, pattern, nameStateCandidate);
//         });
//     }

//     @Test
//     void testAddEndOfMatch_withNullPrevState() {
//         characters = new InputCharacter[] { mock(InputCharacter.class) };
//         // Changed to getChar()
//         when(characters[0].getChar()).thenReturn('h');
//         pattern = new Patterns(MatchType.EXACT);
//         prevByteState = null;
//         assertThrows(NullPointerException.class, () -> {
//             invokeAddEndOfMatch(byteMachine, byteState, prevByteState, characters, 0, pattern, nameStateCandidate);
//         });
//     }
}