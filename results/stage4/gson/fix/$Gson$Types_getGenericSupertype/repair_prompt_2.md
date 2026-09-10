


You are an experienced Java developer tasked with fixing a piece of code that contains execution errors. Your task is to resolve these issues after analyzing the code and its execution feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any logical errors.
2. Analyze the execution feedback to understand the specific errors and their locations within the code.
3. Apply necessary corrections to the code based on the feedback, and output the compelete correct code.
The Java code you need to fix is provided below:
```java
package com.google.gson.internal;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;

@ExtendWith(MockitoExtension.class)
class $Gson$Types_getGenericSupertype_Test {

    private Type context;

    private Class<?> rawType;

    private Class<?> supertype;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the parameters for the test cases
        context = new ArrayList<String>() {
        }.getClass().getGenericSuperclass();
        rawType = ArrayList.class;
        supertype = List.class;
    }

    private Type invokeGetGenericSupertype(Type context, Class<?> rawType, Class<?> supertype) {
        try {
            // Use reflection to invoke the private static method getGenericSupertype
            return (Type) $Gson$Types.class.getDeclaredMethod("getGenericSupertype", Type.class, Class.class, Class.class).invoke(null, context, rawType, supertype);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testGetGenericSupertype() {
        // Given
        // This would depend on the actual expected output
        Type expectedSupertype = context;
        // When
        Type actualSupertype = invokeGetGenericSupertype(context, rawType, supertype);
        // Then
        assertEquals(expectedSupertype, actualSupertype);
    }

    @Test
    void testGetGenericSupertype_withSameRawType() {
        // Test when supertype is the same as rawType, expecting to return the context.
        Type expected = String.class;
        Type actual = invokeGetGenericSupertype(String.class, Object.class, String.class);
        assertEquals(expected, actual);
    }

    @Test
    void testGetGenericSupertype_withInterfaceImplementedByRawType() {
        // Test when supertype is an interface implemented by rawType, expecting to return the generic interface.
        Type expected = List.class;
        Type actual = invokeGetGenericSupertype(String.class, ArrayList.class, List.class);
        assertEquals(expected, actual);
    }

    @Test
    void testGetGenericSupertype_withSuperinterfaceOfRawType() {
        // Test when supertype is a superinterface of rawType, expecting to return the generic superinterface.
        Type expected = Collection.class;
        Type actual = invokeGetGenericSupertype(String.class, ArrayList.class, Collection.class);
        assertEquals(expected, actual);
    }

    @Test
    void testGetGenericSupertype_withSuperclassOfRawType() {
        // Test when supertype is the superclass of rawType, expecting to return the generic superclass.
        Type expected = Object.class;
        Type actual = invokeGetGenericSupertype(String.class, ArrayList.class, Object.class);
        assertEquals(expected, actual);
    }

    @Test
    void testGetGenericSupertype_withIndirectSuperinterface() {
        // Test when supertype is a superinterface of rawType that is not directly implemented, expecting to return the correct generic superinterface.
        Type expected = Serializable.class;
        Type actual = invokeGetGenericSupertype(String.class, ArrayList.class, Serializable.class);
        assertEquals(expected, actual);
    }

    @Test
    void testGetGenericSupertype_withSameContextAndRawType() {
        // Test when supertype is the same as rawType and context is also the same, expecting to return the context.
        Type expected = String.class;
        Type actual = invokeGetGenericSupertype(String.class, String.class, String.class);
        assertEquals(expected, actual);
    }

    @Test
    void testGetGenericSupertype_withUnrelatedSupertype() {
        // Test when supertype is not related to rawType, expecting to return the supertype itself.
        Type expected = Integer.class;
        Type actual = invokeGetGenericSupertype(String.class, String.class, Integer.class);
        assertEquals(expected, actual);
    }

    @Test
    void testGetGenericSupertype_withNullContext() {
        // Test when context is null, expecting to return the correct generic interface.
        Type expected = List.class;
        Type actual = invokeGetGenericSupertype(null, ArrayList.class, List.class);
        assertEquals(expected, actual);
    }

    @Test
    void testGetGenericSupertype_withNullRawType() {
        // Test when rawType is null, expecting a NullPointerException.
        assertThrows(NullPointerException.class, () -> invokeGetGenericSupertype(String.class, null, String.class));
    }

    @Test
    void testGetGenericSupertype_withNullSupertype() {
        // Test when supertype is null, expecting a NullPointerException.
        assertThrows(NullPointerException.class, () -> invokeGetGenericSupertype(String.class, Object.class, null));
    }

    @Test
    void testGetGenericSupertype_withLinkedList() {
        // Test when supertype is an interface implemented by rawType, expecting to return the generic interface List.
        Type expected = List.class;
        Type actual = invokeGetGenericSupertype(String.class, LinkedList.class, List.class);
        assertEquals(expected, actual);
    }

    @Test
    void testGetGenericSupertype_withHashMap() {
        // Test when supertype is a superinterface of rawType, expecting to return the generic superinterface Map.
        Type expected = Map.class;
        Type actual = invokeGetGenericSupertype(String.class, HashMap.class, Map.class);
        assertEquals(expected, actual);
    }

    @Test
    void testGetGenericSupertype_withObjectSuperclass() {
        // Test when supertype is the superclass of rawType, expecting to return the generic superclass Object.
        Type expected = Object.class;
        Type actual = invokeGetGenericSupertype(String.class, HashMap.class, Object.class);
        assertEquals(expected, actual);
    }

    @Test
    void testGetGenericSupertype_withIndirectCloneable() {
        // Test when supertype is a superinterface of rawType that is not directly implemented, expecting to return the correct generic superinterface Cloneable.
        Type expected = Cloneable.class;
        Type actual = invokeGetGenericSupertype(String.class, ArrayList.class, Cloneable.class);
        assertEquals(expected, actual);
    }

    @Test
    void testGetGenericSupertype_withRunnable() {
        // Test when supertype is not related to rawType, expecting to return the supertype Runnable.
        Type expected = Runnable.class;
        Type actual = invokeGetGenericSupertype(String.class, ArrayList.class, Runnable.class);
        assertEquals(expected, actual);
    }

    @Test
    void testGetGenericSupertype_withNumberSupertype() {
        // Test when context is Integer, rawType is ArrayList, and supertype is Number, expecting to return the supertype Number.
        Type expected = Number.class;
        Type actual = invokeGetGenericSupertype(Integer.class, ArrayList.class, Number.class);
        assertEquals(expected, actual);
    }

    @Test
    void testGetGenericSupertype_withNullContextAndRawType() {
        // Test when both context and rawType are null, expecting a NullPointerException.
        assertThrows(NullPointerException.class, () -> invokeGetGenericSupertype(null, null, String.class));
    }

//     @Test
//     void testGetGenericSupertype_withNullRawType() {
//         // Test when rawType is null, expecting a NullPointerException.
//         assertThrows(NullPointerException.class, () -> invokeGetGenericSupertype(String.class, null, String.class));
//     }

//     @Test
//     void testGetGenericSupertype_withNullSupertype() {
//         // Test when supertype is null, expecting a NullPointerException.
//         assertThrows(NullPointerException.class, () -> invokeGetGenericSupertype(String.class, Object.class, null));
//     }

    @Test
    void testGetGenericSupertype_withAllNullParameters() {
        // Test when all parameters are null, expecting a NullPointerException.
        assertThrows(NullPointerException.class, () -> invokeGetGenericSupertype(null, null, null));
    }
}
```

Corresponding execution feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended

╷
├─ JUnit Jupiter ✔
│  └─ $Gson$Types_getGenericSupertype_Test ✔
│     ├─ testGetGenericSupertype_withSameContextAndRawType() ✘ java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
│     ├─ testGetGenericSupertype_withSameRawType() ✘ java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
│     ├─ testGetGenericSupertype_withSuperinterfaceOfRawType() ✘ java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
│     ├─ testGetGenericSupertype_withAllNullParameters() ✘ Unexpected exception type thrown, expected: <java.lang.NullPointerException> but was: <java.lang.RuntimeException>
│     ├─ testGetGenericSupertype_withHashMap() ✘ java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
│     ├─ testGetGenericSupertype() ✘ java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
│     ├─ testGetGenericSupertype_withNullContextAndRawType() ✘ Unexpected exception type thrown, expected: <java.lang.NullPointerException> but was: <java.lang.RuntimeException>
│     ├─ testGetGenericSupertype_withIndirectSuperinterface() ✘ java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
│     ├─ testGetGenericSupertype_withRunnable() ✘ java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
│     ├─ testGetGenericSupertype_withObjectSuperclass() ✘ java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
│     ├─ testGetGenericSupertype_withLinkedList() ✘ java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
│     ├─ testGetGenericSupertype_withSuperclassOfRawType() ✘ java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
│     ├─ testGetGenericSupertype_withNullSupertype() ✘ Unexpected exception type thrown, expected: <java.lang.NullPointerException> but was: <java.lang.RuntimeException>
│     ├─ testGetGenericSupertype_withNullContext() ✘ java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
│     ├─ testGetGenericSupertype_withNullRawType() ✘ Unexpected exception type thrown, expected: <java.lang.NullPointerException> but was: <java.lang.RuntimeException>
│     ├─ testGetGenericSupertype_withNumberSupertype() ✘ java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
│     ├─ testGetGenericSupertype_withInterfaceImplementedByRawType() ✘ java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
│     ├─ testGetGenericSupertype_withUnrelatedSupertype() ✘ java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
│     └─ testGetGenericSupertype_withIndirectCloneable() ✘ java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Failures (19):
  JUnit Jupiter:$Gson$Types_getGenericSupertype_Test:testGetGenericSupertype_withSameContextAndRawType()
    MethodSource [className = 'com.google.gson.internal.$Gson$Types_getGenericSupertype_Test', methodName = 'testGetGenericSupertype_withSameContextAndRawType', methodParameterTypes = '']
    => java.lang.RuntimeException: java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.invokeGetGenericSupertype($Gson$Types_getGenericSupertype_Test.java:41)
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.testGetGenericSupertype_withSameContextAndRawType($Gson$Types_getGenericSupertype_Test.java:100)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
     Caused by: java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
       java.base/jdk.internal.reflect.Reflection.newIllegalAccessException(Reflection.java:392)
       java.base/java.lang.reflect.AccessibleObject.checkAccess(AccessibleObject.java:674)
       java.base/java.lang.reflect.Method.invoke(Method.java:561)
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.invokeGetGenericSupertype($Gson$Types_getGenericSupertype_Test.java:39)
       [...]
  JUnit Jupiter:$Gson$Types_getGenericSupertype_Test:testGetGenericSupertype_withSameRawType()
    MethodSource [className = 'com.google.gson.internal.$Gson$Types_getGenericSupertype_Test', methodName = 'testGetGenericSupertype_withSameRawType', methodParameterTypes = '']
    => java.lang.RuntimeException: java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.invokeGetGenericSupertype($Gson$Types_getGenericSupertype_Test.java:41)
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.testGetGenericSupertype_withSameRawType($Gson$Types_getGenericSupertype_Test.java:60)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
     Caused by: java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
       java.base/jdk.internal.reflect.Reflection.newIllegalAccessException(Reflection.java:392)
       java.base/java.lang.reflect.AccessibleObject.checkAccess(AccessibleObject.java:674)
       java.base/java.lang.reflect.Method.invoke(Method.java:561)
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.invokeGetGenericSupertype($Gson$Types_getGenericSupertype_Test.java:39)
       [...]
  JUnit Jupiter:$Gson$Types_getGenericSupertype_Test:testGetGenericSupertype_withSuperinterfaceOfRawType()
    MethodSource [className = 'com.google.gson.internal.$Gson$Types_getGenericSupertype_Test', methodName = 'testGetGenericSupertype_withSuperinterfaceOfRawType', methodParameterTypes = '']
    => java.lang.RuntimeException: java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.invokeGetGenericSupertype($Gson$Types_getGenericSupertype_Test.java:41)
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.testGetGenericSupertype_withSuperinterfaceOfRawType($Gson$Types_getGenericSupertype_Test.java:76)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
     Caused by: java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
       java.base/jdk.internal.reflect.Reflection.newIllegalAccessException(Reflection.java:392)
       java.base/java.lang.reflect.AccessibleObject.checkAccess(AccessibleObject.java:674)
       java.base/java.lang.reflect.Method.invoke(Method.java:561)
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.invokeGetGenericSupertype($Gson$Types_getGenericSupertype_Test.java:39)
       [...]
  JUnit Jupiter:$Gson$Types_getGenericSupertype_Test:testGetGenericSupertype_withAllNullParameters()
    MethodSource [className = 'com.google.gson.internal.$Gson$Types_getGenericSupertype_Test', methodName = 'testGetGenericSupertype_withAllNullParameters', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Unexpected exception type thrown, expected: <java.lang.NullPointerException> but was: <java.lang.RuntimeException>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:67)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3128)
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.testGetGenericSupertype_withAllNullParameters($Gson$Types_getGenericSupertype_Test.java:201)
       [...]
     Caused by: java.lang.RuntimeException: java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.invokeGetGenericSupertype($Gson$Types_getGenericSupertype_Test.java:41)
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.lambda$testGetGenericSupertype_withAllNullParameters$3($Gson$Types_getGenericSupertype_Test.java:201)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:53)
       [...]
     Caused by: java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
       java.base/jdk.internal.reflect.Reflection.newIllegalAccessException(Reflection.java:392)
       java.base/java.lang.reflect.AccessibleObject.checkAccess(AccessibleObject.java:674)
       java.base/java.lang.reflect.Method.invoke(Method.java:561)
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.invokeGetGenericSupertype($Gson$Types_getGenericSupertype_Test.java:39)
       [...]
  JUnit Jupiter:$Gson$Types_getGenericSupertype_Test:testGetGenericSupertype_withHashMap()
    MethodSource [className = 'com.google.gson.internal.$Gson$Types_getGenericSupertype_Test', methodName = 'testGetGenericSupertype_withHashMap', methodParameterTypes = '']
    => java.lang.RuntimeException: java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.invokeGetGenericSupertype($Gson$Types_getGenericSupertype_Test.java:41)
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.testGetGenericSupertype_withHashMap($Gson$Types_getGenericSupertype_Test.java:144)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
     Caused by: java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
       java.base/jdk.internal.reflect.Reflection.newIllegalAccessException(Reflection.java:392)
       java.base/java.lang.reflect.AccessibleObject.checkAccess(AccessibleObject.java:674)
       java.base/java.lang.reflect.Method.invoke(Method.java:561)
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.invokeGetGenericSupertype($Gson$Types_getGenericSupertype_Test.java:39)
       [...]
  JUnit Jupiter:$Gson$Types_getGenericSupertype_Test:testGetGenericSupertype()
    MethodSource [className = 'com.google.gson.internal.$Gson$Types_getGenericSupertype_Test', methodName = 'testGetGenericSupertype', methodParameterTypes = '']
    => java.lang.RuntimeException: java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.invokeGetGenericSupertype($Gson$Types_getGenericSupertype_Test.java:41)
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.testGetGenericSupertype($Gson$Types_getGenericSupertype_Test.java:51)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
     Caused by: java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
       java.base/jdk.internal.reflect.Reflection.newIllegalAccessException(Reflection.java:392)
       java.base/java.lang.reflect.AccessibleObject.checkAccess(AccessibleObject.java:674)
       java.base/java.lang.reflect.Method.invoke(Method.java:561)
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.invokeGetGenericSupertype($Gson$Types_getGenericSupertype_Test.java:39)
       [...]
  JUnit Jupiter:$Gson$Types_getGenericSupertype_Test:testGetGenericSupertype_withNullContextAndRawType()
    MethodSource [className = 'com.google.gson.internal.$Gson$Types_getGenericSupertype_Test', methodName = 'testGetGenericSupertype_withNullContextAndRawType', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Unexpected exception type thrown, expected: <java.lang.NullPointerException> but was: <java.lang.RuntimeException>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:67)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3128)
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.testGetGenericSupertype_withNullContextAndRawType($Gson$Types_getGenericSupertype_Test.java:183)
       [...]
     Caused by: java.lang.RuntimeException: java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.invokeGetGenericSupertype($Gson$Types_getGenericSupertype_Test.java:41)
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.lambda$testGetGenericSupertype_withNullContextAndRawType$2($Gson$Types_getGenericSupertype_Test.java:183)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:53)
       [...]
     Caused by: java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
       java.base/jdk.internal.reflect.Reflection.newIllegalAccessException(Reflection.java:392)
       java.base/java.lang.reflect.AccessibleObject.checkAccess(AccessibleObject.java:674)
       java.base/java.lang.reflect.Method.invoke(Method.java:561)
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.invokeGetGenericSupertype($Gson$Types_getGenericSupertype_Test.java:39)
       [...]
  JUnit Jupiter:$Gson$Types_getGenericSupertype_Test:testGetGenericSupertype_withIndirectSuperinterface()
    MethodSource [className = 'com.google.gson.internal.$Gson$Types_getGenericSupertype_Test', methodName = 'testGetGenericSupertype_withIndirectSuperinterface', methodParameterTypes = '']
    => java.lang.RuntimeException: java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.invokeGetGenericSupertype($Gson$Types_getGenericSupertype_Test.java:41)
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.testGetGenericSupertype_withIndirectSuperinterface($Gson$Types_getGenericSupertype_Test.java:92)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
     Caused by: java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
       java.base/jdk.internal.reflect.Reflection.newIllegalAccessException(Reflection.java:392)
       java.base/java.lang.reflect.AccessibleObject.checkAccess(AccessibleObject.java:674)
       java.base/java.lang.reflect.Method.invoke(Method.java:561)
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.invokeGetGenericSupertype($Gson$Types_getGenericSupertype_Test.java:39)
       [...]
  JUnit Jupiter:$Gson$Types_getGenericSupertype_Test:testGetGenericSupertype_withRunnable()
    MethodSource [className = 'com.google.gson.internal.$Gson$Types_getGenericSupertype_Test', methodName = 'testGetGenericSupertype_withRunnable', methodParameterTypes = '']
    => java.lang.RuntimeException: java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.invokeGetGenericSupertype($Gson$Types_getGenericSupertype_Test.java:41)
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.testGetGenericSupertype_withRunnable($Gson$Types_getGenericSupertype_Test.java:168)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
     Caused by: java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
       java.base/jdk.internal.reflect.Reflection.newIllegalAccessException(Reflection.java:392)
       java.base/java.lang.reflect.AccessibleObject.checkAccess(AccessibleObject.java:674)
       java.base/java.lang.reflect.Method.invoke(Method.java:561)
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.invokeGetGenericSupertype($Gson$Types_getGenericSupertype_Test.java:39)
       [...]
  JUnit Jupiter:$Gson$Types_getGenericSupertype_Test:testGetGenericSupertype_withObjectSuperclass()
    MethodSource [className = 'com.google.gson.internal.$Gson$Types_getGenericSupertype_Test', methodName = 'testGetGenericSupertype_withObjectSuperclass', methodParameterTypes = '']
    => java.lang.RuntimeException: java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.invokeGetGenericSupertype($Gson$Types_getGenericSupertype_Test.java:41)
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.testGetGenericSupertype_withObjectSuperclass($Gson$Types_getGenericSupertype_Test.java:152)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
     Caused by: java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
       java.base/jdk.internal.reflect.Reflection.newIllegalAccessException(Reflection.java:392)
       java.base/java.lang.reflect.AccessibleObject.checkAccess(AccessibleObject.java:674)
       java.base/java.lang.reflect.Method.invoke(Method.java:561)
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.invokeGetGenericSupertype($Gson$Types_getGenericSupertype_Test.java:39)
       [...]
  JUnit Jupiter:$Gson$Types_getGenericSupertype_Test:testGetGenericSupertype_withLinkedList()
    MethodSource [className = 'com.google.gson.internal.$Gson$Types_getGenericSupertype_Test', methodName = 'testGetGenericSupertype_withLinkedList', methodParameterTypes = '']
    => java.lang.RuntimeException: java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.invokeGetGenericSupertype($Gson$Types_getGenericSupertype_Test.java:41)
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.testGetGenericSupertype_withLinkedList($Gson$Types_getGenericSupertype_Test.java:136)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
     Caused by: java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
       java.base/jdk.internal.reflect.Reflection.newIllegalAccessException(Reflection.java:392)
       java.base/java.lang.reflect.AccessibleObject.checkAccess(AccessibleObject.java:674)
       java.base/java.lang.reflect.Method.invoke(Method.java:561)
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.invokeGetGenericSupertype($Gson$Types_getGenericSupertype_Test.java:39)
       [...]
  JUnit Jupiter:$Gson$Types_getGenericSupertype_Test:testGetGenericSupertype_withSuperclassOfRawType()
    MethodSource [className = 'com.google.gson.internal.$Gson$Types_getGenericSupertype_Test', methodName = 'testGetGenericSupertype_withSuperclassOfRawType', methodParameterTypes = '']
    => java.lang.RuntimeException: java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.invokeGetGenericSupertype($Gson$Types_getGenericSupertype_Test.java:41)
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.testGetGenericSupertype_withSuperclassOfRawType($Gson$Types_getGenericSupertype_Test.java:84)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
     Caused by: java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
       java.base/jdk.internal.reflect.Reflection.newIllegalAccessException(Reflection.java:392)
       java.base/java.lang.reflect.AccessibleObject.checkAccess(AccessibleObject.java:674)
       java.base/java.lang.reflect.Method.invoke(Method.java:561)
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.invokeGetGenericSupertype($Gson$Types_getGenericSupertype_Test.java:39)
       [...]
  JUnit Jupiter:$Gson$Types_getGenericSupertype_Test:testGetGenericSupertype_withNullSupertype()
    MethodSource [className = 'com.google.gson.internal.$Gson$Types_getGenericSupertype_Test', methodName = 'testGetGenericSupertype_withNullSupertype', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Unexpected exception type thrown, expected: <java.lang.NullPointerException> but was: <java.lang.RuntimeException>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:67)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3128)
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.testGetGenericSupertype_withNullSupertype($Gson$Types_getGenericSupertype_Test.java:129)
       [...]
     Caused by: java.lang.RuntimeException: java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.invokeGetGenericSupertype($Gson$Types_getGenericSupertype_Test.java:41)
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.lambda$testGetGenericSupertype_withNullSupertype$1($Gson$Types_getGenericSupertype_Test.java:129)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:53)
       [...]
     Caused by: java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
       java.base/jdk.internal.reflect.Reflection.newIllegalAccessException(Reflection.java:392)
       java.base/java.lang.reflect.AccessibleObject.checkAccess(AccessibleObject.java:674)
       java.base/java.lang.reflect.Method.invoke(Method.java:561)
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.invokeGetGenericSupertype($Gson$Types_getGenericSupertype_Test.java:39)
       [...]
  JUnit Jupiter:$Gson$Types_getGenericSupertype_Test:testGetGenericSupertype_withNullContext()
    MethodSource [className = 'com.google.gson.internal.$Gson$Types_getGenericSupertype_Test', methodName = 'testGetGenericSupertype_withNullContext', methodParameterTypes = '']
    => java.lang.RuntimeException: java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.invokeGetGenericSupertype($Gson$Types_getGenericSupertype_Test.java:41)
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.testGetGenericSupertype_withNullContext($Gson$Types_getGenericSupertype_Test.java:116)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
     Caused by: java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
       java.base/jdk.internal.reflect.Reflection.newIllegalAccessException(Reflection.java:392)
       java.base/java.lang.reflect.AccessibleObject.checkAccess(AccessibleObject.java:674)
       java.base/java.lang.reflect.Method.invoke(Method.java:561)
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.invokeGetGenericSupertype($Gson$Types_getGenericSupertype_Test.java:39)
       [...]
  JUnit Jupiter:$Gson$Types_getGenericSupertype_Test:testGetGenericSupertype_withNullRawType()
    MethodSource [className = 'com.google.gson.internal.$Gson$Types_getGenericSupertype_Test', methodName = 'testGetGenericSupertype_withNullRawType', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Unexpected exception type thrown, expected: <java.lang.NullPointerException> but was: <java.lang.RuntimeException>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:67)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3128)
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.testGetGenericSupertype_withNullRawType($Gson$Types_getGenericSupertype_Test.java:123)
       [...]
     Caused by: java.lang.RuntimeException: java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.invokeGetGenericSupertype($Gson$Types_getGenericSupertype_Test.java:41)
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.lambda$testGetGenericSupertype_withNullRawType$0($Gson$Types_getGenericSupertype_Test.java:123)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:53)
       [...]
     Caused by: java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
       java.base/jdk.internal.reflect.Reflection.newIllegalAccessException(Reflection.java:392)
       java.base/java.lang.reflect.AccessibleObject.checkAccess(AccessibleObject.java:674)
       java.base/java.lang.reflect.Method.invoke(Method.java:561)
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.invokeGetGenericSupertype($Gson$Types_getGenericSupertype_Test.java:39)
       [...]
  JUnit Jupiter:$Gson$Types_getGenericSupertype_Test:testGetGenericSupertype_withNumberSupertype()
    MethodSource [className = 'com.google.gson.internal.$Gson$Types_getGenericSupertype_Test', methodName = 'testGetGenericSupertype_withNumberSupertype', methodParameterTypes = '']
    => java.lang.RuntimeException: java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.invokeGetGenericSupertype($Gson$Types_getGenericSupertype_Test.java:41)
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.testGetGenericSupertype_withNumberSupertype($Gson$Types_getGenericSupertype_Test.java:176)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
     Caused by: java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
       java.base/jdk.internal.reflect.Reflection.newIllegalAccessException(Reflection.java:392)
       java.base/java.lang.reflect.AccessibleObject.checkAccess(AccessibleObject.java:674)
       java.base/java.lang.reflect.Method.invoke(Method.java:561)
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.invokeGetGenericSupertype($Gson$Types_getGenericSupertype_Test.java:39)
       [...]
  JUnit Jupiter:$Gson$Types_getGenericSupertype_Test:testGetGenericSupertype_withInterfaceImplementedByRawType()
    MethodSource [className = 'com.google.gson.internal.$Gson$Types_getGenericSupertype_Test', methodName = 'testGetGenericSupertype_withInterfaceImplementedByRawType', methodParameterTypes = '']
    => java.lang.RuntimeException: java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.invokeGetGenericSupertype($Gson$Types_getGenericSupertype_Test.java:41)
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.testGetGenericSupertype_withInterfaceImplementedByRawType($Gson$Types_getGenericSupertype_Test.java:68)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
     Caused by: java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
       java.base/jdk.internal.reflect.Reflection.newIllegalAccessException(Reflection.java:392)
       java.base/java.lang.reflect.AccessibleObject.checkAccess(AccessibleObject.java:674)
       java.base/java.lang.reflect.Method.invoke(Method.java:561)
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.invokeGetGenericSupertype($Gson$Types_getGenericSupertype_Test.java:39)
       [...]
  JUnit Jupiter:$Gson$Types_getGenericSupertype_Test:testGetGenericSupertype_withUnrelatedSupertype()
    MethodSource [className = 'com.google.gson.internal.$Gson$Types_getGenericSupertype_Test', methodName = 'testGetGenericSupertype_withUnrelatedSupertype', methodParameterTypes = '']
    => java.lang.RuntimeException: java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.invokeGetGenericSupertype($Gson$Types_getGenericSupertype_Test.java:41)
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.testGetGenericSupertype_withUnrelatedSupertype($Gson$Types_getGenericSupertype_Test.java:108)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
     Caused by: java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
       java.base/jdk.internal.reflect.Reflection.newIllegalAccessException(Reflection.java:392)
       java.base/java.lang.reflect.AccessibleObject.checkAccess(AccessibleObject.java:674)
       java.base/java.lang.reflect.Method.invoke(Method.java:561)
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.invokeGetGenericSupertype($Gson$Types_getGenericSupertype_Test.java:39)
       [...]
  JUnit Jupiter:$Gson$Types_getGenericSupertype_Test:testGetGenericSupertype_withIndirectCloneable()
    MethodSource [className = 'com.google.gson.internal.$Gson$Types_getGenericSupertype_Test', methodName = 'testGetGenericSupertype_withIndirectCloneable', methodParameterTypes = '']
    => java.lang.RuntimeException: java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.invokeGetGenericSupertype($Gson$Types_getGenericSupertype_Test.java:41)
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.testGetGenericSupertype_withIndirectCloneable($Gson$Types_getGenericSupertype_Test.java:160)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
     Caused by: java.lang.IllegalAccessException: class com.google.gson.internal.$Gson$Types_getGenericSupertype_Test cannot access a member of class com.google.gson.internal.$Gson$Types with modifiers "private static"
       java.base/jdk.internal.reflect.Reflection.newIllegalAccessException(Reflection.java:392)
       java.base/java.lang.reflect.AccessibleObject.checkAccess(AccessibleObject.java:674)
       java.base/java.lang.reflect.Method.invoke(Method.java:561)
       com.google.gson.internal.$Gson$Types_getGenericSupertype_Test.invokeGetGenericSupertype($Gson$Types_getGenericSupertype_Test.java:39)
       [...]

Test run finished after 408 ms
[         4 containers found      ]
[         0 containers skipped    ]
[         4 containers started    ]
[         0 containers aborted    ]
[         4 containers successful ]
[         0 containers failed     ]
[        19 tests found           ]
[         0 tests skipped         ]
[        19 tests started         ]
[         0 tests aborted         ]
[         0 tests successful      ]
[        19 tests failed          ]


```


And you can use the following related information to assist you in your analysis and correction:

api document of class com.google.gson.internal.$Gson$Types: 

 * Static methods for working with types.
 *
 * @author Bob Lee
 * @author Jesse Wilson
 

api document of method getGenericSupertype(Type, Class<?>, Class<?>): 

   * Returns the generic supertype for {@code supertype}. For example, given a class {@code
   * IntegerSet}, the result for when supertype is {@code Set.class} is {@code Set<Integer>} and the
   * result when the supertype is {@code Collection.class} is {@code Collection<Integer>}.
   

dependent classes: 
class `com.google.gson.internal.$Gson$Types`:
	related functions:
		method `private static Type resolve(Type, Class<?>, Type, Map<TypeVariable<?>, Type>)` returns `java.lang.reflect.Type`, related with `com.google.gson.internal.$Gson$Types.getGenericSupertype(Type, Class<?>, Class<?>)`;
		method `public static Class<?> getRawType(Type)` returns `java.lang.Class<?>`, related with `com.google.gson.internal.$Gson$Types.getGenericSupertype(Type, Class<?>, Class<?>)`;
		method `private static Type getSupertype(Type, Class<?>, Class<?>)` returns `java.lang.reflect.Type`, related with `com.google.gson.internal.$Gson$Types.getGenericSupertype(Type, Class<?>, Class<?>)`, api document: 
   * Returns the generic form of {@code supertype}. For example, if this is {@code
   * ArrayList<String>}, this returns {@code Iterable<String>} given the input {@code
   * Iterable.class}.
   *
   * @param supertype a superclass of, or interface implemented by, this.
   ;
class `com.google.gson.reflect.TypeToken`:
	related functions:
		method `private static boolean isAssignableFrom(Type, ParameterizedType, Map<String, Type>)` returns `boolean`, related with `com.google.gson.internal.$Gson$Types.getGenericSupertype(Type, Class<?>, Class<?>)`, api document: 
   * Private recursive helper function to actually do the type-safe checking
   * of assignability.
   ;
		method `public static TypeToken<?> getParameterized(Type, Type[])` returns `com.google.gson.reflect.TypeToken<?>`, related with `com.google.gson.internal.$Gson$Types.getGenericSupertype(Type, Class<?>, Class<?>)`, api document: 
   * Gets a type literal for the parameterized type represented by applying {@code typeArguments} to
   * {@code rawType}. This is mainly intended for situations where the type arguments are not
   * available at compile time. The following example shows how a type token for {@code Map<K, V>}
   * can be created:
   * <pre>{@code
   * Class<K> keyClass = ...;
   * Class<V> valueClass = ...;
   * TypeToken<?> mapTypeToken = TypeToken.getParameterized(Map.class, keyClass, valueClass);
   * }</pre>
   * As seen here the result is a {@code TypeToken<?>}; this method cannot provide any type safety,
   * and care must be taken to pass in the correct number of type arguments.
   *
   * @throws IllegalArgumentException
   *   If {@code rawType} is not of type {@code Class}, or if the type arguments are invalid for
   *   the raw type
   ;



Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 
