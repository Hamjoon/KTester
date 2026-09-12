package org.apache.commons.collections4.trie;

import org.junit.jupiter.api.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.HashMap;
import java.util.Map;
import java.util.Collection;
import java.util.SortedMap;
import java.util.Set;
import org.apache.commons.collections4.OrderedMapIterator;
import org.junit.jupiter.api.extension.ExtendWith;
import java.util.Comparator;

@ExtendWith(MockitoExtension.class)
class AbstractPatriciaTrie_put_Test {

    private AbstractPatriciaTrie<String, String> trie;

    private KeyAnalyzer<String> keyAnalyzer;

    @BeforeEach
    void setupBeforeEach() {
        keyAnalyzer = mock(KeyAnalyzer.class);
        trie = new AbstractPatriciaTrie<String, String>(keyAnalyzer) {

            @Override
            AbstractPatriciaTrie.TrieEntry<String, String> addEntry(AbstractPatriciaTrie.TrieEntry<String, String> entry, int lengthInBits) {
                // Implementation for abstract method
                return entry;
            }

            @Override
            AbstractPatriciaTrie.TrieEntry<String, String> ceilingEntry(String key) {
                return null;
            }

            @Override
            public void clear() {
            }

            @Override
            public Comparator<? super String> comparator() {
                return null;
            }

            @Override
            public boolean containsKey(Object k) {
                return false;
            }

            // Removed @Override annotation here
            void decrementSize() {
            }

            @Override
            public Set<Map.Entry<String, String>> entrySet() {
                return null;
            }

            @Override
            AbstractPatriciaTrie.TrieEntry<String, String> firstEntry() {
                return null;
            }

            @Override
            public String firstKey() {
                return null;
            }

            @Override
            AbstractPatriciaTrie.TrieEntry<String, String> floorEntry(String key) {
                return null;
            }

            @Override
            AbstractPatriciaTrie.TrieEntry<String, String> followLeft(AbstractPatriciaTrie.TrieEntry<String, String> node) {
                return null;
            }

            @Override
            AbstractPatriciaTrie.TrieEntry<String, String> followRight(AbstractPatriciaTrie.TrieEntry<String, String> node) {
                return null;
            }

            @Override
            public String get(Object k) {
                return null;
            }

            @Override
            AbstractPatriciaTrie.TrieEntry<String, String> getEntry(Object k) {
                return null;
            }

            @Override
            AbstractPatriciaTrie.TrieEntry<String, String> getNearestEntryForKey(String key, int lengthInBits) {
                return null;
            }

            @Override
            public SortedMap<String, String> headMap(String toKey) {
                return null;
            }

            @Override
            AbstractPatriciaTrie.TrieEntry<String, String> higherEntry(String key) {
                return null;
            }

            @Override
            void incrementModCount() {
            }

            @Override
            void incrementSize() {
            }

            @Override
            public Set<String> keySet() {
                return null;
            }

            @Override
            AbstractPatriciaTrie.TrieEntry<String, String> lastEntry() {
                return null;
            }

            @Override
            public String lastKey() {
                return null;
            }

            @Override
            AbstractPatriciaTrie.TrieEntry<String, String> lowerEntry(String key) {
                return null;
            }

            @Override
            public OrderedMapIterator<String, String> mapIterator() {
                return null;
            }

            @Override
            AbstractPatriciaTrie.TrieEntry<String, String> nextEntry(AbstractPatriciaTrie.TrieEntry<String, String> node) {
                return null;
            }

            @Override
            AbstractPatriciaTrie.TrieEntry<String, String> nextEntryImpl(AbstractPatriciaTrie.TrieEntry<String, String> start, AbstractPatriciaTrie.TrieEntry<String, String> previous, AbstractPatriciaTrie.TrieEntry<String, String> tree) {
                return null;
            }

            @Override
            AbstractPatriciaTrie.TrieEntry<String, String> nextEntryInSubtree(AbstractPatriciaTrie.TrieEntry<String, String> node, AbstractPatriciaTrie.TrieEntry<String, String> parentOfSubtree) {
                return null;
            }

            @Override
            public String nextKey(String key) {
                return null;
            }

            @Override
            public SortedMap<String, String> prefixMap(String key) {
                return null;
            }

            @Override
            AbstractPatriciaTrie.TrieEntry<String, String> previousEntry(AbstractPatriciaTrie.TrieEntry<String, String> start) {
                return null;
            }

            @Override
            public String previousKey(String key) {
                return null;
            }

            @Override
            public String remove(Object k) {
                return null;
            }

            @Override
            String removeEntry(AbstractPatriciaTrie.TrieEntry<String, String> h) {
                return null;
            }

            @Override
            public Map.Entry<String, String> select(String key) {
                return null;
            }

            @Override
            public String selectKey(String key) {
                return null;
            }

            @Override
            public String selectValue(String key) {
                return null;
            }

            @Override
            public int size() {
                return 0;
            }

            @Override
            public SortedMap<String, String> subMap(String fromKey, String toKey) {
                return null;
            }

            @Override
            AbstractPatriciaTrie.TrieEntry<String, String> subtree(String prefix, int offsetInBits, int lengthInBits) {
                return null;
            }

            @Override
            public SortedMap<String, String> tailMap(String fromKey) {
                return null;
            }

            @Override
            public Collection<String> values() {
                return null;
            }
        };
    }

    @Test
    void testPutWithValidKeyAndValue() {
        // Given
        String key = "testKey";
        String value = "testValue";
        // When
        String result = trie.put(key, value);
        // Then
        assertNull(result, "Expected null when adding a new key-value pair");
        // Additional assertions to validate the state of the trie
    }

    @Test
    void testPutWithNullKey() {
        // Given
        String key = null;
        String value = "someValue";
        // When & Then
        assertThrows(NullPointerException.class, () -> trie.put(key, value), "Expected NullPointerException for null key");
    }

    @Test
    void testPutWithZeroLengthKey() {
        // Given
        String key = "zeroLengthKey";
        String value = "value1";
        // Mocking lengthInBits to return 0
        when(keyAnalyzer.lengthInBits(key)).thenReturn(0);
        // When
        String previousValue = trie.put(key, value);
        // Then
        assertNull(previousValue, "Expected null when the root is empty");
        // Additional assertions to validate size increment or modification count
    }

    @Test
    void testPutWithExistingKey() {
        // Given
        String key = "existingKey";
        String value = "newValue";
        AbstractPatriciaTrie.TrieEntry<String, String> existingEntry = new AbstractPatriciaTrie.TrieEntry<>(key, "oldValue", 1);
        // Mocking getNearestEntryForKey to return existing entry
        when(trie.getNearestEntryForKey(key, keyAnalyzer.lengthInBits(key))).thenReturn(existingEntry);
        // When
        String previousValue = trie.put(key, value);
        // Then
        assertEquals("oldValue", previousValue, "Expected old value to be returned");
        // Additional assertions to validate modification count
    }

    @Test
    void testPutWithNewKey() {
        // Given
        String key = "newKey";
        String value = "newValue";
        AbstractPatriciaTrie.TrieEntry<String, String> nearestEntry = new AbstractPatriciaTrie.TrieEntry<>(null, null, 1);
        // Mocking getNearestEntryForKey to return a non-matching entry
        when(trie.getNearestEntryForKey(key, keyAnalyzer.lengthInBits(key))).thenReturn(nearestEntry);
        when(trie.compareKeys(key, nearestEntry.key)).thenReturn(false);
        // When
        String previousValue = trie.put(key, value);
        // Then
        assertNull(previousValue, "Expected null when adding a new key-value pair");
        // Additional assertions to verify TrieEntry creation and size increment
    }

    @Test
    void testPutWithNullBitKey() {
        // Given
        String key = "nullBitKey";
        String value = "value2";
        when(keyAnalyzer.isNullBitKey(anyInt())).thenReturn(true);
        // When
        String previousValue = trie.put(key, value);
        // Then
        assertNull(previousValue, "Expected null when the root is empty");
        // Additional assertions to validate size increment or modification count
    }

    @Test
    void testPutWithEqualBitKey() {
        // Given
        String key = "equalBitKey";
        String value = "value3";
        AbstractPatriciaTrie.TrieEntry<String, String> foundEntry = new AbstractPatriciaTrie.TrieEntry<>(key, "oldValue", 1);
        when(trie.getNearestEntryForKey(key, keyAnalyzer.lengthInBits(key))).thenReturn(foundEntry);
        when(keyAnalyzer.isEqualBitKey(anyInt())).thenReturn(true);
        // When
        String previousValue = trie.put(key, value);
        // Then
        assertEquals("oldValue", previousValue, "Expected old value to be returned");
        // Additional assertions to validate modification count
    }

    @Test
    void testPutWithInvalidKey() {
        // Given
        String key = "invalidKey";
        String value = "value4";
        when(keyAnalyzer.isOutOfBoundsIndex(anyInt())).thenReturn(true);
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> trie.put(key, value), "Expected IllegalArgumentException for out of bounds index");
    }
}
