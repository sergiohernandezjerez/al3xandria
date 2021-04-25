/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al3xandria.pedroServer.Main;

import al3xandria.pedroServer.controlador.ControladorServidor;
import al3xandria.pedroServer.model.ModelServidor;

/**
 *
 * @author PedroN
 * Classe que  executa el Servidor
 */
public class MainServer {
    
    public static void main(String[] args) {
        
        ModelServidor model = new ModelServidor();
        ControladorServidor controlador = new ControladorServidor(model);       
        model.setControlador(controlador);        
        controlador.arrancar();       
    }   
}
