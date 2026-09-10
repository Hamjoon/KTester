package com.google.gson.reflect;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class TypeToken_isAssignableFrom_Test {

    private TypeToken<?> typeTokenObject;

    private TypeToken<?> typeTokenString;

    private TypeToken<?> typeTokenNumber;

    private Map<String, Type> typeVarMap;

    private List<Integer> listOfInteger;

    private List<Number> listOfNumber;

    private List<String> listOfString;

    private List<?> listOfUnknown;

    private Set<String> listOfSetOfString;

    private Set<?> listOfSetOfUnknown;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize TypeTokens for testing
        typeTokenObject = TypeToken.get(Object.class);
        typeTokenString = TypeToken.get(String.class);
        typeTokenNumber = TypeToken.get(Number.class);
        typeVarMap = new HashMap<>();
        // Initialize lists for testing
        listOfInteger = new ArrayList<>();
        listOfNumber = new ArrayList<>();
        listOfString = new ArrayList<>();
        listOfUnknown = new ArrayList<>();
        listOfSetOfString = Set.of("test");
        listOfSetOfUnknown = Set.of();
    }

    // Helper method to invoke the private method using reflection
    private boolean invokeIsAssignableFrom(Object from, String to, Map<String, Type> typeVarMap) {
        try {
            Class<?> clazz = Class.forName(to);
            ParameterizedType parameterizedTypeTo = (ParameterizedType) clazz.getGenericSuperclass();
            return (boolean) TypeToken.class.getDeclaredMethod("isAssignableFrom", Type.class, ParameterizedType.class, Map.class).invoke(null, from, parameterizedTypeTo, typeVarMap);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testIsAssignableFromRawTypes() {
        // Test when checking assignability between raw types
        assertTrue(typeTokenObject.isAssignableFrom(String.class));
        assertFalse(typeTokenString.isAssignableFrom(Object.class));
    }

    @Test
    void testIsAssignableFromWithTypeParameters() throws Exception {
        // Test when 'from' and 'to' are parameterized types with the same type arguments
        Type listOfIntegerType = getClass().getDeclaredField("listOfInteger").getGenericType();
        Type listOfNumberType = getClass().getDeclaredField("listOfNumber").getGenericType();
        assertTrue(TypeToken.get(listOfIntegerType).isAssignableFrom(listOfIntegerType));
        assertTrue(TypeToken.get(listOfNumberType).isAssignableFrom(listOfNumberType));
        assertFalse(TypeToken.get(listOfIntegerType).isAssignableFrom(listOfNumberType));
        assertFalse(TypeToken.get(listOfNumberType).isAssignableFrom(listOfIntegerType));
    }

    @Test
    void testIsAssignableFromWithBasicWildcards() throws Exception {
        // Test when checking assignability with wildcards
        Type listOfStringType = getClass().getDeclaredField("listOfString").getGenericType();
        Type listOfUnknownType = getClass().getDeclaredField("listOfUnknown").getGenericType();
        assertTrue(TypeToken.get(listOfStringType).isAssignableFrom(listOfStringType));
        assertTrue(TypeToken.get(listOfUnknownType).isAssignableFrom(listOfUnknownType));
        assertFalse(TypeToken.get(listOfStringType).isAssignableFrom(listOfUnknownType));
    }

    @Test
    void testIsAssignableFromWithNestedWildcards() throws Exception {
        // Test when checking nested wildcard types
        Type listOfSetOfStringType = getClass().getDeclaredField("listOfSetOfString").getGenericType();
        Type listOfSetOfUnknownType = getClass().getDeclaredField("listOfSetOfUnknown").getGenericType();
        assertTrue(TypeToken.get(listOfSetOfStringType).isAssignableFrom(listOfSetOfStringType));
        assertTrue(TypeToken.get(listOfSetOfUnknownType).isAssignableFrom(listOfSetOfUnknownType));
        assertFalse(TypeToken.get(listOfSetOfStringType).isAssignableFrom(listOfSetOfUnknownType));
    }

    @Test
    void testArrayFactory() {
        // Test the creation of an array TypeToken
        TypeToken<?> expectedStringArray = new TypeToken<String[]>() {
        };
        assertEquals(expectedStringArray, TypeToken.getArray(String.class));
    }

    // Test cases from JSON input
    @Test
    void testIsAssignableFromNullType() {
        // Test when 'from' is null
        assertFalse(invokeIsAssignableFrom(null, "java.util.ArrayList", new HashMap<>()));
    }

    @Test
    void testIsAssignableFromEqualTypes() {
        // Test when 'from' and 'to' are equal types
        assertTrue(invokeIsAssignableFrom(TypeToken.get(ArrayList.class).getType(), "java.util.ArrayList", new HashMap<>()));
    }

    @Test
    void testIsAssignableFromParameterizedTypeHandling() {
        // Test handling parameterized types
        assertFalse(invokeIsAssignableFrom(TypeToken.get(ArrayList.class).getType(), "java.util.ArrayList<SomeOtherType>", new HashMap<>()));
        assertTrue(invokeIsAssignableFrom(TypeToken.get(ArrayList.class).getType(), "java.util.ArrayList<SomeType>", new HashMap<>()));
    }

    @Test
    void testIsAssignableFromInterfaceAssignment() {
        // Test interface assignment
        assertTrue(invokeIsAssignableFrom(TypeToken.get(List.class).getType(), "java.util.Collection", new HashMap<>()));
        assertFalse(invokeIsAssignableFrom(TypeToken.get(ArrayList.class).getType(), "java.util.Set", new HashMap<>()));
    }

    @Test
    void testIsAssignableFromSuperclassAssignment() {
        // Test superclass assignment
        assertTrue(invokeIsAssignableFrom(TypeToken.get(ArrayList.class).getType(), "java.util.List", new HashMap<>()));
        assertFalse(invokeIsAssignableFrom(TypeToken.get(HashMap.class).getType(), "java.util.List", new HashMap<>()));
    }

    @Test
    void testIsAssignableFromGenericArrayTypeHandling() {
        // Test handling generic array types
        assertFalse(invokeIsAssignableFrom(TypeToken.get(List.class).getType(), "java.util.List<SomeOtherType>[]", new HashMap<>()));
        assertTrue(invokeIsAssignableFrom(TypeToken.get(List.class).getType(), "java.util.List<SomeType>[]", new HashMap<>()));
    }

    @Test
    void testIsAssignableFromNestedParameterizedTypes() {
        // Test nested parameterized types
        assertFalse(invokeIsAssignableFrom(TypeToken.get(Map.class).getType(), "java.util.Map<SomeOtherType, SomeOtherInnerType>", new HashMap<>()));
        assertTrue(invokeIsAssignableFrom(TypeToken.get(Map.class).getType(), "java.util.Map<SomeType, SomeInnerType>", new HashMap<>()));
    }

    @Test
    void testIsAssignableFromWildcardTypeHandling() {
        // Test wildcard type handling
        assertFalse(invokeIsAssignableFrom(TypeToken.get(List.class).getType(), "java.util.List<? extends SomeOtherBaseType>", new HashMap<>()));
        assertTrue(invokeIsAssignableFrom(TypeToken.get(List.class).getType(), "java.util.List<? extends SomeBaseType>", new HashMap<>()));
    }

    @Test
    void testIsAssignableFromEmptyTypeVarMap() {
        // Test with an empty type variable map
        assertTrue(invokeIsAssignableFrom(TypeToken.get(ArrayList.class).getType(), "java.util.ArrayList<SomeType>", new HashMap<>()));
        assertFalse(invokeIsAssignableFrom(TypeToken.get(ArrayList.class).getType(), "java.util.ArrayList<AnotherType>", new HashMap<>()));
    }
}
