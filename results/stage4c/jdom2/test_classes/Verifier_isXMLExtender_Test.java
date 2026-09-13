package org.jdom2;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

// No need for Mockito imports as there are no dependencies to mock
class Verifier_isXMLExtender_Test {

    @Test
    void testIsXMLExtender_withValidExtenderCharacters() {
        // Test valid extender characters
        assertTrue(Verifier.isXMLExtender((char) 0xb7), "0xb7 should be a valid XML extender");
        assertTrue(Verifier.isXMLExtender((char) 0x2d0), "0x2d0 should be a valid XML extender");
        assertTrue(Verifier.isXMLExtender((char) 0x2d1), "0x2d1 should be a valid XML extender");
        assertTrue(Verifier.isXMLExtender((char) 0x387), "0x387 should be a valid XML extender");
        assertTrue(Verifier.isXMLExtender((char) 0x640), "0x640 should be a valid XML extender");
        assertTrue(Verifier.isXMLExtender((char) 0xe46), "0xe46 should be a valid XML extender");
        assertTrue(Verifier.isXMLExtender((char) 0xec6), "0xec6 should be a valid XML extender");
        assertTrue(Verifier.isXMLExtender((char) 0x3005), "0x3005 should be a valid XML extender");
        assertTrue(Verifier.isXMLExtender((char) 0x3031), "0x3031 should be a valid XML extender");
        assertTrue(Verifier.isXMLExtender((char) 0x3032), "0x3032 should be a valid XML extender");
        assertTrue(Verifier.isXMLExtender((char) 0x3033), "0x3033 should be a valid XML extender");
        assertTrue(Verifier.isXMLExtender((char) 0x3034), "0x3034 should be a valid XML extender");
        assertTrue(Verifier.isXMLExtender((char) 0x3035), "0x3035 should be a valid XML extender");
        assertTrue(Verifier.isXMLExtender((char) 0x309d), "0x309d should be a valid XML extender");
        assertTrue(Verifier.isXMLExtender((char) 0x309e), "0x309e should be a valid XML extender");
        assertTrue(Verifier.isXMLExtender((char) 0x30fc), "0x30fc should be a valid XML extender");
        assertTrue(Verifier.isXMLExtender((char) 0x30fd), "0x30fd should be a valid XML extender");
        assertTrue(Verifier.isXMLExtender((char) 0x30fe), "0x30fe should be a valid XML extender");
    }

    @Test
    void testIsXMLExtender_withInvalidCharacters() {
        // Test characters that are not valid extenders
        // 'A'
        assertFalse(Verifier.isXMLExtender((char) 0x41), "0x41 should not be a valid XML extender");
        // 'Z'
        assertFalse(Verifier.isXMLExtender((char) 0x5a), "0x5a should not be a valid XML extender");
        // 'a'
        assertFalse(Verifier.isXMLExtender((char) 0x61), "0x61 should not be a valid XML extender");
        // 'z'
        assertFalse(Verifier.isXMLExtender((char) 0x7a), "0x7a should not be a valid XML extender");
        // '0'
        assertFalse(Verifier.isXMLExtender((char) 0x30), "0x30 should not be a valid XML extender");
        // '9'
        assertFalse(Verifier.isXMLExtender((char) 0x39), "0x39 should not be a valid XML extender");
        // Space
        assertFalse(Verifier.isXMLExtender((char) 0x20), "0x20 should not be a valid XML extender");
        // '/'
        assertFalse(Verifier.isXMLExtender((char) 0x2f), "0x2f should not be a valid XML extender");
    }

    @Test
    void testIsXMLExtender_withCharactersBelow0xb6() {
        // Test characters less than 0xb6 to ensure they return false
        for (char c = 0; c < 0xb6; c++) {
            assertFalse(Verifier.isXMLExtender(c), "Character " + (int) c + " should not be a valid XML extender");
        }
    }

    @Test
    void testIsXMLExtender_withBoundaryExtenderCharacters() {
        // Test boundary conditions for extender characters
        assertFalse(Verifier.isXMLExtender((char) 0xb6), "0xb6 should not be a valid XML extender");
        assertTrue(Verifier.isXMLExtender((char) 0xb7), "0xb7 should be a valid XML extender");
        assertFalse(Verifier.isXMLExtender((char) 0x3030), "0x3030 should not be a valid XML extender");
        assertTrue(Verifier.isXMLExtender((char) 0x3031), "0x3031 should be a valid XML extender");
        assertFalse(Verifier.isXMLExtender((char) 0x309c), "0x309c should not be a valid XML extender");
        assertTrue(Verifier.isXMLExtender((char) 0x309d), "0x309d should be a valid XML extender");
        assertFalse(Verifier.isXMLExtender((char) 0x30fb), "0x30fb should not be a valid XML extender");
        assertTrue(Verifier.isXMLExtender((char) 0x30fc), "0x30fc should be a valid XML extender");
    }
}
