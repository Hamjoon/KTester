```java
package org.apache.commons.codec.binary;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class Base64_encodeBase64_4_Test {

    /** Sample data used by several tests */
    private static final byte[] SAMPLE = "Apache Commons Codec".getBytes();

    @BeforeAll
    static void setupBeforeAll() {
        // No global resources required for these tests
    }

    @BeforeEach
    void setupBeforeEach() {
        // Nothing to initialise before each test
    }

    @AfterEach
    void teardownAfterEach() {
        // Nothing to clean up after each test
    }

    @AfterAll
    static void teardownAfterAll() {
        // No global cleanup required
    }

    /**
     * The method should return the original array when the input is empty.
     * This also verifies that the reference is unchanged (not a copy).
     */
    @Test
    void encodeBase64_EmptyInput_ReturnsSameArray() {
        byte[] empty = new byte[0];
        byte[] result = Base64.encodeBase64(empty, false, false, Integer.MAX_VALUE);
        assertSame(empty, result, "Empty input must be returned unchanged");
    }

    /**
     * Verify normal (non‑chunked, non‑URL‑safe) encoding produces the same
     * bytes as the simple overload {@code Base64.encodeBase64(byte[])}.
     */
    @Test
    void encodeBase64_NonChunkedNonUrlSafe_MatchesSimpleOverload() {
        byte[] expected = Base64.encodeBase64(SAMPLE); // simple overload
        byte[] actual = Base64.encodeBase64(SAMPLE, false, false, Integer.MAX_VALUE);
        assertArrayEquals(expected, actual, "Encoding without chunking or URL‑safe must match simple overload");
    }

    /**
     * When URL‑safe is requested the result must be identical to the dedicated
     * {@code encodeBase64URLSafe} method.
     */
    @Test
    void encodeBase64_UrlSafe_MatchesUrlSafeOverload() {
        byte[] expected = Base64.encodeBase64URLSafe(SAMPLE);
        byte[] actual = Base64.encodeBase64(SAMPLE, false, true, Integer.MAX_VALUE);
        assertArrayEquals(expected, actual, "URL‑safe encoding must match encodeBase64URLSafe");
    }

    /**
     * Chunked encoding adds line separators only when the encoded output exceeds
     * the default line length (76). For a short payload the result must be identical
     * to the non‑chunked version.
     */
    @Test
    void encodeBase64_Chunked_ShortInput_NoSeparatorAdded() {
        byte[] expected = Base64.encodeBase64(SAMPLE); // non‑chunked
        byte[] actual = Base64.encodeBase64(SAMPLE, true, false, Integer.MAX_VALUE);
        assertArrayEquals(expected, actual, "Chunked encoding of short data must not insert line separators");
    }

    /**
     * Verify that the method respects {@code maxResultSize}. The encoded length is
     * obtained from a fresh {@code Base64} instance; the test forces an
     * {@code IllegalArgumentException} when the limit is smaller than that length.
     */
    @Test
    void encodeBase64_MaxResultSize_TooSmall_ThrowsException() {
        // Build a relatively large input so that the encoded length is definitely > 10
        byte[] large = new byte[256];
        for (int i = 0; i < large.length; i++) {
            large[i] = (byte) i;
        }

        // Compute the length that the encoder would produce
        Base64 encoder = new Base64(); // default configuration
        long encodedLen = encoder.getEncodedLength(large);

        // Use a maxResultSize that is smaller than the required length
        long tooSmall = encodedLen - 1;

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> Base64.encodeBase64(large, false, false, (int) tooSmall),
                "Expected IllegalArgumentException when maxResultSize is too small"
        );
        assertTrue(ex.getMessage().contains("Input array too big"),
                "Exception message should indicate the input is too big");
    }
}
```