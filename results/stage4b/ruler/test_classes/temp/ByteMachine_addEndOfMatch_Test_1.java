package software.amazon.event.ruler;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.lang.reflect.Method;
// Correct import for Field
import java.lang.reflect.Field;
// Assuming this is the concrete subclass of InputCharacter
import software.amazon.event.ruler.input.ConcreteInputCharacter;

@ExtendWith(MockitoExtension.class)
class ByteMachine_addEndOfMatch_Test {

    private ByteMachine byteMachine;

    private ByteState state;

    private ByteState prevState;

    private InputCharacter[] characters;

    private Patterns pattern;

    private NameState nameStateCandidate;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the ByteMachine and other dependencies before each test
        byteMachine = new ByteMachine();
        state = new ByteState();
        prevState = new ByteState();
        // Initialize with test characters as needed
        characters = new InputCharacter[] {};
        pattern = new Patterns(MatchType.EXISTS);
        nameStateCandidate = null;
    }

    // Helper method to invoke the private method addEndOfMatch using reflection
    private NameState invokeAddEndOfMatch(ByteState state, ByteState prevState, InputCharacter[] characters, int charIndex, Patterns pattern, NameState nameStateCandidate) {
        try {
            Method method = ByteMachine.class.getDeclaredMethod("addEndOfMatch", ByteState.class, ByteState.class, InputCharacter[].class, int.class, Patterns.class, NameState.class);
            method.setAccessible(true);
            return (NameState) method.invoke(byteMachine, state, prevState, characters, charIndex, pattern, nameStateCandidate);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Helper method to get the startStateMatch for verification
    private ByteMatch getStartStateMatch() {
        try {
            Field field = ByteMachine.class.getDeclaredField("startStateMatch");
            field.setAccessible(true);
            return (ByteMatch) field.get(byteMachine);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testAddEndOfMatch_withWildcardCharacter() {
        // Use concrete subclass
        characters = new InputCharacter[] { new ConcreteInputCharacter('*') };
        pattern = new Patterns(MatchType.EXACT);
        NameState result = invokeAddEndOfMatch(state, null, characters, 0, pattern, nameStateCandidate);
        assertNotNull(result);
        assertNotNull(getStartStateMatch());
    }

    @Test
    void testAddEndOfMatch_withExactMatch() {
        // Use concrete subclass
        characters = new InputCharacter[] { new ConcreteInputCharacter('a'), new ConcreteInputCharacter('b') };
        nameStateCandidate = new NameState();
        NameState result = invokeAddEndOfMatch(state, prevState, characters, 0, pattern, nameStateCandidate);
        assertNotNull(result);
    }

    @Test
    void testAddEndOfMatch_withShortcutTransition() {
        // Use concrete subclass
        characters = new InputCharacter[] { new ConcreteInputCharacter('a'), new ConcreteInputCharacter('b') };
        pattern = new Patterns(MatchType.EXACT);
        NameState result = invokeAddEndOfMatch(state, prevState, characters, 0, pattern, nameStateCandidate);
        assertNotNull(result);
    }

    @Test
    void testAddEndOfMatch_withNonMatchHandling() {
        // Use concrete subclass
        characters = new InputCharacter[] { new ConcreteInputCharacter('x'), new ConcreteInputCharacter('y') };
        pattern = new Patterns(MatchType.ANYTHING_BUT_SUFFIX);
        NameState result = invokeAddEndOfMatch(state, prevState, characters, 0, pattern, nameStateCandidate);
        assertNotNull(result);
    }

    @Test
    void testAddEndOfMatch_withInvalidStateHandling() {
        state = null;
        // Use concrete subclass
        characters = new InputCharacter[] { new ConcreteInputCharacter('a') };
        pattern = new Patterns(MatchType.EXACT);
        assertThrows(NullPointerException.class, () -> {
            invokeAddEndOfMatch(state, prevState, characters, 0, pattern, nameStateCandidate);
        });
    }

    @Test
    void testAddEndOfMatch_withInvalidCharactersHandling() {
        // Empty array
        characters = new InputCharacter[] {};
        pattern = new Patterns(MatchType.EXACT);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            invokeAddEndOfMatch(state, prevState, characters, 0, pattern, nameStateCandidate);
        });
    }

    @Test
    void testAddEndOfMatch_withInvalidCharIndexHandling() {
        // Use concrete subclass
        characters = new InputCharacter[] { new ConcreteInputCharacter('a') };
        pattern = new Patterns(MatchType.EXACT);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            invokeAddEndOfMatch(state, prevState, characters, 1, pattern, nameStateCandidate);
        });
    }
}
