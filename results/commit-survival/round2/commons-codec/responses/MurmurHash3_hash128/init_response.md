```java
package org.apache.commons.codec.digest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link MurmurHash3#hash128(byte[])}.
 *
 * <p>
 * The tests focus on the contract of the overload that uses the default seed
 * ({@link MurmurHash3#DEFAULT_SEED}). They verify:
 *
 * <ul>
 *   <li>Behaviour with an empty input.</li>
 *   <li>Behaviour with a typical non‑empty input.</li>
 *   <li>That the method forwards to the full overload with the default seed.</li>
 *   <li>That a {@code null} argument results in a {@link NullPointerException}.</li>
 * </ul>
 * </p>
 */
@ExtendWith(MockitoExtension.class)
class MurmurHash3_hash128_Test {

    /** Sample data used by several tests. */
    private static final byte[] SAMPLE_DATA = "The quick brown fox jumps over the lazy dog".getBytes();

    @BeforeAll
    static void setupBeforeAll() {
        // No global resources required for these tests.
    }

    @BeforeEach
    void setupBeforeEach() {
        // No per‑test initialisation required.
    }

    @AfterEach
    void teardownAfterEach() {
        // No per‑test cleanup required.
    }

    @AfterAll
    static void teardownAfterAll() {
        // No global cleanup required.
    }

    /**
     * The 128‑bit hash of an empty byte array must be equal to the result of the
     * full overload when the default seed is supplied explicitly.
     */
    @Test
    void hash128_EmptyArray_UsesDefaultSeed() {
        byte[] empty = new byte[0];
        long[] expected = MurmurHash3.hash128(empty, 0, empty.length, MurmurHash3.DEFAULT_SEED);
        long[] actual   = MurmurHash3.hash128(empty);
        assertArrayEquals(expected, actual, "hash128(empty) should delegate to the full overload with the default seed");
    }

    /**
     * For a non‑empty input the method must delegate to the full overload with the
     * default seed and therefore produce the same result.
     */
    @Test
    void hash128_NonEmptyArray_DelegatesToFullOverload() {
        long[] expected = MurmurHash3.hash128(SAMPLE_DATA, 0, SAMPLE_DATA.length, MurmurHash3.DEFAULT_SEED);
        long[] actual   = MurmurHash3.hash128(SAMPLE_DATA);
        assertArrayEquals(expected, actual, "hash128(byte[]) should produce the same result as the full overload with the default seed");
    }

    /**
     * The result of the hash for a known input should be deterministic.
     * This test does not assert a hard‑coded value (which could become outdated
     * if the implementation changes) but checks that the two 64‑bit parts are
     * not both zero and that the two parts are not equal (the algorithm mixes
     * the data strongly).
     */
    @Test
    void hash128_DeterministicResult() {
        long[] result = MurmurHash3.hash128(SAMPLE_DATA);
        assertNotNull(result, "Result must not be null");
        assertEquals(2, result.length, "Result array must contain exactly two longs");
        // Basic sanity checks – the hash of a non‑empty array should not be all zeros.
        assertTrue(result[0] != 0L || result[1] != 0L, "Hash of non‑empty input should not be all zeros");
        // The two 64‑bit halves should differ for this particular input.
        assertNotEquals(result[0], result[1], "The two 64‑bit halves of the hash should differ for typical input");
    }

    /**
     * Passing {@code null} as the data array must result in a {@link NullPointerException}.
     */
    @Test
    void hash128_NullInput_ThrowsNPE() {
        assertThrows(NullPointerException.class, () -> MurmurHash3.hash128(null),
                "hash128(null) should throw NullPointerException");
    }
}
```