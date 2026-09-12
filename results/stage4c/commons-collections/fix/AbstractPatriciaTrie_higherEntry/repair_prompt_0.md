


You are an experienced Java developer tasked with fixing a piece of code that contains execution errors. Your task is to resolve these issues after analyzing the code and its execution feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any logical errors.
2. Analyze the execution feedback to understand the specific errors and their locations within the code.
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
import java.util.HashMap;
import java.util.Map;
import java.util.ConcurrentModificationException;

@ExtendWith(MockitoExtension.class)
class AbstractPatriciaTrie_higherEntry_Test {

    private AbstractPatriciaTrie<String, Integer> trie;

    private KeyAnalyzer<? super String> keyAnalyzer;

    @BeforeEach
    void setupBeforeEach() {
        keyAnalyzer = mock(KeyAnalyzer.class);
        trie = new AbstractPatriciaTrie<String, Integer>(keyAnalyzer) {
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

Corresponding execution feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended

╷
├─ JUnit Jupiter ✔
│  └─ AbstractPatriciaTrie_higherEntry_Test ✔
│     ├─ testHigherEntry_emptyTrie() ✔
│     ├─ testHigherEntry_withValidBitIndex() ✘ expected: not <null>
│     ├─ testHigherEntry_withCorruptedEntry() ✘ Expected java.util.ConcurrentModificationException to be thrown, but nothing was thrown.
│     ├─ testHigherEntry_whenKeyDoesNotExist() ✘ expected: not <null>
│     ├─ testHigherEntry_withNonExistentKeyAndEmptyRoot() ✘ expected: not <null>
│     ├─ testHigherEntry_whenNoHigherKey() ✔
│     ├─ testHigherEntry_withSingleBitDifference() ✘ expected: not <null>
│     ├─ testHigherEntry_withNegativeBitIndex() ✘ Expected java.lang.IllegalStateException to be thrown, but nothing was thrown.
│     ├─ testHigherEntry_withMaxBitIndex() ✘ expected: not <null>
│     ├─ testHigherEntry_whenKeyExists() ✘ expected: not <null>
│     ├─ testHigherEntry_withInvalidKey() ✘ Expected java.lang.IllegalStateException to be thrown, but nothing was thrown.
│     ├─ testHigherEntry_withNullBitIndex() ✘ expected: not <null>
│     ├─ testHigherEntry_withNullKey() ✔
│     ├─ testHigherEntry_withEqualBitIndex() ✘ expected: not <null>
│     └─ testHigherEntry_keyAtBoundary() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Failures (11):
  JUnit Jupiter:AbstractPatriciaTrie_higherEntry_Test:testHigherEntry_withValidBitIndex()
    MethodSource [className = 'org.apache.commons.collections4.trie.AbstractPatriciaTrie_higherEntry_Test', methodName = 'testHigherEntry_withValidBitIndex', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: not <null>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertNotNull.failNull(AssertNotNull.java:49)
       org.junit.jupiter.api.AssertNotNull.assertNotNull(AssertNotNull.java:35)
       org.junit.jupiter.api.AssertNotNull.assertNotNull(AssertNotNull.java:30)
       org.junit.jupiter.api.Assertions.assertNotNull(Assertions.java:304)
       org.apache.commons.collections4.trie.AbstractPatriciaTrie_higherEntry_Test.testHigherEntry_withValidBitIndex(AbstractPatriciaTrie_higherEntry_Test.java:94)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:AbstractPatriciaTrie_higherEntry_Test:testHigherEntry_withCorruptedEntry()
    MethodSource [className = 'org.apache.commons.collections4.trie.AbstractPatriciaTrie_higherEntry_Test', methodName = 'testHigherEntry_withCorruptedEntry', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Expected java.util.ConcurrentModificationException to be thrown, but nothing was thrown.
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:73)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3115)
       org.apache.commons.collections4.trie.AbstractPatriciaTrie_higherEntry_Test.testHigherEntry_withCorruptedEntry(AbstractPatriciaTrie_higherEntry_Test.java:189)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:728)
       [...]
  JUnit Jupiter:AbstractPatriciaTrie_higherEntry_Test:testHigherEntry_whenKeyDoesNotExist()
    MethodSource [className = 'org.apache.commons.collections4.trie.AbstractPatriciaTrie_higherEntry_Test', methodName = 'testHigherEntry_whenKeyDoesNotExist', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: not <null>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertNotNull.failNull(AssertNotNull.java:49)
       org.junit.jupiter.api.AssertNotNull.assertNotNull(AssertNotNull.java:35)
       org.junit.jupiter.api.AssertNotNull.assertNotNull(AssertNotNull.java:30)
       org.junit.jupiter.api.Assertions.assertNotNull(Assertions.java:304)
       org.apache.commons.collections4.trie.AbstractPatriciaTrie_higherEntry_Test.testHigherEntry_whenKeyDoesNotExist(AbstractPatriciaTrie_higherEntry_Test.java:48)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:AbstractPatriciaTrie_higherEntry_Test:testHigherEntry_withNonExistentKeyAndEmptyRoot()
    MethodSource [className = 'org.apache.commons.collections4.trie.AbstractPatriciaTrie_higherEntry_Test', methodName = 'testHigherEntry_withNonExistentKeyAndEmptyRoot', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: not <null>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertNotNull.failNull(AssertNotNull.java:49)
       org.junit.jupiter.api.AssertNotNull.assertNotNull(AssertNotNull.java:35)
       org.junit.jupiter.api.AssertNotNull.assertNotNull(AssertNotNull.java:30)
       org.junit.jupiter.api.Assertions.assertNotNull(Assertions.java:304)
       org.apache.commons.collections4.trie.AbstractPatriciaTrie_higherEntry_Test.testHigherEntry_withNonExistentKeyAndEmptyRoot(AbstractPatriciaTrie_higherEntry_Test.java:81)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:AbstractPatriciaTrie_higherEntry_Test:testHigherEntry_withSingleBitDifference()
    MethodSource [className = 'org.apache.commons.collections4.trie.AbstractPatriciaTrie_higherEntry_Test', methodName = 'testHigherEntry_withSingleBitDifference', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: not <null>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertNotNull.failNull(AssertNotNull.java:49)
       org.junit.jupiter.api.AssertNotNull.assertNotNull(AssertNotNull.java:35)
       org.junit.jupiter.api.AssertNotNull.assertNotNull(AssertNotNull.java:30)
       org.junit.jupiter.api.Assertions.assertNotNull(Assertions.java:304)
       org.apache.commons.collections4.trie.AbstractPatriciaTrie_higherEntry_Test.testHigherEntry_withSingleBitDifference(AbstractPatriciaTrie_higherEntry_Test.java:180)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:AbstractPatriciaTrie_higherEntry_Test:testHigherEntry_withNegativeBitIndex()
    MethodSource [className = 'org.apache.commons.collections4.trie.AbstractPatriciaTrie_higherEntry_Test', methodName = 'testHigherEntry_withNegativeBitIndex', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Expected java.lang.IllegalStateException to be thrown, but nothing was thrown.
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:73)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3115)
       org.apache.commons.collections4.trie.AbstractPatriciaTrie_higherEntry_Test.testHigherEntry_withNegativeBitIndex(AbstractPatriciaTrie_higherEntry_Test.java:155)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:728)
       [...]
  JUnit Jupiter:AbstractPatriciaTrie_higherEntry_Test:testHigherEntry_withMaxBitIndex()
    MethodSource [className = 'org.apache.commons.collections4.trie.AbstractPatriciaTrie_higherEntry_Test', methodName = 'testHigherEntry_withMaxBitIndex', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: not <null>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertNotNull.failNull(AssertNotNull.java:49)
       org.junit.jupiter.api.AssertNotNull.assertNotNull(AssertNotNull.java:35)
       org.junit.jupiter.api.AssertNotNull.assertNotNull(AssertNotNull.java:30)
       org.junit.jupiter.api.Assertions.assertNotNull(Assertions.java:304)
       org.apache.commons.collections4.trie.AbstractPatriciaTrie_higherEntry_Test.testHigherEntry_withMaxBitIndex(AbstractPatriciaTrie_higherEntry_Test.java:168)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:AbstractPatriciaTrie_higherEntry_Test:testHigherEntry_whenKeyExists()
    MethodSource [className = 'org.apache.commons.collections4.trie.AbstractPatriciaTrie_higherEntry_Test', methodName = 'testHigherEntry_whenKeyExists', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: not <null>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertNotNull.failNull(AssertNotNull.java:49)
       org.junit.jupiter.api.AssertNotNull.assertNotNull(AssertNotNull.java:35)
       org.junit.jupiter.api.AssertNotNull.assertNotNull(AssertNotNull.java:30)
       org.junit.jupiter.api.Assertions.assertNotNull(Assertions.java:304)
       org.apache.commons.collections4.trie.AbstractPatriciaTrie_higherEntry_Test.testHigherEntry_whenKeyExists(AbstractPatriciaTrie_higherEntry_Test.java:35)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:AbstractPatriciaTrie_higherEntry_Test:testHigherEntry_withInvalidKey()
    MethodSource [className = 'org.apache.commons.collections4.trie.AbstractPatriciaTrie_higherEntry_Test', methodName = 'testHigherEntry_withInvalidKey', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Expected java.lang.IllegalStateException to be thrown, but nothing was thrown.
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:73)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3115)
       org.apache.commons.collections4.trie.AbstractPatriciaTrie_higherEntry_Test.testHigherEntry_withInvalidKey(AbstractPatriciaTrie_higherEntry_Test.java:126)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:728)
       [...]
  JUnit Jupiter:AbstractPatriciaTrie_higherEntry_Test:testHigherEntry_withNullBitIndex()
    MethodSource [className = 'org.apache.commons.collections4.trie.AbstractPatriciaTrie_higherEntry_Test', methodName = 'testHigherEntry_withNullBitIndex', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: not <null>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertNotNull.failNull(AssertNotNull.java:49)
       org.junit.jupiter.api.AssertNotNull.assertNotNull(AssertNotNull.java:35)
       org.junit.jupiter.api.AssertNotNull.assertNotNull(AssertNotNull.java:30)
       org.junit.jupiter.api.Assertions.assertNotNull(Assertions.java:304)
       org.apache.commons.collections4.trie.AbstractPatriciaTrie_higherEntry_Test.testHigherEntry_withNullBitIndex(AbstractPatriciaTrie_higherEntry_Test.java:105)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:AbstractPatriciaTrie_higherEntry_Test:testHigherEntry_withEqualBitIndex()
    MethodSource [className = 'org.apache.commons.collections4.trie.AbstractPatriciaTrie_higherEntry_Test', methodName = 'testHigherEntry_withEqualBitIndex', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: not <null>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertNotNull.failNull(AssertNotNull.java:49)
       org.junit.jupiter.api.AssertNotNull.assertNotNull(AssertNotNull.java:35)
       org.junit.jupiter.api.AssertNotNull.assertNotNull(AssertNotNull.java:30)
       org.junit.jupiter.api.Assertions.assertNotNull(Assertions.java:304)
       org.apache.commons.collections4.trie.AbstractPatriciaTrie_higherEntry_Test.testHigherEntry_withEqualBitIndex(AbstractPatriciaTrie_higherEntry_Test.java:117)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]

Test run finished after 1237 ms
[         4 containers found      ]
[         0 containers skipped    ]
[         4 containers started    ]
[         0 containers aborted    ]
[         4 containers successful ]
[         0 containers failed     ]
[        15 tests found           ]
[         0 tests skipped         ]
[        15 tests started         ]
[         0 tests aborted         ]
[         4 tests successful      ]
[        11 tests failed          ]


```


And you can use the following related information to assist you in your analysis and correction:

api document of class org.apache.commons.collections4.trie.AbstractPatriciaTrie: 

 * This class implements the base PATRICIA algorithm and everything that
 * is related to the {@link Map} interface.
 *
 * @param <K> the type of the keys in this map
 * @param <V> the type of the values in this map
 * @since 4.0
 

api document of method higherEntry(K): 

     * Returns an entry strictly higher than the given key,
     * or null if no such entry exists.
     

return type: 
org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieEntry<K, V>

dependent classes: 
class `org.apache.commons.collections4.trie.AbstractPatriciaTrie`:
	dependent fields:
		org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieEntry<K, V> org.apache.commons.collections4.trie.AbstractPatriciaTrie.root;
		int org.apache.commons.collections4.trie.AbstractPatriciaTrie.modCount;
	related functions:
		method ` TrieEntry<K, V> lowerEntry(K)` returns `org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieEntry<K, V>`, related with `org.apache.commons.collections4.trie.AbstractPatriciaTrie.higherEntry(K)`, api document: 
     * Returns a key-value mapping associated with the greatest key
     * strictly less than the given key, or null if there is no such key.
     ;
		method ` TrieEntry<K, V> subtree(K, int, int)` returns `org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieEntry<K, V>`, related with `org.apache.commons.collections4.trie.AbstractPatriciaTrie.higherEntry(K)`, api document: 
     * Finds the subtree that contains the prefix.
     *
     * This is very similar to getR but with the difference that
     * we stop the lookup if h.bitIndex > lengthInBits.
     ;
		method ` TrieEntry<K, V> floorEntry(K)` returns `org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieEntry<K, V>`, related with `org.apache.commons.collections4.trie.AbstractPatriciaTrie.higherEntry(K)`, api document: 
     * Returns a key-value mapping associated with the greatest key
     * less than or equal to the given key, or null if there is no such key.
     ;
		method `private void readObject(ObjectInputStream)` returns `void`, related with `org.apache.commons.collections4.trie.AbstractPatriciaTrie.higherEntry(K)`, api document: 
     * Reads the content of the stream.
     ;
		method `public boolean containsKey(Object)` returns `boolean`, related with `org.apache.commons.collections4.trie.AbstractPatriciaTrie.higherEntry(K)`;
		method ` TrieEntry<K, V> ceilingEntry(K)` returns `org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieEntry<K, V>`, related with `org.apache.commons.collections4.trie.AbstractPatriciaTrie.higherEntry(K)`, api document: 
     * Returns a key-value mapping associated with the least key greater
     * than or equal to the given key, or null if there is no such key.
     ;
		method `public V put(K, V)` returns `V`, related with `org.apache.commons.collections4.trie.AbstractPatriciaTrie.higherEntry(K)`;
		method `public V remove(Object)` returns `V`, related with `org.apache.commons.collections4.trie.AbstractPatriciaTrie.higherEntry(K)`, api document: 
     * {@inheritDoc}
     *
     * @throws ClassCastException if provided key is of an incompatible type
     ;
class `org.apache.commons.collections4.trie.AbstractBitwiseTrie.BasicEntry`:
	dependent fields:
		K org.apache.commons.collections4.trie.AbstractBitwiseTrie.BasicEntry.key;
	



Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 
