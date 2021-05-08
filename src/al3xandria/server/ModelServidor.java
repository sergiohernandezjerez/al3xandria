/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al3xandria.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyStore;
import java.io.PrintWriter;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.net.ServerSocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;

/**
 *
 * @author PedroN
 * 
 * Classe que gestiona la connexio del Server amb el port indicat 
 * i amb el client
 * Obre els fluxos de entrada i sortida del server per a la comunicació 
 * entre server i client.
 * Gestiona els missatges rebuts del client i li permet obrir 
 * i tancar sessions d'usuari fent consultes a la BBDD.
 */

public class ModelServidor extends Thread{
    
    private  static  ControladorServidor controlador;
    private static final int PORT = 5556;
    private static ServerSocket sk;
    private static Socket socket;
    private  static ServerSocket _socket;
    private  static BufferedReader br;
    private static BufferedWriter bw;
    FuncionesBBDD obj = new FuncionesBBDD();
    java.util.Date data = new Date();    
    HashMap<String, String> map = new HashMap();
    ResultSetMetaData rsMD;
    static int conexionesActuales = 0;
    
    
    private static final String SERVIDOR_CLAU = "F:" + File.separator + 
    		"workSpaces" + File.separator + "eclipse" + File.separator + 
    		"Al3xandria" + File.separator + "certs" + File.separator + 
    		"serverPedro" + File.separator + "server_ks";
    private static final String SERVIDOR_CLAU_PASSWORD = "123123";
 

    public void servidor() throws IOException, NoSuchAlgorithmException, KeyStoreException, CertificateException, UnrecoverableKeyException, KeyManagementException, SQLException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
        System.out.println("SERVIDOR_CLAU: " + SERVIDOR_CLAU);

        System.setProperty("javax.net.ssl.trustStore", SERVIDOR_CLAU);
        SSLContext context = SSLContext.getInstance("TLS");

        KeyStore ks = KeyStore.getInstance("jceks");
       // KeyStore ks = KeyStore.getInstance("JKS");
        ks.load(new FileInputStream(SERVIDOR_CLAU), null);
        KeyManagerFactory kf = KeyManagerFactory.getInstance("SunX509");
        kf.init(ks, SERVIDOR_CLAU_PASSWORD.toCharArray());

        context.init(kf.getKeyManagers(), null, null);

        System.out.println("Esperant connexions");

        ServerSocketFactory factory = context.getServerSocketFactory();
         _socket = factory.createServerSocket(5556);
        ((SSLServerSocket) _socket).setNeedClientAuth(false);

        while (true) {
            //new ConexioSocketSSL(_socket.accept()).start();
            //obrirPort();
            esperarAlClient();
            newListener();
            crearFluxes();
            rebreMissatgeLogin();
            newListener();
        }        
    }
    
        private void newListener()
    {
        (new Thread(this)).start();
    }
    
    
    /**
     *
     * @param controlador
     */
    public void setControlador(ControladorServidor controlador){
        ModelServidor.controlador = controlador;
    }  
       
    /**
     *Mètode per connectar el server al PORT indicat
     */
    public void obrirPort(){
        
        try {
            
            _socket = new ServerSocket(PORT);
            
            
        } catch (IOException ex) {
            Logger.getLogger(ModelServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     *Mètode que permet conectar diversos clients a la vegada, encarregat 
     * d'obrir fils a mida que els clients es conecten
     * Crida i envia les dades al mètode tratamentDadesRebudes
     * @param data Misssatge que arriba del client
     */    
    public void replay( String data) {
        Thread t = new Thread(() -> {
            try {
                
                //answer(data);
                tratamentDadesRebudes(data);
            } catch (SQLException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
                Logger.getLogger(ModelServidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        t.run();
    }
  
    /**
     *Metode per obrir port per al client
     */
    public void esperarAlClient() throws SQLException{
        try {
            
            socket = _socket.accept();
            
            
        } catch (IOException ex) {
            Logger.getLogger(ModelServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *Mètode per obrir comunicació entre servidor i client
     */
    public void crearFluxes(){
        try {          
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            br = new BufferedReader(isr);

            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            bw = new BufferedWriter(osw);            
            
        } catch (IOException ex) {
            Logger.getLogger(ModelServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    


    /**
     *Métode per enviar missatges del servidor als clients
     * @param missatge que s'envia al client
     */
    public  void enviarMissatge(String missatge) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
        try {
            
            //MissatgesEncriptats.xifrarMissatge(missatge);
            bw.write(missatge);
            bw.newLine();
            bw.flush();
            System.out.println("Dades enviades al client: " + missatge);
            
        } catch (IOException ex) {
            Logger.getLogger(ModelServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Mètode encarregat d'enviar el valor 0 o 1 al client quan demana una 
     * modificació a la base de dades
     * 0 No s'ha pogut fer la modificació
     * 1 S'ha fet la modificació
     * @param valor
     */
    public  void enviarValor(int valor) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
        try {
           
            String rebut = String.valueOf(valor);
            bw.write(rebut);
            bw.newLine();
            bw.flush();
            System.out.println("Dades enviades al client: " + valor);
            
        } catch (IOException ex) {
            Logger.getLogger(ModelServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }      

    
    /**
     *Mètode que rep els missatges dels clients per fer 
     * login i logout i contesta segons
     * sigui el missatge rebut
     * @throws java.sql.SQLException
     */
    public void rebreMissatgeLogin() throws SQLException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
        
        try {
           
            String missatge = br.readLine(); 
            System.out.println("Dades rebudes del client:"+ missatge);
            replay( missatge);
        } catch (IOException ex) {
            Logger.getLogger(ModelServidor.class.getName()).log(Level.SEVERE, null, ex);
        }       
    } 

    /**
     * Metode que gestiona els missatges dels clients 
     * Fa crides primer per comprovació del login a mètodes inclosos en aquesta
     * mateixa classe.
     * Crea el codi de sessió en cas d'usuari registrat, que s'envia al mètode
     * comprovacioLogin
     * @param data paràmetre String que es el missatge del client 
     * @throws java.sql.SQLException 
     */
    public void tratamentDadesRebudes(String data) throws SQLException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
	String[] separateData = data.split(",");
        long cS=System.currentTimeMillis();//Numero de sessió
        String codiSessio = "cS"+cS+"";//Convertim long en String il i afegim un cS al principi
        
        if(separateData[0].startsWith("log")){
            comprovacioLogin(separateData,codiSessio);
        }else if(separateData[0].startsWith("cS")){
            usuariRegistratFuncions(separateData);
            
        }else if(separateData[0].startsWith("0")){
            usuariNoRegistratFuncions(separateData);

        }

    }

    /**
     * Funció encarregada de gestionar els usuaris no registrats que els permet
     * fer una busqueda de llibres i registrar-se com a usuaris.
     * Fa crides a la classe FuncionesBBDD per buscar a la BBDD i crida a la 
     * funció enviarMissatge per enviar el resultat als clients
     * @param data Missatge que li arriba del mètode tratamentDadesRebudes, 
     * es el missatge del client convertit en array de Strings
     */
    public void usuariNoRegistratFuncions(String []data) throws SQLException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
        switch(data[1]){
            case "consulta_llibre_tots":
                enviarMissatge(FuncionesBBDD.LlistarTotsllibres());
                break;
            case "consulta_autors":
                enviarMissatge(FuncionesBBDD.LlistarTotsAutors());
                break;
            case "consulta_generes":
                enviarMissatge(FuncionesBBDD.LlistarTotsGeneres());
                break;
            case "consulta_editorials":
                enviarMissatge(FuncionesBBDD.LlistarTotsEditorials());
                break;
            case "consulta_llibre_titol":
                enviarMissatge(FuncionesBBDD.buscarLlibreTitol(data));
                break;
            case "consulta_llibre_autor":
                enviarMissatge(FuncionesBBDD.buscarLlibreAutor(data));
                break;
            case "consulta_llibre_isbn":
                enviarMissatge(FuncionesBBDD.buscarLlibreIsbn(data));
                break;
            case "insercio_usuari":
                enviarValor(FuncionesBBDD.guardarUsuari(data));
                break;
            case "consulta_llibre_editorial":
                enviarMissatge(FuncionesBBDD.buscarLlibreEditorial(data));
                break;
            case "consulta_llibre_genere":
                enviarMissatge(FuncionesBBDD.buscarLlibreGenere(data));
                break;
            case "insercio_genere":
            	enviarValor(FuncionesBBDD.GuardarGenere(data));
                break;
            case "insercio_autor":
            	enviarValor(FuncionesBBDD.GuardarAutor(data));
                break;
            case "insercio_editorial":
            	enviarValor(FuncionesBBDD.GuardarEditorial(data));
                break;
            case "consulta_prestecs":
            	enviarMissatge(FuncionesBBDD.ConsultaPrestecs());
                break;
        }        
    }
    
    /**
     * Funció encarregada de gestionar els usuaris registrats que els permet
     * fer diverses funcions depenent del tipus de client(administrador o usuari).
     * Fa crides a la classe FuncionesBBDD per consultar o modificar la BBDD i 
     * crida a la funció enviarMissatge o enviarValor 
     * per enviar el resultat als clients de les consultes.
     * @param dada Missatge que li arriba del mètode tratamentDadesRebudes, 
     * es el missatge del client convertit en array de Strings
     * @throws SQLException
     */
    public void usuariRegistratFuncions(String []dada) throws SQLException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{

        switch(dada[1]){
            case "insercio_usuari" :
                enviarValor(FuncionesBBDD.guardarUsuari(dada));
                break;
            case "modificar_usuari":
                enviarValor(FuncionesBBDD.modificarUsuari(dada));
                break;
            case"borrar_usuari":
                enviarValor(FuncionesBBDD.eliminarUsuari(dada));
                break;
            case "consulta_usuari_tots":
                enviarMissatge(FuncionesBBDD.LlistarTotsUsuaris());
                break;
            case "consulta_usuariTots_nom":
                enviarMissatge(FuncionesBBDD.buscarUsuariTotsNom(dada));
                break;
            case "consulta_usuariTots_carnet":
                enviarMissatge(FuncionesBBDD.buscarUsuariTotsCarnet(dada));
                break;
            case "consulta_usuariTots_dninie":
                enviarMissatge(FuncionesBBDD.buscarUsuariTotsDniNie(dada));
                break;
            case "consulta_usuari_nom":
                enviarMissatge(FuncionesBBDD.buscarUsuariNom(dada));
                break;
            case "consulta_usuari_carnet":
                enviarMissatge(FuncionesBBDD.buscarUsuariCarnet(dada));
                break;
            case "consulta_usuari_dninie":
                enviarMissatge(FuncionesBBDD.buscarUsuariDniNie(dada));
                break;
            case "consulta_administrador_nom":
                enviarMissatge(FuncionesBBDD.buscarAdministradorNom(dada));
                break;
            case "consulta_administrador_carnet":
                enviarMissatge(FuncionesBBDD.buscarAdministradorCarnet(dada));
                break;
            case "consulta_administrador_dninie":
                enviarMissatge(FuncionesBBDD.buscarAdministradorDniNie(dada));
                break;
            case "consulta_usuari_aciut":
                enviarMissatge(FuncionesBBDD.buscarUsuariActiu(dada));
                break;
            case"insertar_editorial":
                enviarValor(FuncionesBBDD.insertarEditorial(dada));
                break;
            case "consulta_editorial":
                enviarMissatge(FuncionesBBDD.consultaEditorial(dada));
                break;
            case"insertar_autor":
                enviarValor(FuncionesBBDD.insertarAutor(dada));
                break;
            case"consulta_autor":
                enviarMissatge(FuncionesBBDD.consultaAutor(dada));
                break;
            case "insercio_llibre":
                enviarValor(FuncionesBBDD.guardarLlibre(dada));
                break;
            case "modificar_llibre":
                enviarValor(FuncionesBBDD.modificarLlibre(dada));
                break;
            case"eliminar_llibre":
                enviarValor(FuncionesBBDD.eliminarLlibre(dada));
                break;
            case "consulta_llibre_tots":
                enviarMissatge(FuncionesBBDD.LlistarTotsllibres());
                break;
            case "consulta_llibre_titol":
                enviarMissatge(FuncionesBBDD.buscarLlibreTitol(dada));
                break;
            case "consulta_llibre_autor":
                enviarMissatge(FuncionesBBDD.buscarLlibreAutor(dada));
                break;
            case "consulta_llibre_isbn":
                enviarMissatge(FuncionesBBDD.buscarLlibreIsbn(dada));
                break;
            case"consulta_llibre_editorial":
                enviarMissatge(FuncionesBBDD.buscarLlibreEditorial(dada));
                break;
            case"consulta_llibre_genere":
                enviarMissatge(FuncionesBBDD.buscarLlibreGenere(dada));
                break;
            case "consulta_usuari_tipus":
                enviarMissatge(FuncionesBBDD.buscarUsuariTipus(dada));
                break;
            case "mostrar_usuari":
                enviarMissatge(FuncionesBBDD.mostrarUsuariEmail(dada));
                break;
            case "fer_reserva":
                enviarValor(FuncionesBBDD.ferReserva(dada));
            default:
                enviarMissatge("440");
        }
    }
    
    /**
     * Mètode que rep el email i consulta a BBDD per retornar 
     * el tipus d'usuari
     * @param email enviat per el client
     * @return el tipus d'usuari
     */
    public String getTipusUsuari(String[] dades){
       String tipusUsuari =FuncionesBBDD.buscarTipusUsuari(dades[1]);
       return tipusUsuari;
    }
    
    /**
     * Mètode que s'encarrega de fer el login i el logout. Comprova que el 
     * usuari no estigui ja registrat per el paràmetre codiSessio
     * @param dades missatge enviat per el client 
     * @param codiSessio creat al mètode tratamentDadesRebudes
     */
    public void comprovacioLogin(String[] dades, String codiSessio) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
    
        switch (dades[0]) {
            case "login":
                if(!map.containsKey(dades[1])){//EL usuari no te una sessio oberta
                    
                    if(!"1".equals(FuncionesBBDD.buscarUsuarioRegistrado(dades))){//si l'usuari no está a la base dades
                        enviarMissatge("440");
                    }else{//el usuari està a la base dades i obrim sessió
                        map.put(dades[1], codiSessio);//introduin  la sessió al hashMap
                        System.out.println("Usuari: "+ dades[1]+" el codi de la teva sessió es: "+ codiSessio);//imprimin codi sessió
                        System.out.println("HashMap: "+ map);//Imprimim mapa per comprovació
                        enviarMissatge(1+","+codiSessio+","+getTipusUsuari(dades));//enviem missatge al client amb
                        //codi de sessió i tipus usuari                        
                    }                    
                }else{
                    enviarMissatge("550");//enviem missatge de codi d'error si
                    //l'usuari te sessió oberta
                }   break;
            case "logout":
                if( map.containsValue(dades[1])){//obrim finestra de tancament
                    enviarMissatge("1");
                }   break;
            case "logoutOK":
                enviarMissatge("1");//Finalitzem sessió
                map.remove(dades[1]);//Treiem el codi de sessio del hasMap 
                System.out.println("HashMap: "+ map);
                break;
            default:       
                break;
        }        
    }
}
