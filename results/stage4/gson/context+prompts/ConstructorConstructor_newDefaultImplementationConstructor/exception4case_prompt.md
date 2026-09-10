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
Here is the code of focal method, `newDefaultImplementationConstructor(Type, Class<? super T>)`, with the focal class `ConstructorConstructor`:
```Java
package com.google.gson.internal;
import java.util.Set;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.SortedMap;
import com.google.gson.reflect.TypeToken;
import java.util.LinkedHashMap;
import java.util.SortedSet;
import java.lang.reflect.Modifier;
import java.util.concurrent.ConcurrentHashMap;
import com.google.gson.JsonIOException;
import java.lang.reflect.ParameterizedType;
import java.util.TreeSet;
import java.util.EnumMap;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.ArrayDeque;
import java.util.concurrent.ConcurrentSkipListMap;
import com.google.gson.ReflectionAccessFilter;
import com.google.gson.internal.reflect.ReflectionHelper;
import java.util.EnumSet;
import java.lang.reflect.Constructor;
import java.util.concurrent.ConcurrentMap;
import java.util.TreeMap;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import com.google.gson.ReflectionAccessFilter.FilterResult;
import java.util.Collection;
import com.google.gson.InstanceCreator;
import java.util.Map;
public final class ConstructorConstructor {
    
    @SuppressWarnings("unchecked")
    private static <T> ObjectConstructor<T> newDefaultImplementationConstructor(final Type type, Class<? super T> rawType) {
        /* IMPORTANT: Must only create instances for classes with public no-args constructor.
        For classes with special constructors / factory methods (e.g. EnumSet)
        `newSpecialCollectionConstructor` defined above must be used, to avoid no-args
        constructor check (which is called before this method) detecting internal no-args
        constructors which might be added in a future JDK version
         */
        if (Collection.class.isAssignableFrom(rawType)) {
            if (SortedSet.class.isAssignableFrom(rawType)) {
                return new ObjectConstructor<T>() {
                    @Override
                    public T construct() {
                        return ((T) (new TreeSet<>()));
                    }
                };
            } else if (Set.class.isAssignableFrom(rawType)) {
                return new ObjectConstructor<T>() {
                    @Override
                    public T construct() {
                        return ((T) (new LinkedHashSet<>()));
                    }
                };
            } else if (Queue.class.isAssignableFrom(rawType)) {
                return new ObjectConstructor<T>() {
                    @Override
                    public T construct() {
                        return ((T) (new ArrayDeque<>()));
                    }
                };
            } else {
                return new ObjectConstructor<T>() {
                    @Override
                    public T construct() {
                        return ((T) (new ArrayList<>()));
                    }
                };
            }
        }
        if (Map.class.isAssignableFrom(rawType)) {
            if (ConcurrentNavigableMap.class.isAssignableFrom(rawType)) {
                return new ObjectConstructor<T>() {
                    @Override
                    public T construct() {
                        return ((T) (new ConcurrentSkipListMap<>()));
                    }
                };
            } else if (ConcurrentMap.class.isAssignableFrom(rawType)) {
                return new ObjectConstructor<T>() {
                    @Override
                    public T construct() {
                        return ((T) (new ConcurrentHashMap<>()));
                    }
                };
            } else if (SortedMap.class.isAssignableFrom(rawType)) {
                return new ObjectConstructor<T>() {
                    @Override
                    public T construct() {
                        return ((T) (new TreeMap<>()));
                    }
                };
            } else if ((type instanceof ParameterizedType) && (!String.class.isAssignableFrom(TypeToken.get(((ParameterizedType) (type)).getActualTypeArguments()[0]).getRawType()))) {
                return new ObjectConstructor<T>() {
                    @Override
                    public T construct() {
                        return ((T) (new LinkedHashMap<>()));
                    }
                };
            } else {
                return new ObjectConstructor<T>() {
                    @Override
                    public T construct() {
                        return ((T) (new LinkedTreeMap<>()));
                    }
                };
            }
        }
        return null;
    }
static String checkInstantiable(Class<?> c);
    public <T> ObjectConstructor<T> get(TypeToken<T> typeToken);
    @Override
public String toString();
}
```
@input{existing test case}
here's the existing test cases:
```json
<cases_json>
```

@output{test cases}: output the test cases in the JSON format: