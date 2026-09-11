You are a professional software tester. Write test cases for a focal method in a target class.

**Instructions:**
1. Analyze method behavior and identify ALL possible exception scenarios
2. Design test cases that trigger each identified exception
3. Follow existing test case structure, Avoid duplication and create new test cases for uncovered exception scenarios

Test Case Format:
1. Group test cases by test function name
2. Each test case MUST include:
   - Input parameters with values (use empty list `[]` if none)
   - Expected exception or observable behavior
   - Clear description of test scenario
3. Return empty array if no exception scenarios found

Output Schema (STRICT JSON format):
```json
[
    {
        "group": "test name",
        "cases": [
            {
                "input": [
                    {
                        "parameter": "param name",
                        "value": "param value"
                    }
                ],
                "expected": "expected exception or behavior",
                "description": "test scenario description"
            }
        ]
    },
    {...}
]
```

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
@input{existing test case}
here's the existing test cases:
```json
<cases_json>
```

@output{test cases}: output the test cases in the JSON format: