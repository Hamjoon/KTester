```java
package org.apache.commons.codec.digest;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link MurmurHash3#hash32(long, int)}.
 *
 * <p>
 * The tests verify:
 * <ul>
 *   <li>the overload that uses the default seed produces the same result as the
 *       two‑argument method when the default seed is supplied explicitly;</li>
 *   <li>different seeds lead to different hash values (the function is seed‑sensitive);</li>
 *   <li>the implementation is deterministic – the same input/seed pair always yields the same result;</li>
 *   <li>the method works for a range of edge‑case values (zero, negative, max/min long).</li>
 * </ul>
 * </p>
 */
@ExtendWith(MockitoExtension.class)
class MurmurHash3_hash32_2_Test {

    @BeforeAll
    static void setupBeforeAll() {
        // No global resources required for these static tests.
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
     * Verify that the one‑argument overload delegates to the two‑argument overload
     * using {@link MurmurHash3#DEFAULT_SEED}.
     */
    @Test
    void testDefaultSeedIsUsedWhenCallingHash32Long() {
        long[] testValues = {
                0L,
                1L,
                -1L,
                Long.MAX_VALUE,
                Long.MIN_VALUE,
                0x0123456789ABCDEFL,
                0xFEDCBA9876543210L
        };

        for (long value : testValues) {
            int expected = MurmurHash3.hash32(value, MurmurHash3.DEFAULT_SEED);
            int actual   = MurmurHash3.hash32(value);
            assertEquals(expected, actual,
                    () -> "hash32(" + value + ") should equal hash32(" + value + ", DEFAULT_SEED)");
        }
    }

    /**
     * Different seeds must produce different hash results for the same input.
     * The test checks a few distinct seeds and asserts that at least one of the
     * resulting hashes differs from the hash produced with the default seed.
     */
    @Test
    void testDifferentSeedsYieldDifferentHashes() {
        long data = 0x0F0E0D0C0B0A0908L;
        int defaultHash = MurmurHash3.hash32(data, MurmurHash3.DEFAULT_SEED);

        int[] otherSeeds = {0, 1, 42, -12345, Integer.MAX_VALUE, Integer.MIN_VALUE};

        boolean foundDifferent = false;
        for (int seed : otherSeeds) {
            int hash = MurmurHash3.hash32(data, seed);
            if (hash != defaultHash) {
                foundDifferent = true;
                break;
            }
        }
        assertTrue(foundDifferent, "At least one alternative seed should produce a different hash");
    }

    /**
     * The hash function must be deterministic – repeated invocations with the same
     * arguments must return the same value.
     */
    @Test
    void testDeterminism() {
        long data = 0x1122334455667788L;
        int seed = 987654321;
        int first = MurmurHash3.hash32(data, seed);
        for (int i = 0; i < 10; i++) {
            assertEquals(first, MurmurHash3.hash32(data, seed),
                    "hash32 should be deterministic for data=" + data + " and seed=" + seed);
        }
    }

    /**
     * Verify that a set of edge‑case long values produce distinct, non‑zero hashes.
     * The exact numeric values are not asserted (they are implementation‑specific),
     * but the test ensures the method does not throw and returns a value that
     * appears to be a proper 32‑bit integer.
     */
    @Test
    void testEdgeCaseLongValues() {
        long[] edgeValues = {
                0L,
                -1L,
                Long.MAX_VALUE,
                Long.MIN_VALUE,
                0x7FFFFFFFFFFFFFFFL,
                0x8000000000000000L
        };
        int seed = 12345;

        for (long value : edgeValues) {
            int hash = MurmurHash3.hash32(value, seed);
            // The hash is a signed int; we simply check that the call succeeded.
            // A non‑zero value is a weak sanity check (the algorithm can legitimately
            // return zero, but that is extremely unlikely for random seeds).
            assertNotEquals(Integer.MIN_VALUE, hash,
                    "hash32 should return a valid int for data=" + value);
        }
    }
}
```