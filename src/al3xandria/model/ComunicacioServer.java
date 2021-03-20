package al3xandria.model;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ComunicacioServer {
	
	
	//0, true/false, idSessio, ResulSet
	private Map<String, ArrayList<Object>> dadesRebudesDelServidor = new HashMap<String, ArrayList<Object>>();
	
	

	public ComunicacioServer() {
	
	}
	
	public void repDadesPerEnviarLogin(String email, String contrasenya) {
		enviarDadesAlServidor("login" + "," + email + "," + contrasenya);
	}
	
	public void repDadesPerEnviarLogout(String idSessio, String email) {
		enviarDadesAlServidor("logout" + "," + idSessio);
	}
		
	public void enviarDadesAlServidor(String dades) {
		
		try {
		Socket socket = new Socket("localhost", 5555);
		PrintWriter output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        output.println(dades);
			
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        dadesRebudesDelServidor = (Map<String, ArrayList<Object>>)objectInputStream.readObject();
        
        output.close();
        objectInputStream.close();
        socket.close();
			
		}catch (Exception e) {
			System.err.println(e.getMessage());
            System.exit(1);
		}
		
	}
	
	
	
	public Map<String, ArrayList<Object>> getDadesRebudesDelServidor() {
		return dadesRebudesDelServidor;
	}
	
	public void setDadesRebudesDelServidor(Map<String, ArrayList<Object>> dadesRebudesDelServidor) {
		this.dadesRebudesDelServidor = dadesRebudesDelServidor;
	}
	
}
