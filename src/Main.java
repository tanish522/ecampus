import java.io.Console;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        int ch;

        NewSession:
        //this do while loop will show the menu to the user to choose from student login, teacher login and exit
        do
        {
            System.out.print("\n1. Student \n2. Teacher \n3. Exit");
            System.out.print("\nEnter your choice: ");
            ch = sc.nextInt();
            //As soon as the user chooses, one of these options will be selected and executed accordingly
            switch (ch)
            {
                case 1:
                {
                    // student login

                    break;
                }
                case 2: {
                    // teacher login
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
}s