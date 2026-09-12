package org.apache.commons.collections4.map;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;

class Flat3Map_put_Test {

    private Flat3Map<Integer, String> map;

    @BeforeEach
    void setupBeforeEach() {
        map = new Flat3Map<>();
    }

    @Test
    void testPutSingleElement() {
        // Given
        Integer key = 1;
        String value = "One";
        // When
        String oldValue = map.put(key, value);
        // Then
        assertNull(oldValue);
        assertEquals(1, map.size());
        assertEquals(value, map.get(key));
    }

    @Test
    void testPutMultipleElements() {
        // Given
        map.put(1, "One");
        map.put(2, "Two");
        // When
        String oldValue = map.put(3, "Three");
        // Then
        assertNull(oldValue);
        assertEquals(3, map.size());
        assertEquals("Three", map.get(3));
    }

    @Test
    void testReplaceExistingElement() {
        // Given
        map.put(1, "One");
        map.put(2, "Two");
        // When
        String oldValue = map.put(1, "NewOne");
        // Then
        assertEquals("One", oldValue);
        assertEquals(2, map.size());
        assertEquals("NewOne", map.get(1));
    }

    @Test
    void testPutExceedsFlatMode() {
        // Given
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        // When
        map.put(4, "Four");
        // Then
        assertEquals(4, map.size());
        assertEquals("Four", map.get(4));
    }

    @Test
    void testPutAll() {
        // Given
        Map<Integer, String> otherMap = new HashMap<>();
        otherMap.put(1, "One");
        otherMap.put(2, "Two");
        otherMap.put(3, "Three");
        // When
        map.putAll(otherMap);
        // Then
        assertEquals(3, map.size());
        assertEquals("One", map.get(1));
        assertEquals("Two", map.get(2));
        assertEquals("Three", map.get(3));
    }

    @Test
    void testPutNullKeyWhenEmpty() {
        // Test case: Add a new entry with a null key when the map is empty.
        // Given
        String key = null;
        String value = "value1";
        // When
        String oldValue = map.put(key, value);
        // Then
        assertNull(oldValue);
        assertEquals(1, map.size());
        assertEquals(value, map.get(key));
    }

    @Test
    void testUpdateNullKeyWhenOneEntry() {
        // Test case: Update the existing entry with a null key when the map has one entry with a null key.
        // Given
        map.put(null, "value1");
        // When
        String oldValue = map.put(null, "value2");
        // Then
        assertEquals("value1", oldValue);
        assertEquals(1, map.size());
        assertEquals("value2", map.get(null));
    }

    @Test
    void testPutNonNullKeyWhenEmpty() {
        // Test case: Add a new entry with a non-null key when the map is empty.
        // Given
        String key = "key1";
        String value = "value1";
        // When
        String oldValue = map.put(key, value);
        // Then
        assertNull(oldValue);
        assertEquals(1, map.size());
        assertEquals(value, map.get(key));
    }

    @Test
    void testUpdateNonNullKeyWhenOneEntry() {
        // Test case: Update the existing entry with a non-null key when the map has one entry.
        // Given
        map.put("key1", "value1");
        // When
        String oldValue = map.put("key1", "value2");
        // Then
        assertEquals("value1", oldValue);
        assertEquals(1, map.size());
        assertEquals("value2", map.get("key1"));
    }

    @Test
    void testPutDifferentNonNullKeyWhenOneEntry() {
        // Test case: Add a new entry with a different non-null key when the map has one entry.
        // Given
        map.put("key1", "value1");
        // When
        String oldValue = map.put("key2", "value2");
        // Then
        assertNull(oldValue);
        assertEquals(2, map.size());
        assertEquals("value2", map.get("key2"));
    }

    @Test
    void testUpdateNonNullKeyWhenTwoEntries() {
        // Test case: Update the existing entry with a non-null key when the map has two entries.
        // Given
        map.put("key1", "value1");
        map.put("key2", "value2");
        // When
        String oldValue = map.put("key2", "value2_updated");
        // Then
        assertEquals("value2", oldValue);
        assertEquals(2, map.size());
        assertEquals("value2_updated", map.get("key2"));
    }

    @Test
    void testPutDifferentNonNullKeyWhenTwoEntries() {
        // Test case: Add a new entry with a different non-null key when the map has two entries.
        // Given
        map.put("key1", "value1");
        map.put("key2", "value2");
        // When
        String oldValue = map.put("key3", "value3");
        // Then
        assertNull(oldValue);
        assertEquals(3, map.size());
        assertEquals("value3", map.get("key3"));
    }

    @Test
    void testUpdateNonNullKeyWhenThreeEntries() {
        // Test case: Update the existing entry with a non-null key when the map has three entries.
        // Given
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        // When
        String oldValue = map.put("key3", "value3_updated");
        // Then
        assertEquals("value3", oldValue);
        assertEquals(3, map.size());
        assertEquals("value3_updated", map.get("key3"));
    }

    @Test
    void testPutDifferentNonNullKeyCausingConversion() {
        // Test case: Add a new entry with a different non-null key causing the map to convert to a delegate map.
        // Given
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        // When
        String oldValue = map.put("key4", "value4");
        // Then
        assertNull(oldValue);
        assertEquals(4, map.size());
        assertEquals("value4", map.get("key4"));
    }

    @Test
    void testPutNullKeyCausingConversion() {
        // Test case: Add a new entry with a null key when the map has three entries, causing conversion to a delegate map.
        // Given
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        // When
        String oldValue = map.put(null, "value_null");
        // Then
        assertNull(oldValue);
        assertEquals(4, map.size());
        assertEquals("value_null", map.get(null));
    }

    @Test
    void testUpdateNonNullKeyToNullValueWhenOneEntry() {
        // Test case: Update an existing entry with a non-null key to have a null value when the map has one entry.
        // Given
        map.put("key1", "value1");
        // When
        String oldValue = map.put("key1", null);
        // Then
        assertEquals("value1", oldValue);
        assertEquals(1, map.size());
        assertNull(map.get("key1"));
    }

    @Test
    void testPutNullKeyAndNullValueWhenEmpty() {
        // Test case: Add a new entry with both a null key and a null value when the map is empty.
        // Given
        String key = null;
        String value = null;
        // When
        String oldValue = map.put(key, value);
        // Then
        assertNull(oldValue);
        assertEquals(1, map.size());
        assertNull(map.get(key));
    }

    @Test
    void testUpdateNonNullKeyToNullValueWhenTwoEntries() {
        // Test case: Update an existing entry with a non-null key to have a null value when the map has two entries.
        // Given
        map.put("key1", "value1");
        map.put("key2", "value2");
        // When
        String oldValue = map.put("key2", null);
        // Then
        assertEquals("value2", oldValue);
        assertEquals(2, map.size());
        assertNull(map.get("key2"));
    }

    @Test
    void testUpdateNonNullKeyToNullValueWhenThreeEntries() {
        // Test case: Update an existing entry with a non-null key to have a null value when the map has three entries.
        // Given
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        // When
        String oldValue = map.put("key3", null);
        // Then
        assertEquals("value3", oldValue);
        assertEquals(3, map.size());
        assertNull(map.get("key3"));
    }

    @Test
    void testPutNonNullKeyAndNullValueCausingConversion() {
        // Test case: Add a new entry with a non-null key and a null value causing the map to convert to a delegate map.
        // Given
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        // When
        String oldValue = map.put("key4", null);
        // Then
        assertNull(oldValue);
        assertEquals(4, map.size());
        assertNull(map.get("key4"));
    }
}
