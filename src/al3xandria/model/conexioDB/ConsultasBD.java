package al3xandria.model.conexioDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultasBD {

	private ConexioBaseDeDades conexioBaseDeDades = new ConexioBaseDeDades();
	private Connection conexio = conexioBaseDeDades.getConnection();
	PreparedStatement sentencia = null;
	ResultSet resultSet = null;
	
	private String consultaUsuari = "select nom, cognoms, dni, tipus_usuari, carnet, email, contrasenya "
			+ "from usuaris "
			+ "where email = ? and contrasenya = ?";
	
	
	public ResultSet consultarLogin(String email, String contrasenya) {
		
		
		try {
			sentencia = conexio.prepareStatement(consultaUsuari);
			sentencia.setString(1, email);
			sentencia.setString(2, contrasenya);
			
			resultSet = sentencia.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//tancaConexions();
		}
		
		return resultSet;
		
	}
	
	public void tancaConexions() {
		
		try {
			sentencia.close();
			conexio.close();
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
