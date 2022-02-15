package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {

    private final String url = "jdbc:mysql://localhost:3306/final_exam_modules_3?useSSL=false";
    private final String userName = "root";
    private final String passWord = "12345678";

    public Connection getConnection() {
        Connection myConnection = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            myConnection = DriverManager.getConnection(url, userName, passWord);
        } catch (SQLException  e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return myConnection;
    }
}
