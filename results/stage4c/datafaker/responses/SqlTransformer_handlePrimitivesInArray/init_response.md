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
    private SqlDialect mockDialect;
    private Casing mockCasing;
    private SqlTransformer.Case mockKeywordCase;

    @BeforeAll
    static void setupBeforeAll() {
        // Setup any global resources if necessary
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize mocks and the object under test before each test
        mockDialect = mock(SqlDialect.class);
        mockCasing = mock(Casing.class);
        mockKeywordCase = mock(SqlTransformer.Case.class);
        
        sqlTransformer = new SqlTransformer<>(
            "testSchema", "testTable", '"', mockDialect, "\"", mockCasing, false, 0, mockKeywordCase, false
        );
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup resources if necessary after each test
    }

    @AfterAll
    static void teardownAfterAll() {
        // Cleanup global resources if necessary
    }

    // Example test case for handlePrimitivesInArray method
    @Test
    void testHandlePrimitivesInArray_withIntArray() {
        // Given
        Class<?> componentType = int.class;
        int[] intArray = {1, 2, 3};

        // When
        String result = sqlTransformer.handlePrimitivesInArray(componentType, intArray);

        // Then
        assertEquals("1, 2, 3", result);
    }

    // Additional test cases for other primitive types can be added here
}
```