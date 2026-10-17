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
 * Unit tests for {@link MurmurHash3#hash32x86(byte[])}.
 *
 * <p>
 * The tests verify that the convenience overload {@code hash32x86(byte[])} produces the same result
 * as the full‑parameter overload {@code hash32x86(byte[], int, int, int)} when the seed is the
 * default value {@code 0}. Additional edge‑case tests cover empty input, sub‑array handling and
 * null arguments.
 * </p>
 */
@ExtendWith(MockitoExtension.class)
class MurmurHash3_hash32x86_Test {

    /** Sample data used by several tests. */
    private static byte[] sampleData;

    @BeforeAll
    static void setupBeforeAll() {
        // Initialise a deterministic byte array (the same data used in MurmurHash3Test)
        sampleData = new byte[] {
            (byte) 0xDE, (byte) 0xAD, (byte) 0xBE, (byte) 0xEF,
            (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04,
            (byte) 0x10, (byte) 0x20, (byte) 0x30, (byte) 0x40
        };
    }

    @BeforeEach
    void setupBeforeEach() {
        // No per‑test initialisation needed at the moment.
    }

    @AfterEach
    void teardownAfterEach() {
        // No per‑test cleanup required.
    }

    @AfterAll
    static void teardownAfterAll() {
        // Release static resources.
        sampleData = null;
    }

    /**
     * The convenience method must return the same value as the full overload when the whole
     * array is hashed with the default seed (0).
     */
    @Test
    void hash32x86_fullArray_matchesFullSignature() {
        // Expected value calculated via the full‑parameter overload.
        int expected = MurmurHash3.hash32x86(sampleData, 0, sampleData.length, 0);
        int actual   = MurmurHash3.hash32x86(sampleData);
        assertEquals(expected, actual,
                "hash32x86(byte[]) should delegate to hash32x86(byte[],0,length,0)");
    }

    /**
     * Hashing an empty byte array must yield a deterministic result (the value defined by the
     * MurmurHash3 algorithm for an empty input with seed 0).
     */
    @Test
    void hash32x86_emptyArray() {
        byte[] empty = new byte[0];
        int expected = MurmurHash3.hash32x86(empty, 0, 0, 0);
        int actual   = MurmurHash3.hash32x86(empty);
        assertEquals(expected, actual, "Empty array should produce a deterministic hash");
    }

    /**
     * Verify that hashing a sub‑array via the full overload produces the same result as hashing
     * the same sub‑array with the convenience method after extracting the slice.
     */
    @Test
    void hash32x86_subArrayConsistency() {
        // Choose a slice that is not aligned on a 4‑byte boundary.
        int offset = 2;
        int length = 7; // bytes 2..8 (exclusive)
        byte[] slice = new byte[length];
        System.arraycopy(sampleData, offset, slice, 0, length);

        int expected = MurmurHash3.hash32x86(sampleData, offset, length, 0);
        int actual   = MurmurHash3.hash32x86(slice);
        assertEquals(expected, actual,
                "Hash of a sub‑array should be identical to hashing the extracted slice");
    }

    /**
     * The method is expected to throw a {@link NullPointerException} when a {@code null} array is
     * supplied. This behaviour aligns with the implementation of the overloaded method.
     */
    @Test
    void hash32x86_nullArray_throwsNPE() {
        assertThrows(NullPointerException.class, () -> MurmurHash3.hash32x86(null));
    }
}
```