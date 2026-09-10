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

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the AntPathMatcher with default parameters
        AntPathMatcher.Builder builder = new AntPathMatcher.Builder();
        antPathMatcher = builder.withPathSeparator('/').withIgnoreCase().withTrimTokens().build();
    }

    @Test
    void testIsMatchWithAsterisk() {
        assertTrue(antPathMatcher.isMatch("/api/**", "/api/test"));
        assertTrue(antPathMatcher.isMatch("/**", "/api/test"));
        assertTrue(antPathMatcher.isMatch("/**/test", "/api/test"));
    }

    @Test
    void testIsMatchWithFileExtensions() {
        assertTrue(antPathMatcher.isMatch("/api/**.js", "/api/test.js"));
        assertFalse(antPathMatcher.isMatch("/api/**.js", "/api/test"));
        assertTrue(antPathMatcher.isMatch("/api/**.js", "/api/test/a.js"));
    }

    @Test
    void testIsMatchWithNonMatchingPaths() {
        assertFalse(antPathMatcher.isMatch("/static/**.js", "/api/test/a.js"));
    }

    @Test
    void testIsMatchWithBasicFunctionality() {
        // Test case where both pattern and path are empty strings.
        assertTrue(antPathMatcher.isMatch("", ""), "Expected to return true for both empty pattern and path.");
        // Test case where pattern has a leading wildcard and path is empty.
        assertFalse(antPathMatcher.isMatch("/*", ""), "Expected to return false for pattern '/*' and empty path.");
        // Test case where pattern is a single wildcard and path is a non-empty string.
        assertTrue(antPathMatcher.isMatch("*", "anyPath"), "Expected to return true for pattern '*' and non-empty path.");
        // Test case where pattern matches the path exactly.
        assertTrue(antPathMatcher.isMatch("test", "test"), "Expected to return true for exact match.");
        // Test case where pattern does not match the path.
        assertFalse(antPathMatcher.isMatch("test", "testing"), "Expected to return false for non-matching pattern and path.");
        // Test case where pattern matches a path with a wildcard at the end.
        assertTrue(antPathMatcher.isMatch("test/*", "test/folder"), "Expected to return true for pattern 'test/*' with matching path.");
        // Test case where pattern matches a path with a wildcard at the end and path ends with a separator.
        assertTrue(antPathMatcher.isMatch("test/*", "test/"), "Expected to return true for pattern 'test/*' with path ending with separator.");
        // Test case where pattern does not match the path due to differing prefixes.
        assertFalse(antPathMatcher.isMatch("test/*", "testing/folder"), "Expected to return false for differing prefixes.");
    }

    @Test
    void testIsMatchWithWildcardFunctionality() {
        // Test case where pattern has a wildcard followed by a separator and path ends with a separator.
        assertTrue(antPathMatcher.isMatch("*/", "folder/"), "Expected to return true for pattern '*/' and path 'folder/'.");
        // Test case where pattern matches a path with a wildcard followed by a specific file name.
        assertTrue(antPathMatcher.isMatch("*/file.txt", "folder/file.txt"), "Expected to return true for pattern '*/file.txt' and matching path.");
        // Test case where pattern does not include any wildcards and path includes a folder.
        assertFalse(antPathMatcher.isMatch("file.txt", "folder/file.txt"), "Expected to return false for non-matching pattern and path.");
        // Test case where pattern is a double wildcard.
        assertTrue(antPathMatcher.isMatch("**", "any/path/to/somewhere"), "Expected to return true for double wildcard '**'.");
        // Test case where pattern includes a wildcard followed by a double wildcard and matches the path.
        assertTrue(antPathMatcher.isMatch("*/**/file.txt", "folder/subfolder/file.txt"), "Expected to return true for pattern '*/**/file.txt' and matching path.");
        // Test case where pattern is a double wildcard followed by a specific file name and the path is the file itself.
        assertTrue(antPathMatcher.isMatch("**/file.txt", "file.txt"), "Expected to return true for pattern '**/file.txt' and matching path.");
    }

    @Test
    void testIsMatchWithQuestionMarkFunctionality() {
        // Test case where pattern includes a question mark that matches any single character.
        assertTrue(antPathMatcher.isMatch("te?t", "test"), "Expected to return true for pattern 'te?t' and matching path.");
        // Test case where pattern includes a question mark and path matches.
        assertTrue(antPathMatcher.isMatch("te?t", "teat"), "Expected to return true for pattern 'te?t' with matching path.");
        // Test case where pattern requires a character to match but path is too short.
        assertFalse(antPathMatcher.isMatch("te?t", "te"), "Expected to return false for pattern 'te?t' and short path.");
        // Test case where pattern includes two question marks that match any two characters.
        assertTrue(antPathMatcher.isMatch("te??t", "text"), "Expected to return true for pattern 'te??t' with matching path.");
        // Test case where pattern includes two question marks and path matches.
        assertTrue(antPathMatcher.isMatch("te??t", "teat"), "Expected to return true for pattern 'te??t' and matching path.");
        // Test case where pattern requires two characters to match but path has extra characters.
        assertFalse(antPathMatcher.isMatch("te??t", "testt"), "Expected to return false for pattern 'te??t' with extra characters in path.");
    }

    @Test
    void testIsMatchWithEdgeCases() {
        // Test case where pattern is a wildcard with a leading separator and path is a single separator.
        assertTrue(antPathMatcher.isMatch("/*", "/"), "Expected to return true for pattern '/*' and path '/'.");
        // Test case where pattern is a double wildcard with a leading separator and path has multiple segments.
        assertTrue(antPathMatcher.isMatch("/**", "/some/path"), "Expected to return true for pattern '/**' and matching path.");
        // Test case where pattern is a wildcard with a leading separator but path has additional segments.
        assertFalse(antPathMatcher.isMatch("/*", "folder/test"), "Expected to return false for pattern '/*' with additional segments in path.");
        // Test case where pattern matches a path with a leading separator and a wildcard at the end.
        assertTrue(antPathMatcher.isMatch("/folder/*", "/folder/test"), "Expected to return true for pattern '/folder/*' and matching path.");
        // Test case where pattern is a double wildcard with a leading separator and path has multiple segments leading to the test.
        assertTrue(antPathMatcher.isMatch("/**/test", "/some/folder/test"), "Expected to return true for pattern '/**/test' and matching path.");
        // Test case where pattern matches a single segment but path has extra segments.
        assertFalse(antPathMatcher.isMatch("/*/test", "/folder/test/extra"), "Expected to return false for pattern '/*/test' with extra segments in path.");
    }

    @Test
    void testIsMatchWithExceptionScenarios() {
        // Test case where pattern is null. Expected to throw NullPointerException.
        assertThrows(NullPointerException.class, () -> {
            antPathMatcher.isMatch(null, "validPath");
        }, "Expected NullPointerException when pattern is null.");
        // Test case where path is null. Expected to throw NullPointerException.
        assertThrows(NullPointerException.class, () -> {
            antPathMatcher.isMatch("validPattern", null);
        }, "Expected NullPointerException when path is null.");
        // Test case where pattern is invalid but path is empty.
        assertFalse(antPathMatcher.isMatch("invalid*pattern", ""), "Expected to return false for invalid pattern and empty path.");
        // Test case where pattern is a wildcard with a leading separator and path is non-empty.
        assertTrue(antPathMatcher.isMatch("/*", "nonEmptyPath"), "Expected to return true for pattern '/*' and non-empty path.");
        // Test case where pattern is a wildcard with a leading separator and path is empty.
        assertFalse(antPathMatcher.isMatch("/*", ""), "Expected to return false for pattern '/*' and empty path.");
    }
}
```