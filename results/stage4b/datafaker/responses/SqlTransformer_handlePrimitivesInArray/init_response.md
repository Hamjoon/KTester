Based on the provided information, here is the complete test class template for testing the `handlePrimitivesInArray` method of the `SqlTransformer` class. The test class includes the necessary setup and teardown methods, as well as the structure to add test cases for the focal method. 

```java
package net.datafaker.transformations.sql;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

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

    @Test
    void testHandlePrimitivesInArray_withByteArray() {
        byte[] input = {1, 2, 3};
        String result = sqlTransformer.handlePrimitivesInArray(byte.class, input);
        assertEquals("1, 2, 3", result);
    }

    @Test
    void testHandlePrimitivesInArray_withShortArray() {
        short[] input = {1, 2, 3};
        String result = sqlTransformer.handlePrimitivesInArray(short.class, input);
        assertEquals("1, 2, 3", result);
    }

    @Test
    void testHandlePrimitivesInArray_withIntArray() {
        int[] input = {1, 2, 3};
        String result = sqlTransformer.handlePrimitivesInArray(int.class, input);
        assertEquals("1, 2, 3", result);
    }

    @Test
    void testHandlePrimitivesInArray_withLongArray() {
        long[] input = {1L, 2L, 3L};
        String result = sqlTransformer.handlePrimitivesInArray(long.class, input);
        assertEquals("1, 2, 3", result);
    }

    @Test
    void testHandlePrimitivesInArray_withFloatArray() {
        float[] input = {1.1f, 2.2f, 3.3f};
        String result = sqlTransformer.handlePrimitivesInArray(float.class, input);
        assertEquals("1.1, 2.2, 3.3", result);
    }

    @Test
    void testHandlePrimitivesInArray_withDoubleArray() {
        double[] input = {1.1, 2.2, 3.3};
        String result = sqlTransformer.handlePrimitivesInArray(double.class, input);
        assertEquals("1.1, 2.2, 3.3", result);
    }

    @Test
    void testHandlePrimitivesInArray_withBooleanArray() {
        boolean[] input = {true, false, true};
        String result = sqlTransformer.handlePrimitivesInArray(boolean.class, input);
        assertEquals("true, false, true", result);
    }
}
```

### Explanation:
1. **Imports**: The necessary JUnit and Mockito imports are included.
2. **Setup Methods**: 
   - `setupBeforeAll()` for any global setup.
   - `setupBeforeEach()` initializes the `SqlTransformer` instance and its dependencies before each test.
3. **Teardown Methods**: 
   - `teardownAfterEach()` and `teardownAfterAll()` can be used for cleanup if needed.
4. **Tests**: Each test method checks the output of the `handlePrimitivesInArray` method for different primitive array types, ensuring that the method behaves as expected.

This structure allows for comprehensive unit testing of the `handlePrimitivesInArray` method while adhering to best practices in Java testing.