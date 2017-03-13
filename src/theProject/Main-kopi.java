package project2;

import java.sql.*;
import java.io.IOException;

public class Main {
	
	
	public static void main(String[] args) throws IOException{
		
	//Connect db = new Connect();
	    
	Connection connection = Connect.ConnectToDB();
	
	char c;
	System.out.print("Type in what you want: ");
	c = (char) System.in.read();
	
	
	if(c=='a'){
	View.viewTable(connection);
	
	}
	/*else if (c == 'b'){
		
	}
	
	else if (c == 'b'){
		
	}
	
		}*/
	}
	
	
}
