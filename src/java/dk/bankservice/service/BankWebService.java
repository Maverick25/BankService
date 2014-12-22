/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.bankservice.service;

import com.rabbitmq.client.AMQP.BasicProperties;
import dk.bankservice.controller.CalculateQuote;
import java.io.IOException;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.ejb.Stateless;
import javax.jws.WebParam;

/**
 *
 * @author marekrigan
 */
@WebService(serviceName = "BankWebService")
@Stateless()
public class BankWebService 
{

    /**
     * Web service operation
     * @param request
     * @param props
     * @throws java.io.IOException
     */
    @WebMethod(operationName = "generateQuote")
    public void generateQuote(@WebParam(name = "request") String request, @WebParam(name = "props") BasicProperties props) throws IOException 
    {
        CalculateQuote.calculateInterest(request, props);
    }
    
    
}
