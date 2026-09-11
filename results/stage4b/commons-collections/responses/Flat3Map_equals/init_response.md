```java
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class Flat3Map_equals_Test {
    private Flat3Map<Integer, String> flat3Map;

    @BeforeAll
    static void setupBeforeAll() {
        // Any global setup can go here, if necessary
    }

    @BeforeEach
    void setupBeforeEach() {
        flat3Map = new Flat3Map<>();
        flat3Map.put(1, "One");
        flat3Map.put(2, "Two");
        flat3Map.put(3, "Three");
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup code for each test can go here
    }

    @AfterAll
    static void teardownAfterAll() {
        // Any global cleanup can go here, if necessary
    }

    @Test
    void testEquals_SameReference() {
        assertTrue(flat3Map.equals(flat3Map));
    }

    @Test
    void testEquals_NullObject() {
        assertFalse(flat3Map.equals(null));
    }

    @Test
    void testEquals_DifferentType() {
        assertFalse(flat3Map.equals("Not a Map"));
    }

    @Test
    void testEquals_EqualMaps() {
        Flat3Map<Integer, String> anotherMap = new Flat3Map<>();
        anotherMap.put(1, "One");
        anotherMap.put(2, "Two");
        anotherMap.put(3, "Three");
        assertTrue(flat3Map.equals(anotherMap));
    }

    @Test
    void testEquals_DifferentMaps() {
        Flat3Map<Integer, String> anotherMap = new Flat3Map<>();
        anotherMap.put(1, "One");
        anotherMap.put(2, "Two");
        anotherMap.put(3, "Different");
        assertFalse(flat3Map.equals(anotherMap));
    }

    @Test
    void testEquals_MapsWithDifferentSizes() {
        Flat3Map<Integer, String> anotherMap = new Flat3Map<>();
        anotherMap.put(1, "One");
        anotherMap.put(2, "Two");
        assertFalse(flat3Map.equals(anotherMap));
    }
}
```