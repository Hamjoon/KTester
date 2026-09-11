```java
package org.apache.commons.codec.language;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class Nysiis_nysiis_Test {
    private Nysiis nysiis;

    @BeforeAll
    static void setupBeforeAll() {
        // Any setup needed before all tests, if applicable.
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the Nysiis object before each test
        nysiis = new Nysiis(false); // Using non-strict mode for testing
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup after each test, if necessary
    }

    @AfterAll
    static void teardownAfterAll() {
        // Any cleanup needed after all tests, if applicable.
    }

    @Test
    void testNysiisWithNullInput() {
        // Test with null input; should return null.
        assertNull(nysiis.nysiis(null));
    }

    @Test
    void testNysiisWithEmptyString() {
        // Test with empty string input; should return empty string.
        assertEquals("", nysiis.nysiis(""));
    }

    @Test
    void testNysiisWithTranslationCases() {
        // Test with various input strings that require specific translations.
        assertEquals("MCC", nysiis.nysiis("MAC")); // Test for 'MAC' to 'MCC'
        assertEquals("NIGHT", nysiis.nysiis("KNIGHT")); // Test for 'KNIGHT' to 'NIGHT'
        assertEquals("CITE", nysiis.nysiis("KITE")); // Test for 'KITE' to 'CITE'
        assertEquals("FFILIP", nysiis.nysiis("PHILIP")); // Test for 'PHILIP' to 'FFILIP'
        assertEquals("SSCHOOL", nysiis.nysiis("SCHOOL")); // Test for 'SCHOOL' to 'SSCHOOL'
    }

    @Test
    void testNysiisWithNoTranslationCases() {
        // Test with input strings that should remain unchanged.
        assertEquals("JOHN", nysiis.nysiis("JOHN")); // No patterns match
        assertEquals("HENRY", nysiis.nysiis("HENRY")); // No patterns match
        assertEquals("TOMMY", nysiis.nysiis("TOMMY")); // No patterns match
        assertEquals("SANDY", nysiis.nysiis("SANDY")); // No patterns match
    }

    @Test
    void testNysiisWithEdgeCases() {
        // Test with edge cases that require specific handling.
        assertEquals("", nysiis.nysiis("S")); // Should remove last character 'S' resulting in empty string.
        assertEquals("R", nysiis.nysiis("RAY")); // Should remove last 'A' resulting in 'R'.
        assertEquals("H", nysiis.nysiis("HAY")); // Should remove last 'A' resulting in 'H'.
        assertEquals("Y", nysiis.nysiis("AAAY")); // Should remove last 'A' and 'AY' resulting in 'Y'.
        assertEquals("RAYMOND", nysiis.nysiis("RAYMOND")); // Should remain unchanged as no patterns match.
        assertEquals("FFOEBE", nysiis.nysiis("PHOEBE")); // Test for 'PHOEBE' to 'FFOEBE'
    }

    @Test
    void testNysiisWithMultipleTransformations() {
        // Test with input strings that require multiple transformations.
        assertEquals("NOC", nysiis.nysiis("KNOCK")); // 'KN' to 'NN' and 'K' to 'C'
        assertEquals("HENRYSON", nysiis.nysiis("HENRYSON")); // No patterns match
        assertEquals("FFILIPSON", nysiis.nysiis("PHILIPSON")); // 'PH' to 'FF'
        assertEquals("SSCHOLARS", nysiis.nysiis("SCHOLARS")); // 'SCH' to 'SSS'
    }

    @Test
    void testNysiisWithSpecialCases() {
        // Additional special case tests
        assertEquals("AAAYY", nysiis.nysiis("AAAYY")); // Should remove last 'A' and 'AY' resulting in 'Y'.
        assertEquals("DANIEL", nysiis.nysiis("DANIEL")); // Should remain unchanged as no patterns match.
        assertEquals("K", nysiis.nysiis("KAY")); // Should remove last 'A' resulting in 'K'.
        assertEquals("TRANAN", nysiis.nysiis("Trueman")); // Test for 'Trueman'
    }
}
```