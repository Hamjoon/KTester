package com.google.gson.internal;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.lang.reflect.Field;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;
import com.google.gson.FieldAttributes;
import com.google.gson.ExclusionStrategy;
// Add other imports if necessary
import java.util.Collections;
import java.util.List;
import java.lang.reflect.Modifier;

@ExtendWith(MockitoExtension.class)
class Excluder_excludeField_Test {

    private Excluder excluder;

    private Field mockField;

    // Inner classes for testing purposes
    static class InnerClass {
    }

    static class AnonymousClass {
    }

    @BeforeEach
    void setupBeforeEach() {
        excluder = new Excluder();
        mockField = mock(Field.class);
    }

    @Test
    void testExcludeField_withModifiers() {
        // Given
        when(mockField.getModifiers()).thenReturn(Modifier.TRANSIENT);
        // When
        boolean result = excluder.excludeField(mockField, true);
        // Then
        assertTrue(result);
    }

    @Test
    void testExcludeField_withVersion() {
        // Given
        Since since = mock(Since.class);
        Until until = mock(Until.class);
        when(mockField.getAnnotation(Since.class)).thenReturn(since);
        when(mockField.getAnnotation(Until.class)).thenReturn(until);
        Excluder excluderWithVersion = new Excluder().withVersion(1.0);
        // When
        boolean result = excluderWithVersion.excludeField(mockField, true);
        // Then
        assertTrue(result);
    }

    @Test
    void testExcludeField_withExposeAnnotation() {
        // Given
        Expose expose = mock(Expose.class);
        when(mockField.getAnnotation(Expose.class)).thenReturn(expose);
        when(expose.serialize()).thenReturn(false);
        Excluder excluderWithExpose = new Excluder().excludeFieldsWithoutExposeAnnotation();
        // When
        boolean result = excluderWithExpose.excludeField(mockField, true);
        // Then
        assertTrue(result);
    }

    @Test
    void testExcludeField_withInnerClass() {
        // Given
        when(mockField.getType()).thenReturn((Class) InnerClass.class);
        Excluder excluderWithInnerClassDisabled = new Excluder().disableInnerClassSerialization();
        // When
        boolean result = excluderWithInnerClassDisabled.excludeField(mockField, true);
        // Then
        assertTrue(result);
    }

    @Test
    void testExcludeField_withAnonymousOrNonStaticLocalClass() {
        // Given
        when(mockField.getType()).thenReturn((Class) AnonymousClass.class);
        // When
        boolean result = excluder.excludeField(mockField, true);
        // Then
        assertTrue(result);
    }

    @Test
    void testExcludeField_withExclusionStrategy() {
        // Given
        ExclusionStrategy strategy = mock(ExclusionStrategy.class);
        when(strategy.shouldSkipField(any(FieldAttributes.class))).thenReturn(true);
        Excluder excluderWithStrategy = new Excluder().withExclusionStrategy(strategy, true, false);
        // When
        boolean result = excluderWithStrategy.excludeField(mockField, true);
        // Then
        assertTrue(result);
    }

    @Test
    void testExcludeField_withoutAnyExclusion() {
        // Given
        when(mockField.getModifiers()).thenReturn(0);
        when(mockField.isSynthetic()).thenReturn(false);
        when(mockField.getType()).thenReturn((Class) Object.class);
        // When
        boolean result = excluder.excludeField(mockField, true);
        // Then
        assertFalse(result);
    }
}
