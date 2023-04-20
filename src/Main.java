import java.io.Console;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc=new Scanner(System.in);
        int ch;
        HashMap<String,Teacher> teacherList = new HashMap<String,Teacher>();
        HashMap<Integer,Student> studentList = new HashMap<>();
        StudentData studList = new StudentData();
        studentList = studList.AllStudentDetails;

        NewSession:
        //this do while loop will show the menu to the user to choose from student login, teacher login and exit
        do
        {
            System.out.print("\n1. Student Login/register  \n2. Teacher Login/Register \n3. Exit");
            System.out.print("\nEnter your choice: ");
            ch = sc.nextInt();
            //As soon as the user chooses, one of these options will be selected and executed accordingly
            switch (ch)
            {
                case 1:
                {
                    // student login
                    int ch2;
                        do{
                            System.out.println("================== Student Login ==================");
                            System.out.println("\n1. Login \n2. Forgot Password\n3. Exit");

                            ch2 = sc.nextInt();
                            switch (ch2){
                                case 1:{

                                    System.out.print("\nEnter StudentId: ");
                                    int studId = sc.nextInt();
                                    String password = hiddenPassword();
                                    Student currstud = new Student();
                                    if(currstud.loginStud(studentList,studId,password,sc))
                                    {
                                        System.out.println("Login Successful");
                                        currstud.printScore(studId);
                                    }
                                    else
                                    {
                                        System.out.println("Login Failed");
                                    }
                                    break;
                                }
                                case 2:{
                                    //Call Forgot Password
                                    break;
                                }
                                case 3:{
                                    System.out.println("Exiting");
                                    break;
                                }
                                default:{
                                    System.out.println("Invalid Choice! ");
                                    break;
                                }

                            }
                        }while(ch2!=3);

                    break;
                }
                case 2: {
                    // teacher login
                    int ch2;
                    do{
                        System.out.println("================== Teacher Login ==================");
                        System.out.println("\n1. Login \n2. Register \n3. Forgot Password\n4. Exit");

                        ch2 = sc.nextInt();
                        switch (ch2){
                            case 1:{
                                System.out.print("\nEnter Username: ");
                                String username = sc.next();
                                System.out.println("\nEnter Password: ");
                                String password = hiddenPassword();
                                Teacher currteacher = new Teacher();
                                if(currteacher.loginTeacher(teacherList,username,password))
                                {
                                    System.out.println("Login Successful");
                                    int ch3;
                                    do{
                                        System.out.println("\n1. Add Student \n2. Edit Marks \n3. View Students \n4. Delete Student \n5. Logout" );
                                        System.out.println("\nEnter Your Choice: ");
                                        ch3 = sc.nextInt();
                                        switch (ch3){
                                            case 1:{
                                                currteacher.addStudent(sc);
                                                break;
                                            }
                                            case 2:{
                                                currteacher.editMarks(sc);
                                                break;
                                            }
                                            case 3:{
                                                currteacher.printStudent(studentList);
                                                break;
                                            }
                                            case 4:{
                                                currteacher.deleteStudent(sc);
                                                break;
                                            }
                                            case 5:{
                                                System.out.println("Logout Successful");
                                                break;
                                            }
                                            default:{

                                                System.out.println("\nEnter Valid Choice! ");
                                            }
                                        }
                                    }while(ch3!=5);

                                }
                                else
                                {
                                    System.out.println("Login Failed");
                                }

                                break;
                            }

                            case 2:{
                                Teacher newTeacher = new Teacher();
                                newTeacher.registerTeacher(teacherList,sc);  // register teachers
                                break;
                            }
                            case 3:{

                                //Call Forgot Password Method

                                break;
                            }
                            case 4:{
                                System.out.println("Exiting");
                                break;
                            }
                            default:{
                                System.out.println("Invalid Choice! ");
                                break;
                            }

                        }
                    }while(ch2!=3);
                    break;
                }
                case 3:{
                    //Forgot Password
                }
                case 4:{
                    System.out.println("Exit");
                    break;
                }
                default:
                {
                    System.out.println("Enter Valid Choice! ");
                    break;
                }
            }
        }while(ch!=4);
    }
    public  static String hiddenPassword(){
         /*
         This block of code will hide the password that user is entering and won't show it on
         the screen, instead it will show '*' sign.
         */
        Console c=System.console();
        char[] passw = c.readPassword("Enter Password: ");
        for(int i=0;i<passw.length;i++){
            System.out.print("*");
        }
        String p = new String(passw);
        return p;
    }
}