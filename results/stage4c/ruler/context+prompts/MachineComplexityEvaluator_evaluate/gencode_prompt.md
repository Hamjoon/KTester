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
Here is the code of focal method, `evaluate(ByteState)`, with the focal class `MachineComplexityEvaluator`:
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
    private final int maxComplexity;
    int evaluate(ByteState state) {
        // Upfront cost: generate the map of all matches accessible from every state in the machine.
        Map<SingleByteTransition, Set<ByteMatch>> matchesAccessibleFromEachTransition = getMatchesAccessibleFromEachTransition(state);
        Set<ByteTransition> visited = new HashSet<>();
        visited.add(state);
        int maxSize = 0;
        // We'll do a breadth-first-search but it shouldn't matter.
        Queue<ByteTransition> transitions = new LinkedList<>(state.getTransitions());
        while (!transitions.isEmpty()) {
            ByteTransition transition = transitions.remove();
            if (visited.contains(transition)) {
                continue;
            }
            visited.add(transition);
            // The sum of all the wildcard patterns accessible from each SingleByteTransition we are present in on our
            // current traversal is the number of wildcard rule prefixes matching a theoretical worst-case input value.
            int size = 0;
            for (SingleByteTransition single : transition.expand()) {
                size += getWildcardPatterns(matchesAccessibleFromEachTransition.get(single)).size();
                // Look for "transitions for all bytes" (i.e. wildcard transitions). Since an input value that matches
                // foo will also match foo*, we also need to include in our size wildcard patterns accessible from foo*.
                ByteState nextState = single.getNextByteState();
                if (nextState != null) {
                    Set<SingleByteTransition> transitionsForAllBytes = nextState.getTransitionForAllBytes().expand();
                    for (SingleByteTransition transitionForAllBytes : transitionsForAllBytes) {
                        if ((!(transitionForAllBytes instanceof ByteMachine.EmptyByteTransition)) && (!transition.expand().contains(transitionForAllBytes))) {
                            size += getWildcardPatterns(matchesAccessibleFromEachTransition.get(transitionForAllBytes)).size();
                        }
                    }
                }
            }
            if (size >= maxComplexity) {
                return maxComplexity;
            }
            if (size > maxSize) {
                maxSize = size;
            }
            // Load up our queue with the next round of transitions, where each transition represents a set of states
            // that could be accessed with a particular byte value.
            ByteTransition nextTransition = transition.getTransitionForNextByteStates();
            if (nextTransition != null) {
                transitions.addAll(nextTransition.getTransitions());
            }
        } 
        // Now that we have a maxSize for this ByteMachine, let's recursively get the maxSize for each next NameState
        // accessible via any of this ByteMachine's matches. We will return the maximum maxSize.
        int maxSizeFromNextNameStates = 0;
        Set<ByteMatch> uniqueMatches = new HashSet<>();
        for (Set<ByteMatch> matches : matchesAccessibleFromEachTransition.values()) {
            uniqueMatches.addAll(matches);
        }
        for (ByteMatch match : uniqueMatches) {
            NameState nextNameState = match.getNextNameState();
            if (nextNameState != null) {
                maxSizeFromNextNameStates = Math.max(maxSizeFromNextNameStates, nextNameState.evaluateComplexity(this));
            }
        }
        return Math.max(maxSize, maxSizeFromNextNameStates);
    }
private Map<SingleByteTransition, Set<ByteMatch>> getMatchesAccessibleFromEachTransition(ByteState state);
    int getMaxComplexity();
    private static Set<Patterns> getWildcardPatterns(Set<ByteMatch> matches);
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

api document of class software.amazon.event.ruler.MachineComplexityEvaluator: 

 * Evaluates the complexity of machines.
 

api document of method evaluate(ByteState): 

     * Returns the maximum possible number of wildcard rule prefixes that could match a theoretical input value for a
     * machine beginning with ByteState state. This value is equivalent to the maximum number of states a traversal
     * could be present in simultaneously, counting only states that can lead to a wildcard match pattern. This function
     * will recursively evaluate all other machines accessible via next NameStates, and will return the maximum observed
     * from any machine. Caps out evaluation at maxComplexity to keep runtime under control. Otherwise, runtime for this
     * machine would be O(MN^2), where N is the number of states accessible from ByteState state, and M is the total
     * number of ByteMachines accessible via next NameStates.
     *
     * @param state Evaluates a machine beginning at this state.
     * @return The lesser of maxComplexity and the maximum possible number of wildcard rule prefixes from any machines.
     

return type: 
int

dependent classes: 
class `software.amazon.event.ruler.ByteState`:
	
class `software.amazon.event.ruler.MachineComplexityEvaluator`:
	dependent fields:
		int software.amazon.event.ruler.MachineComplexityEvaluator.maxComplexity;
	related functions:
		method ` int getMaxComplexity()` returns `int`, related with `software.amazon.event.ruler.MachineComplexityEvaluator.evaluate(ByteState)`;
		method `private Map<SingleByteTransition, Set<ByteMatch>> getMatchesAccessibleFromEachTransition(ByteState)` returns `java.util.Map<software.amazon.event.ruler.SingleByteTransition, java.util.Set<software.amazon.event.ruler.ByteMatch>>`, related with `software.amazon.event.ruler.MachineComplexityEvaluator.evaluate(ByteState)`, api document: 
     * Generates a map of SingleByteTransition to all the matches accessible from the SingleByteTransition. The map
     * includes all SingleByteTransitions accessible from ByteState state. This function is O(N), where N is the number
     * of states accessible from ByteState state.
     *
     * @param state Starting state.
     * @return A map of SingleByteTransition to all the matches accessible from the SingleByteTransition
     ;
class `software.amazon.event.ruler.ByteMachine`:
	related functions:
		method `private NameState findMatchPattern(InputCharacter[], Patterns)` returns `software.amazon.event.ruler.NameState`, related with `software.amazon.event.ruler.MachineComplexityEvaluator.evaluate(ByteState)`;
		method `private void doTransitionOn(String, Set<NameState>, boolean)` returns `void`, related with `software.amazon.event.ruler.MachineComplexityEvaluator.evaluate(ByteState)`;
class `software.amazon.event.ruler.CompoundByteTransition`:
	related functions:
		method `public Set<ByteTransition> getTransitions()` returns `java.util.Set<software.amazon.event.ruler.ByteTransition>`, related with `software.amazon.event.ruler.MachineComplexityEvaluator.evaluate(ByteState)`;
class `software.amazon.event.ruler.ByteTransition`:
	related functions:
		method `public void gatherObjects(Set<Object>)` returns `void`, related with `software.amazon.event.ruler.MachineComplexityEvaluator.evaluate(ByteState)`;
class `software.amazon.event.ruler.NameState`:
	related functions:
		method ` Set<NameState> getNameTransitions(String[])` returns `java.util.Set<software.amazon.event.ruler.NameState>`, related with `software.amazon.event.ruler.MachineComplexityEvaluator.evaluate(ByteState)`;
		method ` Set<NameState> getNameTransitions(Event, ArrayMembership)` returns `java.util.Set<software.amazon.event.ruler.NameState>`, related with `software.amazon.event.ruler.MachineComplexityEvaluator.evaluate(ByteState)`;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.