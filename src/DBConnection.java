import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    
    //Static block to load the driver once when class is loaded
    static {
        try { Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("MySQL Driver loaded successfully!");
        }catch (ClassNotFoundException e){
            System.out.println("MySQL Driver Not found: " + e.getMessage());
        }
    }
    
    private static final String URL = "jdbc:mysql://localhost:3306/student_db";
    private static final String USER = "root";
    private static final String PASS = "";
    
  
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
