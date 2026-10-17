As a professional Java software testing expert, your task is to generate test cases for a focal method.

Guidelines:
1. Analyze the method's functionality and identify possible input combinations including normal cases and boundary conditions.
2. Review the provided existing test cases. Avoid duplication and create new test cases for untested input combinations.

Context Provided:
1. The focal method that needs testing.
2. Relevant code and metadata for the class containing the method, including fields and other methods.
3. The existing test cases (in JSON format) to help avoid redundancy.

Output Instructions:
- Organize the test cases by the name of the test function (group).
- Each test case must include:
    - input: A list of parameter names and values. Use an empty list `[]` if no input is required.
    - expected: The return value or expected observable behavior.
    - description: A clear and concise explanation of the scenario and the intent of the test case.

Maintain the following strict JSON format:
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
Here is the code of focal method, `hash32(long, long, int)`, with the focal class `MurmurHash3`:
```Java
package org.apache.commons.codec.digest;
import org.apache.commons.codec.binary.StringUtils;
public final class MurmurHash3 {
    public static final int DEFAULT_SEED = 104729;
    public static int hash32(final long data1, final long data2, final int seed) {
        int hash = seed;
        final long r0 = Long.reverseBytes(data1);
        final long r1 = Long.reverseBytes(data2);
        hash = mix32(((int) (r0)), hash);
        hash = mix32(((int) (r0 >>> 32)), hash);
        hash = mix32(((int) (r1)), hash);
        hash = mix32(((int) (r1 >>> 32)), hash);
        hash ^= Long.BYTES * 2;
        return fmix32(hash);
    }
private static int fmix32(int hash);
    public static long[] hash128(final byte[] data);
    public static long[] hash128x64(final byte[] data);
    public static long[] hash128x64(final byte[] data, final int offset, final int length, final int seed);
    public static int hash32(final long data);
    public static int hash32(final long data, final int seed);
    public static int hash32(final long data1, final long data2);
    public static int hash32x86(final byte[] data);
    public static int hash32x86(final byte[] data, final int offset, final int length, final int seed);
    private static int mix32(int k, int hash);
}
```

@input{existing test case}
here's the existing test cases:
```json
<cases_json>
```

@output{test cases}: output the test cases in the JSON format: