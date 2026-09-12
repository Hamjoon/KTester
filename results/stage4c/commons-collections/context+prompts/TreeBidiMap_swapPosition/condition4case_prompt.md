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
Here is the code of focal method, `swapPosition(Node<K, V>, Node<K, V>, DataElement)`, with the focal class `TreeBidiMap`:
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
private void swapPosition(final Node<K, V> x, final Node<K, V> y, final DataElement dataElement) {
        // Save initial values.
        final Node<K, V> xFormerParent = x.getParent(dataElement);
        final Node<K, V> xFormerLeftChild = x.getLeft(dataElement);
        final Node<K, V> xFormerRightChild = x.getRight(dataElement);
        final Node<K, V> yFormerParent = y.getParent(dataElement);
        final Node<K, V> yFormerLeftChild = y.getLeft(dataElement);
        final Node<K, V> yFormerRightChild = y.getRight(dataElement);
        final boolean xWasLeftChild = (x.getParent(dataElement) != null) && (x == x.getParent(dataElement).getLeft(dataElement));
        final boolean yWasLeftChild = (y.getParent(dataElement) != null) && (y == y.getParent(dataElement).getLeft(dataElement));
        // Swap, handling special cases of one being the other's parent.
        if (x == yFormerParent) {
            // x was y's parent
            x.setParent(y, dataElement);
            if (yWasLeftChild) {
                y.setLeft(x, dataElement);
                y.setRight(xFormerRightChild, dataElement);
            } else {
                y.setRight(x, dataElement);
                y.setLeft(xFormerLeftChild, dataElement);
            }
        } else {
            x.setParent(yFormerParent, dataElement);
            if (yFormerParent != null) {
                if (yWasLeftChild) {
                    yFormerParent.setLeft(x, dataElement);
                } else {
                    yFormerParent.setRight(x, dataElement);
                }
            }
            y.setLeft(xFormerLeftChild, dataElement);
            y.setRight(xFormerRightChild, dataElement);
        }
        if (y == xFormerParent) {
            // y was x's parent
            y.setParent(x, dataElement);
            if (xWasLeftChild) {
                x.setLeft(y, dataElement);
                x.setRight(yFormerRightChild, dataElement);
            } else {
                x.setRight(y, dataElement);
                x.setLeft(yFormerLeftChild, dataElement);
            }
        } else {
            y.setParent(xFormerParent, dataElement);
            if (xFormerParent != null) {
                if (xWasLeftChild) {
                    xFormerParent.setLeft(y, dataElement);
                } else {
                    xFormerParent.setRight(y, dataElement);
                }
            }
            x.setLeft(yFormerLeftChild, dataElement);
            x.setRight(yFormerRightChild, dataElement);
        }
        // Fix children's parent pointers
        if (x.getLeft(dataElement) != null) {
            x.getLeft(dataElement).setParent(x, dataElement);
        }
        if (x.getRight(dataElement) != null) {
            x.getRight(dataElement).setParent(x, dataElement);
        }
        if (y.getLeft(dataElement) != null) {
            y.getLeft(dataElement).setParent(y, dataElement);
        }
        if (y.getRight(dataElement) != null) {
            y.getRight(dataElement).setParent(y, dataElement);
        }
        x.swapColors(y, dataElement);
        // Check if root changed
        if (rootNode[dataElement.ordinal()] == x) {
            rootNode[dataElement.ordinal()] = y;
        } else if (rootNode[dataElement.ordinal()] == y) {
            rootNode[dataElement.ordinal()] = x;
        }
    }
@Override
public void clear();
    @Override
public boolean containsKey(final Object key);
    @Override
public boolean containsValue(final Object value);
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
    @Override
public boolean isEmpty();
    @Override
public Set<K> keySet();
    @Override
public K lastKey();
    @Override
public OrderedMapIterator<K, V> mapIterator();
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
    @Override
public int size();
    @Override
public String toString();
    @Override
public Set<V> values();
}
```

@output{test cases}: output the test cases in the JSON format: