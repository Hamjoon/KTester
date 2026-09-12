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
Here is the code of focal method, `getMatchesAccessibleFromEachTransition(ByteState)`, with the focal class `MachineComplexityEvaluator_getMatchesAccessibleFromEachTransition_Test`:
```Java
package software.amazon.event.ruler;
import static software.amazon.event.ruler.MatchType.WILDCARD;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Queue;
import java.util.Stack;
public class MachineComplexityEvaluator {
    
    private Map<SingleByteTransition, Set<ByteMatch>> getMatchesAccessibleFromEachTransition(ByteState state) {
        Map<SingleByteTransition, Set<ByteMatch>> result = new HashMap<>();
        Set<SingleByteTransition> visited = new HashSet<>();
        Stack<SingleByteTransition> stack = new Stack<>();
        stack.push(state);
        // We'll do a depth-first-search as a state's matches can only be computed once the computation is complete for
        // all deeper states. Let's avoid recursion, which is prone to stack overflow.
        while (!stack.isEmpty()) {
            // Peek instead of pop. Need this transition to remain on stack so we can compute its matches once all
            // deeper states are complete.
            SingleByteTransition transition = stack.peek();
            if (!result.containsKey(transition)) {
                result.put(transition, new HashSet<>());
            }
            Set<ByteMatch> matches = result.get(transition);
            // Visited means we have already processed this transition once (via peeking) and have since computed the
            // matches for all deeper states. Time to compute this transition's matches then pop it from the stack.
            if (visited.contains(transition)) {
                ByteState nextState = transition.getNextByteState();
                if (nextState != null) {
                    for (ByteTransition eachTransition : nextState.getTransitions()) {
                        for (SingleByteTransition single : eachTransition.expand()) {
                            matches.addAll(result.get(single));
                        }
                    }
                }
                stack.pop();
                continue;
            }
            visited.add(transition);
            // Add all matches directly accessible from this transition.
            matches.addAll(transition.getMatches());
            // Push the next round of deeper states into the stack. By the time we return back to the current transition
            // on the stack, all matches for deeper states will have been computed.
            ByteState nextState = transition.getNextByteState();
            if (nextState != null) {
                for (ByteTransition eachTransition : nextState.getTransitions()) {
                    for (SingleByteTransition single : eachTransition.expand()) {
                        if (!visited.contains(single)) {
                            stack.push(single);
                        }
                    }
                }
            }
        } 
        return result;
    }
int evaluate(ByteState state);
    int getMaxComplexity();
}
```


@input{context information}

api document of class software.amazon.event.ruler.MachineComplexityEvaluator: 

 * Evaluates the complexity of machines.
 

constructors for class `software.amazon.event.ruler.MachineComplexityEvaluator`: 
params: 
body:
```java
    public MachineComplexityEvaluator(int maxComplexity) {
        this.maxComplexity = maxComplexity;
    }

```

parameters in constructors and focal method: 
class `software.amazon.event.ruler.ByteState`:
api document : 
 * Represents a state in a state machine and maps utf-8 bytes to transitions. One byte can have many transitions,
 * meaning this is an NFA state as opposed to a DFA state.
 
constructor:
```java
```

invoke examples of focal method: 
example 1:
```java
    int evaluate(ByteState state) {
        // Upfront cost: generate the map of all matches accessible from every state in the machine.
        Map<SingleByteTransition, Set<ByteMatch>> matchesAccessibleFromEachTransition =
                getMatchesAccessibleFromEachTransition(state);
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
class MachineComplexityEvaluator_getMatchesAccessibleFromEachTransition_Test {
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