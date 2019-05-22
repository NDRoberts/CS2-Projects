package GradleBook;

import java.util.Scanner;
import java.io.*;

/**
   Project 2: Code testing of Student and Grade Item classes
   @author Nate Roberts
   @version 1.01
   
   CS 2050, Section 2
   Developed in jGrasp v2.0.5_01 on PC running
   MS Windows 10 Pro (v1803 build 17134.228)
   
   brabble (v.) - To dispute obstinately; to quarrel about trifles
           (n.) - A paltry altercation; a noisy squabble
   
   "I'm sick of following my dreams.  I'm just going to ask them where they're
   going, and hook up with them later."
      -Mitch Hedberg (1968-2005)
   
   This class contains:
      * Two directly-declared objects for testing
      * Two objects references to be instantiated using file inputs
      * A main method which executes a series of tests for the Student
         and GradeItem classes
      * A processStudentData method to populate a Student object 
         with data from the input file
      * A processGradeItemData method to populate a GradeItem object
         with data from the input file
*/

public class NateRoberts_02 {
   
   // Directly declared objects for later comparison
   static Student student1 = new Student("900380091","Nathaniel","Roberts",
                                                "robernat@msudenver.edu");
   static GradeItem gradeItem1 = new GradeItem(1,"900380091","12345",
                                             "Class Work","20180905",50,44);
   
   // Placeholder references for objects to be read from a file
   static Student student2;
   static GradeItem gradeItem2;

   // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

   public static void main(String[] args) throws IOException {
      
      // Enable command-line modification of the input file number:
      // If the user (you!) didn't supply a file number in the
	   // command line, use 01; otherwise use the supplied file number
   
	   String xx = "01";	            // Holds the input file number, 01, 02, ...
	   					               // Assume we'll use input file 01
	   if (args.length > 0) {	      // Use the supplied file number
	   	xx = args[0];
	   	if (xx.length() == 1) {    // User supplied a single digit
	   		xx = "0" + xx;	         // so add a '0' in the front
	   	} // End inner if
	   } // End outer if

      // Variables
      final String INPUT_FILENAME = "Project_02_Input" + xx + ".txt";
                                             // Build input file name w/ args
      String inputLine = new String();       // Container for a full input line
      String[] inputTokens = new String[9];  // Holds split constructor args
      
      // Test 1: Run toString method to verify directly-declared objects
      System.out.println("Running test 1a:");
      System.out.println(student1.toString());
      System.out.println();
      System.out.println("Running test 1b");
      System.out.println(gradeItem1.toString());
      System.out.println();
      
      //Test 2: Construct new objects using data read from a file
      System.out.println("Initializing test 2:");
      try {
         Scanner input = new Scanner(new File(INPUT_FILENAME));
         System.out.println("File read OK.");
         System.out.println();
         
         // Test 2a: Process new Student from file
         System.out.println("Running test 2a:");
         inputLine = input.nextLine();
         inputTokens = inputLine.split(",");
         
         if (inputTokens[0].equals("STUDENT")) {
            processStudentData(inputTokens);
            
            try {
               System.out.println("Student data:");
               System.out.println(student2.getFirstName());
               System.out.println(student2.getLastName());
               System.out.println(student2.getId());
               System.out.println(student2.getEmail());
            } // End inner try
            
            // If instantiation of student2 has failed, Get methods
            // may throw a NullPointerException
            catch (NullPointerException e) {
               System.err.println("Insufficient data to construct new Student.");
            } // End inner catch
            
            System.out.println();
         } // end If
         
         // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
         
         // Test 2b: Process new Grade Item from file
         System.out.println("Running test 2b:");
         inputLine = input.nextLine();
         inputTokens = inputLine.split(",");
         
         if (inputTokens[0].equals("GRADE ITEM")) {
            processGradeItemData(inputTokens);
            
            try {
               System.out.println("Grade Item data:");
               System.out.println(gradeItem2.getItemId());
               System.out.println(gradeItem2.getStudentId());
               System.out.println(gradeItem2.getCourseId());
               System.out.println(gradeItem2.getItemType());
               System.out.println(gradeItem2.getItemDate());
               System.out.println(gradeItem2.getMaxScore());
               System.out.println(gradeItem2.getActualScore());
            } // End inner try
            
            // If instantiation of gradeItem2 has failed, Get methods
            // may throw a NullPointerException
            catch (NullPointerException e) {
               System.err.println("Insufficient data to construct "
                                                   + "Grade Item object.");
            } // End inner catch
            
            System.out.println();
         } // end If
         
         input.close(); // Close input file
      } // End outer try
      
      // If the program is passed a bad filename,
      // it will throw an IOException
      catch (IOException e) {
         System.err.println("File I/O Exception: " + e.getMessage());
      } // End outer catch
      
      // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
      
      // Test 3: Use equals methods to compare both sets of objects
      System.out.println("Initializing test 3:");
      if (student2 == null || gradeItem2 == null) {
         System.err.println("Insufficient data for comparison test.");
      } // End if
      
      else {
         // Test 3a: Compare two Student objects using equals
         System.out.println("Running test 3a:");
         if (student1.equals(student2)) {
            System.out.println("The two Students are identical.");
         } // End inner if
         else {
            System.out.println("The two Students are different.");
         } // End inner else
         
         System.out.println();
         
         // Test 3b: Compare two Grade Item objects using equals
         System.out.println("Running test 3b:");
         if(gradeItem1.equals(gradeItem2)) {
            System.out.println("The two Grade Items are identical.");
         }
         else {
            System.out.println("The two Grade Items are different.");
         }
         System.out.println();
      } // End outer else

   } // End main

   // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
   
   public static void processStudentData(String[] data) {
      /**
         This method takes as input a String Array built from a complete
         line in the input file, confirms that the intent of the line is
         to add a new student, and uses the individual tokens to populate
         a new Student object, student2.
         
         @param   data     A string array of input variables from a file
         @return  [n/a]    populates student2 with data inputs
      */
      if (data[1].equals("ADD")) {
      
         try {
            student2 = new Student(data[2], data[3], data[4], data[5]);
         } // End try
         
         // The Student class contains IllegalArgumentExceptions to be
         // thrown in the event it is passed improperly-formatted data
         catch (IllegalArgumentException e) {
            System.err.println("Illegal argument: " + e.getMessage());
         } // End catch
         
      } // End if
      
   } // End processStudentData
   
   // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
   
   public static void processGradeItemData(String[] data) {
   /**
      This method takes as input a String Array built from a complete
      line in the input file, confirms that the intent of the line is
      to add a new Grade Item, and uses the individual tokens to populate
      a new Grade Item object, gradeItem2.
         
      @param   data     A string array of input variables from a file
      @return  [n/a]    populates gradeItem2 with data inputs
   */
      if (data[1].equals("ADD")) {
         try {
            gradeItem2 = new GradeItem(Integer.parseInt(data[2]), data[3], 
                                             data[4], data[5], data[6], 
                                             Integer.parseInt(data[7]), 
                                             Integer.parseInt(data[8]));
         }
         
         // The GradeItem class contains IllegalArgumentExceptions to be
         // thrown in the event it is passed improperly-formatted data
         catch (IllegalArgumentException e) {
            System.err.println("Illegal argument: " + e.getMessage());
         }
      }
   } // End processGradeItemData
   
} // End NateRoberts_02
