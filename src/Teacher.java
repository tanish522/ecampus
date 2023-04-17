import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Teacher {
    private String courseID;
    private String courseName;
    private String uname;
    private String password;
    private ArrayList<Student> studentList;
    private int passingCriteria;

    public Teacher(String courseId, String courseName) {
        this.courseID = courseId;
        this.courseName = courseName;
        this.studentList = new ArrayList<>();
    }

    /*
    This loginTeacher function will take teacher list, username and password as arguments and
    will check if the credentials entered are correct or not, if it is correct then it will
    return true else false if not correct.
    */
    public boolean loginUser(HashMap<String,Teacher> teacherDetails, String uname, String password) {

        if (teacherDetails.containsKey(uname))
        {
            Teacher temp= teacherDetails.get(uname);
            System.out.println("\nWelcome " + uname);
            if(temp.password.equals(password))
                return true;
            else
                return false;
        }
        else
        {
            return false;
        }
    }

    public void registerTeacher(HashMap<String,Teacher> teacherDetails ,Scanner sc){
        System.out.print("Enter Course ID: ");
        courseID = sc.nextLine();
        System.out.print("Enter Course Name: ");
        courseName = sc.nextLine();
        System.out.print("Enter Passing Criteria (%): ");
        passingCriteria = sc.nextInt();

        // Create Teacher object
        Teacher teacher = new Teacher(courseID, courseName);

        // Input student details from student.txt file
        try {
            File file = new File("students.txt");
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                String studentId = parts[0].trim();
                String studentName = parts[1].trim();
                int studentAge = Integer.parseInt(parts[2].trim());
               // Student student = new Student(studentId, studentName, studentAge);
               // teacher.addStudent(student);
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Failed to read student.txt file. Please make sure the file exists.");
        }
    }

    // Display course details
    public void displayCourseDetails() {
        System.out.println("Course ID: " + courseID);
        System.out.println("Course Name: " + courseName);
        System.out.println("Passing Criteria: " + passingCriteria + "%");
        System.out.println("Student Details: ");
        for (Student student : studentList) {
            System.out.println(student.toString());
        }
    }

}
