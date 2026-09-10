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
class `software.amazon.event.ruler.Range`:
	dependent fields:
		byte[] software.amazon.event.ruler.Range.bottom;
		byte[] software.amazon.event.ruler.Range.top;
		boolean software.amazon.event.ruler.Range.openBottom;
		boolean software.amazon.event.ruler.Range.openTop;
	related functions:
		method `public String toString()` returns `java.lang.String`, related with `software.amazon.event.ruler.ByteMachine.deleteRangePattern(Range)`;
		method `public boolean equals(Object)` returns `boolean`, related with `software.amazon.event.ruler.ByteMachine.deleteRangePattern(Range)`;
class `software.amazon.event.ruler.ComparableNumber`:
	dependent fields:
		int software.amazon.event.ruler.ComparableNumber.MAX_LENGTH_IN_BYTES;
	
class `software.amazon.event.ruler.ByteMachine`:
	dependent fields:
		software.amazon.event.ruler.ByteState software.amazon.event.ruler.ByteMachine.startState;
	related functions:
		method `private NameState findRangePattern(Range)` returns `software.amazon.event.ruler.NameState`, related with `software.amazon.event.ruler.ByteMachine.deleteRangePattern(Range)`;
		method `private void deleteMatchPattern(ValuePatterns)` returns `void`, related with `software.amazon.event.ruler.ByteMachine.deleteRangePattern(Range)`;
		method `private NameState addRangePattern(Range)` returns `software.amazon.event.ruler.NameState`, related with `software.amazon.event.ruler.ByteMachine.deleteRangePattern(Range)`;
		method `private NameState addMatchValue(Patterns, String, NameState)` returns `software.amazon.event.ruler.NameState`, related with `software.amazon.event.ruler.ByteMachine.deleteRangePattern(Range)`;
		method `private void deleteExistencePattern(Patterns)` returns `void`, related with `software.amazon.event.ruler.ByteMachine.deleteRangePattern(Range)`;
		method `private NameState insertMatchForRangePattern(byte, ByteState, NameState, Patterns)` returns `software.amazon.event.ruler.NameState`, related with `software.amazon.event.ruler.ByteMachine.deleteRangePattern(Range)`;
class `software.amazon.event.ruler.Constants`:
	dependent fields:
		byte software.amazon.event.ruler.Constants.MAX_DIGIT;
	



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.