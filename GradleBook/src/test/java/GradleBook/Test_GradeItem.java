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

public class Test_GradeItem {

    GradeItem testItem1 = new GradeItem(1,"001001","54321","HW","20190412",100,95);

    @Test
    void testConstructor() {
        GradeItem  testItem2 = new GradeItem(2,"002002","54321","Quiz","20190412",25,24);
        assertNotNull(testItem2);

        // Constructor exceptions
        assertThrows(IllegalArgumentException.class, 
            () -> { GradeItem testItem3 = new GradeItem(3,"","54321","Test","20190412",54,17); });
        assertThrows(IllegalArgumentException.class, 
            () -> { GradeItem testItem3 = new GradeItem(3,"003003","","Test","20190412",54,17); });
        assertThrows(IllegalArgumentException.class, 
            () -> { GradeItem testItem3 = new GradeItem(3,"003003","54321","","20190412",54,17); });
        assertThrows(IllegalArgumentException.class, 
            () -> { GradeItem testItem3 = new GradeItem(3,"003003","54321","Test","",54,17); });
        assertThrows(IllegalArgumentException.class, 
            () -> { GradeItem testItem3 = new GradeItem(3,"003003","54321","Bishopric","20190412",54,17); });
        assertThrows(IllegalArgumentException.class, 
            () -> { GradeItem testItem3 = new GradeItem(3,"003003","54321","Test","20190412",-54,17); });
        assertThrows(IllegalArgumentException.class, 
            () -> { GradeItem testItem3 = new GradeItem(3,"003003","54321","Test","20190412",54,55); });
        assertThrows(IllegalArgumentException.class, 
            () -> { GradeItem testItem3 = new GradeItem(3,"003003","54321","Test","10",54,17); });

    }

    @Test
    void testGetters() {
        assertEquals(1, testItem1.getItemId());
        assertEquals("001001", testItem1.getStudentId());
        assertEquals("54321", testItem1.getCourseId());
        assertEquals("HW", testItem1.getItemType());
        assertEquals("20190412", testItem1.getItemDate());
        assertEquals(100, testItem1.getMaxScore());
        assertEquals(95, testItem1.getActualScore());
    }

    @Test
    void testToString() {
        assertEquals("Item: 1\nStudent: 001001\nCourse: 54321"
            + "\nItem type: HW\nItem date: 20190412"
            + "\nActual score: 95\nMaximum score: 100", testItem1.toString());;
    }

    @Test
    void testEquals() {
        GradeItem testItem4 = new GradeItem(1,"001001","54321","HW","20190412",100,95);
        GradeItem testItem5 = new GradeItem(5,"001001","54321","Final","20190412",100,87);
        assertTrue(testItem1.equals(testItem4));
        assertFalse(testItem1.equals(testItem5));
    }
}