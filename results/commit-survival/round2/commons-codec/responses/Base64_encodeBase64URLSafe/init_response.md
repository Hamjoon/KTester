```java
package org.apache.commons.codec.binary;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.Base64;

/**
 * Unit tests for {@link Base64#encodeBase64URLSafe(byte[])}.
 *
 * <p>The tests verify that:
 * <ul>
 *   <li>Null input returns {@code null}.</li>
 *   <li>Empty input returns an empty array.</li>
 *   <li>The method produces the same output as {@link java.util.Base64#getUrlEncoder()}
 *       without padding.</li>
 *   <li>The URL‑safe output differs from the standard Base64 output (i.e. '+' → '-',
 *       '/' → '_' and padding is omitted).</li>
 * </ul>
 * </p>
 */
@ExtendWith(MockitoExtension.class)
class Base64_encodeBase64URLSafe_Test {

    private static final Random RANDOM = new Random();

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
    @DisplayName("encodeBase64URLSafe returns null when input is null")
    void testEncodeBase64URLSafe_NullInput() {
        assertNull(Base64.encodeBase64URLSafe(null), "Expected null output for null input");
    }

    @Test
    @DisplayName("encodeBase64URLSafe returns empty array when input is empty")
    void testEncodeBase64URLSafe_EmptyInput() {
        byte[] empty = new byte[0];
        assertArrayEquals(empty, Base64.encodeBase64URLSafe(empty), "Empty input should yield empty output");
    }

    @Test
    @DisplayName("encodeBase64URLSafe matches java.util.Base64 URL encoder for a simple string")
    void testEncodeBase64URLSafe_SimpleString() {
        byte[] data = "foobar".getBytes(StandardCharsets.UTF_8);
        byte[] expected = Base64.getUrlEncoder().withoutPadding().encode(data);
        byte[] actual = Base64.encodeBase64URLSafe(data);
        assertArrayEquals(expected, actual, "URL‑safe encoding of 'foobar' does not match expected value");
    }

    @Test
    @DisplayName("encodeBase64URLSafe matches java.util.Base64 URL encoder for random data")
    void testEncodeBase64URLSafe_RandomData() {
        // Generate 100 random bytes
        byte[] data = new byte[100];
        RANDOM.nextBytes(data);

        byte[] expected = Base64.getUrlEncoder().withoutPadding().encode(data);
        byte[] actual = Base64.encodeBase64URLSafe(data);
        assertArrayEquals(expected, actual, "URL‑safe encoding of random data does not match expected value");
    }

    @Test
    @DisplayName("URL‑safe output differs from standard Base64 output")
    void testEncodeBase64URLSafe_DiffersFromStandard() {
        byte[] data = "Apache Commons Codec".getBytes(StandardCharsets.UTF_8);

        byte[] standard = Base64.encodeBase64(data);               // standard (may contain '+' '/' and padding)
        byte[] urlSafe = Base64.encodeBase64URLSafe(data);          // URL‑safe (no padding, '-' '_' instead)

        // Verify that the two results are not equal (they should differ in at least one byte)
        assertFalse(java.util.Arrays.equals(standard, urlSafe),
                "URL‑safe output should differ from standard Base64 output");

        // Additionally, ensure that the URL‑safe output contains only URL‑safe characters
        String urlSafeStr = new String(urlSafe, StandardCharsets.US_ASCII);
        assertTrue(urlSafeStr.matches("^[A-Za-z0-9\\-\\_]*$"),
                "URL‑safe output contains illegal characters");
        // Ensure padding character '=' is not present
        assertFalse(urlSafeStr.contains("="), "URL‑safe output must not contain padding '='");
    }
}
```