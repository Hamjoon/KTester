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
 

api document of method handleG(String, DoubleMetaphoneResult, int, boolean): 

     * Handles 'G' cases.
     

return type: 
int

dependent classes: 
class `org.apache.commons.codec.language.DoubleMetaphone.DoubleMetaphoneResult`:
	
class `org.apache.commons.codec.language.DoubleMetaphone`:
	dependent fields:
		java.lang.String[] org.apache.commons.codec.language.DoubleMetaphone.ES_EP_EB_EL_EY_IB_IL_IN_IE_EI_ER;
	related functions:
		method `private boolean conditionC0(String, int)` returns `boolean`, related with `org.apache.commons.codec.language.DoubleMetaphone.handleG(String, DoubleMetaphoneResult, int, boolean)`, api document: 
     * Complex condition 0 for 'C'.
     ;
		method `private int handleSC(String, DoubleMetaphoneResult, int)` returns `int`, related with `org.apache.commons.codec.language.DoubleMetaphone.handleG(String, DoubleMetaphoneResult, int, boolean)`, api document: 
     * Handles 'SC' cases.
     ;
		method `private int handleJ(String, DoubleMetaphoneResult, int, boolean)` returns `int`, related with `org.apache.commons.codec.language.DoubleMetaphone.handleG(String, DoubleMetaphoneResult, int, boolean)`, api document: 
     * Handles 'J' cases.
     ;
		method `private int handleCC(String, DoubleMetaphoneResult, int)` returns `int`, related with `org.apache.commons.codec.language.DoubleMetaphone.handleG(String, DoubleMetaphoneResult, int, boolean)`, api document: 
     * Handles 'CC' cases.
     ;
		method `private int handleW(String, DoubleMetaphoneResult, int)` returns `int`, related with `org.apache.commons.codec.language.DoubleMetaphone.handleG(String, DoubleMetaphoneResult, int, boolean)`, api document: 
     * Handles 'W' cases.
     ;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.