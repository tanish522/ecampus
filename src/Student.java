import java.io.*;
import java.util.*;

public class Student implements Serializable {
    private int studId;
    private String name;
    private String password;
    private int attainedCredits;
    private int totalCredits;
    private String courseID;
    private double percentile;
    private String grade;
    private ArrayList<Course> courses = new ArrayList<>();

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

    public double getPercentile() {
        return percentile;
    }

    public void setPercentile(int percentile) {
        this.percentile = percentile;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
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
    boolean loginStud(HashMap<Integer, Student> AllStudentDetails, int studId, String password, Scanner sc) {
        if (AllStudentDetails.containsKey(studId))
        {
            Student temp= AllStudentDetails.get(studId);
            System.out.println("\nWelcome " + studId);
            if(temp.password.equals(password))
                return true;
            else
                return false;
        }
        return false;
    }

    public void printStudentDetails(){

        System.out.println("studId" + "\t" + "name" + "\t" + "attainedCredits" + "\t" + "totalCredits" + "\t" + "grade");
        System.out.println(getStudId() + "\t" + getName() + "\t" + getAttainedCredits() + "\t\t" + getTotalCredits() + "\t\t" + getGrade());
    }
    public void changePassword(HashMap<Integer, Student> AllStudentDetails, int studId, Scanner sc){  //This will allow user to change his password.
        /*
         This block of code will hide the password to be displayed on the screen.
        */
        Student temp= AllStudentDetails.get(studId);
        Console c=System.console();
        char[] passw = c.readPassword("\nEnter Password: ");
        for(int i=0;i<passw.length;i++){
            System.out.print("*");
        }
        String p = new String(passw);
        temp.password = p;
        System.out.print("\nPassword changed successfully\n ");
    }

    public String getStudentString(){
        return this.studId+","+this.password+","+this.name+","+this.courseID+","+this.attainedCredits+","+this.totalCredits+","+this.percentile+","+this.grade;
    }

    public static HashMap<Integer,Student> readStudentsFile(){
        try{
            FileInputStream fileInputStream = new FileInputStream("student.dat");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            HashMap<Integer, Student> studList = (HashMap<Integer, Student>) objectInputStream.readObject();
            return studList;
        }catch (FileNotFoundException e){
            e.printStackTrace();
            System.out.println("File not found!");
            return null;
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    //calculate percentile
    public void percentileCalculator(HashMap<Integer,Student> studentList)
    {
        int i=0;
        int[] marks = new int[studentList.size()];
        for(Student s : studentList.values())
        {
            marks[i]= s.attainedCredits;
            i++;
        }
        double[] p = calculatePercentile(marks, 75);
        i=0;
        for(Student s : studentList.values())
        {
            s.percentile = p[i];
            i++;
        }

    }
    public static double[] calculatePercentile(int[] arr, int percentile) {
        Arrays.sort(arr);
        double[] p = new double[arr.length];
        for(int i=0;i<arr.length;i++)
        {
            p[i] = ( i * 100.0) / (arr.length-1);
        }
        return p ;
    }

//    public Course addCourse()
//    {
//
//    }
}
class Course{
    private String courseName;
    private int totalCredit;
    private int attainedCredit;
    private char grades;


}
//class Thread1 extends Thread
//{
//    public void run()
//    {
//
//    }
//}

