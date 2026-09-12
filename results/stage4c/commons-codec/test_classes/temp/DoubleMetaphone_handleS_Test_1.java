package org.apache.commons.codec.language;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class DoubleMetaphone_handleS_Test {

    private DoubleMetaphone doubleMetaphone;

    private DoubleMetaphone.DoubleMetaphoneResult result;

    @BeforeEach
    void setupBeforeEach() {
        doubleMetaphone = new DoubleMetaphone();
        result = doubleMetaphone.new DoubleMetaphoneResult(doubleMetaphone.getMaxCodeLen());
    }

    private int invokeHandleS(String value, DoubleMetaphone.DoubleMetaphoneResult result, int index, boolean slavoGermanic) throws Exception {
        Method handleSMethod = DoubleMetaphone.class.getDeclaredMethod("handleS", String.class, DoubleMetaphone.DoubleMetaphoneResult.class, int.class, boolean.class);
        handleSMethod.setAccessible(true);
        return (int) handleSMethod.invoke(doubleMetaphone, value, result, index, slavoGermanic);
    }

    @Test
    void testHandleS_SpecialCaseIsland() {
        String value = "ISLAND";
        int index = 2;
        boolean slavoGermanic = false;
        int newIndex = invokeHandleS(value, result, index, slavoGermanic);
        assertEquals(3, newIndex);
        assertEquals("", result.getPrimary());
        assertEquals("", result.getAlternate());
    }

    @Test
    void testHandleS_SpecialCaseSugar() {
        String value = "SUGAR";
        int index = 0;
        boolean slavoGermanic = false;
        int newIndex = invokeHandleS(value, result, index, slavoGermanic);
        assertEquals(1, newIndex);
        assertEquals("X", result.getPrimary());
        assertEquals("S", result.getAlternate());
    }

    @Test
    void testHandleS_SH() {
        String value = "SHELTER";
        int index = 0;
        boolean slavoGermanic = false;
        int newIndex = invokeHandleS(value, result, index, slavoGermanic);
        assertEquals(2, newIndex);
        assertEquals("X", result.getPrimary());
        assertEquals("X", result.getAlternate());
    }

    @Test
    void testHandleS_SIO() {
        String value = "SIOUX";
        int index = 0;
        boolean slavoGermanic = false;
        int newIndex = invokeHandleS(value, result, index, slavoGermanic);
        assertEquals(3, newIndex);
        assertEquals("S", result.getPrimary());
        assertEquals("X", result.getAlternate());
    }

    @Test
    void testHandleS_Germanic() {
        String value = "SMITH";
        int index = 0;
        boolean slavoGermanic = false;
        int newIndex = invokeHandleS(value, result, index, slavoGermanic);
        assertEquals(1, newIndex);
        assertEquals("S", result.getPrimary());
        assertEquals("X", result.getAlternate());
    }

    @Test
    void testHandleS_SZ() {
        String value = "SZABO";
        int index = 0;
        boolean slavoGermanic = false;
        int newIndex = invokeHandleS(value, result, index, slavoGermanic);
        assertEquals(2, newIndex);
        assertEquals("S", result.getPrimary());
        assertEquals("X", result.getAlternate());
    }

    @Test
    void testHandleS_SC() {
        String value = "SCENE";
        int index = 0;
        boolean slavoGermanic = false;
        int expectedIndex = index + 1;
        int newIndex = invokeHandleS(value, result, index, slavoGermanic);
        assertEquals(expectedIndex, newIndex);
    }

    @Test
    void testHandleS_FinalS() {
        String value = "RESNAIS";
        int index = 5;
        boolean slavoGermanic = false;
        int newIndex = invokeHandleS(value, result, index, slavoGermanic);
        assertEquals(6, newIndex);
        assertEquals("", result.getPrimary());
        assertEquals("S", result.getAlternate());
    }

    @Test
    void testHandleS_SSSZ() {
        String value = "SASSY";
        int index = 0;
        boolean slavoGermanic = false;
        int newIndex = invokeHandleS(value, result, index, slavoGermanic);
        assertEquals(2, newIndex);
        assertEquals("S", result.getPrimary());
        assertEquals("S", result.getAlternate());
    }

    @Test
    void testHandleS_SHGermanic() {
        String value = "SHEIM";
        int index = 0;
        boolean slavoGermanic = false;
        int newIndex = invokeHandleS(value, result, index, slavoGermanic);
        assertEquals(2, newIndex);
        assertEquals("S", result.getPrimary());
        assertEquals("S", result.getAlternate());
    }

    @Test
    void testHandleS_SIA_SlavoGermanicTrue() {
        String value = "SIA";
        int index = 0;
        boolean slavoGermanic = true;
        int newIndex = invokeHandleS(value, result, index, slavoGermanic);
        assertEquals(3, newIndex);
        assertEquals("S", result.getPrimary());
        assertEquals("S", result.getAlternate());
    }

    @Test
    void testHandleS_SIAN_SlavoGermanicFalse() {
        String value = "SIAN";
        int index = 0;
        boolean slavoGermanic = false;
        int newIndex = invokeHandleS(value, result, index, slavoGermanic);
        assertEquals(3, newIndex);
        assertEquals("S", result.getPrimary());
        assertEquals("X", result.getAlternate());
    }

    @Test
    void testHandleS_InitialSFollowedByA() {
        String value = "SAINT";
        int index = 0;
        boolean slavoGermanic = false;
        int newIndex = invokeHandleS(value, result, index, slavoGermanic);
        assertEquals(1, newIndex);
        assertEquals("S", result.getPrimary());
        assertEquals("X", result.getAlternate());
    }

    @Test
    void testHandleS_InitialSFollowedByO() {
        String value = "SOIL";
        int index = 0;
        boolean slavoGermanic = false;
        int newIndex = invokeHandleS(value, result, index, slavoGermanic);
        assertEquals(1, newIndex);
        assertEquals("S", result.getPrimary());
        assertEquals("X", result.getAlternate());
    }

    @Test
    void testHandleS_InitialSFollowedByO_M() {
        String value = "SOMEBODY";
        int index = 0;
        boolean slavoGermanic = false;
        int newIndex = invokeHandleS(value, result, index, slavoGermanic);
        assertEquals(1, newIndex);
        assertEquals("S", result.getPrimary());
        assertEquals("X", result.getAlternate());
    }

    @Test
    void testHandleS_NullInputString() {
        assertThrows(NullPointerException.class, () -> {
            try {
                invokeHandleS(null, result, 0, false);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    void testHandleS_NullResultObject() {
        assertThrows(NullPointerException.class, () -> {
            try {
                invokeHandleS("TEST", null, 0, false);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    void testHandleS_NegativeIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            try {
                invokeHandleS("TEST", result, -1, false);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    void testHandleS_IndexGreaterThanLength() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            try {
                invokeHandleS("TEST", result, 5, false);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
