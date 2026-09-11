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

api document of class software.amazon.event.ruler.Ruler: 

 * The core idea of Ruler is to match rules to events at a rate that's independent of the number of rules.  This is
 *  achieved by compiling the rules into an automaton, at some up-front cost for compilation and memory use. There
 *  are some users who are unable to persist the compiled automaton but still like the "Event Pattern" idiom and want
 *  to match events with those semantics.
 * The memory cost is proportional to the product of the number of possible values provided in the rule and can
 *  grow surprisingly large
 * This class matches a single rule to a single event without any precompilation, using brute-force techniques, but
 *  with no up-front compute or memory cost.
 

return type: 
boolean

dependent classes: 
class `software.amazon.event.ruler.Patterns`:
	
class `software.amazon.event.ruler.MatchType`:
	dependent fields:
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.ANYTHING_BUT;
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.EXACT;
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.WILDCARD;
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.EXISTS;
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.NUMERIC_EQ;
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.EQUALS_IGNORE_CASE;
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.NUMERIC_RANGE;
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.ABSENT;
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.ANYTHING_BUT_IGNORE_CASE;
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.PREFIX;
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.ANYTHING_BUT_SUFFIX;
		software.amazon.event.ruler.MatchType software.amazon.event.ruler.MatchType.ANYTHING_BUT_PREFIX;
	
class `software.amazon.event.ruler.Range`:
	dependent fields:
		boolean software.amazon.event.ruler.Range.openBottom;
		boolean software.amazon.event.ruler.Range.openTop;
		byte[] software.amazon.event.ruler.Range.bottom;
		boolean software.amazon.event.ruler.Range.isCIDR;
		byte[] software.amazon.event.ruler.Range.top;
	related functions:
		method `public boolean equals(Object)` returns `boolean`, related with `software.amazon.event.ruler.Ruler.matches(JsonNode, Patterns)`;
		method `public String toString()` returns `java.lang.String`, related with `software.amazon.event.ruler.Ruler.matches(JsonNode, Patterns)`;
class `software.amazon.event.ruler.ByteMachine`:
	related functions:
		method ` NameState findPattern(Patterns)` returns `software.amazon.event.ruler.NameState`, related with `software.amazon.event.ruler.Ruler.matches(JsonNode, Patterns)`;
		method `private void addMatchReferences(ByteMatch)` returns `void`, related with `software.amazon.event.ruler.Ruler.matches(JsonNode, Patterns)`;
		method ` void deletePattern(Patterns)` returns `void`, related with `software.amazon.event.ruler.Ruler.matches(JsonNode, Patterns)`;
		method `private void doTransitionOn(String, Set<NameState>, boolean)` returns `void`, related with `software.amazon.event.ruler.Ruler.matches(JsonNode, Patterns)`;
class `software.amazon.event.ruler.input.DefaultParser`:
	related functions:
		method `public InputCharacter[] parse(MatchType, String)` returns `software.amazon.event.ruler.input.InputCharacter[]`, related with `software.amazon.event.ruler.Ruler.matches(JsonNode, Patterns)`;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.