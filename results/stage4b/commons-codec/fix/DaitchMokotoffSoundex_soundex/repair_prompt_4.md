


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
class DaitchMokotoffSoundex_soundex_Test {

    private DaitchMokotoffSoundex soundexEncoder;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the target class before each test
        soundexEncoder = mock(DaitchMokotoffSoundex.class);
        // Mocking behavior for the soundex method
        when(soundexEncoder.soundex("Straßburg")).thenReturn("294795");
        when(soundexEncoder.soundex(null)).thenReturn(null);
        when(soundexEncoder.soundex("")).thenReturn("");
        when(soundexEncoder.soundex("GERSCHFELD")).thenReturn("547830|545783|594783|594578");
        // Add more mocked responses as needed for other tests
    }

    // Helper method to invoke the private soundex method using reflection
    private String[] invokeSoundex(String source, boolean branching) throws Exception {
        Method method = DaitchMokotoffSoundex.class.getDeclaredMethod("soundex", String.class, boolean.class);
        method.setAccessible(true);
        return (String[]) method.invoke(soundexEncoder, source, branching);
    }

    @Test
    void testSoundexWithValidInput() {
        String input = "Straßburg";
        String result = soundexEncoder.soundex(input);
        assertEquals("294795", result);
    }

    @Test
    void testSoundexWithNullInput() {
        String input = null;
        String result = soundexEncoder.soundex(input);
        assertNull(result);
    }

    @Test
    void testSoundexWithEmptyInput() {
        String input = "";
        String result = soundexEncoder.soundex(input);
        assertEquals("", result);
    }

    @Test
    void testSoundexWithBranching() {
        String input = "GERSCHFELD";
        String result = soundexEncoder.soundex(input);
        assertEquals("547830|545783|594783|594578", result);
    }

    @Test
    void testSoundexWithStandardName() throws Exception {
        String[] result = invokeSoundex("Smith", false);
        assertArrayEquals(new String[] { "S530" }, result);
    }

    @Test
    void testSoundexWithNameRequiringBranching() throws Exception {
        String[] result = invokeSoundex("Miller", true);
        assertArrayEquals(new String[] { "M460" }, result);
    }

    @Test
    void testSoundexWithNameIncludingVowels() throws Exception {
        String[] result = invokeSoundex("Jackson", true);
        assertArrayEquals(new String[] { "J250" }, result);
    }

    @Test
    void testSoundexWithSimpleName() throws Exception {
        String[] result = invokeSoundex("John", false);
        assertArrayEquals(new String[] { "J500" }, result);
    }

    @Test
    void testSoundexWithRepeatedCharacters() throws Exception {
        String[] result = invokeSoundex("Mann", true);
        assertArrayEquals(new String[] { "M500" }, result);
    }

    @Test
    void testSoundexWithWhitespaceInput() throws Exception {
        String[] result = invokeSoundex("  ", false);
        assertArrayEquals(new String[] { "0000" }, result);
    }

    @Test
    void testSoundexWithSingleCharacter() throws Exception {
        String[] result = invokeSoundex("B", false);
        assertArrayEquals(new String[] { "B000" }, result);
    }

    @Test
    void testSoundexWithSingleVowel() throws Exception {
        String[] result = invokeSoundex("A", false);
        assertArrayEquals(new String[] { "A000" }, result);
    }

    @Test
    void testSoundexWithSingleConsonantWithBranching() throws Exception {
        String[] result = invokeSoundex("M", true);
        assertArrayEquals(new String[] { "M000" }, result);
    }

    @Test
    void testSoundexWithConsonantsAndVowels() throws Exception {
        String[] result = invokeSoundex("Nash", true);
        assertArrayEquals(new String[] { "N020" }, result);
    }

    @Test
    void testSoundexWithApostrophe() throws Exception {
        String[] result = invokeSoundex("O'Connor", false);
        assertArrayEquals(new String[] { "O250" }, result);
    }

    @Test
    void testSoundexWithMixedCharacters() throws Exception {
        String[] result = invokeSoundex("Alfred", true);
        assertArrayEquals(new String[] { "A634" }, result);
    }

    @Test
    void testSoundexWithNameStartingWithVowel() throws Exception {
        String[] result = invokeSoundex("Avery", false);
        assertArrayEquals(new String[] { "A640" }, result);
    }

    @Test
    void testSoundexWithUniqueConsonantVowelCombination() throws Exception {
        String[] result = invokeSoundex("Bach", true);
        assertArrayEquals(new String[] { "B200" }, result);
    }

    @Test
    void testSoundexWithNumericInput() throws Exception {
        String[] result = invokeSoundex("12345", false);
        assertArrayEquals(new String[] { "0000" }, result);
    }

    @Test
    void testSoundexWithEmptyString() throws Exception {
        String[] result = invokeSoundex("", false);
        assertArrayEquals(new String[] { "0000" }, result);
    }

    @Test
    void testSoundexWithSingleSpace() throws Exception {
        String[] result = invokeSoundex(" ", false);
        assertArrayEquals(new String[] { "0000" }, result);
    }

    @Test
    void testSoundexWithVowelAndSpace() throws Exception {
        String[] result = invokeSoundex("A B", false);
        assertArrayEquals(new String[] { "A000" }, result);
    }

    @Test
    void testSoundexWithConsonantsSeparatedBySpace() throws Exception {
        String[] result = invokeSoundex("B C", false);
        assertArrayEquals(new String[] { "B000" }, result);
    }

    @Test
    void testSoundexWithSimpleNameWithBranching() throws Exception {
        String[] result = invokeSoundex("John", true);
        assertArrayEquals(new String[] { "J500" }, result);
    }
}
```

Corresponding execution feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended

╷
├─ JUnit Jupiter ✔
│  └─ DaitchMokotoffSoundex_soundex_Test ✔
│     ├─ testSoundexWithUniqueConsonantVowelCombination() ✘ 
│     │        Mockito cannot mock this class: class org.apache.commons.codec.language.DaitchMokotoffSoundex.
│     │     
│     │        If you're not sure why you're getting this error, please open an issue on GitHub.
│     │     
│     │     
│     │        Java               : 17
│     │        JVM vendor name    : Debian
│     │        JVM vendor version : 17.0.20.1+1-1-deb12u1-Debian
│     │        JVM name           : OpenJDK 64-Bit Server VM
│     │        JVM version        : 17.0.20.1+1-1-deb12u1-Debian
│     │        JVM info           : mixed mode, sharing
│     │        OS name            : Linux
│     │        OS version         : 7.0.12-linuxkit
│     │     
│     │     
│     │        You are seeing this disclaimer because Mockito is configured to create inlined mocks.
│     │        You can learn about inline mocks and their limitations under item #39 of the Mockito class javadoc.
│     │     
│     │        Underlying exception : org.mockito.exceptions.base.MockitoException: Cannot instrument class org.apache.commons.codec.language.DaitchMokotoffSoundex because it or one of its supertypes could not be initialized
│     ├─ testSoundexWithConsonantsAndVowels() ✘ Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
│     ├─ testSoundexWithEmptyString() ✘ Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
│     ├─ testSoundexWithVowelAndSpace() ✘ Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
│     ├─ testSoundexWithSingleSpace() ✘ Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
│     ├─ testSoundexWithSingleVowel() ✘ Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
│     ├─ testSoundexWithSimpleName() ✘ Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
│     ├─ testSoundexWithSingleCharacter() ✘ Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
│     ├─ testSoundexWithNumericInput() ✘ Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
│     ├─ testSoundexWithMixedCharacters() ✘ Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
│     ├─ testSoundexWithEmptyInput() ✘ Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
│     ├─ testSoundexWithWhitespaceInput() ✘ Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
│     ├─ testSoundexWithNameStartingWithVowel() ✘ Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
│     ├─ testSoundexWithBranching() ✘ Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
│     ├─ testSoundexWithRepeatedCharacters() ✘ Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
│     ├─ testSoundexWithStandardName() ✘ Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
│     ├─ testSoundexWithNameRequiringBranching() ✘ Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
│     ├─ testSoundexWithSimpleNameWithBranching() ✘ Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
│     ├─ testSoundexWithValidInput() ✘ Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
│     ├─ testSoundexWithSingleConsonantWithBranching() ✘ Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
│     ├─ testSoundexWithConsonantsSeparatedBySpace() ✘ Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
│     ├─ testSoundexWithApostrophe() ✘ Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
│     ├─ testSoundexWithNullInput() ✘ Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
│     └─ testSoundexWithNameIncludingVowels() ✘ Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Failures (24):
  JUnit Jupiter:DaitchMokotoffSoundex_soundex_Test:testSoundexWithUniqueConsonantVowelCombination()
    MethodSource [className = 'org.apache.commons.codec.language.DaitchMokotoffSoundex_soundex_Test', methodName = 'testSoundexWithUniqueConsonantVowelCombination', methodParameterTypes = '']
    => org.mockito.exceptions.base.MockitoException: 
Mockito cannot mock this class: class org.apache.commons.codec.language.DaitchMokotoffSoundex.

If you're not sure why you're getting this error, please open an issue on GitHub.


Java               : 17
JVM vendor name    : Debian
JVM vendor version : 17.0.20.1+1-1-deb12u1-Debian
JVM name           : OpenJDK 64-Bit Server VM
JVM version        : 17.0.20.1+1-1-deb12u1-Debian
JVM info           : mixed mode, sharing
OS name            : Linux
OS version         : 7.0.12-linuxkit


You are seeing this disclaimer because Mockito is configured to create inlined mocks.
You can learn about inline mocks and their limitations under item #39 of the Mockito class javadoc.

Underlying exception : org.mockito.exceptions.base.MockitoException: Cannot instrument class org.apache.commons.codec.language.DaitchMokotoffSoundex because it or one of its supertypes could not be initialized
       org.apache.commons.codec.language.DaitchMokotoffSoundex_soundex_Test.setupBeforeEach(DaitchMokotoffSoundex_soundex_Test.java:19)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       [...]
     Caused by: org.mockito.exceptions.base.MockitoException: Cannot instrument class org.apache.commons.codec.language.DaitchMokotoffSoundex because it or one of its supertypes could not be initialized
       net.bytebuddy.TypeCache.findOrInsert(TypeCache.java:168)
       net.bytebuddy.TypeCache$WithInlineExpunction.findOrInsert(TypeCache.java:399)
       net.bytebuddy.TypeCache.findOrInsert(TypeCache.java:190)
       net.bytebuddy.TypeCache$WithInlineExpunction.findOrInsert(TypeCache.java:410)
       [...]
     Caused by: java.lang.IllegalArgumentException: Unable to resolve required resource: org/apache/commons/codec/language/dmrules.txt
       org.apache.commons.codec.Resources.getInputStream(Resources.java:38)
       org.apache.commons.codec.language.DaitchMokotoffSoundex.<clinit>(DaitchMokotoffSoundex.java:228)
       java.base/java.lang.Class.forName0(Native Method)
       java.base/java.lang.Class.forName(Class.java:469)
       org.mockito.internal.creation.bytebuddy.InlineBytecodeGenerator.assureInitialization(InlineBytecodeGenerator.java:236)
       [...]
  JUnit Jupiter:DaitchMokotoffSoundex_soundex_Test:testSoundexWithConsonantsAndVowels()
    MethodSource [className = 'org.apache.commons.codec.language.DaitchMokotoffSoundex_soundex_Test', methodName = 'testSoundexWithConsonantsAndVowels', methodParameterTypes = '']
    => java.lang.NoClassDefFoundError: Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
       jdk.internal.reflect.GeneratedSerializationConstructorAccessor2.newInstance(Unknown Source)
       java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:500)
       java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:481)
       org.objenesis.instantiator.sun.SunReflectionFactoryInstantiator.newInstance(SunReflectionFactoryInstantiator.java:48)
       org.objenesis.ObjenesisBase.newInstance(ObjenesisBase.java:73)
       [...]
     Caused by: java.lang.ExceptionInInitializerError: Exception java.lang.IllegalArgumentException: Unable to resolve required resource: org/apache/commons/codec/language/dmrules.txt [in thread "main"]
       org.apache.commons.codec.Resources.getInputStream(Resources.java:38)
       org.apache.commons.codec.language.DaitchMokotoffSoundex.<clinit>(DaitchMokotoffSoundex.java:228)
       java.base/java.lang.Class.forName0(Native Method)
       java.base/java.lang.Class.forName(Class.java:469)
       org.mockito.internal.creation.bytebuddy.InlineBytecodeGenerator.assureInitialization(InlineBytecodeGenerator.java:236)
       [...]
  JUnit Jupiter:DaitchMokotoffSoundex_soundex_Test:testSoundexWithEmptyString()
    MethodSource [className = 'org.apache.commons.codec.language.DaitchMokotoffSoundex_soundex_Test', methodName = 'testSoundexWithEmptyString', methodParameterTypes = '']
    => java.lang.NoClassDefFoundError: Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
       jdk.internal.reflect.GeneratedSerializationConstructorAccessor2.newInstance(Unknown Source)
       java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:500)
       java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:481)
       org.objenesis.instantiator.sun.SunReflectionFactoryInstantiator.newInstance(SunReflectionFactoryInstantiator.java:48)
       org.objenesis.ObjenesisBase.newInstance(ObjenesisBase.java:73)
       [...]
     Caused by: java.lang.ExceptionInInitializerError: Exception java.lang.IllegalArgumentException: Unable to resolve required resource: org/apache/commons/codec/language/dmrules.txt [in thread "main"]
       org.apache.commons.codec.Resources.getInputStream(Resources.java:38)
       org.apache.commons.codec.language.DaitchMokotoffSoundex.<clinit>(DaitchMokotoffSoundex.java:228)
       java.base/java.lang.Class.forName0(Native Method)
       java.base/java.lang.Class.forName(Class.java:469)
       org.mockito.internal.creation.bytebuddy.InlineBytecodeGenerator.assureInitialization(InlineBytecodeGenerator.java:236)
       [...]
  JUnit Jupiter:DaitchMokotoffSoundex_soundex_Test:testSoundexWithVowelAndSpace()
    MethodSource [className = 'org.apache.commons.codec.language.DaitchMokotoffSoundex_soundex_Test', methodName = 'testSoundexWithVowelAndSpace', methodParameterTypes = '']
    => java.lang.NoClassDefFoundError: Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
       jdk.internal.reflect.GeneratedSerializationConstructorAccessor2.newInstance(Unknown Source)
       java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:500)
       java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:481)
       org.objenesis.instantiator.sun.SunReflectionFactoryInstantiator.newInstance(SunReflectionFactoryInstantiator.java:48)
       org.objenesis.ObjenesisBase.newInstance(ObjenesisBase.java:73)
       [...]
     Caused by: java.lang.ExceptionInInitializerError: Exception java.lang.IllegalArgumentException: Unable to resolve required resource: org/apache/commons/codec/language/dmrules.txt [in thread "main"]
       org.apache.commons.codec.Resources.getInputStream(Resources.java:38)
       org.apache.commons.codec.language.DaitchMokotoffSoundex.<clinit>(DaitchMokotoffSoundex.java:228)
       java.base/java.lang.Class.forName0(Native Method)
       java.base/java.lang.Class.forName(Class.java:469)
       org.mockito.internal.creation.bytebuddy.InlineBytecodeGenerator.assureInitialization(InlineBytecodeGenerator.java:236)
       [...]
  JUnit Jupiter:DaitchMokotoffSoundex_soundex_Test:testSoundexWithSingleSpace()
    MethodSource [className = 'org.apache.commons.codec.language.DaitchMokotoffSoundex_soundex_Test', methodName = 'testSoundexWithSingleSpace', methodParameterTypes = '']
    => java.lang.NoClassDefFoundError: Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
       jdk.internal.reflect.GeneratedSerializationConstructorAccessor2.newInstance(Unknown Source)
       java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:500)
       java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:481)
       org.objenesis.instantiator.sun.SunReflectionFactoryInstantiator.newInstance(SunReflectionFactoryInstantiator.java:48)
       org.objenesis.ObjenesisBase.newInstance(ObjenesisBase.java:73)
       [...]
     Caused by: java.lang.ExceptionInInitializerError: Exception java.lang.IllegalArgumentException: Unable to resolve required resource: org/apache/commons/codec/language/dmrules.txt [in thread "main"]
       org.apache.commons.codec.Resources.getInputStream(Resources.java:38)
       org.apache.commons.codec.language.DaitchMokotoffSoundex.<clinit>(DaitchMokotoffSoundex.java:228)
       java.base/java.lang.Class.forName0(Native Method)
       java.base/java.lang.Class.forName(Class.java:469)
       org.mockito.internal.creation.bytebuddy.InlineBytecodeGenerator.assureInitialization(InlineBytecodeGenerator.java:236)
       [...]
  JUnit Jupiter:DaitchMokotoffSoundex_soundex_Test:testSoundexWithSingleVowel()
    MethodSource [className = 'org.apache.commons.codec.language.DaitchMokotoffSoundex_soundex_Test', methodName = 'testSoundexWithSingleVowel', methodParameterTypes = '']
    => java.lang.NoClassDefFoundError: Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
       jdk.internal.reflect.GeneratedSerializationConstructorAccessor2.newInstance(Unknown Source)
       java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:500)
       java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:481)
       org.objenesis.instantiator.sun.SunReflectionFactoryInstantiator.newInstance(SunReflectionFactoryInstantiator.java:48)
       org.objenesis.ObjenesisBase.newInstance(ObjenesisBase.java:73)
       [...]
     Caused by: java.lang.ExceptionInInitializerError: Exception java.lang.IllegalArgumentException: Unable to resolve required resource: org/apache/commons/codec/language/dmrules.txt [in thread "main"]
       org.apache.commons.codec.Resources.getInputStream(Resources.java:38)
       org.apache.commons.codec.language.DaitchMokotoffSoundex.<clinit>(DaitchMokotoffSoundex.java:228)
       java.base/java.lang.Class.forName0(Native Method)
       java.base/java.lang.Class.forName(Class.java:469)
       org.mockito.internal.creation.bytebuddy.InlineBytecodeGenerator.assureInitialization(InlineBytecodeGenerator.java:236)
       [...]
  JUnit Jupiter:DaitchMokotoffSoundex_soundex_Test:testSoundexWithSimpleName()
    MethodSource [className = 'org.apache.commons.codec.language.DaitchMokotoffSoundex_soundex_Test', methodName = 'testSoundexWithSimpleName', methodParameterTypes = '']
    => java.lang.NoClassDefFoundError: Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
       jdk.internal.reflect.GeneratedSerializationConstructorAccessor2.newInstance(Unknown Source)
       java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:500)
       java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:481)
       org.objenesis.instantiator.sun.SunReflectionFactoryInstantiator.newInstance(SunReflectionFactoryInstantiator.java:48)
       org.objenesis.ObjenesisBase.newInstance(ObjenesisBase.java:73)
       [...]
     Caused by: java.lang.ExceptionInInitializerError: Exception java.lang.IllegalArgumentException: Unable to resolve required resource: org/apache/commons/codec/language/dmrules.txt [in thread "main"]
       org.apache.commons.codec.Resources.getInputStream(Resources.java:38)
       org.apache.commons.codec.language.DaitchMokotoffSoundex.<clinit>(DaitchMokotoffSoundex.java:228)
       java.base/java.lang.Class.forName0(Native Method)
       java.base/java.lang.Class.forName(Class.java:469)
       org.mockito.internal.creation.bytebuddy.InlineBytecodeGenerator.assureInitialization(InlineBytecodeGenerator.java:236)
       [...]
  JUnit Jupiter:DaitchMokotoffSoundex_soundex_Test:testSoundexWithSingleCharacter()
    MethodSource [className = 'org.apache.commons.codec.language.DaitchMokotoffSoundex_soundex_Test', methodName = 'testSoundexWithSingleCharacter', methodParameterTypes = '']
    => java.lang.NoClassDefFoundError: Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
       jdk.internal.reflect.GeneratedSerializationConstructorAccessor2.newInstance(Unknown Source)
       java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:500)
       java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:481)
       org.objenesis.instantiator.sun.SunReflectionFactoryInstantiator.newInstance(SunReflectionFactoryInstantiator.java:48)
       org.objenesis.ObjenesisBase.newInstance(ObjenesisBase.java:73)
       [...]
     Caused by: java.lang.ExceptionInInitializerError: Exception java.lang.IllegalArgumentException: Unable to resolve required resource: org/apache/commons/codec/language/dmrules.txt [in thread "main"]
       org.apache.commons.codec.Resources.getInputStream(Resources.java:38)
       org.apache.commons.codec.language.DaitchMokotoffSoundex.<clinit>(DaitchMokotoffSoundex.java:228)
       java.base/java.lang.Class.forName0(Native Method)
       java.base/java.lang.Class.forName(Class.java:469)
       org.mockito.internal.creation.bytebuddy.InlineBytecodeGenerator.assureInitialization(InlineBytecodeGenerator.java:236)
       [...]
  JUnit Jupiter:DaitchMokotoffSoundex_soundex_Test:testSoundexWithNumericInput()
    MethodSource [className = 'org.apache.commons.codec.language.DaitchMokotoffSoundex_soundex_Test', methodName = 'testSoundexWithNumericInput', methodParameterTypes = '']
    => java.lang.NoClassDefFoundError: Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
       jdk.internal.reflect.GeneratedSerializationConstructorAccessor2.newInstance(Unknown Source)
       java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:500)
       java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:481)
       org.objenesis.instantiator.sun.SunReflectionFactoryInstantiator.newInstance(SunReflectionFactoryInstantiator.java:48)
       org.objenesis.ObjenesisBase.newInstance(ObjenesisBase.java:73)
       [...]
     Caused by: java.lang.ExceptionInInitializerError: Exception java.lang.IllegalArgumentException: Unable to resolve required resource: org/apache/commons/codec/language/dmrules.txt [in thread "main"]
       org.apache.commons.codec.Resources.getInputStream(Resources.java:38)
       org.apache.commons.codec.language.DaitchMokotoffSoundex.<clinit>(DaitchMokotoffSoundex.java:228)
       java.base/java.lang.Class.forName0(Native Method)
       java.base/java.lang.Class.forName(Class.java:469)
       org.mockito.internal.creation.bytebuddy.InlineBytecodeGenerator.assureInitialization(InlineBytecodeGenerator.java:236)
       [...]
  JUnit Jupiter:DaitchMokotoffSoundex_soundex_Test:testSoundexWithMixedCharacters()
    MethodSource [className = 'org.apache.commons.codec.language.DaitchMokotoffSoundex_soundex_Test', methodName = 'testSoundexWithMixedCharacters', methodParameterTypes = '']
    => java.lang.NoClassDefFoundError: Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
       jdk.internal.reflect.GeneratedSerializationConstructorAccessor2.newInstance(Unknown Source)
       java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:500)
       java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:481)
       org.objenesis.instantiator.sun.SunReflectionFactoryInstantiator.newInstance(SunReflectionFactoryInstantiator.java:48)
       org.objenesis.ObjenesisBase.newInstance(ObjenesisBase.java:73)
       [...]
     Caused by: java.lang.ExceptionInInitializerError: Exception java.lang.IllegalArgumentException: Unable to resolve required resource: org/apache/commons/codec/language/dmrules.txt [in thread "main"]
       org.apache.commons.codec.Resources.getInputStream(Resources.java:38)
       org.apache.commons.codec.language.DaitchMokotoffSoundex.<clinit>(DaitchMokotoffSoundex.java:228)
       java.base/java.lang.Class.forName0(Native Method)
       java.base/java.lang.Class.forName(Class.java:469)
       org.mockito.internal.creation.bytebuddy.InlineBytecodeGenerator.assureInitialization(InlineBytecodeGenerator.java:236)
       [...]
  JUnit Jupiter:DaitchMokotoffSoundex_soundex_Test:testSoundexWithEmptyInput()
    MethodSource [className = 'org.apache.commons.codec.language.DaitchMokotoffSoundex_soundex_Test', methodName = 'testSoundexWithEmptyInput', methodParameterTypes = '']
    => java.lang.NoClassDefFoundError: Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
       jdk.internal.reflect.GeneratedSerializationConstructorAccessor2.newInstance(Unknown Source)
       java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:500)
       java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:481)
       org.objenesis.instantiator.sun.SunReflectionFactoryInstantiator.newInstance(SunReflectionFactoryInstantiator.java:48)
       org.objenesis.ObjenesisBase.newInstance(ObjenesisBase.java:73)
       [...]
     Caused by: java.lang.ExceptionInInitializerError: Exception java.lang.IllegalArgumentException: Unable to resolve required resource: org/apache/commons/codec/language/dmrules.txt [in thread "main"]
       org.apache.commons.codec.Resources.getInputStream(Resources.java:38)
       org.apache.commons.codec.language.DaitchMokotoffSoundex.<clinit>(DaitchMokotoffSoundex.java:228)
       java.base/java.lang.Class.forName0(Native Method)
       java.base/java.lang.Class.forName(Class.java:469)
       org.mockito.internal.creation.bytebuddy.InlineBytecodeGenerator.assureInitialization(InlineBytecodeGenerator.java:236)
       [...]
  JUnit Jupiter:DaitchMokotoffSoundex_soundex_Test:testSoundexWithWhitespaceInput()
    MethodSource [className = 'org.apache.commons.codec.language.DaitchMokotoffSoundex_soundex_Test', methodName = 'testSoundexWithWhitespaceInput', methodParameterTypes = '']
    => java.lang.NoClassDefFoundError: Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
       jdk.internal.reflect.GeneratedSerializationConstructorAccessor2.newInstance(Unknown Source)
       java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:500)
       java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:481)
       org.objenesis.instantiator.sun.SunReflectionFactoryInstantiator.newInstance(SunReflectionFactoryInstantiator.java:48)
       org.objenesis.ObjenesisBase.newInstance(ObjenesisBase.java:73)
       [...]
     Caused by: java.lang.ExceptionInInitializerError: Exception java.lang.IllegalArgumentException: Unable to resolve required resource: org/apache/commons/codec/language/dmrules.txt [in thread "main"]
       org.apache.commons.codec.Resources.getInputStream(Resources.java:38)
       org.apache.commons.codec.language.DaitchMokotoffSoundex.<clinit>(DaitchMokotoffSoundex.java:228)
       java.base/java.lang.Class.forName0(Native Method)
       java.base/java.lang.Class.forName(Class.java:469)
       org.mockito.internal.creation.bytebuddy.InlineBytecodeGenerator.assureInitialization(InlineBytecodeGenerator.java:236)
       [...]
  JUnit Jupiter:DaitchMokotoffSoundex_soundex_Test:testSoundexWithNameStartingWithVowel()
    MethodSource [className = 'org.apache.commons.codec.language.DaitchMokotoffSoundex_soundex_Test', methodName = 'testSoundexWithNameStartingWithVowel', methodParameterTypes = '']
    => java.lang.NoClassDefFoundError: Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
       jdk.internal.reflect.GeneratedSerializationConstructorAccessor2.newInstance(Unknown Source)
       java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:500)
       java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:481)
       org.objenesis.instantiator.sun.SunReflectionFactoryInstantiator.newInstance(SunReflectionFactoryInstantiator.java:48)
       org.objenesis.ObjenesisBase.newInstance(ObjenesisBase.java:73)
       [...]
     Caused by: java.lang.ExceptionInInitializerError: Exception java.lang.IllegalArgumentException: Unable to resolve required resource: org/apache/commons/codec/language/dmrules.txt [in thread "main"]
       org.apache.commons.codec.Resources.getInputStream(Resources.java:38)
       org.apache.commons.codec.language.DaitchMokotoffSoundex.<clinit>(DaitchMokotoffSoundex.java:228)
       java.base/java.lang.Class.forName0(Native Method)
       java.base/java.lang.Class.forName(Class.java:469)
       org.mockito.internal.creation.bytebuddy.InlineBytecodeGenerator.assureInitialization(InlineBytecodeGenerator.java:236)
       [...]
  JUnit Jupiter:DaitchMokotoffSoundex_soundex_Test:testSoundexWithBranching()
    MethodSource [className = 'org.apache.commons.codec.language.DaitchMokotoffSoundex_soundex_Test', methodName = 'testSoundexWithBranching', methodParameterTypes = '']
    => java.lang.NoClassDefFoundError: Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
       jdk.internal.reflect.GeneratedSerializationConstructorAccessor2.newInstance(Unknown Source)
       java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:500)
       java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:481)
       org.objenesis.instantiator.sun.SunReflectionFactoryInstantiator.newInstance(SunReflectionFactoryInstantiator.java:48)
       org.objenesis.ObjenesisBase.newInstance(ObjenesisBase.java:73)
       [...]
     Caused by: java.lang.ExceptionInInitializerError: Exception java.lang.IllegalArgumentException: Unable to resolve required resource: org/apache/commons/codec/language/dmrules.txt [in thread "main"]
       org.apache.commons.codec.Resources.getInputStream(Resources.java:38)
       org.apache.commons.codec.language.DaitchMokotoffSoundex.<clinit>(DaitchMokotoffSoundex.java:228)
       java.base/java.lang.Class.forName0(Native Method)
       java.base/java.lang.Class.forName(Class.java:469)
       org.mockito.internal.creation.bytebuddy.InlineBytecodeGenerator.assureInitialization(InlineBytecodeGenerator.java:236)
       [...]
  JUnit Jupiter:DaitchMokotoffSoundex_soundex_Test:testSoundexWithRepeatedCharacters()
    MethodSource [className = 'org.apache.commons.codec.language.DaitchMokotoffSoundex_soundex_Test', methodName = 'testSoundexWithRepeatedCharacters', methodParameterTypes = '']
    => java.lang.NoClassDefFoundError: Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
       jdk.internal.reflect.GeneratedSerializationConstructorAccessor2.newInstance(Unknown Source)
       java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:500)
       java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:481)
       org.objenesis.instantiator.sun.SunReflectionFactoryInstantiator.newInstance(SunReflectionFactoryInstantiator.java:48)
       org.objenesis.ObjenesisBase.newInstance(ObjenesisBase.java:73)
       [...]
     Caused by: java.lang.ExceptionInInitializerError: Exception java.lang.IllegalArgumentException: Unable to resolve required resource: org/apache/commons/codec/language/dmrules.txt [in thread "main"]
       org.apache.commons.codec.Resources.getInputStream(Resources.java:38)
       org.apache.commons.codec.language.DaitchMokotoffSoundex.<clinit>(DaitchMokotoffSoundex.java:228)
       java.base/java.lang.Class.forName0(Native Method)
       java.base/java.lang.Class.forName(Class.java:469)
       org.mockito.internal.creation.bytebuddy.InlineBytecodeGenerator.assureInitialization(InlineBytecodeGenerator.java:236)
       [...]
  JUnit Jupiter:DaitchMokotoffSoundex_soundex_Test:testSoundexWithStandardName()
    MethodSource [className = 'org.apache.commons.codec.language.DaitchMokotoffSoundex_soundex_Test', methodName = 'testSoundexWithStandardName', methodParameterTypes = '']
    => java.lang.NoClassDefFoundError: Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
       jdk.internal.reflect.GeneratedSerializationConstructorAccessor2.newInstance(Unknown Source)
       java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:500)
       java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:481)
       org.objenesis.instantiator.sun.SunReflectionFactoryInstantiator.newInstance(SunReflectionFactoryInstantiator.java:48)
       org.objenesis.ObjenesisBase.newInstance(ObjenesisBase.java:73)
       [...]
     Caused by: java.lang.ExceptionInInitializerError: Exception java.lang.IllegalArgumentException: Unable to resolve required resource: org/apache/commons/codec/language/dmrules.txt [in thread "main"]
       org.apache.commons.codec.Resources.getInputStream(Resources.java:38)
       org.apache.commons.codec.language.DaitchMokotoffSoundex.<clinit>(DaitchMokotoffSoundex.java:228)
       java.base/java.lang.Class.forName0(Native Method)
       java.base/java.lang.Class.forName(Class.java:469)
       org.mockito.internal.creation.bytebuddy.InlineBytecodeGenerator.assureInitialization(InlineBytecodeGenerator.java:236)
       [...]
  JUnit Jupiter:DaitchMokotoffSoundex_soundex_Test:testSoundexWithNameRequiringBranching()
    MethodSource [className = 'org.apache.commons.codec.language.DaitchMokotoffSoundex_soundex_Test', methodName = 'testSoundexWithNameRequiringBranching', methodParameterTypes = '']
    => java.lang.NoClassDefFoundError: Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
       jdk.internal.reflect.GeneratedSerializationConstructorAccessor2.newInstance(Unknown Source)
       java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:500)
       java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:481)
       org.objenesis.instantiator.sun.SunReflectionFactoryInstantiator.newInstance(SunReflectionFactoryInstantiator.java:48)
       org.objenesis.ObjenesisBase.newInstance(ObjenesisBase.java:73)
       [...]
     Caused by: java.lang.ExceptionInInitializerError: Exception java.lang.IllegalArgumentException: Unable to resolve required resource: org/apache/commons/codec/language/dmrules.txt [in thread "main"]
       org.apache.commons.codec.Resources.getInputStream(Resources.java:38)
       org.apache.commons.codec.language.DaitchMokotoffSoundex.<clinit>(DaitchMokotoffSoundex.java:228)
       java.base/java.lang.Class.forName0(Native Method)
       java.base/java.lang.Class.forName(Class.java:469)
       org.mockito.internal.creation.bytebuddy.InlineBytecodeGenerator.assureInitialization(InlineBytecodeGenerator.java:236)
       [...]
  JUnit Jupiter:DaitchMokotoffSoundex_soundex_Test:testSoundexWithSimpleNameWithBranching()
    MethodSource [className = 'org.apache.commons.codec.language.DaitchMokotoffSoundex_soundex_Test', methodName = 'testSoundexWithSimpleNameWithBranching', methodParameterTypes = '']
    => java.lang.NoClassDefFoundError: Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
       jdk.internal.reflect.GeneratedSerializationConstructorAccessor2.newInstance(Unknown Source)
       java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:500)
       java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:481)
       org.objenesis.instantiator.sun.SunReflectionFactoryInstantiator.newInstance(SunReflectionFactoryInstantiator.java:48)
       org.objenesis.ObjenesisBase.newInstance(ObjenesisBase.java:73)
       [...]
     Caused by: java.lang.ExceptionInInitializerError: Exception java.lang.IllegalArgumentException: Unable to resolve required resource: org/apache/commons/codec/language/dmrules.txt [in thread "main"]
       org.apache.commons.codec.Resources.getInputStream(Resources.java:38)
       org.apache.commons.codec.language.DaitchMokotoffSoundex.<clinit>(DaitchMokotoffSoundex.java:228)
       java.base/java.lang.Class.forName0(Native Method)
       java.base/java.lang.Class.forName(Class.java:469)
       org.mockito.internal.creation.bytebuddy.InlineBytecodeGenerator.assureInitialization(InlineBytecodeGenerator.java:236)
       [...]
  JUnit Jupiter:DaitchMokotoffSoundex_soundex_Test:testSoundexWithValidInput()
    MethodSource [className = 'org.apache.commons.codec.language.DaitchMokotoffSoundex_soundex_Test', methodName = 'testSoundexWithValidInput', methodParameterTypes = '']
    => java.lang.NoClassDefFoundError: Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
       jdk.internal.reflect.GeneratedSerializationConstructorAccessor2.newInstance(Unknown Source)
       java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:500)
       java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:481)
       org.objenesis.instantiator.sun.SunReflectionFactoryInstantiator.newInstance(SunReflectionFactoryInstantiator.java:48)
       org.objenesis.ObjenesisBase.newInstance(ObjenesisBase.java:73)
       [...]
     Caused by: java.lang.ExceptionInInitializerError: Exception java.lang.IllegalArgumentException: Unable to resolve required resource: org/apache/commons/codec/language/dmrules.txt [in thread "main"]
       org.apache.commons.codec.Resources.getInputStream(Resources.java:38)
       org.apache.commons.codec.language.DaitchMokotoffSoundex.<clinit>(DaitchMokotoffSoundex.java:228)
       java.base/java.lang.Class.forName0(Native Method)
       java.base/java.lang.Class.forName(Class.java:469)
       org.mockito.internal.creation.bytebuddy.InlineBytecodeGenerator.assureInitialization(InlineBytecodeGenerator.java:236)
       [...]
  JUnit Jupiter:DaitchMokotoffSoundex_soundex_Test:testSoundexWithSingleConsonantWithBranching()
    MethodSource [className = 'org.apache.commons.codec.language.DaitchMokotoffSoundex_soundex_Test', methodName = 'testSoundexWithSingleConsonantWithBranching', methodParameterTypes = '']
    => java.lang.NoClassDefFoundError: Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
       jdk.internal.reflect.GeneratedSerializationConstructorAccessor2.newInstance(Unknown Source)
       java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:500)
       java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:481)
       org.objenesis.instantiator.sun.SunReflectionFactoryInstantiator.newInstance(SunReflectionFactoryInstantiator.java:48)
       org.objenesis.ObjenesisBase.newInstance(ObjenesisBase.java:73)
       [...]
     Caused by: java.lang.ExceptionInInitializerError: Exception java.lang.IllegalArgumentException: Unable to resolve required resource: org/apache/commons/codec/language/dmrules.txt [in thread "main"]
       org.apache.commons.codec.Resources.getInputStream(Resources.java:38)
       org.apache.commons.codec.language.DaitchMokotoffSoundex.<clinit>(DaitchMokotoffSoundex.java:228)
       java.base/java.lang.Class.forName0(Native Method)
       java.base/java.lang.Class.forName(Class.java:469)
       org.mockito.internal.creation.bytebuddy.InlineBytecodeGenerator.assureInitialization(InlineBytecodeGenerator.java:236)
       [...]
  JUnit Jupiter:DaitchMokotoffSoundex_soundex_Test:testSoundexWithConsonantsSeparatedBySpace()
    MethodSource [className = 'org.apache.commons.codec.language.DaitchMokotoffSoundex_soundex_Test', methodName = 'testSoundexWithConsonantsSeparatedBySpace', methodParameterTypes = '']
    => java.lang.NoClassDefFoundError: Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
       jdk.internal.reflect.GeneratedSerializationConstructorAccessor2.newInstance(Unknown Source)
       java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:500)
       java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:481)
       org.objenesis.instantiator.sun.SunReflectionFactoryInstantiator.newInstance(SunReflectionFactoryInstantiator.java:48)
       org.objenesis.ObjenesisBase.newInstance(ObjenesisBase.java:73)
       [...]
     Caused by: java.lang.ExceptionInInitializerError: Exception java.lang.IllegalArgumentException: Unable to resolve required resource: org/apache/commons/codec/language/dmrules.txt [in thread "main"]
       org.apache.commons.codec.Resources.getInputStream(Resources.java:38)
       org.apache.commons.codec.language.DaitchMokotoffSoundex.<clinit>(DaitchMokotoffSoundex.java:228)
       java.base/java.lang.Class.forName0(Native Method)
       java.base/java.lang.Class.forName(Class.java:469)
       org.mockito.internal.creation.bytebuddy.InlineBytecodeGenerator.assureInitialization(InlineBytecodeGenerator.java:236)
       [...]
  JUnit Jupiter:DaitchMokotoffSoundex_soundex_Test:testSoundexWithApostrophe()
    MethodSource [className = 'org.apache.commons.codec.language.DaitchMokotoffSoundex_soundex_Test', methodName = 'testSoundexWithApostrophe', methodParameterTypes = '']
    => java.lang.NoClassDefFoundError: Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
       jdk.internal.reflect.GeneratedSerializationConstructorAccessor2.newInstance(Unknown Source)
       java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:500)
       java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:481)
       org.objenesis.instantiator.sun.SunReflectionFactoryInstantiator.newInstance(SunReflectionFactoryInstantiator.java:48)
       org.objenesis.ObjenesisBase.newInstance(ObjenesisBase.java:73)
       [...]
     Caused by: java.lang.ExceptionInInitializerError: Exception java.lang.IllegalArgumentException: Unable to resolve required resource: org/apache/commons/codec/language/dmrules.txt [in thread "main"]
       org.apache.commons.codec.Resources.getInputStream(Resources.java:38)
       org.apache.commons.codec.language.DaitchMokotoffSoundex.<clinit>(DaitchMokotoffSoundex.java:228)
       java.base/java.lang.Class.forName0(Native Method)
       java.base/java.lang.Class.forName(Class.java:469)
       org.mockito.internal.creation.bytebuddy.InlineBytecodeGenerator.assureInitialization(InlineBytecodeGenerator.java:236)
       [...]
  JUnit Jupiter:DaitchMokotoffSoundex_soundex_Test:testSoundexWithNullInput()
    MethodSource [className = 'org.apache.commons.codec.language.DaitchMokotoffSoundex_soundex_Test', methodName = 'testSoundexWithNullInput', methodParameterTypes = '']
    => java.lang.NoClassDefFoundError: Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
       jdk.internal.reflect.GeneratedSerializationConstructorAccessor2.newInstance(Unknown Source)
       java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:500)
       java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:481)
       org.objenesis.instantiator.sun.SunReflectionFactoryInstantiator.newInstance(SunReflectionFactoryInstantiator.java:48)
       org.objenesis.ObjenesisBase.newInstance(ObjenesisBase.java:73)
       [...]
     Caused by: java.lang.ExceptionInInitializerError: Exception java.lang.IllegalArgumentException: Unable to resolve required resource: org/apache/commons/codec/language/dmrules.txt [in thread "main"]
       org.apache.commons.codec.Resources.getInputStream(Resources.java:38)
       org.apache.commons.codec.language.DaitchMokotoffSoundex.<clinit>(DaitchMokotoffSoundex.java:228)
       java.base/java.lang.Class.forName0(Native Method)
       java.base/java.lang.Class.forName(Class.java:469)
       org.mockito.internal.creation.bytebuddy.InlineBytecodeGenerator.assureInitialization(InlineBytecodeGenerator.java:236)
       [...]
  JUnit Jupiter:DaitchMokotoffSoundex_soundex_Test:testSoundexWithNameIncludingVowels()
    MethodSource [className = 'org.apache.commons.codec.language.DaitchMokotoffSoundex_soundex_Test', methodName = 'testSoundexWithNameIncludingVowels', methodParameterTypes = '']
    => java.lang.NoClassDefFoundError: Could not initialize class org.apache.commons.codec.language.DaitchMokotoffSoundex
       jdk.internal.reflect.GeneratedSerializationConstructorAccessor2.newInstance(Unknown Source)
       java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:500)
       java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:481)
       org.objenesis.instantiator.sun.SunReflectionFactoryInstantiator.newInstance(SunReflectionFactoryInstantiator.java:48)
       org.objenesis.ObjenesisBase.newInstance(ObjenesisBase.java:73)
       [...]
     Caused by: java.lang.ExceptionInInitializerError: Exception java.lang.IllegalArgumentException: Unable to resolve required resource: org/apache/commons/codec/language/dmrules.txt [in thread "main"]
       org.apache.commons.codec.Resources.getInputStream(Resources.java:38)
       org.apache.commons.codec.language.DaitchMokotoffSoundex.<clinit>(DaitchMokotoffSoundex.java:228)
       java.base/java.lang.Class.forName0(Native Method)
       java.base/java.lang.Class.forName(Class.java:469)
       org.mockito.internal.creation.bytebuddy.InlineBytecodeGenerator.assureInitialization(InlineBytecodeGenerator.java:236)
       [...]

Test run finished after 431 ms
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
[         0 tests successful      ]
[        24 tests failed          ]


```


And you can use the following related information to assist you in your analysis and correction:

api document of class org.apache.commons.codec.language.DaitchMokotoffSoundex: 

 * Encodes a string into a Daitch-Mokotoff Soundex value.
 * <p>
 * The Daitch-Mokotoff Soundex algorithm is a refinement of the Russel and American Soundex algorithms, yielding greater
 * accuracy in matching especially Slavish and Yiddish surnames with similar pronunciation but differences in spelling.
 * </p>
 * <p>
 * The main differences compared to the other soundex variants are:
 * </p>
 * <ul>
 * <li>coded names are 6 digits long
 * <li>the initial character of the name is coded
 * <li>rules to encoded multi-character n-grams
 * <li>multiple possible encodings for the same name (branching)
 * </ul>
 * <p>
 * This implementation supports branching, depending on the used method:
 * <ul>
 * <li>{@link #encode(String)} - branching disabled, only the first code will be returned
 * <li>{@link #soundex(String)} - branching enabled, all codes will be returned, separated by '|'
 * </ul>
 * <p>
 * Note: this implementation has additional branching rules compared to the original description of the algorithm. The
 * rules can be customized by overriding the default rules contained in the resource file
 * {@code org/apache/commons/codec/language/dmrules.txt}.
 * </p>
 * <p>
 * This class is thread-safe.
 * </p>
 *
 * @see Soundex
 * @see <a href="https://en.wikipedia.org/wiki/Daitch%E2%80%93Mokotoff_Soundex"> Wikipedia - Daitch-Mokotoff Soundex</a>
 * @see <a href="http://www.avotaynu.com/soundex.htm">Avotaynu - Soundexing and Genealogy</a>
 *
 * @since 1.10
 

api document of method soundex(String, boolean): 

     * Perform the actual DM Soundex algorithm on the input string.
     *
     * @param source
     *            A String object to encode
     * @param branching
     *            If branching shall be performed
     * @return A string array containing all DM Soundex codes corresponding to the String supplied depending on the
     *         selected branching mode
     

dependent classes: 
class `org.apache.commons.codec.language.DaitchMokotoffSoundex`:
	dependent fields:
		java.util.Map<java.lang.Character, java.util.List<org.apache.commons.codec.language.DaitchMokotoffSoundex.Rule>> org.apache.commons.codec.language.DaitchMokotoffSoundex.RULES;
	
class `org.apache.commons.codec.binary.Base64`:
	related functions:
		method ` static byte[] toIntegerBytes(BigInteger)` returns `byte[]`, related with `org.apache.commons.codec.language.DaitchMokotoffSoundex.soundex(String, boolean)`, api document: 
     * Returns a byte-array representation of a {@code BigInteger} without sign bit.
     *
     * @param bigInt
     *            {@code BigInteger} to be converted
     * @return a byte array representation of the BigInteger parameter
     ;



Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 
