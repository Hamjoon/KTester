package org.apache.commons.codec.language;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DoubleMetaphone_handleG_Test {

    private DoubleMetaphone doubleMetaphone;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the DoubleMetaphone instance before each test
        doubleMetaphone = new DoubleMetaphone();
    }

    @Test
    void testHandleGWithValidInputs() {
        DoubleMetaphone.DoubleMetaphoneResult result = doubleMetaphone.new DoubleMetaphoneResult(doubleMetaphone.getMaxCodeLen());
        String value = "Graham";
        int index = 0;
        boolean slavoGermanic = false;
        // Invoke the method indirectly
        index = doubleMetaphone.handleG(value, result, index, slavoGermanic);
        // Check the result
        assertEquals("K", result.getPrimary());
        assertFalse(result.isComplete());
    }

    @Test
    void testHandleGWithSlavoGermanic() {
        DoubleMetaphone.DoubleMetaphoneResult result = doubleMetaphone.new DoubleMetaphoneResult(doubleMetaphone.getMaxCodeLen());
        String value = "Gorski";
        int index = 0;
        boolean slavoGermanic = true;
        // Invoke the method indirectly
        index = doubleMetaphone.handleG(value, result, index, slavoGermanic);
        // Check the result
        assertEquals("K", result.getPrimary());
        assertFalse(result.isComplete());
    }

    @Test
    void testHandleGWhenNextIsH() {
        // Test when the next character is 'H'
        DoubleMetaphone.DoubleMetaphoneResult result = doubleMetaphone.new DoubleMetaphoneResult(10);
        String value = "GHOST";
        int index = 0;
        boolean slavoGermanic = false;
        // Invoke the method indirectly
        index = doubleMetaphone.handleG(value, result, index, slavoGermanic);
        // Check the result
        // Expect index to be updated
        assertEquals(1, index);
    }

    @Test
    void testHandleGWhenNextIsNWithVowelAtZero() {
        // Test when the next character is 'N' and index is 1 with a vowel at index 0
        DoubleMetaphone.DoubleMetaphoneResult result = doubleMetaphone.new DoubleMetaphoneResult(10);
        String value = "KNIGHT";
        // index is 1 for this case
        int index = 1;
        boolean slavoGermanic = false;
        // Invoke the method indirectly
        index = doubleMetaphone.handleG(value, result, index, slavoGermanic);
        // Check the result
        // Expect index to be updated to 2
        assertEquals(2, index);
        // Expect 'KN' to be appended
        assertEquals("KN", result.getPrimary());
    }

    @Test
    void testHandleGWhenNextIsNWithoutVowelAtZero() {
        // Test when the next character is 'N' and the conditions for appending 'N' are met
        DoubleMetaphone.DoubleMetaphoneResult result = doubleMetaphone.new DoubleMetaphoneResult(10);
        String value = "NIGHT";
        int index = 0;
        boolean slavoGermanic = false;
        // Invoke the method indirectly
        index = doubleMetaphone.handleG(value, result, index, slavoGermanic);
        // Check the result
        // Expect index to be updated to 2
        assertEquals(2, index);
        // Expect 'N' to be appended
        assertEquals("N", result.getPrimary());
    }

    @Test
    void testHandleGWhenNextIsLIAndNotSlavoGermanic() {
        // Test when the next characters are 'LI' and slavoGermanic is false
        DoubleMetaphone.DoubleMetaphoneResult result = doubleMetaphone.new DoubleMetaphoneResult(10);
        String value = "LION";
        int index = 0;
        boolean slavoGermanic = false;
        // Invoke the method indirectly
        index = doubleMetaphone.handleG(value, result, index, slavoGermanic);
        // Check the result
        // Expect index to be updated to 2
        assertEquals(2, index);
        // Expect 'KL' to be appended
        assertEquals("KL", result.getPrimary());
    }

    @Test
    void testHandleGWhenNextIsYAtStart() {
        // Test when the next character is 'Y' at the start
        DoubleMetaphone.DoubleMetaphoneResult result = doubleMetaphone.new DoubleMetaphoneResult(10);
        String value = "YARD";
        int index = 0;
        boolean slavoGermanic = false;
        // Invoke the method indirectly
        index = doubleMetaphone.handleG(value, result, index, slavoGermanic);
        // Check the result
        // Expect index to be updated to 2
        assertEquals(2, index);
        // Expect 'K' and 'J' to be appended
        assertEquals("KJ", result.getPrimary());
    }

    @Test
    void testHandleGWhenNextIsEAndConditionsMet() {
        // Test when the next character is 'E' and the condition for appending 'K' is met
        DoubleMetaphone.DoubleMetaphoneResult result = doubleMetaphone.new DoubleMetaphoneResult(10);
        String value = "DANGER";
        int index = 0;
        boolean slavoGermanic = false;
        // Invoke the method indirectly
        index = doubleMetaphone.handleG(value, result, index, slavoGermanic);
        // Check the result
        // Expect index to be updated to 2
        assertEquals(2, index);
        // Expect 'K' to be appended
        assertEquals("K", result.getPrimary());
    }

    @Test
    void testHandleGWhenSubstringAGGIAtIndexOne() {
        // Test when the substring 'AGGI' is at index 1
        DoubleMetaphone.DoubleMetaphoneResult result = doubleMetaphone.new DoubleMetaphoneResult(10);
        String value = "AGGI";
        // index is 1 for this case
        int index = 1;
        boolean slavoGermanic = false;
        // Invoke the method indirectly
        index = doubleMetaphone.handleG(value, result, index, slavoGermanic);
        // Check the result
        // Expect index to be updated to 2
        assertEquals(2, index);
        // Expect 'J' to be appended
        assertEquals("J", result.getPrimary());
    }

    @Test
    void testHandleGWithEmptyString() {
        // Test with an empty string
        DoubleMetaphone.DoubleMetaphoneResult result = doubleMetaphone.new DoubleMetaphoneResult(10);
        String value = "";
        int index = 0;
        boolean slavoGermanic = false;
        // Expect an IndexOutOfBoundsException when trying to access a character
        assertThrows(IndexOutOfBoundsException.class, () -> {
            doubleMetaphone.handleG(value, result, index, slavoGermanic);
        });
    }

    @Test
    void testHandleGWithResultMaxLengthLessThanNecessary() {
        // Test with a result max length less than necessary
        DoubleMetaphone.DoubleMetaphoneResult result = doubleMetaphone.new DoubleMetaphoneResult(2);
        String value = "GHOST";
        int index = 0;
        boolean slavoGermanic = false;
        // Expect an IndexOutOfBoundsException when trying to append more characters than allowed
        assertThrows(IndexOutOfBoundsException.class, () -> {
            doubleMetaphone.handleG(value, result, index, slavoGermanic);
        });
    }

    @Test
    void testHandleGWithResultMaxLengthOfZero() {
        // Test with a result max length of 0
        DoubleMetaphone.DoubleMetaphoneResult result = doubleMetaphone.new DoubleMetaphoneResult(0);
        String value = "GHOST";
        int index = 0;
        boolean slavoGermanic = false;
        // Expect an IndexOutOfBoundsException when trying to append characters
        assertThrows(IndexOutOfBoundsException.class, () -> {
            doubleMetaphone.handleG(value, result, index, slavoGermanic);
        });
    }
}
