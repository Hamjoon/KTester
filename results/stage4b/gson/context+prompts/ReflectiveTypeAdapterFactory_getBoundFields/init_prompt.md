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
Here is the code of focal method, `getBoundFields(Gson, TypeToken<?>, Class<?>, boolean, boolean)`, with the focal class `ReflectiveTypeAdapterFactory_getBoundFields_Test`:
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


@input{context information}

api document of class com.google.gson.internal.bind.ReflectiveTypeAdapterFactory: 

 * Type adapter that reflects over the fields and methods of a class.
 

constructors for class `com.google.gson.internal.bind.ReflectiveTypeAdapterFactory`: 
params: com.google.gson.internal.ConstructorConstructor constructorConstructor 
com.google.gson.FieldNamingStrategy fieldNamingPolicy 
com.google.gson.internal.Excluder excluder 
com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory 
body:
```java
  public ReflectiveTypeAdapterFactory(ConstructorConstructor constructorConstructor,
      FieldNamingStrategy fieldNamingPolicy, Excluder excluder,
      JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory,
      List<ReflectionAccessFilter> reflectionFilters) {
    this.constructorConstructor = constructorConstructor;
    this.fieldNamingPolicy = fieldNamingPolicy;
    this.excluder = excluder;
    this.jsonAdapterFactory = jsonAdapterFactory;
    this.reflectionFilters = reflectionFilters;
  }

```

parameters in constructors and focal method: 
class `com.google.gson.internal.ConstructorConstructor`:
api document : 
 * Returns a function that can construct an instance of a requested type.
 
constructor:
```java
  public ConstructorConstructor(Map<Type, InstanceCreator<?>> instanceCreators, boolean useJdkUnsafe, List<ReflectionAccessFilter> reflectionFilters) {
    this.instanceCreators = instanceCreators;
    this.useJdkUnsafe = useJdkUnsafe;
    this.reflectionFilters = reflectionFilters;
  }

```
class `com.google.gson.FieldNamingStrategy`:
api document : 
 * A mechanism for providing custom field naming in Gson. This allows the client code to translate
 * field names into a particular convention that is not supported as a normal Java field
 * declaration rules. For example, Java does not support "-" characters in a field name.
 *
 * @author Inderjeet Singh
 * @author Joel Leitch
 * @since 1.3
 
constructor:
```java
```
class `com.google.gson.internal.Excluder`:
api document : 
 * This class selects which fields and types to omit. It is configurable,
 * supporting version attributes {@link Since} and {@link Until}, modifiers,
 * synthetic fields, anonymous and local classes, inner classes, and fields with
 * the {@link Expose} annotation.
 *
 * <p>This class is a type adapter factory; types that are excluded will be
 * adapted to null. It may delegate to another type adapter if only one
 * direction is excluded.
 *
 * @author Joel Leitch
 * @author Jesse Wilson
 
constructor:
```java
```
class `com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory`:
api document : 
 * Given a type T, looks for the annotation {@link JsonAdapter} and uses an instance of the
 * specified class as the default type adapter.
 *
 * @since 2.3
 
constructor:
```java
  public JsonAdapterAnnotationTypeAdapterFactory(ConstructorConstructor constructorConstructor) {
    this.constructorConstructor = constructorConstructor;
  }

```
class `com.google.gson.Gson`:
api document : 
 * This is the main class for using Gson. Gson is typically used by first constructing a
 * Gson instance and then invoking {@link #toJson(Object)} or {@link #fromJson(String, Class)}
 * methods on it. Gson instances are Thread-safe so you can reuse them freely across multiple
 * threads.
 *
 * <p>You can create a Gson instance by invoking {@code new Gson()} if the default configuration
 * is all you need. You can also use {@link GsonBuilder} to build a Gson instance with various
 * configuration options such as versioning support, pretty printing, custom
 * {@link JsonSerializer}s, {@link JsonDeserializer}s, and {@link InstanceCreator}s.</p>
 *
 * <p>Here is an example of how Gson is used for a simple Class:
 *
 * <pre>
 * Gson gson = new Gson(); // Or use new GsonBuilder().create();
 * MyType target = new MyType();
 * String json = gson.toJson(target); // serializes target to JSON
 * MyType target2 = gson.fromJson(json, MyType.class); // deserializes json into target2
 * </pre>
 *
 * <p>If the type of the object that you are converting is a {@code ParameterizedType}
 * (i.e. has at least one type argument, for example {@code List<MyType>}) then for
 * deserialization you must use a {@code fromJson} method with {@link Type} or {@link TypeToken}
 * parameter to specify the parameterized type. For serialization specifying a {@code Type}
 * or {@code TypeToken} is optional, otherwise Gson will use the runtime type of the object.
 * {@link TypeToken} is a class provided by Gson which helps creating parameterized types.
 * Here is an example showing how this can be done:
 * <pre>
 * TypeToken&lt;List&lt;MyType&gt;&gt; listType = new TypeToken&lt;List&lt;MyType&gt;&gt;() {};
 * List&lt;MyType&gt; target = new LinkedList&lt;MyType&gt;();
 * target.add(new MyType(1, "abc"));
 *
 * Gson gson = new Gson();
 * // For serialization you normally do not have to specify the type, Gson will use
 * // the runtime type of the objects, however you can also specify it explicitly
 * String json = gson.toJson(target, listType.getType());
 *
 * // But for deserialization you have to specify the type
 * List&lt;MyType&gt; target2 = gson.fromJson(json, listType);
 * </pre>
 *
 * <p>See the <a href="https://github.com/google/gson/blob/master/UserGuide.md">Gson User Guide</a>
 * for a more complete set of examples.</p>
 *
 * <h2>Lenient JSON handling</h2>
 * For legacy reasons most of the {@code Gson} methods allow JSON data which does not
 * comply with the JSON specification, regardless of whether {@link GsonBuilder#setLenient()}
 * is used or not. If this behavior is not desired, the following workarounds can be used:
 *
 * <h3>Serialization</h3>
 * <ol>
 *   <li>Use {@link #getAdapter(Class)} to obtain the adapter for the type to be serialized
 *   <li>When using an existing {@code JsonWriter}, manually apply the writer settings of this
 *       {@code Gson} instance listed by {@link #newJsonWriter(Writer)}.<br>
 *       Otherwise, when not using an existing {@code JsonWriter}, use {@link #newJsonWriter(Writer)}
 *       to construct one.
 *   <li>Call {@link TypeAdapter#write(JsonWriter, Object)}
 * </ol>
 *
 * <h3>Deserialization</h3>
 * <ol>
 *   <li>Use {@link #getAdapter(Class)} to obtain the adapter for the type to be deserialized
 *   <li>When using an existing {@code JsonReader}, manually apply the reader settings of this
 *       {@code Gson} instance listed by {@link #newJsonReader(Reader)}.<br>
 *       Otherwise, when not using an existing {@code JsonReader}, use {@link #newJsonReader(Reader)}
 *       to construct one.
 *   <li>Call {@link TypeAdapter#read(JsonReader)}
 *   <li>Call {@link JsonReader#peek()} and verify that the result is {@link JsonToken#END_DOCUMENT}
 *       to make sure there is no trailing data
 * </ol>
 *
 * @see TypeToken
 *
 * @author Inderjeet Singh
 * @author Joel Leitch
 * @author Jesse Wilson
 
constructor:
```java
  public Gson() {
    this(Excluder.DEFAULT, DEFAULT_FIELD_NAMING_STRATEGY,
        Collections.<Type, InstanceCreator<?>>emptyMap(), DEFAULT_SERIALIZE_NULLS,
        DEFAULT_COMPLEX_MAP_KEYS, DEFAULT_JSON_NON_EXECUTABLE, DEFAULT_ESCAPE_HTML,
        DEFAULT_PRETTY_PRINT, DEFAULT_LENIENT, DEFAULT_SPECIALIZE_FLOAT_VALUES,
        DEFAULT_USE_JDK_UNSAFE,
        LongSerializationPolicy.DEFAULT, DEFAULT_DATE_PATTERN, DateFormat.DEFAULT, DateFormat.DEFAULT,
        Collections.<TypeAdapterFactory>emptyList(), Collections.<TypeAdapterFactory>emptyList(),
        Collections.<TypeAdapterFactory>emptyList(), DEFAULT_OBJECT_TO_NUMBER_STRATEGY, DEFAULT_NUMBER_TO_NUMBER_STRATEGY,
        Collections.<ReflectionAccessFilter>emptyList());
  }

  Gson(Excluder excluder, FieldNamingStrategy fieldNamingStrategy,
      Map<Type, InstanceCreator<?>> instanceCreators, boolean serializeNulls,
      boolean complexMapKeySerialization, boolean generateNonExecutableGson, boolean htmlSafe,
      boolean prettyPrinting, boolean lenient, boolean serializeSpecialFloatingPointValues,
      boolean useJdkUnsafe,
      LongSerializationPolicy longSerializationPolicy, String datePattern, int dateStyle,
      int timeStyle, List<TypeAdapterFactory> builderFactories,
      List<TypeAdapterFactory> builderHierarchyFactories,
      List<TypeAdapterFactory> factoriesToBeAdded,
      ToNumberStrategy objectToNumberStrategy, ToNumberStrategy numberToNumberStrategy,
      List<ReflectionAccessFilter> reflectionFilters) {
    this.excluder = excluder;
    this.fieldNamingStrategy = fieldNamingStrategy;
    this.instanceCreators = instanceCreators;
    this.constructorConstructor = new ConstructorConstructor(instanceCreators, useJdkUnsafe, reflectionFilters);
    this.serializeNulls = serializeNulls;
    this.complexMapKeySerialization = complexMapKeySerialization;
    this.generateNonExecutableJson = generateNonExecutableGson;
    this.htmlSafe = htmlSafe;
    this.prettyPrinting = prettyPrinting;
    this.lenient = lenient;
    this.serializeSpecialFloatingPointValues = serializeSpecialFloatingPointValues;
    this.useJdkUnsafe = useJdkUnsafe;
    this.longSerializationPolicy = longSerializationPolicy;
    this.datePattern = datePattern;
    this.dateStyle = dateStyle;
    this.timeStyle = timeStyle;
    this.builderFactories = builderFactories;
    this.builderHierarchyFactories = builderHierarchyFactories;
    this.objectToNumberStrategy = objectToNumberStrategy;
    this.numberToNumberStrategy = numberToNumberStrategy;
    this.reflectionFilters = reflectionFilters;

    List<TypeAdapterFactory> factories = new ArrayList<>();

    // built-in type adapters that cannot be overridden
    factories.add(TypeAdapters.JSON_ELEMENT_FACTORY);
    factories.add(ObjectTypeAdapter.getFactory(objectToNumberStrategy));

    // the excluder must precede all adapters that handle user-defined types
    factories.add(excluder);

    // users' type adapters
    factories.addAll(factoriesToBeAdded);

    // type adapters for basic platform types
    factories.add(TypeAdapters.STRING_FACTORY);
    factories.add(TypeAdapters.INTEGER_FACTORY);
    factories.add(TypeAdapters.BOOLEAN_FACTORY);
    factories.add(TypeAdapters.BYTE_FACTORY);
    factories.add(TypeAdapters.SHORT_FACTORY);
    TypeAdapter<Number> longAdapter = longAdapter(longSerializationPolicy);
    factories.add(TypeAdapters.newFactory(long.class, Long.class, longAdapter));
    factories.add(TypeAdapters.newFactory(double.class, Double.class,
            doubleAdapter(serializeSpecialFloatingPointValues)));
    factories.add(TypeAdapters.newFactory(float.class, Float.class,
            floatAdapter(serializeSpecialFloatingPointValues)));
    factories.add(NumberTypeAdapter.getFactory(numberToNumberStrategy));
    factories.add(TypeAdapters.ATOMIC_INTEGER_FACTORY);
    factories.add(TypeAdapters.ATOMIC_BOOLEAN_FACTORY);
    factories.add(TypeAdapters.newFactory(AtomicLong.class, atomicLongAdapter(longAdapter)));
    factories.add(TypeAdapters.newFactory(AtomicLongArray.class, atomicLongArrayAdapter(longAdapter)));
    factories.add(TypeAdapters.ATOMIC_INTEGER_ARRAY_FACTORY);
    factories.add(TypeAdapters.CHARACTER_FACTORY);
    factories.add(TypeAdapters.STRING_BUILDER_FACTORY);
    factories.add(TypeAdapters.STRING_BUFFER_FACTORY);
    factories.add(TypeAdapters.newFactory(BigDecimal.class, TypeAdapters.BIG_DECIMAL));
    factories.add(TypeAdapters.newFactory(BigInteger.class, TypeAdapters.BIG_INTEGER));
    // Add adapter for LazilyParsedNumber because user can obtain it from Gson and then try to serialize it again
    factories.add(TypeAdapters.newFactory(LazilyParsedNumber.class, TypeAdapters.LAZILY_PARSED_NUMBER));
    factories.add(TypeAdapters.URL_FACTORY);
    factories.add(TypeAdapters.URI_FACTORY);
    factories.add(TypeAdapters.UUID_FACTORY);
    factories.add(TypeAdapters.CURRENCY_FACTORY);
    factories.add(TypeAdapters.LOCALE_FACTORY);
    factories.add(TypeAdapters.INET_ADDRESS_FACTORY);
    factories.add(TypeAdapters.BIT_SET_FACTORY);
    factories.add(DateTypeAdapter.FACTORY);
    factories.add(TypeAdapters.CALENDAR_FACTORY);

    if (SqlTypesSupport.SUPPORTS_SQL_TYPES) {
      factories.add(SqlTypesSupport.TIME_FACTORY);
      factories.add(SqlTypesSupport.DATE_FACTORY);
      factories.add(SqlTypesSupport.TIMESTAMP_FACTORY);
    }

    factories.add(ArrayTypeAdapter.FACTORY);
    factories.add(TypeAdapters.CLASS_FACTORY);

    // type adapters for composite and user-defined types
    factories.add(new CollectionTypeAdapterFactory(constructorConstructor));
    factories.add(new MapTypeAdapterFactory(constructorConstructor, complexMapKeySerialization));
    this.jsonAdapterFactory = new JsonAdapterAnnotationTypeAdapterFactory(constructorConstructor);
    factories.add(jsonAdapterFactory);
    factories.add(TypeAdapters.ENUM_FACTORY);
    factories.add(new ReflectiveTypeAdapterFactory(
        constructorConstructor, fieldNamingStrategy, excluder, jsonAdapterFactory, reflectionFilters));

    this.factories = Collections.unmodifiableList(factories);
  }

```

invoke examples of focal method: 
example 1:
```java
  @Override
  public <T> TypeAdapter<T> create(Gson gson, final TypeToken<T> type) {
    Class<? super T> raw = type.getRawType();
    if (!Object.class.isAssignableFrom(raw)) {
    FilterResult filterResult =
        ReflectionAccessFilterHelper.getFilterResult(reflectionFilters, raw);
    if (filterResult == FilterResult.BLOCK_ALL) {
    boolean blockInaccessible = filterResult == FilterResult.BLOCK_INACCESSIBLE;
    if (ReflectionHelper.isRecord(raw)) {
      TypeAdapter<T> adapter = (TypeAdapter<T>) new RecordAdapter<>(raw,
          getBoundFields(gson, type, raw, blockInaccessible, true), blockInaccessible);
    ObjectConstructor<T> constructor = constructorConstructor.get(type);
    return new FieldReflectionAdapter<>(constructor, getBoundFields(gson, type, raw, blockInaccessible, false));
  }
```



@input{test class template}
```java
package com.google.gson.internal.bind;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
// Add other imports if necessary

@ExtendWith(MockitoExtension.class)
class ReflectiveTypeAdapterFactory_getBoundFields_Test {
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