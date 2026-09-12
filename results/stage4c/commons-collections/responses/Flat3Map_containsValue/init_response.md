```java
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class Flat3Map_containsValue_Test {

    private Flat3Map<Integer, String> flat3Map;

    @BeforeAll
    static void setupBeforeAll() {
        // Setup that needs to be done once before all tests
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize Flat3Map before each test
        flat3Map = new Flat3Map<>();
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup after each test if necessary
    }

    @AfterAll
    static void teardownAfterAll() {
        // Cleanup that needs to be done once after all tests
    }

    @Test
    void testContainsValueInFlatMode() {
        // Given
        flat3Map.put(1, "value1");
        flat3Map.put(2, "value2");
        flat3Map.put(3, "value3");

        // When & Then
        assertTrue(flat3Map.containsValue("value1"));
        assertTrue(flat3Map.containsValue("value2"));
        assertTrue(flat3Map.containsValue("value3"));
        assertFalse(flat3Map.containsValue("value4"));
    }

    @Test
    void testContainsValueInDelegateMode() {
        // Given
        Map<Integer, String> initialMap = new HashMap<>();
        initialMap.put(1, "value1");
        initialMap.put(2, "value2");
        initialMap.put(3, "value3");
        initialMap.put(4, "value4");
        flat3Map = new Flat3Map<>(initialMap);

        // When & Then
        assertTrue(flat3Map.containsValue("value1"));
        assertTrue(flat3Map.containsValue("value2"));
        assertTrue(flat3Map.containsValue("value3"));
        assertTrue(flat3Map.containsValue("value4"));
        assertFalse(flat3Map.containsValue("value5"));
    }

    @Test
    void testContainsNullValueInFlatMode() {
        // Given
        flat3Map.put(1, null);
        flat3Map.put(2, "value2");
        flat3Map.put(3, "value3");

        // When & Then
        assertTrue(flat3Map.containsValue(null));
    }
}
```