As a professional Java software testing expert, your task is to generate test cases for a focal method.

You will be provided with:
1. The focal method that needs testing.
2. Basic information of the target class to which the focus method belongs, including fields and methods.

Ensure the generated test cases meet the following requirements:

Test Coverage Requirements:
- Create test cases to cover all conditional branches in the target method, including `if`, `else if`, `else`, `switch`, and `case` statements.

Output Format:
- Organize the test cases by its functions logically.
- Include input parameters, expected output, and a description for each test case.
- Use empty list `[]` for the input when a test case does not require input parameters.
- Provide an expected return value for each test case, or specify the behavior that should be verified if there's no return value.
- Clearly describe the intention of each test case in the description.

Follow the JSON format provided here for the output:
```json
[
    {
        "group": "test name",
        "cases": [
            {
                "input": [
                    {
                        "parameter": "param name",
                        "value": "param value"
                    }
                ],
                "expected": "expected exception or behavior",
                "description": "test scenario description"
            }
        ]
    },
    {...}
]
```
@input{focal method and target class}
Here is the code of focal method, `higherEntry(K)`, with the focal class `AbstractPatriciaTrie`:
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
TrieEntry<K, V> higherEntry(final K key) {
        // TODO: Cleanup so that we don't actually have to add/remove from the
        // tree.  (We do it here because there are other well-defined
        // functions to perform the search.)
        final int lengthInBits = lengthInBits(key);
        if (lengthInBits == 0) {
            if (!root.isEmpty()) {
                // If data in root, and more after -- return it.
                if (size() > 1) {
                    return nextEntry(root);
                }
                // If no more after, no higher entry.
                return null;
            }
            // Root is empty & we want something after empty, return first.
            return firstEntry();
        }
        final TrieEntry<K, V> found = getNearestEntryForKey(key, lengthInBits);
        if (compareKeys(key, found.key)) {
            return nextEntry(found);
        }
        final int bitIndex = bitIndex(key, found.key);
        if (KeyAnalyzer.isValidBitIndex(bitIndex)) {
            final TrieEntry<K, V> added = new TrieEntry<>(key, null, bitIndex);
            addEntry(added, lengthInBits);
            incrementSize();// must increment because remove will decrement
    
            final TrieEntry<K, V> ceil = nextEntry(added);
            removeEntry(added);
            modCount -= 2;// we didn't really modify it.
    
            return ceil;
        }
        if (KeyAnalyzer.isNullBitKey(bitIndex)) {
            if (!root.isEmpty()) {
                return firstEntry();
            }
            if (size() > 1) {
                return nextEntry(firstEntry());
            }
            return null;
        }
        if (KeyAnalyzer.isEqualBitKey(bitIndex)) {
            return nextEntry(found);
        }
        // we should have exited above.
        throw new IllegalStateException("invalid lookup: " + key);
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
public V put(final K key, final V value);
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

@output{test cases}: output the test cases in the JSON format: