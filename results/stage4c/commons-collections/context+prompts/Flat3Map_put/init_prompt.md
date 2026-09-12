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
Here is the code of focal method, `put(K, V)`, with the focal class `Flat3Map_put_Test`:
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
    private transient int hash1;
    private transient int hash2;
    private transient int hash3;
    private transient K key1;
    private transient K key2;
    private transient K key3;
    private transient V value1;
    private transient V value2;
    private transient V value3;
    private transient AbstractHashedMap<K, V> delegateMap;
    @Override
    public V put(final K key, final V value) {
        if (delegateMap != null) {
            return delegateMap.put(key, value);
        }
        // change existing mapping
        if (key == null) {
            switch (size) {
                // drop through
                case 3 :
                    if (key3 == null) {
                        final V old = value3;
                        value3 = value;
                        return old;
                    }
                case 2 :
                    if (key2 == null) {
                        final V old = value2;
                        value2 = value;
                        return old;
                    }
                case 1 :
                    if (key1 == null) {
                        final V old = value1;
                        value1 = value;
                        return old;
                    }
            }
        } else if (size > 0) {
            final int hashCode = key.hashCode();
            switch (size) {
                // drop through
                case 3 :
                    if ((hash3 == hashCode) && key.equals(key3)) {
                        final V old = value3;
                        value3 = value;
                        return old;
                    }
                case 2 :
                    if ((hash2 == hashCode) && key.equals(key2)) {
                        final V old = value2;
                        value2 = value;
                        return old;
                    }
                case 1 :
                    if ((hash1 == hashCode) && key.equals(key1)) {
                        final V old = value1;
                        value1 = value;
                        return old;
                    }
            }
        }
        // add new mapping
        switch (size) {
            default :
                convertToMap();
                delegateMap.put(key, value);
                return null;
            case 2 :
                hash3 = (key == null) ? 0 : key.hashCode();
                key3 = key;
                value3 = value;
                break;
            case 1 :
                hash2 = (key == null) ? 0 : key.hashCode();
                key2 = key;
                value2 = value;
                break;
            case 0 :
                hash1 = (key == null) ? 0 : key.hashCode();
                key1 = key;
                value1 = value;
                break;
        }
        size++;
        return null;
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
    private void convertToMap();
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
 

constructors for class `org.apache.commons.collections4.map.Flat3Map`: 
params: 
body:
```java
    public Flat3Map() {
    }

```
params: 
body:
```java
    public Flat3Map(final Map<? extends K, ? extends V> map) {
        putAll(map);
    }

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
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.collections4.BulkTest;
import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.iterators.AbstractMapIteratorTest;
import org.junit.jupiter.api.Test;

/**
 * JUnit tests.
 */
public class Flat3MapTest<K, V> extends AbstractIterableMapTest<K, V> {

    public class TestFlatMapIterator extends AbstractMapIteratorTest<K, V> {
        public TestFlatMapIterator() {
            super("TestFlatMapIterator");
        }

        @Override
        public V[] addSetValues() {
            return Flat3MapTest.this.getNewSampleValues();
        }

        @Override
        public Map<K, V> getConfirmedMap() {
            // assumes makeFullMapIterator() called first
            return Flat3MapTest.this.getConfirmed();
        }

        @Override
        public IterableMap<K, V> getMap() {
            // assumes makeFullMapIterator() called first
            return Flat3MapTest.this.getMap();
        }

        @Override
        public MapIterator<K, V> makeEmptyIterator() {
            resetEmpty();
            return Flat3MapTest.this.getMap().mapIterator();
        }

        @Override
        public MapIterator<K, V> makeObject() {
            resetFull();
            return Flat3MapTest.this.getMap().mapIterator();
        }

        @Override
        public boolean supportsRemove() {
            return Flat3MapTest.this.isRemoveSupported();
        }

        @Override
        public boolean supportsSetValue() {
            return Flat3MapTest.this.isSetValueSupported();
        }

        @Override
        public void verify() {
            super.verify();
            Flat3MapTest.this.verify();
        }
    }
    private static final Integer ONE = Integer.valueOf(1);
    private static final Integer TWO = Integer.valueOf(2);
    private static final Integer THREE = Integer.valueOf(3);
    private static final String TEN = "10";
......
    public void testSerialisation4() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        final ByteArrayOutputStream bout = new ByteArrayOutputStream();
        final ObjectOutputStream out = new ObjectOutputStream(bout);
        out.writeObject(map);
        final byte[] bytes = bout.toByteArray();
        out.close();
        final ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
        final ObjectInputStream in = new ObjectInputStream(bin);
        final Flat3Map<?, ?> ser = (Flat3Map<?, ?>) in.readObject();
        in.close();
        assertEquals(4, map.size());
        assertEquals(4, ser.size());
        assertTrue(ser.containsKey(ONE));
        assertTrue(ser.containsKey(TWO));
        assertTrue(ser.containsKey(TEN));
        assertTrue(ser.containsKey(TWENTY));
        assertEquals(TEN, ser.get(ONE));
        assertEquals(TWENTY, ser.get(TWO));
        assertEquals(ONE, ser.get(TEN));
        assertEquals(TWO, ser.get(TWENTY));
    }

    @Test
    public void testToString() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        final String string0 = m.toString();
        assertNotNull(string0);
        m.put( Integer.valueOf(1), Integer.valueOf(1) );
        final String string1 = m.toString();
        assertNotNull(string1);
        assertNotSame(string0, string1);
        m.put( Integer.valueOf(0), Integer.valueOf(0) );
        final String string2 = m.toString();
        assertNotNull(string2);
        assertNotSame(string0, string2);
        assertNotSame(string1, string2);
        m.put( Integer.valueOf(2), Integer.valueOf(2) );
        final String string3 = m.toString();
        assertNotNull(string3);
        assertNotSame(string0, string3);
        assertNotSame(string1, string3);
        assertNotSame(string2, string3);
    }
}
```

invoke examples of focal method: 
example 1:
```java
    @SuppressWarnings("unchecked")
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        final int count = in.readInt();
        if (count > 3) {
        for (int i = count; i > 0; i--) {
            put((K) in.readObject(), (V) in.readObject());
    }
```
example 2:
```java
    @Override
    public void putAll(final Map<? extends K, ? extends V> map) {
        final int size = map.size();
        if (size == 0) {
        if (delegateMap != null) {
        if (size < 4) {
            for (final Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
                put(entry.getKey(), entry.getValue());
    }
```



@input{test class template}
```java
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
// Add other imports if necessary

@ExtendWith(MockitoExtension.class)
class Flat3Map_put_Test {
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