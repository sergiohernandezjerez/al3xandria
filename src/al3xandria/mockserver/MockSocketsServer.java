/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al3xandria.mockserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Class that implements MockServer with sockets
 * @author professor
 */
public class MockSocketsServer {
    
	private ArrayList<String> idsSessio = new ArrayList<String>();
	String textSent;
         
    /**
     * Launches server
     * @param port to be listened
     */
    public void run(int port)  {

        
        try {
            ServerSocket sk = new ServerSocket(port);
            
            
            while(true){
                Socket client = sk.accept();
                BufferedReader input = new BufferedReader(
                        new InputStreamReader(client.getInputStream()));

                String data = input.readLine();
                tratamentDadesRebudes(data);
                
                System.out.println(" Received: "+ data);
                
                
                PrintWriter output = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);
                               
                output.println(textSent);
                
            
                
            }
        } catch (IOException e) {
            showIOErrorInformation(e);
        }
    }
    
   
    private void tratamentDadesRebudes(String data) {
		String[] separateData = data.split(",");
		if(separateData[0].equals("login")) {
			textSent = consultaLogin(separateData[1], separateData[2]);
		}else {
			textSent = consultaLogout(separateData[1]);
		}
	}


	private String consultaLogout(String string) {
		String paraLogout = "440";
		if(idsSessio.contains(string)) {
			paraLogout = "0";
		}
		return paraLogout;
	}


	private String consultaLogin(String string, String string2) {
		idsSessio.add("14");
		return "0,14,false";
	}


	private void showIOErrorInformation(Exception e){
        System.err.println("Input/Output error:"+e.getMessage());
    }

     
}
 
