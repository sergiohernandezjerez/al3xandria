package al3xandria.model.usuaris;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.google.gson.Gson;

import al3xandria.model.ComunicacioClientServidor;
import al3xandria.model.db.ConnexioDB;
import al3xandria.model.objects.CreateLlibres;
import al3xandria.model.objects.CreateUsuaris;
import al3xandria.model.objects.Llibres;
import al3xandria.model.objects.Usuari;
import al3xandria.strings.WarningStrings;

/**
 * Classe per omplir la table d'usuaris i sumular la bd
 * @author SergioHernandez
 *
 */
public class UsuarisModel {

	private DefaultTableModel tableModel;
	private ArrayList<Usuari> llistatDeUsuaris;
	private String[] columnes;
	private CreateUsuaris createUsuaris;
	private ConnexioDB connexioDB = new ConnexioDB();
	private String consultaTotsElsUsuaris = "SELECT * FROM usuaris;";
	

	private String[] titols = {"Id", "Nom", "Cognoms", "Dni/Nie", "Email", "Contrasenya", 
			"Adreça", "CP", "Població", 
			"Provincia", "Pais", "Telèfon", "Carnet", "Tipus", "Puntuació", "Actiu?"};
	private ComunicacioClientServidor comunicacioClientServidor;
	Usuari usuariActual = new Usuari();
	
	public UsuarisModel(Usuari usuariActual) {
		this.usuariActual = usuariActual;
	}
	
	
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
			
			llistatDeUsuaris = new ArrayList<Usuari>();
			//Per fer proves utilitzaba una bd local i omplia l'array amb el resultset
			//llistatDeLlibres = resultSetToArrayList(resultSet);
			
			//Per entregar el TEA3 omplo l'array de llibres amb la classe CreateLlibres
//			createUsuaris = new CreateUsuaris();
//			llistatDeUsuaris = createUsuaris.getLlistatUsuaris();
			
			//per l'aplicació final demanem les dades al servidor
			comunicacioClientServidor = new ComunicacioClientServidor();
			comunicacioClientServidor.iniciarComunicacio(usuariActual.getIdSessio()+",consulta_usuari_tots");
			String dadesDelServidor = comunicacioClientServidor.getData();
			Gson gson = new Gson();
			llistatDeUsuaris = new ArrayList<Usuari>();
			Usuari[] llistat = gson.fromJson(dadesDelServidor, Usuari[].class);
			for(int i = 0; i<llistat.length;i++) {
				System.out.println(llistat[i]);
				llistatDeUsuaris.add(llistat[i]);
			}
			return arrayToColumnes();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	

	/**
	 * Passa un array a columnes per omplir la table
	 * @return DefaultTableModel
	 * @author SergioHernandez
	 */
	private DefaultTableModel arrayToColumnes() {
		DefaultTableModel tableModel = new DefaultTableModel(null, titols);
		for(Usuari usuari : llistatDeUsuaris) {
			 columnes[0] = String.valueOf(usuari.getId_usuari());
			 columnes[1] = usuari.getNom_usuari();
			 columnes[2] = usuari.getCognoms_usuari();
			 columnes[3] = usuari.getDni_nie();
			 columnes[4] = usuari.getEmail();
			 columnes[5] = usuari.getContrasenya();
			 columnes[6] = usuari.getAdreca();
			 columnes[7] = usuari.getCodi_postal();
			 columnes[8] = usuari.getPoblacio();
			 columnes[9] = usuari.getProvincia();
			 columnes[10] = usuari.getPais();
			 columnes[11] = usuari.getTelefon();
			 columnes[12] = usuari.getCarnet();
			 columnes[13] = usuari.getTipus_usuari();
			 columnes[14] = String.valueOf(usuari.getPuntuacio_usuari());
			 columnes[15] = String.valueOf(usuari.isActiu());

			 
			 tableModel.addRow(columnes);
		}

		return tableModel;
		
	}
	
	
	/**
	 * Passa un resultset a un ArrayList
	 * @param resultSet
	 * @return ArrayList de llibres
	 * @author SergioHernandez
	 */
	public ArrayList<Usuari> resultSetToArrayList(ResultSet resultSet) {
		Usuari usuari = new Usuari();
		ArrayList<Usuari> usuaris = new ArrayList<Usuari>();
		try {
			while(resultSet.next()) {
				usuari = new Usuari();
				usuari.setId_usuari(resultSet.getInt("id_usuari"));
				usuari.setNom_usuari(resultSet.getString("nom_usuari"));
				usuari.setCognoms_usuari(resultSet.getString("nom_usuari"));
				usuari.setDni_nie(resultSet.getString("dni_nie"));
				usuari.setEmail(resultSet.getString("email"));
				usuari.setContrasenya(resultSet.getString("contraseña"));
				usuari.setAdreca(resultSet.getString("adreca"));
				usuari.setCodi_postal(resultSet.getString("codi_postal"));
				usuari.setPoblacio(resultSet.getString("poblacio"));
				usuari.setProvincia(resultSet.getString("provincia"));
				usuari.setPais(resultSet.getString("pais"));
				usuari.setTelefon(resultSet.getString("telefon"));
				usuari.setCarnet(resultSet.getString("carnet"));
				usuari.setTipus_usuari(resultSet.getString("tipus_usuari"));
				usuari.setPuntuacio_usuari(resultSet.getInt("puntiacio_usuari"));
				usuari.setActiu(resultSet.getBoolean("actiu"));
				
				usuaris.add(usuari);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return usuaris;
		
	}
	
	public DefaultTableModel consultarTotsElsUsuarisPerFiltre(String[] filtre, String consulta) {
		
		String tipusConsultaString = "";
		
		if(filtre[0].equals("nom") && filtre[1].equals("usuari")) {
			tipusConsultaString = "consulta_usuari_nom";
		}
		if(filtre[0].equals("carnet") && filtre[1].equals("usuari")) {
			tipusConsultaString = "consulta_usuari_carnet";
		}
		if(filtre[0].equals("dni/nie") && filtre[1].equals("usuari")) {
			tipusConsultaString = "consulta_usuari_dninie";
		}
		
		if(filtre[0].equals("nom") && filtre[1].equals("administrador")) {
			tipusConsultaString = "consulta_administrador_nom";
		}
		if(filtre[0].equals("carnet") && filtre[1].equals("administrador")) {
			tipusConsultaString = "consulta_administrador_carnet";
		}
		if(filtre[0].equals("dni/nie") && filtre[1].equals("administrador")) {
			tipusConsultaString = "consulta_administrador_dninie";
		}
		
		if(filtre[0].equals("nom") && filtre[1].equals("tots")) {
			tipusConsultaString = "consulta_usuariTots_nom";
		}
		if(filtre[0].equals("carnet") && filtre[1].equals("tots")) {
			tipusConsultaString = "consulta_usuariTots_carnet";
		}
		if(filtre[0].equals("dni/nie") && filtre[1].equals("tots")) {
			tipusConsultaString = "consulta_usuariTots_dninie";
		}
		
		tableModel = new DefaultTableModel(null, titols);
		columnes = new String[16];
		
		try {
			comunicacioClientServidor = new ComunicacioClientServidor();
			comunicacioClientServidor.iniciarComunicacio(usuariActual.getIdSessio()+","+tipusConsultaString + "," + consulta);
			String dadesDelServidor = comunicacioClientServidor.getData();
			if(dadesDelServidor.equals("null")) {
				mostrarMissatgeNoTrobat(filtre[0], consulta);
			}else {
				Gson gson = new Gson();
				llistatDeUsuaris = new ArrayList<Usuari>();
				Usuari[] llistat = gson.fromJson(dadesDelServidor, Usuari[].class);
				for(int i = 0; i<llistat.length;i++) {
					System.out.println(llistat[i]);
					llistatDeUsuaris.add(llistat[i]);
				}

				return arrayToColumnes();
				
			}
			

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return consultarTotsElsUsuaris();
		
	}

	/**
	 * Mostra un missatge d'erro si no troba res a la cerca
	 * @param filtre filtre de la cerca (nom, carnet i actiu)
	 * @param consulta text de la consulta
	 */
	private void mostrarMissatgeNoTrobat(String filtre, String consulta) {
		filtre = filtre.substring(0, 1).toUpperCase() + filtre.substring(1);
		JOptionPane.showMessageDialog(null,
				"No s'ha trobat " + consulta + " per el filtre: " + filtre,
				WarningStrings.getString("Error de consulta!"),
				JOptionPane.ERROR_MESSAGE);
		
	}
	

	

	
	
}
