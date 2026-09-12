
You are an experienced Java developer tasked with fixing a piece of code that contains compilation errors. Your task is to resolve these issues after analyzing the code and its compilation feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any syntactical or logical errors.
2. Analyze the compilation feedback to understand the specific errors and their locations within the code.
3. Apply necessary corrections to the code based on the feedback, and output the compelete correct code.
The Java code you need to fix is provided below:
```java
package org.apache.commons.collections4.trie;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ConcurrentModificationException;

@ExtendWith(MockitoExtension.class)
class AbstractPatriciaTrie_higherEntry_Test {

    private AbstractPatriciaTrie<String, Integer> trie;

    private KeyAnalyzer<? super String> keyAnalyzer;

    @BeforeEach
    void setupBeforeEach() {
        keyAnalyzer = mock(KeyAnalyzer.class);
        trie = new AbstractPatriciaTrie<String, Integer>(keyAnalyzer) {

            @Override
            public TrieEntry<String, Integer> higherEntry(String key) {
                // Implement a mock logic for higherEntry for testing purposes
                if (key.equals("key1")) {
                    return new TrieEntry<>("key2", 2);
                } else if (key.equals("key2")) {
                    return null;
                } else if (key.equals("nonExistentKey")) {
                    return new TrieEntry<>("key1", 1);
                } else if (key.equals("keyWithCorruptedEntry")) {
                    throw new ConcurrentModificationException();
                } else if (key.equals("keyWithNegativeBitIndex") || key.equals("invalidKey")) {
                    throw new IllegalStateException();
                }
                return null;
            }
        };
    }

    @Test
    void testHigherEntry_whenKeyExists() {
        // Given
        trie.put("key1", 1);
        trie.put("key2", 2);
        // When
        AbstractPatriciaTrie.TrieEntry<String, Integer> result = trie.higherEntry("key1");
        // Then
        assertNotNull(result);
        assertEquals("key2", result.getKey());
        assertEquals(2, result.getValue());
    }

    @Test
    void testHigherEntry_whenKeyDoesNotExist() {
        // Given
        trie.put("key1", 1);
        trie.put("key3", 3);
        // When
        AbstractPatriciaTrie.TrieEntry<String, Integer> result = trie.higherEntry("key2");
        // Then
        assertNotNull(result);
        assertEquals("key3", result.getKey());
        assertEquals(3, result.getValue());
    }

    @Test
    void testHigherEntry_whenNoHigherKey() {
        // Given
        trie.put("key1", 1);
        trie.put("key2", 2);
        // When
        AbstractPatriciaTrie.TrieEntry<String, Integer> result = trie.higherEntry("key2");
        // Then
        assertNull(result);
    }

    @Test
    void testHigherEntry_withNullKey() {
        // Given
        trie.put("key1", 1);
        // When
        AbstractPatriciaTrie.TrieEntry<String, Integer> result = trie.higherEntry(null);
        // Then
        assertNull(result);
    }

    @Test
    void testHigherEntry_withNonExistentKeyAndEmptyRoot() {
        // Given
        trie.put("key1", 1);
        // When
        AbstractPatriciaTrie.TrieEntry<String, Integer> result = trie.higherEntry("nonExistentKey");
        // Then
        assertNotNull(result);
        assertEquals("key1", result.getKey());
        assertEquals(1, result.getValue());
    }

    @Test
    void testHigherEntry_withValidBitIndex() {
        // Given
        trie.put("key1", 1);
        trie.put("key2", 2);
        // When
        AbstractPatriciaTrie.TrieEntry<String, Integer> result = trie.higherEntry("nonExistentKeyWithValidBitIndex");
        // Then
        assertNotNull(result);
        // Assuming the next entry logic is correctly implemented
    }

    @Test
    void testHigherEntry_withNullBitIndex() {
        // Given
        trie.put("key1", 1);
        // When
        AbstractPatriciaTrie.TrieEntry<String, Integer> result = trie.higherEntry("keyWithNullBitIndex");
        // Then
        assertNotNull(result);
        // Assuming the first entry logic is correctly implemented
    }

    @Test
    void testHigherEntry_withEqualBitIndex() {
        // Given
        trie.put("key1", 1);
        trie.put("key2", 2);
        // When
        AbstractPatriciaTrie.TrieEntry<String, Integer> result = trie.higherEntry("keyWithEqualBitIndex");
        // Then
        assertNotNull(result);
        // Assuming the next entry logic is correctly implemented
    }

    @Test
    void testHigherEntry_withInvalidKey() {
        // Given
        trie.put("key1", 1);
        // When / Then
        assertThrows(IllegalStateException.class, () -> {
            trie.higherEntry("invalidKey");
        });
    }

    @Test
    void testHigherEntry_emptyTrie() {
        // When
        AbstractPatriciaTrie.TrieEntry<String, Integer> result = trie.higherEntry("emptyTrie");
        // Then
        assertNull(result);
    }

    @Test
    void testHigherEntry_keyAtBoundary() {
        // Given
        trie.put("key1", 1);
        trie.put("keyBoundary", 2);
        // When
        AbstractPatriciaTrie.TrieEntry<String, Integer> result = trie.higherEntry("keyBoundary");
        // Then
        assertNull(result);
    }

    @Test
    void testHigherEntry_withNegativeBitIndex() {
        // Given
        trie.put("key1", 1);
        // When / Then
        assertThrows(IllegalStateException.class, () -> {
            trie.higherEntry("keyWithNegativeBitIndex");
        });
    }

    @Test
    void testHigherEntry_withMaxBitIndex() {
        // Given
        trie.put("key1", 1);
        trie.put("key2", 2);
        // When
        AbstractPatriciaTrie.TrieEntry<String, Integer> result = trie.higherEntry("keyWithMaxBitIndex");
        // Then
        assertNotNull(result);
        // Assuming the next entry logic is correctly implemented
    }

    @Test
    void testHigherEntry_withSingleBitDifference() {
        // Given
        trie.put("key1", 1);
        trie.put("key2", 2);
        // When
        AbstractPatriciaTrie.TrieEntry<String, Integer> result = trie.higherEntry("keyWithSingleBitDifference");
        // Then
        assertNotNull(result);
        // Assuming the next entry logic is correctly implemented
    }

    @Test
    void testHigherEntry_withCorruptedEntry() {
        // Given
        trie.put("key1", 1);
        // When / Then
        assertThrows(ConcurrentModificationException.class, () -> {
            trie.higherEntry("keyWithCorruptedEntry");
        });
    }
}
```

Corresponding compilation feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
src/test/java/org/apache/commons/collections4/trie/AbstractPatriciaTrie_higherEntry_Test.java:27: error: cannot infer type arguments for TrieEntry<>
                    return new TrieEntry<>("key2", 2);
                           ^
  reason: cannot infer type-variable(s) K,V
    (actual and formal argument lists differ in length)
  where K,V are type-variables:
    K extends Object declared in class TrieEntry
    V extends Object declared in class TrieEntry
src/test/java/org/apache/commons/collections4/trie/AbstractPatriciaTrie_higherEntry_Test.java:31: error: cannot infer type arguments for TrieEntry<>
                    return new TrieEntry<>("key1", 1);
                           ^
  reason: cannot infer type-variable(s) K,V
    (actual and formal argument lists differ in length)
  where K,V are type-variables:
    K extends Object declared in class TrieEntry
    V extends Object declared in class TrieEntry
Note: src/test/java/org/apache/commons/collections4/trie/AbstractPatriciaTrie_higherEntry_Test.java uses unchecked or unsafe operations.
Note: Recompile with -Xlint:unchecked for details.
2 errors

```

And you can use the following related information to assist you in your analysis and correction:
```
{'api document of class org.apache.commons.collections4.trie.AbstractPatriciaTrie': '\n * This class implements the base PATRICIA algorithm and everything that\n * is related to the {@link Map} interface.\n *\n * @param <K> the type of the keys in this map\n * @param <V> the type of the values in this map\n * @since 4.0\n ', 'api document of method higherEntry(K)': '\n     * Returns an entry strictly higher than the given key,\n     * or null if no such entry exists.\n     ', 'return type': 'org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieEntry<K, V>', 'dependent classes': 'class `org.apache.commons.collections4.trie.AbstractPatriciaTrie`:\n\tdependent fields:\n\t\torg.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieEntry<K, V> org.apache.commons.collections4.trie.AbstractPatriciaTrie.root;\n\t\tint org.apache.commons.collections4.trie.AbstractPatriciaTrie.modCount;\n\trelated functions:\n\t\tmethod ` TrieEntry<K, V> lowerEntry(K)` returns `org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieEntry<K, V>`, related with `org.apache.commons.collections4.trie.AbstractPatriciaTrie.higherEntry(K)`, api document: \n     * Returns a key-value mapping associated with the greatest key\n     * strictly less than the given key, or null if there is no such key.\n     ;\n\t\tmethod ` TrieEntry<K, V> subtree(K, int, int)` returns `org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieEntry<K, V>`, related with `org.apache.commons.collections4.trie.AbstractPatriciaTrie.higherEntry(K)`, api document: \n     * Finds the subtree that contains the prefix.\n     *\n     * This is very similar to getR but with the difference that\n     * we stop the lookup if h.bitIndex > lengthInBits.\n     ;\n\t\tmethod ` TrieEntry<K, V> floorEntry(K)` returns `org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieEntry<K, V>`, related with `org.apache.commons.collections4.trie.AbstractPatriciaTrie.higherEntry(K)`, api document: \n     * Returns a key-value mapping associated with the greatest key\n     * less than or equal to the given key, or null if there is no such key.\n     ;\n\t\tmethod `private void readObject(ObjectInputStream)` returns `void`, related with `org.apache.commons.collections4.trie.AbstractPatriciaTrie.higherEntry(K)`, api document: \n     * Reads the content of the stream.\n     ;\n\t\tmethod `public boolean containsKey(Object)` returns `boolean`, related with `org.apache.commons.collections4.trie.AbstractPatriciaTrie.higherEntry(K)`;\n\t\tmethod ` TrieEntry<K, V> ceilingEntry(K)` returns `org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieEntry<K, V>`, related with `org.apache.commons.collections4.trie.AbstractPatriciaTrie.higherEntry(K)`, api document: \n     * Returns a key-value mapping associated with the least key greater\n     * than or equal to the given key, or null if there is no such key.\n     ;\n\t\tmethod `public V put(K, V)` returns `V`, related with `org.apache.commons.collections4.trie.AbstractPatriciaTrie.higherEntry(K)`;\n\t\tmethod `public V remove(Object)` returns `V`, related with `org.apache.commons.collections4.trie.AbstractPatriciaTrie.higherEntry(K)`, api document: \n     * {@inheritDoc}\n     *\n     * @throws ClassCastException if provided key is of an incompatible type\n     ;\nclass `org.apache.commons.collections4.trie.AbstractBitwiseTrie.BasicEntry`:\n\tdependent fields:\n\t\tK org.apache.commons.collections4.trie.AbstractBitwiseTrie.BasicEntry.key;\n\t'}
```

**Note**: If the method-to-test in the @Test function is a private method, you can use **reflection** to call it.

Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 


