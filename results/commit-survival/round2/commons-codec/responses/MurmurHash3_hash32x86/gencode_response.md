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

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link MurmurHash3#hash32x86(byte[])}.
 *
 * <p>
 * The tests verify that the convenience overload {@code hash32x86(byte[])} produces the same result
 * as the full‑parameter overload {@code hash32x86(byte[], int, int, int)} when the seed is the
 * default value {@code 0}. Additional edge‑case tests cover empty input, sub‑array handling,
 * null arguments, single‑byte arrays, larger deterministic inputs and the method’s
 * pure‑function nature.
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

    /* --------------------------------------------------------------------- */
    /*               Parameterised tests generated from JSON case data      */
    /* --------------------------------------------------------------------- */

    /**
     * Single‑byte arrays must give the same result as the explicit overload.
     */
    @ParameterizedTest(name = "[{index}] single byte = {0}")
    @MethodSource("singleByteProvider")
    void hash32x86_singleByteConsistency(byte singleByte) {
        byte[] data = new byte[] { singleByte };
        int expected = MurmurHash3.hash32x86(data, 0, 1, 0);
        int actual   = MurmurHash3.hash32x86(data);
        assertEquals(expected, actual,
                () -> String.format("Single‑byte array [%d] should match overload result", singleByte));
    }

    private static Stream<Arguments> singleByteProvider() {
        return Stream.of(
                Arguments.of((byte) 0x00),
                Arguments.of((byte) 0xFF)   // -1 in signed Java byte
        );
    }

    /**
     * Typical multi‑byte inputs must be deterministic and equal to the explicit overload.
     */
    @ParameterizedTest(name = "[{index}] length={1}")
    @MethodSource("multiByteProvider")
    void hash32x86_multipleBytesDeterminism(byte[] data, int length) {
        int expected = MurmurHash3.hash32x86(data, 0, length, 0);
        int actual   = MurmurHash3.hash32x86(data);
        assertEquals(expected, actual,
                () -> String.format("Array of length %d should produce deterministic hash", length));
    }

    private static Stream<Arguments> multiByteProvider() {
        return Stream.of(
                Arguments.of(new byte[]{1,2,3,4,5,6,7,8}, 8),
                Arguments.of(new byte[]{10,20,30,40,50,60,70,80,90,100}, 10)
        );
    }

    /**
     * A 256‑byte array containing every possible byte value (0‑255) must be processed without error
     * and produce the same result as the explicit overload.
     */
    @Test
    void hash32x86_allByteValues() {
        byte[] data = new byte[256];
        for (int i = 0; i < 256; i++) {
            data[i] = (byte) i; // signed conversion is automatic
        }
        int expected = MurmurHash3.hash32x86(data, 0, data.length, 0);
        int actual   = MurmurHash3.hash32x86(data);
        assertEquals(expected, actual,
                "Hash of an array containing every possible byte value must match overload result");
    }

    /**
     * Calling the method twice with the same input must yield identical results,
     * proving that the function is pure and deterministic.
     */
    @Test
    void hash32x86_deterministicAcrossCalls() {
        byte[] data = new byte[]{42, 17, 93, 8, (byte) 0xFF, 0, 123};
        int firstCallResult  = MurmurHash3.hash32x86(data);
        int secondCallResult = MurmurHash3.hash32x86(data);
        assertEquals(firstCallResult, secondCallResult,
                "Two successive calls with identical input must return the same hash");
    }
}
```