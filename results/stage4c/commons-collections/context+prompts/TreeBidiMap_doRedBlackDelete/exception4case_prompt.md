You are a professional software tester. Write test cases for a focal method in a target class.

**Instructions:**
1. Analyze method behavior and identify ALL possible exception scenarios
2. Design test cases that trigger each identified exception
3. Follow existing test case structure, Avoid duplication and create new test cases for uncovered exception scenarios

Test Case Format:
1. Group test cases by test function name
2. Each test case MUST include:
   - Input parameters with values (use empty list `[]` if none)
   - Expected exception or observable behavior
   - Clear description of test scenario
3. Return empty array if no exception scenarios found

Output Schema (STRICT JSON format):
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
Here is the code of focal method, `doRedBlackDelete(Node<K, V>)`, with the focal class `TreeBidiMap`:
```Java
package org.apache.commons.collections4.bidimap;
import org.apache.commons.collections4.iterators.EmptyOrderedMapIterator;
import static org.apache.commons.collections4.bidimap.TreeBidiMap.DataElement.KEY;
import java.util.Set;
import org.apache.commons.collections4.KeyValue;
import java.util.ConcurrentModificationException;
import static org.apache.commons.collections4.bidimap.TreeBidiMap.DataElement.VALUE;
import java.io.IOException;
import org.apache.commons.collections4.OrderedBidiMap;
import java.util.AbstractSet;
import java.io.ObjectOutputStream;
import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.keyvalue.UnmodifiableMapEntry;
import org.apache.commons.collections4.MapIterator;
import java.io.ObjectInputStream;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Map;
import java.io.Serializable;
import org.apache.commons.collections4.OrderedIterator;
public class TreeBidiMap<K extends Comparable<K>, V extends Comparable<V>> implements OrderedBidiMap<K, V> , Serializable {
    private transient Node<K, V>[] rootNode;
        enum DataElement {
    
        KEY("key"),
        VALUE("value");
    
        private final String description;
    
        /**
         * Creates a new TreeBidiMap.DataElement.
         *
         * @param description
         * 		the description for the element
         */
        DataElement(final String description) {
            this.description = description;
        }
    
        @Override
        public String toString() {
            return description;
        }
    }
    /**
     * A node used to store the data.
     */
    static class Node<K extends Comparable<K>, V extends Comparable<V>> implements Map.Entry<K, V> , KeyValue<K, V> {
        private final K key;
    
        private final V value;
    
        private final Node<K, V>[] leftNode;
    
        private final Node<K, V>[] rightNode;
    
        private final Node<K, V>[] parentNode;
    
        private final boolean[] blackColor;
    
        private int hashCodeValue;
    
        private boolean calculatedHashCode;
    
        /**
         * Makes a new cell with given key and value, and with null
         * links, and black (true) colors.
         *
         * @param key
         * 		the key of this node
         * @param value
         * 		the value of this node
         */
        @SuppressWarnings("unchecked")
        Node(final K key, final V value) {
            this.key = key;
            this.value = value;
            leftNode = new Node[2];
            rightNode = new Node[2];
            parentNode = new Node[2];
            blackColor = new boolean[]{ true, true };
            calculatedHashCode = false;
        }
    
        /**
         * Makes this node the same color as another.
         *
         * @param node
         * 		the node whose color we're adopting
         * @param dataElement
         * 		either the {@link DataElement#KEY key}
         * 		or the {@link DataElement#VALUE value}.
         */
        private void copyColor(final Node<K, V> node, final DataElement dataElement) {
            blackColor[dataElement.ordinal()] = node.blackColor[dataElement.ordinal()];
        }
    
        /**
         * Compares the specified object with this entry for equality.
         * Returns true if the given object is also a map entry and
         * the two entries represent the same mapping.
         *
         * @param obj
         * 		the object to be compared for equality with this entry.
         * @return true if the specified object is equal to this entry.
         */
        @Override
        public boolean equals(final Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            final Map.Entry<?, ?> e = ((Map.Entry<?, ?>) (obj));
            return getKey().equals(e.getKey()) && getValue().equals(e.getValue());
        }
    
        private Object getData(final DataElement dataElement) {
            switch (dataElement) {
                case KEY :
                    return getKey();
                case VALUE :
                    return getValue();
                default :
                    throw new IllegalArgumentException();
            }
        }
    
        /**
         * Gets the key.
         *
         * @return the key corresponding to this entry.
         */
        @Override
        public K getKey() {
            return key;
        }
    
        private Node<K, V> getLeft(final DataElement dataElement) {
            return leftNode[dataElement.ordinal()];
        }
    
        /**
         * Gets the parent node.
         *
         * @param dataElement
         * 		either the {@link DataElement#KEY key}
         * 		or the {@link DataElement#VALUE value}.
         * @return the parent node, may be null
         */
        private Node<K, V> getParent(final DataElement dataElement) {
            return parentNode[dataElement.ordinal()];
        }
    
        private Node<K, V> getRight(final DataElement dataElement) {
            return rightNode[dataElement.ordinal()];
        }
    
        /**
         * Gets the value.
         *
         * @return the value corresponding to this entry.
         */
        @Override
        public V getValue() {
            return value;
        }
    
        /**
         *
         * @return the hash code value for this map entry.
         */
        @Override
        public int hashCode() {
            if (!calculatedHashCode) {
                hashCodeValue = getKey().hashCode() ^ getValue().hashCode();
                calculatedHashCode = true;
            }
            return hashCodeValue;
        }
    
        /**
         * Is this node black?
         *
         * @param dataElement
         * 		either the {@link DataElement#KEY key}
         * 		or the {@link DataElement#VALUE value}.
         * @return true if black (which is represented as a true boolean)
         */
        private boolean isBlack(final DataElement dataElement) {
            return blackColor[dataElement.ordinal()];
        }
    
        private boolean isLeftChild(final DataElement dataElement) {
            return (parentNode[dataElement.ordinal()] != null) && (parentNode[dataElement.ordinal()].leftNode[dataElement.ordinal()] == this);
        }
    
        /**
         * Is this node red?
         *
         * @param dataElement
         * 		either the {@link DataElement#KEY key}
         * 		or the {@link DataElement#VALUE value}.
         * @return true if non-black
         */
        private boolean isRed(final DataElement dataElement) {
            return !blackColor[dataElement.ordinal()];
        }
    
        private boolean isRightChild(final DataElement dataElement) {
            return (parentNode[dataElement.ordinal()] != null) && (parentNode[dataElement.ordinal()].rightNode[dataElement.ordinal()] == this);
        }
    
        /**
         * Makes this node black.
         *
         * @param dataElement
         * 		either the {@link DataElement#KEY key}
         * 		or the {@link DataElement#VALUE value}.
         */
        private void setBlack(final DataElement dataElement) {
            blackColor[dataElement.ordinal()] = true;
        }
    
        private void setLeft(final Node<K, V> node, final DataElement dataElement) {
            leftNode[dataElement.ordinal()] = node;
        }
    
        /**
         * Sets this node's parent node.
         *
         * @param node
         * 		the new parent node
         * @param dataElement
         * 		either the {@link DataElement#KEY key}
         * 		or the {@link DataElement#VALUE value}.
         */
        private void setParent(final Node<K, V> node, final DataElement dataElement) {
            parentNode[dataElement.ordinal()] = node;
        }
    
        /**
         * Makes this node red.
         *
         * @param dataElement
         * 		either the {@link DataElement#KEY key}
         * 		or the {@link DataElement#VALUE value}.
         */
        private void setRed(final DataElement dataElement) {
            blackColor[dataElement.ordinal()] = false;
        }
    
        private void setRight(final Node<K, V> node, final DataElement dataElement) {
            rightNode[dataElement.ordinal()] = node;
        }
    
        /**
         * Optional operation that is not permitted in this implementation.
         *
         * @param ignored
         * 		this parameter is ignored.
         * @return does not return
         * @throws UnsupportedOperationException
         * 		always
         */
        @Override
        public V setValue(final V ignored) throws UnsupportedOperationException {
            throw new UnsupportedOperationException("Map.Entry.setValue is not supported");
        }
    
        /**
         * Exchanges colors with another node.
         *
         * @param node
         * 		the node to swap with
         * @param dataElement
         * 		either the {@link DataElement#KEY key}
         * 		or the {@link DataElement#VALUE value}.
         */
        private void swapColors(final Node<K, V> node, final DataElement dataElement) {
            // Swap colors -- old hacker's trick
            blackColor[dataElement.ordinal()] ^= node.blackColor[dataElement.ordinal()];
            node.blackColor[dataElement.ordinal()] ^= blackColor[dataElement.ordinal()];
            blackColor[dataElement.ordinal()] ^= node.blackColor[dataElement.ordinal()];
        }
    }
private void doRedBlackDelete(final Node<K, V> deletedNode) {
        for (final DataElement dataElement : DataElement.values()) {
            // if deleted node has both left and children, swap with
            // the next greater node
            if ((deletedNode.getLeft(dataElement) != null) && (deletedNode.getRight(dataElement) != null)) {
                swapPosition(nextGreater(deletedNode, dataElement), deletedNode, dataElement);
            }
            final Node<K, V> replacement = (deletedNode.getLeft(dataElement) != null) ? deletedNode.getLeft(dataElement) : deletedNode.getRight(dataElement);
            if (replacement != null) {
                replacement.setParent(deletedNode.getParent(dataElement), dataElement);
                if (deletedNode.getParent(dataElement) == null) {
                    rootNode[dataElement.ordinal()] = replacement;
                } else if (deletedNode == deletedNode.getParent(dataElement).getLeft(dataElement)) {
                    deletedNode.getParent(dataElement).setLeft(replacement, dataElement);
                } else {
                    deletedNode.getParent(dataElement).setRight(replacement, dataElement);
                }
                deletedNode.setLeft(null, dataElement);
                deletedNode.setRight(null, dataElement);
                deletedNode.setParent(null, dataElement);
                if (isBlack(deletedNode, dataElement)) {
                    doRedBlackDeleteFixup(replacement, dataElement);
                }
            } else // replacement is null
            if (deletedNode.getParent(dataElement) == null) {
                // empty tree
                rootNode[dataElement.ordinal()] = null;
            } else {
                // deleted node had no children
                if (isBlack(deletedNode, dataElement)) {
                    doRedBlackDeleteFixup(deletedNode, dataElement);
                }
                if (deletedNode.getParent(dataElement) != null) {
                    if (deletedNode == deletedNode.getParent(dataElement).getLeft(dataElement)) {
                        deletedNode.getParent(dataElement).setLeft(null, dataElement);
                    } else {
                        deletedNode.getParent(dataElement).setRight(null, dataElement);
                    }
                    deletedNode.setParent(null, dataElement);
                }
            }
        }
        shrink();
    }
@Override
public void clear();
    @Override
public boolean containsKey(final Object key);
    @Override
public boolean containsValue(final Object value);
    private void doRedBlackDeleteFixup(final Node<K, V> replacementNode, final DataElement dataElement);
    @Override
public Set<Map.Entry<K, V>> entrySet();
    @Override
public boolean equals(final Object obj);
    @Override
public K firstKey();
    @Override
public V get(final Object key);
    @Override
public K getKey(final Object value);
    @Override
public int hashCode();
    @Override
public OrderedBidiMap<V, K> inverseBidiMap();
    private static boolean isBlack(final Node<?, ?> node, final DataElement dataElement);
    @Override
public boolean isEmpty();
    @Override
public Set<K> keySet();
    @Override
public K lastKey();
    @Override
public OrderedMapIterator<K, V> mapIterator();
    private Node<K, V> nextGreater(final Node<K, V> node, final DataElement dataElement);
    @Override
public K nextKey(final K key);
    @Override
public K previousKey(final K key);
    @Override
public V put(final K key, final V value);
    @Override
public void putAll(final Map<? extends K, ? extends V> map);
    @Override
public V remove(final Object key);
    @Override
public K removeValue(final Object value);
    private void shrink();
    @Override
public int size();
    private void swapPosition(final Node<K, V> x, final Node<K, V> y, final DataElement dataElement);
    @Override
public String toString();
    @Override
public Set<V> values();
}
```
@input{existing test case}
here's the existing test cases:
```json
<cases_json>
```

@output{test cases}: output the test cases in the JSON format: