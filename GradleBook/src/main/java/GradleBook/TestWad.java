/**
 * Practice writing test cases for JUnit
 */

package GradleBook;

import java.util.Random;

public class TestWad {

    int integerVal;
    double doubleVal;
    char charVal;
    boolean booleanVal;

    public static void main(String[] args) {

        TestWad test = new TestWad();
        System.out.println("The following is a wad of test:");
        System.out.println(test.toString());

        test.mutate('F');
        System.out.println(test.toString());

        test.mutate(7);
        System.out.println(test.toString());

        test.mutate(false);
        System.out.println(test.toString());

        TestWad test2 = buildWad();
        System.out.println(test2.toString());

    }

    public TestWad() {
        integerVal = 10;
        doubleVal = 1.3;
        charVal = 'Q';
        booleanVal = true;
    }

    public TestWad(int in, double dou, char cha, boolean boo) {
        integerVal = in;
        doubleVal = dou;
        charVal = cha;
        booleanVal = boo;
    }

    public String toString() {
        return "> " + integerVal + " " + doubleVal + " " + charVal + " " + booleanVal + ";";
    }

    public String talkWords() {
        String result = "> " + charVal + (char)(doubleVal * charVal) + (integerVal % charVal) + " " + booleanVal;
        return result;
    }

    public void mutate(int nu) {
        integerVal = nu;
    }

    public void mutate(double nu) {
        doubleVal = nu;
    }

    public void mutate(char nu) {
        charVal = nu;
    }

    public void mutate(boolean nu) {
        booleanVal = nu;
    }

    public static TestWad buildWad() {
        TestWad newGuy;
        Random maker = new Random();
        newGuy = new TestWad(maker.nextInt(255),maker.nextDouble(),(char)maker.nextInt(),maker.nextBoolean());
        return newGuy;
    }

}