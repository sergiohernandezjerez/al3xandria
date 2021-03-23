package al3xandria.mockserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import org.w3c.dom.ls.LSOutput;

import al3xandria.model.usuaris.GestioUsuaris;
import al3xandria.model.usuaris.Usuari;

/**
 * Class that implements MockServer with sockets
 * 
 * @author professor
 */
public class MockSocketsServer {

	//HashMap idSessio, email
	private Map<String, String> idsSessio = new HashMap<String, String>();

	String missatgePerEnviar;
	GestioUsuaris gestioUsuaris;

	/**
	 * Launches server
	 * 
	 * @param port to be listened
	 */
	public void run(int port) {
		System.out.println("--------------------------------------------");
		System.out.println("----------Servidor conectat-----------------");
		System.out.println("----------Port: " + port + "------------------------");
		System.out.println("--------------------------------------------");
		try {
			ServerSocket serverSocket = new ServerSocket(port);

			while (true) {
				Socket client = serverSocket.accept();
				
				BufferedReader input = new BufferedReader(
						new InputStreamReader(client.getInputStream()));

				//rep dades
				String data = input.readLine();
				tractamentDadesRebudes(data);
				System.out.println("Rebut del client: " + data);
				
				
				PrintWriter output = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);
				//envia dades
				output.println(missatgePerEnviar);
			}
		} catch (IOException e) {
			showIOErrorInformation(e);
		}
	}
	
	

	/**
	 * Separa les dades rebudes en un array per tractar-les
	 * Si l'usuari vol fer login abans comprova que no tingui una
	 * sessió oberta. Si la té envia clau d'error 550, si no envia la 
	 * consulta de login. 
	 * Si l'usuari vol fer logout envia la consulta de logout
	 * 
	 * @param data --> dades rebudes pel client
	 * @author SergioHernandez
	 */
	public void tractamentDadesRebudes(String data) {
		String[] separateData = data.split(",");
		if (separateData[0].equals("login")) {
			if (!usuariJaHaFetLogin(separateData[1])) {
				setMissatgePerEnviar(consultaLogin(separateData[1], separateData[2]));
			} else {
				setMissatgePerEnviar("550");
			}

		} else if (separateData[0].equals("logout")) {
			setMissatgePerEnviar(consultaLogout(separateData[1]));
		} else if (separateData[0].equals("logoutOK")) {
			setMissatgePerEnviar("0, Email: " + separateData[1] + ", ha tancat la sessió");
			eliminaIdSessio(separateData[1]);
		}
	}

	
	/**
	 * Comprova si l'usuari te una sessió oberta
	 * @param email  --> email de l'usuari que vol fer la connexió
	 * @return boolean --> true: si té una sessió iniciado | false: si no la té
	 * @author SergioHernandez
	 */
	private boolean usuariJaHaFetLogin(String email) {
		boolean usuariJaHaFetLogin = false;
		if(idsSessio.containsValue(email)) {
			usuariJaHaFetLogin = true;
		}
		return usuariJaHaFetLogin;
	}
	

	/**
	 * Consulta si l'id de sessió de l'usuari existeix com 
	 * una id amb sessió oberta
	 * @param idSessio  --> id de sessió de l'usuari que vol fer logout
	 * @return  String --> el codi que es retornarà al client, 
	 * 440 si error | 0 si logout exitos
	 */
	public String consultaLogout(String idSessio) {
		String retornLogout = "440";
		if (idsSessio.containsKey(idSessio)) {
			retornLogout = "0";
		}
		return retornLogout;
	}
	
	/**
	 * Elimina un objecte del HashMap que emmagatzema les ids de session i el email associat
	 * @param email  --> email de l'usuari que ha fet logout
	 * @author Tom (https://www.iteramos.com/pregunta/25584/como-quitar-una-clave-de-hashmap-mientras-que-iterar-sobre-el)
	 */
	public void eliminaIdSessio(String email) {
		Iterator<Map.Entry<String, String>> iterator = idsSessio.entrySet().iterator();
		while(iterator.hasNext()) {
			Map.Entry<String, String> entry = iterator.next();
			if(email.equalsIgnoreCase(entry.getValue())) {
				iterator.remove();
			}
		}
	}

	
	/**
	 * Consulta a un array d'usuaris si les dades 
	 * coincideixen amb les dades de l'usuari que 
	 * vol fer login
	 * @param email  --> email de l'usuari
	 * @param contrasenya  --> contrasenya de l'usuari
	 * @return String --> les dades de l'usuari o un codi d'error 440
	 */
	public String consultaLogin(String email, String contrasenya) {
		String resultatConsulta = "";
		gestioUsuaris = new GestioUsuaris();
		Usuari usuari = new Usuari();
		usuari = gestioUsuaris.buscarUsuari(email, contrasenya);
		if (usuari != null) {
			usuari.setIdSessio(generaIdSessio(usuari.getContrasenya()));
			idsSessio.put(usuari.getIdSessio(), usuari.getEmail());
			resultatConsulta = "0" + "," + usuari.getIdSessio() + "," + usuari.getTipus();
		} else {
			resultatConsulta = "440";
		}

		return resultatConsulta;
	}

	
	/**
	 * Genera un id de sessió a partir de la contrasenya 
	 * de l'usuari i un nombre aleatori
	 * @param contrasenya  --> contrasenya de l'usuari que ha fet login
	 * @return String --> id de sessió de l'usuari
	 */
	private String generaIdSessio(String contrasenya) {
		String idSessio = contrasenya + numeroAleatori();
		return idSessio;
	}

	
	/**
	 * crea un número aleatori entre 1 i 100 
	 * per formar l'id de sessió
	 * @return int --> numero aleatori
	 */
	private int numeroAleatori() {
		Random rd = new Random();
		int numeroAleatori = rd.nextInt(100) + 1;
		return numeroAleatori;
	}

	
	private void showIOErrorInformation(Exception e) {
		System.err.println("Input/Output error:" + e.getMessage());
	}

	public void setMissatgePerEnviar(String missatgePerEnviar) {
		this.missatgePerEnviar = missatgePerEnviar;
	}
	

}
