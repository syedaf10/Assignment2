/**
 * F1: Read the unit name and students’ marks from a given text file. The file contains the unit name and the list of students with their names, student ids and marks for three assignments. 
 * The file also contains lines, which are comments and your program should check to ignore them when reading the students’ marks.
 * F2: Calculate the total mark for each student from the assessment marks and print out the list of students with their name, student id, assessment marks and the total mark.
 * F3: Print the list of students with the total marks less than a certain threshold. The threshold will be entered from keyboard.
 * F4: Print the top 10 students with the highest total marks and top 10 students with the lowest total marks (algorithm 1).
 * F5: Create a simple menu system to allow users to select and execute each function (algorithm 2).
 * 
 * @author (Syeda Farheen Anjum)
 * @version (2.3/07-04-2022)
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
/*
 * The Class Student holds the details of the Students available in the CSV file like the last name, first name, student ID, 
 * assessment 1, assessment 2, and assessment 3. From the marks, the total marks is calculated as the sum of the three assessments. 
 */
class Student
{
    private String lastName; 
    private String firstName; 
    private String studentID; 
    private Double assignment1; 
    private Double assignment2; 
    private Double assignment3;
    private Double totalMarks;
    //Constructor Input is String Value, which is the individual line item from the CSV file. 
    public Student(String line)
    {
     String[] studentInformation = line.split("[,]",0); //Split function to segregate hte line items into individual values, and store in an array.
     this.lastName = studentInformation[0]; 
     this.firstName = studentInformation[1]; 
     this.studentID = studentInformation[2];
     try {  //Empty value, or Wrong Data Type, then appropriate Exception Handling and assignment of Zero value. 
     this.assignment1 = Double.valueOf(studentInformation[3]);
     } catch(ArrayIndexOutOfBoundsException ex1) {
     this.assignment1 = 0.00;
     } catch(NumberFormatException ex2) {
     this.assignment1 = 0.00;
     }
     try {
     this.assignment2 = Double.valueOf(studentInformation[4]);
     } catch(ArrayIndexOutOfBoundsException ex1) {
     this.assignment2 = 0.00;
     } catch(NumberFormatException ex2) {
     this.assignment2 = 0.00;
     }
     try {
     this.assignment3 = Double.valueOf(studentInformation[5]); 
     } catch(ArrayIndexOutOfBoundsException ex1) {
     this.assignment3 = 0.00;
     } catch(NumberFormatException ex2) {
     this.assignment3 = 0.00;
     }
    }
    //Function to calculate the total marks from the three assignment marks. 
    public void calculateStudentMarks()
    {
     this.totalMarks = this.assignment1 + this.assignment2 + this.assignment3;
    }
    //Accessor Methods for the Private Variables inside the Student Class
    public String getLastName()
    {
     return(this.lastName);
    }
    public String getFirstName()
    {
     return(this.firstName);
    }
    public String getStudentID()
    {
     return(this.studentID);
    }
    public Double getAssignment1()
    {
     return(this.assignment1);
    }
    public Double getAssignment2()
    {
     return(this.assignment2);
    }
    public Double getAssignment3()
    {
     return(this.assignment3);
    }
    public Double getTotalMarks()
    {
     return(this.totalMarks);
    }
}
/*
 * The Class StudentMarksA2 is the Assignment 2 class consisting of the key methods for the performing the requiremnets of the 
 * Assignment 2 questions. 
 * All the Student Information in the Class are stored in the ArrayList, which is a Student class type. 
 */
public class StudentMarksA2
{
 //Instance Variable
 private String unitName;
 private ArrayList<Student> unitStudents;
 private int studentCount; //Total number of Student
 private String line; //Line value in the CSV file. 
 private int lineCount; //Line in the CSV file
 //Constructor for the class StudentMarksA2
 public StudentMarksA2()
 {
  unitStudents = new ArrayList<Student>();
 }
 /*
  * The method readFile() helps to read the input file CSV and gather the data from the file one by one. 
  * Line 0 has the details of the unitName. 
  * Line 1 has the column heading of the data. 
  * From Line 2, the required dataset are available, which are processed and stored in the ArrayList with the object type Student. 
  */
 public void readFile()
 { 
  try{
      File myFile = new File("prog5001_students_grade_2022.csv"); 
      Scanner myScanner = new Scanner(myFile); 
      myScanner.useDelimiter(",");
      lineCount = 0;
      studentCount = 0;
      while(myScanner.hasNextLine()){
          line = myScanner.nextLine(); 
          if(lineCount == 0)
          {
           unitName = line.substring(6, line.length());
          }
          else if(lineCount == 1)
          {
            //Pass without aciton
          }
          else 
          {
            Student studentObj = new Student(line);
            studentObj.calculateStudentMarks();
            unitStudents.add(studentObj);
            studentCount++;
          }
          lineCount++;
      }
      myScanner.close();
  }catch(FileNotFoundException e){
    System.out.println("The file cannot be found");
    e.printStackTrace();
  }
 }
 /*
  * The Method Display Student Details uses the print formatting to display the Tabular Report consists of all the information 
  * of the students in the particular Unit. 
  */
 public void displayStudentDetails()
 {
    int i;
    Student studentObj;
    System.out.printf("%3s %30s %20s %10s %6s %6s %6s %15s", "S.No.", "Last Name","First Name", "Student ID", "A1", "A2", "A3", "Total Marks");
    System.out.println();
    for( i = 0; i < studentCount; i++)
    {
     studentObj = unitStudents.get(i);
     System.out.format("%3d %30s %20s %10s %8.2f %8.2f %8.2f %8.2f", (i+1), studentObj.getLastName(), studentObj.getFirstName(),studentObj.getStudentID(),studentObj.getAssignment1(),studentObj.getAssignment2(),studentObj.getAssignment3(),studentObj.getTotalMarks());
     System.out.println();
    }
 }
  /*
  * The Method Display Below Threshold uses the print formatting to display the Tabular Report consists of all the information 
  * of the students in the particular Unit. But, before that the program asks for the Threshold marks below which the Student List 
  * to be displayed. 
  */
 public void dispalyBelowThreshold()
 {
   int i; 
   Scanner input = new Scanner(System.in); 
   Student studentObj;
   System.out.println("Enter the Threshold Marks to display the list of Students below the threshold: ");
   Double thresholdMarks = Double.valueOf(input.nextLine());
   System.out.printf("%3s %30s %20s %10s %6s %6s %6s %15s", "S.No.", "Last Name","First Name", "Student ID", "A1", "A2", "A3", "Total Marks");
   System.out.println();
   for( i = 0; i < studentCount; i++)
    {
     studentObj = unitStudents.get(i);
     if(studentObj.getTotalMarks() < thresholdMarks)
     {
      System.out.format("%3d %30s %20s %10s %8.2f %8.2f %8.2f %8.2f", (i+1), studentObj.getLastName(), studentObj.getFirstName(),studentObj.getStudentID(),studentObj.getAssignment1(),studentObj.getAssignment2(),studentObj.getAssignment3(),studentObj.getTotalMarks());
      System.out.println();
     }
    }
 }
 /*
  * The Algorithm Bubble Sort is used to Sort the Total List of the Students in the Descending Order based on the Total Marks of the 
  * Students. 
  * The Sorted Dataset is then displayed with only top 10 students. 
  * Therefore, the output is the Top 10 Highest Scoring Students in the Class. 
  * For the display of the Top 10 Lowest Scoring Students in the Class. The Bubble Sort is reversed in the logic of comparison 
  * which then sorts the students list in the ascending order. 
  * The Sorted Dataset is then displayed with only top 10 students. 
  * Therefore, the output is the Top 10 Lowest scoring Students in the Class. 
  */
 public void displayTop10HighestStudents()
 {
    int i, j;
    int arrayListLength;
    boolean swap;
    Student studentObj;
    Student currentStudent; 
    Student nextStudent;
    ArrayList<Student> descendingStudents;
    descendingStudents = unitStudents;
    arrayListLength = descendingStudents.size();    
    
    for(i = 0; i < arrayListLength - 1; i++) 
    {
     for(j = 0; j < arrayListLength - 1 - i; j++) 
     {
      currentStudent = descendingStudents.get(j);
      nextStudent = descendingStudents.get(j+1); 
      if(currentStudent.getTotalMarks() < nextStudent.getTotalMarks())
      {
       descendingStudents.set(j, nextStudent); 
       descendingStudents.set(j+1, currentStudent);
      }
     }
    }
    System.out.println("Descending Order - Top 10 Students"); 
    System.out.printf("%3s %30s %20s %10s %6s %6s %6s %15s", "S.No.", "Last Name","First Name", "Student ID", "A1", "A2", "A3", "Total Marks");
    System.out.println();
    for( i = 0; i < 10; i++)
    {
     studentObj = descendingStudents.get(i);
     System.out.format("%3d %30s %20s %10s %8.2f %8.2f %8.2f %8.2f", (i+1), studentObj.getLastName(), studentObj.getFirstName(),studentObj.getStudentID(),studentObj.getAssignment1(),studentObj.getAssignment2(),studentObj.getAssignment3(),studentObj.getTotalMarks());
     System.out.println();
    }
 }
  public void displayBottom10LowestStudents()
 {
    int i, j;
    int arrayListLength;
    boolean swap;
    Student studentObj;
    Student currentStudent; 
    Student nextStudent;
    ArrayList<Student> descendingStudents;
    descendingStudents = unitStudents;
    arrayListLength = descendingStudents.size();    
    
    for(i = 0; i < arrayListLength - 1; i++) 
    {
     for(j = 0; j < arrayListLength - 1 - i; j++) 
     {
      currentStudent = descendingStudents.get(j);
      nextStudent = descendingStudents.get(j+1); 
      if(currentStudent.getTotalMarks() > nextStudent.getTotalMarks())
      {
       descendingStudents.set(j, nextStudent); 
       descendingStudents.set(j+1, currentStudent);
      }
     }
    }
    System.out.println("Ascending Order - Bottom 10 Students"); 
    System.out.printf("%3s %30s %20s %10s %6s %6s %6s %15s", "S.No.", "Last Name","First Name", "Student ID", "A1", "A2", "A3", "Total Marks");
    System.out.println();
    for( i = 0; i < 10; i++)
    {
     studentObj = descendingStudents.get(i);
     System.out.format("%3d %30s %20s %10s %8.2f %8.2f %8.2f %8.2f", (i+1), studentObj.getLastName(), studentObj.getFirstName(),studentObj.getStudentID(),studentObj.getAssignment1(),studentObj.getAssignment2(),studentObj.getAssignment3(),studentObj.getTotalMarks());
     System.out.println();
    }
 }
 /*
  * For the Menu Driven Program, where the User Preferences are input one by one using an indefinite While Loop. 
  * First, the users is informed about the list of the menu options available to select the user preferences i.e. 1-5. 
  * For Option 1, Display all the Student Details. 
  * For Option 2, Display all the Student Details below a Specific Threshold. 
  * For Option 3, Display Top 10 Highest Scoring Students. 
  * For Option 4, Display Bottom 10 Lowest Scoring Students. 
  * For Option 5, Terminate the Program. 
  */
 public static void main(String[] args)
 {
  int inputChoice;
  Scanner input = new Scanner(System.in); 
  StudentMarksA2 myObj = new StudentMarksA2();
  myObj.readFile();
  System.out.println("Welcome to PROG5001 Student Marks Analysis Software!");
  System.out.println("Unit Name: " + myObj.unitName);
  while(true)
  {
   System.out.println("Please select any one option(1-5): "); 
   System.out.println("1. Display all the Student Details.");
   System.out.println("2. Display all the Student Details below a specific threshold.");
   System.out.println("3. Display Top 10 Highest Scoring Students."); 
   System.out.println("4. Display Bottom 10 Lowest Scoring Students."); 
   System.out.println("5. Exit the Software.");
   inputChoice = Integer.valueOf(input.nextLine());
   switch(inputChoice)
   {
    case 1:
        myObj.displayStudentDetails();
        break;
    case 2:
        myObj.dispalyBelowThreshold();
        break;
    case 3:
        myObj.displayTop10HighestStudents();
        break;
    case 4:
        myObj.displayBottom10LowestStudents();
        break;
    case 5:
        System.out.println("Software Closed!"); 
        System.out.println("Thank you! Have a Great Day!");
        return;
    default:
        break;
   }
  }
 }
}
