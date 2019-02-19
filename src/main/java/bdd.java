import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class bdd {

    private String JDBC_DRIVER;
    private String DB_URL;
    //Database credentials
    private String USER;
    private String PASS;
    private Connection conn;
    private static Connection connection;

    public bdd() {

        JDBC_DRIVER = "com.mysql.jdbc.Driver";
        DB_URL = "jdbc:mysql://localhost/mspr?useUnicode=yes&characterEncoding=UTF-8";
        USER = "root";
        PASS = "rootroot";
        conn = null;
        openConnection();
    }
        public void openConnection(){
            try {
                //Register JDBC driver
                Class.forName(JDBC_DRIVER);
                //Open a connection
                System.out.print("Connecting to a selected database... ");
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                System.out.println("Success!");
            } catch (Exception e) {
                //Handle errors for JDBC
                e.printStackTrace();
            }
        }
        public void closeConnection() {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            System.out.println("Connection closed");
        }
        public Connection getConnection() {
            return conn;
        }

}



