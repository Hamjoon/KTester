You are tasked with creating a framework for a test class in Java. Now analyze the following information carefully:

1. The method that needs to be tested and its parameters.
2. The constructor of the target class and its parameters.
3. Any dependencies or external resources used by the target class.

Based on this analysis, complete the test class template in the provided structure:

1. Initialize necessary fields and dependencies within setupBeforeAll() and setupBeforeEach().
2. Handle any required cleanup in teardownAfterEach() and teardownAfterAll().
3. Make sure not to invoke any private methods or fields of the target class and dependent class.
4. Refer to the "given" phase in the existing test class for guidance.
5. Add necessary import statements and ensure there are no syntax errors in the generated code.

@input{focal method and target class}
Here is the code of focal method, `hash32x86(byte[])`, with the focal class `MurmurHash3_hash32x86_Test`:
```Java
package org.apache.commons.codec.digest;
import org.apache.commons.codec.binary.StringUtils;
public final class MurmurHash3 {
    public static final int DEFAULT_SEED = 104729;
    public static int hash32x86(final byte[] data) {
        return hash32x86(data, 0, data.length, 0);
    }
public static long[] hash128(final byte[] data);
    public static long[] hash128x64(final byte[] data);
    public static long[] hash128x64(final byte[] data, final int offset, final int length, final int seed);
    public static int hash32(final long data);
    public static int hash32(final long data, final int seed);
    public static int hash32(final long data1, final long data2);
    public static int hash32(final long data1, final long data2, final int seed);
    public static int hash32x86(final byte[] data, final int offset, final int length, final int seed);
}
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
 

constructors for class `org.apache.commons.codec.digest.MurmurHash3`: 
params: 
body:
```java
    private MurmurHash3() {
    }
}
```

existing test class: 
```java
/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.codec.digest;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.codec.digest.MurmurHash3.IncrementalHash32;
import org.apache.commons.codec.digest.MurmurHash3.IncrementalHash32x86;
import org.junit.jupiter.api.Test;

/**
 * Test for {@link MurmurHash3}.
 */
@SuppressWarnings("deprecation")
public class MurmurHash3Test {

    /** Test data for the hash64 method. */
    private static final String TEST_HASH64 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?";

    /**
     * 256 integers in the range [0,255] arranged in random order.
     * This ensures all bytes are covered in a full hash of the bytes.
     *
     * <p>To create test data using the python library mmh3 (which invokes the c++ code):</p>
     *
     * <pre>
     * import mmh3
     * import numpy as np
     * # Put random bytes in here:
     * bytes = np.uint8([46,246,249,184,247,84,99,144,62,77,195,220,92,20,150,159,38,40,124,252,185,28,63,13,213,172,85,198,118,74,109,157,132,216,76,177,173,23,140,86,146,95,54,176,114,179,234,174,183,141,122,12,60,116,200,142,6,167,59,240,33,29,165,111,243,30,219,110,255,53,32,35,64,225,96,152,70,41,133,80,244,127,57,199,5,164,151,49,26,180,203,83,108,39,126,208,42,206,178,19,69,223,71,231,250,125,211,232,189,55,44,82,48,221,43,192,241,103,155,27,51,163,21,169,91,94,217,191,78,72,93,102,104,105,8,113,100,143,89,245,227,120,160,251,153,145,45,218,168,233,229,253,67,22,182,98,137,128,135,11,214,66,73,171,188,170,131,207,79,106,24,75,237,194,7,129,215,81,248,242,16,25,136,147,156,97,52,10,181,17,205,58,101,68,230,1,37,0,222,88,130,148,224,47,50,197,34,212,196,209,14,36,139,228,154,31,175,202,236,161,3,162,190,254,134,119,4,61,65,117,186,107,204,9,187,201,90,149,226,56,239,238,235,112,87,18,121,115,138,123,210,2,193,166,158,15])
     * # Seed as appropriate. The default seed in mmh3 is zero.
     * seed = 789
     * # Output data. Adjust as appropriate.
     * for x in range(0, 256):
     *   # 32-bit hash
     *   mmh3.hash(bytes[:x])
     *   mmh3.hash(bytes[:x], seed)
     *   # 128-bit hash as two 64 bit signed ints
     *   mmh3.hash64(bytes[:x])
     *   mmh3.hash64(bytes[:x], seed)
     * # Sub-arrays: lower inclusive, upper exclusive:
     * mmh3.hash(bytes[13:15])
     * </pre>
     */
    private static final int[] RANDOM_INTS = { 46, 246, 249, 184, 247, 84, 99, 144, 62, 77, 195, 220, 92, 20, 150, 159, 38, 40, 124, 252, 185, 28, 63, 13, 213,
            172, 85, 198, 118, 74, 109, 157, 132, 216, 76, 177, 173, 23, 140, 86, 146, 95, 54, 176, 114, 179, 234, 174, 183, 141, 122, 12, 60, 116, 200, 142, 6,
            167, 59, 240, 33, 29, 165, 111, 243, 30, 219, 110, 255, 53, 32, 35, 64, 225, 96, 152, 70, 41, 133, 80, 244, 127, 57, 199, 5, 164, 151, 49, 26, 180,
            203, 83, 108, 39, 126, 208, 42, 206, 178, 19, 69, 223, 71, 231, 250, 125, 211, 232, 189, 55, 44, 82, 48, 221, 43, 192, 241, 103, 155, 27, 51, 163,
            21, 169, 91, 94, 217, 191, 78, 72, 93, 102, 104, 105, 8, 113, 100, 143, 89, 245, 227, 120, 160, 251, 153, 145, 45, 218, 168, 233, 229, 253, 67, 22,
            182, 98, 137, 128, 135, 11, 214, 66, 73, 171, 188, 170, 131, 207, 79, 106, 24, 75, 237, 194, 7, 129, 215, 81, 248, 242, 16, 25, 136, 147, 156, 97,
            52, 10, 181, 17, 205, 58, 101, 68, 230, 1, 37, 0, 222, 88, 130, 148, 224, 47, 50, 197, 34, 212, 196, 209, 14, 36, 139, 228, 154, 31, 175, 202, 236,
            161, 3, 162, 190, 254, 134, 119, 4, 61, 65, 117, 186, 107, 204, 9, 187, 201, 90, 149, 226, 56, 239, 238, 235, 112, 87, 18, 121, 115, 138, 123, 210,
            2, 193, 166, 158, 15 };

    /**
     * 256 bytes in the range [0,255] arranged in random order.
     * This ensures all bytes are covered in a full hash of the bytes.
     */
    private static final byte[] RANDOM_BYTES;

    static {
        RANDOM_BYTES = new byte[RANDOM_INTS.length];
        for (int i = 0; i < RANDOM_BYTES.length; i++) {
            RANDOM_BYTES[i] = (byte) RANDOM_INTS[i];
        }
    }

    // Tests match the declared order of the class:
    // hash32
    // hash64
    // hash128
    // IncrementalHash32
    //
    // There is no reference for the hash64 method. It is not in the original murmurhash3 code.
    // Thus hash64 is not extensively tested and the reference results are assumed to be from the
    // Apache Hive project which was the source of the method.
......
            assertIncrementalHash32x86(bytes, seed, 1, 1, 1, 1, 1, 1, 1, 1);
            // Leading unprocessed 1, 2, 3
            assertIncrementalHash32x86(bytes, seed, 1, 4);
            assertIncrementalHash32x86(bytes, seed, 2, 4);
            assertIncrementalHash32x86(bytes, seed, 3, 4);
            // Trailing unprocessed 1, 2, 3
            assertIncrementalHash32x86(bytes, seed, 4, 1);
            assertIncrementalHash32x86(bytes, seed, 4, 2);
            assertIncrementalHash32x86(bytes, seed, 4, 3);
            // Complete blocks
            assertIncrementalHash32x86(bytes, seed, 4, 16, 64);
            // Some random blocks
            for (int i = 0; i < 10; i++) {
                assertIncrementalHash32x86(bytes, seed, createRandomBlocks(bytes.length));
            }
        }
    }

    /**
     * This test hits an edge case where a very large number of bytes is added to the incremental hash. The data is constructed so that an integer counter of
     * unprocessed bytes will overflow. If this is not handled correctly then the code throws an exception when it copies more data into the unprocessed bytes
     * array.
     */
    @Test
    public void testIncrementalHashWithUnprocessedBytesAndHugeLengthArray() {
        // Assert the test precondition that a large array added to unprocessed bytes
        // will overflow an integer counter. We use the smallest hugeLength possible
        // as some VMs cannot allocate maximum length arrays.
        final int unprocessedSize = 3;
        final int hugeLength = Integer.MAX_VALUE - 2;
        assertTrue(unprocessedSize + hugeLength < 4, "This should overflow to negative");

        // Check the test can be run
        byte[] bytes = null;
        try {
            bytes = new byte[hugeLength];
        } catch (final OutOfMemoryError ignore) {
            // Some VMs cannot allocate an array this large.
            // Some test environments may not have enough available memory for this.
        }
        assumeTrue(bytes != null, "Cannot allocate array of length " + hugeLength);

        final IncrementalHash32x86 inc = new IncrementalHash32x86();
        inc.start(0);
        // Add bytes that should be unprocessed
        inc.add(bytes, 0, unprocessedSize);
        // Add a huge number of bytes to overflow an integer counter of unprocessed bytes.
        inc.add(bytes, 0, hugeLength);
    }
}
```



@input{test class template}
```java
package org.apache.commons.codec.digest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
// Add other imports if necessary

@ExtendWith(MockitoExtension.class)
class MurmurHash3_hash32x86_Test {
    @BeforeAll
    static void setupBeforeAll() {
    }

    @BeforeEach
    void setupBeforeEach() {
    }

    @AfterEach
    void teardownAfterEach() {
    }

    @AfterAll
    static void teardownAfterAll() {
    }
}
```

@output{initial test class}: now consider the invoke examples output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.