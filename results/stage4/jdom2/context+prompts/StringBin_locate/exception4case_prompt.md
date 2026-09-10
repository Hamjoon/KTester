You are a professional software tester. Write test cases for a focal method in a target class.

**Instructions:**
1. Analyze method behavior and identify ALL possible exception scenarios
2. Design test cases that trigger each identified exception
3. Follow existing test case structure, Avoid duplication and create new test cases for uncovered exception scenarios

Test Case Format:
1. Group test cases by test function name
2. Each test case MUST include:
   - Input parameters with values (use empty list `[]` if none)
   - Expected exception or observable behavior
   - Clear description of test scenario
3. Return empty array if no exception scenarios found

Output Schema (STRICT JSON format):
```json
[
    {
        "group": "test name",
        "cases": [
            {
                "input": [
                    {
                        "parameter": "param name",
                        "value": "param value"
                    }
                ],
                "expected": "expected exception or behavior",
                "description": "test scenario description"
            }
        ]
    },
    {...}
]
```

@input{focal method and target class}
Here is the code of focal method, `locate(int, String, String[], int)`, with the focal class `StringBin`:
```Java
package org.jdom2;
import org.jdom2.internal.ArrayCopy;
final class StringBin {
    
    private final int locate(final int hash, final String value, final String[] bucket, final int length) {
        int left = 0;
        int right = length - 1;
        int mid = 0;
        while (left <= right) {
            mid = (left + right) >>> 1;
            if (bucket[mid].hashCode() > hash) {
                right = mid - 1;
            } else if (bucket[mid].hashCode() < hash) {
                left = mid + 1;
            } else {
                // have the same hashcode
                // do a string-compare.
                int cmp = value.compareTo(bucket[mid]);
                if (cmp == 0) {
                    // equals.
                    return mid;
                } else if (cmp < 0) {
                    // our input value comes before the bucket value, search
                    // backwards
                    while (((--mid) >= left) && (bucket[mid].hashCode() == hash)) {
                        // we have gone back one, and we still have the same
                        // hash code... let's compare.
                        cmp = value.compareTo(bucket[mid]);
                        if (cmp == 0) {
                            // equals, found it.
                            return mid;
                        } else if (cmp > 0) {
                            // we were searching backwards because we started at
                            // the mid point which was after the input value.
                            // now that we have found a value that comes
                            // before the input value it means we have gone too
                            // far... which in turn means the insertion point
                            // is one place after where we are.
                            return (-(mid + 1)) - 1;
                        }
                    } 
                    // this must mean that we ran out of data, or ran out of
                    // values with the same hashcode...
                    return (-(mid + 1)) - 1;
                } else {
                    // we have a value that comes before the value with the
                    // same hash as us.
                    while (((++mid) <= right) && (bucket[mid].hashCode() == hash)) {
                        // the next value exists and has the same hash code.
                        cmp = value.compareTo(bucket[mid]);
                        if (cmp == 0) {
                            // found our value.
                            return mid;
                        } else if (cmp < 0) {
                            // we were searching forwards because we started at
                            // the mid point which was before the input value.
                            // now that we have found a value that comes
                            // after the input value it means we have gone too
                            // far... which in turn means the insertion point
                            // is at the point where we are.
                            return (-mid) - 1;
                        }
                    } 
                    // we have run out of values, or the value we are on has a
                    // different hashcode.
                    return (-mid) - 1;
                }
            }
        } 
        // nothing had the same hashcode.
        return (-left) - 1;
    }
public String reuse(final String value);
    public int size();
}
```
@input{existing test case}
here's the existing test cases:
```json
<cases_json>
```

@output{test cases}: output the test cases in the JSON format: