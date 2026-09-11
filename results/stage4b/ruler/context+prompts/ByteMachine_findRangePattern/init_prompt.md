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
Here is the code of focal method, `findRangePattern(Range)`, with the focal class `ByteMachine_findRangePattern_Test`:
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
    NameState findPattern(final Patterns pattern) {
        switch (pattern.type()) {
        case NUMERIC_RANGE:
            assert pattern instanceof Range;
            return findRangePattern((Range) pattern);
    }
```
example 2:
```java
    void deletePattern(final Patterns pattern) {
        switch (pattern.type()) {
            case NUMERIC_RANGE:
                assert pattern instanceof Range;
                deleteRangePattern((Range) pattern);
    }
    private void deleteRangePattern(Range range) {

        // if range to be deleted does not exist, just return
        if (findRangePattern(range) == null) {
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
class ByteMachine_findRangePattern_Test {
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