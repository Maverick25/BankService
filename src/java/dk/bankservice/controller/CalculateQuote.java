/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.bankservice.controller;

import com.google.gson.Gson;
import com.rabbitmq.client.AMQP;
import dk.bankservice.dto.LoanRequestDTO;
import dk.bankservice.dto.LoanResponseDTO;
import dk.bankservice.messaging.Send;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author marekrigan
 */
public class CalculateQuote 
{
    private static Gson gson;
    
    public static void calculateInterest(String input,AMQP.BasicProperties props) throws IOException
    {
        gson = new Gson();
        
        LoanRequestDTO loanRequestDTO;
        LoanResponseDTO loanResponseDTO;
        
//        AMQP.BasicProperties props = delivery.getProperties();
        AMQP.BasicProperties replyProps = new AMQP.BasicProperties.Builder().correlationId(props.getCorrelationId()).replyTo(props.getReplyTo()).build();

        loanRequestDTO = gson.fromJson(input, LoanRequestDTO.class);

        double interestRate = new Random().nextDouble()*20;

        loanResponseDTO = new LoanResponseDTO(interestRate, loanRequestDTO.getSsn());

        System.out.println(loanResponseDTO.toString());

        sendMessage(loanResponseDTO,replyProps);
          
    }
    
    public static void sendMessage(LoanResponseDTO dto, AMQP.BasicProperties props) throws IOException 
    {
        String message = gson.toJson(dto);
        
        Send.sendMessage(message,props);
    }
}
