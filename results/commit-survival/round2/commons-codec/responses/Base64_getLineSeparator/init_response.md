```java
package org.apache.commons.codec.binary;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class Base64_getLineSeparator_Test {

    /** Instances created for the different constructor scenarios. */
    private Base64 defaultBase64;                 // Base64()
    private Base64 chunkedBase64;                 // new Base64(76)  – uses default CRLF separator
    private Base64 customSeparatorBase64;         // new Base64(10, new byte[]{'\n'})
    private Base64 zeroLengthCustomSepBase64;     // new Base64(0, new byte[]{'\r', '\n'})

    @BeforeAll
    static void setupBeforeAll() {
        // No global resources required for these tests.
    }

    @BeforeEach
    void setupBeforeEach() {
        // Default constructor – line length = 0 → no line separator.
        defaultBase64 = new Base64();

        // Positive line length → default CRLF separator is used.
        chunkedBase64 = new Base64(76);

        // Positive line length with an explicit custom separator.
        customSeparatorBase64 = new Base64(10, new byte[]{'\n'});

        // Zero line length with a custom separator – separator must be ignored.
        zeroLengthCustomSepBase64 = new Base64(0, new byte[]{'\r', '\n'});
    }

    @AfterEach
    void teardownAfterEach() {
        // Help GC and make sure each test starts with a clean slate.
        defaultBase64 = null;
        chunkedBase64 = null;
        customSeparatorBase64 = null;
        zeroLengthCustomSepBase64 = null;
    }

    @AfterAll
    static void teardownAfterAll() {
        // No global cleanup required.
    }

    @Test
    void defaultConstructorShouldReturnNullLineSeparator() {
        assertNull(defaultBase64.getLineSeparator(),
                "Base64() should have a null line separator because the default line length is 0");
    }

    @Test
    void positiveLineLengthUsesDefaultCRLFSeparator() {
        byte[] expected = "\r\n".getBytes(StandardCharsets.UTF_8);
        byte[] actual = chunkedBase64.getLineSeparator();

        assertNotNull(actual, "Line separator must not be null for a positive line length");
        assertArrayEquals(expected, actual,
                "Default line separator should be CRLF (\"\\r\\n\") when a positive line length is supplied");
    }

    @Test
    void customLineSeparatorIsReturnedExactly() {
        byte[] expected = new byte[]{'\n'};
        byte[] actual = customSeparatorBase64.getLineSeparator();

        assertNotNull(actual, "Custom line separator should be stored when line length > 0");
        assertArrayEquals(expected, actual,
                "The custom line separator supplied to the constructor must be returned unchanged");
    }

    @Test
    void zeroLineLengthIgnoresProvidedSeparator() {
        assertNull(zeroLengthCustomSepBase64.getLineSeparator(),
                "When line length is zero, any supplied line separator must be ignored and the getter should return null");
    }

    @Test
    void getLineSeparatorReturnsReferenceNotCopy() {
        // Verify that the returned array is the same instance the Base64 object holds.
        // This is the current contract of Base64 (it returns the internal array directly).
        byte[] sep = customSeparatorBase64.getLineSeparator();
        assertSame(sep, customSeparatorBase64.getLineSeparator(),
                "Subsequent calls must return the same array instance");

        // Mutate the returned array and ensure the change is reflected in a later call.
        sep[0] = (byte) 'X';
        assertEquals('X', customSeparatorBase64.getLineSeparator()[0],
                "Modifying the returned array should affect the internal state because the method returns the original reference");
    }
}
```