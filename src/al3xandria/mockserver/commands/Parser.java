/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package al3xandria.mockserver.commands;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * Parses a String as a SocketCommand and converts any object to a String
 * @author professor
 */
public interface Parser {

    /**
     * Parses a String as a SocketCommand.By default,  string format is Json.
     * If syntax error, a ClassCastException is thrown
     * @param toParse String to be parsed
     * @return SocketCommand represented by the string
     */
    
    default SocketCommand parse(String toParse){
        Gson gson = new Gson();
        try{        
            return gson.fromJson(toParse,SocketCommand.class);
        }catch(JsonSyntaxException e){
            throw new ClassCastException(e.getMessage());
        }
        
    }
    
    /**
     * Converts an array of objects to an String.
     * By default,  string format is Json.
     * @param o Object to be converted
     * @return String representing object
     */
    
    default String unparse(Object [] o){
        Gson gson = new Gson();
        
        return gson.toJson(o);
        
    }

}
