import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Student implements Serializable {
    private int studId;
    private String name;
    private String password;
    private int attainedCredits;
    private int totalCredits;
    private String courseID;
    private ArrayList<Course> courseList = new ArrayList<>();
    private int percentile;
    private String grade;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAttainedCredits() {
        return attainedCredits;
    }

    public void setAttainedCredits(int attainedCredits) {
        this.attainedCredits = attainedCredits;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(ArrayList<Course> courseList) {
        this.courseList = courseList;
    }

    public int getPercentile() {
        return percentile;
    }

    public void setPercentile(int percentile) {
        this.percentile = percentile;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String Grade) {
        this.grade = grade;
    }

    public int getStudId() {
        return studId;
    }

    public void setStudId(int studId) {
        this.studId = studId;
    }

    Student() {
    }

    //constructor to feed some static data for test purpose
    Student(int studId, String password, String name, String courseID, int attainedCredits, int totalCredits, int percentile, String grade) {
        this.studId = studId;
        this.password = password;
        this.name = name;
        this.courseID = courseID;
        this.attainedCredits = attainedCredits;
        this.totalCredits = totalCredits;
        this.percentile = percentile;
        this.grade = grade;
    }

    /*
    This loginUser function will take user list, studentid and password as arguments and
    will check if the credentials entered are correct or not, if it is correct then it will
    return true else false if not correct.
    */
    boolean loginstud(String id, String password) throws FileNotFoundException {

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

    public void printStudentDetails(){
        System.out.println(this.studId + " " + this.name + " " + this.attainedCredits + " " + this.totalCredits );
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
        return this.studId+","+this.name+","+this.attainedCredits+","+this.totalCredits+","+this.percentile+","+this.grade;
    }

}

class Course{
    private String courseName;
    private int totalCredit;
    private int attainedCredit;
    private char grades;
}

