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
Here is the code of focal method, `equals(Object)`, with the focal class `Flat3Map`:
```Java
package org.apache.commons.collections4.map;
import org.apache.commons.collections4.iterators.EmptyMapIterator;
import java.util.Set;
import org.apache.commons.collections4.iterators.EmptyIterator;
import java.io.IOException;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import org.apache.commons.collections4.ResettableIterator;
import java.io.ObjectOutputStream;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.IterableMap;
import java.io.ObjectInputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Map;
import java.io.Serializable;
public class Flat3Map<K, V> implements IterableMap<K, V> , Serializable , Cloneable {
    private transient int size;
    private transient K key1;
    private transient K key2;
    private transient K key3;
    private transient V value1;
    private transient V value2;
    private transient V value3;
    private transient AbstractHashedMap<K, V> delegateMap;
    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (delegateMap != null) {
            return delegateMap.equals(obj);
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        final Map<?, ?> other = ((Map<?, ?>) (obj));
        if (size != other.size()) {
            return false;
        }
        if (size > 0) {
            Object otherValue = null;
            switch (size) {
                // drop through
                case 3 :
                    if (!other.containsKey(key3)) {
                        return false;
                    }
                    otherValue = other.get(key3);
                    if (!Objects.equals(value3, otherValue)) {
                        return false;
                    }
                case 2 :
                    if (!other.containsKey(key2)) {
                        return false;
                    }
                    otherValue = other.get(key2);
                    if (!Objects.equals(value2, otherValue)) {
                        return false;
                    }
                case 1 :
                    if (!other.containsKey(key1)) {
                        return false;
                    }
                    otherValue = other.get(key1);
                    if (!Objects.equals(value1, otherValue)) {
                        return false;
                    }
            }
        }
        return true;
    }
@Override
public void clear();
    @Override
@SuppressWarnings("unchecked")
public Flat3Map<K, V> clone();
    @Override
public boolean containsKey(final Object key);
    @Override
public boolean containsValue(final Object value);
    protected AbstractHashedMap<K, V> createDelegateMap();
    @Override
public Set<Map.Entry<K, V>> entrySet();
    @Override
public V get(final Object key);
    @Override
public int hashCode();
    @Override
public boolean isEmpty();
    @Override
public Set<K> keySet();
    @Override
public MapIterator<K, V> mapIterator();
    @Override
public V put(final K key, final V value);
    @Override
public void putAll(final Map<? extends K, ? extends V> map);
    @Override
public V remove(final Object key);
    @Override
public int size();
    @Override
public String toString();
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

api document of class org.apache.commons.collections4.map.Flat3Map: 

 * A {@code Map} implementation that stores data in simple fields until
 * the size is greater than 3.
 * <p>
 * This map is designed for performance and can outstrip HashMap.
 * It also has good garbage collection characteristics.
 * </p>
 * <ul>
 * <li>Optimised for operation at size 3 or less.
 * <li>Still works well once size 3 exceeded.
 * <li>Gets at size 3 or less are about 0-10% faster than HashMap,
 * <li>Puts at size 3 or less are over 4 times faster than HashMap.
 * <li>Performance 5% slower than HashMap once size 3 exceeded once.
 * </ul>
 * <p>
 * The design uses two distinct modes of operation - flat and delegate.
 * While the map is size 3 or less, operations map straight onto fields using
 * switch statements. Once size 4 is reached, the map switches to delegate mode
 * and only switches back when cleared. In delegate mode, all operations are
 * forwarded straight to a HashMap resulting in the 5% performance loss.
 * </p>
 * <p>
 * The performance gains on puts are due to not needing to create a Map Entry
 * object. This is a large saving not only in performance but in garbage collection.
 * </p>
 * <p>
 * Whilst in flat mode this map is also easy for the garbage collector to dispatch.
 * This is because it contains no complex objects or arrays which slow the progress.
 * </p>
 * <p>
 * Do not use {@code Flat3Map} if the size is likely to grow beyond 3.
 * </p>
 * <p>
 * <strong>Note that Flat3Map is not synchronized and is not thread-safe.</strong>
 * If you wish to use this map from multiple threads concurrently, you must use
 * appropriate synchronization. The simplest approach is to wrap this map
 * using {@link java.util.Collections#synchronizedMap(Map)}. This class may throw
 * exceptions when accessed by concurrent threads without synchronization.
 * </p>
 *
 * @param <K> the type of the keys in this map
 * @param <V> the type of the values in this map
 * @since 3.0
 

api document of method equals(Object): 

     * Compares this map with another.
     *
     * @param obj  the object to compare to
     * @return true if equal
     

return type: 
boolean

dependent classes: 
class `org.apache.commons.collections4.map.Flat3Map`:
	dependent fields:
		int org.apache.commons.collections4.map.Flat3Map.size;
		V org.apache.commons.collections4.map.Flat3Map.value1;
		K org.apache.commons.collections4.map.Flat3Map.key2;
		K org.apache.commons.collections4.map.Flat3Map.key1;
		org.apache.commons.collections4.map.AbstractHashedMap<K, V> org.apache.commons.collections4.map.Flat3Map.delegateMap;
		V org.apache.commons.collections4.map.Flat3Map.value2;
		K org.apache.commons.collections4.map.Flat3Map.key3;
		V org.apache.commons.collections4.map.Flat3Map.value3;
	related functions:
		method `public void clear()` returns `void`, related with `org.apache.commons.collections4.map.Flat3Map.equals(Object)`, api document: 
     * Clears the map, resetting the size to zero and nullifying references
     * to avoid garbage collection issues.
     ;
		method `public String toString()` returns `java.lang.String`, related with `org.apache.commons.collections4.map.Flat3Map.equals(Object)`, api document: 
     * Gets the map as a String.
     *
     * @return a string version of the map
     ;
		method `public boolean containsValue(Object)` returns `boolean`, related with `org.apache.commons.collections4.map.Flat3Map.equals(Object)`, api document: 
     * Checks whether the map contains the specified value.
     *
     * @param value  the value to search for
     * @return true if the map contains the key
     ;
class `org.apache.commons.collections4.map.CompositeMap`:
	related functions:
		method `public V get(Object)` returns `V`, related with `org.apache.commons.collections4.map.Flat3Map.equals(Object)`, api document: 
     * Returns the value to which this map maps the specified key.  Returns
     * {@code null} if the map contains no mapping for this key.  A return
     * value of {@code null} does not <i>necessarily</i> indicate that the
     * map contains no mapping for the key; it's also possible that the map
     * explicitly maps the key to {@code null}.  The {@code containsKey}
     * operation may be used to distinguish these two cases.
     *
     * <p>More formally, if this map contains a mapping from a key
     * {@code k} to a value {@code v} such that <code>(key==null ? k==null :
     * key.equals(k))</code>, then this method returns {@code v}; otherwise
     * it returns {@code null}.  (There can be at most one such mapping.)
     *
     * @param key key whose associated value is to be returned.
     * @return the value to which this map maps the specified key, or
     *         {@code null} if the map contains no mapping for this key.
     *
     * @throws ClassCastException if the key is of an inappropriate type for
     *         this map (optional).
     * @throws NullPointerException key is {@code null} and this map does
     *         not permit {@code null} keys (optional).
     *
     * @see #containsKey(Object)
     ;
class `org.apache.commons.collections4.map.DefaultedMap`:
	related functions:
		method `public V get(Object)` returns `V`, related with `org.apache.commons.collections4.map.Flat3Map.equals(Object)`;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.