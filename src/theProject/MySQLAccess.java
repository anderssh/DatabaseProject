package theProject;

import java.sql.*;
import java.util.Date;
import java.util.Properties;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import theProject.PropertyHandling;

@SuppressWarnings("unused")
public class MySQLAccess {
        private Connection connect = null;
        private Statement statement = null;
        private PreparedStatement insert_idrett = null;
        private PreparedStatement list_idrett = null;
        private ResultSet resultSet = null;
        private Properties props = null;
		
        public void readDataBase() throws Exception {
                try {
                		//Load properties
                		PropertyHandling propHandling = new PropertyHandling();
                		props = propHandling.LoadDatabaseProperies();
                	
                        // This will load the MySQL driver, each DB has its own driver
                        Class.forName(props.getProperty("dbdriver"));
                        
                        // Setup the connection with the DB
                        MysqlDataSource dataSource = new MysqlDataSource();
                        
                        dataSource.setUser(props.getProperty("dbuser"));
                        dataSource.setPassword(props.getProperty("dbpassword"));
                        dataSource.setURL(props.getProperty("dbURL"));
                        
                        connect = dataSource.getConnection();
                        
                        // Statements allow to issue SQL queries to the database
                        statement = connect.createStatement();
                        // Result set get the result of the SQL query
                        resultSet = statement.executeQuery("select * from idrett");
                        writeResultSet(resultSet);

                        // PreparedStatements can use variables and are more efficient 
                        insert_idrett = connect.prepareStatement("INSERT INTO  prosjekt1.idrett (navn) VALUES (?)");
                        // IDen auto-oppdaterer seg.
                        
                        System.out.println("Nå er det lagt inn noe mer her, så nå blir det mer neste gang");
                        // Parameters start with 1
                        insert_idrett.setString(1, "Tennis");
                        insert_idrett.executeUpdate();
                        insert_idrett.setString(1, "Tennis");
                        
                        list_idrett = connect.prepareStatement("SELECT * FROM idrett");
                        resultSet = list_idrett.executeQuery();
                       
                        writeResultSet(resultSet);
                        writeMetaData(resultSet);

                } catch (Exception e) {
                        throw e;
                } finally {
                        close();
                }

        }

        private void writeMetaData(ResultSet resultSet) throws SQLException {
                //         Now get some metadata from the database
                // Result set get the result of the SQL query

                System.out.println("The columns in the table are: ");

                System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
                for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
                        System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
                }
        }

        private void writeResultSet(ResultSet resultSet) throws SQLException {
                // ResultSet is initially before the first data set
        		int allId = 0;
                while (resultSet.next()) {
                        // It is possible to get the columns via name
                        // also possible to get the columns via the column number
                        // which starts at 1
                        // e.g. resultSet.getString(2);
                        int idrett_id = resultSet.getInt("idrett_id");
                        String navn = resultSet.getString("navn");
                        System.out.println("ID: " + idrett_id + "      Navn:" + navn);
                }
        }

        // You need to close the resultSet
        private void close() {
                try {
                        if (resultSet != null) {
                                resultSet.close();
                        }

                        if (statement != null) {
                                statement.close();
                        }

                        if (connect != null) {
                                connect.close();
                        }
                } catch (Exception e) {

                }
        }

}