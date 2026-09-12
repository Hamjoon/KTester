


You are an experienced Java developer tasked with fixing a piece of code that contains execution errors. Your task is to resolve these issues after analyzing the code and its execution feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any logical errors.
2. Analyze the execution feedback to understand the specific errors and their locations within the code.
3. Apply necessary corrections to the code based on the feedback, and output the compelete correct code.
The Java code you need to fix is provided below:
```java
package org.apache.commons.codec.language;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class DoubleMetaphone_handleG_Test {

    private DoubleMetaphone doubleMetaphone;

    private DoubleMetaphone.DoubleMetaphoneResult doubleMetaphoneResult;

    @BeforeEach
    void setupBeforeEach() {
        doubleMetaphone = new DoubleMetaphone();
        doubleMetaphoneResult = doubleMetaphone.new DoubleMetaphoneResult(doubleMetaphone.getMaxCodeLen());
    }

    private int invokeHandleG(String value, DoubleMetaphone.DoubleMetaphoneResult result, int index, boolean slavoGermanic) throws Exception {
        Method method = DoubleMetaphone.class.getDeclaredMethod("handleG", String.class, DoubleMetaphone.DoubleMetaphoneResult.class, int.class, boolean.class);
        method.setAccessible(true);
        return (int) method.invoke(doubleMetaphone, value, result, index, slavoGermanic);
    }

    @Test
    void testHandleG() throws Exception {
        String value = "GHOST";
        int index = 0;
        boolean slavoGermanic = false;
        int resultIndex = invokeHandleG(value, doubleMetaphoneResult, index, slavoGermanic);
        assertEquals(2, resultIndex);
        assertEquals("K", doubleMetaphoneResult.getPrimary());
        assertEquals("K", doubleMetaphoneResult.getAlternate());
    }

    @Test
    void testHandleG_Group1() throws Exception {
        String value = "GH";
        int index = 0;
        boolean slavoGermanic = false;
        int resultIndex = invokeHandleG(value, doubleMetaphoneResult, index, slavoGermanic);
        assertNotEquals(index, resultIndex);
    }

    @Test
    void testHandleG_Group2() throws Exception {
        String value = "GN";
        int index = 0;
        boolean slavoGermanic = false;
        int resultIndex = invokeHandleG(value, doubleMetaphoneResult, index, slavoGermanic);
        assertEquals(index + 2, resultIndex);
        assertEquals("KN", doubleMetaphoneResult.getPrimary());
        assertEquals("N", doubleMetaphoneResult.getAlternate());
    }

    @Test
    void testHandleG_Group3() throws Exception {
        String value = "GNE";
        int index = 0;
        boolean slavoGermanic = false;
        int resultIndex = invokeHandleG(value, doubleMetaphoneResult, index, slavoGermanic);
        assertEquals(index + 2, resultIndex);
        assertEquals("N", doubleMetaphoneResult.getPrimary());
        assertEquals("KN", doubleMetaphoneResult.getAlternate());
    }

    @Test
    void testHandleG_Group4() throws Exception {
        String value = "GLI";
        int index = 0;
        boolean slavoGermanic = false;
        int resultIndex = invokeHandleG(value, doubleMetaphoneResult, index, slavoGermanic);
        assertEquals(index + 2, resultIndex);
        assertEquals("KL", doubleMetaphoneResult.getPrimary());
        assertEquals("L", doubleMetaphoneResult.getAlternate());
    }

    @Test
    void testHandleG_Group5() throws Exception {
        String value = "GES";
        int index = 0;
        boolean slavoGermanic = false;
        int resultIndex = invokeHandleG(value, doubleMetaphoneResult, index, slavoGermanic);
        assertEquals(index + 2, resultIndex);
        assertEquals("K", doubleMetaphoneResult.getPrimary());
        assertEquals("J", doubleMetaphoneResult.getAlternate());
    }

    @Test
    void testHandleG_Group6() throws Exception {
        String value = "GER";
        int index = 0;
        boolean slavoGermanic = false;
        int resultIndex = invokeHandleG(value, doubleMetaphoneResult, index, slavoGermanic);
        assertEquals(index + 2, resultIndex);
        assertEquals("K", doubleMetaphoneResult.getPrimary());
        assertEquals("J", doubleMetaphoneResult.getAlternate());
    }

    @Test
    void testHandleG_Group7() throws Exception {
        String value = "GIE";
        int index = 0;
        boolean slavoGermanic = false;
        int resultIndex = invokeHandleG(value, doubleMetaphoneResult, index, slavoGermanic);
        assertEquals(index + 2, resultIndex);
        assertEquals("J", doubleMetaphoneResult.getPrimary());
        assertEquals("K", doubleMetaphoneResult.getAlternate());
    }

    @Test
    void testHandleG_Group8() throws Exception {
        String value = "GG";
        int index = 0;
        boolean slavoGermanic = false;
        int resultIndex = invokeHandleG(value, doubleMetaphoneResult, index, slavoGermanic);
        assertEquals(index + 2, resultIndex);
        assertEquals("K", doubleMetaphoneResult.getPrimary());
        assertEquals("K", doubleMetaphoneResult.getAlternate());
    }

    @Test
    void testHandleG_Group9() throws Exception {
        String value = "GA";
        int index = 0;
        boolean slavoGermanic = false;
        int resultIndex = invokeHandleG(value, doubleMetaphoneResult, index, slavoGermanic);
        assertEquals(index + 1, resultIndex);
        assertEquals("K", doubleMetaphoneResult.getPrimary());
        assertEquals("K", doubleMetaphoneResult.getAlternate());
    }

    @Test
    void testHandleG_Additional1() throws Exception {
        String value = "GIER";
        int index = 0;
        boolean slavoGermanic = false;
        int resultIndex = invokeHandleG(value, doubleMetaphoneResult, index, slavoGermanic);
        assertEquals(index + 2, resultIndex);
        assertEquals("J", doubleMetaphoneResult.getPrimary());
        assertEquals("", doubleMetaphoneResult.getAlternate());
    }

    @Test
    void testHandleG_Additional2() throws Exception {
        String value = "GOGGI";
        int index = 0;
        boolean slavoGermanic = false;
        int resultIndex = invokeHandleG(value, doubleMetaphoneResult, index, slavoGermanic);
        assertEquals(index + 2, resultIndex);
        assertEquals("J", doubleMetaphoneResult.getPrimary());
        assertEquals("K", doubleMetaphoneResult.getAlternate());
    }

    @Test
    void testHandleG_Additional3() throws Exception {
        String value = "GERMAN";
        int index = 0;
        boolean slavoGermanic = true;
        int resultIndex = invokeHandleG(value, doubleMetaphoneResult, index, slavoGermanic);
        assertEquals(index + 2, resultIndex);
        assertEquals("K", doubleMetaphoneResult.getPrimary());
        assertEquals("K", doubleMetaphoneResult.getAlternate());
    }

    @Test
    void testHandleG_Additional4() throws Exception {
        String value = "GON";
        int index = 0;
        boolean slavoGermanic = false;
        int resultIndex = invokeHandleG(value, doubleMetaphoneResult, index, slavoGermanic);
        assertEquals(index + 1, resultIndex);
        assertEquals("K", doubleMetaphoneResult.getPrimary());
        assertEquals("K", doubleMetaphoneResult.getAlternate());
    }

    @Test
    void testHandleG_Additional5() throws Exception {
        String value = "G";
        int index = 0;
        boolean slavoGermanic = false;
        int resultIndex = invokeHandleG(value, doubleMetaphoneResult, index, slavoGermanic);
        assertEquals(index + 1, resultIndex);
        assertEquals("K", doubleMetaphoneResult.getPrimary());
        assertEquals("K", doubleMetaphoneResult.getAlternate());
    }

    @Test
    void testHandleG_Exception1() {
        String value = null;
        int index = 0;
        boolean slavoGermanic = false;
        assertThrows(NullPointerException.class, () -> {
            invokeHandleG(value, doubleMetaphoneResult, index, slavoGermanic);
        });
    }

    @Test
    void testHandleG_Exception2() {
        String value = "G";
        int index = 0;
        boolean slavoGermanic = false;
        assertThrows(NullPointerException.class, () -> {
            invokeHandleG(value, null, index, slavoGermanic);
        });
    }

    @Test
    void testHandleG_Exception3() {
        String value = "G";
        int index = -1;
        boolean slavoGermanic = false;
        assertThrows(IndexOutOfBoundsException.class, () -> {
            invokeHandleG(value, doubleMetaphoneResult, index, slavoGermanic);
        });
    }

    @Test
    void testHandleG_Exception4() {
        String value = "G";
        int index = 1000;
        boolean slavoGermanic = false;
        assertThrows(IndexOutOfBoundsException.class, () -> {
            invokeHandleG(value, doubleMetaphoneResult, index, slavoGermanic);
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
│  └─ DoubleMetaphone_handleG_Test ✔
│     ├─ testHandleG_Group1() ✔
│     ├─ testHandleG_Group2() ✘ expected: <KN> but was: <N>
│     ├─ testHandleG_Group3() ✔
│     ├─ testHandleG_Group4() ✔
│     ├─ testHandleG_Group5() ✔
│     ├─ testHandleG_Group6() ✔
│     ├─ testHandleG_Group7() ✘ expected: <J> but was: <K>
│     ├─ testHandleG_Group8() ✔
│     ├─ testHandleG_Group9() ✔
│     ├─ testHandleG_Additional1() ✘ expected: <J> but was: <K>
│     ├─ testHandleG_Additional2() ✘ expected: <2> but was: <1>
│     ├─ testHandleG_Additional3() ✘ expected: <K> but was: <J>
│     ├─ testHandleG_Additional4() ✔
│     ├─ testHandleG_Additional5() ✔
│     ├─ testHandleG_Exception1() ✘ Unexpected exception type thrown, expected: <java.lang.NullPointerException> but was: <java.lang.reflect.InvocationTargetException>
│     ├─ testHandleG_Exception2() ✘ Unexpected exception type thrown, expected: <java.lang.NullPointerException> but was: <java.lang.reflect.InvocationTargetException>
│     ├─ testHandleG_Exception3() ✘ Expected java.lang.IndexOutOfBoundsException to be thrown, but nothing was thrown.
│     ├─ testHandleG_Exception4() ✘ Expected java.lang.IndexOutOfBoundsException to be thrown, but nothing was thrown.
│     └─ testHandleG() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Failures (9):
  JUnit Jupiter:DoubleMetaphone_handleG_Test:testHandleG_Group2()
    MethodSource [className = 'org.apache.commons.codec.language.DoubleMetaphone_handleG_Test', methodName = 'testHandleG_Group2', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <KN> but was: <N>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1145)
       org.apache.commons.codec.language.DoubleMetaphone_handleG_Test.testHandleG_Group2(DoubleMetaphone_handleG_Test.java:57)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:DoubleMetaphone_handleG_Test:testHandleG_Group7()
    MethodSource [className = 'org.apache.commons.codec.language.DoubleMetaphone_handleG_Test', methodName = 'testHandleG_Group7', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <J> but was: <K>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1145)
       org.apache.commons.codec.language.DoubleMetaphone_handleG_Test.testHandleG_Group7(DoubleMetaphone_handleG_Test.java:112)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:DoubleMetaphone_handleG_Test:testHandleG_Additional1()
    MethodSource [className = 'org.apache.commons.codec.language.DoubleMetaphone_handleG_Test', methodName = 'testHandleG_Additional1', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <J> but was: <K>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1145)
       org.apache.commons.codec.language.DoubleMetaphone_handleG_Test.testHandleG_Additional1(DoubleMetaphone_handleG_Test.java:145)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:DoubleMetaphone_handleG_Test:testHandleG_Additional2()
    MethodSource [className = 'org.apache.commons.codec.language.DoubleMetaphone_handleG_Test', methodName = 'testHandleG_Additional2', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <2> but was: <1>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:150)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:145)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:531)
       org.apache.commons.codec.language.DoubleMetaphone_handleG_Test.testHandleG_Additional2(DoubleMetaphone_handleG_Test.java:155)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:DoubleMetaphone_handleG_Test:testHandleG_Additional3()
    MethodSource [className = 'org.apache.commons.codec.language.DoubleMetaphone_handleG_Test', methodName = 'testHandleG_Additional3', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <K> but was: <J>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1145)
       org.apache.commons.codec.language.DoubleMetaphone_handleG_Test.testHandleG_Additional3(DoubleMetaphone_handleG_Test.java:168)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:DoubleMetaphone_handleG_Test:testHandleG_Exception1()
    MethodSource [className = 'org.apache.commons.codec.language.DoubleMetaphone_handleG_Test', methodName = 'testHandleG_Exception1', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Unexpected exception type thrown, expected: <java.lang.NullPointerException> but was: <java.lang.reflect.InvocationTargetException>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:67)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3115)
       org.apache.commons.codec.language.DoubleMetaphone_handleG_Test.testHandleG_Exception1(DoubleMetaphone_handleG_Test.java:198)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.apache.commons.codec.language.DoubleMetaphone_handleG_Test.invokeHandleG(DoubleMetaphone_handleG_Test.java:27)
       [...]
     Caused by: java.lang.NullPointerException: Cannot invoke "String.length()" because "value" is null
       org.apache.commons.codec.language.DoubleMetaphone.charAt(DoubleMetaphone.java:164)
       org.apache.commons.codec.language.DoubleMetaphone.handleG(DoubleMetaphone.java:560)
       [...]
  JUnit Jupiter:DoubleMetaphone_handleG_Test:testHandleG_Exception2()
    MethodSource [className = 'org.apache.commons.codec.language.DoubleMetaphone_handleG_Test', methodName = 'testHandleG_Exception2', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Unexpected exception type thrown, expected: <java.lang.NullPointerException> but was: <java.lang.reflect.InvocationTargetException>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:67)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3115)
       org.apache.commons.codec.language.DoubleMetaphone_handleG_Test.testHandleG_Exception2(DoubleMetaphone_handleG_Test.java:208)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.apache.commons.codec.language.DoubleMetaphone_handleG_Test.invokeHandleG(DoubleMetaphone_handleG_Test.java:27)
       [...]
     Caused by: java.lang.NullPointerException: Cannot invoke "org.apache.commons.codec.language.DoubleMetaphone$DoubleMetaphoneResult.append(char)" because "result" is null
       org.apache.commons.codec.language.DoubleMetaphone.handleG(DoubleMetaphone.java:609)
       [...]
  JUnit Jupiter:DoubleMetaphone_handleG_Test:testHandleG_Exception3()
    MethodSource [className = 'org.apache.commons.codec.language.DoubleMetaphone_handleG_Test', methodName = 'testHandleG_Exception3', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Expected java.lang.IndexOutOfBoundsException to be thrown, but nothing was thrown.
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:73)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3115)
       org.apache.commons.codec.language.DoubleMetaphone_handleG_Test.testHandleG_Exception3(DoubleMetaphone_handleG_Test.java:218)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:728)
       [...]
  JUnit Jupiter:DoubleMetaphone_handleG_Test:testHandleG_Exception4()
    MethodSource [className = 'org.apache.commons.codec.language.DoubleMetaphone_handleG_Test', methodName = 'testHandleG_Exception4', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Expected java.lang.IndexOutOfBoundsException to be thrown, but nothing was thrown.
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:73)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3115)
       org.apache.commons.codec.language.DoubleMetaphone_handleG_Test.testHandleG_Exception4(DoubleMetaphone_handleG_Test.java:228)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:728)
       [...]

Test run finished after 408 ms
[         4 containers found      ]
[         0 containers skipped    ]
[         4 containers started    ]
[         0 containers aborted    ]
[         4 containers successful ]
[         0 containers failed     ]
[        19 tests found           ]
[         0 tests skipped         ]
[        19 tests started         ]
[         0 tests aborted         ]
[        10 tests successful      ]
[         9 tests failed          ]


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
 

api document of method handleG(String, DoubleMetaphoneResult, int, boolean): 

     * Handles 'G' cases.
     

return type: 
int

dependent classes: 
class `org.apache.commons.codec.language.DoubleMetaphone.DoubleMetaphoneResult`:
	
class `org.apache.commons.codec.language.DoubleMetaphone`:
	dependent fields:
		java.lang.String[] org.apache.commons.codec.language.DoubleMetaphone.ES_EP_EB_EL_EY_IB_IL_IN_IE_EI_ER;
	related functions:
		method `private int handleSC(String, DoubleMetaphoneResult, int)` returns `int`, related with `org.apache.commons.codec.language.DoubleMetaphone.handleG(String, DoubleMetaphoneResult, int, boolean)`, api document: 
     * Handles 'SC' cases.
     ;
		method `private int handleW(String, DoubleMetaphoneResult, int)` returns `int`, related with `org.apache.commons.codec.language.DoubleMetaphone.handleG(String, DoubleMetaphoneResult, int, boolean)`, api document: 
     * Handles 'W' cases.
     ;
		method `private boolean conditionC0(String, int)` returns `boolean`, related with `org.apache.commons.codec.language.DoubleMetaphone.handleG(String, DoubleMetaphoneResult, int, boolean)`, api document: 
     * Complex condition 0 for 'C'.
     ;
		method `private int handleJ(String, DoubleMetaphoneResult, int, boolean)` returns `int`, related with `org.apache.commons.codec.language.DoubleMetaphone.handleG(String, DoubleMetaphoneResult, int, boolean)`, api document: 
     * Handles 'J' cases.
     ;
		method `private int handleCC(String, DoubleMetaphoneResult, int)` returns `int`, related with `org.apache.commons.codec.language.DoubleMetaphone.handleG(String, DoubleMetaphoneResult, int, boolean)`, api document: 
     * Handles 'CC' cases.
     ;



Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 
