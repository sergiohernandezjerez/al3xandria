/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al3xandria.mockserver.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import al3xandria.model.conexioDB.ConsultasBD;


/**
 * Class that implements MockServer with sockets
 * @author professor
 */
public class MockSocketsServer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
                System.out.println(" Received: "+data);
                String[] dadesLogin = data.split(",");
                
                if(dadesLogin[0].equals("login")) {
                	ConsultasBD consultasBD = new ConsultasBD();
        			
            		ResultSet single = consultasBD.consultarLogin(dadesLogin[0], dadesLogin[1]);
            		try {
            			while(single.next()) {
            				System.out.println(
            						single.getString("nom") + " " + 
            								single.getString("cognoms") + " " +
            								single.getString("dni") + " " + 
            								single.getString("tipus_usuari") + " " +
            								single.getString("carnet") + " " +
            								single.getString("email") + " " + 
            								single.getString("contrasenya"));
            			}
            		} catch (SQLException e) {
            			// TODO Auto-generated catch block
            			e.printStackTrace();
            		}
            		ObjectOutputStream objectOutputStream = new ObjectOutputStream(client.getOutputStream());
            		Map<String, ArrayList<Object>> dadesRebudes = new HashMap<String, ArrayList<Object>>();
            		List<Object> objects = new ArrayList<Object>();
            		objects.add(true);
            		objects.add("idSessio01");
            		objects.add("lkj");
            		dadesRebudes.put("0", (ArrayList<Object>) objects);
            		objectOutputStream.writeObject(dadesRebudes);
//            		 PrintWriter output = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);
//                     String textSent="0,14,false";
//                     output.println(textSent);
                     
                }else {
                	ObjectOutputStream objectOutputStream = new ObjectOutputStream(client.getOutputStream());
            		Map<String, ArrayList<Object>> dadesRebudes = new HashMap<String, ArrayList<Object>>();
            		List<Object> objects = new ArrayList<Object>();
            		objects.add(true);
            		objects.add("idSessio01");
            		objects.add(null);
            		dadesRebudes.put("1", (ArrayList<Object>) objects);
            		objectOutputStream.writeObject(dadesRebudes);
                }
                
                
        		
               
                
                
                client.close();
            }
        } catch (IOException e) {
           e.printStackTrace();
        }
    }
    

    
      
}
 
