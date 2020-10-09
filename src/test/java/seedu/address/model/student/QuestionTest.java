package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class QuestionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Question(null, false));
    }

    @Test
    public void constructor_invalidString_throwsIllegalArgumentException() {
        String invalidString = " ";
        assertThrows(IllegalArgumentException.class, () -> new Question(invalidString, false));
    }

    @Test
    public void isValidQuestion() {
        // null question
        assertThrows(NullPointerException.class, () -> Question.isValidQuestion(null));

        // invalid question
        assertFalse(Question.isValidQuestion(" "));
        assertFalse(Question.isValidQuestion(""));

        // valid questions
        assertTrue(Question.isValidQuestion("what is 1 + 1?"));
        assertTrue(Question.isValidQuestion("Hello"));
        assertTrue(Question.isValidQuestion("Explain Newton's Second Law"));
    }

    @Test
    public void toString_equals() {
        assertEquals(new Question("Hello", false).toString(), "[(\u2718) Hello]");
        assertEquals(new Question("1+1=?", true).toString(), "[(\u2713) 1+1=?]");
    }

    @Test
    public void equals() {
        String testString = "1 + 1 = ?";
        Question test = new Question(testString, false);

        // different isResolved gives false
        assertNotEquals(test, new Question(testString, true));

        // different question gives false
        assertNotEquals(test, new Question("test", false));
        assertNotEquals(new Question(testString, true), new Question(testString, false));

        // true
        assertEquals(test, test);
        assertEquals(test, new Question(testString, false));
    }
}
