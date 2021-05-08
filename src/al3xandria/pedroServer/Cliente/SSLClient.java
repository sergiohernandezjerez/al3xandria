/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al3xandria.pedroServer.Cliente;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.KeyStore;
import javax.net.SocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
/**
 *
 * @author PedroN
 */
public class SSLClient {
    
        // private static String CLAU_CLIENT = "C:\\Program Files (x86)\\Java\\jre1.8.0_161\\bin\\client_ks";
    //("." + File.separator + "nomDeLaCarpeta" + File.separator + "nomDeLArxiu.Extensio")
    
    /*
    Primer que tot hem de crear per cada socket un certificat, aquest no és res
    més que un fitxer que generarem amb l'eina keytool de Java. 
    De fet, necessitarem crear 2 certificats, un pel servidor i l'altre pel client.
     */
    
    // Anna: el meu certificat del server l'he creat a Users/anna/ProjectesIOC/NetBeansProjects/Certificats/client_ks
    private static final String CLAU_CLIENT = "C:\\Program Files (x86)\\Java\\jre1.8.0_291\\bin\\client_ks";
    private static String CLAU_CLIENT_PASSWORD = "456456";
    //private static String CLAU_CLIENT_PASSWORD = "client";

    public static void main(String[] args) throws Exception {

        //Estableix el magatzem de claus a utilitzar per validar el certificat del servidor
        System.setProperty("javax.net.ssl.trustStore", CLAU_CLIENT);
        System.setProperty("javax.net.debug", "ssl,handshake");
        SSLClient client = new SSLClient();
        Socket s = client.clientAmbCert2();
        //Socket s = client.clientSenseCert();

        PrintWriter writer = new PrintWriter(s.getOutputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
        writer.println("cS22123435,mostrar_usuari,pedro@pedro.com");
        writer.flush();
        System.out.println(reader.readLine());
        s.close();
    }

    private Socket clientSenseCert() throws Exception {
        SocketFactory sf = SSLSocketFactory.getDefault();
        Socket s = sf.createSocket("localhost", 5556);
        return s;
    }

    Socket clientAmbCert2() throws Exception {
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
