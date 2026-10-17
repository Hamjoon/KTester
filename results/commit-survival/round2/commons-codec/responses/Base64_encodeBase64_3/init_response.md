```java
package org.apache.commons.codec.binary;

import org.junit.jupiter.api.*;

import java.nio.charset.StandardCharsets;
import java.util.Base64 as JdkBase64; // alias to avoid confusion with our class

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class Base64_encodeBase64_3_Test {

    /** Sample data used by several tests. */
    private static final byte[] SAMPLE_TEXT = "The quick brown fox jumps over the lazy dog".getBytes(StandardCharsets.UTF_8);

    /** Expected Base64 representation of {@link #SAMPLE_TEXT} using the JDK encoder (standard, no line‑breaks). */
    private static final byte[] EXPECTED_STANDARD = JdkBase64.getEncoder().encode(SAMPLE_TEXT);

    /** Expected Base64 URL‑safe representation of {@link #SAMPLE_TEXT} (no padding, no line‑breaks). */
    private static final byte[] EXPECTED_URLSAFE = JdkBase64.getUrlEncoder().withoutPadding().encode(SAMPLE_TEXT);

    @BeforeAll
    static void setupBeforeAll() {
        // No global resources needed for these static tests.
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
     * Verifies that encoding without chunking and without URL‑safe mode matches the JDK
     * standard Base64 output.
     */
    @Test
    void encodeBase64_standard_noChunk_noUrlSafe() {
        byte[] result = Base64.encodeBase64(SAMPLE_TEXT, false, false);
        assertArrayEquals(EXPECTED_STANDARD, result,
                "Standard Base64 encoding should match JDK encoder when chunking is disabled and URL‑safe is false");
    }

    /**
     * Verifies that encoding with URL‑safe mode produces the same output as the JDK
     * URL‑safe encoder (without padding).
     */
    @Test
    void encodeBase64_urlSafe_noChunk() {
        byte[] result = Base64.encodeBase64(SAMPLE_TEXT, false, true);
        assertArrayEquals(EXPECTED_URLSAFE, result,
                "URL‑safe Base64 encoding should match JDK URL‑safe encoder when chunking is disabled");
    }

    /**
     * When {@code isChunked} is {@code true} the implementation inserts CRLF
     * line separators every 76 characters (the default MIME line length). For a
     * short input the result should be identical to the non‑chunked version.
     */
    @Test
    void encodeBase64_chunked_smallInput() {
        byte[] result = Base64.encodeBase64(SAMPLE_TEXT, true, false);
        // Input length is far below the 76‑character line limit, so the output must be identical.
        assertArrayEquals(EXPECTED_STANDARD, result,
                "Chunked encoding of a short payload must not introduce line breaks");
    }

    /**
     * An empty input array should result in an empty output array, regardless of
     * the flags.
     */
    @Test
    void encodeBase64_emptyInput() {
        byte[] empty = new byte[0];
        assertArrayEquals(new byte[0], Base64.encodeBase64(empty, false, false));
        assertArrayEquals(new byte[0], Base64.encodeBase64(empty, true, true));
    }

    /**
     * Passing {@code null} should return {@code null} – this mirrors the behaviour
     * of the original Apache Commons Codec implementation.
     */
    @Test
    void encodeBase64_nullInput() {
        assertNull(Base64.encodeBase64(null, false, false));
        assertNull(Base64.encodeBase64(null, true, true));
    }
}
```