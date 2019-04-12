package GradleBook;

import java.util.Objects;

/**
   Project 1: Grade Item class for Grade Book application
   @author  Nate Roberts
   @version 1.02
   
   CS 2050, Section 2
   Developed in jGrasp v2.0.5_01 on PC running 
   MS Windows 10 Pro (v1803 build 17134.228)
   
   williwaw (n.) - A violent squall that blows in near-polar 
                  latitudes, as in the Strait of Magellan, Alaska, 
                  and the Aleutian Islands.
   
   "Everywhere is within walking distance if you have the time."
      -Steven Wright (b. 1955)
   
   The grade object class contains:
      * Seven private instance variables unique to each grade item
      * A constructor requiring inputs for each of those variables
      * Accessor methods to read individual variables' contents
      * A "toString" method which renders all variables as a string
      * An "equals" method for determining if two grade items contain 
         identical data
*/

public class GradeItem {

   // Private Variables
   private int itemId;           // Unique ID number for each grade item
   private String studentId;     // ID number of student owner of grade item
   private String courseId;      // ID number of the home course for this item
   private String itemType;      // Type of assignment (from among validTypes)
   private String itemDate;      // Submission date of the grade item
   private int maxScore;         // Maximum possible points for the grde item
   private int actualScore;      // Number of points awarded for the item
   private String[] validTypes = { "HW" , "Class Work" , "Quiz" , "Test" ,
                                  "Final" }; // Valid types of grade item
   private boolean typeOk;       // True if the item is of a valid type
   
   // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
   
   /**
      Constructor method requiring arguments for all seven variables
      
      @param   id       Integer representing the Grade Item's unique number
      @param   student  String containing the ID of the associated student
      @param   course   String containing the Course ID number
      @param   type     String indicating the type of assignment
      @param   date     String containing the item's date of submission
      @param   max      Integer representing the maximum score for the item
      @param   actual   Integer representing the score earned by the student
      @return  [n/a]    Constructs a Grade Item object
      
   */
   public GradeItem (int id, String student, String course, String type, 
                              String date, int max, int actual) {
      
      // Verify that String input fields contain data
      if(student.isEmpty() || course.isEmpty() || type.isEmpty()
                                                || date.isEmpty()) {
         throw new IllegalArgumentException("Inputs cannot be blank.");
      } // End if
      
      // Check type argument against list of valid types
      typeOk = false;
      for (int t = 0; t < validTypes.length && !typeOk; t++) {
         if (type.equals(validTypes[t])) {
            typeOk = true;
         } // End if
      } // End for loop
      
      // Validate arguments with specific requirements
      if (!typeOk) {
         throw new IllegalArgumentException("Invalid item type found.");
      } // End if
      else if (max <= 0) {
         throw new IllegalArgumentException("Max must be greater than zero.");
      } // End else if
      else if (0 > actual || actual > max) {
         throw new IllegalArgumentException("Actual score must be between "
                                          + "zero and maximum score.");
      } // End else if
      else if (date.length() != 8) {
         throw new IllegalArgumentException("Date must be in YYYYMMDD format.");
      } // End else if
      
      // If all arguments meet requirements, set all variables
      itemId = id;
      studentId = student;
      courseId = course;
      itemType = type;
      itemDate = date;
      maxScore = max;
      actualScore = actual;
      
   } // End constructor
   
   // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --   

   /**
      Getter method to access Item ID number
      @return  String   Item ID
   */
   public int getItemId() {
      return itemId;
   } // End getItemId
   
   // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
   
   /**
      Getter method to access student ID number
      @return  String   Student ID
   */
   public String getStudentId() {
      return studentId;
   } // End getStudentId
   
   // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
   
   /**
      Getter method to access Course ID number
      @return  String   Course ID
   */
   public String getCourseId() {
      return courseId;
   } // End getCourseId
   
   // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
   
   /**
      Getter method to access item type
      @return  String   Item type
   */
   public String getItemType() {
      return itemType;
   } // End getItemType
   
   // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
   
   /**
      Getter method to access item date
      @return  String   Item date
   */
   public String getItemDate() {
      return itemDate;
   } // End getItemDate
   
   // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
   
   /**
      Getter method to access max possible score
      @return  int   Max score
   */
   public int getMaxScore() {
      return maxScore;
   } // End getMaxScore
   
   // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
   
   /**
      Getter method to access actual earned score
      @return  int   Actual score
   */
   public int getActualScore() {
      return actualScore;
   } // End getActualScore
   
   // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
   
   /**
      Render all of a Grade Item's fields as a string,
      using Newline characters to set one field per printed line
      @return  String   List of all of the Grade Item object's fields
   */
   public String toString() {
      return "Item: " + itemId + "\n" 
                     + "Student: " + studentId + "\n"
                     + "Course: " + courseId + "\n"
                     + "Item type: " + itemType +  "\n"
                     + "Item date: " + itemDate + "\n"
                     + "Actual score: " + actualScore + "\n"
                     + "Maximum score: " + maxScore;
   } // End toString
   
   // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
   
   /**
      Compare two Grade Items to determine whether they contain 
      identical data
      @param   obj      An object, which is really a GradeItem, but who knows
      @return  boolean  true (if identical) or false (if distinct)
   */
   @Override
   public boolean equals(Object obj) {
      GradeItem other = (GradeItem)obj;
      if (this.itemId == other.getItemId()
               && this.studentId.equals(other.getStudentId())
               && this.courseId.equals(other.getCourseId())
               && this.itemType.equals(other.getItemType())
               && this.itemDate.equals(other.getItemDate())
               && this.maxScore == other.getMaxScore()
               && this.actualScore == other.getActualScore()) {
         return true;
      }
      return false;
      
   } // End equals
   
} // End of GradeItem class