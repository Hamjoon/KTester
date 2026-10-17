```java
package org.apache.commons.codec.binary;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.lang.reflect.Field;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Additional unit tests for {@link Base32#isInAlphabet(byte)} that
 * exercise branch coverage, edge cases and exceptional situations.
 *
 * <p>The private {@code decodeTable} field is manipulated via reflection
 * to simulate various internal states required by the test data.</p>
 */
@ExtendWith(MockitoExtension.class)   // retained from the original template
class Base32_isInAlphabet_Test {

    /** Instance under test – created fresh for each test method. */
    private Base32 base32;

    @BeforeAll
    static void setupBeforeAll() {
        // No global resources needed for these tests.
    }

    @BeforeEach
    void setupBeforeEach() {
        // Use the default constructor (standard Base32 alphabet).
        base32 = new Base32();
    }

    @AfterEach
    void teardownAfterEach() {
        // Nothing to clean up – the Base32 instance will be garbage‑collected.
        base32 = null;
    }

    @AfterAll
    static void teardownAfterAll() {
        // No global teardown required.
    }

    /* --------------------------------------------------------------------- */
    /* Existing simple tests (kept unchanged)                               */
    /* --------------------------------------------------------------------- */

    /**
     * A byte that is part of the standard Base32 alphabet should return {@code true}.
     * The character {@code 'A'} (0x41) is the first symbol in the alphabet.
     */
    @Test
    void shouldReturnTrueForValidAlphabetByte() {
        byte valid = (byte) 'A'; // 0x41
        assertTrue(base32.isInAlphabet(valid),
                "'A' should be recognised as being in the Base32 alphabet");
    }

    /**
     * A byte that is not part of the Base32 alphabet should return {@code false}.
     * The character {@code '0'} (zero) is not a valid Base32 symbol.
     */
    @Test
    void shouldReturnFalseForInvalidAlphabetByte() {
        byte invalid = (byte) '0'; // 0x30
        assertFalse(base32.isInAlphabet(invalid),
                "'0' should NOT be recognised as being in the Base32 alphabet");
    }

    /**
     * Negative byte values are outside the valid range (the method checks {@code octet >= 0}).
     */
    @Test
    void shouldReturnFalseForNegativeByte() {
        byte negative = (byte) -1; // 0xFF
        assertFalse(base32.isInAlphabet(negative),
                "Negative byte values must be reported as not in the alphabet");
    }

    /**
     * A byte that is within the table bounds but maps to {@code -1} in the decode table
     * (e.g., a whitespace character) must return {@code false}.
     */
    @Test
    void shouldReturnFalseForWhitespaceByte() {
        byte whitespace = (byte) ' '; // space character, 0x20
        assertFalse(base32.isInAlphabet(whitespace),
                "Whitespace characters must be reported as not in the alphabet");
    }

    /**
     * Verify that the method works correctly when the Base32 instance is configured
     * to use the "hex" variant. The hex alphabet contains different symbols, so a
     * character that is valid in the standard alphabet may be invalid in the hex
     * variant (and vice‑versa).
     */
    @Test
    void shouldRespectHexVariantAlphabet() {
        Base32 hexBase32 = new Base32(true); // useHex = true
        // In the hex variant, 'A' is still valid, but 'Z' is not.
        assertTrue(hexBase32.isInAlphabet((byte) 'A'));
        assertFalse(hexBase32.isInAlphabet((byte) 'Z'));
    }

    /* --------------------------------------------------------------------- */
    /* Helper to set the private decodeTable via reflection                 */
    /* --------------------------------------------------------------------- */

    /**
     * Sets the {@code decodeTable} field of the current {@code base32} instance.
     *
     * @param table the byte array to assign; may be {@code null}
     */
    private void setDecodeTable(byte[] table) {
        try {
            Field field = Base32.class.getDeclaredField("decodeTable");
            field.setAccessible(true);
            field.set(base32, table);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Failed to set decodeTable via reflection", e);
        }
    }

    /* --------------------------------------------------------------------- */
    /* Branch‑coverage parameterized tests                                   */
    /* --------------------------------------------------------------------- */

    /**
     * Provides test data for the "isInAlphabet - branch coverage" group.
     *
     * Each {@link Arguments} contains:
     * <ul>
     *   <li>octet value (as {@code byte})</li>
     *   <li>decodeTable to install before the call</li>
     *   <li>expected result (Boolean) – {@code null} indicates an exception is expected</li>
     *   <li>description (String) – used only for readability</li>
     * </ul>
     */
    private static Stream<Arguments> branchCoverageProvider() {
        // A decode table of length 64 filled with 0 (valid) except where we need -1.
        byte[] tableAllValid = new byte[64];
        for (int i = 0; i < tableAllValid.length; i++) tableAllValid[i] = 0;

        byte[] tableWithMinusOneAt10 = tableAllValid.clone();
        tableWithMinusOneAt10[10] = -1;

        byte[] tableWithMinusOneAt63 = tableAllValid.clone();
        tableWithMinusOneAt63[63] = -1;

        return Stream.of(
                // octet = -1  -> negative, should be false irrespective of table
                arguments((byte) -1, tableAllValid, false,
                        "Octet is negative; first condition fails"),
                // octet = 70  -> >= length (64), should be false
                arguments((byte) 70, tableAllValid, false,
                        "Octet >= decodeTable.length (64)"),
                // octet = 10  -> within range but table[10] == -1 -> false
                arguments((byte) 10, tableWithMinusOneAt10, false,
                        "Octet in range but decodeTable[10] == -1"),
                // octet = 0   -> valid entry
                arguments((byte) 0, tableAllValid, true,
                        "Octet 0 with non‑‑1 entry"),
                // octet = 63  -> valid entry
                arguments((byte) 63, tableAllValid, true,
                        "Octet 63 with non‑‑1 entry"),
                // octet = 63  -> decodeTable[63] == -1 -> false
                arguments((byte) 63, tableWithMinusOneAt63, false,
                        "Octet 63 with -1 entry")
        );
    }

    @ParameterizedTest(name = "[{index}] {3}")
    @MethodSource("branchCoverageProvider")
    void testIsInAlphabetBranchCoverage(byte octet, byte[] decodeTable, Boolean expected, String description) {
        setDecodeTable(decodeTable);
        boolean result = base32.isInAlphabet(octet);
        assertEquals(expected, result, description);
    }

    /* --------------------------------------------------------------------- */
    /* Edge‑and‑additional‑cases parameterized tests                         */
    /* --------------------------------------------------------------------- */

    private static Stream<Arguments> edgeCasesProvider() {
        // Various custom tables for the edge cases
        byte[] emptyTable = new byte[0];
        byte[] oneElementMinusOne = new byte[]{-1};
        byte[] oneElementValid = new byte[]{42};
        byte[] length64 = new byte[64]; // all zeros (valid)
        byte[] length100 = new byte[100]; // all zeros (valid)
        byte[] length128MinusOneAt127 = new byte[128];
        for (int i = 0; i < 128; i++) length128MinusOneAt127[i] = 0;
        length128MinusOneAt127[127] = -1;
        byte[] length128ValidAt127 = new byte[128];
        for (int i = 0; i < 128; i++) length128ValidAt127[i] = 0;
        length128ValidAt127[127] = 1;

        return Stream.of(
                // octet = -128, any table (use default) -> false (negative)
                arguments((byte) -128, length64, false,
                        "Minimum byte value, negative check"),
                // octet = 0, empty table -> false (second condition fails)
                arguments((byte) 0, emptyTable, false,
                        "Empty decodeTable, octet 0"),
                // octet = 0, length 1 with -1 -> false (third condition fails)
                arguments((byte) 0, oneElementMinusOne, false,
                        "decodeTable length 1, entry -1"),
                // octet = 0, length 1 with valid value -> true
                arguments((byte) 0, oneElementValid, true,
                        "decodeTable length 1, entry valid"),
                // octet = 64, table length 64 -> false (octet == length)
                arguments((byte) 64, length64, false,
                        "Octet equals decodeTable.length (64)"),
                // octet = 100, table length 100 -> false (octet == length)
                arguments((byte) 100, length100, false,
                        "Octet equals decodeTable.length (100)"),
                // octet = 127, length 128, entry -1 -> false
                arguments((byte) 127, length128MinusOneAt127, false,
                        "Octet 127 with -1 entry in length 128 table"),
                // octet = 127, length 128, entry valid -> true
                arguments((byte) 127, length128ValidAt127, true,
                        "Octet 127 with valid entry in length 128 table"),
                // octet = 127, table length 100 (smaller) -> false (second condition fails)
                arguments((byte) 127, length100, false,
                        "Octet 127 with shorter table (length 100)")
        );
    }

    @ParameterizedTest(name = "[{index}] {3}")
    @MethodSource("edgeCasesProvider")
    void testIsInAlphabetEdgeCases(byte octet, byte[] decodeTable, Boolean expected, String description) {
        setDecodeTable(decodeTable);
        boolean result = base32.isInAlphabet(octet);
        assertEquals(expected, result, description);
    }

    /* --------------------------------------------------------------------- */
    /* Exception‑scenario tests                                              */
    /* --------------------------------------------------------------------- */

    private static Stream<Arguments> exceptionProvider() {
        return Stream.of(
                // octet = 0, decodeTable null -> NPE
                arguments((byte) 0, null, NullPointerException.class,
                        "decodeTable is null, octet 0"),
                // octet = 127, decodeTable null -> NPE
                arguments((byte) 127, null, NullPointerException.class,
                        "decodeTable is null, octet 127")
        );
    }

    @ParameterizedTest(name = "[{index}] {3}")
    @MethodSource("exceptionProvider")
    void testIsInAlphabetExceptions(byte octet, byte[] decodeTable, Class<? extends Throwable> expectedException, String description) {
        setDecodeTable(decodeTable);
        assertThrows(expectedException, () -> base32.isInAlphabet(octet), description);
    }
}
```