package al3xandria.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Delayed;

public class EnviarLoginServer {
	
	private String[] dadesDelServidor;
	private String[] logoutStrings = {"0"};

	public EnviarLoginServer(String dades) {
		
		if(dades!=null) {
			 try{ 
	               
	               
	           
	               Socket socket = new Socket("localhost", 5555);
	               

	PrintWriter output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
    System.out.println("enviado al server: " + dades);
	output.println(dades);
    
    BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String data = input.readLine();
		System.out.println("recibido del server login: " + data);
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
