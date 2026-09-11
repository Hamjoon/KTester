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
Here is the code of focal method, `findRangePattern(Range)`, with the focal class `ByteMachine`:
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
    private NameState findRangePattern(Range range) {
        Set<NameState> nextNameStates = new HashSet<>();
        NameState nextNameState = null;
        ByteTransition forkTrans = startState;
        int forkOffset = 0;
        // bypass common prefix of range's bottom and top patterns
        while (range.bottom[forkOffset] == range.top[forkOffset]) {
            forkTrans = findNextByteStateForRangePattern(forkTrans, range.bottom[forkOffset++]);
            if (forkTrans == null) {
                return null;
            }
        } 
        // fill in matches in the fork state
        for (byte bb : Range.digitSequence(range.bottom[forkOffset], range.top[forkOffset], false, false)) {
            nextNameState = findMatchForRangePattern(bb, forkTrans, range);
            if (nextNameState == null) {
                return null;
            }
            nextNameStates.add(nextNameState);
        }
        // process all the transitions on the bottom range bytes
        ByteTransition trans = forkTrans;
        int lastMatchOffset = forkOffset;
        for (int offsetB = forkOffset + 1; offsetB < (range.bottom.length - 1); offsetB++) {
            byte b = range.bottom[offsetB];
            if (b < Constants.MAX_DIGIT) {
                while (lastMatchOffset < offsetB) {
                    trans = findNextByteStateForRangePattern(trans, range.bottom[lastMatchOffset++]);
                    if (trans == null) {
                        return null;
                    }
                } 
                assert lastMatchOffset == offsetB : "lastMatchOffset == offsetB";
                for (byte bb : Range.digitSequence(b, Constants.MAX_DIGIT, false, true)) {
                    nextNameState = findMatchForRangePattern(bb, trans, range);
                    if (nextNameState == null) {
                        return null;
                    }
                    nextNameStates.add(nextNameState);
                }
            }
        }
        // now for last "bottom" digit
        final byte lastBottom = range.bottom[range.bottom.length - 1];
        final byte lastTop = range.top[range.top.length - 1];
        if ((lastBottom < Constants.MAX_DIGIT) || (!range.openBottom)) {
            while (lastMatchOffset < (range.bottom.length - 1)) {
                trans = findNextByteStateForRangePattern(trans, range.bottom[lastMatchOffset++]);
                if (trans == null) {
                    return null;
                }
            } 
            assert lastMatchOffset == (range.bottom.length - 1) : "lastMatchOffset == (range.bottom.length - 1)";
            if (!range.openBottom) {
                nextNameState = findMatchForRangePattern(lastBottom, trans, range);
                if (nextNameState == null) {
                    return null;
                }
                nextNameStates.add(nextNameState);
            }
            // unless the last digit is also at the fork position, fill in the extra matches due to
            // the strictly-less-than condition (see discussion above)
            if (forkOffset < (range.bottom.length - 1)) {
                for (byte bb : Range.digitSequence(lastBottom, Constants.MAX_DIGIT, false, true)) {
                    nextNameState = findMatchForRangePattern(bb, trans, range);
                    if (nextNameState == null) {
                        return null;
                    }
                    nextNameStates.add(nextNameState);
                }
            }
        }
        // now process transitions along the top range bytes
        trans = forkTrans;
        lastMatchOffset = forkOffset;
        for (int offsetT = forkOffset + 1; offsetT < (range.top.length - 1); offsetT++) {
            byte b = range.top[offsetT];
            if (b > '0') {
                while (lastMatchOffset < offsetT) {
                    trans = findNextByteStateForRangePattern(trans, range.top[lastMatchOffset++]);
                    if (trans == null) {
                        return null;
                    }
                } 
                assert lastMatchOffset == offsetT : "lastMatchOffset == offsetT";
                for (byte bb : Range.digitSequence(((byte) ('0')), range.top[offsetT], true, false)) {
                    nextNameState = findMatchForRangePattern(bb, trans, range);
                    if (nextNameState == null) {
                        return null;
                    }
                    nextNameStates.add(nextNameState);
                }
            }
        }
        // now for last "top" digit
        if ((lastTop > '0') || (!range.openTop)) {
            while (lastMatchOffset < (range.top.length - 1)) {
                trans = findNextByteStateForRangePattern(trans, range.top[lastMatchOffset++]);
                if (trans == null) {
                    return null;
                }
            } 
            assert lastMatchOffset == (range.top.length - 1) : "lastMatchOffset == (range.top.length - 1)";
            if (!range.openTop) {
                nextNameState = findMatchForRangePattern(lastTop, trans, range);
                if (nextNameState == null) {
                    return null;
                }
                nextNameStates.add(nextNameState);
            }
            // unless the last digit is also at the fork position, fill in the extra matches due to
            // the strictly-less-than condition (see discussion above)
            if (forkOffset < (range.top.length - 1)) {
                for (byte bb : Range.digitSequence(((byte) ('0')), lastTop, true, false)) {
                    nextNameState = findMatchForRangePattern(bb, trans, range);
                    if (nextNameState == null) {
                        return null;
                    }
                    nextNameStates.add(nextNameState);
                }
            }
        }
        // There must only have one nextNameState object returned by this range pattern refer to
        // addRangePattern() where only one nextNameState is used by one pattern.
        assert nextNameStates.size() == 1 : "nextNameStates.size() == 1";
        return nextNameState;
    }
NameState addPattern(final Patterns pattern);
    void deletePattern(final Patterns pattern);
    public int evaluateComplexity(MachineComplexityEvaluator evaluator);
    private NameState findMatchForRangePattern(byte b, ByteTransition trans, Patterns pattern);
    private ByteTransition findNextByteStateForRangePattern(ByteTransition trans, final byte b);
    NameState findPattern(final Patterns pattern);
    public void gatherObjects(Set<Object> objectSet);
    boolean isEmpty();
    @Override
public String toString();
    Set<NameState> transitionOn(String valString);
}
```

@output{test cases}: output the test cases in the JSON format: