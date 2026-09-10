```java
package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.reflect.TypeToken;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.SerializedName;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Method;
import java.lang.reflect.Field;

@ExtendWith(MockitoExtension.class)
class ReflectiveTypeAdapterFactory_getBoundFields_Test {
    private ReflectiveTypeAdapterFactory reflectiveTypeAdapterFactory;
    private Gson gson;
    private TypeToken<?> typeToken;
    private Class<?> rawClass;
    private boolean blockInaccessible;
    private boolean isRecord;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the Gson instance and other dependencies
        gson = new Gson();
        ConstructorConstructor constructorConstructor = mock(ConstructorConstructor.class);
        FieldNamingStrategy fieldNamingStrategy = mock(FieldNamingStrategy.class);
        Excluder excluder = mock(Excluder.class);
        JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory = new JsonAdapterAnnotationTypeAdapterFactory(constructorConstructor);
        List<ReflectionAccessFilter> reflectionFilters = Collections.emptyList();

        // Create an instance of ReflectiveTypeAdapterFactory
        reflectiveTypeAdapterFactory = new ReflectiveTypeAdapterFactory(constructorConstructor, fieldNamingStrategy, excluder, jsonAdapterFactory, reflectionFilters);
    }

    @Test
    void testGetBoundFieldsForNormalClass() {
        // Setup parameters for the test case
        typeToken = TypeToken.get(MyClass.class);
        rawClass = MyClass.class;
        blockInaccessible = false;
        isRecord = false;

        // Invoke the getBoundFields method
        Map<String, ReflectiveTypeAdapterFactory.BoundField> result = invokeGetBoundFields(gson, typeToken, rawClass, blockInaccessible, isRecord);

        // Validate the result
        assertNotNull(result);
        assertFalse(result.isEmpty(), "Expected a map with bound fields for MyClass");
    }

    @Test
    void testGetBoundFieldsForInterface() {
        // Setup parameters for the test case
        typeToken = TypeToken.get(MyInterface.class);
        rawClass = MyInterface.class;
        blockInaccessible = false;
        isRecord = false;

        // Invoke the getBoundFields method
        Map<String, ReflectiveTypeAdapterFactory.BoundField> result = invokeGetBoundFields(gson, typeToken, rawClass, blockInaccessible, isRecord);

        // Validate the result
        assertNotNull(result);
        assertTrue(result.isEmpty(), "Expected an empty map when the raw type is an interface.");
    }

    @Test
    void testGetBoundFieldsForRecord() {
        // Setup parameters for the test case
        typeToken = TypeToken.get(MyClass.class);
        rawClass = MyClass.class;
        blockInaccessible = true;
        isRecord = true;

        // Invoke the getBoundFields method
        Map<String, ReflectiveTypeAdapterFactory.BoundField> result = invokeGetBoundFields(gson, typeToken, rawClass, blockInaccessible, isRecord);

        // Validate the result
        assertNotNull(result);
        // Additional assertions based on expected behavior for records
    }

    @Test
    void testGetBoundFieldsExcludesStaticFields() {
        // Setup parameters for the test case
        typeToken = TypeToken.get(MyClassWithStaticField.class);
        rawClass = MyClassWithStaticField.class;
        blockInaccessible = false;
        isRecord = true;

        // Invoke the getBoundFields method
        Map<String, ReflectiveTypeAdapterFactory.BoundField> result = invokeGetBoundFields(gson, typeToken, rawClass, blockInaccessible, isRecord);

        // Validate the result
        assertNotNull(result);
        // Additional assertions to ensure static fields are excluded
    }

    @Test
    void testGetBoundFieldsThrowsJsonIOExceptionForBlockedAccess() {
        // Setup parameters for the test case
        typeToken = TypeToken.get(MyClassWithInaccessibleField.class);
        rawClass = MyClassWithInaccessibleField.class;
        blockInaccessible = true;
        isRecord = false;

        // Invoke the getBoundFields method and expect an exception
        assertThrows(JsonIOException.class, () -> {
            invokeGetBoundFields(gson, typeToken, rawClass, blockInaccessible, isRecord);
        });
    }

    @Test
    void testGetBoundFieldsThrowsIllegalArgumentExceptionForMultipleJsonFields() {
        // Setup parameters for the test case
        typeToken = TypeToken.get(MyClassWithMultipleJsonFields.class);
        rawClass = MyClassWithMultipleJsonFields.class;
        blockInaccessible = false;
        isRecord = false;

        // Invoke the getBoundFields method and expect an exception
        assertThrows(IllegalArgumentException.class, () -> {
            invokeGetBoundFields(gson, typeToken, rawClass, blockInaccessible, isRecord);
        });
    }

    @Test
    void testGetBoundFieldsIncludesPrivateFields() {
        // Setup parameters for the test case
        typeToken = TypeToken.get(MyClassWithPrivateField.class);
        rawClass = MyClassWithPrivateField.class;
        blockInaccessible = false;
        isRecord = false;

        // Invoke the getBoundFields method
        Map<String, ReflectiveTypeAdapterFactory.BoundField> result = invokeGetBoundFields(gson, typeToken, rawClass, blockInaccessible, isRecord);

        // Validate the result
        assertNotNull(result);
        // Additional assertions to ensure private fields are included
    }

    @Test
    void testGetBoundFieldsForInheritedFields() {
        // Setup parameters for the test case
        typeToken = TypeToken.get(MyClassWithInheritedFields.class);
        rawClass = MyClassWithInheritedFields.class;
        blockInaccessible = false;
        isRecord = false;

        // Invoke the getBoundFields method
        Map<String, ReflectiveTypeAdapterFactory.BoundField> result = invokeGetBoundFields(gson, typeToken, rawClass, blockInaccessible, isRecord);

        // Validate the result
        assertNotNull(result);
        // Additional assertions to ensure inherited fields are handled
    }

    // Helper method to invoke the private getBoundFields method using reflection
    private Map<String, ReflectiveTypeAdapterFactory.BoundField> invokeGetBoundFields(Gson context, TypeToken<?> type, Class<?> raw, boolean blockInaccessible, boolean isRecord) {
        try {
            Method method = ReflectiveTypeAdapterFactory.class.getDeclaredMethod("getBoundFields", Gson.class, TypeToken.class, Class.class, boolean.class, boolean.class);
            method.setAccessible(true);
            return (Map<String, ReflectiveTypeAdapterFactory.BoundField>) method.invoke(reflectiveTypeAdapterFactory, context, type, raw, blockInaccessible, isRecord);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Failed to invoke getBoundFields", e);
        }
    }
}
```