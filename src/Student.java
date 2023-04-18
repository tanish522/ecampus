import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Student {
    private int studId;
    private String name;
    private String password;
    private ArrayList<Course> courseList = new ArrayList<>();
    private char overallGrade;
    private int percentile;
    Student() {
    }

    //constructor to feed some static data for test purpose
    Student(int studId, String name, String password, ArrayList courseList, char overallGrade, int percentile) {
        this.studId = 101;
        this.name = name;
        this.password = password;
        this.courseList = courseList;
        this.overallGrade =overallGrade;
        this.percentile = percentile;
    }
    /*
    This loginUser function will take user list, studentid and password as arguments and
    will check if the credentials entered are correct or not, if it is correct then it will
    return true else false if not correct.
    */
    boolean loginstud(HashMap<String, Student> studDetails, String name, String password) {

        if (studDetails.containsKey(name))
        {
            Student temp= studDetails.get(name);
            System.out.println("\nWelcome " + name);
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
    public void changePassword(Scanner sc){  //This will allow user to change his password.
        /*
         This block of code will hide the password to be displayed on the screen.
        */
        Console c=System.console();
        char[] passw = c.readPassword("\nEnter Password: ");
        for(int i=0;i<passw.length;i++){
            System.out.print("*");
        }
        String p = new String(passw);
        this.password = p;
        System.out.print("\nPassword changed successfully\n ");
    }

}

class Course{
    private String courseName;
    private int totalCredit;
    private int attainedCredit;
    private char grades;
}

