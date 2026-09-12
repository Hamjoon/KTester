You are a professional Java software testing expert. Your task is to generate high-quality unit test code for a focal method, using **JUnit5** and **Mockito** libraries. 

You'll be provided with:
1. The focal method to test.
2. The target class's structure (fields and methods).
3. A pre-defined test class framework with `@Before`/`@After` methods.
4. Test cases in JSON format.
5. Any relevant context.

**Requirements:**

Code Quality:
- Include clear comments explaining each test scenario.
- Ensure code is clean, understandable, and maintainable.
- Do not directly access private methods or fields. **Use reflection if the focal method is private.**
- Include all necessary imports; code must be free of syntax errors.

**Test Structure:**
- Follow the given test class framework; do not modify `@Before`/`@After` methods.
- Create one test function per test case group, ensuring all cases are covered.
- Use parameterized tests where appropriate.
- Reinitialize variables inside test functions if `@Setup` values don't fit.
- Properly mock and set up dependencies.

@input{focal method and target class}
Here is the code of focal method, `nysiis(String)`, with the focal class `Nysiis`:
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

@input{initial test class framework}
```Java
<initial_class>
```

@input{existing test case}
here's the existing test cases:
```json
<cases_json>
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
 

api document of method nysiis(String): 

     * Retrieves the NYSIIS code for a given String object.
     *
     * @param str
     *            String to encode using the NYSIIS algorithm
     * @return A NYSIIS code for the String supplied
     

dependent classes: 
class `org.apache.commons.codec.language.Nysiis`:
	dependent fields:
		char org.apache.commons.codec.language.Nysiis.SPACE;
		java.util.regex.Pattern org.apache.commons.codec.language.Nysiis.PAT_PH_PF;
		java.util.regex.Pattern org.apache.commons.codec.language.Nysiis.PAT_EE_IE;
		java.util.regex.Pattern org.apache.commons.codec.language.Nysiis.PAT_SCH;
		int org.apache.commons.codec.language.Nysiis.TRUE_LENGTH;
		java.util.regex.Pattern org.apache.commons.codec.language.Nysiis.PAT_DT_ETC;
		java.util.regex.Pattern org.apache.commons.codec.language.Nysiis.PAT_K;
		java.util.regex.Pattern org.apache.commons.codec.language.Nysiis.PAT_KN;
		java.util.regex.Pattern org.apache.commons.codec.language.Nysiis.PAT_MAC;
	
class `org.apache.commons.codec.language.RefinedSoundex`:
	related functions:
		method `public String soundex(String)` returns `java.lang.String`, related with `org.apache.commons.codec.language.Nysiis.nysiis(String)`, api document: 
     * Retrieves the Refined Soundex code for a given String object.
     *
     * @param str
     *                  String to encode using the Refined Soundex algorithm
     * @return A soundex code for the String supplied
     ;
class `org.apache.commons.codec.language.Metaphone`:
	related functions:
		method `public String metaphone(String)` returns `java.lang.String`, related with `org.apache.commons.codec.language.Nysiis.nysiis(String)`, api document: 
     * Find the metaphone value of a String. This is similar to the
     * soundex algorithm, but better at finding similar sounding words.
     * All input is converted to upper case.
     * Limitations: Input format is expected to be a single ASCII word
     * with only characters in the A - Z range, no punctuation or numbers.
     *
     * @param txt String to find the metaphone code for
     * @return A metaphone code corresponding to the String supplied
     ;
class `org.apache.commons.codec.digest.B64`:
	related functions:
		method ` static String getRandomSalt(int, Random)` returns `java.lang.String`, related with `org.apache.commons.codec.language.Nysiis.nysiis(String)`, api document: 
     * Generates a string of random chars from the B64T set.
     * <p>
     * The salt is generated with the {@link Random} provided.
     * </p>
     *
     * @param num Number of chars to generate.
     * @param random an instance of {@link Random}.
     * @return a random salt {@link String}.
     ;
class `org.apache.commons.codec.language.Soundex`:
	related functions:
		method `public String soundex(String)` returns `java.lang.String`, related with `org.apache.commons.codec.language.Nysiis.nysiis(String)`, api document: 
     * Retrieves the Soundex code for a given String object.
     *
     * @param str
     *                  String to encode using the Soundex algorithm
     * @return A soundex code for the String supplied
     * @throws IllegalArgumentException
     *                  if a character is not mapped
     ;
class `org.apache.commons.codec.language.MatchRatingApproachEncoder`:
	related functions:
		method ` int leftToRightThenRightToLeftProcessing(String, String)` returns `int`, related with `org.apache.commons.codec.language.Nysiis.nysiis(String)`, api document: 
     * Processes the names from left to right (first) then right to left removing identical letters in same positions.
     * Then subtracts the longer string that remains from 6 and returns this.
     *
     * <h2>API Usage</h2>
     * <p>
     * Consider this method private, it is package protected for unit testing only.
     * </p>
     *
     * @param name1
     *            name2
     * @return the length as above
     ;
		method ` String removeAccents(String)` returns `java.lang.String`, related with `org.apache.commons.codec.language.Nysiis.nysiis(String)`, api document: 
     * Removes accented letters and replaces with non-accented ASCII equivalent Case is preserved.
     * http://www.codecodex.com/wiki/Remove_accent_from_letters_%28ex_.%C3%A9_to_e%29
     *
     * @param accentedWord
     *            The word that may have accents in it.
     * @return De-accented word
     ;
class `org.apache.commons.codec.language.SoundexUtils`:
	related functions:
		method ` static int differenceEncoded(String, String)` returns `int`, related with `org.apache.commons.codec.language.Nysiis.nysiis(String)`, api document: 
     * Returns the number of characters in the two Soundex encoded Strings that
     * are the same.
     * <ul>
     * <li>For Soundex, this return value ranges from 0 through 4: 0 indicates
     * little or no similarity, and 4 indicates strong similarity or identical
     * values.</li>
     * <li>For refined Soundex, the return value can be greater than 4.</li>
     * </ul>
     *
     * @param es1
     *                  An encoded String.
     * @param es2
     *                  An encoded String.
     * @return The number of characters in the two Soundex encoded Strings that
     *             are the same.
     *
     * @see <a href="https://msdn.microsoft.com/library/default.asp?url=/library/en-us/tsqlref/ts_de-dz_8co5.asp">
     *          MS T-SQL DIFFERENCE</a>
     ;
class `org.apache.commons.codec.language.DaitchMokotoffSoundex`:
	related functions:
		method `private static void parseRules(Scanner, String, Map<Character, List<Rule>>, Map<Character, Character>)` returns `void`, related with `org.apache.commons.codec.language.Nysiis.nysiis(String)`;
		method `public String soundex(String)` returns `java.lang.String`, related with `org.apache.commons.codec.language.Nysiis.nysiis(String)`, api document: 
     * Encodes a String using the Daitch-Mokotoff soundex algorithm with branching.
     * <p>
     * In case a string is encoded into multiple codes (see branching rules), the result will contain all codes,
     * separated by '|'.
     * </p>
     * <p>
     * Example: the name "AUERBACH" is encoded as both
     * </p>
     * <ul>
     * <li>097400</li>
     * <li>097500</li>
     * </ul>
     * <p>
     * Thus the result will be "097400|097500".
     * </p>
     *
     * @param source
     *            A String object to encode
     * @return A string containing a set of DM Soundex codes corresponding to the String supplied
     * @throws IllegalArgumentException
     *             if a character is not mapped
     ;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.