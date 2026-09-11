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
Here is the code of focal method, `resolve(Type, Class<?>, Type, Map<TypeVariable<?>, Type>)`, with the focal class `$Gson$Types`:
```Java
package com.google.gson.internal;
import java.util.HashMap;
import static java.util.Objects.requireNonNull;
import static com.google.gson.internal.$Gson$Preconditions.checkArgument;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.util.Properties;
import java.lang.reflect.Modifier;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Map;
import java.io.Serializable;
import java.util.Arrays;
public final class $Gson$Types {
    static final Type[] EMPTY_TYPE_ARRAY = new Type[]{  };
    private static Type resolve(Type context, Class<?> contextRawType, Type toResolve, Map<TypeVariable<?>, Type> visitedTypeVariables) {
        // this implementation is made a little more complicated in an attempt to avoid object-creation
        TypeVariable<?> resolving = null;
        while (true) {
            if (toResolve instanceof TypeVariable) {
                TypeVariable<?> typeVariable = ((TypeVariable<?>) (toResolve));
                Type previouslyResolved = visitedTypeVariables.get(typeVariable);
                if (previouslyResolved != null) {
                    // cannot reduce due to infinite recursion
                    return previouslyResolved == Void.TYPE ? toResolve : previouslyResolved;
                }
                // Insert a placeholder to mark the fact that we are in the process of resolving this type
                visitedTypeVariables.put(typeVariable, Void.TYPE);
                if (resolving == null) {
                    resolving = typeVariable;
                }
                toResolve = resolveTypeVariable(context, contextRawType, typeVariable);
                if (toResolve == typeVariable) {
                    break;
                }
            } else if ((toResolve instanceof Class) && ((Class<?>) (toResolve)).isArray()) {
                Class<?> original = ((Class<?>) (toResolve));
                Type componentType = original.getComponentType();
                Type newComponentType = resolve(context, contextRawType, componentType, visitedTypeVariables);
                toResolve = (equal(componentType, newComponentType)) ? original : arrayOf(newComponentType);
                break;
            } else if (toResolve instanceof GenericArrayType) {
                GenericArrayType original = ((GenericArrayType) (toResolve));
                Type componentType = original.getGenericComponentType();
                Type newComponentType = resolve(context, contextRawType, componentType, visitedTypeVariables);
                toResolve = (equal(componentType, newComponentType)) ? original : arrayOf(newComponentType);
                break;
            } else if (toResolve instanceof ParameterizedType) {
                ParameterizedType original = ((ParameterizedType) (toResolve));
                Type ownerType = original.getOwnerType();
                Type newOwnerType = resolve(context, contextRawType, ownerType, visitedTypeVariables);
                boolean changed = !equal(newOwnerType, ownerType);
                Type[] args = original.getActualTypeArguments();
                for (int t = 0, length = args.length; t < length; t++) {
                    Type resolvedTypeArgument = resolve(context, contextRawType, args[t], visitedTypeVariables);
                    if (!equal(resolvedTypeArgument, args[t])) {
                        if (!changed) {
                            args = args.clone();
                            changed = true;
                        }
                        args[t] = resolvedTypeArgument;
                    }
                }
                toResolve = (changed) ? newParameterizedTypeWithOwner(newOwnerType, original.getRawType(), args) : original;
                break;
            } else if (toResolve instanceof WildcardType) {
                WildcardType original = ((WildcardType) (toResolve));
                Type[] originalLowerBound = original.getLowerBounds();
                Type[] originalUpperBound = original.getUpperBounds();
                if (originalLowerBound.length == 1) {
                    Type lowerBound = resolve(context, contextRawType, originalLowerBound[0], visitedTypeVariables);
                    if (lowerBound != originalLowerBound[0]) {
                        toResolve = supertypeOf(lowerBound);
                        break;
                    }
                } else if (originalUpperBound.length == 1) {
                    Type upperBound = resolve(context, contextRawType, originalUpperBound[0], visitedTypeVariables);
                    if (upperBound != originalUpperBound[0]) {
                        toResolve = subtypeOf(upperBound);
                        break;
                    }
                }
                toResolve = original;
                break;
            } else {
                break;
            }
        } 
        // ensure that any in-process resolution gets updated with the final result
        if (resolving != null) {
            visitedTypeVariables.put(resolving, toResolve);
        }
        return toResolve;
    }
public static GenericArrayType arrayOf(Type componentType);
    public static Type canonicalize(Type type);
    static void checkNotPrimitive(Type type);
    private static boolean equal(Object a, Object b);
    public static boolean equals(Type a, Type b);
    public static Type getArrayComponentType(Type array);
    public static Type getCollectionElementType(Type context, Class<?> contextRawType);
    public static Type[] getMapKeyAndValueTypes(Type context, Class<?> contextRawType);
    public static Class<?> getRawType(Type type);
    public static ParameterizedType newParameterizedTypeWithOwner(Type ownerType, Type rawType, Type... typeArguments);
    public static Type resolve(Type context, Class<?> contextRawType, Type toResolve);
    private static Type resolveTypeVariable(Type context, Class<?> contextRawType, TypeVariable<?> unknown);
    public static WildcardType subtypeOf(Type bound);
    public static WildcardType supertypeOf(Type bound);
    public static String typeToString(Type type);
}
```
@input{existing test case}
here's the existing test cases:
```json
<cases_json>
```

@output{test cases}: output the test cases in the JSON format: