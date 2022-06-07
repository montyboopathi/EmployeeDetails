import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {
	
	public static Connection con=null;
    public static String dbUrl="jdbc:mysql://localhost:3306/employee";
    public static String userId="root";
    public static String pwd="123456789";
    
    public static Connection connectDB() throws SQLException{
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");

        }
        catch(Exception e) {
            e.printStackTrace();
            }
        con=DriverManager.getConnection(dbUrl,userId,pwd);
        return con;
    }
}
