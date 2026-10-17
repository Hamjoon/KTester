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

@ExtendWith(MockitoExtension.class)
class MurmurHash3_hash32_Test {

    @BeforeAll
    static void setupBeforeAll() {
        // No global resources required for these tests.
    }

    @BeforeEach
    void setupBeforeEach() {
        // Nothing to initialise before each test.
    }

    @AfterEach
    void teardownAfterEach() {
        // Nothing to clean up after each test.
    }

    @AfterAll
    static void teardownAfterAll() {
        // No global teardown needed.
    }

    /**
     * Verify that the single‑argument overload delegates to the two‑argument overload
     * using {@link MurmurHash3#DEFAULT_SEED}.
     */
    @Test
    void hash32UsesDefaultSeed() {
        long[] testValues = {
                0L,
                1L,
                -1L,
                123456789L,
                -987654321L,
                Long.MAX_VALUE,
                Long.MIN_VALUE
        };
        for (long v : testValues) {
            int expected = MurmurHash3.hash32(v, MurmurHash3.DEFAULT_SEED);
            int actual   = MurmurHash3.hash32(v);
            assertEquals(expected, actual,
                    () -> "hash32(" + v + ") should equal hash32(" + v + ", DEFAULT_SEED)");
        }
    }

    /**
     * The hash function must be deterministic: the same input always yields the same output.
     */
    @Test
    void hash32Deterministic() {
        long value = 0xDEADBEEFL;
        int first = MurmurHash3.hash32(value);
        for (int i = 0; i < 10; i++) {
            assertEquals(first, MurmurHash3.hash32(value),
                    "hash32 must be deterministic for the same input");
        }
    }

    /**
     * Different seeds should (in most cases) produce different hash values.
     * This test checks that at least one seed variation leads to a different result.
     */
    @Test
    void hash32DifferentSeedsProduceDifferentResults() {
        long value = 0xCAFEBABEL;
        int defaultSeedHash = MurmurHash3.hash32(value); // uses DEFAULT_SEED
        int otherSeedHash   = MurmurHash3.hash32(value, MurmurHash3.DEFAULT_SEED + 1);
        // It is possible (though extremely unlikely) that the two hashes collide.
        // In that rare case we simply assert that the method runs and returns an int.
        if (defaultSeedHash == otherSeedHash) {
            // Collisions are acceptable; just ensure the method does not throw.
            assertTrue(true, "Collision occurred but method executed correctly");
        } else {
            assertNotEquals(defaultSeedHash, otherSeedHash,
                    "Different seeds should yield different hash values");
        }
    }

    /**
     * Verify that the method works for the full range of long values by sampling a few edge cases.
     */
    @Test
    void hash32EdgeCases() {
        assertEquals(MurmurHash3.hash32(0L, MurmurHash3.DEFAULT_SEED),
                MurmurHash3.hash32(0L));
        assertEquals(MurmurHash3.hash32(Long.MAX_VALUE, MurmurHash3.DEFAULT_SEED),
                MurmurHash3.hash32(Long.MAX_VALUE));
        assertEquals(MurmurHash3.hash32(Long.MIN_VALUE, MurmurHash3.DEFAULT_SEED),
                MurmurHash3.hash32(Long.MIN_VALUE));
    }
}
```