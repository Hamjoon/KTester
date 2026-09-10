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

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the necessary dependencies
        // Ensure Casing.DEFAULT is a valid enum value
        casing = Casing.DEFAULT;
        // Ensure SqlDialect is correctly instantiated
        sqlDialect = new SqlDialect("'", casing);
        // Ensure Case.DEFAULT is a valid enum value
        keywordCase = SqlTransformer.Case.DEFAULT;
        sqlTransformer = new SqlTransformer<>("schemaName", "tableName", '\'', sqlDialect, "sqlIdentifier", casing, true, 10, keywordCase, false);
    }

    private String invokeHandlePrimitivesInArray(Class<?> componentType, Object value) throws Exception {
        // Use reflection to access the private method
        Method method = SqlTransformer.class.getDeclaredMethod("handlePrimitivesInArray", Class.class, Object.class);
        method.setAccessible(true);
        return (String) method.invoke(sqlTransformer, componentType, value);
    }

    @Test
    void testHandlePrimitivesInArrayWithByteArray() {
        // Given
        Class<?> componentType = byte.class;
        Object value = new byte[] { 1, 2, 3 };
        // When
        String result = invokeHandlePrimitivesInArray(componentType, value);
        // Then
        assertEquals("1, 2, 3", result);
    }

    @Test
    void testHandlePrimitivesInArrayWithIntArray() {
        // Given
        Class<?> componentType = int.class;
        Object value = new int[] { 4, 5, 6 };
        // When
        String result = invokeHandlePrimitivesInArray(componentType, value);
        // Then
        assertEquals("4, 5, 6", result);
    }

    @Test
    void testHandlePrimitivesInArrayWithBooleanArray() {
        // Given
        Class<?> componentType = boolean.class;
        Object value = new boolean[] { true, false, true };
        // When
        String result = invokeHandlePrimitivesInArray(componentType, value);
        // Then
        assertEquals("true, false, true", result);
    }

    @Test
    void testHandlePrimitivesInArrayWithByteArrayNegativeValues() throws Exception {
        // Given
        Class<?> componentType = byte.class;
        Object value = new byte[] { -1, -2, -3 };
        // When
        String result = invokeHandlePrimitivesInArray(componentType, value);
        // Then
        assertEquals("-1, -2, -3", result);
    }

    @Test
    void testHandlePrimitivesInArrayWithEmptyByteArray() throws Exception {
        // Given
        Class<?> componentType = byte.class;
        Object value = new byte[] {};
        // When
        String result = invokeHandlePrimitivesInArray(componentType, value);
        // Then
        assertEquals("", result);
    }

    @Test
    void testHandlePrimitivesInArrayWithShortArray() throws Exception {
        // Given
        Class<?> componentType = short.class;
        Object value = new short[] { 100, 200, 300 };
        // When
        String result = invokeHandlePrimitivesInArray(componentType, value);
        // Then
        assertEquals("100, 200, 300", result);
    }

    @Test
    void testHandlePrimitivesInArrayWithShortArrayNegativeValues() throws Exception {
        // Given
        Class<?> componentType = short.class;
        Object value = new short[] { -100, -200, -300 };
        // When
        String result = invokeHandlePrimitivesInArray(componentType, value);
        // Then
        assertEquals("-100, -200, -300", result);
    }

    @Test
    void testHandlePrimitivesInArrayWithEmptyShortArray() throws Exception {
        // Given
        Class<?> componentType = short.class;
        Object value = new short[] {};
        // When
        String result = invokeHandlePrimitivesInArray(componentType, value);
        // Then
        assertEquals("", result);
    }

    @Test
    void testHandlePrimitivesInArrayWithBooleanArrayAllFalse() throws Exception {
        // Given
        Class<?> componentType = boolean.class;
        Object value = new boolean[] { false, false, false };
        // When
        String result = invokeHandlePrimitivesInArray(componentType, value);
        // Then
        assertEquals("false, false, false", result);
    }

    @Test
    void testHandlePrimitivesInArrayWithBooleanArraySingleTrue() throws Exception {
        // Given
        Class<?> componentType = boolean.class;
        Object value = new boolean[] { true };
        // When
        String result = invokeHandlePrimitivesInArray(componentType, value);
        // Then
        assertEquals("true", result);
    }

    @Test
    void testHandlePrimitivesInArrayWithIntArrayNegativeValues() throws Exception {
        // Given
        Class<?> componentType = int.class;
        Object value = new int[] { -1, -2, -3 };
        // When
        String result = invokeHandlePrimitivesInArray(componentType, value);
        // Then
        assertEquals("-1, -2, -3", result);
    }

    @Test
    void testHandlePrimitivesInArrayWithEmptyIntArray() throws Exception {
        // Given
        Class<?> componentType = int.class;
        Object value = new int[] {};
        // When
        String result = invokeHandlePrimitivesInArray(componentType, value);
        // Then
        assertEquals("", result);
    }

    @Test
    void testHandlePrimitivesInArrayWithLongArray() throws Exception {
        // Given
        Class<?> componentType = long.class;
        Object value = new long[] { 10000000000L, 20000000000L, 30000000000L };
        // When
        String result = invokeHandlePrimitivesInArray(componentType, value);
        // Then
        assertEquals("10000000000, 20000000000, 30000000000", result);
    }

    @Test
    void testHandlePrimitivesInArrayWithLongArrayNegativeValues() throws Exception {
        // Given
        Class<?> componentType = long.class;
        Object value = new long[] { -10000000000L, -20000000000L, -30000000000L };
        // When
        String result = invokeHandlePrimitivesInArray(componentType, value);
        // Then
        assertEquals("-10000000000, -20000000000, -30000000000", result);
    }

    @Test
    void testHandlePrimitivesInArrayWithEmptyLongArray() throws Exception {
        // Given
        Class<?> componentType = long.class;
        Object value = new long[] {};
        // When
        String result = invokeHandlePrimitivesInArray(componentType, value);
        // Then
        assertEquals("", result);
    }

    @Test
    void testHandlePrimitivesInArrayWithFloatArray() throws Exception {
        // Given
        Class<?> componentType = float.class;
        Object value = new float[] { 1.1f, 2.2f, 3.3f };
        // When
        String result = invokeHandlePrimitivesInArray(componentType, value);
        // Then
        assertEquals("1.1, 2.2, 3.3", result);
    }

    @Test
    void testHandlePrimitivesInArrayWithFloatArrayNegativeValues() throws Exception {
        // Given
        Class<?> componentType = float.class;
        Object value = new float[] { -1.1f, -2.2f, -3.3f };
        // When
        String result = invokeHandlePrimitivesInArray(componentType, value);
        // Then
        assertEquals("-1.1, -2.2, -3.3", result);
    }

    @Test
    void testHandlePrimitivesInArrayWithEmptyFloatArray() throws Exception {
        // Given
        Class<?> componentType = float.class;
        Object value = new float[] {};
        // When
        String result = invokeHandlePrimitivesInArray(componentType, value);
        // Then
        assertEquals("", result);
    }

    @Test
    void testHandlePrimitivesInArrayWithDoubleArray() throws Exception {
        // Given
        Class<?> componentType = double.class;
        Object value = new double[] { 1.1, 2.2, 3.3 };
        // When
        String result = invokeHandlePrimitivesInArray(componentType, value);
        // Then
        assertEquals("1.1, 2.2, 3.3", result);
    }

    @Test
    void testHandlePrimitivesInArrayWithDoubleArrayNegativeValues() throws Exception {
        // Given
        Class<?> componentType = double.class;
        Object value = new double[] { -1.1, -2.2, -3.3 };
        // When
        String result = invokeHandlePrimitivesInArray(componentType, value);
        // Then
        assertEquals("-1.1, -2.2, -3.3", result);
    }

    @Test
    void testHandlePrimitivesInArrayWithEmptyDoubleArray() throws Exception {
        // Given
        Class<?> componentType = double.class;
        Object value = new double[] {};
        // When
        String result = invokeHandlePrimitivesInArray(componentType, value);
        // Then
        assertEquals("", result);
    }

    @Test
    void testHandlePrimitivesInArrayWithInvalidComponentType() {
        // Given
        Class<?> componentType = String.class;
        Object value = new String[] {};
        // When & Then
        assertThrows(ClassCastException.class, () -> invokeHandlePrimitivesInArray(componentType, value));
    }

    @Test
    void testHandlePrimitivesInArrayWithNullValue() {
        // Given
        Class<?> componentType = int.class;
        Object value = null;
        // When & Then
        assertThrows(NullPointerException.class, () -> invokeHandlePrimitivesInArray(componentType, value));
    }
}
