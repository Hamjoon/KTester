


You are an experienced Java developer tasked with fixing a piece of code that contains execution errors. Your task is to resolve these issues after analyzing the code and its execution feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any logical errors.
2. Analyze the execution feedback to understand the specific errors and their locations within the code.
3. Apply necessary corrections to the code based on the feedback, and output the compelete correct code.
The Java code you need to fix is provided below:
```java
package org.jdom2.output.support;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.jdom2.Content;
import java.util.List;
import java.util.ArrayList;
import org.jdom2.Content.CType;
import org.jdom2.output.Format;
// Correct import for MultiText
import org.jdom2.output.support.AbstractFormattedWalker.MultiText;
// Correct import for Trim
import org.jdom2.output.support.AbstractFormattedWalker.Trim;
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
class WalkerNORMALIZE_analyzeMultiText_Test {

    private WalkerNORMALIZE walker;

    private MultiText mtext;

    private List<Content> contentList;

    private FormatStack formatStack;

    @BeforeEach
    void setupBeforeEach() {
        // Mock or initialize the required objects
        contentList = new ArrayList<>();
        Format format = mock(Format.class);
        formatStack = new FormatStack(format);
        walker = new WalkerNORMALIZE(contentList, formatStack, true);
        mtext = mock(MultiText.class);
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup resources if needed
        contentList.clear();
    }

    // Helper method to create Content mock
    private Content createContentMock(CType type, String value) {
        Content content = mock(Content.class, RETURNS_DEEP_STUBS);
        when(content.getCType()).thenReturn(type);
        when(content.getValue()).thenReturn(value);
        return content;
    }

    // Test with Text Content that contains only XML whitespace
    @Test
    void testWithTextContentContainingOnlyXMLWhitespace() {
        // Arrange
        Content textContent = createContentMock(CType.Text, "   ");
        contentList.add(textContent);
        // Act
        walker.analyzeMultiText(mtext, 0, 1);
        // Assert
        verify(mtext, never()).appendText(any(), anyString());
    }

    // Test with Text Content containing non-whitespace text
    @Test
    void testWithTextContentContainingNonWhitespaceText() {
        // Arrange
        Content textContent = createContentMock(CType.Text, "Hello");
        contentList.add(textContent);
        // Act
        walker.analyzeMultiText(mtext, 0, 1);
        // Assert
        verify(mtext).appendText(eq(Trim.COMPACT), eq("Hello"));
    }

    // Test with CDATA Content that contains only XML whitespace
    @Test
    void testWithCDATAContentContainingOnlyXMLWhitespace() {
        // Arrange
        Content cdataContent = createContentMock(CType.CDATA, "   ");
        contentList.add(cdataContent);
        // Act
        walker.analyzeMultiText(mtext, 0, 1);
        // Assert
        verify(mtext, never()).appendCDATA(any(), anyString());
    }

    // Test with CDATA Content containing non-whitespace text
    @Test
    void testWithCDATAContentContainingNonWhitespaceText() {
        // Arrange
        Content cdataContent = createContentMock(CType.CDATA, "World");
        contentList.add(cdataContent);
        // Act
        walker.analyzeMultiText(mtext, 0, 1);
        // Assert
        verify(mtext).appendCDATA(eq(Trim.COMPACT), eq("World"));
    }

    // Test with EntityRef Content
    @Test
    void testWithEntityRefContent() {
        // Arrange
        Content entityRefContent = createContentMock(CType.EntityRef, "&entity;");
        contentList.add(entityRefContent);
        // Act
        walker.analyzeMultiText(mtext, 0, 1);
        // Assert
        verify(mtext).appendRaw(eq(entityRefContent));
    }

    // Test with a mix of Text, CDATA, and EntityRef Content
    @Test
    void testWithMixedContentTypes() {
        // Arrange
        Content textContent = createContentMock(CType.Text, "Hello");
        Content cdataContent = createContentMock(CType.CDATA, "World");
        Content entityRefContent = createContentMock(CType.EntityRef, "&entity;");
        contentList.addAll(Arrays.asList(textContent, cdataContent, entityRefContent));
        // Act
        walker.analyzeMultiText(mtext, 0, 3);
        // Assert
        verify(mtext).appendText(eq(Trim.COMPACT), eq("Hello"));
        verify(mtext).appendText(eq(Trim.NONE), eq(" "));
        verify(mtext).appendCDATA(eq(Trim.COMPACT), eq("World"));
        verify(mtext).appendText(eq(Trim.NONE), eq(" "));
        verify(mtext).appendRaw(eq(entityRefContent));
    }

    // Test with Text Content starting and ending with whitespace
    @Test
    void testWithTextContentStartingAndEndingWithWhitespace() {
        // Arrange
        Content textContent = createContentMock(CType.Text, "  Hello  ");
        contentList.add(textContent);
        // Act
        walker.analyzeMultiText(mtext, 0, 1);
        // Assert
        verify(mtext).appendText(eq(Trim.COMPACT), eq("  Hello  "));
    }

    // Test with multiple Text Contents with interspersed whitespace
    @Test
    void testWithMultipleTextContentsWithInterspersedWhitespace() {
        // Arrange
        Content textContent1 = createContentMock(CType.Text, "Hello");
        Content textContent2 = createContentMock(CType.Text, "   ");
        Content textContent3 = createContentMock(CType.Text, "World");
        contentList.addAll(Arrays.asList(textContent1, textContent2, textContent3));
        // Act
        walker.analyzeMultiText(mtext, 0, 3);
        // Assert
        verify(mtext).appendText(eq(Trim.COMPACT), eq("Hello"));
        verify(mtext).appendText(eq(Trim.NONE), eq(" "));
        verify(mtext).appendText(eq(Trim.COMPACT), eq("World"));
    }

    // Test with CDATA Content starting and ending with whitespace
    @Test
    void testWithCDATAContentStartingAndEndingWithWhitespace() {
        // Arrange
        Content cdataContent = createContentMock(CType.CDATA, "  World  ");
        contentList.add(cdataContent);
        // Act
        walker.analyzeMultiText(mtext, 0, 1);
        // Assert
        verify(mtext).appendCDATA(eq(Trim.COMPACT), eq("  World  "));
    }

    // Test with mixed Content types starting with whitespace
    @Test
    void testWithMixedContentTypesWithLeadingWhitespace() {
        // Arrange
        Content textContent = createContentMock(CType.Text, " ");
        Content cdataContent = createContentMock(CType.CDATA, "Hello");
        Content entityRefContent = createContentMock(CType.EntityRef, "&entity;");
        contentList.addAll(Arrays.asList(textContent, cdataContent, entityRefContent));
        // Act
        walker.analyzeMultiText(mtext, 0, 3);
        // Assert
        verify(mtext).appendText(eq(Trim.COMPACT), eq(" "));
        verify(mtext).appendCDATA(eq(Trim.COMPACT), eq("Hello"));
        verify(mtext).appendText(eq(Trim.NONE), eq(" "));
        verify(mtext).appendRaw(eq(entityRefContent));
    }

    // Test with Text Content containing special XML characters
    @Test
    void testWithTextContentContainingSpecialXMLCharacters() {
        // Arrange
        Content textContent = createContentMock(CType.Text, "<tag>");
        contentList.add(textContent);
        // Act
        walker.analyzeMultiText(mtext, 0, 1);
        // Assert
        verify(mtext).appendText(eq(Trim.COMPACT), eq("<tag>"));
    }

    // Test with no Content
    @Test
    void testWithNoContent() {
        // Act
        walker.analyzeMultiText(mtext, 0, 0);
        // Assert
        verify(mtext, never()).appendText(any(), anyString());
        verify(mtext, never()).appendCDATA(any(), anyString());
        verify(mtext, never()).appendRaw(any());
    }

    // Test with offset beyond the size of the Content list
    @Test
    void testWithOffsetBeyondListSize() {
        // Arrange
        Content textContent = createContentMock(CType.Text, "Hello");
        contentList.add(textContent);
        // Act & Assert
        assertThrows(IndexOutOfBoundsException.class, () -> walker.analyzeMultiText(mtext, 10, 1));
    }

    // Test with len extending beyond the size of the Content list
    @Test
    void testWithLenExtendingBeyondListSize() {
        // Arrange
        Content textContent = createContentMock(CType.Text, "Hello");
        contentList.add(textContent);
        // Act & Assert
        assertThrows(IndexOutOfBoundsException.class, () -> walker.analyzeMultiText(mtext, 0, 10));
    }

    // Test with null Content in the MultiText instance
    @Test
    void testWithNullContent() {
        // Arrange
        contentList.add(null);
        // Act & Assert
        assertThrows(NullPointerException.class, () -> walker.analyzeMultiText(mtext, 0, 1));
    }
}

```

Corresponding execution feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended

╷
├─ JUnit Jupiter ✔
│  └─ WalkerNORMALIZE_analyzeMultiText_Test ✔
│     ├─ testWithNullContent() ✔
│     ├─ testWithTextContentContainingNonWhitespaceText() ✘ Cannot invoke "org.jdom2.Content.getCType()" because "c" is null
│     ├─ testWithTextContentContainingSpecialXMLCharacters() ✘ Cannot invoke "org.jdom2.Content.getCType()" because "c" is null
│     ├─ testWithEntityRefContent() ✘ Cannot invoke "org.jdom2.Content.getCType()" because "c" is null
│     ├─ testWithTextContentContainingOnlyXMLWhitespace() ✘ Cannot invoke "org.jdom2.Content.getCType()" because "c" is null
│     ├─ testWithLenExtendingBeyondListSize() ✘ Unexpected exception type thrown, expected: <java.lang.IndexOutOfBoundsException> but was: <java.lang.NullPointerException>
│     ├─ testWithTextContentStartingAndEndingWithWhitespace() ✘ Cannot invoke "org.jdom2.Content.getCType()" because "c" is null
│     ├─ testWithNoContent() ✔
│     ├─ testWithCDATAContentContainingNonWhitespaceText() ✘ Cannot invoke "org.jdom2.Content.getCType()" because "c" is null
│     ├─ testWithOffsetBeyondListSize() ✘ 
│     │        Unnecessary stubbings detected.
│     │        Clean & maintainable test code requires zero unnecessary code.
│     │        Following stubbings are unnecessary (click to navigate to relevant line of code):
│     │          1. -> at org.jdom2.output.support.WalkerNORMALIZE_analyzeMultiText_Test.createContentMock(WalkerNORMALIZE_analyzeMultiText_Test.java:51)
│     │        Please remove unnecessary stubbings or use 'lenient' strictness. More info: javadoc for UnnecessaryStubbingException class.
│     ├─ testWithCDATAContentContainingOnlyXMLWhitespace() ✘ Cannot invoke "org.jdom2.Content.getCType()" because "c" is null
│     ├─ testWithCDATAContentStartingAndEndingWithWhitespace() ✘ Cannot invoke "org.jdom2.Content.getCType()" because "c" is null
│     ├─ testWithMixedContentTypesWithLeadingWhitespace() ✘ Cannot invoke "org.jdom2.Content.getCType()" because "c" is null
│     ├─ testWithMixedContentTypes() ✘ Cannot invoke "org.jdom2.Content.getCType()" because "c" is null
│     └─ testWithMultipleTextContentsWithInterspersedWhitespace() ✘ Cannot invoke "org.jdom2.Content.getCType()" because "c" is null
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Failures (13):
  JUnit Jupiter:WalkerNORMALIZE_analyzeMultiText_Test:testWithTextContentContainingNonWhitespaceText()
    MethodSource [className = 'org.jdom2.output.support.WalkerNORMALIZE_analyzeMultiText_Test', methodName = 'testWithTextContentContainingNonWhitespaceText', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "org.jdom2.Content.getCType()" because "c" is null
       org.jdom2.output.support.WalkerNORMALIZE.analyzeMultiText(WalkerNORMALIZE.java:105)
       org.jdom2.output.support.WalkerNORMALIZE_analyzeMultiText_Test.testWithTextContentContainingNonWhitespaceText(WalkerNORMALIZE_analyzeMultiText_Test.java:74)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:767)
       org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
       org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
       org.junit.jupiter.engine.extension.TimeoutExtension.intercept(TimeoutExtension.java:156)
       [...]
  JUnit Jupiter:WalkerNORMALIZE_analyzeMultiText_Test:testWithTextContentContainingSpecialXMLCharacters()
    MethodSource [className = 'org.jdom2.output.support.WalkerNORMALIZE_analyzeMultiText_Test', methodName = 'testWithTextContentContainingSpecialXMLCharacters', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "org.jdom2.Content.getCType()" because "c" is null
       org.jdom2.output.support.WalkerNORMALIZE.analyzeMultiText(WalkerNORMALIZE.java:105)
       org.jdom2.output.support.WalkerNORMALIZE_analyzeMultiText_Test.testWithTextContentContainingSpecialXMLCharacters(WalkerNORMALIZE_analyzeMultiText_Test.java:197)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:767)
       org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
       org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
       org.junit.jupiter.engine.extension.TimeoutExtension.intercept(TimeoutExtension.java:156)
       [...]
  JUnit Jupiter:WalkerNORMALIZE_analyzeMultiText_Test:testWithEntityRefContent()
    MethodSource [className = 'org.jdom2.output.support.WalkerNORMALIZE_analyzeMultiText_Test', methodName = 'testWithEntityRefContent', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "org.jdom2.Content.getCType()" because "c" is null
       org.jdom2.output.support.WalkerNORMALIZE.analyzeMultiText(WalkerNORMALIZE.java:105)
       org.jdom2.output.support.WalkerNORMALIZE_analyzeMultiText_Test.testWithEntityRefContent(WalkerNORMALIZE_analyzeMultiText_Test.java:110)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:767)
       org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
       org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
       org.junit.jupiter.engine.extension.TimeoutExtension.intercept(TimeoutExtension.java:156)
       [...]
  JUnit Jupiter:WalkerNORMALIZE_analyzeMultiText_Test:testWithTextContentContainingOnlyXMLWhitespace()
    MethodSource [className = 'org.jdom2.output.support.WalkerNORMALIZE_analyzeMultiText_Test', methodName = 'testWithTextContentContainingOnlyXMLWhitespace', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "org.jdom2.Content.getCType()" because "c" is null
       org.jdom2.output.support.WalkerNORMALIZE.analyzeMultiText(WalkerNORMALIZE.java:105)
       org.jdom2.output.support.WalkerNORMALIZE_analyzeMultiText_Test.testWithTextContentContainingOnlyXMLWhitespace(WalkerNORMALIZE_analyzeMultiText_Test.java:62)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:767)
       org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
       org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
       org.junit.jupiter.engine.extension.TimeoutExtension.intercept(TimeoutExtension.java:156)
       [...]
  JUnit Jupiter:WalkerNORMALIZE_analyzeMultiText_Test:testWithLenExtendingBeyondListSize()
    MethodSource [className = 'org.jdom2.output.support.WalkerNORMALIZE_analyzeMultiText_Test', methodName = 'testWithLenExtendingBeyondListSize', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Unexpected exception type thrown, expected: <java.lang.IndexOutOfBoundsException> but was: <java.lang.NullPointerException>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:67)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3128)
       org.jdom2.output.support.WalkerNORMALIZE_analyzeMultiText_Test.testWithLenExtendingBeyondListSize(WalkerNORMALIZE_analyzeMultiText_Test.java:230)
       [...]
     Caused by: java.lang.NullPointerException: Cannot invoke "org.jdom2.Content.getCType()" because "c" is null
       org.jdom2.output.support.WalkerNORMALIZE.analyzeMultiText(WalkerNORMALIZE.java:105)
       org.jdom2.output.support.WalkerNORMALIZE_analyzeMultiText_Test.lambda$testWithLenExtendingBeyondListSize$1(WalkerNORMALIZE_analyzeMultiText_Test.java:230)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:53)
       [...]
  JUnit Jupiter:WalkerNORMALIZE_analyzeMultiText_Test:testWithTextContentStartingAndEndingWithWhitespace()
    MethodSource [className = 'org.jdom2.output.support.WalkerNORMALIZE_analyzeMultiText_Test', methodName = 'testWithTextContentStartingAndEndingWithWhitespace', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "org.jdom2.Content.getCType()" because "c" is null
       org.jdom2.output.support.WalkerNORMALIZE.analyzeMultiText(WalkerNORMALIZE.java:105)
       org.jdom2.output.support.WalkerNORMALIZE_analyzeMultiText_Test.testWithTextContentStartingAndEndingWithWhitespace(WalkerNORMALIZE_analyzeMultiText_Test.java:140)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:767)
       org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
       org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
       org.junit.jupiter.engine.extension.TimeoutExtension.intercept(TimeoutExtension.java:156)
       [...]
  JUnit Jupiter:WalkerNORMALIZE_analyzeMultiText_Test:testWithCDATAContentContainingNonWhitespaceText()
    MethodSource [className = 'org.jdom2.output.support.WalkerNORMALIZE_analyzeMultiText_Test', methodName = 'testWithCDATAContentContainingNonWhitespaceText', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "org.jdom2.Content.getCType()" because "c" is null
       org.jdom2.output.support.WalkerNORMALIZE.analyzeMultiText(WalkerNORMALIZE.java:105)
       org.jdom2.output.support.WalkerNORMALIZE_analyzeMultiText_Test.testWithCDATAContentContainingNonWhitespaceText(WalkerNORMALIZE_analyzeMultiText_Test.java:98)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:767)
       org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
       org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
       org.junit.jupiter.engine.extension.TimeoutExtension.intercept(TimeoutExtension.java:156)
       [...]
  JUnit Jupiter:WalkerNORMALIZE_analyzeMultiText_Test:testWithOffsetBeyondListSize()
    MethodSource [className = 'org.jdom2.output.support.WalkerNORMALIZE_analyzeMultiText_Test', methodName = 'testWithOffsetBeyondListSize', methodParameterTypes = '']
    => org.mockito.exceptions.misusing.UnnecessaryStubbingException: 
Unnecessary stubbings detected.
Clean & maintainable test code requires zero unnecessary code.
Following stubbings are unnecessary (click to navigate to relevant line of code):
  1. -> at org.jdom2.output.support.WalkerNORMALIZE_analyzeMultiText_Test.createContentMock(WalkerNORMALIZE_analyzeMultiText_Test.java:51)
Please remove unnecessary stubbings or use 'lenient' strictness. More info: javadoc for UnnecessaryStubbingException class.
       org.mockito.junit.jupiter.MockitoExtension.afterEach(MockitoExtension.java:186)
       org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$invokeAfterEachCallbacks$13(TestMethodTestDescriptor.java:260)
       org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$invokeAllAfterMethodsOrCallbacks$14(TestMethodTestDescriptor.java:276)
       org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
       org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$invokeAllAfterMethodsOrCallbacks$15(TestMethodTestDescriptor.java:276)
       org.junit.platform.commons.util.CollectionUtils.forEachInReverseOrder(CollectionUtils.java:240)
       org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.invokeAllAfterMethodsOrCallbacks(TestMethodTestDescriptor.java:275)
       org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.invokeAfterEachCallbacks(TestMethodTestDescriptor.java:259)
       org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:144)
       org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:68)
       [...]
  JUnit Jupiter:WalkerNORMALIZE_analyzeMultiText_Test:testWithCDATAContentContainingOnlyXMLWhitespace()
    MethodSource [className = 'org.jdom2.output.support.WalkerNORMALIZE_analyzeMultiText_Test', methodName = 'testWithCDATAContentContainingOnlyXMLWhitespace', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "org.jdom2.Content.getCType()" because "c" is null
       org.jdom2.output.support.WalkerNORMALIZE.analyzeMultiText(WalkerNORMALIZE.java:105)
       org.jdom2.output.support.WalkerNORMALIZE_analyzeMultiText_Test.testWithCDATAContentContainingOnlyXMLWhitespace(WalkerNORMALIZE_analyzeMultiText_Test.java:86)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:767)
       org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
       org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
       org.junit.jupiter.engine.extension.TimeoutExtension.intercept(TimeoutExtension.java:156)
       [...]
  JUnit Jupiter:WalkerNORMALIZE_analyzeMultiText_Test:testWithCDATAContentStartingAndEndingWithWhitespace()
    MethodSource [className = 'org.jdom2.output.support.WalkerNORMALIZE_analyzeMultiText_Test', methodName = 'testWithCDATAContentStartingAndEndingWithWhitespace', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "org.jdom2.Content.getCType()" because "c" is null
       org.jdom2.output.support.WalkerNORMALIZE.analyzeMultiText(WalkerNORMALIZE.java:105)
       org.jdom2.output.support.WalkerNORMALIZE_analyzeMultiText_Test.testWithCDATAContentStartingAndEndingWithWhitespace(WalkerNORMALIZE_analyzeMultiText_Test.java:168)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:767)
       org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
       org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
       org.junit.jupiter.engine.extension.TimeoutExtension.intercept(TimeoutExtension.java:156)
       [...]
  JUnit Jupiter:WalkerNORMALIZE_analyzeMultiText_Test:testWithMixedContentTypesWithLeadingWhitespace()
    MethodSource [className = 'org.jdom2.output.support.WalkerNORMALIZE_analyzeMultiText_Test', methodName = 'testWithMixedContentTypesWithLeadingWhitespace', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "org.jdom2.Content.getCType()" because "c" is null
       org.jdom2.output.support.WalkerNORMALIZE.analyzeMultiText(WalkerNORMALIZE.java:105)
       org.jdom2.output.support.WalkerNORMALIZE_analyzeMultiText_Test.testWithMixedContentTypesWithLeadingWhitespace(WalkerNORMALIZE_analyzeMultiText_Test.java:182)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:767)
       org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
       org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
       org.junit.jupiter.engine.extension.TimeoutExtension.intercept(TimeoutExtension.java:156)
       [...]
  JUnit Jupiter:WalkerNORMALIZE_analyzeMultiText_Test:testWithMixedContentTypes()
    MethodSource [className = 'org.jdom2.output.support.WalkerNORMALIZE_analyzeMultiText_Test', methodName = 'testWithMixedContentTypes', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "org.jdom2.Content.getCType()" because "c" is null
       org.jdom2.output.support.WalkerNORMALIZE.analyzeMultiText(WalkerNORMALIZE.java:105)
       org.jdom2.output.support.WalkerNORMALIZE_analyzeMultiText_Test.testWithMixedContentTypes(WalkerNORMALIZE_analyzeMultiText_Test.java:124)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:767)
       org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
       org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
       org.junit.jupiter.engine.extension.TimeoutExtension.intercept(TimeoutExtension.java:156)
       [...]
  JUnit Jupiter:WalkerNORMALIZE_analyzeMultiText_Test:testWithMultipleTextContentsWithInterspersedWhitespace()
    MethodSource [className = 'org.jdom2.output.support.WalkerNORMALIZE_analyzeMultiText_Test', methodName = 'testWithMultipleTextContentsWithInterspersedWhitespace', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "org.jdom2.Content.getCType()" because "c" is null
       org.jdom2.output.support.WalkerNORMALIZE.analyzeMultiText(WalkerNORMALIZE.java:105)
       org.jdom2.output.support.WalkerNORMALIZE_analyzeMultiText_Test.testWithMultipleTextContentsWithInterspersedWhitespace(WalkerNORMALIZE_analyzeMultiText_Test.java:154)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:767)
       org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
       org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
       org.junit.jupiter.engine.extension.TimeoutExtension.intercept(TimeoutExtension.java:156)
       [...]

Test run finished after 564 ms
[         4 containers found      ]
[         0 containers skipped    ]
[         4 containers started    ]
[         0 containers aborted    ]
[         4 containers successful ]
[         0 containers failed     ]
[        15 tests found           ]
[         0 tests skipped         ]
[        15 tests started         ]
[         0 tests aborted         ]
[         2 tests successful      ]
[        13 tests failed          ]


```


And you can use the following related information to assist you in your analysis and correction:

api document of class org.jdom2.output.support.WalkerNORMALIZE: 

 * This Walker implementation will produce trimmed text content.
 * 
 * @author Rolf Lear
 *
 

dependent classes: 
class `org.jdom2.output.support.AbstractFormattedWalker.MultiText`:
	
class `org.jdom2.output.support.AbstractFormattedWalker`:
	related functions:
		method `private final boolean isTextLike(Content)` returns `boolean`, related with `org.jdom2.output.support.WalkerNORMALIZE.analyzeMultiText(MultiText, int, int)`;
class `org.jdom2.Content.CType`:
	dependent fields:
		org.jdom2.Content.CType org.jdom2.Content.CType.CDATA;
		org.jdom2.Content.CType org.jdom2.Content.CType.EntityRef;
		org.jdom2.Content.CType org.jdom2.Content.CType.Text;
	
class `org.jdom2.output.support.AbstractFormattedWalker.Trim`:
	dependent fields:
		org.jdom2.output.support.AbstractFormattedWalker.Trim org.jdom2.output.support.AbstractFormattedWalker.Trim.NONE;
		org.jdom2.output.support.AbstractFormattedWalker.Trim org.jdom2.output.support.AbstractFormattedWalker.Trim.COMPACT;
	
class `org.jdom2.output.support.WalkerTRIM_FULL_WHITE`:
	related functions:
		method `protected void analyzeMultiText(MultiText, int, int)` returns `void`, related with `org.jdom2.output.support.WalkerNORMALIZE.analyzeMultiText(MultiText, int, int)`;
class `org.jdom2.output.support.WalkerTRIM`:
	related functions:
		method `protected void analyzeMultiText(MultiText, int, int)` returns `void`, related with `org.jdom2.output.support.WalkerNORMALIZE.analyzeMultiText(MultiText, int, int)`;
class `org.jdom2.output.support.AbstractDOMOutputProcessor`:
	related functions:
		method `protected org.w3c.dom.Node helperContentDispatcher(FormatStack, NamespaceStack, org.w3c.dom.Document, Content)` returns `org.w3c.dom.Node`, related with `org.jdom2.output.support.WalkerNORMALIZE.analyzeMultiText(MultiText, int, int)`, api document: 
	 * This method contains code which is reused in a number of places. It
	 * simply determines what content is passed in, and dispatches it to the
	 * correct print* method.
	 * 
	 * @param fstack
	 *        The current FormatStack
	 * @param nstack
	 *        the NamespaceStack
	 * @param basedoc
	 *        The org.w3c.dom.Document for creating DOM Nodes
	 * @param content
	 *        The content to dispatch
	 * @return the input JDOM Content converted to a DOM Node.
	 ;
class `org.jdom2.output.support.AbstractSAXOutputProcessor`:
	related functions:
		method `protected void printContent(SAXTarget, FormatStack, NamespaceStack, Walker)` returns `void`, related with `org.jdom2.output.support.WalkerNORMALIZE.analyzeMultiText(MultiText, int, int)`, api document: 
	 * This will handle printing of a List of {@link Content}.
	 * <p>
	 * It relies on the appropriate Walker to get the formatting right.
	 * 
	 * @param out
	 *        <code>SAXTarget</code> to use.
	 * @param fstack
	 *        the FormatStack
	 * @param nstack
	 *        the NamespaceStack
	 * @param walker
	 *        <code>Waker</code> of <code>Content</code> to write.
	 * @throws SAXException
	 *         if the destination SAXTarget fails
	 ;
class `org.jdom2.output.support.AbstractXMLOutputProcessor`:
	related functions:
		method `protected void printContent(Writer, FormatStack, NamespaceStack, Walker)` returns `void`, related with `org.jdom2.output.support.WalkerNORMALIZE.analyzeMultiText(MultiText, int, int)`, api document: 
	 * This will handle printing of a List of {@link Content}.
	 * <p>
	 * The list of Content is basically processed as one of three types of
	 * content
	 * <ol>
	 * <li>Consecutive text-type (Text, CDATA, and EntityRef) content
	 * <li>Stand-alone text-type content
	 * <li>Non-text-type content.
	 * </ol>
	 * Although the code looks complex, the theory is conceptually simple:
	 * <ol>
	 * <li>identify one of the three types (consecutive, stand-alone, non-text)
	 * <li>do indent if any is specified.
	 * <li>send the type to the respective print* handler (e.g.
	 * {@link #printCDATA(Writer, FormatStack, CDATA)}, or
	 * {@link #printComment(Writer, FormatStack, Comment)},
	 * <li>do a newline if one is specified.
	 * <li>loop back to 1. until there's no more content to process.
	 * </ol>
	 * 
	 * @param out
	 *        <code>Writer</code> to use.
	 * @param fstack
	 *        the FormatStack
	 * @param nstack
	 *        the NamespaceStack
	 * @param walker
	 *        {@link Walker} of <code>Content</code> to write.
	 * @throws IOException
	 *         if the destination Writer fails
	 ;
class `org.jdom2.output.support.AbstractStAXEventProcessor`:
	related functions:
		method `protected void printContent(XMLEventConsumer, FormatStack, NamespaceStack, XMLEventFactory, Walker)` returns `void`, related with `org.jdom2.output.support.WalkerNORMALIZE.analyzeMultiText(MultiText, int, int)`, api document: 
	 * This will handle printing of a List of {@link Content}.
	 * <p>
	 * 
	 * @param out
	 *        <code>XMLEventConsumer</code> to use.
	 * @param fstack
	 *        the FormatStack
	 * @param nstack
	 *        the NamespaceStack
	 * @param eventfactory
	 * 		  The XMLEventFactory for creating XMLEvents 
	 * @param walker
	 *        <code>Walker</code> of <code>Content</code> to write.
	 * @throws XMLStreamException
	 *         if the destination XMLEventConsumer fails
	 ;
class `org.jdom2.output.SAXOutputter`:
	related functions:
		method `public void outputFragment(Content)` returns `void`, related with `org.jdom2.output.support.WalkerNORMALIZE.analyzeMultiText(MultiText, int, int)`, api document: 
	 * This will output a single JDOM nodes as a fragment of an XML document,
	 * firing off the SAX events that have been registered.
	 * <p>
	 * <strong>Warning</strong>: This method does not call the
	 * {@link ContentHandler#setDocumentLocator},
	 * {@link ContentHandler#startDocument} and
	 * {@link ContentHandler#endDocument} callbacks on the
	 * {@link #setContentHandler ContentHandler}. The user shall invoke these
	 * methods directly prior/after outputting the document fragments.
	 * </p>
	 * 
	 * @param node
	 *        the <code>Content</code> node to output.
	 * @throws JDOMException
	 *         if any error occurred.
	 * @see #outputFragment(java.util.List)
	 ;



Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 
