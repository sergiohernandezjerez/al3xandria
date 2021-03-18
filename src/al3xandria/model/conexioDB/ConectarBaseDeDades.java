package al3xandria.model.conexioDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConectarBaseDeDades {


	static ConexioBaseDeDades conexioBaseDeDades = new ConexioBaseDeDades();
	static Connection conexio = conexioBaseDeDades.getConnection();
	
	public static void main(String[] args) {

		String query = "select * from usuaris";
		
		String consultaUsuari = "select nom, cognoms, dni, tipus_usuari, carnet, email, contrasenya "
				+ "from usuaris "
				+ "where email = ? and contrasenya = ?";
		ResultSet single = null;
		ResultSet multi = null;
		
		try {
					

			Statement miStatement = conexio.createStatement();			
			PreparedStatement sentencia = conexio.prepareStatement(consultaUsuari);
			sentencia.setString(1, "pepe@pepe.com");
			sentencia.setString(2, "pepe");
			
			multi = sentencia.executeQuery();
			single = miStatement.executeQuery(query);

			
			while(single.next()) {
				System.out.println(
						single.getString("nom") + " " + 
								single.getString("cognoms") + " " +
								single.getString("dni") + " " + 
								single.getString("tipus_usuari") + " " +
								single.getString("carnet") + " " +
								single.getString("email") + " " + 
								single.getString("contrasenya"));
			}
			
			System.out.println();
			while(multi.next()) {
				System.out.println(
						multi.getString("nom") + " " + 
								multi.getString("cognoms") + " " +
								multi.getString("dni") + " " + 
								multi.getString("tipus_usuari") + " " +
								multi.getString("carnet") + " " +
								multi.getString("email") + " " + 
								multi.getString("contrasenya"));
			}
			
			conexio.close();
			miStatement.close();
			single.close();
			multi.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
}
