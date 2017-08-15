// Data Access Object
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

//Class of DataBase Connection
public class DataBaseConnection 
{
	// This method open and establish the connection with the DataBase
	public static Connection getConnection() 
	{
		String JDBC_DRIVER = "org.h2.Driver";   
		String DB_URL = "jdbc:h2:~/testdb";
		String USER = "test"; 
		String PASS = "";
		System.out.println("\nConnected to DataBase!");
		try {
				Class.forName(JDBC_DRIVER);
				//Class.forName("org.h2.Driver");
				return DriverManager.getConnection(DB_URL,USER,PASS);
				} 
		catch (Exception e) 
		{
			throw new RuntimeException(e);
			}
	}
}