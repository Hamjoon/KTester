You are a professional software tester. Write test cases for a focal method in a target class.

**Instructions:**
1. Analyze method behavior and identify ALL possible exception scenarios
2. Design test cases that trigger each identified exception
3. Follow existing test case structure, Avoid duplication and create new test cases for uncovered exception scenarios

Test Case Format:
1. Group test cases by test function name
2. Each test case MUST include:
   - Input parameters with values (use empty list `[]` if none)
   - Expected exception or observable behavior
   - Clear description of test scenario
3. Return empty array if no exception scenarios found

Output Schema (STRICT JSON format):
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
Here is the code of focal method, `transcodeRemaining(char, char, char, char)`, with the focal class `Nysiis`:
```Java
package org.apache.commons.codec.language;
import java.util.regex.Pattern;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
public class Nysiis implements StringEncoder {
    private static final char[] CHARS_A = new char[]{ 'A' };
    private static final char[] CHARS_AF = new char[]{ 'A', 'F' };
    private static final char[] CHARS_C = new char[]{ 'C' };
    private static final char[] CHARS_FF = new char[]{ 'F', 'F' };
    private static final char[] CHARS_G = new char[]{ 'G' };
    private static final char[] CHARS_N = new char[]{ 'N' };
    private static final char[] CHARS_NN = new char[]{ 'N', 'N' };
    private static final char[] CHARS_S = new char[]{ 'S' };
    private static final char[] CHARS_SSS = new char[]{ 'S', 'S', 'S' };
    private static char[] transcodeRemaining(final char prev, final char curr, final char next, final char aNext) {
        // 1. EV -> AF
        if ((curr == 'E') && (next == 'V')) {
            return CHARS_AF;
        }
        // A, E, I, O, U -> A
        if (isVowel(curr)) {
            return CHARS_A;
        }
        // 2. Q -> G, Z -> S, M -> N
        // 3. KN -> NN else K -> C
        switch (curr) {
            case 'Q' :
                return CHARS_G;
            case 'Z' :
                return CHARS_S;
            case 'M' :
                return CHARS_N;
            case 'K' :
                if (next == 'N') {
                    return CHARS_NN;
                }
                return CHARS_C;
            default :
                break;
        }
        // 4. SCH -> SSS
        if (((curr == 'S') && (next == 'C')) && (aNext == 'H')) {
            return CHARS_SSS;
        }
        // PH -> FF
        if ((curr == 'P') && (next == 'H')) {
            return CHARS_FF;
        }
        // 5. H -> If previous or next is a non vowel, previous.
        if ((curr == 'H') && ((!isVowel(prev)) || (!isVowel(next)))) {
            return new char[]{ prev };
        }
        // 6. W -> If previous is vowel, previous.
        if ((curr == 'W') && isVowel(prev)) {
            return new char[]{ prev };
        }
        return new char[]{ curr };
    }
@Override
public Object encode(final Object obj) throws EncoderException;
    @Override
public String encode(final String str);
    public boolean isStrict();
    private static boolean isVowel(final char c);
    public String nysiis(String str);
}
```
@input{existing test case}
here's the existing test cases:
```json
<cases_json>
```

@output{test cases}: output the test cases in the JSON format: