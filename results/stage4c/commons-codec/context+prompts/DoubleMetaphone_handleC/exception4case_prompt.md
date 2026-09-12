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
Here is the code of focal method, `handleC(String, DoubleMetaphoneResult, int)`, with the focal class `DoubleMetaphone`:
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
private int handleC(final String value, final DoubleMetaphoneResult result, int index) {
        if (conditionC0(value, index)) {
            // very confusing, moved out
            result.append('K');
            index += 2;
        } else if ((index == 0) && contains(value, index, 6, "CAESAR")) {
            result.append('S');
            index += 2;
        } else if (contains(value, index, 2, "CH")) {
            index = handleCH(value, result, index);
        } else if (contains(value, index, 2, "CZ") && (!contains(value, index - 2, 4, "WICZ"))) {
            // -- "Czerny" --//
            result.append('S', 'X');
            index += 2;
        } else if (contains(value, index + 1, 3, "CIA")) {
            // -- "focaccia" --//
            result.append('X');
            index += 3;
        } else if (contains(value, index, 2, "CC") && (!((index == 1) && (charAt(value, 0) == 'M')))) {
            // -- double "cc" but not "McClelland" --//
            return handleCC(value, result, index);
        } else if (contains(value, index, 2, "CK", "CG", "CQ")) {
            result.append('K');
            index += 2;
        } else if (contains(value, index, 2, "CI", "CE", "CY")) {
            // -- Italian vs. English --//
            if (contains(value, index, 3, "CIO", "CIE", "CIA")) {
                result.append('S', 'X');
            } else {
                result.append('S');
            }
            index += 2;
        } else {
            result.append('K');
            if (contains(value, index + 1, 2, " C", " Q", " G")) {
                // -- Mac Caffrey, Mac Gregor --//
                index += 3;
            } else if (contains(value, index + 1, 1, "C", "K", "Q") && (!contains(value, index + 1, 2, "CE", "CI"))) {
                index += 2;
            } else {
                index++;
            }
        }
        return index;
    }
protected char charAt(final String value, final int index);
    private boolean conditionC0(final String value, final int index);
    protected static boolean contains(final String value, final int start, final int length, final String... criteria);
    public String doubleMetaphone(final String value);
    public String doubleMetaphone(String value, final boolean alternate);
    @Override
public Object encode(final Object obj) throws EncoderException;
    @Override
public String encode(final String value);
    public int getMaxCodeLen();
    private int handleCC(final String value, final DoubleMetaphoneResult result, int index);
    private int handleCH(final String value, final DoubleMetaphoneResult result, final int index);
    public boolean isDoubleMetaphoneEqual(final String value1, final String value2);
    public boolean isDoubleMetaphoneEqual(final String value1, final String value2, final boolean alternate);
    public void setMaxCodeLen(final int maxCodeLen);
}
```
@input{existing test case}
here's the existing test cases:
```json
<cases_json>
```

@output{test cases}: output the test cases in the JSON format: