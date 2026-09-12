```java
package org.flmelody.util;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AntPathMatcher_isMatch_Test {

    private AntPathMatcher antPathMatcher;

    @BeforeAll
    static void setupBeforeAll() {
        // Any global setup can be done here if needed
    }

    @BeforeEach
    void setupBeforeEach() {
        // Setup the AntPathMatcher with necessary configurations
        AntPathMatcher.Builder builder = new AntPathMatcher.Builder();
        antPathMatcher = builder
                .withPathSeparator('/')
                .withIgnoreCase()
                .withTrimTokens()
                .build();
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup resources if necessary
    }

    @AfterAll
    static void teardownAfterAll() {
        // Any global teardown can be done here if needed
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
```