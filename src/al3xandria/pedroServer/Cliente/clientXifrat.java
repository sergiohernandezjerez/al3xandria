/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al3xandria.pedroServer.Cliente;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.math.BigInteger; 
import java.net.InetSocketAddress; 
import java.net.Socket; 
import java.security.InvalidKeyException;
import java.security.Key; 
import java.security.KeyFactory; 
import java.security.NoSuchAlgorithmException;
import java.security.spec.RSAPublicKeySpec;
import java.sql.SQLException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher; 
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator; 
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey; 
import javax.crypto.SecretKeyFactory; 
import javax.crypto.spec.DESKeySpec;

/**
 *
 * @author PedroN
 */
public class clientXifrat {
    
    private  static BufferedReader br;
    private static PrintWriter pw;
    private static BufferedWriter bw;
    private static KeyPairGenerator keygen = null;
    private static KeyPair keypair = null;
    private static SecretKeyFactory secretkeyfactory = null;
    private static DESKeySpec deskeyspec = null;
    private static  SecretKey key = null;
    private static Cipher rsaCipher = null;
    private static Cipher desCipher = null;
    private static RSAPublicKeySpec keyspec = null;
    private static KeyFactory keyfac = null;
    private static Key public_key = null;
    private static KeyGenerator seckeygen = null;
    private static SecretKeyFactory seckeyfac = null;
    private static DESKeySpec seckeyspec = null;
    
    private static String prepararClauXifrada(byte[] clau) {    
        String resultat = "";

        for (byte b : clau) 
        {      
            resultat += ((int)b)+",";    
        }
        return resultat;  
    }

    public static void generarClauXifrada() throws InvalidKeyException{
        try {
            Socket clientSocket = new Socket();            
            InetSocketAddress addr = new InetSocketAddress("localhost", 5556);      
            clientSocket.connect(addr);            
            InputStream is = clientSocket.getInputStream();      
            OutputStream os = clientSocket.getOutputStream();      
            br = new BufferedReader(new InputStreamReader(is));      
            pw = new PrintWriter(os, true);

            // Reb clau pública utilitzant un algorisme RSA

            BigInteger modulus = new BigInteger(br.readLine());      
            BigInteger exponente = new BigInteger(br.readLine());      
            keyspec = new RSAPublicKeySpec(modulus, exponente); 
            keyfac = KeyFactory.getInstance("RSA");      
            public_key = keyfac.generatePublic(keyspec);

            // Creant objecte de xifrat asimètric RSA           

            rsaCipher = Cipher.getInstance("RSA");      
            rsaCipher.init(Cipher.ENCRYPT_MODE, public_key);    

            // Generant clau de sessió            

            seckeygen = KeyGenerator.getInstance("DES");      
            key = seckeygen.generateKey();

            // Enviant clau de sessió al servidor, xifrada amb la clau pública.           

            seckeyfac = SecretKeyFactory.getInstance("DES");      
            seckeyspec = (DESKeySpec) seckeyfac.getKeySpec(key, DESKeySpec.class);
            String clau_xifrada = prepararClauXifrada(rsaCipher.doFinal(seckeyspec.getKey()));      

            System.out.println("CLAU de SESSIÓ"+clau_xifrada);

            pw.println(clau_xifrada);
            clientSocket.close();
        }catch (Exception e) {      
                     e.printStackTrace();    
        }             

    }
    
    public static String desxifrarMissatge(String id_fitxer_xifrat) throws NoSuchAlgorithmException, NoSuchPaddingException, IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
        // Creant el objecte de xifrat de sessió     

        desCipher = Cipher.getInstance("DES");

        // Rep el id del fitxer

        id_fitxer_xifrat = br.readLine();  

        System.out.println("ID del fitxer xifrat"+id_fitxer_xifrat);
        desCipher.init(Cipher.DECRYPT_MODE, key);  
              
        byte []aux=Base64.getDecoder().decode(id_fitxer_xifrat);      
        String id_fitxer = new String(desCipher.doFinal(aux));            
        System.out.println("Fitxer rebut del client desencriptat: "+ id_fitxer); 
        
        return id_fitxer;
    }
        
        public static byte [] xifrarMissatge(String perxifrar) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
         
        System.out.println("Fitxer sense xifrar : " +perxifrar);
        desCipher = Cipher.getInstance("DES");          
        desCipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = desCipher.doFinal(perxifrar.getBytes());
        byte[] a = Base64.getEncoder().encode(encrypted);
        return a;
    }
        
    public static void main (String[] args) throws SQLException, InvalidKeyException{
        
        final String HOST = "127.0.0.1";          

        Socket sc ;
        
        String dades = "cS3445532,consulta_llibre_autor,a"; 
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
            try {
                generarClauXifrada();
                sc = new Socket("localhost", 5556);
                PrintWriter output = new PrintWriter(new OutputStreamWriter(sc.getOutputStream()), true);
                output.println(xifrarMissatge(dades));
                BufferedReader input = new BufferedReader(new InputStreamReader(sc.getInputStream()));
                String data = input.readLine();
                String rebut = desxifrarMissatge(data);
                System.out.println("--");
                System.out.println("Enviat al servidor: " + dades);
                System.out.println("Rebut del servidor: " + rebut);
                sc.close();
            } catch (Exception e) {
                System.err.println(e.getMessage());
                System.exit(1);
            }
            
        /*for (int i = 0; i < enviaDades.length; i++) {
            try {
                sc = new Socket("localhost", 5556);
                PrintWriter output = new PrintWriter(new OutputStreamWriter(sc.getOutputStream()), true);
                output.println(xifrarMissatge(enviaDades[i]));
                BufferedReader input = new BufferedReader(new InputStreamReader(sc.getInputStream()));
                String data = input.readLine();
                String rebut = desxifrarMissatge(data);
                System.out.println("--");
                System.out.println("Enviat al servidor: " + enviaDades[i]);
                System.out.println("Rebut del servidor: " + rebut);
                sc.close();
            } catch (Exception e) {
                System.err.println(e.getMessage());
                System.exit(1);
            }
        }*/
    }    
    
    
}
