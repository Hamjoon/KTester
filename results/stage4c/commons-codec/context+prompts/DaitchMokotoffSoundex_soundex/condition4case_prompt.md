As a professional Java software testing expert, your task is to generate test cases for a focal method.

You will be provided with:
1. The focal method that needs testing.
2. Basic information of the target class to which the focus method belongs, including fields and methods.

Ensure the generated test cases meet the following requirements:

Test Coverage Requirements:
- Create test cases to cover all conditional branches in the target method, including `if`, `else if`, `else`, `switch`, and `case` statements.

Output Format:
- Organize the test cases by its functions logically.
- Include input parameters, expected output, and a description for each test case.
- Use empty list `[]` for the input when a test case does not require input parameters.
- Provide an expected return value for each test case, or specify the behavior that should be verified if there's no return value.
- Clearly describe the intention of each test case in the description.

Follow the JSON format provided here for the output:
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

@output{test cases}: output the test cases in the JSON format: