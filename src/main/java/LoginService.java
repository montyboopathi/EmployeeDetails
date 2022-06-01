
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginService extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter print = response.getWriter();
		request.getRequestDispatcher("link.html").include(request, response);

		String name = request.getParameter("name");
		String password = request.getParameter("password");

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Connection con = null;
		Statement stat = null;
		ResultSet res = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root", "123456789");
			stat = con.createStatement();
			res = stat.executeQuery("select * from employee_table");
			while (res.next()) {
				pw.print("<br>" + res.getString("departmentID") + ", " + res.getString("employeePassword") + ","
						+ res.getString("employeeID") + "," + res.getString("employeeName"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
