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
Here is the code of focal method, `doTransitionOn(String, Set<NameState>, boolean)`, with the focal class `ByteMachine`:
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
    private final ByteState startState = new ByteState();
    private ByteMatch startStateMatch;
    private final Set<NameState> anythingButs = ConcurrentHashMap.newKeySet();
    private void doTransitionOn(final String valString, final Set<NameState> transitionTo, boolean fieldValueIsNumeric) {
        final Set<NameState> failedAnythingButs = new HashSet<>();
        final byte[] val = valString.getBytes(StandardCharsets.UTF_8);
        // we need to add the name state for key existence
        addExistenceMatch(transitionTo);
        // attempt to harvest the possible suffix match
        addSuffixMatch(val, transitionTo, failedAnythingButs);
        if (startStateMatch != null) {
            transitionTo.add(startStateMatch.getNextNameState());
        }
        // we have to do old-school indexing rather than "for (byte b : val)" because there is some special-casing
        // on transitions on the last byte in the value array
        ByteTransition trans = startState;
        for (int valIndex = 0; valIndex < val.length; valIndex++) {
            final ByteTransition nextTrans = getTransition(trans, val[valIndex]);
            attemptAddShortcutTransitionMatch(nextTrans, valString, EXACT, transitionTo);
            if (!nextTrans.isShortcutTrans()) {
                // process any matches hanging off this transition
                for (ByteMatch match : nextTrans.getMatches()) {
                    switch (match.getPattern().type()) {
                        case EXACT :
                        case EQUALS_IGNORE_CASE :
                        case WILDCARD :
                            if (valIndex == (val.length - 1)) {
                                transitionTo.add(match.getNextNameState());
                            }
                            break;
                        case NUMERIC_EQ :
                            // only matches at last character
                            if (fieldValueIsNumeric && (valIndex == (val.length - 1))) {
                                transitionTo.add(match.getNextNameState());
                            }
                            break;
                        case PREFIX :
                            transitionTo.add(match.getNextNameState());
                            break;
                        case ANYTHING_BUT_SUFFIX :
                        case SUFFIX :
                        case EXISTS :
                            // we already harvested these matches via separate functions due to special matching
                            // requirements, so just ignore them here.
                            break;
                        case NUMERIC_RANGE :
                            // as soon as you see the match, you've matched
                            Range range = ((Range) (match.getPattern()));
                            if ((fieldValueIsNumeric && (!range.isCIDR)) || ((!fieldValueIsNumeric) && range.isCIDR)) {
                                transitionTo.add(match.getNextNameState());
                            }
                            break;
                        case ANYTHING_BUT :
                            AnythingBut anythingBut = ((AnythingBut) (match.getPattern()));
                            // only applies if at last character
                            if ((valIndex == (val.length - 1)) && (anythingBut.isNumeric() == fieldValueIsNumeric)) {
                                failedAnythingButs.add(match.getNextNameState());
                            }
                            break;
                        case ANYTHING_BUT_IGNORE_CASE :
                            // only applies if at last character
                            if (valIndex == (val.length - 1)) {
                                failedAnythingButs.add(match.getNextNameState());
                            }
                            break;
                        case ANYTHING_BUT_PREFIX :
                            failedAnythingButs.add(match.getNextNameState());
                            break;
                        default :
                            throw new RuntimeException("Not implemented yet");
                    }
                }
            }
            trans = nextTrans.getTransitionForNextByteStates();
            if (trans == null) {
                break;
            }
        }
        // This may look like premature optimization, but the first "if" here yields roughly 10x performance
        // improvement.
        if (!anythingButs.isEmpty()) {
            if (!failedAnythingButs.isEmpty()) {
                transitionTo.addAll(anythingButs.stream().filter(anythingBut -> !failedAnythingButs.contains(anythingBut)).collect(Collectors.toList()));
            } else {
                transitionTo.addAll(anythingButs);
            }
        }
    }
private void addExistenceMatch(final Set<NameState> transitionTo);
    NameState addPattern(final Patterns pattern);
    private void addSuffixMatch(final byte[] val, final Set<NameState> transitionTo, Set<NameState> failedAnythingButs);
    private boolean attemptAddShortcutTransitionMatch(final ByteTransition transition, final String value, final MatchType expectedMatchType, final Set<NameState> transitionTo);
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
 

dependent classes: 
class `software.amazon.event.ruler.ByteMachine`:
	dependent fields:
		software.amazon.event.ruler.ByteState software.amazon.event.ruler.ByteMachine.startState;
		java.util.Set<software.amazon.event.ruler.NameState> software.amazon.event.ruler.ByteMachine.anythingButs;
		software.amazon.event.ruler.ByteMatch software.amazon.event.ruler.ByteMachine.startStateMatch;
	related functions:
		method ` void deletePattern(Patterns)` returns `void`, related with `software.amazon.event.ruler.ByteMachine.doTransitionOn(String, Set<NameState>, boolean)`;
		method `private void addSuffixMatch(byte[], Set<NameState>, Set<NameState>)` returns `void`, related with `software.amazon.event.ruler.ByteMachine.doTransitionOn(String, Set<NameState>, boolean)`;
		method `private void addMatchReferences(ByteMatch)` returns `void`, related with `software.amazon.event.ruler.ByteMachine.doTransitionOn(String, Set<NameState>, boolean)`;
		method `private NameState findMatchPattern(InputCharacter[], Patterns)` returns `software.amazon.event.ruler.NameState`, related with `software.amazon.event.ruler.ByteMachine.doTransitionOn(String, Set<NameState>, boolean)`;
		method `private void updateMatchReferences(ByteMatch)` returns `void`, related with `software.amazon.event.ruler.ByteMachine.doTransitionOn(String, Set<NameState>, boolean)`;
		method `private void addExistenceMatch(Set<NameState>)` returns `void`, related with `software.amazon.event.ruler.ByteMachine.doTransitionOn(String, Set<NameState>, boolean)`;
		method ` NameState findPattern(Patterns)` returns `software.amazon.event.ruler.NameState`, related with `software.amazon.event.ruler.ByteMachine.doTransitionOn(String, Set<NameState>, boolean)`;
class `software.amazon.event.ruler.MatchType`:
	dependent fields:
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.WILDCARD;
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.NUMERIC_EQ;
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.EXISTS;
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.EQUALS_IGNORE_CASE;
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.PREFIX;
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.ANYTHING_BUT_SUFFIX;
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.ANYTHING_BUT;
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.ANYTHING_BUT_IGNORE_CASE;
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.EXACT;
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.ANYTHING_BUT_PREFIX;
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.NUMERIC_RANGE;
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.SUFFIX;
	
class `software.amazon.event.ruler.Range`:
	dependent fields:
		boolean software.amazon.event.ruler.Range.isCIDR;
	
class `software.amazon.event.ruler.Ruler`:
	related functions:
		method `private static boolean matches(JsonNode, Patterns)` returns `boolean`, related with `software.amazon.event.ruler.ByteMachine.doTransitionOn(String, Set<NameState>, boolean)`;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.