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
Here is the code of focal method, `containsValue(Object)`, with the focal class `Flat3Map`:
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
    private transient V value1;
    private transient V value2;
    private transient V value3;
    private transient AbstractHashedMap<K, V> delegateMap;
    @Override
    public boolean containsValue(final Object value) {
        if (delegateMap != null) {
            return delegateMap.containsValue(value);
        }
        if (value == null) {
            // drop through
            switch (size) {
                case 3 :
                    if (value3 == null) {
                        return true;
                    }
                case 2 :
                    if (value2 == null) {
                        return true;
                    }
                case 1 :
                    if (value1 == null) {
                        return true;
                    }
            }
        } else {
            switch (size) {
                // drop through
                case 3 :
                    if (value.equals(value3)) {
                        return true;
                    }
                case 2 :
                    if (value.equals(value2)) {
                        return true;
                    }
                case 1 :
                    if (value.equals(value1)) {
                        return true;
                    }
            }
        }
        return false;
    }
@Override
public void clear();
    @Override
@SuppressWarnings("unchecked")
public Flat3Map<K, V> clone();
    @Override
public boolean containsKey(final Object key);
    protected AbstractHashedMap<K, V> createDelegateMap();
    @Override
public Set<Map.Entry<K, V>> entrySet();
    @Override
public boolean equals(final Object obj);
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
 

api document of method containsValue(Object): 

     * Checks whether the map contains the specified value.
     *
     * @param value  the value to search for
     * @return true if the map contains the key
     

return type: 
boolean

dependent classes: 
class `org.apache.commons.collections4.map.Flat3Map`:
	dependent fields:
		int org.apache.commons.collections4.map.Flat3Map.size;
		V org.apache.commons.collections4.map.Flat3Map.value1;
		org.apache.commons.collections4.map.AbstractHashedMap<K, V> org.apache.commons.collections4.map.Flat3Map.delegateMap;
		V org.apache.commons.collections4.map.Flat3Map.value2;
		V org.apache.commons.collections4.map.Flat3Map.value3;
	related functions:
		method `public V put(K, V)` returns `V`, related with `org.apache.commons.collections4.map.Flat3Map.containsValue(Object)`, api document: 
     * Puts a key-value mapping into this map.
     *
     * @param key  the key to add
     * @param value  the value to add
     * @return the value previously mapped to this key, null if none
     ;
		method `public V get(Object)` returns `V`, related with `org.apache.commons.collections4.map.Flat3Map.containsValue(Object)`, api document: 
     * Gets the value mapped to the key specified.
     *
     * @param key  the key
     * @return the mapped value, null if no match
     ;
class `org.apache.commons.collections4.ArrayUtils`:
	related functions:
		method ` static int indexOf(Object[], Object, int)` returns `int`, related with `org.apache.commons.collections4.map.Flat3Map.containsValue(Object)`, api document: 
     * <p>
     * Finds the index of the given object in the array starting at the given index.
     * </p>
     * <p>
     * This method returns {@link CollectionUtils#INDEX_NOT_FOUND} ({@code -1}) for a {@code null} input array.
     * </p>
     * <p>
     * A negative startIndex is treated as zero. A startIndex larger than the array length will return {@link CollectionUtils#INDEX_NOT_FOUND} ({@code -1}).
     * </p>
     *
     * @param array        the array to search through for the object, may be {@code null}
     * @param objectToFind the object to find, may be {@code null}
     * @param startIndex   the index to start searching at
     * @return the index of the object within the array starting at the index, {@link CollectionUtils#INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null}
     *         array input
     ;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.