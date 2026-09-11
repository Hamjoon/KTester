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
Here is the code of focal method, `addRangePattern(Range)`, with the focal class `ByteMachine_addRangePattern_Test`:
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
    private NameState addRangePattern(final Range range) {
        // we prepare for one new NameSate here which will be used for range match to point to next NameSate.
        // however, it will not be used if match is already existing. in that case, we will reuse NameSate
        // from that match.
        NameState nextNameState = new NameState();
        ByteState forkState = startState;
        int forkOffset = 0;
        // bypass common prefix of range's bottom and top patterns
        while (range.bottom[forkOffset] == range.top[forkOffset]) {
            forkState = findOrMakeNextByteStateForRangePattern(forkState, range.bottom, forkOffset++);
        } 
        // now we've bypassed any initial positions where the top and bottom patterns are the same, and arrived
        // at a position where the 'top' and 'bottom' bytes differ. Such a position must occur because we require
        // that the bottom number be strictly less than the top number.  Let's call the current state the fork state.
        // At the fork state, any byte between the top and bottom byte values means success, the value must be strictly
        // greater than bottom and less than top.  That leaves the transitions for the bottom and top values.
        // After the fork state, we arrive at a state where any digit greater than the next bottom value
        // means success, because after the fork state we are already strictly less than the top value, and we
        // know then that we are greater than the bottom value.  A digit equal to the bottom value leads us
        // to another state where the same applies; anything greater than the bottom value is success.  Finally,
        // when we come to the last digit, as before, anything greater than the bottom value is success, and
        // being equal to the bottom value means failure if the interval is open, because the value is strictly
        // equal to the bottom of the range.
        // Following the top-byte transition out of the fork state leads to a state where the story is reversed;
        // any digit lower than the top value means success, successive matches to the top value lead to similar
        // states, and a final byte that matches the top value means failure if the interval is open at the top.
        // There is a further complication. Consider the case [ > 00299 < 00500 ].  The machine we need to
        // build is like this:
        // State0 =0=> State1 ; State1 =0=> State2 ; State2 =3=> MATCH ; State2 =4=> MATCH
        // That's it. Once you've seen 002 in the input, there's nothing that can follow that will be
        // strictly greater than the remaining 299.  Once you've seen 005 there's nothing that can
        // follow that will be strictly less than the remaining 500
        // But this only works when the suffix of the bottom range pattern is all 9's or if the suffix of the
        // top range pattern is all 0's
        // What could be simpler?
        // fill in matches in the fork state
        for (byte bb : Range.digitSequence(range.bottom[forkOffset], range.top[forkOffset], false, false)) {
            nextNameState = insertMatchForRangePattern(bb, forkState, nextNameState, range);
        }
        // process all the transitions on the bottom range bytes
        ByteState state = forkState;
        // lastMatchOffset is the last offset where we know we have to put in a match
        int lastMatchOffset = forkOffset;
        for (int offsetB = forkOffset + 1; offsetB < (range.bottom.length - 1); offsetB++) {
            // if b is Constants.MAX_DIGIT, then we should hold off adding transitions until we see a non-maxDigit digit
            // because of the special case described above.
            byte b = range.bottom[offsetB];
            if (b < Constants.MAX_DIGIT) {
                // add transitions for any 9's we bypassed
                while (lastMatchOffset < offsetB) {
                    state = findOrMakeNextByteStateForRangePattern(state, range.bottom, lastMatchOffset++);
                } 
                assert lastMatchOffset == offsetB : "lastMatchOffset == offsetB";
                assert state != null : "state != null";
                // now add transitions for values greater than this non-9 digit
                for (byte bb : Range.digitSequence(b, Constants.MAX_DIGIT, false, true)) {
                    nextNameState = insertMatchForRangePattern(bb, state, nextNameState, range);
                }
            }
        }
        // now for last "bottom" digit
        final byte lastBottom = range.bottom[range.bottom.length - 1];
        final byte lastTop = range.top[range.top.length - 1];
        // similarly, if the last digit is 9 and we have openBottom, there can be no matches so we're done.
        if ((lastBottom < Constants.MAX_DIGIT) || (!range.openBottom)) {
            // add transitions for any 9's we bypassed
            while (lastMatchOffset < (range.bottom.length - 1)) {
                state = findOrMakeNextByteStateForRangePattern(state, range.bottom, lastMatchOffset++);
            } 
            assert lastMatchOffset == (range.bottom.length - 1) : "lastMatchOffset == (range.bottom.length - 1)";
            assert state != null : "state != null";
            // now we insert matches for possible values of last digit
            if (!range.openBottom) {
                nextNameState = insertMatchForRangePattern(lastBottom, state, nextNameState, range);
            }
            // unless the last digit is also at the fork position, fill in the extra matches due to
            // the strictly-less-than condition (see discussion above)
            if (forkOffset < (range.bottom.length - 1)) {
                for (byte bb : Range.digitSequence(lastBottom, Constants.MAX_DIGIT, false, true)) {
                    nextNameState = insertMatchForRangePattern(bb, state, nextNameState, range);
                }
            }
        }
        // now process transitions along the top range bytes
        // restore the state and last match offset to fork position to start analyzing top value bytes ...
        state = forkState;
        lastMatchOffset = forkOffset;
        for (int offsetT = forkOffset + 1; offsetT < (range.top.length - 1); offsetT++) {
            // if b is '0', we should hold off adding transitions until we see a non-'0' digit.
            byte b = range.top[offsetT];
            // if need to add transition
            if (b > '0') {
                while (lastMatchOffset < offsetT) {
                    state = findOrMakeNextByteStateForRangePattern(state, range.top, lastMatchOffset++);
                } 
                assert lastMatchOffset == offsetT : "lastMatchOffset == offsetT";
                assert state != null : "state != null";
                // now add transitions for values less than this non-0 digit
                for (byte bb : Range.digitSequence(((byte) ('0')), range.top[offsetT], true, false)) {
                    nextNameState = insertMatchForRangePattern(bb, state, nextNameState, range);
                }
            }
        }
        // now for last "top" digit
        // similarly, if the last digit is 0 and we have openTop, there can be no matches so we're done.
        if ((lastTop > '0') || (!range.openTop)) {
            // add transitions for any 0's we bypassed
            while (lastMatchOffset < (range.top.length - 1)) {
                state = findOrMakeNextByteStateForRangePattern(state, range.top, lastMatchOffset++);
            } 
            assert lastMatchOffset == (range.top.length - 1) : "lastMatchOffset == (range.top.length - 1)";
            assert state != null : "state != null";
            // now we insert matches for possible values of last digit
            if (!range.openTop) {
                nextNameState = insertMatchForRangePattern(lastTop, state, nextNameState, range);
            }
            // unless the last digit is also at the fork position, fill in the extra matches due to
            // the strictly-less-than condition (see discussion above)
            if (forkOffset < (range.top.length - 1)) {
                for (byte bb : Range.digitSequence(((byte) ('0')), lastTop, true, false)) {
                    nextNameState = insertMatchForRangePattern(bb, state, nextNameState, range);
                }
            }
        }
        return nextNameState;
    }
NameState addPattern(final Patterns pattern);
    void deletePattern(final Patterns pattern);
    public int evaluateComplexity(MachineComplexityEvaluator evaluator);
    private ByteState findOrMakeNextByteStateForRangePattern(ByteState state, final byte[] utf8bytes, int currentIndex);
    NameState findPattern(final Patterns pattern);
    public void gatherObjects(Set<Object> objectSet);
    private NameState insertMatchForRangePattern(byte b, ByteState state, NameState nextNameState, Patterns pattern);
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
    NameState addPattern(final Patterns pattern) {
        switch (pattern.type()) {
            case NUMERIC_RANGE:
                assert pattern instanceof Range;
                return addRangePattern((Range) pattern);
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
class ByteMachine_addRangePattern_Test {
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