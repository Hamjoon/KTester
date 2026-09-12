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
import org.flmelody.util.AntPathMatcher;
import org.flmelody.core.exception.RouterMappingException;

@ExtendWith(MockitoExtension.class)
class AbstractRouterGroup_matchRouter_Test {

    private AbstractRouterGroup<?> abstractRouterGroup;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the AbstractRouterGroup with mock dependencies
        abstractRouterGroup = new AbstractRouterGroup<Object>(new Object()) {

            {
                setGroupPath("/groupPath");
                setRouters(Collections.synchronizedMap(new LinkedHashMap<>(2 << 3)));
                setField("antPathMatcher", mock(AntPathMatcher.class));
                setField("matchedRouter", new ConcurrentHashMap<>());
                setField("resourceRouter", true);
            }
        };
    }

    private Object invokeMatchRouter(String relativePath, String method) throws Exception {
        java.lang.reflect.Method methodToTest = AbstractRouterGroup.class.getDeclaredMethod("matchRouter", String.class, String.class);
        methodToTest.setAccessible(true);
        return methodToTest.invoke(abstractRouterGroup, relativePath, method);
    }

    private void setRouters(Map<String, Map<String, Object>> routers) throws Exception {
        java.lang.reflect.Field routersField = AbstractRouterGroup.class.getDeclaredField("routers");
        routersField.setAccessible(true);
        routersField.set(abstractRouterGroup, routers);
    }

    private void setField(String fieldName, Object value) throws Exception {
        java.lang.reflect.Field field = AbstractRouterGroup.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(abstractRouterGroup, value);
    }

    @Test
    void testMatchRouter_whenPathStartsWithGroupPath() {
        // Given
        String relativePath = "/groupPath/samplePath";
        String method = "GET";
        Map<String, Map<String, Object>> routers = Collections.synchronizedMap(new LinkedHashMap<>(2 << 3));
        routers.put(relativePath, Map.of(method, new Object()));
        // Use reflection to set routers for test
        setRouters(routers);
        // When
        Object result = invokeMatchRouter(relativePath, method);
        // Then
        assertNotNull(result);
    }

    @Test
    void testMatchRouter_whenPathDoesNotStartWithGroupPath() {
        // Given
        String relativePath = "/nonExistentPath";
        String method = "GET";
        // When
        Object result = invokeMatchRouter(relativePath, method);
        // Then
        assertNull(result);
    }

    @Test
    void testMatchRouter_whenPathEndsWithSlash() {
        // Given
        String relativePath = "/groupPath/";
        String method = "GET";
        // When
        Object result = invokeMatchRouter(relativePath, method);
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
        // Use reflection to set routers for test
        setRouters(routers);
        // When
        Object result = invokeMatchRouter(relativePath, method);
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
        // Use reflection to set routers for test
        setRouters(routers);
        // When
        Object result = invokeMatchRouter(relativePath, method);
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
        Object result = invokeMatchRouter(relativePath, method);
        // Then
        assertNull(result);
    }

    @Test
    void testMatchRouter_whenMethodIsNotSupported() {
        // Given
        String relativePath = "/groupPath/resource";
        String method = "PUT";
        // When
        Object result = invokeMatchRouter(relativePath, method);
        // Then
        assertNull(result);
    }

    @Test
    void testMatchRouter_whenPathHasMoreSegmentsThanRouterKey() {
        // Given
        String relativePath = "/groupPath/resource/123/extra";
        String method = "GET";
        // When
        Object result = invokeMatchRouter(relativePath, method);
        // Then
        assertNull(result);
    }

    @Test
    void testMatchRouter_whenRelativePathIsNull() {
        // Given
        String relativePath = null;
        String method = "GET";
        // When & Then
        assertThrows(NullPointerException.class, () -> {
            try {
                invokeMatchRouter(relativePath, method);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    void testMatchRouter_whenMethodIsNull() {
        // Given
        String relativePath = "/groupPath/resource";
        String method = null;
        // When & Then
        assertThrows(NullPointerException.class, () -> {
            try {
                invokeMatchRouter(relativePath, method);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    void testMatchRouter_whenMethodIsInvalid() {
        // Given
        String relativePath = "/groupPath/resource";
        String method = "INVALID_METHOD";
        // When & Then
        assertThrows(RouterMappingException.class, () -> {
            try {
                invokeMatchRouter(relativePath, method);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
