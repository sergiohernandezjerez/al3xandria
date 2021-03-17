/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al3xandria.mockserver.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.util.Date;

import al3xandria.mockserver.commands.Parser;
import al3xandria.mockserver.commands.SocketCommand;
import al3xandria.mockserver.controller.ResponseMap;

/**
 * Class that implements MockServer with sockets
 * @author professor
 */
public class MockSocketsServer {
    
    private ResponseMap m;
    Parser parser;
    
    /**
     * Creates a MockServer
     * @param port port to listen
     * @param m ResponseMap to be used
     */
    public MockSocketsServer(int port, ResponseMap m) {
        this.m=m;
        this.parser=new Parser(){};
    }
    
    /**
     * Creates a MockServer
     * @param port port to listen
     * @param m ResponseMap to be used
     * @param parser Parser to be used
     */
    public MockSocketsServer(int port, ResponseMap m, Parser parser) {
        this.m=m;
        this.parser=parser;
    }    

    
    private  void replay(Socket client, String data){
        Thread t=new Thread(()->{
            try {
                answer(client, data);
            } catch (IOException e) {
                showIOErrorInformation(e);
            }
        });
        
        t.run();
    }
    
    /**
     * Launches server
     * @param port to be listened
     */
    public void run(int port)  {

        
        try {
            ServerSocket sk = new ServerSocket(port);
            
            
            while(true){
                Socket client = sk.accept();
                BufferedReader input = new BufferedReader(
                        new InputStreamReader(client.getInputStream()));

                String data = input.readLine();
                System.out.println(thisMoment()+" Received: "+data);
                replay(client,data);
                
            }
        } catch (IOException e) {
            showIOErrorInformation(e);
        }
    }
    
    private void answer (Socket client, String data) throws IOException{
        String textSent="";
        try{
            SocketCommand skc=parser.parse(data);
            textSent=m.respond(client, skc, parser);
            client.close();
            
        } catch(IOException e){
            showIOErrorInformation(e);
        } catch(Exception e){
            if(!client.isClosed()){
                textSent=m.sendBadRequest(client, parser);
                client.close();
            }
        }finally{
            if(textSent.length()!=0)
                System.out.println(thisMoment()+" Sent: "+textSent);
        }
    }
    private void showIOErrorInformation(Exception e){
        System.err.println("Input/Output error:"+e.getMessage());
    }
    
    private String thisMoment(){
        return DateFormat.getDateTimeInstance().format(new Date());
    }
     
}
 
