package theProject;

import java.sql.*;

public class View {
	//private Connection connection;

	public static void viewTable(Connection connection) {

		Statement stmt = null;
	    String query = "select navn " +
	                   "from " + "project2" + ".idrett";

		try {
			stmt = connection.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        while (rs.next()) {
	            String idrettsnavn = rs.getString("navn");
	            System.out.println(idrettsnavn);
	        }
		} 
		
		catch (SQLException e) {
		    throw new IllegalStateException("Something is fuuucked!", e);
		}    

	}

}
