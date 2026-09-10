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
Here is the code of focal method, `addEndOfMatch(ByteState, ByteState, InputCharacter[], int, Patterns, NameState)`, with the focal class `ByteMachine`:
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
    private ByteMatch startStateMatch;
        public static final class EmptyByteTransition extends SingleByteTransition {
        static final EmptyByteTransition INSTANCE = new EmptyByteTransition();
    
        @Override
        public ByteState getNextByteState() {
            return null;
        }
    
        @Override
        public SingleByteTransition setNextByteState(ByteState nextState) {
            return nextState;
        }
    
        @Override
        public ByteTransition getTransition(byte utf8byte) {
            return null;
        }
    
        @Override
        public ByteTransition getTransitionForAllBytes() {
            return null;
        }
    
        @Override
        public Set<ByteTransition> getTransitions() {
            return Collections.emptySet();
        }
    
        @Override
        public ByteMatch getMatch() {
            return null;
        }
    
        @Override
        public Set<ShortcutTransition> getShortcuts() {
            return Collections.emptySet();
        }
    
        @Override
        public SingleByteTransition setMatch(ByteMatch match) {
            return match;
        }
    
        @Override
        public void gatherObjects(Set<Object> objectSet) {
            objectSet.add(this);
        }
    }
private NameState addEndOfMatch(ByteState state, ByteState prevState, final InputCharacter[] characters, final int charIndex, final Patterns pattern, final NameState nameStateCandidate) {
        final int length = characters.length;
        NameState nameState = (nameStateCandidate == null) ? new NameState() : nameStateCandidate;
        if ((length == 1) && isWildcard(characters[0])) {
            // Only character is '*'. Make the start state a match so empty input is matched.
            startStateMatch = new ByteMatch(pattern, nameState);
            return nameState;
        }
        ByteTransition trans = getTransition(state, characters[charIndex]);
        // If it is shortcut transition, we need do adjustment first.
        if ((!trans.isEmpty()) && trans.isShortcutTrans()) {
            ShortcutTransition shortcut = ((ShortcutTransition) (trans));
            ByteMatch match = shortcut.getMatch();
            // In add/delete rule path, match must not be null and must not have other match
            assert (match != null) && SHORTCUT_MATCH_TYPES.contains(match.getPattern().type());
            // If it is the same pattern, just return.
            if (pattern.equals(match.getPattern())) {
                return match.getNextNameState();
            }
            // Have asserted current match pattern must be value patterns
            String valueInCurrentPos = ((ValuePatterns) (match.getPattern())).pattern();
            final InputCharacter[] charactersInCurrentPos = getParser().parse(match.getPattern().type(), valueInCurrentPos);
            // find the position <m> where the common prefix ends.
            int m = charIndex;
            for (; (m < charactersInCurrentPos.length) && (m < length); m++) {
                if (!charactersInCurrentPos[m].equals(characters[m])) {
                    break;
                }
            }
            // Extend the prefix part in value to byte transitions, to avoid impact on concurrent read we need firstly
            // make the new byte chain ready for using and leave the old transition removing to the last step.
            // firstNewState will be head of new byte chain and, to avoid impact on concurrent match traffic in read
            // path, it need be linked to current state chain after adjustment done.
            ByteState firstNewState = null;
            ByteState currentState = state;
            for (int k = charIndex; k < m; k++) {
                // we need keep the current state always pointed to last character.
                if (k != (charactersInCurrentPos.length - 1)) {
                    final ByteState newByteState = new ByteState();
                    newByteState.setIndeterminatePrefix(currentState.hasIndeterminatePrefix());
                    if (k != charIndex) {
                        putTransitionNextState(currentState, charactersInCurrentPos[k], shortcut, newByteState);
                    } else {
                        firstNewState = newByteState;
                    }
                    currentState = newByteState;
                }
            }
            // If it reached to last character, link the previous read transition in this character, else create
            // shortcut transition. Note: at this time, the previous transition can still keep working.
            boolean isShortcutNeeded = m < (charactersInCurrentPos.length - 1);
            int indexToBeChange = (isShortcutNeeded) ? m : charactersInCurrentPos.length - 1;
            putTransitionMatch(currentState, charactersInCurrentPos[indexToBeChange], isShortcutNeeded ? new ShortcutTransition() : EmptyByteTransition.INSTANCE, match);
            removeTransition(currentState, charactersInCurrentPos[indexToBeChange], shortcut);
            // At last, we link the new created chain to the byte state path, so no uncompleted change can be felt by
            // reading thread. Note: we already confirmed there is only old shortcut transition at charIndex position,
            // now we have move it to new position, so we can directly replace previous transition with new transition
            // pointed to new byte state chain.
            putTransitionNextState(state, characters[charIndex], shortcut, firstNewState);
        }
        // If there is a exact match transition on tail of path, after adjustment target transitions, we start
        // looking at current remaining characters.
        // If this is tail transition, go directly analyse the remaining characters, traverse to tail of chain:
        boolean isEligibleForShortcut = true;
        int j = charIndex;
        for (; j < (length - 1); j++) {
            // We do not want to re-use an existing state for the second last character in the case of a final-character
            // wildcard pattern. In this case, we will have a self-referencing composite match state, which allows zero
            // or many character to satisfy the wildcard. The self-reference would lead to unintended matches for the
            // existing patterns.
            if ((j == (length - 2)) && isWildcard(characters[j + 1])) {
                break;
            }
            trans = getTransition(state, characters[j]);
            if (trans.isEmpty()) {
                break;
            }
            ByteState nextByteState = trans.getNextByteState();
            if (nextByteState != null) {
                // We cannot re-use a state with an indeterminate prefix without creating unintended matches.
                if (nextByteState.hasIndeterminatePrefix()) {
                    // Since there is more path we are unable to traverse, this means we cannot insert shortcut without
                    // potentially ignoring matches further down path.
                    isEligibleForShortcut = false;
                    break;
                }
                prevState = state;
                state = nextByteState;
            } else {
                // trans has match but no next state, we need prepare a next next state to add trans for either last
                // character or shortcut byte.
                final ByteState newByteState = new ByteState();
                newByteState.setIndeterminatePrefix(state.hasIndeterminatePrefix());
                // Stream will not be empty since trans has been verified as non-empty
                SingleByteTransition single = trans.expand().iterator().next();
                putTransitionNextState(state, characters[j], single, newByteState);
                prevState = state;
                state = newByteState;
            }
        }
        // look for a chance to put in a shortcut transition.
        // However, for the moment, we only do this for a JSON string match i.e beginning with ", not literals
        // like true or false or numbers, because if we do this for numbers produced by
        // ComparableNumber.generate(), they can be messed up by addRangePattern.
        if (SHORTCUT_MATCH_TYPES.contains(pattern.type())) {
            // For exactly match, if it is last character already, we just put the real transition with match there.
            if (j == (length - 1)) {
                return insertMatch(characters, j, state, nameState, pattern, prevState);
            } else if (isEligibleForShortcut) {
                // If current character is not last character, create the shortcut transition with the next
                ByteMatch byteMatch = new ByteMatch(pattern, nameState);
                addTransition(state, characters[j], new ShortcutTransition().setMatch(byteMatch));
                addMatchReferences(byteMatch);
                return nameState;
            }
        }
        // For other match type, keep the old logic to extend all characters to byte state path and put the match in the
        // tail state.
        for (; j < (length - 1); j++) {
            ByteState nextByteState = findOrMakeNextByteState(state, prevState, characters, j, pattern);
            prevState = state;
            state = nextByteState;
        }
        return insertMatch(characters, length - 1, state, nameState, pattern, prevState);
    }
private void addMatchReferences(ByteMatch match);
    NameState addPattern(final Patterns pattern);
    private static void addTransition(ByteState state, InputCharacter character, SingleByteTransition transition);
    void deletePattern(final Patterns pattern);
    public int evaluateComplexity(MachineComplexityEvaluator evaluator);
    private ByteState findOrMakeNextByteState(ByteState state, ByteState prevState, final InputCharacter[] characters, int currentIndex, Patterns pattern);
    NameState findPattern(final Patterns pattern);
    public void gatherObjects(Set<Object> objectSet);
    private static ByteTransition getTransition(ByteTransition trans, byte b);
    private static ByteTransition getTransition(SingleByteTransition trans, InputCharacter character);
    private NameState insertMatch(InputCharacter[] characters, int currentIndex, ByteState state, NameState nextNameState, Patterns pattern, ByteState prevState);
    boolean isEmpty();
    private static boolean isWildcard(InputCharacter character);
    private static SingleByteTransition putTransitionMatch(ByteState state, InputCharacter character, SingleByteTransition transition, ByteMatch match);
    private static void putTransitionNextState(ByteState state, InputCharacter character, SingleByteTransition transition, ByteState nextState);
    private static void removeTransition(ByteState state, InputCharacter character, SingleByteTransition transition);
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
class `software.amazon.event.ruler.ByteState`:
	
class `software.amazon.event.ruler.Patterns`:
	
class `software.amazon.event.ruler.NameState`:
	
class `software.amazon.event.ruler.ByteMachine`:
	dependent fields:
		java.util.Set<software.amazon.event.ruler.MatchType> software.amazon.event.ruler.ByteMachine.SHORTCUT_MATCH_TYPES;
		software.amazon.event.ruler.ByteMatch software.amazon.event.ruler.ByteMachine.startStateMatch;
	related functions:
		method `private NameState findMatchPattern(InputCharacter[], Patterns)` returns `software.amazon.event.ruler.NameState`, related with `software.amazon.event.ruler.ByteMachine.addEndOfMatch(ByteState, ByteState, InputCharacter[], int, Patterns, NameState)`;
		method `private NameState addMatchValue(Patterns, String, NameState)` returns `software.amazon.event.ruler.NameState`, related with `software.amazon.event.ruler.ByteMachine.addEndOfMatch(ByteState, ByteState, InputCharacter[], int, Patterns, NameState)`;
		method `private ByteTransition extendShortcutTransition(ByteState, ByteTransition, InputCharacter, int)` returns `software.amazon.event.ruler.ByteTransition`, related with `software.amazon.event.ruler.ByteMachine.addEndOfMatch(ByteState, ByteState, InputCharacter[], int, Patterns, NameState)`;
		method `private void deleteMatchPattern(ValuePatterns)` returns `void`, related with `software.amazon.event.ruler.ByteMachine.addEndOfMatch(ByteState, ByteState, InputCharacter[], int, Patterns, NameState)`;
		method `private ByteState findOrMakeNextByteState(ByteState, ByteState, InputCharacter[], int, Patterns)` returns `software.amazon.event.ruler.ByteState`, related with `software.amazon.event.ruler.ByteMachine.addEndOfMatch(ByteState, ByteState, InputCharacter[], int, Patterns, NameState)`;
		method `private void deleteMatchStep(ByteState, int, Patterns, InputCharacter[])` returns `void`, related with `software.amazon.event.ruler.ByteMachine.addEndOfMatch(ByteState, ByteState, InputCharacter[], int, Patterns, NameState)`;
		method `private static void addTransitionNextState(ByteState, InputCharacter, InputCharacter[], int, ByteState, Patterns, ByteState)` returns `void`, related with `software.amazon.event.ruler.ByteMachine.addEndOfMatch(ByteState, ByteState, InputCharacter[], int, Patterns, NameState)`;
		method `private ByteState findOrMakeNextByteStateForRangePattern(ByteState, byte[], int)` returns `software.amazon.event.ruler.ByteState`, related with `software.amazon.event.ruler.ByteMachine.addEndOfMatch(ByteState, ByteState, InputCharacter[], int, Patterns, NameState)`;
		method `private NameState insertMatch(InputCharacter[], int, ByteState, NameState, Patterns, ByteState)` returns `software.amazon.event.ruler.NameState`, related with `software.amazon.event.ruler.ByteMachine.addEndOfMatch(ByteState, ByteState, InputCharacter[], int, Patterns, NameState)`;
class `software.amazon.event.ruler.ByteMachine.EmptyByteTransition`:
	dependent fields:
		software.amazon.event.ruler.ByteMachine.EmptyByteTransition software.amazon.event.ruler.ByteMachine.EmptyByteTransition.INSTANCE;
	



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.