```java
package org.apache.commons.codec.binary;

import org.apache.commons.codec.CodecPolicy;
import org.junit.jupiter.api.*;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class Base32_builder_Test {

    /** The builder used in the tests – created fresh for each test. */
    private Base32.Builder builder;

    @BeforeAll
    static void setupBeforeAll() {
        // No global resources required for these tests.
    }

    @BeforeEach
    void setupBeforeEach() {
        // Obtain a fresh builder before each test.
        builder = Base32.builder();
    }

    @AfterEach
    void teardownAfterEach() {
        // Nothing to clean up after each test.
    }

    @AfterAll
    static void teardownAfterAll() {
        // No global resources to release.
    }

    /**
     * Verify that the default builder creates a {@link Base32} instance with the
     * standard configuration (no line length, CRLF separator, '=' padding and
     * lenient decoding policy). The encoding of a known string is compared with
     * the RFC‑4648 reference output.
     */
    @Test
    void testBuilderCreatesDefaultBase32() {
        Base32 base32 = builder.get();               // default configuration
        assertNotNull(base32, "Builder should return a non‑null Base32 instance");

        // Encode the word "foo" – RFC‑4648 expects "MZXW6===" for the default Base32.
        byte[] encoded = base32.encode("foo".getBytes(StandardCharsets.UTF_8));
        String encodedStr = new String(encoded, StandardCharsets.US_ASCII);
        assertEquals("MZXW6===", encodedStr, "Default Base32 encoding does not match RFC‑4648");
    }

    /**
     * Verify that a custom configuration supplied via the builder is honoured.
     * The test sets a line length, a line separator, a custom padding character
     * and a strict decoding policy, then checks that the resulting encoded data
     * reflects those settings.
     */
    @Test
    void testBuilderCustomConfiguration() {
        // Custom configuration: line length 8, CRLF separator, '*' padding, strict policy.
        Base32 customBase32 = Base32.builder()
                .setLineLength(8)
                .setLineSeparator('\r', '\n')
                .setPadding((byte) '*')
                .setDecodingPolicy(CodecPolicy.STRICT)
                .get();

        assertNotNull(customBase32, "Custom builder should produce a Base32 instance");

        // Encode a single byte "f". With lineLength=8 the output should be exactly 8
        // encoded characters followed by the line separator. Padding must be '*'.
        byte[] encoded = customBase32.encode("f".getBytes(StandardCharsets.UTF_8));
        String encodedStr = new String(encoded, StandardCharsets.US_ASCII);

        // Expected 8 characters (including padding) + CRLF.
        assertTrue(encodedStr.endsWith("\r\n"),
                "Encoded output should end with the configured line separator");
        // Remove the line separator to examine the core Base32 payload.
        String payload = encodedStr.substring(0, encodedStr.length() - 2);
        assertEquals(8, payload.length(),
                "Payload length should match the configured line length");

        // The last six characters are padding; they must be '*'.
        assertTrue(payload.endsWith("******"),
                "Padding characters should be the custom '*' character");
    }

    /**
     * Ensure that each call to {@code Base32.builder()} returns a new independent
     * builder instance. Mutating one builder must not affect another.
     */
    @Test
    void testBuilderInstancesAreIndependent() {
        Base32.Builder first = Base32.builder().setLineLength(4);
        Base32.Builder second = Base32.builder(); // no configuration

        Base32 firstBase32 = first.get();
        Base32 secondBase32 = second.get();

        // The first instance should have a line length of 4 (i.e., a line separator will be inserted).
        // The second instance uses the default (no line length).
        byte[] firstEncoded = firstBase32.encode("f".getBytes(StandardCharsets.UTF_8));
        byte[] secondEncoded = secondBase32.encode("f".getBytes(StandardCharsets.UTF_8));

        String firstStr = new String(firstEncoded, StandardCharsets.US_ASCII);
        String secondStr = new String(secondEncoded, StandardCharsets.US_ASCII);

        // The first encoding must contain a line separator (CRLF) because of lineLength=4.
        assertTrue(firstStr.contains("\r\n"),
                "First builder should have inserted a line separator due to lineLength=4");
        // The second encoding must *not* contain a line separator.
        assertFalse(secondStr.contains("\r\n"),
                "Second builder should use the default configuration with no line separator");
    }

    /**
     * Verify that providing a custom encode table works without throwing an
     * exception and that the resulting Base32 instance can still encode data.
     * The exact output is not checked – only that encoding succeeds.
     */
    @Test
    void testBuilderWithCustomEncodeTable() {
        // A simple reversible custom table (shifted alphabet).
        byte[] customTable = new byte[32];
        for (int i = 0; i < 32; i++) {
            customTable[i] = (byte) ('A' + ((i + 1) % 32));
        }

        Base32 customBase32 = Base32.builder()
                .setEncodeTable(customTable)
                .get();

        assertNotNull(customBase32, "Builder with custom encode table should produce a Base32 instance");

        // Encode some data – we only assert that no exception is thrown and that a result is returned.
        byte[] data = "test".getBytes(StandardCharsets.UTF_8);
        byte[] encoded = customBase32.encode(data);
        assertNotNull(encoded, "Encoded result should not be null");
        assertTrue(encoded.length > 0, "Encoded result should contain data");
    }
}
```