```java
package org.apache.commons.codec.language;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class Nysiis_nysiis_Test {

    private Nysiis nysiis;

    @BeforeAll
    static void setupBeforeAll() {
        // Any setup needed before all tests run
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the Nysiis instance before each test
        nysiis = new Nysiis(false); // or use new Nysiis() for strict mode
    }

    @AfterEach
    void teardownAfterEach() {
        // Any cleanup needed after each test
    }

    @AfterAll
    static void teardownAfterAll() {
        // Any cleanup needed after all tests are run
    }

    @Test
    void testNysiisWithNull() {
        assertNull(nysiis.nysiis(null), "Nysiis should return null for null input");
    }

    @Test
    void testNysiisWithEmptyString() {
        assertEquals("", nysiis.nysiis(""), "Nysiis should return empty string for empty input");
    }

    @Test
    void testNysiisWithVariousInputs() {
        assertEquals("MCANT", nysiis.nysiis("MACINTOSH"), "Failed on MACINTOSH");
        assertEquals("NAT", nysiis.nysiis("KNUTH"), "Failed on KNUTH");
        assertEquals("XY", nysiis.nysiis("XAY"), "Failed on XAY");
        assertEquals("XY", nysiis.nysiis("XAYS"), "Failed on XAYS");
    }

    @Test
    void testNysiisWithStrictMode() {
        Nysiis strictNysiis = new Nysiis(true);
        assertEquals("WASTAR", strictNysiis.nysiis("WESTERLUND"), "Failed on WESTERLUND with strict mode");
    }
}
```