package org.apache.commons.collections4.map;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class Flat3Map_containsValue_Test {

    private Flat3Map<Integer, String> flat3Map;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize Flat3Map before each test
        flat3Map = new Flat3Map<>();
    }

    @Test
    void testContainsValueInFlatMode() {
        // Given: Flat3Map with 3 entries
        flat3Map.put(1, "value1");
        flat3Map.put(2, "value2");
        flat3Map.put(3, "value3");
        // When & Then: Check if each value is contained in the map
        assertTrue(flat3Map.containsValue("value1"));
        assertTrue(flat3Map.containsValue("value2"));
        assertTrue(flat3Map.containsValue("value3"));
        assertFalse(flat3Map.containsValue("value4"));
    }

    @Test
    void testContainsValueInDelegateMode() {
        // Given: Flat3Map with more than 3 entries, triggering delegate mode
        Map<Integer, String> initialMap = new HashMap<>();
        initialMap.put(1, "value1");
        initialMap.put(2, "value2");
        initialMap.put(3, "value3");
        initialMap.put(4, "value4");
        flat3Map = new Flat3Map<>(initialMap);
        // When & Then: Check if each value is contained in the map
        assertTrue(flat3Map.containsValue("value1"));
        assertTrue(flat3Map.containsValue("value2"));
        assertTrue(flat3Map.containsValue("value3"));
        assertTrue(flat3Map.containsValue("value4"));
        assertFalse(flat3Map.containsValue("value5"));
    }

    @Test
    void testContainsNullValueInFlatMode() {
        // Given: Flat3Map with a null value
        flat3Map.put(1, null);
        flat3Map.put(2, "value2");
        flat3Map.put(3, "value3");
        // When & Then: Check if null value is contained in the map
        assertTrue(flat3Map.containsValue(null));
    }

    @Test
    void testNullValueNotContained() {
        // Given: Flat3Map with no null values
        flat3Map.put(1, "value1");
        flat3Map.put(2, "value2");
        flat3Map.put(3, "value3");
        // When & Then: Check that null is not contained
        assertFalse(flat3Map.containsValue(null));
    }

    @Test
    void testNonExistentValue() {
        // Given: Flat3Map with specific values
        flat3Map.put(1, "value1");
        flat3Map.put(2, "value2");
        flat3Map.put(3, "value3");
        // When & Then: Check that a non-existent value is not contained
        assertFalse(flat3Map.containsValue("nonExistentValue"));
    }
}
