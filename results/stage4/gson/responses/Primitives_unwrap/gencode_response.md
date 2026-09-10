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

    @BeforeAll
    static void setupBeforeAll() {
        // Any global setup can be done here
    }

    @BeforeEach
    void setupBeforeEach() {
        // Any setup that needs to be done before each test can be done here
    }

    @AfterEach
    void teardownAfterEach() {
        // Any cleanup that needs to be done after each test can be done here
    }

    @AfterAll
    static void teardownAfterAll() {
        // Any global cleanup can be done here
    }

    @Test
    void testUnwrap_Integer() {
        // Test unwrapping of Integer wrapper type to its primitive type.
        Class<?> result = Primitives.unwrap(Integer.class);
        assertEquals(int.class, result);
    }

    @Test
    void testUnwrap_Float() {
        // Test unwrapping of Float wrapper type to its primitive type.
        Class<?> result = Primitives.unwrap(Float.class);
        assertEquals(float.class, result);
    }

    @Test
    void testUnwrap_Byte() {
        // Test unwrapping of Byte wrapper type to its primitive type.
        Class<?> result = Primitives.unwrap(Byte.class);
        assertEquals(byte.class, result);
    }

    @Test
    void testUnwrap_Double() {
        // Test unwrapping of Double wrapper type to its primitive type.
        Class<?> result = Primitives.unwrap(Double.class);
        assertEquals(double.class, result);
    }

    @Test
    void testUnwrap_Long() {
        // Test unwrapping of Long wrapper type to its primitive type.
        Class<?> result = Primitives.unwrap(Long.class);
        assertEquals(long.class, result);
    }

    @Test
    void testUnwrap_Character() {
        // Test unwrapping of Character wrapper type to its primitive type.
        Class<?> result = Primitives.unwrap(Character.class);
        assertEquals(char.class, result);
    }

    @Test
    void testUnwrap_Boolean() {
        // Test unwrapping of Boolean wrapper type to its primitive type.
        Class<?> result = Primitives.unwrap(Boolean.class);
        assertEquals(boolean.class, result);
    }

    @Test
    void testUnwrap_Short() {
        // Test unwrapping of Short wrapper type to its primitive type.
        Class<?> result = Primitives.unwrap(Short.class);
        assertEquals(short.class, result);
    }

    @Test
    void testUnwrap_Void() {
        // Test unwrapping of Void type to its primitive type.
        Class<?> result = Primitives.unwrap(Void.class);
        assertEquals(void.class, result);
    }

    @Test
    void testUnwrap_NonWrapper() {
        // Test passing a non-wrapper type to ensure it returns the same type.
        Class<?> result = Primitives.unwrap(String.class);
        assertEquals(String.class, result);
    }

    @Test
    void testUnwrap_Object() {
        // Test passing an Object type to ensure it returns the same type.
        Class<?> result = Primitives.unwrap(Object.class);
        assertEquals(Object.class, result);
    }

    @Test
    void testUnwrap_Null() {
        // Test passing a null value to ensure it returns null.
        Class<?> result = Primitives.unwrap(null);
        assertNull(result);
    }
}
```