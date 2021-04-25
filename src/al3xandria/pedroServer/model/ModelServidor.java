/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al3xandria.pedroServer.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import al3xandria.pedroServer.ConnexioBBDD.FuncionesBBDD;
import al3xandria.pedroServer.controlador.ControladorServidor;

import java.util.Date;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.sql.ResultSetMetaData;

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
    private  static BufferedReader br;
    private static BufferedWriter bw;
    FuncionesBBDD obj = new FuncionesBBDD();
    java.util.Date data = new Date();    
    HashMap<String, String> map = new HashMap();
    ResultSetMetaData rsMD;
    
    
    /**
     *
     * @param controlador
     */
    public void setControlador(ControladorServidor controlador){
        this.controlador = controlador;
    }  
       
    /**
     *Mètode per connectar el server al PORT indicat
     */
    public void obrirPort(){
        
        try {
            
            sk = new ServerSocket(PORT);
            
            
        } catch (IOException ex) {
            Logger.getLogger(ModelServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *Metode per obrir port per al client
     */
    public void esperarAlClient(){
        try {
            
            socket = sk.accept();
            
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
    public  void enviarMissatge(String missatge){
        try {
            
            bw.write(missatge);
            bw.newLine();
            bw.flush();
            System.out.println("Dades enviades al client: " + missatge);
            
        } catch (IOException ex) {
            Logger.getLogger(ModelServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public  void enviarValor(int valor){
        try {
            
            bw.write(valor);
            bw.newLine();
            bw.flush();
            System.out.println("Dades enviades al client: " + valor);
            
        } catch (IOException ex) {
            Logger.getLogger(ModelServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }      
    
    public  void enviarObject(Object objecte){
        try {
            FileOutputStream fos = new FileOutputStream("t.tmp");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            oos.writeObject(objecte);
            System.out.println("Dades enviades al client: " + objecte.toString());
            
        } catch (IOException ex) {
            Logger.getLogger(ModelServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    /**
     *Mètode que rep els missatges dels clients per fer 
     * login i logout i contesta segons
     * sigui el missatge rebut
     */
    public void rebreMissatgeLogin(){
        
        try {
           
            String missatge = br.readLine(); 
            tratamentDadesRebudes(missatge);
            System.out.println("Dades rebudes del client:"+ missatge);

        } catch (IOException ex) {
            Logger.getLogger(ModelServidor.class.getName()).log(Level.SEVERE, null, ex);
        }       
    } 

    /**
     * Metode que gestiona els missatges dels clients 
     * En aquest cas poden ser missatges de login i logout únicament
     * Consulta a la base de dades per mirar si es un missatge
     * de un client registrat o no
     * Fa les funcions de login i logout.
     * @param data paràmetre String que arriba que es el missatge del client 
     */
    public void tratamentDadesRebudes(String data) {
	String[] separateData = data.split(",");
        long cS=System.currentTimeMillis();//Numero de sessió
        String codiSessio = "cS"+cS+"";//Convertim long en String il i afegim un cS al principi
        
        if(separateData[0].startsWith("log")){
            comprovacioLogin(separateData,codiSessio);
        }else if(separateData[0].startsWith("cS")){
            usuariRegistratFuncions(separateData);

        }else if(separateData[0].startsWith("0")){
            usuariNoRegistratFuncions(separateData);

            switch(separateData[2]){
                case "consulta_llibre_tots":
                    enviarObject(FuncionesBBDD.LlistarTotsllibres());
                    break;
                case "consulta_llibre_titol":
                    enviarObject(FuncionesBBDD.buscarLlibreTitol(separateData));
                    break;
                case "consulta_llibre_autor":
                    enviarObject(FuncionesBBDD.buscarLlibreAutor(separateData));
                    break;
                case "consulta_llibre_isbn":
                    enviarObject(FuncionesBBDD.buscarLlibreEditorial(separateData));
                    break;
            }
        }

    }
    public void usuariNoRegistratFuncions(String []data){
        switch(data[2]){
            case "consulta_llibre_tots":
                enviarObject(FuncionesBBDD.LlistarTotsllibres());
                break;
            case "consulta_llibre_titol":
                enviarObject(FuncionesBBDD.buscarLlibreTitol(data));
                break;
            case "consulta_llibre_autor":
                enviarObject(FuncionesBBDD.buscarLlibreAutor(data));
                break;
            case "consulta_llibre_isbn":
                enviarObject(FuncionesBBDD.buscarLlibreEditorial(data));
                break;
        }        
    }
    
    public void usuariRegistratFuncions(String []dada){

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
                    enviarObject(FuncionesBBDD.LlistarTotsUsuaris());
                    break;
                case "consulta_usuari_nom":
                    enviarObject(FuncionesBBDD.buscarUsuariNom(dada));
                    break;
                case "consulta_usuari_dni":
                    enviarObject(FuncionesBBDD.buscarUsuariDNI(dada));
                    break;
                case"insertar_editorial":
                    enviarValor(FuncionesBBDD.insertarEditorial(dada));
                    break;
                case "consulta_editorial":
                    enviarObject(FuncionesBBDD.consultaEditorial(dada));
                    break;
                case"insertar_autor":
                    enviarValor(FuncionesBBDD.insertarAutor(dada));
                    break;
                case"consulta_autor":
                    enviarObject(FuncionesBBDD.consultaAutor(dada));
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
                    enviarObject(FuncionesBBDD.LlistarTotsllibres());
                    break;
                case "consulta_llibre_titol":
                    enviarObject(FuncionesBBDD.buscarLlibreTitol(dada));
                    break;
                case "consulta_llibre_autor":
                    enviarObject(FuncionesBBDD.buscarLlibreAutor(dada));
                    break;
                case "consulta_llibre_isbn":
                    enviarObject(FuncionesBBDD.buscarLlibreEditorial(dada));
                    break;
                case"consulta_llibre_editorial":
                    enviarObject(FuncionesBBDD.buscarLlibreEditorial(dada));
                    break;
                case "consulta_usuari_tipus":
                    enviarObject(FuncionesBBDD.buscarUsuariTipus(dada));
                    break;
                default:
                    enviarMissatge("440");
        }
    }
    
    /**
     * Mètode que rep el email i consulta a BBDD per retornar 
     * el tipus d'usuari
     * @param email
     * @return 
     */
    public String getTipusUsuari(String email){
       String tipusUsuari =FuncionesBBDD.buscarTipusUsuari(email);
       return tipusUsuari;
    }
    
    public void comprovacioLogin(String[] dades, String codiSessio){
    
        switch (dades[0]) {
            case "login":
                if(!map.containsKey(dades[1])){//EL usuari no te una sessio oberta
                    
                    if(!"1".equals(FuncionesBBDD.buscarUsuarioRegistrado(dades))){//si l'usuari no está a la base dades
                        enviarMissatge("440");
                    }else{//el usuari està a la base dades iobrim sessió
                        map.put(dades[1], codiSessio);//introduin  la sessió al hashMap
                        System.out.println("Usuari: "+ dades[1]+" el codi de la teva sessió es: "+ codiSessio);//imprimin codi sessió
                        System.out.println("HashMap: "+ map);//Imprimim mapa per comprovació
                        enviarMissatge(0+","+codiSessio+","+getTipusUsuari(dades[1]));//enviem missatge al client amb
                        //codi de sessió i tipus usuari                        
                    }                    
                }else{
                    enviarMissatge("550");//enviem missatge de codi d'error si
                    //l'usuari te sessió oberta
                }   break;
            case "logout":
                if( map.containsValue(dades[1])){//obrim finestra de tancament
                    enviarMissatge("0");
                }   break;
            case "logoutOK":
                enviarMissatge("0");//Finalitzem sessió
                map.remove(dades[1]);//Treiem el codi de sessio del hasMap 
                System.out.println("HashMap: "+ map);
                break;
            default:       
                break;
        }        
    }

}
