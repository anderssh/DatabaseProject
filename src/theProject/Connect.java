package theProject;

import java.sql.*;

public class Connect {
	public static Connection connection;
	public static Connection ConnectToDB() {
		
		//connecting to database
		String url = "jdbc:mysql://localhost:3306/project2";
		String username = "rall";
		String password = "rallstad";

		System.out.println("Connecting database...");

		try {
			Connection connection = DriverManager.getConnection(url, username, password);
		    System.out.println("Database connected!");
		    return connection;
		} 
		
		catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
		
	}
	
}


