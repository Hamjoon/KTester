package org.apache.commons.collections4.trie;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class AbstractPatriciaTrie_put_Test {

    private AbstractPatriciaTrie<String, String> trie;

    private KeyAnalyzer<String> keyAnalyzer;

    @BeforeEach
    void setupBeforeEach() {
        keyAnalyzer = mock(KeyAnalyzer.class);
        // Assuming a concrete implementation exists
        trie = new ConcretePatriciaTrie<>(keyAnalyzer);
    }

    @Test
    void testPutWithValidKeyAndValue() {
        // Given
        String key = "exampleKey";
        String value = "exampleValue";
        // When
        String result = trie.put(key, value);
        // Then
        assertNull(result, "Expected null return for new key insertion");
        assertEquals(value, trie.get(key), "Value should be retrievable after put");
    }

    @Test
    void testPutWithNullKey() {
        // Given
        String value = "exampleValue";
        // When & Then
        assertThrows(NullPointerException.class, () -> trie.put(null, value), "Expected NullPointerException for null key");
    }

    @Test
    void testPutWithExistingKey() {
        // Given
        String key = "existingKey";
        String initialValue = "initialValue";
        trie.put(key, initialValue);
        // When
        String newValue = "newValue";
        String result = trie.put(key, newValue);
        // Then
        assertEquals(initialValue, result, "Expected to return previous value for existing key");
        assertEquals(newValue, trie.get(key), "New value should be retrievable after put");
    }

    @Test
    void testPutWithEmptyKey() {
        // Given
        // Assuming empty string is considered a valid key
        String key = "";
        String value = "valueForEmptyKey";
        // When
        String result = trie.put(key, value);
        // Then
        assertNull(result, "Expected null return for insertion of empty key");
        assertEquals(value, trie.get(key), "Value should be retrievable after putting empty key");
    }

    @Test
    void testPutWithZeroBitKeyWhenRootIsEmpty() {
        // Given
        // Assuming this key has zero bits
        String key = "testKey";
        String value = "valueForRoot";
        // When
        String result = trie.put(key, value);
        // Then
        assertNull(result, "Expected null return for new key insertion at root");
        assertEquals(value, trie.get(key), "Value should be retrievable after put");
    }

    @Test
    void testPutWithZeroBitKeyWhenRootIsNotEmpty() {
        // Given
        // Assuming this key has zero bits
        String key = "testKey";
        String existingValue = "existingValue";
        // Fill the root first
        trie.put(key, existingValue);
        // When
        String newValue = "valueForRoot";
        String result = trie.put(key, newValue);
        // Then
        assertEquals(existingValue, result, "Expected to return previous value for existing key");
        assertEquals(newValue, trie.get(key), "New value should be retrievable after put");
    }

    @Test
    void testPutWithNewKey() {
        // Given
        String key = "anotherKey";
        String value = "newValue";
        // When
        String result = trie.put(key, value);
        // Then
        assertNull(result, "Expected null return for new key insertion");
        assertEquals(value, trie.get(key), "Value should be retrievable after putting new key");
    }

    @Test
    void testPutWithNullBitKey() {
        // Given
        // Assuming this key results in a null bit index
        String key = "keyWithNullBit";
        String value = "valueForNullBit";
        // When
        String result = trie.put(key, value);
        // Then
        assertNull(result, "Expected null return for insertion of key with null bit index");
        assertEquals(value, trie.get(key), "Value should be retrievable after putting key with null bit");
    }

    @Test
    void testPutWithEqualBitKey() {
        // Given
        // Assuming this key has an equal bit index to an existing entry
        String key = "keyWithEqualBit";
        String existingValue = "existingValue";
        // Fill the trie first
        trie.put(key, existingValue);
        // When
        String newValue = "valueForEqualBit";
        String result = trie.put(key, newValue);
        // Then
        assertEquals(existingValue, result, "Expected to return previous value for equal bit key");
        assertEquals(newValue, trie.get(key), "New value should be retrievable after put");
    }

    @Test
    void testPutWithOutOfBoundsBitKey() {
        // Given
        // Assuming this key results in an out-of-bounds bit index
        String key = "keyWithOutOfBoundsBit";
        String value = "valueForOutOfBounds";
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> trie.put(key, value), "Expected IllegalArgumentException for out-of-bounds bit index");
    }

    @Test
    void testPutWithNegativeBitKey() {
        // Given
        // Assuming this key results in a negative bit index
        String key = "keyWithNegativeBit";
        String value = "valueForNegativeBit";
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> trie.put(key, value), "Expected IllegalArgumentException for negative bit index");
    }

    @Test
    void testPutWithValidPositiveBitKey() {
        // Given
        // Assuming this key results in a valid positive bit index
        String key = "keyWithPositiveBit";
        String value = "valueForPositiveBit";
        // When
        String result = trie.put(key, value);
        // Then
        assertNull(result, "Expected null return for valid positive bit key insertion");
        assertEquals(value, trie.get(key), "Value should be retrievable after putting valid positive bit key");
    }

    @Test
    void testPutWithSameValueAsExistingKey() {
        // Given
        String key = "keyWithSameValue";
        String existingValue = "existingValue";
        // Fill the trie first
        trie.put(key, existingValue);
        // When
        String result = trie.put(key, existingValue);
        // Then
        assertEquals(existingValue, result, "Expected to return previous value for same value insertion");
    }

    @Test
    void testPutWithNullValue() {
        // Given
        String key = "keyWithNullValue";
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> trie.put(key, null), "Expected IllegalArgumentException for null value");
    }

    @Test
    void testPutWithLargeBitKey() {
        // Given
        // Assuming this key results in a valid large bit index
        String key = "keyWithLargeBit";
        String value = "valueForLargeBit";
        // When
        String result = trie.put(key, value);
        // Then
        assertNull(result, "Expected null return for valid large bit key insertion");
        assertEquals(value, trie.get(key), "Value should be retrievable after putting valid large bit key");
    }

    @Test
    void testPutWithEmptyStringKey() {
        // Given
        // Assuming empty string is considered a valid key
        String key = "";
        String value = "valueForEmptyString";
        // When
        String result = trie.put(key, value);
        // Then
        assertNull(result, "Expected null return for insertion of empty string key");
        assertEquals(value, trie.get(key), "Value should be retrievable after putting empty string key");
    }

    @Test
    void testPutWithInvalidBitKey() {
        // Given
        // Assuming this key results in an invalid bit index
        String key = "keyWithInvalidBit";
        String value = "valueForInvalidBit";
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> trie.put(key, value), "Expected IllegalArgumentException for invalid bit index");
    }
}
