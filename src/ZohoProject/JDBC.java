package ZohoProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class JDBC{
    final static String DB_URL = "jdbc:mysql://localhost:3306/dataanalytics?autoReconnect=true&useSSL=false";
    final static String User = "root";
    final static String Password = "Kaarthi@2001";
    static Connection conn = null;

    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, User, Password);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
