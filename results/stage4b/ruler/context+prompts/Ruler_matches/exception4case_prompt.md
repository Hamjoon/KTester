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
Here is the code of focal method, `matches(JsonNode, Patterns)`, with the focal class `Ruler`:
```Java
package software.amazon.event.ruler;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import javax.annotation.concurrent.ThreadSafe;
import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.Map;
import javax.annotation.concurrent.Immutable;
import com.fasterxml.jackson.databind.ObjectMapper;
@ThreadSafe
@Immutable
public class Ruler {
    
    private static boolean matches(final JsonNode val, final Patterns pattern) {
        switch (pattern.type()) {
            case EXACT :
                ValuePatterns valuePattern = ((ValuePatterns) (pattern));
                // if it's a string we match the "-quoted form, otherwise (true, false, null) as-is.
                final String compareTo = (val.isTextual()) ? ('"' + val.asText()) + '"' : val.asText();
                return compareTo.equals(valuePattern.pattern());
            case PREFIX :
                valuePattern = ((ValuePatterns) (pattern));
                return val.isTextual() && ('"' + val.asText()).startsWith(valuePattern.pattern());
            case ANYTHING_BUT :
                assert pattern instanceof AnythingBut;
                AnythingBut anythingButPattern = ((AnythingBut) (pattern));
                if (val.isTextual()) {
                    return anythingButPattern.getValues().stream().noneMatch(v -> v.equals(('"' + val.asText()) + '"'));
                } else if (val.isNumber()) {
                    return anythingButPattern.getValues().stream().noneMatch(v -> v.equals(ComparableNumber.generate(val.asDouble())));
                }
                return false;
            case ANYTHING_BUT_IGNORE_CASE :
                assert pattern instanceof AnythingButEqualsIgnoreCase;
                AnythingButEqualsIgnoreCase anythingButIgnoreCasePattern = ((AnythingButEqualsIgnoreCase) (pattern));
                if (val.isTextual()) {
                    return anythingButIgnoreCasePattern.getValues().stream().noneMatch(v -> v.equalsIgnoreCase(('"' + val.asText()) + '"'));
                }
                return false;
            case ANYTHING_BUT_SUFFIX :
                valuePattern = ((ValuePatterns) (pattern));
                return !(val.isTextual() && (val.asText() + '"').startsWith(valuePattern.pattern()));
            case ANYTHING_BUT_PREFIX :
                valuePattern = ((ValuePatterns) (pattern));
                return !(val.isTextual() && ('"' + val.asText()).startsWith(valuePattern.pattern()));
            case NUMERIC_EQ :
                valuePattern = ((ValuePatterns) (pattern));
                return val.isNumber() && ComparableNumber.generate(val.asDouble()).equals(valuePattern.pattern());
            case EXISTS :
                return true;
            case ABSENT :
                return false;
            case NUMERIC_RANGE :
                final Range nr = ((Range) (pattern));
                byte[] bytes;
                if (nr.isCIDR) {
                    if (!val.isTextual()) {
                        return false;
                    }
                    try {
                        bytes = CIDR.ipToString(val.asText()).getBytes(StandardCharsets.UTF_8);
                    } catch (Exception e) {
                        return false;
                    }
                } else {
                    if (!val.isNumber()) {
                        return false;
                    }
                    bytes = ComparableNumber.generate(val.asDouble()).getBytes(StandardCharsets.UTF_8);
                }
                final int comparedToBottom = compare(bytes, nr.bottom);
                if ((comparedToBottom > 0) || ((comparedToBottom == 0) && (!nr.openBottom))) {
                    final int comparedToTop = compare(bytes, nr.top);
                    return (comparedToTop < 0) || ((comparedToTop == 0) && (!nr.openTop));
                }
                return false;
            case EQUALS_IGNORE_CASE :
                valuePattern = ((ValuePatterns) (pattern));
                return val.isTextual() && (('"' + val.asText()) + '"').equalsIgnoreCase(valuePattern.pattern());
            case WILDCARD :
                valuePattern = ((ValuePatterns) (pattern));
                return val.isTextual() && (('"' + val.asText()) + '"').matches(valuePattern.pattern().replaceAll("\\*", ".*"));
            default :
                throw new RuntimeException("Unsupported Pattern type " + pattern.type());
        }
    }
static int compare(final byte[] a, final byte[] b);
    public static boolean matchesRule(final String event, final String rule) throws Exception;
    static JsonNode tryToRetrievePath(JsonNode node, final List<String> path);
}
```
@input{existing test case}
here's the existing test cases:
```json
<cases_json>
```

@output{test cases}: output the test cases in the JSON format: