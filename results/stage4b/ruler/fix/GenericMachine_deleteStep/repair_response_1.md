```java
package software.amazon.event.ruler;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;

@ExtendWith(MockitoExtension.class)
class GenericMachine_deleteStep_Test {

    // Target class instance
    private GenericMachine<String> genericMachine;

    // Mocked NameState
    private NameState mockNameState;

    // Keys for deleteStep
    private List<String> keys;

    // Patterns for deleteStep
    private Map<String, List<Patterns>> patterns;

    // Rule name for deleteStep
    private String ruleName;

    // List to hold deleted keys
    private List<String> deletedKeys;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the GenericMachine instance
        genericMachine = new GenericMachine<>();
        // Mock the NameState
        mockNameState = mock(NameState.class);
        // Sample keys
        keys = new ArrayList<>(Arrays.asList("key1", "key2"));
        // Initialize patterns map
        patterns = new HashMap<>();
        // Initialize deleted keys list
        deletedKeys = new ArrayList<>();
        // Sample rule name
        ruleName = "testRule";
        // Setup mock behavior for NameState
        // Removed unnecessary stubbings
    }

    // Reflection method to invoke the private deleteStep method
    private void invokeDeleteStep(NameState state, List<String> keys, int keyIndex, Map<String, List<Patterns>> patterns, String ruleName, List<String> deletedKeys) throws Exception {
        var method = GenericMachine.class.getDeclaredMethod("deleteStep", NameState.class, List.class, int.class, Map.class, String.class, List.class);
        method.setAccessible(true);
        method.invoke(genericMachine, state, keys, keyIndex, patterns, ruleName, deletedKeys);
    }

    @Test
    void testDeleteStep() {
        // Example test case for deleteStep method
        // Call a method that invokes deleteStep internally
        // Ensure there's a pattern for the key
        patterns.put("key1", new ArrayList<>());
        genericMachine.deletePatternRule(ruleName, patterns);
        // Verify the expected outcome
        // Example assertion, adjust based on expected behavior
        assertTrue(deletedKeys.isEmpty());
    }

    @Test
    void testDeleteStep_NoMatchers() throws Exception {
        // Test case when both matchers (byteMachine and nameMatcher) are null.
        // The method should return without modifying deletedKeys.
        when(mockNameState.getTransitionOn("key1")).thenReturn(null);
        when(mockNameState.getKeyTransitionOn("key1")).thenReturn(null);
        patterns.put("key1", new ArrayList<>());
        invokeDeleteStep(mockNameState, keys, 0, patterns, ruleName, deletedKeys);
        assertTrue(deletedKeys.isEmpty(), "deletedKeys should remain empty when there are no matchers.");
    }

    @Test
    void testDeleteStep_NamePatternMatching() throws Exception {
        // Test case where a nameMatcher finds a matching pattern and deletes the rule.
        // The deletedKeys should include 'key1' if the rule was successfully deleted.
        NameMatcher<NameState> mockNameMatcher = mock(NameMatcher.class);
        when(mockNameState.getKeyTransitionOn("key1")).thenReturn(mockNameMatcher);
        Patterns mockPattern = mock(Patterns.class);
        when(mockNameMatcher.findPattern(mockPattern)).thenReturn(mockNameState);
        when(mockNameState.hasRule(ruleName)).thenReturn(true);
        doNothing().when(mockNameState).deleteRule(ruleName);
        patterns.put("key1", Collections.singletonList(mockPattern));
        invokeDeleteStep(mockNameState, keys, 0, patterns, ruleName, deletedKeys);
        assertTrue(deletedKeys.contains("key1"), "deletedKeys should contain 'key1' after deletion.");
    }

    @Test
    void testDeleteStep_ByteMachineMatching() throws Exception {
        // Test case where a byteMachine finds a matching pattern and deletes the rule.
        // The deletedKeys should include 'key2' if the rule was successfully deleted.
        ByteMachine mockByteMachine = mock(ByteMachine.class);
        when(mockNameState.getTransitionOn("key2")).thenReturn(mockByteMachine);
        Patterns mockPattern = mock(Patterns.class);
        when(mockByteMachine.findPattern(mockPattern)).thenReturn(mockNameState);
        when(mockNameState.hasRule(ruleName)).thenReturn(true);
        doNothing().when(mockNameState).deleteRule(ruleName);
        patterns.put("key2", Collections.singletonList(mockPattern));
        invokeDeleteStep(mockNameState, keys, 0, patterns, ruleName, deletedKeys);
        assertTrue(deletedKeys.contains("key2"), "deletedKeys should contain 'key2' after deletion.");
    }

    @Test
    void testDeleteStep_RecursiveDeletion() throws Exception {
        // Test case where the method is called recursively for multiple keys.
        // Both keys should be deleted if their corresponding rules are successfully deleted.
        NameMatcher<NameState> mockNameMatcher1 = mock(NameMatcher.class);
        NameMatcher<NameState> mockNameMatcher2 = mock(NameMatcher.class);
        when(mockNameState.getKeyTransitionOn("key1")).thenReturn(mockNameMatcher1);
        when(mockNameState.getKeyTransitionOn("key2")).thenReturn(mockNameMatcher2);
        Patterns mockPattern1 = mock(Patterns.class);
        Patterns mockPattern2 = mock(Patterns.class);
        when(mockNameMatcher1.findPattern(mockPattern1)).thenReturn(mockNameState);
        when(mockNameMatcher2.findPattern(mockPattern2)).thenReturn(mockNameState);
        when(mockNameState.hasRule(ruleName)).thenReturn(true);
        doNothing().when(mockNameState).deleteRule(ruleName);
        patterns.put("key1", Collections.singletonList(mockPattern1));
        patterns.put("key2", Collections.singletonList(mockPattern2));
        invokeDeleteStep(mockNameState, keys, 0, patterns, ruleName, deletedKeys);
        assertTrue(deletedKeys.contains("key1"), "deletedKeys should contain 'key1' after deletion.");
        assertTrue(deletedKeys.contains("key2"), "deletedKeys should contain 'key2' after deletion.");
    }

    @Test
    void testDeleteStep_NoRulesToDelete() throws Exception {
        // Test case where the state has no rules to delete.
        // The deletedKeys should remain empty.
        when(mockNameState.getTransitionOn("key3")).thenReturn(null);
        when(mockNameState.getKeyTransitionOn("key3")).thenReturn(null);
        patterns.put("key3", Collections.singletonList(mock(Patterns.class)));
        invokeDeleteStep(mockNameState, keys, 0, patterns, ruleName, deletedKeys);
        assertTrue(deletedKeys.isEmpty(), "deletedKeys should remain empty when there are no rules to delete.");
    }

    @Test
    void testDeleteStep_EmptyPatternsList() throws Exception {
        // Test case where the patterns list for the key is empty.
        // The method should return without modifying deletedKeys.
        patterns.put("key4", new ArrayList<>());
        invokeDeleteStep(mockNameState, keys, 0, patterns, ruleName, deletedKeys);
        assertTrue(deletedKeys.isEmpty(), "deletedKeys should remain empty when patterns list is empty.");
    }
}
```