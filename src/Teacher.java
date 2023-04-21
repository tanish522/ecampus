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
    HashMap<Integer,Student> studentList = new HashMap<>();

    Teacher(){
        
    }

    Teacher(String uname, String password, String courseID, String courseName, int passingCriteria ){
        this.uname = uname;
        this.password = password;
        this.courseID = courseID;
        this.courseName = courseName;
        this.passingCriteria = passingCriteria;
    }


    boolean loginTeacher(HashMap<String,Teacher> teacherDetails, String uname, String password) {

        if (teacherDetails.containsKey(uname))
        {
            Teacher temp = teacherDetails.get(uname);
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

    public void changePassword(Scanner sc){  //This will allow user to change his password.

        Console c=System.console();
        char[] passw = c.readPassword("\nEnter Password: ");
        for(int i=0;i<passw.length;i++){
            System.out.print("*");
        }
        String p = new String(passw);
        this.password = p;
        System.out.print("\nPassword changed successfully\n ");
    }


    /*
    Register Teacher will take object of teachers details(hashmap) and register teacher and add it to the map
    */
    public void registerTeacher(HashMap<String,Teacher> teacherDetails,Scanner sc) {

        System.out.print("Enter Teacher's Username: ");
        this.uname = sc.next();
        while(teacherDetails.containsKey(uname)){
            System.out.println("Username already exists. Enter another username : ");
            this.uname = sc.next();
        }
        System.out.print("Enter Teacher's Password: ");
        this.password = sc.next();
        System.out.print("Enter Teacher's Course ID: ");
        this.courseID = sc.next();
        System.out.print("Enter Teacher's Course Name: ");
        this.courseName = sc.next();
        System.out.print("Enter Passing Criteria of course(%): ");
        this.passingCriteria = sc.nextInt();
        this.studentList = readStudentsFile();  // Input student details from student.txt file
    }

    // edit marks then edit hashmap of studentlist and write that object to student.dat file
    void editMarks(Scanner sc){  //done

        System.out.println("Enter student id you want to edit: ");
        int sID = sc.nextInt();
        Student tempStud = studentList.get(sID);
        if(tempStud.getCourseID().equals(this.courseID)){
            System.out.println("You cannot edit marks. Student ID does not belong to your course!");
        }
        tempStud.printStudentDetails();
        System.out.println("enter new marks : ");
        int newMarks = sc.nextInt();
        System.out.println("enter new grade : ");
        String newGrade = sc.next();
        tempStud.setAttainedCredits(newMarks);
        tempStud.setGrade(newGrade);
        studentList.replace(sID,tempStud);
        studentList.get(1).printStudentDetails();
        writeToStudentDetails(studentList);
    }

    void addStudent(Scanner sc){  //done
        System.out.println("Enter student id: ");
        int studId = sc.nextInt();
        while (studentList.containsKey(studId)){
            System.out.println("Student ID already exists. Enter new student ID : ");
            studId = sc.nextInt();
        }
        String password = String.valueOf(studId); // store default password for student
        System.out.println("Enter student name: ");
        String name = sc.next();
        System.out.println("Enter student attained credits: ");
        int attainedCredits = sc.nextInt();
        System.out.println("Enter student total credits: ");
        int totalCredits = sc.nextInt();
        System.out.println("Enter student percentile: ");
        int percentile = sc.nextInt();
        System.out.println("Enter student grade: ");
        String grade = sc.next();
        Student s = new Student(studId, password, name, courseID, attainedCredits, totalCredits, percentile, grade);
        studentList.put(studId,s);
        writeToStudentDetails(studentList);
    }

    void deleteStudent(Scanner sc){  //done
        System.out.println("Enter student id you want to delete: ");
        int sID = sc.nextInt();
        Student tempStud = studentList.get(sID);
        if(tempStud.getCourseID().equals(this.courseID)){
            System.out.println("You delete this student. Student ID does not belong to your course!");
        }
        studentList.remove(sID);
        writeToStudentDetails(studentList);
    }

    public void printStudent(HashMap<Integer,Student> studList){

        for(Map.Entry<Integer,Student> ele : studList.entrySet()){
            Student student = ele.getValue();
            if(student.getCourseID().equals(this.courseID))
                student.printStudentDetails();
        }
    }

    public static void writeToTeacherDetails(HashMap<String,Teacher> teacherList){
        try{
            FileOutputStream fileOut = new FileOutputStream("teacher.dat");
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            //write into file
            objOut.writeObject(teacherList);

        }catch(FileNotFoundException e){
            e.printStackTrace();
            System.out.println("File not found!");
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Io exception!");
        }
    }

    public static void writeToStudentDetails(HashMap<Integer,Student> studentList) {
        try {
            FileOutputStream fileOut = new FileOutputStream("student.dat");
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(studentList);

            //close file output streams
            objOut.close();
            fileOut.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
            System.out.println("File not found!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //return object of users from file
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

    // Display course details
    public void displayCourseDetails(HashMap<Integer,Student> studList) {
        System.out.println("Course ID: " + courseID);
        System.out.println("Course Name: " + courseName);
        System.out.println("Passing Criteria: " + passingCriteria + "%");
        System.out.println("Student Details under " + courseName + " : ");

        for(Map.Entry<Integer,Student> ele : studList.entrySet()){
            Student student = ele.getValue();
            System.out.println(student.getName() + " " + student.getAttainedCredits() + " " + student.getTotalCredits() + " " + student.getGrade());
        }

    }

}


class TeachersList {
    HashMap<String,Teacher> teachersList = new HashMap<String,Teacher>();

    public Teacher getTeacher(String name)
    {
        return teachersList.get(name);
    }

    public static HashMap<String,Teacher> readTeachersFile(){
        try{
            FileInputStream fileInputStream = new FileInputStream("teacher.dat");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            HashMap<String, Teacher> teacherList = (HashMap<String, Teacher>) objectInputStream.readObject();
            return teacherList;
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
