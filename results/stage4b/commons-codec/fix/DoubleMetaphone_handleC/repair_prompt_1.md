


You are an experienced Java developer tasked with fixing a piece of code that contains execution errors. Your task is to resolve these issues after analyzing the code and its execution feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any logical errors.
2. Analyze the execution feedback to understand the specific errors and their locations within the code.
3. Apply necessary corrections to the code based on the feedback, and output the compelete correct code.
The Java code you need to fix is provided below:
```java
package org.apache.commons.codec.language;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DoubleMetaphone_handleC_Test {

    private DoubleMetaphone doubleMetaphone;

    private DoubleMetaphone.DoubleMetaphoneResult result;

    @BeforeEach
    void setupBeforeEach() {
        doubleMetaphone = new DoubleMetaphone();
        result = doubleMetaphone.new DoubleMetaphoneResult(doubleMetaphone.getMaxCodeLen());
    }

    private int invokeHandleC(String value, DoubleMetaphone.DoubleMetaphoneResult result, int index) {
        try {
            // Use reflection to access the private method
            var method = DoubleMetaphone.class.getDeclaredMethod("handleC", String.class, DoubleMetaphone.DoubleMetaphoneResult.class, int.class);
            method.setAccessible(true);
            return (int) method.invoke(doubleMetaphone, value, result, index);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testHandleC_withValidInput() {
        // Given
        String input = "CIC";
        int index = 0;
        // When
        // Use reflection to invoke
        index = invokeHandleC(input, result, index);
        // Then
        assertEquals('S', result.getPrimary().charAt(0));
        assertEquals(2, index);
    }

    @Test
    void testHandleC_withSpecialCase() {
        // Given
        String input = "CAESAR";
        int index = 0;
        // When
        // Use reflection to invoke
        index = invokeHandleC(input, result, index);
        // Then
        assertEquals('S', result.getPrimary().charAt(0));
        assertEquals(2, index);
    }

    @Test
    void testHandleC_withDoubleCC() {
        // Given
        String input = "ACCIDENT";
        int index = 0;
        // When
        // Use reflection to invoke
        index = invokeHandleC(input, result, index);
        // Then
        assertEquals('K', result.getPrimary().charAt(0));
        assertEquals(2, index);
    }

    @Test
    void testHandleC_withCaesar() {
        // Given
        String input = "CAESAR";
        int index = 0;
        // When
        // Use reflection to invoke
        index = invokeHandleC(input, result, index);
        // Then
        assertEquals('S', result.getPrimary().charAt(0));
        assertEquals(2, index);
    }

    @Test
    void testHandleC_withCzerny() {
        // Given
        String input = "Czerny";
        int index = 0;
        // When
        // Use reflection to invoke
        index = invokeHandleC(input, result, index);
        // Then
        assertEquals('S', result.getPrimary().charAt(0));
        assertEquals('X', result.getAlternate().charAt(0));
        assertEquals(2, index);
    }

    @Test
    void testHandleC_withFocaccia() {
        // Given
        String input = "focaccia";
        int index = 0;
        // When
        // Use reflection to invoke
        index = invokeHandleC(input, result, index);
        // Then
        assertEquals('X', result.getPrimary().charAt(0));
        assertEquals(3, index);
    }

    @Test
    void testHandleC_withMcClelland() {
        // Given
        String input = "McClelland";
        int index = 1;
        // When
        // Use reflection to invoke
        index = invokeHandleC(input, result, index);
        // Then
        assertEquals(1, index);
    }

    @Test
    void testHandleC_withCIG() {
        // Given
        String input = "CIG";
        int index = 0;
        // When
        // Use reflection to invoke
        index = invokeHandleC(input, result, index);
        // Then
        assertEquals('S', result.getPrimary().charAt(0));
        assertEquals(2, index);
    }

    @Test
    void testHandleC_withSingleC() {
        // Given
        String input = "C";
        int index = 0;
        // When
        // Use reflection to invoke
        index = invokeHandleC(input, result, index);
        // Then
        assertEquals('K', result.getPrimary().charAt(0));
        assertEquals(1, index);
    }

    @Test
    void testHandleC_withEmptyString() {
        // Given
        String input = "";
        int index = 0;
        // When & Then
        assertThrows(IndexOutOfBoundsException.class, () -> {
            invokeHandleC(input, result, index);
        });
    }

    @Test
    void testHandleC_withNullInput() {
        // Given
        String input = null;
        int index = 0;
        // When & Then
        assertThrows(NullPointerException.class, () -> {
            invokeHandleC(input, result, index);
        });
    }

    @Test
    void testHandleC_withZeroMaxLength() {
        // Given
        String input = "C";
        result = doubleMetaphone.new DoubleMetaphoneResult(0);
        int index = 0;
        // When & Then
        assertThrows(StringIndexOutOfBoundsException.class, () -> {
            invokeHandleC(input, result, index);
        });
    }
}

```

Corresponding execution feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended

╷
├─ JUnit Jupiter ✔
│  └─ DoubleMetaphone_handleC_Test ✔
│     ├─ testHandleC_withValidInput() ✔
│     ├─ testHandleC_withZeroMaxLength() ✘ Expected java.lang.StringIndexOutOfBoundsException to be thrown, but nothing was thrown.
│     ├─ testHandleC_withDoubleCC() ✔
│     ├─ testHandleC_withNullInput() ✘ Unexpected exception type thrown, expected: <java.lang.NullPointerException> but was: <java.lang.RuntimeException>
│     ├─ testHandleC_withSpecialCase() ✔
│     ├─ testHandleC_withCaesar() ✔
│     ├─ testHandleC_withCzerny() ✘ expected: <S> but was: <K>
│     ├─ testHandleC_withMcClelland() ✘ expected: <1> but was: <3>
│     ├─ testHandleC_withSingleC() ✔
│     ├─ testHandleC_withCIG() ✔
│     ├─ testHandleC_withEmptyString() ✘ Expected java.lang.IndexOutOfBoundsException to be thrown, but nothing was thrown.
│     └─ testHandleC_withFocaccia() ✘ expected: <X> but was: <K>
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Failures (6):
  JUnit Jupiter:DoubleMetaphone_handleC_Test:testHandleC_withZeroMaxLength()
    MethodSource [className = 'org.apache.commons.codec.language.DoubleMetaphone_handleC_Test', methodName = 'testHandleC_withZeroMaxLength', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Expected java.lang.StringIndexOutOfBoundsException to be thrown, but nothing was thrown.
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:73)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3115)
       org.apache.commons.codec.language.DoubleMetaphone_handleC_Test.testHandleC_withZeroMaxLength(DoubleMetaphone_handleC_Test.java:180)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:728)
       [...]
  JUnit Jupiter:DoubleMetaphone_handleC_Test:testHandleC_withNullInput()
    MethodSource [className = 'org.apache.commons.codec.language.DoubleMetaphone_handleC_Test', methodName = 'testHandleC_withNullInput', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Unexpected exception type thrown, expected: <java.lang.NullPointerException> but was: <java.lang.RuntimeException>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:67)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3115)
       org.apache.commons.codec.language.DoubleMetaphone_handleC_Test.testHandleC_withNullInput(DoubleMetaphone_handleC_Test.java:168)
       [...]
     Caused by: java.lang.RuntimeException: java.lang.reflect.InvocationTargetException
       org.apache.commons.codec.language.DoubleMetaphone_handleC_Test.invokeHandleC(DoubleMetaphone_handleC_Test.java:30)
       org.apache.commons.codec.language.DoubleMetaphone_handleC_Test.lambda$testHandleC_withNullInput$1(DoubleMetaphone_handleC_Test.java:169)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:53)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.apache.commons.codec.language.DoubleMetaphone_handleC_Test.invokeHandleC(DoubleMetaphone_handleC_Test.java:28)
       [...]
     Caused by: java.lang.NullPointerException: Cannot invoke "String.length()" because "value" is null
       org.apache.commons.codec.language.DoubleMetaphone.contains(DoubleMetaphone.java:140)
       org.apache.commons.codec.language.DoubleMetaphone.conditionC0(DoubleMetaphone.java:188)
       org.apache.commons.codec.language.DoubleMetaphone.handleC(DoubleMetaphone.java:429)
       [...]
  JUnit Jupiter:DoubleMetaphone_handleC_Test:testHandleC_withCzerny()
    MethodSource [className = 'org.apache.commons.codec.language.DoubleMetaphone_handleC_Test', methodName = 'testHandleC_withCzerny', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <S> but was: <K>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:54)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:49)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1041)
       org.apache.commons.codec.language.DoubleMetaphone_handleC_Test.testHandleC_withCzerny(DoubleMetaphone_handleC_Test.java:95)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:DoubleMetaphone_handleC_Test:testHandleC_withMcClelland()
    MethodSource [className = 'org.apache.commons.codec.language.DoubleMetaphone_handleC_Test', methodName = 'testHandleC_withMcClelland', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <1> but was: <3>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:150)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:145)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:531)
       org.apache.commons.codec.language.DoubleMetaphone_handleC_Test.testHandleC_withMcClelland(DoubleMetaphone_handleC_Test.java:122)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:DoubleMetaphone_handleC_Test:testHandleC_withEmptyString()
    MethodSource [className = 'org.apache.commons.codec.language.DoubleMetaphone_handleC_Test', methodName = 'testHandleC_withEmptyString', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Expected java.lang.IndexOutOfBoundsException to be thrown, but nothing was thrown.
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:73)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3115)
       org.apache.commons.codec.language.DoubleMetaphone_handleC_Test.testHandleC_withEmptyString(DoubleMetaphone_handleC_Test.java:157)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:728)
       [...]
  JUnit Jupiter:DoubleMetaphone_handleC_Test:testHandleC_withFocaccia()
    MethodSource [className = 'org.apache.commons.codec.language.DoubleMetaphone_handleC_Test', methodName = 'testHandleC_withFocaccia', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <X> but was: <K>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:54)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:49)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1041)
       org.apache.commons.codec.language.DoubleMetaphone_handleC_Test.testHandleC_withFocaccia(DoubleMetaphone_handleC_Test.java:109)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]

Test run finished after 461 ms
[         4 containers found      ]
[         0 containers skipped    ]
[         4 containers started    ]
[         0 containers aborted    ]
[         4 containers successful ]
[         0 containers failed     ]
[        12 tests found           ]
[         0 tests skipped         ]
[        12 tests started         ]
[         0 tests aborted         ]
[         6 tests successful      ]
[         6 tests failed          ]


```


And you can use the following related information to assist you in your analysis and correction:

api document of class org.apache.commons.codec.language.DoubleMetaphone: 

 * Encodes a string into a double metaphone value. This Implementation is based on the algorithm by <CITE>Lawrence
 * Philips</CITE>.
 * <p>
 * This class is conditionally thread-safe. The instance field for the maximum code length is mutable
 * {@link #setMaxCodeLen(int)} but is not volatile, and accesses are not synchronized. If an instance of the class is
 * shared between threads, the caller needs to ensure that suitable synchronization is used to ensure safe publication
 * of the value between threads, and must not invoke {@link #setMaxCodeLen(int)} after initial setup.
 * </p>
 *
 * @see <a href="https://drdobbs.com/the-double-metaphone-search-algorithm/184401251?pgno=2">Dr. Dobbs Original Article</a>
 * @see <a href="https://en.wikipedia.org/wiki/Metaphone">Wikipedia Metaphone</a>
 

api document of method handleC(String, DoubleMetaphoneResult, int): 

     * Handles 'C' cases.
     

return type: 
int

dependent classes: 
class `org.apache.commons.codec.language.DoubleMetaphone.DoubleMetaphoneResult`:
	
class `org.apache.commons.codec.language.DoubleMetaphone`:
	related functions:
		method `private int handleC(String, DoubleMetaphoneResult, int)` returns `int`, related with `org.apache.commons.codec.language.DoubleMetaphone.handleC(String, DoubleMetaphoneResult, int)`, api document: 
     * Handles 'C' cases.
     ;
		method `private int handleCC(String, DoubleMetaphoneResult, int)` returns `int`, related with `org.apache.commons.codec.language.DoubleMetaphone.handleC(String, DoubleMetaphoneResult, int)`, api document: 
     * Handles 'CC' cases.
     ;
		method `private int handleP(String, DoubleMetaphoneResult, int)` returns `int`, related with `org.apache.commons.codec.language.DoubleMetaphone.handleC(String, DoubleMetaphoneResult, int)`, api document: 
     * Handles 'P' cases.
     ;
		method `private int handleG(String, DoubleMetaphoneResult, int, boolean)` returns `int`, related with `org.apache.commons.codec.language.DoubleMetaphone.handleC(String, DoubleMetaphoneResult, int)`, api document: 
     * Handles 'G' cases.
     ;
		method `private int handleSC(String, DoubleMetaphoneResult, int)` returns `int`, related with `org.apache.commons.codec.language.DoubleMetaphone.handleC(String, DoubleMetaphoneResult, int)`, api document: 
     * Handles 'SC' cases.
     ;
		method `private int handleJ(String, DoubleMetaphoneResult, int, boolean)` returns `int`, related with `org.apache.commons.codec.language.DoubleMetaphone.handleC(String, DoubleMetaphoneResult, int)`, api document: 
     * Handles 'J' cases.
     ;



Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 
