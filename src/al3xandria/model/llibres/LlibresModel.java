package al3xandria.model.llibres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.junit.platform.commons.util.StringUtils;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.google.gson.Gson;

import al3xandria.model.ComunicacioClientServidor;
import al3xandria.model.db.ConnexioDB;
import al3xandria.model.objects.Autor;
import al3xandria.model.objects.CreateLlibres;
import al3xandria.model.objects.Editorial;
import al3xandria.model.objects.Genere;
import al3xandria.model.objects.Llibres;
import al3xandria.strings.WarningStrings;
import al3xandria.vista.centralPanel.ConsultaLlibresNoRegistrat;

/**
 * Classe creada per simular la bd
 * 
 * @author SergioHernandez
 *
 */
public class LlibresModel {

	private DefaultTableModel tableModel;
	private ArrayList<Llibres> llistatDeLlibres;
	private String[] columnes;
	private CreateLlibres createLlibres;
	private ConnexioDB connexioDB = new ConnexioDB();
	private ComunicacioClientServidor comunicacioClientServidor;

	private String[] titols = { "Id", "isbn", "Títol", "Gènere", "Autor", "Data", "Edició", "Editorial", "Sinopsis",
			"Puntuació", "Núm. Pàgines", "Reservat", "Núm. Reserves" };

	public DefaultTableModel consultarTotsElsLlibres() {

		tableModel = new DefaultTableModel(null, titols);
		columnes = new String[13];

		try {
			

			//llistatDeLlibres = new ArrayList<Llibres>();
			// Per fer proves utilitzaba una bd local i omplia l'array amb el resultset
			// llistatDeLlibres = resultSetToArrayList(resultSet);

			// Per entregar el TEA3 omplo l'array de llibres amb la classe CreateLlibres
//			createLlibres = new CreateLlibres();
//			llistatDeLlibres = createLlibres.getLlistatDeLlibres();
			
			//Per al projecte definitiu consulto els llibres amb el servidor
			comunicacioClientServidor = new ComunicacioClientServidor();
			comunicacioClientServidor.iniciarComunicacio("0000000000000,consulta_llibre_tots");
			String dadesDelServidor = comunicacioClientServidor.getData();
			Gson gson = new Gson();
			llistatDeLlibres = new ArrayList<Llibres>();
			Llibres[] llistat = gson.fromJson(dadesDelServidor, Llibres[].class);
			for(int i = 0; i<llistat.length;i++) {
				System.out.println(llistat[i]);
				llistatDeLlibres.add(llistat[i]);
			}

			return arrayToColumnes();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	/**
	 * Consulta tots els autors que hi ha a la bd
	 * @return ArrayList<String> amb els autors
	 */
	public ArrayList<String> getAutors(){
		comunicacioClientServidor = new ComunicacioClientServidor();
		comunicacioClientServidor.iniciarComunicacio("0000000000000,consulta_autors");
		String dadesDelServidor = comunicacioClientServidor.getData();
		Gson gson = new Gson();
		ArrayList<String> llistatAutors= new ArrayList<String>();
		Autor[] llistat = gson.fromJson(dadesDelServidor, Autor[].class);
		for(int i = 0; i<llistat.length;i++) {
			System.out.println(llistat[i]);
			llistatAutors.add(llistat[i].getNom_autor());
		}
		
		return llistatAutors;
	}
	
	/**
	 * Consulta tots els generes que hi ha a la bd
	 * @return ArrayList<String> amb els generes
	 */
	public ArrayList<String> getGeneres(){
		comunicacioClientServidor = new ComunicacioClientServidor();
		comunicacioClientServidor.iniciarComunicacio("0000000000000,consulta_generes");
		String dadesDelServidor = comunicacioClientServidor.getData();
		Gson gson = new Gson();
		ArrayList<String> llistatGeneres= new ArrayList<String>();
		Genere[] llistat = gson.fromJson(dadesDelServidor, Genere[].class);
		for(int i = 0; i<llistat.length;i++) {
			System.out.println(llistat[i]);
			llistatGeneres.add(llistat[i].getNom_genere());
		}
		
		return llistatGeneres;
	}
	
	/**
	 * Consulta totes les editorials que hi ha a la bd
	 * @return ArrayList<String> amb les editorials
	 */
	public ArrayList<String> getEditorials(){
		comunicacioClientServidor = new ComunicacioClientServidor();
		comunicacioClientServidor.iniciarComunicacio("0000000000000,consulta_editorials");
		String dadesDelServidor = comunicacioClientServidor.getData();
		Gson gson = new Gson();
		ArrayList<String> llistatEditorials= new ArrayList<String>();
		Editorial[] llistat = gson.fromJson(dadesDelServidor, Editorial[].class);
		for(int i = 0; i<llistat.length;i++) {
			System.out.println(llistat[i]);
			llistatEditorials.add(llistat[i].getNom_editorial());
		}
		
		return llistatEditorials;
	}

	/**
	 * Metode que genere una consulta per enviar al servidor segons 
	 * el tipus de filtre escollit
	 * @param filtre tipus de filtre per fer la cerca(titol, autor, editorial, genere)
	 * @param consulta text de la consulta
	 * @return totes les dades per omplir el jtable
	 * @author SergioHernandez
	 */
	public DefaultTableModel consultarTotsElsLlibresPerFiltre(String filtre, String consulta) {
		String tipusConsultaString = "";
		
		if(filtre.equals("titol")) {
			tipusConsultaString = "consulta_llibre_titol";
		}
		if(filtre.equals("autor")) {
			tipusConsultaString = "consulta_llibre_autor";
		}
		if(filtre.equals("editorial")) {
			tipusConsultaString = "consulta_llibre_editorial";
		}
		if(filtre.equals("genere")) {
			tipusConsultaString = "consulta_llibre_genere";
		}
		tableModel = new DefaultTableModel(null, titols);
		columnes = new String[13];
		
		try {
			comunicacioClientServidor = new ComunicacioClientServidor();
			comunicacioClientServidor.iniciarComunicacio("0000000000000,"+tipusConsultaString+","+consulta);
			String dadesDelServidor = comunicacioClientServidor.getData();
			if(dadesDelServidor.equals("null")) {
				mostrarMissatgeNoTrobat(filtre, consulta);
			}else {
				Gson gson = new Gson();
				llistatDeLlibres = new ArrayList<Llibres>();
				Llibres[] llistat = gson.fromJson(dadesDelServidor, Llibres[].class);
				for(int i = 0; i<llistat.length;i++) {
					System.out.println(llistat[i]);
					llistatDeLlibres.add(llistat[i]);
				}

				return arrayToColumnes();
				
			}
			

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return consultarTotsElsLlibres();
	}

	/**
	 * Mostra un missatge d'erro si no troba res a la cerca
	 * @param filtre filtre de la cerca (autor, titol, editorial, genere)
	 * @param consulta text de la consulta
	 */
	private void mostrarMissatgeNoTrobat(String filtre,
			String consulta) {
		filtre = filtre.substring(0, 1).toUpperCase() + filtre.substring(1);
			JOptionPane.showMessageDialog(null,
					"No s'ha trobat " + consulta + " per el filtre: " + filtre,
					WarningStrings.getString("Error de consulta!"),
					JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Passa un array a columnes per omplir la table
	 * 
	 * @return DefaultTableModel
	 * @author SergioHernandez
	 */
	private DefaultTableModel arrayToColumnes() {
		DefaultTableModel tableModel = new DefaultTableModel(null, titols);
		for (Llibres llibre : llistatDeLlibres) {
			columnes[0] = String.valueOf(llibre.getId_llibre());
			columnes[1] = llibre.getIsbn();
			columnes[2] = llibre.getTitol();
			columnes[3] = llibre.getNom_genere();
			columnes[4] = llibre.getNom_autor();
			columnes[5] = String.valueOf(llibre.getData_publicacio());
			columnes[6] = llibre.getEdicio();
			columnes[7] = llibre.getNom_editorial();
			columnes[8] = llibre.getSinopsis();
			columnes[9] = String.valueOf(llibre.getPuntuacio());
			columnes[10] = String.valueOf(llibre.getNum_pagines());
			columnes[11] = String.valueOf(llibre.isReservat());
			columnes[12] = String.valueOf(llibre.getNum_reserves());

			tableModel.addRow(columnes);
		}

		return tableModel;

	}

	/**
	 * Passa un resultset a un ArrayList
	 * 
	 * @param resultSet
	 * @return ArrayList de llibres
	 * @author SergioHernandez
	 */
	public ArrayList<Llibres> resultSetToArrayList(ResultSet resultSet) {
		Llibres llibre = new Llibres();
		ArrayList<Llibres> llibres = new ArrayList<Llibres>();
		try {
			while (resultSet.next()) {
				llibre = new Llibres();
				llibre.setId_llibre(resultSet.getInt("id_llibre"));
				llibre.setIsbn(String.valueOf(resultSet.getInt("isbn")));
				llibre.setTitol(resultSet.getString("titol"));
				llibre.setData_publicacio(resultSet.getDate("data_publicacio").toString());
				llibre.setEdicio(resultSet.getString("edicio"));
				llibre.setPuntuacio(resultSet.getInt("puntuacio"));
				llibre.setNum_reserves(resultSet.getInt("num_reserves"));
				llibre.setSinopsis(resultSet.getString("sinopsis"));
				llibre.setNum_pagines(resultSet.getInt("num_pagines"));
				llibre.setNom_autor(resultSet.getString("nom_autor"));
				llibre.setNom_editorial(resultSet.getString("nom_editorial"));
				llibre.setNom_genere(resultSet.getString("nom_genere"));
				llibre.setReservat(resultSet.getBoolean("reservat"));
				llibres.add(llibre);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return llibres;

	}

	

	public ArrayList<Llibres> getLlistatDeLlibres() {
		return llistatDeLlibres;
	}

	private String consultaTotsElsAutors = "SELECT nom_autor FROM public.autors;";
	private String consultaTotsElsGeneres = "SELECT nom_genere FROM public.generes;";
	private String consultaTotsElsEditorials = "SELECT nom_editorial FROM public.editorials;";


	

}
