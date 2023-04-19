import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Teacher {
    private String courseID;
    private String courseName;
    private String uname;
    private String password;
    private int passingCriteria;
    private HashMap<Integer,Student> studentList = new HashMap<>();


    /*
    This loginTeacher function will take teacher list, username and password as arguments and
    will check if the credentials entered are correct or not, if it is correct then it will
    return true else false if not correct.
    */

    boolean loginTeach(String id, String password) throws FileNotFoundException {

        File myFile = new File("teachersMaster.txt");
        String[] words = null;
        try{
            Scanner sc = new Scanner(myFile);
            String line;
            while(sc.hasNextLine())
            {
                line=sc.nextLine();
                words= line.split(" ");
                if(words[0].equals(id))
                {
                    if(words[1].equals(password))
                    {
                        return true;
                    }
                }

            }
        }
        catch(IOException e)
        {
            System.out.println("Unable to open file");
            e.printStackTrace();
        }
        return false;
    }

    /*
    Register Teacher will take object of teachers details(hashmap) and register teacher and add it to the map
    */
    public void registerTeacher(HashMap<String,Teacher> teacherDetails ,Scanner sc) {

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
                    int studID = Integer.parseInt(words[0]);
                    String name = words[1];
                    int attainedCredits = Integer.parseInt(words[2]);
                    int totalCredits = Integer.parseInt(words[3]);
                    int percentile = Integer.parseInt(words[4]);
                    String passFail = words[5];
                    // creating student object for particular subject
                    Student student = new Student(studID,name,courseID,attainedCredits,totalCredits,percentile,passFail);
                    studentList.put(studID,student);
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // edit marks and grades
    void editMarks(Scanner sc){

        try {
            FileOutputStream fs = new FileOutputStream("students.txt");

            System.out.println("Enter student id you want to edit: ");
            int sID = sc.nextInt();
            Student tempStud = studentList.get(sID);
            String line = tempStud.getStudentString();
            System.out.println(line);
            String[] words = line.split(",");
            tempStud.updateDetails();
            Iterator<Student> cells = studentList.values().iterator();
            while(cells.hasNext())
            {
                tempStud = cells.next();
                line = tempStud.getStudentString();
                fs.write((line+System.getProperty("line.separator")).getBytes());

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
        HashMap<String,Teacher> map = new HashMap<String,Teacher>();
        Scanner sc = new Scanner(System.in);
        t.registerTeacher(map,sc);
        t.editMarks(sc);

    }

}
