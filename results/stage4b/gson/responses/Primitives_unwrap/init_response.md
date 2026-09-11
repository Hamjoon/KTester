```java
package com.google.gson.internal;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class Primitives_unwrap_Test {
    
    // Initialize any necessary fields
    private Class<?> inputClass;

    @BeforeAll
    static void setupBeforeAll() {
        // Perform any setup needed before all tests
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize fields or dependencies before each test
    }

    @Test
    void testUnwrap_Integer() {
        inputClass = Integer.class;
        Class<?> result = Primitives.unwrap(inputClass);
        assertEquals(int.class, result);
    }

    @Test
    void testUnwrap_Float() {
        inputClass = Float.class;
        Class<?> result = Primitives.unwrap(inputClass);
        assertEquals(float.class, result);
    }

    @Test
    void testUnwrap_WrapperType() {
        inputClass = String.class;
        Class<?> result = Primitives.unwrap(inputClass);
        assertEquals(String.class, result);
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup after each test if necessary
    }

    @AfterAll
    static void teardownAfterAll() {
        // Perform any cleanup needed after all tests
    }
}
```