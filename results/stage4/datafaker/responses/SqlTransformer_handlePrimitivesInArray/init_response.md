Based on the provided information, I'll complete the test class template for the `SqlTransformer_handlePrimitivesInArray_Test`. This will include initializing necessary fields and dependencies, handling cleanup, and ensuring that the test class adheres to the specified structure. 

Here's the completed test class:

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
        // Initialize the necessary dependencies
        casing = new Casing(); // Assuming a default constructor or a mock
        sqlDialect = new SqlDialect("'", casing);
        keywordCase = new SqlTransformer.Case(); // Assuming a default constructor or a mock
        sqlTransformer = new SqlTransformer<>("schemaName", "tableName", '\'', sqlDialect, "sqlIdentifier", casing, true, 10, keywordCase, false);
    }

    @Test
    void testHandlePrimitivesInArrayWithByteArray() {
        // Given
        Class<?> componentType = byte.class;
        Object value = new byte[]{1, 2, 3};

        // When
        String result = sqlTransformer.handlePrimitivesInArray(componentType, value);

        // Then
        assertEquals("1, 2, 3", result);
    }

    @Test
    void testHandlePrimitivesInArrayWithIntArray() {
        // Given
        Class<?> componentType = int.class;
        Object value = new int[]{4, 5, 6};

        // When
        String result = sqlTransformer.handlePrimitivesInArray(componentType, value);

        // Then
        assertEquals("4, 5, 6", result);
    }

    @Test
    void testHandlePrimitivesInArrayWithBooleanArray() {
        // Given
        Class<?> componentType = boolean.class;
        Object value = new boolean[]{true, false, true};

        // When
        String result = sqlTransformer.handlePrimitivesInArray(componentType, value);

        // Then
        assertEquals("true, false, true", result);
    }

    // Additional tests for other primitive types can be added here

    @AfterEach
    void teardownAfterEach() {
        // Cleanup code, if necessary
    }

    @AfterAll
    static void teardownAfterAll() {
        // Global cleanup code, if necessary
    }
}
```

### Explanation:

1. **Dependencies Initialization**: The `SqlDialect`, `Casing`, and `SqlTransformer.Case` are initialized in the `setupBeforeEach()` method. You might need to adjust the initialization based on actual constructors or mocking requirements.

2. **Test Methods**: Three test methods are provided to check the functionality of `handlePrimitivesInArray` for `byte`, `int`, and `boolean` arrays. You can add additional tests for other primitive types similarly.

3. **Assertions**: Each test method uses assertions to verify that the output of the focal method matches the expected string.

4. **Cleanup Methods**: The `teardownAfterEach()` and `teardownAfterAll()` methods are placeholders for any necessary cleanup, which can be filled in as needed. 

This structure adheres to the requirements of not invoking private methods directly, instead testing the behavior through public interfaces.