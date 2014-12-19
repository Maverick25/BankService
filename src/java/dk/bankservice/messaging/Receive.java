/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.bankservice.messaging;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import java.io.IOException;
import java.util.HashMap;


/**
 *
 * @author marekrigan
 */
public class Receive 
{
    
    private static final String EXCHANGE_NAME = "cphbusiness.bankService";
    private static Connection connection;
    private static Channel channel;
    
    public static HashMap<String,Object> setUpReceiver() throws java.io.IOException
    {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("datdb.cphbusiness.dk");
        factory.setUsername("student");
        factory.setPassword("cph");
        connection = factory.newConnection();
        channel = connection.createChannel();
        
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        String queueName = channel.queueDeclare().getQueue();
        
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(queueName, false, consumer);

        HashMap<String,Object> returnObjects = new HashMap<>();
        
        returnObjects.put("channel",channel);
        returnObjects.put("consumer",consumer);
        
        return returnObjects;
    }
    
    public static void closeConnections() throws IOException
    {
        channel.close();
        connection.close();
    }
   
}
