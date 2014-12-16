/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.bankservice.service;

import com.google.gson.Gson;
import dk.bankservice.facade.Controller;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import javax.json.JsonObject;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 *
 * @author marekrigan
 */
@WebService(serviceName = "BankWebService")
@Stateless()
public class BankWebService {

    private Controller controller;
    
    public BankWebService()
    {
        controller = new Controller();
    }

    /**
     * Web service operation
     * @param gsonInput
     * @return 
     */
    @WebMethod(operationName = "sendQuote")
    @RequestWrapper(className = "dk.sendQuote_1")
    @ResponseWrapper(className = "dk.sendQuote_1Response")
    public String sendQuote(@WebParam(name = "gsonInput") JsonObject jsonInput) 
    {
        return controller.sendQuote(jsonInput);
    }
}
