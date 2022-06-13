import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SessionValidation {

	public static boolean validate(String employeeName, String employeePassword, String departmentID)

	{
		boolean status = false;
		System.out.println("Driver connected");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/organisation", "root", "123456789");

			PreparedStatement ps = con.prepareStatement(
					"SELECT * FROM organisation.employeedetails  where employeeName=? and employeePassword=? and departmentID=?");
			

			ps.setString(1, employeeName);
			ps.setString(2, employeePassword);
			ps.setString(3, departmentID);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				status = true;
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;

	}

}
