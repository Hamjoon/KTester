package software.amazon.event.ruler;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.nio.charset.StandardCharsets;
import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class ByteMachine_findRangePattern_Test {

    private ByteMachine byteMachine;

    private Range range;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize ByteMachine instance before each test
        byteMachine = new ByteMachine();
        // Example setup for Range
        byte[] bottomBytes = "123".getBytes(StandardCharsets.UTF_8);
        byte[] topBytes = "456".getBytes(StandardCharsets.UTF_8);
        range = new Range(bottomBytes, false, topBytes, false, false);
    }

    private NameState invokeFindRangePattern(Range range) throws Exception {
        Method method = ByteMachine.class.getDeclaredMethod("findRangePattern", Range.class);
        method.setAccessible(true);
        return (NameState) method.invoke(byteMachine, range);
    }

    @Test
    void testFindRangePattern() {
        // Given: A Range object and a ByteMachine instance
        // When: The findRangePattern method is invoked
        NameState result = invokeFindRangePattern(range);
        // Then: Verify the result
        assertNotNull(result, "Expected a non-null result from findRangePattern");
    }

    @Test
    void testFindRangePattern_identicalBottomAndTop() {
        // Given: A Range object where bottom and top are identical
        byte[] bottomBytes = { 0, 1, 2 };
        byte[] topBytes = { 0, 1, 2 };
        range = new Range(bottomBytes, false, topBytes, false, false);
        // When: The findRangePattern method is invoked
        NameState result = invokeFindRangePattern(range);
        // Then: Verify the result
        assertNotNull(result, "Expected a non-null result from findRangePattern when bottom and top are identical");
    }

    @Test
    void testFindRangePattern_topGreaterThanBottomByOne() {
        // Given: A Range object where top is greater than bottom by one
        byte[] bottomBytes = { 0, 1, 2 };
        byte[] topBytes = { 0, 1, 3 };
        range = new Range(bottomBytes, false, topBytes, false, false);
        // When: The findRangePattern method is invoked
        NameState result = invokeFindRangePattern(range);
        // Then: Verify the result
        assertNotNull(result, "Expected a non-null result from findRangePattern when top is greater than bottom by one");
    }

    @Test
    void testFindRangePattern_topLessThanBottom() {
        // Given: A Range object where top is less than bottom
        byte[] bottomBytes = { 0, 1, 2 };
        byte[] topBytes = { 0, 1, 1 };
        range = new Range(bottomBytes, false, topBytes, false, false);
        // When: The findRangePattern method is invoked
        NameState result = invokeFindRangePattern(range);
        // Then: Verify the result
        assertNull(result, "Expected null result from findRangePattern when top is less than bottom");
    }

    @Test
    void testFindRangePattern_openBottomRange() {
        // Given: A Range object with open bottom
        byte[] bottomBytes = { 0, 1, 2 };
        byte[] topBytes = { 0, 1, 3 };
        range = new Range(bottomBytes, true, topBytes, false, false);
        // When: The findRangePattern method is invoked
        NameState result = invokeFindRangePattern(range);
        // Then: Verify the result
        assertNotNull(result, "Expected a non-null result from findRangePattern with open bottom range");
    }

    @Test
    void testFindRangePattern_openTopRange() {
        // Given: A Range object with open top
        byte[] bottomBytes = { 0, 1, 2 };
        byte[] topBytes = { 0, 1, 3 };
        range = new Range(bottomBytes, false, topBytes, true, false);
        // When: The findRangePattern method is invoked
        NameState result = invokeFindRangePattern(range);
        // Then: Verify the result
        assertNotNull(result, "Expected a non-null result from findRangePattern with open top range");
    }

    @Test
    void testFindRangePattern_singleByteEqualBottomAndTop() {
        // Given: A Range object with single byte equal bottom and top
        byte[] bottomBytes = { 0 };
        byte[] topBytes = { 0 };
        range = new Range(bottomBytes, false, topBytes, false, false);
        // When: The findRangePattern method is invoked
        NameState result = invokeFindRangePattern(range);
        // Then: Verify the result
        assertNotNull(result, "Expected a non-null result from findRangePattern with single byte equal bottom and top");
    }

    @Test
    void testFindRangePattern_singleByteTopGreaterThanBottom() {
        // Given: A Range object with single byte top greater than bottom
        byte[] bottomBytes = { 0 };
        byte[] topBytes = { 1 };
        range = new Range(bottomBytes, false, topBytes, false, false);
        // When: The findRangePattern method is invoked
        NameState result = invokeFindRangePattern(range);
        // Then: Verify the result
        assertNotNull(result, "Expected a non-null result from findRangePattern with single byte top greater than bottom");
    }

    @Test
    void testFindRangePattern_singleByteTopLessThanBottom() {
        // Given: A Range object with single byte top less than bottom
        byte[] bottomBytes = { 1 };
        byte[] topBytes = { 0 };
        range = new Range(bottomBytes, false, topBytes, false, false);
        // When: The findRangePattern method is invoked
        NameState result = invokeFindRangePattern(range);
        // Then: Verify the result
        assertNull(result, "Expected null result from findRangePattern with single byte top less than bottom");
    }

    @Test
    void testFindRangePattern_topSignificantlyGreaterThanBottom() {
        // Given: A Range object where top is significantly greater than bottom
        byte[] bottomBytes = { 0, 1, 2 };
        byte[] topBytes = { 0, 1, 9 };
        range = new Range(bottomBytes, false, topBytes, false, false);
        // When: The findRangePattern method is invoked
        NameState result = invokeFindRangePattern(range);
        // Then: Verify the result
        assertNotNull(result, "Expected a non-null result from findRangePattern with top significantly greater than bottom");
    }

    @Test
    void testFindRangePattern_equalBottomAndTopOpen() {
        // Given: A Range object where bottom and top are equal but both open
        byte[] bottomBytes = { 0, 1, 2 };
        byte[] topBytes = { 0, 1, 2 };
        range = new Range(bottomBytes, true, topBytes, true, false);
        // When: The findRangePattern method is invoked
        NameState result = invokeFindRangePattern(range);
        // Then: Verify the result
        assertNull(result, "Expected null result from findRangePattern with equal bottom and top but both open");
    }

    @Test
    void testFindRangePattern_differenceInSecondByte() {
        // Given: A Range object with a difference in the second byte
        byte[] bottomBytes = { 0, 1, 2 };
        byte[] topBytes = { 0, 2, 0 };
        range = new Range(bottomBytes, false, topBytes, false, false);
        // When: The findRangePattern method is invoked
        NameState result = invokeFindRangePattern(range);
        // Then: Verify the result
        assertNotNull(result, "Expected a non-null result from findRangePattern with difference in the second byte");
    }

    @Test
    void testFindRangePattern_topHasMoreBytesThanBottom() {
        // Given: A Range object where top has more bytes than bottom
        byte[] bottomBytes = { 0, 1, 2 };
        byte[] topBytes = { 0, 1, 2, 5 };
        range = new Range(bottomBytes, false, topBytes, false, false);
        // When: The findRangePattern method is invoked
        NameState result = invokeFindRangePattern(range);
        // Then: Verify the result
        assertNull(result, "Expected null result from findRangePattern with top having more bytes than bottom");
    }

    @Test
    void testFindRangePattern_emptyRange() {
        // Given: An empty Range object
        byte[] bottomBytes = {};
        byte[] topBytes = {};
        range = new Range(bottomBytes, false, topBytes, false, false);
        // When: The findRangePattern method is invoked
        NameState result = invokeFindRangePattern(range);
        // Then: Verify the result
        assertNotNull(result, "Expected a non-null result from findRangePattern with empty range");
    }

    @Test
    void testFindRangePattern_equalBottomAndTopOpenTop() {
        // Given: A Range object where bottom and top are equal and top is open
        byte[] bottomBytes = { 0, 1, 2 };
        byte[] topBytes = { 0, 1, 2 };
        range = new Range(bottomBytes, false, topBytes, true, false);
        // When: The findRangePattern method is invoked
        NameState result = invokeFindRangePattern(range);
        // Then: Verify the result
        assertNotNull(result, "Expected a non-null result from findRangePattern with equal bottom and top and open top");
    }

    @Test
    void testFindRangePattern_openBottomAndTop() {
        // Given: A Range object with open bottom and top
        byte[] bottomBytes = { 1, 0, 0 };
        byte[] topBytes = { 1, 0, 1 };
        range = new Range(bottomBytes, true, topBytes, true, false);
        // When: The findRangePattern method is invoked
        NameState result = invokeFindRangePattern(range);
        // Then: Verify the result
        assertNotNull(result, "Expected a non-null result from findRangePattern with open bottom and top");
    }

    @Test
    void testFindRangePattern_equalBottomAndTopOpenBottom() {
        // Given: A Range object where bottom and top are equal but bottom is open
        byte[] bottomBytes = { 1, 0, 0 };
        byte[] topBytes = { 1, 0, 0 };
        range = new Range(bottomBytes, true, topBytes, false, false);
        // When: The findRangePattern method is invoked
        NameState result = invokeFindRangePattern(range);
        // Then: Verify the result
        assertNull(result, "Expected null result from findRangePattern with equal bottom and top but open bottom");
    }

    @Test
    void testFindRangePattern_emptyBottomNonEmptyTop() {
        // Given: A Range object where bottom is empty and top is not
        byte[] bottomBytes = {};
        byte[] topBytes = { 0, 1, 2 };
        range = new Range(bottomBytes, false, topBytes, false, false);
        // When: The findRangePattern method is invoked
        NameState result = invokeFindRangePattern(range);
        // Then: Verify the result
        assertNull(result, "Expected null result from findRangePattern with empty bottom and non-empty top");
    }

    @Test
    void testFindRangePattern_nonEmptyBottomEmptyTop() {
        // Given: A Range object where top is empty and bottom is not
        byte[] bottomBytes = { 0, 1, 2 };
        byte[] topBytes = {};
        range = new Range(bottomBytes, false, topBytes, false, false);
        // When: The findRangePattern method is invoked
        NameState result = invokeFindRangePattern(range);
        // Then: Verify the result
        assertNull(result, "Expected null result from findRangePattern with non-empty bottom and empty top");
    }

    @Test
    void testFindRangePattern_bottomHasMoreBytesThanTop() {
        // Given: A Range object where bottom has more bytes than top
        byte[] bottomBytes = { 0, 1, 2, 4 };
        byte[] topBytes = { 0, 1, 2 };
        range = new Range(bottomBytes, false, topBytes, false, false);
        // When: The findRangePattern method is invoked
        NameState result = invokeFindRangePattern(range);
        // Then: Verify the result
        assertNull(result, "Expected null result from findRangePattern with bottom having more bytes than top");
    }
}
