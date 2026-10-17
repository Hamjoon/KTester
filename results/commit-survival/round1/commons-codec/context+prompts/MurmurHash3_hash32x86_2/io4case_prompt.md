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
Here is the code of focal method, `hash32x86(byte[], int, int, int)`, with the focal class `MurmurHash3`:
```Java
package org.apache.commons.codec.digest;
import org.apache.commons.codec.binary.StringUtils;
public final class MurmurHash3 {
    public static final int DEFAULT_SEED = 104729;
    private static final int C1_32 = 0xcc9e2d51;
    private static final int C2_32 = 0x1b873593;
    private static final int R1_32 = 15;
    public static int hash32x86(final byte[] data, final int offset, final int length, final int seed) {
        int hash = seed;
        final int nblocks = length >> 2;
        // body
        for (int i = 0; i < nblocks; i++) {
            final int index = offset + (i << 2);
            final int k = getLittleEndianInt(data, index);
            hash = mix32(k, hash);
        }
        // tail
        final int index = offset + (nblocks << 2);
        int k1 = 0;
        switch ((offset + length) - index) {
            case 3 :
                k1 ^= (data[index + 2] & 0xff) << 16;
            case 2 :
                k1 ^= (data[index + 1] & 0xff) << 8;
            case 1 :
                k1 ^= data[index] & 0xff;
                // mix functions
                k1 *= C1_32;
                k1 = Integer.rotateLeft(k1, R1_32);
                k1 *= C2_32;
                hash ^= k1;
        }
        hash ^= length;
        return fmix32(hash);
    }
private static int fmix32(int hash);
    private static int getLittleEndianInt(final byte[] data, final int index);
    public static long[] hash128(final byte[] data);
    public static long[] hash128x64(final byte[] data);
    public static long[] hash128x64(final byte[] data, final int offset, final int length, final int seed);
    public static int hash32(final long data);
    public static int hash32(final long data, final int seed);
    public static int hash32(final long data1, final long data2);
    public static int hash32(final long data1, final long data2, final int seed);
    public static int hash32x86(final byte[] data);
    private static int mix32(int k, int hash);
}
```

@input{existing test case}
here's the existing test cases:
```json
<cases_json>
```

@output{test cases}: output the test cases in the JSON format: