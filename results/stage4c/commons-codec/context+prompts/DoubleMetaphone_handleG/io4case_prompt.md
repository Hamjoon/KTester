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
Here is the code of focal method, `handleG(String, DoubleMetaphoneResult, int, boolean)`, with the focal class `DoubleMetaphone`:
```Java
package org.apache.commons.codec.language;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
import org.apache.commons.codec.binary.StringUtils;
public class DoubleMetaphone implements StringEncoder {
    private static final String[] ES_EP_EB_EL_EY_IB_IL_IN_IE_EI_ER = new String[]{ "ES", "EP", "EB", "EL", "EY", "IB", "IL", "IN", "IE", "EI", "ER" };
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
private int handleG(final String value, final DoubleMetaphoneResult result, int index, final boolean slavoGermanic) {
        if (charAt(value, index + 1) == 'H') {
            index = handleGH(value, result, index);
        } else if (charAt(value, index + 1) == 'N') {
            if (((index == 1) && isVowel(charAt(value, 0))) && (!slavoGermanic)) {
                result.append("KN", "N");
            } else if (((!contains(value, index + 2, 2, "EY")) && (charAt(value, index + 1) != 'Y')) && (!slavoGermanic)) {
                result.append("N", "KN");
            } else {
                result.append("KN");
            }
            index += 2;
        } else if (contains(value, index + 1, 2, "LI") && (!slavoGermanic)) {
            result.append("KL", "L");
            index += 2;
        } else if ((index == 0) && ((charAt(value, index + 1) == 'Y') || contains(value, index + 1, 2, ES_EP_EB_EL_EY_IB_IL_IN_IE_EI_ER))) {
            // -- -ges-, -gep-, -gel-, -gie- at beginning --//
            result.append('K', 'J');
            index += 2;
        } else if ((((contains(value, index + 1, 2, "ER") || (charAt(value, index + 1) == 'Y')) && (!contains(value, 0, 6, "DANGER", "RANGER", "MANGER"))) && (!contains(value, index - 1, 1, "E", "I"))) && (!contains(value, index - 1, 3, "RGY", "OGY"))) {
            // -- -ger-, -gy- --//
            result.append('K', 'J');
            index += 2;
        } else if (contains(value, index + 1, 1, "E", "I", "Y") || contains(value, index - 1, 4, "AGGI", "OGGI")) {
            // -- Italian "biaggi" --//
            if ((contains(value, 0, 4, "VAN ", "VON ") || contains(value, 0, 3, "SCH")) || contains(value, index + 1, 2, "ET")) {
                // -- obvious germanic --//
                result.append('K');
            } else if (contains(value, index + 1, 3, "IER")) {
                result.append('J');
            } else {
                result.append('J', 'K');
            }
            index += 2;
        } else {
            if (charAt(value, index + 1) == 'G') {
                index += 2;
            } else {
                index++;
            }
            result.append('K');
        }
        return index;
    }
protected char charAt(final String value, final int index);
    protected static boolean contains(final String value, final int start, final int length, final String... criteria);
    public String doubleMetaphone(final String value);
    public String doubleMetaphone(String value, final boolean alternate);
    @Override
public Object encode(final Object obj) throws EncoderException;
    @Override
public String encode(final String value);
    public int getMaxCodeLen();
    private int handleGH(final String value, final DoubleMetaphoneResult result, int index);
    public boolean isDoubleMetaphoneEqual(final String value1, final String value2);
    public boolean isDoubleMetaphoneEqual(final String value1, final String value2, final boolean alternate);
    private boolean isVowel(final char ch);
    public void setMaxCodeLen(final int maxCodeLen);
}
```

@input{existing test case}
here's the existing test cases:
```json
<cases_json>
```

@output{test cases}: output the test cases in the JSON format: