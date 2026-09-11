package software.amazon.event.ruler;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ByteMachine_findRangePattern_Test {

    private ByteMachine byteMachine;

    private Range range;

    @BeforeEach
    void setupBeforeEach() {
        byteMachine = new ByteMachine();
        // Initialize with a default valid range
        // ASCII for '0'
        byte[] bottom = { 0x30 };
        // ASCII for '9'
        byte[] top = { 0x39 };
        range = new Range(bottom, false, top, false, false);
    }

    // Helper method to invoke the private method using reflection
    private NameState invokeFindRangePattern(Range range) {
        try {
            var method = ByteMachine.class.getDeclaredMethod("findRangePattern", Range.class);
            method.setAccessible(true);
            return (NameState) method.invoke(byteMachine, range);
        } catch (Exception e) {
            throw new RuntimeException("Failed to invoke findRangePattern", e);
        }
    }

    @Test
    void testFindRangePattern() {
        NameState result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a non-null NameState result for the valid range.");
    }

    @Test
    void testFindRangePattern_ValidClosedRange() {
        byte[] bottom = { 0, 1, 2 };
        byte[] top = { 3, 4, 5 };
        range = new Range(bottom, false, top, false, false);
        NameState result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a non-null NameState result for the valid range.");
    }

    @Test
    void testFindRangePattern_OpenBottomClosedTop() {
        byte[] bottom = { 0, 1 };
        byte[] top = { 1, 1 };
        range = new Range(bottom, true, top, false, false);
        NameState result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a non-null NameState result for open bottom and closed top.");
    }

    @Test
    void testFindRangePattern_ClosedBottomOpenTop() {
        byte[] bottom = { 0, 1, 2 };
        byte[] top = { 3, 4, 5 };
        range = new Range(bottom, false, top, true, false);
        NameState result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a non-null NameState result for the closed bottom and open top range.");
    }

    @Test
    void testFindRangePattern_OpenBothEnds() {
        byte[] bottom = { 0, 1, 2 };
        byte[] top = { 3, 4, 5 };
        range = new Range(bottom, true, top, true, false);
        NameState result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a non-null NameState result for the open range.");
    }

    @Test
    void testFindRangePattern_SingleValueClosed() {
        byte[] bottom = { 0 };
        byte[] top = { 0 };
        range = new Range(bottom, false, top, false, false);
        NameState result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a non-null NameState result for the same single value range.");
    }

    @Test
    void testFindRangePattern_BottomLessThanTop() {
        byte[] bottom = { 0 };
        byte[] top = { 1 };
        range = new Range(bottom, false, top, false, false);
        NameState result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a non-null NameState result for bottom less than top.");
    }

    @Test
    void testFindRangePattern_BottomGreaterThanTop() {
        byte[] bottom = { 1 };
        byte[] top = { 0 };
        range = new Range(bottom, false, top, false, false);
        NameState result = invokeFindRangePattern(range);
        assertNull(result, "Expected null result for bottom greater than top.");
    }

    @Test
    void testFindRangePattern_EmptyRanges() {
        byte[] bottom = {};
        byte[] top = {};
        range = new Range(bottom, false, top, false, false);
        NameState result = invokeFindRangePattern(range);
        assertNull(result, "Expected null result for empty ranges.");
    }

    @Test
    void testFindRangePattern_ValidHigherRange() {
        byte[] bottom = { 5, 6, 7 };
        byte[] top = { 8, 9, 10 };
        range = new Range(bottom, false, top, false, false);
        NameState result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a non-null NameState result for higher valid range.");
    }

    @Test
    void testFindRangePattern_OverlappingRanges() {
        byte[] bottom = { 5, 6, 7 };
        byte[] top = { 7, 8, 9 };
        range = new Range(bottom, false, top, true, false);
        NameState result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a non-null NameState result for overlapping ranges.");
    }

    @Test
    void testFindRangePattern_EqualOpenBottom() {
        byte[] bottom = { 2, 3 };
        byte[] top = { 2, 3 };
        range = new Range(bottom, true, top, false, false);
        NameState result = invokeFindRangePattern(range);
        assertNull(result, "Expected null result for equal ranges with open bottom.");
    }

    @Test
    void testFindRangePattern_ValidOpenTop() {
        byte[] bottom = { 0, 0 };
        byte[] top = { 1, 0 };
        range = new Range(bottom, false, top, true, false);
        NameState result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a non-null NameState result for valid open top.");
    }

    @Test
    void testFindRangePattern_EqualMultipleValues() {
        byte[] bottom = { 1, 2, 3 };
        byte[] top = { 1, 2, 3 };
        range = new Range(bottom, false, top, false, false);
        NameState result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a non-null NameState result for equal multiple values.");
    }

    @Test
    void testFindRangePattern_BothOpen() {
        byte[] bottom = { 1 };
        byte[] top = { 2 };
        range = new Range(bottom, true, top, true, false);
        NameState result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a non-null NameState result for both open ranges.");
    }

    @Test
    void testFindRangePattern_EqualButOpenTop() {
        byte[] bottom = { 1, 2, 3 };
        byte[] top = { 1, 2, 3 };
        range = new Range(bottom, false, top, true, false);
        NameState result = invokeFindRangePattern(range);
        assertNull(result, "Expected null result for equal ranges with open top.");
    }

    @Test
    void testFindRangePattern_EqualClosedRanges() {
        byte[] bottom = { 0, 1, 2 };
        byte[] top = { 0, 1, 2 };
        range = new Range(bottom, false, top, false, false);
        NameState result = invokeFindRangePattern(range);
        assertNull(result, "Expected null result for equal closed ranges.");
    }

    @Test
    void testFindRangePattern_BottomLessThanTopEqualLast() {
        byte[] bottom = { 0, 1, 2 };
        byte[] top = { 2, 2, 2 };
        range = new Range(bottom, false, top, false, false);
        NameState result = invokeFindRangePattern(range);
        assertNull(result, "Expected null result for bottom less than top but last bytes equal.");
    }

    @Test
    void testFindRangePattern_BottomGreaterThanTopEqualFirst() {
        byte[] bottom = { 5, 6, 7 };
        byte[] top = { 5, 5, 5 };
        range = new Range(bottom, false, top, false, false);
        NameState result = invokeFindRangePattern(range);
        assertNull(result, "Expected null result for bottom greater than top but first byte equal.");
    }

    @Test
    void testFindRangePattern_BottomGreaterThanTop_Second() {
        byte[] bottom = { 3, 4 };
        byte[] top = { 2, 5 };
        range = new Range(bottom, false, top, false, false);
        NameState result = invokeFindRangePattern(range);
        assertNull(result, "Expected null result for bottom greater than top.");
    }

    @Test
    void testFindRangePattern_ValidClosedRange_Second() {
        byte[] bottom = { 0, 1, 2 };
        byte[] top = { 3, 3, 3 };
        range = new Range(bottom, false, top, false, false);
        NameState result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a non-null NameState result for valid closed range.");
    }

    @Test
    void testFindRangePattern_BottomLessThanTopEqualLast_Second() {
        byte[] bottom = { 0, 1, 2 };
        byte[] top = { 2, 2, 2 };
        range = new Range(bottom, false, top, false, false);
        NameState result = invokeFindRangePattern(range);
        assertNull(result, "Expected null result for bottom less than top but last bytes equal.");
    }
}
