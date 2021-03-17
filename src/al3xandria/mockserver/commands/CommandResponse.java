/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package al3xandria.mockserver.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Server response to a command
 * @author professor
 */
public class CommandResponse {
    private String order;
    private List <Object[]> responses = new ArrayList<>();
    /**
        Default constructor
    */
    public CommandResponse() {
    }

    /**
     * Create an answer according to the parameters received
     * @param order  Order that generates the response
     * @param responses Responses returned by the server in response to the
     * command indicated by the first parameter. 
     * The first answer is for the first call received, the second 
     * for the second, and so on. Each answer is represented by a list of objects.
     */
    public CommandResponse(String order, Object[][] responses) {
        this.order = order;
        this.responses.addAll(Arrays.asList(responses));
    }

    /**
     * Gets the order associated to the responses
     * @return Order associated to the responses
     */
    public String getOrder() {
        return order;
    }

    /**
     * Sets the order associated to the responses
     * @param order Order associated to the responses
     */
    public void setOrder(String order) {
        this.order = order;
    }

    /**
     * Gets responses returned by the server
     * @return Responses returned by the server
     */
    
    public List<Object[]> getResponses() {
        return responses;
    }

    /**
     * Sets responses returned by the server
     * @param responses Responses returned by the server
     */
    
    public void setResponse(List<Object[]> responses) {
        this.responses = responses;
    }
}
