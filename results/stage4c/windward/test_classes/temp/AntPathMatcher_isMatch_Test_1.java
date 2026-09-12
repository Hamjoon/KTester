package org.flmelody.util;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class AntPathMatcher_isMatch_Test {

    private AntPathMatcher antPathMatcher;

    @Nested
    class IsMatchMethodTestCases {

        @Test
        void testEmptyPatternAndPath() {
            // Both pattern and path are empty strings, should return true as they match.
            assertTrue(antPathMatcher.isMatch("", ""));
        }

        @Test
        void testEmptyPatternNonEmptyPath() {
            // Pattern is empty and path is not, should return false as they do not match.
            assertFalse(antPathMatcher.isMatch("", "somePath"));
        }

        @Test
        void testPatternStartsWithPathSeparatorAndEmptyPath() {
            // Pattern starts with pathSeparator and path is empty, should return true if matchStart is true.
            assertTrue(antPathMatcher.isMatch("/", ""));
        }

        @Test
        void testPatternWithSingleAsteriskAndEmptyPath() {
            // Pattern is '/*' and path is empty, should return false if matchStart is false.
            assertFalse(antPathMatcher.isMatch("/*", ""));
        }

        @Test
        void testSingleAsteriskPattern() {
            // Pattern is '*' and path is 'somePath', should return true as '*' matches any sequence of characters.
            assertTrue(antPathMatcher.isMatch("*", "somePath"));
        }

        @Test
        void testPatternWithLeadingAsterisk() {
            // Pattern is '*some' and path is 'anysome', should return true as '*' can match 'any'.
            assertTrue(antPathMatcher.isMatch("*some", "anysome"));
        }

        @Test
        void testPatternWithTrailingAsterisk() {
            // Pattern is 'some*' and path is 'somePath', should return true as '*' can match 'Path'.
            assertTrue(antPathMatcher.isMatch("some*", "somePath"));
        }

        @Test
        void testPatternWithSingleQuestionMark() {
            // Pattern is 'so?e' and path is 'some', should return true as '?' matches any single character.
            assertTrue(antPathMatcher.isMatch("so?e", "some"));
        }

        @Test
        void testPatternWithSingleQuestionMarkForDifferentChar() {
            // Pattern is 'so?e' and path is 'sore', should return true as '?' matches 'r'.
            assertTrue(antPathMatcher.isMatch("so?e", "sore"));
        }

        @Test
        void testPatternWithSingleQuestionMarkAndRepeatedChar() {
            // Pattern is 'so?e' and path is 'sooe', should return true as '?' matches 'o'.
            assertTrue(antPathMatcher.isMatch("so?e", "sooe"));
        }

        @Test
        void testPatternWithSingleQuestionMarkAndShortPath() {
            // Pattern is 'so?e' and path is 'soe', should return false as '?' cannot match zero characters.
            assertFalse(antPathMatcher.isMatch("so?e", "soe"));
        }

        @Test
        void testDoubleAsteriskPattern() {
            // Pattern is '**' and path is 'anyPath', should return true as '**' matches any sequence of characters.
            assertTrue(antPathMatcher.isMatch("**", "anyPath"));
        }
    }

    @Nested
    class IsMatchMethodAdditionalTestCases {

        @Test
        void testDoubleAsteriskInMiddle() {
            // Pattern is 'some/**' and path is 'some/any/path', should return true as '**' matches any sequence of characters including path separators.
            assertTrue(antPathMatcher.isMatch("some/**", "some/any/path"));
        }

        @Test
        void testSingleAsteriskWithTrailingSlash() {
            // Pattern is 'some/*' and path is 'some/', should return true since '*' can match zero or more characters.
            assertTrue(antPathMatcher.isMatch("some/*", "some/"));
        }

        @Test
        void testDoubleAsteriskWithPathSegment() {
            // Pattern is 'some/**/path' and path is 'some/any/deep/path', should return true as '**' can match any path segment.
            assertTrue(antPathMatcher.isMatch("some/**/path", "some/any/deep/path"));
        }

        @Test
        void testSingleAsteriskWithPathSegment() {
            // Pattern is 'some/*/path' and path is 'some/any/path', should return true as '*' can match a single path segment.
            assertTrue(antPathMatcher.isMatch("some/*/path", "some/any/path"));
        }

        @Test
        void testSingleAsteriskWithMissingSegment() {
            // Pattern is 'some/*/path' and path is 'some/path', should return false as '*' should match exactly one segment.
            assertFalse(antPathMatcher.isMatch("some/*/path", "some/path"));
        }

        @Test
        void testAsteriskWithLeadingSlash() {
            // Pattern is '*/path' and path is '/path', should return false because '*' cannot match an empty segment when path starts with a separator.
            assertFalse(antPathMatcher.isMatch("*/path", "/path"));
        }

        @Test
        void testDoubleAsteriskAtEnd() {
            // Pattern is 'some/**' and path is 'some', should return true as '**' can match zero or more characters.
            assertTrue(antPathMatcher.isMatch("some/**", "some"));
        }

        @Test
        void testDoubleAsteriskWithTrailingSlash() {
            // Pattern is 'some/**/' and path is 'some/', should return true as '**' can match zero or more characters including path separators.
            assertTrue(antPathMatcher.isMatch("some/**/", "some/"));
        }

        @Test
        void testDoubleAsteriskWithDeeplyNestedPath() {
            // Pattern is 'some/path/**' and path is 'some/path/deeply/nested', should return true as '**' matches any sequence of characters following 'some/path/'.
            assertTrue(antPathMatcher.isMatch("some/path/**", "some/path/deeply/nested"));
        }

        @Test
        void testSingleAsteriskWithDeeplyNestedPath() {
            // Pattern is 'some/path/*' and path is 'some/path/deeply/nested', should return false as '*' only matches a single segment.
            assertFalse(antPathMatcher.isMatch("some/path/*", "some/path/deeply/nested"));
        }
    }

    @Nested
    class IsMatchMethodExceptionTestCases {

        @Test
        void testNullPattern() {
            // Pattern is null, should throw NullPointerException as the pattern cannot be null.
            assertThrows(NullPointerException.class, () -> antPathMatcher.isMatch(null, "somePath"));
        }

        @Test
        void testNullPath() {
            // Path is null, should throw NullPointerException as the path cannot be null.
            assertThrows(NullPointerException.class, () -> antPathMatcher.isMatch("somePattern", null));
        }
    }

    @BeforeEach
    void setupBeforeEach() {
        // Setup the AntPathMatcher with necessary configurations
        AntPathMatcher.Builder builder = AntPathMatcher.newBuild();
        antPathMatcher = builder.withPathSeparator('/').withMatchStart().build();
    }

    @Test
    void testIsMatchWithAsterisk() {
        assertTrue(antPathMatcher.isMatch("/api/**", "/api/test"));
        assertTrue(antPathMatcher.isMatch("/**", "/api/test"));
        assertTrue(antPathMatcher.isMatch("/**/test", "/api/test"));
    }

    @Test
    void testIsMatchWithSpecificPattern() {
        assertTrue(antPathMatcher.isMatch("/api/**.js", "/api/test.js"));
        assertFalse(antPathMatcher.isMatch("/api/**.js", "/api/test"));
        assertTrue(antPathMatcher.isMatch("/api/**.js", "/api/test/a.js"));
        assertFalse(antPathMatcher.isMatch("/static/**.js", "/api/test/a.js"));
    }
}
