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
Here is the code of focal method, `metaphone(String)`, with the focal class `Metaphone`:
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
 

api document of method metaphone(String): 

     * Find the metaphone value of a String. This is similar to the
     * soundex algorithm, but better at finding similar sounding words.
     * All input is converted to upper case.
     * Limitations: Input format is expected to be a single ASCII word
     * with only characters in the A - Z range, no punctuation or numbers.
     *
     * @param txt String to find the metaphone code for
     * @return A metaphone code corresponding to the String supplied
     

dependent classes: 
class `org.apache.commons.codec.language.Metaphone`:
	dependent fields:
		java.lang.String org.apache.commons.codec.language.Metaphone.FRONTV;
		java.lang.String org.apache.commons.codec.language.Metaphone.VARSON;
	
class `org.apache.commons.codec.language.SoundexUtils`:
	related functions:
		method ` static String clean(String)` returns `java.lang.String`, related with `org.apache.commons.codec.language.Metaphone.metaphone(String)`, api document: 
     * Cleans up the input string before Soundex processing by only returning
     * upper case letters.
     *
     * @param str
     *                  The String to clean.
     * @return A clean String.
     ;
class `org.apache.commons.codec.language.DoubleMetaphone`:
	related functions:
		method `private String cleanInput(String)` returns `java.lang.String`, related with `org.apache.commons.codec.language.Metaphone.metaphone(String)`, api document: 
     * Cleans the input.
     ;
class `org.apache.commons.codec.language.Caverphone1`:
	related functions:
		method `public String encode(String)` returns `java.lang.String`, related with `org.apache.commons.codec.language.Metaphone.metaphone(String)`, api document: 
     * Encodes the given String into a Caverphone value.
     *
     * @param source
     *            String the source string
     * @return A caverphone code for the given String
     ;
class `org.apache.commons.codec.language.Nysiis`:
	related functions:
		method `public String nysiis(String)` returns `java.lang.String`, related with `org.apache.commons.codec.language.Metaphone.metaphone(String)`, api document: 
     * Retrieves the NYSIIS code for a given String object.
     *
     * @param str
     *            String to encode using the NYSIIS algorithm
     * @return A NYSIIS code for the String supplied
     ;
class `org.apache.commons.codec.language.DaitchMokotoffSoundex`:
	related functions:
		method `public String soundex(String)` returns `java.lang.String`, related with `org.apache.commons.codec.language.Metaphone.metaphone(String)`, api document: 
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
class `org.apache.commons.codec.language.ColognePhonetic`:
	related functions:
		method `private char[] preprocess(String)` returns `char[]`, related with `org.apache.commons.codec.language.Metaphone.metaphone(String)`, api document: 
     * Converts the string to upper case and replaces Germanic umlaut characters
     * The following characters are mapped:
     * <ul>
     * <li>capital A, umlaut mark</li>
     * <li>capital U, umlaut mark</li>
     * <li>capital O, umlaut mark</li>
     * <li>small sharp s, German</li>
     * </ul>
     ;
class `org.apache.commons.codec.digest.B64`:
	related functions:
		method ` static String getRandomSalt(int, Random)` returns `java.lang.String`, related with `org.apache.commons.codec.language.Metaphone.metaphone(String)`, api document: 
     * Generates a string of random chars from the B64T set.
     * <p>
     * The salt is generated with the {@link Random} provided.
     * </p>
     *
     * @param num Number of chars to generate.
     * @param random an instance of {@link Random}.
     * @return a random salt {@link String}.
     ;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.