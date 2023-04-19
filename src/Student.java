import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Student {
    private int studId;
    private String name;
    private String password;
    private int attainedCredits;
    private int totalCredits;
    private String courseID;
    private ArrayList<Course> courseList = new ArrayList<>();
    private int percentile;
    private String passFail;
    Student() {
    }

    //constructor to feed some static data for test purpose
    Student(int studId, String name, String courseID, int attainedCredits, int totalCredits, int percentile, String passFail) {
        this.studId = studId;
        this.name = name;
        this.courseID = courseID;
        this.attainedCredits = attainedCredits;
        this.totalCredits = totalCredits;
        this.percentile = percentile;
        this.passFail = passFail;
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

    public String getStudentString(){
        return this.studId+","+this.name+","+this.attainedCredits+","+this.totalCredits+","+this.percentile+","+this.passFail;
    }

    public void updateDetails(){
        Scanner s = new Scanner(System.in);
        System.out.println("Enter column you want to edit between 0 to 5: ");
        int col = s.nextInt();
        System.out.println("Enter new value : ");
        String newVal = s.next();
        switch (col){
            case 0:
                this.studId = Integer.parseInt(newVal);
                break;
            case 1:
                this.name = newVal;
                break;
            case 2:
                this.attainedCredits = Integer.parseInt(newVal);
                break;
            case 3:
                this.totalCredits = Integer.parseInt(newVal);
                break;
            case 4:
                this.percentile = Integer.parseInt(newVal);
                break;
            case 5:
                this.passFail = newVal;
                break;
        }

        s.close();
    }

}

class Course{
    private String courseName;
    private int totalCredit;
    private int attainedCredit;
    private char grades;
}

