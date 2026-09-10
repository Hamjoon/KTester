package com.google.gson.internal;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.lang.reflect.Type;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.WildcardType;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class $Gson$Types_equals_Test {

    private Type typeA;

    private Type typeB;

    private Type typeC;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the types for testing
        // Example type
        typeA = String.class;
        // Example type
        typeB = String.class;
        typeC = new ParameterizedType() {

            @Override
            public Type[] getActualTypeArguments() {
                // Example type argument
                return new Type[] { Integer.class };
            }

            @Override
            public Type getRawType() {
                // Example raw type
                return Collection.class;
            }

            @Override
            public Type getOwnerType() {
                // No owner type for this example
                return null;
            }
        };
    }

    @Test
    void testEquals_SameType() {
        assertTrue($Gson$Types.equals(typeA, typeB), "Types should be equal");
    }

    @Test
    void testEquals_DifferentType() {
        assertFalse($Gson$Types.equals(typeA, typeC), "Types should not be equal");
    }

    @Test
    void testEquals_NullTypes() {
        assertTrue($Gson$Types.equals(null, null), "Both null types should be equal");
    }

    @Test
    void testEquals_NullAndNonNullType() {
        assertFalse($Gson$Types.equals(typeA, null), "One null type should not be equal to a non-null type");
    }

    @Test
    void testEquals_ParameterizedTypes() {
        Type paramTypeA = new ParameterizedType() {

            @Override
            public Type[] getActualTypeArguments() {
                return new Type[] { String.class };
            }

            @Override
            public Type getRawType() {
                return Collection.class;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        };
        Type paramTypeB = new ParameterizedType() {

            @Override
            public Type[] getActualTypeArguments() {
                return new Type[] { String.class };
            }

            @Override
            public Type getRawType() {
                return Collection.class;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        };
        assertTrue($Gson$Types.equals(paramTypeA, paramTypeB), "Parameterized types with same arguments should be equal");
    }

    @Test
    void testEquals_BasicEquality() {
        // Test that equals returns true when both parameters are null.
        assertTrue($Gson$Types.equals(null, null), "Both null types should be equal");
        // Test that equals returns true when both parameters are the same class.
        assertTrue($Gson$Types.equals(typeA, typeB), "Types should be equal");
    }

    @Test
    void testEquals_ClassComparison() {
        // Test that equals returns false when comparing different classes.
        assertFalse($Gson$Types.equals(typeA, Integer.class), "Types should not be equal");
        // Test that equals returns true when comparing the same class.
        assertTrue($Gson$Types.equals(typeA, String.class), "Types should be equal");
    }

    @Test
    void testEquals_ParameterizedTypeComparison() {
        Type paramTypeA = new ParameterizedType() {

            @Override
            public Type[] getActualTypeArguments() {
                return new Type[] { String.class };
            }

            @Override
            public Type getRawType() {
                return Collection.class;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        };
        Type paramTypeB = new ParameterizedType() {

            @Override
            public Type[] getActualTypeArguments() {
                return new Type[] { Integer.class };
            }

            @Override
            public Type getRawType() {
                return Collection.class;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        };
        // Test that equals returns false when comparing parameterized types with different actual type arguments.
        assertFalse($Gson$Types.equals(paramTypeA, paramTypeB), "Parameterized types with different arguments should not be equal");
        // Test that equals returns true when comparing parameterized types with the same actual type arguments.
        Type paramTypeC = new ParameterizedType() {

            @Override
            public Type[] getActualTypeArguments() {
                return new Type[] { String.class };
            }

            @Override
            public Type getRawType() {
                return Collection.class;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        };
        assertTrue($Gson$Types.equals(paramTypeA, paramTypeC), "Parameterized types with the same arguments should be equal");
    }

    @Test
    void testEquals_GenericArrayTypeComparison() {
        GenericArrayType arrayTypeA = new GenericArrayType() {

            @Override
            public Type getGenericComponentType() {
                return String.class;
            }
        };
        GenericArrayType arrayTypeB = new GenericArrayType() {

            @Override
            public Type getGenericComponentType() {
                return Integer.class;
            }
        };
        // Test that equals returns false when comparing generic array types with different component types.
        assertFalse($Gson$Types.equals(arrayTypeA, arrayTypeB), "Generic array types with different components should not be equal");
        GenericArrayType arrayTypeC = new GenericArrayType() {

            @Override
            public Type getGenericComponentType() {
                return String.class;
            }
        };
        // Test that equals returns true when comparing generic array types with the same component types.
        assertTrue($Gson$Types.equals(arrayTypeA, arrayTypeC), "Generic array types with the same components should be equal");
    }

    @Test
    void testEquals_WildcardTypeComparison() {
        WildcardType wildcardTypeA = new WildcardType() {

            @Override
            public Type[] getUpperBounds() {
                return new Type[] { String.class };
            }

            @Override
            public Type[] getLowerBounds() {
                return new Type[] {};
            }
        };
        WildcardType wildcardTypeB = new WildcardType() {

            @Override
            public Type[] getUpperBounds() {
                return new Type[] { Integer.class };
            }

            @Override
            public Type[] getLowerBounds() {
                return new Type[] {};
            }
        };
        // Test that equals returns false when comparing wildcard types with different upper bounds.
        assertFalse($Gson$Types.equals(wildcardTypeA, wildcardTypeB), "Wildcard types with different upper bounds should not be equal");
        WildcardType wildcardTypeC = new WildcardType() {

            @Override
            public Type[] getUpperBounds() {
                return new Type[] { String.class };
            }

            @Override
            public Type[] getLowerBounds() {
                return new Type[] {};
            }
        };
        // Test that equals returns true when comparing wildcard types with the same upper bounds.
        assertTrue($Gson$Types.equals(wildcardTypeA, wildcardTypeC), "Wildcard types with the same upper bounds should be equal");
    }

    @Test
    void testEquals_TypeVariableComparison() {
        TypeVariable<?> typeVarA = new TypeVariable<Type>() {

            @Override
            public String getName() {
                return "T";
            }

            @Override
            public Type getGenericDeclaration() {
                return String.class;
            }

            @Override
            public Type[] getBounds() {
                return new Type[] { String.class };
            }
        };
        TypeVariable<?> typeVarB = new TypeVariable<Type>() {

            @Override
            public String getName() {
                return "X";
            }

            @Override
            public Type getGenericDeclaration() {
                return String.class;
            }

            @Override
            public Type[] getBounds() {
                return new Type[] { String.class };
            }
        };
        // Test that equals returns false when comparing type variables with different declarations.
        assertFalse($Gson$Types.equals(typeVarA, typeVarB), "Type variables with different names should not be equal");
        TypeVariable<?> typeVarC = new TypeVariable<Type>() {

            @Override
            public String getName() {
                return "T";
            }

            @Override
            public Type getGenericDeclaration() {
                return String.class;
            }

            @Override
            public Type[] getBounds() {
                return new Type[] { String.class };
            }
        };
        // Test that equals returns true when comparing type variables with the same declaration and name.
        assertTrue($Gson$Types.equals(typeVarA, typeVarC), "Type variables with the same declaration and name should be equal");
    }

    @Test
    void testEquals_UnsupportedTypes() {
        Object unsupportedTypeA = new Object();
        Object unsupportedTypeB = new Object();
        // Test that equals returns false when comparing unsupported types.
        assertFalse($Gson$Types.equals(unsupportedTypeA, unsupportedTypeB), "Two different unsupported types should not be equal");
    }
}
