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

    private GenericMachine<String> genericMachine;

    private NameState mockState;

    private Map<String, List<Patterns>> mockPatterns;

    private List<String> deletedKeys;

    @BeforeEach
    void setupBeforeEach() {
        genericMachine = new GenericMachine<>();
        mockState = mock(NameState.class);
        mockPatterns = new HashMap<>();
        deletedKeys = new ArrayList<>();
    }

    // Helper method to invoke the private deleteStep method using reflection
    private void invokeDeleteStep(NameState state, List<String> keys, int keyIndex, Map<String, List<Patterns>> patterns, String ruleName, List<String> deletedKeys) throws Exception {
        java.lang.reflect.Method method = GenericMachine.class.getDeclaredMethod("deleteStep", NameState.class, List.class, int.class, Map.class, String.class, List.class);
        method.setAccessible(true);
        method.invoke(genericMachine, state, keys, keyIndex, patterns, ruleName, deletedKeys);
    }

//     @Test
//     void testDeleteStep() {
//         // Given
//         List<String> keys = Arrays.asList("key1", "key2");
//         Patterns mockPattern = mock(Patterns.class);
//         when(mockPatterns.get("key1")).thenReturn(Collections.singletonList(mockPattern));
//         when(mockPatterns.get("key2")).thenReturn(Collections.singletonList(mockPattern));
//         when(mockState.getTransitionOn("key1")).thenReturn(mock(ByteMachine.class));
//         when(mockState.getKeyTransitionOn("key1")).thenReturn(mock(NameMatcher.class));
//         when(mockState.hasRule("ruleName")).thenReturn(true);
//         doNothing().when(mockState).deleteRule("ruleName");
//         // When
//         invokeDeleteStep(mockState, keys, 0, mockPatterns, "ruleName", deletedKeys);
//         // Then
//         assertTrue(deletedKeys.contains("key1"), "Deleted keys should contain 'key1'.");
//     }

    // Test when both matchers are null, expecting no deletion action.
    @Test
    void testDeleteStep_NoMatchers() throws Exception {
        // Given
        List<String> keys = Collections.singletonList("key1");
        when(mockState.getTransitionOn("key1")).thenReturn(mock(ByteMachine.class));
        when(mockState.getKeyTransitionOn("key1")).thenReturn(null);
        // When
        invokeDeleteStep(mockState, keys, 0, new HashMap<>(), "mocked RuleName", deletedKeys);
        // Then
        assertTrue(deletedKeys.isEmpty(), "Deleted keys should remain empty.");
    }

    // Test when a valid pattern is found in NameMatcher and the rule is deleted.
    @Test
    void testDeleteStep_PatternFoundInNameMatcher() throws Exception {
        // Given
        List<String> keys = Collections.singletonList("key1");
        Patterns mockPattern = mock(Patterns.class);
        when(mockPatterns.get("key1")).thenReturn(Collections.singletonList(mockPattern));
        NameMatcher<NameState> mockNameMatcher = mock(NameMatcher.class);
        when(mockState.getKeyTransitionOn("key1")).thenReturn(mockNameMatcher);
        when(mockNameMatcher.findPattern(mockPattern)).thenReturn(mockState);
        when(mockState.hasRule("mocked RuleName")).thenReturn(true);
        doNothing().when(mockState).deleteRule("mocked RuleName");
        // When
        invokeDeleteStep(mockState, keys, 0, mockPatterns, "mocked RuleName", deletedKeys);
        // Then
        assertTrue(deletedKeys.contains("key1"), "Deleted keys should contain 'key1'.");
    }

    // Test when a valid pattern is found in ByteMachine and the rule is deleted.
    @Test
    void testDeleteStep_PatternFoundInByteMachine() throws Exception {
        // Given
        List<String> keys = Collections.singletonList("key1");
        Patterns mockPattern = mock(Patterns.class);
        when(mockPatterns.get("key1")).thenReturn(Collections.singletonList(mockPattern));
        ByteMachine mockByteMachine = mock(ByteMachine.class);
        when(mockState.getTransitionOn("key1")).thenReturn(mockByteMachine);
        when(mockByteMachine.findPattern(mockPattern)).thenReturn(mockState);
        when(mockState.hasRule("mocked RuleName")).thenReturn(true);
        doNothing().when(mockState).deleteRule("mocked RuleName");
        // When
        invokeDeleteStep(mockState, keys, 0, mockPatterns, "mocked RuleName", deletedKeys);
        // Then
        assertTrue(deletedKeys.contains("key1"), "Deleted keys should contain 'key1'.");
    }

    // Test when reaching the last state and there are no rules to delete, expecting deletion of key.
    @Test
    void testDeleteStep_LastStepWithNoRules() throws Exception {
        // Given
        List<String> keys = Arrays.asList("key1", "key2");
        Patterns mockPattern = mock(Patterns.class);
        when(mockPatterns.get("key2")).thenReturn(Collections.singletonList(mockPattern));
        when(mockState.hasRule("mocked RuleName")).thenReturn(false);
        // Simulate transitions
        when(mockState.getTransitionOn("key1")).thenReturn(mock(ByteMachine.class));
        when(mockState.getKeyTransitionOn("key1")).thenReturn(mock(NameMatcher.class));
        // When
        invokeDeleteStep(mockState, keys, 1, mockPatterns, "mocked RuleName", deletedKeys);
        // Then
        assertTrue(deletedKeys.contains("key2"), "Deleted keys should contain 'key2'.");
    }

    // Test when multiple keys lead to recursive calls, expecting both keys to be deleted.
    @Test
    void testDeleteStep_RecursiveDeletion() throws Exception {
        // Given
        List<String> keys = Arrays.asList("key1", "key2");
        Patterns mockPattern1 = mock(Patterns.class);
        Patterns mockPattern2 = mock(Patterns.class);
        when(mockPatterns.get("key1")).thenReturn(Collections.singletonList(mockPattern1));
        when(mockPatterns.get("key2")).thenReturn(Collections.singletonList(mockPattern2));
        // Setup transitions
        when(mockState.getTransitionOn("key1")).thenReturn(mock(ByteMachine.class));
        when(mockState.getKeyTransitionOn("key1")).thenReturn(mock(NameMatcher.class));
        when(mockState.getTransitionOn("key2")).thenReturn(mock(ByteMachine.class));
        when(mockState.getKeyTransitionOn("key2")).thenReturn(mock(NameMatcher.class));
        // Simulate rules
        when(mockState.hasRule("mocked RuleName")).thenReturn(true);
        doNothing().when(mockState).deleteRule("mocked RuleName");
        // When
        invokeDeleteStep(mockState, keys, 0, mockPatterns, "mocked RuleName", deletedKeys);
        // Then
        assertTrue(deletedKeys.contains("key1"), "Deleted keys should contain 'key1'.");
        assertTrue(deletedKeys.contains("key2"), "Deleted keys should contain 'key2'.");
    }

    // Test when the keys list is empty, expecting no deletion action.
    @Test
    void testDeleteStep_EmptyKeysList() throws Exception {
        // Given
        List<String> keys = Collections.emptyList();
        // When
        invokeDeleteStep(mockState, keys, 0, mockPatterns, "mocked RuleName", deletedKeys);
        // Then
        assertTrue(deletedKeys.isEmpty(), "Deleted keys should remain empty.");
    }

    // Test when keyIndex is out of bounds, expecting no deletion action.
    @Test
    void testDeleteStep_KeyIndexOutOfBounds() throws Exception {
        // Given
        List<String> keys = Collections.singletonList("key1");
        // When
        invokeDeleteStep(mockState, keys, 1, mockPatterns, "mocked RuleName", deletedKeys);
        // Then
        assertTrue(deletedKeys.isEmpty(), "Deleted keys should remain empty.");
    }

    // Test when there are no patterns for the key, expecting no deletion action.
    @Test
    void testDeleteStep_NoPatternsForKey() throws Exception {
        // Given
        List<String> keys = Collections.singletonList("key1");
        when(mockPatterns.get("key1")).thenReturn(Collections.emptyList());
        // When
        invokeDeleteStep(mockState, keys, 0, mockPatterns, "mocked RuleName", deletedKeys);
        // Then
        assertTrue(deletedKeys.isEmpty(), "Deleted keys should remain empty.");
    }

    // Test when multiple patterns are present for a key, expecting the key to be deleted if the rule is matched.
    @Test
    void testDeleteStep_MultiplePatternsForKey() throws Exception {
        // Given
        List<String> keys = Collections.singletonList("key1");
        Patterns mockPattern1 = mock(Patterns.class);
        Patterns mockPattern2 = mock(Patterns.class);
        when(mockPatterns.get("key1")).thenReturn(Arrays.asList(mockPattern1, mockPattern2));
        // Setup transitions
        when(mockState.getTransitionOn("key1")).thenReturn(mock(ByteMachine.class));
        when(mockState.getKeyTransitionOn("key1")).thenReturn(mock(NameMatcher.class));
        // Simulate rules
        when(mockState.hasRule("mocked RuleName")).thenReturn(true);
        doNothing().when(mockState).deleteRule("mocked RuleName");
        // When
        invokeDeleteStep(mockState, keys, 0, mockPatterns, "mocked RuleName", deletedKeys);
        // Then
        assertTrue(deletedKeys.contains("key1"), "Deleted keys should contain 'key1'.");
    }

    // Test when the key has existing rules and the rule cannot be deleted, expecting no changes to deletedKeys.
    @Test
    void testDeleteStep_KeyWithExistingRules() throws Exception {
        // Given
        List<String> keys = Collections.singletonList("key1");
        Patterns mockPattern = mock(Patterns.class);
        when(mockPatterns.get("key1")).thenReturn(Collections.singletonList(mockPattern));
        when(mockState.hasRule("mocked RuleName")).thenReturn(true);
        // When
        invokeDeleteStep(mockState, keys, 0, mockPatterns, "mocked RuleName", deletedKeys);
        // Then
        assertTrue(deletedKeys.isEmpty(), "Deleted keys should remain empty.");
    }
}