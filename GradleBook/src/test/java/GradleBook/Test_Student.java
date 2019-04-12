/**
 * The following class is a test bench written in April 2019 as part of an
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
    
    Student testStudent1 = new Student("001001","Stanley","Student","em@il.test");

    @Test
    void testConstructor() {
        Student testStudent2 = new Student("1","Sally","Student,","you@me.us");
        assertNotNull(testStudent2);

        // Constructor exceptions
        assertThrows(IllegalArgumentException.class, 
            () -> { Student testStudent3 = new Student("","Stewart","Student","12@34.5"); });
        assertThrows(IllegalArgumentException.class, 
            () -> { Student testStudent3 = new Student("01","","Student","12@34.5"); });
        assertThrows(IllegalArgumentException.class, 
            () -> { Student testStudent3 = new Student("01","Stewart","","12@34.5"); });
        assertThrows(IllegalArgumentException.class, 
            () -> { Student testStudent3 = new Student("01","Stewart","Student",""); });
        assertThrows(IllegalArgumentException.class, 
            () -> { Student testStudent3 = new Student("01","Stewart","Student","NotAnAddress"); });
    }

    @Test
    void testGetters() {
        assertEquals("001001", testStudent1.getId());
        assertEquals("Stanley", testStudent1.getFirstName());
        assertEquals("Student", testStudent1.getLastName());
        assertEquals("em@il.test", testStudent1.getEmail());
    }
    
    @Test
    void testToString() {
        assertEquals("Student ID: 001001\nLast name: Student"
            + "\nFirst name: Stanley\nEmail address: em@il.test", 
            testStudent1.toString());
    }

    @Test
    void testEquals() {
        Student testStudent4 = new Student("001001","Stanley","Student","em@il.test");
        Student testStudent5 = new Student("001001","Stanley","Studnet","em@il.test");
        assertTrue(testStudent1.equals(testStudent4));
        assertFalse(testStudent1.equals(testStudent5));
    }
}