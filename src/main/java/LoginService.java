
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/jsonlogin")
public class LoginService extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("username");
		String password = request.getParameter("password");
		String departmentID = request.getParameter("departmentID");

		Connection con = null;
		Statement stat = null;
		ResultSet res = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/employee", "root", "123456789");
			stat = con.createStatement();
			String sql = "select employeeName from employee_table where employeeName ='" + name
					+ "' And employeePassword='" + password + "'";
			res = stat.executeQuery(sql);
			if(res!=null) {
				System.out.println("user logged in successfully");
			
			if (departmentID.equals(11)) {
				// login person is user means this block of code will execute
				res = stat.executeQuery("select * from employee_table");

				JSONObject jObj = new JSONObject();

				while (res.next()) {
					JSONObject employee = new JSONObject();

					employee.put("employeeID", res.getInt("employeeID"));
					employee.put("employeeName", res.getString("employeeName"));
					employee.put("employeePassword", res.getString("employeePassword"));
					employee.put("departmentID", res.getInt("departmentID"));

				}
				res = stat.executeQuery("select * from department");
				JSONObject jObj1 = new JSONObject();
				while (res.next()) {
					JSONObject department = new JSONObject();

					department.put("departmentID", res.getInt("departmentID"));
					department.put("departmentName", res.getString("departmentName"));
				}
			} else {
				PreparedStatement st = con.prepareStatement("SELECT * FROM employee  where employeeName=?");
				st.setString(1, name);
				res = st.executeQuery();
				while (res.next()) {
					JSONObject employee = new JSONObject();

					employee.put("employeeID", res.getInt("employeeID"));
					employee.put("employeeName", res.getString("employeeName"));
					employee.put("employeePassword", res.getString("employeePassword"));
					employee.put("departmentID", res.getInt("departmentID"));

				}
				res = stat.executeQuery("select * from department");
				JSONObject jObj1 = new JSONObject();
				while (res.next()) {
					JSONObject department = new JSONObject();

					department.put("departmentID", res.getInt("departmentID"));
					department.put("departmentName", res.getString("departmentName"));
				}
				con.close();
			}
		}
			else {
				System.out.println("user not found");
			}
	}

		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
