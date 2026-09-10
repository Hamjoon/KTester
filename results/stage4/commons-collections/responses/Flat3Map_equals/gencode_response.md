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
    private Flat3Map<String, String> flat3Map;

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
        // Test equality with the same Flat3Map instance.
        assertTrue(flat3Map.equals(flat3Map), "The map should be equal to itself");
    }

    @Test
    void testEquals_NullObject() {
        // Test equality with null object.
        assertFalse(flat3Map.equals(null), "The map should not be equal to null");
    }

    @Test
    void testEquals_NonMapObject() {
        // Test equality with a non-Map object.
        String nonMapObject = "Not a map";
        assertFalse(flat3Map.equals(nonMapObject), "A map should not be equal to a non-map object");
    }

    @Test
    void testEquals_EmptyMaps() {
        // Test equality with an empty map when the Flat3Map is empty.
        Flat3Map<String, String> anotherMap = new Flat3Map<>();
        assertTrue(flat3Map.equals(anotherMap), "Two empty maps should be equal");
    }

    @Test
    void testEquals_NonEmptyMapWhenEmpty() {
        // Test equality with a non-empty map when the Flat3Map is empty.
        Flat3Map<String, String> anotherMap = new Flat3Map<>();
        anotherMap.put("keyA", "valueA");
        assertFalse(flat3Map.equals(anotherMap), "An empty map should not be equal to a non-empty map");
    }

    @ParameterizedTest
    @MethodSource("provideMapsForDifferentKeyValuePairs")
    void testEquals_DifferentKeyValuePairs(Map<String, String> otherMap, boolean expected) {
        // Test equality with a map containing different key-value pairs.
        assertEquals(expected, flat3Map.equals(otherMap), "The maps should be equal based on their contents");
    }

    @ParameterizedTest
    @MethodSource("provideMapsForSameKeyValuePairs")
    void testEquals_SameKeyValuePairs(Map<String, String> otherMap, boolean expected) {
        // Test equality with a map containing the same key-value pairs.
        assertEquals(expected, flat3Map.equals(otherMap), "The maps should be equal based on their contents");
    }

    @Test
    void testEquals_MissingKey2() {
        // Test equality with a map missing key2.
        flat3Map.put("keyA", "valueA");
        flat3Map.put("keyB", "valueB");
        Flat3Map<String, String> anotherMap = new Flat3Map<>();
        anotherMap.put("keyA", "valueA");
        assertFalse(flat3Map.equals(anotherMap), "Maps should not be equal when missing a key");
    }

    @Test
    void testEquals_MissingKey3() {
        // Test equality with a map missing key3.
        flat3Map.put("keyA", "valueA");
        flat3Map.put("keyB", "valueB");
        flat3Map.put("keyC", "valueC");
        Flat3Map<String, String> anotherMap = new Flat3Map<>();
        anotherMap.put("keyA", "valueA");
        anotherMap.put("keyB", "valueB");
        assertFalse(flat3Map.equals(anotherMap), "Maps should not be equal when missing a key");
    }

    @Test
    void testEquals_SameKeyValuePairsAll() {
        // Test equality with a map containing all the same key-value pairs.
        flat3Map.put("keyA", "valueA");
        flat3Map.put("keyB", "valueB");
        flat3Map.put("keyC", "valueC");
        Flat3Map<String, String> anotherMap = new Flat3Map<>();
        anotherMap.put("keyA", "valueA");
        anotherMap.put("keyB", "valueB");
        anotherMap.put("keyC", "valueC");
        assertTrue(flat3Map.equals(anotherMap), "Maps with the same contents should be equal");
    }

    // Method sources for parameterized tests
    private static Stream<Arguments> provideMapsForDifferentKeyValuePairs() {
        return Stream.of(
            Arguments.of(Map.of("keyA", "valueA"), false), // Different key-value pair
            Arguments.of(Map.of("keyB", "valueB"), false)  // Different key-value pair
        );
    }

    private static Stream<Arguments> provideMapsForSameKeyValuePairs() {
        return Stream.of(
            Arguments.of(Map.of("keyA", "valueA"), true), // Same key-value pair
            Arguments.of(Map.of("keyB", "valueB"), true)  // Same key-value pair
        );
    }
}
```