import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Formatter;

public class Student implements Serializable {
    private int studId;
    private String name;
    private String password;
    private int attainedCredits;
    private int totalCredits;
    private String courseID;
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
    boolean loginstud(HashMap<Integer, Student> AllStudentDetails, int studId, String password, Scanner sc) throws FileNotFoundException {
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
//    public static void main(String[] args) throws FileNotFoundException {
//        Scanner sc = new Scanner(System.in);
//        StudentData std= new StudentData();
//        Student std2 = new Student();
//        std2.loginstud(StudentData.AllStudentDetails,202212032,"202212032",sc);
//
//    }
    public void printScore(String id)
    {
        File myFile = new File("students.txt");
        String[] words = null;
//        try{
//            Scanner sc = new Scanner(myFile);
//            String line;
//            while(sc.hasNextLine())
//            {
//                line=sc.nextLine();
//                words= line.split(",");
//                if(words[0].equals(id))
//                {
//                    for(String str : words)
//                    {
//                        System.out.println(str);
//                    }
//                }
//
//            }
//        }
            Formatter fmt = new Formatter();
            fmt.format("\n%10s %15s %15s %12s %15s %15s %15", "Student Id", "Name", "Attained Credits", "Total Credits", "Percentile", "Result");
            fmt.format("\n-------------------------------------------------------------------------------\n");
            Student std = new Student();
            for (int i = words.length - 1; i >= 0; i--) {
                fmt.format("%10s", words[i]);
                fmt.format("%15s", words[i]);
                fmt.format("%15s", words[i]);
                fmt.format("%15s", words[i]);
                fmt.format("%15s", words[i]);
                fmt.format("%15s", words[i]);
            }
            fmt.format("-------------------------------------------------------------------------------\n");
            System.out.print(fmt);
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
        return this.studId+","+this.password+","+this.name+","+this.courseID+","+this.attainedCredits+","+this.totalCredits+","+this.percentile+","+this.grade;
    }
}
class StudentData{
    static HashMap<Integer, Student> AllStudentDetails = new HashMap<Integer, Student>();
    public StudentData(){
        AllStudentDetails = readStudentsFile();

    }
    public Student getStud(int id)
    {
        return AllStudentDetails.get(id);
    }
    public HashMap<Integer,Student> readStudentsFile(){
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
}

class Course{
    private String courseName;
    private int totalCredit;
    private int attainedCredit;
    private char grades;
}

