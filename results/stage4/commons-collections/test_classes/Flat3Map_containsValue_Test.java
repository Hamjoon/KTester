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
        flat3Map = new Flat3Map<>();
    }

    // Helper method to set private fields using reflection
    private void setField(Object obj, String fieldName, Object value) throws Exception {
        var field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
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

    // Tests based on the provided JSON test cases
    @Test
    void testContainsValue_NullValueWithSizeThree() throws Exception {
        setField(flat3Map, "size", 3);
        setField(flat3Map, "value3", null);
        assertTrue(flat3Map.containsValue(null), "Should return true when value is null and size is 3 with value3 also null.");
    }

    @Test
    void testContainsValue_NullValueWithSizeTwo() throws Exception {
        setField(flat3Map, "size", 2);
        setField(flat3Map, "value2", null);
        assertTrue(flat3Map.containsValue(null), "Should return true when value is null and size is 2 with value2 also null.");
    }

    @Test
    void testContainsValue_NullValueWithSizeOne() throws Exception {
        setField(flat3Map, "size", 1);
        setField(flat3Map, "value1", null);
        assertTrue(flat3Map.containsValue(null), "Should return true when value is null and size is 1 with value1 also null.");
    }

    @Test
    void testContainsValue_NonNullValueWithSizeThree() throws Exception {
        setField(flat3Map, "size", 3);
        setField(flat3Map, "value2", "Two");
        setField(flat3Map, "value3", "Three");
        assertFalse(flat3Map.containsValue("Four"), "Should return false when value is non-null and size is 3 with no matching value.");
    }

    @Test
    void testContainsValue_ExistingValueWithSizeThree() throws Exception {
        setField(flat3Map, "size", 3);
        setField(flat3Map, "value3", "Three");
        assertTrue(flat3Map.containsValue("Three"), "Should return true when value matches value3 and size is 3.");
    }

    @Test
    void testContainsValue_ExistingValueWithSizeTwo() throws Exception {
        setField(flat3Map, "size", 2);
        setField(flat3Map, "value2", "Two");
        assertTrue(flat3Map.containsValue("Two"), "Should return true when value matches value2 and size is 2.");
    }

    @Test
    void testContainsValue_ExistingValueWithSizeOne() throws Exception {
        setField(flat3Map, "size", 1);
        setField(flat3Map, "value1", "One");
        assertTrue(flat3Map.containsValue("One"), "Should return true when value matches value1 and size is 1.");
    }

    @Test
    void testContainsValue_NonMatchingValueWithSizeOne() throws Exception {
        setField(flat3Map, "size", 1);
        setField(flat3Map, "value1", "One");
        assertFalse(flat3Map.containsValue("Two"), "Should return false when value is non-matching and size is 1.");
    }

    @Test
    void testContainsValue_EmptyDelegateMapAndSizeZero() throws Exception {
        setField(flat3Map, "size", 0);
        assertFalse(flat3Map.containsValue("AnyValue"), "Should return false when the delegateMap is null and size is 0.");
    }
}
