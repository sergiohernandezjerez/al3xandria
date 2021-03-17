/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package al3xandria.mockserver.controller;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import al3xandria.mockserver.commands.CommandResponse;
import al3xandria.mockserver.commands.Parser;
import al3xandria.mockserver.commands.SocketCommand;

/**
 * "CommandResponse" map indexed by the string that identifies each order
 * @author professor
 */
public class ResponseMap {
    private Map <String,CommandResponse>m=new HashMap<>();
    
    /**
     * Default constructor
     */
    public ResponseMap() {
    }
    
    /**
     * Adds a CommandResponse to the map
     * @param r 
     */
    public void add(CommandResponse r){
         m.put(r.getOrder(),r);
    }
    
    /**
     * Adds a CommandResponse to the map. The CommandResponse is created from the parameters
     * @param order string that identifies the order
     * @param responses Responses returned by the server in response to the
     * command indicated by the first parameter. 
     */
    public void add(String order, Object[][] responses){
        add(new CommandResponse(order, responses));
    }
    
    /**
     * Returns true if the map contains responses for the order and false otherwise. 
     * @param order string that identifies the order
     * @return 
     */
    
    public boolean exists(String order){
        return m.containsKey(order);
    }
    
    /**
     * Erases, if any, responses for the order
     * @param order string that identifies the order 
     */    
    public void erase(String order){
        m.remove(order);
    }

    /**
     * Sends the answer to the client
     * @param client socket open with client
     * @param sc SocketCommand with the request
     * @param parser parser that must be used to make this answer
     * @return message sent
     * @throws IOException  if a network error occurs
     */
    
    public String respond(Socket client, SocketCommand sc, Parser parser) throws IOException{
        CommandResponse c=m.get(sc.getOrder());
        boolean ok=false;
        String messageSent="";
        
        if(c!=null){
            List <Object[]> responses=c.getResponses();
            if(responses!=null && !responses.isEmpty()){
                Object [] first=responses.get(0);
                if(first!=null){
                    messageSent=sendResponse(client, first, parser);
                    ok=true;
                    //responses.remove(0);
                }
            }
        }
        if(!ok){
            messageSent=sendBadRequest(client,parser);
        }
        
        return messageSent;
    }
    /**
     * send to client a "Bad Request" message
     * @param client socket open with the client
     * @param parser parser to convert response (array of objects) to a String
     * @return message sent
     * @throws IOException if a network error occurs
     */
    public String sendBadRequest(Socket client, Parser parser) throws IOException {
        return sendResponse(client,new Object[]{"400","Bad Request"}, parser);
    }
    
    /**
     * send to client a message 
     * @param client socket open with the client
     * @param results response (array of objects)
     * @param parser parser to convert response to a String
     * @return message sent
     * @throws IOException if a network error occurs
     */
    protected String sendResponse(Socket client, Object [] results, Parser parser) throws IOException {
        PrintWriter output = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);
        String textSent=parser.unparse(results);
        
        output.println(textSent);
        return textSent;
    }
}
