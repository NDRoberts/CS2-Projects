/**
 * Tests for the TestWad.java class
 */

package GradleBook;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

 public class Test_TestWad {

    TestWad test1 = new TestWad();
    TestWad test2 = new TestWad(1, 2.0, 'k', false);

     @Test
     public void testConstructors() {
         assertEquals(10, test1.integerVal);
         assertEquals(1.3, test1.doubleVal);
         assertEquals('Q', test1.charVal);
         assertTrue(test1.booleanVal);

         assertEquals(1, test2.integerVal);
         assertEquals(2.0, test2.doubleVal);
         assertEquals('k', test2.charVal);
         assertFalse(test2.booleanVal);
     }

     @Test
     public void testMutate() {
         test1.mutate(42);
         assertEquals(42, test1.integerVal);
         test1.mutate(3.14159);
         assertEquals(3.14159, test1.doubleVal);
         test1.mutate('j');
         assertEquals('j',test1.charVal);
         test1.mutate(false);
         assertFalse(test1.booleanVal);
     }

     @Test
     public void testToString() {
         assertEquals("> 10 1.3 Q true;", test1.toString());
         assertNotEquals("This is the wrong string.", test2.toString());
     }
     @Test
     public void testBuilder() {
         TestWad test3 = TestWad.buildWad();
         assertNotNull(test3);
     }

 }