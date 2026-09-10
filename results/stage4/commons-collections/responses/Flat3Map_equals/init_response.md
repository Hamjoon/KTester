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
        // Any global setup can be done here
    }

    @BeforeEach
    void setupBeforeEach() {
        flat3Map = new Flat3Map<>();
    }

    @AfterEach
    void teardownAfterEach() {
        flat3Map.clear(); // Clear the map after each test
    }

    @AfterAll
    static void teardownAfterAll() {
        // Any global cleanup can be done here
    }

    @Test
    void testEquals_SameObject() {
        assertTrue(flat3Map.equals(flat3Map), "The map should be equal to itself");
    }

    @Test
    void testEquals_EmptyMaps() {
        Flat3Map<Integer, String> anotherMap = new Flat3Map<>();
        assertTrue(flat3Map.equals(anotherMap), "Two empty maps should be equal");
    }

    @Test
    void testEquals_NonMapObject() {
        String nonMapObject = "Not a map";
        assertFalse(flat3Map.equals(nonMapObject), "A map should not be equal to a non-map object");
    }

    @Test
    void testEquals_MapsWithDifferentSizes() {
        Flat3Map<Integer, String> anotherMap = new Flat3Map<>();
        anotherMap.put(1, "One");
        assertFalse(flat3Map.equals(anotherMap), "Maps of different sizes should not be equal");
    }

    @Test
    void testEquals_MapsWithSameSizeDifferentContents() {
        flat3Map.put(1, "One");
        flat3Map.put(2, "Two");
        Flat3Map<Integer, String> anotherMap = new Flat3Map<>();
        anotherMap.put(1, "Uno");
        anotherMap.put(2, "Dos");
        assertFalse(flat3Map.equals(anotherMap), "Maps with the same size but different contents should not be equal");
    }

    @Test
    void testEquals_MapsWithSameContents() {
        flat3Map.put(1, "One");
        flat3Map.put(2, "Two");
        Flat3Map<Integer, String> anotherMap = new Flat3Map<>();
        anotherMap.put(1, "One");
        anotherMap.put(2, "Two");
        assertTrue(flat3Map.equals(anotherMap), "Maps with the same contents should be equal");
    }

    @Test
    void testEquals_MapsWithDifferentKeys() {
        flat3Map.put(1, "One");
        flat3Map.put(2, "Two");
        Flat3Map<Integer, String> anotherMap = new Flat3Map<>();
        anotherMap.put(3, "Three");
        anotherMap.put(4, "Four");
        assertFalse(flat3Map.equals(anotherMap), "Maps with different keys should not be equal");
    }
}
```