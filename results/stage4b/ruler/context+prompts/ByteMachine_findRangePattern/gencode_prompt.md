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
class `software.amazon.event.ruler.Range`:
	dependent fields:
		byte[] software.amazon.event.ruler.Range.bottom;
		boolean software.amazon.event.ruler.Range.openBottom;
		boolean software.amazon.event.ruler.Range.openTop;
		byte[] software.amazon.event.ruler.Range.top;
	related functions:
		method `public boolean equals(Object)` returns `boolean`, related with `software.amazon.event.ruler.ByteMachine.findRangePattern(Range)`;
		method `public String toString()` returns `java.lang.String`, related with `software.amazon.event.ruler.ByteMachine.findRangePattern(Range)`;
class `software.amazon.event.ruler.ByteMachine`:
	dependent fields:
		software.amazon.event.ruler.ByteState software.amazon.event.ruler.ByteMachine.startState;
	related functions:
		method `private NameState findMatchPattern(InputCharacter[], Patterns)` returns `software.amazon.event.ruler.NameState`, related with `software.amazon.event.ruler.ByteMachine.findRangePattern(Range)`;
		method `private NameState addRangePattern(Range)` returns `software.amazon.event.ruler.NameState`, related with `software.amazon.event.ruler.ByteMachine.findRangePattern(Range)`;
		method `private void deleteRangePattern(Range)` returns `void`, related with `software.amazon.event.ruler.ByteMachine.findRangePattern(Range)`;
class `software.amazon.event.ruler.Constants`:
	dependent fields:
		byte software.amazon.event.ruler.Constants.MAX_DIGIT;
	
class `software.amazon.event.ruler.MachineComplexityEvaluator`:
	related functions:
		method `private static Set<Patterns> getWildcardPatterns(Set<ByteMatch>)` returns `java.util.Set<software.amazon.event.ruler.Patterns>`, related with `software.amazon.event.ruler.ByteMachine.findRangePattern(Range)`;
class `software.amazon.event.ruler.NameState`:
	related functions:
		method ` Set<NameState> getNameTransitions(String[])` returns `java.util.Set<software.amazon.event.ruler.NameState>`, related with `software.amazon.event.ruler.ByteMachine.findRangePattern(Range)`;
class `software.amazon.event.ruler.JsonRuleCompiler`:
	related functions:
		method `private static Patterns processAnythingButEqualsIgnoreCaseMatchExpression(JsonParser, JsonToken)` returns `software.amazon.event.ruler.Patterns`, related with `software.amazon.event.ruler.ByteMachine.findRangePattern(Range)`;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.