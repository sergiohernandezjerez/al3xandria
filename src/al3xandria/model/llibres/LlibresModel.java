package al3xandria.model.llibres;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

import al3xandria.model.db.ConnexioDB;

public class LlibresModel {

	private ConnexioDB connexioDB = new ConnexioDB();
	
	public DefaultTableModel consultarTotsElsLlibres() {
		Connection connection;
		Statement statement;
		ResultSet resultSet;
		//id_llibre, isbn, titol, genere, autor, data_publicacio, edicio, editorial
		String[] titols = {"Id", "isbn", "Títol", "Gènere", "Autor", "Data", "Edició", "Editorial"};
		DefaultTableModel tableModel = new DefaultTableModel(null, titols);
		String[] columnes = new String[8];
		String consulta = "SELECT   llibrespereditorial.id_llibre,  llibres.titol,  autors.nom_autor,  editorials.nom_editorial,   llibrespereditorial.id_editorial,\r\n"
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
				+ "                                      order by   editorials.nom_editorial ;";
		try {
			connection = connexioDB.getConnexio();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(consulta);;
			while(resultSet.next()) {
				System.out.println(resultSet.getInt("id_llibre"));
				 columnes[0] = String.valueOf(resultSet.getInt("id_llibre"));
				 columnes[1] = String.valueOf(resultSet.getInt("isbn"));
				 columnes[2] = resultSet.getString("titol");
				 columnes[3] = resultSet.getString("nom_genere");
				 columnes[4] = resultSet.getString("nom_autor");
				 columnes[5] = String.valueOf(resultSet.getDate("data_publicacio"));
				 columnes[6] = resultSet.getString("edicio");
				 columnes[7] = resultSet.getString("nom_editorial");
				 
				 tableModel.addRow(columnes);
			}
			return tableModel;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
}
