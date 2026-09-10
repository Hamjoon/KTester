


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
class Nysiis_nysiis_Test {

    private Nysiis nysiis;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the Nysiis instance before each test
        // or true, depending on the test case
        nysiis = new Nysiis(false);
    }

    @Test
    void testNysiisEncoding() {
        assertEquals("BRAN", nysiis.encode("Brian"));
        assertEquals("CAP", nysiis.encode("Capp"));
        assertEquals("DAD", nysiis.encode("Dent"));
        assertEquals("DAN", nysiis.encode("Dane"));
        assertEquals("XY", nysiis.encode("XAY"));
        assertEquals("XY", nysiis.encode("XAYS"));
        // Add more test cases as needed
    }

    @Test
    void testNysiisEncodingWithNullInput() {
        // Test with null input to verify that the method returns null.
        assertNull(nysiis.nysiis(null));
    }

    @Test
    void testNysiisEncodingWithEmptyString() {
        // Test with an empty string to verify that the method returns an empty string.
        assertEquals("", nysiis.nysiis(""));
    }

    @Test
    void testNysiisEncodingWithMacDonald() {
        // Test with input starting with 'MAC' to verify that it is replaced with 'MCC'.
        assertEquals("MCCD", nysiis.nysiis("MACDonald"));
    }

    @Test
    void testNysiisEncodingWithKnight() {
        // Test with input starting with 'KN' to verify that it is replaced with 'NN'.
        assertEquals("NIGHT", nysiis.nysiis("KNight"));
    }

    @Test
    void testNysiisEncodingWithKite() {
        // Test with input starting with 'K' to verify that it is replaced with 'C'.
        assertEquals("CITE", nysiis.nysiis("Kite"));
    }

    @Test
    void testNysiisEncodingWithPhilip() {
        // Test with input starting with 'PH' to verify that it is replaced with 'FF'.
        assertEquals("FILI", nysiis.nysiis("Philip"));
    }

    @Test
    void testNysiisEncodingWithSchool() {
        // Test with input starting with 'SCH' to verify that it is replaced with 'SSS'.
        assertEquals("SSCOL", nysiis.nysiis("SCHOOL"));
    }

    @Test
    void testNysiisEncodingWithDoe() {
        // Test with input ending with 'E' to verify that it is replaced with 'Y'.
        assertEquals("D", nysiis.nysiis("Doe"));
    }

    @Test
    void testNysiisEncodingWithKnightEnding() {
        // Test with input ending with 'HT' to verify that it is replaced with 'D'.
        assertEquals("NIGHT", nysiis.nysiis("Knight"));
    }

    @Test
    void testNysiisEncodingWithSamantha() {
        // Test with input where last character is 'S', to verify that 'S' is removed.
        assertEquals("SAMANT", nysiis.nysiis("Samantha"));
    }

    @Test
    void testNysiisEncodingWithRay() {
        // Test with input where last characters are 'AY', to verify that 'AY' is replaced with 'Y'.
        assertEquals("R", nysiis.nysiis("Ray"));
    }

    @Test
    void testNysiisEncodingWithSara() {
        // Test with input where last character is 'A', to verify that 'A' is removed.
        assertEquals("S", nysiis.nysiis("Sara"));
    }

    @Test
    void testNysiisEncodingWithAlexander() {
        // Test with input to verify that the method correctly transcodes remaining characters and applies all rules.
        assertEquals("ALXAND", nysiis.nysiis("Alexander"));
    }

    @Test
    void testNysiisEncodingWithJohn() {
        // Test with a normal name that does not trigger any specific rules.
        assertEquals("JON", nysiis.nysiis("John"));
    }

    @Test
    void testNysiisEncodingWithPhilipson() {
        // Test with input starting with 'PH' to verify that it is replaced with 'FF' and processes remaining characters correctly.
        assertEquals("FILSON", nysiis.nysiis("Philipson"));
    }

    @Test
    void testNysiisEncodingWithMacArthur() {
        // Test with input starting with 'MAC' to verify that it is replaced with 'MCC' and applies other rules.
        assertEquals("MCCAR", nysiis.nysiis("MacArthur"));
    }

    @Test
    void testNysiisEncodingWithKatherine() {
        // Test with input starting with 'K' to verify that it is replaced with 'C' and processes remaining characters correctly.
        assertEquals("CATHR", nysiis.nysiis("Katherine"));
    }

    @Test
    void testNysiisEncodingWithSchmidt() {
        // Test with input starting with 'SCH' to verify that it is replaced with 'SSS' and applies ending rules.
        assertEquals("SSMID", nysiis.nysiis("Schmidt"));
    }

    @Test
    void testNysiisEncodingWithKnightly() {
        // Test with input ending with 'LY' to verify that it processes correctly without triggering ending rules.
        assertEquals("NIGHTL", nysiis.nysiis("Knightly"));
    }

    @Test
    void testNysiisEncodingWithSingleA() {
        // Test with a single character 'A' to verify that it is removed.
        assertEquals("", nysiis.nysiis("A"));
    }

    @Test
    void testNysiisEncodingWithDoubleA() {
        // Test with input 'AA' to verify that both 'A's are removed.
        assertEquals("", nysiis.nysiis("AA"));
    }

    @Test
    void testNysiisEncodingWithBobby() {
        // Test with a normal name to verify that it correctly processes and retains unique characters.
        assertEquals("BOBY", nysiis.nysiis("Bobby"));
    }

    @Test
    void testNysiisEncodingWithStringNull() {
        // Test with null input to verify that the method returns null.
        assertNull(nysiis.nysiis("null"));
    }
}

```

Corresponding execution feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended

╷
├─ JUnit Jupiter ✔
│  └─ Nysiis_nysiis_Test ✔
│     ├─ testNysiisEncodingWithKnightly() ✘ expected: <NIGHTL> but was: <NAGTLY>
│     ├─ testNysiisEncodingWithDoubleA() ✘ expected: <> but was: <A>
│     ├─ testNysiisEncodingWithAlexander() ✘ expected: <ALXAND> but was: <ALAXANDAR>
│     ├─ testNysiisEncodingWithSchmidt() ✘ expected: <SSMID> but was: <SNAD>
│     ├─ testNysiisEncodingWithJohn() ✘ expected: <JON> but was: <JAN>
│     ├─ testNysiisEncodingWithKite() ✘ expected: <CITE> but was: <CAT>
│     ├─ testNysiisEncodingWithSara() ✘ expected: <S> but was: <SAR>
│     ├─ testNysiisEncodingWithSingleA() ✘ expected: <> but was: <A>
│     ├─ testNysiisEncodingWithDoe() ✔
│     ├─ testNysiisEncodingWithRay() ✘ expected: <R> but was: <RY>
│     ├─ testNysiisEncodingWithKatherine() ✘ expected: <CATHR> but was: <CATARAN>
│     ├─ testNysiisEncodingWithKnightEnding() ✘ expected: <NIGHT> but was: <NAGT>
│     ├─ testNysiisEncodingWithSamantha() ✘ expected: <SAMANT> but was: <SANANT>
│     ├─ testNysiisEncodingWithNullInput() ✔
│     ├─ testNysiisEncodingWithStringNull() ✘ expected: <null> but was: <NAL>
│     ├─ testNysiisEncoding() ✔
│     ├─ testNysiisEncodingWithEmptyString() ✔
│     ├─ testNysiisEncodingWithKnight() ✘ expected: <NIGHT> but was: <NAGT>
│     ├─ testNysiisEncodingWithPhilip() ✘ expected: <FILI> but was: <FALAP>
│     ├─ testNysiisEncodingWithMacArthur() ✘ expected: <MCCAR> but was: <MCARTAR>
│     ├─ testNysiisEncodingWithSchool() ✘ expected: <SSCOL> but was: <SAL>
│     ├─ testNysiisEncodingWithMacDonald() ✘ expected: <MCCD> but was: <MCDANALD>
│     ├─ testNysiisEncodingWithBobby() ✘ expected: <BOBY> but was: <BABY>
│     └─ testNysiisEncodingWithPhilipson() ✘ expected: <FILSON> but was: <FALAPSAN>
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Failures (20):
  JUnit Jupiter:Nysiis_nysiis_Test:testNysiisEncodingWithKnightly()
    MethodSource [className = 'org.apache.commons.codec.language.Nysiis_nysiis_Test', methodName = 'testNysiisEncodingWithKnightly', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <NIGHTL> but was: <NAGTLY>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1145)
       org.apache.commons.codec.language.Nysiis_nysiis_Test.testNysiisEncodingWithKnightly(Nysiis_nysiis_Test.java:144)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:Nysiis_nysiis_Test:testNysiisEncodingWithDoubleA()
    MethodSource [className = 'org.apache.commons.codec.language.Nysiis_nysiis_Test', methodName = 'testNysiisEncodingWithDoubleA', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <> but was: <A>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1145)
       org.apache.commons.codec.language.Nysiis_nysiis_Test.testNysiisEncodingWithDoubleA(Nysiis_nysiis_Test.java:156)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:Nysiis_nysiis_Test:testNysiisEncodingWithAlexander()
    MethodSource [className = 'org.apache.commons.codec.language.Nysiis_nysiis_Test', methodName = 'testNysiisEncodingWithAlexander', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <ALXAND> but was: <ALAXANDAR>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1145)
       org.apache.commons.codec.language.Nysiis_nysiis_Test.testNysiisEncodingWithAlexander(Nysiis_nysiis_Test.java:108)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:Nysiis_nysiis_Test:testNysiisEncodingWithSchmidt()
    MethodSource [className = 'org.apache.commons.codec.language.Nysiis_nysiis_Test', methodName = 'testNysiisEncodingWithSchmidt', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <SSMID> but was: <SNAD>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1145)
       org.apache.commons.codec.language.Nysiis_nysiis_Test.testNysiisEncodingWithSchmidt(Nysiis_nysiis_Test.java:138)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:Nysiis_nysiis_Test:testNysiisEncodingWithJohn()
    MethodSource [className = 'org.apache.commons.codec.language.Nysiis_nysiis_Test', methodName = 'testNysiisEncodingWithJohn', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <JON> but was: <JAN>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1145)
       org.apache.commons.codec.language.Nysiis_nysiis_Test.testNysiisEncodingWithJohn(Nysiis_nysiis_Test.java:114)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:Nysiis_nysiis_Test:testNysiisEncodingWithKite()
    MethodSource [className = 'org.apache.commons.codec.language.Nysiis_nysiis_Test', methodName = 'testNysiisEncodingWithKite', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <CITE> but was: <CAT>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1145)
       org.apache.commons.codec.language.Nysiis_nysiis_Test.testNysiisEncodingWithKite(Nysiis_nysiis_Test.java:60)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:Nysiis_nysiis_Test:testNysiisEncodingWithSara()
    MethodSource [className = 'org.apache.commons.codec.language.Nysiis_nysiis_Test', methodName = 'testNysiisEncodingWithSara', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <S> but was: <SAR>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1145)
       org.apache.commons.codec.language.Nysiis_nysiis_Test.testNysiisEncodingWithSara(Nysiis_nysiis_Test.java:102)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:Nysiis_nysiis_Test:testNysiisEncodingWithSingleA()
    MethodSource [className = 'org.apache.commons.codec.language.Nysiis_nysiis_Test', methodName = 'testNysiisEncodingWithSingleA', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <> but was: <A>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1145)
       org.apache.commons.codec.language.Nysiis_nysiis_Test.testNysiisEncodingWithSingleA(Nysiis_nysiis_Test.java:150)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:Nysiis_nysiis_Test:testNysiisEncodingWithRay()
    MethodSource [className = 'org.apache.commons.codec.language.Nysiis_nysiis_Test', methodName = 'testNysiisEncodingWithRay', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <R> but was: <RY>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1145)
       org.apache.commons.codec.language.Nysiis_nysiis_Test.testNysiisEncodingWithRay(Nysiis_nysiis_Test.java:96)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:Nysiis_nysiis_Test:testNysiisEncodingWithKatherine()
    MethodSource [className = 'org.apache.commons.codec.language.Nysiis_nysiis_Test', methodName = 'testNysiisEncodingWithKatherine', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <CATHR> but was: <CATARAN>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1145)
       org.apache.commons.codec.language.Nysiis_nysiis_Test.testNysiisEncodingWithKatherine(Nysiis_nysiis_Test.java:132)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:Nysiis_nysiis_Test:testNysiisEncodingWithKnightEnding()
    MethodSource [className = 'org.apache.commons.codec.language.Nysiis_nysiis_Test', methodName = 'testNysiisEncodingWithKnightEnding', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <NIGHT> but was: <NAGT>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1145)
       org.apache.commons.codec.language.Nysiis_nysiis_Test.testNysiisEncodingWithKnightEnding(Nysiis_nysiis_Test.java:84)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:Nysiis_nysiis_Test:testNysiisEncodingWithSamantha()
    MethodSource [className = 'org.apache.commons.codec.language.Nysiis_nysiis_Test', methodName = 'testNysiisEncodingWithSamantha', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <SAMANT> but was: <SANANT>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1145)
       org.apache.commons.codec.language.Nysiis_nysiis_Test.testNysiisEncodingWithSamantha(Nysiis_nysiis_Test.java:90)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:Nysiis_nysiis_Test:testNysiisEncodingWithStringNull()
    MethodSource [className = 'org.apache.commons.codec.language.Nysiis_nysiis_Test', methodName = 'testNysiisEncodingWithStringNull', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <null> but was: <NAL>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertNull.failNotNull(AssertNull.java:50)
       org.junit.jupiter.api.AssertNull.assertNull(AssertNull.java:35)
       org.junit.jupiter.api.AssertNull.assertNull(AssertNull.java:30)
       org.junit.jupiter.api.Assertions.assertNull(Assertions.java:279)
       org.apache.commons.codec.language.Nysiis_nysiis_Test.testNysiisEncodingWithStringNull(Nysiis_nysiis_Test.java:168)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:Nysiis_nysiis_Test:testNysiisEncodingWithKnight()
    MethodSource [className = 'org.apache.commons.codec.language.Nysiis_nysiis_Test', methodName = 'testNysiisEncodingWithKnight', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <NIGHT> but was: <NAGT>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1145)
       org.apache.commons.codec.language.Nysiis_nysiis_Test.testNysiisEncodingWithKnight(Nysiis_nysiis_Test.java:54)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:Nysiis_nysiis_Test:testNysiisEncodingWithPhilip()
    MethodSource [className = 'org.apache.commons.codec.language.Nysiis_nysiis_Test', methodName = 'testNysiisEncodingWithPhilip', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <FILI> but was: <FALAP>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1145)
       org.apache.commons.codec.language.Nysiis_nysiis_Test.testNysiisEncodingWithPhilip(Nysiis_nysiis_Test.java:66)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:Nysiis_nysiis_Test:testNysiisEncodingWithMacArthur()
    MethodSource [className = 'org.apache.commons.codec.language.Nysiis_nysiis_Test', methodName = 'testNysiisEncodingWithMacArthur', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <MCCAR> but was: <MCARTAR>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1145)
       org.apache.commons.codec.language.Nysiis_nysiis_Test.testNysiisEncodingWithMacArthur(Nysiis_nysiis_Test.java:126)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:Nysiis_nysiis_Test:testNysiisEncodingWithSchool()
    MethodSource [className = 'org.apache.commons.codec.language.Nysiis_nysiis_Test', methodName = 'testNysiisEncodingWithSchool', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <SSCOL> but was: <SAL>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1145)
       org.apache.commons.codec.language.Nysiis_nysiis_Test.testNysiisEncodingWithSchool(Nysiis_nysiis_Test.java:72)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:Nysiis_nysiis_Test:testNysiisEncodingWithMacDonald()
    MethodSource [className = 'org.apache.commons.codec.language.Nysiis_nysiis_Test', methodName = 'testNysiisEncodingWithMacDonald', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <MCCD> but was: <MCDANALD>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1145)
       org.apache.commons.codec.language.Nysiis_nysiis_Test.testNysiisEncodingWithMacDonald(Nysiis_nysiis_Test.java:48)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:Nysiis_nysiis_Test:testNysiisEncodingWithBobby()
    MethodSource [className = 'org.apache.commons.codec.language.Nysiis_nysiis_Test', methodName = 'testNysiisEncodingWithBobby', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <BOBY> but was: <BABY>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1145)
       org.apache.commons.codec.language.Nysiis_nysiis_Test.testNysiisEncodingWithBobby(Nysiis_nysiis_Test.java:162)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:Nysiis_nysiis_Test:testNysiisEncodingWithPhilipson()
    MethodSource [className = 'org.apache.commons.codec.language.Nysiis_nysiis_Test', methodName = 'testNysiisEncodingWithPhilipson', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <FILSON> but was: <FALAPSAN>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1145)
       org.apache.commons.codec.language.Nysiis_nysiis_Test.testNysiisEncodingWithPhilipson(Nysiis_nysiis_Test.java:120)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]

Test run finished after 415 ms
[         4 containers found      ]
[         0 containers skipped    ]
[         4 containers started    ]
[         0 containers aborted    ]
[         4 containers successful ]
[         0 containers failed     ]
[        24 tests found           ]
[         0 tests skipped         ]
[        24 tests started         ]
[         0 tests aborted         ]
[         4 tests successful      ]
[        20 tests failed          ]


```


And you can use the following related information to assist you in your analysis and correction:

api document of class org.apache.commons.codec.language.Nysiis: 

 * Encodes a string into a NYSIIS value. NYSIIS is an encoding used to relate similar names, but can also be used as a
 * general purpose scheme to find word with similar phonemes.
 * <p>
 * NYSIIS features an accuracy increase of 2.7% over the traditional Soundex algorithm.
 * </p>
 * <p>
 * Algorithm description:
 * </p>
 * <pre>
 * 1. Transcode first characters of name
 *   1a. MAC -&gt;   MCC
 *   1b. KN  -&gt;   NN
 *   1c. K   -&gt;   C
 *   1d. PH  -&gt;   FF
 *   1e. PF  -&gt;   FF
 *   1f. SCH -&gt;   SSS
 * 2. Transcode last characters of name
 *   2a. EE, IE          -&gt;   Y
 *   2b. DT,RT,RD,NT,ND  -&gt;   D
 * 3. First character of key = first character of name
 * 4. Transcode remaining characters by following these rules, incrementing by one character each time
 *   4a. EV  -&gt;   AF  else A,E,I,O,U -&gt; A
 *   4b. Q   -&gt;   G
 *   4c. Z   -&gt;   S
 *   4d. M   -&gt;   N
 *   4e. KN  -&gt;   N   else K -&gt; C
 *   4f. SCH -&gt;   SSS
 *   4g. PH  -&gt;   FF
 *   4h. H   -&gt;   If previous or next is non-vowel, previous
 *   4i. W   -&gt;   If previous is vowel, previous
 *   4j. Add current to key if current != last key character
 * 5. If last character is S, remove it
 * 6. If last characters are AY, replace with Y
 * 7. If last character is A, remove it
 * 8. Collapse all strings of repeated characters
 * 9. Add original first character of name as first character of key
 * </pre>
 * <p>
 * This class is immutable and thread-safe.
 * </p>
 *
 * @see <a href="https://en.wikipedia.org/wiki/NYSIIS">NYSIIS on Wikipedia</a>
 * @see <a href="http://www.dropby.com/NYSIIS.html">NYSIIS on dropby.com</a>
 * @see Soundex
 * @since 1.7
 

api document of method nysiis(String): 

     * Retrieves the NYSIIS code for a given String object.
     *
     * @param str
     *            String to encode using the NYSIIS algorithm
     * @return A NYSIIS code for the String supplied
     

dependent classes: 
class `org.apache.commons.codec.language.Nysiis`:
	dependent fields:
		java.util.regex.Pattern org.apache.commons.codec.language.Nysiis.PAT_SCH;
		java.util.regex.Pattern org.apache.commons.codec.language.Nysiis.PAT_MAC;
		int org.apache.commons.codec.language.Nysiis.TRUE_LENGTH;
		java.util.regex.Pattern org.apache.commons.codec.language.Nysiis.PAT_K;
		java.util.regex.Pattern org.apache.commons.codec.language.Nysiis.PAT_PH_PF;
		java.util.regex.Pattern org.apache.commons.codec.language.Nysiis.PAT_DT_ETC;
		char org.apache.commons.codec.language.Nysiis.SPACE;
		java.util.regex.Pattern org.apache.commons.codec.language.Nysiis.PAT_KN;
		java.util.regex.Pattern org.apache.commons.codec.language.Nysiis.PAT_EE_IE;
	
class `org.apache.commons.codec.language.RefinedSoundex`:
	related functions:
		method `public String soundex(String)` returns `java.lang.String`, related with `org.apache.commons.codec.language.Nysiis.nysiis(String)`, api document: 
     * Retrieves the Refined Soundex code for a given String object.
     *
     * @param str
     *                  String to encode using the Refined Soundex algorithm
     * @return A soundex code for the String supplied
     ;
class `org.apache.commons.codec.language.Metaphone`:
	related functions:
		method `public String metaphone(String)` returns `java.lang.String`, related with `org.apache.commons.codec.language.Nysiis.nysiis(String)`, api document: 
     * Find the metaphone value of a String. This is similar to the
     * soundex algorithm, but better at finding similar sounding words.
     * All input is converted to upper case.
     * Limitations: Input format is expected to be a single ASCII word
     * with only characters in the A - Z range, no punctuation or numbers.
     *
     * @param txt String to find the metaphone code for
     * @return A metaphone code corresponding to the String supplied
     ;
class `org.apache.commons.codec.digest.B64`:
	related functions:
		method ` static String getRandomSalt(int, Random)` returns `java.lang.String`, related with `org.apache.commons.codec.language.Nysiis.nysiis(String)`, api document: 
     * Generates a string of random chars from the B64T set.
     * <p>
     * The salt is generated with the {@link Random} provided.
     * </p>
     *
     * @param num Number of chars to generate.
     * @param random an instance of {@link Random}.
     * @return a random salt {@link String}.
     ;
class `org.apache.commons.codec.language.Soundex`:
	related functions:
		method `public String soundex(String)` returns `java.lang.String`, related with `org.apache.commons.codec.language.Nysiis.nysiis(String)`, api document: 
     * Retrieves the Soundex code for a given String object.
     *
     * @param str
     *                  String to encode using the Soundex algorithm
     * @return A soundex code for the String supplied
     * @throws IllegalArgumentException
     *                  if a character is not mapped
     ;
class `org.apache.commons.codec.language.MatchRatingApproachEncoder`:
	related functions:
		method ` int leftToRightThenRightToLeftProcessing(String, String)` returns `int`, related with `org.apache.commons.codec.language.Nysiis.nysiis(String)`, api document: 
     * Processes the names from left to right (first) then right to left removing identical letters in same positions.
     * Then subtracts the longer string that remains from 6 and returns this.
     *
     * <h2>API Usage</h2>
     * <p>
     * Consider this method private, it is package protected for unit testing only.
     * </p>
     *
     * @param name1
     *            name2
     * @return the length as above
     ;
		method ` String removeAccents(String)` returns `java.lang.String`, related with `org.apache.commons.codec.language.Nysiis.nysiis(String)`, api document: 
     * Removes accented letters and replaces with non-accented ASCII equivalent Case is preserved.
     * http://www.codecodex.com/wiki/Remove_accent_from_letters_%28ex_.%C3%A9_to_e%29
     *
     * @param accentedWord
     *            The word that may have accents in it.
     * @return De-accented word
     ;
class `org.apache.commons.codec.language.SoundexUtils`:
	related functions:
		method ` static int differenceEncoded(String, String)` returns `int`, related with `org.apache.commons.codec.language.Nysiis.nysiis(String)`, api document: 
     * Returns the number of characters in the two Soundex encoded Strings that
     * are the same.
     * <ul>
     * <li>For Soundex, this return value ranges from 0 through 4: 0 indicates
     * little or no similarity, and 4 indicates strong similarity or identical
     * values.</li>
     * <li>For refined Soundex, the return value can be greater than 4.</li>
     * </ul>
     *
     * @param es1
     *                  An encoded String.
     * @param es2
     *                  An encoded String.
     * @return The number of characters in the two Soundex encoded Strings that
     *             are the same.
     *
     * @see <a href="https://msdn.microsoft.com/library/default.asp?url=/library/en-us/tsqlref/ts_de-dz_8co5.asp">
     *          MS T-SQL DIFFERENCE</a>
     ;
class `org.apache.commons.codec.language.DaitchMokotoffSoundex`:
	related functions:
		method `public String soundex(String)` returns `java.lang.String`, related with `org.apache.commons.codec.language.Nysiis.nysiis(String)`, api document: 
     * Encodes a String using the Daitch-Mokotoff soundex algorithm with branching.
     * <p>
     * In case a string is encoded into multiple codes (see branching rules), the result will contain all codes,
     * separated by '|'.
     * </p>
     * <p>
     * Example: the name "AUERBACH" is encoded as both
     * </p>
     * <ul>
     * <li>097400</li>
     * <li>097500</li>
     * </ul>
     * <p>
     * Thus the result will be "097400|097500".
     * </p>
     *
     * @param source
     *            A String object to encode
     * @return A string containing a set of DM Soundex codes corresponding to the String supplied
     * @throws IllegalArgumentException
     *             if a character is not mapped
     ;
		method `private static void parseRules(Scanner, String, Map<Character, List<Rule>>, Map<Character, Character>)` returns `void`, related with `org.apache.commons.codec.language.Nysiis.nysiis(String)`;



Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 
