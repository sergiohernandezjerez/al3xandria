/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al3xandria.pedroServer.ConnexioBBDD;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PedroN
 */
public class Cliente {
    
    public static void main (String[] args){
        
        final String HOST = "127.0.0.1";
        final int PUERTO = 5433;
        DataInputStream in;
        DataOutputStream out;
        BufferedReader br;
        BufferedWriter bw;
        
        try {
            Socket sc = new Socket(HOST, PUERTO);
            
            InputStream is = sc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            br = new BufferedReader(isr);

            OutputStream os = sc.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            bw = new BufferedWriter(osw);
            
            bw.write("login,pedro@pedro.com,pedro");
            bw.newLine();
            bw.flush();
            
            String missatge = br.readLine();
            System.out.println("Dades rebudes del servidor:"+ missatge);
            
            /*in = new DataInputStream(sc.getInputStream());
            out= new DataOutputStream(sc.getOutputStream());
            
            out.writeUTF("Hola mundo desde el cliente");
            
            String mensaje = in.readUTF();
            
            System.out.print(mensaje);*/
            
            sc.close();
            
            
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
