```java
package software.amazon.event.ruler;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class ByteMachine_updateMatchReferences_Test {

    private ByteMachine byteMachine;

    private Patterns mockPattern;

    private NameState mockNameState;

    private ByteMatch byteMatch;

    @BeforeEach
    void setupBeforeEach() {
        byteMachine = new ByteMachine();
        mockPattern = mock(Patterns.class);
        mockNameState = mock(NameState.class);
        byteMatch = new ByteMatch(mockPattern, mockNameState);
    }

    // Helper method to invoke the private method using reflection
    private void invokeUpdateMatchReferences(ByteMatch match) {
        try {
            Method method = ByteMachine.class.getDeclaredMethod("updateMatchReferences", ByteMatch.class);
            method.setAccessible(true);
            method.invoke(byteMachine, match);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testUpdateMatchReferences_withAnythingButPattern() {
        when(mockPattern.type()).thenReturn(MatchType.ANYTHING_BUT);
        // Assuming this is the correct state for the test
        when(mockPattern.isNumeric()).thenReturn(true);
        when(byteMatch.getNextNameState()).thenReturn(mockNameState);
        invokeUpdateMatchReferences(byteMatch);
        assertEquals(-1, byteMachine.getHasNumeric().get());
        assertFalse(byteMachine.getAnythingButs().contains(mockNameState));
    }

    @Test
    void testUpdateMatchReferences_withUnknownPattern() {
        when(mockPattern.type()).thenReturn(MatchType.EXISTS);
        // Adjust the mockPattern setup to ensure it triggers the exception
        when(mockPattern.isNumeric()).thenReturn(false); // Assuming this is necessary
        assertThrows(IllegalArgumentException.class, () -> invokeUpdateMatchReferences(byteMatch));
    }
}
```