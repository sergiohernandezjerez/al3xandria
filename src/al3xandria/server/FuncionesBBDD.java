/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al3xandria.server;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 *
 * @author PedroN
 * Classe on es guardan les funcions de consulta i
 * modificació de la BBDD local
 */
public class FuncionesBBDD {

    private static ConexioBBDD conexion = new ConexioBBDD();
    private static PreparedStatement sentencia_preparada;

    /**
     *
     */
    public static ResultSet resultado;
    /** Para enviar comandos SQL a la base de datos */
    private static Statement statement = null;
    /** Para obtener los resultados de las consultas SQL de la base de datos */
    private static ResultSet resultSet = null;
    //private static ResultSetMetaData rsMD = null;
    private static Connection conexio = null;
    private static String resultSetAsString = "";
    
    
    /**
     *Converteix resultSet en String
     * @param rs ResultSet
     * @return String
     * @throws SQLException
     */
    public static  String ResultSetToString(ResultSet rs) throws SQLException{
        StringBuilder builder = new StringBuilder();
        //rsMD = rs.getMetaData();
        int columnCount = rs.getMetaData().getColumnCount();
        while (rs.next()) {
            for (int i = 0; i < columnCount;) {
                builder.append(rs.getString(i + 1));
                if (++i < columnCount) builder.append(",");
            }
           
                 builder.append("\r\n");
        }
        resultSetAsString = builder.toString();
            
        return resultSetAsString;
    }
    
    /**
     * Mètode que busca usuaris per el nom
     * @return StringJson
     * @throws SQLException
    */
    public static String MostrarUsuari() throws SQLException{
        String retorn = "";
        try {
            conexio = ConexioBBDD.ConexioBBDD();
            statement = conexio.createStatement();
            resultSet = statement.executeQuery("select json_agg(t)::jsonb FROM "
                    + "(SELECT nom_usuari,cognoms_usuari,dni_nie,email,adreca,"
                    + "codi_postal,poblacio,provincia,pais,telefon,carnet FROM usuaris "
                    + " ORDER BY cognoms_usuari)t");                  
            
        }catch (SQLException ex) {
              
            System.err.println( ex.getMessage() );
           
        }          
            retorn = ResultSetToString(resultSet);              
         
        return retorn;
    }
    

    /**
     * Busca usuari registrat a la BBDD
     * @param data rep paràmetres de busqueda,en aquest cas email i contransenya
     * conecta amb BBDD i li envia sentencia SQL
     * @return la busqueda
    */
    
    public static String buscarUsuarioRegistrado(String []data){
        
        String busqueda_usuario="";

        try{
            conexio = ConexioBBDD.ConexioBBDD();
            String sentencia_buscar_usuario =("SELECT email,contrasenya "
                    + "FROM usuaris WHERE email = '"+ data[1] +"' "
                    + "AND contrasenya =  '"+ data[2] + "'");
            sentencia_preparada = conexio.prepareStatement(sentencia_buscar_usuario);
            resultado = sentencia_preparada.executeQuery();
            if(resultado.next()){
                busqueda_usuario = "1";                
            }else{
                busqueda_usuario = "0";
            }           
            conexio.close();
        }catch(Exception e){
           System.out.println(e); 
        }
        return busqueda_usuario;
    }
    
    /**
     * Buscar tipus usuari a la BBDD segons email rebut per paràmetre
     * @param email
     * @return tipus usuari
     */
    public static String buscarTipusUsuari(String email){
        
        String busqueda_usuario="";
        //Connection conexion = null;
        try{
            conexio = ConexioBBDD.ConexioBBDD();
            String sentencia_buscar =("SELECT tipus_usuari FROM usuaris WHERE email = '"+ email +"'");
            sentencia_preparada = conexio.prepareStatement(sentencia_buscar);
            resultado = sentencia_preparada.executeQuery();
            if(resultado.next()){
                String tipusUsusari = resultado.getString("tipus_usuari");
                busqueda_usuario = tipusUsusari;                
            } else{
                busqueda_usuario = "0";
            }      
            conexio.close();
        }catch(Exception e){
           System.out.println(e); 
        }
        return busqueda_usuario;
    }
    
        /**
     * Mètode que mostra llistat de tots els ususaris
     * @return String Json
     * @throws SQLException
     */
    public static String LlistarTotsUsuaris() throws SQLException{
        
        try {
            conexio = ConexioBBDD.ConexioBBDD();
            statement = conexio.createStatement();
            resultSet = statement.executeQuery("select json_agg(t)::jsonb FROM (select * from usuaris ORDER BY carnet)t");
            //rsMD = resultSet.getMetaData();
            
        }catch (SQLException ex) {
            System.err.println( ex.getMessage() );
        }
        String retorn = ResultSetToString(resultSet);
        return retorn;
    }
    
    /**
     * Mètode que busca usuaris per el nom
     * @param data missatge enviat amb el client amb tots els paràmetres
     * @return StringJson
     * @throws SQLException
     */
    public static String buscarUsuariNom(String []data) throws SQLException{

        try {
            conexio = ConexioBBDD.ConexioBBDD();
            statement = conexio.createStatement();
            resultSet = statement.executeQuery("select json_agg(t)::jsonb FROM (SELECT * FROM usuaris WHERE "
                    + "nom_usuari like '%" +data[2]+ "%' and tipus_usuari like 'usuari' ORDER BY cognoms_usuari)t");
            //rsMD = resultSet.getMetaData();

        }catch (SQLException ex) {
            System.err.println( ex.getMessage() );
        }
        String retorn = ResultSetToString(resultSet);
        return retorn;
    }
    
    /**
     * Mètode que busca usuaris per el dni
     * @param data missatge enviat amb el client amb tots els paràmetres
     * @return
     * @throws SQLException
     */
    public static String buscarUsuariCarnet(String []data) throws SQLException{

        try {
            conexio = ConexioBBDD.ConexioBBDD();
            statement = conexio.createStatement();
            resultSet = statement.executeQuery("select json_agg(t)::jsonb FROM (SELECT * FROM usuaris WHERE "
                    + "carnet = '" +data[2]+ "' and tipus_usuari like 'usuari' ORDER BY cognoms_usuari)t");
            //rsMD = resultSet.getMetaData();

        }catch (SQLException ex) {
            System.err.println( ex.getMessage() );
        }
        String retorn = ResultSetToString(resultSet);
        return retorn;
    }
    
        /**
     * Mètode que busca usuaris per el email
     * @param data missatge enviat amb el client amb tots els paràmetres
     * @return
     * @throws SQLException
     */
    public static String mostrarUsuariEmail(String []data) throws SQLException{

        try {
            conexio = ConexioBBDD.ConexioBBDD();
            statement = conexio.createStatement();
            resultSet = statement.executeQuery("select json_agg(t)::jsonb FROM (SELECT * FROM usuaris WHERE "
                    + "email = '" +data[2]+ "' ORDER BY cognoms_usuari)t");
            //rsMD = resultSet.getMetaData();

        }catch (SQLException ex) {
            System.err.println( ex.getMessage() );
        }
        String retorn = ResultSetToString(resultSet);
        return retorn;
    }
    
    /**
     * Mètode que busca usuaris per el tipus d'usuari
     * @param data missatge enviat amb el client amb tots els paràmetres
     * @return StringJson
     * @throws SQLException
     */
    public static String buscarUsuariTipus(String []data) throws SQLException{

        try {
            conexio = ConexioBBDD.ConexioBBDD();
            statement = conexio.createStatement();
            resultSet = statement.executeQuery("select json_agg(t)::jsonb FROM (SELECT * FROM usuaris WHERE "
                    + "tipus_usuari = '" +data[2]+ "'ORDER BY cognoms_usuari)t");
            //rsMD = resultSet.getMetaData();

        }catch (SQLException ex) {
            System.err.println( ex.getMessage() );
        }
        String retorn = ResultSetToString(resultSet);
        return retorn;
    }

    /**
     * Mètode que s'utilitzarà per insertar usuari en BBDD, rep  un String amb 
     * tots els paràmetres que demanarà la BBDD
     * @param data
     * @return
     */
    public static int guardarUsuari(String[] data){
        
        int resultado = 0;
        String nom = data[2];
        String cognoms = data[3];
        String dni_nie = data[4];
        String email = data[5];
        String contrasenya = data[6]; 
        String adreca = data[7];
        String codiPostal = data[8];
        String poblacio = data[9]; 
        String provincia = data[10]; 
        String pais = data[11];
        String telefon = data[12];            
        String tipus_usuari = data[13];
        Boolean actiu;
        if(data[14].equals("true")) {
        	actiu = true;
        }else {
        	actiu = false;
        }

        
        
        String sentencia_guardar =("INSERT INTO usuaris(nom_usuari, " 
                + "cognoms_usuari, dni_nie, email, contrasenya, " 
                + "adreca, codi_postal, poblacio, provincia, pais, "
                + "telefon, tipus_usuari, actiu) "
                + "values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
         
         try{
            conexio = ConexioBBDD.ConexioBBDD();
            sentencia_preparada = conexio.prepareStatement(sentencia_guardar);
            sentencia_preparada.setString(1, nom);
            sentencia_preparada.setString(2, cognoms);
            sentencia_preparada.setString(3, dni_nie);
            sentencia_preparada.setString(4, email);
            sentencia_preparada.setString(5, contrasenya);
            sentencia_preparada.setString(6, adreca);
            sentencia_preparada.setString(7, codiPostal);
            sentencia_preparada.setString(8, poblacio);
            sentencia_preparada.setString(9, provincia);
            sentencia_preparada.setString(10, pais);
            sentencia_preparada.setString(11, telefon);
            sentencia_preparada.setString(12, tipus_usuari);
            sentencia_preparada.setBoolean(13, actiu);
            resultado = sentencia_preparada.executeUpdate();
            sentencia_preparada.close();
             
            conexio.close();
             
         }catch(Exception e){
             
            System.out.println(e);
         }
         return resultado;        
    }
    
     /**
     * Mètode que s'utilitzarà per insertar usuari en BBDD, rep  un String amb 
     * tots els paràmetres que demanarà la BBDD
     * @param data dades per modificar el usuari
     * @return  1 si s'ha fet modifcació i 0 si no
     */
    public static int modificarUsuari(String []data){

    	int resultado = 0;
        String nom = data[2];
        String cognoms = data[3];
        String dni_nie = data[4];
        String email = data[5];
        String contrasenya = data[6]; 
        String adreca = data[7];
        String codiPostal = data[8];
        String poblacio = data[9]; 
        String provincia = data[10]; 
        String pais = data[11];
        String telefon = data[12];            
        String tipus_usuari = data[13];
        Boolean actiu;
        if(data[14].equals("true")) {
        	actiu = true;
        }else {
        	actiu = false;
        }
        int carnet = Integer.parseInt(data[15]);
        
        String sentencia_guardar =("UPDATE usuaris set nom_usuari='"+nom+"',"
                    + "cognoms_usuari='"+cognoms+"', dni_nie='"+dni_nie+"',"
                    + "email='"+email+"', contrasenya='"+contrasenya+"',"
                    + "adreca='"+adreca+"',codi_postal='"+codiPostal+"', "
                    + "poblacio = '"+poblacio+"', "
                    + "provincia = '"+provincia+"', pais = '"+pais+"', "
                    + "telefon = '"+telefon+"', tipus_usuari = '"+tipus_usuari+"',"
                    + "actiu = '"+actiu+"'"
                    + "WHERE carnet="+carnet+"");
         
        try{
            conexio = ConexioBBDD.ConexioBBDD();
            sentencia_preparada = conexio.prepareStatement(sentencia_guardar);
            resultado = sentencia_preparada.executeUpdate();
            sentencia_preparada.close();
             
            conexio.close();
             
        }catch(Exception e){
             
            System.out.println(e);
        }
        return resultado;        
    }
    
    
     /**
     * Mètode que s'utilitzarà per insertar usuari en BBDD, rep  un String amb 
     * tots els paràmetres que demanarà la BBDD
     * @param data carnet del usuari a eliminar
     * @return 1 si s'ha fet modoifcació i 0 si no
     */
    public static int eliminarUsuari(String []data){

        int resultado = 0;
        Integer carnet = Integer.parseInt(data[2]);
        String sentencia_guardar =("DELETE from usuaris "
                    + "WHERE carnet="+carnet+"");
         
        try{
            conexio = ConexioBBDD.ConexioBBDD();
            sentencia_preparada = conexio.prepareStatement(sentencia_guardar);
            resultado = sentencia_preparada.executeUpdate();
            sentencia_preparada.close();
             
            conexio.close();
             
        }catch(Exception e){
             
            System.out.println(e);
        }
        return resultado;        
    }
    
    /**
     * Mètode per donar d'alta una nova editorial
     * @param data
     * @return 1 si s'ha fet modoifcació i 0 si no
     */
    public static int insertarEditorial(String []data){

        int resultado = 0;
        String sentencia_guardar =("INSERT INTO  editorials (nom_editorial) "
                                   + "values('"+data[2]+"')  ");                  
         
        try{
            conexio = ConexioBBDD.ConexioBBDD();
            sentencia_preparada = conexio.prepareStatement(sentencia_guardar);
            resultado = sentencia_preparada.executeUpdate();
            sentencia_preparada.close();
             
            conexio.close();
             
        }catch(Exception e){
             
            System.out.println(e);
        }
        return resultado;        
    }
    
    /**
     * Mètode per donar d'alta un nou autor
     * @param data nom del nou autor
     * @return 1 si s'ha fet modificació i 0 si no
     */
    public static int insertarAutor(String []data){

        int resultado = 0;
        String sentencia_guardar =("INSERT INTO  autors (nom_autor) "
                                   + "values('"+data[2]+"')  ");                  
         
        try{
            conexio = ConexioBBDD.ConexioBBDD();
            sentencia_preparada = conexio.prepareStatement(sentencia_guardar);
            resultado = sentencia_preparada.executeUpdate();
            sentencia_preparada.close();
             
            conexio.close();
             
        }catch(Exception e){
             
            System.out.println(e);
        }
        return resultado;        
    }
    
    
    /**
     * Mètode que s'utilitzarà per insertar usuari en BBDD, rep  un String amb 
     * tots els paràmetres que demanarà la BBDD
     * @param data Dades de modificació del llibre
     * @return 1 si s'ha fet modificació i 0 si no
    */
    public static int modificarLlibre(String []data){

    	 LocalDate data_publicacio = null;
         int resultado = 0;
         String titol = data[2];
         
         String nom_autor = data[4];
         String nom_genere = data[5];
         String nom_editorial = data[6];
         int puntuacio = Integer.parseInt(data[7]);
         String isbn = data[8];
         String edicio = data[9];
         data_publicacio= LocalDate.parse(data[10]); 
         int num_pagines = Integer.parseInt(data[11]);
         String sinopsis = data[13];
         
         String sentencia_guardar =("UPDATE llibres set titol = ?, data_publicacio = ?, edicio = ?, puntuacio = ?,"
         		+ "sinopsis = ?, num_pagines = ?, id_autor = (select id_autor from autors where nom_autor like ?)"
         		+ ", id_genere = (select id_genere from generes where generes.nom_genere like ?),"
         		+ " id_editorial = (select id_editorial from editorials where editorials.nom_editorial like ?) "
         		+ "where isbn like ?");
         
        
          try{
             conexio = ConexioBBDD.ConexioBBDD();
             sentencia_preparada = conexio.prepareStatement(sentencia_guardar);
             sentencia_preparada.setString(1, titol);
             sentencia_preparada.setObject(2, data_publicacio);
             sentencia_preparada.setString(3, edicio);
             sentencia_preparada.setInt(4, puntuacio);
             sentencia_preparada.setString(5, sinopsis);
             sentencia_preparada.setInt(6, num_pagines);
             sentencia_preparada.setString(7, nom_autor);
             sentencia_preparada.setString(8, nom_genere);
             sentencia_preparada.setString(9, nom_editorial);
             sentencia_preparada.setString(10, isbn);
             resultado = sentencia_preparada.executeUpdate();
             sentencia_preparada.close();
              
             conexio.close();
              
          }catch(Exception e){
              
             System.out.println(e);
          }
          if (resultado==1){
              modificarAutorPerLlibre(isbn, nom_autor);
              modificarAutorPerLlibre(isbn, nom_editorial);
              modificarAutorPerLlibre(isbn, nom_genere);
              return resultado;
          }else {
              return  resultado;
          } 
          
    }
    
    /**
     * Mètode per eliminar llibre de la BBDD
     * @param data isbn del llibre a eliminar
     * @return 1 si s'ha fet modificació i 0 si no
     */
    public static int eliminarLlibre(String []data){

        int resultado = 0;
        Integer id = Integer.parseInt(data[2]);
        String sentencia_guardar =("DELETE from llibres "
                    + "WHERE id_llibre="+id+"");
         
        try{
            conexio = ConexioBBDD.ConexioBBDD();
            sentencia_preparada = conexio.prepareStatement(sentencia_guardar);
            resultado = sentencia_preparada.executeUpdate();
            sentencia_preparada.close();
             
            conexio.close();
             
        }catch(Exception e){
             
            System.out.println(e);
        }
        return resultado;        
    }
    
    /**
     * Mètode que busca un llibre per el titol
     * @param data missatge enviat amb el client amb tots els paràmetres
     * @return StringJson
     * @throws java.sql.SQLException
     */
    public static String buscarLlibreTitol(String[]data) throws SQLException{
   

        try {
            conexio = ConexioBBDD.ConexioBBDD();
            statement = conexio.createStatement();
            resultSet = statement.executeQuery("select json_agg(t)::jsonb FROM (SELECT \n" +
            		"                                         llibres.id_llibre,\n" +
            		"                                         llibres.titol,\n" +
            		"                                         autors.nom_autor,  \n" +
            		"                                         editorials.nom_editorial, \n" +
            		"                                         llibrespereditorial.id_editorial, \n" +
            		"                                         llibres.isbn, \n" +
            		"                                          llibres.data_publicacio, \n" +
            		"                                         llibres.edicio, \n" +
            		"                                          llibres.puntuacio, \n" +
            		"                                          llibres.num_reserves, \n" +
            		"                                          llibres.sinopsis, \n" +
            		"                                          llibres.num_pagines, \n" +
            		"                                         llibres.reservat,    \n" +
            		"                                          generes.nom_genere\n" +
            		"                                        FROM \n" +
            		"                                          public.autors, \n" +
            		"                                          public.llibres, \n" +
            		"                                          public.llibrespereditorial, \n" +
            		"                                          public.editorials, \n" +
            		"                                          public.llibresperautor, \n" +
            		"                                          public.generes, \n" +
            		"                                         public.llibrespergenere \n" +
            		"                                        WHERE \n" +
            		"                                          autors.id_autor = llibresperautor.id_autor AND\n" +
            		"                                         llibres.id_llibre = llibrespereditorial.id_llibre AND\n" +
            		"                                          llibres.id_llibre = llibrespergenere.id_llibre AND\n" +
            		"                                          editorials.id_editorial = llibrespereditorial.id_editorial AND\n" +
            		"                                          llibresperautor.id_llibre = llibrespereditorial.id_llibre AND\n" +
            		"                                          llibrespergenere.id_genere = generes.id_genere AND\n" +
            		"					llibres.titol like '%"+data[2]+"%'" +
            		"                                          order by llibres.titol)t;");
            //rsMD = resultSet.getMetaData();

        }catch (SQLException ex) {
            System.err.println( ex.getMessage() );
        }
        String retorn = ResultSetToString(resultSet);
        return retorn;
    }
    
    /**
     *Mètode que busca un llibre per el autor
     * @param data missatge enviat amb el client amb tots els paràmetres
     * @return StringJson
     * @throws java.sql.SQLException
     */
    public static String buscarLlibreAutor(String [] data) throws SQLException{

        //String resultSetAsString = "";
        try {
            conexio = ConexioBBDD.ConexioBBDD();
            statement = conexio.createStatement();
            resultSet = statement.executeQuery("select json_agg(t)::jsonb FROM (SELECT \n" +
            		"                                         llibres.id_llibre,\n" +
"                                         llibres.titol,\n" +
"                                         autors.nom_autor,  \n" +
"                                         editorials.nom_editorial, \n" +
"                                         llibrespereditorial.id_editorial, \n" +
"                                         llibres.isbn, \n" +
"                                          llibres.data_publicacio, \n" +
"                                         llibres.edicio, \n" +
"                                          llibres.puntuacio, \n" +
"                                          llibres.num_reserves, \n" +
"                                          llibres.sinopsis, \n" +
"                                          llibres.num_pagines, \n" +
"                                         llibres.reservat,    \n" +
"                                          generes.nom_genere\n" +
"                                        FROM \n" +
"                                          public.autors, \n" +
"                                          public.llibres, \n" +
"                                          public.llibrespereditorial, \n" +
"                                          public.editorials, \n" +
"                                          public.llibresperautor, \n" +
"                                          public.generes, \n" +
"                                         public.llibrespergenere \n" +
"                                        WHERE \n" +
"                                          autors.id_autor = llibresperautor.id_autor AND\n" +
"                                         llibres.id_llibre = llibrespereditorial.id_llibre AND\n" +
"                                          llibres.id_llibre = llibrespergenere.id_llibre AND\n" +
"                                          editorials.id_editorial = llibrespereditorial.id_editorial AND\n" +
"                                          llibresperautor.id_llibre = llibrespereditorial.id_llibre AND\n" +
"                                          llibrespergenere.id_genere = generes.id_genere AND\n" +
"					autors.nom_autor like '%"+data[2]+"%'" +
"                                          order by llibres.titol)t;");


        }catch (SQLException ex) {
            System.err.println( ex.getMessage() );
        }
        String retorn = ResultSetToString(resultSet);
        return retorn;
    }
        
    /**
     * Mètode que busca un llibre per el isbn
     * @param data missatge enviat amb el client amb tots els paràmetres
     * @return StringJson
     * @throws java.sql.SQLException
     */
    public static String buscarLlibreIsbn(String []data) throws SQLException{

        //String resultSetAsString = "";
        try {
            conexio = ConexioBBDD.ConexioBBDD();
            statement = conexio.createStatement();
            resultSet = statement.executeQuery("select json_agg(t)::jsonb FROM (SELECT \n" +
            		"                                         llibres.id_llibre,\n" +
"                                         llibres.titol,\n" +
"                                         autors.nom_autor,  \n" +
"                                         editorials.nom_editorial, \n" +
"                                         llibrespereditorial.id_editorial, \n" +
"                                         llibres.isbn, \n" +
"                                          llibres.data_publicacio, \n" +
"                                         llibres.edicio, \n" +
"                                          llibres.puntuacio, \n" +
"                                          llibres.num_reserves, \n" +
"                                          llibres.sinopsis, \n" +
"                                          llibres.num_pagines, \n" +
"                                         llibres.reservat,    \n" +
"                                          generes.nom_genere\n" +
"                                        FROM \n" +
"                                          public.autors, \n" +
"                                          public.llibres, \n" +
"                                          public.llibrespereditorial, \n" +
"                                          public.editorials, \n" +
"                                          public.llibresperautor, \n" +
"                                          public.generes, \n" +
"                                         public.llibrespergenere \n" +
"                                        WHERE \n" +
"                                          autors.id_autor = llibresperautor.id_autor AND\n" +
"                                         llibres.id_llibre = llibrespereditorial.id_llibre AND\n" +
"                                          llibres.id_llibre = llibrespergenere.id_llibre AND\n" +
"                                          editorials.id_editorial = llibrespereditorial.id_editorial AND\n" +
"                                          llibresperautor.id_llibre = llibrespereditorial.id_llibre AND\n" +
"                                          llibrespergenere.id_genere = generes.id_genere AND" +
"					llibres.isbn = '" +data[2]+ "'" +
"                                          order by llibres.titol)t;");


        }catch (SQLException ex) {
            System.err.println( ex.getMessage() );
        }
       String retorn = ResultSetToString(resultSet);
        return retorn;
    }
    
    /**
     * Mètode que busca un llibre per la editorial
     * @param data missatge enviat amb el client amb tots els paràmetres
     * @return StingJson
     * @throws java.sql.SQLException
     */
    public static String buscarLlibreEditorial(String []data) throws SQLException{

        //String resultSetAsString = "";
        try {
            conexio = ConexioBBDD.ConexioBBDD();
            statement = conexio.createStatement();
            resultSet = statement.executeQuery("select json_agg(t)::jsonb FROM (SELECT " +
            		"                                         llibres.id_llibre,\n" +
                                    "  llibres.titol, " +
                                    "  autors.nom_autor, " +
                                    "  editorials.nom_editorial, " +
                                    "  llibrespereditorial.id_llibre, " +
                                    "  llibres.isbn, " +
                                    "  llibres.data_publicacio, " +
                                    "  llibres.edicio, " +
                                    "  llibres.puntuacio, " +
                                    "  llibres.num_reserves, " +
                                    "  llibres.sinopsis, " +
                                    "  llibres.num_pagines, " +
                                    "  llibres.reservat," +                    
                                    "  generes.nom_genere" +
                                    "  FROM " +
                                    "  public.autors, " +
                                    "  public.llibres, " +
                                    "  public.llibrespereditorial," +
                                    "  public.editorials, " +
                                    "  public.llibresperautor, " +
                                    "  public.generes, " +
                                    "  public.llibrespergenere" +
                                    "  WHERE " +
                                    "  autors.id_autor = llibresperautor.id_autor AND" +
                                    "  llibres.id_llibre = llibrespereditorial.id_llibre AND" +
                                    "  llibres.id_llibre = llibrespergenere.id_llibre AND" +
                                    "  editorials.id_editorial = llibrespereditorial.id_editorial AND" +
                                    "  llibresperautor.id_llibre = llibrespereditorial.id_llibre AND" +
                                    "  llibrespergenere.id_genere = generes.id_genere AND" +
                                    "  editorials.nom_editorial like '%"+data[2]+"%'" +
                                    "  order by llibres.titol )t;");


        }catch (SQLException ex) {
            System.err.println( ex.getMessage() );
        }
       //return resultSetAsString;
       String retorn = ResultSetToString(resultSet);
        return retorn;
    }
    
    /**
     *Mètode que busca un llibre per el gènere
     * @param data missatge enviat amb el client amb tots els paràmetres
     * @return 
     * @throws java.sql.SQLException 
     */
    public static String buscarLlibreGenere(String []data) throws SQLException{

        try {
            conexio = ConexioBBDD.ConexioBBDD();
            statement = conexio.createStatement();
            resultSet = statement.executeQuery("select json_agg(t)::jsonb FROM (SELECT \n" +
            		"                                         llibres.id_llibre,\n" +
"                                         llibres.titol,\n" +
"                                         autors.nom_autor,  \n" +
"                                         editorials.nom_editorial, \n" +
"                                         llibrespereditorial.id_editorial, \n" +
"                                         llibres.isbn, \n" +
"                                          llibres.data_publicacio, \n" +
"                                         llibres.edicio, \n" +
"                                          llibres.puntuacio, \n" +
"                                          llibres.num_reserves, \n" +
"                                          llibres.sinopsis, \n" +
"                                          llibres.num_pagines, \n" +
"                                         llibres.reservat,    \n" +
"                                          generes.nom_genere\n" +
"                                        FROM \n" +
"                                          public.autors, \n" +
"                                          public.llibres, \n" +
"                                          public.llibrespereditorial, \n" +
"                                          public.editorials, \n" +
"                                          public.llibresperautor, \n" +
"                                          public.generes, \n" +
"                                         public.llibrespergenere \n" +
"                                        WHERE \n" +
"                                          autors.id_autor = llibresperautor.id_autor AND\n" +
"                                         llibres.id_llibre = llibrespereditorial.id_llibre AND\n" +
"                                          llibres.id_llibre = llibrespergenere.id_llibre AND\n" +
"                                          editorials.id_editorial = llibrespereditorial.id_editorial AND\n" +
"                                          llibresperautor.id_llibre = llibrespereditorial.id_llibre AND\n" +
"                                          llibrespergenere.id_genere = generes.id_genere AND\n" +
"					generes.nom_genere like '%"+data[2]+"%'\n" +
"                                          order by llibres.titol)t;");

        }catch (SQLException ex) {
            System.err.println( ex.getMessage() );
        }
       //return resultSetAsString;
       String retorn = ResultSetToString(resultSet);
        return retorn;
    }
    
        /**
     * Mètode que mostra llista editorials per pàrametre de busqueda
     * @param data missatge enviat amb el client amb tots els paràmetres
     * @return StringJson
     * @throws SQLException
     */
    public static String consultaEditorial(String []data) throws SQLException{
        String retorn= "";
        try {
            conexio = ConexioBBDD.ConexioBBDD();
            statement = conexio.createStatement();
            resultSet = statement.executeQuery("select json_agg(t)::jsonb "
                    + "FROM (SELECT  id_editorial, nom_editorial" +
                    " FROM editorials WHERE nom_editorial like'%" +data[2]+ 
                    "%') t;");                      
        }catch (SQLException e) {
            System.out.println(e);
        }
        retorn = ResultSetToString(resultSet);
        return retorn;
    }
    
        
    /**
     * Mètode que s'utilitzarà per insertar usuari en BBDD, rep  un String amb 
     * tots els paràmetres que demanarà la BBDD
     * @param data missatge enviat amb el client amb tots els paràmetres
     * @return int 1 o 0
     */
    public static int guardarLlibre(String[] data){

        LocalDate data_publicacio = null;
        int resultado = 0;
        String titol = data[2];
        String nom_autor = data[4];
        String nom_genere = data[5];
        String nom_editorial = data[6];
        int puntuacio = Integer.parseInt(data[7]);
        String isbn = data[8];
        String edicio = data[9];
        data_publicacio= LocalDate.parse(data[10]); 
        int num_pagines = Integer.parseInt(data[11]);
        String sinopsis = data[13];
        
        
        String sentencia_guardar =("insert into llibres "
        		+ "(id_llibre, isbn, titol, data_publicacio, edicio, puntuacio, sinopsis, num_pagines, id_autor, id_genere, id_editorial)"+
        		"values (default,?,?,?,?,?,?,?,"
        		+ "(select id_autor from autors where nom_autor like ?),"
        		+ "(select id_genere from generes where generes.nom_genere like ?),"
        		+ "(select id_editorial from editorials where editorials.nom_editorial like ?))");
       
         try{
            conexio = ConexioBBDD.ConexioBBDD();
            sentencia_preparada = conexio.prepareStatement(sentencia_guardar);
            sentencia_preparada.setString(1, isbn);
            sentencia_preparada.setString(2, titol);
            sentencia_preparada.setObject(3, data_publicacio);
            sentencia_preparada.setString(4, edicio);
            sentencia_preparada.setInt(5, puntuacio);
            sentencia_preparada.setString(6, sinopsis);
            sentencia_preparada.setInt(7, num_pagines);
            sentencia_preparada.setString(8, nom_autor);
            sentencia_preparada.setString(9, nom_genere);
            sentencia_preparada.setString(10, nom_editorial);
            resultado = sentencia_preparada.executeUpdate();
            sentencia_preparada.close();
             
            conexio.close();
             
         }catch(Exception e){
             
            System.out.println(e);
         }
         if (resultado==1){
             afegirAutorPerLlibre(isbn, nom_autor);
             afegirEditorialPerLlibre(isbn, nom_editorial);
             afegirGenerePerLlibre(isbn, nom_genere);
             return resultado;
         }else {
             return  resultado;
         }                           
    }
    
    private static void afegirAutorPerLlibre(String isbn, String nom_autor) {
    	
    	String sentencia_guardar = "insert into llibresperautor (id_llibre, id_autor) values "
         		+ "((select id_llibre from llibres where isbn like ?),(select id_autor from autors where nom_autor like ?))";
    	 try{
    		 conexio = ConexioBBDD.ConexioBBDD();
             sentencia_preparada = conexio.prepareStatement(sentencia_guardar);
             sentencia_preparada.setString(1, isbn);
             sentencia_preparada.setString(2, nom_autor);
         
             sentencia_preparada.executeUpdate();
             sentencia_preparada.close();
              
             conexio.close();
         }catch(Exception e){             
             System.out.println(e);
         } 
		
	}
    
private static void afegirGenerePerLlibre(String isbn, String nom_genere) {
    	
    	String sentencia_guardar = "insert into llibrespergenere (id_llibre, id_genere) values "
         		+ "((select id_llibre from llibres where isbn like ?),(select id_genere from generes where nom_genere like ?))";
    	 try{
    		 conexio = ConexioBBDD.ConexioBBDD();
             sentencia_preparada = conexio.prepareStatement(sentencia_guardar);
             sentencia_preparada.setString(1, isbn);
             sentencia_preparada.setString(2, nom_genere);
         
             sentencia_preparada.executeUpdate();
             sentencia_preparada.close();
              
             conexio.close();
         }catch(Exception e){             
             System.out.println(e);
         } 
		
	}

private static void afegirEditorialPerLlibre(String isbn, String nom_editorial) {
	
	String sentencia_guardar = "insert into llibrespereditorial (id_llibre, id_editorial) values "
     		+ "((select id_llibre from llibres where isbn like ?),(select id_editorial from editorials where nom_editorial like ?))";
	 try{
		 conexio = ConexioBBDD.ConexioBBDD();
         sentencia_preparada = conexio.prepareStatement(sentencia_guardar);
         sentencia_preparada.setString(1, isbn);
         sentencia_preparada.setString(2, nom_editorial);
     
         sentencia_preparada.executeUpdate();
         sentencia_preparada.close();
          
         conexio.close();
     }catch(Exception e){             
         System.out.println(e);
     } 
	
}

private static void modificarAutorPerLlibre(String isbn, String nom_autor) {
	
	String sentencia_guardar = "update llibresperautor set id_llibre = (select id_llibre from llibres where isbn like ?),"
			+ " id_autor = (select id_autor from autors where nom_autor like ?))";
	 try{
		 conexio = ConexioBBDD.ConexioBBDD();
         sentencia_preparada = conexio.prepareStatement(sentencia_guardar);
         sentencia_preparada.setString(1, isbn);
         sentencia_preparada.setString(2, nom_autor);
     
         sentencia_preparada.executeUpdate();
         sentencia_preparada.close();
          
         conexio.close();
     }catch(Exception e){             
         System.out.println(e);
     } 
	
}

private static void modificarGenerePerLlibre(String isbn, String nom_genere) {
	
	String sentencia_guardar = "update llibrespergenere set id_llibre = (select id_llibre from llibres where isbn like ?),"
			+ " id_genere = (select id_genere from generes where nom_genere like ?))";
	 try{
		 conexio = ConexioBBDD.ConexioBBDD();
         sentencia_preparada = conexio.prepareStatement(sentencia_guardar);
         sentencia_preparada.setString(1, isbn);
         sentencia_preparada.setString(2, nom_genere);
     
         sentencia_preparada.executeUpdate();
         sentencia_preparada.close();
          
         conexio.close();
     }catch(Exception e){             
         System.out.println(e);
     } 
	
}

private static void modificarEditorialPerLlibre(String isbn, String nom_editorial) {

	String sentencia_guardar = "update llibrespereditorial set id_llibre = (select id_llibre from llibres where isbn like ?),"
			+ " id_editorial = (select id_editorial from editorials where nom_editorial like ?))";
 try{
	 conexio = ConexioBBDD.ConexioBBDD();
     sentencia_preparada = conexio.prepareStatement(sentencia_guardar);
     sentencia_preparada.setString(1, isbn);
     sentencia_preparada.setString(2, nom_editorial);
 
     sentencia_preparada.executeUpdate();
     sentencia_preparada.close();
      
     conexio.close();
 }catch(Exception e){             
     System.out.println(e);
 } 

}

	/**
     * Mètode que torna llistat de tots el llibres
     * @return String Json
     * @throws SQLException
     */
    public static String LlistarTotsllibres() throws SQLException{
        
        try {
            conexio = ConexioBBDD.ConexioBBDD();
            statement = conexio.createStatement();
            resultSet = statement.executeQuery("select json_agg(t)::jsonb FROM (SELECT \n" +
"                                         llibres.titol,\n" +
	"										llibres.id_llibre,  \n" +
"                                         autors.nom_autor,  \n" +
"                                         editorials.nom_editorial, \n" +
"                                         llibrespereditorial.id_editorial, \n" +
"                                         llibres.isbn, \n" +
"                                          llibres.data_publicacio, \n" +
"                                         llibres.edicio, \n" +
"                                          llibres.puntuacio, \n" +
"                                          llibres.num_reserves, \n" +
"                                          llibres.sinopsis, \n" +
"                                          llibres.num_pagines, \n" +
"                                         llibres.reservat,    \n" +
"                                          generes.nom_genere\n" +
"                                        FROM \n" +
"                                          public.autors, \n" +
"                                          public.llibres, \n" +
"                                          public.llibrespereditorial, \n" +
"                                          public.editorials, \n" +
"                                          public.llibresperautor, \n" +
"                                          public.generes, \n" +
"                                         public.llibrespergenere \n" +
"                                        WHERE \n" +
"                                          autors.id_autor = llibresperautor.id_autor AND\n" +
"                                         llibres.id_llibre = llibrespereditorial.id_llibre AND\n" +
"                                          llibres.id_llibre = llibrespergenere.id_llibre AND\n" +
"                                          editorials.id_editorial = llibrespereditorial.id_editorial AND\n" +
"                                          llibresperautor.id_llibre = llibrespereditorial.id_llibre AND\n" +
"                                          llibrespergenere.id_genere = generes.id_genere \n" +
"                                          order by llibres.titol)t;");
            //rsMD = resultSet.getMetaData();
            
        }catch (SQLException ex) {
            System.err.println( ex.getMessage() );
        }
        String retorn = ResultSetToString(resultSet);
        return retorn;
    }
    
    /**
     * Mètode que fara una reserva d'un llibre
     * @param data missatge enviat amb el client amb tots els paràmetres
     * @return int 1 o 0
     */
    public static int ferReserva(String []data){

        int resultado = 0;
           
        int id_usuari = Integer.parseInt(data[2]);
        int id_llibre = Integer.parseInt(data[3]);
        
        String sentencia_guardar =("INSERT INTO reserves(id_usuari, " 
                + "id_llibre, data_reserva) " 
                + "values(?,?,?)");
         
        try{
            conexio = ConexioBBDD.ConexioBBDD();
            sentencia_preparada = conexio.prepareStatement(sentencia_guardar);
            sentencia_preparada.setInt(1, id_usuari);
            sentencia_preparada.setInt(2, id_llibre);
            sentencia_preparada.setDate(3, new Date(new java.util.Date().getTime()));
            resultado = sentencia_preparada.executeUpdate();
            sentencia_preparada.close();
             
            conexio.close();
             
        }catch(Exception e){             
            System.out.println(e);
        }  
        if (resultado==1){
            afegirReservaTaulaLlibres(id_llibre);
            return resultado;
        }else {
            return  resultado;
        }                      
    }
    
    /**
     *
     * @param isbn
     */
    public static  void afegirReservaTaulaLlibres(int id_llibre){                
         
        try{
            conexio = ConexioBBDD.ConexioBBDD();
            statement = conexio.createStatement();
            resultSet = statement.executeQuery("update llibres set num_reserves = num_reserves+1, reservat = true where id_llibre = '"+id_llibre+"'");
            //rsMD = resultSet.getMetaData();
             
        }catch(Exception e){             
            System.out.println(e);
        } 
    }
    
    /**
     * Mètode que retorna llista de autors per el nom
     * @param data missatge enviat amb el client amb tots els paràmetres
     * @return StringJson
     * @throws SQLException
     */
    public static String consultaAutor(String []data) throws SQLException{

        try {
            conexio = ConexioBBDD.ConexioBBDD();
            statement = conexio.createStatement();
            resultSet = statement.executeQuery("select json_agg(t)::jsonb FROM (SELECT * FROM autors WHERE "
                    + "nom_autor like '%" +data[2]+ "%'ORDER BY nom_autor)t");
            //rsMD = resultSet.getMetaData();

        }catch (SQLException ex) {
            System.err.println( ex.getMessage() );
        }
        String retorn = ResultSetToString(resultSet);
        return retorn;
    }

	public static String LlistarTotsAutors() throws SQLException {
		try {
            conexio = ConexioBBDD.ConexioBBDD();
            statement = conexio.createStatement();
            resultSet = statement.executeQuery("select json_agg(t)::jsonb from (select nom_autor from autors)t");

        }catch (SQLException ex) {
            System.err.println( ex.getMessage() );
        }
        String retorn = ResultSetToString(resultSet);
        return retorn;
	}

	public static String LlistarTotsGeneres() throws SQLException {
		try {
            conexio = ConexioBBDD.ConexioBBDD();
            statement = conexio.createStatement();
            resultSet = statement.executeQuery("select json_agg(t)::jsonb from (select nom_genere from generes)t");

        }catch (SQLException ex) {
            System.err.println( ex.getMessage() );
        }
        String retorn = ResultSetToString(resultSet);
        return retorn;
	}

	public static String LlistarTotsEditorials() throws SQLException {
		try {
            conexio = ConexioBBDD.ConexioBBDD();
            statement = conexio.createStatement();
            resultSet = statement.executeQuery("select json_agg(t)::jsonb from (select nom_editorial from editorials)t");

        }catch (SQLException ex) {
            System.err.println( ex.getMessage() );
        }
        String retorn = ResultSetToString(resultSet);
        return retorn;
	}

	public static int GuardarGenere(String[] data) {
		 int resultado = 0;
         
	        String nomGenere = data[2];
	        
	        String sentencia_guardar =("INSERT INTO generes(nom_genere) " 
	                + "values(?)");
	         
	        try{
	            conexio = ConexioBBDD.ConexioBBDD();
	            sentencia_preparada = conexio.prepareStatement(sentencia_guardar);
	            sentencia_preparada.setString(1, nomGenere);
	            resultado = sentencia_preparada.executeUpdate();
	            sentencia_preparada.close();
	             
	            conexio.close();
	             
	        }catch(Exception e){             
	            System.out.println(e);
	        }  
	       return resultado;
	}
	public static int GuardarEditorial(String[] data) {
		 int resultado = 0;
        
	        String nomEditorial = data[2];
	        
	        String sentencia_guardar =("INSERT INTO editorials(nom_editorial) " 
	                + "values(?)");
	         
	        try{
	            conexio = ConexioBBDD.ConexioBBDD();
	            sentencia_preparada = conexio.prepareStatement(sentencia_guardar);
	            sentencia_preparada.setString(1, nomEditorial);
	            resultado = sentencia_preparada.executeUpdate();
	            sentencia_preparada.close();
	             
	            conexio.close();
	             
	        }catch(Exception e){             
	            System.out.println(e);
	        }  
	       return resultado;
	}
	
	
	public static int GuardarAutor(String[] data) {
		 int resultado = 0;
        
	        String nomAutor = data[2];
	        
	        String sentencia_guardar =("INSERT INTO autors(nom_autor) " 
	                + "values(?)");
	         
	        try{
	            conexio = ConexioBBDD.ConexioBBDD();
	            sentencia_preparada = conexio.prepareStatement(sentencia_guardar);
	            sentencia_preparada.setString(1, nomAutor);
	            resultado = sentencia_preparada.executeUpdate();
	            sentencia_preparada.close();
	             
	            conexio.close();
	             
	        }catch(Exception e){             
	            System.out.println(e);
	        }  
	       return resultado;
	}

	public static String ConsultaPrestecs() throws SQLException {
		try {
            conexio = ConexioBBDD.ConexioBBDD();
            statement = conexio.createStatement();
            resultSet = statement.executeQuery("select json_agg(t)::jsonb from "
            		+ "(select p.id_prestec, l.titol, u.nom_usuari, p.data_inici, p.data_final, p.num_renovacio"
            		+ "  from prestecs p, llibres l, usuaris u "
            		+ "where p.id_llibre = l.id_llibre and p.id_usuari = u.id_usuari)t");

        }catch (SQLException ex) {
            System.err.println( ex.getMessage() );
        }
        String retorn = ResultSetToString(resultSet);
        return retorn;
	}

	public static String buscarUsuariActiu(String[] dada) throws SQLException {
		 try {
	            conexio = ConexioBBDD.ConexioBBDD();
	            statement = conexio.createStatement();
	            resultSet = statement.executeQuery("select json_agg(t)::jsonb FROM (SELECT * FROM usuaris WHERE "
	                    + "actiu = '" +dada[2]+ "' ORDER BY cognoms_usuari)t");
	            //rsMD = resultSet.getMetaData();

	        }catch (SQLException ex) {
	            System.err.println( ex.getMessage() );
	        }
	        String retorn = ResultSetToString(resultSet);
	        return retorn;
	}

	public static String buscarUsuariDniNie(String[] dada) throws SQLException {
		 try {
	            conexio = ConexioBBDD.ConexioBBDD();
	            statement = conexio.createStatement();
	            resultSet = statement.executeQuery("select json_agg(t)::jsonb FROM (SELECT * FROM usuaris WHERE "
	                    + "dni_nie = '" +dada[2]+ "' and tipus_usuari like 'usuari' ORDER BY cognoms_usuari)t");
	            //rsMD = resultSet.getMetaData();

	        }catch (SQLException ex) {
	            System.err.println( ex.getMessage() );
	        }
	        String retorn = ResultSetToString(resultSet);
	        return retorn;
	}

	public static String buscarAdministradorNom(String[] dada) throws SQLException {
		 try {
	            conexio = ConexioBBDD.ConexioBBDD();
	            statement = conexio.createStatement();
	            resultSet = statement.executeQuery("select json_agg(t)::jsonb FROM (SELECT * FROM usuaris WHERE "
	                    + "nom_usuari = '" +dada[2]+ "' and tipus_usuari like 'administrador' ORDER BY cognoms_usuari)t");
	            //rsMD = resultSet.getMetaData();

	        }catch (SQLException ex) {
	            System.err.println( ex.getMessage() );
	        }
	        String retorn = ResultSetToString(resultSet);
	        return retorn;
	}

	public static String buscarAdministradorCarnet(String[] dada) throws SQLException {
		try {
            conexio = ConexioBBDD.ConexioBBDD();
            statement = conexio.createStatement();
            resultSet = statement.executeQuery("select json_agg(t)::jsonb FROM (SELECT * FROM usuaris WHERE "
                    + "carnet = '" +dada[2]+ "' and tipus_usuari like 'administrador' ORDER BY cognoms_usuari)t");
            //rsMD = resultSet.getMetaData();

        }catch (SQLException ex) {
            System.err.println( ex.getMessage() );
        }
        String retorn = ResultSetToString(resultSet);
        return retorn;
	}

	public static String buscarAdministradorDniNie(String[] dada) throws SQLException {
		try {
            conexio = ConexioBBDD.ConexioBBDD();
            statement = conexio.createStatement();
            resultSet = statement.executeQuery("select json_agg(t)::jsonb FROM (SELECT * FROM usuaris WHERE "
                    + "dni_nie = '" +dada[2]+ "' and tipus_usuari like 'administrador' ORDER BY cognoms_usuari)t");
            //rsMD = resultSet.getMetaData();

        }catch (SQLException ex) {
            System.err.println( ex.getMessage() );
        }
        String retorn = ResultSetToString(resultSet);
        return retorn;
	}

	public static String buscarUsuariTotsNom(String[] dada) throws SQLException {
		try {
            conexio = ConexioBBDD.ConexioBBDD();
            statement = conexio.createStatement();
            resultSet = statement.executeQuery("select json_agg(t)::jsonb FROM (SELECT * FROM usuaris WHERE "
                    + "nom_usuari = '" +dada[2]+ "' ORDER BY cognoms_usuari)t");
            //rsMD = resultSet.getMetaData();

        }catch (SQLException ex) {
            System.err.println( ex.getMessage() );
        }
        String retorn = ResultSetToString(resultSet);
        return retorn;
	}

	public static String buscarUsuariTotsCarnet(String[] dada) throws SQLException {
		try {
            conexio = ConexioBBDD.ConexioBBDD();
            statement = conexio.createStatement();
            resultSet = statement.executeQuery("select json_agg(t)::jsonb FROM (SELECT * FROM usuaris WHERE "
                    + "carnet = '" +dada[2]+ "' ORDER BY cognoms_usuari)t");
            //rsMD = resultSet.getMetaData();

        }catch (SQLException ex) {
            System.err.println( ex.getMessage() );
        }
        String retorn = ResultSetToString(resultSet);
        return retorn;
	}

	public static String buscarUsuariTotsDniNie(String[] dada) throws SQLException {
		try {
            conexio = ConexioBBDD.ConexioBBDD();
            statement = conexio.createStatement();
            resultSet = statement.executeQuery("select json_agg(t)::jsonb FROM (SELECT * FROM usuaris WHERE "
                    + "dni_nie = '" +dada[2]+ "' ORDER BY cognoms_usuari)t");
            //rsMD = resultSet.getMetaData();

        }catch (SQLException ex) {
            System.err.println( ex.getMessage() );
        }
        String retorn = ResultSetToString(resultSet);
        return retorn;
	}

	public static String ConsultaPrestecsUsuari(String[] dada) throws SQLException{
		try {
		conexio = ConexioBBDD.ConexioBBDD();
        statement = conexio.createStatement();

        resultSet = statement.executeQuery("select json_agg(t)::jsonb from "
        		+ "(select p.id_prestec, l.titol, u.nom_usuari, p.data_inici, p.data_final, p.num_renovacio"
        		+ "  from prestecs p, llibres l, usuaris u "
        		+ "where p.id_llibre = l.id_llibre and p.id_usuari = u.id_usuari and u.id_usuari = '" +dada[2]+ "' order by data_final)t");

    }catch (SQLException ex) {
        System.err.println( ex.getMessage() );
    }
    String retorn = ResultSetToString(resultSet);
    return retorn;
	}

	public static int ConsultaLlogarLlibre(String[] dada) {
		 int resultado = 0;
         
	        int id_usuari = Integer.parseInt(dada[2]);
	        int id_llibre = Integer.parseInt(dada[3]);
	        Date today = new Date(Calendar.getInstance().getTime().getTime());
	        
	        String sentencia_guardar =("INSERT INTO prestecs(id_llibre, " 
	                + "id_usuari, data_inici, data_final) " 
	                + "values(?,?,?,?)");
	         
	        try{
	            conexio = ConexioBBDD.ConexioBBDD();
	            sentencia_preparada = conexio.prepareStatement(sentencia_guardar);
	            sentencia_preparada.setInt(1, id_llibre);
	            sentencia_preparada.setInt(2, id_usuari);
	            sentencia_preparada.setDate(3, today);
	            sentencia_preparada.setDate(4, new Date(addDays(today, 14).getTime()));
	            resultado = sentencia_preparada.executeUpdate();
	            sentencia_preparada.close();
	             
	            conexio.close();
	             
	        }catch(Exception e){             
	            System.out.println(e);
	        }  
	        if (resultado==1){
	            afegirReservaTaulaLlibres(id_llibre);
	            return resultado;
	        }else {
	            return  resultado;
	        }                      
	}
	
	/**
	 * Suma dias a una data
	 * @param data data a la que es vol sumar els dies
	 * @param dies dies que es volen sumar 
	 * @return
	 */
	public static java.util.Date addDays(Date data, int dies) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(data);
		cal.add(Calendar.DATE, dies);
		return cal.getTime();
		}
	
	
	
	
	
	
	
	
	
	
	
	
}