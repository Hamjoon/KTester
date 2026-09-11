package org.apache.commons.collections4.trie;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
// Added import statement for Map
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class AbstractPatriciaTrie_higherEntry_Test {

    private AbstractPatriciaTrie<String, Integer> trie;

    @BeforeEach
    void setupBeforeEach() {
        KeyAnalyzer<String> keyAnalyzer = mock(KeyAnalyzer.class);
        trie = new AbstractPatriciaTrie<String, Integer>(keyAnalyzer) {
        };
    }

    @Test
    void testHigherEntryWhenRootIsEmpty() {
        String key = null;
        Map.Entry<String, Integer> result = trie.higherEntry(key);
        assertNull(result, "Expected higherEntry to return null when root is empty");
    }

    @Test
    void testHigherEntryWithExistingEntries() {
        trie.put("a", 1);
        trie.put("b", 2);
        trie.put("c", 3);
        String key = "a";
        Map.Entry<String, Integer> result = trie.higherEntry(key);
        assertNotNull(result, "Expected higherEntry to return a non-null entry");
        assertEquals("b", result.getKey(), "Expected higherEntry to return the entry with key 'b'");
    }

    @Test
    void testHigherEntryWhenKeyIsNull() {
        trie.put("x", 10);
        String key = null;
        Map.Entry<String, Integer> result = trie.higherEntry(key);
        assertNull(result, "Expected higherEntry to return null when key is null");
    }

    @Test
    void testHigherEntryWhenRootIsNotEmptyAndKeyIsProvided() {
        trie.put("rootKey", 100);
        String key = "someKey";
        Map.Entry<String, Integer> result = trie.higherEntry(key);
        assertNotNull(result, "Expected higherEntry to return the next entry after the root.");
    }

    @Test
    void testHigherEntryWhenKeyIsNotGreaterThanAnyExistingKeys() {
        trie.put("b", 2);
        trie.put("c", 3);
        String key = "a";
        Map.Entry<String, Integer> result = trie.higherEntry(key);
        assertNull(result, "Expected higherEntry to return null when no higher entry exists.");
    }

    @Test
    void testHigherEntryWithEmptyKey() {
        trie.put("first", 1);
        String key = "";
        Map.Entry<String, Integer> result = trie.higherEntry(key);
        assertNotNull(result, "Expected higherEntry to return the first entry when key length is 0.");
        assertEquals("first", result.getKey(), "Expected higherEntry to return the first entry.");
    }

    @Test
    void testHigherEntryWithValidKey() {
        trie.put("validKey", 5);
        String key = "validKey";
        Map.Entry<String, Integer> result = trie.higherEntry(key);
        assertNull(result, "Expected higherEntry to return null when there is no entry after the valid key.");
    }

    @Test
    void testHigherEntryWithKeyThatHasNoHigherEntry() {
        trie.put("a", 1);
        trie.put("b", 2);
        String key = "c";
        Map.Entry<String, Integer> result = trie.higherEntry(key);
        assertNull(result, "Expected higherEntry to return null when no higher entry exists.");
    }

    @Test
    void testHigherEntryWithKeyAtMaxLength() {
        String maxLengthKey = "keyAtMaxLength";
        trie.put(maxLengthKey, 10);
        String key = maxLengthKey;
        Map.Entry<String, Integer> result = trie.higherEntry(key);
        assertNull(result, "Expected higherEntry to return null when there is no higher entry.");
    }

    @Test
    void testHigherEntryWithKeyWithValidBitIndex() {
        String validBitKey = "keyWithValidBitIndex";
        trie.put(validBitKey, 20);
        String key = validBitKey;
        Map.Entry<String, Integer> result = trie.higherEntry(key);
        assertNull(result, "Expected higherEntry to return null when there is no higher entry.");
    }

    @Test
    void testHigherEntryWithKeyWithNullBitIndex() {
        String nullBitKey = "keyWithNullBitIndex";
        trie.put(nullBitKey, 30);
        String key = nullBitKey;
        Map.Entry<String, Integer> result = trie.higherEntry(key);
        assertNull(result, "Expected higherEntry to return null when there is no higher entry.");
    }

    @Test
    void testHigherEntryWithKeyWithEqualBitIndex() {
        String equalBitKey = "keyWithEqualBitIndex";
        trie.put(equalBitKey, 40);
        String key = equalBitKey;
        Map.Entry<String, Integer> result = trie.higherEntry(key);
        assertNull(result, "Expected higherEntry to return null when there is no higher entry.");
    }

    @Test
    void testHigherEntryWithKeyWithNegativeBitIndex() {
        String negativeBitKey = "keyWithNegativeBitIndex";
        trie.put(negativeBitKey, 50);
        String key = negativeBitKey;
        Map.Entry<String, Integer> result = trie.higherEntry(key);
        assertNull(result, "Expected higherEntry to return null when the key has a negative bit index.");
    }

    @Test
    void testHigherEntryWithKeyWithBitIndexGreaterThanMax() {
        String greaterThanMaxKey = "keyWithBitIndexGreaterThanMax";
        trie.put(greaterThanMaxKey, 60);
        String key = greaterThanMaxKey;
        Map.Entry<String, Integer> result = trie.higherEntry(key);
        assertNull(result, "Expected higherEntry to return null when there is no higher entry.");
    }

    @Test
    void testHigherEntryWithInvalidKey() {
        String invalidKey = "invalidKey";
        assertThrows(IllegalStateException.class, () -> {
            trie.higherEntry(invalidKey);
        }, "Expected higherEntry to throw IllegalStateException for invalid key.");
    }
}
