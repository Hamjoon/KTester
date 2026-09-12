


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
import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class DoubleMetaphone_handleC_Test {

    private DoubleMetaphone doubleMetaphone;

    private DoubleMetaphone.DoubleMetaphoneResult doubleMetaphoneResult;

    @BeforeEach
    void setupBeforeEach() {
        doubleMetaphone = new DoubleMetaphone();
        doubleMetaphoneResult = doubleMetaphone.new DoubleMetaphoneResult(doubleMetaphone.getMaxCodeLen());
    }

    private int invokeHandleC(String value, DoubleMetaphone.DoubleMetaphoneResult result, int index) {
        try {
            Method handleCMethod = DoubleMetaphone.class.getDeclaredMethod("handleC", String.class, DoubleMetaphone.DoubleMetaphoneResult.class, int.class);
            handleCMethod.setAccessible(true);
            return (int) handleCMethod.invoke(doubleMetaphone, value, result, index);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testHandleC_Caeser() {
        String value = "CAESAR";
        int index = 0;
        int newIndex = invokeHandleC(value, doubleMetaphoneResult, index);
        assertEquals(2, newIndex);
        assertEquals("S", doubleMetaphoneResult.getPrimary());
    }

    @Test
    void testHandleC_CH() {
        String value = "CHURCH";
        int index = 0;
        int newIndex = invokeHandleC(value, doubleMetaphoneResult, index);
        // Assuming handleCH is correctly implemented and returns the correct index
        assertTrue(newIndex > index);
    }

    @Test
    void testHandleC_CIA() {
        String value = "CIAO";
        int index = 0;
        int newIndex = invokeHandleC(value, doubleMetaphoneResult, index);
        assertEquals(3, newIndex);
        assertEquals("X", doubleMetaphoneResult.getPrimary());
    }

    @Test
    void testHandleC_Default() {
        String value = "CAT";
        int index = 0;
        int newIndex = invokeHandleC(value, doubleMetaphoneResult, index);
        assertEquals(1, newIndex);
        assertEquals("K", doubleMetaphoneResult.getPrimary());
    }

    @Test
    void testHandleC_CZ() {
        String value = "CZERNY";
        int index = 0;
        int newIndex = invokeHandleC(value, doubleMetaphoneResult, index);
        assertEquals(2, newIndex);
        assertEquals("S", doubleMetaphoneResult.getPrimary());
        assertEquals("X", doubleMetaphoneResult.getAlternate());
    }

    @Test
    void testHandleC_CK() {
        String value = "ROCK";
        int index = 0;
        int newIndex = invokeHandleC(value, doubleMetaphoneResult, index);
        assertEquals(2, newIndex);
        assertEquals("K", doubleMetaphoneResult.getPrimary());
    }

    @Test
    void testHandleC_CI() {
        String value = "CIRCLE";
        int index = 0;
        int newIndex = invokeHandleC(value, doubleMetaphoneResult, index);
        assertEquals(2, newIndex);
        assertEquals("S", doubleMetaphoneResult.getPrimary());
    }

    @Test
    void testHandleC_Mac() {
        String value = "MAC GREGOR";
        int index = 0;
        int newIndex = invokeHandleC(value, doubleMetaphoneResult, index);
        assertEquals(3, newIndex);
        assertEquals("K", doubleMetaphoneResult.getPrimary());
    }

    @Test
    void testHandleC_CIO() {
        String value = "CIO";
        int index = 0;
        int newIndex = invokeHandleC(value, doubleMetaphoneResult, index);
        assertEquals(2, newIndex);
        assertEquals("S", doubleMetaphoneResult.getPrimary());
        assertEquals("X", doubleMetaphoneResult.getAlternate());
    }

    @Test
    void testHandleC_Exception_NullValue() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            invokeHandleC(null, doubleMetaphoneResult, 0);
        });
        assertNotNull(exception);
    }

    @Test
    void testHandleC_Exception_NullResult() {
        String value = "SOMESTRING";
        Exception exception = assertThrows(NullPointerException.class, () -> {
            invokeHandleC(value, null, 0);
        });
        assertNotNull(exception);
    }

    @Test
    void testHandleC_Exception_NegativeIndex() {
        String value = "SOMESTRING";
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            invokeHandleC(value, doubleMetaphoneResult, -1);
        });
        assertNotNull(exception);
    }

    @Test
    void testHandleC_Exception_IndexOutOfBounds() {
        String value = "SOMESTRING";
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            invokeHandleC(value, doubleMetaphoneResult, 100);
        });
        assertNotNull(exception);
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
│     ├─ testHandleC_Exception_NegativeIndex() ✘ Expected java.lang.IndexOutOfBoundsException to be thrown, but nothing was thrown.
│     ├─ testHandleC_Exception_IndexOutOfBounds() ✘ Expected java.lang.IndexOutOfBoundsException to be thrown, but nothing was thrown.
│     ├─ testHandleC_Default() ✔
│     ├─ testHandleC_Exception_NullResult() ✘ Unexpected exception type thrown, expected: <java.lang.NullPointerException> but was: <java.lang.RuntimeException>
│     ├─ testHandleC_Exception_NullValue() ✘ Unexpected exception type thrown, expected: <java.lang.NullPointerException> but was: <java.lang.RuntimeException>
│     ├─ testHandleC_Caeser() ✔
│     ├─ testHandleC_CIA() ✘ expected: <3> but was: <2>
│     ├─ testHandleC_CIO() ✔
│     ├─ testHandleC_Mac() ✘ expected: <3> but was: <1>
│     ├─ testHandleC_CH() ✔
│     ├─ testHandleC_CI() ✔
│     ├─ testHandleC_CK() ✘ expected: <2> but was: <1>
│     └─ testHandleC_CZ() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Failures (7):
  JUnit Jupiter:DoubleMetaphone_handleC_Test:testHandleC_Exception_NegativeIndex()
    MethodSource [className = 'org.apache.commons.codec.language.DoubleMetaphone_handleC_Test', methodName = 'testHandleC_Exception_NegativeIndex', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Expected java.lang.IndexOutOfBoundsException to be thrown, but nothing was thrown.
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:73)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3115)
       org.apache.commons.codec.language.DoubleMetaphone_handleC_Test.testHandleC_Exception_NegativeIndex(DoubleMetaphone_handleC_Test.java:137)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:728)
       [...]
  JUnit Jupiter:DoubleMetaphone_handleC_Test:testHandleC_Exception_IndexOutOfBounds()
    MethodSource [className = 'org.apache.commons.codec.language.DoubleMetaphone_handleC_Test', methodName = 'testHandleC_Exception_IndexOutOfBounds', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Expected java.lang.IndexOutOfBoundsException to be thrown, but nothing was thrown.
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:73)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3115)
       org.apache.commons.codec.language.DoubleMetaphone_handleC_Test.testHandleC_Exception_IndexOutOfBounds(DoubleMetaphone_handleC_Test.java:146)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:728)
       [...]
  JUnit Jupiter:DoubleMetaphone_handleC_Test:testHandleC_Exception_NullResult()
    MethodSource [className = 'org.apache.commons.codec.language.DoubleMetaphone_handleC_Test', methodName = 'testHandleC_Exception_NullResult', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Unexpected exception type thrown, expected: <java.lang.NullPointerException> but was: <java.lang.RuntimeException>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:67)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3115)
       org.apache.commons.codec.language.DoubleMetaphone_handleC_Test.testHandleC_Exception_NullResult(DoubleMetaphone_handleC_Test.java:128)
       [...]
     Caused by: java.lang.RuntimeException: java.lang.reflect.InvocationTargetException
       org.apache.commons.codec.language.DoubleMetaphone_handleC_Test.invokeHandleC(DoubleMetaphone_handleC_Test.java:30)
       org.apache.commons.codec.language.DoubleMetaphone_handleC_Test.lambda$testHandleC_Exception_NullResult$1(DoubleMetaphone_handleC_Test.java:129)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:53)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.apache.commons.codec.language.DoubleMetaphone_handleC_Test.invokeHandleC(DoubleMetaphone_handleC_Test.java:28)
       [...]
     Caused by: java.lang.NullPointerException: Cannot invoke "org.apache.commons.codec.language.DoubleMetaphone$DoubleMetaphoneResult.append(char)" because "result" is null
       org.apache.commons.codec.language.DoubleMetaphone.handleC(DoubleMetaphone.java:462)
       [...]
  JUnit Jupiter:DoubleMetaphone_handleC_Test:testHandleC_Exception_NullValue()
    MethodSource [className = 'org.apache.commons.codec.language.DoubleMetaphone_handleC_Test', methodName = 'testHandleC_Exception_NullValue', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Unexpected exception type thrown, expected: <java.lang.NullPointerException> but was: <java.lang.RuntimeException>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:67)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3115)
       org.apache.commons.codec.language.DoubleMetaphone_handleC_Test.testHandleC_Exception_NullValue(DoubleMetaphone_handleC_Test.java:119)
       [...]
     Caused by: java.lang.RuntimeException: java.lang.reflect.InvocationTargetException
       org.apache.commons.codec.language.DoubleMetaphone_handleC_Test.invokeHandleC(DoubleMetaphone_handleC_Test.java:30)
       org.apache.commons.codec.language.DoubleMetaphone_handleC_Test.lambda$testHandleC_Exception_NullValue$0(DoubleMetaphone_handleC_Test.java:120)
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
  JUnit Jupiter:DoubleMetaphone_handleC_Test:testHandleC_CIA()
    MethodSource [className = 'org.apache.commons.codec.language.DoubleMetaphone_handleC_Test', methodName = 'testHandleC_CIA', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <3> but was: <2>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:150)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:145)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:531)
       org.apache.commons.codec.language.DoubleMetaphone_handleC_Test.testHandleC_CIA(DoubleMetaphone_handleC_Test.java:57)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:DoubleMetaphone_handleC_Test:testHandleC_Mac()
    MethodSource [className = 'org.apache.commons.codec.language.DoubleMetaphone_handleC_Test', methodName = 'testHandleC_Mac', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <3> but was: <1>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:150)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:145)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:531)
       org.apache.commons.codec.language.DoubleMetaphone_handleC_Test.testHandleC_Mac(DoubleMetaphone_handleC_Test.java:103)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:DoubleMetaphone_handleC_Test:testHandleC_CK()
    MethodSource [className = 'org.apache.commons.codec.language.DoubleMetaphone_handleC_Test', methodName = 'testHandleC_CK', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <2> but was: <1>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:150)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:145)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:531)
       org.apache.commons.codec.language.DoubleMetaphone_handleC_Test.testHandleC_CK(DoubleMetaphone_handleC_Test.java:85)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]

Test run finished after 416 ms
[         4 containers found      ]
[         0 containers skipped    ]
[         4 containers started    ]
[         0 containers aborted    ]
[         4 containers successful ]
[         0 containers failed     ]
[        13 tests found           ]
[         0 tests skipped         ]
[        13 tests started         ]
[         0 tests aborted         ]
[         6 tests successful      ]
[         7 tests failed          ]


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
		method `private int handleP(String, DoubleMetaphoneResult, int)` returns `int`, related with `org.apache.commons.codec.language.DoubleMetaphone.handleC(String, DoubleMetaphoneResult, int)`, api document: 
     * Handles 'P' cases.
     ;
		method `private int handleSC(String, DoubleMetaphoneResult, int)` returns `int`, related with `org.apache.commons.codec.language.DoubleMetaphone.handleC(String, DoubleMetaphoneResult, int)`, api document: 
     * Handles 'SC' cases.
     ;
		method `private int handleC(String, DoubleMetaphoneResult, int)` returns `int`, related with `org.apache.commons.codec.language.DoubleMetaphone.handleC(String, DoubleMetaphoneResult, int)`, api document: 
     * Handles 'C' cases.
     ;
		method `private int handleG(String, DoubleMetaphoneResult, int, boolean)` returns `int`, related with `org.apache.commons.codec.language.DoubleMetaphone.handleC(String, DoubleMetaphoneResult, int)`, api document: 
     * Handles 'G' cases.
     ;
		method `private int handleCC(String, DoubleMetaphoneResult, int)` returns `int`, related with `org.apache.commons.codec.language.DoubleMetaphone.handleC(String, DoubleMetaphoneResult, int)`, api document: 
     * Handles 'CC' cases.
     ;
		method `private int handleJ(String, DoubleMetaphoneResult, int, boolean)` returns `int`, related with `org.apache.commons.codec.language.DoubleMetaphone.handleC(String, DoubleMetaphoneResult, int)`, api document: 
     * Handles 'J' cases.
     ;



Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 
