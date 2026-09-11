```java
package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class ReflectiveTypeAdapterFactory_getBoundFields_Test {
    private ReflectiveTypeAdapterFactory reflectiveTypeAdapterFactory;
    private Gson gson;
    private ConstructorConstructor constructorConstructor;
    private FieldNamingStrategy fieldNamingPolicy;
    private Excluder excluder;
    private JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory;
    private List<ReflectionAccessFilter> reflectionFilters;

    @BeforeAll
    static void setupBeforeAll() {
        // Any global setup can be done here
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize dependencies
        constructorConstructor = mock(ConstructorConstructor.class);
        fieldNamingPolicy = mock(FieldNamingStrategy.class);
        excluder = mock(Excluder.class);
        jsonAdapterFactory = new JsonAdapterAnnotationTypeAdapterFactory(constructorConstructor);
        reflectionFilters = new ArrayList<>();
        gson = new Gson(); // You can customize Gson if needed

        // Create an instance of ReflectiveTypeAdapterFactory
        reflectiveTypeAdapterFactory = new ReflectiveTypeAdapterFactory(
            constructorConstructor, fieldNamingPolicy, excluder, jsonAdapterFactory, reflectionFilters
        );
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup after each test if necessary
    }

    @AfterAll
    static void teardownAfterAll() {
        // Cleanup after all tests if necessary
    }

    @Test
    void testGetBoundFields_InterfaceHandling() {
        // Given: Setup parameters for an interface type
        TypeToken<?> typeToken = TypeToken.get(MyInterface.class);
        Class<?> rawClass = MyInterface.class;
        boolean blockInaccessible = false;
        boolean isRecord = false;

        // When: Invoke the focal method
        Map<String, ReflectiveTypeAdapterFactory.BoundField> boundFields = 
            reflectiveTypeAdapterFactory.getBoundFields(gson, typeToken, rawClass, blockInaccessible, isRecord);

        // Then: Verify the expected outcome
        assertNotNull(boundFields);
        assertTrue(boundFields.isEmpty(), "Expected an empty map for interface type.");
    }

    @Test
    void testGetBoundFields_ReflectionAccessFilter() {
        // Given: Setup parameters for a class blocked by ReflectionAccessFilter
        TypeToken<?> typeToken = TypeToken.get(MyClass.class);
        Class<?> rawClass = MyClass.class;
        boolean blockInaccessible = false;
        boolean isRecord = false;

        // When & Then: Expect a JsonIOException to be thrown
        JsonIOException exception = assertThrows(JsonIOException.class, () -> {
            reflectiveTypeAdapterFactory.getBoundFields(gson, typeToken, rawClass, blockInaccessible, isRecord);
        });
        assertEquals("ReflectionAccessFilter does not permit using reflection for MyClass (supertype of MyClass). Register a TypeAdapter for this type or adjust the access filter.", exception.getMessage());
    }

    @Test
    void testGetBoundFields_FieldSerializationAndDeserialization() {
        // Given: Setup parameters for a class with fields that should be serialized and deserialized
        TypeToken<?> typeToken = TypeToken.get(MyClass.class);
        Class<?> rawClass = MyClass.class;
        boolean blockInaccessible = true;
        boolean isRecord = false;

        // When: Invoke the focal method
        Map<String, ReflectiveTypeAdapterFactory.BoundField> boundFields = 
            reflectiveTypeAdapterFactory.getBoundFields(gson, typeToken, rawClass, blockInaccessible, isRecord);

        // Then: Verify the expected outcome
        assertNotNull(boundFields);
        assertEquals("BoundField for field1", boundFields.get("field1").name);
        assertEquals("BoundField for field2", boundFields.get("field2").name);
    }

    @Test
    void testGetBoundFields_MultipleJSONFieldsConflict() {
        // Given: Setup parameters for a class with multiple fields having the same JSON name
        TypeToken<?> typeToken = TypeToken.get(MyConflictClass.class);
        Class<?> rawClass = MyConflictClass.class;
        boolean blockInaccessible = false;
        boolean isRecord = false;

        // When & Then: Expect an IllegalArgumentException to be thrown
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            reflectiveTypeAdapterFactory.getBoundFields(gson, typeToken, rawClass, blockInaccessible, isRecord);
        });
        assertEquals("Class MyConflictClass declares multiple JSON fields named 'fieldName'; conflict is caused by fields field1 and field2.", exception.getMessage());
    }

    @Test
    void testGetBoundFields_StaticFieldHandlingInRecords() {
        // Given: Setup parameters for a record with a static field
        TypeToken<?> typeToken = TypeToken.get(MyRecord.class);
        Class<?> rawClass = MyRecord.class;
        boolean blockInaccessible = false;
        boolean isRecord = true;

        // When: Invoke the focal method
        Map<String, ReflectiveTypeAdapterFactory.BoundField> boundFields = 
            reflectiveTypeAdapterFactory.getBoundFields(gson, typeToken, rawClass, blockInaccessible, isRecord);

        // Then: Verify the expected outcome
        assertNotNull(boundFields);
        assertEquals("BoundField for field1", boundFields.get("field1").name);
        assertEquals("BoundField for field2", boundFields.get("field2").name);
    }

    @Test
    void testGetBoundFields_InaccessibleFieldHandling() {
        // Given: Setup parameters for a class with both accessible and inaccessible fields
        TypeToken<?> typeToken = TypeToken.get(MyClassWithInaccessibleFields.class);
        Class<?> rawClass = MyClassWithInaccessibleFields.class;
        boolean blockInaccessible = true;
        boolean isRecord = false;

        // When: Invoke the focal method
        Map<String, ReflectiveTypeAdapterFactory.BoundField> boundFields = 
            reflectiveTypeAdapterFactory.getBoundFields(gson, typeToken, rawClass, blockInaccessible, isRecord);

        // Then: Verify the expected outcome
        assertNotNull(boundFields);
        assertEquals("BoundField for accessibleField", boundFields.get("accessibleField").name);
        assertFalse(boundFields.containsKey("inaccessibleField"), "Inaccessible field should not be included.");
    }

    @Test
    void testGetBoundFields_RecordFieldHandling() {
        // Given: Setup parameters for a record with accessors for fields
        TypeToken<?> typeToken = TypeToken.get(MyRecordWithAccessors.class);
        Class<?> rawClass = MyRecordWithAccessors.class;
        boolean blockInaccessible = false;
        boolean isRecord = true;

        // When: Invoke the focal method
        Map<String, ReflectiveTypeAdapterFactory.BoundField> boundFields = 
            reflectiveTypeAdapterFactory.getBoundFields(gson, typeToken, rawClass, blockInaccessible, isRecord);

        // Then: Verify the expected outcome
        assertNotNull(boundFields);
        assertEquals("BoundField for field1 using accessor", boundFields.get("field1").name);
        assertEquals("BoundField for field2 using accessor", boundFields.get("field2").name);
    }

    @Test
    void testGetBoundFields_EmptyClassHandling() {
        // Given: Setup parameters for an empty class
        TypeToken<?> typeToken = TypeToken.get(EmptyClass.class);
        Class<?> rawClass = EmptyClass.class;
        boolean blockInaccessible = false;
        boolean isRecord = false;

        // When: Invoke the focal method
        Map<String, ReflectiveTypeAdapterFactory.BoundField> boundFields = 
            reflectiveTypeAdapterFactory.getBoundFields(gson, typeToken, rawClass, blockInaccessible, isRecord);

        // Then: Verify the expected outcome
        assertNotNull(boundFields);
        assertTrue(boundFields.isEmpty(), "Expected an empty map for empty class.");
    }

    @Test
    void testGetBoundFields_FieldWithSerializedNameAnnotation() {
        // Given: Setup parameters for a class where a field has a SerializedName annotation but accessor does not
        TypeToken<?> typeToken = TypeToken.get(ClassWithSerializedName.class);
        Class<?> rawClass = ClassWithSerializedName.class;
        boolean blockInaccessible = false;
        boolean isRecord = false;

        // When & Then: Expect a JsonIOException to be thrown
        JsonIOException exception = assertThrows(JsonIOException.class, () -> {
            reflectiveTypeAdapterFactory.getBoundFields(gson, typeToken, rawClass, blockInaccessible, isRecord);
        });
        assertEquals("SerializedName on fieldName is not supported", exception.getMessage());
    }

    @Test
    void testGetBoundFields_MultipleInheritanceHandling() {
        // Given: Setup parameters for a derived class that inherits fields from a base class
        TypeToken<?> typeToken = TypeToken.get(DerivedClass.class);
        Class<?> rawClass = DerivedClass.class;
        boolean blockInaccessible = false;
        boolean isRecord = false;

        // When: Invoke the focal method
        Map<String, ReflectiveTypeAdapterFactory.BoundField> boundFields = 
            reflectiveTypeAdapterFactory.getBoundFields(gson, typeToken, rawClass, blockInaccessible, isRecord);

        // Then: Verify the expected outcome
        assertNotNull(boundFields);
        assertEquals("BoundField for baseField", boundFields.get("baseField").name);
        assertEquals("BoundField for derivedField", boundFields.get("derivedField").name);
    }

    @Test
    void testGetBoundFields_AccessorMethodWithSerializedName() {
        // Given: Setup parameters for a class where the accessor method has a SerializedName annotation but the field does not
        TypeToken<?> typeToken = TypeToken.get(ClassWithAccessorAndSerializedName.class);
        Class<?> rawClass = ClassWithAccessorAndSerializedName.class;
        boolean blockInaccessible = false;
        boolean isRecord = false;

        // When & Then: Expect a JsonIOException to be thrown
        JsonIOException exception = assertThrows(JsonIOException.class, () -> {
            reflectiveTypeAdapterFactory.getBoundFields(gson, typeToken, rawClass, blockInaccessible, isRecord);
        });
        assertEquals("SerializedName on accessorMethod is not supported", exception.getMessage());
    }
}
```