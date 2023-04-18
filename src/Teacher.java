import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Teacher {
    private String courseID;
    private String courseName;
    private String uname;
    private String password;
    private int passingCriteria;
    private HashMap<Double,Student> studentList = new HashMap<>();


    /*
    This loginTeacher function will take teacher list, username and password as arguments and
    will check if the credentials entered are correct or not, if it is correct then it will
    return true else false if not correct.
    */
    public boolean loginUser(HashMap<String,Teacher> teacherDetails, String uname, String password) {
    // pending - take teacher details from file of teacher rather than hashmap
        if (teacherDetails.containsKey(uname))
        {
            Teacher temp= teacherDetails.get(uname);
            System.out.println("\nWelcome " + uname);
            return temp.password.equals(password);
        }
        else
        {
            return false;
        }
    }

    /*
    Register Teacher will take object of teachers details(hashmap) and register teacher and add it to the map
    */
    public void registerTeacher(HashMap<String,Teacher> teacherDetails ,Scanner sc) throws IOException {

        System.out.print("Enter Teacher's Username: ");
        this.uname = sc.nextLine();
        System.out.print("Enter Teacher's Password: ");
        this.password = sc.nextLine();

        // username and password validation pending

        System.out.print("Enter Teacher's Course ID: ");
        this.courseID = sc.nextLine();
        System.out.print("Enter Teacher's Course Name: ");
        this.courseName = sc.nextLine();
        System.out.print("Enter Passing Criteria of course(%): ");
        this.passingCriteria = sc.nextInt();

        // Input student details from student.txt file
        try {
            FileReader fileReader = new FileReader("students.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;  // store line from file
            // while loop to read data from file and store it into local variables
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split(",");  //Split the word using space
                    double studID = Integer.parseInt(words[0]);
                    String name = words[1];
                    int attainedCredits = Integer.parseInt(words[2]);
                    int totalCredits = Integer.parseInt(words[3]);
                    int percentile = Integer.parseInt(words[4]);
                    String passFail = words[5];
                    Student student = new Student(studID,name,attainedCredits,totalCredits,percentile,passFail);
                    studentList.put(studID,student);
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Display course details
    public void displayCourseDetails() {
        System.out.println("Course ID: " + courseID);
        System.out.println("Course Name: " + courseName);
        System.out.println("Passing Criteria: " + passingCriteria + "%");
        System.out.println("Student Details: ");
    }

    public static void main(String[] args) {
        Teacher t = new Teacher();
        HashMap<String,Teacher> map = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        try {
            t.registerTeacher(map,sc);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
