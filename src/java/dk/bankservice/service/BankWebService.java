/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.bankservice.service;

import dk.bankservice.controller.CalculateQuote;
import java.io.IOException;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

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
     * @param loanRequestString
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     */
    @WebMethod(operationName = "calculateQuotes")
    public void calculateQuotes(@WebParam(name = "loanRequestString") String loanRequestString) throws IOException, InterruptedException 
    {
        CalculateQuote.receiveMessages();
    }

}
