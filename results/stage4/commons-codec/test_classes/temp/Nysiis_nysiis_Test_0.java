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

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the Nysiis instance before each test
        // or true, depending on the test case
        nysiis = new Nysiis(false);
    }

    @Test
    void testNysiisEncoding() {
        assertEquals("BRAN", nysiis.encode("Brian"));
        assertEquals("CAP", nysiis.encode("Capp"));
        assertEquals("DAD", nysiis.encode("Dent"));
        assertEquals("DAN", nysiis.encode("Dane"));
        assertEquals("XY", nysiis.encode("XAY"));
        assertEquals("XY", nysiis.encode("XAYS"));
        // Add more test cases as needed
    }

    @Test
    void testNysiisEncodingWithNullInput() {
        // Test with null input to verify that the method returns null.
        assertNull(nysiis.nysiis(null));
    }

    @Test
    void testNysiisEncodingWithEmptyString() {
        // Test with an empty string to verify that the method returns an empty string.
        assertEquals("", nysiis.nysiis(""));
    }

    @Test
    void testNysiisEncodingWithMacDonald() {
        // Test with input starting with 'MAC' to verify that it is replaced with 'MCC'.
        assertEquals("MCCD", nysiis.nysiis("MACDonald"));
    }

    @Test
    void testNysiisEncodingWithKnight() {
        // Test with input starting with 'KN' to verify that it is replaced with 'NN'.
        assertEquals("NIGHT", nysiis.nysiis("KNight"));
    }

    @Test
    void testNysiisEncodingWithKite() {
        // Test with input starting with 'K' to verify that it is replaced with 'C'.
        assertEquals("CITE", nysiis.nysiis("Kite"));
    }

    @Test
    void testNysiisEncodingWithPhilip() {
        // Test with input starting with 'PH' to verify that it is replaced with 'FF'.
        assertEquals("FILI", nysiis.nysiis("Philip"));
    }

    @Test
    void testNysiisEncodingWithSchool() {
        // Test with input starting with 'SCH' to verify that it is replaced with 'SSS'.
        assertEquals("SSCOL", nysiis.nysiis("SCHOOL"));
    }

    @Test
    void testNysiisEncodingWithDoe() {
        // Test with input ending with 'E' to verify that it is replaced with 'Y'.
        assertEquals("D", nysiis.nysiis("Doe"));
    }

    @Test
    void testNysiisEncodingWithKnightEnding() {
        // Test with input ending with 'HT' to verify that it is replaced with 'D'.
        assertEquals("NIGHT", nysiis.nysiis("Knight"));
    }

    @Test
    void testNysiisEncodingWithSamantha() {
        // Test with input where last character is 'S', to verify that 'S' is removed.
        assertEquals("SAMANT", nysiis.nysiis("Samantha"));
    }

    @Test
    void testNysiisEncodingWithRay() {
        // Test with input where last characters are 'AY', to verify that 'AY' is replaced with 'Y'.
        assertEquals("R", nysiis.nysiis("Ray"));
    }

    @Test
    void testNysiisEncodingWithSara() {
        // Test with input where last character is 'A', to verify that 'A' is removed.
        assertEquals("S", nysiis.nysiis("Sara"));
    }

    @Test
    void testNysiisEncodingWithAlexander() {
        // Test with input to verify that the method correctly transcodes remaining characters and applies all rules.
        assertEquals("ALXAND", nysiis.nysiis("Alexander"));
    }

    @Test
    void testNysiisEncodingWithJohn() {
        // Test with a normal name that does not trigger any specific rules.
        assertEquals("JON", nysiis.nysiis("John"));
    }

    @Test
    void testNysiisEncodingWithPhilipson() {
        // Test with input starting with 'PH' to verify that it is replaced with 'FF' and processes remaining characters correctly.
        assertEquals("FILSON", nysiis.nysiis("Philipson"));
    }

    @Test
    void testNysiisEncodingWithMacArthur() {
        // Test with input starting with 'MAC' to verify that it is replaced with 'MCC' and applies other rules.
        assertEquals("MCCAR", nysiis.nysiis("MacArthur"));
    }

    @Test
    void testNysiisEncodingWithKatherine() {
        // Test with input starting with 'K' to verify that it is replaced with 'C' and processes remaining characters correctly.
        assertEquals("CATHR", nysiis.nysiis("Katherine"));
    }

    @Test
    void testNysiisEncodingWithSchmidt() {
        // Test with input starting with 'SCH' to verify that it is replaced with 'SSS' and applies ending rules.
        assertEquals("SSMID", nysiis.nysiis("Schmidt"));
    }

    @Test
    void testNysiisEncodingWithKnightly() {
        // Test with input ending with 'LY' to verify that it processes correctly without triggering ending rules.
        assertEquals("NIGHTL", nysiis.nysiis("Knightly"));
    }

    @Test
    void testNysiisEncodingWithSingleA() {
        // Test with a single character 'A' to verify that it is removed.
        assertEquals("", nysiis.nysiis("A"));
    }

    @Test
    void testNysiisEncodingWithDoubleA() {
        // Test with input 'AA' to verify that both 'A's are removed.
        assertEquals("", nysiis.nysiis("AA"));
    }

    @Test
    void testNysiisEncodingWithBobby() {
        // Test with a normal name to verify that it correctly processes and retains unique characters.
        assertEquals("BOBY", nysiis.nysiis("Bobby"));
    }

    @Test
    void testNysiisEncodingWithStringNull() {
        // Test with null input to verify that the method returns null.
        assertNull(nysiis.nysiis("null"));
    }
}
