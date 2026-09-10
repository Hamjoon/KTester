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
        assertTrue(antPathMatcher.isMatch("", ""), "Expected to return true for both empty pattern and path.");
        assertFalse(antPathMatcher.isMatch("/*", ""), "Expected to return false for pattern '/*' and empty path.");
        assertTrue(antPathMatcher.isMatch("*", "anyPath"), "Expected to return true for pattern '*' and non-empty path.");
        assertTrue(antPathMatcher.isMatch("test", "test"), "Expected to return true for exact match.");
        assertFalse(antPathMatcher.isMatch("test", "testing"), "Expected to return false for non-matching pattern and path.");
        assertTrue(antPathMatcher.isMatch("test/*", "test/folder"), "Expected to return true for pattern 'test/*' with matching path.");
        assertTrue(antPathMatcher.isMatch("test/*", "test/"), "Expected to return true for pattern 'test/*' with path ending with separator.");
        assertFalse(antPathMatcher.isMatch("test/*", "testing/folder"), "Expected to return false for differing prefixes.");
    }

    @Test
    void testIsMatchWithWildcardFunctionality() {
        assertTrue(antPathMatcher.isMatch("*/", "folder/"), "Expected to return true for pattern '*/' and path 'folder/'.");
        assertTrue(antPathMatcher.isMatch("*/file.txt", "folder/file.txt"), "Expected to return true for pattern '*/file.txt' and matching path.");
        assertFalse(antPathMatcher.isMatch("file.txt", "folder/file.txt"), "Expected to return false for non-matching pattern and path.");
        assertTrue(antPathMatcher.isMatch("**", "any/path/to/somewhere"), "Expected to return true for double wildcard '**'.");
        assertTrue(antPathMatcher.isMatch("*/**/file.txt", "folder/subfolder/file.txt"), "Expected to return true for pattern '*/**/file.txt' and matching path.");
        assertTrue(antPathMatcher.isMatch("**/file.txt", "file.txt"), "Expected to return true for pattern '**/file.txt' and matching path.");
    }

    @Test
    void testIsMatchWithQuestionMarkFunctionality() {
        assertTrue(antPathMatcher.isMatch("te?t", "test"), "Expected to return true for pattern 'te?t' and matching path.");
        assertTrue(antPathMatcher.isMatch("te?t", "teat"), "Expected to return true for pattern 'te?t' with matching path.");
        assertFalse(antPathMatcher.isMatch("te?t", "te"), "Expected to return false for pattern 'te?t' and short path.");
        assertTrue(antPathMatcher.isMatch("te??t", "text"), "Expected to return true for pattern 'te??t' with matching path.");
        assertTrue(antPathMatcher.isMatch("te??t", "teat"), "Expected to return true for pattern 'te??t' and matching path.");
        assertFalse(antPathMatcher.isMatch("te??t", "testt"), "Expected to return false for pattern 'te??t' with extra characters in path.");
    }

    @Test
    void testIsMatchWithEdgeCases() {
        assertTrue(antPathMatcher.isMatch("/*", "/"), "Expected to return true for pattern '/*' and path '/'.");
        assertTrue(antPathMatcher.isMatch("/**", "/some/path"), "Expected to return true for pattern '/**' and matching path.");
        assertFalse(antPathMatcher.isMatch("/*", "folder/test"), "Expected to return false for pattern '/*' with additional segments in path.");
        assertTrue(antPathMatcher.isMatch("/folder/*", "/folder/test"), "Expected to return true for pattern '/folder/*' and matching path.");
        assertTrue(antPathMatcher.isMatch("/**/test", "/some/folder/test"), "Expected to return true for pattern '/**/test' and matching path.");
        assertFalse(antPathMatcher.isMatch("/*/test", "/folder/test/extra"), "Expected to return false for pattern '/*/test' with extra segments in path.");
    }

    @Test
    void testIsMatchWithExceptionScenarios() {
        assertThrows(NullPointerException.class, () -> {
            antPathMatcher.isMatch(null, "validPath");
        }, "Expected NullPointerException when pattern is null.");
        assertThrows(NullPointerException.class, () -> {
            antPathMatcher.isMatch("validPattern", null);
        }, "Expected NullPointerException when path is null.");
        assertFalse(antPathMatcher.isMatch("invalid*pattern", ""), "Expected to return false for invalid pattern and empty path.");
        assertTrue(antPathMatcher.isMatch("/*", "nonEmptyPath"), "Expected to return true for pattern '/*' and non-empty path.");
        assertFalse(antPathMatcher.isMatch("/*", ""), "Expected to return false for pattern '/*' and empty path.");
    }
}
