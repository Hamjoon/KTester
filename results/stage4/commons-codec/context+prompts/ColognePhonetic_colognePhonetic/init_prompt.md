You are tasked with creating a framework for a test class in Java. Now analyze the following information carefully:

1. The method that needs to be tested and its parameters.
2. The constructor of the target class and its parameters.
3. Any dependencies or external resources used by the target class.

Based on this analysis, complete the test class template in the provided structure:

1. Initialize necessary fields and dependencies within setupBeforeAll() and setupBeforeEach().
2. Handle any required cleanup in teardownAfterEach() and teardownAfterAll().
3. Make sure not to invoke any private methods or fields of the target class and dependent class.
4. Refer to the "given" phase in the existing test class for guidance.
5. Add necessary import statements and ensure there are no syntax errors in the generated code.

@input{focal method and target class}
Here is the code of focal method, `colognePhonetic(String)`, with the focal class `ColognePhonetic_colognePhonetic_Test`:
```Java
package org.apache.commons.codec.language;
import java.util.Locale;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
import java.util.Arrays;
public class ColognePhonetic implements StringEncoder {
    private static final char[] AEIJOUY = new char[]{ 'A', 'E', 'I', 'J', 'O', 'U', 'Y' };
    private static final char[] CSZ = new char[]{ 'C', 'S', 'Z' };
    private static final char[] FPVW = new char[]{ 'F', 'P', 'V', 'W' };
    private static final char[] GKQ = new char[]{ 'G', 'K', 'Q' };
    private static final char[] CKQ = new char[]{ 'C', 'K', 'Q' };
    private static final char[] AHKLOQRUX = new char[]{ 'A', 'H', 'K', 'L', 'O', 'Q', 'R', 'U', 'X' };
    private static final char[] SZ = new char[]{ 'S', 'Z' };
    private static final char[] AHKOQUX = new char[]{ 'A', 'H', 'K', 'O', 'Q', 'U', 'X' };
    private static final char[] DTX = new char[]{ 'D', 'T', 'X' };
    private static final char CHAR_IGNORE = '-';
        /**
     * This class is not thread-safe; the field {@link #length} is mutable.
     * However, it is not shared between threads, as it is constructed on demand
     * by the method {@link ColognePhonetic#colognePhonetic(String)}
     */
    static abstract class CologneBuffer {
        protected final char[] data;
    
        protected int length;
    
        public CologneBuffer(final char[] data) {
            this.data = data;
            this.length = data.length;
        }
    
        public CologneBuffer(final int buffSize) {
            this.data = new char[buffSize];
            this.length = 0;
        }
    
        protected abstract char[] copyData(int start, int length);
    
        public boolean isEmpty() {
            return length() == 0;
        }
    
        public int length() {
            return length;
        }
    
        @Override
        public String toString() {
            return new String(copyData(0, length));
        }
    }
    private final class CologneOutputBuffer extends CologneBuffer {
        private char lastCode;
    
        public CologneOutputBuffer(final int buffSize) {
            super(buffSize);
            lastCode = '/';// impossible value
    
        }
    
        @Override
        protected char[] copyData(final int start, final int length) {
            return Arrays.copyOfRange(data, start, length);
        }
    
        /**
         * Stores the next code in the output buffer, keeping track of the previous code.
         * '0' is only stored if it is the first entry.
         * Ignored chars are never stored.
         * If the code is the same as the last code (whether stored or not) it is not stored.
         *
         * @param code
         * 		the code to store.
         */
        public void put(final char code) {
            if (((code != CHAR_IGNORE) && (lastCode != code)) && ((code != '0') || (length == 0))) {
                data[length] = code;
                length++;
            }
            lastCode = code;
        }
    }
    private final class CologneInputBuffer extends CologneBuffer {
        public CologneInputBuffer(final char[] data) {
            super(data);
        }
    
        @Override
        protected char[] copyData(final int start, final int length) {
            final char[] newData = new char[length];
            System.arraycopy(data, (data.length - this.length) + start, newData, 0, length);
            return newData;
        }
    
        public char getNextChar() {
            return data[getNextPos()];
        }
    
        protected int getNextPos() {
            return data.length - length;
        }
    
        public char removeNext() {
            final char ch = getNextChar();
            length--;
            return ch;
        }
    }
public String colognePhonetic(final String text) {
        if (text == null) {
            return null;
        }
        final CologneInputBuffer input = new CologneInputBuffer(preprocess(text));
        final CologneOutputBuffer output = new CologneOutputBuffer(input.length() * 2);
        char nextChar;
        char lastChar = CHAR_IGNORE;
        char chr;
        while (!input.isEmpty()) {
            chr = input.removeNext();
            if (!input.isEmpty()) {
                nextChar = input.getNextChar();
            } else {
                nextChar = CHAR_IGNORE;
            }
            if ((chr < 'A') || (chr > 'Z')) {
                continue;// ignore unwanted characters
    
            }
            if (arrayContains(AEIJOUY, chr)) {
                output.put('0');
            } else if ((chr == 'B') || ((chr == 'P') && (nextChar != 'H'))) {
                output.put('1');
            } else if (((chr == 'D') || (chr == 'T')) && (!arrayContains(CSZ, nextChar))) {
                output.put('2');
            } else if (arrayContains(FPVW, chr)) {
                output.put('3');
            } else if (arrayContains(GKQ, chr)) {
                output.put('4');
            } else if ((chr == 'X') && (!arrayContains(CKQ, lastChar))) {
                output.put('4');
                output.put('8');
            } else if ((chr == 'S') || (chr == 'Z')) {
                output.put('8');
            } else if (chr == 'C') {
                if (output.isEmpty()) {
                    if (arrayContains(AHKLOQRUX, nextChar)) {
                        output.put('4');
                    } else {
                        output.put('8');
                    }
                } else if (arrayContains(SZ, lastChar) || (!arrayContains(AHKOQUX, nextChar))) {
                    output.put('8');
                } else {
                    output.put('4');
                }
            } else if (arrayContains(DTX, chr)) {
                output.put('8');
            } else {
                switch (chr) {
                    case 'R' :
                        output.put('7');
                        break;
                    case 'L' :
                        output.put('5');
                        break;
                    case 'M' :
                    case 'N' :
                        output.put('6');
                        break;
                    case 'H' :
                        output.put(CHAR_IGNORE);// needed by put
    
                        break;
                    default :
                        break;
                }
            }
            lastChar = chr;
        } 
        return output.toString();
    }
private static boolean arrayContains(final char[] arr, final char key);
    @Override
public Object encode(final Object object) throws EncoderException;
    @Override
public String encode(final String text);
    public boolean isEncodeEqual(final String text1, final String text2);
    private char[] preprocess(final String text);
}
```


@input{context information}

api document of class org.apache.commons.codec.language.ColognePhonetic: 

 * Encodes a string into a Cologne Phonetic value.
 * <p>
 * Implements the <a href="https://de.wikipedia.org/wiki/K%C3%B6lner_Phonetik">K&ouml;lner Phonetik</a> (Cologne
 * Phonetic) algorithm issued by Hans Joachim Postel in 1969.
 * </p>
 * <p>
 * The <i>K&ouml;lner Phonetik</i> is a phonetic algorithm which is optimized for the German language. It is related to
 * the well-known soundex algorithm.
 * </p>
 *
 * <h2>Algorithm</h2>
 *
 * <ul>
 *
 * <li>
 * <h3>Step 1:</h3>
 * After preprocessing (conversion to upper case, transcription of <a
 * href="https://en.wikipedia.org/wiki/Germanic_umlaut">germanic umlauts</a>, removal of non alphabetical characters) the
 * letters of the supplied text are replaced by their phonetic code according to the following table.
 * <table border="1">
 * <caption style="caption-side: bottom"><small><i>(Source: <a
 * href="https://de.wikipedia.org/wiki/K%C3%B6lner_Phonetik#Buchstabencodes">Wikipedia (de): K&ouml;lner Phonetik --
 * Buchstabencodes</a>)</i></small></caption> <tbody>
 * <tr>
 * <th>Letter</th>
 * <th>Context</th>
 * <th>Code</th>
 * </tr>
 * <tr>
 * <td>A, E, I, J, O, U, Y</td>
 * <td></td>
 * <td>0</td>
 * </tr>
 * <tr>
 *
 * <td>H</td>
 * <td></td>
 * <td>-</td>
 * </tr>
 * <tr>
 * <td>B</td>
 * <td></td>
 * <td rowspan="2">1</td>
 * </tr>
 * <tr>
 * <td>P</td>
 * <td>not before H</td>
 *
 * </tr>
 * <tr>
 * <td>D, T</td>
 * <td>not before C, S, Z</td>
 * <td>2</td>
 * </tr>
 * <tr>
 * <td>F, V, W</td>
 * <td></td>
 * <td rowspan="2">3</td>
 * </tr>
 * <tr>
 *
 * <td>P</td>
 * <td>before H</td>
 * </tr>
 * <tr>
 * <td>G, K, Q</td>
 * <td></td>
 * <td rowspan="3">4</td>
 * </tr>
 * <tr>
 * <td rowspan="2">C</td>
 * <td>at onset before A, H, K, L, O, Q, R, U, X</td>
 *
 * </tr>
 * <tr>
 * <td>before A, H, K, O, Q, U, X except after S, Z</td>
 * </tr>
 * <tr>
 * <td>X</td>
 * <td>not after C, K, Q</td>
 * <td>48</td>
 * </tr>
 * <tr>
 * <td>L</td>
 * <td></td>
 *
 * <td>5</td>
 * </tr>
 * <tr>
 * <td>M, N</td>
 * <td></td>
 * <td>6</td>
 * </tr>
 * <tr>
 * <td>R</td>
 * <td></td>
 * <td>7</td>
 * </tr>
 *
 * <tr>
 * <td>S, Z</td>
 * <td></td>
 * <td rowspan="6">8</td>
 * </tr>
 * <tr>
 * <td rowspan="3">C</td>
 * <td>after S, Z</td>
 * </tr>
 * <tr>
 * <td>at onset except before A, H, K, L, O, Q, R, U, X</td>
 * </tr>
 *
 * <tr>
 * <td>not before A, H, K, O, Q, U, X</td>
 * </tr>
 * <tr>
 * <td>D, T</td>
 * <td>before C, S, Z</td>
 * </tr>
 * <tr>
 * <td>X</td>
 * <td>after C, K, Q</td>
 * </tr>
 * </tbody>
 * </table>
 *
 * <h4>Example:</h4>
 *
 * {@code "M}&uuml;{@code ller-L}&uuml;<code>denscheidt"
 * =&gt; "MULLERLUDENSCHEIDT" =&gt; "6005507500206880022"</code>
 *
 * </li>
 *
 * <li>
 * <h3>Step 2:</h3>
 * Collapse of all multiple consecutive code digits.
 * <h4>Example:</h4>
 * {@code "6005507500206880022" =&gt; "6050750206802"}</li>
 *
 * <li>
 * <h3>Step 3:</h3>
 * Removal of all codes "0" except at the beginning. This means that two or more identical consecutive digits can occur
 * if they occur after removing the "0" digits.
 *
 * <h4>Example:</h4>
 * {@code "6050750206802" =&gt; "65752682"}</li>
 *
 * </ul>
 *
 * <p>
 * This class is thread-safe.
 * </p>
 *
 * @see <a href="https://de.wikipedia.org/wiki/K%C3%B6lner_Phonetik">Wikipedia (de): K&ouml;lner Phonetik (in German)</a>
 * @since 1.5
 

existing test class: 
```java
/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.codec.language;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.apache.commons.codec.AbstractStringEncoderTest;
import org.apache.commons.codec.EncoderException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

/**
 * Tests the {@code ColognePhonetic} class.
 *
 * <p>
 * Keep this file in UTF-8 encoding for proper Javadoc processing.
 * </p>
 */
public class ColognePhoneticTest extends AbstractStringEncoderTest<ColognePhonetic> {

    private static final Set<String> TESTSET = new HashSet<>();

    /** Character sequences to be tested by the code. */
    // @formatter:off
    private static final String[] MATCHES = {
            ".*[AEIOUJY].*",         // A, E, I, J, O, U, Y
            ".*H.*",                 // H
            ".*B.*",                 // B
            ".*P[^H].*",             // P not before H
            ".*[DT][^CSZ].*",        // D,T not before C,S,Z
            ".*[FVW].*",             // F,V,W
            ".*PH.*",                // P before H
            ".*[GKQ].*",             // G,K,Q
            "C[AHKLOQRUX].*",        // Initial C before A, H, K, L, O, Q, R, U, X
            ".*[^SZ]C[AHKLOQRUX].*", // C before A, H, K, L, O, Q, R, U, X but not after S, Z
            ".*[^CKQ]X.*",           // X not after C,K,Q
            ".*L.*",                 // L
            ".*[MN].*",              // M,N
            ".*R.*",                 // R
            ".*[SZ].*",              // S,Z
            ".*[SZ]C.*",             // C after S,Z
            "C[^AHKLOQRUX].*",       // Initial C except before A, H, K, L, O, Q, R, U, X
            ".+C[^AHKLOQRUX].*",     // C except before A, H, K, L, O, Q, R, U, X
            ".*[DT][CSZ].*",         // D,T before C,S,Z
            ".*[CKQ]X.*",            // X after C,K,Q
            // @formatter:on
    };

    @AfterAll
    // Check that all possible input sequence conditions are represented
    public static void finishTests() {
        int errors = 0;
        for (final String m : MATCHES) {
            if (!hasTestCase(m)) {
                System.out.println(m + " has no test case");
                errors++;
            }
        }
        assertEquals(0, errors, "Not expecting any missing test cases");
    }

    private static boolean hasTestCase(final String re) {
        for (final String s : TESTSET) {
            if (s.matches(re)) {
                return true;
            }
        }
        return false;
    }

    // Allow command-line testing
    public static void main(final String args[]) {
        final ColognePhonetic coder = new ColognePhonetic();
        for (final String arg : args) {
            final String code = coder.encode(arg);
            System.out.println("'" + arg + "' = '" + code + "'");
        }
    }
......
            { "heithabu", "021" }
        };
        // @formatter:on
        this.checkEncodings(data);
    }

    @Test
    public void testHyphen() throws EncoderException {
        final String[][] data = { { "bergisch-gladbach", "174845214" }, { "M\u00fcller-L\u00fcdenscheidt", "65752682" } }; // Müller-Lüdenscheidt
        this.checkEncodings(data);
    }

    @Test
    public void testIsEncodeEquals() {
        //@formatter:off
        final String[][] data = {
            { "Muller", "M\u00fcller" }, // Müller
            { "Meyer", "Mayr" },
            { "house", "house" },
            { "House", "house" },
            { "Haus", "house" },
            { "ganz", "Gans" },
            { "ganz", "G\u00e4nse" }, // Gänse
            { "Miyagi", "Miyako" }
        };
        //@formatter:on
        for (final String[] element : data) {
            final boolean encodeEqual = this.getStringEncoder().isEncodeEqual(element[1], element[0]);
            assertTrue(encodeEqual, element[1] + " != " + element[0]);
        }
    }

    @Test
    public void testSpecialCharsBetweenSameLetters() throws EncoderException {
        final String[] data = { "Test test", "Testtest", "Test-test", "TesT#Test", "TesT?test" };
        this.checkEncodingVariations("28282", data);
    }

    @Test
    public void testVariationsMella() throws EncoderException {
        final String[] data = { "mella", "milah", "moulla", "mellah", "muehle", "mule" };
        this.checkEncodingVariations("65", data);
    }

    @Test
    public void testVariationsMeyer() throws EncoderException {
        final String[] data = { "Meier", "Maier", "Mair", "Meyer", "Meyr", "Mejer", "Major" };
        this.checkEncodingVariations("67", data);
    }
}
```

invoke examples of focal method: 
example 1:
```java
    public boolean isEncodeEqual(final String text1, final String text2) {
        return colognePhonetic(text1).equals(colognePhonetic(text2));
    }
```
example 2:
```java
    @Override
    public String encode(final String text) {
        return colognePhonetic(text);
    }
```



@input{test class template}
```java
package org.apache.commons.codec.language;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
// Add other imports if necessary

@ExtendWith(MockitoExtension.class)
class ColognePhonetic_colognePhonetic_Test {
    @BeforeAll
    static void setupBeforeAll() {
    }

    @BeforeEach
    void setupBeforeEach() {
    }

    @AfterEach
    void teardownAfterEach() {
    }

    @AfterAll
    static void teardownAfterAll() {
    }
}
```

@output{initial test class}: now consider the invoke examples output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.