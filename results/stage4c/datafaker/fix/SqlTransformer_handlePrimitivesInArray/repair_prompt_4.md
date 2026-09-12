


You are an experienced Java developer tasked with fixing a piece of code that contains execution errors. Your task is to resolve these issues after analyzing the code and its execution feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any logical errors.
2. Analyze the execution feedback to understand the specific errors and their locations within the code.
3. Apply necessary corrections to the code based on the feedback, and output the compelete correct code.
The Java code you need to fix is provided below:
```java
package net.datafaker.transformations.sql;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.lang.reflect.Method;
import java.util.StringJoiner;
import java.lang.reflect.Constructor;

@ExtendWith(MockitoExtension.class)
class SqlTransformer_handlePrimitivesInArray_Test {

    private SqlTransformer<Object> sqlTransformer;

    private SqlDialect mockDialect;

    private Casing mockCasing;

    private SqlTransformer.Case mockKeywordCase;

    @BeforeEach
    void setupBeforeEach() throws Exception {
        // Initialize mocks before each test
        mockDialect = mock(SqlDialect.class);
        mockCasing = mock(Casing.class);
        mockKeywordCase = mock(SqlTransformer.Case.class);
        // Use reflection to create an instance of SqlTransformer
        Constructor<SqlTransformer> constructor = SqlTransformer.class.getDeclaredConstructor(String.class, String.class, char.class, SqlDialect.class, String.class, Casing.class, boolean.class, int.class, SqlTransformer.Case.class, boolean.class);
        constructor.setAccessible(true);
        sqlTransformer = constructor.newInstance("testSchema", "testTable", '"', mockDialect, "\"", mockCasing, false, 0, mockKeywordCase, false);
    }

    // Reflection method to access private method
    private String invokeHandlePrimitivesInArray(Class<?> componentType, Object value) throws Exception {
        Method method = SqlTransformer.class.getDeclaredMethod("handlePrimitivesInArray", Class.class, Object.class);
        method.setAccessible(true);
        return (String) method.invoke(sqlTransformer, componentType, value);
    }

    // Example test case for handlePrimitivesInArray method
    @Test
    void testHandlePrimitivesInArray_withIntArray() throws Exception {
        // Given
        Class<?> componentType = int.class;
        int[] intArray = { 100, 200, 300 };
        // When
        String result = invokeHandlePrimitivesInArray(componentType, intArray);
        // Then
        assertEquals("100, 200, 300", result);
    }

    @Test
    void testHandlePrimitivesInArray_withByteArray() throws Exception {
        // Given
        Class<?> componentType = byte.class;
        byte[] byteArray = { 1, 2, 3 };
        // When
        String result = invokeHandlePrimitivesInArray(componentType, byteArray);
        // Then
        assertEquals("1, 2, 3", result);
    }

    @Test
    void testHandlePrimitivesInArray_withShortArray() throws Exception {
        // Given
        Class<?> componentType = short.class;
        short[] shortArray = { 10, 20, 30 };
        // When
        String result = invokeHandlePrimitivesInArray(componentType, shortArray);
        // Then
        assertEquals("10, 20, 30", result);
    }

    @Test
    void testHandlePrimitivesInArray_withBooleanArray() throws Exception {
        // Given
        Class<?> componentType = boolean.class;
        boolean[] booleanArray = { true, false, true };
        // When
        String result = invokeHandlePrimitivesInArray(componentType, booleanArray);
        // Then
        assertEquals("true, false, true", result);
    }

    @Test
    void testHandlePrimitivesInArray_withLongArray() throws Exception {
        // Given
        Class<?> componentType = long.class;
        long[] longArray = { 1000L, 2000L, 3000L };
        // When
        String result = invokeHandlePrimitivesInArray(componentType, longArray);
        // Then
        assertEquals("1000, 2000, 3000", result);
    }

    @Test
    void testHandlePrimitivesInArray_withFloatArray() throws Exception {
        // Given
        Class<?> componentType = float.class;
        float[] floatArray = { 1.1f, 2.2f, 3.3f };
        // When
        String result = invokeHandlePrimitivesInArray(componentType, floatArray);
        // Then
        assertEquals("1.1, 2.2, 3.3", result);
    }

    @Test
    void testHandlePrimitivesInArray_withDoubleArray() throws Exception {
        // Given
        Class<?> componentType = double.class;
        double[] doubleArray = { 1.11, 2.22, 3.33 };
        // When
        String result = invokeHandlePrimitivesInArray(componentType, doubleArray);
        // Then
        assertEquals("1.11, 2.22, 3.33", result);
    }

    @Test
    void testHandlePrimitivesInArray_withUnsupportedCharArray() throws Exception {
        // Given
        Class<?> componentType = char.class;
        char[] charArray = { 'a', 'b', 'c' };
        // When
        String result = invokeHandlePrimitivesInArray(componentType, charArray);
        // Then
        assertEquals("", result);
    }

    @Test
    void testHandlePrimitivesInArray_withEmptyByteArray() throws Exception {
        // Given
        Class<?> componentType = byte.class;
        byte[] byteArray = {};
        // When
        String result = invokeHandlePrimitivesInArray(componentType, byteArray);
        // Then
        assertEquals("", result);
    }

    @Test
    void testHandlePrimitivesInArray_withShortBoundaryValues() throws Exception {
        // Given
        Class<?> componentType = short.class;
        short[] shortArray = { Short.MAX_VALUE, Short.MIN_VALUE };
        // When
        String result = invokeHandlePrimitivesInArray(componentType, shortArray);
        // Then
        assertEquals("32767, -32768", result);
    }

    @Test
    void testHandlePrimitivesInArray_withEmptyBooleanArray() throws Exception {
        // Given
        Class<?> componentType = boolean.class;
        boolean[] booleanArray = {};
        // When
        String result = invokeHandlePrimitivesInArray(componentType, booleanArray);
        // Then
        assertEquals("", result);
    }

    @Test
    void testHandlePrimitivesInArray_withIntBoundaryValues() throws Exception {
        // Given
        Class<?> componentType = int.class;
        int[] intArray = { Integer.MAX_VALUE, Integer.MIN_VALUE };
        // When
        String result = invokeHandlePrimitivesInArray(componentType, intArray);
        // Then
        assertEquals("2147483647, -2147483648", result);
    }

    @Test
    void testHandlePrimitivesInArray_withLongBoundaryValues() throws Exception {
        // Given
        Class<?> componentType = long.class;
        long[] longArray = { Long.MAX_VALUE, Long.MIN_VALUE };
        // When
        String result = invokeHandlePrimitivesInArray(componentType, longArray);
        // Then
        assertEquals("9223372036854775807, -9223372036854775808", result);
    }

    @Test
    void testHandlePrimitivesInArray_withFloatBoundaryValues() throws Exception {
        // Given
        Class<?> componentType = float.class;
        float[] floatArray = { Float.MAX_VALUE, Float.MIN_VALUE };
        // When
        String result = invokeHandlePrimitivesInArray(componentType, floatArray);
        // Then
        assertEquals("3.4028235E38, 1.4E-45", result);
    }

    @Test
    void testHandlePrimitivesInArray_withDoubleBoundaryValues() throws Exception {
        // Given
        Class<?> componentType = double.class;
        double[] doubleArray = { Double.MAX_VALUE, Double.MIN_VALUE };
        // When
        String result = invokeHandlePrimitivesInArray(componentType, doubleArray);
        // Then
        assertEquals("1.7976931348623157E308, 4.9E-324", result);
    }

    @Test
    void testHandlePrimitivesInArray_withZeroAndNegativeZero() throws Exception {
        // Given
        Class<?> componentType = int.class;
        int[] intArray = { 0, -0 };
        // When
        String result = invokeHandlePrimitivesInArray(componentType, intArray);
        // Then
        assertEquals("0, 0", result);
    }

    @Test
    void testHandlePrimitivesInArray_withNullByteArray() {
        // Given
        Class<?> componentType = byte.class;
        byte[] byteArray = null;
        // When & Then
        assertThrows(NullPointerException.class, () -> invokeHandlePrimitivesInArray(componentType, byteArray));
    }

    @Test
    void testHandlePrimitivesInArray_withNullShortArray() {
        // Given
        Class<?> componentType = short.class;
        short[] shortArray = null;
        // When & Then
        assertThrows(NullPointerException.class, () -> invokeHandlePrimitivesInArray(componentType, shortArray));
    }

    @Test
    void testHandlePrimitivesInArray_withNullBooleanArray() {
        // Given
        Class<?> componentType = boolean.class;
        boolean[] booleanArray = null;
        // When & Then
        assertThrows(NullPointerException.class, () -> invokeHandlePrimitivesInArray(componentType, booleanArray));
    }

    @Test
    void testHandlePrimitivesInArray_withNullIntArray() {
        // Given
        Class<?> componentType = int.class;
        int[] intArray = null;
        // When & Then
        assertThrows(NullPointerException.class, () -> invokeHandlePrimitivesInArray(componentType, intArray));
    }

    @Test
    void testHandlePrimitivesInArray_withNullLongArray() {
        // Given
        Class<?> componentType = long.class;
        long[] longArray = null;
        // When & Then
        assertThrows(NullPointerException.class, () -> invokeHandlePrimitivesInArray(componentType, longArray));
    }

    @Test
    void testHandlePrimitivesInArray_withNullFloatArray() {
        // Given
        Class<?> componentType = float.class;
        float[] floatArray = null;
        // When & Then
        assertThrows(NullPointerException.class, () -> invokeHandlePrimitivesInArray(componentType, floatArray));
    }

    @Test
    void testHandlePrimitivesInArray_withNullDoubleArray() {
        // Given
        Class<?> componentType = double.class;
        double[] doubleArray = null;
        // When & Then
        assertThrows(NullPointerException.class, () -> invokeHandlePrimitivesInArray(componentType, doubleArray));
    }

    @Test
    void testHandlePrimitivesInArray_withNullUnsupportedCharArray() {
        // Given
        Class<?> componentType = char.class;
        char[] charArray = null;
        // When & Then
        assertThrows(NullPointerException.class, () -> invokeHandlePrimitivesInArray(componentType, charArray));
    }

    @Test
    void testHandlePrimitivesInArray_withUnsupportedStringArray() {
        // Given
        Class<?> componentType = String.class;
        String[] stringArray = { "a", "b", "c" };
        // When & Then
        assertThrows(ClassCastException.class, () -> invokeHandlePrimitivesInArray(componentType, stringArray));
    }
}

```

Corresponding execution feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended

╷
├─ JUnit Jupiter ✔
│  └─ SqlTransformer_handlePrimitivesInArray_Test ✔
│     ├─ testHandlePrimitivesInArray_withIntBoundaryValues() ✔
│     ├─ testHandlePrimitivesInArray_withNullShortArray() ✘ Unexpected exception type thrown, expected: <java.lang.NullPointerException> but was: <java.lang.reflect.InvocationTargetException>
│     ├─ testHandlePrimitivesInArray_withEmptyByteArray() ✔
│     ├─ testHandlePrimitivesInArray_withFloatArray() ✔
│     ├─ testHandlePrimitivesInArray_withFloatBoundaryValues() ✔
│     ├─ testHandlePrimitivesInArray_withIntArray() ✔
│     ├─ testHandlePrimitivesInArray_withDoubleArray() ✔
│     ├─ testHandlePrimitivesInArray_withNullDoubleArray() ✘ Unexpected exception type thrown, expected: <java.lang.NullPointerException> but was: <java.lang.reflect.InvocationTargetException>
│     ├─ testHandlePrimitivesInArray_withDoubleBoundaryValues() ✔
│     ├─ testHandlePrimitivesInArray_withUnsupportedStringArray() ✘ Expected java.lang.ClassCastException to be thrown, but nothing was thrown.
│     ├─ testHandlePrimitivesInArray_withNullFloatArray() ✘ Unexpected exception type thrown, expected: <java.lang.NullPointerException> but was: <java.lang.reflect.InvocationTargetException>
│     ├─ testHandlePrimitivesInArray_withNullLongArray() ✘ Unexpected exception type thrown, expected: <java.lang.NullPointerException> but was: <java.lang.reflect.InvocationTargetException>
│     ├─ testHandlePrimitivesInArray_withShortBoundaryValues() ✔
│     ├─ testHandlePrimitivesInArray_withByteArray() ✔
│     ├─ testHandlePrimitivesInArray_withBooleanArray() ✔
│     ├─ testHandlePrimitivesInArray_withLongBoundaryValues() ✔
│     ├─ testHandlePrimitivesInArray_withUnsupportedCharArray() ✔
│     ├─ testHandlePrimitivesInArray_withNullUnsupportedCharArray() ✘ Expected java.lang.NullPointerException to be thrown, but nothing was thrown.
│     ├─ testHandlePrimitivesInArray_withShortArray() ✔
│     ├─ testHandlePrimitivesInArray_withEmptyBooleanArray() ✔
│     ├─ testHandlePrimitivesInArray_withNullIntArray() ✘ Unexpected exception type thrown, expected: <java.lang.NullPointerException> but was: <java.lang.reflect.InvocationTargetException>
│     ├─ testHandlePrimitivesInArray_withNullBooleanArray() ✘ Unexpected exception type thrown, expected: <java.lang.NullPointerException> but was: <java.lang.reflect.InvocationTargetException>
│     ├─ testHandlePrimitivesInArray_withNullByteArray() ✘ Unexpected exception type thrown, expected: <java.lang.NullPointerException> but was: <java.lang.reflect.InvocationTargetException>
│     ├─ testHandlePrimitivesInArray_withZeroAndNegativeZero() ✔
│     └─ testHandlePrimitivesInArray_withLongArray() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Failures (9):
  JUnit Jupiter:SqlTransformer_handlePrimitivesInArray_Test:testHandlePrimitivesInArray_withNullShortArray()
    MethodSource [className = 'net.datafaker.transformations.sql.SqlTransformer_handlePrimitivesInArray_Test', methodName = 'testHandlePrimitivesInArray_withNullShortArray', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Unexpected exception type thrown, expected: <java.lang.NullPointerException> but was: <java.lang.reflect.InvocationTargetException>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:67)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3128)
       net.datafaker.transformations.sql.SqlTransformer_handlePrimitivesInArray_Test.testHandlePrimitivesInArray_withNullShortArray(SqlTransformer_handlePrimitivesInArray_Test.java:235)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       net.datafaker.transformations.sql.SqlTransformer_handlePrimitivesInArray_Test.invokeHandlePrimitivesInArray(SqlTransformer_handlePrimitivesInArray_Test.java:40)
       [...]
     Caused by: java.lang.NullPointerException: Cannot read the array length because "array" is null
       net.datafaker.transformations.sql.SqlTransformer.handlePrimitivesInArray(SqlTransformer.java:228)
       [...]
  JUnit Jupiter:SqlTransformer_handlePrimitivesInArray_Test:testHandlePrimitivesInArray_withNullDoubleArray()
    MethodSource [className = 'net.datafaker.transformations.sql.SqlTransformer_handlePrimitivesInArray_Test', methodName = 'testHandlePrimitivesInArray_withNullDoubleArray', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Unexpected exception type thrown, expected: <java.lang.NullPointerException> but was: <java.lang.reflect.InvocationTargetException>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:67)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3128)
       net.datafaker.transformations.sql.SqlTransformer_handlePrimitivesInArray_Test.testHandlePrimitivesInArray_withNullDoubleArray(SqlTransformer_handlePrimitivesInArray_Test.java:280)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       net.datafaker.transformations.sql.SqlTransformer_handlePrimitivesInArray_Test.invokeHandlePrimitivesInArray(SqlTransformer_handlePrimitivesInArray_Test.java:40)
       [...]
     Caused by: java.lang.NullPointerException: Cannot read the array length because "array" is null
       net.datafaker.transformations.sql.SqlTransformer.handlePrimitivesInArray(SqlTransformer.java:254)
       [...]
  JUnit Jupiter:SqlTransformer_handlePrimitivesInArray_Test:testHandlePrimitivesInArray_withUnsupportedStringArray()
    MethodSource [className = 'net.datafaker.transformations.sql.SqlTransformer_handlePrimitivesInArray_Test', methodName = 'testHandlePrimitivesInArray_withUnsupportedStringArray', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Expected java.lang.ClassCastException to be thrown, but nothing was thrown.
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:73)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3128)
       net.datafaker.transformations.sql.SqlTransformer_handlePrimitivesInArray_Test.testHandlePrimitivesInArray_withUnsupportedStringArray(SqlTransformer_handlePrimitivesInArray_Test.java:298)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:767)
       [...]
  JUnit Jupiter:SqlTransformer_handlePrimitivesInArray_Test:testHandlePrimitivesInArray_withNullFloatArray()
    MethodSource [className = 'net.datafaker.transformations.sql.SqlTransformer_handlePrimitivesInArray_Test', methodName = 'testHandlePrimitivesInArray_withNullFloatArray', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Unexpected exception type thrown, expected: <java.lang.NullPointerException> but was: <java.lang.reflect.InvocationTargetException>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:67)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3128)
       net.datafaker.transformations.sql.SqlTransformer_handlePrimitivesInArray_Test.testHandlePrimitivesInArray_withNullFloatArray(SqlTransformer_handlePrimitivesInArray_Test.java:271)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       net.datafaker.transformations.sql.SqlTransformer_handlePrimitivesInArray_Test.invokeHandlePrimitivesInArray(SqlTransformer_handlePrimitivesInArray_Test.java:40)
       [...]
     Caused by: java.lang.NullPointerException: Cannot read the array length because "array" is null
       net.datafaker.transformations.sql.SqlTransformer.handlePrimitivesInArray(SqlTransformer.java:249)
       [...]
  JUnit Jupiter:SqlTransformer_handlePrimitivesInArray_Test:testHandlePrimitivesInArray_withNullLongArray()
    MethodSource [className = 'net.datafaker.transformations.sql.SqlTransformer_handlePrimitivesInArray_Test', methodName = 'testHandlePrimitivesInArray_withNullLongArray', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Unexpected exception type thrown, expected: <java.lang.NullPointerException> but was: <java.lang.reflect.InvocationTargetException>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:67)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3128)
       net.datafaker.transformations.sql.SqlTransformer_handlePrimitivesInArray_Test.testHandlePrimitivesInArray_withNullLongArray(SqlTransformer_handlePrimitivesInArray_Test.java:262)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       net.datafaker.transformations.sql.SqlTransformer_handlePrimitivesInArray_Test.invokeHandlePrimitivesInArray(SqlTransformer_handlePrimitivesInArray_Test.java:40)
       [...]
     Caused by: java.lang.NullPointerException: Cannot read the array length because "array" is null
       net.datafaker.transformations.sql.SqlTransformer.handlePrimitivesInArray(SqlTransformer.java:244)
       [...]
  JUnit Jupiter:SqlTransformer_handlePrimitivesInArray_Test:testHandlePrimitivesInArray_withNullUnsupportedCharArray()
    MethodSource [className = 'net.datafaker.transformations.sql.SqlTransformer_handlePrimitivesInArray_Test', methodName = 'testHandlePrimitivesInArray_withNullUnsupportedCharArray', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Expected java.lang.NullPointerException to be thrown, but nothing was thrown.
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:73)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3128)
       net.datafaker.transformations.sql.SqlTransformer_handlePrimitivesInArray_Test.testHandlePrimitivesInArray_withNullUnsupportedCharArray(SqlTransformer_handlePrimitivesInArray_Test.java:289)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:767)
       [...]
  JUnit Jupiter:SqlTransformer_handlePrimitivesInArray_Test:testHandlePrimitivesInArray_withNullIntArray()
    MethodSource [className = 'net.datafaker.transformations.sql.SqlTransformer_handlePrimitivesInArray_Test', methodName = 'testHandlePrimitivesInArray_withNullIntArray', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Unexpected exception type thrown, expected: <java.lang.NullPointerException> but was: <java.lang.reflect.InvocationTargetException>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:67)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3128)
       net.datafaker.transformations.sql.SqlTransformer_handlePrimitivesInArray_Test.testHandlePrimitivesInArray_withNullIntArray(SqlTransformer_handlePrimitivesInArray_Test.java:253)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       jdk.internal.reflect.GeneratedMethodAccessor15.invoke(Unknown Source)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       net.datafaker.transformations.sql.SqlTransformer_handlePrimitivesInArray_Test.invokeHandlePrimitivesInArray(SqlTransformer_handlePrimitivesInArray_Test.java:40)
       net.datafaker.transformations.sql.SqlTransformer_handlePrimitivesInArray_Test.lambda$testHandlePrimitivesInArray_withNullIntArray$3(SqlTransformer_handlePrimitivesInArray_Test.java:253)
       [...]
     Caused by: java.lang.NullPointerException: Cannot read the array length because "array" is null
       net.datafaker.transformations.sql.SqlTransformer.handlePrimitivesInArray(SqlTransformer.java:239)
       [...]
  JUnit Jupiter:SqlTransformer_handlePrimitivesInArray_Test:testHandlePrimitivesInArray_withNullBooleanArray()
    MethodSource [className = 'net.datafaker.transformations.sql.SqlTransformer_handlePrimitivesInArray_Test', methodName = 'testHandlePrimitivesInArray_withNullBooleanArray', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Unexpected exception type thrown, expected: <java.lang.NullPointerException> but was: <java.lang.reflect.InvocationTargetException>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:67)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3128)
       net.datafaker.transformations.sql.SqlTransformer_handlePrimitivesInArray_Test.testHandlePrimitivesInArray_withNullBooleanArray(SqlTransformer_handlePrimitivesInArray_Test.java:244)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       jdk.internal.reflect.GeneratedMethodAccessor15.invoke(Unknown Source)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       net.datafaker.transformations.sql.SqlTransformer_handlePrimitivesInArray_Test.invokeHandlePrimitivesInArray(SqlTransformer_handlePrimitivesInArray_Test.java:40)
       net.datafaker.transformations.sql.SqlTransformer_handlePrimitivesInArray_Test.lambda$testHandlePrimitivesInArray_withNullBooleanArray$2(SqlTransformer_handlePrimitivesInArray_Test.java:244)
       [...]
     Caused by: java.lang.NullPointerException: Cannot read the array length because "array" is null
       net.datafaker.transformations.sql.SqlTransformer.handlePrimitivesInArray(SqlTransformer.java:234)
       [...]
  JUnit Jupiter:SqlTransformer_handlePrimitivesInArray_Test:testHandlePrimitivesInArray_withNullByteArray()
    MethodSource [className = 'net.datafaker.transformations.sql.SqlTransformer_handlePrimitivesInArray_Test', methodName = 'testHandlePrimitivesInArray_withNullByteArray', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Unexpected exception type thrown, expected: <java.lang.NullPointerException> but was: <java.lang.reflect.InvocationTargetException>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:67)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3128)
       net.datafaker.transformations.sql.SqlTransformer_handlePrimitivesInArray_Test.testHandlePrimitivesInArray_withNullByteArray(SqlTransformer_handlePrimitivesInArray_Test.java:226)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       jdk.internal.reflect.GeneratedMethodAccessor15.invoke(Unknown Source)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       net.datafaker.transformations.sql.SqlTransformer_handlePrimitivesInArray_Test.invokeHandlePrimitivesInArray(SqlTransformer_handlePrimitivesInArray_Test.java:40)
       net.datafaker.transformations.sql.SqlTransformer_handlePrimitivesInArray_Test.lambda$testHandlePrimitivesInArray_withNullByteArray$0(SqlTransformer_handlePrimitivesInArray_Test.java:226)
       [...]
     Caused by: java.lang.NullPointerException: Cannot read the array length because "array" is null
       net.datafaker.transformations.sql.SqlTransformer.handlePrimitivesInArray(SqlTransformer.java:222)
       [...]

Test run finished after 526 ms
[         4 containers found      ]
[         0 containers skipped    ]
[         4 containers started    ]
[         0 containers aborted    ]
[         4 containers successful ]
[         0 containers failed     ]
[        25 tests found           ]
[         0 tests skipped         ]
[        25 tests started         ]
[         0 tests aborted         ]
[        16 tests successful      ]
[         9 tests failed          ]


```


And you can use the following related information to assist you in your analysis and correction:

dependent classes: 
class `net.datafaker.providers.base.Finance`:
	related functions:
		method `private static String calculateIbanChecksum(String, String)` returns `java.lang.String`, related with `net.datafaker.transformations.sql.SqlTransformer.handlePrimitivesInArray(Class<?>, Object)`;
class `net.datafaker.providers.base.Internet`:
	related functions:
		method `public String image()` returns `java.lang.String`, related with `net.datafaker.transformations.sql.SqlTransformer.handlePrimitivesInArray(Class<?>, Object)`;



Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 
