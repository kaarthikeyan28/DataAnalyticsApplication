package ZohoProject;

import  java.util.Collections;
import  java.util.HashMap;
import  java.util.Map;

public class View {
   public Map<String,File> filemap;


   View(){
      this.filemap = new HashMap<String,File>();
   }

   public void addFile(String filename , File fileobj){
        this.filemap.put(filename,fileobj);
   }
   public File getFile(String filename){
       return this.filemap.get(filename);
   }

   public void displayStatus(){
       int uploaded=0;
       int pending=0;
       int tablecount=1;


       System.out.println("----------------------------------------");
       System.out.println("Sno      Files               Status");
       System.out.println("----------------------------------------\n");

       for(Map.Entry<String,File> entry : this.filemap.entrySet()){
           if(entry.getValue().isUploaded()) {
               System.out.println(tablecount++ +"      "+entry.getKey() +"         "+"Uploaded");
               uploaded++;
           }
           if(!(entry.getValue().isUploaded())){
               System.out.println(tablecount++ +"      "+entry.getKey() +"         "+"Pending for upload");
               pending++;
           }
       }
       if(uploaded==0 && pending==0) System.out.println("     No Files Attached !");

       System.out.println("\n");

       System.out.print("Uploaded Files :"+uploaded+"       ");
       System.out.println("Pending Files: "+pending);
       System.out.println();
   }


}
