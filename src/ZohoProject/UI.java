package ZohoProject;
import  java.util.Scanner;

public class UI{
    String name;
    public void login(){
        View viewobj = new View();

        Scanner sc = new Scanner(System.in);

        System.out.println("How can I call you ?");
        name = sc.nextLine();

        System.out.println("------------------------------");
        System.out.println("Welcome "+name + "!");
        System.out.println("------------------------------");

        ThreadQueue thread = new ThreadQueue(viewobj);
        thread.ThreadCall();
    }
}
