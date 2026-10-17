```java
package org.apache.commons.codec.binary;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link Base64#getLineSeparator()}.
 *
 * <p>The tests cover the following scenarios (derived from the provided JSON cases):
 * <ul>
 *   <li>Default constructor – no line separator (null).</li>
 *   <li>Chunked mode – default CRLF separator.</li>
 *   <li>Custom separator supplied via constructor.</li>
 *   <li>Multi‑byte custom separator.</li>
 *   <li>Empty separator (zero‑length array).</li>
 *   <li>Explicit {@code null} separator via the {@link Base64.Builder}.</li>
 *   <li>Behaviour of the returned array (reference vs. defensive copy).</li>
 * </ul>
 * </p>
 */
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

    /* --------------------------------------------------------------------- */
    /*  Existing tests (provided in the original framework)                */
    /* --------------------------------------------------------------------- */

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
        // This matches the current contract of Apache Commons Codec Base64.
        byte[] sep = customSeparatorBase64.getLineSeparator();
        assertSame(sep, customSeparatorBase64.getLineSeparator(),
                "Subsequent calls must return the same array instance");

        // Mutate the returned array and ensure the change is reflected in a later call.
        sep[0] = (byte) 'X';
        assertEquals('X', customSeparatorBase64.getLineSeparator()[0],
                "Modifying the returned array should affect the internal state because the method returns the original reference");
    }

    /* --------------------------------------------------------------------- */
    /*  Additional tests derived from the JSON test cases                    */
    /* --------------------------------------------------------------------- */

    /**
     * Verifies that a pipe character (ASCII 124) supplied as a custom line separator
     * is stored and returned unchanged.
     */
    @Test
    void customPipeSeparatorIsReturnedExactly() {
        byte[] pipe = new byte[]{'|'};               // ASCII 124
        Base64 pipeBase64 = new Base64(10, pipe);
        assertArrayEquals(pipe, pipeBase64.getLineSeparator(),
                "The pipe ('|') separator must be returned exactly as supplied");
    }

    /**
     * Verifies handling of a two‑character custom separator ("\n\t").
     */
    @Test
    void multiByteCustomSeparatorIsReturnedExactly() {
        byte[] nlTab = new byte[]{'\n', '\t'};       // ASCII 10, 9
        Base64 nlTabBase64 = new Base64(12, nlTab);
        assertArrayEquals(nlTab, nlTabBase64.getLineSeparator(),
                "The multi‑byte separator (newline + tab) must be returned unchanged");
    }

    /**
     * An empty byte array supplied as a line separator should be stored as an
     * empty array (not {@code null}). This mirrors the behaviour of the Builder
     * when {@code setLineSeparator(new byte[0])} is used.
     */
    @Test
    void emptyLineSeparatorIsStoredAsEmptyArray() {
        byte[] empty = new byte[0];
        Base64 emptySepBase64 = new Base64(10, empty);
        byte[] result = emptySepBase64.getLineSeparator();

        assertNotNull(result, "The line separator field must not be null when an empty array is supplied");
        assertEquals(0, result.length, "The stored line separator should be an empty array");
    }

    /**
     * The {@link Base64.Builder} allows an explicit {@code null} line separator.
     * In that case the resulting instance should behave like the default
     * configuration – i.e. {@code getLineSeparator()} returns {@code null}.
     */
    @Test
    void builderExplicitNullLineSeparatorResultsInNull() {
        Base64 nullSepBase64 = Base64.builder()
                .setLineLength(10)          // any positive length to force “chunked” mode
                .setLineSeparator((byte[]) null) // explicit null
                .get();

        assertNull(nullSepBase64.getLineSeparator(),
                "When the builder is given a null separator the getter must return null");
    }

    /**
     * Verifies that the method really returns the internal array reference.
     * This test complements {@link #getLineSeparatorReturnsReferenceNotCopy()}
     * and demonstrates the current contract (no defensive copy).
     */
    @Test
    void returnedArrayIsLiveReference() {
        byte[] separator = new byte[]{'|'};
        Base64 b64 = new Base64(8, separator);

        // Grab the reference and mutate it.
        byte[] returned = b64.getLineSeparator();
        returned[0] = (byte) '*';

        // Subsequent calls must see the mutated value.
        assertEquals('*', b64.getLineSeparator()[0],
                "The getter returns a live reference; changes to the returned array affect the internal state");
    }

    /* --------------------------------------------------------------------- */
    /*  Parameterised tests (optional – demonstrate usage)                  */
    /* --------------------------------------------------------------------- */

    /**
     * Parameterised test that iterates over a few custom separator scenarios.
     * The data provider supplies the separator to use and a textual description
     * for clearer failure messages.
     */
    @ParameterizedTest(name = "[{index}] Separator: {1}")
    @MethodSource("customSeparatorProvider")
    void parameterisedCustomSeparatorsAreReturnedAsSupplied(byte[] separator, String description) {
        Base64 b64 = new Base64(15, separator);
        assertArrayEquals(separator, b64.getLineSeparator(),
                () -> "Expected custom separator [" + description + "] to be returned unchanged");
    }

    /** Data provider for {@link #parameterisedCustomSeparatorsAreReturnedAsSupplied(byte[], String)}. */
    static Stream<org.junit.jupiter.params.provider.Arguments> customSeparatorProvider() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(new byte[]{'|'}, "124 (pipe)"),
                org.junit.jupiter.params.provider.Arguments.of(new byte[]{'\n', '\t'}, "10,9 (newline + tab)"),
                org.junit.jupiter.params.provider.Arguments.of(new byte[0], "empty")
        );
    }
}
```