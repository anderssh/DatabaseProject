package theProject;

//import theProject.MySQLAccess;

public class Main {
	public static void main(String[] args) throws Exception {
		//MySQLAccess dao = new MySQLAccess();
		//dao.readDataBase();
		PropertyHandling jee = new PropertyHandling();
		jee.CreateDatabaseProperies();
		//jee.LoadDatabaseProperies();
	}
}
