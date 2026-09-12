```java
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;

class Flat3Map_equals_Test {

    private Flat3Map<Integer, String> flat3Map;

    @BeforeAll
    static void setupBeforeAll() {
        // Any setup required before all tests are run
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize a new Flat3Map before each test
        flat3Map = new Flat3Map<>();
    }

    @AfterEach
    void teardownAfterEach() {
        // Any cleanup required after each test
        flat3Map = null;
    }

    @AfterAll
    static void teardownAfterAll() {
        // Any cleanup required after all tests are run
    }

    @Test
    void testEquals_SameInstance() {
        assertTrue(flat3Map.equals(flat3Map), "A Flat3Map should be equal to itself");
    }

    @Test
    void testEquals_DifferentInstanceSameContent() {
        Map<Integer, String> otherMap = new HashMap<>();
        otherMap.put(1, "One");
        otherMap.put(2, "Two");
        otherMap.put(3, "Three");

        flat3Map.put(1, "One");
        flat3Map.put(2, "Two");
        flat3Map.put(3, "Three");

        assertTrue(flat3Map.equals(otherMap), "Flat3Map should be equal to another map with the same content");
    }

    @Test
    void testEquals_DifferentInstanceDifferentContent() {
        Map<Integer, String> otherMap = new HashMap<>();
        otherMap.put(1, "One");
        otherMap.put(2, "Two");

        flat3Map.put(1, "One");
        flat3Map.put(2, "Two");
        flat3Map.put(3, "Three");

        assertFalse(flat3Map.equals(otherMap), "Flat3Map should not be equal to another map with different content");
    }

    @Test
    void testEquals_Null() {
        assertFalse(flat3Map.equals(null), "Flat3Map should not be equal to null");
    }

    @Test
    void testEquals_DifferentType() {
        assertFalse(flat3Map.equals("A String"), "Flat3Map should not be equal to an object of a different type");
    }
}
```