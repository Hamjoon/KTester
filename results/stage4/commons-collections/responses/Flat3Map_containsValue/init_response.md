```java
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class Flat3Map_containsValue_Test {
    private Flat3Map<Integer, String> flat3Map;

    @BeforeAll
    static void setupBeforeAll() {
        // Any global setup can be done here
    }

    @BeforeEach
    void setupBeforeEach() {
        flat3Map = new Flat3Map<>();
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup after each test if necessary
    }

    @AfterAll
    static void teardownAfterAll() {
        // Cleanup after all tests if necessary
    }

    @Test
    void testContainsValue_WithExistingValue() {
        flat3Map.put(1, "One");
        flat3Map.put(2, "Two");
        flat3Map.put(3, "Three");
        assertTrue(flat3Map.containsValue("One"), "Should return true for existing value");
    }

    @Test
    void testContainsValue_WithNonExistingValue() {
        flat3Map.put(1, "One");
        flat3Map.put(2, "Two");
        flat3Map.put(3, "Three");
        assertFalse(flat3Map.containsValue("Four"), "Should return false for non-existing value");
    }

    @Test
    void testContainsValue_WithNullValue() {
        flat3Map.put(1, null);
        flat3Map.put(2, "Two");
        assertTrue(flat3Map.containsValue(null), "Should return true when null is stored");
    }

    @Test
    void testContainsValue_WithNullValueNotStored() {
        flat3Map.put(1, "One");
        flat3Map.put(2, "Two");
        assertFalse(flat3Map.containsValue(null), "Should return false when null is not stored");
    }

    @Test
    void testContainsValue_EmptyMap() {
        assertFalse(flat3Map.containsValue("AnyValue"), "Should return false for any value in an empty map");
    }
}
```