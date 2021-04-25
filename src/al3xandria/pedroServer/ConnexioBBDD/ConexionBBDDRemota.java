/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al3xandria.pedroServer.ConnexioBBDD;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author PedroN
 * Classe per conectar de forma remota amb la BBDD local gestionada per 
 * PgAdminIII, feta per la falta del Servei web i perque els companys de
 * projecte puguin fer proves. 
 * S'ha de testejar, última prova no conectava
 */
public class ConexionBBDDRemota {
    
    /**
     *
     */
    public Connection coneccion = null;

    /**
     *
     * @throws SQLException
     */
    public ConexionBBDDRemota() throws SQLException{
        System.out.println(" Creando una instancia de la clase");
        try{
            System.out.println(" Buscando el driver de la base de datos PostgreSQL");
            Class.forName("org.postgresql.Driver");
        }catch(ClassNotFoundException e){
            System.out.println(" No se encontro el nombre del driver de la base de datos PostgreSQL" + e.getMessage());
        }
        System.out.println(" Estableciendo la url de conexión");
        String url = "jdbc:postgresql://192.168.1.51:5434/Projecte_Final_proves"; //hay que cambiar por el numero de ip o la ubicacion de la maquina
        try{
            System.out.println(" Estableciendo la conexión");
            coneccion = DriverManager.getConnection("jdbc:postgresql://192.168.1.51:5434/Projecte_Final_proves", "ioc", "ioc");
            System.out.println(" Obteniendo los metadatos de la base de datos");
            DatabaseMetaData dbmd = coneccion.getMetaData();
            System.out.println(" Conexión exitosa. Nombre del producto: " + dbmd.getDatabaseProductName() + " Versión: " + dbmd.getDatabaseProductVersion());
        }catch(SQLException e){
            System.out.println(" Excepción al establecer la conexión " + e.getMessage());
        }
    }    
}
