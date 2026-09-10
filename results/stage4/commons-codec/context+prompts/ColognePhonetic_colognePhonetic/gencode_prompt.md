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
Here is the code of focal method, `colognePhonetic(String)`, with the focal class `ColognePhonetic`:
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
 

api document of method colognePhonetic(String): 

     * <p>
     * Implements the <i>K&ouml;lner Phonetik</i> algorithm.
     * </p>
     * <p>
     * In contrast to the initial description of the algorithm, this implementation does the encoding in one pass.
     * </p>
     *
     * @param text The source text to encode
     * @return the corresponding encoding according to the <i>K&ouml;lner Phonetik</i> algorithm
     

dependent classes: 
class `org.apache.commons.codec.language.ColognePhonetic`:
	dependent fields:
		char[] org.apache.commons.codec.language.ColognePhonetic.GKQ;
		char[] org.apache.commons.codec.language.ColognePhonetic.SZ;
		char[] org.apache.commons.codec.language.ColognePhonetic.CSZ;
		char[] org.apache.commons.codec.language.ColognePhonetic.DTX;
		char org.apache.commons.codec.language.ColognePhonetic.CHAR_IGNORE;
		char[] org.apache.commons.codec.language.ColognePhonetic.AHKOQUX;
		char[] org.apache.commons.codec.language.ColognePhonetic.AEIJOUY;
		char[] org.apache.commons.codec.language.ColognePhonetic.FPVW;
		char[] org.apache.commons.codec.language.ColognePhonetic.AHKLOQRUX;
		char[] org.apache.commons.codec.language.ColognePhonetic.CKQ;
	
class `org.apache.commons.codec.language.ColognePhonetic.CologneBuffer`:
	related functions:
		method `public boolean isEmpty()` returns `boolean`, related with `org.apache.commons.codec.language.ColognePhonetic.colognePhonetic(String)`;
class `org.apache.commons.codec.language.ColognePhonetic.CologneOutputBuffer`:
	related functions:
		method `public void put(char)` returns `void`, related with `org.apache.commons.codec.language.ColognePhonetic.colognePhonetic(String)`, api document: 
         * Stores the next code in the output buffer, keeping track of the previous code.
         * '0' is only stored if it is the first entry.
         * Ignored chars are never stored.
         * If the code is the same as the last code (whether stored or not) it is not stored.
         *
         * @param code the code to store.
         ;
class `org.apache.commons.codec.binary.Base16`:
	related functions:
		method ` void decode(byte[], int, int, Context)` returns `void`, related with `org.apache.commons.codec.language.ColognePhonetic.colognePhonetic(String)`;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.