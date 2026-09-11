```java
package net.datafaker.transformations.sql;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class SqlTransformer_handlePrimitivesInArray_Test {
    private SqlTransformer<Object> sqlTransformer;
    private SqlDialect sqlDialect;
    private Casing casing;
    private SqlTransformer.Case keywordCase;

    @BeforeAll
    static void setupBeforeAll() {
        // Any global setup can be done here
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the dependencies and the SqlTransformer instance
        sqlDialect = new SqlDialect("`");
        casing = new Casing(); // Assuming a default constructor is available
        keywordCase = new SqlTransformer.Case(); // Assuming a default constructor is available
        sqlTransformer = new SqlTransformer<>("schemaName", "tableName", '`', sqlDialect, "sqlIdentifier", casing, true, 100, keywordCase, false);
    }

    @AfterEach
    void teardownAfterEach() {
        // Clean up after each test if necessary
    }

    @AfterAll
    static void teardownAfterAll() {
        // Any global cleanup can be done here
    }

    // Helper method to invoke the private method using reflection
    private String invokeHandlePrimitivesInArray(Class<?> componentType, Object value) throws Exception {
        Method method = SqlTransformer.class.getDeclaredMethod("handlePrimitivesInArray", Class.class, Object.class);
        method.setAccessible(true);
        return (String) method.invoke(sqlTransformer, componentType, value);
    }

    @Test
    void testHandlePrimitivesInArray_withByteArray() throws Exception {
        byte[] input = {1, 2, 3};
        String result = invokeHandlePrimitivesInArray(byte.class, input);
        assertEquals("1, 2, 3", result);
    }

    @Test
    void testHandlePrimitivesInArray_withByteArrayNegativeZero() throws Exception {
        byte[] input = {-1, 0, 1};
        String result = invokeHandlePrimitivesInArray(byte.class, input);
        assertEquals("-1, 0, 1", result);
    }

    @Test
    void testHandlePrimitivesInArray_withShortArray() throws Exception {
        short[] input = {100, 200, 300};
        String result = invokeHandlePrimitivesInArray(short.class, input);
        assertEquals("100, 200, 300", result);
    }

    @Test
    void testHandlePrimitivesInArray_withShortArrayMinMax() throws Exception {
        short[] input = {-32768, 0, 32767};
        String result = invokeHandlePrimitivesInArray(short.class, input);
        assertEquals("-32768, 0, 32767", result);
    }

    @Test
    void testHandlePrimitivesInArray_withIntArray() throws Exception {
        int[] input = {1, 2, 3, 4};
        String result = invokeHandlePrimitivesInArray(int.class, input);
        assertEquals("1, 2, 3, 4", result);
    }

    @Test
    void testHandlePrimitivesInArray_withLongArray() throws Exception {
        long[] input = {10000000000L, 20000000000L};
        String result = invokeHandlePrimitivesInArray(long.class, input);
        assertEquals("10000000000, 20000000000", result);
    }

    @Test
    void testHandlePrimitivesInArray_withFloatArray() throws Exception {
        float[] input = {1.1f, 2.2f, 3.3f};
        String result = invokeHandlePrimitivesInArray(float.class, input);
        assertEquals("1.1, 2.2, 3.3", result);
    }

    @Test
    void testHandlePrimitivesInArray_withDoubleArray() throws Exception {
        double[] input = {1.1, 2.2, 3.3};
        String result = invokeHandlePrimitivesInArray(double.class, input);
        assertEquals("1.1, 2.2, 3.3", result);
    }

    @Test
    void testHandlePrimitivesInArray_withBooleanArray() throws Exception {
        boolean[] input = {true, false, true};
        String result = invokeHandlePrimitivesInArray(boolean.class, input);
        assertEquals("true, false, true", result);
    }

    @Test
    void testHandlePrimitivesInArray_withEmptyBooleanArray() throws Exception {
        boolean[] input = {};
        String result = invokeHandlePrimitivesInArray(boolean.class, input);
        assertEquals("", result);
    }

    @Test
    void testHandlePrimitivesInArray_withUnsupportedType() throws Exception {
        String[] input = {};
        String result = invokeHandlePrimitivesInArray(String.class, input);
        assertEquals("", result);
    }
}
```