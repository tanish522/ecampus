import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    boolean loginstud(String id, String password) throws FileNotFoundException {

//        if (studDetails.containsKey(name))
//        {
//            Student temp= studDetails.get(name);
//            System.out.println("\nWelcome " + name);
//            if(temp.password.equals(password))
//                return true;
//            else
//                return false;
//        }
//        else
//        {
//            return false;
//        }
        File myFile = new File("studentsMaster.txt");
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
    public void printScore(String id)
    {
        File myFile = new File("students.txt");
        String[] words = null;
        try{
            Scanner sc = new Scanner(myFile);
            String line;
            while(sc.hasNextLine())
            {
                line=sc.nextLine();
                words= line.split(",");
                if(words[0].equals(id))
                {
                    for(String str : words)
                    {
                        System.out.println(str);
                    }
                }

            }
        }
        catch(IOException e)
        {
            System.out.println("Unable to open file");
            e.printStackTrace();
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

