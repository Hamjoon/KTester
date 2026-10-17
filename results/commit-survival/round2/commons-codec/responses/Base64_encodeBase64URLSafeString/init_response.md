```java
package org.apache.commons.codec.binary;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link Base64#encodeBase64URLSafeString(byte[])}.
 *
 * The tests cover:
 * <ul>
 *   <li>Typical encoding of a non‑empty byte array.</li>
 *   <li>Encoding of an empty byte array (should return an empty {@link String}).</li>
 *   <li>Encoding of {@code null} (should return {@code null}).</li>
 *   <li>Verification that the method produces URL‑safe output (i.e. “+” and “/” are replaced with “‑” and “_” and padding “=” is omitted).</li>
 * </ul>
 */
@ExtendWith(MockitoExtension.class)
class Base64_encodeBase64URLSafeString_Test {

    /* --------------------------------------------------------------------- */
    /*  Lifecycle hooks – they are kept for completeness; no state is needed */
    /* --------------------------------------------------------------------- */
    @BeforeAll
    static void setupBeforeAll() {
        // No global resources required for static Base64 methods.
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
    /*  Test cases                                                          */
    /* --------------------------------------------------------------------- */

    /**
     * Verify that a known byte array is encoded to the expected URL‑safe Base64 string.
     * The expected value is taken from the upstream Commons‑Codec test data
     * (see {@code Base64Test} – urlSafe3 array).
     */
    @Test
    void encodeBase64URLSafeString_knownData() {
        // Input taken from the RFC‑4648 example: "foobar"
        final byte[] input = "foobar".getBytes(StringUtils.UTF_8);
        // Expected URL‑safe Base64 without padding: "Zm9vYmFy"
        // In URL‑safe mode the characters are the same because the string does not contain '+' or '/'.
        final String expected = "Zm9vYmFy";

        final String actual = Base64.encodeBase64URLSafeString(input);
        assertEquals(expected, actual, "URL‑safe Base64 encoding of \"foobar\" is incorrect");
    }

    /**
     * Verify that the method produces URL‑safe output when the encoded data would
     * normally contain '+' and '/' characters. The expected string is taken from the
     * original Commons‑Codec test data (urlSafe3[0]).
     */
    @Test
    void encodeBase64URLSafeString_urlSafeCharacters() {
        // This byte array corresponds to the UUID used in the original test suite.
        final byte[] uuidBytes = new byte[]{
                (byte) 0x92, (byte) 0xED, (byte) 0x8D, (byte) 0x03,
                (byte) 0x19, (byte) 0xE4, (byte) 0x49, (byte) 0x33,
                (byte) 0x99, (byte) 0x55, (byte) 0x86, (byte) 0xFB,
                (byte) 0x67, (byte) 0x40, (byte) 0x4D, (byte) 0x37
        };
        // Expected URL‑safe Base64 string (no padding, '+' → '-', '/' → '_')
        final String expected = "lO2NAxnkSTOZVg-2dATTcA";

        final String actual = Base64.encodeBase64URLSafeString(uuidBytes);
        assertEquals(expected, actual, "URL‑safe Base64 encoding with special characters is incorrect");
    }

    /**
     * An empty input should result in an empty string (not {@code null}).
     */
    @Test
    void encodeBase64URLSafeString_emptyInput() {
        final byte[] empty = new byte[0];
        final String result = Base64.encodeBase64URLSafeString(empty);
        assertNotNull(result, "Result must not be null for empty input");
        assertEquals("", result, "Result for empty input should be an empty string");
    }

    /**
     * A {@code null} input must be handled gracefully and return {@code null}.
     */
    @Test
    void encodeBase64URLSafeString_nullInput() {
        final String result = Base64.encodeBase64URLSafeString(null);
        assertNull(result, "Result must be null when the input byte array is null");
    }
}
```