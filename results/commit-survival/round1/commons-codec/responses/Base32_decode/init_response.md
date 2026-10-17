```java
package org.apache.commons.codec.binary;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class Base32_decode_Test {

    /** RFC‑4648 test vectors – plain text and its Base32 representation. */
    // @formatter:off
    private static final String[][] BASE32_TEST_CASES = {
        { "",                 "" },
        { "f",                "MY======" },
        { "fo",               "MZXQ====" },
        { "foo",              "MZXW6===" },
        { "foob",             "MZXW6YQ=" },
        { "fooba",            "MZXW6YTB" },
        { "foobar",           "MZXW6YTBOI======" }
    };
    // @formatter:on

    /** Cases that contain impossible trailing characters – they must trigger an exception. */
    // @formatter:off
    private static final String[] IMPOSSIBLE_CASES = {
        "MC======",
        "MZXE====",
        "MZXWB===",
        "MZXW6YB=",
        "MZXW6YTBOC======",
        "AB======"
    };
    // @formatter:on

    /** Chunked (line‑separated) versions of the impossible cases – used with a line length > 0. */
    // @formatter:off
    private static final String[] IMPOSSIBLE_CASES_CHUNKED = {
        "M2======\r\n",
        "MZX0====\r\n",
        "MZXW0===\r\n",
        "MZXW6Y2=\r\n",
        "MZXW6YTBO2======\r\n"
    };
    // @formatter:on

    @BeforeAll
    static void setupBeforeAll() {
        // No global resources needed for these tests.
    }

    @BeforeEach
    void setupBeforeEach() {
        // Nothing to initialise per‑test at the moment.
    }

    @AfterEach
    void teardownAfterEach() {
        // No per‑test cleanup required.
    }

    @AfterAll
    static void teardownAfterAll() {
        // No global teardown required.
    }

    /** Verify that the standard RFC‑4648 vectors decode to the original bytes. */
    @Test
    void testStandardDecoding() {
        final Base32 codec = new Base32(); // default (no line length, no separator)
        for (String[] pair : BASE32_TEST_CASES) {
            final String plain = pair[0];
            final String encoded = pair[1];
            byte[] expected = plain.getBytes(StandardCharsets.UTF_8);
            byte[] actual = codec.decode(encoded.getBytes(StandardCharsets.US_ASCII));
            assertArrayEquals(expected, actual,
                    () -> "Failed to decode Base32 string: \"" + encoded + "\"");
        }
    }

    /** Decoding with a line length and line separator should ignore the separator characters. */
    @Test
    void testChunkedDecoding() {
        // line length 10, CRLF separator – matches the strings in IMPOSSIBLE_CASES_CHUNKED
        final Base32 codec = new Base32(10, "\r\n".getBytes(StandardCharsets.US_ASCII));
        for (String chunked : IMPOSSIBLE_CASES_CHUNKED) {
            // The chunked strings are *invalid* – they should raise an exception.
            assertThrows(IllegalArgumentException.class,
                    () -> codec.decode(chunked.getBytes(StandardCharsets.US_ASCII)),
                    () -> "Expected exception for chunked invalid input: \"" + chunked + "\"");
        }
    }

    /** Ensure that inputs with impossible trailing characters raise an exception. */
    @Test
    void testImpossibleTrailingCharacters() {
        final Base32 codec = new Base32();
        for (String bad : IMPOSSIBLE_CASES) {
            assertThrows(IllegalArgumentException.class,
                    () -> codec.decode(bad.getBytes(StandardCharsets.US_ASCII)),
                    () -> "Expected exception for impossible case: \"" + bad + "\"");
        }
    }

    /** Verify that the decoder correctly handles the various modulus values (2,4,5,6,7). */
    @Test
    void testModulusEdgeCases() {
        final Base32 codec = new Base32();

        // Modulus 2 – 2 encoded chars -> 1 decoded byte (e.g. "MY")
        assertArrayEquals(
                new byte[]{'f'},
                codec.decode("MY".getBytes(StandardCharsets.US_ASCII)),
                "Modulus 2 (2 chars) failed");

        // Modulus 4 – 4 encoded chars -> 2 decoded bytes (e.g. "MZXQ")
        assertArrayEquals(
                new byte[]{'f', 'o'},
                codec.decode("MZXQ".getBytes(StandardCharsets.US_ASCII)),
                "Modulus 4 (4 chars) failed");

        // Modulus 5 – 5 encoded chars -> 3 decoded bytes (e.g. "MZXW6")
        assertArrayEquals(
                new byte[]{'f', 'o', 'o'},
                codec.decode("MZXW6".getBytes(StandardCharsets.US_ASCII)),
                "Modulus 5 (5 chars) failed");

        // Modulus 6 – 6 encoded chars -> 3 decoded bytes (e.g. "MZXW6Y")
        assertArrayEquals(
                new byte[]{'f', 'o', 'o', 'b'},
                codec.decode("MZXW6Y".getBytes(StandardCharsets.US_ASCII)),
                "Modulus 6 (6 chars) failed");

        // Modulus 7 – 7 encoded chars -> 4 decoded bytes (e.g. "MZXW6YT")
        assertArrayEquals(
                new byte[]{'f', 'o', 'o', 'b', 'a'},
                codec.decode("MZXW6YT".getBytes(StandardCharsets.US_ASCII)),
                "Modulus 7 (7 chars) failed");
    }

    /** Verify that the decoder works with the Base32‑hex variant. */
    @Test
    void testHexVariantDecoding() {
        // Base32‑hex uses a different alphabet; the string "C" corresponds to byte 0.
        final Base32 codec = new Base32(true);
        // "C" is the first character of the hex alphabet, representing the 5‑bit value 0.
        // With padding we need 8 chars to get a full byte; "C=======" yields a single zero byte.
        byte[] decoded = codec.decode("C=======".getBytes(StandardCharsets.US_ASCII));
        assertArrayEquals(new byte[]{0}, decoded, "Hex variant decoding failed");
    }

    /** Ensure that decoding an empty input returns an empty byte array and does not set EOF prematurely. */
    @Test
    void testEmptyInput() {
        final Base32 codec = new Base32();
        byte[] decoded = codec.decode(new byte[0]);
        assertNotNull(decoded, "Decoded result should not be null");
        assertEquals(0, decoded.length, "Decoded length for empty input should be zero");
    }
}
```