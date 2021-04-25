/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al3xandria.pedroServer.controlador;

import al3xandria.pedroServer.model.ModelServidor;

/**
 *
 * @author PedroN
 * Classe controlador que activa i controla les pricipals funcions
 * de la classe ModelServidor
 */
public class ControladorServidor {

    
    ModelServidor model;

    public ControladorServidor(ModelServidor model) {
       
        this.model = model;
    }
    
    public void arrancar(){
      
        model.obrirPort();
        //Mantenim obert en tot moment la conexió del Servidor
        //per a que el client es pogui conectar sempre
        while(true){
        model.esperarAlClient();
        model.crearFluxes();
        model.rebreMissatgeLogin();
        }
    }

   
    
}
