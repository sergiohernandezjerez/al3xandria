/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al3xandria.pedroServer.ConnexioBBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.time.LocalDate;

/**
 *
 * @author PedroN
 * Classe on es guardan les funcions de consulta i
 * modificació de la BBDD local
 */
public class FuncionesBBDD {

    private static ConexioBBDD conexion = new ConexioBBDD();
    private static PreparedStatement sentencia_preparada;
    public static ResultSet resultado;
    /** Para enviar comandos SQL a la base de datos */
    private static Statement statement = null;
    public static ConexionBBDDRemota conexionRemota ;
    /** Para obtener los resultados de las consultas SQL de la base de datos */
    private static ResultSet resultSet = null;
    private static ResultSetMetaData rsMD = null;
    private static Connection conexio = null;
    
    
    
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
        
        String sentencia_guardar =("INSERT INTO usuaris(nom_usuari, " 
                + "cognoms_usuari, dni_nie, email, contrasenya, " 
                + "adreca, codi_postal, poblacio, provincia, pais, "
                + "telefon, tipus_usuari) "
                + "values(?,?,?,?,?,?,?,?,?,?,?,?)");
         
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
     * @param data
     * @return
     */
    public static int modificarUsuari(String []data){

        int resultado = 0;
        Integer carnet = Integer.parseInt(data[2]);
        String nom = data[3];
        String cognoms = data[4];
        String dni_nie = data[5];
        String email = data[6];
        String contrasenya = data[7]; 
        String adreca = data[8];
        String codiPostal = data[9];
        String poblacio = data[10]; 
        String provincia = data[11]; 
        String pais = data[12];
        String telefon = data[13];            
        String tipus_usuari = data[14];
        Boolean actiu = Boolean.parseBoolean(data[15]);
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
     * @param data
     * @return
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
     * @param data
     * @return
     */
    public static int modificarLlibre(String []data){

        int resultado = 0;
        LocalDate data_publicacio=null;
        Integer isbn = Integer.parseInt(data[2]);
        String titol = data[3];
        data_publicacio= LocalDate.parse(data[4]);
        String edicio = data[5];
        Integer puntuacio = Integer.parseInt(data[6]);
        Integer num_reserves = Integer.parseInt(data[7]); 
        String sinopsis = data[8];
        Integer num_pag = Integer.parseInt(data[9]);


        String sentencia_guardar =("UPDATE llibres set titol='"+titol+"',"
                    + "data_publicacio='"+data_publicacio+"', dni_nie='"+edicio+"',"
                    + "puntuacio='"+puntuacio+"', num_reserves='"+num_reserves+"',"
                    + "sinopsis='"+sinopsis+"',num_pagines='"+num_pag+"' "
                    + "WHERE isbn="+isbn+"");
         
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
    
    public static int eliminarLlibre(String []data){

        int resultado = 0;
        Integer isbn = Integer.parseInt(data[2]);
        String sentencia_guardar =("DELETE from llibres "
                    + "WHERE isbn="+isbn+"");
         
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
    
        
    public static Object buscarLlibreTitol(String[]data){
   
        //String resultSetAsString = "";
        try {
            conexio = ConexioBBDD.ConexioBBDD();
            statement = conexio.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM llibres WHERE "
                                            + "titol like '%" +data[2]+ "%'");
            rsMD = resultSet.getMetaData();
            
            
        /*StringBuilder builder = new StringBuilder();
        int columnCount = resultSet.getMetaData().getColumnCount();
        while (resultSet.next()) {
            for (int i = 0; i < columnCount;) {
                builder.append(resultSet.getString(i + 1));
                if (++i < columnCount) builder.append(",");
            }
        builder.append("\r\n");
        }
        resultSetAsString = builder.toString();*/
        }catch (SQLException ex) {
            System.err.println( ex.getMessage() );
        }
       return rsMD;
    }
    
        public static Object buscarLlibreAutor(String [] data){

        //String resultSetAsString = "";
        try {
            conexio = ConexioBBDD.ConexioBBDD();
            statement = conexio.createStatement();
            resultSet = statement.executeQuery("select au.nom_autor , l.titol" +
                                            "from llibresperautor as lp " +
                                            "join autors as au on au.id_autor=lp.id_autor" +
                                            "join llibres as l on l.id_llibre =lp.id_llibre" +
                                            "where au.nom_autor like '%"+data[2]+"%'");
            rsMD = resultSet.getMetaData();

        }catch (SQLException ex) {
            System.err.println( ex.getMessage() );
        }
       return rsMD;
    }
        
    public static Object buscarLlibreIsbn(String []data){

        //String resultSetAsString = "";
        try {
            conexio = ConexioBBDD.ConexioBBDD();
            statement = conexio.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM llibres WHERE "
                    + "isbn='" +data[2]+ "'");
            rsMD = resultSet.getMetaData();

        }catch (SQLException ex) {
            System.err.println( ex.getMessage() );
        }
       return rsMD;
    }
    
    public static Object buscarLlibreEditorial(String []data){

        //String resultSetAsString = "";
        try {
            conexio = ConexioBBDD.ConexioBBDD();
            statement = conexio.createStatement();
            resultSet = statement.executeQuery("SELECT " +
                                    "  llibrespereditorial.id_llibre, " +
                                    "  llibres.titol, " +
                                    "  autors.nom_autor, " +
                                    "  editorials.nom_editorial, " +
                                    "  llibrespereditorial.id_editorial, " +
                                    "  llibres.isbn, " +
                                    "  llibres.data_publicacio, " +
                                    "  llibres.edicio, " +
                                    "  llibres.puntuacio, " +
                                    "  llibres.num_reserves, " +
                                    "  llibres.sinopsis, " +
                                    "  llibres.num_pagines, " +
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
                                    "  order by   editorials.nom_editorial ;");
            rsMD = resultSet.getMetaData();

        }catch (SQLException ex) {
            System.err.println( ex.getMessage() );
        }
       //return resultSetAsString;
       return rsMD;
    }
    
        public static Object buscarLlibreGenere(String []data){

        //String resultSetAsString = "";
        try {
            conexio = ConexioBBDD.ConexioBBDD();
            statement = conexio.createStatement();
            resultSet = statement.executeQuery("SELECT " +
                                        "  llibrespereditorial.id_llibre, " +
                                        "  llibres.titol," +
                                        "  autors.nom_autor,  " +
                                        "  generes.nom_genere," +
                                        "  editorials.nom_editorial, " +
                                        "  llibrespereditorial.id_editorial, " +
                                        "  llibres.isbn, " +
                                        "  llibres.data_publicacio, " +
                                        "  llibres.edicio, " +
                                        "  llibres.puntuacio, " +
                                        "  llibres.num_reserves, " +
                                        "  llibres.sinopsis, " +
                                        "  llibres.num_pagines " +
                                        "FROM " +
                                        "  public.autors, " +
                                        "  public.llibres, " +
                                        "  public.llibrespereditorial, " +
                                        "  public.editorials, " +
                                        "  public.llibresperautor, " +
                                        "  public.generes, " +
                                        "  public.llibrespergenere " +
                                        "WHERE " +
                                        "  autors.id_autor = llibresperautor.id_autor AND" +
                                        "  llibres.id_llibre = llibrespereditorial.id_llibre AND" +
                                        "  llibres.id_llibre = llibrespergenere.id_llibre AND" +
                                        "  editorials.id_editorial = llibrespereditorial.id_editorial AND" +
                                        "  llibresperautor.id_llibre = llibrespereditorial.id_llibre AND" +
                                        "  llibrespergenere.id_genere = generes.id_genere AND" +
                                        "  generes.nom_genere like '%"+data[2]+"%'" +
                                        "  order by generes.nom_genere;");
            rsMD = resultSet.getMetaData();

        }catch (SQLException ex) {
            System.err.println( ex.getMessage() );
        }
       //return resultSetAsString;
       return rsMD;
    }
    
    public static Object LlistarTotsUsuaris(){
        
        try {
            conexio = ConexioBBDD.ConexioBBDD();
            statement = conexio.createStatement();
            resultSet = statement.executeQuery("select * from usuaris"                                                
                                                +"ORDER BY carnet");
            rsMD = resultSet.getMetaData();
            
        }catch (SQLException ex) {
            System.err.println( ex.getMessage() );
        }
        return  rsMD;
    }
    
    public static Object buscarUsuariNom(String []data){

        try {
            conexio = ConexioBBDD.ConexioBBDD();
            statement = conexio.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM usuaris WHERE "
                    + "nom_usuari like '%" +data[2]+ "%' ORDER BY cognoms_usuari");
            rsMD = resultSet.getMetaData();

        }catch (SQLException ex) {
            System.err.println( ex.getMessage() );
        }
       return rsMD;
    }
    
    public static Object buscarUsuariDNI(String []data){

        try {
            conexio = ConexioBBDD.ConexioBBDD();
            statement = conexio.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM usuaris WHERE "
                    + "dni_nie = '" +data[2]+ "' ORDER BY cognoms_usuari");
            rsMD = resultSet.getMetaData();

        }catch (SQLException ex) {
            System.err.println( ex.getMessage() );
        }
       return rsMD;
    }
    
    public static Object buscarUsuariTipus(String []data){

        try {
            conexio = ConexioBBDD.ConexioBBDD();
            statement = conexio.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM usuaris WHERE "
                    + "tipus_usuari = '" +data[2]+ "'ORDER BY cognoms_usuari");
            rsMD = resultSet.getMetaData();

        }catch (SQLException ex) {
            System.err.println( ex.getMessage() );
        }
       return rsMD;
    }
        
    /**
     * Mètode que s'utilitzarà per insertar usuari en BBDD, rep  un String amb 
     * tots els paràmetres que demanarà la BBDD
     * @param data
     * @return
     */
    public static int guardarLlibre(String[] data){

        LocalDate data_publicacio = null;
        int resultado = 0;
        Integer isbn = Integer.parseInt(data[2]);
        String titol = data[3];
        data_publicacio= LocalDate.parse(data[4]); 
        String edicio = data[5]; 
        String sinopsis = data[6];
        Integer num_pagines = Integer.parseInt(data[7]);
        
        
        String sentencia_guardar =("INSERT INTO llibres(isbn, " 
                + "titol, data_publicacio, edicio, " 
                + " sinopsis, num_pagines) "
                + "values(?,?,?,?,?,?)");
         
         try{
            conexio = ConexioBBDD.ConexioBBDD();
            sentencia_preparada = conexio.prepareStatement(sentencia_guardar);
            sentencia_preparada.setInt(1, isbn);
            sentencia_preparada.setString(2, titol);
            sentencia_preparada.setObject(3, data_publicacio);
            sentencia_preparada.setString(4, edicio);
            sentencia_preparada.setString(5, sinopsis);
            sentencia_preparada.setInt(6, num_pagines);
            resultado = sentencia_preparada.executeUpdate();
            sentencia_preparada.close();
             
            conexio.close();
             
         }catch(Exception e){
             
            System.out.println(e);
         }
         return resultado;        
    }
    
    public static Object LlistarTotsllibres(){
        
        try {
            conexio = ConexioBBDD.ConexioBBDD();
            statement = conexio.createStatement();
            resultSet = statement.executeQuery("select * from llibres"                                                
                                                +"ORDER BY data_publicacio");
            rsMD = resultSet.getMetaData();
            
        }catch (SQLException ex) {
            System.err.println( ex.getMessage() );
        }
        return  rsMD;
    }
    
    public static int ferReserva(String []data){

        int resultado = 0;
           
        Integer id_usuari = Integer.parseInt(data[2]);
        Integer id_llibre = Integer.parseInt(data[3]); 
        
        String sentencia_guardar =("INSERT INTO reserves(id_usuari, " 
                + "id_llibre) " 
                + "values(?,?)");
         
        try{
            conexio = ConexioBBDD.ConexioBBDD();
            sentencia_preparada = conexio.prepareStatement(sentencia_guardar);
            sentencia_preparada.setInt(1, id_usuari);
            sentencia_preparada.setInt(2, id_llibre);
            resultado = sentencia_preparada.executeUpdate();
            sentencia_preparada.close();
             
            conexio.close();
             
        }catch(Exception e){             
            System.out.println(e);
        }           
           
        return resultado;
    }
    
    public static Object consultaEditorial(String []data){
        
        try {
            conexio = ConexioBBDD.ConexioBBDD();
            statement = conexio.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM editorials WHERE "
                    + "nom_editorial like '%" +data[2]+ "%'ORDER BY nom_editorial");
            rsMD = resultSet.getMetaData();

        }catch (SQLException ex) {
            System.err.println( ex.getMessage() );
        }
       return rsMD;
    }
    
    public static Object consultaAutor(String []data){

        try {
            conexio = ConexioBBDD.ConexioBBDD();
            statement = conexio.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM autors WHERE "
                    + "nom_autor like '%" +data[2]+ "%'ORDER BY nom_autor");
            rsMD = resultSet.getMetaData();

        }catch (SQLException ex) {
            System.err.println( ex.getMessage() );
        }
       return rsMD;
    }
    
    
    
}
