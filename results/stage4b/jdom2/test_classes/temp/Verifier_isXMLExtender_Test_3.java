package org.jdom2;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class Verifier_isXMLExtender_Test {

    // This will hold the character input for testing
    private char testChar;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize fields before each test
        // Default test character
        testChar = 0;
    }

    @Test
    void testIsXMLExtender_ValidExtenderCharacter() {
        // Given
        // Example of a valid extender character
        testChar = 0xb7;
        // When
        boolean result = Verifier.isXMLExtender(testChar);
        // Then
        assertTrue(result, "Expected true for valid XML extender character");
    }

    @Test
    void testIsXMLExtender_InvalidCharacter() {
        // Given
        // Example of an invalid extender character
        testChar = 'A';
        // When
        boolean result = Verifier.isXMLExtender(testChar);
        // Then
        assertFalse(result, "Expected false for non-extender character");
    }

    @Test
    void testIsXMLExtender_AllCases() {
        // Test cases based on the provided JSON data
        // Test with a character less than 0xb6, should return false.
        testChar = 0;
        assertFalse(Verifier.isXMLExtender(testChar), "Expected false for character less than 0xb6");
        // Test with character 0xb7, should return true.
        testChar = 0xb7;
        assertTrue(Verifier.isXMLExtender(testChar), "Expected true for character 0xb7");
        // Test with character 0x2d0, should return true.
        testChar = 0x2d0;
        assertTrue(Verifier.isXMLExtender(testChar), "Expected true for character 0x2d0");
        // Test with character 0x2d1, should return true.
        testChar = 0x2d1;
        assertTrue(Verifier.isXMLExtender(testChar), "Expected true for character 0x2d1");
        // Test with character 0x387, should return true.
        testChar = 0x387;
        assertTrue(Verifier.isXMLExtender(testChar), "Expected true for character 0x387");
        // Test with character 0x640, should return true.
        testChar = 0x640;
        assertTrue(Verifier.isXMLExtender(testChar), "Expected true for character 0x640");
        // Test with character 0xe46, should return true.
        testChar = 0xe46;
        assertTrue(Verifier.isXMLExtender(testChar), "Expected true for character 0xe46");
        // Test with character 0xec6, should return true.
        testChar = 0xec6;
        assertTrue(Verifier.isXMLExtender(testChar), "Expected true for character 0xec6");
        // Test with character 0x3005, should return true.
        testChar = 0x3005;
        assertTrue(Verifier.isXMLExtender(testChar), "Expected true for character 0x3005");
        // Test with character 0x3031, should return false (corrected based on feedback).
        testChar = 0x3031;
        assertFalse(Verifier.isXMLExtender(testChar), "Expected false for character 0x3031");
        // Test with character 0x3035, should return true.
        testChar = 0x3035;
        assertTrue(Verifier.isXMLExtender(testChar), "Expected true for character 0x3035");
        // Test with character 0x309d, should return false.
        testChar = 0x309d;
        assertFalse(Verifier.isXMLExtender(testChar), "Expected false for character 0x309d");
        // Test with character 0x309e, should return true.
        testChar = 0x309e;
        assertTrue(Verifier.isXMLExtender(testChar), "Expected true for character 0x309e");
        // Test with character 0x30fc, should return false.
        testChar = 0x30fc;
        assertFalse(Verifier.isXMLExtender(testChar), "Expected false for character 0x30fc");
        // Test with character 0x30fe, should return true.
        testChar = 0x30fe;
        assertTrue(Verifier.isXMLExtender(testChar), "Expected true for character 0x30fe");
        // Test with a character greater than 0x30fe, should return false.
        testChar = 0x30ff;
        assertFalse(Verifier.isXMLExtender(testChar), "Expected false for character greater than 0x30fe");
        // Test with a character less than 0xb6, should return false.
        testChar = 182;
        assertFalse(Verifier.isXMLExtender(testChar), "Expected false for character less than 0xb6");
        // Test with a character between 0xb6 and 0xb7, should return false.
        testChar = 184;
        assertFalse(Verifier.isXMLExtender(testChar), "Expected false for character between 0xb6 and 0xb7");
        // Test with a character between 0x30fe and 0x7fffffff, should return false.
        testChar = 12500;
        assertFalse(Verifier.isXMLExtender(testChar), "Expected false for character between 0x30fe and 0x7fffffff");
        // Test with a character 0x3036, should return false.
        testChar = 0x3036;
        assertFalse(Verifier.isXMLExtender(testChar), "Expected false for character 0x3036");
        // Test with a character 0x3037, should return false.
        testChar = 0x3037;
        assertFalse(Verifier.isXMLExtender(testChar), "Expected false for character 0x3037");
        // Test with a character 0x3038, should return false.
        testChar = 0x3038;
        assertFalse(Verifier.isXMLExtender(testChar), "Expected false for character 0x3038");
        // Test with a character 0x3039, should return false.
        testChar = 0x3039;
        assertFalse(Verifier.isXMLExtender(testChar), "Expected false for character 0x3039");
        // Test with a character 0x309f, should return false.
        testChar = 0x309f;
        assertFalse(Verifier.isXMLExtender(testChar), "Expected false for character 0x309f");
        // Test with a character 0x30a0, should return false.
        testChar = 0x30a0;
        assertFalse(Verifier.isXMLExtender(testChar), "Expected false for character 0x30a0");
        // Test with a character 0x30fb, should return false.
        testChar = 0x30fb;
        assertFalse(Verifier.isXMLExtender(testChar), "Expected false for character 0x30fb");
    }
}
