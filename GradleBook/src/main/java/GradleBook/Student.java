package GradleBook;

import java.util.Objects;
/**
   Project 1: Student class for Grade Book application
   @author Nate Roberts
   @version 1.02
   
   CS 2050, Section 2
   Developed in jGrasp v2.0.5_01 on PC running 
   MS Windows 10 Pro (v1803 build 17134.228)
   
   sesquipedalian (adj.) - Given to using long words;
                           (of a word) Containing many syllables.
   
   "I have a personal philosophy in life: If somebody else can do 
   something that I'm doing, they should do it.  What I want to do 
   is find things that would represent a unique contribution to the 
   world - the contribution that only I, and my portfolio of talents, 
   can make happen.  Those are my priorities in life."
      -Neil deGrasse Tyson (b. 1958)

   The Student class contains:
      * Four private instance variable declarations
      * A constructor requiring input for each of those variables
      * Accessor methods to read those variables' contents
      * A "toString" method which renders all four variables as a string
      * An "equals" method to determine if two Student objects contain 
         identical data
*/

public class Student {

   // Private Variables
   private String studentId;     // Unique student ID number
   private String firstName;     // Student's first name
   private String lastName;      // Student's last name
   private String emailAddress;  // Student's email address(also unique)
   
   // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
   
   /**
      Constructor method requiring arguments for all four variables
      @param   id       A string containing the student's ID number
      @param   first    A string containing the student's first name
      @param   last     A string containing the student's last name
      @param   email    A string containing the student's email address
      @return  [n/a]    Constructs a Student object
   */
   public Student (String id, String first, String last, String email)
                                 throws IllegalArgumentException {
      
      // Check to ensure none of the arguments is empty
      if (id.isEmpty() || first.isEmpty() || last.isEmpty() 
                                                || email.isEmpty()) {
         throw new IllegalArgumentException("All fields must be filled in.");
      }
      
      // Check to ensure email address is in plausibly valid format
      else if (!email.contains("@")) {
         throw new IllegalArgumentException("Email address must contain @.");
      }
      
      // If no empty/invalid arguments found, set instance values
      studentId = id;
      firstName = first;
      lastName = last;
      emailAddress = email;
      
   } // End Constructor
   
   // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
   
   /**
      Getter method to access student ID number
      @return  String   Student ID 
   */
   
   public String getId() {
      return studentId;
   } // End getId
   
   // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
   
   /**
      Getter method to access student first name
      @return  String   Student first name
   */

   public String getFirstName() {
      return firstName;
   } // end getFirstName
   
   // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
   
   /**
      Getter method to access student last name
      @return  String   Student last name
   */

   public String getLastName() {
      return lastName;
   } // end getLastName
   
   // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
   
   /**
      Getter method to access student email address
      @return  String   Student email address
   */

   public String getEmail() {
      return emailAddress;
   } // end getEmail
   
   // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

   /**
      Render all fields of a student object as a string,
      using Newline characters to set each field to a separate line
      @return  string   List of all the Student object's fields
   */
   
   public String toString() {
      return "Student ID: " + studentId + "\n"
                        + "Last name: " + lastName + "\n"
                        + "First name: " + firstName + "\n"
                        + "Email address: " + emailAddress;
   } // End toString
   
   // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

   /**
      Compare two Student objects to determine whether they contain 
      identical data
      @param   obj    An Object, which is really a Student, but maybe not
      @return  boolean  true (if identical) or false (if distinct)
   */
   
   @Override
   public boolean equals (Object obj) {
      Student other = (Student)obj;
      if (this.studentId.equals(other.getId()) &&
               this.firstName.equals(other.getFirstName()) && 
               this.lastName.equals(other.getLastName()) &&
               this.emailAddress.equals(other.getEmail())) {
         return true;
      }
      return false;
      
   } // End equals

} // End of Student class