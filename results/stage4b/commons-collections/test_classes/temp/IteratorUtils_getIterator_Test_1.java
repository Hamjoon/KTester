package org.apache.commons.collections4;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;
import org.w3c.dom.Node;
import org.apache.commons.collections4.iterators.NodeListIterator;
import org.w3c.dom.NodeList;

@ExtendWith(MockitoExtension.class)
class IteratorUtils_getIterator_Test {

    private IteratorUtils iteratorUtils;

    private static class CustomIterable implements Iterable<String> {

        @Override
        public Iterator<String> iterator() {
            return Arrays.asList("custom").iterator();
        }
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
        Iterator<?> result = IteratorUtils.getIterator(null);
        assertSame(IteratorUtils.EMPTY_ITERATOR, result, "Expected empty iterator for null input");
    }

    @Test
    void testGetIteratorWithIterator() {
        // Changed to use a non-empty list to create an iterator of type Iterator<Object>
        List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        Iterator<?> result = IteratorUtils.getIterator(list.iterator());
        assertSame(list.iterator(), result, "Expected the same iterator instance as input");
    }

    @Test
    void testGetIteratorWithIterable() {
        Iterable<Integer> iterable = new ArrayList<>(Arrays.asList(1, 2, 3));
        Iterator<?> result = IteratorUtils.getIterator(iterable);
        assertTrue(result.hasNext(), "Iterator should have elements");
        assertEquals(1, result.next(), "First element should be 1");
    }

    @Test
    void testGetIteratorWithArray() {
        String[] array = { "a", "b", "c" };
        Iterator<?> result = IteratorUtils.getIterator(array);
        assertTrue(result.hasNext(), "Iterator should have elements");
        assertEquals("a", result.next(), "First element should be 'a'");
    }

    @Test
    void testGetIteratorWithEnumeration() {
        Enumeration<Integer> enumeration = new Vector<>(Arrays.asList(1, 2, 3)).elements();
        Iterator<?> result = IteratorUtils.getIterator(enumeration);
        assertTrue(result.hasNext(), "Iterator should have elements");
        assertEquals(1, result.next(), "First element should be 1");
    }

    @Test
    void testGetIteratorWithMap() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "a");
        map.put(2, "b");
        Iterator<?> result = IteratorUtils.getIterator(map);
        assertTrue(result.hasNext(), "Iterator should have elements");
        assertEquals("a", result.next(), "First value should be 'a'");
    }

    @Test
    void testGetIteratorWithNodeList() {
        NodeList nodeList = createNodeList(new Node[] {});
        Iterator<?> result = IteratorUtils.getIterator(nodeList);
        assertFalse(result.hasNext(), "NodeList should be empty");
    }

    @Test
    void testGetIteratorWithNode() {
        Node node = mock(Node.class);
        Iterator<?> result = IteratorUtils.getIterator(node);
        assertTrue(result instanceof NodeListIterator, "Expected a NodeListIterator instance");
    }

    @Test
    void testGetIteratorWithDictionary() {
        Dictionary<Integer, String> dictionary = new Hashtable<>(Map.of(1, "a", 2, "b"));
        Iterator<?> result = IteratorUtils.getIterator(dictionary);
        assertTrue(result.hasNext(), "Iterator should have elements");
        assertEquals("a", result.next(), "First value should be 'a'");
    }

    @Test
    void testGetIteratorWithPrimitiveArray() {
        char[] charArray = { 'a', 'b', 'c' };
        Iterator<?> result = IteratorUtils.getIterator(charArray);
        assertTrue(result.hasNext(), "Iterator should have elements");
        assertEquals('a', result.next(), "First element should be 'a'");
    }

    @Test
    void testGetIteratorWithCustomIterable() {
        CustomIterable customIterable = new CustomIterable();
        Iterator<?> result = IteratorUtils.getIterator(customIterable);
        assertTrue(result.hasNext(), "Iterator should have elements");
        assertEquals("custom", result.next(), "First element should be 'custom'");
    }

    @Test
    void testGetIteratorWithNoMatchingType() {
        Object obj = new Object();
        Iterator<?> result = IteratorUtils.getIterator(obj);
        assertTrue(result.hasNext(), "Iterator should have elements");
        assertSame(obj, result.next(), "Expected singleton iterator containing the input object");
    }
}
