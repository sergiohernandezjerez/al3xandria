package al3xandria.model.usuaris;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import al3xandria.model.db.ConnexioDB;
import al3xandria.model.objects.CreateLlibres;
import al3xandria.model.objects.CreateUsuaris;
import al3xandria.model.objects.Llibres;
import al3xandria.model.objects.Usuari;

public class UsuarisModel {

	private DefaultTableModel tableModel;
	private ArrayList<Usuari> llistatDUsuaris;
	private String[] columnes;
	private CreateUsuaris createUsuaris;
	private ConnexioDB connexioDB = new ConnexioDB();
	private String consultaTotsElsUsuaris = "SELECT * FROM usuaris;";

	private String[] titols = {"Id", "Nom", "Cognoms", "Dni/Nie", "Email", "Contrasenya", 
			"Adreça", "CP", "Població", 
			"Provincia", "Pais", "Telèfon", "Carnet", "Tipus", "Puntuació", "Actiu?"};
	
	
	public DefaultTableModel consultarTotsElsUsuaris() {
		Connection connection;
		Statement statement;
		ResultSet resultSet;
		
		tableModel = new DefaultTableModel(null, titols);
		columnes = new String[16];
		
		try {
			connection = connexioDB.getConnexio();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(consultaTotsElsUsuaris);
			
			llistatDUsuaris = new ArrayList<Usuari>();
			//Per fer proves utilitzaba una bd local i omplia l'array amb el resultset
			//llistatDeLlibres = resultSetToArrayList(resultSet);
			
			//Per entregar el TEA3 omplo l'array de llibres amb la classe CreateLlibres
			createUsuaris = new CreateUsuaris();
			llistatDUsuaris = createUsuaris.getLlistatUsuaris();
			
			return arrayToColumnes();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	

	private DefaultTableModel arrayToColumnes() {
		DefaultTableModel tableModel = new DefaultTableModel(null, titols);
		for(Usuari usuari : llistatDUsuaris) {
			 columnes[0] = String.valueOf(usuari.getIdUsuari());
			 columnes[1] = usuari.getNomUsuari();
			 columnes[2] = usuari.getCognomsUsuari();
			 columnes[3] = usuari.getDniNie();
			 columnes[4] = usuari.getEmail();
			 columnes[5] = usuari.getContrasenya();
			 columnes[6] = usuari.getAdreca();
			 columnes[7] = usuari.getCodiPostal();
			 columnes[8] = usuari.getPoblacio();
			 columnes[9] = usuari.getProvincia();
			 columnes[10] = usuari.getPais();
			 columnes[11] = usuari.getTelefon();
			 columnes[12] = usuari.getCarnet();
			 columnes[13] = usuari.getTipusUsuari();
			 columnes[14] = String.valueOf(usuari.getPuntuacioUsuari());
			 columnes[15] = String.valueOf(usuari.getActiu());

			 
			 tableModel.addRow(columnes);
		}

		return tableModel;
		
	}
	
	
	public ArrayList<Usuari> resultSetToArrayList(ResultSet resultSet) {
		Usuari usuari = new Usuari();
		ArrayList<Usuari> usuaris = new ArrayList<Usuari>();
		try {
			while(resultSet.next()) {
				usuari = new Usuari();
				usuari.setIdUsuari(resultSet.getInt("id_usuari"));
				usuari.setNomUsuari(resultSet.getString("nom_usuari"));
				usuari.setCognomsUsuari(resultSet.getString("nom_usuari"));
				usuari.setDniNie(resultSet.getString("dni_nie"));
				usuari.setEmail(resultSet.getString("email"));
				usuari.setContrasenya(resultSet.getString("contraseña"));
				usuari.setAdreca(resultSet.getString("adreca"));
				usuari.setCodiPostal(resultSet.getString("codi_postal"));
				usuari.setPoblacio(resultSet.getString("poblacio"));
				usuari.setProvincia(resultSet.getString("provincia"));
				usuari.setPais(resultSet.getString("pais"));
				usuari.setTelefon(resultSet.getString("telefon"));
				usuari.setCarnet(resultSet.getString("carnet"));
				usuari.setTipusUsuari(resultSet.getString("tipus_usuari"));
				usuari.setPuntuacioUsuari(resultSet.getInt("puntiacio_usuari"));
				usuari.setActiu(resultSet.getBoolean("actiu"));
				
				usuaris.add(usuari);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return usuaris;
		
	}
	
	public void consultarTotsElsUsuarisPerFiltre(String consulta) {
		
	}
	
//	private String getConsultaPerFiltre(String filtre) {
//		String consultaString = "";
//		String[] filtres = filtre.split(",");
//		switch (filtres[0]) {
//		case "autor":
//			consultaString = consultaTotsElsLlibresPerAutor;
//			break;
//
//		default:
//			consultaString = consultaTotsElsLlibres;
//			break;
//		}
//		return null;
//	}
	

	
	
}
