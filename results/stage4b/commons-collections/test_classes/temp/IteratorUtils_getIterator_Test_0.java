package org.apache.commons.collections4;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;

@ExtendWith(MockitoExtension.class)
class IteratorUtils_getIterator_Test {

    private IteratorUtils iteratorUtils;

    private static class CustomIterable implements Iterable<String> {

        @Override
        public Iterator<String> iterator() {
            return Arrays.asList("custom").iterator();
        }
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the IteratorUtils instance if necessary
        iteratorUtils = new IteratorUtils();
    }

    private NodeList createNodeList(final Node[] nodes) {
        return new NodeList() {

            @Override
            public int getLength() {
                return nodes.length;
            }

            @Override
            public Node item(final int index) {
                return nodes[index];
            }
        };
    }

    @Test
    void testGetIteratorWithNull() {
        // Test case where the input is null. The method should return an empty iterator.
        Iterator<?> result = IteratorUtils.getIterator(null);
        assertSame(IteratorUtils.EMPTY_ITERATOR, result, "Expected empty iterator for null input");
    }

    @Test
    void testGetIteratorWithIterator() {
        // Test case where the input is an instance of Iterator. The method should return the same iterator.
        Iterator<String> iterator = new ArrayList<>().iterator();
        Iterator<?> result = IteratorUtils.getIterator(iterator);
        assertSame(iterator, result, "Expected the same iterator instance as input");
    }

    @Test
    void testGetIteratorWithIterable() {
        // Test case where the input is an instance of Iterable. The method should return an iterator for the elements of the Iterable.
        Iterable<Integer> iterable = new ArrayList<>(Arrays.asList(1, 2, 3));
        Iterator<?> result = IteratorUtils.getIterator(iterable);
        assertTrue(result.hasNext(), "Iterator should have elements");
        assertEquals(1, result.next(), "First element should be 1");
    }

    @Test
    void testGetIteratorWithArray() {
        // Test case where the input is an array. The method should return an iterator that iterates over the array elements.
        String[] array = { "a", "b", "c" };
        Iterator<?> result = IteratorUtils.getIterator(array);
        assertTrue(result.hasNext(), "Iterator should have elements");
        assertEquals("a", result.next(), "First element should be 'a'");
    }

    @Test
    void testGetIteratorWithEnumeration() {
        // Test case where the input is an instance of Enumeration. The method should return an iterator for the elements of the Enumeration.
        Enumeration<Integer> enumeration = new Vector<>(Arrays.asList(1, 2, 3)).elements();
        Iterator<?> result = IteratorUtils.getIterator(enumeration);
        assertTrue(result.hasNext(), "Iterator should have elements");
        assertEquals(1, result.next(), "First element should be 1");
    }

    @Test
    void testGetIteratorWithMap() {
        // Test case where the input is a Map. The method should return an iterator over the values of the Map.
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "a");
        map.put(2, "b");
        Iterator<?> result = IteratorUtils.getIterator(map);
        assertTrue(result.hasNext(), "Iterator should have elements");
        assertEquals("a", result.next(), "First value should be 'a'");
    }

    @Test
    void testGetIteratorWithNodeList() {
        // Test case where the input is an instance of NodeList. The method should return a NodeListIterator.
        // Provide actual nodes if necessary
        NodeList nodeList = createNodeList(new Node[] {});
        Iterator<?> result = IteratorUtils.getIterator(nodeList);
        assertFalse(result.hasNext(), "NodeList should be empty");
    }

    @Test
    void testGetIteratorWithNode() {
        // Test case where the input is an instance of Node. The method should return a NodeListIterator.
        Node node = mock(Node.class);
        Iterator<?> result = IteratorUtils.getIterator(node);
        assertTrue(result instanceof NodeListIterator, "Expected a NodeListIterator instance");
    }

    @Test
    void testGetIteratorWithDictionary() {
        // Test case where the input is an instance of Dictionary. The method should return an iterator for the elements of the Dictionary.
        Dictionary<Integer, String> dictionary = new Hashtable<>(Map.of(1, "a", 2, "b"));
        Iterator<?> result = IteratorUtils.getIterator(dictionary);
        assertTrue(result.hasNext(), "Iterator should have elements");
        assertEquals("a", result.next(), "First value should be 'a'");
    }

    @Test
    void testGetIteratorWithPrimitiveArray() {
        // Test case where the input is a primitive array. The method should return an iterator that iterates over the array elements.
        char[] charArray = { 'a', 'b', 'c' };
        Iterator<?> result = IteratorUtils.getIterator(charArray);
        assertTrue(result.hasNext(), "Iterator should have elements");
        assertEquals('a', result.next(), "First element should be 'a'");
    }

    @Test
    void testGetIteratorWithCustomIterable() {
        // Test case where the input has an 'iterator' method that returns an Iterator. The method should return the iterator from that method.
        CustomIterable customIterable = new CustomIterable();
        Iterator<?> result = IteratorUtils.getIterator(customIterable);
        assertTrue(result.hasNext(), "Iterator should have elements");
        assertEquals("custom", result.next(), "First element should be 'custom'");
    }

    @Test
    void testGetIteratorWithNoMatchingType() {
        // Test case where the input does not match any specific type. The method should return a singleton iterator containing the input object.
        Object obj = new Object();
        Iterator<?> result = IteratorUtils.getIterator(obj);
        assertTrue(result.hasNext(), "Iterator should have elements");
        assertSame(obj, result.next(), "Expected singleton iterator containing the input object");
    }
}
