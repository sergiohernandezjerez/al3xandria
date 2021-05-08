/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al3xandria.pedroServer.Cliente;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.KeyStore;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.net.SocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;



/**
 *
 * @author PedroN
 */
public class cliente {
    
    final String HOST = "127.0.0.1";
    final int PUERTO = 5000;
    static DataInputStream is;
    DataOutputStream out;
    BufferedReader br;
    BufferedWriter bw;
    final ResultSet resultSet = null;
    private static final String CLAU_CLIENT = "C:\\Program Files (x86)\\Java\\jre1.8.0_291\\bin\\client_ks";
    private static String CLAU_CLIENT_PASSWORD = "456456";

    
    public static void main (String[] args) throws SQLException, Exception{
        
        final String HOST = "127.0.0.1";          

        Socket s ;
        //Estableix el magatzem de claus a utilitzar per validar el certificat del servidor
        System.setProperty("javax.net.ssl.trustStore", CLAU_CLIENT);
        System.setProperty("javax.net.debug", "ssl,handshake");
        cliente client = new cliente();
        s = client.clientAmbCert();
        

        String enviaDades [] = {"cS3445532,consulta_llibre_autor,a",
            "cS3445532,consulta_llibre_isbn,22222",
            "cS34455655,consulta_llibre_editorial,a",
            "cS223347765,consulta_llibre_genere,a",
            "cS34455655,consulta_usuari_tots",
            "cS34455655,consulta_usuari_nom,a",
            "cS34455655,consulta_usuari_dni,543123432R",
            "cS34455655,consulta_usuari_tipus,administrador",                
            "cS223344355,consulta_llibre_tots",                
            "cS1617896594846,consulta_editorial,a",
            "cS334454466,consulta_llibre_titol,a",
            "cS23344333,consulta_autor,a",
            "00000000000,consulta_llibre_tots",
            "00000000000,consulta_llibre_autor,a",
            "00000000000,consulta_llibre_isbn,22222",
            "00000000000,consulta_llibre_titol,a",
                
            };
            
            
        for (int i = 0; i < enviaDades.length; i++) {
            try {
            

                PrintWriter output = new PrintWriter(s.getOutputStream());
                output.println(enviaDades[i]);
                output.flush();
                BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
                
                String data = input.readLine();

                System.out.println("--");
                System.out.println("Enviat al servidor: " + "cS3445532,consulta_llibre_autor,a");
                System.out.println("Rebut del servidor: " + data);
                output.close();
                input.close();
                s.close();
            } catch (Exception e) {
                System.err.println(e.getMessage());
                System.exit(1);
            }
        }
        
        
    }  
    
     Socket clientAmbCert() throws Exception {
        SSLContext context = SSLContext.getInstance("TLS");
        //KeyStore ks = KeyStore.getInstance("jceks");
        KeyStore ks = KeyStore.getInstance("JKS");

        ks.load(new FileInputStream(CLAU_CLIENT), null);
        KeyManagerFactory kf = KeyManagerFactory.getInstance("SunX509");
        kf.init(ks, CLAU_CLIENT_PASSWORD.toCharArray());
        context.init(kf.getKeyManagers(), null, null);

        SocketFactory factory = context.getSocketFactory();
        Socket s = factory.createSocket("localhost", 5556);
        return s;
    }
}
