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
Here is the code of focal method, `matches(JsonNode, Patterns)`, with the focal class `Ruler_matches_Test`:
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
 

constructors for class `software.amazon.event.ruler.Ruler`: 
params: 
body:
```java
    private Ruler() { }

```

parameters in constructors and focal method: 
class `software.amazon.event.ruler.Patterns`:
api document : 
 * The Patterns deal pre-processing of rules for the eventual matching against events.
 * It has subclasses for different match types (like ValuePatterns, Ranges, AnythingBut, etc).
 * This class also acts as the factory for to build patterns which is useful if you have
 * key value pairs and would like to build your rules for them directly instead of using the fancy
 * JSON query language (and its compiler). Once you build rules, you can add them via `Machine.addRule()`.
 *
 * NOTE: The subclasses have additional builders that are only needed by JSON rule compilier and are
 * left out of here intentionally for now.
 
constructor:
```java
    Patterns(final MatchType type) {
        this.type = type;
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
class Ruler_matches_Test {
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