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
Here is the code of focal method, `nysiis(String)`, with the focal class `Nysiis`:
```Java
package org.apache.commons.codec.language;
import java.util.regex.Pattern;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
public class Nysiis implements StringEncoder {
    private static final Pattern PAT_MAC = Pattern.compile("^MAC");
    private static final Pattern PAT_KN = Pattern.compile("^KN");
    private static final Pattern PAT_K = Pattern.compile("^K");
    private static final Pattern PAT_PH_PF = Pattern.compile("^(PH|PF)");
    private static final Pattern PAT_SCH = Pattern.compile("^SCH");
    private static final Pattern PAT_EE_IE = Pattern.compile("(EE|IE)$");
    private static final Pattern PAT_DT_ETC = Pattern.compile("(DT|RT|RD|NT|ND)$");
    private static final char SPACE = ' ';
    private static final int TRUE_LENGTH = 6;
    public String nysiis(String str) {
        if (str == null) {
            return null;
        }
        // Use the same clean rules as Soundex
        str = SoundexUtils.clean(str);
        if (str.isEmpty()) {
            return str;
        }
        // Translate first characters of name:
        // MAC -> MCC, KN -> NN, K -> C, PH | PF -> FF, SCH -> SSS
        str = PAT_MAC.matcher(str).replaceFirst("MCC");
        str = PAT_KN.matcher(str).replaceFirst("NN");
        str = PAT_K.matcher(str).replaceFirst("C");
        str = PAT_PH_PF.matcher(str).replaceFirst("FF");
        str = PAT_SCH.matcher(str).replaceFirst("SSS");
        // Translate last characters of name:
        // EE -> Y, IE -> Y, DT | RT | RD | NT | ND -> D
        str = PAT_EE_IE.matcher(str).replaceFirst("Y");
        str = PAT_DT_ETC.matcher(str).replaceFirst("D");
        // First character of key = first character of name.
        final StringBuilder key = new StringBuilder(str.length());
        key.append(str.charAt(0));
        // Transcode remaining characters, incrementing by one character each time
        final char[] chars = str.toCharArray();
        final int len = chars.length;
        for (int i = 1; i < len; i++) {
            final char next = (i < (len - 1)) ? chars[i + 1] : SPACE;
            final char aNext = (i < (len - 2)) ? chars[i + 2] : SPACE;
            final char[] transcoded = transcodeRemaining(chars[i - 1], chars[i], next, aNext);
            System.arraycopy(transcoded, 0, chars, i, transcoded.length);
            // only append the current char to the key if it is different from the last one
            if (chars[i] != chars[i - 1]) {
                key.append(chars[i]);
            }
        }
        if (key.length() > 1) {
            char lastChar = key.charAt(key.length() - 1);
            // If last character is S, remove it.
            if (lastChar == 'S') {
                key.deleteCharAt(key.length() - 1);
                lastChar = key.charAt(key.length() - 1);
            }
            if (key.length() > 2) {
                final char last2Char = key.charAt(key.length() - 2);
                // If last characters are AY, replace with Y.
                if ((last2Char == 'A') && (lastChar == 'Y')) {
                    key.deleteCharAt(key.length() - 2);
                }
            }
            // If last character is A, remove it.
            if (lastChar == 'A') {
                key.deleteCharAt(key.length() - 1);
            }
        }
        final String string = key.toString();
        return this.isStrict() ? string.substring(0, Math.min(TRUE_LENGTH, string.length())) : string;
    }
@Override
public Object encode(final Object obj) throws EncoderException;
    @Override
public String encode(final String str);
    public boolean isStrict();
    private static char[] transcodeRemaining(final char prev, final char curr, final char next, final char aNext);
}
```
@input{existing test case}
here's the existing test cases:
```json
<cases_json>
```

@output{test cases}: output the test cases in the JSON format: