As a professional Java software testing expert, your task is to generate test cases for a focal method.

Guidelines:
1. Analyze the method's functionality and identify possible input combinations including normal cases and boundary conditions.
2. Review the provided existing test cases. Avoid duplication and create new test cases for untested input combinations.

Context Provided:
1. The focal method that needs testing.
2. Relevant code and metadata for the class containing the method, including fields and other methods.
3. The existing test cases (in JSON format) to help avoid redundancy.

Output Instructions:
- Organize the test cases by the name of the test function (group).
- Each test case must include:
    - input: A list of parameter names and values. Use an empty list `[]` if no input is required.
    - expected: The return value or expected observable behavior.
    - description: A clear and concise explanation of the scenario and the intent of the test case.

Maintain the following strict JSON format:
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
Here is the code of focal method, `doubleMetaphone(String, boolean)`, with the focal class `DoubleMetaphone`:
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

@input{existing test case}
here's the existing test cases:
```json
<cases_json>
```

@output{test cases}: output the test cases in the JSON format: