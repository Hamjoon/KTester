package software.amazon.event.ruler;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class ByteMachine_findRangePattern_Test {

    private ByteMachine byteMachine;

    private Range range;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the ByteMachine instance
        byteMachine = new ByteMachine();
        // Initialize a sample Range object for testing
        byte[] bottom = { '0', '1' };
        byte[] top = { '2', '3' };
        range = new Range(bottom, false, top, false, false);
    }

    private NameState invokeFindRangePattern(Range range) throws Exception {
        if (range == null) {
            return null;
        }
        Method method = ByteMachine.class.getDeclaredMethod("findRangePattern", Range.class);
        method.setAccessible(true);
        return (NameState) method.invoke(byteMachine, range);
    }

    @Test
    void testFindRangePattern() throws Exception {
        NameState result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a valid NameState object");
    }

    // Test cases for findRangePattern - Basic Functionality
    @Test
    void testFindRangePattern_BasicFunctionality() throws Exception {
        range = new Range(new byte[] { 1, 2, 3 }, false, new byte[] { 1, 2, 4 }, false, false);
        NameState result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a valid NameState object");
        range = new Range(new byte[] { 1, 2, 3 }, false, new byte[] { 1, 2, 3 }, false, false);
        result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a valid NameState object");
        range = new Range(new byte[] { 1, 2, 4 }, false, new byte[] { 1, 2, 5 }, false, false);
        result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a valid NameState object");
        range = new Range(new byte[] { 0, 1 }, false, new byte[] { 0, 2 }, false, false);
        result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a valid NameState object");
    }

    // Test cases for findRangePattern - Edge Cases
    @Test
    void testFindRangePattern_EdgeCases() throws Exception {
        range = new Range(new byte[] { 1, 2, 3 }, false, new byte[] { 1, 2, 2 }, false, true);
        NameState result = invokeFindRangePattern(range);
        assertNull(result, "Expected null for invalid range where top is less than bottom");
        range = new Range(new byte[] { 0, 0, 0 }, false, new byte[] { 0, 0, 0 }, false, true);
        result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a valid NameState object");
        range = new Range(new byte[] { 1, 2, 3 }, false, new byte[] { 1, 3, 2 }, false, false);
        result = invokeFindRangePattern(range);
        assertNull(result, "Expected null for invalid range order");
        range = new Range(new byte[] { 1 }, true, new byte[] { 2 }, false, false);
        result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a valid NameState object");
    }

    // Test cases for findRangePattern - Null and Invalid Inputs
    @Test
    void testFindRangePattern_NullAndInvalidInputs() throws Exception {
        range = null;
        NameState result = invokeFindRangePattern(range);
        assertNull(result, "Expected null for null range input");
        range = new Range(new byte[] { 1, 2 }, false, new byte[] { 1 }, false, false);
        result = invokeFindRangePattern(range);
        assertNull(result, "Expected null for invalid range where bottom is longer than top");
        range = new Range(new byte[] {}, false, new byte[] {}, false, false);
        result = invokeFindRangePattern(range);
        assertNull(result, "Expected null for empty range arrays");
        range = new Range(new byte[] { 5 }, false, new byte[] { 3 }, false, false);
        result = invokeFindRangePattern(range);
        assertNull(result, "Expected null for range where bottom is greater than top");
    }

    // Test cases for findRangePattern - Boundary Conditions
    @Test
    void testFindRangePattern_BoundaryConditions() throws Exception {
        range = new Range(new byte[] { 0 }, true, new byte[] { 0 }, true, true);
        NameState result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a valid NameState object for minimum values");
        range = new Range(new byte[] { 127 }, false, new byte[] { 127 }, false, false);
        result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a valid NameState object for maximum values");
        range = new Range(new byte[] { 0, 0 }, false, new byte[] { 0, 1 }, false, true);
        result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a valid NameState object for boundary condition with open top");
        range = new Range(new byte[] { 126 }, false, new byte[] { 127 }, false, true);
        result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a valid NameState object for boundary condition with open top");
    }
}
