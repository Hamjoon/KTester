package software.amazon.event.ruler;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.event.ruler.input.InputCharacter;
import software.amazon.event.ruler.input.InputCharacterType;
import software.amazon.event.ruler.Patterns;
import software.amazon.event.ruler.NameState;
import software.amazon.event.ruler.MatchType;
import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class ByteMachine_findMatchPattern_Test {

    private ByteMachine byteMachine;

    private Patterns patterns;

    // Example concrete implementation of InputCharacter for testing purposes
    class ConcreteInputCharacter extends InputCharacter {

        private char value;

        public ConcreteInputCharacter(char value) {
            this.value = value;
        }

        @Override
        public char getValue() {
            return value;
        }

        @Override
        public InputCharacterType getType() {
            // Return a default type, adjust as necessary
            // Assuming DEFAULT is a valid type
            return InputCharacterType.DEFAULT;
        }
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the ByteMachine and Patterns instances before each test
        byteMachine = new ByteMachine();
        // Example initialization, adjust as necessary
        patterns = new Patterns(MatchType.EXISTS);
    }

    private NameState invokeFindMatchPattern(InputCharacter[] characters, Patterns pattern) {
        try {
            Method method = ByteMachine.class.getDeclaredMethod("findMatchPattern", InputCharacter[].class, Patterns.class);
            method.setAccessible(true);
            return (NameState) method.invoke(byteMachine, (Object) characters, pattern);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testFindMatchPatternWithExists() {
        // Given
        InputCharacter[] inputCharacters = { new ConcreteInputCharacter('A') };
        Patterns pattern = new Patterns(MatchType.EXISTS);
        // When
        NameState result = invokeFindMatchPattern(inputCharacters, pattern);
        // Then
        assertNotNull(result);
    }

    @Test
    void testFindMatchPatternWithAnythingBut() {
        // Given
        InputCharacter[] inputCharacters = { new ConcreteInputCharacter('B') };
        Patterns pattern = new Patterns(MatchType.ANYTHING_BUT);
        // When
        NameState result = invokeFindMatchPattern(inputCharacters, pattern);
        // Then
        assertNotNull(result);
    }

    @Test
    void testFindMatchPatternWithAnythingButIgnoreCase() {
        // Given
        InputCharacter[] inputCharacters = { new ConcreteInputCharacter('C') };
        Patterns pattern = new Patterns(MatchType.ANYTHING_BUT_IGNORE_CASE);
        // When
        NameState result = invokeFindMatchPattern(inputCharacters, pattern);
        // Then
        assertNotNull(result);
    }

    @Test
    void testFindMatchPatternWithExact() {
        // Given
        InputCharacter[] inputCharacters = { new ConcreteInputCharacter('A'), new ConcreteInputCharacter('B'), new ConcreteInputCharacter('C') };
        Patterns pattern = new Patterns(MatchType.EXACT);
        // When
        NameState result = invokeFindMatchPattern(inputCharacters, pattern);
        // Then
        assertNotNull(result);
    }

    @Test
    void testFindMatchPatternWithAnythingButSuffix() {
        // Given
        InputCharacter[] inputCharacters = { new ConcreteInputCharacter('A'), new ConcreteInputCharacter('B'), new ConcreteInputCharacter('C') };
        Patterns pattern = new Patterns(MatchType.ANYTHING_BUT_SUFFIX);
        // When
        NameState result = invokeFindMatchPattern(inputCharacters, pattern);
        // Then
        assertNotNull(result);
    }

    @Test
    void testFindMatchPatternWithSuffix() {
        // Given
        InputCharacter[] inputCharacters = { new ConcreteInputCharacter('X'), new ConcreteInputCharacter('Y'), new ConcreteInputCharacter('Z') };
        Patterns pattern = new Patterns(MatchType.SUFFIX);
        // When
        NameState result = invokeFindMatchPattern(inputCharacters, pattern);
        // Then
        assertNull(result);
    }

    @Test
    void testFindMatchPatternWithEmptyCharacters() {
        // Given
        InputCharacter[] inputCharacters = {};
        Patterns pattern = new Patterns(MatchType.EXACT);
        // When
        NameState result = invokeFindMatchPattern(inputCharacters, pattern);
        // Then
        assertNull(result);
    }

    @Test
    void testFindMatchPatternWithSingleCharacterExists() {
        // Given
        InputCharacter[] inputCharacters = { new ConcreteInputCharacter('A') };
        Patterns pattern = new Patterns(MatchType.EXISTS);
        // When
        NameState result = invokeFindMatchPattern(inputCharacters, pattern);
        // Then
        assertNotNull(result);
    }

    @Test
    void testFindMatchPatternWithLongerExactPattern() {
        // Given
        InputCharacter[] inputCharacters = { new ConcreteInputCharacter('A'), new ConcreteInputCharacter('B'), new ConcreteInputCharacter('C'), new ConcreteInputCharacter('D') };
        Patterns pattern = new Patterns(MatchType.EXACT);
        // When
        NameState result = invokeFindMatchPattern(inputCharacters, pattern);
        // Then
        assertNotNull(result);
    }

    @Test
    void testFindMatchPatternWithLongerExactPatternMatch() {
        // Given
        InputCharacter[] inputCharacters = { new ConcreteInputCharacter('A'), new ConcreteInputCharacter('B'), new ConcreteInputCharacter('C'), new ConcreteInputCharacter('D'), new ConcreteInputCharacter('E') };
        Patterns pattern = new Patterns(MatchType.EXACT);
        // When
        NameState result = invokeFindMatchPattern(inputCharacters, pattern);
        // Then
        assertNotNull(result);
    }

    @Test
    void testFindMatchPatternWithSuffixMatch() {
        // Given
        InputCharacter[] inputCharacters = { new ConcreteInputCharacter('D'), new ConcreteInputCharacter('E'), new ConcreteInputCharacter('F') };
        Patterns pattern = new Patterns(MatchType.SUFFIX);
        // When
        NameState result = invokeFindMatchPattern(inputCharacters, pattern);
        // Then
        assertNotNull(result);
    }

    @Test
    void testFindMatchPatternWithNonExistentPattern() {
        // Given
        InputCharacter[] inputCharacters = { new ConcreteInputCharacter('A'), new ConcreteInputCharacter('B'), new ConcreteInputCharacter('C') };
        Patterns pattern = new Patterns(MatchType.EXISTS);
        // When
        NameState result = invokeFindMatchPattern(inputCharacters, pattern);
        // Then
        assertNotNull(result);
    }

    @Test
    void testFindMatchPatternWithNullPattern() {
        // Given
        InputCharacter[] inputCharacters = { new ConcreteInputCharacter('A'), new ConcreteInputCharacter('B'), new ConcreteInputCharacter('C') };
        Patterns pattern = null;
        // When
        NameState result = invokeFindMatchPattern(inputCharacters, pattern);
        // Then
        assertNull(result);
    }
}
