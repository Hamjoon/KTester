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
Here is the code of focal method, `deleteRangePattern(Range)`, with the focal class `ByteMachine_deleteRangePattern_Test`:
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


@input{context information}

api document of class software.amazon.event.ruler.ByteMachine: 

 * Represents a UTF8-byte-level state machine that matches a Ruler state machine's field values.
 * Each state is a map keyed by utf8 byte. getTransition(byte) yields a Target, which can contain either or
 *  both of the next ByteState, and the first of a chain of Matches, which indicate a match to some pattern.
 

parameters in constructors and focal method: 
class `software.amazon.event.ruler.Range`:
api document : 
 * Represents a range of numeric values to match against.
 * "Numeric" means that the character repertoire is "digits"; initially, either 0-9 or 0-9a-f. In the current
 *  implementation, the number of digits in the top and bottom of the range is the same.
 
constructor:
```java
    private Range(final double bottom, final boolean openBottom, final double top, final boolean openTop) {
        super(MatchType.NUMERIC_RANGE);
        if (bottom >= top) {
            throw new IllegalArgumentException("Bottom must be less than top");
        }
        this.bottom = ComparableNumber.generate(bottom).getBytes(StandardCharsets.UTF_8);
        this.openBottom = openBottom;
        this.top = ComparableNumber.generate(top).getBytes(StandardCharsets.UTF_8);
        this.openTop = openTop;
        isCIDR = false;
    }

    Range(final byte[] bottom, final boolean openBottom, final byte[] top, final boolean openTop, final boolean isCIDR) {
        super(MatchType.NUMERIC_RANGE);
        this.bottom = bottom;
        this.top = top;
        this.openBottom = openBottom;
        this.openTop = openTop;
        this.isCIDR = isCIDR;
    }

    private Range(Range range) {
        super(MatchType.NUMERIC_RANGE);
        this.bottom = range.bottom.clone();
        this.openBottom = range.openBottom;
        this.top = range.top.clone();
        this.openTop = range.openTop;
        this.isCIDR = range.isCIDR;
    }

```

invoke examples of focal method: 
example 1:
```java
    void deletePattern(final Patterns pattern) {
        switch (pattern.type()) {
            case NUMERIC_RANGE:
                assert pattern instanceof Range;
                deleteRangePattern((Range) pattern);
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
class ByteMachine_deleteRangePattern_Test {
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