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

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the Flat3Map before each test
        flat3Map = new Flat3Map<>();
    }

    @Test
    void testContainsValueWhenValueExists() {
        flat3Map.put(1, "One");
        flat3Map.put(2, "Two");
        flat3Map.put(3, "Three");
        // Test for existing value
        assertTrue(flat3Map.containsValue("One"));
        // Test for existing value
        assertTrue(flat3Map.containsValue("Two"));
        // Test for existing value
        assertTrue(flat3Map.containsValue("Three"));
    }

    @Test
    void testContainsValueWhenValueDoesNotExist() {
        flat3Map.put(1, "One");
        flat3Map.put(2, "Two");
        // Test for non-existing value
        assertFalse(flat3Map.containsValue("Three"));
    }

    @Test
    void testContainsValueWhenValueIsNull() {
        flat3Map.put(1, null);
        flat3Map.put(2, "Two");
        // Test for null value
        assertTrue(flat3Map.containsValue(null));
    }

    @Test
    void testContainsValueWithEmptyMap() {
        // Test on empty map
        assertFalse(flat3Map.containsValue("AnyValue"));
    }

    @Test
    void testContainsValueWithDelegateMap() {
        // Mocking the delegateMap
        AbstractHashedMap<Integer, String> mockDelegateMap = mock(AbstractHashedMap.class);
        // Set the mock delegateMap
        flat3Map.delegateMap = mockDelegateMap;
        // Test when delegateMap contains the value
        when(mockDelegateMap.containsValue("someValue")).thenReturn(true);
        // Should return true
        assertTrue(flat3Map.containsValue("someValue"));
        // Test when delegateMap does not contain the value
        when(mockDelegateMap.containsValue("nonExistentValue")).thenReturn(false);
        // Should return false
        assertFalse(flat3Map.containsValue("nonExistentValue"));
    }

    @Test
    void testContainsValueWithNullValue() {
        // Test cases for null values with different sizes
        flat3Map.size = 3;
        flat3Map.value3 = null;
        // Should return true
        assertTrue(flat3Map.containsValue(null));
        flat3Map.size = 2;
        flat3Map.value2 = null;
        // Should return true
        assertTrue(flat3Map.containsValue(null));
        flat3Map.size = 1;
        flat3Map.value1 = null;
        // Should return true
        assertTrue(flat3Map.containsValue(null));
        // When values are not null
        flat3Map.size = 3;
        flat3Map.value3 = "value3";
        // Should return false
        assertFalse(flat3Map.containsValue(null));
        flat3Map.size = 2;
        flat3Map.value2 = "value2";
        // Should return false
        assertFalse(flat3Map.containsValue(null));
        flat3Map.size = 1;
        flat3Map.value1 = "value1";
        // Should return false
        assertFalse(flat3Map.containsValue(null));
    }

    @Test
    void testContainsValueWithValuesPresent() {
        // Test cases for when values are present
        flat3Map.put(1, "value1");
        // Should return true
        assertTrue(flat3Map.containsValue("value1"));
        flat3Map.put(2, "value2");
        // Should return true
        assertTrue(flat3Map.containsValue("value2"));
        flat3Map.put(3, "value3");
        // Should return true
        assertTrue(flat3Map.containsValue("value3"));
    }

    @Test
    void testContainsValueWithBoundaryConditions() {
        // Test containsValue with null when size is 0
        flat3Map.size = 0;
        // Should return false
        assertFalse(flat3Map.containsValue(null));
        // Test cases for null values when size is 3, 2, and 1
        flat3Map.size = 3;
        flat3Map.value3 = null;
        // Should return true
        assertTrue(flat3Map.containsValue(null));
        flat3Map.size = 2;
        flat3Map.value2 = null;
        // Should return true
        assertTrue(flat3Map.containsValue(null));
        flat3Map.size = 1;
        flat3Map.value1 = null;
        // Should return true
        assertTrue(flat3Map.containsValue(null));
    }
}
