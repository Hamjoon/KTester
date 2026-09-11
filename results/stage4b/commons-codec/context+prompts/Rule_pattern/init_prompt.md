You are tasked with creating a framework for a test class in Java. Now analyze the following information carefully:

1. The method that needs to be tested and its parameters.
2. The constructor of the target class and its parameters.
3. Any dependencies or external resources used by the target class.

Based on this analysis, complete the test class template in the provided structure:

1. Initialize necessary fields and dependencies within setupBeforeAll() and setupBeforeEach().
2. Handle any required cleanup in teardownAfterEach() and teardownAfterAll().
3. Make sure not to invoke any private methods or fields of the target class and dependent class.
4. Refer to the "given" phase in the existing test class for guidance.
5. Add necessary import statements and ensure there are no syntax errors in the generated code.

@input{focal method and target class}
Here is the code of focal method, `pattern(String)`, with the focal class `Rule_pattern_Test`:
```Java
package org.apache.commons.codec.language.bm;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.Set;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.util.Comparator;
import org.apache.commons.codec.Resources;
import org.apache.commons.codec.language.bm.Languages.LanguageSet;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Collections;
public class Rule {
    public static final RPattern ALL_STRINGS_RMATCHER = input -> true;
    public static final String ALL = "ALL";
    private static RPattern pattern(final String regex) {
        final boolean startsWith = regex.startsWith("^");
        final boolean endsWith = regex.endsWith("$");
        final String content = regex.substring(startsWith ? 1 : 0, endsWith ? regex.length() - 1 : regex.length());
        final boolean boxes = content.contains("[");
        if (!boxes) {
            if (startsWith && endsWith) {
                // exact match
                if (content.isEmpty()) {
                    // empty
                    return input -> input.length() == 0;
                }
                return input -> input.equals(content);
            }
            if ((startsWith || endsWith) && content.isEmpty()) {
                // matches every string
                return ALL_STRINGS_RMATCHER;
            }
            if (startsWith) {
                // matches from start
                return input -> startsWith(input, content);
            }
            if (endsWith) {
                // matches from start
                return input -> endsWith(input, content);
            }
        } else {
            final boolean startsWithBox = content.startsWith("[");
            final boolean endsWithBox = content.endsWith("]");
            if (startsWithBox && endsWithBox) {
                String boxContent = content.substring(1, content.length() - 1);
                if (!boxContent.contains("[")) {
                    // box containing alternatives
                    final boolean negate = boxContent.startsWith("^");
                    if (negate) {
                        boxContent = boxContent.substring(1);
                    }
                    final String bContent = boxContent;
                    final boolean shouldMatch = !negate;
                    if (startsWith && endsWith) {
                        // exact match
                        return input -> (input.length() == 1) && (contains(bContent, input.charAt(0)) == shouldMatch);
                    }
                    if (startsWith) {
                        // first char
                        return input -> (input.length() > 0) && (contains(bContent, input.charAt(0)) == shouldMatch);
                    }
                    if (endsWith) {
                        // last char
                        return input -> (input.length() > 0) && (contains(bContent, input.charAt(input.length() - 1)) == shouldMatch);
                    }
                }
            }
        }
        return new RPattern() {
            final Pattern pattern = Pattern.compile(regex);
    
            @Override
            public boolean isMatch(final CharSequence input) {
                final Matcher matcher = pattern.matcher(input);
                return matcher.find();
            }
        };
    }
private static boolean contains(final CharSequence chars, final char input);
    private static boolean endsWith(final CharSequence input, final CharSequence suffix);
    public static List<Rule> getInstance(final NameType nameType, final RuleType rt, final String lang);
    public static List<Rule> getInstance(final NameType nameType, final RuleType rt, final Languages.LanguageSet langs);
    public static Map<String, List<Rule>> getInstanceMap(final NameType nameType, final RuleType rt, final String lang);
    public static Map<String, List<Rule>> getInstanceMap(final NameType nameType, final RuleType rt, final Languages.LanguageSet langs);
    public RPattern getLContext();
    public String getPattern();
    public PhonemeExpr getPhoneme();
    public RPattern getRContext();
    public boolean patternAndContextMatches(final CharSequence input, final int i);
    private static boolean startsWith(final CharSequence input, final CharSequence prefix);
}
```


@input{context information}

api document of class org.apache.commons.codec.language.bm.Rule: 

 * A phoneme rule.
 * <p>
 * Rules have a pattern, left context, right context, output phoneme, set of languages for which they apply
 * and a logical flag indicating if all languages must be in play. A rule matches if:
 * </p>
 * <ul>
 * <li>the pattern matches at the current position</li>
 * <li>the string up until the beginning of the pattern matches the left context</li>
 * <li>the string from the end of the pattern matches the right context</li>
 * <li>logical is ALL and all languages are in scope; or</li>
 * <li>logical is any other value and at least one language is in scope</li>
 * </ul>
 * <p>
 * Rules are typically generated by parsing rules resources. In normal use, there will be no need for the user
 * to explicitly construct their own.
 * </p>
 * <p>
 * Rules are immutable and thread-safe.
 * </p>
 * <h2>Rules resources</h2>
 * <p>
 * Rules are typically loaded from resource files. These are UTF-8 encoded text files. They are systematically
 * named following the pattern:
 * </p>
 * <blockquote>org/apache/commons/codec/language/bm/${NameType#getName}_${RuleType#getName}_${language}.txt</blockquote>
 * <p>
 * The format of these resources is the following:
 * </p>
 * <ul>
 * <li><b>Rules:</b> whitespace separated, double-quoted strings. There should be 4 columns to each row, and these
 * will be interpreted as:
 * <ol>
 * <li>pattern</li>
 * <li>left context</li>
 * <li>right context</li>
 * <li>phoneme</li>
 * </ol>
 * </li>
 * <li><b>End-of-line comments:</b> Any occurrence of '//' will cause all text following on that line to be discarded
 * as a comment.</li>
 * <li><b>Multi-line comments:</b> Any line starting with '/*' will start multi-line commenting mode. This will skip
 * all content until a line ending in '*' and '/' is found.</li>
 * <li><b>Blank lines:</b> All blank lines will be skipped.</li>
 * </ul>
 *
 * @since 1.6
 

constructors for class `org.apache.commons.codec.language.bm.Rule`: 
params: org.apache.commons.codec.language.bm.Rule.PhonemeExpr phoneme 
body:
```java
    public Rule(final String pattern, final String lContext, final String rContext, final PhonemeExpr phoneme) {
        this.pattern = pattern;
        this.lContext = pattern(lContext + "$");
        this.rContext = pattern("^" + rContext);
        this.phoneme = phoneme;
    }

```

parameters in constructors and focal method: 
class `org.apache.commons.codec.language.bm.Rule.PhonemeExpr`:
constructor:
```java
```

existing test class: 
```java
/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.codec.language.bm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests Rule.
 */
public class RuleTest {
    private Rule.Phoneme[][] makePhonemes() {
        final String[][] words = {
                { "rinD", "rinDlt", "rina", "rinalt", "rino", "rinolt", "rinu", "rinult" },
                { "dortlaj", "dortlej", "ortlaj", "ortlej", "ortlej-dortlaj" } };
        final Rule.Phoneme[][] phonemes = new Rule.Phoneme[words.length][];

        for (int i = 0; i < words.length; i++) {
            final String[] words_i = words[i];
            final Rule.Phoneme[] phonemes_i = phonemes[i] = new Rule.Phoneme[words_i.length];
            for (int j = 0; j < words_i.length; j++) {
                phonemes_i[j] = new Rule.Phoneme(words_i[j], Languages.NO_LANGUAGES);
            }
        }

        return phonemes;
    }

    @Test
    public void testPhonemeComparedToLaterIsNegative() {
        for (final Rule.Phoneme[] phs : makePhonemes()) {
            for (int i = 0; i < phs.length; i++) {
                for (int j = i + 1; j < phs.length; j++) {
                    final int c = Rule.Phoneme.COMPARATOR.compare(phs[i], phs[j]);

                    assertTrue(Integer.valueOf(c).intValue() < 0,
                            "Comparing " + phs[i].getPhonemeText() + " to " + phs[j].getPhonemeText() + " should be negative");
                }
            }
        }
    }

    @Test
    public void testPhonemeComparedToSelfIsZero() {
        for (final Rule.Phoneme[] phs : makePhonemes()) {
            for (final Rule.Phoneme ph : phs) {
                assertEquals(0, Rule.Phoneme.COMPARATOR.compare(ph, ph),
                        "Phoneme compared to itself should be zero: " + ph.getPhonemeText());
            }
        }
    }

    @Test
    public void testSubSequenceWorks() {
        // AppendableCharSequence is private to Rule. We can only make it through a Phoneme.

        final Rule.Phoneme a = new Rule.Phoneme("a", null);
        final Rule.Phoneme b = new Rule.Phoneme("b", null);
        final Rule.Phoneme cd = new Rule.Phoneme("cd", null);
        final Rule.Phoneme ef = new Rule.Phoneme("ef", null);
        final Rule.Phoneme ghi = new Rule.Phoneme("ghi", null);
        final Rule.Phoneme jkl = new Rule.Phoneme("jkl", null);

        assertEquals('a', a.getPhonemeText().charAt(0));
        assertEquals('b', b.getPhonemeText().charAt(0));
        assertEquals('c', cd.getPhonemeText().charAt(0));
        assertEquals('d', cd.getPhonemeText().charAt(1));
        assertEquals('e', ef.getPhonemeText().charAt(0));
        assertEquals('f', ef.getPhonemeText().charAt(1));
        assertEquals('g', ghi.getPhonemeText().charAt(0));
        assertEquals('h', ghi.getPhonemeText().charAt(1));
        assertEquals('i', ghi.getPhonemeText().charAt(2));
        assertEquals('j', jkl.getPhonemeText().charAt(0));
        assertEquals('k', jkl.getPhonemeText().charAt(1));
        assertEquals('l', jkl.getPhonemeText().charAt(2));

        final Rule.Phoneme a_b = new Rule.Phoneme(a, b);
        assertEquals('a', a_b.getPhonemeText().charAt(0));
        assertEquals('b', a_b.getPhonemeText().charAt(1));
        assertEquals("ab", a_b.getPhonemeText().subSequence(0, 2).toString());
        assertEquals("a", a_b.getPhonemeText().subSequence(0, 1).toString());
        assertEquals("b", a_b.getPhonemeText().subSequence(1, 2).toString());

        final Rule.Phoneme cd_ef = new Rule.Phoneme(cd, ef);
        assertEquals('c', cd_ef.getPhonemeText().charAt(0));
        assertEquals('d', cd_ef.getPhonemeText().charAt(1));
        assertEquals('e', cd_ef.getPhonemeText().charAt(2));
        assertEquals('f', cd_ef.getPhonemeText().charAt(3));
        assertEquals("c", cd_ef.getPhonemeText().subSequence(0, 1).toString());
        assertEquals("d", cd_ef.getPhonemeText().subSequence(1, 2).toString());
        assertEquals("e", cd_ef.getPhonemeText().subSequence(2, 3).toString());
        assertEquals("f", cd_ef.getPhonemeText().subSequence(3, 4).toString());
        assertEquals("cd", cd_ef.getPhonemeText().subSequence(0, 2).toString());
        assertEquals("de", cd_ef.getPhonemeText().subSequence(1, 3).toString());
        assertEquals("ef", cd_ef.getPhonemeText().subSequence(2, 4).toString());
        assertEquals("cde", cd_ef.getPhonemeText().subSequence(0, 3).toString());
        assertEquals("def", cd_ef.getPhonemeText().subSequence(1, 4).toString());
        assertEquals("cdef", cd_ef.getPhonemeText().subSequence(0, 4).toString());

        final Rule.Phoneme a_b_cd = new Rule.Phoneme(new Rule.Phoneme(a, b), cd);
        assertEquals('a', a_b_cd.getPhonemeText().charAt(0));
        assertEquals('b', a_b_cd.getPhonemeText().charAt(1));
        assertEquals('c', a_b_cd.getPhonemeText().charAt(2));
        assertEquals('d', a_b_cd.getPhonemeText().charAt(3));
        assertEquals("a", a_b_cd.getPhonemeText().subSequence(0, 1).toString());
        assertEquals("b", a_b_cd.getPhonemeText().subSequence(1, 2).toString());
        assertEquals("c", a_b_cd.getPhonemeText().subSequence(2, 3).toString());
        assertEquals("d", a_b_cd.getPhonemeText().subSequence(3, 4).toString());
        assertEquals("ab", a_b_cd.getPhonemeText().subSequence(0, 2).toString());
        assertEquals("bc", a_b_cd.getPhonemeText().subSequence(1, 3).toString());
        assertEquals("cd", a_b_cd.getPhonemeText().subSequence(2, 4).toString());
        assertEquals("abc", a_b_cd.getPhonemeText().subSequence(0, 3).toString());
        assertEquals("bcd", a_b_cd.getPhonemeText().subSequence(1, 4).toString());
        assertEquals("abcd", a_b_cd.getPhonemeText().subSequence(0, 4).toString());
    }
}

```



@input{test class template}
```java
package org.apache.commons.codec.language.bm;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
// Add other imports if necessary

@ExtendWith(MockitoExtension.class)
class Rule_pattern_Test {
    @BeforeAll
    static void setupBeforeAll() {
    }

    @BeforeEach
    void setupBeforeEach() {
    }

    @AfterEach
    void teardownAfterEach() {
    }

    @AfterAll
    static void teardownAfterAll() {
    }
}
```

@output{initial test class}: now consider the invoke examples output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.