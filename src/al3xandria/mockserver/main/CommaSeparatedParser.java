/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package al3xandria.mockserver.main;

import java.util.Arrays;

import al3xandria.mockserver.commands.Parser;
import al3xandria.mockserver.commands.SocketCommand;
/**
 * Parses a String as a SocketCommand and converts any object to a String
 * Values are "comma delimited"
 * @author professor
 */
public class CommaSeparatedParser implements Parser{


    @Override
    public SocketCommand parse(String toParse) {
        String[] aux= toParse.split(",");
        String[] aux2=Arrays.copyOfRange(aux,1,aux.length);

        SocketCommand result=new SocketCommand();

        result.setOrder(aux[0]);
        result.setParameters(aux2);

        return result;
    }

    @Override
    public String unparse(Object []o) {

        Object[] aux= (Object[]) o;
        String result="";
        String separator="";

        for(Object x:aux) {
               result=result+separator+x.toString();
               separator=",";
        }

        return result;
    }

}