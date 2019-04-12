/**
 * The following class is a test battery written in April 2019 as part of an
 * experiment in teaching myself to use Gradle package management and JUnit
 * testing methods, and to prepare my old Computer Science 2 Java code for
 * presentation in an online portfolio repository.
 * 
 * The code to be tested was written in the Fall of 2018. It has not been
 * modified, and is presented as it was turned in, completed, to the professor.
 */

package GradleBook;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class Test_Student {
    
    Student testStudent1 = new Student("001001","Stanley","Student","em@ai.l");

    @Test
    void testGetters() {
        assertEquals("001001", testStudent1.getId());
        assertEquals("Stanley", testStudent1.getFirstName());
        assertEquals("Student", testStudent1.getLastName());
        assertEquals("em@ai.l", testStudent1.getEmail());
    }
}