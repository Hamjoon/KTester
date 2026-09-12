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
Here is the code of focal method, `nysiis(String)`, with the focal class `Nysiis_nysiis_Test`:
```Java
package org.apache.commons.codec.language;
import java.util.regex.Pattern;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
public class Nysiis implements StringEncoder {
    private static final Pattern PAT_MAC = Pattern.compile("^MAC");
    private static final Pattern PAT_KN = Pattern.compile("^KN");
    private static final Pattern PAT_K = Pattern.compile("^K");
    private static final Pattern PAT_PH_PF = Pattern.compile("^(PH|PF)");
    private static final Pattern PAT_SCH = Pattern.compile("^SCH");
    private static final Pattern PAT_EE_IE = Pattern.compile("(EE|IE)$");
    private static final Pattern PAT_DT_ETC = Pattern.compile("(DT|RT|RD|NT|ND)$");
    private static final char SPACE = ' ';
    private static final int TRUE_LENGTH = 6;
    public String nysiis(String str) {
        if (str == null) {
            return null;
        }
        // Use the same clean rules as Soundex
        str = SoundexUtils.clean(str);
        if (str.isEmpty()) {
            return str;
        }
        // Translate first characters of name:
        // MAC -> MCC, KN -> NN, K -> C, PH | PF -> FF, SCH -> SSS
        str = PAT_MAC.matcher(str).replaceFirst("MCC");
        str = PAT_KN.matcher(str).replaceFirst("NN");
        str = PAT_K.matcher(str).replaceFirst("C");
        str = PAT_PH_PF.matcher(str).replaceFirst("FF");
        str = PAT_SCH.matcher(str).replaceFirst("SSS");
        // Translate last characters of name:
        // EE -> Y, IE -> Y, DT | RT | RD | NT | ND -> D
        str = PAT_EE_IE.matcher(str).replaceFirst("Y");
        str = PAT_DT_ETC.matcher(str).replaceFirst("D");
        // First character of key = first character of name.
        final StringBuilder key = new StringBuilder(str.length());
        key.append(str.charAt(0));
        // Transcode remaining characters, incrementing by one character each time
        final char[] chars = str.toCharArray();
        final int len = chars.length;
        for (int i = 1; i < len; i++) {
            final char next = (i < (len - 1)) ? chars[i + 1] : SPACE;
            final char aNext = (i < (len - 2)) ? chars[i + 2] : SPACE;
            final char[] transcoded = transcodeRemaining(chars[i - 1], chars[i], next, aNext);
            System.arraycopy(transcoded, 0, chars, i, transcoded.length);
            // only append the current char to the key if it is different from the last one
            if (chars[i] != chars[i - 1]) {
                key.append(chars[i]);
            }
        }
        if (key.length() > 1) {
            char lastChar = key.charAt(key.length() - 1);
            // If last character is S, remove it.
            if (lastChar == 'S') {
                key.deleteCharAt(key.length() - 1);
                lastChar = key.charAt(key.length() - 1);
            }
            if (key.length() > 2) {
                final char last2Char = key.charAt(key.length() - 2);
                // If last characters are AY, replace with Y.
                if ((last2Char == 'A') && (lastChar == 'Y')) {
                    key.deleteCharAt(key.length() - 2);
                }
            }
            // If last character is A, remove it.
            if (lastChar == 'A') {
                key.deleteCharAt(key.length() - 1);
            }
        }
        final String string = key.toString();
        return this.isStrict() ? string.substring(0, Math.min(TRUE_LENGTH, string.length())) : string;
    }
@Override
public Object encode(final Object obj) throws EncoderException;
    @Override
public String encode(final String str);
    public boolean isStrict();
    private static char[] transcodeRemaining(final char prev, final char curr, final char next, final char aNext);
}
```


@input{context information}

api document of class org.apache.commons.codec.language.Nysiis: 

 * Encodes a string into a NYSIIS value. NYSIIS is an encoding used to relate similar names, but can also be used as a
 * general purpose scheme to find word with similar phonemes.
 * <p>
 * NYSIIS features an accuracy increase of 2.7% over the traditional Soundex algorithm.
 * </p>
 * <p>
 * Algorithm description:
 * </p>
 * <pre>
 * 1. Transcode first characters of name
 *   1a. MAC -&gt;   MCC
 *   1b. KN  -&gt;   NN
 *   1c. K   -&gt;   C
 *   1d. PH  -&gt;   FF
 *   1e. PF  -&gt;   FF
 *   1f. SCH -&gt;   SSS
 * 2. Transcode last characters of name
 *   2a. EE, IE          -&gt;   Y
 *   2b. DT,RT,RD,NT,ND  -&gt;   D
 * 3. First character of key = first character of name
 * 4. Transcode remaining characters by following these rules, incrementing by one character each time
 *   4a. EV  -&gt;   AF  else A,E,I,O,U -&gt; A
 *   4b. Q   -&gt;   G
 *   4c. Z   -&gt;   S
 *   4d. M   -&gt;   N
 *   4e. KN  -&gt;   N   else K -&gt; C
 *   4f. SCH -&gt;   SSS
 *   4g. PH  -&gt;   FF
 *   4h. H   -&gt;   If previous or next is non-vowel, previous
 *   4i. W   -&gt;   If previous is vowel, previous
 *   4j. Add current to key if current != last key character
 * 5. If last character is S, remove it
 * 6. If last characters are AY, replace with Y
 * 7. If last character is A, remove it
 * 8. Collapse all strings of repeated characters
 * 9. Add original first character of name as first character of key
 * </pre>
 * <p>
 * This class is immutable and thread-safe.
 * </p>
 *
 * @see <a href="https://en.wikipedia.org/wiki/NYSIIS">NYSIIS on Wikipedia</a>
 * @see <a href="http://www.dropby.com/NYSIIS.html">NYSIIS on dropby.com</a>
 * @see Soundex
 * @since 1.7
 

constructors for class `org.apache.commons.codec.language.Nysiis`: 
params: 
body:
```java
    public Nysiis() {
        this(true);
    }

```
params: 
body:
```java
    public Nysiis(final boolean strict) {
        this.strict = strict;
    }

```

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

import org.apache.commons.codec.AbstractStringEncoderTest;
import org.junit.jupiter.api.Test;

/**
 * Tests {@link Nysiis}
 */
public class NysiisTest extends AbstractStringEncoderTest<Nysiis> {

    private final Nysiis fullNysiis = new Nysiis(false);

    /**
     * Takes an array of String pairs where each pair's first element is the input and the second element the expected
     * encoding.
     *
     * @param testValues
     *            an array of String pairs where each pair's first element is the input and the second element the
     *            expected encoding.
     */
    private void assertEncodings(final String[]... testValues) {
        for (final String[] arr : testValues) {
            assertEquals(arr[1], this.fullNysiis.encode(arr[0]), "Problem with " + arr[0]);
        }
    }

    @Override
    protected Nysiis createStringEncoder() {
        return new Nysiis();
    }

    private void encodeAll(final String[] strings, final String expectedEncoding) {
        for (final String string : strings) {
            assertEquals(expectedEncoding, getStringEncoder().encode(string), "Problem with " + string);
        }
    }

    @Test
    public void testBran() {
        encodeAll(new String[] { "Brian", "Brown", "Brun" }, "BRAN");
    }

    @Test
    public void testCap() {
        this.encodeAll(new String[] { "Capp", "Cope", "Copp", "Kipp" }, "CAP");
    }

    @Test
    public void testDad() {
        // Data Quality and Record Linkage Techniques P.121 claims this is DAN,
        // but it should be DAD, verified also with dropby.com
        this.encodeAll(new String[] { "Dent" }, "DAD");
    }

    @Test
    public void testDan() {
        this.encodeAll(new String[] { "Dane", "Dean", "Dionne" }, "DAN");
    }

    /**
     * Tests data gathered from around the internet.
     *
     * @see <a href="http://www.dropby.com/NYSIISTextStrings.html">http://www.dropby.com/NYSIISTextStrings.html</a>*/
    @Test
    public void testDropBy() {
        // Explanation of differences between this implementation and the one at dropby.com is
        // prepended to the test string. The referenced rules refer to the outlined steps the
        // class description for Nysiis.

        this.assertEncodings(
                // 1. Transcode first characters of name
                new String[] { "MACINTOSH", "MCANT" },
                // violates 4j: the second N should not be added, as the first
                //              key char is already a N
                new String[] { "KNUTH", "NAT" },           // Original: NNAT; modified: NATH
                // O and E are transcoded to A because of rule 4a
                // H also to A because of rule 4h
                // the N gets mysteriously lost, maybe because of a wrongly implemented rule 4h
                // that skips the next char in such a case?
                // the remaining A is removed because of rule 7
......
                new String[] { "XAY", "XY" },
                new String[] { "XAYS", "XY" }); // Rules 5, 6
    }

    /**
     * Tests rule 7: If last character is A, remove it.*/
    @Test
    public void testRule7() {
        this.assertEncodings(
                new String[] { "XA", "X" },
                new String[] { "XAS", "X" }); // Rules 5, 7
    }
    @Test
    public void testSnad() {
        // Data Quality and Record Linkage Techniques P.121 claims this is SNAT,
        // but it should be SNAD
        this.encodeAll(new String[] { "Schmidt" }, "SNAD");
    }

    @Test
    public void testSnat() {
        this.encodeAll(new String[] { "Smith", "Schmit" }, "SNAT");
    }

    @Test
    public void testSpecialBranches() {
        this.encodeAll(new String[] { "Kobwick" }, "CABWAC");
        this.encodeAll(new String[] { "Kocher" }, "CACAR");
        this.encodeAll(new String[] { "Fesca" }, "FASC");
        this.encodeAll(new String[] { "Shom" }, "SAN");
        this.encodeAll(new String[] { "Ohlo" }, "OL");
        this.encodeAll(new String[] { "Uhu" }, "UH");
        this.encodeAll(new String[] { "Um" }, "UN");
    }

    @Test
    public void testTranan() {
        this.encodeAll(new String[] { "Trueman", "Truman" }, "TRANAN");
    }

    @Test
    public void testTrueVariant() {
        final Nysiis encoder = new Nysiis(true);

        final String encoded = encoder.encode("WESTERLUND");
        assertTrue(encoded.length() <= 6);
        assertEquals("WASTAR", encoded);
    }

}
```

invoke examples of focal method: 
example 1:
```java
    @Override
    public String encode(final String str) {
        return this.nysiis(str);
    }
```
example 2:
```java
    @Override
    public Object encode(final Object obj) throws EncoderException {
        if (!(obj instanceof String)) {
        return this.nysiis((String) obj);
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
class Nysiis_nysiis_Test {
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