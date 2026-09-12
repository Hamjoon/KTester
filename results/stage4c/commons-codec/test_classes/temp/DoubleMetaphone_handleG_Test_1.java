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
    void testHandleG() {
        String value = "GHOST";
        int index = 0;
        boolean slavoGermanic = false;
        int resultIndex = invokeHandleG(value, doubleMetaphoneResult, index, slavoGermanic);
        assertEquals(2, resultIndex);
        assertEquals("K", doubleMetaphoneResult.getPrimary());
        assertEquals("K", doubleMetaphoneResult.getAlternate());
    }

    @Test
    void testHandleG_Group1() {
        String value = "GH";
        int index = 0;
        boolean slavoGermanic = false;
        int resultIndex = invokeHandleG(value, doubleMetaphoneResult, index, slavoGermanic);
        assertNotEquals(index, resultIndex);
    }

    @Test
    void testHandleG_Group2() {
        String value = "GN";
        int index = 0;
        boolean slavoGermanic = false;
        int resultIndex = invokeHandleG(value, doubleMetaphoneResult, index, slavoGermanic);
        assertEquals(index + 2, resultIndex);
        assertEquals("KN", doubleMetaphoneResult.getPrimary());
        assertEquals("N", doubleMetaphoneResult.getAlternate());
    }

    @Test
    void testHandleG_Group3() {
        String value = "GNE";
        int index = 0;
        boolean slavoGermanic = false;
        int resultIndex = invokeHandleG(value, doubleMetaphoneResult, index, slavoGermanic);
        assertEquals(index + 2, resultIndex);
        assertEquals("N", doubleMetaphoneResult.getPrimary());
        assertEquals("KN", doubleMetaphoneResult.getAlternate());
    }

    @Test
    void testHandleG_Group4() {
        String value = "GLI";
        int index = 0;
        boolean slavoGermanic = false;
        int resultIndex = invokeHandleG(value, doubleMetaphoneResult, index, slavoGermanic);
        assertEquals(index + 2, resultIndex);
        assertEquals("KL", doubleMetaphoneResult.getPrimary());
        assertEquals("L", doubleMetaphoneResult.getAlternate());
    }

    @Test
    void testHandleG_Group5() {
        String value = "GES";
        int index = 0;
        boolean slavoGermanic = false;
        int resultIndex = invokeHandleG(value, doubleMetaphoneResult, index, slavoGermanic);
        assertEquals(index + 2, resultIndex);
        assertEquals("K", doubleMetaphoneResult.getPrimary());
        assertEquals("J", doubleMetaphoneResult.getAlternate());
    }

    @Test
    void testHandleG_Group6() {
        String value = "GER";
        int index = 0;
        boolean slavoGermanic = false;
        int resultIndex = invokeHandleG(value, doubleMetaphoneResult, index, slavoGermanic);
        assertEquals(index + 2, resultIndex);
        assertEquals("K", doubleMetaphoneResult.getPrimary());
        assertEquals("J", doubleMetaphoneResult.getAlternate());
    }

    @Test
    void testHandleG_Group7() {
        String value = "GIE";
        int index = 0;
        boolean slavoGermanic = false;
        int resultIndex = invokeHandleG(value, doubleMetaphoneResult, index, slavoGermanic);
        assertEquals(index + 2, resultIndex);
        assertEquals("J", doubleMetaphoneResult.getPrimary());
        assertEquals("K", doubleMetaphoneResult.getAlternate());
    }

    @Test
    void testHandleG_Group8() {
        String value = "GG";
        int index = 0;
        boolean slavoGermanic = false;
        int resultIndex = invokeHandleG(value, doubleMetaphoneResult, index, slavoGermanic);
        assertEquals(index + 2, resultIndex);
        assertEquals("K", doubleMetaphoneResult.getPrimary());
        assertEquals("K", doubleMetaphoneResult.getAlternate());
    }

    @Test
    void testHandleG_Group9() {
        String value = "GA";
        int index = 0;
        boolean slavoGermanic = false;
        int resultIndex = invokeHandleG(value, doubleMetaphoneResult, index, slavoGermanic);
        assertEquals(index + 1, resultIndex);
        assertEquals("K", doubleMetaphoneResult.getPrimary());
        assertEquals("K", doubleMetaphoneResult.getAlternate());
    }

    @Test
    void testHandleG_Additional1() {
        String value = "GIER";
        int index = 0;
        boolean slavoGermanic = false;
        int resultIndex = invokeHandleG(value, doubleMetaphoneResult, index, slavoGermanic);
        assertEquals(index + 2, resultIndex);
        assertEquals("J", doubleMetaphoneResult.getPrimary());
        assertEquals("", doubleMetaphoneResult.getAlternate());
    }

    @Test
    void testHandleG_Additional2() {
        String value = "GOGGI";
        int index = 0;
        boolean slavoGermanic = false;
        int resultIndex = invokeHandleG(value, doubleMetaphoneResult, index, slavoGermanic);
        assertEquals(index + 2, resultIndex);
        assertEquals("J", doubleMetaphoneResult.getPrimary());
        assertEquals("K", doubleMetaphoneResult.getAlternate());
    }

    @Test
    void testHandleG_Additional3() {
        String value = "GERMAN";
        int index = 0;
        boolean slavoGermanic = true;
        int resultIndex = invokeHandleG(value, doubleMetaphoneResult, index, slavoGermanic);
        assertEquals(index + 2, resultIndex);
        assertEquals("K", doubleMetaphoneResult.getPrimary());
        assertEquals("K", doubleMetaphoneResult.getAlternate());
    }

    @Test
    void testHandleG_Additional4() {
        String value = "GON";
        int index = 0;
        boolean slavoGermanic = false;
        int resultIndex = invokeHandleG(value, doubleMetaphoneResult, index, slavoGermanic);
        assertEquals(index + 1, resultIndex);
        assertEquals("K", doubleMetaphoneResult.getPrimary());
        assertEquals("K", doubleMetaphoneResult.getAlternate());
    }

    @Test
    void testHandleG_Additional5() {
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
