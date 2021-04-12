package al3xandria.model.llibres;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import al3xandria.model.db.ConnexioDB;
import al3xandria.model.objects.Llibres;

public class LlibresModel {
	
	private ArrayList<Llibres> llistatDeLlibres;

	private ConnexioDB connexioDB = new ConnexioDB();
	
	public DefaultTableModel consultarTotsElsLlibres() {
		Connection connection;
		Statement statement;
		ResultSet resultSet;
		//id_llibre, isbn, titol, genere, autor, data_publicacio, edicio, editorial
		String[] titols = {"Id", "isbn", "Títol", "Gènere", "Autor", "Data", "Edició", "Editorial", "Sinopsis", 
				"Puntuació", "Núm. Pàgines", "Reservat", "Núm. Reserves"};
		DefaultTableModel tableModel = new DefaultTableModel(null, titols);
		String[] columnes = new String[13];
		String consulta = "SELECT   llibrespereditorial.id_llibre,  llibres.titol, llibres.reservat, autors.nom_autor,  editorials.nom_editorial,   "
				+ "llibrespereditorial.id_editorial,\r\n"
				+ "                                  llibres.isbn,\r\n"
				+ "                                      llibres.data_publicacio,\r\n"
				+ "                                      llibres.edicio,\r\n"
				+ "                                      llibres.puntuacio,\r\n"
				+ "                                     llibres.num_reserves,\r\n"
				+ "                                     llibres.sinopsis,\r\n"
				+ "                                     llibres.num_pagines,\r\n"
				+ "                                      generes.nom_genere\r\n"
				+ "                                     FROM\r\n"
				+ "                                      public.autors,\r\n"
				+ "                                      public.llibres,\r\n"
				+ "                                      public.llibrespereditorial,\r\n"
				+ "                                      public.editorials,\r\n"
				+ "                                      public.llibresperautor,\r\n"
				+ "                                      public.generes,\r\n"
				+ "                                     public.llibrespergenere\r\n"
				+ "                                     WHERE\r\n"
				+ "                                     autors.id_autor = llibresperautor.id_autor AND\r\n"
				+ "                                      llibres.id_llibre = llibrespereditorial.id_llibre AND\r\n"
				+ "                                      llibres.id_llibre = llibrespergenere.id_llibre AND\r\n"
				+ "                                      editorials.id_editorial = llibrespereditorial.id_editorial AND\r\n"
				+ "                                      llibresperautor.id_llibre = llibrespereditorial.id_llibre AND\r\n"
				+ "                                      llibrespergenere.id_genere = generes.id_genere AND\r\n"
				+ "                                      editorials.nom_editorial like '%a%'\r\n"
				+ "                                      order by   llibres.id_llibre ;";
		try {
			connection = connexioDB.getConnexio();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(consulta);;
			llistatDeLlibres = new ArrayList<Llibres>();
			
			llistatDeLlibres = resultSetToArrayList(resultSet);

//			"Id", "isbn", "Títol", "Gènere", "Autor", "Data", "Edició", "Editorial", "Sinopsis", 
//			"Puntuació", "Núm. Pàgines", "Reservat", "Núm. Reserves"
			for(Llibres llibre : llistatDeLlibres) {
				 columnes[0] = String.valueOf(llibre.getIdLlibre());
				 columnes[1] = llibre.getIsbn();
				 columnes[2] = llibre.getTitol();
				 columnes[3] = llibre.getGenere();
				 columnes[4] = llibre.getAutor().toString();
				 columnes[5] = String.valueOf(llibre.getDataPublicacio());
				 columnes[6] = llibre.getEdicio();
				 columnes[7] = llibre.getEditorial();
				 columnes[8] = llibre.getSinopsi();
				 columnes[9] = String.valueOf(llibre.getPuntuacio());
				 columnes[10] = String.valueOf(llibre.getNumeroDePagines());
				 columnes[11] = String.valueOf(llibre.getEstaReservat());
				 columnes[12] = String.valueOf(llibre.getNumeroDeReserves());
				 
				 tableModel.addRow(columnes);
			}

			return tableModel;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}

	public ArrayList<Llibres> resultSetToArrayList(ResultSet resultSet) {
		Llibres llibre = new Llibres();
		ArrayList<Llibres> llibres = new ArrayList<Llibres>();
		try {
			while(resultSet.next()) {
				llibre =new Llibres();
				llibre.setIdLlibre(resultSet.getInt("id_llibre"));
				llibre.setIsbn(String.valueOf(resultSet.getInt("isbn")));
				llibre.setTitol(resultSet.getString("titol"));
				llibre.setDataPublicacio(resultSet.getDate("data_publicacio"));
				llibre.setEdicio(resultSet.getString("edicio"));
				llibre.setPuntuacio(resultSet.getInt("puntuacio"));
				llibre.setNumeroDeReserves(resultSet.getInt("num_reserves"));
				llibre.setSinopsi(resultSet.getString("sinopsis"));
				llibre.setNumeroDePagines(resultSet.getInt("num_pagines"));
				llibre.setAutor(resultSet.getString("nom_autor"));
				llibre.setEditorial(resultSet.getString("nom_editorial"));
				llibre.setGenere(resultSet.getString("nom_genere"));
				llibre.setEstaReservat(resultSet.getBoolean("reservat"));
				llibres.add(llibre);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return llibres;
		
	}
	
	public ArrayList<Llibres> getLlistatDeLlibres() {
		return llistatDeLlibres;
	}
	
	
		
	
}
