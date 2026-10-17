```java
package org.apache.commons.codec.binary;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link Base32#getLineSeparator()}.
 *
 * <p>
 * The tests cover:
 * <ul>
 *   <li>Default construction (no line separator).</li>
 *   <li>Builder construction without explicitly setting a line separator.</li>
 *   <li>Custom line separators supplied via constructors.</li>
 *   <li>Custom line separators supplied via the {@code Builder}.</li>
 *   <li>Reference‑semantic behaviour – the returned array is the internal mutable field.</li>
 *   <li>Defensive copying of the array passed to the constructor.</li>
 *   <li>Empty line separator array.</li>
 * </ul>
 * </p>
 */
@ExtendWith(MockitoExtension.class)
class Base32_getLineSeparator_Test {

    /** Instance used by tests that need a default‑constructed {@link Base32}. */
    private Base32 defaultBase32;

    @BeforeAll
    static void setupBeforeAll() {
        // No global resources required.
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialise a Base32 with the no‑arg constructor for convenience.
        defaultBase32 = new Base32();
    }

    @AfterEach
    void teardownAfterEach() {
        // Nothing to clean up for this class.
        defaultBase32 = null;
    }

    @AfterAll
    static void teardownAfterAll() {
        // No global teardown needed.
    }

    /* --------------------------------------------------------------
     *  Default construction & builder without line separator
     * -------------------------------------------------------------- */

    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("defaultConstructionProvider")
    void testDefaultConstruction(String description, Base32 codec) {
        // The codec should have no line separator (null).
        assertNull(codec.getLineSeparator(),
                "Expected null lineSeparator for: " + description);
    }

    static Stream<Arguments> defaultConstructionProvider() {
        return Stream.of(
                Arguments.of("Default constructor", new Base32()),
                Arguments.of("Builder without explicit line separator",
                        Base32.builder().build())
        );
    }

    /* --------------------------------------------------------------
     *  Custom line separator via constructor
     * -------------------------------------------------------------- */

    @ParameterizedTest(name = "{index}: {1}")
    @MethodSource("constructorSeparatorProvider")
    void testCustomLineSeparatorViaConstructor(String description,
                                                byte[] separator,
                                                Base32 codec,
                                                byte[] expected) {
        assertArrayEquals(expected, codec.getLineSeparator(),
                "Line separator mismatch for: " + description);
    }

    static Stream<Arguments> constructorSeparatorProvider() {
        return Stream.of(
                Arguments.of("CRLF separator",
                        new byte[]{'\r', '\n'},
                        new Base32(0, new byte[]{'\r', '\n'}),
                        new byte[]{13, 10}),
                Arguments.of("Three spaces",
                        new byte[]{' ', ' ', ' '},
                        new Base32(0, new byte[]{' ', ' ', ' '}),
                        new byte[]{32, 32, 32})
        );
    }

    /* --------------------------------------------------------------
     *  Custom line separator via Builder
     * -------------------------------------------------------------- */

    @ParameterizedTest(name = "{index}: {1}")
    @MethodSource("builderSeparatorProvider")
    void testCustomLineSeparatorViaBuilder(String description,
                                           byte[] separator,
                                           Base32 codec,
                                           byte[] expected) {
        assertArrayEquals(expected, codec.getLineSeparator(),
                "Line separator mismatch for: " + description);
    }

    static Stream<Arguments> builderSeparatorProvider() {
        return Stream.of(
                Arguments.of("Single LF",
                        new byte[]{'\n'},
                        Base32.builder().withLineSeparator(new byte[]{'\n'}).build(),
                        new byte[]{10}),
                Arguments.of("Two pipe characters",
                        new byte[]{'|', '|'},
                        Base32.builder().withLineSeparator(new byte[]{'|', '|'}).build(),
                        new byte[]{124, 124})
        );
    }

    /* --------------------------------------------------------------
     *  Reference‑semantic behaviour – returned array is mutable
     * -------------------------------------------------------------- */

    @Test
    void testReferenceSemantics_ModifyReturnedArrayAffectsInternalState() {
        byte[] original = new byte[]{'*', '*'}; // ASCII 42
        Base32 codec = new Base32(0, original);

        // Retrieve the internal array and modify it.
        byte[] retrieved = codec.getLineSeparator();
        retrieved[0] = 42; // change first element to '*'

        // A second call must reflect the mutation.
        assertArrayEquals(new byte[]{42, 42}, codec.getLineSeparator(),
                "Modification of the returned array should be reflected in subsequent calls");
    }

    @Test
    void testReferenceSemantics_ModifySingleByteSeparator() {
        byte[] original = new byte[]{'!'}; // ASCII 33
        Base32 codec = new Base32(0, original);

        byte[] retrieved = codec.getLineSeparator();
        retrieved[0] = 63; // '?' (ASCII 63)

        assertArrayEquals(new byte[]{63}, codec.getLineSeparator(),
                "Single‑byte separator should reflect modification");
    }

    /* --------------------------------------------------------------
     *  Defensive copy of constructor argument
     * -------------------------------------------------------------- */

    @Test
    void testConstructorDefensiveCopy_OriginalArrayModificationDoesNotAffectCodec() {
        byte[] separator = new byte[]{'A', 'B'};
        Base32 codec = new Base32(0, separator);

        // Mutate the original array after construction.
        separator[0] = 'Z';

        // The codec's internal copy must remain unchanged.
        byte[] internal = codec.getLineSeparator();
        assertEquals('A', internal[0],
                "Internal lineSeparator must not reflect modifications to the original array");
        assertEquals('B', internal[1],
                "Internal lineSeparator must retain the original second byte");
    }

    /* --------------------------------------------------------------
     *  Empty line separator array
     * -------------------------------------------------------------- */

    @Test
    void testEmptyLineSeparatorArray() {
        Base32 codec = new Base32(0, new byte[0]);
        assertNotNull(codec.getLineSeparator(),
                "Empty separator should not be null");
        assertEquals(0, codec.getLineSeparator().length,
                "Expected an empty array for line separator");
    }
}
```