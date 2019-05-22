package GradleBook;

import java.io.*;
import java.util.Scanner;

/**
   Project 4: Main class (with Linked Lists) for Gradebook application
   @author Nate Roberts
   @version 1.0
   @throws IOException if input file is missing
   
   CS 2050, Section 2
   Developed in jGrasp v2.0.5_01 on PC running
      MS Windows 10 Pro (v1803 build 17134.285)
   
   WORD
   
   "The fact that some geniuses were laughed at does not imply that all who are
   laughed at are geniuses.  They laughed at Columbus, they laughed at Fulton, 
   they laughed at the Wright brothers.  But they also laughed at Bozo the 
   Clown."
   -Carl Sagan (1934 - 1996)
   
   This main class contains code to process data from an input file containing
      both student and grade item data, parsing the raw text into manipulable
      objects.  It then prepares an ouput report by sorting grade items
      according to the student with which they are associated, and calculating
      an overall grade percentage.
*/

public class NateRoberts_04 {

   // Static variables
   private static NodeList<Student> listOfStudents;     // NodeList to store
                                                        // all Student objects
   private static NodeList<GradeItem> listOfGradeItems; // NodeList to store
                                                        // all Grade Item objects
   
   // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
   
   public static void main(String[] args) throws IOException {
   
      // Enable command-line modification of the input file number:
      // If the user (you!) didn�t supply a file number in the
	   // command line, use 01; otherwise use the supplied file number
   
	   String xx = "01";	            // Holds the input file number, 01, 02, �
	   					               // Assume we�ll use input file 01
	   if (args.length > 0) {	      // Use the supplied file number
	   	xx = args[0];
	   	if (xx.length() == 1) {    // User supplied a single digit
	   		xx = "0" + xx;	         // � so add a �0� in the front
	   	} // End inner if
	   } // End outer if
      
      final String INPUT_FILE = "hw4input" + xx + ".txt";
                     // Construct input file name using command-line arguments
      final String OUTPUT_FILE = "hw4output" + xx + ".txt";
                     // Construct output file name using command-line arguments
      listOfStudents = new NodeList<Student>();     // Initialize Students
      listOfGradeItems = new NodeList<GradeItem>(); // Initialize GradeItems
      
      System.out.println("Processing input from file " + INPUT_FILE + "...");
      processInput(INPUT_FILE);
      System.out.println("Processing complete.  Generating report...");
      generateReport(OUTPUT_FILE);
      System.out.println("Done.  Report stored as " + OUTPUT_FILE + ".");
   
   } // End main
   
   // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
   
   /**
      Scans an input file for Student and GradeItem constructor data,
      converting it to a String array andsending it out to processing methods
      to be turned into usable objects.
      
      @param   inFile      String containing the name of the input file
      @throws  IOException If passed a bad filename
      @return  [n/a]       Calls processing methods on input
   */
   public static void processInput(String inFile) throws IOException {
   
      Scanner input = new Scanner(new File(inFile));  // Scanner for input file
      String dataLine = "";                        // Blank line for input data
      
      // Read each line of the input, determine what kind of object it is for,
      // and send it to the appropriate processing method
      while (input.hasNext()) {
         dataLine = input.nextLine();
         String[] dataTokens = dataLine.split(",");
         if (dataTokens[0].equals("STUDENT")) {
            processStudentData(dataTokens);
         } // End if
         else if (dataTokens[0].equals("GRADE ITEM")) {
            processGradeItemData(dataTokens);
         } // End else if
         else {
            System.err.println("Error: " + dataTokens[0] + " is not a valid object type.");
         } // End else
      } // End while
      
      input.close();
         
   } // End processInput
   
   // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
   
   /**
      Converts an input String array into a Student object, then either adds it
      to or removes it from the main List, or reports an error if passed a bad
      command.
      
      @param   info  String array beginning with "STUDENT" from processInput
      @return  [n/a] Adds to or removes from Student list
   */
   
   public static void processStudentData(String[] info) {
      
      try {
         Student newStudent = new Student(info[2], info[3], info[4], info[5]);
                                 // New Student object from input String array
         String modified = "";     // Will say "not" if list modification fails
         
         // Add student to list
         if (info[1].equals("ADD")) {
            if (listOfStudents.contains(newStudent)) {
               System.err.print("Student already present in list.  ");
               modified = " not";
            } // End if(contains)
            else {
               listOfStudents.add(newStudent);
            } // End else
            System.out.println("Student " + info[2] + modified + " added.");
         } // End if(ADD)
         
         // Remove student from list
         else if (info[1].equals("DEL")) {
            if (listOfStudents.contains(newStudent)) {
               listOfStudents.remove(newStudent);
            } // End if(contains)
            else {
               System.err.print("No such student in list.  ");
               modified = " not";
            } // End else
            System.out.println("Student " + info[2] + modified + " removed.");
         } // End else if(DEL)
         
         // Bad command argument
         else {
            System.err.println("Error: " + info[1] + " is not a valid command.");
         } // End else
         
      } // End try
      
      // Catch exceptions related to constructor failure, move on to next line
      catch (IllegalArgumentException e) {
         System.err.println("Constructor fault: " + e.getMessage());
      } // End catch (illegal argument)
      catch (NullPointerException e) {
         System.err.println("Insufficient data to modify student.");
      } // End catch (null pointer)
   
   } // End processStudentData

   // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
   
   /**
      Converts an input String array into a GradeItem object, then either adds
      it to or removes it from the main List, or reports an error if passed a
      bad command.
      
      @param   info  String array beginning with "GRADE ITEM" from processInput
      @return  [n/a] Adds to or removes from Grade Item list
   */
   
   public static void processGradeItemData(String[] info) {
      
      try {
         GradeItem grItem = new GradeItem(Integer.parseInt(info[2]), info[3], 
                                             info[4], info[5], info[6], 
                                             Integer.parseInt(info[7]), 
                                             Integer.parseInt(info[8]));
                                 // New GradeItem object from input String array
         String modified = "";   // Will say "not" if list modification fails
         
         // Add item to list
         if (info[1].equals("ADD")) {
            // do ADD
            if (listOfGradeItems.contains(grItem)) {
               System.err.print("Item already present in list.  ");
               modified = " not";
            } // End if(contains)
            else {
               listOfGradeItems.add(grItem);
            } // End else
            System.out.println("Grade item " + info[2] + modified + " added.");
         } // End if(ADD)
         
         // Remove item from list
         else if (info[1].equals("DEL")) {
            // do DEL
            if (listOfGradeItems.contains(grItem)) {
               listOfGradeItems.remove(grItem);
            } // End if(contains)
            else {
               System.err.print("No such item in list.  ");
               modified = " not";
            } // End else
            System.out.println("Grade item " + info[2] + modified + " removed.");
         } // End else if
         
         // Bad command argument
         else {
            System.err.println("Error: " + info[1] + " is not a valid command.");
         } // End else
      } // End try
      
      // Catch exceptions related to constructor failure, move on to next line
      catch (IllegalArgumentException e) {
         System.err.println("Constructor fault: " + e.getMessage());
      } // End catch (illegal argument)
      catch (NullPointerException e) {
         System.err.println("Insufficient data to modify grade item.");
      } // End catch (null pointer)

   } // End processGradeItemData
   
   // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
   
   /**
      Creates a report for output to a specified file by correlating with 
      each Student all Grade Items that have matching Student ID numbers,
      then calculating that student's grade by totaling available points 
      and received points.
      
      @param   outFile     String containing the name of the output file
      @throws  IOException maybe, but probably not
      @return  [n/a]       Sends output directly to file
   */
   
   public static void generateReport(String outFile) throws IOException {
   
      PrintWriter out = new PrintWriter(new FileWriter(outFile)); // Output file
      Object[] studentArray = listOfStudents.toArray();
      Student currentStudent;       // Student object in a given loop iteration
      Object[] gradeArray = listOfGradeItems.toArray();
      GradeItem currentItem;        // Grade Item in a given loop iteration
      int maxTotal;                 // Total of a student's max available points
      int actualTotal;              // Total of a student's earned points
      double overall;               // Percentage grade (Actual / Maximum)
      
      out.println("StudentID FirstName LastName Email");
      out.println("========================================================");
      out.println();
      
      // Iterate through all Students in the list
      for (int s = 0; s < studentArray.length; s++) {
         currentStudent = (Student)studentArray[s];
         maxTotal = 0;
         actualTotal = 0;
         overall = 0.0;
         out.println(currentStudent.getId() + " " + currentStudent.getFirstName()
                                            + " " + currentStudent.getLastName() 
                                            + " " + currentStudent.getEmail());
         out.println("   Grade Items");

            // Iterate through all Grade Items one at a time,
            // adding to the report if it is associated with the current Student
            for (int i = 0; i < gradeArray.length; i++) {
               currentItem = (GradeItem)gradeArray[i];
               if (currentItem.getStudentId().equals(currentStudent.getId())) {
                  out.printf("%-8s", "   " + currentItem.getItemId());
                  out.printf("%-8s", currentItem.getCourseId());
                  out.printf("%-12s", currentItem.getItemType());
                  out.printf("%-12s", currentItem.getItemDate());
                  out.printf("%-8s", currentItem.getMaxScore());
                  out.printf("%-8s%n", currentItem.getActualScore());
                  maxTotal += currentItem.getMaxScore();
                  actualTotal += currentItem.getActualScore();
               } // End if
            } // End for(grade items)
            
            // After finding a student's Grade Items, calculate overall grade
            // and add totals to the report
            if (maxTotal > 0) {
               overall = (actualTotal / (double)maxTotal) * 100.0;
            }
            out.println("===================================================");
            out.printf("%-8s", "   Total");
            out.printf("%32s", "");
            out.printf("%-8d", maxTotal);
            out.printf("%-8d", actualTotal);
            out.printf("%4.1f", overall);
            out.printf("%1s%n", "%");
            out.println();
            
         } // End for(students)
      
      out.close();
      
   } // End generateReport

} // End NateRoberts_03