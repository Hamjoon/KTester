package org.apache.commons.collections4.map;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;

class Flat3Map_get_Test {

    private Flat3Map<Integer, String> flatMap;

    @BeforeEach
    void setupBeforeEach() {
        flatMap = new Flat3Map<>();
    }

    @AfterEach
    void teardownAfterEach() {
        flatMap.clear();
    }

    @Test
    void testGetWithExistingKey() {
        // Given
        flatMap.put(1, "one");
        flatMap.put(2, "two");
        flatMap.put(3, "three");
        // When
        String value = flatMap.get(2);
        // Then
        assertEquals("two", value);
    }

    @Test
    void testGetWithNonExistingKey() {
        // Given
        flatMap.put(1, "one");
        flatMap.put(2, "two");
        // When
        String value = flatMap.get(3);
        // Then
        assertNull(value);
    }

    @Test
    void testGetWithNullKey() {
        // Given
        flatMap.put(null, "nullValue");
        // When
        String value = flatMap.get(null);
        // Then
        assertEquals("nullValue", value);
    }

    @Test
    void testGetWithDelegateMap() {
        // Given
        Map<Integer, String> delegate = new HashMap<>();
        delegate.put(4, "four");
        flatMap = new Flat3Map<>(delegate);
        // When
        String value = flatMap.get(4);
        // Then
        assertEquals("four", value);
    }

    @Test
    void testGetWithNullKeyAndSize3() {
        // Given
        flatMap.put(null, "value1");
        flatMap.put(2, "value2");
        flatMap.put(3, "value3");
        // When
        String value = flatMap.get(null);
        // Then
        assertEquals("value1", value);
    }

    @Test
    void testGetWithKeysAndSize3() {
        // Given
        flatMap.put(1, "value1");
        flatMap.put(2, "value2");
        flatMap.put(3, "value3");
        // When
        String value1 = flatMap.get(1);
        String value2 = flatMap.get(2);
        String value3 = flatMap.get(3);
        // Then
        assertEquals("value1", value1);
        assertEquals("value2", value2);
        assertEquals("value3", value3);
    }

    @Test
    void testGetWithNonExistentKey() {
        // Given
        flatMap.put(1, "value1");
        flatMap.put(2, "value2");
        flatMap.put(3, "value3");
        // When
        String value = flatMap.get(4);
        // Then
        assertNull(value);
    }

    @Test
    void testGetWithEmptyMap() {
        // When
        String value = flatMap.get(1);
        // Then
        assertNull(value);
    }

    @Test
    void testGetWithSameHashDifferentKey() {
        // Given
        flatMap.put(1, "value1");
        flatMap.put(2, "value2");
        flatMap.put(3, "value3");
        // Assuming key 4 has the same hash as key 1 but is not equal
        // When
        String value = flatMap.get(4);
        // Then
        assertNull(value);
    }
}
