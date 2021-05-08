/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al3xandria.server;

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

/**
 *
 * @author PedroN
 * Classe que  executa el Servidor
 */
public class MainServer {
    
    public static void main(String[] args) throws SQLException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, KeyStoreException, IOException, FileNotFoundException, CertificateException, UnrecoverableKeyException, KeyManagementException {
        
        ModelServidor model = new ModelServidor();
        ControladorServidor controlador = new ControladorServidor(model);       
        model.setControlador(controlador);        
        controlador.arrancar();       
    }   
}

