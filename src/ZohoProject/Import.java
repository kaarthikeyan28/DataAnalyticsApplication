package ZohoProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.Statement;

class Import implements Runnable{
    Connection conn = JDBC.getConnection();
    public String filename;
    public View viewObj;

    Import(String filename,View viewobj){
        this.filename = filename;
        this.viewObj = viewobj;
    }

    @Override
    public void run(){
        loadFiles();
    }
    synchronized public void loadFiles(){
        try{

            String tablename = filename.split("\\.")[0];

            String path = "C:\\Users\\hp\\Desktop\\ZohoProject" + "\\" + filename;

            Scanner sc = new Scanner(new File(path));
            sc.useDelimiter(",");

            BufferedReader reader = new BufferedReader(new FileReader(path));
            List<String> lines = new ArrayList<>();
            String line = null;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            createTable(tablename,lines);
        }
        catch (Exception e) {
            e.printStackTrace();

        }
    }

     synchronized public void createTable(String tablename,List<String> lines){
        try {
            String[] colNames =  lines.get(0).split(",");

            for(int i=0; i<colNames.length; i++) {
                colNames[i] = colNames[i].replaceAll("[^a-zA-Z0-9]", "_");
            }

            StringBuilder sb = new StringBuilder();
            sb.append("CREATE TABLE ");
            sb.append(tablename);
            sb.append(" (ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,");

            for (int i=0; i< colNames.length; i++){
                sb.append(colNames[i]);
                sb.append(" ");
                if(i== colNames.length-1) sb.append("VARCHAR(1000)");
                else sb.append("VARCHAR(1000),");
            }
            sb.append(");");

            Statement stmt = conn.createStatement();

            stmt.executeUpdate(sb.toString());

            insertValues(tablename,lines);

            return;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    synchronized public void insertValues(String tablename,List<String> Nlines){
            try{
                String sql = "INSERT INTO "+tablename+" VALUES(?,";
                String second[] = Nlines.get(1).split(",");

                String questionmark="";
                for(int i=0;i<second.length;i++){
                    if(i==second.length-1){
                        questionmark = questionmark+"?"+");";
                        break;
                    }
                    questionmark=questionmark+"?"+",";
                }
                sql = sql +questionmark;

                for(int iter=1;iter<Nlines.size();iter++) {
                    PreparedStatement ptst = conn.prepareStatement(sql);

                    String data[] = Nlines.get(iter).split(",");

                    if (data.length != 0) {
                        ptst.setInt(1, iter);
                        for (int j = 0; j < data.length; j++) {
                            ptst.setString(j + 2, data[j]);
                        }
                        ptst.executeUpdate();
                    }

                }
//                System.out.println(tablename+" Insertion success !");
                return;
            }
            catch(Exception e){
                e.printStackTrace();
            }
            finally {
                viewObj.getFile(filename).setUploaded(true);
            }

        }

    }