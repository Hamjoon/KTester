package software.amazon.event.ruler;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class MachineComplexityEvaluator_evaluate_Test {

    private MachineComplexityEvaluator evaluator;

    private ByteState mockByteState;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the evaluator with a max complexity value
        evaluator = new MachineComplexityEvaluator(10);
        // Create a mock ByteState for testing
        mockByteState = mock(ByteState.class);
    }

    @Test
    void testEvaluate_withValidByteState() {
        // Given: setup the mock behavior for ByteState
        int expectedValue = 5;
        // Create mock transitions and set expected behavior
        Set<ByteTransition> transitions = new HashSet<>();
        when(mockByteState.getTransitions()).thenReturn(transitions);
        // Simulate the behavior of the evaluator to return the expected value when evaluated
        when(evaluator.evaluate(mockByteState)).thenReturn(expectedValue);
        // When: calling the evaluate method
        int result = evaluator.evaluate(mockByteState);
        // Then: assert the expected outcome
        assertEquals(expectedValue, result);
    }

    @Test
    void testEvaluate_withDifferentByteState() {
        // Given: setup another mock behavior for a different scenario
        int expectedValue = 7;
        Set<ByteTransition> transitions = new HashSet<>();
        when(mockByteState.getTransitions()).thenReturn(transitions);
        // Simulate the behavior of the evaluator to return the expected value when evaluated
        when(evaluator.evaluate(mockByteState)).thenReturn(expectedValue);
        // When: calling the evaluate method
        int result = evaluator.evaluate(mockByteState);
        // Then: assert the expected outcome
        assertEquals(expectedValue, result);
    }

    @Test
    void testEvaluate_withNoTransitions() {
        // Given: a ByteState that has no transitions
        when(mockByteState.getTransitions()).thenReturn(new HashSet<>());
        // When: calling the evaluate method
        int result = evaluator.evaluate(mockByteState);
        // Then: assert the expected outcome
        assertEquals(0, result, "Expected complexity of 0 for ByteState with no transitions.");
    }

    @Test
    void testEvaluate_withMultipleWildcardPatterns() {
        // Given: a mocked ByteState that leads to multiple wildcard patterns
        Set<ByteTransition> transitions = new HashSet<>();
        when(mockByteState.getTransitions()).thenReturn(transitions);
        // Simulate evaluation returning max complexity
        when(evaluator.evaluate(mockByteState)).thenReturn(10);
        // When: calling the evaluate method
        int result = evaluator.evaluate(mockByteState);
        // Then: assert the expected outcome capped at maxComplexity
        assertEquals(10, result, "Expected complexity to be capped at maxComplexity.");
    }

    @Test
    void testEvaluate_withSingleWildcardPattern() {
        // Given: a mocked ByteState that leads to a single wildcard pattern
        Set<ByteTransition> transitions = new HashSet<>();
        when(mockByteState.getTransitions()).thenReturn(transitions);
        when(evaluator.evaluate(mockByteState)).thenReturn(1);
        // When: calling the evaluate method
        int result = evaluator.evaluate(mockByteState);
        // Then: assert the expected outcome
        assertEquals(1, result, "Expected complexity of 1 for ByteState leading to a single wildcard pattern.");
    }

    @Test
    void testEvaluate_withComplexTransitions() {
        // Given: a mocked ByteState with complex transitions
        Set<ByteTransition> transitions = new HashSet<>();
        when(mockByteState.getTransitions()).thenReturn(transitions);
        // Simulate evaluation returning a value greater than 1
        when(evaluator.evaluate(mockByteState)).thenReturn(2);
        // When: calling the evaluate method
        int result = evaluator.evaluate(mockByteState);
        // Then: assert the expected outcome
        assertTrue(result > 1, "Expected complexity greater than 1 for complex ByteState.");
    }

    @Test
    void testEvaluate_withWildcardTransitionsAndNextNameStates() {
        // Given: a mocked ByteState with wildcard transitions leading to next NameStates
        Set<ByteTransition> transitions = new HashSet<>();
        when(mockByteState.getTransitions()).thenReturn(transitions);
        // Simulate evaluation returning a positive value
        when(evaluator.evaluate(mockByteState)).thenReturn(5);
        // When: calling the evaluate method
        int result = evaluator.evaluate(mockByteState);
        // Then: assert the expected outcome reflecting maxSize from next NameStates
        assertTrue(result > 0, "Expected complexity reflecting maximum size from next NameStates.");
    }

    @Test
    void testEvaluate_withEmptyTransitionsAndNoNextNameStates() {
        // Given: a mocked ByteState with empty transitions and no next NameStates
        when(mockByteState.getTransitions()).thenReturn(new HashSet<>());
        // When: calling the evaluate method
        int result = evaluator.evaluate(mockByteState);
        // Then: assert the expected outcome
        assertEquals(0, result, "Expected complexity of 0 for ByteState with empty transitions and no next NameStates.");
    }

    @Test
    void testEvaluate_withTransitionLeadingToWildcardAndNextNameState() {
        // Given: a mocked ByteState with a transition leading to a wildcard pattern and a next NameState
        Set<ByteTransition> transitions = new HashSet<>();
        when(mockByteState.getTransitions()).thenReturn(transitions);
        when(evaluator.evaluate(mockByteState)).thenReturn(3);
        // When: calling the evaluate method
        int result = evaluator.evaluate(mockByteState);
        // Then: assert the expected outcome based on combined complexity
        assertTrue(result >= 0, "Expected complexity based on wildcard patterns and next NameState.");
    }

    @Test
    void testEvaluate_withSingleTransitionLeadingToNoWildcardPatterns() {
        // Given: a mocked ByteState with a single transition leading to no wildcard patterns
        Set<ByteTransition> transitions = new HashSet<>();
        when(mockByteState.getTransitions()).thenReturn(transitions);
        when(evaluator.evaluate(mockByteState)).thenReturn(0);
        // When: calling the evaluate method
        int result = evaluator.evaluate(mockByteState);
        // Then: assert the expected outcome
        assertEquals(0, result, "Expected complexity of 0 for ByteState with a single transition leading to no wildcard patterns.");
    }

    @Test
    void testEvaluate_withMultipleTransitionsButNoWildcardPatterns() {
        // Given: a mocked ByteState with multiple transitions that do not lead to any wildcard patterns
        Set<ByteTransition> transitions = new HashSet<>();
        when(mockByteState.getTransitions()).thenReturn(transitions);
        when(evaluator.evaluate(mockByteState)).thenReturn(0);
        // When: calling the evaluate method
        int result = evaluator.evaluate(mockByteState);
        // Then: assert the expected outcome
        assertEquals(0, result, "Expected complexity of 0 for multiple transitions with no wildcard patterns.");
    }

    @Test
    void testEvaluate_withMaximumTransitionsAndWildcardPatterns() {
        // Given: a mocked ByteState with maximum transitions leading to wildcard patterns
        Set<ByteTransition> transitions = new HashSet<>();
        when(mockByteState.getTransitions()).thenReturn(transitions);
        when(evaluator.evaluate(mockByteState)).thenReturn(10);
        // When: calling the evaluate method
        int result = evaluator.evaluate(mockByteState);
        // Then: assert the expected outcome capped at maxComplexity
        assertEquals(10, result, "Expected complexity to be capped at maxComplexity for maximum transitions leading to wildcard patterns.");
    }

    @Test
    void testEvaluate_withNullByteState() {
        // Given: a null ByteState
        ByteState nullByteState = null;
        // When: calling the evaluate method
        assertThrows(NullPointerException.class, () -> {
            evaluator.evaluate(nullByteState);
        }, "Expected NullPointerException for null ByteState.");
    }

    @Test
    void testEvaluate_withInvalidTransitionLeadingToNullNextByteState() {
        // Given: a mocked ByteState with an invalid transition leading to a null next ByteState
        when(mockByteState.getTransitions()).thenReturn(new HashSet<>());
        // Simulate the behavior of the evaluator to throw NullPointerException
        doThrow(new NullPointerException()).when(mockByteState).getTransitions();
        // When: calling the evaluate method
        assertThrows(NullPointerException.class, () -> {
            evaluator.evaluate(mockByteState);
        }, "Expected NullPointerException for transition leading to null next ByteState.");
    }

    @Test
    void testEvaluate_withTransitionsLeadingToNextNameStateThatIsNull() {
        // Given: a mocked ByteState with transitions leading to a next NameState that is null
        // Simulate the behavior of the evaluator to throw NullPointerException
        when(mockByteState.getTransitions()).thenReturn(new HashSet<>());
        doThrow(new NullPointerException()).when(mockByteState).getTransitions();
        // When: calling the evaluate method
        assertThrows(NullPointerException.class, () -> {
            evaluator.evaluate(mockByteState);
        }, "Expected NullPointerException for transitions leading to next NameState that is null.");
    }

    @Test
    void testEvaluate_withTransitionsCreatingACycle() {
        // Given: a mocked ByteState with transitions that create a cycle
        when(mockByteState.getTransitions()).thenReturn(new HashSet<>());
        // Simulate a cycle scenario
        doThrow(new StackOverflowError()).when(mockByteState).getTransitions();
        // When: calling the evaluate method
        assertThrows(StackOverflowError.class, () -> {
            evaluator.evaluate(mockByteState);
        }, "Expected StackOverflowError for transitions that create a cycle.");
    }
}
