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
@input{existing test case}
here's the existing test cases:
```json
<cases_json>
```

@output{test cases}: output the test cases in the JSON format: