package ZohoProject;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadQueue{
    public View viewobj;

    ThreadQueue(View viewobj){
        this.viewobj = viewobj;
    }

    public void ThreadCall(){
        Scanner sc = new Scanner(System.in);
        ExecutorService executor = Executors.newFixedThreadPool(10);

        do{
            System.out.println("Enter Option to choose ");
            System.out.println(" 1 - Import Files ");
            System.out.println(" 2 - View Files ");
            System.out.println(" 3 - Exit ");

            int ask = sc.nextInt();

            if(ask==3) break;

            switch (ask){
                case 1:{
                    System.out.println("Choose your file name:");
                    String filelocation = sc.next();

                    File fileobj = new File(filelocation);
                    viewobj.addFile(filelocation,fileobj);
                    //System.out.println(viewobj.getFile(filelocation).isUploaded());

                    Runnable threadref = new Import(filelocation,viewobj);
                    executor.execute(threadref);

                    break;
                }
                case 2:{
                    viewobj.displayStatus();
                    break;
                }
                default:
                    System.err.println("Your option doesn't exist");
            }

        }while(true);

        executor.shutdown();

    }
}
