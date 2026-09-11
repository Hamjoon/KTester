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
Here is the code of focal method, `metaphone(String)`, with the focal class `Metaphone_metaphone_Test`:
```Java
package org.apache.commons.codec.language;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
public class Metaphone implements StringEncoder {
    private static final String FRONTV = "EIY";
    private static final String VARSON = "CSPTG";
    public String metaphone(final String txt) {
        boolean hard = false;
        final int txtLength;
        if ((txt == null) || ((txtLength = txt.length()) == 0)) {
            return "";
        }
        // single character is itself
        if (txtLength == 1) {
            return txt.toUpperCase(Locale.ENGLISH);
        }
        final char[] inwd = txt.toUpperCase(Locale.ENGLISH).toCharArray();
        final StringBuilder local = new StringBuilder(40);// manipulate
    
        final StringBuilder code = new StringBuilder(10);// output
    
        // handle initial 2 characters exceptions
        switch (inwd[0]) {
            case 'K' :
            case 'G' :
            case 'P' :
                /* looking for KN, etc */
                if (inwd[1] == 'N') {
                    local.append(inwd, 1, inwd.length - 1);
                } else {
                    local.append(inwd);
                }
                break;
            case 'A' :
                /* looking for AE */
                if (inwd[1] == 'E') {
                    local.append(inwd, 1, inwd.length - 1);
                } else {
                    local.append(inwd);
                }
                break;
            case 'W' :
                /* looking for WR or WH */
                if (inwd[1] == 'R') {
                    // WR -> R
                    local.append(inwd, 1, inwd.length - 1);
                    break;
                }
                if (inwd[1] == 'H') {
                    local.append(inwd, 1, inwd.length - 1);
                    local.setCharAt(0, 'W');// WH -> W
    
                } else {
                    local.append(inwd);
                }
                break;
            case 'X' :
                /* initial X becomes S */
                inwd[0] = 'S';
                local.append(inwd);
                break;
            default :
                local.append(inwd);
        }// now local has working string with initials fixed
    
        final int wdsz = local.length();
        int n = 0;
        while ((code.length() < this.getMaxCodeLen()) && (n < wdsz)) {
            // max code size of 4 works well
            final char symb = local.charAt(n);
            // remove duplicate letters except C
            if ((symb != 'C') && isPreviousChar(local, n, symb)) {
                n++;
            }// end else from symb != 'C'
             else {
                // not dup
                switch (symb) {
                    case 'A' :
                    case 'E' :
                    case 'I' :
                    case 'O' :
                    case 'U' :
                        if (n == 0) {
                            code.append(symb);
                        }
                        break;// only use vowel if leading char
    
                    case 'B' :
                        if (isPreviousChar(local, n, 'M') && isLastChar(wdsz, n)) {
                            // B is silent if word ends in MB
                            break;
                        }
                        code.append(symb);
                        break;
                    case 'C' :
                        // lots of C special cases
                        /* discard if SCI, SCE or SCY */
                        if ((isPreviousChar(local, n, 'S') && (!isLastChar(wdsz, n))) && (FRONTV.indexOf(local.charAt(n + 1)) >= 0)) {
                            break;
                        }
                        if (regionMatch(local, n, "CIA")) {
                            // "CIA" -> X
                            code.append('X');
                            break;
                        }
                        if ((!isLastChar(wdsz, n)) && (FRONTV.indexOf(local.charAt(n + 1)) >= 0)) {
                            code.append('S');
                            break;// CI,CE,CY -> S
    
                        }
                        if (isPreviousChar(local, n, 'S') && isNextChar(local, n, 'H')) {
                            // SCH->sk
                            code.append('K');
                            break;
                        }
                        if (isNextChar(local, n, 'H')) {
                            // detect CH
                            if (((n == 0) && (wdsz >= 3)) && isVowel(local, 2)) {
                                // CH consonant -> K consonant
                                code.append('K');
                            } else {
                                code.append('X');// CHvowel -> X
    
                            }
                        } else {
                            code.append('K');
                        }
                        break;
                    case 'D' :
                        if (((!isLastChar(wdsz, n + 1)) && isNextChar(local, n, 'G')) && (FRONTV.indexOf(local.charAt(n + 2)) >= 0)) {
                            // DGE DGI DGY -> J
                            code.append('J');
                            n += 2;
                        } else {
                            code.append('T');
                        }
                        break;
                    case 'G' :
                        // GH silent at end or before consonant
                        if (isLastChar(wdsz, n + 1) && isNextChar(local, n, 'H')) {
                            break;
                        }
                        if (((!isLastChar(wdsz, n + 1)) && isNextChar(local, n, 'H')) && (!isVowel(local, n + 2))) {
                            break;
                        }
                        if ((n > 0) && (regionMatch(local, n, "GN") || regionMatch(local, n, "GNED"))) {
                            break;// silent G
    
                        }
                        // NOTE: Given that duplicated chars are removed, I don't see how this can ever be true
                        hard = isPreviousChar(local, n, 'G');
                        if (((!isLastChar(wdsz, n)) && (FRONTV.indexOf(local.charAt(n + 1)) >= 0)) && (!hard)) {
                            code.append('J');
                        } else {
                            code.append('K');
                        }
                        break;
                    case 'H' :
                        if (isLastChar(wdsz, n)) {
                            break;// terminal H
    
                        }
                        if ((n > 0) && (VARSON.indexOf(local.charAt(n - 1)) >= 0)) {
                            break;
                        }
                        if (isVowel(local, n + 1)) {
                            code.append('H');// Hvowel
    
                        }
                        break;
                    case 'F' :
                    case 'J' :
                    case 'L' :
                    case 'M' :
                    case 'N' :
                    case 'R' :
                        code.append(symb);
                        break;
                    case 'K' :
                        if (n > 0) {
                            // not initial
                            if (!isPreviousChar(local, n, 'C')) {
                                code.append(symb);
                            }
                        } else {
                            code.append(symb);// initial K
    
                        }
                        break;
                    case 'P' :
                        if (isNextChar(local, n, 'H')) {
                            // PH -> F
                            code.append('F');
                        } else {
                            code.append(symb);
                        }
                        break;
                    case 'Q' :
                        code.append('K');
                        break;
                    case 'S' :
                        if ((regionMatch(local, n, "SH") || regionMatch(local, n, "SIO")) || regionMatch(local, n, "SIA")) {
                            code.append('X');
                        } else {
                            code.append('S');
                        }
                        break;
                    case 'T' :
                        if (regionMatch(local, n, "TIA") || regionMatch(local, n, "TIO")) {
                            code.append('X');
                            break;
                        }
                        if (regionMatch(local, n, "TCH")) {
                            // Silent if in "TCH"
                            break;
                        }
                        // substitute numeral 0 for TH (resembles theta after all)
                        if (regionMatch(local, n, "TH")) {
                            code.append('0');
                        } else {
                            code.append('T');
                        }
                        break;
                    case 'V' :
                        code.append('F');
                        break;
                    case 'W' :
                    case 'Y' :
                        // silent if not followed by vowel
                        if ((!isLastChar(wdsz, n)) && isVowel(local, n + 1)) {
                            code.append(symb);
                        }
                        break;
                    case 'X' :
                        code.append('K');
                        code.append('S');
                        break;
                    case 'Z' :
                        code.append('S');
                        break;
                    default :
                        // do nothing
                        break;
                }// end switch
    
                n++;
            }// end else from symb != 'C'
    
            if (code.length() > this.getMaxCodeLen()) {
                code.setLength(this.getMaxCodeLen());
            }
        } 
        return code.toString();
    }
@Override
public Object encode(final Object obj) throws EncoderException;
    @Override
public String encode(final String str);
    public int getMaxCodeLen();
    private boolean isLastChar(final int wdsz, final int n);
    public boolean isMetaphoneEqual(final String str1, final String str2);
    private boolean isNextChar(final StringBuilder string, final int index, final char c);
    private boolean isPreviousChar(final StringBuilder string, final int index, final char c);
    private boolean isVowel(final StringBuilder string, final int index);
    private boolean regionMatch(final StringBuilder string, final int index, final String test);
    public void setMaxCodeLen(final int maxCodeLen);
}
```


@input{context information}

api document of class org.apache.commons.codec.language.Metaphone: 

 * Encodes a string into a Metaphone value.
 * <p>
 * Initial Java implementation by <CITE>William B. Brogden. December, 1997</CITE>.
 * Permission given by <CITE>wbrogden</CITE> for code to be used anywhere.
 * </p>
 * <p>
 * <CITE>Hanging on the Metaphone</CITE> by <CITE>Lawrence Philips</CITE> in <CITE>Computer Language of Dec. 1990,
 * p 39.</CITE>
 * </p>
 * <p>
 * Note, that this does not match the algorithm that ships with PHP, or the algorithm found in the Perl implementations:
 * </p>
 * <ul>
 * <li><a href="https://search.cpan.org/~mschwern/Text-Metaphone-1.96/Metaphone.pm">Text:Metaphone-1.96</a>
 *  (broken link 4/30/2013) </li>
 * <li><a href="https://metacpan.org/source/MSCHWERN/Text-Metaphone-1.96//Metaphone.pm">Text:Metaphone-1.96</a>
 *  (link checked 4/30/2013) </li>
 * </ul>
 * <p>
 * They have had undocumented changes from the originally published algorithm.
 * For more information, see <a href="https://issues.apache.org/jira/browse/CODEC-57">CODEC-57</a>.
 * </p>
 * <p>
 * This class is conditionally thread-safe.
 * The instance field for maximum code length is mutable {@link #setMaxCodeLen(int)}
 * but is not volatile, and accesses are not synchronized.
 * If an instance of the class is shared between threads, the caller needs to ensure that suitable synchronization
 * is used to ensure safe publication of the value between threads, and must not invoke {@link #setMaxCodeLen(int)}
 * after initial setup.
 * </p>
 

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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.apache.commons.codec.AbstractStringEncoderTest;
import org.junit.jupiter.api.Test;

/**
 */
public class MetaphoneTest extends AbstractStringEncoderTest<Metaphone> {

    public void assertIsMetaphoneEqual(final String source, final String[] matches) {
        // match source to all matches
        for (final String matche : matches) {
            assertTrue(this.getStringEncoder().isMetaphoneEqual(source, matche), "Source: " + source + ", should have same Metaphone as: " + matche);
        }
        // match to each other
        for (final String matche : matches) {
            for (final String matche2 : matches) {
                assertTrue(this.getStringEncoder().isMetaphoneEqual(matche, matche2));
            }
        }
    }

    public void assertMetaphoneEqual(final String[][] pairs) {
        this.validateFixture(pairs);
        for (final String[] pair : pairs) {
            final String name0 = pair[0];
            final String name1 = pair[1];
            final String failMsg = "Expected match between " + name0 + " and " + name1;
            assertTrue(this.getStringEncoder().isMetaphoneEqual(name0, name1), failMsg);
            assertTrue(this.getStringEncoder().isMetaphoneEqual(name1, name0), failMsg);
        }
    }

    @Override
    protected Metaphone createStringEncoder() {
        return new Metaphone();
    }

    @Test
    public void testDiscardOfSCEOrSCIOrSCY() {
        assertEquals("SNS", this.getStringEncoder().metaphone("SCIENCE"));
        assertEquals("SN", this.getStringEncoder().metaphone("SCENE"));
        assertEquals("S", this.getStringEncoder().metaphone("SCY"));
    }

    @Test
    public void testDiscardOfSilentGN() {
        // NOTE: This does not test for silent GN, but for starting with GN
        assertEquals("N", this.getStringEncoder().metaphone("GNU"));

        // NOTE: Trying to test for GNED, but expected code does not appear to execute
        assertEquals("SNT", this.getStringEncoder().metaphone("SIGNED"));
    }

    @Test
    public void testDiscardOfSilentHAfterG() {
        assertEquals("KNT", this.getStringEncoder().metaphone("GHENT"));
        assertEquals("B", this.getStringEncoder().metaphone("BAUGH"));
    }

    @Test
    public void testExceedLength() {
        // should be AKSKS, but is truncated by Max Code Length
        assertEquals("AKSK", this.getStringEncoder().metaphone("AXEAXE"));
    }

    @Test
    public void testIsMetaphoneEqual1() {
        this.assertMetaphoneEqual(new String[][] { { "Case", "case" }, { "CASE", "Case" }, { "caSe", "cAsE" }, { "quick", "cookie" } });
    }

    /**
     * Matches computed from http://www.lanw.com/java/phonetic/default.htm
     */
    @Test
    public void testIsMetaphoneEqual2() {
        this.assertMetaphoneEqual(new String[][] { { "Lawrence", "Lorenza" }, { "Gary", "Cahra" }, });
    }

......

    @Test
    public void testTranslateOfSCHAndCH() {
        assertEquals("SKTL", this.getStringEncoder().metaphone("SCHEDULE"));
        assertEquals("SKMT", this.getStringEncoder().metaphone("SCHEMATIC"));

        assertEquals("KRKT", this.getStringEncoder().metaphone("CHARACTER"));
        assertEquals("TX", this.getStringEncoder().metaphone("TEACH"));
    }

    @Test
    public void testTranslateToJOfDGEOrDGIOrDGY() {
        assertEquals("TJ", this.getStringEncoder().metaphone("DODGY"));
        assertEquals("TJ", this.getStringEncoder().metaphone("DODGE"));
        assertEquals("AJMT", this.getStringEncoder().metaphone("ADGIEMTI"));
    }

    /**
     * Tests (CODEC-57) Metaphone.metaphone(String) returns an empty string when passed the word "why"
     */
    @Test
    public void testWhy() {
        // PHP returns "H". The original metaphone returns an empty string.
        assertEquals("", this.getStringEncoder().metaphone("WHY"));
    }

    @Test
    public void testWordEndingInMB() {
        assertEquals("KM", this.getStringEncoder().metaphone("COMB"));
        assertEquals("TM", this.getStringEncoder().metaphone("TOMB"));
        assertEquals("WM", this.getStringEncoder().metaphone("WOMB"));
    }

    @Test
    public void testWordsWithCIA() {
        assertEquals("XP", this.getStringEncoder().metaphone("CIAPO"));
    }

    public void validateFixture(final String[][] pairs) {
        if (pairs.length == 0) {
            fail("Test fixture is empty");
        }
        for (int i = 0; i < pairs.length; i++) {
            if (pairs[i].length != 2) {
                fail("Error in test fixture in the data array at index " + i);
            }
        }
    }

}
```

invoke examples of focal method: 
example 1:
```java
    public boolean isMetaphoneEqual(final String str1, final String str2) {
        return metaphone(str1).equals(metaphone(str2));
    }
```
example 2:
```java
    @Override
    public String encode(final String str) {
        return metaphone(str);
    }
```
example 3:
```java
    @Override
    public Object encode(final Object obj) throws EncoderException {
        if (!(obj instanceof String)) {
        return metaphone((String) obj);
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
class Metaphone_metaphone_Test {
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