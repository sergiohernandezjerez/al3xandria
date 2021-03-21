package al3xandria.mockserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import al3xandria.model.usuaris.GestioUsuaris;
import al3xandria.model.usuaris.Usuari;

/**
 * Class that implements MockServer with sockets
 * 
 * @author professor
 */
public class MockSocketsServer {

	private Map<String, String> idsSessio = new HashMap<String, String>();

	String textSent;
	GestioUsuaris gestioUsuaris;

	/**
	 * Launches server
	 * 
	 * @param port to be listened
	 */
	public void run(int port) {

		try {
			ServerSocket sk = new ServerSocket(port);

			while (true) {
				Socket client = sk.accept();
				BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));

				String data = input.readLine();
				tratamentDadesRebudes(data);

				System.out.println("Rebut del client: " + data);

				PrintWriter output = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);

				output.println(textSent);

			}
		} catch (IOException e) {
			showIOErrorInformation(e);
		}
	}
	
	

	/**
	 * 
	 * @param data
	 */
	public void tratamentDadesRebudes(String data) {
		String[] separateData = data.split(",");
		if (separateData[0].equals("login")) {
			if (!usuariJaHaFetLogin(separateData[1])) {
				setTextSent(consultaLogin(separateData[1], separateData[2]));
			} else {
				setTextSent("550");
			}

		} else {
			setTextSent(consultaLogout(separateData[1]));
		}
	}

	private boolean usuariJaHaFetLogin(String email) {
		boolean usuariJaHaFetLogin = false;
		if (idsSessio.size() > 0) {
			for (Object value : idsSessio.values()) {
				if (value.equals(email)) {
					usuariJaHaFetLogin = true;
				}
			}
		}

		return usuariJaHaFetLogin;
	}

	public String consultaLogout(String idSessio) {
		String paraLogout = "440";
		if (idsSessio.containsKey(idSessio)) {
			idsSessio.remove(idSessio);
			paraLogout = "0";
		}
		return paraLogout;
	}

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

	private String generaIdSessio(String contrasenya) {
		String idSessio = contrasenya + numeroAleatori();
		return idSessio;
	}

	private int numeroAleatori() {
		Random rd = new Random();
		int numeroAleatori = rd.nextInt(100) + 1;
		return numeroAleatori;
	}

	private void showIOErrorInformation(Exception e) {
		System.err.println("Input/Output error:" + e.getMessage());
	}

	public void setTextSent(String textSent) {
		this.textSent = textSent;
	}

}
