You are a professional Java software testing expert. Your task is to generate high-quality unit test code for a focal method, using **JUnit5** and **Mockito** libraries. 

You'll be provided with:
1. The focal method to test.
2. The target class's structure (fields and methods).
3. A pre-defined test class framework with `@Before`/`@After` methods.
4. Test cases in JSON format.
5. Any relevant context.

**Requirements:**

Code Quality:
- Include clear comments explaining each test scenario.
- Ensure code is clean, understandable, and maintainable.
- Do not directly access private methods or fields. **Use reflection if the focal method is private.**
- Include all necessary imports; code must be free of syntax errors.

**Test Structure:**
- Follow the given test class framework; do not modify `@Before`/`@After` methods.
- Create one test function per test case group, ensuring all cases are covered.
- Use parameterized tests where appropriate.
- Reinitialize variables inside test functions if `@Setup` values don't fit.
- Properly mock and set up dependencies.

@input{focal method and target class}
Here is the code of focal method, `put(K, V)`, with the focal class `AbstractPatriciaTrie`:
```Java
package org.apache.commons.collections4.trie;
import java.util.Set;
import java.util.ConcurrentModificationException;
import java.io.IOException;
import java.util.SortedMap;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.io.ObjectOutputStream;
import org.apache.commons.collections4.OrderedMapIterator;
import java.util.Comparator;
import java.util.AbstractMap;
import java.io.ObjectInputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Map;
import org.apache.commons.collections4.Trie;
import java.util.Collections;
public abstract class AbstractPatriciaTrie<K, V> extends AbstractBitwiseTrie<K, V> {
    private transient TrieEntry<K, V> root = new TrieEntry<>(null, null, -1);
    protected transient int modCount;
        /**
     * A {@link org.apache.commons.collections4.Trie} is a set of {@link TrieEntry} nodes.
     */
    protected static class TrieEntry<K, V> extends BasicEntry<K, V> {
        private static final long serialVersionUID = 4596023148184140013L;
    
        /**
         * The index this entry is comparing.
         */
        protected int bitIndex;
    
        /**
         * The parent of this entry.
         */
        protected TrieEntry<K, V> parent;
    
        /**
         * The left child of this entry.
         */
        protected TrieEntry<K, V> left;
    
        /**
         * The right child of this entry.
         */
        protected TrieEntry<K, V> right;
    
        /**
         * The entry who uplinks to this entry.
         */
        protected TrieEntry<K, V> predecessor;
    
        public TrieEntry(final K key, final V value, final int bitIndex) {
            super(key, value);
            this.bitIndex = bitIndex;
            this.parent = null;
            this.left = this;
            this.right = null;
            this.predecessor = this;
        }
    
        /**
         * Whether or not the entry is storing a key.
         * Only the root can potentially be empty, all other
         * nodes must have a key.
         */
        public boolean isEmpty() {
            return key == null;
        }
    
        /**
         * Either the left or right child is a loopback.
         */
        public boolean isExternalNode() {
            return !isInternalNode();
        }
    
        /**
         * Neither the left nor right child is a loopback.
         */
        public boolean isInternalNode() {
            return (left != this) && (right != this);
        }
    
        @Override
        public String toString() {
            final StringBuilder buffer = new StringBuilder();
            if (bitIndex == (-1)) {
                buffer.append("RootEntry(");
            } else {
                buffer.append("Entry(");
            }
            buffer.append("key=").append(getKey()).append(" [").append(bitIndex).append("], ");
            buffer.append("value=").append(getValue()).append(", ");
            // buffer.append("bitIndex=").append(bitIndex).append(", ");
            if (parent != null) {
                if (parent.bitIndex == (-1)) {
                    buffer.append("parent=").append("ROOT");
                } else {
                    buffer.append("parent=").append(parent.getKey()).append(" [").append(parent.bitIndex).append("]");
                }
            } else {
                buffer.append("parent=").append("null");
            }
            buffer.append(", ");
            if (left != null) {
                if (left.bitIndex == (-1)) {
                    buffer.append("left=").append("ROOT");
                } else {
                    buffer.append("left=").append(left.getKey()).append(" [").append(left.bitIndex).append("]");
                }
            } else {
                buffer.append("left=").append("null");
            }
            buffer.append(", ");
            if (right != null) {
                if (right.bitIndex == (-1)) {
                    buffer.append("right=").append("ROOT");
                } else {
                    buffer.append("right=").append(right.getKey()).append(" [").append(right.bitIndex).append("]");
                }
            } else {
                buffer.append("right=").append("null");
            }
            buffer.append(", ");
            if (predecessor != null) {
                if (predecessor.bitIndex == (-1)) {
                    buffer.append("predecessor=").append("ROOT");
                } else {
                    buffer.append("predecessor=").append(predecessor.getKey()).append(" [").append(predecessor.bitIndex).append("]");
                }
            }
            buffer.append(")");
            return buffer.toString();
        }
    }
@Override
    public V put(final K key, final V value) {
        Objects.requireNonNull(key, "key");
        final int lengthInBits = lengthInBits(key);
        // The only place to store a key with a length
        // of zero bits is the root node
        if (lengthInBits == 0) {
            if (root.isEmpty()) {
                incrementSize();
            } else {
                incrementModCount();
            }
            return root.setKeyValue(key, value);
        }
        final TrieEntry<K, V> found = getNearestEntryForKey(key, lengthInBits);
        if (compareKeys(key, found.key)) {
            if (found.isEmpty()) {
                // <- must be the root
                incrementSize();
            } else {
                incrementModCount();
            }
            return found.setKeyValue(key, value);
        }
        final int bitIndex = bitIndex(key, found.key);
        if (!KeyAnalyzer.isOutOfBoundsIndex(bitIndex)) {
            if (KeyAnalyzer.isValidBitIndex(bitIndex)) {
                // in 99.999...9% the case
                /* NEW KEY+VALUE TUPLE */
                final TrieEntry<K, V> t = new TrieEntry<>(key, value, bitIndex);
                addEntry(t, lengthInBits);
                incrementSize();
                return null;
            }
            if (KeyAnalyzer.isNullBitKey(bitIndex)) {
                // A bits of the Key are zero. The only place to
                // store such a Key is the root Node!
                /* NULL BIT KEY */
                if (root.isEmpty()) {
                    incrementSize();
                } else {
                    incrementModCount();
                }
                return root.setKeyValue(key, value);
            }
            if (KeyAnalyzer.isEqualBitKey(bitIndex) && (found != root)) {
                // NOPMD
                incrementModCount();
                return found.setKeyValue(key, value);
            }
        }
        throw new IllegalArgumentException((((("Failed to put: " + key) + " -> ") + value) + ", ") + bitIndex);
    }
TrieEntry<K, V> addEntry(final TrieEntry<K, V> entry, final int lengthInBits);
    TrieEntry<K, V> ceilingEntry(final K key);
    @Override
public void clear();
    @Override
public Comparator<? super K> comparator();
    @Override
public boolean containsKey(final Object k);
    void decrementSize();
    @Override
public Set<Map.Entry<K, V>> entrySet();
    TrieEntry<K, V> firstEntry();
    @Override
public K firstKey();
    TrieEntry<K, V> floorEntry(final K key);
    TrieEntry<K, V> followLeft(TrieEntry<K, V> node);
    TrieEntry<K, V> followRight(TrieEntry<K, V> node);
    @Override
public V get(final Object k);
    TrieEntry<K, V> getEntry(final Object k);
    TrieEntry<K, V> getNearestEntryForKey(final K key, final int lengthInBits);
    @Override
public SortedMap<K, V> headMap(final K toKey);
    TrieEntry<K, V> higherEntry(final K key);
    private void incrementModCount();
    void incrementSize();
    static boolean isValidUplink(final TrieEntry<?, ?> next, final TrieEntry<?, ?> from);
    @Override
public Set<K> keySet();
    TrieEntry<K, V> lastEntry();
    @Override
public K lastKey();
    TrieEntry<K, V> lowerEntry(final K key);
    @Override
public OrderedMapIterator<K, V> mapIterator();
    TrieEntry<K, V> nextEntry(final TrieEntry<K, V> node);
    TrieEntry<K, V> nextEntryImpl(final TrieEntry<K, V> start, final TrieEntry<K, V> previous, final TrieEntry<K, V> tree);
    TrieEntry<K, V> nextEntryInSubtree(final TrieEntry<K, V> node, final TrieEntry<K, V> parentOfSubtree);
    @Override
public K nextKey(final K key);
    @Override
public SortedMap<K, V> prefixMap(final K key);
    TrieEntry<K, V> previousEntry(final TrieEntry<K, V> start);
    @Override
public K previousKey(final K key);
    @Override
public V remove(final Object k);
    V removeEntry(final TrieEntry<K, V> h);
    public Map.Entry<K, V> select(final K key);
    public K selectKey(final K key);
    public V selectValue(final K key);
    @Override
public int size();
    @Override
public SortedMap<K, V> subMap(final K fromKey, final K toKey);
    TrieEntry<K, V> subtree(final K prefix, final int offsetInBits, final int lengthInBits);
    @Override
public SortedMap<K, V> tailMap(final K fromKey);
    @Override
public Collection<V> values();
}
```

@input{initial test class framework}
```Java
<initial_class>
```

@input{existing test case}
here's the existing test cases:
```json
<cases_json>
```


@input{context information}

api document of class org.apache.commons.collections4.trie.AbstractPatriciaTrie: 

 * This class implements the base PATRICIA algorithm and everything that
 * is related to the {@link Map} interface.
 *
 * @param <K> the type of the keys in this map
 * @param <V> the type of the values in this map
 * @since 4.0
 

return type: 
V

dependent classes: 
class `org.apache.commons.collections4.trie.AbstractPatriciaTrie`:
	dependent fields:
		org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieEntry<K, V> org.apache.commons.collections4.trie.AbstractPatriciaTrie.root;
	related functions:
		method ` TrieEntry<K, V> floorEntry(K)` returns `org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieEntry<K, V>`, related with `org.apache.commons.collections4.trie.AbstractPatriciaTrie.put(K, V)`, api document: 
     * Returns a key-value mapping associated with the greatest key
     * less than or equal to the given key, or null if there is no such key.
     ;
		method `public boolean containsKey(Object)` returns `boolean`, related with `org.apache.commons.collections4.trie.AbstractPatriciaTrie.put(K, V)`;
		method ` TrieEntry<K, V> lowerEntry(K)` returns `org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieEntry<K, V>`, related with `org.apache.commons.collections4.trie.AbstractPatriciaTrie.put(K, V)`, api document: 
     * Returns a key-value mapping associated with the greatest key
     * strictly less than the given key, or null if there is no such key.
     ;
		method ` TrieEntry<K, V> ceilingEntry(K)` returns `org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieEntry<K, V>`, related with `org.apache.commons.collections4.trie.AbstractPatriciaTrie.put(K, V)`, api document: 
     * Returns a key-value mapping associated with the least key greater
     * than or equal to the given key, or null if there is no such key.
     ;
		method `private void readObject(ObjectInputStream)` returns `void`, related with `org.apache.commons.collections4.trie.AbstractPatriciaTrie.put(K, V)`, api document: 
     * Reads the content of the stream.
     ;
		method ` TrieEntry<K, V> higherEntry(K)` returns `org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieEntry<K, V>`, related with `org.apache.commons.collections4.trie.AbstractPatriciaTrie.put(K, V)`, api document: 
     * Returns an entry strictly higher than the given key,
     * or null if no such entry exists.
     ;
		method `public V remove(Object)` returns `V`, related with `org.apache.commons.collections4.trie.AbstractPatriciaTrie.put(K, V)`, api document: 
     * {@inheritDoc}
     *
     * @throws ClassCastException if provided key is of an incompatible type
     ;
class `org.apache.commons.collections4.trie.AbstractBitwiseTrie`:
	
class `org.apache.commons.collections4.trie.AbstractBitwiseTrie.BasicEntry`:
	dependent fields:
		K org.apache.commons.collections4.trie.AbstractBitwiseTrie.BasicEntry.key;
	



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.