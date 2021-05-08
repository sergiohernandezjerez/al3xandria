/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al3xandria.pedroServer.Cliente;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author PedroN
 */
public class cliente2 {
    
    final String HOST = "127.0.0.1";
    final int PUERTO = 5000;
    static DataInputStream is;
    DataOutputStream out;
    BufferedReader br;
    BufferedWriter bw;
    final ResultSet resultSet = null;
    

    
    public static void main (String[] args) throws SQLException{
        
            Socket sc ;


            String enviaDades [] = {"cS12234435,modificar_usuari,5014,Marc,Sola,"
                    + "345467466T,felipe@felipe.com,felipe,carrer cinc,"
                    + "05664,felipe@felipe.com,felipe,carrer cinc,05664,"
                    + "Barcelona,Barcelona,Catalunya,12344556,usuari,true",                       
                "cS12234435,insercio_usuari,Felipe,Recio,3455675545T,"
                    + "felipe@felipe.com,felipe,carrer pentar,05664,"
                    + "Barcelona,Barcelona,Catalunya,12344556,usuari",
                "cS23343556,insercio_llibre,017665584,Windows ,"
                    + "2007-02-23,primera,w7,178",
                "cS12234435,borrar_usuari,5024",
                "cS234532525,eliminar_llibre,1534584",
                "cS233477654,insertar_editorial,Editorial InfoBasic",
                "cS223344565,insertar_autor, Maria Claro",
                };
            
            
            for (int i = 0; i < enviaDades.length; i++) {
            try {
                sc = new Socket("localhost", 5556);
                PrintWriter output = new PrintWriter(new OutputStreamWriter(sc.getOutputStream()), true);
                output.println(enviaDades[i]);
                BufferedReader input = new BufferedReader(new InputStreamReader(sc.getInputStream()));
                String data = input.readLine();

                System.out.println("--");
                System.out.println("Enviat al servidor: " + enviaDades[i]);
                System.out.println("Rebut del servidor: " + data);
                sc.close();
            } catch (Exception e) {
                System.err.println(e.getMessage());
                System.exit(1);
            }
        }
    }    
}

