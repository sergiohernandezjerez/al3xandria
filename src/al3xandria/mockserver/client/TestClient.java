/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package al3xandria.mockserver.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Class containing a client program to test the mock server that works
 * with sockets
 * @author professor
 */
public class TestClient {
    /**
     * Client program to test the "mock" server working with sockets
     * @param args arguments received by the program: hostname and port number
     */
    public static void main (String args[]){
        
        if(args.length!=2) {System.out.println("Bad parameters"); System.exit(1);}
        
        try{
                InputStreamReader isr = new InputStreamReader(System.in);
                BufferedReader br = new BufferedReader (isr);
                String cadena = br.readLine();            
            
                //Socket socket = new Socket(args[0], Integer.parseInt(args[1]));
                Socket socket = new Socket("localhost", 5555);

                PrintWriter output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
                output.println(cadena);
                
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
           
                String data = input.readLine();

                System.out.println(data);
                
                socket.close();

        }catch(Exception e){

                System.err.println(e.getMessage());
                System.exit(1);
        }
     }
}
    