
// Import required java libraries

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

// Extend HttpServlet class
@WebServlet("/GradeTransaction")
public class GradeTransaction extends HttpServlet {
	static Connection conn = null;

	private String message = "";

	public void init() throws ServletException {
		// Do required initialization

		openConnection();

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Set response content type
		response.setContentType("text/html");
		message = "";
		// Actual logic goes here.
		// PrintWriter out = response.getWriter();
		String history = request.getParameter("history");
		ArrayList<String> assignmentList = new ArrayList<String>();
		ArrayList<String> gradeList = new ArrayList<String>();
		try {
			String sql = "select assignment,grade from Gradebook";
			ResultSet result;
			result = getFromDB(sql);
			String assignment = "";
			String grade = "";
			
			while (result.next()) {
				assignment = result.getString("assignment");
				grade = result.getString("grade");
				
				assignmentList.add(assignment);
				gradeList.add(grade);
				
			}
			message += "<div class=\"container\"><h2>Gradebook</h2><p>Assignment History</p> "
					+ "<table class= \"table\"><thead><tr><th>Assignment</th><th>Grade</th></tr></thead><tbody>";
			
			for (int i = 0; i < assignmentList.size();i++){
				assignment = assignmentList.get(i);
				grade = gradeList.get(i);
				
			message += "<tr><td>" + assignment + "</td><td>" + grade + "</td></tr>";
			}
			message += "</tbody></table></div>";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("message", message);
		getServletContext().getRequestDispatcher("/gradetransaction.jsp").forward(
				request, response);

	}
	
	public static void openConnection() {
		String url = "jdbc:oracle:thin:testuser/password@localhost";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Properties props = new Properties();
		props.setProperty("user", "testdb");
		props.setProperty("password", "password");
		try {
			conn = DriverManager.getConnection(url, props);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ResultSet getFromDB(String sql) throws SQLException {

		PreparedStatement preStatement = conn.prepareStatement(sql);
		ResultSet result = preStatement.executeQuery();
		return result;
	}
}