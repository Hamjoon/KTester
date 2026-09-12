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
Here is the code of focal method, `deleteStep(NameState, List<String>, int, Map<String, List<Patterns>>, T, List<String>)`, with the focal class `GenericMachine`:
```Java
package software.amazon.event.ruler;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.io.InputStream;
import java.util.ArrayList;
import java.io.IOException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.core.JsonParseException;
import java.io.Reader;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Collections;
public class GenericMachine<T> {
    
    private void deleteStep(final NameState state, final List<String> keys, final int keyIndex, final Map<String, List<Patterns>> patterns, final T ruleName, List<String> deletedKeys) {
        final String key = keys.get(keyIndex);
        ByteMachine byteMachine = state.getTransitionOn(key);
        NameMatcher<NameState> nameMatcher = state.getKeyTransitionOn(key);
        // matchers are null, we have nothing to delete.
        if ((byteMachine == null) && (nameMatcher == null)) {
            return;
        }
        for (Patterns pattern : patterns.get(key)) {
            NameState nextNameState = null;
            if (isNamePattern(pattern)) {
                if (nameMatcher != null) {
                    nextNameState = nameMatcher.findPattern(pattern);
                }
            } else if (byteMachine != null) {
                nextNameState = byteMachine.findPattern(pattern);
            }
            if (nextNameState != null) {
                // if this was the last step, then reaching the last state means the rule matched.
                final int nextKeyIndex = keyIndex + 1;
                if (nextKeyIndex == keys.size()) {
                    if (nextNameState.hasRule(ruleName)) {
                        nextNameState.deleteRule(ruleName);
                        // only when this state have no rules and no next transition, we could remove it.
                        if (checkAndDeleteNameState(nextNameState, state, key, pattern)) {
                            deletedKeys.add(key);
                        }
                    }
                } else {
                    deleteStep(nextNameState, keys, nextKeyIndex, patterns, ruleName, deletedKeys);
                    // only when this state have no rules and no next transition, we could remove it.
                    if (checkAndDeleteNameState(nextNameState, state, key, pattern)) {
                        deletedKeys.add(key);
                    }
                }
            }
        }
    }
public void addPatternRule(final T name, final Map<String, List<Patterns>> namevals);
    public void addRule(final T name, final byte[] json) throws IOException;
    public void addRule(final T name, final InputStream json) throws IOException;
    public void addRule(final T name, final Reader json) throws IOException;
    public void addRule(final T name, final String json) throws IOException;
    public void addRule(final T name, final Map<String, List<String>> namevals);
    public int approximateObjectCount();
    private boolean checkAndDeleteNameState(final NameState currentNameState, final NameState parentNameState, final String key, Patterns pattern);
    public void deletePatternRule(final T name, final Map<String, List<Patterns>> namevals);
    public void deleteRule(final T name, final InputStream json) throws IOException;
    public void deleteRule(final T name, final Reader json) throws IOException;
    public void deleteRule(final T name, final String json) throws IOException;
    public void deleteRule(final T name, final Map<String, List<String>> namevals);
    public int evaluateComplexity(MachineComplexityEvaluator evaluator);
    final NameState getStartState();
    public boolean isEmpty();
    boolean isFieldStepUsed(final String stepName);
    private boolean isNamePattern(Patterns pattern);
    @SuppressWarnings("unchecked")
public List<T> rulesForEvent(final String[] event);
    @SuppressWarnings("unchecked")
public List<T> rulesForEvent(final List<String> event);
    @SuppressWarnings("unchecked")
public List<T> rulesForJSONEvent(final JsonNode eventRoot);
    @SuppressWarnings("unchecked")
public List<T> rulesForJSONEvent(final String jsonEvent) throws Exception;
    @Override
public String toString();
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

api document of class software.amazon.event.ruler.GenericMachine: 

 *  Represents a state machine used to match name/value patterns to rules.
 *  The machine is thread safe. The concurrency strategy is:
 *  Multi-thread access assumed, single-thread update enforced by synchronized on
 *  addRule/deleteRule.
 *  ConcurrentHashMap and ConcurrentSkipListSet are used so that writer and readers can be in tables
 *  simultaneously. So all changes the writer made could be synced to and viable by all readers (in other threads).
 *  Though it may  generate a half-built rule to rulesForEvent() e.g. when a long rule is adding and
 *  in the middle of adding, some event is coming to query machine, it won't generate side impact with rulesForEvent
 *  because each step of routing will check next State and transition map before moving forward.
 *
 *  T is a type representing a Rule name, it should be an immutable class.
 

dependent classes: 
class `software.amazon.event.ruler.NameState`:
	related functions:
		method ` ByteMachine getTransitionOn(String)` returns `software.amazon.event.ruler.ByteMachine`, related with `software.amazon.event.ruler.GenericMachine.deleteStep(NameState, List<String>, int, Map<String, List<Patterns>>, T, List<String>)`;
class `software.amazon.event.ruler.GenericMachine`:
	related functions:
		method `private void deleteStep(NameState, List<String>, int, Map<String, List<Patterns>>, T, List<String>)` returns `void`, related with `software.amazon.event.ruler.GenericMachine.deleteStep(NameState, List<String>, int, Map<String, List<Patterns>>, T, List<String>)`;
		method `private boolean hasValuePatterns(List<Patterns>)` returns `boolean`, related with `software.amazon.event.ruler.GenericMachine.deleteStep(NameState, List<String>, int, Map<String, List<Patterns>>, T, List<String>)`;
		method `private boolean checkAndDeleteNameState(NameState, NameState, String, Patterns)` returns `boolean`, related with `software.amazon.event.ruler.GenericMachine.deleteStep(NameState, List<String>, int, Map<String, List<Patterns>>, T, List<String>)`;
		method `private void addStep(NameState, List<String>, int, Map<String, List<Patterns>>, T, List<String>)` returns `void`, related with `software.amazon.event.ruler.GenericMachine.deleteStep(NameState, List<String>, int, Map<String, List<Patterns>>, T, List<String>)`;
class `software.amazon.event.ruler.Event`:
	related functions:
		method ` static void recordNameVal(Map<String, List<String>>, Stack<String>, String)` returns `void`, related with `software.amazon.event.ruler.GenericMachine.deleteStep(NameState, List<String>, int, Map<String, List<Patterns>>, T, List<String>)`;
		method `private static List<String> doFlatten(JsonNode)` returns `java.util.List<java.lang.String>`, related with `software.amazon.event.ruler.GenericMachine.deleteStep(NameState, List<String>, int, Map<String, List<Patterns>>, T, List<String>)`;
class `software.amazon.event.ruler.ACFinder`:
	related functions:
		method `private static void tryStep(ACTask, ACStep)` returns `void`, related with `software.amazon.event.ruler.GenericMachine.deleteStep(NameState, List<String>, int, Map<String, List<Patterns>>, T, List<String>)`;
class `software.amazon.event.ruler.RuleCompiler.ListBasedRuleCompiler`:
	related functions:
		method `private static List<String> rulePathname(Deque<String>, String)` returns `java.util.List<java.lang.String>`, related with `software.amazon.event.ruler.GenericMachine.deleteStep(NameState, List<String>, int, Map<String, List<Patterns>>, T, List<String>)`;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.