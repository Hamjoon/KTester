```java
package org.flmelody.util;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AntPathMatcher_isMatch_Test {

    private AntPathMatcher antPathMatcher;

    @BeforeAll
    static void setupBeforeAll() {
        // Any global setup can be done here
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the AntPathMatcher with default parameters
        AntPathMatcher.Builder builder = new AntPathMatcher.Builder();
        antPathMatcher = builder.withPathSeparator('/').withIgnoreCase().withTrimTokens().build();
    }

    @AfterEach
    void teardownAfterEach() {
        // Any cleanup after each test can be done here
    }

    @AfterAll
    static void teardownAfterAll() {
        // Any global cleanup can be done here
    }

    @Test
    void testIsMatchWithEmptyStrings() {
        // Test when both pattern and path are empty strings. The method should return true.
        assertTrue(antPathMatcher.isMatch("", ""), "Expected true when both pattern and path are empty.");
    }

    @Test
    void testIsMatchWithSingleAsteriskAndEmptyPath() {
        // Test when pattern is '/*' and path is empty. The method should return false.
        assertFalse(antPathMatcher.isMatch("/*", ""), "Expected false when pattern is '/*' and path is empty.");
    }

    @Test
    void testIsMatchWithSingleAsteriskAndRootPath() {
        // Test when pattern is '/*' and path is '/'. The method should return true.
        assertTrue(antPathMatcher.isMatch("/*", "/"), "Expected true when pattern is '/*' and path is '/'.");
    }

    @Test
    void testIsMatchWithAsteriskAndAnyPath() {
        // Test when pattern is '*' and path is 'anyPath'. The method should return true.
        assertTrue(antPathMatcher.isMatch("*", "anyPath"), "Expected true when pattern is '*' and path is 'anyPath'.");
    }

    @Test
    void testIsMatchWithAsteriskAndTrailingSlash() {
        // Test when pattern is '*/' and path is 'anyPath/'. The method should return true.
        assertTrue(antPathMatcher.isMatch("*/", "anyPath/"), "Expected true when pattern is '*/' and path is 'anyPath/'.");
    }

    @Test
    void testIsMatchWithDoubleQuestionMark() {
        // Test when pattern is '??' and path is 'ab'. The method should return true.
        assertTrue(antPathMatcher.isMatch("??", "ab"), "Expected true when pattern is '??' and path is 'ab'.");
    }

    @Test
    void testIsMatchWithQuestionMarkAndSingleCharacter() {
        // Test when pattern is '??' and path is 'a'. The method should return false.
        assertFalse(antPathMatcher.isMatch("??", "a"), "Expected false when pattern is '??' and path is 'a'.");
    }

    @Test
    void testIsMatchWithAsteriskAndMultipleCharacters() {
        // Test when pattern is 'a*' and path is 'abc'. The method should return true.
        assertTrue(antPathMatcher.isMatch("a*", "abc"), "Expected true when pattern is 'a*' and path is 'abc'.");
    }

    @Test
    void testIsMatchWithAsteriskInMiddle() {
        // Test when pattern is 'a*b' and path is 'acb'. The method should return true.
        assertTrue(antPathMatcher.isMatch("a*b", "acb"), "Expected true when pattern is 'a*b' and path is 'acb'.");
    }

    @Test
    void testIsMatchWithAsteriskAndSingleCharacter() {
        // Test when pattern is 'a*b' and path is 'a'. The method should return false.
        assertFalse(antPathMatcher.isMatch("a*b", "a"), "Expected false when pattern is 'a*b' and path is 'a'.");
    }

    @Test
    void testIsMatchWithQuestionMarkInMiddle() {
        // Test when pattern is 'a?b' and path is 'acb'. The method should return true.
        assertTrue(antPathMatcher.isMatch("a?b", "acb"), "Expected true when pattern is 'a?b' and path is 'acb'.");
    }

    @Test
    void testIsMatchWithQuestionMarkAndExactMatch() {
        // Test when pattern is 'a?b' and path is 'ab'. The method should return false.
        assertFalse(antPathMatcher.isMatch("a?b", "ab"), "Expected false when pattern is 'a?b' and path is 'ab'.");
    }

    @Test
    void testIsMatchWithMultipleAsterisks() {
        // Test when pattern is 'a*b*c' and path is 'abc'. The method should return true.
        assertTrue(antPathMatcher.isMatch("a*b*c", "abc"), "Expected true when pattern is 'a*b*c' and path is 'abc'.");
    }

    @Test
    void testIsMatchWithComplexPatternAndPath() {
        // Test when pattern is 'a*b*c' and path is 'aXbYc'. The method should return true.
        assertTrue(antPathMatcher.isMatch("a*b*c", "aXbYc"), "Expected true when pattern is 'a*b*c' and path is 'aXbYc'.");
    }

    @Test
    void testIsMatchWithPatternNotMatchingPath() {
        // Test when pattern is 'a*b*c' and path is 'ab'. The method should return false.
        assertFalse(antPathMatcher.isMatch("a*b*c", "ab"), "Expected false when pattern is 'a*b*c' and path is 'ab'.");
    }

    @Test
    void testIsMatchWithDoubleSlashPattern() {
        // Test when pattern is '/*/*' and path is '/path/to/resource'. The method should return true.
        assertTrue(antPathMatcher.isMatch("/*/*", "/path/to/resource"), "Expected true when pattern is '/*/*' and path is '/path/to/resource'.");
    }

    @Test
    void testIsMatchWithDoubleSlashPatternAndSinglePath() {
        // Test when pattern is '/*/*' and path is '/path'. The method should return false.
        assertFalse(antPathMatcher.isMatch("/*/*", "/path"), "Expected false when pattern is '/*/*' and path is '/path'.");
    }

    @Test
    void testIsMatchWithAsteriskInMiddleOfPath() {
        // Test when pattern is 'a*/b' and path is 'abc/b'. The method should return true.
        assertTrue(antPathMatcher.isMatch("a*/b", "abc/b"), "Expected true when pattern is 'a*/b' and path is 'abc/b'.");
    }

    @Test
    void testIsMatchWithAsteriskInMiddleNotMatching() {
        // Test when pattern is 'a*/b' and path is 'ab/b'. The method should return false.
        assertFalse(antPathMatcher.isMatch("a*/b", "ab/b"), "Expected false when pattern is 'a*/b' and path is 'ab/b'.");
    }

    @Test
    void testIsMatchWithSingleAsteriskAndRootPathAgain() {
        // Test when pattern is '*/' and path is '/'. The method should return true.
        assertTrue(antPathMatcher.isMatch("*/", "/"), "Expected true when pattern is '*/' and path is '/'.");
    }

    @Test
    void testIsMatchWithSingleAsteriskAndNonEmptyPath() {
        // Test when pattern is '*/' and path is 'path/'. The method should return true.
        assertTrue(antPathMatcher.isMatch("*/", "path/"), "Expected true when pattern is '*/' and path is 'path/'.");
    }

    @Test
    void testIsMatchWithDoubleQuestionMarkAndPath() {
        // Test when pattern is '??/path' and path is 'ab/path'. The method should return true.
        assertTrue(antPathMatcher.isMatch("??/path", "ab/path"), "Expected true when pattern is '??/path' and path is 'ab/path'.");
    }

    @Test
    void testIsMatchWithDoubleQuestionMarkAndSingleCharacterPath() {
        // Test when pattern is '??/path' and path is 'a/path'. The method should return false.
        assertFalse(antPathMatcher.isMatch("??/path", "a/path"), "Expected false when pattern is '??/path' and path is 'a/path'.");
    }

    @Test
    void testIsMatchWithWildcardAndPath() {
        // Test when pattern is '*/**' and path is 'any/path/here'. The method should return true.
        assertTrue(antPathMatcher.isMatch("*/**", "any/path/here"), "Expected true when pattern is '*/**' and path is 'any/path/here'.");
    }

    @Test
    void testIsMatchWithPathEndingWithWildcard() {
        // Test when pattern is '**/path' and path is 'any/path'. The method should return true.
        assertTrue(antPathMatcher.isMatch("**/path", "any/path"), "Expected true when pattern is '**/path' and path is 'any/path'.");
    }

    @Test
    void testIsMatchWithNullPatternAndEmptyPath() {
        // Test when pattern is null and path is an empty string. The method should throw a NullPointerException.
        assertThrows(NullPointerException.class, () -> {
            antPathMatcher.isMatch(null, "");
        }, "Expected NullPointerException when pattern is null and path is an empty string.");
    }

    @Test
    void testIsMatchWithEmptyPatternAndNullPath() {
        // Test when pattern is an empty string and path is null. The method should throw a NullPointerException.
        assertThrows(NullPointerException.class, () -> {
            antPathMatcher.isMatch("", null);
        }, "Expected NullPointerException when pattern is an empty string and path is null.");
    }

    @Test
    void testIsMatchWithNullPatternAndNullPath() {
        // Test when both pattern and path are null. The method should throw a NullPointerException.
        assertThrows(NullPointerException.class, () -> {
            antPathMatcher.isMatch(null, null);
        }, "Expected NullPointerException when both pattern and path are null.");
    }
}
```