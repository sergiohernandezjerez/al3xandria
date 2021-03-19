/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al3xandria.mockserver.main;

import java.io.IOException;

import al3xandria.mockserver.controller.ResponseMap;
import al3xandria.mockserver.sockets.MockSocketsServer;


/**
 * Class containing program that uses MockServer
 * Uses default CommaSeparatedParser
 * @author professor
 */
public class MockSocketsServerMain {


    /**Program that uses MockServer
     * @param args the command line arguments: port number only
     * @throws java.io.IOException in case of network problem
     * @throws java.lang.InterruptedException in case of process interruption
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        //int port=Integer.parseInt(args[0]);
        int port=5555;
        
        ResponseMap map=new ResponseMap();
        
        fillMap(map);

        MockSocketsServer mss = new MockSocketsServer();
        
        mss.run(port);
       
    }
    /**
     * Method to fill the map with Responses
     * @param map map to be filled
     */    
    public static void fillMap(ResponseMap map){
        // Little example of use
        
        map.add("login@user.com",new Object[][]{
                new Object[]{0,14, false},  // call with user and password ok. Session code assigned = 14, is an user
        });

        map.add("login@admin.com",new Object[][]{
            new Object[]{0,15, true},  // call with user and password ok. Session code assigned = 14, is an administrator
           
    });
        map.add("login@fail.com",new Object[][]{
            new Object[]{1},  // call with user and password fail
           
    });
        
        map.add("logout",new Object[][]{
                new Object[]{0},  // logout ok
        });
        
        map.add("logoutFail",new Object[][]{
            new Object[]{1} // error
    });

        
    }
        
}
 
