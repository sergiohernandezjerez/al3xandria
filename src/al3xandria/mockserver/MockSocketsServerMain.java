/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al3xandria.mockserver;

import java.io.IOException;


/**
 * Class containing program that uses MockServer
 * Uses default CommaSeparatedParser
 * @author professor
 */
public class MockSocketsServerMain {


    public static void main(String[] args) throws IOException, InterruptedException {
        int port=5555;
        

        MockSocketsServer mss = new MockSocketsServer();
        
        mss.run(port);
       
    }
   
        
    
        
}
 
