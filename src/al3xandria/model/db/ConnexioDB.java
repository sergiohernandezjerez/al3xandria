package al3xandria.model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnexioDB {

//	private static String USER = "tekiopy";
//	private static String PASS = "al3xandria";
//	private static String URL = "jdbc:postgresql://al3xandriadb.czodwvzoccdt.us-east-1.rds.amazonaws.com:5432/al3xandriaDB";
	
	private static String USER = "postgres";
	private static String PASS = "d466986";
	private static String URL = "jdbc:postgresql://localhost:5432/al3xandria";

	
	public Connection getConnexio() {
		Connection connexio = null;
		try {
			//1. Crear la conexion
			connexio = DriverManager.getConnection(URL, USER, PASS);
			//2. crear objecto Statement
			Statement miStatement = connexio.createStatement();
			
			//3. ejecuta sql y crerar Resulset
			String query = "select * from llibres";
			ResultSet resultSet = miStatement.executeQuery(query);

			//4. Recorrer el resulset
			while(resultSet.next()) {
				System.out.println(resultSet.getString("titol"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return connexio;
	}

//	//2. crear objecto Statement
//	Statement miStatement = connexio.createStatement();
//	
//	//3. ejecuta sql y crerar Resulset
//	String query = "select * from llibres";
//	ResultSet resultSet = miStatement.executeQuery(query);
//
//	//4. Recorrer el resulset
//	while(resultSet.next()) {
//		System.out.println(resultSet.getString("titol"));
//	}
		
	
}
