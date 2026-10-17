```java
package org.apache.commons.codec.binary;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.charset.StandardCharsets;
import java.util.Base64 as JdkBase64; // only for expected values, not required but illustrative

import static org.junit.jupiter.api.Assertions.*;

/**
 * Basic unit tests for {@link Base64#encodeBase64(byte[])}.
 *
 * <p>
 * The tests cover the most common scenarios:
 * <ul>
 *   <li>Encoding a regular non‑empty byte array.</li>
 *   <li>Encoding an empty byte array.</li>
 *   <li>Encoding {@code null} (should return {@code null}).</li>
 * </ul>
 * No external resources or complex dependencies are required for these
 * cases, therefore the {@code @BeforeAll}/{@code @BeforeEach}/{@code @AfterEach}/{@code @AfterAll}
 * methods are kept empty but are present for future extensions.
 * </p>
 */
@ExtendWith(MockitoExtension.class)
class Base64_encodeBase64_Test {

    @BeforeAll
    static void setupBeforeAll() {
        // No global resources needed for the current tests.
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
     * Verify that a known input is encoded to the expected Base64 representation.
     */
    @Test
    void testEncodeBase64_KnownString() {
        // Given
        final byte[] input = "Hello".getBytes(StandardCharsets.UTF_8);
        final byte[] expected = "SGVsbG8=".getBytes(StandardCharsets.UTF_8);

        // When
        final byte[] actual = Base64.encodeBase64(input);

        // Then
        assertArrayEquals(expected, actual, "Base64 encoding of \"Hello\" should match RFC 4648");
    }

    /**
     * Encoding an empty byte array should return an empty array (not {@code null}).
     */
    @Test
    void testEncodeBase64_EmptyArray() {
        // Given
        final byte[] input = new byte[0];
        final byte[] expected = new byte[0];

        // When
        final byte[] actual = Base64.encodeBase64(input);

        // Then
        assertNotNull(actual, "Result must not be null for empty input");
        assertArrayEquals(expected, actual, "Encoding an empty array should yield an empty array");
    }

    /**
     * Encoding {@code null} must return {@code null} according to the contract of the method.
     */
    @Test
    void testEncodeBase64_NullInput() {
        // When
        final byte[] actual = Base64.encodeBase64(null);

        // Then
        assertNull(actual, "Encoding null should return null");
    }

    /**
     * Verify that the method delegates correctly to the overloaded version with the
     * default {@code isChunked = false}. The result should be identical to the direct call.
     */
    @Test
    void testEncodeBase64_DelegatesToOverload() {
        // Given
        final byte[] input = "Apache Commons Codec".getBytes(StandardCharsets.UTF_8);
        final byte[] expected = Base64.encodeBase64(input, false);

        // When
        final byte[] actual = Base64.encodeBase64(input);

        // Then
        assertArrayEquals(expected, actual,
                "encodeBase64(byte[]) must behave like encodeBase64(byte[], false)");
    }
}
```