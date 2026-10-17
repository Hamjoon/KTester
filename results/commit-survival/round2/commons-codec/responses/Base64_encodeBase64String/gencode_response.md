```java
package org.apache.commons.codec.binary;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link Base64#encodeBase64String(byte[])}.
 *
 * <p>The tests cover the scenarios described in the provided JSON test matrix:
 * <ul>
 *   <li>Null and empty inputs</li>
 *   <li>Various lengths that trigger different padding rules</li>
 *   <li>Typical ASCII text</li>
 *   <li>Large generated payloads</li>
 *   <li>Alphabet validation via a regular expression</li>
 * </ul>
 *
 * <p>No mocking is required because {@code encodeBase64String} is a pure static method.</p>
 */
@ExtendWith(MockitoExtension.class)
class Base64_encodeBase64String_Test {

    @BeforeAll
    static void setupBeforeAll() {
        // No global resources required for these static method tests.
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

    /* --------------------------------------------------------------------- *
     *  Simple explicit tests (null / empty)                                 *
     * --------------------------------------------------------------------- */

    @Test
    void encodeBase64String_nullInput_returnsNull() {
        // According to the JSON spec the method returns null (encodeBase64 returns null).
        assertNull(Base64.encodeBase64String(null),
                "When the input byte array is null the method should return null");
    }

    @Test
    void encodeBase64String_emptyArray_returnsEmptyString() {
        byte[] empty = new byte[0];
        String result = Base64.encodeBase64String(empty);
        assertNotNull(result, "Result should never be null for a non‑null input");
        assertEquals("", result, "Encoding an empty array must yield an empty string");
    }

    /* --------------------------------------------------------------------- *
     *  Parameterized tests for deterministic byte‑array fixtures            *
     * --------------------------------------------------------------------- */

    private static byte[] toByteArray(int[] values) {
        byte[] bytes = new byte[values.length];
        for (int i = 0; i < values.length; i++) {
            bytes[i] = (byte) values[i];
        }
        return bytes;
    }

    private static Stream<Arguments> deterministicCases() {
        return Stream.of(
                // Single‑byte input (padding "==")
                Arguments.of(toByteArray(new int[]{0}), "AA=="),
                // Two‑byte input (padding "=")
                Arguments.of(toByteArray(new int[]{0, 1}), "AAE="),
                // Three‑byte input (no padding)
                Arguments.of(toByteArray(new int[]{0, 1, 2}), "AAEC"),
                // Typical ASCII string "Hello"
                Arguments.of("Hello".getBytes(StandardCharsets.UTF_8), "SGVsbG8="),
                // Large (20‑byte) input spanning several quanta
                Arguments.of(toByteArray(new int[]{
                        0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
                        10, 11, 12, 13, 14, 15, 16, 17, 18, 19}), "AAECAwQFBgcICQoLDA0ODw=="),
                // Non‑ASCII signed bytes (255,128,127) -> [-1, -128, 127]
                Arguments.of(toByteArray(new int[]{255, 128, 127}), "/4B/"),
                // All‑zero 30‑byte array (10 full quanta, no padding)
                Arguments.of(toByteArray(new int[]{
                        // 30 zeros
                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0
                }), "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")
        );
    }

    @ParameterizedTest(name = "[{index}] deterministic case")
    @MethodSource("deterministicCases")
    void encodeBase64String_deterministicCases(byte[] input, String expected) {
        String actual = Base64.encodeBase64String(input);
        assertEquals(expected, actual);
    }

    /* --------------------------------------------------------------------- *
     *  Very large generated payload (10 000 bytes)                          *
     * --------------------------------------------------------------------- */

    @Test
    void encodeBase64String_veryLargePatternInput_correctLengthAndSuffix() {
        // Build a 10 000‑byte array with the repeating pattern 0x01,0x02,0x03
        final int length = 10_000;
        byte[] data = new byte[length];
        for (int i = 0; i < length; i++) {
            data[i] = (byte) ((i % 3) + 1); // 1,2,3,1,2,3,...
        }

        String encoded = Base64.encodeBase64String(data);

        // Expected length = ceil(10000 / 3) * 4 = 13336
        int expectedLength = ((length + 2) / 3) * 4;
        assertEquals(expectedLength, encoded.length(),
                "Encoded string length must match Base64 size formula");

        // The leftover byte after 3333 complete triples is a single 0x01,
        // which should be encoded as "AQ==".
        assertTrue(encoded.endsWith("AQ=="),
                "The final four characters must be the encoding of the remaining byte 0x01");
    }

    /* --------------------------------------------------------------------- *
     *  Full byte range (0‑255)                                               *
     * --------------------------------------------------------------------- */

    @Test
    void encodeBase64String_fullByteRange_correctLengthPrefixAndSuffix() {
        byte[] allBytes = new byte[256];
        for (int i = 0; i < 256; i++) {
            allBytes[i] = (byte) i;
        }

        String encoded = Base64.encodeBase64String(allBytes);

        // 256 bytes → ceil(256/3)=86 quanta → 86*4 = 344 characters, with 2 padding chars.
        assertEquals(344, encoded.length(), "Encoded length for 256 bytes should be 344");

        // Verify first three bytes (0,1,2) encode to "AAEC"
        assertTrue(encoded.startsWith("AAEC"), "Prefix should match encoding of 0,1,2");

        // Verify last three bytes (253,254,255) encode to "//8="
        assertTrue(encoded.endsWith("//8="), "Suffix should match encoding of 253,254,255");
    }

    /* --------------------------------------------------------------------- *
     *  Alphabet validation (random 57‑byte payload)                         *
     * --------------------------------------------------------------------- */

    @Test
    void encodeBase64String_randomData_matchesBase64Alphabet() {
        byte[] random = new byte[57];
        new Random(0x5A5A5A5AL).nextBytes(random); // deterministic seed for reproducibility

        String encoded = Base64.encodeBase64String(random);

        // Base64 alphabet plus optional padding at the end (0‑2 '=')
        assertTrue(encoded.matches("^[A-Za-z0-9+/]*={0,2}$"),
                "Encoded string must consist only of Base64 alphabet characters and at most two padding '='");
    }
}
```