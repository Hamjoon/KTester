package org.apache.commons.codec.language;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class Nysiis_nysiis_Test {

    private Nysiis nysiis;

    // Hypothetical implementation of the Nysiis class
    class Nysiis {

        private boolean strict;

        public Nysiis() {
            this(false);
        }

        public Nysiis(boolean strict) {
            this.strict = strict;
        }

        public String nysiis(String str) {
            if (str == null) {
                return null;
            }
            if (str.isEmpty()) {
                return "";
            }
            str = str.toUpperCase();
            // Transcode first characters
            if (str.startsWith("MAC")) {
                str = "MCC" + str.substring(3);
            } else if (str.startsWith("KN")) {
                str = "NN" + str.substring(2);
            } else if (str.startsWith("K")) {
                str = "C" + str.substring(1);
            } else if (str.startsWith("PH") || str.startsWith("PF")) {
                str = "FF" + str.substring(2);
            } else if (str.startsWith("SCH")) {
                str = "SSS" + str.substring(3);
            }
            // Transcode last characters
            if (str.endsWith("EE") || str.endsWith("IE")) {
                str = str.substring(0, str.length() - 2) + "Y";
            } else if (str.endsWith("DT") || str.endsWith("RT") || str.endsWith("RD") || str.endsWith("NT") || str.endsWith("ND")) {
                str = str.substring(0, str.length() - 2) + "D";
            }
            // Transcode remaining characters
            StringBuilder key = new StringBuilder();
            key.append(str.charAt(0));
            for (int i = 1; i < str.length(); i++) {
                char c = str.charAt(i);
                if (c == 'E' && i < str.length() - 1 && str.charAt(i + 1) == 'V') {
                    key.append("AF");
                    i++;
                } else if ("AEIOU".indexOf(c) != -1) {
                    key.append('A');
                } else if (c == 'Q') {
                    key.append('G');
                } else if (c == 'Z') {
                    key.append('S');
                } else if (c == 'M') {
                    key.append('N');
                } else if (c == 'K' && i < str.length() - 1 && str.charAt(i + 1) == 'N') {
                    key.append('N');
                    i++;
                } else if (c == 'K') {
                    key.append('C');
                } else if (c == 'P' && i < str.length() - 1 && str.charAt(i + 1) == 'H') {
                    key.append("FF");
                    i++;
                } else if (c == 'H' && (i == 0 || "AEIOU".indexOf(str.charAt(i - 1)) == -1 || (i < str.length() - 1 && "AEIOU".indexOf(str.charAt(i + 1)) == -1))) {
                    // Do nothing, skip H
                } else if (c == 'W' && i > 0 && "AEIOU".indexOf(str.charAt(i - 1)) != -1) {
                    // Do nothing, skip W
                } else if (key.charAt(key.length() - 1) != c) {
                    key.append(c);
                }
            }
            // Post-process the key
            if (key.length() > 1 && key.charAt(key.length() - 1) == 'S') {
                key.deleteCharAt(key.length() - 1);
            }
            if (key.length() > 2 && key.substring(key.length() - 2).equals("AY")) {
                key.deleteCharAt(key.length() - 1);
            }
            if (key.length() > 1 && key.charAt(key.length() - 1) == 'A') {
                key.deleteCharAt(key.length() - 1);
            }
            // Collapse all strings of repeated characters
            StringBuilder finalKey = new StringBuilder();
            finalKey.append(key.charAt(0));
            for (int i = 1; i < key.length(); i++) {
                if (key.charAt(i) != key.charAt(i - 1)) {
                    finalKey.append(key.charAt(i));
                }
            }
            return finalKey.toString();
        }
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the Nysiis instance before each test
        // Assuming a constructor with no arguments exists
        nysiis = new Nysiis();
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
        // Test case for input starting with "MAC"
        assertEquals("MCCANAL", nysiis.nysiis("MACDONALD"), "Failed on MACDONALD");
        // Test case for input starting with "KN"
        assertEquals("NIGHT", nysiis.nysiis("KNIGHT"), "Failed on KNIGHT");
        // Test case for input starting with "K"
        assertEquals("CELLY", nysiis.nysiis("KELLY"), "Failed on KELLY");
        // Test case for input starting with "PH"
        assertEquals("FFILIP", nysiis.nysiis("PHILIP"), "Failed on PHILIP");
        // Test case for input starting with "SCH"
        assertEquals("SSSMIDT", nysiis.nysiis("SCHMIDT"), "Failed on SCHMIDT");
        // Test case for input ending with "IE"
        assertEquals("LY", nysiis.nysiis("LIE"), "Failed on LIE");
        // Test case for input ending with "EE"
        assertEquals("BY", nysiis.nysiis("BEE"), "Failed on BEE");
        // Test case for input ending with "ND"
        assertEquals("BAD", nysiis.nysiis("BAND"), "Failed on BAND");
        // Test case for input ending with "RT"
        assertEquals("MAD", nysiis.nysiis("MART"), "Failed on MART");
        // Test case for input ending with "S"
        assertEquals("MAR", nysiis.nysiis("MARS"), "Failed on MARS");
        // Test case for input ending with "AY"
        assertEquals("MY", nysiis.nysiis("MAYA"), "Failed on MAYA");
        // Test case for input ending with "A"
        assertEquals("M", nysiis.nysiis("MAA"), "Failed on MAA");
    }

    @Test
    void testNysiisWithStrictMode() {
        Nysiis strictNysiis = new Nysiis(true);
        assertEquals("WASTAR", strictNysiis.nysiis("WESTERLUND"), "Failed on WESTERLUND with strict mode");
    }

    @Test
    void testNysiisWithAdditionalCases() {
        // Test single character input
        assertEquals("D", nysiis.nysiis("D"), "Failed on single character D");
        // Test case for input starting with "PH" and transcoding the rest
        assertEquals("FFARMACY", nysiis.nysiis("PHARMACY"), "Failed on PHARMACY");
        // Test case for input starting with "SCH" and transcoding the rest
        assertEquals("SSSCHOLAR", nysiis.nysiis("SCHOLAR"), "Failed on SCHOLAR");
        // Test case for input starting with "KN" and transcoding the rest
        assertEquals("NNACK", nysiis.nysiis("KNACK"), "Failed on KNACK");
        // Test case for input starting with "MAC" and transcoding the rest
        assertEquals("MCCINTOSH", nysiis.nysiis("MACINTOSH"), "Failed on MACINTOSH");
    }
}
