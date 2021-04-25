/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al3xandria.pedroServer.ConnexioBBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author PedroN
 * Classe que serveix per conectar
 * amb la base de dades local allotjada al 
 * administardor de BBDD PgAdminIII, utilitzant API JDBC.
 */
public class ConexioBBDD {    
    
    /* DATOS PARA LA CONEXION */
    /** Nombre de la base de datos */
    private static String db = "al3xandria";
    /** Usuario postgreSQL */
    private static String user = "postgres";
    /** Contraseña postgreSQL */
    private static String password = "d466986";
    /** Cadena de conexión */
    private static String url = "jdbc:postgresql://localhost:5432/"+db;
    /** Conexion a base de datos */    
    Connection conn = null;
    /** Driver de connexió  */
    public static String clase = "org.postgresql.Driver";
    java.sql.Connection con;
    
    /**
     *Constructor 
     * @return conexio a BBDD
     */
    public static Connection ConexioBBDD(){
        
        
        Connection conexion = null;
            try{
                Class.forName(clase);
                conexion = DriverManager.getConnection(url, user , password);
                System.out.println("Conexión establecida");
            }
            catch(ClassNotFoundException | SQLException e){
                System.out.println(e);
            }
            return conexion; 
    }
    
      
}
