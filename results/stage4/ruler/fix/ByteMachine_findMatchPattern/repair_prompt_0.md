
You are an experienced Java developer tasked with fixing a piece of code that contains compilation errors. Your task is to resolve these issues after analyzing the code and its compilation feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any syntactical or logical errors.
2. Analyze the compilation feedback to understand the specific errors and their locations within the code.
3. Apply necessary corrections to the code based on the feedback, and output the compelete correct code.
The Java code you need to fix is provided below:
```java
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
            // Make the private method accessible
            method.setAccessible(true);
            return (NameState) method.invoke(byteMachine, (Object) characters, pattern);
        } catch (Exception e) {
            // Handle exceptions appropriately
            throw new RuntimeException(e);
        }
    }

    @Test
    void testFindMatchPatternWithExists() {
        // Given
        InputCharacter[] inputCharacters = {/* Initialize with test data */
        };
        // Adjust as necessary
        Patterns pattern = new Patterns(MatchType.EXISTS);
        // When
        NameState result = byteMachine.findMatchPattern(inputCharacters, pattern);
        // Then
        // Adjust assertions based on expected behavior
        assertNotNull(result);
    }

    @Test
    void testFindMatchPatternWithAnythingBut() {
        // Given
        InputCharacter[] inputCharacters = {/* Initialize with test data */
        };
        // Adjust as necessary
        Patterns pattern = new Patterns(MatchType.ANYTHING_BUT);
        // When
        NameState result = byteMachine.findMatchPattern(inputCharacters, pattern);
        // Then
        // Adjust assertions based on expected behavior
        assertNotNull(result);
    }

    @Test
    void testFindMatchPatternWithAnythingButIgnoreCase() {
        // Given
        InputCharacter[] inputCharacters = {/* Initialize with test data */
        };
        // Adjust as necessary
        Patterns pattern = new Patterns(MatchType.ANYTHING_BUT_IGNORE_CASE);
        // When
        NameState result = byteMachine.findMatchPattern(inputCharacters, pattern);
        // Then
        // Adjust assertions based on expected behavior
        assertNotNull(result);
    }

    @Test
    void testFindMatchPatternWithExact() {
        // Given
        InputCharacter[] inputCharacters = { new InputCharacter('A'), new InputCharacter('B'), new InputCharacter('C') };
        Patterns pattern = new Patterns(MatchType.EXACT);
        // When
        NameState result = invokeFindMatchPattern(inputCharacters, pattern);
        // Then
        // Adjust assertions based on expected behavior
        assertNotNull(result);
    }

    @Test
    void testFindMatchPatternWithAnythingButSuffix() {
        // Given
        InputCharacter[] inputCharacters = { new InputCharacter('A'), new InputCharacter('B'), new InputCharacter('C') };
        Patterns pattern = new Patterns(MatchType.ANYTHING_BUT_SUFFIX);
        // When
        NameState result = invokeFindMatchPattern(inputCharacters, pattern);
        // Then
        // Adjust assertions based on expected behavior
        assertNotNull(result);
    }

    @Test
    void testFindMatchPatternWithSuffix() {
        // Given
        InputCharacter[] inputCharacters = { new InputCharacter('X'), new InputCharacter('Y'), new InputCharacter('Z') };
        Patterns pattern = new Patterns(MatchType.SUFFIX);
        // When
        NameState result = invokeFindMatchPattern(inputCharacters, pattern);
        // Then
        // Expecting a null return as characters do not match the pattern
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
        // Expecting a null return for empty character array
        assertNull(result);
    }

    @Test
    void testFindMatchPatternWithSingleCharacterExists() {
        // Given
        InputCharacter[] inputCharacters = { new InputCharacter('A') };
        Patterns pattern = new Patterns(MatchType.EXISTS);
        // When
        NameState result = invokeFindMatchPattern(inputCharacters, pattern);
        // Then
        // Expecting a NameState object matching the pattern
        assertNotNull(result);
    }

    @Test
    void testFindMatchPatternWithLongerExactPattern() {
        // Given
        InputCharacter[] inputCharacters = { new InputCharacter('A'), new InputCharacter('B'), new InputCharacter('C'), new InputCharacter('D') };
        Patterns pattern = new Patterns(MatchType.EXACT);
        // When
        NameState result = invokeFindMatchPattern(inputCharacters, pattern);
        // Then
        // Expecting a NameState object or null based on the pattern
        assertNotNull(result);
    }

    @Test
    void testFindMatchPatternWithLongerExactPatternMatch() {
        // Given
        InputCharacter[] inputCharacters = { new InputCharacter('A'), new InputCharacter('B'), new InputCharacter('C'), new InputCharacter('D'), new InputCharacter('E') };
        Patterns pattern = new Patterns(MatchType.EXACT);
        // When
        NameState result = invokeFindMatchPattern(inputCharacters, pattern);
        // Then
        // Expecting a NameState object matching the longer exact pattern
        assertNotNull(result);
    }

    @Test
    void testFindMatchPatternWithSuffixMatch() {
        // Given
        InputCharacter[] inputCharacters = { new InputCharacter('D'), new InputCharacter('E'), new InputCharacter('F') };
        Patterns pattern = new Patterns(MatchType.SUFFIX);
        // When
        NameState result = invokeFindMatchPattern(inputCharacters, pattern);
        // Then
        // Expecting a NameState object matching the suffix pattern
        assertNotNull(result);
    }

    @Test
    void testFindMatchPatternWithNonExistentPattern() {
        // Given
        InputCharacter[] inputCharacters = { new InputCharacter('A'), new InputCharacter('B'), new InputCharacter('C') };
        Patterns pattern = new Patterns(MatchType.NON_EXISTENT_PATTERN);
        // When
        NameState result = invokeFindMatchPattern(inputCharacters, pattern);
        // Then
        // Expecting a null return
        assertNull(result);
    }

    @Test
    void testFindMatchPatternWithNullPattern() {
        // Given
        InputCharacter[] inputCharacters = { new InputCharacter('A'), new InputCharacter('B'), new InputCharacter('C') };
        Patterns pattern = null;
        // When
        NameState result = invokeFindMatchPattern(inputCharacters, pattern);
        // Then
        // Expecting a null return
        assertNull(result);
    }
}
```

Corresponding compilation feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
src/test/java/software/amazon/event/ruler/ByteMachine_findMatchPattern_Test.java:51: error: method findMatchPattern in class ByteMachine cannot be applied to given types;
        NameState result = byteMachine.findMatchPattern(inputCharacters, pattern);
                                      ^
  required: InputCharacter[],Patterns
  found:    InputCharacter[],Patterns
  reason: findMatchPattern(InputCharacter[],Patterns) has private access in ByteMachine
src/test/java/software/amazon/event/ruler/ByteMachine_findMatchPattern_Test.java:65: error: method findMatchPattern in class ByteMachine cannot be applied to given types;
        NameState result = byteMachine.findMatchPattern(inputCharacters, pattern);
                                      ^
  required: InputCharacter[],Patterns
  found:    InputCharacter[],Patterns
  reason: findMatchPattern(InputCharacter[],Patterns) has private access in ByteMachine
src/test/java/software/amazon/event/ruler/ByteMachine_findMatchPattern_Test.java:79: error: method findMatchPattern in class ByteMachine cannot be applied to given types;
        NameState result = byteMachine.findMatchPattern(inputCharacters, pattern);
                                      ^
  required: InputCharacter[],Patterns
  found:    InputCharacter[],Patterns
  reason: findMatchPattern(InputCharacter[],Patterns) has private access in ByteMachine
src/test/java/software/amazon/event/ruler/ByteMachine_findMatchPattern_Test.java:88: error: InputCharacter is abstract; cannot be instantiated
        InputCharacter[] inputCharacters = { new InputCharacter('A'), new InputCharacter('B'), new InputCharacter('C') };
                                             ^
src/test/java/software/amazon/event/ruler/ByteMachine_findMatchPattern_Test.java:88: error: InputCharacter is abstract; cannot be instantiated
        InputCharacter[] inputCharacters = { new InputCharacter('A'), new InputCharacter('B'), new InputCharacter('C') };
                                                                      ^
src/test/java/software/amazon/event/ruler/ByteMachine_findMatchPattern_Test.java:88: error: InputCharacter is abstract; cannot be instantiated
        InputCharacter[] inputCharacters = { new InputCharacter('A'), new InputCharacter('B'), new InputCharacter('C') };
                                                                                               ^
src/test/java/software/amazon/event/ruler/ByteMachine_findMatchPattern_Test.java:100: error: InputCharacter is abstract; cannot be instantiated
        InputCharacter[] inputCharacters = { new InputCharacter('A'), new InputCharacter('B'), new InputCharacter('C') };
                                             ^
src/test/java/software/amazon/event/ruler/ByteMachine_findMatchPattern_Test.java:100: error: InputCharacter is abstract; cannot be instantiated
        InputCharacter[] inputCharacters = { new InputCharacter('A'), new InputCharacter('B'), new InputCharacter('C') };
                                                                      ^
src/test/java/software/amazon/event/ruler/ByteMachine_findMatchPattern_Test.java:100: error: InputCharacter is abstract; cannot be instantiated
        InputCharacter[] inputCharacters = { new InputCharacter('A'), new InputCharacter('B'), new InputCharacter('C') };
                                                                                               ^
src/test/java/software/amazon/event/ruler/ByteMachine_findMatchPattern_Test.java:112: error: InputCharacter is abstract; cannot be instantiated
        InputCharacter[] inputCharacters = { new InputCharacter('X'), new InputCharacter('Y'), new InputCharacter('Z') };
                                             ^
src/test/java/software/amazon/event/ruler/ByteMachine_findMatchPattern_Test.java:112: error: InputCharacter is abstract; cannot be instantiated
        InputCharacter[] inputCharacters = { new InputCharacter('X'), new InputCharacter('Y'), new InputCharacter('Z') };
                                                                      ^
src/test/java/software/amazon/event/ruler/ByteMachine_findMatchPattern_Test.java:112: error: InputCharacter is abstract; cannot be instantiated
        InputCharacter[] inputCharacters = { new InputCharacter('X'), new InputCharacter('Y'), new InputCharacter('Z') };
                                                                                               ^
src/test/java/software/amazon/event/ruler/ByteMachine_findMatchPattern_Test.java:136: error: InputCharacter is abstract; cannot be instantiated
        InputCharacter[] inputCharacters = { new InputCharacter('A') };
                                             ^
src/test/java/software/amazon/event/ruler/ByteMachine_findMatchPattern_Test.java:148: error: InputCharacter is abstract; cannot be instantiated
        InputCharacter[] inputCharacters = { new InputCharacter('A'), new InputCharacter('B'), new InputCharacter('C'), new InputCharacter('D') };
                                             ^
src/test/java/software/amazon/event/ruler/ByteMachine_findMatchPattern_Test.java:148: error: InputCharacter is abstract; cannot be instantiated
        InputCharacter[] inputCharacters = { new InputCharacter('A'), new InputCharacter('B'), new InputCharacter('C'), new InputCharacter('D') };
                                                                      ^
src/test/java/software/amazon/event/ruler/ByteMachine_findMatchPattern_Test.java:148: error: InputCharacter is abstract; cannot be instantiated
        InputCharacter[] inputCharacters = { new InputCharacter('A'), new InputCharacter('B'), new InputCharacter('C'), new InputCharacter('D') };
                                                                                               ^
src/test/java/software/amazon/event/ruler/ByteMachine_findMatchPattern_Test.java:148: error: InputCharacter is abstract; cannot be instantiated
        InputCharacter[] inputCharacters = { new InputCharacter('A'), new InputCharacter('B'), new InputCharacter('C'), new InputCharacter('D') };
                                                                                                                        ^
src/test/java/software/amazon/event/ruler/ByteMachine_findMatchPattern_Test.java:160: error: InputCharacter is abstract; cannot be instantiated
        InputCharacter[] inputCharacters = { new InputCharacter('A'), new InputCharacter('B'), new InputCharacter('C'), new InputCharacter('D'), new InputCharacter('E') };
                                             ^
src/test/java/software/amazon/event/ruler/ByteMachine_findMatchPattern_Test.java:160: error: InputCharacter is abstract; cannot be instantiated
        InputCharacter[] inputCharacters = { new InputCharacter('A'), new InputCharacter('B'), new InputCharacter('C'), new InputCharacter('D'), new InputCharacter('E') };
                                                                      ^
src/test/java/software/amazon/event/ruler/ByteMachine_findMatchPattern_Test.java:160: error: InputCharacter is abstract; cannot be instantiated
        InputCharacter[] inputCharacters = { new InputCharacter('A'), new InputCharacter('B'), new InputCharacter('C'), new InputCharacter('D'), new InputCharacter('E') };
                                                                                               ^
src/test/java/software/amazon/event/ruler/ByteMachine_findMatchPattern_Test.java:160: error: InputCharacter is abstract; cannot be instantiated
        InputCharacter[] inputCharacters = { new InputCharacter('A'), new InputCharacter('B'), new InputCharacter('C'), new InputCharacter('D'), new InputCharacter('E') };
                                                                                                                        ^
src/test/java/software/amazon/event/ruler/ByteMachine_findMatchPattern_Test.java:160: error: InputCharacter is abstract; cannot be instantiated
        InputCharacter[] inputCharacters = { new InputCharacter('A'), new InputCharacter('B'), new InputCharacter('C'), new InputCharacter('D'), new InputCharacter('E') };
                                                                                                                                                 ^
src/test/java/software/amazon/event/ruler/ByteMachine_findMatchPattern_Test.java:172: error: InputCharacter is abstract; cannot be instantiated
        InputCharacter[] inputCharacters = { new InputCharacter('D'), new InputCharacter('E'), new InputCharacter('F') };
                                             ^
src/test/java/software/amazon/event/ruler/ByteMachine_findMatchPattern_Test.java:172: error: InputCharacter is abstract; cannot be instantiated
        InputCharacter[] inputCharacters = { new InputCharacter('D'), new InputCharacter('E'), new InputCharacter('F') };
                                                                      ^
src/test/java/software/amazon/event/ruler/ByteMachine_findMatchPattern_Test.java:172: error: InputCharacter is abstract; cannot be instantiated
        InputCharacter[] inputCharacters = { new InputCharacter('D'), new InputCharacter('E'), new InputCharacter('F') };
                                                                                               ^
src/test/java/software/amazon/event/ruler/ByteMachine_findMatchPattern_Test.java:184: error: InputCharacter is abstract; cannot be instantiated
        InputCharacter[] inputCharacters = { new InputCharacter('A'), new InputCharacter('B'), new InputCharacter('C') };
                                             ^
src/test/java/software/amazon/event/ruler/ByteMachine_findMatchPattern_Test.java:184: error: InputCharacter is abstract; cannot be instantiated
        InputCharacter[] inputCharacters = { new InputCharacter('A'), new InputCharacter('B'), new InputCharacter('C') };
                                                                      ^
src/test/java/software/amazon/event/ruler/ByteMachine_findMatchPattern_Test.java:184: error: InputCharacter is abstract; cannot be instantiated
        InputCharacter[] inputCharacters = { new InputCharacter('A'), new InputCharacter('B'), new InputCharacter('C') };
                                                                                               ^
src/test/java/software/amazon/event/ruler/ByteMachine_findMatchPattern_Test.java:185: error: cannot find symbol
        Patterns pattern = new Patterns(MatchType.NON_EXISTENT_PATTERN);
                                                 ^
  symbol:   variable NON_EXISTENT_PATTERN
  location: class MatchType
src/test/java/software/amazon/event/ruler/ByteMachine_findMatchPattern_Test.java:196: error: InputCharacter is abstract; cannot be instantiated
        InputCharacter[] inputCharacters = { new InputCharacter('A'), new InputCharacter('B'), new InputCharacter('C') };
                                             ^
src/test/java/software/amazon/event/ruler/ByteMachine_findMatchPattern_Test.java:196: error: InputCharacter is abstract; cannot be instantiated
        InputCharacter[] inputCharacters = { new InputCharacter('A'), new InputCharacter('B'), new InputCharacter('C') };
                                                                      ^
src/test/java/software/amazon/event/ruler/ByteMachine_findMatchPattern_Test.java:196: error: InputCharacter is abstract; cannot be instantiated
        InputCharacter[] inputCharacters = { new InputCharacter('A'), new InputCharacter('B'), new InputCharacter('C') };
                                                                                               ^
Note: Some messages have been simplified; recompile with -Xdiags:verbose to get full output
32 errors

```

And you can use the following related information to assist you in your analysis and correction:
```
{'api document of class software.amazon.event.ruler.ByteMachine': "\n * Represents a UTF8-byte-level state machine that matches a Ruler state machine's field values.\n * Each state is a map keyed by utf8 byte. getTransition(byte) yields a Target, which can contain either or\n *  both of the next ByteState, and the first of a chain of Matches, which indicate a match to some pattern.\n ", 'return type': 'software.amazon.event.ruler.NameState', 'dependent classes': "class `software.amazon.event.ruler.Patterns`:\n\t\nclass `software.amazon.event.ruler.ByteMachine`:\n\tdependent fields:\n\t\tjava.util.Set<software.amazon.event.ruler.MatchType> software.amazon.event.ruler.ByteMachine.SHORTCUT_MATCH_TYPES;\n\t\tsoftware.amazon.event.ruler.ByteState software.amazon.event.ruler.ByteMachine.startState;\n\trelated functions:\n\t\tmethod `private void deleteMatchStep(ByteState, int, Patterns, InputCharacter[])` returns `void`, related with `software.amazon.event.ruler.ByteMachine.findMatchPattern(InputCharacter[], Patterns)`;\n\t\tmethod `private void deleteMatchPattern(ValuePatterns)` returns `void`, related with `software.amazon.event.ruler.ByteMachine.findMatchPattern(InputCharacter[], Patterns)`;\n\t\tmethod `private NameState addEndOfMatch(ByteState, ByteState, InputCharacter[], int, Patterns, NameState)` returns `software.amazon.event.ruler.NameState`, related with `software.amazon.event.ruler.ByteMachine.findMatchPattern(InputCharacter[], Patterns)`;\n\t\tmethod `private NameState insertMatch(InputCharacter[], int, ByteState, NameState, Patterns, ByteState)` returns `software.amazon.event.ruler.NameState`, related with `software.amazon.event.ruler.ByteMachine.findMatchPattern(InputCharacter[], Patterns)`;\n\t\tmethod `private void addExistenceMatch(Set<NameState>)` returns `void`, related with `software.amazon.event.ruler.ByteMachine.findMatchPattern(InputCharacter[], Patterns)`;\n\t\tmethod `private NameState addMatchValue(Patterns, String, NameState)` returns `software.amazon.event.ruler.NameState`, related with `software.amazon.event.ruler.ByteMachine.findMatchPattern(InputCharacter[], Patterns)`;\n\t\tmethod `private void doTransitionOn(String, Set<NameState>, boolean)` returns `void`, related with `software.amazon.event.ruler.ByteMachine.findMatchPattern(InputCharacter[], Patterns)`;\n\t\tmethod `private SingleByteTransition deleteMatchStepForWildcard(ByteState, int, Patterns, InputCharacter[], SingleByteTransition, ByteState)` returns `software.amazon.event.ruler.SingleByteTransition`, related with `software.amazon.event.ruler.ByteMachine.findMatchPattern(InputCharacter[], Patterns)`, api document: \n     * Performs delete match step handling for certain wildcard cases.\n     *\n     * @param byteState The current ByteState.\n     * @param charIndex The index of the current character in characters.\n     * @param pattern The pattern we are deleting.\n     * @param characters The array of InputCharacters corresponding to the pattern's value.\n     * @param transition One of the transitions from byteState using the current byte.\n     * @param nextByteState The next ByteState led to by transition.\n     * @return Transition, or a replacement for transition if the original instance no longer exists in the machine.\n     ;"}
```

**Note**: If the method-to-test in the @Test function is a private method, you can use **reflection** to call it.

Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 


