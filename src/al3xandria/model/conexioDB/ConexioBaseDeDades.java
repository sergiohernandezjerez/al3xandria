package al3xandria.model.conexioDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexioBaseDeDades {

	private static String USER = "tekiopy";
	private static String PASS = "d466986";
	private static String URL = "jdbc:mysql://localhost:3306/al3xandria";
	private Connection connection = null;

	public ConexioBaseDeDades() {
		
	}
	
	public Connection getConnection() {

		try {
			connection = DriverManager.getConnection(URL, USER, PASS);

			return connection;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}

		return connection;
	}
	
	
}
