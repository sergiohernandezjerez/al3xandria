package al3xandria.model.prestecs;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import com.google.gson.Gson;

import al3xandria.model.ComunicacioClientServidor;
import al3xandria.model.objects.Prestecs;

public class PrestecsModel {
	private DefaultTableModel tableModel;
	private ArrayList<Prestecs> llistatDePrestecs;
	private String[] columnes;
	
	private ComunicacioClientServidor comunicacioClientServidor;

	private String[] titols = { "Id", "llibre", "usuari", "data inici", "data final", "num. renovacio"};
	
	
	/**
	 * Consulta tots els prestecs que hi ha a la biblioteca
	 * @return
	 */
	public DefaultTableModel consultarTotsElsPrestecs() {
		
		tableModel = new DefaultTableModel(null, titols);
		columnes = new String[6];
		
try {
			
			comunicacioClientServidor = new ComunicacioClientServidor();
			comunicacioClientServidor.iniciarComunicacio("0000000000000,consulta_prestecs");
			String dadesDelServidor = comunicacioClientServidor.getData();
			Gson gson = new Gson();
			llistatDePrestecs = new ArrayList<Prestecs>();
			Prestecs[] llistat = gson.fromJson(dadesDelServidor, Prestecs[].class);
			for(int i = 0; i<llistat.length;i++) {
				System.out.println(llistat[i]);
				llistatDePrestecs.add(llistat[i]);
			}

			return arrayToColumnes();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Consulta tots els prestecs que hi ha a la biblioteca
	 * @return
	 */
	public DefaultTableModel consultarPrestecsUsuari(String idSessio, int idUsuari) {
		
		tableModel = new DefaultTableModel(null, titols);
		columnes = new String[6];
		
try {
			
			comunicacioClientServidor = new ComunicacioClientServidor();
			comunicacioClientServidor.iniciarComunicacio(idSessio + ",consulta_prestecs_usuari," + idUsuari);
			String dadesDelServidor = comunicacioClientServidor.getData();
			Gson gson = new Gson();
			llistatDePrestecs = new ArrayList<Prestecs>();
			Prestecs[] llistat = gson.fromJson(dadesDelServidor, Prestecs[].class);
			for(int i = 0; i<llistat.length;i++) {
				System.out.println(llistat[i]);
				llistatDePrestecs.add(llistat[i]);
			}

			return arrayToColumnes();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Passa un array a columnes per omplir la table
	 * 
	 * @return DefaultTableModel
	 * @author SergioHernandez
	 */
	private DefaultTableModel arrayToColumnes() {
		DefaultTableModel tableModel = new DefaultTableModel(null, titols);
		for (Prestecs prestec : llistatDePrestecs) {
			columnes[0] = String.valueOf(prestec.getId_prestec());
			columnes[1] = prestec.gettitol();
			columnes[2] = prestec.getNom_usuari();
			columnes[3] = prestec.getData_inici();
			columnes[4] = prestec.getData_final();
			columnes[5] = String.valueOf(prestec.getNum_renovacio());
			

			tableModel.addRow(columnes);
		}

		return tableModel;

	}
	
	
	
}
