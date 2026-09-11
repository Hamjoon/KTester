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
Here is the code of focal method, `deleteStep(NameState, List<String>, int, Map<String, List<Patterns>>, T, List<String>)`, with the focal class `GenericMachine_deleteStep_Test`:
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
 

constructors for class `software.amazon.event.ruler.GenericMachine`: 
params: 
body:
```java
    public GenericMachine() {}

```

parameters in constructors and focal method: 
class `software.amazon.event.ruler.NameState`:
api document : 
 * Represents a state in the machine.
 *
 * The "valueTransitions" map is keyed by field name and yields a ByteMachine
 * that is used to match values.
 *
 * The "keyTransitions" map is keyed by field name and yields a NameMatcher
 * that is used to match keys for [ { exists: false } ].
 
constructor:
```java
```

invoke examples of focal method: 
example 1:
```java
    public void deletePatternRule(final T name, final Map<String, List<Patterns>> namevals) {
        if (namevals.size() > MAXIMUM_RULE_SIZE) {
            throw new RuntimeException("Size of rule '" + name + "' exceeds max value of " + MAXIMUM_RULE_SIZE);
        }
        final List<String> keys = new ArrayList<>(namevals.keySet());
        Collections.sort(keys);
        synchronized(this) {
            final List<String> deletedKeys =  new ArrayList<>();
            deleteStep(getStartState(), keys, 0, namevals, name, deletedKeys);
    }
```
example 2:
```java
    public void deletePatternRule(final T name, final Map<String, List<Patterns>> namevals) {
        if (namevals.size() > MAXIMUM_RULE_SIZE) {
            throw new RuntimeException("Size of rule '" + name + "' exceeds max value of " + MAXIMUM_RULE_SIZE);
        }
        final List<String> keys = new ArrayList<>(namevals.keySet());
        Collections.sort(keys);
        synchronized(this) {
            final List<String> deletedKeys =  new ArrayList<>();
            deleteStep(getStartState(), keys, 0, namevals, name, deletedKeys);
    }
    private void deleteStep(final NameState state,
                            final List<String> keys,
                            final int keyIndex,
                            final Map<String, List<Patterns>> patterns,
                            final T ruleName,
                            List<String> deletedKeys) {

        final String key = keys.get(keyIndex);
        ByteMachine byteMachine = state.getTransitionOn(key);
        NameMatcher<NameState> nameMatcher = state.getKeyTransitionOn(key);
        if (byteMachine == null && nameMatcher == null) {
        for (Patterns pattern : patterns.get(key)) {
            if (nextNameState != null) {
                final int nextKeyIndex = keyIndex + 1;
                if (nextKeyIndex == keys.size()) {
                    deleteStep(nextNameState, keys, nextKeyIndex, patterns, ruleName, deletedKeys);
    }
```
example 3:
```java
    private void deleteStep(final NameState state,
                            final List<String> keys,
                            final int keyIndex,
                            final Map<String, List<Patterns>> patterns,
                            final T ruleName,
                            List<String> deletedKeys) {

        final String key = keys.get(keyIndex);
        ByteMachine byteMachine = state.getTransitionOn(key);
        NameMatcher<NameState> nameMatcher = state.getKeyTransitionOn(key);
        if (byteMachine == null && nameMatcher == null) {
        for (Patterns pattern : patterns.get(key)) {
            if (nextNameState != null) {
                final int nextKeyIndex = keyIndex + 1;
                if (nextKeyIndex == keys.size()) {
                    deleteStep(nextNameState, keys, nextKeyIndex, patterns, ruleName, deletedKeys);
    }
    private void deleteStep(final NameState state,
                            final List<String> keys,
                            final int keyIndex,
                            final Map<String, List<Patterns>> patterns,
                            final T ruleName,
                            List<String> deletedKeys) {

        final String key = keys.get(keyIndex);
        ByteMachine byteMachine = state.getTransitionOn(key);
        NameMatcher<NameState> nameMatcher = state.getKeyTransitionOn(key);
        if (byteMachine == null && nameMatcher == null) {
        for (Patterns pattern : patterns.get(key)) {
            if (nextNameState != null) {
                final int nextKeyIndex = keyIndex + 1;
                if (nextKeyIndex == keys.size()) {
                    deleteStep(nextNameState, keys, nextKeyIndex, patterns, ruleName, deletedKeys);
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
class GenericMachine_deleteStep_Test {
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