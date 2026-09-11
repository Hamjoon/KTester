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
Here is the code of focal method, `getIterator(Object)`, with the focal class `IteratorUtils`:
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

api document of class org.apache.commons.collections4.IteratorUtils: 

 * Provides static utility methods and decorators for {@link Iterator}
 * instances. The implementations are provided in the iterators subpackage.
 *
 * @since 2.1
 

api document of method getIterator(Object): 

     * Gets a suitable Iterator for the given object.
     * <p>
     * This method can handle objects as follows
     * <ul>
     * <li>null - empty iterator
     * <li>Iterator - returned directly
     * <li>Enumeration - wrapped
     * <li>Collection - iterator from collection returned
     * <li>Map - values iterator returned
     * <li>Dictionary - values (elements) enumeration returned as iterator
     * <li>array - iterator over array returned
     * <li>object with iterator() public method accessed by reflection
     * <li>object - singleton iterator
     * <li>NodeList - iterator over the list
     * <li>Node - iterator over the child nodes
     * </ul>
     *
     * @param obj  the object to convert to an iterator
     * @return a suitable iterator, never null
     

dependent classes: 
class `org.apache.commons.collections4.IteratorUtils`:
	related functions:
		method `public static Iterator<?> getIterator(Object)` returns `java.util.Iterator<?>`, related with `org.apache.commons.collections4.IteratorUtils.getIterator(Object)`, api document: 
     * Gets a suitable Iterator for the given object.
     * <p>
     * This method can handle objects as follows
     * <ul>
     * <li>null - empty iterator
     * <li>Iterator - returned directly
     * <li>Enumeration - wrapped
     * <li>Collection - iterator from collection returned
     * <li>Map - values iterator returned
     * <li>Dictionary - values (elements) enumeration returned as iterator
     * <li>array - iterator over array returned
     * <li>object with iterator() public method accessed by reflection
     * <li>object - singleton iterator
     * <li>NodeList - iterator over the list
     * <li>Node - iterator over the child nodes
     * </ul>
     *
     * @param obj  the object to convert to an iterator
     * @return a suitable iterator, never null
     ;
class `org.apache.commons.collections4.functors.InvokerTransformer`:
	related functions:
		method `public O transform(Object)` returns `O`, related with `org.apache.commons.collections4.IteratorUtils.getIterator(Object)`, api document: 
     * Transforms the input to result by invoking a method on the input.
     *
     * @param input  the input object to transform
     * @return the transformed result, null if null input
     ;
class `org.apache.commons.collections4.IterableUtils`:
	related functions:
		method `private static Iterator<E> emptyIteratorIfNull(Iterable<E>)` returns `java.util.Iterator<E>`, related with `org.apache.commons.collections4.IteratorUtils.getIterator(Object)`, api document: 
     * Returns an empty iterator if the argument is {@code null},
     * or {@code iterable.iterator()} otherwise.
     *
     * @param <E> the element type
     * @param iterable  the iterable, possibly {@code null}
     * @return an empty iterator if the argument is {@code null}
     ;
class `org.apache.commons.collections4.functors.PrototypeFactory.PrototypeCloneFactory`:
	related functions:
		method `private void findCloneMethod()` returns `void`, related with `org.apache.commons.collections4.IteratorUtils.getIterator(Object)`, api document: 
         * Find the Clone method for the class specified.
         ;
class `org.apache.commons.collections4.functors.PrototypeFactory`:
	related functions:
		method `public static Factory<T> prototypeFactory(T)` returns `org.apache.commons.collections4.Factory<T>`, related with `org.apache.commons.collections4.IteratorUtils.getIterator(Object)`, api document: 
     * Factory method that performs validation.
     * <p>
     * Creates a Factory that will return a clone of the same prototype object
     * each time the factory is used. The prototype will be cloned using one of these
     * techniques (in order):
     * </p>
     *
     * <ul>
     * <li>public clone method</li>
     * <li>public copy constructor</li>
     * <li>serialization clone</li>
     * </ul>
     *
     * @param <T>  the type the factory creates
     * @param prototype  the object to clone each time in the factory
     * @return the {@code prototype} factory, or a {@link ConstantFactory#NULL_INSTANCE} if
     * the {@code prototype} is {@code null}
     * @throws IllegalArgumentException if the prototype cannot be cloned
     ;
class `org.apache.commons.collections4.FluentIterable`:
	related functions:
		method `public Iterator<E> iterator()` returns `java.util.Iterator<E>`, related with `org.apache.commons.collections4.IteratorUtils.getIterator(Object)`, api document:  {@inheritDoc} ;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.