/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.bankservice.facade;

import com.google.gson.Gson;
import javax.json.JsonObject;

/**
 *
 * @author marekrigan
 */
public class Controller 
{
    public Controller()
    {
        
    }
    
    public String sendQuote(JsonObject jsonInput)
    {
        try
        {
            int creditScore = jsonInput.getInt("creditScore");
            
            String quote;
            if (creditScore < 620)
            {
                quote = "NOOO WAAAY!!!!";
            }
            else
            {
                quote = "Welcome to the brotherhood!";
            }
            
            Gson gson = new Gson();
            return gson.toJson(quote);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
