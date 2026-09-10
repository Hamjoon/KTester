You are tasked with creating a framework for a test class in Java. Now analyze the following information carefully:

1. The method that needs to be tested and its parameters.
2. The constructor of the target class and its parameters.
3. Any dependencies or external resources used by the target class.

Based on this analysis, complete the test class template in the provided structure:

1. Initialize necessary fields and dependencies within setupBeforeAll() and setupBeforeEach().
2. Handle any required cleanup in teardownAfterEach() and teardownAfterAll().
3. Make sure not to invoke any private methods or fields of the target class and dependent class.
4. Refer to the "given" phase in the existing test class for guidance.
5. Add necessary import statements and ensure there are no syntax errors in the generated code.

@input{focal method and target class}
Here is the code of focal method, `getIterator(Object)`, with the focal class `IteratorUtils_getIterator_Test`:
```Java
package org.apache.commons.collections4;
import java.util.Dictionary;
import org.apache.commons.collections4.iterators.EmptyOrderedMapIterator;
import org.apache.commons.collections4.iterators.EnumerationIterator;
import org.apache.commons.collections4.iterators.EmptyMapIterator;
import org.apache.commons.collections4.iterators.UnmodifiableMapIterator;
import org.apache.commons.collections4.iterators.EmptyIterator;
import org.apache.commons.collections4.iterators.SingletonIterator;
import org.apache.commons.collections4.iterators.EmptyOrderedIterator;
import org.w3c.dom.Node;
import java.util.ArrayList;
import org.apache.commons.collections4.iterators.TransformIterator;
import org.apache.commons.collections4.iterators.PushbackIterator;
import org.apache.commons.collections4.iterators.EmptyListIterator;
import org.apache.commons.collections4.iterators.ListIteratorWrapper;
import java.util.Comparator;
import org.apache.commons.collections4.functors.EqualPredicate;
import org.apache.commons.collections4.iterators.ObjectArrayListIterator;
import org.apache.commons.collections4.iterators.IteratorIterable;
import org.apache.commons.collections4.iterators.BoundedIterator;
import org.apache.commons.collections4.iterators.FilterIterator;
import java.util.Iterator;
import org.apache.commons.collections4.iterators.LoopingListIterator;
import java.util.List;
import org.apache.commons.collections4.iterators.SkippingIterator;
import org.apache.commons.collections4.iterators.SingletonListIterator;
import org.apache.commons.collections4.iterators.ZippingIterator;
import org.apache.commons.collections4.iterators.ArrayListIterator;
import org.apache.commons.collections4.iterators.FilterListIterator;
import org.apache.commons.collections4.iterators.IteratorEnumeration;
import org.apache.commons.collections4.iterators.NodeListIterator;
import org.w3c.dom.NodeList;
import org.apache.commons.collections4.iterators.CollatingIterator;
import java.lang.reflect.Method;
import org.apache.commons.collections4.iterators.PeekingIterator;
import org.apache.commons.collections4.iterators.IteratorChain;
import org.apache.commons.collections4.iterators.ObjectArrayIterator;
import java.util.Enumeration;
import java.util.ListIterator;
import org.apache.commons.collections4.iterators.UnmodifiableListIterator;
import org.apache.commons.collections4.iterators.ArrayIterator;
import java.lang.reflect.Array;
import org.apache.commons.collections4.iterators.UnmodifiableIterator;
import java.util.Collection;
import java.util.Objects;
import org.apache.commons.collections4.iterators.ObjectGraphIterator;
import java.util.Map;
import org.apache.commons.collections4.iterators.LoopingIterator;
public class IteratorUtils {
    @SuppressWarnings("rawtypes")
public static final ResettableIterator EMPTY_ITERATOR = EmptyIterator.RESETTABLE_INSTANCE;
    @SuppressWarnings("rawtypes")
public static final ResettableListIterator EMPTY_LIST_ITERATOR = EmptyListIterator.RESETTABLE_INSTANCE;
    @SuppressWarnings("rawtypes")
public static final OrderedIterator EMPTY_ORDERED_ITERATOR = EmptyOrderedIterator.INSTANCE;
    @SuppressWarnings("rawtypes")
public static final MapIterator EMPTY_MAP_ITERATOR = EmptyMapIterator.INSTANCE;
    @SuppressWarnings("rawtypes")
public static final OrderedMapIterator EMPTY_ORDERED_MAP_ITERATOR = EmptyOrderedMapIterator.INSTANCE;
    public static Iterator<?> getIterator(final Object obj) {
        if (obj == null) {
            return emptyIterator();
        }
        if (obj instanceof Iterator) {
            return ((Iterator<?>) (obj));
        }
        if (obj instanceof Iterable) {
            return ((Iterable<?>) (obj)).iterator();
        }
        if (obj instanceof Object[]) {
            return new ObjectArrayIterator<>(((Object[]) (obj)));
        }
        if (obj instanceof Enumeration) {
            return new EnumerationIterator<>(((Enumeration<?>) (obj)));
        }
        if (obj instanceof Map) {
            return ((Map<?, ?>) (obj)).values().iterator();
        }
        if (obj instanceof NodeList) {
            return new NodeListIterator(((NodeList) (obj)));
        }
        if (obj instanceof Node) {
            return new NodeListIterator(((Node) (obj)));
        }
        if (obj instanceof Dictionary) {
            return new EnumerationIterator<>(((Dictionary<?, ?>) (obj)).elements());
        }
        if (obj.getClass().isArray()) {
            return new ArrayIterator<>(obj);
        }
        try {
            final Method method = obj.getClass().getMethod("iterator", ((Class[]) (null)));
            if (Iterator.class.isAssignableFrom(method.getReturnType())) {
                final Iterator<?> it = ((Iterator<?>) (method.invoke(obj, ((Object[]) (null)))));
                if (it != null) {
                    return it;
                }
            }
        } catch (RuntimeException | ReflectiveOperationException ignore) {
            // NOPMD
            // ignore
        }
        return singletonIterator(obj);
    }
public static <E> ResettableIterator<E> arrayIterator(final Object array);
    public static <E> ResettableIterator<E> arrayIterator(final Object array, final int start);
    public static <E> ResettableIterator<E> arrayIterator(final Object array, final int start, final int end);
    public static <E> ResettableIterator<E> arrayIterator(final E... array);
    public static <E> ResettableIterator<E> arrayIterator(final E[] array, final int start);
    public static <E> ResettableIterator<E> arrayIterator(final E[] array, final int start, final int end);
    public static <E> ResettableListIterator<E> arrayListIterator(final Object array);
    public static <E> ResettableListIterator<E> arrayListIterator(final Object array, final int start);
    public static <E> ResettableListIterator<E> arrayListIterator(final Object array, final int start, final int end);
    public static <E> ResettableListIterator<E> arrayListIterator(final E... array);
    public static <E> ResettableListIterator<E> arrayListIterator(final E[] array, final int start);
    public static <E> ResettableListIterator<E> arrayListIterator(final E[] array, final int start, final int end);
    public static <E> Enumeration<E> asEnumeration(final Iterator<? extends E> iterator);
    public static <E> Iterable<E> asIterable(final Iterator<? extends E> iterator);
    public static <E> Iterator<E> asIterator(final Enumeration<? extends E> enumeration);
    public static <E> Iterator<E> asIterator(final Enumeration<? extends E> enumeration, final Collection<? super E> removeCollection);
    public static <E> Iterable<E> asMultipleUseIterable(final Iterator<? extends E> iterator);
    public static <E> BoundedIterator<E> boundedIterator(final Iterator<? extends E> iterator, final long max);
    public static <E> BoundedIterator<E> boundedIterator(final Iterator<? extends E> iterator, final long offset, final long max);
    public static <E> Iterator<E> chainedIterator(final Collection<Iterator<? extends E>> iterators);
    public static <E> Iterator<E> chainedIterator(final Iterator<? extends E> iterator1, final Iterator<? extends E> iterator2);
    public static <E> Iterator<E> chainedIterator(final Iterator<? extends E>... iterators);
    public static <E> Iterator<E> collatedIterator(final Comparator<? super E> comparator, final Collection<Iterator<? extends E>> iterators);
    public static <E> Iterator<E> collatedIterator(final Comparator<? super E> comparator, final Iterator<? extends E> iterator1, final Iterator<? extends E> iterator2);
    public static <E> Iterator<E> collatedIterator(final Comparator<? super E> comparator, final Iterator<? extends E>... iterators);
    public static <E> boolean contains(final Iterator<E> iterator, final Object object);
    public static <E> ResettableIterator<E> emptyIterator();
    public static <E> ResettableListIterator<E> emptyListIterator();
    public static <K, V> MapIterator<K, V> emptyMapIterator();
    public static <E> OrderedIterator<E> emptyOrderedIterator();
    public static <K, V> OrderedMapIterator<K, V> emptyOrderedMapIterator();
    public static <E> Iterator<E> filteredIterator(final Iterator<? extends E> iterator, final Predicate<? super E> predicate);
    public static <E> ListIterator<E> filteredListIterator(final ListIterator<? extends E> listIterator, final Predicate<? super E> predicate);
    public static <E> E find(final Iterator<E> iterator, final Predicate<? super E> predicate);
    public static <E> E first(final Iterator<E> iterator);
    public static <E> void forEach(final Iterator<E> iterator, final Closure<? super E> closure);
    public static <E> E forEachButLast(final Iterator<E> iterator, final Closure<? super E> closure);
    public static <E> E get(final Iterator<E> iterator, final int index);
    public static <E> int indexOf(final Iterator<E> iterator, final Predicate<? super E> predicate);
    public static boolean isEmpty(final Iterator<?> iterator);
    public static <E> ResettableIterator<E> loopingIterator(final Collection<? extends E> collection);
    public static <E> ResettableListIterator<E> loopingListIterator(final List<E> list);
    public static <E> boolean matchesAll(final Iterator<E> iterator, final Predicate<? super E> predicate);
    public static <E> boolean matchesAny(final Iterator<E> iterator, final Predicate<? super E> predicate);
    public static NodeListIterator nodeListIterator(final Node node);
    public static NodeListIterator nodeListIterator(final NodeList nodeList);
    public static <E> Iterator<E> objectGraphIterator(final E root, final Transformer<? super E, ? extends E> transformer);
    public static <E> Iterator<E> peekingIterator(final Iterator<? extends E> iterator);
    public static <E> Iterator<E> pushbackIterator(final Iterator<? extends E> iterator);
    public static <E> ResettableIterator<E> singletonIterator(final E object);
    public static <E> ListIterator<E> singletonListIterator(final E object);
    public static int size(final Iterator<?> iterator);
    public static <E> SkippingIterator<E> skippingIterator(final Iterator<E> iterator, final long offset);
    public static Object[] toArray(final Iterator<?> iterator);
    public static <E> E[] toArray(final Iterator<? extends E> iterator, final Class<E> arrayClass);
    public static <E> List<E> toList(final Iterator<? extends E> iterator);
    public static <E> List<E> toList(final Iterator<? extends E> iterator, final int estimatedSize);
    public static <E> ListIterator<E> toListIterator(final Iterator<? extends E> iterator);
    public static <E> String toString(final Iterator<E> iterator);
    public static <E> String toString(final Iterator<E> iterator, final Transformer<? super E, String> transformer);
    public static <E> String toString(final Iterator<E> iterator, final Transformer<? super E, String> transformer, final String delimiter, final String prefix, final String suffix);
    public static <I, O> Iterator<O> transformedIterator(final Iterator<? extends I> iterator, final Transformer<? super I, ? extends O> transformer);
    public static <E> Iterator<E> unmodifiableIterator(final Iterator<E> iterator);
    public static <E> ListIterator<E> unmodifiableListIterator(final ListIterator<E> listIterator);
    public static <K, V> MapIterator<K, V> unmodifiableMapIterator(final MapIterator<K, V> mapIterator);
    public static <E> ZippingIterator<E> zippingIterator(final Iterator<? extends E> a, final Iterator<? extends E> b);
    public static <E> ZippingIterator<E> zippingIterator(final Iterator<? extends E> a, final Iterator<? extends E> b, final Iterator<? extends E> c);
    public static <E> ZippingIterator<E> zippingIterator(final Iterator<? extends E>... iterators);
}
```


@input{context information}

api document of class org.apache.commons.collections4.IteratorUtils: 

 * Provides static utility methods and decorators for {@link Iterator}
 * instances. The implementations are provided in the iterators subpackage.
 *
 * @since 2.1
 

constructors for class `org.apache.commons.collections4.IteratorUtils`: 
params: 
body:
```java
    private IteratorUtils() {}

```

existing test class: 
```java
/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.collections4;

import static org.apache.commons.collections4.functors.EqualPredicate.equalPredicate;
import static org.apache.commons.collections4.functors.TruePredicate.INSTANCE;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Vector;

import org.apache.commons.collections4.iterators.ArrayIterator;
import org.apache.commons.collections4.iterators.EmptyIterator;
import org.apache.commons.collections4.iterators.EmptyListIterator;
import org.apache.commons.collections4.iterators.EmptyMapIterator;
import org.apache.commons.collections4.iterators.EmptyOrderedIterator;
import org.apache.commons.collections4.iterators.EmptyOrderedMapIterator;
import org.apache.commons.collections4.iterators.EnumerationIterator;
import org.apache.commons.collections4.iterators.NodeListIterator;
import org.apache.commons.collections4.iterators.ObjectArrayIterator;
import org.apache.commons.collections4.iterators.ZippingIterator;
import org.apache.commons.collections4.map.EntrySetToMapIteratorAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Tests for IteratorUtils.
 */
public class IteratorUtilsTest {

    /**
     * Collection of {@link Integer}s
     */
    private List<Integer> collectionA;

    /**
     * Collection of even {@link Integer}s
     */
    private List<Integer> collectionEven;

    /**
     * Collection of odd {@link Integer}s
     */
    private List<Integer> collectionOdd;

    private final Collection<Integer> emptyCollection = new ArrayList<>(1);

    private Iterable<Integer> iterableA;

    /**
     * Creates a NodeList containing the specified nodes.
     */
    private NodeList createNodeList(final Node[] nodes) {
        return new NodeList() {
            @Override
            public int getLength() {
                return nodes.length;
            }

            @Override
            public Node item(final int index) {
......
        assertTrue(listIterator.hasNext());

        assertEquals("c", listIterator.next());

        assertTrue(listIterator.hasPrevious());
        assertTrue(listIterator.hasNext());

        assertEquals("d", listIterator.next());

        assertTrue(listIterator.hasPrevious());
        assertFalse(listIterator.hasNext());

        assertEquals("d", listIterator.previous());

        assertTrue(listIterator.hasPrevious());
        assertTrue(listIterator.hasNext());

        assertEquals("c", listIterator.previous());

        assertTrue(listIterator.hasPrevious());
        assertTrue(listIterator.hasNext());

        assertEquals("b", listIterator.previous());

        assertTrue(listIterator.hasPrevious());
        assertTrue(listIterator.hasNext());

        assertEquals("a", listIterator.previous());

        assertFalse(listIterator.hasPrevious());
        assertTrue(listIterator.hasNext());
    }

    @Test
    public void testUnmodifiableMapIterator() {
        final Set<?> set = new LinkedHashSet<>();
        final MapIterator iterator = new EntrySetToMapIteratorAdapter(set);
        assertTrue(IteratorUtils.unmodifiableMapIterator(iterator) instanceof MapIterator, "create instance fail");
        assertThrows(NullPointerException.class, () -> IteratorUtils.unmodifiableMapIterator(null));
    }

    @Test
    public void testZippingIterator() {
        final ArrayList arrayList = new ArrayList();
        final Iterator iterator = arrayList.iterator();
        assertTrue(IteratorUtils.zippingIterator(iterator, iterator, iterator) instanceof ZippingIterator, "create instance fail");
        assertTrue(IteratorUtils.zippingIterator(iterator, iterator) instanceof ZippingIterator, "create instance fail");
    }

}
```



@input{test class template}
```java
package org.apache.commons.collections4;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
// Add other imports if necessary

@ExtendWith(MockitoExtension.class)
class IteratorUtils_getIterator_Test {
    @BeforeAll
    static void setupBeforeAll() {
    }

    @BeforeEach
    void setupBeforeEach() {
    }

    @AfterEach
    void teardownAfterEach() {
    }

    @AfterAll
    static void teardownAfterAll() {
    }
}
```

@output{initial test class}: now consider the invoke examples output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.