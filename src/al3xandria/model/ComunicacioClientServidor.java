package al3xandria.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.KeyStore;

import javax.net.SocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.swing.JOptionPane;

import al3xandria.model.objects.Usuari;
import al3xandria.strings.WarningStrings;
import al3xandria.vista.headPanel.HeadPanel;

/**
 * Clase que envia i rep dades del servidor
 * 
 * @author SergioHernandez
 *
 */
public class ComunicacioClientServidor {

	private static String CLAU_CLIENT = "F:" + File.separator + "workSpaces" + File.separator + 
			"eclipse" + File.separator + "Al3xandria" + File.separator + "certs" 
			+ File.separator + "client" + File.separator + "client_ks";
	private static String CLAU_CLIENT_PASSWORD = "456456";
	private String[] dadesDelServidor;
	private final int PORT = 5556;
	private HeadPanel headPanel;
	String data;
	

	/**
	 * El constructor s'encarrega de conectar amb el client envia i rep informacio
	 * 
	 */
	public ComunicacioClientServidor() {
		
	}
	
	/**
	 * Inicia la comunicació amb el client i envia i rep informació
	 * @param dades
	 */
	public void iniciarComunicacio(String dades) {
		//IMPLEMENTA
        //Estableix el magatzem de claus a utilitzar per validar el certificat del servidor
        System.setProperty("javax.net.ssl.trustStore", CLAU_CLIENT);
        System.setProperty("javax.net.debug", "ssl,handshake");
        ComunicacioClientServidor client = new ComunicacioClientServidor();
		mostraCartellClientConnectat();
		if (dades != null) {
			try {

				Socket socket = client.clientAmbCert();

				PrintWriter output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
				System.out.println("Dades enviades al server: " + dades);

				output.println(dades);

				BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				data = input.readLine();
				dadesDelServidor = data.split(",");
				System.out.println("Dades rebudes del server: " + data);
								
				socket.close();

			} catch (Exception e) {
				errorConexioServidor();
				
				// System.exit(1);
			}
		}
	}
	
	
	/**
	 * Missatge que mostra un avís quan la conexió amb el server falla
	 * 
	 * @author SergioHernandez
	 */
	public void errorConexioServidor() {
		JOptionPane.showMessageDialog(headPanel,
				WarningStrings.getString("HeadPanel.errorConexioServer"),
				WarningStrings.getString("HeadPanel.titolErrorConexioServer"), JOptionPane.ERROR_MESSAGE);
	}
	
	//creat per fer el video
	public void mostraCartellClientConnectat() {
		System.out.println("--------------------------------------------");
		System.out.println("----------Client connectat-------------------");
		System.out.println("----------Port: " + PORT + "------------------------");
		System.out.println("--------------------------------------------");
	}
	
	

	/*-------------------------- Getters and Setters Methods --------------------------*/
	public String[] getDadesDelServidor() {
		return dadesDelServidor;
	}
	
	public String getData() {
		return data;
	}
	
	private Socket clientSenseCert() throws Exception {
        SocketFactory sf = SSLSocketFactory.getDefault();
        Socket s = sf.createSocket("localhost", 8443);
        return s;
    }

    Socket clientAmbCert() throws Exception {
        SSLContext context = SSLContext.getInstance("TLS");
        KeyStore ks = KeyStore.getInstance("jceks");

        ks.load(new FileInputStream(CLAU_CLIENT), null);
        KeyManagerFactory kf = KeyManagerFactory.getInstance("SunX509");
        kf.init(ks, CLAU_CLIENT_PASSWORD.toCharArray());
        context.init(kf.getKeyManagers(), null, null);

        SocketFactory factory = context.getSocketFactory();
        Socket s = factory.createSocket("localhost", 5556);
        return s;
    }
	

}
