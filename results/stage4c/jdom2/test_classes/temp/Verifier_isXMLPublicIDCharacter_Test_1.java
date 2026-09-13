package org.jdom2;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Verifier_isXMLPublicIDCharacter_Test {

    class Verifier {

        public static boolean isXMLPublicIDCharacter(char c) {
            // Define the valid ranges for XML Public ID characters
            if (c == '\n' || c == '\r' || c == '\t' || c == ' ' || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || (c >= '\'' && c <= ';') || (c >= '?' && c <= 'Z')) {
                return true;
            }
            return false;
        }
    }

    @Test
    void testIsXMLPublicIDCharacter_ValidCharacters() {
        // Test valid characters for XML Public ID
        assertTrue(Verifier.isXMLPublicIDCharacter('a'), "Lowercase 'a' should be valid as it is within 'a' to 'z'.");
        assertTrue(Verifier.isXMLPublicIDCharacter('Z'), "Uppercase 'Z' should be valid as it is within '?' to 'Z'.");
        assertTrue(Verifier.isXMLPublicIDCharacter(' '), "Space character should be valid as it is explicitly checked.");
        assertTrue(Verifier.isXMLPublicIDCharacter('-'), "Character '-' should be valid as it is within ''' to ';'.");
        assertTrue(Verifier.isXMLPublicIDCharacter('\n'), "Newline character '\\n' should be valid as it is explicitly checked.");
        assertTrue(Verifier.isXMLPublicIDCharacter('\r'), "Carriage return '\\r' should be valid as it is explicitly checked.");
        assertTrue(Verifier.isXMLPublicIDCharacter('\t'), "Tab character '\\t' should be valid as it is explicitly checked.");
        assertTrue(Verifier.isXMLPublicIDCharacter('M'), "Uppercase 'M' should be valid as it is within '?' to 'Z'.");
        assertTrue(Verifier.isXMLPublicIDCharacter('0'), "Digit '0' should be valid as it is within '?' to 'Z'.");
        assertTrue(Verifier.isXMLPublicIDCharacter('+'), "Character '+' should be valid as it is within ''' to ';'.");
        assertTrue(Verifier.isXMLPublicIDCharacter(','), "Character ',' should be valid as it is within ''' to ';'.");
        assertTrue(Verifier.isXMLPublicIDCharacter('.'), "Character '.' should be valid as it is within ''' to ';'.");
        assertTrue(Verifier.isXMLPublicIDCharacter(':'), "Character ':' should be valid as it is within ''' to ';'.");
        assertTrue(Verifier.isXMLPublicIDCharacter('*'), "Character '*' should be valid as it is within ''' to ';'.");
        assertTrue(Verifier.isXMLPublicIDCharacter('@'), "Character '@' should be valid as it is within '?' to 'Z'.");
    }

    @Test
    void testIsXMLPublicIDCharacter_InvalidCharacters() {
        // Test invalid characters for XML Public ID
        assertFalse(Verifier.isXMLPublicIDCharacter('~'), "Character '~' should be invalid as it is not within any valid range.");
        assertFalse(Verifier.isXMLPublicIDCharacter('^'), "Character '^' should be invalid as it is not within any valid range.");
        assertFalse(Verifier.isXMLPublicIDCharacter('`'), "Character '`' should be invalid as it is not within any valid range.");
        assertFalse(Verifier.isXMLPublicIDCharacter('9'), "Character '9' should be invalid as it is not within any valid range.");
        assertFalse(Verifier.isXMLPublicIDCharacter('&'), "Character '&' should be invalid as it is not within any valid range.");
        assertFalse(Verifier.isXMLPublicIDCharacter('>'), "Character '>' should be invalid as it is not within any valid range.");
    }
}
