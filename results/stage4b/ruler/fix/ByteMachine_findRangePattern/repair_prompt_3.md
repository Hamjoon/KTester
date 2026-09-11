


You are an experienced Java developer tasked with fixing a piece of code that contains execution errors. Your task is to resolve these issues after analyzing the code and its execution feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any logical errors.
2. Analyze the execution feedback to understand the specific errors and their locations within the code.
3. Apply necessary corrections to the code based on the feedback, and output the compelete correct code.
The Java code you need to fix is provided below:
```java
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
        // Initialize the ByteMachine instance and any required Range instances before each test.
        byteMachine = new ByteMachine();
        // Example of initializing Range (you may need to adjust based on the actual values you want to test)
        // Example bottom range (ASCII for '0')
        byte[] bottom = { 0x30 };
        // Example top range (ASCII for '9')
        byte[] top = { 0x39 };
        // Adjust parameters as needed
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
        // Given: a valid range
        // When: invoking the findRangePattern method
        NameState result = invokeFindRangePattern(range);
        // Then: assert the expected outcome (adjust assertions based on expected behavior)
        assertNotNull(result, "Expected a non-null NameState result for the valid range.");
    }

    @Test
    void testFindRangePattern_ValidClosedRange() {
        // Given: a valid closed range
        byte[] bottom = { 0, 1, 2 };
        byte[] top = { 3, 4, 5 };
        range = new Range(bottom, false, top, false, false);
        // When: invoking the findRangePattern method
        NameState result = invokeFindRangePattern(range);
        // Then: assert the expected outcome
        assertNotNull(result, "Expected a non-null NameState result for the valid range.");
    }

    @Test
    void testFindRangePattern_OpenBottomClosedTop() {
        // Test case where the bottom range is open and the top range is closed.
        byte[] bottom = { 0, 1 };
        byte[] top = { 1, 1 };
        range = new Range(bottom, true, top, false, false);
        NameState result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a non-null NameState result for open bottom and closed top.");
    }

    @Test
    void testFindRangePattern_ClosedBottomOpenTop() {
        // Test case where the bottom range is closed and the top range is open.
        byte[] bottom = { 0, 1, 2 };
        byte[] top = { 3, 4, 5 };
        range = new Range(bottom, false, top, true, false);
        NameState result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a non-null NameState result for the closed bottom and open top range.");
    }

    @Test
    void testFindRangePattern_OpenBothEnds() {
        // Test case where both bottom and top ranges are open.
        byte[] bottom = { 0, 1, 2 };
        byte[] top = { 3, 4, 5 };
        range = new Range(bottom, true, top, true, false);
        NameState result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a non-null NameState result for the open range.");
    }

    @Test
    void testFindRangePattern_SingleValueClosed() {
        // Test case where the bottom and top range are the same single value and both are closed.
        byte[] bottom = { 0 };
        byte[] top = { 0 };
        range = new Range(bottom, false, top, false, false);
        NameState result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a non-null NameState result for the same single value range.");
    }

    @Test
    void testFindRangePattern_BottomLessThanTop() {
        // Test case where the bottom range is less than the top range, both are closed.
        byte[] bottom = { 0 };
        byte[] top = { 1 };
        range = new Range(bottom, false, top, false, false);
        NameState result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a non-null NameState result for bottom less than top.");
    }

    @Test
    void testFindRangePattern_BottomGreaterThanTop() {
        // Test case where the bottom range is greater than the top range, expecting null return.
        byte[] bottom = { 1 };
        byte[] top = { 0 };
        range = new Range(bottom, false, top, false, false);
        NameState result = invokeFindRangePattern(range);
        assertNull(result, "Expected null result for bottom greater than top.");
    }

    @Test
    void testFindRangePattern_EmptyRanges() {
        // Test case where the bottom and top ranges are empty, expecting null return.
        byte[] bottom = {};
        byte[] top = {};
        range = new Range(bottom, false, top, false, false);
        NameState result = invokeFindRangePattern(range);
        assertNull(result, "Expected null result for empty ranges.");
    }

    @Test
    void testFindRangePattern_ValidHigherRange() {
        // Test case where the bottom range is higher than previous tests but still valid, both closed.
        byte[] bottom = { 5, 6, 7 };
        byte[] top = { 8, 9, 10 };
        range = new Range(bottom, false, top, false, false);
        NameState result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a non-null NameState result for higher valid range.");
    }

    @Test
    void testFindRangePattern_OverlappingRanges() {
        // Test case where bottom and top ranges overlap with top being open.
        byte[] bottom = { 5, 6, 7 };
        byte[] top = { 7, 8, 9 };
        range = new Range(bottom, false, top, true, false);
        NameState result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a non-null NameState result for overlapping ranges.");
    }

    @Test
    void testFindRangePattern_EqualOpenBottom() {
        // Test case where both ranges are equal but bottom is open, expecting null return.
        byte[] bottom = { 2, 3 };
        byte[] top = { 2, 3 };
        range = new Range(bottom, true, top, false, false);
        NameState result = invokeFindRangePattern(range);
        assertNull(result, "Expected null result for equal ranges with open bottom.");
    }

    @Test
    void testFindRangePattern_ValidOpenTop() {
        // Test case where the bottom range is less than the top range, bottom is closed and top is open.
        byte[] bottom = { 0, 0 };
        byte[] top = { 1, 0 };
        range = new Range(bottom, false, top, true, false);
        NameState result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a non-null NameState result for valid open top.");
    }

    @Test
    void testFindRangePattern_EqualMultipleValues() {
        // Test case where both ranges are equal with multiple values and both closed.
        byte[] bottom = { 1, 2, 3 };
        byte[] top = { 1, 2, 3 };
        range = new Range(bottom, false, top, false, false);
        NameState result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a non-null NameState result for equal multiple values.");
    }

//     @Test
//     void testFindRangePattern_BottomGreaterThanTop() {
//         // Test case where the bottom range is greater than the top range, expecting null return.
//         byte[] bottom = { 3, 4 };
//         byte[] top = { 2, 5 };
//         range = new Range(bottom, false, top, false, false);
//         NameState result = byteMachine.findRangePattern(range);
//         assertNull(result, "Expected null result for bottom greater than top.");
//     }

    @Test
    void testFindRangePattern_BothOpen() {
        // Test case where both bottom and top ranges are open and valid.
        byte[] bottom = { 1 };
        byte[] top = { 2 };
        range = new Range(bottom, true, top, true, false);
        NameState result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a non-null NameState result for both open ranges.");
    }

//     @Test
//     void testFindRangePattern_OpenBottomClosedTop() {
//         // Test case where the bottom range is open and the top range is closed.
//         byte[] bottom = { 0, 1 };
//         byte[] top = { 1, 1 };
//         range = new Range(bottom, true, top, false, false);
//         NameState result = byteMachine.findRangePattern(range);
//         assertNotNull(result, "Expected a non-null NameState result for open bottom and closed top.");
//     }

    @Test
    void testFindRangePattern_EqualButOpenTop() {
        // Test case where both ranges are equal but the top is open, expecting null return.
        byte[] bottom = { 1, 2, 3 };
        byte[] top = { 1, 2, 3 };
        range = new Range(bottom, false, top, true, false);
        NameState result = invokeFindRangePattern(range);
        assertNull(result, "Expected null result for equal ranges with open top.");
    }

    @Test
    void testFindRangePattern_EqualClosedRanges() {
        // Test case where both ranges are equal and closed, expecting null return.
        byte[] bottom = { 0, 1, 2 };
        byte[] top = { 0, 1, 2 };
        range = new Range(bottom, false, top, false, false);
        NameState result = invokeFindRangePattern(range);
        assertNull(result, "Expected null result for equal closed ranges.");
    }

    @Test
    void testFindRangePattern_BottomLessThanTopEqualLast() {
        // Test case where the bottom range is less than the top range but the last bytes are equal, expecting null return.
        byte[] bottom = { 0, 1, 2 };
        byte[] top = { 2, 2, 2 };
        range = new Range(bottom, false, top, false, false);
        NameState result = invokeFindRangePattern(range);
        assertNull(result, "Expected null result for bottom less than top but last bytes equal.");
    }

    @Test
    void testFindRangePattern_BottomGreaterThanTopEqualFirst() {
        // Test case where the bottom range is greater than the top range but the first byte is equal, expecting null return.
        byte[] bottom = { 5, 6, 7 };
        byte[] top = { 5, 5, 5 };
        range = new Range(bottom, false, top, false, false);
        NameState result = invokeFindRangePattern(range);
        assertNull(result, "Expected null result for bottom greater than top but first byte equal.");
    }

//     @Test
//     void testFindRangePattern_BottomLessThanTopEqualLast() {
//         // Test case where the bottom range is less than the top range but the last bytes are equal, expecting null return.
//         byte[] bottom = { 0, 1, 2 };
//         byte[] top = { 2, 2, 2 };
//         range = new Range(bottom, false, top, false, false);
//         NameState result = byteMachine.findRangePattern(range);
//         assertNull(result, "Expected null result for bottom less than top but last bytes equal.");
//     }

//     @Test
//     void testFindRangePattern_ValidClosedRange() {
//         // Test case where the bottom range is less than the top range and both are closed.
//         byte[] bottom = { 0, 1, 2 };
//         byte[] top = { 3, 3, 3 };
//         range = new Range(bottom, false, top, false, false);
//         NameState result = byteMachine.findRangePattern(range);
//         assertNotNull(result, "Expected a non-null NameState result for valid closed range.");
//     }

    @Test
    void testFindRangePattern_BottomGreaterThanTop_Second() {
        // Test case where the bottom range is greater than the top range, expecting null return.
        byte[] bottom = { 3, 4 };
        byte[] top = { 2, 5 };
        range = new Range(bottom, false, top, false, false);
        NameState result = invokeFindRangePattern(range);
        assertNull(result, "Expected null result for bottom greater than top.");
    }

    @Test
    void testFindRangePattern_ValidClosedRange_Second() {
        // Test case where the bottom range is less than the top range and both are closed.
        byte[] bottom = { 0, 1, 2 };
        byte[] top = { 3, 3, 3 };
        range = new Range(bottom, false, top, false, false);
        NameState result = invokeFindRangePattern(range);
        assertNotNull(result, "Expected a non-null NameState result for valid closed range.");
    }

    @Test
    void testFindRangePattern_BottomLessThanTopEqualLast_Second() {
        // Test case where the bottom range is less than the top range but the last bytes are equal, expecting null return.
        byte[] bottom = { 0, 1, 2 };
        byte[] top = { 2, 2, 2 };
        range = new Range(bottom, false, top, false, false);
        NameState result = invokeFindRangePattern(range);
        assertNull(result, "Expected null result for bottom less than top but last bytes equal.");
    }
}
```

Corresponding execution feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended

╷
├─ JUnit Jupiter ✔
│  └─ ByteMachine_findRangePattern_Test ✔
│     ├─ testFindRangePattern_ValidClosedRange() ✘ Failed to invoke findRangePattern
│     ├─ testFindRangePattern_ValidHigherRange() ✘ Failed to invoke findRangePattern
│     ├─ testFindRangePattern_BottomGreaterThanTop_Second() ✘ Failed to invoke findRangePattern
│     ├─ testFindRangePattern_EqualButOpenTop() ✔
│     ├─ testFindRangePattern_ValidOpenTop() ✘ Failed to invoke findRangePattern
│     ├─ testFindRangePattern_BottomLessThanTopEqualLast() ✘ Failed to invoke findRangePattern
│     ├─ testFindRangePattern_EqualClosedRanges() ✔
│     ├─ testFindRangePattern_ClosedBottomOpenTop() ✘ Failed to invoke findRangePattern
│     ├─ testFindRangePattern_BottomLessThanTop() ✘ Failed to invoke findRangePattern
│     ├─ testFindRangePattern_EqualMultipleValues() ✘ Expected a non-null NameState result for equal multiple values. ==> expected: not <null>
│     ├─ testFindRangePattern_ValidClosedRange_Second() ✘ Failed to invoke findRangePattern
│     ├─ testFindRangePattern_BottomLessThanTopEqualLast_Second() ✘ Failed to invoke findRangePattern
│     ├─ testFindRangePattern_BottomGreaterThanTop() ✘ Failed to invoke findRangePattern
│     ├─ testFindRangePattern_BothOpen() ✘ Failed to invoke findRangePattern
│     ├─ testFindRangePattern_EqualOpenBottom() ✔
│     ├─ testFindRangePattern_OpenBothEnds() ✘ Failed to invoke findRangePattern
│     ├─ testFindRangePattern_OpenBottomClosedTop() ✘ Failed to invoke findRangePattern
│     ├─ testFindRangePattern_OverlappingRanges() ✘ Failed to invoke findRangePattern
│     ├─ testFindRangePattern_SingleValueClosed() ✘ Expected a non-null NameState result for the same single value range. ==> expected: not <null>
│     ├─ testFindRangePattern() ✘ Expected a non-null NameState result for the valid range. ==> expected: not <null>
│     ├─ testFindRangePattern_EmptyRanges() ✘ Failed to invoke findRangePattern
│     └─ testFindRangePattern_BottomGreaterThanTopEqualFirst() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Failures (18):
  JUnit Jupiter:ByteMachine_findRangePattern_Test:testFindRangePattern_ValidClosedRange()
    MethodSource [className = 'software.amazon.event.ruler.ByteMachine_findRangePattern_Test', methodName = 'testFindRangePattern_ValidClosedRange', methodParameterTypes = '']
    => java.lang.RuntimeException: Failed to invoke findRangePattern
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.invokeFindRangePattern(ByteMachine_findRangePattern_Test.java:37)
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.testFindRangePattern_ValidClosedRange(ByteMachine_findRangePattern_Test.java:57)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.invokeFindRangePattern(ByteMachine_findRangePattern_Test.java:35)
       [...]
     Caused by: java.lang.ArrayIndexOutOfBoundsException: arraycopy: source index -54 out of bounds for byte[16]
       java.base/java.lang.System.arraycopy(Native Method)
       software.amazon.event.ruler.Range.digitSequence(Range.java:105)
       software.amazon.event.ruler.ByteMachine.findRangePattern(ByteMachine.java:964)
       [...]
  JUnit Jupiter:ByteMachine_findRangePattern_Test:testFindRangePattern_ValidHigherRange()
    MethodSource [className = 'software.amazon.event.ruler.ByteMachine_findRangePattern_Test', methodName = 'testFindRangePattern_ValidHigherRange', methodParameterTypes = '']
    => java.lang.RuntimeException: Failed to invoke findRangePattern
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.invokeFindRangePattern(ByteMachine_findRangePattern_Test.java:37)
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.testFindRangePattern_ValidHigherRange(ByteMachine_findRangePattern_Test.java:138)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.invokeFindRangePattern(ByteMachine_findRangePattern_Test.java:35)
       [...]
     Caused by: java.lang.ArrayIndexOutOfBoundsException: arraycopy: source index -49 out of bounds for byte[16]
       java.base/java.lang.System.arraycopy(Native Method)
       software.amazon.event.ruler.Range.digitSequence(Range.java:105)
       software.amazon.event.ruler.ByteMachine.findRangePattern(ByteMachine.java:964)
       [...]
  JUnit Jupiter:ByteMachine_findRangePattern_Test:testFindRangePattern_BottomGreaterThanTop_Second()
    MethodSource [className = 'software.amazon.event.ruler.ByteMachine_findRangePattern_Test', methodName = 'testFindRangePattern_BottomGreaterThanTop_Second', methodParameterTypes = '']
    => java.lang.RuntimeException: Failed to invoke findRangePattern
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.invokeFindRangePattern(ByteMachine_findRangePattern_Test.java:37)
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.testFindRangePattern_BottomGreaterThanTop_Second(ByteMachine_findRangePattern_Test.java:278)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.invokeFindRangePattern(ByteMachine_findRangePattern_Test.java:35)
       [...]
     Caused by: java.lang.NegativeArraySizeException: -2
       software.amazon.event.ruler.Range.digitSequence(Range.java:103)
       software.amazon.event.ruler.ByteMachine.findRangePattern(ByteMachine.java:964)
       [...]
  JUnit Jupiter:ByteMachine_findRangePattern_Test:testFindRangePattern_ValidOpenTop()
    MethodSource [className = 'software.amazon.event.ruler.ByteMachine_findRangePattern_Test', methodName = 'testFindRangePattern_ValidOpenTop', methodParameterTypes = '']
    => java.lang.RuntimeException: Failed to invoke findRangePattern
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.invokeFindRangePattern(ByteMachine_findRangePattern_Test.java:37)
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.testFindRangePattern_ValidOpenTop(ByteMachine_findRangePattern_Test.java:168)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.invokeFindRangePattern(ByteMachine_findRangePattern_Test.java:35)
       [...]
     Caused by: java.lang.ArrayIndexOutOfBoundsException: arraycopy: source index -54 out of bounds for byte[16]
       java.base/java.lang.System.arraycopy(Native Method)
       software.amazon.event.ruler.Range.digitSequence(Range.java:105)
       software.amazon.event.ruler.ByteMachine.findRangePattern(ByteMachine.java:964)
       [...]
  JUnit Jupiter:ByteMachine_findRangePattern_Test:testFindRangePattern_BottomLessThanTopEqualLast()
    MethodSource [className = 'software.amazon.event.ruler.ByteMachine_findRangePattern_Test', methodName = 'testFindRangePattern_BottomLessThanTopEqualLast', methodParameterTypes = '']
    => java.lang.RuntimeException: Failed to invoke findRangePattern
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.invokeFindRangePattern(ByteMachine_findRangePattern_Test.java:37)
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.testFindRangePattern_BottomLessThanTopEqualLast(ByteMachine_findRangePattern_Test.java:238)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.invokeFindRangePattern(ByteMachine_findRangePattern_Test.java:35)
       [...]
     Caused by: java.lang.ArrayIndexOutOfBoundsException: arraycopy: source index -54 out of bounds for byte[16]
       java.base/java.lang.System.arraycopy(Native Method)
       software.amazon.event.ruler.Range.digitSequence(Range.java:105)
       software.amazon.event.ruler.ByteMachine.findRangePattern(ByteMachine.java:964)
       [...]
  JUnit Jupiter:ByteMachine_findRangePattern_Test:testFindRangePattern_ClosedBottomOpenTop()
    MethodSource [className = 'software.amazon.event.ruler.ByteMachine_findRangePattern_Test', methodName = 'testFindRangePattern_ClosedBottomOpenTop', methodParameterTypes = '']
    => java.lang.RuntimeException: Failed to invoke findRangePattern
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.invokeFindRangePattern(ByteMachine_findRangePattern_Test.java:37)
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.testFindRangePattern_ClosedBottomOpenTop(ByteMachine_findRangePattern_Test.java:78)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.invokeFindRangePattern(ByteMachine_findRangePattern_Test.java:35)
       [...]
     Caused by: java.lang.ArrayIndexOutOfBoundsException: arraycopy: source index -54 out of bounds for byte[16]
       java.base/java.lang.System.arraycopy(Native Method)
       software.amazon.event.ruler.Range.digitSequence(Range.java:105)
       software.amazon.event.ruler.ByteMachine.findRangePattern(ByteMachine.java:964)
       [...]
  JUnit Jupiter:ByteMachine_findRangePattern_Test:testFindRangePattern_BottomLessThanTop()
    MethodSource [className = 'software.amazon.event.ruler.ByteMachine_findRangePattern_Test', methodName = 'testFindRangePattern_BottomLessThanTop', methodParameterTypes = '']
    => java.lang.RuntimeException: Failed to invoke findRangePattern
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.invokeFindRangePattern(ByteMachine_findRangePattern_Test.java:37)
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.testFindRangePattern_BottomLessThanTop(ByteMachine_findRangePattern_Test.java:108)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.invokeFindRangePattern(ByteMachine_findRangePattern_Test.java:35)
       [...]
     Caused by: java.lang.ArrayIndexOutOfBoundsException: arraycopy: source index -54 out of bounds for byte[16]
       java.base/java.lang.System.arraycopy(Native Method)
       software.amazon.event.ruler.Range.digitSequence(Range.java:105)
       software.amazon.event.ruler.ByteMachine.findRangePattern(ByteMachine.java:964)
       [...]
  JUnit Jupiter:ByteMachine_findRangePattern_Test:testFindRangePattern_EqualMultipleValues()
    MethodSource [className = 'software.amazon.event.ruler.ByteMachine_findRangePattern_Test', methodName = 'testFindRangePattern_EqualMultipleValues', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Expected a non-null NameState result for equal multiple values. ==> expected: not <null>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertNotNull.failNull(AssertNotNull.java:49)
       org.junit.jupiter.api.AssertNotNull.assertNotNull(AssertNotNull.java:35)
       org.junit.jupiter.api.Assertions.assertNotNull(Assertions.java:312)
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.testFindRangePattern_EqualMultipleValues(ByteMachine_findRangePattern_Test.java:179)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       [...]
  JUnit Jupiter:ByteMachine_findRangePattern_Test:testFindRangePattern_ValidClosedRange_Second()
    MethodSource [className = 'software.amazon.event.ruler.ByteMachine_findRangePattern_Test', methodName = 'testFindRangePattern_ValidClosedRange_Second', methodParameterTypes = '']
    => java.lang.RuntimeException: Failed to invoke findRangePattern
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.invokeFindRangePattern(ByteMachine_findRangePattern_Test.java:37)
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.testFindRangePattern_ValidClosedRange_Second(ByteMachine_findRangePattern_Test.java:288)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.invokeFindRangePattern(ByteMachine_findRangePattern_Test.java:35)
       [...]
     Caused by: java.lang.ArrayIndexOutOfBoundsException: arraycopy: source index -54 out of bounds for byte[16]
       java.base/java.lang.System.arraycopy(Native Method)
       software.amazon.event.ruler.Range.digitSequence(Range.java:105)
       software.amazon.event.ruler.ByteMachine.findRangePattern(ByteMachine.java:964)
       [...]
  JUnit Jupiter:ByteMachine_findRangePattern_Test:testFindRangePattern_BottomLessThanTopEqualLast_Second()
    MethodSource [className = 'software.amazon.event.ruler.ByteMachine_findRangePattern_Test', methodName = 'testFindRangePattern_BottomLessThanTopEqualLast_Second', methodParameterTypes = '']
    => java.lang.RuntimeException: Failed to invoke findRangePattern
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.invokeFindRangePattern(ByteMachine_findRangePattern_Test.java:37)
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.testFindRangePattern_BottomLessThanTopEqualLast_Second(ByteMachine_findRangePattern_Test.java:298)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.invokeFindRangePattern(ByteMachine_findRangePattern_Test.java:35)
       [...]
     Caused by: java.lang.ArrayIndexOutOfBoundsException: arraycopy: source index -54 out of bounds for byte[16]
       java.base/java.lang.System.arraycopy(Native Method)
       software.amazon.event.ruler.Range.digitSequence(Range.java:105)
       software.amazon.event.ruler.ByteMachine.findRangePattern(ByteMachine.java:964)
       [...]
  JUnit Jupiter:ByteMachine_findRangePattern_Test:testFindRangePattern_BottomGreaterThanTop()
    MethodSource [className = 'software.amazon.event.ruler.ByteMachine_findRangePattern_Test', methodName = 'testFindRangePattern_BottomGreaterThanTop', methodParameterTypes = '']
    => java.lang.RuntimeException: Failed to invoke findRangePattern
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.invokeFindRangePattern(ByteMachine_findRangePattern_Test.java:37)
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.testFindRangePattern_BottomGreaterThanTop(ByteMachine_findRangePattern_Test.java:118)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.invokeFindRangePattern(ByteMachine_findRangePattern_Test.java:35)
       [...]
     Caused by: java.lang.NegativeArraySizeException: -2
       software.amazon.event.ruler.Range.digitSequence(Range.java:103)
       software.amazon.event.ruler.ByteMachine.findRangePattern(ByteMachine.java:964)
       [...]
  JUnit Jupiter:ByteMachine_findRangePattern_Test:testFindRangePattern_BothOpen()
    MethodSource [className = 'software.amazon.event.ruler.ByteMachine_findRangePattern_Test', methodName = 'testFindRangePattern_BothOpen', methodParameterTypes = '']
    => java.lang.RuntimeException: Failed to invoke findRangePattern
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.invokeFindRangePattern(ByteMachine_findRangePattern_Test.java:37)
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.testFindRangePattern_BothOpen(ByteMachine_findRangePattern_Test.java:198)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.invokeFindRangePattern(ByteMachine_findRangePattern_Test.java:35)
       [...]
     Caused by: java.lang.ArrayIndexOutOfBoundsException: arraycopy: source index -53 out of bounds for byte[16]
       java.base/java.lang.System.arraycopy(Native Method)
       software.amazon.event.ruler.Range.digitSequence(Range.java:105)
       software.amazon.event.ruler.ByteMachine.findRangePattern(ByteMachine.java:964)
       [...]
  JUnit Jupiter:ByteMachine_findRangePattern_Test:testFindRangePattern_OpenBothEnds()
    MethodSource [className = 'software.amazon.event.ruler.ByteMachine_findRangePattern_Test', methodName = 'testFindRangePattern_OpenBothEnds', methodParameterTypes = '']
    => java.lang.RuntimeException: Failed to invoke findRangePattern
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.invokeFindRangePattern(ByteMachine_findRangePattern_Test.java:37)
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.testFindRangePattern_OpenBothEnds(ByteMachine_findRangePattern_Test.java:88)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.invokeFindRangePattern(ByteMachine_findRangePattern_Test.java:35)
       [...]
     Caused by: java.lang.ArrayIndexOutOfBoundsException: arraycopy: source index -54 out of bounds for byte[16]
       java.base/java.lang.System.arraycopy(Native Method)
       software.amazon.event.ruler.Range.digitSequence(Range.java:105)
       software.amazon.event.ruler.ByteMachine.findRangePattern(ByteMachine.java:964)
       [...]
  JUnit Jupiter:ByteMachine_findRangePattern_Test:testFindRangePattern_OpenBottomClosedTop()
    MethodSource [className = 'software.amazon.event.ruler.ByteMachine_findRangePattern_Test', methodName = 'testFindRangePattern_OpenBottomClosedTop', methodParameterTypes = '']
    => java.lang.RuntimeException: Failed to invoke findRangePattern
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.invokeFindRangePattern(ByteMachine_findRangePattern_Test.java:37)
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.testFindRangePattern_OpenBottomClosedTop(ByteMachine_findRangePattern_Test.java:68)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       jdk.internal.reflect.GeneratedMethodAccessor13.invoke(Unknown Source)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.invokeFindRangePattern(ByteMachine_findRangePattern_Test.java:35)
       [...]
     Caused by: java.lang.ArrayIndexOutOfBoundsException: arraycopy: source index -54 out of bounds for byte[16]
       java.base/java.lang.System.arraycopy(Native Method)
       software.amazon.event.ruler.Range.digitSequence(Range.java:105)
       software.amazon.event.ruler.ByteMachine.findRangePattern(ByteMachine.java:964)
       [...]
  JUnit Jupiter:ByteMachine_findRangePattern_Test:testFindRangePattern_OverlappingRanges()
    MethodSource [className = 'software.amazon.event.ruler.ByteMachine_findRangePattern_Test', methodName = 'testFindRangePattern_OverlappingRanges', methodParameterTypes = '']
    => java.lang.RuntimeException: Failed to invoke findRangePattern
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.invokeFindRangePattern(ByteMachine_findRangePattern_Test.java:37)
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.testFindRangePattern_OverlappingRanges(ByteMachine_findRangePattern_Test.java:148)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       jdk.internal.reflect.GeneratedMethodAccessor13.invoke(Unknown Source)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.invokeFindRangePattern(ByteMachine_findRangePattern_Test.java:35)
       [...]
     Caused by: java.lang.ArrayIndexOutOfBoundsException: arraycopy: source index -49 out of bounds for byte[16]
       java.base/java.lang.System.arraycopy(Native Method)
       software.amazon.event.ruler.Range.digitSequence(Range.java:105)
       software.amazon.event.ruler.ByteMachine.findRangePattern(ByteMachine.java:964)
       [...]
  JUnit Jupiter:ByteMachine_findRangePattern_Test:testFindRangePattern_SingleValueClosed()
    MethodSource [className = 'software.amazon.event.ruler.ByteMachine_findRangePattern_Test', methodName = 'testFindRangePattern_SingleValueClosed', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Expected a non-null NameState result for the same single value range. ==> expected: not <null>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertNotNull.failNull(AssertNotNull.java:49)
       org.junit.jupiter.api.AssertNotNull.assertNotNull(AssertNotNull.java:35)
       org.junit.jupiter.api.Assertions.assertNotNull(Assertions.java:312)
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.testFindRangePattern_SingleValueClosed(ByteMachine_findRangePattern_Test.java:99)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       [...]
  JUnit Jupiter:ByteMachine_findRangePattern_Test:testFindRangePattern()
    MethodSource [className = 'software.amazon.event.ruler.ByteMachine_findRangePattern_Test', methodName = 'testFindRangePattern', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Expected a non-null NameState result for the valid range. ==> expected: not <null>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertNotNull.failNull(AssertNotNull.java:49)
       org.junit.jupiter.api.AssertNotNull.assertNotNull(AssertNotNull.java:35)
       org.junit.jupiter.api.Assertions.assertNotNull(Assertions.java:312)
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.testFindRangePattern(ByteMachine_findRangePattern_Test.java:47)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       [...]
  JUnit Jupiter:ByteMachine_findRangePattern_Test:testFindRangePattern_EmptyRanges()
    MethodSource [className = 'software.amazon.event.ruler.ByteMachine_findRangePattern_Test', methodName = 'testFindRangePattern_EmptyRanges', methodParameterTypes = '']
    => java.lang.RuntimeException: Failed to invoke findRangePattern
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.invokeFindRangePattern(ByteMachine_findRangePattern_Test.java:37)
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.testFindRangePattern_EmptyRanges(ByteMachine_findRangePattern_Test.java:128)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       jdk.internal.reflect.GeneratedMethodAccessor13.invoke(Unknown Source)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       software.amazon.event.ruler.ByteMachine_findRangePattern_Test.invokeFindRangePattern(ByteMachine_findRangePattern_Test.java:35)
       [...]
     Caused by: java.lang.ArrayIndexOutOfBoundsException: Index 0 out of bounds for length 0
       software.amazon.event.ruler.ByteMachine.findRangePattern(ByteMachine.java:956)
       [...]

Test run finished after 446 ms
[         4 containers found      ]
[         0 containers skipped    ]
[         4 containers started    ]
[         0 containers aborted    ]
[         4 containers successful ]
[         0 containers failed     ]
[        22 tests found           ]
[         0 tests skipped         ]
[        22 tests started         ]
[         0 tests aborted         ]
[         4 tests successful      ]
[        18 tests failed          ]


```


And you can use the following related information to assist you in your analysis and correction:

api document of class software.amazon.event.ruler.ByteMachine: 

 * Represents a UTF8-byte-level state machine that matches a Ruler state machine's field values.
 * Each state is a map keyed by utf8 byte. getTransition(byte) yields a Target, which can contain either or
 *  both of the next ByteState, and the first of a chain of Matches, which indicate a match to some pattern.
 

return type: 
software.amazon.event.ruler.NameState

dependent classes: 
class `software.amazon.event.ruler.Range`:
	dependent fields:
		byte[] software.amazon.event.ruler.Range.bottom;
		boolean software.amazon.event.ruler.Range.openBottom;
		boolean software.amazon.event.ruler.Range.openTop;
		byte[] software.amazon.event.ruler.Range.top;
	related functions:
		method `public boolean equals(Object)` returns `boolean`, related with `software.amazon.event.ruler.ByteMachine.findRangePattern(Range)`;
		method `public String toString()` returns `java.lang.String`, related with `software.amazon.event.ruler.ByteMachine.findRangePattern(Range)`;
class `software.amazon.event.ruler.ByteMachine`:
	dependent fields:
		software.amazon.event.ruler.ByteState software.amazon.event.ruler.ByteMachine.startState;
	related functions:
		method `private NameState findMatchPattern(InputCharacter[], Patterns)` returns `software.amazon.event.ruler.NameState`, related with `software.amazon.event.ruler.ByteMachine.findRangePattern(Range)`;
		method `private NameState addRangePattern(Range)` returns `software.amazon.event.ruler.NameState`, related with `software.amazon.event.ruler.ByteMachine.findRangePattern(Range)`;
		method `private void deleteRangePattern(Range)` returns `void`, related with `software.amazon.event.ruler.ByteMachine.findRangePattern(Range)`;
class `software.amazon.event.ruler.Constants`:
	dependent fields:
		byte software.amazon.event.ruler.Constants.MAX_DIGIT;
	
class `software.amazon.event.ruler.MachineComplexityEvaluator`:
	related functions:
		method `private static Set<Patterns> getWildcardPatterns(Set<ByteMatch>)` returns `java.util.Set<software.amazon.event.ruler.Patterns>`, related with `software.amazon.event.ruler.ByteMachine.findRangePattern(Range)`;
class `software.amazon.event.ruler.NameState`:
	related functions:
		method ` Set<NameState> getNameTransitions(String[])` returns `java.util.Set<software.amazon.event.ruler.NameState>`, related with `software.amazon.event.ruler.ByteMachine.findRangePattern(Range)`;
class `software.amazon.event.ruler.JsonRuleCompiler`:
	related functions:
		method `private static Patterns processAnythingButEqualsIgnoreCaseMatchExpression(JsonParser, JsonToken)` returns `software.amazon.event.ruler.Patterns`, related with `software.amazon.event.ruler.ByteMachine.findRangePattern(Range)`;



Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 
