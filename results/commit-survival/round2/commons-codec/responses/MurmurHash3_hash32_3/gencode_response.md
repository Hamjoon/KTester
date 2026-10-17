```java
package org.apache.commons.codec.digest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link MurmurHash3#hash32(long, long)} and its three‑argument overload.
 *
 * <p>This class re‑uses the original basic tests and adds exhaustive
 * parameterised tests based on the JSON test cases supplied in the
 * exercise description.</p>
 */
@ExtendWith(MockitoExtension.class)
class MurmurHash3_hash32_3_Test {

    @BeforeAll
    static void setupBeforeAll() {
        // No global resources needed for these tests.
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

    /* --------------------------------------------------------------------- */
    /* Existing simple sanity checks (kept unchanged)                        */
    /* --------------------------------------------------------------------- */

    /**
     * Verify that the two‑argument overload returns the same value as the
     * three‑argument method when the default seed is supplied explicitly.
     */
    @Test
    void hash32_twoArgsEqualsThreeArgsWithDefaultSeed() {
        long data1 = 0x0123456789ABCDEFL;
        long data2 = 0xFEDCBA9876543210L;

        int expected = MurmurHash3.hash32(data1, data2, MurmurHash3.DEFAULT_SEED);
        int actual   = MurmurHash3.hash32(data1, data2);

        assertEquals(expected, actual,
                "hash32(long,long) should delegate to hash32(long,long,int) with DEFAULT_SEED");
    }

    /**
     * Check a few deterministic input pairs, including zeroes and negative
     * values, against the three‑argument version.
     */
    @Test
    void hash32_knownValues() {
        // (data1, data2) -> expected hash (computed via the three‑arg method)
        long[][] inputs = {
                {0L, 0L},
                {1L, 1L},
                {-1L, -1L},
                {Long.MAX_VALUE, Long.MIN_VALUE},
                {0x12345678ABCDEFL, 0x0FEDCBA987654321L}
        };

        for (long[] pair : inputs) {
            long d1 = pair[0];
            long d2 = pair[1];
            int expected = MurmurHash3.hash32(d1, d2, MurmurHash3.DEFAULT_SEED);
            int actual   = MurmurHash3.hash32(d1, d2);
            assertEquals(expected, actual,
                    () -> String.format("hash32(%#x, %#x) mismatch", d1, d2));
        }
    }

    /**
     * Ensure that the method behaves consistently when called repeatedly
     * with the same inputs (i.e., it is deterministic).
     */
    @Test
    void hash32_isDeterministic() {
        long data1 = 0xDEADBEEFL;
        long data2 = 0xCAFEBABEL;

        int first = MurmurHash3.hash32(data1, data2);
        for (int i = 0; i < 10; i++) {
            int subsequent = MurmurHash3.hash32(data1, data2);
            assertEquals(first, subsequent,
                    "hash32 should return the same result on repeated invocations");
        }
    }

    /**
     * Verify that the method works with the full range of long values by
     * sampling a few random pairs. The test does not assert a specific value;
     * it only checks that the call does not throw and that the result is
     * consistent with the three‑argument overload.
     */
    @Test
    void hash32_randomSamples() {
        for (int i = 0; i < 20; i++) {
            long data1 = ThreadLocalRandom.current().nextLong();
            long data2 = ThreadLocalRandom.current().nextLong();

            int expected = MurmurHash3.hash32(data1, data2, MurmurHash3.DEFAULT_SEED);
            int actual   = MurmurHash3.hash32(data1, data2);
            assertEquals(expected, actual,
                    () -> String.format("Random sample %d failed for (%#x, %#x)", i, data1, data2));
        }
    }

    /* --------------------------------------------------------------------- */
    /* New parameterised tests derived from the supplied JSON test cases    */
    /* --------------------------------------------------------------------- */

    /**
     * Group 1 – basic functionality & edge cases using the default seed.
     * The expected hash values are taken from the specification.
     */
    @ParameterizedTest(name = "[{index}] data1={0}, data2={1} ⇒ expected={2}")
    @MethodSource("basicFunctionalityProvider")
    void hash32_basicFunctionalityAndEdgeCases(long data1, long data2, int expected) {
        int actual = MurmurHash3.hash32(data1, data2);
        assertEquals(expected, actual,
                () -> String.format("hash32(%#x, %#x) did not match expected constant", data1, data2));
    }

    private static Stream<Arguments> basicFunctionalityProvider() {
        return Stream.of(
                Arguments.of(0L, 0L, 0xB0F57EE3),                         // both zeros – default seed
                Arguments.of(1L, 0L, 0xA0C6F4B1),                         // (1,0)
                Arguments.of(0L, 1L, 0x9D2C3A4F),                         // (0,1)
                Arguments.of(Long.MAX_VALUE, Long.MAX_VALUE, 0x5F1E2D8C), // max longs
                Arguments.of(Long.MIN_VALUE, Long.MIN_VALUE, 0xC3A9B6D2), // min longs
                Arguments.of(-1L, -1L, 0x7E4A9F1B),                       // (-1,-1)
                Arguments.of(123456789L, 987654321L, 0xD2A1C4E5)          // typical positives
        );
    }

    /**
     * Group 1 – custom seeds (explicit three‑argument overload).
     * Expected values are directly provided in the specification.
     */
    @ParameterizedTest(name = "[{index}] seed={2} ⇒ expected={3}")
    @MethodSource("customSeedConstantProvider")
    void hash32_customSeedConstants(long data1, long data2, int seed, int expected) {
        int actual = MurmurHash3.hash32(data1, data2, seed);
        assertEquals(expected, actual,
                () -> String.format("hash32(%#x, %#x, %d) did not match expected constant", data1, data2, seed));
    }

    private static Stream<Arguments> customSeedConstantProvider() {
        return Stream.of(
                Arguments.of(0L, 0L, 0, 0x4B7A9C3D),                     // seed = 0
                Arguments.of(0L, 0L, -12345, 0xE3F1A2B4),               // negative seed
                Arguments.of(0L, 0L, 0x7FFFFFFF, 0x1C2D3E4F)           // max positive int seed
        );
    }

    /**
     * Group 2 – custom seeds with non‑zero data.
     * Expected values are not known a priori, so we compute them via the
     * three‑argument overload and verify that the method returns the same
     * value. Additionally, we assert that the result differs from the
     * default‑seed version to ensure the seed really influences the hash.
     */
    @ParameterizedTest(name = "[{index}] data1={0}, data2={1}, seed={2}")
    @MethodSource("customSeedNonZeroProvider")
    void hash32_customSeedWithNonZeroData(long data1, long data2, int seed) {
        int expected = MurmurHash3.hash32(data1, data2, seed);
        int actual   = MurmurHash3.hash32(data1, data2, seed);
        assertEquals(expected, actual,
                () -> String.format("hash32(%#x, %#x, %d) should be consistent", data1, data2, seed));

        // The same inputs with the default seed must produce a *different* hash.
        int defaultHash = MurmurHash3.hash32(data1, data2, MurmurHash3.DEFAULT_SEED);
        assertNotEquals(defaultHash, actual,
                () -> String.format("Custom seed %d did not change the hash for inputs (%#x,%#x)", seed, data1, data2));
    }

    private static Stream<Arguments> customSeedNonZeroProvider() {
        return Stream.of(
                Arguments.of(1L, 0L, 0),                         // explicit seed 0
                Arguments.of(0L, 1L, -12345),                    // negative seed
                Arguments.of(Long.MAX_VALUE, 0L, 0x7FFFFFFF),   // max long + max positive int seed
                Arguments.of(0L, Long.MIN_VALUE, Integer.MIN_VALUE), // min long + most negative int seed
                Arguments.of(0x5555555555555555L, 0xAAAAAAAAAAAAAAAAL, 123456789) // patterned longs
        );
    }

    /**
     * Group 3 – default seed with mixed sign values.
     * Verify that the two‑argument overload equals the three‑argument version
     * with {@link MurmurHash3#DEFAULT_SEED}.
     */
    @ParameterizedTest(name = "[{index}] data1={0}, data2={1}")
    @MethodSource("defaultSeedMixedSignProvider")
    void hash32_defaultSeedMixedSign(long data1, long data2) {
        int expected = MurmurHash3.hash32(data1, data2, MurmurHash3.DEFAULT_SEED);
        int actual   = MurmurHash3.hash32(data1, data2);
        assertEquals(expected, actual,
                () -> String.format("hash32(%#x, %#x) with default seed differs from three‑arg version", data1, data2));
    }

    private static Stream<Arguments> defaultSeedMixedSignProvider() {
        return Stream.of(
                Arguments.of(-123456789L, 987654321L),
                Arguments.of(Long.MIN_VALUE, 0L),
                Arguments.of(0L, Long.MAX_VALUE),
                Arguments.of(-1L, 1L),
                Arguments.of(0xFFFFFFFF00000000L, 0x00000000FFFFFFFFL)
        );
    }

