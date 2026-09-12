package software.amazon.event.ruler;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class ByteMachine_deleteRangePattern_Test {

    private ByteMachine byteMachine;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the ByteMachine instance before each test
        byteMachine = new ByteMachine();
    }

    @Test
    void testDeleteRangePattern() throws Exception {
        // Use reflection to access the private method deleteRangePattern
        Method deleteRangePatternMethod = ByteMachine.class.getDeclaredMethod("deleteRangePattern", Range.class);
        deleteRangePatternMethod.setAccessible(true);
        // Test case group: deleteRangePattern
        // Test case 1: Range does not exist
        byte[] bottom1 = { 0 };
        byte[] top1 = { 0 };
        Range range1 = new Range(bottom1, false, top1, false, false);
        deleteRangePatternMethod.invoke(byteMachine, range1);
        // Test case 2: Deletion of specific range pattern
        byte[] bottom2 = { 1, 2, 3 };
        byte[] top2 = { 1, 2, 3 };
        Range range2 = new Range(bottom2, false, top2, false, false);
        deleteRangePatternMethod.invoke(byteMachine, range2);
        // Test case 3: Deletion of range patterns between 1,2,3 and 1,2,4
        byte[] bottom3 = { 1, 2, 3 };
        byte[] top3 = { 1, 2, 4 };
        Range range3 = new Range(bottom3, false, top3, false, false);
        deleteRangePatternMethod.invoke(byteMachine, range3);
        // Test case 4: No deletion as range is open and does not match any exact pattern
        byte[] bottom4 = { 1, 2, 3 };
        byte[] top4 = { 1, 2, 3 };
        Range range4 = new Range(bottom4, true, top4, true, false);
        deleteRangePatternMethod.invoke(byteMachine, range4);
        // Test case 5: Partial deletion due to open top
        byte[] bottom5 = { 1, 2, 3 };
        byte[] top5 = { 1, 2, 3 };
        Range range5 = new Range(bottom5, false, top5, true, false);
        deleteRangePatternMethod.invoke(byteMachine, range5);
        // Test case 6: Deletion of patterns from 1,2,4 to 1,2,5
        byte[] bottom6 = { 1, 2, 4 };
        byte[] top6 = { 1, 2, 5 };
        Range range6 = new Range(bottom6, true, top6, false, false);
        deleteRangePatternMethod.invoke(byteMachine, range6);
        // Test case 7: Deletion of all patterns within the full byte range
        byte[] bottom7 = { 0 };
        byte[] top7 = { (byte) 255 };
        Range range7 = new Range(bottom7, false, top7, false, false);
        deleteRangePatternMethod.invoke(byteMachine, range7);
        // Test case 8: Deletion of patterns with byte value 128
        byte[] bottom8 = { 127 };
        byte[] top8 = { (byte) 128 };
        Range range8 = new Range(bottom8, true, top8, false, false);
        deleteRangePatternMethod.invoke(byteMachine, range8);
        // Test case 9: No deletion due to empty bottom value
        byte[] bottom9 = {};
        byte[] top9 = { 1, 2, 3 };
        Range range9 = new Range(bottom9, false, top9, false, false);
        deleteRangePatternMethod.invoke(byteMachine, range9);
        // Test case 10: Deletion of range patterns between 1,2,3 and 1,2,3,4
        byte[] bottom10 = { 1, 2, 3 };
        byte[] top10 = { 1, 2, 3, 4 };
        Range range10 = new Range(bottom10, false, top10, false, false);
        deleteRangePatternMethod.invoke(byteMachine, range10);
        // Test case 11: No deletion as open bottom does not match any exact pattern
        byte[] bottom11 = { 1, 2, 3 };
        byte[] top11 = { 1, 2, 3 };
        Range range11 = new Range(bottom11, true, top11, false, false);
        deleteRangePatternMethod.invoke(byteMachine, range11);
        // Test case 12: No deletion due to empty bottom and top values
        byte[] bottom12 = {};
        byte[] top12 = {};
        Range range12 = new Range(bottom12, false, top12, false, false);
        deleteRangePatternMethod.invoke(byteMachine, range12);
        // Test case 13: No deletion due to empty top value
        byte[] bottom13 = { 1 };
        byte[] top13 = {};
        Range range13 = new Range(bottom13, false, top13, false, false);
        deleteRangePatternMethod.invoke(byteMachine, range13);
        // Test case 14: No deletion due to top value being shorter than bottom value
        byte[] bottom14 = { 1, 2, 3 };
        byte[] top14 = { 1, 2 };
        Range range14 = new Range(bottom14, false, top14, false, false);
        deleteRangePatternMethod.invoke(byteMachine, range14);
        // Test case 15: Partial deletion due to open top
        byte[] bottom15 = { 1, 2, 3 };
        byte[] top15 = { 1, 2, 4 };
        Range range15 = new Range(bottom15, false, top15, true, false);
        deleteRangePatternMethod.invoke(byteMachine, range15);
    }
}
