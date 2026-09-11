package org.apache.commons.collections4.trie;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AbstractPatriciaTrie_put_Test {

    private AbstractPatriciaTrie<String, String> trie;

    private KeyAnalyzer<String> keyAnalyzer;

    @BeforeEach
    void setupBeforeEach() {
        keyAnalyzer = mock(KeyAnalyzer.class);
        trie = new AbstractPatriciaTrie<>(keyAnalyzer);
    }

    @Test
    void testPutWithNullKey() {
        // Test case where the key is null. Expecting a NullPointerException to be thrown.
        assertThrows(NullPointerException.class, () -> {
            trie.put(null, "someValue");
        });
    }

    @Test
    void testPutWithEmptyKey() {
        // Test case where the key has zero length. If the root is empty, it should store the key-value pair.
        String result = trie.put("", "someValue");
        // Assuming the root is empty initially
        assertNull(result);
        // Test case where the key has zero length and the root is not empty.
        trie.put("", "anotherValue");
        result = trie.put("", "anotherValue");
        // The value at the root should be updated
        assertEquals("anotherValue", result);
    }

    @Test
    void testPutWithValidKey() {
        // Test behavior when putting a valid key
        String result = trie.put("key", "value");
        // Assuming the key was not present before
        assertNull(result);
    }

    @Test
    void testPutWithExistingKey() {
        trie.put("key", "value");
        String result = trie.put("key", "newValue");
        // The previous value should be returned
        assertEquals("value", result);
    }

    @Test
    void testPutWithFoundAndEmptyKey() {
        // Test case where the key exists but is empty. Expecting the method to store the new key-value pair and increment size.
        // Simulate an empty entry
        trie.put("existingKey", null);
        String result = trie.put("existingKey", "newValue");
        // The new value should be returned
        assertEquals("newValue", result);
    }

    @Test
    void testPutWithFoundAndNotEmptyKey() {
        trie.put("existingKey", "oldValue");
        String result = trie.put("existingKey", "updatedValue");
        // The previous value should be returned
        assertEquals("oldValue", result);
    }

    @Test
    void testPutWithNewKeyValidBitIndex() {
        // Test case where a new key is added with a valid bit index. Expecting the method to add the entry and return null.
        String result = trie.put("newKey", "newValue");
        // The method should return null
        assertNull(result);
    }

    @Test
    void testPutWithNewKeyNullBitKey() {
        // Test case where a new key is added with a null bit key. Expecting the method to store the key-value pair at the root.
        String result = trie.put("keyWithNullBit", "value");
        // Value should be stored at the root
        assertEquals("value", result);
    }

    @Test
    void testPutWithNewKeyEqualBitKey() {
        // Test case where a new key is added with an equal bit key but not at the root. Expecting the method to update the value.
        trie.put("keyWithEqualBit", "value");
        String result = trie.put("keyWithEqualBit", "updatedValue");
        // The value should be updated
        assertEquals("updatedValue", result);
    }

    @Test
    void testPutWithInvalidBitIndex() {
        // Test case where an invalid bit index is encountered. Expecting an IllegalArgumentException to be thrown.
        assertThrows(IllegalArgumentException.class, () -> {
            trie.put("invalidKey", "value");
        });
    }

    @Test
    void testPutWithNonEmptyValue() {
        // Test case where a non-empty key is added with a non-empty value. Expecting the method to add the entry and return null.
        String result = trie.put("nonEmptyKey", "valueForNonEmptyKey");
        // The method should return null
        assertNull(result);
    }

    @Test
    void testPutWithDuplicateValue() {
        // Test case where a key that already exists is added with the same value. Expecting the method to return null.
        trie.put("duplicateKey", "duplicateValue");
        String result = trie.put("duplicateKey", "duplicateValue");
        // The method should return null as the value is the same
        assertNull(result);
    }

    @Test
    void testPutWithSpecialCharacters() {
        // Test case where a key with special characters is added. Expecting the method to add the entry and return null.
        String result = trie.put("!@#$%^&*()", "valueWithSpecialChars");
        // The method should return null
        assertNull(result);
    }

    @Test
    void testPutWithLongString() {
        // Test case where a very long key string is added. Expecting the method to add the entry and return null.
        String result = trie.put("aVeryLongKeyStringThatExceedsNormalLength", "longValue");
        // The method should return null
        assertNull(result);
    }

    @Test
    void testPutWithKeyFoundAndUpdatedNewValue() {
        trie.put("existingKey", "oldValue");
        String result = trie.put("existingKey", "updatedValueAgain");
        // The previous value should be returned
        assertEquals("oldValue", result);
    }

    @Test
    void testPutWithKeyWithEmptyStringValue() {
        // Test case where a key is added with an empty string value. Expecting the method to add the entry and return null.
        String result = trie.put("keyWithEmptyValue", "");
        // The method should return null
        assertNull(result);
    }

    @Test
    void testPutWithKeyFoundEqualBitKeyNotRoot() {
        // Test case where a key is found with an equal bit key but not at the root. Expecting the method to update the value.
        trie.put("keyWithEqualBit", "value");
        String result = trie.put("keyWithEqualBit", "updatedValue");
        // The value should be updated
        assertEquals("updatedValue", result);
    }
}
