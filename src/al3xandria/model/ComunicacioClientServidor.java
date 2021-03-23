package al3xandria.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

import javax.swing.JOptionPane;

import al3xandria.controlador.login.HeadPanelControlador;
import al3xandria.strings.ExternalizeStrings;
import al3xandria.vista.headPanel.HeadPanel;

/**
 * Clase que envia i rep dades del servidor
 * 
 * @author SergioHernandez
 *
 */
public class ComunicacioClientServidor {

	private String[] dadesDelServidor;
	private final int PORT = 5556;
	private HeadPanel headPanel;

	/**
	 * El constructor s'encarrega de conectar amb el client envia i rep informacio
	 * 
	 * @param dades
	 */
	public ComunicacioClientServidor(String dades) {
		System.out.println("--------------------------------------------");
		System.out.println("----------Client conectat-------------------");
		System.out.println("----------Port: " + PORT + "------------------------");
		System.out.println("--------------------------------------------");
		if (dades != null) {
			try {

				Socket socket = new Socket("localhost", 5556);

				PrintWriter output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
				System.out.println("Dades enviades al server: " + dades);

				output.println(dades);

				BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String data = input.readLine();
				dadesDelServidor = data.split(",");
				System.out.println("Dades rebudes del server: " + data);

				socket.close();

			} catch (Exception e) {
				errorCampEmailBuit();
				
				// System.exit(1);
			}
		}

	}
	
	
	/**
	 * Missatge que mostra un avís quan la conexió amb el server falla
	 * 
	 * @author SergioHernandez
	 */
	public void errorCampEmailBuit() {
		JOptionPane.showMessageDialog(headPanel,
				ExternalizeStrings.getString("HeadPanel.errorConexioServer"),
				ExternalizeStrings.getString("HeadPanel.titolErrorConexioServer"), JOptionPane.ERROR_MESSAGE);
	}
	
	

	/*-------------------------- Getters and Setters Methods --------------------------*/
	public String[] getDadesDelServidor() {
		return dadesDelServidor;
	}

}
