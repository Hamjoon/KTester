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
        // Any setup that needs to be done once before all tests
    }

    @BeforeEach
    void setupBeforeEach() {
        flat3Map = new Flat3Map<>(); // Initialize the Flat3Map before each test
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup that needs to be done after each test
    }

    @AfterAll
    static void teardownAfterAll() {
        // Any cleanup that needs to be done once after all tests
    }

    @Test
    void testContainsValueWhenValueExists() {
        flat3Map.put(1, "One");
        flat3Map.put(2, "Two");
        flat3Map.put(3, "Three");

        assertTrue(flat3Map.containsValue("One")); // Test for existing value
        assertTrue(flat3Map.containsValue("Two")); // Test for existing value
        assertTrue(flat3Map.containsValue("Three")); // Test for existing value
    }

    @Test
    void testContainsValueWhenValueDoesNotExist() {
        flat3Map.put(1, "One");
        flat3Map.put(2, "Two");

        assertFalse(flat3Map.containsValue("Three")); // Test for non-existing value
    }

    @Test
    void testContainsValueWhenValueIsNull() {
        flat3Map.put(1, null);
        flat3Map.put(2, "Two");
        
        assertTrue(flat3Map.containsValue(null)); // Test for null value
    }

    @Test
    void testContainsValueWithEmptyMap() {
        assertFalse(flat3Map.containsValue("AnyValue")); // Test on empty map
    }
}
```