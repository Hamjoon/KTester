As a professional Java software testing expert, your task is to generate test cases for a focal method.

You will be provided with:
1. The focal method that needs testing.
2. Basic information of the target class to which the focus method belongs, including fields and methods.

Ensure the generated test cases meet the following requirements:

Test Coverage Requirements:
- Create test cases to cover all conditional branches in the target method, including `if`, `else if`, `else`, `switch`, and `case` statements.

Output Format:
- Organize the test cases by its functions logically.
- Include input parameters, expected output, and a description for each test case.
- Use empty list `[]` for the input when a test case does not require input parameters.
- Provide an expected return value for each test case, or specify the behavior that should be verified if there's no return value.
- Clearly describe the intention of each test case in the description.

Follow the JSON format provided here for the output:
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
Here is the code of focal method, `hash128x64Internal(byte[], int, int, long)`, with the focal class `MurmurHash3`:
```Java
package org.apache.commons.codec.digest;
import org.apache.commons.codec.binary.StringUtils;
public final class MurmurHash3 {
    public static final int DEFAULT_SEED = 104729;
    private static final long C1 = 0x87c37b91114253d5L;
    private static final long C2 = 0x4cf5ad432745937fL;
    private static final int R1 = 31;
    private static final int R2 = 27;
    private static final int R3 = 33;
    private static final int M = 5;
    private static final int N1 = 0x52dce729;
    private static final int N2 = 0x38495ab5;
    private static long[] hash128x64Internal(final byte[] data, final int offset, final int length, final long seed) {
        long h1 = seed;
        long h2 = seed;
        final int nblocks = length >> 4;
        // body
        for (int i = 0; i < nblocks; i++) {
            final int index = offset + (i << 4);
            long k1 = getLittleEndianLong(data, index);
            long k2 = getLittleEndianLong(data, index + 8);
            // mix functions for k1
            k1 *= C1;
            k1 = Long.rotateLeft(k1, R1);
            k1 *= C2;
            h1 ^= k1;
            h1 = Long.rotateLeft(h1, R2);
            h1 += h2;
            h1 = (h1 * M) + N1;
            // mix functions for k2
            k2 *= C2;
            k2 = Long.rotateLeft(k2, R3);
            k2 *= C1;
            h2 ^= k2;
            h2 = Long.rotateLeft(h2, R1);
            h2 += h1;
            h2 = (h2 * M) + N2;
        }
        // tail
        long k1 = 0;
        long k2 = 0;
        final int index = offset + (nblocks << 4);
        switch ((offset + length) - index) {
            case 15 :
                k2 ^= (((long) (data[index + 14])) & 0xff) << 48;
            case 14 :
                k2 ^= (((long) (data[index + 13])) & 0xff) << 40;
            case 13 :
                k2 ^= (((long) (data[index + 12])) & 0xff) << 32;
            case 12 :
                k2 ^= (((long) (data[index + 11])) & 0xff) << 24;
            case 11 :
                k2 ^= (((long) (data[index + 10])) & 0xff) << 16;
            case 10 :
                k2 ^= (((long) (data[index + 9])) & 0xff) << 8;
            case 9 :
                k2 ^= data[index + 8] & 0xff;
                k2 *= C2;
                k2 = Long.rotateLeft(k2, R3);
                k2 *= C1;
                h2 ^= k2;
            case 8 :
                k1 ^= (((long) (data[index + 7])) & 0xff) << 56;
            case 7 :
                k1 ^= (((long) (data[index + 6])) & 0xff) << 48;
            case 6 :
                k1 ^= (((long) (data[index + 5])) & 0xff) << 40;
            case 5 :
                k1 ^= (((long) (data[index + 4])) & 0xff) << 32;
            case 4 :
                k1 ^= (((long) (data[index + 3])) & 0xff) << 24;
            case 3 :
                k1 ^= (((long) (data[index + 2])) & 0xff) << 16;
            case 2 :
                k1 ^= (((long) (data[index + 1])) & 0xff) << 8;
            case 1 :
                k1 ^= data[index] & 0xff;
                k1 *= C1;
                k1 = Long.rotateLeft(k1, R1);
                k1 *= C2;
                h1 ^= k1;
        }
        // finalization
        h1 ^= length;
        h2 ^= length;
        h1 += h2;
        h2 += h1;
        h1 = fmix64(h1);
        h2 = fmix64(h2);
        h1 += h2;
        h2 += h1;
        return new long[]{ h1, h2 };
    }
private static long fmix64(long hash);
    private static long getLittleEndianLong(final byte[] data, final int index);
    public static long[] hash128(final byte[] data);
    public static long[] hash128x64(final byte[] data);
    public static long[] hash128x64(final byte[] data, final int offset, final int length, final int seed);
    public static int hash32(final long data);
    public static int hash32(final long data, final int seed);
    public static int hash32(final long data1, final long data2);
    public static int hash32(final long data1, final long data2, final int seed);
    public static int hash32x86(final byte[] data);
    public static int hash32x86(final byte[] data, final int offset, final int length, final int seed);
}
```

@output{test cases}: output the test cases in the JSON format: