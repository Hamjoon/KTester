package org.jdom2;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.lang.reflect.Constructor;

@ExtendWith(MockitoExtension.class)
class Verifier_isXMLPublicIDCharacter_Test {

    private Verifier verifier;

    @BeforeEach
    void setupBeforeEach() {
        // Use reflection to access the private constructor of Verifier
        Constructor<Verifier> constructor = Verifier.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        verifier = constructor.newInstance();
    }

    @Test
    void testIsXMLPublicIDCharacter_ValidCharacters() {
        // Given valid XML public ID characters
        char[] validCharacters = { ' ', '\n', '\r', '!', '=', '#', '$', '_', '%', 'a', 'Z', '-', '(', ')', '+', ',', '.', '/', ':', '?', ';', '*', '@', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        // When checking each character
        for (char c : validCharacters) {
            // Then it should return true
            assertTrue(Verifier.isXMLPublicIDCharacter(c), "Failed for character: " + c);
        }
    }

    @Test
    void testIsXMLPublicIDCharacter_InvalidCharacters() {
        // Given invalid XML public ID characters
        char[] invalidCharacters = { '\u0000', '\u001F', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '}', '~', '<', '>', '&' };
        // When checking each character
        for (char c : invalidCharacters) {
            // Then it should return false
            assertFalse(Verifier.isXMLPublicIDCharacter(c), "Failed for character: " + c);
        }
    }

    @Test
    void testIsXMLPublicIDCharacter_SpecificValidCases() {
        // Test with specific valid characters
        char[] specificValidCases = { 'a', 'Z', ' ', '!', '#', '$', '%', '\n', '\r', '\t', '?', '\'', '(', ')', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        for (char c : specificValidCases) {
            assertTrue(Verifier.isXMLPublicIDCharacter(c), "Failed for character: " + c);
        }
    }

    @Test
    void testIsXMLPublicIDCharacter_SpecificInvalidCases() {
        // Test with specific invalid characters
        char[] specificInvalidCases = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '}', '~', '<', '>', '&' };
        for (char c : specificInvalidCases) {
            assertFalse(Verifier.isXMLPublicIDCharacter(c), "Failed for character: " + c);
        }
    }
}
