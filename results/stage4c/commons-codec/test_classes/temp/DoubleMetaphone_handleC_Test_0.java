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

    private DoubleMetaphone.DoubleMetaphoneResult doubleMetaphoneResult;

    @BeforeEach
    void setupBeforeEach() {
        doubleMetaphone = new DoubleMetaphone();
        doubleMetaphoneResult = doubleMetaphone.new DoubleMetaphoneResult(doubleMetaphone.getMaxCodeLen());
    }

    @Test
    void testHandleC_Caeser() {
        String value = "CAESAR";
        int index = 0;
        int newIndex = doubleMetaphone.handleC(value, doubleMetaphoneResult, index);
        assertEquals(2, newIndex);
        assertEquals("S", doubleMetaphoneResult.getPrimary());
    }

    @Test
    void testHandleC_CH() {
        String value = "CHURCH";
        int index = 0;
        int newIndex = doubleMetaphone.handleC(value, doubleMetaphoneResult, index);
        // Assuming handleCH is correctly implemented and returns the correct index
        assertTrue(newIndex > index);
    }

    @Test
    void testHandleC_CIA() {
        String value = "CIAO";
        int index = 0;
        int newIndex = doubleMetaphone.handleC(value, doubleMetaphoneResult, index);
        assertEquals(3, newIndex);
        assertEquals("X", doubleMetaphoneResult.getPrimary());
    }

    @Test
    void testHandleC_Default() {
        String value = "CAT";
        int index = 0;
        int newIndex = doubleMetaphone.handleC(value, doubleMetaphoneResult, index);
        assertEquals(1, newIndex);
        assertEquals("K", doubleMetaphoneResult.getPrimary());
    }

    @Test
    void testHandleC_CZ() {
        String value = "CZERNY";
        int index = 0;
        int newIndex = doubleMetaphone.handleC(value, doubleMetaphoneResult, index);
        assertEquals(2, newIndex);
        assertEquals("S", doubleMetaphoneResult.getPrimary());
        assertEquals("X", doubleMetaphoneResult.getAlternate());
    }

    @Test
    void testHandleC_CK() {
        String value = "ROCK";
        int index = 0;
        int newIndex = doubleMetaphone.handleC(value, doubleMetaphoneResult, index);
        assertEquals(2, newIndex);
        assertEquals("K", doubleMetaphoneResult.getPrimary());
    }

    @Test
    void testHandleC_CI() {
        String value = "CIRCLE";
        int index = 0;
        int newIndex = doubleMetaphone.handleC(value, doubleMetaphoneResult, index);
        assertEquals(2, newIndex);
        assertEquals("S", doubleMetaphoneResult.getPrimary());
    }

    @Test
    void testHandleC_Mac() {
        String value = "MAC GREGOR";
        int index = 0;
        int newIndex = doubleMetaphone.handleC(value, doubleMetaphoneResult, index);
        assertEquals(3, newIndex);
        assertEquals("K", doubleMetaphoneResult.getPrimary());
    }

    @Test
    void testHandleC_CIO() {
        String value = "CIO";
        int index = 0;
        int newIndex = doubleMetaphone.handleC(value, doubleMetaphoneResult, index);
        assertEquals(2, newIndex);
        assertEquals("S", doubleMetaphoneResult.getPrimary());
        assertEquals("X", doubleMetaphoneResult.getAlternate());
    }

    @Test
    void testHandleC_Exception_NullValue() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            doubleMetaphone.handleC(null, doubleMetaphoneResult, 0);
        });
        assertNotNull(exception);
    }

    @Test
    void testHandleC_Exception_NullResult() {
        String value = "SOMESTRING";
        Exception exception = assertThrows(NullPointerException.class, () -> {
            doubleMetaphone.handleC(value, null, 0);
        });
        assertNotNull(exception);
    }

    @Test
    void testHandleC_Exception_NegativeIndex() {
        String value = "SOMESTRING";
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            doubleMetaphone.handleC(value, doubleMetaphoneResult, -1);
        });
        assertNotNull(exception);
    }

    @Test
    void testHandleC_Exception_IndexOutOfBounds() {
        String value = "SOMESTRING";
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            doubleMetaphone.handleC(value, doubleMetaphoneResult, 100);
        });
        assertNotNull(exception);
    }
}
