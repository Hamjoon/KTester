```java
package org.flmelody.core;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class AbstractRouterGroup_matchRouter_Test {
    private AbstractRouterGroup<Windward> routerGroup;
    private Windward mockManager;
    private String groupPath = "/groupPath";

    @BeforeAll
    static void setupBeforeAll() {
        // Any global setup if needed
    }

    @BeforeEach
    void setupBeforeEach() {
        mockManager = mock(Windward.class); // Mock the manager
        routerGroup = new TestRouterGroup(mockManager, groupPath); // Create an instance of the target class
        // Setting up routers for testing
        setupRouters();
    }

    @AfterEach
    void teardownAfterEach() {
        // Clean up resources if needed
    }

    @AfterAll
    static void teardownAfterAll() {
        // Final cleanup if needed
    }

    private void setupRouters() {
        // Mocking FunctionMetaInfo for testing
        FunctionMetaInfo<Object> functionMetaInfo = mock(FunctionMetaInfo.class);
        Map<String, Object> pathVariables = new HashMap<>();
        when(functionMetaInfo.getPathVariables()).thenReturn(pathVariables);

        // Adding routes to the routers map
        Map<String, Object> getMethodMap = new HashMap<>();
        getMethodMap.put("GET", functionMetaInfo);
        routerGroup.routers.put("/groupPath/test", getMethodMap);
        routerGroup.routers.put("/groupPath/test/{id}", getMethodMap);
        routerGroup.routers.put("/groupPath/test/{id}/details", getMethodMap);
    }

    @Test
    void testMatchRouter_whenPathDoesNotStartWithGroupPath_shouldReturnNull() {
        // Given
        String relativePath = "/test/path";
        String method = "GET";

        // When
        FunctionMetaInfo<?> result = routerGroup.matchRouter(relativePath, method);

        // Then
        assertNull(result, "Expected null when relativePath does not start with groupPath.");
    }

    @Test
    void testMatchRouter_whenPathEndsWithSlash_shouldReturnModifiedPath() {
        // Given
        String relativePath = "/groupPath/test/";
        String method = "GET";

        // When
        FunctionMetaInfo<?> result = routerGroup.matchRouter(relativePath, method);

        // Then
        assertNotNull(result, "Expected FunctionMetaInfo instance after removing trailing slash.");
    }

    @Test
    void testMatchRouter_whenMethodNotAvailable_shouldReturnNull() {
        // Given
        String relativePath = "/groupPath/test";
        String method = "POST";

        // When
        FunctionMetaInfo<?> result = routerGroup.matchRouter(relativePath, method);

        // Then
        assertNull(result, "Expected null when method is not available.");
    }

    @Test
    void testMatchRouter_whenPathMatchesRouterWithVariable_shouldReturnFunctionMetaInfo() {
        // Given
        String relativePath = "/groupPath/test/{id}";
        String method = "GET";

        // When
        FunctionMetaInfo<?> result = routerGroup.matchRouter(relativePath, method);

        // Then
        assertNotNull(result, "Expected FunctionMetaInfo instance when path matches a router with variable.");
    }

    @Test
    void testMatchRouter_whenPathMatchesRegexAndPopulatesVariables_shouldReturnFunctionMetaInfo() {
        // Given
        String relativePath = "/groupPath/test/1";
        String method = "GET";

        // When
        FunctionMetaInfo<?> result = routerGroup.matchRouter(relativePath, method);

        // Then
        assertNotNull(result, "Expected FunctionMetaInfo with populated path variables.");
    }

    @Test
    void testMatchRouter_whenPathMatchesDirectRouterKey_shouldReturnFunctionMetaInfo() {
        // Given
        String relativePath = "/groupPath/test";
        String method = "GET";

        // When
        FunctionMetaInfo<?> result = routerGroup.matchRouter(relativePath, method);

        // Then
        assertNotNull(result, "Expected FunctionMetaInfo instance for direct router key match.");
    }

    @Test
    void testMatchRouter_whenPathDoesNotMatchAnyRouterKeys_shouldReturnNull() {
        // Given
        String relativePath = "/groupPath/unknown";
        String method = "GET";

        // When
        FunctionMetaInfo<?> result = routerGroup.matchRouter(relativePath, method);

        // Then
        assertNull(result, "Expected null when relativePath does not match any router keys.");
    }

    @Test
    void testMatchRouter_whenPathEndsWithSlash_shouldReturnFunctionMetaInfo() {
        // Given
        String relativePath = "/groupPath/test/";
        String method = "GET";

        // When
        FunctionMetaInfo<?> result = routerGroup.matchRouter(relativePath, method);

        // Then
        assertNotNull(result, "Expected FunctionMetaInfo instance after removing trailing slash.");
    }

    @Test
    void testMatchRouter_whenPathMatchesRouterWithVariableButMethodNotAvailable_shouldReturnNull() {
        // Given
        String relativePath = "/groupPath/test/{id}";
        String method = "PUT";

        // When
        FunctionMetaInfo<?> result = routerGroup.matchRouter(relativePath, method);

        // Then
        assertNull(result, "Expected null when method is not available for path variable.");
    }

    @Test
    void testMatchRouter_whenPathMatchesRegexButMethodNotAvailable_shouldReturnNull() {
        // Given
        String relativePath = "/groupPath/test/1";
        String method = "POST";

        // When
        FunctionMetaInfo<?> result = routerGroup.matchRouter(relativePath, method);

        // Then
        assertNull(result, "Expected null when method is not available for regex pattern.");
    }

    @Test
    void testMatchRouter_whenPathMatchesRouterWithAdditionalSegments_shouldReturnFunctionMetaInfo() {
        // Given
        String relativePath = "/groupPath/test/{id}/details";
        String method = "GET";

        // When
        FunctionMetaInfo<?> result = routerGroup.matchRouter(relativePath, method);

        // Then
        assertNotNull(result, "Expected FunctionMetaInfo instance for path with additional segments.");
    }

    @Test
    void testMatchRouter_whenPathMatchesRouterWithAdditionalSegmentsButMethodNotAvailable_shouldReturnNull() {
        // Given
        String relativePath = "/groupPath/test/{id}/details";
        String method = "DELETE";

        // When
        FunctionMetaInfo<?> result = routerGroup.matchRouter(relativePath, method);

        // Then
        assertNull(result, "Expected null when method is not available for path with additional segments.");
    }

    @Test
    void testMatchRouter_whenPathMatchesRegexWithAdditionalSegments_shouldReturnFunctionMetaInfo() {
        // Given
        String relativePath = "/groupPath/test/1/details";
        String method = "GET";

        // When
        FunctionMetaInfo<?> result = routerGroup.matchRouter(relativePath, method);

        // Then
        assertNotNull(result, "Expected FunctionMetaInfo with populated path variables for additional segments.");
    }

    @Test
    void testMatchRouter_whenPathIsJustGroupPathWithTrailingSlash_shouldReturnNull() {
        // Given
        String relativePath = "/groupPath/";
        String method = "GET";

        // When
        FunctionMetaInfo<?> result = routerGroup.matchRouter(relativePath, method);

        // Then
        assertNull(result, "Expected null when relativePath is just the groupPath with a trailing slash.");
    }

    @Test
    void testMatchRouter_whenPathMatchesRouterWithMultiplePathVariables_shouldReturnFunctionMetaInfo() {
        // Given
        String relativePath = "/groupPath/test/{id}/extra/{extraId}";
        String method = "GET";

        // When
        FunctionMetaInfo<?> result = routerGroup.matchRouter(relativePath, method);

        // Then
        assertNotNull(result, "Expected FunctionMetaInfo instance for path with multiple variables.");
    }

    // A concrete implementation of AbstractRouterGroup for testing purposes
    private static class TestRouterGroup extends AbstractRouterGroup<Windward> {
        protected TestRouterGroup(Windward manager, String groupPath) {
            super(manager, groupPath);
        }
        
        // Override any methods if needed for testing
    }
}
```