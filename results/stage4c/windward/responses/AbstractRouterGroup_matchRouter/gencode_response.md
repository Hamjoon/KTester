```java
package org.flmelody.core;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.LinkedHashMap;
import java.util.Collections;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@ExtendWith(MockitoExtension.class)
class AbstractRouterGroup_matchRouter_Test {

    private AbstractRouterGroup<?> abstractRouterGroup;

    @BeforeAll
    static void setupBeforeAll() {
        // Initialize resources needed for all tests
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the AbstractRouterGroup with mock dependencies
        abstractRouterGroup = new AbstractRouterGroup<Object>() {
            {
                this.groupPath = "/groupPath";
                this.routers = Collections.synchronizedMap(new LinkedHashMap<>(2 << 3));
                this.antPathMatcher = mock(AntPathMatcher.class);
                this.matchedRouter = new ConcurrentHashMap<>();
                this.resourceRouter = true;
            }
        };
    }

    @AfterEach
    void teardownAfterEach() {
        // Clean up resources after each test
    }

    @AfterAll
    static void teardownAfterAll() {
        // Clean up resources after all tests
    }

    @Test
    void testMatchRouter_whenPathStartsWithGroupPath() {
        // Given
        String relativePath = "/groupPath/samplePath";
        String method = "GET";
        Map<String, Map<String, Object>> routers = Collections.synchronizedMap(new LinkedHashMap<>(2 << 3));
        routers.put(relativePath, Map.of(method, new Object()));
        // Reflection or direct access to set routers for test
        // abstractRouterGroup.setRouters(routers);

        // When
        Object result = abstractRouterGroup.matchRouter(relativePath, method);

        // Then
        assertNotNull(result);
    }

    @Test
    void testMatchRouter_whenPathDoesNotStartWithGroupPath() {
        // Given
        String relativePath = "/nonExistentPath";
        String method = "GET";

        // When
        Object result = abstractRouterGroup.matchRouter(relativePath, method);

        // Then
        assertNull(result);
    }

    @Test
    void testMatchRouter_whenPathEndsWithSlash() {
        // Given
        String relativePath = "/groupPath/";
        String method = "GET";

        // When
        Object result = abstractRouterGroup.matchRouter(relativePath, method);

        // Then
        assertNull(result);
    }

    @Test
    void testMatchRouter_whenPathMatchesRouterKeyWithoutVariables() {
        // Given
        String relativePath = "/groupPath/resource";
        String method = "GET";
        Object expectedFunctionMetaInfo = new Object();
        Map<String, Map<String, Object>> routers = Collections.synchronizedMap(new LinkedHashMap<>(2 << 3));
        routers.put(relativePath, Map.of(method, expectedFunctionMetaInfo));
        // Reflection or direct access to set routers for test
        // abstractRouterGroup.setRouters(routers);

        // When
        Object result = abstractRouterGroup.matchRouter(relativePath, method);

        // Then
        assertEquals(expectedFunctionMetaInfo, result);
    }

    @Test
    void testMatchRouter_whenPathMatchesRouterKeyWithVariables() {
        // Given
        String relativePath = "/groupPath/resource/123";
        String method = "GET";
        FunctionMetaInfo<?> expectedFunctionMetaInfo = mock(FunctionMetaInfo.class);
        Map<String, Object> pathVariables = new ConcurrentHashMap<>();
        when(expectedFunctionMetaInfo.getPathVariables()).thenReturn(pathVariables);
        
        Map<String, Map<String, Object>> routers = Collections.synchronizedMap(new LinkedHashMap<>(2 << 3));
        routers.put("/groupPath/resource/{id}", Map.of(method, expectedFunctionMetaInfo));
        // Reflection or direct access to set routers for test
        // abstractRouterGroup.setRouters(routers);

        // When
        Object result = abstractRouterGroup.matchRouter(relativePath, method);

        // Then
        assertEquals(expectedFunctionMetaInfo, result);
        assertEquals("123", pathVariables.get("id"));
    }

    @Test
    void testMatchRouter_whenMethodDoesNotMatch() {
        // Given
        String relativePath = "/groupPath/resource";
        String method = "POST";

        // When
        Object result = abstractRouterGroup.matchRouter(relativePath, method);

        // Then
        assertNull(result);
    }

    @Test
    void testMatchRouter_whenMethodIsNotSupported() {
        // Given
        String relativePath = "/groupPath/resource";
        String method = "PUT";

        // When
        Object result = abstractRouterGroup.matchRouter(relativePath, method);

        // Then
        assertNull(result);
    }

    @Test
    void testMatchRouter_whenPathHasMoreSegmentsThanRouterKey() {
        // Given
        String relativePath = "/groupPath/resource/123/extra";
        String method = "GET";

        // When
        Object result = abstractRouterGroup.matchRouter(relativePath, method);

        // Then
        assertNull(result);
    }

    @Test
    void testMatchRouter_whenRelativePathIsNull() {
        // Given
        String relativePath = null;
        String method = "GET";

        // When & Then
        assertThrows(NullPointerException.class, () -> abstractRouterGroup.matchRouter(relativePath, method));
    }

    @Test
    void testMatchRouter_whenMethodIsNull() {
        // Given
        String relativePath = "/groupPath/resource";
        String method = null;

        // When & Then
        assertThrows(NullPointerException.class, () -> abstractRouterGroup.matchRouter(relativePath, method));
    }

    @Test
    void testMatchRouter_whenMethodIsInvalid() {
        // Given
        String relativePath = "/groupPath/resource";
        String method = "INVALID_METHOD";

        // When & Then
        assertThrows(RouterMappingException.class, () -> abstractRouterGroup.matchRouter(relativePath, method));
    }
}
```
