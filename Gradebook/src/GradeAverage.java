

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
@WebServlet("/GradeAverage")
public class GradeAverage extends HttpServlet {
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
		String average = request.getParameter("average");

		ArrayList<Integer> gradeList = new ArrayList<Integer>();

		try {
			String sql = "select grade from Gradebook";
			ResultSet result;
			result = getFromDB(sql);
			
			String gradeString = "";
			int grade = 0;

			while (result.next()) {
				gradeString = result.getString("grade");
				grade = Integer.parseInt(gradeString);
				gradeList.add(grade);
			}

			int total = 0;

			for (int i = 0; i < gradeList.size(); i++) {
				total += gradeList.get(i);
			}

			double avg = 0;
			avg = total/gradeList.size();
			
			message += "Average:" + avg;

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("message", message);
		getServletContext().getRequestDispatcher("/gradeaverage.jsp").forward(
				request, response);

	}

	public static void openConnection() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:testuser/password@localhost";
			Properties props = new Properties();
			props.setProperty("user", "testdb");
			props.setProperty("password", "password");
			conn = DriverManager.getConnection(url, props);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

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