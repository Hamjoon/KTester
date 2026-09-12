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
Here is the code of focal method, `writeRules(List<Map<String, List<Patterns>>>, String, JsonParser, boolean)`, with the focal class `JsonRuleCompiler`:
```Java
package software.amazon.event.ruler;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.HashMap;
import java.io.InputStream;
import java.util.ArrayList;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import java.io.Reader;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.List;
import java.util.Map;
import java.util.HashSet;
public class JsonRuleCompiler {
    
    private static void writeRules(final List<Map<String, List<Patterns>>> rules, final String name, final JsonParser parser, final boolean withQuotes) throws IOException {
        JsonToken token;
        final List<Patterns> values = new ArrayList<>();
        while ((token = parser.nextToken()) != JsonToken.END_ARRAY) {
            switch (token) {
                case START_OBJECT :
                    values.add(processMatchExpression(parser));
                    break;
                case VALUE_STRING :
                    final String toMatch = parser.getText();
                    final Range ipRange = CIDR.ipToRangeIfPossible(toMatch);
                    if (ipRange != null) {
                        values.add(ipRange);
                    } else if (withQuotes) {
                        values.add(Patterns.exactMatch(('"' + toMatch) + '"'));
                    } else {
                        values.add(Patterns.exactMatch(toMatch));
                    }
                    break;
                case VALUE_NUMBER_FLOAT :
                case VALUE_NUMBER_INT :
                    /* If the rule specifies a match to a number, we'll insert matchers for both the
                     literal expression and the ComparableNumber form. But the number might not
                     be representable as a ComparableNumber, for example an AWS account number,
                     so make that condition survivable.
                     */
                    try {
                        values.add(Patterns.numericEquals(parser.getDoubleValue()));
                    } catch (Exception e) {
                        // no-op
                    }
                    values.add(Patterns.exactMatch(parser.getText()));
                    break;
                case VALUE_NULL :
                case VALUE_TRUE :
                case VALUE_FALSE :
                    values.add(Patterns.exactMatch(parser.getText()));
                    break;
                default :
                    barf(parser, "Match value must be String, number, true, false, or null");
            }
        } 
        if (values.isEmpty()) {
            barf(parser, "Empty arrays are not allowed");
        }
        // If the rules list is empty, add the first rule
        if (rules.isEmpty()) {
            rules.add(new HashMap<>());
        }
        rules.forEach(rule -> rule.put(name, values));
    }
private static void barf(final JsonParser parser, final String message) throws JsonParseException;
    public static String check(final byte[] source);
    public static String check(final InputStream source);
    public static String check(final Reader source);
    public static String check(final String source);
    public static List<Map<String, List<Patterns>>> compile(final byte[] source) throws IOException;
    public static List<Map<String, List<Patterns>>> compile(final InputStream source) throws IOException;
    public static List<Map<String, List<Patterns>>> compile(final Reader source) throws IOException;
    public static List<Map<String, List<Patterns>>> compile(final String source) throws IOException;
    private static Patterns processMatchExpression(final JsonParser parser) throws IOException;
}
```
@input{existing test case}
here's the existing test cases:
```json
<cases_json>
```

@output{test cases}: output the test cases in the JSON format: