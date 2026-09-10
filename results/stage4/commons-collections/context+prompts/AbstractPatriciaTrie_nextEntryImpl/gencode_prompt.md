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
Here is the code of focal method, `nextEntryImpl(TrieEntry<K, V>, TrieEntry<K, V>, TrieEntry<K, V>)`, with the focal class `AbstractPatriciaTrie`:
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
TrieEntry<K, V> nextEntryImpl(final TrieEntry<K, V> start, final TrieEntry<K, V> previous, final TrieEntry<K, V> tree) {
        TrieEntry<K, V> current = start;
        // Only look at the left if this was a recursive or
        // the first check, otherwise we know we've already looked
        // at the left.
        if ((previous == null) || (start != previous.predecessor)) {
            while (!current.left.isEmpty()) {
                // stop traversing if we've already
                // returned the left of this node.
                if (previous == current.left) {
                    break;
                }
                if (isValidUplink(current.left, current)) {
                    return current.left;
                }
                current = current.left;
            } 
        }
        // If there's no data at all, exit.
        if (current.isEmpty()) {
            return null;
        }
        // If we've already returned the left,
        // and the immediate right is null,
        // there's only one entry in the Trie
        // which is stored at the root.
        // 
        // / ("")   <-- root
        // \_/  \
        // null <-- 'current'
        // 
        if (current.right == null) {
            return null;
        }
        // If nothing valid on the left, try the right.
        if (previous != current.right) {
            // See if it immediately is valid.
            if (isValidUplink(current.right, current)) {
                return current.right;
            }
            // Must search on the right's side if it wasn't initially valid.
            return nextEntryImpl(current.right, previous, tree);
        }
        // Neither left nor right are valid, find the first parent
        // whose child did not come from the right & traverse it.
        while (current == current.parent.right) {
            // If we're going to traverse to above the subtree, stop.
            if (current == tree) {
                return null;
            }
            current = current.parent;
        } 
        // If we're on the top of the subtree, we can't go any higher.
        if (current == tree) {
            return null;
        }
        // If there's no right, the parent must be root, so we're done.
        if (current.parent.right == null) {
            return null;
        }
        // If the parent's right points to itself, we've found one.
        if ((previous != current.parent.right) && isValidUplink(current.parent.right, current.parent)) {
            return current.parent.right;
        }
        // If the parent's right is itself, there can't be any more nodes.
        if (current.parent.right == current.parent) {
            return null;
        }
        // We need to traverse down the parent's right's path.
        return nextEntryImpl(current.parent.right, previous, tree);
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
 

api document of method nextEntryImpl(TrieEntry<K, V>, TrieEntry<K, V>, TrieEntry<K, V>): 

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
     

return type: 
org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieEntry<K, V>

dependent classes: 
class `org.apache.commons.collections4.trie.AbstractPatriciaTrie`:
	related functions:
		method `private void removeInternalEntry(TrieEntry<K, V>)` returns `void`, related with `org.apache.commons.collections4.trie.AbstractPatriciaTrie.nextEntryImpl(TrieEntry<K, V>, TrieEntry<K, V>, TrieEntry<K, V>)`, api document: 
     * Removes an internal entry from the {@link org.apache.commons.collections4.Trie}.
     *
     * If it's an internal Entry then "good luck" with understanding
     * this code. The Idea is essentially that Entry p takes Entry h's
     * place in the trie which requires some re-wiring.
     ;
		method `private void removeExternalEntry(TrieEntry<K, V>)` returns `void`, related with `org.apache.commons.collections4.trie.AbstractPatriciaTrie.nextEntryImpl(TrieEntry<K, V>, TrieEntry<K, V>, TrieEntry<K, V>)`, api document: 
     * Removes an external entry from the {@link org.apache.commons.collections4.Trie}.
     *
     * If it's an external Entry then just remove it.
     * This is very easy and straight forward.
     ;
		method ` TrieEntry<K, V> addEntry(TrieEntry<K, V>, int)` returns `org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieEntry<K, V>`, related with `org.apache.commons.collections4.trie.AbstractPatriciaTrie.nextEntryImpl(TrieEntry<K, V>, TrieEntry<K, V>, TrieEntry<K, V>)`, api document: 
     * Adds the given {@link TrieEntry} to the {@link org.apache.commons.collections4.Trie}.
     ;
		method `private boolean selectR(TrieEntry<K, V>, int, K, int, Reference<Map.Entry<K, V>>)` returns `boolean`, related with `org.apache.commons.collections4.trie.AbstractPatriciaTrie.nextEntryImpl(TrieEntry<K, V>, TrieEntry<K, V>, TrieEntry<K, V>)`;
		method ` TrieEntry<K, V> followLeft(TrieEntry<K, V>)` returns `org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieEntry<K, V>`, related with `org.apache.commons.collections4.trie.AbstractPatriciaTrie.nextEntryImpl(TrieEntry<K, V>, TrieEntry<K, V>, TrieEntry<K, V>)`, api document: 
     * Goes left through the tree until it finds a valid node.
     ;
		method ` TrieEntry<K, V> nextEntry(TrieEntry<K, V>)` returns `org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieEntry<K, V>`, related with `org.apache.commons.collections4.trie.AbstractPatriciaTrie.nextEntryImpl(TrieEntry<K, V>, TrieEntry<K, V>, TrieEntry<K, V>)`, api document: 
     * Returns the entry lexicographically after the given entry.
     * If the given entry is null, returns the first node.
     ;
		method ` TrieEntry<K, V> previousEntry(TrieEntry<K, V>)` returns `org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieEntry<K, V>`, related with `org.apache.commons.collections4.trie.AbstractPatriciaTrie.nextEntryImpl(TrieEntry<K, V>, TrieEntry<K, V>, TrieEntry<K, V>)`, api document: 
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
class `org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieEntry`:
	dependent fields:
		org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieEntry<K, V> org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieEntry.predecessor;
		org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieEntry<K, V> org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieEntry.parent;
		org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieEntry<K, V> org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieEntry.right;
		org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieEntry<K, V> org.apache.commons.collections4.trie.AbstractPatriciaTrie.TrieEntry.left;
	



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.