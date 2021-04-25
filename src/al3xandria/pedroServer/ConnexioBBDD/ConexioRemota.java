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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PedroN
 */
public class ConexioRemota {
    
    public static void main(String[] args){

        ServerSocket servidor = null;
        Socket sc = null;
        DataInputStream in;
        DataOutputStream out;
        BufferedReader br;
        BufferedWriter bw;
        
        
        final int PUERTO = 5000;

        try{
            servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor iniciado");

            while(true){

            sc = servidor.accept();
            System.out.println("Cliente conectado");
                
            InputStream is = sc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            br = new BufferedReader(isr);

            OutputStream os = sc.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            bw = new BufferedWriter(osw);
            
            bw.write("HOla desde el servidor");
            bw.newLine();
            bw.flush();
            
            String missatge = br.readLine();
            System.out.println("Dades rebudes del cliente:"+ missatge);
                
                
                /*in = new DataInputStream(sc.getInputStream());
                out= new DataOutputStream(sc.getOutputStream());
                
                String mensaje = in.readUTF();
                
                out.writeUTF("hola mundo, desde el servidor");*/
                
                sc.close();
                System.out.println("Cliente desconectado");
                

            }





        }catch(IOException ex){
            Logger.getLogger(ConexioRemota.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
