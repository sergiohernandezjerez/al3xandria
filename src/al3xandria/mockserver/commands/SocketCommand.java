/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package al3xandria.mockserver.commands;

/**
 * Representation of an order with its parameters
 * @author professor
 */
public class SocketCommand {
    private String order;
    private Object[] parameters;

    /**
     * Default constructor
     */
    public SocketCommand() {
    }

    
    /**
     * Order to be sent to the server with its parameters
     * @param order string that identifies the order
     * @param parameters order parameters 
     */
    public SocketCommand(String order, Object[] parameters) {
        this.order = order;
        this.parameters = parameters;
    }

    
    /**
     * Gets the string that identifies the order
     * @return the string that identifies the order
     */
    public String getOrder() {
        return order;
    }

    /**
     * Sets the string that identifies the order
     * @param order the string that identifies the order
     */
    
    public void setOrder(String order) {
        this.order = order;
    }

    /**
     * Gets the order parameters
     * @return order parameters
     */
    
    public Object[] getParameters() {
        return parameters;
    }

    /**
     * Sets the order parameters
     * @param parameters order parameters
     */
    
    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

}
