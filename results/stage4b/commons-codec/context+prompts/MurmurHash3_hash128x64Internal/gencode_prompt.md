You are a professional Java software testing expert. Your task is to generate high-quality unit test code for a focal method, using **JUnit5** and **Mockito** libraries. 

You'll be provided with:
1. The focal method to test.
2. The target class's structure (fields and methods).
3. A pre-defined test class framework with `@Before`/`@After` methods.
4. Test cases in JSON format.
5. Any relevant context.

**Requirements:**

Code Quality:
- Include clear comments explaining each test scenario.
- Ensure code is clean, understandable, and maintainable.
- Do not directly access private methods or fields. **Use reflection if the focal method is private.**
- Include all necessary imports; code must be free of syntax errors.

**Test Structure:**
- Follow the given test class framework; do not modify `@Before`/`@After` methods.
- Create one test function per test case group, ensuring all cases are covered.
- Use parameterized tests where appropriate.
- Reinitialize variables inside test functions if `@Setup` values don't fit.
- Properly mock and set up dependencies.

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

@input{initial test class framework}
```Java
<initial_class>
```

@input{existing test case}
here's the existing test cases:
```json
<cases_json>
```


@input{context information}

api document of class org.apache.commons.codec.digest.MurmurHash3: 

 * Implementation of the MurmurHash3 32-bit and 128-bit hash functions.
 *
 * <p>
 * MurmurHash is a non-cryptographic hash function suitable for general hash-based lookup. The name comes from two basic
 * operations, multiply (MU) and rotate (R), used in its inner loop. Unlike cryptographic hash functions, it is not
 * specifically designed to be difficult to reverse by an adversary, making it unsuitable for cryptographic purposes.
 * </p>
 *
 * <p>
 * This contains a Java port of the 32-bit hash function {@code MurmurHash3_x86_32} and the 128-bit hash function
 * {@code MurmurHash3_x64_128} from Austin Appleby's original {@code c++} code in SMHasher.
 * </p>
 *
 * <p>
 * This is public domain code with no copyrights. From home page of
 * <a href="https://github.com/aappleby/smhasher">SMHasher</a>:
 * </p>
 *
 * <blockquote> "All MurmurHash versions are public domain software, and the author disclaims all copyright to their
 * code." </blockquote>
 *
 * <p>
 * Original adaption from Apache Hive. That adaption contains a {@code hash64} method that is not part of the original
 * MurmurHash3 code. It is not recommended to use these methods. They will be removed in a future release. To obtain a
 * 64-bit hash use half of the bits from the {@code hash128x64} methods using the input data converted to bytes.
 * </p>
 *
 * @see <a href="https://en.wikipedia.org/wiki/MurmurHash">MurmurHash</a>
 * @see <a href="https://github.com/aappleby/smhasher/blob/master/src/MurmurHash3.cpp"> Original MurmurHash3 c++
 *      code</a>
 * @see <a href=
 *      "https://github.com/apache/hive/blob/master/storage-api/src/java/org/apache/hive/common/util/Murmur3.java">
 *      Apache Hive Murmer3</a>
 * @since 1.13
 

api document of method hash128x64Internal(byte[], int, int, long): 

     * Generates 128-bit hash from the byte array with the given offset, length and seed.
     *
     * <p>This is an implementation of the 128-bit hash function {@code MurmurHash3_x64_128}
     * from Austin Appleby's original MurmurHash3 {@code c++} code in SMHasher.</p>
     *
     * @param data The input byte array
     * @param offset The first element of array
     * @param length The length of array
     * @param seed The initial seed value
     * @return The 128-bit hash (2 longs)
     

return type: 
long[]

dependent classes: 
class `org.apache.commons.codec.digest.MurmurHash3`:
	dependent fields:
		int org.apache.commons.codec.digest.MurmurHash3.M;
		int org.apache.commons.codec.digest.MurmurHash3.R1;
		long org.apache.commons.codec.digest.MurmurHash3.C1;
		int org.apache.commons.codec.digest.MurmurHash3.R2;
		long org.apache.commons.codec.digest.MurmurHash3.C2;
		int org.apache.commons.codec.digest.MurmurHash3.N2;
		int org.apache.commons.codec.digest.MurmurHash3.R3;
		int org.apache.commons.codec.digest.MurmurHash3.N1;
	
class `org.apache.commons.codec.binary.Base16`:
	related functions:
		method ` void decode(byte[], int, int, Context)` returns `void`, related with `org.apache.commons.codec.digest.MurmurHash3.hash128x64Internal(byte[], int, int, long)`;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.