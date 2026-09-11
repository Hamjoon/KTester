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
Here is the code of focal method, `getBoundFields(Gson, TypeToken<?>, Class<?>, boolean, boolean)`, with the focal class `ReflectiveTypeAdapterFactory`:
```Java
package com.google.gson.internal.bind;
import com.google.gson.internal.ReflectionAccessFilterHelper;
import com.google.gson.annotations.JsonAdapter;
import java.util.HashMap;
import java.util.ArrayList;
import com.google.gson.reflect.TypeToken;
import com.google.gson.internal.Excluder;
import java.util.LinkedHashMap;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.JsonParseException;
import com.google.gson.Gson;
import com.google.gson.internal.ConstructorConstructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import com.google.gson.JsonIOException;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.stream.JsonToken;
import java.lang.reflect.AccessibleObject;
import java.util.List;
import java.lang.reflect.Member;
import com.google.gson.internal.Primitives;
import com.google.gson.stream.JsonReader;
import com.google.gson.internal.ObjectConstructor;
import java.util.Collections;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.ReflectionAccessFilter;
import com.google.gson.internal.reflect.ReflectionHelper;
import java.lang.reflect.Constructor;
import java.io.IOException;
import java.lang.reflect.Method;
import com.google.gson.stream.JsonWriter;
import java.lang.reflect.InvocationTargetException;
import com.google.gson.JsonSyntaxException;
import java.lang.reflect.Type;
import com.google.gson.ReflectionAccessFilter.FilterResult;
import com.google.gson.TypeAdapter;
import java.util.Map;
import java.util.Arrays;
public final class ReflectiveTypeAdapterFactory implements TypeAdapterFactory {
    private final List<ReflectionAccessFilter> reflectionFilters;
        static abstract class BoundField {
        final String name;
    
        final Field field;
    
        /**
         * Name of the underlying field
         */
        final String fieldName;
    
        final boolean serialized;
    
        final boolean deserialized;
    
        protected BoundField(String name, Field field, boolean serialized, boolean deserialized) {
            this.name = name;
            this.field = field;
            this.fieldName = field.getName();
            this.serialized = serialized;
            this.deserialized = deserialized;
        }
    
        /**
         * Read this field value from the source, and append its JSON value to the writer
         */
        abstract void write(JsonWriter writer, Object source) throws IOException, IllegalAccessException;
    
        /**
         * Read the value into the target array, used to provide constructor arguments for records
         */
        abstract void readIntoArray(JsonReader reader, int index, Object[] target) throws IOException, JsonParseException;
    
        /**
         * Read the value from the reader, and set it on the corresponding field on target via reflection
         */
        abstract void readIntoField(JsonReader reader, Object target) throws IOException, IllegalAccessException;
    }
private Map<String, BoundField> getBoundFields(Gson context, TypeToken<?> type, Class<?> raw, boolean blockInaccessible, boolean isRecord) {
        Map<String, BoundField> result = new LinkedHashMap<>();
        if (raw.isInterface()) {
            return result;
        }
        Class<?> originalRaw = raw;
        while (raw != Object.class) {
            Field[] fields = raw.getDeclaredFields();
            // For inherited fields, check if access to their declaring class is allowed
            if ((raw != originalRaw) && (fields.length > 0)) {
                FilterResult filterResult = ReflectionAccessFilterHelper.getFilterResult(reflectionFilters, raw);
                if (filterResult == FilterResult.BLOCK_ALL) {
                    throw new JsonIOException((((("ReflectionAccessFilter does not permit using reflection for " + raw) + " (supertype of ") + originalRaw) + "). Register a TypeAdapter for this type") + " or adjust the access filter.");
                }
                blockInaccessible = filterResult == FilterResult.BLOCK_INACCESSIBLE;
            }
            for (Field field : fields) {
                boolean serialize = includeField(field, true);
                boolean deserialize = includeField(field, false);
                if ((!serialize) && (!deserialize)) {
                    continue;
                }
                // The accessor method is only used for records. If the type is a record, we will read out values
                // via its accessor method instead of via reflection. This way we will bypass the accessible restrictions
                Method accessor = null;
                if (isRecord) {
                    // If there is a static field on a record, there will not be an accessor. Instead we will use the default
                    // field serialization logic, but for deserialization the field is excluded for simplicity. Note that Gson
                    // ignores static fields by default, but GsonBuilder.excludeFieldsWithModifiers can overwrite this.
                    if (Modifier.isStatic(field.getModifiers())) {
                        deserialize = false;
                    } else {
                        accessor = ReflectionHelper.getAccessor(raw, field);
                        // If blockInaccessible, skip and perform access check later
                        if (!blockInaccessible) {
                            ReflectionHelper.makeAccessible(accessor);
                        }
                        // @SerializedName can be placed on accessor method, but it is not supported there
                        // If field and method have annotation it is not easily possible to determine if accessor method
                        // is implicit and has inherited annotation, or if it is explicitly declared with custom annotation
                        if ((accessor.getAnnotation(SerializedName.class) != null) && (field.getAnnotation(SerializedName.class) == null)) {
                            String methodDescription = ReflectionHelper.getAccessibleObjectDescription(accessor, false);
                            throw new JsonIOException(("@SerializedName on " + methodDescription) + " is not supported");
                        }
                    }
                }
                // If blockInaccessible, skip and perform access check later
                // For Records if the accessor method is used the field does not have to be made accessible
                if ((!blockInaccessible) && (accessor == null)) {
                    ReflectionHelper.makeAccessible(field);
                }
                Type fieldType = $Gson$Types.resolve(type.getType(), raw, field.getGenericType());
                List<String> fieldNames = getFieldNames(field);
                BoundField previous = null;
                for (int i = 0, size = fieldNames.size(); i < size; ++i) {
                    String name = fieldNames.get(i);
                    if (i != 0)
                        serialize = false;
                    // only serialize the default name
    
                    BoundField boundField = createBoundField(context, field, accessor, name, TypeToken.get(fieldType), serialize, deserialize, blockInaccessible);
                    BoundField replaced = result.put(name, boundField);
                    if (previous == null)
                        previous = replaced;
    
                }
                if (previous != null) {
                    throw new IllegalArgumentException(((((((("Class " + originalRaw.getName()) + " declares multiple JSON fields named '") + previous.name) + "'; conflict is caused") + " by fields ") + ReflectionHelper.fieldToString(previous.field)) + " and ") + ReflectionHelper.fieldToString(field));
                }
            }
            type = TypeToken.get($Gson$Types.resolve(type.getType(), raw, raw.getGenericSuperclass()));
            raw = type.getRawType();
        } 
        return result;
    }
@Override
public <T> TypeAdapter<T> create(Gson gson, final TypeToken<T> type);
    private BoundField createBoundField(final Gson context, final Field field, final Method accessor, final String name, final TypeToken<?> fieldType, boolean serialize, boolean deserialize, final boolean blockInaccessible);
    private List<String> getFieldNames(Field f);
    private boolean includeField(Field f, boolean serialize);
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

api document of class com.google.gson.internal.bind.ReflectiveTypeAdapterFactory: 

 * Type adapter that reflects over the fields and methods of a class.
 

dependent classes: 
class `com.google.gson.Gson`:
	
class `com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.BoundField`:
	dependent fields:
		java.lang.String com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.BoundField.name;
		java.lang.reflect.Field com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.BoundField.field;
	
class `com.google.gson.ReflectionAccessFilter.FilterResult`:
	dependent fields:
		com.google.gson.ReflectionAccessFilter.FilterResult com.google.gson.ReflectionAccessFilter.FilterResult.BLOCK_ALL;
		com.google.gson.ReflectionAccessFilter.FilterResult com.google.gson.ReflectionAccessFilter.FilterResult.BLOCK_INACCESSIBLE;
	
class `com.google.gson.internal.bind.ReflectiveTypeAdapterFactory`:
	dependent fields:
		java.util.List<com.google.gson.ReflectionAccessFilter> com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.reflectionFilters;
	related functions:
		method `private List<String> getFieldNames(Field)` returns `java.util.List<java.lang.String>`, related with `com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.getBoundFields(Gson, TypeToken<?>, Class<?>, boolean, boolean)`, api document:  first element holds the default name ;
		method `private BoundField createBoundField(Gson, Field, Method, String, TypeToken<?>, boolean, boolean, boolean)` returns `com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.BoundField`, related with `com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.getBoundFields(Gson, TypeToken<?>, Class<?>, boolean, boolean)`;
		method `public TypeAdapter<T> create(Gson, TypeToken<T>)` returns `com.google.gson.TypeAdapter<T>`, related with `com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.getBoundFields(Gson, TypeToken<?>, Class<?>, boolean, boolean)`;
class `com.google.gson.internal.$Gson$Types`:
	related functions:
		method `private static Type getSupertype(Type, Class<?>, Class<?>)` returns `java.lang.reflect.Type`, related with `com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.getBoundFields(Gson, TypeToken<?>, Class<?>, boolean, boolean)`, api document: 
   * Returns the generic form of {@code supertype}. For example, if this is {@code
   * ArrayList<String>}, this returns {@code Iterable<String>} given the input {@code
   * Iterable.class}.
   *
   * @param supertype a superclass of, or interface implemented by, this.
   ;
		method `public static Class<?> getRawType(Type)` returns `java.lang.Class<?>`, related with `com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.getBoundFields(Gson, TypeToken<?>, Class<?>, boolean, boolean)`;
		method `private static Type getGenericSupertype(Type, Class<?>, Class<?>)` returns `java.lang.reflect.Type`, related with `com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.getBoundFields(Gson, TypeToken<?>, Class<?>, boolean, boolean)`, api document: 
   * Returns the generic supertype for {@code supertype}. For example, given a class {@code
   * IntegerSet}, the result for when supertype is {@code Set.class} is {@code Set<Integer>} and the
   * result when the supertype is {@code Collection.class} is {@code Collection<Integer>}.
   ;
class `com.google.gson.reflect.TypeToken`:
	related functions:
		method `private static boolean isAssignableFrom(Type, ParameterizedType, Map<String, Type>)` returns `boolean`, related with `com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.getBoundFields(Gson, TypeToken<?>, Class<?>, boolean, boolean)`, api document: 
   * Private recursive helper function to actually do the type-safe checking
   * of assignability.
   ;
class `com.google.gson.internal.ConstructorConstructor`:
	related functions:
		method `private static ObjectConstructor<T> newDefaultConstructor(Class<? super T>, FilterResult)` returns `com.google.gson.internal.ObjectConstructor<T>`, related with `com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.getBoundFields(Gson, TypeToken<?>, Class<?>, boolean, boolean)`;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.