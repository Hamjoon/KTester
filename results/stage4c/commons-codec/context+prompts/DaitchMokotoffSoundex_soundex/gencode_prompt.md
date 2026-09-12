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
Here is the code of focal method, `soundex(String, boolean)`, with the focal class `DaitchMokotoffSoundex`:
```Java
package org.apache.commons.codec.language;
import java.util.Scanner;
import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
import org.apache.commons.codec.Resources;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.Collections;
public class DaitchMokotoffSoundex implements StringEncoder {
    private static final Map<Character, List<Rule>> RULES = new HashMap<>();
        /**
     * Inner class representing a branch during DM soundex encoding.
     */
    private static final class Branch {
        private final StringBuilder builder;
    
        private String cachedString;
    
        private String lastReplacement;
    
        private Branch() {
            builder = new StringBuilder();
            lastReplacement = null;
            cachedString = null;
        }
    
        /**
         * Creates a new branch, identical to this branch.
         *
         * @return a new, identical branch
         */
        public Branch createBranch() {
            final Branch branch = new Branch();
            branch.builder.append(toString());
            branch.lastReplacement = this.lastReplacement;
            return branch;
        }
    
        @Override
        public boolean equals(final Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Branch)) {
                return false;
            }
            return toString().equals(((Branch) (other)).toString());
        }
    
        /**
         * Finish this branch by appending '0's until the maximum code length has been reached.
         */
        public void finish() {
            while (builder.length() < MAX_LENGTH) {
                builder.append('0');
                cachedString = null;
            } 
        }
    
        @Override
        public int hashCode() {
            return toString().hashCode();
        }
    
        /**
         * Process the next replacement to be added to this branch.
         *
         * @param replacement
         * 		the next replacement to append
         * @param forceAppend
         * 		indicates if the default processing shall be overridden
         */
        public void processNextReplacement(final String replacement, final boolean forceAppend) {
            final boolean append = ((lastReplacement == null) || (!lastReplacement.endsWith(replacement))) || forceAppend;
            if (append && (builder.length() < MAX_LENGTH)) {
                builder.append(replacement);
                // remove all characters after the maximum length
                if (builder.length() > MAX_LENGTH) {
                    builder.delete(MAX_LENGTH, builder.length());
                }
                cachedString = null;
            }
            lastReplacement = replacement;
        }
    
        @Override
        public String toString() {
            if (cachedString == null) {
                cachedString = builder.toString();
            }
            return cachedString;
        }
    }
    /**
     * Inner class for storing rules.
     */
    private static final class Rule {
        private final String pattern;
    
        private final String[] replacementAtStart;
    
        private final String[] replacementBeforeVowel;
    
        private final String[] replacementDefault;
    
        protected Rule(final String pattern, final String replacementAtStart, final String replacementBeforeVowel, final String replacementDefault) {
            this.pattern = pattern;
            this.replacementAtStart = replacementAtStart.split("\\|");
            this.replacementBeforeVowel = replacementBeforeVowel.split("\\|");
            this.replacementDefault = replacementDefault.split("\\|");
        }
    
        public int getPatternLength() {
            return pattern.length();
        }
    
        public String[] getReplacements(final String context, final boolean atStart) {
            if (atStart) {
                return replacementAtStart;
            }
            final int nextIndex = getPatternLength();
            final boolean nextCharIsVowel = (nextIndex < context.length()) && isVowel(context.charAt(nextIndex));
            if (nextCharIsVowel) {
                return replacementBeforeVowel;
            }
            return replacementDefault;
        }
    
        private boolean isVowel(final char ch) {
            return ((((ch == 'a') || (ch == 'e')) || (ch == 'i')) || (ch == 'o')) || (ch == 'u');
        }
    
        public boolean matches(final String context) {
            return context.startsWith(pattern);
        }
    
        @Override
        public String toString() {
            return String.format("%s=(%s,%s,%s)", pattern, Arrays.asList(replacementAtStart), Arrays.asList(replacementBeforeVowel), Arrays.asList(replacementDefault));
        }
    }
private String[] soundex(final String source, final boolean branching) {
        if (source == null) {
            return null;
        }
        final String input = cleanup(source);
        final Set<Branch> currentBranches = new LinkedHashSet<>();
        currentBranches.add(new Branch());
        char lastChar = '\u0000';
        for (int index = 0; index < input.length(); index++) {
            final char ch = input.charAt(index);
            // ignore whitespace inside a name
            if (Character.isWhitespace(ch)) {
                continue;
            }
            final String inputContext = input.substring(index);
            final List<Rule> rules = RULES.get(ch);
            if (rules == null) {
                continue;
            }
            // use an EMPTY_LIST to avoid false positive warnings wrt potential null pointer access
            final List<Branch> nextBranches = (branching) ? new ArrayList<>() : Collections.emptyList();
            for (final Rule rule : rules) {
                if (rule.matches(inputContext)) {
                    if (branching) {
                        nextBranches.clear();
                    }
                    final String[] replacements = rule.getReplacements(inputContext, lastChar == '\u0000');
                    final boolean branchingRequired = (replacements.length > 1) && branching;
                    for (final Branch branch : currentBranches) {
                        for (final String nextReplacement : replacements) {
                            // if we have multiple replacements, always create a new branch
                            final Branch nextBranch = (branchingRequired) ? branch.createBranch() : branch;
                            // special rule: occurrences of mn or nm are treated differently
                            final boolean force = ((lastChar == 'm') && (ch == 'n')) || ((lastChar == 'n') && (ch == 'm'));
                            nextBranch.processNextReplacement(nextReplacement, force);
                            if (!branching) {
                                break;
                            }
                            nextBranches.add(nextBranch);
                        }
                    }
                    if (branching) {
                        currentBranches.clear();
                        currentBranches.addAll(nextBranches);
                    }
                    index += rule.getPatternLength() - 1;
                    break;
                }
            }
            lastChar = ch;
        }
        final String[] result = new String[currentBranches.size()];
        int index = 0;
        for (final Branch branch : currentBranches) {
            branch.finish();
            result[index++] = branch.toString();
        }
        return result;
    }
private String cleanup(final String input);
    @Override
public Object encode(final Object obj) throws EncoderException;
    @Override
public String encode(final String source);
    public String soundex(final String source);
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

api document of class org.apache.commons.codec.language.DaitchMokotoffSoundex: 

 * Encodes a string into a Daitch-Mokotoff Soundex value.
 * <p>
 * The Daitch-Mokotoff Soundex algorithm is a refinement of the Russel and American Soundex algorithms, yielding greater
 * accuracy in matching especially Slavish and Yiddish surnames with similar pronunciation but differences in spelling.
 * </p>
 * <p>
 * The main differences compared to the other soundex variants are:
 * </p>
 * <ul>
 * <li>coded names are 6 digits long
 * <li>the initial character of the name is coded
 * <li>rules to encoded multi-character n-grams
 * <li>multiple possible encodings for the same name (branching)
 * </ul>
 * <p>
 * This implementation supports branching, depending on the used method:
 * <ul>
 * <li>{@link #encode(String)} - branching disabled, only the first code will be returned
 * <li>{@link #soundex(String)} - branching enabled, all codes will be returned, separated by '|'
 * </ul>
 * <p>
 * Note: this implementation has additional branching rules compared to the original description of the algorithm. The
 * rules can be customized by overriding the default rules contained in the resource file
 * {@code org/apache/commons/codec/language/dmrules.txt}.
 * </p>
 * <p>
 * This class is thread-safe.
 * </p>
 *
 * @see Soundex
 * @see <a href="https://en.wikipedia.org/wiki/Daitch%E2%80%93Mokotoff_Soundex"> Wikipedia - Daitch-Mokotoff Soundex</a>
 * @see <a href="http://www.avotaynu.com/soundex.htm">Avotaynu - Soundexing and Genealogy</a>
 *
 * @since 1.10
 

api document of method soundex(String, boolean): 

     * Perform the actual DM Soundex algorithm on the input string.
     *
     * @param source
     *            A String object to encode
     * @param branching
     *            If branching shall be performed
     * @return A string array containing all DM Soundex codes corresponding to the String supplied depending on the
     *         selected branching mode
     

dependent classes: 
class `org.apache.commons.codec.language.DaitchMokotoffSoundex`:
	dependent fields:
		java.util.Map<java.lang.Character, java.util.List<org.apache.commons.codec.language.DaitchMokotoffSoundex.Rule>> org.apache.commons.codec.language.DaitchMokotoffSoundex.RULES;
	
class `org.apache.commons.codec.binary.Base64`:
	related functions:
		method ` static byte[] toIntegerBytes(BigInteger)` returns `byte[]`, related with `org.apache.commons.codec.language.DaitchMokotoffSoundex.soundex(String, boolean)`, api document: 
     * Returns a byte-array representation of a {@code BigInteger} without sign bit.
     *
     * @param bigInt
     *            {@code BigInteger} to be converted
     * @return a byte array representation of the BigInteger parameter
     ;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.