
You are an experienced Java developer tasked with fixing a piece of code that contains compilation errors. Your task is to resolve these issues after analyzing the code and its compilation feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any syntactical or logical errors.
2. Analyze the compilation feedback to understand the specific errors and their locations within the code.
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

@ExtendWith(MockitoExtension.class)
class DoubleMetaphone_handleG_Test {

    private DoubleMetaphone doubleMetaphone;

    private DoubleMetaphone.DoubleMetaphoneResult doubleMetaphoneResult;

    @BeforeEach
    void setupBeforeEach() {
        doubleMetaphone = new DoubleMetaphone();
        doubleMetaphoneResult = doubleMetaphone.new DoubleMetaphoneResult(doubleMetaphone.getMaxCodeLen());
    }

    @Test
    void testHandleG() {
        // Example test case
        String value = "GHOST";
        int index = 0;
        boolean slavoGermanic = false;
        int resultIndex = doubleMetaphone.handleG(value, doubleMetaphoneResult, index, slavoGermanic);
        assertEquals(2, resultIndex);
        assertEquals("K", doubleMetaphoneResult.getPrimary());
        assertEquals("K", doubleMetaphoneResult.getAlternate());
    }

    @Test
    void testHandleG_Group1() {
        // Test case: When 'G' is followed by 'H', handleGH should be invoked
        String value = "GH";
        int index = 0;
        boolean slavoGermanic = false;
        int resultIndex = doubleMetaphone.handleG(value, doubleMetaphoneResult, index, slavoGermanic);
        // Assuming handleGH changes the index, verify the result
        // This is a placeholder, replace with the actual expected behavior
        assertNotEquals(index, resultIndex);
    }

    @Test
    void testHandleG_Group2() {
        // Test case: When 'G' is followed by 'N' and the condition is met, 'KN' and 'N' should be appended
        String value = "GN";
        int index = 0;
        boolean slavoGermanic = false;
        int resultIndex = doubleMetaphone.handleG(value, doubleMetaphoneResult, index, slavoGermanic);
        assertEquals(index + 2, resultIndex);
        assertEquals("KN", doubleMetaphoneResult.getPrimary());
        assertEquals("N", doubleMetaphoneResult.getAlternate());
    }

    @Test
    void testHandleG_Group3() {
        // Test case: When 'G' is followed by 'N' and the condition is not met, 'N' and 'KN' should be appended
        String value = "GNE";
        int index = 0;
        boolean slavoGermanic = false;
        int resultIndex = doubleMetaphone.handleG(value, doubleMetaphoneResult, index, slavoGermanic);
        assertEquals(index + 2, resultIndex);
        assertEquals("N", doubleMetaphoneResult.getPrimary());
        assertEquals("KN", doubleMetaphoneResult.getAlternate());
    }

    @Test
    void testHandleG_Group4() {
        // Test case: When 'G' is followed by 'LI' and not Slavo-Germanic, 'KL' and 'L' should be appended
        String value = "GLI";
        int index = 0;
        boolean slavoGermanic = false;
        int resultIndex = doubleMetaphone.handleG(value, doubleMetaphoneResult, index, slavoGermanic);
        assertEquals(index + 2, resultIndex);
        assertEquals("KL", doubleMetaphoneResult.getPrimary());
        assertEquals("L", doubleMetaphoneResult.getAlternate());
    }

    @Test
    void testHandleG_Group5() {
        // Test case: When 'G' is at the beginning followed by specific strings, 'K' and 'J' should be appended
        String value = "GES";
        int index = 0;
        boolean slavoGermanic = false;
        int resultIndex = doubleMetaphone.handleG(value, doubleMetaphoneResult, index, slavoGermanic);
        assertEquals(index + 2, resultIndex);
        assertEquals("K", doubleMetaphoneResult.getPrimary());
        assertEquals("J", doubleMetaphoneResult.getAlternate());
    }

    @Test
    void testHandleG_Group6() {
        // Test case: When 'G' is followed by 'ER' or 'Y' and not preceded by specific strings, 'K' and 'J' should be appended
        String value = "GER";
        int index = 0;
        boolean slavoGermanic = false;
        int resultIndex = doubleMetaphone.handleG(value, doubleMetaphoneResult, index, slavoGermanic);
        assertEquals(index + 2, resultIndex);
        assertEquals("K", doubleMetaphoneResult.getPrimary());
        assertEquals("J", doubleMetaphoneResult.getAlternate());
    }

    @Test
    void testHandleG_Group7() {
        // Test case: When 'G' is followed by 'E', 'I', 'Y' or specific strings, 'J' and 'K' should be appended
        String value = "GIE";
        int index = 0;
        boolean slavoGermanic = false;
        int resultIndex = doubleMetaphone.handleG(value, doubleMetaphoneResult, index, slavoGermanic);
        assertEquals(index + 2, resultIndex);
        assertEquals("J", doubleMetaphoneResult.getPrimary());
        assertEquals("K", doubleMetaphoneResult.getAlternate());
    }

    @Test
    void testHandleG_Group8() {
        // Test case: When 'G' is followed by another 'G', index should increase by 2
        String value = "GG";
        int index = 0;
        boolean slavoGermanic = false;
        int resultIndex = doubleMetaphone.handleG(value, doubleMetaphoneResult, index, slavoGermanic);
        assertEquals(index + 2, resultIndex);
        assertEquals("K", doubleMetaphoneResult.getPrimary());
        assertEquals("K", doubleMetaphoneResult.getAlternate());
    }

    @Test
    void testHandleG_Group9() {
        // Test case: Default case when none of the conditions are met, 'K' should be appended
        String value = "GA";
        int index = 0;
        boolean slavoGermanic = false;
        int resultIndex = doubleMetaphone.handleG(value, doubleMetaphoneResult, index, slavoGermanic);
        assertEquals(index + 1, resultIndex);
        assertEquals("K", doubleMetaphoneResult.getPrimary());
        assertEquals("K", doubleMetaphoneResult.getAlternate());
    }

    @Test
    void testHandleG_Additional1() {
        // Test case: When 'G' is followed by 'IER', 'J' should be appended
        String value = "GIER";
        int index = 0;
        boolean slavoGermanic = false;
        int resultIndex = doubleMetaphone.handleG(value, doubleMetaphoneResult, index, slavoGermanic);
        assertEquals(index + 2, resultIndex);
        assertEquals("J", doubleMetaphoneResult.getPrimary());
        // Assuming alternate remains empty
        assertEquals("", doubleMetaphoneResult.getAlternate());
    }

    @Test
    void testHandleG_Additional2() {
        // Test case: When 'G' is followed by 'OGGI', 'J' and 'K' should be appended
        String value = "GOGGI";
        int index = 0;
        boolean slavoGermanic = false;
        int resultIndex = doubleMetaphone.handleG(value, doubleMetaphoneResult, index, slavoGermanic);
        assertEquals(index + 2, resultIndex);
        assertEquals("J", doubleMetaphoneResult.getPrimary());
        assertEquals("K", doubleMetaphoneResult.getAlternate());
    }

    @Test
    void testHandleG_Additional3() {
        // Test case: When 'G' is followed by 'ER' in a Slavo-Germanic context, 'K' should be appended
        String value = "GERMAN";
        int index = 0;
        boolean slavoGermanic = true;
        int resultIndex = doubleMetaphone.handleG(value, doubleMetaphoneResult, index, slavoGermanic);
        assertEquals(index + 2, resultIndex);
        assertEquals("K", doubleMetaphoneResult.getPrimary());
        assertEquals("K", doubleMetaphoneResult.getAlternate());
    }

    @Test
    void testHandleG_Additional4() {
        // Test case: When 'G' is followed by 'ON', default case should apply, 'K' should be appended
        String value = "GON";
        int index = 0;
        boolean slavoGermanic = false;
        int resultIndex = doubleMetaphone.handleG(value, doubleMetaphoneResult, index, slavoGermanic);
        assertEquals(index + 1, resultIndex);
        assertEquals("K", doubleMetaphoneResult.getPrimary());
        assertEquals("K", doubleMetaphoneResult.getAlternate());
    }

    @Test
    void testHandleG_Additional5() {
        // Test case: When 'G' is a standalone character, 'K' should be appended
        String value = "G";
        int index = 0;
        boolean slavoGermanic = false;
        int resultIndex = doubleMetaphone.handleG(value, doubleMetaphoneResult, index, slavoGermanic);
        assertEquals(index + 1, resultIndex);
        assertEquals("K", doubleMetaphoneResult.getPrimary());
        assertEquals("K", doubleMetaphoneResult.getAlternate());
    }

    @Test
    void testHandleG_Exception1() {
        // Test case: When the input 'value' is null, should throw NullPointerException
        String value = null;
        int index = 0;
        boolean slavoGermanic = false;
        assertThrows(NullPointerException.class, () -> {
            doubleMetaphone.handleG(value, doubleMetaphoneResult, index, slavoGermanic);
        });
    }

    @Test
    void testHandleG_Exception2() {
        // Test case: When the 'result' is null, should throw NullPointerException
        String value = "G";
        int index = 0;
        boolean slavoGermanic = false;
        assertThrows(NullPointerException.class, () -> {
            doubleMetaphone.handleG(value, null, index, slavoGermanic);
        });
    }

    @Test
    void testHandleG_Exception3() {
        // Test case: When 'index' is negative, should throw IndexOutOfBoundsException
        String value = "G";
        int index = -1;
        boolean slavoGermanic = false;
        assertThrows(IndexOutOfBoundsException.class, () -> {
            doubleMetaphone.handleG(value, doubleMetaphoneResult, index, slavoGermanic);
        });
    }

    @Test
    void testHandleG_Exception4() {
        // Test case: When 'index' is out of bounds of the 'value' string length, should throw IndexOutOfBoundsException
        String value = "G";
        int index = 1000;
        boolean slavoGermanic = false;
        assertThrows(IndexOutOfBoundsException.class, () -> {
            doubleMetaphone.handleG(value, doubleMetaphoneResult, index, slavoGermanic);
        });
    }
}
```

Corresponding compilation feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
src/test/java/org/apache/commons/codec/language/DoubleMetaphone_handleG_Test.java:29: error: handleG(String,DoubleMetaphone.DoubleMetaphoneResult,int,boolean) has private access in DoubleMetaphone
        int resultIndex = doubleMetaphone.handleG(value, doubleMetaphoneResult, index, slavoGermanic);
                                         ^
src/test/java/org/apache/commons/codec/language/DoubleMetaphone_handleG_Test.java:41: error: handleG(String,DoubleMetaphone.DoubleMetaphoneResult,int,boolean) has private access in DoubleMetaphone
        int resultIndex = doubleMetaphone.handleG(value, doubleMetaphoneResult, index, slavoGermanic);
                                         ^
src/test/java/org/apache/commons/codec/language/DoubleMetaphone_handleG_Test.java:53: error: handleG(String,DoubleMetaphone.DoubleMetaphoneResult,int,boolean) has private access in DoubleMetaphone
        int resultIndex = doubleMetaphone.handleG(value, doubleMetaphoneResult, index, slavoGermanic);
                                         ^
src/test/java/org/apache/commons/codec/language/DoubleMetaphone_handleG_Test.java:65: error: handleG(String,DoubleMetaphone.DoubleMetaphoneResult,int,boolean) has private access in DoubleMetaphone
        int resultIndex = doubleMetaphone.handleG(value, doubleMetaphoneResult, index, slavoGermanic);
                                         ^
src/test/java/org/apache/commons/codec/language/DoubleMetaphone_handleG_Test.java:77: error: handleG(String,DoubleMetaphone.DoubleMetaphoneResult,int,boolean) has private access in DoubleMetaphone
        int resultIndex = doubleMetaphone.handleG(value, doubleMetaphoneResult, index, slavoGermanic);
                                         ^
src/test/java/org/apache/commons/codec/language/DoubleMetaphone_handleG_Test.java:89: error: handleG(String,DoubleMetaphone.DoubleMetaphoneResult,int,boolean) has private access in DoubleMetaphone
        int resultIndex = doubleMetaphone.handleG(value, doubleMetaphoneResult, index, slavoGermanic);
                                         ^
src/test/java/org/apache/commons/codec/language/DoubleMetaphone_handleG_Test.java:101: error: handleG(String,DoubleMetaphone.DoubleMetaphoneResult,int,boolean) has private access in DoubleMetaphone
        int resultIndex = doubleMetaphone.handleG(value, doubleMetaphoneResult, index, slavoGermanic);
                                         ^
src/test/java/org/apache/commons/codec/language/DoubleMetaphone_handleG_Test.java:113: error: handleG(String,DoubleMetaphone.DoubleMetaphoneResult,int,boolean) has private access in DoubleMetaphone
        int resultIndex = doubleMetaphone.handleG(value, doubleMetaphoneResult, index, slavoGermanic);
                                         ^
src/test/java/org/apache/commons/codec/language/DoubleMetaphone_handleG_Test.java:125: error: handleG(String,DoubleMetaphone.DoubleMetaphoneResult,int,boolean) has private access in DoubleMetaphone
        int resultIndex = doubleMetaphone.handleG(value, doubleMetaphoneResult, index, slavoGermanic);
                                         ^
src/test/java/org/apache/commons/codec/language/DoubleMetaphone_handleG_Test.java:137: error: handleG(String,DoubleMetaphone.DoubleMetaphoneResult,int,boolean) has private access in DoubleMetaphone
        int resultIndex = doubleMetaphone.handleG(value, doubleMetaphoneResult, index, slavoGermanic);
                                         ^
src/test/java/org/apache/commons/codec/language/DoubleMetaphone_handleG_Test.java:149: error: handleG(String,DoubleMetaphone.DoubleMetaphoneResult,int,boolean) has private access in DoubleMetaphone
        int resultIndex = doubleMetaphone.handleG(value, doubleMetaphoneResult, index, slavoGermanic);
                                         ^
src/test/java/org/apache/commons/codec/language/DoubleMetaphone_handleG_Test.java:162: error: handleG(String,DoubleMetaphone.DoubleMetaphoneResult,int,boolean) has private access in DoubleMetaphone
        int resultIndex = doubleMetaphone.handleG(value, doubleMetaphoneResult, index, slavoGermanic);
                                         ^
src/test/java/org/apache/commons/codec/language/DoubleMetaphone_handleG_Test.java:174: error: handleG(String,DoubleMetaphone.DoubleMetaphoneResult,int,boolean) has private access in DoubleMetaphone
        int resultIndex = doubleMetaphone.handleG(value, doubleMetaphoneResult, index, slavoGermanic);
                                         ^
src/test/java/org/apache/commons/codec/language/DoubleMetaphone_handleG_Test.java:186: error: handleG(String,DoubleMetaphone.DoubleMetaphoneResult,int,boolean) has private access in DoubleMetaphone
        int resultIndex = doubleMetaphone.handleG(value, doubleMetaphoneResult, index, slavoGermanic);
                                         ^
src/test/java/org/apache/commons/codec/language/DoubleMetaphone_handleG_Test.java:198: error: handleG(String,DoubleMetaphone.DoubleMetaphoneResult,int,boolean) has private access in DoubleMetaphone
        int resultIndex = doubleMetaphone.handleG(value, doubleMetaphoneResult, index, slavoGermanic);
                                         ^
src/test/java/org/apache/commons/codec/language/DoubleMetaphone_handleG_Test.java:211: error: handleG(String,DoubleMetaphone.DoubleMetaphoneResult,int,boolean) has private access in DoubleMetaphone
            doubleMetaphone.handleG(value, doubleMetaphoneResult, index, slavoGermanic);
                           ^
src/test/java/org/apache/commons/codec/language/DoubleMetaphone_handleG_Test.java:222: error: handleG(String,DoubleMetaphone.DoubleMetaphoneResult,int,boolean) has private access in DoubleMetaphone
            doubleMetaphone.handleG(value, null, index, slavoGermanic);
                           ^
src/test/java/org/apache/commons/codec/language/DoubleMetaphone_handleG_Test.java:233: error: handleG(String,DoubleMetaphone.DoubleMetaphoneResult,int,boolean) has private access in DoubleMetaphone
            doubleMetaphone.handleG(value, doubleMetaphoneResult, index, slavoGermanic);
                           ^
src/test/java/org/apache/commons/codec/language/DoubleMetaphone_handleG_Test.java:244: error: handleG(String,DoubleMetaphone.DoubleMetaphoneResult,int,boolean) has private access in DoubleMetaphone
            doubleMetaphone.handleG(value, doubleMetaphoneResult, index, slavoGermanic);
                           ^
19 errors

```

And you can use the following related information to assist you in your analysis and correction:
```
{'api document of class org.apache.commons.codec.language.DoubleMetaphone': '\n * Encodes a string into a double metaphone value. This Implementation is based on the algorithm by <CITE>Lawrence\n * Philips</CITE>.\n * <p>\n * This class is conditionally thread-safe. The instance field for the maximum code length is mutable\n * {@link #setMaxCodeLen(int)} but is not volatile, and accesses are not synchronized. If an instance of the class is\n * shared between threads, the caller needs to ensure that suitable synchronization is used to ensure safe publication\n * of the value between threads, and must not invoke {@link #setMaxCodeLen(int)} after initial setup.\n * </p>\n *\n * @see <a href="https://drdobbs.com/the-double-metaphone-search-algorithm/184401251?pgno=2">Dr. Dobbs Original Article</a>\n * @see <a href="https://en.wikipedia.org/wiki/Metaphone">Wikipedia Metaphone</a>\n ', 'api document of method handleG(String, DoubleMetaphoneResult, int, boolean)': "\n     * Handles 'G' cases.\n     ", 'return type': 'int', 'dependent classes': "class `org.apache.commons.codec.language.DoubleMetaphone.DoubleMetaphoneResult`:\n\t\nclass `org.apache.commons.codec.language.DoubleMetaphone`:\n\tdependent fields:\n\t\tjava.lang.String[] org.apache.commons.codec.language.DoubleMetaphone.ES_EP_EB_EL_EY_IB_IL_IN_IE_EI_ER;\n\trelated functions:\n\t\tmethod `private int handleSC(String, DoubleMetaphoneResult, int)` returns `int`, related with `org.apache.commons.codec.language.DoubleMetaphone.handleG(String, DoubleMetaphoneResult, int, boolean)`, api document: \n     * Handles 'SC' cases.\n     ;\n\t\tmethod `private int handleW(String, DoubleMetaphoneResult, int)` returns `int`, related with `org.apache.commons.codec.language.DoubleMetaphone.handleG(String, DoubleMetaphoneResult, int, boolean)`, api document: \n     * Handles 'W' cases.\n     ;\n\t\tmethod `private boolean conditionC0(String, int)` returns `boolean`, related with `org.apache.commons.codec.language.DoubleMetaphone.handleG(String, DoubleMetaphoneResult, int, boolean)`, api document: \n     * Complex condition 0 for 'C'.\n     ;\n\t\tmethod `private int handleJ(String, DoubleMetaphoneResult, int, boolean)` returns `int`, related with `org.apache.commons.codec.language.DoubleMetaphone.handleG(String, DoubleMetaphoneResult, int, boolean)`, api document: \n     * Handles 'J' cases.\n     ;\n\t\tmethod `private int handleCC(String, DoubleMetaphoneResult, int)` returns `int`, related with `org.apache.commons.codec.language.DoubleMetaphone.handleG(String, DoubleMetaphoneResult, int, boolean)`, api document: \n     * Handles 'CC' cases.\n     ;"}
```

**Note**: If the method-to-test in the @Test function is a private method, you can use **reflection** to call it.

Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 


