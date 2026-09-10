Based on the compilation feedback, the main issue lies with the `Range` constructor being passed `double[]` arrays, while it expects `byte[]` arrays. Consequently, the test code must be adjusted to create `Range` objects with `byte[]` instead of `double[]`.

Here’s the corrected code:

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
class ByteMachine_addRangePattern_Test {

    private ByteMachine byteMachine;

    private Range range;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the ByteMachine and any other dependencies before each test
        byteMachine = new ByteMachine();
        // Example of creating a Range object
        range = new Range(new byte[] { 0 }, true, new byte[] { 10 }, false);
    }

    private NameState invokeAddRangePattern(Range range) throws Exception {
        Method method = ByteMachine.class.getDeclaredMethod("addRangePattern", Range.class);
        method.setAccessible(true);
        return (NameState) method.invoke(byteMachine, range);
    }

    @Test
    void testAddRangePattern() {
        // Given: A valid Range object
        // When: The addRangePattern method is called
        NameState result = invokeAddRangePattern(range);
        // Then: Assert the expected behavior or state
        assertNotNull(result);
    }

    @Test
    void testAddRangePattern_NormalCases() {
        // Test with a normal range where bottom is less than top, and both ends are closed.
        range = new Range(new byte[] { 0, 0, 2, 9 }, false, new byte[] { 0, 0, 5, 0 }, false);
        NameState result = invokeAddRangePattern(range);
        assertNotNull(result, "Expected a valid NameState object for closed range.");
        // Test with a range where the bottom is open and the top is closed.
        range = new Range(new byte[] { 0, 1, 0 }, true, new byte[] { 0, 1, 5 }, false);
        result = invokeAddRangePattern(range);
        assertNotNull(result, "Expected a valid NameState object for open bottom and closed top.");
        // Test with a normal range where bottom is less than top, bottom is closed and top is open.
        range = new Range(new byte[] { 0, 1, 2 }, false, new byte[] { 0, 1, 3 }, true);
        result = invokeAddRangePattern(range);
        assertNotNull(result, "Expected a valid NameState object for closed bottom and open top.");
        // Test with a range where the bottom is open and the top is closed.
        range = new Range(new byte[] { 1, 0 }, true, new byte[] { 1, 2 }, false);
        result = invokeAddRangePattern(range);
        assertNotNull(result, "Expected a valid NameState object for open bottom and closed top.");
    }

    @Test
    void testAddRangePattern_EdgeCases() {
        // Test with a range where the bottom is less than the top but the last digit of bottom is 9 and top is open.
        range = new Range(new byte[] { 0, 0, 9 }, false, new byte[] { 0, 1, 0 }, true);
        NameState result = invokeAddRangePattern(range);
        assertNotNull(result, "Expected a valid NameState object with no transitions for last digit.");
        // Test with a range where both bottom and top are equal and closed.
        range = new Range(new byte[] { 0, 0, 0 }, false, new byte[] { 0, 0, 0 }, false);
        result = invokeAddRangePattern(range);
        assertNotNull(result, "Expected a valid NameState object with no transitions.");
        // Test with a range where the bottom is less than the top but the last digit of bottom is 8 and both ends are closed.
        range = new Range(new byte[] { 0, 0, 8 }, false, new byte[] { 0, 1, 0 }, false);
        result = invokeAddRangePattern(range);
        assertNotNull(result, "Expected a valid NameState object with no transitions for last digit.");
        // Test with a range where both bottom and top are equal and bottom is open while top is closed.
        range = new Range(new byte[] { 0, 2, 0 }, true, new byte[] { 0, 2, 0 }, false);
        result = invokeAddRangePattern(range);
        assertNotNull(result, "Expected a valid NameState object with no transitions.");
    }

    @Test
    void testAddRangePattern_InvalidCases() {
        // Test with an invalid range where bottom is greater than top.
        range = new Range(new byte[] { 0, 2, 0 }, false, new byte[] { 0, 1, 5 }, false);
        assertThrows(IllegalArgumentException.class, () -> invokeAddRangePattern(range), "Expected an exception for invalid range where bottom > top.");
        // Test with a range where both bottom and top are equal and both are open.
        range = new Range(new byte[] { 0, 1, 5 }, true, new byte[] { 0, 1, 5 }, true);
        assertThrows(IllegalArgumentException.class, () -> invokeAddRangePattern(range), "Expected an exception for invalid range where both ends are open.");
        // Test with an invalid range where bottom is equal to top but bottom is closed and top is open.
        range = new Range(new byte[] { 0, 2, 5 }, false, new byte[] { 0, 2, 5 }, true);
        assertThrows(IllegalArgumentException.class, () -> invokeAddRangePattern(range), "Expected an exception for invalid range where bottom == top but bottom is closed.");
        // Test with an invalid range where bottom is less than top but the last digit of bottom is 9 and top is open.
        range = new Range(new byte[] { 0, 1, 9 }, false, new byte[] { 0, 2, 0 }, true);
        assertThrows(IllegalArgumentException.class, () -> invokeAddRangePattern(range), "Expected an exception for invalid range where last digit of bottom is 9 and top is open.");
    }

    @Test
    void testAddRangePattern_ExceptionScenarios() {
        // Test with a range where both bottom and top are equal and closed.
        range = new Range(new byte[] { 0, 0, 2, 9 }, false, new byte[] { 0, 0, 2, 9 }, false);
        assertThrows(IllegalArgumentException.class, () -> invokeAddRangePattern(range), "Expected an exception for range where both ends are equal and closed.");
        // Test with an invalid range where bottom is equal to top but bottom is open and top is closed.
        range = new Range(new byte[] { 0, 2, 5 }, true, new byte[] { 0, 2, 5 }, false);
        assertThrows(IllegalArgumentException.class, () -> invokeAddRangePattern(range), "Expected an exception for invalid range where bottom == top but bottom is open.");
        // Test with a range where both bottom and top are equal and both are open.
        range = new Range(new byte[] { 0, 1, 0 }, true, new byte[] { 0, 1, 0 }, true);
        assertThrows(IllegalArgumentException.class, () -> invokeAddRangePattern(range), "Expected an exception for range where both ends are equal and both are open.");
        // Test with an invalid range where bottom is less than top but the last digit of bottom is 9 and top is open.
        range = new Range(new byte[] { 0, 1, 9 }, false, new byte[] { 0, 2, 0 }, true);
        assertThrows(IllegalArgumentException.class, () -> invokeAddRangePattern(range), "Expected an exception for invalid range where last digit of bottom is 9 and top is open.");
    }
}
```

In the corrected code:
- All instances of `new double[]` have been replaced with `new byte[]` to match the expected constructor parameter types for the `Range` class. This should resolve the compilation errors.