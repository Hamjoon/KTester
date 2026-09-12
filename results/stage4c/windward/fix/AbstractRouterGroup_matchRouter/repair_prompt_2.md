


You are an experienced Java developer tasked with fixing a piece of code that contains execution errors. Your task is to resolve these issues after analyzing the code and its execution feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any logical errors.
2. Analyze the execution feedback to understand the specific errors and their locations within the code.
3. Apply necessary corrections to the code based on the feedback, and output the compelete correct code.
The Java code you need to fix is provided below:
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
import org.flmelody.util.AntPathMatcher;
import org.flmelody.core.exception.RouterMappingException;

@ExtendWith(MockitoExtension.class)
class AbstractRouterGroup_matchRouter_Test {

    private AbstractRouterGroup<?> abstractRouterGroup;

    @BeforeEach
    void setupBeforeEach()throws Exception {
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
    void testMatchRouter_whenPathStartsWithGroupPath()throws Exception {
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
    void testMatchRouter_whenPathDoesNotStartWithGroupPath()throws Exception {
        // Given
        String relativePath = "/nonExistentPath";
        String method = "GET";
        // When
        Object result = invokeMatchRouter(relativePath, method);
        // Then
        assertNull(result);
    }

    @Test
    void testMatchRouter_whenPathEndsWithSlash()throws Exception {
        // Given
        String relativePath = "/groupPath/";
        String method = "GET";
        // When
        Object result = invokeMatchRouter(relativePath, method);
        // Then
        assertNull(result);
    }

    @Test
    void testMatchRouter_whenPathMatchesRouterKeyWithoutVariables()throws Exception {
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
    void testMatchRouter_whenPathMatchesRouterKeyWithVariables()throws Exception {
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
    void testMatchRouter_whenMethodDoesNotMatch()throws Exception {
        // Given
        String relativePath = "/groupPath/resource";
        String method = "POST";
        // When
        Object result = invokeMatchRouter(relativePath, method);
        // Then
        assertNull(result);
    }

    @Test
    void testMatchRouter_whenMethodIsNotSupported()throws Exception {
        // Given
        String relativePath = "/groupPath/resource";
        String method = "PUT";
        // When
        Object result = invokeMatchRouter(relativePath, method);
        // Then
        assertNull(result);
    }

    @Test
    void testMatchRouter_whenPathHasMoreSegmentsThanRouterKey()throws Exception {
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
```

Corresponding execution feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended

╷
├─ JUnit Jupiter ✔
│  └─ AbstractRouterGroup_matchRouter_Test ✔
│     ├─ testMatchRouter_whenMethodDoesNotMatch() ✘ Cannot invoke "Object.getClass()" because "o" is null
│     ├─ testMatchRouter_whenPathEndsWithSlash() ✘ Cannot invoke "Object.getClass()" because "o" is null
│     ├─ testMatchRouter_whenPathMatchesRouterKeyWithVariables() ✘ Cannot invoke "Object.getClass()" because "o" is null
│     ├─ testMatchRouter_whenRelativePathIsNull() ✘ Cannot invoke "Object.getClass()" because "o" is null
│     ├─ testMatchRouter_whenMethodIsNull() ✘ Cannot invoke "Object.getClass()" because "o" is null
│     ├─ testMatchRouter_whenPathMatchesRouterKeyWithoutVariables() ✘ Cannot invoke "Object.getClass()" because "o" is null
│     ├─ testMatchRouter_whenPathStartsWithGroupPath() ✘ Cannot invoke "Object.getClass()" because "o" is null
│     ├─ testMatchRouter_whenPathHasMoreSegmentsThanRouterKey() ✘ Cannot invoke "Object.getClass()" because "o" is null
│     ├─ testMatchRouter_whenMethodIsInvalid() ✘ Cannot invoke "Object.getClass()" because "o" is null
│     ├─ testMatchRouter_whenPathDoesNotStartWithGroupPath() ✘ Cannot invoke "Object.getClass()" because "o" is null
│     └─ testMatchRouter_whenMethodIsNotSupported() ✘ Cannot invoke "Object.getClass()" because "o" is null
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Failures (11):
  JUnit Jupiter:AbstractRouterGroup_matchRouter_Test:testMatchRouter_whenMethodDoesNotMatch()
    MethodSource [className = 'org.flmelody.core.AbstractRouterGroup_matchRouter_Test', methodName = 'testMatchRouter_whenMethodDoesNotMatch', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "Object.getClass()" because "o" is null
       java.base/jdk.internal.reflect.UnsafeFieldAccessorImpl.ensureObj(UnsafeFieldAccessorImpl.java:57)
       java.base/jdk.internal.reflect.UnsafeQualifiedObjectFieldAccessorImpl.set(UnsafeQualifiedObjectFieldAccessorImpl.java:77)
       java.base/java.lang.reflect.Field.set(Field.java:799)
       org.flmelody.core.AbstractRouterGroup_matchRouter_Test.setRouters(AbstractRouterGroup_matchRouter_Test.java:51)
       org.flmelody.core.AbstractRouterGroup_matchRouter_Test$1.<init>(AbstractRouterGroup_matchRouter_Test.java:34)
       org.flmelody.core.AbstractRouterGroup_matchRouter_Test.setupBeforeEach(AbstractRouterGroup_matchRouter_Test.java:30)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       [...]
  JUnit Jupiter:AbstractRouterGroup_matchRouter_Test:testMatchRouter_whenPathEndsWithSlash()
    MethodSource [className = 'org.flmelody.core.AbstractRouterGroup_matchRouter_Test', methodName = 'testMatchRouter_whenPathEndsWithSlash', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "Object.getClass()" because "o" is null
       java.base/jdk.internal.reflect.UnsafeFieldAccessorImpl.ensureObj(UnsafeFieldAccessorImpl.java:57)
       java.base/jdk.internal.reflect.UnsafeQualifiedObjectFieldAccessorImpl.set(UnsafeQualifiedObjectFieldAccessorImpl.java:77)
       java.base/java.lang.reflect.Field.set(Field.java:799)
       org.flmelody.core.AbstractRouterGroup_matchRouter_Test.setRouters(AbstractRouterGroup_matchRouter_Test.java:51)
       org.flmelody.core.AbstractRouterGroup_matchRouter_Test$1.<init>(AbstractRouterGroup_matchRouter_Test.java:34)
       org.flmelody.core.AbstractRouterGroup_matchRouter_Test.setupBeforeEach(AbstractRouterGroup_matchRouter_Test.java:30)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       [...]
  JUnit Jupiter:AbstractRouterGroup_matchRouter_Test:testMatchRouter_whenPathMatchesRouterKeyWithVariables()
    MethodSource [className = 'org.flmelody.core.AbstractRouterGroup_matchRouter_Test', methodName = 'testMatchRouter_whenPathMatchesRouterKeyWithVariables', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "Object.getClass()" because "o" is null
       java.base/jdk.internal.reflect.UnsafeFieldAccessorImpl.ensureObj(UnsafeFieldAccessorImpl.java:57)
       java.base/jdk.internal.reflect.UnsafeQualifiedObjectFieldAccessorImpl.set(UnsafeQualifiedObjectFieldAccessorImpl.java:77)
       java.base/java.lang.reflect.Field.set(Field.java:799)
       org.flmelody.core.AbstractRouterGroup_matchRouter_Test.setRouters(AbstractRouterGroup_matchRouter_Test.java:51)
       org.flmelody.core.AbstractRouterGroup_matchRouter_Test$1.<init>(AbstractRouterGroup_matchRouter_Test.java:34)
       org.flmelody.core.AbstractRouterGroup_matchRouter_Test.setupBeforeEach(AbstractRouterGroup_matchRouter_Test.java:30)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       [...]
  JUnit Jupiter:AbstractRouterGroup_matchRouter_Test:testMatchRouter_whenRelativePathIsNull()
    MethodSource [className = 'org.flmelody.core.AbstractRouterGroup_matchRouter_Test', methodName = 'testMatchRouter_whenRelativePathIsNull', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "Object.getClass()" because "o" is null
       java.base/jdk.internal.reflect.UnsafeFieldAccessorImpl.ensureObj(UnsafeFieldAccessorImpl.java:57)
       java.base/jdk.internal.reflect.UnsafeQualifiedObjectFieldAccessorImpl.set(UnsafeQualifiedObjectFieldAccessorImpl.java:77)
       java.base/java.lang.reflect.Field.set(Field.java:799)
       org.flmelody.core.AbstractRouterGroup_matchRouter_Test.setRouters(AbstractRouterGroup_matchRouter_Test.java:51)
       org.flmelody.core.AbstractRouterGroup_matchRouter_Test$1.<init>(AbstractRouterGroup_matchRouter_Test.java:34)
       org.flmelody.core.AbstractRouterGroup_matchRouter_Test.setupBeforeEach(AbstractRouterGroup_matchRouter_Test.java:30)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       [...]
  JUnit Jupiter:AbstractRouterGroup_matchRouter_Test:testMatchRouter_whenMethodIsNull()
    MethodSource [className = 'org.flmelody.core.AbstractRouterGroup_matchRouter_Test', methodName = 'testMatchRouter_whenMethodIsNull', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "Object.getClass()" because "o" is null
       java.base/jdk.internal.reflect.UnsafeFieldAccessorImpl.ensureObj(UnsafeFieldAccessorImpl.java:57)
       java.base/jdk.internal.reflect.UnsafeQualifiedObjectFieldAccessorImpl.set(UnsafeQualifiedObjectFieldAccessorImpl.java:77)
       java.base/java.lang.reflect.Field.set(Field.java:799)
       org.flmelody.core.AbstractRouterGroup_matchRouter_Test.setRouters(AbstractRouterGroup_matchRouter_Test.java:51)
       org.flmelody.core.AbstractRouterGroup_matchRouter_Test$1.<init>(AbstractRouterGroup_matchRouter_Test.java:34)
       org.flmelody.core.AbstractRouterGroup_matchRouter_Test.setupBeforeEach(AbstractRouterGroup_matchRouter_Test.java:30)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       [...]
  JUnit Jupiter:AbstractRouterGroup_matchRouter_Test:testMatchRouter_whenPathMatchesRouterKeyWithoutVariables()
    MethodSource [className = 'org.flmelody.core.AbstractRouterGroup_matchRouter_Test', methodName = 'testMatchRouter_whenPathMatchesRouterKeyWithoutVariables', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "Object.getClass()" because "o" is null
       java.base/jdk.internal.reflect.UnsafeFieldAccessorImpl.ensureObj(UnsafeFieldAccessorImpl.java:57)
       java.base/jdk.internal.reflect.UnsafeQualifiedObjectFieldAccessorImpl.set(UnsafeQualifiedObjectFieldAccessorImpl.java:77)
       java.base/java.lang.reflect.Field.set(Field.java:799)
       org.flmelody.core.AbstractRouterGroup_matchRouter_Test.setRouters(AbstractRouterGroup_matchRouter_Test.java:51)
       org.flmelody.core.AbstractRouterGroup_matchRouter_Test$1.<init>(AbstractRouterGroup_matchRouter_Test.java:34)
       org.flmelody.core.AbstractRouterGroup_matchRouter_Test.setupBeforeEach(AbstractRouterGroup_matchRouter_Test.java:30)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       [...]
  JUnit Jupiter:AbstractRouterGroup_matchRouter_Test:testMatchRouter_whenPathStartsWithGroupPath()
    MethodSource [className = 'org.flmelody.core.AbstractRouterGroup_matchRouter_Test', methodName = 'testMatchRouter_whenPathStartsWithGroupPath', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "Object.getClass()" because "o" is null
       java.base/jdk.internal.reflect.UnsafeFieldAccessorImpl.ensureObj(UnsafeFieldAccessorImpl.java:57)
       java.base/jdk.internal.reflect.UnsafeQualifiedObjectFieldAccessorImpl.set(UnsafeQualifiedObjectFieldAccessorImpl.java:77)
       java.base/java.lang.reflect.Field.set(Field.java:799)
       org.flmelody.core.AbstractRouterGroup_matchRouter_Test.setRouters(AbstractRouterGroup_matchRouter_Test.java:51)
       org.flmelody.core.AbstractRouterGroup_matchRouter_Test$1.<init>(AbstractRouterGroup_matchRouter_Test.java:34)
       org.flmelody.core.AbstractRouterGroup_matchRouter_Test.setupBeforeEach(AbstractRouterGroup_matchRouter_Test.java:30)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       [...]
  JUnit Jupiter:AbstractRouterGroup_matchRouter_Test:testMatchRouter_whenPathHasMoreSegmentsThanRouterKey()
    MethodSource [className = 'org.flmelody.core.AbstractRouterGroup_matchRouter_Test', methodName = 'testMatchRouter_whenPathHasMoreSegmentsThanRouterKey', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "Object.getClass()" because "o" is null
       java.base/jdk.internal.reflect.UnsafeFieldAccessorImpl.ensureObj(UnsafeFieldAccessorImpl.java:57)
       java.base/jdk.internal.reflect.UnsafeQualifiedObjectFieldAccessorImpl.set(UnsafeQualifiedObjectFieldAccessorImpl.java:77)
       java.base/java.lang.reflect.Field.set(Field.java:799)
       org.flmelody.core.AbstractRouterGroup_matchRouter_Test.setRouters(AbstractRouterGroup_matchRouter_Test.java:51)
       org.flmelody.core.AbstractRouterGroup_matchRouter_Test$1.<init>(AbstractRouterGroup_matchRouter_Test.java:34)
       org.flmelody.core.AbstractRouterGroup_matchRouter_Test.setupBeforeEach(AbstractRouterGroup_matchRouter_Test.java:30)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       [...]
  JUnit Jupiter:AbstractRouterGroup_matchRouter_Test:testMatchRouter_whenMethodIsInvalid()
    MethodSource [className = 'org.flmelody.core.AbstractRouterGroup_matchRouter_Test', methodName = 'testMatchRouter_whenMethodIsInvalid', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "Object.getClass()" because "o" is null
       java.base/jdk.internal.reflect.UnsafeFieldAccessorImpl.ensureObj(UnsafeFieldAccessorImpl.java:57)
       java.base/jdk.internal.reflect.UnsafeQualifiedObjectFieldAccessorImpl.set(UnsafeQualifiedObjectFieldAccessorImpl.java:77)
       java.base/java.lang.reflect.Field.set(Field.java:799)
       org.flmelody.core.AbstractRouterGroup_matchRouter_Test.setRouters(AbstractRouterGroup_matchRouter_Test.java:51)
       org.flmelody.core.AbstractRouterGroup_matchRouter_Test$1.<init>(AbstractRouterGroup_matchRouter_Test.java:34)
       org.flmelody.core.AbstractRouterGroup_matchRouter_Test.setupBeforeEach(AbstractRouterGroup_matchRouter_Test.java:30)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       [...]
  JUnit Jupiter:AbstractRouterGroup_matchRouter_Test:testMatchRouter_whenPathDoesNotStartWithGroupPath()
    MethodSource [className = 'org.flmelody.core.AbstractRouterGroup_matchRouter_Test', methodName = 'testMatchRouter_whenPathDoesNotStartWithGroupPath', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "Object.getClass()" because "o" is null
       java.base/jdk.internal.reflect.UnsafeFieldAccessorImpl.ensureObj(UnsafeFieldAccessorImpl.java:57)
       java.base/jdk.internal.reflect.UnsafeQualifiedObjectFieldAccessorImpl.set(UnsafeQualifiedObjectFieldAccessorImpl.java:77)
       java.base/java.lang.reflect.Field.set(Field.java:799)
       org.flmelody.core.AbstractRouterGroup_matchRouter_Test.setRouters(AbstractRouterGroup_matchRouter_Test.java:51)
       org.flmelody.core.AbstractRouterGroup_matchRouter_Test$1.<init>(AbstractRouterGroup_matchRouter_Test.java:34)
       org.flmelody.core.AbstractRouterGroup_matchRouter_Test.setupBeforeEach(AbstractRouterGroup_matchRouter_Test.java:30)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       [...]
  JUnit Jupiter:AbstractRouterGroup_matchRouter_Test:testMatchRouter_whenMethodIsNotSupported()
    MethodSource [className = 'org.flmelody.core.AbstractRouterGroup_matchRouter_Test', methodName = 'testMatchRouter_whenMethodIsNotSupported', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "Object.getClass()" because "o" is null
       java.base/jdk.internal.reflect.UnsafeFieldAccessorImpl.ensureObj(UnsafeFieldAccessorImpl.java:57)
       java.base/jdk.internal.reflect.UnsafeQualifiedObjectFieldAccessorImpl.set(UnsafeQualifiedObjectFieldAccessorImpl.java:77)
       java.base/java.lang.reflect.Field.set(Field.java:799)
       org.flmelody.core.AbstractRouterGroup_matchRouter_Test.setRouters(AbstractRouterGroup_matchRouter_Test.java:51)
       org.flmelody.core.AbstractRouterGroup_matchRouter_Test$1.<init>(AbstractRouterGroup_matchRouter_Test.java:34)
       org.flmelody.core.AbstractRouterGroup_matchRouter_Test.setupBeforeEach(AbstractRouterGroup_matchRouter_Test.java:30)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       [...]

Test run finished after 387 ms
[         4 containers found      ]
[         0 containers skipped    ]
[         4 containers started    ]
[         0 containers aborted    ]
[         4 containers successful ]
[         0 containers failed     ]
[        11 tests found           ]
[         0 tests skipped         ]
[        11 tests started         ]
[         0 tests aborted         ]
[         0 tests successful      ]
[        11 tests failed          ]


```


And you can use the following related information to assist you in your analysis and correction:

api document of class org.flmelody.core.AbstractRouterGroup: 

 * @author esotericman
 

return type: 
R

dependent classes: 
class `org.flmelody.util.UrlUtil`:
	dependent fields:
		java.lang.String org.flmelody.util.UrlUtil.SLASH;
	related functions:
		method `public static String buildUrl(String[])` returns `java.lang.String`, related with `org.flmelody.core.AbstractRouterGroup.matchRouter(String, String)`;
class `org.flmelody.core.AbstractRouterGroup`:
	dependent fields:
		java.util.Map<java.lang.String, java.lang.Boolean> org.flmelody.core.AbstractRouterGroup.matchedRouter;
		java.lang.String org.flmelody.core.AbstractRouterGroup.groupPath;
		org.flmelody.util.AntPathMatcher org.flmelody.core.AbstractRouterGroup.antPathMatcher;
		boolean org.flmelody.core.AbstractRouterGroup.resourceRouter;
		java.util.Map<java.lang.String, java.util.Map<java.lang.String, ? super java.lang.Object>> org.flmelody.core.AbstractRouterGroup.routers;
	related functions:
		method `private Map<String, Object> checkPlaceholder(String)` returns `java.util.Map<java.lang.String, java.lang.Object>`, related with `org.flmelody.core.AbstractRouterGroup.matchRouter(String, String)`;
		method `public RouterGroup<M> ws(String, Consumer<WebSocketWindwardContext>)` returns `org.flmelody.core.RouterGroup<M>`, related with `org.flmelody.core.AbstractRouterGroup.matchRouter(String, String)`;
		method `public RouterGroup<M> resources(String, String[])` returns `org.flmelody.core.RouterGroup<M>`, related with `org.flmelody.core.AbstractRouterGroup.matchRouter(String, String)`;
		method `private void registerRouter(String, String, I, Class<? extends WindwardContext>)` returns `void`, related with `org.flmelody.core.AbstractRouterGroup.matchRouter(String, String)`;
		method `public RouterGroup<M> get(String, Supplier<R>)` returns `org.flmelody.core.RouterGroup<M>`, related with `org.flmelody.core.AbstractRouterGroup.matchRouter(String, String)`;
		method `protected void setGroupPath(String)` returns `void`, related with `org.flmelody.core.AbstractRouterGroup.matchRouter(String, String)`;
class `org.flmelody.core.HttpMethod`:
	dependent fields:
		org.flmelody.core.HttpMethod org.flmelody.core.HttpMethod.GET;
	
class `org.flmelody.core.plugin.resource.FixedStaticResourcePlugin`:
	related functions:
		method `protected String findResource(String, StaticResource)` returns `java.lang.String`, related with `org.flmelody.core.AbstractRouterGroup.matchRouter(String, String)`;
class `org.flmelody.core.netty.handler.HttpServerHandler`:
	related functions:
		method `private Map<String, List<String>> prepareHeaders(HttpHeaders)` returns `java.util.Map<java.lang.String, java.util.List<java.lang.String>>`, related with `org.flmelody.core.AbstractRouterGroup.matchRouter(String, String)`;



Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 
