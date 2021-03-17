package al3xandria.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class EnviarLoginServer {
	
	private String[] dadesDelServidor;

	public EnviarLoginServer(String dades) {
		
		if(dades!=null) {
			 try{ 
	               
	               String cadena = dades;  
	           
	               Socket socket = new Socket("localhost", 5555);

	               PrintWriter output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
	               output.println(cadena);
	               
	               BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	   				String data = input.readLine();

	   			dadesDelServidor = data.split(",");
	               
	               socket.close();

	       }catch(Exception e){

	               System.err.println(e.getMessage());
	               System.exit(1);
	       }
		}
		  
	}
	
	public String[] getDadesDelServidor() {
		return dadesDelServidor;
	}
	
	
	
	
}
