package org.apache.commons.codec.language.bm;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.regex.Pattern;
import org.mockito.Mockito;

// Add other imports if necessary
class Rule_pattern_Test {

    @Test
    void testExactMatch() {
        // Given
        String regex = "^abc$";
        RPattern pattern = Rule.pattern(regex);
        // When
        boolean result = pattern.isMatch("abc");
        // Then
        assertTrue(result, "Pattern should match the exact string 'abc'");
    }

    @Test
    void testStartsWithMatch() {
        // Given
        String regex = "^abc";
        RPattern pattern = Rule.pattern(regex);
        // When
        boolean result = pattern.isMatch("abcdef");
        // Then
        assertTrue(result, "Pattern should match strings starting with 'abc'");
    }

    @Test
    void testEndsWithMatch() {
        // Given
        String regex = "def$";
        RPattern pattern = Rule.pattern(regex);
        // When
        boolean result = pattern.isMatch("abcdef");
        // Then
        assertTrue(result, "Pattern should match strings ending with 'def'");
    }

    @Test
    void testContainsCharacterClass() {
        // Given
        String regex = "^[abc]$";
        RPattern pattern = Rule.pattern(regex);
        // When
        boolean resultA = pattern.isMatch("a");
        boolean resultB = pattern.isMatch("b");
        boolean resultC = pattern.isMatch("c");
        boolean resultD = pattern.isMatch("d");
        // Then
        assertTrue(resultA, "Pattern should match 'a'");
        assertTrue(resultB, "Pattern should match 'b'");
        assertTrue(resultC, "Pattern should match 'c'");
        assertFalse(resultD, "Pattern should not match 'd'");
    }

    @Test
    void testPatternWithNegationInCharacterClass() {
        // Given
        String regex = "^[^abc]$";
        RPattern pattern = Rule.pattern(regex);
        // When
        boolean resultD = pattern.isMatch("d");
        boolean resultA = pattern.isMatch("a");
        // Then
        assertTrue(resultD, "Pattern should match 'd'");
        assertFalse(resultA, "Pattern should not match 'a'");
    }

    @Test
    void testComplexRegexPattern() {
        // Given
        String regex = "a.*b";
        RPattern pattern = Rule.pattern(regex);
        // When
        boolean result = pattern.isMatch("a123b");
        // Then
        assertTrue(result, "Pattern should match strings starting with 'a' and ending with 'b' with any characters in between");
    }

    @Test
    void testPatternWithNullRegex() {
        // Given
        String regex = null;
        // Expect
        assertThrows(NullPointerException.class, () -> {
            Rule.pattern(regex);
        }, "Pattern should throw NullPointerException for null regex");
    }

    @Test
    void testPatternWithInvalidRegex() {
        // Given
        String regex = "(unclosed";
        // Expect
        assertThrows(PatternSyntaxException.class, () -> {
            Rule.pattern(regex);
        }, "Pattern should throw PatternSyntaxException for unclosed parenthesis");
    }

    @Test
    void testPatternWithUnterminatedCharacterClass() {
        // Given
        String regex = "[unterminated";
        // Expect
        assertThrows(PatternSyntaxException.class, () -> {
            Rule.pattern(regex);
        }, "Pattern should throw PatternSyntaxException for unterminated character class");
    }

    @Test
    void testPatternWithSingleBackslash() {
        // Given
        String regex = "\\";
        // Expect
        assertThrows(PatternSyntaxException.class, () -> {
            Rule.pattern(regex);
        }, "Pattern should throw PatternSyntaxException for single backslash");
    }

    @Test
    void testPatternWithInvalidGroupSyntax() {
        // Given
        String regex = "(?invalid)";
        // Expect
        assertThrows(PatternSyntaxException.class, () -> {
            Rule.pattern(regex);
        }, "Pattern should throw PatternSyntaxException for invalid group syntax");
    }
}
