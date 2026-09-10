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
Here is the code of focal method, `doubleMetaphone(String, boolean)`, with the focal class `DoubleMetaphone_doubleMetaphone_Test`:
```Java
package org.apache.commons.codec.language;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
import org.apache.commons.codec.binary.StringUtils;
public class DoubleMetaphone implements StringEncoder {
    
        /**
     * Inner class for storing results, since there is the optional alternate encoding.
     */
    public class DoubleMetaphoneResult {
        private final StringBuilder primary = new StringBuilder(getMaxCodeLen());
    
        private final StringBuilder alternate = new StringBuilder(getMaxCodeLen());
    
        private final int maxLength;
    
        public DoubleMetaphoneResult(final int maxLength) {
            this.maxLength = maxLength;
        }
    
        public void append(final char value) {
            appendPrimary(value);
            appendAlternate(value);
        }
    
        public void append(final char primary, final char alternate) {
            appendPrimary(primary);
            appendAlternate(alternate);
        }
    
        public void append(final String value) {
            appendPrimary(value);
            appendAlternate(value);
        }
    
        public void append(final String primary, final String alternate) {
            appendPrimary(primary);
            appendAlternate(alternate);
        }
    
        public void appendAlternate(final char value) {
            if (this.alternate.length() < this.maxLength) {
                this.alternate.append(value);
            }
        }
    
        public void appendAlternate(final String value) {
            final int addChars = this.maxLength - this.alternate.length();
            if (value.length() <= addChars) {
                this.alternate.append(value);
            } else {
                this.alternate.append(value, 0, addChars);
            }
        }
    
        public void appendPrimary(final char value) {
            if (this.primary.length() < this.maxLength) {
                this.primary.append(value);
            }
        }
    
        public void appendPrimary(final String value) {
            final int addChars = this.maxLength - this.primary.length();
            if (value.length() <= addChars) {
                this.primary.append(value);
            } else {
                this.primary.append(value, 0, addChars);
            }
        }
    
        public String getAlternate() {
            return this.alternate.toString();
        }
    
        public String getPrimary() {
            return this.primary.toString();
        }
    
        public boolean isComplete() {
            return (this.primary.length() >= this.maxLength) && (this.alternate.length() >= this.maxLength);
        }
    }
public String doubleMetaphone(String value, final boolean alternate) {
        value = cleanInput(value);
        if (value == null) {
            return null;
        }
        final boolean slavoGermanic = isSlavoGermanic(value);
        int index = (isSilentStart(value)) ? 1 : 0;
        final DoubleMetaphoneResult result = new DoubleMetaphoneResult(this.getMaxCodeLen());
        while ((!result.isComplete()) && (index <= (value.length() - 1))) {
            switch (value.charAt(index)) {
                case 'A' :
                case 'E' :
                case 'I' :
                case 'O' :
                case 'U' :
                case 'Y' :
                    index = handleAEIOUY(result, index);
                    break;
                case 'B' :
                    result.append('P');
                    index = (charAt(value, index + 1) == 'B') ? index + 2 : index + 1;
                    break;
                case '?' :
                    // A C with a Cedilla
                    result.append('S');
                    index++;
                    break;
                case 'C' :
                    index = handleC(value, result, index);
                    break;
                case 'D' :
                    index = handleD(value, result, index);
                    break;
                case 'F' :
                    result.append('F');
                    index = (charAt(value, index + 1) == 'F') ? index + 2 : index + 1;
                    break;
                case 'G' :
                    index = handleG(value, result, index, slavoGermanic);
                    break;
                case 'H' :
                    index = handleH(value, result, index);
                    break;
                case 'J' :
                    index = handleJ(value, result, index, slavoGermanic);
                    break;
                case 'K' :
                    result.append('K');
                    index = (charAt(value, index + 1) == 'K') ? index + 2 : index + 1;
                    break;
                case 'L' :
                    index = handleL(value, result, index);
                    break;
                case 'M' :
                    result.append('M');
                    index = (conditionM0(value, index)) ? index + 2 : index + 1;
                    break;
                case 'N' :
                    result.append('N');
                    index = (charAt(value, index + 1) == 'N') ? index + 2 : index + 1;
                    break;
                case '?' :
                    // N with a tilde (spanish ene)
                    result.append('N');
                    index++;
                    break;
                case 'P' :
                    index = handleP(value, result, index);
                    break;
                case 'Q' :
                    result.append('K');
                    index = (charAt(value, index + 1) == 'Q') ? index + 2 : index + 1;
                    break;
                case 'R' :
                    index = handleR(value, result, index, slavoGermanic);
                    break;
                case 'S' :
                    index = handleS(value, result, index, slavoGermanic);
                    break;
                case 'T' :
                    index = handleT(value, result, index);
                    break;
                case 'V' :
                    result.append('F');
                    index = (charAt(value, index + 1) == 'V') ? index + 2 : index + 1;
                    break;
                case 'W' :
                    index = handleW(value, result, index);
                    break;
                case 'X' :
                    index = handleX(value, result, index);
                    break;
                case 'Z' :
                    index = handleZ(value, result, index, slavoGermanic);
                    break;
                default :
                    index++;
                    break;
            }
        } 
        return alternate ? result.getAlternate() : result.getPrimary();
    }
protected char charAt(final String value, final int index);
    private String cleanInput(String input);
    private boolean conditionM0(final String value, final int index);
    protected static boolean contains(final String value, final int start, final int length, final String... criteria);
    public String doubleMetaphone(final String value);
    @Override
public Object encode(final Object obj) throws EncoderException;
    @Override
public String encode(final String value);
    public int getMaxCodeLen();
    private int handleAEIOUY(final DoubleMetaphoneResult result, final int index);
    private int handleC(final String value, final DoubleMetaphoneResult result, int index);
    private int handleD(final String value, final DoubleMetaphoneResult result, int index);
    private int handleG(final String value, final DoubleMetaphoneResult result, int index, final boolean slavoGermanic);
    private int handleH(final String value, final DoubleMetaphoneResult result, int index);
    private int handleJ(final String value, final DoubleMetaphoneResult result, int index, final boolean slavoGermanic);
    private int handleL(final String value, final DoubleMetaphoneResult result, int index);
    private int handleP(final String value, final DoubleMetaphoneResult result, int index);
    private int handleR(final String value, final DoubleMetaphoneResult result, final int index, final boolean slavoGermanic);
    private int handleS(final String value, final DoubleMetaphoneResult result, int index, final boolean slavoGermanic);
    private int handleT(final String value, final DoubleMetaphoneResult result, int index);
    private int handleW(final String value, final DoubleMetaphoneResult result, int index);
    private int handleX(final String value, final DoubleMetaphoneResult result, int index);
    private int handleZ(final String value, final DoubleMetaphoneResult result, int index, final boolean slavoGermanic);
    public boolean isDoubleMetaphoneEqual(final String value1, final String value2);
    public boolean isDoubleMetaphoneEqual(final String value1, final String value2, final boolean alternate);
    private boolean isSilentStart(final String value);
    private boolean isSlavoGermanic(final String value);
    public void setMaxCodeLen(final int maxCodeLen);
}
```


@input{context information}

api document of class org.apache.commons.codec.language.DoubleMetaphone: 

 * Encodes a string into a double metaphone value. This Implementation is based on the algorithm by <CITE>Lawrence
 * Philips</CITE>.
 * <p>
 * This class is conditionally thread-safe. The instance field for the maximum code length is mutable
 * {@link #setMaxCodeLen(int)} but is not volatile, and accesses are not synchronized. If an instance of the class is
 * shared between threads, the caller needs to ensure that suitable synchronization is used to ensure safe publication
 * of the value between threads, and must not invoke {@link #setMaxCodeLen(int)} after initial setup.
 * </p>
 *
 * @see <a href="https://drdobbs.com/the-double-metaphone-search-algorithm/184401251?pgno=2">Dr. Dobbs Original Article</a>
 * @see <a href="https://en.wikipedia.org/wiki/Metaphone">Wikipedia Metaphone</a>
 

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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.apache.commons.codec.AbstractStringEncoderTest;
import org.apache.commons.codec.EncoderException;
import org.junit.jupiter.api.Test;

/**
 * Tests {@link DoubleMetaphone}.
 *
 * <p>Keep this file in UTF-8 encoding for proper Javadoc processing.</p>
 *
 * @see "http://www.cuj.com/documents/s=8038/cuj0006philips/"
 */
public class DoubleMetaphoneTest extends AbstractStringEncoderTest<DoubleMetaphone> {

    /**
     * Test data from https://aspell.net/test/orig/batch0.tab.
     *
     * "Copyright (C) 2002 Kevin Atkinson (kevina@gnu.org). Verbatim copying
     * and distribution of this entire article is permitted in any medium,
     * provided this notice is preserved."
     *
     * Massaged the test data in the array below.
     */
    private static final String[][] FIXTURE = { { "Accosinly", "Occasionally" }, {
            "Ciculer", "Circler" }, {
            "Circue", "Circle" }, {
            "Maddness", "Madness" }, {
            "Occusionaly", "Occasionally" }, {
            "Steffen", "Stephen" }, {
            "Thw", "The" }, {
            "Unformanlly", "Unfortunately" }, {
            "Unfortally", "Unfortunately" }, {
            "abilitey", "ability" }, {
            "abouy", "about" }, {
            "absorbtion", "absorption" }, {
            "accidently", "accidentally" }, {
            "accomodate", "accommodate" }, {
            "acommadate", "accommodate" }, {
            "acord", "accord" }, {
            "adultry", "adultery" }, {
            "aggresive", "aggressive" }, {
            "alchohol", "alcohol" }, {
            "alchoholic", "alcoholic" }, {
            "allieve", "alive" }, {
            "alot", "a lot" }, {
            "alright", "all right" }, {
            "amature", "amateur" }, {
            "ambivilant", "ambivalent" }, {
            "amification", "amplification" }, {
            "amourfous", "amorphous" }, {
            "annoint", "anoint" }, {
            "annonsment", "announcement" }, {
            "annoyting", "anting" }, {
            "annuncio", "announce" }, {
            "anonomy", "anatomy" }, {
            "anotomy", "anatomy" }, {
            "antidesestablishmentarianism", "antidisestablishmentarianism" }, {
            "antidisestablishmentarism", "antidisestablishmentarianism" }, {
            "anynomous", "anonymous" }, {
            "appelet", "applet" }, {
            "appreceiated", "appreciated" }, {
            "appresteate", "appreciate" }, {
            "aquantance", "acquaintance" }, {
            "aratictature", "architecture" }, {
            "archeype", "archetype" }, {
            "aricticure", "architecture" }, {
            "artic", "arctic" }, {
            "asentote", "asymptote" }, {
            "ast", "at" }, {
            "asterick", "asterisk" }, {
            "asymetric", "asymmetric" }, {
            "atentively", "attentively" }, {
            "autoamlly", "automatically" }, {
            "bankrot", "bankrupt" }, {
            "basicly", "basically" }, {
            "batallion", "battalion" }, {
......
            final boolean match2 = this.getStringEncoder().isDoubleMetaphoneEqual(name0, name1, true);
            if (!match1 && !match2) {
                fail("Expected match [" + i + "] " + name0 + " and " + name1);
            }
        }
    }

    @Test
    public void testIsDoubleMetaphoneNotEqual() {
        doubleMetaphoneNotEqualTest(false);
        doubleMetaphoneNotEqualTest(true);
    }

    @Test
    public void testNTilde() {
        assertTrue(this.getStringEncoder().isDoubleMetaphoneEqual("\u00f1", "N")); // n-tilde
    }

    /**
     * Test setting maximum length
     */
    @Test
    public void testSetMaxCodeLength() {
        final String value = "jumped";

        final DoubleMetaphone doubleMetaphone = new DoubleMetaphone();

        // Sanity check of default settings
        assertEquals(4, doubleMetaphone.getMaxCodeLen(), "Default Max Code Length");
        assertEquals("JMPT", doubleMetaphone.doubleMetaphone(value, false), "Default Primary");
        assertEquals("AMPT", doubleMetaphone.doubleMetaphone(value, true), "Default Alternate");

        // Check setting Max Code Length
        doubleMetaphone.setMaxCodeLen(3);
        assertEquals(3, doubleMetaphone.getMaxCodeLen(), "Set Max Code Length");
        assertEquals("JMP", doubleMetaphone.doubleMetaphone(value, false), "Max=3 Primary");
        assertEquals("AMP", doubleMetaphone.doubleMetaphone(value, true), "Max=3 Alternate");
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
    public String doubleMetaphone(final String value) {
        return doubleMetaphone(value, false);
    }
```
example 2:
```java
    public boolean isDoubleMetaphoneEqual(final String value1, final String value2, final boolean alternate) {
        return StringUtils.equals(doubleMetaphone(value1, alternate), doubleMetaphone(value2, alternate));
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
class DoubleMetaphone_doubleMetaphone_Test {
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