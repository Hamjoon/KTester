```java
package org.apache.commons.codec.digest;

import org.junit.jupiter.api.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link MurmurHash3#hash32x86(byte[], int, int, int)}.
 *
 * <p>The tests focus on the public contract of the method:
 * <ul>
 *   <li>correct handling of empty input</li>
 *   <li>consistency with the overload {@code hash32x86(byte[])}</li>
 *   <li>different seeds produce different hash values</li>
 *   <li>offset/length parameters correctly select a sub‑range of the input array</li>
 *   <li>invalid arguments throw the expected exceptions</li>
 *   <li>null data throws {@link NullPointerException}</li>
 * </ul>
 * </p>
 *
 * <p>For cases that require the result of the private {@code fmix32} method,
 * reflection is used to invoke it without breaking encapsulation.</p>
 */
class MurmurHash3_hash32x86_2_Test {

    @BeforeAll
    static void setupBeforeAll() {
        // No global resources required for these tests.
    }

    @BeforeEach
    void setupBeforeEach() {
        // No per‑test initialization required.
    }

    @AfterEach
    void teardownAfterEach() {
        // No per‑test cleanup required.
    }

    @AfterAll
    static void teardownAfterAll() {
        // No global cleanup required.
    }

    /* --------------------------------------------------------------------- *
     * Existing straightforward tests (kept unchanged)                        *
     * --------------------------------------------------------------------- */

    @Test
    void testEmptyArrayReturnsZero() {
        byte[] empty = new byte[0];
        int hash = MurmurHash3.hash32x86(empty, 0, 0, 0);
        assertEquals(0, hash, "Hash of an empty array with seed 0 must be 0");
    }

    @Test
    void testConsistencyWithSimpleOverload() {
        byte[] data = new byte[]{(byte) 0x12, (byte) 0x34, (byte) 0x56, (byte) 0x78};
        // The simple overload uses offset 0, length = data.length and seed = 0
        int hashSimple = MurmurHash3.hash32x86(data);
        int hashFull = MurmurHash3.hash32x86(data, 0, data.length, 0);
        assertEquals(hashSimple, hashFull,
                "hash32x86(byte[]) must be equivalent to hash32x86(byte[],0,length,0)");
    }

    @Test
    void testDifferentSeedsYieldDifferentHashes() {
        byte[] data = new byte[]{(byte) 0xAA, (byte) 0xBB, (byte) 0xCC, (byte) 0xDD};
        int hashSeedZero = MurmurHash3.hash32x86(data, 0, data.length, 0);
        int hashSeedOne = MurmurHash3.hash32x86(data, 0, data.length, 1);
        assertNotEquals(hashSeedZero, hashSeedOne,
                "Different seeds should produce different hash values");
    }

    @Test
    void testSubArrayHashMatchesSlice() {
        byte[] source = new byte[20];
        for (int i = 0; i < source.length; i++) {
            source[i] = (byte) i; // deterministic content 0..19
        }
        int offset = 5;
        int length = 8; // bytes 5..12 inclusive (8 bytes)
        byte[] slice = java.util.Arrays.copyOfRange(source, offset, offset + length);

        int hashFull = MurmurHash3.hash32x86(source, offset, length, 0);
        int hashSlice = MurmurHash3.hash32x86(slice, 0, slice.length, 0);
        assertEquals(hashSlice, hashFull,
                "Hash of a sub‑range must equal hash of an independent array containing that range");
    }

    @Test
    void testMultipleBlocksAndTailProcessing() {
        // 10 bytes = 2 full 4‑byte blocks + a 2‑byte tail
        byte[] data = new byte[]{
                (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04,
                (byte) 0x05, (byte) 0x06, (byte) 0x07, (byte) 0x08,
                (byte) 0x09, (byte) 0x0A
        };
        // Compute hash via the method under test
        int hash = MurmurHash3.hash32x86(data, 0, data.length, 12345);
        // Deterministic repeatability check
        assertEquals(hash, MurmurHash3.hash32x86(data, 0, data.length, 12345),
                "Repeated invocation with identical parameters must return the same result");
    }

    /* --------------------------------------------------------------------- *
     * New tests derived from the JSON specification                         *
     * --------------------------------------------------------------------- */

    /** Helper that invokes the private static fmix32(int) method via reflection. */
    private static int fmix32(int hash) {
        try {
            Method m = MurmurHash3.class.getDeclaredMethod("fmix32", int.class);
            m.setAccessible(true);
            return (int) m.invoke(null, hash);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Unable to invoke fmix32 via reflection", e);
        }
    }

    /** Parameter provider for invalid‑argument cases that must throw {@link ArrayIndexOutOfBoundsException}. */
    private static Stream<Arguments> invalidArgumentProvider() {
        return Stream.of(
                // Negative offset
                Arguments.of(new byte[]{0x01, 0x02}, -1, 2, 0, ArrayIndexOutOfBoundsException.class,
                        "Negative offset should cause an out‑of‑bounds access when getLittleEndianInt or tail processing reads the array."),
                // Length exceeds array size
                Arguments.of(new byte[]{0x01, 0x02}, 0, 5, 0, ArrayIndexOutOfBoundsException.class,
                        "Length that exceeds the array size should trigger an exception during block or tail read.")
        );
    }

    @ParameterizedTest(name = "[{index}] {4}")
    @MethodSource("invalidArgumentProvider")
    void testInvalidArgumentsThrowException(byte[] data, int offset, int length, int seed,
                                            Class<? extends Exception> expectedException,
                                            String description) {
        Exception ex = assertThrows(expectedException, () ->
                MurmurHash3.hash32x86(data, offset, length, seed));
        // The message is not asserted – we only verify the type matches the contract.
        assertNotNull(ex, description);
    }

    @Test
    void testNullDataThrowsNPE() {
        assertThrows(NullPointerException.class,
                () -> MurmurHash3.hash32x86(null, 0, 0, 0),
                "Passing a null byte array should cause a NullPointerException before any processing occurs.");
    }

    @Nested
    @DisplayName("Zero‑length input cases")
    class ZeroLengthTests {

        @Test
        @DisplayName("Empty array with seed 0 returns 0")
        void emptyArraySeedZero() {
            byte[] empty = new byte[0];
            int hash = MurmurHash3.hash32x86(empty, 0, 0, 0);
            assertEquals(0, hash, "An empty array with length 0 and seed 0 must return 0");
        }

        @Test
        @DisplayName("Empty array with non‑zero seed returns fmix32(seed)")
        void emptyArrayNonZeroSeed() {
            byte[] empty = new byte[0];
            int seed = 12_345;
            int expected = fmix32(seed);
            int actual = MurmurHash3.hash32x86(empty, 0, 0, seed);
            assertEquals(expected, actual,
                    "Empty input with a non‑zero seed should return fmix32(seed)");
        }
    }
}
```