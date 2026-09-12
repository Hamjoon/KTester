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
Here is the code of focal method, `removeInternalEntry(TrieEntry<K, V>)`, with the focal class `AbstractPatriciaTrie`:
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
private void removeInternalEntry(final TrieEntry<K, V> h) {
        if (h == root) {
            throw new IllegalArgumentException("Cannot delete root Entry!");
        }
        if (!h.isInternalNode()) {
            throw new IllegalArgumentException(h + " is not an internal Entry!");
        }
        final TrieEntry<K, V> p = h.predecessor;
        // Set P's bitIndex
        p.bitIndex = h.bitIndex;
        // Fix P's parent, predecessor and child Nodes
        {
            final TrieEntry<K, V> parent = p.parent;
            final TrieEntry<K, V> child = (p.left == h) ? p.right : p.left;
            // if it was looping to itself previously,
            // it will now be pointed from its parent
            // (if we aren't removing its parent --
            // in that case, it remains looping to itself).
            // otherwise, it will continue to have the same
            // predecessor.
            if ((p.predecessor == p) && (p.parent != h)) {
                p.predecessor = p.parent;
            }
            if (parent.left == p) {
                parent.left = child;
            } else {
                parent.right = child;
            }
            if (child.bitIndex > parent.bitIndex) {
                child.parent = parent;
            }
        }
        // Fix H's parent and child Nodes
        {
            // If H is a parent of its left and right child
            // then change them to P
            if (h.left.parent == h) {
                h.left.parent = p;
            }
            if (h.right.parent == h) {
                h.right.parent = p;
            }
            // Change H's parent
            if (h.parent.left == h) {
                h.parent.left = p;
            } else {
                h.parent.right = p;
            }
        }
        // Copy the remaining fields from H to P
        // p.bitIndex = h.bitIndex;
        p.parent = h.parent;
        p.left = h.left;
        p.right = h.right;
        // Make sure that if h was pointing to any uplinks,
        // p now points to them.
        if (isValidUplink(p.left, p)) {
            p.left.predecessor = p;
        }
        if (isValidUplink(p.right, p)) {
            p.right.predecessor = p;
        }
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
 

api document of method removeInternalEntry(TrieEntry<K, V>): 

     * Removes an internal entry from the {@link org.apache.commons.collections4.Trie}.
     *
     * If it's an internal Entry then "good luck" with understanding
     * this code. The Idea is essentially that Entry p takes Entry h's
     * place in the trie which requires some re-wiring.
     

dependent classes: 
class `org.apache.commons.collections4.trie.AbstractPatriciaTrie`:
	dependent fields:
		org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieEntry<K, V> org.apache.commons.collections4.trie.AbstractPatriciaTrie.root;
	related functions:
		method `private void removeExternalEntry(TrieEntry<K, V>)` returns `void`, related with `org.apache.commons.collections4.trie.AbstractPatriciaTrie.removeInternalEntry(TrieEntry<K, V>)`, api document: 
     * Removes an external entry from the {@link org.apache.commons.collections4.Trie}.
     *
     * If it's an external Entry then just remove it.
     * This is very easy and straight forward.
     ;
		method ` TrieEntry<K, V> nextEntryImpl(TrieEntry<K, V>, TrieEntry<K, V>, TrieEntry<K, V>)` returns `org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieEntry<K, V>`, related with `org.apache.commons.collections4.trie.AbstractPatriciaTrie.removeInternalEntry(TrieEntry<K, V>)`, api document: 
     * Scans for the next node, starting at the specified point, and using 'previous'
     * as a hint that the last node we returned was 'previous' (so we know not to return
     * it again).  If 'tree' is non-null, this will limit the search to the given tree.
     *
     * The basic premise is that each iteration can follow the following steps:
     *
     * 1) Scan all the way to the left.
     *   a) If we already started from this node last time, proceed to Step 2.
     *   b) If a valid uplink is found, use it.
     *   c) If the result is an empty node (root not set), break the scan.
     *   d) If we already returned the left node, break the scan.
     *
     * 2) Check the right.
     *   a) If we already returned the right node, proceed to Step 3.
     *   b) If it is a valid uplink, use it.
     *   c) Do Step 1 from the right node.
     *
     * 3) Back up through the parents until we encounter find a parent
     *    that we're not the right child of.
     *
     * 4) If there's no right child of that parent, the iteration is finished.
     *    Otherwise continue to Step 5.
     *
     * 5) Check to see if the right child is a valid uplink.
     *    a) If we already returned that child, proceed to Step 6.
     *       Otherwise, use it.
     *
     * 6) If the right child of the parent is the parent itself, we've
     *    already found &amp; returned the end of the Trie, so exit.
     *
     * 7) Do Step 1 on the parent's right child.
     ;
		method ` TrieEntry<K, V> previousEntry(TrieEntry<K, V>)` returns `org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieEntry<K, V>`, related with `org.apache.commons.collections4.trie.AbstractPatriciaTrie.removeInternalEntry(TrieEntry<K, V>)`, api document: 
     * Returns the node lexicographically before the given node (or null if none).
     *
     * This follows four simple branches:
     *  - If the uplink that returned us was a right uplink:
     *      - If predecessor's left is a valid uplink from predecessor, return it.
     *      - Else, follow the right path from the predecessor's left.
     *  - If the uplink that returned us was a left uplink:
     *      - Loop back through parents until we encounter a node where
     *        node != node.parent.left.
     *          - If node.parent.left is uplink from node.parent:
     *              - If node.parent.left is not root, return it.
     *              - If it is root &amp; root isEmpty, return null.
     *              - If it is root &amp; root !isEmpty, return root.
     *          - If node.parent.left is not uplink from node.parent:
     *              - Follow right path for first right child from node.parent.left
     *
     * @param start  the start entry
     ;
		method `public void clear()` returns `void`, related with `org.apache.commons.collections4.trie.AbstractPatriciaTrie.removeInternalEntry(TrieEntry<K, V>)`;
		method ` TrieEntry<K, V> addEntry(TrieEntry<K, V>, int)` returns `org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieEntry<K, V>`, related with `org.apache.commons.collections4.trie.AbstractPatriciaTrie.removeInternalEntry(TrieEntry<K, V>)`, api document: 
     * Adds the given {@link TrieEntry} to the {@link org.apache.commons.collections4.Trie}.
     ;
class `org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieEntry`:
	dependent fields:
		org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieEntry<K, V> org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieEntry.parent;
		int org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieEntry.bitIndex;
		org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieEntry<K, V> org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieEntry.right;
		org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieEntry<K, V> org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieEntry.left;
		org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieEntry<K, V> org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieEntry.predecessor;
	related functions:
		method `public String toString()` returns `java.lang.String`, related with `org.apache.commons.collections4.trie.AbstractPatriciaTrie.removeInternalEntry(TrieEntry<K, V>)`;
class `org.apache.commons.collections4.bloomfilter.Shape`:
	related functions:
		method `private static int checkNumberOfBits(int)` returns `int`, related with `org.apache.commons.collections4.trie.AbstractPatriciaTrie.removeInternalEntry(TrieEntry<K, V>)`, api document: 
     * Check number of bits is strictly positive.
     *
     * @param numberOfBits the number of bits
     * @return the number of bits
     * @throws IllegalArgumentException if the number of bits is {@code < 1}.
     ;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.