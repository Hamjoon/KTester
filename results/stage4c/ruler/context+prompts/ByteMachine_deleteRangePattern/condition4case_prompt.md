As a professional Java software testing expert, your task is to generate test cases for a focal method.

You will be provided with:
1. The focal method that needs testing.
2. Basic information of the target class to which the focus method belongs, including fields and methods.

Ensure the generated test cases meet the following requirements:

Test Coverage Requirements:
- Create test cases to cover all conditional branches in the target method, including `if`, `else if`, `else`, `switch`, and `case` statements.

Output Format:
- Organize the test cases by its functions logically.
- Include input parameters, expected output, and a description for each test case.
- Use empty list `[]` for the input when a test case does not require input parameters.
- Provide an expected return value for each test case, or specify the behavior that should be verified if there's no return value.
- Clearly describe the intention of each test case in the description.

Follow the JSON format provided here for the output:
```json
[
    {
        "group": "test name",
        "cases": [
            {
                "input": [
                    {
                        "parameter": "param name",
                        "value": "param value"
                    }
                ],
                "expected": "expected exception or behavior",
                "description": "test scenario description"
            }
        ]
    },
    {...}
]
```
@input{focal method and target class}
Here is the code of focal method, `deleteRangePattern(Range)`, with the focal class `ByteMachine`:
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
    private void deleteRangePattern(Range range) {
        // if range to be deleted does not exist, just return
        if (findRangePattern(range) == null) {
            return;
        }
        // Inside Range pattern, there are bottom value and top value, this function will traverse each byte of bottom
        // and top value separately to hunt down all matches eligible to be deleted.
        // The ArrayDequeue is used to save all byteStates in transition path since state associated with first byte of
        // value to state associated with last byte of value. Then, checkAndDeleteStateAlongTraversedPath function will
        // check each state saved in ArrayDequeue along reverse direction of traversing path and recursively check and
        // delete match and state if it is eligible.
        // Note: byteState is deletable only when it has no match and no transition to next byteState.
        // Refer to definition of class ComparableNumber, the max length in bytes for Number type is 16,
        // so here we take 16 as ArrayDeque capacity which is defined as ComparableNumber.MAX_BYTE_LENGTH.
        final ArrayDeque<AbstractMap.SimpleImmutableEntry<Byte, ByteTransition>> byteStatesTraversePathAlongRangeBottomValue = new ArrayDeque<>(ComparableNumber.MAX_LENGTH_IN_BYTES);
        final ArrayDeque<AbstractMap.SimpleImmutableEntry<Byte, ByteTransition>> byteStatesTraversePathAlongRangeTopValue = new ArrayDeque<>(ComparableNumber.MAX_LENGTH_IN_BYTES);
        ByteTransition forkState = startState;
        int forkOffset = 0;
        byteStatesTraversePathAlongRangeBottomValue.addFirst(new AbstractMap.SimpleImmutableEntry<>(range.bottom[0], forkState));
        byteStatesTraversePathAlongRangeTopValue.addFirst(new AbstractMap.SimpleImmutableEntry<>(range.top[0], forkState));
        // bypass common prefix of range's bottom and top patterns
        // we need move forward the state and save all states traversed for checking later.
        while (range.bottom[forkOffset] == range.top[forkOffset]) {
            forkState = findNextByteStateForRangePattern(forkState, range.bottom[forkOffset]);
            assert forkState != null : "forkState != null";
            byteStatesTraversePathAlongRangeBottomValue.addFirst(new AbstractMap.SimpleImmutableEntry<>(range.bottom[forkOffset], forkState));
            byteStatesTraversePathAlongRangeTopValue.addFirst(new AbstractMap.SimpleImmutableEntry<>(range.top[forkOffset], forkState));
            forkOffset++;
        } 
        // when bottom byte on forkOffset position < top byte in same position, there must be matches existing
        // in this state, go ahead to delete matches in the fork state.
        for (byte bb : Range.digitSequence(range.bottom[forkOffset], range.top[forkOffset], false, false)) {
            deleteMatches(getParser().parse(bb), forkState, range);
        }
        // process all the transitions on the bottom range bytes
        ByteTransition state = forkState;
        int lastMatchOffset = forkOffset;
        // see explanation in addRangePattern(), we need delete state and match accordingly.
        for (int offsetB = forkOffset + 1; offsetB < (range.bottom.length - 1); offsetB++) {
            byte b = range.bottom[offsetB];
            if (b < Constants.MAX_DIGIT) {
                while (lastMatchOffset < offsetB) {
                    state = findNextByteStateForRangePattern(state, range.bottom[lastMatchOffset]);
                    assert state != null : "state must be existing for this pattern";
                    byteStatesTraversePathAlongRangeBottomValue.addFirst(new AbstractMap.SimpleImmutableEntry<>(range.bottom[lastMatchOffset], state));
                    lastMatchOffset++;
                } 
                assert lastMatchOffset == offsetB : "lastMatchOffset == offsetB";
                for (byte bb : Range.digitSequence(b, Constants.MAX_DIGIT, false, true)) {
                    deleteMatches(getParser().parse(bb), state, range);
                }
            }
        }
        // now for last "bottom" digit
        // see explanation in addRangePattern(), we need to delete states and matches accordingly.
        final byte lastBottom = range.bottom[range.bottom.length - 1];
        final byte lastTop = range.top[range.top.length - 1];
        if ((lastBottom < Constants.MAX_DIGIT) || (!range.openBottom)) {
            while (lastMatchOffset < (range.bottom.length - 1)) {
                state = findNextByteStateForRangePattern(state, range.bottom[lastMatchOffset]);
                assert state != null : "state != null";
                byteStatesTraversePathAlongRangeBottomValue.addFirst(new AbstractMap.SimpleImmutableEntry<>(range.bottom[lastMatchOffset], state));
                lastMatchOffset++;
            } 
            assert lastMatchOffset == (range.bottom.length - 1) : "lastMatchOffset == range.bottom.length - 1";
            if (!range.openBottom) {
                deleteMatches(getParser().parse(lastBottom), state, range);
            }
            // unless the last digit is also at the fork position, fill in the extra matches due to
            // the strictly-less-than condition (see discussion above)
            if (forkOffset < (range.bottom.length - 1)) {
                for (byte bb : Range.digitSequence(lastBottom, Constants.MAX_DIGIT, false, true)) {
                    deleteMatches(getParser().parse(bb), state, range);
                }
            }
        }
        // now process transitions along the top range bytes
        // see explanation in addRangePattern(), we need to delete states and matches accordingly.
        state = forkState;
        lastMatchOffset = forkOffset;
        for (int offsetT = forkOffset + 1; offsetT < (range.top.length - 1); offsetT++) {
            byte b = range.top[offsetT];
            if (b > '0') {
                while (lastMatchOffset < offsetT) {
                    state = findNextByteStateForRangePattern(state, range.top[lastMatchOffset]);
                    assert state != null : "state must be existing for this pattern";
                    byteStatesTraversePathAlongRangeTopValue.addFirst(new AbstractMap.SimpleImmutableEntry<>(range.top[lastMatchOffset], state));
                    lastMatchOffset++;
                } 
                assert lastMatchOffset == offsetT : "lastMatchOffset == offsetT";
                for (byte bb : Range.digitSequence(((byte) ('0')), range.top[offsetT], true, false)) {
                    deleteMatches(getParser().parse(bb), state, range);
                }
            }
        }
        // now for last "top" digit.
        // see explanation in addRangePattern(), we need to delete states and matches accordingly.
        if ((lastTop > '0') || (!range.openTop)) {
            while (lastMatchOffset < (range.top.length - 1)) {
                state = findNextByteStateForRangePattern(state, range.top[lastMatchOffset]);
                assert state != null : "state != null";
                byteStatesTraversePathAlongRangeTopValue.addFirst(new AbstractMap.SimpleImmutableEntry<>(range.top[lastMatchOffset], state));
                lastMatchOffset++;
            } 
            assert lastMatchOffset == (range.top.length - 1) : "lastMatchOffset == range.top.length - 1";
            if (!range.openTop) {
                deleteMatches(getParser().parse(lastTop), state, range);
            }
            // unless the last digit is also at the fork position, fill in the extra matches due to
            // the strictly-less-than condition (see discussion above)
            if (forkOffset < (range.top.length - 1)) {
                for (byte bb : Range.digitSequence(((byte) ('0')), lastTop, true, false)) {
                    deleteMatches(getParser().parse(bb), state, range);
                }
            }
        }
        // by now we should have deleted all matches in all associated byteStates,
        // now we start cleaning up ineffective byteSates along states traversed path we saved before.
        checkAndDeleteStateAlongTraversedPath(byteStatesTraversePathAlongRangeBottomValue);
        checkAndDeleteStateAlongTraversedPath(byteStatesTraversePathAlongRangeTopValue);
        // well done now, we have deleted all matches pattern matched and cleaned all empty state as if that pattern
        // wasn't added into machine before.
    }
NameState addPattern(final Patterns pattern);
    private void checkAndDeleteStateAlongTraversedPath(ArrayDeque<AbstractMap.SimpleImmutableEntry<Byte, ByteTransition>> byteStateQueue);
    private void deleteMatches(InputCharacter character, ByteTransition transition, Patterns pattern);
    void deletePattern(final Patterns pattern);
    public int evaluateComplexity(MachineComplexityEvaluator evaluator);
    private ByteTransition findNextByteStateForRangePattern(ByteTransition trans, final byte b);
    NameState findPattern(final Patterns pattern);
    private NameState findRangePattern(Range range);
    public void gatherObjects(Set<Object> objectSet);
    boolean isEmpty();
    @Override
public String toString();
    Set<NameState> transitionOn(String valString);
}
```

@output{test cases}: output the test cases in the JSON format: