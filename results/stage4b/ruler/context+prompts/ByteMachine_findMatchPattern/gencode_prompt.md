You are a professional Java software testing expert. Your task is to generate high-quality unit test code for a focal method, using **JUnit5** and **Mockito** libraries. 

You'll be provided with:
1. The focal method to test.
2. The target class's structure (fields and methods).
3. A pre-defined test class framework with `@Before`/`@After` methods.
4. Test cases in JSON format.
5. Any relevant context.

**Requirements:**

Code Quality:
- Include clear comments explaining each test scenario.
- Ensure code is clean, understandable, and maintainable.
- Do not directly access private methods or fields. **Use reflection if the focal method is private.**
- Include all necessary imports; code must be free of syntax errors.

**Test Structure:**
- Follow the given test class framework; do not modify `@Before`/`@After` methods.
- Create one test function per test case group, ensuring all cases are covered.
- Use parameterized tests where appropriate.
- Reinitialize variables inside test functions if `@Setup` values don't fit.
- Properly mock and set up dependencies.

@input{focal method and target class}
Here is the code of focal method, `findMatchPattern(InputCharacter[], Patterns)`, with the focal class `ByteMachine`:
```Java
package software.amazon.event.ruler;
import software.amazon.event.ruler.input.InputMultiByteSet;
import java.util.Set;
import static software.amazon.event.ruler.MatchType.ANYTHING_BUT_SUFFIX;
import static software.amazon.event.ruler.input.MultiByte.MAX_FIRST_BYTE_FOR_TWO_BYTE_CHAR;
import java.util.AbstractMap;
import java.util.concurrent.ConcurrentHashMap;
import static software.amazon.event.ruler.input.DefaultParser.getParser;
import static software.amazon.event.ruler.MatchType.EXACT;
import static software.amazon.event.ruler.input.MultiByte.MIN_FIRST_BYTE_FOR_ONE_BYTE_CHAR;
import software.amazon.event.ruler.input.InputCharacter;
import java.util.HashSet;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.ArrayDeque;
import static software.amazon.event.ruler.MatchType.SUFFIX;
import software.amazon.event.ruler.input.InputCharacterType;
import static software.amazon.event.ruler.MatchType.EXISTS;
import static software.amazon.event.ruler.input.MultiByte.MAX_FIRST_BYTE_FOR_ONE_BYTE_CHAR;
import static software.amazon.event.ruler.input.MultiByte.MAX_NON_FIRST_BYTE;
import java.nio.charset.StandardCharsets;
import static software.amazon.event.ruler.CompoundByteTransition.coalesce;
import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.AtomicInteger;
import com.fasterxml.jackson.core.io.doubleparser.FastDoubleParser;
import static software.amazon.event.ruler.input.MultiByte.MIN_FIRST_BYTE_FOR_TWO_BYTE_CHAR;
import software.amazon.event.ruler.input.InputByte;
import software.amazon.event.ruler.input.MultiByte;
import java.util.Arrays;
@ThreadSafe
class ByteMachine {
    private static final Set<MatchType> SHORTCUT_MATCH_TYPES = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(EXACT)));
    private final ByteState startState = new ByteState();
    private NameState findMatchPattern(final InputCharacter[] characters, final Patterns pattern) {
        Set<SingleByteTransition> transitionsToProcess = new HashSet<>();
        transitionsToProcess.add(startState);
        ByteTransition shortcutTrans = null;
        // Iterate down all possible paths in machine that match characters.
        outerLoop : for (int i = 0; i < (characters.length - 1); i++) {
            Set<SingleByteTransition> nextTransitionsToProcess = new HashSet<>();
            for (SingleByteTransition trans : transitionsToProcess) {
                ByteTransition nextTrans = getTransition(trans, characters[i]);
                for (SingleByteTransition eachTrans : nextTrans.expand()) {
                    // Shortcut and stop outer character loop
                    if (SHORTCUT_MATCH_TYPES.contains(pattern.type()) && eachTrans.isShortcutTrans()) {
                        shortcutTrans = eachTrans;
                        break outerLoop;
                    }
                    SingleByteTransition nextByteState = eachTrans.getNextByteState();
                    if (nextByteState == null) {
                        return null;
                    }
                    // We are interested in the first state that hasn't simply led back to trans
                    if (trans != nextByteState) {
                        nextTransitionsToProcess.add(nextByteState);
                    }
                }
            }
            transitionsToProcess = nextTransitionsToProcess;
        }
        // Get all possible transitions for final character.
        Set<ByteTransition> finalTransitionsToProcess = new HashSet<>();
        if (shortcutTrans != null) {
            finalTransitionsToProcess.add(shortcutTrans);
        } else {
            for (SingleByteTransition trans : transitionsToProcess) {
                finalTransitionsToProcess.add(getTransition(trans, characters[characters.length - 1]));
            }
        }
        // Check matches for all possible final transitions until we find the pattern we are looking for.
        for (ByteTransition nextTrans : finalTransitionsToProcess) {
            for (ByteMatch match : nextTrans.getMatches()) {
                if (match.getPattern().equals(pattern)) {
                    return match.getNextNameState();
                }
            }
        }
        return null;
    }
NameState addPattern(final Patterns pattern);
    void deletePattern(final Patterns pattern);
    public int evaluateComplexity(MachineComplexityEvaluator evaluator);
    NameState findPattern(final Patterns pattern);
    public void gatherObjects(Set<Object> objectSet);
    private static ByteTransition getTransition(ByteTransition trans, byte b);
    private static ByteTransition getTransition(SingleByteTransition trans, InputCharacter character);
    boolean isEmpty();
    @Override
public String toString();
    Set<NameState> transitionOn(String valString);
}
```

@input{initial test class framework}
```Java
<initial_class>
```

@input{existing test case}
here's the existing test cases:
```json
<cases_json>
```


@input{context information}

api document of class software.amazon.event.ruler.ByteMachine: 

 * Represents a UTF8-byte-level state machine that matches a Ruler state machine's field values.
 * Each state is a map keyed by utf8 byte. getTransition(byte) yields a Target, which can contain either or
 *  both of the next ByteState, and the first of a chain of Matches, which indicate a match to some pattern.
 

return type: 
software.amazon.event.ruler.NameState

dependent classes: 
class `software.amazon.event.ruler.Patterns`:
	
class `software.amazon.event.ruler.ByteMachine`:
	dependent fields:
		java.util.Set<software.amazon.event.ruler.MatchType> software.amazon.event.ruler.ByteMachine.SHORTCUT_MATCH_TYPES;
		software.amazon.event.ruler.ByteState software.amazon.event.ruler.ByteMachine.startState;
	related functions:
		method `private void deleteMatchPattern(ValuePatterns)` returns `void`, related with `software.amazon.event.ruler.ByteMachine.findMatchPattern(InputCharacter[], Patterns)`;
		method `private NameState addMatchValue(Patterns, String, NameState)` returns `software.amazon.event.ruler.NameState`, related with `software.amazon.event.ruler.ByteMachine.findMatchPattern(InputCharacter[], Patterns)`;
		method `private NameState addEndOfMatch(ByteState, ByteState, InputCharacter[], int, Patterns, NameState)` returns `software.amazon.event.ruler.NameState`, related with `software.amazon.event.ruler.ByteMachine.findMatchPattern(InputCharacter[], Patterns)`;
		method `private void doTransitionOn(String, Set<NameState>, boolean)` returns `void`, related with `software.amazon.event.ruler.ByteMachine.findMatchPattern(InputCharacter[], Patterns)`;
		method `private void deleteMatchStep(ByteState, int, Patterns, InputCharacter[])` returns `void`, related with `software.amazon.event.ruler.ByteMachine.findMatchPattern(InputCharacter[], Patterns)`;
		method `private SingleByteTransition deleteMatchStepForWildcard(ByteState, int, Patterns, InputCharacter[], SingleByteTransition, ByteState)` returns `software.amazon.event.ruler.SingleByteTransition`, related with `software.amazon.event.ruler.ByteMachine.findMatchPattern(InputCharacter[], Patterns)`, api document: 
     * Performs delete match step handling for certain wildcard cases.
     *
     * @param byteState The current ByteState.
     * @param charIndex The index of the current character in characters.
     * @param pattern The pattern we are deleting.
     * @param characters The array of InputCharacters corresponding to the pattern's value.
     * @param transition One of the transitions from byteState using the current byte.
     * @param nextByteState The next ByteState led to by transition.
     * @return Transition, or a replacement for transition if the original instance no longer exists in the machine.
     ;
		method `private void addExistenceMatch(Set<NameState>)` returns `void`, related with `software.amazon.event.ruler.ByteMachine.findMatchPattern(InputCharacter[], Patterns)`;
		method `private NameState insertMatch(InputCharacter[], int, ByteState, NameState, Patterns, ByteState)` returns `software.amazon.event.ruler.NameState`, related with `software.amazon.event.ruler.ByteMachine.findMatchPattern(InputCharacter[], Patterns)`;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.