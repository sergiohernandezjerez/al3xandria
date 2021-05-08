/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al3xandria.pedroServer.controlador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.sql.SQLException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import al3xandria.pedroServer.model.ModelServidor;


/**
 *
 * @author PedroN
 * Classe controlador que activa i controla les pricipals funcions
 * de la classe ModelServidor
 */
public class ControladorServidor extends Thread {

    
    ModelServidor model;



    public ControladorServidor(ModelServidor model) {
       
        this.model = model;
    }
    
    
    public void arrancar() throws SQLException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, KeyStoreException, IOException, FileNotFoundException, CertificateException, UnrecoverableKeyException, KeyManagementException{
      
        //model.obrirPort();
        //model.generarClaus();
        model.servidor();

        //model.start();
        //Mantenim obert en tot moment la conexió del Servidor
        //per a que el client es pogui conectar sempre
        //while(true){
        //model.esperarAlClient();
        
        //model.crearFluxes();
        //model.rebreMissatgeLogin();
        //}
    }

   
    
}