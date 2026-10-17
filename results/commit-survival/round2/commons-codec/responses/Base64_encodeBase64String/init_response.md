```java
package org.apache.commons.codec.binary;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.nio.charset.StandardCharsets;

/**
 * Unit tests for {@link Base64#encodeBase64String(byte[])}.
 *
 * The tests cover typical usage scenarios, edge‑cases such as {@code null}
 * and empty inputs, and verification that the returned string matches the
 * expected Base64 representation.
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

    @Test
    void encodeBase64String_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Base64.encodeBase64String(null));
    }

    @Test
    void encodeBase64String_emptyArray_returnsEmptyString() {
        byte[] empty = new byte[0];
        String result = Base64.encodeBase64String(empty);
        assertNotNull(result, "Result should not be null");
        assertEquals("", result, "Encoding an empty array must yield an empty string");
    }

    @Test
    void encodeBase64String_simpleAsciiString_returnsExpectedBase64() {
        // "Hello" in UTF‑8 bytes
        byte[] data = "Hello".getBytes(StandardCharsets.UTF_8);
        String encoded = Base64.encodeBase64String(data);
        // Expected Base64 string for "Hello"
        assertEquals("SGVsbG8=", encoded);
    }

    @Test
    void encodeBase64String_binaryData_returnsCorrectBase64() {
        // Example binary data containing non‑printable bytes
        byte[] data = new byte[] { (byte) 0x00, (byte) 0xFF, (byte) 0x7A, (byte) 0x13 };
        String encoded = Base64.encodeBase64String(data);
        // Verify against the same encoding performed by the instance method for confidence
        byte[] expectedBytes = Base64.encodeBase64(data);
        String expected = StringUtils.newStringUsAscii(expectedBytes);
        assertEquals(expected, encoded);
    }
}
```