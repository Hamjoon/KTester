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
        byteMachine = new ByteMachine();
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
    void testFindRangePattern() throws Exception {
        NameState result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a non-null result from findRangePattern");
    }

    @Test
    void testFindRangePattern_identicalBottomAndTop() throws Exception {
        byte[] bottomBytes = { 0, 1, 2 };
        byte[] topBytes = { 0, 1, 2 };
        range = new Range(bottomBytes, false, topBytes, false, false);
        NameState result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a non-null result from findRangePattern when bottom and top are identical");
    }

    @Test
    void testFindRangePattern_topGreaterThanBottomByOne() throws Exception {
        byte[] bottomBytes = { 0, 1, 2 };
        byte[] topBytes = { 0, 1, 3 };
        range = new Range(bottomBytes, false, topBytes, false, false);
        NameState result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a non-null result from findRangePattern when top is greater than bottom by one");
    }

    @Test
    void testFindRangePattern_topLessThanBottom() throws Exception {
        byte[] bottomBytes = { 0, 1, 2 };
        byte[] topBytes = { 0, 1, 1 };
        range = new Range(bottomBytes, false, topBytes, false, false);
        NameState result = invokeFindRangePattern(range);
        assertNull(result, "Expected null result from findRangePattern when top is less than bottom");
    }

    @Test
    void testFindRangePattern_openBottomRange() throws Exception {
        byte[] bottomBytes = { 0, 1, 2 };
        byte[] topBytes = { 0, 1, 3 };
        range = new Range(bottomBytes, true, topBytes, false, false);
        NameState result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a non-null result from findRangePattern with open bottom range");
    }

    @Test
    void testFindRangePattern_openTopRange() throws Exception {
        byte[] bottomBytes = { 0, 1, 2 };
        byte[] topBytes = { 0, 1, 3 };
        range = new Range(bottomBytes, false, topBytes, true, false);
        NameState result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a non-null result from findRangePattern with open top range");
    }

    @Test
    void testFindRangePattern_singleByteEqualBottomAndTop() throws Exception {
        byte[] bottomBytes = { 0 };
        byte[] topBytes = { 0 };
        range = new Range(bottomBytes, false, topBytes, false, false);
        NameState result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a non-null result from findRangePattern with single byte equal bottom and top");
    }

    @Test
    void testFindRangePattern_singleByteTopGreaterThanBottom() throws Exception {
        byte[] bottomBytes = { 0 };
        byte[] topBytes = { 1 };
        range = new Range(bottomBytes, false, topBytes, false, false);
        NameState result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a non-null result from findRangePattern with single byte top greater than bottom");
    }

    @Test
    void testFindRangePattern_singleByteTopLessThanBottom() throws Exception {
        byte[] bottomBytes = { 1 };
        byte[] topBytes = { 0 };
        range = new Range(bottomBytes, false, topBytes, false, false);
        NameState result = invokeFindRangePattern(range);
        assertNull(result, "Expected null result from findRangePattern with single byte top less than bottom");
    }

    @Test
    void testFindRangePattern_topSignificantlyGreaterThanBottom() throws Exception {
        byte[] bottomBytes = { 0, 1, 2 };
        byte[] topBytes = { 0, 1, 9 };
        range = new Range(bottomBytes, false, topBytes, false, false);
        NameState result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a non-null result from findRangePattern with top significantly greater than bottom");
    }

    @Test
    void testFindRangePattern_equalBottomAndTopOpen() throws Exception {
        byte[] bottomBytes = { 0, 1, 2 };
        byte[] topBytes = { 0, 1, 2 };
        range = new Range(bottomBytes, true, topBytes, true, false);
        NameState result = invokeFindRangePattern(range);
        assertNull(result, "Expected null result from findRangePattern with equal bottom and top but both open");
    }

    @Test
    void testFindRangePattern_differenceInSecondByte() throws Exception {
        byte[] bottomBytes = { 0, 1, 2 };
        byte[] topBytes = { 0, 2, 0 };
        range = new Range(bottomBytes, false, topBytes, false, false);
        NameState result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a non-null result from findRangePattern with difference in the second byte");
    }

    @Test
    void testFindRangePattern_topHasMoreBytesThanBottom() throws Exception {
        byte[] bottomBytes = { 0, 1, 2 };
        byte[] topBytes = { 0, 1, 2, 5 };
        range = new Range(bottomBytes, false, topBytes, false, false);
        NameState result = invokeFindRangePattern(range);
        assertNull(result, "Expected null result from findRangePattern with top having more bytes than bottom");
    }

    @Test
    void testFindRangePattern_emptyRange() throws Exception {
        byte[] bottomBytes = {};
        byte[] topBytes = {};
        range = new Range(bottomBytes, false, topBytes, false, false);
        NameState result = invokeFindRangePattern(range);
        assertNull(result, "Expected null result from findRangePattern with empty range");
    }

    @Test
    void testFindRangePattern_equalBottomAndTopOpenTop() throws Exception {
        byte[] bottomBytes = { 0, 1, 2 };
        byte[] topBytes = { 0, 1, 2 };
        range = new Range(bottomBytes, false, topBytes, true, false);
        NameState result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a non-null result from findRangePattern with equal bottom and top and open top");
    }

    @Test
    void testFindRangePattern_openBottomAndTop() throws Exception {
        byte[] bottomBytes = { 1, 0, 0 };
        byte[] topBytes = { 1, 0, 1 };
        range = new Range(bottomBytes, true, topBytes, true, false);
        NameState result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a non-null result from findRangePattern with open bottom and top");
    }

    @Test
    void testFindRangePattern_equalBottomAndTopOpenBottom() throws Exception {
        byte[] bottomBytes = { 1, 0, 0 };
        byte[] topBytes = { 1, 0, 0 };
        range = new Range(bottomBytes, true, topBytes, false, false);
        NameState result = invokeFindRangePattern(range);
        assertNull(result, "Expected null result from findRangePattern with equal bottom and top but open bottom");
    }

    @Test
    void testFindRangePattern_emptyBottomNonEmptyTop() throws Exception {
        byte[] bottomBytes = {};
        byte[] topBytes = { 0, 1, 2 };
        range = new Range(bottomBytes, false, topBytes, false, false);
        NameState result = invokeFindRangePattern(range);
        assertNull(result, "Expected null result from findRangePattern with empty bottom and non-empty top");
    }

    @Test
    void testFindRangePattern_nonEmptyBottomEmptyTop() throws Exception {
        byte[] bottomBytes = { 0, 1, 2 };
        byte[] topBytes = {};
        range = new Range(bottomBytes, false, topBytes, false, false);
        NameState result = invokeFindRangePattern(range);
        assertNull(result, "Expected null result from findRangePattern with non-empty bottom and empty top");
    }

    @Test
    void testFindRangePattern_bottomHasMoreBytesThanTop() throws Exception {
        byte[] bottomBytes = { 0, 1, 2, 4 };
        byte[] topBytes = { 0, 1, 2 };
        range = new Range(bottomBytes, false, topBytes, false, false);
        NameState result = invokeFindRangePattern(range);
        assertNull(result, "Expected null result from findRangePattern with bottom having more bytes than top");
    }
}
