```java
package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.ReflectionAccessFilter;
import com.google.gson.JsonIOException;
import com.google.gson.internal.reflect.ReflectionHelper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class ReflectiveTypeAdapterFactory_getBoundFields_Test{

    @Mock
    private ConstructorConstructor constructorConstructor;

    @Mock
    private FieldNamingStrategy fieldNamingStrategy;

    @Mock
    private Excluder excluder;

    @Mock
    private JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory;

    @Mock
    private Gson gson;

    @Mock
    private TypeToken<?> typeToken;

    private ReflectiveTypeAdapterFactory reflectiveTypeAdapterFactory;

    @BeforeAll
    static void setupBeforeAll() {
        // Perform setup that needs to happen once before all tests
    }

    @BeforeEach
    void setupBeforeEach() {
        List<ReflectionAccessFilter> reflectionFilters = Collections.emptyList();
        reflectiveTypeAdapterFactory = new ReflectiveTypeAdapterFactory(
            constructorConstructor,
            fieldNamingStrategy,
            excluder,
            jsonAdapterFactory,
            reflectionFilters
        );
    }

    @AfterEach
    void teardownAfterEach() {
        // Perform cleanup after each test
    }

    @AfterAll
    static void teardownAfterAll() {
        // Perform cleanup after all tests have been run
    }

    @Test
    void testGetBoundFields_Interface() {
        // given
        Class<?> raw = MockClass.class; // MockClass should be an interface
        boolean blockInaccessible = false;
        boolean isRecord = false;

        // when
        Map<String, ReflectiveTypeAdapterFactory.BoundField> boundFields = reflectiveTypeAdapterFactory.getBoundFields(
            gson, typeToken, raw, blockInaccessible, isRecord
        );

        // then
        assertNotNull(boundFields);
        assertTrue(boundFields.isEmpty(), "Expected an empty map for interface");
    }

    @Test
    void testGetBoundFields_ReflectionAccessFilterBlockAll() {
        // given
        Class<?> raw = ConcreteClass.class; // ConcreteClass should be a valid class
        boolean blockInaccessible = false;
        boolean isRecord = false;

        // Mock behavior of ReflectionAccessFilterHelper
        ReflectionAccessFilterHelper reflectionAccessFilterHelper = mock(ReflectionAccessFilterHelper.class);
        when(reflectionAccessFilterHelper.getFilterResult(anyList(), eq(raw)))
            .thenReturn(ReflectionAccessFilter.FilterResult.BLOCK_ALL);

        // when & then
        assertThrows(JsonIOException.class, () -> {
            reflectiveTypeAdapterFactory.getBoundFields(
                gson, typeToken, raw, blockInaccessible, isRecord
            );
        }, "Expected JsonIOException due to BLOCK_ALL filter result");
    }

    @Test
    void testGetBoundFields_AccessibleFields() {
        // given
        Class<?> raw = ConcreteClass.class; // ConcreteClass should be a valid class
        boolean blockInaccessible = false;
        boolean isRecord = false;

        // when
        Map<String, ReflectiveTypeAdapterFactory.BoundField> boundFields = reflectiveTypeAdapterFactory.getBoundFields(
            gson, typeToken, raw, blockInaccessible, isRecord
        );

        // then
        assertNotNull(boundFields);
        assertFalse(boundFields.isEmpty(), "Expected a map with bound fields");
    }

    @Test
    void testGetBoundFields_RecordClassWithAccessors() {
        // given
        Class<?> raw = RecordClass.class; // RecordClass should be a valid record class
        boolean blockInaccessible = false;
        boolean isRecord = true;

        // when
        Map<String, ReflectiveTypeAdapterFactory.BoundField> boundFields = reflectiveTypeAdapterFactory.getBoundFields(
            gson, typeToken, raw, blockInaccessible, isRecord
        );

        // then
        assertNotNull(boundFields);
        assertFalse(boundFields.isEmpty(), "Expected a map with bound fields using accessor methods");
    }

    @Test
    void testGetBoundFields_ConflictingFieldNames() {
        // given
        Class<?> raw = ConflictingClass.class; // ConflictingClass should have conflicting field names
        boolean blockInaccessible = false;
        boolean isRecord = false;

        // when & then
        assertThrows(IllegalArgumentException.class, () -> {
            reflectiveTypeAdapterFactory.getBoundFields(
                gson, typeToken, raw, blockInaccessible, isRecord
            );
        }, "Expected IllegalArgumentException due to conflicting field names");
    }

    @Test
    void testGetBoundFields_EmptyClass() {
        // given
        Class<?> raw = EmptyClass.class; // EmptyClass should have no fields
        boolean blockInaccessible = false;
        boolean isRecord = false;

        // when
        Map<String, ReflectiveTypeAdapterFactory.BoundField> boundFields = reflectiveTypeAdapterFactory.getBoundFields(
            gson, typeToken, raw, blockInaccessible, isRecord
        );

        // then
        assertNotNull(boundFields);
        assertTrue(boundFields.isEmpty(), "Expected an empty map for a class with no fields");
    }

    @Test
    void testGetBoundFields_StaticFieldsClass() {
        // given
        Class<?> raw = StaticFieldsClass.class; // StaticFieldsClass should only have static fields
        boolean blockInaccessible = false;
        boolean isRecord = false;

        // when
        Map<String, ReflectiveTypeAdapterFactory.BoundField> boundFields = reflectiveTypeAdapterFactory.getBoundFields(
            gson, typeToken, raw, blockInaccessible, isRecord
        );

        // then
        assertNotNull(boundFields);
        // Assuming Gson ignores static fields by default
        assertTrue(boundFields.isEmpty(), "Expected a map excluding static fields");
    }

    @Test
    void testGetBoundFields_InaccessibleFields() {
        // given
        Class<?> raw = InaccessibleFieldsClass.class; // InaccessibleFieldsClass should have fields that are inaccessible
        boolean blockInaccessible = true;
        boolean isRecord = false;

        // when & then
        assertThrows(JsonIOException.class, () -> {
            reflectiveTypeAdapterFactory.getBoundFields(
                gson, typeToken, raw, blockInaccessible, isRecord
            );
        }, "Expected JsonIOException due to inaccessible fields");
    }

    @Test
    void testGetBoundFields_AnnotatedFieldClass() {
        // given
        Class<?> raw = AnnotatedFieldClass.class; // AnnotatedFieldClass should have fields with annotations
        boolean blockInaccessible = false;
        boolean isRecord = false;

        // when
        Map<String, ReflectiveTypeAdapterFactory.BoundField> boundFields = reflectiveTypeAdapterFactory.getBoundFields(
            gson, typeToken, raw, blockInaccessible, isRecord
        );

        // then
        assertNotNull(boundFields);
        assertFalse(boundFields.isEmpty(), "Expected a map with bound fields including annotated fields");
    }

    @Test
    void testGetBoundFields_SuperClass() {
        // given
        Class<?> raw = SuperClass.class; // SuperClass should have inherited fields
        boolean blockInaccessible = false;
        boolean isRecord = false;

        // when
        Map<String, ReflectiveTypeAdapterFactory.BoundField> boundFields = reflectiveTypeAdapterFactory.getBoundFields(
            gson, typeToken, raw, blockInaccessible, isRecord
        );

        // then
        assertNotNull(boundFields);
        assertFalse(boundFields.isEmpty(), "Expected a map with bound fields from superclass");
    }

    @Test
    void testGetBoundFields_RecordClassWithSerializedName() {
        // given
        Class<?> raw = RecordClassWithSerializedName.class; // RecordClassWithSerializedName should have @SerializedName on accessor method
        boolean blockInaccessible = false;
        boolean isRecord = true;

        // when & then
        assertThrows(JsonIOException.class, () -> {
            reflectiveTypeAdapterFactory.getBoundFields(
                gson, typeToken, raw, blockInaccessible, isRecord
            );
        }, "Expected JsonIOException due to @SerializedName on accessor method without corresponding field annotation");
    }
}
```