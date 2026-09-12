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

api document of class software.amazon.event.ruler.JsonRuleCompiler: 

 * Represents a updated compiler comparing to RuleCompiler class, it parses a rule described by a JSON string into
 * a list of Map which is composed of field Patterns, each Map represents one dedicated match branch in the rule.
 * By default, fields in the rule will be interpreted as "And" relationship implicitly and will result into one Map with
 * fields of Patterns, but once it comes across the "$or" relationship which is explicitly decorated by "$or" primitive
 * array, for each object in "$or" array, this Compiler will fork a new Map to include all fields of patterns in
 * that "or" branch, and when it goes over all branches in "$or" relationship, it will end up with the list of Map
 * and each Map represents a match branch composed of fields of patterns.
 * i.e.: a rule which describe the effect of ("source" AND ("metricName" OR ("metricType AND "namespace") OR "scope"))
 * its JSON format String is:
 *   {
 *     "source": [ "aws.cloudwatch" ],
 *     "$or": [
 *       { "metricName": [ "CPUUtilization", "ReadLatency" ] },
 *       {
 *         "metricType": [ "MetricType" ] ,
 *         "namespace": [ "AWS/EC2", "AWS/ES" ]
 *       },
 *       { "scope": [ "Service" ] }
 *     ]
 *   }
 * It will be parsed to a list of match branches composed by a Map of fields patterns, e.g. for above rule, there will
 * result 3 match branches:
 * [
 *   {source=[\"aws.cloudwatch\"], metricName=[\"CPUUtilization\",\"ReadLatency\"]},
 *   {namespace=[\"AWS/EC2\", \"AWS/ES\"], metricType=[\"MetricType\"], source=[\"aws.cloudwatch\"]},
 *   {source=[\"aws.cloudwatch\"], scope=[\"Service\"]}
 * ]
 

dependent classes: 
class `software.amazon.event.ruler.RuleCompiler`:
	related functions:
		method `private static void writeRules(Map<String, List<Patterns>>, String, JsonParser, boolean)` returns `void`, related with `software.amazon.event.ruler.JsonRuleCompiler.writeRules(List<Map<String, List<Patterns>>>, String, JsonParser, boolean)`;
		method `private static Patterns processAnythingButMatchExpression(JsonParser, JsonToken)` returns `software.amazon.event.ruler.Patterns`, related with `software.amazon.event.ruler.JsonRuleCompiler.writeRules(List<Map<String, List<Patterns>>>, String, JsonParser, boolean)`;
		method `private static Patterns processAnythingButListMatchExpression(JsonParser)` returns `software.amazon.event.ruler.Patterns`, related with `software.amazon.event.ruler.JsonRuleCompiler.writeRules(List<Map<String, List<Patterns>>>, String, JsonParser, boolean)`;
class `software.amazon.event.ruler.RuleCompiler.ListBasedRuleCompiler`:
	related functions:
		method `private static void writeRules(Map<List<String>, List<Patterns>>, List<String>, JsonParser, boolean)` returns `void`, related with `software.amazon.event.ruler.JsonRuleCompiler.writeRules(List<Map<String, List<Patterns>>>, String, JsonParser, boolean)`;
class `software.amazon.event.ruler.JsonRuleCompiler`:
	related functions:
		method `private static Patterns processAnythingButEqualsIgnoreCaseListMatchExpression(JsonParser)` returns `software.amazon.event.ruler.Patterns`, related with `software.amazon.event.ruler.JsonRuleCompiler.writeRules(List<Map<String, List<Patterns>>>, String, JsonParser, boolean)`;
		method `private static Patterns completeNumericRange(JsonParser, JsonToken, double, boolean)` returns `software.amazon.event.ruler.Patterns`, related with `software.amazon.event.ruler.JsonRuleCompiler.writeRules(List<Map<String, List<Patterns>>>, String, JsonParser, boolean)`;
		method `private static Patterns processAnythingButMatchExpression(JsonParser, JsonToken)` returns `software.amazon.event.ruler.Patterns`, related with `software.amazon.event.ruler.JsonRuleCompiler.writeRules(List<Map<String, List<Patterns>>>, String, JsonParser, boolean)`;
		method `private static Patterns processAnythingButListMatchExpression(JsonParser)` returns `software.amazon.event.ruler.Patterns`, related with `software.amazon.event.ruler.JsonRuleCompiler.writeRules(List<Map<String, List<Patterns>>>, String, JsonParser, boolean)`;
		method `private static void parseIntoOrRelationship(List<Map<String, List<Patterns>>>, Path, JsonParser, boolean)` returns `void`, related with `software.amazon.event.ruler.JsonRuleCompiler.writeRules(List<Map<String, List<Patterns>>>, String, JsonParser, boolean)`, api document: 
     * This function is to parse the "$or" relationship described as an array, each object in the array will be
     * interpreted as "$or" relationship, for example:
     * {
     *     "detail": {
     *         "$or" : [
     *             {"c-count": [ { "numeric": [ ">", 0, "<=", 5 ] } ]},
     *             {"d-count": [ { "numeric": [ "<", 10 ] } ]},
     *             {"x-limit": [ { "numeric": [ "=", 3.018e2 ] } ]}
     *         ]
     *     }
     * }
     * above rule will be interpreted as or effect: ("detail.c-count" || "detail.d-count" || detail.x-limit"),
     * The result will be described as the list of Map, each Map represents a sub rule composed of fields of patterns in
     * that or condition path, for example:
     * [
     *   {detail.c-count=[38D7EA4C68000/38D7EA512CB40:true/false]},
     *   {detail.d-count=[0000000000000/38D7EA55F1680:false/true]},
     *   {detail.x-limit=[VP:38D7EB6C39A40]}
     * ]
     * This List will be added into Ruler with the same rule name to demonstrate the "or" effects inside Ruler state
     * machine.
     ;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.