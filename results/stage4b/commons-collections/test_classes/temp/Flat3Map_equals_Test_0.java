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

    @BeforeEach
    void setupBeforeEach() {
        flat3Map = new Flat3Map<>();
        flat3Map.put(1, "One");
        flat3Map.put(2, "Two");
        flat3Map.put(3, "Three");
    }

    @Test
    void testEquals_SameReference() {
        // Test equals with the same object reference, should return true.
        assertTrue(flat3Map.equals(flat3Map));
    }

    @Test
    void testEquals_NullObject() {
        // Test equals with a null object, should return false.
        assertFalse(flat3Map.equals(null));
    }

    @Test
    void testEquals_DifferentType() {
        // Test equals with a non-Map object, should return false.
        assertFalse(flat3Map.equals("Not a Map"));
    }

    @Test
    void testEquals_EqualMaps() {
        // Test equals with another equal map, should return true.
        Flat3Map<Integer, String> anotherMap = new Flat3Map<>();
        anotherMap.put(1, "One");
        anotherMap.put(2, "Two");
        anotherMap.put(3, "Three");
        assertTrue(flat3Map.equals(anotherMap));
    }

    @Test
    void testEquals_DifferentMaps() {
        // Test equals with another map having different values, should return false.
        Flat3Map<Integer, String> anotherMap = new Flat3Map<>();
        anotherMap.put(1, "One");
        anotherMap.put(2, "Two");
        anotherMap.put(3, "Different");
        assertFalse(flat3Map.equals(anotherMap));
    }

    @Test
    void testEquals_MapsWithDifferentSizes() {
        // Test equals with another map of different size, should return false.
        Flat3Map<Integer, String> anotherMap = new Flat3Map<>();
        anotherMap.put(1, "One");
        anotherMap.put(2, "Two");
        assertFalse(flat3Map.equals(anotherMap));
    }

    @Test
    void testEquals_DelegateMap() {
        // Test equals when delegateMap is not null, should delegate comparison to delegateMap.
        AbstractHashedMap<Integer, String> mockedDelegateMap = mock(AbstractHashedMap.class);
        flat3Map.delegateMap = mockedDelegateMap;
        when(mockedDelegateMap.equals(any())).thenReturn(true);
        assertTrue(flat3Map.equals(mockedDelegateMap));
        when(mockedDelegateMap.equals(any())).thenReturn(false);
        assertFalse(flat3Map.equals(mockedDelegateMap));
    }

    @Test
    void testEquals_EmptyMap() {
        // Test equals with another empty map, should return true.
        Flat3Map<Integer, String> anotherMap = new Flat3Map<>();
        assertTrue(flat3Map.equals(anotherMap));
    }

    @Test
    void testEquals_MapWithDifferentSize() {
        // Test equals with another map of different size, should return false.
        Map<Integer, String> mapWithDifferentSize = new HashMap<>();
        mapWithDifferentSize.put(1, "One");
        mapWithDifferentSize.put(2, "Two");
        assertFalse(flat3Map.equals(mapWithDifferentSize));
    }

    @Test
    void testEquals_SameSizeButDifferentKeys() {
        // Test equals with another map of the same size but different keys, should return false.
        Map<Integer, String> mapWithSameSizeButDifferentKeys = new HashMap<>();
        mapWithSameSizeButDifferentKeys.put(4, "Four");
        mapWithSameSizeButDifferentKeys.put(5, "Five");
        mapWithSameSizeButDifferentKeys.put(6, "Six");
        assertFalse(flat3Map.equals(mapWithSameSizeButDifferentKeys));
    }

    @Test
    void testEquals_SameSizeAndKeysButDifferentValues() {
        // Test equals with another map having same size and keys but different values, should return false.
        Map<Integer, String> mapWithSameSizeAndKeysButDifferentValues = new HashMap<>();
        mapWithSameSizeAndKeysButDifferentValues.put(1, "One");
        mapWithSameSizeAndKeysButDifferentValues.put(2, "Two");
        mapWithSameSizeAndKeysButDifferentValues.put(3, "Different");
        assertFalse(flat3Map.equals(mapWithSameSizeAndKeysButDifferentValues));
    }

    @Test
    void testEquals_SameSizeAndKeysAndValues() {
        // Test equals with another map having same size, keys, and values, should return true.
        Map<Integer, String> mapWithSameSizeAndKeysAndValues = new HashMap<>();
        mapWithSameSizeAndKeysAndValues.put(1, "One");
        mapWithSameSizeAndKeysAndValues.put(2, "Two");
        mapWithSameSizeAndKeysAndValues.put(3, "Three");
        assertTrue(flat3Map.equals(mapWithSameSizeAndKeysAndValues));
    }

    @Test
    void testEquals_SameSizeAndKeysButPartialValues() {
        // Test equals with another map having same size and keys but only some values matching, should return false.
        Map<Integer, String> mapWithSameSizeAndPartialValues = new HashMap<>();
        mapWithSameSizeAndPartialValues.put(1, "One");
        mapWithSameSizeAndPartialValues.put(2, "Different");
        mapWithSameSizeAndPartialValues.put(3, "Three");
        assertFalse(flat3Map.equals(mapWithSameSizeAndPartialValues));
    }
}
