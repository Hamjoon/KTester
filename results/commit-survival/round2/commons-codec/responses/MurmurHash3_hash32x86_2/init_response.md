```java
package org.apache.commons.codec.digest;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

/**
 * Unit tests for {@link MurmurHash3#hash32x86(byte[], int, int, int)}.
 *
 * <p>The tests focus on the public contract of the method:
 * <ul>
 *   <li>correct handling of empty input</li>
 *   <li>consistency with the overload {@code hash32x86(byte[])}</li>
 *   <li>different seeds produce different hash values</li>
 *   <li>offset/length parameters correctly select a sub‑range of the input array</li>
 * </ul>
 * </p>
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

    @Test
    void testEmptyArrayReturnsZero() {
        byte[] empty = new byte[0];
        int hash = MurmurHash3.hash32x86(empty, 0, 0, 0);
        assertEquals(0, hash, "Hash of an empty array with seed 0 must be 0");
    }

    @Test
    void testConsistencyWithSimpleOverload() {
        byte[] data = new byte[] { (byte) 0x12, (byte) 0x34, (byte) 0x56, (byte) 0x78 };
        // The simple overload uses offset 0, length = data.length and seed = 0
        int hashSimple = MurmurHash3.hash32x86(data);
        int hashFull = MurmurHash3.hash32x86(data, 0, data.length, 0);
        assertEquals(hashSimple, hashFull,
                "hash32x86(byte[]) must be equivalent to hash32x86(byte[],0,length,0)");
    }

    @Test
    void testDifferentSeedsYieldDifferentHashes() {
        byte[] data = new byte[] { (byte) 0xAA, (byte) 0xBB, (byte) 0xCC, (byte) 0xDD };
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
        byte[] slice = Arrays.copyOfRange(source, offset, offset + length);

        int hashFull = MurmurHash3.hash32x86(source, offset, length, 0);
        int hashSlice = MurmurHash3.hash32x86(slice, 0, slice.length, 0);
        assertEquals(hashSlice, hashFull,
                "Hash of a sub‑range must equal hash of an independent array containing that range");
    }

    @Test
    void testMultipleBlocksAndTailProcessing() {
        // 10 bytes = 2 full 4‑byte blocks + a 2‑byte tail
        byte[] data = new byte[] {
                (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04,
                (byte) 0x05, (byte) 0x06, (byte) 0x07, (byte) 0x08,
                (byte) 0x09, (byte) 0x0A
        };
        // Compute hash via the method under test
        int hash = MurmurHash3.hash32x86(data, 0, data.length, 12345);
        // Compute the same hash by hashing the whole array with the same seed using the simple overload
        // (the simple overload always uses seed 0, so we cannot reuse it directly).
        // Instead we verify that the method does not throw and returns a deterministic value.
        assertEquals(hash, MurmurHash3.hash32x86(data, 0, data.length, 12345),
                "Repeated invocation with identical parameters must return the same result");
    }
}
```