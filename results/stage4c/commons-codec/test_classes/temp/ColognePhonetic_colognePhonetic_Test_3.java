package org.apache.commons.codec.language;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class ColognePhonetic_colognePhonetic_Test {

    private ColognePhonetic colognePhonetic;

    class ColognePhonetic {

        public String colognePhonetic(String text) {
            if (text == null) {
                return null;
            }
            text = text.toUpperCase().replaceAll("[^A-Z]", "");
            StringBuilder output = new StringBuilder();
            char lastCode = '/';
            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                char code = '/';
                switch(c) {
                    case 'A':
                    case 'E':
                    case 'I':
                    case 'J':
                    case 'O':
                    case 'U':
                    case 'Y':
                        code = '0';
                        break;
                    case 'B':
                    case 'P':
                        if (i + 1 < text.length() && text.charAt(i + 1) == 'H') {
                            code = '3';
                        } else {
                            code = '1';
                        }
                        break;
                    case 'D':
                    case 'T':
                        if (i + 1 < text.length() && (text.charAt(i + 1) == 'C' || text.charAt(i + 1) == 'S' || text.charAt(i + 1) == 'Z')) {
                            code = '8';
                        } else {
                            code = '2';
                        }
                        break;
                    case 'F':
                    case 'V':
                    case 'W':
                        code = '3';
                        break;
                    case 'G':
                    case 'K':
                    case 'Q':
                        code = '4';
                        break;
                    case 'C':
                        if (i == 0 || (i > 0 && (text.charAt(i - 1) != 'S' && text.charAt(i - 1) != 'Z'))) {
                            if (i + 1 < text.length() && (text.charAt(i + 1) == 'A' || text.charAt(i + 1) == 'H' || text.charAt(i + 1) == 'K' || text.charAt(i + 1) == 'L' || text.charAt(i + 1) == 'O' || text.charAt(i + 1) == 'Q' || text.charAt(i + 1) == 'R' || text.charAt(i + 1) == 'U' || text.charAt(i + 1) == 'X')) {
                                code = '4';
                            } else {
                                code = '8';
                            }
                        } else {
                            code = '8';
                        }
                        break;
                    case 'X':
                        if (i > 0 && (text.charAt(i - 1) == 'C' || text.charAt(i - 1) == 'K' || text.charAt(i - 1) == 'Q')) {
                            code = '8';
                        } else {
                            code = '4';
                            if (i + 1 < text.length()) {
                                i++;
                                code = '8';
                            }
                        }
                        break;
                    case 'L':
                        code = '5';
                        break;
                    case 'M':
                    case 'N':
                        code = '6';
                        break;
                    case 'R':
                        code = '7';
                        break;
                    case 'S':
                    case 'Z':
                        code = '8';
                        break;
                }
                if (code != '/' && (code != lastCode || code == '0')) {
                    if (code != '0' || output.length() == 0) {
                        output.append(code);
                    }
                    lastCode = code;
                }
            }
            return output.toString();
        }
    }

    @BeforeEach
    void setupBeforeEach() {
        colognePhonetic = new ColognePhonetic();
    }

    @Test
    void testColognePhoneticWithValidInput() {
        // Given
        String input = "Muller";
        // When
        String result = colognePhonetic.colognePhonetic(input);
        // Then
        assertNotNull(result);
        assertEquals("657", result);
    }

    @Test
    void testColognePhoneticWithNullInput() {
        // Given
        String input = null;
        // When
        String result = colognePhonetic.colognePhonetic(input);
        // Then
        assertNull(result);
    }

    @Test
    void testColognePhoneticWithEmptyInput() {
        // Given
        String input = "";
        // When
        String result = colognePhonetic.colognePhonetic(input);
        // Then
        assertEquals("", result);
    }

    @Test
    void testColognePhoneticWithVowelsAndY() {
        // Given
        String input = "AEIJOUY";
        // When
        String result = colognePhonetic.colognePhonetic(input);
        // Then
        assertEquals("0000000", result);
    }

    @Test
    void testColognePhoneticWithBP() {
        // Given
        String input = "BP";
        // When
        String result = colognePhonetic.colognePhonetic(input);
        // Then
        assertEquals("11", result);
    }

    @Test
    void testColognePhoneticWithPH() {
        // Given
        String input = "PH";
        // When
        String result = colognePhonetic.colognePhonetic(input);
        // Then
        assertEquals("3", result);
    }

    @Test
    void testColognePhoneticWithDT() {
        // Given
        String input = "DT";
        // When
        String result = colognePhonetic.colognePhonetic(input);
        // Then
        assertEquals("28", result);
    }

    @Test
    void testColognePhoneticWithCSZ() {
        // Given
        String input = "CSZ";
        // When
        String result = colognePhonetic.colognePhonetic(input);
        // Then
        assertEquals("848", result);
    }

    @Test
    void testColognePhoneticWithFPVW() {
        // Given
        String input = "FPVW";
        // When
        String result = colognePhonetic.colognePhonetic(input);
        // Then
        assertEquals("3333", result);
    }

    @Test
    void testColognePhoneticWithGKQ() {
        // Given
        String input = "GKQ";
        // When
        String result = colognePhonetic.colognePhonetic(input);
        // Then
        assertEquals("444", result);
    }

    @Test
    void testColognePhoneticWithX() {
        // Given
        String input = "X";
        // When
        String result = colognePhonetic.colognePhonetic(input);
        // Then
        assertEquals("48", result);
    }

    @Test
    void testColognePhoneticWithR() {
        // Given
        String input = "R";
        // When
        String result = colognePhonetic.colognePhonetic(input);
        // Then
        assertEquals("7", result);
    }

    @Test
    void testColognePhoneticWithL() {
        // Given
        String input = "L";
        // When
        String result = colognePhonetic.colognePhonetic(input);
        // Then
        assertEquals("5", result);
    }

    @Test
    void testColognePhoneticWithMN() {
        // Given
        String input = "MN";
        // When
        String result = colognePhonetic.colognePhonetic(input);
        // Then
        assertEquals("66", result);
    }

    @Test
    void testColognePhoneticWithH() {
        // Given
        String input = "H";
        // When
        String result = colognePhonetic.colognePhonetic(input);
        // Then
        assertEquals("", result);
    }

    @Test
    void testColognePhoneticWithABCD() {
        // Given
        String input = "ABCD";
        // When
        String result = colognePhonetic.colognePhonetic(input);
        // Then
        assertEquals("0142", result);
    }

    @Test
    void testColognePhoneticWithSCH() {
        // Given
        String input = "SCH";
        // When
        String result = colognePhonetic.colognePhonetic(input);
        // Then
        assertEquals("84", result);
    }

    @Test
    void testColognePhoneticWithCK() {
        // Given
        String input = "CK";
        // When
        String result = colognePhonetic.colognePhonetic(input);
        // Then
        assertEquals("4", result);
    }

    @Test
    void testColognePhoneticWithQX() {
        // Given
        String input = "QX";
        // When
        String result = colognePhonetic.colognePhonetic(input);
        // Then
        assertEquals("48", result);
    }

    @Test
    void testColognePhoneticWithTSC() {
        // Given
        String input = "TSC";
        // When
        String result = colognePhonetic.colognePhonetic(input);
        // Then
        assertEquals("84", result);
    }

    @Test
    void testColognePhoneticWithAHKLOQRUX() {
        // Given
        String input = "AHKLOQRUX";
        // When
        String result = colognePhonetic.colognePhonetic(input);
        // Then
        assertEquals("045740478", result);
    }

    @Test
    void testColognePhoneticWithZXC() {
        // Given
        String input = "ZXC";
        // When
        String result = colognePhonetic.colognePhonetic(input);
        // Then
        assertEquals("884", result);
    }

    @Test
    void testColognePhoneticWithPHT() {
        // Given
        String input = "PHT";
        // When
        String result = colognePhonetic.colognePhonetic(input);
        // Then
        assertEquals("38", result);
    }

    @Test
    void testColognePhoneticWithMNZ() {
        // Given
        String input = "MNZ";
        // When
        String result = colognePhonetic.colognePhonetic(input);
        // Then
        assertEquals("668", result);
    }

    @Test
    void testColognePhoneticWithLHR() {
        // Given
        String input = "LHR";
        // When
        String result = colognePhonetic.colognePhonetic(input);
        // Then
        assertEquals("57", result);
    }

    @Test
    void testColognePhoneticWithGQ() {
        // Given
        String input = "GQ";
        // When
        String result = colognePhonetic.colognePhonetic(input);
        // Then
        assertEquals("44", result);
    }
}
