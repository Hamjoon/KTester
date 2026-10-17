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
Here is the code of focal method, `hash128(byte[])`, with the focal class `MurmurHash3`:
```Java
package org.apache.commons.codec.digest;
import org.apache.commons.codec.binary.StringUtils;
public final class MurmurHash3 {
    public static final int DEFAULT_SEED = 104729;
    public static long[] hash128(final byte[] data) {
        return hash128(data, 0, data.length, DEFAULT_SEED);
    }
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
 

api document of method hash128(byte[]): 

     * Generates 128-bit hash from the byte array with a default seed.
     * This is a helper method that will produce the same result as:
     *
     * <pre>
     * int offset = 0;
     * int seed = 104729;
     * int hash = MurmurHash3.hash128(data, offset, data.length, seed);
     * </pre>
     *
     * <p>Note: The sign extension bug in {@link #hash128(byte[], int, int, int)} does not effect
     * this result as the default seed is positive.</p>
     *
     * @param data The input byte array
     * @return The 128-bit hash (2 longs)
     * @see #hash128(byte[], int, int, int)
     

return type: 
long[]

dependent classes: 
class `org.apache.commons.codec.digest.MurmurHash3`:
	dependent fields:
		int org.apache.commons.codec.digest.MurmurHash3.DEFAULT_SEED;
	
class `org.apache.commons.codec.binary.Base64`:
	related functions:
		method ` static byte[] toIntegerBytes(BigInteger)` returns `byte[]`, related with `org.apache.commons.codec.digest.MurmurHash3.hash128(byte[])`, api document: 
     * Returns a byte-array representation of a {@code BigInteger} without sign bit.
     *
     * @param bigInt
     *            {@code BigInteger} to be converted
     * @return a byte array representation of the BigInteger parameter
     ;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.