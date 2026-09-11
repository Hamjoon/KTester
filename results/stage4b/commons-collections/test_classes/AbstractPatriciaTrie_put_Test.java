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

    // Concrete implementation of AbstractPatriciaTrie for testing purposes
    private static class TestPatriciaTrie extends AbstractPatriciaTrie<String, String> {

        public TestPatriciaTrie(KeyAnalyzer<String> keyAnalyzer) {
            super(keyAnalyzer);
        }

        @Override
        public String put(String key, String value) {
            // Simplified put logic for testing
            return super.put(key, value);
        }
    }

    @BeforeEach
    void setupBeforeEach() {
        keyAnalyzer = mock(KeyAnalyzer.class);
        trie = new TestPatriciaTrie(keyAnalyzer);
    }

    @Test
    void testPutWithNullKey() {
        assertThrows(NullPointerException.class, () -> {
            trie.put(null, "someValue");
        });
    }

    @Test
    void testPutWithEmptyKey() {
        String result = trie.put("", "someValue");
        assertNull(result);
        trie.put("", "anotherValue");
        result = trie.put("", "anotherValue");
        assertEquals("anotherValue", result);
    }

    @Test
    void testPutWithValidKey() {
        String result = trie.put("key", "value");
        assertNull(result);
    }

    @Test
    void testPutWithExistingKey() {
        trie.put("key", "value");
        String result = trie.put("key", "newValue");
        assertEquals("value", result);
    }

    @Test
    void testPutWithFoundAndEmptyKey() {
        trie.put("existingKey", "oldValue");
        String result = trie.put("existingKey", null);
        assertEquals("oldValue", result);
        result = trie.put("existingKey", "newValue");
        assertEquals("newValue", result);
    }

    @Test
    void testPutWithFoundAndNotEmptyKey() {
        trie.put("existingKey", "oldValue");
        String result = trie.put("existingKey", "updatedValue");
        assertEquals("oldValue", result);
    }

    @Test
    void testPutWithNewKeyValidBitIndex() {
        String result = trie.put("newKey", "newValue");
        assertNull(result);
    }

    @Test
    void testPutWithNewKeyNullBitKey() {
        String result = trie.put("keyWithNullBit", "value");
        // Corrected the logic to ensure it returns the value after insertion
        assertNull(result);
        result = trie.put("keyWithNullBit", "value");
        assertEquals("value", result);
    }

    @Test
    void testPutWithNewKeyEqualBitKey() {
        trie.put("keyWithEqualBit", "value");
        String result = trie.put("keyWithEqualBit", "updatedValue");
        assertEquals("value", result);
    }

    @Test
    void testPutWithInvalidBitIndex() {
        // Assuming the trie should throw an exception for a specific invalid key
        // Adjust this logic according to the actual implementation of the Trie
        assertThrows(IllegalArgumentException.class, () -> {
            // Adjust the key to trigger the exception
            trie.put("invalidKey", "value");
        });
    }

    @Test
    void testPutWithNonEmptyValue() {
        String result = trie.put("nonEmptyKey", "valueForNonEmptyKey");
        assertNull(result);
    }

    @Test
    void testPutWithDuplicateValue() {
        trie.put("duplicateKey", "duplicateValue");
        String result = trie.put("duplicateKey", "duplicateValue");
        assertEquals("duplicateValue", result);
    }

    @Test
    void testPutWithSpecialCharacters() {
        String result = trie.put("!@#$%^&*()", "valueWithSpecialChars");
        assertNull(result);
    }

    @Test
    void testPutWithLongString() {
        String result = trie.put("aVeryLongKeyStringThatExceedsNormalLength", "longValue");
        assertNull(result);
    }

    @Test
    void testPutWithKeyFoundAndUpdatedNewValue() {
        trie.put("existingKey", "oldValue");
        String result = trie.put("existingKey", "updatedValueAgain");
        assertEquals("oldValue", result);
    }

    @Test
    void testPutWithKeyWithEmptyStringValue() {
        String result = trie.put("keyWithEmptyValue", "");
        assertNull(result);
    }

    @Test
    void testPutWithKeyFoundEqualBitKeyNotRoot() {
        trie.put("keyWithEqualBit", "value");
        String result = trie.put("keyWithEqualBit", "updatedValue");
        assertEquals("value", result);
    }
}
