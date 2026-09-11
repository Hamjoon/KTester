You are tasked with creating a framework for a test class in Java. Now analyze the following information carefully:

1. The method that needs to be tested and its parameters.
2. The constructor of the target class and its parameters.
3. Any dependencies or external resources used by the target class.

Based on this analysis, complete the test class template in the provided structure:

1. Initialize necessary fields and dependencies within setupBeforeAll() and setupBeforeEach().
2. Handle any required cleanup in teardownAfterEach() and teardownAfterAll().
3. Make sure not to invoke any private methods or fields of the target class and dependent class.
4. Refer to the "given" phase in the existing test class for guidance.
5. Add necessary import statements and ensure there are no syntax errors in the generated code.

@input{focal method and target class}
Here is the code of focal method, `doTransitionOn(String, Set<NameState>, boolean)`, with the focal class `ByteMachine_doTransitionOn_Test`:
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


@input{context information}

api document of class software.amazon.event.ruler.ByteMachine: 

 * Represents a UTF8-byte-level state machine that matches a Ruler state machine's field values.
 * Each state is a map keyed by utf8 byte. getTransition(byte) yields a Target, which can contain either or
 *  both of the next ByteState, and the first of a chain of Matches, which indicate a match to some pattern.
 

invoke examples of focal method: 
example 1:
```java
    Set<NameState> transitionOn(String valString) {

        // not thread-safe, but this is only used in the scope of this method on one thread
        final Set<NameState> transitionTo = new HashSet<>();
        boolean fieldValueIsNumeric = false;
        if (hasNumeric.get() > 0) {
        doTransitionOn(valString, transitionTo, fieldValueIsNumeric);
    }
```



@input{test class template}
```java
package software.amazon.event.ruler;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
// Add other imports if necessary

@ExtendWith(MockitoExtension.class)
class ByteMachine_doTransitionOn_Test {
    @BeforeAll
    static void setupBeforeAll() {
    }

    @BeforeEach
    void setupBeforeEach() {
    }

    @AfterEach
    void teardownAfterEach() {
    }

    @AfterAll
    static void teardownAfterAll() {
    }
}
```

@output{initial test class}: now consider the invoke examples output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.