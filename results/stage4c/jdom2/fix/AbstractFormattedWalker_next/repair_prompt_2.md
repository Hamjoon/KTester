


You are an experienced Java developer tasked with fixing a piece of code that contains execution errors. Your task is to resolve these issues after analyzing the code and its execution feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any logical errors.
2. Analyze the execution feedback to understand the specific errors and their locations within the code.
3. Apply necessary corrections to the code based on the feedback, and output the compelete correct code.
The Java code you need to fix is provided below:
```java
package org.jdom2.output.support;

import org.jdom2.Content;
import org.jdom2.output.Format;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.jdom2.output.EscapeStrategy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class AbstractFormattedWalker_next_Test {

    private AbstractFormattedWalker walker;

    private List<Content> contentList;

    private FormatStack formatStack;

    private EscapeStrategy escapeStrategy;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the necessary objects and dependencies
        contentList = new ArrayList<>();
        Format format = mock(Format.class);
        escapeStrategy = mock(EscapeStrategy.class);
        when(format.getEscapeStrategy()).thenReturn(escapeStrategy);
        formatStack = new FormatStack(format);
        walker = new AbstractFormattedWalker(contentList, formatStack, true) {

            @Override
            protected void analyzeMultiText(MultiText mtext, int offset, int len) {
                // Implementation for testing purposes
            }
        };
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup after each test
        contentList.clear();
    }

    @Test
    void testNextWithNoContent() {
        // Given
        assertThrows(NoSuchElementException.class, () -> {
            // When
            walker.next();
        });
    }

    @Test
    void testNextWithContent() {
        // Given
        Content content = mock(Content.class);
        contentList.add(content);
        // When
        Content result = walker.next();
        // Then
        assertNotNull(result);
        assertEquals(content, result);
    }

    @Test
    void testNextWithMultitextNotNull() throws Exception {
        // Given
        Field multitextField = AbstractFormattedWalker.class.getDeclaredField("multitext");
        multitextField.setAccessible(true);
        Field mtsizeField = AbstractFormattedWalker.class.getDeclaredField("mtsize");
        mtsizeField.setAccessible(true);
        Field mtposField = AbstractFormattedWalker.class.getDeclaredField("mtpos");
        mtposField.setAccessible(true);
        Field mttextField = AbstractFormattedWalker.class.getDeclaredField("mttext");
        mttextField.setAccessible(true);
        Object multitext = multitextField.get(walker);
        multitextField.set(walker, multitext);
        mtsizeField.setInt(walker, 2);
        mtposField.setInt(walker, 0);
        String[] mttext = (String[]) mttextField.get(walker);
        mttext[0] = null;
        // When
        Content result = walker.next();
        // Then
        // Expecting null indicating text content
        assertNull(result);
    }

    @Test
    void testNextWithRecalculatedMultitext() throws Exception {
        // Given
        Field pendingmtField = AbstractFormattedWalker.class.getDeclaredField("pendingmt");
        pendingmtField.setAccessible(true);
        Field mtwasescapeField = AbstractFormattedWalker.class.getDeclaredField("mtwasescape");
        mtwasescapeField.setAccessible(true);
        Object pendingmt = pendingmtField.get(walker);
        pendingmtField.set(walker, pendingmt);
        mtwasescapeField.set(walker, Boolean.FALSE);
        when(formatStack.getEscapeOutput()).thenReturn(true);
        // When
        Content result = walker.next();
        // Then
        // Expecting a Content object
        assertNotNull(result);
    }

    @Test
    void testNextWithPendingNonTextContent() throws Exception {
        // Given
        Content content = mock(Content.class);
        Field pendingField = AbstractFormattedWalker.class.getDeclaredField("pending");
        pendingField.setAccessible(true);
        pendingField.set(walker, content);
        Method isTextLikeMethod = AbstractFormattedWalker.class.getDeclaredMethod("isTextLike", Content.class);
        isTextLikeMethod.setAccessible(true);
        when((Boolean) isTextLikeMethod.invoke(walker, content)).thenReturn(false);
        // When
        Content result = walker.next();
        // Then
        // Expecting a Content object
        assertEquals(content, result);
    }

    @Test
    void testNextWithPendingTextContentAndMtsizeGreaterThanZero() throws Exception {
        // Given
        Content content = mock(Content.class);
        Field pendingField = AbstractFormattedWalker.class.getDeclaredField("pending");
        pendingField.setAccessible(true);
        pendingField.set(walker, content);
        Method isTextLikeMethod = AbstractFormattedWalker.class.getDeclaredMethod("isTextLike", Content.class);
        isTextLikeMethod.setAccessible(true);
        when((Boolean) isTextLikeMethod.invoke(walker, content)).thenReturn(true);
        Field mtsizeField = AbstractFormattedWalker.class.getDeclaredField("mtsize");
        mtsizeField.setAccessible(true);
        mtsizeField.setInt(walker, 1);
        // When
        Content result = walker.next();
        // Then
        // Expecting a Content object
        assertNotNull(result);
    }

    @Test
    void testNextWithPendingTextContentAndMtsizeZeroAndNewlineIndentNotNull() throws Exception {
        // Given
        Content content = mock(Content.class);
        Field pendingField = AbstractFormattedWalker.class.getDeclaredField("pending");
        pendingField.setAccessible(true);
        pendingField.set(walker, content);
        Method isTextLikeMethod = AbstractFormattedWalker.class.getDeclaredMethod("isTextLike", Content.class);
        isTextLikeMethod.setAccessible(true);
        when((Boolean) isTextLikeMethod.invoke(walker, content)).thenReturn(true);
        Field mtsizeField = AbstractFormattedWalker.class.getDeclaredField("mtsize");
        mtsizeField.setAccessible(true);
        mtsizeField.setInt(walker, 0);
        Field newlineindentField = AbstractFormattedWalker.class.getDeclaredField("newlineindent");
        newlineindentField.setAccessible(true);
        newlineindentField.set(walker, "  ");
        // When
        Content result = walker.next();
        // Then
        // Expecting a Content object
        assertNotNull(result);
    }

    @Test
    void testNextWithPendingNull() throws Exception {
        // Given
        Field pendingField = AbstractFormattedWalker.class.getDeclaredField("pending");
        pendingField.setAccessible(true);
        pendingField.set(walker, null);
        // When
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            walker.next();
        });
        // Then
        assertEquals("Cannot walk off end of Content", exception.getMessage());
    }
}

```

Corresponding execution feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended

╷
├─ JUnit Jupiter ✔
│  └─ AbstractFormattedWalker_next_Test ✔
│     ├─ testNextWithRecalculatedMultitext() ✘ Cannot walk off end of Content
│     ├─ testNextWithPendingNull() ✔
│     ├─ testNextWithContent() ✘ Cannot walk off end of Content
│     ├─ testNextWithPendingNonTextContent() ✘ Cannot invoke "org.jdom2.Content$CType.ordinal()" because the return value of "org.jdom2.Content.getCType()" is null
│     ├─ testNextWithMultitextNotNull() ✘ Cannot walk off end of Content
│     ├─ testNextWithPendingTextContentAndMtsizeGreaterThanZero() ✘ Cannot invoke "org.jdom2.Content$CType.ordinal()" because the return value of "org.jdom2.Content.getCType()" is null
│     ├─ testNextWithNoContent() ✔
│     └─ testNextWithPendingTextContentAndMtsizeZeroAndNewlineIndentNotNull() ✘ Cannot invoke "org.jdom2.Content$CType.ordinal()" because the return value of "org.jdom2.Content.getCType()" is null
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Failures (6):
  JUnit Jupiter:AbstractFormattedWalker_next_Test:testNextWithRecalculatedMultitext()
    MethodSource [className = 'org.jdom2.output.support.AbstractFormattedWalker_next_Test', methodName = 'testNextWithRecalculatedMultitext', methodParameterTypes = '']
    => java.util.NoSuchElementException: Cannot walk off end of Content
       org.jdom2.output.support.AbstractFormattedWalker.next(AbstractFormattedWalker.java:405)
       org.jdom2.output.support.AbstractFormattedWalker_next_Test.testNextWithRecalculatedMultitext(AbstractFormattedWalker_next_Test.java:109)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:767)
       org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
       org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
       org.junit.jupiter.engine.extension.TimeoutExtension.intercept(TimeoutExtension.java:156)
       [...]
  JUnit Jupiter:AbstractFormattedWalker_next_Test:testNextWithContent()
    MethodSource [className = 'org.jdom2.output.support.AbstractFormattedWalker_next_Test', methodName = 'testNextWithContent', methodParameterTypes = '']
    => java.util.NoSuchElementException: Cannot walk off end of Content
       org.jdom2.output.support.AbstractFormattedWalker.next(AbstractFormattedWalker.java:405)
       org.jdom2.output.support.AbstractFormattedWalker_next_Test.testNextWithContent(AbstractFormattedWalker_next_Test.java:67)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:767)
       org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
       org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
       org.junit.jupiter.engine.extension.TimeoutExtension.intercept(TimeoutExtension.java:156)
       [...]
  JUnit Jupiter:AbstractFormattedWalker_next_Test:testNextWithPendingNonTextContent()
    MethodSource [className = 'org.jdom2.output.support.AbstractFormattedWalker_next_Test', methodName = 'testNextWithPendingNonTextContent', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "org.jdom2.Content$CType.ordinal()" because the return value of "org.jdom2.Content.getCType()" is null
       org.jdom2.output.support.AbstractFormattedWalker.isTextLike(AbstractFormattedWalker.java:591)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.jdom2.output.support.AbstractFormattedWalker_next_Test.testNextWithPendingNonTextContent(AbstractFormattedWalker_next_Test.java:124)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       [...]
  JUnit Jupiter:AbstractFormattedWalker_next_Test:testNextWithMultitextNotNull()
    MethodSource [className = 'org.jdom2.output.support.AbstractFormattedWalker_next_Test', methodName = 'testNextWithMultitextNotNull', methodParameterTypes = '']
    => java.util.NoSuchElementException: Cannot walk off end of Content
       org.jdom2.output.support.AbstractFormattedWalker.next(AbstractFormattedWalker.java:405)
       org.jdom2.output.support.AbstractFormattedWalker_next_Test.testNextWithMultitextNotNull(AbstractFormattedWalker_next_Test.java:91)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:767)
       org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
       org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
       org.junit.jupiter.engine.extension.TimeoutExtension.intercept(TimeoutExtension.java:156)
       [...]
  JUnit Jupiter:AbstractFormattedWalker_next_Test:testNextWithPendingTextContentAndMtsizeGreaterThanZero()
    MethodSource [className = 'org.jdom2.output.support.AbstractFormattedWalker_next_Test', methodName = 'testNextWithPendingTextContentAndMtsizeGreaterThanZero', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "org.jdom2.Content$CType.ordinal()" because the return value of "org.jdom2.Content.getCType()" is null
       org.jdom2.output.support.AbstractFormattedWalker.isTextLike(AbstractFormattedWalker.java:591)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.jdom2.output.support.AbstractFormattedWalker_next_Test.testNextWithPendingTextContentAndMtsizeGreaterThanZero(AbstractFormattedWalker_next_Test.java:141)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       [...]
  JUnit Jupiter:AbstractFormattedWalker_next_Test:testNextWithPendingTextContentAndMtsizeZeroAndNewlineIndentNotNull()
    MethodSource [className = 'org.jdom2.output.support.AbstractFormattedWalker_next_Test', methodName = 'testNextWithPendingTextContentAndMtsizeZeroAndNewlineIndentNotNull', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot invoke "org.jdom2.Content$CType.ordinal()" because the return value of "org.jdom2.Content.getCType()" is null
       org.jdom2.output.support.AbstractFormattedWalker.isTextLike(AbstractFormattedWalker.java:591)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.jdom2.output.support.AbstractFormattedWalker_next_Test.testNextWithPendingTextContentAndMtsizeZeroAndNewlineIndentNotNull(AbstractFormattedWalker_next_Test.java:161)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       [...]

Test run finished after 492 ms
[         4 containers found      ]
[         0 containers skipped    ]
[         4 containers started    ]
[         0 containers aborted    ]
[         4 containers successful ]
[         0 containers failed     ]
[         8 tests found           ]
[         0 tests skipped         ]
[         8 tests started         ]
[         0 tests aborted         ]
[         2 tests successful      ]
[         6 tests failed          ]


```


And you can use the following related information to assist you in your analysis and correction:

api document of class org.jdom2.output.support.AbstractFormattedWalker: 

 * This Walker implementation walks a list of Content in a Formatted form of
 * some sort.
 * <p>
 * The JDOM content can be loosely categorised in to 'Text-like' content
 * (consisting of Text, CDATA, and EntityRef), and everything else. This
 * distinction is significant for for this class and it's sub-classes.
 * <p>
 * There will be text manipulation, and some (but not necessarily 
 * all) Text-like content will be returned as text() instead of next().
 * <p>
 * The trick in this class is that it deals with the regular content, and
 * delegates the Text-like content to the sub-classes.
 * <p>
 * Subclasses are tasked with analysing chunks of Text-like content in the
 * {@link #analyzeMultiText(MultiText, int, int)}  method. The subclasses are
 * responsible for adding the relevant text content to the suppliedMultiText
 * instance in such a way as to result in the correct format.
 * <p>
 * The Subclass needs to concern itself with only the text portion because this
 * abstract class will ensure the Text-like content is appropriately indented.
 * 
 * @author Rolf Lear
 

return type: 
org.jdom2.Content

dependent classes: 
class `org.jdom2.output.support.AbstractFormattedWalker`:
	dependent fields:
		int org.jdom2.output.support.AbstractFormattedWalker.mtpos;
		java.lang.Boolean org.jdom2.output.support.AbstractFormattedWalker.mtwasescape;
		org.jdom2.Content org.jdom2.output.support.AbstractFormattedWalker.pending;
		java.lang.String[] org.jdom2.output.support.AbstractFormattedWalker.mttext;
		org.jdom2.Content[] org.jdom2.output.support.AbstractFormattedWalker.mtdata;
		org.jdom2.output.support.AbstractFormattedWalker.MultiText org.jdom2.output.support.AbstractFormattedWalker.multitext;
		org.jdom2.output.support.AbstractFormattedWalker.MultiText org.jdom2.output.support.AbstractFormattedWalker.pendingmt;
		int org.jdom2.output.support.AbstractFormattedWalker.mtsize;
		int org.jdom2.output.support.AbstractFormattedWalker.mtsourcesize;
		boolean org.jdom2.output.support.AbstractFormattedWalker.hasnext;
		org.jdom2.output.support.FormatStack org.jdom2.output.support.AbstractFormattedWalker.fstack;
		java.util.Iterator<? extends org.jdom2.Content> org.jdom2.output.support.AbstractFormattedWalker.content;
		org.jdom2.output.support.AbstractFormattedWalker.MultiText org.jdom2.output.support.AbstractFormattedWalker.holdingmt;
		java.lang.String org.jdom2.output.support.AbstractFormattedWalker.newlineindent;
	related functions:
		method `public final boolean isCDATA()` returns `boolean`, related with `org.jdom2.output.support.AbstractFormattedWalker.next()`;
		method `public final String text()` returns `java.lang.String`, related with `org.jdom2.output.support.AbstractFormattedWalker.next()`;
		method `private void resetMultiText()` returns `void`, related with `org.jdom2.output.support.AbstractFormattedWalker.next()`;
		method `private final MultiText buildMultiText(boolean)` returns `org.jdom2.output.support.AbstractFormattedWalker.MultiText`, related with `org.jdom2.output.support.AbstractFormattedWalker.next()`, api document: 
	 * This method was changed in 2.0.5
	 * It now is only called when building the content of the variable pendingmt
	 * This is important, because only pendingmt can be referenced when analyzing
	 * the MultiText content.
	 * @param first
	 * @return The updated MultiText containing the correct sequence of Text-like content
	 ;
class `org.jdom2.Element`:
	related functions:
		method `public Element getChild(String, Namespace)` returns `org.jdom2.Element`, related with `org.jdom2.output.support.AbstractFormattedWalker.next()`, api document: 
	 * This returns the first child element within this element with the
	 * given local name and belonging to the given namespace.
	 * If no elements exist for the specified name and namespace, null is
	 * returned.
	 *
	 * @param cname local name of child element to match
	 * @param ns <code>Namespace</code> to search within. A null implies Namespace.NO_NAMESPACE.
	 * @return the first matching child element, or null if not found
	 ;
class `org.jdom2.DescendantIterator`:
	related functions:
		method `public Content next()` returns `org.jdom2.Content`, related with `org.jdom2.output.support.AbstractFormattedWalker.next()`, api document: 
	 * Returns the next {@link Content} descendant.
	 *
	 * @return the next descendant
	 ;



Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 
